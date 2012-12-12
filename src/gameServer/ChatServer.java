package gameServer;

import java.io.*;
import java.net.*;
import java.util.*;

import games.civ.*;
import games.*;

public class ChatServer{

   private static final long serialVersionUID = 2656160651104344949L;

   private static Properties serverProperties;

	private static ArrayList<ClientHandler> clientList;
   private static ArrayList<Object> gameList;
   private static ArrayList<Object> activeCivList;

	private static ServerGUI sGUI;
	private static ServerSocket serverSocket;
	protected static ServerSocket serverGameSocket;

	protected static int PORT;
	protected static int GAME_PORT;

	private static int count;
	private static	ClientHandler temp;//for broadcasting
			
   public ChatServer(){}
	
	public static void main(String[] args)	throws IOException
	{
		clientList = new ArrayList<ClientHandler>();//create list for clients
		gameList = new ArrayList<Object>(); 
		activeCivList = new ArrayList<Object>(); 
		count = 0;
		initProperties();
		try{
			serverSocket = new ServerSocket(PORT);
			serverGameSocket = new ServerSocket(GAME_PORT);
		}
		catch (IOException ioEx){
			System.out.println("\nUnable to set up port!");
			System.exit(1);
		}

		sGUI = new ServerGUI();
		sGUI.append("Awaiting Client");
      String nameCheck;  
      boolean addClient;
		
		while(true){
			Socket client = serverSocket.accept();
			Socket gameClient = serverGameSocket.accept();
			
			ClientHandler handler = new ClientHandler(client, gameClient, sGUI, count);
         nameCheck = handler.setName();
			addClient = true;			

			for(ClientHandler clientCheck : clientList){
			   if(nameCheck.equals("RESUME"+clientCheck.getUsername())){
               addClient = false;
               if(!client.isConnected() || !gameClient.isConnected()){          
						clientCheck.reconnect(client, gameClient);
	            } 
					sGUI.append("\nRESUMING CONNECTION.\n");
					break; 
				}
			}

         if(addClient){
				sGUI.append("\nNew client socket accepted.\n");
				sGUI.append("New client game socket accepted.\n");
				clientList.add(count, handler);
				handler.setUsername(nameCheck);
				handler.start();
				sGUI.append("added >> "+client.toString());
				count++;
				sGUI.append("Clients " +count);
			}
		}
	}//end main

	private static void initProperties() throws IOException{
	   FileInputStream propertiesFile = new FileInputStream("server.properties");
  		serverProperties = new Properties();    
		serverProperties.load(propertiesFile);
      
		PORT = Integer.valueOf(serverProperties.getProperty("port"));
		GAME_PORT = Integer.valueOf(serverProperties.getProperty("gamePort"));

	   propertiesFile.close();
	}
	
	public synchronized static void remClient(int c, Socket s)
	{
		if(!clientList.isEmpty()){
			temp = clientList.get(c);
			temp.broadMessage("Stop Chatting. Learn JAVA instead");
			String tempName = temp.getUsername();
			clientList.remove(c);
			count--;
			for(int i=0; i < clientList.size(); i++){
				//remove from client side lists
				temp = clientList.get(i);
				if(i >= c){
					temp.setIndex(i);
				}
				String send = "remove> "+tempName;
				temp.broadMessage(send);
				}
			sGUI.append("\nremoved >> "+s+"Clients " +count);
		}
	}//end remClient()

	public synchronized static void removeGames()
	{
System.out.println("Removeing games" + gameList.size());
		if(!gameList.isEmpty()){
         ClientHandler tempClient;
         String send = "removeGame>";
         for(int k = 0; k < gameList.size(); k++){ 
				for(int i=0; i < clientList.size(); i++){
					//remove from client side lists
					tempClient = clientList.get(i);
					tempClient.broadGame(send, gameList.get(k));
            }
			}
			gameList.clear();
		}
	}

	public synchronized static void broadcast(String s, int index)
	{
		for(int i=0; i < count; i++){
			temp = clientList.get(i);
			if(i != index){
				 temp.broadMessage(s);
			}
		}
	}//end broadcast(String, int)
	
	public synchronized static void broadCommand(String msg, String to, String from)
	{
		for(int i=0; i < count; i++){
			temp = clientList.get(i);
			if(to.equals(temp.getUsername())){
				 temp.broadMessage(from+"[private]> "+ msg);
			}
		}
	}//end broadCommand(String, String, String)
		
	public synchronized static void userList(int index, String s)
	{
System.out.println("userList " + gameList.size());
		for(int i=0; i < clientList.size(); i++){
			ClientHandler chnd = clientList.get(i);
			for(int j=0; j < clientList.size(); j++){
				temp = clientList.get(j);
				if(!temp.getUsername().equals("")){
					String send = s+"> "+temp.getUsername();
					chnd.broadMessage(send);
				}
			}
		}
      gameList(index);
	}//end userList()
	
	public synchronized static void gameList(int index)
	{
System.out.println("gameList " + gameList.size());
		ClientHandler chnd;
		for(int k=0; k < clientList.size(); k++){
         chnd = clientList.get(k);
	      if(k == index){    
				for(int j=0; j < gameList.size(); j++){
					String send = "addGame>";
					chnd.broadGame(send, gameList.get(j));
				}
         }
      }
	}

