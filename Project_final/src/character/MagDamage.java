package character;
import java.io.Serializable;

//Character's mag attack base ability
public class MagDamage  implements Serializable{
	private double damage;
	
	public MagDamage() {
		this.damage = 50;
	}
	
	public MagDamage(double damage) {
		this.damage = damage;
	}
	
	public MagDamage(MagDamage m) {
		this.damage = m.damage;
	}
	
	public double getDamage() {
		return this.damage;
	}
	
	public void setDamage(double new_damage)	{
		this.damage = new_damage;
	}
}
