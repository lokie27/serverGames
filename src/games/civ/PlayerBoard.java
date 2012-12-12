package games.civ;

import javax.swing.*;
import java.awt.image.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;
import java.awt.image.*;
import java.util.Vector;
import games.*;
import java.io.Serializable;

public class PlayerBoard implements Cloneable, MouseListener, Serializable
{

  private static final long serialVersionUID = 3537354627714821891L;

  public GameJLabel playerBrd, trDial, goDial, govCard, hutLabel, villageLabel;
  public GameJLayeredPane playerBrd2, techPane, unitBoard;
  public JTextArea showIron, showIncense, showSilk, showWheat, showSpy,
            showUranium, showCulture, showVillage, showHut;
  
  public GameJPanel turnHolder;
  private Object[] comp;
  private Vector techs;
  private int numVillages, numHuts;
  private Player owner;
  
  public PlayerBoard(){}//bean default
  
  public PlayerBoard(PlayerBoard p, Player name)
  {
	  Font f = new Font("Monospaced", Font.BOLD, 18);
	  techs = p.techs;
	  owner = null;
	  
	  playerBrd = new GameJLabel(p.playerBrd.getIcon());
	  playerBrd2 = new GameJLayeredPane();
  
     trDial = new GameJLabel(p.trDial.getIcon());
     goDial = new GameJLabel(p.goDial.getIcon());

     GameImageIcon ironIcon = getImage("data/Iron.png");
	  GameJLabel ironLabel = new GameJLabel(ironIcon);
     ironLabel.setBounds(15, 25, 25, 25);
     showIron = new JTextArea();
     showIron.setEditable(false);
     showIron.setOpaque(false);
     showIron.setText(Integer.toString(name.getmarketIron()));
	  showIron.setBounds(40, 25, 10, 25);

     GameImageIcon incenseIcon = getImage("data/Incense.png");
	  GameJLabel incenseLabel = new GameJLabel(incenseIcon);
     incenseLabel.setBounds(55, 25, 25, 25);
     showIncense = new JTextArea();
     showIncense.setEditable(false);
     showIncense.setOpaque(false);
     showIncense.setText(Integer.toString(name.getmarketIncense()));
	  showIncense.setBounds(80, 25, 10, 25);

     GameImageIcon silkIcon = getImage("data/Silk.png");
	  GameJLabel silkLabel = new GameJLabel(silkIcon);
     silkLabel.setBounds(15, 57, 25, 25);
     showSilk = new JTextArea();
     showSilk.setEditable(false);
     showSilk.setOpaque(false);
     showSilk.setText(Integer.toString(name.getmarketSilk()));
	  showSilk.setBounds(40, 57, 10, 25);

     GameImageIcon wheatIcon = getImage("data/Wheat.png");
	  GameJLabel wheatLabel = new GameJLabel(wheatIcon);
     wheatLabel.setBounds(55, 57, 25, 25);
     showWheat = new JTextArea();
     showWheat.setEditable(false);
     showWheat.setOpaque(false);
     showWheat.setText(Integer.toString(name.getmarketWheat()));
	  showWheat.setBounds(80, 57, 10, 25);
 
/*
     GameImageIcon spyIcon = getImage("data/Spy.png");
	  GameJLabel spyLabel = new GameJLabel(spyIcon);
     spyLabel.setBounds(15, 89, 25, 25);
     showSpy = new JTextArea();
     showSpy.setEditable(false);
     showSpy.setOpaque(false);
     showSpy.setText(p.showSpy.getText());
	  showSpy.setBounds(40, 89, 10, 22);

     GameImageIcon uraniumIcon = getImage("data/Uranium.png");
	  GameJLabel uraniumLabel = new GameJLabel(uraniumIcon);
     uraniumLabel.setBounds(55, 89, 25, 25);
     showUranium = new JTextArea();
     showUranium.setEditable(false);
     showUranium.setOpaque(false);
     showUranium.setText(p.showUranium.getText());
	  showUranium.setBounds(80, 89, 10, 22);
*/

     GameImageIcon cultureIcon = getImage("data/Culture.png");
	  GameJLabel cultureLabel = new GameJLabel(cultureIcon);
     cultureLabel.setBounds(280, 200, 55, 55);
     showCulture = new JTextArea();
     showCulture.setEditable(false);
     showCulture.setOpaque(false);
     showCulture.setText(p.showCulture.getText());
	  showCulture.setBounds(335, 220, 15, 15);
	  
	  GameImageIcon villageIcon = getImage("data/village.png");
     villageLabel = new GameJLabel(villageIcon);
	  villageLabel.setBounds(15, 89, 25, 25);
     showVillage = new JTextArea();
     showVillage.setEditable(false);
     showVillage.setOpaque(false);
     showVillage.setText(p.showVillage.getText());
	  showVillage.setBounds(40, 89, 10, 22);
	  
	  GameImageIcon hutIcon = getImage("data/hut.png");
     hutLabel = new GameJLabel(hutIcon);
	  hutLabel.setBounds(55, 89, 25, 25);
     showHut = new JTextArea();
     showHut.setEditable(false);
     showHut.setOpaque(false);
     showHut.setText(p.showHut.getText());
	  showHut.setBounds(80, 89, 10, 22);
	  
	  playerBrd2.add(villageLabel, new Integer(2));
     playerBrd2.add(showVillage, new Integer(3));
	  playerBrd2.add(hutLabel, new Integer(2));
     playerBrd2.add(showHut, new Integer(3));
	  playerBrd2.add(ironLabel, new Integer(2));
     playerBrd2.add(showIron, new Integer(3));
     playerBrd2.add(incenseLabel, new Integer(2));
     playerBrd2.add(showIncense, new Integer(3));
     playerBrd2.add(silkLabel, new Integer(2));
     playerBrd2.add(showSilk, new Integer(3));
     playerBrd2.add(wheatLabel, new Integer(2));
     playerBrd2.add(showWheat, new Integer(3));
//     playerBrd2.add(spyLabel, new Integer(2));
//     playerBrd2.add(showSpy, new Integer(3));
//     playerBrd2.add(uraniumLabel, new Integer(2));
//     playerBrd2.add(showUranium, new Integer(3));
     playerBrd2.add(cultureLabel, new Integer(2));
     playerBrd2.add(showCulture, new Integer(3));

     techPane = new GameJLayeredPane();
     techPane.setOpaque(false);	  	 
     techPane.setBackground(new Color(255, 255, 255));
     
	  comp = techs.toArray();
     int size = techs.size();     
     GameJLabel temp = new GameJLabel();
	  for(int k = 0; k < size; k++)
	  {
	    Tech t = (Tech)comp[k];
		 temp = (GameJLabel)t.getLabel();  
System.out.println("Component count "+temp.getIcon());
		 GameJLabel l = new GameJLabel(temp.getIcon());
		 l.setBounds(temp.getBounds());
		 techPane.add(l, new Integer(1));
	    l.addMouseListener(this);
	  }
     GameJLabel moveLbl = new GameJLabel("Move Limit "+name.getMoveLimit());  
	  moveLbl.setBounds(50, 195, 170, 20);
     moveLbl.setFont(f);
     moveLbl.setForeground(new Color(255, 255, 255));
	  
     GameJLabel stackLbl = new GameJLabel("Stack Limit "+name.getStacklimit());  
	  stackLbl.setBounds(50, 225, 170, 20);
     stackLbl.setFont(f);
     stackLbl.setForeground(new Color(255, 255, 255));

     techPane.add(moveLbl, new Integer(2));
     techPane.add(stackLbl, new Integer(2));
	  
	  techPane.setBounds(410, 10, 380, 250);

	  playerBrd.setBounds(00, 05, 400, 268);
     trDial.setBounds(p.trDial.getBounds());//230, 23, 150, 150);
     goDial.setBounds(p.goDial.getBounds());

     turnHolder = new GameJPanel();
     turnHolder.setBackground(new Color(0,0,0));
	  turnHolder.setBounds(00, 00, 205, 205);

	  govCard = new GameJLabel(p.govCard.getIcon());
     govCard.setBounds(10, 130, 90, 130); 
     
     unitBoard = new GameJLayeredPane();
     unitBoard.setBounds(0, 300, 80, 80);  
	  GameJLabel lbl = new GameJLabel(getImage("data/unitBack.png"));
	  lbl.setBounds(0, 0, 80, 80);

     JTextArea ta = new JTextArea();
     ta.setEditable(false);
	  ta.setBounds(0, 0, 30, 27);
     ta.setForeground(new Color(250, 250, 250));
     ta.setBackground(new Color(0, 0, 0));
	  ta.setBorder(new MatteBorder(2, 3, 2, 3, getImage("data/goldBorder.png")));
     ta.setFont(f);
     if(name.getUnitHand().size() < 10)
       ta.setText("0"+Integer.toString(name.getUnitHand().size()));
     else
       ta.setText(Integer.toString(name.getUnitHand().size()));
	  
	  unitBoard.add(lbl, new Integer(0));  
	  unitBoard.add(ta, new Integer(1));

     JTextArea unitText = new JTextArea();
     unitText.setEditable(false);
	  unitText.setBounds(10, 270, 60, 30);
     unitText.setForeground(new Color(250, 250, 250));
     unitText.setBackground(new Color(0, 0, 0));
	  unitText.setBorder(new MatteBorder(2, 3, 2, 3, getImage("data/goldBorder.png")));
     unitText.setFont(f);
     unitText.setText("Units");

     playerBrd2.add(unitBoard, new Integer(0));
     playerBrd2.add(unitText, new Integer(0));
     playerBrd2.add(playerBrd, new Integer(0));
     playerBrd2.add(techPane, new Integer(1));
     playerBrd2.add(govCard, new Integer(2));
     playerBrd2.add(trDial, new Integer(3));
     playerBrd2.add(goDial, new Integer(4));

	  playerBrd2.setPreferredSize(new Dimension(800, 485));
     playerBrd2.setBackground(new Color(0,0,0));
     playerBrd2.setBounds(00, 00, 800, 490);
  }
  
