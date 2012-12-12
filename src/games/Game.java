package games;

import java.io.*;
import java.net.*;
import java.beans.*;
import java.util.Random;

public class Game implements Serializable{

   private static final long serialVersionUID = -2767988911061104316L;

   private String gameType;
	private String gameName;
	private String creator;
	private Object gameData;
	private int numberPlayers;
	
   public Game(){}
	
   public Game(String gameType, String creator, Object gameData){
	   this.gameType = gameType;
		this.creator = creator;
		gameName = gameType + new Random().nextLong() + creator;
		this.gameData = gameData;
      numberPlayers = 1;
	}
	
	public Game(Game inGame){
	   deepClone(inGame);
	}
	
	private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException{
		try{
         gameType = stream.readUTF();
         gameName = stream.readUTF();
         creator = stream.readUTF();
         numberPlayers = stream.readInt(); 
         gameData = stream.readObject();
      }catch(IOException ioEx){
			System.out.println("IO EXCEPTION getSentObject\n");
			ioEx.printStackTrace();
		}
   }

   private void writeObject(ObjectOutputStream stream) throws IOException{
		try{
         stream.writeUTF(gameType);
         stream.writeUTF(gameName);
         stream.writeUTF(creator);
         stream.writeInt(numberPlayers); 
         stream.writeObject(gameData);
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
 
   private void readObjectNoData() throws ObjectStreamException{}

	public void deepClone(Game inGame){
	   this.gameType = inGame.gameType;
      this.gameName = inGame.gameName;
		this.creator = inGame.creator;
		this.gameData = inGame.gameData;
		this.numberPlayers = inGame.numberPlayers;
	}
	
	public void incrementNumberPlayers(){
	   numberPlayers++;
	}
	
	public void decrementNumberPlayers(){
	   numberPlayers--;
	}
	
	public void setGameName(String gameName){
	   this.gameName = gameName;
	}
   public String getGameName(){
	   return gameName;
	}

	public void setGameType(String gameType){
	   this.gameType = gameType;
	}
   public String getGameType(){
	   return gameType;
	}

   public void setCreator(String creator){
	   this.creator = creator;
	}
   public String getCreator(){
	   return creator;
	}

   public void setGameData(Object gameData){
	   this.gameData = gameData;
	}
   public Object getGameData(){
	   return gameData;
	}

   public void setNumberPlayers(int numberPlayers){
	   this.numberPlayers = numberPlayers;
	}
	public int getNumberPlayers(){
	   return numberPlayers;
	}
}