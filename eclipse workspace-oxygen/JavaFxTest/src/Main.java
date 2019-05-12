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
		
		ArrayList<String> buyNumList = new ArrayList<>();// �����������б�
		buyNumList.add("1");
		buyNumList.add("2");
		buyNumList.add("3");
		buyNumList.add("4");
		ComboBox<String> comboBox = new ComboBox<>();
		comboBox.setValue("1");// ���ֵΪ1
		ObservableList<String> items = FXCollections.observableArrayList(buyNumList);
		comboBox.setEditable(true);
		comboBox.setItems(items);// ���items
		comboBox.setOnAction(event1 -> {
			
		});// ��ȡ��������б���±�

		pane.getChildren().add(comboBox);
		
		Scene scene = new Scene(pane, 1248, 648);
		primaryStage.setTitle("test");
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();
	}

}
