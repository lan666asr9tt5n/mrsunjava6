/**
 * 
 */
package simulator.controller;

import simulator.model.Model;
import simulator.modules.food.AbstractFood;
import simulator.modules.insulin.Insulin;

/**
 * @author rc
 *
 */
public class ConcreteController extends AbstractController {


	/**
	 * 
	 */
	public ConcreteController(Model model2) {
		super(model2);
	}

	@Override
	public void addFood(AbstractFood food) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addInsulin(Insulin insulin) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setDelay(int delay) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setPause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void unsetPause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	void addFood(FoodType foodType) {
		// TODO Auto-generated method stub
		
	}

	@Override
	void addInsulin(InsulinType insulinType) {
		// TODO Auto-generated method stub
		
	}

}
