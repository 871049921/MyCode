
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.shape.*;

public class Main extends Application {
	private String text = "";

	@Override // Override the start method in the Application class
	public void start(Stage primaryStage) {
		Pane pane = new Pane();
		Label lblText = new Label("Programming is fun");
		pane.getChildren().add(lblText);
		Rectangle rectangle1 = new Rectangle();
		Rectangle rectangle2 = new Rectangle();
		rectangle1.setHeight(100);
		rectangle1.setWidth(50);
		rectangle1.setStroke(Color.BLACK);
		rectangle1.setFill(Color.BLACK);
		rectangle1.setX(100);
		rectangle1.setY(200);
		rectangle2.setHeight(100);
		rectangle2.setWidth(50);
		rectangle2.setStroke(Color.BLACK);
		rectangle2.setFill(Color.RED);
		rectangle2.setX(150);
		rectangle2.setY(200);
		pane.getChildren().add(rectangle1);
		pane.getChildren().add(rectangle2);

		new Thread(new Runnable() {
			@Override
			public void run() {
				int pos = 1;
				try {
					while (true) {
						if (rectangle1.getWidth() >= 0 && rectangle1.getWidth() <= 50) {
							rectangle1.setWidth(rectangle1.getWidth() - pos);
							rectangle2.setWidth(rectangle1.getWidth() - pos);
							rectangle2.setX(rectangle2.getX() + pos);
						} else {
							pos = -pos;
							rectangle1.setWidth(rectangle1.getWidth() - pos);
							rectangle2.setWidth(rectangle1.getWidth() - pos);
						}

						Platform.runLater(new Runnable() {
							@Override
							public void run() {
								lblText.setText(text);
							}
						});

						Thread.sleep(100);
					}
				} catch (InterruptedException ex) {
				}
			}
		}).start();

		// Create a scene and place it in the stage
		Scene scene = new Scene(pane, 500, 500);
		primaryStage.setTitle("FlashText"); // Set the stage title
		primaryStage.setScene(scene); // Place the scene in the stage
		primaryStage.show(); // Display the stage
	}

	public static void main(String[] args) {
		launch(args);
	}
}