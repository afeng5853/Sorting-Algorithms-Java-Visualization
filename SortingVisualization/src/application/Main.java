package application;

import java.util.ArrayList;

import barcharts.BarChartBox;
import buttons.ButtonBox;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import shapes.ArrayGUI;
import shapes.ElementContainer;

/**
 * @author Alex Feng -> Algorithm Visualization, Raymond Cheung -> Chart
 */
public class Main extends Application {
	private static BarChartBox chart;

    @Override
    public void start(Stage stage) throws Exception {
    	HBox buttons = new HBox();
    	BorderPane border = new BorderPane();
    	HBox hbox = new HBox();

    	ButtonBox btnBox = new ButtonBox(buttons);
    	buttons.getStyleClass().add("buttons");

	    hbox.getStyleClass().add("array");
	    border.setTop(hbox);
	    
	    // Starting elements in the array
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
	    
	    ArrayGUI array = new ArrayGUI(eleC, hbox);

	    // Initialize buttons
	    btnBox.addButton(array, "Pause");
	    btnBox.addButton(array, "Resume");
	    btnBox.addButton(array, "Forward Speed 1x");
	    btnBox.addButton(array, "Reverse Speed -1x");
	    btnBox.addButton(array, "Faster");
	    btnBox.addButton(array, "Slower");
	    btnBox.addButton(array, "Skip");
	    btnBox.addButton(array, "Random Text");
	    btnBox.addButton(array, "Quick Sort");
	    btnBox.addButton(array, "Selection Sort");
	    btnBox.addButton(array, "Bubble Sort");
	    btnBox.addButton(array, "Insertion Sort");
	    border.setBottom(buttons);
	    
	    //bar chart
	    
	    CategoryAxis xAxis = new CategoryAxis();
	    NumberAxis yAxis = new NumberAxis();
	    xAxis.setLabel("Sort Type");       
	    yAxis.setLabel("Time (ns)");
	    BarChart bc = new BarChart(xAxis, yAxis);
	    bc.setTitle("Run Time");
	    chart = new BarChartBox(bc);
      

	    // javafx initialize scene
	    Scene scene = new Scene(border, 1280, 720);
	    array.setScene(scene);
	    scene.getStylesheets().add("application/application.css");
	  	stage.setScene(scene);
	  	stage.setResizable(false);
	  	stage.show();
	  	stage.setTitle("Sorting Visualization");
	    border.setCenter(bc);
    }
    
    /**
     * Add data point to bar chart
     * @param name label of data
     * @param time time of data
     */
    public static void addTime(String name, long time) {
    	chart.addData(name, time);
    }
    
    /**
     * bubblesort implementation for ArrayGUI
     * @param array
     */
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

    /**
     * finds the minimum in the array for ArrayGUI
     * @param array
     * @param start
     * @param end
     * @return
     */
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

    /**
     * selection sort implementation for ArrayGUI
     * @param array
     */
    public static void selectionSort(ArrayGUI array) {
		for (int i = 0; i < array.size() - 1; i++) {
			Label tempLabel = array.mark(i, "red");
			int minIdx = min(array, i, array.size());
			array.swap(i, minIdx);
			array.unmark(tempLabel);
		}
	}

    /**
     * insertion sort implementation for ArrayGUI
     * @param array
     */
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

    /**
     * launch javafx app
     * @param args
     */
    public static void main(String args[]){
        launch(args);
    }

    /**
     * partition implementation for ArrayGUI
     * @param array
     * @param a start pos
     * @param b end pos
     * @return pivot idx
     */
    public static int partition(ArrayGUI array, int a, int b) {
		ElementContainer pivot = array.get(b); // set pivot to last item
		int i = a - 1;
		Label marker1 = array.mark(b, "blue");
		for (int j = a; j < b; j++) {
			Label tempLabel = array.mark(j, "red");
			if (array.get(j).compareTo(pivot) <= 0) {
				i++;
				array.swap(i, j);
			}
			array.unmark(tempLabel);
		}
		// return pivot to correct location
		Label marker2 = array.mark(i + 1, "red");
		array.swap(b, i + 1);
		array.unmark(marker2);
		array.unmark(marker1);
		return i+1;
	}

    /**
     * quick sort implementation for ArrayGUI
     * @param arr
     * @param i start pos
     * @param j end pos
     */
	public static void quickSort(ArrayGUI arr, int i, int j) {
		if (i >= j) {
			return;
		}
		else {
			int pivotIdx = partition(arr, i, j);
			quickSort(arr, i, pivotIdx - 1);
			quickSort(arr, pivotIdx + 1, j);
		}
	}

}
