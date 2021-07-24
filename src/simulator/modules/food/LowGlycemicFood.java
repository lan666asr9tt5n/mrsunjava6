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
	public LowGlycemicFood(int ammount, double time) {
		super(ammount, time);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see simulator.modules.food.AbstractFood#calculateGlucose(int)
	 */
	@Override
	double calculateGlucose(double time) {
		double localTime = time - this.time;
		double quotient = localTime / 9.d;
		if (quotient > 0.7 && quotient < 1.3) return 0.7;
		return (quotient < 1) ? quotient : 2 - quotient;  
	}

}
