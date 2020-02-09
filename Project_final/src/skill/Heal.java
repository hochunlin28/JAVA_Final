package skill;

import java.io.Serializable;

public class Heal implements Serializable{
	private double baseHeal,phyMult,magMult;
	EffectType effectType;
	/**default constructor*/
	public Heal() {
		baseHeal = 0;
		phyMult = 0;
		magMult = 0;
		effectType = new EffectType();
	}
	/**normal constructor*/
	public Heal(double baseHeal,double phyMult,double magMult) {
		this.baseHeal = baseHeal;
		this.phyMult = phyMult;
		this.magMult = magMult;
		effectType = new EffectType("heal");
	}
	/**copy constructor*/
	public Heal(Heal heal) {
		this.baseHeal = heal.getBaseHeal();
		this.phyMult = heal.getPhyMult();
		this.magMult = heal.getMagMult();
		effectType = new EffectType(heal.getEffectType());
	}
	/**return baseHeal*/
	public double getBaseHeal() {
		return baseHeal;
	}
	/**this.baseHeal = baseHeal*/
	public void setBaseHeal(double baseHeal) {
		this.baseHeal = baseHeal;
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
	/**return final heal*/
	public double getHeal(double phyDamage,double magDamage) {
		return baseHeal + phyDamage * phyMult + magDamage * magMult;
	}
	/**active heal*/
	public void perform() {
		/* Character performer;
		 * Character[] target;
		 * int target_count;
		 * locate targets and store them in target[] and count target number
		 * double heal = getHeal(performer.phydamage.getdamage(),performer.magdamage.getdamage());
		 * for(int i = 0;i < target_count;++i) target[i].health.receiveHeal(heal,effecttype);
		 * */
	}
	/**print message of Heal*/
	public String print() {
		String str = "";
		str+="baseHeal = " + baseHeal+ "\n";
		str+="phyMult = " + phyMult+ "\n";
		str+="magMult = " + magMult+ "\n";
		str+="effectType = " + effectType.getType()+ "\n";
		return str;
	}
}
