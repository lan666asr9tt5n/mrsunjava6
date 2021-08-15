package simulator;

import java.io.IOException;

import simulator.controller.AbstractController;
import simulator.controller.ConcreteController;
import simulator.model.Model;
import simulator.view.input.AbstractInput;
import simulator.view.input.GraphicalInput;
import simulator.view.output.AbstractOutput;
import simulator.view.output.GraphicalOutput;

public class TestDriver {

	/**
	 * 
	 * This is a test class to actually run the whole thing (for now the body simulation.
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Starting up...");
		Model mod = new Model();
		AbstractController contr = new ConcreteController(mod);
		AbstractInput in = new GraphicalInput(contr);
		in.workLoop();
		
		AbstractOutput output = new GraphicalOutput(mod);
		mod.addObserver(output);
		System.out.println("main: " + mod.countObservers());
		
		
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Shuting down...");
	}

}
