package games.civ;

import javax.swing.*;
import java.util.Vector;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;
import java.util.*;
import games.*;

public class Combat extends JComponent implements MouseListener, ActionListener
{
  int handLimit, handLimit2;
  Player att, def;

  Vector defHand, attHand, defFront, attFront, excessAttCards, excessDefCards, defGraveCards, attGraveCards;
  Vector defHandDef, attHandDef, defFrontDef, attFrontDef, excessAttCardsDef, excessDefCardsDef, defGraveCardsDef, attGraveCardsDef;

  Front[] fronts;

  boolean combatResolved, attackSelected, metalAtt, metalDef, metalListen, animalHusbandry, animalHusbandryActive,
          mathAtt, mathDef, mathListen, mathActive, ballAtt, ballDef, ballListen, ballActive, hasBiology;

  JLabel[] attCards, defCards;
  JLabel[] attCardsDef, defCardsDef;

  int attSize, defSize, attackCard, defenseCard, cardChoice, frontChoice,
      numAiFronts, numPlayerFronts, attGraveSize, defGraveSize, attBonus, defBonus,
		attTotalWounds, defTotalWounds, heal, mathWounds, mathDeaths, ballWounds, ballDeaths, attCastleValue, defCastleValue;

  JDialog combatBoard, combatBoardDef;
  JPanel attPanel, defPanel, choiceManager, techManager;
  JPanel attPanelDef, defPanelDef, choiceManagerDef, techManagerDef;

  JLayeredPane board, defGraveyard, attGraveyard;
  JLayeredPane boardDef, defGraveyardDef, attGraveyardDef;

  JButton select, newFront, metal, animal, math, ball, biology;
  JButton selectDef, newFrontDef, metalDefend, animalDef, mathDefend, ballDefend, biologyDef;

  JTextArea choose, attBonusText, defBonusText;
  JTextArea chooseDef, attBonusTextDef, defBonusTextDef;

  Die dice;
  MoveGUI mGUI;
    
  //for settler only combat
  public Combat(Player defender, Player attacker, boolean dummy)
  {
	  JOptionPane victor = new JOptionPane();
	  ImageIcon im = getImage("data/"+attacker.getNation()+"Icon.png");  
     victor.showMessageDialog(board, "YOU WIN THE "+"'BATTLE'"+"\nPREPARE TO LOOT!", "COMBAT RESULT",
	  													 JOptionPane.INFORMATION_MESSAGE, im);	 	 
     String returnWinner = "settlerOnly";
     String winner = "attacker";  
     if(attacker.getTechVector().get(1) != null)//calling here for now, can also call in combatResume() in MoveGUI
	  {
       attacker.addTechGold(1, 1);   
	  }

	  getSpoils(winner);
  }
  
  //for PvP and vs. Village combat
  public Combat(Player defender, Player attacker, MoveGUI m)
  {
  	 if(defender.getNation().equals("Village"))
	 {
		if(attacker.getPlayerUI().getInfDeckSize() > 0)
      {
			Card inf = attacker.getPlayerUI().getInfCard(defender);
	      inf.getLabel().setVisible(true);  
			defender.addUnitToHand(inf);
      }  
		if(attacker.getPlayerUI().getMntDeckSize() > 0)
      {
		   Card mnt = attacker.getPlayerUI().getMntCard(defender);
	      mnt.getLabel().setVisible(true);  
		   defender.addUnitToHand(mnt);
      }
		if(attacker.getPlayerUI().getArtDeckSize() > 0)
      {
		   Card art = attacker.getPlayerUI().getArtCard(defender);
	      art.getLabel().setVisible(true);  
		   defender.addUnitToHand(art);
      }  
	 }

 	 att = attacker;
	 def = defender;

//for the castle wonder
    if(att.getWonderCards(6)) 
      attCastleValue = 1;
	 else	
      attCastleValue = 0;

    if(!def.getNation().equals("Village") && def.getWonderCards(6)) 
      defCastleValue = 1;
	 else	
      defCastleValue = 0;

    combatResolved = false;
    attackSelected = false;
  
    metalAtt = false;
	 metalDef = false;
	 metalListen = false;
	 
    mathAtt = false;
	 mathDef = false;
	 mathListen = false;
	 mathWounds = 3;
	 mathDeaths = 0;
	 
    ballAtt = false;
	 ballDef = false;
	 ballListen = false;
	 ballWounds = 6;
	 ballDeaths = 0;

    numAiFronts = 0;
    numPlayerFronts = 0;

    attTotalWounds = 0;
	 defTotalWounds = 0;

	 mGUI = m;
	 dice = new Die(new Random().nextInt(666));

    defHand = new Vector(0,1);
    attHand = new Vector(0,1);

    defHandDef = new Vector(0,1);
    attHandDef = new Vector(0,1);
  //shuffle the deck and get the top x card, x = hand size limit
	 for(int k = 0; k < def.getUnitHand().size(); k++)
	 {
      Card temp = (Card)def.getUnitHand().get(k);
	   defHand.add(temp);
      Card temp2 = (Card)def.getUnitHand().get(k);
	   attHandDef.add(temp2);
	 }
	 for(int k = 0; k < att.getUnitHand().size(); k++)
	 {
//System.out.println("ATTHAND "+att.getUnitHand().size());
      Card temp = (Card)att.getUnitHand().get(k);
	   attHand.add(temp);
      Card temp2 = (Card)att.getUnitHand().get(k);
	   defHandDef.add(temp2);
    }	 
//System.out.println("DEFHAND BEFORE: " +defHand.size());
    if(!def.getNation().equals("Village") && (defHand.size() > def.getCombatHandLimit(mGUI.getCombatUnit())))
	 {
	   excessDefCards = new Vector(0, 1);
	   shuffle(defHand);
      Vector temp = new Vector(0, 1);
		int count;
		for(count = 0; count < def.getCombatHandLimit(mGUI.getCombatUnit()); count++)
		{
		  temp.add(defHand.remove(0));
		}
	   for(count = 0; count < defHand.size(); count++) 
		{
		  excessDefCards.add(defHand.get(count));
		}
//System.out.println("EXCESS CARDS: " +excessDefCards.size());
	   defHand = temp;
	 }
//System.out.println("DEFHAND After: " +defHand.size());
  
//System.out.println("ATTHAND BEFORE: " +attHand.size());
    if(!att.getNation().equals("Village") && (attHand.size() > att.getCombatHandLimit(mGUI.getCombatUnit())))
	 {
	   excessAttCards = new Vector(0,1);
	   shuffle(attHand);
      Vector temp = new Vector(0,1);
		int count;
		for(count = 0; count < att.getCombatHandLimit(mGUI.getCombatUnit()); count++)
		{
//System.out.println("ATTHAND TWO "+attHand.size());
		  temp.add(attHand.remove(0));
		}
	   for(count = 0; count < attHand.size(); count++) 
		{
		  excessAttCards.add(attHand.get(count));
		}
//System.out.println("EXCESS CARDS: " +excessAttCards.size());
	   attHand = temp;
	 }
//System.out.println("ATTHAND After: " +attHand.size());

  //for controlling cards in the front  
	 defFront = new Vector(0, 1);
	 attFront = new Vector(0, 1);

	 defFrontDef = new Vector(0, 1);
	 attFrontDef = new Vector(0, 1);

//add init calls here
	 initAttackerDialog(att, def);
    if(!def.getNation().equals("Village")){
		 initDefenderDialog(def, att);
	 }
	  
	 attSize = attHand.size();
	 defSize = defHand.size();
	 
	 fronts = new Front[attHand.size()+defHand.size()];
	 
    attGraveSize = 0;
	 defGraveSize = 0;
    attGraveCards = new Vector(0, 1);
    defGraveCards = new Vector(0, 1);

	 performActions();
  
  }

