import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.util.List;

public class a {

	public static void main(String[] args) throws IOException {
		try {
			List<Integer> i = null;
			int k = i.size();
			System.out.println(k);
		} catch(Exception e) {
			String keyword = URLEncoder.encode(e.toString(), "utf-8");
			System.out.println(keyword);
			Desktop.getDesktop().browse(URI.create("http://www.baidu.com/s?ie=UTF-8&wd=" + keyword));
		}
	}
}
