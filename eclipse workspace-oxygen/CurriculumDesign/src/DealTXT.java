import java.util.ArrayList;

public class DealTXT {
	
	private ArrayList<String> receive = new ArrayList<String>();// ���ܵ�TXT�ļ�

	public DealTXT() {
		
	}
	
	public DealTXT(ArrayList<String> receive) {
		this.receive = receive;
	}
	
	public ArrayList<String> TXTToXML() {

		int endIndex = 0;
		int index = 0;
		for(int i = 0; i < receive.size(); i ++) {
			if(receive.get(i).startsWith("</")) {
				continue;
			}
			int spcaeNumber = startWithCountOfSpace(receive.get(i));
			endIndex = findSameSpaceLine(i, spcaeNumber);
			String space = "";
			for(int j = 0; j < spcaeNumber; j++) {
				space += " ";
			}
			if((endIndex != -1 && endIndex != -2)) {
				receive.add(endIndex, space + "</" + receive.get(i).trim() + ">");
				receive.set(i, space + "<" + receive.get(i).trim() + ">");
			} else if(endIndex == -2) {
				receive.add(receive.size() - index, space + "</" + receive.get(i).trim() + ">");
				receive.set(i, space + "<" + receive.get(i).trim() + ">");
				index ++;
			}
		}
		standardizeXML();// ��׼��
		return receive;
	}
	
	// �Զ��ٸ��ո�������ʼ
	public int startWithCountOfSpace(String str) {
		int count = 0;
		char[] temp = str.toCharArray();
		int i = 0;
		while(temp[i] == ' ') {
			count ++;
			i ++;
		}
		return count;
	}
	
	// �����ͬ�ո��������������
	public int findSameSpaceLine(int startIndex, int spaceNumber) {
		for(int i = startIndex + 1 ; i < receive.size(); i ++) {
			if(startWithCountOfSpace(receive.get(i)) <= spaceNumber) {// ������
				if(i == startIndex + 1) {
					return -1;
				}
				return i;
			} else if(startWithCountOfSpace(receive.get(i)) == spaceNumber - 1) {
				return -1;
			}
		}
		return -2;// û��ƥ�������򷵻�-2
	}
	
	// ��׼��XML
	public void standardizeXML() {

		for(int i = 0; i < receive.size(); i ++) {
			if(receive.get(i).trim().startsWith("<") && !receive.get(i).trim().startsWith("</")) {
				String a = receive.get(i).trim().replace("</", "").replace("<", "").replace(">", "");
				int endIndex = findEnd(i, a);
				int spcaeNumber = startWithCountOfSpace(receive.get(i));
				if(endIndex != -1) {
					dealAttribute(i, endIndex, a, spcaeNumber);// ��������
				}
			}
		}
		indent();// ����
	}
	
	// �ҵ�һ��Ŀ¼�Ľ�β
	public int findEnd(int startNumber, String startString) {
		int endIndex = -1;
		for(int j = startNumber + 1; j < receive.size(); j ++) {
			if(receive.get(j).trim().equals("</" + startString + ">")) {
				endIndex = j;
				return endIndex;
			}
		}
		return endIndex;
	}
	
	// ��������
	public void dealAttribute(int startNumber, int endNumber, String startString, int spaceNumber) {
		String a = new String(startString);
		//System.out.println("startNumber = " + startNumber + " endNumber = " + endNumber);
		int i = 0;
		for(i = startNumber + 1; i < endNumber; i ++) {
			int compareSpaceNumber = startWithCountOfSpace(receive.get(i));
			//System.out.println("spaceNumber = " + spaceNumber + " compareSpaceNumber = " + compareSpaceNumber);
			if(receive.get(i).contains("=\"") && (compareSpaceNumber - 1 == spaceNumber)) {
				a += " ";
				a += receive.get(i).trim();
			}
		}
		int spn = startWithCountOfSpace(receive.get(i));
		receive.set(startNumber, creatBlank(spn) + "<" + a.trim() + ">");
		
		for(i = startNumber + 1; i < endNumber;) {// @TODO ���ܵ��µ�bug���������д�����Ԫ��ʱ�������
			try {
				if(receive.get(i).contains("=\"")) {
					receive.remove(i);
				} else {
					break;
				}
			}
			catch(Exception e) {
				
			}
		}
	}
	
	// ����
	public void indent() {
		for(int i = 0; i < receive.size(); i ++) {
			String a = receive.get(i).trim().split(" ")[0].replace("<", "").replaceAll(">", "");
			//System.out.println("a:" + a);
			int endIndex = findEnd(i, a);
			//System.out.println("endIndex" + endIndex);
			if((i + 2 == endIndex) && (!receive.get(i + 1).startsWith("<") && !receive.get(i + 1).endsWith(">"))) {
				//System.out.println("???");
				String str = receive.get(i + 1).trim() + receive.get(i + 2).trim();
				receive.set(i, receive.get(i) + str);
				receive.remove(i + 1);
				receive.remove(i + 1);
			} else if((i + 1 == endIndex)
					&& receive.get(i).trim().split(" ")[0].replace("<", "").equals(receive.get(i + 1).trim().replace("</", "").replace(">", ""))) {
				receive.set(i, receive.get(i).replace(">", "/>"));
				receive.remove(i + 1);
			}
		}
	}
	
	// �����ո�
	public String creatBlank(int count) {
		String space = "";
		for(int j = 0; j < count; j ++) {
			space += " ";
		}
		return space;
	}
}
