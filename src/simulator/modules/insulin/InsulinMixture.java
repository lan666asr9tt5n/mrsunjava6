package behavior.insulin;

public abstract class InsulinMixture extends Insulin {

	protected Insulin i1;
	protected Insulin i2;
	protected double weight;

	public InsulinMixture(Insulin i1,Insulin i2,double weight) {
		this.i1 = i1;
		this.i2 = i2;
		this.weight = weight;
	}

	public double getPmol(double start, double end) {
		double result = -1.0;

		
		return result;
	}

}