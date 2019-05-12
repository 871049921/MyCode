import java.util.ArrayList;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application{

	public static void main(String[] args) {
		Application.launch(args);

	}
	
	@Override
	public void start(Stage primaryStage) {
		
		Pane pane = new Pane();
		
		ArrayList<String> buyNumList = new ArrayList<>();// 存放下拉版的列表
		buyNumList.add("1");
		buyNumList.add("2");
		buyNumList.add("3");
		buyNumList.add("4");
		ComboBox<String> comboBox = new ComboBox<>();
		comboBox.setValue("1");// 设初值为1
		ObservableList<String> items = FXCollections.observableArrayList(buyNumList);
		comboBox.setEditable(true);
		comboBox.setItems(items);// 添加items
		comboBox.setOnAction(event1 -> {
			
		});// 获取点击下拉列表的下标

		pane.getChildren().add(comboBox);
		
		Scene scene = new Scene(pane, 1248, 648);
		primaryStage.setTitle("test");
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();
	}

}
