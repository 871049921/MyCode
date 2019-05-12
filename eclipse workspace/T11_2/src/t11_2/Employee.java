package t11_2;

public class Employee extends Person{
	private String office;
	private double salary;
	private String date;
	Mydate myDate = new Mydate();
	
	public Employee() {
		office = "D301";
		salary = 1000.0;
		date = "Year: " + myDate.getYear() + " Month: " + myDate.getMonth() + " Day: " + myDate.getDay();
	}
	
	public String getOffice() {
		return office;
	}
	
	public double getSalary() {
		return salary;
	}
	
	public String getDate() {
		return date;
	}
	
	@Override
	public String toString() {
		return "class: Employee Name: " + getName(); 
	}
}
