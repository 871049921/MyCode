package experiment4;

public class SwimShow extends Cruise implements Comparable<Cruise> {
	
	public SwimShow() {
		
	}
	
	public SwimShow(String type, int score) {
		this.type = type;
		this.score = score;
	}
	
	@Override
	public void show() {
		System.out.println(type + "游过...... 得分：" + score);

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
