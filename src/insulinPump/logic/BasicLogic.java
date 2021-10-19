/**
 * 
 */
package insulinPump.logic;

import simulator.model.Model;
import insulinPump.injector.BasicInjector;
import insulinPump.sensor.BasicSensor;

/**
 * This class serves as the very basic example of how to possibly implement the
 * logic of an insulin pump.
 * 
 * @author rc
 * 
 */
public class BasicLogic {

	private BasicInjector basicInjector;

	/**
	 * 
	 */
	public BasicLogic(BasicInjector basicInjector2) {
		basicInjector = basicInjector2;
	}

	/**
	 * Here the actual processing (i.e. computation etc.) will take place.
	 * Actions will be triggered or executed via the injector.
	 * This method is called by the sensor which is notified by the model.
	 * Therefore the notification will be kind of forwarded.
	 * 
	 * @param glucoseLevel
	 */
	public void doAction(double glucoseLevel) {
		if (glucoseLevel > Model.glucoseUpperBound) {
			this.basicInjector.injectInsulin();
		}
	}
}
