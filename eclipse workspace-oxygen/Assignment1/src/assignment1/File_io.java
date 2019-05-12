package assignment1;

import java.io.*;
import java.util.Scanner;
//文件的输入输出
public class File_io {
	
	public File_io() {
		
	}
	
//	public void inputInformation() throws Exception {
//		Scanner inputFromKeyBoard = new Scanner(System.in);
//		File f1 = new File("Informations.txt");
//		PrintWriter output = new PrintWriter(f1);
//		String informations = inputFromKeyBoard.nextLine();
//		output.print(informations);
//		output.close();
//		Scanner inputFile = new Scanner(f1);
//		String a = inputFile.next();
//		System.out.println(a);
//		inputFile.close();
//	}
	
	public String[] outputInformation() throws Exception{
		File f1 = new File("Informations.txt");
		Scanner input = new Scanner(f1);
		if (!f1.exists()) {
			System.exit(0);
		}
		String[] recv = new String[10000];
		for (int i = 0; input.hasNextLine() == true; i++) {
			recv[i] = input.nextLine();
			//System.out.println(recv[i]);
		}
		input.close();
		return recv;
	}
}
