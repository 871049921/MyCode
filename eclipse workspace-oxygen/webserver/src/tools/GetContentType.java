package tools;

import java.io.File;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class GetContentType {
	
	private static Document doc;
	static{
		SAXReader reader = new SAXReader();
		try {
			doc = reader.read(new File("src/config/type.xml"));
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
	
	private GetContentType() {}

	// ������url��׺����ȡXML��ǩ�е�ֵ
	public static String getContentType(String url){
		Element e = doc.getRootElement();
		return e.element(url.split("\\.")[1]).getStringValue();
	}
}