

public class Interaction {
	private String name;
	private int time;
	
	// �չ��취
	public Interaction() {
		name = null;
		time = 0;
	}
	
	//���ι��취
	public Interaction(String name,int time) {
		this.name = name;
		this.time = time;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public double getTime() {
		return time;
	}
	
	public void setTime(int time) {
		this.time = time;
	}
}
