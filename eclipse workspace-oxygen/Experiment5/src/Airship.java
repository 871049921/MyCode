import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Airship extends Staff{
	private int number;
	private int feedTime;
	
	public Airship() {
		number = 90002;
		feedTime = 1;
		items = new ShowItem[3];
		interaction = new Interaction[3] ;
		items[0] = new ShowItem("滑翔", 10);
		items[1] = new ShowItem("俯冲", 14);
		items[2] = new ShowItem("开炮", 16);
		interaction[0] = new Interaction("亲吻", 1);
		interaction[1] = new Interaction("拥抱", 2);
		interaction[2] = new Interaction("打人", 3);
		type = "飞艇";
		imageView = new ImageView(new Image("file:images/AirShip.jpg"));
	}
	
	public Airship(String name) {
		number = 90002;
		feedTime = 1;
		setName(name);
		items = new ShowItem[3];
		interaction = new Interaction[3] ;
		items[0] = new ShowItem("滑翔", 10);
		items[1] = new ShowItem("俯冲", 14);
		items[2] = new ShowItem("开炮", 16);
		interaction[0] = new Interaction("亲吻", 1);
		interaction[1] = new Interaction("拥抱", 2);
		interaction[2] = new Interaction("打人", 3);
		type = "飞艇";
		imageView = new ImageView(new Image("file:images/AirShip.jpg"));
	}
	
	public String toString() {
		return "今天表演的飞艇名叫"  + getName() + ",下面有请他出场";
	}
	
	public int getFeedTime() {
		return feedTime;
	}
}
