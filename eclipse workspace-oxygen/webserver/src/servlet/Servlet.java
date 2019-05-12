package servlet;

import request.HttpRequest;
import response.HttpResponse;

public interface Servlet {
	
	void service(HttpRequest request,HttpResponse response);
}
