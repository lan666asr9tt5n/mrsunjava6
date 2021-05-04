package simulator;
import java.util.Date;

public class Diabetes {
	
	private float illness;
	private int plasmaInsulin;
	

	public Diabetes() {
		//default constructor
		
	}
	
	public int getPlasmaInsulin(Date currentTime){
		return this.plasmaInsulin;
	}

	public void setIllness(float illness) {
		this.illness = illness;
	}
	
}
