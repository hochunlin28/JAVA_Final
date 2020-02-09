package character;
import java.io.Serializable;

public class Level  implements Serializable{
	private int level;
	
	public Level() {
		this.level = 1;
	}
	
	public Level(int level) {
		this.level = level;
	}
	
	public Level(Level l) {
		this.level = l.level;
	}
	
	public int getLevel() {
		return this.level;
	}
	
	public void setLevel(int new_level) {
		this.level = new_level;
	}
	
	public void update() {
		level = level + 1;
	}
}
