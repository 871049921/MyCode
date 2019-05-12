
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.Toolkit;

public class Dispatcher {
	protected RequestQueue requests;// 请求队列
	protected String[] outputArr = new String[10000];
	protected static int indexOfOutputArr = 0;
	protected Elevator elevator;// 电梯类
	protected Floor floor;// 楼层类
	protected static float currentTime;// 目前时间
	protected String lastRequest;
	protected ArrayList<Passenger> numberInElevator;// 电梯内的人数
	protected ArrayList<String> waitsTime;// 等待时间输出
	protected int numberInFR;
	protected float allWaitsTime;

	public Dispatcher() {
		elevator = new Elevator();
		floor = new Floor();
		currentTime = 0.0f;
		lastRequest = "";
		numberInElevator = new ArrayList<Passenger>();
		waitsTime = new ArrayList<String>();
		numberInFR = 0;
		allWaitsTime = 0.0f;
	}

	public Dispatcher(RequestQueue requests) {
		this.requests = requests;
		elevator = new Elevator();
		floor = new Floor();
		currentTime = 0.0f;
		lastRequest = "";
		numberInElevator = new ArrayList<Passenger>();
		waitsTime = new ArrayList<String>();
		numberInFR = 0;
		allWaitsTime = 0.0f;
	}
	
	// 获得输出时间
	public ArrayList<String> getWaitsTime() {
		return this.waitsTime;
	}
	
	// 获得FR人数
	public int getFRNumber() {
		return this.numberInFR;
	}
	
	// 获得所有等待时间
	public float getAllWaitsTime() {
		return this.allWaitsTime;
	}
	
	// 处理所有的请求
	public void dealWithRequests() {
		while (!requests.isEmpty()) {
			String str = requests.deQueue();// 请求出队
			String origin = str;
			str = deleteAllBlank(str);
			System.out.println("origin: " + origin + "lastRequest: " + lastRequest);
			if (isSameFloorUPDOWN(origin, lastRequest)) {
				String last = outputArr[indexOfOutputArr - 1];
				int index = last.indexOf("/");
				last = last.substring(index + 1);
				String a = regularRequest(last);
				String symbols[] = a.split(",");
				float time = Float.parseFloat(symbols[2]) + 1;
				last = "(" + symbols[0] + "," + "STILL" + "," + time + ")";
				outputArr[indexOfOutputArr++] = "[" + str + "]/" + last;
				currentTime ++;
				lastRequest = origin;
				numberInElevatorOpration(origin, time);// 电梯内人数的增减以及BEEP
				continue;
			}
			else if (str.contains("nwtc1")) {
				String last = outputArr[indexOfOutputArr - 1];
				int index = last.indexOf("/");
				last = last.substring(index + 1);
				str = str.replace("nwtc1", "");
				outputArr[indexOfOutputArr++] = "[(" + str + ")]/" + last;
				lastRequest = origin;
				last = regularRequest(last);
				String[] symbols = last.split(",");
				float time = Float.parseFloat(symbols[symbols.length - 1]);
				numberInElevatorOpration(str, time);// 电梯内人数的增减以及BEEP
				continue;
			}
			else if (isValid(str, lastRequest)) {
				System.out.println("str:" + str);
				lastRequest = origin;// 储存上一个请求
				str = regularRequest(str);// 规范化请求
				String[] symbols = str.split(",");
				switch (symbols[0]) {
				case "FR":// 楼层
					int floor = Integer.parseInt(symbols[1]);
					String direction = symbols[2];
					int StartTimeFR = Integer.parseInt(symbols[3]);
					floorHandleMethod(floor, direction, StartTimeFR, origin);
					elevator.setElevatorFloor(Integer.parseInt(symbols[1]));
					break;
				case "ER":// 电梯
					int toFloor = Integer.parseInt(symbols[1]);
					int StartTimeER = Integer.parseInt(symbols[2]);
					elevatorHandleMethod(toFloor, StartTimeER, origin);
					elevator.setElevatorFloor(Integer.parseInt(symbols[1]));
					break;
				case "RUN":
					return;// 直接退出
				default:// 错误
					System.out.println("MISTAKE!!!");
					System.exit(-1);
				}
				
			}
			System.out.println(currentTime + "currentTime");
		}
	}

	// 删除所有空白
	protected String deleteAllBlank(String expression) {
		expression = expression.replaceAll(" ", "");
		expression = expression.replaceAll("\t", "");
		return expression;
	}

