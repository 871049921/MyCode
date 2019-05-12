package assignment2;

public class RequestQueue {
	private Request top;//���ж�
	private Request down;//���е�
	
	//��ʼ���������
	public RequestQueue() {
		top = new Request();
		down = new Request();
		down.setNextNode(null);
		down.setBeforeNode(top);
	}
	
	//�������
	public void enQueue(String request) {
		Request newRequest = new Request(request);
		newRequest.setNextNode(top);
		top.setBeforeNode(newRequest);
		top = newRequest;
		if (down.getRequest() == null) {
			down = top;
		}
	}
	
	//�˳�����
	public String deQueue() {
		String request = "";
		request = down.getRequest(); 
		down = down.getBeforeNode();
		//down.setNextNode(null);//�����bug
		return request;
	}
	
	//��ö��е׵�����
	public String getDownRequest() {
		return down.getRequest();
	}
	
	//�ж϶����Ƿ�Ϊ��
	public boolean isEmpty() {
		if (down == null) {
			return true;
		}
		else {
			return false;
		}
	}
}