package games.civ;

import java.io.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.Vector;
import java.beans.*;
import javax.swing.border.*;
import games.*;

public class TradeGUI extends JComponent implements ActionListener, Serializable
{
   private static final long serialVersionUID = -2221318175074413917L;

   private GameInternalFrame internal;
   private GameJLayeredPane format, select;  
	private JTextArea showTrade;  
	private int collectTrade, horseTrade;
	private GameJButton done, horse;
   private Player player;
   private LogicBoard lb;
	private GameJLabel trDial, goDial;
	private boolean hasHorseback;
   private GameJButton[] nm;
   private GameJLabel[] nmHolder;
   private GameJLayeredPane[] nmPane;
	
	public TradeGUI(JComponent frame, Player pName, LogicBoard lbSet)
   {
      horseTrade = 0;
		collectTrade = 0;   
		player = pName;
		lb = lbSet;
		  
		if(player.getTechVector().get(3) != null)
		  hasHorseback = true;
		else 
		  hasHorseback = false;

		format = new GameJLayeredPane();    
      format.setPreferredSize(new Dimension(198, 198));  
			 
      done = new GameJButton("BEGIN CITY MANAGEMENT");
	   done.setBounds(5, 145, 180, 20);
		done.addActionListener(this);
      
      if(hasHorseback && player.getSilk() > 0)
		{
		  horse = new GameJButton("Use Horseback Riding", getImage("data/Silk.png"));
	     horse.setBounds(2, 50, 190, 27);
		  horse.addActionListener(this);
        format.add(horse);
      }
		
		for(int k = 0; k < player.numCities(); k++)
		  collectTrade += player.getCityTrade(k);
				
      player.addTrade(collectTrade);
      if(player.getCurrentGov() == 2)
		{
		  collectTrade += 2;
		}
      if(player.getCurrentGov() == 6)
		{
		  collectTrade -= 2;
		}
		showTrade = new JTextArea("Collected "+collectTrade+" trade!");
      showTrade.setEditable(false);
		showTrade.setBounds(40, 05, 108, 20);
		showTrade.setBorder(new BevelBorder(1, new Color(255, 255, 255), new Color(0, 0, 0)));

		internal = new GameInternalFrame("Trade turn");
	   internal.setPreferredSize(new Dimension(198, 198));

      if(player.getColor().equals("Red"))
		  internal.setBackground(new Color(255, 0, 0));    
      else if(player.getColor().equals("Yellow"))
		  internal.setBackground(new Color(255, 255, 0));    
      else if(player.getColor().equals("Blue"))
		  internal.setBackground(new Color(0, 0, 255));    
      else if(player.getColor().equals("Green"))
		  internal.setBackground(new Color(0, 255, 0)); 
		    
      format.add(showTrade);    
		format.add(done);

      internal.add(format);    
		internal.show();
		internal.toFront();
    
	   GameRotatedIcon trIcon = new GameRotatedIcon(getImage("data/tradeDial.png"), 12.85714285714286*(player.getTrade()-1));
      GameRotatedIcon goIcon = new GameRotatedIcon(getImage("data/goldDial.png"), 323 + (25*(player.getGold())) + (12.85714285714286*(player.getTrade())));
	   ImageIcon PlayerBoardIcon = getImage("data/card"+player.getNation()+".png");

      trDial = player.playerBoard.trDial;
		goDial = player.playerBoard.goDial;

      if(player.playerBoard.playerBrd2.getIndexOf(player.playerBoard.goDial) >= 0)    
		  player.playerBoard.playerBrd2.remove(player.playerBoard.playerBrd2.getIndexOf(player.playerBoard.goDial));
      if(player.playerBoard.playerBrd2.getIndexOf(player.playerBoard.trDial) >= 0)
	     player.playerBoard.playerBrd2.remove(player.playerBoard.playerBrd2.getIndexOf(player.playerBoard.trDial));
      if(player.playerBoard.playerBrd2.getIndexOf(goDial) >= 0)    
		  player.playerBoard.playerBrd2.remove(player.playerBoard.playerBrd2.getIndexOf(goDial));
      if(player.playerBoard.playerBrd2.getIndexOf(trDial) >=0)
		  player.playerBoard.playerBrd2.remove(player.playerBoard.playerBrd2.getIndexOf(trDial));

      trDial.setIcon(trIcon);
      goDial.setIcon(goIcon);

      if(player.getTrade() == 0)    
      {
	     trDial.setBounds(230, 23, trIcon.getIconWidth(), trIcon.getIconHeight());
        goDial.setBounds(272, 60, goIcon.getIconWidth(), goIcon.getIconHeight());
      } 
      else if(player.getTrade() == 1)    
      {
		  trDial.setBounds(231, 23, trIcon.getIconWidth(), trIcon.getIconHeight());
        goDial.setBounds(273, 60, goIcon.getIconWidth(), goIcon.getIconHeight());
      } 
      else if(player.getTrade() == 2)    
      {
		  trDial.setBounds(232, 23, trIcon.getIconWidth(), trIcon.getIconHeight());
        goDial.setBounds(274, 60, goIcon.getIconWidth(), goIcon.getIconHeight());
      } 
      else if(player.getTrade() == 3)    
      {
		  trDial.setBounds(232, 24, trIcon.getIconWidth(), trIcon.getIconHeight());
        goDial.setBounds(274, 61, goIcon.getIconWidth(), goIcon.getIconHeight());
      } 
      else if(player.getTrade() == 4)    
      {
		  trDial.setBounds(232, 24, trIcon.getIconWidth(), trIcon.getIconHeight());
        goDial.setBounds(274, 61, goIcon.getIconWidth(), goIcon.getIconHeight());
      } 
      else if(player.getTrade() == 5)    
      {
		  trDial.setBounds(233, 24, trIcon.getIconWidth(), trIcon.getIconHeight());
        goDial.setBounds(276, 62, goIcon.getIconWidth(), goIcon.getIconHeight());
      } 
      else if(player.getTrade() == 6)    
      {
		  trDial.setBounds(233, 24, trIcon.getIconWidth(), trIcon.getIconHeight());
        goDial.setBounds(278, 62, goIcon.getIconWidth(), goIcon.getIconHeight());
      } 
      else if(player.getTrade() == 7)    
      {
		  trDial.setBounds(233, 25, trIcon.getIconWidth(), trIcon.getIconHeight());
        goDial.setBounds(280, 64, goIcon.getIconWidth(), goIcon.getIconHeight());
      } 
      else if(player.getTrade() == 8)    
      {
		  trDial.setBounds(233, 26, trIcon.getIconWidth(), trIcon.getIconHeight());
        goDial.setBounds(280, 65, goIcon.getIconWidth(), goIcon.getIconHeight());
      } 
      else if(player.getTrade() == 9)    
      {
		  trDial.setBounds(233, 27, trIcon.getIconWidth(), trIcon.getIconHeight());
        goDial.setBounds(280, 66, goIcon.getIconWidth(), goIcon.getIconHeight());
      } 
      else if(player.getTrade() == 10)    
      {
		  trDial.setBounds(233, 27, trIcon.getIconWidth(), trIcon.getIconHeight());
        goDial.setBounds(280, 68, goIcon.getIconWidth(), goIcon.getIconHeight());
      } 
      else if(player.getTrade() == 11)    
      {
		  trDial.setBounds(230, 28, trIcon.getIconWidth(), trIcon.getIconHeight());
        goDial.setBounds(276, 71, goIcon.getIconWidth(), goIcon.getIconHeight());
      } 
      else if(player.getTrade() == 12)    
      {
		  trDial.setBounds(230, 28, trIcon.getIconWidth(), trIcon.getIconHeight());
        goDial.setBounds(275, 71, goIcon.getIconWidth(), goIcon.getIconHeight());
      } 
      else if(player.getTrade() == 13)    
      {
		  trDial.setBounds(230, 28, trIcon.getIconWidth(), trIcon.getIconHeight());
        goDial.setBounds(275, 71, goIcon.getIconWidth(), goIcon.getIconHeight());
      } 
      else if(player.getTrade() == 14)    
      {
		  trDial.setBounds(230, 28, trIcon.getIconWidth(), trIcon.getIconHeight());
        goDial.setBounds(275, 73, goIcon.getIconWidth(), goIcon.getIconHeight());
      } 
      else if(player.getTrade() == 15)    
      {
		  trDial.setBounds(230, 28, trIcon.getIconWidth(), trIcon.getIconHeight());
        goDial.setBounds(274, 73, goIcon.getIconWidth(), goIcon.getIconHeight());
      } 
      else if(player.getTrade() == 16)    
      {
		  trDial.setBounds(230, 28, trIcon.getIconWidth(), trIcon.getIconHeight());
        goDial.setBounds(273, 73, goIcon.getIconWidth(), goIcon.getIconHeight());
      } 
      else if(player.getTrade() == 17)    
      {
		  trDial.setBounds(229, 28, trIcon.getIconWidth(), trIcon.getIconHeight());
        goDial.setBounds(271, 73, goIcon.getIconWidth(), goIcon.getIconHeight());
      } 
      else if(player.getTrade() == 18)    
      {
		  trDial.setBounds(229, 28, trIcon.getIconWidth(), trIcon.getIconHeight());
        goDial.setBounds(270, 73, goIcon.getIconWidth(), goIcon.getIconHeight());
      } 
      else if(player.getTrade() == 19)    
      {
		  trDial.setBounds(230, 28, trIcon.getIconWidth(), trIcon.getIconHeight());
        goDial.setBounds(270, 72, goIcon.getIconWidth(), goIcon.getIconHeight());
      } 
      else if(player.getTrade() == 20)    
      {
		  trDial.setBounds(230, 28, trIcon.getIconWidth(), trIcon.getIconHeight());
        goDial.setBounds(270, 72, goIcon.getIconWidth(), goIcon.getIconHeight());
      } 
      else if(player.getTrade() == 21)    
      {
		  trDial.setBounds(230, 27, trIcon.getIconWidth(), trIcon.getIconHeight());
        goDial.setBounds(270, 70, goIcon.getIconWidth(), goIcon.getIconHeight());
      } 
      else if(player.getTrade() == 22)    
      {
		  trDial.setBounds(230, 26, trIcon.getIconWidth(), trIcon.getIconHeight());
        goDial.setBounds(269, 69, goIcon.getIconWidth(), goIcon.getIconHeight());
      } 
      else if(player.getTrade() == 23)    
      {
		  trDial.setBounds(230, 25, trIcon.getIconWidth(), trIcon.getIconHeight());
        goDial.setBounds(269, 68, goIcon.getIconWidth(), goIcon.getIconHeight());
      } 
      else if(player.getTrade() == 24)    
      {
		  trDial.setBounds(230, 24, trIcon.getIconWidth(), trIcon.getIconHeight());
        goDial.setBounds(269, 67, goIcon.getIconWidth(), goIcon.getIconHeight());
      } 
      else if(player.getTrade() == 25)    
      {
		  trDial.setBounds(230, 24, trIcon.getIconWidth(), trIcon.getIconHeight());
        goDial.setBounds(269, 66, goIcon.getIconWidth(), goIcon.getIconHeight());
      } 
      else if(player.getTrade() == 26)    
      {
		  trDial.setBounds(230, 24, trIcon.getIconWidth(), trIcon.getIconHeight());
        goDial.setBounds(270, 66, goIcon.getIconWidth(), goIcon.getIconHeight());
      } 
      else if(player.getTrade() == 27)    
      {
		  trDial.setBounds(230, 24, trIcon.getIconWidth(), trIcon.getIconHeight());
        goDial.setBounds(270, 65, goIcon.getIconWidth(), goIcon.getIconHeight());
      } 
      
      player.playerBoard.playerBrd2.add(trDial, new Integer(50));
      player.playerBoard.playerBrd2.add(goDial, new Integer(51));

	   internal.setBorder(new BevelBorder(1, new Color(0, 0, 0), new Color(255, 255, 255)));
		frame.removeAll();
		frame.add(internal);

	   internal.setMaximum(true);
	   frame.validate();

      if(player.getNation().equals(player.playerUI.aiChoice))
	   {
//	     System.out.println("AI TRADEGUI");
//	     CivGUI.turn.cityTurn(player);
	   }
  }

