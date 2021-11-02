/**
 * 
 */
package simulator.view.input;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JToggleButton;

import simulator.controller.AbstractController;
import simulator.view.output.GraphicalOutput;


/**
 * @author rc
 *
 */
public class GraphicalInput extends AbstractInput {

	private GraphicalOutput graphicalOutput;	
	
	public GraphicalInput(AbstractController controller2, GraphicalOutput gO) {
		super(controller2);	
		this.graphicalOutput = gO;
	}	
	@Override
	public void workLoop() {
		JFrame frame = new JFrame("Simulation control");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new GridLayout(0, 1, 2, 8));

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
		
		JButton addRapidInsulinButton = new JButton("Add rapid acting insulin");
		frame.add(addRapidInsulinButton);
		addRapidInsulinButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent aE) {
				controller.addInsulin(AbstractController.InsulinType.RAPID);				
			}
		});
		
		JButton addShortInsulinButton = new JButton("Add short acting insulin");
		frame.add(addShortInsulinButton);
		addShortInsulinButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent aE) {
				controller.addInsulin(AbstractController.InsulinType.SHORT);				
			}
		});
		
		JButton addLongInsulinButton = new JButton("Add long acting insulin");
		frame.add(addLongInsulinButton);
		addLongInsulinButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent aE) {
				controller.addInsulin(AbstractController.InsulinType.LONG);				
			}
		});

		JToggleButton toggleChartButton = new JToggleButton("Hide chart");
		frame.add(toggleChartButton);
		toggleChartButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent aE) {
				graphicalOutput.toggleVisibility();
			}
		});

		JToggleButton togglePauseButton = new JToggleButton("Pause");
		frame.add(togglePauseButton);
		togglePauseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent aE) {
				controller.setPause();
			}
		});

		frame.pack();
		frame.setVisible(true);
	}	

}
