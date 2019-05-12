package experiment4;

public class Visitor {
	String name;//����
	String gender;
	int number;//���
	boolean isVip;//�Ƿ�ΪVip
	int vipNumber;//vip����
	int money;//���
	
	public Visitor() {
		name = "";
		number = 0;
		isVip = false;
		vipNumber = -1;
		money = 1000;
		gender = "��";
	}
	
	public Visitor(String name, int number, boolean isVip, String gender) {
		this.name = name;
		this.number = number;
		this.isVip = isVip;
		vipNumber = -1;
		money = 1000;
		this.gender = gender;
	}
	
	@Override
	public String toString() {
		if (isVip == false) {
			if (gender.equals("��")) {
				return name + "�������뵽ǰ����";
			} else {
				return name + "С�㣬�뵽ǰ����";
			}
		} else {
			if (gender.equals("��")) {
				return "��Ա" + name + "�������뵽ǰ����";
			} else {
				return "��Ա" + name + "С�㣬�뵽ǰ����";
			}
		}
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getNumber() {
		return this.number;
	}
	
	public void setNumber(int number) {
		this.number = number;
	}
	
	public boolean getIsVip() {
		return this.isVip;
	}
	
	public void setIsVip(boolean isVip) {
		this.isVip = isVip;
	}
	
	public int getVipNumber() {
		return this.vipNumber;
	}
	
	public void setVipNumber(int vipNumber) {
		this.vipNumber = vipNumber;
	}
	
	public int getMoney() {
		return this.money;
	}
	
	public void setMoney(int money) {
		this.money = money;
	}
}
