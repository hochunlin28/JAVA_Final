package skill;

import java.io.Serializable;

public class Attack implements Serializable{
	private double baseDamage,phyMult,magMult;
	EffectType effectType;
	/**default constructor*/
	public Attack() {
		baseDamage = 0;
		phyMult = 0;
		magMult = 0;
		effectType = new EffectType();
	}
	/**normal constructor with using string to initialize effecttype*/
	public Attack(double baseDamage,double phyMult,double magMult,String type) {
		this.baseDamage = baseDamage;
		this.phyMult = phyMult;
		this.magMult = magMult;
		this.effectType = new EffectType(type);
	}
	/**normal constructor with using EffectType to initialize effecttype*/
	public Attack(double baseDamage,double phyMult,double magMult,EffectType effecttype) {
		this.baseDamage = baseDamage;
		this.phyMult = phyMult;
		this.magMult = magMult;
		this.effectType = new EffectType(effecttype);
	}
	/**copy constructor*/
	public Attack(Attack attack) {
		baseDamage = attack.getBaseDamage();
		phyMult = attack.getPhyMult();
		magMult = attack.getMagMult();
		effectType = new EffectType(attack.getEffectType());
	}
	/**return baseDamage*/
	public double getBaseDamage() {
		return baseDamage;
	}
	/**this.baseDamage = baseDamage*/
	public void setBaseDamage(double baseDamage) {
		this.baseDamage = baseDamage;
	}
	/**return phyMult*/
	public double getPhyMult() {
		return phyMult;
	}
	/**this.phyMult = phyMult*/
	public void setPhyMult(double phyMult) {
		this.phyMult = phyMult;
	}
	/**return magMult*/
	public double getMagMult() {
		return magMult;
	}
	/**this.magMult = magMult*/
	public void setMagMult(double magMult) {
		this.magMult = magMult;
	}
	/**this.effectType = effectType*/
	public void setEffectType(EffectType effectType) {
		this.effectType = effectType;
	}
	/**return effectType*/
	public EffectType getEffectType() {
		return effectType;
	}
	/**return final damage*/
	public double getDamage(double phyDamage,double magDamage) {
		return baseDamage + phyDamage * phyMult + magDamage * magMult;
	}
	/**active attack*/
	public void perform() {
		/* Character performer;
		 * Character[] target;
		 * int target_count;
		 * locate targets and store them in target[] and count target number
		 * double damage = getDamage(performer.phydamage.getdamage(),performer.magdamage.getdamage());
		 * for(int i = 0;i < target_count;++i) target[i].health.receiveDamage(damage,effecttype);
		 * */
	}
	/**print message of Attack*/
	public String print() {
		String str = "";
		str+="baseDamage = " + baseDamage+ "\n";
		str+="phyMult = " + phyMult+ "\n";
		str+="magMult = " + magMult+ "\n";
		str+="effectType = " + effectType.getType()+ "\n";
		return str;
	}
}
