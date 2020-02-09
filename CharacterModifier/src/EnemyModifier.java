import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

import character.*;
import skill.*;
import character.Character;

public class EnemyModifier 
{
	List<Character> enemy = new ArrayList<>();
	List<Skill> skill = new ArrayList<>();
	
	public static void main(String[] args) 
	{
		EnemyModifier  em = new EnemyModifier();
		Scanner keyin = new Scanner(System.in);
		em.readSkillFile();
		
		em.showMessage();
		while(true)
		{
			System.out.println("List Length: " + em.enemy.size());
			String str;
			System.out.println("繼續執行(y/n)?");
			str = keyin.nextLine();
			if(!str.equals("n")) {
				System.out.println("輸入指令:");
				str = keyin.nextLine();
				em.process(str);
			}
			else
				break;
		}
		System.out.println("程式結束");
	}
	
	public void showMessage()
	{
		System.out.println("h - 顯示所有指令");
		System.out.println("fromfile - 從.obj檔讀並寫入List裡(會覆蓋List)");
		System.out.println("tofile - 把List資料寫入.obj檔裡");
		System.out.println("print - 把List所有內容寫入allEnemy.txt裡");
		System.out.println("add - 在list尾巴增加物件");
		System.out.println("search index(int) - 在Console顯示某一Enemy的資料");
		System.out.println("name index(int) str(String) - 修改名字");
		System.out.println("level index(int) str(String) - 修改等級");
		System.out.println("hpg index(int) num(double) - 修改血量成長係數");
		System.out.println("hpb index(int) num(double) - 修改血量成長基數");
		System.out.println("mpg index(int) num(double) - 修改魔力成長係數");
		System.out.println("mpb index(int) num(double) - 修改魔力成長基數");
		System.out.println("pdg index(int) num(double) - 修改物攻成長係數");
		System.out.println("pdb index(int) num(double) - 修改物攻成長基數");
		System.out.println("mdg index(int) num(double) - 修改魔攻成長係數");
		System.out.println("mdb index(int) num(double) - 修改魔攻成長基數");
		System.out.println("pdeg index(int) num(double) - 修改物防成長係數");
		System.out.println("pdeb index(int) num(double) - 修改物防成長基數");
		System.out.println("mdeg index(int) num(double) - 修改魔防成長係數");
		System.out.println("mdeb index(int) num(double) - 修改魔防成長基數");
		System.out.println("spe index(int) num(double) - 修改速度");
		System.out.println("ski index(int) indexForCharacterSkillEqu(int) indexForSkillList(int) - 將技能放入怪物中");
	}
	
	public void process(String str)
	{
		try 
		{
			if(str.equals("h"))
				this.showMessage();
			else if(str.equals("fromfile"))
				this.readFile();
			else if(str.equals("tofile"))
				this.writeFile();
			else if(str.equals("print"))
				this.print();
			else if(str.equals("add"))
				this.add();
			else if(str.length()>=6 && str.substring(0,6).equals("search")) 
			{
				int i = Integer.valueOf(str.substring(7));
				this.search(i);
			}
			else if(str.length()>=4 && str.substring(0,4).equals("name")) 
				this.changeName(str.substring(5));
			else if(str.length()>=5 && str.substring(0,5).equals("level")) 
				this.changeLevel(str.substring(6));
			else if(str.length()>=3 && str.substring(0,3).equals("hpg"))
				this.changeHG(str.substring(4));
			else if(str.length()>=3 && str.substring(0,3).equals("hpb"))
				this.changeHB(str.substring(4));
			else if(str.length()>=3 && str.substring(0,3).equals("mpg"))
				this.changeMG(str.substring(4));
			else if(str.length()>=3 && str.substring(0,3).equals("mpb"))
				this.changeMB(str.substring(4));
			else if(str.length()>=3 && str.substring(0,3).equals("pdg"))
				this.changePG(str.substring(4));
			else if(str.length()>=3 && str.substring(0,3).equals("pdb"))
				this.changePB(str.substring(4));
			else if(str.length()>=3 && str.substring(0,3).equals("mdg"))
				this.changeMDG(str.substring(4));
			else if(str.length()>=3 && str.substring(0,3).equals("mdb"))
				this.changeMDB(str.substring(4));
			else if(str.length()>=3 && str.substring(0,4).equals("pdeg"))
				this.changePDEG(str.substring(5));
			else if(str.length()>=3 && str.substring(0,4).equals("pdeb"))
				this.changePDEB(str.substring(5));
			else if(str.length()>=3 && str.substring(0,4).equals("mdeg"))
				this.changeMDEG(str.substring(5));
			else if(str.length()>=3 && str.substring(0,4).equals("mdeb"))
				this.changeMDEB(str.substring(5));
			else if(str.length()>=3 && str.substring(0,3).equals("spe"))
				this.changeSP(str.substring(4));
			else if(str.length()>=3 && str.substring(0,3).equals("ski"))
				this.changeSK(str.substring(4));
			else
				System.out.println("無此指令");
		}
		catch(Exception e) {e.printStackTrace();}
	}
	
