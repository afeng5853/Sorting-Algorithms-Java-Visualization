package buttons;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import shapes.ArrayGUI;
import application.Main;

public class ButtonBox {
	private HBox parent;

	public ButtonBox(HBox parent) {
		this.parent = parent;
	}

	public void addButton(ArrayGUI array, String name) {
		//testing only
		Button btn = new Button(name);
		if (name.equals("Pause")) {
			btn.setOnAction(e -> array.pause());
		} else if (name.equals("Resume")) {
			btn.setOnAction(e -> array.resume());
		} else if (name.equals("Forward")) {
			btn.setOnAction(e -> array.forward());
		} else if (name.equals("Reverse")) {
			btn.setOnAction(e -> array.reverse());
		} else if (name.equals("Slower")) {
			btn.setOnAction(e -> array.slower());
		} else if (name.equals("Faster")) {
			btn.setOnAction(e -> array.faster());
		}
		//else if(name.equals("Merge Sort")) {
		//	btn.setOnAction(Main.mergeSort(array));
		//}
		else if(name.equals("Quick Sort")) {
			btn.setOnAction((event) -> {
			  	array.resetTimelines();
			  	array.play();
				Main.quickSort(array, 0, array.size());
			  	System.out.println(array.getTimelineDuration());
			});
		}
		else if(name.equals("Bubble Sort")) {
			btn.setOnAction((event) -> {
				Main.bubbleSort(array);
			});
		}
		else if(name.equals("Insertion Sort")) {
			btn.setOnAction((event) -> {
				Main.insertionSort(array);
			});
		}
		else if(name.equals("Selection Sort")) {
			btn.setOnAction((event) -> {
				Main.selectionSort(array);
			});
		}
		this.parent.getChildren().add(btn);
	}


}
