package gameClient;

import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import games.civ.*;
import games.*;

public class ClientGUI extends JFrame implements WindowListener, ActionListener, MouseListener, Serializable{

   private static final long serialVersionUID = -7995901967193411581L;

	private transient JTextArea showClient;	
	private transient JTextField answerBox;
	private transient JMenuItem exit, clear, civ;
	private transient JList showUsers;
	private transient JScrollBar botVert;
	private transient String results;
 	private transient UserName userName;	
	private transient Vector<String> userVector;
	
   private transient JList<Object> showGames;
	private transient JScrollBar gamesVert;

//for active games
	private Handler handle;
   private String clientName;
   private ArrayList<Game> activeGames;
	private ArrayList<Game> gameVector;

	public Handler getHandle(){
	   return handle;
	}
	public void setHandle(Handler handle){
	   this.handle = handle;
	}
	
	public String getClientName(){
	   return clientName;
	}
	public void setClientName(String clientName){
	   this.clientName = clientName;
	}
	
	public ArrayList<Game> getActiveGames(){
	   return activeGames;
	}
   public void setActiveGames(ArrayList<Game> activeGames){
	   this.activeGames = activeGames;
	}
	
	public ArrayList<Game> getGameVector(){
	   return gameVector;
	}
   public void setGameVector(ArrayList<Game> gameVector){
	   this.gameVector = gameVector;
	}

	public ClientGUI(){}

	public ClientGUI(Handler h){

		handle = h;
		userVector = new Vector<String>();
      gameVector = new ArrayList<Game>();
		activeGames = new ArrayList<Game>();
		userName = new UserName();
	}//end constructor	

	public void actionPerformed(ActionEvent e){
  		if(e.getSource() == answerBox){
			results = answerBox.getText();
			answerBox.setText("");
			answerBox.requestFocus();
			handle.setAnswer(results);
		}
		else if(e.getSource() == exit){
			handle.setQuit();
		}
		else if(e.getSource() == clear){
			showClient.setText("CHAT LOG\n");
		}
		else if(e.getSource() == civ){	
			CivLaunch civ = new CivLaunch(handle.getUserName(), this);
		   Game temp = new Game("civ", handle.getUserName(), (Object)civ);
			activeGames.add(temp);
			handle.sendGameCommand("~"+handle.getUserName()+"~"+"addGame~", temp);
	System.out.println("num players " + temp.getNumberPlayers());
		}		
	}

	public void mouseClicked(MouseEvent e){

		if(e.getSource() == showUsers){// && e.getClickCount() == 2){

			UserMenu menu; 
			if(!userName.getName().equals(userVector.elementAt(showUsers.locationToIndex(e.getPoint())).toString().trim()))
				menu = new UserMenu(userVector.elementAt(showUsers.locationToIndex(e.getPoint())).toString(),
				                 showUsers.locationToIndex(e.getPoint()), showUsers.getLocationOnScreen().x,
									  showUsers.getLocationOnScreen().y);
		}

		if(e.getSource() == showGames){// && e.getClickCount() == 2){

System.out.println("CLICK GAMES" + gameVector.size());
			if(gameVector.size() > 0){
				GameMenu menu; 
	         Game game = (Game)gameVector.get(showGames.locationToIndex(e.getPoint()));
	         boolean canJoin = false;
				         
            if(game.getNumberPlayers() < 2){
	            CivLaunch temp = (CivLaunch)game.getGameData();
					String[] compare = temp.getPlayerName(); 
               for(String name : compare){
						if(name.equals(userName.getName())){
		               canJoin  = false;
							break;
						}else{
						   canJoin = true;
		            }
					
					}   
            }

				if(canJoin){
               game = (Game)gameVector.get(showGames.locationToIndex(e.getPoint()));
					menu = new GameMenu(game,
											  game.getGameName(),
				                       showGames.locationToIndex(e.getPoint()),
											  showGames.getLocationOnScreen().x,
										     showGames.getLocationOnScreen().y);
System.out.println("canJoin " +game.getNumberPlayers());		
				}
         }
		}
	}
	public void mouseEntered(MouseEvent e){}
	public void mousePressed(MouseEvent e){}
	public void mouseReleased(MouseEvent e){}
	public void mouseExited(MouseEvent e){}
	
