package gameClient;

import java.net.*;
import java.util.Properties;
import java.io.*;

public class ChatClient{

   private static final long serialVersionUID = 6367753178141774813L;
	
   private static Properties serverProperties;

	private static InetAddress host;
   private static String ipAddress;
	private static int PORT;
	private static int GAME_PORT;
	
   public ChatClient(){}
	
	public static void main(String[] args)
	{
		try{
//			host = InetAddress.getLocalHost();
	      initProperties();
			host = InetAddress.getByName(ipAddress);
		}catch(UnknownHostException uhEx){
			System.out.println("Host ID not found!\n");
			System.exit(1);
		}catch(IOException ioEx){
			System.out.println("Properties file not found!\n");
			System.exit(2);
		}
System.out.println("Init handler\n");
		Handler h = new Handler(host, PORT, GAME_PORT);
	}

	private static void initProperties() throws IOException{
	   FileInputStream propertiesFile = new FileInputStream("server.properties");
  		serverProperties = new Properties();    
		serverProperties.load(propertiesFile);
      
      ipAddress = serverProperties.getProperty("ip");
		PORT = Integer.valueOf(serverProperties.getProperty("port"));
		GAME_PORT = Integer.valueOf(serverProperties.getProperty("gamePort"));
	}

}//end class
