package character;
import java.io.Serializable;

public class MagDefence  implements Serializable{

	private double defence;
	
	public MagDefence() {
		this.defence = 50;
	}
	
	public MagDefence(double defence) {
		this.defence = defence;
	}
	
	public MagDefence(MagDefence m) {
		this.defence = m.defence;
	}
	
	public double getDefence() {
		return this.defence;
	}
	
	public void setDecence(double new_defence) {
		this.defence = new_defence;
	}
}