  public PlayerBoard(Player name)
  {
	  owner = name;
	  numVillages = 0;
	  numHuts = 0;
	  techs = new Vector(0, 1);
	  
	  GameImageIcon playerBoardIcon = getImage("data/card"+name.getNation()+".png");
	  playerBrd = new GameJLabel(playerBoardIcon);
	  playerBrd2 = new GameJLayeredPane();
  
  	  GameRotatedIcon trIcon = new GameRotatedIcon(getImage("data/tradeDial.png"), 348);
     GameRotatedIcon goIcon = new GameRotatedIcon(getImage("data/goldDial.png"), 323);
  
     trDial = new GameJLabel(trIcon);
     goDial = new GameJLabel(goIcon);

     GameImageIcon ironIcon = getImage("data/Iron.png");
	  GameJLabel ironLabel = new GameJLabel(ironIcon);
     ironLabel.setBounds(15, 25, 25, 25);
     showIron = new JTextArea();
     showIron.setEditable(false);
     showIron.setOpaque(false);
     showIron.setText("0");
	  showIron.setBounds(40, 25, 10, 25);

     GameImageIcon incenseIcon = getImage("data/Incense.png");
	  GameJLabel incenseLabel = new GameJLabel(incenseIcon);
     incenseLabel.setBounds(55, 25, 25, 25);
     showIncense = new JTextArea();
     showIncense.setEditable(false);
     showIncense.setOpaque(false);
     showIncense.setText("0");
	  showIncense.setBounds(80, 25, 10, 25);

     GameImageIcon silkIcon = getImage("data/Silk.png");
	  GameJLabel silkLabel = new GameJLabel(silkIcon);
     silkLabel.setBounds(15, 57, 25, 25);
     showSilk = new JTextArea();
     showSilk.setEditable(false);
     showSilk.setOpaque(false);
     showSilk.setText("0");
	  showSilk.setBounds(40, 57, 10, 25);

     GameImageIcon wheatIcon = getImage("data/Wheat.png");
	  GameJLabel wheatLabel = new GameJLabel(wheatIcon);
     wheatLabel.setBounds(55, 57, 25, 25);
     showWheat = new JTextArea();
     showWheat.setEditable(false);
     showWheat.setOpaque(false);
     showWheat.setText("0");
	  showWheat.setBounds(80, 57, 10, 25);
 
     GameImageIcon spyIcon = getImage("data/Spy.png");
	  GameJLabel spyLabel = new GameJLabel(spyIcon);
     spyLabel.setBounds(15, 89, 25, 25);
     showSpy = new JTextArea();
     showSpy.setEditable(false);
     showSpy.setOpaque(false);
     showSpy.setText("0");
	  showSpy.setBounds(40, 89, 10, 22);

     GameImageIcon uraniumIcon = getImage("data/Uranium.png");
	  GameJLabel uraniumLabel = new GameJLabel(uraniumIcon);
     uraniumLabel.setBounds(55, 89, 25, 25);
     showUranium = new JTextArea();
     showUranium.setEditable(false);
     showUranium.setOpaque(false);
     showUranium.setText("0");
	  showUranium.setBounds(80, 89, 10, 22);

     GameImageIcon cultureIcon = getImage("data/Culture.png");
	  GameJLabel cultureLabel = new GameJLabel(cultureIcon);
     cultureLabel.setBounds(280, 200, 55, 55);
     showCulture = new JTextArea();
     showCulture.setEditable(false);
     showCulture.setOpaque(false);
     showCulture.setText("0");
	  showCulture.setBounds(335, 220, 15, 15);
	  
	  GameImageIcon villageIcon = getImage("data/village.png");
     villageLabel = new GameJLabel(villageIcon);
	  villageLabel.setBounds(350, 190, 25, 25);
     showVillage = new JTextArea();
     showVillage.setEditable(false);
     showVillage.setOpaque(false);
     showVillage.setText("0");
	  showVillage.setBounds(378, 197, 15, 15);
     villageLabel.addMouseListener(this);
	  
	  GameImageIcon hutIcon = getImage("data/hut.png");
     hutLabel = new GameJLabel(hutIcon);
	  hutLabel.setBounds(350, 225, 25, 25);
     showHut = new JTextArea();
     showHut.setEditable(false);
     showHut.setOpaque(false);
     showHut.setText("0");
	  showHut.setBounds(378, 232, 15, 15);
     hutLabel.addMouseListener(this);
	  
	  playerBrd2.add(villageLabel, new Integer(2));
     playerBrd2.add(showVillage, new Integer(3));
	  playerBrd2.add(hutLabel, new Integer(2));
     playerBrd2.add(showHut, new Integer(3));
	  playerBrd2.add(ironLabel, new Integer(2));
     playerBrd2.add(showIron, new Integer(3));
     playerBrd2.add(incenseLabel, new Integer(2));
     playerBrd2.add(showIncense, new Integer(3));
     playerBrd2.add(silkLabel, new Integer(2));
     playerBrd2.add(showSilk, new Integer(3));
     playerBrd2.add(wheatLabel, new Integer(2));
     playerBrd2.add(showWheat, new Integer(3));
     playerBrd2.add(spyLabel, new Integer(2));
     playerBrd2.add(showSpy, new Integer(3));
     playerBrd2.add(uraniumLabel, new Integer(2));
     playerBrd2.add(showUranium, new Integer(3));
     playerBrd2.add(cultureLabel, new Integer(2));
     playerBrd2.add(showCulture, new Integer(3));

     techPane = new GameJLayeredPane();
     techPane.setPreferredSize(new Dimension(340, 200));
     techPane.setBounds(475, 270, 340, 200);	  	 
     techPane.setOpaque(true);	  	 
     techPane.setBackground(new Color(255, 255, 255));

	  playerBrd.setBounds(00, 05, 400, 268);
     trDial.setBounds(230, 23, trIcon.getIconWidth(), trIcon.getIconHeight());
	  goDial.setBounds(268, 60, goIcon.getIconWidth(), goIcon.getIconHeight());

     turnHolder = new GameJPanel();
     turnHolder.setBackground(new Color(0,0,0));
	  turnHolder.setBounds(00, 00, 205, 205);

	  govCard = new GameJLabel();
     govCard.setBounds(10, 130, 90, 130); 

//     playerBrd2.add(turnHolder, new Integer(0));
     playerBrd2.add(playerBrd, new Integer(0));
     playerBrd2.add(govCard, new Integer(1));
     playerBrd2.add(trDial, new Integer(2));
     playerBrd2.add(goDial, new Integer(3));

	  playerBrd2.setPreferredSize(new Dimension(400, 268));
     playerBrd2.setBackground(new Color(0,0,0));
     playerBrd2.setBounds(210, 00, 500, 400);
  }

