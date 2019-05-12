import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DispatcherTest extends Dispatcher {

	@Override
	// �����Ƿ�Ϸ�
	protected boolean isValid(String expression, String lastExpression) {
		if (expression.equals("RUN")) {
			return true;
		}
		if (!isLegalFormat(expression)) {
			outputArr[indexOfOutputArr++] = "INVALID[" + expression + "]";
			System.out.println("INVALID[" + expression + "]");// ����
			return false;
		}
		if (!isTimeSequenceLegal(expression, lastExpression)) {
			outputArr[indexOfOutputArr++] = "INVALID[" + expression + "]";
			System.out.println("INVALID[" + expression + "]");// ����
			return false;
		}
		// if (!firstRequestTimeIsZero(expression)) {// ��һ�������ʱ�䲻Ϊ0
		// outputArr[indexOfOutputArr++] = expression + "\r\nERROR\r\n#crash";
		// System.out.println(expression + "\r\nERROR\r\n#crash");// ����
		// try {
		// File_IO.crashError();
		// } catch (Exception e) {
		// System.exit(0);
		// }
		// System.exit(0);
		// return false;
		// }
		if (isrepeated(expression, lastExpression)) {// �ж������Ƿ��ظ�
			outputArr[indexOfOutputArr++] = "SAME[" + expression + "]";
			System.out.println("SAME[" + expression + "]");// ����
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
				outputArr[indexOfOutputArr++] = "INVALID[" + expression + "]";
				System.out.println("INVALID[" + expression + "]");// ����
				return false;
			}
		}

		return true;
	}
}
