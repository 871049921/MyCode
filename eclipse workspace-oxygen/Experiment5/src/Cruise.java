import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public abstract class Cruise implements CruiseShow ,Comparable<Cruise> { // Ñ²ÓÎÀà
	
	protected String type;
	protected int score;
	protected ImageView imageView;
	
	public Cruise() {
		
	}
	
	public abstract ImageView show(TextArea TA);
	
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