  protected Object clone()
  {
    try
    {
      PlayerBoard pb = (PlayerBoard)super.clone();   
		return pb;
    }
    catch(CloneNotSupportedException e)
    {  
      return null;
    }
  } 

	public GameImageIcon getImage(String pathName){
     GameImageIcon image;
     java.net.URL url = getClass().getResource(pathName); 
     try{
		  if(url != null){
		    image = new GameImageIcon(url.toURI());
			 return image;    
	     }
	     else 
	       System.out.println("NULL IMAGE IN CivLAUNCH");       
     }catch(java.io.IOException IOEx){
        System.out.println("IOEx NULL IMAGE IN CivLAUNCH");       
        IOEx.printStackTrace();	     
	  }catch(java.net.URISyntaxException URIEx){
        System.out.println("URIEx NULL IMAGE IN CivLAUNCH");       
        URIEx.printStackTrace();	     
	  }
	  return null;
   }

  public GameJLayeredPane getPlayerboard()
  {
    return this.playerBrd2;
  }

  public GameJPanel getPlayerturnholder()
  {
    return this.turnHolder;
  }

  public GameJLayeredPane getPlayertechpane()
  {
    return this.techPane;
  }

  public PlayerBoard getClone()
  {
    return (PlayerBoard)this.clone();
  }

