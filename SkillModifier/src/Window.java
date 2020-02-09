import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import skill.*;



public class Window extends JFrame implements ActionListener{
	int index;
	List<Skill> skill = new ArrayList<>();
	
	JTextField skNameText = new JTextField(15); JLabel skNameValue = new JLabel();
	JTextField bdAttDou = new JTextField(15);   JLabel bdAttValue = new JLabel();
	JTextField pmAttDou = new JTextField(15);   JLabel pmAttValue = new JLabel();
	JTextField mmAttDou = new JTextField(15);	JLabel mmAttValue = new JLabel();
	JTextField AttEfText = new JTextField(15);	JLabel AttEfValue = new JLabel();
	JTextField bhHealDou = new JTextField(15);  JLabel bhHealValue = new JLabel();
	JTextField pmHealDou = new JTextField(15);  JLabel pmHealValue = new JLabel();
	JTextField mmHealDou = new JTextField(15);  JLabel mmHealValue = new JLabel();
	JTextField TargetText = new JTextField(15); JLabel TargetValue = new JLabel();
	JTextField manaCostDou = new JTextField(15); JLabel manaCostValue = new JLabel();
	JTextField descrText = new JTextField(25); JLabel descrValue = new JLabel();
	JTextArea ObjInformation = new JTextArea(30, 50);
	
	private int currentIndex; 
	JLabel currentIndexLb = new JLabel(); JLabel listLenLb = new JLabel();
	JTextField searchIndex = new JTextField(8);
	
	JButton addObjBtn = new JButton("增加物件"); JButton writeObjBtn = new JButton("寫入物件");
	JButton searchBtn = new JButton("搜尋");
	JButton fromFileBtn = new JButton("從檔案載入"); JButton toFileBtn = new JButton("儲存進檔案");
	JButton printFileBtn = new JButton("Print out");
	
