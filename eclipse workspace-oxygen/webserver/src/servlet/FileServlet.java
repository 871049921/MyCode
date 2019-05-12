package servlet;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

import request.HttpRequest;
import response.HttpResponse;
import tools.BufferedRandomAccessFile;
import tools.GetContentType;

public class FileServlet implements Servlet {

	@Override
	public void service(HttpRequest request, HttpResponse response) {
		PrintStream out = null;
		BufferedOutputStream bos = null;
		BufferedRandomAccessFile raf = null;	//某位国外大牛做的带缓冲区的随机读写流
		try {
			out = new PrintStream(response.getOutputStream());
			String url = request.getUrl();
			File file = new File("D:\\WebServerFile\\" + url); // 路径是存放服务器资源的文件夹
			
			raf = new BufferedRandomAccessFile(file, "r",5000000);//断点传输需要支持随机读取	
			
			//如果需要断点传输，执行以下代码
			if (request.getHeaders().containsKey("Range")) {
				String range = request.getHeaders().get("Range");
				int offset = Integer.valueOf(range.replace("-", "").split("=")[1]);	//获取到本次请求的数据的起点
				
				raf.seek(offset);	//从该起点开始读取
				out.println("HTTP/1.1 206 Partial Content");
				out.println("Accept-Ranges : bytes");
				//响应客户端时响应起点要与请求的起点一致，"-"后响应的传输的文件的长度-1	"/"表示或者，文件的总长度
				out.println("Content-Range: bytes " + offset + "-" + (file.length() - 1) + "/" + file.length());
				out.println("Content-Length: " + (file.length() - offset));	//此次响应内容的长度就是文件总长度 - 请求起点
			} else {
				out.println("HTTP/1.1 200 OK");
				out.println("Content-Length: " + file.length());
			}
			out.println(GetContentType.getContentType(url));
			out.println();
			
			System.out.println("客户端请求文件:" + file.getName() + ", 是否存在: " + file.exists());
			
			if (file.exists()) {
				bos = new BufferedOutputStream(response.getOutputStream(),5000000);
				byte[] bs = new byte[5000000];

				int len = 0;
				while ((len = raf.read(bs)) != -1) {
					bos.write(bs, 0, len);
				}
				raf.close();
			} else {
				response.fileNotFound();
			}
		} catch (IOException e) {
			System.err.println("文件传输终止");
		} finally {
			try {
				if (bos != null)
					bos.close();
			} catch (Exception e) {
				System.err.println("通道关闭异常");
			}
		}
	}
}
