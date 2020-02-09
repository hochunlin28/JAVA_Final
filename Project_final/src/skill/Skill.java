package skill;

import java.io.Serializable;

public class Skill implements Serializable{
	Attack attack;
	Heal heal;
	TargetType targetType;
	double manaConsume;
	String name,description;
	
	/**default constructor*/
	public Skill() {
		attack = new Attack();
		heal = new Heal();
		targetType = new TargetType();
		manaConsume = 0;
		name = "";
		description = "";
	}
	/**normal constructor*/
	public Skill(Attack attack,Heal heal,TargetType targettype,double manaConsume,String name,String description) {
		this.attack = new Attack(attack);
		this.heal = new Heal(heal);
		this.targetType = new TargetType(targettype);
		this.manaConsume = manaConsume;
		this.name = new String(name);
		this.description = new String(description);
	}
	/**this.attack = attack*/
	public void setAttack(Attack attack) {
		this.attack.setBaseDamage(attack.getBaseDamage());
		this.attack.setEffectType(attack.getEffectType());
		this.attack.setPhyMult(attack.getPhyMult());
		this.attack.setMagMult(attack.getMagMult());
	}
	/**active skill(unfinished)*/
	public void activeSkill() {
		
	}
	/**return manaConsume*/
	public double getManaConsume() {
		return manaConsume;
	}
	public void setManaConsume(double d) {
		this.manaConsume = d;
	}
	/**return name*/
	public String getName() {
		return name;
	}
	/**return description*/
	public String getDescription() {
		return description;
	}
	public void setDescription(String str) {
		this.description = str;
	}
	public void setName(String str) {
		this.name = str;
	}
	public Attack getAttack() {
		return this.attack;
	}
	
	public Heal getHeal() {
		return this.heal;
	}
	
	public TargetType getTargetType() {
		return this.targetType;
	}
	/**print message of skill*/
	public String print() {
		return name+"\n" +description+"\n" + "耗魔: "+manaConsume+"\n"
				+ attack.print() + heal.print() + targetType.print();
	}
}
