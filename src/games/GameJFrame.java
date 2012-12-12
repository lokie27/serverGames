package games;

import java.io.Serializable;
import javax.swing.JFrame;
import java.beans.*;

public class GameJFrame extends JFrame implements Serializable{

   private static final long serialVersionUID = -4372772685422410948L;

   private String title;
	  
	public GameJFrame(){}
	
	public GameJFrame(String title){
		super(title);
	   this.title = title;
	}

   public void setTitle(String title){
	   this.title = title;
	}
   public String getTitle(){
	   return title;
	}

	private void readObject(java.io.ObjectInputStream stream) throws java.io.IOException, ClassNotFoundException{
		try{
         stream.defaultReadObject();
      }catch(java.io.IOException ioEx){
			System.out.println("IO EXCEPTION readObject\n");
			ioEx.printStackTrace();
		}catch(NullPointerException NEx){
			System.out.println("NullPointerExcepyion readObject\n");
			NEx.printStackTrace();
		}
   }

   private void writeObject(java.io.ObjectOutputStream stream) throws java.io.IOException{
		try{
         stream.defaultWriteObject();
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
	
}
