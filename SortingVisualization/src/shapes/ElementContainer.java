package shapes;

import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class ElementContainer implements Comparable{
	private DoubleProperty fontSize;
	private int x;
	private int y;
	private Label label;
	private int size;
	private Pane pane;
	private Pane eleContainerPanel;
	private String text;

	public ElementContainer(HBox array, int x, int y, int size, String text, int fontSize) {
		// Double property to make font size relative to pane
		this.fontSize = new SimpleDoubleProperty(fontSize);
		this.pane = array;
		this.setText(text);

		// StackPane container properties
		StackPane eleContainer = new StackPane();
		eleContainer.getStyleClass().add("element-container");

	    // Label properties
	    Label label = new Label(text);
	    //label.setPrefHeight(size);
	    label.setPrefWidth(size);

	    // Binding font size
	    this.fontSize.bind(pane.widthProperty().add(pane.heightProperty()).divide(75));
	    pane.styleProperty().bind(Bindings.concat("-fx-font-size: ", this.fontSize.asString(), ";"));

	    // add elements to app
	    eleContainer.getChildren().addAll(label);
	    pane.getChildren().add(eleContainer);


	    this.x = x;
	    this.y = y;
	    this.setLabel(label);
	    this.size = size;
	    this.setEleContainerPanel(eleContainer);
	}

	@Override
	public String toString() {
		return this.getLabel().getText();
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


	public Pane getEleContainerPanel() {
		return eleContainerPanel;
	}


	public void setEleContainerPanel(Pane elementContainerPanel) {
		this.eleContainerPanel = elementContainerPanel;
	}


	public Label getLabel() {
		return label;
	}


	public void setLabel(Label label) {
		this.label = label;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Override
	public int compareTo(Object o) {
		if (this == o) return 0;

		if (!(o instanceof ElementContainer))
			throw new ClassCastException("An ElementContainer object expected.");

		return this.label.getText().compareTo(((ElementContainer) o).label.getText());
	}
}
