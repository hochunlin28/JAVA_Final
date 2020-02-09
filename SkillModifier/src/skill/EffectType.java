package skill;

import java.io.Serializable;

/**there are only pure phy mag heal four types*/
public class EffectType implements Serializable{
	String type;
	//pure phy mag heal
	/**default constructor*/
	public EffectType() {
		type = "pure";
	}
	/**normal constructor*/
	public EffectType(String type) {
		this.type = type;
	}
	/**default constructor*/
	public EffectType(EffectType effecttype) {
		this.type = new String(effecttype.getType());
	}
	/**if type == pure return true else false*/
	public boolean isPure() {
		if(type.equals("pure"))return true;
		else return false;
	}
	/**if type == phy return true else false*/
	public boolean isPhy() {
		if(type.equals("phy"))return true;
		else return false;
	}
	/**if type == mag return true else false*/
	public boolean ismag() {
		if(type.equals("mag"))return true;
		else return false;
	}
	/**if type == heal return true else false*/
	public boolean isHeal() {
		if(type.equals("heal"))return true;
		else return false;
	}
	/**if this.type == type return true else false*/
	public boolean equals(String type) {
		if(this.type.equals(type))return true;
		else return false;
	}
	/**if type == effecttype.type return true else false*/
	public boolean equals(EffectType effecttype) {
		if(this.type.equals(effecttype.getType()))return true;
		else return false;
	}
	/**return type*/
	public String getType() {
		return type;
	}
	/**this.type = type*/
	public void setType(String type) {
		this.type = type;
	}
	/**his.type = effecttype.type*/
	public void setType(EffectType effecttype) {
		this.type = effecttype.getType();
	}
}
