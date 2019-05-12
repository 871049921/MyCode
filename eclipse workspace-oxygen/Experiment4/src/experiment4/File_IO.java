package experiment4;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class File_IO {
	// 将数组中的信息传到文件中
	public static void inputInformation(ArrayList<String> str) throws Exception {
		File f2 = new File("output.txt");
		if (!f2.exists()) {
			f2.createNewFile();
		}
		FileWriter output = new FileWriter(f2);
		for (int i = 0; i < str.size(); i++) {
			output.write(str.get(i) + "\r\n");
		}
		output.close();
	}

	// 读取文件
	public ArrayList<String> outputInformation() throws Exception {
		File f1 = new File("output.txt");// 创建请求文档
		Scanner input = new Scanner(f1);
		if (!f1.exists()) {
			f1.createNewFile();
		}
		ArrayList<String> recv = new ArrayList<String>();
		for (int i = 0; input.hasNextLine() == true; i++) {
			recv.add(i, input.nextLine());
		}
		input.close();
		return recv;
	}
}
