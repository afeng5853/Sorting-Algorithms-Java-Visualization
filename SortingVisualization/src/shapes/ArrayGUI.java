package shapes;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.collections.ObservableList;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.util.Duration;

/**
 * Class used to control the array visualization
 * @author alex
 *
 */
public class ArrayGUI {
	private Scene scene;
	private static final int X = 0;
	private static final int Y = 1;
	private ArrayList<Timeline> timelines;
	private ArrayList<ElementContainer> array;
	private AtomicInteger timelineIdx;
	private ArrayList<double[]> coordinates;
	private boolean firstSwap = true;
	private Timeline currentTimeline;
	private List<Label> marks;
	private double rate = 1;
	private HBox hbox;

	public ArrayGUI(ArrayList<ElementContainer> array, HBox hbox) {
		this.timelines = new ArrayList<Timeline>();
		this.array = array;
		this.coordinates = new ArrayList<>();
		this.timelineIdx = new AtomicInteger();
		this.marks = new ArrayList<>();
		this.hbox = hbox;
	}
	
	public ArrayList<Timeline> getTimelines() {
		return timelines;
	}

	
	public void setArray(ArrayList<ElementContainer> array) {
		 this.array = array;
		 }
	
	public ArrayList<ElementContainer> copyArray() {
		ArrayList<ElementContainer> copy = new ArrayList<>();
		for (ElementContainer ele: this.array) {
			copy.add(ele);
		}	    
		return copy;
	}
	
	/**
	 * initializes the coordinates of each elementcontainer
	 */
	private void initCoordinates() {
		for (int i = 0; i < array.size(); i++) {
			Node node = this.getArray().get(i).getEleContainerPanel();
			coordinates.add(this.getCoord(node));
		}
	}

	/**
	 * translates backend coordinates
	 * @param i index
	 * @param x
	 * @param y
	 */
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

	/**
	 * front end moving of a node and back end updating
	 * @param timeline
	 * @param idx
	 * @param node
	 * @param duration
	 */
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

	/**
	 * bring out animation and back end updating
	 * @param timeline
	 * @param idx
	 * @param duration
	 */
	private void bringOut(Timeline timeline, int idx, int duration) {
		double translateY = this.scene == null ? 100 : this.scene.getHeight() * 0.1;
		Node node = this.getArray().get(idx).getEleContainerPanel();
		translateCoord(idx, 0, translateY);
		move(timeline, idx, node, duration);
	}

	/**
	 * bring in animation and back end updating
	 * @param timeline
	 * @param idx
	 * @param duration
	 */
	private void bringIn(Timeline timeline, int idx, int duration) {
		double translateY = this.scene == null ? 100 : this.scene.getHeight() * 0.1;
		Node node = this.getArray().get(idx).getEleContainerPanel();
		//double translateX = coordinates.get(idx)[X] - getCoord(node)[X];
		translateCoord(idx, 0, -translateY);
		move(timeline, idx, node, duration);
	}

	/**
	 * get the coordinate of a node
	 * @param node
	 * @return
	 */
	private double[] getCoord(Node node) {
		Bounds bbInfo = node.localToScreen(node.getBoundsInLocal());
		double x = bbInfo.getMinX();
		double y = bbInfo.getMinY();
		return new double[] {x, y};
	}

	/**
	 * plays the animation to switch positions of two nodes
	 * @param timeline
	 * @param i idx of node 1
	 * @param j idx of node 2
	 * @param duration animation duration
	 */
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

	/**
	 * plays all of the animations stored
	 */
	public void play() {
		// if index is out of bounds on the left, do nothing
		if (timelineIdx.get() < 0) {
			return;
		}
		// if the index is at the end, just play instead of going to the next
		if (timelineIdx.get() == timelines.size() - 2) {
			Timeline currentT = timelines.get(timelineIdx.get());
			currentT.play();
			timelines.get(timelineIdx.incrementAndGet()).play();
			return;
		}
		Timeline currentT = timelines.get(timelineIdx.get());
		currentT.setRate(this.rate);
		if (this.rate > 0) {
			this.currentTimeline = currentT;
			currentT.play();
			timelines.get(timelineIdx.getAndIncrement()).setOnFinished(e -> {
				play();
			});
		} else {
			this.currentTimeline = currentT;
			currentT.play();
			timelines.get(timelineIdx.getAndDecrement()).setOnFinished(e -> {
				play();
			});
		}
	}

	public void pause() {
		this.currentTimeline.pause();
	}

	public void resume() {
		this.currentTimeline.play();
	}

	public void forward() {
		this.rate = 1;
	}

	public void reverse() {
		this.rate = -1;
	}

	public void faster() {
		this.currentTimeline.setRate(this.rate += 0.05);
	}
	
	public void skip() {
		this.rate = 10000;
	}

	public void slower() {
		this.currentTimeline.setRate(this.rate -= 0.05);
	}

