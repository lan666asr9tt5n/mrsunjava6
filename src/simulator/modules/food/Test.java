package simulator.modules.food;

import java.io.IOException;


/**
 * This class contains the main method to start the dance.
 * 
 * @author Ruediger Gad
 * 
 */
public class Test {

	public static void main(String[] args) {
		FoodModule foodModule = new FoodModule();
		ChartDisplay chartDisplay = new ChartDisplay(foodModule);
		FoodControl foodControl = new FoodControl(foodModule, chartDisplay);
		try {
			foodControl.workLoop();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