  private void initAttackerDialog(Player att, Player def){
    defCards = new JLabel[defHand.size()];
	 attCards = new JLabel[attHand.size()];
    attGraveyard = new JLayeredPane();
    defGraveyard = new JLayeredPane();
    JTextArea grave1 = new JTextArea("GRAVEYARD");
    JTextArea grave2 = new JTextArea("GRAVEYARD");
    grave1.setEditable(false);
    grave1.setBounds(0, 0, 100, 20); 
    grave2.setEditable(false);
    grave2.setBounds(0, 0, 100, 20); 
	 
    defGraveyard.setPreferredSize(new Dimension(165, 300));
	 defGraveyard.setBounds(860, 100, 160, 400);	 	   
	 defGraveyard.setForeground(def.getPlayerColor());
    defGraveyard.add(grave1, new Integer(0));
	  
    attGraveyard.setPreferredSize(new Dimension(165, 365));  
    attGraveyard.setBounds(860, 405, 160, 365);	 	   
	 attGraveyard.setForeground(att.getPlayerColor());
    attGraveyard.add(grave2, new Integer(0));

//need to change to if village dont draw, if human defender the draw there too	
	 combatBoard = new JDialog(att.playerUI.c, att.getNation());
    combatBoard.setResizable(false);
  
    if(defHand.size() > attHand.size())
	   combatBoard.setSize(new Dimension(1200, 800));
    else
	   combatBoard.setSize(new Dimension(1200, 800));

    attPanel = new JPanel();
	 attPanel.setBackground(att.getPlayerColor());
    attPanel.setBounds(00, 610, combatBoard.getWidth(), 160);

	 board = new JLayeredPane();
	 board.setBackground(new Color(255,0,0));
    board.setBounds(00, 160, combatBoard.getWidth(), 800-attPanel.getHeight());

    choiceManager = new JPanel();
    choiceManager.setBackground(att.getPlayerColor());
    choiceManager.setPreferredSize(new Dimension(100, 150));
	 choiceManager.setBounds(00, 00, 100, 150);
	 choiceManager.setVisible(true);
	 attPanel.add(choiceManager);

    Army cu = mGUI.getCombatUnit();
    if(att.playerUI.lb.isCitySpace(cu.getX(), cu.getY()))
    {
	   Vector temp = att.getCityVector();
		for(int k = 0; k < temp.size(); k++)
		{
		  if(att.getCityXpos(k) == cu.getX() && att.getCityYpos(k) == cu.getY())
		    attBonus = att.getCityCombatBonus(temp.get(k));
		}  
    }  
	 else
      attBonus = att.getCombatBonus();
	 attBonusText = new JTextArea("Bonus: "+Integer.toString(attBonus));
	 attBonusText.setBounds(327, 586, 68, 25);
    attBonusText.setEditable(false);
    attBonusText.setForeground(new Color(250, 250, 250));
    attBonusText.setBackground(new Color(0, 120, 205));
	 attBonusText.setBorder(new MatteBorder(4, 4, 4, 4, getImage("data/goldBorder.png")));
	 
    defPanel = new JPanel();
	 defPanel.setBackground(def.getPlayerColor());
    defPanel.setBounds(00, 00, combatBoard.getWidth(), 90);

    if(att.playerUI.lb.isCitySpace(cu.getX(), cu.getY()))
    {
	   Vector temp = def.getCityVector();
		for(int k = 0; k < temp.size(); k++)
		{
		  if(def.getCityXpos(k) == cu.getX() && def.getCityYpos(k) == cu.getY())
		    defBonus = def.getCityCombatBonus(temp.get(k));
		}  
    }  
	 else
      defBonus = def.getCombatBonus();
	 defBonusText = new JTextArea("Bonus: "+Integer.toString(defBonus));
    defBonusText.setBounds(1, 90, 68, 25);
    defBonusText.setEditable(false);
    defBonusText.setForeground(new Color(250, 250, 250));
    defBonusText.setBackground(new Color(0, 120, 205));
    defBonusText.setBorder(new MatteBorder(4, 4, 4, 4, getImage("data/goldBorder.png")));

    if(attBonus > defBonus)
	 {
      attBonus -= defBonus; 
      attBonusText.setText("Bonus: "+Integer.toString(attBonus));
      defBonus = 0;  
	 } 
    else
      attBonusText.setText("Bonus: 0");
	   
	 if(attBonus < defBonus)
    {
      defBonus -= attBonus; 
      defBonusText.setText("Bonus: "+Integer.toString(defBonus));
      attBonus = 0;  
    }
    else
      defBonusText.setText("Bonus: 0");
 
    board.add(attBonusText, new Integer(21));
	 board.add(defBonusText, new Integer(21));
    techManager = new JPanel();
//	 techManager.setOpaque(false);
//    techManager.setForeground(new Color(0, 0, 0));
    techManager.setPreferredSize(new Dimension (200, 100));
	 techManager.setBounds(10, 480, 200, 120);
    techManager.setVisible(true);
//for animal husbandry

    if(att.getTechVector().get(0) != null)	 
	 {
	   animal = new JButton("Use Animal Husbandry");
		animal.setIcon(getImage("data/animalIcon.png"));
	   animal.setBounds(00, 20, 80, 15);
      animalHusbandry = true;
		animal.addActionListener(this);
	 }
    else
	   animalHusbandry = false;

    if(att.getTechVector().get(21) != null)	 
	 {
	   biology = new JButton("Use Biology");
		biology.setIcon(getImage("data/bioIcon.png"));
	   biology.setBounds(00, 40, 80, 15);
      hasBiology = true;
		biology.addActionListener(this);
	 }
    else
	   animalHusbandry = false;

//for iron battle techs
    if(att.getTechVector().get(5) != null)	 
	 {
	   metal = new JButton("Use Metalworking");
		metal.setIcon(getImage("data/Iron.png"));
	   metal.setBounds(00, 60, 80, 15);
	 }

    if(att.getTechVector().get(16) != null)
	 {
	   math = new JButton("Use Mathematics");
		math.setIcon(getImage("data/Iron.png"));
	   math.setBounds(00, 80, 80, 15);
	 }

    if(att.getTechVector().get(30) != null)
	 {
	   ball = new JButton("Use Ballistics");
		ball.setIcon(getImage("data/Iron.png"));
	   ball.setBounds(00, 100, 80, 15);
	 }
 
    if(def.getNation().equals(def.playerUI.aiChoice))  
	 {
	   for(int k = 0; k < defHand.size(); k++)
      {
        Card temp = (Card)defHand.get(k);
        temp.getLabel().setVisible(true);    
		  if(att.getWonderCards(3))
		  {
		    defCards[k] = new JLabel(temp.getFrontIcon());     
		    temp.setLabelIcon(temp.getFrontIcon());
 		  } 
		  else
		  {
		    defCards[k] = new JLabel(temp.getCombatBackIcon());     
		    temp.setLabelIcon(temp.getBackIcon());
		  }
		  defCards[k].setBounds((160*(k+1)),(20), temp.getCombatBackIcon().getIconWidth(), temp.getCombatBackIcon().getIconHeight());
		  defPanel.add(temp.getLabel());

        defCards[k].addMouseListener(this);
	   }
    }
	 else if(def.getNation().equals(def.playerUI.playerChoice))  
	 {
	   for(int k = 0; k < defHand.size(); k++)
      {
        Card temp = (Card)defHand.get(k);
        temp.getLabel().setVisible(true);    
        defCards[k] = new JLabel(temp.getFrontIcon());     
		  defCards[k].setBounds((160*(k+1)),(20), temp.getFrontIcon().getIconWidth(), temp.getFrontIcon().getIconHeight());
		  temp.setLabelIcon(temp.getFrontIcon());
		  defPanel.add(defCards[k]);

        defCards[k].addMouseListener(this);
	   }
    }
    if(att.getNation().equals(att.playerUI.aiChoice))  
	 {
	   for(int k = 0; k < attHand.size(); k++)
      {
        Card temp = (Card)attHand.get(k);
        temp.getLabel().setVisible(true);    
        attCards[k] = new JLabel(temp.getCombatBackIcon());     
		  attCards[k].setBounds((160*(k+1)),(600), temp.getCombatBackIcon().getIconWidth(), temp.getCombatBackIcon().getIconHeight());
		  temp.setLabelIcon(temp.getBackIcon());
		  attPanel.add(temp.getLabel());
        attCards[k].addMouseListener(this);
	   }
    }
    else if(att.getNation().equals(att.playerUI.playerChoice))  
	 {
	   for(int k = 0; k < attHand.size(); k++)
      {
        Card temp = (Card)attHand.get(k);
        temp.getLabel().setVisible(true);    
        attCards[k] = new JLabel(temp.getFrontIcon());     
		  attCards[k].setBounds((160*(k+1)),(600), temp.getFrontIcon().getIconWidth(), temp.getFrontIcon().getIconHeight());
		  attPanel.add(attCards[k]);
        attCards[k].addMouseListener(this);
	   }
    }
    

	 combatBoard.add(attPanel);
	 combatBoard.add(defPanel);
	 combatBoard.add(board);
    combatBoard.setDefaultCloseOperation(0);
    combatBoard.setVisible(true);
    
//    CivGUI.c.add(combatBoard);
    choose = new JTextArea();
	 choose.setPreferredSize(new Dimension(210, 15));
	 choose.setOpaque(true);
	 
	 select = new JButton("Play Card");
	 newFront = new JButton("New Front");

	 board.add(choose, new Integer(30));
	 board.add(defGraveyard, new Integer(20));
	 board.add(attGraveyard, new Integer(20));
    board.add(techManager, new Integer(30));
  
  }
  
