package skill;

import java.io.Serializable;

public class TargetType implements Serializable{
	private String type = "";
	//none single_enemy multi_enemy single_ally multi_ally all
	/**default constructor*/
	public TargetType() {
		type = "none";
	}
	/**normal constructor*/
	public TargetType(String type) {
		this.type = type;
	}
	/**copy constructor*/
	public TargetType(TargetType targettype) {
		this.type = new String(targettype.getType());
	}
	/**if this.type == type return true else false*/
	public boolean equals(String type) {
		if(this.type.equals(type))return true;
		else return false;
	}
	/**if type == tatgettype.type return true else false*/
	public boolean equals(TargetType targettype) {
		if(this.type.equals(targettype.getType()))return true;
		else return false;
	}
	/**this.type = type*/
	public void setType(String type) {
		this.type = type;
	}
	/**return type*/
	public String getType() {
		return type;
	}
	/**print message of TargetType*/
	public String print() {
		return type+ "\n";
	}
}
