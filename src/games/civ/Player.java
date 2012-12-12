   package games.civ;
	
   import java.io.Serializable;
   import java.util.*;
   import javax.swing.*;
   import java.awt.*;
   import java.awt.event.*;
   import java.awt.image.*;
	import java.beans.*;
   import javax.swing.border.*;
	import games.*;

   public class Player extends Thread implements MouseListener, ActionListener, Serializable{

	   private static final long serialVersionUID = -6725876658946858673L;

      final int MAX_ARMY = 6;
      final int MAX_SETTLER = 2;
      final int MAX_CITIES = 3;
      final int MAX_TRADE = 27;
   
      CivGUI playerUI;
      PlayerBoard playerBoard;
      GameJLayeredPane playersArea, playerCard, cardHolder, playerTechpane;
      GameJPanel playerTurnholder;
      Vector unitArmyLabels, unitSettlerLabels;  
	
      GameInternalFrame select;
      Vector tech;//lay out techs so each int relates to a specific technology
      Tech[] techListener;
      GameJLabel[] wonderListener; 
      GameJButton[] cityButtons, settlerButtons, armyButtons;    
		Vector cities;
      Vector settlers;
      Vector armies;
      Vector unitHand;
      Vector greatPerson;
      Vector hutRes;
		Vector villageRes;    
		String nation, color;
      Color playerColor;
      int culture;
      int gold, cityGold;
      int trade;
      int stackLimit; 
      int handLimitCulture;
      int moveLimit;
      int Iron, Silk, Incense, Wheat, Spy, Uranium, marketWheat, marketIron, marketSilk, marketIncense;
      int infLevel, mntLevel, artLevel, acftLevel;
      int numLvl1, numLvl2, numLvl3, numLvl4;
      int gov, freeGov;
      int numWonders = 0;
      int combatBonus;    
	   int startChoice;
		int numGroups = 0, group1total = 0, group2total = 0, group3total = 0, group4total = 0;
		int playerNumber;
		boolean canWaterLand, canWaterCross;
      boolean hasIrrigation, codeOfLaw;
      boolean firstTurn;
      boolean freeGovChange, inAnarchy;
		boolean whiteArmy;
      GameJLabel trDial, goDial, playerSettler, playerArmy, playerCity;
      Card[] availableWonders;
//      GameJLayeredPane techPane;
      GameJLayeredPane wondersPane;
      GameJLayeredPane culturePane;
      JTextArea cultureHandLim;  
	
      public Player(){}//bean default
		
	   //for village  
		public Player(String nation)
      {
         this.nation = nation;
         infLevel = 1;
         mntLevel = 1;
         artLevel = 1;
         acftLevel = 0;
         combatBonus = 0;      
         unitHand = new Vector(0,1);
//			addUnitToHand(CivGUI.combatDeck.getInfantry(1));
//			addUnitToHand(CivGUI.combatDeck.getMounted(1));
//			addUnitToHand(CivGUI.combatDeck.getArtillery(1));
		}

		public Player(String nation, String col, CivGUI gui, int playerNum)
      {
         playersArea = new GameJLayeredPane();
			playersArea.setPreferredSize(new Dimension(700, 500));
         cardHolder = new GameJLayeredPane();
			   
		   whiteArmy = false;

         playerNumber = playerNum;
			playerUI = new CivGUI(this, gui);
//			playerUI = (CivGUI)gui.clone();  

         unitArmyLabels = new Vector(0, 1);
         unitSettlerLabels = new Vector(0, 1);
        
		   numWonders = 0;
         marketWheat = 0;
         marketIron = 0;
         marketSilk = 0;
         marketIncense = 0;
         Iron = 0;
         Silk = 0;
         Incense = 0;
         Wheat = 0; 
         Spy = 0;
         Uranium = 0;
         trDial = new GameJLabel();
         goDial = new GameJLabel();
         hutRes = new Vector(0,1);
         villageRes = new Vector(0,1);
         unitHand = new Vector(0,1);
         greatPerson = new Vector(0,1);
         tech = new Vector(36);//there are 36 techs 0-35 and when null are not yet reserched
         for(int k = 0; k < 36; k++)
            tech.add(k, null); 
         firstTurn = false;
         color = col;
         codeOfLaw = false;
         this.nation = nation;
         moveLimit = 2;
         infLevel = 1;
         mntLevel = 1;
         artLevel = 1;
         acftLevel = 0;
         combatBonus = 0;      
			numLvl1 = 0;  
         numLvl2 = 0;  
         numLvl3 = 0;  
         numLvl4 = 0;  
         techListener = new Tech[36];
         wonderListener = new GameJLabel[12];
         freeGovChange = false;
         inAnarchy = false;
         gov = 7;
      
         wondersPane = new GameJLayeredPane();
         wondersPane.setPreferredSize(new Dimension(90, 220));
         wondersPane.setBounds(710, 50, 90, 220);	  	 
         wondersPane.setBackground(new Color(255, 0, 255));
         wondersPane.setOpaque(true);	  	 
         availableWonders = new Card[4];
      
         culturePane = new GameJLayeredPane();
         culturePane.setPreferredSize(new Dimension(90, 250));
         culturePane.setBounds(615, 50, 90, 250);	  	 
         culturePane.setBackground(new Color(0, 255, 255));
         culturePane.setOpaque(true);	  	 
         cultureHandLim = new JTextArea();    
		   cultureHandLim.setOpaque(false);
		   cultureHandLim.setEditable(false);
		   cultureHandLim.setForeground(new Color(255, 255, 255));
		   cultureHandLim.setBounds(3, 3, 100, 30);
         culturePane.add(cultureHandLim, new Integer(0));		
      
         if(color.equals("Red"))	 
            playerColor = new Color(255, 0, 0);
         else if(color.equals("Yellow"))	 
            playerColor = new Color(255, 255, 0);
         else if(color.equals("Green"))	 
            playerColor = new Color(0, 255, 0);
         else if(color.equals("Blue"))	 
            playerColor = new Color(0, 0, 255);
         else if(color.equals("Gray")) //for villages
            playerColor = new Color(100, 100, 100);
      
         if(nation.equals("America"))
         {
            stackLimit = 2;
            gold = 0;
            trade = 0;
            culture = 0;
            handLimitCulture = 2;
            cities = new Vector(3);
            settlers = new Vector(2);	 
            armies = new Vector(6);	 
            canWaterLand = false;
            canWaterCross = false;
            hasIrrigation = false;
            playerBoard = new PlayerBoard(this);  
            addTech(2);
         }
         else if(nation.equals("China"))
         {
            stackLimit = 2;
            gold = 0;
            trade = 0;
            culture = 0;
            handLimitCulture = 2;
            cities = new Vector(3);
            settlers = new Vector(2);	 
            armies = new Vector(6);	 
            canWaterLand = false;
            canWaterCross = false;
            hasIrrigation = false;
            playerBoard = new PlayerBoard(this);  
            addTech(9);
			}
         else if(nation.equals("Egypt"))
         {
            stackLimit = 2;
            gold = 0;
            trade = 0;
            culture = 0;
            handLimitCulture = 2;
            cities = new Vector(3);
            settlers = new Vector(2);	 
            armies = new Vector(6);	 
            canWaterLand = false;
            canWaterCross = false;
            hasIrrigation = false;
            playerBoard = new PlayerBoard(this);  
            addTech(12);
         }
         else if(nation.equals("Germany"))
         {
            stackLimit = 2;
            gold = 0;
            trade = 0;
            culture = 0;
            handLimitCulture = 2;
            cities = new Vector(3);
            settlers = new Vector(2);	 
            armies = new Vector(6);	 
            canWaterLand = false;
            canWaterCross = false;
            hasIrrigation = false;
            playerBoard = new PlayerBoard(this);  
            addTech(5);
         }
         else if(nation.equals("Rome"))
         {
            stackLimit = 2;
            gold = 0;
            trade = 0;
            culture = 0;
            handLimitCulture = 2;
            cities = new Vector(3);
            settlers = new Vector(2);	 
            armies = new Vector(6);	 
            canWaterLand = false;
            canWaterCross = false;
            hasIrrigation = false;
            gov = 1;
            playerBoard = new PlayerBoard(this);  
            addTech(1);
         }
         else if(nation.equals("Russia"))
         {
            stackLimit = 3;
            gold = 0;
            trade = 0;
            culture = 0;
            handLimitCulture = 2;
            cities = new Vector(3);
            settlers = new Vector(2);	 
            armies = new Vector(6);	 
            canWaterLand = false;
            canWaterCross = false;
            hasIrrigation = false;
            gov = 5;  
            playerBoard = new PlayerBoard(this);  
            addTech(22);
         }

         if(!nation.equals("Village"))
			{
            cultureHandLim.setText("Culture Hand\n Limit: "+handLimitCulture);    
System.out.println("pass 2");

				addUnitToHand(playerUI.combatDeck.getInfantry(1));
System.out.println("pass 3");
				if(nation.equals("Germany"))
				{
				  addUnitToHand(playerUI.combatDeck.getInfantry(1));
				  addUnitToHand(playerUI.combatDeck.getInfantry(1));
				}
				addUnitToHand(playerUI.combatDeck.getMounted(1));
				addUnitToHand(playerUI.combatDeck.getArtillery(1));
				
			   updateUnits();
System.out.println("pass 4");
//            startCity();
			   startGov();
System.out.println("pass 5");
				
				playerCard = new GameJLayeredPane();
				playerCard = playerBoard.getPlayerboard();
				playerTurnholder = new GameJPanel();
				playerTurnholder = playerBoard.getPlayerturnholder();
            playerTechpane = new GameJLayeredPane();
            playerTechpane = playerBoard.getPlayertechpane(); 

		      playersArea.add(playerTechpane, new Integer(0));	
		      playersArea.add(wondersPane, new Integer(1));	
		      playersArea.add(culturePane, new Integer(1));	

				playersArea.add(playerCard, new Integer(1));
				playersArea.add(playerTurnholder, new Integer(1));
				
			   playersArea.setBounds(20, 420, 805, 458);
			   playersArea.setOpaque(false);
			   playersArea.setBackground(new Color(50, 50, 50));

				playerUI.mainBoard.add(playersArea, new Integer(5));
			}
System.out.println("pass 6");
 		}

      public void initStartCity(){
         startCity();
		}
		
      public void run()
      {
System.out.println("passed RUN");
         if(!playerUI.gameEnd)//game engine
            playerUI.turn.startTurn(this);
         else
            System.exit(0);
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
   
	   public void drawAIstuff(Player p)
		{
		  City c = (City)p.getCityVector().get(0);//number of players
		  GameJLabel playerCity = new GameJLabel(c.getIcon());
        playerCity.setBounds(0, 0, 55, 55);
		  playerCity.addMouseListener(this);     
		  playerUI.lb.getHolder(c.getXPos(), c.getYPos()).add(playerCity, new Integer(1));
		  playerUI.lb.addPeice(2, c.getXPos(), c.getYPos());	  
		
		  Settler s = (Settler)p.getSettlerVector().get(0);
		  GameJLabel sett = new GameJLabel(s.getIcon());
		  sett.setBounds(00, 00, 20, 20);
		  sett.addMouseListener(this);
	     sett.setName(Integer.toString(p.getSettlerVector().size()));    
		  playerUI.lb.getHolder(s.getX(), s.getY()).add(sett, new Integer(5));
		  playerUI.lb.addSettlerCount(s.getX(), s.getY());
        unitSettlerLabels.add(sett);

		  Army a = (Army)p.getArmyVector().get(0);
		  GameJLabel arm = new GameJLabel(a.getIcon());
		  arm.setBounds(20*p.playerUI.lb.getSettlerCount(a.getX(), a.getY()), 00, 15, 21);
	     arm.setName(Integer.toString(p.getArmyVector().size()));    
//System.out.println("ARMY PLACED NAME "+arm.getName());  

		  arm.addMouseListener(this);
		  playerUI.lb.getHolder(a.getX(), a.getY()).add(arm, new Integer(5));
		  playerUI.lb.addArmyCount(a.getX(), a.getY());
        unitArmyLabels.add(arm);
		
		  //if name is russia add white Army!!
//		  arm.setBounds(20*(p.playerUI.lb.getSettlerCount(a.getX(), a.getY()) + p.playerUI.lb.getArmyCount(a.getX(), a.getY())), 00, 15, 21);
		}
		 
	   public void drawPlayerstuff(Player p)
		{
//System.out.println(this+" "+p.getNation());
		  City c = (City)p.getCityVector().get(0);
		  GameJLabel playerCity = new GameJLabel(c.getIcon());
        playerCity.setBounds(0, 0, 55, 55);
//		  playerCity.addMouseListener(this);     
System.out.println(c.getXPos() +" "+ c.getYPos());
		  playerUI.lb.getHolder(c.getXPos(), c.getYPos()).add(playerCity, new Integer(1));
		  playerUI.lb.addPeice(2, c.getXPos(), c.getYPos());	  
System.out.println(playerUI.lb.getOwner(c.getXPos(), c.getYPos()).getNation());
		
		  Settler s = (Settler)p.getSettlerVector().get(0);
		  GameJLabel sett = new GameJLabel(s.getIcon());
		  sett.setBounds(00, 00, 20, 20);
	     sett.setName(Integer.toString(p.getSettlerVector().size()));    
//		  sett.addMouseListener(this);
		  playerUI.lb.getHolder(s.getX(), s.getY()).add(sett, new Integer(5));
		  playerUI.lb.addSettlerCount(s.getX(), s.getY());
        unitSettlerLabels.add(sett);

		  Army a = (Army)p.getArmyVector().get(0);
		  GameJLabel arm = new GameJLabel(a.getIcon());
		  arm.setBounds(20*p.playerUI.lb.getSettlerCount(a.getX(), a.getY()), 00, 15, 21);
	     arm.setName(Integer.toString(p.getArmyVector().size()));    
//		  arm.addMouseListener(this);
		  playerUI.lb.getHolder(a.getX(), a.getY()).add(arm, new Integer(5));
		  playerUI.lb.addArmyCount(a.getX(), a.getY());
        unitArmyLabels.add(arm);
		
		  //if name is russia add white Army!!
//		  arm.setBounds(20*(p.playerUI.lb.getSettlerCount(a.getX(), a.getY()) + p.playerUI.lb.getArmyCount(a.getX(), a.getY())), 00, 15, 21);
		}
      
      public int getPlayerNumber(){
		   return playerNumber;
		}
		
		public PlayerBoard getPlayerBoard()
		{
		  return playerBoard;
//		  new PlayerBoard((PlayerBoard)playerBoard.clone());
		}
		
      public int getNumGroups()
		{
		  return numGroups;
		}  

      public void addNumGroups()
		{
		  numGroups++;
		}
		
      public void subNumGroups()
		{
		  numGroups--;
		}

      public void subGrouptotal(int n)
		{
		  switch(n)
		  {
		    case 1:
            group1total--;
			   break;
		    case 2:
            group2total--;
			   break;
		    case 3:
            group3total--;
			   break;
		    case 4:
            group4total--;
			   break;
		  }
      }
		
      public void addGrouptotal(int n)
		{
		  switch(n)
		  {
		    case 1:
            group1total++;
			   break;
		    case 2:
            group2total++;
			   break;
		    case 3:
            group3total++;
			   break;
		    case 4:
            group4total++;
			   break;
		  }
		}
		
      public int getGrouptotal(int n)
		{
		  switch(n)
		  {
		    case 1:
            return group1total;
//			   break;
		    case 2:
            return group2total;
//			   break;
		    case 3:
            return group3total;
//			   break;
		    case 4:
            return group4total;
//			   break;
		  }
		  return 0;
		}

      public void resetGrouptotal(int n)
		{
        switch(n)
		  {
		    case 1:
            group1total -= group1total;
			   break;
		    case 2:
            group2total -= group2total;
			   break;
		    case 3:
            group3total -= group3total;
			   break;
		    case 4:
            group4total -= group4total;
			   break;
		  }
		}
	 
      public void disbandGroup(int grp)
		{
        int k;
        Settler tempSett;
		  Army tempArm;
		  for(k = 0; k < settlers.size(); k++)
		  {
		    tempSett = (Settler)settlers.get(k);
		    if(tempSett.getGroup() == grp)
			 {
		      subGrouptotal(tempSett.getGroup());
			   tempSett.setGroup(0);
          }
          else if(tempSett.getGroup() > grp)
			 {
		      subGrouptotal(tempSett.getGroup());
			   tempSett.setGroup(tempSett.getGroup()-1);
		      addGrouptotal(tempSett.getGroup());  
			 }
		  }
		  for(k = 0; k < armies.size(); k++)
		  {
		    tempArm = (Army)armies.get(k);
		    if(tempArm.getGroup() == grp)
			 {
		      subGrouptotal(tempArm.getGroup());
				tempArm.setGroup(0);
          }  
	       else if(tempArm.getGroup() > grp)
			 {
		      subGrouptotal(tempArm.getGroup());
			   tempArm.setGroup(tempArm.getGroup()-1);
		      addGrouptotal(tempArm.getGroup());  
			 }
		  }
//		  numGroups--;
		}
		
	   public void addAvailableWonder(Card c, int index)
      {
         c.getLabel().addMouseListener(this);
         availableWonders[index] = c;
      }
   
      public int getAvailableWonder(int index)
      {
         if(availableWonders[index] != null)
         {
            Card temp = (Card)availableWonders[index];
            return temp.getValue();  
         }
         else
            return -1;
      }
		
      public void addWonderCard(int nm)
      {
			for(int k = 0; k < 4; k++)
         {
            if(availableWonders[k] != null)
				{
					Card temp = (Card)availableWonders[k];
	            if(temp.getValue() == (nm-20))
	            {
	               GameJLabel l = new GameJLabel(new GameRotatedIcon(temp.getFrontIcon(), 180));
	               l.setBounds(00, 00+(60*numWonders), 88, 53);
	               wondersPane.add(l, new Integer(0));
	               wondersPane.repaint();
	            //        need to blow up this icon still
	               wonderListener[nm-20] = l;
	               wonderListener[nm-20].addMouseListener(this);
	               numWonders++; 
	               playerUI.replaceWonder(k);  
	            }
	            else
	               System.out.println("bad algorithm in wonders");
            }      
			}
      
      }
   
      public void obsoleteWonder(int nm)
		{
		  if(wonderListener[nm-20] != null)
		  {
           if((nm-20) < 4)
           {
				  wonderListener[nm-20].removeMouseListener(this);
	           GameJLabel l = (GameJLabel)wonderListener[nm-20];
				  wondersPane.remove(wondersPane.getIndexOf(l));		  
			     l.setIcon(getImage("data/ancientCard.png"));
              wondersPane.add(l);      
			     wondersPane.repaint();
			     wonderListener[nm-20] = null;
			  }
		  }
		}
		    
		public void updateAvailableWonders(int w, Card c)
      {
         for(int k = 0; k < 4; k++)
         {
            Card temp = (Card)availableWonders[k];
            if(temp.getValue() == w)
            {
               availableWonders[w] = c;
            }
         }
      }
   
      public boolean getWonderCards(int index)
		{
		  if(wonderListener[index] != null) 
		    return true;
		  return false;	 
		}
		
      public void startGov()
      {
			if(gov == 0)
		   {
			  playerBoard.govCard.setIcon(getImage("data/anarchyGOV.png"));
			}  
			else if(gov == 1)
			{
			  playerBoard.govCard.setIcon(getImage("data/republicGOV.png"));
			}  
			else if(gov == 2)
			{
			  playerBoard.govCard.setIcon(getImage("data/democracyGOV.png"));
			}  
			else if(gov == 3)
			{
			  playerBoard.govCard.setIcon(getImage("data/monarchyGOV.png"));
			}  
			else if(gov == 4)
			{
			  playerBoard.govCard.setIcon(getImage("data/feudalismGOV.png"));
			}  
			else if(gov == 5)
			{
			  playerBoard.govCard.setIcon(getImage("data/communismGOV.png"));
			}  
			else if(gov == 6)
			{
			  playerBoard.govCard.setIcon(getImage("data/fundamentalismGOV.png"));
			}  
			else if(gov == 7)
			{
			  playerBoard.govCard.setIcon(getImage("data/despotismGOV.png"));
			}  
		 
		   playersArea.repaint();

         if(gov == 3)
         {
            handLimitCulture++;
//            System.out.println("culture hand: "+handLimitCulture);	 
         }
         if(gov == 4)
         {
            gold++;
            TradeGUI.startTrade(this, playerBoard.playerBrd2, true);
         }
         freeGov = 7; 
      }


      public void changeGov()
      {
         freeGovChange = false;
         gov = freeGov;  
			
			if(gov == 0)
		   {
			  playerBoard.govCard.setIcon(getImage("data/anarchyGOV.png"));
			}  
			else if(gov == 1)
			{
			  playerBoard.govCard.setIcon(getImage("data/republicGOV.png"));
			}  
			else if(gov == 2)
			{
			  playerBoard.govCard.setIcon(getImage("data/democracyGOV.png"));
			}  
			else if(gov == 3)
			{
			  playerBoard.govCard.setIcon(getImage("data/monarchyGOV.png"));
			}  
			else if(gov == 4)
			{
			  playerBoard.govCard.setIcon(getImage("data/feudalismGOV.png"));
			}  
			else if(gov == 5)
			{
			  playerBoard.govCard.setIcon(getImage("data/communismGOV.png"));
			}  
			else if(gov == 6)
			{
			  playerBoard.govCard.setIcon(getImage("data/fundamentalismGOV.png"));
			}  
			else if(gov == 7)
			{
			  playerBoard.govCard.setIcon(getImage("data/despotismGOV.png"));
			}  
		 
		   playersArea.repaint();

         if(gov == 3)
         {
            handLimitCulture++;
//            System.out.println("culture hand: "+handLimitCulture);	 
         }
         if(gov == 4)
         {
            gold++;
            TradeGUI.startTrade(this, playerBoard.playerBrd2, true);
         }
         freeGov = 7; 
      }
   
      public void enterAnarchy()
      {
         freeGovChange = false;
         inAnarchy = true;
         if(gov == 3)
            handLimitCulture--;
         if(gov == 4)
            gold--;
			playerBoard.govCard.setIcon(getImage("data/anarchyGOV.png"));
         gov = 0;  
         freeGov = 0; 
      }
   
      public void resetAnarchy()
		{
		  inAnarchy = false;
		}
		    
		public boolean getAnarchy()
      {
         return inAnarchy;
      }
   
      public GameJLabel getGovHolder()
      {
         return playerBoard.govCard;
      }
   
      public boolean hasfreeGov()
      {
         return freeGovChange;
      }
   
      public void resetfreeGov()
      {
         freeGovChange = false;
      }
   
      public int getfreeGov()
      {
         return freeGov;
      }
   
      public int getCurrentGov()
      {
         return gov;
      }
   
      public void alterCurrentGov(int gov)
      {
         this.gov = gov;
//         CivGUI.drawGovernment(g, govCard);  
			if(gov == 0)
		   {
			  playerBoard.govCard.setIcon(getImage("data/anarchyGOV.png"));
			}  
			else if(gov == 1)
			{
			  playerBoard.govCard.setIcon(getImage("data/republicGOV.png"));
			}  
			else if(gov == 2)
			{
			  playerBoard.govCard.setIcon(getImage("data/democracyGOV.png"));
			}  
			else if(gov == 3)
			{
			  playerBoard.govCard.setIcon(getImage("data/monarchyGOV.png"));
			}  
			else if(gov == 4)
			{
			  playerBoard.govCard.setIcon(getImage("data/feudalismGOV.png"));
			}  
			else if(gov == 5)
			{
			  playerBoard.govCard.setIcon(getImage("data/communismGOV.png"));
			}  
			else if(gov == 6)
			{
			  playerBoard.govCard.setIcon(getImage("data/fundamentalismGOV.png"));
			}  
			else if(gov == 7)
			{
			  playerBoard.govCard.setIcon(getImage("data/despotismGOV.png"));
			}  

      }
   
   public void killUnit()
	{
      Random rng = new Random();  
	   Die dice = new Die(rng.nextInt(666));
	   int rand = dice.rollX(unitHand.size());
	   
		Object o = unitHand.get(rand);
		removeUnitHand(o);
	
		Card temp = (Card)o;
		GameImageIcon im = temp.getFrontIcon().getIcon();
	   playerUI.addUnitToGrave(temp);
	   playerUI.checkCombatDecks();
      
		for(int k = 0; k < 2; k++)//number of players
		{
	     if(this == playerUI.players[k])
		  {  
			  JDialog d = new JDialog(new JFrame(), "MONARCHY RESULT", false);
			  d.setSize(new Dimension(180, 220));
//	        d.getContentPane().setBackground(new Color(255, 255, 255));    
			  d.setResizable(false);
	
           GameJLabel l = new GameJLabel("You lost a random unit!");
           l.setBounds(20, 10, 180, 20);
			  l.setOpaque(false);
            
			  GameJLabel lost = new GameJLabel(im);
			  lost.setBounds(10, 40, 150, 150);  

			  GameJLayeredPane a = new GameJLayeredPane();
			  a.setBounds(0, 0, 180, 220);
			  a.setOpaque(false);
			  
           a.add(lost, new Integer(0));
			  a.add(l, new Integer(0));			   

			  d.add(a);
           d.setLocationRelativeTo(playerUI.players[k].playerBoard.playerBrd2);
	        d.setVisible(true);
        }
		  else
		  {
			  JDialog d = new JDialog(new JFrame(), "MONARCHY RESULT", false);
			  d.setSize(new Dimension(180, 220));
//	        d.getContentPane().setBackground(new Color(255, 255, 255));    
			  d.setResizable(false);
	
           GameJLabel l = new GameJLabel(nation+" lost a random unit!");
           l.setBounds(12, 10, 180, 20);
			  l.setOpaque(false);
            
			  GameJLabel lost = new GameJLabel(im);
			  lost.setBounds(10, 40, 150, 150);  

			  GameJLayeredPane a = new GameJLayeredPane();
			  a.setBounds(0, 0, 180, 220);
			  a.setOpaque(false);
			  
           a.add(lost, new Integer(0));
			  a.add(l, new Integer(0));			   

			  d.add(a);
           d.setLocationRelativeTo(playerUI.players[k].playerBoard.playerBrd2);
	        d.setVisible(true);
		  }  
	   }
	}
	  
	public void updateUnits()
	{
	  if(playersArea.getIndexOf(cardHolder) >= 0)
     {
//System.out.println("REMOVING HOLDER");
	    playersArea.remove(playersArea.getIndexOf(cardHolder));
       cardHolder.removeAll();  
	    cardHolder.repaint();
	  }   
	  GameJLayeredPane cardsInf = new GameJLayeredPane();
	  GameJLayeredPane cardsMnt = new GameJLayeredPane();
	  GameJLayeredPane cardsArt = new GameJLayeredPane();
	  GameJLayeredPane cardsAcft = new GameJLayeredPane();

	  cardsInf.setPreferredSize(new Dimension(150, 360));
     cardsInf.setBackground(new Color(0,0,0));

	  cardsMnt.setPreferredSize(new Dimension(150, 360));
     cardsMnt.setBackground(new Color(0,0,0));

	  cardsArt.setPreferredSize(new Dimension(150, 360));
     cardsArt.setBackground(new Color(0,0,0));

	  cardsAcft.setPreferredSize(new Dimension(150, 360));
     cardsAcft.setBackground(new Color(0,0,0));

	  Vector units = getUnitHand();
	  Vector inf = new Vector(0, 1);
	  Vector mnt = new Vector(0, 1);
	  Vector art = new Vector(0, 1);
	  Vector acft = new Vector(0, 1);
     for(int k = 0; k < units.size(); k++)
	  {
	    Card temp = (Card)units.get(k);
		 if(temp.getType().equals("infantry"))    
       { 
			inf.add(temp);    
		 }      
		 else if(temp.getType().equals("mounted"))    
       {
         mnt.add(temp);    
		 }    
		 else if(temp.getType().equals("artillery"))    
       { 
         art.add(temp);    
		 }    
		 else if(temp.getType().equals("aircraft"))    
       {
	      acft.add(temp);  
		 } 
	  }
	  if(inf.size() > 0)
     {
	    cardsInf.setBounds(00, 00, 160, 360);
	    for(int k = 0; k < inf.size(); k++)
		 {
	      GameJLabel label = new GameJLabel();    
		   Card temp = (Card)inf.get(k);
         label = temp.getLabel();
			GameJLabel l = new GameJLabel(temp.getFrontIcon());
		   l.setBounds(00, 00, 150, 150);
		   temp.setLabel(l);

			l.setBounds(00, (k*20)+20, 150, 150); 
		   cardsInf.add(l, new Integer(k));
		 }  
		 cardHolder.add(cardsInf, new Integer(0));
       if(mnt.size() > 0)  
       {    
		    cardsMnt.setBounds(160, 00, 160, 360);
		    for(int k = 0; k < mnt.size(); k++)
			 {
		      GameJLabel label = new GameJLabel();    
			   Card temp = (Card)mnt.get(k);
	         label = temp.getLabel();
			   GameJLabel l = new GameJLabel(temp.getFrontIcon());
			   l.setBounds(00, 00, 150, 150);
			   temp.setLabel(l);
	
				l.setBounds(00, (k*20)+20, 150, 150); 
			   cardsMnt.add(l, new Integer(k));
			 }  
	       cardHolder.add(cardsMnt, new Integer(0));
		   if(art.size() > 0)
         {
	        cardsArt.setBounds(320, 00, 160, 360);
		    for(int k = 0; k < art.size(); k++)
			 {
		      GameJLabel label = new GameJLabel();    
			   Card temp = (Card)art.get(k);
	         label = temp.getLabel();
			   GameJLabel l = new GameJLabel(temp.getFrontIcon());
			   l.setBounds(00, 00, 150, 150);
			   temp.setLabel(l);
	
				l.setBounds(00, (k*20)+20, 150, 150); 
			   cardsArt.add(l, new Integer(k));
			 }  
	        cardHolder.add(cardsArt, new Integer(0));
			}		 
		   else if(acft.size() > 0)
         {
		     cardsAcft.setBounds(340, 00, 160, 360);
		    for(int k = 0; k < acft.size(); k++)
			 {
		      GameJLabel label = new GameJLabel();    
			   Card temp = (Card)acft.get(k);
	         label = temp.getLabel();
			   GameJLabel l = new GameJLabel(temp.getFrontIcon());
			   l.setBounds(00, 00, 150, 150);
			   temp.setLabel(l);
	
				l.setBounds(00, (k*20)+20, 150, 150); 
			   cardsAcft.add(l, new Integer(k));
			 }  
	        cardHolder.add(cardsAcft, new Integer(0));
			}
		 }  
		 else if(art.size() > 0)  
	    {
	      cardsArt.setBounds(180, 00, 160, 360);
		    for(int k = 0; k < art.size(); k++)
			 {
		      GameJLabel label = new GameJLabel();    
			   Card temp = (Card)art.get(k);
	         label = temp.getLabel();
			   GameJLabel l = new GameJLabel(temp.getFrontIcon());
			   l.setBounds(00, 00, 150, 150);
			   temp.setLabel(l);
	
				l.setBounds(00, (k*20)+20, 150, 150); 
			   cardsArt.add(l, new Integer(k));
			 }  
	      cardHolder.add(cardsArt, new Integer(0));
		   if(acft.size() > 0)
			{
		     cardsAcft.setBounds(340, 00, 160, 360);
		    for(int k = 0; k < acft.size(); k++)
			 {
		      GameJLabel label = new GameJLabel();    
			   Card temp = (Card)acft.get(k);
	         label = temp.getLabel();
			   GameJLabel l = new GameJLabel(temp.getFrontIcon());
			   l.setBounds(00, 00, 150, 150);
			   temp.setLabel(l);
	
				l.setBounds(00, (k*20)+20, 150, 150); 
			   cardsAcft.add(l, new Integer(k));
			 }  
	        cardHolder.add(cardsAcft, new Integer(0));
			}
		 }
		 else if(acft.size() > 0)  
	    {
		   cardsAcft.setBounds(180, 00, 160, 360);
		    for(int k = 0; k < acft.size(); k++)
			 {
		      GameJLabel label = new GameJLabel();    
			   Card temp = (Card)acft.get(k);
	         label = temp.getLabel();
			   GameJLabel l = new GameJLabel(temp.getFrontIcon());
			   l.setBounds(00, 00, 150, 150);
			   temp.setLabel(l);
	
				l.setBounds(00, (k*20)+20, 150, 150); 
			   cardsAcft.add(l, new Integer(k));
			 }  
	      cardHolder.add(cardsAcft, new Integer(0));
		 }
	  }  
	  else if(mnt.size() > 0)  
	  {  
		 cardsMnt.setBounds(20, 00, 160, 360);
		    for(int k = 0; k < mnt.size(); k++)
			 {
		      GameJLabel label = new GameJLabel();    
			   Card temp = (Card)mnt.get(k);
	         label = temp.getLabel();
			   GameJLabel l = new GameJLabel(temp.getFrontIcon());
			   l.setBounds(00, 00, 150, 150);
			   temp.setLabel(l);
	
				l.setBounds(00, (k*20)+20, 150, 150); 
			   cardsMnt.add(l, new Integer(k));
			 }  
	    cardHolder.add(cardsMnt, new Integer(0));
       if(art.size() > 0)  
       {
	      cardsArt.setBounds(180, 00, 160, 360);
		    for(int k = 0; k < art.size(); k++)
			 {
		      GameJLabel label = new GameJLabel();    
			   Card temp = (Card)art.get(k);
	         label = temp.getLabel();
			   GameJLabel l = new GameJLabel(temp.getFrontIcon());
			   l.setBounds(00, 00, 150, 150);
			   temp.setLabel(l);
	
				l.setBounds(00, (k*20)+20, 150, 150); 
			   cardsArt.add(l, new Integer(k));
			 }  
	      cardHolder.add(cardsArt, new Integer(0));
		   if(acft.size() > 0)
		   {
		     cardsAcft.setBounds(340, 00, 160, 360);
		    for(int k = 0; k < acft.size(); k++)
			 {
		      GameJLabel label = new GameJLabel();    
			   Card temp = (Card)acft.get(k);
	         label = temp.getLabel();
			   GameJLabel l = new GameJLabel(temp.getFrontIcon());
			   l.setBounds(00, 00, 150, 150);
			   temp.setLabel(l);
	
				l.setBounds(00, (k*20)+20, 150, 150); 
			   cardsAcft.add(l, new Integer(k));
			 }  
	        cardHolder.add(cardsAcft, new Integer(0));
			}
		 }
	    else if(acft.size() > 0)
	    {
		   cardsAcft.setBounds(180, 00, 160, 360);
		    for(int k = 0; k < acft.size(); k++)
			 {
		      GameJLabel label = new GameJLabel();    
			   Card temp = (Card)acft.get(k);
	         label = temp.getLabel();
			   GameJLabel l = new GameJLabel(temp.getFrontIcon());
			   l.setBounds(00, 00, 150, 150);
			   temp.setLabel(l);
	
				l.setBounds(00, (k*20)+20, 150, 150); 
			   cardsAcft.add(l, new Integer(k));
			 }  
	      cardHolder.add(cardsAcft, new Integer(0));
		 } 
	  }  
	  else if(art.size() > 0)
	  {
	    cardsArt.setBounds(20, 00, 160, 360);
		    for(int k = 0; k < art.size(); k++)
			 {
		      GameJLabel label = new GameJLabel();    
			   Card temp = (Card)art.get(k);
	         label = temp.getLabel();
			   GameJLabel l = new GameJLabel(temp.getFrontIcon());
			   l.setBounds(00, 00, 150, 150);
			   temp.setLabel(l);
	
				l.setBounds(00, (k*20)+20, 150, 150); 
			   cardsArt.add(l, new Integer(k));
			 }  
	    cardHolder.add(cardsArt, new Integer(0));
       if(acft.size() > 0)      
       {
		   cardsAcft.setBounds(180, 00, 160, 360);
		    for(int k = 0; k < acft.size(); k++)
			 {
		      GameJLabel label = new GameJLabel();    
			   Card temp = (Card)acft.get(k);
	         label = temp.getLabel();
			   GameJLabel l = new GameJLabel(temp.getFrontIcon());
			   l.setBounds(00, 00, 150, 150);
			   temp.setLabel(l);
	
				l.setBounds(00, (k*20)+20, 150, 150); 
			   cardsAcft.add(l, new Integer(k));
			 }  
	      cardHolder.add(cardsAcft, new Integer(0));
		 }
	  }  
	  else if(acft.size() > 0)
     {    
		 cardsAcft.setBounds(20, 00, 160, 360);
		    for(int k = 0; k < acft.size(); k++)
			 {
		      GameJLabel label = new GameJLabel();    
			   Card temp = (Card)acft.get(k);
	         label = temp.getLabel();
			   GameJLabel l = new GameJLabel(temp.getFrontIcon());
			   l.setBounds(00, 00, 150, 150);
			   temp.setLabel(l);
	
				l.setBounds(00, (k*20)+20, 150, 150); 
			   cardsAcft.add(l, new Integer(k));
			 }  
	    cardHolder.add(cardsAcft, new Integer(0));
     }

	  cardHolder.setPreferredSize(new Dimension(600, 400));
	  cardHolder.setBounds(00, 260, 600, 400);
	  cardHolder.validate();
	  cardHolder.repaint();

	  playersArea.add(cardHolder, new Integer(9));  
	  playersArea.validate();
	  playersArea.repaint();
	}

      public void redrawUnitHand()
      {
         updateUnits();
      }
   
      public void upgradeInf()
      {
         for(int k = 0; k < unitHand.size(); k++)
         {
            Card temp = (Card)unitHand.get(k);  
            if(temp.getType().equals("infantry"))
            {
            //System.out.println("Infantry lvl: " +infLevel);
               if(infLevel == 2)
               {
               //System.out.println("LEVEL2" + temp);
                  temp.setValue(temp.getValue()+1);
               //System.out.println("LEVEL2VALUR" + temp.getValue());
                  if(temp.getValue() == 2)
                     temp.setIconFront(new GameRotatedIcon(getImage("data/infantry_1.png"), 270));
                  else if(temp.getValue() == 3)
                     temp.setIconFront(new GameRotatedIcon(getImage("data/infantry_2.png"), 270));
                  else if(temp.getValue() == 4)			 
                     temp.setIconFront(new GameRotatedIcon(getImage("data/infantry_3.png"), 270));
                  temp.setLabelIcon(temp.getFrontIcon());
                  playerUI.updateCombatHolder("infantry", new GameJLabel(getImage("data/infLevel_2_"+color+".png")), this);  
               }
               if(infLevel == 3)
               {
                  temp.setValue(temp.getValue()+2);
                  if(temp.getValue() == 3)
                     temp.setIconFront(new GameRotatedIcon(getImage("data/infantry_1.png"), 180));
                  else if(temp.getValue() == 4)
                     temp.setIconFront(new GameRotatedIcon(getImage("data/infantry_2.png"), 180));
                  else if(temp.getValue() == 5)			 
                     temp.setIconFront(new GameRotatedIcon(getImage("data/infantry_3.png"), 180));
                  temp.setLabelIcon(temp.getFrontIcon());
                  playerUI.updateCombatHolder("infantry", new GameJLabel(getImage("data/infLevel_3_"+color+".png")), this);  
               }
               if(infLevel == 4)
               {
                  temp.setValue(temp.getValue()+3);
                  if(temp.getValue() == 4)
                     temp.setIconFront(new GameRotatedIcon(getImage("data/infantry_1.png"), 90));
                  else if(temp.getValue() == 5)
                     temp.setIconFront(new GameRotatedIcon(getImage("data/infantry_2.png"), 90));
                  else if(temp.getValue() == 6)			 
                     temp.setIconFront(new GameRotatedIcon(getImage("data/infantry_3.png"), 90));
                  temp.setLabelIcon(temp.getFrontIcon());
                  playerUI.updateCombatHolder("infantry", new GameJLabel(getImage("data/infLevel_4_"+color+".png")), this);  
               }
               unitHand.removeElementAt(k);
               unitHand.add(k, temp);
            }
         }
         if(nation.equals("Germany"))
			{
				 addUnitToHand(playerUI.getInfCard(this));
		
		       JOptionPane free = new JOptionPane();    
				 Vector tempc = new Vector(0, 1);
			    if(playerUI.numIncense > 0)
				   tempc.add(getImage("data/Incense.png"));  
			    if(playerUI.numIron > 0)
				   tempc.add(getImage("data/Iron.png"));  
			    if(playerUI.numSilk > 0)
				   tempc.add(getImage("data/Silk.png"));  
			    if(playerUI.numWheat > 0)
				   tempc.add(getImage("data/Wheat.png"));  
		
             if(tempc.size() > 0)
		       {
					 Object[] options = tempc.toArray();
				    int choice = -1;
			       while(choice < 0)
			       {
						choice = free.showOptionDialog(playerBoard.playerBrd2, "Pick a free market resource", "Germany",
			                           JOptionPane.CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
			                           null, options, null);
				    }
			       switch(choice)
					 {
					   case(0):
	                 if(options[0].toString().equals("file:/C:/Code/Civ/classes/data/Incense.png") || options[0].toString().equals("jar:file:/C:/Code/Civ/classes/Civ.jar!/data/Incense.png"))					  
						    addmarketResource("Incense");
	                 else if(options[0].toString().equals("file:/C:/Code/Civ/classes/data/Iron.png") || options[0].toString().equals("jar:file:/C:/Code/Civ/classes/Civ.jar!/data/Iron.png"))					  
						    addmarketResource("Iron");
	                 else if(options[0].toString().equals("file:/C:/Code/Civ/classes/data/Silk.png") || options[0].toString().equals("jar:file:/C:/Code/Civ/classes/Civ.jar!/data/Silk.png"))					  
						    addmarketResource("Silk");
	                 else if(options[0].toString().equals("file:/C:/Code/Civ/classes/data/Wheat.png") || options[0].toString().equals("jar:file:/C:/Code/Civ/classes/Civ.jar!/data/Wheat.png"))					  
						    addmarketResource("Wheat");
						  break;
					   case(1):
	                 if(options[1].toString().equals("file:/C:/Code/Civ/classes/data/Incense.png") || options[1].toString().equals("jar:file:/C:/Code/Civ/classes/Civ.jar!/data/Incense.png"))					  
						    addmarketResource("Incense");
	                 else if(options[1].toString().equals("file:/C:/Code/Civ/classes/data/Iron.png") || options[1].toString().equals("jar:file:/C:/Code/Civ/classes/Civ.jar!/data/Iron.png"))					  
						    addmarketResource("Iron");
	                 else if(options[1].toString().equals("file:/C:/Code/Civ/classes/data/Silk.png") || options[1].toString().equals("jar:file:/C:/Code/Civ/classes/Civ.jar!/data/Silk.png"))					  
						    addmarketResource("Silk");
	                 else if(options[1].toString().equals("file:/C:/Code/Civ/classes/data/Wheat.png") || options[1].toString().equals("jar:file:/C:/Code/Civ/classes/Civ.jar!/data/Wheat.png"))					  
						    addmarketResource("Wheat");
						  break;
					   case(2):
	                 if(options[2].toString().equals("file:/C:/Code/Civ/classes/data/Incense.png") || options[2].toString().equals("jar:file:/C:/Code/Civ/classes/Civ.jar!/data/Incense.png"))					  
						    addmarketResource("Incense");
	                 else if(options[2].toString().equals("file:/C:/Code/Civ/classes/data/Iron.png") || options[2].toString().equals("jar:file:/C:/Code/Civ/classes/Civ.jar!/data/Iron.png"))					  
						    addmarketResource("Iron");
	                 else if(options[2].toString().equals("file:/C:/Code/Civ/classes/data/Silk.png") || options[2].toString().equals("jar:file:/C:/Code/Civ/classes/Civ.jar!/data/Silk.png"))					  
						    addmarketResource("Silk");
	                 else if(options[2].toString().equals("file:/C:/Code/Civ/classes/data/Wheat.png") || options[2].toString().equals("jar:file:/C:/Code/Civ/classes/Civ.jar!/data/Wheat.png"))					  
						    addmarketResource("Wheat");
						  break;
					   case(3):
	                 if(options[3].toString().equals("file:/C:/Code/Civ/classes/data/Incense.png") || options[3].toString().equals("jar:file:/C:/Code/Civ/classes/Civ.jar!/data/Incense.png"))					  
						    addmarketResource("Incense");
	                 else if(options[3].toString().equals("file:/C:/Code/Civ/classes/data/Iron.png") || options[3].toString().equals("jar:file:/C:/Code/Civ/classes/Civ.jar!/data/Iron.png"))					  
						    addmarketResource("Iron");
	                 else if(options[3].toString().equals("file:/C:/Code/Civ/classes/data/Silk.png") || options[3].toString().equals("jar:file:/C:/Code/Civ/classes/Civ.jar!/data/Silk.png"))					  
						    addmarketResource("Silk");
	                 else if(options[3].toString().equals("file:/C:/Code/Civ/classes/data/Wheat.png") || options[3].toString().equals("jar:file:/C:/Code/Civ/classes/Civ.jar!/data/Wheat.png"))					  
						    addmarketResource("Wheat");
						  break;
					 }     
            }
		  }
		  updateUnits();
      }
   
      public void upgradeMnt()
      {
         for(int k = 0; k < unitHand.size(); k++)
         {
            Card temp = (Card)unitHand.get(k);  
            if(temp.getType().equals("mounted"))
            {
            //System.out.println("Mntantry lvl: " +mntLevel);
               if(mntLevel == 2)
               {
               //System.out.println("LEVEL2" + temp);
                  temp.setValue(temp.getValue()+1);
               //System.out.println("LEVEL2VALUR" + temp.getValue());
                  if(temp.getValue() == 2)
                     temp.setIconFront(new GameRotatedIcon(getImage("data/mounted_1.png"), 270));
                  else if(temp.getValue() == 3)
                     temp.setIconFront(new GameRotatedIcon(getImage("data/mounted_2.png"), 270));
                  else if(temp.getValue() == 4)			 
                     temp.setIconFront(new GameRotatedIcon(getImage("data/mounted_3.png"), 270));
                  temp.setLabelIcon(temp.getFrontIcon());
                  playerUI.updateCombatHolder("mounted", new GameJLabel(getImage("data/mntLevel_2_"+color+".png")), this);  
               }
               if(mntLevel == 3)
               {
                  temp.setValue(temp.getValue()+2);
                  if(temp.getValue() == 3)
                     temp.setIconFront(new GameRotatedIcon(getImage("data/mounted_1.png"), 180));
                  else if(temp.getValue() == 4)
                     temp.setIconFront(new GameRotatedIcon(getImage("data/mounted_2.png"), 180));
                  else if(temp.getValue() == 5)			 
                     temp.setIconFront(new GameRotatedIcon(getImage("data/mounted_3.png"), 180));
                  temp.setLabelIcon(temp.getFrontIcon());
                  playerUI.updateCombatHolder("mounted", new GameJLabel(getImage("data/mntLevel_3_"+color+".png")), this);  
               }
               if(mntLevel == 4)
               {
                  temp.setValue(temp.getValue()+3);
                  if(temp.getValue() == 4)
                     temp.setIconFront(new GameRotatedIcon(getImage("data/mounted_1.png"), 90));
                  else if(temp.getValue() == 5)
                     temp.setIconFront(new GameRotatedIcon(getImage("data/mounted_2.png"), 90));
                  else if(temp.getValue() == 6)			 
                     temp.setIconFront(new GameRotatedIcon(getImage("data/mounted_3.png"), 90));
                  temp.setLabelIcon(temp.getFrontIcon());
                  playerUI.updateCombatHolder("mounted", new GameJLabel(getImage("data/mntLevel_4_"+color+".png")), this);  
               }
               unitHand.removeElementAt(k);
               unitHand.add(k, temp);
            }
         }
         if(nation.equals("Germany"))
			{
			  addUnitToHand(playerUI.getMntCard(this));
		       JOptionPane free = new JOptionPane();    
				 Vector tempc = new Vector(0, 1);
			    if(playerUI.numIncense > 0)
				   tempc.add(getImage("data/Incense.png"));  
			    if(playerUI.numIron > 0)
				   tempc.add(getImage("data/Iron.png"));  
			    if(playerUI.numSilk > 0)
				   tempc.add(getImage("data/Silk.png"));  
			    if(playerUI.numWheat > 0)
				   tempc.add(getImage("data/Wheat.png"));  
		
             if(tempc.size() > 0)
		       {
					 Object[] options = tempc.toArray();
				    int choice = -1;
			       while(choice < 0)
			       {
						choice = free.showOptionDialog(playerBoard.playerBrd2, "Pick a free market resource", "Germany",
			                           JOptionPane.CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
			                           null, options, null);
				    }
			       switch(choice)
					 {
					   case(0):
	                 if(options[0].toString().equals("file:/C:/Code/Civ/classes/data/Incense.png") || options[0].toString().equals("jar:file:/C:/Code/Civ/classes/Civ.jar!/data/Incense.png"))					  
						    addmarketResource("Incense");
	                 else if(options[0].toString().equals("file:/C:/Code/Civ/classes/data/Iron.png") || options[0].toString().equals("jar:file:/C:/Code/Civ/classes/Civ.jar!/data/Iron.png"))					  
						    addmarketResource("Iron");
	                 else if(options[0].toString().equals("file:/C:/Code/Civ/classes/data/Silk.png") || options[0].toString().equals("jar:file:/C:/Code/Civ/classes/Civ.jar!/data/Silk.png"))					  
						    addmarketResource("Silk");
	                 else if(options[0].toString().equals("file:/C:/Code/Civ/classes/data/Wheat.png") || options[0].toString().equals("jar:file:/C:/Code/Civ/classes/Civ.jar!/data/Wheat.png"))					  
						    addmarketResource("Wheat");
						  break;
					   case(1):
	                 if(options[1].toString().equals("file:/C:/Code/Civ/classes/data/Incense.png") || options[1].toString().equals("jar:file:/C:/Code/Civ/classes/Civ.jar!/data/Incense.png"))					  
						    addmarketResource("Incense");
	                 else if(options[1].toString().equals("file:/C:/Code/Civ/classes/data/Iron.png") || options[1].toString().equals("jar:file:/C:/Code/Civ/classes/Civ.jar!/data/Iron.png"))					  
						    addmarketResource("Iron");
	                 else if(options[1].toString().equals("file:/C:/Code/Civ/classes/data/Silk.png") || options[1].toString().equals("jar:file:/C:/Code/Civ/classes/Civ.jar!/data/Silk.png"))					  
						    addmarketResource("Silk");
	                 else if(options[1].toString().equals("file:/C:/Code/Civ/classes/data/Wheat.png") || options[1].toString().equals("jar:file:/C:/Code/Civ/classes/Civ.jar!/data/Wheat.png"))					  
						    addmarketResource("Wheat");
						  break;
					   case(2):
	                 if(options[2].toString().equals("file:/C:/Code/Civ/classes/data/Incense.png") || options[2].toString().equals("jar:file:/C:/Code/Civ/classes/Civ.jar!/data/Incense.png"))					  
						    addmarketResource("Incense");
	                 else if(options[2].toString().equals("file:/C:/Code/Civ/classes/data/Iron.png") || options[2].toString().equals("jar:file:/C:/Code/Civ/classes/Civ.jar!/data/Iron.png"))					  
						    addmarketResource("Iron");
	                 else if(options[2].toString().equals("file:/C:/Code/Civ/classes/data/Silk.png") || options[2].toString().equals("jar:file:/C:/Code/Civ/classes/Civ.jar!/data/Silk.png"))					  
						    addmarketResource("Silk");
	                 else if(options[2].toString().equals("file:/C:/Code/Civ/classes/data/Wheat.png") || options[2].toString().equals("jar:file:/C:/Code/Civ/classes/Civ.jar!/data/Wheat.png"))					  
						    addmarketResource("Wheat");
						  break;
					   case(3):
	                 if(options[3].toString().equals("file:/C:/Code/Civ/classes/data/Incense.png") || options[3].toString().equals("jar:file:/C:/Code/Civ/classes/Civ.jar!/data/Incense.png"))					  
						    addmarketResource("Incense");
	                 else if(options[3].toString().equals("file:/C:/Code/Civ/classes/data/Iron.png") || options[3].toString().equals("jar:file:/C:/Code/Civ/classes/Civ.jar!/data/Iron.png"))					  
						    addmarketResource("Iron");
	                 else if(options[3].toString().equals("file:/C:/Code/Civ/classes/data/Silk.png") || options[3].toString().equals("jar:file:/C:/Code/Civ/classes/Civ.jar!/data/Silk.png"))					  
						    addmarketResource("Silk");
	                 else if(options[3].toString().equals("file:/C:/Code/Civ/classes/data/Wheat.png") || options[3].toString().equals("jar:file:/C:/Code/Civ/classes/Civ.jar!/data/Wheat.png"))					  
						    addmarketResource("Wheat");
						  break;
					 }     
            }
		  }
		  updateUnits();
      }
   
      public void upgradeArt()
      {
         for(int k = 0; k < unitHand.size(); k++)
         {
            Card temp = (Card)unitHand.get(k);  
            if(temp.getType().equals("artillery"))
            {
            //System.out.println("Artantry lvl: " +artLevel);
               if(artLevel == 2)
               {
               //System.out.println("LEVEL2" + temp);
                  temp.setValue(temp.getValue()+1);
               //System.out.println("LEVEL2VALUR" + temp.getValue());
                  if(temp.getValue() == 2)
                     temp.setIconFront(new GameRotatedIcon(getImage("data/artillery_1.png"), 270));
                  else if(temp.getValue() == 3)
                     temp.setIconFront(new GameRotatedIcon(getImage("data/artillery_2.png"), 270));
                  else if(temp.getValue() == 4)			 
                     temp.setIconFront(new GameRotatedIcon(getImage("data/artillery_3.png"), 270));
                  temp.setLabelIcon(temp.getFrontIcon());
                  playerUI.updateCombatHolder("artillery", new GameJLabel(getImage("data/artLevel_2_"+color+".png")), this);  
               }
               if(artLevel == 3)
               {
                  temp.setValue(temp.getValue()+2);
                  if(temp.getValue() == 3)
                     temp.setIconFront(new GameRotatedIcon(getImage("data/artillery_1.png"), 180));
                  else if(temp.getValue() == 4)
                     temp.setIconFront(new GameRotatedIcon(getImage("data/artillery_2.png"), 180));
                  else if(temp.getValue() == 5)			 
                     temp.setIconFront(new GameRotatedIcon(getImage("data/artillery_3.png"), 180));
                  temp.setLabelIcon(temp.getFrontIcon());
                  playerUI.updateCombatHolder("artillery", new GameJLabel(getImage("data/artLevel_3_"+color+".png")), this);  
               }
               if(artLevel == 4)
               {
                  temp.setValue(temp.getValue()+3);
                  if(temp.getValue() == 4)
                     temp.setIconFront(new GameRotatedIcon(getImage("data/artillery_1.png"), 90));
                  else if(temp.getValue() == 5)
                     temp.setIconFront(new GameRotatedIcon(getImage("data/artillery_2.png"), 90));
                  else if(temp.getValue() == 6)			 
                     temp.setIconFront(new GameRotatedIcon(getImage("data/artillery_3.png"), 90));
                  temp.setLabelIcon(temp.getFrontIcon());
                  playerUI.updateCombatHolder("artillery", new GameJLabel(getImage("data/artLevel_4_"+color+".png")), this);  
               }
               unitHand.removeElementAt(k);
               unitHand.add(k, temp);
            }
         }
         if(nation.equals("Germany"))
			{
			  addUnitToHand(playerUI.getArtCard(this));
		       JOptionPane free = new JOptionPane();    
				 Vector tempc = new Vector(0, 1);
			    if(playerUI.numIncense > 0)
				   tempc.add(getImage("data/Incense.png"));  
			    if(playerUI.numIron > 0)
				   tempc.add(getImage("data/Iron.png"));  
			    if(playerUI.numSilk > 0)
				   tempc.add(getImage("data/Silk.png"));  
			    if(playerUI.numWheat > 0)
				   tempc.add(getImage("data/Wheat.png"));  
		
             if(tempc.size() > 0)
		       {
					 Object[] options = tempc.toArray();
				    int choice = -1;
			       while(choice < 0)
			       {
						choice = free.showOptionDialog(playerBoard.playerBrd2, "Pick a free market resource", "Germany",
			                           JOptionPane.CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
			                           null, options, null);
				    }
			       switch(choice)
					 {
					   case(0):
	                 if(options[0].toString().equals("file:/C:/Code/Civ/classes/data/Incense.png") || options[0].toString().equals("jar:file:/C:/Code/Civ/classes/Civ.jar!/data/Incense.png"))					  
						    addmarketResource("Incense");
	                 else if(options[0].toString().equals("file:/C:/Code/Civ/classes/data/Iron.png") || options[0].toString().equals("jar:file:/C:/Code/Civ/classes/Civ.jar!/data/Iron.png"))					  
						    addmarketResource("Iron");
	                 else if(options[0].toString().equals("file:/C:/Code/Civ/classes/data/Silk.png") || options[0].toString().equals("jar:file:/C:/Code/Civ/classes/Civ.jar!/data/Silk.png"))					  
						    addmarketResource("Silk");
	                 else if(options[0].toString().equals("file:/C:/Code/Civ/classes/data/Wheat.png") || options[0].toString().equals("jar:file:/C:/Code/Civ/classes/Civ.jar!/data/Wheat.png"))					  
						    addmarketResource("Wheat");
						  break;
					   case(1):
	                 if(options[1].toString().equals("file:/C:/Code/Civ/classes/data/Incense.png") || options[1].toString().equals("jar:file:/C:/Code/Civ/classes/Civ.jar!/data/Incense.png"))					  
						    addmarketResource("Incense");
	                 else if(options[1].toString().equals("file:/C:/Code/Civ/classes/data/Iron.png") || options[1].toString().equals("jar:file:/C:/Code/Civ/classes/Civ.jar!/data/Iron.png"))					  
						    addmarketResource("Iron");
	                 else if(options[1].toString().equals("file:/C:/Code/Civ/classes/data/Silk.png") || options[1].toString().equals("jar:file:/C:/Code/Civ/classes/Civ.jar!/data/Silk.png"))					  
						    addmarketResource("Silk");
	                 else if(options[1].toString().equals("file:/C:/Code/Civ/classes/data/Wheat.png") || options[1].toString().equals("jar:file:/C:/Code/Civ/classes/Civ.jar!/data/Wheat.png"))					  
						    addmarketResource("Wheat");
						  break;
					   case(2):
	                 if(options[2].toString().equals("file:/C:/Code/Civ/classes/data/Incense.png") || options[2].toString().equals("jar:file:/C:/Code/Civ/classes/Civ.jar!/data/Incense.png"))					  
						    addmarketResource("Incense");
	                 else if(options[2].toString().equals("file:/C:/Code/Civ/classes/data/Iron.png") || options[2].toString().equals("jar:file:/C:/Code/Civ/classes/Civ.jar!/data/Iron.png"))					  
						    addmarketResource("Iron");
	                 else if(options[2].toString().equals("file:/C:/Code/Civ/classes/data/Silk.png") || options[2].toString().equals("jar:file:/C:/Code/Civ/classes/Civ.jar!/data/Silk.png"))					  
						    addmarketResource("Silk");
	                 else if(options[2].toString().equals("file:/C:/Code/Civ/classes/data/Wheat.png") || options[2].toString().equals("jar:file:/C:/Code/Civ/classes/Civ.jar!/data/Wheat.png"))					  
						    addmarketResource("Wheat");
						  break;
					   case(3):
	                 if(options[3].toString().equals("file:/C:/Code/Civ/classes/data/Incense.png") || options[3].toString().equals("jar:file:/C:/Code/Civ/classes/Civ.jar!/data/Incense.png"))					  
						    addmarketResource("Incense");
	                 else if(options[3].toString().equals("file:/C:/Code/Civ/classes/data/Iron.png") || options[3].toString().equals("jar:file:/C:/Code/Civ/classes/Civ.jar!/data/Iron.png"))					  
						    addmarketResource("Iron");
	                 else if(options[3].toString().equals("file:/C:/Code/Civ/classes/data/Silk.png") || options[3].toString().equals("jar:file:/C:/Code/Civ/classes/Civ.jar!/data/Silk.png"))					  
						    addmarketResource("Silk");
	                 else if(options[3].toString().equals("file:/C:/Code/Civ/classes/data/Wheat.png") || options[3].toString().equals("jar:file:/C:/Code/Civ/classes/Civ.jar!/data/Wheat.png"))					  
						    addmarketResource("Wheat");
						  break;
					 }     
            }
		  }
		  updateUnits();
      }
   
      public void upgradeAcft()
      {
         playerUI.updateCombatHolder("aircraft", new GameJLabel(getImage("data/acft_front_"+color+".png")), this);  
         if(nation.equals("Germany"))
			{
			  addUnitToHand(playerUI.getAcftCard());
		       JOptionPane free = new JOptionPane();    
				 Vector tempc = new Vector(0, 1);
			    if(playerUI.numIncense > 0)
				   tempc.add(getImage("data/Incense.png"));  
			    if(playerUI.numIron > 0)
				   tempc.add(getImage("data/Iron.png"));  
			    if(playerUI.numSilk > 0)
				   tempc.add(getImage("data/Silk.png"));  
			    if(playerUI.numWheat > 0)
				   tempc.add(getImage("data/Wheat.png"));  
		
             if(tempc.size() > 0)
		       {
					 Object[] options = tempc.toArray();
				    int choice = -1;
			       while(choice < 0)
			       {
						choice = free.showOptionDialog(playerBoard.playerBrd2, "Pick a free market resource", "Germany",
			                           JOptionPane.CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
			                           null, options, null);
				    }
			       switch(choice)
					 {
					   case(0):
	                 if(options[0].toString().equals("file:/C:/Code/Civ/classes/data/Incense.png") || options[0].toString().equals("jar:file:/C:/Code/Civ/classes/Civ.jar!/data/Incense.png"))					  
						    addmarketResource("Incense");
	                 else if(options[0].toString().equals("file:/C:/Code/Civ/classes/data/Iron.png") || options[0].toString().equals("jar:file:/C:/Code/Civ/classes/Civ.jar!/data/Iron.png"))					  
						    addmarketResource("Iron");
	                 else if(options[0].toString().equals("file:/C:/Code/Civ/classes/data/Silk.png") || options[0].toString().equals("jar:file:/C:/Code/Civ/classes/Civ.jar!/data/Silk.png"))					  
						    addmarketResource("Silk");
	                 else if(options[0].toString().equals("file:/C:/Code/Civ/classes/data/Wheat.png") || options[0].toString().equals("jar:file:/C:/Code/Civ/classes/Civ.jar!/data/Wheat.png"))					  
						    addmarketResource("Wheat");
						  break;
					   case(1):
	                 if(options[1].toString().equals("file:/C:/Code/Civ/classes/data/Incense.png") || options[1].toString().equals("jar:file:/C:/Code/Civ/classes/Civ.jar!/data/Incense.png"))					  
						    addmarketResource("Incense");
	                 else if(options[1].toString().equals("file:/C:/Code/Civ/classes/data/Iron.png") || options[1].toString().equals("jar:file:/C:/Code/Civ/classes/Civ.jar!/data/Iron.png"))					  
						    addmarketResource("Iron");
	                 else if(options[1].toString().equals("file:/C:/Code/Civ/classes/data/Silk.png") || options[1].toString().equals("jar:file:/C:/Code/Civ/classes/Civ.jar!/data/Silk.png"))					  
						    addmarketResource("Silk");
	                 else if(options[1].toString().equals("file:/C:/Code/Civ/classes/data/Wheat.png") || options[1].toString().equals("jar:file:/C:/Code/Civ/classes/Civ.jar!/data/Wheat.png"))					  
						    addmarketResource("Wheat");
						  break;
					   case(2):
	                 if(options[2].toString().equals("file:/C:/Code/Civ/classes/data/Incense.png") || options[2].toString().equals("jar:file:/C:/Code/Civ/classes/Civ.jar!/data/Incense.png"))					  
						    addmarketResource("Incense");
	                 else if(options[2].toString().equals("file:/C:/Code/Civ/classes/data/Iron.png") || options[2].toString().equals("jar:file:/C:/Code/Civ/classes/Civ.jar!/data/Iron.png"))					  
						    addmarketResource("Iron");
	                 else if(options[2].toString().equals("file:/C:/Code/Civ/classes/data/Silk.png") || options[2].toString().equals("jar:file:/C:/Code/Civ/classes/Civ.jar!/data/Silk.png"))					  
						    addmarketResource("Silk");
	                 else if(options[2].toString().equals("file:/C:/Code/Civ/classes/data/Wheat.png") || options[2].toString().equals("jar:file:/C:/Code/Civ/classes/Civ.jar!/data/Wheat.png"))					  
						    addmarketResource("Wheat");
						  break;
					   case(3):
	                 if(options[3].toString().equals("file:/C:/Code/Civ/classes/data/Incense.png") || options[3].toString().equals("jar:file:/C:/Code/Civ/classes/Civ.jar!/data/Incense.png"))					  
						    addmarketResource("Incense");
	                 else if(options[3].toString().equals("file:/C:/Code/Civ/classes/data/Iron.png") || options[3].toString().equals("jar:file:/C:/Code/Civ/classes/Civ.jar!/data/Iron.png"))					  
						    addmarketResource("Iron");
	                 else if(options[3].toString().equals("file:/C:/Code/Civ/classes/data/Silk.png") || options[3].toString().equals("jar:file:/C:/Code/Civ/classes/Civ.jar!/data/Silk.png"))					  
						    addmarketResource("Silk");
	                 else if(options[3].toString().equals("file:/C:/Code/Civ/classes/data/Wheat.png") || options[3].toString().equals("jar:file:/C:/Code/Civ/classes/Civ.jar!/data/Wheat.png"))					  
						    addmarketResource("Wheat");
						  break;
					 }     
            }
		  }
		  updateUnits();
      }
   
      public int getInfLevel()
      {
         return infLevel;
      }
   
      public int getMntLevel()
      {
         return mntLevel;
      }
   
      public int getArtLevel()
      {
         return artLevel;
      }
   
      public int getAcftLevel()
      {
         return acftLevel;
      }
   
      public int getMoveLimit()
      {
         return moveLimit;
      }
   
      public boolean getCodeofLaw()
      {
         return codeOfLaw;
      }
   
      public void researchCodeOfLaw()
      {
         codeOfLaw = true;
      }
   
      public Color getPlayerColor()
      {
         return playerColor;
      }
   
      public void setFirstTurn(boolean b)
      {
         firstTurn = b;
      }
   
      public boolean getFirstTurn()
      {
         return firstTurn;
      }
   
      public int getCombatBonus()
		{
		  return combatBonus;
		}  
	  
	   public int getCityCombatBonus(Object c)
		{
        City temp = (City) c;
		  int b = temp.getBonus();
		  return b+combatBonus;
		}
		  
		public Vector getUnitHand()
      {
         return unitHand;
      }
   
      public int getCombatHandLimit(Army a)
      {
         if(a != null)
         {
            int limit = 0, govLimit = 0;   
            if(gov == 6)
               govLimit++;
            int techlimit = 0;
            for(int k = 0; k < armies.size(); k++)
            {
               Army check = (Army)armies.get(k);  
               if((check.getX() == a.getX()) && (check.getY() == a.getY()))
                  limit++;
            }  
            if(tech.get(32) != null)
               techlimit += (gold/5);
//            System.out.println("HAND LIMIT ARMY "+((limit-1)*2)+techlimit+govLimit+3);
            return ((limit-1)*2)+techlimit+govLimit+3;
         }
         else if(gov == 6)
            return 4;
         else
            return 3;
      }
   
      public int getCityCombatHandLimit(Army a)
      {
         return 0;
      }
   
   
      public void addUnitToHand(Object obj)
      {
         unitHand.add(obj);
         if(!nation.equals("Village"))       
			  redrawUnitHand();
      }
   
      public void removeUnitHand(Object obj)
      {
         unitHand.remove(obj);
         redrawUnitHand();
      }
   
      public void postCombatHand(Vector v)
      {
         unitHand = new Vector(0, 1);
         for(int k = 0; k < v.size(); k++)
         { 
            Card temp = (Card)v.get(k);
            unitHand.add(k, temp);
         }
         redrawUnitHand();
      }
   
      public GameJLabel getGoldDial()
      {
         return goDial;
      }
   
      public GameJLabel getTradeDial()
      {
         return trDial;
      }
   
      public void addGold(int num)
      {
         gold = gold + num;
      }
   
      public boolean addSettler(Settler mov)
      {
         if(settlers.size() < MAX_SETTLER)  
         {
            if(color.equals("Yellow"))
				  mov.setColor(new Color(10, 10, 10));        
            settlers.add(mov);
            resetUnitMoves();
				return true;
         }
         else
            return false;
      }
   
      public boolean canBuildSettler()
		{
         if(settlers.size() < MAX_SETTLER)  
           return true;
			return false;  
		}
		     
      public boolean canBuildArmy()
		{
         if(armies.size() < MAX_ARMY)  
           return true;
			return false;  
		}

		public void removeSettler(Settler mov)
      {
         settlers.remove(mov);
         for(int k = 0; k < settlers.size(); k++)
			{
			  Settler temp = (Settler)settlers.get(k);
			  temp.setNumText(Integer.toString(k+1));
			}    
		}
   
      public Vector getSettlerVector()
      {
         return settlers;
      }
   
      public void resetUnitMoves()
      {
         if(settlers.size() > 0)  
         {
            for(int k = 0; k < settlers.size(); k++)
            {
               Settler s = (Settler)settlers.get(k);
               s.resetMoves(this);
            } 
         }  
         if(armies.size() > 0)
         {
            for(int k = 0; k < armies.size(); k++)
            {
               Army a = (Army)armies.get(k);
               a.resetMoves(this);
            } 
         }
      }
   
      public void addArmy(Army mov)
      {
         if(nation.equals("Russia"))
			{
			  if(armies.size() < (MAX_ARMY+1))  
           {
              if(color.equals("Yellow"))
				    mov.setColor(new Color(10, 10, 10));        
				  if(!whiteArmy && armies.size() > 0)
				  {
				    mov.setRussiaArmyColor("White");
				    whiteArmy = true;//white Army is alive        
				  }
				  armies.add(mov);
              resetUnitMoves();    
           }
			}      
			else if(armies.size() < MAX_ARMY)  
         {
            if(color.equals("Yellow"))
				  mov.setColor(new Color(10, 10, 10));        
            armies.add(mov);
            resetUnitMoves();    
         }
      }
   
      public void removeArmy(Army mov)
      {
         armies.remove(mov);
         if(nation.equals("Russia"))
			{
			  if(mov.getArmyColor().equals("White"))
			  {
			    whiteArmy = false;//white Army is dead
			  }
			}      
			for(int k = 0; k < armies.size(); k++)
			{
			  Army temp = (Army)armies.get(k);
			  temp.setNumText(Integer.toString(k+1));
			}    
      }
   
      public Vector getArmyVector()
      {
         return armies;
      }
   
      public boolean addCity(int x, int y, boolean cap)
      {
         if(cities.size() < MAX_CITIES)  
         {
            cities.add(new City(x, y, cap, this));

//            Player[] temp = CivGUI.getPlayerList();
//            for(int k = 0; k < temp.length; k++){        
//				   temp[k].playerUI.lb.setOwner(x, y, this);
//		         temp[k].playerUI.lb.addPeice(2, x, y);	  
//				}    
//				playerUI.lb.setOwner(x, y, this);
            if(nation.equals("Rome"))
              playerUI.advanceCultureTrack(1, 0, "Rome");//int num, int player, String name)			
            return true;
         }
         else
            return false;
      }
   
      public void addTrade(int num)
      {
         if((trade + num) <= MAX_TRADE)  
            trade = trade + num;
         else
            trade = MAX_TRADE;
      }
   
      public void addCulture(int num)
      {
         culture = culture + num;
         playerBoard.showCulture.setText(Integer.toString(culture));
      }
   
      public int getTechLvl1()
      {
         return numLvl1;
      }
   
      public int getTechLvl2()
      {
         return numLvl2;
      }
   
      public int getTechLvl3()
      {
         return numLvl3;
      }
   
      public int getTechLvl4()
      {
         return numLvl4;
      }
   
      public void addTech(int num)
      {
         tech.removeElementAt(num);
         tech.add(num, new Tech(num, this));
         Tech t = (Tech)tech.get(num);    
         playerBoard.addTech(t);      

         Image img = t.icon.getImage();
         BufferedImage bi = new BufferedImage(55, 36, BufferedImage.TYPE_INT_ARGB);
         bi.getGraphics().drawImage(img, 0, 0, 55, 36, null);
         GameImageIcon icon = new GameImageIcon(bi);
      
         t.label.setIcon(icon);
         if(num < 10)
         {  
            t.label.setBounds((55*numLvl1), 150, icon.getIconWidth(), icon.getIconHeight());
            numLvl1++;
         }
         else if(num >= 10 && num < 20)
         {
            if(numLvl1 == 0 && nation.equals("Egypt"))  
            {
               t.label.setBounds((55*numLvl1), 150, icon.getIconWidth(), icon.getIconHeight());
               numLvl1++;
            }
            else
            {
               t.label.setBounds((55*(numLvl2)+23), 110, icon.getIconWidth(), icon.getIconHeight());
               numLvl2++;
            }
         }
         else if(num >=20 && num < 29)
         {
            if(numLvl1 == 0 && nation.equals("Russia"))  
            {
               t.label.setBounds((55*numLvl1), 150, icon.getIconWidth(), icon.getIconHeight());
               numLvl1++;
            }
            else
            {
               t.label.setBounds((55*(numLvl3)+46), 70, icon.getIconWidth(), icon.getIconHeight());
               numLvl3++;
            }
         }
         else if(num >= 29 && num < 36)
         {
            t.label.setBounds((55*(numLvl4)+69), 30, icon.getIconWidth(), icon.getIconHeight());
            numLvl4++;
         }
         else
         {t.label.setBounds(130, 00, icon.getIconWidth(), icon.getIconHeight());}
      
         playerBoard.techPane.add(t.label, new Integer(1));
         playerBoard.techPane.repaint();
      }
   
      public void setStacklimit(int num)
      {
         stackLimit = num;
      }
   
      public void spendGold(int num)
      {
         gold = gold - num;
     }
   
      public void spendTrade(int num)
      {
         if((trade - num) < 0)  
            trade = (0 + gold + cityGold);
         else
            trade = trade - num;
         TradeGUI.startTrade(this, playerUI.mainBoard, true);  
      }
   
      public void convertTrade(int num)
      {
         if((trade - num) < 0)  
            trade = 0;
         else
            trade = trade - num;
         TradeGUI.startTrade(this, playerUI.mainBoard, true);  
      }
   
      public void spendCulture(int num)
      {
         culture = culture - num;
         playerBoard.showCulture.setText(Integer.toString(culture));
      }
   
      public void cityAddWalls(int index, LogicBoard lb)
      {
         City temp = (City)cities.get(index);
         temp.addWalls(playerUI.lb, this); 
      }
   
      public boolean cityHasWalls(int index)
      {
         City temp = (City)cities.get(index);
         return temp.hasWalls(); 
      }
   
      public void resetCityLinkVector()
      {
         City temp;
         for(int k = 0; k < cities.size(); k++)
         {
            temp = (City)cities.get(k);
            temp.resetLinkVector();
         }
      }
   
      public void setCitySettlerLink(int index, Settler s)
      { 
         City temp = (City)cities.get(index);
         temp.addLink(s); 
      }
   
      public Vector getCitySettlerLink(int index)
      {
         City temp = (City)cities.get(index);
         return temp.getLinkVector(); 
      }
   
      public GameJLabel getCityBuildingLabel(int index, int type)
      {
         City c = (City)cities.get(index);
//         System.out.println(c.getBuildingLabel(type));	 
         return c.getBuildingLabel(type);
      }
   
      public String getCityBuildingName(int index, int type)
      {
         City c = (City)cities.get(index);
         if(c.getBuildingName(type) != null)       
			{
//				System.out.println(c.getBuildingName(type));	 
	         return c.getBuildingName(type);
         }
			return null;
      }

      public void addBuildingtoCity(int index, int nm, int pos)
      {
         City c = (City)cities.get(index);
         c.addBuilding(nm, pos, this);
         if(nm == 20 || nm ==  21|| nm ==  22|| nm ==  23|| nm ==  24|| nm == 25 || nm == 26 || nm == 27 || nm == 28 || nm == 29 || nm == 30 || nm == 31)      
			  addWonderCard(nm);
         if(nation.equals("Rome"))
           playerUI.advanceCultureTrack(1, 0, "Rome");//int num, int player, String name)			
         if(!nation.equals(playerUI.aiChoice))//take out for thread fix 	   
			  TradeGUI.startTrade(this, playerBoard.playerBrd2, true);
      }
   
      public void addBuildingtoCity(int index, int nm, int pos, boolean egyptSpecial)
      {
         City c = (City)cities.get(index);
         c.addBuilding(nm, pos, this);
           addEgyptStartWonder(nm);      
      }

      public void removeCityBuilding(int index, int x, int y, int type)
		{
         City c = (City)cities.get(index);
         c.removeBuilding(x, y, getCityXpos(index), getCityYpos(index), type, this);
		   
//			for(int i = 0; i < 2; i++)//number of players
//			{
//			  if(this != CivGUI.players[i])
			    playerUI.lb.removeBuilding(x, y);
//			    CivGUI.players[i].playerUI.lb.removeBuilding(x, y);
//			}
         playerUI.addBuilding(type);
		}
		
      public void upgradeBuildings(int type)
		{
		  for(int k = 0; k < 2; k++)//number of players
		  {
		    if(this != playerUI.players[k])
			 {
		        for(int i = 0; i < cities.size(); i++)
				  {
				    City temp = (City)cities.get(i);
					 int x = temp.getXPos();
				    int y = temp.getYPos();
                playerUI.players[k].upgrade_lb_buildings(x, y, type, playerUI.players[k], temp);  				
				  }
			 }
		  }

        for(int k = 0; k < cities.size(); k++)
		  {
		    City temp = (City)cities.get(k);
			 temp.upgradeBuilding(type, this);		
		  }

		}

      public void upgrade_lb_buildings(int x, int y, int type, Player p, City cty)
		{
	        int index = cities.indexOf(cty);    
			  switch(type)
			  {
			    case 0:
	            int k, i;
	            int position = 0;
	         
		         for(k = x-1; k <= x+1; k++)
		         {  
		           i = y-1;
		           if(cty.buildings.get(position) != null)   
		           {
		              Building temp = (Building)cty.buildings.get(position);
		              if(temp.getName().equals("granary"))
                    {
                          p.playerUI.lb.getHolder(k, i).remove(p.playerUI.lb.getHolder(k, i).getIndexOf(p.playerUI.lb.getLabel(k, i))); 
								  temp = (Building)cty.buildings.get(position);
                          p.playerUI.lb.addBuilding(k, i, 9, new GameJLabel(getImage("data/aqueduct.png")), false, p, index);
						  }
	              }
                 position++;
	            }
	            for(k = x-1; k <= x+1; k++)
	            {  
	               i = y+1;
	               if(cty.buildings.get(position) != null)   
	               {
	                  Building temp = (Building)cty.buildings.get(position);
	                  if(temp.getName().equals("granary"))
                       {
                         p.playerUI.lb.getHolder(k, i).remove(p.playerUI.lb.getHolder(k, i).getIndexOf(p.playerUI.lb.getLabel(k, i))); 
							    temp = (Building)cty.buildings.get(position);
                         p.playerUI.lb.addBuilding(k, i, 9, new GameJLabel(getImage("data/aqueduct.png")), false, p, index);
							}
	               }
                  position++;
	            }
               if(cty.buildings.get(position) != null)   
	               {
	                  Building temp = (Building)cty.buildings.get(position);
	                  if(temp.getName().equals("granary"))
                     {
                         p.playerUI.lb.getHolder(x-1, y).remove(p.playerUI.lb.getHolder(x-1, y).getIndexOf(p.playerUI.lb.getLabel(x-1, y))); 
							    temp = (Building)cty.buildings.get(position);
                         p.playerUI.lb.addBuilding(x-1, y, 9, new GameJLabel(getImage("data/aqueduct.png")), false, p, index);
							}
	               }
               position++;
               if(cty.buildings.get(position) != null)   
               {
                  Building temp = (Building)cty.buildings.get(position);
                  if(temp.getName().equals("granary"))
                  {
                         p.playerUI.lb.getHolder(x+1, y).remove(p.playerUI.lb.getHolder(x+1, y).getIndexOf(p.playerUI.lb.getLabel(x+1, y))); 
							    temp = (Building)cty.buildings.get(position);
                         p.playerUI.lb.addBuilding(x+1, y, 9, new GameJLabel(getImage("data/aqueduct.png")), false, p, index);
						}
               }
				   break;
			    case 1:
	             position = 0;
	         
		         for(k = x-1; k <= x+1; k++)
		         {  
		           i = y-1;
		           if(cty.buildings.get(position) != null)   
		           {
		              Building temp = (Building)cty.buildings.get(position);
		              if(temp.getName().equals("library"))
                    {
                          p.playerUI.lb.getHolder(k, i).remove(p.playerUI.lb.getHolder(k, i).getIndexOf(p.playerUI.lb.getLabel(k, i))); 
								  temp = (Building)cty.buildings.get(position);
                          p.playerUI.lb.addBuilding(k, i, 10, new GameJLabel(getImage("data/university.png")), false, p, index);
						  }
	              }
                 position++;
	            }
	            for(k = x-1; k <= x+1; k++)
	            {  
	               i = y+1;
	               if(cty.buildings.get(position) != null)   
	               {
	                  Building temp = (Building)cty.buildings.get(position);
	                  if(temp.getName().equals("library"))
                       {
                         p.playerUI.lb.getHolder(k, i).remove(p.playerUI.lb.getHolder(k, i).getIndexOf(p.playerUI.lb.getLabel(k, i))); 
							    temp = (Building)cty.buildings.get(position);
                         p.playerUI.lb.addBuilding(k, i, 10, new GameJLabel(getImage("data/university.png")), false, p, index);
							}
	               }
                  position++;
	            }
               if(cty.buildings.get(position) != null)   
	               {
	                  Building temp = (Building)cty.buildings.get(position);
	                  if(temp.getName().equals("library"))
                     {
                         p.playerUI.lb.getHolder(x-1, y).remove(p.playerUI.lb.getHolder(x-1, y).getIndexOf(p.playerUI.lb.getLabel(x-1, y))); 
							    temp = (Building)cty.buildings.get(position);
                         p.playerUI.lb.addBuilding(x-1, y, 10, new GameJLabel(getImage("data/university.png")), false, p, index);
							}
	               }
               position++;
               if(cty.buildings.get(position) != null)   
               {
                  Building temp = (Building)cty.buildings.get(position);
                  if(temp.getName().equals("library"))
                  {
                         p.playerUI.lb.getHolder(x+1, y).remove(p.playerUI.lb.getHolder(x+1, y).getIndexOf(p.playerUI.lb.getLabel(x+1, y))); 
							    temp = (Building)cty.buildings.get(position);
                         p.playerUI.lb.addBuilding(x+1, y, 10, new GameJLabel(getImage("data/university.png")), false, p, index);
						}
               }
				   break;
			    case 2:
	             position = 0;
	         
		         for(k = x-1; k <= x+1; k++)
		         {  
		           i = y-1;
		           if(cty.buildings.get(position) != null)   
		           {
		              Building temp = (Building)cty.buildings.get(position);
		              if(temp.getName().equals("market"))
                    {
                          p.playerUI.lb.getHolder(k, i).remove(p.playerUI.lb.getHolder(k, i).getIndexOf(p.playerUI.lb.getLabel(k, i))); 
								  temp = (Building)cty.buildings.get(position);
                          p.playerUI.lb.addBuilding(k, i, 11, new GameJLabel(getImage("data/bank.png")), false, p, index);
						  }
	              }
                 position++;
	            }
	            for(k = x-1; k <= x+1; k++)
	            {  
	               i = y+1;
	               if(cty.buildings.get(position) != null)   
	               {
	                  Building temp = (Building)cty.buildings.get(position);
	                  if(temp.getName().equals("market"))
                       {
                         p.playerUI.lb.getHolder(k, i).remove(p.playerUI.lb.getHolder(k, i).getIndexOf(p.playerUI.lb.getLabel(k, i))); 
							    temp = (Building)cty.buildings.get(position);
                         p.playerUI.lb.addBuilding(k, i, 11, new GameJLabel(getImage("data/bank.png")), false, p, index);
							}
	               }
                  position++;
	            }
               if(cty.buildings.get(position) != null)   
	               {
	                  Building temp = (Building)cty.buildings.get(position);
	                  if(temp.getName().equals("market"))
                     {
                         p.playerUI.lb.getHolder(x-1, y).remove(p.playerUI.lb.getHolder(x-1, y).getIndexOf(p.playerUI.lb.getLabel(x-1, y))); 
							    temp = (Building)cty.buildings.get(position);
                         p.playerUI.lb.addBuilding(x-1, y, 11, new GameJLabel(getImage("data/bank.png")), false, p, index);
							}
	               }
               position++;
               if(cty.buildings.get(position) != null)   
               {
                  Building temp = (Building)cty.buildings.get(position);
                  if(temp.getName().equals("market"))
                  {
                         p.playerUI.lb.getHolder(x+1, y).remove(p.playerUI.lb.getHolder(x+1, y).getIndexOf(p.playerUI.lb.getLabel(x+1, y))); 
							    temp = (Building)cty.buildings.get(position);
                         p.playerUI.lb.addBuilding(x+1, y, 11, new GameJLabel(getImage("data/bank.png")), false, p, index);
						}
               }
				   break;
			    case 3:
	             position = 0;
	         
		         for(k = x-1; k <= x+1; k++)
		         {  
		           i = y-1;
		           if(cty.buildings.get(position) != null)   
		           {
		              Building temp = (Building)cty.buildings.get(position);
		              if(temp.getName().equals("barracks"))
                    {
                          p.playerUI.lb.getHolder(k, i).remove(p.playerUI.lb.getHolder(k, i).getIndexOf(p.playerUI.lb.getLabel(k, i))); 
								  temp = (Building)cty.buildings.get(position);
                          p.playerUI.lb.addBuilding(k, i, 8, new GameJLabel(getImage("data/academy.png")), false, p, index);
						  }
	              }
                 position++;
	            }
	            for(k = x-1; k <= x+1; k++)
	            {  
	               i = y+1;
	               if(cty.buildings.get(position) != null)   
	               {
	                  Building temp = (Building)cty.buildings.get(position);
	                  if(temp.getName().equals("barracks"))
                       {
                         p.playerUI.lb.getHolder(k, i).remove(p.playerUI.lb.getHolder(k, i).getIndexOf(p.playerUI.lb.getLabel(k, i))); 
							    temp = (Building)cty.buildings.get(position);
                         p.playerUI.lb.addBuilding(k, i, 8, new GameJLabel(getImage("data/academy.png")), false, p, index);
							}
	               }
                  position++;
	            }
               if(cty.buildings.get(position) != null)   
	               {
	                  Building temp = (Building)cty.buildings.get(position);
	                  if(temp.getName().equals("barracks"))
                     {
                         p.playerUI.lb.getHolder(x-1, y).remove(p.playerUI.lb.getHolder(x-1, y).getIndexOf(p.playerUI.lb.getLabel(x-1, y))); 
							    temp = (Building)cty.buildings.get(position);
                         p.playerUI.lb.addBuilding(x-1, y, 8, new GameJLabel(getImage("data/academy.png")), false, p, index);
							}
	               }
               position++;
               if(cty.buildings.get(position) != null)   
               {
                  Building temp = (Building)cty.buildings.get(position);
                  if(temp.getName().equals("barracks"))
                  {
                         p.playerUI.lb.getHolder(x+1, y).remove(p.playerUI.lb.getHolder(x+1, y).getIndexOf(p.playerUI.lb.getLabel(x+1, y))); 
							    temp = (Building)cty.buildings.get(position);
                         p.playerUI.lb.addBuilding(x+1, y, 8, new GameJLabel(getImage("data/academy.png")), false, p, index);
						}
               }
				   break;
			    case 4:
	             position = 0;
	         
		         for(k = x-1; k <= x+1; k++)
		         {  
		           i = y-1;
		           if(cty.buildings.get(position) != null)   
		           {
		              Building temp = (Building)cty.buildings.get(position);
		              if(temp.getName().equals("workshop"))
                    {
                          p.playerUI.lb.getHolder(k, i).remove(p.playerUI.lb.getHolder(k, i).getIndexOf(p.playerUI.lb.getLabel(k, i))); 
								  temp = (Building)cty.buildings.get(position);
                          p.playerUI.lb.addBuilding(k, i, 13, new GameJLabel(getImage("data/ironmine.png")), false, p, index);
						  }
	              }
                 position++;
	            }
	            for(k = x-1; k <= x+1; k++)
	            {  
	               i = y+1;
	               if(cty.buildings.get(position) != null)   
	               {
	                  Building temp = (Building)cty.buildings.get(position);
	                  if(temp.getName().equals("workshop"))
                       {
                         p.playerUI.lb.getHolder(k, i).remove(p.playerUI.lb.getHolder(k, i).getIndexOf(p.playerUI.lb.getLabel(k, i))); 
							    temp = (Building)cty.buildings.get(position);
                         p.playerUI.lb.addBuilding(k, i, 13, new GameJLabel(getImage("data/ironmine.png")), false, p, index);
							}
	               }
                  position++;
	            }
               if(cty.buildings.get(position) != null)   
	               {
	                  Building temp = (Building)cty.buildings.get(position);
	                  if(temp.getName().equals("workshop"))
                     {
                         p.playerUI.lb.getHolder(x-1, y).remove(p.playerUI.lb.getHolder(x-1, y).getIndexOf(p.playerUI.lb.getLabel(x-1, y))); 
							    temp = (Building)cty.buildings.get(position);
                         p.playerUI.lb.addBuilding(x-1, y, 13, new GameJLabel(getImage("data/ironmine.png")), false, p, index);
							}
	               }
               position++;
               if(cty.buildings.get(position) != null)   
               {
                  Building temp = (Building)cty.buildings.get(position);
                  if(temp.getName().equals("workshop"))
                  {
                         p.playerUI.lb.getHolder(x+1, y).remove(p.playerUI.lb.getHolder(x+1, y).getIndexOf(p.playerUI.lb.getLabel(x+1, y))); 
							    temp = (Building)cty.buildings.get(position);
                         p.playerUI.lb.addBuilding(x+1, y, 13, new GameJLabel(getImage("data/ironmine.png")), false, p, index);
						}
               }
				   break;
			    case 5:
	             position = 0;
	         
		         for(k = x-1; k <= x+1; k++)
		         {  
		           i = y-1;
		           if(cty.buildings.get(position) != null)   
		           {
		              Building temp = (Building)cty.buildings.get(position);
		              if(temp.getName().equals("temple"))
                    {
                          p.playerUI.lb.getHolder(k, i).remove(p.playerUI.lb.getHolder(k, i).getIndexOf(p.playerUI.lb.getLabel(k, i))); 
								  temp = (Building)cty.buildings.get(position);
                          p.playerUI.lb.addBuilding(k, i, 12, new GameJLabel(getImage("data/cathedral.png")), false, p, index);
						  }
	              }
                 position++;
	            }
	            for(k = x-1; k <= x+1; k++)
	            {  
	               i = y+1;
	               if(cty.buildings.get(position) != null)   
	               {
	                  Building temp = (Building)cty.buildings.get(position);
	                  if(temp.getName().equals("temple"))
                       {
                         p.playerUI.lb.getHolder(k, i).remove(p.playerUI.lb.getHolder(k, i).getIndexOf(p.playerUI.lb.getLabel(k, i))); 
							    temp = (Building)cty.buildings.get(position);
                         p.playerUI.lb.addBuilding(k, i, 12, new GameJLabel(getImage("data/cathedral.png")), false, p, index);
							}
	               }
                  position++;
	            }
               if(cty.buildings.get(position) != null)   
	               {
	                  Building temp = (Building)cty.buildings.get(position);
	                  if(temp.getName().equals("temple"))
                     {
                         p.playerUI.lb.getHolder(x-1, y).remove(p.playerUI.lb.getHolder(x-1, y).getIndexOf(p.playerUI.lb.getLabel(x-1, y))); 
							    temp = (Building)cty.buildings.get(position);
                         p.playerUI.lb.addBuilding(x-1, y, 12, new GameJLabel(getImage("data/cathedral.png")), false, p, index);
							}
	               }
               position++;
               if(cty.buildings.get(position) != null)   
               {
                  Building temp = (Building)cty.buildings.get(position);
                  if(temp.getName().equals("temple"))
                  {
                         p.playerUI.lb.getHolder(x+1, y).remove(p.playerUI.lb.getHolder(x+1, y).getIndexOf(p.playerUI.lb.getLabel(x+1, y))); 
							    temp = (Building)cty.buildings.get(position);
                         p.playerUI.lb.addBuilding(x+1, y, 12, new GameJLabel(getImage("data/cathedral.png")), false, p, index);
						}
               }
				   break;
			  }		
		}    
		
		public void addEgyptStartWonder(int nm)
      {
		   for(int k = 0; k < 4; k++)
         {
            if(availableWonders[k] != null)
				{
					Card temp = (Card)availableWonders[k];
		         GameJLabel l = new GameJLabel(new GameRotatedIcon(temp.getFrontIcon(), 180));
		         l.setBounds(00, 00+(60*numWonders), 88, 53);
		         wondersPane.add(l, new Integer(0));
		         wondersPane.repaint();
		            //        need to blow up this icon still
		         wonderListener[nm-20] = l;
		         wonderListener[nm-20].addMouseListener(this);
		         numWonders++; 
            }
		   }		    
		}

      public Vector getCityVector()
      {
         return cities;
      }
   
      public GameImageIcon getCityIcon(int index)
      {
         City temp = (City)cities.get(index);
         return temp.getIcon();
      }
   
      public int getCityXpos(int index)
      {
         if(index >= 0 && index <= cities.size()){
           City temp = (City)cities.get(index);
           return temp.getXPos();
         }    
		   else return -1;
		}
   
      public int getCityYpos(int index)
      {
         if(index >= 0 && index <= cities.size()){
           City temp = (City)cities.get(index);
           return temp.getYPos();
         }    
		   else return -1;
		}
   
      public int getCityProduction(int index)
      {
         City c = (City)cities.get(index);
         return c.getProd(this);
      }
   
      public int getCityTrade(int index)
      {
         City c = (City)cities.get(index);
         return c.getTrade(this);
      }
   
      public void gainCulture(int index)
      {
         City c = (City)cities.get(index);
         culture += c.getCulture(this);
         if(gov == 3)
            culture++;  
         playerBoard.showCulture.setText(Integer.toString(culture));
      }
   
      public boolean hasCityUnique(int index)
      {
         City temp = (City)cities.get(index);
         return temp.hasUniqueBuilding();
      }
   
      public boolean hasCityWonder(int index)
      {
         City temp = (City)cities.get(index);
         return temp.hasWonder();
      }
   
      public int numCities()
      {
         return this.cities.size();
      }
   
      public String getNation()
      {
         return nation;
      }
   
      public void setNation(String nation){
		   this.nation = nation;
		}
		
      public String getColor()
      {
         return this.color;
      }	  
   
      public int getGold()
      {
      //     int tempgold = this.gold;
      //	  for(int k = 0; k < cities.size(); k++)
      //	  {
      //	    City temp = (City)cities.get(k);  
      //		 tempgold += temp.getGold();  
      //	  }
      //	  this.gold -= tempgold;
      //	  this.cityGold = tempgold;
      //System.out.println("tempGold: "+tempgold +" gold: "+gold +"cityGold: "+cityGold);
         return gold;
      }	  
   
      public int getTrade()
      {
         return this.trade;
      }	
   
      public int getCulture()
      {
         return this.culture;
      }	  
   
      public GameJLayeredPane getTechPane()
      {
         return playerTechpane;
      }
   
      public Vector getTechVector()
      {
         return tech;
      }	  
   
      public boolean getTech(String name)
      {
         if(tech.contains(name))  
            return true;
         else
            return false;
      }	  
   
      public int getStacklimit()
      {
         return this.stackLimit;
      }	  
   
      public boolean getHasIrrigation()
      {
         return hasIrrigation;
      }
   
      public boolean getWaterLand()
      {
         return this.canWaterLand;
      }	  
   
      public boolean getWaterCross()
      {
         return this.canWaterCross;
      }	  
   
      public void setWaterLand()
      {
         canWaterLand = true;
      }
   
      public void setWaterCross()
      {
         canWaterCross = true;
      }
   
      public void setIrrigation()
      {
         hasIrrigation = true;
      }
   
      public void addmarketResource(String type)
      {
         if(type.equals("Iron"))
            addmarketIron(1);
         else if(type.equals("Incense"))
            addmarketIncense(1);
         else if(type.equals("Silk"))
            addmarketSilk(1);
         else if(type.equals("Wheat"))
            addmarketWheat(1);
      }
   
      public Vector getHutVector()
		{
		  return hutRes;
		}
		    
      public Vector getVillageVector()
		{
		  return villageRes;
		}

		public void addResource(String type, String source)
      {
         if(type.equals("Iron"))
         {
			   addIron(1);
			}  
	      else if(type.equals("Incense"))
         {
			   addIncense(1);
         }  
	      else if(type.equals("Silk"))
         {      
			   addSilk(1);
         }      
			else if(type.equals("Wheat"))
         {
			   addWheat(1);
         }   
         else if(type.equals("Spy"))
         {
			   addSpy(1);
         }  
	      else if(type.equals("Uranium"))
         {      
			   addUranium(1);
         }
         else if(type.equals("gpVillage"))
            addGreatPerson(playerUI.getRandomPerson());

         if(source.equals("hut"))
			{
			  hutRes.add(type);
			}
			else if(source.equals("village"))
			{
			  villageRes.add(type); 
			}      
      }
   
      public void spendResource(String peice, String src)
      {
   	   StringTokenizer peices = new StringTokenizer(peice, " ");
 		  
		   String type = peices.nextToken();
		   String source = peices.nextToken();
			
			if(type.equals("Iron"))
         {
            if(source.equals("market"))
               spendmarketIron();
            else if(source.equals("hut"))  
            {
					spendIron();
				   String com = "";  
               boolean done = false; 
 					for(int k = 0; !done; k++)  
					{
					  com = (String)hutRes.get(k);
					  if(com.equals(type))
					  {
					    hutRes.remove(k);
					    done = true;
					  }
					}
					playerBoard.setHut(-1);
				}      
            else
				{
				   spendIron();
				   String com = "";  
               boolean done = false;
				   for(int k = 0; !done; k++)  
					{
					  com = (String)villageRes.get(k);
					  if(com.equals(type))
					  {
					    villageRes.remove(k);
					    done = true;
					  }
					}
				   playerBoard.setVillage(-1);
				}
			}  
         else if(type.equals("Incense"))
         {
            if(source.equals("market"))
               spendmarketIncense();
            else if(source.equals("hut"))  
            {
					spendIncense();
				   String com = "";  
               boolean done = false; 
 					for(int k = 0; !done; k++)  
					{
					  com = (String)hutRes.get(k);
					  if(com.equals(type))
					  {
					    hutRes.remove(k);
					    done = true;
					  }
					}
					playerBoard.setHut(-1);
				}      
            else
				{
				   spendIncense();
				   String com = "";  
               boolean done = false;
				   for(int k = 0; !done; k++)  
					{
					  com = (String)villageRes.get(k);
					  if(com.equals(type))
					  {
					    villageRes.remove(k);
					    done = true;
					  }
					}
				   playerBoard.setVillage(-1);
				}
			}
         else if(type.equals("Silk"))
         {
            if(source.equals("market"))
               spendmarketSilk();
            else if(source.equals("hut"))  
            {
					spendSilk();
				   String com = "";  
               boolean done = false; 
 					for(int k = 0; !done; k++)  
					{
					  com = (String)hutRes.get(k);
					  if(com.equals(type))
					  {
					    hutRes.remove(k);
					    done = true;
					  }
					}
					playerBoard.setHut(-1);
				}      
            else
				{
				   spendSilk();
				   String com = "";  
               boolean done = false;
				   for(int k = 0; !done; k++)  
					{
					  com = (String)villageRes.get(k);
					  if(com.equals(type))
					  {
					    villageRes.remove(k);
					    done = true;
					  }
					}
				   playerBoard.setVillage(-1);
				}
			}  
         else if(type.equals("Wheat"))
         {
            if(source.equals("market"))
               spendmarketWheat();
            else if(source.equals("hut"))  
            {
					spendWheat();
				   String com = "";  
               boolean done = false; 
 					for(int k = 0; !done; k++)  
					{
					  com = (String)hutRes.get(k);
					  if(com.equals(type))
					  {
					    hutRes.remove(k);
					    done = true;
					  }
					}
					playerBoard.setHut(-1);
				}      
            else
				{
				   spendWheat();
				   String com = "";  
               boolean done = false;
				   for(int k = 0; !done; k++)  
					{
					  com = (String)villageRes.get(k);
					  if(com.equals(type))
					  {
					    villageRes.remove(k);
					    done = true;
					  }
					}
				   playerBoard.setVillage(-1);
				}
			}  
         else if(type.equals("Spy"))
         {
			   if(source.equals("hut"))  
            {
					spendSpy();
				   String com = "";  
               boolean done = false; 
 					for(int k = 0; !done; k++)  
					{
					  com = (String)hutRes.get(k);
					  if(com.equals(type))
					  {
					    hutRes.remove(k);
					    done = true;
					  }
					}
					playerBoard.setHut(-1);
				}      
            else
				{
				   spendSpy();
				   String com = "";  
               boolean done = false;
				   for(int k = 0; !done; k++)  
					{
					  com = (String)villageRes.get(k);
					  if(com.equals(type))
					  {
					    villageRes.remove(k);
					    done = true;
					  }
					}
				   playerBoard.setVillage(-1);
				}
          }   
         else if(type.equals("Uranium"))
         {
				   spendUranium();
				   String com = "";  
               boolean done = false;
				   for(int k = 0; !done; k++)  
					{
					  com = (String)villageRes.get(k);
					  if(com.equals(type))
					  {
					    villageRes.remove(k);
					    done = true;
					  }
					}
				   playerBoard.setVillage(-1);
	      }  
		}

		public void chooseResource(String type)
		{
		  Vector v = new Vector(0, 1);
		  Vector s = new Vector(0, 1);
	     Vector nameHuts = getHutVector();
	     Vector nameVillages = getVillageVector();
		  
		  if(type.equals("Iron"))
		  {
	 	    int marketiron = getmarketIron();   
			for(int k = 0; k < marketiron; k++)
			{
	        v.add(getImage("data/Iron_market.png"));
	        s.add("Iron market");
			}
	      for(int k = 0; k < nameHuts.size(); k++)
			{
			  String com = (String)nameHuts.get(k);
			  if(com.equals("Iron"))
	        {
			    v.add(getImage("data/Iron_hut.png"));
		       s.add("Iron hut");
			  }
         }		  
	      for(int k = 0; k < nameVillages.size(); k++)
			{
			  String com = (String)nameVillages.get(k);
			  if(com.equals("Iron"))
	        {
			    v.add(getImage("data/Iron_village.png"));
		       s.add("Iron village");
			  }
			}
		  }
		  else if(type.equals("Incense"))
		  {
 	       int marketincense = getmarketIncense();   
			for(int k = 0; k < marketincense; k++)
			{
	        v.add(getImage("data/Incense_market.png"));
	        s.add("Incense market");
			}
	      for(int k = 0; k < nameHuts.size(); k++)
			{
			  String com = (String)nameHuts.get(k);
			  if(com.equals("Incense"))
	        {
			    v.add(getImage("data/Incense_hut.png"));
		       s.add("Incense hut");
			  }
         }		  
		  }
		  else if(type.equals("Silk"))
		  {
	 	    int marketsilk = getmarketSilk();   
			for(int k = 0; k < marketsilk; k++)
			{
	        v.add(getImage("data/Silk_market.png"));
	        s.add("Silk market");
			}
	      for(int k = 0; k < nameHuts.size(); k++)
			{
			  String com = (String)nameHuts.get(k);
			  if(com.equals("Silk"))
	        {
			    v.add(getImage("data/Silk_hut.png"));
		       s.add("Silk hut");
			  }
		   }
		  }
		  else if(type.equals("Wheat"))
		  {
	 	    int marketwheat = getmarketWheat();   
			for(int k = 0; k < marketwheat; k++)
			{
	        v.add(getImage("data/Wheat_market.png"));
	        s.add("Wheat market");
			}
	      for(int k = 0; k < nameHuts.size(); k++)
			{
			  String com = (String)nameHuts.get(k);
			  if(com.equals("Wheat"))
	        {
			    v.add(getImage("data/Wheat_hut.png"));
		       s.add("Wheat hut");
			  }
		   }		  
		  }
		  else if(type.equals("Spy"))
		  {
	      for(int k = 0; k < nameHuts.size(); k++)
			{
			  String com = (String)nameHuts.get(k);
			  if(com.equals("Spy"))
	        {
			    v.add(getImage("data/Spy_hut.png"));
		       s.add("Spy hut");
			  }
		   }
	      if(v.size() == 0)
			{
				for(int k = 0; k < nameVillages.size(); k++)
				{
				  String com = (String)nameVillages.get(k);
				  if(com.equals("Spy"))
		        {
				    v.add(getImage("data/Spy_village.png"));
			       s.add("Spy village");
				  }
				}
         }
		   else
			  spendResource("Spy hut", " ");
		  }

        Object[] options_resource = v.toArray();
        JOptionPane start = new JOptionPane();

        String res1 = "";
		  int resource_1 = -1;
      
		  resource_1 = start.showOptionDialog(playerUI.c, "Choose resource token", getNation(),
                       JOptionPane.CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
                       getImage("data/"+type+".png"), options_resource, null);

		  if(resource_1 >= 0)
		  {
//System.out.println("CHOOSE "+(String)s.get(resource_1));
		    res1 = (String)s.get(resource_1);
          spendResource(res1, " ");           
        }
		}
		
		public void spendResource(String type)
      {
         if(type.equals("Iron"))
         {
            if(Iron == 0)
               spendmarketIron();
				else if(Iron > 0)  
            {
					chooseResource("Iron");
				}      
			}  
         else if(type.equals("Incense"))
         {
            if(Incense == 0)
               spendmarketIncense();
            else if(marketIncense == 0)
				   spendResource("Incense hut", " ");        
            else if(Incense > 0)  
            {
				   chooseResource("Incense");
            }      
			}
         else if(type.equals("Silk"))
         {
            if(Silk == 0)
               spendmarketSilk();
            else if(marketSilk == 0)
				   spendResource("Silk hut", " ");        
            else if(Silk > 0)  
            {
				   chooseResource("Silk");
            }      
			}  
         else if(type.equals("Wheat"))
         {
            if(Wheat == 0)
               spendmarketWheat();
            else if(marketWheat == 0)
				   spendResource("Wheat hut", " ");        
            else if(Wheat > 0)  
            {
				   chooseResource("Wheat");
            }      
			}  
         else if(type.equals("Spy"))
            chooseResource("Spy");
         else if(type.equals("Uranium"))
         {
            spendUranium();
            playerBoard.setVillage(-1);      
			}
      }
   
      public Vector getGreatPeople()
      {
         return greatPerson;
      }
    
      public void addGreatPerson(String type)
      {
         greatPerson.add(type);
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
         GameJLabel l = new GameJLabel(icon);
         l.setBounds(55, 55, 233+(56*greatPerson.size()), 320);
      //		playerBoard.playerBrd2.add(l, new Integer(5));
      //	   playerBoard.playerBrd2.repaint();
      }
   
      public void addIron(int i)
      {
         Iron = Iron + i;
         playerBoard.showIron.setText(Integer.toString(Iron));
      }
   
      public void spendIron()
      {
         Iron--;
         playerBoard.showIron.setText(Integer.toString(Iron+marketIron));
      }
   
      public int getIron()
      {
         return Iron+marketIron;
      }
   
      public void addmarketIron(int i)
      {
         marketIron = marketIron + i;
         playerUI.removeMarketResource("Iron");
         playerBoard.showIron.setText(Integer.toString(Iron+marketIron));
      }
   
      public void spendmarketIron()
      {
         marketIron--;
         playerUI.addMarketResource("Iron");
         playerBoard.showIron.setText(Integer.toString(Iron+marketIron));
      }
   
      public int getmarketIron()
      {
         return marketIron;
      }
   
      public void addSilk(int i)
      {
         Silk = Silk + i;
         playerBoard.showSilk.setText(Integer.toString(Silk+marketSilk));
      }
      public void spendSilk()
      {
         Silk--;
         playerBoard.showSilk.setText(Integer.toString(Silk+marketSilk));
      }
      public int getSilk()
      {
         return Silk+marketSilk;
      }
   
      public void addmarketSilk(int i)
      {
         marketSilk = marketSilk + i;
         playerUI.removeMarketResource("Silk");
         playerBoard.showSilk.setText(Integer.toString(Silk+marketSilk));
      }
      public void spendmarketSilk()
      {
         marketSilk--;
         playerUI.addMarketResource("Silk");
         playerBoard.showSilk.setText(Integer.toString(Silk+marketSilk));
      }
      public int getmarketSilk()
      {
         return marketSilk;
      }
   
      public void addIncense(int i)
      {
         Incense = Incense + i;
         playerBoard.showIncense.setText(Integer.toString(Incense+marketIncense));
      }
      public void spendIncense()
      {
         Incense--;
         playerBoard.showIncense.setText(Integer.toString(Incense+marketIncense));
      }
      public int getIncense()
      {
         return Incense+marketIncense;
      }
   
      public void addmarketIncense(int i)
      {
         marketIncense = marketIncense + i;
         playerUI.removeMarketResource("Incense");
         playerBoard.showIncense.setText(Integer.toString(Incense+marketIncense));
      }
      public void spendmarketIncense()
      {
         marketIncense--;
         playerUI.addMarketResource("Incense");
         playerBoard.showIncense.setText(Integer.toString(Incense+marketIncense));
      }
      public int getmarketIncense()
      {
         return marketIncense;
      }
   
      public void addWheat(int i)
      {
         Wheat = Wheat + i;
         playerBoard.showWheat.setText(Integer.toString(Wheat+marketWheat));
      }
      public void spendWheat()
      {
         Wheat--;
         playerBoard.showWheat.setText(Integer.toString(Wheat+marketWheat));
      }
      public int getWheat()
      {
         return Wheat+marketWheat;
      }
   
      public void addmarketWheat(int i)
      {
         marketWheat = marketWheat + i;
         playerUI.removeMarketResource("Wheat");
         playerBoard.showWheat.setText(Integer.toString(Wheat+marketWheat));
      }
      public void spendmarketWheat()
      {
         marketWheat--;
         playerUI.addMarketResource("Wheat");
         playerBoard.showWheat.setText(Integer.toString(Wheat+marketWheat));
      }
      public int getmarketWheat()
      {
         return marketWheat;
      }
   
      public void addSpy(int i)
      {
         Spy = Spy + i;
         playerBoard.showSpy.setText(Integer.toString(Spy));
      }
      public void spendSpy()
      {
         Spy--;
         playerBoard.showSpy.setText(Integer.toString(Spy));
      }
      public int getSpy()
      {
         return Spy;
      }
   
      public void addUranium(int i)
      {
         Uranium = Uranium + i;
         playerBoard.showUranium.setText(Integer.toString(Uranium));
      }
      public void spendUranium()
      {
         Uranium--;
         playerBoard.showUranium.setText(Integer.toString(Uranium));
      }
      public int getUranium()
      {
         return Uranium;
      }
   
      public void addMListener(GameJLabel l)
      {
		   l.addMouseListener(this);  
      }
   
      public void addTechGold(int num, int index)
      {
         Tech temp = (Tech)tech.get(index);  
         temp.addGold(num, this);
      }
   
      public void updateTechGold(int g)
      {
         gold += g;
         TradeGUI.startTrade(this, playerBoard.playerBrd2, true);
      }
   
      public void actionPerformed(ActionEvent e){

        for(int k = 0; k < 4; k++){
		    if(e.getSource() == cityButtons[k]){
            if(playerNumber == 0){
				   if(nation.equals("China")){
					    GameImageIcon iconCity1 = getImage("data/capital"+color+"16.png");
					    playerCity = new GameJLabel(iconCity1);
				           
					    playerUI.lb.getHolder(1, 13).remove(cityButtons[0]);
				       playerUI.lb.getHolder(1, 13).repaint();
				       playerUI.lb.getHolder(2, 13).remove(cityButtons[1]);
				       playerUI.lb.getHolder(2, 13).repaint();
				       playerUI.lb.getHolder(1, 14).remove(cityButtons[2]);
				       playerUI.lb.getHolder(1, 14).repaint();
				       playerUI.lb.getHolder(2, 14).remove(cityButtons[3]);
				       playerUI.lb.getHolder(2, 14).repaint();
		
			          playerCity.setBounds(0, 0, iconCity1.getIconWidth(),
			                                     iconCity1.getIconHeight()); 
	
						 switch(k)
				  		 {
						  case 0:
					       addCity(1, 13, true);      
							 addTrade(playerUI.lb.getTrade(1, 13));
				          TradeGUI.startTrade(this, playerUI.mainBoard, false); 
						    playerUI.ch6.add(playerCity, new Integer(1));
							 break;
						  case 1:
					       addCity(2, 13, true);      
							 addTrade(playerUI.lb.getTrade(2, 13));
				          TradeGUI.startTrade(this, playerUI.mainBoard, false); 
						    playerUI.ch7.add(playerCity, new Integer(1));
							 break;
				 		  case 2:
					       addCity(1, 14, true);      
							 addTrade(playerUI.lb.getTrade(1, 14));
				          TradeGUI.startTrade(this, playerUI.mainBoard, false); 
						    playerUI.ch10.add(playerCity, new Integer(1));
						    break; 
				 		  case 3:
					       addCity(2, 14, true);      
							 addTrade(playerUI.lb.getTrade(2, 14));
				          TradeGUI.startTrade(this, playerUI.mainBoard, false); 
						    playerUI.ch11.add(playerCity, new Integer(1));
						    break; 
					   }
	             }
		     }else if(playerNumber == 1){
				   if(nation.equals("America")){
					    GameImageIcon iconCity1 = getImage("data/capital"+color+"12.png");
					    playerCity = new GameJLabel(iconCity1);

					    playerUI.lb.getHolder(5, 1).remove(cityButtons[0]);
				       playerUI.lb.getHolder(5, 1).repaint();
				       playerUI.lb.getHolder(6, 1).remove(cityButtons[1]);
				       playerUI.lb.getHolder(6, 1).repaint();
				       playerUI.lb.getHolder(5, 2).remove(cityButtons[2]);
				       playerUI.lb.getHolder(5, 2).repaint();
				       playerUI.lb.getHolder(6, 2).remove(cityButtons[3]);
				       playerUI.lb.getHolder(6, 2).repaint();
		
			          playerCity.setBounds(0, 0, iconCity1.getIconWidth(),
			                                     iconCity1.getIconHeight()); 
		
						 switch(k)
				  		 {
						  case 0:
					       addCity(5, 1, true);      
							 addTrade(playerUI.lb.getTrade(5, 1));
				          TradeGUI.startTrade(this, playerUI.mainBoard, false); 
						    playerUI.am6.add(playerCity, new Integer(1));
							 break;
						  case 1:
					       addCity(6, 1, true);      
							 addTrade(playerUI.lb.getTrade(6, 1));
				          TradeGUI.startTrade(this, playerUI.mainBoard, false); 
						    playerUI.am7.add(playerCity, new Integer(1));
							 break;
				 		  case 2:
					       addCity(5, 2, true);      
							 addTrade(playerUI.lb.getTrade(5, 2));
				          TradeGUI.startTrade(this, playerUI.mainBoard, false); 
						    playerUI.am10.add(playerCity, new Integer(1));
						    break; 
				 		  case 3:
					       addCity(6, 2, true);      
							 addTrade(playerUI.lb.getTrade(6, 2));
				          TradeGUI.startTrade(this, playerUI.mainBoard, false); 
						    playerUI.am11.add(playerCity, new Integer(1));
						    break; 
					   }
	             }
				  }

			     playerCity.addMouseListener(this);  
				  startChoice = k;  
System.out.println("starting settler\n");
				  startSettler();   
			 }
		  }
//Settler start action
        for(int k = 0; k < 8; k++){
           if(e.getSource() == settlerButtons[k]){

				  if(playerNumber == 0){        
					  if(nation.equals("China")){
//JDialog jh = new JDialog();
//jh.add(new javax.swing.JTextArea(Integer.valueOf(playerNumber).toString()), BorderLayout.NORTH);
//jh.add(new javax.swing.JTextArea(color), BorderLayout.WEST);
//jh.add(new javax.swing.JTextArea(Integer.valueOf(k).toString()), BorderLayout.SOUTH);
//jh.add(new javax.swing.JTextArea("NATION:"+nation+":"), BorderLayout.CENTER);
//jh.show();              

		              Settler playerSettler = new Settler(color, "1");

//	System.out.println("BUTTONSettler");			 
		              switch(startChoice){
						    case 0:
								 playerUI.lb.getHolder(0, 12).remove(settlerButtons[0]);
							    playerUI.lb.getHolder(0, 12).repaint();
							    playerUI.lb.getHolder(1, 12).remove(settlerButtons[1]);
							    playerUI.lb.getHolder(1, 12).repaint();
							    playerUI.lb.getHolder(2, 12).remove(settlerButtons[2]);
							    playerUI.lb.getHolder(2, 12).repaint();
							    playerUI.lb.getHolder(0, 13).remove(settlerButtons[3]);
							    playerUI.lb.getHolder(0, 13).repaint();
							    playerUI.lb.getHolder(2, 13).remove(settlerButtons[4]);
							    playerUI.lb.getHolder(2, 13).repaint();
							    playerUI.lb.getHolder(0, 14).remove(settlerButtons[5]);
							    playerUI.lb.getHolder(0, 14).repaint();
							    playerUI.lb.getHolder(1, 14).remove(settlerButtons[6]);
							    playerUI.lb.getHolder(1, 14).repaint();
							    playerUI.lb.getHolder(2, 14).remove(settlerButtons[7]);
							    playerUI.lb.getHolder(2, 14).repaint();
						       switch(k)
								 {
								   case 0:
								       playerUI.ch1.add(playerSettler.getLabel(), new Integer(5));		       
										 addSettler(playerSettler);
										 playerSettler.setPosition(0, 12);
										 playerUI.lb.addSettlerCount(0, 12);      
		                         break;							
									case 1:
								       playerUI.ch2.add(playerSettler.getLabel(), new Integer(5));		       
										 addSettler(playerSettler);
										 playerSettler.setPosition(1, 12);
						             playerUI.lb.addSettlerCount(1, 12);      
									  break;	 
									case 2:
								       playerUI.ch3.add(playerSettler.getLabel(), new Integer(5));		       
										 addSettler(playerSettler);
										 playerSettler.setPosition(2, 12);
						             playerUI.lb.addSettlerCount(2, 12);      
									  break;	 
									case 3:
								       playerUI.ch5.add(playerSettler.getLabel(), new Integer(5));		       
										 addSettler(playerSettler);
										 playerSettler.setPosition(0, 13);
						             playerUI.lb.addSettlerCount(0, 13);      
									  break;	 
									case 4:
								       playerUI.ch7.add(playerSettler.getLabel(), new Integer(5));		       
										 addSettler(playerSettler);
										 playerSettler.setPosition(2, 13);
						             playerUI.lb.addSettlerCount(2, 13);      
									  break;	 
									case 5:
								       playerUI.ch9.add(playerSettler.getLabel(), new Integer(5));		       
										 addSettler(playerSettler);
										 playerSettler.setPosition(0, 14);
						             playerUI.lb.addSettlerCount(0, 14);      
									  break;	 
									case 6:
								       playerUI.ch10.add(playerSettler.getLabel(), new Integer(5));		       
										 addSettler(playerSettler);
										 playerSettler.setPosition(1, 14);
						             playerUI.lb.addSettlerCount(1, 14);      
									  break;	 
									case 7:
								       playerUI.ch11.add(playerSettler.getLabel(), new Integer(5));		       
										 addSettler(playerSettler);
										 playerSettler.setPosition(2, 14);
						             playerUI.lb.addSettlerCount(2, 14);      
									  break;	 
								 }    
								 break;	 
							 case 1:
								 playerUI.lb.getHolder(1, 12).remove(settlerButtons[0]);
							    playerUI.lb.getHolder(1, 12).repaint();
							    playerUI.lb.getHolder(2, 12).remove(settlerButtons[1]);
							    playerUI.lb.getHolder(2, 12).repaint();
							    playerUI.lb.getHolder(3, 12).remove(settlerButtons[2]);
							    playerUI.lb.getHolder(3, 12).repaint();
							    playerUI.lb.getHolder(1, 13).remove(settlerButtons[3]);
							    playerUI.lb.getHolder(1, 13).repaint();
							    playerUI.lb.getHolder(3, 13).remove(settlerButtons[4]);
							    playerUI.lb.getHolder(3, 13).repaint();
							    playerUI.lb.getHolder(1, 14).remove(settlerButtons[5]);
							    playerUI.lb.getHolder(1, 14).repaint();
							    playerUI.lb.getHolder(2, 14).remove(settlerButtons[6]);
							    playerUI.lb.getHolder(2, 14).repaint();
							    playerUI.lb.getHolder(3, 14).remove(settlerButtons[7]);
							    playerUI.lb.getHolder(3, 14).repaint();
						       switch(k)
								 {
								   case 0:
								       playerUI.ch2.add(playerSettler.getLabel(), new Integer(5));		       
										 addSettler(playerSettler);
										 playerSettler.setPosition(1, 12);
										 playerUI.lb.addSettlerCount(1, 12);      
		                         break;							
									case 1:
								       playerUI.ch3.add(playerSettler.getLabel(), new Integer(5));		       
										 addSettler(playerSettler);
										 playerSettler.setPosition(2, 12);
						             playerUI.lb.addSettlerCount(2, 12);      
									  break;	 
									case 2:
								       playerUI.ch4.add(playerSettler.getLabel(), new Integer(5));		       
										 addSettler(playerSettler);
										 playerSettler.setPosition(3, 12);
						             playerUI.lb.addSettlerCount(3, 12);      
									  break;	 
									case 3:
								       playerUI.ch6.add(playerSettler.getLabel(), new Integer(5));		       
										 addSettler(playerSettler);
										 playerSettler.setPosition(1, 13);
						             playerUI.lb.addSettlerCount(1, 13);      
									  break;	 
									case 4:
								       playerUI.ch8.add(playerSettler.getLabel(), new Integer(5));		       
										 addSettler(playerSettler);
										 playerSettler.setPosition(3, 13);
						             playerUI.lb.addSettlerCount(3, 13);      
									  break;	 
									case 5:
								       playerUI.ch10.add(playerSettler.getLabel(), new Integer(5));		       
										 addSettler(playerSettler);
										 playerSettler.setPosition(0, 14);
						             playerUI.lb.addSettlerCount(0, 14);      
									  break;	 
									case 6:
								       playerUI.ch11.add(playerSettler.getLabel(), new Integer(5));		       
										 addSettler(playerSettler);
										 playerSettler.setPosition(2, 14);
						             playerUI.lb.addSettlerCount(2, 14);      
									  break;	 
									case 7:
								       playerUI.ch12.add(playerSettler.getLabel(), new Integer(5));		       
										 addSettler(playerSettler);
										 playerSettler.setPosition(3, 14);
						             playerUI.lb.addSettlerCount(3, 14);      
									  break;	 
								 }					 
								 break;	 
							 case 2:
								 playerUI.lb.getHolder(0, 13).remove(settlerButtons[0]);
							    playerUI.lb.getHolder(0, 13).repaint();
							    playerUI.lb.getHolder(1, 13).remove(settlerButtons[1]);
							    playerUI.lb.getHolder(1, 13).repaint();
							    playerUI.lb.getHolder(2, 13).remove(settlerButtons[2]);
							    playerUI.lb.getHolder(2, 13).repaint();
							    playerUI.lb.getHolder(0, 14).remove(settlerButtons[3]);
							    playerUI.lb.getHolder(0, 14).repaint();
							    playerUI.lb.getHolder(2, 14).remove(settlerButtons[4]);
							    playerUI.lb.getHolder(2, 14).repaint();
							    playerUI.lb.getHolder(0, 15).remove(settlerButtons[5]);
							    playerUI.lb.getHolder(0, 15).repaint();
							    playerUI.lb.getHolder(2, 15).remove(settlerButtons[6]);
							    playerUI.lb.getHolder(2, 15).repaint();
						       switch(k)
								 {
								   case 0:
								       playerUI.ch5.add(playerSettler.getLabel(), new Integer(5));		       
										 addSettler(playerSettler);
										 playerSettler.setPosition(0, 13);
										 playerUI.lb.addSettlerCount(0, 13);      
		                         break;							
									case 1:
								       playerUI.ch6.add(playerSettler.getLabel(), new Integer(5));		       
										 addSettler(playerSettler);
										 playerSettler.setPosition(1, 13);
						             playerUI.lb.addSettlerCount(1, 13);      
									  break;	 
									case 2:
								       playerUI.ch7.add(playerSettler.getLabel(), new Integer(5));		       
										 addSettler(playerSettler);
										 playerSettler.setPosition(2, 13);
						             playerUI.lb.addSettlerCount(2, 13);      
									  break;	 
									case 3:
								       playerUI.ch9.add(playerSettler.getLabel(), new Integer(5));		       
										 addSettler(playerSettler);
										 playerSettler.setPosition(0, 14);
						             playerUI.lb.addSettlerCount(0, 14);      
									  break;	 
									case 4:
								       playerUI.ch11.add(playerSettler.getLabel(), new Integer(5));		       
										 addSettler(playerSettler);
										 playerSettler.setPosition(1, 14);
						             playerUI.lb.addSettlerCount(1, 14);      
									  break;	 
									case 5:
								       playerUI.ch13.add(playerSettler.getLabel(), new Integer(5));		       
										 addSettler(playerSettler);
										 playerSettler.setPosition(0, 15);
						             playerUI.lb.addSettlerCount(0, 15);      
									  break;	 
									case 6:
								       playerUI.ch15.add(playerSettler.getLabel(), new Integer(5));		       
										 addSettler(playerSettler);
										 playerSettler.setPosition(2, 15);
						             playerUI.lb.addSettlerCount(2, 15);      
									  break;	 
								 }					 
								 break;	 
							 case 3:
								 playerUI.lb.getHolder(1, 13).remove(settlerButtons[0]);
							    playerUI.lb.getHolder(1, 13).repaint();
							    playerUI.lb.getHolder(2, 13).remove(settlerButtons[1]);
							    playerUI.lb.getHolder(2, 13).repaint();
							    playerUI.lb.getHolder(3, 13).remove(settlerButtons[2]);
							    playerUI.lb.getHolder(3, 13).repaint();
							    playerUI.lb.getHolder(1, 14).remove(settlerButtons[3]);
							    playerUI.lb.getHolder(1, 14).repaint();
							    playerUI.lb.getHolder(3, 14).remove(settlerButtons[4]);
							    playerUI.lb.getHolder(3, 14).repaint();
							    playerUI.lb.getHolder(2, 15).remove(settlerButtons[5]);
							    playerUI.lb.getHolder(2, 15).repaint();
						       switch(k)
								 {
								   case 0:
								       playerUI.ch6.add(playerSettler.getLabel(), new Integer(5));		       
										 addSettler(playerSettler);
										 playerSettler.setPosition(1, 13);
										 playerUI.lb.addSettlerCount(1, 13);      
		                         break;							
									case 1:
								       playerUI.ch7.add(playerSettler.getLabel(), new Integer(5));		       
										 addSettler(playerSettler);
										 playerSettler.setPosition(2, 13);
						             playerUI.lb.addSettlerCount(2, 13);      
									  break;	 
									case 2:
								       playerUI.ch8.add(playerSettler.getLabel(), new Integer(5));		       
										 addSettler(playerSettler);
										 playerSettler.setPosition(3, 13);
						             playerUI.lb.addSettlerCount(3, 13);      
									  break;	 
									case 3:
								       playerUI.ch10.add(playerSettler.getLabel(), new Integer(5));		       
										 addSettler(playerSettler);
										 playerSettler.setPosition(1, 14);
						             playerUI.lb.addSettlerCount(1, 14);      
									  break;	 
									case 4:
								       playerUI.ch12.add(playerSettler.getLabel(), new Integer(5));		       
										 addSettler(playerSettler);
										 playerSettler.setPosition(3, 14);
						             playerUI.lb.addSettlerCount(3, 14);      
									  break;	 
									case 5:
								       playerUI.ch15.add(playerSettler.getLabel(), new Integer(5));		       
										 addSettler(playerSettler);
										 playerSettler.setPosition(2, 15);
						             playerUI.lb.addSettlerCount(2, 15);      
									  break;	 
		                   }
								 break;	 
						 }        
					 }
             }else if(playerNumber == 1){
				    if(nation.equals("America")){
		              Settler playerSettler = new Settler(color, "1");
		              switch(startChoice){
						    case 0:
								 playerUI.lb.getHolder(4, 0).remove(settlerButtons[0]);
							    playerUI.lb.getHolder(4, 0).repaint();
							    playerUI.lb.getHolder(6, 0).remove(settlerButtons[1]);
							    playerUI.lb.getHolder(6, 0).repaint();
							    playerUI.lb.getHolder(4, 1).remove(settlerButtons[2]);
							    playerUI.lb.getHolder(4, 1).repaint();
							    playerUI.lb.getHolder(6, 1).remove(settlerButtons[3]);
							    playerUI.lb.getHolder(6, 1).repaint();
							    playerUI.lb.getHolder(5, 2).remove(settlerButtons[4]);
							    playerUI.lb.getHolder(5, 2).repaint();
							    playerUI.lb.getHolder(6, 2).remove(settlerButtons[5]);
							    playerUI.lb.getHolder(6, 2).repaint();
						       switch(k)
								 {
								   case 0:
								       playerUI.am1.add(playerSettler.getLabel(), new Integer(5));		       
										 addSettler(playerSettler);
										 playerSettler.setPosition(4, 0);
										 playerUI.lb.addSettlerCount(4, 0);      
		                         break;							
									case 1:
								       playerUI.am3.add(playerSettler.getLabel(), new Integer(5));		       
										 addSettler(playerSettler);
										 playerSettler.setPosition(6, 0);
						             playerUI.lb.addSettlerCount(6, 0);      
									  break;	 
									case 2:
								       playerUI.am5.add(playerSettler.getLabel(), new Integer(5));		       
										 addSettler(playerSettler);
										 playerSettler.setPosition(4, 1);
						             playerUI.lb.addSettlerCount(4, 1);      
									  break;	 
									case 3:
								       playerUI.am7.add(playerSettler.getLabel(), new Integer(5));		       
										 addSettler(playerSettler);
										 playerSettler.setPosition(6, 1);
						             playerUI.lb.addSettlerCount(6, 1);      
									  break;	 
									case 4:
								       playerUI.am10.add(playerSettler.getLabel(), new Integer(5));		       
										 addSettler(playerSettler);
										 playerSettler.setPosition(5, 2);
						             playerUI.lb.addSettlerCount(5, 2);      
									  break;	 
									case 5:
								       playerUI.am11.add(playerSettler.getLabel(), new Integer(5));		       
										 addSettler(playerSettler);
										 playerSettler.setPosition(6, 2);
						             playerUI.lb.addSettlerCount(6, 2);      
									  break;	 
								 }    
								 break;	 
							 case 1:
								 playerUI.lb.getHolder(6, 0).remove(settlerButtons[0]);
							    playerUI.lb.getHolder(6, 0).repaint();
							    playerUI.lb.getHolder(5, 1).remove(settlerButtons[1]);
							    playerUI.lb.getHolder(5, 1).repaint();
							    playerUI.lb.getHolder(7, 1).remove(settlerButtons[2]);
							    playerUI.lb.getHolder(7, 1).repaint();
							    playerUI.lb.getHolder(5, 2).remove(settlerButtons[3]);
							    playerUI.lb.getHolder(5, 2).repaint();
							    playerUI.lb.getHolder(6, 2).remove(settlerButtons[4]);
							    playerUI.lb.getHolder(6, 2).repaint();
							    playerUI.lb.getHolder(7, 2).remove(settlerButtons[5]);
							    playerUI.lb.getHolder(7, 2).repaint();
						       switch(k)
								 {
								   case 0:
								       playerUI.am3.add(playerSettler.getLabel(), new Integer(5));		       
										 addSettler(playerSettler);
										 playerSettler.setPosition(6, 0);
										 playerUI.lb.addSettlerCount(6, 0);      
		                         break;							
									case 1:
								       playerUI.am6.add(playerSettler.getLabel(), new Integer(5));		       
										 addSettler(playerSettler);
										 playerSettler.setPosition(5, 1);
						             playerUI.lb.addSettlerCount(5, 1);      
									  break;	 
									case 2:
								       playerUI.am8.add(playerSettler.getLabel(), new Integer(5));		       
										 addSettler(playerSettler);
										 playerSettler.setPosition(7, 1);
						             playerUI.lb.addSettlerCount(7, 1);      
									  break;	 
									case 3:
								       playerUI.am10.add(playerSettler.getLabel(), new Integer(5));		       
										 addSettler(playerSettler);
										 playerSettler.setPosition(5, 2);
						             playerUI.lb.addSettlerCount(5, 2);      
									  break;	 
									case 4:
								       playerUI.am11.add(playerSettler.getLabel(), new Integer(5));		       
										 addSettler(playerSettler);
										 playerSettler.setPosition(6, 2);
						             playerUI.lb.addSettlerCount(6, 2);      
									  break;	 
									case 5:
								       playerUI.am12.add(playerSettler.getLabel(), new Integer(5));		       
										 addSettler(playerSettler);
										 playerSettler.setPosition(7, 2);
						             playerUI.lb.addSettlerCount(7, 2);      
									  break;	 
								 }					 
								 break;	 
							 case 2:
								 playerUI.lb.getHolder(4, 1).remove(settlerButtons[0]);
							    playerUI.lb.getHolder(4, 1).repaint();
							    playerUI.lb.getHolder(5, 1).remove(settlerButtons[1]);
							    playerUI.lb.getHolder(5, 1).repaint();
							    playerUI.lb.getHolder(6, 1).remove(settlerButtons[2]);
							    playerUI.lb.getHolder(6, 1).repaint();
							    playerUI.lb.getHolder(6, 2).remove(settlerButtons[3]);
							    playerUI.lb.getHolder(6, 2).repaint();
							    playerUI.lb.getHolder(4, 3).remove(settlerButtons[4]);
							    playerUI.lb.getHolder(4, 3).repaint();
							    playerUI.lb.getHolder(6, 3).remove(settlerButtons[5]);
							    playerUI.lb.getHolder(6, 3).repaint();
						       switch(k)
								 {
								   case 0:
								       playerUI.am5.add(playerSettler.getLabel(), new Integer(5));		       
										 addSettler(playerSettler);
										 playerSettler.setPosition(4, 1);
										 playerUI.lb.addSettlerCount(4, 1);      
		                         break;							
									case 1:
								       playerUI.am6.add(playerSettler.getLabel(), new Integer(5));		       
										 addSettler(playerSettler);
										 playerSettler.setPosition(5, 1);
						             playerUI.lb.addSettlerCount(5, 1);      
									  break;	 
									case 2:
								       playerUI.am7.add(playerSettler.getLabel(), new Integer(5));		       
										 addSettler(playerSettler);
										 playerSettler.setPosition(6, 1);
						             playerUI.lb.addSettlerCount(6, 1);      
									  break;	 
									case 3:
								       playerUI.am11.add(playerSettler.getLabel(), new Integer(5));		       
										 addSettler(playerSettler);
										 playerSettler.setPosition(6, 2);
						             playerUI.lb.addSettlerCount(6, 2);      
									  break;	 
									case 4:
								       playerUI.am13.add(playerSettler.getLabel(), new Integer(5));		       
										 addSettler(playerSettler);
										 playerSettler.setPosition(4, 3);
						             playerUI.lb.addSettlerCount(4, 3);      
									  break;	 
									case 5:
								       playerUI.am15.add(playerSettler.getLabel(), new Integer(5));		       
										 addSettler(playerSettler);
										 playerSettler.setPosition(6, 3);
						             playerUI.lb.addSettlerCount(6, 3);      
									  break;	 
								 }					 
								 break;	 
							 case 3:
								 playerUI.lb.getHolder(5, 1).remove(settlerButtons[0]);
							    playerUI.lb.getHolder(5, 1).repaint();
							    playerUI.lb.getHolder(6, 1).remove(settlerButtons[1]);
							    playerUI.lb.getHolder(6, 1).repaint();
							    playerUI.lb.getHolder(7, 1).remove(settlerButtons[2]);
							    playerUI.lb.getHolder(7, 1).repaint();
							    playerUI.lb.getHolder(5, 2).remove(settlerButtons[3]);
							    playerUI.lb.getHolder(5, 2).repaint();
							    playerUI.lb.getHolder(7, 2).remove(settlerButtons[4]);
							    playerUI.lb.getHolder(7, 2).repaint();
							    playerUI.lb.getHolder(6, 3).remove(settlerButtons[5]);
							    playerUI.lb.getHolder(6, 3).repaint();
							    playerUI.lb.getHolder(7, 3).remove(settlerButtons[6]);
							    playerUI.lb.getHolder(7, 3).repaint();
						       switch(k)
								 {
								   case 0:
								       playerUI.am6.add(playerSettler.getLabel(), new Integer(5));		       
										 addSettler(playerSettler);
										 playerSettler.setPosition(5, 1);
										 playerUI.lb.addSettlerCount(5, 1);      
		                         break;							
									case 1:
								       playerUI.am7.add(playerSettler.getLabel(), new Integer(5));		       
										 addSettler(playerSettler);
										 playerSettler.setPosition(6, 1);
						             playerUI.lb.addSettlerCount(6, 1);      
									  break;	 
									case 2:
								       playerUI.am8.add(playerSettler.getLabel(), new Integer(5));		       
										 addSettler(playerSettler);
										 playerSettler.setPosition(7, 1);
						             playerUI.lb.addSettlerCount(7, 1);      
									  break;	 
									case 3:
								       playerUI.am10.add(playerSettler.getLabel(), new Integer(5));		       
										 addSettler(playerSettler);
										 playerSettler.setPosition(5, 2);
						             playerUI.lb.addSettlerCount(5, 2);      
									  break;	 
									case 4:
								       playerUI.am12.add(playerSettler.getLabel(), new Integer(5));		       
										 addSettler(playerSettler);
										 playerSettler.setPosition(7, 2);
						             playerUI.lb.addSettlerCount(7, 2);      
									  break;	 
									case 5:
								       playerUI.am15.add(playerSettler.getLabel(), new Integer(5));		       
										 addSettler(playerSettler);
										 playerSettler.setPosition(6, 3);
						             playerUI.lb.addSettlerCount(6, 3);      
									  break;	 
									case 6:
								       playerUI.am16.add(playerSettler.getLabel(), new Integer(5));		       
										 addSettler(playerSettler);
										 playerSettler.setPosition(7, 3);
						             playerUI.lb.addSettlerCount(7, 3);      
									  break;	 
		                   }
								 break;	 
						 }        
					 
					 }
				 }
System.out.println("starting army\n");
             startArmy();    
		     }
		  }	

        for(int k = 0; k < 8; k++){
		    if(e.getSource() == armyButtons[k]){
            if(playerNumber == 0){
					if(nation.equals("China")){
		           Army playerArmy = new Army(color, "1");
//	System.out.println("BUTTONArmy");			 
	              switch(startChoice)
					  {
					    case 0:
							 playerUI.lb.getHolder(0, 12).remove(armyButtons[0]);
						    playerUI.lb.getHolder(0, 12).repaint();
						    playerUI.lb.getHolder(1, 12).remove(armyButtons[1]);
						    playerUI.lb.getHolder(1, 12).repaint();
						    playerUI.lb.getHolder(2, 12).remove(armyButtons[2]);
						    playerUI.lb.getHolder(2, 12).repaint();
						    playerUI.lb.getHolder(0, 13).remove(armyButtons[3]);
						    playerUI.lb.getHolder(0, 13).repaint();
						    playerUI.lb.getHolder(2, 13).remove(armyButtons[4]);
						    playerUI.lb.getHolder(2, 13).repaint();
						    playerUI.lb.getHolder(0, 14).remove(armyButtons[5]);
						    playerUI.lb.getHolder(0, 14).repaint();
						    playerUI.lb.getHolder(1, 14).remove(armyButtons[6]);
						    playerUI.lb.getHolder(1, 14).repaint();
						    playerUI.lb.getHolder(2, 14).remove(armyButtons[7]);
						    playerUI.lb.getHolder(2, 14).repaint();
					       switch(k)
							 {
							   case 0:
			                   playerArmy.setBounds(playerUI.lb.getSettlerCount(0, 12) , playerUI.lb.getArmyCount(0, 12));    
							       playerUI.ch1.add(playerArmy.getLabel(), new Integer(5));		       
									 addArmy(playerArmy);
									 playerArmy.setPosition(0, 12);
									 playerUI.lb.addArmyCount(0, 12);      
	                         break;							
								case 1:
			                   playerArmy.setBounds(playerUI.lb.getSettlerCount(1, 12) , playerUI.lb.getArmyCount(1, 12));    
							       playerUI.ch2.add(playerArmy.getLabel(), new Integer(5));		       
									 addArmy(playerArmy);
									 playerArmy.setPosition(1, 12);
					             playerUI.lb.addArmyCount(1, 12);      
								  break;	 
								case 2:
			                   playerArmy.setBounds(playerUI.lb.getSettlerCount(2, 12) , playerUI.lb.getArmyCount(2, 12));    
							       playerUI.ch3.add(playerArmy.getLabel(), new Integer(5));		       
									 addArmy(playerArmy);
									 playerArmy.setPosition(2, 12);
					             playerUI.lb.addArmyCount(2, 12);      
								  break;	 
								case 3:
			                   playerArmy.setBounds(playerUI.lb.getSettlerCount(0, 13) , playerUI.lb.getArmyCount(0, 13));    
							       playerUI.ch5.add(playerArmy.getLabel(), new Integer(5));		       
									 addArmy(playerArmy);
									 playerArmy.setPosition(0, 13);
					             playerUI.lb.addArmyCount(0, 13);      
								  break;	 
								case 4:
			                   playerArmy.setBounds(playerUI.lb.getSettlerCount(2, 13) , playerUI.lb.getArmyCount(2, 13));    
							       playerUI.ch7.add(playerArmy.getLabel(), new Integer(5));		       
									 addArmy(playerArmy);
									 playerArmy.setPosition(2, 13);
					             playerUI.lb.addArmyCount(2, 13);      
								  break;	 
								case 5:
			                   playerArmy.setBounds(playerUI.lb.getSettlerCount(0, 14) , playerUI.lb.getArmyCount(0, 14));    
							       playerUI.ch9.add(playerArmy.getLabel(), new Integer(5));		       
									 addArmy(playerArmy);
									 playerArmy.setPosition(0, 14);
					             playerUI.lb.addArmyCount(0, 14);      
								  break;	 
								case 6:
			                   playerArmy.setBounds(playerUI.lb.getSettlerCount(1, 14) , playerUI.lb.getArmyCount(1, 14));    
							       playerUI.ch10.add(playerArmy.getLabel(), new Integer(5));		       
									 addArmy(playerArmy);
									 playerArmy.setPosition(1, 14);
					             playerUI.lb.addArmyCount(1, 14);      
								  break;	 
								case 7:
			                   playerArmy.setBounds(playerUI.lb.getSettlerCount(2, 14) , playerUI.lb.getArmyCount(2, 14));    
							       playerUI.ch11.add(playerArmy.getLabel(), new Integer(5));		       
									 addArmy(playerArmy);
									 playerArmy.setPosition(2, 14);
					             playerUI.lb.addArmyCount(2, 14);      
								  break;	 
							 }    
							 break;	 
						 case 1:
							 playerUI.lb.getHolder(1, 12).remove(armyButtons[0]);
						    playerUI.lb.getHolder(1, 12).repaint();
						    playerUI.lb.getHolder(2, 12).remove(armyButtons[1]);
						    playerUI.lb.getHolder(2, 12).repaint();
						    playerUI.lb.getHolder(3, 12).remove(armyButtons[2]);
						    playerUI.lb.getHolder(3, 12).repaint();
						    playerUI.lb.getHolder(1, 13).remove(armyButtons[3]);
						    playerUI.lb.getHolder(1, 13).repaint();
						    playerUI.lb.getHolder(3, 13).remove(armyButtons[4]);
						    playerUI.lb.getHolder(3, 13).repaint();
						    playerUI.lb.getHolder(1, 14).remove(armyButtons[5]);
						    playerUI.lb.getHolder(1, 14).repaint();
						    playerUI.lb.getHolder(2, 14).remove(armyButtons[6]);
						    playerUI.lb.getHolder(2, 14).repaint();
						    playerUI.lb.getHolder(3, 14).remove(armyButtons[7]);
						    playerUI.lb.getHolder(3, 14).repaint();
					       switch(k)
							 {
							   case 0:
			                   playerArmy.setBounds(playerUI.lb.getSettlerCount(1, 12) , playerUI.lb.getArmyCount(1, 12));    
							       playerUI.ch2.add(playerArmy.getLabel(), new Integer(5));		       
									 addArmy(playerArmy);
									 playerArmy.setPosition(1, 12);
									 playerUI.lb.addArmyCount(1, 12);      
	                         break;							
								case 1:
			                   playerArmy.setBounds(playerUI.lb.getSettlerCount(2, 12) , playerUI.lb.getArmyCount(2, 12));    
							       playerUI.ch3.add(playerArmy.getLabel(), new Integer(5));		       
									 addArmy(playerArmy);
									 playerArmy.setPosition(2, 12);
					             playerUI.lb.addArmyCount(2, 12);      
								  break;	 
								case 2:
			                   playerArmy.setBounds(playerUI.lb.getSettlerCount(3, 12) , playerUI.lb.getArmyCount(3, 12));    
							       playerUI.ch4.add(playerArmy.getLabel(), new Integer(5));		       
									 addArmy(playerArmy);
									 playerArmy.setPosition(3, 12);
					             playerUI.lb.addArmyCount(3, 12);      
								  break;	 
								case 3:
			                   playerArmy.setBounds(playerUI.lb.getSettlerCount(1, 13) , playerUI.lb.getArmyCount(1, 13));    
							       playerUI.ch6.add(playerArmy.getLabel(), new Integer(5));		       
									 addArmy(playerArmy);
									 playerArmy.setPosition(1, 13);
					             playerUI.lb.addArmyCount(1, 13);      
								  break;	 
								case 4:
			                   playerArmy.setBounds(playerUI.lb.getSettlerCount(3, 13) , playerUI.lb.getArmyCount(3, 13));    
							       playerUI.ch8.add(playerArmy.getLabel(), new Integer(5));		       
									 addArmy(playerArmy);
									 playerArmy.setPosition(3, 13);
					             playerUI.lb.addArmyCount(3, 13);      
								  break;	 
								case 5:
			                   playerArmy.setBounds(playerUI.lb.getSettlerCount(0, 14) , playerUI.lb.getArmyCount(0, 14));    
							       playerUI.ch10.add(playerArmy.getLabel(), new Integer(5));		       
									 addArmy(playerArmy);
									 playerArmy.setPosition(0, 14);
					             playerUI.lb.addArmyCount(0, 14);      
								  break;	 
								case 6:
			                   playerArmy.setBounds(playerUI.lb.getSettlerCount(2, 14) , playerUI.lb.getArmyCount(2, 14));    
							       playerUI.ch11.add(playerArmy.getLabel(), new Integer(5));		       
									 addArmy(playerArmy);
									 playerArmy.setPosition(2, 14);
					             playerUI.lb.addArmyCount(2, 14);      
								  break;	 
								case 7:
			                   playerArmy.setBounds(playerUI.lb.getSettlerCount(3, 14) , playerUI.lb.getArmyCount(3, 14));    
							       playerUI.ch12.add(playerArmy.getLabel(), new Integer(5));		       
									 addArmy(playerArmy);
									 playerArmy.setPosition(3, 14);
					             playerUI.lb.addArmyCount(3, 14);      
								  break;	 
							 }					 
							 break;	 
						 case 2:
							 playerUI.lb.getHolder(0, 13).remove(armyButtons[0]);
						    playerUI.lb.getHolder(0, 13).repaint();
						    playerUI.lb.getHolder(1, 13).remove(armyButtons[1]);
						    playerUI.lb.getHolder(1, 13).repaint();
						    playerUI.lb.getHolder(2, 13).remove(armyButtons[2]);
						    playerUI.lb.getHolder(2, 13).repaint();
						    playerUI.lb.getHolder(0, 14).remove(armyButtons[3]);
						    playerUI.lb.getHolder(0, 14).repaint();
						    playerUI.lb.getHolder(2, 14).remove(armyButtons[4]);
						    playerUI.lb.getHolder(2, 14).repaint();
						    playerUI.lb.getHolder(0, 15).remove(armyButtons[5]);
						    playerUI.lb.getHolder(0, 15).repaint();
						    playerUI.lb.getHolder(2, 15).remove(armyButtons[6]);
						    playerUI.lb.getHolder(2, 15).repaint();
					       switch(k)
							 {
							   case 0:
			                   playerArmy.setBounds(playerUI.lb.getSettlerCount(0, 13) , playerUI.lb.getArmyCount(0, 13));    
							       playerUI.ch5.add(playerArmy.getLabel(), new Integer(5));		       
									 addArmy(playerArmy);
									 playerArmy.setPosition(0, 13);
									 playerUI.lb.addArmyCount(0, 13);      
	                         break;							
								case 1:
			                   playerArmy.setBounds(playerUI.lb.getSettlerCount(1, 13) , playerUI.lb.getArmyCount(1, 13));    
							       playerUI.ch6.add(playerArmy.getLabel(), new Integer(5));		       
									 addArmy(playerArmy);
									 playerArmy.setPosition(1, 13);
					             playerUI.lb.addArmyCount(1, 13);      
								  break;	 
								case 2:
			                   playerArmy.setBounds(playerUI.lb.getSettlerCount(2, 13) , playerUI.lb.getArmyCount(2, 13));    
							       playerUI.ch7.add(playerArmy.getLabel(), new Integer(5));		       
									 addArmy(playerArmy);
									 playerArmy.setPosition(2, 13);
					             playerUI.lb.addArmyCount(2, 13);      
								  break;	 
								case 3:
			                   playerArmy.setBounds(playerUI.lb.getSettlerCount(0, 14) , playerUI.lb.getArmyCount(0, 14));    
							       playerUI.ch9.add(playerArmy.getLabel(), new Integer(5));		       
									 addArmy(playerArmy);
									 playerArmy.setPosition(0, 14);
					             playerUI.lb.addArmyCount(0, 14);      
								  break;	 
								case 4:
			                   playerArmy.setBounds(playerUI.lb.getSettlerCount(1, 14) , playerUI.lb.getArmyCount(1, 14));    
							       playerUI.ch11.add(playerArmy.getLabel(), new Integer(5));		       
									 addArmy(playerArmy);
									 playerArmy.setPosition(1, 14);
					             playerUI.lb.addArmyCount(1, 14);      
								  break;	 
								case 5:
			                   playerArmy.setBounds(playerUI.lb.getSettlerCount(0, 15) , playerUI.lb.getArmyCount(0, 15));    
							       playerUI.ch13.add(playerArmy.getLabel(), new Integer(5));		       
									 addArmy(playerArmy);
									 playerArmy.setPosition(0, 15);
					             playerUI.lb.addArmyCount(0, 15);      
								  break;	 
								case 6:
			                   playerArmy.setBounds(playerUI.lb.getSettlerCount(2, 15) , playerUI.lb.getArmyCount(2, 15));    
							       playerUI.ch15.add(playerArmy.getLabel(), new Integer(5));		       
									 addArmy(playerArmy);
									 playerArmy.setPosition(2, 15);
					             playerUI.lb.addArmyCount(2, 15);      
								  break;	 
							 }					 
							 break;	 
						 case 3:
							 playerUI.lb.getHolder(1, 13).remove(armyButtons[0]);
						    playerUI.lb.getHolder(1, 13).repaint();
						    playerUI.lb.getHolder(2, 13).remove(armyButtons[1]);
						    playerUI.lb.getHolder(2, 13).repaint();
						    playerUI.lb.getHolder(3, 13).remove(armyButtons[2]);
						    playerUI.lb.getHolder(3, 13).repaint();
						    playerUI.lb.getHolder(1, 14).remove(armyButtons[3]);
						    playerUI.lb.getHolder(1, 14).repaint();
						    playerUI.lb.getHolder(3, 14).remove(armyButtons[4]);
						    playerUI.lb.getHolder(3, 14).repaint();
						    playerUI.lb.getHolder(2, 15).remove(armyButtons[5]);
						    playerUI.lb.getHolder(2, 15).repaint();
					       switch(k)
							 {
							   case 0:
			                   playerArmy.setBounds(playerUI.lb.getSettlerCount(1, 13) , playerUI.lb.getArmyCount(1, 13));    
							       playerUI.ch6.add(playerArmy.getLabel(), new Integer(5));		       
									 addArmy(playerArmy);
									 playerArmy.setPosition(1, 13);
									 playerUI.lb.addArmyCount(1, 13);      
	                         break;							
								case 1:
			                   playerArmy.setBounds(playerUI.lb.getSettlerCount(2, 13) , playerUI.lb.getArmyCount(2, 13));    
							       playerUI.ch7.add(playerArmy.getLabel(), new Integer(5));		       
									 addArmy(playerArmy);
									 playerArmy.setPosition(2, 13);
					             playerUI.lb.addArmyCount(2, 13);      
								  break;	 
								case 2:
			                   playerArmy.setBounds(playerUI.lb.getSettlerCount(3, 13) , playerUI.lb.getArmyCount(3, 13));    
							       playerUI.ch8.add(playerArmy.getLabel(), new Integer(5));		       
									 addArmy(playerArmy);
									 playerArmy.setPosition(3, 13);
					             playerUI.lb.addArmyCount(3, 13);      
								  break;	 
								case 3:
			                   playerArmy.setBounds(playerUI.lb.getSettlerCount(1, 14) , playerUI.lb.getArmyCount(1, 14));    
							       playerUI.ch10.add(playerArmy.getLabel(), new Integer(5));		       
									 addArmy(playerArmy);
									 playerArmy.setPosition(1, 14);
					             playerUI.lb.addArmyCount(1, 14);      
								  break;	 
								case 4:
			                   playerArmy.setBounds(playerUI.lb.getSettlerCount(3, 14) , playerUI.lb.getArmyCount(3, 14));    
							       playerUI.ch12.add(playerArmy.getLabel(), new Integer(5));		       
									 addArmy(playerArmy);
									 playerArmy.setPosition(3, 14);
					             playerUI.lb.addArmyCount(3, 14);      
								  break;	 
								case 5:
			                   playerArmy.setBounds(playerUI.lb.getSettlerCount(2, 15) , playerUI.lb.getArmyCount(2, 15));    
							       playerUI.ch15.add(playerArmy.getLabel(), new Integer(5));		       
									 addArmy(playerArmy);
									 playerArmy.setPosition(2, 15);
					             playerUI.lb.addArmyCount(2, 15);      
								  break;	 
	                   }
							 break;	 
					  }        
					}
            }else if(playerNumber == 1){
				   if(nation.equals("America")){
		              Army playerArmy = new Army(color, "1");
		              switch(startChoice){
						    case 0:
								 playerUI.lb.getHolder(4, 0).remove(armyButtons[0]);
							    playerUI.lb.getHolder(4, 0).repaint();
							    playerUI.lb.getHolder(6, 0).remove(armyButtons[1]);
							    playerUI.lb.getHolder(6, 0).repaint();
							    playerUI.lb.getHolder(4, 1).remove(armyButtons[2]);
							    playerUI.lb.getHolder(4, 1).repaint();
							    playerUI.lb.getHolder(6, 1).remove(armyButtons[3]);
							    playerUI.lb.getHolder(6, 1).repaint();
							    playerUI.lb.getHolder(5, 2).remove(armyButtons[4]);
							    playerUI.lb.getHolder(5, 2).repaint();
							    playerUI.lb.getHolder(6, 2).remove(armyButtons[5]);
							    playerUI.lb.getHolder(6, 2).repaint();
						       switch(k)
								 {
								   case 0:
			                      playerArmy.setBounds(playerUI.lb.getSettlerCount(4, 0) , playerUI.lb.getArmyCount(4, 0));    
								       playerUI.am1.add(playerArmy.getLabel(), new Integer(5));		       
										 addArmy(playerArmy);
										 playerArmy.setPosition(4, 0);
										 playerUI.lb.addArmyCount(4, 0);      
		                         break;							
									case 1:
			                      playerArmy.setBounds(playerUI.lb.getSettlerCount(6, 0) , playerUI.lb.getArmyCount(6, 0));    
								       playerUI.am3.add(playerArmy.getLabel(), new Integer(5));		       
										 addArmy(playerArmy);
										 playerArmy.setPosition(6, 0);
						             playerUI.lb.addArmyCount(6, 0);      
									  break;	 
									case 2:
			                      playerArmy.setBounds(playerUI.lb.getSettlerCount(4, 1) , playerUI.lb.getArmyCount(4, 1));    
								       playerUI.am5.add(playerArmy.getLabel(), new Integer(5));		       
										 addArmy(playerArmy);
										 playerArmy.setPosition(4, 1);
						             playerUI.lb.addArmyCount(4, 1);      
									  break;	 
									case 3:
								       playerUI.am7.add(playerArmy.getLabel(), new Integer(5));		       
										 addArmy(playerArmy);
										 playerArmy.setPosition(6, 1);
						             playerUI.lb.addArmyCount(6, 1);      
									  break;	 
									case 4:
			                      playerArmy.setBounds(playerUI.lb.getSettlerCount(5, 2) , playerUI.lb.getArmyCount(5, 2));    
								       playerUI.am10.add(playerArmy.getLabel(), new Integer(5));		       
										 addArmy(playerArmy);
										 playerArmy.setPosition(5, 2);
						             playerUI.lb.addArmyCount(5, 2);      
									  break;	 
									case 5:
			                      playerArmy.setBounds(playerUI.lb.getSettlerCount(6, 2) , playerUI.lb.getArmyCount(6, 2));    
								       playerUI.am11.add(playerArmy.getLabel(), new Integer(5));		       
										 addArmy(playerArmy);
										 playerArmy.setPosition(6, 2);
						             playerUI.lb.addArmyCount(6, 2);      
									  break;	 
								 }    
								 break;	 
							 case 1:
								 playerUI.lb.getHolder(6, 0).remove(armyButtons[0]);
							    playerUI.lb.getHolder(6, 0).repaint();
							    playerUI.lb.getHolder(5, 1).remove(armyButtons[1]);
							    playerUI.lb.getHolder(5, 1).repaint();
							    playerUI.lb.getHolder(7, 1).remove(armyButtons[2]);
							    playerUI.lb.getHolder(7, 1).repaint();
							    playerUI.lb.getHolder(5, 2).remove(armyButtons[3]);
							    playerUI.lb.getHolder(5, 2).repaint();
							    playerUI.lb.getHolder(6, 2).remove(armyButtons[4]);
							    playerUI.lb.getHolder(6, 2).repaint();
							    playerUI.lb.getHolder(7, 2).remove(armyButtons[5]);
							    playerUI.lb.getHolder(7, 2).repaint();
						       switch(k)
								 {
								   case 0:
			                      playerArmy.setBounds(playerUI.lb.getSettlerCount(6, 0) , playerUI.lb.getArmyCount(6, 0));    
								       playerUI.am3.add(playerArmy.getLabel(), new Integer(5));		       
										 addArmy(playerArmy);
										 playerArmy.setPosition(6, 0);
										 playerUI.lb.addArmyCount(6, 0);      
		                         break;							
									case 1:
			                      playerArmy.setBounds(playerUI.lb.getSettlerCount(5, 1) , playerUI.lb.getArmyCount(5, 1));    
								       playerUI.am6.add(playerArmy.getLabel(), new Integer(5));		       
										 addArmy(playerArmy);
										 playerArmy.setPosition(5, 1);
						             playerUI.lb.addArmyCount(5, 1);      
									  break;	 
									case 2:
			                      playerArmy.setBounds(playerUI.lb.getSettlerCount(7, 1) , playerUI.lb.getArmyCount(7, 1));    
								       playerUI.am8.add(playerArmy.getLabel(), new Integer(5));		       
										 addArmy(playerArmy);
										 playerArmy.setPosition(7, 1);
						             playerUI.lb.addArmyCount(7, 1);      
									  break;	 
									case 3:
			                      playerArmy.setBounds(playerUI.lb.getSettlerCount(5, 2) , playerUI.lb.getArmyCount(5, 2));    
								       playerUI.am10.add(playerArmy.getLabel(), new Integer(5));		       
										 addArmy(playerArmy);
										 playerArmy.setPosition(5, 2);
						             playerUI.lb.addArmyCount(5, 2);      
									  break;	 
									case 4:
			                      playerArmy.setBounds(playerUI.lb.getSettlerCount(6, 2) , playerUI.lb.getArmyCount(6, 2));    
								       playerUI.am11.add(playerArmy.getLabel(), new Integer(5));		       
										 addArmy(playerArmy);
										 playerArmy.setPosition(6, 2);
						             playerUI.lb.addArmyCount(6, 2);      
									  break;	 
									case 5:
			                      playerArmy.setBounds(playerUI.lb.getSettlerCount(7, 2) , playerUI.lb.getArmyCount(7, 2));    
								       playerUI.am12.add(playerArmy.getLabel(), new Integer(5));		       
										 addArmy(playerArmy);
										 playerArmy.setPosition(7, 2);
						             playerUI.lb.addArmyCount(7, 2);      
									  break;	 
								 }					 
								 break;	 
							 case 2:
								 playerUI.lb.getHolder(4, 1).remove(armyButtons[0]);
							    playerUI.lb.getHolder(4, 1).repaint();
							    playerUI.lb.getHolder(5, 1).remove(armyButtons[1]);
							    playerUI.lb.getHolder(5, 1).repaint();
							    playerUI.lb.getHolder(6, 1).remove(armyButtons[2]);
							    playerUI.lb.getHolder(6, 1).repaint();
							    playerUI.lb.getHolder(6, 2).remove(armyButtons[3]);
							    playerUI.lb.getHolder(6, 2).repaint();
							    playerUI.lb.getHolder(4, 3).remove(armyButtons[4]);
							    playerUI.lb.getHolder(4, 3).repaint();
							    playerUI.lb.getHolder(6, 3).remove(armyButtons[5]);
							    playerUI.lb.getHolder(6, 3).repaint();
						       switch(k)
								 {
								   case 0:
			                      playerArmy.setBounds(playerUI.lb.getSettlerCount(4, 1) , playerUI.lb.getArmyCount(4, 1));    
								       playerUI.am5.add(playerArmy.getLabel(), new Integer(5));		       
										 addArmy(playerArmy);
										 playerArmy.setPosition(4, 1);
										 playerUI.lb.addArmyCount(4, 1);      
		                         break;							
									case 1:
			                      playerArmy.setBounds(playerUI.lb.getSettlerCount(5, 1) , playerUI.lb.getArmyCount(5, 1));    
								       playerUI.am6.add(playerArmy.getLabel(), new Integer(5));		       
										 addArmy(playerArmy);
										 playerArmy.setPosition(5, 1);
						             playerUI.lb.addArmyCount(5, 1);      
									  break;	 
									case 2:
			                      playerArmy.setBounds(playerUI.lb.getSettlerCount(6, 1) , playerUI.lb.getArmyCount(6, 1));    
								       playerUI.am7.add(playerArmy.getLabel(), new Integer(5));		       
										 addArmy(playerArmy);
										 playerArmy.setPosition(6, 1);
						             playerUI.lb.addArmyCount(6, 1);      
									  break;	 
									case 3:
			                      playerArmy.setBounds(playerUI.lb.getSettlerCount(6, 2) , playerUI.lb.getArmyCount(6, 2));    
								       playerUI.am11.add(playerArmy.getLabel(), new Integer(5));		       
										 addArmy(playerArmy);
										 playerArmy.setPosition(6, 2);
						             playerUI.lb.addArmyCount(6, 2);      
									  break;	 
									case 4:
			                      playerArmy.setBounds(playerUI.lb.getSettlerCount(4, 3) , playerUI.lb.getArmyCount(4, 3));    
								       playerUI.am13.add(playerArmy.getLabel(), new Integer(5));		       
										 addArmy(playerArmy);
										 playerArmy.setPosition(4, 3);
						             playerUI.lb.addArmyCount(4, 3);      
									  break;	 
									case 5:
			                      playerArmy.setBounds(playerUI.lb.getSettlerCount(6, 3) , playerUI.lb.getArmyCount(6, 3));    
								       playerUI.am15.add(playerArmy.getLabel(), new Integer(5));		       
										 addArmy(playerArmy);
										 playerArmy.setPosition(6, 3);
						             playerUI.lb.addArmyCount(6, 3);      
									  break;	 
								 }					 
								 break;	 
							 case 3:
								 playerUI.lb.getHolder(5, 1).remove(armyButtons[0]);
							    playerUI.lb.getHolder(5, 1).repaint();
							    playerUI.lb.getHolder(6, 1).remove(armyButtons[1]);
							    playerUI.lb.getHolder(6, 1).repaint();
							    playerUI.lb.getHolder(7, 1).remove(armyButtons[2]);
							    playerUI.lb.getHolder(7, 1).repaint();
							    playerUI.lb.getHolder(5, 2).remove(armyButtons[3]);
							    playerUI.lb.getHolder(5, 2).repaint();
							    playerUI.lb.getHolder(7, 2).remove(armyButtons[4]);
							    playerUI.lb.getHolder(7, 2).repaint();
							    playerUI.lb.getHolder(6, 3).remove(armyButtons[5]);
							    playerUI.lb.getHolder(6, 3).repaint();
							    playerUI.lb.getHolder(7, 3).remove(armyButtons[6]);
							    playerUI.lb.getHolder(7, 3).repaint();
						       switch(k)
								 {
								   case 0:
			                      playerArmy.setBounds(playerUI.lb.getSettlerCount(5, 1) , playerUI.lb.getArmyCount(5, 1));    
								       playerUI.am6.add(playerArmy.getLabel(), new Integer(5));		       
										 addArmy(playerArmy);
										 playerArmy.setPosition(5, 1);
										 playerUI.lb.addArmyCount(5, 1);      
		                         break;							
									case 1:
			                      playerArmy.setBounds(playerUI.lb.getSettlerCount(6, 1) , playerUI.lb.getArmyCount(6, 1));    
								       playerUI.am7.add(playerArmy.getLabel(), new Integer(5));		       
										 addArmy(playerArmy);
										 playerArmy.setPosition(6, 1);
						             playerUI.lb.addArmyCount(6, 1);      
									  break;	 
									case 2:
			                      playerArmy.setBounds(playerUI.lb.getSettlerCount(7, 1) , playerUI.lb.getArmyCount(7, 1));    
								       playerUI.am8.add(playerArmy.getLabel(), new Integer(5));		       
										 addArmy(playerArmy);
										 playerArmy.setPosition(7, 1);
						             playerUI.lb.addArmyCount(7, 1);      
									  break;	 
									case 3:
			                      playerArmy.setBounds(playerUI.lb.getSettlerCount(5, 2) , playerUI.lb.getArmyCount(5, 2));    
								       playerUI.am10.add(playerArmy.getLabel(), new Integer(5));		       
										 addArmy(playerArmy);
										 playerArmy.setPosition(5, 2);
						             playerUI.lb.addArmyCount(5, 2);      
									  break;	 
									case 4:
			                      playerArmy.setBounds(playerUI.lb.getSettlerCount(7, 2) , playerUI.lb.getArmyCount(7, 2));    
								       playerUI.am12.add(playerArmy.getLabel(), new Integer(5));		       
										 addArmy(playerArmy);
										 playerArmy.setPosition(7, 2);
						             playerUI.lb.addArmyCount(7, 2);      
									  break;	 
									case 5:
			                      playerArmy.setBounds(playerUI.lb.getSettlerCount(6, 3) , playerUI.lb.getArmyCount(6, 3));    
								       playerUI.am15.add(playerArmy.getLabel(), new Integer(5));		       
										 addArmy(playerArmy);
										 playerArmy.setPosition(6, 3);
						             playerUI.lb.addArmyCount(6, 3);      
									  break;	 
									case 6:
			                      playerArmy.setBounds(playerUI.lb.getSettlerCount(7, 3) , playerUI.lb.getArmyCount(7, 3));    
								       playerUI.am16.add(playerArmy.getLabel(), new Integer(5));		       
										 addArmy(playerArmy);
										 playerArmy.setPosition(7, 3);
						             playerUI.lb.addArmyCount(7, 3);      
									  break;	 
		                   }
								 break;	 
						 }        
					 }
				}
//            playerBoard.turnHolder.removeAll();    
		        playerBoard.turnHolder.remove(select);
		        playerBoard.turnHolder.repaint();
				      
				  select = new GameInternalFrame();
			     select.setBackground(playerColor); 
			     select.setBorder(new BevelBorder(1, new Color(0, 0, 0), new Color(255, 255, 255)));
		        select.setPreferredSize(new Dimension(198, 198));
		
				  GameJLabel pick = new GameJLabel(" Waiting for setup");
				  GameJLabel pick2 = new GameJLabel("  to complete");
		        pick.setBounds(00, 50, 190, 20);
		         if(color.equals("Blue")){
						pick.setForeground(new Color(250, 250, 250));			 
		         }else{
						pick.setForeground(new Color(50, 50, 50));			 
					}
		         pick2.setBounds(00, 70, 190, 20);
		         if(color.equals("Blue")){
						pick2.setForeground(new Color(250, 250, 250));			 
			      }else{
						pick2.setForeground(new Color(50, 50, 50));			 
					}
				  Font f = new Font("Monospaced", Font.BOLD, 13);
		        pick.setFont(f); 
		        pick2.setFont(f); 
		
			     select.add(pick);
			     select.add(pick2);
		        select.show();
					
		        playerBoard.turnHolder.add(select);
				    
			     select.setMaximum(true);
					
System.out.println("starting player ready\n");
				  playerUI.playerReady(this);
			 }	
		  }
      }
		    
	   public void mouseEntered(MouseEvent e)
      {
         for(int k = 0; k < 36; k++)
         {
            if(techListener[k] != null && e.getSource() == techListener[k].label)
            {
               playerUI.blowUpIcon(techListener[k].icon, techListener[k].gold, this);
//               System.out.println("YES!");
            }
         }
         for(int k = 0; k < 12; k++)
         {
            if(wonderListener[k] != null && e.getSource() == wonderListener[k])
            {
               switch(k)
               {
                  case 0:
                     playerUI.blowUpIcon(535, 470, getImage("data/stonehengeCard.png"), this);
                     break;
                  case 1:
                     playerUI.blowUpIcon(535, 470, getImage("data/colossusCard.png"), this);
                     break;
                  case 2:
                     playerUI.blowUpIcon(535, 470, getImage("data/hanginggardensCard.png"), this);
                     break;
                  case 3:
                     playerUI.blowUpIcon(535, 470, getImage("data/oracleCard.png"), this);
                     break;
                  case 4:
                     playerUI.blowUpIcon(535, 470, getImage("data/towerCard.png"), this);
                     break;
                  case 5:
                     playerUI.blowUpIcon(535, 470, getImage("data/angkorwatCard.png"), this);
                     break;
                  case 6:
                     playerUI.blowUpIcon(535, 470, getImage("data/castleCard.png"), this);
                     break;
                  case 7:
                     playerUI.blowUpIcon(535, 470, getImage("data/louvreCard.png"), this);
                     break;
                  case 8:
                     playerUI.blowUpIcon(535, 470, getImage("data/panamaCard.png"), this);
                     break;
                  case 9:
                     playerUI.blowUpIcon(535, 470, getImage("data/statueCard.png"), this);
                     break;
                  case 10:
                     playerUI.blowUpIcon(535, 470, getImage("data/operaCard.png"), this);
                     break;
                  case 11:
                     playerUI.blowUpIcon(535, 470, getImage("data/unitednationsCard.png"), this);
                     break;
               }
 //              System.out.println(wonderListener[k]);
            }
         }
         for(int k = 0; k < 4; k++)
         {
            if(availableWonders[k] != null && e.getSource() == availableWonders[k].getLabel())
            {
               playerUI.blowUpIcon(120, 72+(k*67), availableWonders[k].getBlowupIcon(), this);
//               System.out.println("YES WONDERS!");
            }
         }
      }//end mouseEntered
      public void mouseClicked(MouseEvent e)
      {
         if(techListener[30] != null && e.getSource() == techListener[30])
         {
 //           System.out.println("Metalworking"); 
         //battle
         }
	      if(e.getSource() == playerCity && e.getClickCount() == 1)
			{
		     
			  JOptionPane info = new JOptionPane();
			  GameImageIcon icon = getImage("data/" + nation +"Icon.png");
			  info.setIcon(icon);
			  info.showMessageDialog(playerUI.c, "Production: "+ getCityProduction(0)+
			                            "\nTrade: " + getCityTrade(0),
	                                  "Capitol", JOptionPane.INFORMATION_MESSAGE, icon);
			}  
      }//end mouseEntered
      public void mousePressed(MouseEvent e){}//end mousePressed
      public void mouseReleased(MouseEvent e){}//end mouseReleased
      public void mouseExited(MouseEvent e)
      {
         for(int k = 0; k < 36; k++)
         {
            if(techListener[k] != null && e.getSource() == techListener[k].label)
            {
               playerUI.blowDownIcon(techListener[k].gold, this);
//               System.out.println("DOWN!");
            }
         }
         for(int k = 0; k < 12; k++)
         {
            if(wonderListener[k] != null && e.getSource() == wonderListener[k])
            {
               playerUI.blowDownIcon(this);
            //	      System.out.println("DOWN!");
            }
         }
         for(int k = 0; k < 4; k++)
         {
            if(availableWonders[k] != null && e.getSource() == availableWonders[k].getLabel())
            {
               playerUI.blowDownIcon(this);
//               System.out.println("DOWN WONDERS!");
            }
         }
      }//end mouseExited
 
      public CivGUI getPlayerUI(){
		   return playerUI;
		}
		
      private void startCity()
		{
         select = new GameInternalFrame("Setup");
         select.setBackground(playerColor); 
	      select.setBorder(new BevelBorder(1, new Color(0, 0, 0), new Color(255, 255, 255)));
         select.setPreferredSize(new Dimension(198, 198));

	      startChoice = -1; 
		   cityButtons = new GameJButton[4];
			 
			GameJLabel pick = new GameJLabel("   Pick starting city");
			GameJLabel pick2 = new GameJLabel("    placement on map");
         pick.setBounds(00, 50, 190, 20);
         if(color.equals("Blue")){
				pick.setForeground(new Color(250, 250, 250));			 
         }else{
				pick.setForeground(new Color(50, 50, 50));			 
			}
         pick2.setBounds(00, 70, 190, 20);
         if(color.equals("Blue")){
				pick2.setForeground(new Color(250, 250, 250));			 
	      }else{
				pick2.setForeground(new Color(50, 50, 50));			 
			}
			Font f = new Font("Monospaced", Font.BOLD, 13);
         pick.setFont(f); 
         pick2.setFont(f); 
			
			select.add(pick);
	      select.add(pick2);
	      select.show();

		   if(playerNumber == 1){
			   if(nation.equals("America")){
//               Player[] temp = CivGUI.getPlayerList();          
System.out.println("AMERICA INITMAP");
               playerUI.initPlayerMap("America", this, 4);
				
				   GameImageIcon icon6 = getImage("data/am6rev.png");
			      GameImageIcon icon7 = getImage("data/am7rev.png");
			      GameImageIcon icon10 = getImage("data/am10rev.png");
			      GameImageIcon icon11 = getImage("data/am11rev.png");
	
					cityButtons[0] = new GameJButton(icon11);
				   cityButtons[0].setBounds(0, 0, 55, 55);
				   cityButtons[0].setBackground(playerColor);
				   cityButtons[0].addActionListener(this);
					cityButtons[1] = new GameJButton(icon10);
					cityButtons[1].setBounds(0, 0, 55, 55);
				   cityButtons[1].setBackground(playerColor);
				   cityButtons[1].addActionListener(this);
					cityButtons[2] = new GameJButton(icon7);
					cityButtons[2].setBounds(0, 0, 55, 55);
				   cityButtons[2].setBackground(playerColor);
				   cityButtons[2].addActionListener(this);
					cityButtons[3] = new GameJButton(icon6);
					cityButtons[3].setBounds(0, 0, 55, 55);
				   cityButtons[3].setBackground(playerColor);
				   cityButtons[3].addActionListener(this);
		         
					playerUI.lb.getHolder(5, 1).add(cityButtons[0], new Integer(50));
				   playerUI.lb.getHolder(5, 1).repaint();
				   playerUI.lb.getHolder(6, 1).add(cityButtons[1], new Integer(50));
				   playerUI.lb.getHolder(6, 1).repaint();
				   playerUI.lb.getHolder(5, 2).add(cityButtons[2], new Integer(50));
				   playerUI.lb.getHolder(5, 2).repaint();
				   playerUI.lb.getHolder(6, 2).add(cityButtons[3], new Integer(50));
				   playerUI.lb.getHolder(6, 2).repaint();
			   }
			}else if (playerNumber == 0){  
				if(nation.equals("China"))  
		      {
//             Player[] temp = CivGUI.getPlayerList();          
System.out.println("CHINA INITMAP");
             playerUI.initPlayerMap("China", this, 3);
	
				 GameImageIcon icon6 = getImage("data/ch6.png");
		       GameImageIcon icon7 = getImage("data/ch7.png");
		       GameImageIcon icon10 = getImage("data/ch10.png");
		       GameImageIcon icon11 = getImage("data/ch11.png");
	
				 cityButtons[0] = new GameJButton(icon6);
			    cityButtons[0].setBounds(0, 0, 55, 55);
			    cityButtons[0].setBackground(playerColor);
			    cityButtons[0].addActionListener(this);
				 cityButtons[1] = new GameJButton(icon7);
				 cityButtons[1].setBounds(0, 0, 55, 55);
			    cityButtons[1].setBackground(playerColor);
			    cityButtons[1].addActionListener(this);
				 cityButtons[2] = new GameJButton(icon10);
				 cityButtons[2].setBounds(0, 0, 55, 55);
			    cityButtons[2].setBackground(playerColor);
			    cityButtons[2].addActionListener(this);
				 cityButtons[3] = new GameJButton(icon11);
				 cityButtons[3].setBounds(0, 0, 55, 55);
			    cityButtons[3].setBackground(playerColor);
			    cityButtons[3].addActionListener(this);
	         
	//System.out.println(playerUI.lb.getHolder(1, 13));			   
				 playerUI.lb.getHolder(1, 13).add(cityButtons[0], new Integer(50));
			    playerUI.lb.getHolder(1, 13).repaint();
			    playerUI.lb.getHolder(2, 13).add(cityButtons[1], new Integer(50));
			    playerUI.lb.getHolder(2, 13).repaint();
			    playerUI.lb.getHolder(1, 14).add(cityButtons[2], new Integer(50));
			    playerUI.lb.getHolder(1, 14).repaint();
			    playerUI.lb.getHolder(2, 14).add(cityButtons[3], new Integer(50));
			    playerUI.lb.getHolder(2, 14).repaint();
	    
	//	       playerBoard.turnHolder.add(cityButtons[0]);
	//	       playerBoard.turnHolder.add(cityButtons[1]);
	//	       playerBoard.turnHolder.add(cityButtons[2]);
	//	       playerBoard.turnHolder.add(cityButtons[3]);
				}	    
			} 
//    	    Vector legalSpace = new Vector(0, 1); 
//          Vector icons = new Vector(0, 1);

//		    legalSpace.add(playerUI.lb.getHolder(1, 13));
//System.out.println(playerUI.lb.getHolder(1, 13));			   
//		    legalSpace.add(playerUI.lb.getHolder(2, 13));
//		    legalSpace.add(playerUI.lb.getHolder(1, 14));
//		    legalSpace.add(playerUI.lb.getHolder(2, 14));


          playerBoard.turnHolder.add(select);
			 
//		    playerBoard.turnHolder.add(select);
		    
	       select.setMaximum(true);
//          playerBoard.turnHolder.validate();
//          playerBoard.turnHolder.repaint();
System.out.println("CHINA SUCCESS");
		}      

      private void startSettler(){
 		  armyButtons = new GameJButton[8];

        playerBoard.turnHolder.remove(select);
        playerBoard.turnHolder.repaint();
		      
		  select = new GameInternalFrame("Setup");
	     select.setBackground(playerColor); 
	     select.setBorder(new BevelBorder(1, new Color(0, 0, 0), new Color(255, 255, 255)));
        select.setPreferredSize(new Dimension(198, 198));

		  GameJLabel pick = new GameJLabel(" Pick starting Settler");
		  GameJLabel pick2 = new GameJLabel("  placement on map");
        pick.setBounds(00, 50, 190, 20);
         if(color.equals("Blue")){
				pick.setForeground(new Color(250, 250, 250));			 
         }else{
				pick.setForeground(new Color(50, 50, 50));			 
			}
         pick2.setBounds(00, 70, 190, 20);
         if(color.equals("Blue")){
				pick2.setForeground(new Color(250, 250, 250));			 
	      }else{
				pick2.setForeground(new Color(50, 50, 50));			 
			}
		  Font f = new Font("Monospaced", Font.BOLD, 13);
        pick.setFont(f); 
        pick2.setFont(f); 

	     select.add(pick);
	     select.add(pick2);
        select.show();

		  if(playerNumber == 1){
	        if(nation.equals("America")){

			     GameImageIcon icon1 = getImage("data/am16rev.png");
			     GameImageIcon icon2 = getImage("data/am15rev.png");
			     GameImageIcon icon3 = getImage("data/am14rev.png");
			     GameImageIcon icon4 = getImage("data/am13rev.png");
			     GameImageIcon icon5 = getImage("data/am12rev.png");
			     GameImageIcon icon6 = getImage("data/am11rev.png");
			     GameImageIcon icon7 = getImage("data/am10rev.png");
			     GameImageIcon icon8 = getImage("data/am9rev.png");
			     GameImageIcon icon9 = getImage("data/am8rev.png");
			     GameImageIcon icon10 = getImage("data/am7rev.png");
			     GameImageIcon icon11 = getImage("data/am6rev.png");
			     GameImageIcon icon12 = getImage("data/am5rev.png");
			     GameImageIcon icon13 = getImage("data/am4rev.png");
			     GameImageIcon icon14 = getImage("data/am3rev.png");
			     GameImageIcon icon15 = getImage("data/am2rev.png");
			     GameImageIcon icon16 = getImage("data/am1rev.png");
	
				  switch(startChoice)
				  {
				    case 0:
						 settlerButtons = new GameJButton[8];
			
						 settlerButtons[0] = new GameJButton(icon1);
					    settlerButtons[0].setBounds(0, 0, 55, 55);
					    settlerButtons[0].addActionListener(this);
						 settlerButtons[1] = new GameJButton(icon3);
						 settlerButtons[1].setBounds(0, 0, 55, 55);
					    settlerButtons[1].addActionListener(this);
						 settlerButtons[2] = new GameJButton(icon5);
						 settlerButtons[2].setBounds(0, 0, 55, 55);
					    settlerButtons[2].addActionListener(this);
						 settlerButtons[3] = new GameJButton(icon7);
						 settlerButtons[3].setBounds(0, 0, 55, 55);
					    settlerButtons[3].addActionListener(this);
						 settlerButtons[4] = new GameJButton(icon10);
					    settlerButtons[4].setBounds(0, 0, 55, 55);
					    settlerButtons[4].addActionListener(this);
						 settlerButtons[5] = new GameJButton(icon11);
					    settlerButtons[5].setBounds(0, 0, 55, 55);
					    settlerButtons[5].addActionListener(this);
			
						 playerUI.lb.getHolder(4, 0).add(settlerButtons[0], new Integer(50));
					    playerUI.lb.getHolder(4, 0).repaint();
					    playerUI.lb.getHolder(6, 0).add(settlerButtons[1], new Integer(50));
					    playerUI.lb.getHolder(6, 0).repaint();
					    playerUI.lb.getHolder(4, 1).add(settlerButtons[2], new Integer(50));
					    playerUI.lb.getHolder(4, 1).repaint();
					    playerUI.lb.getHolder(6, 1).add(settlerButtons[3], new Integer(50));
					    playerUI.lb.getHolder(6, 1).repaint();
					    playerUI.lb.getHolder(5, 2).add(settlerButtons[4], new Integer(50));
					    playerUI.lb.getHolder(5, 2).repaint();
					    playerUI.lb.getHolder(6, 2).add(settlerButtons[5], new Integer(50));
					    playerUI.lb.getHolder(6, 2).repaint();
				       break;	 
					 case 1:
						 settlerButtons = new GameJButton[8];
			
						 settlerButtons[0] = new GameJButton(icon3);
					    settlerButtons[0].setBounds(0, 0, 55, 55);
					    settlerButtons[0].addActionListener(this);
						 settlerButtons[1] = new GameJButton(icon6);
						 settlerButtons[1].setBounds(0, 0, 55, 55);
					    settlerButtons[1].addActionListener(this);
						 settlerButtons[2] = new GameJButton(icon8);
						 settlerButtons[2].setBounds(0, 0, 55, 55);
					    settlerButtons[2].addActionListener(this);
						 settlerButtons[3] = new GameJButton(icon10);
						 settlerButtons[3].setBounds(0, 0, 55, 55);
					    settlerButtons[3].addActionListener(this);
						 settlerButtons[4] = new GameJButton(icon11);
					    settlerButtons[4].setBounds(0, 0, 55, 55);
					    settlerButtons[4].addActionListener(this);
						 settlerButtons[5] = new GameJButton(icon12);
					    settlerButtons[5].setBounds(0, 0, 55, 55);
					    settlerButtons[5].addActionListener(this);
			
						 playerUI.lb.getHolder(6, 0).add(settlerButtons[0], new Integer(50));
					    playerUI.lb.getHolder(6, 0).repaint();
					    playerUI.lb.getHolder(5, 1).add(settlerButtons[1], new Integer(50));
					    playerUI.lb.getHolder(5, 1).repaint();
					    playerUI.lb.getHolder(7, 1).add(settlerButtons[2], new Integer(50));
					    playerUI.lb.getHolder(7, 1).repaint();
					    playerUI.lb.getHolder(5, 2).add(settlerButtons[3], new Integer(50));
					    playerUI.lb.getHolder(5, 2).repaint();
					    playerUI.lb.getHolder(6, 2).add(settlerButtons[4], new Integer(50));
					    playerUI.lb.getHolder(6, 2).repaint();
					    playerUI.lb.getHolder(7, 2).add(settlerButtons[5], new Integer(50));
					    playerUI.lb.getHolder(7, 2).repaint();
				       break;	 
					 case 2:
						 settlerButtons = new GameJButton[8];
						 
						 settlerButtons[0] = new GameJButton(icon5);
					    settlerButtons[0].setBounds(0, 0, 55, 55);
					    settlerButtons[0].addActionListener(this);
						 settlerButtons[1] = new GameJButton(icon6);
						 settlerButtons[1].setBounds(0, 0, 55, 55);
					    settlerButtons[1].addActionListener(this);
						 settlerButtons[2] = new GameJButton(icon7);
						 settlerButtons[2].setBounds(0, 0, 55, 55);
					    settlerButtons[2].addActionListener(this);
						 settlerButtons[3] = new GameJButton(icon11);
						 settlerButtons[3].setBounds(0, 0, 55, 55);
					    settlerButtons[3].addActionListener(this);
						 settlerButtons[4] = new GameJButton(icon13);
					    settlerButtons[4].setBounds(0, 0, 55, 55);
					    settlerButtons[4].addActionListener(this);
						 settlerButtons[5] = new GameJButton(icon15);
					    settlerButtons[5].setBounds(0, 0, 55, 55);
					    settlerButtons[5].addActionListener(this);
			
						 playerUI.lb.getHolder(4, 1).add(settlerButtons[0], new Integer(50));
					    playerUI.lb.getHolder(4, 1).repaint();
					    playerUI.lb.getHolder(5, 1).add(settlerButtons[1], new Integer(50));
					    playerUI.lb.getHolder(5, 1).repaint();
					    playerUI.lb.getHolder(6, 1).add(settlerButtons[2], new Integer(50));
					    playerUI.lb.getHolder(6, 1).repaint();
					    playerUI.lb.getHolder(6, 2).add(settlerButtons[3], new Integer(50));
					    playerUI.lb.getHolder(6, 2).repaint();
					    playerUI.lb.getHolder(4, 3).add(settlerButtons[4], new Integer(50));
					    playerUI.lb.getHolder(4, 3).repaint();
					    playerUI.lb.getHolder(6, 3).add(settlerButtons[5], new Integer(50));
					    playerUI.lb.getHolder(6, 3).repaint();
				       break;	 
					 case 3:
						 settlerButtons = new GameJButton[8];
			
						 settlerButtons[0] = new GameJButton(icon6);
					    settlerButtons[0].setBounds(0, 0, 55, 55);
					    settlerButtons[0].addActionListener(this);
						 settlerButtons[1] = new GameJButton(icon7);
						 settlerButtons[1].setBounds(0, 0, 55, 55);
					    settlerButtons[1].addActionListener(this);
						 settlerButtons[2] = new GameJButton(icon8);
						 settlerButtons[2].setBounds(0, 0, 55, 55);
					    settlerButtons[2].addActionListener(this);
						 settlerButtons[3] = new GameJButton(icon10);
						 settlerButtons[3].setBounds(0, 0, 55, 55);
					    settlerButtons[3].addActionListener(this);
						 settlerButtons[4] = new GameJButton(icon12);
					    settlerButtons[4].setBounds(0, 0, 55, 55);
					    settlerButtons[4].addActionListener(this);
						 settlerButtons[5] = new GameJButton(icon15);
					    settlerButtons[5].setBounds(0, 0, 55, 55);
					    settlerButtons[5].addActionListener(this);
						 settlerButtons[6] = new GameJButton(icon16);
					    settlerButtons[6].setBounds(0, 0, 55, 55);
					    settlerButtons[6].addActionListener(this);
			
						 playerUI.lb.getHolder(5, 1).add(settlerButtons[0], new Integer(50));
					    playerUI.lb.getHolder(5, 1).repaint();
					    playerUI.lb.getHolder(6, 1).add(settlerButtons[1], new Integer(50));
					    playerUI.lb.getHolder(6, 1).repaint();
					    playerUI.lb.getHolder(7, 1).add(settlerButtons[2], new Integer(50));
					    playerUI.lb.getHolder(7, 1).repaint();
					    playerUI.lb.getHolder(5, 2).add(settlerButtons[3], new Integer(50));
					    playerUI.lb.getHolder(5, 2).repaint();
					    playerUI.lb.getHolder(7, 2).add(settlerButtons[4], new Integer(50));
					    playerUI.lb.getHolder(7, 2).repaint();
					    playerUI.lb.getHolder(6, 3).add(settlerButtons[5], new Integer(50));
					    playerUI.lb.getHolder(6, 3).repaint();
					    playerUI.lb.getHolder(7, 3).add(settlerButtons[6], new Integer(50));
					    playerUI.lb.getHolder(7, 3).repaint();
				       break;	 
				  }
	        }
		  }else if(playerNumber == 0){
		     if(nation.equals("China")){
			     GameImageIcon icon1 = getImage("data/ch1.png");
		        GameImageIcon icon2 = getImage("data/ch2.png");
		        GameImageIcon icon3 = getImage("data/ch3.png");
		        GameImageIcon icon4 = getImage("data/ch4.png");
				  GameImageIcon icon5 = getImage("data/ch5.png");
				  GameImageIcon icon6 = getImage("data/ch6.png");
		        GameImageIcon icon7 = getImage("data/ch7.png");
		        GameImageIcon icon8 = getImage("data/ch8.png");
		        GameImageIcon icon9 = getImage("data/ch9.png");
		        GameImageIcon icon10 = getImage("data/ch10.png");
		        GameImageIcon icon11 = getImage("data/ch11.png");
		        GameImageIcon icon12 = getImage("data/ch12.png");
		        GameImageIcon icon13 = getImage("data/ch13.png");
		        GameImageIcon icon14 = getImage("data/ch14.png");
		        GameImageIcon icon15 = getImage("data/ch15.png");
		        GameImageIcon icon16 = getImage("data/ch16.png");
	
				  switch(startChoice)
				  {
				    case 0:
						 settlerButtons = new GameJButton[8];
			
						 settlerButtons[0] = new GameJButton(icon1);
					    settlerButtons[0].setBounds(0, 0, 55, 55);
					    settlerButtons[0].addActionListener(this);
						 settlerButtons[1] = new GameJButton(icon2);
						 settlerButtons[1].setBounds(0, 0, 55, 55);
					    settlerButtons[1].addActionListener(this);
						 settlerButtons[2] = new GameJButton(icon3);
						 settlerButtons[2].setBounds(0, 0, 55, 55);
					    settlerButtons[2].addActionListener(this);
						 settlerButtons[3] = new GameJButton(icon5);
						 settlerButtons[3].setBounds(0, 0, 55, 55);
					    settlerButtons[3].addActionListener(this);
						 settlerButtons[4] = new GameJButton(icon7);
					    settlerButtons[4].setBounds(0, 0, 55, 55);
					    settlerButtons[4].addActionListener(this);
						 settlerButtons[5] = new GameJButton(icon9);
					    settlerButtons[5].setBounds(0, 0, 55, 55);
					    settlerButtons[5].addActionListener(this);
						 settlerButtons[6] = new GameJButton(icon10);
					    settlerButtons[6].setBounds(0, 0, 55, 55);
					    settlerButtons[6].addActionListener(this);
						 settlerButtons[7] = new GameJButton(icon11);
					    settlerButtons[7].setBounds(0, 0, 55, 55);
					    settlerButtons[7].addActionListener(this);
			
			//System.out.println(playerUI.lb.getHolder(1, 13));			   
						 playerUI.lb.getHolder(0, 12).add(settlerButtons[0], new Integer(50));
					    playerUI.lb.getHolder(0, 12).repaint();
					    playerUI.lb.getHolder(1, 12).add(settlerButtons[1], new Integer(50));
					    playerUI.lb.getHolder(1, 12).repaint();
					    playerUI.lb.getHolder(2, 12).add(settlerButtons[2], new Integer(50));
					    playerUI.lb.getHolder(2, 12).repaint();
					    playerUI.lb.getHolder(0, 13).add(settlerButtons[3], new Integer(50));
					    playerUI.lb.getHolder(0, 13).repaint();
					    playerUI.lb.getHolder(2, 13).add(settlerButtons[4], new Integer(50));
					    playerUI.lb.getHolder(2, 13).repaint();
					    playerUI.lb.getHolder(0, 14).add(settlerButtons[5], new Integer(50));
					    playerUI.lb.getHolder(0, 14).repaint();
					    playerUI.lb.getHolder(1, 14).add(settlerButtons[6], new Integer(50));
					    playerUI.lb.getHolder(1, 14).repaint();
					    playerUI.lb.getHolder(2, 14).add(settlerButtons[7], new Integer(50));
					    playerUI.lb.getHolder(2, 14).repaint();
				       break;	 
					 case 1:
						 settlerButtons = new GameJButton[8];
			
						 settlerButtons[0] = new GameJButton(icon2);
					    settlerButtons[0].setBounds(0, 0, 55, 55);
					    settlerButtons[0].addActionListener(this);
						 settlerButtons[1] = new GameJButton(icon3);
						 settlerButtons[1].setBounds(0, 0, 55, 55);
					    settlerButtons[1].addActionListener(this);
						 settlerButtons[2] = new GameJButton(icon4);
						 settlerButtons[2].setBounds(0, 0, 55, 55);
					    settlerButtons[2].addActionListener(this);
						 settlerButtons[3] = new GameJButton(icon6);
						 settlerButtons[3].setBounds(0, 0, 55, 55);
					    settlerButtons[3].addActionListener(this);
						 settlerButtons[4] = new GameJButton(icon8);
					    settlerButtons[4].setBounds(0, 0, 55, 55);
					    settlerButtons[4].addActionListener(this);
						 settlerButtons[5] = new GameJButton(icon10);
					    settlerButtons[5].setBounds(0, 0, 55, 55);
					    settlerButtons[5].addActionListener(this);
						 settlerButtons[6] = new GameJButton(icon11);
					    settlerButtons[6].setBounds(0, 0, 55, 55);
					    settlerButtons[6].addActionListener(this);
						 settlerButtons[7] = new GameJButton(icon12);
					    settlerButtons[7].setBounds(0, 0, 55, 55);
					    settlerButtons[7].addActionListener(this);
			
			//System.out.println(playerUI.lb.getHolder(1, 13));			   
						 playerUI.lb.getHolder(1, 12).add(settlerButtons[0], new Integer(50));
					    playerUI.lb.getHolder(1, 12).repaint();
					    playerUI.lb.getHolder(2, 12).add(settlerButtons[1], new Integer(50));
					    playerUI.lb.getHolder(2, 12).repaint();
					    playerUI.lb.getHolder(3, 12).add(settlerButtons[2], new Integer(50));
					    playerUI.lb.getHolder(3, 12).repaint();
					    playerUI.lb.getHolder(1, 13).add(settlerButtons[3], new Integer(50));
					    playerUI.lb.getHolder(1, 13).repaint();
					    playerUI.lb.getHolder(3, 13).add(settlerButtons[4], new Integer(50));
					    playerUI.lb.getHolder(3, 13).repaint();
					    playerUI.lb.getHolder(1, 14).add(settlerButtons[5], new Integer(50));
					    playerUI.lb.getHolder(1, 14).repaint();
					    playerUI.lb.getHolder(2, 14).add(settlerButtons[6], new Integer(50));
					    playerUI.lb.getHolder(2, 14).repaint();
					    playerUI.lb.getHolder(3, 14).add(settlerButtons[7], new Integer(50));
					    playerUI.lb.getHolder(3, 14).repaint();
				       break;	 
					 case 2:
						 settlerButtons = new GameJButton[8];
						 
						 settlerButtons[0] = new GameJButton(icon5);
					    settlerButtons[0].setBounds(0, 0, 55, 55);
					    settlerButtons[0].addActionListener(this);
						 settlerButtons[1] = new GameJButton(icon6);
						 settlerButtons[1].setBounds(0, 0, 55, 55);
					    settlerButtons[1].addActionListener(this);
						 settlerButtons[2] = new GameJButton(icon7);
						 settlerButtons[2].setBounds(0, 0, 55, 55);
					    settlerButtons[2].addActionListener(this);
						 settlerButtons[3] = new GameJButton(icon9);
						 settlerButtons[3].setBounds(0, 0, 55, 55);
					    settlerButtons[3].addActionListener(this);
						 settlerButtons[4] = new GameJButton(icon11);
					    settlerButtons[4].setBounds(0, 0, 55, 55);
					    settlerButtons[4].addActionListener(this);
						 settlerButtons[5] = new GameJButton(icon13);
					    settlerButtons[5].setBounds(0, 0, 55, 55);
					    settlerButtons[5].addActionListener(this);
						 settlerButtons[6] = new GameJButton(icon15);
					    settlerButtons[6].setBounds(0, 0, 55, 55);
					    settlerButtons[6].addActionListener(this);
						 settlerButtons[7] = new GameJButton(icon16);//dummy
					    settlerButtons[7].setBounds(0, 0, 55, 55);
			
			//System.out.println(playerUI.lb.getHolder(1, 13));			   
						 playerUI.lb.getHolder(0, 13).add(settlerButtons[0], new Integer(50));
					    playerUI.lb.getHolder(0, 13).repaint();
					    playerUI.lb.getHolder(1, 13).add(settlerButtons[1], new Integer(50));
					    playerUI.lb.getHolder(1, 13).repaint();
					    playerUI.lb.getHolder(2, 13).add(settlerButtons[2], new Integer(50));
					    playerUI.lb.getHolder(2, 13).repaint();
					    playerUI.lb.getHolder(0, 14).add(settlerButtons[3], new Integer(50));
					    playerUI.lb.getHolder(0, 14).repaint();
					    playerUI.lb.getHolder(2, 14).add(settlerButtons[4], new Integer(50));
					    playerUI.lb.getHolder(2, 14).repaint();
					    playerUI.lb.getHolder(0, 15).add(settlerButtons[5], new Integer(50));
					    playerUI.lb.getHolder(0, 15).repaint();
					    playerUI.lb.getHolder(2, 15).add(settlerButtons[6], new Integer(50));
					    playerUI.lb.getHolder(2, 15).repaint();
				       break;	 
					 case 3:
						 settlerButtons = new GameJButton[8];
			
						 settlerButtons[0] = new GameJButton(icon6);
					    settlerButtons[0].setBounds(0, 0, 55, 55);
					    settlerButtons[0].addActionListener(this);
						 settlerButtons[1] = new GameJButton(icon7);
						 settlerButtons[1].setBounds(0, 0, 55, 55);
					    settlerButtons[1].addActionListener(this);
						 settlerButtons[2] = new GameJButton(icon8);
						 settlerButtons[2].setBounds(0, 0, 55, 55);
					    settlerButtons[2].addActionListener(this);
						 settlerButtons[3] = new GameJButton(icon10);
						 settlerButtons[3].setBounds(0, 0, 55, 55);
					    settlerButtons[3].addActionListener(this);
						 settlerButtons[4] = new GameJButton(icon12);
					    settlerButtons[4].setBounds(0, 0, 55, 55);
					    settlerButtons[4].addActionListener(this);
						 settlerButtons[5] = new GameJButton(icon15);
					    settlerButtons[5].setBounds(0, 0, 55, 55);
					    settlerButtons[5].addActionListener(this);
						 settlerButtons[6] = new GameJButton(icon16);//dummy
					    settlerButtons[6].setBounds(0, 0, 55, 55);
						 settlerButtons[7] = new GameJButton(icon16);//dummy
					    settlerButtons[7].setBounds(0, 0, 55, 55);
			
			//System.out.println(playerUI.lb.getHolder(1, 13));			   
						 playerUI.lb.getHolder(1, 13).add(settlerButtons[0], new Integer(50));
					    playerUI.lb.getHolder(1, 13).repaint();
					    playerUI.lb.getHolder(2, 13).add(settlerButtons[1], new Integer(50));
					    playerUI.lb.getHolder(2, 13).repaint();
					    playerUI.lb.getHolder(3, 13).add(settlerButtons[2], new Integer(50));
					    playerUI.lb.getHolder(3, 13).repaint();
					    playerUI.lb.getHolder(1, 14).add(settlerButtons[3], new Integer(50));
					    playerUI.lb.getHolder(1, 14).repaint();
					    playerUI.lb.getHolder(3, 14).add(settlerButtons[4], new Integer(50));
					    playerUI.lb.getHolder(3, 14).repaint();
					    playerUI.lb.getHolder(2, 15).add(settlerButtons[5], new Integer(50));
					    playerUI.lb.getHolder(2, 15).repaint();
				       break;	 
				  }
	        }
		  }
        playerBoard.turnHolder.add(select);
		    
	     select.setMaximum(true);

        removeListeners(cityButtons);
		}
		
		private void startArmy(){
        playerBoard.turnHolder.remove(select);
        playerBoard.turnHolder.repaint();
		      
		  select = new GameInternalFrame("Setup");
	     select.setBackground(playerColor); 
	     select.setBorder(new BevelBorder(1, new Color(0, 0, 0), new Color(255, 255, 255)));
        select.setPreferredSize(new Dimension(198, 198));

		  GameJLabel pick = new GameJLabel(" Pick starting Army");
		  GameJLabel pick2 = new GameJLabel("  placement on map");
        pick.setBounds(00, 50, 190, 20);
         if(color.equals("Blue")){
				pick.setForeground(new Color(250, 250, 250));			 
         }else{
				pick.setForeground(new Color(50, 50, 50));			 
			}
         pick2.setBounds(00, 70, 190, 20);
         if(color.equals("Blue")){
				pick2.setForeground(new Color(250, 250, 250));			 
	      }else{
				pick2.setForeground(new Color(50, 50, 50));			 
			}
		  Font f = new Font("Monospaced", Font.BOLD, 13);
        pick.setFont(f); 
        pick2.setFont(f); 

	     select.add(pick);
	     select.add(pick2);
        select.show();

		  if(playerNumber == 0){
			  if(nation.equals("China")){
			     GameImageIcon icon1 = getImage("data/ch1.png");
		        GameImageIcon icon2 = getImage("data/ch2.png");
		        GameImageIcon icon3 = getImage("data/ch3.png");
		        GameImageIcon icon4 = getImage("data/ch4.png");
				  GameImageIcon icon5 = getImage("data/ch5.png");
				  GameImageIcon icon6 = getImage("data/ch6.png");
		        GameImageIcon icon7 = getImage("data/ch7.png");
		        GameImageIcon icon8 = getImage("data/ch8.png");
		        GameImageIcon icon9 = getImage("data/ch9.png");
		        GameImageIcon icon10 = getImage("data/ch10.png");
		        GameImageIcon icon11 = getImage("data/ch11.png");
		        GameImageIcon icon12 = getImage("data/ch12.png");
		        GameImageIcon icon13 = getImage("data/ch13.png");
		        GameImageIcon icon14 = getImage("data/ch14.png");
		        GameImageIcon icon15 = getImage("data/ch15.png");
		        GameImageIcon icon16 = getImage("data/ch16.png");
	
				  switch(startChoice)
				  {
				    case 0:
						 armyButtons[0] = new GameJButton(icon1);
					    armyButtons[0].setBounds(0, 0, 55, 55);
					    armyButtons[0].addActionListener(this);
						 armyButtons[1] = new GameJButton(icon2);
						 armyButtons[1].setBounds(0, 0, 55, 55);
					    armyButtons[1].addActionListener(this);
						 armyButtons[2] = new GameJButton(icon3);
						 armyButtons[2].setBounds(0, 0, 55, 55);
					    armyButtons[2].addActionListener(this);
						 armyButtons[3] = new GameJButton(icon5);
						 armyButtons[3].setBounds(0, 0, 55, 55);
					    armyButtons[3].addActionListener(this);
						 armyButtons[4] = new GameJButton(icon7);
					    armyButtons[4].setBounds(0, 0, 55, 55);
					    armyButtons[4].addActionListener(this);
						 armyButtons[5] = new GameJButton(icon9);
					    armyButtons[5].setBounds(0, 0, 55, 55);
					    armyButtons[5].addActionListener(this);
						 armyButtons[6] = new GameJButton(icon10);
					    armyButtons[6].setBounds(0, 0, 55, 55);
					    armyButtons[6].addActionListener(this);
						 armyButtons[7] = new GameJButton(icon11);
					    armyButtons[7].setBounds(0, 0, 55, 55);
					    armyButtons[7].addActionListener(this);
			
			//System.out.println(playerUI.lb.getHolder(1, 13));			   
						 playerUI.lb.getHolder(0, 12).add(armyButtons[0], new Integer(2));
					    playerUI.lb.getHolder(0, 12).repaint();
					    playerUI.lb.getHolder(1, 12).add(armyButtons[1], new Integer(2));
					    playerUI.lb.getHolder(1, 12).repaint();
					    playerUI.lb.getHolder(2, 12).add(armyButtons[2], new Integer(2));
					    playerUI.lb.getHolder(2, 12).repaint();
					    playerUI.lb.getHolder(0, 13).add(armyButtons[3], new Integer(2));
					    playerUI.lb.getHolder(0, 13).repaint();
					    playerUI.lb.getHolder(2, 13).add(armyButtons[4], new Integer(2));
					    playerUI.lb.getHolder(2, 13).repaint();
					    playerUI.lb.getHolder(0, 14).add(armyButtons[5], new Integer(2));
					    playerUI.lb.getHolder(0, 14).repaint();
					    playerUI.lb.getHolder(1, 14).add(armyButtons[6], new Integer(2));
					    playerUI.lb.getHolder(1, 14).repaint();
					    playerUI.lb.getHolder(2, 14).add(armyButtons[7], new Integer(2));
					    playerUI.lb.getHolder(2, 14).repaint();
				       break;	 
					 case 1:
						 armyButtons[0] = new GameJButton(icon2);
					    armyButtons[0].setBounds(0, 0, 55, 55);
					    armyButtons[0].addActionListener(this);
						 armyButtons[1] = new GameJButton(icon3);
						 armyButtons[1].setBounds(0, 0, 55, 55);
					    armyButtons[1].addActionListener(this);
						 armyButtons[2] = new GameJButton(icon4);
						 armyButtons[2].setBounds(0, 0, 55, 55);
					    armyButtons[2].addActionListener(this);
						 armyButtons[3] = new GameJButton(icon6);
						 armyButtons[3].setBounds(0, 0, 55, 55);
					    armyButtons[3].addActionListener(this);
						 armyButtons[4] = new GameJButton(icon8);
					    armyButtons[4].setBounds(0, 0, 55, 55);
					    armyButtons[4].addActionListener(this);
						 armyButtons[5] = new GameJButton(icon10);
					    armyButtons[5].setBounds(0, 0, 55, 55);
					    armyButtons[5].addActionListener(this);
						 armyButtons[6] = new GameJButton(icon11);
					    armyButtons[6].setBounds(0, 0, 55, 55);
					    armyButtons[6].addActionListener(this);
						 armyButtons[7] = new GameJButton(icon12);
					    armyButtons[7].setBounds(0, 0, 55, 55);
					    armyButtons[7].addActionListener(this);
			
			//System.out.println(playerUI.lb.getHolder(1, 13));			   
						 playerUI.lb.getHolder(1, 12).add(armyButtons[0], new Integer(2));
					    playerUI.lb.getHolder(1, 12).repaint();
					    playerUI.lb.getHolder(2, 12).add(armyButtons[1], new Integer(2));
					    playerUI.lb.getHolder(2, 12).repaint();
					    playerUI.lb.getHolder(3, 12).add(armyButtons[2], new Integer(2));
					    playerUI.lb.getHolder(3, 12).repaint();
					    playerUI.lb.getHolder(1, 13).add(armyButtons[3], new Integer(2));
					    playerUI.lb.getHolder(1, 13).repaint();
					    playerUI.lb.getHolder(3, 13).add(armyButtons[4], new Integer(2));
					    playerUI.lb.getHolder(3, 13).repaint();
					    playerUI.lb.getHolder(1, 14).add(armyButtons[5], new Integer(2));
					    playerUI.lb.getHolder(1, 14).repaint();
					    playerUI.lb.getHolder(2, 14).add(armyButtons[6], new Integer(2));
					    playerUI.lb.getHolder(2, 14).repaint();
					    playerUI.lb.getHolder(3, 14).add(armyButtons[7], new Integer(2));
					    playerUI.lb.getHolder(3, 14).repaint();
				       break;	 
					 case 2:
						 armyButtons[0] = new GameJButton(icon5);
					    armyButtons[0].setBounds(0, 0, 55, 55);
					    armyButtons[0].addActionListener(this);
						 armyButtons[1] = new GameJButton(icon6);
						 armyButtons[1].setBounds(0, 0, 55, 55);
					    armyButtons[1].addActionListener(this);
						 armyButtons[2] = new GameJButton(icon7);
						 armyButtons[2].setBounds(0, 0, 55, 55);
					    armyButtons[2].addActionListener(this);
						 armyButtons[3] = new GameJButton(icon9);
						 armyButtons[3].setBounds(0, 0, 55, 55);
					    armyButtons[3].addActionListener(this);
						 armyButtons[4] = new GameJButton(icon11);
					    armyButtons[4].setBounds(0, 0, 55, 55);
					    armyButtons[4].addActionListener(this);
						 armyButtons[5] = new GameJButton(icon13);
					    armyButtons[5].setBounds(0, 0, 55, 55);
					    armyButtons[5].addActionListener(this);
						 armyButtons[6] = new GameJButton(icon15);
					    armyButtons[6].setBounds(0, 0, 55, 55);
					    armyButtons[6].addActionListener(this);
						 armyButtons[7] = new GameJButton(icon16);//dummy
					    armyButtons[7].setBounds(0, 0, 55, 55);
			
			//System.out.println(playerUI.lb.getHolder(1, 13));			   
						 playerUI.lb.getHolder(0, 13).add(armyButtons[0], new Integer(2));
					    playerUI.lb.getHolder(0, 13).repaint();
					    playerUI.lb.getHolder(1, 13).add(armyButtons[1], new Integer(2));
					    playerUI.lb.getHolder(1, 13).repaint();
					    playerUI.lb.getHolder(2, 13).add(armyButtons[2], new Integer(2));
					    playerUI.lb.getHolder(2, 13).repaint();
					    playerUI.lb.getHolder(0, 14).add(armyButtons[3], new Integer(2));
					    playerUI.lb.getHolder(0, 14).repaint();
					    playerUI.lb.getHolder(2, 14).add(armyButtons[4], new Integer(2));
					    playerUI.lb.getHolder(2, 14).repaint();
					    playerUI.lb.getHolder(0, 15).add(armyButtons[5], new Integer(2));
					    playerUI.lb.getHolder(0, 15).repaint();
					    playerUI.lb.getHolder(2, 15).add(armyButtons[6], new Integer(2));
					    playerUI.lb.getHolder(2, 15).repaint();
				       break;	 
					 case 3:
						 armyButtons[0] = new GameJButton(icon6);
					    armyButtons[0].setBounds(0, 0, 55, 55);
					    armyButtons[0].addActionListener(this);
						 armyButtons[1] = new GameJButton(icon7);
						 armyButtons[1].setBounds(0, 0, 55, 55);
					    armyButtons[1].addActionListener(this);
						 armyButtons[2] = new GameJButton(icon8);
						 armyButtons[2].setBounds(0, 0, 55, 55);
					    armyButtons[2].addActionListener(this);
						 armyButtons[3] = new GameJButton(icon10);
						 armyButtons[3].setBounds(0, 0, 55, 55);
					    armyButtons[3].addActionListener(this);
						 armyButtons[4] = new GameJButton(icon12);
					    armyButtons[4].setBounds(0, 0, 55, 55);
					    armyButtons[4].addActionListener(this);
						 armyButtons[5] = new GameJButton(icon15);
					    armyButtons[5].setBounds(0, 0, 55, 55);
					    armyButtons[5].addActionListener(this);
						 armyButtons[6] = new GameJButton(icon16);//dummy
					    armyButtons[6].setBounds(0, 0, 55, 55);
						 armyButtons[7] = new GameJButton(icon16);//dummy
					    armyButtons[7].setBounds(0, 0, 55, 55);
			
			//System.out.println(playerUI.lb.getHolder(1, 13));			   
						 playerUI.lb.getHolder(1, 13).add(armyButtons[0], new Integer(2));
					    playerUI.lb.getHolder(1, 13).repaint();
					    playerUI.lb.getHolder(2, 13).add(armyButtons[1], new Integer(2));
					    playerUI.lb.getHolder(2, 13).repaint();
					    playerUI.lb.getHolder(3, 13).add(armyButtons[2], new Integer(2));
					    playerUI.lb.getHolder(3, 13).repaint();
					    playerUI.lb.getHolder(1, 14).add(armyButtons[3], new Integer(2));
					    playerUI.lb.getHolder(1, 14).repaint();
					    playerUI.lb.getHolder(3, 14).add(armyButtons[4], new Integer(2));
					    playerUI.lb.getHolder(3, 14).repaint();
					    playerUI.lb.getHolder(2, 15).add(armyButtons[5], new Integer(2));
					    playerUI.lb.getHolder(2, 15).repaint();
				       break;	 
				  }
	        }
        }else if(playerNumber == 1){
	        if(nation.equals("America")){
			     GameImageIcon icon1 = getImage("data/am16rev.png");
			     GameImageIcon icon2 = getImage("data/am15rev.png");
			     GameImageIcon icon3 = getImage("data/am14rev.png");
			     GameImageIcon icon4 = getImage("data/am13rev.png");
			     GameImageIcon icon5 = getImage("data/am12rev.png");
			     GameImageIcon icon6 = getImage("data/am11rev.png");
			     GameImageIcon icon7 = getImage("data/am10rev.png");
			     GameImageIcon icon8 = getImage("data/am9rev.png");
			     GameImageIcon icon9 = getImage("data/am8rev.png");
			     GameImageIcon icon10 = getImage("data/am7rev.png");
			     GameImageIcon icon11 = getImage("data/am6rev.png");
			     GameImageIcon icon12 = getImage("data/am5rev.png");
			     GameImageIcon icon13 = getImage("data/am4rev.png");
			     GameImageIcon icon14 = getImage("data/am3rev.png");
			     GameImageIcon icon15 = getImage("data/am2rev.png");
			     GameImageIcon icon16 = getImage("data/am1rev.png");
	
				  switch(startChoice)
				  {
				    case 0:
						 armyButtons[0] = new GameJButton(icon1);
					    armyButtons[0].setBounds(0, 0, 55, 55);
					    armyButtons[0].addActionListener(this);
						 armyButtons[1] = new GameJButton(icon3);
						 armyButtons[1].setBounds(0, 0, 55, 55);
					    armyButtons[1].addActionListener(this);
						 armyButtons[2] = new GameJButton(icon5);
						 armyButtons[2].setBounds(0, 0, 55, 55);
					    armyButtons[2].addActionListener(this);
						 armyButtons[3] = new GameJButton(icon7);
						 armyButtons[3].setBounds(0, 0, 55, 55);
					    armyButtons[3].addActionListener(this);
						 armyButtons[4] = new GameJButton(icon10);
					    armyButtons[4].setBounds(0, 0, 55, 55);
					    armyButtons[4].addActionListener(this);
						 armyButtons[5] = new GameJButton(icon11);
					    armyButtons[5].setBounds(0, 0, 55, 55);
					    armyButtons[5].addActionListener(this);
			
						 playerUI.lb.getHolder(4, 0).add(armyButtons[0], new Integer(2));
					    playerUI.lb.getHolder(4, 0).repaint();
					    playerUI.lb.getHolder(6, 0).add(armyButtons[1], new Integer(2));
					    playerUI.lb.getHolder(6, 0).repaint();
					    playerUI.lb.getHolder(4, 1).add(armyButtons[2], new Integer(2));
					    playerUI.lb.getHolder(4, 1).repaint();
					    playerUI.lb.getHolder(6, 1).add(armyButtons[3], new Integer(2));
					    playerUI.lb.getHolder(6, 1).repaint();
					    playerUI.lb.getHolder(5, 2).add(armyButtons[4], new Integer(2));
					    playerUI.lb.getHolder(5, 2).repaint();
					    playerUI.lb.getHolder(6, 2).add(armyButtons[5], new Integer(2));
					    playerUI.lb.getHolder(6, 2).repaint();
				       break;	 
					 case 1:
						 armyButtons[0] = new GameJButton(icon3);
					    armyButtons[0].setBounds(0, 0, 55, 55);
					    armyButtons[0].addActionListener(this);
						 armyButtons[1] = new GameJButton(icon6);
						 armyButtons[1].setBounds(0, 0, 55, 55);
					    armyButtons[1].addActionListener(this);
						 armyButtons[2] = new GameJButton(icon8);
						 armyButtons[2].setBounds(0, 0, 55, 55);
					    armyButtons[2].addActionListener(this);
						 armyButtons[3] = new GameJButton(icon10);
						 armyButtons[3].setBounds(0, 0, 55, 55);
					    armyButtons[3].addActionListener(this);
						 armyButtons[4] = new GameJButton(icon11);
					    armyButtons[4].setBounds(0, 0, 55, 55);
					    armyButtons[4].addActionListener(this);
						 armyButtons[5] = new GameJButton(icon12);
					    armyButtons[5].setBounds(0, 0, 55, 55);
					    armyButtons[5].addActionListener(this);
			
						 playerUI.lb.getHolder(6, 0).add(armyButtons[0], new Integer(2));
					    playerUI.lb.getHolder(6, 0).repaint();
					    playerUI.lb.getHolder(5, 1).add(armyButtons[1], new Integer(2));
					    playerUI.lb.getHolder(5, 1).repaint();
					    playerUI.lb.getHolder(7, 1).add(armyButtons[2], new Integer(2));
					    playerUI.lb.getHolder(7, 1).repaint();
					    playerUI.lb.getHolder(5, 2).add(armyButtons[3], new Integer(2));
					    playerUI.lb.getHolder(5, 2).repaint();
					    playerUI.lb.getHolder(6, 2).add(armyButtons[4], new Integer(2));
					    playerUI.lb.getHolder(6, 2).repaint();
					    playerUI.lb.getHolder(7, 2).add(armyButtons[5], new Integer(2));
					    playerUI.lb.getHolder(7, 2).repaint();
				       break;	 
					 case 2:
						 armyButtons[0] = new GameJButton(icon5);
					    armyButtons[0].setBounds(0, 0, 55, 55);
					    armyButtons[0].addActionListener(this);
						 armyButtons[1] = new GameJButton(icon6);
						 armyButtons[1].setBounds(0, 0, 55, 55);
					    armyButtons[1].addActionListener(this);
						 armyButtons[2] = new GameJButton(icon7);
						 armyButtons[2].setBounds(0, 0, 55, 55);
					    armyButtons[2].addActionListener(this);
						 armyButtons[3] = new GameJButton(icon11);
						 armyButtons[3].setBounds(0, 0, 55, 55);
					    armyButtons[3].addActionListener(this);
						 armyButtons[4] = new GameJButton(icon13);
					    armyButtons[4].setBounds(0, 0, 55, 55);
					    armyButtons[4].addActionListener(this);
						 armyButtons[5] = new GameJButton(icon15);
					    armyButtons[5].setBounds(0, 0, 55, 55);
					    armyButtons[5].addActionListener(this);
			
						 playerUI.lb.getHolder(4, 1).add(armyButtons[0], new Integer(2));
					    playerUI.lb.getHolder(4, 1).repaint();
					    playerUI.lb.getHolder(5, 1).add(armyButtons[1], new Integer(2));
					    playerUI.lb.getHolder(5, 1).repaint();
					    playerUI.lb.getHolder(6, 1).add(armyButtons[2], new Integer(2));
					    playerUI.lb.getHolder(6, 1).repaint();
					    playerUI.lb.getHolder(6, 2).add(armyButtons[3], new Integer(2));
					    playerUI.lb.getHolder(6, 2).repaint();
					    playerUI.lb.getHolder(4, 3).add(armyButtons[4], new Integer(2));
					    playerUI.lb.getHolder(4, 3).repaint();
					    playerUI.lb.getHolder(6, 3).add(armyButtons[5], new Integer(2));
					    playerUI.lb.getHolder(6, 3).repaint();
				       break;	 
					 case 3:
						 armyButtons[0] = new GameJButton(icon6);
					    armyButtons[0].setBounds(0, 0, 55, 55);
					    armyButtons[0].addActionListener(this);
						 armyButtons[1] = new GameJButton(icon7);
						 armyButtons[1].setBounds(0, 0, 55, 55);
					    armyButtons[1].addActionListener(this);
						 armyButtons[2] = new GameJButton(icon8);
						 armyButtons[2].setBounds(0, 0, 55, 55);
					    armyButtons[2].addActionListener(this);
						 armyButtons[3] = new GameJButton(icon10);
						 armyButtons[3].setBounds(0, 0, 55, 55);
					    armyButtons[3].addActionListener(this);
						 armyButtons[4] = new GameJButton(icon12);
					    armyButtons[4].setBounds(0, 0, 55, 55);
					    armyButtons[4].addActionListener(this);
						 armyButtons[5] = new GameJButton(icon15);
					    armyButtons[5].setBounds(0, 0, 55, 55);
					    armyButtons[5].addActionListener(this);
						 armyButtons[6] = new GameJButton(icon16);
					    armyButtons[6].setBounds(0, 0, 55, 55);
					    armyButtons[6].addActionListener(this);
			
						 playerUI.lb.getHolder(5, 1).add(armyButtons[0], new Integer(2));
					    playerUI.lb.getHolder(5, 1).repaint();
					    playerUI.lb.getHolder(6, 1).add(armyButtons[1], new Integer(2));
					    playerUI.lb.getHolder(6, 1).repaint();
					    playerUI.lb.getHolder(7, 1).add(armyButtons[2], new Integer(2));
					    playerUI.lb.getHolder(7, 1).repaint();
					    playerUI.lb.getHolder(5, 2).add(armyButtons[3], new Integer(2));
					    playerUI.lb.getHolder(5, 2).repaint();
					    playerUI.lb.getHolder(7, 2).add(armyButtons[4], new Integer(2));
					    playerUI.lb.getHolder(7, 2).repaint();
					    playerUI.lb.getHolder(6, 3).add(armyButtons[5], new Integer(2));
					    playerUI.lb.getHolder(6, 3).repaint();
					    playerUI.lb.getHolder(7, 3).add(armyButtons[6], new Integer(2));
					    playerUI.lb.getHolder(7, 3).repaint();
				       break;	 
				  }
           }
		  }	  
        playerBoard.turnHolder.add(select);
		    
	     select.setMaximum(true);

		}

      private void removeListeners(JButton[] target){
		   for(JButton temp : target){
			   temp.removeActionListener(this);
			}
		}

/* getter/setter */

	public void setPlayerUI(CivGUI playerUI) {
		this.playerUI = playerUI;
	}
	public void setPlayerBoard(PlayerBoard PlayerBoard) {
		this.playerBoard = playerBoard;
	}
	public GameJLayeredPane getPlayersArea() {
		return playersArea;
	}
	public void setPlayersArea(GameJLayeredPane playersArea) {
		this.playersArea = playersArea;
	}
	public GameJLayeredPane getPlayerCard() {
		return playerCard;
	}
	public void setPlayerCard(GameJLayeredPane playerCard) {
		this.playerCard = playerCard;
	}
	public GameJLayeredPane getCardHolder() {
		return cardHolder;
	}
	public void setCardHolder(GameJLayeredPane cardHolder) {
		this.cardHolder = cardHolder;
	}
	public GameJLayeredPane getPlayerTechpane() {
		return playerTechpane;
	}
	public void setPlayerTechpane(GameJLayeredPane playerTechpane) {
		this.playerTechpane = playerTechpane;
	}
	public GameJPanel getPlayerTurnholder() {
		return playerTurnholder;
	}
	public void setPlayerTurnholder(GameJPanel playerTurnholder) {
		this.playerTurnholder = playerTurnholder;
	}
	public Vector getUnitArmyLabels() {
		return unitArmyLabels;
	}
	public void setUnitArmyLabels(Vector unitArmyLabels) {
		this.unitArmyLabels = unitArmyLabels;
	}
	public Vector getUnitSettlerLabels() {
		return unitSettlerLabels;
	}
	public void setUnitSettlerLabels(Vector unitSettlerLabels) {
		this.unitSettlerLabels = unitSettlerLabels;
	}
	public GameInternalFrame getSelect() {
		return select;
	}
	public void setSelect(GameInternalFrame select) {
		this.select = select;
	}
	public Vector getTech() {
		return tech;
	}
	public void setTech(Vector tech) {
		this.tech = tech;
	}
	public Tech[] getTechListener() {
		return techListener;
	}
	public void setTechListener(Tech[] techListener) {
		this.techListener = techListener;
	}
	public GameJLabel[] getWonderListener() {
		return wonderListener;
	}
	public void setWonderListener(GameJLabel[] wonderListener) {
		this.wonderListener = wonderListener;
	}
	public GameJButton[] getCityButtons() {
		return cityButtons;
	}
	public void setCityButtons(GameJButton[] cityButtons) {
		this.cityButtons = cityButtons;
	}
	public GameJButton[] getsettlerButtons() {
		return settlerButtons;
	}
	public void setsettlerButtons(GameJButton[] settlerButtons) {
		this.settlerButtons = settlerButtons;
	}
	public GameJButton[] getarmyButtons() {
		return armyButtons;
	}
	public void setarmyButtons(GameJButton[] armyButtons) {
		this.armyButtons = armyButtons;
	}
	public Vector getCities() {
		return cities;
	}
	public void setCities(Vector cities) {
		this.cities = cities;
	}
	public Vector getSettlers() {
		return settlers;
	}
	public void setSettlers(Vector Settlers) {
		this.settlers = settlers;
	}
	public Vector getArmies() {
		return armies;
	}
	public void setArmies(Vector armies) {
		this.armies = armies;
	}
	public void setUnitHand(Vector unitHand) {
		this.unitHand = unitHand;
	}
	public Vector getGreatPerson() {
		return greatPerson;
	}
	public void setGreatPerson(Vector greatPerson) {
		this.greatPerson = greatPerson;
	}
	public Vector getHutRes() {
		return hutRes;
	}
	public void setHutRes(Vector hutRes) {
		this.hutRes = hutRes;
	}
	public Vector getVillageRes() {
		return villageRes;
	}
	public void setVillageRes(Vector villageRes) {
		this.villageRes = villageRes;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public void setPlayerColor(Color playerColor) {
		this.playerColor = playerColor;
	}
	public void setCulture(int culture) {
		this.culture = culture;
	}
	public void setGold(int gold) {
		this.gold = gold;
	}
	public int getCityGold() {
		return cityGold;
	}
	public void setCityGold(int cityGold) {
		this.cityGold = cityGold;
	}
	public void setTrade(int trade) {
		this.trade = trade;
	}
	public int getStackLimit() {
		return stackLimit;
	}
	public void setStackLimit(int stackLimit) {
		this.stackLimit = stackLimit;
	}
	public int getHandLimitCulture() {
		return handLimitCulture;
	}
	public void setHandLimitCulture(int handLimitCulture) {
		this.handLimitCulture = handLimitCulture;
	}
	public void setMoveLimit(int moveLimit) {
		this.moveLimit = moveLimit;
	}
	public void setIron(int iron) {
		Iron = iron;
	}
	public void setSilk(int silk) {
		Silk = silk;
	}
	public void setIncense(int incense) {
		Incense = incense;
	}
	public void setWheat(int wheat) {
		Wheat = wheat;
	}
	public void setSpy(int spy) {
		Spy = spy;
	}
	public void setUranium(int uranium) {
		Uranium = uranium;
	}
	public int getMarketWheat() {
		return marketWheat;
	}
	public void setMarketWheat(int marketWheat) {
		this.marketWheat = marketWheat;
	}
	public int getMarketIron() {
		return marketIron;
	}
	public void setMarketIron(int marketIron) {
		this.marketIron = marketIron;
	}
	public int getMarketSilk() {
		return marketSilk;
	}
	public void setMarketSilk(int marketSilk) {
		this.marketSilk = marketSilk;
	}
	public int getMarketIncense() {
		return marketIncense;
	}
	public void setMarketIncense(int marketIncense) {
		this.marketIncense = marketIncense;
	}
	public void setInfLevel(int infLevel) {
		this.infLevel = infLevel;
	}
	public void setMntLevel(int mntLevel) {
		this.mntLevel = mntLevel;
	}
	public void setArtLevel(int artLevel) {
		this.artLevel = artLevel;
	}
	public void setAcftLevel(int acftLevel) {
		this.acftLevel = acftLevel;
	}
	public int getNumLvl1() {
		return numLvl1;
	}
	public void setNumLvl1(int numLvl1) {
		this.numLvl1 = numLvl1;
	}
	public int getNumLvl2() {
		return numLvl2;
	}
	public void setNumLvl2(int numLvl2) {
		this.numLvl2 = numLvl2;
	}
	public int getNumLvl3() {
		return numLvl3;
	}
	public void setNumLvl3(int numLvl3) {
		this.numLvl3 = numLvl3;
	}
	public int getNumLvl4() {
		return numLvl4;
	}
	public void setNumLvl4(int numLvl4) {
		this.numLvl4 = numLvl4;
	}
	public int getGov() {
		return gov;
	}
	public void setGov(int gov) {
		this.gov = gov;
	}
	public int getFreeGov() {
		return freeGov;
	}
	public void setFreeGov(int freeGov) {
		this.freeGov = freeGov;
	}
	public int getNumWonders() {
		return numWonders;
	}
	public void setNumWonders(int numWonders) {
		this.numWonders = numWonders;
	}
	public void setCombatBonus(int combatBonus) {
		this.combatBonus = combatBonus;
	}
	public int getStartChoice() {
		return startChoice;
	}
	public void setStartChoice(int startChoice) {
		this.startChoice = startChoice;
	}
	public void setNumGroups(int numGroups) {
		this.numGroups = numGroups;
	}
	public int getGroup1total() {
		return group1total;
	}
	public void setGroup1total(int group1total) {
		this.group1total = group1total;
	}
	public int getGroup2total() {
		return group2total;
	}
	public void setGroup2total(int group2total) {
		this.group2total = group2total;
	}
	public int getGroup3total() {
		return group3total;
	}
	public void setGroup3total(int group3total) {
		this.group3total = group3total;
	}
	public int getGroup4total() {
		return group4total;
	}
	public void setGroup4total(int group4total) {
		this.group4total = group4total;
	}
	public void setPlayerNumber(int playerNumber) {
		this.playerNumber = playerNumber;
	}
	public boolean isCanWaterLand() {
		return canWaterLand;
	}
	public void setCanWaterLand(boolean canWaterLand) {
		this.canWaterLand = canWaterLand;
	}
	public boolean isCanWaterCross() {
		return canWaterCross;
	}
	public void setCanWaterCross(boolean canWaterCross) {
		this.canWaterCross = canWaterCross;
	}
	public boolean isHasIrrigation() {
		return hasIrrigation;
	}
	public void setHasIrrigation(boolean hasIrrigation) {
		this.hasIrrigation = hasIrrigation;
	}
	public boolean isCodeOfLaw() {
		return codeOfLaw;
	}
	public void setCodeOfLaw(boolean codeOfLaw) {
		this.codeOfLaw = codeOfLaw;
	}
	public boolean isFirstTurn() {
		return firstTurn;
	}
	public boolean isFreeGovChange() {
		return freeGovChange;
	}
	public void setFreeGovChange(boolean freeGovChange) {
		this.freeGovChange = freeGovChange;
	}
	public boolean isInAnarchy() {
		return inAnarchy;
	}
	public void setInAnarchy(boolean inAnarchy) {
		this.inAnarchy = inAnarchy;
	}
	public boolean isWhiteArmy() {
		return whiteArmy;
	}
	public void setWhiteArmy(boolean whiteArmy) {
		this.whiteArmy = whiteArmy;
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
	public GameJLabel getPlayerSettler() {
		return playerSettler;
	}
	public void setPlayerSettler(GameJLabel playerSettler) {
		this.playerSettler = playerSettler;
	}
	public GameJLabel getPlayerArmy() {
		return playerArmy;
	}
	public void setPlayerArmy(GameJLabel playerArmy) {
		this.playerArmy = playerArmy;
	}
	public GameJLabel getPlayerCity() {
		return playerCity;
	}
	public void setPlayerCity(GameJLabel playerCity) {
		this.playerCity = playerCity;
	}
	public Card[] getAvailableWonders() {
		return availableWonders;
	}
	public void setAvailableWonders(Card[] availableWonders) {
		this.availableWonders = availableWonders;
	}
	public GameJLayeredPane getWondersPane() {
		return wondersPane;
	}
	public void setWondersPane(GameJLayeredPane wondersPane) {
		this.wondersPane = wondersPane;
	}
	public GameJLayeredPane getCulturePane() {
		return culturePane;
	}
	public void setCulturePane(GameJLayeredPane culturePane) {
		this.culturePane = culturePane;
	}
	public JTextArea getCultureHandLim() {
		return cultureHandLim;
	}
	public void setCultureHandLim(JTextArea cultureHandLim) {
		this.cultureHandLim = cultureHandLim;
	}
	public int getMAX_ARMY() {
		return MAX_ARMY;
	}
	public int getMAX_SETTLER() {
		return MAX_SETTLER;
	}
	public int getMAX_CITIES() {
		return MAX_CITIES;
	}
	public int getMAX_TRADE() {
		return MAX_TRADE;
	}

	private void readObject(java.io.ObjectInputStream stream) throws java.io.IOException, ClassNotFoundException{
		try{
			culture = stream.readInt();
			gold = stream.readInt();
			cityGold = stream.readInt();
			trade = stream.readInt();
			stackLimit = stream.readInt();
			handLimitCulture = stream.readInt();
			moveLimit = stream.readInt();
			Iron = stream.readInt();
			Silk = stream.readInt();
			Incense = stream.readInt();
			Wheat = stream.readInt();
			Spy = stream.readInt();
			Uranium = stream.readInt();
			marketWheat = stream.readInt();
			marketIron = stream.readInt();
			marketSilk = stream.readInt();
			marketIncense = stream.readInt();
			infLevel = stream.readInt();
			mntLevel = stream.readInt();
			artLevel = stream.readInt();
			acftLevel = stream.readInt();
			numLvl1 = stream.readInt();
			numLvl2 = stream.readInt();
			numLvl3 = stream.readInt();
			numLvl4 = stream.readInt();
			gov = stream.readInt();
			freeGov = stream.readInt();
			numWonders = stream.readInt();
			combatBonus = stream.readInt();
			startChoice = stream.readInt();
			numGroups = stream.readInt();
			group1total = stream.readInt();
			group2total = stream.readInt();
			group3total = stream.readInt();
			group4total = stream.readInt();
			playerNumber = stream.readInt();
			nation = stream.readUTF();
			color = stream.readUTF();
			hasIrrigation = stream.readBoolean();
			codeOfLaw = stream.readBoolean();
			firstTurn = stream.readBoolean();
			freeGovChange = stream.readBoolean();
			inAnarchy = stream.readBoolean();
			whiteArmy = stream.readBoolean();
			canWaterLand = stream.readBoolean();
			canWaterCross = stream.readBoolean();
			playerUI = (CivGUI)stream.readObject();
			playerBoard = (PlayerBoard)stream.readObject();
			playersArea = (GameJLayeredPane)stream.readObject();
			playerCard = (GameJLayeredPane)stream.readObject();
			cardHolder = (GameJLayeredPane)stream.readObject();
			playerTechpane = (GameJLayeredPane)stream.readObject();
			playerTurnholder = (GameJPanel)stream.readObject();
			unitArmyLabels = (Vector)stream.readObject();
			unitSettlerLabels = (Vector)stream.readObject();  
			select = (GameInternalFrame)stream.readObject();;
			tech = (Vector)stream.readObject();
			techListener = (Tech[])stream.readObject();
			wonderListener = (GameJLabel[])stream.readObject(); 
			cityButtons = (GameJButton[])stream.readObject();
			settlerButtons = (GameJButton[])stream.readObject();
			armyButtons = (GameJButton[])stream.readObject();    
			cities = (Vector)stream.readObject();
			settlers = (Vector)stream.readObject();
			armies = (Vector)stream.readObject();
			unitHand = (Vector)stream.readObject();
			greatPerson = (Vector)stream.readObject();
			hutRes = (Vector)stream.readObject();
			villageRes = (Vector)stream.readObject();    
			playerColor = (Color)stream.readObject();
			trDial = (GameJLabel)stream.readObject();
			goDial = (GameJLabel)stream.readObject();
			playerSettler = (GameJLabel)stream.readObject();
			playerArmy = (GameJLabel)stream.readObject();
			playerCity = (GameJLabel)stream.readObject();
			availableWonders = (Card[])stream.readObject();
			wondersPane = (GameJLayeredPane)stream.readObject();
			culturePane = (GameJLayeredPane)stream.readObject();
			cultureHandLim = (JTextArea)stream.readObject();  
      }catch(java.io.IOException ioEx){
			System.out.println("IO EXCEPTION getSentObject\n");
			ioEx.printStackTrace();
		}
   }

   private void writeObject(java.io.ObjectOutputStream stream) throws java.io.IOException{
		try{
			stream.writeInt(culture);
			stream.writeInt(gold);
			stream.writeInt(cityGold);
			stream.writeInt(trade);
			stream.writeInt(stackLimit);
			stream.writeInt(handLimitCulture);
			stream.writeInt(moveLimit);
			stream.writeInt(Iron);
			stream.writeInt(Silk);
			stream.writeInt(Incense);
			stream.writeInt(Wheat);
			stream.writeInt(Spy);
			stream.writeInt(Uranium);
			stream.writeInt(marketWheat);
			stream.writeInt(marketIron);
			stream.writeInt(marketSilk);
			stream.writeInt(marketIncense);
			stream.writeInt(infLevel);
			stream.writeInt(mntLevel);
			stream.writeInt(artLevel);
			stream.writeInt(acftLevel);
			stream.writeInt(numLvl1);
			stream.writeInt(numLvl2);
			stream.writeInt(numLvl3);
			stream.writeInt(numLvl4);
			stream.writeInt(gov);
			stream.writeInt(freeGov);
			stream.writeInt(numWonders);
			stream.writeInt(combatBonus);
			stream.writeInt(startChoice);
			stream.writeInt(numGroups);
			stream.writeInt(group1total);
			stream.writeInt(group2total);
			stream.writeInt(group3total);
			stream.writeInt(group4total);
			stream.writeInt(playerNumber);
			stream.writeUTF(nation);
			stream.writeUTF(color);
			stream.writeBoolean(hasIrrigation);
			stream.writeBoolean(codeOfLaw);
			stream.writeBoolean(firstTurn);
			stream.writeBoolean(freeGovChange);
			stream.writeBoolean(inAnarchy);
			stream.writeBoolean(whiteArmy);
			stream.writeBoolean(canWaterLand);
			stream.writeBoolean(canWaterCross);
			stream.writeObject(playerUI);
			stream.writeObject(playerBoard);
			stream.writeObject(playersArea);
			stream.writeObject(playerCard);
			stream.writeObject(cardHolder);
			stream.writeObject(playerTechpane);
			stream.writeObject(playerTurnholder);
			stream.writeObject(unitArmyLabels);
			stream.writeObject(unitSettlerLabels);  
			stream.writeObject(select);
			stream.writeObject(tech);
			stream.writeObject(techListener);
			stream.writeObject(wonderListener); 
			stream.writeObject(cityButtons);
			stream.writeObject(settlerButtons);
			stream.writeObject(armyButtons);    
			stream.writeObject(cities);
			stream.writeObject(settlers);
			stream.writeObject(armies);
			stream.writeObject(unitHand);
			stream.writeObject(greatPerson);
			stream.writeObject(hutRes);
			stream.writeObject(villageRes);    
			stream.writeObject(playerColor);
			stream.writeObject(trDial);
			stream.writeObject(goDial);
			stream.writeObject(playerSettler);
			stream.writeObject(playerArmy);
			stream.writeObject(playerCity);
			stream.writeObject(availableWonders);
			stream.writeObject(wondersPane);
			stream.writeObject(culturePane);
			stream.writeObject(cultureHandLim);  
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

}//end file
/*      private void startArea()
		{
		
		 	 if(startChoice == 0)
			 {
				playerCity1.setBounds(0, 0, iconCity1.getIconWidth(),
	                                     iconCity1.getIconHeight()); 
	         ch6.setPreferredSize(new Dimension(iconCity1.getIconWidth(),
	                                            iconCity1.getIconHeight()));
				ch6.add(playerCity1, new Integer(1));
	      
		      GameImageIcon icon1 = getImage("data/ch1.png");
	         GameImageIcon icon2 = getImage("data/ch2.png");
	         GameImageIcon icon3 = getImage("data/ch3.png");
	         GameImageIcon icon5 = getImage("data/ch5.png");
	         GameImageIcon icon9 = getImage("data/ch9.png");
	
	         Object[] options_Settler = {icon1, icon2, icon3, icon5, icon7, icon9, icon10, icon11};
			   int Settler_start = -1;
	         playTrack("Settler");         
				while(Settler_start < 0)
				{
				  Settler_start = start.showOptionDialog(c, "Choose Settler square", "Player 1",
	                              JOptionPane.CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
	                              null, options_Settler, null);
	         }
	
	         switch(Settler_start)
			   {
			     case 0:
			       ch1.add(playerSettler.getLabel(), new Integer(5));		       
					 player_1.addSettler(playerSettler);
					 playerSettler.setPosition(0, 12);
					 lb.addSettlerCount(0, 12);      
					 break;
			     case 1:
			       ch2.add(playerSettler.getLabel(), new Integer(5));		       
					 player_1.addSettler(playerSettler);
					 playerSettler.setPosition(1, 12);
	             lb.addSettlerCount(1, 12);      
				    break;
	 		     case 2:
			       ch3.add(playerSettler.getLabel(), new Integer(5));		       
					 player_1.addSettler(playerSettler);
					 playerSettler.setPosition(2, 12);
	             lb.addSettlerCount(2, 12);      
			       break; 
			     case 3:
			       ch5.add(playerSettler.getLabel(), new Integer(5));		       
					 player_1.addSettler(playerSettler);
					 playerSettler.setPosition(0, 13);
	             lb.addSettlerCount(0, 13);      
			       break;
			     case 4:
			       ch7.add(playerSettler.getLabel(), new Integer(5));		       
					 player_1.addSettler(playerSettler);
					 playerSettler.setPosition(2, 13);
	             lb.addSettlerCount(2, 13);      
				    break;
	 		     case 5:
			       ch9.add(playerSettler.getLabel(), new Integer(5));		       
					 player_1.addSettler(playerSettler);
					 playerSettler.setPosition(0, 14);
	             lb.addSettlerCount(0, 14);      
			       break; 
	 		     case 6:
			       ch10.add(playerSettler.getLabel(), new Integer(5));		       
					 player_1.addSettler(playerSettler);
					 playerSettler.setPosition(1, 14);
	             lb.addSettlerCount(1, 14);      
			       break; 
	 		     case 7:
			       ch11.add(playerSettler.getLabel(), new Integer(5));		       
					 player_1.addSettler(playerSettler);
					 playerSettler.setPosition(2, 14);
	             lb.addSettlerCount(2, 14);      
			       break; 
			   }
	
	         Object[] options_Army = {icon1, icon2, icon3, icon5, icon7, icon9, icon10, icon11};
			   int Army1_start = start.showOptionDialog(c, "Choose Army square", "Player 1",
	                              JOptionPane.CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
	                              null, options_Army, null);
	         switch(Army1_start)
			   {
			     case 0:
			       playerArmy1.setBounds(lb.getSettlerCount(0,12) , lb.getArmyCount(0,12));    
			       ch1.add(playerArmy1.getLabel(), new Integer(5));		       
					 player_1.addArmy(playerArmy1);
					 playerArmy1.setPosition(0, 12);
					 lb.addArmyCount(0, 12);      
					 break;
			     case 1:
			       playerArmy1.setBounds(lb.getSettlerCount(1,12) , lb.getArmyCount(1,12));    
			       ch2.add(playerArmy1.getLabel(), new Integer(5));		       
					 player_1.addArmy(playerArmy1);
					 playerArmy1.setPosition(1, 12);
	             lb.addArmyCount(1, 12);      
				    break;
	 		     case 2:
			       playerArmy1.setBounds(lb.getSettlerCount(2,12) , lb.getArmyCount(2,12));    
			       ch3.add(playerArmy1.getLabel(), new Integer(5));		       
					 player_1.addArmy(playerArmy1);
					 playerArmy1.setPosition(2, 12);
	             lb.addArmyCount(2, 12);      
			       break; 
			     case 3:
			       playerArmy1.setBounds(lb.getSettlerCount(0,13) , lb.getArmyCount(0,13));    
			       ch5.add(playerArmy1.getLabel(), new Integer(5));		       
					 player_1.addArmy(playerArmy1);
					 playerArmy1.setPosition(0, 13);
	             lb.addArmyCount(0, 13);      
			       break;
			     case 4:
			       playerArmy1.setBounds(lb.getSettlerCount(2,13) , lb.getArmyCount(2,13));    
			       ch7.add(playerArmy1.getLabel(), new Integer(5));		       
					 player_1.addArmy(playerArmy1);
					 playerArmy1.setPosition(2, 13);
	             lb.addArmyCount(2, 13);      
				    break;
	 		     case 5:
			       playerArmy1.setBounds(lb.getSettlerCount(0,14) , lb.getArmyCount(0,14));    
			       ch9.add(playerArmy1.getLabel(), new Integer(5));		       
					 player_1.addArmy(playerArmy1);
					 playerArmy1.setPosition(0, 14);
	             lb.addArmyCount(0, 14);      
			       break; 
	 		     case 6:
			       playerArmy1.setBounds(lb.getSettlerCount(1,14) , lb.getArmyCount(1,14));    
			       ch10.add(playerArmy1.getLabel(), new Integer(5));		       
					 player_1.addArmy(playerArmy1);
					 playerArmy1.setPosition(1, 14);
	             lb.addArmyCount(1, 14);      
			       break; 
	 		     case 7:
			       playerArmy1.setBounds(lb.getSettlerCount(2,14) , lb.getArmyCount(2,14));    
			       ch11.add(playerArmy1.getLabel(), new Integer(5));		       
					 player_1.addArmy(playerArmy1);
					 playerArmy1.setPosition(2, 14);
	             lb.addArmyCount(2, 14);      
			       break; 
			   }
		  	 }
			 else if(startChoice == 1)
			 {
	         playerCity1.setBounds(0, 0, iconCity1.getIconWidth(),
	                                     iconCity1.getIconHeight()); 
	         ch7.setPreferredSize(new Dimension(iconCity1.getIconWidth(),
	                                            iconCity1.getIconHeight()));
				ch7.add(playerCity1, new Integer(1));
	      
		      GameImageIcon icon2 = getImage("data/ch2.png");
	         GameImageIcon icon3 = getImage("data/ch3.png");
	         GameImageIcon icon4 = getImage("data/ch4.png");
	         GameImageIcon icon8 = getImage("data/ch8.png");
	         GameImageIcon icon12 = getImage("data/ch12.png");
	
	         Object[] options_Settler = {icon2, icon3, icon4, icon6, icon8, icon10, icon11, icon12};
			   int Settler_start = -1;
	         playTrack("Settler");         
				while(Settler_start < 0)
				{
				  Settler_start = start.showOptionDialog(c, "Choose Settler square", "Player 1",
	                              JOptionPane.CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
	                              null, options_Settler, null);
	         }
	         switch(Settler_start)
			   {
			     case 0:
			       ch2.add(playerSettler.getLabel(), new Integer(5));		       
					 player_1.addSettler(playerSettler);
					 playerSettler.setPosition(1, 12);
	             lb.addSettlerCount(1, 12);      
					 break;
			     case 1:
			       ch3.add(playerSettler.getLabel(), new Integer(5));		       
					 player_1.addSettler(playerSettler);
					 playerSettler.setPosition(2, 12);
	             lb.addSettlerCount(2, 12);      
				    break;
	 		     case 2:
			       ch4.add(playerSettler.getLabel(), new Integer(5));		       
					 player_1.addSettler(playerSettler);
					 playerSettler.setPosition(3, 12);
	             lb.addSettlerCount(3, 12);      
			       break; 
			     case 3:
			       ch6.add(playerSettler.getLabel(), new Integer(5));		       
					 player_1.addSettler(playerSettler);
					 playerSettler.setPosition(1, 13);
	             lb.addSettlerCount(1, 13);      
			       break;
			     case 4:
			       ch8.add(playerSettler.getLabel(), new Integer(5));		       
					 player_1.addSettler(playerSettler);
					 playerSettler.setPosition(3, 13);
	             lb.addSettlerCount(3, 13);      
				    break;
	 		     case 5:
			       ch10.add(playerSettler.getLabel(), new Integer(5));		       
					 player_1.addSettler(playerSettler);
					 playerSettler.setPosition(1, 14);
	             lb.addSettlerCount(1, 14);      
			       break; 
	 		     case 6:
			       ch11.add(playerSettler.getLabel(), new Integer(5));		       
					 player_1.addSettler(playerSettler);
					 playerSettler.setPosition(2, 14);
	             lb.addSettlerCount(2, 14);      
			       break; 
	 		     case 7:
			       ch12.add(playerSettler.getLabel(), new Integer(5));		       
					 player_1.addSettler(playerSettler);
					 playerSettler.setPosition(3, 14);
	             lb.addSettlerCount(3, 14);      
			       break; 
			   }
	
	         Object[] options_Army = {icon2, icon3, icon4, icon6, icon8, icon10, icon11, icon12};
			   int Army1_start = -1;
				playTrack("Army");
				while(Army1_start < 0)
				{    
				  Army1_start = start.showOptionDialog(c, "Choose Army square", "Player 1",
	                              JOptionPane.CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
	                              null, options_Army, null);
	         }  
	
	         switch(Army1_start)
			   {
			     case 0:
			       playerArmy1.setBounds(lb.getSettlerCount(1,12) , lb.getArmyCount(1,12));    
			       ch2.add(playerArmy1.getLabel(), new Integer(5));		       
					 player_1.addArmy(playerArmy1);
					 playerArmy1.setPosition(1, 12);
	             lb.addArmyCount(1, 12);      
					 break;
			     case 1:
			       playerArmy1.setBounds(lb.getSettlerCount(2,12) , lb.getArmyCount(2,12));    
			       ch3.add(playerArmy1.getLabel(), new Integer(5));		       
					 player_1.addArmy(playerArmy1);
					 playerArmy1.setPosition(2, 12);
	             lb.addArmyCount(2, 12);      
				    break;
	 		     case 2:
			       playerArmy1.setBounds(lb.getSettlerCount(3,12) , lb.getArmyCount(3,12));    
			       ch4.add(playerArmy1.getLabel(), new Integer(5));		       
					 player_1.addArmy(playerArmy1);
					 playerArmy1.setPosition(3, 12);
	             lb.addArmyCount(3, 12);      
			       break; 
			     case 3:
			       playerArmy1.setBounds(lb.getSettlerCount(1,13) , lb.getArmyCount(1,13));    
			       ch6.add(playerArmy1.getLabel(), new Integer(5));		       
					 player_1.addArmy(playerArmy1);
					 playerArmy1.setPosition(1, 13);
	             lb.addArmyCount(1, 13);      
			       break;
			     case 4:
			       playerArmy1.setBounds(lb.getSettlerCount(3,13) , lb.getArmyCount(3,13));    
			       ch8.add(playerArmy1.getLabel(), new Integer(5));		       
					 player_1.addArmy(playerArmy1);
					 playerArmy1.setPosition(3, 13);
	             lb.addArmyCount(3, 13);      
				    break;
	 		     case 5:
			       playerArmy1.setBounds(lb.getSettlerCount(1,14) , lb.getArmyCount(1,14));    
			       ch10.add(playerArmy1.getLabel(), new Integer(5));		       
					 player_1.addArmy(playerArmy1);
					 playerArmy1.setPosition(1, 14);
	             lb.addArmyCount(1, 14);      
			       break; 
	 		     case 6:
			       playerArmy1.setBounds(lb.getSettlerCount(2,14) , lb.getArmyCount(2,14));    
			       ch11.add(playerArmy1.getLabel(), new Integer(5));		       
					 player_1.addArmy(playerArmy1);
					 playerArmy1.setPosition(2, 14);
	             lb.addArmyCount(2, 14);      
			       break; 
	 		     case 7:
			       playerArmy1.setBounds(lb.getSettlerCount(3,14) , lb.getArmyCount(3,14));    
			       ch12.add(playerArmy1.getLabel(), new Integer(5));		       
					 player_1.addArmy(playerArmy1);
					 playerArmy1.setPosition(3, 14);
	             lb.addArmyCount(3, 14);      
			       break; 
			   }
		  	 }
			 else if(startChoice == 2)
	       {
	         playerCity1.setBounds(0, 0, iconCity1.getIconWidth(),
	                                         iconCity1.getIconHeight()); 
	         ch10.setPreferredSize(new Dimension(iconCity1.getIconWidth(),
	                                            iconCity1.getIconHeight()));
				ch10.add(playerCity1, new Integer(1));
	      
		      GameImageIcon icon5 = getImage("data/ch5.png");
	         GameImageIcon icon9 = getImage("data/ch9.png");
	         GameImageIcon icon13 = getImage("data/ch13.png");
	         GameImageIcon icon15 = getImage("data/ch15.png");
	
	         Object[] options_Settler = {icon5, icon6, icon7, icon9, icon11, icon13, icon15};
			   int Settler_start = -1;
	         playTrack("Settler");         
				while(Settler_start < 0)
				{
				  Settler_start = start.showOptionDialog(c, "Choose Settler square", "Player 1",
	                              JOptionPane.CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
	                              null, options_Settler, null);
	         }
	
	         switch(Settler_start)
			   {
			     case 0:
			       ch5.add(playerSettler.getLabel(), new Integer(5));		       
					 player_1.addSettler(playerSettler);
					 playerSettler.setPosition(0, 13);
	             lb.addSettlerCount(0, 13);      
					 break;
			     case 1:
			       ch6.add(playerSettler.getLabel(), new Integer(5));		       
					 player_1.addSettler(playerSettler);
					 playerSettler.setPosition(1, 13);
	             lb.addSettlerCount(1, 13);      
				    break;
	 		     case 2:
			       ch7.add(playerSettler.getLabel(), new Integer(5));		       
					 player_1.addSettler(playerSettler);
					 playerSettler.setPosition(2, 13);
	             lb.addSettlerCount(2, 13);      
			       break; 
			     case 3:
			       ch9.add(playerSettler.getLabel(), new Integer(5));		       
					 player_1.addSettler(playerSettler);
					 playerSettler.setPosition(0, 14);
	             lb.addSettlerCount(0, 14);      
			       break;
			     case 4:
			       ch11.add(playerSettler.getLabel(), new Integer(5));		       
					 player_1.addSettler(playerSettler);
					 playerSettler.setPosition(2, 14);
	             lb.addSettlerCount(2, 14);      
				    break;
	 		     case 5:
			       ch13.add(playerSettler.getLabel(), new Integer(5));		       
					 player_1.addSettler(playerSettler);
					 playerSettler.setPosition(0, 15);
	             lb.addSettlerCount(0, 15);      
			       break; 
	 		     case 6:
			       ch15.add(playerSettler.getLabel(), new Integer(5));		       
					 player_1.addSettler(playerSettler);
					 playerSettler.setPosition(2, 15);
	             lb.addSettlerCount(2, 15);      
			       break; 
			   }
	
	         Object[] options_Army = {icon5, icon6, icon7, icon9, icon11, icon13, icon15};
			   int Army1_start = -1;
				playTrack("Army");
				while(Army1_start < 0)
				{    
				  Army1_start = start.showOptionDialog(c, "Choose Army square", "Player 1",
	                              JOptionPane.CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
	                              null, options_Army, null);
	         }  
	         switch(Army1_start)
			   {
			     case 0:
			       playerArmy1.setBounds(lb.getSettlerCount(0,13) , lb.getArmyCount(0,13));    
			       ch5.add(playerArmy1.getLabel(), new Integer(5));		       
					 player_1.addArmy(playerArmy1);
					 playerArmy1.setPosition(0, 13);
	             lb.addArmyCount(0, 13);      
					 break;
			     case 1:
			       playerArmy1.setBounds(lb.getSettlerCount(1,13) , lb.getArmyCount(1,13));    
			       ch6.add(playerArmy1.getLabel(), new Integer(5));		       
					 player_1.addArmy(playerArmy1);
					 playerArmy1.setPosition(1, 13);
	             lb.addArmyCount(1, 13);      
				    break;
	 		     case 2:
			       playerArmy1.setBounds(lb.getSettlerCount(2,13) , lb.getArmyCount(2,13));    
			       ch7.add(playerArmy1.getLabel(), new Integer(5));		       
					 player_1.addArmy(playerArmy1);
					 playerArmy1.setPosition(2, 13);
	             lb.addArmyCount(2, 13);      
			       break; 
			     case 3:
			       playerArmy1.setBounds(lb.getSettlerCount(0,14) , lb.getArmyCount(0,14));    
			       ch9.add(playerArmy1.getLabel(), new Integer(5));		       
					 player_1.addArmy(playerArmy1);
					 playerArmy1.setPosition(0, 14);
	             lb.addArmyCount(0, 14);      
			       break;
			     case 4:
			       playerArmy1.setBounds(lb.getSettlerCount(2,14) , lb.getArmyCount(2,14));    
			       ch11.add(playerArmy1.getLabel(), new Integer(5));		       
					 player_1.addArmy(playerArmy1);
					 playerArmy1.setPosition(2, 14);
	             lb.addArmyCount(2, 14);      
				    break;
	 		     case 5:
			       playerArmy1.setBounds(lb.getSettlerCount(0,15) , lb.getArmyCount(0,15));    
			       ch13.add(playerArmy1.getLabel(), new Integer(5));		       
					 player_1.addArmy(playerArmy1);
					 playerArmy1.setPosition(0, 15);
	             lb.addArmyCount(0, 15);      
			       break; 
	 		     case 6:
			       playerArmy1.setBounds(lb.getSettlerCount(2,15) , lb.getArmyCount(2,15));    
			       ch15.add(playerArmy1.getLabel(), new Integer(5));		       
					 player_1.addArmy(playerArmy1);
					 playerArmy1.setPosition(2, 15);
	             lb.addArmyCount(2, 15);      
			       break; 
			   }
		  	 }
			 else if(startChoice == 3)
	       {
	         playerCity1.setBounds(0, 0, iconCity1.getIconWidth(),
	                                         iconCity1.getIconHeight()); 
	         ch11.setPreferredSize(new Dimension(iconCity1.getIconWidth(),
	                                            iconCity1.getIconHeight()));
				ch11.add(playerCity1, new Integer(1));
	      
		      GameImageIcon icon8 = getImage("data/ch8.png");
		      GameImageIcon icon12 = getImage("data/ch12.png");
	         GameImageIcon icon15 = getImage("data/ch15.png");
	
	         Object[] options_Settler = {icon6, icon7, icon8, icon10, icon12, icon15};
			   int Settler_start = -1;
	         playTrack("Settler");         
				while(Settler_start < 0)
				{
				  Settler_start = start.showOptionDialog(c, "Choose Settler square", "Player 1",
	                              JOptionPane.CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
	                              null, options_Settler, null);
	         }
	
	         switch(Settler_start)
			   {
			     case 0:
			       ch6.add(playerSettler.getLabel(), new Integer(5));		       
					 player_1.addSettler(playerSettler);
					 playerSettler.setPosition(1, 13);
	             lb.addSettlerCount(1, 13);      
					 break;
			     case 1:
			       ch7.add(playerSettler.getLabel(), new Integer(5));		       
					 player_1.addSettler(playerSettler);
					 playerSettler.setPosition(2, 13);
	             lb.addSettlerCount(2, 13);      
				    break;
	 		     case 2:
			       ch8.add(playerSettler.getLabel(), new Integer(5));		       
					 player_1.addSettler(playerSettler);
					 playerSettler.setPosition(3, 13);
	             lb.addSettlerCount(3, 13);      
			       break; 
			     case 3:
			       ch10.add(playerSettler.getLabel(), new Integer(5));		       
					 player_1.addSettler(playerSettler);
					 playerSettler.setPosition(1, 14);
	             lb.addSettlerCount(1, 14);      
			       break;
			     case 4:
			       ch12.add(playerSettler.getLabel(), new Integer(5));		       
					 player_1.addSettler(playerSettler);
					 playerSettler.setPosition(3, 14);
	             lb.addSettlerCount(3, 14);      
				    break;
	 		     case 5:
			       ch15.add(playerSettler.getLabel(), new Integer(5));		       
					 player_1.addSettler(playerSettler);
					 playerSettler.setPosition(2, 15);
	             lb.addSettlerCount(2, 15);      
			       break; 
			   }
	
	         Object[] options_Army = {icon6, icon7, icon8, icon10, icon12, icon15};
			   int Army1_start = -1;
				playTrack("Army");
				while(Army1_start < 0)
				{    
				  Army1_start = start.showOptionDialog(c, "Choose Army square", "Player 1",
	                              JOptionPane.CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
	                              null, options_Army, null);
	         }  
	         playerArmy1.setBounds(30, 0,  
	                                  iconArmy.getIconWidth(),
	                                  iconArmy.getIconHeight()); 
	         switch(Army1_start)
			   {
			     case 0:
			       playerArmy1.setBounds(lb.getSettlerCount(1,13) , lb.getArmyCount(1,13));    
			       ch6.add(playerArmy1.getLabel(), new Integer(5));		       
					 player_1.addArmy(playerArmy1);
					 playerArmy1.setPosition(1, 13);
	             lb.addArmyCount(1, 13);      
					 break;
			     case 1:
			       playerArmy1.setBounds(lb.getSettlerCount(2,13) , lb.getArmyCount(2,13));    
			       ch7.add(playerArmy1.getLabel(), new Integer(5));		       
					 player_1.addArmy(playerArmy1);
					 playerArmy1.setPosition(2, 13);
	             lb.addArmyCount(2, 13);      
				    break;
	 		     case 2:
			       playerArmy1.setBounds(lb.getSettlerCount(3,13) , lb.getArmyCount(3,13));    
			       ch8.add(playerArmy1.getLabel(), new Integer(5));		       
					 player_1.addArmy(playerArmy1);
					 playerArmy1.setPosition(3, 13);
	             lb.addArmyCount(3, 13);      
			       break; 
			     case 3:
			       playerArmy1.setBounds(lb.getSettlerCount(1,14) , lb.getArmyCount(1,14));    
			       ch10.add(playerArmy1.getLabel(), new Integer(5));		       
					 player_1.addArmy(playerArmy1);
					 playerArmy1.setPosition(1, 14);
	             lb.addArmyCount(1, 14);      
			       break;
			     case 4:
			       playerArmy1.setBounds(lb.getSettlerCount(3,14) , lb.getArmyCount(3,14));    
			       ch12.add(playerArmy1.getLabel(), new Integer(5));		       
					 player_1.addArmy(playerArmy1);
					 playerArmy1.setPosition(3, 14);
	             lb.addArmyCount(3, 14);      
				    break;
	 		     case 5:
			       playerArmy1.setBounds(lb.getSettlerCount(2,15) , lb.getArmyCount(2,15));    
			       ch15.add(playerArmy1.getLabel(), new Integer(5));		       
					 player_1.addArmy(playerArmy1);
					 playerArmy1.setPosition(2, 15);
	             lb.addArmyCount(2, 15);      
			       break; 
			   }
		   }
		 }
		}
		*/
/* note: tech are assigned the following ints
0 animalHusbandry
1 codeOfLaws
2 currency
3 horsebackRiding
4 masonry
5 metalWorking
6 navigation
7 philosophy
8 pottery
9 writing //end level 1
10 chivalry
11 civilService
12 construction
13 democracy 
14 engineering
15 irrigation
16 mathematics
17 monarchy
18 printingPress
19 sailing //end level 2
20 banking
21 biology
22 communism
23 gunpowder
24 metalCasting
25 militaryScience
26 railroad
27 steamPower
28 theology //end level 3
29 atomicTheory
30 ballistics
31 combustion
32 computers
33 flight
34 massMedia
35 replaceableParts //end level 4
36 spaceFlight //level 5 gamewinner
*/