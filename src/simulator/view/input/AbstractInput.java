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

	public AbstractController controller;

	/**
	 * 
	 */
	public AbstractInput(AbstractController controller2) {
		controller = controller2;
		// TODO Auto-generated constructor stub
	}
	public AbstractInput() {
	}
}
