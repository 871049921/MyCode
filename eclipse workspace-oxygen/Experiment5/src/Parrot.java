import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Parrot extends OrnamentalAnimalShow {

	public Parrot() {
		setType("鹦鹉");
		imageView = new ImageView(new Image("file:images/Parrot.jpg"));
	}
	
	public Parrot(int number, String name, int age, String gender, int feedTime) {
		super();
		setType("鹦鹉");
		setNumber(number);
		setName(name);
		setAge(age);
		setGender(gender);
		setFeedTime(feedTime);
		setCruiseType("Fly");
		imageView = new ImageView(new Image("file:images/Parrot.jpg"));
	}
	
	@Override
	public String toString() {
		String information = "";
		
		AnimalTrainer animalTrainer = new AnimalTrainer("pgw");//驯兽师名字
		information = "今天表演的" + getType() + "名叫" + getName() + "," + getAge() + 
						"岁,现在有请驯兽师" + animalTrainer.getName() + "带领" + getName() + "出场";
		
		return information;
	}

	@Override
	public boolean equals(int number) {
		if (number == getNumber()) {
			return true;
		} else {
			return false;
		}
	}

}
