import character.*;
import character.Character;

public class Test {

	public static void main(String[] args) {
		Character p = new Character("Mumi", new Health(), new Mana(), new PhyDamage(), new MagDamage(), 
				new PhyDefence(), new MagDefence(), new SkillEquip(), new Speed(), new Level(), new Exp(),
				1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1);

		Character c = new Character(p);
		c.getHealth().setCurrenthealth(27.5);
		c.mdefGrow = 5.8;
		c.setName("Wa");
		c.getLevel().setLevel(6);
		
		System.out.println(p.getHealth().getCurrenthealth() + " " + c.getHealth().getCurrenthealth());
		System.out.println(p.mdefGrow + " " + c.mdefGrow);
		System.out.println(p.getName() + " " + c.getName());
		System.out.println(p.getLevel().getLevel() + " " + c.getLevel().getLevel());
		System.out.println(p.getMana().getMaxmana() + " " + c.getMana().getMaxmana());
		p.getMana().setMaxmana(87);
		c.getMana().setMaxmana(22);
		System.out.println(p.getMana().getMaxmana() + " " + c.getMana().getMaxmana());
	}

}
