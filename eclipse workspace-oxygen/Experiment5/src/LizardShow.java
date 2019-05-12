import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class LizardShow extends ReptileShow {
	public LizardShow() {
		setType("����");
		imageView = new ImageView(new Image("file:images/Lizard.jpg"));
	}
	
	public LizardShow(int number, String name, int age, String gender, int feedTime) {
		super();
		setType("����");
		setNumber(number);
		setName(name);
		setAge(age);
		setGender(gender);
		setFeedTime(feedTime);
		setCruiseType("Swim");
		imageView = new ImageView(new Image("file:images/Lizard.jpg"));
	}
	
	public String toString() {
		String information = "";
		
		AnimalTrainer animalTrainer = new AnimalTrainer("pgw");//ѱ��ʦ����
		information = "������ݵ�" + getType() + "����" + getName() + "," + getAge() + 
						"��,��������ѱ��ʦ" + animalTrainer.getName() + "����" + getName() + "����";
		
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