  private void addHorseTrade()
  {
	      GameRotatedIcon trIcon = new GameRotatedIcon(getImage("data/tradeDial.png"), 12.85714285714286*(player.getTrade()-1));
         GameRotatedIcon goIcon = new GameRotatedIcon(getImage("data/goldDial.png"), 323 + (25*(player.getGold())) + (12.85714285714286*(player.getTrade())));

			GameJLabel starttrDial = player.playerBoard.trDial;
	      starttrDial.setIcon(trIcon);
	      GameJLabel startgoDial = player.playerBoard.goDial;
	      startgoDial.setIcon(goIcon);
	
	      if(player.getTrade()  == 0)    
	      {
		     starttrDial.setBounds(230, 23, trIcon.getIconWidth(), trIcon.getIconHeight());
	        startgoDial.setBounds(272, 60, goIcon.getIconWidth(), goIcon.getIconHeight());
	      } 
	      else if(player.getTrade()  == 1)    
	      {
			  starttrDial.setBounds(231, 23, trIcon.getIconWidth(), trIcon.getIconHeight());
	        startgoDial.setBounds(273, 60, goIcon.getIconWidth(), goIcon.getIconHeight());
	      } 
	      else if(player.getTrade()  == 2)    
	      {
			  starttrDial.setBounds(232, 23, trIcon.getIconWidth(), trIcon.getIconHeight());
	        startgoDial.setBounds(274, 60, goIcon.getIconWidth(), goIcon.getIconHeight());
	      } 
	      else if(player.getTrade()  == 3)    
	      {
			  starttrDial.setBounds(232, 24, trIcon.getIconWidth(), trIcon.getIconHeight());
	        startgoDial.setBounds(274, 61, goIcon.getIconWidth(), goIcon.getIconHeight());
	      } 
	      else if(player.getTrade()  == 4)    
	      {
			  starttrDial.setBounds(232, 24, trIcon.getIconWidth(), trIcon.getIconHeight());
	        startgoDial.setBounds(274, 61, goIcon.getIconWidth(), goIcon.getIconHeight());
	      } 
	      else if(player.getTrade()  == 5)    
	      {
			  starttrDial.setBounds(233, 24, trIcon.getIconWidth(), trIcon.getIconHeight());
	        startgoDial.setBounds(276, 62, goIcon.getIconWidth(), goIcon.getIconHeight());
	      } 
	      else if(player.getTrade()  == 6)    
	      {
			  starttrDial.setBounds(233, 24, trIcon.getIconWidth(), trIcon.getIconHeight());
	        startgoDial.setBounds(278, 62, goIcon.getIconWidth(), goIcon.getIconHeight());
	      } 
	      else if(player.getTrade()  == 7)    
	      {
			  starttrDial.setBounds(233, 25, trIcon.getIconWidth(), trIcon.getIconHeight());
	        startgoDial.setBounds(280, 64, goIcon.getIconWidth(), goIcon.getIconHeight());
	      } 
	      else if(player.getTrade()  == 8)    
	      {
			  starttrDial.setBounds(233, 26, trIcon.getIconWidth(), trIcon.getIconHeight());
	        startgoDial.setBounds(280, 65, goIcon.getIconWidth(), goIcon.getIconHeight());
	      } 
	      else if(player.getTrade()  == 9)    
	      {
			  starttrDial.setBounds(233, 27, trIcon.getIconWidth(), trIcon.getIconHeight());
	        startgoDial.setBounds(280, 66, goIcon.getIconWidth(), goIcon.getIconHeight());
	      } 
	      else if(player.getTrade()  == 10)    
	      {
			  starttrDial.setBounds(233, 27, trIcon.getIconWidth(), trIcon.getIconHeight());
	        startgoDial.setBounds(280, 68, goIcon.getIconWidth(), goIcon.getIconHeight());
	      } 
	      else if(player.getTrade()  == 11)    
	      {
			  starttrDial.setBounds(230, 28, trIcon.getIconWidth(), trIcon.getIconHeight());
	        startgoDial.setBounds(276, 71, goIcon.getIconWidth(), goIcon.getIconHeight());
	      } 
	      else if(player.getTrade()  == 12)    
	      {
			  starttrDial.setBounds(230, 28, trIcon.getIconWidth(), trIcon.getIconHeight());
	        startgoDial.setBounds(275, 71, goIcon.getIconWidth(), goIcon.getIconHeight());
	      } 
	      else if(player.getTrade()  == 13)    
	      {
			  starttrDial.setBounds(230, 28, trIcon.getIconWidth(), trIcon.getIconHeight());
	        startgoDial.setBounds(275, 71, goIcon.getIconWidth(), goIcon.getIconHeight());
	      } 
	      else if(player.getTrade()  == 14)    
	      {
			  starttrDial.setBounds(230, 28, trIcon.getIconWidth(), trIcon.getIconHeight());
	        startgoDial.setBounds(275, 73, goIcon.getIconWidth(), goIcon.getIconHeight());
	      } 
	      else if(player.getTrade()  == 15)    
	      {
			  starttrDial.setBounds(230, 28, trIcon.getIconWidth(), trIcon.getIconHeight());
	        startgoDial.setBounds(274, 73, goIcon.getIconWidth(), goIcon.getIconHeight());
	      } 
	      else if(player.getTrade()  == 16)    
	      {
			  starttrDial.setBounds(230, 28, trIcon.getIconWidth(), trIcon.getIconHeight());
	        startgoDial.setBounds(273, 73, goIcon.getIconWidth(), goIcon.getIconHeight());
	      } 
	      else if(player.getTrade()  == 17)    
	      {
			  starttrDial.setBounds(229, 28, trIcon.getIconWidth(), trIcon.getIconHeight());
	        startgoDial.setBounds(271, 73, goIcon.getIconWidth(), goIcon.getIconHeight());
	      } 
	      else if(player.getTrade()  == 18)    
	      {
			  starttrDial.setBounds(229, 28, trIcon.getIconWidth(), trIcon.getIconHeight());
	        startgoDial.setBounds(270, 73, goIcon.getIconWidth(), goIcon.getIconHeight());
	      } 
	      else if(player.getTrade()  == 19)    
	      {
			  starttrDial.setBounds(230, 28, trIcon.getIconWidth(), trIcon.getIconHeight());
	        startgoDial.setBounds(270, 72, goIcon.getIconWidth(), goIcon.getIconHeight());
	      } 
	      else if(player.getTrade()  == 20)    
	      {
			  starttrDial.setBounds(230, 28, trIcon.getIconWidth(), trIcon.getIconHeight());
	        startgoDial.setBounds(270, 72, goIcon.getIconWidth(), goIcon.getIconHeight());
	      } 
	      else if(player.getTrade()  == 21)    
	      {
			  starttrDial.setBounds(230, 27, trIcon.getIconWidth(), trIcon.getIconHeight());
	        startgoDial.setBounds(270, 70, goIcon.getIconWidth(), goIcon.getIconHeight());
	      } 
	      else if(player.getTrade()  == 22)    
	      {
			  starttrDial.setBounds(230, 26, trIcon.getIconWidth(), trIcon.getIconHeight());
	        startgoDial.setBounds(269, 69, goIcon.getIconWidth(), goIcon.getIconHeight());
	      } 
	      else if(player.getTrade()  == 23)    
	      {
			  starttrDial.setBounds(230, 25, trIcon.getIconWidth(), trIcon.getIconHeight());
	        startgoDial.setBounds(269, 68, goIcon.getIconWidth(), goIcon.getIconHeight());
	      } 
	      else if(player.getTrade()  == 24)    
	      {
			  starttrDial.setBounds(230, 24, trIcon.getIconWidth(), trIcon.getIconHeight());
	        startgoDial.setBounds(269, 67, goIcon.getIconWidth(), goIcon.getIconHeight());
	      } 
	      else if(player.getTrade()  == 25)    
	      {
			  starttrDial.setBounds(230, 24, trIcon.getIconWidth(), trIcon.getIconHeight());
	        startgoDial.setBounds(269, 66, goIcon.getIconWidth(), goIcon.getIconHeight());
	      } 
	      else if(player.getTrade()  == 26)    
	      {
			  starttrDial.setBounds(230, 24, trIcon.getIconWidth(), trIcon.getIconHeight());
	        startgoDial.setBounds(270, 66, goIcon.getIconWidth(), goIcon.getIconHeight());
	      } 
	      else if(player.getTrade()  == 27)    
	      {
			  starttrDial.setBounds(230, 24, trIcon.getIconWidth(), trIcon.getIconHeight());
	        startgoDial.setBounds(270, 65, goIcon.getIconWidth(), goIcon.getIconHeight());
	      } 
		   horseTrade = 0;  
			player.playerBoard.playerBrd2.remove(0);
		   player.playerBoard.playerBrd2.remove(0);

         player.playerBoard.playerBrd2.add(starttrDial, new Integer(50));
         player.playerBoard.playerBrd2.add(startgoDial, new Integer(51));

         player.playerBoard.playerBrd2.setPreferredSize(new Dimension(400, 268));
         player.playerBoard.playerBrd2.validate();
  }
  
