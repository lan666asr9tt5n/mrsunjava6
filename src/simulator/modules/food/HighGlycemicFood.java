/**
 * 
 */
package simulator.modules.food;

/**
 * Simplyfied assumption of a high glycemic food is a softdrink.
 * Serving unit assumed is 250ml.
 * @author rc
 *
 */
public class HighGlycemicFood extends AbstractFood {

	/**
	 * @param ammount
	 * @param time
	 */
	public HighGlycemicFood(int ammount, double time) {
		super(ammount, time, 26);
	}

	/* (non-Javadoc)
	 * @see simulator.modules.food.AbstractFood#calculateGlucose(int)
	 */
	@Override
	double calculateGlucose(double time) {
		double localTime = time - this.time;
		return Math.sin(((double)localTime)/this.ammount);
	}

}
