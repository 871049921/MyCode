package experiment3;

public class Mermaid extends Staff {
	private int number;
	private int feedTime;
	private ShowItem[] items;
	private Interaction[] interaction;
	
	public Mermaid() {
		number = 90000;
		feedTime = 1;
		items = new ShowItem[3];
		interaction = new Interaction[3] ;
		items[0] = new ShowItem("Խ����", 10);
		items[1] = new ShowItem("��Ȧ", 14);
		items[2] = new ShowItem("����", 16);
		interaction[0] = new Interaction("����", 1);
		interaction[1] = new Interaction("ӵ��", 2);
		interaction[2] = new Interaction("����", 3);
	}
	
	public Mermaid(String name) {
		number = 90000;
		feedTime = 1;
		setName(name);
		items = new ShowItem[3];
		interaction = new Interaction[3] ;
		items[0] = new ShowItem("Խ����", 10);
		items[1] = new ShowItem("��Ȧ", 14);
		items[2] = new ShowItem("����", 16);
		interaction[0] = new Interaction("����", 1);
		interaction[1] = new Interaction("ӵ��", 2);
		interaction[2] = new Interaction("����", 3);
	}
	
	public String toString() {
		return "������ݵ�����������"  + getName() + ",��������������";
	}
	
	public int getFeedTime() {
		return feedTime;
	}
	
	public ShowItem[] getShowItem() {
		return items;
	}
	
	public Interaction[] getInteraction() {
		return interaction;
	}
}