	public void windowDeactivated(WindowEvent e){}
	public void windowActivated(WindowEvent e){}
	public void windowDeiconified(WindowEvent e){}
	public void windowIconified(WindowEvent e){}
	public void windowClosed(WindowEvent e){
System.out.println("window closed");
      handle.setQuit();
	}
	public void windowClosing(WindowEvent e){
System.out.println("window closing");
      handle.setQuit();
	}
	public void windowOpened(WindowEvent e){}

	//GAME METHODS
	
   public void joinGame(Game game){
      //if civLaunch...instanceOf keyword? maybe game.getGameData().getClass().getName()
		game.setNumberPlayers(game.getNumberPlayers() + 1);
      CivLaunch inLaunch = (CivLaunch)game.getGameData();
      activeGames.add(game);
      inLaunch.addPlayerToGame(clientName, this, inLaunch);
      //on and on for other games
	}

   public void addGame(Game game){
System.out.println("addGame " + game);//.getClass().getName());

      boolean containsGame = false;
      String currentGameName = game.getGameName();

      for(Game compareGame: gameVector){
		   if(compareGame.getGameName().equals(currentGameName)){
				containsGame = true;
				break;			
			}
		}

      if(!containsGame){
			gameVector.add(game);
	      showGames.setListData(gameVector.toArray());
System.out.println("addGame numPlayers" + game.getNumberPlayers());
      }
	}
	
	public void leaveGame(Game game){

System.out.println("LEAVEGAME");

      game.decrementNumberPlayers(); 

      //Civ specific...needs altering for other games...
		if(activeGames.contains(game)){
System.out.println("CONTAINS GAME");
			CivLaunch temp = (CivLaunch) activeGames.get(activeGames.indexOf(game)).getGameData();
	      temp.getC().dispose();
         activeGames.remove(game);
	      if(game.getNumberPlayers() > 0){ 
         String[] userNames = temp.getPlayerName();      
	         for(String names : userNames){
				
				}
      }
//			for(int k = 0; k < userNames.length; k++){
//System.out.println(userNames[k]);
//				if(userNames[k].equals(userName.getName())){
//					activeCivJoin = new CivLaunch(userName.getName(), this);
//		         GameID id = new GameID(gameVector.size(), activeCivJoin);
//		         activeCivJoin.setGameID(id);
//					handle.sendGameCommand("~"+userName.getName()+"~"+"addGame~", (Object)activeCivJoin);
//            }
//         }
      }
	}

	public void removeGame(Game game){

System.out.println("removeGame " + game);
      for(Game compareGame : gameVector){
		   if(compareGame.getGameName().equals(game.getGameName())){
				gameVector.remove(compareGame);
//            activeGames.remove(compareGame);
				break;			
			}
		}
      showGames.setListData(gameVector.toArray());
	}


	private void setInitName(){
		String tmpuserName = userName.getName();
		if(tmpuserName.equals(""))
			userName.illegalName(0);
		else if(tmpuserName.lastIndexOf(" ") > -1){
			userName.illegalName(1);
		}
		else if(tmpuserName.contains("/"))
			userName.illegalName(2);
		else if(tmpuserName.contains("~"))
			userName.illegalName(3);
		else{
			handle.setUserName(userName.getName());
			handle.checkName();
		}
	}//end setInitName(String)

	protected void updateUsers(String user, int b){
		if(b == 1 && !userVector.contains(user)){
			userVector.add(user);
		}
		else if(b == 0 && userVector.contains(user)){
			userVector.remove(user);
		}
		InsertionSort(userVector);//sort for clients.
		showUsers.setListData(userVector);
	}//end updateUsers(String, int)

	private void InsertionSort(Vector<String> v){
		int size = v.size();
      int k, j;
  		String temp;    
		for(k=0; k < size; k++){
			temp = v.elementAt(k).toString();
         j=k;
			while(j > 0 && v.elementAt(j-1).toString().compareToIgnoreCase(temp) > 0){
            v.set(j, v.elementAt(j-1));
			   j--;
         }	
  			v.set(j, temp);
  			showUsers.ensureIndexIsVisible(j);
    	} 
	}//end InsertionSort(Vector)


	protected void append(String s){
		showClient.append(s);
		botVert.setValue(botVert.getMaximum());
	}//end append(String)

