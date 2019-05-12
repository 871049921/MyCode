import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class File_IO {

	// 构造方法
	public File_IO() {

	}

	// 读取文件
	public ArrayList<String> outputInformation() throws Exception {
		File f1 = new File("xml1.txt");// 创建请求文档
		Scanner input = new Scanner(f1);
		if (!f1.exists()) {
			f1.createNewFile();
		}
		ArrayList<String> recv = new ArrayList<String>();
		while(input.hasNextLine()) {
			recv.add(input.nextLine());
		}
		input.close();
		return recv;
	}
	
	// 读取文件（有路径）
	public ArrayList<String> outputInformation(File f) throws Exception {
		Scanner input = new Scanner(f);
		if (!f.exists()) {
			f.createNewFile();
		}
		ArrayList<String> recv = new ArrayList<String>();
		while(input.hasNextLine()) {
			recv.add(input.nextLine());
		}
		input.close();
		return recv;
	}

	// 将数组中的信息传到文件中
	public void inputInformation(String[] str) throws Exception {
		File f2 = new File("xml1.txt");
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
		output.close();
	}
	
	// 将数组中的信息传到文件中(有文件 String[])
	public void inputInformation(String[] str, File f) throws Exception {
		if (!f.exists()) {
			f.createNewFile();
		}
		FileWriter output = new FileWriter(f);
		for (int i = 0; i < str.length; i++) {
			try {
				output.write(str[i]);
				output.write("\r\n");
			} catch (NullPointerException e) {

			}
		}
		output.close();
	}
	
	// 将数组中的信息传到文件中(有文件 ArrayList)
	public void inputInformation(ArrayList<Object> str, File f) throws Exception {
		if (!f.exists()) {
			f.createNewFile();
		}
		FileWriter output = new FileWriter(f);
		for (int i = 0; i < str.size(); i++) {
			try {
				output.write((String)str.get(i));
				//output.write("\r\n");
			} catch (NullPointerException e) {
				
			}
		}
		output.close();
	}
	
	// 文件选择器
	public File chooseFolder() {
		try {
			FileChooser fileChooser = new FileChooser();
			fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt", "XML files (*.xml)", "*.xml"));
			Stage fileStage = null;
			File selectedFile = fileChooser.showOpenDialog(fileStage);
			return selectedFile;
		}
		catch(Exception e) {
			return null;
		}
	}
}