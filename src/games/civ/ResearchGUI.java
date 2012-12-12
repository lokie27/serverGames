package games.civ;

import java.io.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.Vector;
import java.beans.*;
import javax.swing.border.*;

public class ResearchGUI extends JComponent implements ActionListener
{
	JInternalFrame internal;
   JScrollPane techScrollPane;
   JLayeredPane holder;  
	JButton lvl1, lvl2, lvl3, lvl4, lvl5, done;
   JButton[] techButtons; 
   JLabel none; 
	Player name;
   LogicBoard lb;
	static boolean playerDone; 

	public ResearchGUI(JComponent frame, Player pName, LogicBoard lbSet)
   {
	 playerDone = false;
    name = pName;
    if(!true)//name.getNation().equals(CivGUI.aiChoice))
	 {
//	   System.out.println("AI RESEARCHGUI");
//		playerWait();
//		if(!name.getFirstTurn())
//      { 
//		  name.setFirstTurn(true);
//		}
//		else
//		{
//		  name.setFirstTurn(false);
//        playerDone = true;   
//	   }
//		CivGUI.turn.startTurn(name);
	 }
	 else
	 {
		lb = lbSet;
		    
		internal = new JInternalFrame("Research turn");
	   internal.setPreferredSize(new Dimension(198, 198));

		if(name.getColor().equals("Red"))
		  internal.setBackground(new Color(255, 0, 0));    
      else if(name.getColor().equals("Yellow"))
		  internal.setBackground(new Color(255, 255, 0));    
      else if(name.getColor().equals("Blue"))
		  internal.setBackground(new Color(0, 0, 255));    
      else if(name.getColor().equals("Green"))
		  internal.setBackground(new Color(0, 255, 0)); 
      
      drawTechs();  
		internal.add(holder);
      internal.show();
    
	   internal.setBorder(new BevelBorder(1, new Color(0, 0, 0), new Color(255, 255, 255)));
	   frame.removeAll();  
		frame.add(internal);

		try
	   {internal.setMaximum(true);}
	   catch(PropertyVetoException ex)
	   {}
	   frame.validate();
	}
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
      System.out.println("NULL IMAGE IN ResearchGUI");       
	 return null;
  }

  private void drawTechs()
  {
    holder = new JLayeredPane();
    holder.setBounds(00, 00, 198, 198);	 

    if(name.getTrade() >= 6 || (name.getWonderCards(4) && name.getTrade() >= 1))  
	 {
	   lvl1 = new JButton(getImage("data/level1_"+name.getColor()+".png"));
	   lvl1.setBounds(15, 5, lvl1.getIcon().getIconWidth(), lvl1.getIcon().getIconHeight());
      lvl1.addActionListener(this);
		holder.add(lvl1, new Integer(1));
    }
    if((name.getTrade() >= 11 || (name.getWonderCards(4) && name.getTrade() >= 6)) && ((name.getTechLvl1()-2) >= name.getTechLvl2()))  
	 {
	   lvl2 = new JButton(getImage("data/level2_"+name.getColor()+".png"));
	   lvl2.setBounds(100, 5, lvl2.getIcon().getIconWidth(), lvl2.getIcon().getIconHeight());
      lvl2.addActionListener(this);
		holder.add(lvl2, new Integer(1));
    }
    if((name.getTrade() >= 16 || (name.getWonderCards(4) && name.getTrade() >= 11)) && ((name.getTechLvl2()-2) >= name.getTechLvl3()))  
	 {
	   lvl3 = new JButton(getImage("data/level3_"+name.getColor()+".png"));
	   lvl3.setBounds(15, 70, lvl3.getIcon().getIconWidth(), lvl3.getIcon().getIconHeight());
      lvl3.addActionListener(this);
		holder.add(lvl3, new Integer(1));
    }
    if((name.getTrade() >= 21 || (name.getWonderCards(4) && name.getTrade() >= 15)) && ((name.getTechLvl3()-2) >= name.getTechLvl4()))  
	 {
	   lvl4 = new JButton(getImage("data/level4_"+name.getColor()+".png"));
	   lvl4.setBounds(100, 70, lvl4.getIcon().getIconWidth(), lvl4.getIcon().getIconHeight());
      lvl4.addActionListener(this);
		holder.add(lvl4, new Integer(1));
    }
    if((name.getTrade() >= 26 || (name.getWonderCards(4) && name.getTrade() >= 21)) && (name.getTechLvl4() >= 2))  
	 {
	   lvl5 = new JButton(getImage("data/level5_"+name.getColor()+".png"));
	   lvl5.setBounds(50, 120, lvl5.getIcon().getIconWidth(), lvl5.getIcon().getIconHeight());
      lvl5.addActionListener(this);
		holder.add(lvl5, new Integer(1));
    }
    if(name.getTrade() < 6)
    {
	   none = new JLabel("Cannot research");
      none.setBounds(45, 80, 100, 20);
		holder.add(none, new Integer(0));
	 }
	 
    done = new JButton("END TURN");
	 done.setBounds(5, 145, 180, 20);
    done.addActionListener(this);
	 holder.add(done, new Integer(0));
    techButtons = new JButton[36];
  }
  
