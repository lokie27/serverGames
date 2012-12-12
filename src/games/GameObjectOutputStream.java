package games;

import java.io.Serializable;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.beans.*;

public class GameObjectOutputStream extends ObjectOutputStream implements Serializable{

   private static final long serialVersionUID = 619852292986182782L;

   private OutputStream out;
//	private java.net.SocketOutputStream test;

   public GameObjectOutputStream() throws java.io.IOException{}
	
	public GameObjectOutputStream(OutputStream out) throws java.io.IOException {
		super(out);
      this.out = out;
	}

   public OutputStream getOut(){
	   return out;
	}
	public void setOut(OutputStream out){
	   this.out = out;
	}
}