  private void initDefenderDialog(Player att, Player def){
    defCardsDef = new JLabel[defHandDef.size()];
	 attCardsDef = new JLabel[attHandDef.size()];
    attGraveyardDef = new JLayeredPane();
    defGraveyardDef = new JLayeredPane();
    JTextArea grave1 = new JTextArea("GRAVEYARD");
    JTextArea grave2 = new JTextArea("GRAVEYARD");
    grave1.setEditable(false);
    grave1.setBounds(0, 0, 100, 20); 
    grave2.setEditable(false);
    grave2.setBounds(0, 0, 100, 20); 
	 
    defGraveyardDef.setPreferredSize(new Dimension(165, 300));
	 defGraveyardDef.setBounds(860, 100, 160, 400);	 	   
	 defGraveyardDef.setForeground(def.getPlayerColor());
    defGraveyardDef.add(grave1, new Integer(0));
	  
    attGraveyardDef.setPreferredSize(new Dimension(165, 365));  
    attGraveyardDef.setBounds(860, 405, 160, 365);	 	   
	 attGraveyardDef.setForeground(att.getPlayerColor());
    attGraveyardDef.add(grave2, new Integer(0));

//need to change to if village dont draw, if human defender the draw there too	
	 combatBoardDef = new JDialog(att.playerUI.c, att.getNation());
    combatBoardDef.setResizable(false);
  
    if(defHandDef.size() > attHandDef.size())
	   combatBoardDef.setSize(new Dimension(1200, 800));
    else
	   combatBoardDef.setSize(new Dimension(1200, 800));

    attPanelDef = new JPanel();
	 attPanelDef.setBackground(att.getPlayerColor());
    attPanelDef.setBounds(00, 610, combatBoardDef.getWidth(), 160);

	 boardDef = new JLayeredPane();
	 boardDef.setBackground(new Color(255,0,0));
    boardDef.setBounds(00, 160, combatBoardDef.getWidth(), 800-attPanelDef.getHeight());

    choiceManagerDef = new JPanel();
    choiceManagerDef.setBackground(att.getPlayerColor());
    choiceManagerDef.setPreferredSize(new Dimension(100, 150));
	 choiceManagerDef.setBounds(00, 00, 100, 150);
	 choiceManagerDef.setVisible(true);
	 attPanelDef.add(choiceManagerDef);

    Army cu = mGUI.getCombatUnit();
    if(att.playerUI.lb.isCitySpace(cu.getX(), cu.getY()))
    {
	   Vector temp = att.getCityVector();
		for(int k = 0; k < temp.size(); k++)
		{
		  if(att.getCityXpos(k) == cu.getX() && att.getCityYpos(k) == cu.getY())
		    attBonus = att.getCityCombatBonus(temp.get(k));
		}  
    }  
	 else
      attBonus = att.getCombatBonus();
	 attBonusTextDef = new JTextArea("Bonus: "+Integer.toString(attBonus));
	 attBonusTextDef.setBounds(327, 586, 68, 25);
    attBonusTextDef.setEditable(false);
    attBonusTextDef.setForeground(new Color(250, 250, 250));
    attBonusTextDef.setBackground(new Color(0, 120, 205));
	 attBonusTextDef.setBorder(new MatteBorder(4, 4, 4, 4, getImage("data/goldBorder.png")));
	 
    defPanelDef = new JPanel();
	 defPanelDef.setBackground(def.getPlayerColor());
    defPanelDef.setBounds(00, 00, combatBoard.getWidth(), 90);

    if(att.playerUI.lb.isCitySpace(cu.getX(), cu.getY()))
    {
	   Vector temp = def.getCityVector();
		for(int k = 0; k < temp.size(); k++)
		{
		  if(def.getCityXpos(k) == cu.getX() && def.getCityYpos(k) == cu.getY())
		    defBonus = def.getCityCombatBonus(temp.get(k));
		}  
    }  
	 else
      defBonus = def.getCombatBonus();
	 defBonusTextDef = new JTextArea("Bonus: "+Integer.toString(defBonus));
    defBonusTextDef.setBounds(1, 90, 68, 25);
    defBonusTextDef.setEditable(false);
    defBonusTextDef.setForeground(new Color(250, 250, 250));
    defBonusTextDef.setBackground(new Color(0, 120, 205));
    defBonusTextDef.setBorder(new MatteBorder(4, 4, 4, 4, getImage("data/goldBorder.png")));

    if(attBonus > defBonus)
	 {
      attBonus -= defBonus; 
      attBonusTextDef.setText("Bonus: "+Integer.toString(attBonus));
      defBonus = 0;  
	 } 
    else
      attBonusTextDef.setText("Bonus: 0");
	   
	 if(attBonus < defBonus)
    {
      defBonus -= attBonus; 
      defBonusTextDef.setText("Bonus: "+Integer.toString(defBonus));
      attBonus = 0;  
    }
    else
      defBonusTextDef.setText("Bonus: 0");
 
    boardDef.add(attBonusTextDef, new Integer(21));
	 boardDef.add(defBonusTextDef, new Integer(21));
    techManagerDef = new JPanel();
    techManagerDef.setPreferredSize(new Dimension (200, 100));
	 techManagerDef.setBounds(10, 480, 200, 120);
    techManagerDef.setVisible(true);

    if(att.getTechVector().get(0) != null)	 
	 {
	   animalDef = new JButton("Use Animal Husbandry");
		animalDef.setIcon(getImage("data/animalIcon.png"));
	   animalDef.setBounds(00, 20, 80, 15);
      animalHusbandry = true;
		animalDef.addActionListener(this);
	 }
    else
	   animalHusbandry = false;

    if(att.getTechVector().get(21) != null)	 
	 {
	   biologyDef = new JButton("Use Biology");
		biologyDef.setIcon(getImage("data/bioIcon.png"));
	   biologyDef.setBounds(00, 40, 80, 15);
      hasBiology = true;
		biologyDef.addActionListener(this);
	 }
    else
	   animalHusbandry = false;

//for iron battle techs
    if(att.getTechVector().get(5) != null)	 
	 {
	   metalDefend = new JButton("Use Metalworking");
		metalDefend.setIcon(getImage("data/Iron.png"));
	   metalDefend.setBounds(0, 60, 80, 15);
	 }

    if(att.getTechVector().get(16) != null)
	 {
	   mathDefend = new JButton("Use Mathematics");
		mathDefend.setIcon(getImage("data/Iron.png"));
	   mathDefend.setBounds(0, 80, 80, 15);
	 }

    if(att.getTechVector().get(30) != null)
	 {
	   ballDefend = new JButton("Use Ballistics");
		ballDefend.setIcon(getImage("data/Iron.png"));
	   ballDefend.setBounds(0, 100, 80, 15);
	 }
 
    if(def.getNation().equals(def.playerUI.aiChoice))  
	 {
	   for(int k = 0; k < defHandDef.size(); k++)
      {
        Card temp = (Card)defHandDef.get(k);
        temp.getLabel().setVisible(true);    
		  if(att.getWonderCards(3))
		  {
		    defCardsDef[k] = new JLabel(temp.getFrontIcon());     
		    temp.setLabelIcon(temp.getFrontIcon());
 		  } 
		  else
		  {
		    defCardsDef[k] = new JLabel(temp.getCombatBackIcon());     
		    temp.setLabelIcon(temp.getBackIcon());
		  }
		  defCardsDef[k].setBounds((160*(k+1)),(20), temp.getCombatBackIcon().getIconWidth(), temp.getCombatBackIcon().getIconHeight());
		  defPanelDef.add(temp.getLabel());

        defCardsDef[k].addMouseListener(this);
	   }
    }
	 else if(def.getNation().equals(def.playerUI.playerChoice))  
	 {
	   for(int k = 0; k < defHandDef.size(); k++)
      {
        Card temp = (Card)defHandDef.get(k);
        temp.getLabel().setVisible(true);    
        defCardsDef[k] = new JLabel(temp.getFrontIcon());     
		  defCardsDef[k].setBounds((160*(k+1)),(20), temp.getFrontIcon().getIconWidth(), temp.getFrontIcon().getIconHeight());
		  temp.setLabelIcon(temp.getFrontIcon());
		  defPanelDef.add(defCardsDef[k]);

        defCardsDef[k].addMouseListener(this);
	   }
    }
    if(att.getNation().equals(att.playerUI.aiChoice))  
	 {
	   for(int k = 0; k < attHandDef.size(); k++)
      {
        Card temp = (Card)attHandDef.get(k);
        temp.getLabel().setVisible(true);    
        attCardsDef[k] = new JLabel(temp.getCombatBackIcon());     
		  attCardsDef[k].setBounds((160*(k+1)),(600), temp.getCombatBackIcon().getIconWidth(), temp.getCombatBackIcon().getIconHeight());
		  temp.setLabelIcon(temp.getBackIcon());
		  attPanelDef.add(temp.getLabel());
        attCardsDef[k].addMouseListener(this);
	   }
    }
    else if(att.getNation().equals(att.playerUI.playerChoice))  
	 {
	   for(int k = 0; k < attHandDef.size(); k++)
      {
        Card temp = (Card)attHandDef.get(k);
        temp.getLabel().setVisible(true);    
        attCardsDef[k] = new JLabel(temp.getFrontIcon());     
		  attCardsDef[k].setBounds((160*(k+1)),(600), temp.getFrontIcon().getIconWidth(), temp.getFrontIcon().getIconHeight());
		  attPanelDef.add(attCardsDef[k]);
        attCardsDef[k].addMouseListener(this);
	   }
    }
    

	 combatBoardDef.add(attPanelDef);
	 combatBoardDef.add(defPanelDef);
	 combatBoardDef.add(boardDef);
    combatBoard.setDefaultCloseOperation(0);
    combatBoardDef.setVisible(true);
    
//    CivGUI.c.add(combatBoard);
    chooseDef = new JTextArea();
	 chooseDef.setPreferredSize(new Dimension(210, 15));
	 chooseDef.setOpaque(true);
	 
	 selectDef = new JButton("Play Card");
	 newFrontDef = new JButton("New Front");

	 boardDef.add(chooseDef, new Integer(30));
	 boardDef.add(defGraveyardDef, new Integer(20));
	 boardDef.add(attGraveyardDef, new Integer(20));
    boardDef.add(techManagerDef, new Integer(30));
  
  }

