package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			//scene.setFill(Color.ALICEBLUE);
			//root.setStyle("-fx-background-color: ALICEBLUE");
			//scene.setFill(Color.PINK);
			//root.setStyle("-fx-background-color: PINK");
			//scene.setFill(Color.SLATEGRAY);
			//root.setStyle("-fx-background-color: SLATEGRAY");

			scene.setFill(Color.MEDIUMPURPLE);
			//root.setStyle("-fx-background-color: BURLYWOOD");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
