package application;
	


import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			
			//set top
			Label topLabel = new Label("CS400 MyFirstJavaFX");
			root.setTop(topLabel);
			
			//set combo box
			ComboBox cb1 = new ComboBox();
			cb1.getItems().addAll("Click this1", "Click this2", "Click this3");
			root.setLeft(cb1);
			
			//set image
			Image image = new Image("Jhon.jpg");
			ImageView iv1 = new ImageView();
			iv1.setImage(image);
			root.setCenter(iv1);
			
			//set button
			Button bottomButton = new Button("Done");
			root.setBottom(bottomButton);
	        
			//set textfield
			Label label1 = new Label("Name:");
			TextField textField = new TextField ();
			HBox hb = new HBox();
			hb.getChildren().addAll(label1, textField);
			hb.setSpacing(10);
			root.setRight(hb);
			
			Scene scene = new Scene(root,1000,1000);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("this is my title");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
