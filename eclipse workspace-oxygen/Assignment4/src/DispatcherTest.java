import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DispatcherTest extends Dispatcher {

	@Override
	// 请求是否合法
	protected boolean isValid(String expression, String lastExpression) {
		if (expression.equals("RUN")) {
			return true;
		}
		if (!isLegalFormat(expression)) {
			outputArr[indexOfOutputArr++] = "INVALID[" + expression + "]";
			System.out.println("INVALID[" + expression + "]");// 测试
			return false;
		}
		if (!isTimeSequenceLegal(expression, lastExpression)) {
			outputArr[indexOfOutputArr++] = "INVALID[" + expression + "]";
			System.out.println("INVALID[" + expression + "]");// 测试
			return false;
		}
		// if (!firstRequestTimeIsZero(expression)) {// 第一个请求的时间不为0
		// outputArr[indexOfOutputArr++] = expression + "\r\nERROR\r\n#crash";
		// System.out.println(expression + "\r\nERROR\r\n#crash");// 测试
		// try {
		// File_IO.crashError();
		// } catch (Exception e) {
		// System.exit(0);
		// }
		// System.exit(0);
		// return false;
		// }
		if (isrepeated(expression, lastExpression)) {// 判断请求是否重复
			outputArr[indexOfOutputArr++] = "SAME[" + expression + "]";
			System.out.println("SAME[" + expression + "]");// 测试
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
				outputArr[indexOfOutputArr++] = "INVALID[" + expression + "]";
				System.out.println("INVALID[" + expression + "]");// 测试
				return false;
			}
		}

		return true;
	}
}
