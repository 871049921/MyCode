
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;


public class FlyShow extends Cruise implements Comparable<Cruise> {
	
	public FlyShow() {
		
	}
	
	public FlyShow(String type, int score, ImageView imageView) {
		this.type = type;
		this.score = score;
		this.imageView = imageView;
	}
	
	@Override
	public ImageView show(TextArea TA) {
		System.out.println(type + "飞过...... 得分：" + score);
		TA.appendText(type + "飞过...... 得分：" + score + "\n");
		return this.imageView;
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
