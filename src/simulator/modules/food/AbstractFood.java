/**
 * 
 */
package simulator.modules.food;

/**
 * @author rc
 *
 */
public abstract class AbstractFood {

	protected int ammount;
	protected int time;

	public AbstractFood(int ammount, int time) {
		this.ammount = ammount;
		this.time = time;
		System.out.println(this);
	}

	/**
	 * Calculate the ammount of glucose released to the blood at a given time.
	 */
	abstract double calculateGlucose(int time);

	@Override
	public String toString(){
		return this.getClass().getName() + ":" + this.time + " - " + this.ammount;
	}
}
