package request;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.net.URLDecoder;
import java.util.LinkedHashMap;
import java.util.Map;

public class HttpRequest implements Request {

	private Socket s;
	private String url;
	private Map<String, String> headers = new LinkedHashMap<>();
	
	public HttpRequest(){}

	public HttpRequest(Socket s) {
		this.s = s;
	}

	@Override
	public void init() {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(s.getInputStream()));
			String header = reader.readLine();
			url = splitRequest(header);
			while(!(header = reader.readLine()).equals("")){
				String[] strings = header.split(":\\s+");
				headers.put(strings[0], strings[1]);//以键值对的形式保存所有请求头的属性名、属性值。
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	public Map<String, String> getHeaders() {
		return headers;
	}

	@Override
	public String getUrl() {
		if (url.equals("")) {
			url = "index.html";
		}
		return url;
	}

	/**
	 * 解析Http请求行并返回请求的资源名
	 * 
	 * @param url
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public String splitRequest(String url) throws UnsupportedEncodingException {
		int beginIndex = url.indexOf("/");
		int endIndex = url.lastIndexOf(" HTTP/");
		String str = url.substring(beginIndex + 1, endIndex);
		String encode = URLDecoder.decode(str, "UTF-8");
		return encode;
	}
}