  public void addTech(Tech t)
  {
    techs.add(t);
  }
  	
  public void setVillage(int num)
  {
    numVillages += num;
	 showVillage.setText(Integer.toString(numVillages));  
    playerBrd2.repaint(); 
  }

  public void setHut(int num)
  {
    numHuts += num;
	 showHut.setText(Integer.toString(numHuts));  
    playerBrd2.repaint(); 
  }

	public void blowUpIcon(GameImageIcon l)
	{
	  GameJLabel lbl = new GameJLabel(l);
	  lbl.setBounds(450, 200, 220, 150);
	  
	  playerBrd2.add(lbl, new Integer(20));  
	  playerBrd2.repaint();
	}

   public void blowDownIcon(Player p)
   {
	  playerBrd2.remove(0);  
	  playerBrd2.repaint();
	}

/*
	public void blowUpIcon(GameImageIcon l, int gold, Player p)
	{
	  GameJLabel lbl = new GameJLabel(l);
	  lbl.setBounds(600, 700, 220, 150);
	  
	  p.playerUI.mainBoard.add(lbl, new Integer(20));  
     if(gold > 0)
	  {
	    for(int k = 0; k < gold; k++)
		 {
		   GameJLabel go = new GameJLabel(getStaticImage("data/goldIcon.png"));
			go.setBounds(640+(25*k), 812, 15, 15);
			p.playerUI.mainBoard.add(go, new Integer(21));
//	      go.MoveToFront();
		 }
	  }
	  p.playerUI.mainBoard.repaint();
	}
	
   public void blowDownIcon(int gold, Player p)
   {
	  if(gold > 0)
	  {
	    for(int k = 0; k < gold; k++)
		   p.playerUI.mainBoard.remove(0);  
	  }
	  p.playerUI.mainBoard.remove(0);  
	  p.playerUI.mainBoard.repaint();
	}
*/