  synchronized void playerWait()
  {
    System.out.println(name.getNation()+"is REsearch HERE!");
	 while(!playerDone)
		{
        System.out.println(name.getNation()+"is RESEARCH WAITING!");
		  try{wait(1000);}
		  catch(InterruptedException ex){System.out.println("EXCEPTION");}
		}
    notify();
  }

  private void setupLvl1()
  {
      GridLayout grid = new GridLayout(10, 1, 0, 9);
   	JPanel techPanel = new JPanel(grid);
	   techScrollPane = new JScrollPane(techPanel, 22, 31);
      Vector check = new Vector(0, 1);
	   
		ImageIcon i0 = getImage("data/animalHusbandry.png");
	   check.add(i0);  
		ImageIcon i1 = getImage("data/codeOfLaws.png");
	   check.add(i1);  
	   ImageIcon i2 = getImage("data/currency.png");
	   check.add(i2);  
	   ImageIcon i3 = getImage("data/horsebackRiding.png");
	   check.add(i3);  
	   ImageIcon i4 = getImage("data/masonry.png");
	   check.add(i4);  
	   ImageIcon i5 = getImage("data/metalWorking.png");
	   check.add(i5);  
	   ImageIcon i6 = getImage("data/navigation.png");
	   check.add(i6);  
	   ImageIcon i7 = getImage("data/philosophy.png");
	   check.add(i7);  
	   ImageIcon i8 = getImage("data/pottery.png");
	   check.add(i8);  
	   ImageIcon i9 = getImage("data/writing.png");
	   check.add(i9);  
     
		Vector v = name.getTechVector();
	   for(int k = 0; k < 10; k++)
		{
		  if(v.get(k) == null)//.getClass().getName().equals("java.lang.Object"))
		  {
//System.out.println("RES !!: "+ k);		    
			 ImageIcon temp = (ImageIcon)check.get(k);
          JButton l = new JButton(temp);  
          l.setPreferredSize(new Dimension(225, 150));//, temp.getIconWidth(), temp.getIconHeight());
          l.setBackground(new Color(0, 0, 0));
          techButtons[k] = l;
			 techButtons[k].addActionListener(this);
			 techPanel.add(techButtons[k]);
		  }
		}

	   techScrollPane.setBounds(00, 420, 240, 270);    
      techPanel.setBackground(name.getPlayerColor());
      techScrollPane.validate();
		name.playerUI.mainBoard.add(techScrollPane, new Integer(19)); 
      name.playerUI.mainBoard.repaint();
  }
  
  private void setupLvl2()
  {
      GridLayout grid = new GridLayout(10, 1, 0, 9);
   	JPanel techPanel = new JPanel(grid);
	   techScrollPane = new JScrollPane(techPanel, 22, 31);
      Vector check = new Vector(0, 1);
	   
		ImageIcon i0 = getImage("data/chivalry.png");
	   check.add(i0);  
		ImageIcon i1 = getImage("data/civilService.png");
	   check.add(i1);  
	   ImageIcon i2 = getImage("data/construction.png");
	   check.add(i2);  
	   ImageIcon i3 = getImage("data/democracy.png");
	   check.add(i3);  
	   ImageIcon i4 = getImage("data/engineering.png");
	   check.add(i4);  
	   ImageIcon i5 = getImage("data/irrigation.png");
	   check.add(i5);  
	   ImageIcon i6 = getImage("data/mathematics.png");
	   check.add(i6);  
	   ImageIcon i7 = getImage("data/monarchy.png");
	   check.add(i7);  
	   ImageIcon i8 = getImage("data/printingPress.png");
	   check.add(i8);  
	   ImageIcon i9 = getImage("data/sailing.png");
	   check.add(i9);  
     
		Vector v = name.getTechVector();
		for(int k = 10, j = 0; k < 20; k++, j++)
		{
		  if(v.get(k) == null)//.getClass().getName().equals("java.lang.Object"))
		  {
			 ImageIcon temp = (ImageIcon)check.get(j);
          JButton l = new JButton(temp);  
          l.setPreferredSize(new Dimension(225, 150));//, temp.getIconWidth(), temp.getIconHeight());
          l.setBackground(new Color(0, 0, 0));
          techButtons[k] = l;
			 techButtons[k].addActionListener(this);
	       techPanel.add(techButtons[k]);
		  }
		}
  
		techScrollPane.setBounds(00, 420, 240, 270);    
      techPanel.setBackground(name.getPlayerColor());
      techScrollPane.validate();
		name.playerUI.mainBoard.add(techScrollPane, new Integer(19)); 
      name.playerUI.mainBoard.repaint();
  }