	//Set UI
	public Window() {
		setSize(1500, 1000);
		setLayout(null);
		JLabel lb = new JLabel();
		lb.setSize(100,25);
		lb.setText("Skill name");
		skNameText.setSize(skNameText.getPreferredSize());
		lb.setLocation(10,10);
		skNameText.setLocation(240,10);
		
		JLabel lb1 = new JLabel();
		lb1.setText("Attack");
		lb1.setSize(100,25);
		lb1.setLocation(10,90);
		JLabel lb2 = new JLabel();
		lb2.setText("baseDamage");
		lb2.setSize(100,25);
		lb2.setLocation(10,140);
		bdAttDou.setSize(bdAttDou.getPreferredSize());
		bdAttDou.setLocation(210,140);
		JLabel lb3 = new JLabel();
		lb3.setText("phyMult");
		lb3.setSize(100,25);
		lb3.setLocation(10,190);
		pmAttDou.setSize(pmAttDou.getPreferredSize());
		pmAttDou.setLocation(210,190);
		JLabel lb4 = new JLabel();
		lb4.setText("magMult");
		lb4.setSize(100,25);
		lb4.setLocation(10,240);
		mmAttDou.setSize(mmAttDou.getPreferredSize());
		mmAttDou.setLocation(210,240);
		JLabel lb5 = new JLabel();
		lb5.setText("effectType");
		lb5.setSize(100,25);
		lb5.setLocation(10,290);
		AttEfText.setSize(AttEfText.getPreferredSize());
		AttEfText.setLocation(210,290);
		
		JLabel lb6 = new JLabel();
		lb6.setText("Heal");
		lb6.setSize(100,25);
		lb6.setLocation(10,370);
		JLabel lb7 = new JLabel();
		lb7.setText("baseHeal");
		lb7.setSize(100,25);
		lb7.setLocation(10,420);
		bhHealDou.setSize(bhHealDou.getPreferredSize());
		bhHealDou.setLocation(210,420);
		JLabel lb8 = new JLabel();
		lb8.setText("phyMult");
		lb8.setSize(100,25);
		lb8.setLocation(10,470);
		pmHealDou.setSize(pmHealDou.getPreferredSize());
		pmHealDou.setLocation(210,470);
		JLabel lb9 = new JLabel();
		lb9.setText("magMult");
		lb9.setSize(100,25);
		lb9.setLocation(10,520);
		mmHealDou.setSize(mmHealDou.getPreferredSize());
		mmHealDou.setLocation(210,520);
		
		JLabel lb10 = new JLabel();
		lb10.setText("TargetType");
		lb10.setSize(100,25);
		lb10.setLocation(10,600);
		TargetText.setSize(TargetText.getPreferredSize());
		TargetText.setLocation(210,600);
		
		JLabel lb11 = new JLabel();
		lb11.setText("manaConsume");	
		lb11.setSize(100,25);
		lb11.setLocation(10,680);
		manaCostDou.setSize(manaCostDou.getPreferredSize());
		manaCostDou.setLocation(210,680);
		
		JLabel lb12 = new JLabel();
		lb12.setText("description");	
		lb12.setSize(100,25);
		lb12.setLocation(10,730);
		descrText.setSize(descrText.getPreferredSize());
		descrText.setLocation(10,780);
		
		//Skill
		add(lb);
		add(skNameText);
		
		//Attack
		add(lb1);
		add(lb2);
		add(bdAttDou);
		add(lb3);
		add(pmAttDou);
		add(lb4);
		add(mmAttDou);
		add(lb5);
		add(AttEfText);
		
		//Heal
		add(lb6);
		add(lb7);
		add(bhHealDou);
		add(lb8);
		add(pmHealDou);
		add(lb9);
		add(mmHealDou);
		
		//TargetType
		add(lb10);
		add(TargetText);
		
		//ManaCost
		add(lb11);
		add(manaCostDou);
		
		//description
		add(lb12);
		add(descrText);
		
		initialCurrentIndex();
		setCurrentValue();
		
		// Current Value of Skill elements (label)
		skNameValue.setLocation(110,10);
		bdAttValue.setLocation(110,140);
		pmAttValue.setLocation(110,190);
		mmAttValue.setLocation(110,240);
		AttEfValue.setLocation(110,290);
		bhHealValue.setLocation(110,420);
		pmHealValue.setLocation(110,470);
		mmHealValue.setLocation(110,520);
		TargetValue.setLocation(110,600);
		manaCostValue.setLocation(110,680);
		descrValue.setLocation(110,730);
		
		add(skNameValue);
		add(bdAttValue);
		add(pmAttValue);
		add(mmAttValue);
		add(AttEfValue);
		add(bhHealValue);
		add(pmHealValue);
		add(mmHealValue);
		add(TargetValue);
		add(manaCostValue);
		add(descrValue);
		
		//set & show Current Index label & List Length label
		add(currentIndexLb);
		add(listLenLb);
		showCurrentIndex_ListLen();
		
		//search field
		searchIndex.setLocation(450,120);
		searchIndex.setSize(searchIndex.getPreferredSize());
		searchBtn.setLocation(570,115);
		searchBtn.setSize(80, 30);
		searchBtn.addActionListener(this);
		add(searchIndex);
		add(searchBtn);
		
		//set Btns
		addObjBtn.setLocation(20, 880);
		addObjBtn.setSize(150, 30);
		addObjBtn.addActionListener(this);
		writeObjBtn.setLocation(220, 880);
		writeObjBtn.setSize(150, 30);
		writeObjBtn.addActionListener(this);
		fromFileBtn.setLocation(400,280);
		fromFileBtn.setSize(150, 30);
		fromFileBtn.addActionListener(this);
		toFileBtn.setLocation(600,280);
		toFileBtn.setSize(150, 30);
		toFileBtn.addActionListener(this);
		printFileBtn.setLocation(500,380);
		printFileBtn.setSize(150, 30);
		printFileBtn.addActionListener(this);
		add(addObjBtn);
		add(writeObjBtn);
		add(fromFileBtn);
		add(toFileBtn);	
		add(printFileBtn);	
		
		//all Obj information field
		JScrollPane scrolledText = new JScrollPane(ObjInformation);
		scrolledText.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrolledText.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrolledText.setLocation(850, 150);
		scrolledText.setSize(scrolledText.getPreferredSize());
		add(scrolledText);
	}
	
