/**
 * 
 */
package simulator.modules.food;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.DefaultXYDataset;

/**
 * @author rc
 *
 */
public class ChartDisplay extends Thread {

	private FoodModule foodModule;

	/**
	 * 
	 */
	public ChartDisplay(FoodModule foodModule2) {
		foodModule = foodModule2;
	}

	@Override
	public void run(){
		JFrame myWindow = new JFrame("ChartDisplay");
		
		DefaultXYDataset xyDataset = new DefaultXYDataset();
		double [][] series = new double[2][100];
		for (int i = 1; i < 100; i++) {
			series[0][i] = ((double) i)/10;
			series[1][i] = .0;
		}
		xyDataset.addSeries("Food", series);
		
		JFreeChart lineChart = ChartFactory.createXYLineChart("My Line Chart", "My X Axis", "Y-axis", xyDataset, PlotOrientation.VERTICAL, true, true, false);

		ChartPanel chartPanel = new ChartPanel(lineChart);

		myWindow.add(chartPanel);
		myWindow.setSize(800, 400);
		myWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myWindow.setVisible(true);
		
		int temp = 0;
		while(myWindow.isShowing()){
			for (int i = 1; i < 100; i++) {
				series[0][i] = ((double) i)/10;
				series[1][i] = foodModule.calculateOverallGlucose(i+temp);
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
