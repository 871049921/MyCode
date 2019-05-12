package assignment1;

import java.io.*;
import java.util.Scanner;
//文件的输入输出
public class File_io {
	
	public File_io() {
		
	}
	
	//将文件中的信息传到数组中
	public String[] outputInformation() throws Exception{
		File f1 = new File("Informations.txt");
		Scanner input = new Scanner(f1);
		if (!f1.exists()) {
			f1.createNewFile();
		}
		String[] recv = new String[1000000];
		int i = 0;
		while (input.hasNextLine()) {
			recv[i] = input.nextLine();
			i++;
		}
		input.close();
		return recv;
	}
	
	//将数组中的信息传到文件中
	public static void inputInformation(String[] str) throws Exception{
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
	
}
