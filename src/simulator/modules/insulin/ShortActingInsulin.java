package simulator.modules.insulin;

import java.util.Random;

public class ShortActingInsulin extends Insulin {

	public ShortActingInsulin() {
		super.alpha = 3;
		super.beta = 2.7;
		super.startingtime = ((new Random().nextInt() % 10) + 25)/60;
		super.duration = ((new Random().nextInt() % 180)/60) + 5;
	}
	
}