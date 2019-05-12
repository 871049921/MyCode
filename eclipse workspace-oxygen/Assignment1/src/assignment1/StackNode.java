package assignment1;

//非公共类栈节点
class StackNode {

	public String symbol;//符号
	public StackNode nextNode;
	
	//初始化节点,默认
	public StackNode() {
		this.symbol = "";
	}
	
	//初始化节点,符号
	public StackNode(String symbol) {
		this.symbol = symbol;
	}
}
