package experiment4;

public abstract class Cruise implements CruiseShow ,Comparable<Cruise> { // Ñ²ÓÎÀà
	
	protected String type;
	protected int score;
	
	public Cruise() {
		
	}
	
	public abstract void show();
	
	public String getType() {
		return this.type;
	}
	
	public void getType(String type) {
		this.type = type;
	}
	
	public int getScore() {
		return this.score;
	}
	
	public void setScore(int score) {
		this.score = score;
	}
	
	@Override
	public int compareTo(Cruise c) {
		 if (this.score > c.score) {
			 return -1;
		 } else if (this.score < c.score) {
			 return 1;
		 } else {
			 return 1;
		 }
	 }
}