  public void mouseEntered(MouseEvent e)
  {
    for(int k = 0; k < techs.size(); k++)
	 {
	   if(owner == null && techs.get(k) != null)
		{
        Tech temp = (Tech)techs.get(k);     
//		  GameJLabel temp = (GameJLabel)techs.getLabel();
        GameImageIcon ic = (GameImageIcon)temp.icon;
System.out.println("OTHER TECH"+ic.getImage());		
		  blowUpIcon(ic);   
//        blowUpIcon(techListener[k].icon, techListener[k].gold, this);
		}
	 }
  }
  public void mouseClicked(MouseEvent e)
  {
    if(e.getSource() == hutLabel && numHuts > 0)
	 {
System.out.println("HUT CLICK");		
	   JDialog d = new JDialog(new JFrame(), "Hut loot", true);
      d.getContentPane().setBackground(new Color(0, 0, 0));    
		d.setResizable(false);
      
		GameJLayeredPane hutPane = new GameJLayeredPane();
  
	 	int iron = owner.getIron() - owner.getmarketIron();   
	 	int incense = owner.getIncense() - owner.getmarketIncense();   
	 	int silk = owner.getSilk() - owner.getmarketSilk();   
	 	int wheat = owner.getWheat() - owner.getmarketWheat();   
      int spy = owner.getSpy();
		int uranium = owner.getUranium();
      int total = 0;
		
		for(int k = 0; k < iron; k++)
		{
        GameJLabel lbl = new GameJLabel(getImage("data/Iron.png"));
        if(total < 6)
		  {  
		    lbl.setBounds(10+(25*total), 0, 25, 25);
        }
		  else if(total < 12)
		  {
		    lbl.setBounds(10+(25*(total-6)), 30, 25, 25);
		  }    
		  else
		    lbl.setBounds(10+(25*(total-12)), 60, 25, 25);
		  hutPane.add(lbl, new Integer(0));
		  total++;
		}
		for(int k = 0; k < incense; k++)
		{
        GameJLabel lbl = new GameJLabel(getImage("data/Incense.png"));
        if(total < 6)
		  {  
		    lbl.setBounds(10+(25*total), 0, 25, 25);
        }
		  else if(total < 12)
		  {
		    lbl.setBounds(10+(25*(total-6)), 30, 25, 25);
		  }    
		  else
		    lbl.setBounds(10+(25*(total-12)), 60, 25, 25);
		  hutPane.add(lbl, new Integer(0));
		  total++;
		}
		for(int k = 0; k < silk; k++)
		{
        GameJLabel lbl = new GameJLabel(getImage("data/Silk.png"));
        if(total < 6)
		  {  
		    lbl.setBounds(10+(25*total), 0, 25, 25);
        }
		  else if(total < 12)
		  {
		    lbl.setBounds(10+(25*(total-6)), 30, 25, 25);
		  }    
		  else
		    lbl.setBounds(10+(25*(total-12)), 60, 25, 25);
		  hutPane.add(lbl, new Integer(0));
		  total++;
		}
		for(int k = 0; k < wheat; k++)
		{
        GameJLabel lbl = new GameJLabel(getImage("data/Wheat.png"));
        if(total < 6)
		  {  
		    lbl.setBounds(10+(25*total), 0, 25, 25);
        }
		  else if(total < 12)
		  {
		    lbl.setBounds(10+(25*(total-6)), 30, 25, 25);
		  }    
		  else
		    lbl.setBounds(10+(25*(total-12)), 60, 25, 25);
		  hutPane.add(lbl, new Integer(0));
		  total++;
		}
		for(int k = 0; k < spy; k++)
		{
        GameJLabel lbl = new GameJLabel(getImage("data/Spy.png"));
        if(total < 6)
		  {  
		    lbl.setBounds(10+(25*total), 0, 25, 25);
        }
		  else if(total < 12)
		  {
		    lbl.setBounds(10+(25*(total-6)), 30, 25, 25);
		  }    
		  else
		    lbl.setBounds(10+(25*(total-12)), 60, 25, 25);
		  hutPane.add(lbl, new Integer(0));
		  total++;
		}
		for(int k = 0; k < uranium; k++)
		{
        GameJLabel lbl = new GameJLabel(getImage("data/Uranium.png"));
        if(total <= 6)
		  {  
		    lbl.setBounds(10+(25*total), 0, 25, 25);
        }
		  else if(total <= 12)
		  {
		    lbl.setBounds(10+(25*(total-6)), 30, 25, 25);
		  }    
		  else
		    lbl.setBounds(10+(25*(total-12)), 60, 25, 25);
		  
		  hutPane.add(lbl, new Integer(0));
		  total++;
		}
		
		if(total <= 6)
		  d.setSize(new Dimension(total*30, 60));
		else if(total <= 12)
		  d.setSize(new Dimension(175, 90));
      else
		  d.setSize(new Dimension(175, 110));
		d.add(hutPane);
      d.setLocationRelativeTo(hutLabel);
      d.setVisible(true);
	 
	 }
	 if(e.getSource() == villageLabel && numVillages > 0)
	 {
System.out.println("VILLAGE CLICK");		
	   JDialog d = new JDialog(new JFrame(), "Village loot", true);
      d.getContentPane().setBackground(new Color(0, 0, 0));    
		d.setResizable(false);
      
		GameJLayeredPane villagePane = new GameJLayeredPane();
  
	 	int iron = owner.getIron() - owner.getmarketIron();   
	 	int incense = owner.getIncense() - owner.getmarketIncense();   
	 	int silk = owner.getSilk() - owner.getmarketSilk();   
	 	int wheat = owner.getWheat() - owner.getmarketWheat();   
      int spy = owner.getSpy();
		int uranium = owner.getUranium();
      int total = 0;
		
		for(int k = 0; k < iron; k++)
		{
        GameJLabel lbl = new GameJLabel(getImage("data/Iron.png"));
        if(total < 6)
		  {  
		    lbl.setBounds(10+(25*total), 0, 25, 25);
        }
		  else if(total < 12)
		  {
		    lbl.setBounds(10+(25*(total-6)), 30, 25, 25);
		  }    
		  else
		    lbl.setBounds(10+(25*(total-12)), 60, 25, 25);
		  villagePane.add(lbl, new Integer(0));
		  total++;
		}
		for(int k = 0; k < incense; k++)
		{
        GameJLabel lbl = new GameJLabel(getImage("data/Incense.png"));
        if(total < 6)
		  {  
		    lbl.setBounds(10+(25*total), 0, 25, 25);
        }
		  else if(total < 12)
		  {
		    lbl.setBounds(10+(25*(total-6)), 30, 25, 25);
		  }    
		  else
		    lbl.setBounds(10+(25*(total-12)), 60, 25, 25);
		  villagePane.add(lbl, new Integer(0));
		  total++;
		}
		for(int k = 0; k < silk; k++)
		{
        GameJLabel lbl = new GameJLabel(getImage("data/Silk.png"));
        if(total < 6)
		  {  
		    lbl.setBounds(10+(25*total), 0, 25, 25);
        }
		  else if(total < 12)
		  {
		    lbl.setBounds(10+(25*(total-6)), 30, 25, 25);
		  }    
		  else
		    lbl.setBounds(10+(25*(total-12)), 60, 25, 25);
		  villagePane.add(lbl, new Integer(0));
		  total++;
		}
		for(int k = 0; k < wheat; k++)
		{
        GameJLabel lbl = new GameJLabel(getImage("data/Wheat.png"));
        if(total < 6)
		  {  
		    lbl.setBounds(10+(25*total), 0, 25, 25);
        }
		  else if(total < 12)
		  {
		    lbl.setBounds(10+(25*(total-6)), 30, 25, 25);
		  }    
		  else
		    lbl.setBounds(10+(25*(total-12)), 60, 25, 25);
		  villagePane.add(lbl, new Integer(0));
		  total++;
		}
		for(int k = 0; k < spy; k++)
		{
        GameJLabel lbl = new GameJLabel(getImage("data/Spy.png"));
        if(total < 6)
		  {  
		    lbl.setBounds(10+(25*total), 0, 25, 25);
        }
		  else if(total < 12)
		  {
		    lbl.setBounds(10+(25*(total-6)), 30, 25, 25);
		  }    
		  else
		    lbl.setBounds(10+(25*(total-12)), 60, 25, 25);
		  villagePane.add(lbl, new Integer(0));
		  total++;
		}
		for(int k = 0; k < uranium; k++)
		{
        GameJLabel lbl = new GameJLabel(getImage("data/Uranium.png"));
        if(total <= 6)
		  {  
		    lbl.setBounds(10+(25*total), 0, 25, 25);
        }
		  else if(total <= 12)
		  {
		    lbl.setBounds(10+(25*(total-6)), 30, 25, 25);
		  }    
		  else
		    lbl.setBounds(10+(25*(total-12)), 60, 25, 25);
		  
		  villagePane.add(lbl, new Integer(0));
		  total++;
		}
		
		if(total <= 6)
		  d.setSize(new Dimension(total*30, 60));
		else if(total <= 12)
		  d.setSize(new Dimension(175, 90));
      else
		  d.setSize(new Dimension(175, 110));
		d.add(villagePane);
      d.setLocationRelativeTo(villageLabel);
      d.setVisible(true);
	 
	 }
  }//end mouseEntered
  public void mousePressed(MouseEvent e){}//end mousePressed
  public void mouseReleased(MouseEvent e){}//end mouseReleased
  public void mouseExited(MouseEvent e)
  {
    for(int k = 0; k < techPane.getComponentCountInLayer(1); k++)
	 {
	   if(owner == null && comp[k] != null)
		{
System.out.println("OTHER TECH EXIT");		
		  playerBrd2.remove(0);
		  playerBrd2.repaint();
		}
	 }
  }

