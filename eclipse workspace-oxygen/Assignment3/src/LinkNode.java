
public class LinkNode {
	private String date;
	private LinkNode next;
	private int time;

	// Ĭ�Ϲ��췽��
	public LinkNode() {
		date = null;
		next = null;
		time = -1;
	}

	// ���취
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

	// ��ȡ����
	public String getDate() {
		return date;
	}

	// �޸�����
	public void setDate(String date) {
		this.date = date;
	}

	// �����һ���ڵ�
	public LinkNode getNextNode() {
		return next;
	}

	// �޸���һ�ڵ�
	public void setNextNode(LinkNode next) {
		this.next = next;
	}

	// ���ʱ��
	public int getTime() {
		return time;
	}
}
