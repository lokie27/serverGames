package games.civ;

import java.io.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.Vector;
import java.beans.*;
import javax.swing.border.*;
import games.*;

public class StartGUI extends JComponent implements ActionListener, MouseListener
{
   JInternalFrame internal;
   JScrollPane startScrollPane;  
	JLayeredPane format, nmLinkPane, select;
	JButton done, skip, skipCity, skipResource, skipPerson, govChange, buttonPeople, buttonBuild, buttonLink,
	        anarchy, gardenArmy, gardenSettler, republic, democracy, feud, fund, monarchy, communism, despot;
   JButton[] nmCity, spaceButtons, nmLink, cityButtons, peopleButtons, buildingButtons, peoplecityButtons;  
   JLayeredPane[] nmCityPane;
   JPanel startPanel;
	Player name;
	LogicBoard lb;
	JLabel cityLabel;
   JLabel[] nmCityHolder, nmLinkHolder, peopleHolder;
   Settler[] s;  
	Settler activeSettler;
	int sourcePeice, targetPeice, size, cityXpos, cityYpos, cityNumber, activeChoice, personNumber, options;
	Vector movePeices, tempSettlers, temparmies, legalSpace, cityVector, peopleCityVector, settlerTemp, tempPeople, legalBuilding;
   Object currentReposition;
	boolean repositionActive, linkActive, linkToCity, canBuild, buildActive,
	        peopleActive, peopleCityActive, peopleSpaceActive, skipBuild, gardenUsed;
		
	public StartGUI(JComponent frame, Player pName, LogicBoard lbSet)
   {
	 if(!true)//pName.getNation().equals(CivGUI.aiChoice))
	 {
//	   System.out.println("AI STARTGUI");
//		CivGUI.turn.tradeTurn(pName);
	 }
	 else
	 {
		name = pName;
		lb = lbSet;

      buildActive = false;    
		skipBuild = false;    
		peopleActive = false;
	   repositionActive = false;
	   linkToCity = false;
	   linkActive = false;
      canBuild = false;
      gardenUsed = false;
				
		name.resetCityLinkVector();//reset Settler links
		    
      done = new JButton("BEGIN TRADE TURN");
	   done.setPreferredSize(new Dimension(180, 20));  
		done.setBounds(5, 145, 180, 20);

		internal = new JInternalFrame("Start of turn");
  
      format = new JLayeredPane();
	    
      if(name.getColor().equals("Red"))
		  internal.setBackground(new Color(255, 0, 0));    
      else if(name.getColor().equals("Yellow"))
		  internal.setBackground(new Color(255, 255, 0));    
      else if(name.getColor().equals("Blue"))
		  internal.setBackground(new Color(0, 0, 255));    
      else if(name.getColor().equals("Green"))
		  internal.setBackground(new Color(0, 255, 0));    

//		format.setPreferredSize(new Dimension(198, 300));
      format.setBounds(00, 00, 198, 198);
	   
	   internal.setBorder(new BevelBorder(1, new Color(0, 0, 0), new Color(255, 255, 255)));
	   internal.setPreferredSize(new Dimension(198, 198));
//      internal.setBounds(00, 00, 198, 198);
      internal.show();

      if(name.getColor().equals("Red"))
		  frame.setBackground(new Color(255, 0, 0));    
      else if(name.getColor().equals("Yellow"))
		  frame.setBackground(new Color(255, 255, 0));    
      else if(name.getColor().equals("Blue"))
		  frame.setBackground(new Color(0, 0, 255));    
      else if(name.getColor().equals("Green"))
		  frame.setBackground(new Color(0, 255, 0)); 

//startofturn wonder actions
      options = 0;
      if(name.getWonderCards(0))
		  name.addCulture(1);
      if(name.getWonderCards(1))
		{
		  name.addTrade(3);
        TradeGUI.startTrade(name, name.playerBoard.playerBrd2, true);
		}  
      if(name.getWonderCards(7))//louvre
		  name.addCulture(3);
      if(name.getWonderCards(8))//panama canal gain a gold
      {
        name.addGold(1);
        TradeGUI.startTrade(name, name.playerBoard.playerBrd2, true);
		}

		int k = 0, p = 0;
      if(name.getCurrentGov() != 1)    
      {
	      movePeices = new Vector(name.settlers.size());
	      size = movePeices.size();
			
			if(!name.settlers.isEmpty())
		   {
	        for(; k < name.settlers.size(); k++)
		     {
			    movePeices.add(name.settlers.get(k));
			    size++;
			  }  
	      }
      }
      else
		{
	      movePeices = new Vector(name.settlers.size() + name.armies.size());
	      size = movePeices.size();
			
			if(!name.settlers.isEmpty() || !name.armies.isEmpty())
		   {
	        for(; k < name.settlers.size(); k++)
		     {
			    movePeices.add(name.settlers.get(k));
			    size++;
			  }  
	        for(k = 0; k < name.armies.size(); k++)
		     {
			    movePeices.add(name.armies.get(k));
			    size++;
			  }  
	      }
		}

		cityVector = name.getCityVector();
		peopleCityVector = new Vector();
		
		int i;
		for(i = 0; i < size; i++)
	   {
		  if(canSettle(movePeices.get(i)))    
		  { 
			 canBuild = true;
		  }  
		}

		Vector temp = name.getSettlerVector();
		settlerTemp = new Vector(0, 1);
		for(k = 0; k < temp.size(); k++)
		{ 
		  if(temp.get(k) != null)
		  {
		    Settler sett = (Settler)temp.get(k);
		    if((k > 0 && name.playerUI.lb.getSettlerCount(sett.getX(), sett.getY()) < 2) || k == 0)  
		    {
		 	   if(!name.playerUI.lb.isCityAdjacent(sett.getX(), sett.getY()))
		        settlerTemp.add(temp.get(k));
          }    
		  }    
		}
//begin options for startofturn actions
     if(!name.getAnarchy())    
	  { 
		if(name.getWonderCards(2))
		{
		  if(name.canBuildSettler())
		  {
	       temp = name.getCityVector();
			 for(int m = 0; m < temp.size(); m++)
			 {  
			      int x = name.getCityXpos(m);
			      int y = name.getCityYpos(m);
					int j=0;
					for(k = x-1; k <= x+1; k++, j++)
					{  
					  i = y-1;
					  if((name.getCityBuildingName(m, j) != null && name.getCityBuildingName(m, j).equals("hanginggardens"))
					      && (name.playerUI.lb.getOwner(k, i) == name))
					  {
						  gardenSettler = new JButton("Build Settler in garden");
						  gardenSettler.setBounds(05, 10+(30*options), 180, 20);
				        gardenSettler.addActionListener(this);
						  format.add(gardenSettler);
						  options++;
					  }
					}
				   for(k = x-1; k <= x+1; k++, j++)
					{  
					  i = y+1;
					  if((name.getCityBuildingName(m, j) != null && name.getCityBuildingName(m, j).equals("hanginggardens"))
					      && (name.playerUI.lb.getOwner(k, i) == name))
					  {
						  gardenSettler = new JButton("Build Settler in garden");
						  gardenSettler.setBounds(05, 10+(30*options), 180, 20);
				        gardenSettler.addActionListener(this);
						  format.add(gardenSettler);
						  options++;
					  }
			 		}
					  if((name.getCityBuildingName(m, 6) != null && name.getCityBuildingName(m, 6).equals("hanginggardens"))
					      && (name.playerUI.lb.getOwner(x-1, y) == name))
					  {
						  gardenSettler = new JButton("Build Settler in garden");
						  gardenSettler.setBounds(05, 10+(30*options), 185, 20);
				        gardenSettler.addActionListener(this);
						  format.add(gardenSettler);
						  options++;
					  }
					  if((name.getCityBuildingName(m, 7) != null && name.getCityBuildingName(m, 7).equals("hanginggardens"))
					      && (name.playerUI.lb.getOwner(x+1, y) == name))
					  {
						  gardenSettler = new JButton("Build Settler in garden");
						  gardenSettler.setBounds(05, 10+(30*options), 185, 20);
				        gardenSettler.addActionListener(this);
						  format.add(gardenSettler);
						  options++;
					  }
		       }
          }
		  
		  if(name.canBuildArmy())
		  {
	       temp = name.getCityVector();
			 for(int m = 0; m < temp.size(); m++)
			 {  
			      int x = name.getCityXpos(m);
			      int y = name.getCityYpos(m);
					int j=0;
					for(k = x-1; k <= x+1; k++, j++)
					{  
					  i = y-1;
					  if((name.getCityBuildingName(m, j) != null && name.getCityBuildingName(m, j).equals("hanginggardens"))
					      && (name.playerUI.lb.getOwner(k, i) == name))
					  {
						  gardenArmy = new JButton("Build Army in garden");
						  gardenArmy.setBounds(05, 10+(30*options), 180, 20);
				        gardenArmy.addActionListener(this);
						  format.add(gardenArmy);
						  options++;
					  }
					}
				   for(k = x-1; k <= x+1; k++, j++)
					{  
					  i = y+1;
					  if((name.getCityBuildingName(m, j) != null && name.getCityBuildingName(m, j).equals("hanginggardens"))
					      && (name.playerUI.lb.getOwner(k, i) == name))
					  {
						  gardenArmy = new JButton("Build Army in garden");
						  gardenArmy.setBounds(05, 10+(30*options), 180, 20);
				        gardenArmy.addActionListener(this);
						  format.add(gardenArmy);
						  options++;
					  }
			 		}
					  if((name.getCityBuildingName(m, 6) != null && name.getCityBuildingName(m, 6).equals("hanginggardens"))
					      && (name.playerUI.lb.getOwner(x-1, y) == name))
					  {
						  gardenArmy = new JButton("Build Army in garden");
						  gardenArmy.setBounds(05, 10+(30*options), 180, 20);
				        gardenArmy.addActionListener(this);
						  format.add(gardenArmy);
						  options++;
					  }
					  if((name.getCityBuildingName(m, 7) != null && name.getCityBuildingName(m, 7).equals("hanginggardens"))
					      && (name.playerUI.lb.getOwner(x+1, y) == name))
					  {
						  gardenArmy = new JButton("Build Army in garden");
						  gardenArmy.setBounds(05, 10+(30*options), 180, 20);
				        gardenArmy.addActionListener(this);
						  format.add(gardenArmy);
						  options++;
					  }
		       }
		   }
		}    

      if((  (name.getTechVector().get(1) != null && name.getCurrentGov() != 1)
		   || (name.getTechVector().get(13) != null && name.getCurrentGov() != 2)
			|| (name.getTechVector().get(10) != null && name.getCurrentGov() != 4)
			|| (name.getTechVector().get(17) != null && name.getCurrentGov() != 3)
		   || (name.getTechVector().get(22) != null && name.getCurrentGov() != 5)
			|| (name.getTechVector().get(28) != null && name.getCurrentGov() != 6))
		   || (name.getCurrentGov() != 7)  
			&& !name.hasfreeGov())
	   {
		 anarchy = new JButton("Enter Anarchy");
		 anarchy.setBounds(05, 10+(30*options), 180, 20);
       anarchy.addActionListener(this);
		 format.add(anarchy);
       options++; 
		}

		if(name.hasfreeGov())
		{
        int g = name.getfreeGov();
        String gov;
		  switch(g)
		  {
		    case 1:
            gov = "republic"; 
			   break;
		    case 2:
            gov = "democracy"; 
			   break;
		    case 3:
            gov = "monarchy"; 
			   break;
		    case 4:
            gov = "feudalism"; 
			   break;
		    case 5:
            gov = "communism"; 
			   break;
		    case 6:
            gov = "fundamentalism"; 
			   break;
		    default:
            gov = "ERROR"; 
			   break;
		  }
        govChange = new JButton("Change to "+gov);
		  govChange.setBounds(00, 10+(30*options), 198, 20);
        govChange.addActionListener(this);
		  format.add(govChange);
		  options++;
		}    
		if(canBuild)
		{
		  buttonBuild = new JButton("Establish new city");
		  buttonBuild.setBounds(05, 10+(30*options), 180, 20);
        buttonBuild.addActionListener(this);
		  format.add(buttonBuild);
		  options++;
		}
      if(settlerTemp.size() > 0)
		{
		  buttonLink = new JButton("Link Settler to city");
		  buttonLink.setBounds(05, 10+(30*options), 180, 20);
        buttonLink.addActionListener(this);
		  format.add(buttonLink);
		  options++;
		}
		if(name.getGreatPeople().size() > 0)
		{
		  buttonPeople = new JButton("Place great people");
		  buttonPeople.setBounds(05, 10+(30*options), 180, 20);
        buttonPeople.addActionListener(this);
		  format.add(buttonPeople);
		  options++;
		}
      if(options == 0)
		{
		   JLabel lb_1 = new JLabel("You have no");  
	      lb_1.setBounds(30, 10, 170, 15);
	
		   JLabel lb_2 = new JLabel("actions to perform!");  
	      lb_2.setBounds(45, 25, 160, 15);
	
	      format.removeAll();
		   format.add(lb_1);//, new Integer(0));  
			format.add(lb_2);//, new Integer(0));  
      }
	  }	
	   else
	   {
        options = 0;
		  name.resetAnarchy();

	     if(name.getTechVector().get(1) != null)
        {    
			  republic = new JButton("Change to republic");
			  republic.setBounds(05, 10+(30*options), 180, 20);
	        republic.addActionListener(this);
			  format.add(republic);
			  options++;
        }
	     if(name.getTechVector().get(13) != null)
        {    
			  democracy = new JButton("Change to democracy");
			  democracy.setBounds(05, 10+(30*options), 180, 20);
	        democracy.addActionListener(this);
			  format.add(democracy);
			  options++;
        }
	     if(name.getTechVector().get(10) != null)
        {    
			  feud = new JButton("Change to feudalism");
			  feud.setBounds(05, 10+(30*options), 180, 20);
	        feud.addActionListener(this);
			  format.add(feud);
			  options++;
        }
	     if(name.getTechVector().get(28) != null)
        {    
			  fund = new JButton("Change to fundamentalism");
			  fund.setBounds(05, 10+(30*options), 180, 20);
	        fund.addActionListener(this);
			  format.add(fund);
			  options++;
        }
	     if(name.getTechVector().get(17) != null)
        {    
			  monarchy = new JButton("Change to monarchy");
			  monarchy.setBounds(05, 10+(30*options), 180, 20);
	        monarchy.addActionListener(this);
			  format.add(monarchy);
			  options++;
        }
	     if(name.getTechVector().get(22) != null)
        {    
			  communism = new JButton("Change to communism");
			  communism.setBounds(05, 10+(30*options), 180, 20);
	        communism.addActionListener(this);
			  format.add(communism);
			  options++;
        }
		  despot = new JButton("Change to despotism");
		  despot.setBounds(05, 10+(30*options), 180, 20);
        despot.addActionListener(this);
		  format.add(despot);
		  options++;
	  }

		GridLayout grid = new GridLayout(1, 1, 0, 1);
      startPanel = new JPanel(grid);
		startPanel.setBackground(name.getPlayerColor());
		startPanel.setPreferredSize(new Dimension(190, 30*options));
      startPanel.add(format);
 
		startScrollPane = new JScrollPane(startPanel, 20, 31);
      startScrollPane.setPreferredSize(new Dimension(190, 100));

	   internal.add(startScrollPane, "Center");
		internal.add(done, "South");
		done.addActionListener(this);    
		frame.removeAll();
		frame.add(internal);
		try
	   {internal.setMaximum(true);}
	   catch(PropertyVetoException ex)
	   {}
	   frame.validate();
	 }
  }

