package application;

import java.util.ArrayList;

import buttons.ButtonBox;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import shapes.ArrayGUI;
import shapes.ElementContainer;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {

    	HBox buttons = new HBox();
    	BorderPane border = new BorderPane();
    	HBox hbox = new HBox();

    	ButtonBox btnBox = new ButtonBox(buttons);
    	buttons.getStyleClass().add("buttons");

	    hbox.getStyleClass().add("array");
	    border.setTop(hbox);

	    ElementContainer one = new ElementContainer(hbox, 0, 0, 200, "estate", 10);
	    ElementContainer two = new ElementContainer(hbox, 0, 0, 200, "defector", 10);
	    ElementContainer three = new ElementContainer(hbox, 0, 0, 200, "actuality", 10);
	    ElementContainer four = new ElementContainer(hbox, 0, 0, 200, "neurotic", 10);
	    ElementContainer five = new ElementContainer(hbox, 0, 0, 200, "bananas", 10);
	    ElementContainer six = new ElementContainer(hbox, 0, 0, 200, "cholera", 10);
	    ElementContainer seven = new ElementContainer(hbox, 0, 0, 200, "feudal", 10);
	    ElementContainer eight = new ElementContainer(hbox, 0, 0, 200, "boardroom", 10);
	    ElementContainer nine = new ElementContainer(hbox, 0, 0, 200, "ghostly", 10);





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
	    btnBox.addButton(array, "Pause");
	    btnBox.addButton(array, "Resume");
	    btnBox.addButton(array, "Forward");
	    btnBox.addButton(array, "Reverse");
	    border.setBottom(buttons);

	    //array.getChildren().add(pane);
	    Scene scene = new Scene(border, 1280, 720);
	    array.setScene(scene);
	    scene.getStylesheets().add("application/application.css");
	  	stage.setScene(scene);
	  	stage.setResizable(false);
	  	//stage.setMaximized(true);
	  	stage.show();
	  	quickSort(array, 0, array.size());
	  	System.out.println(array.getTimelineDuration());
	  	array.play();
    }

    public static void bubbleSort(ArrayGUI array) {
    	boolean swapped = true;
	    while (swapped) {
	       swapped = false;
	       for(int i=1; i< array.size(); i++) {
	    	   Label marker1 = array.mark(i, "red");
	    	   Label marker2 = array.mark(i - 1, "red");
	           if(array.get(i).compareTo(array.get(i-1)) < 0) {
	        	   array.swap(i, i-1);
	               swapped = true;
	            }
	           array.unmark(marker1);
	           array.unmark(marker2);
	        }
	    }
    }

    private static int min(ArrayGUI array, int start, int end) {
		if (array.size() == 0) {
			return -1;
		}
		ElementContainer min = array.get(start);
		int minIdx = start;
		Label tempMinLabel = null;
		for (int i = start + 1; i < end; i++) {
			Label tempLabel = array.mark(i, "orange");
			if (array.get(i).compareTo(min) < 0) {
				min = array.get(i);
				minIdx = i;
				array.unmark(tempMinLabel);
				tempMinLabel = array.mark(minIdx, "yellow");
			}
			array.unmark(tempLabel);
		}
		array.unmark(tempMinLabel);
		return minIdx;
	}

    public static void selectionSort(ArrayGUI array) {
		for (int i = 0; i < array.size() - 1; i++) {
			Label tempLabel = array.mark(i, "red");
			int minIdx = min(array, i, array.size());
			array.swap(i, minIdx);
			array.unmark(tempLabel);
		}
	}

    public static void insertionSort(ArrayGUI array) {
		for (int i = 1; i < array.size(); i++) {
			Label tempLabel = array.mark(i, "red");
			int j = i-1;
			int k = i;
			while (j != -1 && array.get(k).compareTo(array.get(j)) < 0) {
				Label marker1 = array.mark(j, "red");
				Label marker2 = array.mark(k, "red");
				array.swap(k, j);
				array.unmark(marker1);
				array.unmark(marker2);
				j--;
				k--;
			}
			array.unmark(tempLabel);
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
		Label marker1 = array.mark(pivotIdx, "blue");
		for (int i = a + 1; i < b; i++) {
			Label tempLabel = array.mark(i, "red");
			if (array.get(i).compareTo(pivot) <= 0) {
				// track pivot index
				if (pivotIdx == j) {
					pivotIdx = i;
				}
				// swap pivot to latest index in the small partition
				array.swap(i, j);
				j++;
			}
			array.unmark(tempLabel);

		}
		// return pivot to correct location
		Label marker2 = array.mark(j, "red");
		array.swap(pivotIdx, j);
		array.unmark(marker2);
		array.unmark(marker1);
		return j;
	}

	public static void quickSort(ArrayGUI arr, int i, int j) {
		if (j - i <= 1) {
			return;
		}
		else {
			int pivotIdx = partition(arr, i, j);
			quickSort(arr, i, pivotIdx);
			quickSort(arr, pivotIdx + 1, j);
		}
	}


}
