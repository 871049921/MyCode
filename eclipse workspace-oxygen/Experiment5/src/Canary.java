import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Canary extends OrnamentalAnimalShow {
	
	public Canary() {
		setType("金丝鸟");
		imageView = new ImageView(new Image("file:images/Canary.jpg"));
	}
	
	public Canary(int number, String name, int age, String gender, int feedTime) {
		super();
		setType("金丝鸟");
		setNumber(number);
		setName(name);
		setAge(age);
		setGender(gender);
		setFeedTime(feedTime);
		setCruiseType("Fly");
		imageView = new ImageView(new Image("file:images/Canary.jpg"));
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
