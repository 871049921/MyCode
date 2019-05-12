import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Canary extends OrnamentalAnimalShow {
	
	public Canary() {
		setType("��˿��");
		imageView = new ImageView(new Image("file:images/Canary.jpg"));
	}
	
	public Canary(int number, String name, int age, String gender, int feedTime) {
		super();
		setType("��˿��");
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
		
		AnimalTrainer animalTrainer = new AnimalTrainer("pgw");//ѱ��ʦ����
		information = "������ݵ�" + getType() + "����" + getName() + "," + getAge() + 
						"��,��������ѱ��ʦ" + animalTrainer.getName() + "����" + getName() + "����";
		
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
