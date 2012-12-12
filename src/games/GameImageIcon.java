package games;

import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.Graphics;

import java.awt.image.*;
import java.net.URI;

import javax.swing.ImageIcon;
import javax.swing.Icon;

import java.beans.*;
import java.io.Serializable;

public class GameImageIcon extends ImageIcon implements Icon, Serializable{

   private static final long serialVersionUID = -6271410522904192959L;

   private URI uri;
	private Icon icon;

   public GameImageIcon(){};

   public GameImageIcon(BufferedImage im){
	   super(im);
	}

   public GameImageIcon(URI uri) throws java.net.MalformedURLException{
		super(uri.toURL());
	   this.uri = uri;
	}

   public void setURI(URI uri){
	   this.uri = uri;
	}
   public URI getURI(){
	   return uri;
	}

   public void setIcon(Icon icon){
	   this.icon = icon;
	}
   public Icon getIcon(){
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

//	@Override
//    public void paintIcon(Component c, Graphics g, int x, int y)
//    {
//		Graphics2D g2 = (Graphics2D)g.create();
//    }

}