  private void paintThisGUI()
  {
		canBuild = false;   
	   repositionActive = false;
      format.removeAll();  

		int k=0, i, j=0, p=0;
      if(name.getCurrentGov() != 1)    
      {
	      movePeices = new Vector(name.settlers.size());
	      size = movePeices.size();
			
			if(!name.settlers.isEmpty())
		   {
	        for(; k < name.settlers.size(); k++)
		     {
			    movePeices.add(name.settlers.get(k));
			    size++;
			  }  
	      }
      }
      else
		{
	      movePeices = new Vector(name.settlers.size() + name.armies.size());
	      size = movePeices.size();
			
			if(!name.settlers.isEmpty() || !name.armies.isEmpty())
		   {
	        for(; k < name.settlers.size(); k++)
		     {
			    movePeices.add(name.settlers.get(k));
			    size++;
			  }  
	        for(k = 0; k < name.armies.size(); k++)
		     {
			    movePeices.add(name.armies.get(k));
			    size++;
			  }  
	      }
		}

		for(i = 0; i < size; i++)
	   {
		  if(canSettle(movePeices.get(i)))    
		  { 
			 canBuild = true;
		  }  
		}

      options = 0;
      if(name.getWonderCards(2) && !gardenUsed)
		{
		  if(name.canBuildSettler())
		  {
	       Vector temp = name.getCityVector();
			 for(int m = 0; m < temp.size(); m++)
			 {  
			      int x = name.getCityXpos(m);
			      int y = name.getCityYpos(m);
					j=0;
					for(k = x-1; k <= x+1; k++, j++)
					{  
					  i = y-1;
					  if((name.getCityBuildingName(m, j) != null && name.getCityBuildingName(m, j).equals("hanginggardens"))
					      && (name.playerUI.lb.getOwner(k, i) == name))
					  {
						  gardenSettler = new JButton("Build free Settler in garden");
						  gardenSettler.setBounds(05, 10+(30*options), 180, 20);
				        gardenSettler.addActionListener(this);
						  format.add(gardenSettler);
						  options++;
					  }
					}
				   for(k = x-1; k <= x+1; k++, j++)
					{  
					  i = y+1;
					  if((name.getCityBuildingName(m, j) != null && name.getCityBuildingName(m, j).equals("hanginggardens"))
					      && (name.playerUI.lb.getOwner(k, i) == name))
					  {
						  gardenSettler = new JButton("Build free Settler in garden");
						  gardenSettler.setBounds(05, 10+(30*options), 180, 20);
				        gardenSettler.addActionListener(this);
						  format.add(gardenSettler);
						  options++;
					  }
			 		}
					  if((name.getCityBuildingName(m, 6) != null && name.getCityBuildingName(m, 6).equals("hanginggardens"))
					      && (name.playerUI.lb.getOwner(x-1, y) == name))
					  {
						  gardenSettler = new JButton("Build free Settler in garden");
						  gardenSettler.setBounds(05, 10+(30*options), 180, 20);
				        gardenSettler.addActionListener(this);
						  format.add(gardenSettler);
						  options++;
					  }
					  if((name.getCityBuildingName(m, 7) != null && name.getCityBuildingName(m, 7).equals("hanginggardens"))
					      && (name.playerUI.lb.getOwner(x+1, y) == name))
					  {
						  gardenSettler = new JButton("Build free Settler in garden");
						  gardenSettler.setBounds(05, 10+(30*options), 180, 20);
				        gardenSettler.addActionListener(this);
						  format.add(gardenSettler);
						  options++;
					  }
		       }
          }
		  
		  if(name.canBuildArmy())
		  {
	       Vector temp = name.getCityVector();
			 for(int m = 0; m < temp.size(); m++)
			 {  
			      int x = name.getCityXpos(m);
			      int y = name.getCityYpos(m);
					j=0;
					for(k = x-1; k <= x+1; k++, j++)
					{  
					  i = y-1;
					  if((name.getCityBuildingName(m, j) != null && name.getCityBuildingName(m, j).equals("hanginggardens"))
					      && (name.playerUI.lb.getOwner(k, i) == name))
					  {
						  gardenArmy = new JButton("Build free Army in garden");
						  gardenArmy.setBounds(05, 10+(30*options), 180, 20);
				        gardenArmy.addActionListener(this);
						  format.add(gardenArmy);
						  options++;
					  }
					}
				   for(k = x-1; k <= x+1; k++, j++)
					{  
					  i = y+1;
					  if((name.getCityBuildingName(m, j) != null && name.getCityBuildingName(m, j).equals("hanginggardens"))
					      && (name.playerUI.lb.getOwner(k, i) == name))
					  {
						  gardenArmy = new JButton("Build free Army in garden");
						  gardenArmy.setBounds(05, 10+(30*options), 180, 20);
				        gardenArmy.addActionListener(this);
						  format.add(gardenArmy);
						  options++;
					  }
			 		}
					  if((name.getCityBuildingName(m, 6) != null && name.getCityBuildingName(m, 6).equals("hanginggardens"))
					      && (name.playerUI.lb.getOwner(x-1, y) == name))
					  {
						  gardenArmy = new JButton("Build free Army in garden");
						  gardenArmy.setBounds(05, 10+(30*options), 180, 20);
				        gardenArmy.addActionListener(this);
						  format.add(gardenArmy);
						  options++;
					  }
					  if((name.getCityBuildingName(m, 7) != null && name.getCityBuildingName(m, 7).equals("hanginggardens"))
					      && (name.playerUI.lb.getOwner(x+1, y) == name))
					  {
						  gardenArmy = new JButton("Build free Army in garden");
						  gardenArmy.setBounds(05, 10+(30*options), 180, 20);
				        gardenArmy.addActionListener(this);
						  format.add(gardenArmy);
						  options++;
					  }
		       }
		   }
		}	
      if((  (name.getTechVector().get(1) != null && name.getCurrentGov() != 1)
		   || (name.getTechVector().get(13) != null && name.getCurrentGov() != 2)
			|| (name.getTechVector().get(10) != null && name.getCurrentGov() != 4)
			|| (name.getTechVector().get(17) != null && name.getCurrentGov() != 3)
		   || (name.getTechVector().get(22) != null && name.getCurrentGov() != 5)
			|| (name.getTechVector().get(28) != null && name.getCurrentGov() != 6))
		   && (!name.hasfreeGov() && !name.getAnarchy()))
		{	
		  anarchy = new JButton("Enter Anarchy");
		  anarchy.setBounds(05, 10+(30*options), 180, 20);
        anarchy.addActionListener(this);
		  format.add(anarchy);
        options++; 
		}

		if(name.hasfreeGov())
		{
        int g = name.getfreeGov();
        String gov;
		  switch(g)
		  {
		    case 1:
            gov = "republic"; 
			   break;
		    case 2:
            gov = "democracy"; 
			   break;
		    case 3:
            gov = "monarchy"; 
			   break;
		    case 4:
            gov = "feudalism"; 
			   break;
		    case 5:
            gov = "communism"; 
			   break;
		    case 6:
            gov = "fundamentalism"; 
			   break;
		    default:
            gov = "ERROR"; 
			   break;
		  }
        govChange = new JButton("Change to "+gov);
		  govChange.setBounds(05, 10+(30*options), 180, 20);
        govChange.addActionListener(this);
		  format.add(govChange);
		  options++;
		}    
		if(canBuild)
		{
		  buttonBuild = new JButton("Establish new city");
		  buttonBuild.setBounds(05, 10+(30*options), 180, 20);
        buttonBuild.addActionListener(this);
		  format.add(buttonBuild);
		  options++;
		}

      if(settlerTemp.size() > 0)
		{
		  Vector temp = name.getSettlerVector();
		  settlerTemp = new Vector(0, 1);
		  for(k = 0; k < temp.size(); k++)
		  { 
			 if(temp.get(k) != null)
			 {
			   Settler sett = (Settler)temp.get(k);
			   if((k > 0 && name.playerUI.lb.getSettlerCount(sett.getX(), sett.getY()) < 2) || k == 0)  
			   {
				  if(!name.playerUI.lb.isCityAdjacent(sett.getX(), sett.getY()))
			       settlerTemp.add(temp.get(k));
	         }    
			 }    
		  }
        if(settlerTemp.size() > 0)
		  {
			  buttonLink = new JButton("Link Settler to city");
			  buttonLink.setBounds(05, 10+(30*options), 180, 20);
	        buttonLink.addActionListener(this);
			  format.add(buttonLink);
			  options++;
		  }
		}

		if(name.getGreatPeople().size() > 0)
		{
		  buttonPeople = new JButton("Place great people");
		  buttonPeople.setBounds(05, 10+(30*options), 180, 20);
        buttonPeople.addActionListener(this);
		  format.add(buttonPeople);
		  options++;
		}
      if(options == 0)
		{
		   JLabel lb_1 = new JLabel("You have no further");  
	      lb_1.setBounds(30, 10, 170, 15);
	
		   JLabel lb_2 = new JLabel("actions to perform!");  
	      lb_2.setBounds(45, 25, 160, 15);
	
	      format.removeAll();
		   format.add(lb_1);//, new Integer(0));  
			format.add(lb_2);//, new Integer(0));  
      }
		done.addActionListener(this);    
		format.repaint(); 
		startPanel.setPreferredSize(new Dimension(190, 30*options));
      startScrollPane.revalidate();
  }
  
