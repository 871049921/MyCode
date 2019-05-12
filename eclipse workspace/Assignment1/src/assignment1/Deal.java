package assignment1;

import java.util.regex.*;

public class Deal {
	private String[] str = {""};//原表达式
	private String result;//结果
	Stack stack;//生成栈模型
	private static int indexOfoutputArr = 0;
	static String[] outputArr = new String[1000000];//输出的信息
	private static String errors[] = {"[^0-9\\+\\-\\/\\*\\(\\)]", "(\\(\\))", 
			"(\\(\\*)", "(\\(\\/)", "[\\+\\-\\*\\/][\\+\\-\\*\\/]", 
			"(\\+\\))", "(\\-\\))", "(\\*\\))", "(\\/\\))", "[\\d][\\(]", "[\\)][\\d]", "[\\+\\-\\*\\/]$", "^\\*.", "^\\/."};
//	private static String errorsName[] = {"存在非法字符", "存在括号内什么都没有", "存在(*", "存在(/", "存在连续运算符", "存在+)", "存在-)", 
//	"存在*)", "存在/)", "存在左括号左边有数字", "存在右括号右边有数字", "结尾存在运算符", "存在*开头", "存在/开头"};
	private static String errorsName[] = {"存在非法字符", "表达式错误", "表达式错误", "表达式错误", "表达式错误", "表达式错误", "表达式错误", 
			"表达式错误", "表达式错误", "表达式错误", "表达式错误", "表达式错误", "表达式错误", "表达式错误"};
	
	//接收一个数组
	public Deal(String[] str) {
		this.str = str;
		stack = new Stack();//生成一个栈
	}
	
	//对每条语句分割 并操作
	public String[] divideArr() {
		for (int i = 0; str[i] != null; i++) {
			String originalWrong = str[i];
			if(isEmptyString(str[i])) {
				printWrongResults(originalWrong, "存在空表达式");
				continue;
			}
			result = str[i] = deleteAllBlankSpace(str[i]);//删除原表达式中的空格
			if (isrepeated(result, i)) {
				continue;
			}
			String original = str[i];
			if (isValid(result, originalWrong)) {
				result = dealWtihExpection(result);
				if (result == "ERROR") {
					printWrongResults(originalWrong, "存在除数为零");//打印错误结果
					continue;
				}
				printRightResults(original, result);//打印正确结果并将其输入到文件里
			}
			else {
				continue;
			}
		}
		return outputArr;
	}
	
	//判断该表达式格式是否正确,用正则表达式
	public static boolean isValid(String str,String originalWrong) {//省略参数
		if (!isNumberOfBracketRight(str, indexOfoutputArr)) {//判断左右括号数量是否匹配
			printWrongResults(originalWrong, "左右括号数量不匹配");
			return false;
		}
		for (int i = 0;i < errors.length; i++) {
			Pattern p = Pattern.compile(errors[i]);
			Matcher m = p.matcher(str);
			if (m.find()) {
				outputArr[indexOfoutputArr] = originalWrong + "\r\nERROR\r\n#" + errorsName[i];
				System.out.println(outputArr[indexOfoutputArr]);
				indexOfoutputArr++;
				return false;
			}
		}
		return true;
	}
	
	//打印正确的每条语句计算后的结果,并将其输入到文件里
	public void printRightResults(String str, String result){
		String s = "";
		if (result != "") {
			s = str + "=" + result;
		}
		else {//只有数字
			s = str + "=" + str;
		}
		outputArr[indexOfoutputArr] = s;
		System.out.println(s);
		indexOfoutputArr++;
	}
	
	//打印出错的每条语句的结果
	public static void printWrongResults(String originalWrong, String wrongInformation) {
		outputArr[indexOfoutputArr] = originalWrong + "\r\nERROR\r\n#" + wrongInformation;
		System.out.println(outputArr[indexOfoutputArr]);
		indexOfoutputArr++;
	}
	
	//删除字符串里所有的空格
	public String deleteAllBlankSpace(String str) {
		String strDel;
		strDel = str.replace(" ", "");
		strDel = strDel.replace("\t", "");
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
	
	//判断左右括号数量是否匹配
	public static boolean isNumberOfBracketRight(String str, int index) {
		int numberOfBracket = 0;
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == '(') {
				numberOfBracket++;
			}
			else if (str.charAt(i) == ')') {
				numberOfBracket--;
			}
			if (numberOfBracket < 0) {
				return false;
			}
		}
		if (numberOfBracket != 0) {
			return false;
		}
		return true;
	}
	
	//判断首字符是否是加减号若是则补0
	public String starWithAddOrSub(String str) {
		if (str.charAt(0) == '+' || str.charAt(0) == '-') {
			str = "0" + str;
		}
		return str;
	}
	
	//判断是否为空字符串
	public boolean isEmptyString(String str) {
		if (str.matches("^\\s*$")) {
			return true;
		}
		else {
			return false;
		}
	}
	
	//判断是否该字符穿是否重复
	public boolean isrepeated(String str1, int index) {
		for (int i = 0; i <  index; i++) {
			if (str1.equals(this.str[i])) {
				return true;
			}
		}
		return false;
	}
	
	//规范化表达式
	public String regularyException(String str) {
		str = str.replace("(+", "(0+");//补0
		str = str.replace("(-", "(0-");//补0
		str = starWithAddOrSub(str);//判断是否以加减号开头，是的话补0
		return str;
	}
	
	//处理表达式
	public String dealWtihExpection(String str) {
		str = regularyException(str);//规范化表达式
		str = DealAfterDeleteAllBlankSpace(str);// 处理删除空格后的表达式
		str = stack.toSuffixExpression(str);//将原表达式变为后缀表达式
		str = stack.calculateSuffixExpression(str);//计算后缀表达式
		return str;
	}
	
}
