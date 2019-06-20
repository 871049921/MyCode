import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.util.List;

public class t1 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		try {
			List<Integer> i = null;
			i.size();
		} catch(Exception e) {
			String keyword = URLEncoder.encode(e.toString(), "utf-8");
			Desktop.getDesktop().browse(URI.create("http://www.baidu.com/s?ie=UTF-8&wd=" + keyword));
		}

	}

}
