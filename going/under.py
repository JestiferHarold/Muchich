import joblib
import pandas as pd
from sklearn.pipeline import Pipeline
from sklearn.impute import SimpleImputer
from sklearn.preprocessing import OneHotEncoder
from sklearn.compose import ColumnTransformer
from sklearn.ensemble import RandomForestClassifier
from sklearn.model_selection import train_test_split
from sklearn.metrics import accuracy_score, classification_report
import lightgbm as lgb
from os import sep

person_age = person_income = loan_amnt = amount_paid = person_home_ownership = is_urban = num_people = cb_person_default_on_file = None

# Function to calculate cb_person_cred_hist_length based on age
def calculate_credit_history_length(age):
    # Assuming they started using credit at age 18
    if age >= 18:
        return age - 18  # Credit history length starts at 18
    else:
        return 0  # If the person is under 18, assume no credit history


# Function to get real-time input and preprocess data
def get_user_input(preprocessor, model, dataset):
    print("Enter the following details:")

    list = [
        person_age,
        person_income,
        loan_amnt,
        amount_paid,
        num_people,
        person_home_ownership,
        is_urban,
        cb_person_default_on_file,
    ]
    
    for i, j in zip(list, dataset):
        j = i

    # Calculate derived features
    monthly_income = person_income / 12  # Monthly income from annual income
    loan_percent_income = loan_amnt / person_income  # Loan percent of income
    electricity_to_income = amount_paid / monthly_income  # Electricity to income ratio
    financial_strain_index = loan_percent_income + electricity_to_income  # Financial strain index

    # Calculate credit history length based on age
    cb_person_cred_hist_length = calculate_credit_history_length(person_age)

    # Prepare the data in a DataFrame for prediction, include monthly_income
    data = pd.DataFrame([{
        'person_age': person_age,
        'person_income': person_income,
        'monthly_income': monthly_income,  # Explicitly add monthly_income here
        'loan_amnt': loan_amnt,
        'loan_percent_income': loan_percent_income,
        'amount_paid': amount_paid,
        'electricity_to_income': electricity_to_income,
        'person_home_ownership': person_home_ownership,
        'is_urban': is_urban,
        'num_people': num_people,
        'cb_person_default_on_file': cb_person_default_on_file,
        'cb_person_cred_hist_length': cb_person_cred_hist_length,  # Include credit history length
        'loan_grade': 'C',  # Default loan grade or can be predicted
        'financial_strain_index': financial_strain_index
    }])

    # Preprocess the data using the preprocessor
    processed_data = preprocessor.transform(data)

    # Make prediction
    prediction = model.predict(processed_data)

    # Display the result
    return prediction


# Load datasets and preprocess data for training
def load_and_preprocess_data():
    # Load datasets
    credit_risk = pd.read_csv(f"C:{sep}Users{sep}aniru{sep}Downloads{sep}credit_risk_dataset.csv")  # Dataset 1: Credit Risk
    electricity_data = pd.read_csv("C:\\Users\\aniru\\Downloads\\archive (1){sep}Household energy bill data.csv")  # Dataset 2: Electricity Data

    # Preprocessing step to convert 'person_income' to 'monthly_income' in credit_risk
    credit_risk['monthly_income'] = credit_risk['person_income'] / 12
    electricity_data.rename(columns={'ave_monthly_income': 'monthly_income'}, inplace=True)

    # Merge datasets on monthly_income (with a tolerance)
    merged_data = pd.merge_asof(
        credit_risk.sort_values('monthly_income'),
        electricity_data.sort_values('monthly_income'),
        on='monthly_income',
        tolerance=credit_risk['monthly_income'].mean() * 0.1,
        direction='nearest'
    )

    # Feature Engineering - Electricity to Income Ratio
    merged_data['electricity_to_income'] = merged_data['amount_paid'] / merged_data['monthly_income']

    # Financial Strain Index (example composite metric)
    merged_data['financial_strain_index'] = merged_data['loan_percent_income'] + merged_data['electricity_to_income']

    # Separate features and target variable
    X = merged_data[[ 
        'person_age',
        'person_income',
        'monthly_income',
        'loan_amnt',
        'loan_percent_income',
        'amount_paid',
        'electricity_to_income',
        'person_home_ownership',
        'is_urban',
        'num_people',
        'cb_person_default_on_file',
        'cb_person_cred_hist_length',
        'loan_grade',
        'financial_strain_index'
    ]]
    y = merged_data['loan_status']  # Target variable

    return X, y


# Define preprocessing pipeline for both numeric and categorical features
def create_preprocessing_pipeline(X):
    numeric_features = X.select_dtypes(include=['float64', 'int64']).columns
    categorical_features = X.select_dtypes(include=['object']).columns

    # Define preprocessing steps for numeric and categorical features
    numeric_transformer = Pipeline(steps=[('imputer', SimpleImputer(strategy='median'))])  # Fill missing numerical data with median
    categorical_transformer = Pipeline(steps=[
        ('imputer', SimpleImputer(strategy='most_frequent')),  # Fill missing categorical data with most frequent
        ('encoder', OneHotEncoder(handle_unknown='ignore', sparse_output=False))  # OneHotEncoding for categorical features
    ])

    # Combine transformers in ColumnTransformer
    preprocessor = ColumnTransformer(
        transformers=[
            ('num', numeric_transformer, numeric_features),
            ('cat', categorical_transformer, categorical_features)
        ])
    
    return preprocessor


# Train the model and save the pipeline
def train_and_save_model(X, y, preprocessor):
    # Preprocess the data
    data_pipeline = Pipeline(steps=[('preprocessor', preprocessor)])
    processed_data = data_pipeline.fit_transform(X)

    # Split the data into 80% training and 20% testing
    X_train, X_test, y_train, y_test = train_test_split(processed_data, y, test_size=0.2, random_state=42)

    # Define and train the model
    lgb_model = lgb.LGBMClassifier(n_estimators=100, learning_rate=0.1, random_state=42)
    lgb_model.fit(X_train, y_train)

    # Make predictions and evaluate the model
    y_pred = lgb_model.predict(X_test)
    accuracy = accuracy_score(y_test, y_pred)
    print(f"Accuracy: {accuracy}")
    print("Classification Report:")
    print(classification_report(y_test, y_pred))

    # Save the entire pipeline (preprocessor + model)
    full_pipeline = Pipeline(steps=[('preprocessor', preprocessor), ('classifier', lgb_model)])
    joblib.dump(full_pipeline, 'credit_risk_model_pipeline.pkl')
    joblib.dump(lgb_model, 'credit_risk_model.pkl')


# Main function for combining everything
def main(dataset):
    # Load and preprocess the data
    X, y = load_and_preprocess_data()

    # Create preprocessing pipeline
    preprocessor = create_preprocessing_pipeline(X)

    # Train the model and save the pipeline
    #train_and_save_model(X, y, preprocessor)

    # Load the trained model and preprocessor for real-time prediction
    full_pipeline = joblib.load('credit_risk_model_pipeline.pkl')

    # Get user input and make prediction
    
    processed_data = get_user_input(full_pipeline.named_steps['preprocessor'], full_pipeline.named_steps['classifier'], dataset)

if __name__ == "__main__":
    main()