	// 删除左右括号
	protected String deleteBrackets(String expression) {
		expression = expression.replace("(", "");
		expression = expression.replace(")", "");
		return expression;
	}

	// 楼层请求处理方法
	protected void floorHandleMethod(int floor, String direction, int StartTime, String origin) {
		// if (StartTime > currentTime) {
		// currentTime = StartTime;
		// }
		String direction1 = "";
		int currentFloor = elevator.getElevatorFloor();

		if (currentFloor - floor < 0) {// 向上运行
			direction1 = "UP";
		} else if (currentFloor - floor == 0) {// 不动
			direction1 = "STILL";
			currentTime += 1.0f;// 开关门时间
		} else {// 向下运行
			direction1 = "DOWN";
		}
		currentTime += Math.abs(currentFloor - floor) * 0.5;// 电梯运行时间
		String index = "[" + origin + "]/(" + floor + "," + direction1 + "," + currentTime + ")";
		numberInElevatorOpration(origin, currentTime);// 电梯内人数的增减以及BEEP
		if (!direction1.equals("STILL")) {// 如果电梯不是不动
			currentTime += 1.0f;// 开关门时间
		}
		elevator.setElevatorFloor(floor);
		outputArr[indexOfOutputArr++] = index;
		System.out.println(index);// 测试
	}

	// 电梯请求处理方法
	protected void elevatorHandleMethod(int toFloor, int StartTime, String origin) {
		// if (StartTime > currentTime) {
		// currentTime = StartTime;
		// }
		String direction = "";
		int currentFloor = elevator.getElevatorFloor();

		if (currentFloor - toFloor < 0) {// 向上运行
			direction = "UP";
		} else if (currentFloor - toFloor == 0) {// 不动
			direction = "STILL";
			currentTime += 1.0f;// 开关门时间
		} else {// 向下运行
			direction = "DOWN";
		}
		currentTime += Math.abs(currentFloor - toFloor) * 0.5;// 电梯运行时间
		System.out.println("origin:asdasd:" + origin);
		String index = "[" + origin + "]/(" + toFloor + "," + direction + "," + currentTime + ")";
		numberInElevatorOpration(origin, currentTime);// 电梯内人数的增减以及BEEP
		if (!direction.equals("STILL")) {// 如果电梯不是不动
			currentTime += 1.0f;// 开关门时间
		}
		elevator.setElevatorFloor(toFloor);
		outputArr[indexOfOutputArr++] = index;
		System.out.println(index);// 测试
	}

	// 请求错误的输出结果
	public void outputOfWrong(String outputExpression, String wrongInformation) {
		outputArr[indexOfOutputArr++] = outputExpression + "\r\nERROR\r\n#" + wrongInformation;
	}

	// 获取所有输出结果的数组
	public String[] getOutputArr() {
		return outputArr;
	}

	// 请求是否合法
	protected boolean isValid(String expression, String lastExpression) {
		if (expression.equals("RUN")) {
			return true;
		}
		if (!isLegalFormat(expression)) {
			outputArr[indexOfOutputArr++] = expression + "\r\nERROR\r\n#格式错误";
			System.out.println(expression + "\r\nERROR\r\n#格式错误");// 测试
			return false;
		}
		if (!isTimeSequenceLegal(expression, lastExpression)) {
			outputArr[indexOfOutputArr++] = expression + "\r\nERROR\r\n#时间顺序不合法";
			System.out.println(expression + "\r\nERROR\r\n#时间顺序不合法");// 测试
			return false;
		}
		if (!firstRequestTimeIsZero(expression)) {// 第一个请求的时间不为0
			outputArr[indexOfOutputArr++] = expression + "\r\nERROR\r\n#crash";
			System.out.println(expression + "\r\nERROR\r\n#crash");// 测试
			try {
				File_IO.crashError();
			} catch (Exception e) {
				System.exit(0);
			}
			System.exit(0);
			return false;
		}
		if (isrepeated(expression, lastExpression)) {// 判断请求是否重复
			outputArr[indexOfOutputArr++] = expression + "\r\nERROR\r\n#重复请求";
			System.out.println(expression + "\r\nERROR\r\n#重复请求");// 测试
			return false;
		}
		String errors[] = { "[^1-9|10|UP|DOWN|FR|ER|\\(|\\)|\\,]", "^\\((FR)\\,1\\,(DOWN)\\,\\d{1,}\\)",
				"^\\((FR)\\,(10)\\,(UP)\\,\\d{1,}\\)", "\\,\\,", "^\\s*$" };
		String errorsName[] = { "存在非法字符", "1楼DOWN", "10楼UP", "非法请求！", "空表达式" };

		expression = deleteAllBlank(expression);// 删除所有空白
		for (int i = 0; i < errors.length; i++) {
			Pattern p = Pattern.compile(errors[i]);
			Matcher m = p.matcher(expression);
			if (m.find()) {
				outputArr[indexOfOutputArr++] = expression + "\r\nERROR\r\n#" + errorsName[i];
				System.out.println(expression + "\r\nERROR\r\n#" + errorsName[i]);// 测试
				return false;
			}
		}

		return true;
	}

