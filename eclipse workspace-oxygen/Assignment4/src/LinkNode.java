
public class LinkNode {
	private String date;
	private LinkNode next;
	private int time;

	// 默认构造方法
	public LinkNode() {
		date = null;
		next = null;
		time = -1;
	}

	// 构造法
	public LinkNode(String date) {
		int time;
		this.date = date;
		try {
			date = date.replace(")", "");
			date = date.replace("(", "");
			String[] symbols = date.split(",");
			if (symbols[0].equals("FR")) {
				time = Integer.parseInt(symbols[3]);
				this.time = time;
			} else if (symbols[0].equals("ER")) {
				time = Integer.parseInt(symbols[2]);
				this.time = time;
			} else {
				time = -1;
			}
		} catch (NullPointerException e) {
			time = -1;
		}
	}

	// 获取数据
	public String getDate() {
		return date;
	}

	// 修改数据
	public void setDate(String date) {
		this.date = date;
	}

	// 获得下一个节点
	public LinkNode getNextNode() {
		return next;
	}

	// 修改下一节点
	public void setNextNode(LinkNode next) {
		this.next = next;
	}

	// 获得时间
	public int getTime() {
		return time;
	}
}
