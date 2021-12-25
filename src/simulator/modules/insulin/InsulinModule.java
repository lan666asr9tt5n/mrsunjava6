package simulator.modules.insulin;

import java.util.Date;
import java.util.Vector;

public class InsulinModule {
	Vector<Injection> vecInsulin = new Vector<Injection>();
	
	public double getRelInsulin(Date time){
		double result = 0;
		for (int insulin_cnt = 0; insulin_cnt < vecInsulin.size(); insulin_cnt++)
		{
			result += vecInsulin.elementAt(insulin_cnt).getInsulinAmount(time);
		}
		return result;
	}

	public double getPredictedInsulin() {
		double result = 0;
		for (int insulin_cnt = 0; insulin_cnt < vecInsulin.size(); insulin_cnt++)
		{
			result += vecInsulin.elementAt(insulin_cnt).getPredictedInsulinAmount();
		}
		return result;
	}

	public void addInjection(Injection injection){
		vecInsulin.add(injection);
	}
	
	
}