	// 请求是否重复
	protected boolean isrepeated(String expression, String lastExpression) {
		if (lastExpression.equals("")) {// 第一个请求
			return false;
		}
		if (expression.equals(lastExpression)) {
			return true;
		}
		expression = regularRequest(expression);
		lastExpression = regularRequest(lastExpression);
		String[] ElementsOfExpression = expression.split(",");
		String[] ElementsOfLastExpression = lastExpression.split(",");
		if (isFloorRepeated(ElementsOfExpression, ElementsOfLastExpression)) {// 第一个操作数相同
			if (isFloorRepeated(ElementsOfExpression, ElementsOfLastExpression)) {// 楼层类判断
				return true;
			} else if (isElevatorRepeated(ElementsOfExpression, ElementsOfLastExpression)) {// 电梯类判断
				return true;
			}
		}
		return false;
	}

	// 楼层类是否重复判断
	protected boolean isFloorRepeated(String[] ElementsOfExpression, String[] ElementsOfLastExpression) {
		if (ElementsOfExpression[1].equals(ElementsOfLastExpression[1])
				&& ElementsOfExpression[2].equals(ElementsOfLastExpression[2])) {
			int Time = Integer.parseInt(ElementsOfExpression[3]);
			int lastTime = Integer.parseInt(ElementsOfLastExpression[3]);
			// 两层楼的差乘0.5加1>=两条请求之间的时间差>=两层楼的差乘0.5
			if ((Math.abs(Integer.parseInt(ElementsOfExpression[1]) - elevator.getElevatorFloor()) * 0.5 <= Time
					- lastTime)
					&& (Time - lastTime <= Math
							.abs(Integer.parseInt(ElementsOfExpression[1]) - elevator.getElevatorFloor()) * 0.5 + 1)) {
				return true;
			}
		}
		return false;
	}

	// 电梯类是否重复判断
	protected boolean isElevatorRepeated(String[] ElementsOfExpression, String[] ElementsOfLastExpression) {
		if (ElementsOfExpression[1].equals(ElementsOfLastExpression[1])) {
			int Time = Integer.parseInt(ElementsOfExpression[2]);
			int lastTime = Integer.parseInt(ElementsOfLastExpression[2]);
			// 两层楼的差乘0.5加1>=两条请求之间的时间差>=两层楼的差乘0.5
			if ((Math.abs(Integer.parseInt(ElementsOfExpression[1]) - elevator.getElevatorFloor()) * 0.5 <= Time
					- lastTime)
					&& (Time - lastTime <= Math
							.abs(Integer.parseInt(ElementsOfExpression[1]) - elevator.getElevatorFloor()) * 0.5 + 1)) {
				return true;
			}
		}
		return false;
	}

	// 第一条请求是否时间-是否为0
	protected boolean firstRequestTimeIsZero(String expression) {
		expression = regularRequest(expression);
		String[] elements = expression.split(",");
		if (lastRequest.equals("")) {
			if ((elements[2].equals("0") || elements[3].equals("0"))) {
				return true;
			} else {
				return false;
			}
		}
		return true;
	}

	// 两条请求的时间顺序是否合法
	protected boolean isTimeSequenceLegal(String expression, String lastExpression) {
		if (lastExpression.equals("")) {
			return true;
		}
		expression = regularRequest(expression);
		lastExpression = regularRequest(lastExpression);
		String[] ElementsOfExpression = expression.split(",");
		String[] ElementsOfLastExpression = lastExpression.split(",");
		if (isFR_FRLegal(ElementsOfExpression, ElementsOfLastExpression)) {
			return true;
		} else if (isFR_ERLegal(ElementsOfExpression, ElementsOfLastExpression)) {
			return true;
		} else if (isER_FRLegal(ElementsOfExpression, ElementsOfLastExpression)) {
			return true;
		} else if (isER_ERLegal(ElementsOfExpression, ElementsOfLastExpression)) {
			return true;
		}
		return false;
	}

