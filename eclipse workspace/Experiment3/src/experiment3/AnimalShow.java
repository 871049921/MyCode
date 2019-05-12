package experiment3;

public abstract class AnimalShow {
	protected int number;//���
	protected String type;//����
	protected String name;//����
	protected int age;//����
	protected String gender;//�Ա�
	protected int feedTime;//ιʳʱ��
	protected ShowItem[] items;
	protected Interaction[] interaction;
	
	//�չ��취
	protected AnimalShow() {
		number = 0;
		type = null;
		name = null;
		age = 0;
		gender = null;
		feedTime = 0;
		items = new ShowItem[3];
		interaction = new Interaction[3] ;
		items[0] = new ShowItem("Խ����", 10);
		items[1] = new ShowItem("��Ȧ", 14);
		items[2] = new ShowItem("����", 16);
		interaction[0] = new Interaction("����", 1);
		interaction[1] = new Interaction("ӵ��", 2);
		interaction[2] = new Interaction("����", 3);
	}
	
	public int getNumber() {
		return number;
	}
	
	public void setNumber(int number) {
		this.number = number;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getAge() {
		return age;
	}
	
	public void setAge(int age) {
		this.age = age;
	}
	
	public String getGender() {
		return gender;
	}
	
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public double getFeedTime() {
		return feedTime;
	}
	
	public void setFeedTime(int feedTime) {
		this.feedTime = feedTime;
	}
	
	public ShowItem[] getShowItem() {
		return items;
	}
	
	public void setShowItem(ShowItem[] items) {
		this.items = items;
	}
	
	public Interaction[] getInteraction() {
		return interaction;
	}
	
	public void setInteraction(Interaction[] interaction) {
		this.interaction = interaction;
	}
}
