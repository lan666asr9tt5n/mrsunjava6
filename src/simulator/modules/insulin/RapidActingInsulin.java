package behavior.insulin;

import java.util.Random;

public class RapidActingInsulin extends Insulin {

	public RapidActingInsulin() {
		super.alpha = 1.7;
		super.beta = 1.3;
		super.startingtime = ((new Random().nextInt() % 10) + 5)/60;
		super.duration = ((new Random().nextInt() % 180)/60) + 5;
	}
	
}