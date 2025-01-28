import pandas as pd
from sklearn.model_selection import train_test_split
from sklearn.metrics import accuracy_score, classification_report
import lightgbm as lgb
from sklearn.impute import SimpleImputer
from sklearn.preprocessing import OneHotEncoder
from sklearn.compose import ColumnTransformer
from sklearn.pipeline import Pipeline
import joblib
from os import sep


# Load datasets
credit_risk = pd.read_csv(f"dataset{sep}credit_risk_dataset.csv")  # Dataset 1: Credit Risk
electricity_data = pd.read_csv(f"dataset{sep}Household energy bill data.csv")  # Dataset 2: Electricity Data

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

# Define Preprocessing Pipeline for both numeric and categorical features
numeric_features = X.select_dtypes(include=['float64', 'int64']).columns
categorical_features = X.select_dtypes(include=['object']).columns

# Define preprocessing steps for numeric and categorical features
numeric_transformer = Pipeline(steps=[
    ('imputer', SimpleImputer(strategy='median')),  # Fill missing numerical data with median
])

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

# Create a preprocessing pipeline
data_pipeline = Pipeline(steps=[
    ('preprocessor', preprocessor),  # Preprocessing step
])

# Apply the pipeline to preprocess the data (only preparation, no model training)
processed_data = data_pipeline.fit_transform(X)

# Convert processed data back to a DataFrame for further use or saving
# This will flatten the one-hot encoded columns into the dataset
processed_data_df = pd.DataFrame(processed_data, columns=numeric_features.tolist() + list(data_pipeline.named_steps['preprocessor'].transformers_[1][1].named_steps['encoder'].get_feature_names_out(categorical_features)))

# Split the data into 80% training and 20% testing
X_train, X_test, y_train, y_test = train_test_split(processed_data_df, y, test_size=0.2, random_state=42)

# Define the LightGBM model
lgb_model = lgb.LGBMClassifier(n_estimators=100, learning_rate=0.1, random_state=42)

# Train the model
lgb_model.fit(X_train, y_train)

# Make predictions on the test set
y_pred = lgb_model.predict(X_test)

# Evaluate the model
accuracy = accuracy_score(y_test, y_pred)
print(f"Accuracy: {accuracy}")
print("Classification Report:")
print(classification_report(y_test, y_pred))
full_pipeline = Pipeline(steps=[
    ('preprocessor', preprocessor),  # The preprocessing steps
    ('classifier', lgb_model)  # The trained classifier
])

# Save the entire pipeline
joblib.dump(full_pipeline, 'credit_risk_model_pipeline.pkl')
joblib.dump(lgb_model, 'credit_risk_model.pkl')