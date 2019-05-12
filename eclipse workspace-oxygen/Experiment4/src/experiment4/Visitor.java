package experiment4;

public class Visitor {
	String name;//姓名
	String gender;
	int number;//编号
	boolean isVip;//是否为Vip
	int vipNumber;//vip号码
	int money;//存款
	
	public Visitor() {
		name = "";
		number = 0;
		isVip = false;
		vipNumber = -1;
		money = 1000;
		gender = "男";
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
			if (gender.equals("男")) {
				return name + "先生，请到前面来";
			} else {
				return name + "小姐，请到前面来";
			}
		} else {
			if (gender.equals("男")) {
				return "会员" + name + "先生，请到前面来";
			} else {
				return "会员" + name + "小姐，请到前面来";
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