	protected void validateName(boolean val){
		if(val){
			handle.setUserName(userName.getName());
			clientName = userName.getName();
			startGUI();
		}
		else if(!val)
			userName.takenName();
	}//end validateName(boolean)

//begin Civ specific calls
	public void goCivGame(CivLaunch game){
      int index = 0; 
      for(Game sendGame : activeGames){
		   if(sendGame.getGameData().equals(game)){
			   break;
			}else{
				index++;			
			}
		}
      if(index >= 0 && index < activeGames.size()){
//System.out.println("sent updateGame command from goCivGame" + activeGames.get(index));
			Game temp = activeGames.get(index);
	      CivGUI civGUI = new CivGUI(game.getPlayerChoice(),
			                           game.getAiChoice(),
												game.getPlayerColorChoice(),
												game.getAiColorChoice(),
	 	                              2,
												game.getGameType().getSelectedIndex(),
												game.getClientGUI(),
												game.getPlayerName(),
												temp.getGameName());
			temp.setGameData(civGUI);		
			handle.sendGameCommand("~"+clientName+"~"+"addCivGame~", activeGames.get(index));
	   }else{
System.out.println("GAME NOT FOUND updateCivGames");
		}
	}

	public void updateCivLaunch(CivLaunch game){

      int index = 0; 
      for(Game sendGame : activeGames){
		   if(sendGame.getGameData().equals(game)){
			   break;
			}else{
				index++;			
			}
		}
      if(index >= 0 && index < activeGames.size()){
			CivLaunch inLaunch = (CivLaunch)activeGames.get(index).getGameData();
			handle.sendGameCommand("~"+clientName+"~"+"updateGame~", activeGames.get(index));
	   }else{
System.out.println("GAME NOT FOUND updateCivLaunch");
		}
	}

	public void updateCivLaunch(Game game){

System.out.println("update " + game.getGameName());
		int index = 0; 
      for(Game sendGame : activeGames){
		   if(sendGame.getGameName().equals(game.getGameName())){
System.out.println("update match ");
			   break;
			}else{
				index++;			
			}
		}
System.out.println("index " +index);
      if(index < activeGames.size()){
			CivLaunch temp = (CivLaunch)activeGames.get(index).getGameData();
//			temp.deepClone((CivLaunch)game.getGameData(), userName.getName());
			temp.updateGUI((CivLaunch)game.getGameData());
      }
	}

	public void addCivGame(Game game){
System.out.println("addCivGame " + game);
		int index = 0; 
      for(Game sendGame : activeGames){
		   if(sendGame.getGameName().equals(game.getGameName())){
System.out.println("update match " + game.getGameData());
            CivLaunch launch = (CivLaunch)sendGame.getGameData();
            launch.getC().dispose();        
				sendGame.setGameData(game.getGameData());
			   break;
			}else{
				index++;			
			}
		}
      if(index < activeGames.size()){
         CivGUI civGUI = new CivGUI((CivGUI)game.getGameData(), this);
      }
   }

   public void updateCivGames(CivGUI game){
      int index = 0; 
      for(Game sendGame : activeGames){
		   if(sendGame.getGameName().equals(game.getGameID())){
			   break;
			}else{
				index++;			
			}
		}
      if(index >= 0 && index < activeGames.size()){
System.out.println("GAME FOUND updateCivGames");
			CivGUI inGUI = (CivGUI)activeGames.get(index).getGameData();
			handle.sendGameCommand("~"+clientName+"~"+"updateCivGame~", activeGames.get(index));
	   }else{
System.out.println("GAME NOT FOUND updateCivGames");
		}
	}
	
	public void updateCivGUI(Game game){

System.out.println("update " + game.getGameName());
		int index = 0; 
      for(Game sendGame : activeGames){
		   if(sendGame.getGameName().equals(game.getGameName())){
System.out.println("update match ");
			   break;
			}else{
				index++;			
			}
		}
System.out.println("index " +index);
      if(index < activeGames.size()){
			CivGUI temp = (CivGUI)activeGames.get(index).getGameData();
			temp.updateGUI((CivGUI)game.getGameData());
      }
	}

	public void leaveCivGames(CivLaunch game){

      int index = -1; 
      for(Game sendGame : gameVector){
		   if(sendGame.getGameData().equals(game)){
			   break;
			}else{
				index++;			
			}
		}
      if(index >= 0 && index < gameVector.size()){
			game.getC().dispose();
			handle.sendGameCommand("~"+clientName+"~"+"leaveGame~", gameVector.get(index));
System.out.println("sent leaveGame command");
		}
	}

