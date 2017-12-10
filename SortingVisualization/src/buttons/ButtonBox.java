package buttons;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import shapes.ArrayGUI;
import shapes.ElementContainer;
import java.util.ArrayList;
import javafx.stage.Stage;
import javafx.scene.Scene;

import application.Main;

public class ButtonBox {
	private HBox parent;
	private ArrayGUI copy;

	public ButtonBox(HBox parent) {
		this.parent = parent;
	}

	public void addButton(ArrayGUI array, String name) {
		//testing only
		//Scene scene = Main.getScene();
		//Stage primStage = Main.getStage();
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
				//primStage.setScene(scene);
				copy = Main.reset(array);
				copy.resetTimelines();
				copy.play();
				Main.quickSort(copy, 0, copy.size());
				System.out.println(copy.getTimelineDuration());
			});
		}
		else if(name.equals("Bubble Sort")) {
			btn.setOnAction((event) -> {
				copy = Main.reset(array);
				copy.resetTimelines();
				copy.play();
				Main.bubbleSort(copy);
				System.out.println(copy.getTimelineDuration());			
			});
		}
		else if(name.equals("Insertion Sort")) {
			btn.setOnAction((event) -> {
				copy = Main.reset(array);
				copy.resetTimelines();
				copy.play();
				Main.insertionSort(copy);
				System.out.println(copy.getTimelineDuration());
			});
		}
		else if(name.equals("Selection Sort")) {
			btn.setOnAction((event) -> {
				copy = Main.reset(array);
				copy.resetTimelines();
				copy.play();
				Main.selectionSort(copy);
				System.out.println(copy.getTimelineDuration());
			});
		}
		this.parent.getChildren().add(btn);
	}


}
