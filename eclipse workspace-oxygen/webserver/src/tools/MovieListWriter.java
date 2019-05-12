package tools;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.Test;

/**
 * 此类用于创建导航栏中请求的电影列表HTML文件
 * @author Administrator
 */
public class MovieListWriter {

	@Test
	public void createMoveList() throws IOException {	//new File("D:\\WebServerFile\\" + dirName + ".html")
		File[] films = new File("D:\\WebServerFile\\video").listFiles();
		BufferedWriter writer = null;
		
		for (File dir : films) {
			
			String dirName = dir.getName();	//文件夹的名称	"国产"、"欧美"
			writer = new BufferedWriter(new FileWriter(new File("D:\\WebServerFile\\" + dirName + ".html")));
			writer.write(
					"<!DOCTYPE html><html><head><meta charset=\"UTF-8\"><title></title><link rel=\"stylesheet\" type=\"text/css\" href=\"css/reset.css\"/><link rel=\"stylesheet\" type=\"text/css\" href=\"css/movlist.css\"/></head><body>\r\n");
			writer.write("<div class=\"bgcolor movieNav\">\r\n");
			writer.write("<ul >\r\n");
			
			File [] movies = dir.listFiles();	//再遍历目录下的电影文件
			
			for (File movie : movies) {
				writer.write("<li>\r\n");
				writer.write("<a href=\"video/" + dirName + "/" + movie.getName() + "\" target=\"windowframe\">");
				writer.write(movie.getName());
				writer.write("</a>\r\n");
				writer.write("</li>\r\n");
			}
			writer.write("</ul>\r\n");
			writer.write("</div>\r\n");
			writer.write("</body>\r\n");
			writer.write("</html>\r\n");
			writer.close();
		}
	}
}