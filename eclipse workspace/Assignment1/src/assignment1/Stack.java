package assignment1;

public class Stack {
	public StackNode top;//栈顶
	
	//初始化栈
	public Stack() {
		top = new StackNode();
	}
	
	//@TODO 将原表达式变为后缀表达式
	public String toSuffixExpression(String str) {
		String str1 = "";
		String splitedStr[] = str.split(" ");//按空格分割
		String suffixExpression = "";//后缀表达式(结果)
		for (int i = 0; i < splitedStr.length; i++) {
			str1 = splitedStr[i];
			switch(str1){
				case "+":
				case "-":
					suffixExpression = symbolAddSub(suffixExpression, str1);
					break;
				case "*":
				case "/":
					suffixExpression = symbolMulDiv(suffixExpression, str1);
					break;
				case "(":
					push(str1);//入栈
					break;
				case ")":
					suffixExpression = symbolRightBracket(suffixExpression, str1);
					break;
				default:
					suffixExpression = suffixExpression + " " + str1;//直接输出
					break;
			}
		}
		while(!isEmpty()) {//输出所有元素
			suffixExpression = suffixExpression + " " + pop();
		}
		suffixExpression = suffixExpression.trim();//去掉头部空格
		return suffixExpression;
	}
	
	//计算后缀表达式
	public String calculateSuffixExpression(String str) {
		String str1 = "";
		String splitedStr[] = str.split(" ");//按空格分割
		String result = "";
		
		for (int i = 0; i < splitedStr.length; i++) {
			str1 = splitedStr[i];
			if (isNumber(str1)) {
				push(str1);
			}
			else{
				String k = calculate(pop(), pop(), str1);//出栈并计算值
				if (k == "ERROR") {//除数为零
					return "ERROR";
				}
				result = k;
				push(k);
			}
		}
		while(!isEmpty()) {//输出所有元素
			pop();
		}
		return result;
	}
	
	//元素入栈
	public void push(String str) {
		StackNode newNode = new StackNode(str);//创建一个新节点
		newNode.nextNode = top;
		top = newNode;
	}
	
	//元素出栈
	public String pop() {//删除一个节点并输出里面的数据
		String recv = top.symbol;
		top = top.nextNode;
		return recv;
	}
	
	//判断栈是否为空
	public boolean isEmpty() {
		if (top.nextNode == null) {
			return true;
		}
		else {
			return false;
		}
	}
	
	//判断一个字符串是否为数字
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
	
	//四则运算
	public String calculate(String number1, String number2, String symbol) {
		double result = 0.0;
		double number1D = Double.parseDouble(number1);
		double number2D = Double.parseDouble(number2);
		
		
		switch(symbol) {
		case "+":
			result = number2D + number1D;
			break;
		case "-":
			result = number2D - number1D;
			break;
		case "*":
			result = number2D * number1D;
			break;
		case "/":
			if (number1D == 0.0) {
				return "ERROR";
			}
			result = number2D / number1D;
			break;
		}
		
		return String.valueOf(result);
	}
	
	//符号是加减号的处理方法
	public String symbolAddSub(String suffixExpression, String str1) {
		while (!top.symbol.equals("(") && top.nextNode != null) {//栈顶元素不为左括号
			suffixExpression = suffixExpression + " " + pop();//栈顶元素出栈
		}
		push(str1);//当前元素入栈
		return suffixExpression;
	}
	
	//符号是乘除号的处理方法
	public String symbolMulDiv(String suffixExpression, String str1) {
		while (!top.symbol.equals("(")) {//栈顶元素不为左括号
			if (top.symbol.equals("*") || top.symbol.equals("/")) {//该符号的优先级不高于栈顶元素的优先级
				suffixExpression = suffixExpression + " " + pop();//栈顶元素出栈
			}
			else {	
				break;
			}
		}
		push(str1);//当前元素入栈
		return suffixExpression;
	}
	
	//符号是右括号的处理方法
	public String symbolRightBracket(String suffixExpression, String str1) {
		while (!top.symbol.equals("(")) {//到左括号前一直出栈
			suffixExpression = suffixExpression + " " + pop();
		}
		pop();//删除左括号
		return suffixExpression;
	}
	
}
