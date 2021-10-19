/**
 * 
 */
package insulinPump.injector;

import simulator.controller.AbstractController;
import simulator.view.input.AbstractInput;

/**
 * 
 * Simple example implementation of an injector class for the insulin pump.
 * 
 * @author rc
 * 
 */
public class BasicInjector extends AbstractInput {

	public BasicInjector(AbstractController controller) {
		super(controller);
	}

	@Override
	public void workLoop() {
	}

	public void injectInsulin() {
		this.controller.addInsulin(AbstractController.InsulinType.RAPID);
	}
}
