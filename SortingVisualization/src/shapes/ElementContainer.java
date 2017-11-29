package shapes;

import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class ElementContainer {
	private DoubleProperty fontSize;
	private int x;
	private int y;
	private Label label;
	private int size;
	private Pane pane;
	private Pane elementContainerPanel;

	public ElementContainer(Pane pane, int x, int y, int size, String text, int fontSize) {
		// Double property to make font size relative to pane
		this.fontSize = new SimpleDoubleProperty(fontSize);

		// StackPane container properties
		StackPane eleContainer = new StackPane();
		eleContainer.getStyleClass().add("element-container");

	    // Label properties
	    Label label = new Label(text);

	    // Binding font size
	    this.fontSize.bind(pane.widthProperty().add(pane.heightProperty()).divide(50));
	    pane.styleProperty().bind(Bindings.concat("-fx-font-size: ", this.fontSize.asString(), ";"));

	    // add elements to app
	    eleContainer.getChildren().addAll(label);
	    pane.getChildren().add(eleContainer);

	    this.x = x;
	    this.y = y;
	    this.setLabel(label);
	    this.size = size;
	    this.pane = pane;
	    this.setElementContainerPanel(eleContainer);
	}


	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public Pane getPane() {
		return pane;
	}

	public void setPane(Pane pane) {
		this.pane = pane;
	}


	public Pane getElementContainerPanel() {
		return elementContainerPanel;
	}


	public void setElementContainerPanel(Pane elementContainerPanel) {
		this.elementContainerPanel = elementContainerPanel;
	}


	public Label getLabel() {
		return label;
	}


	public void setLabel(Label label) {
		this.label = label;
	}
}
