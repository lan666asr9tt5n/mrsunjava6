package simulator.modules.insulin;

import java.util.Date;

public class Injection {

	private Insulin insulin;
	private double insulinAmount;
	private Date injectionTime;
	
	private Date lastCalculation;

	public Injection(Insulin insulin, double amount) {
		this.setValues(insulin, new Date(), amount);
	}

	public Injection(Insulin insulin, Date timestamp, double amount) {
		setValues(insulin, timestamp, amount);
	}

	private void setValues(Insulin insulin, Date timestamp, double amount) {
		this.injectionTime = timestamp;
		this.insulin = insulin;
		this.insulinAmount = amount;
		this.lastCalculation = timestamp;
	}

	public double getInsulinAmount(Date time){
		double result = 0;
		
		double timelast = DateDiff(injectionTime, lastCalculation);
		double timenow = DateDiff(injectionTime, time);
		//System.out.println(Double.toString(timelast) + '-' + Double.toString(timenow));
		lastCalculation = time;
		
		result = insulin.getPercent(timelast, timenow) * insulinAmount;
		//System.out.println(result);
		return result;
	}

	public double getPredictedInsulinAmount(){
		double result = 0;
		
		double timenow = DateDiff(injectionTime, new Date());
		double timefuture = DateDiff(injectionTime, new Date((long) (injectionTime.getTime() + insulin.getDuration())));
		//System.out.println(Double.toString(timelast) + '-' + Double.toString(timenow));
		
		result = insulin.getPercent(timenow, timefuture) * insulinAmount;
		//System.out.println(result);
		return result;
	}

	private double DateDiff(Date date1, Date date2){
		return (double)(date2.getTime()-date1.getTime())/3600000;
	}
	
}
