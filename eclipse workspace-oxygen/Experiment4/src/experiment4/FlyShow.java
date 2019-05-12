package experiment4;

public class FlyShow extends Cruise implements Comparable<Cruise> {
	
	public FlyShow() {
		
	}
	
	public FlyShow(String type, int score) {
		this.type = type;
		this.score = score;
	}
	
	@Override
	public void show() {
		System.out.println(type + "·É¹ý...... µÃ·Ö£º" + score);

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
