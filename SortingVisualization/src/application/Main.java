package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import shapes.ArrayGUI;
import shapes.ElementContainer;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
    	BorderPane border = new BorderPane();
    	ArrayGUI array = new ArrayGUI();
	    array.getHBox().getStyleClass().add("array");
	    border.setTop(array.getHBox());

	    ElementContainer one = new ElementContainer(array, 0, 0, 200, "one", 15);
	    ElementContainer two = new ElementContainer(array, 0, 0, 200, "two", 15);
	    ElementContainer three = new ElementContainer(array, 0, 0, 200, "three", 15);
	    ElementContainer four = new ElementContainer(array, 0, 0, 200, "four", 15);
	    ElementContainer five = new ElementContainer(array, 0, 0, 200, "five", 15);
	    ElementContainer six = new ElementContainer(array, 0, 0, 200, "six", 15);
	    ElementContainer seven = new ElementContainer(array, 0, 0, 200, "seven", 15);
	    ElementContainer eight = new ElementContainer(array, 0, 0, 200, "eight", 15);
	    ElementContainer nine = new ElementContainer(array, 0, 0, 200, "nine", 15);

	    //array.getChildren().add(pane);
	    Scene scene = new Scene(border, 800, 600);
	    array.setScene(scene);
	    scene.getStylesheets().add("application/application.css");
	  	stage.setScene(scene);
	  	//stage.setMaximized(true);
	  	stage.show();
	  	array.swap(0, 1);
    }



    public static void main(String args[]){
        launch(args);
    }
}
