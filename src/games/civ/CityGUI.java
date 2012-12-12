package games.civ;

import java.io.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.Vector;
import java.beans.*;
import javax.swing.border.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import games.*;

public class CityGUI extends JComponent implements ActionListener
{
   ChangeEvent tradeMath;
	JInternalFrame internal;
   GameJPanel cityPanel;
	GameJLayeredPane cityHolder;  
   GameJButton done;
   Player name;
   LogicBoard lb;
//	static boolean playerDone;
   JScrollPane cityScrollPane;
	
   GameJButton animal, steam, atomic, currency, philosophy, pottery, writing,
	        chivalry, construction, monarchy, banking, gunpowder, metalCast,
			  masonry, democracy, engineering, print, 
			  spendTrade, spend, cancel, cultureTurn, Settler, Army,
			  aircraft, artillery, infantry, mounted,
			  harvestIncense, harvestIron, harvestSilk, harvestWheat, 
			  tradingPost, market, barracks, harbor, temple, granary,
			  library, workshop, aqueduct, university, bank, academy,
			  ironmine, cathedral, stone, colossus, gardens, oracle,
			  tower, angkor, castle, louvre, panama, statue, opera, unitednations, egypt;

   GameJButton[] cityButtons, SettlerButtons, ArmyButtons, buildingButtons;  
	Vector cityVector, legalSettler, legalArmy, legalBuilding, units;	
   int cityNumber, production, tradeProduction, specialProd, canBuild, engineeringProd, cityActions;
	JSpinner trade;
   JTextArea beforeConvert, afterConvert; 

   boolean SettlerActive, ArmyActive, tradingPostActive, marketActive,
	        barracksActive, harborActive, templeActive, granaryActive,
			  libraryActive, workshopActive, aqueductActive, universityActive,
			  bankActive, academyActive, ironmineActive, cathedralActive, 
			  usedAnimal, usedCurrency, usedMasonry, usedPhilosophy, usedPottery,
			  usedWriting, usedChivalry, usedConstruction, usedDemocracy,
			  usedEngineering, builtEngineeringOne, usedMonarchy, monarchyActive, usedPrint, usedBanking, usedGunpowder,
			  gunpowderActive, usedMetalCast, usedSteam, steamActive, steamMoveActive, usedAtomic, wonderActive,
			  stoneActive, colossusActive, gardensActive, oracleActive, towerActive,
			  angkorActive, castleActive, louvreActive, panamaActive, statueActive,
			  operaActive, unitednationsActive, egyptFree, freeEgyptActive;
		
	public CityGUI(JComponent frame, Player pName, LogicBoard lbSet)
   {
    name = pName;  
	 if(!true)//name.getNation().equals(CivGUI.aiChoice) && !playerDone)
	 {
//	   System.out.println("AI CITYGUI");
//	   if(!name.getFirstTurn())
//		  playerWait();
//	     CivGUI.turn.cityTurn(name);
	 }
	 else
	 {
	   legalBuilding = new Vector(0, 1);
      legalArmy = new Vector(0, 1);    
		legalSettler = new Vector(0, 1);
		tradeProduction = 0; 
	   specialProd = 0;
		engineeringProd = 0;     
      egyptFree = false;
		colossusActive = false;
		gardensActive = false;
		oracleActive = false;
		towerActive = false;
	   angkorActive = false;
		castleActive = false;
		louvreActive = false;
		panamaActive = false;
		statueActive = false;
	   operaActive = false;
		unitednationsActive = false;
		stoneActive = false;
	   cathedralActive = false;
	   ironmineActive = false;
	   academyActive = false;
	   bankActive = false;
	   universityActive = false;
	   aqueductActive = false;
	   workshopActive = false;
	   libraryActive = false;
	   barracksActive = false;
		harborActive  = false;
		templeActive = false;
		granaryActive  = false;  
		marketActive = false;
	   tradingPostActive = false;
	   SettlerActive = false;
		ArmyActive = false;
//	   playerDone = false;
		usedAnimal = false;
		usedCurrency = false;
		usedMasonry = false;
		usedPhilosophy = false;
		usedPottery = false;
		usedWriting = false;
		usedChivalry = false;
		usedConstruction = false;
		usedDemocracy = false;
		usedEngineering = false;
      builtEngineeringOne = false;
		usedMonarchy = false;
      monarchyActive = false;
		usedPrint = false;
		usedBanking = false;
		usedGunpowder = false;
		gunpowderActive = false;
		usedMetalCast = false;
		usedSteam = false;
		steamActive = false;
		steamMoveActive = false;
		usedAtomic = false;
		
		lb = lbSet;
		
		cityHolder = new GameJLayeredPane();
		GridLayout grid = new GridLayout(1, 1, 0, 1);
   	cityPanel = new GameJPanel(grid);
    
	   cityPanel.add(cityHolder);
		
		internal = new JInternalFrame("City management");
	   internal.setPreferredSize(new Dimension(198, 198));
      if(name.getColor().equals("Red"))
      {
		  internal.setBackground(new Color(255, 0, 0));    
      }    
		else if(name.getColor().equals("Yellow"))
	   {
		  internal.setBackground(new Color(255, 255, 0));    
		}
		else if(name.getColor().equals("Blue"))
	   {
		  internal.setBackground(new Color(0, 0, 255));    
		}
		else if(name.getColor().equals("Green"))
	   {
		  internal.setBackground(new Color(0, 255, 0)); 
		}
		
		cityVector = new Vector(0, 1);
		for(int k = 0; k < name.getCityVector().size(); k++)
  	   {
		  cityVector.add(name.getCityVector().get(k));
      }
//System.out.println("CITY SIZE "+cityVector.size());		
		cityButtons = new GameJButton[cityVector.size()];
		cityActions = 0;
		
		int k;
		if(name.getAnarchy())
		  k = 1;
		else
		  k = 0;  
		for(; k < cityVector.size(); k++)
		{
		  cityButtons[k] = new GameJButton(name.getCityIcon(k));
		  cityButtons[k].setBounds(00, 00, name.getCityIcon(k).getIconWidth(), name.getCityIcon(k).getIconHeight());
		  int x = name.getCityXpos(k);
		  int y = name.getCityYpos(k);
		  name.playerUI.lb.getHolder(x, y).add(cityButtons[k], new Integer(50));
        name.playerUI.lb.getHolder(x, y).repaint();
        cityButtons[k].addActionListener(this);
		  cityActions++;
		}

//display non-city build options inclusing relivent techs

      GameJLabel initial1;
		GameJLabel initial2;
		if(cityActions > 0)
		{
	      initial1 = new GameJLabel("Click a city on the map");
			initial2 = new GameJLabel("       to take it's city action!");
			initial1.setBounds(10, 20, 190, 20);
			initial2.setBounds(10, 40, 190, 20);
      }
		else
		{
			initial1 = new GameJLabel("No city turns this turn!");
			initial2 = new GameJLabel("");
			initial1.setBounds(25, 20, 190, 20);
			initial2.setBounds(10, 40, 190, 20);
		}

      cityHolder.setBounds(00, 00, 198, 198);	 
		cityHolder.add(initial1, new Integer(44));
		cityHolder.add(initial2, new Integer(44));
      cityHolder.setOpaque(true);
      cityHolder.setBackground(name.getPlayerColor());
      
	   cityPanel.setPreferredSize(new Dimension(190, 100));  
		
		cityScrollPane = new JScrollPane(cityPanel, 20, 31);
      cityScrollPane.setPreferredSize(new Dimension(180, 100));
//      cityScrollPane.setBounds(00, 00, 00, 00);
//      cityScrollPane.setOpaque(true);
      done = new GameJButton("BEGIN MOVEMENT TURN");
	   done.setPreferredSize(new Dimension(180, 20));  
		done.setBounds(5, 145, 180, 20);
	   done.addActionListener(this);
		
		internal.add(cityScrollPane, "Center");
		internal.add(done, "South");
      internal.show();
	   internal.setBorder(new BevelBorder(1, new Color(0, 0, 0), new Color(255, 255, 255)));

	   frame.removeAll();  
		frame.add(internal);

		try
	   {internal.setMaximum(true);}
		// techInternal.setMaximum(true);}
	   catch(PropertyVetoException ex)
	   {}
		frame.validate();

		cityNumber = -1;
		canBuild = 2;
		setTechButtons();
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

  private void redrawInitial()
  {
      cityHolder.removeAll();
		
      GameJLabel initial1;
		GameJLabel initial2;
		if(cityActions > 0)      
		{
			initial1 = new GameJLabel("Click a city on the map");
			initial2 = new GameJLabel("       to take it's city action!");
			initial1.setBounds(10, 20, 190, 20);
			initial2.setBounds(10, 40, 190, 20);
         cityNumber = -1;    
		}
		else
		{
			initial1 = new GameJLabel("All city turns taken!");
			initial2 = new GameJLabel("");
			initial1.setBounds(25, 20, 190, 20);
			initial2.setBounds(10, 40, 190, 20);
		}
		
		cityHolder.add(initial1, new Integer(44));
		cityHolder.add(initial2, new Integer(44));

      cityHolder.repaint();
      cityPanel.setPreferredSize(new Dimension(190, 100));
      cityScrollPane.revalidate();
 	   
//		for(int k = 0; k < cityVector.size(); k++)
//	   {
//	     if(cityVector.get(k) != null)  
//		    cityButtons[k].addActionListener(this);
//	   }  
	   canBuild = 2;  
		setTechButtons();
	   done.addActionListener(this);

		 for(int k = 0; k < cityVector.size(); k++)
		 {
		   if(cityVector.get(k) != null)  
			  cityButtons[k].addActionListener(this);
		 }  

  }

  private void displayCityOptions(int cnum)
  {
    cityNumber = cnum;
    if(cnum >= 0)
      production = (name.getCityProduction(cnum) + tradeProduction + specialProd + engineeringProd);
	 
	 for(int k = 0; k < cityVector.size(); k++)
	 {
	   if(cityVector.get(k) != null)  
		  cityButtons[k].removeActionListener(this);
	 }  
//  cityHolder.remove(0);
//	 cityHolder.remove(0);
    
	 cityHolder.removeAll();
	 cityHolder.repaint();
	 
    //display production
	 GameJLabel prod = new GameJLabel(Integer.toString(production), getImage("data/production.png"), GameJLabel.CENTER);
	 prod.setBounds(70, 00, 100, 30);
	 cityHolder.add(prod, new Integer(5));
	 //display build options (including relivent techs)
    
	 canBuild = 1;//1 instead of zero for space management(i.e good GUI)
	 for(int k = 0; k < 4; k++)
	 {
	   if(name.getAvailableWonder(k) > -1)
		{
		  int choice = name.getAvailableWonder(k);
		  
		  switch(choice)
		  {
		    case 0:
				if(production >= 10 && !name.hasCityWonder(cityNumber))
				{
				  if(lb.hasBuildTerrain(name.getCityXpos(cityNumber), name.getCityYpos(cityNumber), "Any"))
              {        
					 stone = new GameJButton("Build Stonehenge");
					 stone.setBounds(05, 27*canBuild, 155, 25);
					 cityHolder.add(stone, new Integer(5)); 
					 stone.addActionListener(this);
					 canBuild++;
				  }
				}
				break;
		    case 1:
				if(((production >= 15) || (name.getTechVector().get(5) != null && production >= 10)) && !name.hasCityWonder(cityNumber))
				{
				  if(lb.hasBuildTerrain(name.getCityXpos(cityNumber), name.getCityYpos(cityNumber), "Any"))
              {        
					 colossus = new GameJButton("Build Colossus");
					 colossus.setBounds(05, 27*canBuild, 155, 25);
					 cityHolder.add(colossus, new Integer(5)); 
					 colossus.addActionListener(this);
					 canBuild++;
				  }
				}
				break;
		    case 2:
				if(((production >= 15) || (name.getTechVector().get(0) != null  && production >= 10)) && !name.hasCityWonder(cityNumber))
				{
				  if(lb.hasBuildTerrain(name.getCityXpos(cityNumber), name.getCityYpos(cityNumber), "Any"))
              {        
					 gardens = new GameJButton("Build Hanging Gardens");
					 gardens.setBounds(05, 27*canBuild, 155, 25);
					 cityHolder.add(gardens, new Integer(5)); 
					 gardens.addActionListener(this);
					 canBuild++;
				  }
				}
				break;
		    case 3:
				if(((production >= 15) || (name.getTechVector().get(1) != null && production >= 10)) && !name.hasCityWonder(cityNumber))
				{
				  if(lb.hasBuildTerrain(name.getCityXpos(cityNumber), name.getCityYpos(cityNumber), "Any"))
              {        
					 oracle = new GameJButton("Build Oracle");
					 oracle.setBounds(05, 27*canBuild, 155, 25);
					 cityHolder.add(oracle, new Integer(5)); 
					 oracle.addActionListener(this);
					 canBuild++;
				  }
				}
				break;
		    case 4:
				if(((production >= 20) || (name.getTechVector().get(12) != null && production >= 15)) && !name.hasCityWonder(cityNumber))
				{
				  if(lb.hasBuildTerrain(name.getCityXpos(cityNumber), name.getCityYpos(cityNumber), "Any"))
              {        
					 tower = new GameJButton("Build Porcelain Tower");
					 tower.setBounds(05, 27*canBuild, 155, 25);
					 cityHolder.add(tower, new Integer(5)); 
					 tower.addActionListener(this);
					 canBuild++;
				  }
				}
				break;
		    case 5:
				if(((production >= 20) || (name.getTechVector().get(7) != null && production >= 15)) && !name.hasCityWonder(cityNumber))
				{
				  if(lb.hasBuildTerrain(name.getCityXpos(cityNumber), name.getCityYpos(cityNumber), "Any"))
              {        
					 angkor = new GameJButton("Build Angkor Wat");
					 angkor.setBounds(05, 27*canBuild, 155, 25);
					 cityHolder.add(angkor, new Integer(5)); 
					 angkor.addActionListener(this);
					 canBuild++;
				  }
				}
				break;
		    case 6:
				if(((production >= 20) || (name.getTechVector().get(17) != null && production >= 15)) && !name.hasCityWonder(cityNumber))
				{
				  if(lb.hasBuildTerrain(name.getCityXpos(cityNumber), name.getCityYpos(cityNumber), "Any"))
              {        
					 castle = new GameJButton("Build Himeji Castle");
					 castle.setBounds(05, 27*canBuild, 155, 25);
					 cityHolder.add(castle, new Integer(5)); 
					 castle.addActionListener(this);
					 canBuild++;
				  }
				}
				break;
		    case 7:
				if(((production >= 20) || (name.getTechVector().get(18) != null && production >= 15)) && !name.hasCityWonder(cityNumber))
				{
				  if(lb.hasBuildTerrain(name.getCityXpos(cityNumber), name.getCityYpos(cityNumber), "Any"))
              {        
					 louvre = new GameJButton("Build Louvre");
					 louvre.setBounds(05, 27*canBuild, 155, 25);
					 cityHolder.add(louvre, new Integer(5)); 
					 louvre.addActionListener(this);
					 canBuild++;
				  }
				}
				break;
		    case 8:
				if(((production >= 25) || (name.getTechVector().get(14) != null && production >= 20)) && !name.hasCityWonder(cityNumber))
				{
				  if(lb.hasBuildTerrain(name.getCityXpos(cityNumber), name.getCityYpos(cityNumber), "Any"))
              {        
					 panama = new GameJButton("Build Panama Canal");
					 panama.setBounds(05, 27*canBuild, 155, 25);
					 cityHolder.add(panama, new Integer(5)); 
					 panama.addActionListener(this);
					 canBuild++;
				  }
				}
				break;
		    case 9:
				if(((production >= 25) || (name.getTechVector().get(24) != null && production >= 20)) && !name.hasCityWonder(cityNumber))
				{
				  if(lb.hasBuildTerrain(name.getCityXpos(cityNumber), name.getCityYpos(cityNumber), "Any"))
              {        
					 statue = new GameJButton("Build Statue of Liberty");
					 statue.setBounds(05, 27*canBuild, 155, 25);
					 cityHolder.add(statue, new Integer(5)); 
					 statue.addActionListener(this);
					 canBuild++;
				  }
				}
				break;
		    case 10:
				if((production >= 25) && !name.hasCityWonder(cityNumber))
				{
				  if(lb.hasBuildTerrain(name.getCityXpos(cityNumber), name.getCityYpos(cityNumber), "Any"))
              {        
					 opera = new GameJButton("Build Sydney Opera House");
					 opera.setBounds(05, 27*canBuild, 155, 25);
					 cityHolder.add(opera, new Integer(5)); 
					 opera.addActionListener(this);
					 canBuild++;
				  }
				}
				break;
		    case 11:
				if((production >= 20) && !name.hasCityWonder(cityNumber))
				{
				  if(lb.hasBuildTerrain(name.getCityXpos(cityNumber), name.getCityYpos(cityNumber), "Any"))
              {        
					 unitednations = new GameJButton("Build United Nations");
					 unitednations.setBounds(05, 27*canBuild, 155, 25);
					 cityHolder.add(unitednations, new Integer(5)); 
					 unitednations.addActionListener(this);
					 canBuild++;
				  }
				}
				break;
		  }
		}
	 }
	 
	 if(name.getNation().equals("Egypt") && !egyptFree && !builtEngineeringOne)
	 {
       if((name.getTechVector().get(28) != null && !name.hasCityUnique(cityNumber) && name.playerUI.lb.hasBuildTerrain(name.getCityXpos(cityNumber), name.getCityYpos(cityNumber), "Any")  && name.playerUI.availableCathedral > 0)
				|| (name.getTechVector().get(26) != null && name.playerUI.lb.hasBuildTerrain(name.getCityXpos(cityNumber), name.getCityYpos(cityNumber), "Mountain") && name.playerUI.availableIronmine > 0)
				|| (name.getTechVector().get(25) != null && !name.hasCityUnique(cityNumber) && name.playerUI.lb.hasBuildTerrain(name.getCityXpos(cityNumber), name.getCityYpos(cityNumber), "Any") && name.playerUI.availableAcademy > 0)
				|| (name.getTechVector().get(20) != null && !name.hasCityUnique(cityNumber) && name.playerUI.lb.hasBuildTerrain(name.getCityXpos(cityNumber), name.getCityYpos(cityNumber), "Any") && name.playerUI.availableBank > 0)
				|| (name.getTechVector().get(18) != null && name.playerUI.lb.hasBuildTerrain(name.getCityXpos(cityNumber), name.getCityYpos(cityNumber), "Grass") && name.playerUI.availableUniversity > 0)
				|| (name.getTechVector().get(14) != null && name.playerUI.lb.hasBuildTerrain(name.getCityXpos(cityNumber), name.getCityYpos(cityNumber), "Grass") && name.playerUI.availableAqueduct > 0)
				|| (name.getTechVector().get(12) != null && name.playerUI.lb.hasBuildTerrain(name.getCityXpos(cityNumber), name.getCityYpos(cityNumber), "Mountain") && name.playerUI.availableWorkshop > 0) 
				|| (name.getTechVector().get(9) != null && name.playerUI.lb.hasBuildTerrain(name.getCityXpos(cityNumber), name.getCityYpos(cityNumber), "Grass") && name.playerUI.availableLibrary > 0)
				|| (name.getTechVector().get(8) != null && name.playerUI.lb.hasBuildTerrain(name.getCityXpos(cityNumber), name.getCityYpos(cityNumber), "Grass") && name.playerUI.availableGranary > 0)
				|| (name.getTechVector().get(7) != null && !name.hasCityUnique(cityNumber) && name.playerUI.lb.hasBuildTerrain(name.getCityXpos(cityNumber), name.getCityYpos(cityNumber), "Any") && name.playerUI.availableTemple > 0)
				|| (name.getTechVector().get(6) != null && name.playerUI.lb.hasBuildTerrain(name.getCityXpos(cityNumber), name.getCityYpos(cityNumber), "Water") && name.playerUI.availableHarbor > 0)
				|| (name.getTechVector().get(5) != null && !name.hasCityUnique(cityNumber) && name.playerUI.lb.hasBuildTerrain(name.getCityXpos(cityNumber), name.getCityYpos(cityNumber), "Any") && name.playerUI.availableMarket > 0)
				|| (name.getTechVector().get(2) != null && !name.hasCityUnique(cityNumber) && name.playerUI.lb.hasBuildTerrain(name.getCityXpos(cityNumber), name.getCityYpos(cityNumber), "Any") && name.playerUI.availableTradepost > 0) 
				|| (name.getTechVector().get(1) != null && name.playerUI.lb.hasBuildTerrain(name.getCityXpos(cityNumber), name.getCityYpos(cityNumber), "Desert"))) 
		 		 {
					 egypt = new GameJButton("Egypt's free building!");
				    egypt.setBounds(05, 27*canBuild, 155, 25);
				    cityHolder.add(egypt, new Integer(5)); 
				    egypt.addActionListener(this);
					 canBuild++;
		       }
	 }
	 
	 if(name.getTechVector().get(28) != null && production >= 10 && !name.hasCityUnique(cityNumber))
	 {
      if(lb.hasBuildTerrain(name.getCityXpos(cityNumber), name.getCityYpos(cityNumber), "Any"))
	   {
			 cathedral = new GameJButton("Build Cathedral");
		    cathedral.setBounds(05, 27*canBuild, 155, 25);
		    cityHolder.add(cathedral, new Integer(5)); 
		    cathedral.addActionListener(this);
			 canBuild++;
      }
	 }
	 if(name.getTechVector().get(26) != null && production >= 10)
	 {
      if(lb.hasBuildTerrain(name.getCityXpos(cityNumber), name.getCityYpos(cityNumber), "Mountain") && name.playerUI.availableIronmine > 0)
	   {
			 ironmine = new GameJButton("Build Iron Mine");
		    ironmine.setBounds(05, 27*canBuild, 155, 25);
		    cityHolder.add(ironmine, new Integer(5)); 
		    ironmine.addActionListener(this);   
			 canBuild++;
      }
	 }
	 if(name.getTechVector().get(25) != null && production >= 10 && !name.hasCityUnique(cityNumber))
	 {
      if(lb.hasBuildTerrain(name.getCityXpos(cityNumber), name.getCityYpos(cityNumber), "Any") && name.playerUI.availableAcademy > 0)
	   {
			 academy = new GameJButton("Build Military Academy");
		    academy.setBounds(05, 27*canBuild, 155, 25);
		    cityHolder.add(academy, new Integer(5)); 
		    academy.addActionListener(this);   
			 canBuild++;
	   }
	 }
	 if(name.getTechVector().get(20) != null && production >= 10 && !name.hasCityUnique(cityNumber))
	 {
      if(lb.hasBuildTerrain(name.getCityXpos(cityNumber), name.getCityYpos(cityNumber), "Any") && name.playerUI.availableBank > 0)
	   {
		 	 bank = new GameJButton("Build Bank");
		    bank.setBounds(05, 27*canBuild, 155, 25);
		    cityHolder.add(bank, new Integer(5)); 
		    bank.addActionListener(this);   
			 canBuild++;
      }
	 }
	 if(name.getTechVector().get(18) != null && production >= 8)
	 {
      if(lb.hasBuildTerrain(name.getCityXpos(cityNumber), name.getCityYpos(cityNumber), "Grass") && name.playerUI.availableUniversity > 0)
	   {
			 university = new GameJButton("Build University");
		    university.setBounds(05, 27*canBuild, 155, 25);
		    cityHolder.add(university, new Integer(5)); 
		    university.addActionListener(this);   
			 canBuild++;
      }
	 }
	 if(name.getTechVector().get(14) != null && production >= 8)
	 {
      if(lb.hasBuildTerrain(name.getCityXpos(cityNumber), name.getCityYpos(cityNumber), "Grass") && name.playerUI.availableAqueduct > 0)
	   {
		    aqueduct = new GameJButton("Build Aqueduct");
		    aqueduct.setBounds(05, 27*canBuild, 155, 25);
		    cityHolder.add(aqueduct, new Integer(5)); 
		    aqueduct.addActionListener(this);   
			 canBuild++;
	   }
	 }
	 if(name.getTechVector().get(12) != null && production >= 7)
	 {
      if(lb.hasBuildTerrain(name.getCityXpos(cityNumber), name.getCityYpos(cityNumber), "Mountain") && name.playerUI.availableWorkshop > 0)
	   {
			 workshop = new GameJButton("Build Workshop");
		    workshop.setBounds(05, 27*canBuild, 155, 25);
		    cityHolder.add(workshop, new Integer(5)); 
		    workshop.addActionListener(this);   
			 canBuild++;
	   }
	 }
	 if(name.getTechVector().get(9) != null && production >= 5)
	 {
      if(lb.hasBuildTerrain(name.getCityXpos(cityNumber), name.getCityYpos(cityNumber), "Grass") && name.playerUI.availableLibrary > 0)
	   {
			 library = new GameJButton("Build Library");
		    library.setBounds(05, 27*canBuild, 155, 25);
		    cityHolder.add(library, new Integer(5)); 
		    library.addActionListener(this);   
			 canBuild++;
      }
	 }
	 if(name.getTechVector().get(8) != null && production >= 5)
	 {
      if(lb.hasBuildTerrain(name.getCityXpos(cityNumber), name.getCityYpos(cityNumber), "Grass") && name.playerUI.availableGranary > 0)
	   {
			 granary = new GameJButton("Build Granary");
		    granary.setBounds(05, 27*canBuild, 155, 25);
		    cityHolder.add(granary, new Integer(5)); 
		    granary.addActionListener(this);   
			 canBuild++;
      }
	 }
	 if(name.getTechVector().get(7) != null && production >= 7 && !name.hasCityUnique(cityNumber) && name.playerUI.availableTemple > 0)
	 {
      if(lb.hasBuildTerrain(name.getCityXpos(cityNumber), name.getCityYpos(cityNumber), "Any"))
	   {
			 temple = new GameJButton("Build Temple");
		    temple.setBounds(05, 27*canBuild, 155, 25);
		    cityHolder.add(temple, new Integer(5)); 
		    temple.addActionListener(this);   
			 canBuild++;
      }
	 }
	 if(name.getTechVector().get(6) != null && production >= 7)
	 {
      if(lb.hasBuildTerrain(name.getCityXpos(cityNumber), name.getCityYpos(cityNumber), "Water") && name.playerUI.availableHarbor > 0)
	   {
			 harbor = new GameJButton("Build Harbor");
		    harbor.setBounds(05, 27*canBuild, 155, 25);
		    cityHolder.add(harbor, new Integer(5)); 
		    harbor.addActionListener(this);   
			 canBuild++;
      }
	 }
	 if(name.getTechVector().get(5) != null && production >= 7 && !name.hasCityUnique(cityNumber) && name.playerUI.availableBarrack > 0)
	 {
      if(lb.hasBuildTerrain(name.getCityXpos(cityNumber), name.getCityYpos(cityNumber), "Any"))
	   {
			 barracks = new GameJButton("Build Barracks");
		    barracks.setBounds(05, 27*canBuild, 155, 25);
		    cityHolder.add(barracks, new Integer(5)); 
		    barracks.addActionListener(this);   
			 canBuild++;
      }
	 }
	 if(name.getTechVector().get(2) != null && production >= 7 && !name.hasCityUnique(cityNumber) && name.playerUI.availableMarket > 0)
	 {
      if(lb.hasBuildTerrain(name.getCityXpos(cityNumber), name.getCityYpos(cityNumber), "Any"))
	   {
			 market = new GameJButton("Build Market");
		    market.setBounds(05, 27*canBuild, 155, 25);
		    cityHolder.add(market, new Integer(5)); 
		    market.addActionListener(this);   
			 canBuild++;
	   }
	 }
	 if(name.getTechVector().get(1) != null && production >= 7)
	 {
		 if(lb.hasBuildTerrain(name.getCityXpos(cityNumber), name.getCityYpos(cityNumber), "Desert") && name.playerUI.availableTradepost > 0)
		 {
			 tradingPost = new GameJButton("Build Trading Post");
		    tradingPost.setBounds(05, 27*canBuild, 155, 25);
		    cityHolder.add(tradingPost, new Integer(5)); 
		    tradingPost.addActionListener(this);   
			 canBuild++;
       }
	 }
	 if((name.getInfLevel() == 1 && production >=5)
	    || (name.getInfLevel() == 2 && production >=7)
		 || (name.getInfLevel() == 3 && production >=9) 
		 || (name.getInfLevel() == 4 && production >=11))//infcost
	 {
		 if(name.getPlayerUI().getInfDeckSize() > 0)
		 {
			 infantry = new GameJButton("Build Infantry Unit");
		    infantry.setBounds(05, 27*canBuild, 155, 25);
		    cityHolder.add(infantry, new Integer(5)); 
		    infantry.addActionListener(this);   
			 canBuild++;
       }
	 }
	 if((name.getMntLevel() == 1 && production >=5)
	    || (name.getMntLevel() == 2 && production >=7)
		 || (name.getMntLevel() == 3 && production >=9) 
		 || (name.getMntLevel() == 4 && production >=11))//infcost
	 {
		 if(name.getPlayerUI().getMntDeckSize() > 0)
		 {
			 mounted = new GameJButton("Build Mounted Unit");
		    mounted.setBounds(05, 27*canBuild, 155, 25);
		    cityHolder.add(mounted, new Integer(5)); 
		    mounted.addActionListener(this);   
			 canBuild++;
	    }
	 }
	 if((name.getArtLevel() == 1 && production >=5)
	    || (name.getArtLevel() == 2 && production >=7)
		 || (name.getArtLevel() == 3 && production >=9) 
		 || (name.getArtLevel() == 4 && production >=11))//infcost
	 {
		 if(name.getPlayerUI().getArtDeckSize() > 0)
		 {
			 artillery = new GameJButton("Build Artillery Unit");
		    artillery.setBounds(05, 27*canBuild, 155, 25);
		    cityHolder.add(artillery, new Integer(5)); 
		    artillery.addActionListener(this);   
			 canBuild++;
       }
	 }
	 if(name.getTechVector().get(33) != null && production >= 12)
	 {
		 if(name.getPlayerUI().getAcftDeckSize() > 0)
		 {
			 aircraft = new GameJButton("Build Aircraft Unit");
		    aircraft.setBounds(05, 27*canBuild, 155, 25);
		    cityHolder.add(aircraft, new Integer(5)); 
		    aircraft.addActionListener(this);   
			 canBuild++;
       }
	 }
	 if(name.getNation().equals("Russia") && (name.getArmyVector().size() < 7 && production >= 4))
	 {
		 Army = new GameJButton("Build Army");
	    Army.setBounds(05, 27*canBuild, 155, 25);
	    cityHolder.add(Army, new Integer(5)); 
	    Army.addActionListener(this);   
		 canBuild++;
	 }
	 else if(name.getArmyVector().size() < 6 && production >= 4)
	 {
		 Army = new GameJButton("Build Army");
	    Army.setBounds(05, 27*canBuild, 155, 25);
	    cityHolder.add(Army, new Integer(5)); 
	    Army.addActionListener(this);   
		 canBuild++;
	 }
	 if(name.getSettlerVector().size() < 2 && production >= 6)
	 {
		 Settler = new GameJButton("Build Settler");
	    Settler.setBounds(05, 27*canBuild, 155, 25);
	    cityHolder.add(Settler, new Integer(5)); 
	    Settler.addActionListener(this);   
		 canBuild++;
	 }
	 if(!builtEngineeringOne)//can always do this except second engineering action
	 {
		 cultureTurn = new GameJButton("Devote to the arts!");
	    cultureTurn.setBounds(05, 27*canBuild, 155, 25);
	    cityHolder.add(cultureTurn, new Integer(5)); 
	    cultureTurn.addActionListener(this);   
		 canBuild++;
	 }
	 int AngkorWatCity = -1;
	 if(name.getWonderCards(5) && !builtEngineeringOne)
	 {
        Vector temp = name.getCityVector();
		  for(int m = 0; m < temp.size(); m++)
		  {  
	       int x = name.getCityXpos(m);
	       int y = name.getCityYpos(m);
			 int k, i, j=0;
			 for(k = x-1; k <= x+1; k++, j++)
			 {  
			   i = y-1;
			   if((name.getCityBuildingName(m, j) != null && name.getCityBuildingName(m, j).equals("angkorwat")))
 			   {
			      if(name.playerUI.numIncense > 0 && m == cityNumber)
					{
					  harvestIncense = new GameJButton("Harvest Incense");
					  harvestIncense.setBounds(05, 27*canBuild, 155, 25);
					  cityHolder.add(harvestIncense, new Integer(5)); 
					  harvestIncense.addActionListener(this);   
					  canBuild++;
					}
					if(name.playerUI.numIron > 0 && m == cityNumber)
					{
					  harvestIron = new GameJButton("Harvest Iron");
					  harvestIron.setBounds(05, 27*canBuild, 155, 25);
					  cityHolder.add(harvestIron, new Integer(5)); 
					  harvestIron.addActionListener(this);   
					  canBuild++;
					}
				   if(name.playerUI.numSilk > 0 && m == cityNumber)
				   {
					  harvestSilk = new GameJButton("Harvest Silk");
					  harvestSilk.setBounds(05, 27*canBuild, 155, 25);
					  cityHolder.add(harvestSilk, new Integer(5)); 
					  harvestSilk.addActionListener(this);   
					  canBuild++;
					}
					if(name.playerUI.numWheat > 0 && m == cityNumber)
					{
					  harvestWheat = new GameJButton("Harvest Wheat");
					  harvestWheat.setBounds(05, 27*canBuild, 155, 25);
					  cityHolder.add(harvestWheat, new Integer(5)); 
					  harvestWheat.addActionListener(this);   
					  canBuild++;
					}
               AngkorWatCity = m;
			   }
			 }
		    for(k = x-1; k <= x+1; k++, j++)
			 {  
			   i = y+1;
			   if((name.getCityBuildingName(m, j) != null && name.getCityBuildingName(m, j).equals("angkorwat")) && !builtEngineeringOne)
 			   {
			      if(name.playerUI.numIncense > 0 && m == cityNumber)
					{
					  harvestIncense = new GameJButton("Harvest Incense");
					  harvestIncense.setBounds(05, 27*canBuild, 155, 25);
					  cityHolder.add(harvestIncense, new Integer(5)); 
					  harvestIncense.addActionListener(this);   
					  canBuild++;
					}
					if(name.playerUI.numIron > 0 && m == cityNumber)
					{
					  harvestIron = new GameJButton("Harvest Iron");
					  harvestIron.setBounds(05, 27*canBuild, 155, 25);
					  cityHolder.add(harvestIron, new Integer(5)); 
					  harvestIron.addActionListener(this);   
					  canBuild++;
					}
				   if(name.playerUI.numSilk > 0 && m == cityNumber)
				   {
					  harvestSilk = new GameJButton("Harvest Silk");
					  harvestSilk.setBounds(05, 27*canBuild, 155, 25);
					  cityHolder.add(harvestSilk, new Integer(5)); 
					  harvestSilk.addActionListener(this);   
					  canBuild++;
					}
					if(name.playerUI.numWheat > 0 && m == cityNumber)
					{
					  harvestWheat = new GameJButton("Harvest Wheat");
					  harvestWheat.setBounds(05, 27*canBuild, 155, 25);
					  cityHolder.add(harvestWheat, new Integer(5)); 
					  harvestWheat.addActionListener(this);   
					  canBuild++;
					}
              AngkorWatCity = m;
			   }
	 		 }
			 if((name.getCityBuildingName(m, 6) != null && name.getCityBuildingName(m, 6).equals("angkorwat")) && !builtEngineeringOne)
 			 {
			      if(name.playerUI.numIncense > 0 && m == cityNumber)
					{
					  harvestIncense = new GameJButton("Harvest Incense");
					  harvestIncense.setBounds(05, 27*canBuild, 155, 25);
					  cityHolder.add(harvestIncense, new Integer(5)); 
					  harvestIncense.addActionListener(this);   
					  canBuild++;
					}
					if(name.playerUI.numIron > 0 && m == cityNumber)
					{
					  harvestIron = new GameJButton("Harvest Iron");
					  harvestIron.setBounds(05, 27*canBuild, 155, 25);
					  cityHolder.add(harvestIron, new Integer(5)); 
					  harvestIron.addActionListener(this);   
					  canBuild++;
					}
				   if(name.playerUI.numSilk > 0 && m == cityNumber)
				   {
					  harvestSilk = new GameJButton("Harvest Silk");
					  harvestSilk.setBounds(05, 27*canBuild, 155, 25);
					  cityHolder.add(harvestSilk, new Integer(5)); 
					  harvestSilk.addActionListener(this);   
					  canBuild++;
					}
					if(name.playerUI.numWheat > 0 && m == cityNumber)
					{
					  harvestWheat = new GameJButton("Harvest Wheat");
					  harvestWheat.setBounds(05, 27*canBuild, 155, 25);
					  cityHolder.add(harvestWheat, new Integer(5)); 
					  harvestWheat.addActionListener(this);   
					  canBuild++;
					}
            AngkorWatCity = m;
			 }
			 if((name.getCityBuildingName(m, 7) != null && name.getCityBuildingName(m, 7).equals("angkorwat")) && !builtEngineeringOne)
 			 {
			      if(name.playerUI.numIncense > 0 && m == cityNumber)
					{
					  harvestIncense = new GameJButton("Harvest Incense");
					  harvestIncense.setBounds(05, 27*canBuild, 155, 25);
					  cityHolder.add(harvestIncense, new Integer(5)); 
					  harvestIncense.addActionListener(this);   
					  canBuild++;
					}
					if(name.playerUI.numIron > 0 && m == cityNumber)
					{
					  harvestIron = new GameJButton("Harvest Iron");
					  harvestIron.setBounds(05, 27*canBuild, 155, 25);
					  cityHolder.add(harvestIron, new Integer(5)); 
					  harvestIron.addActionListener(this);   
					  canBuild++;
					}
				   if(name.playerUI.numSilk > 0 && m == cityNumber)
				   {
					  harvestSilk = new GameJButton("Harvest Silk");
					  harvestSilk.setBounds(05, 27*canBuild, 155, 25);
					  cityHolder.add(harvestSilk, new Integer(5)); 
					  harvestSilk.addActionListener(this);   
					  canBuild++;
					}
					if(name.playerUI.numWheat > 0 && m == cityNumber)
					{
					  harvestWheat = new GameJButton("Harvest Wheat");
					  harvestWheat.setBounds(05, 27*canBuild, 155, 25);
					  cityHolder.add(harvestWheat, new Integer(5)); 
					  harvestWheat.addActionListener(this);   
					  canBuild++;
					}
            AngkorWatCity = m;
			 }
	    }
	 }
	 if(name.getCurrentGov() == 4)
	 {
	   for(int k = 0; k < name.cities.size(); k++)
	   {
			if(lb.canHarvestX(name.getCityXpos(k), name.getCityYpos(k), name, k) && AngkorWatCity != k  && !builtEngineeringOne)
			{
		      int x = name.getCityXpos(k);
				int y = name.getCityYpos(k);    
		      if(lb.canHarvestIncense(x, y, name, k) && name.playerUI.numIncense > 0)
				{
				  harvestIncense = new GameJButton("Harvest Incense");
			     harvestIncense.setBounds(05, 27*canBuild, 155, 25);
			     cityHolder.add(harvestIncense, new Integer(5)); 
			     harvestIncense.addActionListener(this);   
				  canBuild++;
				}
		      if(lb.canHarvestIron(x, y, name, k) && name.playerUI.numIron > 0)
				{
				  harvestIron = new GameJButton("Harvest Iron");
			     harvestIron.setBounds(05, 27*canBuild, 155, 25);
			     cityHolder.add(harvestIron, new Integer(5)); 
			     harvestIron.addActionListener(this);   
				  canBuild++;
				}
		      if(lb.canHarvestSilk(x, y, name, k) && name.playerUI.numSilk > 0)
				{
				  harvestSilk = new GameJButton("Harvest Silk");
			     harvestSilk.setBounds(05, 27*canBuild, 155, 25);
			     cityHolder.add(harvestSilk, new Integer(5)); 
			     harvestSilk.addActionListener(this);   
				  canBuild++;
				}
				if(lb.canHarvestWheat(x, y, name, k) && name.playerUI.numWheat > 0)
				{
				  harvestWheat = new GameJButton("Harvest Wheat");
			     harvestWheat.setBounds(05, 27*canBuild, 155, 25);
			     cityHolder.add(harvestWheat, new Integer(5)); 
			     harvestWheat.addActionListener(this);   
				  canBuild++;
				}
	      }
	   } 
    }  
	 else
	 {
//System.out.println("Harvest ERROR 1: "+name.getCityXpos(cityNumber)+" 2: "+name.getCityYpos(cityNumber)+" 3: "+ name+" 4: "+ cityNumber);		
		if(name.playerUI.lb.canHarvestX(name.getCityXpos(cityNumber), name.getCityYpos(cityNumber), name, cityNumber) && AngkorWatCity != cityNumber  && !builtEngineeringOne)
	   {
	      int x = name.getCityXpos(cityNumber);
			int y = name.getCityYpos(cityNumber);    
	      if(lb.canHarvestIncense(x, y, name, cityNumber)  && name.playerUI.numIncense > 0)
			{
			  harvestIncense = new GameJButton("Harvest Incense");
		     harvestIncense.setBounds(05, 27*canBuild, 155, 25);
		     cityHolder.add(harvestIncense, new Integer(5)); 
		     harvestIncense.addActionListener(this);   
			  canBuild++;
			}
	      if(lb.canHarvestIron(x, y, name, cityNumber) && name.playerUI.numIron > 0)
			{
			  harvestIron = new GameJButton("Harvest Iron");
		     harvestIron.setBounds(05, 27*canBuild, 155, 25);
		     cityHolder.add(harvestIron, new Integer(5)); 
		     harvestIron.addActionListener(this);   
			  canBuild++;
			}
	      if(lb.canHarvestSilk(x, y, name, cityNumber) && name.playerUI.numSilk > 0)
			{
			  harvestSilk = new GameJButton("Harvest Silk");
		     harvestSilk.setBounds(05, 27*canBuild, 155, 25);
		     cityHolder.add(harvestSilk, new Integer(5)); 
		     harvestSilk.addActionListener(this);   
			  canBuild++;
			}
			if(lb.canHarvestWheat(x, y, name, cityNumber) && name.playerUI.numWheat > 0)
			{
			  harvestWheat = new GameJButton("Harvest Wheat");
		     harvestWheat.setBounds(05, 27*canBuild, 155, 25);
		     cityHolder.add(harvestWheat, new Integer(5)); 
		     harvestWheat.addActionListener(this);   
			  canBuild++;
			}
	   }
	 }	
	 if(name.getTrade() >= 3 || (name.getNation().equals("America") && name.getTrade() >= 2))
	 {
		 spendTrade = new GameJButton("Convert trade");
	    spendTrade.setBounds(05, 27*canBuild, 155, 25);
	    cityHolder.add(spendTrade, new Integer(5)); 
	    spendTrade.addActionListener(this);   
		 canBuild++;
	 }
    
//	 done.removeActionListener(this);
//    cityHolder.repaint();
//    cityPanel.setPreferredSize(new Dimension(190, 27*canBuild));
//    cityScrollPane.revalidate();
	 setTechButtons();
  }
  
  private void displayEgyptOptions()
  {
    canBuild = 0;
	 cityHolder.removeAll();
    cityHolder.repaint();
    cityPanel.setPreferredSize(new Dimension(190, 100));
    cityScrollPane.revalidate();
	 if(name.getTechVector().get(28) != null && !name.hasCityUnique(cityNumber))
	 {
      if(lb.hasBuildTerrain(name.getCityXpos(cityNumber), name.getCityYpos(cityNumber), "Any") && name.playerUI.availableCathedral > 0)
	   {
			 cathedral = new GameJButton("Build Cathedral");
		    cathedral.setBounds(05, 27*canBuild, 155, 25);
		    cityHolder.add(cathedral, new Integer(5)); 
		    cathedral.addActionListener(this);
			 canBuild++;
      }
	 }
	 if(name.getTechVector().get(26) != null)
	 {
      if(lb.hasBuildTerrain(name.getCityXpos(cityNumber), name.getCityYpos(cityNumber), "Mountain") && name.playerUI.availableIronmine > 0)
	   {
			 ironmine = new GameJButton("Build Iron Mine");
		    ironmine.setBounds(05, 27*canBuild, 155, 25);
		    cityHolder.add(ironmine, new Integer(5)); 
		    ironmine.addActionListener(this);   
			 canBuild++;
      }
	 }
	 if(name.getTechVector().get(25) != null && !name.hasCityUnique(cityNumber))
	 {
      if(lb.hasBuildTerrain(name.getCityXpos(cityNumber), name.getCityYpos(cityNumber), "Any") && name.playerUI.availableAcademy > 0)
	   {
			 academy = new GameJButton("Build Military Academy");
		    academy.setBounds(05, 27*canBuild, 155, 25);
		    cityHolder.add(academy, new Integer(5)); 
		    academy.addActionListener(this);   
			 canBuild++;
	   }
	 }
	 if(name.getTechVector().get(20) != null && !name.hasCityUnique(cityNumber))
	 {
      if(lb.hasBuildTerrain(name.getCityXpos(cityNumber), name.getCityYpos(cityNumber), "Any") && name.playerUI.availableBank > 0)
	   {
		 	 bank = new GameJButton("Build Bank");
		    bank.setBounds(05, 27*canBuild, 155, 25);
		    cityHolder.add(bank, new Integer(5)); 
		    bank.addActionListener(this);   
			 canBuild++;
      }
	 }
	 if(name.getTechVector().get(18) != null)
	 {
      if(lb.hasBuildTerrain(name.getCityXpos(cityNumber), name.getCityYpos(cityNumber), "Grass") && name.playerUI.availableUniversity > 0)
	   {
			 university = new GameJButton("Build University");
		    university.setBounds(05, 27*canBuild, 155, 25);
		    cityHolder.add(university, new Integer(5)); 
		    university.addActionListener(this);   
			 canBuild++;
      }
	 }
	 if(name.getTechVector().get(14) != null)
	 {
      if(lb.hasBuildTerrain(name.getCityXpos(cityNumber), name.getCityYpos(cityNumber), "Grass") && name.playerUI.availableAqueduct > 0)
	   {
		    aqueduct = new GameJButton("Build Aqueduct");
		    aqueduct.setBounds(05, 27*canBuild, 155, 25);
		    cityHolder.add(aqueduct, new Integer(5)); 
		    aqueduct.addActionListener(this);   
			 canBuild++;
	   }
	 }
	 if(name.getTechVector().get(12) != null)
	 {
      if(lb.hasBuildTerrain(name.getCityXpos(cityNumber), name.getCityYpos(cityNumber), "Mountain") && name.playerUI.availableWorkshop > 0)
	   {
			 workshop = new GameJButton("Build Workshop");
		    workshop.setBounds(05, 27*canBuild, 155, 25);
		    cityHolder.add(workshop, new Integer(5)); 
		    workshop.addActionListener(this);   
			 canBuild++;
	   }
	 }
	 if(name.getTechVector().get(9) != null)
	 {
      if(lb.hasBuildTerrain(name.getCityXpos(cityNumber), name.getCityYpos(cityNumber), "Grass") && name.playerUI.availableLibrary > 0)
	   {
			 library = new GameJButton("Build Library");
		    library.setBounds(05, 27*canBuild, 155, 25);
		    cityHolder.add(library, new Integer(5)); 
		    library.addActionListener(this);   
			 canBuild++;
      }
	 }
	 if(name.getTechVector().get(8) != null)
	 {
      if(lb.hasBuildTerrain(name.getCityXpos(cityNumber), name.getCityYpos(cityNumber), "Grass") && name.playerUI.availableGranary > 0)
	   {
			 granary = new GameJButton("Build Granary");
		    granary.setBounds(05, 27*canBuild, 155, 25);
		    cityHolder.add(granary, new Integer(5)); 
		    granary.addActionListener(this);   
			 canBuild++;
      }
	 }
	 if(name.getTechVector().get(7) != null && !name.hasCityUnique(cityNumber))
	 {
      if(lb.hasBuildTerrain(name.getCityXpos(cityNumber), name.getCityYpos(cityNumber), "Any") && name.playerUI.availableTemple > 0)
	   {
			 temple = new GameJButton("Build Temple");
		    temple.setBounds(05, 27*canBuild, 155, 25);
		    cityHolder.add(temple, new Integer(5)); 
		    temple.addActionListener(this);   
			 canBuild++;
      }
	 }
	 if(name.getTechVector().get(6) != null)
	 {
      if(lb.hasBuildTerrain(name.getCityXpos(cityNumber), name.getCityYpos(cityNumber), "Water") && name.playerUI.availableHarbor > 0)
	   {
			 harbor = new GameJButton("Build Harbor");
		    harbor.setBounds(05, 27*canBuild, 155, 25);
		    cityHolder.add(harbor, new Integer(5)); 
		    harbor.addActionListener(this);   
			 canBuild++;
      }
	 }
	 if(name.getTechVector().get(5) != null && !name.hasCityUnique(cityNumber))
	 {
      if(lb.hasBuildTerrain(name.getCityXpos(cityNumber), name.getCityYpos(cityNumber), "Any") && name.playerUI.availableBarrack > 0)
	   {
			 barracks = new GameJButton("Build Barracks");
		    barracks.setBounds(05, 27*canBuild, 155, 25);
		    cityHolder.add(barracks, new Integer(5)); 
		    barracks.addActionListener(this);   
			 canBuild++;
      }
	 }
	 if(name.getTechVector().get(2) != null && !name.hasCityUnique(cityNumber))
	 {
      if(lb.hasBuildTerrain(name.getCityXpos(cityNumber), name.getCityYpos(cityNumber), "Any") && name.playerUI.availableMarket > 0)
	   {
			 market = new GameJButton("Build Market");
		    market.setBounds(05, 27*canBuild, 155, 25);
		    cityHolder.add(market, new Integer(5)); 
		    market.addActionListener(this);   
			 canBuild++;
	   }
	 }
	 if(name.getTechVector().get(1) != null)
	 {
		 if(lb.hasBuildTerrain(name.getCityXpos(cityNumber), name.getCityYpos(cityNumber), "Desert") && name.playerUI.availableTradepost > 0)
		 {
			 tradingPost = new GameJButton("Build Trading Post");
		    tradingPost.setBounds(05, 27*canBuild, 155, 25);
		    cityHolder.add(tradingPost, new Integer(5)); 
		    tradingPost.addActionListener(this);   
			 canBuild++;
       }
	 }
    freeEgyptActive = true;
	 cityHolder.repaint();
  }

  private void finishCityTurn()
  {
    for(int k = 0; k < cityVector.size(); k++)
	 {
//	   if(cityNumber != k)
//		  cityButtons[k].addActionListener(this);
	   if(cityNumber == k)//changed this
		{
		  int x = name.getCityXpos(k);
		  int y = name.getCityYpos(k);
		  if(!usedMasonry)
		    name.playerUI.lb.getHolder(x, y).remove(0);
		  cityButtons[k].removeActionListener(this);
		  cityVector.remove(k);
        cityVector.add(k, null);
 		}  
	 }  
    if(usedEngineering)  
	   builtEngineeringOne = true;   
	 production = 0;  
    tradeProduction = 0; 
	 specialProd = 0;
    engineeringProd = 0;
	 cityActions--;
	 redrawInitial();
  }
  
  private void setTechButtons()
  {
    int numTechs = canBuild;  
	 if(name.getTechVector().get(29) != null && name.getUranium() > 0 && !usedAtomic)
	 {
	 	atomic = new GameJButton("Use Atomic Theory");
		atomic.setIcon(getImage("data/Uranium.png"));
	   atomic.setBounds(00, 30*numTechs, 195, 28);
	   atomic.addActionListener(this);   
	   cityHolder.add(atomic, new Integer(5)); 
      numTechs++;
	 }
	 if(name.getTechVector().get(27) != null && name.getSilk() > 0 && !usedSteam && (name.getSettlerVector().size()+name.getArmyVector().size() > 0))
	 {
	 	steam = new GameJButton("Use Steam Power");
		steam.setIcon(getImage("data/Silk.png"));
	   steam.setBounds(00, 30*numTechs, 195, 28);
	   steam.addActionListener(this);   
	   cityHolder.add(steam, new Integer(5)); 
      numTechs++;
	 }
	 if(name.getTechVector().get(24) != null && name.getIncense() > 0 && !usedMetalCast)
	 {
	 	metalCast = new GameJButton("Use Metal Casting");
		metalCast.setIcon(getImage("data/Incense.png"));
	   metalCast.setBounds(00, 30*numTechs, 195, 27);
	   metalCast.addActionListener(this);   
	   cityHolder.add(metalCast, new Integer(5)); 
      numTechs++;
	 }
	 if(name.getTechVector().get(23) != null && ((name.getUranium() + name.getSpy() + name.getIncense() + name.getIron() + name.getSilk() + name.getWheat()) > 1)  && !usedGunpowder)
	 {
	 	gunpowder = new GameJButton("Use Gunpowder");
		gunpowder.setIcon(getImage("data/Any.png"));
	   gunpowder.setBounds(00, 30*numTechs, 195, 27);
	   gunpowder.addActionListener(this);   
	   cityHolder.add(gunpowder, new Integer(5)); 
      numTechs++;
	 }
	 if((cityNumber >=0) && name.getTechVector().get(20) != null && name.getWheat() > 0 && !usedBanking && (cityActions > 0))
	 {
	 	banking = new GameJButton("Use Banking");
		banking.setIcon(getImage("data/Wheat.png"));
	   banking.setBounds(00, 30*numTechs, 195, 27);
	   banking.addActionListener(this);   
	   cityHolder.add(banking, new Integer(5)); 
      numTechs++;
	 }
	 if(name.getTechVector().get(18) != null && name.getCulture() > 5 && !usedPrint)
	 {
	 	print = new GameJButton("Use Printing Press");
		print.setIcon(getImage("data/cultureSpend.png"));
	   print.setBounds(00, 30*numTechs, 195, 27);
	   print.addActionListener(this);   
	   cityHolder.add(print, new Integer(5)); 
      numTechs++;
	 }
	 if(name.getTechVector().get(17) != null && name.getSilk() > 0 && !usedMonarchy)
	 {
	 	monarchy = new GameJButton("Use Monarchy");
		monarchy.setIcon(getImage("data/Silk.png"));
	   monarchy.setBounds(00, 30*numTechs, 195, 27);
	   monarchy.addActionListener(this);   
	   cityHolder.add(monarchy, new Integer(5)); 
      numTechs++;
	 }
	 if((cityNumber >=0) && name.getTechVector().get(14) != null && !usedEngineering && (cityActions > 0))
	 {
	 	engineering = new GameJButton("Use Engineering");
		engineering.setIcon(getImage("data/production.png"));
	   engineering.setBounds(00, 30*numTechs, 195, 27);
	   engineering.addActionListener(this);   
	   cityHolder.add(engineering, new Integer(5)); 
      numTechs++;
	 }
	 if(name.getTechVector().get(13) != null && name.getTrade() >= 6 && !usedDemocracy)
	 {
	 	democracy = new GameJButton("Use Democracy");
		democracy.setIcon(getImage("data/trade.png"));
	   democracy.setBounds(00, 30*numTechs, 195, 27);
	   democracy.addActionListener(this);   
	   cityHolder.add(democracy, new Integer(5)); 
      numTechs++;
	 }
	 if((cityNumber >=0) && name.getTechVector().get(12) != null  && name.getWheat() > 0 && !usedConstruction && (cityActions > 0))
	 {
	 	construction = new GameJButton("Use Construction");
		construction.setIcon(getImage("data/Wheat.png"));
	   construction.setBounds(00, 30*numTechs, 195, 27);
	   construction.addActionListener(this);   
	   cityHolder.add(construction, new Integer(5)); 
      numTechs++;
	 }
	 if(name.getTechVector().get(10) != null && name.getIncense() > 0 && !usedChivalry)
	 {
	 	chivalry = new GameJButton("Use Chivalry");
		chivalry.setIcon(getImage("data/Incense.png"));
	   chivalry.setBounds(00, 30*numTechs, 195, 27);
	   chivalry.addActionListener(this);   
	   cityHolder.add(chivalry, new Integer(5)); 
      numTechs++;
	 }
	 if(name.getTechVector().get(9) != null && name.getSpy() > 0 && !usedWriting)
	 {
	 	writing = new GameJButton("Use Writing");
		writing.setIcon(getImage("data/Spy.png"));
	   writing.setBounds(00, 30*numTechs, 195, 27);
	   writing.addActionListener(this);   
	   cityHolder.add(writing, new Integer(5)); 
      numTechs++;
	 }
	 if(name.getTechVector().get(8) != null && ((name.getUranium() + name.getSpy() + name.getIncense() + name.getIron() + name.getSilk() + name.getWheat()) > 1) && !usedPottery)
	 {
	 	pottery = new GameJButton("Use Pottery");
		pottery.setIcon(getImage("data/Any.png"));
	   pottery.setBounds(00, 30*numTechs, 195, 27);
	   pottery.addActionListener(this);   
	   cityHolder.add(pottery, new Integer(5)); 
      numTechs++;
	 }
	 if(name.getTechVector().get(7) != null && ((name.getUranium() + name.getSpy() + name.getIncense() + name.getIron() + name.getSilk() + name.getWheat()) > 2) && !usedPhilosophy)
	 {
	 	philosophy = new GameJButton("Use Philosophy");
		philosophy.setIcon(getImage("data/Any.png"));
	   philosophy.setBounds(00, 30*numTechs, 195, 27);
	   philosophy.addActionListener(this);   
	   cityHolder.add(philosophy, new Integer(5)); 
      numTechs++;
	 }
	 if((cityNumber >=0) && name.getTechVector().get(4) != null  && name.getCityProduction(cityNumber) > 6  && !name.cityHasWalls(cityNumber) && (cityActions > 0))
	 {
	 	masonry = new GameJButton("Use Masonry");
		masonry.setIcon(getImage("data/production.png"));
	   masonry.setBounds(00, 30*numTechs, 195, 27);
	   masonry.addActionListener(this);   
	   cityHolder.add(masonry, new Integer(5)); 
      numTechs++;
	 }
	 if(name.getTechVector().get(2) != null && name.getIncense() > 0 && !usedCurrency)
	 {
	 	currency = new GameJButton("Use Currency");
		currency.setIcon(getImage("data/Incense.png"));
	   currency.setBounds(00, 30*numTechs, 195, 27);
	   currency.addActionListener(this);   
	   cityHolder.add(currency, new Integer(5)); 
      numTechs++;
	 }
	 if((cityNumber >=0) && name.getTechVector().get(0) != null && name.getWheat() > 0 && !usedAnimal && (cityActions > 0))
	 {
	 	animal = new GameJButton("Use Animal Husbandry");
		animal.setIcon(getImage("data/Wheat.png"));
	   animal.setBounds(00, 30*numTechs, 195, 27);
	   animal.addActionListener(this);   
	   cityHolder.add(animal, new Integer(5)); 
      numTechs++;
	 }
	 cityHolder.repaint();
    cityPanel.setPreferredSize(new Dimension(195, 30*numTechs));
    cityScrollPane.revalidate();

  }
  
  synchronized void playerWait()
  {
//    System.out.println(name.getNation()+"is HERE!");
//	 while(!playerDone)
//		{
//        System.out.println(name.getNation()+"is WAITING!");
//		  try{wait(1000);}
//		  catch(InterruptedException ex){System.out.println("EXCEPTION");}
//		}
    notify();
  }
  
//  public static boolean getDone(){
//     return playerDone;
//  }
  
  public void actionPerformed(ActionEvent e) 
  {

    for(int k = 0; k < cityVector.size(); k++)  
	 {
      if(cityButtons[k] != null && e.getSource() == cityButtons[k])    
      {
        displayCityOptions(k);    
		}
	 }
	 	
    if(e.getSource() == done)
    {
		for(int k = 0; k < cityVector.size(); k++)
		{
		  int x = name.getCityXpos(k);
		  int y = name.getCityYpos(k);
		  if(name.playerUI.lb.getHolder(x, y).getIndexOf(cityButtons[k]) >= 0)
		    name.playerUI.lb.getHolder(x, y).remove(name.playerUI.lb.getHolder(x, y).getIndexOf(cityButtons[k]));
        name.playerUI.lb.getHolder(x, y).repaint();
		}
	   internal.dispose();
//		playerDone = true;
//      playerWait();  
	   name.getPlayerUI().getTurn().moveTurn(name);
	 }

    if(e.getSource() == spendTrade)
    {
		cityHolder.removeAll();
		if(name.getNation().equals("America"))
		{
		  SpinnerModel model =
        new SpinnerNumberModel(0, //initial value
                               0, //min
                               name.getTrade()/2, //max
                               1);                //step
	     trade = new JSpinner(model);
		  trade.setBounds(40, 20, 100, 20);
     
	     GameJLabel lb_1 = new GameJLabel("Select amount to convert");  
        lb_1.setBounds(25, 10, 180, 15);

	     GameJLabel lb_2 = new GameJLabel("(one production per two trade)");  
        lb_2.setBounds(04, 25, 190, 15);

	     GameJLabel lb_3 = new GameJLabel("# production:");  
        lb_3.setBounds(05, 50, 80, 20);

	     trade = new JSpinner(model);
		  trade.setBounds(90, 50, 100, 20);

	     spend = new GameJButton("convert");
		  spend.setBounds(10, 90, 100, 20);    
		  spend.addActionListener(this);

	     cancel = new GameJButton("cancel");
		  cancel.setBounds(10, 120, 100, 20);    
		  cancel.addActionListener(this);
		  
	     GameJLabel lb_4 = new GameJLabel("trade before");  
        lb_4.setBounds(120, 77, 80, 10);
        beforeConvert = new JTextArea(Integer.toString(name.getTrade()));
        beforeConvert.setEditable(false);
        beforeConvert.setBackground(new Color(0, 0, 0));
        beforeConvert.setForeground(new Color(255, 255, 255));
        beforeConvert.setBounds(140, 90, 15, 15);

	     GameJLabel lb_5 = new GameJLabel("trade after");  
        lb_5.setBounds(120, 108, 80, 10);
        afterConvert = new JTextArea(Integer.toString(name.getTrade()));
        afterConvert.setBackground(new Color(0, 0, 0));
        afterConvert.setForeground(new Color(255, 255, 255));
        afterConvert.setEditable(false);
        afterConvert.setBounds(140, 120, 15, 15);
		  
		  cityHolder.add(lb_1, new Integer(0));  
		  cityHolder.add(lb_2, new Integer(0));  
		  cityHolder.add(lb_3, new Integer(0));  
		  cityHolder.add(lb_4, new Integer(0));  
		  cityHolder.add(lb_5, new Integer(0));  
		  cityHolder.add(trade, new Integer(0));  
		  cityHolder.add(cancel, new Integer(0));  
		  cityHolder.add(spend, new Integer(0));  
		  cityHolder.add(beforeConvert, new Integer(0));  
		  cityHolder.add(afterConvert, new Integer(0));  
		  cityHolder.repaint();
        cityPanel.setPreferredSize(new Dimension(190, 140));
        cityScrollPane.revalidate();

        trade.addChangeListener(new SpinnerListener());
		}
		else
		{
//add ok and cancel buttons		  
		  SpinnerModel model =
        new SpinnerNumberModel(0, //initial value
                               0, //min
                               name.getTrade()/3, //max
                               1);                //step

	     GameJLabel lb_1 = new GameJLabel("Select amount to convert");  
        lb_1.setBounds(25, 10, 180, 15);

	     GameJLabel lb_2 = new GameJLabel("(one production per three trade)");  
        lb_2.setBounds(04, 25, 190, 15);

	     GameJLabel lb_3 = new GameJLabel("# production:");  
        lb_3.setBounds(05, 50, 80, 20);

	     trade = new JSpinner(model);
		  trade.setBounds(90, 50, 100, 20);

	     spend = new GameJButton("convert");
		  spend.setBounds(10, 90, 100, 20);    
		  spend.addActionListener(this);

	     cancel = new GameJButton("cancel");
		  cancel.setBounds(10, 120, 100, 20);    
		  cancel.addActionListener(this);
		  
	     GameJLabel lb_4 = new GameJLabel("trade before");  
        lb_4.setBounds(120, 77, 80, 10);
        beforeConvert = new JTextArea(Integer.toString(name.getTrade()));
        beforeConvert.setEditable(false);
        beforeConvert.setBackground(new Color(0, 0, 0));
        beforeConvert.setForeground(new Color(255, 255, 255));
        beforeConvert.setBounds(140, 90, 15, 15);

	     GameJLabel lb_5 = new GameJLabel("trade after");  
        lb_5.setBounds(120, 108, 80, 10);
        afterConvert = new JTextArea(Integer.toString(name.getTrade()));
        afterConvert.setBackground(new Color(0, 0, 0));
        afterConvert.setForeground(new Color(255, 255, 255));
        afterConvert.setEditable(false);
        afterConvert.setBounds(140, 120, 15, 15);
		  
		  cityHolder.add(lb_1, new Integer(0));  
		  cityHolder.add(lb_2, new Integer(0));  
		  cityHolder.add(lb_3, new Integer(0));  
		  cityHolder.add(lb_4, new Integer(0));  
		  cityHolder.add(lb_5, new Integer(0));  
		  cityHolder.add(trade, new Integer(0));  
		  cityHolder.add(cancel, new Integer(0));  
		  cityHolder.add(spend, new Integer(0));  
		  cityHolder.add(beforeConvert, new Integer(0));  
		  cityHolder.add(afterConvert, new Integer(0));  
		  cityHolder.repaint();
        cityPanel.setPreferredSize(new Dimension(190, 140));
        cityScrollPane.revalidate();
		
        trade.addChangeListener(new SpinnerListener());

		}  
	 }

    if(e.getSource() == spend)
    {
      spend.removeActionListener(this);
      tradeProduction += (Integer)trade.getValue();
      if(name.getNation().equals("America"))
		  name.convertTrade((Integer)trade.getValue()*2);
      else
		  name.convertTrade((Integer)trade.getValue()*3);

//		redrawInitial();
      displayCityOptions(cityNumber); 
	 }  
    
	 if(e.getSource() == cancel)
    {
      cancel.removeActionListener(this);
      production -= name.getCityProduction(cityNumber);    
		
      GameJLayeredPane temp;
		for(int k = 0; k < legalBuilding.size(); k++)
		{
		  if(legalBuilding.get(k) != null)
		  {
			  temp = (GameJLayeredPane)legalBuilding.get(k);
			  if(temp.getIndexOf(buildingButtons[k]) >= 0)
			    temp.remove(temp.getIndexOf(buildingButtons[k]));
           temp.repaint();
		  }    
	   }
      if(ArmyActive)
		{
			for(int k = 0; k < legalArmy.size(); k++)
			{
			   if(legalArmy.get(k) != null)
				{
				  temp = (GameJLayeredPane)legalArmy.get(k);
			     temp.remove(temp.getIndexOf(ArmyButtons[k]));
	           temp.repaint();
				}    
			}
      }
		if(SettlerActive)
		{
			for(int k = 0; k < legalSettler.size(); k++)
			{
			   if(legalSettler.get(k) != null)
				{
				  temp = (GameJLayeredPane)legalSettler.get(k);
			     temp.remove(temp.getIndexOf(SettlerButtons[k]));
	           temp.repaint();
				}    
			}
      }

      egyptFree = false;
      colossusActive = false;
		gardensActive = false;
		oracleActive = false;
		towerActive = false;
	   angkorActive = false;
		castleActive = false;
		louvreActive = false;
		panamaActive = false;
		statueActive = false;
	   operaActive = false;
		unitednationsActive = false;
		stoneActive = false;
	   cathedralActive = false;
	   ironmineActive = false;
	   academyActive = false;
	   bankActive = false;
	   universityActive = false;
	   aqueductActive = false;
	   workshopActive = false;
	   libraryActive = false;
	   barracksActive = false;
		harborActive  = false;
		templeActive = false;
		granaryActive  = false;  
		marketActive = false;
	   tradingPostActive = false;
	   SettlerActive = false;
		ArmyActive = false;
//      redrawInitial();
		displayCityOptions(cityNumber); 
	 }  

    if(e.getSource() == cultureTurn)
    {
		cultureTurn.removeActionListener(this);
	   name.gainCulture(cityNumber);
	   finishCityTurn();
	 }

    if(e.getSource() == Settler)
	 {
		legalSettler = new Vector(0, 1); 
      Vector icons = new Vector(0, 1);
      int x = name.getCityXpos(cityNumber);
      int y = name.getCityYpos(cityNumber);
		int k, i;
	   boolean waterException = false; 
		for(k = x-1; k <= x+1; k++)
		{  
		  i = y-1;
		  if(name.playerUI.lb.getTerrain(k, i).equals("Water") && !name.getWaterLand())
          waterException = true;
		  if((name.playerUI.lb.getSettlerCount(k, i) + name.playerUI.lb.getArmyCount(k, i)) < name.getStacklimit() && !waterException)
		  {
		    legalSettler.add(name.playerUI.lb.getHolder(k, i));
          icons.add(name.playerUI.lb.getIcon(k, i));  
		  }
		  else
        {
		    legalSettler.add(null);		 
          icons.add(null);    
		  }   
		  waterException = false;
		}
	   for(k = x-1; k <= x+1; k++)
		{  
		  i = y+1;
		  if(name.playerUI.lb.getTerrain(k, i).equals("Water") && !name.getWaterLand())
          waterException = true;
		  if((name.playerUI.lb.getSettlerCount(k, i) + name.playerUI.lb.getArmyCount(k, i)) < name.getStacklimit() && !waterException)
		  {
		    legalSettler.add(name.playerUI.lb.getHolder(k, i));
          icons.add(name.playerUI.lb.getIcon(k, i));  
	     }	
		  else
        {
		    legalSettler.add(null);		 
          icons.add(null);    
		  }   
		  waterException = false;
 		}
		if(name.playerUI.lb.getTerrain(x-1, y).equals("Water") && !name.getWaterLand())
         waterException = true;
		if((name.playerUI.lb.getSettlerCount(x-1, y) + name.playerUI.lb.getArmyCount(x-1, y)) < name.getStacklimit() && !waterException)
		{
		  legalSettler.add(name.playerUI.lb.getHolder(x-1, y));
          icons.add(name.playerUI.lb.getIcon(x-1, y));  
      }
		else
      {
		  legalSettler.add(null);		 
        icons.add(null);    
		}   
      waterException = false;
		if(name.playerUI.lb.getTerrain(x+1, y).equals("Water") && !name.getWaterLand())
          waterException = true;
		if((name.playerUI.lb.getSettlerCount(x+1, y) + name.playerUI.lb.getArmyCount(x+1, y)) < name.getStacklimit() && !waterException)
		{
		  legalSettler.add(name.playerUI.lb.getHolder(x+1, y));
        icons.add(name.playerUI.lb.getIcon(x+1, y));  
      }
		else
      {
		  legalSettler.add(null);		 
        icons.add(null);    
		}   
	   SettlerButtons = new GameJButton[legalSettler.size()];  
		for(k = 0; k < legalSettler.size(); k++)
		{
        if(legalSettler.get(k) != null)    
		  {
			  GameJLayeredPane temp = (GameJLayeredPane)legalSettler.get(k);
	        GameImageIcon icon = (GameImageIcon)icons.get(k);
	        SettlerButtons[k] = new GameJButton(icon);
			  SettlerButtons[k].setBounds(00, 00, icon.getIconWidth(), icon.getIconHeight());
			  temp.add(SettlerButtons[k], new Integer(4));
	        temp.repaint();
			  SettlerButtons[k].addActionListener(this);
		  }
		}
		for(k = 0; k < cityVector.size(); k++)
	   {
	     if(cityVector.get(k) != null)  
		    cityButtons[k].removeActionListener(this);
	   }  
	   GameJLabel lb_1 = new GameJLabel("Select position on map");  
      lb_1.setBounds(30, 10, 150, 15);

	   GameJLabel lb_2 = new GameJLabel("to add Settler!");  
      lb_2.setBounds(45, 25, 110, 15);

		cancel = new GameJButton("cancel");
		cancel.setBounds(40, 80, 100, 20);    
		cancel.addActionListener(this);

      cityHolder.removeAll();
	   cityHolder.add(lb_1, new Integer(0));  
		cityHolder.add(lb_2, new Integer(0));  
		cityHolder.add(cancel, new Integer(0));  
		cityHolder.repaint();
      cityPanel.setPreferredSize(new Dimension(190, 110));
      cityScrollPane.revalidate();
	   
	   SettlerActive = true;
	 }
    if(SettlerActive)
    {
      for(int k = 0; k < legalSettler.size(); k++)  
	   {
        if(SettlerButtons[k] != null && e.getSource() == SettlerButtons[k])    
        {
			 Settler s = new Settler(name.getColor(), name.getMoveLimit(), Integer.toString(name.getSettlerVector().size()+1));

          int x = name.getCityXpos(cityNumber);
          int y = name.getCityYpos(cityNumber);

          Player[] tempArray = name.getPlayerUI().getPlayers();
			 			  
          switch(k)
			 {
			   case 0:
	           name.playerUI.lb.addPeice(0, x-1, y-1);
			     s.setBounds(name.playerUI.lb.getSettlerCount(x-1, y-1) + name.playerUI.lb.getArmyCount(x-1, y-1));
	           s.setPosition(x-1, y-1);      
		        for(int i = 0; i < 2; i++)//number of players
		        {
                if(name != tempArray[i])
                {
                  GameJLabel l = new GameJLabel(s.getIcon());
						l.setBounds(s.getCurrentbounds());
				      l.setName(s.getLabelName());    
				      tempArray[i].playerUI.lb.getHolder(x-1, y-1).add(l, new Integer(5));
                  tempArray[i].playerUI.lb.addPeice(0, x-1, y-1);				    
					   tempArray[i].unitSettlerLabels.add(l);
					 }
				  }	 
				  break;
			   case 1:
	           name.playerUI.lb.addPeice(0, x, y-1);
			     s.setBounds(name.playerUI.lb.getSettlerCount(x, y-1) + name.playerUI.lb.getArmyCount(x, y-1));
	           s.setPosition(x, y-1);      
		        for(int i = 0; i < 2; i++)//number of players
		        {
                if(name != tempArray[i])
                {
                  GameJLabel l = new GameJLabel(s.getIcon());
						l.setBounds(s.getCurrentbounds());
				      l.setName(s.getLabelName());    
				      tempArray[i].playerUI.lb.getHolder(x, y-1).add(l, new Integer(5));
                  tempArray[i].playerUI.lb.addPeice(0, x, y-1);				    
					   tempArray[i].unitSettlerLabels.add(l);
					 }
				  }	 
				  break;
			   case 2:
	           name.playerUI.lb.addPeice(0, x+1, y-1);
			     s.setBounds(name.playerUI.lb.getSettlerCount(x+1, y-1) + name.playerUI.lb.getArmyCount(x+1, y-1));
	           s.setPosition(x+1, y-1);      
		        for(int i = 0; i < 2; i++)//number of players
		        {
                if(name != tempArray[i])
                {
                  GameJLabel l = new GameJLabel(s.getIcon());
						l.setBounds(s.getCurrentbounds());
				      l.setName(s.getLabelName());    
				      tempArray[i].playerUI.lb.getHolder(x+1, y-1).add(l, new Integer(5));
                  tempArray[i].playerUI.lb.addPeice(0, x+1, y-1);				    
					   tempArray[i].unitSettlerLabels.add(l);
					 }
				  }	 
				  break;
			   case 3:
	           name.playerUI.lb.addPeice(0, x-1, y+1);
			     s.setBounds(name.playerUI.lb.getSettlerCount(x-1, y+1) + name.playerUI.lb.getArmyCount(x-1, y+1));
	           s.setPosition(x-1, y+1);      
		        for(int i = 0; i < 2; i++)//number of players
		        {
                if(name != tempArray[i])
                {
                  GameJLabel l = new GameJLabel(s.getIcon());
						l.setBounds(s.getCurrentbounds());
				      l.setName(s.getLabelName());    
				      tempArray[i].playerUI.lb.getHolder(x-1, y+1).add(l, new Integer(5));
                  tempArray[i].playerUI.lb.addPeice(0, x-1, y+1);				    
					   tempArray[i].unitSettlerLabels.add(l);
					 }
				  }	 
				  break;
			   case 4:
	           name.playerUI.lb.addPeice(0, x, y+1);
			     s.setBounds(name.playerUI.lb.getSettlerCount(x, y+1) + name.playerUI.lb.getArmyCount(x, y+1));
	           s.setPosition(x, y+1);      
		        for(int i = 0; i < 2; i++)//number of players
		        {
                if(name != tempArray[i])
                {
                  GameJLabel l = new GameJLabel(s.getIcon());
						l.setBounds(s.getCurrentbounds());
				      l.setName(s.getLabelName());    
				      tempArray[i].playerUI.lb.getHolder(x, y+1).add(l, new Integer(5));
                  tempArray[i].playerUI.lb.addPeice(0, x, y+1);				    
					   tempArray[i].unitSettlerLabels.add(l);
					 }
				  }	 
				  break;
			   case 5:
	           name.playerUI.lb.addPeice(0, x+1, y+1);
			     s.setBounds(name.playerUI.lb.getSettlerCount(x+1, y+1) + name.playerUI.lb.getArmyCount(x+1, y+1));
	           s.setPosition(x+1, y+1);      
		        for(int i = 0; i < 2; i++)//number of players
		        {
                if(name != tempArray[i])
                {
                  GameJLabel l = new GameJLabel(s.getIcon());
						l.setBounds(s.getCurrentbounds());
				      l.setName(s.getLabelName());    
				      tempArray[i].playerUI.lb.getHolder(x+1, y+1).add(l, new Integer(5));
                  tempArray[i].playerUI.lb.addPeice(0, x+1, y+1);				    
					   tempArray[i].unitSettlerLabels.add(l);
					 }
				  }	 
				  break;
			   case 6:
	           name.playerUI.lb.addPeice(0, x-1, y);
			     s.setBounds(name.playerUI.lb.getSettlerCount(x-1, y) + name.playerUI.lb.getArmyCount(x-1, y));
	           s.setPosition(x-1, y);      
		        for(int i = 0; i < 2; i++)//number of players
		        {
                if(name != tempArray[i])
                {
                  GameJLabel l = new GameJLabel(s.getIcon());
						l.setBounds(s.getCurrentbounds());
				      l.setName(s.getLabelName());    
				      tempArray[i].playerUI.lb.getHolder(x-1, y).add(l, new Integer(5));
                  tempArray[i].playerUI.lb.addPeice(0, x-1, y);				    
					   tempArray[i].unitSettlerLabels.add(l);
					 }
				  }	 
				  break;
			   case 7:
				  name.playerUI.lb.addPeice(0, x+1, y);
			     s.setBounds(name.playerUI.lb.getSettlerCount(x+1, y) + name.playerUI.lb.getArmyCount(x+1, y));
	           s.setPosition(x+1, y);      
		        for(int i = 0; i < 2; i++)//number of players
		        {
                if(name != tempArray[i])
                {
                  GameJLabel l = new GameJLabel(s.getIcon());
						l.setBounds(s.getCurrentbounds());
				      l.setName(s.getLabelName());    
				      tempArray[i].playerUI.lb.getHolder(x+1, y).add(l, new Integer(5));
                  tempArray[i].playerUI.lb.addPeice(0, x+1, y);				    
					   tempArray[i].unitSettlerLabels.add(l);
					 }
				  }	 
				  break;
			 }

          name.addSettler(s); 
	       name.playerUI.lb.getHolder(s.getX(), s.getY()).add(s.getLabel(), new Integer(5));
          name.playerUI.lb.getHolder(s.getX(), s.getY()).repaint();
          SettlerActive = false;
	   
          GameJLayeredPane temp;
		    for(k = 0; k < legalSettler.size(); k++)
			 {
			   if(legalSettler.get(k) != null)
				{
				  temp = (GameJLayeredPane)legalSettler.get(k);
			     temp.remove(temp.getIndexOf(SettlerButtons[k]));
              temp.repaint();
				}    
			 }
		    Settler.removeActionListener(this);
		    if(!usedEngineering)  
			   finishCityTurn();
			 else if(builtEngineeringOne)
			   finishCityTurn();
			 else
			 {
		      engineeringProd = -6;    
				builtEngineeringOne = true;
				displayCityOptions(cityNumber);
			 }
		  }
	   }
    }

    if(e.getSource() == Army)
	 {
		legalArmy = new Vector(0, 1); 
      Vector icons = new Vector(0, 1);
      int x = name.getCityXpos(cityNumber);
      int y = name.getCityYpos(cityNumber);
		int k, i;
	   boolean waterException = false; 
		for(k = x-1; k <= x+1; k++)
		{  
		  i = y-1;
		  if(name.playerUI.lb.getTerrain(k, i).equals("Water") && !name.getWaterLand())
          waterException = true;
		  if((name.playerUI.lb.getSettlerCount(k, i) + name.playerUI.lb.getArmyCount(k, i)) < name.getStacklimit() && !waterException)
		  {
		    legalArmy.add(name.playerUI.lb.getHolder(k, i));
          icons.add(name.playerUI.lb.getIcon(k, i));  
		  }
		  else
        {
		    legalArmy.add(null);		 
          icons.add(null);    
		  }   
		  waterException = false;
		}
	   for(k = x-1; k <= x+1; k++)
		{  
		  i = y+1;
		  if(name.playerUI.lb.getTerrain(k, i).equals("Water") && !name.getWaterLand())
          waterException = true;
		  if((name.playerUI.lb.getSettlerCount(k, i) + name.playerUI.lb.getArmyCount(k, i)) < name.getStacklimit() && !waterException)
		  {
		    legalArmy.add(name.playerUI.lb.getHolder(k, i));
          icons.add(name.playerUI.lb.getIcon(k, i));  
	     }	
		  else
        {
		    legalArmy.add(null);		 
          icons.add(null);    
		  }   
		  waterException = false;
 		}
		if(name.playerUI.lb.getTerrain(x-1, y).equals("Water") && !name.getWaterLand())
         waterException = true;
		if((name.playerUI.lb.getSettlerCount(x-1, y) + name.playerUI.lb.getArmyCount(x-1, y)) < name.getStacklimit() && !waterException)
		{
		  legalArmy.add(name.playerUI.lb.getHolder(x-1, y));
        icons.add(name.playerUI.lb.getIcon(x-1, y));  
      }
		else
      {
		  legalArmy.add(null);		 
        icons.add(null);    
		}   
      waterException = false;
		if(name.playerUI.lb.getTerrain(x+1, y).equals("Water") && !name.getWaterLand())
          waterException = true;
		if((name.playerUI.lb.getSettlerCount(x+1, y) + name.playerUI.lb.getArmyCount(x+1, y)) < name.getStacklimit() && !waterException)
		{
		  legalArmy.add(name.playerUI.lb.getHolder(x+1, y));
        icons.add(name.playerUI.lb.getIcon(x+1, y));  
      }
		else
      {
		  legalArmy.add(null);		 
        icons.add(null);    
		}   
	   ArmyButtons = new GameJButton[legalArmy.size()];  
		for(k = 0; k < legalArmy.size(); k++)
		{
        if(legalArmy.get(k) != null)    
		  {
			  GameJLayeredPane temp = (GameJLayeredPane)legalArmy.get(k);
	        GameImageIcon icon = (GameImageIcon)icons.get(k);
	        ArmyButtons[k] = new GameJButton(icon);
			  ArmyButtons[k].setBounds(00, 00, icon.getIconWidth(), icon.getIconHeight());
			  temp.add(ArmyButtons[k], new Integer(4));
	        temp.repaint();
			  ArmyButtons[k].addActionListener(this);
		  }
		}
		for(k = 0; k < cityVector.size(); k++)
	   {
	     if(cityVector.get(k) != null)  
		    cityButtons[k].removeActionListener(this);
	   }  
	   GameJLabel lb_1 = new GameJLabel("Select position on map");  
      lb_1.setBounds(30, 10, 150, 15);

	   GameJLabel lb_2 = new GameJLabel("to add Army!");  
      lb_2.setBounds(45, 25, 110, 15);

		cancel = new GameJButton("cancel");
		cancel.setBounds(40, 80, 100, 20);    
		cancel.addActionListener(this);

      cityHolder.removeAll();
	   cityHolder.add(lb_1, new Integer(0));  
		cityHolder.add(lb_2, new Integer(0));  
		cityHolder.add(cancel, new Integer(0));  
		cityHolder.repaint();
      cityPanel.setPreferredSize(new Dimension(190, 110));
      cityScrollPane.revalidate();
	   
	   ArmyActive = true;
	 }
    if(ArmyActive)
    {
      for(int k = 0; k < legalArmy.size(); k++)  
	   {
        if(ArmyButtons[k] != null && e.getSource() == ArmyButtons[k])    
        {
			 Army a = new Army(name.getColor(), name.getMoveLimit(), Integer.toString(name.getArmyVector().size()+1));


          Player[] tempArray = name.getPlayerUI().getPlayers();

          int x = name.getCityXpos(cityNumber);
          int y = name.getCityYpos(cityNumber);
//add placement for all players here.			  
          switch(k)
			 {
			   case 0:
	           name.playerUI.lb.addPeice(1, x-1, y-1);
			     a.setBounds(name.playerUI.lb.getSettlerCount(x-1, y-1), name.playerUI.lb.getArmyCount(x-1, y-1));
	           a.setPosition(x-1, y-1);      
		        for(int i = 0; i < 2; i++)//number of players
		        {
                if(name != tempArray[i])
                {
                  GameJLabel l = new GameJLabel(a.getIcon());
						l.setBounds(a.getCurrentbounds());
				      l.setName(a.getLabelName());    
				      tempArray[i].playerUI.lb.getHolder(x-1, y-1).add(l, new Integer(5));
                  tempArray[i].playerUI.lb.addPeice(1, x-1, y-1);				    
					   tempArray[i].unitArmyLabels.add(l);
					 }
				  }	 
				  break;
			   case 1:
	           name.playerUI.lb.addPeice(1, x, y-1);
			     a.setBounds(name.playerUI.lb.getSettlerCount(x, y-1), name.playerUI.lb.getArmyCount(x, y-1));
	           a.setPosition(x, y-1);      
		        for(int i = 0; i < 2; i++)//number of players
		        {
                if(name != tempArray[i])
                {
                  GameJLabel l = new GameJLabel(a.getIcon());
						l.setBounds(a.getCurrentbounds());
				      l.setName(a.getLabelName());    
				      tempArray[i].playerUI.lb.getHolder(x, y-1).add(l, new Integer(5));
                  tempArray[i].playerUI.lb.addPeice(1, x, y-1);				    
					   tempArray[i].unitArmyLabels.add(l);
					 }
				  }	 
				  break;
			   case 2:
	           name.playerUI.lb.addPeice(1, x+1, y-1);
			     a.setBounds(name.playerUI.lb.getSettlerCount(x+1, y-1), name.playerUI.lb.getArmyCount(x+1, y-1));
	           a.setPosition(x+1, y-1);      
		        for(int i = 0; i < 2; i++)//number of players
		        {
                if(name != tempArray[i])
                {
                  GameJLabel l = new GameJLabel(a.getIcon());
						l.setBounds(a.getCurrentbounds());
				      l.setName(a.getLabelName());    
				      tempArray[i].playerUI.lb.getHolder(x+1, y-1).add(l, new Integer(5));
                  tempArray[i].playerUI.lb.addPeice(1, x+1, y-1);				    
					   tempArray[i].unitArmyLabels.add(l);
					 }
				  }	 
				  break;
			   case 3:
	           name.playerUI.lb.addPeice(1, x-1, y+1);
			     a.setBounds(name.playerUI.lb.getSettlerCount(x-1, y+1), name.playerUI.lb.getArmyCount(x-1, y+1));
	           a.setPosition(x-1, y+1);      
		        for(int i = 0; i < 2; i++)//number of players
		        {
                if(name != tempArray[i])
                {
                  GameJLabel l = new GameJLabel(a.getIcon());
						l.setBounds(a.getCurrentbounds());
				      l.setName(a.getLabelName());    
				      tempArray[i].playerUI.lb.getHolder(x-1, y+1).add(l, new Integer(5));
                  tempArray[i].playerUI.lb.addPeice(1, x-1, y+1);				    
					   tempArray[i].unitArmyLabels.add(l);
					 }
				  }	 
				  break;
			   case 4:
	           name.playerUI.lb.addPeice(1, x, y+1);
			     a.setBounds(name.playerUI.lb.getSettlerCount(x, y+1), name.playerUI.lb.getArmyCount(x, y+1));
	           a.setPosition(x, y+1);      
		        for(int i = 0; i < 2; i++)//number of players
		        {
                if(name != tempArray[i])
                {
                  GameJLabel l = new GameJLabel(a.getIcon());
						l.setBounds(a.getCurrentbounds());
				      l.setName(a.getLabelName());    
				      tempArray[i].playerUI.lb.getHolder(x, y+1).add(l, new Integer(5));
                  tempArray[i].playerUI.lb.addPeice(1, x, y+1);				    
					   tempArray[i].unitArmyLabels.add(l);
					 }
				  }	 
				  break;
			   case 5:
	           name.playerUI.lb.addPeice(1, x+1, y+1);
			     a.setBounds(name.playerUI.lb.getSettlerCount(x+1, y+1), name.playerUI.lb.getArmyCount(x+1, y+1));
	           a.setPosition(x+1, y+1);      
		        for(int i = 0; i < 2; i++)//number of players
		        {
                if(name != tempArray[i])
                {
                  GameJLabel l = new GameJLabel(a.getIcon());
						l.setBounds(a.getCurrentbounds());
				      l.setName(a.getLabelName());    
				      tempArray[i].playerUI.lb.getHolder(x+1, y+1).add(l, new Integer(5));
                  tempArray[i].playerUI.lb.addPeice(1, x+1, y+1);				    
					   tempArray[i].unitArmyLabels.add(l);
					 }
				  }	 
				  break;
			   case 6:
	           name.playerUI.lb.addPeice(1, x-1, y);
			     a.setBounds(name.playerUI.lb.getSettlerCount(x-1, y), name.playerUI.lb.getArmyCount(x-1, y));
	           a.setPosition(x-1, y);      
		        for(int i = 0; i < 2; i++)//number of players
		        {
                if(name != tempArray[i])
                {
                  GameJLabel l = new GameJLabel(a.getIcon());
						l.setBounds(a.getCurrentbounds());
				      l.setName(a.getLabelName());    
				      tempArray[i].playerUI.lb.getHolder(x-1, y).add(l, new Integer(5));
                  tempArray[i].playerUI.lb.addPeice(1, x-1, y);				    
					   tempArray[i].unitArmyLabels.add(l);
					 }
				  }	 
				  break;
			   case 7:
				  name.playerUI.lb.addPeice(1, x+1, y);
			     a.setBounds(name.playerUI.lb.getSettlerCount(x+1, y), name.playerUI.lb.getArmyCount(x+1, y));
	           a.setPosition(x+1, y);      
		        for(int i = 0; i < 2; i++)//number of players
		        {
                if(name != tempArray[i])
                {
                  GameJLabel l = new GameJLabel(a.getIcon());
						l.setBounds(a.getCurrentbounds());
				      l.setName(a.getLabelName());    
						tempArray[i].playerUI.lb.getHolder(x+1, y).add(l, new Integer(5));
                  tempArray[i].playerUI.lb.addPeice(1, x+1, y);				    
					   tempArray[i].unitArmyLabels.add(l);
					 }
				  }	 
				  break;
			 }

          name.addArmy(a); 
	       name.playerUI.lb.getHolder(a.getX(), a.getY()).add(a.getLabel(), new Integer(5));
          name.playerUI.lb.getHolder(a.getX(), a.getY()).repaint();
          ArmyActive = false;
	   
          GameJLayeredPane temp;
		    for(k = 0; k < legalArmy.size(); k++)
			 {
			   if(legalArmy.get(k) != null)
				{
				  temp = (GameJLayeredPane)legalArmy.get(k);
			     temp.remove(temp.getIndexOf(ArmyButtons[k]));
              temp.repaint();
				}    
			 }
		    Army.removeActionListener(this);
		    if(!usedEngineering)  
			   finishCityTurn();
			 else if(builtEngineeringOne)
			   finishCityTurn();
			 else
			 {
            engineeringProd = -4;
		      builtEngineeringOne = true;
			   displayCityOptions(cityNumber);
			 }
		  }
	   }
    }
    if(e.getSource() == aircraft)
	 {
		name.addUnitToHand(name.getPlayerUI().getAcftCard());
      aircraft.removeActionListener(this);
		    if(!usedEngineering)  
			   finishCityTurn();
			 else if(builtEngineeringOne)
			   finishCityTurn();
			 else
			 {
            engineeringProd = -12;
 		      builtEngineeringOne = true;
			   displayCityOptions(cityNumber);
			 }
    }
    if(e.getSource() == infantry)
	 {
		name.addUnitToHand(name.getPlayerUI().getInfCard(name));
      infantry.removeActionListener(this);
		    if(!usedEngineering)  
			   finishCityTurn();
			 else if(builtEngineeringOne)
			   finishCityTurn();
			 else
			 {
            engineeringProd = -(5+(2*(name.getInfLevel()-1)));
		      builtEngineeringOne = true;
			   displayCityOptions(cityNumber);
			 }
    }
    if(e.getSource() == mounted)
	 {
		name.addUnitToHand(name.getPlayerUI().getMntCard(name));
		mounted.removeActionListener(this);
		    if(!usedEngineering)  
			   finishCityTurn();
			 else if(builtEngineeringOne)
			   finishCityTurn();
			 else
			 {
            engineeringProd = -(5+(2*(name.getMntLevel()-1)));
		      builtEngineeringOne = true;
			   displayCityOptions(cityNumber);
			 }
    }
    if(e.getSource() == artillery)
	 {
		name.addUnitToHand(name.getPlayerUI().getArtCard(name));
		artillery.removeActionListener(this);
		    if(!usedEngineering)  
			   finishCityTurn();
			 else if(builtEngineeringOne)
			   finishCityTurn();
			 else
			 {
            engineeringProd = -(5+(2*(name.getArtLevel()-1)));
		      builtEngineeringOne = true;
			   displayCityOptions(cityNumber);
			 }
    }

    if(e.getSource() == harvestIncense)
	 {
      name.addmarketResource("Incense");
		finishCityTurn();
    }
    if(e.getSource() == harvestIron)
	 {
      name.addmarketResource("Iron");
		finishCityTurn();
	 }
    if(e.getSource() == harvestSilk)
	 {
      name.addmarketResource("Silk");
		finishCityTurn();
    }
    if(e.getSource() == harvestWheat)
	 {
      name.addmarketResource("Wheat");
		finishCityTurn();
    }

    if(e.getSource() == tradingPost)
	 {
		legalBuilding = new Vector(0, 1); 
      Vector icons = new Vector(0, 1);
      int x = name.getCityXpos(cityNumber);
      int y = name.getCityYpos(cityNumber);
		int k, i;
		for(k = x-1; k <= x+1; k++)
		{  
		  i = y-1;
		  if(name.playerUI.lb.getTerrain(k, i).equals("Desert") && !lb.getHasBuild(k, i))
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
		  if(name.playerUI.lb.getTerrain(k, i).equals("Desert") && !lb.getHasBuild(k, i))
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
		if(name.playerUI.lb.getTerrain(x-1, y).equals("Desert") && !lb.getHasBuild(x-1, y))
		{
		  legalBuilding.add(name.playerUI.lb.getHolder(x-1, y));
        icons.add(name.playerUI.lb.getIcon(x-1, y));  
      }
		else
      {
		  legalBuilding.add(null);		 
        icons.add(null);    
		}   
		if(name.playerUI.lb.getTerrain(x+1, y).equals("Desert") && !lb.getHasBuild(x+1, y))
		{
		  legalBuilding.add(name.playerUI.lb.getHolder(x+1, y));
        icons.add(name.playerUI.lb.getIcon(x+1, y));  
      }
		else
      {
		  legalBuilding.add(null);		 
        icons.add(null);    
		}   
	   buildingButtons = new GameJButton[legalBuilding.size()];  
		for(k = 0; k < legalBuilding.size(); k++)
		{
        if(legalBuilding.get(k) != null)    
		  {
			  GameJLayeredPane temp = (GameJLayeredPane)legalBuilding.get(k);
	        GameImageIcon icon = (GameImageIcon)icons.get(k);
	        buildingButtons[k] = new GameJButton(icon);
			  buildingButtons[k].setBounds(00, 00, icon.getIconWidth(), icon.getIconHeight());
			  temp.add(buildingButtons[k], new Integer(4));
	        temp.repaint();
			  buildingButtons[k].addActionListener(this);
		  }
		}
		for(k = 0; k < cityVector.size(); k++)
	   {
	     if(cityVector.get(k) != null)  
		    cityButtons[k].removeActionListener(this);
	   }  
	   GameJLabel lb_1 = new GameJLabel("Select position on map");  
      lb_1.setBounds(30, 10, 150, 15);

	   GameJLabel lb_2 = new GameJLabel("to add trading post!");  
      lb_2.setBounds(45, 25, 110, 15);

		cancel = new GameJButton("cancel");
		cancel.setBounds(40, 80, 100, 20);    
		cancel.addActionListener(this);

      cityHolder.removeAll();
	   cityHolder.add(lb_1, new Integer(0));  
		cityHolder.add(lb_2, new Integer(0));  
		cityHolder.add(cancel, new Integer(0));  
		cityHolder.repaint();
      cityPanel.setPreferredSize(new Dimension(190, 110));
      cityScrollPane.revalidate();
	   
	   tradingPostActive = true;
	 }
    if(tradingPostActive)
    {
      for(int k = 0; k < legalBuilding.size(); k++)  
	   {
        if(buildingButtons[k] != null && e.getSource() == buildingButtons[k])    
        {
          addPost(k);

	       name.getPlayerUI().removeBuilding("tradePost");

          tradingPostActive = false;
	   
          GameJLayeredPane temp;
		    for(k = 0; k < legalBuilding.size(); k++)
			 {
			   if(legalBuilding.get(k) != null)
				{
				  temp = (GameJLayeredPane)legalBuilding.get(k);
			     temp.remove(temp.getIndexOf(buildingButtons[k]));
              temp.repaint();
				}    
			 }
		    if(!usedEngineering)  
			   finishCityTurn();
			 else if(builtEngineeringOne)
			   finishCityTurn();
			 else
			 {
            engineeringProd = -7;
		      builtEngineeringOne = true;
			   displayCityOptions(cityNumber);
			 }
		  }
	   }
    }

    if(e.getSource() == market)
	 {
		legalBuilding = new Vector(0, 1); 
      Vector icons = new Vector(0, 1);
      int x = name.getCityXpos(cityNumber);
      int y = name.getCityYpos(cityNumber);
		int k, i;
		for(k = x-1; k <= x+1; k++)
		{  
		  i = y-1;
		  if(!lb.getTerrain(k, i).equals("Water") && !lb.getHasBuild(k, i))
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
		  if(!lb.getTerrain(k, i).equals("Water") && !lb.getHasBuild(k, i))
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
		if(!lb.getTerrain(x-1, y).equals("Water") && !lb.getHasBuild(x-1, y))
		{
		  legalBuilding.add(name.playerUI.lb.getHolder(x-1, y));
        icons.add(name.playerUI.lb.getIcon(x-1, y));  
      }
		else
      {
		  legalBuilding.add(null);		 
        icons.add(null);    
		}   
		if(!lb.getTerrain(x+1, y).equals("Water") && !lb.getHasBuild(x+1, y))
		{
		  legalBuilding.add(name.playerUI.lb.getHolder(x+1, y));
        icons.add(name.playerUI.lb.getIcon(x+1, y));  
      }
		else
      {
		  legalBuilding.add(null);		 
        icons.add(null);    
		}   
	   buildingButtons = new GameJButton[legalBuilding.size()];  
		for(k = 0; k < legalBuilding.size(); k++)
		{
        if(legalBuilding.get(k) != null)    
		  {
			  GameJLayeredPane temp = (GameJLayeredPane)legalBuilding.get(k);
	        GameImageIcon icon = (GameImageIcon)icons.get(k);
	        buildingButtons[k] = new GameJButton(icon);
			  buildingButtons[k].setBounds(00, 00, icon.getIconWidth(), icon.getIconHeight());
			  temp.add(buildingButtons[k], new Integer(4));
	        temp.repaint();
			  buildingButtons[k].addActionListener(this);
		  }
		}
		for(k = 0; k < cityVector.size(); k++)
	   {
	     if(cityVector.get(k) != null)  
		    cityButtons[k].removeActionListener(this);
	   }  
	   GameJLabel lb_1 = new GameJLabel("Select position on map");  
      lb_1.setBounds(30, 10, 150, 15);

	   GameJLabel lb_2 = new GameJLabel("to add market!");  
      lb_2.setBounds(45, 25, 110, 15);

		cancel = new GameJButton("cancel");
		cancel.setBounds(40, 80, 100, 20);    
		cancel.addActionListener(this);

      cityHolder.removeAll();
	   cityHolder.add(lb_1, new Integer(0));  
		cityHolder.add(lb_2, new Integer(0));  
		cityHolder.add(cancel, new Integer(0));  
		cityHolder.repaint();
      cityPanel.setPreferredSize(new Dimension(190, 110));
      cityScrollPane.revalidate();
	   
	   marketActive = true;
	 }
    if(marketActive)
    {
      for(int k = 0; k < legalBuilding.size(); k++)  
	   {
        if(buildingButtons[k] != null && e.getSource() == buildingButtons[k])    
        {
          addMarket(k);      

	       name.getPlayerUI().removeBuilding("market");
	       name.getPlayerUI().removeBuilding("bank");
   
          marketActive = false;
	       market.removeActionListener(this);
          GameJLayeredPane temp;
		    for(k = 0; k < legalBuilding.size(); k++)
			 {
			   if(legalBuilding.get(k) != null)
				{
				  temp = (GameJLayeredPane)legalBuilding.get(k);
			     temp.remove(temp.getIndexOf(buildingButtons[k]));
              temp.repaint();
				}    
			 }
		    if(!usedEngineering)  
			   finishCityTurn();
			 else if(builtEngineeringOne)
			   finishCityTurn();
			 else
			 {
            engineeringProd = -7;
		      builtEngineeringOne = true;
			   displayCityOptions(cityNumber);
			 }
		  }
	   }
    }

    if(e.getSource() == barracks)
	 {
		legalBuilding = new Vector(0, 1); 
      Vector icons = new Vector(0, 1);
      int x = name.getCityXpos(cityNumber);
      int y = name.getCityYpos(cityNumber);
		int k, i;
		for(k = x-1; k <= x+1; k++)
		{  
		  i = y-1;
		  if(!lb.getTerrain(k, i).equals("Water") && !lb.getHasBuild(k, i))
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
		  if(!lb.getTerrain(k, i).equals("Water") && !lb.getHasBuild(k, i))
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
		if(!lb.getTerrain(x-1, y).equals("Water") && !lb.getHasBuild(x-1, y))
		{
		  legalBuilding.add(name.playerUI.lb.getHolder(x-1, y));
        icons.add(name.playerUI.lb.getIcon(x-1, y));  
      }
		else
      {
		  legalBuilding.add(null);		 
        icons.add(null);    
		}   
		if(!lb.getTerrain(x+1, y).equals("Water") && !lb.getHasBuild(x+1, y))
		{
		  legalBuilding.add(name.playerUI.lb.getHolder(x+1, y));
        icons.add(name.playerUI.lb.getIcon(x+1, y));  
      }
		else
      {
		  legalBuilding.add(null);		 
        icons.add(null);    
		}   
	   buildingButtons = new GameJButton[legalBuilding.size()];  
		for(k = 0; k < legalBuilding.size(); k++)
		{
        if(legalBuilding.get(k) != null)    
		  {
			  GameJLayeredPane temp = (GameJLayeredPane)legalBuilding.get(k);
	        GameImageIcon icon = (GameImageIcon)icons.get(k);
	        buildingButtons[k] = new GameJButton(icon);
			  buildingButtons[k].setBounds(00, 00, icon.getIconWidth(), icon.getIconHeight());
			  temp.add(buildingButtons[k], new Integer(4));
	        temp.repaint();
			  buildingButtons[k].addActionListener(this);
		  }
		}
		for(k = 0; k < cityVector.size(); k++)
	   {
	     if(cityVector.get(k) != null)  
		    cityButtons[k].removeActionListener(this);
	   }  
	   GameJLabel lb_1 = new GameJLabel("Select position on map");  
      lb_1.setBounds(30, 10, 150, 15);

	   GameJLabel lb_2 = new GameJLabel("to add barracks!");  
      lb_2.setBounds(45, 25, 110, 15);

		cancel = new GameJButton("cancel");
		cancel.setBounds(40, 80, 100, 20);    
		cancel.addActionListener(this);

      cityHolder.removeAll();
	   cityHolder.add(lb_1, new Integer(0));  
		cityHolder.add(lb_2, new Integer(0));  
		cityHolder.add(cancel, new Integer(0));  
		cityHolder.repaint();
      cityPanel.setPreferredSize(new Dimension(190, 110));
      cityScrollPane.revalidate();
	   
	   barracksActive = true;
	 }
    if(barracksActive)
    {
      for(int k = 0; k < legalBuilding.size(); k++)  
	   {
        if(buildingButtons[k] != null && e.getSource() == buildingButtons[k])    
        {
          addBarracks(k);
			 
	       name.getPlayerUI().removeBuilding("barracks");
	       name.getPlayerUI().removeBuilding("academy");
	   
          barracksActive = false;
	       barracks.removeActionListener(this);
          GameJLayeredPane temp;
		    for(k = 0; k < legalBuilding.size(); k++)
			 {
			   if(legalBuilding.get(k) != null)
				{
				  temp = (GameJLayeredPane)legalBuilding.get(k);
			     temp.remove(temp.getIndexOf(buildingButtons[k]));
              temp.repaint();
				}    
			 }
		    if(!usedEngineering)  
			   finishCityTurn();
			 else if(builtEngineeringOne)
			   finishCityTurn();
			 else
			 {
            engineeringProd = -7;
		      builtEngineeringOne = true;
			   displayCityOptions(cityNumber);
			 }
		  }
	   }
    }

    if(e.getSource() == harbor)
	 {
		legalBuilding = new Vector(0, 1); 
      Vector icons = new Vector(0, 1);
      int x = name.getCityXpos(cityNumber);
      int y = name.getCityYpos(cityNumber);
		int k, i;
		for(k = x-1; k <= x+1; k++)
		{  
		  i = y-1;
		  if(name.playerUI.lb.getTerrain(k, i).equals("Water") && !lb.getHasBuild(k, i))
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
		  if(name.playerUI.lb.getTerrain(k, i).equals("Water") && !lb.getHasBuild(k, i))
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
		if(name.playerUI.lb.getTerrain(x-1, y).equals("Water") && !lb.getHasBuild(x-1, y))
		{
		  legalBuilding.add(name.playerUI.lb.getHolder(x-1, y));
        icons.add(name.playerUI.lb.getIcon(x-1, y));  
      }
		else
      {
		  legalBuilding.add(null);		 
        icons.add(null);    
		}   
		if(name.playerUI.lb.getTerrain(x+1, y).equals("Water") && !lb.getHasBuild(x+1, y))
		{
		  legalBuilding.add(name.playerUI.lb.getHolder(x+1, y));
        icons.add(name.playerUI.lb.getIcon(x+1, y));  
      }
		else
      {
		  legalBuilding.add(null);		 
        icons.add(null);    
		}   
	   buildingButtons = new GameJButton[legalBuilding.size()];  
		for(k = 0; k < legalBuilding.size(); k++)
		{
        if(legalBuilding.get(k) != null)    
		  {
			  GameJLayeredPane temp = (GameJLayeredPane)legalBuilding.get(k);
	        GameImageIcon icon = (GameImageIcon)icons.get(k);
	        buildingButtons[k] = new GameJButton(icon);
			  buildingButtons[k].setBounds(00, 00, icon.getIconWidth(), icon.getIconHeight());
			  temp.add(buildingButtons[k], new Integer(4));
	        temp.repaint();
			  buildingButtons[k].addActionListener(this);
		  }
		}
		for(k = 0; k < cityVector.size(); k++)
	   {
	     if(cityVector.get(k) != null)  
		    cityButtons[k].removeActionListener(this);
	   }  
	   GameJLabel lb_1 = new GameJLabel("Select position on map");  
      lb_1.setBounds(30, 10, 150, 15);

	   GameJLabel lb_2 = new GameJLabel("to add harbor!");  
      lb_2.setBounds(45, 25, 110, 15);

		cancel = new GameJButton("cancel");
		cancel.setBounds(40, 80, 100, 20);    
		cancel.addActionListener(this);

      cityHolder.removeAll();
	   cityHolder.add(lb_1, new Integer(0));  
		cityHolder.add(lb_2, new Integer(0));  
		cityHolder.add(cancel, new Integer(0));  
		cityHolder.repaint();
      cityPanel.setPreferredSize(new Dimension(190, 110));
      cityScrollPane.revalidate();
	   
	   harborActive = true;
	 }
    if(harborActive)
    {
      for(int k = 0; k < legalBuilding.size(); k++)  
	   {
        if(buildingButtons[k] != null && e.getSource() == buildingButtons[k])    
        {
          addHarbor(k);      

	       name.getPlayerUI().removeBuilding("harbor");
	   
          harborActive = false;
	   
          GameJLayeredPane temp;
		    for(k = 0; k < legalBuilding.size(); k++)
			 {
			   if(legalBuilding.get(k) != null)
				{
				  temp = (GameJLayeredPane)legalBuilding.get(k);
			     temp.remove(temp.getIndexOf(buildingButtons[k]));
              temp.repaint();
				}    
			 }
		    if(!usedEngineering)  
			   finishCityTurn();
			 else if(builtEngineeringOne)
			   finishCityTurn();
			 else
			 {
            engineeringProd = -7;
		      builtEngineeringOne = true;
			   displayCityOptions(cityNumber);
			 }
		  }
	   }
    }

    if(e.getSource() == temple)
	 {
		legalBuilding = new Vector(0, 1); 
      Vector icons = new Vector(0, 1);
      int x = name.getCityXpos(cityNumber);
      int y = name.getCityYpos(cityNumber);
		int k, i;
		for(k = x-1; k <= x+1; k++)
		{  
		  i = y-1;
		  if(!lb.getTerrain(k, i).equals("Water") && !lb.getHasBuild(k, i))
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
		  if(!lb.getTerrain(k, i).equals("Water") && !lb.getHasBuild(k, i))
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
		if(!lb.getTerrain(x-1, y).equals("Water") && !lb.getHasBuild(x-1, y))
		{
		  legalBuilding.add(name.playerUI.lb.getHolder(x-1, y));
        icons.add(name.playerUI.lb.getIcon(x-1, y));  
      }
		else
      {
		  legalBuilding.add(null);		 
        icons.add(null);    
		}   
		if(!lb.getTerrain(x+1, y).equals("Water") && !lb.getHasBuild(x+1, y))
		{
		  legalBuilding.add(name.playerUI.lb.getHolder(x+1, y));
        icons.add(name.playerUI.lb.getIcon(x+1, y));  
      }
		else
      {
		  legalBuilding.add(null);		 
        icons.add(null);    
		}   
	   buildingButtons = new GameJButton[legalBuilding.size()];  
		for(k = 0; k < legalBuilding.size(); k++)
		{
        if(legalBuilding.get(k) != null)    
		  {
			  GameJLayeredPane temp = (GameJLayeredPane)legalBuilding.get(k);
	        GameImageIcon icon = (GameImageIcon)icons.get(k);
	        buildingButtons[k] = new GameJButton(icon);
			  buildingButtons[k].setBounds(00, 00, icon.getIconWidth(), icon.getIconHeight());
			  temp.add(buildingButtons[k], new Integer(4));
	        temp.repaint();
			  buildingButtons[k].addActionListener(this);
		  }
		}
		for(k = 0; k < cityVector.size(); k++)
	   {
	     if(cityVector.get(k) != null)  
		    cityButtons[k].removeActionListener(this);
	   }  
	   GameJLabel lb_1 = new GameJLabel("Select position on map");  
      lb_1.setBounds(30, 10, 150, 15);

	   GameJLabel lb_2 = new GameJLabel("to add temple!");  
      lb_2.setBounds(45, 25, 110, 15);

		cancel = new GameJButton("cancel");
		cancel.setBounds(40, 80, 100, 20);    
		cancel.addActionListener(this);

      cityHolder.removeAll();
	   cityHolder.add(lb_1, new Integer(0));  
		cityHolder.add(lb_2, new Integer(0));  
		cityHolder.add(cancel, new Integer(0));  
		cityHolder.repaint();
      cityPanel.setPreferredSize(new Dimension(190, 110));
      cityScrollPane.revalidate();
	   
	   templeActive = true;
	 }
    if(templeActive)
    {
      Player[] tempArray = name.getPlayerUI().getPlayers();
      for(int k = 0; k < legalBuilding.size(); k++)  
	   {
        if(buildingButtons[k] != null && e.getSource() == buildingButtons[k])    
        {
          int x = name.getCityXpos(cityNumber);
          int y = name.getCityYpos(cityNumber);

          switch(k)
			 {
			   case 0:
	           name.addBuildingtoCity(cityNumber, 5, 0);      
              for(int i = 0; i < 2; i++)//number of players         
				  {
				    if(name == tempArray[i])
					 	tempArray[i].playerUI.lb.addBuilding(x-1, y-1, 5, name.getCityBuildingLabel(cityNumber, 0), false, name, cityNumber);
                else
					 	tempArray[i].playerUI.lb.addBuilding(x-1, y-1, 5, new GameJLabel(name.getCityBuildingLabel(cityNumber, 0).getIcon()), false, name, cityNumber);
				  }
				  break;
			   case 1:
	           name.addBuildingtoCity(cityNumber, 5, 1);      
              for(int i = 0; i < 2; i++)//number of players         
				  {
				    if(name == tempArray[i])
	                tempArray[i].playerUI.lb.addBuilding(x, y-1, 5, name.getCityBuildingLabel(cityNumber, 1), false, name, cityNumber);
                else
	                tempArray[i].playerUI.lb.addBuilding(x, y-1, 5, new GameJLabel(name.getCityBuildingLabel(cityNumber, 1).getIcon()), false, name, cityNumber);
				  }
				  break;
			   case 2:
	           name.addBuildingtoCity(cityNumber, 5, 2);      
              for(int i = 0; i < 2; i++)//number of players         
				  {
				    if(name == tempArray[i])
	                tempArray[i].playerUI.lb.addBuilding(x+1, y-1, 5, name.getCityBuildingLabel(cityNumber, 2), false, name, cityNumber);
                else
	                tempArray[i].playerUI.lb.addBuilding(x+1, y-1, 5, new GameJLabel(name.getCityBuildingLabel(cityNumber, 2).getIcon()), false, name, cityNumber);
				  }
				  break;
			   case 3:
	           name.addBuildingtoCity(cityNumber, 5, 3);      
              for(int i = 0; i < 2; i++)//number of players         
				  {
				    if(name == tempArray[i])
	                tempArray[i].playerUI.lb.addBuilding(x-1, y+1, 5, name.getCityBuildingLabel(cityNumber, 3), false, name, cityNumber);
                else
	                tempArray[i].playerUI.lb.addBuilding(x-1, y+1, 5, new GameJLabel(name.getCityBuildingLabel(cityNumber, 3).getIcon()), false, name, cityNumber);
				  }
				  break;
			   case 4:
	           name.addBuildingtoCity(cityNumber, 5, 4);      
              for(int i = 0; i < 2; i++)//number of players         
				  {
				    if(name == tempArray[i])
	                tempArray[i].playerUI.lb.addBuilding(x, y+1, 5, name.getCityBuildingLabel(cityNumber, 4), false, name, cityNumber);
                else
	                tempArray[i].playerUI.lb.addBuilding(x, y+1, 5, new GameJLabel(name.getCityBuildingLabel(cityNumber, 4).getIcon()), false, name, cityNumber);
				  }
				  break;
			   case 5:
	           name.addBuildingtoCity(cityNumber, 5, 5);      
              for(int i = 0; i < 2; i++)//number of players         
				  {
				    if(name == tempArray[i])
	                tempArray[i].playerUI.lb.addBuilding(x+1, y+1, 5, name.getCityBuildingLabel(cityNumber, 5), false, name, cityNumber);
                else
	                tempArray[i].playerUI.lb.addBuilding(x+1, y+1, 5, new GameJLabel(name.getCityBuildingLabel(cityNumber, 5).getIcon()), false, name, cityNumber);
				  }
				  break;
			   case 6:
	           name.addBuildingtoCity(cityNumber, 5, 6);      
              for(int i = 0; i < 2; i++)//number of players         
				  {
				    if(name == tempArray[i])
	               tempArray[i].playerUI.lb.addBuilding(x-1, y, 5, name.getCityBuildingLabel(cityNumber, 6), false, name, cityNumber);
                else
	               tempArray[i].playerUI.lb.addBuilding(x-1, y, 5, new GameJLabel(name.getCityBuildingLabel(cityNumber, 6).getIcon()), false, name, cityNumber);
				  }
				  break;
			   case 7:
	           name.addBuildingtoCity(cityNumber, 5, 7);      
              for(int i = 0; i < 2; i++)//number of players         
				  {
				    if(name == tempArray[i])
	                tempArray[i].playerUI.lb.addBuilding(x+1, y, 5, name.getCityBuildingLabel(cityNumber, 7), false, name, cityNumber);
                else
	                tempArray[i].playerUI.lb.addBuilding(x+1, y, 5, new GameJLabel(name.getCityBuildingLabel(cityNumber, 7).getIcon()), false, name, cityNumber);
				  }
				  break;
			 }
	       name.getPlayerUI().removeBuilding("temple");
	       name.getPlayerUI().removeBuilding("cathedral");
	   
          templeActive = false;
	       temple.removeActionListener(this);
          GameJLayeredPane temp;
		    for(k = 0; k < legalBuilding.size(); k++)
			 {
			   if(legalBuilding.get(k) != null)
				{
				  temp = (GameJLayeredPane)legalBuilding.get(k);
			     temp.remove(temp.getIndexOf(buildingButtons[k]));
              temp.repaint();
				}    
			 }
		    if(!usedEngineering)  
			   finishCityTurn();
			 else if(builtEngineeringOne)
			   finishCityTurn();
			 else
			 {
            engineeringProd = -7;
		      builtEngineeringOne = true;
			   displayCityOptions(cityNumber);
			 }
		  }
	   }
    }

    if(e.getSource() == granary)
	 {
		legalBuilding = new Vector(0, 1); 
      Vector icons = new Vector(0, 1);
      int x = name.getCityXpos(cityNumber);
      int y = name.getCityYpos(cityNumber);
		int k, i;
		for(k = x-1; k <= x+1; k++)
		{  
		  i = y-1;
		  if(name.playerUI.lb.getTerrain(k, i).equals("Grass") && !lb.getHasBuild(k, i))
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
		  if(name.playerUI.lb.getTerrain(k, i).equals("Grass") && !lb.getHasBuild(k, i))
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
		if(name.playerUI.lb.getTerrain(x-1, y).equals("Grass") && !lb.getHasBuild(x-1, y))
		{
		  legalBuilding.add(name.playerUI.lb.getHolder(x-1, y));
        icons.add(name.playerUI.lb.getIcon(x-1, y));  
      }
		else
      {
		  legalBuilding.add(null);		 
        icons.add(null);    
		}   
		if(name.playerUI.lb.getTerrain(x+1, y).equals("Grass") && !lb.getHasBuild(x+1, y))
		{
		  legalBuilding.add(name.playerUI.lb.getHolder(x+1, y));
        icons.add(name.playerUI.lb.getIcon(x+1, y));  
      }
		else
      {
		  legalBuilding.add(null);		 
        icons.add(null);    
		}   
	   buildingButtons = new GameJButton[legalBuilding.size()];  
		for(k = 0; k < legalBuilding.size(); k++)
		{
        if(legalBuilding.get(k) != null)    
		  {
			  GameJLayeredPane temp = (GameJLayeredPane)legalBuilding.get(k);
	        GameImageIcon icon = (GameImageIcon)icons.get(k);
	        buildingButtons[k] = new GameJButton(icon);
			  buildingButtons[k].setBounds(00, 00, icon.getIconWidth(), icon.getIconHeight());
			  temp.add(buildingButtons[k], new Integer(4));
	        temp.repaint();
			  buildingButtons[k].addActionListener(this);
		  }
		}
		for(k = 0; k < cityVector.size(); k++)
	   {
	     if(cityVector.get(k) != null)  
		    cityButtons[k].removeActionListener(this);
	   }  
	   GameJLabel lb_1 = new GameJLabel("Select position on map");  
      lb_1.setBounds(30, 10, 150, 15);

	   GameJLabel lb_2 = new GameJLabel("to add granary!");  
      lb_2.setBounds(45, 25, 110, 15);

		cancel = new GameJButton("cancel");
		cancel.setBounds(40, 80, 100, 20);    
		cancel.addActionListener(this);

      cityHolder.removeAll();
	   cityHolder.add(lb_1, new Integer(0));  
		cityHolder.add(lb_2, new Integer(0));  
		cityHolder.add(cancel, new Integer(0));  
		cityHolder.repaint();
      cityPanel.setPreferredSize(new Dimension(190, 110));
      cityScrollPane.revalidate();
	   
	   granaryActive = true;
	 }
    if(granaryActive)
    {
      for(int k = 0; k < legalBuilding.size(); k++)  
	   {
        if(buildingButtons[k] != null && e.getSource() == buildingButtons[k])    
        {
          addGranary(k);      

	       name.getPlayerUI().removeBuilding("granary");
	       name.getPlayerUI().removeBuilding("aqueduct");
	   
          granaryActive = false;
	   
          GameJLayeredPane temp;
		    for(k = 0; k < legalBuilding.size(); k++)
			 {
			   if(legalBuilding.get(k) != null)
				{
				  temp = (GameJLayeredPane)legalBuilding.get(k);
			     temp.remove(temp.getIndexOf(buildingButtons[k]));
              temp.repaint();
				}    
			 }
		    if(!usedEngineering)  
			   finishCityTurn();
			 else if(builtEngineeringOne)
			   finishCityTurn();
			 else
			 {
            engineeringProd = -5;
		      builtEngineeringOne = true;
			   displayCityOptions(cityNumber);
			 }
		  }
	   }
    }

    if(e.getSource() == library)
	 {
		legalBuilding = new Vector(0, 1); 
      Vector icons = new Vector(0, 1);
      int x = name.getCityXpos(cityNumber);
      int y = name.getCityYpos(cityNumber);
		int k, i;
		for(k = x-1; k <= x+1; k++)
		{  
		  i = y-1;
		  if(name.playerUI.lb.getTerrain(k, i).equals("Grass") && !lb.getHasBuild(k, i))
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
		  if(name.playerUI.lb.getTerrain(k, i).equals("Grass") && !lb.getHasBuild(k, i))
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
		if(name.playerUI.lb.getTerrain(x-1, y).equals("Grass") && !lb.getHasBuild(x-1, y))
		{
		  legalBuilding.add(name.playerUI.lb.getHolder(x-1, y));
        icons.add(name.playerUI.lb.getIcon(x-1, y));  
      }
		else
      {
		  legalBuilding.add(null);		 
        icons.add(null);    
		}   
		if(name.playerUI.lb.getTerrain(x+1, y).equals("Grass") && !lb.getHasBuild(x+1, y))
		{
		  legalBuilding.add(name.playerUI.lb.getHolder(x+1, y));
        icons.add(name.playerUI.lb.getIcon(x+1, y));  
      }
		else
      {
		  legalBuilding.add(null);		 
        icons.add(null);    
		}   
	   buildingButtons = new GameJButton[legalBuilding.size()];  
		for(k = 0; k < legalBuilding.size(); k++)
		{
        if(legalBuilding.get(k) != null)    
		  {
			  GameJLayeredPane temp = (GameJLayeredPane)legalBuilding.get(k);
	        GameImageIcon icon = (GameImageIcon)icons.get(k);
	        buildingButtons[k] = new GameJButton(icon);
			  buildingButtons[k].setBounds(00, 00, icon.getIconWidth(), icon.getIconHeight());
			  temp.add(buildingButtons[k], new Integer(4));
	        temp.repaint();
			  buildingButtons[k].addActionListener(this);
		  }
		}
		for(k = 0; k < cityVector.size(); k++)
	   {
	     if(cityVector.get(k) != null)  
		    cityButtons[k].removeActionListener(this);
	   }  
	   GameJLabel lb_1 = new GameJLabel("Select position on map");  
      lb_1.setBounds(30, 10, 150, 15);

	   GameJLabel lb_2 = new GameJLabel("to add library!");  
      lb_2.setBounds(45, 25, 110, 15);

		cancel = new GameJButton("cancel");
		cancel.setBounds(40, 80, 100, 20);    
		cancel.addActionListener(this);

      cityHolder.removeAll();
	   cityHolder.add(lb_1, new Integer(0));  
		cityHolder.add(lb_2, new Integer(0));  
		cityHolder.add(cancel, new Integer(0));  
		cityHolder.repaint();
      cityPanel.setPreferredSize(new Dimension(190, 110));
      cityScrollPane.revalidate();
	   
	   libraryActive = true;
	 }
    if(libraryActive)
    {
      for(int k = 0; k < legalBuilding.size(); k++)  
	   {
        if(buildingButtons[k] != null && e.getSource() == buildingButtons[k])    
        {
          addLibrary(k);
			 
	       name.getPlayerUI().removeBuilding("library");
	       name.getPlayerUI().removeBuilding("university");
	   
          libraryActive = false;
	   
          GameJLayeredPane temp;
		    for(k = 0; k < legalBuilding.size(); k++)
			 {
			   if(legalBuilding.get(k) != null)
				{
				  temp = (GameJLayeredPane)legalBuilding.get(k);
			     temp.remove(temp.getIndexOf(buildingButtons[k]));
              temp.repaint();
				}    
			 }
		    if(!usedEngineering)  
			   finishCityTurn();
			 else if(builtEngineeringOne)
			   finishCityTurn();
			 else
			 {
            engineeringProd = -5;
		      builtEngineeringOne = true;
			   displayCityOptions(cityNumber);
			 }
		  }
	   }
    }

    if(e.getSource() == workshop)
	 {
		legalBuilding = new Vector(0, 1); 
      Vector icons = new Vector(0, 1);
      int x = name.getCityXpos(cityNumber);
      int y = name.getCityYpos(cityNumber);
		int k, i;
		for(k = x-1; k <= x+1; k++)
		{  
		  i = y-1;
		  if(name.playerUI.lb.getTerrain(k, i).equals("Mountain") && !lb.getHasBuild(k, i))
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
		  if(name.playerUI.lb.getTerrain(k, i).equals("Mountain") && !lb.getHasBuild(k, i))
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
		if(name.playerUI.lb.getTerrain(x-1, y).equals("Mountain") && !lb.getHasBuild(x-1, y))
		{
		  legalBuilding.add(name.playerUI.lb.getHolder(x-1, y));
        icons.add(name.playerUI.lb.getIcon(x-1, y));  
      }
		else
      {
		  legalBuilding.add(null);		 
        icons.add(null);    
		}   
		if(name.playerUI.lb.getTerrain(x+1, y).equals("Mountain") && !lb.getHasBuild(x+1, y))
		{
		  legalBuilding.add(name.playerUI.lb.getHolder(x+1, y));
        icons.add(name.playerUI.lb.getIcon(x+1, y));  
      }
		else
      {
		  legalBuilding.add(null);		 
        icons.add(null);    
		}   
	   buildingButtons = new GameJButton[legalBuilding.size()];  
		for(k = 0; k < legalBuilding.size(); k++)
		{
        if(legalBuilding.get(k) != null)    
		  {
			  GameJLayeredPane temp = (GameJLayeredPane)legalBuilding.get(k);
	        GameImageIcon icon = (GameImageIcon)icons.get(k);
	        buildingButtons[k] = new GameJButton(icon);
			  buildingButtons[k].setBounds(00, 00, icon.getIconWidth(), icon.getIconHeight());
			  temp.add(buildingButtons[k], new Integer(4));
	        temp.repaint();
			  buildingButtons[k].addActionListener(this);
		  }
		}
		for(k = 0; k < cityVector.size(); k++)
	   {
	     if(cityVector.get(k) != null)  
		    cityButtons[k].removeActionListener(this);
	   }  
	   GameJLabel lb_1 = new GameJLabel("Select position on map");  
      lb_1.setBounds(30, 10, 150, 15);

	   GameJLabel lb_2 = new GameJLabel("to add workshop!");  
      lb_2.setBounds(45, 25, 110, 15);

		cancel = new GameJButton("cancel");
		cancel.setBounds(40, 80, 100, 20);    
		cancel.addActionListener(this);

      cityHolder.removeAll();
	   cityHolder.add(lb_1, new Integer(0));  
		cityHolder.add(lb_2, new Integer(0));  
		cityHolder.add(cancel, new Integer(0));  
		cityHolder.repaint();
      cityPanel.setPreferredSize(new Dimension(190, 110));
      cityScrollPane.revalidate();
	   
	   workshopActive = true;
	 }
    if(workshopActive)
    {
      Player[] tempArray = name.getPlayerUI().getPlayers();
      for(int k = 0; k < legalBuilding.size(); k++)  
	   {
        if(buildingButtons[k] != null && e.getSource() == buildingButtons[k])    
        {
          int x = name.getCityXpos(cityNumber);
          int y = name.getCityYpos(cityNumber);

          switch(k)
			 {
			   case 0:
	           name.addBuildingtoCity(cityNumber, 7, 0);      
              for(int i = 0; i < 2; i++)//number of players         
				  {
				    if(name == tempArray[i])
						 tempArray[i].playerUI.lb.addBuilding(x-1, y-1, 7, name.getCityBuildingLabel(cityNumber, 0), false, name, cityNumber);
                else
						 tempArray[i].playerUI.lb.addBuilding(x-1, y-1, 7, new GameJLabel(name.getCityBuildingLabel(cityNumber, 0).getIcon()), false, name, cityNumber);
				  }
				  break;
			   case 1:
	           name.addBuildingtoCity(cityNumber, 7, 1);      
              for(int i = 0; i < 2; i++)//number of players         
				  {
				    if(name == tempArray[i])
	                tempArray[i].playerUI.lb.addBuilding(x, y-1, 7, name.getCityBuildingLabel(cityNumber, 1), false, name, cityNumber);
                else
	                tempArray[i].playerUI.lb.addBuilding(x, y-1, 7, new GameJLabel(name.getCityBuildingLabel(cityNumber, 1).getIcon()), false, name, cityNumber);
				  }
				  break;
			   case 2:
	           name.addBuildingtoCity(cityNumber, 7, 2);      
              for(int i = 0; i < 2; i++)//number of players         
				  {
				    if(name == tempArray[i])
	                tempArray[i].playerUI.lb.addBuilding(x+1, y-1, 7, name.getCityBuildingLabel(cityNumber, 2), false, name, cityNumber);
                else
	                tempArray[i].playerUI.lb.addBuilding(x+1, y-1, 7, new GameJLabel(name.getCityBuildingLabel(cityNumber, 2).getIcon()), false, name, cityNumber);
				  }
				  break;
			   case 3:
	           name.addBuildingtoCity(cityNumber, 7, 3);      
              for(int i = 0; i < 2; i++)//number of players         
				  {
				    if(name == tempArray[i])
	                tempArray[i].playerUI.lb.addBuilding(x-1, y+1, 7, name.getCityBuildingLabel(cityNumber, 3), false, name, cityNumber);
                else
	                tempArray[i].playerUI.lb.addBuilding(x-1, y+1, 7, new GameJLabel(name.getCityBuildingLabel(cityNumber, 3).getIcon()), false, name, cityNumber);
				  }
				  break;
			   case 4:
	           name.addBuildingtoCity(cityNumber, 7, 4);      
              for(int i = 0; i < 2; i++)//number of players         
				  {
				    if(name == tempArray[i])
	                tempArray[i].playerUI.lb.addBuilding(x, y+1, 7, name.getCityBuildingLabel(cityNumber, 4), false, name, cityNumber);
                else
	                tempArray[i].playerUI.lb.addBuilding(x, y+1, 7, new GameJLabel(name.getCityBuildingLabel(cityNumber, 4).getIcon()), false, name, cityNumber);
				  }
				  break;
			   case 5:
	           name.addBuildingtoCity(cityNumber, 7, 5);      
              for(int i = 0; i < 2; i++)//number of players         
				  {
				    if(name == tempArray[i])
	                tempArray[i].playerUI.lb.addBuilding(x+1, y+1, 7, name.getCityBuildingLabel(cityNumber, 5), false, name, cityNumber);
                else
	                tempArray[i].playerUI.lb.addBuilding(x+1, y+1, 7, new GameJLabel(name.getCityBuildingLabel(cityNumber, 5).getIcon()), false, name, cityNumber);
				  }
				  break;
			   case 6:
	           name.addBuildingtoCity(cityNumber, 7, 6);      
              for(int i = 0; i < 2; i++)//number of players         
				  {
				    if(name == tempArray[i])
	                tempArray[i].playerUI.lb.addBuilding(x-1, y, 7, name.getCityBuildingLabel(cityNumber, 6), false, name, cityNumber);
                else
	                tempArray[i].playerUI.lb.addBuilding(x-1, y, 7, new GameJLabel(name.getCityBuildingLabel(cityNumber, 6).getIcon()), false, name, cityNumber);
				  }
				  break;
			   case 7:
	           name.addBuildingtoCity(cityNumber, 7, 7);      
              for(int i = 0; i < 2; i++)//number of players         
				  {
				    if(name == tempArray[i])
	                tempArray[i].playerUI.lb.addBuilding(x+1, y, 7, name.getCityBuildingLabel(cityNumber, 7), false, name, cityNumber);
                else
	                tempArray[i].playerUI.lb.addBuilding(x+1, y, 7, new GameJLabel(name.getCityBuildingLabel(cityNumber, 7).getIcon()), false, name, cityNumber);
				  }
				  break;
			 }
	       name.getPlayerUI().removeBuilding("workshop");
	       name.getPlayerUI().removeBuilding("ironmine");
	   
          workshopActive = false;
	   
          GameJLayeredPane temp;
		    for(k = 0; k < legalBuilding.size(); k++)
			 {
			   if(legalBuilding.get(k) != null)
				{
				  temp = (GameJLayeredPane)legalBuilding.get(k);
			     temp.remove(temp.getIndexOf(buildingButtons[k]));
              temp.repaint();
				}    
			 }
		    if(!usedEngineering)  
			   finishCityTurn();
			 else if(builtEngineeringOne)
			   finishCityTurn();
			 else
			 {
            engineeringProd = -7;
		      builtEngineeringOne = true;
			   displayCityOptions(cityNumber);
			 }
		  }
	   }
    }

    if(e.getSource() == aqueduct)
	 {
		legalBuilding = new Vector(0, 1); 
      Vector icons = new Vector(0, 1);
      int x = name.getCityXpos(cityNumber);
      int y = name.getCityYpos(cityNumber);
		int k, i;
		for(k = x-1; k <= x+1; k++)
		{  
		  i = y-1;
		  if(name.playerUI.lb.getTerrain(k, i).equals("Grass") && !lb.getHasBuild(k, i))
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
		  if(name.playerUI.lb.getTerrain(k, i).equals("Grass") && !lb.getHasBuild(k, i))
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
		if(name.playerUI.lb.getTerrain(x-1, y).equals("Grass") && !lb.getHasBuild(x-1, y))
		{
		  legalBuilding.add(name.playerUI.lb.getHolder(x-1, y));
        icons.add(name.playerUI.lb.getIcon(x-1, y));  
      }
		else
      {
		  legalBuilding.add(null);		 
        icons.add(null);    
		}   
		if(name.playerUI.lb.getTerrain(x+1, y).equals("Grass") && !lb.getHasBuild(x+1, y))
		{
		  legalBuilding.add(name.playerUI.lb.getHolder(x+1, y));
        icons.add(name.playerUI.lb.getIcon(x+1, y));  
      }
		else
      {
		  legalBuilding.add(null);		 
        icons.add(null);    
		}   
	   buildingButtons = new GameJButton[legalBuilding.size()];  
		for(k = 0; k < legalBuilding.size(); k++)
		{
        if(legalBuilding.get(k) != null)    
		  {
			  GameJLayeredPane temp = (GameJLayeredPane)legalBuilding.get(k);
	        GameImageIcon icon = (GameImageIcon)icons.get(k);
	        buildingButtons[k] = new GameJButton(icon);
			  buildingButtons[k].setBounds(00, 00, icon.getIconWidth(), icon.getIconHeight());
			  temp.add(buildingButtons[k], new Integer(4));
	        temp.repaint();
			  buildingButtons[k].addActionListener(this);
		  }
		}
		for(k = 0; k < cityVector.size(); k++)
	   {
	     if(cityVector.get(k) != null)  
		    cityButtons[k].removeActionListener(this);
	   }  
	   GameJLabel lb_1 = new GameJLabel("Select position on map");  
      lb_1.setBounds(30, 10, 150, 15);

	   GameJLabel lb_2 = new GameJLabel("to add aqueduct!");  
      lb_2.setBounds(45, 25, 110, 15);

		cancel = new GameJButton("cancel");
		cancel.setBounds(40, 80, 100, 20);    
		cancel.addActionListener(this);

      cityHolder.removeAll();
	   cityHolder.add(lb_1, new Integer(0));  
		cityHolder.add(lb_2, new Integer(0));  
		cityHolder.add(cancel, new Integer(0));  
		cityHolder.repaint();
      cityPanel.setPreferredSize(new Dimension(190, 110));
      cityScrollPane.revalidate();
	   
	   aqueductActive = true;
	 }
    if(aqueductActive)
    {
      Player[] tempArray = name.getPlayerUI().getPlayers();
      for(int k = 0; k < legalBuilding.size(); k++)  
	   {
        if(buildingButtons[k] != null && e.getSource() == buildingButtons[k])    
        {
          int x = name.getCityXpos(cityNumber);
          int y = name.getCityYpos(cityNumber);

          switch(k)
			 {
			   case 0:
	           name.addBuildingtoCity(cityNumber, 9, 0);      
              for(int i = 0; i < 2; i++)//number of players         
				  {
				    if(name == tempArray[i])
						 tempArray[i].playerUI.lb.addBuilding(x-1, y-1, 9, name.getCityBuildingLabel(cityNumber, 0), false, name, cityNumber);
                else
						 tempArray[i].playerUI.lb.addBuilding(x-1, y-1, 9, new GameJLabel(name.getCityBuildingLabel(cityNumber, 0).getIcon()), false, name, cityNumber);
				  }
				  break;
			   case 1:
	           name.addBuildingtoCity(cityNumber, 9, 1);      
              for(int i = 0; i < 2; i++)//number of players         
				  {
				    if(name == tempArray[i])
	                tempArray[i].playerUI.lb.addBuilding(x, y-1, 9, name.getCityBuildingLabel(cityNumber, 1), false, name, cityNumber);
                else
	                tempArray[i].playerUI.lb.addBuilding(x, y-1, 9, new GameJLabel(name.getCityBuildingLabel(cityNumber, 1).getIcon()), false, name, cityNumber);
				  }
				  break;
			   case 2:
	           name.addBuildingtoCity(cityNumber, 9, 2);      
              for(int i = 0; i < 2; i++)//number of players         
				  {
				    if(name == tempArray[i])
	                tempArray[i].playerUI.lb.addBuilding(x+1, y-1, 9, name.getCityBuildingLabel(cityNumber, 2), false, name, cityNumber);
                else
	                tempArray[i].playerUI.lb.addBuilding(x+1, y-1, 9, new GameJLabel(name.getCityBuildingLabel(cityNumber, 2).getIcon()), false, name, cityNumber);
				  }
				  break;
			   case 3:
	           name.addBuildingtoCity(cityNumber, 9, 3);      
              for(int i = 0; i < 2; i++)//number of players         
				  {
				    if(name == tempArray[i])
	                tempArray[i].playerUI.lb.addBuilding(x-1, y+1, 9, name.getCityBuildingLabel(cityNumber, 3), false, name, cityNumber);
                else
	                tempArray[i].playerUI.lb.addBuilding(x-1, y+1, 9, new GameJLabel(name.getCityBuildingLabel(cityNumber, 3).getIcon()), false, name, cityNumber);
				  }
				  break;
			   case 4:
	           name.addBuildingtoCity(cityNumber, 9, 4);      
              for(int i = 0; i < 2; i++)//number of players         
				  {
				    if(name == tempArray[i])
	                tempArray[i].playerUI.lb.addBuilding(x, y+1, 9, name.getCityBuildingLabel(cityNumber, 4), false, name, cityNumber);
                else
	                tempArray[i].playerUI.lb.addBuilding(x, y+1, 9, new GameJLabel(name.getCityBuildingLabel(cityNumber, 4).getIcon()), false, name, cityNumber);
				  }
				  break;
			   case 5:
	           name.addBuildingtoCity(cityNumber, 9, 5);      
              for(int i = 0; i < 2; i++)//number of players         
				  {
				    if(name == tempArray[i])
	                tempArray[i].playerUI.lb.addBuilding(x+1, y+1, 9, name.getCityBuildingLabel(cityNumber, 5), false, name, cityNumber);
                else
	                tempArray[i].playerUI.lb.addBuilding(x+1, y+1, 9, new GameJLabel(name.getCityBuildingLabel(cityNumber, 5).getIcon()), false, name, cityNumber);
				  }
				  break;
			   case 6:
	           name.addBuildingtoCity(cityNumber, 9, 6);      
              for(int i = 0; i < 2; i++)//number of players         
				  {
				    if(name == tempArray[i])
	                tempArray[i].playerUI.lb.addBuilding(x-1, y, 9, name.getCityBuildingLabel(cityNumber, 6), false, name, cityNumber);
                else
	                tempArray[i].playerUI.lb.addBuilding(x-1, y, 9, new GameJLabel(name.getCityBuildingLabel(cityNumber, 6).getIcon()), false, name, cityNumber);
				  }
				  break;
			   case 7:
	           name.addBuildingtoCity(cityNumber, 9, 7);      
              for(int i = 0; i < 2; i++)//number of players         
				  {
				    if(name == tempArray[i])
	                tempArray[i].playerUI.lb.addBuilding(x+1, y, 9, name.getCityBuildingLabel(cityNumber, 7), false, name, cityNumber);
                else
	                tempArray[i].playerUI.lb.addBuilding(x+1, y, 9, new GameJLabel(name.getCityBuildingLabel(cityNumber, 7).getIcon()), false, name, cityNumber);
				  }
				  break;
			 }
	       name.getPlayerUI().removeBuilding("aqueduct");
	       name.getPlayerUI().removeBuilding("granary");
	   
          aqueductActive = false;
	   
          GameJLayeredPane temp;
		    for(k = 0; k < legalBuilding.size(); k++)
			 {
			   if(legalBuilding.get(k) != null)
				{
				  temp = (GameJLayeredPane)legalBuilding.get(k);
			     temp.remove(temp.getIndexOf(buildingButtons[k]));
              temp.repaint();
				}    
			 }
		    if(!usedEngineering)  
			   finishCityTurn();
			 else if(builtEngineeringOne)
			   finishCityTurn();
			 else
			 {
            engineeringProd = -8;
		      builtEngineeringOne = true;
			   displayCityOptions(cityNumber);
			 }
		  }
	   }
    }

    if(e.getSource() == university)
	 {
		legalBuilding = new Vector(0, 1); 
      Vector icons = new Vector(0, 1);
      int x = name.getCityXpos(cityNumber);
      int y = name.getCityYpos(cityNumber);
		int k, i;
		for(k = x-1; k <= x+1; k++)
		{  
		  i = y-1;
		  if(name.playerUI.lb.getTerrain(k, i).equals("Grass") && !lb.getHasBuild(k, i))
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
		  if(name.playerUI.lb.getTerrain(k, i).equals("Grass") && !lb.getHasBuild(k, i))
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
		if(name.playerUI.lb.getTerrain(x-1, y).equals("Grass") && !lb.getHasBuild(x-1, y))
		{
		  legalBuilding.add(name.playerUI.lb.getHolder(x-1, y));
        icons.add(name.playerUI.lb.getIcon(x-1, y));  
      }
		else
      {
		  legalBuilding.add(null);		 
        icons.add(null);    
		}   
		if(name.playerUI.lb.getTerrain(x+1, y).equals("Grass") && !lb.getHasBuild(x+1, y))
		{
		  legalBuilding.add(name.playerUI.lb.getHolder(x+1, y));
        icons.add(name.playerUI.lb.getIcon(x+1, y));  
      }
		else
      {
		  legalBuilding.add(null);		 
        icons.add(null);    
		}   
	   buildingButtons = new GameJButton[legalBuilding.size()];  
		for(k = 0; k < legalBuilding.size(); k++)
		{
        if(legalBuilding.get(k) != null)    
		  {
			  GameJLayeredPane temp = (GameJLayeredPane)legalBuilding.get(k);
	        GameImageIcon icon = (GameImageIcon)icons.get(k);
	        buildingButtons[k] = new GameJButton(icon);
			  buildingButtons[k].setBounds(00, 00, icon.getIconWidth(), icon.getIconHeight());
			  temp.add(buildingButtons[k], new Integer(4));
	        temp.repaint();
			  buildingButtons[k].addActionListener(this);
		  }
		}
		for(k = 0; k < cityVector.size(); k++)
	   {
	     if(cityVector.get(k) != null)  
		    cityButtons[k].removeActionListener(this);
	   }  
	   GameJLabel lb_1 = new GameJLabel("Select position on map");  
      lb_1.setBounds(30, 10, 150, 15);

	   GameJLabel lb_2 = new GameJLabel("to add university!");  
      lb_2.setBounds(45, 25, 110, 15);

		cancel = new GameJButton("cancel");
		cancel.setBounds(40, 80, 100, 20);    
		cancel.addActionListener(this);

      cityHolder.removeAll();
	   cityHolder.add(lb_1, new Integer(0));  
		cityHolder.add(lb_2, new Integer(0));  
		cityHolder.add(cancel, new Integer(0));  
		cityHolder.repaint();
      cityPanel.setPreferredSize(new Dimension(190, 110));
      cityScrollPane.revalidate();
	   
	   universityActive = true;
	 }
    if(universityActive)
    {
      Player[] tempArray = name.getPlayerUI().getPlayers();
      for(int k = 0; k < legalBuilding.size(); k++)  
	   {
        if(buildingButtons[k] != null && e.getSource() == buildingButtons[k])    
        {
          int x = name.getCityXpos(cityNumber);
          int y = name.getCityYpos(cityNumber);

          switch(k)
			 {
			   case 0:
	           name.addBuildingtoCity(cityNumber, 10, 0);      
              for(int i = 0; i < 2; i++)//number of players         
				  {
				    if(name == tempArray[i])
					 	tempArray[i].playerUI.lb.addBuilding(x-1, y-1, 10, name.getCityBuildingLabel(cityNumber, 0), false, name, cityNumber);
                else
					 	tempArray[i].playerUI.lb.addBuilding(x-1, y-1, 10, new GameJLabel(name.getCityBuildingLabel(cityNumber, 0).getIcon()), false, name, cityNumber);
				  }
				  break;
			   case 1:
	           name.addBuildingtoCity(cityNumber, 10, 1);      
              for(int i = 0; i < 2; i++)//number of players         
				  {
				    if(name == tempArray[i])
	                tempArray[i].playerUI.lb.addBuilding(x, y-1, 10, name.getCityBuildingLabel(cityNumber, 1), false, name, cityNumber);
                else
	                tempArray[i].playerUI.lb.addBuilding(x, y-1, 10, new GameJLabel(name.getCityBuildingLabel(cityNumber, 1).getIcon()), false, name, cityNumber);
				  }
				  break;
			   case 2:
	           name.addBuildingtoCity(cityNumber, 10, 2);      
              for(int i = 0; i < 2; i++)//number of players         
				  {
				    if(name == tempArray[i])
	                tempArray[i].playerUI.lb.addBuilding(x+1, y-1, 10, name.getCityBuildingLabel(cityNumber, 2), false, name, cityNumber);
                else
	                tempArray[i].playerUI.lb.addBuilding(x+1, y-1, 10, new GameJLabel(name.getCityBuildingLabel(cityNumber, 2).getIcon()), false, name, cityNumber);
				  }
				  break;
			   case 3:
	           name.addBuildingtoCity(cityNumber, 10, 3);      
              for(int i = 0; i < 2; i++)//number of players         
				  {
				    if(name == tempArray[i])
	                tempArray[i].playerUI.lb.addBuilding(x-1, y+1, 10, name.getCityBuildingLabel(cityNumber, 3), false, name, cityNumber);
                else
	                tempArray[i].playerUI.lb.addBuilding(x-1, y+1, 10, new GameJLabel(name.getCityBuildingLabel(cityNumber, 3).getIcon()), false, name, cityNumber);
				  }
				  break;
			   case 4:
	           name.addBuildingtoCity(cityNumber, 10, 4);      
              for(int i = 0; i < 2; i++)//number of players         
				  {
				    if(name == tempArray[i])
	                tempArray[i].playerUI.lb.addBuilding(x, y+1, 10, name.getCityBuildingLabel(cityNumber, 4), false, name, cityNumber);
                else
	                tempArray[i].playerUI.lb.addBuilding(x, y+1, 10, new GameJLabel(name.getCityBuildingLabel(cityNumber, 4).getIcon()), false, name, cityNumber);
				  }
				  break;
			   case 5:
	           name.addBuildingtoCity(cityNumber, 10, 5);      
              for(int i = 0; i < 2; i++)//number of players         
				  {
				    if(name == tempArray[i])
	                tempArray[i].playerUI.lb.addBuilding(x+1, y+1, 10, name.getCityBuildingLabel(cityNumber, 5), false, name, cityNumber);
                else
	                tempArray[i].playerUI.lb.addBuilding(x+1, y+1, 10, new GameJLabel(name.getCityBuildingLabel(cityNumber, 5).getIcon()), false, name, cityNumber);
				  }
				  break;
			   case 6:
	           name.addBuildingtoCity(cityNumber, 10, 6);      
              for(int i = 0; i < 2; i++)//number of players         
				  {
				    if(name == tempArray[i])
	                tempArray[i].playerUI.lb.addBuilding(x-1, y, 10, name.getCityBuildingLabel(cityNumber, 6), false, name, cityNumber);
                else
	                tempArray[i].playerUI.lb.addBuilding(x-1, y, 10, new GameJLabel(name.getCityBuildingLabel(cityNumber, 6).getIcon()), false, name, cityNumber);
				  }
				  break;
			   case 7:
	           name.addBuildingtoCity(cityNumber, 10, 7);      
              for(int i = 0; i < 2; i++)//number of players         
				  {
				    if(name == tempArray[i])
	                tempArray[i].playerUI.lb.addBuilding(x+1, y, 10, name.getCityBuildingLabel(cityNumber, 7), false, name, cityNumber);
                else
	                tempArray[i].playerUI.lb.addBuilding(x+1, y, 10, new GameJLabel(name.getCityBuildingLabel(cityNumber, 7).getIcon()), false, name, cityNumber);
				  }
				  break;
			 }
	       name.getPlayerUI().removeBuilding("university");
	       name.getPlayerUI().removeBuilding("library");
	   
          universityActive = false;
	   
          GameJLayeredPane temp;
		    for(k = 0; k < legalBuilding.size(); k++)
			 {
			   if(legalBuilding.get(k) != null)
				{
				  temp = (GameJLayeredPane)legalBuilding.get(k);
			     temp.remove(temp.getIndexOf(buildingButtons[k]));
              temp.repaint();
				}    
			 }
		    if(!usedEngineering)  
			   finishCityTurn();
			 else if(builtEngineeringOne)
			   finishCityTurn();
			 else
			 {
            engineeringProd = -8;
		      builtEngineeringOne = true;
			   displayCityOptions(cityNumber);
			 }
		  }
	   }
    }

    if(e.getSource() == bank)
	 {
		legalBuilding = new Vector(0, 1); 
      Vector icons = new Vector(0, 1);
      int x = name.getCityXpos(cityNumber);
      int y = name.getCityYpos(cityNumber);
		int k, i;
		for(k = x-1; k <= x+1; k++)
		{  
		  i = y-1;
		  if(!lb.getTerrain(k, i).equals("Water") && !lb.getHasBuild(k, i))
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
		  if(!lb.getTerrain(k, i).equals("Water") && !lb.getHasBuild(k, i))
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
		if(!lb.getTerrain(x-1, y).equals("Water") && !lb.getHasBuild(x-1, y))
		{
		  legalBuilding.add(name.playerUI.lb.getHolder(x-1, y));
        icons.add(name.playerUI.lb.getIcon(x-1, y));  
      }
		else
      {
		  legalBuilding.add(null);		 
        icons.add(null);    
		}   
		if(!lb.getTerrain(x+1, y).equals("Water") && !lb.getHasBuild(x+1, y))
		{
		  legalBuilding.add(name.playerUI.lb.getHolder(x+1, y));
        icons.add(name.playerUI.lb.getIcon(x+1, y));  
      }
		else
      {
		  legalBuilding.add(null);		 
        icons.add(null);    
		}   
	   buildingButtons = new GameJButton[legalBuilding.size()];  
		for(k = 0; k < legalBuilding.size(); k++)
		{
        if(legalBuilding.get(k) != null)    
		  {
			  GameJLayeredPane temp = (GameJLayeredPane)legalBuilding.get(k);
	        GameImageIcon icon = (GameImageIcon)icons.get(k);
	        buildingButtons[k] = new GameJButton(icon);
			  buildingButtons[k].setBounds(00, 00, icon.getIconWidth(), icon.getIconHeight());
			  temp.add(buildingButtons[k], new Integer(4));
	        temp.repaint();
			  buildingButtons[k].addActionListener(this);
		  }
		}
		for(k = 0; k < cityVector.size(); k++)
	   {
	     if(cityVector.get(k) != null)  
		    cityButtons[k].removeActionListener(this);
	   }  
	   GameJLabel lb_1 = new GameJLabel("Select position on map");  
      lb_1.setBounds(30, 10, 150, 15);

	   GameJLabel lb_2 = new GameJLabel("to add bank!");  
      lb_2.setBounds(45, 25, 110, 15);

		cancel = new GameJButton("cancel");
		cancel.setBounds(40, 80, 100, 20);    
		cancel.addActionListener(this);

      cityHolder.removeAll();
	   cityHolder.add(lb_1, new Integer(0));  
		cityHolder.add(lb_2, new Integer(0));  
		cityHolder.add(cancel, new Integer(0));  
		cityHolder.repaint();
      cityPanel.setPreferredSize(new Dimension(190, 110));
      cityScrollPane.revalidate();
	   
	   bankActive = true;
	 }
    if(bankActive)
    {
      Player[] tempArray = name.getPlayerUI().getPlayers();
      for(int k = 0; k < legalBuilding.size(); k++)  
	   {
        if(buildingButtons[k] != null && e.getSource() == buildingButtons[k])    
        {
          int x = name.getCityXpos(cityNumber);
          int y = name.getCityYpos(cityNumber);

          switch(k)
			 {
			   case 0:
	           name.addBuildingtoCity(cityNumber, 11, 0);      
              for(int i = 0; i < 2; i++)//number of players         
				  {
				    if(name == tempArray[i])
						 tempArray[i].playerUI.lb.addBuilding(x-1, y-1, 11, name.getCityBuildingLabel(cityNumber, 0), false, name, cityNumber);
                else
						 tempArray[i].playerUI.lb.addBuilding(x-1, y-1, 11, new GameJLabel(name.getCityBuildingLabel(cityNumber, 0).getIcon()), false, name, cityNumber);
				  }
				  break;
			   case 1:
	           name.addBuildingtoCity(cityNumber, 11, 1);      
              for(int i = 0; i < 2; i++)//number of players         
				  {
				    if(name == tempArray[i])
	                tempArray[i].playerUI.lb.addBuilding(x, y-1, 11, name.getCityBuildingLabel(cityNumber, 1), false, name, cityNumber);
                else
	                tempArray[i].playerUI.lb.addBuilding(x, y-1, 11, new GameJLabel(name.getCityBuildingLabel(cityNumber, 1).getIcon()), false, name, cityNumber);
				  }
				  break;
			   case 2:
	           name.addBuildingtoCity(cityNumber, 11, 2);      
              for(int i = 0; i < 2; i++)//number of players         
				  {
				    if(name == tempArray[i])
	                tempArray[i].playerUI.lb.addBuilding(x+1, y-1, 11, name.getCityBuildingLabel(cityNumber, 2), false, name, cityNumber);
                else
	                tempArray[i].playerUI.lb.addBuilding(x+1, y-1, 11, new GameJLabel(name.getCityBuildingLabel(cityNumber, 2).getIcon()), false, name, cityNumber);
				  }
				  break;
			   case 3:
	           name.addBuildingtoCity(cityNumber, 11, 3);      
              for(int i = 0; i < 2; i++)//number of players         
				  {
				    if(name == tempArray[i])
	                tempArray[i].playerUI.lb.addBuilding(x-1, y+1, 11, name.getCityBuildingLabel(cityNumber, 3), false, name, cityNumber);
                else
	                tempArray[i].playerUI.lb.addBuilding(x-1, y+1, 11, new GameJLabel(name.getCityBuildingLabel(cityNumber, 3).getIcon()), false, name, cityNumber);
				  }
				  break;
			   case 4:
	           name.addBuildingtoCity(cityNumber, 11, 4);      
              for(int i = 0; i < 2; i++)//number of players         
				  {
				    if(name == tempArray[i])
	                tempArray[i].playerUI.lb.addBuilding(x, y+1, 11, name.getCityBuildingLabel(cityNumber, 4), false, name, cityNumber);
                else
	                tempArray[i].playerUI.lb.addBuilding(x, y+1, 11, new GameJLabel(name.getCityBuildingLabel(cityNumber, 4).getIcon()), false, name, cityNumber);
				  }
				  break;
			   case 5:
	           name.addBuildingtoCity(cityNumber, 11, 5);      
              for(int i = 0; i < 2; i++)//number of players         
				  {
				    if(name == tempArray[i])
	                tempArray[i].playerUI.lb.addBuilding(x+1, y+1, 11, name.getCityBuildingLabel(cityNumber, 5), false, name, cityNumber);
                else
	                tempArray[i].playerUI.lb.addBuilding(x+1, y+1, 11, new GameJLabel(name.getCityBuildingLabel(cityNumber, 5).getIcon()), false, name, cityNumber);
				  }
				  break;
			   case 6:
	           name.addBuildingtoCity(cityNumber, 11, 6);      
              for(int i = 0; i < 2; i++)//number of players         
				  {
				    if(name == tempArray[i])
	                tempArray[i].playerUI.lb.addBuilding(x-1, y, 11, name.getCityBuildingLabel(cityNumber, 6), false, name, cityNumber);
                else
	                tempArray[i].playerUI.lb.addBuilding(x-1, y, 11, new GameJLabel(name.getCityBuildingLabel(cityNumber, 6).getIcon()), false, name, cityNumber);
				  }
				  break;
			   case 7:
	           name.addBuildingtoCity(cityNumber, 11, 7);      
              for(int i = 0; i < 2; i++)//number of players         
				  {
				    if(name == tempArray[i])
	                tempArray[i].playerUI.lb.addBuilding(x+1, y, 11, name.getCityBuildingLabel(cityNumber, 7), false, name, cityNumber);
                else
	                tempArray[i].playerUI.lb.addBuilding(x+1, y, 11, new GameJLabel(name.getCityBuildingLabel(cityNumber, 7).getIcon()), false, name, cityNumber);
				  }
				  break;
			 }
	       name.getPlayerUI().removeBuilding("bank");
	       name.getPlayerUI().removeBuilding("market");
	   
          bankActive = false;
	       bank.removeActionListener(this);
          GameJLayeredPane temp;
		    for(k = 0; k < legalBuilding.size(); k++)
			 {
			   if(legalBuilding.get(k) != null)
				{
				  temp = (GameJLayeredPane)legalBuilding.get(k);
			     temp.remove(temp.getIndexOf(buildingButtons[k]));
              temp.repaint();
				}    
			 }
		    if(!usedEngineering)  
			   finishCityTurn();
			 else if(builtEngineeringOne)
			   finishCityTurn();
			 else
			 {
            engineeringProd = -10;
		      builtEngineeringOne = true;
			   displayCityOptions(cityNumber);
			 }
		  }
	   }
    }

    if(e.getSource() == academy)
	 {
		legalBuilding = new Vector(0, 1); 
      Vector icons = new Vector(0, 1);
      int x = name.getCityXpos(cityNumber);
      int y = name.getCityYpos(cityNumber);
		int k, i;
		for(k = x-1; k <= x+1; k++)
		{  
		  i = y-1;
		  if(!lb.getTerrain(k, i).equals("Water") && !lb.getHasBuild(k, i))
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
		  if(!lb.getTerrain(k, i).equals("Water") && !lb.getHasBuild(k, i))
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
		if(!lb.getTerrain(x-1, y).equals("Water") && !lb.getHasBuild(x-1, y))
		{
		  legalBuilding.add(name.playerUI.lb.getHolder(x-1, y));
        icons.add(name.playerUI.lb.getIcon(x-1, y));  
      }
		else
      {
		  legalBuilding.add(null);		 
        icons.add(null);    
		}   
		if(!lb.getTerrain(x+1, y).equals("Water") && !lb.getHasBuild(x+1, y))
		{
		  legalBuilding.add(name.playerUI.lb.getHolder(x+1, y));
        icons.add(name.playerUI.lb.getIcon(x+1, y));  
      }
		else
      {
		  legalBuilding.add(null);		 
        icons.add(null);    
		}   
	   buildingButtons = new GameJButton[legalBuilding.size()];  
		for(k = 0; k < legalBuilding.size(); k++)
		{
        if(legalBuilding.get(k) != null)    
		  {
			  GameJLayeredPane temp = (GameJLayeredPane)legalBuilding.get(k);
	        GameImageIcon icon = (GameImageIcon)icons.get(k);
	        buildingButtons[k] = new GameJButton(icon);
			  buildingButtons[k].setBounds(00, 00, icon.getIconWidth(), icon.getIconHeight());
			  temp.add(buildingButtons[k], new Integer(4));
	        temp.repaint();
			  buildingButtons[k].addActionListener(this);
		  }
		}
		for(k = 0; k < cityVector.size(); k++)
	   {
	     if(cityVector.get(k) != null)  
		    cityButtons[k].removeActionListener(this);
	   }  
	   GameJLabel lb_1 = new GameJLabel("Select position on map");  
      lb_1.setBounds(30, 10, 150, 15);

	   GameJLabel lb_2 = new GameJLabel("to add academy!");  
      lb_2.setBounds(45, 25, 110, 15);

		cancel = new GameJButton("cancel");
		cancel.setBounds(40, 80, 100, 20);    
		cancel.addActionListener(this);

      cityHolder.removeAll();
	   cityHolder.add(lb_1, new Integer(0));  
		cityHolder.add(lb_2, new Integer(0));  
		cityHolder.add(cancel, new Integer(0));  
		cityHolder.repaint();
      cityPanel.setPreferredSize(new Dimension(190, 110));
      cityScrollPane.revalidate();
	   
	   academyActive = true;
	 }
    if(academyActive)
    {
      Player[] tempArray = name.getPlayerUI().getPlayers();
      for(int k = 0; k < legalBuilding.size(); k++)  
	   {
        if(buildingButtons[k] != null && e.getSource() == buildingButtons[k])    
        {
          int x = name.getCityXpos(cityNumber);
          int y = name.getCityYpos(cityNumber);

          switch(k)
			 {
			   case 0:
	           name.addBuildingtoCity(cityNumber, 8, 0);      
              for(int i = 0; i < 2; i++)//number of players         
				  {
				    if(name == tempArray[i])
						 tempArray[i].playerUI.lb.addBuilding(x-1, y-1, 8, name.getCityBuildingLabel(cityNumber, 0), false, name, cityNumber);
                else
						 tempArray[i].playerUI.lb.addBuilding(x-1, y-1, 8, new GameJLabel(name.getCityBuildingLabel(cityNumber, 0).getIcon()), false, name, cityNumber);
				  }
				  break;
			   case 1:
	           name.addBuildingtoCity(cityNumber, 8, 1);      
              for(int i = 0; i < 2; i++)//number of players         
				  {
				    if(name == tempArray[i])
	                tempArray[i].playerUI.lb.addBuilding(x, y-1, 8, name.getCityBuildingLabel(cityNumber, 1), false, name, cityNumber);
                else
	                tempArray[i].playerUI.lb.addBuilding(x, y-1, 8, new GameJLabel(name.getCityBuildingLabel(cityNumber, 1).getIcon()), false, name, cityNumber);
				  }
				  break;
			   case 2:
	           name.addBuildingtoCity(cityNumber, 8, 2);      
              for(int i = 0; i < 2; i++)//number of players         
				  {
				    if(name == tempArray[i])
	                tempArray[i].playerUI.lb.addBuilding(x+1, y-1, 8, name.getCityBuildingLabel(cityNumber, 2), false, name, cityNumber);
                else
	                tempArray[i].playerUI.lb.addBuilding(x+1, y-1, 8, new GameJLabel(name.getCityBuildingLabel(cityNumber, 2).getIcon()), false, name, cityNumber);
				  }
				  break;
			   case 3:
	           name.addBuildingtoCity(cityNumber, 8, 3);      
              for(int i = 0; i < 2; i++)//number of players         
				  {
				    if(name == tempArray[i])
	                tempArray[i].playerUI.lb.addBuilding(x-1, y+1, 8, name.getCityBuildingLabel(cityNumber, 3), false, name, cityNumber);
                else
	                tempArray[i].playerUI.lb.addBuilding(x-1, y+1, 8, new GameJLabel(name.getCityBuildingLabel(cityNumber, 3).getIcon()), false, name, cityNumber);
				  }
				  break;
			   case 4:
	           name.addBuildingtoCity(cityNumber, 8, 4);      
              for(int i = 0; i < 2; i++)//number of players         
				  {
				    if(name == tempArray[i])
	                tempArray[i].playerUI.lb.addBuilding(x, y+1, 8, name.getCityBuildingLabel(cityNumber, 4), false, name, cityNumber);
                else
	                tempArray[i].playerUI.lb.addBuilding(x, y+1, 8, new GameJLabel(name.getCityBuildingLabel(cityNumber, 4).getIcon()), false, name, cityNumber);
				  }
				  break;
			   case 5:
	           name.addBuildingtoCity(cityNumber, 8, 5);      
              for(int i = 0; i < 2; i++)//number of players         
				  {
				    if(name == tempArray[i])
	                tempArray[i].playerUI.lb.addBuilding(x+1, y+1, 8, name.getCityBuildingLabel(cityNumber, 5), false, name, cityNumber);
                else
	                tempArray[i].playerUI.lb.addBuilding(x+1, y+1, 8, new GameJLabel(name.getCityBuildingLabel(cityNumber, 5).getIcon()), false, name, cityNumber);
				  }
				  break;
			   case 6:
	           name.addBuildingtoCity(cityNumber, 8, 6);      
              for(int i = 0; i < 2; i++)//number of players         
				  {
				    if(name == tempArray[i])
	                tempArray[i].playerUI.lb.addBuilding(x-1, y, 8, name.getCityBuildingLabel(cityNumber, 6), false, name, cityNumber);
                else
	                tempArray[i].playerUI.lb.addBuilding(x-1, y, 8, new GameJLabel(name.getCityBuildingLabel(cityNumber, 6).getIcon()), false, name, cityNumber);
				  }
				  break;
			   case 7:
	           name.addBuildingtoCity(cityNumber, 8, 7);      
              for(int i = 0; i < 2; i++)//number of players         
				  {
				    if(name == tempArray[i])
	               tempArray[i].playerUI.lb.addBuilding(x+1, y, 8, name.getCityBuildingLabel(cityNumber, 7), false, name, cityNumber);
                else
	               tempArray[i].playerUI.lb.addBuilding(x+1, y, 8, new GameJLabel(name.getCityBuildingLabel(cityNumber, 7).getIcon()), false, name, cityNumber);
				  }
				  break;
			 }
	       name.getPlayerUI().removeBuilding("academy");
	       name.getPlayerUI().removeBuilding("barracks");
	   
          academyActive = false;
	       academy.removeActionListener(this);
          GameJLayeredPane temp;
		    for(k = 0; k < legalBuilding.size(); k++)
			 {
			   if(legalBuilding.get(k) != null)
				{
				  temp = (GameJLayeredPane)legalBuilding.get(k);
			     temp.remove(temp.getIndexOf(buildingButtons[k]));
              temp.repaint();
				}    
			 }
		    if(!usedEngineering)  
			   finishCityTurn();
			 else if(builtEngineeringOne)
			   finishCityTurn();
			 else
			 {
            engineeringProd = -10;
		      builtEngineeringOne = true;
			   displayCityOptions(cityNumber);
			 }
		  }
	   }
    }

    if(e.getSource() == ironmine)
	 {
		legalBuilding = new Vector(0, 1); 
      Vector icons = new Vector(0, 1);
      int x = name.getCityXpos(cityNumber);
      int y = name.getCityYpos(cityNumber);
		int k, i;
		for(k = x-1; k <= x+1; k++)
		{  
		  i = y-1;
		  if(name.playerUI.lb.getTerrain(k, i).equals("Mountain") && !lb.getHasBuild(k, i))
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
		  if(name.playerUI.lb.getTerrain(k, i).equals("Mountain") && !lb.getHasBuild(k, i))
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
		if(name.playerUI.lb.getTerrain(x-1, y).equals("Mountain") && !lb.getHasBuild(x-1, y))
		{
		  legalBuilding.add(name.playerUI.lb.getHolder(x-1, y));
        icons.add(name.playerUI.lb.getIcon(x-1, y));  
      }
		else
      {
		  legalBuilding.add(null);		 
        icons.add(null);    
		}   
		if(name.playerUI.lb.getTerrain(x+1, y).equals("Mountain") && !lb.getHasBuild(x+1, y))
		{
		  legalBuilding.add(name.playerUI.lb.getHolder(x+1, y));
        icons.add(name.playerUI.lb.getIcon(x+1, y));  
      }
		else
      {
		  legalBuilding.add(null);		 
        icons.add(null);    
		}   
	   buildingButtons = new GameJButton[legalBuilding.size()];  
		for(k = 0; k < legalBuilding.size(); k++)
		{
        if(legalBuilding.get(k) != null)    
		  {
			  GameJLayeredPane temp = (GameJLayeredPane)legalBuilding.get(k);
	        GameImageIcon icon = (GameImageIcon)icons.get(k);
	        buildingButtons[k] = new GameJButton(icon);
			  buildingButtons[k].setBounds(00, 00, icon.getIconWidth(), icon.getIconHeight());
			  temp.add(buildingButtons[k], new Integer(4));
	        temp.repaint();
			  buildingButtons[k].addActionListener(this);
		  }
		}
		for(k = 0; k < cityVector.size(); k++)
	   {
	     if(cityVector.get(k) != null)  
		    cityButtons[k].removeActionListener(this);
	   }  
	   GameJLabel lb_1 = new GameJLabel("Select position on map");  
      lb_1.setBounds(30, 10, 150, 15);

	   GameJLabel lb_2 = new GameJLabel("to add ironmine!");  
      lb_2.setBounds(45, 25, 110, 15);

		cancel = new GameJButton("cancel");
		cancel.setBounds(40, 80, 100, 20);    
		cancel.addActionListener(this);

      cityHolder.removeAll();
	   cityHolder.add(lb_1, new Integer(0));  
		cityHolder.add(lb_2, new Integer(0));  
		cityHolder.add(cancel, new Integer(0));  
		cityHolder.repaint();
      cityPanel.setPreferredSize(new Dimension(190, 110));
      cityScrollPane.revalidate();
	   
	   ironmineActive = true;
	 }
    if(ironmineActive)
    {
      Player[] tempArray = name.getPlayerUI().getPlayers();
      for(int k = 0; k < legalBuilding.size(); k++)  
	   {
        if(buildingButtons[k] != null && e.getSource() == buildingButtons[k])    
        {
          int x = name.getCityXpos(cityNumber);
          int y = name.getCityYpos(cityNumber);

          switch(k)
			 {
			   case 0:
	           name.addBuildingtoCity(cityNumber, 13, 0);      
              for(int i = 0; i < 2; i++)//number of players         
				  {
				    if(name == tempArray[i])
						 tempArray[i].playerUI.lb.addBuilding(x-1, y-1, 13, name.getCityBuildingLabel(cityNumber, 0), false, name, cityNumber);
                else
						 tempArray[i].playerUI.lb.addBuilding(x-1, y-1, 13, new GameJLabel(name.getCityBuildingLabel(cityNumber, 0).getIcon()), false, name, cityNumber);
				  }
				  break;
			   case 1:
	           name.addBuildingtoCity(cityNumber, 13, 1);      
              for(int i = 0; i < 2; i++)//number of players         
				  {
				    if(name == tempArray[i])
	                tempArray[i].playerUI.lb.addBuilding(x, y-1, 13, name.getCityBuildingLabel(cityNumber, 1), false, name, cityNumber);
                else
	                tempArray[i].playerUI.lb.addBuilding(x, y-1, 13, new GameJLabel(name.getCityBuildingLabel(cityNumber, 1).getIcon()), false, name, cityNumber);
				  }
				  break;
			   case 2:
	           name.addBuildingtoCity(cityNumber, 13, 2);      
              for(int i = 0; i < 2; i++)//number of players         
				  {
				    if(name == tempArray[i])
	                tempArray[i].playerUI.lb.addBuilding(x+1, y-1, 13, name.getCityBuildingLabel(cityNumber, 2), false, name, cityNumber);
                else
	                tempArray[i].playerUI.lb.addBuilding(x+1, y-1, 13, new GameJLabel(name.getCityBuildingLabel(cityNumber, 2).getIcon()), false, name, cityNumber);
				  }
				  break;
			   case 3:
	           name.addBuildingtoCity(cityNumber, 13, 3);      
              for(int i = 0; i < 2; i++)//number of players         
				  {
				    if(name == tempArray[i])
	                tempArray[i].playerUI.lb.addBuilding(x-1, y+1, 13, name.getCityBuildingLabel(cityNumber, 3), false, name, cityNumber);
                else
	                tempArray[i].playerUI.lb.addBuilding(x-1, y+1, 13, new GameJLabel(name.getCityBuildingLabel(cityNumber, 3).getIcon()), false, name, cityNumber);
				  }
				  break;
			   case 4:
	           name.addBuildingtoCity(cityNumber, 13, 4);      
              for(int i = 0; i < 2; i++)//number of players         
				  {
				    if(name == tempArray[i])
	                tempArray[i].playerUI.lb.addBuilding(x, y+1, 13, name.getCityBuildingLabel(cityNumber, 4), false, name, cityNumber);
                else
	                tempArray[i].playerUI.lb.addBuilding(x, y+1, 13, new GameJLabel(name.getCityBuildingLabel(cityNumber, 4).getIcon()), false, name, cityNumber);
				  }
				  break;
			   case 5:
	           name.addBuildingtoCity(cityNumber, 13, 5);      
              for(int i = 0; i < 2; i++)//number of players         
				  {
				    if(name == tempArray[i])
	                tempArray[i].playerUI.lb.addBuilding(x+1, y+1, 13, name.getCityBuildingLabel(cityNumber, 5), false, name, cityNumber);
                else
	                tempArray[i].playerUI.lb.addBuilding(x+1, y+1, 13, new GameJLabel(name.getCityBuildingLabel(cityNumber, 5).getIcon()), false, name, cityNumber);
				  }
				  break;
			   case 6:
	           name.addBuildingtoCity(cityNumber, 13, 6);      
              for(int i = 0; i < 2; i++)//number of players         
				  {
				    if(name == tempArray[i])
	                tempArray[i].playerUI.lb.addBuilding(x-1, y, 13, name.getCityBuildingLabel(cityNumber, 6), false, name, cityNumber);
                else
	                tempArray[i].playerUI.lb.addBuilding(x-1, y, 13, new GameJLabel(name.getCityBuildingLabel(cityNumber, 6).getIcon()), false, name, cityNumber);
				  }
				  break;
			   case 7:
	           name.addBuildingtoCity(cityNumber, 13, 7);      
              for(int i = 0; i < 2; i++)//number of players         
				  {
				    if(name == tempArray[i])
	                tempArray[i].playerUI.lb.addBuilding(x+1, y, 13, name.getCityBuildingLabel(cityNumber, 7), false, name, cityNumber);
                else
	                tempArray[i].playerUI.lb.addBuilding(x+1, y, 13, new GameJLabel(name.getCityBuildingLabel(cityNumber, 7).getIcon()), false, name, cityNumber);
				  }
				  break;
			 }
	       name.getPlayerUI().removeBuilding("ironmine");
	       name.getPlayerUI().removeBuilding("workshop");

          ironmineActive = false;
	   
          GameJLayeredPane temp;
		    for(k = 0; k < legalBuilding.size(); k++)
			 {
			   if(legalBuilding.get(k) != null)
				{
				  temp = (GameJLayeredPane)legalBuilding.get(k);
			     temp.remove(temp.getIndexOf(buildingButtons[k]));
              temp.repaint();
				}    
			 }
		    if(!usedEngineering)  
			   finishCityTurn();
			 else if(builtEngineeringOne)
			   finishCityTurn();
			 else
			 {
            engineeringProd = -10;
		      builtEngineeringOne = true;
			   displayCityOptions(cityNumber);
			 }
		  }
	   }
    }

    if(e.getSource() == cathedral)
	 {
		legalBuilding = new Vector(0, 1); 
      Vector icons = new Vector(0, 1);
      int x = name.getCityXpos(cityNumber);
      int y = name.getCityYpos(cityNumber);
		int k, i;
		for(k = x-1; k <= x+1; k++)
		{  
		  i = y-1;
		  if(!lb.getTerrain(k, i).equals("Water") && !lb.getHasBuild(k, i))
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
		  if(!lb.getTerrain(k, i).equals("Water") && !lb.getHasBuild(k, i))
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
		if(!lb.getTerrain(x-1, y).equals("Water") && !lb.getHasBuild(x-1, y))
		{
		  legalBuilding.add(name.playerUI.lb.getHolder(x-1, y));
        icons.add(name.playerUI.lb.getIcon(x-1, y));  
      }
		else
      {
		  legalBuilding.add(null);		 
        icons.add(null);    
		}   
		if(!lb.getTerrain(x+1, y).equals("Water") && !lb.getHasBuild(x+1, y))
		{
		  legalBuilding.add(name.playerUI.lb.getHolder(x+1, y));
        icons.add(name.playerUI.lb.getIcon(x+1, y));  
      }
		else
      {
		  legalBuilding.add(null);		 
        icons.add(null);    
		}   
	   buildingButtons = new GameJButton[legalBuilding.size()];  
		for(k = 0; k < legalBuilding.size(); k++)
		{
        if(legalBuilding.get(k) != null)    
		  {
			  GameJLayeredPane temp = (GameJLayeredPane)legalBuilding.get(k);
	        GameImageIcon icon = (GameImageIcon)icons.get(k);
	        buildingButtons[k] = new GameJButton(icon);
			  buildingButtons[k].setBounds(00, 00, icon.getIconWidth(), icon.getIconHeight());
			  temp.add(buildingButtons[k], new Integer(4));
	        temp.repaint();
			  buildingButtons[k].addActionListener(this);
		  }
		}
		for(k = 0; k < cityVector.size(); k++)
	   {
	     if(cityVector.get(k) != null)  
		    cityButtons[k].removeActionListener(this);
	   }  
	   GameJLabel lb_1 = new GameJLabel("Select position on map");  
      lb_1.setBounds(30, 10, 150, 15);

	   GameJLabel lb_2 = new GameJLabel("to add cathedral!");  
      lb_2.setBounds(45, 25, 110, 15);

		cancel = new GameJButton("cancel");
		cancel.setBounds(40, 80, 100, 20);    
		cancel.addActionListener(this);

      cityHolder.removeAll();
	   cityHolder.add(lb_1, new Integer(0));  
		cityHolder.add(lb_2, new Integer(0));  
		cityHolder.add(cancel, new Integer(0));  
		cityHolder.repaint();
      cityPanel.setPreferredSize(new Dimension(190, 110));
      cityScrollPane.revalidate();
	   
	   cathedralActive = true;
	 }
    if(cathedralActive)
    {
      Player[] tempArray = name.getPlayerUI().getPlayers();
      for(int k = 0; k < legalBuilding.size(); k++)  
	   {
        if(buildingButtons[k] != null && e.getSource() == buildingButtons[k])    
        {
          int x = name.getCityXpos(cityNumber);
          int y = name.getCityYpos(cityNumber);

          switch(k)
			 {
			   case 0:
	           name.addBuildingtoCity(cityNumber, 12, 0);      
              for(int i = 0; i < 2; i++)//number of players         
				  {
				    if(name == tempArray[i])
					 	tempArray[i].playerUI.lb.addBuilding(x-1, y-1, 12, name.getCityBuildingLabel(cityNumber, 0), false, name, cityNumber);
                else
					 	tempArray[i].playerUI.lb.addBuilding(x-1, y-1, 12, new GameJLabel(name.getCityBuildingLabel(cityNumber, 0).getIcon()), false, name, cityNumber);
				  }
				  break;
			   case 1:
	           name.addBuildingtoCity(cityNumber, 12, 1);      
              for(int i = 0; i < 2; i++)//number of players         
				  {
				    if(name == tempArray[i])
	                tempArray[i].playerUI.lb.addBuilding(x, y-1, 12, name.getCityBuildingLabel(cityNumber, 1), false, name, cityNumber);
                else
	                tempArray[i].playerUI.lb.addBuilding(x, y-1, 12, new GameJLabel(name.getCityBuildingLabel(cityNumber, 1).getIcon()), false, name, cityNumber);
				  }
				  break;
			   case 2:
	           name.addBuildingtoCity(cityNumber, 12, 2);      
              for(int i = 0; i < 2; i++)//number of players         
				  {
				    if(name == tempArray[i])
	                tempArray[i].playerUI.lb.addBuilding(x+1, y-1, 12, name.getCityBuildingLabel(cityNumber, 2), false, name, cityNumber);
                else
	                tempArray[i].playerUI.lb.addBuilding(x+1, y-1, 12, new GameJLabel(name.getCityBuildingLabel(cityNumber, 2).getIcon()), false, name, cityNumber);
				  }
				  break;
			   case 3:
	           name.addBuildingtoCity(cityNumber, 12, 3);      
              for(int i = 0; i < 2; i++)//number of players         
				  {
				    if(name == tempArray[i])
	                tempArray[i].playerUI.lb.addBuilding(x-1, y+1, 12, name.getCityBuildingLabel(cityNumber, 3), false, name, cityNumber);
                else
	                tempArray[i].playerUI.lb.addBuilding(x-1, y+1, 12, new GameJLabel(name.getCityBuildingLabel(cityNumber, 3).getIcon()), false, name, cityNumber);
				  }
				  break;
			   case 4:
	           name.addBuildingtoCity(cityNumber, 12, 4);      
              for(int i = 0; i < 2; i++)//number of players         
				  {
				    if(name == tempArray[i])
	                tempArray[i].playerUI.lb.addBuilding(x, y+1, 12, name.getCityBuildingLabel(cityNumber, 4), false, name, cityNumber);
                else
	                tempArray[i].playerUI.lb.addBuilding(x, y+1, 12, new GameJLabel(name.getCityBuildingLabel(cityNumber, 4).getIcon()), false, name, cityNumber);
				  }
				  break;
			   case 5:
	           name.addBuildingtoCity(cityNumber, 12, 5);      
              for(int i = 0; i < 2; i++)//number of players         
				  {
				    if(name == tempArray[i])
	                tempArray[i].playerUI.lb.addBuilding(x+1, y+1, 12, name.getCityBuildingLabel(cityNumber, 5), false, name, cityNumber);
                else
	                tempArray[i].playerUI.lb.addBuilding(x+1, y+1, 12, new GameJLabel(name.getCityBuildingLabel(cityNumber, 5).getIcon()), false, name, cityNumber);
				  }
				  break;
			   case 6:
	           name.addBuildingtoCity(cityNumber, 12, 6);      
              for(int i = 0; i < 2; i++)//number of players         
				  {
				    if(name == tempArray[i])
	                tempArray[i].playerUI.lb.addBuilding(x-1, y, 12, name.getCityBuildingLabel(cityNumber, 6), false, name, cityNumber);
                else
	                tempArray[i].playerUI.lb.addBuilding(x-1, y, 12, new GameJLabel(name.getCityBuildingLabel(cityNumber, 6).getIcon()), false, name, cityNumber);
				  }
				  break;
			   case 7:
	           name.addBuildingtoCity(cityNumber, 12, 7);      
              for(int i = 0; i < 2; i++)//number of players         
				  {
				    if(name == tempArray[i])
	                tempArray[i].playerUI.lb.addBuilding(x+1, y, 12, name.getCityBuildingLabel(cityNumber, 7), false, name, cityNumber);
                else
	                tempArray[i].playerUI.lb.addBuilding(x+1, y, 12, new GameJLabel(name.getCityBuildingLabel(cityNumber, 7).getIcon()), false, name, cityNumber);
				  }
				  break;
			 }
	       name.getPlayerUI().removeBuilding("cathedral");
	       name.getPlayerUI().removeBuilding("temple");
	   
          cathedralActive = false;
	       cathedral.removeActionListener(this);
          GameJLayeredPane temp;
		    for(k = 0; k < legalBuilding.size(); k++)
			 {
			   if(legalBuilding.get(k) != null)
				{
				  temp = (GameJLayeredPane)legalBuilding.get(k);
			     temp.remove(temp.getIndexOf(buildingButtons[k]));
              temp.repaint();
				}    
			 }
		    if(!usedEngineering)  
			   finishCityTurn();
			 else if(builtEngineeringOne)
			   finishCityTurn();
			 else
			 {
            engineeringProd = -10;
		      builtEngineeringOne = true;
			   displayCityOptions(cityNumber);
			 }
		  }
	   }
    }

    if(e.getSource() == stone)
	 {
		legalBuilding = new Vector(0, 1); 
      Vector icons = new Vector(0, 1);
      int x = name.getCityXpos(cityNumber);
      int y = name.getCityYpos(cityNumber);
		int k, i;
		for(k = x-1; k <= x+1; k++)
		{  
		  i = y-1;
		  if(!lb.getTerrain(k, i).equals("Water") && !lb.getHasBuild(k, i))
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
		  if(!lb.getTerrain(k, i).equals("Water") && !lb.getHasBuild(k, i))
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
		if(!lb.getTerrain(x-1, y).equals("Water") && !lb.getHasBuild(x-1, y))
		{
		  legalBuilding.add(name.playerUI.lb.getHolder(x-1, y));
        icons.add(name.playerUI.lb.getIcon(x-1, y));  
      }
		else
      {
		  legalBuilding.add(null);		 
        icons.add(null);    
		}   
		if(!lb.getTerrain(x+1, y).equals("Water") && !lb.getHasBuild(x+1, y))
		{
		  legalBuilding.add(name.playerUI.lb.getHolder(x+1, y));
        icons.add(name.playerUI.lb.getIcon(x+1, y));  
      }
		else
      {
		  legalBuilding.add(null);		 
        icons.add(null);    
		}   
	   buildingButtons = new GameJButton[legalBuilding.size()];  
		for(k = 0; k < legalBuilding.size(); k++)
		{
        if(legalBuilding.get(k) != null)    
		  {
			  GameJLayeredPane temp = (GameJLayeredPane)legalBuilding.get(k);
	        GameImageIcon icon = (GameImageIcon)icons.get(k);
	        buildingButtons[k] = new GameJButton(icon);
			  buildingButtons[k].setBounds(00, 00, icon.getIconWidth(), icon.getIconHeight());
			  temp.add(buildingButtons[k], new Integer(4));
	        temp.repaint();
			  buildingButtons[k].addActionListener(this);
		  }
		}
		for(k = 0; k < cityVector.size(); k++)
	   {
	     if(cityVector.get(k) != null)  
		    cityButtons[k].removeActionListener(this);
	   }  
	   GameJLabel lb_1 = new GameJLabel("Select position on map");  
      lb_1.setBounds(30, 10, 150, 15);

	   GameJLabel lb_2 = new GameJLabel("to add Stonehenge!");  
      lb_2.setBounds(45, 25, 110, 15);

		cancel = new GameJButton("cancel");
		cancel.setBounds(40, 80, 100, 20);    
		cancel.addActionListener(this);

      cityHolder.removeAll();
	   cityHolder.add(lb_1, new Integer(0));  
		cityHolder.add(lb_2, new Integer(0));  
		cityHolder.add(cancel, new Integer(0));  
		cityHolder.repaint();
      cityPanel.setPreferredSize(new Dimension(190, 110));
      cityScrollPane.revalidate();
	   
	   stoneActive = true;
	 }
    if(stoneActive)
    {
      Player[] tempArray = name.getPlayerUI().getPlayers();
      for(int k = 0; k < legalBuilding.size(); k++)  
	   {
        if(buildingButtons[k] != null && e.getSource() == buildingButtons[k])    
        {
          int x = name.getCityXpos(cityNumber);
          int y = name.getCityYpos(cityNumber);

          switch(k)
			 {
			   case 0:
	           name.addBuildingtoCity(cityNumber, 20, 0);      
              for(int i = 0; i < 2; i++)//number of players         
                tempArray[i].playerUI.lb.addBuilding(x-1, y-1, 20, new GameJLabel(name.getCityBuildingLabel(cityNumber, 0).getIcon()), false, name, cityNumber);
				  break;
			   case 1:
	           name.addBuildingtoCity(cityNumber, 20, 1);      
              for(int i = 0; i < 2; i++)//number of players         
                tempArray[i].playerUI.lb.addBuilding(x, y-1, 20, new GameJLabel(name.getCityBuildingLabel(cityNumber, 1).getIcon()), false, name, cityNumber);
				  break;
			   case 2:
	           name.addBuildingtoCity(cityNumber, 20, 2);      
              for(int i = 0; i < 2; i++)//number of players         
                tempArray[i].playerUI.lb.addBuilding(x+1, y-1, 20, new GameJLabel(name.getCityBuildingLabel(cityNumber, 2).getIcon()), false, name, cityNumber);
				  break;
			   case 3:
	           name.addBuildingtoCity(cityNumber, 20, 3);      
              for(int i = 0; i < 2; i++)//number of players         
                tempArray[i].playerUI.lb.addBuilding(x-1, y+1, 20, new GameJLabel(name.getCityBuildingLabel(cityNumber, 3).getIcon()), false, name, cityNumber);
				  break;
			   case 4:
	           name.addBuildingtoCity(cityNumber, 20, 4);      
              for(int i = 0; i < 2; i++)//number of players         
                tempArray[i].playerUI.lb.addBuilding(x, y+1, 20, new GameJLabel(name.getCityBuildingLabel(cityNumber, 4).getIcon()), false, name, cityNumber);
				  break;
			   case 5:
	           name.addBuildingtoCity(cityNumber, 20, 5);      
              for(int i = 0; i < 2; i++)//number of players         
                tempArray[i].playerUI.lb.addBuilding(x+1, y+1, 20, new GameJLabel(name.getCityBuildingLabel(cityNumber, 5).getIcon()), false, name, cityNumber);
				  break;
			   case 6:
	           name.addBuildingtoCity(cityNumber, 20, 6);      
              for(int i = 0; i < 2; i++)//number of players         
                tempArray[i].playerUI.lb.addBuilding(x-1, y, 20, new GameJLabel(name.getCityBuildingLabel(cityNumber, 6).getIcon()), false, name, cityNumber);
				  break;
			   case 7:
	           name.addBuildingtoCity(cityNumber, 20, 7);      
              for(int i = 0; i < 2; i++)//number of players         
                tempArray[i].playerUI.lb.addBuilding(x+1, y, 20, new GameJLabel(name.getCityBuildingLabel(cityNumber, 7).getIcon()), false, name, cityNumber);
				  break;
			 }
	   
          stoneActive = false;
	       stone.removeActionListener(this);
          GameJLayeredPane temp;
		    for(k = 0; k < legalBuilding.size(); k++)
			 {
			   if(legalBuilding.get(k) != null)
				{
				  temp = (GameJLayeredPane)legalBuilding.get(k);
			     temp.remove(temp.getIndexOf(buildingButtons[k]));
              temp.repaint();
				}    
			 }
		    if(!usedEngineering)  
			   finishCityTurn();
			 else if(builtEngineeringOne)
			   finishCityTurn();
			 else
			 {
            engineeringProd = -10;
		      builtEngineeringOne = true;
			   displayCityOptions(cityNumber);
			 }
		  }
	   }
    }

    if(e.getSource() == colossus)
	 {
		legalBuilding = new Vector(0, 1); 
      Vector icons = new Vector(0, 1);
      int x = name.getCityXpos(cityNumber);
      int y = name.getCityYpos(cityNumber);
		int k, i;
		for(k = x-1; k <= x+1; k++)
		{  
		  i = y-1;
		  if(!lb.getTerrain(k, i).equals("Water") && !lb.getHasBuild(k, i))
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
		  if(!lb.getTerrain(k, i).equals("Water") && !lb.getHasBuild(k, i))
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
		if(!lb.getTerrain(x-1, y).equals("Water") && !lb.getHasBuild(x-1, y))
		{
		  legalBuilding.add(name.playerUI.lb.getHolder(x-1, y));
        icons.add(name.playerUI.lb.getIcon(x-1, y));  
      }
		else
      {
		  legalBuilding.add(null);		 
        icons.add(null);    
		}   
		if(!lb.getTerrain(x+1, y).equals("Water") && !lb.getHasBuild(x+1, y))
		{
		  legalBuilding.add(name.playerUI.lb.getHolder(x+1, y));
        icons.add(name.playerUI.lb.getIcon(x+1, y));  
      }
		else
      {
		  legalBuilding.add(null);		 
        icons.add(null);    
		}   
	   buildingButtons = new GameJButton[legalBuilding.size()];  
		for(k = 0; k < legalBuilding.size(); k++)
		{
        if(legalBuilding.get(k) != null)    
		  {
			  GameJLayeredPane temp = (GameJLayeredPane)legalBuilding.get(k);
	        GameImageIcon icon = (GameImageIcon)icons.get(k);
	        buildingButtons[k] = new GameJButton(icon);
			  buildingButtons[k].setBounds(00, 00, icon.getIconWidth(), icon.getIconHeight());
			  temp.add(buildingButtons[k], new Integer(4));
	        temp.repaint();
			  buildingButtons[k].addActionListener(this);
		  }
		}
		for(k = 0; k < cityVector.size(); k++)
	   {
	     if(cityVector.get(k) != null)  
		    cityButtons[k].removeActionListener(this);
	   }  
	   GameJLabel lb_1 = new GameJLabel("Select position on map");  
      lb_1.setBounds(30, 10, 150, 15);

	   GameJLabel lb_2 = new GameJLabel("to add Colossus!");  
      lb_2.setBounds(45, 25, 110, 15);

		cancel = new GameJButton("cancel");
		cancel.setBounds(40, 80, 100, 20);    
		cancel.addActionListener(this);

      cityHolder.removeAll();
	   cityHolder.add(lb_1, new Integer(0));  
		cityHolder.add(lb_2, new Integer(0));  
		cityHolder.add(cancel, new Integer(0));  
		cityHolder.repaint();
      cityPanel.setPreferredSize(new Dimension(190, 110));
      cityScrollPane.revalidate();
	   
	   colossusActive = true;
	 }
    if(colossusActive)
    {
      Player[] tempArray = name.getPlayerUI().getPlayers();
      for(int k = 0; k < legalBuilding.size(); k++)  
	   {
        if(buildingButtons[k] != null && e.getSource() == buildingButtons[k])    
        {
          int x = name.getCityXpos(cityNumber);
          int y = name.getCityYpos(cityNumber);

          switch(k)
			 {
			   case 0:
	           name.addBuildingtoCity(cityNumber, 21, 0);      
              for(int i = 0; i < 2; i++)//number of players         
                tempArray[i].playerUI.lb.addBuilding(x-1, y-1, 21, new GameJLabel(name.getCityBuildingLabel(cityNumber, 0).getIcon()), false, name, cityNumber);
				  break;
			   case 1:
	           name.addBuildingtoCity(cityNumber, 21, 1);      
              for(int i = 0; i < 2; i++)//number of players         
                tempArray[i].playerUI.lb.addBuilding(x, y-1, 21, new GameJLabel(name.getCityBuildingLabel(cityNumber, 1).getIcon()), false, name, cityNumber);
				  break;
			   case 2:
	           name.addBuildingtoCity(cityNumber, 21, 2);      
              for(int i = 0; i < 2; i++)//number of players         
                tempArray[i].playerUI.lb.addBuilding(x+1, y-1, 21, new GameJLabel(name.getCityBuildingLabel(cityNumber, 2).getIcon()), false, name, cityNumber);
				  break;
			   case 3:
	           name.addBuildingtoCity(cityNumber, 21, 3);      
              for(int i = 0; i < 2; i++)//number of players         
                tempArray[i].playerUI.lb.addBuilding(x-1, y+1, 21, new GameJLabel(name.getCityBuildingLabel(cityNumber, 3).getIcon()), false, name, cityNumber);
				  break;
			   case 4:
	           name.addBuildingtoCity(cityNumber, 21, 4);      
              for(int i = 0; i < 2; i++)//number of players         
                tempArray[i].playerUI.lb.addBuilding(x, y+1, 21, new GameJLabel(name.getCityBuildingLabel(cityNumber, 4).getIcon()), false, name, cityNumber);
				  break;
			   case 5:
	           name.addBuildingtoCity(cityNumber, 21, 5);      
              for(int i = 0; i < 2; i++)//number of players         
                tempArray[i].playerUI.lb.addBuilding(x+1, y+1, 21, new GameJLabel(name.getCityBuildingLabel(cityNumber, 5).getIcon()), false, name, cityNumber);
				  break;
			   case 6:
	           name.addBuildingtoCity(cityNumber, 21, 6);      
              for(int i = 0; i < 2; i++)//number of players         
                tempArray[i].playerUI.lb.addBuilding(x-1, y, 21, new GameJLabel(name.getCityBuildingLabel(cityNumber, 6).getIcon()), false, name, cityNumber);
				  break;
			   case 7:
	           name.addBuildingtoCity(cityNumber, 21, 7);      
              for(int i = 0; i < 2; i++)//number of players         
                tempArray[i].playerUI.lb.addBuilding(x+1, y, 21, new GameJLabel(name.getCityBuildingLabel(cityNumber, 7).getIcon()), false, name, cityNumber);
				  break;
			 }
	   
          colossusActive = false;
	       colossus.removeActionListener(this);
          GameJLayeredPane temp;
		    for(k = 0; k < legalBuilding.size(); k++)
			 {
			   if(legalBuilding.get(k) != null)
				{
				  temp = (GameJLayeredPane)legalBuilding.get(k);
			     temp.remove(temp.getIndexOf(buildingButtons[k]));
              temp.repaint();
				}    
			 }
		    if(!usedEngineering)  
			   finishCityTurn();
			 else if(builtEngineeringOne)
			   finishCityTurn();
			 else
			 {
            if(name.getTechVector().get(5) != null)
				  engineeringProd = -10;
				else
				  engineeringProd = -15;
		      builtEngineeringOne = true;
			   displayCityOptions(cityNumber);
			 }
		  }
	   }
    }

    if(e.getSource() == gardens)
	 {
		legalBuilding = new Vector(0, 1); 
      Vector icons = new Vector(0, 1);
      int x = name.getCityXpos(cityNumber);
      int y = name.getCityYpos(cityNumber);
		int k, i;
		for(k = x-1; k <= x+1; k++)
		{  
		  i = y-1;
		  if(!lb.getTerrain(k, i).equals("Water") && !lb.getHasBuild(k, i))
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
		  if(!lb.getTerrain(k, i).equals("Water") && !lb.getHasBuild(k, i))
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
		if(!lb.getTerrain(x-1, y).equals("Water") && !lb.getHasBuild(x-1, y))
		{
		  legalBuilding.add(name.playerUI.lb.getHolder(x-1, y));
        icons.add(name.playerUI.lb.getIcon(x-1, y));  
      }
		else
      {
		  legalBuilding.add(null);		 
        icons.add(null);    
		}   
		if(!lb.getTerrain(x+1, y).equals("Water") && !lb.getHasBuild(x+1, y))
		{
		  legalBuilding.add(name.playerUI.lb.getHolder(x+1, y));
        icons.add(name.playerUI.lb.getIcon(x+1, y));  
      }
		else
      {
		  legalBuilding.add(null);		 
        icons.add(null);    
		}   
	   buildingButtons = new GameJButton[legalBuilding.size()];  
		for(k = 0; k < legalBuilding.size(); k++)
		{
        if(legalBuilding.get(k) != null)    
		  {
			  GameJLayeredPane temp = (GameJLayeredPane)legalBuilding.get(k);
	        GameImageIcon icon = (GameImageIcon)icons.get(k);
	        buildingButtons[k] = new GameJButton(icon);
			  buildingButtons[k].setBounds(00, 00, icon.getIconWidth(), icon.getIconHeight());
			  temp.add(buildingButtons[k], new Integer(4));
	        temp.repaint();
			  buildingButtons[k].addActionListener(this);
		  }
		}
		for(k = 0; k < cityVector.size(); k++)
	   {
	     if(cityVector.get(k) != null)  
		    cityButtons[k].removeActionListener(this);
	   }  
	   GameJLabel lb_1 = new GameJLabel("Select position on map");  
      lb_1.setBounds(30, 10, 150, 15);

	   GameJLabel lb_2 = new GameJLabel("to add Hanging Gardens!");  
      lb_2.setBounds(45, 25, 110, 15);

		cancel = new GameJButton("cancel");
		cancel.setBounds(40, 80, 100, 20);    
		cancel.addActionListener(this);

      cityHolder.removeAll();
	   cityHolder.add(lb_1, new Integer(0));  
		cityHolder.add(lb_2, new Integer(0));  
		cityHolder.add(cancel, new Integer(0));  
		cityHolder.repaint();
      cityPanel.setPreferredSize(new Dimension(190, 110));
      cityScrollPane.revalidate();
	   
	   gardensActive = true;
	 }
    if(gardensActive)
    {
      Player[] tempArray = name.getPlayerUI().getPlayers();
      for(int k = 0; k < legalBuilding.size(); k++)  
	   {
        if(buildingButtons[k] != null && e.getSource() == buildingButtons[k])    
        {
          int x = name.getCityXpos(cityNumber);
          int y = name.getCityYpos(cityNumber);

          switch(k)
			 {
			   case 0:
	           name.addBuildingtoCity(cityNumber, 22, 0);      
              for(int i = 0; i < 2; i++)//number of players         
                tempArray[i].playerUI.lb.addBuilding(x-1, y-1, 22, new GameJLabel(name.getCityBuildingLabel(cityNumber, 0).getIcon()), false, name, cityNumber);
				  break;
			   case 1:
	           name.addBuildingtoCity(cityNumber, 22, 1);      
              for(int i = 0; i < 2; i++)//number of players         
                tempArray[i].playerUI.lb.addBuilding(x, y-1, 22, new GameJLabel(name.getCityBuildingLabel(cityNumber, 1).getIcon()), false, name, cityNumber);
				  break;
			   case 2:
	           name.addBuildingtoCity(cityNumber, 22, 2);      
              for(int i = 0; i < 2; i++)//number of players         
                tempArray[i].playerUI.lb.addBuilding(x+1, y-1, 22, new GameJLabel(name.getCityBuildingLabel(cityNumber, 2).getIcon()), false, name, cityNumber);
				  break;
			   case 3:
	           name.addBuildingtoCity(cityNumber, 22, 3);      
              for(int i = 0; i < 2; i++)//number of players         
                tempArray[i].playerUI.lb.addBuilding(x-1, y+1, 22, new GameJLabel(name.getCityBuildingLabel(cityNumber, 3).getIcon()), false, name, cityNumber);
				  break;
			   case 4:
	           name.addBuildingtoCity(cityNumber, 22, 4);      
              for(int i = 0; i < 2; i++)//number of players         
                tempArray[i].playerUI.lb.addBuilding(x, y+1, 22, new GameJLabel(name.getCityBuildingLabel(cityNumber, 4).getIcon()), false, name, cityNumber);
				  break;
			   case 5:
	           name.addBuildingtoCity(cityNumber, 22, 5);      
              for(int i = 0; i < 2; i++)//number of players         
                tempArray[i].playerUI.lb.addBuilding(x+1, y+1, 22, new GameJLabel(name.getCityBuildingLabel(cityNumber, 5).getIcon()), false, name, cityNumber);
				  break;
			   case 6:
	           name.addBuildingtoCity(cityNumber, 22, 6);      
              for(int i = 0; i < 2; i++)//number of players         
                tempArray[i].playerUI.lb.addBuilding(x-1, y, 22, new GameJLabel(name.getCityBuildingLabel(cityNumber, 6).getIcon()), false, name, cityNumber);
				  break;
			   case 7:
	           name.addBuildingtoCity(cityNumber, 22, 7);      
              for(int i = 0; i < 2; i++)//number of players         
                tempArray[i].playerUI.lb.addBuilding(x+1, y, 22, new GameJLabel(name.getCityBuildingLabel(cityNumber, 7).getIcon()), false, name, cityNumber);
				  break;
			 }
	   
          gardensActive = false;
	       gardens.removeActionListener(this);
          GameJLayeredPane temp;
		    for(k = 0; k < legalBuilding.size(); k++)
			 {
			   if(legalBuilding.get(k) != null)
				{
				  temp = (GameJLayeredPane)legalBuilding.get(k);
			     temp.remove(temp.getIndexOf(buildingButtons[k]));
              temp.repaint();
				}    
			 }
		    if(!usedEngineering)  
			   finishCityTurn();
			 else if(builtEngineeringOne)
			   finishCityTurn();
			 else
			 {
            if(name.getTechVector().get(0) != null)
				  engineeringProd = -10;
				else
				  engineeringProd = -15;
		      builtEngineeringOne = true;
			   displayCityOptions(cityNumber);
			 }
		  }
	   }
    }

    if(e.getSource() == oracle)
	 {
		legalBuilding = new Vector(0, 1); 
      Vector icons = new Vector(0, 1);
      int x = name.getCityXpos(cityNumber);
      int y = name.getCityYpos(cityNumber);
		int k, i;
		for(k = x-1; k <= x+1; k++)
		{  
		  i = y-1;
		  if(!lb.getTerrain(k, i).equals("Water") && !lb.getHasBuild(k, i))
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
		  if(!lb.getTerrain(k, i).equals("Water") && !lb.getHasBuild(k, i))
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
		if(!lb.getTerrain(x-1, y).equals("Water") && !lb.getHasBuild(x-1, y))
		{
		  legalBuilding.add(name.playerUI.lb.getHolder(x-1, y));
        icons.add(name.playerUI.lb.getIcon(x-1, y));  
      }
		else
      {
		  legalBuilding.add(null);		 
        icons.add(null);    
		}   
		if(!lb.getTerrain(x+1, y).equals("Water") && !lb.getHasBuild(x+1, y))
		{
		  legalBuilding.add(name.playerUI.lb.getHolder(x+1, y));
        icons.add(name.playerUI.lb.getIcon(x+1, y));  
      }
		else
      {
		  legalBuilding.add(null);		 
        icons.add(null);    
		}   
	   buildingButtons = new GameJButton[legalBuilding.size()];  
		for(k = 0; k < legalBuilding.size(); k++)
		{
        if(legalBuilding.get(k) != null)    
		  {
			  GameJLayeredPane temp = (GameJLayeredPane)legalBuilding.get(k);
	        GameImageIcon icon = (GameImageIcon)icons.get(k);
	        buildingButtons[k] = new GameJButton(icon);
			  buildingButtons[k].setBounds(00, 00, icon.getIconWidth(), icon.getIconHeight());
			  temp.add(buildingButtons[k], new Integer(4));
	        temp.repaint();
			  buildingButtons[k].addActionListener(this);
		  }
		}
		for(k = 0; k < cityVector.size(); k++)
	   {
	     if(cityVector.get(k) != null)  
		    cityButtons[k].removeActionListener(this);
	   }  
	   GameJLabel lb_1 = new GameJLabel("Select position on map");  
      lb_1.setBounds(30, 10, 150, 15);

	   GameJLabel lb_2 = new GameJLabel("to add Oracle!");  
      lb_2.setBounds(45, 25, 110, 15);

		cancel = new GameJButton("cancel");
		cancel.setBounds(40, 80, 100, 20);    
		cancel.addActionListener(this);

      cityHolder.removeAll();
	   cityHolder.add(lb_1, new Integer(0));  
		cityHolder.add(lb_2, new Integer(0));  
		cityHolder.add(cancel, new Integer(0));  
		cityHolder.repaint();
      cityPanel.setPreferredSize(new Dimension(190, 110));
      cityScrollPane.revalidate();
	   
	   oracleActive = true;
	 }
    if(oracleActive)
    {
      Player[] tempArray = name.getPlayerUI().getPlayers();
      for(int k = 0; k < legalBuilding.size(); k++)  
	   {
        if(buildingButtons[k] != null && e.getSource() == buildingButtons[k])    
        {
          int x = name.getCityXpos(cityNumber);
          int y = name.getCityYpos(cityNumber);

          switch(k)
			 {
			   case 0:
	           name.addBuildingtoCity(cityNumber, 23, 0);      
              for(int i = 0; i < 2; i++)//number of players         
                tempArray[i].playerUI.lb.addBuilding(x-1, y-1, 23, new GameJLabel(name.getCityBuildingLabel(cityNumber, 0).getIcon()), false, name, cityNumber);
				  break;
			   case 1:
	           name.addBuildingtoCity(cityNumber, 23, 1);      
              for(int i = 0; i < 2; i++)//number of players         
                tempArray[i].playerUI.lb.addBuilding(x, y-1, 23, new GameJLabel(name.getCityBuildingLabel(cityNumber, 1).getIcon()), false, name, cityNumber);
				  break;
			   case 2:
	           name.addBuildingtoCity(cityNumber, 23, 2);      
              for(int i = 0; i < 2; i++)//number of players         
                tempArray[i].playerUI.lb.addBuilding(x+1, y-1, 23, new GameJLabel(name.getCityBuildingLabel(cityNumber, 2).getIcon()), false, name, cityNumber);
				  break;
			   case 3:
	           name.addBuildingtoCity(cityNumber, 23, 3);      
              for(int i = 0; i < 2; i++)//number of players         
                tempArray[i].playerUI.lb.addBuilding(x-1, y+1, 23, new GameJLabel(name.getCityBuildingLabel(cityNumber, 3).getIcon()), false, name, cityNumber);
				  break;
			   case 4:
	           name.addBuildingtoCity(cityNumber, 23, 4);      
              for(int i = 0; i < 2; i++)//number of players         
                tempArray[i].playerUI.lb.addBuilding(x, y+1, 23, new GameJLabel(name.getCityBuildingLabel(cityNumber, 4).getIcon()), false, name, cityNumber);
				  break;
			   case 5:
	           name.addBuildingtoCity(cityNumber, 23, 5);      
              for(int i = 0; i < 2; i++)//number of players         
                tempArray[i].playerUI.lb.addBuilding(x+1, y+1, 23, new GameJLabel(name.getCityBuildingLabel(cityNumber, 5).getIcon()), false, name, cityNumber);
				  break;
			   case 6:
	           name.addBuildingtoCity(cityNumber, 23, 6);      
              for(int i = 0; i < 2; i++)//number of players         
                tempArray[i].playerUI.lb.addBuilding(x-1, y, 23, new GameJLabel(name.getCityBuildingLabel(cityNumber, 6).getIcon()), false, name, cityNumber);
				  break;
			   case 7:
	           name.addBuildingtoCity(cityNumber, 23, 7);      
              for(int i = 0; i < 2; i++)//number of players         
                tempArray[i].playerUI.lb.addBuilding(x+1, y, 23, new GameJLabel(name.getCityBuildingLabel(cityNumber, 7).getIcon()), false, name, cityNumber);
				  break;
			 }
	   
          oracleActive = false;
	       oracle.removeActionListener(this);
          GameJLayeredPane temp;
		    for(k = 0; k < legalBuilding.size(); k++)
			 {
			   if(legalBuilding.get(k) != null)
				{
				  temp = (GameJLayeredPane)legalBuilding.get(k);
			     temp.remove(temp.getIndexOf(buildingButtons[k]));
              temp.repaint();
				}    
			 }
		    if(!usedEngineering)  
			   finishCityTurn();
			 else if(builtEngineeringOne)
			   finishCityTurn();
			 else
			 {
            if(name.getTechVector().get(1) != null)
				  engineeringProd = -10;
				else
				  engineeringProd = -15;
		      builtEngineeringOne = true;
			   displayCityOptions(cityNumber);
			 }
		  }
	   }
    }

    if(e.getSource() == tower)
	 {
		legalBuilding = new Vector(0, 1); 
      Vector icons = new Vector(0, 1);
      int x = name.getCityXpos(cityNumber);
      int y = name.getCityYpos(cityNumber);
		int k, i;
		for(k = x-1; k <= x+1; k++)
		{  
		  i = y-1;
		  if(!lb.getTerrain(k, i).equals("Water") && !lb.getHasBuild(k, i))
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
		  if(!lb.getTerrain(k, i).equals("Water") && !lb.getHasBuild(k, i))
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
		if(!lb.getTerrain(x-1, y).equals("Water") && !lb.getHasBuild(x-1, y))
		{
		  legalBuilding.add(name.playerUI.lb.getHolder(x-1, y));
        icons.add(name.playerUI.lb.getIcon(x-1, y));  
      }
		else
      {
		  legalBuilding.add(null);		 
        icons.add(null);    
		}   
		if(!lb.getTerrain(x+1, y).equals("Water") && !lb.getHasBuild(x+1, y))
		{
		  legalBuilding.add(name.playerUI.lb.getHolder(x+1, y));
        icons.add(name.playerUI.lb.getIcon(x+1, y));  
      }
		else
      {
		  legalBuilding.add(null);		 
        icons.add(null);    
		}   
	   buildingButtons = new GameJButton[legalBuilding.size()];  
		for(k = 0; k < legalBuilding.size(); k++)
		{
        if(legalBuilding.get(k) != null)    
		  {
			  GameJLayeredPane temp = (GameJLayeredPane)legalBuilding.get(k);
	        GameImageIcon icon = (GameImageIcon)icons.get(k);
	        buildingButtons[k] = new GameJButton(icon);
			  buildingButtons[k].setBounds(00, 00, icon.getIconWidth(), icon.getIconHeight());
			  temp.add(buildingButtons[k], new Integer(4));
	        temp.repaint();
			  buildingButtons[k].addActionListener(this);
		  }
		}
		for(k = 0; k < cityVector.size(); k++)
	   {
	     if(cityVector.get(k) != null)  
		    cityButtons[k].removeActionListener(this);
	   }  
	   GameJLabel lb_1 = new GameJLabel("Select position on map");  
      lb_1.setBounds(30, 10, 150, 15);

	   GameJLabel lb_2 = new GameJLabel("to add Porcelain Tower!");  
      lb_2.setBounds(45, 25, 110, 15);

		cancel = new GameJButton("cancel");
		cancel.setBounds(40, 80, 100, 20);    
		cancel.addActionListener(this);

      cityHolder.removeAll();
	   cityHolder.add(lb_1, new Integer(0));  
		cityHolder.add(lb_2, new Integer(0));  
		cityHolder.add(cancel, new Integer(0));  
		cityHolder.repaint();
      cityPanel.setPreferredSize(new Dimension(190, 110));
      cityScrollPane.revalidate();
	   
	   towerActive = true;
	 }
    if(towerActive)
    {
      Player[] tempArray = name.getPlayerUI().getPlayers();
      for(int k = 0; k < legalBuilding.size(); k++)  
	   {
        if(buildingButtons[k] != null && e.getSource() == buildingButtons[k])    
        {
          int x = name.getCityXpos(cityNumber);
          int y = name.getCityYpos(cityNumber);

          switch(k)
			 {
			   case 0:
	           name.addBuildingtoCity(cityNumber, 24, 0);      
              for(int i = 0; i < 2; i++)//number of players         
                tempArray[i].playerUI.lb.addBuilding(x-1, y-1, 24, new GameJLabel(name.getCityBuildingLabel(cityNumber, 0).getIcon()), false, name, cityNumber);
				  break;
			   case 1:
	           name.addBuildingtoCity(cityNumber, 24, 1);      
              for(int i = 0; i < 2; i++)//number of players         
                tempArray[i].playerUI.lb.addBuilding(x, y-1, 24, new GameJLabel(name.getCityBuildingLabel(cityNumber, 1).getIcon()), false, name, cityNumber);
				  break;
			   case 2:
	           name.addBuildingtoCity(cityNumber, 24, 2);      
              for(int i = 0; i < 2; i++)//number of players         
                tempArray[i].playerUI.lb.addBuilding(x+1, y-1, 24, new GameJLabel(name.getCityBuildingLabel(cityNumber, 2).getIcon()), false, name, cityNumber);
				  break;
			   case 3:
	           name.addBuildingtoCity(cityNumber, 24, 3);      
              for(int i = 0; i < 2; i++)//number of players         
                tempArray[i].playerUI.lb.addBuilding(x-1, y+1, 24, new GameJLabel(name.getCityBuildingLabel(cityNumber, 3).getIcon()), false, name, cityNumber);
				  break;
			   case 4:
	           name.addBuildingtoCity(cityNumber, 24, 4);      
              for(int i = 0; i < 2; i++)//number of players         
                tempArray[i].playerUI.lb.addBuilding(x, y+1, 24, new GameJLabel(name.getCityBuildingLabel(cityNumber, 4).getIcon()), false, name, cityNumber);
				  break;
			   case 5:
	           name.addBuildingtoCity(cityNumber, 24, 5);      
              for(int i = 0; i < 2; i++)//number of players         
                tempArray[i].playerUI.lb.addBuilding(x+1, y+1, 24, new GameJLabel(name.getCityBuildingLabel(cityNumber, 5).getIcon()), false, name, cityNumber);
				  break;
			   case 6:
	           name.addBuildingtoCity(cityNumber, 24, 6);      
              for(int i = 0; i < 2; i++)//number of players         
                tempArray[i].playerUI.lb.addBuilding(x-1, y, 24, new GameJLabel(name.getCityBuildingLabel(cityNumber, 6).getIcon()), false, name, cityNumber);
				  break;
			   case 7:
	           name.addBuildingtoCity(cityNumber, 24, 7);      
              for(int i = 0; i < 2; i++)//number of players         
                tempArray[i].playerUI.lb.addBuilding(x+1, y, 24, new GameJLabel(name.getCityBuildingLabel(cityNumber, 7).getIcon()), false, name, cityNumber);
				  break;
			 }
	   
          towerActive = false;
	       tower.removeActionListener(this);
          GameJLayeredPane temp;
		    for(k = 0; k < legalBuilding.size(); k++)
			 {
			   if(legalBuilding.get(k) != null)
				{
				  temp = (GameJLayeredPane)legalBuilding.get(k);
			     temp.remove(temp.getIndexOf(buildingButtons[k]));
              temp.repaint();
				}    
			 }
		    if(!usedEngineering)  
			   finishCityTurn();
			 else if(builtEngineeringOne)
			   finishCityTurn();
			 else
			 {
            if(name.getTechVector().get(12) != null)
				  engineeringProd = -15;
				else
				  engineeringProd = -20;
		      builtEngineeringOne = true;
			   displayCityOptions(cityNumber);
			 }
		  }
	   }
    }

    if(e.getSource() == angkor)
	 {
		legalBuilding = new Vector(0, 1); 
      Vector icons = new Vector(0, 1);
      int x = name.getCityXpos(cityNumber);
      int y = name.getCityYpos(cityNumber);
		int k, i;
		for(k = x-1; k <= x+1; k++)
		{  
		  i = y-1;
		  if(!lb.getTerrain(k, i).equals("Water") && !lb.getHasBuild(k, i))
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
		  if(!lb.getTerrain(k, i).equals("Water") && !lb.getHasBuild(k, i))
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
		if(!lb.getTerrain(x-1, y).equals("Water") && !lb.getHasBuild(x-1, y))
		{
		  legalBuilding.add(name.playerUI.lb.getHolder(x-1, y));
        icons.add(name.playerUI.lb.getIcon(x-1, y));  
      }
		else
      {
		  legalBuilding.add(null);		 
        icons.add(null);    
		}   
		if(!lb.getTerrain(x+1, y).equals("Water") && !lb.getHasBuild(x+1, y))
		{
		  legalBuilding.add(name.playerUI.lb.getHolder(x+1, y));
        icons.add(name.playerUI.lb.getIcon(x+1, y));  
      }
		else
      {
		  legalBuilding.add(null);		 
        icons.add(null);    
		}   
	   buildingButtons = new GameJButton[legalBuilding.size()];  
		for(k = 0; k < legalBuilding.size(); k++)
		{
        if(legalBuilding.get(k) != null)    
		  {
			  GameJLayeredPane temp = (GameJLayeredPane)legalBuilding.get(k);
	        GameImageIcon icon = (GameImageIcon)icons.get(k);
	        buildingButtons[k] = new GameJButton(icon);
			  buildingButtons[k].setBounds(00, 00, icon.getIconWidth(), icon.getIconHeight());
			  temp.add(buildingButtons[k], new Integer(4));
	        temp.repaint();
			  buildingButtons[k].addActionListener(this);
		  }
		}
		for(k = 0; k < cityVector.size(); k++)
	   {
	     if(cityVector.get(k) != null)  
		    cityButtons[k].removeActionListener(this);
	   }  
	   GameJLabel lb_1 = new GameJLabel("Select position on map");  
      lb_1.setBounds(30, 10, 150, 15);

	   GameJLabel lb_2 = new GameJLabel("to add Angkor Wat!");  
      lb_2.setBounds(45, 25, 110, 15);

		cancel = new GameJButton("cancel");
		cancel.setBounds(40, 80, 100, 20);    
		cancel.addActionListener(this);

      cityHolder.removeAll();
	   cityHolder.add(lb_1, new Integer(0));  
		cityHolder.add(lb_2, new Integer(0));  
		cityHolder.add(cancel, new Integer(0));  
		cityHolder.repaint();
      cityPanel.setPreferredSize(new Dimension(190, 110));
      cityScrollPane.revalidate();
	   
	   angkorActive = true;
	 }
    if(angkorActive)
    {
      Player[] tempArray = name.getPlayerUI().getPlayers();
      for(int k = 0; k < legalBuilding.size(); k++)  
	   {
        if(buildingButtons[k] != null && e.getSource() == buildingButtons[k])    
        {
          int x = name.getCityXpos(cityNumber);
          int y = name.getCityYpos(cityNumber);

          switch(k)
			 {
			   case 0:
	           name.addBuildingtoCity(cityNumber, 25, 0);      
              for(int i = 0; i < 2; i++)//number of players         
                tempArray[i].playerUI.lb.addBuilding(x-1, y-1, 25, new GameJLabel(name.getCityBuildingLabel(cityNumber, 0).getIcon()), false, name, cityNumber);
				  break;
			   case 1:
	           name.addBuildingtoCity(cityNumber, 25, 1);      
              for(int i = 0; i < 2; i++)//number of players         
                tempArray[i].playerUI.lb.addBuilding(x, y-1, 25, new GameJLabel(name.getCityBuildingLabel(cityNumber, 1).getIcon()), false, name, cityNumber);
				  break;
			   case 2:
	           name.addBuildingtoCity(cityNumber, 25, 2);      
              for(int i = 0; i < 2; i++)//number of players         
                tempArray[i].playerUI.lb.addBuilding(x+1, y-1, 25, new GameJLabel(name.getCityBuildingLabel(cityNumber, 2).getIcon()), false, name, cityNumber);
				  break;
			   case 3:
	           name.addBuildingtoCity(cityNumber, 25, 3);      
              for(int i = 0; i < 2; i++)//number of players         
                tempArray[i].playerUI.lb.addBuilding(x-1, y+1, 25, new GameJLabel(name.getCityBuildingLabel(cityNumber, 3).getIcon()), false, name, cityNumber);
				  break;
			   case 4:
	           name.addBuildingtoCity(cityNumber, 25, 4);      
              for(int i = 0; i < 2; i++)//number of players         
                tempArray[i].playerUI.lb.addBuilding(x, y+1, 25, new GameJLabel(name.getCityBuildingLabel(cityNumber, 4).getIcon()), false, name, cityNumber);
				  break;
			   case 5:
	           name.addBuildingtoCity(cityNumber, 25, 5);      
              for(int i = 0; i < 2; i++)//number of players         
                tempArray[i].playerUI.lb.addBuilding(x+1, y+1, 25, new GameJLabel(name.getCityBuildingLabel(cityNumber, 5).getIcon()), false, name, cityNumber);
				  break;
			   case 6:
	           name.addBuildingtoCity(cityNumber, 25, 6);      
              for(int i = 0; i < 2; i++)//number of players         
                tempArray[i].playerUI.lb.addBuilding(x-1, y, 25, new GameJLabel(name.getCityBuildingLabel(cityNumber, 6).getIcon()), false, name, cityNumber);
				  break;
			   case 7:
	           name.addBuildingtoCity(cityNumber, 25, 7);      
              for(int i = 0; i < 2; i++)//number of players         
                tempArray[i].playerUI.lb.addBuilding(x+1, y, 25, new GameJLabel(name.getCityBuildingLabel(cityNumber, 7).getIcon()), false, name, cityNumber);
				  break;
			 }
	   
          angkorActive = false;
	       angkor.removeActionListener(this);
          GameJLayeredPane temp;
		    for(k = 0; k < legalBuilding.size(); k++)
			 {
			   if(legalBuilding.get(k) != null)
				{
				  temp = (GameJLayeredPane)legalBuilding.get(k);
			     temp.remove(temp.getIndexOf(buildingButtons[k]));
              temp.repaint();
				}    
			 }
		    if(!usedEngineering)  
			   finishCityTurn();
			 else if(builtEngineeringOne)
			   finishCityTurn();
			 else
			 {
            if(name.getTechVector().get(7) != null)
				  engineeringProd = -15;
				else
				  engineeringProd = -20;
		      builtEngineeringOne = true;
			   displayCityOptions(cityNumber);
			 }
		  }
	   }
    }

    if(e.getSource() == castle)
	 {
		legalBuilding = new Vector(0, 1); 
      Vector icons = new Vector(0, 1);
      int x = name.getCityXpos(cityNumber);
      int y = name.getCityYpos(cityNumber);
		int k, i;
		for(k = x-1; k <= x+1; k++)
		{  
		  i = y-1;
		  if(!lb.getTerrain(k, i).equals("Water") && !lb.getHasBuild(k, i))
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
		  if(!lb.getTerrain(k, i).equals("Water") && !lb.getHasBuild(k, i))
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
		if(!lb.getTerrain(x-1, y).equals("Water") && !lb.getHasBuild(x-1, y))
		{
		  legalBuilding.add(name.playerUI.lb.getHolder(x-1, y));
        icons.add(name.playerUI.lb.getIcon(x-1, y));  
      }
		else
      {
		  legalBuilding.add(null);		 
        icons.add(null);    
		}   
		if(!lb.getTerrain(x+1, y).equals("Water") && !lb.getHasBuild(x+1, y))
		{
		  legalBuilding.add(name.playerUI.lb.getHolder(x+1, y));
        icons.add(name.playerUI.lb.getIcon(x+1, y));  
      }
		else
      {
		  legalBuilding.add(null);		 
        icons.add(null);    
		}   
	   buildingButtons = new GameJButton[legalBuilding.size()];  
		for(k = 0; k < legalBuilding.size(); k++)
		{
        if(legalBuilding.get(k) != null)    
		  {
			  GameJLayeredPane temp = (GameJLayeredPane)legalBuilding.get(k);
	        GameImageIcon icon = (GameImageIcon)icons.get(k);
	        buildingButtons[k] = new GameJButton(icon);
			  buildingButtons[k].setBounds(00, 00, icon.getIconWidth(), icon.getIconHeight());
			  temp.add(buildingButtons[k], new Integer(4));
	        temp.repaint();
			  buildingButtons[k].addActionListener(this);
		  }
		}
		for(k = 0; k < cityVector.size(); k++)
	   {
	     if(cityVector.get(k) != null)  
		    cityButtons[k].removeActionListener(this);
	   }  
	   GameJLabel lb_1 = new GameJLabel("Select position on map");  
      lb_1.setBounds(30, 10, 150, 15);

	   GameJLabel lb_2 = new GameJLabel("to add Himeji Castle!");  
      lb_2.setBounds(45, 25, 110, 15);

		cancel = new GameJButton("cancel");
		cancel.setBounds(40, 80, 100, 20);    
		cancel.addActionListener(this);

      cityHolder.removeAll();
	   cityHolder.add(lb_1, new Integer(0));  
		cityHolder.add(lb_2, new Integer(0));  
		cityHolder.add(cancel, new Integer(0));  
		cityHolder.repaint();
      cityPanel.setPreferredSize(new Dimension(190, 110));
      cityScrollPane.revalidate();
	   
	   castleActive = true;
	 }
    if(castleActive)
    {
      Player[] tempArray = name.getPlayerUI().getPlayers();
      for(int k = 0; k < legalBuilding.size(); k++)  
	   {
        if(buildingButtons[k] != null && e.getSource() == buildingButtons[k])    
        {
          int x = name.getCityXpos(cityNumber);
          int y = name.getCityYpos(cityNumber);

          switch(k)
			 {
			   case 0:
	           name.addBuildingtoCity(cityNumber, 26, 0);      
              for(int i = 0; i < 2; i++)//number of players         
                tempArray[i].playerUI.lb.addBuilding(x-1, y-1, 26, new GameJLabel(name.getCityBuildingLabel(cityNumber, 0).getIcon()), false, name, cityNumber);
				  break;
			   case 1:
	           name.addBuildingtoCity(cityNumber, 26, 1);      
              for(int i = 0; i < 2; i++)//number of players         
                tempArray[i].playerUI.lb.addBuilding(x, y-1, 26, new GameJLabel(name.getCityBuildingLabel(cityNumber, 1).getIcon()), false, name, cityNumber);
				  break;
			   case 2:
	           name.addBuildingtoCity(cityNumber, 26, 2);      
              for(int i = 0; i < 2; i++)//number of players         
                tempArray[i].playerUI.lb.addBuilding(x+1, y-1, 26, new GameJLabel(name.getCityBuildingLabel(cityNumber, 2).getIcon()), false, name, cityNumber);
				  break;
			   case 3:
	           name.addBuildingtoCity(cityNumber, 26, 3);      
              for(int i = 0; i < 2; i++)//number of players         
                tempArray[i].playerUI.lb.addBuilding(x-1, y+1, 26, new GameJLabel(name.getCityBuildingLabel(cityNumber, 3).getIcon()), false, name, cityNumber);
				  break;
			   case 4:
	           name.addBuildingtoCity(cityNumber, 26, 4);      
              for(int i = 0; i < 2; i++)//number of players         
                tempArray[i].playerUI.lb.addBuilding(x, y+1, 26, new GameJLabel(name.getCityBuildingLabel(cityNumber, 4).getIcon()), false, name, cityNumber);
				  break;
			   case 5:
	           name.addBuildingtoCity(cityNumber, 26, 5);      
              for(int i = 0; i < 2; i++)//number of players         
                tempArray[i].playerUI.lb.addBuilding(x+1, y+1, 26, new GameJLabel(name.getCityBuildingLabel(cityNumber, 5).getIcon()), false, name, cityNumber);
				  break;
			   case 6:
	           name.addBuildingtoCity(cityNumber, 26, 6);      
              for(int i = 0; i < 2; i++)//number of players         
                tempArray[i].playerUI.lb.addBuilding(x-1, y, 26, new GameJLabel(name.getCityBuildingLabel(cityNumber, 6).getIcon()), false, name, cityNumber);
				  break;
			   case 7:
	           name.addBuildingtoCity(cityNumber, 26, 7);      
              for(int i = 0; i < 2; i++)//number of players         
                tempArray[i].playerUI.lb.addBuilding(x+1, y, 26, new GameJLabel(name.getCityBuildingLabel(cityNumber, 7).getIcon()), false, name, cityNumber);
				  break;
			 }
	   
          castleActive = false;
	       castle.removeActionListener(this);
          GameJLayeredPane temp;
		    for(k = 0; k < legalBuilding.size(); k++)
			 {
			   if(legalBuilding.get(k) != null)
				{
				  temp = (GameJLayeredPane)legalBuilding.get(k);
			     temp.remove(temp.getIndexOf(buildingButtons[k]));
              temp.repaint();
				}    
			 }
		    if(!usedEngineering)  
			   finishCityTurn();
			 else if(builtEngineeringOne)
			   finishCityTurn();
			 else
			 {
            if(name.getTechVector().get(17) != null)
				  engineeringProd = -15;
				else
				  engineeringProd = -20;
		      builtEngineeringOne = true;
			   displayCityOptions(cityNumber);
			 }
		  }
	   }
    }

    if(e.getSource() == louvre)
	 {
		legalBuilding = new Vector(0, 1); 
      Vector icons = new Vector(0, 1);
      int x = name.getCityXpos(cityNumber);
      int y = name.getCityYpos(cityNumber);
		int k, i;
		for(k = x-1; k <= x+1; k++)
		{  
		  i = y-1;
		  if(!lb.getTerrain(k, i).equals("Water") && !lb.getHasBuild(k, i))
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
		  if(!lb.getTerrain(k, i).equals("Water") && !lb.getHasBuild(k, i))
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
		if(!lb.getTerrain(x-1, y).equals("Water") && !lb.getHasBuild(x-1, y))
		{
		  legalBuilding.add(name.playerUI.lb.getHolder(x-1, y));
        icons.add(name.playerUI.lb.getIcon(x-1, y));  
      }
		else
      {
		  legalBuilding.add(null);		 
        icons.add(null);    
		}   
		if(!lb.getTerrain(x+1, y).equals("Water") && !lb.getHasBuild(x+1, y))
		{
		  legalBuilding.add(name.playerUI.lb.getHolder(x+1, y));
        icons.add(name.playerUI.lb.getIcon(x+1, y));  
      }
		else
      {
		  legalBuilding.add(null);		 
        icons.add(null);    
		}   
	   buildingButtons = new GameJButton[legalBuilding.size()];  
		for(k = 0; k < legalBuilding.size(); k++)
		{
        if(legalBuilding.get(k) != null)    
		  {
			  GameJLayeredPane temp = (GameJLayeredPane)legalBuilding.get(k);
	        GameImageIcon icon = (GameImageIcon)icons.get(k);
	        buildingButtons[k] = new GameJButton(icon);
			  buildingButtons[k].setBounds(00, 00, icon.getIconWidth(), icon.getIconHeight());
			  temp.add(buildingButtons[k], new Integer(4));
	        temp.repaint();
			  buildingButtons[k].addActionListener(this);
		  }
		}
		for(k = 0; k < cityVector.size(); k++)
	   {
	     if(cityVector.get(k) != null)  
		    cityButtons[k].removeActionListener(this);
	   }  
	   GameJLabel lb_1 = new GameJLabel("Select position on map");  
      lb_1.setBounds(30, 10, 150, 15);

	   GameJLabel lb_2 = new GameJLabel("to add Louvre!");  
      lb_2.setBounds(45, 25, 110, 15);

		cancel = new GameJButton("cancel");
		cancel.setBounds(40, 80, 100, 20);    
		cancel.addActionListener(this);

      cityHolder.removeAll();
	   cityHolder.add(lb_1, new Integer(0));  
		cityHolder.add(lb_2, new Integer(0));  
		cityHolder.add(cancel, new Integer(0));  
		cityHolder.repaint();
      cityPanel.setPreferredSize(new Dimension(190, 110));
      cityScrollPane.revalidate();
	   
	   louvreActive = true;
	 }
    if(louvreActive)
    {
      Player[] tempArray = name.getPlayerUI().getPlayers();
      for(int k = 0; k < legalBuilding.size(); k++)  
	   {
        if(buildingButtons[k] != null && e.getSource() == buildingButtons[k])    
        {
          int x = name.getCityXpos(cityNumber);
          int y = name.getCityYpos(cityNumber);

          switch(k)
			 {
			   case 0:
	           name.addBuildingtoCity(cityNumber, 27, 0);      
              for(int i = 0; i < 2; i++)//number of players         
                tempArray[i].playerUI.lb.addBuilding(x-1, y-1, 27, new GameJLabel(name.getCityBuildingLabel(cityNumber, 0).getIcon()), false, name, cityNumber);
				  break;
			   case 1:
	           name.addBuildingtoCity(cityNumber, 27, 1);      
              for(int i = 0; i < 2; i++)//number of players         
                tempArray[i].playerUI.lb.addBuilding(x, y-1, 27, new GameJLabel(name.getCityBuildingLabel(cityNumber, 1).getIcon()), false, name, cityNumber);
				  break;
			   case 2:
	           name.addBuildingtoCity(cityNumber, 27, 2);      
              for(int i = 0; i < 2; i++)//number of players         
                tempArray[i].playerUI.lb.addBuilding(x+1, y-1, 27, new GameJLabel(name.getCityBuildingLabel(cityNumber, 2).getIcon()), false, name, cityNumber);
				  break;
			   case 3:
	           name.addBuildingtoCity(cityNumber, 27, 3);      
              for(int i = 0; i < 2; i++)//number of players         
                tempArray[i].playerUI.lb.addBuilding(x-1, y+1, 27, new GameJLabel(name.getCityBuildingLabel(cityNumber, 3).getIcon()), false, name, cityNumber);
				  break;
			   case 4:
	           name.addBuildingtoCity(cityNumber, 27, 4);      
              for(int i = 0; i < 2; i++)//number of players         
                tempArray[i].playerUI.lb.addBuilding(x, y+1, 27, new GameJLabel(name.getCityBuildingLabel(cityNumber, 4).getIcon()), false, name, cityNumber);
				  break;
			   case 5:
	           name.addBuildingtoCity(cityNumber, 27, 5);      
              for(int i = 0; i < 2; i++)//number of players         
                tempArray[i].playerUI.lb.addBuilding(x+1, y+1, 27, new GameJLabel(name.getCityBuildingLabel(cityNumber, 5).getIcon()), false, name, cityNumber);
				  break;
			   case 6:
	           name.addBuildingtoCity(cityNumber, 27, 6);      
              for(int i = 0; i < 2; i++)//number of players         
                tempArray[i].playerUI.lb.addBuilding(x-1, y, 27, new GameJLabel(name.getCityBuildingLabel(cityNumber, 6).getIcon()), false, name, cityNumber);
				  break;
			   case 7:
	           name.addBuildingtoCity(cityNumber, 27, 7);      
              for(int i = 0; i < 2; i++)//number of players         
                tempArray[i].playerUI.lb.addBuilding(x+1, y, 27, new GameJLabel(name.getCityBuildingLabel(cityNumber, 7).getIcon()), false, name, cityNumber);
				  break;
			 }
	   
          louvreActive = false;
	       louvre.removeActionListener(this);
          GameJLayeredPane temp;
		    for(k = 0; k < legalBuilding.size(); k++)
			 {
			   if(legalBuilding.get(k) != null)
				{
				  temp = (GameJLayeredPane)legalBuilding.get(k);
			     temp.remove(temp.getIndexOf(buildingButtons[k]));
              temp.repaint();
				}    
			 }
		    if(!usedEngineering)  
			   finishCityTurn();
			 else if(builtEngineeringOne)
			   finishCityTurn();
			 else
			 {
            if(name.getTechVector().get(18) != null)
				  engineeringProd = -15;
				else
				  engineeringProd = -20;
		      builtEngineeringOne = true;
			   displayCityOptions(cityNumber);
			 }
		  }
	   }
    }

    if(e.getSource() == panama)
	 {
		legalBuilding = new Vector(0, 1); 
      Vector icons = new Vector(0, 1);
      int x = name.getCityXpos(cityNumber);
      int y = name.getCityYpos(cityNumber);
		int k, i;
		for(k = x-1; k <= x+1; k++)
		{  
		  i = y-1;
		  if(!lb.getTerrain(k, i).equals("Water") && !lb.getHasBuild(k, i))
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
		  if(!lb.getTerrain(k, i).equals("Water") && !lb.getHasBuild(k, i))
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
		if(!lb.getTerrain(x-1, y).equals("Water") && !lb.getHasBuild(x-1, y))
		{
		  legalBuilding.add(name.playerUI.lb.getHolder(x-1, y));
        icons.add(name.playerUI.lb.getIcon(x-1, y));  
      }
		else
      {
		  legalBuilding.add(null);		 
        icons.add(null);    
		}   
		if(!lb.getTerrain(x+1, y).equals("Water") && !lb.getHasBuild(x+1, y))
		{
		  legalBuilding.add(name.playerUI.lb.getHolder(x+1, y));
        icons.add(name.playerUI.lb.getIcon(x+1, y));  
      }
		else
      {
		  legalBuilding.add(null);		 
        icons.add(null);    
		}   
	   buildingButtons = new GameJButton[legalBuilding.size()];  
		for(k = 0; k < legalBuilding.size(); k++)
		{
        if(legalBuilding.get(k) != null)    
		  {
			  GameJLayeredPane temp = (GameJLayeredPane)legalBuilding.get(k);
	        GameImageIcon icon = (GameImageIcon)icons.get(k);
	        buildingButtons[k] = new GameJButton(icon);
			  buildingButtons[k].setBounds(00, 00, icon.getIconWidth(), icon.getIconHeight());
			  temp.add(buildingButtons[k], new Integer(4));
	        temp.repaint();
			  buildingButtons[k].addActionListener(this);
		  }
		}
		for(k = 0; k < cityVector.size(); k++)
	   {
	     if(cityVector.get(k) != null)  
		    cityButtons[k].removeActionListener(this);
	   }  
	   GameJLabel lb_1 = new GameJLabel("Select position on map");  
      lb_1.setBounds(30, 10, 150, 15);

	   GameJLabel lb_2 = new GameJLabel("to add Panama Canal!");  
      lb_2.setBounds(45, 25, 110, 15);

		cancel = new GameJButton("cancel");
		cancel.setBounds(40, 80, 100, 20);    
		cancel.addActionListener(this);

      cityHolder.removeAll();
	   cityHolder.add(lb_1, new Integer(0));  
		cityHolder.add(lb_2, new Integer(0));  
		cityHolder.add(cancel, new Integer(0));  
		cityHolder.repaint();
      cityPanel.setPreferredSize(new Dimension(190, 110));
      cityScrollPane.revalidate();
	   
	   panamaActive = true;
	 }
    if(panamaActive)
    {
      Player[] tempArray = name.getPlayerUI().getPlayers();
      for(int k = 0; k < legalBuilding.size(); k++)  
	   {
        if(buildingButtons[k] != null && e.getSource() == buildingButtons[k])    
        {
          int x = name.getCityXpos(cityNumber);
          int y = name.getCityYpos(cityNumber);

          switch(k)
			 {
			   case 0:
	           name.addBuildingtoCity(cityNumber, 28, 0);      
              for(int i = 0; i < 2; i++)//number of players         
                tempArray[i].playerUI.lb.addBuilding(x-1, y-1, 28, new GameJLabel(name.getCityBuildingLabel(cityNumber, 0).getIcon()), false, name, cityNumber);
				  break;
			   case 1:
	           name.addBuildingtoCity(cityNumber, 28, 1);      
              for(int i = 0; i < 2; i++)//number of players         
                tempArray[i].playerUI.lb.addBuilding(x, y-1, 28, new GameJLabel(name.getCityBuildingLabel(cityNumber, 1).getIcon()), false, name, cityNumber);
				  break;
			   case 2:
	           name.addBuildingtoCity(cityNumber, 28, 2);      
              for(int i = 0; i < 2; i++)//number of players         
                tempArray[i].playerUI.lb.addBuilding(x+1, y-1, 28, new GameJLabel(name.getCityBuildingLabel(cityNumber, 2).getIcon()), false, name, cityNumber);
				  break;
			   case 3:
	           name.addBuildingtoCity(cityNumber, 28, 3);      
              for(int i = 0; i < 2; i++)//number of players         
                tempArray[i].playerUI.lb.addBuilding(x-1, y+1, 28, new GameJLabel(name.getCityBuildingLabel(cityNumber, 3).getIcon()), false, name, cityNumber);
				  break;
			   case 4:
	           name.addBuildingtoCity(cityNumber, 28, 4);      
              for(int i = 0; i < 2; i++)//number of players         
                tempArray[i].playerUI.lb.addBuilding(x, y+1, 28, new GameJLabel(name.getCityBuildingLabel(cityNumber, 4).getIcon()), false, name, cityNumber);
				  break;
			   case 5:
	           name.addBuildingtoCity(cityNumber, 28, 5);      
              for(int i = 0; i < 2; i++)//number of players         
                tempArray[i].playerUI.lb.addBuilding(x+1, y+1, 28, new GameJLabel(name.getCityBuildingLabel(cityNumber, 5).getIcon()), false, name, cityNumber);
				  break;
			   case 6:
	           name.addBuildingtoCity(cityNumber, 28, 6);      
              for(int i = 0; i < 2; i++)//number of players         
                tempArray[i].playerUI.lb.addBuilding(x-1, y, 28, new GameJLabel(name.getCityBuildingLabel(cityNumber, 6).getIcon()), false, name, cityNumber);
				  break;
			   case 7:
	           name.addBuildingtoCity(cityNumber, 28, 7);      
              for(int i = 0; i < 2; i++)//number of players         
                tempArray[i].playerUI.lb.addBuilding(x+1, y, 28, new GameJLabel(name.getCityBuildingLabel(cityNumber, 7).getIcon()), false, name, cityNumber);
				  break;
			 }
	   
          panamaActive = false;
	       panama.removeActionListener(this);
          GameJLayeredPane temp;
		    for(k = 0; k < legalBuilding.size(); k++)
			 {
			   if(legalBuilding.get(k) != null)
				{
				  temp = (GameJLayeredPane)legalBuilding.get(k);
			     temp.remove(temp.getIndexOf(buildingButtons[k]));
              temp.repaint();
				}    
			 }
		    if(!usedEngineering)  
			   finishCityTurn();
			 else if(builtEngineeringOne)
			   finishCityTurn();
			 else
			 {
            if(name.getTechVector().get(14) != null)
				  engineeringProd = -20;
				else
				  engineeringProd = -25;
		      builtEngineeringOne = true;
			   displayCityOptions(cityNumber);
			 }
		  }
	   }
    }

    if(e.getSource() == statue)
	 {
		legalBuilding = new Vector(0, 1); 
      Vector icons = new Vector(0, 1);
      int x = name.getCityXpos(cityNumber);
      int y = name.getCityYpos(cityNumber);
		int k, i;
		for(k = x-1; k <= x+1; k++)
		{  
		  i = y-1;
		  if(!lb.getTerrain(k, i).equals("Water") && !lb.getHasBuild(k, i))
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
		  if(!lb.getTerrain(k, i).equals("Water") && !lb.getHasBuild(k, i))
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
		if(!lb.getTerrain(x-1, y).equals("Water") && !lb.getHasBuild(x-1, y))
		{
		  legalBuilding.add(name.playerUI.lb.getHolder(x-1, y));
        icons.add(name.playerUI.lb.getIcon(x-1, y));  
      }
		else
      {
		  legalBuilding.add(null);		 
        icons.add(null);    
		}   
		if(!lb.getTerrain(x+1, y).equals("Water") && !lb.getHasBuild(x+1, y))
		{
		  legalBuilding.add(name.playerUI.lb.getHolder(x+1, y));
        icons.add(name.playerUI.lb.getIcon(x+1, y));  
      }
		else
      {
		  legalBuilding.add(null);		 
        icons.add(null);    
		}   
	   buildingButtons = new GameJButton[legalBuilding.size()];  
		for(k = 0; k < legalBuilding.size(); k++)
		{
        if(legalBuilding.get(k) != null)    
		  {
			  GameJLayeredPane temp = (GameJLayeredPane)legalBuilding.get(k);
	        GameImageIcon icon = (GameImageIcon)icons.get(k);
	        buildingButtons[k] = new GameJButton(icon);
			  buildingButtons[k].setBounds(00, 00, icon.getIconWidth(), icon.getIconHeight());
			  temp.add(buildingButtons[k], new Integer(4));
	        temp.repaint();
			  buildingButtons[k].addActionListener(this);
		  }
		}
		for(k = 0; k < cityVector.size(); k++)
	   {
	     if(cityVector.get(k) != null)  
		    cityButtons[k].removeActionListener(this);
	   }  
	   GameJLabel lb_1 = new GameJLabel("Select position on map");  
      lb_1.setBounds(30, 10, 150, 15);

	   GameJLabel lb_2 = new GameJLabel("to add Statue of Liberty!");  
      lb_2.setBounds(45, 25, 110, 15);

		cancel = new GameJButton("cancel");
		cancel.setBounds(40, 80, 100, 20);    
		cancel.addActionListener(this);

      cityHolder.removeAll();
	   cityHolder.add(lb_1, new Integer(0));  
		cityHolder.add(lb_2, new Integer(0));  
		cityHolder.add(cancel, new Integer(0));  
		cityHolder.repaint();
      cityPanel.setPreferredSize(new Dimension(190, 110));
      cityScrollPane.revalidate();
	   
	   statueActive = true;
	 }
    if(statueActive)
    {
      Player[] tempArray = name.getPlayerUI().getPlayers();
      for(int k = 0; k < legalBuilding.size(); k++)  
	   {
        if(buildingButtons[k] != null && e.getSource() == buildingButtons[k])    
        {
          int x = name.getCityXpos(cityNumber);
          int y = name.getCityYpos(cityNumber);

          switch(k)
			 {
			   case 0:
	           name.addBuildingtoCity(cityNumber, 29, 0);      
              for(int i = 0; i < 2; i++)//number of players         
                tempArray[i].playerUI.lb.addBuilding(x-1, y-1, 29, new GameJLabel(name.getCityBuildingLabel(cityNumber, 0).getIcon()), false, name, cityNumber);
				  break;
			   case 1:
	           name.addBuildingtoCity(cityNumber, 29, 1);      
              for(int i = 0; i < 2; i++)//number of players         
                tempArray[i].playerUI.lb.addBuilding(x, y-1, 29, new GameJLabel(name.getCityBuildingLabel(cityNumber, 1).getIcon()), false, name, cityNumber);
				  break;
			   case 2:
	           name.addBuildingtoCity(cityNumber, 29, 2);      
              for(int i = 0; i < 2; i++)//number of players         
                tempArray[i].playerUI.lb.addBuilding(x+1, y-1, 29, new GameJLabel(name.getCityBuildingLabel(cityNumber, 2).getIcon()), false, name, cityNumber);
				  break;
			   case 3:
	           name.addBuildingtoCity(cityNumber, 29, 3);      
              for(int i = 0; i < 2; i++)//number of players         
                tempArray[i].playerUI.lb.addBuilding(x-1, y+1, 29, new GameJLabel(name.getCityBuildingLabel(cityNumber, 3).getIcon()), false, name, cityNumber);
				  break;
			   case 4:
	           name.addBuildingtoCity(cityNumber, 29, 4);      
              for(int i = 0; i < 2; i++)//number of players         
                tempArray[i].playerUI.lb.addBuilding(x, y+1, 29, new GameJLabel(name.getCityBuildingLabel(cityNumber, 4).getIcon()), false, name, cityNumber);
				  break;
			   case 5:
	           name.addBuildingtoCity(cityNumber, 29, 5);      
              for(int i = 0; i < 2; i++)//number of players         
                tempArray[i].playerUI.lb.addBuilding(x+1, y+1, 29, new GameJLabel(name.getCityBuildingLabel(cityNumber, 5).getIcon()), false, name, cityNumber);
				  break;
			   case 6:
	           name.addBuildingtoCity(cityNumber, 29, 6);      
              for(int i = 0; i < 2; i++)//number of players         
                tempArray[i].playerUI.lb.addBuilding(x-1, y, 29, new GameJLabel(name.getCityBuildingLabel(cityNumber, 6).getIcon()), false, name, cityNumber);
				  break;
			   case 7:
	           name.addBuildingtoCity(cityNumber, 29, 7);      
              for(int i = 0; i < 2; i++)//number of players         
                tempArray[i].playerUI.lb.addBuilding(x+1, y, 29, new GameJLabel(name.getCityBuildingLabel(cityNumber, 7).getIcon()), false, name, cityNumber);
				  break;
			 }
	   
          statueActive = false;
	       statue.removeActionListener(this);
          GameJLayeredPane temp;
		    for(k = 0; k < legalBuilding.size(); k++)
			 {
			   if(legalBuilding.get(k) != null)
				{
				  temp = (GameJLayeredPane)legalBuilding.get(k);
			     temp.remove(temp.getIndexOf(buildingButtons[k]));
              temp.repaint();
				}    
			 }
		    if(!usedEngineering)  
			   finishCityTurn();
			 else if(builtEngineeringOne)
			   finishCityTurn();
			 else
			 {
            if(name.getTechVector().get(24) != null)
				  engineeringProd = -20;
				else
				  engineeringProd = -25;
		      builtEngineeringOne = true;
			   displayCityOptions(cityNumber);
			 }
		  }
	   }
    }

    if(e.getSource() == opera)
	 {
		legalBuilding = new Vector(0, 1); 
      Vector icons = new Vector(0, 1);
      int x = name.getCityXpos(cityNumber);
      int y = name.getCityYpos(cityNumber);
		int k, i;
		for(k = x-1; k <= x+1; k++)
		{  
		  i = y-1;
		  if(!lb.getTerrain(k, i).equals("Water") && !lb.getHasBuild(k, i))
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
		  if(!lb.getTerrain(k, i).equals("Water") && !lb.getHasBuild(k, i))
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
		if(!lb.getTerrain(x-1, y).equals("Water") && !lb.getHasBuild(x-1, y))
		{
		  legalBuilding.add(name.playerUI.lb.getHolder(x-1, y));
        icons.add(name.playerUI.lb.getIcon(x-1, y));  
      }
		else
      {
		  legalBuilding.add(null);		 
        icons.add(null);    
		}   
		if(!lb.getTerrain(x+1, y).equals("Water") && !lb.getHasBuild(x+1, y))
		{
		  legalBuilding.add(name.playerUI.lb.getHolder(x+1, y));
        icons.add(name.playerUI.lb.getIcon(x+1, y));  
      }
		else
      {
		  legalBuilding.add(null);		 
        icons.add(null);    
		}   
	   buildingButtons = new GameJButton[legalBuilding.size()];  
		for(k = 0; k < legalBuilding.size(); k++)
		{
        if(legalBuilding.get(k) != null)    
		  {
			  GameJLayeredPane temp = (GameJLayeredPane)legalBuilding.get(k);
	        GameImageIcon icon = (GameImageIcon)icons.get(k);
	        buildingButtons[k] = new GameJButton(icon);
			  buildingButtons[k].setBounds(00, 00, icon.getIconWidth(), icon.getIconHeight());
			  temp.add(buildingButtons[k], new Integer(4));
	        temp.repaint();
			  buildingButtons[k].addActionListener(this);
		  }
		}
		for(k = 0; k < cityVector.size(); k++)
	   {
	     if(cityVector.get(k) != null)  
		    cityButtons[k].removeActionListener(this);
	   }  
	   GameJLabel lb_1 = new GameJLabel("Select position on map");  
      lb_1.setBounds(30, 10, 150, 15);

	   GameJLabel lb_2 = new GameJLabel("to add Sydney Opera House!");  
      lb_2.setBounds(45, 25, 110, 15);

		cancel = new GameJButton("cancel");
		cancel.setBounds(40, 80, 100, 20);    
		cancel.addActionListener(this);

      cityHolder.removeAll();
	   cityHolder.add(lb_1, new Integer(0));  
		cityHolder.add(lb_2, new Integer(0));  
		cityHolder.add(cancel, new Integer(0));  
		cityHolder.repaint();
      cityPanel.setPreferredSize(new Dimension(190, 110));
      cityScrollPane.revalidate();
	   
	   operaActive = true;
	 }
    if(operaActive)
    {
      Player[] tempArray = name.getPlayerUI().getPlayers();
      for(int k = 0; k < legalBuilding.size(); k++)  
	   {
        if(buildingButtons[k] != null && e.getSource() == buildingButtons[k])    
        {
          int x = name.getCityXpos(cityNumber);
          int y = name.getCityYpos(cityNumber);

          switch(k)
			 {
			   case 0:
	           name.addBuildingtoCity(cityNumber, 30, 0);      
              for(int i = 0; i < 2; i++)//number of players         
                tempArray[i].playerUI.lb.addBuilding(x-1, y-1, 30, new GameJLabel(name.getCityBuildingLabel(cityNumber, 0).getIcon()), false, name, cityNumber);
				  break;
			   case 1:
	           name.addBuildingtoCity(cityNumber, 30, 1);      
              for(int i = 0; i < 2; i++)//number of players         
                tempArray[i].playerUI.lb.addBuilding(x, y-1, 30, new GameJLabel(name.getCityBuildingLabel(cityNumber, 1).getIcon()), false, name, cityNumber);
				  break;
			   case 2:
	           name.addBuildingtoCity(cityNumber, 30, 2);      
              for(int i = 0; i < 2; i++)//number of players         
                tempArray[i].playerUI.lb.addBuilding(x+1, y-1, 30, new GameJLabel(name.getCityBuildingLabel(cityNumber, 2).getIcon()), false, name, cityNumber);
				  break;
			   case 3:
	           name.addBuildingtoCity(cityNumber, 30, 3);      
              for(int i = 0; i < 2; i++)//number of players         
                tempArray[i].playerUI.lb.addBuilding(x-1, y+1, 30, new GameJLabel(name.getCityBuildingLabel(cityNumber, 3).getIcon()), false, name, cityNumber);
				  break;
			   case 4:
	           name.addBuildingtoCity(cityNumber, 30, 4);      
              for(int i = 0; i < 2; i++)//number of players         
                tempArray[i].playerUI.lb.addBuilding(x, y+1, 30, new GameJLabel(name.getCityBuildingLabel(cityNumber, 4).getIcon()), false, name, cityNumber);
				  break;
			   case 5:
	           name.addBuildingtoCity(cityNumber, 30, 5);      
              for(int i = 0; i < 2; i++)//number of players         
                tempArray[i].playerUI.lb.addBuilding(x+1, y+1, 30, new GameJLabel(name.getCityBuildingLabel(cityNumber, 5).getIcon()), false, name, cityNumber);
				  break;
			   case 6:
	           name.addBuildingtoCity(cityNumber, 30, 6);      
              for(int i = 0; i < 2; i++)//number of players         
                tempArray[i].playerUI.lb.addBuilding(x-1, y, 30, new GameJLabel(name.getCityBuildingLabel(cityNumber, 6).getIcon()), false, name, cityNumber);
				  break;
			   case 7:
	           name.addBuildingtoCity(cityNumber, 30, 7);      
              for(int i = 0; i < 2; i++)//number of players         
                tempArray[i].playerUI.lb.addBuilding(x+1, y, 30, new GameJLabel(name.getCityBuildingLabel(cityNumber, 7).getIcon()), false, name, cityNumber);
				  break;
			 }
	   
          operaActive = false;
	       opera.removeActionListener(this);
          GameJLayeredPane temp;
		    for(k = 0; k < legalBuilding.size(); k++)
			 {
			   if(legalBuilding.get(k) != null)
				{
				  temp = (GameJLayeredPane)legalBuilding.get(k);
			     temp.remove(temp.getIndexOf(buildingButtons[k]));
              temp.repaint();
				}    
			 }
		    if(!usedEngineering)  
			   finishCityTurn();
			 else if(builtEngineeringOne)
			   finishCityTurn();
			 else
			 {
			   engineeringProd = -25;
		      builtEngineeringOne = true;
			   displayCityOptions(cityNumber);
			 }
		  }
	   }
    }

    if(e.getSource() == unitednations)
	 {
		legalBuilding = new Vector(0, 1); 
      Vector icons = new Vector(0, 1);
      int x = name.getCityXpos(cityNumber);
      int y = name.getCityYpos(cityNumber);
		int k, i;
		for(k = x-1; k <= x+1; k++)
		{  
		  i = y-1;
		  if(!lb.getTerrain(k, i).equals("Water") && !lb.getHasBuild(k, i))
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
		  if(!lb.getTerrain(k, i).equals("Water") && !lb.getHasBuild(k, i))
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
		if(!lb.getTerrain(x-1, y).equals("Water") && !lb.getHasBuild(x-1, y))
		{
		  legalBuilding.add(name.playerUI.lb.getHolder(x-1, y));
        icons.add(name.playerUI.lb.getIcon(x-1, y));  
      }
		else
      {
		  legalBuilding.add(null);		 
        icons.add(null);    
		}   
		if(!lb.getTerrain(x+1, y).equals("Water") && !lb.getHasBuild(x+1, y))
		{
		  legalBuilding.add(name.playerUI.lb.getHolder(x+1, y));
        icons.add(name.playerUI.lb.getIcon(x+1, y));  
      }
		else
      {
		  legalBuilding.add(null);		 
        icons.add(null);    
		}   
	   buildingButtons = new GameJButton[legalBuilding.size()];  
		for(k = 0; k < legalBuilding.size(); k++)
		{
        if(legalBuilding.get(k) != null)    
		  {
			  GameJLayeredPane temp = (GameJLayeredPane)legalBuilding.get(k);
	        GameImageIcon icon = (GameImageIcon)icons.get(k);
	        buildingButtons[k] = new GameJButton(icon);
			  buildingButtons[k].setBounds(00, 00, icon.getIconWidth(), icon.getIconHeight());
			  temp.add(buildingButtons[k], new Integer(4));
	        temp.repaint();
			  buildingButtons[k].addActionListener(this);
		  }
		}
		for(k = 0; k < cityVector.size(); k++)
	   {
	     if(cityVector.get(k) != null)  
		    cityButtons[k].removeActionListener(this);
	   }  
	   GameJLabel lb_1 = new GameJLabel("Select position on map");  
      lb_1.setBounds(30, 10, 150, 15);

	   GameJLabel lb_2 = new GameJLabel("to add United Nations!");  
      lb_2.setBounds(45, 25, 110, 15);

		cancel = new GameJButton("cancel");
		cancel.setBounds(40, 80, 100, 20);    
		cancel.addActionListener(this);

      cityHolder.removeAll();
	   cityHolder.add(lb_1, new Integer(0));  
		cityHolder.add(lb_2, new Integer(0));  
		cityHolder.add(cancel, new Integer(0));  
		cityHolder.repaint();
      cityPanel.setPreferredSize(new Dimension(190, 110));
      cityScrollPane.revalidate();
	   
	   unitednationsActive = true;
	 }
    if(unitednationsActive)
    {
      Player[] tempArray = name.getPlayerUI().getPlayers();
      for(int k = 0; k < legalBuilding.size(); k++)  
	   {
        if(buildingButtons[k] != null && e.getSource() == buildingButtons[k])    
        {
          int x = name.getCityXpos(cityNumber);
          int y = name.getCityYpos(cityNumber);

          switch(k)
			 {
			   case 0:
	           name.addBuildingtoCity(cityNumber, 31, 0);      
              for(int i = 0; i < 2; i++)//number of players         
					 tempArray[i].playerUI.lb.addBuilding(x-1, y-1, 31, new GameJLabel(name.getCityBuildingLabel(cityNumber, 0).getIcon()), false, name, cityNumber);
				  break;
			   case 1:
	           name.addBuildingtoCity(cityNumber, 31, 1);      
              for(int i = 0; i < 2; i++)//number of players         
                tempArray[i].playerUI.lb.addBuilding(x, y-1, 31, new GameJLabel(name.getCityBuildingLabel(cityNumber, 1).getIcon()), false, name, cityNumber);
				  break;
			   case 2:
	           name.addBuildingtoCity(cityNumber, 31, 2);      
              for(int i = 0; i < 2; i++)//number of players         
                tempArray[i].playerUI.lb.addBuilding(x+1, y-1, 31, new GameJLabel(name.getCityBuildingLabel(cityNumber, 2).getIcon()), false, name, cityNumber);
				  break;
			   case 3:
	           name.addBuildingtoCity(cityNumber, 31, 3);      
              for(int i = 0; i < 2; i++)//number of players         
                tempArray[i].playerUI.lb.addBuilding(x-1, y+1, 31, new GameJLabel(name.getCityBuildingLabel(cityNumber, 3).getIcon()), false, name, cityNumber);
				  break;
			   case 4:
	           name.addBuildingtoCity(cityNumber, 31, 4);      
              for(int i = 0; i < 2; i++)//number of players         
                tempArray[i].playerUI.lb.addBuilding(x, y+1, 31, new GameJLabel(name.getCityBuildingLabel(cityNumber, 4).getIcon()), false, name, cityNumber);
				  break;
			   case 5:
	           name.addBuildingtoCity(cityNumber, 31, 5);      
              for(int i = 0; i < 2; i++)//number of players         
                tempArray[i].playerUI.lb.addBuilding(x+1, y+1, 31, new GameJLabel(name.getCityBuildingLabel(cityNumber, 5).getIcon()), false, name, cityNumber);
				  break;
			   case 6:
	           name.addBuildingtoCity(cityNumber, 31, 6);      
              for(int i = 0; i < 2; i++)//number of players         
                tempArray[i].playerUI.lb.addBuilding(x-1, y, 31, new GameJLabel(name.getCityBuildingLabel(cityNumber, 6).getIcon()), false, name, cityNumber);
				  break;
			   case 7:
	           name.addBuildingtoCity(cityNumber, 31, 7);      
              for(int i = 0; i < 2; i++)//number of players         
                tempArray[i].playerUI.lb.addBuilding(x+1, y, 31, new GameJLabel(name.getCityBuildingLabel(cityNumber, 7).getIcon()), false, name, cityNumber);
				  break;
			 }
	   
          unitednationsActive = false;
	       unitednations.removeActionListener(this);
          GameJLayeredPane temp;
		    for(k = 0; k < legalBuilding.size(); k++)
			 {
			   if(legalBuilding.get(k) != null)
				{
				  temp = (GameJLayeredPane)legalBuilding.get(k);
			     temp.remove(temp.getIndexOf(buildingButtons[k]));
              temp.repaint();
				}    
			 }
		    if(!usedEngineering)  
			   finishCityTurn();
			 else if(builtEngineeringOne)
			   finishCityTurn();
			 else
			 {
			   engineeringProd = -20;
		      builtEngineeringOne = true;
			   displayCityOptions(cityNumber);
			 }
		  }
	   }
    }

    if(e.getSource() == egypt)
	 {
      egyptFree = true;
      egypt.removeActionListener(this);
	   cityHolder.remove(cityHolder.getIndexOf(egypt));  
		displayEgyptOptions();
    }
	 
    if(e.getSource() == animal)
	 {
	   specialProd += 3;  
		name.spendResource("Wheat");
		animal.removeActionListener(this);
	   cityHolder.remove(cityHolder.getIndexOf(animal));  
      usedAnimal = true;
      displayCityOptions(cityNumber);
	 }

    if(e.getSource() == currency)
	 {
	   name.addCulture(3);  
		name.spendResource("Incense");
		currency.removeActionListener(this);
	   cityHolder.remove(cityHolder.getIndexOf(currency));  
      usedCurrency = true;
		if(cityActions > 0 && cityNumber >= 0)
		  displayCityOptions(cityNumber);
	   else
		  redrawInitial();
	 }

    if(e.getSource() == masonry)
	 {
	   specialProd += -7;
		name.cityAddWalls(cityNumber, lb);  
		masonry.removeActionListener(this);
	   cityHolder.remove(cityHolder.getIndexOf(masonry));  
      usedMasonry = true;
      displayCityOptions(cityNumber);
	 }

    if(e.getSource() == philosophy)
	 {
	   philosophy.removeActionListener(this);
	   cityHolder.remove(cityHolder.getIndexOf(philosophy));  
      usedPhilosophy = true;
		if(cityActions > 0 && cityNumber >= 0)
		  displayCityOptions(cityNumber);
	   else
		  redrawInitial();
	 
	 }

    if(e.getSource() == pottery)
	 {
	 	int marketiron = name.getmarketIron();   
	 	int marketincense = name.getmarketIncense();   
	 	int marketsilk = name.getmarketSilk();   
	 	int marketwheat = name.getmarketWheat();   

      int total = 0;
		
		Vector v = new Vector(0, 1);
		Vector s = new Vector(0, 1);

		for(int k = 0; k < marketiron; k++)
		{
        v.add(getImage("data/Iron_market.png"));
        s.add("Iron market");
		  total++;
		}
		for(int k = 0; k < marketincense; k++)
		{
        v.add(getImage("data/Incense_market.png"));
        s.add("Incense market");
		  total++;
		}
		for(int k = 0; k < marketsilk; k++)
		{
        v.add(getImage("data/Silk_market.png"));
        s.add("Silk market");
		  total++;
		}
		for(int k = 0; k < marketwheat; k++)
		{
        v.add(getImage("data/Wheat_market.png"));
        s.add("Wheat market");
		  total++;
		}
    
	   Vector nameHuts = name.getHutVector();
      for(int k = 0; k < nameHuts.size(); k++)
		{
		  String com = (String)nameHuts.get(k);
		  if(com.equals("Iron"))
        {
		    v.add(getImage("data/Iron_hut.png"));
	       s.add("Iron hut");
			 total++;
		  }
		  else if(com.equals("Incense"))
        {
		    v.add(getImage("data/Incense_hut.png"));
	       s.add("Incense hut");
			 total++;
		  }
		  else if(com.equals("Silk"))
        {
		    v.add(getImage("data/Silk_hut.png"));
	       s.add("Silk hut");
			 total++;
		  }
		  else if(com.equals("Wheat"))
        {
		    v.add(getImage("data/Wheat_hut.png"));
	       s.add("Wheat hut");
			 total++;
		  }
		  else if(com.equals("Spy"))
        {
		    v.add(getImage("data/Spy_hut.png"));
	       s.add("Spy hut");
			 total++;
		  }
		}		

	   Vector nameVillages = name.getVillageVector();
      for(int k = 0; k < nameVillages.size(); k++)
		{
		  String com = (String)nameVillages.get(k);
		  if(com.equals("Iron"))
        {
		    v.add(getImage("data/Iron_village.png"));
	       s.add("Iron village");
			 total++;
		  }
		  else if(com.equals("Spy"))
        {
		    v.add(getImage("data/Spy_village.png"));
	       s.add("Spy village");
			 total++;
		  }
		  else if(com.equals("uranium"))
		  {
          v.add(getImage("data/Uranium.png"));
          s.add("Uranium village");
		    total++;
		  }
		}		

      Object[] options_resource = v.toArray();
      JOptionPane start = new JOptionPane();

      String res1 = "", res2 = "";
		int resource_1 = -1, resource_2 = -1;
      
		resource_1 = start.showOptionDialog(name.playerUI.c, "Choose resource (1 of 2)", name.getNation(),
                       JOptionPane.CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
                       getImage("data/Any.png"), options_resource, null);

		if(resource_1 >= 0)
		{
//System.out.println((String)s.get(resource_1));
		  res1 = (String)s.get(resource_1);
		  v.remove(resource_1);
		  s.remove(resource_1);
				
        Object[] options_resource2 = v.toArray();
        JOptionPane start2 = new JOptionPane();
      
		  resource_2 = start2.showOptionDialog(name.playerUI.c, "Choose resource (2 of 2)", name.getNation(),
                       JOptionPane.CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
                       getImage("data/Any.png"), options_resource2, null);
      }
				
	   if(resource_2 >= 0)
		{
//System.out.println((String)s.get(resource_2));
		  res2 = (String)s.get(resource_2);
		  name.spendResource(res1, " ");
		  name.spendResource(res2, " ");
        name.addTechGold(1, 8);   
	     pottery.removeActionListener(this);
	     cityHolder.remove(cityHolder.getIndexOf(pottery));  
		  usedPottery = true;
      }
      
		if(cityActions > 0 && cityNumber >= 0)
		  displayCityOptions(cityNumber);
	   else
		  redrawInitial();
	 }

    if(e.getSource() == writing)
	 {
		name.spendSpy();
	   writing.removeActionListener(this);
	   cityHolder.remove(cityHolder.getIndexOf(writing));  
      usedWriting = true;
		if(cityActions > 0)
//		  displayCityOptions(cityNumber);
//	   else
		  redrawInitial();
	 
	 }

    if(e.getSource() == chivalry)
	 {
	   name.addCulture(5);  
		name.spendResource("Incense");
	   chivalry.removeActionListener(this);
	   cityHolder.remove(cityHolder.getIndexOf(chivalry));  
      usedChivalry = true;
		if(cityActions > 0 && cityNumber >= 0)
		  displayCityOptions(cityNumber);
	   else
		  redrawInitial();
	 }

    if(e.getSource() == construction)
	 {
	   specialProd += 5;  
		name.spendResource("Wheat");
	   construction.removeActionListener(this);
	   cityHolder.remove(cityHolder.getIndexOf(construction));  
      usedConstruction = true;
      displayCityOptions(cityNumber);
	 }

    if(e.getSource() == democracy)
	 {
      name.addTechGold(1, 13);   
		name.spendTrade(6);
	   democracy.removeActionListener(this);
	   cityHolder.remove(cityHolder.getIndexOf(democracy));  
      usedDemocracy = true;
		if(cityActions > 0 && cityNumber >= 0)
		  displayCityOptions(cityNumber);
	   else
		  redrawInitial();
	 }

    if(e.getSource() == engineering)
	 {
	   engineering.removeActionListener(this);
	   cityHolder.remove(cityHolder.getIndexOf(engineering));  
      usedEngineering = true;
      displayCityOptions(cityNumber);
	 }
    
    if(e.getSource() == monarchy)
	 {
	   done.removeActionListener(this);

		name.spendResource("Silk");
	   usedMonarchy = true;
	   monarchy.removeActionListener(this);
	   cityHolder.remove(cityHolder.getIndexOf(monarchy));  
      monarchyActive = true;	 
	 
		legalBuilding = new Vector(0, 1); 
      Vector icons = new Vector(0, 1);

	   for(int k = 0; k < 8; k++)
		{
		  for(int i = 0; i < 16; i++)
		  {
		    if(name.playerUI.lb.getLabel(k, i) != null)  
			 {
//System.out.println(name.playerUI.lb.getLabel(k, i).getName()+" "+k+ " "+i);
				 if(name.playerUI.lb.getLabel(k, i).getName().equals("wonder") && 
				 ((name.playerUI.lb.getBuildingNumber(name.playerUI.lb.getHolder(k, i)) == 20)
				  || (name.playerUI.lb.getBuildingNumber(name.playerUI.lb.getHolder(k, i)) == 21)
				  || (name.playerUI.lb.getBuildingNumber(name.playerUI.lb.getHolder(k, i)) == 22)
				  || (name.playerUI.lb.getBuildingNumber(name.playerUI.lb.getHolder(k, i)) == 23)))
	          {
				   legalBuilding.add(name.playerUI.lb.getHolder(k, i));
				   icons.add(name.playerUI.lb.getIcon(k, i));
				 }
          }
		  }
		}
      
	   buildingButtons = new GameJButton[legalBuilding.size() + 2];//number of players  

      Player[] tempArray = name.getPlayerUI().getPlayers();

		for(int k = 0; k < 2; k++)//number of players
		{
		  if(tempArray[k] != name)
		  {
			  GameJButton player = new GameJButton(tempArray[k].getNation());
			  player.setBounds(40, 55+(25*k), 100, 20);    
	        buildingButtons[k] = player;
			  buildingButtons[k].addActionListener(this);
        }
		  else
		    buildingButtons[k] = null;
		}

		for(int k = 2; k < legalBuilding.size()+2; k++)//k = number of players
		{
		   GameJLayeredPane temp = (GameJLayeredPane)legalBuilding.get(k-2);
		   GameImageIcon icon = (GameImageIcon)icons.get(k-2);
         buildingButtons[k] = new GameJButton(icon);//k + number of players-1
			buildingButtons[k].setBounds(00, 00, icon.getIconWidth(), icon.getIconHeight());//k + number of players-1
			temp.add(buildingButtons[k], new Integer(4));//k + number of players-1
		   temp.repaint();
//			legalBuilding.add(temp);
			buildingButtons[k].addActionListener(this);//k + number of players-1
	   }
		
		cityHolder.removeAll();

	   if(legalBuilding.size() > 0)
		{
			GameJLabel lb_1 = new GameJLabel("Select wonder on map");  
	      lb_1.setBounds(10, 10, 150, 15);
	
		   GameJLabel lb_2 = new GameJLabel("to obsolete! Or choose player");  
	      lb_2.setBounds(15, 25, 180, 15);
	
		   GameJLabel lb_3 = new GameJLabel("below to kill a random unit!");  
	      lb_3.setBounds(25, 40, 160, 15);

		   cityHolder.add(lb_1, new Integer(0));  
			cityHolder.add(lb_2, new Integer(0));  
			cityHolder.add(lb_3, new Integer(0));  
      }
      else
		{
			GameJLabel lb_1 = new GameJLabel("");  
	      lb_1.setBounds(10, 10, 150, 15);

		   GameJLabel lb_2 = new GameJLabel("Choose player below to");  
	      lb_2.setBounds(25, 25, 160, 15);
	
		   GameJLabel lb_3 = new GameJLabel("kill a random unit!");  
	      lb_3.setBounds(35, 40, 160, 15);

		   cityHolder.add(lb_1, new Integer(0));  
			cityHolder.add(lb_2, new Integer(0));  
			cityHolder.add(lb_3, new Integer(0));  
		}    

		for(int k = 0; k < 2; k++)//number of players 
		{
		  if(tempArray[k] != name)
		    cityHolder.add(buildingButtons[k], new Integer(0));  
		}
		cityHolder.repaint();
      cityPanel.setPreferredSize(new Dimension(190, 110));
      cityScrollPane.revalidate();
	   
		for(int k = 0; k < cityVector.size(); k++)
		{
		  if(cityVector.get(k) != null)  
		    cityButtons[k].removeActionListener(this);
		}  
	 }

    if(monarchyActive)
    {
       Player[] tempArray = name.getPlayerUI().getPlayers(); 
       for(int k = 0; k < legalBuilding.size()+2; k++)//legalBuilding.size() + number of players
       {
		    if(buildingButtons[k] != null && e.getSource() == buildingButtons[k])
			 {
			   if(k < 2)//number of players
				{
				  tempArray[k].killUnit();//kill random unit
				}
			   else
				{

			      GameJLayeredPane temp;
				   for(int i = 0; i < legalBuilding.size(); i++)
					{
					  if(buildingButtons[i+2] != null)
					  {
						  temp = (GameJLayeredPane)legalBuilding.get(i);
					     temp.remove(temp.getIndexOf(buildingButtons[i+2]));//number of players
			           temp.repaint();
					  }    
				   }

              GameJLayeredPane pane = (GameJLayeredPane)legalBuilding.get(k-2);//number of players
				  Player p = name.playerUI.lb.getOwner(name.playerUI.lb.getBoardxpos(pane), name.playerUI.lb.getBoardypos(pane));
				  p.obsoleteWonder(name.playerUI.lb.getBuildingNumber(pane));
//				  CivGUI.players[k-legalBuilding.size()].removeCityBuilding(cityNumber, name.playerUI.lb.getBoardxpos(pane), name.playerUI.lb.getBoardypos(pane));
				}

            monarchyActive = false;
				if(cityActions > 0 && cityNumber >= 0)
				{
//					 for(k = 0; k < cityVector.size(); k++)
//					 {
//					   if(cityVector.get(k) != null)  
//						  cityButtons[k].addActionListener(this);
//					 }  
			     displayCityOptions(cityNumber);
			   }
			   else
				  redrawInitial();
			 }
		 }
    }
	 
    if(e.getSource() == print)
	 {
      name.addTechGold(1, 18);   
		name.spendCulture(5);
	   print.removeActionListener(this);
	   cityHolder.remove(cityHolder.getIndexOf(print));  
      usedPrint = true;
		if(cityActions > 0 && cityNumber >= 0)
		  displayCityOptions(cityNumber);
	   else
		  redrawInitial();
	 
	 }

    if(e.getSource() == banking)
	 {
      specialProd += 7;
	   banking.removeActionListener(this);
	   cityHolder.remove(cityHolder.getIndexOf(banking));  
      usedBanking = true;
      displayCityOptions(cityNumber);
	 }

    if(e.getSource() == gunpowder)
	 {
	    done.removeActionListener(this);
 
		 for(int k = 0; k < cityVector.size(); k++)
		 {
		   if(cityVector.get(k) != null)  
			  cityButtons[k].removeActionListener(this);
		 }  

		int marketiron = name.getmarketIron();   
	 	int marketincense = name.getmarketIncense();   
	 	int marketsilk = name.getmarketSilk();   
	 	int marketwheat = name.getmarketWheat();   

		Vector v = new Vector(0, 1);
		Vector s = new Vector(0, 1);

		for(int k = 0; k < marketiron; k++)
		{
        v.add(getImage("data/Iron_market.png"));
        s.add("Iron market");
		}
		for(int k = 0; k < marketincense; k++)
		{
        v.add(getImage("data/Incense_market.png"));
        s.add("Incense market");
		}
		for(int k = 0; k < marketsilk; k++)
		{
        v.add(getImage("data/Silk_market.png"));
        s.add("Silk market");
		}
		for(int k = 0; k < marketwheat; k++)
		{
        v.add(getImage("data/Wheat_market.png"));
        s.add("Wheat market");
		}
    
	   Vector nameHuts = name.getHutVector();
      for(int k = 0; k < nameHuts.size(); k++)
		{
		  String com = (String)nameHuts.get(k);
		  if(com.equals("Iron"))
        {
		    v.add(getImage("data/Iron_hut.png"));
	       s.add("Iron hut");
		  }
		  else if(com.equals("Incense"))
        {
		    v.add(getImage("data/Incense_hut.png"));
	       s.add("Incense hut");
		  }
		  else if(com.equals("Silk"))
        {
		    v.add(getImage("data/Silk_hut.png"));
	       s.add("Silk hut");
		  }
		  else if(com.equals("Wheat"))
        {
		    v.add(getImage("data/Wheat_hut.png"));
	       s.add("Wheat hut");
		  }
		  else if(com.equals("Spy"))
        {
		    v.add(getImage("data/Spy_hut.png"));
	       s.add("Spy hut");
		  }
		}		

	   Vector nameVillages = name.getVillageVector();
      for(int k = 0; k < nameVillages.size(); k++)
		{
		  String com = (String)nameVillages.get(k);
		  if(com.equals("Iron"))
        {
		    v.add(getImage("data/Iron_village.png"));
	       s.add("Iron village");
		  }
		  else if(com.equals("Spy"))
        {
		    v.add(getImage("data/Spy_village.png"));
	       s.add("Spy village");
		  }
		  else if(com.equals("uranium"))
		  {
          v.add(getImage("data/Uranium.png"));
          s.add("Uranium village");
		  }
		}		

      Object[] options_resource = v.toArray();
      JOptionPane start = new JOptionPane();

      String res1 = "", res2 = "";
		int resource_1 = -1, resource_2 = -1;
      
		resource_1 = start.showOptionDialog(name.playerUI.c, "Choose resource (1 of 2)", name.getNation(),
                       JOptionPane.CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
                       getImage("data/Any.png"), options_resource, null);

		if(resource_1 >= 0)
		{
//System.out.println((String)s.get(resource_1));
		  res1 = (String)s.get(resource_1);
		  v.remove(resource_1);
		  s.remove(resource_1);
				
        Object[] options_resource2 = v.toArray();
        JOptionPane start2 = new JOptionPane();
      
		  resource_2 = start2.showOptionDialog(name.playerUI.c, "Choose resource (2 of 2)", name.getNation(),
                       JOptionPane.CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
                       getImage("data/Any.png"), options_resource2, null);
      }
				
	   if(resource_2 >= 0)
		{
//System.out.println((String)s.get(resource_2));
		  res2 = (String)s.get(resource_2);
		  name.spendResource(res1, " ");
		  name.spendResource(res2, " ");
        usedGunpowder = true;
        gunpowderActive = true;
	     gunpowder.removeActionListener(this);
	     cityHolder.remove(cityHolder.getIndexOf(gunpowder));  

			legalBuilding = new Vector(0, 1); 
	      Vector icons = new Vector(0, 1);
	
		   for(int k = 0; k < 8; k++)
			{
			  for(int i = 0; i < 16; i++)
			  {
			    if(name.playerUI.lb.getLabel(k, i) != null)  
				 {
					 if((name.playerUI.lb.getLabel(k, i).getName().equals("wonder") && 
					 ((name.playerUI.lb.getBuildingNumber(name.playerUI.lb.getHolder(k, i)) == 20)
					  || (name.playerUI.lb.getBuildingNumber(name.playerUI.lb.getHolder(k, i)) == 21)
					  || (name.playerUI.lb.getBuildingNumber(name.playerUI.lb.getHolder(k, i)) == 22)
					  || (name.playerUI.lb.getBuildingNumber(name.playerUI.lb.getHolder(k, i)) == 23)
					  || (name.playerUI.lb.getBuildingNumber(name.playerUI.lb.getHolder(k, i)) == 24)
					  || (name.playerUI.lb.getBuildingNumber(name.playerUI.lb.getHolder(k, i)) == 25)
					  || (name.playerUI.lb.getBuildingNumber(name.playerUI.lb.getHolder(k, i)) == 26)
					  || (name.playerUI.lb.getBuildingNumber(name.playerUI.lb.getHolder(k, i)) == 27)))
					  || (name.playerUI.lb.getLabel(k, i).getName().equals(" ")))
		          {
					   legalBuilding.add(name.playerUI.lb.getHolder(k, i));
					   icons.add(name.playerUI.lb.getIcon(k, i));
					 }
	          }
			  }
			}
	      
		   buildingButtons = new GameJButton[legalBuilding.size()];
	
			for(int k = 0; k < legalBuilding.size(); k++)
			{
			   GameJLayeredPane temp = (GameJLayeredPane)legalBuilding.get(k);
			   GameImageIcon icon = (GameImageIcon)icons.get(k);
	         buildingButtons[k] = new GameJButton(icon);
				buildingButtons[k].setBounds(00, 00, icon.getIconWidth(), icon.getIconHeight());
				temp.add(buildingButtons[k], new Integer(4));
			   temp.repaint();
				buildingButtons[k].addActionListener(this);
		   }
			
			cityHolder.removeAll();
	
			GameJLabel lb_1 = new GameJLabel("Select wonder to obsolete");  
	      lb_1.setBounds(10, 10, 150, 15);
		
		   GameJLabel lb_2 = new GameJLabel("or building to destroy");  
	      lb_2.setBounds(15, 25, 180, 15);
		
		   GameJLabel lb_3 = new GameJLabel("on map!");  
	      lb_3.setBounds(25, 40, 160, 15);
	
		   cityHolder.add(lb_1, new Integer(0));  
			cityHolder.add(lb_2, new Integer(0));  
			cityHolder.add(lb_3, new Integer(0));  
	
			cityHolder.repaint();
	      cityPanel.setPreferredSize(new Dimension(190, 110));
	      cityScrollPane.revalidate();
      }
	 }

    if(gunpowderActive)
	 {
	   for(int k = 0; k < legalBuilding.size(); k++)
		{
		  if(buildingButtons[k] != null && e.getSource() == buildingButtons[k])
		  {
		    gunpowderActive = false;
			 
	       GameJLayeredPane temp;
			 for(int i = 0; i < legalBuilding.size(); i++)
			 {
				if(buildingButtons[i] != null)
				{
				  temp = (GameJLayeredPane)legalBuilding.get(i);
				  temp.remove(temp.getIndexOf(buildingButtons[i]));
			     temp.repaint();
				}    
			 }

          GameJLayeredPane pane = (GameJLayeredPane)legalBuilding.get(k);
			 Player p = name.playerUI.lb.getOwner(name.playerUI.lb.getBoardxpos(pane), name.playerUI.lb.getBoardypos(pane));
			 if(name.playerUI.lb.getLabel(name.playerUI.lb.getBoardxpos(pane), name.playerUI.lb.getBoardypos(pane)).getName().equals("wonder"))
			 {
			   p.obsoleteWonder(name.playerUI.lb.getBuildingNumber(pane));
          }
			 else
			 {
            p.removeCityBuilding(name.playerUI.lb.getCityNumber(name.playerUI.lb.getBoardxpos(pane), name.playerUI.lb.getBoardypos(pane)), name.playerUI.lb.getBoardxpos(pane), name.playerUI.lb.getBoardypos(pane), name.playerUI.lb.getBuildingNumber(pane));
			 }
			 
			 if(cityActions > 0 && cityNumber >= 0)
			   displayCityOptions(cityNumber);
		    else
			   redrawInitial();
		  }
		}
	 }
	     
	 if(e.getSource() == metalCast)
	 {
      name.addCulture(7);
		name.spendResource("Incense");
	   metalCast.removeActionListener(this);
	   cityHolder.remove(cityHolder.getIndexOf(metalCast));  
      usedMetalCast = true;
		if(cityActions > 0 && cityNumber >= 0)
		  displayCityOptions(cityNumber);
	   else
		  redrawInitial();
	 }

    if(e.getSource() == steam)
	 {
	   steam.removeActionListener(this);
	   cityHolder.remove(cityHolder.getIndexOf(steam));  
      
		units = new Vector(0, 1);
		Vector s = new Vector(0, 1);
		for(int k = 0; k < name.getSettlerVector().size(); k++)
		  s.add(name.getSettlerVector().get(k));
		Vector a = new Vector(0, 1);
		for(int k = 0; k < name.getArmyVector().size(); k++)
		  a.add(name.getArmyVector().get(k));
		
		if(s.size() > 0)
		{
		  for(int k = 0; k < s.size(); k++)
		  {
		    Settler sett = (Settler)s.get(k); 
			 for(int i = 1; i < s.size();)
			 {
            Settler settCom = (Settler)s.get(i); 
			   if(settCom.getX() == sett.getX() && settCom.getY() == sett.getY() && settCom != sett)
				{
				  s.remove(i);
				}
		      else
				  i++;  
			 }
          for(int i = 0; i < a.size();)
			 {
	  		   Army arm = (Army)a.get(i);
 		      if(arm.getX() == sett.getX() && arm.getY() == sett.getY())	 
			   {
				  a.remove(i);
				}
			   else
				  i++;
			 }
		  }
		  for(int k = 0; k < a.size(); k++)
		  {
		    Army sett = (Army)a.get(k); 
          for(int i = 1; i < a.size();)
			 {
	  		   Army arm = (Army)a.get(i);
 		      if(arm.getX() == sett.getX() && arm.getY() == sett.getY() && arm != sett)	 
			   {
				  a.remove(i);
				}
			   else
				  i++;
			 }
        }

        for(int k = 0; k < s.size(); k++)
		  { 
		    units.add(s.get(k));
		  }
        for(int k = 0; k < a.size(); k++)
		  {
		    units.add(a.get(k));
		  }
     }
     else
	  {
		  for(int k = 0; k < a.size(); k++)
		  {
		    Army sett = (Army)a.get(k); 
          for(int i = 1; i < a.size();)
			 {
	  		   Army arm = (Army)a.get(i);
 		      if(arm.getX() == sett.getX() && arm.getY() == sett.getY() && arm != sett)	 
			   {
				  a.remove(i);
				}
			   else
				  i++;
			 }
        }	  
        for(int k = 0; k < a.size(); k++)
		  {
		    units.add(a.get(k));
		  }
	  }

	   buildingButtons = new GameJButton[units.size()];  
		legalBuilding = new Vector(0, 1);
		for(int k = 0; k < units.size(); k++)
		{
           if(units.get(k).getClass().getName().equals("Settler"))
           {
			     Settler sett = (Settler)units.get(k);			  
				  GameJLayeredPane temp = name.playerUI.lb.getHolder(sett.getX(), sett.getY());
		        GameImageIcon icon = name.playerUI.lb.getIcon(sett.getX(), sett.getY());
		        buildingButtons[k] = new GameJButton(icon);
				  buildingButtons[k].setBounds(00, 00, icon.getIconWidth(), icon.getIconHeight());
				  temp.add(buildingButtons[k], new Integer(4));
		        temp.repaint();
				  legalBuilding.add(temp);
				  buildingButtons[k].addActionListener(this);
			  }
			  else
			  {  
              Army sett = (Army)units.get(k);
				  GameJLayeredPane temp = name.playerUI.lb.getHolder(sett.getX(), sett.getY());
		        GameImageIcon icon = name.playerUI.lb.getIcon(sett.getX(), sett.getY());
		        buildingButtons[k] = new GameJButton(icon);
				  buildingButtons[k].setBounds(00, 00, icon.getIconWidth(), icon.getIconHeight());
				  temp.add(buildingButtons[k], new Integer(4));
		        temp.repaint();
				  legalBuilding.add(temp);
				  buildingButtons[k].addActionListener(this);
		     }
		}

	   GameJLabel lb_1 = new GameJLabel("Select space on map");  
      lb_1.setBounds(20, 10, 150, 15);

	   GameJLabel lb_2 = new GameJLabel("containing units to move!");  
      lb_2.setBounds(35, 25, 160, 15);

//		cancel = new GameJButton("cancel");
//		cancel.setBounds(40, 80, 100, 20);    
//		cancel.addActionListener(this);

      cityHolder.removeAll();
	   cityHolder.add(lb_1, new Integer(0));  
		cityHolder.add(lb_2, new Integer(0));  
//		cityHolder.add(cancel, new Integer(0));  
		cityHolder.repaint();
      cityPanel.setPreferredSize(new Dimension(190, 110));
      cityScrollPane.revalidate();
	   
	   steamActive = true;
	 }

    if(steamActive)
	 {
	   for(int k = 0; k < units.size(); k++)
		{
		  if(e.getSource() == buildingButtons[k])
		  {
          steamActive = false;
		    int x=0, y=0;  
			 steamMoveActive = true;
		    if(units.get(k).getClass().getName().equals("Settler"))
			 {
			   Settler sett = (Settler)units.get(k);  
				x = sett.getX();
			   y = sett.getY();
			 }
          else
			 {
			   Army arm = (Army)units.get(k);  
				x = arm.getX();
			   y = arm.getY();
			 }

	      GameJLayeredPane temp;
		   for(k = 0; k < legalBuilding.size(); k++)
			{
			  if(units.get(k) != null)
			  {
				  temp = (GameJLayeredPane)legalBuilding.get(k);
			     temp.remove(temp.getIndexOf(buildingButtons[k]));
	           temp.repaint();
			  }    
		   }
		    units = new Vector(0, 1);
			 Vector s = new Vector(0, 1);
			 for(int i = 0; i < name.getSettlerVector().size(); i++)
			   s.add(name.getSettlerVector().get(i));
//System.out.println("S SIZE "+s.size());			
			 Vector a = new Vector(0, 1);
			 for(int i = 0; i < name.getArmyVector().size(); i++)
			   a.add(name.getArmyVector().get(i));
//System.out.println("A SIZE "+a.size());			
			 for(int i = 0; i < s.size(); i++)
			 {
			   Settler sett = (Settler)s.get(i);
//System.out.println("Settler "+sett.getX()+sett.getY());			
				if(sett.getX() == x && sett.getY() == y)
				  units.add(sett);
			 }
			 for(int i = 0; i < a.size(); i++)
			 {
			   Army arm = (Army)a.get(i);
//System.out.println("Army "+arm.getX()+arm.getY());			
				if(arm.getX() == x && arm.getY() == y)
				  units.add(arm);
			 }
			
//System.out.println("UNIT SIZE "+units.size());			
			legalBuilding = new Vector(0, 1); 
	      Vector icons = new Vector(0, 1);
	
	      for(k = 0; k < 8; k++)
			{
			  for(int i = 0; i < 16; i++)
			  {
			    if(name.playerUI.lb.getTerrain(k, i) != null)  
				 {
					 if(name.playerUI.lb.getTerrain(k, i).equals("Water"))
		          {
					   legalBuilding.add(name.playerUI.lb.getHolder(k, i));
					   icons.add(name.playerUI.lb.getIcon(k, i));
					 }
             }
			  }
			}
		   
			buildingButtons = new GameJButton[legalBuilding.size()];  
			for(k = 0; k < legalBuilding.size(); k++)
			{
				  temp = (GameJLayeredPane)legalBuilding.get(k);
		        GameImageIcon icon = (GameImageIcon)icons.get(k);
		        buildingButtons[k] = new GameJButton(icon);
				  buildingButtons[k].setBounds(00, 00, icon.getIconWidth(), icon.getIconHeight());
				  temp.add(buildingButtons[k], new Integer(4));
		        temp.repaint();
				  buildingButtons[k].addActionListener(this);
			}
	
		   GameJLabel lb_1 = new GameJLabel("Select water space on");  
	      lb_1.setBounds(20, 10, 150, 15);
	
		   GameJLabel lb_2 = new GameJLabel("map to move units!");  
	      lb_2.setBounds(35, 25, 160, 15);
	
//			cancel = new GameJButton("cancel");
//			cancel.setBounds(40, 80, 100, 20);    
//			cancel.addActionListener(this);
	
	      cityHolder.removeAll();
		   cityHolder.add(lb_1, new Integer(0));  
			cityHolder.add(lb_2, new Integer(0));  
//			cityHolder.add(cancel, new Integer(0));  
			cityHolder.repaint();
	      cityPanel.setPreferredSize(new Dimension(190, 110));
	      cityScrollPane.revalidate();
		  }
		}
	 }
	   
	 if(steamMoveActive)
	 {
		for(int k = 0; k < legalBuilding.size(); k++)
      {
         if(e.getSource() == buildingButtons[k])         
			{
				steamMoveActive = false;

		      GameJLayeredPane space = (GameJLayeredPane)legalBuilding.get(k);

			   for(int j = 0; j < units.size(); j++)
				  moveUnit(units.get(j), space);

				name.spendResource("Silk");
		      usedSteam = true;
			   
				for(int i = 0; i < legalBuilding.size(); i++)
				{
				  if(legalBuilding.get(i) != null)
				  {
					 GameJLayeredPane temp = (GameJLayeredPane)legalBuilding.get(i);
				    temp.remove(temp.getIndexOf(buildingButtons[i]));
	             temp.repaint();
				  }    
				}
	
				if(cityActions > 0 && cityNumber >= 0)
				  displayCityOptions(cityNumber);
			   else
				  redrawInitial();
         }
      }
	 }
	 
	 if(e.getSource() == atomic)
	 {
		name.spendUranium();
	   atomic.removeActionListener(this);
	   cityHolder.remove(cityHolder.getIndexOf(atomic));  
      usedAtomic = true;
		if(cityActions > 0 && cityNumber >= 0)
		  displayCityOptions(cityNumber);
	   else
		  redrawInitial();
	 
	 }

  }//end actionPermformed


class SpinnerListener implements ChangeListener
{
  public void stateChanged(ChangeEvent e)
  {
		  JSpinner spinner = (JSpinner)e.getSource();
        int newtrade;
        if(name.getNation().equals("America"))
          newtrade = name.getTrade() - ((Integer)spinner.getValue()*2);  
        else
          newtrade = name.getTrade() - ((Integer)spinner.getValue()*3);  
        afterConvert.setText(Integer.toString(newtrade));
		  afterConvert.repaint();
  }
}

  private void moveUnit(Object obj, GameJLayeredPane pane)
  {
     Player[] tempArray = name.getPlayerUI().getPlayers(); 
	  if(obj.getClass().getName().equals("Settler"))
	  {
		  Settler s = (Settler)obj;
		  int x = name.playerUI.lb.getBoardxpos(pane);
		  int y = name.playerUI.lb.getBoardypos(pane);
		  
		  s.setBounds(name.playerUI.lb.getSettlerCount(x, y) + name.playerUI.lb.getArmyCount(x, y));
	     for(int k = 0; k < 2; k++)//number of players
	     {
	  	    if(tempArray[k] == name)
			 {
			   tempArray[k].playerUI.lb.getHolder(s.getX(), s.getY()).add(s.getLabel(), new Integer(5));    
			   tempArray[k].playerUI.lb.getHolder(x, y).add(s.getLabel(), new Integer(5));  
			   tempArray[k].playerUI.lb.subSettlerCount(s.getX(), s.getY()); 
				if(((tempArray[k].playerUI.lb.getSettlerCount(s.getX(), s.getY()) + tempArray[k].playerUI.lb.getArmyCount(s.getX(), s.getY())) == 0) && !tempArray[k].playerUI.lb.getHasBuild(s.getX(), s.getY()))      
				   tempArray[k].playerUI.lb.setOwner(s.getX(), s.getY(), null);  
			   tempArray[k].playerUI.lb.addSettlerCount(x, y);
		    }
			 else
			 {
             GameJLabel lbl = new GameJLabel();

				 for(int i = 0; !s.getLabelName().equals(lbl.getName());)
             {        
				   lbl = (GameJLabel)tempArray[k].unitSettlerLabels.get(i);				   
					if(s.getLabelName().equals(lbl.getName()))
					{
//System.out.println("Settler COMPARED NAME "+lbl.getName() +" "+s.getLabelName());  
			         tempArray[k].playerUI.lb.getHolder(s.getX(), s.getY()).remove(tempArray[k].playerUI.lb.getHolder(s.getX(), s.getY()).getIndexOf((GameJLabel)tempArray[k].unitSettlerLabels.remove(i)));
                  tempArray[k].playerUI.lb.getHolder(s.getX(), s.getY()).repaint();
               }
					else
					  i++;  
             }
			      
				 
				 GameJLabel l = new GameJLabel(s.getIcon());
				 l.setBounds(s.getCurrentbounds());
				 l.setName(s.getLabelName());
			    tempArray[k].playerUI.lb.getHolder(x, y).add(l, new Integer(5));  
			    tempArray[k].playerUI.lb.addSettlerCount(x, y);
	          tempArray[k].unitSettlerLabels.add(l);
			    tempArray[k].playerUI.lb.subSettlerCount(s.getX(), s.getY()); 
				 if(((tempArray[k].playerUI.lb.getSettlerCount(s.getX(), s.getY()) + tempArray[k].playerUI.lb.getArmyCount(s.getX(), s.getY())) == 0) && !tempArray[k].playerUI.lb.getHasBuild(s.getX(), s.getY()))      
				   tempArray[k].playerUI.lb.setOwner(s.getX(), s.getY(), null);  
	       }
	     }    
        s.setPosition(x, y);
        s.combatMoves();
        pane.repaint();
	  }  
	  else
	  {
		  Army s = (Army)obj;
		  int x = name.playerUI.lb.getBoardxpos(pane);
		  int y = name.playerUI.lb.getBoardypos(pane);
		  
		  s.setBounds(name.playerUI.lb.getSettlerCount(x, y), name.playerUI.lb.getArmyCount(x, y));
	     for(int k = 0; k < 2; k++)//number of players
	     {
	  	    if(tempArray[k] == name)
			 {
			   tempArray[k].playerUI.lb.getHolder(s.getX(), s.getY()).add(s.getLabel(), new Integer(5));    
			   tempArray[k].playerUI.lb.getHolder(x, y).add(s.getLabel(), new Integer(5));  
			   tempArray[k].playerUI.lb.subArmyCount(s.getX(), s.getY()); 
				if(((tempArray[k].playerUI.lb.getSettlerCount(s.getX(), s.getY()) + tempArray[k].playerUI.lb.getArmyCount(s.getX(), s.getY())) == 0) && !tempArray[k].playerUI.lb.getHasBuild(s.getX(), s.getY()))      
				   tempArray[k].playerUI.lb.setOwner(s.getX(), s.getY(), null);  
			   tempArray[k].playerUI.lb.addArmyCount(x, y);
		    }
			 else
			 {
             GameJLabel lbl = new GameJLabel();

				 for(int i = 0; !s.getLabelName().equals(lbl.getName());)
             {        
				   lbl = (GameJLabel)tempArray[k].unitArmyLabels.get(i);				   
					if(s.getLabelName().equals(lbl.getName()))
					{
//System.out.println("ARMY COMPARED NAME "+lbl.getName() +" "+s.getLabelName());  
					  tempArray[k].playerUI.lb.getHolder(s.getX(), s.getY()).remove(tempArray[k].playerUI.lb.getHolder(s.getX(), s.getY()).getIndexOf((GameJLabel)tempArray[k].unitArmyLabels.remove(i)));
                 tempArray[k].playerUI.lb.getHolder(s.getX(), s.getY()).repaint();
					}
			      else 
					  i++;  
				 }

			    GameJLabel l = new GameJLabel(s.getIcon());
				 l.setBounds(s.getCurrentbounds());
				 l.setName(s.getLabelName());
			    tempArray[k].playerUI.lb.getHolder(x, y).add(l, new Integer(5));  
			    tempArray[k].playerUI.lb.addArmyCount(x, y);
	          tempArray[k].unitArmyLabels.add(l);
			    tempArray[k].playerUI.lb.subArmyCount(s.getX(), s.getY()); 
				 if(((tempArray[k].playerUI.lb.getSettlerCount(s.getX(), s.getY()) + tempArray[k].playerUI.lb.getArmyCount(s.getX(), s.getY())) == 0) && !tempArray[k].playerUI.lb.getHasBuild(s.getX(), s.getY()))      
				   tempArray[k].playerUI.lb.setOwner(s.getX(), s.getY(), null);  
	       }
	     }    
        s.setPosition(x, y);
        s.combatMoves();
        pane.repaint();
	  }
  }
  
  private void addPost(int k)
  {
          Player[] tempArray = name.getPlayerUI().getPlayers(); 
          int x = name.getCityXpos(cityNumber);
          int y = name.getCityYpos(cityNumber);

          switch(k)
			 {
			   case 0:
	           name.addBuildingtoCity(cityNumber, 6, 0);      
              for(int i = 0; i < 2; i++)//number of players         
				  {
				    if(name == tempArray[i])
					   tempArray[i].playerUI.lb.addBuilding(x-1, y-1, 6, name.getCityBuildingLabel(cityNumber, 0), false, name, cityNumber);
				    else
					   tempArray[i].playerUI.lb.addBuilding(x-1, y-1, 6, new GameJLabel(name.getCityBuildingLabel(cityNumber, 0).getIcon()), false, name, cityNumber);
				  }
				  break;
			   case 1:
	           name.addBuildingtoCity(cityNumber, 6, 1);      
              for(int i = 0; i < 2; i++)//number of players         
				  {
				    if(name == tempArray[i])
	                tempArray[i].playerUI.lb.addBuilding(x, y-1, 6, name.getCityBuildingLabel(cityNumber, 1), false, name, cityNumber);
				    else
	                tempArray[i].playerUI.lb.addBuilding(x, y-1, 6, new GameJLabel(name.getCityBuildingLabel(cityNumber, 1).getIcon()), false, name, cityNumber);
				  }
				  break;
			   case 2:
	           name.addBuildingtoCity(cityNumber, 6, 2);      
              for(int i = 0; i < 2; i++)//number of players         
				  {
				    if(name == tempArray[i])
	               tempArray[i].playerUI.lb.addBuilding(x+1, y-1, 6, name.getCityBuildingLabel(cityNumber, 2), false, name, cityNumber);
 				    else
	               tempArray[i].playerUI.lb.addBuilding(x+1, y-1, 6, new GameJLabel(name.getCityBuildingLabel(cityNumber, 2).getIcon()), false, name, cityNumber);
				  }
				  break;
			   case 3:
	           name.addBuildingtoCity(cityNumber, 6, 3);      
              for(int i = 0; i < 2; i++)//number of players         
				  {
				    if(name == tempArray[i])
	               tempArray[i].playerUI.lb.addBuilding(x-1, y+1, 6, name.getCityBuildingLabel(cityNumber, 3), false, name, cityNumber);
 				    else
	               tempArray[i].playerUI.lb.addBuilding(x-1, y+1, 6, new GameJLabel(name.getCityBuildingLabel(cityNumber, 3).getIcon()), false, name, cityNumber);
				  }
				  break;
			   case 4:
	           name.addBuildingtoCity(cityNumber, 6, 4);      
              for(int i = 0; i < 2; i++)//number of players         
				  {
				    if(name == tempArray[i])
	                tempArray[i].playerUI.lb.addBuilding(x, y+1, 6, name.getCityBuildingLabel(cityNumber, 4), false, name, cityNumber);
				    else
	                tempArray[i].playerUI.lb.addBuilding(x, y+1, 6, new GameJLabel(name.getCityBuildingLabel(cityNumber, 4).getIcon()), false, name, cityNumber);
				  }
				  break;
			   case 5:
	           name.addBuildingtoCity(cityNumber, 6, 5);      
              for(int i = 0; i < 2; i++)//number of players         
				  {
				    if(name == tempArray[i])
	                tempArray[i].playerUI.lb.addBuilding(x+1, y+1, 6, name.getCityBuildingLabel(cityNumber, 5), false, name, cityNumber);
				    else
	                tempArray[i].playerUI.lb.addBuilding(x+1, y+1, 6, new GameJLabel(name.getCityBuildingLabel(cityNumber, 5).getIcon()), false, name, cityNumber);
				  }
				  break;
			   case 6:
	           name.addBuildingtoCity(cityNumber, 6, 6);      
              for(int i = 0; i < 2; i++)//number of players         
				  {
				    if(name == tempArray[i])
	                tempArray[i].playerUI.lb.addBuilding(x-1, y, 6, name.getCityBuildingLabel(cityNumber, 6), false, name, cityNumber);
				    else
	                tempArray[i].playerUI.lb.addBuilding(x-1, y, 6, new GameJLabel(name.getCityBuildingLabel(cityNumber, 6).getIcon()), false, name, cityNumber);
				  }
				  break;
			   case 7:
	           name.addBuildingtoCity(cityNumber, 6, 7);      
              for(int i = 0; i < 2; i++)//number of players         
				  {
				    if(name == tempArray[i])
	                tempArray[i].playerUI.lb.addBuilding(x+1, y, 6, name.getCityBuildingLabel(cityNumber, 7), false, name, cityNumber);
				    else
	                tempArray[i].playerUI.lb.addBuilding(x+1, y, 6, new GameJLabel(name.getCityBuildingLabel(cityNumber, 7).getIcon()), false, name, cityNumber);
				  }
				  break;
			 }
  }
  
  private void addHarbor(int k)
  {
          Player[] tempArray = name.getPlayerUI().getPlayers(); 
			 int x = name.getCityXpos(cityNumber);
          int y = name.getCityYpos(cityNumber);

          switch(k)
			 {
			   case 0:
	           name.addBuildingtoCity(cityNumber, 2, 0);      
              for(int i = 0; i < 2; i++)//number of players         
				  {
				    if(name == tempArray[i])
					 	tempArray[i].playerUI.lb.addBuilding(x-1, y-1, 2, name.getCityBuildingLabel(cityNumber, 0), false, name, cityNumber);
                else
					 	tempArray[i].playerUI.lb.addBuilding(x-1, y-1, 2, new GameJLabel(name.getCityBuildingLabel(cityNumber, 0).getIcon()), false, name, cityNumber);
				  }
				  break;
			   case 1:
	           name.addBuildingtoCity(cityNumber, 2, 1);      
              for(int i = 0; i < 2; i++)//number of players         
				  {
				    if(name == tempArray[i])
	                tempArray[i].playerUI.lb.addBuilding(x, y-1, 2, name.getCityBuildingLabel(cityNumber, 1), false, name, cityNumber);
                else
	                tempArray[i].playerUI.lb.addBuilding(x, y-1, 2, new GameJLabel(name.getCityBuildingLabel(cityNumber, 1).getIcon()), false, name, cityNumber);
				  }
				  break;
			   case 2:
	           name.addBuildingtoCity(cityNumber, 2, 2);      
              for(int i = 0; i < 2; i++)//number of players         
				  {
				    if(name == tempArray[i])
	                tempArray[i].playerUI.lb.addBuilding(x+1, y-1, 2, name.getCityBuildingLabel(cityNumber, 2), false, name, cityNumber);
                else
	                tempArray[i].playerUI.lb.addBuilding(x+1, y-1, 2, new GameJLabel(name.getCityBuildingLabel(cityNumber, 2).getIcon()), false, name, cityNumber);
				  }
				  break;
			   case 3:
	           name.addBuildingtoCity(cityNumber, 2, 3);      
              for(int i = 0; i < 2; i++)//number of players         
				  {
				    if(name == tempArray[i])
	                tempArray[i].playerUI.lb.addBuilding(x-1, y+1, 2, name.getCityBuildingLabel(cityNumber, 3), false, name, cityNumber);
                else
	                tempArray[i].playerUI.lb.addBuilding(x-1, y+1, 2, new GameJLabel(name.getCityBuildingLabel(cityNumber, 3).getIcon()), false, name, cityNumber);
				  }
				  break;
			   case 4:
	           name.addBuildingtoCity(cityNumber, 2, 4);      
              for(int i = 0; i < 2; i++)//number of players         
				  {
				    if(name == tempArray[i])
	                tempArray[i].playerUI.lb.addBuilding(x, y+1, 2, name.getCityBuildingLabel(cityNumber, 4), false, name, cityNumber);
                else
	                tempArray[i].playerUI.lb.addBuilding(x, y+1, 2, new GameJLabel(name.getCityBuildingLabel(cityNumber, 4).getIcon()), false, name, cityNumber);
				  }
				  break;
			   case 5:
	           name.addBuildingtoCity(cityNumber, 2, 5);      
              for(int i = 0; i < 2; i++)//number of players         
				  {
				    if(name == tempArray[i])
	                tempArray[i].playerUI.lb.addBuilding(x+1, y+1, 2, name.getCityBuildingLabel(cityNumber, 5), false, name, cityNumber);
                else
	                tempArray[i].playerUI.lb.addBuilding(x+1, y+1, 2, new GameJLabel(name.getCityBuildingLabel(cityNumber, 5).getIcon()), false, name, cityNumber);
				  }
				  break;
			   case 6:
	           name.addBuildingtoCity(cityNumber, 2, 6);      
              for(int i = 0; i < 2; i++)//number of players         
				  {
				    if(name == tempArray[i])
	                tempArray[i].playerUI.lb.addBuilding(x-1, y, 2, name.getCityBuildingLabel(cityNumber, 6), false, name, cityNumber);
                else
	                tempArray[i].playerUI.lb.addBuilding(x-1, y, 2, new GameJLabel(name.getCityBuildingLabel(cityNumber, 6).getIcon()), false, name, cityNumber);
				  }
				  break;
			   case 7:
	           name.addBuildingtoCity(cityNumber, 2, 7);      
              for(int i = 0; i < 2; i++)//number of players         
				  {
				    if(name == tempArray[i])
	                tempArray[i].playerUI.lb.addBuilding(x+1, y, 2, name.getCityBuildingLabel(cityNumber, 7), false, name, cityNumber);
                else
	                tempArray[i].playerUI.lb.addBuilding(x+1, y, 2, new GameJLabel(name.getCityBuildingLabel(cityNumber, 7).getIcon()), false, name, cityNumber);
				  }
				  break;
			 }
  }
  
  private void addMarket(int k)
  {
          Player[] tempArray = name.getPlayerUI().getPlayers(); 
			 int x = name.getCityXpos(cityNumber);
          int y = name.getCityYpos(cityNumber);

          switch(k)
			 {
			   case 0:
	           name.addBuildingtoCity(cityNumber, 4, 0);      
              for(int i = 0; i < 2; i++)//number of players         
				  {
				    if(name == tempArray[i])
	                tempArray[i].playerUI.lb.addBuilding(x-1, y-1, 4, name.getCityBuildingLabel(cityNumber, 0), false, name, cityNumber);
                else
	                tempArray[i].playerUI.lb.addBuilding(x-1, y-1, 4, new GameJLabel(name.getCityBuildingLabel(cityNumber, 0).getIcon()), false, name, cityNumber);
				  }
				  break;
			   case 1:
	           name.addBuildingtoCity(cityNumber, 4, 1);      
              for(int i = 0; i < 2; i++)//number of players         
				  {
				    if(name == tempArray[i])
	                tempArray[i].playerUI.lb.addBuilding(x, y-1, 4, name.getCityBuildingLabel(cityNumber, 1), false, name, cityNumber);
                else
	                tempArray[i].playerUI.lb.addBuilding(x, y-1, 4, new GameJLabel(name.getCityBuildingLabel(cityNumber, 1).getIcon()), false, name, cityNumber);
	           }
				  break;
			   case 2:
	           name.addBuildingtoCity(cityNumber, 4, 2);      
              for(int i = 0; i < 2; i++)//number of players         
				  {
				    if(name == tempArray[i])
	                tempArray[i].playerUI.lb.addBuilding(x+1, y-1, 4, name.getCityBuildingLabel(cityNumber, 2), false, name, cityNumber);
                else
	                tempArray[i].playerUI.lb.addBuilding(x+1, y-1, 4, new GameJLabel(name.getCityBuildingLabel(cityNumber, 2).getIcon()), false, name, cityNumber);
	           }
				  break;
			   case 3:
	           name.addBuildingtoCity(cityNumber, 4, 3);      
              for(int i = 0; i < 2; i++)//number of players         
				  {
				    if(name == tempArray[i])
	                tempArray[i].playerUI.lb.addBuilding(x-1, y+1, 4, name.getCityBuildingLabel(cityNumber, 3), false, name, cityNumber);
                else
	                tempArray[i].playerUI.lb.addBuilding(x-1, y+1, 4, new GameJLabel(name.getCityBuildingLabel(cityNumber, 3).getIcon()), false, name, cityNumber);
	           }
				  break;
			   case 4:
	           name.addBuildingtoCity(cityNumber, 4, 4);      
              for(int i = 0; i < 2; i++)//number of players         
				  {
				    if(name == tempArray[i])
	               tempArray[i].playerUI.lb.addBuilding(x, y+1, 4, name.getCityBuildingLabel(cityNumber, 4), false, name, cityNumber);
                else
	               tempArray[i].playerUI.lb.addBuilding(x, y+1, 4, new GameJLabel(name.getCityBuildingLabel(cityNumber, 4).getIcon()), false, name, cityNumber);
	           }
				  break;
			   case 5:
	           name.addBuildingtoCity(cityNumber, 4, 5);      
              for(int i = 0; i < 2; i++)//number of players         
				  {
				    if(name == tempArray[i])
	                tempArray[i].playerUI.lb.addBuilding(x+1, y+1, 4, name.getCityBuildingLabel(cityNumber, 5), false, name, cityNumber);
                else
	                tempArray[i].playerUI.lb.addBuilding(x+1, y+1, 4, new GameJLabel(name.getCityBuildingLabel(cityNumber, 5).getIcon()), false, name, cityNumber);
	           }
				  break;
			   case 6:
	           name.addBuildingtoCity(cityNumber, 4, 6);      
              for(int i = 0; i < 2; i++)//number of players         
				  {
				    if(name == tempArray[i])
	                tempArray[i].playerUI.lb.addBuilding(x-1, y, 4, name.getCityBuildingLabel(cityNumber, 6), false, name, cityNumber);
                else
	                tempArray[i].playerUI.lb.addBuilding(x-1, y, 4, new GameJLabel(name.getCityBuildingLabel(cityNumber, 6).getIcon()), false, name, cityNumber);
	           }
				  break;
			   case 7:
	           name.addBuildingtoCity(cityNumber, 4, 7);      
              for(int i = 0; i < 2; i++)//number of players         
				  {
				    if(name == tempArray[i])
	                tempArray[i].playerUI.lb.addBuilding(x+1, y, 4, name.getCityBuildingLabel(cityNumber, 7), false, name, cityNumber);
                else
	                tempArray[i].playerUI.lb.addBuilding(x+1, y, 4, new GameJLabel(name.getCityBuildingLabel(cityNumber, 7).getIcon()), false, name, cityNumber);
	           }
				  break;
			 }
  }
  
  private void addGranary(int k)
  {
          Player[] tempArray = name.getPlayerUI().getPlayers(); 
			 int x = name.getCityXpos(cityNumber);
          int y = name.getCityYpos(cityNumber);

          switch(k)
			 {
			   case 0:
	           name.addBuildingtoCity(cityNumber, 1, 0);      
              for(int i = 0; i < 2; i++)//number of players         
              {        
				    if(name == tempArray[i])
					   tempArray[i].playerUI.lb.addBuilding(x-1, y-1, 1, name.getCityBuildingLabel(cityNumber, 0), false, name, cityNumber);
					 else
					   tempArray[i].playerUI.lb.addBuilding(x-1, y-1, 1, new GameJLabel(name.getCityBuildingLabel(cityNumber, 0).getIcon()), false, name, cityNumber);
				  }
				  break;
			   case 1:
	           name.addBuildingtoCity(cityNumber, 1, 1);      
              for(int i = 0; i < 2; i++)//number of players         
              {
				    if(name == tempArray[i])
   				    tempArray[i].playerUI.lb.addBuilding(x, y-1, 1, name.getCityBuildingLabel(cityNumber, 1), false, name, cityNumber);
			       else
   				    tempArray[i].playerUI.lb.addBuilding(x, y-1, 1, new GameJLabel(name.getCityBuildingLabel(cityNumber, 1).getIcon()), false, name, cityNumber);
				  }
				  break;
			   case 2:
	           name.addBuildingtoCity(cityNumber, 1, 2);      
              for(int i = 0; i < 2; i++)//number of players         
              {
				    if(name == tempArray[i])
					   tempArray[i].playerUI.lb.addBuilding(x+1, y-1, 1, name.getCityBuildingLabel(cityNumber, 2), false, name, cityNumber);
				    else
					   tempArray[i].playerUI.lb.addBuilding(x+1, y-1, 1, new GameJLabel(name.getCityBuildingLabel(cityNumber, 2).getIcon()), false, name, cityNumber);
				  }
				  break;
			   case 3:
	           name.addBuildingtoCity(cityNumber, 1, 3);      
              for(int i = 0; i < 2; i++)//number of players         
              {
				    if(name == tempArray[i])
					    tempArray[i].playerUI.lb.addBuilding(x-1, y+1, 1, name.getCityBuildingLabel(cityNumber, 3), false, name, cityNumber);
                else
					    tempArray[i].playerUI.lb.addBuilding(x-1, y+1, 1, new GameJLabel(name.getCityBuildingLabel(cityNumber, 3).getIcon()), false, name, cityNumber);
				  }
				  break;
			   case 4:
	           name.addBuildingtoCity(cityNumber, 1, 4);      
              for(int i = 0; i < 2; i++)//number of players         
              {
				    if(name == tempArray[i])
					    tempArray[i].playerUI.lb.addBuilding(x, y+1, 1, name.getCityBuildingLabel(cityNumber, 4), false, name, cityNumber);
                else
					    tempArray[i].playerUI.lb.addBuilding(x, y+1, 1, new GameJLabel(name.getCityBuildingLabel(cityNumber, 4).getIcon()), false, name, cityNumber);
				  }
				  break;
			   case 5:
	           name.addBuildingtoCity(cityNumber, 1, 5);      
              for(int i = 0; i < 2; i++)//number of players         
              {
				    if(name == tempArray[i])
					    tempArray[i].playerUI.lb.addBuilding(x+1, y+1, 1, name.getCityBuildingLabel(cityNumber, 5), false, name, cityNumber);
                else
					    tempArray[i].playerUI.lb.addBuilding(x+1, y+1, 1, new GameJLabel(name.getCityBuildingLabel(cityNumber, 5).getIcon()), false, name, cityNumber);
				  }
				  break;
			   case 6:
	           name.addBuildingtoCity(cityNumber, 1, 6);      
              for(int i = 0; i < 2; i++)//number of players         
              {
				    if(name == tempArray[i])
					    tempArray[i].playerUI.lb.addBuilding(x-1, y, 1, name.getCityBuildingLabel(cityNumber, 6), false, name, cityNumber);
                else
					    tempArray[i].playerUI.lb.addBuilding(x-1, y, 1, new GameJLabel(name.getCityBuildingLabel(cityNumber, 6).getIcon()), false, name, cityNumber);
				  }
				  break;
			   case 7:
	           name.addBuildingtoCity(cityNumber, 1, 7);      
              for(int i = 0; i < 2; i++)//number of players         
              {
				    if(name == tempArray[i])
					    tempArray[i].playerUI.lb.addBuilding(x+1, y, 1, name.getCityBuildingLabel(cityNumber, 7), false, name, cityNumber);
                else
					    tempArray[i].playerUI.lb.addBuilding(x+1, y, 1, new GameJLabel(name.getCityBuildingLabel(cityNumber, 7).getIcon()), false, name, cityNumber);
				  }
				  break;
			 }
  } 
  
  private void addBarracks(int k)
  {
          Player[] tempArray = name.getPlayerUI().getPlayers(); 
          int x = name.getCityXpos(cityNumber);
          int y = name.getCityYpos(cityNumber);

          switch(k)
			 {
			   case 0:
	           name.addBuildingtoCity(cityNumber, 0, 0);      
              for(int i = 0; i < 2; i++)//number of players         
				  {
				    if(name == tempArray[i])
			          tempArray[i].playerUI.lb.addBuilding(x-1, y-1, 0, name.getCityBuildingLabel(cityNumber, 0), false, name, cityNumber);
                else    
			          tempArray[i].playerUI.lb.addBuilding(x-1, y-1, 0, new GameJLabel(name.getCityBuildingLabel(cityNumber, 0).getIcon()), false, name, cityNumber);
              }
				  break;
			   case 1:
	           name.addBuildingtoCity(cityNumber, 0, 1);      
              for(int i = 0; i < 2; i++)//number of players         
				  {
				    if(name == tempArray[i])
	                tempArray[i].playerUI.lb.addBuilding(x, y-1, 0, name.getCityBuildingLabel(cityNumber, 1), false, name, cityNumber);
                else    
	                tempArray[i].playerUI.lb.addBuilding(x, y-1, 0, new GameJLabel(name.getCityBuildingLabel(cityNumber, 1).getIcon()), false, name, cityNumber);
				  }
				  break;
			   case 2:
	           name.addBuildingtoCity(cityNumber, 0, 2);      
              for(int i = 0; i < 2; i++)//number of players         
				  {
				    if(name == tempArray[i])
	                tempArray[i].playerUI.lb.addBuilding(x+1, y-1, 0, name.getCityBuildingLabel(cityNumber, 2), false, name, cityNumber);
                else    
	                tempArray[i].playerUI.lb.addBuilding(x+1, y-1, 0, new GameJLabel(name.getCityBuildingLabel(cityNumber, 2).getIcon()), false, name, cityNumber);
				  }
				  break;
			   case 3:
	           name.addBuildingtoCity(cityNumber, 0, 3);      
              for(int i = 0; i < 2; i++)//number of players         
				  {
				    if(name == tempArray[i])
	                tempArray[i].playerUI.lb.addBuilding(x-1, y+1, 0, name.getCityBuildingLabel(cityNumber, 3), false, name, cityNumber);
                else    
	                tempArray[i].playerUI.lb.addBuilding(x-1, y+1, 0, new GameJLabel(name.getCityBuildingLabel(cityNumber, 3).getIcon()), false, name, cityNumber);
				  }
				  break;
			   case 4:
	           name.addBuildingtoCity(cityNumber, 0, 4);      
              for(int i = 0; i < 2; i++)//number of players         
				  {
				    if(name == tempArray[i])
	                tempArray[i].playerUI.lb.addBuilding(x, y+1, 0, name.getCityBuildingLabel(cityNumber, 4), false, name, cityNumber);
                else    
	                tempArray[i].playerUI.lb.addBuilding(x, y+1, 0, new GameJLabel(name.getCityBuildingLabel(cityNumber, 4).getIcon()), false, name, cityNumber);
				  }
				  break;
			   case 5:
	           name.addBuildingtoCity(cityNumber, 0, 5);      
              for(int i = 0; i < 2; i++)//number of players         
				  {
				    if(name == tempArray[i])
	                tempArray[i].playerUI.lb.addBuilding(x+1, y+1, 0, name.getCityBuildingLabel(cityNumber, 5), false, name, cityNumber);
                else    
	                tempArray[i].playerUI.lb.addBuilding(x+1, y+1, 0, new GameJLabel(name.getCityBuildingLabel(cityNumber, 5).getIcon()), false, name, cityNumber);
				  }
				  break;
			   case 6:
	           name.addBuildingtoCity(cityNumber, 0, 6);      
              for(int i = 0; i < 2; i++)//number of players         
				  {
				    if(name == tempArray[i])
	                tempArray[i].playerUI.lb.addBuilding(x-1, y, 0, name.getCityBuildingLabel(cityNumber, 6), false, name, cityNumber);
                else    
	                tempArray[i].playerUI.lb.addBuilding(x-1, y, 0, new GameJLabel(name.getCityBuildingLabel(cityNumber, 6).getIcon()), false, name, cityNumber);
				  }
				  break;
			   case 7:
	           name.addBuildingtoCity(cityNumber, 0, 7);      
              for(int i = 0; i < 2; i++)//number of players         
				  {
				    if(name == tempArray[i])
	                tempArray[i].playerUI.lb.addBuilding(x+1, y, 0, name.getCityBuildingLabel(cityNumber, 7), false, name, cityNumber);
                else    
	                tempArray[i].playerUI.lb.addBuilding(x+1, y, 0, new GameJLabel(name.getCityBuildingLabel(cityNumber, 7).getIcon()), false, name, cityNumber);
				  }
				  break;
			 }
  }

  private void addLibrary(int k)
  {
          Player[] tempArray = name.getPlayerUI().getPlayers(); 
          int x = name.getCityXpos(cityNumber);
          int y = name.getCityYpos(cityNumber);

          switch(k)
			 {
			   case 0:
	           name.addBuildingtoCity(cityNumber, 3, 0);      
              for(int i = 0; i < 2; i++)//number of players         
				  {
				    if(name == tempArray[i])
					 	tempArray[i].playerUI.lb.addBuilding(x-1, y-1, 3, name.getCityBuildingLabel(cityNumber, 0), false, name, cityNumber);
                else
					 	tempArray[i].playerUI.lb.addBuilding(x-1, y-1, 3, new GameJLabel(name.getCityBuildingLabel(cityNumber, 0).getIcon()), false, name, cityNumber);
				  }
				  break;
			   case 1:
	           name.addBuildingtoCity(cityNumber, 3, 1);      
              for(int i = 0; i < 2; i++)//number of players         
				  {
				    if(name == tempArray[i])
	                tempArray[i].playerUI.lb.addBuilding(x, y-1, 3, name.getCityBuildingLabel(cityNumber, 1), false, name, cityNumber);
                else
	                tempArray[i].playerUI.lb.addBuilding(x, y-1, 3, new GameJLabel(name.getCityBuildingLabel(cityNumber, 1).getIcon()), false, name, cityNumber);
				  }
				  break;
			   case 2:
	           name.addBuildingtoCity(cityNumber, 3, 2);      
              for(int i = 0; i < 2; i++)//number of players         
				  {
				    if(name == tempArray[i])
	                tempArray[i].playerUI.lb.addBuilding(x+1, y-1, 3, name.getCityBuildingLabel(cityNumber, 2), false, name, cityNumber);
                else
	                tempArray[i].playerUI.lb.addBuilding(x+1, y-1, 3, new GameJLabel(name.getCityBuildingLabel(cityNumber, 2).getIcon()), false, name, cityNumber);
				  }
				  break;
			   case 3:
	           name.addBuildingtoCity(cityNumber, 3, 3);      
              for(int i = 0; i < 2; i++)//number of players         
				  {
				    if(name == tempArray[i])
	                tempArray[i].playerUI.lb.addBuilding(x-1, y+1, 3, name.getCityBuildingLabel(cityNumber, 3), false, name, cityNumber);
                else
	                tempArray[i].playerUI.lb.addBuilding(x-1, y+1, 3, new GameJLabel(name.getCityBuildingLabel(cityNumber, 3).getIcon()), false, name, cityNumber);
				  }
				  break;
			   case 4:
	           name.addBuildingtoCity(cityNumber, 3, 4);      
              for(int i = 0; i < 2; i++)//number of players         
				  {
				    if(name == tempArray[i])
	                tempArray[i].playerUI.lb.addBuilding(x, y+1, 3, name.getCityBuildingLabel(cityNumber, 4), false, name, cityNumber);
                else
	                tempArray[i].playerUI.lb.addBuilding(x, y+1, 3, new GameJLabel(name.getCityBuildingLabel(cityNumber, 4).getIcon()), false, name, cityNumber);
				  }
				  break;
			   case 5:
	           name.addBuildingtoCity(cityNumber, 3, 5);      
              for(int i = 0; i < 2; i++)//number of players         
				  {
				    if(name == tempArray[i])
	                tempArray[i].playerUI.lb.addBuilding(x+1, y+1, 3, name.getCityBuildingLabel(cityNumber, 5), false, name, cityNumber);
                else
	                tempArray[i].playerUI.lb.addBuilding(x+1, y+1, 3, new GameJLabel(name.getCityBuildingLabel(cityNumber, 5).getIcon()), false, name, cityNumber);
				  }
				  break;
			   case 6:
	           name.addBuildingtoCity(cityNumber, 3, 6);      
              for(int i = 0; i < 2; i++)//number of players         
				  {
				    if(name == tempArray[i])
	                tempArray[i].playerUI.lb.addBuilding(x-1, y, 3, name.getCityBuildingLabel(cityNumber, 6), false, name, cityNumber);
                else
	                tempArray[i].playerUI.lb.addBuilding(x-1, y, 3, new GameJLabel(name.getCityBuildingLabel(cityNumber, 6).getIcon()), false, name, cityNumber);
				  }
				  break;
			   case 7:
	           name.addBuildingtoCity(cityNumber, 3, 7);      
              for(int i = 0; i < 2; i++)//number of players         
				  {
				    if(name == tempArray[i])
	                tempArray[i].playerUI.lb.addBuilding(x+1, y, 3, name.getCityBuildingLabel(cityNumber, 7), false, name, cityNumber);
                else
	                tempArray[i].playerUI.lb.addBuilding(x+1, y, 3, new GameJLabel(name.getCityBuildingLabel(cityNumber, 7).getIcon()), false, name, cityNumber);
				  }
				  break;
			 }
  }

}//end class