  public static void startTrade(Player player, JComponent frame, boolean reset)
  {
	   GameRotatedIcon trIcon = new GameRotatedIcon(getStaticImage("data/tradeDial.png"), 12.85714285714286*(player.getTrade()-1));
      GameRotatedIcon goIcon = new GameRotatedIcon(getStaticImage("data/goldDial.png"), 323 + (25*(player.getGold())) + (12.85714285714286*(player.getTrade())));
	   ImageIcon PlayerBoardIcon = getStaticImage("data/card"+player.getNation()+".png");

      if(!reset)
		{    
			GameJLabel starttrDial = player.playerBoard.trDial;
	      starttrDial.setIcon(trIcon);
	      GameJLabel startgoDial = player.playerBoard.goDial;
	      startgoDial.setIcon(goIcon);
	
	      if(player.getTrade() == 0)    
	      {
		     starttrDial.setBounds(230, 23, trIcon.getIconWidth(), trIcon.getIconHeight());
	        startgoDial.setBounds(272, 60, goIcon.getIconWidth(), goIcon.getIconHeight());
	      } 
	      else if(player.getTrade() == 1)    
	      {
			  starttrDial.setBounds(231, 23, trIcon.getIconWidth(), trIcon.getIconHeight());
	        startgoDial.setBounds(273, 60, goIcon.getIconWidth(), goIcon.getIconHeight());
	      } 
	      else if(player.getTrade() == 2)    
	      {
			  starttrDial.setBounds(232, 23, trIcon.getIconWidth(), trIcon.getIconHeight());
	        startgoDial.setBounds(274, 60, goIcon.getIconWidth(), goIcon.getIconHeight());
	      } 
	      else if(player.getTrade() == 3)    
	      {
			  starttrDial.setBounds(232, 24, trIcon.getIconWidth(), trIcon.getIconHeight());
	        startgoDial.setBounds(274, 61, goIcon.getIconWidth(), goIcon.getIconHeight());
	      } 
	      else if(player.getTrade() == 4)    
	      {
			  starttrDial.setBounds(232, 24, trIcon.getIconWidth(), trIcon.getIconHeight());
	        startgoDial.setBounds(274, 61, goIcon.getIconWidth(), goIcon.getIconHeight());
	      } 
	      else if(player.getTrade() == 5)    
	      {
			  starttrDial.setBounds(233, 24, trIcon.getIconWidth(), trIcon.getIconHeight());
	        startgoDial.setBounds(276, 62, goIcon.getIconWidth(), goIcon.getIconHeight());
	      } 
	      else if(player.getTrade() == 6)    
	      {
			  starttrDial.setBounds(233, 24, trIcon.getIconWidth(), trIcon.getIconHeight());
	        startgoDial.setBounds(278, 62, goIcon.getIconWidth(), goIcon.getIconHeight());
	      } 
	      else if(player.getTrade() == 7)    
	      {
			  starttrDial.setBounds(233, 25, trIcon.getIconWidth(), trIcon.getIconHeight());
	        startgoDial.setBounds(280, 64, goIcon.getIconWidth(), goIcon.getIconHeight());
	      } 
	      else if(player.getTrade() == 8)    
	      {
			  starttrDial.setBounds(233, 26, trIcon.getIconWidth(), trIcon.getIconHeight());
	        startgoDial.setBounds(280, 65, goIcon.getIconWidth(), goIcon.getIconHeight());
	      } 
	      else if(player.getTrade() == 9)    
	      {
			  starttrDial.setBounds(233, 27, trIcon.getIconWidth(), trIcon.getIconHeight());
	        startgoDial.setBounds(280, 66, goIcon.getIconWidth(), goIcon.getIconHeight());
	      } 
	      else if(player.getTrade() == 10)    
	      {
			  starttrDial.setBounds(233, 27, trIcon.getIconWidth(), trIcon.getIconHeight());
	        startgoDial.setBounds(280, 68, goIcon.getIconWidth(), goIcon.getIconHeight());
	      } 
	      else if(player.getTrade() == 11)    
	      {
			  starttrDial.setBounds(230, 28, trIcon.getIconWidth(), trIcon.getIconHeight());
	        startgoDial.setBounds(276, 71, goIcon.getIconWidth(), goIcon.getIconHeight());
	      } 
	      else if(player.getTrade() == 12)    
	      {
			  starttrDial.setBounds(230, 28, trIcon.getIconWidth(), trIcon.getIconHeight());
	        startgoDial.setBounds(275, 71, goIcon.getIconWidth(), goIcon.getIconHeight());
	      } 
	      else if(player.getTrade() == 13)    
	      {
			  starttrDial.setBounds(230, 28, trIcon.getIconWidth(), trIcon.getIconHeight());
	        startgoDial.setBounds(275, 71, goIcon.getIconWidth(), goIcon.getIconHeight());
	      } 
	      else if(player.getTrade() == 14)    
	      {
			  starttrDial.setBounds(230, 28, trIcon.getIconWidth(), trIcon.getIconHeight());
	        startgoDial.setBounds(275, 73, goIcon.getIconWidth(), goIcon.getIconHeight());
	      } 
	      else if(player.getTrade() == 15)    
	      {
			  starttrDial.setBounds(230, 28, trIcon.getIconWidth(), trIcon.getIconHeight());
	        startgoDial.setBounds(274, 73, goIcon.getIconWidth(), goIcon.getIconHeight());
	      } 
	      else if(player.getTrade() == 16)    
	      {
			  starttrDial.setBounds(230, 28, trIcon.getIconWidth(), trIcon.getIconHeight());
	        startgoDial.setBounds(273, 73, goIcon.getIconWidth(), goIcon.getIconHeight());
	      } 
	      else if(player.getTrade() == 17)    
	      {
			  starttrDial.setBounds(229, 28, trIcon.getIconWidth(), trIcon.getIconHeight());
	        startgoDial.setBounds(271, 73, goIcon.getIconWidth(), goIcon.getIconHeight());
	      } 
	      else if(player.getTrade() == 18)    
	      {
			  starttrDial.setBounds(229, 28, trIcon.getIconWidth(), trIcon.getIconHeight());
	        startgoDial.setBounds(270, 73, goIcon.getIconWidth(), goIcon.getIconHeight());
	      } 
	      else if(player.getTrade() == 19)    
	      {
			  starttrDial.setBounds(230, 28, trIcon.getIconWidth(), trIcon.getIconHeight());
	        startgoDial.setBounds(270, 72, goIcon.getIconWidth(), goIcon.getIconHeight());
	      } 
	      else if(player.getTrade() == 20)    
	      {
			  starttrDial.setBounds(230, 28, trIcon.getIconWidth(), trIcon.getIconHeight());
	        startgoDial.setBounds(270, 72, goIcon.getIconWidth(), goIcon.getIconHeight());
	      } 
	      else if(player.getTrade() == 21)    
	      {
			  starttrDial.setBounds(230, 27, trIcon.getIconWidth(), trIcon.getIconHeight());
	        startgoDial.setBounds(270, 70, goIcon.getIconWidth(), goIcon.getIconHeight());
	      } 
	      else if(player.getTrade() == 22)    
	      {
			  starttrDial.setBounds(230, 26, trIcon.getIconWidth(), trIcon.getIconHeight());
	        startgoDial.setBounds(269, 69, goIcon.getIconWidth(), goIcon.getIconHeight());
	      } 
	      else if(player.getTrade() == 23)    
	      {
			  starttrDial.setBounds(230, 25, trIcon.getIconWidth(), trIcon.getIconHeight());
	        startgoDial.setBounds(269, 68, goIcon.getIconWidth(), goIcon.getIconHeight());
	      } 
	      else if(player.getTrade() == 24)    
	      {
			  starttrDial.setBounds(230, 24, trIcon.getIconWidth(), trIcon.getIconHeight());
	        startgoDial.setBounds(269, 67, goIcon.getIconWidth(), goIcon.getIconHeight());
	      } 
	      else if(player.getTrade() == 25)    
	      {
			  starttrDial.setBounds(230, 24, trIcon.getIconWidth(), trIcon.getIconHeight());
	        startgoDial.setBounds(269, 66, goIcon.getIconWidth(), goIcon.getIconHeight());
	      } 
	      else if(player.getTrade() == 26)    
	      {
			  starttrDial.setBounds(230, 24, trIcon.getIconWidth(), trIcon.getIconHeight());
	        startgoDial.setBounds(270, 66, goIcon.getIconWidth(), goIcon.getIconHeight());
	      } 
	      else if(player.getTrade() == 27)    
	      {
			  starttrDial.setBounds(230, 24, trIcon.getIconWidth(), trIcon.getIconHeight());
	        startgoDial.setBounds(270, 65, goIcon.getIconWidth(), goIcon.getIconHeight());
	      } 
			
		   player.playerBoard.playerBrd2.remove(player.playerBoard.playerBrd2.getIndexOf(starttrDial));
         player.playerBoard.playerBrd2.remove(player.playerBoard.playerBrd2.getIndexOf(startgoDial));

         player.playerBoard.playerBrd2.add(starttrDial, new Integer(50));
         player.playerBoard.playerBrd2.add(startgoDial, new Integer(51));
      }
		else 
		{
			GameJLabel starttrDial = player.playerBoard.trDial;
	      starttrDial.setIcon(trIcon);
	      GameJLabel startgoDial = player.playerBoard.goDial;
	      startgoDial.setIcon(goIcon);
	
	      if(player.getTrade() == 0)    
	      {
		     starttrDial.setBounds(230, 23, trIcon.getIconWidth(), trIcon.getIconHeight());
	        startgoDial.setBounds(272, 60, goIcon.getIconWidth(), goIcon.getIconHeight());
	      } 
	      else if(player.getTrade() == 1)    
	      {
			  starttrDial.setBounds(231, 23, trIcon.getIconWidth(), trIcon.getIconHeight());
	        startgoDial.setBounds(273, 60, goIcon.getIconWidth(), goIcon.getIconHeight());
	      } 
	      else if(player.getTrade() == 2)    
	      {
			  starttrDial.setBounds(232, 23, trIcon.getIconWidth(), trIcon.getIconHeight());
	        startgoDial.setBounds(274, 60, goIcon.getIconWidth(), goIcon.getIconHeight());
	      } 
	      else if(player.getTrade() == 3)    
	      {
			  starttrDial.setBounds(232, 24, trIcon.getIconWidth(), trIcon.getIconHeight());
	        startgoDial.setBounds(274, 61, goIcon.getIconWidth(), goIcon.getIconHeight());
	      } 
	      else if(player.getTrade() == 4)    
	      {
			  starttrDial.setBounds(232, 24, trIcon.getIconWidth(), trIcon.getIconHeight());
	        startgoDial.setBounds(274, 61, goIcon.getIconWidth(), goIcon.getIconHeight());
	      } 
	      else if(player.getTrade() == 5)    
	      {
			  starttrDial.setBounds(233, 24, trIcon.getIconWidth(), trIcon.getIconHeight());
	        startgoDial.setBounds(276, 62, goIcon.getIconWidth(), goIcon.getIconHeight());
	      } 
	      else if(player.getTrade() == 6)    
	      {
			  starttrDial.setBounds(233, 24, trIcon.getIconWidth(), trIcon.getIconHeight());
	        startgoDial.setBounds(278, 62, goIcon.getIconWidth(), goIcon.getIconHeight());
	      } 
	      else if(player.getTrade() == 7)    
	      {
			  starttrDial.setBounds(233, 25, trIcon.getIconWidth(), trIcon.getIconHeight());
	        startgoDial.setBounds(280, 64, goIcon.getIconWidth(), goIcon.getIconHeight());
	      } 
	      else if(player.getTrade() == 8)    
	      {
			  starttrDial.setBounds(233, 26, trIcon.getIconWidth(), trIcon.getIconHeight());
	        startgoDial.setBounds(280, 65, goIcon.getIconWidth(), goIcon.getIconHeight());
	      } 
	      else if(player.getTrade() == 9)    
	      {
			  starttrDial.setBounds(233, 27, trIcon.getIconWidth(), trIcon.getIconHeight());
	        startgoDial.setBounds(280, 66, goIcon.getIconWidth(), goIcon.getIconHeight());
	      } 
	      else if(player.getTrade() == 10)    
	      {
			  starttrDial.setBounds(233, 27, trIcon.getIconWidth(), trIcon.getIconHeight());
	        startgoDial.setBounds(280, 68, goIcon.getIconWidth(), goIcon.getIconHeight());
	      } 
	      else if(player.getTrade() == 11)    
	      {
			  starttrDial.setBounds(230, 28, trIcon.getIconWidth(), trIcon.getIconHeight());
	        startgoDial.setBounds(276, 71, goIcon.getIconWidth(), goIcon.getIconHeight());
	      } 
	      else if(player.getTrade() == 12)    
	      {
			  starttrDial.setBounds(230, 28, trIcon.getIconWidth(), trIcon.getIconHeight());
	        startgoDial.setBounds(275, 71, goIcon.getIconWidth(), goIcon.getIconHeight());
	      } 
	      else if(player.getTrade() == 13)    
	      {
			  starttrDial.setBounds(230, 28, trIcon.getIconWidth(), trIcon.getIconHeight());
	        startgoDial.setBounds(275, 71, goIcon.getIconWidth(), goIcon.getIconHeight());
	      } 
	      else if(player.getTrade() == 14)    
	      {
			  starttrDial.setBounds(230, 28, trIcon.getIconWidth(), trIcon.getIconHeight());
	        startgoDial.setBounds(275, 73, goIcon.getIconWidth(), goIcon.getIconHeight());
	      } 
	      else if(player.getTrade() == 15)    
	      {
			  starttrDial.setBounds(230, 28, trIcon.getIconWidth(), trIcon.getIconHeight());
	        startgoDial.setBounds(274, 73, goIcon.getIconWidth(), goIcon.getIconHeight());
	      } 
	      else if(player.getTrade() == 16)    
	      {
			  starttrDial.setBounds(230, 28, trIcon.getIconWidth(), trIcon.getIconHeight());
	        startgoDial.setBounds(273, 73, goIcon.getIconWidth(), goIcon.getIconHeight());
	      } 
	      else if(player.getTrade() == 17)    
	      {
			  starttrDial.setBounds(229, 28, trIcon.getIconWidth(), trIcon.getIconHeight());
	        startgoDial.setBounds(271, 73, goIcon.getIconWidth(), goIcon.getIconHeight());
	      } 
	      else if(player.getTrade() == 18)    
	      {
			  starttrDial.setBounds(229, 28, trIcon.getIconWidth(), trIcon.getIconHeight());
	        startgoDial.setBounds(270, 73, goIcon.getIconWidth(), goIcon.getIconHeight());
	      } 
	      else if(player.getTrade() == 19)    
	      {
			  starttrDial.setBounds(230, 28, trIcon.getIconWidth(), trIcon.getIconHeight());
	        startgoDial.setBounds(270, 72, goIcon.getIconWidth(), goIcon.getIconHeight());
	      } 
	      else if(player.getTrade() == 20)    
	      {
			  starttrDial.setBounds(230, 28, trIcon.getIconWidth(), trIcon.getIconHeight());
	        startgoDial.setBounds(270, 72, goIcon.getIconWidth(), goIcon.getIconHeight());
	      } 
	      else if(player.getTrade() == 21)    
	      {
			  starttrDial.setBounds(230, 27, trIcon.getIconWidth(), trIcon.getIconHeight());
	        startgoDial.setBounds(270, 70, goIcon.getIconWidth(), goIcon.getIconHeight());
	      } 
	      else if(player.getTrade() == 22)    
	      {
			  starttrDial.setBounds(230, 26, trIcon.getIconWidth(), trIcon.getIconHeight());
	        startgoDial.setBounds(269, 69, goIcon.getIconWidth(), goIcon.getIconHeight());
	      } 
	      else if(player.getTrade() == 23)    
	      {
			  starttrDial.setBounds(230, 25, trIcon.getIconWidth(), trIcon.getIconHeight());
	        startgoDial.setBounds(269, 68, goIcon.getIconWidth(), goIcon.getIconHeight());
	      } 
	      else if(player.getTrade() == 24)    
	      {
			  starttrDial.setBounds(230, 24, trIcon.getIconWidth(), trIcon.getIconHeight());
	        startgoDial.setBounds(269, 67, goIcon.getIconWidth(), goIcon.getIconHeight());
	      } 
	      else if(player.getTrade() == 25)    
	      {
			  starttrDial.setBounds(230, 24, trIcon.getIconWidth(), trIcon.getIconHeight());
	        startgoDial.setBounds(269, 66, goIcon.getIconWidth(), goIcon.getIconHeight());
	      } 
	      else if(player.getTrade() == 26)    
	      {
			  starttrDial.setBounds(230, 24, trIcon.getIconWidth(), trIcon.getIconHeight());
	        startgoDial.setBounds(270, 66, goIcon.getIconWidth(), goIcon.getIconHeight());
	      } 
	      else if(player.getTrade() == 27)    
	      {
			  starttrDial.setBounds(230, 24, trIcon.getIconWidth(), trIcon.getIconHeight());
	        startgoDial.setBounds(270, 65, goIcon.getIconWidth(), goIcon.getIconHeight());
	      } 
		   player.playerBoard.playerBrd2.remove(0);
		   player.playerBoard.playerBrd2.remove(0);

         player.playerBoard.playerBrd2.add(starttrDial, new Integer(50));
         player.playerBoard.playerBrd2.add(startgoDial, new Integer(51));
		}

      player.playerBoard.playerBrd2.setPreferredSize(new Dimension(400, 268));
      player.playerBoard.playerBrd2.validate();
  }

