package simulator.modules.food;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.DefaultXYDataset;

/**
 * This class is intended to serve as Test class mainly for testing JFreeChart.
 * In order for this to work JFreeChart is needed: http://www.jfree.org/jfreechart
 * 
 * @author Ruediger Gad
 * 
 */
public class Test {

	public static void main(String[] args) {
		JFrame myWindow = new JFrame("Foo");
		
		DefaultXYDataset xyDataset = new DefaultXYDataset();
		double [][] series = new double[2][100];
		for (int i = 1; i < 100; i++) {
			series[0][i] = ((double) i)/10;
			series[1][i] = .0;
		}
		xyDataset.addSeries("Sinus", series);
		
		JFreeChart lineChart = ChartFactory.createXYLineChart("My Line Chart", "My X Axis", "Y-axis", xyDataset, PlotOrientation.VERTICAL, true, true, false);

		ChartPanel chartPanel = new ChartPanel(lineChart);

		myWindow.add(chartPanel);
		myWindow.setSize(800, 400);
		myWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myWindow.setVisible(true);
		
		int temp = 0;
		while(true){
			for (int i = 1; i < 100; i++) {
				series[0][i] = ((double) i)/10;
				series[1][i] = Math.sin(((double)i+temp)/10);
				xyDataset.seriesChanged(null);
				try {
					Thread.sleep(170);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}	
			temp+=100;
		}
	}

}
