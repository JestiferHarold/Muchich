import joblib
import pandas as pd
import shap


# Function to calculate credit history length based on age
def calculate_credit_history_length(age):
    return max(0, age - 18)


# Function to preprocess user inputs and make predictions
def process_and_predict(input_values):
    try:
        # Load the trained model pipeline
        full_pipeline = joblib.load('credit_risk_model_pipeline.pkl')
        classifier = full_pipeline.named_steps['classifier']
        preprocessor = full_pipeline.named_steps['preprocessor']
        explainer = shap.TreeExplainer(classifier)

        # Extract input values
        person_age = float(input_values["Age"])
        person_income = float(input_values["Annual Income"])
        loan_amnt = float(input_values["Loan Amount"])
        amount_paid = float(input_values["Electrical Bill"])
        num_people = int(input_values["Number of Household Members"])
        owns_house = int(input_values.get("Do you own a house?", 0))
        is_urban = int(input_values.get("Urban area?", 0))
        defaulted_before = int(input_values.get("Has the person defaulted on credit before?", 0))

        # Derived features
        monthly_income = person_income / 12
        loan_percent_income = loan_amnt / person_income
        electricity_to_income = amount_paid / monthly_income
        financial_strain_index = loan_percent_income + electricity_to_income
        cb_person_cred_hist_length = calculate_credit_history_length(person_age)

        # Prepare the data as a DataFrame
        data = pd.DataFrame([{
            'person_age': person_age,
            'person_income': person_income,
            'monthly_income': monthly_income,
            'loan_amnt': loan_amnt,
            'loan_percent_income': loan_percent_income,
            'amount_paid': amount_paid,
            'electricity_to_income': electricity_to_income,
            'person_home_ownership': 'own' if owns_house else 'rent',
            'is_urban': is_urban,
            'num_people': num_people,
            'cb_person_default_on_file': defaulted_before,
            'cb_person_cred_hist_length': cb_person_cred_hist_length,
            'loan_grade': 'C',
            'financial_strain_index': financial_strain_index
        }])

        # Preprocess the data
        processed_data = preprocessor.transform(data)

        # Predict
        prediction = classifier.predict(processed_data)[0]

        # SHAP Explanation
        shap_values = explainer.shap_values(processed_data)
        high_risk_shap_values = shap_values[1] if isinstance(shap_values, list) else shap_values

        # Determine prediction outcome
        outcome = "High Risk" if prediction == 1 else "Low Risk"
        return outcome

    except Exception as e:
        print(f"Error during prediction: {e}")
        return None

