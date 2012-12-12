package games;

import java.io.Serializable;
import javax.swing.JComboBox;
import java.awt.Dimension;
import java.beans.*;

public class GameJComboBox extends JComboBox implements Serializable{

   private static final long serialVersionUID = -7334739062542598179L;

   private String[] boxValues;
	  
	public GameJComboBox(){}
	
	public GameJComboBox(String[] boxValues){
		super(boxValues);
      this.boxValues = boxValues;
	}

   public void setBoxValues(String[] boxValues){
	   this.boxValues = boxValues;
	}
   public String[] getboxValues(){
	   return boxValues;
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
