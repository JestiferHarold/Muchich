import tkinter as tk
from main import process_and_predict

# Input labels
listing = [
    "Age",
    "Annual Income",
    "Loan Amount",
    "Electrical Bill",
    "Number of Household Members",
]

# Radio button questions
radio_questions = [
    "Do you own a house?",
    "Urban area?",
    "Has the person defaulted on credit before?",
]

class App(tk.Tk):
    def __init__(self):
        super().__init__()
        self.title("Real-Time Credit Risk Assessment")
        self.geometry("600x800")
        self.current_frame = None
        self.show_frame(UserInput, None)

    def show_frame(self, frame_class, value):
        if self.current_frame:
            self.current_frame.destroy()

        self.current_frame = frame_class(self, value)
        self.current_frame.pack(fill=tk.BOTH, expand=True)


class UserInput(tk.Frame):
    def __init__(self, parent, val):
        super().__init__(parent)
        self.entries = {}  
        self.radio_vars = {}

        # Create input fields
        for idx, label_text in enumerate(listing):
            self.create_label(label_text, idx)
            self.create_entry(label_text, idx)

        # Create radio buttons
        for idx, question in enumerate(radio_questions, start=len(listing)):
            self.create_label(question, idx)
            self.create_radio_button(question, idx)

        # Submit button
        self.create_submit_button(len(listing) + len(radio_questions))

    def create_label(self, text, row):
        label = tk.Label(self, text=text, font=("Arial", 14))
        label.grid(row=row, column=0, padx=10, pady=5, sticky="w")

    def create_entry(self, label, row):
        entry = tk.Entry(self, width=30, font=("Arial", 14))
        entry.grid(row=row, column=1, padx=10, pady=5)
        self.entries[label] = entry

    def create_radio_button(self, question, row):
        var = tk.IntVar(value=0)
        yes_radio = tk.Radiobutton(self, text="Yes", variable=var, value=1, font=("Arial", 14))
        no_radio = tk.Radiobutton(self, text="No", variable=var, value=0, font=("Arial", 14))
        yes_radio.grid(row=row, column=1, sticky="w")
        no_radio.grid(row=row, column=2, sticky="w")
        self.radio_vars[question] = var

    def create_submit_button(self, row):
        button = tk.Button(self, text="Submit", command=self.handle_submit, font=("Arial", 16))
        button.grid(row=row, column=0, columnspan=3, pady=10)

    def handle_submit(self):
        # Collect inputs
        input_values = {label: entry.get() for label, entry in self.entries.items()}
        input_values.update({question: var.get() for question, var in self.radio_vars.items()})

        # Validate inputs
        for label, value in input_values.items():
            if not value.strip():  
                print(f"Error: {label} cannot be empty!")
                return

        # Call model prediction
        result = process_and_predict(input_values)
        if result is not None:
            self.master.show_frame(FinalOutput, result)


class FinalOutput(tk.Frame):
    def __init__(self, parent, result):
        super().__init__(parent)
        label = tk.Label(self, text=f"Prediction: {result}", font=("Arial", 20))
        label.pack(pady=20)

        button = tk.Button(self, text="Check Another User's Credit Score", command=lambda: parent.show_frame(UserInput, None))
        button.pack(pady=10)


if __name__ == "__main__":
    app = App()
    app.mainloop()

