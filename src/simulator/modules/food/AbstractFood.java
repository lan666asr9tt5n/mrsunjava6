/**
 * 
 */
package simulator.modules.food;

import simulator.model.Model;

/**
 * @author rc
 * 
 */
public abstract class AbstractFood {

	// Amount of food which is eaten (currently just 1) in serving units.
	protected int ammount;
	// Carbohydrates in gram for 1 amount of food.
	protected float carbonHydrates;
	// The time when the food was eaten.
	protected double time;

	public AbstractFood(int ammount, double time, float carbs) {
		this.ammount = ammount;
		this.time = time;
		this.carbonHydrates = carbs;
		System.out.println(this);
	}

	/**
	 * Calculate the amount of glucose in mmol/l released to the blood at a given time.
	 */
	abstract double calculateGlucose(double time);

	@Override
	public String toString() {
		return this.getClass().getName() + ":" + Model.getDateFromDouble(time)
				+ " - " + this.ammount + " _ " + this.carbonHydrates;
	}
	
	/**
	 * Get carbohydrates in grams.
	 * @return
	 */
	public float getCarboHydrates(){
		return this.carbonHydrates * this.ammount;
	}
}