  public ImageIcon getImage(String pathName)
  {
    ImageIcon image;
    java.net.URL url = getClass().getResource(pathName); 
    if(url != null)       
    {
	   image = new ImageIcon(url);
		return image;    
    }
    else 
      System.out.println("NULL IMAGE IN COMBAT");       
	 return null;
  }

  private void performActions()
  {
    if(def.getNation().equals("Village"))
	 {
	   aiDefend();
	 }
	 else
	 {
	   playerDefend();
    }
  }
  
  private void aiDefend()
  {
//	 if(defSize <= 0)
//	   playerAttack();
    
    //do defend stuff 
    if(numAiFronts == 0)
	 {
	   int rand = dice.rollX(defSize);
	   Card temp = (Card)defHand.remove(rand);  
	   defPanel.remove(temp.getLabel());
      fronts[numAiFronts+numPlayerFronts] = new Front(170*(numAiFronts+numPlayerFronts), 00, null, temp);
      fronts[numAiFronts+numPlayerFronts].getDefPane().addMouseListener(this);
	   numAiFronts++;	 
//		addAiFront(temp.getLabel(), temp.getFrontIcon(), rand);
      defSize--;
	 }
	 else if(numAiFronts > 0)
	 {
      int play = -1;
      int playCard = -1;
	   for(int k = 0; k < numAiFronts+numPlayerFronts; k++)
		{
		  for(int j = 0; j < defHand.size(); j++)
		  {
		    if(fronts[k].getAtt() != null)  
			 {
			   Card temp = (Card)defHand.get(j);  
				if((fronts[k].getDef() == null) && (temp.trumpsCard(fronts[k].getAtt())))  
		      {
				  play = k;
				  playCard = j;
				}  
			 }
		  }
		}
	   if(play >= 0)
	   {
		  Card temp = (Card)defHand.remove(playCard);
//	     defPanel.remove(defCards[playCard]);
//        defenseCard = playCard;
	     defPanel.remove(temp.getLabel());
		  fronts[play].drawFront(temp);
	     if(fronts[play].getDefPane() != null)  
		    fronts[play].getDefPane().addMouseListener(this);
	     defSize--;
		}
	   else
      {
		   int rand = dice.rollX(defSize);
		   Card temp = (Card)defHand.remove(rand);  
//stupid card does not remove itself!!!!
		   defPanel.remove(temp.getLabel());
	      fronts[numAiFronts+numPlayerFronts] = new Front(170*(numAiFronts+numPlayerFronts), 00, null, temp);
	      fronts[numAiFronts+numPlayerFronts].getDefPane().addMouseListener(this);
		   numAiFronts++;	 
	      defSize--;
      }	 
	 }
	 if(attSize <= 0 && defSize <= 0)
	   combatEnd();
    else if(attSize <= 0)  
	   aiDefend();
	 else
      playerAttack();
  }

  private void addAiFront(JLabel l, ImageIcon c, int rem)
  {
    JLabel front = l;
	 front.setIcon(c);
	 front.setBounds(160*(numAiFronts+numPlayerFronts), 160, 160, 160);
	 front.setBorder(new BevelBorder(1, new Color(0, 0, 0), new Color(0, 0, 0)));
    	 
	 defPanel.remove(defCards[rem]);
    defPanel.validate();
	 defPanel.repaint();

	 board.add(front);
    board.validate();
	 board.repaint();

    defFront.add(l);
//	 l.addMouseListener(this);
	 numAiFronts++;	 
  }
  
  private void addPlayerFront()
  {
	   Card temp = (Card)attHand.get(attackCard);  
      fronts[numAiFronts+numPlayerFronts] = new Front(170*(numAiFronts+numPlayerFronts), 160, temp, null);
      fronts[numAiFronts+numPlayerFronts].getAttPane().addMouseListener(this);
	   numPlayerFronts++;	 
      attCards[attackCard].setBorder(null);
  }
 
