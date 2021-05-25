package behavior.insulin;

import java.util.Random;

public class LongActingInsulin extends Insulin {
	
	public LongActingInsulin() {
		super.alpha = 5.7;
		super.beta = 6;
		super.startingtime = ((new Random().nextInt() % 10) + 25)/60;
		super.duration = ((new Random().nextInt() % 180)/60) + 5;
	}

}