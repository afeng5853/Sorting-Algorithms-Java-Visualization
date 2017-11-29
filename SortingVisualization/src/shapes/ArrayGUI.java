package shapes;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.util.Duration;

public class ArrayGUI {
	private HBox hBox = new HBox();
	private Scene scene;

	public ArrayGUI() {
	}

	public Scene getScene() {
		return scene;
	}

	public void setScene(Scene scene) {
		this.scene = scene;
	}

	public HBox getHBox() {
		return hBox;
	}

	public void setHBox(HBox hBox) {
		this.hBox = hBox;
	}

	public ObservableList<Node> getChildren() {
		return hBox.getChildren();
	}

	private void bringOut(int i) {
		double translation = this.scene == null ? 100 : this.scene.getHeight() * 0.1;
		Node node = this.getChildren().get(i);
		Timeline timeline = new Timeline();
		timeline.getKeyFrames().add(
				new KeyFrame(new Duration(1000),
						new KeyValue(node.translateYProperty(), translation))
		);
		timeline.play();
	}

	// move these helper functions
	private double[] calculateCenter(double x1, double y1, double x2, double y2) {
		double centerX = (x1 + x2) / 2;
		double centerY = (y1 + y2) / 2;
		return new double[] {centerX, centerY};
	}

	private double[] calculateCenter(double[] vec1, double[] vec2) {
		double centerX = (vec1[0] + vec2[0]) / 2;
		double centerY = (vec1[1] + vec2[1]) / 2;
		return new double[] {centerX, centerY};
	}

	private void switchPos(int i, int j) {
		Node node1 = this.getChildren().get(i);
		Node node2 = this.getChildren().get(j);
		System.out.println(node1.localToScreen(node1.getBoundsInLocal()));
		System.out.println(node2.localToScreen(node2.getBoundsInLocal()));
	}

	public void swap(int i, int j) {
		bringOut(i);
		bringOut(j);
		switchPos(i, j);
	}
}
