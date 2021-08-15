/**
 * 
 */
package simulator.view.input;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JToggleButton;

import simulator.controller.AbstractController;
import simulator.controller.ConcreteController;
import simulator.modules.food.ChartDisplay;


/**
 * @author rc
 *
 */
public class GraphicalInput extends AbstractInput {

	private ChartDisplay chartDisplay;	
	private boolean pause = false;
	
	public GraphicalInput(AbstractController controller2) {
		super(controller2);	
	}	
	@Override
	public void workLoop() {
		JFrame frame = new JFrame("Food control");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new GridLayout(0, 1, 2, 5));

		JButton addHighButton = new JButton("Add high glycemic food");
		frame.add(addHighButton);
		addHighButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent aE) {
				controller.addFood(AbstractController.FoodType.HIGH);
			}
		});

		JButton addMedButton = new JButton("Add med glycemic food");
		frame.add(addMedButton);
		addMedButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent aE) {
				controller.addFood(AbstractController.FoodType.MED);				
			}
		});

		JButton addLowButton = new JButton("Add low glycemic food");
		frame.add(addLowButton);
		addLowButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent aE) {
				controller.addFood(AbstractController.FoodType.LOW);				
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
				controller.setPause(pause);
			}
		});

		frame.pack();
		frame.setVisible(true);
	}	

}
