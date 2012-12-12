package games;

import java.io.Serializable;
import java.io.ObjectInputStream;
import java.io.InputStream;
import java.beans.*;

public class GameObjectInputStream extends ObjectInputStream implements Serializable{

   private static final long serialVersionUID = -6070689227339649100L;

   private InputStream in;
	
   public GameObjectInputStream() throws java.io.IOException{}
	
	public GameObjectInputStream(InputStream in) throws java.io.IOException {
		super(in);
      this.in = in;
	}

   public InputStream getOut(){
	   return in;
	}
	public void setOut(InputStream in){
	   this.in = in;
	}
}
