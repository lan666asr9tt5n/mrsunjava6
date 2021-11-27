package insulinPump.logic;

import simulator.model.Model;
import simulator.modules.insulin.Injection;
import simulator.modules.insulin.InsulinModule;
import simulator.modules.insulin.RapidActingInsulin;
import insulinPump.injector.BasicInjector;

public class ExtendedLogic implements Logic {

	public static final double MAX_GRADIENT_UPPER    = 0.3;
	public static final double MAX_GRADIENT_CRITICAL = 0.1;

	private BasicInjector injector;
	private InsulinModule virtualInsulin;
	private double lastLevel;

	public ExtendedLogic(BasicInjector injector) {
		this.injector = injector;
		this.lastLevel = Model.glucoseUpperBound;
	}

	private double getNeededInsulinAmount(double glucoseLevel) {
		double refGlucose = glucoseLevel - Model.glucoseBaseLevel;
		double predInsulin = this.virtualInsulin.getPredictedInsulin() / 4;

		return (refGlucose - predInsulin) * 4;
	}

	@Override
	public void doAction(double glucoseLevel) {
		if(Model.glucoseUpperBound < glucoseLevel) {
			double gradient = glucoseLevel - this.lastLevel;

			if(this.virtualInsulin == null) {
				this.virtualInsulin = new InsulinModule();
				this.virtualInsulin.addInjection(new Injection(new RapidActingInsulin(),180));
				this.injector.injectInsulin();
			} else {
				boolean inject = false;

				if(glucoseLevel >= Model.glucoseCriticalUpperBound && gradient > MAX_GRADIENT_CRITICAL) {
					inject = true;
				} else if(gradient > MAX_GRADIENT_UPPER) {
					inject = true;
				}

				if(inject) {
					this.injector.injectInsulin(getNeededInsulinAmount(glucoseLevel));
					this.lastLevel = glucoseLevel;
				}
			}
		} else {
			this.lastLevel = Model.glucoseUpperBound;
		}
	}

}