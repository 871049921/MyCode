
public class Request {

	private String request = null;
	private Request next = null;// 下一个请求节点
	private Request before = null;// 上一个请求节点

	// 空构造方法
	public Request() {
		request = null;
		next = null;
		before = null;
	}

	// 构造方法
	public Request(String request) {
		this.request = request;
	}

	// 获得请求
	public String getRequest() {
		return request;
	}

	// 获得下一个请求节点
	public Request getNextNode() {
		return next;
	}

	// 修改下一个请求节点
	public void setNextNode(Request next) {
		this.next = next;
	}

	// 获得上一个请求节点
	public Request getBeforeNode() {
		return before;
	}

	// 修改上一个请求节点
	public void setBeforeNode(Request before) {
		this.before = before;
	}

	// 修改请求
	public void setRequest(String request) {
		this.request = request;
	}
}
