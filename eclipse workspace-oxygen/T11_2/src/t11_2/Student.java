package t11_2;

public class Student extends Person{
	private final static int grade = 1;
	
	public Student() {
		
	}
	
	public int getGrade() {
		return grade;
	}
	
	@Override
	public String toString() {
		return "class: Student Name: " + getName(); 
	}
}
