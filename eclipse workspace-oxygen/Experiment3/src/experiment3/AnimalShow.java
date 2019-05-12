package experiment3;

public abstract class AnimalShow {
	protected int number;//编号
	protected String type;//类型
	protected String name;//姓名
	protected int age;//年龄
	protected String gender;//性别
	protected int feedTime;//喂食时间
	protected ShowItem[] items;
	protected Interaction[] interaction;
	
	//空构造法
	protected AnimalShow() {
		number = 0;
		type = null;
		name = null;
		age = 0;
		gender = null;
		feedTime = 0;
		items = new ShowItem[3];
		interaction = new Interaction[3] ;
		items[0] = new ShowItem("越龙门", 10);
		items[1] = new ShowItem("钻圈", 14);
		items[2] = new ShowItem("跳舞", 16);
		interaction[0] = new Interaction("亲吻", 1);
		interaction[1] = new Interaction("拥抱", 2);
		interaction[2] = new Interaction("打人", 3);
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
