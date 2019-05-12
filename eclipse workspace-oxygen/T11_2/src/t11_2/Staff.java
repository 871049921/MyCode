package t11_2;

public class Staff extends Employee{
	private String designation;
	public Staff() {
		designation = "Teacher";
	}
	
	public String getDesignation() {
		return designation;
	}
	
	@Override
	public String toString() {
		return "class: Staff Name: " + getName(); 
	}
}
