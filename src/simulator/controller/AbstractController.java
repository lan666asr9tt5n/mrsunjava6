package simulator.controller;

import simulator.model.Model;
import simulator.modules.food.AbstractFood;
import simulator.modules.insulin.Insulin;

public abstract class AbstractController {

	public static enum FoodType {HIGH,MED,LOW};
	public static enum InsulinType {LONG,RAPID,SHORT};
	
	protected Model model;
	/**
	 * 
	 */
	Thread timeSource;
	/**
	 * 
	 */
	 java.lang.Boolean pause;
	/**
	 * 
	 */
	 int delay;
	/**
	 * 
	 */
	public abstract void addFood(AbstractFood food);

	/**
	 * 
	 */
	 abstract void addInsulin(Insulin insulin);

	/**
	 * 
	 */
	 public abstract void setPause(Boolean pause);

	/**
	 * 
	 */
	 public abstract void unsetPause(Boolean pause);

	/**
	 * 
	 */
	 public abstract void setDelay(int delay);

	public AbstractController(Model model2) {
		model = model2;
	}
	public AbstractController() {

	}
	/**
	 * 
	 */
	public abstract void addFood(FoodType foodType);
	public abstract void addFood(FoodType foodType,long inputTimeSource);	 	 
	public abstract void addInsulin(InsulinType insulinType);

}
