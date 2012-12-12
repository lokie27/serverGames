package games.civ;

import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import games.*;

public class Settler extends JComponent implements Serializable
{
  private static final long serialVersionUID = -8450101602229685195L;

  private GameImageIcon iconSettler;
  private GameJLayeredPane playerSettler;
  private GameJLabel SettlerLabel;
  private int xPos, yPos, moves;
  private JTextArea num;
  private int group;
  
  public Settler(){}//bean default
  
  public Settler(String color, String number)
  {
     group = 0;  
	  num = new JTextArea(number);  
	  num.setOpaque(false); 
     num.setEditable(false);
     num.setForeground(new Color(250, 250, 250));
     Font f = new Font("Monospaced", Font.BOLD, 8);
     num.setFont(f);
     iconSettler = getImage("data/settler"+color+".png");
	  playerSettler = new GameJLayeredPane();
     playerSettler.setOpaque(false);
	  SettlerLabel = new GameJLabel(iconSettler);
	  xPos = 0;
	  yPos = 0;
	  playerSettler.setBounds(0, 0,  
                                  iconSettler.getIconWidth(),
                                  iconSettler.getIconHeight());
     SettlerLabel.setBounds(0, 0,  
                                  iconSettler.getIconWidth(),
                                  iconSettler.getIconHeight());
     num.setBounds(playerSettler.getX()+7, playerSettler.getY(), 15, 15);  
     playerSettler.add(SettlerLabel, new Integer(0));  
     playerSettler.add(num, new Integer(1));  
     moves = 2;
 
     SettlerLabel.setName(number);
System.out.println("Settler NAME "+SettlerLabel.getName());  
 }
  
  public Settler(String color, int m, String number)
  {
     group = 0;  
     num = new JTextArea(number);  
	  num.setOpaque(false); 
     num.setEditable(false);
     num.setForeground(new Color(250, 250, 250));
     Font f = new Font("Monospaced", Font.BOLD, 8);
     num.setFont(f);
     iconSettler = getImage("data/settler"+color+".png");
	  playerSettler = new GameJLayeredPane();
     playerSettler.setOpaque(false);
	  SettlerLabel = new GameJLabel(iconSettler);
	  xPos = 0;
	  yPos = 0;
	  playerSettler.setBounds(00, 00,  
                                  iconSettler.getIconWidth(),
                                  iconSettler.getIconHeight());
     SettlerLabel.setBounds(00, 00,  
                                  iconSettler.getIconWidth(),
                                  iconSettler.getIconHeight());
     num.setBounds(playerSettler.getX()+7, playerSettler.getY(), 15, 15);  
     playerSettler.add(SettlerLabel, new Integer(0));  
     playerSettler.add(num, new Integer(1));  
     moves = m;

     SettlerLabel.setName(number);
System.out.println("Settler NAME "+SettlerLabel.getName());  
  }

	public GameImageIcon getImage(String pathName){
     GameImageIcon image;
     java.net.URL url = getClass().getResource(pathName); 
     try{
		  if(url != null){
		    image = new GameImageIcon(url.toURI());
			 return image;    
	     }
	     else 
	       System.out.println("NULL IMAGE IN CivLAUNCH");       
     }catch(IOException IOEx){
        System.out.println("IOEx NULL IMAGE IN CivLAUNCH");       
        IOEx.printStackTrace();	     
	  }catch(java.net.URISyntaxException URIEx){
        System.out.println("URIEx NULL IMAGE IN CivLAUNCH");       
        URIEx.printStackTrace();	     
	  }
	  return null;
   }
 
  public void setBounds(int num)
  {
    if(num <= 1)
	   playerSettler.setBounds(00, 00,  
                                  iconSettler.getIconWidth(),
                                  iconSettler.getIconHeight());
    if(num == 2)
	   playerSettler.setBounds(25, 00,  
                                  iconSettler.getIconWidth(),
                                  iconSettler.getIconHeight());
    if(num == 3)
	   playerSettler.setBounds(00, 25,  
                                  iconSettler.getIconWidth(),
                                  iconSettler.getIconHeight());
    if(num == 4)
	   playerSettler.setBounds(25, 25,  
                                  iconSettler.getIconWidth(),
                                  iconSettler.getIconHeight());
    if(num == 5)
	   playerSettler.setBounds(45, 00,  
                                  iconSettler.getIconWidth(),
                                  iconSettler.getIconHeight());
    if(num == 6)
	   playerSettler.setBounds(45, 00,  
                                  iconSettler.getIconWidth(),
                                  iconSettler.getIconHeight());
  }
  
