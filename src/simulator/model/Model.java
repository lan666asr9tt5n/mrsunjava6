/**
 * 
 */
package simulator.model;

import java.util.Date;
import java.util.Observable;

import simulator.modules.diabetes.Diabetes;
import simulator.modules.food.AbstractFood;
import simulator.modules.food.FoodModule;
import simulator.modules.insulin.Injection;
import simulator.modules.insulin.Insulin;
import simulator.modules.insulin.InsulinModule;

/**
 * Combines all behavioral models and calculates overall values.
 * 
 * @author rc
 * 
 */
public class Model extends Observable {

	public static double getDoubleFromDate(Date date) {
		return (date.getTime() / ((double) 1000 * 60 * 60));
	}

	public static Date getDateFromDouble(double d) {
		return new Date(((long) d) * ((long) 1000 * 60 * 60));
	}

	/*
	 * The single modules which are used to calculate the overall numbers.
	 */
	private FoodModule foodModule;
	private InsulinModule insulinModule;
	private Diabetes diabetesModule;

	/*
	 * Keep the actual time. Used for computation and as output value to the
	 * output views.
	 */
	private Date time = null;

	/*
	 * Field for the resulting values after each iteration
	 */
	private double foodGlucose;
	private double injectedInsulin;
	private double absoluteGlucose;
	private double diabetesInsulin;
	private double absorbedGlucose;
	private double overallInsulin;

	public static final double glucoseBaseLevel = 5.5;

	// TODO: upper bound = 7.5 ok?
	public static final double glucoseCriticalUpperBound = 10.5;
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
		this.diabetesModule.setCarbNew(food.getCarboHydrates());
	}

	/**
	 * Add insulin to simulation.
	 */
	public void addInsulin(Insulin insulin,double amount) {
		this.insulinModule.addInjection(new Injection(insulin, time, amount));
	}
	public void addInsulin(Insulin insulin) {
		addInsulin(insulin, 180);
	}

	/**
	 * Set new time, calculate resulting values and notify observers.
	 */
	public void setTime(Date time) {

		if (this.time == null) {
			this.diabetesModule = new Diabetes(new Date (time.getTime()));
		}

		/*
		 * Calculate values..
		 */
		this.time = time;
		this.foodGlucose = this.foodModule.calculateOverallGlucose(time);
		this.injectedInsulin = this.insulinModule.getRelInsulin(time);
		this.diabetesModule.setTime(time);
		this.diabetesInsulin = this.diabetesModule.getInsulin();

		this.absorbedGlucose = (this.injectedInsulin + this.diabetesInsulin)*0.25;
		this.absoluteGlucose = this.foodGlucose + Model.glucoseBaseLevel - this.absorbedGlucose;

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

	public double getInjectedInsulin() {
		return injectedInsulin;
	}

	public double getAbsorbedGlucose() {
		return absorbedGlucose;
	}
	
	public double getDiabetesInsulin() {
		return diabetesInsulin;
	}
	
	public double getOverallInsulin() {
		return overallInsulin;
	}
}

