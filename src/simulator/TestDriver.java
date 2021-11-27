package simulator;

import java.io.File;

import insulinPump.injector.BasicInjector;
import insulinPump.logic.BasicLogic;
import insulinPump.logic.ExtendedLogic;
import insulinPump.sensor.BasicSensor;
import simulator.controller.AbstractController;
import simulator.controller.ConcreteController;
import simulator.model.Model;
import simulator.view.input.AbstractInput;
import simulator.view.input.GraphicalInput;
import simulator.view.output.CSVOutput;
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
		GraphicalOutput output = new GraphicalOutput(mod);
		AbstractInput in = new GraphicalInput(contr, output);

		BasicInjector injector = new BasicInjector(contr);
		//BasicLogic logic = new BasicLogic(injector);
		ExtendedLogic logic = new ExtendedLogic(injector);
		BasicSensor sensor = new BasicSensor(mod,logic);

		new CSVOutput(mod,new File("testOutput.csv"),true);
		in.workLoop();
	}

}