  public void actionPerformed(ActionEvent e) 
  {
    if(e.getSource() == done)
    {
	   internal.dispose();
		player.getPlayerUI().getTurn().cityTurn(player); 
	 }
    else if(e.getSource() == horse)//another player gains 6 trade
    {
//	   usedHorse = true;  
		horseTrade = 9;
		player.spendResource("Silk");
	   player.addTrade(9);  
		addHorseTrade();
      horseTrade = 0;
//		if(player.getSilk() <= 0)
//		{
		format.remove(format.getIndexOf(horse));
		format.repaint();
//		}  
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
     }catch(IOException IOEx){
        System.out.println("IOEx NULL IMAGE IN CivLAUNCH");       
        IOEx.printStackTrace();	     
	  }catch(java.net.URISyntaxException URIEx){
        System.out.println("URIEx NULL IMAGE IN CivLAUNCH");       
        URIEx.printStackTrace();	     
	  }
	  return null;
   }
   
	public static GameImageIcon getStaticImage(String pathName){
     GameImageIcon image;
     java.net.URL url = CivGUI.class.getResource(pathName); 
     try{
		  if(url != null){
		    image = new GameImageIcon(url.toURI());
			 return image;    
	     }
	     else 
	       System.out.println("NULL IMAGE IN CivLAUNCH");       
     }catch(IOException IOEx){
        System.out.println("IOEx NULL IMAGE IN CivLAUNCH");       
        IOEx.printStackTrace();	     
	  }catch(java.net.URISyntaxException URIEx){
        System.out.println("URIEx NULL IMAGE IN CivLAUNCH");       
        URIEx.printStackTrace();	     
	  }
	  return null;
   }

