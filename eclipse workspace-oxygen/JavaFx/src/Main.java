
import javafx.application.*;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.shape.Rectangle;

public class Main extends Application{
	public static void main(String[] args) {
		Application.launch(args);
	}
	
	@Override
	public void start(Stage primartStage) {
		Rectangle rectangle = new Rectangle();
		rectangle.setX(19);
		rectangle.setY(36);
		rectangle.setHeight(19);
		rectangle.setWidth(36);
		rectangle.setStroke(Color.RED);
		rectangle.setFill(Color.YELLOW);
//		Circle circle = new Circle();
//		circle.setCenterX(100);
//		circle.setCenterY(100);
//		circle.setRadius(30);
//		circle.setStroke(Color.BLACK);
//		circle.setFill(Color.RED);
		StackPane pane = new StackPane();
		pane.setStyle("-fx-border-color: blue");
		pane.setRotate(12);
		pane.getChildren().add(rectangle);
		Scene scene = new Scene(pane, 500, 600);
		
		primartStage.setTitle("»ÆÎ¤µÂ");
		primartStage.setScene(scene);
		primartStage.show();
		
//		Stage stage = new Stage();
//		stage.setTitle("Second stage");
//		stage.setScene(new Scene(new Button("??"), 100, 100));
//		stage.show();
	}
}
