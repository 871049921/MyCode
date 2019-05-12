package tast2;

public class T9_2 {
	public static void main(String[] args) {
		Stock ORCL = new Stock("ORCL", "Oracle Corporation", 34.5, 34.35);
		double percent = ORCL.getChangePrecent() * 100;
		System.out.println("Name: ORCL, symbol: Oracle Corporation, previousClosingPrice:34.5, currentPrice:34.35, Change: " + percent + "%");
	}
}

class Stock {
	private String symbol;
	private String name;
	private double previousClosingPrice;
	private double currentPrice;
	
	public Stock() {
		symbol = "NULL";
		name = "NULL";
		previousClosingPrice = 0;
		currentPrice = 0;
	}
	
	public Stock(String symbol, String name, double previousClosingPrice, double currentPrice) {
		this.symbol = symbol;
		this.name = name;
		this.previousClosingPrice = previousClosingPrice;
		this.currentPrice = currentPrice;
	}
	
	public double getChangePrecent() {
		return (currentPrice - previousClosingPrice) / previousClosingPrice;
	}
}