  private void playerAttack()
  {
    choose.setText("Choose attack card");
	 
	 choose.setBounds(400, 580, 200, 20);  
    choose.setEditable(false);
//	 select.setBounds(10, 10, 60, 20);
//	 newFront.setBounds(10, 80, 60, 20);

//    newFront.addActionListener(this);

    choiceManager.add(select);
    select.addActionListener(this);
	 
    if(metalListen)
	 {
      techManager.remove(metal);
	   metal.removeActionListener(this);
      metalListen = false;
	 }
	 
//	 if(mathListen)  
//	 {
//      techManager.remove(math);
//	   math.removeActionListener(this);
//      mathListen = false;
//	 }

    if(att.getIron() > 0 && att.getTechVector().get(16) != null && numAiFronts > 0)
	 {
//System.out.println("AFTERMetalBtn: "+attPanel);
	   techManager.add(math);
	   mathListen = true;    
		math.addActionListener(this);
	 }

    if(att.getIron() > 0 && att.getTechVector().get(30) != null && numAiFronts > 0)
	 {
//System.out.println("AFTERMetalBtn: "+attPanel);
	   techManager.add(ball);
	   ballListen = true;    
		ball.addActionListener(this);
	 }

    if(hasBiology && attTotalWounds > 0)
	 {
	   techManager.add(biology);
	 }
	 
//	 techManager.validate();
	 techManager.repaint();
	 choiceManager.remove(newFront);
    newFront.removeActionListener(this);
//    attPanel.add(newFront);
  }
    
  private void playerDefend()
  {
  
  }
  
  private void shuffle(Vector c)
  {
  //shuffle the deck and get the top x card, x = hand size limit
    Random rng = new Random();  
	 Die dice = new Die(rng.nextInt(666));
    Object[] obj = new Object[c.size()]; 
    obj = c.toArray();
	 for(int k = 0; k < c.size(); k++)  
    {
	   int rand = dice.rollX(c.size());  
		Object temp = obj[k];
		obj[k] = obj[rand];
		c.remove(k);    
		c.add(k, obj[rand]);
		obj[rand] = temp;
      c.remove(rand);    
		c.add(rand, temp);	   
	 }
  }

  public void combatEnd()
  {
     board.remove(0);
	  attPanel.removeAll();   
     choiceManager.removeAll();
	  techManager.removeAll();
	  defPanel.removeAll();
//	  select.removeActionListener(this);
	  
//     attPanel.remove(newFront);
//     newFront.removeActionListener(this);
     
	  Vector surviveAtt = new Vector(0, 1);  
     Vector surviveDef = new Vector(0, 1);  

	  String winner, returnWinner;

	  int totalDef=0, totalAtt=0;
	  for(int k = 0; k < (numAiFronts+numPlayerFronts); k++)
	  {
	    Front f = fronts[k];
		 if(f.getDef() != null)
		 {
			totalDef += (f.getDef().getValue()+defCastleValue);
			Card temp = f.getDef();
			temp.resetWounds();
		   surviveDef.add(temp);
//System.out.println("DEFSURVIVE: "+k+ " "+f.getDef().getType());		   
	      if(def.getNation().equals("Village"))
		   {
			  f.defGraveyard(temp);				
	        if(board.getIndexOf(f.attPane) >= 0)  
		       board.remove(board.getIndexOf(f.attPane));
	      }
		 }
		 if(f.getAtt() != null)
		 {
		   totalAtt += (f.getAtt().getValue() + attCastleValue);
         Card temp = f.getAtt(); 
			temp.resetWounds();
		   surviveAtt.add(temp);
//System.out.println("ATTACKSURVIVE: "+k+ " "+f.getAtt().getType());		   
		 }
	    if(def.getNation().equals("Village"))
		 {
	      if(board.getIndexOf(f.attPane) >= 0)  
		     board.remove(board.getIndexOf(f.attPane));
	    }
//	    if(board.getIndexOf(f) >= 0)  
//		   board.remove(board.getIndexOf(f));
	  }  
     totalDef += defBonus;
	  totalAtt += attBonus;
	  
	  JOptionPane victor = new JOptionPane();
	  ImageIcon im = getImage("data/"+att.playerUI.playerChoice+"Icon.png");  

	  if(totalAtt > totalDef)
     {
	    winner = att.getNation();
	    if(winner.equals("Village"))	 
       {
	      victor.showMessageDialog(board, "YOU LOSE!", "COMBAT RESULT",
	  													 JOptionPane.INFORMATION_MESSAGE, im);	 	 
	  		returnWinner = "Village";
       }
		 else
		 {
         returnWinner = "attacker";
		 }
	  }
	  else
     {
	    winner = def.getNation();
	    if(winner.equals("Village"))	 
		 {
	      victor.showMessageDialog(board, "YOU LOSE!", "COMBAT RESULT",
	  													 JOptionPane.INFORMATION_MESSAGE, im);	 	 
         returnWinner = "Village";
		 }
		 else
	    {
         returnWinner = "defender";
	    }
	  } 

	  if(winner.equals(att.playerUI.playerChoice) && !def.getNation().equals("Village"))
	  {
	    victor.showMessageDialog(board, "YOU WIN! \nPREPARE TO REAP THE SPOILS", "COMBAT RESULT",
	  													 JOptionPane.INFORMATION_MESSAGE, im);	 	 
	    if(att.getNation().equals(att.playerUI.playerChoice))
         returnWinner = "attacker";
		 else
         returnWinner = "defender";
		 getSpoils(winner);
	  }
	  else if(winner.equals(att.playerUI.aiChoice) && !winner.equals("Village"))
     {
	    victor.showMessageDialog(board, "YOU LOSE! \nPREPARE TO BE PLUNDERED", "COMBAT RESULT",
	  													 JOptionPane.INFORMATION_MESSAGE, im);	 	 
       if(att.getNation().equals(att.playerUI.aiChoice))
         returnWinner = "attacker";
		 else
         returnWinner = "defender";
	    getSpoils(winner);
     }

 	  if(att.getNation().equals("China") && attGraveCards.size() > 0)
	  {
       JOptionPane save = new JOptionPane();    
		 Vector tempc = new Vector(0, 1);
       for(int k = 0; k < attGraveCards.size(); k++)
		 {
		   Card tempCard = (Card)attGraveCards.get(k);
			tempc.add((GameRotatedIcon)tempCard.getFrontIcon());
		 }
       Object[] options = tempc.toArray();
	    int choice = -1;
       while(choice < 0)
       {
			choice = save.showOptionDialog(board, "Pick a unit to save", "China",
                           JOptionPane.CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
                           null, options, null);
	    }
	    surviveAtt.add(attGraveCards.get(choice));
	  }
	  
 	  if(def.getNation().equals("China") && defGraveCards.size() > 0)
	  {
       JOptionPane save = new JOptionPane();    
		 Vector tempc = new Vector(0, 1);
       for(int k = 0; k < defGraveCards.size(); k++)
		 {
		   Card tempCard = (Card)defGraveCards.get(k);
			tempc.add((GameRotatedIcon)tempCard.getFrontIcon());
		 }
       Object[] options = tempc.toArray();
	    int choice = -1;
       while(choice < 0)
       {
			choice = save.showOptionDialog(board, "Pick a unit to save", "China",
                           JOptionPane.CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
                           null, options, null);
	    }
	    surviveDef.add(defGraveCards.get(choice));
	  }
	   
	  if(excessDefCards != null)
     {
		  for(int k = 0; k < excessDefCards.size(); k++)
		  {
		    surviveDef.add(excessDefCards.get(k));
		  }
	  }
	   
 	  if(excessAttCards != null)
     {
		  for(int k = 0; k < excessAttCards.size(); k++)
		  {
		    surviveAtt.add(excessAttCards.get(k));
		  }
     }
	    
	  if(!def.getNation().equals("Village")) 
	  {
	    def.postCombatHand(surviveDef); 
	  }
	  att.postCombatHand(surviveAtt); 

     att.playerUI.checkCombatDecks();//check for casualties and empty draw decks
  
     mGUI.combatResume(returnWinner);
	  combatBoard.dispose();  
  }

