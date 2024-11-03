package application;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class Test extends Application {

    @Override
    public void start(Stage primaryStage) {
        FlowPane flowPane = new FlowPane();
        flowPane.setPadding(new Insets(10));
        flowPane.setHgap(10); // المسافة الأفقية بين العناصر
        flowPane.setVgap(10); // المسافة الرأسية بين العناصر
        flowPane.setPrefWrapLength(200); // عرض السطر
        flowPane.setColumnHalignment(javafx.geometry.HPos.CENTER); // توسيط العناصر أفقياً في كل سطر
        flowPane.setRowValignment(javafx.geometry.VPos.CENTER); // توسيط العناصر رأسياً في كل سطر

        for (int i = 0; i < 10; i++) {
            Button button = new Button("Button " + i);
            flowPane.getChildren().add(button);
        }

        Scene scene = new Scene(flowPane, 400, 300);

        primaryStage.setScene(scene);
        primaryStage.setTitle("FlowPane Example");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
