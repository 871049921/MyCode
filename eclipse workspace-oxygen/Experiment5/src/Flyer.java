import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Flyer extends Staff {
	private int number;
	private int feedTime;
	
	public Flyer() {
		number = 90001;
		feedTime = 1;
		items = new ShowItem[3];
		interaction = new Interaction[3] ;
		items[0] = new ShowItem("滑翔", 10);
		items[1] = new ShowItem("俯冲", 14);
		items[2] = new ShowItem("开炮", 16);
		interaction[0] = new Interaction("亲吻", 1);
		interaction[1] = new Interaction("拥抱", 2);
		interaction[2] = new Interaction("打人", 3);
		type = "空中飞人";
		imageView = new ImageView(new Image("file:images/Flyer.jpg"));
	}
	
	public Flyer(String name) {
		number = 90001;
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
		type = "空中飞人";
		imageView = new ImageView(new Image("file:images/Flyer.jpg"));
	}
	
	public String toString() {
		return "今天表演的空中飞人名叫"  + getName() + ",下面有请他出场";
	}
	
	public int getFeedTime() {
		return feedTime;
	}
}
