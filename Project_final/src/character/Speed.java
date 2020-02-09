package character;
import java.io.Serializable;

public class Speed  implements Serializable{
	double basespeed;	//base speed of a character
	double charge;		//once get a full charge character can attack
	
	public Speed() {
		this.basespeed = 1;
		this.charge = 0;
	}
	
	public Speed(double basespeed,double charge) {
		this.basespeed = basespeed;
		this.charge = charge;
	}
	
	//copy constructor
	public Speed(Speed s) {
		this.basespeed = s.basespeed;
		this.charge = s.charge;
	}
	
	public void doDelta(double delta) {
		this.charge = this.charge + delta;
	}
	
	public double getBaseSpeed() {
		return this.basespeed;
	}
	
	public void setBaseSpeed(double new_basespeed) {
		this.basespeed = new_basespeed;
	}
	
	public double getCharge() {
		return this.charge;
	}
	
	public void setCharge(double new_charge) {
		this.charge = new_charge;
	}
}