  private void getSpoils(String s)
  {
//    if(att.getNation().equals(CivGUI.playerChoice))
//	    CivGUI.updateUnits(att);
//	 else
//	 	 CivGUI.updateUnits(def);
  //choose spoils of victory per rules  
  }
  
  protected void healAllWounds()
  {
    for(int k = 0; k < (numPlayerFronts+numAiFronts); k++)
	 {
	   if(fronts[k].getAtt() != null)
		{
         int i = fronts[k].getAtt().getWounds();         
			for(int j = 0; j < i; j++)
         {
		     fronts[k].getAtt().subWound(1);
           fronts[k].getAttPane().remove(0);
           fronts[k].getAttPane().repaint();
	        attTotalWounds--;
//System.out.println("HEALED IN BIOLOGY "+attTotalWounds);
		   }
		}
	 }
//    choiceManager.add(select);
//	 choiceManager.repaint();	  
//    choose.setText("Choose attack card");    
    playerAttack();  
  }

  protected void addAttListener(JLayeredPane p)
  {
    p.addMouseListener(this);
  }
  
  protected void removeMListener(JLayeredPane p)
  {
    p.removeMouseListener(this);
  }

  public void actionPerformed(ActionEvent e) 
  {
    if(e.getSource() == select)
    {  
      if(attCards[attackCard].getBorder() != null)    
		{
			choiceManager.remove(select);    
	      select.removeActionListener(this);
	
	      choiceManager.add(newFront);    
	      newFront.addActionListener(this);
	
  	      techManager.removeAll();
         techManager.repaint();    
			      
			if(att.getIron() > 0 && att.getTechVector().get(5) != null)
		   {
//System.out.println("AFTERMetalBtn: "+attPanel);
		     techManager.add(metal);
	        techManager.repaint();    
			  metalListen = true;    
			  metal.addActionListener(this);
	      }
	      
			attPanel.validate();
	      attPanel.repaint();
			choose.setText("Select front to attack");
	      attackSelected = true;
	//		if(attSize >= 0)
	//        aiDefend();
	   }
	 }
    else if(e.getSource() == newFront && attackSelected)
    {  
//      attPanel.remove(select);    
      choiceManager.remove(newFront);    
      choiceManager.repaint();
//      select.removeActionListener(this);
      newFront.removeActionListener(this);
      attPanel.remove(attCards[attackCard]);
      attPanel.validate();
      attPanel.repaint();
//      combatBoard.validate();
      addPlayerFront();
      attSize--; 
      attackSelected = false;  

	   if(attSize <= 0 && defSize <= 0)
	     combatEnd();
      else if(defSize <= 0)  
	     playerAttack();
      else
		  aiDefend();
	 }
    else if(e.getSource() == metal && attackSelected)
	 {
        metalAtt = true; 
		  att.spendResource("Iron");
		  techManager.remove(metal);
	     metal.removeActionListener(this);

        techManager.validate();
        techManager.repaint();
	 }
    else if(e.getSource() == animal)
	 {
      animalHusbandryActive = true; 
	   heal = 0;
      
      choose.setText("Select wound(s) to heal");

		choiceManager.removeAll();
	   techManager.removeAll();
	   techManager.repaint();  
	 }
    else if(e.getSource() == biology)
	 {
      hasBiology = false;
      biology.removeActionListener(this);
      techManager.removeAll();
	   techManager.repaint();  
		healAllWounds();      
	 }
    else if(e.getSource() == math)
	 {
        mathActive = true; 
		  att.spendResource("Iron");
		  techManager.removeAll();
	     math.removeActionListener(this);

		  techManager.validate();
        techManager.repaint();
	  
        choose.setText("Select units to wound: "+ mathWounds + " wounds left");
	     choiceManager.removeAll();
	 }
    else if(e.getSource() == ball)
	 {
        ballActive = true; 
		  att.spendResource("Iron");
		  techManager.removeAll();
	     ball.removeActionListener(this);

		  techManager.validate();
        techManager.repaint();
	  
        choose.setText("Select units to wound: "+ ballWounds + " wounds left");
	     choiceManager.removeAll();
	 }
  }
 
  public void mouseClicked(MouseEvent e)
  {
    for(cardChoice = 0; cardChoice < attHand.size(); cardChoice++)  
	 {
	   if(e.getSource() == attCards[cardChoice])
      {
        if(!attackSelected)
		  {
			  attackCard = cardChoice;    
			  JComponent c = (JComponent)e.getSource();
			  for(int k = 0; k < attHand.size(); k++)
			    if(attCards[k].getBorder() != null)
	            attCards[k].setBorder(null);
	        c.setBorder(new BevelBorder(1, new Color(0, 0, 0), new Color(0, 0, 0)));
////System.out.println(c.getBorder());    
        }  
	   }
	 }
    for(frontChoice = 0; frontChoice < numAiFronts+numPlayerFronts; frontChoice++)
    {
	   if(e.getSource() == fronts[frontChoice].getDefPane() && attackSelected)
	   {
        Card temp = (Card)attHand.get(attackCard);
        fronts[frontChoice].drawFront(temp);
        attCards[attackCard].setBorder(null);
	     attackSelected = false;  
        attSize--; 
	     if(attSize <= 0 && defSize <= 0)
	       combatEnd();
        else if(defSize <= 0)  
	       playerAttack();
        else
		    aiDefend();
		}  
	   else if(e.getSource() == fronts[frontChoice].getAttPane() && animalHusbandryActive)
	   {
//System.out.println("ANIMAL FRONT CHOICE");        
	     choose.setText("Select wound(s) to heal");  
		  animalHusbandry = false;
		  fronts[frontChoice].healWound();
		}  
      else if(e.getSource() == fronts[frontChoice].getDefPane() && mathActive)
		{
		  fronts[frontChoice].dealMathWounds();
//System.out.println("FRONT CHOICE "+frontChoice);
      }
      else if(e.getSource() == fronts[frontChoice].getDefPane() && ballActive)
		{
		  fronts[frontChoice].dealBallWounds();
//System.out.println("FRONT CHOICE "+frontChoice);
      }
    }
  }

  public void mouseEntered(MouseEvent e){}//end mouseEntered
  public void mousePressed(MouseEvent e){}//end mousePressed
  public void mouseReleased(MouseEvent e){}//end mouseReleased
  public void mouseExited(MouseEvent e){}//end mouseExited
 

  class Front extends JComponent
  {
     JLayeredPane attPane, defPane;
	  Card attack, defend;
     int xPos, yPos;
     JLabel attFront, defFront;	  
	  
	  protected Front(int x, int y, Card acard, Card dcard)
	  {
	    attPane = new JLayeredPane();  
	    defPane = new JLayeredPane();  
		 xPos = x;
		 yPos = y;
		 attack = acard;
		 defend = dcard;
       drawFront();
     }
	  
     public void redrawDefFront()
	  {
	     if(defPane.getIndexOf(defFront) >= 0)  
		    defPane.remove(defPane.getIndexOf(defFront));
	     if(board.getIndexOf(defPane) >= 0)  
 		    board.remove(board.getIndexOf(defPane));

//        attack = null;
		      
		  drawFront();			 
	  }

     public void redrawAttFront()
	  {
	     if(attPane.getIndexOf(attFront) >= 0)  
	       attPane.remove(attPane.getIndexOf(attFront));
	     if(board.getIndexOf(attPane) >= 0)  
		    board.remove(board.getIndexOf(attPane));
        
//		  defend = null;
    
	     drawFront();			 
	  }

