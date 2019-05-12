package t11_8;

import java.util.ArrayList;

public class NewAccount extends Account{
	private String name;
	private ArrayList<Transaction> transactions = new ArrayList<Transaction>();
	
	public NewAccount(String name, int id, double balance) {
		super(id, balance);
		this.name = name;
	}
	
	@Override
	public double withDraw(double money) {
		setDateCreated(System.currentTimeMillis());
		this.setBalance(getBalance() - money);
		String description = "Time: " + getDateCreated() + " Name: " + getName() + " Id: " + getId() + " AnnualInterestRate: " + getAnnualInterestRate() * 100 + "% Type: W Money: " + money + " Balance: " + getBalance(); 
		Transaction transaction = new Transaction('W', money, getBalance(), description);
		transactions.add(transaction);
		return money;
	}
	
	@Override
	public double deposit(double money) {
		setDateCreated(System.currentTimeMillis());
		this.setBalance(getBalance() + money);
		String description = "Time: " + getDateCreated() + " Name: " + getName() + " Id: " + getId() + " AnnualInterestRate: " + getAnnualInterestRate() * 100 + "% Type: D Money: " + money + " Balance: " + getBalance(); 
		Transaction transaction = new Transaction('D', money, getBalance(), description);
		transactions.add(transaction);
		return money;
	}
	
	//getName
	public String getName() {
		return name;
	}
	
	//setName
	public void setName(String name) {
		this.name = name;
	}
	
	//getArrayList
	public ArrayList<Transaction> getArrList() {
		return transactions;
	}
}
