package assignment1;

import java.util.regex.*;

public class Deal {//@TODO
	private String[] str = {""};//ԭ���ʽ
	private String result;//���
	Stack stack;//����ջģ��
	
	//����һ������
	public Deal(String[] str) {
		this.str = str;
		stack = new Stack();//����һ��ջ
	}
	
	//��ÿ�����ָ� ������
	public void divideArr() {
		for (int i = 0; str[i] != null; i++) {
//			if (i == 0) {
//				Scanner input = new Scanner(System.in);
//				str[i] = input.next();
//			}
			//System.out.println(str[i]);
			String original = str[i];
			str[i] = deleteAllBlankSpace(str[i]);//ɾ�����ʽ�����пհ�
			if (isValid(str[i]) == true) {
				str[i] = DealAfterDeleteAllBlankSpace(str[i]);// ����ɾ���ո��ı��ʽ
				str[i] = stack.toSuffixExpression(str[i]);//��ԭ���ʽ��Ϊ��׺���ʽ
				result = stack.calculateSuffixExpression(str[i]);//�����׺���ʽ
				printRightResults(original, result);//��ӡ��ȷ���
			}
			else {
				
				continue;
				//printWrongResults();//��ӡ������
			}
		}
	}
	
	//@TODO �жϸñ��ʽ��ʽ�Ƿ���ȷ,��������ʽ
	public static boolean isValid(String str) {
		int numberOfBracket = 0;
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == '(') {
				numberOfBracket++;
			}
			else if (str.charAt(i) == ')') {
				numberOfBracket--;
			}
			if (numberOfBracket < 0) {
				System.out.println("������������ͬ");
				return false;
			}
		}
		if (numberOfBracket != 0) {
			System.out.println("������������ͬ");
			return false;
		}
		String errors[] = {"[^0-9\\+\\-\\/\\*\\(\\)]", "(\\(\\))", "(\\(\\+)", 
							"(\\(\\-)", "(\\(\\*)", "(\\(\\/)", "[\\+\\-\\*\\/][\\+\\-\\*\\/]", 
							"(\\+\\))", "(\\-\\))", "(\\*\\))", "(\\/\\))", "[\\d][\\(]", "[\\)][\\d]", "^[\\+\\-\\*\\/]", "[\\+\\-\\*\\/]$"};
		String errorsName[] = {"���ڷǷ��ַ�", "����������ʲô��û��", "����(+", "����(-", "����(*", "����(/", "�������������", "����+)", "����-)", 
								"����*)", "����/)", "�������������������", "�����������ұ�������", "��ͷ���������", "��β���������"};
		for (int i = 0;i < errors.length; i++) {
			Pattern p = Pattern.compile(errors[i]);
			Matcher m = p.matcher(str);
			if (m.find()) {
				System.out.println(errorsName[i]);
				return false;
			}
		}
		return true;
	}
	
	//@TODO ����һ����ʽ��ȷ�����ĺ�׺���ʽ�������������ַ���
	public String calculate(String str) {
		String result = str;
		
		
		//@TODO
		
		return result;
	}
	
	//��ӡ��ȷ��ÿ���������Ľ��
	public void printRightResults(String str, String result){
		System.out.println(str + " = " + result);
	}
	
	//��ӡ�����ÿ�����Ľ��
	public void printWrongResults() {
		
	}
	
	//ɾ���ַ��������еĿո�
	public String deleteAllBlankSpace(String str) {
		String strDel;
		strDel = str.replace(" ", "");
		//strDel = str.replace("	", "");
		//System.out.println(strDel);
		return strDel;
	}
	
	//����ɾ�����пհ׺��ԭ���ʽ
	public String DealAfterDeleteAllBlankSpace(String str) {
		String result = "";
		int i;
		for (i = 0;i < str.length() - 1; ++i) {
			if (isNumber(str.charAt(i)) && isNumber(str.charAt(i + 1)) ) {
				result += str.charAt(i);
			}
			else {
				result = result + str.charAt(i) + " ";
			}
		}
		result += str.charAt(str.length() - 1);
		//System.out.println(result);
		return result;
	}
	
	//�ж��Ƿ�Ϊ����
	public boolean isNumber(char ch) {
        if (ch >= '0' && ch <= '9') {
        	return true;
        }
        else {
        	return false;
        } 
    }
	
	//�ж��Ƿ�Ϊ����
	public boolean isNumber(String str) {
		char[] chs = str.toCharArray();
        int number = 0;
        for(int i = 0; i < chs.length; i++){
            if(chs[i] >= '0' && chs[i] <= '9') {
            	number++;
            }
        }
        if (chs.length == number) {
        	return true;
        }
        else {
        	return false;
        }
    }
}