	public GameInternalFrame getInternal() {
		return internal;
	}
	public void setInternal(GameInternalFrame internal) {
		this.internal = internal;
	}
	public GameJLayeredPane getFormat() {
		return format;
	}
	public void setFormat(GameJLayeredPane format) {
		this.format = format;
	}
	public GameJLayeredPane getSelect() {
		return select;
	}
	public void setSelect(GameJLayeredPane select) {
		this.select = select;
	}
	public JTextArea getShowTrade() {
		return showTrade;
	}
	public void setShowTrade(JTextArea showTrade) {
		this.showTrade = showTrade;
	}
	public int getCollectTrade() {
		return collectTrade;
	}
	public void setCollectTrade(int collectTrade) {
		this.collectTrade = collectTrade;
	}
	public int getHorseTrade() {
		return horseTrade;
	}
	public void setHorseTrade(int horseTrade) {
		this.horseTrade = horseTrade;
	}
	public GameJButton getDone() {
		return done;
	}
	public void setDone(GameJButton done) {
		this.done = done;
	}
	public GameJButton getHorse() {
		return horse;
	}
	public void setHorse(GameJButton horse) {
		this.horse = horse;
	}
	public Player getPlayer() {
		return player;
	}
	public void setPlayer(Player player) {
		this.player = player;
	}
	public LogicBoard getLb() {
		return lb;
	}
	public void setLb(LogicBoard lb) {
		this.lb = lb;
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
	public boolean getHasHorseback() {
		return hasHorseback;
	}
	public void setHasHorseback(boolean hasHorseback) {
		this.hasHorseback = hasHorseback;
	}
	public GameJButton[] getNm() {
		return nm;
	}
	public void setNm(GameJButton[] nm) {
		this.nm = nm;
	}
	public GameJLabel[] getNmHolder() {
		return nmHolder;
	}
	public void setNmHolder(GameJLabel[] nmHolder) {
		this.nmHolder = nmHolder;
	}
	public GameJLayeredPane[] getNmPane() {
		return nmPane;
	}
	public void setNmPane(GameJLayeredPane[] nmPane) {
		this.nmPane = nmPane;
	}
}