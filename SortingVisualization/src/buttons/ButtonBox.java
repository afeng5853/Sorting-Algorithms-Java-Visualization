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
		}

		this.parent.getChildren().add(btn);
	}


}
