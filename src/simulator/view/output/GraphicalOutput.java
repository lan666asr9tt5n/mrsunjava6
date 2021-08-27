/**
 * 
 */
package simulator.view.output;

import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Observable;

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

import simulator.model.Model;

/**
 * @author rc
 *
 */
public class GraphicalOutput extends AbstractOutput {

	private JFrame frame = new JFrame("GUI Output");

	private JFreeChart chart;
	private TimeSeries foodGlucoseLevel = new TimeSeries("Food Glucose",
			Minute.class);
	private TimeSeries glucoseUpperBound = new TimeSeries("Glucose Upper Bound",
			Minute.class);
	private TimeSeries glucoseLowerAbound = new TimeSeries("Glucose Lower Bound",
			Minute.class);
	private TimeSeries absoluteGlucoseLevel = new TimeSeries("Absolute Glucose Level",
			Minute.class);
	private TimeSeries relativeInsulinLevel = new TimeSeries("Relative Insulin Level",
			Minute.class);
	private TimeSeriesCollection dataset = new TimeSeriesCollection();
	/**
	 * @param model2
	 */
	public GraphicalOutput(Model model2) {
		super(model2);
		
		chart = ChartFactory.createTimeSeriesChart("Body Simulation Monitor", // title
				"Date", // x-axis label
				"", // y-axis label
				dataset, // data
				true, // create legend?
				true, // generate tooltips?
				false // generate URLs?
				);
		dataset.addSeries(this.foodGlucoseLevel);
		dataset.addSeries(this.absoluteGlucoseLevel);
		dataset.addSeries(this.glucoseLowerAbound);
		dataset.addSeries(this.glucoseUpperBound);
		dataset.addSeries(this.relativeInsulinLevel);

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
		this.frame.add(cP);
		this.frame.setSize(800,400);
		this.frame.pack();
		this.frame.setVisible(true);
		this.frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		this.model.addObserver(this);
	}

	/* (non-Javadoc)
	 * @see simulator.view.output.AbstractOutput#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable o, Object arg) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(this.model.getTime());
		Minute min = new Minute(calendar.getTime());
		this.foodGlucoseLevel.add(min, this.model.getFoodGlucose());
		this.absoluteGlucoseLevel.add(min, this.model.getAbsoluteGlucose());
		this.glucoseLowerAbound.add(min, Model.glucoseLowerBound);
		this.glucoseUpperBound.add(min, Model.glucoseUpperBound);
		this.relativeInsulinLevel.add(min, this.model.getInsulin());
		dataset.seriesChanged(null);
	}

	public void toggleVisibility() {
		if (this.frame.isVisible()) {
			this.frame.setVisible(false);
		} else {
			this.frame.setVisible(true);
		}
	}

}
