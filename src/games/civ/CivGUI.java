   package games.civ;

   import sun.audio.*;
   import java.net.URL;

   import java.awt.dnd.*;
   import java.awt.*;
   import java.awt.event.*;
   import java.awt.image.*;//bufferedimage;
   import javax.swing.*;
   import java.util.*;
   import java.io.*;
   import javax.swing.border.*;
   import java.awt.image.*;
   import java.beans.*;
   import gameClient.*;
   import games.*;


   public class CivGUI extends GameJFrame implements ActionListener, MouseListener, Cloneable, Serializable{

	   private static final long serialVersionUID = -5979889473064460750L;

      public ClientGUI clientGUI;
   
      final int TWO_PLAYER = 2;
      final int THREE_PLAYER = 3;
      final int FOUR_PLAYER = 4;
   
      final int MAX_MARKET = 5;
      final int MAX_BANK = 5;
      final int MAX_TEMPLE = 5;
      final int MAX_CATHEDRAL = 5;
      final int MAX_GRANARY = 6;
      final int MAX_AQUEDUCT = 6;
      final int MAX_LIBRARY = 6;
      final int MAX_UNIVERSITY = 6;
      final int MAX_BARRACKS = 5;
      final int MAX_ACADEMY = 5;
      final int MAX_WORKSHOP = 6;
      final int MAX_IRONMINE = 6;
      final int MAX_TRADEPOST = 6;
      final int MAX_HARBOR = 10;
   
      public int maxWheat, maxIron, maxIncense, maxSilk, numIron, numSilk, numWheat, numIncense,
              startCount, tradeCount, cityCount, moveCount, researchCount;
   
      public int availableMarket, availableBarrack, availableGranary, availableHarbor,
      		  availableLibrary, availableTemple, availableTradepost, availableWorkshop,
      		  availableAcademy, availableAqueduct, availableUniversity, availableBank,
      		  availableCathedral, availableIronmine;	                 
   
      public Player[] players;
      public GameJLabel[][] cultureTrack;//for culture track [numberplayers][num culture total spaces]
      public boolean gameEnd = false;
      public PlayerTurn turn;
//      public Die dice;
      public Deck combatDeck, wonderDeck, culture1Deck, culture2Deck, culture3Deck; 
      public Deck myWonderDeck, myCombatDeck, myCulture1Deck, myCulture2Deck, myCulture3Deck;  
      public Vector villages, huts, greatPeople, ready;
   
      public GameJPanel panel0, panel1, panel2, panel3, panel4, panel5, panel6, panel7;//, map;//, turnHolder;
   
      public GameJPanel mypanel0, mypanel1, mypanel2, mypanel3, mypanel4, mypanel5, mypanel6, mypanel7;
      public GameJLabel myfog1, myfog2, myfog3, myfog4, myfog5, myfog6;						
   
      public GameJLayeredPane am1, am2, am3, am4,
                               am5, am6, am7, am8,
      								 am9, am10, am11, am12,
      								 am13, am14, am15, am16; 
      public GameJLayeredPane ch1, ch2, ch3, ch4,
                               ch5, ch6, ch7, ch8,
      								 ch9, ch10, ch11, ch12,
      								 ch13, ch14, ch15, ch16; 
      public GameJLayeredPane egy1, egy2, egy3, egy4,
                               egy5, egy6, egy7, egy8,
      								 egy9, egy10, egy11, egy12,
      								 egy13, egy14, egy15, egy16;
      public GameJLayeredPane ger1, ger2, ger3, ger4,
                               ger5, ger6, ger7, ger8,
      								 ger9, ger10, ger11, ger12,
      								 ger13, ger14, ger15, ger16;
      public GameJLayeredPane ro1, ro2, ro3, ro4,
                               ro5, ro6, ro7, ro8,
      								 ro9, ro10, ro11, ro12,
      								 ro13, ro14, ro15, ro16;
      public GameJLayeredPane russ1, russ2, russ3, russ4,
                               russ5, russ6, russ7, russ8,
      								 russ9, russ10, russ11, russ12,
      								 russ13, russ14, russ15, russ16;										 										 
      public GameJLayeredPane  rand1_1, rand1_2, rand1_3, rand1_4,
                               rand1_5, rand1_6, rand1_7, rand1_8,
      								 rand1_9, rand1_10, rand1_11, rand1_12,
      								 rand1_13, rand1_14, rand1_15, rand1_16;
      public GameJLayeredPane  rand2_1, rand2_2, rand2_3, rand2_4,
                               rand2_5, rand2_6, rand2_7, rand2_8,
      								 rand2_9, rand2_10, rand2_11, rand2_12,
      								 rand2_13, rand2_14, rand2_15, rand2_16;
      public GameJLayeredPane  rand3_1, rand3_2, rand3_3, rand3_4,
                               rand3_5, rand3_6, rand3_7, rand3_8,
      								 rand3_9, rand3_10, rand3_11, rand3_12,
      								 rand3_13, rand3_14, rand3_15, rand3_16;										 
      public GameJLayeredPane  rand4_1, rand4_2, rand4_3, rand4_4,
                               rand4_5, rand4_6, rand4_7, rand4_8,
      								 rand4_9, rand4_10, rand4_11, rand4_12,
      								 rand4_13, rand4_14, rand4_15, rand4_16;										 
      public GameJLayeredPane  rand5_1, rand5_2, rand5_3, rand5_4,
                               rand5_5, rand5_6, rand5_7, rand5_8,
      								 rand5_9, rand5_10, rand5_11, rand5_12,
      								 rand5_13, rand5_14, rand5_15, rand5_16;										 
      public GameJLayeredPane  rand6_1, rand6_2, rand6_3, rand6_4,
                               rand6_5, rand6_6, rand6_7, rand6_8,
      								 rand6_9, rand6_10, rand6_11, rand6_12,
      								 rand6_13, rand6_14, rand6_15, rand6_16;										 
      public GameJLayeredPane  rand7_1, rand7_2, rand7_3, rand7_4,
                               rand7_5, rand7_6, rand7_7, rand7_8,
      								 rand7_9, rand7_10, rand7_11, rand7_12,
      								 rand7_13, rand7_14, rand7_15, rand7_16;										 
      public GameJLayeredPane  rand8_1, rand8_2, rand8_3, rand8_4,
                               rand8_5, rand8_6, rand8_7, rand8_8,
      								 rand8_9, rand8_10, rand8_11, rand8_12,
      								 rand8_13, rand8_14, rand8_15, rand8_16;										 
      public GameJLayeredPane  rand9_1, rand9_2, rand9_3, rand9_4,
                               rand9_5, rand9_6, rand9_7, rand9_8,
      								 rand9_9, rand9_10, rand9_11, rand9_12,
      								 rand9_13, rand9_14, rand9_15, rand9_16;										 
      public GameJLayeredPane  rand10_1, rand10_2, rand10_3, rand10_4,
                               rand10_5, rand10_6, rand10_7, rand10_8,
      								 rand10_9, rand10_10, rand10_11, rand10_12,
      								 rand10_13, rand10_14, rand10_15, rand10_16;										 
      public GameJLayeredPane  rand11_1, rand11_2, rand11_3, rand11_4,
                               rand11_5, rand11_6, rand11_7, rand11_8,
      								 rand11_9, rand11_10, rand11_11, rand11_12,
      								 rand11_13, rand11_14, rand11_15, rand11_16;										 
      public GameJLayeredPane  rand12_1, rand12_2, rand12_3, rand12_4,
                               rand12_5, rand12_6, rand12_7, rand12_8,
      								 rand12_9, rand12_10, rand12_11, rand12_12,
      								 rand12_13, rand12_14, rand12_15, rand12_16;										 
      public GameJLayeredPane  rand13_1, rand13_2, rand13_3, rand13_4,
                               rand13_5, rand13_6, rand13_7, rand13_8,
      								 rand13_9, rand13_10, rand13_11, rand13_12,
      								 rand13_13, rand13_14, rand13_15, rand13_16;										 
      public GameJLayeredPane  rand14_1, rand14_2, rand14_3, rand14_4,
                               rand14_5, rand14_6, rand14_7, rand14_8,
      								 rand14_9, rand14_10, rand14_11, rand14_12,
      								 rand14_13, rand14_14, rand14_15, rand14_16;
   
   
   
      public GameJLayeredPane  market, infLevelHolder, mntLevelHolder, artLevelHolder, acftLevelHolder;//cardHolder,										 
   //per player elements
      public Settler playerSettler, aiSettler;  
      public Army playerArmy1, aiArmy1, playerArmy2;
      public GameJLayeredPane others, mainBoard, mainBrd2;
   //   public GameJLayeredPane playerBrd2;
      public JTextArea showAvailableMarket, showAvailableBarrack, showAvailableGranary, showAvailableHarbor,
      							showAvailableLibrary, showAvailableTemple, showAvailableTradepost, showAvailableWorkshop,
      							showAvailableAcademy, showAvailableAqueduct, showAvailableUniversity, showAvailableBank,
      							showAvailableCathedral, showAvailableIronmine;
      public JTextArea marketIron, marketIncense, marketSilk, marketWheat;
      public GameJLabel playerBrd, mainBrd,
      			  fog1, fog2, fog3, fog4, fog5, fog6;
      public GameJFrame c;
      public GameJPanel map;
      public String playerChoice, aiChoice, playerColorChoice, aiColorChoice, userName;
      public LogicBoard lb;
      public String[] boardPosition;
      public GameJLabel[] wonderCards, wonderBuildings;
      public GameJButton[] otherBoards;
   
      private String[] playerName;
      private int playerNumber, gameType;
		private String gameID;
        
	
      public void mouseEntered(MouseEvent e)	{}//end mouseEntered
      public void mousePressed(MouseEvent e){}//end mousePressed
      public void mouseReleased(MouseEvent e){}//end mouseReleased
      public void mouseExited(MouseEvent e){}//end mouseExited
   
      public void actionPerformed(ActionEvent e)
      {}//end actionPerformed(e)
   
	   private void updateThreads(CivGUI cGUI){
	      cGUI.getClientGUI().updateCivGames(cGUI);	
		}

      public CivGUI(){}//bean default
   
      public CivGUI(String playerChoice, String aiChoice,
                 String playerColorChoice, String aiColorChoice,
      			  int numPlayers, int gameType, ClientGUI clientGUI,
      			  String[] playerName, String gameID){
      
         this.gameID = gameID;
         this.playerChoice = playerChoice;
System.out.println("CivGUI " + playerChoice);
         this.aiChoice = aiChoice;
System.out.println("CivGUI " + aiChoice);
         this.playerColorChoice = playerColorChoice;
System.out.println("CivGUI " + playerColorChoice);
         this.aiColorChoice = aiColorChoice; 
System.out.println("CivGUI " + aiColorChoice);
         this.gameType = gameType;
System.out.println("CivGUI " + gameType);
         this.clientGUI = clientGUI;
System.out.println("CivGUI " + clientGUI);
         this.playerName = playerName;
System.out.println("CivGUI " + playerName);

         this.userName = clientGUI.getHandle().getUserName();
      
System.out.println("UserName " + clientGUI.getHandle().getUserName());

         playerNumber = 0;
      
         villages = new Vector(0, 1);
         huts = new Vector(0, 1);
         greatPeople = new Vector(0, 1);
      
         players = new Player[numPlayers];
			
         ready = new Vector(0, 1);
      	  
         combatDeck = new Deck();
			wonderDeck = new Deck();
			culture1Deck = new Deck();
			culture2Deck = new Deck();
			culture3Deck = new Deck();
			myWonderDeck = new Deck();
			myCombatDeck = new Deck();
			myCulture1Deck = new Deck();
			myCulture2Deck = new Deck();
			myCulture3Deck = new Deck();

         setRandomBoard();
      
System.out.println("Creating player 1");			
         createPlayer(playerNumber);

//System.out.println("Creating player 2");			
//         createPlayer(playerNumber+1);

         players[playerNumber].initStartCity();
         players[playerNumber].getPlayerUI().otherBoards = new GameJButton[1]; //number of players-1
      }
   
      public CivGUI(CivGUI game, ClientGUI clientGUI){
      
System.out.println("CIVGUI con " + game + " "+clientGUI);
         this.gameID = game.gameID;
         this.playerChoice = game.playerChoice;
         this.aiChoice = game.aiChoice;
         this.playerColorChoice = game.playerColorChoice;
         this.aiColorChoice = game.aiColorChoice; 
         this.gameType = game.gameType;
         this.clientGUI = clientGUI;
         this.playerName = game.playerName;

System.out.println("CIVGUI con username " + clientGUI.getHandle().getUserName());
         this.userName = clientGUI.getHandle().getUserName();
			
         playerNumber = 1;
      
         villages = game.villages;
         huts = game.huts;
         greatPeople = game.greatPeople;
      
System.out.println("PLAYERS "+ game.players);
         players = game.players;
//         players = new Player[2];

System.out.println("ready " + game.ready);
         ready = game.ready;
//System.out.println("ready " + ready.get(0));
         
			this.boardPosition = game.boardPosition;      
         this.panel0 = game.panel0;
         this.panel1 = game.panel1;
         this.panel2 = game.panel2;
         this.panel3 = game.panel3;
         this.panel4 = game.panel4;
         this.panel5 = game.panel5;
         this.panel6 = game.panel6;
         this.panel7 = game.panel7;
System.out.println("pass 1");

         createPlayer(playerNumber);
System.out.println("pass 2");

         players[playerNumber].initStartCity();
System.out.println("pass 3");
      //      for(int k = 0; k < players.length; k++){
      //		   players[playerNumber].initStartCity();
         players[playerNumber].getPlayerUI().otherBoards = new GameJButton[1]; //number of players-1
      //		}
System.out.println("end constructor");
      }
   
      public CivGUI(Player p, CivGUI cGUI)
      {
         this.clientGUI = cGUI.clientGUI;
			this.gameID = cGUI.gameID;
			
         this.playerChoice = cGUI.playerChoice;
         this.aiChoice = cGUI.aiChoice;
         this.playerColorChoice = cGUI.playerColorChoice;
         this.aiColorChoice = cGUI.aiColorChoice;
      
		   this.playerName = cGUI.playerName;    

         this.userName = cGUI.getUserName();
		  
         this.villages = cGUI.villages;
         this.huts = cGUI.huts;
         this.greatPeople = cGUI.greatPeople;
         this.players = cGUI.players;
         ready = cGUI.ready;
      
         lb = new LogicBoard("dummy", -1, p);

         this.rand1_1 = new GameJLayeredPane();
         this.rand1_2 = new GameJLayeredPane();
         this.rand1_3 = new GameJLayeredPane();
         this.rand1_4 = new GameJLayeredPane();
         this.rand1_5 = new GameJLayeredPane();
         this.rand1_6 = new GameJLayeredPane();
         this.rand1_7 = new GameJLayeredPane();
         this.rand1_8 = new GameJLayeredPane();
         this.rand1_9 = new GameJLayeredPane();
         this.rand1_10 = new GameJLayeredPane();
         this.rand1_11 = new GameJLayeredPane();
         this.rand1_12 = new GameJLayeredPane();
         this.rand1_13 = new GameJLayeredPane();
         this.rand1_14 = new GameJLayeredPane();
         this.rand1_15 = new GameJLayeredPane();
         this.rand1_16 = new GameJLayeredPane();
      
         this.rand2_1 = new GameJLayeredPane();
         this.rand2_2 = new GameJLayeredPane();
         this.rand2_3 = new GameJLayeredPane();
         this.rand2_4 = new GameJLayeredPane();
         this.rand2_5 = new GameJLayeredPane();
         this.rand2_6 = new GameJLayeredPane();
         this.rand2_7 = new GameJLayeredPane();
         this.rand2_8 = new GameJLayeredPane();
         this.rand2_9 = new GameJLayeredPane();
         this.rand2_10 = new GameJLayeredPane();
         this.rand2_11 = new GameJLayeredPane();
         this.rand2_12 = new GameJLayeredPane();
         this.rand2_13 = new GameJLayeredPane();
         this.rand2_14 = new GameJLayeredPane();
         this.rand2_15 = new GameJLayeredPane();
         this.rand2_16 = new GameJLayeredPane();
      	  
         this.rand3_1 = new GameJLayeredPane();
         this.rand3_2 = new GameJLayeredPane();
         this.rand3_3 = new GameJLayeredPane();
         this.rand3_4 = new GameJLayeredPane();
         this.rand3_5 = new GameJLayeredPane();
         this.rand3_6 = new GameJLayeredPane();
         this.rand3_7 = new GameJLayeredPane();
         this.rand3_8 = new GameJLayeredPane();
         this.rand3_9 = new GameJLayeredPane();
         this.rand3_10 = new GameJLayeredPane();
         this.rand3_11 = new GameJLayeredPane();
         this.rand3_12 = new GameJLayeredPane();
         this.rand3_13 = new GameJLayeredPane();
         this.rand3_14 = new GameJLayeredPane();
         this.rand3_15 = new GameJLayeredPane();
         this.rand3_16 = new GameJLayeredPane();
      	  
         this.rand4_1 = new GameJLayeredPane();
         this.rand4_2 = new GameJLayeredPane();
         this.rand4_3 = new GameJLayeredPane();
         this.rand4_4 = new GameJLayeredPane();
         this.rand4_5 = new GameJLayeredPane();
         this.rand4_6 = new GameJLayeredPane();
         this.rand4_7 = new GameJLayeredPane();
         this.rand4_8 = new GameJLayeredPane();
         this.rand4_9 = new GameJLayeredPane();
         this.rand4_10 = new GameJLayeredPane();
         this.rand4_11 = new GameJLayeredPane();
         this.rand4_12 = new GameJLayeredPane();
         this.rand4_13 = new GameJLayeredPane();
         this.rand4_14 = new GameJLayeredPane();
         this.rand4_15 = new GameJLayeredPane();
         this.rand4_16 = new GameJLayeredPane();
      
         this.rand5_1 = new GameJLayeredPane();
         this.rand5_2 = new GameJLayeredPane();
         this.rand5_3 = new GameJLayeredPane();
         this.rand5_4 = new GameJLayeredPane();
         this.rand5_5 = new GameJLayeredPane();
         this.rand5_6 = new GameJLayeredPane();
         this.rand5_7 = new GameJLayeredPane();
         this.rand5_8 = new GameJLayeredPane();
         this.rand5_9 = new GameJLayeredPane();
         this.rand5_10 = new GameJLayeredPane();
         this.rand5_11 = new GameJLayeredPane();
         this.rand5_12 = new GameJLayeredPane();
         this.rand5_13 = new GameJLayeredPane();
         this.rand5_14 = new GameJLayeredPane();
         this.rand5_15 = new GameJLayeredPane();
         this.rand5_16 = new GameJLayeredPane();
      	  
         this.rand6_1 = new GameJLayeredPane();
         this.rand6_2 = new GameJLayeredPane();
         this.rand6_3 = new GameJLayeredPane();
         this.rand6_4 = new GameJLayeredPane();
         this.rand6_5 = new GameJLayeredPane();
         this.rand6_6 = new GameJLayeredPane();
         this.rand6_7 = new GameJLayeredPane();
         this.rand6_8 = new GameJLayeredPane();
         this.rand6_9 = new GameJLayeredPane();
         this.rand6_10 = new GameJLayeredPane();
         this.rand6_11 = new GameJLayeredPane();
         this.rand6_12 = new GameJLayeredPane();
         this.rand6_13 = new GameJLayeredPane();
         this.rand6_14 = new GameJLayeredPane();
         this.rand6_15 = new GameJLayeredPane();
         this.rand6_16 = new GameJLayeredPane();
      
         this.rand7_1 = new GameJLayeredPane();
         this.rand7_2 = new GameJLayeredPane();
         this.rand7_3 = new GameJLayeredPane();
         this.rand7_4 = new GameJLayeredPane();
         this.rand7_5 = new GameJLayeredPane();
         this.rand7_6 = new GameJLayeredPane();
         this.rand7_7 = new GameJLayeredPane();
         this.rand7_8 = new GameJLayeredPane();
         this.rand7_9 = new GameJLayeredPane();
         this.rand7_10 = new GameJLayeredPane();
         this.rand7_11 = new GameJLayeredPane();
         this.rand7_12 = new GameJLayeredPane();
         this.rand7_13 = new GameJLayeredPane();
         this.rand7_14 = new GameJLayeredPane();
         this.rand7_15 = new GameJLayeredPane();
         this.rand7_16 = new GameJLayeredPane();
      
         this.rand8_1 = new GameJLayeredPane();
         this.rand8_2 = new GameJLayeredPane();
         this.rand8_3 = new GameJLayeredPane();
         this.rand8_4 = new GameJLayeredPane();
         this.rand8_5 = new GameJLayeredPane();
         this.rand8_6 = new GameJLayeredPane();
         this.rand8_7 = new GameJLayeredPane();
         this.rand8_8 = new GameJLayeredPane();
         this.rand8_9 = new GameJLayeredPane();
         this.rand8_10 = new GameJLayeredPane();
         this.rand8_11 = new GameJLayeredPane();
         this.rand8_12 = new GameJLayeredPane();
         this.rand8_13 = new GameJLayeredPane();
         this.rand8_14 = new GameJLayeredPane();
         this.rand8_15 = new GameJLayeredPane();
         this.rand8_16 = new GameJLayeredPane();
      
         this.rand9_1 = new GameJLayeredPane();
         this.rand9_2 = new GameJLayeredPane();
         this.rand9_3 = new GameJLayeredPane();
         this.rand9_4 = new GameJLayeredPane();
         this.rand9_5 = new GameJLayeredPane();
         this.rand9_6 = new GameJLayeredPane();
         this.rand9_7 = new GameJLayeredPane();
         this.rand9_8 = new GameJLayeredPane();
         this.rand9_9 = new GameJLayeredPane();
         this.rand9_10 = new GameJLayeredPane();
         this.rand9_11 = new GameJLayeredPane();
         this.rand9_12 = new GameJLayeredPane();
         this.rand9_13 = new GameJLayeredPane();
         this.rand9_14 = new GameJLayeredPane();
         this.rand9_15 = new GameJLayeredPane();
         this.rand9_16 = new GameJLayeredPane();
      
         this.rand10_1 = new GameJLayeredPane();
         this.rand10_2 = new GameJLayeredPane();
         this.rand10_3 = new GameJLayeredPane();
         this.rand10_4 = new GameJLayeredPane();
         this.rand10_5 = new GameJLayeredPane();
         this.rand10_6 = new GameJLayeredPane();
         this.rand10_7 = new GameJLayeredPane();
         this.rand10_8 = new GameJLayeredPane();
         this.rand10_9 = new GameJLayeredPane();
         this.rand10_10 = new GameJLayeredPane();
         this.rand10_11 = new GameJLayeredPane();
         this.rand10_12 = new GameJLayeredPane();
         this.rand10_13 = new GameJLayeredPane();
         this.rand10_14 = new GameJLayeredPane();
         this.rand10_15 = new GameJLayeredPane();
         this.rand10_16 = new GameJLayeredPane();
      
         this.rand11_1 = new GameJLayeredPane();
         this.rand11_2 = new GameJLayeredPane();
         this.rand11_3 = new GameJLayeredPane();
         this.rand11_4 = new GameJLayeredPane();
         this.rand11_5 = new GameJLayeredPane();
         this.rand11_6 = new GameJLayeredPane();
         this.rand11_7 = new GameJLayeredPane();
         this.rand11_8 = new GameJLayeredPane();
         this.rand11_9 = new GameJLayeredPane();
         this.rand11_10 = new GameJLayeredPane();
         this.rand11_11 = new GameJLayeredPane();
         this.rand11_12 = new GameJLayeredPane();
         this.rand11_13 = new GameJLayeredPane();
         this.rand11_14 = new GameJLayeredPane();
         this.rand11_15 = new GameJLayeredPane();
         this.rand11_16 = new GameJLayeredPane();
      
         this.rand12_1 = new GameJLayeredPane();
         this.rand12_2 = new GameJLayeredPane();
         this.rand12_3 = new GameJLayeredPane();
         this.rand12_4 = new GameJLayeredPane();
         this.rand12_5 = new GameJLayeredPane();
         this.rand12_6 = new GameJLayeredPane();
         this.rand12_7 = new GameJLayeredPane();
         this.rand12_8 = new GameJLayeredPane();
         this.rand12_9 = new GameJLayeredPane();
         this.rand12_10 = new GameJLayeredPane();
         this.rand12_11 = new GameJLayeredPane();
         this.rand12_12 = new GameJLayeredPane();
         this.rand12_13 = new GameJLayeredPane();
         this.rand12_14 = new GameJLayeredPane();
         this.rand12_15 = new GameJLayeredPane();
         this.rand12_16 = new GameJLayeredPane();
      	  
         this.rand13_1 = new GameJLayeredPane();
         this.rand13_2 = new GameJLayeredPane();
         this.rand13_3 = new GameJLayeredPane();
         this.rand13_4 = new GameJLayeredPane();
         this.rand13_5 = new GameJLayeredPane();
         this.rand13_6 = new GameJLayeredPane();
         this.rand13_7 = new GameJLayeredPane();
         this.rand13_8 = new GameJLayeredPane();
         this.rand13_9 = new GameJLayeredPane();
         this.rand13_10 = new GameJLayeredPane();
         this.rand13_11 = new GameJLayeredPane();
         this.rand13_12 = new GameJLayeredPane();
         this.rand13_13 = new GameJLayeredPane();
         this.rand13_14 = new GameJLayeredPane();
         this.rand13_15 = new GameJLayeredPane();
         this.rand13_16 = new GameJLayeredPane();
      	  
         this.rand14_1 = new GameJLayeredPane();
         this.rand14_2 = new GameJLayeredPane();
         this.rand14_3 = new GameJLayeredPane();
         this.rand14_4 = new GameJLayeredPane();
         this.rand14_5 = new GameJLayeredPane();
         this.rand14_6 = new GameJLayeredPane();
         this.rand14_7 = new GameJLayeredPane();
         this.rand14_8 = new GameJLayeredPane();
         this.rand14_9 = new GameJLayeredPane();
         this.rand14_10 = new GameJLayeredPane();
         this.rand14_11 = new GameJLayeredPane();
         this.rand14_12 = new GameJLayeredPane();
         this.rand14_13 = new GameJLayeredPane();
         this.rand14_14 = new GameJLayeredPane();
         this.rand14_15 = new GameJLayeredPane();
         this.rand14_16 = new GameJLayeredPane();
      
         gameBoard(cGUI);
      }

      public void playerReady(Player p)
      {
System.out.println("Entered player ready ");
         if(!p.playerUI.ready.contains(p)){
			   p.playerUI.ready.add(p.playerUI.playerNumber, p);
System.out.println("added player "+p);
	         updateThreads(p.playerUI);
//            updateGUI(p.playerUI);
System.out.println("READY "+p.playerUI.ready.size());
         }
      }
		
		public void initGameStart(Player p){
System.out.println("INIT START GAME" + p.getNation());	

//            Player tempPlayer;
//            for(int k = 0; k < ready.size(); k++){
//            tempPlayer = (Player)ready.get(k);            

//            if(tempPlayer.getNation().equals(p.getNation())){ 
            drawCombatholder(p);
System.out.println("DONE DRAWING COMBAT HOLDER");	
//            }
					
            if(players[0].getNation().equals("America") && p != players[0]){
               p.getPlayerUI().initPlayerMap("America", p, 3);
System.out.println("ADDED AMERICA IN 3");	
            }else if(players[0].getNation().equals("China") && p != players[0]){
               p.getPlayerUI().initPlayerMap("China", p, 3);
System.out.println("ADDED CHINA IN 3");	
            }else if(players[1].getNation().equals("America") && p != players[1]){
               p.getPlayerUI().initPlayerMap("America", p, 4);
System.out.println("ADDED AMERICA IN 4");	
            }else if(players[1].getNation().equals("China") && p != players[1]){
               p.getPlayerUI().initPlayerMap("China", p, 4);
System.out.println("ADDED CHINA IN 4");	
            }

            p.getPlayerUI().validate();        
            p.getPlayerUI().repaint();        
//				}    
/*         
System.out.println("pass 2");	

            Vector tempCities;
            Player[] tempPlacement = p.playerUI.getPlayers();
            for(int k = 0; k < temp.length; k++){        
               tempCities = temp[k].cities;          
System.out.println("temp city "+temp[k].cities);	
               for(int j = 0; j < tempCities.size(); j++){  
                  for(int i = 0; i < temp.length; i++){
                     City tempCity = (City)tempCities.get(i);
                     tempPlacement[i].playerUI.lb.setOwner(tempCity.getXPos(), tempCity.getYPos(), temp[k]);
                     tempPlacement[i].playerUI.lb.addPeice(2, tempCity.getXPos(), tempCity.getYPos());	  
                  }
               }
            }    
System.out.println("pass 3");	
         
         //        for(int k = 0; k < players.length; k++)
         //           players[k].setFirstTurn(true);
            for(int k = 0; k < players.length; k++){
               drawPlayerStart(players[k]);
               for(int j = 0; j < players.length; j++){
                  if(players[k] != players[j])  
                     players[k].getPlayerUI().addOtherBoards(players[j]);
               }
            }
*/         
System.out.println("pass 4");	
         //		  players[0].setFirstTurn(true);      
            p.playerUI.turn = new PlayerTurn();
System.out.println("pass 5");	
         
//            for(int k = 0; k < players.length; k++){
//               turn[k] = new PlayerTurn();
//            }    
System.out.println("pass 6");	
         
	         drawWonders();
System.out.println("pass 7");	
	         drawCombatDecks();
System.out.println("pass 8");	

            setSpaceOwners();
System.out.println("pass 9");	
            playGame(p); 
System.out.println("pass 10");	
      } 
		
   
      public void addOtherBoards(Player p)
      {
         Font f = new Font("Monospaced", Font.BOLD, 10);
      
         others = new GameJLayeredPane();
      
         otherBoards[0] = new GameJButton(getImage("data/"+p.getNation()+"Board.png"));
         otherBoards[0].setName(p.getNation());
         otherBoards[0].setBounds(00, 00, 60, 40);
         otherBoards[0].setFont(f);
         Insets in = new Insets(9, 9, 9, 9);
         otherBoards[0].setMargin(in);
         otherBoards[0].addMouseListener(this);
      	  
         JTextArea click = new JTextArea("click to see opponent board");
         click.setFont(f);
         click.setForeground(new Color(255, 255, 255));
         click.setBounds(10, 40, 180, 15);
         click.setOpaque(false);
         click.setEditable(false);
      
         others.add(otherBoards[0], new Integer(1));
         others.add(click, new Integer(1));
         others.setBounds(640, 415, 180, 55);
         others.setBackground(new Color(6, 60, 60));
         others.setOpaque(false);
      
         mainBoard.add(others, new Integer(1));
      }
   
      @Override
      public Object clone()
      {
         try
         {
         //System.out.println("CLONED");
            CivGUI c = (CivGUI)super.clone();
            return c;  
         }
            catch( CloneNotSupportedException e )
            {  
            //System.out.println("FAILED CLONED");
               return null;
            }
      } 
   
      private void drawCombatholder(Player p)
      {
System.out.println("DREW COMBAT HOLDER");	
         p.playerUI.infLevelHolder = new GameJLayeredPane();
         p.playerUI.mntLevelHolder = new GameJLayeredPane();
         p.playerUI.artLevelHolder = new GameJLayeredPane();
         p.playerUI.acftLevelHolder = new GameJLayeredPane();										 
      
         p.playerUI.infLevelHolder.setBounds(445, 133, 150, 30);
         p.playerUI.mntLevelHolder.setBounds(445, 302, 150, 30);
         p.playerUI.artLevelHolder.setBounds(633, 133, 150, 30);
         p.playerUI.acftLevelHolder.setBounds(633, 302, 150, 30);										 
      
System.out.println("DREW COMBAT HOLDER");	
         for(int k = 0; k < players.length - 1; k++)//number of players 
         {
System.out.println(players.length);	
System.out.println(players[k].getColor());	
            GameJLabel inf = new GameJLabel(getImage("data/infLevel_1_"+players[k].getColor()+".png"));
            inf.setBounds(00+(35*k), 00, 30, 30);
            p.playerUI.infLevelHolder.add(inf, new Integer(0));
         
            GameJLabel mnt = new GameJLabel(getImage("data/mntLevel_1_"+players[k].getColor()+".png"));
            mnt.setBounds(00+(35*k), 00, 30, 30);
            p.playerUI.mntLevelHolder.add(mnt, new Integer(0));
         
            GameJLabel art = new GameJLabel(getImage("data/artLevel_1_"+players[k].getColor()+".png"));
            art.setBounds(00+(35*k), 00, 30, 30);
            p.playerUI.artLevelHolder.add(art, new Integer(0));
         
            GameJLabel acft = new GameJLabel(getImage("data/acft_back.png"));
            acft.setBounds(00+(35*k), 00, 30, 30);
            p.playerUI.acftLevelHolder.add(acft, new Integer(0));
         
//            if(k == (players.length - 1))//number of players (0-x)
//            {
//               k = 0;
//               m++;
//            System.out.println("M "+m);
//            }
//            else
//               k++;
         	System.out.println(players[k].getNation());
         }
      
System.out.println("DREW COMBAT HOLDER");	
         p.playerUI.mainBrd2.add(p.playerUI.infLevelHolder, new Integer(5));
         p.playerUI.mainBrd2.add(p.playerUI.mntLevelHolder, new Integer(5));
         p.playerUI.mainBrd2.add(p.playerUI.artLevelHolder, new Integer(5));
         p.playerUI.mainBrd2.add(p.playerUI.acftLevelHolder, new Integer(5));
         p.playerUI.validate();    
		}
   
      public void updateCombatHolder(String type, GameJLabel l, Player p)
      {
         if(type.equals("infantry"))
         {
            for(int i = 0; i < 2; i++)//number of players
            {
               players[i].playerUI.infLevelHolder.removeAll();
               for(int j = 0; j < 2; j++)//number of players
               {
                  GameJLabel lbl = new GameJLabel(getImage("data/infLevel_"+players[j].getInfLevel()+"_"+players[j].getColor()+".png"));      
                  lbl.setBounds(00+(35*j), 00, 30, 30);
                  players[i].playerUI.infLevelHolder.add(lbl, new Integer(0));
                  players[i].playerUI.infLevelHolder.repaint();
               }        
            }
         }
         else if(type.equals("mounted"))
         {
            for(int i = 0; i < 2; i++)//number of players
            {
               players[i].playerUI.mntLevelHolder.removeAll();
               for(int j = 0; j < 2; j++)//number of players
               {
                  GameJLabel lbl = new GameJLabel(getImage("data/mntLevel_"+players[j].getMntLevel()+"_"+players[j].getColor()+".png"));      
                  lbl.setBounds(00+(35*j), 00, 30, 30);
                  players[i].playerUI.mntLevelHolder.add(lbl, new Integer(0));
                  players[i].playerUI.mntLevelHolder.repaint();
               }
            }
         }
         if(type.equals("artillery"))
         {
            for(int i = 0; i < 2; i++)//number of players
            {
               players[i].playerUI.artLevelHolder.removeAll();
               for(int j = 0; j < 2; j++)//number of players
               {
                  GameJLabel lbl = new GameJLabel(getImage("data/artLevel_"+players[j].getArtLevel()+"_"+players[j].getColor()+".png"));      
                  lbl.setBounds(00+(35*j), 00, 30, 30);
                  players[i].playerUI.artLevelHolder.add(lbl, new Integer(0));
                  players[i].playerUI.artLevelHolder.repaint();
               }
            }
         }
         if(type.equals("aircraft"))
         {
            for(int i = 0; i < 2; i++)//number of players
            {
               players[i].playerUI.acftLevelHolder.removeAll();
               for(int j = 0; j < 2; j++)//number of players
               {
                  GameJLabel lbl = new GameJLabel(getImage("data/acft_front_"+players[j].getColor()+".png"));      
                  lbl.setBounds(00+(35*j), 00, 30, 30);
                  players[i].playerUI.acftLevelHolder.add(lbl, new Integer(0));
                  players[i].playerUI.acftLevelHolder.repaint();
               }
            }
         }
      }
   
      public static void drawGovernment(int gov, GameJLabel label)
      {
         if(gov == 0)
         {
            label.setIcon(getStaticImage("data/anarchyGOV.png"));
         }  
         else if(gov == 1)
         {
            label.setIcon(getStaticImage("data/republicGOV.png"));
         }  
         else if(gov == 2)
         {
            label.setIcon(getStaticImage("data/democracyGOV.png"));
         }  
         else if(gov == 3)
         {
            label.setIcon(getStaticImage("data/monarchyGOV.png"));
         }  
         else if(gov == 4)
         {
            label.setIcon(getStaticImage("data/feudalismGOV.png"));
         }  
         else if(gov == 5)
         {
            label.setIcon(getStaticImage("data/communismGOV.png"));
         }  
         else if(gov == 6)
         {
            label.setIcon(getStaticImage("data/fundamentalismGOV.png"));
         }  
         else if(gov == 7)
         {
            label.setIcon(getStaticImage("data/despotismGOV.png"));
         }  
         label.setBounds(11, 125, label.getIcon().getIconWidth(), label.getIcon().getIconHeight());
      }
   
      private void createPlayer(int playerNum)
      {
         if(gameType != 1){//not a single player game  
         //	     for(int k = 0; k < players.length; k++){//for some type of holder for nation strings
            if(playerNum == 0){
               createNation(playerChoice, playerColorChoice, 0);
            }
            else if(playerNum == 1){
               System.out.println("pass 1");
               createNation(aiChoice, aiColorChoice, 1);
            }
         }	  
      }
   
      private void createNation(String nation, String color, int playerNumber){
         System.out.println("NATION "+nation);
         System.out.println("COLOR "+color);
         players[playerNumber] = new Player(nation, color, this, playerNumber);  
			System.out.println("pass CreateNation");
      }
   
      public Player[] getPlayers(){
         return players;
      }
   
      public void setPlayers(Player[] players){
         this.players = players;
      }

      public void initPlayerMap(String nation, Player p, int startPosition){
      //     for(int k = 0; k < players.length; k++){
         if(nation.equals("America")){
            this.am1 = new GameJLayeredPane();
            this.am2 = new GameJLayeredPane();
            this.am3 = new GameJLayeredPane();
            this.am4 = new GameJLayeredPane();
            this.am5 = new GameJLayeredPane();
            this.am6 = new GameJLayeredPane();
            this.am7 = new GameJLayeredPane();
            this.am8 = new GameJLayeredPane();
            this.am9 = new GameJLayeredPane();
            this.am10 = new GameJLayeredPane();
            this.am11 = new GameJLayeredPane();
            this.am12 = new GameJLayeredPane();
            this.am13 = new GameJLayeredPane();
            this.am14 = new GameJLayeredPane();
            this.am15 = new GameJLayeredPane();
            this.am16 = new GameJLayeredPane();
         
            GameImageIcon iconAi1 = getImage("data/am16rev.png");
            GameImageIcon iconAi2 = getImage("data/am15rev.png");
            GameImageIcon iconAi3 = getImage("data/am14rev.png");
            GameImageIcon iconAi4 = getImage("data/am13rev.png");
            GameImageIcon iconAi5 = getImage("data/am12rev.png");
            GameImageIcon iconAi6 = getImage("data/am11rev.png");
            GameImageIcon iconAi7 = getImage("data/am10rev.png");
            GameImageIcon iconAi8 = getImage("data/am9rev.png");
            GameImageIcon iconAi9 = getImage("data/am8rev.png");
            GameImageIcon iconAi10 = getImage("data/am7rev.png");
            GameImageIcon iconAi11 = getImage("data/am6rev.png");
            GameImageIcon iconAi12 = getImage("data/am5rev.png");
            GameImageIcon iconAi13 = getImage("data/am4rev.png");
            GameImageIcon iconAi14 = getImage("data/am3rev.png");
            GameImageIcon iconAi15 = getImage("data/am2rev.png");
            GameImageIcon iconAi16 = getImage("data/am1rev.png");
         
         //add components to board
            GameJLabel boardAi1 = new GameJLabel(iconAi1);
            GameJLabel boardAi2 = new GameJLabel(iconAi2);
            GameJLabel boardAi3 = new GameJLabel(iconAi3);
            GameJLabel boardAi4 = new GameJLabel(iconAi4);
            GameJLabel boardAi5 = new GameJLabel(iconAi5);
            GameJLabel boardAi6 = new GameJLabel(iconAi6);
            GameJLabel boardAi7 = new GameJLabel(iconAi7);
            GameJLabel boardAi8 = new GameJLabel(iconAi8);
            GameJLabel boardAi9 = new GameJLabel(iconAi9);
            GameJLabel boardAi10 = new GameJLabel(iconAi10);
            GameJLabel boardAi11 = new GameJLabel(iconAi11);
            GameJLabel boardAi12 = new GameJLabel(iconAi12);
            GameJLabel boardAi13 = new GameJLabel(iconAi13);
            GameJLabel boardAi14 = new GameJLabel(iconAi14);
            GameJLabel boardAi15 = new GameJLabel(iconAi15);
            GameJLabel boardAi16 = new GameJLabel(iconAi16);
         
            this.am1.add(boardAi1, new Integer(0));
            this.am2.add(boardAi2, new Integer(0));
            this.am3.add(boardAi3, new Integer(0));
            this.am4.add(boardAi4, new Integer(0));
            this.am5.add(boardAi5, new Integer(0));
            this.am6.add(boardAi6, new Integer(0));
            this.am7.add(boardAi7, new Integer(0));
            this.am8.add(boardAi8, new Integer(0));
            this.am9.add(boardAi9, new Integer(0));
            this.am10.add(boardAi10, new Integer(0));
            this.am11.add(boardAi11, new Integer(0));
            this.am12.add(boardAi12, new Integer(0));
            this.am13.add(boardAi13, new Integer(0));
            this.am14.add(boardAi14, new Integer(0));
            this.am15.add(boardAi15, new Integer(0));
            this.am16.add(boardAi16, new Integer(0));
         
            boardAi1.setBounds(0, 0, iconAi1.getIconWidth(),
                                        iconAi1.getIconHeight()); 
            this.am1.setPreferredSize(new Dimension(iconAi1.getIconWidth(),
                                              iconAi1.getIconHeight()));
            boardAi2.setBounds(0, 0, iconAi2.getIconWidth(),
                                        iconAi2.getIconHeight()); 
            this.am2.setPreferredSize(new Dimension(iconAi2.getIconWidth(),
                                              iconAi2.getIconHeight()));
            boardAi3.setBounds(0, 0, iconAi3.getIconWidth(),
                                        iconAi3.getIconHeight()); 
            this.am3.setPreferredSize(new Dimension(iconAi3.getIconWidth(),
                                                 iconAi3.getIconHeight()));
            boardAi4.setBounds(0, 0, iconAi4.getIconWidth(),
                                        iconAi4.getIconHeight()); 
            this.am4.setPreferredSize(new Dimension(iconAi4.getIconWidth(),
                                              iconAi4.getIconHeight()));
            boardAi5.setBounds(0, 0, iconAi5.getIconWidth(),
                                        iconAi5.getIconHeight()); 
            this.am5.setPreferredSize(new Dimension(iconAi5.getIconWidth(),
                                              iconAi5.getIconHeight()));
            boardAi6.setBounds(0, 0, iconAi6.getIconWidth(),
                                           iconAi6.getIconHeight()); 
            this.am6.setPreferredSize(new Dimension(iconAi6.getIconWidth(),
                                              iconAi6.getIconHeight()));
            boardAi7.setBounds(0, 0, iconAi7.getIconWidth(),
                                        iconAi7.getIconHeight()); 
            this.am7.setPreferredSize(new Dimension(iconAi7.getIconWidth(),
                                              iconAi7.getIconHeight()));
            boardAi8.setBounds(0, 0, iconAi8.getIconWidth(),
                                        iconAi8.getIconHeight()); 
            this.am8.setPreferredSize(new Dimension(iconAi8.getIconWidth(),
                                              iconAi8.getIconHeight()));
            boardAi9.setBounds(0, 0, iconAi9.getIconWidth(),
                                        iconAi9.getIconHeight()); 
            this.am9.setPreferredSize(new Dimension(iconAi9.getIconWidth(),
                                              iconAi9.getIconHeight()));
            boardAi10.setBounds(0, 0, iconAi10.getIconWidth(),
                                        iconAi10.getIconHeight()); 
            this.am10.setPreferredSize(new Dimension(iconAi10.getIconWidth(),
                                              iconAi10.getIconHeight()));													 													 
            boardAi11.setBounds(0, 0, iconAi11.getIconWidth(),
                                        iconAi11.getIconHeight()); 
            this.am11.setPreferredSize(new Dimension(iconAi11.getIconWidth(),
                                              iconAi11.getIconHeight()));
            boardAi12.setBounds(0, 0, iconAi12.getIconWidth(),
                                        iconAi12.getIconHeight()); 
            this.am12.setPreferredSize(new Dimension(iconAi12.getIconWidth(),
                                              iconAi12.getIconHeight()));
            boardAi13.setBounds(0, 0, iconAi13.getIconWidth(),
                                        iconAi13.getIconHeight()); 
            this.am13.setPreferredSize(new Dimension(iconAi13.getIconWidth(),
                                              iconAi13.getIconHeight()));
            boardAi14.setBounds(0, 0, iconAi14.getIconWidth(),
                                        iconAi14.getIconHeight()); 
            this.am14.setPreferredSize(new Dimension(iconAi14.getIconWidth(),
                                              iconAi14.getIconHeight()));
            boardAi15.setBounds(0, 0, iconAi15.getIconWidth(),
                                        iconAi15.getIconHeight()); 
            this.am15.setPreferredSize(new Dimension(iconAi15.getIconWidth(),
                                              iconAi15.getIconHeight()));	
            boardAi16.setBounds(0, 0, iconAi16.getIconWidth(),
                                        iconAi16.getIconHeight()); 
            this.am16.setPreferredSize(new Dimension(iconAi16.getIconWidth(),
                                           iconAi16.getIconHeight()));													 												 													 
         
            this.mypanel4.add(this.am1);
            this.mypanel4.add(this.am2);
            this.mypanel4.add(this.am3);
            this.mypanel4.add(this.am4);
            this.mypanel4.add(this.am5);
            this.mypanel4.add(this.am6);
            this.mypanel4.add(this.am7);
            this.mypanel4.add(this.am8);
            this.mypanel4.add(this.am9);
            this.mypanel4.add(this.am10);
            this.mypanel4.add(this.am11);
            this.mypanel4.add(this.am12);
            this.mypanel4.add(this.am13);
            this.mypanel4.add(this.am14);
            this.mypanel4.add(this.am15);
            this.mypanel4.add(this.am16);
            this.lb.populateTile("America", startPosition, 1, p);
         }
      
         if(nation.equals("China")){
         //pretty up the board panels
            this.ch1 = new GameJLayeredPane();
            this.ch2 = new GameJLayeredPane();
            this.ch3 = new GameJLayeredPane();
            this.ch4 = new GameJLayeredPane();
            this.ch5 = new GameJLayeredPane();
            this.ch6 = new GameJLayeredPane();
            this.ch7 = new GameJLayeredPane();
            this.ch8 = new GameJLayeredPane();
            this.ch9 = new GameJLayeredPane();
            this.ch10 = new GameJLayeredPane();
            this.ch11 = new GameJLayeredPane();
            this.ch12 = new GameJLayeredPane();
            this.ch13 = new GameJLayeredPane();
            this.ch14 = new GameJLayeredPane();
            this.ch15 = new GameJLayeredPane();
            this.ch16 = new GameJLayeredPane();
         
            GameImageIcon iconPlayer1 = getImage("data/ch1.png");
            GameImageIcon iconPlayer2 = getImage("data/ch2.png");
            GameImageIcon iconPlayer3 = getImage("data/ch3.png");
            GameImageIcon iconPlayer4 = getImage("data/ch4.png");
            GameImageIcon iconPlayer5 = getImage("data/ch5.png");
            GameImageIcon iconPlayer6 = getImage("data/ch6.png");
            GameImageIcon iconPlayer7 = getImage("data/ch7.png");
            GameImageIcon iconPlayer8 = getImage("data/ch8.png");
            GameImageIcon iconPlayer9 = getImage("data/ch9.png");
            GameImageIcon iconPlayer10 = getImage("data/ch10.png");
            GameImageIcon iconPlayer11 = getImage("data/ch11.png");
            GameImageIcon iconPlayer12 = getImage("data/ch12.png");
            GameImageIcon iconPlayer13 = getImage("data/ch13.png");
            GameImageIcon iconPlayer14 = getImage("data/ch14.png");
            GameImageIcon iconPlayer15 = getImage("data/ch15.png");
            GameImageIcon iconPlayer16 = getImage("data/ch16.png");
         
         //add components to board
            GameJLabel boardPlayer1 = new GameJLabel(iconPlayer1);
            GameJLabel boardPlayer2 = new GameJLabel(iconPlayer2);
            GameJLabel boardPlayer3 = new GameJLabel(iconPlayer3);
            GameJLabel boardPlayer4 = new GameJLabel(iconPlayer4);
            GameJLabel boardPlayer5 = new GameJLabel(iconPlayer5);
            GameJLabel boardPlayer6 = new GameJLabel(iconPlayer6);
            GameJLabel boardPlayer7 = new GameJLabel(iconPlayer7);
            GameJLabel boardPlayer8 = new GameJLabel(iconPlayer8);
            GameJLabel boardPlayer9 = new GameJLabel(iconPlayer9);
            GameJLabel boardPlayer10 = new GameJLabel(iconPlayer10);
            GameJLabel boardPlayer11 = new GameJLabel(iconPlayer11);
            GameJLabel boardPlayer12 = new GameJLabel(iconPlayer12);
            GameJLabel boardPlayer13 = new GameJLabel(iconPlayer13);
            GameJLabel boardPlayer14 = new GameJLabel(iconPlayer14);
            GameJLabel boardPlayer15 = new GameJLabel(iconPlayer15);
            GameJLabel boardPlayer16 = new GameJLabel(iconPlayer16);
         
            this.ch1.add(boardPlayer1, new Integer(0));
            this.ch2.add(boardPlayer2, new Integer(0));
            this.ch3.add(boardPlayer3, new Integer(0));
            this.ch4.add(boardPlayer4, new Integer(0));
            this.ch5.add(boardPlayer5, new Integer(0));
            this.ch6.add(boardPlayer6, new Integer(0));
            this.ch7.add(boardPlayer7, new Integer(0));
            this.ch8.add(boardPlayer8, new Integer(0));
            this.ch9.add(boardPlayer9, new Integer(0));
            this.ch10.add(boardPlayer10, new Integer(0));
            this.ch11.add(boardPlayer11, new Integer(0));
            this.ch12.add(boardPlayer12, new Integer(0));
            this.ch13.add(boardPlayer13, new Integer(0));
            this.ch14.add(boardPlayer14, new Integer(0));
            this.ch15.add(boardPlayer15, new Integer(0));
            this.ch16.add(boardPlayer16, new Integer(0));
         
            boardPlayer1.setBounds(0, 0, iconPlayer1.getIconWidth(),
                                        iconPlayer1.getIconHeight()); 
            this.ch1.setPreferredSize(new Dimension(iconPlayer1.getIconWidth(),
                                              iconPlayer1.getIconHeight()));
            boardPlayer2.setBounds(0, 0, iconPlayer2.getIconWidth(),
                                        iconPlayer2.getIconHeight()); 
            this.ch2.setPreferredSize(new Dimension(iconPlayer2.getIconWidth(),
                                              iconPlayer2.getIconHeight()));
            boardPlayer3.setBounds(0, 0, iconPlayer3.getIconWidth(),
                                        iconPlayer3.getIconHeight()); 
            this.ch3.setPreferredSize(new Dimension(iconPlayer3.getIconWidth(),
                                              iconPlayer3.getIconHeight()));
            boardPlayer4.setBounds(0, 0, iconPlayer4.getIconWidth(),
                                        iconPlayer4.getIconHeight()); 
            this.ch4.setPreferredSize(new Dimension(iconPlayer4.getIconWidth(),
                                              iconPlayer4.getIconHeight()));
            boardPlayer5.setBounds(0, 0, iconPlayer5.getIconWidth(),
                                        iconPlayer5.getIconHeight()); 
            this.ch5.setPreferredSize(new Dimension(iconPlayer5.getIconWidth(),
                                              iconPlayer5.getIconHeight()));
            boardPlayer6.setBounds(0, 0, iconPlayer6.getIconWidth(),
                                        iconPlayer6.getIconHeight()); 
            this.ch6.setPreferredSize(new Dimension(iconPlayer6.getIconWidth(),
                                              iconPlayer6.getIconHeight()));
            boardPlayer7.setBounds(0, 0, iconPlayer7.getIconWidth(),
                                        iconPlayer7.getIconHeight()); 
            this.ch7.setPreferredSize(new Dimension(iconPlayer7.getIconWidth(),
                                              iconPlayer7.getIconHeight()));
            boardPlayer8.setBounds(0, 0, iconPlayer8.getIconWidth(),
                                        iconPlayer8.getIconHeight()); 
            this.ch8.setPreferredSize(new Dimension(iconPlayer8.getIconWidth(),
                                              iconPlayer8.getIconHeight()));
            boardPlayer9.setBounds(0, 0, iconPlayer9.getIconWidth(),
                                        iconPlayer9.getIconHeight()); 
            this.ch9.setPreferredSize(new Dimension(iconPlayer9.getIconWidth(),
                                              iconPlayer9.getIconHeight()));
            boardPlayer10.setBounds(0, 0, iconPlayer10.getIconWidth(),
                                        iconPlayer10.getIconHeight()); 
            this.ch10.setPreferredSize(new Dimension(iconPlayer10.getIconWidth(),
                                              iconPlayer10.getIconHeight()));													 													 
            boardPlayer11.setBounds(0, 0, iconPlayer11.getIconWidth(),
                                        iconPlayer11.getIconHeight()); 
            this.ch11.setPreferredSize(new Dimension(iconPlayer11.getIconWidth(),
                                              iconPlayer11.getIconHeight()));
            boardPlayer12.setBounds(0, 0, iconPlayer12.getIconWidth(),
                                        iconPlayer12.getIconHeight()); 
            this.ch12.setPreferredSize(new Dimension(iconPlayer12.getIconWidth(),
                                              iconPlayer12.getIconHeight()));
            boardPlayer13.setBounds(0, 0, iconPlayer13.getIconWidth(),
                                        iconPlayer13.getIconHeight()); 
            this.ch13.setPreferredSize(new Dimension(iconPlayer13.getIconWidth(),
                                              iconPlayer13.getIconHeight()));
            boardPlayer14.setBounds(0, 0, iconPlayer14.getIconWidth(),
                                        iconPlayer14.getIconHeight()); 
            this.ch14.setPreferredSize(new Dimension(iconPlayer14.getIconWidth(),
                                              iconPlayer14.getIconHeight()));
            boardPlayer15.setBounds(0, 0, iconPlayer15.getIconWidth(),
                                        iconPlayer15.getIconHeight()); 
            this.ch15.setPreferredSize(new Dimension(iconPlayer15.getIconWidth(),
                                              iconPlayer15.getIconHeight()));	
            boardPlayer16.setBounds(0, 0, iconPlayer16.getIconWidth(),
                                        iconPlayer16.getIconHeight()); 
            this.ch16.setPreferredSize(new Dimension(iconPlayer16.getIconWidth(),
                                              iconPlayer16.getIconHeight()));													 												 													 
         
            this.mypanel3.add(this.ch1);
            this.mypanel3.add(this.ch2);
            this.mypanel3.add(this.ch3);
            this.mypanel3.add(this.ch4);
            this.mypanel3.add(this.ch5);
            this.mypanel3.add(this.ch6);
            this.mypanel3.add(this.ch7);
            this.mypanel3.add(this.ch8);
            this.mypanel3.add(this.ch9);
            this.mypanel3.add(this.ch10);
            this.mypanel3.add(this.ch11);
            this.mypanel3.add(this.ch12);
            this.mypanel3.add(this.ch13);
            this.mypanel3.add(this.ch14);
            this.mypanel3.add(this.ch15);
            this.mypanel3.add(this.ch16);
            this.lb.populateTile("China", startPosition, 1, p);
         }//end china
      //      }
      }
     
      public void drawAIStart(Player p){
         p.drawAIstuff(players[1]);//number of players
      }
    
      public void drawPlayerStart(Player p){
         for(int k = 0; k < players.length; k++){
            if(players[k] != p){
               p.drawPlayerstuff(players[k]);//number of players
            }
         }
      }
   
      public void setRandomBoard()
      {
      //     c.dispose();
         Die dice = new Die(new Random().nextInt(666));

         boardPosition = new String[8];//for position on logicboard
      /*
      [0][4]
      [1][5]
      [2][6]
      [3][7]
      where [3] is player start and [4] is ai start for 2 players/(1 human, 1 ai)
      */
         boolean match = false;
      
         for(int k = 0; k < 3;)
         {
            String s = "";
            int j = dice.rollFourteen();
            s = "rand"+j;
            for(int i = 0; i < 8; i++)  
            {
               if(s.equals(boardPosition[i]))  
               {
                  match = true;      
               }
            }
            if(!match)
            {
               boardPosition[k] = s;
            //System.out.println(boardPosition[k]);		 
               k++;
            //         match = false;
            }
            match = false; 
         }
      
         boardPosition[3] = playerChoice;
         boardPosition[4] = aiChoice;
         match = false;
      
         for(int k = 5; k < 8;)
         {
            String s = "";
            int j = dice.rollFourteen();
            s = "rand"+j;
            for(int i = 0; i < 8; i++)  
            {
               if(s.equals(boardPosition[i]))  
               {
                  match = true;      
               }
            }
            if(!match)
            {
               boardPosition[k] = s;
            //System.out.println(boardPosition[k]);		 
               k++;
            //         match = false;
            }
            match = false; 
         }	    
         GridLayout grid = new GridLayout(4, 4, 1, 1);
         FlowLayout lay = new FlowLayout(3, 0, 0);
      
         panel0 = new GameJPanel(grid);
         panel0.setName("panel0");
         panel1 = new GameJPanel(grid);
         panel1.setName("panel1");
         panel2 = new GameJPanel(grid);
         panel2.setName("panel2");
         panel3 = new GameJPanel(grid);
         panel3.setName("panel3");
         panel4 = new GameJPanel(grid);
         panel4.setName("panel4");
         panel5 = new GameJPanel(grid);
         panel5.setName("panel5");
         panel6 = new GameJPanel(grid);
         panel6.setName("panel6");
         panel7 = new GameJPanel(grid);
         panel7.setName("panel7");
      
         panel0.setBackground(new Color(0, 0, 0));
         panel1.setBackground(new Color(0, 0, 0));
         panel2.setBackground(new Color(0, 0, 0));
         panel3.setBackground(new Color(0, 0, 0));
         panel4.setBackground(new Color(0, 0, 0));
         panel5.setBackground(new Color(0, 0, 0));
         panel6.setBackground(new Color(0, 0, 0));
         panel7.setBackground(new Color(0, 0, 0));
      }
   
      public void gameBoard(CivGUI cGUI)
      {
      
      //remove all components from civilization choice
         GridLayout grid = new GridLayout(4, 4, 1, 1);
         FlowLayout lay = new FlowLayout(3, 0, 0);
      
         mypanel0 = new GameJPanel(grid);
         mypanel0.setName("panel0");
         mypanel1 = new GameJPanel(grid);
         mypanel1.setName("panel1");
         mypanel2 = new GameJPanel(grid);
         mypanel2.setName("panel2");
         mypanel3 = new GameJPanel(grid);
         mypanel3.setName("panel3");
         mypanel4 = new GameJPanel(grid);
         mypanel4.setName("panel4");
         mypanel5 = new GameJPanel(grid);
         mypanel5.setName("panel5");
         mypanel6 = new GameJPanel(grid);
         mypanel6.setName("panel6");
         mypanel7 = new GameJPanel(grid);
         mypanel7.setName("panel7");
      
         mypanel0.setBackground(new Color(0, 0, 0));
         mypanel1.setBackground(new Color(0, 0, 0));
         mypanel2.setBackground(new Color(0, 0, 0));
         mypanel3.setBackground(new Color(0, 0, 0));
         mypanel4.setBackground(new Color(0, 0, 0));
         mypanel5.setBackground(new Color(0, 0, 0));
         mypanel6.setBackground(new Color(0, 0, 0));
         mypanel7.setBackground(new Color(0, 0, 0));
      
         mainBoard = new GameJLayeredPane();
         mainBoard.setOpaque(true);
      
         GameImageIcon mainBoardIcon = getImage("data/mainBoard1.png");
         mainBrd = new GameJLabel(mainBoardIcon);
         mainBrd2 = new GameJLayeredPane();
         GameImageIcon PlayerBoardIcon = getImage("data/card"+playerChoice+".png");
      
      //cultre track will be set for [#players][if not null then that is as far advanced]
      //x + 35 per advance on the track
         cultureTrack = new GameJLabel[2][22];
         cultureTrack[0][0] = new GameJLabel(getImage("data/CUL"+playerChoice+".png")); 
         cultureTrack[0][0].setBounds(10, 360, 20, 15);
         cultureTrack[1][0] = new GameJLabel(getImage("data/CUL"+aiChoice+".png")); 
         cultureTrack[1][0].setBounds(10, 375, 20, 15);
      
         mainBrd2.add(cultureTrack[0][0], new Integer(4));
         mainBrd2.add(cultureTrack[1][0], new Integer(4));
      
         setVectors();
      
         mainBrd.setBounds(0, 0, 800, 410);
         mainBrd2.add(mainBrd, new Integer(0));
      
         mainBrd2.setPreferredSize(new Dimension(800, 410));
         mainBrd2.setBackground(new Color(0,0,0));
      
         availableMarket = MAX_MARKET;
         availableBarrack = MAX_BARRACKS;
         availableGranary = MAX_GRANARY;
         availableHarbor = MAX_HARBOR;
         availableLibrary = MAX_LIBRARY; 
         availableTemple = MAX_TEMPLE;
         availableTradepost = MAX_TRADEPOST;
         availableWorkshop = MAX_WORKSHOP;
         availableAcademy = MAX_ACADEMY;
         availableAqueduct = MAX_AQUEDUCT;
         availableUniversity = MAX_UNIVERSITY;
         availableBank = MAX_BANK;
         availableCathedral = MAX_CATHEDRAL;
         availableIronmine = MAX_IRONMINE;
      
         showAvailableMarket = new JTextArea();
         showAvailableMarket.setEditable(false);
         showAvailableMarket.setBounds(178, 13, 13, 17);
         showAvailableMarket.setForeground(new Color(250, 250, 250));
         showAvailableMarket.setText(Integer.toString(MAX_MARKET));//Integer.toString(MAX_MARKET));
         showAvailableMarket.setBackground(new Color(0, 0, 0));
         showAvailableMarket.setBorder(new MatteBorder(2, 3, 2, 3, getImage("data/goldBorder.png")));
         mainBrd2.add(showAvailableMarket, new Integer(3));
      
         showAvailableBank = new JTextArea();
         showAvailableBank.setEditable(false);
         showAvailableBank.setBounds(233, 13, 13, 17);
         showAvailableBank.setForeground(new Color(250, 250, 250));
         showAvailableBank.setBackground(new Color(0, 0, 0));
         showAvailableBank.setBorder(new MatteBorder(2, 3, 2, 3, getImage("data/goldBorder.png")));
         showAvailableBank.setText(Integer.toString(MAX_BANK));//Integer.toString(MAX_MARKET));
         mainBrd2.add(showAvailableBank, new Integer(3));
      
         showAvailableTemple = new JTextArea();
         showAvailableTemple.setEditable(false);
         showAvailableTemple.setBounds(296, 13, 13, 17);
         showAvailableTemple.setForeground(new Color(250, 250, 250));
         showAvailableTemple.setBackground(new Color(0, 0, 0));
         showAvailableTemple.setBorder(new MatteBorder(2, 3, 2, 3, getImage("data/goldBorder.png")));
         showAvailableTemple.setText(Integer.toString(MAX_TEMPLE));//Integer.toString(MAX_MARKET));
         mainBrd2.add(showAvailableTemple, new Integer(3));
      
         showAvailableCathedral = new JTextArea();
         showAvailableCathedral.setEditable(false);
         showAvailableCathedral.setBounds(351, 13, 13, 17);
         showAvailableCathedral.setForeground(new Color(250, 250, 250));
         showAvailableCathedral.setBackground(new Color(0, 0, 0));
         showAvailableCathedral.setBorder(new MatteBorder(2, 3, 2, 3, getImage("data/goldBorder.png")));
         showAvailableCathedral.setText(Integer.toString(MAX_CATHEDRAL));//Integer.toString(MAX_MARKET));
         mainBrd2.add(showAvailableCathedral, new Integer(3));
      
         showAvailableGranary = new JTextArea();
         showAvailableGranary.setEditable(false);
         showAvailableGranary.setBounds(178, 93, 13, 17);
         showAvailableGranary.setForeground(new Color(250, 250, 250));
         showAvailableGranary.setBackground(new Color(0, 0, 0));
         showAvailableGranary.setBorder(new MatteBorder(2, 3, 2, 3, getImage("data/goldBorder.png")));
         showAvailableGranary.setText(Integer.toString(MAX_GRANARY));//Integer.toString(MAX_MARKET));
         mainBrd2.add(showAvailableGranary, new Integer(3));
      
         showAvailableAqueduct = new JTextArea();
         showAvailableAqueduct.setEditable(false);
         showAvailableAqueduct.setBounds(233, 93, 13, 17);
         showAvailableAqueduct.setForeground(new Color(250, 250, 250));
         showAvailableAqueduct.setBackground(new Color(0, 0, 0));
         showAvailableAqueduct.setBorder(new MatteBorder(2, 3, 2, 3, getImage("data/goldBorder.png")));
         showAvailableAqueduct.setText(Integer.toString(MAX_AQUEDUCT));//Integer.toString(MAX_MARKET));
         mainBrd2.add(showAvailableAqueduct, new Integer(3));
      
         showAvailableLibrary = new JTextArea();
         showAvailableLibrary.setEditable(false);
         showAvailableLibrary.setBounds(296, 93, 13, 17);
         showAvailableLibrary.setForeground(new Color(250, 250, 250));
         showAvailableLibrary.setBackground(new Color(0, 0, 0));
         showAvailableLibrary.setBorder(new MatteBorder(2, 3, 2, 3, getImage("data/goldBorder.png")));
         showAvailableLibrary.setText(Integer.toString(MAX_LIBRARY));//Integer.toString(MAX_MARKET));
         mainBrd2.add(showAvailableLibrary, new Integer(3));
      
         showAvailableUniversity = new JTextArea();
         showAvailableUniversity.setEditable(false);
         showAvailableUniversity.setBounds(351, 93, 13, 17);
         showAvailableUniversity.setForeground(new Color(250, 250, 250));
         showAvailableUniversity.setBackground(new Color(0, 0, 0));
         showAvailableUniversity.setBorder(new MatteBorder(2, 3, 2, 3, getImage("data/goldBorder.png")));
         showAvailableUniversity.setText(Integer.toString(MAX_UNIVERSITY));//Integer.toString(MAX_MARKET));
         mainBrd2.add(showAvailableUniversity, new Integer(3));
      
         showAvailableBarrack = new JTextArea();
         showAvailableBarrack.setEditable(false);
         showAvailableBarrack.setBounds(178, 175, 13, 17);
         showAvailableBarrack.setForeground(new Color(250, 250, 250));
         showAvailableBarrack.setBackground(new Color(0, 0, 0));
         showAvailableBarrack.setBorder(new MatteBorder(2, 3, 2, 3, getImage("data/goldBorder.png")));
         showAvailableBarrack.setText(Integer.toString(MAX_BARRACKS));//Integer.toString(MAX_MARKET));
         mainBrd2.add(showAvailableBarrack, new Integer(3));
      
         showAvailableAcademy = new JTextArea();
         showAvailableAcademy.setEditable(false);
         showAvailableAcademy.setBounds(233, 175, 13, 17);
         showAvailableAcademy.setForeground(new Color(250, 250, 250));
         showAvailableAcademy.setBackground(new Color(0, 0, 0));
         showAvailableAcademy.setBorder(new MatteBorder(2, 3, 2, 3, getImage("data/goldBorder.png")));
         showAvailableAcademy.setText(Integer.toString(MAX_ACADEMY));//Integer.toString(MAX_MARKET));
         mainBrd2.add(showAvailableAcademy, new Integer(3));
      
         showAvailableWorkshop = new JTextArea();
         showAvailableWorkshop.setEditable(false);
         showAvailableWorkshop.setBounds(296, 175, 13, 17);
         showAvailableWorkshop.setForeground(new Color(250, 250, 250));
         showAvailableWorkshop.setBackground(new Color(0, 0, 0));
         showAvailableWorkshop.setBorder(new MatteBorder(2, 3, 2, 3, getImage("data/goldBorder.png")));
         showAvailableWorkshop.setText(Integer.toString(MAX_WORKSHOP));//Integer.toString(MAX_MARKET));
         mainBrd2.add(showAvailableWorkshop, new Integer(3));
      
         showAvailableIronmine = new JTextArea();
         showAvailableIronmine.setEditable(false);
         showAvailableIronmine.setBounds(351, 175, 13, 17);
         showAvailableIronmine.setForeground(new Color(250, 250, 250));
         showAvailableIronmine.setBackground(new Color(0, 0, 0));
         showAvailableIronmine.setBorder(new MatteBorder(2, 3, 2, 3, getImage("data/goldBorder.png")));
         showAvailableIronmine.setText(Integer.toString(MAX_IRONMINE));//Integer.toString(MAX_MARKET));
         mainBrd2.add(showAvailableIronmine, new Integer(3));
      
         showAvailableTradepost = new JTextArea();
         showAvailableTradepost.setEditable(false);
         showAvailableTradepost.setBounds(205, 256, 13, 17);
         showAvailableTradepost.setForeground(new Color(250, 250, 250));
         showAvailableTradepost.setBackground(new Color(0, 0, 0));
         showAvailableTradepost.setBorder(new MatteBorder(2, 3, 2, 3, getImage("data/goldBorder.png")));
         showAvailableTradepost.setText(Integer.toString(MAX_TRADEPOST));//Integer.toString(MAX_MARKET));
         mainBrd2.add(showAvailableTradepost, new Integer(3));
      
         showAvailableHarbor = new JTextArea();
         showAvailableHarbor.setEditable(false);
         showAvailableHarbor.setBounds(322, 256, 20, 17);
         showAvailableHarbor.setForeground(new Color(250, 250, 250));
         showAvailableHarbor.setBackground(new Color(0, 0, 0));
         showAvailableHarbor.setBorder(new MatteBorder(2, 3, 2, 3, getImage("data/goldBorder.png")));
         showAvailableHarbor.setText(Integer.toString(MAX_HARBOR));//Integer.toString(MAX_MARKET));
         mainBrd2.add(showAvailableHarbor, new Integer(3));
      
         combatDeck = new Deck(mainBrd2);      
         mainBrd2.setBounds(20, 00, 800, 410);
      
         mainBoard.add(mainBrd2, new Integer(0));  
      
         Toolkit toolkit =  Toolkit.getDefaultToolkit ();
         Dimension dim = toolkit.getScreenSize();  
      
         mainBoard.setBackground(new Color(0,0,0));
         mainBoard.setPreferredSize(new Dimension(828, 880));
      
         GridLayout grid_1 = new GridLayout(4, 2, 1, 1);
         map = new GameJPanel();  
         map.setLayout(grid_1);
      
         map.setPreferredSize(new Dimension(442, 880));
         map.setBackground(new Color(0,0,0));
      
         GameImageIcon fogIcon = getImage("data/unexploredMap.png");
         myfog1 = new GameJLabel(fogIcon);
         myfog2 = new GameJLabel(fogIcon);
         myfog3 = new GameJLabel(fogIcon);
         myfog4 = new GameJLabel(fogIcon);
         myfog5 = new GameJLabel(fogIcon);
         myfog6 = new GameJLabel(fogIcon);
      
         map.add(myfog1);
         map.add(mypanel4);
         map.add(myfog2);
         map.add(myfog3);
         map.add(myfog4);
         map.add(myfog5);
         map.add(mypanel3);
         map.add(myfog6);
      
      //max = numplayers for resources
         maxIncense = 2;
         maxIron = 2;
         maxSilk = 2;
         maxWheat = 2;
      
         numIncense = maxIncense;
         numIron = maxIron;
         numSilk = maxSilk;
         numWheat = maxWheat;
       
         GameImageIcon marketincenseIcon = getImage("data/Incense.png");
         GameJLabel marketincenseLabel = new GameJLabel(marketincenseIcon);
         marketincenseLabel.setBounds(10, 20, 25, 25);
         marketIncense = new JTextArea();
         marketIncense.setEditable(false);
         marketIncense.setText(Integer.toString(maxIncense));
         marketIncense.setBackground(new Color(220, 202, 30));
         marketIncense.setBounds(40, 20, 10, 25);
      
         GameImageIcon marketironIcon = getImage("data/Iron.png");
         GameJLabel marketironLabel = new GameJLabel(marketironIcon);
         marketironLabel.setBounds(60, 20, 25, 25);
         marketIron = new JTextArea();
         marketIron.setEditable(false);
         marketIron.setText(Integer.toString(maxIron));
         marketIron.setBackground(new Color(220, 202, 30));
         marketIron.setBounds(90, 20, 10, 25);
      
         GameImageIcon marketsilkIcon = getImage("data/Silk.png");
         GameJLabel marketsilkLabel = new GameJLabel(marketsilkIcon);
         marketsilkLabel.setBounds(110, 20, 25, 25);
         marketSilk = new JTextArea();
         marketSilk.setEditable(false);
         marketSilk.setText(Integer.toString(maxSilk));
         marketSilk.setBackground(new Color(220, 202, 30));
         marketSilk.setBounds(140, 20, 10, 25);
      
         GameImageIcon marketwheatIcon = getImage("data/Wheat.png");
         GameJLabel marketwheatLabel = new GameJLabel(marketwheatIcon);
         marketwheatLabel.setBounds(160, 20, 25, 25);
         marketWheat = new JTextArea();
         marketWheat.setEditable(false);
         marketWheat.setText(Integer.toString(maxWheat));
         marketWheat.setBackground(new Color(220, 202, 30));
         marketWheat.setBounds(190, 20, 10, 25);
      
         market = new GameJLayeredPane();
         market.setBounds(20, 630, 205, 58);  
         market.setBackground(new Color(220, 202, 30));
         market.setBorder(new MatteBorder(5, 5, 5, 5, getImage("data/goldBorder.png")));
         market.setOpaque(true);
      
         GameJLabel mLabel = new GameJLabel("Market");
         mLabel.setBounds(80, 04, 70, 15);
      
         market.add(mLabel);
         market.add(marketincenseLabel);
         market.add(marketIncense);
         market.add(marketironLabel);
         market.add(marketIron);
         market.add(marketsilkLabel);
         market.add(marketSilk);
         market.add(marketwheatLabel);
         market.add(marketWheat);
         mainBoard.add(market, new Integer (0));
      
         c = new GameJFrame();
System.out.println("pass 7");
         c.setResizable(false);
         c.setBounds(0,0,1200,1000);
         c.setVisible(true);
         c.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
      
         c.setBackground(new Color(0,0,0));
         c.add(map, "West");
         c.add(mainBoard, "East");
      
         wonderCards = new GameJLabel[4];
         wonderBuildings = new GameJLabel[4];
         wonderDeck = new Deck(mainBrd2, "wonder");
      
         drawCultureCards();
      
         System.out.println(dim);
      
         c.setBounds(0, 0, (int)dim.getWidth(), (int)dim.getHeight()); 
      
         c.pack();
         c.validate();  
System.out.println("pass 8");
      }  
   
      public void drawCultureCards()
      {
         culture1Deck = new Deck(mainBrd2, "culture1");
         culture2Deck = new Deck(mainBrd2, "culture2");
         culture3Deck = new Deck(mainBrd2, "culture3");
      }
   
      public void drawCombatDecks()
      {
         for(int i = 0; i < 2; i++)//number of players
         {
            players[i].playerUI.myCombatDeck = new Deck("combat", combatDeck, players[i]);      
         }  
      }
   
      public void drawWonders()
      {
         for(int i = 0; i < 2; i++)//number of players
         {
            players[i].playerUI.myWonderDeck = new Deck("wonder", wonderDeck, players[i]);      
         }  
      
         for(int k = 0; k < 4; k++)
         {
            wonderDeck.removeTopWonder();
            Card tempPrime = new Card();
           
            for(int j = 0; j < 2; j++)//number of players  
            {
               tempPrime = players[j].playerUI.myWonderDeck.getWonder(0);
               Card temp = new Card("wonder", tempPrime.getValue());
            //System.out.println(tempPrime.getType() + tempPrime.getValue());
               GameJLabel wonderCard = new GameJLabel(new GameRotatedIcon(temp.getFrontIcon(), 180));
               wonderCard.setBounds(10, 76+(k*67), 90, 55);
            
               GameImageIcon ic = temp.getCombatBackIcon().getIcon();
               Image img = ic.getImage();
               BufferedImage bi = new BufferedImage(40, 40, BufferedImage.TYPE_INT_ARGB);
               bi.getGraphics().drawImage(img, 0, 0, 40, 40, null);
               GameImageIcon icon = new GameImageIcon(bi);
            
               GameRotatedIcon r = new GameRotatedIcon(icon, 90);
               GameJLabel wonderBuilding = new GameJLabel(r);
               wonderBuilding.setBounds(106, 84+(k*67), 40, 40);
            
               players[j].playerUI.mainBrd2.add(wonderBuilding, new Integer(1));
               players[j].playerUI.mainBrd2.add(wonderCard, new Integer(1));
               players[j].playerUI.mainBrd2.repaint();
            
               players[j].playerUI.wonderBuildings[k] = wonderBuilding;
               players[j].playerUI.wonderCards[k] = wonderCard;  
            
               temp.setLabel(wonderCard);
            
               players[j].addAvailableWonder(temp, k);
            }
         }
      }
   
      public void replaceWonder(int w)
      {
         Card tempPrime = wonderDeck.getWonder(0);
      
         for(int k = 0; k < 2; k++)//number of players
         {
            Card temp = new Card("wonder", tempPrime.getValue());
            tempPrime = players[k].playerUI.myWonderDeck.getWonder(0);
         
            if(players[k].playerUI.mainBrd2.getIndexOf(players[k].playerUI.wonderCards[w]) >= 0)
               players[k].playerUI.mainBrd2.remove(players[k].playerUI.mainBrd2.getIndexOf(players[k].playerUI.wonderCards[w]));
            if(players[k].playerUI.mainBrd2.getIndexOf(players[k].playerUI.wonderBuildings[w]) >= 0)
               players[k].playerUI.mainBrd2.remove(players[k].playerUI.mainBrd2.getIndexOf(players[k].playerUI.wonderBuildings[w]));
         
            GameJLabel wonderCard = new GameJLabel(new GameRotatedIcon(temp.getFrontIcon(), 180));
            wonderCard.setBounds(10, 76+(w*67), 90, 55);
         
            GameImageIcon ic = temp.getCombatBackIcon().getIcon();
            Image img = ic.getImage(); 
            BufferedImage bi = new BufferedImage(40, 40, BufferedImage.TYPE_INT_ARGB);
            bi.getGraphics().drawImage(img, 0, 0, 40, 40, null);
            GameImageIcon icon = new GameImageIcon(bi);
         
            GameRotatedIcon r = new GameRotatedIcon(icon, 90);
            GameJLabel wonderBuilding = new GameJLabel(r);
            wonderBuilding.setBounds(106, 84+(w*67), 40, 40);
         
            players[k].playerUI.mainBrd2.add(wonderBuilding, new Integer(1));
            players[k].playerUI.mainBrd2.add(wonderCard, new Integer(1));
              
            temp.setLabel(wonderCard);
            players[k].playerUI.wonderCards[w] = wonderCard;
            players[k].playerUI.wonderBuildings[w] = wonderBuilding;
         
            players[k].addAvailableWonder(temp, w);
         }
      
      //	  for(int i = 0; i < 2; i++)//number of players
      //	  {
      //		 players[i].addAvailableWonder(temp, w);
      //	  }  
      }  
   
      public void addBuilding(int type)
      {
         switch(type)    
         {
            case 0://barracks
               for(int k = 0; k < 2; k++)//number of players
               {
                  players[k].playerUI.availableBarrack++;
                  players[k].playerUI.showAvailableBarrack.setText(Integer.toString(players[k].playerUI.availableBarrack)+ "  ");
                  players[k].playerUI.availableAcademy++;
                  players[k].playerUI.showAvailableAcademy.setText(Integer.toString(players[k].playerUI.availableAcademy)+ "  ");
               }    
               break;
            case 1://granary
               for(int k = 0; k < 2; k++)//number of players
               {
                  players[k].playerUI.availableGranary++;
                  players[k].playerUI.showAvailableGranary.setText(Integer.toString(players[k].playerUI.availableGranary)+ "  ");
                  players[k].playerUI.availableAqueduct++;
                  players[k].playerUI.showAvailableAqueduct.setText(Integer.toString(players[k].playerUI.availableAqueduct)+ "  ");
               }
               break;  
            case 2://harbor
               for(int k = 0; k < 2; k++)//number of players
               {
                  players[k].playerUI.availableHarbor++;
                  players[k].playerUI.showAvailableHarbor.setText(Integer.toString(players[k].playerUI.availableHarbor)+ "     ");
               }
               break;  
            case 3://library
               for(int k = 0; k < 2; k++)//number of players
               {
                  players[k].playerUI.availableLibrary++;
                  players[k].playerUI.showAvailableLibrary.setText(Integer.toString(players[k].playerUI.availableLibrary)+ "  ");
                  players[k].playerUI.availableUniversity++;
                  players[k].playerUI.showAvailableUniversity.setText(Integer.toString(players[k].playerUI.availableUniversity)+ "  ");
               }
               break;  
            case 4://market
               for(int k = 0; k < 2; k++)//number of players
               {
                  players[k].playerUI.availableMarket++;
                  players[k].playerUI.showAvailableMarket.setText(Integer.toString(players[k].playerUI.availableMarket)+ "  ");
                  players[k].playerUI.availableBank++;
                  players[k].playerUI.showAvailableBank.setText(Integer.toString(players[k].playerUI.availableBank)+ "  ");
               }
               break;  
            case 5://temple
               for(int k = 0; k < 2; k++)//number of players
               {
                  players[k].playerUI.availableTemple++;
                  players[k].playerUI.showAvailableTemple.setText(Integer.toString(players[k].playerUI.availableTemple)+ "  ");
                  players[k].playerUI.availableCathedral++;
                  players[k].playerUI.showAvailableCathedral.setText(Integer.toString(players[k].playerUI.availableCathedral)+ "  ");
               }
               break;  
            case 6://trade
               for(int k = 0; k < 2; k++)//number of players
               {
                  players[k].playerUI.availableTradepost++;
                  players[k].playerUI.showAvailableTradepost.setText(Integer.toString(players[k].playerUI.availableTradepost)+ "  ");
               }
               break;  
            case 7://workshop
               for(int k = 0; k < 2; k++)//number of players
               {
                  players[k].playerUI.availableWorkshop++;
                  players[k].playerUI.showAvailableWorkshop.setText(Integer.toString(players[k].playerUI.availableWorkshop)+ "  ");
                  players[k].playerUI.availableIronmine++;
                  players[k].playerUI.showAvailableIronmine.setText(Integer.toString(players[k].playerUI.availableIronmine)+ "  ");
               }
               break;  
         }
      }
   
      public void removeBuilding(String type)
      {
      
         if(type.equals("tradePost"))
         {
            for(int k = 0; k < 2; k++)//number of players
            {
               players[k].playerUI.availableTradepost--;
               players[k].playerUI.showAvailableTradepost.setText(Integer.toString(players[k].playerUI.availableTradepost)+ "  ");
            }
         }
         else if(type.equals("market"))
         {
            for(int k = 0; k < 2; k++)//number of players
            {
               players[k].playerUI.availableMarket--;
               players[k].playerUI.showAvailableMarket.setText(Integer.toString(players[k].playerUI.availableMarket)+ "  ");
            }
         }
         else if(type.equals("barracks"))
         {
            for(int k = 0; k < 2; k++)//number of players
            {
               players[k].playerUI.availableBarrack--;
               players[k].playerUI.showAvailableBarrack.setText(Integer.toString(players[k].playerUI.availableBarrack)+ "  ");
            }    
         }
         else if(type.equals("harbor"))
         { 	
            for(int k = 0; k < 2; k++)//number of players
            {
               players[k].playerUI.availableHarbor--;
               players[k].playerUI.showAvailableHarbor.setText(Integer.toString(players[k].playerUI.availableHarbor)+ "     ");
            }
         }
         else if(type.equals("temple"))
         {
            for(int k = 0; k < 2; k++)//number of players
            {
               players[k].playerUI.availableTemple--;
               players[k].playerUI.showAvailableTemple.setText(Integer.toString(players[k].playerUI.availableTemple)+ "  ");
            }
         }
         else if(type.equals("granary"))
         {
            for(int k = 0; k < 2; k++)//number of players
            {
               players[k].playerUI.availableGranary--;
               players[k].playerUI.showAvailableGranary.setText(Integer.toString(players[k].playerUI.availableGranary)+ "  ");
            }
         }
         else if(type.equals("library"))
         {
            for(int k = 0; k < 2; k++)//number of players
            {
               players[k].playerUI.availableLibrary--;
               players[k].playerUI.showAvailableLibrary.setText(Integer.toString(players[k].playerUI.availableLibrary)+ "  ");
            }
         } 
         else if(type.equals("university"))
         {
            for(int k = 0; k < 2; k++)//number of players
            {
               players[k].playerUI.availableUniversity--;
               players[k].playerUI.showAvailableUniversity.setText(Integer.toString(players[k].playerUI.availableUniversity)+ "  ");
            }
         }
         else if(type.equals("workshop"))
         {
            for(int k = 0; k < 2; k++)//number of players
            {
               players[k].playerUI.availableWorkshop--;
               players[k].playerUI.showAvailableWorkshop.setText(Integer.toString(players[k].playerUI.availableWorkshop)+ "  ");
            }
         }
         else if(type.equals("aqueduct"))
         {
            for(int k = 0; k < 2; k++)//number of players
            {
               players[k].playerUI.availableAqueduct--;
               players[k].playerUI.showAvailableAqueduct.setText(Integer.toString(players[k].playerUI.availableAqueduct)+ "  ");
            }
         }
         else if(type.equals("bank"))
         {
            for(int k = 0; k < 2; k++)//number of players
            {
               players[k].playerUI.availableBank--;
               players[k].playerUI.showAvailableBank.setText(Integer.toString(players[k].playerUI.availableBank)+ "  ");
            }
         }    
         else if(type.equals("academy"))
         {
            for(int k = 0; k < 2; k++)//number of players
            {
               players[k].playerUI.availableAcademy--;
               players[k].playerUI.showAvailableAcademy.setText(Integer.toString(players[k].playerUI.availableAcademy)+ "  ");
            }
         }    
         else if(type.equals("ironmine"))
         {
            for(int k = 0; k < 2; k++)//number of players
            {
               players[k].playerUI.availableIronmine--;
               players[k].playerUI.showAvailableIronmine.setText(Integer.toString(players[k].playerUI.availableIronmine)+ "  ");
            }
         }
         else if(type.equals("cathedral"))
         {
            for(int k = 0; k < 2; k++)//number of players
            {
               players[k].playerUI.availableCathedral--;
               players[k].playerUI.showAvailableCathedral.setText(Integer.toString(players[k].playerUI.availableCathedral)+ "  ");
            }
         }
      }
   
      public void removeMarketResource(String type)
      {
         if(type.equals("Iron"))
         {
            numIron--;	  
            for(int k = 0; k < 2; k++)//number of players 
            {
               players[k].playerUI.marketIron.setText(Integer.toString(numIron));
               players[k].playerUI.marketIron.repaint(); 
            }
         }
         if(type.equals("Incense"))
         {
            numIncense--;
            for(int k = 0; k < 2; k++)//number of players 
            {
               players[k].playerUI.marketIncense.setText(Integer.toString(numIncense));
               players[k].playerUI.marketIncense.repaint(); 
            }
         }
         if(type.equals("Silk"))
         {
            numSilk--;
            for(int k = 0; k < 2; k++)//number of players 
            {
               players[k].playerUI.marketSilk.setText(Integer.toString(numSilk));
               players[k].playerUI.marketSilk.repaint(); 
            }
         }
         if(type.equals("Wheat"))
         {
            numWheat--;
            for(int k = 0; k < 2; k++)//number of players 
            {
               players[k].playerUI.marketWheat.setText(Integer.toString(numWheat));
               players[k].playerUI.marketWheat.repaint(); 
            }
         }
      }
   
      public void addMarketResource(String type)
      {
         if(type.equals("Iron"))
         {
            numIron++;	  
            for(int k = 0; k < 2; k++)//number of players 
            {
               players[k].playerUI.marketIron.setText(Integer.toString(numIron));
               players[k].playerUI.marketIron.repaint(); 
            }
         }
         if(type.equals("Incense"))
         {
            numIncense++;
            for(int k = 0; k < 2; k++)//number of players 
            {
               players[k].playerUI.marketIncense.setText(Integer.toString(numIncense));
               players[k].playerUI.marketIncense.repaint(); 
            }
         }
         if(type.equals("Silk"))
         {
            numSilk++;
            for(int k = 0; k < 2; k++)//number of players 
            {
               players[k].playerUI.marketSilk.setText(Integer.toString(numSilk));
               players[k].playerUI.marketSilk.repaint(); 
            }
         }
         if(type.equals("Wheat"))
         {
            numWheat++;
            for(int k = 0; k < 2; k++)//number of players 
            {
               players[k].playerUI.marketWheat.setText(Integer.toString(numWheat));
               players[k].playerUI.marketWheat.repaint(); 
            }
         }
      }
   
      public void setVectors()
      {
         villages.add("Uranium");
         villages.add("Uranium");
         villages.add("Spy");
         villages.add("Spy");
         villages.add("Spy");
         villages.add("Iron");
         villages.add("Iron");
         villages.add("Iron");
         villages.add("gpVillage");
         villages.add("gpVillage");
         huts.add("Wheat");
         huts.add("Wheat");
         huts.add("Wheat");
         huts.add("Wheat");
         huts.add("Wheat");
         huts.add("Incense");
         huts.add("Incense");
         huts.add("Incense");
         huts.add("Incense");
         huts.add("Incense");
         huts.add("Silk");
         huts.add("Silk");
         huts.add("Silk");
         huts.add("Silk");
         huts.add("Silk");
         huts.add("Spy");
         huts.add("Spy");
         huts.add("Spy");
         huts.add("Iron");
         huts.add("Iron");
         greatPeople.add("artist");
         greatPeople.add("artist");
         greatPeople.add("artist");
         greatPeople.add("builder");
         greatPeople.add("builder");
         greatPeople.add("builder");
         greatPeople.add("general");
         greatPeople.add("general");
         greatPeople.add("general");
         greatPeople.add("humanitarian");
         greatPeople.add("humanitarian");
         greatPeople.add("humanitarian");
         greatPeople.add("industrialist");
         greatPeople.add("industrialist");
         greatPeople.add("industrialist");
         greatPeople.add("scientist");
         greatPeople.add("scientist");
         greatPeople.add("scientist");
      //System.out.println(villages.size());
      //System.out.println(huts.size());
      //System.out.println(greatPeople.size());
      }
   
      public static void drawRandom(String type, GameJLayeredPane mapSquare)
      {
         GameImageIcon im;
         GameJLabel l = new GameJLabel();
         if(type.equals("village"))
         {
            im = getStaticImage("data/village.png");	  
            l.setIcon(im); 
         }
         else if(type.equals("hut")) 
         {
            im = getStaticImage("data/hut.png");	  
            l.setIcon(im); 
         }
         l.setBounds(15, 15, 25, 25);
         mapSquare.add(l, new Integer(6));
         mapSquare.validate();
         mapSquare.repaint();
      //System.out.println("INDEX OF HUT "+mapSquare.getIndexOf(l)); 
      }
   
      public void tellHutResource(String type)
      {
         JOptionPane resource = new JOptionPane();
         GameImageIcon im = getStaticImage("data/"+type+".png");
         resource.showMessageDialog(c, "You Have Found "+type, "Hut passified",
            					    JOptionPane.INFORMATION_MESSAGE, im);	 	 
      //    if(type.equals("Iron"))
      //      showIron.setText(Integer.toString(player_1.getIron()));
      //    else if(type.equals("Incense"))
      //      showIncense.setText(Integer.toString(player_1.getIncense()));
      //    else if(type.equals("Silk"))
      //      showSilk.setText(Integer.toString(player_1.getSilk()));
      //    else if(type.equals("Wheat"))
      //      showWheat.setText(Integer.toString(player_1.getWheat()));
      //   else if(type.equals("Spy"))
      //      showSpy.setText(Integer.toString(player_1.getSpy()));
      //    else if(type.equals("Uranium"))
      //      showUranium.setText(Integer.toString(player_1.getUranium()));
      }   
   
      public void tellVillageResource(String type)
      {
         JOptionPane resource = new JOptionPane();
         GameImageIcon im = getStaticImage("data/"+type+".png");
         resource.showMessageDialog(c, "You Have Plundered "+type, "Village conquered",
            					   JOptionPane.INFORMATION_MESSAGE, im);	 	 
      //     if(type.equals("Iron"))
      //       showIron.setText(Integer.toString(player_1.getIron()));
      //     else if(type.equals("Incense"))
      //       showIncense.setText(Integer.toString(player_1.getIncense()));
      //     else if(type.equals("Silk"))
      //       showSilk.setText(Integer.toString(player_1.getSilk()));
      //     else if(type.equals("Wheat"))
      //       showWheat.setText(Integer.toString(player_1.getWheat()));
      //     else if(type.equals("Spy"))
      //       showSpy.setText(Integer.toString(player_1.getSpy()));
      //     else if(type.equals("Uranium"))
      //       showUranium.setText(Integer.toString(player_1.getUranium()));
      //     else if(type.equals("Uranium"))
      //     showUranium.setText(Integer.toString(player_1.getUranium()));
      }   
   
      public String getRandomVillage()
      {
         Die dice = new Die(new Random().nextInt(666));
         Object temp = villages.remove(dice.rollX(villages.size()));
         return (String)temp; 
      }
   
      public String getRandomHut()
      {
         Die dice = new Die(new Random().nextInt(666));
         Object temp = huts.remove(dice.rollX(huts.size()));
         return (String)temp; 
      }
   
      public String getRandomPerson()
      {
         Die dice = new Die(new Random().nextInt(666));
         Object temp = greatPeople.remove(dice.rollX(greatPeople.size()));
         return (String)temp; 
      }
   
      public Card getInfCard(Player p)
      {
         Card if_village = combatDeck.getInfantry(p.getInfLevel());
         Card temp=null, dummy=null;
         if(!p.getNation().equals("Village")){
            temp = (Card)p.playerUI.myCombatDeck.getInfantry(p.getInfLevel());
         }
         for(int k = 0; k < 2; k++)//number of players
         {
            if(players[k] != p)  
               dummy = players[k].playerUI.myCombatDeck.getInfantry(p.getInfLevel());
         }
         if(temp != null)
            return temp;
         else if(if_village != null)
            return if_village;
         return null;
      }
   
      public Card getMntCard(Player p)
      {
         Card if_village = combatDeck.getMounted(p.getMntLevel());
         Card temp=null, dummy=null;
         if(!p.getNation().equals("Village")){
            temp = (Card)p.playerUI.myCombatDeck.getMounted(p.getMntLevel());
         }
         for(int k = 0; k < 2; k++)//number of players
         {
            if(players[k] != p)  
               dummy = players[k].playerUI.myCombatDeck.getMounted(p.getMntLevel());
         }
         if(temp != null)
            return temp;
         else if(if_village != null)
            return if_village;
         return null;
      }
   
      public Card getArtCard(Player p)
      {
         Card if_village = combatDeck.getArtillery(p.getArtLevel());
         Card temp=null, dummy=null;
         if(!p.getNation().equals("Village")){
            temp = (Card)p.playerUI.myCombatDeck.getArtillery(p.getArtLevel());
         }
         for(int k = 0; k < 2; k++)//number of players
         {
            if(players[k] != p)  
               dummy = players[k].playerUI.myCombatDeck.getArtillery(p.getArtLevel());
         }
         if(temp != null)
            return temp;
         else if(if_village != null)
            return if_village;
         return null;
      }
   
      public Card getAcftCard()
      {
         Card temp = combatDeck.getAircraft();
         for(int k = 0; k < 2; k++)//number of players
         {
            Card dummy = players[k].playerUI.myCombatDeck.getAircraft();
         }
         if(temp != null)
            return temp;
         return null;
      }
   
      public void addUnitToGrave(Card c)
      {
         combatDeck.addToGrave(c);
         for(int k = 0; k < 2; k++)//number of players
         {
            players[k].playerUI.myCombatDeck.addToGrave(c);
         }
      }
   
      public void checkCombatDecks()
      {
         combatDeck.checkDecks();
         for(int k = 0; k < 2; k++)//number of players
         {
            players[k].playerUI.myCombatDeck.checkDecks();
         }
      }
   
      public void getError(String cause)
      {
         if(cause.equals("civ"))
         {
            c.setTitle("Civilizations cannot be the same!");
         }
         else if(cause.equals("color")) 
         {
            c.setTitle("Colors cannot be the same!");
         }
         c.repaint();
      }
   
      public void advanceCultureTrack(int num, int player, String name)
      {
         boolean indexFound = false;
         int index = 0;
         for(int k = 0; k < 16; k++)
         {
            if(cultureTrack[player][k] != null)
            {
               for(int i = 0; i < 2; i++)//number of players
               {  
                  if(players[i].playerUI.mainBrd2.getIndexOf(cultureTrack[player][k]) >= 0)
                     players[i].playerUI.mainBrd2.remove(players[i].playerUI.mainBrd2.getIndexOf(cultureTrack[player][k]));
               }    
            }
            if(cultureTrack[player][k] == null && !indexFound)
            {
               indexFound = true;
               index = k; 
            }
         }
         int j;  
         for(j = index; j < num+index; j++)
         {
            if(cultureTrack[player][j] == null)     
               cultureTrack[player][j] = new GameJLabel(getStaticImage("data/CUL"+name+".png"));
         //cultre track will be set for [#players][if not null then that is as far advanced]
         //x + 35 per advance on the track
         }
         j--;
         cultureTrack[player][j].setBounds(10+((j)*35), 360, 20, 15);
         for(int i = 0; i < 2; i++)//number of players
         {  
            players[i].playerUI.mainBrd2.add(cultureTrack[player][j], new Integer(4));
            players[i].playerUI.mainBrd2.repaint();  
         }
      }
   
   
      public void setSpaceOwners(){
      //set owners for Settlers and armies    
         for(int k = 0; k < players.length; k++){
            Vector armies = players[k].getArmyVector();
            Vector Settlers = players[k].getSettlerVector();  
            for(int j = 0; j < armies.size(); j++){
               Army a = (Army)armies.get(j);
               for(int l = 0; l < players.length; l++){
                  players[l].playerUI.lb.setOwner(a.getX(), a.getY(), players[k]);
               }			
            }
            for(int j = 0; j < Settlers.size(); j++){
               Settler s = (Settler)Settlers.get(j);
               for(int l = 0; l < players.length; l++){
                  players[l].playerUI.lb.setOwner(s.getX(), s.getY(), players[k]);
               }			
            }
         }    
      }
   
      public void setFirstPlayer(){
         int currentFirstPlayer = -1;//for beginning of game
         for(int k = 0; k < players.length; k++){
            if(players[k].getFirstTurn()){
            //System.out.println("CURRENT "+ k + players[k].getNation());	  
               currentFirstPlayer = k;
               players[k].setFirstTurn(false);
               break;
            }
         }
         if((currentFirstPlayer + 1) >= players.length){
            players[0].setFirstTurn(true);
         //System.out.println("ZERO "+ (currentFirstPlayer + 1));	  
         }
         else{
            players[currentFirstPlayer + 1].setFirstTurn(true);
         //System.out.println("+1 " + (currentFirstPlayer + 1));	  
         }
      }
   
      public void start(Player name) 
      {
         if(!turn.research){
            researchCount++;
         }
      
         if(researchCount < players.length){
         
            for(int k = 0; k < players.length; k++){
               if(turn.research){
                  displayWait(players[k].getNation(), "Turn");
               }
            }
         
         }
         else{
            setFirstPlayer();
            for(int k = 0; k < players.length; k++){
               StartGUI start = new StartGUI(players[k].playerBoard.turnHolder, players[k], players[k].playerUI.lb);
            }
            researchCount = 0;
         }
      //	  StartGUI start = new StartGUI(name.playerBoard.turnHolder, name, name.playerUI.lb);   
      }
   
      public void trade(Player name) 
      {
         if(!turn.startOfTurn){
            startCount++;
         }
      //System.out.println("Start Count: "+startCount);     
         if(startCount < players.length){
            for(int k = 0; k < players.length; k++){
               if(turn.startOfTurn){
                  displayWait(players[k].getNation(), "Turn");
               }
            }
         }
         else{
            for(int k = 0; k < players.length; k++){
               TradeGUI trade = new TradeGUI(players[k].playerBoard.turnHolder, players[k], players[k].playerUI.lb);
            }
            startCount = 0;
         }  
      //     TradeGUI trade = new TradeGUI(name.playerBoard.turnHolder, name, name.playerUI.lb);
      }
   
      public void city(Player name) 
      {
         if(!turn.trade){
            tradeCount++;
         }
         if(tradeCount < players.length){
            for(int k = 0; k < players.length; k++){
               if(turn.trade){
                  displayWait(players[k].getNation(), "Turn");
               }
            }
         }
         else{
            tradeCount = 0;
            city();  
         }
      //		CityGUI city = new CityGUI(name.playerBoard.turnHolder, name, name.playerUI.lb);
      }
   
      public void city() 
      {
         for(int k = 0; k < players.length; k++){
            if(players[k].getFirstTurn()){
               CityGUI city = new CityGUI(players[k].playerBoard.turnHolder, players[k], players[k].playerUI.lb);
               displayWait(players[k].getNation(), "Turn");
               break;
            }
         }	  
      }
   
      public void move(Player name) 
      {
         if(!turn.cityManage){
            cityCount++;
         }
      //System.out.println("CITY TURNS: " +cityCount+" "+name.getNation());	  
         if(!turn.cityManage && cityCount < players.length){
         
            int currentPlayer = name.getPlayerNumber() + 1;//plus 1 for next player
         //System.out.println("CITY TURNS: ELSE IF NOT TURN "+name.getNation());	  
            if(currentPlayer >= players.length){
               currentPlayer = 0;
            }
         
            if(turn.cityManage){
            //System.out.println("BEGINNING CITY INSTANCE "+players[currentPlayer].getNation());	  
               CityGUI city = new CityGUI(players[currentPlayer].playerBoard.turnHolder, players[currentPlayer], players[currentPlayer].playerUI.lb);
            }
            else{
               for(int k = 0; k < players.length; k++){
                  if(players[currentPlayer] != players[k]){
                     displayWait(players[currentPlayer].getNation(), "Turn");
                  //System.out.println(players[k].getNation()+" WAITING FOR "+players[currentPlayer].getNation()+ " IN ELSE");	  
                  }
               }
            }
         
            if(!turn.cityManage){
               for(int k = 0; k < players.length; k++){
                  if(players[currentPlayer] != players[k]){
                     displayWait(players[currentPlayer].getNation(), "Turn");
                  //System.out.println(players[k].getNation()+" WAITING FOR "+players[currentPlayer].getNation()+ " IN IF");	  
                  }
               }
            }	 
         }
      
         if(cityCount > players.length && turn.move){
         //System.out.println("CITY TURNS: END "+name.getNation());	  
            cityCount = 0;
            move();  
         }
         else if(cityCount >= players.length && turn.move){
         //System.out.println("CITY TURNS: ELSE IF END "+name.getNation());	  
            boolean turnsLeft=false; 
            for(int k = 0; k < players.length; k++){
               if(turn.cityManage){
                  displayWait(players[k].getNation(), "Turn");
               //System.out.println(name.getNation()+" WAITING FOR "+players[k].getNation()+ " IN ELSE IF");	  
                  turnsLeft = true;
                  break;  
               }
            }
            if(!turnsLeft){
            //		System.out.println("CITY TURNS: END ELSE IF"+name.getNation());	  
               cityCount = 0;
               move();  
            }
         }
      //      MoveGUI move = new MoveGUI(name.playerBoard.turnHolder, name, name.playerUI.lb);
      //      if(!name.getNation().equals(aiChoice))
      //        updatePostMove(name);  
      }
   
      public void move(){
      
         for(int k = 0; k < players.length; k++){
            if(players[k].getFirstTurn()){
               MoveGUI move = new MoveGUI(players[k].playerBoard.turnHolder, players[k], players[k].playerUI.lb);
               displayWait(players[k].getNation(), "Turn");
               break;
            }
         }	  
      }
   
      public void research(Player name) 
      {
         if(!turn.move){
            moveCount++;
         }
         if(moveCount < players.length){
            int currentPlayer = name.getPlayerNumber() + 1;//plus 1 for next player
            if(currentPlayer >= players.length){
               currentPlayer = 0;
            }
         
            MoveGUI move = new MoveGUI(players[currentPlayer].playerBoard.turnHolder, players[currentPlayer], players[currentPlayer].playerUI.lb);
         
            for(int k = 0; k < players.length; k++){
               if(turn.move){
                  displayWait(players[k].getNation(), "Turn");
               }
            }
         
         }
         else{
            for(int k = 0; k < players.length; k++){
               ResearchGUI research = new ResearchGUI(players[k].playerBoard.turnHolder, players[k], players[k].playerUI.lb);
            }
            moveCount = 0;
         }
      //     ResearchGUI res = new ResearchGUI(name.playerBoard.turnHolder, name, name.playerUI.lb);
      }
   
      public void displayWait(String nation, String turnType){
         for(int k = 0; k < players.length; k++){
            if(!players[k].getNation().equals(nation)){
            //			   CityGUI.displayWaiting(players[k], players[k].playerUI.lb, nation);
               players[k].playerBoard.turnHolder.removeAll();
               players[k].playerBoard.turnHolder.repaint();
                  
               JInternalFrame select = new JInternalFrame("Waiting");
               select.setBackground(players[k].playerColor); 
               select.setBorder(new BevelBorder(1, new Color(0, 0, 0), new Color(255, 255, 255)));
               select.setPreferredSize(new Dimension(198, 198));
            
               GameJLabel pick = new GameJLabel(" Waiting on "+nation);
               GameJLabel pick2 = new GameJLabel("  to finish "+turnType);
               pick.setBounds(00, 50, 190, 20);
               if(players[k].getColor().equals("Blue")){
                  pick.setForeground(new Color(250, 250, 250));			 
               }
               else{
                  pick.setForeground(new Color(50, 50, 50));			 
               }
               pick2.setBounds(00, 70, 190, 20);
            
               if(players[k].getColor().equals("Blue")){
                  pick2.setForeground(new Color(250, 250, 250));			 
               }
               else{
                  pick2.setForeground(new Color(50, 50, 50));			 
               }
               Font f = new Font("Monospaced", Font.BOLD, 13);
               pick.setFont(f); 
               pick2.setFont(f); 
            
               select.add(pick);
               select.add(pick2);
               select.show();
            
               players[k].playerBoard.turnHolder.add(select);
            
               try
               {select.setMaximum(true);}
                  catch(PropertyVetoException ex)
                  {}
            }
         }
      }
   //   public void addTechListener(GameJLabel l)
   //	{
   //	  l.addMouseListener(this);
   //	}
      public void addCulturePane(GameJLayeredPane lp)
      {
         mainBoard.add(lp, new Integer(1));	
      }
   
      public void addWonderPane(GameJLayeredPane lp)
      {
         mainBoard.add(lp, new Integer(1));	
      }
   
      public void addTechPane(GameJLayeredPane lp)
      {
         mainBoard.add(lp, new Integer(0));	
      }
   
      public static void updateTechPane(GameJLayeredPane lp)
      {
      //     mainBoard.repaint();
      }
     
      public void blowUpIcon(int x, int y, GameImageIcon l, Player p)
      {
         GameJLabel lbl = new GameJLabel(l);
         lbl.setBounds(x, y, 220, 150);
      
         p.playerUI.mainBoard.add(lbl, new Integer(20));  
         p.playerUI.mainBoard.repaint();
      }
   
      public void blowDownIcon(Player p)
      {
         p.playerUI.mainBoard.remove(0);  
         p.playerUI.mainBoard.repaint();
      }
   
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
   
      public int getInfDeckSize()
      {
         return combatDeck.getInfSize();
      }
   
      public int getMntDeckSize()
      {
         return combatDeck.getMntSize();
      }
   
      public int getArtDeckSize()
      {
         return combatDeck.getArtSize();
      }
   
      public int getAcftDeckSize()
      {
         return combatDeck.getAcftSize();
      }
   
      public void mouseClicked(MouseEvent e)
      {
         for(int k = 0; k < players.length; k++){//number of players  
            if(e.getSource() == players[k].getPlayerUI().otherBoards[0]){
            //					  System.out.println("OTHERBOARD " + players[k].getPlayerUI().otherBoards[0].getName());
               JDialog d = new JDialog(c, players[k].getPlayerUI().otherBoards[0].getName(), true);
               d.setSize(new Dimension(805, 490));
            //			        d.setLocation(0, 0);
               d.getContentPane().setBackground(new Color(0, 0, 0));    
               d.setResizable(false);
            
               int targetPlayer = 0;
               for(; targetPlayer < players.length; targetPlayer++){
                  if(players[targetPlayer].getNation().equals(players[k].getPlayerUI().otherBoards[0].getName())){
                     break;
                  }
               }
               PlayerBoard pb = new PlayerBoard(players[targetPlayer].getPlayerBoard(), players[targetPlayer]);
             
               GameJLayeredPane a = pb.getPlayerboard();
            	   
               d.add(a);
               d.setVisible(true);
            }
         } 
      }//end mouseClicked
   
      public void playGame(Player p)
      {
      //        turn[0].startTurn(players[0]);
      //        turn[1].startTurn(players[1]);
      
//         for(int k = 0; k < players.length; k++){
            p.start();
//         }
      }
   
      public void playTrack(String name)
      {
         try{
            InputStream in = new FileInputStream("data/"+name+".wav");
         //	     AudioStream as = new AudioStream(in);         
         //  		  AudioPlayer.player.start(as);            
         }catch(FileNotFoundException FNFEx){
				System.out.println("File not found");
            FNFEx.printStackTrace(); 
			}
      }
   
	public GameImageIcon getImage(String pathName){
     GameImageIcon image;
     URL url = getClass().getResource(pathName);
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
     URL url = CivGUI.class.getResource(pathName);
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


   /* Get and Set methods*/


	public ClientGUI getClientGUI() {
		return clientGUI;
	}
	public void setClientGUI(ClientGUI clientGUI) {
		this.clientGUI = clientGUI;
	}
	public int getMaxWheat() {
		return maxWheat;
	}
	public void setMaxWheat(int maxWheat) {
		this.maxWheat = maxWheat;
	}
	public int getMaxIron() {
		return maxIron;
	}
	public void setMaxIron(int maxIron) {
		this.maxIron = maxIron;
	}
	public int getMaxIncense() {
		return maxIncense;
	}
	public void setMaxIncense(int maxIncense) {
		this.maxIncense = maxIncense;
	}
	public int getMaxSilk() {
		return maxSilk;
	}
	public void setMaxSilk(int maxSilk) {
		this.maxSilk = maxSilk;
	}
	public int getNumIron() {
		return numIron;
	}
	public void setNumIron(int numIron) {
		this.numIron = numIron;
	}
	public int getNumSilk() {
		return numSilk;
	}
	public void setNumSilk(int numSilk) {
		this.numSilk = numSilk;
	}
	public int getNumWheat() {
		return numWheat;
	}
	public void setNumWheat(int numWheat) {
		this.numWheat = numWheat;
	}
	public int getNumIncense() {
		return numIncense;
	}
	public void setNumIncense(int numIncense) {
		this.numIncense = numIncense;
	}
	public int getStartCount() {
		return startCount;
	}
	public void setStartCount(int startCount) {
		this.startCount = startCount;
	}
	public int getTradeCount() {
		return tradeCount;
	}
	public void setTradeCount(int tradeCount) {
		this.tradeCount = tradeCount;
	}
	public int getCityCount() {
		return cityCount;
	}
	public void setCityCount(int cityCount) {
		this.cityCount = cityCount;
	}
	public int getMoveCount() {
		return moveCount;
	}
	public void setMoveCount(int moveCount) {
		this.moveCount = moveCount;
	}
	public int getResearchCount() {
		return researchCount;
	}
	public void setResearchCount(int researchCount) {
		this.researchCount = researchCount;
	}
	public int getAvailableMarket() {
		return availableMarket;
	}
	public void setAvailableMarket(int availableMarket) {
		this.availableMarket = availableMarket;
	}
	public int getAvailableBarrack() {
		return availableBarrack;
	}
	public void setAvailableBarrack(int availableBarrack) {
		this.availableBarrack = availableBarrack;
	}
	public int getAvailableGranary() {
		return availableGranary;
	}
	public void setAvailableGranary(int availableGranary) {
		this.availableGranary = availableGranary;
	}
	public int getAvailableHarbor() {
		return availableHarbor;
	}
	public void setAvailableHarbor(int availableHarbor) {
		this.availableHarbor = availableHarbor;
	}
	public int getAvailableLibrary() {
		return availableLibrary;
	}
	public void setAvailableLibrary(int availableLibrary) {
		this.availableLibrary = availableLibrary;
	}
	public int getAvailableTemple() {
		return availableTemple;
	}
	public void setAvailableTemple(int availableTemple) {
		this.availableTemple = availableTemple;
	}
	public int getAvailableTradepost() {
		return availableTradepost;
	}
	public void setAvailableTradepost(int availableTradepost) {
		this.availableTradepost = availableTradepost;
	}
	public int getAvailableWorkshop() {
		return availableWorkshop;
	}
	public void setAvailableWorkshop(int availableWorkshop) {
		this.availableWorkshop = availableWorkshop;
	}
	public int getAvailableAcademy() {
		return availableAcademy;
	}
	public void setAvailableAcademy(int availableAcademy) {
		this.availableAcademy = availableAcademy;
	}
	public int getAvailableAqueduct() {
		return availableAqueduct;
	}
	public void setAvailableAqueduct(int availableAqueduct) {
		this.availableAqueduct = availableAqueduct;
	}
	public int getAvailableUniversity() {
		return availableUniversity;
	}
	public void setAvailableUniversity(int availableUniversity) {
		this.availableUniversity = availableUniversity;
	}
	public int getAvailableBank() {
		return availableBank;
	}
	public void setAvailableBank(int availableBank) {
		this.availableBank = availableBank;
	}
	public int getAvailableCathedral() {
		return availableCathedral;
	}
	public void setAvailableCathedral(int availableCathedral) {
		this.availableCathedral = availableCathedral;
	}
	public int getAvailableIronmine() {
		return availableIronmine;
	}
	public void setAvailableIronmine(int availableIronmine) {
		this.availableIronmine = availableIronmine;
	}
	public Deck getMyWonderDeck() {
		return myWonderDeck;
	}
	public void setMyWonderDeck(Deck myWonderDeck) {
		this.myWonderDeck = myWonderDeck;
	}
	public Deck getMyCombatDeck() {
		return myCombatDeck;
	}
	public void setMyCombatDeck(Deck myCombatDeck) {
		this.myCombatDeck = myCombatDeck;
	}
	public Deck getMyCulture1Deck() {
		return myCulture1Deck;
	}
	public void setMyCulture1Deck(Deck myCulture1Deck) {
		this.myCulture1Deck = myCulture1Deck;
	}
	public Deck getMyCulture2Deck() {
		return myCulture2Deck;
	}
	public void setMyCulture2Deck(Deck myCulture2Deck) {
		this.myCulture2Deck = myCulture2Deck;
	}
	public Deck getMyCulture3Deck() {
		return myCulture3Deck;
	}
	public void setMyCulture3Deck(Deck myCulture3Deck) {
		this.myCulture3Deck = myCulture3Deck;
	}
	public Vector getVillages() {
		return villages;
	}
	public void setVillages(Vector villages) {
		this.villages = villages;
	}
	public Vector getHuts() {
		return huts;
	}
	public void setHuts(Vector huts) {
		this.huts = huts;
	}
	public Vector getGreatPeople() {
		return greatPeople;
	}
	public void setGreatPeople(Vector greatPeople) {
		this.greatPeople = greatPeople;
	}
	public Vector getReady() {
		return ready;
	}
	public void setReady(Vector ready) {
		this.ready = ready;
	}
	public GameJPanel getPanel0() {
		return panel0;
	}
	public void setPanel0(GameJPanel panel0) {
		this.panel0 = panel0;
	}
	public GameJPanel getPanel1() {
		return panel1;
	}
	public void setPanel1(GameJPanel panel1) {
		this.panel1 = panel1;
	}
	public GameJPanel getPanel2() {
		return panel2;
	}
	public void setPanel2(GameJPanel panel2) {
		this.panel2 = panel2;
	}
	public GameJPanel getPanel3() {
		return panel3;
	}
	public void setPanel3(GameJPanel panel3) {
		this.panel3 = panel3;
	}
	public GameJPanel getPanel4() {
		return panel4;
	}
	public void setPanel4(GameJPanel panel4) {
		this.panel4 = panel4;
	}
	public GameJPanel getPanel5() {
		return panel5;
	}
	public void setPanel5(GameJPanel panel5) {
		this.panel5 = panel5;
	}
	public GameJPanel getPanel6() {
		return panel6;
	}
	public void setPanel6(GameJPanel panel6) {
		this.panel6 = panel6;
	}
	public GameJPanel getPanel7() {
		return panel7;
	}
	public void setPanel7(GameJPanel panel7) {
		this.panel7 = panel7;
	}
	public GameJPanel getMypanel0() {
		return mypanel0;
	}
	public void setMypanel0(GameJPanel mypanel0) {
		this.mypanel0 = mypanel0;
	}
	public GameJPanel getMypanel1() {
		return mypanel1;
	}
	public void setMypanel1(GameJPanel mypanel1) {
		this.mypanel1 = mypanel1;
	}
	public GameJPanel getMypanel2() {
		return mypanel2;
	}
	public void setMypanel2(GameJPanel mypanel2) {
		this.mypanel2 = mypanel2;
	}
	public GameJPanel getMypanel3() {
		return mypanel3;
	}
	public void setMypanel3(GameJPanel mypanel3) {
		this.mypanel3 = mypanel3;
	}
	public GameJPanel getMypanel4() {
		return mypanel4;
	}
	public void setMypanel4(GameJPanel mypanel4) {
		this.mypanel4 = mypanel4;
	}
	public GameJPanel getMypanel5() {
		return mypanel5;
	}
	public void setMypanel5(GameJPanel mypanel5) {
		this.mypanel5 = mypanel5;
	}
	public GameJPanel getMypanel6() {
		return mypanel6;
	}
	public void setMypanel6(GameJPanel mypanel6) {
		this.mypanel6 = mypanel6;
	}
	public GameJPanel getMypanel7() {
		return mypanel7;
	}
	public void setMypanel7(GameJPanel mypanel7) {
		this.mypanel7 = mypanel7;
	}
	public GameJLabel getMyfog1() {
		return myfog1;
	}
	public void setMyfog1(GameJLabel myfog1) {
		this.myfog1 = myfog1;
	}
	public GameJLabel getMyfog2() {
		return myfog2;
	}
	public void setMyfog2(GameJLabel myfog2) {
		this.myfog2 = myfog2;
	}
	public GameJLabel getMyfog3() {
		return myfog3;
	}
	public void setMyfog3(GameJLabel myfog3) {
		this.myfog3 = myfog3;
	}
	public GameJLabel getMyfog4() {
		return myfog4;
	}
	public void setMyfog4(GameJLabel myfog4) {
		this.myfog4 = myfog4;
	}
	public GameJLabel getMyfog5() {
		return myfog5;
	}
	public void setMyfog5(GameJLabel myfog5) {
		this.myfog5 = myfog5;
	}
	public GameJLabel getMyfog6() {
		return myfog6;
	}
	public void setMyfog6(GameJLabel myfog6) {
		this.myfog6 = myfog6;
	}
	public GameJLayeredPane getAm1() {
		return am1;
	}
	public void setAm1(GameJLayeredPane am1) {
		this.am1 = am1;
	}
	public GameJLayeredPane getAm2() {
		return am2;
	}
	public void setAm2(GameJLayeredPane am2) {
		this.am2 = am2;
	}
	public GameJLayeredPane getAm3() {
		return am3;
	}
	public void setAm3(GameJLayeredPane am3) {
		this.am3 = am3;
	}
	public GameJLayeredPane getAm4() {
		return am4;
	}
	public void setAm4(GameJLayeredPane am4) {
		this.am4 = am4;
	}
	public GameJLayeredPane getAm5() {
		return am5;
	}
	public void setAm5(GameJLayeredPane am5) {
		this.am5 = am5;
	}
	public GameJLayeredPane getAm6() {
		return am6;
	}
	public void setAm6(GameJLayeredPane am6) {
		this.am6 = am6;
	}
	public GameJLayeredPane getAm7() {
		return am7;
	}
	public void setAm7(GameJLayeredPane am7) {
		this.am7 = am7;
	}
	public GameJLayeredPane getAm8() {
		return am8;
	}
	public void setAm8(GameJLayeredPane am8) {
		this.am8 = am8;
	}
	public GameJLayeredPane getAm9() {
		return am9;
	}
	public void setAm9(GameJLayeredPane am9) {
		this.am9 = am9;
	}
	public GameJLayeredPane getAm10() {
		return am10;
	}
	public void setAm10(GameJLayeredPane am10) {
		this.am10 = am10;
	}
	public GameJLayeredPane getAm11() {
		return am11;
	}
	public void setAm11(GameJLayeredPane am11) {
		this.am11 = am11;
	}
	public GameJLayeredPane getAm12() {
		return am12;
	}
	public void setAm12(GameJLayeredPane am12) {
		this.am12 = am12;
	}
	public GameJLayeredPane getAm13() {
		return am13;
	}
	public void setAm13(GameJLayeredPane am13) {
		this.am13 = am13;
	}
	public GameJLayeredPane getAm14() {
		return am14;
	}
	public void setAm14(GameJLayeredPane am14) {
		this.am14 = am14;
	}
	public GameJLayeredPane getAm15() {
		return am15;
	}
	public void setAm15(GameJLayeredPane am15) {
		this.am15 = am15;
	}
	public GameJLayeredPane getAm16() {
		return am16;
	}
	public void setAm16(GameJLayeredPane am16) {
		this.am16 = am16;
	}
	public GameJLayeredPane getCh1() {
		return ch1;
	}
	public void setCh1(GameJLayeredPane ch1) {
		this.ch1 = ch1;
	}
	public GameJLayeredPane getCh2() {
		return ch2;
	}
	public void setCh2(GameJLayeredPane ch2) {
		this.ch2 = ch2;
	}
	public GameJLayeredPane getCh3() {
		return ch3;
	}
	public void setCh3(GameJLayeredPane ch3) {
		this.ch3 = ch3;
	}
	public GameJLayeredPane getCh4() {
		return ch4;
	}
	public void setCh4(GameJLayeredPane ch4) {
		this.ch4 = ch4;
	}
	public GameJLayeredPane getCh5() {
		return ch5;
	}
	public void setCh5(GameJLayeredPane ch5) {
		this.ch5 = ch5;
	}
	public GameJLayeredPane getCh6() {
		return ch6;
	}
	public void setCh6(GameJLayeredPane ch6) {
		this.ch6 = ch6;
	}
	public GameJLayeredPane getCh7() {
		return ch7;
	}
	public void setCh7(GameJLayeredPane ch7) {
		this.ch7 = ch7;
	}
	public GameJLayeredPane getCh8() {
		return ch8;
	}
	public void setCh8(GameJLayeredPane ch8) {
		this.ch8 = ch8;
	}
	public GameJLayeredPane getCh9() {
		return ch9;
	}
	public void setCh9(GameJLayeredPane ch9) {
		this.ch9 = ch9;
	}
	public GameJLayeredPane getCh10() {
		return ch10;
	}
	public void setCh10(GameJLayeredPane ch10) {
		this.ch10 = ch10;
	}
	public GameJLayeredPane getCh11() {
		return ch11;
	}
	public void setCh11(GameJLayeredPane ch11) {
		this.ch11 = ch11;
	}
	public GameJLayeredPane getCh12() {
		return ch12;
	}
	public void setCh12(GameJLayeredPane ch12) {
		this.ch12 = ch12;
	}
	public GameJLayeredPane getCh13() {
		return ch13;
	}
	public void setCh13(GameJLayeredPane ch13) {
		this.ch13 = ch13;
	}
	public GameJLayeredPane getCh14() {
		return ch14;
	}
	public void setCh14(GameJLayeredPane ch14) {
		this.ch14 = ch14;
	}
	public GameJLayeredPane getCh15() {
		return ch15;
	}
	public void setCh15(GameJLayeredPane ch15) {
		this.ch15 = ch15;
	}
	public GameJLayeredPane getCh16() {
		return ch16;
	}
	public void setCh16(GameJLayeredPane ch16) {
		this.ch16 = ch16;
	}
	public GameJLayeredPane getEgy1() {
		return egy1;
	}
	public void setEgy1(GameJLayeredPane egy1) {
		this.egy1 = egy1;
	}
	public GameJLayeredPane getEgy2() {
		return egy2;
	}
	public void setEgy2(GameJLayeredPane egy2) {
		this.egy2 = egy2;
	}
	public GameJLayeredPane getEgy3() {
		return egy3;
	}
	public void setEgy3(GameJLayeredPane egy3) {
		this.egy3 = egy3;
	}
	public GameJLayeredPane getEgy4() {
		return egy4;
	}
	public void setEgy4(GameJLayeredPane egy4) {
		this.egy4 = egy4;
	}
	public GameJLayeredPane getEgy5() {
		return egy5;
	}
	public void setEgy5(GameJLayeredPane egy5) {
		this.egy5 = egy5;
	}
	public GameJLayeredPane getEgy6() {
		return egy6;
	}
	public void setEgy6(GameJLayeredPane egy6) {
		this.egy6 = egy6;
	}
	public GameJLayeredPane getEgy7() {
		return egy7;
	}
	public void setEgy7(GameJLayeredPane egy7) {
		this.egy7 = egy7;
	}
	public GameJLayeredPane getEgy8() {
		return egy8;
	}
	public void setEgy8(GameJLayeredPane egy8) {
		this.egy8 = egy8;
	}
	public GameJLayeredPane getEgy9() {
		return egy9;
	}
	public void setEgy9(GameJLayeredPane egy9) {
		this.egy9 = egy9;
	}
	public GameJLayeredPane getEgy10() {
		return egy10;
	}
	public void setEgy10(GameJLayeredPane egy10) {
		this.egy10 = egy10;
	}
	public GameJLayeredPane getEgy11() {
		return egy11;
	}
	public void setEgy11(GameJLayeredPane egy11) {
		this.egy11 = egy11;
	}
	public GameJLayeredPane getEgy12() {
		return egy12;
	}
	public void setEgy12(GameJLayeredPane egy12) {
		this.egy12 = egy12;
	}
	public GameJLayeredPane getEgy13() {
		return egy13;
	}
	public void setEgy13(GameJLayeredPane egy13) {
		this.egy13 = egy13;
	}
	public GameJLayeredPane getEgy14() {
		return egy14;
	}
	public void setEgy14(GameJLayeredPane egy14) {
		this.egy14 = egy14;
	}
	public GameJLayeredPane getEgy15() {
		return egy15;
	}
	public void setEgy15(GameJLayeredPane egy15) {
		this.egy15 = egy15;
	}
	public GameJLayeredPane getEgy16() {
		return egy16;
	}
	public void setEgy16(GameJLayeredPane egy16) {
		this.egy16 = egy16;
	}
	public GameJLayeredPane getGer1() {
		return ger1;
	}
	public void setGer1(GameJLayeredPane ger1) {
		this.ger1 = ger1;
	}
	public GameJLayeredPane getGer2() {
		return ger2;
	}
	public void setGer2(GameJLayeredPane ger2) {
		this.ger2 = ger2;
	}
	public GameJLayeredPane getGer3() {
		return ger3;
	}
	public void setGer3(GameJLayeredPane ger3) {
		this.ger3 = ger3;
	}
	public GameJLayeredPane getGer4() {
		return ger4;
	}
	public void setGer4(GameJLayeredPane ger4) {
		this.ger4 = ger4;
	}
	public GameJLayeredPane getGer5() {
		return ger5;
	}
	public void setGer5(GameJLayeredPane ger5) {
		this.ger5 = ger5;
	}
	public GameJLayeredPane getGer6() {
		return ger6;
	}
	public void setGer6(GameJLayeredPane ger6) {
		this.ger6 = ger6;
	}
	public GameJLayeredPane getGer7() {
		return ger7;
	}
	public void setGer7(GameJLayeredPane ger7) {
		this.ger7 = ger7;
	}
	public GameJLayeredPane getGer8() {
		return ger8;
	}
	public void setGer8(GameJLayeredPane ger8) {
		this.ger8 = ger8;
	}
	public GameJLayeredPane getGer9() {
		return ger9;
	}
	public void setGer9(GameJLayeredPane ger9) {
		this.ger9 = ger9;
	}
	public GameJLayeredPane getGer10() {
		return ger10;
	}
	public void setGer10(GameJLayeredPane ger10) {
		this.ger10 = ger10;
	}
	public GameJLayeredPane getGer11() {
		return ger11;
	}
	public void setGer11(GameJLayeredPane ger11) {
		this.ger11 = ger11;
	}
	public GameJLayeredPane getGer12() {
		return ger12;
	}
	public void setGer12(GameJLayeredPane ger12) {
		this.ger12 = ger12;
	}
	public GameJLayeredPane getGer13() {
		return ger13;
	}
	public void setGer13(GameJLayeredPane ger13) {
		this.ger13 = ger13;
	}
	public GameJLayeredPane getGer14() {
		return ger14;
	}
	public void setGer14(GameJLayeredPane ger14) {
		this.ger14 = ger14;
	}
	public GameJLayeredPane getGer15() {
		return ger15;
	}
	public void setGer15(GameJLayeredPane ger15) {
		this.ger15 = ger15;
	}
	public GameJLayeredPane getGer16() {
		return ger16;
	}
	public void setGer16(GameJLayeredPane ger16) {
		this.ger16 = ger16;
	}
	public GameJLayeredPane getRo1() {
		return ro1;
	}
	public void setRo1(GameJLayeredPane ro1) {
		this.ro1 = ro1;
	}
	public GameJLayeredPane getRo2() {
		return ro2;
	}
	public void setRo2(GameJLayeredPane ro2) {
		this.ro2 = ro2;
	}
	public GameJLayeredPane getRo3() {
		return ro3;
	}
	public void setRo3(GameJLayeredPane ro3) {
		this.ro3 = ro3;
	}
	public GameJLayeredPane getRo4() {
		return ro4;
	}
	public void setRo4(GameJLayeredPane ro4) {
		this.ro4 = ro4;
	}
	public GameJLayeredPane getRo5() {
		return ro5;
	}
	public void setRo5(GameJLayeredPane ro5) {
		this.ro5 = ro5;
	}
	public GameJLayeredPane getRo6() {
		return ro6;
	}
	public void setRo6(GameJLayeredPane ro6) {
		this.ro6 = ro6;
	}
	public GameJLayeredPane getRo7() {
		return ro7;
	}
	public void setRo7(GameJLayeredPane ro7) {
		this.ro7 = ro7;
	}
	public GameJLayeredPane getRo8() {
		return ro8;
	}
	public void setRo8(GameJLayeredPane ro8) {
		this.ro8 = ro8;
	}
	public GameJLayeredPane getRo9() {
		return ro9;
	}
	public void setRo9(GameJLayeredPane ro9) {
		this.ro9 = ro9;
	}
	public GameJLayeredPane getRo10() {
		return ro10;
	}
	public void setRo10(GameJLayeredPane ro10) {
		this.ro10 = ro10;
	}
	public GameJLayeredPane getRo11() {
		return ro11;
	}
	public void setRo11(GameJLayeredPane ro11) {
		this.ro11 = ro11;
	}
	public GameJLayeredPane getRo12() {
		return ro12;
	}
	public void setRo12(GameJLayeredPane ro12) {
		this.ro12 = ro12;
	}
	public GameJLayeredPane getRo13() {
		return ro13;
	}
	public void setRo13(GameJLayeredPane ro13) {
		this.ro13 = ro13;
	}
	public GameJLayeredPane getRo14() {
		return ro14;
	}
	public void setRo14(GameJLayeredPane ro14) {
		this.ro14 = ro14;
	}
	public GameJLayeredPane getRo15() {
		return ro15;
	}
	public void setRo15(GameJLayeredPane ro15) {
		this.ro15 = ro15;
	}
	public GameJLayeredPane getRo16() {
		return ro16;
	}
	public void setRo16(GameJLayeredPane ro16) {
		this.ro16 = ro16;
	}
	public GameJLayeredPane getRuss1() {
		return russ1;
	}
	public void setRuss1(GameJLayeredPane russ1) {
		this.russ1 = russ1;
	}
	public GameJLayeredPane getRuss2() {
		return russ2;
	}
	public void setRuss2(GameJLayeredPane russ2) {
		this.russ2 = russ2;
	}
	public GameJLayeredPane getRuss3() {
		return russ3;
	}
	public void setRuss3(GameJLayeredPane russ3) {
		this.russ3 = russ3;
	}
	public GameJLayeredPane getRuss4() {
		return russ4;
	}
	public void setRuss4(GameJLayeredPane russ4) {
		this.russ4 = russ4;
	}
	public GameJLayeredPane getRuss5() {
		return russ5;
	}
	public void setRuss5(GameJLayeredPane russ5) {
		this.russ5 = russ5;
	}
	public GameJLayeredPane getRuss6() {
		return russ6;
	}
	public void setRuss6(GameJLayeredPane russ6) {
		this.russ6 = russ6;
	}
	public GameJLayeredPane getRuss7() {
		return russ7;
	}
	public void setRuss7(GameJLayeredPane russ7) {
		this.russ7 = russ7;
	}
	public GameJLayeredPane getRuss8() {
		return russ8;
	}
	public void setRuss8(GameJLayeredPane russ8) {
		this.russ8 = russ8;
	}
	public GameJLayeredPane getRuss9() {
		return russ9;
	}
	public void setRuss9(GameJLayeredPane russ9) {
		this.russ9 = russ9;
	}
	public GameJLayeredPane getRuss10() {
		return russ10;
	}
	public void setRuss10(GameJLayeredPane russ10) {
		this.russ10 = russ10;
	}
	public GameJLayeredPane getRuss11() {
		return russ11;
	}
	public void setRuss11(GameJLayeredPane russ11) {
		this.russ11 = russ11;
	}
	public GameJLayeredPane getRuss12() {
		return russ12;
	}
	public void setRuss12(GameJLayeredPane russ12) {
		this.russ12 = russ12;
	}
	public GameJLayeredPane getRuss13() {
		return russ13;
	}
	public void setRuss13(GameJLayeredPane russ13) {
		this.russ13 = russ13;
	}
	public GameJLayeredPane getRuss14() {
		return russ14;
	}
	public void setRuss14(GameJLayeredPane russ14) {
		this.russ14 = russ14;
	}
	public GameJLayeredPane getRuss15() {
		return russ15;
	}
	public void setRuss15(GameJLayeredPane russ15) {
		this.russ15 = russ15;
	}
	public GameJLayeredPane getRuss16() {
		return russ16;
	}
	public void setRuss16(GameJLayeredPane russ16) {
		this.russ16 = russ16;
	}
	public GameJLayeredPane getRand1_1() {
		return rand1_1;
	}
	public void setRand1_1(GameJLayeredPane rand1_1) {
		this.rand1_1 = rand1_1;
	}
	public GameJLayeredPane getRand1_2() {
		return rand1_2;
	}
	public void setRand1_2(GameJLayeredPane rand1_2) {
		this.rand1_2 = rand1_2;
	}
	public GameJLayeredPane getRand1_3() {
		return rand1_3;
	}
	public void setRand1_3(GameJLayeredPane rand1_3) {
		this.rand1_3 = rand1_3;
	}
	public GameJLayeredPane getRand1_4() {
		return rand1_4;
	}
	public void setRand1_4(GameJLayeredPane rand1_4) {
		this.rand1_4 = rand1_4;
	}
	public GameJLayeredPane getRand1_5() {
		return rand1_5;
	}
	public void setRand1_5(GameJLayeredPane rand1_5) {
		this.rand1_5 = rand1_5;
	}
	public GameJLayeredPane getRand1_6() {
		return rand1_6;
	}
	public void setRand1_6(GameJLayeredPane rand1_6) {
		this.rand1_6 = rand1_6;
	}
	public GameJLayeredPane getRand1_7() {
		return rand1_7;
	}
	public void setRand1_7(GameJLayeredPane rand1_7) {
		this.rand1_7 = rand1_7;
	}
	public GameJLayeredPane getRand1_8() {
		return rand1_8;
	}
	public void setRand1_8(GameJLayeredPane rand1_8) {
		this.rand1_8 = rand1_8;
	}
	public GameJLayeredPane getRand1_9() {
		return rand1_9;
	}
	public void setRand1_9(GameJLayeredPane rand1_9) {
		this.rand1_9 = rand1_9;
	}
	public GameJLayeredPane getRand1_10() {
		return rand1_10;
	}
	public void setRand1_10(GameJLayeredPane rand1_10) {
		this.rand1_10 = rand1_10;
	}
	public GameJLayeredPane getRand1_11() {
		return rand1_11;
	}
	public void setRand1_11(GameJLayeredPane rand1_11) {
		this.rand1_11 = rand1_11;
	}
	public GameJLayeredPane getRand1_12() {
		return rand1_12;
	}
	public void setRand1_12(GameJLayeredPane rand1_12) {
		this.rand1_12 = rand1_12;
	}
	public GameJLayeredPane getRand1_13() {
		return rand1_13;
	}
	public void setRand1_13(GameJLayeredPane rand1_13) {
		this.rand1_13 = rand1_13;
	}
	public GameJLayeredPane getRand1_14() {
		return rand1_14;
	}
	public void setRand1_14(GameJLayeredPane rand1_14) {
		this.rand1_14 = rand1_14;
	}
	public GameJLayeredPane getRand1_15() {
		return rand1_15;
	}
	public void setRand1_15(GameJLayeredPane rand1_15) {
		this.rand1_15 = rand1_15;
	}
	public GameJLayeredPane getRand1_16() {
		return rand1_16;
	}
	public void setRand1_16(GameJLayeredPane rand1_16) {
		this.rand1_16 = rand1_16;
	}
	public GameJLayeredPane getRand2_1() {
		return rand2_1;
	}
	public void setRand2_1(GameJLayeredPane rand2_1) {
		this.rand2_1 = rand2_1;
	}
	public GameJLayeredPane getRand2_2() {
		return rand2_2;
	}
	public void setRand2_2(GameJLayeredPane rand2_2) {
		this.rand2_2 = rand2_2;
	}
	public GameJLayeredPane getRand2_3() {
		return rand2_3;
	}
	public void setRand2_3(GameJLayeredPane rand2_3) {
		this.rand2_3 = rand2_3;
	}
	public GameJLayeredPane getRand2_4() {
		return rand2_4;
	}
	public void setRand2_4(GameJLayeredPane rand2_4) {
		this.rand2_4 = rand2_4;
	}
	public GameJLayeredPane getRand2_5() {
		return rand2_5;
	}
	public void setRand2_5(GameJLayeredPane rand2_5) {
		this.rand2_5 = rand2_5;
	}
	public GameJLayeredPane getRand2_6() {
		return rand2_6;
	}
	public void setRand2_6(GameJLayeredPane rand2_6) {
		this.rand2_6 = rand2_6;
	}
	public GameJLayeredPane getRand2_7() {
		return rand2_7;
	}
	public void setRand2_7(GameJLayeredPane rand2_7) {
		this.rand2_7 = rand2_7;
	}
	public GameJLayeredPane getRand2_8() {
		return rand2_8;
	}
	public void setRand2_8(GameJLayeredPane rand2_8) {
		this.rand2_8 = rand2_8;
	}
	public GameJLayeredPane getRand2_9() {
		return rand2_9;
	}
	public void setRand2_9(GameJLayeredPane rand2_9) {
		this.rand2_9 = rand2_9;
	}
	public GameJLayeredPane getRand2_10() {
		return rand2_10;
	}
	public void setRand2_10(GameJLayeredPane rand2_10) {
		this.rand2_10 = rand2_10;
	}
	public GameJLayeredPane getRand2_11() {
		return rand2_11;
	}
	public void setRand2_11(GameJLayeredPane rand2_11) {
		this.rand2_11 = rand2_11;
	}
	public GameJLayeredPane getRand2_12() {
		return rand2_12;
	}
	public void setRand2_12(GameJLayeredPane rand2_12) {
		this.rand2_12 = rand2_12;
	}
	public GameJLayeredPane getRand2_13() {
		return rand2_13;
	}
	public void setRand2_13(GameJLayeredPane rand2_13) {
		this.rand2_13 = rand2_13;
	}
	public GameJLayeredPane getRand2_14() {
		return rand2_14;
	}
	public void setRand2_14(GameJLayeredPane rand2_14) {
		this.rand2_14 = rand2_14;
	}
	public GameJLayeredPane getRand2_15() {
		return rand2_15;
	}
	public void setRand2_15(GameJLayeredPane rand2_15) {
		this.rand2_15 = rand2_15;
	}
	public GameJLayeredPane getRand2_16() {
		return rand2_16;
	}
	public void setRand2_16(GameJLayeredPane rand2_16) {
		this.rand2_16 = rand2_16;
	}
	public GameJLayeredPane getRand3_1() {
		return rand3_1;
	}
	public void setRand3_1(GameJLayeredPane rand3_1) {
		this.rand3_1 = rand3_1;
	}
	public GameJLayeredPane getRand3_2() {
		return rand3_2;
	}
	public void setRand3_2(GameJLayeredPane rand3_2) {
		this.rand3_2 = rand3_2;
	}
	public GameJLayeredPane getRand3_3() {
		return rand3_3;
	}
	public void setRand3_3(GameJLayeredPane rand3_3) {
		this.rand3_3 = rand3_3;
	}
	public GameJLayeredPane getRand3_4() {
		return rand3_4;
	}
	public void setRand3_4(GameJLayeredPane rand3_4) {
		this.rand3_4 = rand3_4;
	}
	public GameJLayeredPane getRand3_5() {
		return rand3_5;
	}
	public void setRand3_5(GameJLayeredPane rand3_5) {
		this.rand3_5 = rand3_5;
	}
	public GameJLayeredPane getRand3_6() {
		return rand3_6;
	}
	public void setRand3_6(GameJLayeredPane rand3_6) {
		this.rand3_6 = rand3_6;
	}
	public GameJLayeredPane getRand3_7() {
		return rand3_7;
	}
	public void setRand3_7(GameJLayeredPane rand3_7) {
		this.rand3_7 = rand3_7;
	}
	public GameJLayeredPane getRand3_8() {
		return rand3_8;
	}
	public void setRand3_8(GameJLayeredPane rand3_8) {
		this.rand3_8 = rand3_8;
	}
	public GameJLayeredPane getRand3_9() {
		return rand3_9;
	}
	public void setRand3_9(GameJLayeredPane rand3_9) {
		this.rand3_9 = rand3_9;
	}
	public GameJLayeredPane getRand3_10() {
		return rand3_10;
	}
	public void setRand3_10(GameJLayeredPane rand3_10) {
		this.rand3_10 = rand3_10;
	}
	public GameJLayeredPane getRand3_11() {
		return rand3_11;
	}
	public void setRand3_11(GameJLayeredPane rand3_11) {
		this.rand3_11 = rand3_11;
	}
	public GameJLayeredPane getRand3_12() {
		return rand3_12;
	}
	public void setRand3_12(GameJLayeredPane rand3_12) {
		this.rand3_12 = rand3_12;
	}
	public GameJLayeredPane getRand3_13() {
		return rand3_13;
	}
	public void setRand3_13(GameJLayeredPane rand3_13) {
		this.rand3_13 = rand3_13;
	}
	public GameJLayeredPane getRand3_14() {
		return rand3_14;
	}
	public void setRand3_14(GameJLayeredPane rand3_14) {
		this.rand3_14 = rand3_14;
	}
	public GameJLayeredPane getRand3_15() {
		return rand3_15;
	}
	public void setRand3_15(GameJLayeredPane rand3_15) {
		this.rand3_15 = rand3_15;
	}
	public GameJLayeredPane getRand3_16() {
		return rand3_16;
	}
	public void setRand3_16(GameJLayeredPane rand3_16) {
		this.rand3_16 = rand3_16;
	}
	public GameJLayeredPane getRand4_1() {
		return rand4_1;
	}
	public void setRand4_1(GameJLayeredPane rand4_1) {
		this.rand4_1 = rand4_1;
	}
	public GameJLayeredPane getRand4_2() {
		return rand4_2;
	}
	public void setRand4_2(GameJLayeredPane rand4_2) {
		this.rand4_2 = rand4_2;
	}
	public GameJLayeredPane getRand4_3() {
		return rand4_3;
	}
	public void setRand4_3(GameJLayeredPane rand4_3) {
		this.rand4_3 = rand4_3;
	}
	public GameJLayeredPane getRand4_4() {
		return rand4_4;
	}
	public void setRand4_4(GameJLayeredPane rand4_4) {
		this.rand4_4 = rand4_4;
	}
	public GameJLayeredPane getRand4_5() {
		return rand4_5;
	}
	public void setRand4_5(GameJLayeredPane rand4_5) {
		this.rand4_5 = rand4_5;
	}
	public GameJLayeredPane getRand4_6() {
		return rand4_6;
	}
	public void setRand4_6(GameJLayeredPane rand4_6) {
		this.rand4_6 = rand4_6;
	}
	public GameJLayeredPane getRand4_7() {
		return rand4_7;
	}
	public void setRand4_7(GameJLayeredPane rand4_7) {
		this.rand4_7 = rand4_7;
	}
	public GameJLayeredPane getRand4_8() {
		return rand4_8;
	}
	public void setRand4_8(GameJLayeredPane rand4_8) {
		this.rand4_8 = rand4_8;
	}
	public GameJLayeredPane getRand4_9() {
		return rand4_9;
	}
	public void setRand4_9(GameJLayeredPane rand4_9) {
		this.rand4_9 = rand4_9;
	}
	public GameJLayeredPane getRand4_10() {
		return rand4_10;
	}
	public void setRand4_10(GameJLayeredPane rand4_10) {
		this.rand4_10 = rand4_10;
	}
	public GameJLayeredPane getRand4_11() {
		return rand4_11;
	}
	public void setRand4_11(GameJLayeredPane rand4_11) {
		this.rand4_11 = rand4_11;
	}
	public GameJLayeredPane getRand4_12() {
		return rand4_12;
	}
	public void setRand4_12(GameJLayeredPane rand4_12) {
		this.rand4_12 = rand4_12;
	}
	public GameJLayeredPane getRand4_13() {
		return rand4_13;
	}
	public void setRand4_13(GameJLayeredPane rand4_13) {
		this.rand4_13 = rand4_13;
	}
	public GameJLayeredPane getRand4_14() {
		return rand4_14;
	}
	public void setRand4_14(GameJLayeredPane rand4_14) {
		this.rand4_14 = rand4_14;
	}
	public GameJLayeredPane getRand4_15() {
		return rand4_15;
	}
	public void setRand4_15(GameJLayeredPane rand4_15) {
		this.rand4_15 = rand4_15;
	}
	public GameJLayeredPane getRand4_16() {
		return rand4_16;
	}
	public void setRand4_16(GameJLayeredPane rand4_16) {
		this.rand4_16 = rand4_16;
	}
	public GameJLayeredPane getRand5_1() {
		return rand5_1;
	}
	public void setRand5_1(GameJLayeredPane rand5_1) {
		this.rand5_1 = rand5_1;
	}
	public GameJLayeredPane getRand5_2() {
		return rand5_2;
	}
	public void setRand5_2(GameJLayeredPane rand5_2) {
		this.rand5_2 = rand5_2;
	}
	public GameJLayeredPane getRand5_3() {
		return rand5_3;
	}
	public void setRand5_3(GameJLayeredPane rand5_3) {
		this.rand5_3 = rand5_3;
	}
	public GameJLayeredPane getRand5_4() {
		return rand5_4;
	}
	public void setRand5_4(GameJLayeredPane rand5_4) {
		this.rand5_4 = rand5_4;
	}
	public GameJLayeredPane getRand5_5() {
		return rand5_5;
	}
	public void setRand5_5(GameJLayeredPane rand5_5) {
		this.rand5_5 = rand5_5;
	}
	public GameJLayeredPane getRand5_6() {
		return rand5_6;
	}
	public void setRand5_6(GameJLayeredPane rand5_6) {
		this.rand5_6 = rand5_6;
	}
	public GameJLayeredPane getRand5_7() {
		return rand5_7;
	}
	public void setRand5_7(GameJLayeredPane rand5_7) {
		this.rand5_7 = rand5_7;
	}
	public GameJLayeredPane getRand5_8() {
		return rand5_8;
	}
	public void setRand5_8(GameJLayeredPane rand5_8) {
		this.rand5_8 = rand5_8;
	}
	public GameJLayeredPane getRand5_9() {
		return rand5_9;
	}
	public void setRand5_9(GameJLayeredPane rand5_9) {
		this.rand5_9 = rand5_9;
	}
	public GameJLayeredPane getRand5_10() {
		return rand5_10;
	}
	public void setRand5_10(GameJLayeredPane rand5_10) {
		this.rand5_10 = rand5_10;
	}
	public GameJLayeredPane getRand5_11() {
		return rand5_11;
	}
	public void setRand5_11(GameJLayeredPane rand5_11) {
		this.rand5_11 = rand5_11;
	}
	public GameJLayeredPane getRand5_12() {
		return rand5_12;
	}
	public void setRand5_12(GameJLayeredPane rand5_12) {
		this.rand5_12 = rand5_12;
	}
	public GameJLayeredPane getRand5_13() {
		return rand5_13;
	}
	public void setRand5_13(GameJLayeredPane rand5_13) {
		this.rand5_13 = rand5_13;
	}
	public GameJLayeredPane getRand5_14() {
		return rand5_14;
	}
	public void setRand5_14(GameJLayeredPane rand5_14) {
		this.rand5_14 = rand5_14;
	}
	public GameJLayeredPane getRand5_15() {
		return rand5_15;
	}
	public void setRand5_15(GameJLayeredPane rand5_15) {
		this.rand5_15 = rand5_15;
	}
	public GameJLayeredPane getRand5_16() {
		return rand5_16;
	}
	public void setRand5_16(GameJLayeredPane rand5_16) {
		this.rand5_16 = rand5_16;
	}
	public GameJLayeredPane getRand6_1() {
		return rand6_1;
	}
	public void setRand6_1(GameJLayeredPane rand6_1) {
		this.rand6_1 = rand6_1;
	}
	public GameJLayeredPane getRand6_2() {
		return rand6_2;
	}
	public void setRand6_2(GameJLayeredPane rand6_2) {
		this.rand6_2 = rand6_2;
	}
	public GameJLayeredPane getRand6_3() {
		return rand6_3;
	}
	public void setRand6_3(GameJLayeredPane rand6_3) {
		this.rand6_3 = rand6_3;
	}
	public GameJLayeredPane getRand6_4() {
		return rand6_4;
	}
	public void setRand6_4(GameJLayeredPane rand6_4) {
		this.rand6_4 = rand6_4;
	}
	public GameJLayeredPane getRand6_5() {
		return rand6_5;
	}
	public void setRand6_5(GameJLayeredPane rand6_5) {
		this.rand6_5 = rand6_5;
	}
	public GameJLayeredPane getRand6_6() {
		return rand6_6;
	}
	public void setRand6_6(GameJLayeredPane rand6_6) {
		this.rand6_6 = rand6_6;
	}
	public GameJLayeredPane getRand6_7() {
		return rand6_7;
	}
	public void setRand6_7(GameJLayeredPane rand6_7) {
		this.rand6_7 = rand6_7;
	}
	public GameJLayeredPane getRand6_8() {
		return rand6_8;
	}
	public void setRand6_8(GameJLayeredPane rand6_8) {
		this.rand6_8 = rand6_8;
	}
	public GameJLayeredPane getRand6_9() {
		return rand6_9;
	}
	public void setRand6_9(GameJLayeredPane rand6_9) {
		this.rand6_9 = rand6_9;
	}
	public GameJLayeredPane getRand6_10() {
		return rand6_10;
	}
	public void setRand6_10(GameJLayeredPane rand6_10) {
		this.rand6_10 = rand6_10;
	}
	public GameJLayeredPane getRand6_11() {
		return rand6_11;
	}
	public void setRand6_11(GameJLayeredPane rand6_11) {
		this.rand6_11 = rand6_11;
	}
	public GameJLayeredPane getRand6_12() {
		return rand6_12;
	}
	public void setRand6_12(GameJLayeredPane rand6_12) {
		this.rand6_12 = rand6_12;
	}
	public GameJLayeredPane getRand6_13() {
		return rand6_13;
	}
	public void setRand6_13(GameJLayeredPane rand6_13) {
		this.rand6_13 = rand6_13;
	}
	public GameJLayeredPane getRand6_14() {
		return rand6_14;
	}
	public void setRand6_14(GameJLayeredPane rand6_14) {
		this.rand6_14 = rand6_14;
	}
	public GameJLayeredPane getRand6_15() {
		return rand6_15;
	}
	public void setRand6_15(GameJLayeredPane rand6_15) {
		this.rand6_15 = rand6_15;
	}
	public GameJLayeredPane getRand6_16() {
		return rand6_16;
	}
	public void setRand6_16(GameJLayeredPane rand6_16) {
		this.rand6_16 = rand6_16;
	}
	public GameJLayeredPane getRand7_1() {
		return rand7_1;
	}
	public void setRand7_1(GameJLayeredPane rand7_1) {
		this.rand7_1 = rand7_1;
	}
	public GameJLayeredPane getRand7_2() {
		return rand7_2;
	}
	public void setRand7_2(GameJLayeredPane rand7_2) {
		this.rand7_2 = rand7_2;
	}
	public GameJLayeredPane getRand7_3() {
		return rand7_3;
	}
	public void setRand7_3(GameJLayeredPane rand7_3) {
		this.rand7_3 = rand7_3;
	}
	public GameJLayeredPane getRand7_4() {
		return rand7_4;
	}
	public void setRand7_4(GameJLayeredPane rand7_4) {
		this.rand7_4 = rand7_4;
	}
	public GameJLayeredPane getRand7_5() {
		return rand7_5;
	}
	public void setRand7_5(GameJLayeredPane rand7_5) {
		this.rand7_5 = rand7_5;
	}
	public GameJLayeredPane getRand7_6() {
		return rand7_6;
	}
	public void setRand7_6(GameJLayeredPane rand7_6) {
		this.rand7_6 = rand7_6;
	}
	public GameJLayeredPane getRand7_7() {
		return rand7_7;
	}
	public void setRand7_7(GameJLayeredPane rand7_7) {
		this.rand7_7 = rand7_7;
	}
	public GameJLayeredPane getRand7_8() {
		return rand7_8;
	}
	public void setRand7_8(GameJLayeredPane rand7_8) {
		this.rand7_8 = rand7_8;
	}
	public GameJLayeredPane getRand7_9() {
		return rand7_9;
	}
	public void setRand7_9(GameJLayeredPane rand7_9) {
		this.rand7_9 = rand7_9;
	}
	public GameJLayeredPane getRand7_10() {
		return rand7_10;
	}
	public void setRand7_10(GameJLayeredPane rand7_10) {
		this.rand7_10 = rand7_10;
	}
	public GameJLayeredPane getRand7_11() {
		return rand7_11;
	}
	public void setRand7_11(GameJLayeredPane rand7_11) {
		this.rand7_11 = rand7_11;
	}
	public GameJLayeredPane getRand7_12() {
		return rand7_12;
	}
	public void setRand7_12(GameJLayeredPane rand7_12) {
		this.rand7_12 = rand7_12;
	}
	public GameJLayeredPane getRand7_13() {
		return rand7_13;
	}
	public void setRand7_13(GameJLayeredPane rand7_13) {
		this.rand7_13 = rand7_13;
	}
	public GameJLayeredPane getRand7_14() {
		return rand7_14;
	}
	public void setRand7_14(GameJLayeredPane rand7_14) {
		this.rand7_14 = rand7_14;
	}
	public GameJLayeredPane getRand7_15() {
		return rand7_15;
	}
	public void setRand7_15(GameJLayeredPane rand7_15) {
		this.rand7_15 = rand7_15;
	}
	public GameJLayeredPane getRand7_16() {
		return rand7_16;
	}
	public void setRand7_16(GameJLayeredPane rand7_16) {
		this.rand7_16 = rand7_16;
	}
	public GameJLayeredPane getRand8_1() {
		return rand8_1;
	}
	public void setRand8_1(GameJLayeredPane rand8_1) {
		this.rand8_1 = rand8_1;
	}
	public GameJLayeredPane getRand8_2() {
		return rand8_2;
	}
	public void setRand8_2(GameJLayeredPane rand8_2) {
		this.rand8_2 = rand8_2;
	}
	public GameJLayeredPane getRand8_3() {
		return rand8_3;
	}
	public void setRand8_3(GameJLayeredPane rand8_3) {
		this.rand8_3 = rand8_3;
	}
	public GameJLayeredPane getRand8_4() {
		return rand8_4;
	}
	public void setRand8_4(GameJLayeredPane rand8_4) {
		this.rand8_4 = rand8_4;
	}
	public GameJLayeredPane getRand8_5() {
		return rand8_5;
	}
	public void setRand8_5(GameJLayeredPane rand8_5) {
		this.rand8_5 = rand8_5;
	}
	public GameJLayeredPane getRand8_6() {
		return rand8_6;
	}
	public void setRand8_6(GameJLayeredPane rand8_6) {
		this.rand8_6 = rand8_6;
	}
	public GameJLayeredPane getRand8_7() {
		return rand8_7;
	}
	public void setRand8_7(GameJLayeredPane rand8_7) {
		this.rand8_7 = rand8_7;
	}
	public GameJLayeredPane getRand8_8() {
		return rand8_8;
	}
	public void setRand8_8(GameJLayeredPane rand8_8) {
		this.rand8_8 = rand8_8;
	}
	public GameJLayeredPane getRand8_9() {
		return rand8_9;
	}
	public void setRand8_9(GameJLayeredPane rand8_9) {
		this.rand8_9 = rand8_9;
	}
	public GameJLayeredPane getRand8_10() {
		return rand8_10;
	}
	public void setRand8_10(GameJLayeredPane rand8_10) {
		this.rand8_10 = rand8_10;
	}
	public GameJLayeredPane getRand8_11() {
		return rand8_11;
	}
	public void setRand8_11(GameJLayeredPane rand8_11) {
		this.rand8_11 = rand8_11;
	}
	public GameJLayeredPane getRand8_12() {
		return rand8_12;
	}
	public void setRand8_12(GameJLayeredPane rand8_12) {
		this.rand8_12 = rand8_12;
	}
	public GameJLayeredPane getRand8_13() {
		return rand8_13;
	}
	public void setRand8_13(GameJLayeredPane rand8_13) {
		this.rand8_13 = rand8_13;
	}
	public GameJLayeredPane getRand8_14() {
		return rand8_14;
	}
	public void setRand8_14(GameJLayeredPane rand8_14) {
		this.rand8_14 = rand8_14;
	}
	public GameJLayeredPane getRand8_15() {
		return rand8_15;
	}
	public void setRand8_15(GameJLayeredPane rand8_15) {
		this.rand8_15 = rand8_15;
	}
	public GameJLayeredPane getRand8_16() {
		return rand8_16;
	}
	public void setRand8_16(GameJLayeredPane rand8_16) {
		this.rand8_16 = rand8_16;
	}
	public GameJLayeredPane getRand9_1() {
		return rand9_1;
	}
	public void setRand9_1(GameJLayeredPane rand9_1) {
		this.rand9_1 = rand9_1;
	}
	public GameJLayeredPane getRand9_2() {
		return rand9_2;
	}
	public void setRand9_2(GameJLayeredPane rand9_2) {
		this.rand9_2 = rand9_2;
	}
	public GameJLayeredPane getRand9_3() {
		return rand9_3;
	}
	public void setRand9_3(GameJLayeredPane rand9_3) {
		this.rand9_3 = rand9_3;
	}
	public GameJLayeredPane getRand9_4() {
		return rand9_4;
	}
	public void setRand9_4(GameJLayeredPane rand9_4) {
		this.rand9_4 = rand9_4;
	}
	public GameJLayeredPane getRand9_5() {
		return rand9_5;
	}
	public void setRand9_5(GameJLayeredPane rand9_5) {
		this.rand9_5 = rand9_5;
	}
	public GameJLayeredPane getRand9_6() {
		return rand9_6;
	}
	public void setRand9_6(GameJLayeredPane rand9_6) {
		this.rand9_6 = rand9_6;
	}
	public GameJLayeredPane getRand9_7() {
		return rand9_7;
	}
	public void setRand9_7(GameJLayeredPane rand9_7) {
		this.rand9_7 = rand9_7;
	}
	public GameJLayeredPane getRand9_8() {
		return rand9_8;
	}
	public void setRand9_8(GameJLayeredPane rand9_8) {
		this.rand9_8 = rand9_8;
	}
	public GameJLayeredPane getRand9_9() {
		return rand9_9;
	}
	public void setRand9_9(GameJLayeredPane rand9_9) {
		this.rand9_9 = rand9_9;
	}
	public GameJLayeredPane getRand9_10() {
		return rand9_10;
	}
	public void setRand9_10(GameJLayeredPane rand9_10) {
		this.rand9_10 = rand9_10;
	}
	public GameJLayeredPane getRand9_11() {
		return rand9_11;
	}
	public void setRand9_11(GameJLayeredPane rand9_11) {
		this.rand9_11 = rand9_11;
	}
	public GameJLayeredPane getRand9_12() {
		return rand9_12;
	}
	public void setRand9_12(GameJLayeredPane rand9_12) {
		this.rand9_12 = rand9_12;
	}
	public GameJLayeredPane getRand9_13() {
		return rand9_13;
	}
	public void setRand9_13(GameJLayeredPane rand9_13) {
		this.rand9_13 = rand9_13;
	}
	public GameJLayeredPane getRand9_14() {
		return rand9_14;
	}
	public void setRand9_14(GameJLayeredPane rand9_14) {
		this.rand9_14 = rand9_14;
	}
	public GameJLayeredPane getRand9_15() {
		return rand9_15;
	}
	public void setRand9_15(GameJLayeredPane rand9_15) {
		this.rand9_15 = rand9_15;
	}
	public GameJLayeredPane getRand9_16() {
		return rand9_16;
	}
	public void setRand9_16(GameJLayeredPane rand9_16) {
		this.rand9_16 = rand9_16;
	}
	public GameJLayeredPane getRand10_1() {
		return rand10_1;
	}
	public void setRand10_1(GameJLayeredPane rand10_1) {
		this.rand10_1 = rand10_1;
	}
	public GameJLayeredPane getRand10_2() {
		return rand10_2;
	}
	public void setRand10_2(GameJLayeredPane rand10_2) {
		this.rand10_2 = rand10_2;
	}
	public GameJLayeredPane getRand10_3() {
		return rand10_3;
	}
	public void setRand10_3(GameJLayeredPane rand10_3) {
		this.rand10_3 = rand10_3;
	}
	public GameJLayeredPane getRand10_4() {
		return rand10_4;
	}
	public void setRand10_4(GameJLayeredPane rand10_4) {
		this.rand10_4 = rand10_4;
	}
	public GameJLayeredPane getRand10_5() {
		return rand10_5;
	}
	public void setRand10_5(GameJLayeredPane rand10_5) {
		this.rand10_5 = rand10_5;
	}
	public GameJLayeredPane getRand10_6() {
		return rand10_6;
	}
	public void setRand10_6(GameJLayeredPane rand10_6) {
		this.rand10_6 = rand10_6;
	}
	public GameJLayeredPane getRand10_7() {
		return rand10_7;
	}
	public void setRand10_7(GameJLayeredPane rand10_7) {
		this.rand10_7 = rand10_7;
	}
	public GameJLayeredPane getRand10_8() {
		return rand10_8;
	}
	public void setRand10_8(GameJLayeredPane rand10_8) {
		this.rand10_8 = rand10_8;
	}
	public GameJLayeredPane getRand10_9() {
		return rand10_9;
	}
	public void setRand10_9(GameJLayeredPane rand10_9) {
		this.rand10_9 = rand10_9;
	}
	public GameJLayeredPane getRand10_10() {
		return rand10_10;
	}
	public void setRand10_10(GameJLayeredPane rand10_10) {
		this.rand10_10 = rand10_10;
	}
	public GameJLayeredPane getRand10_11() {
		return rand10_11;
	}
	public void setRand10_11(GameJLayeredPane rand10_11) {
		this.rand10_11 = rand10_11;
	}
	public GameJLayeredPane getRand10_12() {
		return rand10_12;
	}
	public void setRand10_12(GameJLayeredPane rand10_12) {
		this.rand10_12 = rand10_12;
	}
	public GameJLayeredPane getRand10_13() {
		return rand10_13;
	}
	public void setRand10_13(GameJLayeredPane rand10_13) {
		this.rand10_13 = rand10_13;
	}
	public GameJLayeredPane getRand10_14() {
		return rand10_14;
	}
	public void setRand10_14(GameJLayeredPane rand10_14) {
		this.rand10_14 = rand10_14;
	}
	public GameJLayeredPane getRand10_15() {
		return rand10_15;
	}
	public void setRand10_15(GameJLayeredPane rand10_15) {
		this.rand10_15 = rand10_15;
	}
	public GameJLayeredPane getRand10_16() {
		return rand10_16;
	}
	public void setRand10_16(GameJLayeredPane rand10_16) {
		this.rand10_16 = rand10_16;
	}
	public GameJLayeredPane getRand11_1() {
		return rand11_1;
	}
	public void setRand11_1(GameJLayeredPane rand11_1) {
		this.rand11_1 = rand11_1;
	}
	public GameJLayeredPane getRand11_2() {
		return rand11_2;
	}
	public void setRand11_2(GameJLayeredPane rand11_2) {
		this.rand11_2 = rand11_2;
	}
	public GameJLayeredPane getRand11_3() {
		return rand11_3;
	}
	public void setRand11_3(GameJLayeredPane rand11_3) {
		this.rand11_3 = rand11_3;
	}
	public GameJLayeredPane getRand11_4() {
		return rand11_4;
	}
	public void setRand11_4(GameJLayeredPane rand11_4) {
		this.rand11_4 = rand11_4;
	}
	public GameJLayeredPane getRand11_5() {
		return rand11_5;
	}
	public void setRand11_5(GameJLayeredPane rand11_5) {
		this.rand11_5 = rand11_5;
	}
	public GameJLayeredPane getRand11_6() {
		return rand11_6;
	}
	public void setRand11_6(GameJLayeredPane rand11_6) {
		this.rand11_6 = rand11_6;
	}
	public GameJLayeredPane getRand11_7() {
		return rand11_7;
	}
	public void setRand11_7(GameJLayeredPane rand11_7) {
		this.rand11_7 = rand11_7;
	}
	public GameJLayeredPane getRand11_8() {
		return rand11_8;
	}
	public void setRand11_8(GameJLayeredPane rand11_8) {
		this.rand11_8 = rand11_8;
	}
	public GameJLayeredPane getRand11_9() {
		return rand11_9;
	}
	public void setRand11_9(GameJLayeredPane rand11_9) {
		this.rand11_9 = rand11_9;
	}
	public GameJLayeredPane getRand11_10() {
		return rand11_10;
	}
	public void setRand11_10(GameJLayeredPane rand11_10) {
		this.rand11_10 = rand11_10;
	}
	public GameJLayeredPane getRand11_11() {
		return rand11_11;
	}
	public void setRand11_11(GameJLayeredPane rand11_11) {
		this.rand11_11 = rand11_11;
	}
	public GameJLayeredPane getRand11_12() {
		return rand11_12;
	}
	public void setRand11_12(GameJLayeredPane rand11_12) {
		this.rand11_12 = rand11_12;
	}
	public GameJLayeredPane getRand11_13() {
		return rand11_13;
	}
	public void setRand11_13(GameJLayeredPane rand11_13) {
		this.rand11_13 = rand11_13;
	}
	public GameJLayeredPane getRand11_14() {
		return rand11_14;
	}
	public void setRand11_14(GameJLayeredPane rand11_14) {
		this.rand11_14 = rand11_14;
	}
	public GameJLayeredPane getRand11_15() {
		return rand11_15;
	}
	public void setRand11_15(GameJLayeredPane rand11_15) {
		this.rand11_15 = rand11_15;
	}
	public GameJLayeredPane getRand11_16() {
		return rand11_16;
	}
	public void setRand11_16(GameJLayeredPane rand11_16) {
		this.rand11_16 = rand11_16;
	}
	public GameJLayeredPane getRand12_1() {
		return rand12_1;
	}
	public void setRand12_1(GameJLayeredPane rand12_1) {
		this.rand12_1 = rand12_1;
	}
	public GameJLayeredPane getRand12_2() {
		return rand12_2;
	}
	public void setRand12_2(GameJLayeredPane rand12_2) {
		this.rand12_2 = rand12_2;
	}
	public GameJLayeredPane getRand12_3() {
		return rand12_3;
	}
	public void setRand12_3(GameJLayeredPane rand12_3) {
		this.rand12_3 = rand12_3;
	}
	public GameJLayeredPane getRand12_4() {
		return rand12_4;
	}
	public void setRand12_4(GameJLayeredPane rand12_4) {
		this.rand12_4 = rand12_4;
	}
	public GameJLayeredPane getRand12_5() {
		return rand12_5;
	}
	public void setRand12_5(GameJLayeredPane rand12_5) {
		this.rand12_5 = rand12_5;
	}
	public GameJLayeredPane getRand12_6() {
		return rand12_6;
	}
	public void setRand12_6(GameJLayeredPane rand12_6) {
		this.rand12_6 = rand12_6;
	}
	public GameJLayeredPane getRand12_7() {
		return rand12_7;
	}
	public void setRand12_7(GameJLayeredPane rand12_7) {
		this.rand12_7 = rand12_7;
	}
	public GameJLayeredPane getRand12_8() {
		return rand12_8;
	}
	public void setRand12_8(GameJLayeredPane rand12_8) {
		this.rand12_8 = rand12_8;
	}
	public GameJLayeredPane getRand12_9() {
		return rand12_9;
	}
	public void setRand12_9(GameJLayeredPane rand12_9) {
		this.rand12_9 = rand12_9;
	}
	public GameJLayeredPane getRand12_10() {
		return rand12_10;
	}
	public void setRand12_10(GameJLayeredPane rand12_10) {
		this.rand12_10 = rand12_10;
	}
	public GameJLayeredPane getRand12_11() {
		return rand12_11;
	}
	public void setRand12_11(GameJLayeredPane rand12_11) {
		this.rand12_11 = rand12_11;
	}
	public GameJLayeredPane getRand12_12() {
		return rand12_12;
	}
	public void setRand12_12(GameJLayeredPane rand12_12) {
		this.rand12_12 = rand12_12;
	}
	public GameJLayeredPane getRand12_13() {
		return rand12_13;
	}
	public void setRand12_13(GameJLayeredPane rand12_13) {
		this.rand12_13 = rand12_13;
	}
	public GameJLayeredPane getRand12_14() {
		return rand12_14;
	}
	public void setRand12_14(GameJLayeredPane rand12_14) {
		this.rand12_14 = rand12_14;
	}
	public GameJLayeredPane getRand12_15() {
		return rand12_15;
	}
	public void setRand12_15(GameJLayeredPane rand12_15) {
		this.rand12_15 = rand12_15;
	}
	public GameJLayeredPane getRand12_16() {
		return rand12_16;
	}
	public void setRand12_16(GameJLayeredPane rand12_16) {
		this.rand12_16 = rand12_16;
	}
	public GameJLayeredPane getRand13_1() {
		return rand13_1;
	}
	public void setRand13_1(GameJLayeredPane rand13_1) {
		this.rand13_1 = rand13_1;
	}
	public GameJLayeredPane getRand13_2() {
		return rand13_2;
	}
	public void setRand13_2(GameJLayeredPane rand13_2) {
		this.rand13_2 = rand13_2;
	}
	public GameJLayeredPane getRand13_3() {
		return rand13_3;
	}
	public void setRand13_3(GameJLayeredPane rand13_3) {
		this.rand13_3 = rand13_3;
	}
	public GameJLayeredPane getRand13_4() {
		return rand13_4;
	}
	public void setRand13_4(GameJLayeredPane rand13_4) {
		this.rand13_4 = rand13_4;
	}
	public GameJLayeredPane getRand13_5() {
		return rand13_5;
	}
	public void setRand13_5(GameJLayeredPane rand13_5) {
		this.rand13_5 = rand13_5;
	}
	public GameJLayeredPane getRand13_6() {
		return rand13_6;
	}
	public void setRand13_6(GameJLayeredPane rand13_6) {
		this.rand13_6 = rand13_6;
	}
	public GameJLayeredPane getRand13_7() {
		return rand13_7;
	}
	public void setRand13_7(GameJLayeredPane rand13_7) {
		this.rand13_7 = rand13_7;
	}
	public GameJLayeredPane getRand13_8() {
		return rand13_8;
	}
	public void setRand13_8(GameJLayeredPane rand13_8) {
		this.rand13_8 = rand13_8;
	}
	public GameJLayeredPane getRand13_9() {
		return rand13_9;
	}
	public void setRand13_9(GameJLayeredPane rand13_9) {
		this.rand13_9 = rand13_9;
	}
	public GameJLayeredPane getRand13_10() {
		return rand13_10;
	}
	public void setRand13_10(GameJLayeredPane rand13_10) {
		this.rand13_10 = rand13_10;
	}
	public GameJLayeredPane getRand13_11() {
		return rand13_11;
	}
	public void setRand13_11(GameJLayeredPane rand13_11) {
		this.rand13_11 = rand13_11;
	}
	public GameJLayeredPane getRand13_12() {
		return rand13_12;
	}
	public void setRand13_12(GameJLayeredPane rand13_12) {
		this.rand13_12 = rand13_12;
	}
	public GameJLayeredPane getRand13_13() {
		return rand13_13;
	}
	public void setRand13_13(GameJLayeredPane rand13_13) {
		this.rand13_13 = rand13_13;
	}
	public GameJLayeredPane getRand13_14() {
		return rand13_14;
	}
	public void setRand13_14(GameJLayeredPane rand13_14) {
		this.rand13_14 = rand13_14;
	}
	public GameJLayeredPane getRand13_15() {
		return rand13_15;
	}
	public void setRand13_15(GameJLayeredPane rand13_15) {
		this.rand13_15 = rand13_15;
	}
	public GameJLayeredPane getRand13_16() {
		return rand13_16;
	}
	public void setRand13_16(GameJLayeredPane rand13_16) {
		this.rand13_16 = rand13_16;
	}
	public GameJLayeredPane getRand14_1() {
		return rand14_1;
	}
	public void setRand14_1(GameJLayeredPane rand14_1) {
		this.rand14_1 = rand14_1;
	}
	public GameJLayeredPane getRand14_2() {
		return rand14_2;
	}
	public void setRand14_2(GameJLayeredPane rand14_2) {
		this.rand14_2 = rand14_2;
	}
	public GameJLayeredPane getRand14_3() {
		return rand14_3;
	}
	public void setRand14_3(GameJLayeredPane rand14_3) {
		this.rand14_3 = rand14_3;
	}
	public GameJLayeredPane getRand14_4() {
		return rand14_4;
	}
	public void setRand14_4(GameJLayeredPane rand14_4) {
		this.rand14_4 = rand14_4;
	}
	public GameJLayeredPane getRand14_5() {
		return rand14_5;
	}
	public void setRand14_5(GameJLayeredPane rand14_5) {
		this.rand14_5 = rand14_5;
	}
	public GameJLayeredPane getRand14_6() {
		return rand14_6;
	}
	public void setRand14_6(GameJLayeredPane rand14_6) {
		this.rand14_6 = rand14_6;
	}
	public GameJLayeredPane getRand14_7() {
		return rand14_7;
	}
	public void setRand14_7(GameJLayeredPane rand14_7) {
		this.rand14_7 = rand14_7;
	}
	public GameJLayeredPane getRand14_8() {
		return rand14_8;
	}
	public void setRand14_8(GameJLayeredPane rand14_8) {
		this.rand14_8 = rand14_8;
	}
	public GameJLayeredPane getRand14_9() {
		return rand14_9;
	}
	public void setRand14_9(GameJLayeredPane rand14_9) {
		this.rand14_9 = rand14_9;
	}
	public GameJLayeredPane getRand14_10() {
		return rand14_10;
	}
	public void setRand14_10(GameJLayeredPane rand14_10) {
		this.rand14_10 = rand14_10;
	}
	public GameJLayeredPane getRand14_11() {
		return rand14_11;
	}
	public void setRand14_11(GameJLayeredPane rand14_11) {
		this.rand14_11 = rand14_11;
	}
	public GameJLayeredPane getRand14_12() {
		return rand14_12;
	}
	public void setRand14_12(GameJLayeredPane rand14_12) {
		this.rand14_12 = rand14_12;
	}
	public GameJLayeredPane getRand14_13() {
		return rand14_13;
	}
	public void setRand14_13(GameJLayeredPane rand14_13) {
		this.rand14_13 = rand14_13;
	}
	public GameJLayeredPane getRand14_14() {
		return rand14_14;
	}
	public void setRand14_14(GameJLayeredPane rand14_14) {
		this.rand14_14 = rand14_14;
	}
	public GameJLayeredPane getRand14_15() {
		return rand14_15;
	}
	public void setRand14_15(GameJLayeredPane rand14_15) {
		this.rand14_15 = rand14_15;
	}
	public GameJLayeredPane getRand14_16() {
		return rand14_16;
	}
	public void setRand14_16(GameJLayeredPane rand14_16) {
		this.rand14_16 = rand14_16;
	}
	public GameJLayeredPane getMarket() {
		return market;
	}
	public void setMarket(GameJLayeredPane market) {
		this.market = market;
	}
	public GameJLayeredPane getInfLevelHolder() {
		return infLevelHolder;
	}
	public void setInfLevelHolder(GameJLayeredPane infLevelHolder) {
		this.infLevelHolder = infLevelHolder;
	}
	public GameJLayeredPane getMntLevelHolder() {
		return mntLevelHolder;
	}
	public void setMntLevelHolder(GameJLayeredPane mntLevelHolder) {
		this.mntLevelHolder = mntLevelHolder;
	}
	public GameJLayeredPane getArtLevelHolder() {
		return artLevelHolder;
	}
	public void setArtLevelHolder(GameJLayeredPane artLevelHolder) {
		this.artLevelHolder = artLevelHolder;
	}
	public GameJLayeredPane getAcftLevelHolder() {
		return acftLevelHolder;
	}
	public void setAcftLevelHolder(GameJLayeredPane acftLevelHolder) {
		this.acftLevelHolder = acftLevelHolder;
	}
	public Settler getPlayerSettler() {
		return playerSettler;
	}
	public void setPlayerSettler(Settler playerSettler) {
		this.playerSettler = playerSettler;
	}
	public Settler getAiSettler() {
		return aiSettler;
	}
	public void setAiSettler(Settler aiSettler) {
		this.aiSettler = aiSettler;
	}
	public Army getPlayerArmy1() {
		return playerArmy1;
	}
	public void setPlayerArmy1(Army playerArmy1) {
		this.playerArmy1 = playerArmy1;
	}
	public Army getAiArmy1() {
		return aiArmy1;
	}
	public void setAiArmy1(Army aiArmy1) {
		this.aiArmy1 = aiArmy1;
	}
	public Army getPlayerArmy2() {
		return playerArmy2;
	}
	public void setPlayerArmy2(Army playerArmy2) {
		this.playerArmy2 = playerArmy2;
	}
	public GameJLayeredPane getOthers() {
		return others;
	}
	public void setOthers(GameJLayeredPane others) {
		this.others = others;
	}
	public GameJLayeredPane getMainBoard() {
		return mainBoard;
	}
	public void setMainBoard(GameJLayeredPane mainBoard) {
		this.mainBoard = mainBoard;
	}
	public GameJLayeredPane getMainBrd2() {
		return mainBrd2;
	}
	public void setMainBrd2(GameJLayeredPane mainBrd2) {
		this.mainBrd2 = mainBrd2;
	}
	public JTextArea getShowAvailableMarket() {
		return showAvailableMarket;
	}
	public void setShowAvailableMarket(JTextArea showAvailableMarket) {
		this.showAvailableMarket = showAvailableMarket;
	}
	public JTextArea getShowAvailableBarrack() {
		return showAvailableBarrack;
	}
	public void setShowAvailableBarrack(JTextArea showAvailableBarrack) {
		this.showAvailableBarrack = showAvailableBarrack;
	}
	public JTextArea getShowAvailableGranary() {
		return showAvailableGranary;
	}
	public void setShowAvailableGranary(JTextArea showAvailableGranary) {
		this.showAvailableGranary = showAvailableGranary;
	}
	public JTextArea getShowAvailableHarbor() {
		return showAvailableHarbor;
	}
	public void setShowAvailableHarbor(JTextArea showAvailableHarbor) {
		this.showAvailableHarbor = showAvailableHarbor;
	}
	public JTextArea getShowAvailableLibrary() {
		return showAvailableLibrary;
	}
	public void setShowAvailableLibrary(JTextArea showAvailableLibrary) {
		this.showAvailableLibrary = showAvailableLibrary;
	}
	public JTextArea getShowAvailableTemple() {
		return showAvailableTemple;
	}
	public void setShowAvailableTemple(JTextArea showAvailableTemple) {
		this.showAvailableTemple = showAvailableTemple;
	}
	public JTextArea getShowAvailableTradepost() {
		return showAvailableTradepost;
	}
	public void setShowAvailableTradepost(JTextArea showAvailableTradepost) {
		this.showAvailableTradepost = showAvailableTradepost;
	}
	public JTextArea getShowAvailableWorkshop() {
		return showAvailableWorkshop;
	}
	public void setShowAvailableWorkshop(JTextArea showAvailableWorkshop) {
		this.showAvailableWorkshop = showAvailableWorkshop;
	}
	public JTextArea getShowAvailableAcademy() {
		return showAvailableAcademy;
	}
	public void setShowAvailableAcademy(JTextArea showAvailableAcademy) {
		this.showAvailableAcademy = showAvailableAcademy;
	}
	public JTextArea getShowAvailableAqueduct() {
		return showAvailableAqueduct;
	}
	public void setShowAvailableAqueduct(JTextArea showAvailableAqueduct) {
		this.showAvailableAqueduct = showAvailableAqueduct;
	}
	public JTextArea getShowAvailableUniversity() {
		return showAvailableUniversity;
	}
	public void setShowAvailableUniversity(JTextArea showAvailableUniversity) {
		this.showAvailableUniversity = showAvailableUniversity;
	}
	public JTextArea getShowAvailableBank() {
		return showAvailableBank;
	}
	public void setShowAvailableBank(JTextArea showAvailableBank) {
		this.showAvailableBank = showAvailableBank;
	}
	public JTextArea getShowAvailableCathedral() {
		return showAvailableCathedral;
	}
	public void setShowAvailableCathedral(JTextArea showAvailableCathedral) {
		this.showAvailableCathedral = showAvailableCathedral;
	}
	public JTextArea getShowAvailableIronmine() {
		return showAvailableIronmine;
	}
	public void setShowAvailableIronmine(JTextArea showAvailableIronmine) {
		this.showAvailableIronmine = showAvailableIronmine;
	}
	public JTextArea getMarketIron() {
		return marketIron;
	}
	public void setMarketIron(JTextArea marketIron) {
		this.marketIron = marketIron;
	}
	public JTextArea getMarketIncense() {
		return marketIncense;
	}
	public void setMarketIncense(JTextArea marketIncense) {
		this.marketIncense = marketIncense;
	}
	public JTextArea getMarketSilk() {
		return marketSilk;
	}
	public void setMarketSilk(JTextArea marketSilk) {
		this.marketSilk = marketSilk;
	}
	public JTextArea getMarketWheat() {
		return marketWheat;
	}
	public void setMarketWheat(JTextArea marketWheat) {
		this.marketWheat = marketWheat;
	}
	public GameJLabel getPlayerBrd() {
		return playerBrd;
	}
	public void setPlayerBrd(GameJLabel playerBrd) {
		this.playerBrd = playerBrd;
	}
	public GameJLabel getMainBrd() {
		return mainBrd;
	}
	public void setMainBrd(GameJLabel mainBrd) {
		this.mainBrd = mainBrd;
	}
	public GameJLabel getFog1() {
		return fog1;
	}
	public void setFog1(GameJLabel fog1) {
		this.fog1 = fog1;
	}
	public GameJLabel getFog2() {
		return fog2;
	}
	public void setFog2(GameJLabel fog2) {
		this.fog2 = fog2;
	}
	public GameJLabel getFog3() {
		return fog3;
	}
	public void setFog3(GameJLabel fog3) {
		this.fog3 = fog3;
	}
	public GameJLabel getFog4() {
		return fog4;
	}
	public void setFog4(GameJLabel fog4) {
		this.fog4 = fog4;
	}
	public GameJLabel getFog5() {
		return fog5;
	}
	public void setFog5(GameJLabel fog5) {
		this.fog5 = fog5;
	}
	public GameJLabel getFog6() {
		return fog6;
	}
	public void setFog6(GameJLabel fog6) {
		this.fog6 = fog6;
	}
//	public GameJFrame getC() {
//		return c;
//	}
//	public void setC(GameJFrame c) {
//		this.c = c;
//	}
	public GameJPanel getMap() {
		return map;
	}
	public void setMap(GameJPanel map) {
		this.map = map;
	}
	public String getPlayerChoice() {
		return playerChoice;
	}
	public void setPlayerChoice(String playerChoice) {
		this.playerChoice = playerChoice;
	}
	public String getAiChoice() {
		return aiChoice;
	}
	public void setAiChoice(String aiChoice) {
		this.aiChoice = aiChoice;
	}
	public String getPlayerColorChoice() {
		return playerColorChoice;
	}
	public void setPlayerColorChoice(String playerColorChoice) {
		this.playerColorChoice = playerColorChoice;
	}
	public String getAiColorChoice() {
		return aiColorChoice;
	}
	public void setAiColorChoice(String aiColorChoice) {
		this.aiColorChoice = aiColorChoice;
	}
	public LogicBoard getLb() {
		return lb;
	}
	public void setLb(LogicBoard lb) {
		this.lb = lb;
	}
	public String[] getBoardPosition() {
		return boardPosition;
	}
	public void setBoardPosition(String[] boardPosition) {
		this.boardPosition = boardPosition;
	}
	public GameJLabel[] getWonderCards() {
		return wonderCards;
	}
	public void setWonderCards(GameJLabel[] wonderCards) {
		this.wonderCards = wonderCards;
	}
	public GameJLabel[] getWonderBuildings() {
		return wonderBuildings;
	}
	public void setWonderBuildings(GameJLabel[] wonderBuildings) {
		this.wonderBuildings = wonderBuildings;
	}
	public GameJButton[] getOtherBoards() {
		return otherBoards;
	}
	public void setOtherBoards(GameJButton[] otherBoards) {
		this.otherBoards = otherBoards;
	}
	public String[] getPlayerName() {
		return playerName;
	}
	public void setPlayerName(String[] playerName) {
		this.playerName = playerName;
	}
	public int getPlayerNumber() {
		return playerNumber;
	}
	public void setPlayerNumber(int playerNumber) {
		this.playerNumber = playerNumber;
	}
	public int getGameType() {
		return gameType;
	}
	public void setGameType(int gameType) {
		this.gameType = gameType;
	}
	public int getTWO_PLAYER() {
		return TWO_PLAYER;
	}
	public int getTHREE_PLAYER() {
		return THREE_PLAYER;
	}
	public int getFOUR_PLAYER() {
		return FOUR_PLAYER;
	}
	public int getMAX_MARKET() {
		return MAX_MARKET;
	}
	public int getMAX_BANK() {
		return MAX_BANK;
	}
	public int getMAX_TEMPLE() {
		return MAX_TEMPLE;
	}
	public int getMAX_CATHEDRAL() {
		return MAX_CATHEDRAL;
	}
	public int getMAX_GRANARY() {
		return MAX_GRANARY;
	}
	public int getMAX_AQUEDUCT() {
		return MAX_AQUEDUCT;
	}
	public int getMAX_LIBRARY() {
		return MAX_LIBRARY;
	}
	public int getMAX_UNIVERSITY() {
		return MAX_UNIVERSITY;
	}
	public int getMAX_BARRACKS() {
		return MAX_BARRACKS;
	}
	public int getMAX_ACADEMY() {
		return MAX_ACADEMY;
	}
	public int getMAX_WORKSHOP() {
		return MAX_WORKSHOP;
	}
	public int getMAX_IRONMINE() {
		return MAX_IRONMINE;
	}
	public int getMAX_TRADEPOST() {
		return MAX_TRADEPOST;
	}
	public int getMAX_HARBOR() {
		return MAX_HARBOR;
	}
	public GameJLabel[][] getCultureTrack() {
		return cultureTrack;
	}
	public void setCultureTrack(GameJLabel[][] cultureTrack) {
		this.cultureTrack = cultureTrack;
	}
	public boolean isGameEnd() {
		return gameEnd;
	}
	public void setGameEnd(boolean gameEnd) {
		this.gameEnd = gameEnd;
	}
	public PlayerTurn getTurn() {
		return turn;
	}
	public void setTurn(PlayerTurn turn) {
		this.turn = turn;
	}
//	public Die getDice() {
//		return dice;
//	}
//	public void setDice(Die dice) {
//		this.dice = dice;
//	}
	public Deck getCombatDeck() {
		return combatDeck;
	}
	public void setCombatDeck(Deck combatDeck) {
		this.combatDeck = combatDeck;
	}
	public Deck getWonderDeck() {
		return wonderDeck;
	}
	public void setWonderDeck(Deck wonderDeck) {
		this.wonderDeck = wonderDeck;
	}
	public Deck getCulture1Deck() {
		return culture1Deck;
	}
	public void setCulture1Deck(Deck culture1Deck) {
		this.culture1Deck = culture1Deck;
	}
	public Deck getCulture2Deck() {
		return culture2Deck;
	}
	public void setCulture2Deck(Deck culture2Deck) {
		this.culture2Deck = culture2Deck;
	}
	public Deck getCulture3Deck() {
		return culture3Deck;
	}
	public void setCulture3Deck(Deck culture3Deck) {
		this.culture3Deck = culture3Deck;
	}   
   public String getUserName(){
	   return userName;
	}
   public void setUserName(String userName){
	   this.userName = userName;
	}
   public String getGameID(){
	   return gameID;
	}
	public void setGameID(String gameID){
	   this.gameID = gameID;
	}
	
	private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException{
		try{
			clientGUI = (ClientGUI)stream.readObject();
			maxWheat = stream.readInt();
			maxIron = stream.readInt();
			maxIncense = stream.readInt();
			maxSilk = stream.readInt();
			numIron = stream.readInt();
			numSilk = stream.readInt();
			numWheat = stream.readInt();
			numIncense = stream.readInt();
			startCount = stream.readInt();
			tradeCount = stream.readInt();
			cityCount = stream.readInt();
			moveCount = stream.readInt();
			researchCount = stream.readInt();
			availableMarket = stream.readInt();
			availableBarrack = stream.readInt();
			availableGranary = stream.readInt();
			availableHarbor = stream.readInt();
			availableLibrary = stream.readInt();
			availableTemple = stream.readInt();
			availableTradepost = stream.readInt();
			availableWorkshop = stream.readInt();
			availableAcademy = stream.readInt();
			availableAqueduct = stream.readInt();
			availableUniversity = stream.readInt();
			availableBank = stream.readInt();
			availableCathedral = stream.readInt();
			availableIronmine = stream.readInt();
			playerNumber = stream.readInt();
			gameType = stream.readInt();
			players = (Player[])stream.readObject();
			cultureTrack = (GameJLabel[][])stream.readObject();
			gameEnd = stream.readBoolean();
			playerChoice = stream.readUTF();
			aiChoice = stream.readUTF();
			playerColorChoice = stream.readUTF();
			aiColorChoice = stream.readUTF();
			userName = stream.readUTF();
			turn = (PlayerTurn)stream.readObject();
			combatDeck = (Deck)stream.readObject();
			wonderDeck = (Deck)stream.readObject();
			culture1Deck = (Deck)stream.readObject();
			culture2Deck = (Deck)stream.readObject();
			culture3Deck = (Deck)stream.readObject();
			myWonderDeck = (Deck)stream.readObject();
			myCombatDeck = (Deck)stream.readObject();
			myCulture1Deck = (Deck)stream.readObject();
			myCulture2Deck = (Deck)stream.readObject();
			myCulture3Deck = (Deck)stream.readObject();
			villages = (Vector)stream.readObject();
			huts = (Vector)stream.readObject();
			greatPeople = (Vector)stream.readObject();
			ready = (Vector)stream.readObject();
			panel0 = (GameJPanel)stream.readObject();
			panel1 = (GameJPanel)stream.readObject();
			panel2 = (GameJPanel)stream.readObject();
			panel3 = (GameJPanel)stream.readObject();
			panel4 = (GameJPanel)stream.readObject();
			panel5 = (GameJPanel)stream.readObject();
			panel6 = (GameJPanel)stream.readObject();
			panel7 = (GameJPanel)stream.readObject();
			mypanel0 = (GameJPanel)stream.readObject();
			mypanel1 = (GameJPanel)stream.readObject();
			mypanel2 = (GameJPanel)stream.readObject();
			mypanel3 = (GameJPanel)stream.readObject();
			mypanel4 = (GameJPanel)stream.readObject();
			mypanel5 = (GameJPanel)stream.readObject();
			mypanel6 = (GameJPanel)stream.readObject();
			mypanel7 = (GameJPanel)stream.readObject();
			myfog1 = (GameJLabel)stream.readObject();
			myfog2 = (GameJLabel)stream.readObject();
			myfog3 = (GameJLabel)stream.readObject();
			myfog4 = (GameJLabel)stream.readObject();
			myfog5 = (GameJLabel)stream.readObject();
			myfog6 = (GameJLabel)stream.readObject();
			am1 = (GameJLayeredPane)stream.readObject();
			am2 = (GameJLayeredPane)stream.readObject();
			am3 = (GameJLayeredPane)stream.readObject();
			am4 = (GameJLayeredPane)stream.readObject();
			am5 = (GameJLayeredPane)stream.readObject();
			am6 = (GameJLayeredPane)stream.readObject();
			am7 = (GameJLayeredPane)stream.readObject();
			am8 = (GameJLayeredPane)stream.readObject();
			am9 = (GameJLayeredPane)stream.readObject();
			am10 = (GameJLayeredPane)stream.readObject();
			am11 = (GameJLayeredPane)stream.readObject();
			am12 = (GameJLayeredPane)stream.readObject();
			am13 = (GameJLayeredPane)stream.readObject();
			am14 = (GameJLayeredPane)stream.readObject();
			am15 = (GameJLayeredPane)stream.readObject();
			am16 = (GameJLayeredPane)stream.readObject();
			ch1 = (GameJLayeredPane)stream.readObject();
			ch2 = (GameJLayeredPane)stream.readObject();
			ch3 = (GameJLayeredPane)stream.readObject();
			ch4 = (GameJLayeredPane)stream.readObject();
			ch5 = (GameJLayeredPane)stream.readObject();
			ch6 = (GameJLayeredPane)stream.readObject();
			ch7 = (GameJLayeredPane)stream.readObject();
			ch8 = (GameJLayeredPane)stream.readObject();
			ch9 = (GameJLayeredPane)stream.readObject();
			ch10 = (GameJLayeredPane)stream.readObject();
			ch11 = (GameJLayeredPane)stream.readObject();
			ch12 = (GameJLayeredPane)stream.readObject();
			ch13 = (GameJLayeredPane)stream.readObject();
			ch14 = (GameJLayeredPane)stream.readObject();
			ch15 = (GameJLayeredPane)stream.readObject();
			ch16 = (GameJLayeredPane)stream.readObject();
			egy1 = (GameJLayeredPane)stream.readObject();
			egy2 = (GameJLayeredPane)stream.readObject();
			egy3 = (GameJLayeredPane)stream.readObject();
			egy4 = (GameJLayeredPane)stream.readObject();
			egy5 = (GameJLayeredPane)stream.readObject();
			egy6 = (GameJLayeredPane)stream.readObject();
			egy7 = (GameJLayeredPane)stream.readObject();
			egy8 = (GameJLayeredPane)stream.readObject();
			egy9 = (GameJLayeredPane)stream.readObject();
			egy10 = (GameJLayeredPane)stream.readObject();
			egy11 = (GameJLayeredPane)stream.readObject();
			egy12 = (GameJLayeredPane)stream.readObject();
			egy13 = (GameJLayeredPane)stream.readObject();
			egy14 = (GameJLayeredPane)stream.readObject();
			egy15 = (GameJLayeredPane)stream.readObject();
			egy16 = (GameJLayeredPane)stream.readObject();
			ger1 = (GameJLayeredPane)stream.readObject();
			ger2 = (GameJLayeredPane)stream.readObject();
			ger3 = (GameJLayeredPane)stream.readObject();
			ger4 = (GameJLayeredPane)stream.readObject();
			ger5 = (GameJLayeredPane)stream.readObject();
			ger6 = (GameJLayeredPane)stream.readObject();
			ger7 = (GameJLayeredPane)stream.readObject();
			ger8 = (GameJLayeredPane)stream.readObject();
			ger9 = (GameJLayeredPane)stream.readObject();
			ger10 = (GameJLayeredPane)stream.readObject();
			ger11 = (GameJLayeredPane)stream.readObject();
			ger12 = (GameJLayeredPane)stream.readObject();
			ger13 = (GameJLayeredPane)stream.readObject();
			ger14 = (GameJLayeredPane)stream.readObject();
			ger15 = (GameJLayeredPane)stream.readObject();
			ger16 = (GameJLayeredPane)stream.readObject();
			ro1 = (GameJLayeredPane)stream.readObject();
			ro2 = (GameJLayeredPane)stream.readObject();
			ro3 = (GameJLayeredPane)stream.readObject();
			ro4 = (GameJLayeredPane)stream.readObject();
			ro5 = (GameJLayeredPane)stream.readObject();
			ro6 = (GameJLayeredPane)stream.readObject();
			ro7 = (GameJLayeredPane)stream.readObject();
			ro8 = (GameJLayeredPane)stream.readObject();
			ro9 = (GameJLayeredPane)stream.readObject();
			ro10 = (GameJLayeredPane)stream.readObject();
			ro11 = (GameJLayeredPane)stream.readObject();
			ro12 = (GameJLayeredPane)stream.readObject();
			ro13 = (GameJLayeredPane)stream.readObject();
			ro14 = (GameJLayeredPane)stream.readObject();
			ro15 = (GameJLayeredPane)stream.readObject();
			ro16 = (GameJLayeredPane)stream.readObject();
			russ1 = (GameJLayeredPane)stream.readObject();
			russ2 = (GameJLayeredPane)stream.readObject();
			russ3 = (GameJLayeredPane)stream.readObject();
			russ4 = (GameJLayeredPane)stream.readObject();
			russ5 = (GameJLayeredPane)stream.readObject();
			russ6 = (GameJLayeredPane)stream.readObject();
			russ7 = (GameJLayeredPane)stream.readObject();
			russ8 = (GameJLayeredPane)stream.readObject();
			russ9 = (GameJLayeredPane)stream.readObject();
			russ10 = (GameJLayeredPane)stream.readObject();
			russ11 = (GameJLayeredPane)stream.readObject();
			russ12 = (GameJLayeredPane)stream.readObject();
			russ13 = (GameJLayeredPane)stream.readObject();
			russ14 = (GameJLayeredPane)stream.readObject();
			russ15 = (GameJLayeredPane)stream.readObject();
			russ16 = (GameJLayeredPane)stream.readObject();															
			rand1_1 = (GameJLayeredPane)stream.readObject();
			rand1_2 = (GameJLayeredPane)stream.readObject();
			rand1_3 = (GameJLayeredPane)stream.readObject();
			rand1_4 = (GameJLayeredPane)stream.readObject();
			rand1_5 = (GameJLayeredPane)stream.readObject();
			rand1_6 = (GameJLayeredPane)stream.readObject();
			rand1_7 = (GameJLayeredPane)stream.readObject();
			rand1_8 = (GameJLayeredPane)stream.readObject();
			rand1_9 = (GameJLayeredPane)stream.readObject();
			rand1_10 = (GameJLayeredPane)stream.readObject();
			rand1_11 = (GameJLayeredPane)stream.readObject();
			rand1_12 = (GameJLayeredPane)stream.readObject();
			rand1_13 = (GameJLayeredPane)stream.readObject();
			rand1_14 = (GameJLayeredPane)stream.readObject();
			rand1_15 = (GameJLayeredPane)stream.readObject();
			rand1_16 = (GameJLayeredPane)stream.readObject();
			rand2_1 = (GameJLayeredPane)stream.readObject();
			rand2_2 = (GameJLayeredPane)stream.readObject();
			rand2_3 = (GameJLayeredPane)stream.readObject();
			rand2_4 = (GameJLayeredPane)stream.readObject();
			rand2_5 = (GameJLayeredPane)stream.readObject();
			rand2_6 = (GameJLayeredPane)stream.readObject();
			rand2_7 = (GameJLayeredPane)stream.readObject();
			rand2_8 = (GameJLayeredPane)stream.readObject();
			rand2_9 = (GameJLayeredPane)stream.readObject();
			rand2_10 =(GameJLayeredPane) stream.readObject();
			rand2_11 =(GameJLayeredPane) stream.readObject();
			rand2_12 =(GameJLayeredPane) stream.readObject();
			rand2_13 =(GameJLayeredPane) stream.readObject();
			rand2_14 =(GameJLayeredPane) stream.readObject();
			rand2_15 =(GameJLayeredPane) stream.readObject();
			rand2_16 =(GameJLayeredPane) stream.readObject();
			rand3_1 = (GameJLayeredPane)stream.readObject();
			rand3_2 = (GameJLayeredPane)stream.readObject();
			rand3_3 = (GameJLayeredPane)stream.readObject();
			rand3_4 = (GameJLayeredPane)stream.readObject();
			rand3_5 = (GameJLayeredPane)stream.readObject();
			rand3_6 = (GameJLayeredPane)stream.readObject();
			rand3_7 = (GameJLayeredPane)stream.readObject();
			rand3_8 = (GameJLayeredPane)stream.readObject();
			rand3_9 = (GameJLayeredPane)stream.readObject();
			rand3_10 =(GameJLayeredPane) stream.readObject();
			rand3_11 =(GameJLayeredPane) stream.readObject();
			rand3_12 =(GameJLayeredPane) stream.readObject();
			rand3_13 =(GameJLayeredPane) stream.readObject();
			rand3_14 =(GameJLayeredPane) stream.readObject();
			rand3_15 =(GameJLayeredPane) stream.readObject();
			rand3_16 =(GameJLayeredPane) stream.readObject();							
			rand4_1 = (GameJLayeredPane)stream.readObject();
			rand4_2 = (GameJLayeredPane)stream.readObject();
			rand4_3 = (GameJLayeredPane)stream.readObject();
			rand4_4 = (GameJLayeredPane)stream.readObject();
			rand4_5 = (GameJLayeredPane)stream.readObject();
			rand4_6 = (GameJLayeredPane)stream.readObject();
			rand4_7 = (GameJLayeredPane)stream.readObject();
			rand4_8 = (GameJLayeredPane)stream.readObject();
			rand4_9 = (GameJLayeredPane)stream.readObject();
			rand4_10 = (GameJLayeredPane) stream.readObject();
			rand4_11 = (GameJLayeredPane) stream.readObject();
			rand4_12 = (GameJLayeredPane) stream.readObject();
			rand4_13 = (GameJLayeredPane) stream.readObject();
			rand4_14 = (GameJLayeredPane)stream.readObject();
			rand4_15 = (GameJLayeredPane) stream.readObject();
			rand4_16 = (GameJLayeredPane)stream.readObject();						
			rand5_1 = (GameJLayeredPane) stream.readObject();
			rand5_2 = (GameJLayeredPane) stream.readObject();
			rand5_3 = (GameJLayeredPane) stream.readObject();
			rand5_4 = (GameJLayeredPane) stream.readObject();
			rand5_5 = (GameJLayeredPane) stream.readObject();
			rand5_6 = (GameJLayeredPane) stream.readObject();
			rand5_7 = (GameJLayeredPane) stream.readObject();
			rand5_8 = (GameJLayeredPane) stream.readObject();
			rand5_9 = (GameJLayeredPane) stream.readObject();
			rand5_10 = (GameJLayeredPane) stream.readObject();
			rand5_11 = (GameJLayeredPane) stream.readObject();
			rand5_12 = (GameJLayeredPane) stream.readObject();
			rand5_13 = (GameJLayeredPane) stream.readObject();
			rand5_14 = (GameJLayeredPane) stream.readObject();
			rand5_15 = (GameJLayeredPane) stream.readObject();
			rand5_16 = (GameJLayeredPane) stream.readObject();							
			rand6_1 = (GameJLayeredPane) stream.readObject();
			rand6_2 = (GameJLayeredPane) stream.readObject();
			rand6_3 = (GameJLayeredPane) stream.readObject();
			rand6_4 = (GameJLayeredPane) stream.readObject();
			rand6_5 = (GameJLayeredPane) stream.readObject();
			rand6_6 = (GameJLayeredPane) stream.readObject();
			rand6_7 = (GameJLayeredPane) stream.readObject();
			rand6_8 = (GameJLayeredPane) stream.readObject();
			rand6_9 = (GameJLayeredPane) stream.readObject();
			rand6_10 = (GameJLayeredPane) stream.readObject();
			rand6_11 = (GameJLayeredPane) stream.readObject();
			rand6_12 = (GameJLayeredPane) stream.readObject();
			rand6_13 = (GameJLayeredPane) stream.readObject();
			rand6_14 = (GameJLayeredPane) stream.readObject();
			rand6_15 = (GameJLayeredPane) stream.readObject();
			rand6_16 = (GameJLayeredPane) stream.readObject();						
			rand7_1 = (GameJLayeredPane) stream.readObject();
			rand7_2 = (GameJLayeredPane) stream.readObject();
			rand7_3 = (GameJLayeredPane) stream.readObject();
			rand7_4 = (GameJLayeredPane) stream.readObject();
			rand7_5 = (GameJLayeredPane) stream.readObject();
			rand7_6 = (GameJLayeredPane) stream.readObject();
			rand7_7 = (GameJLayeredPane) stream.readObject();
			rand7_8 = (GameJLayeredPane) stream.readObject();
			rand7_9 = (GameJLayeredPane) stream.readObject();
			rand7_10 = (GameJLayeredPane) stream.readObject();
			rand7_11 = (GameJLayeredPane) stream.readObject();
			rand7_12 = (GameJLayeredPane) stream.readObject();
			rand7_13 = (GameJLayeredPane) stream.readObject();
			rand7_14 = (GameJLayeredPane) stream.readObject();
			rand7_15 = (GameJLayeredPane) stream.readObject();
			rand7_16 = (GameJLayeredPane) stream.readObject();							
			rand8_1 = (GameJLayeredPane) stream.readObject();
			rand8_2 = (GameJLayeredPane) stream.readObject();
			rand8_3 = (GameJLayeredPane) stream.readObject();
			rand8_4 = (GameJLayeredPane) stream.readObject();
			rand8_5 = (GameJLayeredPane) stream.readObject();
			rand8_6 = (GameJLayeredPane) stream.readObject();
			rand8_7 = (GameJLayeredPane) stream.readObject();
			rand8_8 = (GameJLayeredPane) stream.readObject();
			rand8_9 = (GameJLayeredPane) stream.readObject();
			rand8_10 = (GameJLayeredPane) stream.readObject();
			rand8_11 = (GameJLayeredPane) stream.readObject();
			rand8_12 = (GameJLayeredPane) stream.readObject();
			rand8_13 = (GameJLayeredPane) stream.readObject();
			rand8_14 = (GameJLayeredPane) stream.readObject();
			rand8_15 = (GameJLayeredPane) stream.readObject();
			rand8_16 = (GameJLayeredPane) stream.readObject();							
			rand9_1 = (GameJLayeredPane) stream.readObject();
			rand9_2 = (GameJLayeredPane) stream.readObject();
			rand9_3 = (GameJLayeredPane) stream.readObject();
			rand9_4 = (GameJLayeredPane) stream.readObject();
			rand9_5 = (GameJLayeredPane) stream.readObject();
			rand9_6 = (GameJLayeredPane) stream.readObject();
			rand9_7 = (GameJLayeredPane) stream.readObject();
			rand9_8 = (GameJLayeredPane) stream.readObject();
			rand9_9 = (GameJLayeredPane) stream.readObject();
			rand9_10 = (GameJLayeredPane) stream.readObject();
			rand9_11 = (GameJLayeredPane) stream.readObject();
			rand9_12 = (GameJLayeredPane) stream.readObject();
			rand9_13 = (GameJLayeredPane) stream.readObject();
			rand9_14 = (GameJLayeredPane) stream.readObject();
			rand9_15 = (GameJLayeredPane) stream.readObject();
			rand9_16 = (GameJLayeredPane) stream.readObject();							
			rand10_1 = (GameJLayeredPane) stream.readObject();
			rand10_2 = (GameJLayeredPane) stream.readObject();
			rand10_3 = (GameJLayeredPane) stream.readObject();
			rand10_4 = (GameJLayeredPane) stream.readObject();
			rand10_5 = (GameJLayeredPane) stream.readObject();
			rand10_6 = (GameJLayeredPane) stream.readObject();
			rand10_7 = (GameJLayeredPane) stream.readObject();
			rand10_8 = (GameJLayeredPane) stream.readObject();
			rand10_9 = (GameJLayeredPane) stream.readObject();
			rand10_10 = (GameJLayeredPane) stream.readObject();
			rand10_11 = (GameJLayeredPane) stream.readObject();
			rand10_12 = (GameJLayeredPane) stream.readObject();
			rand10_13 = (GameJLayeredPane) stream.readObject();
			rand10_14 = (GameJLayeredPane) stream.readObject();
			rand10_15 = (GameJLayeredPane) stream.readObject();
			rand10_16 = (GameJLayeredPane) stream.readObject();							
			rand11_1 = (GameJLayeredPane) stream.readObject();
			rand11_2 = (GameJLayeredPane) stream.readObject();
			rand11_3 = (GameJLayeredPane) stream.readObject();
			rand11_4 = (GameJLayeredPane) stream.readObject();
			rand11_5 = (GameJLayeredPane) stream.readObject();
			rand11_6 = (GameJLayeredPane) stream.readObject();
			rand11_7 = (GameJLayeredPane) stream.readObject();
			rand11_8 = (GameJLayeredPane) stream.readObject();
			rand11_9 = (GameJLayeredPane) stream.readObject();
			rand11_10 = (GameJLayeredPane) stream.readObject();
			rand11_11 = (GameJLayeredPane) stream.readObject();
			rand11_12 = (GameJLayeredPane) stream.readObject();
			rand11_13 = (GameJLayeredPane) stream.readObject();
			rand11_14 = (GameJLayeredPane) stream.readObject();
			rand11_15 = (GameJLayeredPane) stream.readObject();
			rand11_16 = (GameJLayeredPane) stream.readObject();							
			rand12_1 = (GameJLayeredPane) stream.readObject();
			rand12_2 = (GameJLayeredPane) stream.readObject();
			rand12_3 = (GameJLayeredPane) stream.readObject();
			rand12_4 = (GameJLayeredPane) stream.readObject();
			rand12_5 = (GameJLayeredPane) stream.readObject();
			rand12_6 = (GameJLayeredPane) stream.readObject();
			rand12_7 = (GameJLayeredPane) stream.readObject();
			rand12_8 = (GameJLayeredPane) stream.readObject();
			rand12_9 = (GameJLayeredPane) stream.readObject();
			rand12_10 = (GameJLayeredPane) stream.readObject();
			rand12_11 = (GameJLayeredPane) stream.readObject();
			rand12_12 = (GameJLayeredPane) stream.readObject();
			rand12_13 = (GameJLayeredPane) stream.readObject();
			rand12_14 = (GameJLayeredPane) stream.readObject();
			rand12_15 = (GameJLayeredPane) stream.readObject();
			rand12_16 = (GameJLayeredPane) stream.readObject();							
			rand13_1 = (GameJLayeredPane) stream.readObject();
			rand13_2 = (GameJLayeredPane) stream.readObject();
			rand13_3 = (GameJLayeredPane) stream.readObject();
			rand13_4 = (GameJLayeredPane) stream.readObject();
			rand13_5 = (GameJLayeredPane) stream.readObject();
			rand13_6 = (GameJLayeredPane) stream.readObject();
			rand13_7 = (GameJLayeredPane) stream.readObject();
			rand13_8 = (GameJLayeredPane) stream.readObject();
			rand13_9 = (GameJLayeredPane) stream.readObject();
			rand13_10 = (GameJLayeredPane) stream.readObject();
			rand13_11 = (GameJLayeredPane) stream.readObject();
			rand13_12 = (GameJLayeredPane) stream.readObject();
			rand13_13 = (GameJLayeredPane) stream.readObject();
			rand13_14 = (GameJLayeredPane) stream.readObject();
			rand13_15 = (GameJLayeredPane) stream.readObject();
			rand13_16 = (GameJLayeredPane) stream.readObject();							
			rand14_1 = (GameJLayeredPane) stream.readObject();
			rand14_2 = (GameJLayeredPane) stream.readObject();
			rand14_3 = (GameJLayeredPane) stream.readObject();
			rand14_4 = (GameJLayeredPane) stream.readObject();
			rand14_5 = (GameJLayeredPane) stream.readObject();
			rand14_6 = (GameJLayeredPane) stream.readObject();
			rand14_7 = (GameJLayeredPane) stream.readObject();
			rand14_8 = (GameJLayeredPane) stream.readObject();
			rand14_9 = (GameJLayeredPane) stream.readObject();
			rand14_10 = (GameJLayeredPane) stream.readObject();
			rand14_11 = (GameJLayeredPane) stream.readObject();
			rand14_12 = (GameJLayeredPane) stream.readObject();
			rand14_13 = (GameJLayeredPane) stream.readObject();
			rand14_14 = (GameJLayeredPane) stream.readObject();
			rand14_15 = (GameJLayeredPane) stream.readObject();
			rand14_16 = (GameJLayeredPane) stream.readObject();
			market = (GameJLayeredPane) stream.readObject();
			infLevelHolder = (GameJLayeredPane) stream.readObject();
			mntLevelHolder = (GameJLayeredPane) stream.readObject();
			artLevelHolder = (GameJLayeredPane) stream.readObject();
			acftLevelHolder = (GameJLayeredPane) stream.readObject();
			playerSettler = (Settler) stream.readObject();
			aiSettler = (Settler) stream.readObject();
			playerArmy1 = (Army) stream.readObject();
			aiArmy1 = (Army) stream.readObject();
			playerArmy2 = (Army) stream.readObject();
			others = (GameJLayeredPane) stream.readObject();
			mainBoard = (GameJLayeredPane) stream.readObject();
			mainBrd2 = (GameJLayeredPane) stream.readObject();
			showAvailableMarket = (JTextArea) stream.readObject();
			showAvailableBarrack = (JTextArea) stream.readObject();
			showAvailableGranary = (JTextArea) stream.readObject();
			showAvailableHarbor = (JTextArea) stream.readObject();
			showAvailableLibrary = (JTextArea) stream.readObject();
			showAvailableTemple = (JTextArea) stream.readObject();
			showAvailableTradepost = (JTextArea) stream.readObject();
			showAvailableWorkshop = (JTextArea) stream.readObject();
			showAvailableAcademy = (JTextArea) stream.readObject();
			showAvailableAqueduct = (JTextArea) stream.readObject();
			showAvailableUniversity = (JTextArea) stream.readObject();
			showAvailableBank = (JTextArea) stream.readObject();
			showAvailableCathedral = (JTextArea) stream.readObject();
			showAvailableIronmine = (JTextArea) stream.readObject();
			marketIron = (JTextArea) stream.readObject();
			marketIncense = (JTextArea) stream.readObject();
			marketSilk = (JTextArea) stream.readObject();
			marketWheat = (JTextArea) stream.readObject();
			playerBrd = (GameJLabel) stream.readObject();
			mainBrd = (GameJLabel) stream.readObject();
			fog1 = (GameJLabel) stream.readObject();
			fog2 = (GameJLabel) stream.readObject();
			fog3 = (GameJLabel) stream.readObject();
			fog4 = (GameJLabel) stream.readObject();
			fog5 = (GameJLabel) stream.readObject();
			fog6 = (GameJLabel) stream.readObject();
			c = (GameJFrame)stream.readObject();
			map = (GameJPanel)stream.readObject();
			lb = (LogicBoard)stream.readObject();
			boardPosition = (String[])stream.readObject();
			wonderCards = (GameJLabel[])stream.readObject();
			wonderBuildings = (GameJLabel[])stream.readObject();
			otherBoards = (GameJButton[])stream.readObject();
			playerName = (String[])stream.readObject();
         gameID = stream.readUTF();
      }catch(IOException ioEx){
			System.out.println("IO EXCEPTION readObject\n");
			ioEx.printStackTrace();
		}catch(NullPointerException NEx){
			System.out.println("NullPointerExcepyion readObject\n");
			NEx.printStackTrace();
		}
   }

   private void writeObject(ObjectOutputStream stream) throws IOException{
		try{
			stream.writeObject(clientGUI);
			stream.writeInt(maxWheat);
			stream.writeInt(maxIron);
			stream.writeInt(maxIncense);
			stream.writeInt(maxSilk);
			stream.writeInt(numIron);
			stream.writeInt(numSilk);
			stream.writeInt(numWheat);
			stream.writeInt(numIncense);
			stream.writeInt(startCount);
			stream.writeInt(tradeCount);
			stream.writeInt(cityCount);
			stream.writeInt(moveCount);
			stream.writeInt(researchCount);
			stream.writeInt(availableMarket);
			stream.writeInt(availableBarrack);
			stream.writeInt(availableGranary);
			stream.writeInt(availableHarbor);
			stream.writeInt(availableLibrary);
			stream.writeInt(availableTemple);
			stream.writeInt(availableTradepost);
			stream.writeInt(availableWorkshop);
			stream.writeInt(availableAcademy);
			stream.writeInt(availableAqueduct);
			stream.writeInt(availableUniversity);
			stream.writeInt(availableBank);
			stream.writeInt(availableCathedral);
			stream.writeInt(availableIronmine);
			stream.writeInt(playerNumber);
			stream.writeInt(gameType);
			stream.writeObject(players);
			stream.writeObject(cultureTrack);
			stream.writeBoolean(gameEnd);
			stream.writeUTF(playerChoice);
			stream.writeUTF(aiChoice);
			stream.writeUTF(playerColorChoice);
			stream.writeUTF(aiColorChoice);
			stream.writeUTF(userName);
			stream.writeObject(turn);
			stream.writeObject(combatDeck);
			stream.writeObject(wonderDeck);
			stream.writeObject(culture1Deck);
			stream.writeObject(culture2Deck);
			stream.writeObject(culture3Deck);
			stream.writeObject(myWonderDeck);
			stream.writeObject(myCombatDeck);
			stream.writeObject(myCulture1Deck);
			stream.writeObject(myCulture2Deck);
			stream.writeObject(myCulture3Deck);
			stream.writeObject(villages);
			stream.writeObject(huts);
			stream.writeObject(greatPeople);
			stream.writeObject(ready);
			stream.writeObject(panel0);
			stream.writeObject(panel1);
			stream.writeObject(panel2);
			stream.writeObject(panel3);
			stream.writeObject(panel4);
			stream.writeObject(panel5);
			stream.writeObject(panel6);
			stream.writeObject(panel7);
			stream.writeObject(mypanel0);
			stream.writeObject(mypanel1);
			stream.writeObject(mypanel2);
			stream.writeObject(mypanel3);
			stream.writeObject(mypanel4);
			stream.writeObject(mypanel5);
			stream.writeObject(mypanel6);
			stream.writeObject(mypanel7);
			stream.writeObject(myfog1);
			stream.writeObject(myfog2);
			stream.writeObject(myfog3);
			stream.writeObject(myfog4);
			stream.writeObject(myfog5);
			stream.writeObject(myfog6);						
			stream.writeObject(am1);
			stream.writeObject(am2);
			stream.writeObject(am3);
			stream.writeObject(am4);
			stream.writeObject(am5);
			stream.writeObject(am6);
			stream.writeObject(am7);
			stream.writeObject(am8);
			stream.writeObject(am9);
			stream.writeObject(am10);
			stream.writeObject(am11);
			stream.writeObject(am12);
			stream.writeObject(am13);
			stream.writeObject(am14);
			stream.writeObject(am15);
			stream.writeObject(am16);
			stream.writeObject(ch1);
			stream.writeObject(ch2);
			stream.writeObject(ch3);
			stream.writeObject(ch4);
			stream.writeObject(ch5);
			stream.writeObject(ch6);
			stream.writeObject(ch7);
			stream.writeObject(ch8);
			stream.writeObject(ch9);
			stream.writeObject(ch10);
			stream.writeObject(ch11);
			stream.writeObject(ch12);
			stream.writeObject(ch13);
			stream.writeObject(ch14);
			stream.writeObject(ch15);
			stream.writeObject(ch16);		
			stream.writeObject(egy1);
			stream.writeObject(egy2);
			stream.writeObject(egy3);
			stream.writeObject(egy4);
			stream.writeObject(egy5);
			stream.writeObject(egy6);
			stream.writeObject(egy7);
			stream.writeObject(egy8);
			stream.writeObject(egy9);
			stream.writeObject(egy10);
			stream.writeObject(egy11);
			stream.writeObject(egy12);
			stream.writeObject(egy13);
			stream.writeObject(egy14);
			stream.writeObject(egy15);
			stream.writeObject(egy16);
			stream.writeObject(ger1);
			stream.writeObject(ger2);
			stream.writeObject(ger3);
			stream.writeObject(ger4);
			stream.writeObject(ger5);
			stream.writeObject(ger6);
			stream.writeObject(ger7);
			stream.writeObject(ger8);
			stream.writeObject(ger9);
			stream.writeObject(ger10);
			stream.writeObject(ger11);
			stream.writeObject(ger12);
			stream.writeObject(ger13);
			stream.writeObject(ger14);
			stream.writeObject(ger15);
			stream.writeObject(ger16);
			stream.writeObject(ro1);
			stream.writeObject(ro2);
			stream.writeObject(ro3);
			stream.writeObject(ro4);
			stream.writeObject(ro5);
			stream.writeObject(ro6);
			stream.writeObject(ro7);
			stream.writeObject(ro8);
			stream.writeObject(ro9);
			stream.writeObject(ro10);
			stream.writeObject(ro11);
			stream.writeObject(ro12);
			stream.writeObject(ro13);
			stream.writeObject(ro14);
			stream.writeObject(ro15);
			stream.writeObject(ro16);
			stream.writeObject(russ1);
			stream.writeObject(russ2);
			stream.writeObject(russ3);
			stream.writeObject(russ4);
			stream.writeObject(russ5);
			stream.writeObject(russ6);
			stream.writeObject(russ7);
			stream.writeObject(russ8);
			stream.writeObject(russ9);
			stream.writeObject(russ10);
			stream.writeObject(russ11);
			stream.writeObject(russ12);
			stream.writeObject(russ13);
			stream.writeObject(russ14);
			stream.writeObject(russ15);
			stream.writeObject(russ16);			
			stream.writeObject(rand1_1);
			stream.writeObject(rand1_2);
			stream.writeObject(rand1_3);
			stream.writeObject(rand1_4);
			stream.writeObject(rand1_5);
			stream.writeObject(rand1_6);
			stream.writeObject(rand1_7);
			stream.writeObject(rand1_8);
			stream.writeObject(rand1_9);
			stream.writeObject(rand1_10);
			stream.writeObject(rand1_11);
			stream.writeObject(rand1_12);
			stream.writeObject(rand1_13);
			stream.writeObject(rand1_14);
			stream.writeObject(rand1_15);
			stream.writeObject(rand1_16);
			stream.writeObject(rand2_1);
			stream.writeObject(rand2_2);
			stream.writeObject(rand2_3);
			stream.writeObject(rand2_4);
			stream.writeObject(rand2_5);
			stream.writeObject(rand2_6);
			stream.writeObject(rand2_7);
			stream.writeObject(rand2_8);
			stream.writeObject(rand2_9);
			stream.writeObject(rand2_10);
			stream.writeObject(rand2_11);
			stream.writeObject(rand2_12);
			stream.writeObject(rand2_13);
			stream.writeObject(rand2_14);
			stream.writeObject(rand2_15);
			stream.writeObject(rand2_16);
			stream.writeObject(rand3_1);
			stream.writeObject(rand3_2);
			stream.writeObject(rand3_3);
			stream.writeObject(rand3_4);
			stream.writeObject(rand3_5);
			stream.writeObject(rand3_6);
			stream.writeObject(rand3_7);
			stream.writeObject(rand3_8);
			stream.writeObject(rand3_9);
			stream.writeObject(rand3_10);
			stream.writeObject(rand3_11);
			stream.writeObject(rand3_12);
			stream.writeObject(rand3_13);
			stream.writeObject(rand3_14);
			stream.writeObject(rand3_15);
			stream.writeObject(rand3_16);
			stream.writeObject(rand4_1);
			stream.writeObject(rand4_2);
			stream.writeObject(rand4_3);
			stream.writeObject(rand4_4);
			stream.writeObject(rand4_5);
			stream.writeObject(rand4_6);
			stream.writeObject(rand4_7);
			stream.writeObject(rand4_8);
			stream.writeObject(rand4_9);
			stream.writeObject(rand4_10);
			stream.writeObject(rand4_11);
			stream.writeObject(rand4_12);
			stream.writeObject(rand4_13);
			stream.writeObject(rand4_14);
			stream.writeObject(rand4_15);
			stream.writeObject(rand4_16);
			stream.writeObject(rand5_1);
			stream.writeObject(rand5_2);
			stream.writeObject(rand5_3);
			stream.writeObject(rand5_4);
			stream.writeObject(rand5_5);
			stream.writeObject(rand5_6);
			stream.writeObject(rand5_7);
			stream.writeObject(rand5_8);
			stream.writeObject(rand5_9);
			stream.writeObject(rand5_10);
			stream.writeObject(rand5_11);
			stream.writeObject(rand5_12);
			stream.writeObject(rand5_13);
			stream.writeObject(rand5_14);
			stream.writeObject(rand5_15);
			stream.writeObject(rand5_16);
			stream.writeObject(rand6_1);
			stream.writeObject(rand6_2);
			stream.writeObject(rand6_3);
			stream.writeObject(rand6_4);
			stream.writeObject(rand6_5);
			stream.writeObject(rand6_6);
			stream.writeObject(rand6_7);
			stream.writeObject(rand6_8);
			stream.writeObject(rand6_9);
			stream.writeObject(rand6_10);
			stream.writeObject(rand6_11);
			stream.writeObject(rand6_12);
			stream.writeObject(rand6_13);
			stream.writeObject(rand6_14);
			stream.writeObject(rand6_15);
			stream.writeObject(rand6_16);
			stream.writeObject(rand7_1);
			stream.writeObject(rand7_2);
			stream.writeObject(rand7_3);
			stream.writeObject(rand7_4);
			stream.writeObject(rand7_5);
			stream.writeObject(rand7_6);
			stream.writeObject(rand7_7);
			stream.writeObject(rand7_8);
			stream.writeObject(rand7_9);
			stream.writeObject(rand7_10);
			stream.writeObject(rand7_11);
			stream.writeObject(rand7_12);
			stream.writeObject(rand7_13);
			stream.writeObject(rand7_14);
			stream.writeObject(rand7_15);
			stream.writeObject(rand7_16);
			stream.writeObject(rand8_1);
			stream.writeObject(rand8_2);
			stream.writeObject(rand8_3);
			stream.writeObject(rand8_4);
			stream.writeObject(rand8_5);
			stream.writeObject(rand8_6);
			stream.writeObject(rand8_7);
			stream.writeObject(rand8_8);
			stream.writeObject(rand8_9);
			stream.writeObject(rand8_10);
			stream.writeObject(rand8_11);
			stream.writeObject(rand8_12);
			stream.writeObject(rand8_13);
			stream.writeObject(rand8_14);
			stream.writeObject(rand8_15);
			stream.writeObject(rand8_16);
			stream.writeObject(rand9_1);
			stream.writeObject(rand9_2);
			stream.writeObject(rand9_3);
			stream.writeObject(rand9_4);
			stream.writeObject(rand9_5);
			stream.writeObject(rand9_6);
			stream.writeObject(rand9_7);
			stream.writeObject(rand9_8);
			stream.writeObject(rand9_9);
			stream.writeObject(rand9_10);
			stream.writeObject(rand9_11);
			stream.writeObject(rand9_12);
			stream.writeObject(rand9_13);
			stream.writeObject(rand9_14);
			stream.writeObject(rand9_15);
			stream.writeObject(rand9_16);
			stream.writeObject(rand10_1);
			stream.writeObject(rand10_2);
			stream.writeObject(rand10_3);
			stream.writeObject(rand10_4);
			stream.writeObject(rand10_5);
			stream.writeObject(rand10_6);
			stream.writeObject(rand10_7);
			stream.writeObject(rand10_8);
			stream.writeObject(rand10_9);
			stream.writeObject(rand10_10);
			stream.writeObject(rand10_11);
			stream.writeObject(rand10_12);
			stream.writeObject(rand10_13);
			stream.writeObject(rand10_14);
			stream.writeObject(rand10_15);
			stream.writeObject(rand10_16);
			stream.writeObject(rand11_1);
			stream.writeObject(rand11_2);
			stream.writeObject(rand11_3);
			stream.writeObject(rand11_4);
			stream.writeObject(rand11_5);
			stream.writeObject(rand11_6);
			stream.writeObject(rand11_7);
			stream.writeObject(rand11_8);
			stream.writeObject(rand11_9);
			stream.writeObject(rand11_10);
			stream.writeObject(rand11_11);
			stream.writeObject(rand11_12);
			stream.writeObject(rand11_13);
			stream.writeObject(rand11_14);
			stream.writeObject(rand11_15);
			stream.writeObject(rand11_16);
			stream.writeObject(rand12_1);
			stream.writeObject(rand12_2);
			stream.writeObject(rand12_3);
			stream.writeObject(rand12_4);
			stream.writeObject(rand12_5);
			stream.writeObject(rand12_6);
			stream.writeObject(rand12_7);
			stream.writeObject(rand12_8);
			stream.writeObject(rand12_9);
			stream.writeObject(rand12_10);
			stream.writeObject(rand12_11);
			stream.writeObject(rand12_12);
			stream.writeObject(rand12_13);
			stream.writeObject(rand12_14);
			stream.writeObject(rand12_15);
			stream.writeObject(rand12_16);
			stream.writeObject(rand13_1);
			stream.writeObject(rand13_2);
			stream.writeObject(rand13_3);
			stream.writeObject(rand13_4);
			stream.writeObject(rand13_5);
			stream.writeObject(rand13_6);
			stream.writeObject(rand13_7);
			stream.writeObject(rand13_8);
			stream.writeObject(rand13_9);
			stream.writeObject(rand13_10);
			stream.writeObject(rand13_11);
			stream.writeObject(rand13_12);
			stream.writeObject(rand13_13);
			stream.writeObject(rand13_14);
			stream.writeObject(rand13_15);
			stream.writeObject(rand13_16);
			stream.writeObject(rand14_1);
			stream.writeObject(rand14_2);
			stream.writeObject(rand14_3);
			stream.writeObject(rand14_4);
			stream.writeObject(rand14_5);
			stream.writeObject(rand14_6);
			stream.writeObject(rand14_7);
			stream.writeObject(rand14_8);
			stream.writeObject(rand14_9);
			stream.writeObject(rand14_10);
			stream.writeObject(rand14_11);
			stream.writeObject(rand14_12);
			stream.writeObject(rand14_13);
			stream.writeObject(rand14_14);
			stream.writeObject(rand14_15);
			stream.writeObject(rand14_16);
			stream.writeObject(market);
			stream.writeObject(infLevelHolder);
			stream.writeObject(mntLevelHolder);
			stream.writeObject(artLevelHolder);
			stream.writeObject(acftLevelHolder);
			stream.writeObject(playerSettler);
			stream.writeObject(aiSettler);
			stream.writeObject(playerArmy1);
			stream.writeObject(aiArmy1);
			stream.writeObject(playerArmy2);
			stream.writeObject(others);
			stream.writeObject(mainBoard);
			stream.writeObject(mainBrd2);
			stream.writeObject(showAvailableMarket);
			stream.writeObject(showAvailableBarrack);
			stream.writeObject(showAvailableGranary);
			stream.writeObject(showAvailableHarbor);
			stream.writeObject(showAvailableLibrary);
			stream.writeObject(showAvailableTemple);
			stream.writeObject(showAvailableTradepost);
			stream.writeObject(showAvailableWorkshop);
			stream.writeObject(showAvailableAcademy);
			stream.writeObject(showAvailableAqueduct);
			stream.writeObject(showAvailableUniversity);
			stream.writeObject(showAvailableBank);
			stream.writeObject(showAvailableCathedral);
			stream.writeObject(showAvailableIronmine);
			stream.writeObject(marketIron);
			stream.writeObject(marketIncense);
			stream.writeObject(marketSilk);
			stream.writeObject(marketWheat);
			stream.writeObject(playerBrd);
			stream.writeObject(mainBrd);
			stream.writeObject(fog1);
			stream.writeObject(fog2);
			stream.writeObject(fog3);
			stream.writeObject(fog4);
			stream.writeObject(fog5);
			stream.writeObject(fog6);
			stream.writeObject(c);
			stream.writeObject(map);
			stream.writeObject(lb);
			stream.writeObject(boardPosition);
			stream.writeObject(wonderCards);
			stream.writeObject(wonderBuildings);
			stream.writeObject(otherBoards);
			stream.writeObject(playerName);
         stream.writeUTF(gameID);
		}
		catch(NotSerializableException NSEx){
			System.out.println("NOT SERIALIZABLE\n");
			NSEx.printStackTrace();
		}
		catch(IOException ioEx){
			System.out.println("IO EXCEPTION sendGameCommand\n");
			ioEx.printStackTrace();
		}
	
	}
 
   private void readObjectNoData() throws ObjectStreamException{}

   public void updateGUI(CivGUI game){

       for(Object readyPlayer : game.ready){
		    Player player = (Player)readyPlayer;  
			 if(!ready.contains(player)){
				 ready.add(player.playerUI.playerNumber, player);		  
             players[player.playerUI.playerNumber] = game.players[player.playerUI.playerNumber];
System.out.println("UPDATE READY "+player.playerUI.playerNumber+" "+player.nation);
			 }
       }
       if(game.ready.size() == players.length)//number of players
		      {
		         initGameStart(players[0]);
		         initGameStart(players[1]);
	            Player temp = (Player)ready.get(0);
	            System.out.println(temp.getNation());
	            temp = (Player)ready.get(1);
	            System.out.println(temp.getNation());
		      }
	}
}//end class
