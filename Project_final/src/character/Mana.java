package character;
import java.io.Serializable;

public class Mana  implements Serializable{
	double recover;		//use doDelta to recover
	double maxmana;
	double currentmana;
	
	public Mana() {
		this.recover = 10;
		this.maxmana = 100;
		this.currentmana = 100;
	}
	
	public Mana(double maxmana,double currentmana,double recover) {
		this.recover = recover;
		this.maxmana = maxmana;
		this.currentmana = currentmana;
	}
	
	public Mana(Mana m) {
		this.recover = m.recover;
		this.maxmana = m.maxmana;
		this.currentmana = m.currentmana;
	}
	
	public void doDelta(double delta) {
		this.currentmana = this.currentmana + delta;
	}
	
	public double getMaxmana() {
		return this.maxmana;
	}
	
	public void setMaxmana(double new_maxmana) {
		this.maxmana = new_maxmana;
	}
	
	public double getCurrentmana() {
		return this.currentmana;
	}
	
	public void setCurrentmana(double new_currentmana) {
		this.currentmana = new_currentmana;
	}
	
	public double getRecover() {
		return this.recover;
	}
}