	/**
	 * adds bringOut, switchPos, bringIn animations to swap two elements
	 * @param i idx of node 1
	 * @param j idx of node 2
	 */
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
		switchPos(timeline, i, j, 400);
		bringIn(timeline, i, 600);
		bringIn(timeline, j, 600);
		swapBackEnd(i, j);
		timelines.add(timeline);
	}

	public ElementContainer get(int i) {
		return this.array.get(i);
	}

	public int size() {
		return this.array.size();
	}

	/**
	 * swap back end data
	 * @param i idx of node 1
	 * @param j idx of node 2
	 */
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

	/**
	 * colors a node
	 * @param i idx
	 * @param color
	 * @return the label used to color
	 */
	public Label mark(int i, String color) {
		Label tempLabel = new Label();
		tempLabel.setStyle("-fx-background-color:" + color + ";" +
				"-fx-opacity: 0" + ";");
		this.marks.add(tempLabel);
		
		
		ElementContainer ec = this.get(i);
		ObservableList<Node> childNodes = ec.getEleContainerPanel().getChildren();
		childNodes.add(tempLabel);
		Label label = (Label) ec.getEleContainerPanel().getChildren().get(0);
		
		tempLabel.prefWidthProperty().bind(label.prefWidthProperty());
		Timeline tl = new Timeline(
	            new KeyFrame(new Duration(1), new KeyValue(tempLabel.opacityProperty(), 0.7)));
		timelines.add(tl);
		return tempLabel;
	}

	/**
	 * removes all labels (markers)
	 */
	public void unmarkAll() {
		for (int i = 0; i < this.array.size(); i++) {
			Label currentMark;
			ElementContainer ec = this.get(i);
			if (ec.getEleContainerPanel().getChildren().size() > 1) {
				currentMark = (Label) ec.getEleContainerPanel().getChildren().get(1);
				Timeline tl = new Timeline(
			            new KeyFrame(new Duration(1), new KeyValue(currentMark.opacityProperty(), 0)));
				tl.setOnFinished(e -> {
					ec.getEleContainerPanel().getChildren().remove(currentMark);
				});
				timelines.add(tl);
			}
		}
		this.marks = new ArrayList<>();
	}
	
	/**
	 * removes a label
	 * @param tempLabel
	 */
	public void unmark(Label tempLabel) {
		if (tempLabel == null) {
			return;
		}
		Timeline tl = new Timeline(
	            new KeyFrame(new Duration(300), new KeyValue(tempLabel.opacityProperty(), 0)));
		timelines.add(tl);
	}

	/**
	 * remove a label at index
	 * @param i
	 */
	public void unmark(int i) {
		ElementContainer ec = this.get(i);
		Label tempLabel = (Label) ec.getEleContainerPanel().getChildren().get(1);
		Timeline tl = new Timeline(
	            new KeyFrame(new Duration(300), new KeyValue(tempLabel.opacityProperty(), 0)));
		timelines.add(tl);
	}

	/**
	 * @return how long the animation is
	 */
	public double getTimelineDuration() {
		double sum = 0;
		for (Timeline t : this.timelines) {
			sum += t.getTotalDuration().toMillis();
		}
		return sum;
	}

	/**
	 * reinitializes timelines array
	 */
	public void resetTimelines() {
		timelineIdx = new AtomicInteger(0);
		if (currentTimeline != null)
			currentTimeline.stop();
		this.timelines = new ArrayList<>();
	}
	
	/**
	 * generates random text for elmenets
	 */
	public void resetText() {
		String[] text = new String[] {"estate", "defector", "actuality", "neurotic", "cholera", "feudal", "boardroom", "ghostly",
				"corporative", "shcheglovsk", "kyphosis", "biocycle", "nondelivery", "airship", "syleus", "zosimus", "northeastward", "aghast"};
		hbox.getChildren().clear();
		Random r = new Random();
		ElementContainer one = new ElementContainer(hbox, 0, 0, 200, text[r.nextInt(text.length)], 10);
	    ElementContainer two = new ElementContainer(hbox, 0, 0, 200, text[r.nextInt(text.length)], 10);
	    ElementContainer three = new ElementContainer(hbox, 0, 0, 200, text[r.nextInt(text.length)], 10);
	    ElementContainer four = new ElementContainer(hbox, 0, 0, 200, text[r.nextInt(text.length)], 10);
	    ElementContainer five = new ElementContainer(hbox, 0, 0, 200, text[r.nextInt(text.length)], 10);
	    ElementContainer six = new ElementContainer(hbox, 0, 0, 200, text[r.nextInt(text.length)], 10);
	    ElementContainer seven = new ElementContainer(hbox, 0, 0, 200, text[r.nextInt(text.length)], 10);
	    ElementContainer eight = new ElementContainer(hbox, 0, 0, 200, text[r.nextInt(text.length)], 10);
	    ElementContainer nine = new ElementContainer(hbox, 0, 0, 200, text[r.nextInt(text.length)], 10);

	    ArrayList<ElementContainer> eleC = new ArrayList<>();
	    eleC.add(one);
	    eleC.add(two);
	    eleC.add(three);
	    eleC.add(four);
	    eleC.add(five);
	    eleC.add(six);
	    eleC.add(seven);
	    eleC.add(eight);
	    eleC.add(nine);
	    this.array = eleC;
    	
    }
}
