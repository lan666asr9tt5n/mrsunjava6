package simulator.controller;
import java.util.Calendar;
import java.util.Date;

import simulator.model.Model;
import simulator.modules.food.AbstractFood;
import simulator.modules.food.HighGlycemicFood;
import simulator.modules.food.LowGlycemicFood;
import simulator.modules.food.MedGlycemicFood;
import simulator.modules.insulin.Insulin;
import simulator.modules.insulin.LongActingInsulin;
import simulator.modules.insulin.RapidActingInsulin;
import simulator.modules.insulin.ShortActingInsulin;
/**
 * @author rc
 *
 */
public class ConcreteController extends AbstractController {
	/**
	 * 
	 */
	private Timer timeSource;
	public ConcreteController(Model model) {
		super(model);
		super.model=new Model();
		this.timeSource=this.new Timer(new Date());
		this.timeSource.start();
	}
	@Override
	public void addFood(AbstractFood food) {
		super.model.addFood(food);		
	}
	@Override
	public void addInsulin(Insulin insulin) {
		super.model.addInsulin(insulin);		
	}
	@Override
	public void setDelay(int delay) {
		super.delay=delay;		
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
		switch(foodType){
			case HIGH:
				super.model.addFood(new HighGlycemicFood(1,timeSource.timer.getTime()));
				break;
			case MED:
				super.model.addFood(new MedGlycemicFood(1,timeSource.timer.getTime()));				
				break;				
			case LOW:
				super.model.addFood(new LowGlycemicFood(1,timeSource.timer.getTime()));					
				break;				
		}		
	}
	@Override
	void addInsulin(InsulinType insulinType) {
		switch(insulinType){
			case LONG:
				super.model.addInsulin(new LongActingInsulin());
				break;
			case RAPID:
				super.model.addInsulin(new RapidActingInsulin());				
				break;				
			case SHORT:
				super.model.addInsulin(new ShortActingInsulin());					
				break;				
		}
	}
	@Override	
	void addFood(FoodType foodType,long inputTimeSource) {
		switch(foodType){
			case HIGH:
				super.model.addFood(new HighGlycemicFood(1,inputTimeSource));
				break;
			case MED:
				super.model.addFood(new MedGlycemicFood(1,inputTimeSource));				
				break;				
			case LOW:
				super.model.addFood(new LowGlycemicFood(1,inputTimeSource));					
				break;				
		}		
	}

	public class Timer extends Thread {
		public Date timer;
		Timer(Date timer){
			this.timer=timer;
			this.start();
		}
		public void run() {
			try {
				while(true){
					int delay=ConcreteController.this.delay;
					Thread.sleep(delay);
					long t = timer.getTime(); 			 
					t += delay*60*1000; 			 
					timer = new Date(t);
				}	
			}catch(Exception ex ){			
			}			
		}
	}	
}