	public synchronized static int findGameIndex(Object game){
	   int index = 0;
      Game tempGame = (Game)game;
	   String gameID = tempGame.getGameName();
	     
		Game compareGame;  
      String compareID;
		for(Object findGame : gameList){
		   compareGame = (Game)findGame;
			compareID = compareGame.getGameName();
			if(gameID.equals(compareID)){
System.out.println("FIND GAME FOUND");
            break;
			}else{
			   index++;
			}
		}
      return index;
	}

	public synchronized static void replaceGameData(Object game){
//do isInstance checks for different games
      Game temp = (Game)game;
      Object compareType = temp.getGameData();    
      int range = findGameIndex(game);
		if(compareType instanceof CivLaunch){
         temp = (Game)gameList.get(findGameIndex(game));      
			temp.setGameData(compareType);
      }
		else if(compareType instanceof CivGUI){
         if(range >= 0 && range < gameList.size()){
				temp = (Game)gameList.get(findGameIndex(game));      
				temp.setGameData(compareType);
         }
      }
	}

	public synchronized static void addLaunchList(int index, Object o)
	{
System.out.println("Entered addLaunchList" + o);
      if(!gameList.contains(o)){//o.getClass().getName().equals("Civ.CivLaunch")){
		   gameList.add(o);
System.out.println("Adding to gameList " + o+ " "+gameList.size());
		}
      for(ClientHandler handler: clientList){
		   handler.broadGame("addGame>", o);
		}
	}
	
	public synchronized static void civLaunchList(int index, Object game)
	{
      replaceGameData(game); 
      for(ClientHandler handler: clientList){
		   handler.broadGame("updateGame>", game);
		}
	}

	public synchronized static void civLeaveList(int index, Object o)
	{
      if(gameList.contains(o)){//o.getClass().getName().equals("Civ.CivLaunch")){
		   gameList.remove(o);
		}

      for(ClientHandler handler: clientList){
		   handler.broadGame("leaveGame>", o);
		}
System.out.println("Leaving civLeaveList" + o.getClass().getName());
	}

	public synchronized static void civRemoveGame(Object game)
	{
System.out.println("civRemoveGame" + gameList.size());
		Game temp = (Game) game;
		Game listGame;
  
      for(Object compareGame : gameList){
			listGame = (Game)compareGame;   
			if(listGame.getGameName().equals(temp.getGameName())){
			   gameList.remove(compareGame);
				break;
			}
		}

      for(ClientHandler handler: clientList){
		   handler.broadGame("removeGame>", game);
		}
	}

/*ACTIVE CIVILIZATION COMMUNICATION*/

	public synchronized static void addCivList(int index, Object game)
	{
      Game compareGame = (Game)game;
		String compareCreator = compareGame.getCreator();  
System.out.println("Entered addCivList" + compareCreator);

      gameList.add(game);
	   replaceGameData(game); 
      for(ClientHandler handler: clientList){
         if(!handler.getUsername().equals(compareCreator)){
			   handler.broadGame("addCivGame>", game);
	      }
		}
	}

	public synchronized static void civGameList(int index, Object game)
	{
System.out.println("Entered civGameList" + game.getClass().getName());
		replaceGameData(game);
      Game castGame = (Game)game;
      CivGUI temp = (CivGUI)castGame.getGameData();
      String[] tempPlayers = temp.getPlayerName();
		for(ClientHandler handler : clientList){
         for(String player : tempPlayers){
		      if(handler.getUsername().equals(player)){
					String send = "updateCivGame>";
					handler.broadGame(send, game);
            }
         }
      }
	}

	public synchronized static void civGameLeaveList(int index, Object o)
	{
      if(gameList.contains(o)){//o.getClass().getName().equals("Civ.CivLaunch")){
		   gameList.remove(o);
		}
		for(int i=0; i < clientList.size(); i++){
         if(i != index){
				ClientHandler chnd = clientList.get(i);
				String send = "leaveCivGame>";
				chnd.broadGame(send, o);
         }
      }
System.out.println("Leaving civLeaveList" + o.getClass().getName());
	}

	public synchronized static void civGameRemoveGame(Object o)
	{
System.out.println("civRemoveGame" + gameList.size());
CivLaunch temp = (CivLaunch)o;
CivLaunch temp2;
      for(int k = 0; k < gameList.size(); k++){
			temp2 = (CivLaunch)gameList.get(k);
// change getID()...			if(temp.getGameID().getID() == temp2.getGameID().getID()){		
//		      gameList.remove(k);
//         }
		}    
		for(int i=0; i < clientList.size(); i++){
//         if(i != index){
				ClientHandler chnd = clientList.get(i);
				String send = "removeCivGame>";
				chnd.broadGame(send, o);
//         }
      }
	}

	public synchronized static boolean checkInvalidName(String n, int index)
	{
		if(!clientList.isEmpty()){
			for(ClientHandler handle : clientList){
            if(n.contains("RESUME") && handle.getUsername().equals(n)){
				   return true;
				} 
				if((handle.getUsername().equals("")) || (handle.getUsername().equalsIgnoreCase(n))){
					return false;
				}			
			}		
		}
		return true;
	}//end checkValidName(String, int)
}//end class