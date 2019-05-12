import java.util.*;

public class NewDispatcher extends Dispatcher {
	private LinkList linkList;

	public NewDispatcher() {
		super();
	}

	public NewDispatcher(LinkList linkList) {
		requests = new RequestQueue();
		this.linkList = linkList;
		elevator = new Elevator();
		floor = new Floor();
		currentTime = 0.0f;
		lastRequest = "";
	}

	@Override // 重写处理信息
	public void dealWithRequests(){
		while (!linkList.isEmpty()) {
			int flag = 0;
			String request = linkList.outList();
			request = deleteAllBlank(request);
			requests.enQueue(request);
			if (request.equals("RUN")) {
				return;
			}
			setCurrentTime();// 设置currentTime
			if (request.equals("(FR,1,UP,0)") && flag == 0) {
				flag = 1;
				super.dealWithRequests();// 处理队列中的请求
				elevator.setElevatorFloor(1);
			}
			if (flag != 1) {
				request = regularRequest(request);// 规范化请求
				setElevatorDirection(request);// 设置电梯运行方向
				outListAndEnQueue(request);
				System.out.println("开始了！！！！");
				super.dealWithRequests();// 处理队列中的请求
				System.out.println(lastRequest + "lastQueueLastRequest");
				System.out.println("结束了！！！！");
			}
		}
	}

	@Override
	// 请求是否合法
	protected boolean isValid(String expression, String lastExpression) {
		return true;
	}

	@Override
	public boolean isrepeated(String expression, String lastExpression) {
		return true;
	}

	// 设置电梯运动方向
	public void setElevatorDirection(String expression) {
		elevator.setLastDirection(elevator.getDirection());
		System.out.println("setLastDirection" + elevator.getLastDirection());
		String[] symbols = expression.split(",");
		if (symbols[0].equals("FR")) {// 楼层
			int toFloor = Integer.parseInt(symbols[1]);
			if (elevator.getElevatorFloor() > toFloor) {
				elevator.setDirection("DOWN");
			} else if (elevator.getElevatorFloor() < toFloor) {
				elevator.setDirection("UP");
			} else if (elevator.getElevatorFloor() == toFloor) {
				elevator.setDirection("STILL");
			}
		} else {// 电梯
			int toFloor = Integer.parseInt(symbols[1]);
			if (elevator.getElevatorFloor() > toFloor) {
				elevator.setDirection("DOWN");
			} else if (elevator.getElevatorFloor() < toFloor) {
				elevator.setDirection("UP");
			} else if (elevator.getElevatorFloor() == toFloor) {
				elevator.setDirection("STILL");
			}
		}
	}

	// 把所有符合时间要求的请求出链表并放入队列
	public void outListAndEnQueue(String expression) {
		float needTime;
		String[] symbols = expression.split(",");
		switch (symbols[0]) {
		case "FR":// 楼层
			int floor = Integer.parseInt(symbols[1]);
			needTime = (float) Math.abs((elevator.getElevatorFloor() - floor) * 0.5 + 1);
			needTime += currentTime;
			enQueue(needTime, floor, elevator.getElevatorFloor(), expression);// 将所有符合时间和方向要求的请求入队
			break;
		case "ER":// 电梯
			int toFloor = Integer.parseInt(symbols[1]);
			needTime = (float) Math.abs((elevator.getElevatorFloor() - toFloor) * 0.5 + 1);
			needTime += currentTime;
			enQueue(needTime, toFloor, elevator.getElevatorFloor(), expression);// 将所有符合时间和方向要求的请求入队
			break;
		case "RUN":
			return;// 直接退出
		default:// 错误
			System.out.println("MISTAKE!!!");
			// return;
		}
	}

	// 将所有符合时间和方向要求的请求入队
	public void enQueue(float needTime, int toFloor, int elevatorFloor, String request) {
		//System.out.println("电梯现在楼层123 " + elevatorFloor);
		DealOutList deal = new DealOutList();
		//String request1 = "(" + request + ")";
		deal.setNeedTime(needTime);
		deal.setDirectionOfElevator(elevator.getDirection());
		deal.setElevatorFloor(elevatorFloor);
		deal.setCurrentTime(currentTime);
		deal.setElevator(elevator);
		deal.setRequest1(request);
		ArrayList<String> receive = linkList.outList(deal);// 获取所有适合的请求
		request = "(" + request + ")";
		receive.add(0, request);
		requests.deQueue();
		receive = sort(receive);// 冒泡排序
		
		while (!requests.isEmpty()) {
			requests.deQueue();
		}
		requests = isValid2(receive, elevator.getDirection());
		
//		for (int i = 0; i < receive.size(); i++) {
//			requests.enQueue(receive.get(i));
//		}
	}

	// 将收到的请求按层数排序 冒泡排序
	public ArrayList<String> sort(ArrayList<String> receive) {
		System.out.println(receive.size());
		for (int i = 0; i < receive.size() - 1; i++) {
			for (int j = 0; j < receive.size() - i - 1; j++) {
				receive = dealSort(receive, j);
			}
		}
		System.out.println(receive + "1");
		return receive;
	}

	private ArrayList<String> dealSort(ArrayList<String> receive, int j) {
		String[] symbols1 = receive.get(j).split(",");
		String[] symbols2 = receive.get(j + 1).split(",");
		try {
			int a = Integer.parseInt(symbols1[1]);
			int b = Integer.parseInt(symbols2[1]);
			if (elevator.getDirection().equals("UP")) {
				if (a > b) {
					String temp = receive.get(j);
					receive.set(j, receive.get(j + 1));
					receive.set(j + 1, temp);
				}
			} else if (elevator.getDirection().equals("DOWN")) {
				if (a < b) {
					String temp = receive.get(j);
					receive.set(j, receive.get(j + 1));
					receive.set(j + 1, temp);
				}
			}

		} catch (ArrayIndexOutOfBoundsException e) {

		}
		return receive;
	}

