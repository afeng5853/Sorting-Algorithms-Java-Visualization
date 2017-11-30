package buttons;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import application.Main;

public class ButtonBox {
	private HBox parent;

	public ButtonBox(HBox parent) {
		this.parent = parent;
	}

	public void addButton(String name, String sortType) {
		Button btn = new Button(name);
		btn.setOnAction(new EventHandler<ActionEvent>(){

	        @Override
	        public void handle(ActionEvent event) {
	            //Main.quickSort(, 0, );
	        }
	    });

	    ;
		this.parent.getChildren().add(btn);
	}
	
	
}
