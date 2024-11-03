package application;

import java.io.*;

import javafx.application.Application;
import javafx.collections.*;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.util.*;

public class MemoryTest extends Application {
	ArrayList<Martyr> nameList = new ArrayList<>();
	Label[] nameLabels;
	TextField first = new TextField();
	TextField second = new TextField();
	Button submit = new Button("Submit");
	Button clear = new Button("Clear");
	Label response = new Label("");

	@Override
	public void start(Stage windowTestStage) throws IOException {
         //define the files and data stream to read and write from/to file
		FileOutputStream outputStream = new FileOutputStream("martyrs.dat", true);
		DataOutputStream dataoutstream = new DataOutputStream(outputStream);
		FileInputStream inputStream = new FileInputStream("martyrs.dat");
		DataInputStream datainstream = new DataInputStream(inputStream);
		//read from the file and store in the array
		try {
			while (datainstream.available() > 0) {
				String[] line = datainstream.readUTF().split(":");
				if (line.length == 2) {
					Martyr martyr = new Martyr(line[0], line[1]);
					nameList.add(martyr);
				}
			}
		} catch (IOException e2) {

		}

		// The initial window
		InitialWindow initialwindow = new InitialWindow();

		
		
		// The add martyr window
		initialwindow.getButtonAddMartyr().setOnAction(e1 -> { // the add martyr window appear when click this button
			AddMartyrWindow addMartyrWindow = new AddMartyrWindow();
             //click to this button to add martyr to file
			addMartyrWindow.getAddToFile().setOnAction(e2 -> {
				Martyr martyr;
				//information of martyr from textField
				String[] information = addMartyrWindow.getEnterInformation().getText().split(" ");
				if (information.length == 2) { //if the information = 2
					String[] date = information[1].split("/");  //take the date
					if (date.length == 3) {
						try {  //check the date

							int day = Integer.parseInt(date[0]);
							int month = Integer.parseInt(date[1]);
							int year = Integer.parseInt(date[2]);
							martyr = new Martyr(information[0], information[1]);
							if (!inList(martyr.getMartyrName())) {

								try { //write a martyr to the file
									dataoutstream.writeUTF(information[0] + ":" + information[1]);
								} catch (IOException e) {
									// TODO Auto-generated catch block
									System.out.println(e.toString());
								}

								nameList.add(martyr); //add the martyr to array
							}
						} catch (NumberFormatException e) {
							System.out.println(e.toString());
						}

					}
				}

			});

		});

		
		//Test memory window
		initialwindow.getButtonMemoryTest().setOnAction(e -> {
			//define all panes and labels and textField and buttons which we use
			VBox allPanes = new VBox(15);
			Label label1 = new Label("Test your memory");
			Label label2 = new Label("Hey,my frind!Test your Memory to see if you remember who was martyred before");
			Label label3 = new Label("Pick two martyrs names from the following list, enter them in the boxes in the correct order(date of death)and then press ");
			Label label4 = new Label("the Sumbit button");
			VBox vboxLabels = new VBox(20);
			HBox towMartyrs = new HBox(20);
			ComboBox<String> selectColor = new ComboBox<>();      ////////////
			HBox submitAndclear = new HBox(10);
			StackPane responseBox = new StackPane();
			Scene windowTestScene = new Scene(allPanes, 800, 800);
			FlowPane flowpane = new FlowPane();
			 
			//design the window
			allPanes.setAlignment(Pos.CENTER);

			
			label1.setFont(Font.font(35));
			label2.setFont(Font.font(15));			
			label3.setFont(Font.font(13));			
			label4.setFont(Font.font(13));
			

			vboxLabels.getChildren().addAll(label1, label2, label3, label4);
			vboxLabels.setAlignment(Pos.CENTER);
			vboxLabels.setPadding(new Insets(15));
			
			
			flowpane.setPadding(new Insets(5, 35, 5, 35));
			flowpane.setVgap(1);
			flowpane.setHgap(20);
            flowpane.setAlignment(Pos.CENTER);
            
            //but the martyrs names in the array Label
			nameLabels = new Label[nameList.size()];
			for (int i = 0; i < nameList.size(); i++) {
				nameLabels[i] = new Label(nameList.get(i).getMartyrName());
				nameLabels[i].setPrefSize(80, 25);
				flowpane.getChildren().add(nameLabels[i]);
			}
			
			towMartyrs.setAlignment(Pos.CENTER);
			towMartyrs.getChildren().addAll(first, new Label("martyred before:"), second);
			
			selectColor.setPromptText("Select Color");         /////////////////
			String[] colors = { "ALICEBLUE", "PINK", "SLATEGRAY", "MEDIUMPURPLE" }; ////////////
			ObservableList list = FXCollections.observableArrayList(colors);  /////////////
			selectColor.getItems().addAll(list);       //////////////
			//this comboBox to change color of window
			selectColor.setOnAction(e1 -> {                  ///////////
				if (selectColor.getSelectionModel().getSelectedIndex() == 0) {
					allPanes.setStyle("-fx-background-color: ALICEBLUE");
				} else if (selectColor.getSelectionModel().getSelectedIndex() == 1) {
					allPanes.setStyle("-fx-background-color: PINK");
				} else if (selectColor.getSelectionModel().getSelectedIndex() == 2) {
					allPanes.setStyle("-fx-background-color: SLATEGRAY");
				} else {
					allPanes.setStyle("-fx-background-color: MEDIUMPURPLE");
				}

			});
			
			submitAndclear.setAlignment(Pos.CENTER);
			submitAndclear.setPadding(new Insets(10, 50, 10, 150));
			submitAndclear.getChildren().addAll(submit, clear, selectColor);
			
			responseBox.getChildren().add(response);
			
			//this button to compare the martyrs
			submit.setOnAction(e2 -> {
				if (first.getText().equals("") | second.getText().equals("")) {
					response.setText("Enter names in both boxes. Then press Submit");

				} else if (inList(first.getText()) == false & inList(second.getText()) == false) {
					response.setText("Neither entry is in the name list");
				} else if (inList(first.getText()) == false & inList(second.getText()) == true) {
					response.setText("First entry not in name list – check spelling");
				} else if (inList(first.getText()) == true & inList(second.getText()) == false) {
					response.setText("Second entry not in name list – check spelling");
				} else if (inList(first.getText()) == true & inList(second.getText()) == true
						& first.getText().equals(second.getText())) {
					response.setText(" You entered the same names. Try again");
				} else {
					try {
						String[] arrayDate1String = returnMartyr(first.getText()).getDateOfMartyrdom().split("/");
						int[] arrayDate1Int = { Integer.parseInt(arrayDate1String[0]),
								Integer.parseInt(arrayDate1String[1]), Integer.parseInt(arrayDate1String[2]) };
						String[] arrayDate2String = returnMartyr(second.getText()).getDateOfMartyrdom().split("/");
						int[] arrayDate2Int = { Integer.parseInt(arrayDate2String[0]),
								Integer.parseInt(arrayDate2String[1]), Integer.parseInt(arrayDate2String[2]) };
						//make an objects Date to compare
						Date date1 = new Date(arrayDate1Int[2] - 1900, arrayDate1Int[1] - 1, arrayDate1Int[0]);  
						Date date2 = new Date(arrayDate2Int[2] - 1900, arrayDate2Int[1] - 1, arrayDate2Int[0]);
						if (date1.compareTo(date2) == -1) {
							response.setText("You are correct!"); 
						} else {
							response.setText("Wrong. Try again");
						}
					} catch (NumberFormatException ex) {
						System.out.println(ex.getMessage());
					}
				}
			});

			clear.setOnAction(e3 -> {
				first.setText("");
				second.setText("");
			});

			allPanes.getChildren().addAll(vboxLabels, flowpane, towMartyrs, submitAndclear, responseBox);
			
			windowTestStage.setScene(windowTestScene);
			windowTestStage.setTitle("Memory Test");
			windowTestStage.show();

		});

	}

	private boolean inList(String name) { //this method to check if martyr already exist
		boolean isExist = false;
		for (int i = 0; i < nameList.size(); i++) {
			if (name.equals(nameList.get(i).getMartyrName())) {
				isExist = true;
			}
		}
		return isExist;
	}

	private Martyr returnMartyr(String name) {  //this method to return the martyr according to name
		for (int i = 0; i < nameList.size(); i++) {
			if (nameList.get(i).getMartyrName().equals(name)) {
				return nameList.get(i);
			}
		}
		return null;
	}

	public static void main(String[] args) {
		launch(args);
	}
}
