package gameServer;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import javax.swing.*;
import java.util.*;
import games.civ.*;
import games.*;

public class ClientHandler extends Thread{

   private static final long serialVersionUID = 3797371564797076875L;

	private ServerGUI sGUI;
	private Socket client;
	private Socket gameClient;

	private GameObjectOutputStream output;
	private GameObjectOutputStream gameOut;
	private GameObjectInputStream input;
   private GameObjectInputStream gameIn;

	private String send, name="", message="", received="";
	private int index;//index of this client in list
	
	public ClientHandler(){}

	public ClientHandler(Socket s, Socket gameSocket, ServerGUI g, int c)
	{
		index = c;
		sGUI = g;
		client = s;
      gameClient = gameSocket;

		try{
			output = new GameObjectOutputStream(client.getOutputStream());
			input = new GameObjectInputStream(client.getInputStream());
         gameOut = new GameObjectOutputStream(gameClient.getOutputStream());
			gameIn = new GameObjectInputStream(gameClient.getInputStream());
		}catch(IOException ioEx){
			ioEx.printStackTrace();
      }
	}//end constructor
		
   public void reconnect(Socket s, Socket gameSocket){
		client = s;
      gameClient = gameSocket;

		try{
			output = new GameObjectOutputStream(client.getOutputStream());
			input = new GameObjectInputStream(client.getInputStream());
         gameOut = new GameObjectOutputStream(gameClient.getOutputStream());
			gameIn = new GameObjectInputStream(gameClient.getInputStream());
		}catch(IOException ioEx){
			ioEx.printStackTrace();
      }
	}
	
   public GameObjectOutputStream getGameOut(){
	   return gameOut;
	}
   public void setGameOut(GameObjectOutputStream gameOut){
	   this.gameOut = gameOut;
	}

   public GameObjectInputStream getGameIn(){
	   return gameIn;
	}
   public void setGameIn(GameObjectInputStream gameIn){
	   this.gameIn = gameIn;
	}

   public GameObjectOutputStream getOutput(){
	   return output;
	}
   public void setOutput(GameObjectOutputStream output){
	   this.output = output;
	}

   public GameObjectInputStream getInput(){
	   return input;
	}
   public void setInput(GameObjectInputStream input){
	   this.input = input;
	}

	private Object getSentObject(){
		try{
         return gameIn.readObject();
		}catch(IOException ioEx){
			System.out.println("ioEX");
			ioEx.printStackTrace();
		}catch(ClassNotFoundException CEx){
			System.out.println("CEX");
			CEx.printStackTrace();
		}catch(NullPointerException NPEx){
			System.out.println("NPEx Error in Handler.getSentObject() Server\n");
		   NPEx.printStackTrace();
		} 

      return null; 
	}
	
   private String retrieveMessage(){
	   try{  
	      return (String)input.readObject();
      }catch(IOException ioEx){
			System.out.println("IO EXCEPTION retrieveMessage()\n");
			ioEx.printStackTrace();
		}catch(ClassNotFoundException CEx){
			System.out.println("CLASSNOTFOUNDEXCEPTION retrieveMessage()\n");
			CEx.printStackTrace();
		}catch(NullPointerException NPEx){
			System.out.println("NPEx retrieveMessage()\n");
		   NPEx.printStackTrace();
		} 

      return null;
	}

	public void broadMessage(String s){
	   try{  
System.out.println("broadcasting message" + s);
         if(!s.equals("true") || !s.equals("false")){
			   output.writeObject(s);
	         output.flush();
            output.reset();      
			}
//         output.reset();
	   }
		catch(IOException ioEx){
			System.out.println("IOEx error in Handler.sendGameCommand() Server\n");
		   ioEx.printStackTrace();
		}
	}//end broadMessage(String)

	public void broadGame(String s, Object o){
Game mystring = (Game)o;
System.out.println("GameData: " + mystring.getGameData());


		broadMessage(s);
	   try{  
System.out.println("broadcasting game" + o + " " + name);
		   gameOut.writeObject(o);
         gameOut.flush();
         gameOut.reset();
	   }
		catch(IOException ioEx){
			System.out.println("IOEx error in Handler.sendGameCommand() Server\n");
		   ioEx.printStackTrace();
		}
	}

