package simulator.modules.food;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


/**
 * This class is intended to serve as Test class mainly for testing JFreeChart.
 * In order for this to work JFreeChart is needed: http://www.jfree.org/jfreechart
 * 
 * @author Ruediger Gad
 * 
 */
public class Test {

	public static void main(String[] args) throws IOException {
		FoodModule foodModule = new FoodModule();
		ChartDisplay chartDisplay = new ChartDisplay(foodModule);
		System.out.println("Starting test...");
		chartDisplay.start();
		
		boolean exit = false;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while (!exit) {
			System.out.println("Add a [h]igh, [m]ed or [l]ow glycemic food to simulation or [e]xit...");
			String input = br.readLine();
			char[] in = input.toCharArray();
			switch (in[0]) {
				case 'h':
					foodModule.addFood(new HighGlycemicFood (1, foodModule.getTime()) );
					break;
				case 'm':
					foodModule.addFood(new MedGlycemicFood (1, foodModule.getTime()) );
					break;
				case 'l':
					break;
				case 'e':
					exit = true;
					break;
				default:
					System.out.println(in);
			}
		}
		
		chartDisplay.interrupt();
		System.exit(0);
	}

}
