import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class JAVAFX extends Application{
	
	ArrayList<String> receive;
	File f;
	File file = new File("date.txt");
	
	public JAVAFX() {
		
	}
	
	public JAVAFX(ArrayList<String> receive) {
		this.receive = receive;
	}
	
	public void go(String[] args) {
		Application.launch(args);
	}
	
	@Override
	public void start(Stage primaryStage){
		HBox hbMainPane = new HBox();// 主要场景
		VBox vbMainPane = new VBox();// 主要场景
		TextArea XMLEditable = new TextArea();
		XMLEditable.setEditable(true);
		XMLEditable.setMinHeight(630);
		TextArea XMLUneditable = new TextArea();
		XMLUneditable.setEditable(false);
		hbMainPane.getChildren().addAll(XMLUneditable, XMLEditable);
		
		StackPane stackPane = new StackPane();
		Button btOpen = new Button("打开");
		btOpen.setMinWidth(200);
		Button btXMLToTXT = new Button("变为txt");
		btXMLToTXT.setMinWidth(200);
		Button btTXTToXML = new Button("变为xml");
		btTXTToXML.setMinWidth(200);
		Button btSave = new Button("保存");
		Button btInsert = new Button("插入数据");
		btSave.setMinWidth(200);
		btInsert.setMinWidth(200);
		Button btTotxt = new Button("表格变txt");
		btTotxt.setMinWidth(200);
		HBox hbMainButton = new HBox();
		hbMainButton.getChildren().addAll(btOpen, btXMLToTXT, btTXTToXML, btSave, btInsert);
		vbMainPane.getChildren().addAll(hbMainButton, hbMainPane);
		
		// 将XML转化为TXT
		btXMLToTXT.setOnAction(e -> {
			try {
				XMLtoTXT(XMLUneditable, XMLEditable);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});
		
		// 导入XML文件
		btOpen.setOnAction(e -> {
			try {
				importXML(XMLEditable);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});
		
		// 保存文件
		btSave.setOnAction(e -> {
			try {
				savaFile(XMLEditable);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});
		
		// 插入数据
		btInsert.setOnAction(e -> {
			try {
				ArrayList<Object> a = insertDate("插入数据", new Button(), new Button());
				hbMainButton.getChildren().add(btTotxt);
				btTotxt.setOnAction(ex -> {
					File_IO f = new File_IO();
					try {
						ArrayList<Object> al = new ArrayList<Object>();
						formToTxt(a, 0, al);
						f.inputInformation(al, file);
						ArrayList<String> recv1 = f.outputInformation(file);
						XMLEditable.clear();
						for(int i = 0; i < recv1.size() && !recv1.get(i).equals(""); i ++) {
							XMLEditable.appendText(recv1.get(i) + "\r\n");
						}
					}
					catch(Exception eq) {
						
					}
					hbMainButton.getChildren().removeAll(btTotxt);
				});
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});
		
		btTXTToXML.setOnAction(e -> {
			try {
				ArrayList<String> recv = new ArrayList<String>(Arrays.asList(XMLEditable.getText().split("\n")));
				DealTXT deal = new DealTXT(recv);
				XMLUneditable.clear();
				deal.TXTToXML();
				for(int i = 0; i < recv.size(); i ++) {
					XMLUneditable.appendText(recv.get(i) + "\r\n");
				}
				f = new File("my.txt");
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});
		
		stackPane.getChildren().addAll(vbMainPane);
		
		Scene scene = new Scene(stackPane, 1248, 648);
		primaryStage.setTitle("xml编辑器");
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();
	}
	
	// 导入XML文件
	public void importXML(TextArea XMLEditable) throws Exception {
		ArrayList<String> recv = new ArrayList<String>();
		File_IO file = new File_IO();
		f = file.chooseFolder();
		if(f == null) {
			popUp("打开错误");
		} else {
			if(!f.getPath().equals("")) {
				recv = file.outputInformation(f);
			}
			XMLEditable.clear();
			for(int i = 0; i < recv.size(); i ++) {
				XMLEditable.appendText(recv.get(i) + "\r\n");
			}
		}
	}
	
	// 将XML文件转化为TXT文件
	public void XMLtoTXT(TextArea XMLUneditable, TextArea XMLEditable) throws Exception {
		
		ArrayList<String> recv = new ArrayList<String>(Arrays.asList(XMLEditable.getText().split("\n")));
		DealXML dealXML = new DealXML(recv);
		
		recv = dealXML.toTXT();
		XMLUneditable.clear();
		for(int i = 0; i < recv.size(); i ++) {
			XMLUneditable.appendText(recv.get(i) + "\r\n");
		}
	}
	
	// 保存文件
	public void savaFile(TextArea XMLEditable) throws Exception {
		String[] save = XMLEditable.getText().split("\r");
		File_IO file = new File_IO();
		try {
			file.inputInformation(save, f);
			popUp("保存成功");
		}
		catch(Exception e) {
			popUp("保存失败！");
		}
	}
	
	// 插入数据
	public ArrayList<Object> insertDate(String titleName, Button lastSaveAll, Button lastInsert) {
		Stage stage = new Stage();
		Pane pane = new Pane();
		boolean[] isInserted = new boolean[6];
		isInserted[0] = false;// 判断是否插入了数据
		isInserted[1] = false;// 判断是否插入了数据
		isInserted[2] = false;// 判断是否插入了数据
		isInserted[3] = false;// 判断是否插入了数据
		isInserted[4] = false;// 判断是否插入了数据
		isInserted[5] = false;// 判断是否插入了数据
		ArrayList<Object> date = new ArrayList<Object>();// 数据
		date.add(titleName);// 第一位存名字
		for(int i = 1; i < 6; i ++) {
			date.add(i, "");
		}
		lastSaveAll.setDisable(true);
		lastInsert.setDisable(true);
		
		TextField tf1 = new TextField();
		TextField tf2 = new TextField();
		TextField tf3 = new TextField();
		TextField tf4 = new TextField();
		TextField tf5 = new TextField();
		Button btSaveAll = new Button("保存所有");
		Button btInsert1 = new Button("插入数据");
		Button btInsert2 = new Button("插入数据");
		Button btInsert3 = new Button("插入数据");
		Button btInsert4 = new Button("插入数据");
		Button btInsert5 = new Button("插入数据");
		VBox vbtf = new VBox();
		HBox hb1 = new HBox();
		HBox hb2 = new HBox();
		HBox hb3 = new HBox();
		HBox hb4 = new HBox();
		HBox hb5 = new HBox();
		hb1.getChildren().addAll(tf1, btInsert1);
		hb2.getChildren().addAll(tf2, btInsert2);
		hb3.getChildren().addAll(tf3, btInsert3);
		hb4.getChildren().addAll(tf4, btInsert4);
		hb5.getChildren().addAll(tf5, btInsert5);
		
		vbtf.getChildren().addAll(hb1, hb2, hb3, hb4, hb5, btSaveAll);
		
		pane.getChildren().add(vbtf);
		
		Pattern pattern = Pattern.compile("\\s+|(^$)");// 空白符
		
		btSaveAll.setOnAction(e -> {
			lastSaveAll.setDisable(false);
			lastInsert.setDisable(false);
			if(!isInserted[0]) {
				if(!tf1.getText().equals("")) {
					if(isInserted[1] == false) {
						date.set(1, tf1.getText());
					}
				}
				if(!tf2.getText().equals("")) {
					if(isInserted[2] == false) {
						date.set(2, tf2.getText());
					}
				}
				if(!tf3.getText().equals("")) {
					if(isInserted[3] == false) {
						date.set(3, tf3.getText());
					}
				}
				if(!tf4.getText().equals("")) {
					if(isInserted[4] == false) {
						date.set(4, tf4.getText());
					}
				}
				if(!tf5.getText().equals("")) {
					if(isInserted[5] == false) {
						date.set(5, tf5.getText());
					}
				}
			}
			removeBlank(date);
			System.out.println(date);
			stage.close();
		});
		
		btInsert1.setOnAction(e -> {
			Matcher m = pattern.matcher(tf1.getText());
			if(m.find()) {
				popUp("存在非法字符！！！");
			} else {
				isInserted[1] = true;
				date.set(1, insertDate(tf1.getText(), btSaveAll, btInsert1));
			}
		});
		
		btInsert2.setOnAction(e -> {
			Matcher m = pattern.matcher(tf2.getText());
			if(m.find()) {
				popUp("存在非法字符！！！");
			} else {
				isInserted[2] = true;
				date.set(2, insertDate(tf2.getText(), btSaveAll, btInsert2));
			}
		});
		
		btInsert3.setOnAction(e -> {
			Matcher m = pattern.matcher(tf3.getText());
			if(m.find()) {
				popUp("存在非法字符！！！");
			} else {
				isInserted[3] = true;
				date.set(3, insertDate(tf3.getText(), btSaveAll, btInsert3));
			}
		});
		
		btInsert4.setOnAction(e -> {
			Matcher m = pattern.matcher(tf4.getText());
			if(m.find()) {
				popUp("存在非法字符！！！");
			} else {
				isInserted[4] = true;
				date.set(4, insertDate(tf4.getText(), btSaveAll, btInsert4));
			}
		});
		
		btInsert5.setOnAction(e -> {
			Matcher m = pattern.matcher(tf5.getText());
			if(m.find()) {
				popUp("存在非法字符！！！");
			} else {
				isInserted[5] = true;
				date.set(5, insertDate(tf5.getText(), btSaveAll, btInsert5));
			}
		});
		
		Scene scene = new Scene(pane, 300, 200);
		stage.setTitle(titleName);
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();
		return date;
	}
	
	// 去除ArrayList中的空白
	public ArrayList<Object> removeBlank(ArrayList<Object> date) {
		for(int i = 0; i < date.size();) {
			if(date.get(i).equals("") || date.get(i).equals(" ") || date.get(i).equals(null)) {
				date.remove(i);
			} else {
				i ++;
			}
		}
		return date;
	}
	
	// 弹窗
	public void popUp(String imformation) {
		Stage stage = new Stage();
		StackPane stackPane = new StackPane();
		Label label = new Label(imformation);
		Button btComfirm = new Button("确认");
		VBox vbMain = new VBox();
		vbMain.setPadding(new Insets(30));
		vbMain.setSpacing(30);
		vbMain.getChildren().addAll(label, btComfirm);
		stackPane.getChildren().add(vbMain);
		btComfirm.setOnAction(e -> stage.close());
		
		Scene scene = new Scene(stackPane, 200, 100);
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();
	}
	
	// 表格变为txt
	public void formToTxt(ArrayList<Object> form, int margin, ArrayList<Object> output) {
		for(int i = 0; i < form.size(); i ++) {
			if(!(form.get(i) instanceof String)) {// 为ArrayList
				@SuppressWarnings("unchecked")
				ArrayList<Object> a = (ArrayList<Object>)form.get(i);
				formToTxt(a, margin, output);
			} else {// 数据
				if(form.get(i).equals("插入数据") || form.get(i).equals("") || form.get(i).equals(" ") || form.get(i).equals(null)) {
					continue;
				}
				String str = creatBlank(margin) + (String)form.get(i) + "\n";
				output.add(str);
				System.out.print(str);
				if(i == 0) {
					margin ++;
				}
			}
		}
		margin --;		
	}
	
	// 创建空格
	public String creatBlank(int count) {
		String space = "";
		for(int j = 0; j < count; j ++) {
			space += " ";
		}
		return space;
	}
}
