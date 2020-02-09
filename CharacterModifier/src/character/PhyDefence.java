package character;
import java.io.Serializable;

public class PhyDefence implements Serializable{

	private double defence;
	
	public PhyDefence() {
		this.defence = 50;
	}
	
	public PhyDefence(double defence) {
		this.defence = defence;
	}
	
	public PhyDefence(PhyDefence p) {
		this.defence = p.defence;
	}
	
	public double getDefence() {
		return this.defence;
	}
	
	public void setDecence(double new_defence) {
		this.defence = new_defence;
	}
}
