package experiment3;

public class SeaLoinShow extends MammalShow {
	public SeaLoinShow() {
		
	}
	
	public SeaLoinShow(int number, String name, int age, String gender, int feedTime) {
		super();
		setType("��ʨ");
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