  public void setBounds(int x, int y, int w, int h)
  {
    playerSettler.setBounds(x, y, w, h); 
  }
  
  public void takeMove()
  {
    moves--;
  }

  public void resetMoves(Player name)
  {
    moves = name.getMoveLimit();
  }
  
  public int getMoves()
  {
    return moves;
  }

  public void combatMoves()
  {
    moves = 0;
  }

  public void setPosition(int x, int y)
  {
    xPos = x;
	 yPos = y;
  }

  public int getX()
  {
    return xPos;
  }

  public int getY()
  {
    return yPos;
  }

  public GameJLayeredPane getLabel()
  {
    return playerSettler;
  }

  public GameJLabel getSettlerLabel()
  {
    return SettlerLabel;
  }
  
  public JTextArea getNum()
  {
    return num;
  }
    
  public void setNumText(String s)
  {
    num.setText(s);
  }
  
  public GameImageIcon getIcon()
  {
    return iconSettler;
  }

  public int getIconWidth()
  {
    return iconSettler.getIconWidth();
  }

  public int getIconHeight()
  {
    return iconSettler.getIconHeight();
  }

  public int getGroup()
  {
    return group;
  }

  public void setGroup(int g)
  {
    group = g;
  }
  
  public void setColor(Color c)
  {
    num.setForeground(c);
  }

  public Rectangle getCurrentbounds()
  {
    return this.playerSettler.getBounds();
  }

  public String getLabelName()
  {
    return SettlerLabel.getName();
  }
	public GameImageIcon getIconSettler() {
		return iconSettler;
	}
	public void setIconSettler(GameImageIcon iconSettler) {
		this.iconSettler = iconSettler;
	}
	public GameJLayeredPane getPlayerSettler() {
		return playerSettler;
	}
	public void setPlayerSettler(GameJLayeredPane playerSettler) {
		this.playerSettler = playerSettler;
	}
	public void setSettlerLabel(GameJLabel SettlerLabel) {
		this.SettlerLabel = SettlerLabel;
	}
	public int getXPos() {
		return xPos;
	}
	public void setXPos(int xPos) {
		this.xPos = xPos;
	}
	public int getYPos() {
		return yPos;
	}
	public void setYPos(int yPos) {
		this.yPos = yPos;
	}
	public void setMoves(int moves) {
		this.moves = moves;
	}
	public void setNum(JTextArea num) {
		this.num = num;
	}

	private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException{
		try{
			iconSettler = (GameImageIcon)stream.readObject();
			playerSettler = (GameJLayeredPane)stream.readObject();
			SettlerLabel = (GameJLabel)stream.readObject();
			xPos = stream.readInt();
			yPos = stream.readInt();
			moves = stream.readInt();
			num = (JTextArea)stream.readObject();
			group = stream.readInt();
      }catch(IOException ioEx){
			System.out.println("IO EXCEPTION getSentObject\n");
			ioEx.printStackTrace();
		}
   }

   private void writeObject(ObjectOutputStream stream) throws IOException{
		try{
			stream.writeObject(iconSettler);
			stream.writeObject(playerSettler);
			stream.writeObject(SettlerLabel);
			stream.writeInt(xPos);
			stream.writeInt(yPos);
			stream.writeInt(moves);
			stream.writeObject(num);
			stream.writeInt(group);
		}
		catch(java.net.ConnectException conEx){
			System.out.println("Connection Refused!\n");
			conEx.printStackTrace();
			System.exit(1);
		}
		catch(NotSerializableException NSEx){
			System.out.println("NOT SERIALIZABLE\n");
			NSEx.printStackTrace();
		}
		catch(IOException ioEx){
			System.out.println("IO EXCEPTION sendGameCommand\n");
			ioEx.printStackTrace();
		}
	}
 
   private void readObjectNoData() throws ObjectStreamException{}

}