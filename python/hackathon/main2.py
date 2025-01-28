import sys
import joblib
import pandas as pd
import shap
from PyQt5.QtWidgets import (
    QApplication,
    QMainWindow,
    QVBoxLayout,
    QHBoxLayout,
    QLabel,
    QLineEdit,
    QPushButton,
    QRadioButton,
    QButtonGroup,
    QWidget,
    QMessageBox,
)
from matplotlib.backends.backend_qt5agg import FigureCanvasQTAgg as FigureCanvas
import matplotlib.pyplot as plt


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

        return outcome, high_risk_shap_values, data
    except Exception as e:
        QMessageBox.critical(None, "Error", f"Prediction Error: {e}")
        return None


class CreditRiskApp(QMainWindow):
    def __init__(self):
        super().__init__()
        self.setWindowTitle("Credit Risk Assessment")
        self.setGeometry(100, 100, 800, 600)
        self.main_widget = UserInputWidget(self)
        self.setCentralWidget(self.main_widget)


class UserInputWidget(QWidget):
    def __init__(self, parent=None):
        super().__init__(parent)
        self.layout = QVBoxLayout()

        # Input Fields
        self.inputs = {}
        fields = [
            "Age", "Annual Income", "Loan Amount",
            "Electrical Bill", "Number of Household Members"
        ]
        for field in fields:
            self.create_input_row(field)

        # Radio Buttons
        self.radio_buttons = {}
        self.create_radio_buttons("Do you own a house?")
        self.create_radio_buttons("Urban area?")
        self.create_radio_buttons("Has the person defaulted on credit before?")

        # Submit Button
        self.submit_button = QPushButton("Submit")
        self.submit_button.clicked.connect(self.handle_submit)
        self.layout.addWidget(self.submit_button)

        self.setLayout(self.layout)

    def create_input_row(self, label_text):
        layout = QHBoxLayout()
        label = QLabel(label_text)
        input_field = QLineEdit()
        layout.addWidget(label)
        layout.addWidget(input_field)
        self.layout.addLayout(layout)
        self.inputs[label_text] = input_field

    def create_radio_buttons(self, question):
        layout = QHBoxLayout()
        label = QLabel(question)
        layout.addWidget(label)

        yes_radio = QRadioButton("Yes")
        no_radio = QRadioButton("No")
        no_radio.setChecked(True)

        button_group = QButtonGroup(self)
        button_group.addButton(yes_radio)
        button_group.addButton(no_radio)
        self.radio_buttons[question] = button_group

        layout.addWidget(yes_radio)
        layout.addWidget(no_radio)
        self.layout.addLayout(layout)

    def handle_submit(self):
        # Collect input values
        input_values = {key: input_field.text() for key, input_field in self.inputs.items()}
        input_values.update({
            key: 1 if group.checkedButton().text() == "Yes" else 0
            for key, group in self.radio_buttons.items()
        })

        # Validate inputs
        for key, value in input_values.items():
            if not str(value).strip():
                QMessageBox.warning(self, "Input Error", f"Field '{key}' cannot be empty.")
                return

        # Process Prediction
        result = process_and_predict(input_values)
        if result:
            outcome, shap_values, data = result
            self.show_result(outcome, shap_values, data)

    def show_result(self, outcome, shap_values, data):
        result_widget = ResultWidget(outcome, shap_values, data, self)
        self.parent().setCentralWidget(result_widget)


class ResultWidget(QWidget):
    def __init__(self, outcome, shap_values, data, parent=None):
        super().__init__(parent)
        self.layout = QVBoxLayout()

        # Display Outcome
        result_label = QLabel(f"Prediction: {outcome}")
        result_label.setStyleSheet("font-size: 20px; font-weight: bold;")
        self.layout.addWidget(result_label)

        # Display SHAP Plot
        shap.summary_plot(shap_values, data, show=False)
        fig = plt.gcf()
        canvas = FigureCanvas(fig)
        self.layout.addWidget(canvas)

        # Back Button
        back_button = QPushButton("Back")
        back_button.clicked.connect(self.handle_back)
        self.layout.addWidget(back_button)

        self.setLayout(self.layout)

    def handle_back(self):
        input_widget = UserInputWidget(self)
        self.parent().setCentralWidget(input_widget)


if __name__ == "__main__":
    app = QApplication(sys.argv)
    main_window = CreditRiskApp()
    main_window.show()
    sys.exit(app.exec())
