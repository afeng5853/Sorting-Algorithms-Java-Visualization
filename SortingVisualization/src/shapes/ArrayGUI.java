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
import javafx.util.Duration;

public class ArrayGUI {
	private HBox hBox = new HBox();
	private Scene scene;
	private static final int X = 0;
	private static final int Y = 1;
	private ArrayList<Timeline> timelines;
	private ArrayList<ElementContainer> array;
	private AtomicInteger timelineIdx;

	public ArrayGUI() {
		this.timelines = new ArrayList<Timeline>();
		this.array = new ArrayList<>();
		this.timelineIdx = new AtomicInteger();
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

	private void incrementTime(int time) {
		this.time += time;
	}

	private void move(Timeline timeline, Node node, double x, double y, int duration) {
		timeline.getKeyFrames().add(
				new KeyFrame(new Duration(duration),
						new KeyValue(node.translateXProperty(), x),
						new KeyValue(node.translateYProperty(), y))
		);
	}

	private void move(Timeline timeline, Node node, double[] coord, int duration) {
		timeline.getKeyFrames().add(
				new KeyFrame(new Duration(duration),
						new KeyValue(node.translateXProperty(), coord[X]),
						new KeyValue(node.translateYProperty(), coord[Y]))
		);
	}

	private void moveToCoord(Timeline timeline,Node node, double x, double y, int duration) {
		double[] coord = getCoord(node);
		double translateX = x - coord[X];
		double translateY = y - coord[Y];
		move(timeline, node, translateX, translateY, duration);
	}

	private void moveToCoord(Timeline timeline, Node node, double[] coord, int duration) {
		double[] thisCoord = getCoord(node);
		double translateX = coord[X] - thisCoord[X];
		double translateY = coord[Y] - thisCoord[Y];
		move(timeline, node, translateX, translateY, duration);
	}

	private void bringOut(Timeline timeline, int i, int duration) {
		double translation = this.scene == null ? 100 : this.scene.getHeight() * 0.1;
		Node node = this.getArray().get(i).getEleContainerPanel();
		move(timeline, node, 0, translation, duration);
	}

	// move these helper functions
	private double[] calculateCenter(double x1, double y1, double x2, double y2) {
		double centerX = (x1 + x2) / 2;
		double centerY = (y1 + y2) / 2;
		return new double[] {centerX, centerY};
	}

	private double[] calculateCenter(double[] vec1, double[] vec2) {
		double centerX = (vec1[X] + vec2[X]) / 2;
		double centerY = (vec1[Y] + vec2[Y]) / 2;
		return new double[] {centerX, centerY};
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
		double[] node1Coord = getCoord(node1);
		double[] node2Coord = getCoord(node2);
		moveToCoord(timeline, node1, node2Coord, duration);
		moveToCoord(timeline, node2, node1Coord, duration);
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
		System.out.println(timelineIdx.get());
		Timeline currentT = timelines.get(timelineIdx.get());
		currentT.play();
		timelines.get(timelineIdx.getAndIncrement()).setOnFinished(e -> play());
	}

	public void swap(int i, int j) {
		Timeline timeline = new Timeline();
		bringOut(timeline, i, 1000);
		bringOut(timeline, j, 1000);
		switchPos(timeline, i, j, 2000);
		swapBackEnd(i, j);
		timelines.add(timeline);
		//this.timeline.setOnFinished(e -> reset());
	}

	private void swapBackEnd(int i, int j) {
		ElementContainer temp = array.get(i);
		array.set(i, array.get(j));
		array.set(j, temp);
	}

	public ArrayList<ElementContainer> getArray() {
		return array;
	}

}