     public void drawFront()
	  {
       if(metalAtt)
		 {
         metalAtt = false;    
		 }
		 if(metalDef)
       {
         metalDef = false;    
		 }

       if(attack == null)
		 {
		    defFront = defend.getLabel();
		    defFront.setIcon(defend.getFrontIcon());
		    defFront.setBounds(00, 00, 160, 160);
		    defFront.setBorder(new BevelBorder(1, new Color(0, 0, 0), new Color(0, 0, 0)));
	    	 
	       defPanel.validate();
		    defPanel.repaint();
	
	       defPane.setBounds(xPos, 160, 160, 160);
		    defPane.setBorder(new BevelBorder(1, new Color(0, 0, 0), new Color(0, 0, 0)));
	       defPane.add(defFront, new Integer(1));
			 
		    board.add(defPane, new Integer(1));
	       board.validate();
		    board.repaint();
		 }
       else if(defend == null)
		 {
		    attFront = attack.getLabel();
		    attFront.setIcon(attack.getFrontIcon());
		    attFront.setBounds(00, 00, 160, 160);
		    attFront.setBorder(new BevelBorder(1, new Color(0, 0, 0), new Color(0, 0, 0)));
	    	 
	       attPanel.validate();
		    attPanel.repaint();
	
	       attPane.setBounds(xPos, 320, 160, 160);
		    attPane.setBorder(new BevelBorder(1, new Color(0, 0, 0), new Color(0, 0, 0)));
	       attPane.add(attFront, new Integer(1));
			 
		    board.add(attPane, new Integer(1));
	       board.validate();
		    board.repaint();
		 }
	  }

     public void drawFront(Card c)
	  {
	    if(defend == null)
		 {
//System.out.println("DEFEND");		 
		    defend = c;  

          defPanel.remove(defCards[defenseCard]);
          defPanel.validate();
			 defPanel.repaint();

			 defFront = defend.getLabel();
		    defFront.setIcon(defend.getFrontIcon());
		    defFront.setBounds(00, 00, 160, 160);
		    defFront.setBorder(new BevelBorder(1, new Color(0, 0, 0), new Color(0, 0, 0)));

	       defPane.setBounds(xPos, 160, 160, 160);
		    defPane.setBorder(new BevelBorder(1, new Color(0, 0, 0), new Color(0, 0, 0)));
			 defPane.add(defFront, new Integer(1));

		    board.add(defPane, new Integer(1));
	       board.validate();
		    board.repaint();
		 
			 if(attack != null)
			   doCombat();
	       if(attack == null && defend == null && !mathActive && !ballActive)
		      numAiFronts--;
		 }
		 else if(attack == null)
		 {
//System.out.println("ATTACK");		 
		    attack = c;  


          attPanel.remove(attCards[attackCard]);
          attPanel.validate();
			 attPanel.repaint();

			 attFront = attack.getLabel();
		    attFront.setIcon(attack.getFrontIcon());
		    attFront.setBounds(00, 00, 160, 160);
		    attFront.setBorder(new BevelBorder(1, new Color(0, 0, 0), new Color(0, 0, 0)));
	    	 
	
	       attPane.setBounds(xPos, 320, 160, 160);
		    attPane.setBorder(new BevelBorder(1, new Color(0, 0, 0), new Color(0, 0, 0)));
			 attPane.add(attFront, new Integer(1));

		    board.add(attPane, new Integer(1));
	       board.validate();
		    board.repaint();
			   
			 if(defend != null)
			   doCombat();
	       if(attack == null && defend == null && !mathActive &&!ballActive)
		      numPlayerFronts--;
	    }
	  }
	    
     protected void doCombat()
	  {
	    String attType = attack.getType();
	    String defType = defend.getType();
		 int attStr;
		 if(metalAtt)
		 {
		   attStr = attack.getValue()+3+attCastleValue;
//System.out.println("Metal attstr: "+attStr);
		 }    
		 else
		 {
		   attStr = attack.getValue()+attCastleValue;
		 } 
		 int defStr;
		 if(metalDef)
		 {
		   defStr = defend.getValue()+3+defCastleValue;
//System.out.println("Metal DEFstr: "+defStr);
       }	       
		 else  
		 {
		   defStr = defend.getValue()+defCastleValue;
		 }
		 boolean defTrump = false;
		 boolean attTrump = false;
		 		 
		 if(attType.equals("infantry") && defType.equals("mounted"))
		   attTrump = true;
		 else if(attType.equals("mounted") && defType.equals("artillery"))
		   attTrump = true;
		 else if(attType.equals("artillery") && defType.equals("infantry"))
		   attTrump = true;
		 if(defType.equals("infantry") && attType.equals("mounted"))
		   defTrump = true;
		 else if(defType.equals("mounted") && attType.equals("artillery"))
		   defTrump = true;
		 else if(defType.equals("artillery") && attType.equals("infantry"))
		   defTrump = true;
	
	    if(attTrump)
		 {
//System.out.println("attTrump");
		   defend.addWounds(attStr);
		   if((defStr-defend.getWounds()) <= 0)
           defGraveyard();
         else
			{
//System.out.println("attTrump ELSE");//test mnt trump
			  drawWounds("def");
		     attack.addWounds(defStr);  
           if((attStr-attack.getWounds()) <= 0)
			    attGraveyard();
			  else
			    drawWounds("att");
			}
		 }	 
	    else if(defTrump)
		 {
		   attack.addWounds(defStr);
		   if((attStr-attack.getWounds()) <= 0)
           attGraveyard();
         else
			{
			  drawWounds("att");
		     defend.addWounds(attStr);  
           if((defStr-defend.getWounds()) <= 0)
			    defGraveyard();
			  else
			    drawWounds("def");
			}
		 }	 
	    else
		 {
			attack.addWounds(defStr);
		   if((attStr-attack.getWounds()) <= 0)
           attGraveyard();
		   else
			{ 
		     drawWounds("att");
         }  
			defend.addWounds(attStr);
         if((defStr-defend.getWounds()) <= 0)
			  defGraveyard();
	      else
			{
	        drawWounds("def");
		   }
		 }
       if(metalAtt)
		 {
         metalAtt = false;    
		   attStr -= 3;
//System.out.println("Metal attstr: "+attStr);
//System.out.println("Wounds "+ attack.getWounds());
		   if((attStr - attack.getWounds()) <= 0)
           attGraveyard();
		 }
		 if(metalDef)
       {
         metalDef = false;    
         defStr -= 3;      
			if((defStr-defend.getWounds()) <= 0)
			  defGraveyard();
		 }
	  }
	  
     protected void defGraveyard()
	  {
//System.out.println("Defender dies");
		 if(defPane.getIndexOf(defFront) >= 0)
		   defPane.remove(defPane.getIndexOf(defFront));  
       JLabel label = new JLabel();    
       label = defend.getLabel();
		 label.setIcon(defend.getFrontIcon());
		 label.setBounds(00, (defGraveSize*20)+20, 150, 150);
	    defGraveyard.add(label, new Integer(defGraveSize));//, new Integer(defGraveSize));  
	    defPane.removeAll();  //added after working
		 board.remove(board.getIndexOf(defPane));
		 defGraveCards.add(defend);
		 def.playerUI.addUnitToGrave(defend);
		 
		 defend = null;
	    defGraveSize++;  
   
	    board.repaint();

		 if(!mathActive && !ballActive)
		 { 
		   numAiFronts--;
		   numPlayerFronts++;
	    }
	  }

     public void defGraveyard(Card c)
	  {
//System.out.println("Defender dies");
		 if(defPane.getIndexOf(defFront) >= 0)
		   defPane.remove(defPane.getIndexOf(defFront));  
       JLabel label = new JLabel();    
       label = c.getLabel();
		 label.setIcon(c.getFrontIcon());
		 label.setBounds(00, (defGraveSize*20)+20, 150, 150);
	    defGraveyard.add(label, new Integer(defGraveSize));//, new Integer(defGraveSize));  
	    defPane.removeAll();  //added after working
		 board.remove(board.getIndexOf(defPane));
		 defGraveCards.add(c);
		 def.playerUI.addUnitToGrave(c);

		 defend = null;
	    defGraveSize++;  

	    board.repaint();

	    if(!mathActive && !ballActive)
		 { 
		   numAiFronts--;
		   numPlayerFronts++;
	    }
	  }