	// 规范化请求
	protected String regularRequest(String request) {
		request = deleteAllBlank(request);// 删除请求的所有空白
		request = deleteBrackets(request);// 删除左右括号
		return request;
	}

	// 正确请求格式
	protected boolean isLegalFormat(String expression) {
		if (expression.matches("^\\(FR\\,([1-9]|10)\\,(UP|DOWN)\\,\\d+\\)$")
				|| (expression.matches("^\\(ER\\,([1-9]|10)\\,\\d+\\)"))) {
			return true;
		} else {
			return false;
		}
	}

	// FR—FR型是否符合
	protected boolean isFR_FRLegal(String[] str1, String[] str2) {
		if (str1[0].equals("FR") && str2[0].equals("FR")) {
			if (Integer.parseInt(str1[3]) >= Integer.parseInt(str2[3])) {
				return true;
			}
		}
		return false;
	}

	// FR—ER型是否符合
	protected boolean isFR_ERLegal(String[] str1, String[] str2) {
		if (str1[0].equals("FR") && str2[0].equals("ER")) {
			if (Integer.parseInt(str1[3]) >= Integer.parseInt(str2[2])) {
				return true;
			}
		}
		return false;
	}

	// ER—FR型是否符合
	protected boolean isER_FRLegal(String[] str1, String[] str2) {
		if (str1[0].equals("ER") && str2[0].equals("FR")) {
			if (Integer.parseInt(str1[2]) >= Integer.parseInt(str2[3])) {
				return true;
			}
		}
		return false;
	}

	// ER—ER型是否符合
	protected boolean isER_ERLegal(String[] str1, String[] str2) {
		if (str1[0].equals("ER") && str2[0].equals("ER")) {
			if (Integer.parseInt(str1[2]) >= Integer.parseInt(str2[2])) {
				return true;
			}
		}
		return false;
	}
	
	//判断两个相同楼层是否为同上下
	public boolean isSameFloorUPDOWN(String request, String lastRequest) {
		request = regularRequest(request);
		request = request.replace("nwtc1", "");
		lastRequest = regularRequest(lastRequest);
		lastRequest = lastRequest.replace("nwtc1", "");
		String symbol1[] = request.split(",");
		String symbol2[] = lastRequest.split(",");
		System.out.println("requestasd: " + request + "lastRequest: " + lastRequest);
		if (!symbol1[0].equals("FR") || !symbol2[0].equals("FR")) {
			System.out.println("return false1");
			return false;
		}
		if (symbol1[0].equals(symbol2[0]) && symbol1[1].equals(symbol2[1]) && symbol1[3].equals(symbol2[3])) {
			if (symbol1[2].equals("UP") && symbol2[2].equals("DOWN")) {
				System.out.println("return true1");
				return true;
			} else if (symbol1[2].equals("DOWN") && symbol2[2].equals("UP")) {
				System.out.println("return true2");
				return true;
			}
		}
		System.out.println("return false2");
		return false;
	}
	
	// 电梯内人数的增减以及BEEP
	public void numberInElevatorOpration(String experssion, float currentTime) {
		experssion = regularRequest(experssion);
		String[] symbols = experssion.split(",");
		int random = 1;
		if (experssion.contains("FR")) {
			for (int i = 0; i < random; i ++) {
				if (numberInElevator.size() > 13) {
					Toolkit.getDefaultToolkit().beep();// 报警声
					break;
				} else {
					Passenger passenger = new Passenger();
					numberInFR ++;
					allWaitsTime = allWaitsTime + (currentTime - Float.parseFloat(symbols[symbols.length - 1]));
					numberInElevator.add(passenger);
				}
			}
		} else if (experssion.contains("ER")) {
			for (int i = random; i > 0; i --) {
				if (numberInElevator.size() != 0) {
					numberInElevator.get(0).setLeaveTime(currentTime);
					waitsTime.add(numberInElevator.get(0).toString());// 加入输出文件
					numberInElevator.remove(0);// 排在队列第一个的乘客出电梯
				} else if (numberInElevator.size() == 0) {
					waitsTime.add("乘客的离开电梯的时间是" + currentTime);
					break;
				}
			}
		}
	}
}
