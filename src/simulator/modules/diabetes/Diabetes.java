package simulator.modules.diabetes;
import java.util.Date;

public class Diabetes {
private float carbOld, carbNew, carbWorked, minAmount;
private int interval;
private Date time, timeOld;

	public void setCarbNew(float newC) {
		
		carbOld = this.carbNew;
		this.carbNew = newC;
		if (newC-carbOld >0){
			this.carbWorked += (newC - carbOld);
		}
	}



	public void setTime(Date newTime) {
		this.timeOld.setTime(time.getTime());
		this.time.setTime(newTime.getTime());
		
	}

	public Diabetes(long initialDate){
		this.time = new Date(initialDate);
		this.timeOld = new Date(initialDate);
		this.setCarbNew(0);
		this.carbOld = 0;
		this.carbWorked = 0;
		this.interval = 3000;
		this.minAmount = 0.1f;
	}
	
	public float getInsulin(){
		float circles, plasmaInsulin = 0, minInsulinPerInterval ;
		
		
		circles = (time.getTime() - timeOld.getTime())/interval;
		int endCircle = (int) Math.floor(circles);
		
		minInsulinPerInterval = minAmount /(60 / interval);
				
		
		for(int x=1 ; x<=endCircle ; x++){
			plasmaInsulin += carbWorked / 24;
			carbWorked = carbWorked / 2;
			
			
			if (carbWorked < minInsulinPerInterval){
				carbWorked = 0;
				plasmaInsulin += minInsulinPerInterval;
			}						
		}
		
		if((endCircle * interval) < (time.getTime() - timeOld.getTime())) {
			this.time.setTime(this.timeOld.getTime() + (endCircle * interval));
		}
		return plasmaInsulin;
		
	}

	
}
	
	
