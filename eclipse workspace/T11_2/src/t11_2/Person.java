package t11_2;

public class Person {
	private final static String name = "Tom";
	private final static String address = "China";
	private final static int Tel = 110;
	private final static String eMail = "123123@qq.com";
	
	public Person() {
		
	}
	
	public String getName() {
		return name;
	}
	
	public String getAddress() {
		return address;
	}
	
	public int getTel() {
		return Tel;
	}
	
	public String getEMail() {
		return eMail;
	}
	
	public String toString() {
		String className = "Person";
		String Information;
		Information = "class: " + className + " Name: " + getName();
		return Information;
	}
}
