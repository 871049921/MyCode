package t11_8;

import java.util.Date;

public class Transaction {
	private Date date;
	private char type;
	private double ammount;
	private double balance;
	private String description;
	
	public Transaction(char type, double ammount, double balance, String description) {
		this.type = type;
		this.ammount = ammount;
		this.balance = balance;
		this.description = description;
	}
	
	//getDate
	public Date getDate() {
		return date;
	}
	
	//setDate
	public void setDate(Date date) {
		this.date = date;
	}
	
	//getType
	public char getType() {
		return type;
	}
	
	//setType
	public void setType(char type) {
		this.type = type;
	}
	
	//getAmmoubt
	public double getAmmount() {
		return ammount;
	}
	
	//setAmmount
	public void setAmmount(double ammount) {
		this.ammount = ammount;
	}
	
	//getBalance
	public double getBalance() {
		return balance;
	}
	
	//setBalance
	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	//getDescription
	public String getDescription() {
		return description;
	}
	
	//getDescription
	public void setDescription(String description) {
		this.description = description;
	}
}