	public GameJLabel getPlayerBrd() {
		return playerBrd;
	}
	public void setPlayerBrd(GameJLabel playerBrd) {
		this.playerBrd = playerBrd;
	}
	public GameJLabel getTrDial() {
		return trDial;
	}
	public void setTrDial(GameJLabel trDial) {
		this.trDial = trDial;
	}
	public GameJLabel getGoDial() {
		return goDial;
	}
	public void setGoDial(GameJLabel goDial) {
		this.goDial = goDial;
	}
	public GameJLabel getGovCard() {
		return govCard;
	}
	public void setGovCard(GameJLabel govCard) {
		this.govCard = govCard;
	}
	public GameJLabel getHutLabel() {
		return hutLabel;
	}
	public void setHutLabel(GameJLabel hutLabel) {
		this.hutLabel = hutLabel;
	}
	public GameJLabel getVillageLabel() {
		return villageLabel;
	}
	public void setVillageLabel(GameJLabel villageLabel) {
		this.villageLabel = villageLabel;
	}
	public GameJLayeredPane getPlayerBrd2() {
		return playerBrd2;
	}
	public void setPlayerBrd2(GameJLayeredPane playerBrd2) {
		this.playerBrd2 = playerBrd2;
	}
	public GameJLayeredPane getTechPane() {
		return techPane;
	}
	public void setTechPane(GameJLayeredPane techPane) {
		this.techPane = techPane;
	}
	public GameJLayeredPane getUnitBoard() {
		return unitBoard;
	}
	public void setUnitBoard(GameJLayeredPane unitBoard) {
		this.unitBoard = unitBoard;
	}
	public JTextArea getShowIron() {
		return showIron;
	}
	public void setShowIron(JTextArea showIron) {
		this.showIron = showIron;
	}
	public JTextArea getShowIncense() {
		return showIncense;
	}
	public void setShowIncense(JTextArea showIncense) {
		this.showIncense = showIncense;
	}
	public JTextArea getShowSilk() {
		return showSilk;
	}
	public void setShowSilk(JTextArea showSilk) {
		this.showSilk = showSilk;
	}
	public JTextArea getShowWheat() {
		return showWheat;
	}
	public void setShowWheat(JTextArea showWheat) {
		this.showWheat = showWheat;
	}
	public JTextArea getShowSpy() {
		return showSpy;
	}
	public void setShowSpy(JTextArea showSpy) {
		this.showSpy = showSpy;
	}
	public JTextArea getShowUranium() {
		return showUranium;
	}
	public void setShowUranium(JTextArea showUranium) {
		this.showUranium = showUranium;
	}
	public JTextArea getShowCulture() {
		return showCulture;
	}
	public void setShowCulture(JTextArea showCulture) {
		this.showCulture = showCulture;
	}
	public JTextArea getShowVillage() {
		return showVillage;
	}
	public void setShowVillage(JTextArea showVillage) {
		this.showVillage = showVillage;
	}
	public JTextArea getShowHut() {
		return showHut;
	}
	public void setShowHut(JTextArea showHut) {
		this.showHut = showHut;
	}
	public GameJPanel getTurnHolder() {
		return turnHolder;
	}
	public void setTurnHolder(GameJPanel turnHolder) {
		this.turnHolder = turnHolder;
	}
	public Object[] getComp() {
		return comp;
	}
	public void setComp(Object[] comp) {
		this.comp = comp;
	}
	public Vector getTechs() {
		return techs;
	}
	public void setTechs(Vector techs) {
		this.techs = techs;
	}
	public int getNumVillages() {
		return numVillages;
	}
	public void setNumVillages(int numVillages) {
		this.numVillages = numVillages;
	}
	public int getNumHuts() {
		return numHuts;
	}
	public void setNumHuts(int numHuts) {
		this.numHuts = numHuts;
	}
	public Player getOwner() {
		return owner;
	}
	public void setOwner(Player owner) {
		this.owner = owner;
	}

