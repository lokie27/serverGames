package games.civ;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.awt.font.*;
import games.*;
import java.io.Serializable;
//import java.text.*;

public class Army extends JComponent implements Serializable
{
  private static final long serialVersionUID = 702578283861056698L;
 
  private GameImageIcon iconArmy;
  private JLayeredPane playerArmy;
  private JLabel ArmyLabel;
  private int xPos, yPos, moves;
  private JTextArea num;
  private int group;
  private String ArmyColor;
  
  public Army(){}//bean default
  
  public Army(String color, String number)
  {
     group = 0;  
	  ArmyColor = color; 
	  num = new JTextArea(number);  
	  num.setOpaque(false); 
     num.setEditable(false);
     num.setForeground(new Color(250, 250, 250));
     Font f = new Font("Monospaced", Font.BOLD, 8);
     num.setFont(f);
//	  belongPane = new JPanel();  
	  iconArmy = getImage("data/army"+color+".png");
	  playerArmy = new JLayeredPane();
	  playerArmy.setOpaque(false);
	  ArmyLabel = new JLabel(iconArmy);
	  xPos = 0;
	  yPos = 0;
     playerArmy.setBounds(00, 00,  
                                  iconArmy.getIconWidth(),
                                  iconArmy.getIconHeight());
     ArmyLabel.setBounds(00, 00,  
                                  iconArmy.getIconWidth(),
                                  iconArmy.getIconHeight());
     num.setBounds(playerArmy.getX()+5, playerArmy.getY(), 15, 15);  
     playerArmy.add(ArmyLabel, new Integer(0));  
     playerArmy.add(num, new Integer(1));  
	  moves = 2;

     ArmyLabel.setName(number);
//System.out.println("ARMY NAME "+ArmyLabel.getName());  
  }

