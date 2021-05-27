package simulator.modules.insulin;


public abstract class Insulin{
	protected double alpha;
	protected double beta;
	protected double startingtime;
	protected double duration;
	
	public double getDuration() {
		return duration;
	}

	public double getStartingTime() {
		return startingtime;
	}
	
	public double getPercent(double start, double end){
		// function to calculate relative insulin
		double result = 0;
				
		if (start < getDuration()){
			start -= startingtime;
			end -= startingtime;
			if (start < 0) start = 0;
			if (end < 0) end = 0;
			
			result = calcIntegral(end) - calcIntegral(start);
		}
		
		return result;
	}

	public double calcIntegral(double time){
		double tmp_alpha = 1 / alpha;
		double tmp_beta = 1 / beta;
		double numerator = (Math.exp(tmp_alpha * time * (-1))/tmp_alpha)-(Math.exp(tmp_beta * time * (-1))/tmp_beta);
		double denominator = 1/tmp_beta - 1/tmp_alpha;
		
		return 1 + (numerator / denominator);
	}

}