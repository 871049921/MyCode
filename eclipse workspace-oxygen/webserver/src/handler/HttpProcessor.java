package handler;

import java.io.IOException;
import java.util.regex.Pattern;

import request.HttpRequest;
import response.HttpResponse;
import servlet.FileServlet;
import servlet.SearchServlet;
import servlet.Servlet;

public class HttpProcessor {

	public void handler(HttpRequest request, HttpResponse response) throws IOException {

		String url = request.getUrl();
		Servlet servlet = null;
		
		if (Pattern.matches("\\S{1,}\\.[a-zA-Z0-9]{2,5}", url)) {
			servlet = new FileServlet();
			servlet.service(request, response);
		} else if (url.contains("?searchValue")) {
			servlet = new SearchServlet();
			response.successResponseHeader("text/html");
			servlet.service(request, response);
		} else {
			response.requestError();
		}
	}
}
