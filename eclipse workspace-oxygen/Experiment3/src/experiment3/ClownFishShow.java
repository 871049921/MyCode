package experiment3;

public class ClownFishShow extends FishShow {
	public ClownFishShow() {
		
	}
	
	public ClownFishShow(int number, String name, int age, String gender, int feedTime) {
		super();
		setType("С����");
		setNumber(number);
		setName(name);
		setAge(age);
		setGender(gender);
		setFeedTime(feedTime);
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
