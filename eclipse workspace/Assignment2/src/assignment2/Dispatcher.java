package assignment2;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Dispatcher {
	private RequestQueue requests;// �������
	private String[] outputArr = new String[10000];
	private static int indexOfOutputArr = 0;
	private Elevator elevator;// ������
	private Floor floor;// ¥����
	private static float currentTime;// Ŀǰʱ��
	private String lastRequest;

	public Dispatcher() {
		elevator = new Elevator();
		floor = new Floor();
		currentTime = 0.0f;
		lastRequest = "";
	}

	public Dispatcher(RequestQueue requests) {
		this.requests = requests;
		elevator = new Elevator();
		floor = new Floor();
		currentTime = 0.0f;
		lastRequest = "";
	}

	// �������е�����
	public void dealWithRequests() {
		while (!requests.isEmpty()) {
			String str = requests.deQueue();// �������
			String origin = str;
			str = deleteAllBlank(str);
			if (isValid(str, lastRequest)) {
				lastRequest = origin;// ������һ������
				str = regularRequest(str);// �淶������
				String[] symbols = str.split(",");
				switch (symbols[0]) {
				case "FR":// ¥��
					int floor = Integer.parseInt(symbols[1]);
					String direction = symbols[2];
					int StartTimeFR = Integer.parseInt(symbols[3]);
					floorHandleMethod(floor, direction, StartTimeFR);
					break;
				case "ER":// ����
					int toFloor = Integer.parseInt(symbols[1]);
					int StartTimeER = Integer.parseInt(symbols[2]);
					elevatorHandleMethod(toFloor, StartTimeER);
					break;
				case "RUN":
					return;// ֱ���˳�
				default:// ����
					System.out.println("MISTAKE!!!");
					System.exit(-1);
				}

			}
		}
	}

	// ɾ�����пհ�
	private String deleteAllBlank(String expression) {
		expression = expression.replaceAll(" ", "");
		expression = expression.replaceAll("\t", "");
		return expression;
	}

	// ɾ����������
	private String deleteBrackets(String expression) {
		expression = expression.replace("(", "");
		expression = expression.replace(")", "");
		return expression;
	}

	//¥����������
	private void floorHandleMethod(int floor, String direction, int StartTime) {
		if (StartTime > currentTime) {
			currentTime = StartTime;
		}
		String direction1 = "";
		int currentFloor = elevator.getElevatorFloor();

		if (currentFloor - floor < 0) {// ��������
			direction1 = "UP";
		} else if (currentFloor - floor == 0) {// ����
			direction1 = "STILL";
			currentTime += 1.0f;// ������ʱ��
		} else {// ��������
			direction1 = "DOWN";
		}
		currentTime += Math.abs(currentFloor - floor) * 0.5;// ��������ʱ��
		String index = "(" + floor + "," + direction1 + "," + currentTime + ")";
		if (!direction1.equals("STILL")) {// ������ݲ��ǲ���
			currentTime += 1.0f;// ������ʱ��
		}
		elevator.setElevatorFloor(floor);
		outputArr[indexOfOutputArr++] = index;
		System.out.println(index);// ����
	}

	// ������������
	private void elevatorHandleMethod(int toFloor, int StartTime) {
		if (StartTime > currentTime) {
			currentTime = StartTime;
		}
		String direction = "";
		int currentFloor = elevator.getElevatorFloor();

		if (currentFloor - toFloor < 0) {// ��������
			direction = "UP";
		} else if (currentFloor - toFloor == 0) {// ����
			direction = "STILL";
			currentTime += 1.0f;// ������ʱ��
		} else {// ��������
			direction = "DOWN";
		}
		currentTime += Math.abs(currentFloor - toFloor) * 0.5;// ��������ʱ��
		String index = "(" + toFloor + "," + direction + "," + currentTime + ")";
		if (!direction.equals("STILL")) {// ������ݲ��ǲ���
			currentTime += 1.0f;// ������ʱ��
		}
		elevator.setElevatorFloor(toFloor);
		outputArr[indexOfOutputArr++] = index;
		System.out.println(index);// ����
	}

	// ������ȷ��������
	public void outputOfRight(String outputExpression) {
		outputArr[indexOfOutputArr++] = outputExpression;
	}

	// ��������������
	public void outputOfWrong(String outputExpression, String wrongInformation) {
		outputArr[indexOfOutputArr++] = outputExpression + "\r\nERROR\r\n#" + wrongInformation;
	}

	// ��ȡ����������������
	public String[] getOutputArr() {
		return outputArr;
	}

	// �����Ƿ�Ϸ�
	private boolean isValid(String expression, String lastExpression) {
		if (expression.equals("RUN")) {
			return true;
		}
		if (!isLegalFormat(expression)) {
			outputArr[indexOfOutputArr++] = expression + "\r\nERROR\r\n#��ʽ����";
			System.out.println(expression + "\r\nERROR\r\n#��ʽ����");// ����
			return false;
		}
		if (!isTimeSequenceLegal(expression, lastExpression)) {
			outputArr[indexOfOutputArr++] = expression + "\r\nERROR\r\n#ʱ��˳�򲻺Ϸ�";
			System.out.println(expression + "\r\nERROR\r\n#ʱ��˳�򲻺Ϸ�");// ����
			return false;
		}
		if (!firstRequestTimeIsZero(expression)) {// ��һ�������ʱ�䲻Ϊ0
			outputArr[indexOfOutputArr++] = expression + "\r\nERROR\r\n#crash";
			System.out.println(expression + "\r\nERROR\r\n#crash");// ����
			try {
				File_IO.crashError();
			} catch (Exception e) {
				System.exit(0);
			}
			System.exit(0);
			return false;
		}
		if (isrepeated(expression, lastExpression)) {// �ж������Ƿ��ظ�
			outputArr[indexOfOutputArr++] = expression + "\r\nERROR\r\n#�ظ�����";
			System.out.println(expression + "\r\nERROR\r\n#�ظ�����");// ����
			return false;
		}
		String errors[] = { "[^1-9|10|UP|DOWN|FR|ER|\\(|\\)|\\,]", "^\\((FR)\\,1\\,(DOWN)\\,\\d{1,}\\)",
				"^\\((FR)\\,(10)\\,(UP)\\,\\d{1,}\\)", "\\,\\,", "^\\s*$" };
		String errorsName[] = { "���ڷǷ��ַ�", "1¥DOWN", "10¥UP", "�Ƿ�����", "�ձ��ʽ" };

		expression = deleteAllBlank(expression);// ɾ�����пհ�
		for (int i = 0; i < errors.length; i++) {
			Pattern p = Pattern.compile(errors[i]);
			Matcher m = p.matcher(expression);
			if (m.find()) {
				outputArr[indexOfOutputArr++] = expression + "\r\nERROR\r\n#" + errorsName[i];
				System.out.println(expression + "\r\nERROR\r\n#" + errorsName[i]);// ����
				return false;
			}
		}

		return true;
	}

	// �����Ƿ��ظ�
	private boolean isrepeated(String expression, String lastExpression) {
		if (lastExpression.equals("")) {// ��һ������
			return false;
		}
		if (expression.equals(lastExpression)) {
			return true;
		}
		expression = regularRequest(expression);
		lastExpression = regularRequest(lastExpression);
		String[] ElementsOfExpression = expression.split(",");
		String[] ElementsOfLastExpression = lastExpression.split(",");
		if (isFloorRepeated(ElementsOfExpression, ElementsOfLastExpression)) {// ��һ����������ͬ
			if (isFloorRepeated(ElementsOfExpression, ElementsOfLastExpression)) {// ¥�����ж�
				return true;
			} else if (isElevatorRepeated(ElementsOfExpression, ElementsOfLastExpression)) {// �������ж�
				return true;
			}
		}
		return false;
	}
	
	//¥�����Ƿ��ظ��ж�
	private boolean isFloorRepeated(String[] ElementsOfExpression, String[] ElementsOfLastExpression){
		if (ElementsOfExpression[1].equals(ElementsOfLastExpression[1])
				&& ElementsOfExpression[2].equals(ElementsOfLastExpression[2])) {
			int Time = Integer.parseInt(ElementsOfExpression[3]);
			int lastTime = Integer.parseInt(ElementsOfLastExpression[3]);
			// ����¥�Ĳ��0.5��1>=��������֮���ʱ���>=����¥�Ĳ��0.5
			if ((Math.abs(Integer.parseInt(ElementsOfExpression[1]) - elevator.getElevatorFloor()) * 0.5 <= Time
					- lastTime)
					&& (Time - lastTime <= Math
							.abs(Integer.parseInt(ElementsOfExpression[1]) - elevator.getElevatorFloor()) * 0.5
							+ 1)) {
				return true;
			}
		}
		return false;
	}
	
	//�������Ƿ��ظ��ж�
	private boolean isElevatorRepeated(String[] ElementsOfExpression, String[] ElementsOfLastExpression) {
		if (ElementsOfExpression[1].equals(ElementsOfLastExpression[1])) {
			int Time = Integer.parseInt(ElementsOfExpression[2]);
			int lastTime = Integer.parseInt(ElementsOfLastExpression[2]);
			// ����¥�Ĳ��0.5��1>=��������֮���ʱ���>=����¥�Ĳ��0.5
			if ((Math.abs(Integer.parseInt(ElementsOfExpression[1]) - elevator.getElevatorFloor()) * 0.5 <= Time
					- lastTime)
					&& (Time - lastTime <= Math
							.abs(Integer.parseInt(ElementsOfExpression[1]) - elevator.getElevatorFloor()) * 0.5
							+ 1)) {
				return true;
			}
		}
		return false;
	}

	// ��һ�������Ƿ�ʱ��-�Ƿ�Ϊ0
	private boolean firstRequestTimeIsZero(String expression) {
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

	// ���������ʱ��˳���Ƿ�Ϸ�
	private boolean isTimeSequenceLegal(String expression, String lastExpression) {
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

	// �淶������
	private String regularRequest(String request) {
		request = deleteAllBlank(request);// ɾ����������пհ�
		request = deleteBrackets(request);// ɾ����������
		return request;
	}

	// ��ȷ�����ʽ
	private boolean isLegalFormat(String expression) {
		if (expression.matches("^\\(FR\\,([1-9]|10)\\,(UP|DOWN)\\,\\d+\\)$")
				|| (expression.matches("^\\(ER\\,([1-9]|10)\\,\\d+\\)"))) {
			return true;
		} else {
			return false;
		}
	}
	
	//FR��FR���Ƿ����
	private boolean isFR_FRLegal(String[] str1, String[] str2){
		if (str1[0].equals("FR") && str2[0].equals("FR")) {
			if (Integer.parseInt(str1[3]) >= Integer.parseInt(str2[3])) {
				return true;
			}
		}
		return false;
	}
	
	//FR��ER���Ƿ����
	private boolean isFR_ERLegal(String[] str1, String[] str2){
		if (str1[0].equals("FR") && str2[0].equals("ER")) {
			if (Integer.parseInt(str1[3]) >= Integer.parseInt(str2[2])) {
				return true;
			}
		}
		return false;
	}
	
	//ER��FR���Ƿ����
	private boolean isER_FRLegal(String[] str1, String[] str2){
		if (str1[0].equals("ER") && str2[0].equals("FR")) {
			if (Integer.parseInt(str1[2]) >= Integer.parseInt(str2[3])) {
				return true;
			}
		}
		return false;
	}
	
	//ER��ER���Ƿ����
	private boolean isER_ERLegal(String[] str1, String[] str2){
		if (str1[0].equals("ER") && str2[0].equals("ER")) {
			if (Integer.parseInt(str1[2]) >= Integer.parseInt(str2[2])) {
				return true;
			}
		}
		return false;
	}
}