  public boolean canSettle(Object obj)
  {
     Settler s = null;
	  Army a = null;
     int x=0, y=0;
	  if(obj.getClass().getName() == "Settler")
	  {
		 s = (Settler)obj;
	    x = s.getX();
		 y = s.getY();
	  }
	  else if(obj.getClass().getName() == "Army")
	  {
		 a = (Army)obj;
	    x = a.getX();
		 y = a.getY();
	  }
	  
	  if(name.numCities() == 2 && !name.getHasIrrigation())
	  {
		 return false;
	  }
	  else if(x-1 < 0 || x+1 > 8 || y-1 < 0 || y+1 > 16)
	  {
	    return false;
	  }
	  else if(!name.playerUI.lb.canBuildCity(x, y, name) || name.numCities() == 3)
	  {
	    return false;
	  }
     return true;
  }

  public void drawCity(Object obj)
  {
     Settler s = null;
	  Army a = null;
	  int x=0, y=0;
	  if(obj.getClass().getName() == "Settler")
	  {
		 s = (Settler)obj;
		 x = s.getX();
		 y = s.getY();
	  }
	  else if(obj.getClass().getName() == "Army")
	  {
		 a = (Army)obj;
		 x = a.getX();
		 y = a.getY();
	  }

     name.addCity(x, y, false);		
     Player[] tempArray = name.getPlayerUI().getPlayers();
	  
     for(int k = 0; k < 2; k++)//number of players
	  {
		  tempArray[k].playerUI.lb.addPeice(2, x, y);
	
	     GameImageIcon iconCity = getImage("data/city"+name.getColor()+"6.png");
	     cityLabel = new JLabel(iconCity);
	     cityLabel.addMouseListener(this);     
				 
	     if(name == tempArray[k])  
		  {
			  if(s != null)
		     {
			    tempArray[k].playerUI.lb.getHolder(x, y).remove(s.getLabel());    
	          tempArray[k].playerUI.lb.subSettlerCount(x, y);
			  }
			  else if(a != null)
		     {
			    tempArray[k].playerUI.lb.getHolder(x, y).remove(a.getLabel());    
	          tempArray[k].playerUI.lb.subArmyCount(x, y);
           }    
		  }
		  else
		  {
            if(s != null)
				{
	             JLabel l = new JLabel();
					 for(int i = 0; !s.getLabelName().equals(l.getName());)
	             {        
					   l = (JLabel)tempArray[k].unitSettlerLabels.get(i);				   
						if(s.getLabelName().equals(l.getName()))
						{
	System.out.println("Settler COMPARED NAME "+l.getName() +" "+s.getLabelName());  
				         tempArray[k].playerUI.lb.getHolder(x, y).remove(tempArray[k].playerUI.lb.getHolder(x, y).getIndexOf((JLabel)tempArray[k].unitSettlerLabels.remove(i)));
	                  tempArray[k].playerUI.lb.subSettlerCount(x, y);
	               }
						else
						  i++;  
	             }
            }
            else
            {
	             JLabel l = new JLabel();
					 for(int i = 0; !a.getLabelName().equals(l.getName());)
	             {        
					   l = (JLabel)tempArray[k].unitArmyLabels.get(i);				   
						if(a.getLabelName().equals(l.getName()))
						{
	System.out.println("ARMY COMPARED NAME "+l.getName() +" "+a.getLabelName());  
	
						  tempArray[k].playerUI.lb.getHolder(x, y).remove(tempArray[k].playerUI.lb.getHolder(x, y).getIndexOf((JLabel)tempArray[k].unitArmyLabels.remove(i)));
				 	     tempArray[k].playerUI.lb.subArmyCount(x, y);
						}
				      else 
						  i++;  
					 }
            }				       
			}

		  cityLabel.setBounds(0, 0, iconCity.getIconWidth(),
	                               iconCity.getIconHeight()); 
	     tempArray[k].playerUI.lb.getHolder(x, y).setPreferredSize(new Dimension(iconCity.getIconWidth(),
	                                      iconCity.getIconHeight()));
	     tempArray[k].playerUI.lb.getHolder(x, y).add(cityLabel, new Integer(1));		
     }
	  
     if(s != null)
	  {
  	    movePeices.remove(s);
	    name.removeSettler(s);      
       settlerTemp.remove(s);
     }  
	  else if(a != null)
	  {
  	    movePeices.remove(a);
	    name.removeArmy(a);      
     }
     size--;
 
	  format.remove(nmCityPane[targetPeice]);
     format.repaint();

	  if((name.playerUI.lb.getSettlerCount(x, y) + name.playerUI.lb.getArmyCount(x, y)) > 0)
	  {
	    cityXpos = x;//s.getX();
	    cityYpos = y;//s.getY();
	    setUpReposition(cityXpos, cityYpos);  
	  }	 
     else
	    paintThisGUI();
  }
  
  private void setUpReposition(int x, int y)
  {
		  
		Vector Settlers = name.getSettlerVector();
		Vector armies = name.getArmyVector();
	   tempSettlers = new Vector(0, 1);
		temparmies = new Vector(0, 1);
		
		for(int k = 0; k < Settlers.size(); k++)
		{
        Settler temp = (Settler)Settlers.get(k);    
		  if(temp.getX() == x && temp.getY() == y)
		  {
		    if(temp.getGroup() > 0)
			 {
			   name.disbandGroup(temp.getGroup());
			 }
		    tempSettlers.add(temp);
        }
	   }
		for(int k = 0; k < armies.size(); k++)
		{
        Army temp = (Army)armies.get(k);    
		  if(temp.getX() == x && temp.getY() == y)
		  {
		    if(temp.getGroup() > 0)
			 {
			   name.disbandGroup(temp.getGroup());
			 }
		    temparmies.add(temp);
	     }  
		}
      if(tempSettlers.size() > 0)
		  repositionUnit(x, y, tempSettlers.remove(0));   
      else
		  repositionUnit(x, y, temparmies.remove(0));  
  }
  
  private void repositionUnit(int x, int y, Object o)
  {
      currentReposition = o; 
    	legalSpace = new Vector(0, 1); 
      Vector icons = new Vector(0, 1);
		int k, i;
	   boolean waterException = false; 
		for(k = x-1; k <= x+1; k++)
		{  
		  i = y-1;
		  if(name.playerUI.lb.getTerrain(k, i).equals("Water") && !name.getWaterLand())
          waterException = true;
		  if((name.playerUI.lb.getSettlerCount(k, i) + name.playerUI.lb.getArmyCount(k, i)) < name.getStacklimit() && !waterException)
		  {
		    legalSpace.add(name.playerUI.lb.getHolder(k, i));
          icons.add(name.playerUI.lb.getIcon(k, i));  
		  }
		  else
        {
		    legalSpace.add(null);		 
          icons.add(null);    
		  }   
		  waterException = false;
		}
	   for(k = x-1; k <= x+1; k++)
		{  
		  i = y+1;
		  if(name.playerUI.lb.getTerrain(k, i).equals("Water") && !name.getWaterLand())
          waterException = true;
		  if((name.playerUI.lb.getSettlerCount(k, i) + name.playerUI.lb.getArmyCount(k, i)) < name.getStacklimit() && !waterException)//prod = prod + bp[k][i].getProduction();
		  {
		    legalSpace.add(name.playerUI.lb.getHolder(k, i));
          icons.add(name.playerUI.lb.getIcon(k, i));  
	     }	
		  else
        {
		    legalSpace.add(null);		 
          icons.add(null);    
		  }   
		  waterException = false;
 		}
		if(name.playerUI.lb.getTerrain(x-1, y).equals("Water") && !name.getWaterLand())
         waterException = true;
		if((name.playerUI.lb.getSettlerCount(x-1, y) + name.playerUI.lb.getArmyCount(x-1, y)) < name.getStacklimit() && !waterException)//prod = prod + bp[k][i].getProduction();
		{
		  legalSpace.add(name.playerUI.lb.getHolder(x-1, y));
        icons.add(name.playerUI.lb.getIcon(x-1, y));  
      }
		else
      {
		  legalSpace.add(null);		 
        icons.add(null);    
		}   
      waterException = false;
		if(name.playerUI.lb.getTerrain(x+1, y).equals("Water") && !name.getWaterLand())
          waterException = true;
		if((name.playerUI.lb.getSettlerCount(x+1, y) + name.playerUI.lb.getArmyCount(x+1, y)) < name.getStacklimit() && !waterException)//prod = prod + bp[k][i].getProduction();
		{
		  legalSpace.add(name.playerUI.lb.getHolder(x+1, y));
        icons.add(name.playerUI.lb.getIcon(x+1, y));  
      }
		else
      {
		  legalSpace.add(null);		 
        icons.add(null);    
		}   
	   spaceButtons = new JButton[legalSpace.size()];  
		for(k = 0; k < legalSpace.size(); k++)
		{
        if(legalSpace.get(k) != null)    
		  {
			  JLayeredPane temp = (JLayeredPane)legalSpace.get(k);
	        GameImageIcon icon = (GameImageIcon)icons.get(k);
	        spaceButtons[k] = new JButton(icon);
			  spaceButtons[k].setBounds(00, 00, icon.getIconWidth(), icon.getIconHeight());
			  temp.add(spaceButtons[k], new Integer(4));
	        temp.repaint();
			  spaceButtons[k].addActionListener(this);
		  }
		}

	   JLabel lb_1 = new JLabel("Select position on map");  
      lb_1.setBounds(30, 10, 150, 15);

	   JLabel lb_2 = new JLabel("to place unit!");  
      lb_2.setBounds(45, 25, 110, 15);

      format.removeAll();
	   format.add(lb_1);//, new Integer(0));  
		format.add(lb_2);//, new Integer(0));  
		format.repaint();
	   repositionActive = true;
	 }

