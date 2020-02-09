import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.*;
import character.*;
import character.Character;
import skill.Skill;

public class GamePanel extends TimerTask implements ActionListener{
	JFrame gameFrame;
	JPanel jp1,jp2,jp3,battlepanel,startPanel,dungeonPanel,eventPanel,informationPanel;
	JLabel bar,notice,jl4,chpicture,chname,background,eventPicture,askContent;
	JButton startBtn,road_left,road_mid,road_right,informationBtn,backBtn,continueBtn,switchbtn;
	JButton[] changePlayer;
	List<JButton> playerbtn,enemy,skill;
	List<JLabel> playerLb,enemyLb,enemySmallIcon,playerBlood,playerMana,enemyBlood,enemyMana;
	List<Character> allPlayer,allEnemy,list;
	ImageIcon barImg,img2;
	JTextArea chstate,chinformation,eventtext;
	int FRAME_WIDTH,FRAME_HEIGHT,BAR_WIDTH,BAR_LOCATION_X,BAR_LOCATION_Y,BAR_HEIGHT,PLAYER_WIDTH,PLAYER_HEIGHT,ENEMY_WIDTH,ENEMY_HEIGHT,SKILL_WIDTH,SKILL_HEIGHT,activePlayer,currentSkill,currentTarget,activeEnemy,currentTargetPlayer,BLOCK_WIDTH,BLOCK_HEIGHT;
	int[] PLAYER_LOCATION_X,PLAYER_LOCATION_Y,ENEMY_LOCATION_X,ENEMY_LOCATION_Y,SKILL_LOCATION_X,SKILL_LOCATION_Y,PLAYER_BLOOD_LOCATION_X,PLAYER_BLOOD_LOCATION_Y,PLAYER_MANA_LOCATION_X,PLAYER_MANA_LOCATION_Y,ENEMY_BLOOD_LOCATION_X,ENEMY_BLOOD_LOCATION_Y,ENEMY_MANA_LOCATION_X,ENEMY_MANA_LOCATION_Y,chooseEnemy;
	double[] currentPlayerSpeed,currentEnemySpeed;
	boolean isSelectSkill,isSelectTarget,isBattle,showInformation,isAttack;
	boolean[] PlayerIsAlive,EnemyIsAlive;
	String[] smallPlayerPic,playerLabelPic,smallEnemyPic,enemyLabelPic;
	String[][] enemyPic,playerPic;
	DecimalFormat df;
	Timer timer;
	Random rand;
	Character[] currentPlayer,currentEnemy;

	JButton defaultBtn = new JButton("3 基礎攻擊");
	
	public GamePanel(){
		FRAME_WIDTH = 1200;
		FRAME_HEIGHT = 700;
		//for bar
		BAR_WIDTH = 1000;
		BAR_LOCATION_X = (FRAME_WIDTH-BAR_WIDTH)/2;
		BAR_LOCATION_Y = 50;
		BAR_HEIGHT = 40;
		
		//for playerbtn
		PLAYER_WIDTH = 120;
		PLAYER_HEIGHT = 136;
		
		//for enemy
		ENEMY_WIDTH = 120;
		ENEMY_HEIGHT = 136;
		
		//for skill
		SKILL_WIDTH = 200;
		SKILL_HEIGHT = 50;
		battlepanel = new JPanel();
		rand = new Random();
		bar = new JLabel("");
		barImg= new ImageIcon("./image/bar.png");
		playerbtn = new ArrayList<JButton>();
		PLAYER_LOCATION_X = new int[] {250,390,250};
		PLAYER_LOCATION_Y = new int[] {190,270,370};
		PlayerIsAlive = new boolean[] {true,true,true};
		playerLb = new ArrayList<>();
		enemy = new ArrayList<JButton>();
		ENEMY_LOCATION_X = new int[] {750,600,750};
		ENEMY_LOCATION_Y = new int[] {190,270,370};
		EnemyIsAlive = new boolean[] {true,true,true};
		enemyLb = new ArrayList<>();
		skill = new ArrayList<JButton>();
		SKILL_LOCATION_X = new int[] {250,450,650,850};
		SKILL_LOCATION_Y = new int[] {600,600,600,600};
		allPlayer = new ArrayList<Character>();
		currentPlayer = new Character[3];
		allEnemy = new ArrayList<Character>();
		currentEnemy = new Character[3];
		currentPlayerSpeed = new double[] {0,0,0};
		currentEnemySpeed = new double[] {0,0,0};
		notice = new JLabel("");
		enemySmallIcon = new ArrayList<>(); //enemysmallicon
		playerBlood = new ArrayList<>();
		playerMana = new ArrayList<>();
		PLAYER_BLOOD_LOCATION_X = new int[] {70,70,70};
		PLAYER_BLOOD_LOCATION_Y = new int[] {0,50,100};
		PLAYER_MANA_LOCATION_X = new int[] {70,70,70};
		PLAYER_MANA_LOCATION_Y = new int[] {25,75,125};
		enemyBlood = new ArrayList<>();
		enemyMana = new ArrayList<>();
		ENEMY_BLOOD_LOCATION_X = new int[] {25,25,25};
		ENEMY_BLOOD_LOCATION_Y = new int[] {0,50,100};
		ENEMY_MANA_LOCATION_X = new int[] {25,25,25};
		ENEMY_MANA_LOCATION_Y = new int[] {25,75,125};
		BLOCK_WIDTH = 100;
		BLOCK_HEIGHT = 50;

		chooseEnemy = new int[3];
		enemyPic = new String[7][2]; //be attacked and idle
		playerPic = new String[3][2];
		smallPlayerPic = new String[] {"./image/char/archer0.png","./image/char/warrior0.png","./image/char/magician0.png"};
		playerLabelPic = new String[] {"./image/char/icon/p1.png","./image/char/icon/p2.png","./image/char/icon/p3.png"};
		smallEnemyPic = new String[7];
		enemyLabelPic = new String[7];
		jp1 = new JPanel(); // playerbtn small icon
		jp2 = new JPanel();
		jp3 = new JPanel();
		img2 = new ImageIcon("./image/panel2.png");
		jl4 = new JLabel(img2);

	}
	
