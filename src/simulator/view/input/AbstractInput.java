/**
 * 
 */
package simulator.view.input;

import simulator.controller.AbstractController;

/**
 * @author rc
 *
 */
public abstract class AbstractInput {

	protected AbstractController controller;

	public abstract void doInput();

	/**
	 * 
	 */
	public AbstractInput(AbstractController controller) {
		this.controller = controller;
		// TODO Auto-generated constructor stub
	}

}
