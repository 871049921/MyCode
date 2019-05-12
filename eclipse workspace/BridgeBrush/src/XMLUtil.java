import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;
import java.io.*;
public class XMLUtil
{
//�÷������ڴ�XML�����ļ�����ȡ������������������һ��ʵ������
	public static Object getBean(String args)
	{
		try
		{
			//�����ĵ�����
			DocumentBuilderFactory dFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = dFactory.newDocumentBuilder();
			Document doc;							
			doc = builder.parse(new File("config.xml")); 
			NodeList nl=null;
			Node classNode=null;
			String cName=null;
			nl = doc.getElementsByTagName("className");
			
			if(args.equals("Rain"))
			{
				//��ȡ�����������ı��ڵ�
	            classNode=nl.item(0).getFirstChild();
	            
			}
	         cName=classNode.getNodeValue();
	         //ͨ����������ʵ�����󲢽��䷵��
	         Class c=Class.forName(cName);
		  	 Object obj=c.newInstance();
	         return obj;		
           }   
           	catch(Exception e)
           	{
           		e.printStackTrace();
           		return null;
           	}
		}
}