	public void construct() {
		df = new DecimalFormat("0.##");
		readData();
		isBattle = false;
		isAttack = false;
		//mainFrame
		gameFrame = new JFrame();
		gameFrame.setSize(1200, 700);
		gameFrame.setLayout(null);
		gameFrame.setTitle("Dungeon Story");
		gameFrame.setVisible(true);
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//startPanelStartBtn
		startBtn = new JButton();
		startBtn.setSize(1200,700);
		startBtn.setText("start");
		startBtn.addActionListener(this);
		
		//battlepanel
		battlepanel.setSize(1200,700);
		battlepanel.setLayout(null);
		battlepanel.setVisible(false);
		
		jp1.setBounds(0, 130, 170, 170);
		jp1.setBackground(Color.gray);
		jp1.setLayout(null);
		battlepanel.add(jp1);
		
		jp2.setBounds(1000, 130, 170, 170);
		jp2.setBackground(Color.gray);
		jp2.setLayout(null);
		battlepanel.add(jp2);
		
		jp3.setLayout(null);
		jp3.setOpaque(false);
		jp3.setBackground(Color.black);
		jp3.setBounds(350, 500, 500, 110);
		img2.setImage(img2.getImage().getScaledInstance(jp3.getWidth(), jp3.getHeight(),Image.SCALE_DEFAULT));
		jl4.setBounds(0, 0, jp3.getWidth(), jp3.getHeight());
		notice.setBounds(50, 0, 500, 110);	
		jp3.add(notice);
		jp3.add(jl4);
		battlepanel.add(jp3);
		
		isSelectSkill = false;
		isSelectTarget = false;
		showInformation = true;	
		
		playerPic[0][0] = "./image/char/archer1.png";
		playerPic[1][0] = "./image/char/warrior1.png";
		playerPic[2][0] = "./image/char/magician1.png";
		playerPic[0][1] = "./image/char/archer2.png";
		playerPic[1][1] = "./image/char/warrior2.png";
		playerPic[2][1] = "./image/char/magician2.png";
		
		for(int i=0;i<7;++i) {
			smallEnemyPic[i] = "./image/enemy/e0" + (i+1) + "_" + 0 + ".png";
			enemyLabelPic[i] = "./image/enemy/icon/e0" + (i+1) + ".png";
			for(int j=0;j<2;++j) {
				enemyPic[i][j] = "./image/enemy/e0" + (i+1) + "_" + (j+1) + ".png";
			}
		}
		
		barImg.setImage(barImg.getImage().getScaledInstance(BAR_WIDTH,BAR_HEIGHT,Image.SCALE_DEFAULT));
			
		//new object and store in arraylist
		for(int i=0; i<3; i++) {
			ImageIcon img = new ImageIcon(playerPic[i][0]);
			ImageIcon smallImg = new ImageIcon(smallPlayerPic[i]);
			img.setImage(img.getImage().getScaledInstance(PLAYER_WIDTH,PLAYER_HEIGHT,Image.SCALE_DEFAULT));
			smallImg.setImage(smallImg.getImage().getScaledInstance(40,40,Image.SCALE_DEFAULT));
			
			JLabel l = new JLabel(Integer.toString(i));
			JLabel ll = new JLabel(Integer.toString(i+3));
			JButton b = new JButton(img);
			JButton bb = new JButton();
			JButton s = new JButton(Integer.toString(i) +" :1234");
			
			JLabel sl = new JLabel(smallImg);
			sl.setBounds(PLAYER_BLOOD_LOCATION_X[i] - 50, PLAYER_BLOOD_LOCATION_Y[i] + 15, 40 ,40);
			jp1.add(sl);
			
			JLabel sll = new JLabel();
			enemySmallIcon.add(sll);
			jp2.add(sll);
			
			ImageIcon playerLabelImg = new ImageIcon(playerLabelPic[i]);
			playerLabelImg.setImage(playerLabelImg.getImage().getScaledInstance(35,35,Image.SCALE_DEFAULT));
			l.setBackground(Color.BLUE);
			l.setOpaque(false);
			l.setIcon(playerLabelImg);
			ll.setBackground(Color.GREEN);
			ll.setOpaque(false);
			//set playerbtn,enemy picture
			b.setText("P"+Integer.toString(i));
			b.setBorder(null);
			b.setOpaque(false);
			b.setBackground(Color.BLUE);
			
			bb.setText("E"+Integer.toString(i+3));
			bb.setBorder(null);
			bb.setOpaque(false);
			bb.setBackground(Color.BLUE);
			
			//s.setBackground(Color.blue);
			JLabel pblood = new JLabel(Double.toString(allPlayer.get(i).getHealth().getCurrenthealth()) + "/"+allPlayer.get(i).getHealth().getMaxhealth());
			JLabel pmana = new JLabel(Double.toString(allPlayer.get(i).getMana().getCurrentmana()) + "/"+allPlayer.get(i).getMana().getMaxmana());
			JLabel eblood = new JLabel();
			//Double.toString(currentEnemy[i].health.getCurrenthealth()) + "/"+currentEnemy[i].health.getMaxhealth()
			JLabel emana = new JLabel();
			//Double.toString(currentEnemy[i].mana.getCurrentmana()) + "/"+currentEnemy[i].mana.getMaxmana()
			pblood.setForeground(Color.red);
			pmana.setForeground(Color.blue);
			eblood.setForeground(Color.red);
			emana.setForeground(Color.blue);
			
			playerLb.add(l);
			enemyLb.add(ll);
			playerbtn.add(b);
			enemy.add(bb);
			skill.add(s);
			playerBlood.add(pblood);
			playerMana.add(pmana);
			enemyBlood.add(eblood);
			enemyMana.add(emana);
		}

		//set location
		for(int i=0; i<3; i++) {
			playerLb.get(i).setBounds(BAR_LOCATION_X, BAR_LOCATION_Y - 20, 35, 35);
			enemyLb.get(i).setBounds(BAR_LOCATION_X + BAR_WIDTH  - enemyLb.get(i).getWidth(), BAR_LOCATION_Y - 20, 35, 35);			
			battlepanel.add(playerLb.get(i));
			battlepanel.add(enemyLb.get(i));
			
			playerbtn.get(i).addActionListener(this);
			playerbtn.get(i).setBounds(PLAYER_LOCATION_X[i], PLAYER_LOCATION_Y[i], PLAYER_WIDTH, PLAYER_HEIGHT);
			battlepanel.add(playerbtn.get(i));
			
			enemy.get(i).addActionListener(this);
			enemy.get(i).setBounds(ENEMY_LOCATION_X[i], ENEMY_LOCATION_Y[i], ENEMY_WIDTH, ENEMY_HEIGHT);
			battlepanel.add(enemy.get(i));
			
			skill.get(i).setBounds(SKILL_LOCATION_X[i], SKILL_LOCATION_Y[i], SKILL_WIDTH, SKILL_HEIGHT);
			battlepanel.add(skill.get(i));
			skill.get(i).addActionListener(this);
			skill.get(i).setVisible(false);
			
			playerBlood.get(i).setBounds(PLAYER_BLOOD_LOCATION_X[i], PLAYER_BLOOD_LOCATION_Y[i], BLOCK_WIDTH, BLOCK_HEIGHT);
			jp1.add(playerBlood.get(i));
			playerMana.get(i).setBounds(PLAYER_MANA_LOCATION_X[i], PLAYER_MANA_LOCATION_Y[i], BLOCK_WIDTH, BLOCK_HEIGHT);
			jp1.add(playerMana.get(i));
			enemyBlood.get(i).setBounds(ENEMY_BLOOD_LOCATION_X[i], ENEMY_BLOOD_LOCATION_Y[i], BLOCK_WIDTH, BLOCK_HEIGHT);
			jp2.add(enemyBlood.get(i));
			enemyMana.get(i).setBounds(ENEMY_MANA_LOCATION_X[i], ENEMY_MANA_LOCATION_Y[i], BLOCK_WIDTH, BLOCK_HEIGHT);
			jp2.add(enemyMana.get(i));
		}
		skill.add(defaultBtn);
		skill.get(3).setBounds(SKILL_LOCATION_X[3], SKILL_LOCATION_Y[3], SKILL_WIDTH, SKILL_HEIGHT);
		battlepanel.add(skill.get(3));
		skill.get(3).addActionListener(this);
		skill.get(3).setVisible(false);
		bar.setIcon(barImg);
		bar.setBounds(BAR_LOCATION_X, BAR_LOCATION_Y,BAR_WIDTH, BAR_HEIGHT);
		battlepanel.add(bar);
		
		JLabel bgImg = new JLabel(new ImageIcon("./image/battle_back.jpg"));
		bgImg.setBounds(0, 0, FRAME_WIDTH, FRAME_HEIGHT);
		battlepanel.add(bgImg);
		
		ImageIcon pImg = new ImageIcon("./image/panel.png");
		pImg.setImage(pImg.getImage().getScaledInstance(jp1.getWidth(), jp1.getHeight(),Image.SCALE_DEFAULT));
		
		JLabel panelLabel = new JLabel(pImg);		
		panelLabel.setBounds(0, 0, jp1.getWidth(), jp1.getHeight());
		jp1.setOpaque(false);
		jp1.add(panelLabel);
		
		
		ImageIcon pImg1 = new ImageIcon("./image/panel.png");
		pImg1.setImage(pImg1.getImage().getScaledInstance(jp2.getWidth(), jp2.getHeight(),Image.SCALE_DEFAULT));
		JLabel panelLabel1 = new JLabel(pImg1);		
		panelLabel1.setBounds(0, 0, jp2.getWidth(), jp2.getHeight());
		jp2.setOpaque(false);
		jp2.add(panelLabel1);
		gameFrame.add(battlepanel);
		//end attlepanel............................................................................................
		
		//startPanel
		startPanel = new JPanel();
		startPanel.setSize(1200,700);
		startPanel.setLayout(null);
		startPanel.add(startBtn);
		startPanel.setVisible(true);
		
		gameFrame.add(startPanel);
		
		//road_left
		road_left = new JButton();
		road_left.setSize(160,110);
		road_left.setLocation(20, 300);
		road_left.setText("left");
		road_left.setIcon(new ImageIcon("./image/Larrow.PNG"));
		road_left.addActionListener(this);
		//road_mid
		road_mid = new JButton();
		road_mid.setSize(120,160);
		road_mid.setLocation(450, 180);
		road_mid.setText("mid");
		road_mid.setIcon(new ImageIcon("./image/Farrow.PNG"));
		road_mid.addActionListener(this);
		//road_right
		road_right = new JButton();
		road_right.setSize(120,160);
		road_right.setLocation(1000, 200);
		road_right.setText("right");
		road_right.setIcon(new ImageIcon("./image/Farrow.PNG"));
		road_right.addActionListener(this);
		
		//imformationBtn
		informationBtn = new JButton();
		informationBtn.setSize(150,50); //modified
		informationBtn.setLocation(50, 50);
		informationBtn.setText("角色資訊"); //modified
		informationBtn.addActionListener(this);
			
		//ask 
		JLabel askBack = new JLabel();
		askBack.setIcon(new ImageIcon("./image/panel2.PNG"));
		askBack.setSize(400,280);
		askBack.setLocation(350,400);
		askContent = new JLabel();
		askContent.setSize(400,280);
		askContent.setText("請選擇探索方向：");
		askContent.setLocation(400,400);
		
		//background
		background = new JLabel();
		background.setSize(1200,700);
		background.setLocation(0,0);
		background.setIcon(new ImageIcon("./image/探索/explore_back.png"));
		
		//dungeonPanel
		dungeonPanel = new JPanel();
		dungeonPanel.setSize(1200,700);
		dungeonPanel.setLayout(null);
		dungeonPanel.add(road_left);
		dungeonPanel.add(road_mid);
		dungeonPanel.add(road_right);
		dungeonPanel.add(informationBtn);
		dungeonPanel.add(askContent);
		dungeonPanel.add(askBack);
		dungeonPanel.add(background);
		dungeonPanel.setVisible(false);
		gameFrame.add(dungeonPanel);
		
		//backBtn
		backBtn = new JButton();
		backBtn.setSize(80,50);
		backBtn.setLocation(50, 50);
		backBtn.setText("返回");
		backBtn.addActionListener(this);
			
		//switchbtn
		switchbtn = new JButton();
		switchbtn.setSize(100,50);
		switchbtn.setLocation(175, 50);
		switchbtn.setText("顯示技能");
		switchbtn.addActionListener(this);
		
		//changePlayer		
		changePlayer = new JButton[3];
		for(int i = 0;i<3;++i) {
			changePlayer[i] = new JButton();
			changePlayer[i].setIcon(new ImageIcon("./image/char/icon/P" + (i+1) + "_0.PNG"));
			changePlayer[i].setSize(50,50);
			changePlayer[i].setLocation(50 + 75 * i, 125);
			changePlayer[i].setText("changePlayer" + i);
			changePlayer[i].addActionListener(this);
		}

		//chpicture
		chpicture = new JLabel();
		chpicture.setSize(56,56);
		chpicture.setLocation(50, 225);
		chpicture.setOpaque(true);
		
		//chname
		chname = new JLabel();
		chname.setSize(150,50);
		chname.setLocation(110, 225);
		chname.setFont(new Font(Font.DIALOG,Font.PLAIN,25));
		
		//chstate
		chstate = new JTextArea();
		chstate.setSize(300,300);
		chstate.setLocation(50, 300);
		chstate.setFont(new Font(Font.DIALOG,Font.PLAIN,25));
		chstate.setEditable(false);
		
		//chinformation
		chinformation = new JTextArea();
		chinformation.setSize(500,550);
		chinformation.setLocation(500, 50);
		chinformation.setFont(new Font(Font.DIALOG,Font.PLAIN,15));
		chinformation.setText("");
		chinformation.setEditable(false);
		
		//imformationPanel
		informationPanel = new JPanel();
		informationPanel.setSize(1200,700);
		informationPanel.setLayout(null);
		informationPanel.setVisible(false);
		for(int i = 0;i<3;++i) {
			informationPanel.add(changePlayer[i]);
		}
		informationPanel.add(backBtn);
		informationPanel.add(switchbtn);
		informationPanel.add(chpicture);
		informationPanel.add(chname);
		informationPanel.add(chstate);
		informationPanel.add(chinformation);
		
		gameFrame.add(informationPanel);
		
		//eventPicture
		eventPicture = new JLabel();
		eventPicture.setSize(1000,400);
		eventPicture.setLocation(100, 100);
		
		//eventtext
		eventtext = new JTextArea();
		eventtext.setSize(800,100);
		eventtext.setLocation(100, 525);
		eventtext.setFont(new Font(Font.DIALOG,Font.PLAIN,15));
		eventtext.setText("");
		eventtext.setEditable(false);
		
		//continueBtn
		continueBtn = new JButton();
		continueBtn.setSize(100,50);
		continueBtn.setLocation(1050, 575);
		continueBtn.setText("繼續");
		continueBtn.addActionListener(this);		
		
		//eventPanel
		eventPanel = new JPanel();
		eventPanel.setSize(1200,700);
		eventPanel.setLayout(null);
		eventPanel.setVisible(false);
		eventPanel.add(continueBtn);
		eventPanel.add(eventPicture);
		eventPanel.add(eventtext);
		
		gameFrame.add(eventPanel);
	}
	
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		switch(command) {
		case"start":
			startPanel.setVisible(false);
			dungeonPanel.setVisible(true);
			break;
		case"left":
			nextStage();
			break;
		case"mid":
			nextStage();
			break;
		case"right":
			nextStage();
			break;
		case"角色資訊":
			changeInformation(0);
			dungeonPanel.setVisible(false);
			informationPanel.setVisible(true);
			break;
		case"changePlayer0":
			changeInformation(0);
			break;
		case"changePlayer1":
			changeInformation(1);
			break;
		case"changePlayer2":
			changeInformation(2);
			break;
		case"返回":
			informationPanel.setVisible(false);
			dungeonPanel.setVisible(true);
			break;
		case"顯示技能":
			showInformation = false;
			switchbtn.setText("顯示故事");
			changeInformation(0);
			break;
		case"顯示故事":
			showInformation = true;
			switchbtn.setText("顯示技能");
			changeInformation(0);
			break;
		case"繼續":
			eventPanel.setVisible(false);
			dungeonPanel.setVisible(true);
			askContent.setText("請選擇探索方向：");
			break;
		}
		
