package response;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;

public class HttpResponse implements Response {
	
	private Socket s;
	private PrintStream out;
	
	public HttpResponse(){}
	
	public HttpResponse(Socket s) {
		this.s = s;
	}

	public void init() throws IOException {
		out = new PrintStream(s.getOutputStream());
		System.out.println("客户端已连接，地址: " + s.getInetAddress().getHostAddress() + ", 端口: " + s.getPort());
	}
	
	
	/**
	 * 发送响应行信息
	 * @param type : 内容的类型
	 */
	public void successResponseHeader(String type){
		out.println("HTTP/1.1 200 OK");
		out.println("Content-Type: " + type);	//	image/png	image/jpg	image/gif
		out.println();
	}
	
	/**
	 * 文件不存在
	 */
	public void fileNotFound(){
		out.println("HTTP/1.1 404 File Not Found");
		out.println("Content-Type: text/html");
		out.println();
		out.println("<HTML><body><script>alert('请求的资源不存在')</script><h1 style=\"color: red;\"> File Not Found...</h1></body></HTML>");
		out.close();
	}
	
	/**
	 * 请求格式非法
	 */
	public void requestError(){
		successResponseHeader("text/html");
		out.println("<HTML><body><script>alert('请求格式错误')</script><h1 style=\"color: red;\">error format</h1></body></HTML>");
		out.close();
	}

	@Override
	public OutputStream getOutputStream() throws IOException {
		return s.getOutputStream();
	}
}
