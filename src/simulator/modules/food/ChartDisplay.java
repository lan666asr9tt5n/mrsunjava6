/**
 * 
 */
package simulator.modules.food;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.DefaultXYDataset;

import simulator.modules.food.FoodModule.FoodData;

/**
 * 
 * Class for displaying the JFreeChart. It observes what {@link #foodModule} is
 * doing. Updates are triggered in
 * {@link FoodModule#calculateOverallGlucose(int)}. Information is exchanged via
 * {@link FoodData} objects.
 * 
 * @author rc
 * 
 */
public class ChartDisplay extends JFrame implements Observer {

	private static final long serialVersionUID = 1L;

	private FoodModule foodModule;

	/*
	 * Fields for holding the data for displaying the chart.
	 */
	private DefaultXYDataset xyDataset = new DefaultXYDataset();
	private double[][] series = new double[2][100];

	public ChartDisplay(FoodModule foodModule2) {
		super("Chart Display");
		foodModule = foodModule2;
		foodModule.addObserver(this);

		for (int i = 1; i < 100; i++) {
			series[0][i] = ((double) i) / 10;
			series[1][i] = .0;
		}
		xyDataset.addSeries("Food", series);

		JFreeChart lineChart = ChartFactory.createXYLineChart(
				"Blood glucose level over time", "Time", "Glucose Level",
				xyDataset, PlotOrientation.VERTICAL, true, true, false);
		ChartPanel chartPanel = new ChartPanel(lineChart);

		this.add(chartPanel);
		this.setSize(800, 400);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setVisible(true);
	}

	/**
	 * Update our chart if anything changed in the subject (i.e.
	 * {@link #foodModule}) we are observing.
	 */
	@Override
	public void update(Observable o, Object arg) {
		if (arg.getClass().equals(FoodData.class)) {
			FoodData fD = (FoodData) arg;
			System.out.println(fD);
			series[0][fD.getTime() % 100] = ((double) fD.getTime() % 100) / 10;
			series[1][fD.getTime() % 100] = fD.getGlucose();
			xyDataset.seriesChanged(null);
		} else {
			System.out.println("Unknown object of class: "
					+ arg.getClass().getName());
		}
	}
}
