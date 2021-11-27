package simulator.controller;
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
	private boolean debug = false;
	
	public ConcreteController(Model model) {
		super(model);
		this.pause=false;
		this.timeSource=this.new Timer(new Date(System.currentTimeMillis()));
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
		this.pause = !this.pause;
		if (!this.pause) {
			synchronized (timeSource) {
				timeSource.notify();
			}
		}		
	}
	@Override
	public void unsetPause(Boolean pause) {
	
	}
	@Override
	public void addFood(FoodType foodType) {
		this.addFood(foodType, this.timeSource.timer.getTime());
	}
	@Override
	public void addInsulin(InsulinType insulinType,double amount) {
		switch(insulinType){
		case LONG:
			super.model.addInsulin(new LongActingInsulin(),amount);
			break;
		case RAPID:
			super.model.addInsulin(new RapidActingInsulin(),amount);				
			break;				
		case SHORT:
			super.model.addInsulin(new ShortActingInsulin(),amount);					
			break;				
		}
	}
	@Override
	public void addInsulin(InsulinType insulinType) {
		addInsulin(insulinType,180);
	}
	@Override	
	public void addFood(FoodType foodType,long inputTimeSource) {
		switch(foodType){
			case HIGH:
				super.model.addFood(new HighGlycemicFood(1,Model.getDoubleFromDate(new Date(inputTimeSource))));
				break;
			case MED:
				super.model.addFood(new MedGlycemicFood(1,Model.getDoubleFromDate(new Date(inputTimeSource))));				
				break;				
			case LOW:
				super.model.addFood(new LowGlycemicFood(1,Model.getDoubleFromDate(new Date(inputTimeSource))));					
				break;				
		}		
	}

	public class Timer extends Thread {
		public Date timer;
		public Timer(Date timer){
			this.timer=timer;
			this.start();
		}
		@Override
		public void run() {
			try {
				while(true){
					int delay=300;
					Thread.sleep(delay);
					long t = timer.getTime(); 			 
					t += (1000*60*10); 			 
					timer = new Date(t);
					if (debug) {
						System.out.println(timer);
					}
					model.setTime(timer);
					this.pause();
				}	
			}catch(Exception ex ){			
			}			
		}
		public void pause() {
			synchronized (this) {
				while (ConcreteController.this.pause) {
					try {
						this.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}		
	}

}
