
import java.util.ArrayList;

import javafx.animation.PathTransition;
import javafx.application.*;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Assignment5 extends Application {

		private static String[] output;
		private static String[] output1 = new String[1000];

		public static void main(String[] args) throws Exception {
			File_IO file = new File_IO();
			String requests[] = file.outputInformation();

			LinkList linklist = new LinkList();
			for (int i = 0; requests[i] != null; i++) {
				linklist.inList(requests[i]);
			}
			Dispatcher newDispatcher = new NewDispatcher(linklist);
			newDispatcher.dealWithRequests();
			output = newDispatcher.getOutputArr();
			for (int i = 0; output[i] != null; i++) {
				output1[i] = output[i].split("/")[1];
				output1[i] = output1[i].replace("(", "");
				output1[i] = output1[i].replace("(", "");
			}
			file.inputInformation(newDispatcher.getOutputArr(), linklist.getInValid());
			String averangeTime = "�˿͵�ƽ���ȴ�ʱ����" + newDispatcher.getAllWaitsTime() / newDispatcher.getFRNumber() + "��";
		ArrayList<String> waitsTime = newDispatcher.getWaitsTime();
		waitsTime.add(averangeTime);
		file.inputInformation(waitsTime);
		Application.launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		BorderPane pane = new BorderPane();
		Rectangle rectangle1 = new Rectangle();
		Rectangle rectangle2 = new Rectangle();
		Button btStart = new Button("��ʼ");
		Button btPause = new Button("��ͣ");
		Button btClose = new Button("�ر�");
		HBox hBox = new HBox();
		VBox vBox1 = new VBox();
		VBox vBox2 = new VBox();
		Label lbFloor = new Label("��ǰ¥����Ϊ��1");
		Label lbNumber = new Label("����������Ϊ��0");
		Polygon pgUP = createAPolygon("UP");
		Polygon pgDOWN = createAPolygon("DOWN");
		rectangle1.setHeight(300);
		rectangle1.setWidth(75);
		rectangle1.setStroke(Color.BLACK);
		rectangle1.setFill(Color.BLACK);
		rectangle2.setHeight(300);
		rectangle2.setWidth(75);
		rectangle2.setStroke(Color.BLACK);
		rectangle2.setFill(Color.BLACK);
		hBox.getChildren().addAll(rectangle1, rectangle2);
		hBox.setAlignment(Pos.CENTER);
		vBox1.getChildren().addAll(lbFloor, pgUP, pgDOWN);
		vBox1.setAlignment(Pos.CENTER);
		vBox2.getChildren().addAll(lbNumber, btStart, btPause, btClose);
		vBox2.setAlignment(Pos.CENTER);
		pane.setCenter(hBox);
		pane.setLeft(vBox1);
		pane.setRight(vBox2);

		btStart.setOnAction(e -> {
			vBox2.getChildren().removeAll(btStart);

			new Thread(new Runnable() {// ������ϵ�

				private boolean isStationary = false;// Ĭ�ϲ�ִ��wait, ����ͣ��

				public synchronized void toStation() {// ͬ������
					isStationary = true;
				}

				public synchronized void toMove() {// ��ִ��wait����������ͣ���߳�
					isStationary = false;
					
					notify();// ��ǰ�ȴ��Ľ��̣�����ִ��
				}

				int i = 0;
				int numberInElevator = 0;

				@Override
				public void run() {

					btPause.setOnAction(e -> {
						if (btPause.getText().equals("��ͣ")) {
							btPause.setText("����");
							toStation();
						} else {
							btPause.setText("��ͣ");
							toMove();
						}
					});

					try {
						for (; output1[i] != null; i++) {
							if (output1[i].contains("STILL")) {
								pgDOWN.setFill(Color.GREEN);
								pgUP.setFill(Color.GREEN);
								doorPathTransition(rectangle1, "LEFT");
								doorPathTransition(rectangle2, "RIGHT");
							} else if (output1[i].contains("UP")) {
								pgDOWN.setFill(Color.WHITE);
								pgUP.setFill(Color.RED);
								doorPathTransition(rectangle1, "LEFT");
								doorPathTransition(rectangle2, "RIGHT");
							} else if (output1[i].contains("DOWN")) {
								pgUP.setFill(Color.WHITE);
								pgDOWN.setFill(Color.RED);
								doorPathTransition(rectangle1, "LEFT");
								doorPathTransition(rectangle2, "RIGHT");
							}

							Platform.runLater(new Runnable() {
								@Override
								public void run() {
									lbFloor.setText("��ǰ¥����Ϊ��" + output1[i].split(",")[0]);
									if (output[i].contains("FR")) {
										numberInElevator++;
									} else if (output[i].contains("ER") && numberInElevator != 0) {
										numberInElevator--;
									}
									lbNumber.setText("����������Ϊ��" + numberInElevator);

								}
							});

							Thread.sleep(2500);
							synchronized (this) {// ������ϵ�
								while (isStationary) {
									try {
										wait();// �߳̽���ȴ�״̬
									} catch (InterruptedException e) {

									}
								}
							}
						}
					} catch (InterruptedException ex) {

					}
				}
			}).start();
		});

		btClose.setOnAction(e -> {
			System.exit(0);
		});

		Scene scene = new Scene(pane, 500, 500);
		primaryStage.setTitle("����");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	// �ŵĶ���
	public void doorPathTransition(Rectangle rectangle, String direction) {
		PathTransition pt = new PathTransition();
		Line line;
		if (direction.equals("LEFT")) {
			line = new Line(25, 150, 0, 150);
		} else if (direction.equals("RIGHT")){
			line = new Line(25, 150, 50, 150);
		} else {
			line = new Line();
		}
		pt.setDuration(Duration.millis(1000));
		pt.setPath(line);
		pt.setNode(rectangle);
		pt.setAutoReverse(true);
		pt.setCycleCount(2);
		pt.play();
	}

	// ���¼�ͷ
	public Polygon createAPolygon(String direction) {
		Polygon pg = new Polygon();
		pg.setFill(Color.WHITE);
		pg.setStroke(Color.BLACK);
		ObservableList<Double> list = pg.getPoints();
		if (direction.equals("UP")) {
			list.addAll(30.0, 0.0, 0.0, 40.0, 20.0, 40.0, 20.0, 80.0, 40.0, 80.0, 40.0, 40.0, 60.0, 40.0);
		} else if (direction.equals("DOWN")) {
			list.addAll(20.0, 120.0, 20.0, 160.0, 0.0, 160.0, 30.0, 200.0, 60.0, 160.0, 40.0, 160.0, 40.0, 120.0);
		}
		pg.setFill(Color.WHITE);
		return pg;
	}

}
