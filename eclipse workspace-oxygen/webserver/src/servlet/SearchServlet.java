package servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

import request.HttpRequest;
import response.HttpResponse;

public class SearchServlet implements Servlet {

	@Override 
	public void service(HttpRequest request, HttpResponse response) {
		PrintStream out = null;
		try {
			out = new PrintStream(response.getOutputStream());
			//获取电影名称。
			String value = request.getUrl().split("=")[1];
			
			//将页面样式输出到浏览器
			out.println("<!DOCTYPE html><html><head><meta charset=\"utf-8\"><link rel=\"stylesheet\" type=\"text/css\" href=\"css/reset.css\"/><link rel=\"stylesheet\" type=\"text/css\" href=\"css/search.css\"/></head>");
			out.println("<body><div class=\"container\"><div class=\"billboard bgcolor\"><p class=\"billboardFont\">广告位招租:QQ123456</p></div><div class=\"searchlist bgcolor\">");
			out.println("<span style=\"color: red;\">\"" + value + "\"</span><span>的搜索结果如下：</span>");
			//获取所有文件夹中的电影文件，匹配要搜索的电影名称
			File[] dirs = new File("D:\\WebServerFile\\video").listFiles();
			
			for (File dir : dirs) {
				String dirName = dir.getName();	//父级目录名称，a标签里的电影路径需要用到。
				File[] movies = dir.listFiles();
				for (File movie : movies) {
					if (movie.getName().contains(value)) {
						out.println("<a href=\"video/" + dirName + "/" + movie.getName() + "\" target=\"playframe\">");
						out.println(movie.getName());
						out.println("</a>");
					}
				}
			}
			out.println("</div><div class=\"playwindow bgcolor\">");
			out.println("<iframe name=\"playframe\" allowfullscreen=\"true\" allowtransparency=\"true\">");
			out.println("<video controls=\"controls\" autoplay=\"autoplay\" loop=\"loop\" /></iframe>");
			out.println("</div></div></body></html>");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (out != null)
				out.close();
		}
	}
}
