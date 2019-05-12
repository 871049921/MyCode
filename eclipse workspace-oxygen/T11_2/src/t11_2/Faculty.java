package t11_2;

public class Faculty extends Employee{
	private String officeTime;
	private int level;
	
	public Faculty() {
		officeTime = "8:00am-5:00pm";
		level = 3;
	}
	
	public String getOfficeTime() {
		return officeTime;
	}
	
	public int getLevel() {
		return level;
	}
	
	@Override
	public String toString() {
		return "class: Faculty Name: " + getName(); 
	}
}
