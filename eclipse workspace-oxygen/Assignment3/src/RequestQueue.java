
public class RequestQueue {
	private Request top;// 队列顶
	private Request down;// 队列底

	// 初始化请求队列
	public RequestQueue() {
		top = new Request();
		down = new Request();
		down.setNextNode(null);
		down.setBeforeNode(top);
	}

	// 进入队列
	public void enQueue(String request) {
		Request newRequest = new Request(request);
		newRequest.setNextNode(top);
		top.setBeforeNode(newRequest);
		top = newRequest;
		try {
			if (down.getRequest() == null) {
				down = top;
			}
		} catch (NullPointerException e) {
			down = top;
		}
	}

	// 退出队列
	public String deQueue() {
		String request = "";
		request = down.getRequest();
		down = down.getBeforeNode();
		// down.setNextNode(null);//这句有bug
		return request;
	}

	// 获得队列底的请求
	public String getDownRequest() {
		return down.getRequest();
	}
	
	// 获得队列头的请求
	public String getTopRequest() {
		return top.getRequest();
	}

	// 判断队列是否为空
	public boolean isEmpty() {
		if (down == null) {
			return true;
		} else {
			return false;
		}
	}
}
