
import javax.swing.plaf.synth.SynthSpinnerUI;

import com.sun.prism.paint.Color;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Main extends Application{
	
	private Pane pane = new Pane();
	
	public static void main(String args[]) {
		Application.launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) {
		HBox hBox = new HBox();
		hBox.setSpacing(10);
		hBox.setAlignment(Pos.CENTER);
		Button btEllipse = new Button("Draw Ellipse");
		Button btCircle = new Button("Draw Circle");
		Button btRectangle = new Button("Draw Rectangle");
		Button btClose = new Button("Close");
		hBox.getChildren().add(btEllipse);
		hBox.getChildren().add(btCircle);
		hBox.getChildren().add(btRectangle);
		hBox.getChildren().add(btClose);
		
		btClose.setOnAction(e -> {
			System.exit(0);
		});
		
		btEllipse.setOnAction(e -> {
			Ellipse ellipse = new Ellipse();
			ellipse.setCenterX(200);
			ellipse.setCenterY(200);
			ellipse.setRadiusX(100);
			ellipse.setRadiusY(200);
			pane.getChildren().add(ellipse);
		});
		
		btCircle.setOnAction(e -> {
			Circle circle = new Circle();
			circle.setCenterX(350);
			circle.setCenterY(350);
			circle.setRadius(50);
			pane.getChildren().add(circle);
		});
		
		btRectangle.setOnAction(e -> {
			Rectangle rectangle = new Rectangle();
			rectangle.setX(500);
			rectangle.setY(500);
			rectangle.setWidth(100);
			rectangle.setHeight(100);
			rectangle.setRotate(100);
			pane.getChildren().add(rectangle);
		});

		
		BorderPane borderPane = new BorderPane();
		borderPane.setCenter(pane);
		borderPane.setBottom(hBox);
		BorderPane.setAlignment(hBox, Pos.CENTER);
		Scene scene = new Scene(borderPane, 1000, 1000);
		primaryStage.setTitle("»ÆÎ¤µÂ");
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
}
