package character;

import java.io.Serializable;

public class Character implements Serializable{
	String name;
	Health health;		
	Mana mana;		
	PhyDamage phyDamage;	
	MagDamage magDamage;	
	PhyDefence phyDefence;
	MagDefence magDefence;	
	SkillEquip skillEquip;	
	Speed speed;
	Level level;
	Exp exp;
	
	public double pdamGrow, pdamBase, mdamGrow, mdamBase, pdefGrow, pdefBase, mdefGrow, mdefBase;
	public double hGrow, hBase, manaGrow, manaBase;
	
	public Character() {
		
	}
	
	public Character(String name, Health health, Mana mana, PhyDamage phyDamage, MagDamage magDamage,	
			PhyDefence phyDefence, MagDefence magDefence, SkillEquip skillEquip, Speed speed,Level level,Exp exp,
			double pdg, double pdb, double mdg, double mdb, double pdeg, double pdeb, double mdeg, double mdeb,
			double hg, double hb, double mg, double mb) 
	{
				this.name = name;
				this.health = health;
				this.mana = mana;
				this.phyDamage = phyDamage;
				this.magDamage = magDamage;
				this.phyDefence = phyDefence;
				this.magDefence = magDefence;
				this.skillEquip = skillEquip;
				this.speed = speed;
				this.level = level;
				this.exp = exp;
				this.pdamGrow = pdg;
				this.pdamBase = pdb;
				this.mdamGrow = mdg;
				this.mdamBase = mdb;
				this.pdefGrow = pdeg;
				this.pdefBase = pdeb;
				this.mdefGrow = mdeg;
				this.mdefBase = mdeb;
				this.hGrow = hg;
				this.hBase = hb;
				this.manaGrow = mg;
				this.manaBase = mb;
	}
			
	public Character(Character p) 
	{
				this.name = new String(p.name);
				this.health = new Health(p.getHealth().getMaxhealth(), p.getHealth().getCurrenthealth());
				this.mana = new Mana(p.getMana());
				this.phyDamage = new PhyDamage(p.getPhyDamage().getDamage());
				this.magDamage = new MagDamage(p.getMagDamage().getDamage());
				this.phyDefence = new PhyDefence(p.getPhyDefence().getDefence());
				this.magDefence = new MagDefence(p.getMagDefence().getDefence());
				this.skillEquip = new SkillEquip(p.getSkillEquip());
				this.speed = new Speed(p.getSpeed());
				this.level = new Level(p.getLevel().getLevel());
				this.exp = new Exp(p.getExp());
				this.pdamGrow = p.pdamGrow;
				this.pdamBase = p.pdamBase;
				this.mdamGrow = p.mdamGrow;
				this.mdamBase = p.mdamBase;
				this.pdefGrow = p.pdefGrow;
				this.pdefBase = p.pdefBase;
				this.mdefGrow = p.mdefGrow;
				this.mdefBase = p.mdefBase;
				this.hGrow = p.hGrow;
				this.hBase = p.hBase;
				this.manaGrow = p.manaGrow;
				this.manaBase = p.manaBase;
	}
	
	void receiveDamage(double damage) 
	{
		 this.health.doDelta(-(damage));
	}
	
	void receiveHeal(double heal) 
	{
		this.health.doDelta(heal);
	}
	
	public Health getHealth() {return this.health;}
	public Mana getMana() {return this.mana;}
	public PhyDamage getPhyDamage() {return this.phyDamage;}
	public MagDamage getMagDamage() {return this.magDamage;}
	public PhyDefence getPhyDefence() {return this.phyDefence;} 
	public MagDefence getMagDefence() {return this.magDefence;}  	
	public SkillEquip getSkillEquip() {return this.skillEquip;}   	
	public Speed getSpeed() {return this.speed;} 
	public Level getLevel() {return this.level;}  
	public Exp getExp() {return this.exp;}  
	
	public String getName() {return this.name;}
	public void setName(String str) {this.name = str;}
	
	//任何可能改到角色等級的狀況，都要call這個
	public void setPropertybyFormula() 
	{
		double hp = this.hGrow * this.level.getLevel() +  this.hBase ;
		this.health.setMaxhealth(hp); this.health.setCurrenthealth(hp);
		
		double mp = this.manaGrow * this.level.getLevel() + this.manaBase;
		this.mana.setMaxmana(mp); this.mana.setCurrentmana(mp);
		
		double pd = this.pdamGrow * this.level.getLevel() +  this.pdamBase;
		this.phyDamage.setDamage(pd);
		
		double md = this.mdamGrow * this.level.getLevel() +  this.mdamBase;
		this.magDamage.setDamage(md);
		
		double pdef = this.pdefGrow * this.level.getLevel() +  this.pdefBase;
		this.phyDefence.setDecence(pdef);
		
		double mdef = this.mdefGrow * this.level.getLevel() +  this.mdefBase;
		this.magDefence.setDecence(mdef);
	}
}
