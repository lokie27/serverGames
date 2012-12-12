package games;

import java.io.Serializable;
import java.io.PrintWriter;
import java.io.OutputStream;
import java.beans.*;

public class GamePrintWriter extends PrintWriter implements Serializable{

   private static final long serialVersionUID = 1656012533629545684L;

   private OutputStream out;
	private boolean autoFlush;

	public GamePrintWriter(OutputStream out, boolean autoFlush){
		super(out, autoFlush);
      this.out = out;
		this.autoFlush = autoFlush;
	}

   public OutputStream getOut(){
	   return out;
	}
	public void setOut(OutputStream out){
	   this.out = out;
	}
	
	public boolean getAutoFlush(){
	   return autoFlush;
	}
	public void setAutoFlush(boolean autoFlush){
	   this.autoFlush = autoFlush;
	}
}
