package experiment4;

public class Staff {
	protected String name;
	protected String type;
	protected ShowItem[] items;
	protected Interaction[] interaction;
	protected int score = (int)(Math.random() * 10);
	
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
	
	public ShowItem[] getShowItem() {
		return items;
	}
	
	public Interaction[] getInteraction() {
		return interaction;
	}
	
	public int getScore() {
		return this.score;
	}
	
	public void setScore(int score) {
		this.score = score;
	}
}