	public void run()
	{
		try{
			//send update to user entering chatroom
			ChatServer.userList(index, "add");
			StringTokenizer messageToken;
			String tempmsg;
			//main loop until quit
			while(true){

//				if(input.hasNextLine()){
				received = retrieveMessage(); 
System.out.println(received);
//				}else{
//				   received = "EMPTY LINE";
//				}

				//all commands have more than one token
				if(received.contains("~")){
					messageToken = new StringTokenizer(received, "~");
System.out.println(messageToken.countTokens());
					if(messageToken.countTokens() == 2){
						send = messageToken.nextToken();
						if(!ChatServer.checkInvalidName(send.trim(), -1)){
							message = received.substring(send.length()+2);
							if(message.equals("pokes you!")){
								ChatServer.broadCommand(message, send.trim(), name);
							} 	
							//can add more commands	with else if{} statements
							//fallthrough is a private message
							else if(message.equals("addGame~")){
System.out.println("add game in ClientHandler");
						      send = messageToken.nextToken();
							   ChatServer.addLaunchList(index, getSentObject());
							}
							else if(message.equals("updateGame~")){
						      send = messageToken.nextToken();
System.out.println("Update in Server");
							   ChatServer.civLaunchList(index, getSentObject());
							}
							else if(message.equals("leaveGame~")){
						      send = messageToken.nextToken();
System.out.println("leave in Server");
							   ChatServer.civLeaveList(index, getSentObject());
							}
							else if(message.equals("removeGame~")){
						      send = messageToken.nextToken();
System.out.println("remove in Server");
							   ChatServer.civRemoveGame(getSentObject());
							}
							else if(message.equals("addCivGame~")){
System.out.println("addCivGame in Server");
						      send = messageToken.nextToken();
							   ChatServer.addCivList(index, getSentObject());
							}
							else if(message.equals("updateCivGame~")){
						      send = messageToken.nextToken();
								ChatServer.civGameList(index, getSentObject());
System.out.println("UpdateCivGame in Server");
							}
							else if(message.equals("leaveCivGame~")){
						      send = messageToken.nextToken();
								ChatServer.civGameLeaveList(index, getSentObject());
System.out.println("leaveCivGame in Server");
							}
							else if(message.equals("removeCivGame~")){
						      send = messageToken.nextToken();
								ChatServer.civGameRemoveGame(getSentObject());
System.out.println("removeCivGame in Server");
							}
							else{
								ChatServer.broadCommand(message, send.trim(), name);
							}
						}
						//handles cases where there are two ~ tokens in nonprivate messages
						else{
							message = received.substring(name.length()+1);
							send = name+"> "+message;
							ChatServer.broadcast(send, index);
						}
					}
					//handles cases where there are not exactly two ~ tokens in message
					else{
						tempmsg = messageToken.nextToken("/");
						//public ~
						if(tempmsg.equals(name)){
							message = received.substring(name.length()+1);
							send = name+"> "+message;
							ChatServer.broadcast(send, index);
						}
						//private ~
						else{
							messageToken = new StringTokenizer(received, "~");
							send = messageToken.nextToken();
							message = received.substring(send.length()+2);
							ChatServer.broadCommand(message, send.trim(), name);
						}
					}
				}
				//else broadcast to all if no ~ in message
				else{
						message = received.substring(name.length()+1);
						send = name+"> "+message;
						ChatServer.broadcast(send, index);
				}
			}//end while
		}//end try
		catch(NoSuchElementException nseEx){
			sGUI.append("\nClient closed by window exit!");
			System.out.println("\nClient closed by window exit!");
		   nseEx.printStackTrace();
		}
		finally{
			try{
				sGUI.append("\n* Closing connection... *");
				System.out.println("\n* Closing connection... *");
				ChatServer.remClient(index, client);
				client.close(); 
			   gameClient.close();
			}
			catch(IOException ioEx){
				sGUI.append("\nUnable to disconnect!");
				System.out.println("\nUnable to disconnect!");
				System.exit(2);
			}
		}
	}//end run	

	public String getUsername(){

		return name;
	}//end getUsername()
	
   public void setUsername(String name){
	   this.name = name;
	}
	
   public int getIndex(){

	   return index;
	}
	
	public void setIndex(int i){

		index = i;
	}//end setIndex(int)

	public String setName(){

		boolean validName = false;
		
		do{
			name = retrieveMessage();
	
			validName = ChatServer.checkInvalidName(name, index);
		
			broadMessage(new Boolean(validName).toString());

		}while(!validName);

      return name;
	}//end setName()
}//end class