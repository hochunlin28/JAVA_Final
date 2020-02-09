package character;
import java.io.Serializable;

public class Health implements Serializable{

	private double maxhealth;
	private double currenthealth;
	
	public Health() {
		this.maxhealth = 100;
		this.currenthealth = 100;
	}
	
	public Health(double maxhealth,double currenthealth) {
		this.maxhealth = maxhealth;
		this.currenthealth = currenthealth;
	}
	
	public Health(Health h) {
		this.maxhealth = h.maxhealth;
		this.currenthealth = h.currenthealth;
	}
	
	//
	public void doDelta(double delta) {
		this.currenthealth = this.currenthealth + delta;
	}
	
	//return maxhealth
	public double getMaxhealth() {
		return this.maxhealth;
	}
	
	//change maxhealth
	public void setMaxhealth(double new_maxhealth){
		this.maxhealth = new_maxhealth;
	}
	
	//return currenthealth
	public double getCurrenthealth() {
		return this.currenthealth;
	}
	
	//change currenthealth
	public void setCurrenthealth(double new_currenthealth){
		this.currenthealth = new_currenthealth;
	}
	
	//receiveDamage(func)	//return damage_caculated
	//:receiveHeal(func)		//return heal_caculated
}
