package experiment4;

public class Mermaid extends Staff {
	private int number;
	private int feedTime;
	
	public Mermaid() {
		number = 90000;
		feedTime = 1;
		items = new ShowItem[3];
		interaction = new Interaction[3] ;
		items[0] = new ShowItem("越龙门", 10);
		items[1] = new ShowItem("钻圈", 14);
		items[2] = new ShowItem("跳舞", 16);
		interaction[0] = new Interaction("亲吻", 1);
		interaction[1] = new Interaction("拥抱", 2);
		interaction[2] = new Interaction("打人", 3);
		type = "美人鱼";
	}
	
	public Mermaid(String name) {
		number = 90000;
		feedTime = 1;
		setName(name);
		items = new ShowItem[3];
		interaction = new Interaction[3] ;
		items[0] = new ShowItem("越龙门", 10);
		items[1] = new ShowItem("钻圈", 14);
		items[2] = new ShowItem("跳舞", 16);
		interaction[0] = new Interaction("亲吻", 1);
		interaction[1] = new Interaction("拥抱", 2);
		interaction[2] = new Interaction("打人", 3);
		type = "美人鱼";
	}
	
	public String toString() {
		return "今天表演的美人鱼名叫"  + getName() + ",下面有请她出场";
	}
	
	public int getFeedTime() {
		return feedTime;
	}

}
