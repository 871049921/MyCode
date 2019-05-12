
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Main  extends Application {

	public static void main(String[] args) {
		
		
		Application.launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) {
		StackPane stackPane = new StackPane();
		stackPane.setAlignment(Pos.CENTER);
		HBox hbUsersName = new HBox();
		HBox hbPassWord = new HBox();
		HBox hbComfirmPassWord = new HBox();
		HBox hbEMail = new HBox();
		HBox hbPhoneNumber = new HBox();
		VBox vBox = new VBox();
		Label lbUsersName = new Label("�û�����                ");
		Label lbPassWord = new Label("��½���룺             ");
		Label lbComfirmPassWord = new Label("ȷ�����룺             ");
		Label lbEMail = new Label("���䣺                    ");
		Label lbPhoneNumber = new Label("�����������ֻ��ţ�");
		TextField tfUsersName = new TextField();
		TextField tfPassWord = new TextField();
		TextField tfComfirmPassWord = new TextField();
		TextField tfEMail = new TextField();
		TextField tfPhoneNumber = new TextField();
		Button btcomFirm = new Button("�ύע����Ϣ");
		stackPane.setPadding(new Insets(50, 25, 25, 25));
		btcomFirm.setMinWidth(350);
		
//		hbUsersName.setAlignment(Pos.CENTER);
//		hbPassWord.setAlignment(Pos.CENTER);
//		hbComfirmPassWord.setAlignment(Pos.CENTER);
//		hbEMail.setAlignment(Pos.CENTER);
//		hbPhoneNumber.setAlignment(Pos.CENTER);
//		btcomFirm.setAlignment(Pos.CENTER);
		
		hbUsersName.getChildren().addAll(lbUsersName, tfUsersName);
		hbPassWord.getChildren().addAll(lbPassWord, tfPassWord);
		hbComfirmPassWord.getChildren().addAll(lbComfirmPassWord, tfComfirmPassWord);
		hbEMail.getChildren().addAll(lbEMail, tfEMail);
		hbPhoneNumber.getChildren().addAll(lbPhoneNumber, tfPhoneNumber);
		vBox.getChildren().addAll(hbUsersName, hbPassWord, hbComfirmPassWord, hbEMail, hbPhoneNumber, btcomFirm);
		stackPane.getChildren().add(vBox);
		
		//��ע�ᰴť��Ĳ���
		btcomFirm.setOnAction(e -> {
			// ��ע��ɹ���Ϣ
		});
		
		Scene scene = new Scene(stackPane, 400, 300);
		primaryStage.setScene(scene);
		primaryStage.setTitle("�");
		primaryStage.show();
	}
}