	// 要设置currentTime
	public void setCurrentTime() {
		String a = requests.getDownRequest();
		a = regularRequest(a);
		String[] symbols = a.split(",");
		int time = Integer.parseInt(symbols[symbols.length - 1]);
		if (time > currentTime) {
			currentTime = time;
		}
	}
		//isVaild2
		public RequestQueue isValid2(ArrayList<String> receive, String direction) {
			Elevator elevator1 = new Elevator();
			elevator1.setDirection(direction);
			RequestQueue requestsOutput = new RequestQueue();
			String[] symbols = receive.get(0).split(",");
			String lastExpression = "";
			int elevatorFloor = elevator.getElevatorFloor();
			elevator1.setElevatorFloor(elevatorFloor);
			for (int i = 0; i < receive.size(); i ++) {
				String expression = receive.get(i);
				String[] symbol = expression.split(",");
				if (!isrep(expression, lastExpression, elevatorFloor, requestsOutput)) {
					lastExpression = expression;
					System.out.println(expression + "???");
					requestsOutput.enQueue(expression);
					elevator1.setElevatorFloor(Integer.parseInt(symbol[1]));
					elevatorFloor = Integer.parseInt(symbol[1]);
				} else {
					//inValid.add(expression);
					//linkList.setInValid("SAME[" + expression + "]");
					System.out.println(expression + "aaa");
				}
			}
			return requestsOutput;
		}
		
		//rep
		public boolean isrep(String expression, String lastExpression, int elevatorFloor, RequestQueue requestsOutput) {
			if (lastExpression.equals("")) {
				return false;
			}
			if (expression.equals(lastExpression)) {
				return true;
			}
			int a = Integer.parseInt((expression.split(",")[1]));
			lastExpression = regularRequest(lastExpression);
			expression = regularRequest(expression);
			String[] symbol2 = lastExpression.split(",");
			int b = Integer.parseInt(symbol2[symbol2.length - 1]);
			String[] symbol3 = expression.split(",");
			int c = Integer.parseInt(symbol3[symbol3.length - 1]);
			System.out.println("a:" + a + " b:" + b + " c:" + c);
			if (isSame(expression, lastExpression, elevatorFloor)) {
				return true;
			}
			if ((Integer.parseInt(expression.split(",")[1]) == Integer.parseInt(lastExpression.split(",")[1])) && 
					!(expression.split(",")[0]).equals(lastExpression.split(",")[0])) { //同层请求
				if (symbol2[0].equals("FR")) {
					lastExpression = "ER," + symbol3[1] + "," + symbol3[symbol3.length - 1];
				} else if (symbol2[0].equals("ER")){
					lastExpression = "FR," + symbol3[1] + "," + elevator.getDirection() + "," + symbol3[symbol3.length - 1];
					System.out.println("elevator.getDirection()" + elevator.getDirection());
					System.out.println("lastExpression:" + lastExpression + "expression:" + expression);
				}
				System.out.println("-----------lastExpression:" + lastExpression + "this.lastRequest:" + this.lastRequest + "------------------");
				if (isrep1(expression, lastExpression, elevatorFloor, requestsOutput)) {
					lastExpression += "nwtc1";
					System.out.println("if nwtc1" + lastExpression);
					requestsOutput.enQueue(lastExpression);
					
					return true; //进入计算时结果为上一个请求的结果
				} 
			}
			return false;
		}
		
		//判断是否有内外同层请求
		public boolean isrep1(String expression, String lastExpression, int elevatorFloor, RequestQueue requestsOutput) {
			if (expression.equals(lastExpression)) {
				return true;
			}
			int a = Integer.parseInt((expression.split(",")[1]));
			lastExpression = regularRequest(lastExpression);
			expression = regularRequest(expression);
			String[] symbol2 = lastExpression.split(",");
			int b = Integer.parseInt(symbol2[symbol2.length - 1]);
			String[] symbol3 = expression.split(",");
			int c = Integer.parseInt(symbol3[symbol3.length - 1]);
			System.out.println("a:" + a + " b:" + b + " c:" + c);
			if ((Integer.parseInt(expression.split(",")[1]) == Integer.parseInt(lastExpression.split(",")[1])) && 
					(expression.split(",")[0]).equals(lastExpression.split(",")[0])) {
				if (Math.abs(elevatorFloor - a) * 0.5 + 1 >= c - b) {
					//linkList.setInValid("SAME1[" + lastExpression + "]");
					return true;
				}
			} else if (symbol2[0].equals("FR") && symbol3[0].equals("FR")) {}
			return false;
		}
		
		//SAME
		public boolean isSame(String expression, String lastExpression, int elevatorFloor) {
			int a = Integer.parseInt((expression.split(",")[1]));
			lastExpression = regularRequest(lastExpression);
			expression = regularRequest(expression);
			String[] symbol2 = lastExpression.split(",");
			int b = Integer.parseInt(symbol2[symbol2.length - 1]);
			String[] symbol3 = expression.split(",");
			int c = Integer.parseInt(symbol3[symbol3.length - 1]);
			System.out.println("a:" + a + " b:" + b + " c:" + c);
			if ((Integer.parseInt(expression.split(",")[1]) == Integer.parseInt(lastExpression.split(",")[1])) && 
					(expression.split(",")[0]).equals(lastExpression.split(",")[0])) {
				if (Math.abs(elevatorFloor - a) * 0.5 + 1 >= c - b) {
					linkList.setInValid("SAME[" + expression + "]");
					return true;
				}
			} 
			return false;
		}
}
