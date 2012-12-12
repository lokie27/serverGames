package gameClient;

import java.io.*;
import java.net.*;
import java.util.*;
import games.civ.*;
import games.*;

public class Handler extends Thread implements Serializable{

   private static final long serialVersionUID = -5940049506134122660L;

	private GameSocket socket, gameSocket; 

	private String response="", message ="";
	private String userName;
	private ClientGUI clientGUI;

	private transient GameObjectOutputStream output;
	private transient GameObjectInputStream input;

	private transient GameObjectOutputStream gameOut;
   private transient GameObjectInputStream gameIn;

	private transient StringTokenizer messageToken;
	private boolean quit;
	private int port, gamePort;
	private InetAddress host;
	
	public Handler(){} 

	public Handler(InetAddress host, int PORT, int GAME_PORT){

      this.host = host;
		this.port = PORT;
		this.gamePort = GAME_PORT;

		socket = null; 
		gameSocket = null; 
		quit = false;

		try{
			socket = new GameSocket(host, PORT); 
			gameSocket = new GameSocket(host, GAME_PORT); 

			output = new GameObjectOutputStream(socket.getOutputStream());
			input = new GameObjectInputStream(socket.getInputStream()); 

         gameOut = new GameObjectOutputStream(gameSocket.getOutputStream());
			gameIn = new GameObjectInputStream(gameSocket.getInputStream());
		}
		catch(ConnectException conEx){
			System.out.println("Connection Refused!\n");
			System.exit(1);
		}
		catch(IOException ioEx){
			System.out.println("IO Exception! Connection Refused!\n");
			ioEx.printStackTrace();
		}
System.out.println("Init clientGUI\n");
		clientGUI = new ClientGUI(this);
	}//end constructor

   protected void closeSockets(){
		try{
			clientGUI.append("* Closing connection... *\n");
			System.out.println("* Closing connection... *\n");

         input.close();
         output.close();
			socket.close(); 
         
			gameIn.close();
			gameOut.close();   
	      gameSocket.close();
		}
		catch(IOException ioEx){
			clientGUI.append("Unable to disconnect!\n");
			System.out.println("Unable to disconnect!\n");
		   ioEx.printStackTrace();
		}
	}
	
   private String retrieveMessage(){
		try{
         if(input == null){
	         reconnectInSocket();
	      }
			String msg = (String)input.readObject();
			return msg;
      }catch(IOException ioEx){
			System.out.println("IO EXCEPTION retrieveMessage\n");
			ioEx.printStackTrace();
		}catch(ClassNotFoundException CEx){
			System.out.println("CLASSNOTFOUNDEXCEPTION getSentObject\n");
			CEx.printStackTrace();
		}catch(NullPointerException NPEx){
			System.out.println("NPEx getSentObject\n");
		   NPEx.printStackTrace();
		} 

      return null;
	}

   private Object getSentObject(){
		try{
         if(gameIn == null){
	         reconnectGameInSocket();
	      }
		   return gameIn.readObject();
      }catch(IOException ioEx){
			System.out.println("IO EXCEPTION getSentObject\n");
			ioEx.printStackTrace();
		}catch(ClassNotFoundException CEx){
			System.out.println("CLASSNOTFOUNDEXCEPTION getSentObject\n");
			CEx.printStackTrace();
		}catch(NullPointerException NPEx){
			System.out.println("NPEx getSentObject\n");
		   NPEx.printStackTrace();
		} 

      return null;
	}

   public void sendMessage(String send){
		try{
         if(output == null){
	         reconnectOutSocket();
	      }
			output.writeObject(send);
         output.flush();
			output.reset();
		}
		catch(ConnectException conEx){
			System.out.println("Connection Refused!\n");
			conEx.printStackTrace();
			System.exit(1);
		}
		catch(NotSerializableException NSEx){
			System.out.println("NOT SERIALIZABLE\n");
			NSEx.printStackTrace();
		}
		catch(IOException ioEx){
			System.out.println("IO EXCEPTION sendMessage\n");
			ioEx.printStackTrace();
		}
		catch(NullPointerException NPEx){
			System.out.println("NULLPOIUNTER sendMessage\n");
			NPEx.printStackTrace();
		}
	}

