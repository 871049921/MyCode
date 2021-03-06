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
		items[0] = new ShowItem("����", 10);
		items[1] = new ShowItem("����", 14);
		items[2] = new ShowItem("����", 16);
		interaction[0] = new Interaction("����", 1);
		interaction[1] = new Interaction("ӵ��", 2);
		interaction[2] = new Interaction("����", 3);
		type = "��ͧ";
		imageView = new ImageView(new Image("file:images/AirShip.jpg"));
	}
	
	public Airship(String name) {
		number = 90002;
		feedTime = 1;
		setName(name);
		items = new ShowItem[3];
		interaction = new Interaction[3] ;
		items[0] = new ShowItem("����", 10);
		items[1] = new ShowItem("����", 14);
		items[2] = new ShowItem("����", 16);
		interaction[0] = new Interaction("����", 1);
		interaction[1] = new Interaction("ӵ��", 2);
		interaction[2] = new Interaction("����", 3);
		type = "��ͧ";
		imageView = new ImageView(new Image("file:images/AirShip.jpg"));
	}
	
	public String toString() {
		return "������ݵķ�ͧ����"  + getName() + ",��������������";
	}
	
	public int getFeedTime() {
		return feedTime;
	}
}
