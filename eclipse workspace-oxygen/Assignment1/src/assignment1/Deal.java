package assignment1;

import java.util.regex.*;

public class Deal {//@TODO
	private String[] str = {""};//原表达式
	private String result;//结果
	Stack stack;//生成栈模型
	
	//接收一个数组
	public Deal(String[] str) {
		this.str = str;
		stack = new Stack();//生成一个栈
	}
	
	//对每条语句分割 并操作
	public void divideArr() {
		for (int i = 0; str[i] != null; i++) {
//			if (i == 0) {
//				Scanner input = new Scanner(System.in);
//				str[i] = input.next();
//			}
			//System.out.println(str[i]);
			String original = str[i];
			str[i] = deleteAllBlankSpace(str[i]);//删除表达式中所有空白
			if (isValid(str[i]) == true) {
				str[i] = DealAfterDeleteAllBlankSpace(str[i]);// 处理删除空格后的表达式
				str[i] = stack.toSuffixExpression(str[i]);//将原表达式变为后缀表达式
				result = stack.calculateSuffixExpression(str[i]);//计算后缀表达式
				printRightResults(original, result);//打印正确结果
			}
			else {
				
				continue;
				//printWrongResults();//打印错误结果
			}
		}
	}
	
	//@TODO 判断该表达式格式是否正确,用正则表达式
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
				System.out.println("括号数量不相同");
				return false;
			}
		}
		if (numberOfBracket != 0) {
			System.out.println("括号数量不相同");
			return false;
		}
		String errors[] = {"[^0-9\\+\\-\\/\\*\\(\\)]", "(\\(\\))", "(\\(\\+)", 
							"(\\(\\-)", "(\\(\\*)", "(\\(\\/)", "[\\+\\-\\*\\/][\\+\\-\\*\\/]", 
							"(\\+\\))", "(\\-\\))", "(\\*\\))", "(\\/\\))", "[\\d][\\(]", "[\\)][\\d]", "^[\\+\\-\\*\\/]", "[\\+\\-\\*\\/]$"};
		String errorsName[] = {"存在非法字符", "存在括号内什么都没有", "存在(+", "存在(-", "存在(*", "存在(/", "存在连续运算符", "存在+)", "存在-)", 
								"存在*)", "存在/)", "存在左括号左边有数字", "存在右括号右边有数字", "开头存在运算符", "结尾存在运算符"};
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
	
	//@TODO 计算一条格式正确的语句的后缀表达式，并返回整个字符串
	public String calculate(String str) {
		String result = str;
		
		
		//@TODO
		
		return result;
	}
	
	//打印正确的每条语句计算后的结果
	public void printRightResults(String str, String result){
		System.out.println(str + " = " + result);
	}
	
	//打印出错的每条语句的结果
	public void printWrongResults() {
		
	}
	
	//删除字符串里所有的空格
	public String deleteAllBlankSpace(String str) {
		String strDel;
		strDel = str.replace(" ", "");
		//strDel = str.replace("	", "");
		//System.out.println(strDel);
		return strDel;
	}
	
	//处理删除所有空白后的原表达式
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
	
	//判断是否为数字
	public boolean isNumber(char ch) {
        if (ch >= '0' && ch <= '9') {
        	return true;
        }
        else {
        	return false;
        } 
    }
	
	//判断是否为数字
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
