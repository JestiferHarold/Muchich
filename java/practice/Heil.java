import java.util.*;
import java.io.*;

class bank {

	private String bankName;
	private Set<Employee> employees;
		
	public bank(String BankName) {
		this.bankName = BankName;
	}

	public String getBankName() {
		return this.bankName;
	}


