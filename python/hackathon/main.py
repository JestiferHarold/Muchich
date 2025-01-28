import tkinter as tk
from tkinter import messagebox
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

        return outcome, high_risk_shap_values, processed_data, explainer, preprocessor
    except Exception as e:
        messagebox.showerror("Error", f"Prediction Error: {e}")
        return None

# GUI Class
class CreditRiskApp(tk.Tk):
    def __init__(self):
        super().__init__()
        self.title("Credit Risk Assessment")
        self.geometry("600x800")
        self.current_frame = None
        self.show_frame(UserInputFrame)

    def show_frame(self, frame_class, *args):
        if self.current_frame:
            self.current_frame.destroy()
        self.current_frame = frame_class(self, *args)
        self.current_frame.pack(fill=tk.BOTH, expand=True)

# User Input Frame
class UserInputFrame(tk.Frame):
    def __init__(self, parent):
        super().__init__(parent)
        self.parent = parent
        self.entries = {}
        self.radio_vars = {}

        # Input fields
        fields = [
            "Age", "Annual Income", "Loan Amount",
            "Electrical Bill", "Number of Household Members"
        ]
        for idx, field in enumerate(fields):
            self.create_label(field, idx)
            self.create_entry(field, idx)

        # Radio button fields
        radio_fields = [
            "Do you own a house?", "Urban area?",
            "Has the person defaulted on credit before?"
        ]
        for idx, field in enumerate(radio_fields, start=len(fields)):
            self.create_label(field, idx)
            self.create_radio_button(field, idx)

        # Submit button
        self.create_submit_button(len(fields) + len(radio_fields))

    def create_label(self, text, row):
        label = tk.Label(self, text=text, font=("Arial", 14))
        label.grid(row=row, column=0, padx=10, pady=5, sticky="w")

    def create_entry(self, field, row):
        entry = tk.Entry(self, width=30, font=("Arial", 14))
        entry.grid(row=row, column=1, padx=10, pady=5)
        self.entries[field] = entry

    def create_radio_button(self, field, row):
        var = tk.IntVar(value=0)
        yes_radio = tk.Radiobutton(self, text="Yes", variable=var, value=1, font=("Arial", 14))
        no_radio = tk.Radiobutton(self, text="No", variable=var, value=0, font=("Arial", 14))
        yes_radio.grid(row=row, column=1, sticky="w")
        no_radio.grid(row=row, column=2, sticky="w")
        self.radio_vars[field] = var

    def create_submit_button(self, row):
        button = tk.Button(self, text="Submit", command=self.handle_submit, font=("Arial", 16))
        button.grid(row=row, column=0, columnspan=3, pady=20)

    def handle_submit(self):
        input_values = {field: entry.get() for field, entry in self.entries.items()}
        input_values.update({field: var.get() for field, var in self.radio_vars.items()})

    # Validate inputs
        for key, value in input_values.items():
        # Validate text inputs
            if isinstance(value, str) and not value.strip():
                messagebox.showerror("Input Error", f"Field '{key}' cannot be empty.")
                return
        # Validate radio button inputs
            if isinstance(value, int) and value not in [0, 1]:
                messagebox.showerror("Input Error", f"Invalid value for '{key}'.")
                return

    # Process prediction
        result = process_and_predict(input_values)
        if result:
            self.parent.show_frame(ResultFrame, *result)

# Result Frame
class ResultFrame(tk.Frame):
    def __init__(self, parent, outcome, shap_values, processed_data, explainer, preprocessor):
        super().__init__(parent)
        self.parent = parent

        # Display Prediction Result
        result_label = tk.Label(self, text=f"Prediction: {outcome}", font=("Arial", 20))
        result_label.pack(pady=20)

        # Back button
        back_button = tk.Button(self, text="Back", command=lambda: parent.show_frame(UserInputFrame))
        back_button.pack(pady=10)


# Run the app
if __name__ == "__main__":
    app = CreditRiskApp()
    app.mainloop()
