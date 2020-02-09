package character;
import java.io.Serializable;

//Character's phy attack base ability
public class PhyDamage  implements Serializable{
	private double damage;
	
	public PhyDamage() {
		this.damage = 50;
	}
	
	public PhyDamage(double damage) {
		this.damage = damage;
	}
	
	public PhyDamage(PhyDamage p) {
		this.damage = p.damage;
	}
	
	public double getDamage() {
		return this.damage;
	}
	
	public void setDamage(double new_damage)	{
		this.damage = new_damage;
	}
}