		//button is no enemy and playerbtn
		if(e.getActionCommand().charAt(0) != 'E' && e.getActionCommand().charAt(0) != 'P') {
			isSelectSkill = true;	
			isSelectTarget = false;
			currentSkill = e.getActionCommand().charAt(0) - '0';
		}
		
		//enemy is clicked
		if((e.getActionCommand().charAt(0) == 'E') && isSelectSkill) {
			isSelectTarget = true;
			currentTarget = e.getActionCommand().charAt(1) - '0' -3;
		}
	}
	
	public void nextStage() {
		if(Math.random()>=0.3) {
			double temp = Math.random();
			if(temp<0.25) {
				eventPicture.setIcon(new ImageIcon("./image/探索/event/00.gif"));
				eventtext.setText("遠遠的看見一名手持藍黑雙刀的劍客，使用著那不能說的招式，在與閃耀魔眼搏鬥。\n你決定在噓完他之後離開。");
			}
			else if(temp<0.5) {
				eventPicture.setIcon(new ImageIcon("./image/探索/event/01.gif"));
				eventtext.setText("一名持有著藍黑雙刀的劍客要求你幫他撐十秒。(他身後的隊友已經殘血了)\n在考慮過後，你在給予他代表尊重的BP後離開。");
			}
			else if(temp<0.75) {
				eventPicture.setIcon(new ImageIcon("./image/探索/event/02.gif"));
				eventtext.setText("天啊，地城裡怎麼會有這種地方(前方有廣大的人群還有舞台，感覺是演唱會)\n你的法師朋友為了是氣氛更加火爆而使用了\"雷擊\"(雷擊精準地擊中台上的人)\n你在還沒有人發現你們是真兇時加速離開。");
			}
			else{
				eventPicture.setIcon(new ImageIcon("./image/探索/event/03.gif"));
				eventtext.setText("魔物出現了!!(前方佇立著一個稻草人，他四周圍繞著說謊的氛圍)\n(!!!)它說話了\"發財!發財!\"響亮的聲音在地城裡迴盪......然而並沒有任何事情發生\n你認為它身上並沒有任何有價值的物品，而且戰鬥力低得沒辦法拿來增加經驗\n於是你在它開始召喚其他魔物前離開了");
			}
			dungeonPanel.setVisible(false);
			eventPanel.setVisible(true);
		}
		else {
			dungeonPanel.setVisible(false);
			battlepanel.setVisible(true);
			initialize();
		}
	}
	
	public void changeInformation(int index) {
		//chpicture.setBackground(Color.BLACK);
		chpicture.setIcon(new ImageIcon("./image/char/icon/P" + (index+1) + ".PNG"));
		chname.setText(allPlayer.get(index).getName());
		chstate.setText("Hp:\t" + allPlayer.get(index).getHealth().getMaxhealth() + "\nMp:\t" + allPlayer.get(index).getMana().getMaxmana() + "\nPhy Att:\t" + allPlayer.get(index).getPhyDamage().getDamage() + "\nMag Att:\t" + allPlayer.get(index).getMagDamage().getDamage() + "\nPhy Def:\t" + allPlayer.get(index).getPhyDefence().getDefence() + "\nMag Def:\t" + allPlayer.get(index).getMagDefence().getDefence() + "\nSpeed:\t" + allPlayer.get(index).getSpeed().getBaseSpeed());
		if(showInformation) {
			switch(index) {
			case 0:
				chinformation.setText("愛克莉絲:\n生日: 11/24\n弓箭手。\n擁有湖水般的水藍色頭髮加上蓬蓬的空氣旁分瀏海、鮮橙色的瞳孔。\n很會打架，\n以前的長髮因為不方便打架而自己剪短了。\n基本上，眼睛只要是睜開的就會打架，\n但內心其實很脆弱(輸不起)。\n因為輸掉了一場激烈的戰鬥，\n決定到地下城尋找傳說中的火屬性神武「燄票」。\n然而失敗了，\n不但被困在地下城，\n還有困在時空狹縫，\n不斷在同一天輪迴。");
				break;
			case 1:
				chinformation.setText("諾亞\n生日: 11/28\n劍士。是一位樂觀的少年。\n灰色的短髮像刺蝟一樣，\n橘色的眼睛很好看，\n但因為戴著頭盔，\n根本沒人看得見。\n入伍那天，\n軍隊在王都掃街，\n諾亞表示，\n以他和第一線的民眾接觸，\n所感受到的熱情，\n「我對我們的軍威愈來愈樂觀！」\n途中許多群眾在路邊高喊、勉勵，\n也有支持和平主義的民眾，\n直接對軍隊比中指，\n但部隊的長官仍相當有風度的鞠躬致謝。\n這種往事，\n如今他只能靠在冰冷的岩壁上回想，\n雖然他在地下城迷了路，\n仍不忘每日樂觀的精神。");
				break;
			case 2:
				chinformation.setText("珂瑞艾斯\n生日: 6/1\n魔法師。\n個性溫暖，\n人脈很好。\n在所處的布魯魔法師協會知名度大增，\n在一場人氣的票選活動上獲得了九十萬人的投票支持，\n也順勢當上南部分會的統領著。\n這統領者的位置坐了不到半年，\n他便決定參與總部會長的選拔。\n卻在北上的過程中，\n不小心走錯路，\n因而被困在地下城裡，\n嗚呼哀哉。");
				break;
			}
		}
		else {
			String temp = "";
			for(int i=0;i<allPlayer.get(index).getSkillEquip().getSkillLen();++i) {
				temp += allPlayer.get(index).getSkillEquip().getSkill(i).getName() + "\n" + allPlayer.get(index).getSkillEquip().getSkill(i).getDescription() + "\n\n";
			}
			chinformation.setText(temp);
		}
	}
	
	public void initialize() {
		notice.setText("");
		isBattle = true;
		
		readData();
		for(int i=0; i<3; i++) {
			//player
			currentPlayer[i] = new Character(allPlayer.get(i));
			currentPlayerSpeed[i] = 0;
			PlayerIsAlive[i] = true;
			//enemy
			int n = rand.nextInt(7);
			chooseEnemy[i] = n;
			currentEnemy[i] = new Character(allEnemy.get(n));
			currentEnemySpeed[i] = 0;
			EnemyIsAlive[i] = true;
		}
		setEnemyPicture();
		isSelectSkill = false;
		isSelectTarget = false;
		
		for(int i=0; i<3; i++) {
		}
		
		//setEnemyPicture();
		for(int i=0; i<3; i++) {
			ImageIcon img = new ImageIcon(enemyPic[i][0]);
			img.setImage(img.getImage().getScaledInstance(ENEMY_WIDTH,ENEMY_HEIGHT,Image.SCALE_DEFAULT));

			playerLb.get(i).setBounds(BAR_LOCATION_X, BAR_LOCATION_Y - 20, 35, 35);
			enemyLb.get(i).setBounds(BAR_LOCATION_X + BAR_WIDTH - enemyLb.get(i).getWidth(), BAR_LOCATION_Y - 20, 35, 35);			
			playerLb.get(i).setVisible(true);
			enemyLb.get(i).setVisible(true);
			
			playerbtn.get(i).setBounds(PLAYER_LOCATION_X[i], PLAYER_LOCATION_Y[i], PLAYER_WIDTH, PLAYER_HEIGHT);
			playerbtn.get(i).setVisible(true);
			
			enemy.get(i).setIcon(img);
			enemy.get(i).setBounds(ENEMY_LOCATION_X[i], ENEMY_LOCATION_Y[i], ENEMY_WIDTH, ENEMY_HEIGHT);
			enemy.get(i).setVisible(true);
			
			skill.get(i).setBounds(SKILL_LOCATION_X[i], SKILL_LOCATION_Y[i], SKILL_WIDTH, SKILL_HEIGHT);
			skill.get(i).setVisible(false);
			
			playerBlood.get(i).setBounds(PLAYER_BLOOD_LOCATION_X[i], PLAYER_BLOOD_LOCATION_Y[i], BLOCK_WIDTH, BLOCK_HEIGHT);
			playerBlood.get(i).setVisible(true);
			playerMana.get(i).setBounds(PLAYER_MANA_LOCATION_X[i], PLAYER_MANA_LOCATION_Y[i], BLOCK_WIDTH, BLOCK_HEIGHT);
			playerMana.get(i).setVisible(true);
			enemyBlood.get(i).setBounds(ENEMY_BLOOD_LOCATION_X[i], ENEMY_BLOOD_LOCATION_Y[i], BLOCK_WIDTH, BLOCK_HEIGHT);
			enemyBlood.get(i).setVisible(true);
			enemyMana.get(i).setBounds(ENEMY_MANA_LOCATION_X[i], ENEMY_MANA_LOCATION_Y[i], BLOCK_WIDTH, BLOCK_HEIGHT);
			enemyMana.get(i).setVisible(true);
			
			playerBlood.get(i).setText(Double.toString(currentPlayer[i].getHealth().getCurrenthealth()) + "/"+currentPlayer[i].getHealth().getMaxhealth());
			playerMana.get(i).setText(Double.toString(currentPlayer[i].getMana().getCurrentmana()) + "/"+currentPlayer[i].getMana().getMaxmana());
			enemyBlood.get(i).setText(Double.toString(currentEnemy[i].getHealth().getCurrenthealth()) + "/"+currentEnemy[i].getHealth().getMaxhealth());
			enemyMana.get(i).setText(Double.toString(currentEnemy[i].getMana().getCurrentmana()) + "/"+currentEnemy[i].getMana().getMaxmana());

		}
		skill.get(3).setBounds(SKILL_LOCATION_X[3], SKILL_LOCATION_Y[3], SKILL_WIDTH, SKILL_HEIGHT);
		skill.get(3).setVisible(false);
		bar.setIcon(barImg);
		bar.setBounds(BAR_LOCATION_X, BAR_LOCATION_Y,BAR_WIDTH, BAR_HEIGHT);
		setEnemyPicture();
		if(timer == null) {
			timer = new Timer();
			timer.schedule(this, 0, 5);
		}
	}
	
	public void setEnemyPicture() {
		ImageIcon img1;
		ImageIcon smallImg;
		ImageIcon enemyLabelImg;
		for(int i=0; i<3; i++) {
			System.out.println(currentEnemy[i].getName());
			switch(currentEnemy[i].getName()){
			case "變種蜥蜴":
				System.out.println("case變種蜥蜴");
				img1 = new ImageIcon(enemyPic[0][0]);
				enemy.get(i).setIcon(img1);
				smallImg = new ImageIcon("./image/enemy/e01_0.png");
				smallImg.setImage(smallImg.getImage().getScaledInstance(40,40,Image.SCALE_DEFAULT));
				enemySmallIcon.get(i).setIcon(smallImg);
				enemySmallIcon.get(i).setBounds(ENEMY_BLOOD_LOCATION_X[i] + 85, ENEMY_BLOOD_LOCATION_Y[i] + 15, 40 ,40);
				enemyLabelImg = new ImageIcon("./image/enemy/icon/e01.png");
				enemyLabelImg.setImage(enemyLabelImg.getImage().getScaledInstance(35,35,Image.SCALE_DEFAULT));
				enemyLb.get(i).setIcon(enemyLabelImg);
				break;
			case "菇菇寶貝":
				System.out.println("case菇菇寶貝");
				img1 = new ImageIcon(enemyPic[1][0]);
				enemy.get(i).setIcon(img1);
				smallImg = new ImageIcon("./image/enemy/e02_0.png");
				smallImg.setImage(smallImg.getImage().getScaledInstance(40,40,Image.SCALE_DEFAULT));
				enemySmallIcon.get(i).setIcon(smallImg);
				enemySmallIcon.get(i).setBounds(ENEMY_BLOOD_LOCATION_X[i] + 85, ENEMY_BLOOD_LOCATION_Y[i] + 15, 40 ,40);
				enemyLabelImg = new ImageIcon("./image/enemy/icon/e02.png");
				enemyLabelImg.setImage(enemyLabelImg.getImage().getScaledInstance(35,35,Image.SCALE_DEFAULT));
				enemyLb.get(i).setIcon(enemyLabelImg);
				break;
			case "花巨人":
				System.out.println("case花巨人");
				img1 = new ImageIcon(enemyPic[2][0]);
				enemy.get(i).setIcon(img1);
				smallImg = new ImageIcon("./image/enemy/e03_0.png");
				smallImg.setImage(smallImg.getImage().getScaledInstance(40,40,Image.SCALE_DEFAULT));
				enemySmallIcon.get(i).setIcon(smallImg);
				enemySmallIcon.get(i).setBounds(ENEMY_BLOOD_LOCATION_X[i] + 85, ENEMY_BLOOD_LOCATION_Y[i] + 15, 40 ,40);
				enemyLabelImg = new ImageIcon("./image/enemy/icon/e03.png");
				enemyLabelImg.setImage(enemyLabelImg.getImage().getScaledInstance(35,35,Image.SCALE_DEFAULT));
				enemyLb.get(i).setIcon(enemyLabelImg);
				break;
			case "可怕蜈蚣":
				System.out.println("case可怕蜈蚣");
				img1 = new ImageIcon(enemyPic[3][0]);
				enemy.get(i).setIcon(img1);
				smallImg = new ImageIcon("./image/enemy/e04_0.png");
				smallImg.setImage(smallImg.getImage().getScaledInstance(40,40,Image.SCALE_DEFAULT));
				enemySmallIcon.get(i).setIcon(smallImg);
				enemySmallIcon.get(i).setBounds(ENEMY_BLOOD_LOCATION_X[i] + 85, ENEMY_BLOOD_LOCATION_Y[i] + 15, 40 ,40);
				enemyLabelImg = new ImageIcon("./image/enemy/icon/e04.png");
				enemyLabelImg.setImage(enemyLabelImg.getImage().getScaledInstance(35,35,Image.SCALE_DEFAULT));
				enemyLb.get(i).setIcon(enemyLabelImg);
				break;
			case "巨魔龜":
				System.out.println("case巨魔龜");
				img1 = new ImageIcon(enemyPic[4][0]);
				enemy.get(i).setIcon(img1);
				smallImg = new ImageIcon("./image/enemy/e05_0.png");
				smallImg.setImage(smallImg.getImage().getScaledInstance(40,40,Image.SCALE_DEFAULT));
				enemySmallIcon.get(i).setIcon(smallImg);
				enemySmallIcon.get(i).setBounds(ENEMY_BLOOD_LOCATION_X[i] + 85, ENEMY_BLOOD_LOCATION_Y[i] + 15, 40 ,40);
				enemyLabelImg = new ImageIcon("./image/enemy/icon/e05.png");
				enemyLabelImg.setImage(enemyLabelImg.getImage().getScaledInstance(35,35,Image.SCALE_DEFAULT));
				enemyLb.get(i).setIcon(enemyLabelImg);
				break;
			case "毛怪":
				System.out.println("case毛怪");
				img1 = new ImageIcon(enemyPic[5][0]);
				enemy.get(i).setIcon(img1);
				smallImg = new ImageIcon("./image/enemy/e06_0.png");
				smallImg.setImage(smallImg.getImage().getScaledInstance(40,40,Image.SCALE_DEFAULT));
				enemySmallIcon.get(i).setIcon(smallImg);
				enemySmallIcon.get(i).setBounds(ENEMY_BLOOD_LOCATION_X[i] + 85, ENEMY_BLOOD_LOCATION_Y[i] + 15, 40 ,40);
				enemyLabelImg = new ImageIcon("./image/enemy/icon/e06.png");
				enemyLabelImg.setImage(enemyLabelImg.getImage().getScaledInstance(35,35,Image.SCALE_DEFAULT));
				enemyLb.get(i).setIcon(enemyLabelImg);
				break;
			case "夜之死咒法師":
				System.out.println("case夜之死咒法師");
				img1 = new ImageIcon(enemyPic[6][0]);
				enemy.get(i).setIcon(img1);
				smallImg = new ImageIcon("./image/enemy/e07_0.png");
				smallImg.setImage(smallImg.getImage().getScaledInstance(40,40,Image.SCALE_DEFAULT));
				enemySmallIcon.get(i).setIcon(smallImg);
				enemySmallIcon.get(i).setBounds(ENEMY_BLOOD_LOCATION_X[i] + 85, ENEMY_BLOOD_LOCATION_Y[i] + 15, 40 ,40);
				enemyLabelImg = new ImageIcon("./image/enemy/icon/e07.png");
				enemyLabelImg.setImage(enemyLabelImg.getImage().getScaledInstance(35,35,Image.SCALE_DEFAULT));
				enemyLb.get(i).setIcon(enemyLabelImg);
				break;
				default:
					System.out.println("casedefault");
					img1 = new ImageIcon(enemyPic[0][0]);
			}
			img1.setImage(img1.getImage().getScaledInstance(ENEMY_WIDTH,ENEMY_HEIGHT,Image.SCALE_DEFAULT));
			enemy.get(i).setIcon(img1);
		}
		battlepanel.setBounds(0, 0, FRAME_WIDTH, FRAME_HEIGHT);
	}
	
	public void changeSkill() {
		for(int i=0; i<3; i++) {
			skill.get(i).setVisible(true);
			skill.get(i).setText(Integer.toString(i)+" "+currentPlayer[activePlayer].getSkillEquip().getSkill(i).getName());
			if(currentPlayer[activePlayer].getMana().getCurrentmana() < currentPlayer[activePlayer].getSkillEquip().getSkill(i).getManaConsume()) { //currentmana < skillmana
				skill.get(i).setEnabled(false);
			}
			else {
				skill.get(i).setEnabled(true);
			}
		}
		skill.get(3).setVisible(true);
		selectSkillAndTarget();
	}
	
	public void selectSkillAndTarget() {
		while(!isSelectSkill || !isSelectTarget) {
			//wait to select skill
			try {
			Thread.sleep(10);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		isSelectSkill = false;
		isSelectTarget = false;
		for(int i=0; i<3; i++) {
			skill.get(i).setVisible(false);		
		}
		skill.get(3).setVisible(false);	
		playerAttack();
	
	}

//check if playerbtn or enemy is dead, and set invisible
	public void check() {
		for(int i=0; i<3; i++) {
			if(currentPlayer[i].getHealth().getCurrenthealth() <= 0) {
				playerbtn.get(i).setVisible(false);
				playerLb.get(i).setVisible(false);
			}
			if(EnemyIsAlive[i] == false) {
				enemy.get(i).setVisible(false);
				enemyLb.get(i).setVisible(false);
			}
		}
		
		if(PlayerIsAlive[0] == false && PlayerIsAlive[1] == false && PlayerIsAlive[2] == false) {
			JOptionPane.showMessageDialog (null,"戰鬥失敗","",JOptionPane.OK_OPTION);
			battlepanel.setVisible(false);
			isBattle = false;
			dungeonPanel.setVisible(true);
			battlepanel.setVisible(false);
		}
		
		list = new ArrayList<>();
		//playerWin
		if(EnemyIsAlive[0] == false && EnemyIsAlive[1] == false && EnemyIsAlive[2] == false) {
			JOptionPane.showMessageDialog (null,"戰鬥成功","",JOptionPane.OK_OPTION);
			
			battlepanel.setVisible(false);
			isBattle = false;
			dungeonPanel.setVisible(true);
			battlepanel.setVisible(false);
			for(int i=0; i<3; i++) {
				System.out.println(currentPlayer[i].getLevel().getLevel());
				
				currentPlayer[i].getExp().add(50, currentPlayer[i].getLevel());
				currentPlayer[i].setPropertybyFormula() ;
				currentPlayer[i].getHealth().setCurrenthealth(currentPlayer[i].getHealth().getMaxhealth());
				currentPlayer[i].getMana().setCurrentmana(currentPlayer[i].getMana().getMaxmana());
				list.add(currentPlayer[i]);
			}
			saveData();
		}
	}

	public void playerAttack() {
		
		if(currentSkill <=2) {
			double damage = currentPlayer[activePlayer].getSkillEquip().getSkill(currentSkill).getAttack().getPhyMult()*
					currentPlayer[activePlayer].getPhyDamage().getDamage() + currentPlayer[activePlayer].getSkillEquip().getSkill(currentSkill).getAttack().getMagMult()*
					currentPlayer[activePlayer].getMagDamage().getDamage() + currentPlayer[activePlayer].getSkillEquip().getSkill(currentSkill).getAttack().getBaseDamage();
			currentPlayer[activePlayer].getMana().doDelta(-currentPlayer[activePlayer].getSkillEquip().getSkill(currentSkill).getManaConsume());
			if(currentPlayer[activePlayer].getSkillEquip().getSkill(currentSkill).getAttack().getEffectType().equals("phy")) {
				damage = Math.max(damage - currentEnemy[currentTarget].getPhyDefence().getDefence(),0);
			}
			else {
				damage = Math.max(damage - currentEnemy[currentTarget].getMagDefence().getDefence(),0);
			}
			notice.setText(currentPlayer[activePlayer].getName()+" 使用 "+currentPlayer[activePlayer].getSkillEquip().getSkill(currentSkill).getName()+" 對"+currentEnemy[currentTarget].getName() + "造成 " + df.format(damage) + " 點傷害");
			currentEnemy[currentTarget].getHealth().doDelta((damage >= currentEnemy[currentTarget].getHealth().getCurrenthealth())?(-currentEnemy[currentTarget].getHealth().getCurrenthealth()):(-damage));
		}
		else {
			double damage = Math.max(1.2 * currentPlayer[activePlayer].getPhyDamage().getDamage() - currentEnemy[currentTarget].getPhyDefence().getDefence(), 0);
			currentEnemy[currentTarget].getHealth().doDelta((damage >= currentEnemy[currentTarget].getHealth().getCurrenthealth())?(-currentEnemy[currentTarget].getHealth().getCurrenthealth()):(-damage));
			notice.setText(currentPlayer[activePlayer].getName() + " 使用基礎攻擊對 "+currentEnemy[currentTarget].getName() + "造成 " + df.format(damage) + " 點傷害");
		}
		
		beAttackedImg("enemy");
		if(currentEnemy[currentTarget].getHealth().getCurrenthealth()<=0) {
			EnemyIsAlive[currentTarget] = false;
		}
		check();
		for(int i=0; i<3; i++) {
			playerMana.get(i).setText(Double.toString(currentPlayer[i].getMana().getCurrentmana()) + "/"+currentPlayer[i].getMana().getMaxmana());
			enemyBlood.get(i).setText(df.format(currentEnemy[i].getHealth().getCurrenthealth()) + "/" + df.format(currentEnemy[i].getHealth().getMaxhealth()));
		}
		
		
		try {
		Thread.sleep(1000);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		//recover mana
		currentPlayer[activePlayer].getMana().doDelta(currentPlayer[activePlayer].getMana().getRecover());
		currentPlayer[activePlayer].getMana().setCurrentmana(Math.min(currentPlayer[activePlayer].getMana().getCurrentmana(), currentPlayer[activePlayer].getMana().getMaxmana()));
		playerMana.get(activePlayer).setText(Double.toString(currentPlayer[activePlayer].getMana().getCurrentmana()) + "/"+currentPlayer[activePlayer].getMana().getMaxmana());
		try {
			Thread.sleep(1000);
			}catch(Exception e) {
				e.printStackTrace();
			}
		isAttack = false;
	}
	
	public void enemyAttack() {
		int n = rand.nextInt(3);
		while(PlayerIsAlive[n] == false) {
			n = rand.nextInt(3);
		}
		currentTargetPlayer = n;
		n = rand.nextInt(currentEnemy[activeEnemy].getSkillEquip().getSkillLen());
		if(currentEnemy[activeEnemy].getSkillEquip().getSkill(n).getManaConsume()>currentEnemy[activeEnemy].getMana().getCurrentmana())n = 0;
		double damage = currentEnemy[activeEnemy].getSkillEquip().getSkill(n).getAttack().getPhyMult()*
				currentEnemy[activeEnemy].getPhyDamage().getDamage() + currentEnemy[activeEnemy].getSkillEquip().getSkill(n).getAttack().getMagMult()*
				currentEnemy[activeEnemy].getMagDamage().getDamage() + currentEnemy[activeEnemy].getSkillEquip().getSkill(n).getAttack().getBaseDamage();
		if(currentEnemy[activeEnemy].getSkillEquip().getSkill(n).getAttack().getEffectType().equals("phy")) {
			damage = Math.max(damage - currentPlayer[currentTargetPlayer].getPhyDefence().getDefence(),0);
		}
		else {
			damage = Math.max(damage - currentPlayer[currentTargetPlayer].getMagDefence().getDefence(),0);
		}
		notice.setText(currentEnemy[activeEnemy].getName()+" 使用 "+currentEnemy[activeEnemy].getSkillEquip().getSkill(n).getName()+" 對"+currentPlayer[currentTargetPlayer].getName() + "造成 " + damage + " 點傷害");
		currentPlayer[currentTargetPlayer].getHealth().doDelta((damage > currentPlayer[currentTargetPlayer].getHealth().getCurrenthealth())?(-currentPlayer[currentTargetPlayer].getHealth().getCurrenthealth()):(-damage));
		beAttackedImg("playerbtn");
		
		currentEnemy[activeEnemy].getMana().doDelta(-currentEnemy[activeEnemy].getSkillEquip().getSkill(n).getManaConsume());
		for(int i=0; i<3; i++) {
			playerBlood.get(i).setText(df.format(currentPlayer[i].getHealth().getCurrenthealth()) + "/" + df.format(currentPlayer[i].getHealth().getMaxhealth()));
			enemyMana.get(i).setText(Double.toString(currentEnemy[i].getMana().getCurrentmana()) + "/"+currentEnemy[i].getMana().getMaxmana());
		}
		
		//check if blood is <=0
		if(currentPlayer[currentTargetPlayer].getHealth().getCurrenthealth() <= 0) {
			PlayerIsAlive[currentTargetPlayer] = false;
		}
		
		//check terminate condition
		check();
		
		try {
			Thread.sleep(1000);
			}catch(Exception e) {
				e.printStackTrace();
			}
		
		//recover mana
		currentEnemy[activeEnemy].getMana().doDelta(currentEnemy[activeEnemy].getMana().getRecover());
		currentEnemy[activeEnemy].getMana().setCurrentmana(Math.min(currentEnemy[activeEnemy].getMana().getCurrentmana(), currentEnemy[activeEnemy].getMana().getMaxmana()));
		enemyMana.get(activeEnemy).setText(Double.toString(currentEnemy[activeEnemy].getMana().getCurrentmana()) + "/"+currentEnemy[activeEnemy].getMana().getMaxmana());
		try {
			Thread.sleep(1000);
			}catch(Exception e) {
				e.printStackTrace();
			}
		isAttack = false;
	}

	

	public void readData() {
		//read all player and enemy
		try {
			ObjectInputStream reader = new ObjectInputStream(new FileInputStream("./player.obj"));
			allPlayer = (List<Character>)reader.readObject();
			reader.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		try {
			ObjectInputStream reader = new ObjectInputStream(new FileInputStream("./enemy.obj"));
			allEnemy = (List<Character>)reader.readObject();
			reader.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void saveData() {
		//save player
		try {
			ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream("./player.obj"));
			writer.writeObject(list);
			writer.flush();
			writer.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void beAttackedImg(String character) {
		if(character.equals("playerbtn")) {
			ImageIcon img = new ImageIcon(playerPic[currentTargetPlayer][1]);
			img.setImage(img.getImage().getScaledInstance(PLAYER_WIDTH,PLAYER_HEIGHT,Image.SCALE_DEFAULT));
			playerbtn.get(currentTargetPlayer).setIcon(img);
			playerbtn.get(currentTargetPlayer).setLocation(PLAYER_LOCATION_X[currentTargetPlayer]-10, PLAYER_LOCATION_Y[currentTargetPlayer]);
			try {
				Thread.sleep(1000);
				}catch(Exception e) {
					e.printStackTrace();
				}
			ImageIcon img1 = new ImageIcon(playerPic[currentTargetPlayer][0]);
			img1.setImage(img1.getImage().getScaledInstance(PLAYER_WIDTH,PLAYER_HEIGHT,Image.SCALE_DEFAULT));
			playerbtn.get(currentTargetPlayer).setIcon(img1);
			playerbtn.get(currentTargetPlayer).setLocation(PLAYER_LOCATION_X[currentTargetPlayer], PLAYER_LOCATION_Y[currentTargetPlayer]);
		}
		else if(character.equals("enemy")) {
			ImageIcon img;
			ImageIcon img1;
			switch(currentEnemy[currentTarget].getName()) {
			case "變種蜥蜴":
				System.out.println("case變種蜥蜴");
				img = new ImageIcon(enemyPic[0][1]);
				img1 = new ImageIcon(enemyPic[0][0]);
				break;
			case "菇菇寶貝":
				System.out.println("case菇菇寶貝");
				img = new ImageIcon(enemyPic[1][1]);
				img1 = new ImageIcon(enemyPic[1][0]);
				break;
			case "花巨人":
				System.out.println("case花巨人");
				img = new ImageIcon(enemyPic[2][1]);
				img1 = new ImageIcon(enemyPic[2][0]);
				break;
			case "可怕蜈蚣":
				System.out.println("case可怕蜈蚣");
				img = new ImageIcon(enemyPic[3][1]);
				img1 = new ImageIcon(enemyPic[3][0]);
				break;
			case "巨魔龜":
				System.out.println("case巨魔龜");
				img = new ImageIcon(enemyPic[4][1]);
				img1 = new ImageIcon(enemyPic[4][0]);
				break;
			case "毛怪":
				System.out.println("case毛怪");
				img = new ImageIcon(enemyPic[5][1]);
				img1 = new ImageIcon(enemyPic[5][0]);
				break;
			case "夜之死咒法師":
				System.out.println("case夜之死咒法師");
				img = new ImageIcon(enemyPic[6][1]);
				img1 = new ImageIcon(enemyPic[6][0]);
				break;
				default:
					System.out.println("casedefault");
					img = new ImageIcon(enemyPic[0][1]);
					img1 = new ImageIcon(enemyPic[0][0]);
			}
			img.setImage(img.getImage().getScaledInstance(ENEMY_WIDTH,ENEMY_HEIGHT,Image.SCALE_DEFAULT));
			enemy.get(currentTarget).setIcon(img);
			enemy.get(currentTarget).setLocation(ENEMY_LOCATION_X[currentTarget]+10, ENEMY_LOCATION_Y[currentTarget]);
			try {
				Thread.sleep(1000);
				}catch(Exception e) {
					e.printStackTrace();
				}
			img1.setImage(img1.getImage().getScaledInstance(ENEMY_WIDTH,ENEMY_HEIGHT,Image.SCALE_DEFAULT));
			enemy.get(currentTarget).setIcon(img1);
			enemy.get(currentTarget).setLocation(ENEMY_LOCATION_X[currentTarget], ENEMY_LOCATION_Y[currentTarget]);
			}
		}
	
	public void run() {
		if(isAttack||(!isBattle))return;
		for(int i=0; i<3; i++) {
			currentPlayerSpeed[i] = currentPlayerSpeed[i] + currentPlayer[i].getSpeed().getBaseSpeed();
			currentEnemySpeed[i] = currentEnemySpeed[i] + currentEnemy[i].getSpeed().getBaseSpeed();
			//playerbtn
			if(PlayerIsAlive[i] == true  && isBattle == true) {
				if((playerLb.get(i).getLocation().x + playerLb.get(i).getWidth()/2) >= (BAR_LOCATION_X+BAR_WIDTH/2)) {
					playerLb.get(i).setBounds(BAR_LOCATION_X, BAR_LOCATION_Y - 20, 35, 35);
					activePlayer = i;
					currentPlayerSpeed[i] = 0;
					notice.setText("等待 "+currentPlayer[i].getName()+" 攻擊");
					isAttack = true;
					changeSkill();
					currentSkill = -1;
				}else {
					playerLb.get(i).setBounds(BAR_LOCATION_X+(int) currentPlayerSpeed[i], 30, 35, 35);	
				}
			}
			
			//enemy
			if(EnemyIsAlive[i] == true && isBattle == true) {
				if(enemyLb.get(i).getLocation().x + enemyLb.get(i).getWidth()/2 <= BAR_LOCATION_X+BAR_WIDTH/2) {
					enemyLb.get(i).setBounds(BAR_LOCATION_X+ BAR_WIDTH - enemyLb.get(i).getWidth(), BAR_LOCATION_Y - 20, 35, 35);
					activeEnemy = i;
					currentEnemySpeed[i]=0;
					notice.setText("wait enemy "+currentEnemy[i].getName()+" attack");
					isAttack = true;
					enemyAttack();
				}else {
					enemyLb.get(i).setBounds(BAR_LOCATION_X+ BAR_WIDTH- enemyLb.get(i).getWidth() - (int)currentEnemySpeed[i], 30,35, 35);	
				}
			}
		}
	}
}
