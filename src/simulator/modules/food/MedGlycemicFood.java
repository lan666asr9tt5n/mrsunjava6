/**
 * 
 */
package simulator.modules.food;

/**
 * @author rc
 *
 */
public class MedGlycemicFood extends AbstractFood {

	/**
	 * @param ammount
	 * @param time
	 */
	public MedGlycemicFood(int ammount, int time) {
		super(ammount, time);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see simulator.modules.food.AbstractFood#calculateGlucose(int)
	 */
	@Override
	double calculateGlucose(int time) {
		int localTime = time - this.time;
		double quotient = localTime / 3.d;
		return (quotient < 1) ? quotient : 2 - quotient;  
	}

}
