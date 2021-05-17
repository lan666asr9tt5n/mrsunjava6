/**
 * 
 */
package simulator.modules.food;

/**
 * @author rc
 *
 */
public class HighGlycemicFood extends AbstractFood {

	/**
	 * @param ammount
	 * @param time
	 */
	public HighGlycemicFood(int ammount, int time) {
		super(ammount, time);
	}

	/* (non-Javadoc)
	 * @see simulator.modules.food.AbstractFood#calculateGlucose(int)
	 */
	@Override
	double calculateGlucose(int time) {
		int localTime = time - this.time;
		return Math.sin(((double)localTime)/10*this.ammount);
	}

}
