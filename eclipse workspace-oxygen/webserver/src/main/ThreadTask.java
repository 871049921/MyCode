package main;

import java.io.IOException;
import java.net.Socket;

import handler.HttpProcessor;
import request.HttpRequest;
import response.HttpResponse;

public class ThreadTask implements Runnable {

	private Socket s;

	public ThreadTask(Socket s) {
		this.s = s;
	}

	@Override
	public void run() {
		try {
			//封装请求信息并初始化
			HttpRequest request = new HttpRequest(s);
			request.init();
			
			//封装响应信息并初始化
			HttpResponse response = new HttpResponse(s);
			response.init();
			
			//Processor处理请求并作响应
			new HttpProcessor().handler(request, response);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
