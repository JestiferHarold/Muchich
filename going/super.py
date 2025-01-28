import tkinter as tk
import under

listing = [
    "Age",
    "Annual Income",
    "Loan Amount",
    "Electrical Bill",
    "Number of Household Members",
]

class App(tk.Tk):
    def __init__(self):
        super().__init__()
        self.title("Real-Time Credit Risk Assessment")
        self.geometry("1080x1920")
        self.current_frame = None
        self.show_frame(FinalOutput, None)
        
    def show_frame(self, frame_class, value):
        if self.current_frame:
            self.current_frame.destroy()

        self.current_frame = frame_class(self, value)
        self.current_frame.pack(fill=tk.BOTH, expand=True)

class UserInput(tk.Frame):
    def __init__(self, parent, val):
        super().__init__(parent)

        self.entries = {}  
        self.var1 = tk.IntVar(value=1)
        self.var2 = tk.IntVar(value=1)
        self.var3 = tk.IntVar(value=1)

        for idx, label_text in enumerate(listing):
            self.labeling(label_text, idx)
            self.get_input(label_text, idx)

        radio_questions = [
            "Do you own a house?",
            "Urban area?",
            "Has the person defaulted on credit before?",
        ]
        variables = [self.var1, self.var2, self.var3]

        for idx, (question, variable) in enumerate(zip(radio_questions, variables), start=5):
            self.labeling(question, idx)
            self.radioButton(variable, idx)

        self.submit_button(8)
    
    def radioButton(self, variable, row):
        yes_radio = tk.Radiobutton(self, text="Yes", variable=variable, value=1, font=("Algerian", 16))
        no_radio = tk.Radiobutton(self, text="No", variable=variable, value=0, font=("Algerian", 16))
        yes_radio.grid(row=row, column=1, sticky="w")
        no_radio.grid(row=row, column=2, sticky="w")

    def submit_button(self, row):
        button = tk.Button(self, text="Submit", command=self.get_user_input_values, font=("Algerian", 20))
        button.grid(row=row, column=0, columnspan=3, pady=10)

    def get_user_input_values(self):
        self.input_values = {label: entry.get() for label, entry in self.entries.items()}
        for label, value in self.input_values.items():
            if not value.strip():  
                print(f"Error: {label} cannot be empty!")
                return
        if not under.main(self.input_values):
            self.master.show_frame(FinalOutput, 0)
        else:
            self.master.show_frame(FinalOutput, 1)

    def get_input(self, label, row):
        entry = tk.Entry(self, width=30, font=("Algerian", 20))
        entry.grid(row=row, column=1, padx=10, pady=5)
        self.entries[label] = entry 

    def labeling(self, text_to_be_labeled, row):
        label = tk.Label(self, text=text_to_be_labeled, font=("Algerian", 20))
        label.grid(row=row, column=0, padx=10, pady=5, sticky="w")

class FinalOutput(tk.Frame):
    def __init__(self, parent, val):
        super().__init__(parent)
        label_text = "High Credit" if val == 0 else "Low Credit"
        label = tk.Label(self, text=label_text, font=("Algerian", 24))
        label.pack(pady=20)

        button = tk.Button(self, text="Check Another User's Credit Score", command=lambda: parent.show_frame(UserInput, None))
        button.pack(pady=10)


app = App()
app.mainloop()
