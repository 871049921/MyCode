import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CrocodileShow extends ReptileShow {
	public CrocodileShow() {
		setType("鳄鱼");
		imageView = new ImageView(new Image("file:images/Crocodile.jpg"));
	}
	
	public CrocodileShow(int number, String name, int age, String gender, int feedTime) {
		super();
		setType("鳄鱼");
		setNumber(number);
		setName(name);
		setAge(age);
		setGender(gender);
		setFeedTime(feedTime);
		setCruiseType("Swim");
		imageView = new ImageView(new Image("file:images/Crocodile.jpg"));
	}
	
	public String toString() {
		String information = "";
		
		AnimalTrainer animalTrainer = new AnimalTrainer("pgw");//驯兽师名字
		information = "今天表演的" + getType() + "名叫" + getName() + "," + getAge() + 
						"岁,现在有请驯兽师" + animalTrainer.getName() + "带领" + getName() + "出场";
		
		return information;
	}
	
	public boolean equals(int number) {
		if (number == getNumber()) {
			return true;
		} else {
			return false;
		}
	}
}
