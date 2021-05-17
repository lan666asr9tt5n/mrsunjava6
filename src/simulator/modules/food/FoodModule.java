/**
 * 
 */
package simulator.modules.food;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import simulator.modules.food.AbstractFood;

/**
 * 
 * This class holds a list of all foods that are consumed and are still active
 * (i.e. are being processed). It computes the overall amount of glucose which
 * is released to the blood by the foods.
 * 
 * @author rc
 * 
 */
public class FoodModule extends Observable {

	private List<AbstractFood> foods = new ArrayList<AbstractFood>();

	private int time;

	/**
	 * Add foods to module.
	 */
	void addFood(AbstractFood food) {
		foods.add(food);
	}

	/**
	 * Calculate overall amount of glucose which will be released by all foods
	 * currently in the module.
	 */
	public double calculateOverallGlucose(int time) {
		this.time = time;
		double ret = 0;
		if (!this.foods.isEmpty()) {
			List<AbstractFood> removals = new ArrayList<AbstractFood>();
			for (AbstractFood food : this.foods) {
				if (food.calculateGlucose(time) >= 0) {
					ret += food.calculateGlucose(time);
				} else {
					removals.add(food);
				}
			}
			this.foods.removeAll(removals);
		}
		this.setChanged();
		this.notifyObservers(new FoodData(this.time, ret));
		return ret;
	}

	public int getTime() {
		return this.time;
	}

	/**
	 * This nested class is used to pass data to the observers.
	 * 
	 * @author rc
	 * 
	 */
	public class FoodData {
		private int time;
		private double glucose;

		public int getTime() {
			return time;
		}

		public double getGlucose() {
			return glucose;
		}

		public FoodData(int time, double glucose) {
			this.time = time;
			this.glucose = glucose;
		}

		@Override
		public String toString() {
			return "t:" + this.time + " - g:" + this.glucose;
		}
	};
}