	private void readObject(java.io.ObjectInputStream stream) throws java.io.IOException, ClassNotFoundException{
		try{
			  playerBrd = (GameJLabel)stream.readObject();
			  trDial = (GameJLabel)stream.readObject();
			  goDial = (GameJLabel)stream.readObject();
			  govCard = (GameJLabel)stream.readObject();
			  hutLabel = (GameJLabel)stream.readObject();
			  villageLabel = (GameJLabel)stream.readObject();
			  playerBrd2 = (GameJLayeredPane)stream.readObject();
			  techPane = (GameJLayeredPane)stream.readObject();
			  unitBoard = (GameJLayeredPane)stream.readObject();
			  showIron = (JTextArea)stream.readObject();
			  showIncense = (JTextArea)stream.readObject();
			  showSilk = (JTextArea)stream.readObject();
			  showWheat = (JTextArea)stream.readObject();
			  showSpy = (JTextArea)stream.readObject();
			  showUranium = (JTextArea)stream.readObject();
			  showCulture = (JTextArea)stream.readObject();
			  showVillage = (JTextArea)stream.readObject();
			  showHut = (JTextArea)stream.readObject();
			  turnHolder = (GameJPanel)stream.readObject();
			  comp = (Object[])stream.readObject();
			  techs = (Vector)stream.readObject();
			  numVillages = stream.readInt();
			  numHuts = stream.readInt();
			  owner = (Player)stream.readObject();
      }catch(java.io.IOException ioEx){
			System.out.println("IO EXCEPTION readObject\n");
			ioEx.printStackTrace();
		}
   }
		
