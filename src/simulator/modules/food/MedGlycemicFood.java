/**
 * 
 */
package simulator.modules.food;

/**
 * As med glycemic food spaghetti is assumed.
 * Serving size is 360g.
 * @author rc
 *
 */
public class MedGlycemicFood extends AbstractFood {

	/**
	 * @param ammount
	 * @param time
	 */
	public MedGlycemicFood(int ammount, double time) {
		super(ammount, time, 48);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see simulator.modules.food.AbstractFood#calculateGlucose(int)
	 */
	@Override
	double calculateGlucose(double time) {
		double localTime = time - this.time;
		double quotient = localTime / 5.d;
		return (quotient < 1) ? quotient : 2 - quotient;  
	}

}
