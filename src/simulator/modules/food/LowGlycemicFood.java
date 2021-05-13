/**
 * 
 */
package simulator.modules.food;

/**
 * @author rc
 *
 */
public class LowGlycemicFood extends AbstractFood {

	/**
	 * @param ammount
	 * @param time
	 */
	public LowGlycemicFood(int ammount, int time) {
		super(ammount, time);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see simulator.modules.food.AbstractFood#calculateGlucose(int)
	 */
	@Override
	double calculateGlucose(int time) {
		// TODO Auto-generated method stub
		return 0;
	}

}
