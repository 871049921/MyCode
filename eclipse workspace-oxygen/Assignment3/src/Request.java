
public class Request {

	private String request = null;
	private Request next = null;// ��һ������ڵ�
	private Request before = null;// ��һ������ڵ�

	// �չ��췽��
	public Request() {
		request = null;
		next = null;
		before = null;
	}

	// ���췽��
	public Request(String request) {
		this.request = request;
	}

	// �������
	public String getRequest() {
		return request;
	}

	// �����һ������ڵ�
	public Request getNextNode() {
		return next;
	}

	// �޸���һ������ڵ�
	public void setNextNode(Request next) {
		this.next = next;
	}

	// �����һ������ڵ�
	public Request getBeforeNode() {
		return before;
	}

	// �޸���һ������ڵ�
	public void setBeforeNode(Request before) {
		this.before = before;
	}

	// �޸�����
	public void setRequest(String request) {
		this.request = request;
	}
}