    private void addSettlerResource()
    { 
		 select = new JLayeredPane();  
	 	 GridLayout grid;
	    grid = new GridLayout(2, 1, 1, 1);
		 select.setLayout(grid); 	 	 
		 select.setPreferredSize(new Dimension(160, 120));
		 select.setBounds(15, 15, 160, 120);
	
		 JLabel label = new JLabel("Select Settler to link to city");
	    label.setBounds(00, 00, 120, 15);
		 
		 select.add(label);
		 
		 nmLinkPane = new JLayeredPane();
		 nmLinkPane.setBounds(00, 00, 120, 60);
		 
		 nmLink = new JButton[settlerTemp.size()];
		 nmLinkHolder = new JLabel[settlerTemp.size()];
		 s = new Settler[settlerTemp.size()];
		 
		 for(int i = 0; i < settlerTemp.size(); i++)
		 {
			  s[i] = (Settler)settlerTemp.get(i);  
			  nmLink[i] = new JButton(s[i].getIcon());
			  nmLink[i].addActionListener(this);  
			  nmLinkHolder[i] = new JLabel(name.playerUI.lb.getIcon(s[i].getX(), s[i].getY()));  
	        nmLinkHolder[i].setBounds((60*i), 00, 55, 55);
		     nmLink[i].setBounds((60*i)+12, 12, 25, 25);  
			  nmLink[i].setBackground(new Color(0, 0, 0));
			  nmLinkPane.add(nmLinkHolder[i], new Integer(0));    
		     nmLinkPane.add(nmLink[i], new Integer(1));
		 }
		 select.add(nmLinkPane);
		 format.removeAll();
		 format.add(select);
		 format.repaint(); 
	    linkActive = true;
	 }
  
  public void repaintLinkOptions()
  {
	 JLabel label = new JLabel("Select Settler to link to city");
    label.setBounds(00, 20, 150, 15);
	 
    select.removeAll();
	 nmLinkPane.removeAll();
	 
	 select.add(label, 0);
	 for(int i = 0; i < settlerTemp.size(); i++)
	 {
	    s[i] = (Settler)settlerTemp.get(i);  
	    nmLink[i] = new JButton(s[i].getIcon());
		 nmLink[i].addActionListener(this);  
		 nmLinkHolder[i] = new JLabel(name.playerUI.lb.getIcon(s[i].getX(), s[i].getY()));  
       nmLinkHolder[i].setBounds((60*i), 00, 55, 55);
	    nmLink[i].setBounds((60*i)+12, 12, 25, 25);  
		 nmLink[i].setBackground(new Color(0, 0, 0));
		 nmLinkPane.add(nmLinkHolder[i], new Integer(0));    
	    nmLinkPane.add(nmLink[i], new Integer(1));
    }
	 select.add(nmLinkPane);
    linkActive = true;
    nmLinkPane.repaint();
    select.repaint(); 
	 format.repaint();
  }

  private void placeGreatPeople()
  {
    peopleCityVector = name.getCityVector();
    tempPeople = name.getGreatPeople();
    peopleButtons = new JButton[tempPeople.size()];
	 
	 for(int k = 0; k < tempPeople.size(); k++)
	 {
	   String type = (String)tempPeople.get(k);
      GameImageIcon icon = new GameImageIcon();
		if(type.equals("artist"))
		{
		  icon = getImage("data/artist.png");
		}
		 else if(type.equals("builder"))
		 {
		   icon = getImage("data/builder.png");
		 }
		 else if(type.equals("general"))
		 {
		   icon = getImage("data/general.png");
		 }
		 else if(type.equals("humanitarian"))
		 {
		   icon = getImage("data/human.png");
		 }
		 else if(type.equals("industrialist"))
		 {
		   icon = getImage("data/industry.png");
		 }
		 else if(type.equals("scientist"))
		 {
		   icon = getImage("data/scientist.png");
		 }
      peopleButtons[k] = new JButton(icon);
		peopleButtons[k].setBounds(110+(56*k), 180, 55, 55);
      peopleButtons[k].addActionListener(this);
		name.playerBoard.playerBrd2.add(peopleButtons[k], new Integer(4));
	 }
		name.playerBoard.playerBrd2.remove(2);
	   name.playerBoard.playerBrd2.repaint();

		JLabel label1 = new JLabel("Select great person");
		label1.setBounds(15, 20, 185, 15);

		JLabel label2 = new JLabel("from your player card.");
		label2.setBounds(25, 35, 180, 15);

		format.removeAll();
     
		format.add(label1);   
		format.add(label2);   
      format.repaint();
  
      peopleActive = true;
  }
    
  public void drawOtherPlayers(int oldx, int oldy, int newx, int newy, Object obj)
  {
    Player[] tempArray = name.getPlayerUI().getPlayers();    
    if(obj.getClass().getName().equals("Settler"))
	 {
		 for(int k = 0; k < 2; k++)//number of players
		 {
          if(name != tempArray[k])
          {
             Settler s = (Settler)obj;
             JLabel l = new JLabel();

				 for(int i = 0; !s.getLabelName().equals(l.getName());)
             {        
				   l = (JLabel)tempArray[k].unitSettlerLabels.get(i);				   
					if(s.getLabelName().equals(l.getName()))
					{
			          JLabel lbl = new JLabel(l.getIcon());      
						 tempArray[k].playerUI.lb.getHolder(oldx, oldy).remove(tempArray[k].playerUI.lb.getHolder(oldx, oldy).getIndexOf((JLabel)tempArray[k].unitSettlerLabels.remove(i)));
					    lbl.setBounds(s.getCurrentbounds());  
                   lbl.setName(s.getLabelName()); 
                   tempArray[k].unitSettlerLabels.add(lbl);
                   tempArray[k].playerUI.lb.getHolder(newx, newy).add(lbl, new Integer(5));  
  			          tempArray[k].playerUI.lb.subSettlerCount(oldx, oldy);
  			          tempArray[k].playerUI.lb.addSettlerCount(newx, newy);
			 	       tempArray[k].playerUI.lb.getHolder(newx, newy).validate();
			          tempArray[k].playerUI.lb.getHolder(oldx, oldy).repaint();
			          tempArray[k].playerUI.lb.getHolder(newx, newy).repaint();
               }
					else
					  i++;  
             }
//System.out.println("Settler "+ tempArray[k].playerUI.lb.getSettlerCount(x, y));          
          }
		 } 
	 }
	 else
	 {
        for(int k = 0; k < 2; k++)//number of players
	     {
          if(name != tempArray[k])
          {
             Army a = (Army)obj;
             JLabel l = new JLabel();
//		        if(tempArray[k].playerUI.lb.getHolder(x, y).getIndexOf((JLabel)tempArray[k].unitArmyLabels.remove(0)) >= 0)
				 for(int i = 0; !a.getLabelName().equals(l.getName());)
             {        
				   l = (JLabel)tempArray[k].unitArmyLabels.get(i);				   
					if(a.getLabelName().equals(l.getName()))
					{
			          JLabel lbl = new JLabel(l.getIcon());      
					    tempArray[k].playerUI.lb.getHolder(oldx, oldy).remove(tempArray[k].playerUI.lb.getHolder(oldx, oldy).getIndexOf((JLabel)tempArray[k].unitArmyLabels.remove(i)));
					    lbl.setBounds(a.getCurrentbounds());  
                   lbl.setName(a.getLabelName()); 
                   tempArray[k].unitArmyLabels.add(lbl);
                   tempArray[k].playerUI.lb.getHolder(newx, newy).add(lbl, new Integer(5));  
  			          tempArray[k].playerUI.lb.subArmyCount(oldx, oldy);
  			          tempArray[k].playerUI.lb.addArmyCount(newx, newy);
			 	       tempArray[k].playerUI.lb.getHolder(newx, newy).validate();
			          tempArray[k].playerUI.lb.getHolder(newx, newy).repaint();
					}
			      else 
					  i++;  
				 }
			 }
		  }
	 }  
  }
  