	public void readFile()
	{
		try 
		{
			ObjectInputStream reader = new ObjectInputStream(new FileInputStream("./enemy.obj"));
			this.enemy = (List<Character>)reader.readObject();
			reader.close();
		}
		catch(Exception e) {e.printStackTrace();}
	}
	
	public void readSkillFile()
	{
		try 
		{
			ObjectInputStream reader = new ObjectInputStream(new FileInputStream("./skill.obj"));
			this.skill = (List<Skill>)reader.readObject();
			reader.close();
		}
		catch(Exception e) {e.printStackTrace();}
	}

	public void writeFile()
	{
		try 
		{
			ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream("./enemy.obj"));
			writer.writeObject(this.enemy);
			writer.flush();
			writer.close();
		}
		catch(Exception e) {e.printStackTrace();}
	}
	
	public void print()
	{
		try 
		{		
			PrintWriter fileWriter = new PrintWriter(new FileOutputStream("./allEnemy.txt"));
			Character p;
			for(int i=0 ; i<enemy.size() ; ++i)
			{
				p = this.enemy.get(i);
				fileWriter.println("No."+i);
				fileWriter.println("Name: "+p.getName());
				fileWriter.println("Level: "+p.getLevel().getLevel());
				fileWriter.println("Health: "+p.getHealth().getMaxhealth());
				fileWriter.println("(a= "+p.hGrow+" b= "+p.hBase+")");
				fileWriter.println("Mana: "+p.getMana().getMaxmana());
				fileWriter.println("(a= "+p.manaGrow+" b= "+p.manaBase+")");
				fileWriter.println("Mana recover: "+p.getMana().getRecover());
				fileWriter.println("phyDamage: "+p.getPhyDamage().getDamage());
				fileWriter.println("(a= "+p.pdamGrow+" b= "+p.pdamBase+")");
				fileWriter.println("magDamage: "+p.getMagDamage().getDamage());
				fileWriter.println("(a= "+p.mdamGrow+" b= "+p.mdamBase+")");
				fileWriter.println("phyDefence: "+p.getPhyDefence().getDefence());
				fileWriter.println("(a= "+p.pdefGrow+" b= "+p.pdefBase+")");
				fileWriter.println("magDefence: "+p.getMagDefence().getDefence());
				fileWriter.println("(a= "+p.mdefGrow+" b= "+p.mdefBase+")");
				fileWriter.println("Speed: "+p.getSpeed().getBaseSpeed());
				for(int j=0 ; j < p.getSkillEquip().getSkillLen() ; ++j)
				{
					if(p.getSkillEquip().getSkill(j)==null)
						fileWriter.println("Skill "+j+": null");
					else
						fileWriter.println("Skill "+j+": " + p.getSkillEquip().getSkill(j).getName());
				}
				fileWriter.println();
			}
			fileWriter.flush();
			fileWriter.close();
		}
		catch(Exception e) {e.printStackTrace();}
	}

	public void add()
	{
		Character p = new Character("Mumi", new Health(), new Mana(), new PhyDamage(), new MagDamage(), 
				new PhyDefence(), new MagDefence(), new SkillEquip(), new Speed(), new Level(), new Exp(),
				1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1);
		enemy.add(p);
	}
	
	public void search(int i)
	{
		Character p = this.enemy.get(i);
		
		System.out.println("No."+i);
		System.out.println("Name: "+p.getName());
		System.out.println("Level: "+p.getLevel().getLevel());
		System.out.println("Health: "+p.getHealth().getMaxhealth());
		System.out.println("(a= "+p.hGrow+" b= "+p.hBase+")");
		System.out.println("Mana: "+p.getMana().getMaxmana());
		System.out.println("(a= "+p.manaGrow+" b= "+p.manaBase+")");
		System.out.println("Mana recover: "+p.getMana().getRecover());
		System.out.println("phyDamage: "+p.getPhyDamage().getDamage());
		System.out.println("(a= "+p.pdamGrow+" b= "+p.pdamBase+")");
		System.out.println("magDamage: "+p.getMagDamage().getDamage());
		System.out.println("(a= "+p.mdamGrow+" b= "+p.mdamBase+")");
		System.out.println("phyDefence: "+p.getPhyDefence().getDefence());
		System.out.println("(a= "+p.pdefGrow+" b= "+p.pdefBase+")");
		System.out.println("magDefence: "+p.getMagDefence().getDefence());
		System.out.println("(a= "+p.mdefGrow+" b= "+p.mdefBase+")");
		System.out.println("Speed: "+p.getSpeed().getBaseSpeed());
		for(int j=0 ; j < p.getSkillEquip().getSkillLen() ; ++j)
		{
			if(p.getSkillEquip().getSkill(j)==null)
				System.out.println("Skill "+j+": null");
			else
				System.out.println("Skill "+j+": " + p.getSkillEquip().getSkill(j).getName());
		}
		System.out.println();
	}
	
	public void changeName(String str)
	{
		StringTokenizer st = new StringTokenizer(str, " ");
		try 
		{
			String str1 = st.nextToken();
			String str2 = st.nextToken();
			int index = Integer.valueOf(str1);
			if(index>=0 && index<enemy.size())
			{
				enemy.get(index).setName(str2);
			}
		}
		catch(Exception e) {e.printStackTrace();}
	}
	
	public void changeLevel(String str)
	{
		StringTokenizer st = new StringTokenizer(str, " ");
		try 
		{
			String str1 = st.nextToken();
			String str2 = st.nextToken();
			int index = Integer.valueOf(str1);
			int num = Integer.valueOf(str2);
			if(index>=0 && index<enemy.size())
			{
				enemy.get(index).getLevel().setLevel(num);
				enemy.get(index).setPropertybyFormula();
			}
		}
		catch(Exception e) {e.printStackTrace();}
	}
	
	public void changeHG(String str)
	{
		StringTokenizer st = new StringTokenizer(str, " ");
		try 
		{
			String str1 = st.nextToken();
			String str2 = st.nextToken();
			int index = Integer.valueOf(str1);
			double num = Double.valueOf(str2);
			if(index>=0 && index<enemy.size())
			{
				enemy.get(index).hGrow = num;
				enemy.get(index).setPropertybyFormula();
			}
		}
		catch(Exception e) {e.printStackTrace();}
	}
	
	public void changeHB(String str)
	{
		StringTokenizer st = new StringTokenizer(str, " ");
		try 
		{
			String str1 = st.nextToken();
			String str2 = st.nextToken();
			int index = Integer.valueOf(str1);
			double num = Double.valueOf(str2);
			if(index>=0 && index<enemy.size())
			{
				enemy.get(index).hBase = num;
				enemy.get(index).setPropertybyFormula();
			}
		}
		catch(Exception e) {e.printStackTrace();}
	}
	
	public void changeMG(String str)
	{
		StringTokenizer st = new StringTokenizer(str, " ");
		try 
		{
			String str1 = st.nextToken();
			String str2 = st.nextToken();
			int index = Integer.valueOf(str1);
			double num = Double.valueOf(str2);
			if(index>=0 && index<enemy.size())
			{
				enemy.get(index).manaGrow = num;
				enemy.get(index).setPropertybyFormula();
			}
		}
		catch(Exception e) {e.printStackTrace();}
	}
	
	public void changeMB(String str)
	{
		StringTokenizer st = new StringTokenizer(str, " ");
		try 
		{
			String str1 = st.nextToken();
			String str2 = st.nextToken();
			int index = Integer.valueOf(str1);
			double num = Double.valueOf(str2);
			if(index>=0 && index<enemy.size())
			{
				enemy.get(index).manaBase = num;
				enemy.get(index).setPropertybyFormula();
			}
		}
		catch(Exception e) {e.printStackTrace();}
	}
	
	public void changePG(String str)
	{
		StringTokenizer st = new StringTokenizer(str, " ");
		try 
		{
			String str1 = st.nextToken();
			String str2 = st.nextToken();
			int index = Integer.valueOf(str1);
			double num = Double.valueOf(str2);
			if(index>=0 && index<enemy.size())
			{
				enemy.get(index).pdamGrow = num;
				enemy.get(index).setPropertybyFormula();
			}
		}
		catch(Exception e) {e.printStackTrace();}
	}
	
	public void changePB(String str)
	{
		StringTokenizer st = new StringTokenizer(str, " ");
		try 
		{
			String str1 = st.nextToken();
			String str2 = st.nextToken();
			int index = Integer.valueOf(str1);
			double num = Double.valueOf(str2);
			if(index>=0 && index<enemy.size())
			{
				enemy.get(index).pdamBase = num;
				enemy.get(index).setPropertybyFormula();
			}
		}
		catch(Exception e) {e.printStackTrace();}
	}
	
	public void changeMDG(String str)
	{
		StringTokenizer st = new StringTokenizer(str, " ");
		try 
		{
			String str1 = st.nextToken();
			String str2 = st.nextToken();
			int index = Integer.valueOf(str1);
			double num = Double.valueOf(str2);
			if(index>=0 && index<enemy.size())
			{
				enemy.get(index).mdamGrow = num;
				enemy.get(index).setPropertybyFormula();
			}
		}
		catch(Exception e) {e.printStackTrace();}
	}
	
	public void changeMDB(String str)
	{
		StringTokenizer st = new StringTokenizer(str, " ");
		try 
		{
			String str1 = st.nextToken();
			String str2 = st.nextToken();
			int index = Integer.valueOf(str1);
			double num = Double.valueOf(str2);
			if(index>=0 && index<enemy.size())
			{
				enemy.get(index).mdamBase = num;
				enemy.get(index).setPropertybyFormula();
			}
		}
		catch(Exception e) {e.printStackTrace();}
	}
	
	public void changePDEG(String str)
	{
		StringTokenizer st = new StringTokenizer(str, " ");
		try 
		{
			String str1 = st.nextToken();
			String str2 = st.nextToken();
			int index = Integer.valueOf(str1);
			double num = Double.valueOf(str2);
			if(index>=0 && index<enemy.size())
			{
				enemy.get(index).pdefGrow = num;
				enemy.get(index).setPropertybyFormula();
			}
		}
		catch(Exception e) {e.printStackTrace();}
	}
	
	public void changePDEB(String str)
	{
		StringTokenizer st = new StringTokenizer(str, " ");
		try 
		{
			String str1 = st.nextToken();
			String str2 = st.nextToken();
			int index = Integer.valueOf(str1);
			double num = Double.valueOf(str2);
			if(index>=0 && index<enemy.size())
			{
				enemy.get(index).pdefBase = num;
				enemy.get(index).setPropertybyFormula();
			}
		}
		catch(Exception e) {e.printStackTrace();}
	}
	
	public void changeMDEG(String str)
	{
		StringTokenizer st = new StringTokenizer(str, " ");
		try 
		{
			String str1 = st.nextToken();
			String str2 = st.nextToken();
			int index = Integer.valueOf(str1);
			double num = Double.valueOf(str2);
			if(index>=0 && index<enemy.size())
			{
				enemy.get(index).mdefGrow = num;
				enemy.get(index).setPropertybyFormula();
			}
		}
		catch(Exception e) {e.printStackTrace();}
	}
	
	public void changeMDEB(String str)
	{
		StringTokenizer st = new StringTokenizer(str, " ");
		try 
		{
			String str1 = st.nextToken();
			String str2 = st.nextToken();
			int index = Integer.valueOf(str1);
			double num = Double.valueOf(str2);
			if(index>=0 && index<enemy.size())
			{
				enemy.get(index).mdefBase = num;
				enemy.get(index).setPropertybyFormula();
			}
		}
		catch(Exception e) {e.printStackTrace();}
	}
	public void changeSP(String str)
	{
		StringTokenizer st = new StringTokenizer(str, " ");
		try 
		{
			String str1 = st.nextToken();
			String str2 = st.nextToken();
			int index = Integer.valueOf(str1);
			double num = Double.valueOf(str2);
			if(index>=0 && index<enemy.size())
			{
				enemy.get(index).getSpeed().setBaseSpeed(num); 
			}
		}
		catch(Exception e) {e.printStackTrace();}
	}
	public void changeSK(String str)
	{
		StringTokenizer st = new StringTokenizer(str, " ");
		try 
		{
			String str1 = st.nextToken();
			String str2 = st.nextToken();
			String str3 = st.nextToken();
			int index = Integer.valueOf(str1);
			int indexEqu = Integer.valueOf(str2);
			int indexSki = Integer.valueOf(str3);
			if(index>=0 && index<enemy.size())
			{
				enemy.get(index).getSkillEquip().setSkill(indexEqu, this.skill.get(indexSki));
			}
		}
		catch(Exception e) {e.printStackTrace();}
	}
}