     protected void attGraveyard()
	  {
//ystem.out.println("Attack dies: "+attGraveSize);
       attTotalWounds -= attack.getWounds();

		 if(attPane.getIndexOf(attFront) >= 0)
	      attPane.remove(attPane.getIndexOf(attFront));  
		 JLabel label = new JLabel();    
		 label = attack.getLabel();
		 label.setIcon(attack.getFrontIcon());
		 label.setBounds(00, (attGraveSize*20)+20, 150, 150);
	    attGraveyard.add(label, new Integer(attGraveSize));  
	    attPane.removeAll(); //added after working 
       board.remove(board.getIndexOf(attPane));
		 attGraveCards.add(attack);
		 att.playerUI.addUnitToGrave(attack);

		 attack = null;
	    attGraveSize++;  

	    board.repaint();

	    numAiFronts++;
		 numPlayerFronts--;

//	    if(attTotalWounds <= 0)
//	  	 {
//			techManager.remove(animal);
//         techManager.repaint();
//		 }
	  } 

     protected void attGraveyard(Card c)
	  {
//System.out.println("Attack dies: "+attGraveSize);
       attTotalWounds -= attack.getWounds();

		 if(attPane.getIndexOf(attFront) >= 0)
	      attPane.remove(attPane.getIndexOf(attFront));  
		 JLabel label = new JLabel();    
		 label = c.getLabel();
		 label.setIcon(c.getFrontIcon());
		 label.setBounds(00, (attGraveSize*20)+20, 150, 150);
	    attGraveyard.add(label, new Integer(attGraveSize));  
	    attPane.removeAll(); //added after working 
       board.remove(board.getIndexOf(attPane));
		 attGraveCards.add(c);
		 att.playerUI.addUnitToGrave(c);

		 attack = null;
	    attGraveSize++;  

	    board.repaint();

	    numAiFronts++;
		 numPlayerFronts--;

//	    if(attTotalWounds <= 0)
//	  	 {
//			techManager.remove(animal);
//       techManager.repaint();
//		 }
	  } 

     protected void drawWounds(String s)
	  {
//	    if(attPane.getIndexOf(addWnd) >= 0)
//		   attPane.remove(attPane.getIndexOf(addWnd));  
//	    if(defPane.getIndexOf(addWnd) >= 0)
//		   defPane.remove(defPane.getIndexOf(addWnd));  
       if(s.equals("att"))
		 {
			for(int k = 0; k < attack.getWounds(); k++)  
			{
			  attTotalWounds++;
			  ImageIcon wnd = getImage("data/wound.png");
		     JLabel addWnd = new JLabel(wnd);
			  addWnd.setBounds(00+(25*k), 70, 25, 25);
			  attPane.add(addWnd, new Integer(attTotalWounds + 3));
 //System.out.println("Attacker label " + attPane.getIndexOf(addWnd));
			}   
         if(animalHusbandry && attTotalWounds > 0)
			{
			  techManager.add(animal);
           techManager.repaint();
		   }
		 }
		 else if(s.equals("def"))
	    {
		   for(int k = 0; k < defend.getWounds(); k++)  
			{
			  defTotalWounds++;
			  ImageIcon wnd = getImage("data/wound.png");
		     JLabel addWnd = new JLabel(wnd);
			  addWnd.setBounds(00+(25*k), 70, 25, 25);
			  defPane.add(addWnd, new Integer(defTotalWounds + 3));
 //System.out.println("Defender label " + defPane.getIndexOf(addWnd));
			}   
		 }
	  }
	  
	  protected void healWound()
	  {
       attPane.remove(0);
		 attTotalWounds--;
//System.out.println("HEAL WOUND: "+attTotalWounds);        
		 heal++;
	    attPane.repaint();	  

       if(attTotalWounds == 0 || heal == 3)
		 {
         animalHusbandryActive = false; 
 	      choiceManager.add(select);
	  		choiceManager.repaint();	  
         choose.setText("Choose attack card");    
		 }  
	  }     
	  
	  protected void dealMathWounds()
	  {
		 int defStr = 0;
		 if(defend != null)
		 	 defStr = defend.getValue()+defCastleValue;
       defend.addWounds(1);
		 drawWounds("def");
		 mathWounds--;
		 if(defStr-defend.getWounds() <= 0)
		 {
			defGraveyard();
         mathDeaths++;    
//System.out.println("mathDeath "+mathDeaths);
		 }
//System.out.println("MATH BEFOR RESET "+mathWounds+" "+numAiFronts);
       choose.setText("Select units to wound: "+ mathWounds + " wound(s) left");

		 if(mathWounds == 0 || (numAiFronts-mathDeaths == 0))
		 {
//System.out.println("MATH RESET "+mathWounds+" "+numAiFronts);
         int place = 0;      
			for(int k = 0; k < (numAiFronts+numPlayerFronts); k++)
			{
			  if(fronts[k] != null)
			  {
			    if(fronts[k].getAtt() != null)
				 {  
//			      Card tempD = (Card)fronts[k].getDef();
				   fronts[k].setXpos(170*(place));
					fronts[k].redrawAttFront();
				 }
				 else if(fronts[k].getDef() != null)
				 {
//			      Card tempA = (Card)fronts[k].getAtt();
				   fronts[k].setXpos(170*(place));
				   fronts[k].redrawDefFront();
			    }
			      place++;
//System.out.println("place "+place);
			  } 
			}
         numAiFronts -= mathDeaths;

			mathDeaths = 0;
			mathWounds = 3;

	      mathListen = false;    
         mathActive = false; 

 	      choiceManager.add(select);
	  		choiceManager.repaint();	  
         choose.setText("Choose attack card");    
		 }
	  }
	  
	  protected void dealBallWounds()
	  {
		 int defStr = 0;
		 if(defend != null)
		 	 defStr = defend.getValue()+defCastleValue;
       defend.addWounds(1);
		 drawWounds("def");
		 ballWounds--;
		 if(defStr-defend.getWounds() <= 0)
		 {
			defGraveyard();
         ballDeaths++;    
//System.out.println("ballDeath "+ballDeaths);
		 }
//System.out.println("BALL BEFOR RESET "+ballWounds+" "+numAiFronts);
       choose.setText("Select units to wound: "+ ballWounds + " wound(s) left");

		 if(ballWounds == 0 || (numAiFronts-ballDeaths == 0))
		 {
//System.out.println("BALL RESET "+ballWounds+" "+numAiFronts);
         int place = 0;      
			for(int k = 0; k < (numAiFronts+numPlayerFronts); k++)
			{
			  if(fronts[k] != null)
			  {
			    if(fronts[k].getAtt() != null)
				 {  
//			      Card tempD = (Card)fronts[k].getDef();
				   fronts[k].setXpos(170*(place));
					fronts[k].redrawAttFront();
				 }
				 else if(fronts[k].getDef() != null)
				 {
//			      Card tempA = (Card)fronts[k].getAtt();
				   fronts[k].setXpos(170*(place));
				   fronts[k].redrawDefFront();
			    }
			      place++;
//System.out.println("place "+place);
			  } 
			}
         numAiFronts -= ballDeaths;

			ballDeaths = 0;
			ballWounds = 6;

	      ballListen = false;    
         ballActive = false; 

 	      choiceManager.add(select);
	  		choiceManager.repaint();	  
         choose.setText("Choose attack card");    
		 }
	  }

	  protected void addAttacker(Card c)
	  {
	  }

     protected void addDefender(Card c)
	  {
	  }

     protected JLayeredPane getDefPane()
	  {
	    return defPane;
	  }

     protected JLayeredPane getAttPane()
	  {
	    return attPane;
	  }

     protected int getXpos()
	  {
	    return xPos;
	  }
    
     protected void setXpos(int x)
	  {
	    xPos = x;
	  }

     protected void setYpos(int y)
	  {
	    yPos = y;
	  }

     protected int getYpos()
	  {
	    return yPos;
	  }

     protected Card getAtt()
	  {
	    return attack;
	  }

     protected Card getDef()
	  {
	    return defend;
	  }
  }

}//end combat