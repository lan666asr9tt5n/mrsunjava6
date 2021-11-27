/**
 * 
 */
package insulinPump.injector;

import simulator.controller.AbstractController;

/**
 * 
 * Simple example implementation of an injector class for the insulin pump.
 * 
 * @author rc
 * 
 */
public class BasicInjector extends AbstractInjector {

	public BasicInjector(AbstractController controller) {
		super(controller);
	}

	@Override
	public void workLoop() {
	}

	@Override
	public void injectInsulin() {
		this.controller.addInsulin(AbstractController.InsulinType.RAPID);
	}

	@Override
	public void injectInsulin(double amount) {
		this.controller.addInsulin(AbstractController.InsulinType.RAPID, amount);
	}

}
