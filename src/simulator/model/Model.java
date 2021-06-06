/**
 * 
 */
package simulator.model;

import java.util.Observable;
import simulator.modules.food.AbstractFood;
import simulator.modules.insulin.Insulin;
import java.util.Date;
import simulator.modules.food.FoodModule;
import simulator.modules.insulin.InsulinModule;

/**
 * @author rc
 *
 */
public class Model extends Observable {

	/**
	 * 
	 */
	 private FoodModule foodModule;
	/**
	 * 
	 */
	 InsulinModule insulinModule;

	 Date time;
	 double glucose;
	 double insulin;
	/**
	 * 
	 */
	public Model() {
	}

	/**
	 * 
	 */
	 void addFood(AbstractFood name) {
		
	}

	/**
	 * 
	 */
	 void addInsulin(Insulin insulin) {
		
	}

	/**
	 * 
	 */
	 void setTime(Date name) {
		
	}

	public Date getTime() {
		return time;
	}

	public double getGlucose() {
		return glucose;
	}

	public double getInsulin() {
		return insulin;
	}

}