	public void removeCivGames(CivLaunch game){

      int index = -1; 
      for(Game sendGame : gameVector){
		   if(sendGame.getGameData() == game){
			   break;
			}else{
				index++;			
			}
		}
      if(index >= 0 && index < gameVector.size()){
			System.out.println(gameVector.remove(game));
	      showGames.setListData(gameVector.toArray());
			game.getC().dispose();
			handle.sendGameCommand("~"+clientName+"~"+"removeGame~", gameVector.get(index));
System.out.println("removeGame");
		}
	}

	private void startGUI(){
		JFrame c = new JFrame(userName.getName()+"'s Chat Window");	
		c.addWindowListener(this);
		
		//set up menu
		JMenuBar m = new JMenuBar();
		JMenu file = new JMenu("File");
		JMenu edit = new JMenu("Edit");
		JMenu game = new JMenu("Game");
		exit = new JMenuItem("Exit");
		clear = new JMenuItem("Clear Chat");
      civ = new JMenuItem("Civ");
		m.add(file);
		m.add(edit);
      m.add(game);
		file.add(exit);
		edit.add(clear);
      game.add(civ);
						
		GameJPanel p = new GameJPanel();
  		GameJPanel p2 = new GameJPanel();
		GameJPanel p3 = new GameJPanel();		
		JLabel lP2 = new JLabel("Message");
		answerBox = new JTextField("", 35);
		showUsers = new JList();
		showUsers.setBackground(new Color(0, 50, 250));
		showUsers.setForeground(new Color(255, 255, 255));		
		
		showClient = new JTextArea("CHAT LOG\n", 10, 40);
		showClient.setEditable(false);                
		showClient.setBackground(new Color(255, 255, 255));
		showClient.setFont(new Font("Serif", 0, 14));
		showClient.setForeground(new Color(0, 0, 255));
		showClient.setLineWrap(true);

		JScrollPane sPane = new JScrollPane(showClient, 22, 31);
		botVert = sPane.getVerticalScrollBar(); 
		JScrollPane uPane = new JScrollPane(showUsers, 20, 30);
		uPane.setPreferredSize(new Dimension(150, 200));

		showGames = new JList<Object>();
		showGames.setBackground(new Color(250, 50, 0));
		showGames.setForeground(new Color(255, 255, 255));		
		GameJPanel p4 = new GameJPanel();		
		JScrollPane gamePane = new JScrollPane(showGames, 20, 30);
		gamePane.setPreferredSize(new Dimension(150, 200));
		showGames.addMouseListener(this);
		p4.add(gamePane);  		
		p4.setBackground(new Color(0, 0, 0));
		c.add(p4, "East");
				
		c.setJMenuBar(m);
		c.add(p ,"Center");
	 	c.add(p2 ,"South");
		c.add(p3, "West");
		p.add(sPane);
		p2.add(lP2);
		p2.add(answerBox);
		p3.add(uPane);  		
		p3.setBackground(new Color(0, 0, 0));

		answerBox.addActionListener(this);
		civ.addActionListener(this);
		exit.addActionListener(this);
		clear.addActionListener(this);
		showUsers.addMouseListener(this);

		p.setBackground(new Color(0, 0, 0));
		p2.setBackground(new Color(200, 200, 200));
		c.setBounds(300, 450, 800, 300);
		c.setResizable(false);
		c.setVisible(true);
		c.setDefaultCloseOperation(EXIT_ON_CLOSE);
		answerBox.requestFocus();
		//start Thread
		handle.start();
	}//end startGUI()

   //inner class to get userName from user
	private class UserName implements ActionListener, Serializable{

		private String userName;
		private JTextField userNameBox;
		private JFrame c;
				
		public UserName(){
			c = new JFrame();
			c.setResizable(false);
			GameJPanel p = new GameJPanel();
			JLabel l = new JLabel("Enter Name");
			userNameBox = new JTextField("", 15);		
			userNameBox.addActionListener(this);
			p.add(l);
			p.add(userNameBox);
			c.add(p);
			c.setBounds(100,200,230,100);
			c.setDefaultCloseOperation(EXIT_ON_CLOSE);
         c.setVisible(true);
		}//end UserName
	
		private void takenName(){
			c.setTitle("NAME TAKEN!");
			c.setVisible(true);	
			c.repaint();
		}//end takenName()
		
		private void illegalName(int err){
			switch(err){
				case 0:
					c.setTitle("EMPTY NAME!");
					break;
				case 1:
					c.setTitle("NO SPACES IN NAME!");
					break;
				case 2://reserved for messages
					c.setTitle("NO / IN NAME!");
					break;
				case 3://reserved for ~COMMAND~ 
					c.setTitle("NO ~ IN NAME!");
					break;
			}
			c.setVisible(true);	
			c.repaint();
		}//end illegalName()
		
