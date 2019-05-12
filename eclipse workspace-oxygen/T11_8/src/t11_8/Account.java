package t11_8;

import java.util.Date;

public class Account {
	private int id;
	private double balance;
	private static double annualInterestRate;
	private Date dateCreated;
	
	//空构造法
	public Account() {
		id = 0;
		balance = 0.0;
		annualInterestRate = 0.0;
	}
	
	//id与balance构造法
	public Account(int id, double balance) {
		this.id = id;
		this.balance = balance;
		annualInterestRate = 0.0;
	}
	
	//getId
	public int getId() {
		return id;
	}
	
	//setId
	public void setId(int id) {
		this.id = id;
	}
	
	//getBalance
	public double getBalance() {
		return balance;
	}
	
	//setBalance
	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	//getAnnualInterestRate
	public double getAnnualInterestRate() {
		return annualInterestRate;
	}
	
	//setAnnualInterestRate
	public void setAnnualInterestRate(double annualInterestRate) {
		this.annualInterestRate = annualInterestRate / 100;
	}
	
	//getDateCreated
	public Date getDateCreated() {
		return dateCreated;
	}
	
	//setDateCreated
	public void setDateCreated(long ms) {
		this.dateCreated = new Date(ms);
	}
	
	//getMonthlyInterestRate
	public double getMonthlyInterestRate() {
		return this.annualInterestRate / 12;
	}
	
	//取钱
	public double withDraw(double money) {
		this.balance -= money;
		return money;
	}
	
	//存钱
	public double deposit(double money) {
		this.balance += money;
		return balance;
	}
}
