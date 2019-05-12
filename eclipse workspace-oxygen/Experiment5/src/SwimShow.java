import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

public class SwimShow extends Cruise implements Comparable<Cruise> {
	
	public SwimShow() {
		
	}
	
	public SwimShow(String type, int score, ImageView imageView) {
		this.type = type;
		this.score = score;
		this.imageView = imageView;
	}
	
	@Override
	public ImageView show(TextArea TA) {
		System.out.println(type + "�ι�...... �÷֣�" + score);
		TA.appendText(type + "�ι�...... �÷֣�" + score + "\n");
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
