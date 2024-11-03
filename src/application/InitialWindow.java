package application;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class InitialWindow {
	Stage initialwindowStage;
	Button buttonAddMartyr ;  //define the buttons of this window
	Button buttonMemoryTest;
	VBox vbox ;
	StackPane stack ;
	Scene sceneFirstWindow ;
	 //define the VBox  and add buttons to it
	public InitialWindow(){
		initialwindowStage=new Stage();
		buttonAddMartyr= new Button("Create Martyr List Window");
		buttonMemoryTest = new Button("Memory Test Window");
		vbox = new VBox(5);
		stack = new StackPane();
		sceneFirstWindow = new Scene(stack, 400, 400);
		
		vbox.getChildren().addAll(buttonAddMartyr, buttonMemoryTest);
		vbox.setAlignment(Pos.CENTER);
		stack.getChildren().add(vbox);
		initialwindowStage.setScene(sceneFirstWindow);
		initialwindowStage.setTitle("The war on Gaza");
		initialwindowStage.show();
	}
	public Button getButtonAddMartyr() {
		return buttonAddMartyr;
	}
	public Button getButtonMemoryTest() {
		return buttonMemoryTest;
	}
	
	
	
	
}
