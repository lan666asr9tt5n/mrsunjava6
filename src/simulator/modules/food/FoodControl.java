/**
 * 
 */
package simulator.modules.food;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JToggleButton;

/**
 * Provides controls for controlling the actions, behavior and display of
 * FoodModule and DisplayChart.<br>
 * These controls basically consist of a JFrame, quite a few JButtons and the
 * inner classes for implementing the corresponding ActionListeners. This class
 * currently also holds the time source defined as a Thread which constantly
 * increases the time.
 * 
 * @author rc
 * 
 */
public class FoodControl {

	private ChartDisplay chartDisplay;
	private FoodModule foodModule;

	private boolean pause = false;

	public FoodControl(FoodModule foodModule2, ChartDisplay chartDisplay2) {
		foodModule = foodModule2;
		chartDisplay = chartDisplay2;
	}

	public void workLoop() throws IOException {
		System.out.println("Starting test...");

		/*
		 * We need to have some time source which generates a constant
		 * proceeding time so this is done here in a thread.
		 */
		final Thread timeSource = new Thread() {
			public void run() {
				Date time = new Date(System.currentTimeMillis());
				Calendar cal = Calendar.getInstance();
				cal.setTime(time);

				while (!this.isInterrupted()) {
					foodModule.calculateOverallGlucose(cal.getTime());
					cal.add(Calendar.HOUR_OF_DAY, 1);
					pause();
					try {
						Thread.sleep(300);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}

			/*
			 * Method to pause the thread if requested. Done using boolean
			 * variable of outer class.
			 */
			public void pause() {
				synchronized (this) {
					while (pause) {
						try {
							this.wait();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}
		};
		timeSource.start();

		JFrame frame = new JFrame("Food control");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new GridLayout(0, 1, 2, 5));

		JButton addHighButton = new JButton("Add high glycemic food");
		frame.add(addHighButton);
		addHighButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent aE) {
				foodModule
						.addFood(new HighGlycemicFood(1, foodModule.getTime()));
			}
		});

		JButton addMedButton = new JButton("Add med glycemic food");
		frame.add(addMedButton);
		addMedButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent aE) {
				foodModule
						.addFood(new MedGlycemicFood(1, foodModule.getTime()));
			}
		});

		JButton addLowButton = new JButton("Add low glycemic food");
		frame.add(addLowButton);
		addLowButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent aE) {
				foodModule
						.addFood(new LowGlycemicFood(1, foodModule.getTime()));
			}
		});

		JToggleButton toggleChartButton = new JToggleButton("Hide chart");
		frame.add(toggleChartButton);
		toggleChartButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent aE) {
				if (chartDisplay.isVisible()) {
					chartDisplay.setVisible(false);
				} else {
					chartDisplay.setVisible(true);
				}
			}
		});

		JToggleButton togglePauseButton = new JToggleButton("Pause");
		frame.add(togglePauseButton);
		togglePauseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent aE) {
				pause = !pause;
				if (!pause) {
					synchronized (timeSource) {
						timeSource.notify();
					}
				}
			}
		});

		frame.pack();
		frame.setVisible(true);
	}
}
