
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class File_IO {

	// 构造方法
	public File_IO() {

	}

	// 读取文件
	public String[] outputInformation() throws Exception {
		File f1 = new File("Requests.txt");// 创建请求文档
		Scanner input = new Scanner(f1);
		if (!f1.exists()) {
			f1.createNewFile();
		}
		String[] recv = new String[1000000];
		for (int i = 0; input.hasNextLine() == true; i++) {
			recv[i] = input.nextLine();
		}
		input.close();
		return recv;
	}

	// 将数组中的信息传到文件中
	public void inputInformation(String[] str, ArrayList<String> inValid) throws Exception {
		File f2 = new File("output.txt");
		if (!f2.exists()) {
			f2.createNewFile();
		}
		FileWriter output = new FileWriter(f2);
		for (int i = 0; i < str.length; i++) {
			try {
				output.write(str[i]);
				output.write("\r\n");
			} catch (NullPointerException e) {

			}
		}
		for (int i = 0; i < inValid.size(); i++) {
			output.write(inValid.get(i));
			output.write("\r\n");
		}
		output.close();
	}

	// crash错误
	public static void crashError() throws Exception {
		File f2 = new File("output.txt");
		if (!f2.exists()) {
			f2.createNewFile();
		}
		FileWriter output = new FileWriter(f2);
		output.write("ERROR\r\n#crash");
		output.close();
	}
	
	public void inputInformation(ArrayList<String> str) throws Exception {
		File f2 = new File("waitsTime.txt");
		if (!f2.exists()) {
			f2.createNewFile();
		}
		FileWriter output = new FileWriter(f2);
		for (int i = 0; i < str.size(); i++) {
			try {
				output.write(str.get(i));
				output.write("\r\n");
			} catch (NullPointerException e) {

			}
		}
		output.close();
	}
}
