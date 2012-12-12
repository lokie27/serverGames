package games;

import java.io.Serializable;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.beans.*;

public class GameJPanel extends JPanel implements Serializable{

   private static final long serialVersionUID = -8130688050071496952L;

   private Dimension size;
	  
	public GameJPanel(){}
	
	public GameJPanel(Dimension size){
		super();
		setSize(size);
	   this.size = size;
	}

	public GameJPanel(LayoutManager layout){
		super(layout);
	}

   public void setSize(Dimension size){
	   this.size = size;
	}
   public Dimension getSize(){
	   return size;
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
