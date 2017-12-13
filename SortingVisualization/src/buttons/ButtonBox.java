package buttons;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import application.Main;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import shapes.ArrayGUI;

public class ButtonBox {
	private HBox parent;
	private List<Button> buttons;

	public ButtonBox(HBox parent) {
		this.parent = parent;
		this.buttons = new ArrayList<>();
	}

	public void disableButtons() {
		String[] disabled = new String[] {"Random Text", "Quick Sort", "Bubble Sort", "Insertion Sort", "Selection Sort"};
		for (Button button : buttons) {
			if (Arrays.asList(disabled).contains(button.getText())) {
				button.setDisable(true);
			}
		}
	}
	public void enableButtons() {
		System.out.println("test");
		for (Button button : buttons) {
			button.setDisable(false);
		}
	}
	
	public void addButton(ArrayGUI array, String name) {
		//testing only
		//Scene scene = Main.getScene();
		//Stage primStage = Main.getStage();
		Button btn = new Button(name);
		buttons.add(btn);
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
		} else if (name.equals("Random Text")) {
			btn.setOnAction(e -> { 
				array.resetTimelines();
				array.unmarkAll();
				array.resetText();;
			});
		}
		//else if(name.equals("Merge Sort")) {
		//	btn.setOnAction(Main.mergeSort(array));
		//}
		else if(name.equals("Quick Sort")) {
			btn.setOnAction((event) -> {
				//primStage.setScene(scene)
				array.resetTimelines();
				array.unmarkAll();
				long startTime = System.nanoTime();
				Main.quickSort(array, 0, array.size() - 1);
				long time = System.nanoTime() - startTime;
				Main.addTime("Quick Sort", time);
				array.play();
			});
		}
		else if(name.equals("Bubble Sort")) {
			btn.setOnAction((event) -> {
				array.resetTimelines();
				array.unmarkAll();
				long startTime = System.nanoTime();
				Main.bubbleSort(array);
				long time = System.nanoTime() - startTime;
				Main.addTime("Bubble Sort", time);
				array.play();
				//System.out.println(array.getTimelineDuration());			
			});
		}
		else if(name.equals("Insertion Sort")) {
			btn.setOnAction((event) -> {
				array.resetTimelines();
				array.unmarkAll();
				long startTime = System.nanoTime();
				Main.insertionSort(array);
				long time = System.nanoTime() - startTime;
				Main.addTime("Insertion Sort", time);
				array.play();
				//System.out.println(array.getTimelineDuration());
			});
		}
		else if(name.equals("Selection Sort")) {
			btn.setOnAction((event) -> {
				array.resetTimelines();
				array.unmarkAll();
				long startTime = System.nanoTime();
				Main.selectionSort(array);
				long time = System.nanoTime() - startTime;
				Main.addTime("Selection Sort", time);
				array.play();
				//System.out.println(array.getTimelineDuration());
			});
		}
		this.parent.getChildren().add(btn);
	}


}
