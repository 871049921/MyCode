package experiment3;

public class Mermaid extends Staff {
	private int number;
	private int feedTime;
	
	public Mermaid() {
		number = 90000;
		feedTime = 1;
	}
	
	public Mermaid(String name) {
		number = 90000;
		feedTime = 1;
		setName(name);
	}
	
	public String toString() {
		return "������ݵ�����������"  + getName() + ",��������������";
	}
	
	public int getFeedTime() {
		return feedTime;
	}
}