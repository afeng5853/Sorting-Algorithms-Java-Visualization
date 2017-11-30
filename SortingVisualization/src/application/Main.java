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

	    ElementContainer one = new ElementContainer(hbox, 0, 0, 200, "4", 10);
	    ElementContainer two = new ElementContainer(hbox, 0, 0, 200, "2", 10);
	    ElementContainer three = new ElementContainer(hbox, 0, 0, 200, "3", 10);
	    ElementContainer four = new ElementContainer(hbox, 0, 0, 200, "3", 10);
	    ElementContainer five = new ElementContainer(hbox, 0, 0, 200, "5", 10);
	    ElementContainer six = new ElementContainer(hbox, 0, 0, 200, "8", 10);
	    ElementContainer seven = new ElementContainer(hbox, 0, 0, 200, "2", 10);
	    ElementContainer eight = new ElementContainer(hbox, 0, 0, 200, "1", 10);
	    ElementContainer nine = new ElementContainer(hbox, 0, 0, 200, "7", 10);

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

	  	partition(array, 0, eleC.size());
	  	array.play();
    }

    public static void bubbleSort(ArrayGUI array) {
    	boolean swapped = true;
	    while (swapped) {
	       swapped = false;
	       for(int i=1; i< array.size(); i++) {
	           if(array.get(i).compareTo(array.get(i-1)) < 0) {
	        	   array.swap(i, i-1);
	               swapped = true;
	            }
	        }
	    }
    }

    private static int min(ArrayGUI array, int start, int end) {
		if (array.size() == 0) {
			return -1;
		}
		ElementContainer min = array.get(start);
		int minIdx = start;
		for (int i = start + 1; i < end; i++) {
			if (array.get(i).compareTo(min) < 0) {
				min = array.get(i);
				minIdx = i;
			}
		}
		return minIdx;
	}

    public static void selectionSort(ArrayGUI array) {
		for (int i = 0; i < array.size(); i++) {
			int minIdx = min(array, i, array.size());
			array.swap(i, minIdx);
		}
	}

    public static void insertionSort(ArrayGUI array) {
		for (int i = 1; i < array.size(); i++) {
			int j = i-1;
			int k = i;
			while (j != -1 && array.get(k).compareTo(array.get(j)) < 0) {
				array.swap(k, j);
				j--;
				k--;
			}
		}
	}

    public static void main(String args[]){
        launch(args);
    }
    
    public static int partition(ArrayGUI array, int a, int b) {
		// if list is empty
		if (array.size() == 0) {
			return -1;
		}
		ElementContainer pivot = array.get(a); // set pivot to first item
		int j = a; // current index to swap for items less than the pivot
		int pivotIdx = a;
		for (int i = a + 1; i < b; i++) {
			if (array.get(i).compareTo(pivot) <= 0) {
				// track pivot index 
				if (pivotIdx == j) {
					pivotIdx = i;
				}
				// swap pivot to latest index in the small partition
				array.swap(i, j);
				j++;
			}
			
		}
		// return pivot to correct location
		array.swap(pivotIdx, j);
		return j;
	}

}
