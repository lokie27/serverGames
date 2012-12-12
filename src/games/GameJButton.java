package games;

import java.io.Serializable;
import javax.swing.JButton;
import java.beans.*;

public class GameJButton extends JButton implements Serializable{

   private static final long serialVersionUID = 1854955709164684916L;

   private String stringValue;
   private GameImageIcon icon;
		  
	public GameJButton(){}
	
	public GameJButton(String stringValue){
		super(stringValue);
      this.stringValue = stringValue;
	}

	public GameJButton(GameImageIcon icon){
		super(icon);
		this.icon = icon;
	}

	public GameJButton(String stringValue, GameImageIcon icon){
		super(stringValue, icon);
      this.stringValue = stringValue;
		this.icon = icon;
	}

   public void setStringValue(String stringValue){
	   this.stringValue = stringValue;
	}
   public String getStringValue(){
	   return stringValue;
	}

   public void setIcon(GameImageIcon icon){
	   this.icon = icon;
	}
	public GameImageIcon getIcon(){
	   return icon;
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
