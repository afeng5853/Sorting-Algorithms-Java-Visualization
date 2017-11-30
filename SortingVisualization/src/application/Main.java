package application;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import shapes.ArrayGUI;
import shapes.ElementContainer;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
    	BorderPane border = new BorderPane();
    	HBox hbox = new HBox();

	    hbox.getStyleClass().add("array");
	    border.setTop(hbox);

	    ElementContainer one = new ElementContainer(hbox, 0, 0, 200, "1", 10);
	    ElementContainer two = new ElementContainer(hbox, 0, 0, 200, "2", 10);
	    ElementContainer three = new ElementContainer(hbox, 0, 0, 200, "3", 10);
	    ElementContainer four = new ElementContainer(hbox, 0, 0, 200, "4", 10);
	    ElementContainer five = new ElementContainer(hbox, 0, 0, 200, "5", 10);
	    ElementContainer six = new ElementContainer(hbox, 0, 0, 200, "6", 10);
	    ElementContainer seven = new ElementContainer(hbox, 0, 0, 200, "7", 10);
	    ElementContainer eight = new ElementContainer(hbox, 0, 0, 200, "8", 10);
	    ElementContainer nine = new ElementContainer(hbox, 0, 0, 200, "9", 10);

	    ArrayList<ElementContainer> eleC = new ArrayList<>();
	    eleC.add(one);
	    eleC.add(two);
	    eleC.add(three);
	    eleC.add(four);
	    eleC.add(five);
	    eleC.add(six);
	    eleC.add(seven);
	    eleC.add(eight);
	    eleC.add(nine);
	    ArrayGUI array = new ArrayGUI(eleC);

	    //array.getChildren().add(pane);
	    Scene scene = new Scene(border, 1280, 720);
	    array.setScene(scene);
	    scene.getStylesheets().add("application/application.css");
	  	stage.setScene(scene);
	  	stage.setResizable(false);
	  	//stage.setMaximized(true);
	  	stage.show();
	  	array.swap(4, 6);
	  	array.swap(3, 4);
	  	array.play();
    }



    public static void main(String args[]){
        launch(args);
    }
}
