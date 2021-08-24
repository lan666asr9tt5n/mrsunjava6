/**
 * 
 */
package simulator.model;

import java.util.Observable;
import java.util.Observer;

import simulator.modules.food.AbstractFood;
import simulator.modules.insulin.Injection;
import simulator.modules.insulin.Insulin;
import java.util.Date;
import simulator.modules.food.FoodModule;
import simulator.modules.insulin.InsulinModule;

/**
 * Combines all behavioral models and calculates overall values.
 * 
 * @author rc
 *
 */
public class Model extends Observable {

	public static double getDoubleFromDate(Date date){
		return (date.getTime()/((double) 1000*60*60));
	}
	public static Date getDateFromDouble(double d){
		return new Date(((long) d) * ((long)1000*60*60));
	}
	/*
	 * The single modules which are used to calculate the overall numbers.
	 */
	 private FoodModule foodModule;
	 private InsulinModule insulinModule;

	 /*
	  * Keep the actual time.
	  * Used for computation and as output value to the output views.
	  */
	 private Date time;
	 
	 /*
	  * Field for the resulting values after each iteration
	  */
	 private double foodGlucose;
	 private double insulin;
	 private double absoluteGlucose;
	 
	 public static final double glucoseBaseLevel = 5.5; 
	 
	 //TODO: upper bound = 7.5 ok?
	 public static final double glucoseUpperBound = 7.5;
	 public static final double glucoseLowerBound = 4.0;
	 

	public Model() {
		this.foodModule = new FoodModule();
		this.insulinModule = new InsulinModule();
	}

	/**
	 * Add food to simulation.
	 */
	 public void addFood(AbstractFood food) {
		 this.foodModule.addFood(food);
	}

	/**
	 * Add insulin to simulation.
	 */
	 public void addInsulin(Insulin insulin) {
		 this.insulinModule.addInjection(new Injection(insulin, 1));
	}

	/**
	 * Set new time, calculate resulting values and notify observers.
	 */
	 public void setTime(Date time) {
		 /*
		  * Calculate values..
		  */
		 this.time = time;
		 this.foodGlucose = this.foodModule.calculateOverallGlucose(time);
		 this.insulin = this.insulinModule.getRelInsulin(time);
		 this.absoluteGlucose = this.foodGlucose + Model.glucoseBaseLevel;
		 
		 /*
		  * ..and notify observers.
		  */
		 this.setChanged();
		 this.notifyObservers(null);
	}

	public Date getTime() {
		return time;
	}

	public double getFoodGlucose() {
		return foodGlucose;
	}
	
	public double getAbsoluteGlucose() {
		return this.absoluteGlucose;
	}

	public double getInsulin() {
		return insulin;
	}
}