  public Army(String color, int m, String number)
  {
//     belongPane = new JPanel();  
     group = 0;  
	  ArmyColor = color; 
     num = new JTextArea(number);  
	  num.setOpaque(false); 
     num.setEditable(false);
     num.setForeground(new Color(250, 250, 250));
     Font f = new Font("Monospaced", Font.BOLD, 8);
     num.setFont(f);
	  iconArmy = getImage("data/army"+color+".png");
	  playerArmy = new JLayeredPane();
	  playerArmy.setOpaque(false);
	  ArmyLabel = new JLabel(iconArmy);
	  xPos = 0;
	  yPos = 0;
     playerArmy.setBounds(00, 00,  
                                  iconArmy.getIconWidth(),
                                  iconArmy.getIconHeight());
     ArmyLabel.setBounds(00, 00,  
                                  iconArmy.getIconWidth(),
                                  iconArmy.getIconHeight());
     num.setBounds(playerArmy.getX()+5, playerArmy.getY(), 15, 15);  
     playerArmy.add(ArmyLabel, new Integer(0));  
     playerArmy.add(num, new Integer(1));  
	  moves = m;

     ArmyLabel.setName(number);
//System.out.println("ARMY NAME "+ArmyLabel.getName());  
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
     }catch(java.io.IOException IOEx){
        System.out.println("IOEx NULL IMAGE IN CivLAUNCH");       
        IOEx.printStackTrace();	     
	  }catch(java.net.URISyntaxException URIEx){
        System.out.println("URIEx NULL IMAGE IN CivLAUNCH");       
        URIEx.printStackTrace();	     
	  }
	  return null;
   }
  
  public void setBounds(int numSettlers, int numArmies)
  {
    if(numSettlers == 0)
	 {
		 if(numArmies <= 1)
		   playerArmy.setBounds(00, 00,  
	                                  iconArmy.getIconWidth(),
	                                  iconArmy.getIconHeight());
	    if(numArmies == 2)
		   playerArmy.setBounds(23, 00,  
	                                  iconArmy.getIconWidth(),
	                                  iconArmy.getIconHeight());
	    if(numArmies == 3)
		   playerArmy.setBounds(40, 00,  
	                                  iconArmy.getIconWidth(),
	                                  iconArmy.getIconHeight());
	    if(numArmies == 4)
		   playerArmy.setBounds(00, 20,  
	                                  iconArmy.getIconWidth(),
	                                  iconArmy.getIconHeight());
	    if(numArmies == 5)
		   playerArmy.setBounds(23, 20,  
	                                  iconArmy.getIconWidth(),
	                                  iconArmy.getIconHeight());
	    if(numArmies == 6)
		   playerArmy.setBounds(40, 20,  
	                                  iconArmy.getIconWidth(),
	                                  iconArmy.getIconHeight());
//       num.setBounds(playerArmy.getX()+5, playerArmy.getY(), 15, 15);
	 }
	 else if(numSettlers == 1)
	 {  
	    if(numArmies <= 1)
		   playerArmy.setBounds(23, 00,  
	                                  iconArmy.getIconWidth(),
	                                  iconArmy.getIconHeight());
	    if(numArmies == 2)
		   playerArmy.setBounds(40, 00,  
	                                  iconArmy.getIconWidth(),
	                                  iconArmy.getIconHeight());
	    if(numArmies == 3)
		   playerArmy.setBounds(00, 20,  
	                                  iconArmy.getIconWidth(),
	                                  iconArmy.getIconHeight());
	    if(numArmies == 4)
		   playerArmy.setBounds(23, 20,  
	                                  iconArmy.getIconWidth(),
	                                  iconArmy.getIconHeight());
	    if(numArmies == 5)
		   playerArmy.setBounds(40, 20,  
	                                  iconArmy.getIconWidth(),
	                                  iconArmy.getIconHeight());
//       num.setBounds(playerArmy.getX()+5, playerArmy.getY(), 15, 15);  
	 }
	 else if(numSettlers == 2)
	 {  
	    if(numArmies <= 1)
		   playerArmy.setBounds(40, 00,  
	                                  iconArmy.getIconWidth(),
	                                  iconArmy.getIconHeight());
	    if(numArmies == 2)
		   playerArmy.setBounds(00, 20,  
	                                  iconArmy.getIconWidth(),
	                                  iconArmy.getIconHeight());
	    if(numArmies == 3)
		   playerArmy.setBounds(20, 20,  
	                                  iconArmy.getIconWidth(),
	                                  iconArmy.getIconHeight());
	    if(numArmies == 4)
		   playerArmy.setBounds(40, 20,  
	                                  iconArmy.getIconWidth(),
	                                  iconArmy.getIconHeight());
//       num.setBounds(playerArmy.getX()+5, playerArmy.getY(), 15, 15);  
    }
  
  }

  public void setBounds(int x, int y, int w, int h)
  {
    playerArmy.setBounds(x, y, w, h); 
    playerArmy.repaint();
System.out.println("BOUNDS "+x+" "+y+" "+w+" "+h);  
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

  public JTextArea getNum()
  {
    return num;
  }

  public void setNumText(String s)
  {
    num.setText(s);
  }

  public JLayeredPane getLabel()
  {
    return playerArmy;
  }
  
  public JLabel getArmyLabel()
  {
    return ArmyLabel;
  }

  public GameImageIcon getIcon()
  {
    return iconArmy;
  }

  public int getIconWidth()
  {
    return iconArmy.getIconWidth();
  }

  public int getIconHeight()
  {
    return iconArmy.getIconHeight();
  }

  public int getGroup()
  {
    return group;
  }

  public void setGroup(int group)
  {
    this.group = group;
  }

  public void setColor(Color c)
  {
    num.setForeground(c);
  }

  public Color getTextColor()
  {
    return num.getForeground();
  }

  public String getArmyColor()
  {
    return ArmyColor;
  } 

  public Rectangle getCurrentbounds()
  {
    return this.playerArmy.getBounds();
  }

  public void setArmyColor(String ArmyColor){
     this.ArmyColor = ArmyColor;
  }
  
  public void setRussiaArmyColor(String s)
  {
    ArmyColor = "White";
	 iconArmy = getImage("data/Army"+ArmyColor+".png");
    num.setForeground(new Color(230, 0, 0));
	 ArmyLabel.setIcon(iconArmy);
	 ArmyLabel.repaint();
  } 

  public String getLabelName()
  {
    return ArmyLabel.getName();
  }

	public GameImageIcon getIconArmy() {
		return iconArmy;
	}
	public void setIconArmy(GameImageIcon iconArmy) {
		this.iconArmy = iconArmy;
	}
	public JLayeredPane getPlayerArmy() {
		return playerArmy;
	}
	public void setPlayerArmy(JLayeredPane playerArmy) {
		this.playerArmy = playerArmy;
	}
	public void setArmyLabel(JLabel ArmyLabel) {
		this.ArmyLabel = ArmyLabel;
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

	private void readObject(java.io.ObjectInputStream stream) throws java.io.IOException, ClassNotFoundException{
		try{
			iconArmy = (GameImageIcon)stream.readObject();
			playerArmy = (JLayeredPane)stream.readObject();
			ArmyLabel = (JLabel)stream.readObject();
			xPos = stream.readInt();
			yPos = stream.readInt();
			moves = stream.readInt();
			num = (JTextArea)stream.readObject();
			group = stream.readInt();
			ArmyColor = stream.readUTF();
      }catch(java.io.IOException ioEx){
			System.out.println("IO EXCEPTION getSentObject\n");
			ioEx.printStackTrace();
		}
   }

   private void writeObject(java.io.ObjectOutputStream stream) throws java.io.IOException{
		try{
			stream.writeObject(iconArmy);
			stream.writeObject(playerArmy);
			stream.writeObject(ArmyLabel);
			stream.writeInt(xPos);
			stream.writeInt(yPos);
			stream.writeInt(moves);
			stream.writeObject(num);
			stream.writeInt(group);
			stream.writeUTF(ArmyColor);
		}
		catch(java.net.ConnectException conEx){
			System.out.println("Connection Refused!\n");
			conEx.printStackTrace();
			System.exit(1);
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
 
   private void readObjectNoData() throws java.io.ObjectStreamException{}

}