   public void sendGameCommand(String s, Game game){
 		sendMessage(s);
		try{
			if(gameOut == null){
            reconnectGameOutSocket();
         }
			gameOut.writeObject(game);
         gameOut.flush();
			gameOut.reset();
		}
		catch(ConnectException conEx){
			System.out.println("Connection Refused!\n");
			conEx.printStackTrace();
			System.exit(1);
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
	
	public void run(){

		try{

			while(!quit){

				response = retrieveMessage();

				messageToken = new StringTokenizer(response, "> ");
				message = messageToken.nextToken();

				if(message.equals("add")){
					clientGUI.updateUsers(messageToken.nextToken(">"), 1);
				}
				else if(message.equals("remove")){
					clientGUI.updateUsers(messageToken.nextToken(">"), 0);				
				}
				else if(message.equals("addGame")){
System.out.println("addGame in run");
					Game tempGame = (Game)getSentObject();
					clientGUI.addGame(tempGame);				
				}
				else if(message.equals("updateGame")){
System.out.println("updateGame in run");
					Game tempGame = (Game)getSentObject();
					clientGUI.updateCivLaunch(tempGame);				
				}
				else if(message.equals("removeGame")){
System.out.println("removeGame in run");
					Game tempGame = (Game)getSentObject();
					clientGUI.removeGame(tempGame);				
				}
				else if(message.equals("leaveGame")){
System.out.println("leaveGame in run");
					Game tempGame = (Game)getSentObject();
					clientGUI.leaveGame(tempGame);				
				}
				else if(message.equals("addCivGame")){
System.out.println("addCivGame in run");
					Game tempGame = (Game)getSentObject();
					clientGUI.addCivGame(tempGame);				
				}
				else if(message.equals("updateCivGame")){
System.out.println("updateCivGame in run");
					Game tempGame = (Game)getSentObject();
					clientGUI.updateCivGUI(tempGame);				
				}
				else if(message.equals("removeCivGame")){
System.out.println("removeCivGame in run");
					CivGUI tempGame = (CivGUI)getSentObject();
//					clientGUI.removeCivGame(tempGame);				
				}
				else if(message.equals("leaveCivGame")){
System.out.println("leaveCivGame in run");
					CivGUI tempGame = (CivGUI)getSentObject();
//					clientGUI.leaveCivGame(tempGame);				
				}
				else{
					clientGUI.append(response+"\n");
				}
			}
		}
		//catch server closing
		catch(NoSuchElementException nseEx){
			clientGUI.append("Server closed!\n");
//		   nseEx.printStackTrace();
		}
		finally{
		   setQuit();
		}
	}//end run()
	
	public void writeMessage(){
		message = response;
		clientGUI.append(userName+" -> "+message+"\n");
		sendMessage(userName+"/"+message); 
	}//end writeMessage()
	
	public void setAnswer(String s){
		response = s;
		if(!response.trim().equals(""))
			writeMessage();
	}

	public void checkName(){

		sendMessage(userName);

		response = retrieveMessage();
		
		if(response.equals("true")){
			clientGUI.validateName(true);
      }else if(response.equals("false")){
			clientGUI.validateName(false);
      }

 	}//end checkName()
		
	public void setCommand(String s){
		sendMessage(s);
	}//end setCommand(String)
	
	//quit function for menu exit selected
	public void setQuit(){
      closeSockets();
		System.exit(0);
	}//end setQuit()
		
	public GameSocket getSocket() {
		return socket;
	}
	public void setSocket(GameSocket socket) {
		this.socket = socket;
	}
	public GameSocket getGameSocket() {
		return gameSocket;
	}
	public void setGameSocket(GameSocket gameSocket) {
		this.gameSocket = gameSocket;
	}
	public String getResponse() {
		return response;
	}
	public void setResponse(String response) {
		this.response = response;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public ClientGUI getClientGUI() {
		return clientGUI;
	}
	public void setClientGUI(ClientGUI clientGUI) {
		this.clientGUI = clientGUI;
	}
	public GameObjectOutputStream getOutput() {
		return output;
	}
	public void setOutput(GameObjectOutputStream output) {
		this.output = output;
	}
	public GameObjectInputStream getInput() {
		return input;
	}
	public void setInput(GameObjectInputStream input) {
		this.input = input;
	}
	public GameObjectOutputStream getGameOut() {
		return gameOut;
	}
	public void setGameOut(GameObjectOutputStream gameOut) {
		this.gameOut = gameOut;
	}
	public GameObjectInputStream getGameIn() {
		return gameIn;
	}
	public void setGameIn(GameObjectInputStream gameIn) {
		this.gameIn = gameIn;
	}
	public StringTokenizer getMessageToken() {
		return messageToken;
	}
	public void setMessageToken(StringTokenizer messageToken) {
		this.messageToken = messageToken;
	}
	public boolean getQuit() {
		return quit;
	}
	public void setQuit(boolean quit) {
		this.quit = quit;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public int getGamePort() {
		return gamePort;
	}
	public void setGamePort(int gamePort) {
		this.gamePort = gamePort;
	}
	public InetAddress getHost() {
		return host;
	}
	public void setHost(InetAddress host) {
		this.host = host;
	}

	private void reconnectOutSocket(){
		try{
         socket.connect(socket.getSocketAddress());
         output = new GameObjectOutputStream(socket.getOutputStream());
			sendMessage("RESUME"+userName);
      }catch(IOException ioEx){
			System.out.println("IO EXCEPTION getSentObject\n");
			ioEx.printStackTrace();
		}
   }

	private void reconnectInSocket(){
		try{
         socket.connect(socket.getSocketAddress());
			input = new GameObjectInputStream(socket.getInputStream()); 
			sendMessage("RESUME"+userName);
      }catch(IOException ioEx){
			System.out.println("IO EXCEPTION getSentObject\n");
			ioEx.printStackTrace();
		}
   }

	private void reconnectGameOutSocket(){
		try{

         gameSocket.connect(gameSocket.getSocketAddress());
         gameOut = new GameObjectOutputStream(gameSocket.getOutputStream());
			sendMessage("RESUME"+userName);
      }catch(IOException ioEx){
			System.out.println("IO EXCEPTION getSentObject\n");
			ioEx.printStackTrace();
		}
   }

	private void reconnectGameInSocket(){
		try{
         gameSocket.connect(gameSocket.getSocketAddress());
			gameIn = new GameObjectInputStream(gameSocket.getInputStream());
			sendMessage("RESUME"+userName);
      }catch(IOException ioEx){
			System.out.println("IO EXCEPTION getSentObject\n");
			ioEx.printStackTrace();
		}
   }

/*
	private void readObject(java.io.ObjectInputStream stream) throws IOException, ClassNotFoundException{
		try{
System.out.println("read before: "+socket+"\n");
         socket = (GameSocket)stream.readObject();
         gameSocket = (GameSocket)stream.readObject();

         socket.connect(socket.getSocketAddress());
         gameSocket.connect(gameSocket.getSocketAddress());

         userName = stream.readUTF();
System.out.println("read after: "+socket+"\n");
System.out.println("read after: "+userName+"\n");

         output = new GameObjectOutputStream(socket.getOutputStream());
         gameOut = new GameObjectOutputStream(gameSocket.getOutputStream());

			input = new GameObjectInputStream(socket.getInputStream()); 
			gameIn = new GameObjectInputStream(gameSocket.getInputStream());

			sendMessage("RESUME"+userName);
  
      }catch(IOException ioEx){
			System.out.println("IO EXCEPTION getSentObject\n");
			ioEx.printStackTrace();
		}
   }

   private void writeObject(java.io.ObjectOutputStream stream) throws IOException{
		try{
System.out.println("write before: "+socket+"\n");
         stream.writeObject(socket);
         stream.writeObject(gameSocket);
         stream.writeUTF(userName);
System.out.println("write after: "+socket+"\n");
		}
		catch(ConnectException conEx){
			System.out.println("Connection Refused!\n");
			conEx.printStackTrace();
			System.exit(1);
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
 
   private void readObjectNoData() throws ObjectStreamException{
	
	}
*/
}//end class