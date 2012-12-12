package games;

import java.io.Serializable;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.InetAddress;
import java.beans.*;

public class GameSocket extends Socket implements Serializable{

   private static final long serialVersionUID = -644849837133073287L;

   private InetAddress address;
	private int port;
   private SocketAddress socketAddress;
	
	public GameSocket(){}
	
	public GameSocket(InetAddress address, int port) throws java.io.IOException{
		super(address, port);
      this.address = address;
		this.port = port;
		this.socketAddress = getRemoteSocketAddress();
System.out.println("SocketAddress\n" + socketAddress);
	}

	private void readObject(java.io.ObjectInputStream stream) throws java.io.IOException, ClassNotFoundException{
		try{
         stream.defaultReadObject();
      }catch(java.io.IOException ioEx){
			System.out.println("IO EXCEPTION getSentObject\n");
			ioEx.printStackTrace();
		}
   }

   private void writeObject(java.io.ObjectOutputStream stream) throws java.io.IOException{
		try{
         stream.defaultWriteObject();
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
 
   private void readObjectNoData() throws java.io.ObjectStreamException{
	
	}

   public void setSocketAddress(SocketAddress socketAddress){
	   this.socketAddress = socketAddress;
	}
	public SocketAddress getSocketAddress(){
	   return socketAddress;
	}
	
   public void setAddress(InetAddress address){
	   this.address = address;
	}
   public InetAddress getAddress(){
	   return address;
	}
	
	public void setPort(int port){
	   this.port = port;
	}
	public int getPort(){
	   return port;
	}
}
