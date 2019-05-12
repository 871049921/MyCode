import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class File_IO {

	// ���췽��
	public File_IO() {

	}

	// ��ȡ�ļ�
	public ArrayList<String> outputInformation() throws Exception {
		File f1 = new File("xml1.txt");// ���������ĵ�
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
	
	// ��ȡ�ļ�����·����
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

	// �������е���Ϣ�����ļ���
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
	
	// �������е���Ϣ�����ļ���(���ļ� String[])
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
	
	// �������е���Ϣ�����ļ���(���ļ� ArrayList)
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
	
	// �ļ�ѡ����
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