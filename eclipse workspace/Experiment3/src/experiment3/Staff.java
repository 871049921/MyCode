package experiment3;

public class Staff {
	private String name;
	private String type;
	
	//ø’ππ‘Ï∑®
	public Staff() {
		name = null;
		type = null;
	}
	
	public Staff(String name, String type) {
		this.name = name;
		this.type = type;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
}
