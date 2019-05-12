package assignment2;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class File_IO {
	
	//���췽��
	public File_IO() {
		
	}
	
	//��ȡ�ļ�
	public String[] outputInformation() throws Exception {
		File f1 = new File("Requests.txt");//���������ĵ�
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
	
	//�������е���Ϣ�����ļ���
	public void inputInformation(String[] str) throws Exception {
		File f2 = new File("output.txt");
		if (!f2.exists()) {
			f2.createNewFile();
		}
		FileWriter output = new FileWriter(f2);
		for (int i = 0; str[i] != null; i++) {
			if (str[i+1] != null) {
				output.write(str[i] + "\r\n");
			}
			else {
				output.write(str[i]);
			}
		}
		output.close();
	}
	
	//crash����
	public static void crashError()throws Exception {
		File f2 = new File("output.txt");
		if (!f2.exists()) {
			f2.createNewFile();
		}
		FileWriter output = new FileWriter(f2);
		output.write("ERROR\r\n#crash");
		output.close();
	}
}