  public void actionPerformed(ActionEvent e) 
  {
    if(buildActive)  
	 {
	   for(sourcePeice = 0; sourcePeice < size; sourcePeice++)
	   {  
		   if(nmCity[sourcePeice] != null && e.getSource() == nmCity[sourcePeice])
         {
		     targetPeice = sourcePeice;  
		     drawCity(movePeices.get(sourcePeice));
           buildActive = false;   	
			}
      }   
	 }
    
	 if(linkActive)
	 {    
		for(int k = 0; k < settlerTemp.size(); k++)  
	   {
        if(nmLink[k] != null && e.getSource() == nmLink[k])    
        {
//System.out.println("city button craetion");
			   activeSettler = s[k];
            activeChoice = k;
				cityVector = name.getCityVector();
				cityButtons = new JButton[cityVector.size()];
				for(int i = 0; i < cityVector.size(); i++)
				{
				  cityButtons[i] = new JButton(name.getCityIcon(i));
				  cityButtons[i].setBounds(00, 00, name.getCityIcon(i).getIconWidth(), name.getCityIcon(i).getIconHeight());
				  int x = name.getCityXpos(i);
				  int y = name.getCityYpos(i);
				  name.playerUI.lb.getHolder(x, y).add(cityButtons[i], new Integer(50));
		        name.playerUI.lb.getHolder(x, y).repaint();
		        cityButtons[i].addActionListener(this);
				}

		      JLabel label = new JLabel("Select city to link Settler to");
		      label.setBounds(00, 20, 150, 15);
			 
				nmLink[activeChoice].removeActionListener(this);    

		      GameImageIcon icon = (GameImageIcon)nmLink[activeChoice].getIcon();
            JLabel l = new JLabel(icon);
				l.setBounds((60*activeChoice)+12, 12, 25, 25);

				nmLinkPane.removeAll();        
				nmLinkPane.add(nmLinkHolder[activeChoice], new Integer(0));    
			   nmLinkPane.add(l, new Integer(1));
				    
		      select.removeAll();
				select.add(label);
				select.add(nmLinkPane);
		
		      nmLinkPane.repaint();
				select.repaint();

				format.repaint();
				
				linkActive = false;    
		      linkToCity = true;
		  }
	   }
	 }

	 if(linkToCity)
	 {
	   for(int k = 0; k < cityVector.size(); k++)  
	   {
		  if(cityButtons[k] != null && e.getSource() == cityButtons[k])    
        {
          name.setCitySettlerLink(k, activeSettler);    
//System.out.println("city chossen "+k);
          linkToCity = false;
		    int x = name.getCityXpos(k);
			 int y = name.getCityYpos(k);
          name.playerUI.lb.getHolder(x, y).remove(name.playerUI.lb.getHolder(x, y).getIndexOf(cityButtons[k]));
          select.remove(select.getIndexOf(nmLinkPane));        
			 settlerTemp.remove(activeChoice);
		    for(k = 0; k < cityVector.size(); k++)
			 {
			   if(cityButtons[k] != null)
            {
		        x = name.getCityXpos(k);
			     y = name.getCityYpos(k);
				  if(name.playerUI.lb.getHolder(x, y).getIndexOf(cityButtons[k]) >= 0)
				    name.playerUI.lb.getHolder(x, y).remove(name.playerUI.lb.getHolder(x, y).getIndexOf(cityButtons[k]));
			   }  
			 }
			 if(settlerTemp.size() > 0)
			   repaintLinkOptions();
			 else
			 {  
				paintThisGUI();
		    } 
		  }
	   }
    }  

	  if(repositionActive)
	  {    
		for(int k = 0; k < legalSpace.size(); k++)  
	   {
        if(spaceButtons[k] != null && e.getSource() == spaceButtons[k])    
        {
			 if(currentReposition.getClass().getName() == "Settler")
			 {
			   Settler s = (Settler)currentReposition;
            name.playerUI.lb.subSettlerCount(cityXpos, cityYpos);
	         name.playerUI.lb.getHolder(cityXpos, cityYpos).remove(s.getLabel());    
	         name.playerUI.lb.getHolder(cityXpos, cityYpos).repaint();
				switch(k)
			   {
				   case 0:
		           name.playerUI.lb.addPeice(0, cityXpos-1, cityYpos-1);
				     s.setBounds(name.playerUI.lb.getSettlerCount(cityXpos-1, cityYpos-1) + name.playerUI.lb.getArmyCount(cityXpos-1, cityYpos-1));
		           s.setPosition(cityXpos-1, cityYpos-1);      
					  drawOtherPlayers(cityXpos, cityYpos, cityXpos-1, cityYpos-1, s);
					  break;
				   case 1:
		           name.playerUI.lb.addPeice(0, cityXpos, cityYpos-1);
				     s.setBounds(name.playerUI.lb.getSettlerCount(cityXpos, cityYpos-1) + name.playerUI.lb.getArmyCount(cityXpos, cityYpos-1));
		           s.setPosition(cityXpos, cityYpos-1);      
					  drawOtherPlayers(cityXpos, cityYpos, cityXpos, cityYpos-1, s);
					  break;
				   case 2:
		           name.playerUI.lb.addPeice(0, cityXpos+1, cityYpos-1);
				     s.setBounds(name.playerUI.lb.getSettlerCount(cityXpos+1, cityYpos-1) + name.playerUI.lb.getArmyCount(cityXpos+1, cityYpos-1));
		           s.setPosition(cityXpos+1, cityYpos-1);      
					  drawOtherPlayers(cityXpos, cityYpos, cityXpos+1, cityYpos-1, s);
					  break;
				   case 3:
		           name.playerUI.lb.addPeice(0, cityXpos-1, cityYpos+1);
				     s.setBounds(name.playerUI.lb.getSettlerCount(cityXpos-1, cityYpos+1) + name.playerUI.lb.getArmyCount(cityXpos-1, cityYpos+1));
		           s.setPosition(cityXpos-1, cityYpos+1);      
					  drawOtherPlayers(cityXpos, cityYpos, cityXpos-1, cityYpos+1, s);
					  break;
				   case 4:
		           name.playerUI.lb.addPeice(0, cityXpos, cityYpos+1);
				     s.setBounds(name.playerUI.lb.getSettlerCount(cityXpos, cityYpos+1) + name.playerUI.lb.getArmyCount(cityXpos, cityYpos+1));
		           s.setPosition(cityXpos, cityYpos+1);      
					  drawOtherPlayers(cityXpos, cityYpos, cityXpos, cityYpos+1, s);
					  break;
				   case 5:
		           name.playerUI.lb.addPeice(0, cityXpos+1, cityYpos+1);
				     s.setBounds(name.playerUI.lb.getSettlerCount(cityXpos+1, cityYpos+1) + name.playerUI.lb.getArmyCount(cityXpos+1, cityYpos+1));
		           s.setPosition(cityXpos+1, cityYpos+1);      
					  drawOtherPlayers(cityXpos, cityYpos, cityXpos+1, cityYpos+1, s);
					  break;
				   case 6:
		           name.playerUI.lb.addPeice(0, cityXpos-1, cityYpos);
				     s.setBounds(name.playerUI.lb.getSettlerCount(cityXpos-1, cityYpos) + name.playerUI.lb.getArmyCount(cityXpos-1, cityYpos));
		           s.setPosition(cityXpos-1, cityYpos);      
					  drawOtherPlayers(cityXpos, cityYpos, cityXpos-1, cityYpos, s);
					  break;
				   case 7:
					  name.playerUI.lb.addPeice(0, cityXpos+1, cityYpos);
				     s.setBounds(name.playerUI.lb.getSettlerCount(cityXpos+1, cityYpos) + name.playerUI.lb.getArmyCount(cityXpos+1, cityYpos));
		           s.setPosition(cityXpos+1, cityYpos);      
					  drawOtherPlayers(cityXpos, cityYpos, cityXpos+1, cityYpos, s);
					  break;
				 }
	          name.playerUI.lb.getHolder(s.getX(), s.getY()).add(s.getLabel(), new Integer(5));
             name.playerUI.lb.getHolder(s.getX(), s.getY()).repaint();
             settlerTemp.remove(s);      
			 }
          else
			 {
			   Army a = (Army)currentReposition;
            name.playerUI.lb.subArmyCount(cityXpos, cityYpos);
	         name.playerUI.lb.getHolder(cityXpos, cityYpos).remove(a.getLabel());    
	         name.playerUI.lb.getHolder(cityXpos, cityYpos).repaint();
            switch(k)
			   {
				   case 0:
		           name.playerUI.lb.addPeice(1, cityXpos-1, cityYpos-1);
				     a.setBounds(name.playerUI.lb.getSettlerCount(cityXpos-1, cityYpos-1) , name.playerUI.lb.getArmyCount(cityXpos-1, cityYpos-1));
		           a.setPosition(cityXpos-1, cityYpos-1);      
					  drawOtherPlayers(cityXpos, cityYpos, cityXpos-1, cityYpos-1, a);
					  break;
				   case 1:
		           name.playerUI.lb.addPeice(1, cityXpos, cityYpos-1);
				     a.setBounds(name.playerUI.lb.getSettlerCount(cityXpos, cityYpos-1) , name.playerUI.lb.getArmyCount(cityXpos, cityYpos-1));
		           a.setPosition(cityXpos, cityYpos-1);      
					  drawOtherPlayers(cityXpos, cityYpos, cityXpos, cityYpos-1, a);
					  break;
				   case 2:
		           lb.addPeice(1, cityXpos+1, cityYpos-1);
				     a.setBounds(name.playerUI.lb.getSettlerCount(cityXpos+1, cityYpos-1) , name.playerUI.lb.getArmyCount(cityXpos+1, cityYpos-1));
		           a.setPosition(cityXpos+1, cityYpos-1);      
					  drawOtherPlayers(cityXpos, cityYpos, cityXpos+1, cityYpos-1, a);
					  break;
				   case 3:
		           name.playerUI.lb.addPeice(1, cityXpos-1, cityYpos+1);
				     a.setBounds(name.playerUI.lb.getSettlerCount(cityXpos-1, cityYpos+1) , name.playerUI.lb.getArmyCount(cityXpos-1, cityYpos+1));
		           a.setPosition(cityXpos-1, cityYpos+1);      
					  drawOtherPlayers(cityXpos, cityYpos, cityXpos-1, cityYpos+1, a);
					  break;
				   case 4:
		           name.playerUI.lb.addPeice(1, cityXpos, cityYpos+1);
				     a.setBounds(name.playerUI.lb.getSettlerCount(cityXpos, cityYpos+1) , name.playerUI.lb.getArmyCount(cityXpos, cityYpos+1));
		           a.setPosition(cityXpos, cityYpos+1);      
					  drawOtherPlayers(cityXpos, cityYpos, cityXpos, cityYpos+1, a);
					  break;
				   case 5:
		           name.playerUI.lb.addPeice(1, cityXpos+1, cityYpos+1);
				     a.setBounds(name.playerUI.lb.getSettlerCount(cityXpos+1, cityYpos+1) , name.playerUI.lb.getArmyCount(cityXpos+1, cityYpos+1));
		           a.setPosition(cityXpos+1, cityYpos+1);      
					  drawOtherPlayers(cityXpos, cityYpos, cityXpos+1, cityYpos+1, a);
					  break;
				   case 6:
		           name.playerUI.lb.addPeice(1, cityXpos-1, cityYpos);
				     a.setBounds(name.playerUI.lb.getSettlerCount(cityXpos-1, cityYpos) , name.playerUI.lb.getArmyCount(cityXpos-1, cityYpos));
		           a.setPosition(cityXpos-1, cityYpos);      
					  drawOtherPlayers(cityXpos, cityYpos, cityXpos-1, cityYpos, a);
					  break;
				   case 7:
					  name.playerUI.lb.addPeice(1, cityXpos+1, cityYpos);
				     a.setBounds(name.playerUI.lb.getSettlerCount(cityXpos+1, cityYpos) , name.playerUI.lb.getArmyCount(cityXpos+1, cityYpos));
		           a.setPosition(cityXpos+1, cityYpos);      
					  drawOtherPlayers(cityXpos, cityYpos, cityXpos+1, cityYpos, a);
					  break;
				 }
	          name.playerUI.lb.getHolder(a.getX(), a.getY()).add(a.getLabel(), new Integer(5));
             name.playerUI.lb.getHolder(a.getX(), a.getY()).repaint();
			 }
          repositionActive = false;
          JLayeredPane temp;
		    for(k = 0; k < legalSpace.size(); k++)
			 {
			   if(legalSpace.get(k) != null)
				{
				  temp = (JLayeredPane)legalSpace.get(k);
			     temp.remove(temp.getIndexOf(spaceButtons[k]));
              temp.repaint();
				}    
			 }
		    if(tempSettlers.size() > 0)
			   repositionUnit(cityXpos, cityYpos, tempSettlers.remove(0));
          else if(temparmies.size() > 0)
			   repositionUnit(cityXpos, cityYpos, temparmies.remove(0));
			 else
			 {  
			   paintThisGUI();
		    } 
		   }
       }
     }  

	  if(peopleActive)
	  {
		for(int k = 0; k < tempPeople.size(); k++)  
	   {
        if(peopleButtons[k] != null && e.getSource() == peopleButtons[k])    
        {
//System.out.println("GREAT PERSON button craetion");
			 peoplecityButtons = new JButton[peopleCityVector.size()];
          personNumber = k;
				
			 for(int i = 0; i < peopleCityVector.size(); i++)
			 {
			   peoplecityButtons[i] = new JButton(name.getCityIcon(i));
			   peoplecityButtons[i].setBounds(00, 00, name.getCityIcon(i).getIconWidth(), name.getCityIcon(i).getIconHeight());
			   int x = name.getCityXpos(i);
			   int y = name.getCityYpos(i);
			   name.playerUI.lb.getHolder(x, y).add(peoplecityButtons[i], new Integer(50));
		      name.playerUI.lb.getHolder(x, y).repaint();
		      peoplecityButtons[i].addActionListener(this);
			 }

		    JLabel label = new JLabel("Select city to place person!");
		    label.setBounds(10, 20, 160, 15);

			 format.removeAll();
			 format.add(label);   
          format.repaint();

			 peopleCityActive = true;    
		    peopleActive = false;
		  }
	    } 
     }

     if(peopleCityActive)
	  {
	    for(int m = 0; m < cityVector.size(); m++)
		 {
	      if(peoplecityButtons[m] != null && e.getSource() == peoplecityButtons[m])
			{
		     peopleCityActive = false;
           if(lb.hasBuildTerrain(name.getCityXpos(m), name.getCityYpos(m), "Any"))
 		     {
//System.out.println("GREAT PERSON city button craetion: " + m);
			    name.playerBoard.playerBrd2.remove(2);
             name.playerBoard.playerBrd2.repaint();
             cityNumber = m;
				 legalBuilding = new Vector(0, 1); 
    			 Vector icons = new Vector(0, 1);
		       int x = name.getCityXpos(cityNumber);
		       int y = name.getCityYpos(cityNumber);
		 		 int k, i;
		 		 for(k = x-1; k <= x+1; k++)
  				 {  
				   i = y-1;
				   if(!name.playerUI.lb.getTerrain(k, i).equals("Water") && !name.playerUI.lb.getHasBuild(k, i))
       		   {
		 		     legalBuilding.add(name.playerUI.lb.getHolder(k, i));
		           icons.add(name.playerUI.lb.getIcon(k, i));  
				   }
		  		   else
        			{
		    		  legalBuilding.add(null);		 
          		  icons.add(null);    
		  			}   
				}
	         for(k = x-1; k <= x+1; k++)
			   {  
		  	 	  i = y+1;
		  		  if(!name.playerUI.lb.getTerrain(k, i).equals("Water") && !name.playerUI.lb.getHasBuild(k, i))
        		  {
		    		 legalBuilding.add(name.playerUI.lb.getHolder(k, i));
          		 icons.add(name.playerUI.lb.getIcon(k, i));  
		  		  }
		  		 else
        		 {
		    		legalBuilding.add(null);		 
          		icons.add(null);    
		  		 }   
 			  }
			  if(!name.playerUI.lb.getTerrain(x-1, y).equals("Water") && !name.playerUI.lb.getHasBuild(x-1, y))
			  {
		  		 legalBuilding.add(name.playerUI.lb.getHolder(x-1, y));
        		 icons.add(name.playerUI.lb.getIcon(x-1, y));  
      	  }
			  else
      	  {
		  		 legalBuilding.add(null);		 
        		 icons.add(null);    
			  }   
			  if(!name.playerUI.lb.getTerrain(x+1, y).equals("Water") && !name.playerUI.lb.getHasBuild(x+1, y))
			  {
		  		 legalBuilding.add(name.playerUI.lb.getHolder(x+1, y));
        		 icons.add(name.playerUI.lb.getIcon(x+1, y));  
      	  }
			  else
      	  {
		  		 legalBuilding.add(null);		 
        		 icons.add(null);    
			  }   
	        buildingButtons = new JButton[legalBuilding.size()];  
		     for(k = 0; k < legalBuilding.size(); k++)
		     {
             if(legalBuilding.get(k) != null)    
		       {
			        JLayeredPane temp = (JLayeredPane)legalBuilding.get(k);
	              GameImageIcon icon = (GameImageIcon)icons.get(k);
	              buildingButtons[k] = new JButton(icon);
			        buildingButtons[k].setBounds(00, 00, icon.getIconWidth(), icon.getIconHeight());
			        temp.add(buildingButtons[k], new Integer(4));
	              temp.repaint();
			        buildingButtons[k].addActionListener(this);
			 	 }  
		    }
		   
		      for(k = 0; k < cityVector.size(); k++)
	         {
	           if(cityVector.get(k) != null)  
		        {
				    name.playerUI.lb.getHolder(name.getCityXpos(k), name.getCityYpos(k)).remove(0);
				    peoplecityButtons[k].removeActionListener(this);
	           }      
				}  
				
				JLabel lb_1 = new JLabel("Select position on map");  
            lb_1.setBounds(25, 10, 170, 15);

	         JLabel lb_2 = new JLabel("to add great person!");  
            lb_2.setBounds(35, 25, 160, 15);
             
	         format.removeAll();      
            format.add(lb_1); 
            format.add(lb_2); 
            format.repaint();

		      peopleSpaceActive = true;  
           }
			}
	    }
	  }	 
	  
	  if(peopleSpaceActive)
     {
	    for(int k = 0; k < legalBuilding.size(); k++)
		 {
	      if(buildingButtons[k] != null && e.getSource() == buildingButtons[k])
			{

			  String type = (String)tempPeople.remove(personNumber);      
           int x = name.getCityXpos(cityNumber);
           int y = name.getCityYpos(cityNumber);
			  
			  if(type.equals("artist"))//nm 14
			  {
             name.addBuildingtoCity(cityNumber, 14, k);
			  }
			  else if(type.equals("builder"))
			  {
             name.addBuildingtoCity(cityNumber, 15, k);
			  }
			  else if(type.equals("general"))
			  {
             name.addBuildingtoCity(cityNumber, 16, k);
			  }
			  else if(type.equals("humanitarian"))
			  {
             name.addBuildingtoCity(cityNumber, 17, k);
			  }
			  else if(type.equals("industrialist"))
			  {
             name.addBuildingtoCity(cityNumber, 18, k);
			  }
			  else if(type.equals("scientist"))
			  {
             name.addBuildingtoCity(cityNumber, 19, k);
			  }

	          switch(k)
				 {
				   case 0:
	              lb.addBuilding(x-1, y-1, 2, name.getCityBuildingLabel(cityNumber, 0), false, name, cityNumber);
					  break;
				   case 1:
	              lb.addBuilding(x, y-1, 2, name.getCityBuildingLabel(cityNumber, 1), false, name, cityNumber);
					  break;
				   case 2:
	              lb.addBuilding(x+1, y-1, 2, name.getCityBuildingLabel(cityNumber, 2), false, name, cityNumber);
					  break;
				   case 3:
	              lb.addBuilding(x-1, y+1, 2, name.getCityBuildingLabel(cityNumber, 3), false, name, cityNumber);
					  break;
				   case 4:
	              lb.addBuilding(x, y+1, 2, name.getCityBuildingLabel(cityNumber, 4), false, name, cityNumber);
					  break;
				   case 5:
	              lb.addBuilding(x+1, y+1, 2, name.getCityBuildingLabel(cityNumber, 5), false, name, cityNumber);
					  break;
				   case 6:
	              lb.addBuilding(x-1, y, 2, name.getCityBuildingLabel(cityNumber, 6), false, name, cityNumber);
					  break;
				   case 7:
	              lb.addBuilding(x+1, y, 2, name.getCityBuildingLabel(cityNumber, 7), false, name, cityNumber);
					  break;
				 }

           peopleSpaceActive = false;
	       
         
			JLayeredPane temp;
		   for(k = 0; k < legalBuilding.size(); k++)
			{
			  if(legalBuilding.get(k) != null)
			  {
				 temp = (JLayeredPane)legalBuilding.get(k);
			    if(temp.getIndexOf(buildingButtons[k]) >= 0)  
				 {
				   temp.remove(temp.getIndexOf(buildingButtons[k]));
               buildingButtons[k].removeActionListener(this);       
				 }   
	          temp.repaint();
			  }    
			}
         format.removeAll();
         format.repaint();
		   paintThisGUI();
		  }
		}
	 }
	 
	  if(e.getSource() == done)
     {
       name.resetfreeGov();
		 done.removeActionListener(this); 
		 internal.dispose();

		 name.getPlayerUI().getTurn().tradeTurn(name);
	  }
	
	  if(e.getSource() == anarchy)
	  {
       done.removeActionListener(this);
       anarchy.removeActionListener(this); 
	    name.enterAnarchy();
		 paintThisGUI();
	  }

	  if(e.getSource() == govChange)
	  {
       done.removeActionListener(this);
       govChange.removeActionListener(this); 
	    name.changeGov();
		 paintThisGUI();
	  }

	  if(e.getSource() == gardenArmy)
	  {
       done.removeActionListener(this);
       Vector temp = name.getCityVector();
		 for(int m = 0; m < temp.size(); m++)
		 {  
	      int x = name.getCityXpos(m);
	      int y = name.getCityYpos(m);
			int k, i, j=0;
			for(k = x-1; k <= x+1 && !gardenUsed; k++, j++)
			{  
			  i = y-1;
			  if((name.getCityBuildingName(m, j) != null && name.getCityBuildingName(m, j).equals("hanginggardens")) && ((name.playerUI.lb.getSettlerCount(k, i) + name.playerUI.lb.getArmyCount(k, i)) < name.getStacklimit()))
			  {
 			    Army s = new Army(name.getColor(), name.getMoveLimit(), Integer.toString(name.getArmyVector().size()+1));
	          name.playerUI.lb.addPeice(1, k, i);
			    s.setBounds(name.playerUI.lb.getSettlerCount(k, i), name.playerUI.lb.getArmyCount(k, i));
	          s.setPosition(k, i);      
             name.addArmy(s); 
	          name.playerUI.lb.getHolder(s.getX(), s.getY()).add(s.getLabel(), new Integer(5));
             name.playerUI.lb.getHolder(s.getX(), s.getY()).repaint();
             name.playerUI.lb.addArmyCount(s.getX(), s.getY());
	          
				 Player[] tempArray = name.getPlayerUI().getPlayers();
				 for(int n = 0; n < 2; n++)//number of players
				 {
				   if(name != tempArray[n])
					{
                 JLabel l = new JLabel(s.getIcon());
					  l.setBounds(s.getCurrentbounds());
				     l.setName(s.getLabelName());    
                 tempArray[n].playerUI.lb.addArmyCount(s.getX(), s.getY());
					  tempArray[n].playerUI.lb.getHolder(s.getX(), s.getY()).add(l, new Integer(5));
	              tempArray[n].playerUI.lb.getHolder(s.getX(), s.getY()).repaint();
					  tempArray[n].unitArmyLabels.add(l);
					}
				 }      
				 gardenUsed = true;
//System.out.println("GardenArmy: "+ lb.getArmyCount(k, i)+ "GardenSett: "+lb.getSettlerCount(k, i));
			  }
			  else if((name.getCityBuildingName(m, j) != null && name.getCityBuildingName(m, j).equals("hanginggardens")) && ((name.playerUI.lb.getSettlerCount(k, i) + name.playerUI.lb.getArmyCount(k, i)) >= name.getStacklimit()))
           {
				  JOptionPane illegal = new JOptionPane();
				  GameImageIcon im = getImage("data/"+name.getNation()+"Icon.png");  
		        illegal.showMessageDialog(name.playerUI.map, "YOU CANNOT EXCEED YOUR STACK LIMIT\nIN THE GARDENS!", "CANNOT PERFORM ACTION",
				  					 			   JOptionPane.INFORMATION_MESSAGE, im);	 	 
			  }
			}
		   for(k = x-1; k <= x+1 && !gardenUsed; k++, j++)
			{  
			  i = y+1;
			  if((name.getCityBuildingName(m, j) != null && name.getCityBuildingName(m, j).equals("hanginggardens")) && ((name.playerUI.lb.getSettlerCount(k, i) + name.playerUI.lb.getArmyCount(k, i)) < name.getStacklimit()))
			  {
 			    Army s = new Army(name.getColor(), name.getMoveLimit(), Integer.toString(name.getArmyVector().size()+1));
	          name.playerUI.lb.addPeice(1, k, i);
			    s.setBounds(name.playerUI.lb.getSettlerCount(k, i), name.playerUI.lb.getArmyCount(k, i));
	          s.setPosition(k, i);      
             name.addArmy(s); 
	          name.playerUI.lb.getHolder(s.getX(), s.getY()).add(s.getLabel(), new Integer(5));
             name.playerUI.lb.getHolder(s.getX(), s.getY()).repaint();
             name.playerUI.lb.addArmyCount(s.getX(), s.getY());
		       Player[] tempArray = name.getPlayerUI().getPlayers();    

	          for(int n = 0; n < 2; n++)//number of players
				 {
				   if(name != tempArray[n])
					{
                 JLabel l = new JLabel(s.getIcon());
					  l.setBounds(s.getCurrentbounds());
				     l.setName(s.getLabelName());    
                 tempArray[n].playerUI.lb.addArmyCount(s.getX(), s.getY());
					  tempArray[n].playerUI.lb.getHolder(s.getX(), s.getY()).add(l, new Integer(5));
	              tempArray[n].playerUI.lb.getHolder(s.getX(), s.getY()).repaint();
					  tempArray[n].unitArmyLabels.add(l);
					}
				 }      
	          gardenUsed = true;
//System.out.println("GardenArmy: "+ lb.getArmyCount(k, i)+ "GardenSett: "+lb.getSettlerCount(k, i));
			  }
			  else if((name.getCityBuildingName(m, j) != null && name.getCityBuildingName(m, j).equals("hanginggardens")) && ((name.playerUI.lb.getSettlerCount(k, i) + name.playerUI.lb.getArmyCount(k, i)) >= name.getStacklimit()))
           {
				  JOptionPane illegal = new JOptionPane();
				  GameImageIcon im = getImage("data/"+name.getNation()+"Icon.png");  
		        illegal.showMessageDialog(name.playerUI.map, "YOU CANNOT EXCEED YOUR STACK LIMIT\nIN THE GARDENS!", "CANNOT PERFORM ACTION",
				  					 			   JOptionPane.INFORMATION_MESSAGE, im);	 	 
			  }
	 		}
			  if((name.getCityBuildingName(m, 6) != null && name.getCityBuildingName(m, 6).equals("hanginggardens")) && ((name.playerUI.lb.getSettlerCount(x-1, y) + name.playerUI.lb.getArmyCount(x-1, y)) < name.getStacklimit()))
			  {
 			    Army s = new Army(name.getColor(), name.getMoveLimit(), Integer.toString(name.getArmyVector().size()+1));
	          name.playerUI.lb.addPeice(1, x-1, y);
			    s.setBounds(name.playerUI.lb.getSettlerCount(x-1, y), name.playerUI.lb.getArmyCount(x-1, y));
	          s.setPosition(x-1, y);      
             name.addArmy(s); 
	          name.playerUI.lb.getHolder(s.getX(), s.getY()).add(s.getLabel(), new Integer(5));
             name.playerUI.lb.getHolder(s.getX(), s.getY()).repaint();
             name.playerUI.lb.addArmyCount(s.getX(), s.getY());
		       Player[] tempArray = name.getPlayerUI().getPlayers();    

	          for(int n = 0; n < 2; n++)//number of players
				 {
				   if(name != tempArray[n])
					{
                 JLabel l = new JLabel(s.getIcon());
					  l.setBounds(s.getCurrentbounds());
				     l.setName(s.getLabelName());    
                 tempArray[n].playerUI.lb.addArmyCount(s.getX(), s.getY());
					  tempArray[n].playerUI.lb.getHolder(s.getX(), s.getY()).add(l, new Integer(5));
	              tempArray[n].playerUI.lb.getHolder(s.getX(), s.getY()).repaint();
					  tempArray[n].unitArmyLabels.add(l);
					}
				 }      
	          gardenUsed = true;
			  }
			  else if((name.getCityBuildingName(m, 6) != null && name.getCityBuildingName(m, 6).equals("hanginggardens")) && ((name.playerUI.lb.getSettlerCount(x-1, y) + name.playerUI.lb.getArmyCount(x-1, y)) >= name.getStacklimit()))
           {
				  JOptionPane illegal = new JOptionPane();
				  GameImageIcon im = getImage("data/"+name.getNation()+"Icon.png");  
		        illegal.showMessageDialog(name.playerUI.map, "YOU CANNOT EXCEED YOUR STACK LIMIT\nIN THE GARDENS!", "CANNOT PERFORM ACTION",
				  					 			   JOptionPane.INFORMATION_MESSAGE, im);	 	 
			  }
			  if((name.getCityBuildingName(m, 7) != null && name.getCityBuildingName(m, 7).equals("hanginggardens")) && ((name.playerUI.lb.getSettlerCount(x+1, y) + name.playerUI.lb.getArmyCount(x+1, y)) < name.getStacklimit()))
			  {
 			    Army s = new Army(name.getColor(), name.getMoveLimit(), Integer.toString(name.getArmyVector().size()+1));
	          name.playerUI.lb.addPeice(1, x+1, y);
			    s.setBounds(name.playerUI.lb.getSettlerCount(x+1, y), name.playerUI.lb.getArmyCount(x+1, y));
	          s.setPosition(x+1, y);      
             name.addArmy(s); 
	          name.playerUI.lb.getHolder(s.getX(), s.getY()).add(s.getLabel(), new Integer(5));
             name.playerUI.lb.getHolder(s.getX(), s.getY()).repaint();
             name.playerUI.lb.addArmyCount(s.getX(), s.getY());
		       Player[] tempArray = name.getPlayerUI().getPlayers();    
	          for(int n = 0; n < 2; n++)//number of players
				 {
				   if(name != tempArray[n])
					{
                 JLabel l = new JLabel(s.getIcon());
					  l.setBounds(s.getCurrentbounds());
				     l.setName(s.getLabelName());    
                 tempArray[n].playerUI.lb.addArmyCount(s.getX(), s.getY());
					  tempArray[n].playerUI.lb.getHolder(s.getX(), s.getY()).add(l, new Integer(5));
	              tempArray[n].playerUI.lb.getHolder(s.getX(), s.getY()).repaint();
					  tempArray[n].unitArmyLabels.add(l);
					}
				 }      
	          gardenUsed = true;
			  }
			  else if((name.getCityBuildingName(m, 7) != null && name.getCityBuildingName(m, 7).equals("hanginggardens")) && ((name.playerUI.lb.getSettlerCount(x+1, y) + name.playerUI.lb.getArmyCount(x-1, y)) >= name.getStacklimit()))
           {
				  JOptionPane illegal = new JOptionPane();
				  GameImageIcon im = getImage("data/"+name.getNation()+"Icon.png");  
		        illegal.showMessageDialog(name.playerUI.map, "YOU CANNOT EXCEED YOUR STACK LIMIT\nIN THE GARDENS!", "CANNOT PERFORM ACTION",
				  					 			   JOptionPane.INFORMATION_MESSAGE, im);	 	 
			  }
	    }
	    gardenArmy.removeActionListener(this);  
		 paintThisGUI();
	  }
	  
	  if(e.getSource() == gardenSettler)
 	  {
       done.removeActionListener(this);
       Vector temp = name.getCityVector();
		 for(int m = 0; m < temp.size(); m++)
		 {  
	      int x = name.getCityXpos(m);
	      int y = name.getCityYpos(m);
			int k, i, j=0;
			for(k = x-1; k <= x+1 && !gardenUsed; k++, j++)
			{  
			  i = y-1;
			  if((name.getCityBuildingName(m, j) != null && name.getCityBuildingName(m, j).equals("hanginggardens")) && ((name.playerUI.lb.getSettlerCount(k, i) + name.playerUI.lb.getArmyCount(k, i)) < name.getStacklimit()))
			  {
 			    Settler s = new Settler(name.getColor(), name.getMoveLimit(), Integer.toString(name.getSettlerVector().size()+1));
	          name.playerUI.lb.addPeice(0, k, i);
			    s.setBounds(name.playerUI.lb.getSettlerCount(k, i) + name.playerUI.lb.getArmyCount(k, i));
	          s.setPosition(k, i);      
             name.addSettler(s); 
	          name.playerUI.lb.getHolder(s.getX(), s.getY()).add(s.getLabel(), new Integer(5));
             name.playerUI.lb.getHolder(s.getX(), s.getY()).repaint();
             name.playerUI.lb.addSettlerCount(s.getX(), s.getY());

		       Player[] tempArray = name.getPlayerUI().getPlayers();    
	          for(int n = 0; n < 2; n++)//number of players
				 {
				   if(name != tempArray[n])
					{
                 JLabel l = new JLabel(s.getIcon());
					  l.setBounds(s.getCurrentbounds());
				     l.setName(s.getLabelName());    
                 tempArray[n].playerUI.lb.addSettlerCount(s.getX(), s.getY());
					  tempArray[n].playerUI.lb.getHolder(s.getX(), s.getY()).add(l, new Integer(5));
	              tempArray[n].playerUI.lb.getHolder(s.getX(), s.getY()).repaint();
					  tempArray[n].unitSettlerLabels.add(l);
					}
				 }      
	          gardenUsed = true;
			  }
			  else if((name.getCityBuildingName(m, j) != null && name.getCityBuildingName(m, j).equals("hanginggardens")) && ((name.playerUI.lb.getSettlerCount(k, i) + name.playerUI.lb.getArmyCount(k, i)) >= name.getStacklimit()))
           {
				  JOptionPane illegal = new JOptionPane();
				  GameImageIcon im = getImage("data/"+name.getNation()+"Icon.png");  
		        illegal.showMessageDialog(name.playerUI.map, "YOU CANNOT EXCEED YOUR STACK LIMIT\nIN THE GARDENS!", "CANNOT PERFORM ACTION",
				  					 			   JOptionPane.INFORMATION_MESSAGE, im);	 	 
			  }
			}
		   for(k = x-1; k <= x+1; k++, j++)
			{  
			  i = y+1;
			  if((name.getCityBuildingName(m, j) != null && name.getCityBuildingName(m, j).equals("hanginggardens")) && ((name.playerUI.lb.getSettlerCount(k, i) + name.playerUI.lb.getArmyCount(k, i)) < name.getStacklimit()))
			  {
 			    Settler s = new Settler(name.getColor(), name.getMoveLimit(), Integer.toString(name.getSettlerVector().size()+1));
	          name.playerUI.lb.addPeice(0, k, i);
			    s.setBounds(name.playerUI.lb.getSettlerCount(k, i) + name.playerUI.lb.getArmyCount(k, i));
	          s.setPosition(k, i);      
             name.addSettler(s); 
	          name.playerUI.lb.getHolder(s.getX(), s.getY()).add(s.getLabel(), new Integer(5));
             name.playerUI.lb.getHolder(s.getX(), s.getY()).repaint();
             name.playerUI.lb.addSettlerCount(s.getX(), s.getY());

		       Player[] tempArray = name.getPlayerUI().getPlayers();    
	          for(int n = 0; n < 2; n++)//number of players
				 {
				   if(name != tempArray[n])
					{
                 JLabel l = new JLabel(s.getIcon());
					  l.setBounds(s.getCurrentbounds());
				     l.setName(s.getLabelName());    
                 tempArray[n].playerUI.lb.addSettlerCount(s.getX(), s.getY());
					  tempArray[n].playerUI.lb.getHolder(s.getX(), s.getY()).add(l, new Integer(5));
	              tempArray[n].playerUI.lb.getHolder(s.getX(), s.getY()).repaint();
					  tempArray[n].unitSettlerLabels.add(l);
					}
				 }      
	          gardenUsed = true;
			  }
			  else if((name.getCityBuildingName(m, j) != null && name.getCityBuildingName(m, j).equals("hanginggardens")) && ((name.playerUI.lb.getSettlerCount(k, i) + name.playerUI.lb.getArmyCount(k, i)) >= name.getStacklimit()))
           {
				  JOptionPane illegal = new JOptionPane();
				  GameImageIcon im = getImage("data/"+name.getNation()+"Icon.png");  
		        illegal.showMessageDialog(name.playerUI.map, "YOU CANNOT EXCEED YOUR STACK LIMIT\nIN THE GARDENS!", "CANNOT PERFORM ACTION",
				  					 			   JOptionPane.INFORMATION_MESSAGE, im);	 	 
			  }
	 		}
			  if((name.getCityBuildingName(m, 6) != null && name.getCityBuildingName(m, 6).equals("hanginggardens")) && ((name.playerUI.lb.getSettlerCount(x-1, y) + name.playerUI.lb.getArmyCount(x-1, y)) < name.getStacklimit()))
			  {
 			    Settler s = new Settler(name.getColor(), name.getMoveLimit(), Integer.toString(name.getSettlerVector().size()+1));
	          name.playerUI.lb.addPeice(0, x-1, y);
			    s.setBounds(name.playerUI.lb.getSettlerCount(x-1, y) + name.playerUI.lb.getArmyCount(x-1, y));
	          s.setPosition(x-1, y);      
             name.addSettler(s); 
	          name.playerUI.lb.getHolder(s.getX(), s.getY()).add(s.getLabel(), new Integer(5));
             name.playerUI.lb.getHolder(s.getX(), s.getY()).repaint();
             name.playerUI.lb.addSettlerCount(s.getX(), s.getY());

		       Player[] tempArray = name.getPlayerUI().getPlayers();    
	          for(int n = 0; n < 2; n++)//number of players
				 {
				   if(name != tempArray[n])
					{
                 JLabel l = new JLabel(s.getIcon());
					  l.setBounds(s.getCurrentbounds());
				     l.setName(s.getLabelName());    
                 tempArray[n].playerUI.lb.addSettlerCount(s.getX(), s.getY());
					  tempArray[n].playerUI.lb.getHolder(s.getX(), s.getY()).add(l, new Integer(5));
	              tempArray[n].playerUI.lb.getHolder(s.getX(), s.getY()).repaint();
					  tempArray[n].unitSettlerLabels.add(l);
					}
				 }      
	          gardenUsed = true;
			  }
			  else if((name.getCityBuildingName(m, 6) != null && name.getCityBuildingName(m, 6).equals("hanginggardens")) && ((name.playerUI.lb.getSettlerCount(x-1, y) + name.playerUI.lb.getArmyCount(x-1, y)) >= name.getStacklimit()))
           {
				  JOptionPane illegal = new JOptionPane();
				  GameImageIcon im = getImage("data/"+name.getNation()+"Icon.png");  
		        illegal.showMessageDialog(name.playerUI.map, "YOU CANNOT EXCEED YOUR STACK LIMIT\nIN THE GARDENS!", "CANNOT PERFORM ACTION",
				  					 			   JOptionPane.INFORMATION_MESSAGE, im);	 	 
			  }
			  if((name.getCityBuildingName(m, 7) != null && name.getCityBuildingName(m, 7).equals("hanginggardens")) && ((name.playerUI.lb.getSettlerCount(x+1, y) + name.playerUI.lb.getArmyCount(x+1, y)) < name.getStacklimit()))
			  {
 			    Settler s = new Settler(name.getColor(), name.getMoveLimit(), Integer.toString(name.getSettlerVector().size()+1));
	          name.playerUI.lb.addPeice(0, x+1, y);
			    s.setBounds(name.playerUI.lb.getSettlerCount(x+1, y) + name.playerUI.lb.getArmyCount(x+1, y));
	          s.setPosition(x+1, y);      
             name.addSettler(s); 
	          name.playerUI.lb.getHolder(s.getX(), s.getY()).add(s.getLabel(), new Integer(5));
             name.playerUI.lb.getHolder(s.getX(), s.getY()).repaint();
             name.playerUI.lb.addSettlerCount(s.getX(), s.getY());

		       Player[] tempArray = name.getPlayerUI().getPlayers();    
	          for(int n = 0; n < 2; n++)//number of players
				 {
				   if(name != tempArray[n])
					{
                 JLabel l = new JLabel(s.getIcon());
					  l.setBounds(s.getCurrentbounds());
				     l.setName(s.getLabelName());    
                 tempArray[n].playerUI.lb.addSettlerCount(s.getX(), s.getY());
					  tempArray[n].playerUI.lb.getHolder(s.getX(), s.getY()).add(l, new Integer(5));
	              tempArray[n].playerUI.lb.getHolder(s.getX(), s.getY()).repaint();
					  tempArray[n].unitSettlerLabels.add(l);
					}
				 }      
	          gardenUsed = true;
			  }
			  else if((name.getCityBuildingName(m, 7) != null && name.getCityBuildingName(m, 7).equals("hanginggardens")) && ((name.playerUI.lb.getSettlerCount(x+1, y) + name.playerUI.lb.getArmyCount(x+1, y)) >= name.getStacklimit()))
           {
				  JOptionPane illegal = new JOptionPane();
				  GameImageIcon im = getImage("data/"+name.getNation()+"Icon.png");  
		        illegal.showMessageDialog(name.playerUI.map, "YOU CANNOT EXCEED YOUR STACK LIMIT\nIN THE GARDENS!", "CANNOT PERFORM ACTION",
				  					 			   JOptionPane.INFORMATION_MESSAGE, im);	 	 
			  }
	    }
	    gardenSettler.removeActionListener(this);  
	    paintThisGUI();
	  }

	  if(e.getSource() == buttonBuild)
	  {
       format.removeAll();
       done.removeActionListener(this);
       buttonBuild.removeActionListener(this); 

		 nmCity = new JButton[size];  
		 nmCityHolder = new JLabel[size];  
		 nmCityPane = new JLayeredPane[size];

	    Settler s = null;
		 Army a = null;
	    int x=0, y=0;

		 int i;
		 for(i = 0; i < size; i++)
	    {
		   if(canSettle(movePeices.get(i)))    
		   { 
			 Object obj = movePeices.get(i);
			 if(obj.getClass().getName() == "Settler")
			 {
				s = (Settler)obj;
			   x = s.getX();
				y = s.getY();
			 }
			 else if(obj.getClass().getName() == "Army")
			 {
				a = (Army)obj;
			   x = a.getX();
				y = a.getY();
			 }
			 nmCityPane[i] = new JLayeredPane();        
			 if(s != null)
			   nmCity[i] = new JButton(s.getIcon());
			 else if(a != null)
			   nmCity[i] = new JButton(a.getIcon());
			 nmCity[i].addActionListener(this);  
			 nmCityHolder[i] = new JLabel(name.playerUI.lb.getIcon(x, y));  
			 nmCityHolder[i].setSize(new Dimension(55, 55));
			 nmCity[i].setBounds(10, 10, 25, 25);  
			 nmCityPane[i].add(nmCityHolder[i], new Integer(0));    
			 nmCityPane[i].add(nmCity[i], new Integer(1));
          if(i > 4)       
			   nmCityPane[i].setBounds(00+(55*i-5), 55, 55, 55);
          else if(i > 8)
			   nmCityPane[i].setBounds(00, 110, 55, 55);
			 else
			   nmCityPane[i].setBounds(00+(55*i), 00, 55, 55);
			 format.add(nmCityPane[i]);
			}  
         s = null;
			a = null;
		 }
	    format.repaint();
	    canBuild = false;  
		 buildActive = true;
	  }

	  if(e.getSource() == buttonLink)
	  {
        done.removeActionListener(this);
        buttonLink.removeActionListener(this);    
		  format.removeAll();
		  format.repaint();
	     addSettlerResource();  
	  }

	  if(e.getSource() == buttonPeople)
	  {
        done.removeActionListener(this); 
        buttonPeople.removeActionListener(this); 
        format.removeAll();
	     format.repaint();
	     placeGreatPeople();
	  }

	  if(e.getSource() == republic)
	  {
        done.removeActionListener(this); 
        republic.removeActionListener(this);
        name.setGov(1);
        paintThisGUI();  
	  }
	  if(e.getSource() == democracy)
	  {
        done.removeActionListener(this); 
        democracy.removeActionListener(this);
        name.setGov(2);
        paintThisGUI();  
     }
	  if(e.getSource() == fund)
	  {
        done.removeActionListener(this); 
        fund.removeActionListener(this);
        name.setGov(6);
        paintThisGUI();  
     }
	  if(e.getSource() == feud)
	  {
        done.removeActionListener(this); 
        feud.removeActionListener(this);
        name.setGov(4);
        paintThisGUI();  
     }
	  if(e.getSource() == monarchy)
	  {
        done.removeActionListener(this); 
        monarchy.removeActionListener(this);
        name.setGov(3);
        paintThisGUI();  
     }
	  if(e.getSource() == communism)
	  {
        done.removeActionListener(this); 
        communism.removeActionListener(this);
        name.setGov(5);
        paintThisGUI();  
     }
	  if(e.getSource() == despot)
	  {
        done.removeActionListener(this); 
        despot.removeActionListener(this);
        name.setGov(7);
        paintThisGUI();  
     }

	}

   public void mouseClicked(MouseEvent e)
	{
      if(e.getSource() == cityLabel && e.getClickCount() == 1)
		{
	     
		  JOptionPane info = new JOptionPane();
		  GameImageIcon icon = getImage("data/" + name.getNation() +"Icon.png");
		  info.setIcon(icon);
		  info.showMessageDialog(name.playerUI.c, "Production: "+ name.getCityProduction(0)+
		                            "\nTrade: " + name.getCityTrade(0),
                                  "City", JOptionPane.INFORMATION_MESSAGE, icon);
		}  
	}//end mouseClicked
	public void mouseEntered(MouseEvent e){}//end mouseEntered
	public void mousePressed(MouseEvent e){}//end mousePressed
	public void mouseReleased(MouseEvent e){}//end mouseReleased
	public void mouseExited(MouseEvent e){}//end mouseExited


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


}