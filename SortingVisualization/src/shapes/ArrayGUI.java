package shapes;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.collections.ObservableList;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class ArrayGUI {
	private Scene scene;
	private static final int X = 0;
	private static final int Y = 1;
	private ArrayList<Timeline> timelines;
	private ArrayList<ElementContainer> array;
	private AtomicInteger timelineIdx;
	private ArrayList<double[]> coordinates;
	private boolean firstSwap = true;

	public ArrayGUI(ArrayList<ElementContainer> array) {
		this.timelines = new ArrayList<Timeline>();
		this.array = array;
		this.coordinates = new ArrayList<>();
		this.timelineIdx = new AtomicInteger();

	}

	private void initCoordinates() {
		for (int i = 0; i < array.size(); i++) {
			Node node = this.getArray().get(i).getEleContainerPanel();
			coordinates.add(this.getCoord(node));
		}
	}

	private void printCoords() {
		for (double[] coord : coordinates) {
			System.out.print("[" + coord[X] + " " + coord[Y] + "] ");
		}
	}

	private void translateCoord(int i, double x, double y) {
		double[] currentCoord = coordinates.get(i);
		currentCoord[X] += x;
		currentCoord[Y] += y;
	}


	public Scene getScene() {
		return scene;
	}

	public void setScene(Scene scene) {
		this.scene = scene;
	}

	private void move(Timeline timeline, int idx, Node node, int duration) {
		double[] origC = getCoord(node);
		double[] trueC = coordinates.get(idx);
		double correctionX = trueC[X] - origC[X];
		double correctionY = trueC[Y] - origC[Y];
		timeline.getKeyFrames().add(
				new KeyFrame(new Duration(duration),
						new KeyValue(node.translateXProperty(), correctionX),
						new KeyValue(node.translateYProperty(), correctionY)
				)
		);
	}


	private void moveToCoord(Timeline timeline, int i, int j, Node node, int duration) {

	}

	private void bringOut(Timeline timeline, int idx, int duration) {
		double translateY = this.scene == null ? 100 : this.scene.getHeight() * 0.1;
		Node node = this.getArray().get(idx).getEleContainerPanel();
		//double translateX = coordinates.get(idx)[X] - getCoord(node)[X];
		translateCoord(idx, 0, translateY);
		move(timeline, idx, node, duration);
	}

	private void bringIn(Timeline timeline, int idx, int duration) {
		double translateY = this.scene == null ? 100 : this.scene.getHeight() * 0.1;
		Node node = this.getArray().get(idx).getEleContainerPanel();
		//double translateX = coordinates.get(idx)[X] - getCoord(node)[X];
		translateCoord(idx, 0, -translateY);
		move(timeline, idx, node, duration);
	}

	private double[] getCoord(Node node) {
		Bounds bbInfo = node.localToScreen(node.getBoundsInLocal());
		double x = bbInfo.getMinX();
		double y = bbInfo.getMinY();
		return new double[] {x, y};
	}

	private void switchPos(Timeline timeline, int i, int j, int duration) {
		Node node1 = this.getArray().get(i).getEleContainerPanel();
		Node node2 = this.getArray().get(j).getEleContainerPanel();
		double[] thisCoord = coordinates.get(i);
		double[] otherCoord = coordinates.get(j);
		double translateX = otherCoord[X] - thisCoord[X];
		double translateY = otherCoord[Y] - thisCoord[Y];
		translateCoord(i, translateX, translateY);
		translateCoord(j, -translateX, translateY);
		move(timeline, i, node1, duration);
		move(timeline, j, node2, duration);
	}

	@Override
	public String toString() {
		StringBuilder build = new StringBuilder();
		for (ElementContainer e : this.array) {
			build.append(e + " ");
		}
		return build.toString();
	}

	public void play() {
		if (timelineIdx.get() >= timelines.size()) {
			return;
		}
		Timeline currentT = timelines.get(timelineIdx.get());
		currentT.play();
		timelines.get(timelineIdx.getAndIncrement()).setOnFinished(e -> {
			play();
		});
	}

	public void swap(int i, int j) {
		if (firstSwap) {
			initCoordinates();
			firstSwap = false;
		}
		if (i == j) {
			return;
		}
		Timeline timeline = new Timeline();
		bringOut(timeline, i, 200);
		bringOut(timeline, j, 200);
		printCoords();
		switchPos(timeline, i, j, 400);
		printCoords();
		bringIn(timeline, i, 600);
		bringIn(timeline, j, 600);
		swapBackEnd(i, j);
		/*
		Pane node1 = this.getArray().get(i).getEleContainerPanel();
		Pane node2 = this.getArray().get(j).getEleContainerPanel();
		timeline.getKeyFrames().add(
				new KeyFrame(new Duration(2000),

						new KeyValue(node2.layoutXProperty(), node2.getLayoutX() + node2.getTranslateX()),
						new KeyValue(node2.layoutYProperty(), node2.getLayoutY() + node2.getTranslateY())
				)
		);*/
		timelines.add(timeline);
		//this.timeline.setOnFinished(e -> reset());
	}

	public ElementContainer get(int i) {
		return this.array.get(i);
	}
	
	public int size() {
		return this.array.size();
	}
	
	private void swapBackEnd(int i, int j) {
		ElementContainer temp = array.get(i);
		array.set(i, array.get(j));
		array.set(j, temp);

		double[] temp2 = coordinates.get(i);
		coordinates.set(i, coordinates.get(j));
		coordinates.set(j, temp2);
	}

	public ArrayList<ElementContainer> getArray() {
		return array;
	}

}
