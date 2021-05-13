/**
 * 
 */
package simulator.modules.food;

import java.util.ArrayList;
import java.util.List;
import simulator.modules.food.AbstractFood;

/**
 * @author rc
 *
 */
public class FoodModule {

	private List<AbstractFood> foods = new ArrayList<AbstractFood>();
	/**
	 * 
	 */
	 int time;

	/**
	 * Add foods to module.
	 */
	void addFood(AbstractFood food) {
		this.foods.add(food);
	}

	/**
	 * Calculate overall amount of glucose which will be released by all foods currently in the module.
	 */
	 double calculateOverallGlucose(int time) {
		this.time = time;
		if (this.foods.isEmpty()) {
			return 0;
		} else {
			double ret = 0;
			List <AbstractFood> removals = new ArrayList<AbstractFood>();
			for (AbstractFood food : this.foods) {
				if (food.calculateGlucose(time) >= 0 ) {
					ret += food.calculateGlucose(time);
				} else {
					removals.add(food);
				}
			}
			this.foods.removeAll(removals);
			return ret;
		}
	}

	/**
	 * Remove a food from the the module if the food stoped to have effects on the glucose level.
	 * This can be seen as the food has been completely processed by the body.
	 */
	 void delFood(AbstractFood name) {
		
	}

	public int getTime() {
		return this.time;
	}

}
