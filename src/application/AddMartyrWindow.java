package application;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class AddMartyrWindow {
	Stage addMartyrStage ;
	TextField enterInformation ;
	Button AddToFile;
	HBox hbox ;
	StackPane stackPane;
	Scene AddToFileScene ;
	
	
	AddMartyrWindow(){
		addMartyrStage = new Stage();
		enterInformation = new TextField();
		AddToFile = new Button("Add to file");
		hbox = new HBox(10);
		stackPane = new StackPane();
		AddToFileScene = new Scene(stackPane, 600, 150);
		
		//add the (TextField which we write the information of martyr inside it and the Labels and the button which adds the martyr to the file) to HBox 
		hbox.getChildren().addAll(new Label("Add Martyr:(Name  date of martyrdom(day/month/year))"),enterInformation, AddToFile);
		hbox.setAlignment(Pos.CENTER);
		
		//add the HBox to StackPane 
		stackPane.getChildren().add(hbox);
		
		//add the Scene to Stage of this window
		addMartyrStage.setScene(AddToFileScene);
		addMartyrStage.setTitle("Add a Martyr to the File");
		addMartyrStage.show();
	}


	public TextField getEnterInformation() {
		return enterInformation;
	}


	public Button getAddToFile() {
		return AddToFile;
	}
	
	
	
	
	
}
