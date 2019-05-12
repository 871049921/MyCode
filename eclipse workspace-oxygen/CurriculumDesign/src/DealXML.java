import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DealXML {
	// 接收一个文本文件的xml，其格式为一行一行的xml代码,如：
	private ArrayList<String> receive = new ArrayList<String>();// 接受的xml文件
	
	public DealXML() {
		
	}
	
	public DealXML(ArrayList<String> receive) {
		this.receive = receive;
	}
	
	public ArrayList<String> toTXT() {
		Iterator<String> it = receive.iterator();// 迭代器
		int currentIndex = 0;// 检索文件的当前位置
		String firstE = null;
		
		while(it.hasNext()) {// 逐句判断
			String str = it.next();
			if(!isEndInOneString(str)) {// 非一个语句中直接结尾
				if(str.matches("\\<\\?(.*?)\\?\\>")) {// 版本号
					firstE = str.replace("<?", "");
					firstE = firstE.replace("?>", "");
					receive.set(currentIndex, firstE);
					continue;
				}
				str = str.trim();
				if(hasAttribute(str)) {
					firstE = str.split(" ")[0].replace("<", "").replace(">", "");
				} else {
					firstE = str.split(">")[0].replace("<", "");
				}
				String endOfFirstE = "</" + firstE + ">";
				for(int i = currentIndex; i < receive.size(); i ++) {
					if(receive.get(i).contains(endOfFirstE)) {
						receive.set(i, receive.get(i).replace(endOfFirstE, ""));
						break;
					}
				}
			} else {// 一个语句中直接结尾
				firstE = receive.get(currentIndex).split(" ")[0].replace("<", "").replace(">", "");
				receive.set(currentIndex, receive.get(currentIndex).replace("/>", "></" + firstE + ">"));
			}
			currentIndex ++;
		}
		receive = dealElements(receive);
		format(receive);
		receive = dealAttribute(receive);
		
		return receive;// @TODO
	}
	
	// 判断一条语句是否含有属性
	public boolean hasAttribute(String str) {
		Pattern p = Pattern.compile("\\=\"");
		Matcher m = p.matcher(str);
		if (m.find()) {
			return true;
		}
		return false;
	}
	
	// 判断一条有属性的句子是否在这条语句中直接结尾 如：<VersionInfo No="8.48.0.0" yyPath="8.48.0.0" Enable="1" First="0" Index="2"/>
	// 一个语句中直接结尾
	public boolean isEndInOneString(String str) {
		if(str.endsWith("/>")) {
			return true;
		}
		return false;
	}
	
	// 处理属性
	public ArrayList<String> dealAttribute(ArrayList<String> al) {
		ArrayList<String> results = new ArrayList<String>();
		for(int i = 0; i < al.size(); i++) {
			Pattern p1 = Pattern.compile("\\s{1}(.*?)\\=\"(.*?)\"");// a="b"
			Pattern p2 = Pattern.compile("\\<(.*?)\\>");
			Matcher m1 = p1.matcher(al.get(i));
			Matcher m2 = p2.matcher(al.get(i));
			if(m2.find()) {
				results.add(m2.group());
				while(m1.find()) {
					results.add(m1.group());
				}
			} else {
				results.add(al.get(i));
			}
		}
		return results;
	}
	
	public void format(ArrayList<String> receive) {
		
		for(int i = 0; i < receive.size(); i ++) {
			if(hasAttribute(receive.get(i))) {
				receive.set(i, receive.get(i).trim());
				receive.set(i, receive.get(i).replaceFirst("\\s", "\\> "));
			}
		}
	}
	
	public ArrayList<String> dealElements(ArrayList<String> receive) {
		
		ArrayList<String> results = new ArrayList<String>();
		
		for(int i = 0; i < receive.size(); i ++) {
			results.add(receive.get(i));
			if(receive.get(i).lastIndexOf(">") != receive.get(i).length() -1 && receive.get(i).contains(">")) {
				String element = receive.get(i).substring(receive.get(i).lastIndexOf(">") + 1, receive.get(i).length());
				results.add(" " + element);
			}
		}
		
		return results;
	}
}
