import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LinkList {
	private LinkNode head;// ͷ�ڵ�
	private LinkNode rear;// β�ڵ�
	private ArrayList<String> inValid = new ArrayList<String>();

	public LinkList() {
		head = new LinkNode(null);
		head.setNextNode(null);
		rear = head;
	}

	// ���ݽ�������(β�ӷ�����ͷ��ĵ�һ��Ԫ���ǵ�һ�������Ԫ��)
	public void inList(String date) {
		LinkNode node = new LinkNode(date);
		rear.setNextNode(node);
		node.setNextNode(null);
		rear = node;
	}
	
	public void setInValid(String inValid) {
		this.inValid.add(inValid);
	}
	
	// ͷ�ڵ��һ���ڵ����
	public String outList() {
		String date = "";
		if (!isEmpty()) {// ����Ϊ��
			if (head.getNextNode() != null) {// ��һ���ڵ㲻Ϊ��
				LinkNode node = head.getNextNode();
				date = head.getNextNode().getDate();
				// System.out.println(node.getDate() + "������");
				head.setNextNode(head.getNextNode().getNextNode());
				node.setNextNode(null);// ɾ���ڵ�
			} else {// ��һ���ڵ�Ϊ��
				LinkNode node = head.getNextNode();
				date = head.getNextNode().getDate();
				head.setNextNode(null);
				node.setNextNode(null);// ɾ���ڵ�
			}
		} else {// ����Ϊ��
			date = "NULL LIST";
		}
		return date;
	}

	// ���ݳ�����(ͬʱ�ڵ��ȥ) ����ʱ�����
	public ArrayList<String> outList(DealOutList deal) {
		int index = 0;// havaBigger
		float needTime = deal.getNeedTime();
		String directionOfElevator = deal.getDirectionOfElevator();
		int elevatorFloor = deal.getElevatorFloor();
		float currentTime = deal.getCurrentTime();
		Elevator elevator = deal.getElevator();
		String request1 = deal.getRequest1();
		String symbols1[] = request1.split(",");
		ArrayList<String> output = new ArrayList<String>();
		LinkNode node = head.getNextNode();
		LinkNode nodeBefore = head;
		int currentFloor = elevatorFloor;
		String lastExpression = "(" + request1 + ")";
		int time = Integer.parseInt(symbols1[1]);
		if (node.getDate().equals("RUN")) {
			return output;
		}
		while (node != null) {// �ڵ㲻Ϊ��
			String origin = node.getDate();
			if (origin.equals("RUN")) {
				return output;
			}
			String request = node.getDate().replace(")", "");
			request = request.replace("(", "");
			String symbols[] = request.split(",");
			System.out.println(origin + "origin");
			System.out.println(lastExpression + "lastExpression");
			System.out.println("��������¥��" + elevator.getElevatorFloor());
			if (!isValid(origin, lastExpression, elevator)) { // ���������
				nodeBefore.setNextNode(node.getNextNode());
				node.setNextNode(null);// ɾ���ڵ�
				node = nodeBefore.getNextNode();
				continue;
			}
			if (havaBigger(origin, output, directionOfElevator, index)) {
				needTime -= 1;
				System.out.println("currentTime::::" + currentTime);
			}
			if (node.getTime() <= needTime && isInSameDirection(node.getDate(), directionOfElevator, elevatorFloor)
					&& isTime(Integer.parseInt(symbols[symbols.length - 1]), currentTime)) {// �ҵ��ڵ�
				int a = currentFloor;
				currentFloor = Integer.parseInt(symbols[1]);
				float b = (float) (Math.abs(currentFloor - a) * 0.5 + 1);
				currentTime += b;
				if (currentFloor - a == 0) {
					currentTime -= 1;
				}
				output.add(node.getDate());
				nodeBefore.setNextNode(node.getNextNode());
				node.setNextNode(null);// ɾ���ڵ�
				node = nodeBefore.getNextNode();
				lastExpression = origin;
			} else {// δ�ҵ��ڵ�
				nodeBefore = node;
				node = node.getNextNode();
				lastExpression = origin;
			}
			index = output.size();
		}
		return output;
	}

	public ArrayList<String> getInValid() {
		return inValid;
	}

	// �жϱ��Ƿ�Ϊ��
	public boolean isEmpty() {
		if (head.getNextNode() == null) {
			return true;
		} else {
			return false;
		}
	}

	// ���з����Ƿ��밴��������ͬ
	public boolean isInSameDirection(String expression, String directionOfElevator, int elevatorFloor) {
		// System.out.println(directionOfElevator);
		if (expression.equals("RUN")) {
			return false;
		}
		String[] symbols = expression.split(",");
		if (symbols[0].equals("(FR")) {

			if (!symbols[2].equals(directionOfElevator)) {
				return false;
			}
		}
		int enterFloor = Integer.parseInt(symbols[1]);
		if (directionOfElevator.equals("UP")) {
			if (enterFloor > elevatorFloor) {
				return true;
			}
		} else if (directionOfElevator.equals("DOWN")) {
			if (enterFloor < elevatorFloor) {
				return true;
			}
		} else if (directionOfElevator.equals("STILL")) {
			return true;
		}
		return false;
	}

	// �ж�ʱ�䵽ʱ�����Ƿ񵽴��¥��
	public boolean isTime(int time, float currentTime) {
		if (currentTime > time && currentTime < time + 1 ) {
			return false;
		} else {
			return true;
		}
	}
	
//	public boolean isTime1(float time, float needTime) {
//		if (needTime > time) {
//			return true;
//		} else {
//			return false;
//		}
//	}

	// dispatcher cv
	protected boolean isValid(String expression, String lastExpression, Elevator elevator) {
		if (expression.equals("RUN")) {
			return true;
		}
		if (!isLegalFormat(expression)) {
			String a = "INVALID[" + expression + "]";
			inValid.add(a);
			return false;
		}
		if (!isTimeSequenceLegal(expression, lastExpression)) {
			String a = "INVALID[" + expression + "]";
			inValid.add(a);
			return false;
		}
		if (isrepeated(expression, lastExpression, elevator)) {// �ж������Ƿ��ظ�
			String a = "SAME[" + expression + "]";
			inValid.add(a);
			return false;
		}
		String errors[] = { "[^1-9|10|UP|DOWN|FR|ER|\\(|\\)|\\,]", "^\\((FR)\\,1\\,(DOWN)\\,\\d{1,}\\)",
				"^\\((FR)\\,(10)\\,(UP)\\,\\d{1,}\\)", "\\,\\,", "^\\s*$" };

		expression = deleteAllBlank(expression);// ɾ�����пհ�
		for (int i = 0; i < errors.length; i++) {
			Pattern p = Pattern.compile(errors[i]);
			Matcher m = p.matcher(expression);
			if (m.find()) {
				String a = "INVALID[" + expression + "]";
				inValid.add(a);
				return false;
			}
		}

		return true;
	}

	// �����Ƿ��ظ�
	protected boolean isrepeated(String expression, String lastExpression, Elevator elevator) {
		System.out.println(lastExpression);
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
		if (isFloorRepeated(ElementsOfExpression, ElementsOfLastExpression, elevator)) {// ��һ����������ͬ
			if (isFloorRepeated(ElementsOfExpression, ElementsOfLastExpression, elevator)) {// ¥�����ж�
				return true;
			} else if (isElevatorRepeated(ElementsOfExpression, ElementsOfLastExpression, elevator)) {// �������ж�
				return true;
			}
		}
		return false;
	}

	// ¥�����Ƿ��ظ��ж�
	protected boolean isFloorRepeated(String[] ElementsOfExpression, String[] ElementsOfLastExpression,
			Elevator elevator) {
		if (ElementsOfExpression[1].equals(ElementsOfLastExpression[1])
				&& ElementsOfExpression[2].equals(ElementsOfLastExpression[2])) {
			int Time = Integer.parseInt(ElementsOfExpression[3]);
			int lastTime = Integer.parseInt(ElementsOfLastExpression[3]);
			// ����¥�Ĳ��0.5��1>=��������֮���ʱ���>=����¥�Ĳ��0.5
			if ((Math.abs(Integer.parseInt(ElementsOfExpression[1]) - elevator.getElevatorFloor()) * 0.5 <= Time
					- lastTime)
					&& (Time - lastTime <= Math
							.abs(Integer.parseInt(ElementsOfExpression[1]) - elevator.getElevatorFloor()) * 0.5 + 1)) {
				return true;
			}
		}
		return false;
	}

	// �������Ƿ��ظ��ж�
	protected boolean isElevatorRepeated(String[] ElementsOfExpression, String[] ElementsOfLastExpression,
			Elevator elevator) {
		if (ElementsOfExpression[1].equals(ElementsOfLastExpression[1])) {
			int Time = Integer.parseInt(ElementsOfExpression[2]);
			int lastTime = Integer.parseInt(ElementsOfLastExpression[2]);
			// ����¥�Ĳ��0.5��1>=��������֮���ʱ���>=����¥�Ĳ��0.5
			if ((Math.abs(Integer.parseInt(ElementsOfExpression[1]) - elevator.getElevatorFloor()) * 0.5 <= Time
					- lastTime)
					&& (Time - lastTime <= Math
							.abs(Integer.parseInt(ElementsOfExpression[1]) - elevator.getElevatorFloor()) * 0.5 + 1)) {
				return true;
			}
		}
		return false;
	}

	// ���������ʱ��˳���Ƿ�Ϸ�
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

	// �淶������
	protected String regularRequest(String request) {
		request = deleteAllBlank(request);// ɾ����������пհ�
		request = deleteBrackets(request);// ɾ����������
		return request;
	}

	// ��ȷ�����ʽ
	protected boolean isLegalFormat(String expression) {
		if (expression.matches("^\\(FR\\,([1-9]|10)\\,(UP|DOWN)\\,\\d+\\)$")
				|| (expression.matches("^\\(ER\\,([1-9]|10)\\,\\d+\\)"))) {
			return true;
		} else {
			return false;
		}
	}

	// FR��FR���Ƿ����
	protected boolean isFR_FRLegal(String[] str1, String[] str2) {
		if (str1[0].equals("FR") && str2[0].equals("FR")) {
			if (Integer.parseInt(str1[3]) >= Integer.parseInt(str2[3])) {
				return true;
			}
		}
		return false;
	}

	// FR��ER���Ƿ����
	protected boolean isFR_ERLegal(String[] str1, String[] str2) {
		if (str1[0].equals("FR") && str2[0].equals("ER")) {
			if (Integer.parseInt(str1[3]) >= Integer.parseInt(str2[2])) {
				return true;
			}
		}
		return false;
	}

	// ER��FR���Ƿ����
	protected boolean isER_FRLegal(String[] str1, String[] str2) {
		if (str1[0].equals("ER") && str2[0].equals("FR")) {
			if (Integer.parseInt(str1[2]) >= Integer.parseInt(str2[3])) {
				return true;
			}
		}
		return false;
	}

	// ER��ER���Ƿ����
	protected boolean isER_ERLegal(String[] str1, String[] str2) {
		if (str1[0].equals("ER") && str2[0].equals("ER")) {
			if (Integer.parseInt(str1[2]) >= Integer.parseInt(str2[2])) {
				return true;
			}
		}
		return false;
	}

	// ɾ�����пհ�
	protected String deleteAllBlank(String expression) {
		expression = expression.replaceAll(" ", "");
		expression = expression.replaceAll("\t", "");
		return expression;
	}

	// ɾ����������
	protected String deleteBrackets(String expression) {
		expression = expression.replace("(", "");
		expression = expression.replace(")", "");
		return expression;
	}
	
	//output���Ƿ��и����
	public boolean havaBigger(String request, ArrayList<String> output, String direction, int index) {
		boolean big = false;
		request = regularRequest(request);
		String symbols1[] = request.split(",");
		for (int i = 0; i < index; i++) {
			String outputRequest = regularRequest(output.get(i));
			String symbols2[] = outputRequest.split(",");
			if (direction.equals("UP")) {
				if (Integer.parseInt(symbols2[1]) > Integer.parseInt(symbols1[1])) {
					big =  true;
				}
			} else if (direction.equals("DOWN")) {
				if (Integer.parseInt(symbols2[1]) < Integer.parseInt(symbols1[1])) {
					big =  true;
				}
			}
		}
		return big;
	}
	
	//������ȷ�Ľڵ�
	public void dealRightNode() {}
}
