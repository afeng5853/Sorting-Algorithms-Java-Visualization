package barcharts;

import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import shapes.ArrayGUI;

/**
 * Wrapper Class for BarChart
 * @author Raymond Cheung
 *
 */
public class BarChartBox {
	private BarChart parent;

	public BarChartBox(BarChart parent) {
		this.parent = parent;
	}

	 /**
     * Add data point to bar chart
     * @param name label of data
     * @param time time of data
     */
	public void addData(String name, double runTime) {
		
       //Prepare XYChart.Series objects by setting data       
       XYChart.Series<String, Number> series = new XYChart.Series<>();
       series.getData().add(new XYChart.Data<>(name, runTime));
       this.parent.getData().add(series);
       
	}
}