   private void writeObject(java.io.ObjectOutputStream stream) throws java.io.IOException{
		try{
			  stream.writeObject(playerBrd);
			  stream.writeObject(trDial);
			  stream.writeObject(goDial);
			  stream.writeObject(govCard);
			  stream.writeObject(hutLabel);
			  stream.writeObject(villageLabel);
			  stream.writeObject(playerBrd2);
			  stream.writeObject(techPane);
			  stream.writeObject(unitBoard);
			  stream.writeObject(showIron);
			  stream.writeObject(showIncense);
			  stream.writeObject(showSilk);
			  stream.writeObject(showWheat);
			  stream.writeObject(showSpy);
			  stream.writeObject(showUranium);
			  stream.writeObject(showCulture);
			  stream.writeObject(showVillage);
			  stream.writeObject(showHut);
			  stream.writeObject(turnHolder);
			  stream.writeObject(comp);
			  stream.writeObject(techs);
			  stream.writeInt(numVillages);
			  stream.writeInt(numHuts);
			  stream.writeObject(owner);
		}
		catch(java.net.ConnectException conEx){
			System.out.println("Connection Refused!\n");
			conEx.printStackTrace();
			System.exit(1);
		}
		catch(java.io.NotSerializableException NSEx){
			System.out.println("NOT SERIALIZABLE\n");
			NSEx.printStackTrace();
		}
		catch(java.io.IOException ioEx){
			System.out.println("IO EXCEPTION sendGameCommand\n");
			ioEx.printStackTrace();
		}
	}
		 
   private void readObjectNoData() throws java.io.ObjectStreamException{}

}