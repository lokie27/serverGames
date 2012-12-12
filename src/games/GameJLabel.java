package games;

import java.io.Serializable;
import javax.swing.JLabel;
import java.beans.*;

public class GameJLabel extends JLabel implements Serializable{

   private static final long serialVersionUID = -8057892535427135868L;

   private String stringValue;
	private GameImageIcon icon;
	private GameRotatedIcon rotatedIcon;
	  
	public GameJLabel(){}
	
	public GameJLabel(String stringValue){
		super(stringValue);
      this.stringValue = stringValue;
	}

	public GameJLabel(GameImageIcon icon){
		super(icon);
	   this.icon = icon;
	}

	public GameJLabel(GameRotatedIcon icon){
		super(icon.getIcon());
	   this.rotatedIcon = icon;
	}

	public GameJLabel(String stringValue, GameImageIcon icon, int position){
		super(stringValue, icon, position);
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

   public void setRotatedIcon(GameRotatedIcon rotatedIcon){
	   this.rotatedIcon = rotatedIcon;
	}
   public GameRotatedIcon getRotatedIcon(){
	   return rotatedIcon;
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
