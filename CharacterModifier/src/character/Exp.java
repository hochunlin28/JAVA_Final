package character;
import java.io.Serializable;

public class Exp  implements Serializable{
	private int currentExp;
	private int levelExp;
	
	public Exp() {
		this.currentExp = 0;
		this.levelExp = 50;
	}
	
	public Exp(int currentExp, int levelExp) {
		this.currentExp = currentExp;
		this.levelExp = levelExp;
	}
	
	public Exp(Exp e) {
		this.currentExp = e.currentExp;
		this.levelExp = e.levelExp;
	}
	
	public void add(int addi, Level l)
	{
		this.currentExp += addi;
		if(currentExp>=levelExp)
			this.update(l);
	}
	
	public void update(Level l) {
		this.currentExp = 0;
		this.levelExp = this.levelExp + l.getLevel() * 30;
		l.update();
	}
}
