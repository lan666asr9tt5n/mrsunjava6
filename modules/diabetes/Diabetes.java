package simulator.modules.diabetes;

public class Diabetes {
private float carbOld, carbNew, carbWorked, minAmount;
private int time, timeOld, interval;

	public void setCarbNew(float newC) {
		
		carbOld = this.carbNew;
		this.carbNew = newC;
		if (newC-carbOld >0){
			this.carbWorked += (newC - carbOld);
		}
	}



	public void setTime(int newTime) {
		timeOld = this.time;
		this.time = newTime;
		
	}

	public Diabetes(){
		this.setCarbNew(0);
		this.setTime(0);
		this.carbOld = 0;
		this.carbWorked = 0;
		this.timeOld = 0;
		this.interval = 3;
		this.minAmount = 0.1f;
	}
	
	public float getInsulin(){
		float circles, plasmaInsulin = 0, minInsulinPerInterval ;
		
		
		circles = (time - timeOld)/interval;
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
		
		return plasmaInsulin;
		
	}

	
}
	
	