  private void setupLvl3()
  {
      GridLayout grid = new GridLayout(9, 1, 0, 9);
   	JPanel techPanel = new JPanel(grid);
	   techScrollPane = new JScrollPane(techPanel, 22, 31);
      Vector check = new Vector(0, 1);
	   
		ImageIcon i0 = getImage("data/banking.png");
	   check.add(i0);  
		ImageIcon i1 = getImage("data/biology.png");
	   check.add(i1);  
	   ImageIcon i2 = getImage("data/communism.png");
	   check.add(i2);  
	   ImageIcon i3 = getImage("data/gunpowder.png");
	   check.add(i3);  
	   ImageIcon i4 = getImage("data/metalCasting.png");
	   check.add(i4);  
	   ImageIcon i5 = getImage("data/militaryScience.png");
	   check.add(i5);  
	   ImageIcon i6 = getImage("data/railroad.png");
	   check.add(i6);  
	   ImageIcon i7 = getImage("data/steamPower.png");
	   check.add(i7);  
	   ImageIcon i8 = getImage("data/theology.png");
	   check.add(i8);  
     
		Vector v = name.getTechVector();
	   for(int k = 20, j = 0; k < 29; k++, j++)
		{
        
		  if(v.get(k) == null)//.getClass().getName().equals("java.lang.Object"))
		  {
			 ImageIcon temp = (ImageIcon)check.get(j);
          JButton l = new JButton(temp);  
          l.setPreferredSize(new Dimension(225, 150));//, temp.getIconWidth(), temp.getIconHeight());
          l.setBackground(new Color(0, 0, 0));
          techButtons[k] = l;
			 techButtons[k].addActionListener(this);
			 techPanel.add(techButtons[k]);
		  }
		}
	   techScrollPane.setBounds(00, 420, 240, 270);    
      techPanel.setBackground(name.getPlayerColor());
      techScrollPane.validate();
		name.playerUI.mainBoard.add(techScrollPane, new Integer(19)); 
      name.playerUI.mainBoard.repaint();
  }

  private void setupLvl4()
  {
      GridLayout grid = new GridLayout(7, 1, 0, 9);
   	JPanel techPanel = new JPanel(grid);
	   techScrollPane = new JScrollPane(techPanel, 22, 31);
      Vector check = new Vector(0, 1);
	   
		ImageIcon i0 = getImage("data/atomicTheory.png");
	   check.add(i0);  
		ImageIcon i1 = getImage("data/ballistics.png");
	   check.add(i1);  
	   ImageIcon i2 = getImage("data/combustion.png");
	   check.add(i2);  
	   ImageIcon i3 = getImage("data/computers.png");
	   check.add(i3);  
	   ImageIcon i4 = getImage("data/flight.png");
	   check.add(i4);  
	   ImageIcon i5 = getImage("data/massMedia.png");
	   check.add(i5);  
	   ImageIcon i6 = getImage("data/replaceableParts.png");
	   check.add(i6);  
     
		Vector v = name.getTechVector();
	   for(int k = 29, j = 0; k < 36; k++, j++)
		{
        
		  if(v.get(k) == null)//.getClass().getName().equals("java.lang.Object"))
		  {
			 ImageIcon temp = (ImageIcon)check.get(j);
          JButton l = new JButton(temp);  
          l.setPreferredSize(new Dimension(225, 150));//, temp.getIconWidth(), temp.getIconHeight());
          l.setBackground(new Color(0, 0, 0));
          techButtons[k] = l;
			 techButtons[k].addActionListener(this);
			 techPanel.add(techButtons[k]);
		  }
		}
	   techScrollPane.setBounds(00, 420, 240, 270);    
      techPanel.setBackground(name.getPlayerColor());
      techScrollPane.validate();
		name.playerUI.mainBoard.add(techScrollPane, new Integer(19)); 
      name.playerUI.mainBoard.repaint();
  }

  public void actionPerformed(ActionEvent e) 
  {
    if(e.getSource() == done)
    {
	   internal.dispose();
//		if(!name.getFirstTurn())
//		  name.setFirstTurn(true);
//		else
//		{
//		  name.setFirstTurn(false);
//      }	
		playerDone = true;
//		playerWait();
		name.getPlayerUI().getTurn().startTurn(name);
	 }
    if(e.getSource() == lvl1)
    {
		setupLvl1();
	 }
    if(e.getSource() == lvl2)
    {
      setupLvl2();	 
	 }
    if(e.getSource() == lvl3)
    {
      setupLvl3();	 
	 }
    if(e.getSource() == lvl4)
    {
      setupLvl4();	 
	 }
    if(e.getSource() == lvl5)
    {
	 //win
	 }
    for(int k = 0; k < 36; k++)  
	 {
//System.out.println("BUTTON: "+techButtons[k]); 	  
      if(techButtons[k] != null && e.getSource() == techButtons[k])    
      {
//System.out.println("BUTTON: "+k); 	  
  	       name.addTech(k);
			 techScrollPane.setVisible(false);    
		    name.playerUI.mainBoard.remove(0);
		    name.spendTrade(27);
		    holder.removeAll();
			 holder.add(done);
      }
  	 }
  }
}