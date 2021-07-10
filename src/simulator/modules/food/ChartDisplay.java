/**
 * 
 */
package simulator.modules.food;

import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.time.Minute;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.ui.RectangleInsets;

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
	JFreeChart chart;
	private TimeSeries glucoseLevel = new TimeSeries("Overall score",
			Minute.class);
	private TimeSeriesCollection dataset = new TimeSeriesCollection();

	public ChartDisplay(FoodModule foodModule2) {
		super("Chart Display");
		foodModule = foodModule2;
		foodModule.addObserver(this);

		chart = ChartFactory.createTimeSeriesChart("Target statistics for ", // title
				"Date", // x-axis label
				"", // y-axis label
				dataset, // data
				true, // create legend?
				true, // generate tooltips?
				false // generate URLs?
				);
		dataset.addSeries(this.glucoseLevel);

		chart.setBackgroundPaint(Color.white);

		XYPlot plot = (XYPlot) chart.getPlot();
		plot.setBackgroundPaint(Color.lightGray);
		plot.setDomainGridlinePaint(Color.white);
		plot.setRangeGridlinePaint(Color.white);
		plot.setAxisOffset(new RectangleInsets(5.0, 5.0, 5.0, 5.0));
		plot.setDomainCrosshairVisible(true);
		plot.setRangeCrosshairVisible(true);

		XYItemRenderer r = plot.getRenderer();
		if (r instanceof XYLineAndShapeRenderer) {
			XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer) r;
			renderer.setBaseShapesVisible(true);
			renderer.setBaseShapesFilled(true);
		}

		DateAxis axis = (DateAxis) plot.getDomainAxis();
		axis.setDateFormatOverride(new SimpleDateFormat("dd-MM-yy"));

		ChartPanel cP = new ChartPanel(this.chart);
		this.add(cP);
		this.setSize(800,400);
		this.pack();
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

			Calendar calendar = Calendar.getInstance();
			calendar.setTime(fD.getTime());
			Minute min = new Minute(calendar.getTime());
			this.glucoseLevel.add(min, fD.getGlucose());
			dataset.seriesChanged(null);
			
		} else {
			System.out.println("Unknown object of class: "
					+ arg.getClass().getName());
		}
	}
}