	public void read() {
		try {
			ObjectInputStream reader = new ObjectInputStream(new FileInputStream("./skill.obj"));
			skill = (List<Skill>)reader.readObject();
			reader.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void write() {
		try {
			ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream("./skill.obj"));
			writer.writeObject(skill);
			writer.flush();
			writer.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/*public void addSkill() {
		skill.add(new Skill(new Attack(10.0,1.0,0.0,"phy"),new Heal(),new TargetType("single_enemy"),"DefaultAttack"));
		skill.add(new Skill(new Attack(),new Heal(10.0,0.0,1.0),new TargetType("single_ally"),"Heal"));
		write();
	}*/
	
	public void print() {
		ObjInformation.setText("");
		String str = "";
		for(int i = 0;i<skill.size();++i) {
			str+="Skill No. "+i+":\n";
			str+=skill.get(i).print();
			str+="\n";
		}
		ObjInformation.setText(str);
	}
	
	public void initialCurrentIndex()
	{
		if(skill.size()<=0)
			currentIndex = -1;
		else
			currentIndex = 0;
	}
	
	public void showCurrentIndex_ListLen()
	{
		if(skill.size()<=0)
		{
			currentIndexLb.setText("當前List為空");
			currentIndexLb.setSize(currentIndexLb.getPreferredSize());
			currentIndexLb.setLocation(450,10);
		}
		else
		{
			currentIndexLb.setText("當前物件編號: " + currentIndex);
			currentIndexLb.setSize(currentIndexLb.getPreferredSize());
			currentIndexLb.setLocation(450,10);
		}
		listLenLb.setText("List Length: " + skill.size());
		listLenLb.setSize(listLenLb.getPreferredSize());
		listLenLb.setLocation(450,60);
	}
	
	public void setCurrentValue() {
		if(skill.size()<=0) 
		{
			skNameValue.setText("--");
			skNameValue.setSize(skNameValue.getPreferredSize());
			bdAttValue.setText("--");
			bdAttValue.setSize(bdAttValue.getPreferredSize());
			pmAttValue.setText("--");
			pmAttValue.setSize(pmAttValue.getPreferredSize());
			mmAttValue.setText("--");
			mmAttValue.setSize(mmAttValue.getPreferredSize());
			AttEfValue.setText("--");
			AttEfValue.setSize(AttEfValue.getPreferredSize());
			bhHealValue.setText("--");
			bhHealValue.setSize(bhHealValue.getPreferredSize());
			pmHealValue.setText("--");
			pmHealValue.setSize(pmHealValue.getPreferredSize());
			mmHealValue.setText("--");
			mmHealValue.setSize(mmHealValue.getPreferredSize());
			TargetValue.setText("--");
			TargetValue.setSize(TargetValue.getPreferredSize());
			manaCostValue.setText("--");
			manaCostValue.setSize(manaCostValue.getPreferredSize());
			descrValue.setText("--");
			descrValue.setSize(descrValue.getPreferredSize());
		}
		else
		{
			double d;
			skNameValue.setText(skill.get(currentIndex).getName());
			skNameValue.setSize(skNameValue.getPreferredSize());
			d = skill.get(currentIndex).getAttack().getBaseDamage();
			bdAttValue.setText(Double.toString(d));
			bdAttValue.setSize(bdAttValue.getPreferredSize());
			d = skill.get(currentIndex).getAttack().getPhyMult();
			pmAttValue.setText(Double.toString(d));
			pmAttValue.setSize(pmAttValue.getPreferredSize());
			d = skill.get(currentIndex).getAttack().getMagMult();
			mmAttValue.setText(Double.toString(d));
			mmAttValue.setSize(mmAttValue.getPreferredSize());
			AttEfValue.setText(skill.get(currentIndex).getAttack().getEffectType().getType());
			AttEfValue.setSize(AttEfValue.getPreferredSize());
			d = skill.get(currentIndex).getHeal().getBaseHeal();
			bhHealValue.setText(Double.toString(d));
			bhHealValue.setSize(bhHealValue.getPreferredSize());
			d = skill.get(currentIndex).getHeal().getPhyMult();
			pmHealValue.setText(Double.toString(d));
			pmHealValue.setSize(pmHealValue.getPreferredSize());
			d = skill.get(currentIndex).getHeal().getMagMult();
			mmHealValue.setText(Double.toString(d));
			mmHealValue.setSize(mmHealValue.getPreferredSize());
			TargetValue.setText(skill.get(currentIndex).getTargetType().getType());
			TargetValue.setSize(TargetValue.getPreferredSize());
			d = skill.get(currentIndex).getManaConsume();
			manaCostValue.setText(Double.toString(d));
			manaCostValue.setSize(manaCostValue.getPreferredSize());
			descrValue.setText(skill.get(currentIndex).getDescription());
			descrValue.setSize(descrValue.getPreferredSize());
		}
	}
	
	public void actionPerformed(ActionEvent e)
	{
		String command = e.getActionCommand();
		if(command.equals("增加物件"))
		{
			skill.add(new Skill(new Attack(10.0,1.0,0.0,"phy"),new Heal(),new TargetType("single_enemy"),0,
					"DefaultAttack","Nothing"));
			currentIndex = skill.size()-1;
			setCurrentValue();
			showCurrentIndex_ListLen();
		}
		else if(command.equals("寫入物件"))
		{
			try 
			{
				if(currentIndex>=0 && currentIndex<skill.size()) 
				{
					String skName = (skNameText.getText().equals(""))? 
							skill.get(currentIndex).getName() : skNameText.getText();
							
					double attbd = (bdAttDou.getText().equals(""))? 
							skill.get(currentIndex).getAttack().getBaseDamage() : Double.valueOf(bdAttDou.getText());
							
					double attpm = (pmAttDou.getText().equals(""))? 
							skill.get(currentIndex).getAttack().getPhyMult() : Double.valueOf(pmAttDou.getText());
							
					double attmm = (mmAttDou.getText().equals(""))? 
							skill.get(currentIndex).getAttack().getMagMult() : Double.valueOf(mmAttDou.getText());
							
					String effType = (AttEfText.getText().equals(""))? 
							skill.get(currentIndex).getAttack().getEffectType().getType() : AttEfText.getText();
							
					double healbh = (bhHealDou.getText().equals(""))? 
							skill.get(currentIndex).getHeal().getBaseHeal() : Double.valueOf(bhHealDou.getText());
							
					double healpm = (pmHealDou.getText().equals(""))?
							skill.get(currentIndex).getHeal().getPhyMult() : Double.valueOf(pmHealDou.getText());
							
					double healmm = (mmHealDou.getText().equals(""))?
							skill.get(currentIndex).getHeal().getMagMult() : Double.valueOf(mmHealDou.getText());
							
					String tarType = (TargetText.getText().equals(""))?
							skill.get(currentIndex).getTargetType().getType() : TargetText.getText();
							
					double manaCost = (manaCostDou.getText().equals(""))? 
							skill.get(currentIndex).getManaConsume() : Double.valueOf(manaCostDou.getText());
					String descr = (descrText.getText().equals(""))?
							skill.get(currentIndex).getDescription() : descrText.getText();
					
					skill.get(currentIndex).setName(skName);
					Attack att = new Attack(attbd,attpm,attmm,effType);
					skill.get(currentIndex).setAttack(att);
					skill.get(currentIndex).getHeal().setBaseHeal(healbh);
					skill.get(currentIndex).getHeal().setPhyMult(healpm);
					skill.get(currentIndex).getHeal().setMagMult(healmm);
					skill.get(currentIndex).getTargetType().setType(tarType);
					skill.get(currentIndex).setManaConsume(manaCost);
					skill.get(currentIndex).setDescription(descr);
					setCurrentValue();
				}
			}
			catch(Exception ex) {ex.printStackTrace();}
		}
		else if(command.equals("搜尋")) 
		{
			try 
			{
				int i = Integer.valueOf(searchIndex.getText());
				if(i>=0 && i<skill.size())
					currentIndex = i;
				setCurrentValue();
				showCurrentIndex_ListLen();
			}
			catch(Exception ex) {ex.printStackTrace();}
		}
		else if(command.equals("從檔案載入"))
		{
			read();
			initialCurrentIndex();
			showCurrentIndex_ListLen();
			setCurrentValue();
		}
		else if(command.equals("儲存進檔案")) 
		{
			write();
		}
		else if(command.equals("Print out")) 
		{
			this.print();
		}
	}
}