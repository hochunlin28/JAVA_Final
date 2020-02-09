package character;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import skill.*;

public class SkillEquip implements Serializable{
	
	Skill[] skill = new Skill[3];
	
	public SkillEquip() {
		this.skill = new Skill[3];
		for(int i=0 ; i<skill.length ; ++i) {
			this.skill[i] = new Skill();
		}
			
	}
	
	//copy constructor
	public SkillEquip(SkillEquip se) {
		this.skill = new Skill[3];
		for(int i=0 ; i<skill.length ; ++i) {
			this.skill[i] = new Skill(se.getSkill(i).getAttack(), se.getSkill(i).getHeal(), se.getSkill(i).getTargetType(),
					se.getSkill(i).getManaConsume(), se.getSkill(i).getName() ,se.getSkill(i).getDescription());
		}
	}
	
	public Skill getSkill(int index)	{
		if(index>=0 && index < skill.length) {
			return this.skill[index];
		}
		return null;
	}
	
	public int getSkillLen()	{
		return this.skill.length;
	}
	
	public void setSkill(int index, Skill new_Skill) {
		if(index>=0 && index < skill.length) {
			this.skill[index] = new Skill(new_Skill.getAttack(), new_Skill.getHeal(), new_Skill.getTargetType(),
					new_Skill.getManaConsume(), new_Skill.getName() ,new_Skill.getDescription());
		}
	}
	
	//remove skill form this.skill
	public void skillUnequip(int index) {
		if(index>=0 && index < skill.length) {
			this.skill[index] = null;
		}
	}
	
	//add skill to this.skill
		/*public void skillEquip(Skill skill)	{
			if(!this.skill.contains(skill)) {
				this.skill.add(skill);
			}
		}*/
}
