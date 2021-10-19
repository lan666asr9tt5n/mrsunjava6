/**
 * 
 */
package insulinPump.sensor;

import insulinPump.logic.BasicLogic;

import java.util.Observable;

import simulator.model.Model;
import simulator.view.output.AbstractOutput;

/**
 * Sample implementation of a sensor for the insulin pump.
 * 
 * @author rc
 * 
 */
public class BasicSensor extends AbstractOutput {

	private BasicLogic basicLogic;

	public BasicSensor(Model model2, BasicLogic basicLogic2) {
		super(model2);
		model2.addObserver(this);
		basicLogic = basicLogic2;
	}

	@Override
	public void update(Observable o, Object arg) {
		this.basicLogic.doAction(super.model.getAbsoluteGlucose());
	}

}
