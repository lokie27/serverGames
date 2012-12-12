package games.civ;

import java.beans.*;
import java.io.*;
import java.util.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;

import gameClient.*;
import games.*;

public class CivLaunch implements ActionListener, WindowListener, MouseListener, Serializable{ 

   private static final long serialVersionUID = 7760680371533563833L;
	
   public GameJFrame c;
   public ClientGUI clientGUI;

   public String[] playerName;
   public String thisName;   
	
	public GameJButton GO;	    
   public GameJPanel p1, p2, p3;

	public GameJLabel L1icon, L2icon, L1, L2, L3;
	public Color L1color, L2color;  

	public String playerChoice, aiChoice, playerColorChoice, aiColorChoice;
	public GameJComboBox playerList, aiList, playerColor, aiColor, gameType;
	public GameImageIcon startupIconPlayer, startupIconAi;

		public GameJFrame getC() {
			return c;
		}
		public void setC(GameJFrame c) {
			this.c = c;
		}
		public ClientGUI getClientGUI() {
			return clientGUI;
		}
		public void setClientGUI(ClientGUI clientGUI) {
			this.clientGUI = clientGUI;
		}
		public String[] getPlayerName() {
			return playerName;
		}
		public void setPlayerName(String[] playerName) {
			this.playerName = playerName;
		}
		public String getThisName() {
			return thisName;
		}
		public void setThisName(String thisName) {
			this.thisName = thisName;
		}
		public GameJButton getGO() {
			return GO;
		}
		public void setGO(GameJButton gO) {
			GO = gO;
		}
		public GameJPanel getP1() {
			return p1;
		}
		public void setP1(GameJPanel p1) {
			this.p1 = p1;
		}
		public GameJPanel getP2() {
			return p2;
		}
		public void setP2(GameJPanel p2) {
			this.p2 = p2;
		}
		public GameJPanel getP3() {
			return p3;
		}
		public void setP3(GameJPanel p3) {
			this.p3 = p3;
		}
		public GameJLabel getL1icon() {
			return L1icon;
		}
		public void setL1icon(GameJLabel l1icon) {
			L1icon = l1icon;
		}
		public GameJLabel getL2icon() {
			return L2icon;
		}
		public void setL2icon(GameJLabel l2icon) {
			L2icon = l2icon;
		}
		public GameJLabel getL1() {
			return L1;
		}
		public void setL1(GameJLabel l1) {
			L1 = l1;
		}
		public GameJLabel getL2() {
			return L2;
		}
		public void setL2(GameJLabel l2) {
			L2 = l2;
		}
		public GameJLabel getL3() {
			return L3;
		}
		public void setL3(GameJLabel l3) {
			L3 = l3;
		}
		public Color getL1color() {
			return L1color;
		}
		public void setL1color(Color l1color) {
			L1color = l1color;
		}
		public Color getL2color() {
			return L2color;
		}
		public void setL2color(Color l2color) {
			L2color = l2color;
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
		public GameJComboBox getPlayerList() {
			return playerList;
		}
		public void setPlayerList(GameJComboBox playerList) {
			this.playerList = playerList;
		}
		public GameJComboBox getAiList() {
			return aiList;
		}
		public void setAiList(GameJComboBox aiList) {
			this.aiList = aiList;
		}
		public GameJComboBox getPlayerColor() {
			return playerColor;
		}
		public void setPlayerColor(GameJComboBox playerColor) {
			this.playerColor = playerColor;
		}
		public GameJComboBox getAiColor() {
			return aiColor;
		}
		public void setAiColor(GameJComboBox aiColor) {
			this.aiColor = aiColor;
		}
		public GameJComboBox getGameType() {
			return gameType;
		}
		public void setGameType(GameJComboBox gameType) {
			this.gameType = gameType;
		}
		public GameImageIcon getStartupIconPlayer() {
			return startupIconPlayer;
		}
		public void setStartupIconPlayer(GameImageIcon startupIconPlayer) {
			this.startupIconPlayer = startupIconPlayer;
		}
		public GameImageIcon getStartupIconAi() {
			return startupIconAi;
		}
		public void setStartupIconAi(GameImageIcon startupIconAi) {
			this.startupIconAi = startupIconAi;
		}

	public void mouseEntered(MouseEvent e)	{}//end mouseEntered
	public void mousePressed(MouseEvent e){}//end mousePressed
	public void mouseReleased(MouseEvent e){}//end mouseReleased
	public void mouseExited(MouseEvent e){}//end mouseExited

	public void windowDeactivated(WindowEvent e){}//end mouseExited
	public void windowActivated(WindowEvent e){}//end mouseExited
	public void windowDeiconified(WindowEvent e){}//end mouseExited
	public void windowIconified(WindowEvent e){}//end mouseExited
	public void windowClosed(WindowEvent e){
      clientGUI.removeCivGames(this);
	}//end mouseExited
	public void windowClosing(WindowEvent e){
//      clientGUI.leaveCivGames(this);
	}//end mouseExited
	public void windowOpened(WindowEvent e){}//end mouseExited

	public void actionPerformed(ActionEvent e)
	{
	 	if(e.getSource() == GO)
  	   {
//         playTrack("welcome");      
			if(playerList.getSelectedItem().toString().equals(aiList.getSelectedItem().toString()))      
			{
			  getError("civ");
         }      
         else if(playerColor.getSelectedItem().toString().equals(aiColor.getSelectedItem().toString()))
			{
			  getError("color");
			}
			else if(gameType.getSelectedIndex() == 0){
System.out.println("Network");
			  playerChoice = playerList.getSelectedItem().toString();
System.out.println(playerChoice);
           playerColorChoice = playerColor.getSelectedItem().toString();      
System.out.println(playerColorChoice);
           aiChoice = aiList.getSelectedItem().toString();
System.out.println(aiChoice);
           aiColorChoice = aiColor.getSelectedItem().toString();
System.out.println(aiColorChoice);

           c.dispose();      
			  clientGUI.goCivGame(this);  
 		   }
			else if(gameType.getSelectedIndex() == 1){
System.out.println("Single Player");
			  playerChoice = playerList.getSelectedItem().toString();
           aiChoice = aiList.getSelectedItem().toString();
           playerColorChoice = playerColor.getSelectedItem().toString();      
           aiColorChoice = aiColor.getSelectedItem().toString();
           String start = aiChoice+"."+playerChoice;
         }
		}
	   else if(e.getSource() == playerList)  
      {
         if(playerList.getSelectedItem().toString().equals("Germany"))
			{
   			startupIconPlayer = getImage("data/germanyBack.png");
            playerChoice = "germany";
			}
			else if(playerList.getSelectedItem().toString().equals("Russia"))
         {
   			startupIconPlayer = getImage("data/russiaBack.png");
            playerChoice = "russia";
			}   
			else if(playerList.getSelectedItem().toString().equals("America"))
         {
   			startupIconPlayer = getImage("data/americaBack.png");
            playerChoice = "america";
			}   
			else if(playerList.getSelectedItem().toString().equals("China"))
         {
   			startupIconPlayer = getImage("data/chinaBack.png");
            playerChoice = "china";
			}   
			else if(playerList.getSelectedItem().toString().equals("Rome"))
         {
   			startupIconPlayer = getImage("data/romeBack.png");
            playerChoice = "rome";
			}   
			else if(playerList.getSelectedItem().toString().equals("Egypt"))
         {
   			startupIconPlayer = getImage("data/egyptBack.png");
            playerChoice = "egypt";
			}   
  			L1icon.setIcon(startupIconPlayer);
	      updateThreads();
		}
		else if(e.getSource() == aiList)
      {
         if(aiList.getSelectedItem().toString().equals("Germany"))
			{
				startupIconAi = getImage("data/germanyBack.png");
            aiChoice = "germany";
			}
			else if(aiList.getSelectedItem().toString().equals("Russia"))
         {
   			startupIconAi = getImage("data/russiaBack.png");
            aiChoice = "russia";
			}   
			else if(aiList.getSelectedItem().toString().equals("America"))
         {
   			startupIconAi = getImage("data/americaBack.png");
            aiChoice = "america";
			}   
			else if(aiList.getSelectedItem().toString().equals("China"))
         {
   			startupIconAi = getImage("data/chinaBack.png");
            aiChoice = "china";
			}   
			else if(aiList.getSelectedItem().toString().equals("Rome"))
         {
   			startupIconAi = getImage("data/romeBack.png");
            aiChoice = "rome";
			}   
			else if(aiList.getSelectedItem().toString().equals("Egypt"))
         {
   			startupIconAi = getImage("data/egyptBack.png");
            aiChoice = "egypt";
			}
	      L2icon.setIcon(startupIconAi);  
	      updateThreads();
		}
      else if(e.getSource() == playerColor)
		{
		  if(playerColor.getSelectedItem().toString().equals("Red"))
		  {
		     L1.setForeground(new Color(255, 255, 255));  
		     L1color = new Color(255, 0, 0);
		  }
		  else if(playerColor.getSelectedItem().toString().equals("Blue"))
		  {
		     L1.setForeground(new Color(255, 255, 255));  
		     L1color = new Color(0, 0, 255);
		  }
		  else if(playerColor.getSelectedItem().toString().equals("Yellow"))
		  {
		     L1.setForeground(new Color(0, 0, 0));  
		     L1color = new Color(255, 255, 0);
		  }
		  else if(playerColor.getSelectedItem().toString().equals("Green"))
		  {
		     L1.setForeground(new Color(0, 0, 0));  
		     L1color = new Color(0, 255, 0);
		  }
		  p1.setBackground(L1color);
	     updateThreads();
		}
      else if(e.getSource() == aiColor)
		{
		  if(aiColor.getSelectedItem().toString().equals("Red"))
		  {
		     L2.setForeground(new Color(255, 255, 255));  
		     L2color = new Color(255, 0, 0);
		  }
		  else if(aiColor.getSelectedItem().toString().equals("Blue"))
		  {
		     L2.setForeground(new Color(255, 255, 255));  
		     L2color = new Color(0, 0, 255);
		  }
		  else if(aiColor.getSelectedItem().toString().equals("Yellow"))
		  {
		     L2.setForeground(new Color(0, 0, 0));  
			  L2color = new Color(255, 255, 0);
		  }
		  else if(aiColor.getSelectedItem().toString().equals("Green"))
		  {
		     L2.setForeground(new Color(0, 0, 0));  
		     L2color = new Color(0, 255, 0);
		  }
		  p2.setBackground(L2color);
	     updateThreads();
		}
	}//end actionPerformed(e)
	
	public CivLaunch(){}

	public CivLaunch(String playerNames, ClientGUI cGUI)
	{
		playerName = new String[2];
      playerName[0] = playerNames;		
      playerName[1] = "";
      thisName = playerNames;
				
	 	c = new GameJFrame("Civ: "+thisName);	
      clientGUI = cGUI;
		
		p1 = new GameJPanel(new Dimension(450, 250));
      
		p2 = new GameJPanel(new Dimension(450, 250));

		p3 = new GameJPanel(new Dimension(450, 50));

      String[] nationStrings = {"America", "China", "Egypt", "Germany", "Rome", "Russia"};
      String[] colorStrings = {"Red", "Yellow", "Green", "Blue"};
      String[] gameTypeStrings = {"Network"};//"Hot Seat", "Single Player"};

      gameType = new GameJComboBox(gameTypeStrings);
      gameType.setSelectedIndex(0);
      gameType.addActionListener(this);

      playerList = new GameJComboBox(nationStrings);
      playerList.setSelectedIndex(0);
      playerList.addActionListener(this);

      playerColor = new GameJComboBox(colorStrings);
      playerColor.setSelectedIndex(0);
      playerColor.addActionListener(this);
      playerColorChoice = "null";

	   GO = new GameJButton("Play");
	   GO.addActionListener(this);
      GO.setEnabled(false);
      L3 = new GameJLabel("GAME TYPE");
      p3.add(L3);
		p3.add(gameType);
      p3.add(GO);

      startupIconPlayer = getImage("data/americaBack.png");
		L1 = new GameJLabel(playerName[0]);
      playerChoice = "america";
    //should be default to America and ythen change per selection with eventlistener
      L1icon = new GameJLabel();
		L1icon.setIcon(startupIconPlayer);
      L1color = new Color(255, 0, 0); 
		p1.add(L1);
      p1.add(playerList);
      p1.add(L1icon);    
		p1.add(playerColor);
		p1.setBackground(L1color);
		L1.setForeground(new Color(255, 255, 255));

      aiList = new GameJComboBox(nationStrings);
      aiList.setSelectedIndex(0);

      aiColor = new GameJComboBox(colorStrings);
      aiColor.setSelectedIndex(3);
		aiColorChoice = "null";

      startupIconAi = getImage("data/americaBack.png");
      aiChoice = "america";
      L2 = new GameJLabel("Waiting for another player");
      L2color = new Color(0, 0, 0); 
      p2.add(L2); 
      p2.setBackground(L2color);
		L2.setForeground(new Color(255, 255, 255));

		c.add(p1, "North");
      c.add(p2, "South");
      c.add(p3, "Center"); 

		c.setResizable(false);
      c.setVisible(true);
		c.setDefaultCloseOperation(GameJFrame.DISPOSE_ON_CLOSE);
      c.addWindowListener(this);
		c.pack();
  
//      playTrack("welcome");      
   }//end CivGUI()


	public CivLaunch(String inName, ClientGUI cGUI, CivLaunch inGame){
      thisName = inName;
	 	c = new GameJFrame("Civ: "+thisName);	
      clientGUI = cGUI;

	   deepClone(inGame);
		updateThreads();
	}

   public void addPlayerToGame(String playerNames, ClientGUI cGUI, CivLaunch inGame){
      playerName[1] = playerNames;		
      thisName = playerNames;
		
	 	c = new GameJFrame("Civ: "+thisName);	
      clientGUI = cGUI;

		p1 = new GameJPanel(new Dimension(450, 250));
		
		p2 = new GameJPanel(new Dimension(450, 250));

		p3 = new GameJPanel(new Dimension(450, 50));

      String[] nationStrings = {"America", "China", "Egypt", "Germany", "Rome", "Russia"};
      String[] colorStrings = {"Red", "Yellow", "Green", "Blue"};
      String[] gameTypeStrings = {"Network"};//"Hot Seat", "Single Player"};

      gameType = new GameJComboBox(gameTypeStrings);
      gameType.setSelectedIndex(0);
      gameType.addActionListener(this);

      playerList = new GameJComboBox(nationStrings);
      playerList.setSelectedIndex(0);
      playerList.addActionListener(this);

      playerColor = new GameJComboBox(colorStrings);
      playerColor.setSelectedIndex(0);
      playerColor.addActionListener(this);

      aiList = new GameJComboBox(nationStrings);
      aiList.setSelectedIndex(0);
      aiList.addActionListener(this);

      aiColor = new GameJComboBox(colorStrings);
      aiColor.setSelectedIndex(3);
      aiColor.addActionListener(this);

	   GO = new GameJButton("Play");
	   GO.addActionListener(this);
      GO.setEnabled(false);
      L3 = new GameJLabel("GAME TYPE");
      p3.add(L3);
		p3.add(gameType);
      p3.add(GO);

      playerChoice = inGame.getPlayerChoice();
      startupIconPlayer = getImage("data/"+playerChoice+"Back.png");
		L1 = new GameJLabel(playerName[0]);
      L1icon = new GameJLabel();
		L1icon.setIcon(startupIconPlayer);
      L1color = inGame.getL1color(); 
		p1.add(L1);
      p1.add(playerList);
      p1.add(L1icon);    
		p1.add(playerColor);
		p1.setBackground(L1color);
		L1.setForeground(new Color(255, 255, 255));

      startupIconAi = getImage("data/americaBack.png");
      aiChoice = "america";
      L2 = new GameJLabel(playerName[1]);
      L2icon = new GameJLabel();
		L2icon.setIcon(startupIconAi);
      L2color = new Color(0, 0, 255); 
      p2.add(L2); 
      p2.add(aiList);
      p2.add(L2icon);    
      p2.add(aiColor);
      p2.setBackground(L2color);
		L2.setForeground(new Color(255, 255, 255));

		c.add(p1, "North");
      c.add(p2, "South");
      c.add(p3, "Center"); 

		c.setResizable(false);

      c.setVisible(true);
		c.setDefaultCloseOperation(GameJFrame.DISPOSE_ON_CLOSE);
      c.addWindowListener(this);

		c.pack();
  
//      playTrack("welcome");
  	   GO.setEnabled(true);
	   updateThreads();
	}
	 	
   private void updateThreads(){
//      removePropertyChangeListeners(p1);
//      removePropertyChangeListeners(p2);
//      removePropertyChangeListeners(p3);
	   disableGameJButtonListeners(thisName);
	   disableGameJComboBoxListeners(thisName);
		clientGUI.updateCivLaunch(this);	
	}

   public void getError(String cause){
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

	public void mouseClicked(MouseEvent e){}//end mouseClicked

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

   public void updateGUI(CivLaunch in){
      if(playerName[0].equals("")){
         playerName[0] = in.playerName[0];		
      }
      if(playerName[1].equals("")){
	      playerName[1] = in.playerName[1];		
      }

		if(!thisName.equals(in.thisName)){

         if(thisName.equals(playerName[0])){    
System.out.println("IF "+thisName);
           
            aiChoice = in.aiChoice;
				
		      aiList.setSelectedIndex(in.aiList.getSelectedIndex());
		      aiColor.setSelectedIndex(in.aiColor.getSelectedIndex());

		      p2.removeAll();
		      p2.setBackground(in.L2color);
		      p2.add(in.L2); 
		      p2.add(aiList);
            L2icon = new GameJLabel(getImage("data/"+in.aiChoice+"Back.png"));
				p2.add(L2icon);    
		      p2.add(aiColor);
				L2.setForeground(new Color(255, 255, 255));
		      p2.repaint();
		      p3.repaint();

         }else if(thisName.equals(playerName[1])){
System.out.println("ELSE IF "+thisName);

		      gameType.setSelectedIndex(in.gameType.getSelectedIndex());
		      playerList.setSelectedIndex(in.playerList.getSelectedIndex());
		      playerColor.setSelectedIndex(in.playerColor.getSelectedIndex());
            
				playerChoice = in.playerChoice; 

		      p1.removeAll();
			   p1.setBackground(in.L1color);
				p1.add(in.L1);
		      p1.add(playerList);
            L1icon = new GameJLabel(getImage("data/"+in.playerChoice+"Back.png"));
		      p1.add(L1icon);    
				p1.add(playerColor);
				L1.setForeground(new Color(255, 255, 255));
		      p1.repaint();
		      p3.repaint();
			}

		}
      c.repaint();
	   c.pack();

	   disableGameJComboBoxListeners(thisName);
	   disableGameJButtonListeners(thisName);
	   enableListeners(thisName);
   }
	
   public void deepClone(CivLaunch deepClone){
      this.playerName = deepClone.playerName;
		this.p1 = deepClone.p1;
		this.p2 = deepClone.p2;
		this.p3 = deepClone.p3;
      this.playerChoice = deepClone.playerChoice;
      this.playerList = deepClone.playerList;
      this.playerColor = deepClone.playerColor;
      this.playerColorChoice = deepClone.playerColorChoice;
      this.aiChoice = deepClone.aiChoice;
      this.aiList = deepClone.aiList;
      this.aiColor = deepClone.aiColor;
      this.aiColorChoice = deepClone.aiColorChoice;
	   this.GO = deepClone.GO;
      this.L1icon = deepClone.L1icon;
		this.L2icon = deepClone.L2icon;
		this.L1 = deepClone.L1;
      this.L3 = deepClone.L3;
      this.L2 = deepClone.L2;
      this.startupIconPlayer = deepClone.startupIconPlayer;
      this.startupIconAi = deepClone.startupIconAi;
      this.L1color = deepClone.L1color;
		this.L2color = deepClone.L2color;
		this.gameType = deepClone.gameType;
	} 	

	private void disableGameJButtonListeners(String name){
      removeGameJButtonListeners(GO);	
	}

	private void disableGameJComboBoxListeners(String name){
		removeGameJComboBoxListeners(gameType);
		removeGameJComboBoxListeners(playerList);
		removeGameJComboBoxListeners(playerColor);
		removeGameJComboBoxListeners(aiList);
		removeGameJComboBoxListeners(aiColor);
   }

	private void enableListeners(String name){
      if(name.equals(playerName[0])){
			gameType.addActionListener(this);
			playerList.addActionListener(this);
			playerColor.addActionListener(this);
         GO.addActionListener(this);
			aiList.setEnabled(false);
			aiColor.setEnabled(false);
         if(!playerName[1].equals("")){
				GO.setEnabled(true);
         }
		}    
		else if(name.equals(playerName[1])){
			aiList.addActionListener(this);
			aiColor.addActionListener(this);
         gameType.setEnabled(false);
         playerList.setEnabled(false);
         playerColor.setEnabled(false);
         GO.setEnabled(false);    
		}
	}

   private void removeGameJComboBoxListeners(GameJComboBox c){
      ActionListener[] temp = c.getActionListeners();
		for(int k = 0; k < temp.length; k++){
		   c.removeActionListener(temp[k]);
		}	 
	}

   private void removeGameJButtonListeners(GameJButton b){
      ActionListener[] temp = b.getActionListeners();
		for(int k = 0; k < temp.length; k++){
		   b.removeActionListener(temp[k]);
		}	 
	}

   private void removeAncestorListeners(JComponent c){
      AncestorListener[] temp = c.getAncestorListeners();
		for(int k = 0; k < temp.length; k++){
		   c.removeAncestorListener(temp[k]);
		}	 
	}

   private void removePropertyChangeListeners(Component c){
      PropertyChangeListener[] temp = c.getPropertyChangeListeners();
		for(int k = 0; k < temp.length; k++){
		   c.removePropertyChangeListener(temp[k]);
		}	 
	}

	private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException{
		try{
		   c = (GameJFrame)stream.readObject();
		   clientGUI = (ClientGUI)stream.readObject();
		   playerName = (String[])stream.readObject();
		   thisName = stream.readUTF();   
			GO = (GameJButton)stream.readObject();	    
		   p1 = (GameJPanel)stream.readObject();
			p2 = (GameJPanel)stream.readObject();
			p3 = (GameJPanel)stream.readObject();
			L1icon = (GameJLabel)stream.readObject();
			L2icon = (GameJLabel)stream.readObject();
			L1 = (GameJLabel)stream.readObject();
			L2 = (GameJLabel)stream.readObject();
			L3 = (GameJLabel)stream.readObject();
			L1color = (Color)stream.readObject();
			L2color = (Color)stream.readObject();  
			playerChoice = stream.readUTF();
			aiChoice = stream.readUTF();
			playerColorChoice = stream.readUTF();
			aiColorChoice  = stream.readUTF();
			playerList = (GameJComboBox)stream.readObject();
			aiList = (GameJComboBox)stream.readObject();
			playerColor = (GameJComboBox)stream.readObject();
			aiColor = (GameJComboBox)stream.readObject();
			gameType = (GameJComboBox)stream.readObject();
			startupIconPlayer = (GameImageIcon)stream.readObject();
			startupIconAi = (GameImageIcon)stream.readObject();
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
		   stream.writeObject(c);
		   stream.writeObject(clientGUI);
		   stream.writeObject(playerName);
		   stream.writeUTF(thisName);   
			stream.writeObject(GO);	    
		   stream.writeObject(p1);
			stream.writeObject(p2);
			stream.writeObject(p3);
			stream.writeObject(L1icon);
			stream.writeObject(L2icon);
			stream.writeObject(L1);
			stream.writeObject(L2);
			stream.writeObject(L3);
			stream.writeObject(L1color);
			stream.writeObject(L2color);  
			stream.writeUTF(playerChoice);
			stream.writeUTF(aiChoice);
			stream.writeUTF(playerColorChoice);
			stream.writeUTF(aiColorChoice);
			stream.writeObject(playerList);
			stream.writeObject(aiList);
			stream.writeObject(playerColor);
			stream.writeObject(aiColor);
			stream.writeObject(gameType);
			stream.writeObject(startupIconPlayer);
			stream.writeObject(startupIconAi);
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

}//end class

