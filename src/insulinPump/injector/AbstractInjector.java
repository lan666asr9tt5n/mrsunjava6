package insulinPump.injector;

import simulator.controller.AbstractController;
import simulator.view.input.AbstractInput;

public abstract class AbstractInjector extends AbstractInput {

	public AbstractInjector(AbstractController controller) {
		super(controller);
	}

	public abstract void injectInsulin();
	public abstract void injectInsulin(double amount);

}