		public String getName(){
			if(c.isVisible()){
				c.dispose();
			}
			return userName;
		}//end getName()
		
		public void actionPerformed(ActionEvent e){
  			if(e.getSource() == userNameBox){	
				userName = userNameBox.getText();
				setInitName();
			}		
		}//end actionPerformed(e)

	   public String getUserName() {
			return userName;
		}
		public void setUserName(String userName) {
			this.userName = userName;
		}
		public JTextField getUserNameBox() {
			return userNameBox;
		}
		public void setUserNameBox(JTextField userNameBox) {
			this.userNameBox = userNameBox;
		}
		public JFrame getC() {
			return c;
		}
		public void setC(JFrame c) {
			this.c = c;
		}

	}//end class

	private class UserMenu implements ActionListener, Serializable{

		private JButton pm, pk;
		private JFrame c;
		private JPanel p;
		private JTextField pmBox;
				
		public UserMenu(){}

		public UserMenu(String t, int index, int x, int y){

			c = new JFrame();
			c.setResizable(false);
			c.setTitle(t);
			p = new JPanel();
			pm = new JButton("Private Message");
			pk = new JButton("Poke");
			c.add(p);
			p.add(pm);
			p.add(pk);
			pm.addActionListener(this);
			pk.addActionListener(this);
			c.setBounds(x, y, 230,100);
         c.setVisible(true);
		}//end constructor

		public void actionPerformed(ActionEvent e){
  			if(e.getSource() == pk){	
				c.dispose();
            pk.removeActionListener(this);
				sendCommand("~"+c.getTitle()+"~"+"pokes you!");
			}		
			else if(e.getSource() == pm){
            pm.removeActionListener(this);
				pmBox = new JTextField("", 50);		
				JLabel l = new JLabel("Enter Message");
				p.remove(pk);
				p.remove(pm);
				p.add(l);
				p.add(pmBox);
				pmBox.addActionListener(this);
				c.setSize(640,100);
				pmBox.requestFocus();
				c.repaint();
			}		
			else if(e.getSource() == pmBox){
				c.dispose();
            pmBox.removeActionListener(this);
				sendCommand("~"+c.getTitle()+"~"+pmBox.getText());
			}
		}//end actionPerformed(e)

		public JButton getPm() {
			return pm;
		}
		public void setPm(JButton pm) {
			this.pm = pm;
		}
		public JButton getPk() {
			return pk;
		}
		public void setPk(JButton pk) {
			this.pk = pk;
		}
		public JFrame getC() {
			return c;
		}
		public void setC(JFrame c) {
			this.c = c;
		}
		public JPanel getP() {
			return p;
		}
		public void setP(JPanel p) {
			this.p = p;
		}
		public JTextField getPmBox() {
			return pmBox;
		}
		public void setPmBox(JTextField pmBox) {
			this.pmBox = pmBox;
		}
	}

   //needed for UserMenu actionPerformed
	public void sendCommand(String s){
		results = s;
		handle.setCommand(results);	
	}//end sendCommand(String)

	private class GameMenu implements ActionListener, Serializable
	{
		private JButton join;
		private JFrame c;
		private JPanel p;
		private Game game;
				
		public GameMenu(){}

		public GameMenu(Game gameToJoin, String t, int index, int x, int y)
		{
			game = gameToJoin;
			c = new JFrame("?");
			c.setResizable(false);
			c.setTitle(t);
			p = new JPanel();
			join = new JButton("Join Game");
			c.add(p);
			p.add(join);
			join.addActionListener(this);
			c.setBounds(x, y, 150,80);
         c.setVisible(true);
		}//end constructor
		
		public void actionPerformed(ActionEvent e) 
		{
  			if(e.getSource() == join){	
				join.removeActionListener(this);
				c.dispose();
            joinGame(game);        
			}		
		}//end actionPerformed(e)

		public JButton getJoin() {
			return join;
		}
		public void setJoin(JButton join) {
			this.join = join;
		}
		public JFrame getC() {
			return c;
		}
		public void setC(JFrame c) {
			this.c = c;
		}
		public JPanel getP() {
			return p;
		}
		public void setP(JPanel p) {
			this.p = p;
		}
		public Game getGame() {
			return game;
		}
		public void setGame(Game game) {
			this.game = game;
		}
	}//end class
}
