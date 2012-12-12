package games.civ;

import java.io.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.Vector;
import java.beans.*;
import javax.swing.border.*;
import games.*;

public class MoveGUI extends JComponent implements ActionListener, MouseListener, KeyListener
{
  boolean exit = false;
  Color color;
  JInternalFrame internal, select;
  JButton done, lt, up, rt, dw, turnOver, disband,
          group, groupDone, move, groupMove;
  JButton[] nm;

  JLabel[] nmHolder, singleUnit;

  JLayeredPane[] nmPane;
  JButton[][] gm;
  JLabel[][] groupHolder;
  JLabel[][] groupIcons;

  JLabel[][] groupSettler;
  JLabel[][] groupArmy;

  JLayeredPane[][] groupPane;
  JLayeredPane unitHolder;
  JCheckBox[] groupCheck;
//  JPanel noneHolder;
  int movesLeft, totalMoves, size, sourcePeice, targetPeice, groupSource, singleCount, selectionCount,
      num0group, num1group, num2group, num3group, groupInt; 
  Vector movePeices, moveEnd, groupPeices, noGrp, grp1, grp2, grp3, grp4;
  Vector labelArmyVector, labelSettVector;
  Object groupObject;
  Player name;
  LogicBoard lb;
  JTextArea showMoves;
  static boolean playerDone;
  static Army combatUnit;
  Combat combat;
  
  public MoveGUI(JComponent frame, Player pName, LogicBoard lbSet)
  {
	 labelArmyVector = new Vector(0, 1);
	 labelSettVector = new Vector(0, 1);

	 combatUnit = null;
	 playerDone = false;
	 name = pName;
	 lb = lbSet;
	 
    movePeices = new Vector(0, 1);
    moveEnd = new Vector(0, 1);

    Player[] tempArray = name.getPlayerUI().getPlayers();
	  
 	 int k = 0, j = 0, p = 0;
	 if(!name.settlers.isEmpty())
	 {
	   for(; k < name.settlers.size(); k++)
	   {
	     Settler s = (Settler)name.settlers.get(k);
		  movePeices.add(s);
        moveEnd.add(s);
		  for(int m = 0; m < 2; m++)//number of players
		  {
		    tempArray[m].playerUI.lb.setOwner(s.getX(), s.getY(), name);  
		  }
		} 
		if(!name.armies.isEmpty())  
		{
		  for(; j < name.armies.size(); j++, k++)
	     {
	       Army a = (Army)name.armies.get(j);
		    movePeices.add(a);
          moveEnd.add(a);
			 for(int m = 0; m < 2; m++)//number of players
			 {
			   tempArray[m].playerUI.lb.setOwner(a.getX(), a.getY(), name);  
			 }
//	       name.playerUI.lb.setOwner(a.getX(), a.getY(), name);  
	     }
		}
	 }  
	 else if(!name.armies.isEmpty())  
	 {
		for(; k < name.armies.size(); k++)
	   {
	     Army a = (Army)name.armies.get(k);
		  movePeices.add(a);
        moveEnd.add(a);
		  for(int m = 0; m < 2; m++)//number of players
		  {
		    tempArray[m].playerUI.lb.setOwner(a.getX(), a.getY(), name);  
		  }
//	     name.playerUI.lb.setOwner(a.getX(), a.getY(), name);  
	   }
	 }

//System.out.println("MOVESP "+movePeices.size());
    if(name.getColor().equals("Red"))
      color = new Color(255, 255, 255);
    else if(name.getColor().equals("Yellow"))
      color = new Color(50, 50, 50);
    else if(name.getColor().equals("Blue"))
      color = new Color(255, 255, 255);
    else if(name.getColor().equals("Green"))
      color = new Color(255, 255, 255);

    frame.removeAll();  
	 paintGUI(frame);
//should be player queue set up from CivGUI play while loop
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

  public void paintGUI(JComponent frame)
  {
   if(!true)//name.getNation().equals(CivGUI.aiChoice))
   {
//System.out.println("AI MOVEGUI");
//     System.out.println(name.getNation()+"is MOVE HERE!");
//	  if(!name.getFirstTurn())
//	    playerWait();  
//     CivGUI.turn.researchTurn(name);
	}
	else
	{
	 for(int k = 0; k < name.getNumGroups(); k++)
	 {
	   name.resetGrouptotal(k+1);
	 }	

	 select = new JInternalFrame("Choose unit to move");  

    if(name.getColor().equals("Red"))
		select.setBackground(new Color(255, 0, 0));    
    else if(name.getColor().equals("Yellow"))
	   select.setBackground(new Color(255, 255, 0));    
    else if(name.getColor().equals("Blue"))
		select.setBackground(new Color(0, 0, 255));    
    else if(name.getColor().equals("Green"))
	   select.setBackground(new Color(0, 255, 0)); 

	 select.setBorder(new BevelBorder(1, new Color(0, 0, 0), new Color(255, 255, 255)));
    select.setPreferredSize(new Dimension(198, 198));

	 done = new JButton("Done");
    done.setSize(new Dimension(40, 20));
	 done.addActionListener(this);  
		 
    nm = new JButton[movePeices.size()];  
    nmHolder = new JLabel[movePeices.size()];  
    nmPane = new JLayeredPane[movePeices.size()];
   
	 singleUnit = new JLabel[movePeices.size()];
	 groupSettler = new JLabel[4][name.getStacklimit()];

    if(name.getNumGroups() == 0)
      gm = new JButton[1][1];
	 else
	   gm = new JButton[name.getNumGroups()][name.getStacklimit()];

    groupHolder = new JLabel[4][name.getStacklimit()];
    groupIcons = new JLabel[4][name.getStacklimit()];
    groupPane = new JLayeredPane[4][name.getStacklimit()];
    gm = new JButton[4][name.getStacklimit()];

	 unitHolder = new JLayeredPane();
	 Font f = new Font("Monospaced", Font.BOLD, 8);
    
    grp1 = new Vector(0, 1);
    grp2 = new Vector(0, 1);
    grp3 = new Vector(0, 1);
    grp4 = new Vector(0, 1);
	 noGrp = new Vector(0, 1);

	 int groupCount = 0;
	 singleCount = 0;  

    int i, m;  
    for(i = 0; i < name.getNumGroups(); i++)
	 {
	    groupPane[i][0] = new JLayeredPane();
	 }

	 num0group = 0;
	 num1group = 0;
	 num2group = 0;
	 num3group = 0;

    groupPeices = new Vector(0, 1);
	 
	 for(int k = 0; k < movePeices.size(); k++)
	 {
	   if(movePeices.get(k).getClass().getName().equals("Settler"))
		{
		  Settler s = (Settler)movePeices.get(k);
		  if(s.getMoves() > 0)
		  {
			  switch(s.getGroup())
			  {
	          case 0:
				   noGrp.add(s);  
					break;		  
	          case 1:
				   grp1.add(s);  
					break;		  
	          case 2:
				   grp2.add(s);  
					break;		  
	          case 3:
				   grp3.add(s);  
					break;		  
	          case 4:
				   grp4.add(s);  
					break;		  
			  }
        }
		}
	 }
	 for(int k = 0; k < movePeices.size(); k++)
	 {
	   if(movePeices.get(k).getClass().getName().equals("Army"))
		{
		  Army a = (Army)movePeices.get(k);
		  if(a.getMoves() > 0)
		  {
			  switch(a.getGroup())
			  {
	          case 0:
				   noGrp.add(a);  
					break;		  
	          case 1:
				   grp1.add(a);  
					break;		  
	          case 2:
				   grp2.add(a);  
					break;		  
	          case 3:
				   grp3.add(a);  
					break;		  
	          case 4:
				   grp4.add(a);  
					break;		  
			  }
        }
		}
	 }
  
    int xSet = 0;
	 int ySet = 0;
	 	 
    for(i = 0; i < noGrp.size(); i++)
	 {
	   if(noGrp.get(i).getClass().getName().equals("Settler"))  
		{
		     Settler s = (Settler)noGrp.get(i); 
	        nmPane[i] = new JLayeredPane();        
	 		  nm[i] = new JButton(s.getIcon());
	        nm[i].setBounds(10, 10, 25, 25);  
			  nm[i].setBackground(new Color(0, 0, 0));
			  nmHolder[i] = new JLabel(name.playerUI.lb.getIcon(s.getX(), s.getY()));  

           singleUnit[i] = s.getSettlerLabel();
           singleUnit[i].addMouseListener(this);
			  
			  nmHolder[i].setSize(new Dimension(55, 55));
           nmHolder[i].setBounds(0, 0, 55, 55);
			  nmPane[i].add(nmHolder[i], new Integer(0));    
	        nmPane[i].add(nm[i], new Integer(1));
	        JTextArea num = new JTextArea();
			  num.setOpaque(false); 
		     num.setEditable(false);
		     num.setForeground(color);
		     num.setFont(f);
			  num.setText(s.getNum().getText());
		     num.setBounds(20, 12, 10, 10);
			  nmPane[i].add(num, new Integer(2));    
			  nm[i].addActionListener(this);  
           singleCount++;
           
			  int xpos=0, ypos=0;
			  switch(xSet)
			  {
			    case 0:
               xpos = 00; 				   
					break;
			    case 1:
               xpos = 45; 				   
				   break;
			    case 2:
               xpos = 90; 				   
				   break;
			    case 3:
               xpos = 135; 				   
				   break;
			    case 4:
               xpos = 00; 				   
				   break;
			    case 5:
               xpos = 45; 				   
				   break;
			    case 6:
               xpos = 90; 				   
				   break;
			    case 7:
               xpos = 135; 				   
				   break;
			    case 8:
               xpos = 00; 				   
				   break;
			  }
			  switch(ySet)
			  {
			    case 0:
               ypos = 00;
				   break;
			    case 1:
               ypos = 00;
				   break;
			    case 2:
               ypos = 00;
				   break;
			    case 3:
               ypos = 00;
				   break;
			    case 4:
               ypos = 45;
				   break;
			    case 5:
               ypos = 45;
				   break;
			    case 6:
               ypos = 45;
				   break;
			    case 7:
               ypos = 45;
				   break;
			    case 8:
               ypos = 90;
				   break;
			  }
           nmPane[i].setBounds(xpos, ypos, 45, 45);
           unitHolder.add(nmPane[i], new Integer(0));
           xSet++;
			  ySet++;
       }
	    else
		 {
		     Army a = (Army)noGrp.get(i); 
	        nmPane[i] = new JLayeredPane();        
			  nm[i] = new JButton(a.getIcon());
	        nm[i].setBounds(10, 10, 25, 25);  
			  nm[i].setBackground(new Color(0, 0, 0));
			  nmHolder[i] = new JLabel(name.playerUI.lb.getIcon(a.getX(), a.getY()));  

           singleUnit[i] = a.getArmyLabel();
           singleUnit[i].addMouseListener(this);

			  nmHolder[i].setSize(new Dimension(55, 55));
           nmHolder[i].setBounds(0, 0, 55, 55);
			  nmPane[i].add(nmHolder[i], new Integer(0));    
	        nmPane[i].add(nm[i], new Integer(1));
	        JTextArea num = new JTextArea();
			  num.setOpaque(false); 
		     num.setEditable(false);
           if(a.getArmyColor().equals("White"))
		       num.setForeground(new Color(250, 0, 0));
			  else
		       num.setForeground(color);
		     num.setFont(f);
			  num.setText(a.getNum().getText());
		     num.setBounds(22, 12, 10, 10);
			  nmPane[i].add(num, new Integer(2));    
			  nm[i].addActionListener(this);  
           singleCount++;
			  int xpos=0, ypos=0;
			  switch(xSet)
			  {
			    case 0:
               xpos = 00; 				   
					break;
			    case 1:
               xpos = 45; 				   
				   break;
			    case 2:
               xpos = 90; 				   
				   break;
			    case 3:
               xpos = 135; 				   
				   break;
			    case 4:
               xpos = 00; 				   
				   break;
			    case 5:
               xpos = 45; 				   
				   break;
			    case 6:
               xpos = 90; 				   
				   break;
			    case 7:
               xpos = 135; 				   
				   break;
			    case 8:
               xpos = 00; 				   
				   break;
			  }
			  switch(ySet)
			  {
			    case 0:
               ypos = 00;
				   break;
			    case 1:
               ypos = 00;
				   break;
			    case 2:
               ypos = 00;
				   break;
			    case 3:
               ypos = 00;
				   break;
			    case 4:
               ypos = 45;
				   break;
			    case 5:
               ypos = 45;
				   break;
			    case 6:
               ypos = 45;
				   break;
			    case 7:
               ypos = 45;
				   break;
			    case 8:
               ypos = 90;
				   break;
			  }
           nmPane[i].setBounds(xpos, ypos, 45, 45);
           unitHolder.add(nmPane[i], new Integer(0));
           xSet++;
			  ySet++;
       }
    }		 

    for(i = 0; i < grp1.size(); i++)
	 {
	   if(grp1.get(i).getClass().getName().equals("Settler"))  
		{
			Settler s = (Settler)grp1.get(i);
			
         groupCount++;
			name.addGrouptotal(s.getGroup());
		   groupHolder[0][i] = new JLabel(name.playerUI.lb.getIcon(s.getX(), s.getY()));  
         groupHolder[0][i].setBounds(0+(getCurrentNum(0)*30), 0, 30, 45);
			groupIcons[0][i] = new JLabel(s.getIcon());  
         groupIcons[0][i].setBounds(5+(getCurrentNum(0)*30), 0, 20, 20);
	      groupPane[0][0].add(groupHolder[0][i], new Integer(1)); 
         groupPane[0][0].add(groupIcons[0][i], new Integer(2));

         groupSettler[0][i] = s.getSettlerLabel();
         groupSettler[0][i].addMouseListener(this);
//         groupPeices.add(s);
//System.out.println("added Settler group holder "+(s.getGroup()-1));            
			
         JTextArea num = new JTextArea();
			num.setOpaque(false); 
		   num.setEditable(false);
		   num.setForeground(color);
		   num.setFont(f);
			num.setText(s.getNum().getText());
		   num.setBounds(13+(getCurrentNum(0)*29), 0, 10, 10);
         groupPane[0][0].add(num, new Integer(3));

			addCurrentNum(0);
       }
		 else
		 {
         Army a = (Army)grp1.get(i);
			
         groupCount++;
			name.addGrouptotal(a.getGroup());
			groupHolder[0][i] = new JLabel(name.playerUI.lb.getIcon(a.getX(), a.getY()));  
         groupHolder[0][i].setBounds(0+(getCurrentNum(0)*30), 0, 30, 45);
			groupIcons[0][i] = new JLabel(a.getIcon());  
         groupIcons[0][i].setBounds(5+(getCurrentNum(0)*30), 0, 20, 20);
	      groupPane[0][0].add(groupHolder[a.getGroup()-1][i], new Integer(1)); 
         groupPane[0][0].add(groupIcons[a.getGroup()-1][i], new Integer(2));

         groupSettler[0][i] = a.getArmyLabel();
         groupSettler[0][i].addMouseListener(this);
//         groupPeices.add(a);
//System.out.println("added Army group holder "+(a.getGroup()-1));            

         JTextArea num = new JTextArea();
			num.setOpaque(false); 
		   num.setEditable(false);
         if(a.getArmyColor().equals("White"))
	        num.setForeground(new Color(250, 0, 0));
		   else
		     num.setForeground(color);
		   num.setFont(f);
			num.setText(a.getNum().getText());
		   num.setBounds(13+(getCurrentNum(0)*30), 0, 10, 10);
         groupPane[0][0].add(num, new Integer(3));

			addCurrentNum(0);
      }  
    }
//       			gm[a.getGroup()-1][0] = new JButton();
//		 			Insets in = new Insets(2, 2, 2, 2);
//       			gm[a.getGroup()-1][0].setMargin(in);
//	    			gm[a.getGroup()-1][0].setFont(f);
//					gm[a.getGroup()-1][0].setBounds(10*count, 30, 25*(count), 15);
//		         gm[a.getGroup()-1][0].setText("Group "+ Integer.toString(a.getGroup()));//+ Integer.toString(a.getGroup()));
//		         groupPane[a.getGroup()-1][0].add(gm[a.getGroup()-1][0], new Integer(a.getGroup()+10));      
//			      gm[a.getGroup()-1][0].addActionListener(this);
//		         groupPane[a.getGroup()-1][0].setPreferredSize(new Dimension(55*count, 55));      
//				groupPane[a.getGroup()-1][0].setBounds(00, 00, 55*count, 55);
//System.out.println("xSET, Yset " +  xSet + ySet);
			if(grp1.size() > 0)  
			{
			  int xpos=0, ypos=0;
			  switch(xSet)
			  {
			    case 0:
					switch(grp1.size())
					{
					  case 2:
                   xpos = 00; 				   
					    break;
					  case 3:
	                xpos = 00; 				   
					    break;
					  case 4:
	                xpos = 00; 				   
					    break;
					  case 5:
	                xpos = 00; 				   
					    break;
					  case 6:
	                xpos = 00; 				   
					    break;
					}
					break;
			    case 1:
					switch(grp1.size())
					{
					  case 2:
                   xpos = 45; 				   
					    break;
					  case 3:
	                xpos = 45; 				   
					    break;
					  case 4:
	                xpos = 45; 				   
					    break;
					  case 5:
	                xpos = 00; 				   
					    break;
					  case 6:
	                xpos = 00; 				   
					    break;
					}
				   break;
			    case 2:
					switch(grp1.size())
					{
					  case 2:
                   xpos = 90; 				   
					    break;
					  case 3:
	                xpos = 90; 				   
					    break;
					  case 4:
	                xpos = 00; 				   
					    break;
					  case 5:
	                xpos = 00; 				   
					    break;
					  case 6:
	                xpos = 00; 				   
					    break;
					}
				   break;
			    case 3:
					switch(grp1.size())
					{
					  case 2:
                   xpos = 135; 				   
					    break;
					  case 3:
	                xpos = 135; 				   
					    break;
					  case 4:
	                xpos = 00; 				   
					    break;
					  case 5:
	                xpos = 00; 				   
					    break;
					  case 6:
	                xpos = 00; 				   
					    break;
					}
				   break;
			    case 4:
					switch(grp1.size())
					{
					  case 2:
                   xpos = 00; 				   
					    break;
					  case 3:
	                xpos = 00; 				   
					    break;
					  case 4:
	                xpos = 00; 				   
					    break;
					  case 5:
	                xpos = 00; 				   
					    break;
					  case 6:
	                xpos = 00; 				   
					    break;
					}
				   break;
			    case 5:
					switch(grp1.size())
					{
					  case 2:
                   xpos = 45; 				   
					    break;
					  case 3:
	                xpos = 45; 				   
					    break;
					  case 4:
	                xpos = 45; 				   
					    break;
					  case 5:
	                xpos = 45; 				   
					    break;
					  case 6:
	                xpos = 00; 				   
					    break;
					}
				   break;
			    case 6:
					switch(grp1.size())
					{
					  case 2:
                   xpos = 90; 				   
					    break;
					  case 3:
	                xpos = 90; 				   
					    break;
					  case 4:
	                xpos = 00; 				   
					    break;
					  case 5:
	                xpos = 00; 				   
					    break;
					  case 6:
	                xpos = 00; 				   
					    break;
					}
				   break;
			    case 7:
					switch(grp1.size())
					{
					  case 2:
                   xpos = 00; 				   
					    break;
					  case 3:
	                xpos = 00; 				   
					    break;
					  case 4:
	                xpos = 00; 				   
					    break;
					  case 5:
	                xpos = 00; 				   
					    break;
					  case 6:
	                xpos = 00; 				   
					    break;
					}
				   break;
			  }
			  switch(ySet)
			  {
			    case 0:
					switch(grp1.size())
					{
					  case 2:
                   ypos = 00; 				   
					    break;
					  case 3:
	                ypos = 00; 				   
					    break;
					  case 4:
	                ypos = 00; 				   
					    break;
					  case 5:
	                ypos = 00; 				   
					    break;
					  case 6:
	                ypos = 00; 				   
					    break;
					}
				   break;
			    case 1:
					switch(grp1.size())
					{
					  case 2:
                   ypos = 00; 				   
					    break;
					  case 3:
	                ypos = 00; 				   
					    break;
					  case 4:
	                ypos = 00; 				   
					    break;
					  case 5:
	                ypos = 45; 				   
					    break;
					  case 6:
	                ypos = 45; 				   
					    break;
               }
				   break;
			    case 2:
					switch(grp1.size())
					{
					  case 2:
                   ypos = 00; 				   
					    break;
					  case 3:
	                ypos = 00; 				   
					    break;
					  case 4:
	                ypos = 45; 				   
					    break;
					  case 5:
	                ypos = 45; 				   
					    break;
					  case 6:
	                ypos = 45; 				   
					    break;
				   }  
					break;
				 case 3:
					switch(grp1.size())
					{
					  case 2:
                   ypos = 00; 				   
					    break;
					  case 3:
	                ypos = 00; 				   
					    break;
					  case 4:
	                ypos = 45; 				   
					    break;
					  case 5:
	                ypos = 45; 				   
					    break;
					  case 6:
	                ypos = 45; 				   
					    break;
               }
				   break;
			    case 4:
					switch(grp1.size())
					{
					  case 2:
                   ypos = 45; 				   
					    break;
					  case 3:
	                ypos = 45; 				   
					    break;
					  case 4:
	                ypos = 45; 				   
					    break;
					  case 5:
	                ypos = 45; 				   
					    break;
					  case 6:
	                ypos = 45; 				   
					    break;
               }
				   break;
			    case 5:
					switch(grp1.size())
					{
					  case 2:
                   ypos = 45; 				   
					    break;
					  case 3:
	                ypos = 45; 				   
					    break;
					  case 4:
	                ypos = 45; 				   
					    break;
					  case 5:
	                ypos = 45; 				   
					    break;
					  case 6:
	                ypos = 90; 				   
					    break;
               }
				   break;
			    case 6:
					switch(grp1.size())
					{
					  case 2:
                   ypos = 45; 				   
					    break;
					  case 3:
	                ypos = 45; 				   
					    break;
					  case 4:
	                ypos = 90; 				   
					    break;
					  case 5:
	                ypos = 90; 				   
					    break;
					  case 6:
	                ypos = 90; 				   
					    break;
               } 
				   break;
			    case 7:
					switch(grp1.size())
					{
					  case 2:
                   ypos = 90; 				   
					    break;
					  case 3:
	                ypos = 90; 				   
					    break;
					  case 4:
	                ypos = 90; 				   
					    break;
					  case 5:
	                ypos = 90; 				   
					    break;
					  case 6:
	                ypos = 90; 				   
					    break;
               }
				   break;
			  }
         
	      gm[0][0] = new JButton();
			Insets in = new Insets(2, 2, 2, 2);
	      gm[0][0].setMargin(in);
		   gm[0][0].setFont(f);
		   gm[0][0].setBounds(3*grp1.size(), 30, 23*grp1.size(), 12);
         gm[0][0].setText("Group 1");//+ Integer.toString(s.getGroup()));
         groupPane[0][0].add(gm[0][0], new Integer(10));      
	      gm[0][0].addActionListener(this);
         gm[0][0].setDefaultCapable(false);
			 
			groupPane[0][0].setBounds(xpos, ypos, 30*grp1.size(), 45);
//System.out.println("GROUP1: "+grp1.size());
			unitHolder.add(groupPane[0][0], new Integer(0));
  
         xSet += grp1.size();
         ySet += grp1.size();
		}
		
    for(i = 0; i < grp2.size(); i++)
	 {
	   if(grp2.get(i).getClass().getName().equals("Settler"))  
		{
			Settler s = (Settler)grp2.get(i);
			
         groupCount++;
			name.addGrouptotal(s.getGroup());
		   groupHolder[1][i] = new JLabel(name.playerUI.lb.getIcon(s.getX(), s.getY()));  
         groupHolder[1][i].setBounds(0+(getCurrentNum(1)*30), 0, 30, 45);
			groupIcons[1][i] = new JLabel(s.getIcon());  
         groupIcons[1][i].setBounds(5+(getCurrentNum(1)*30), 0, 20, 20);
	      groupPane[1][0].add(groupHolder[1][i], new Integer(1)); 
         groupPane[1][0].add(groupIcons[1][i], new Integer(2));

         groupSettler[1][i] = s.getSettlerLabel();
         groupSettler[1][i].addMouseListener(this);
//         groupPeices.add(s);
//System.out.println("added Settler group holder "+(s.getGroup()-1));            
			
         JTextArea num = new JTextArea();
			num.setOpaque(false); 
		   num.setEditable(false);
		   num.setForeground(color);
		   num.setFont(f);
			num.setText(s.getNum().getText());
		   num.setBounds(13+(getCurrentNum(1)*29), 0, 10, 10);
         groupPane[1][0].add(num, new Integer(3));

			addCurrentNum(1);
       }
		 else
		 {
         Army a = (Army)grp2.get(i);
			
         groupCount++;
			name.addGrouptotal(a.getGroup());
			groupHolder[1][i] = new JLabel(name.playerUI.lb.getIcon(a.getX(), a.getY()));  
         groupHolder[1][i].setBounds(0+(getCurrentNum(1)*30), 0, 30, 45);
			groupIcons[1][i] = new JLabel(a.getIcon());  
         groupIcons[1][i].setBounds(5+(getCurrentNum(1)*30), 0, 20, 20);
	      groupPane[1][0].add(groupHolder[a.getGroup()-1][i], new Integer(1)); 
         groupPane[1][0].add(groupIcons[a.getGroup()-1][i], new Integer(2));

         groupSettler[1][i] = a.getArmyLabel();
         groupSettler[1][i].addMouseListener(this);
//         groupPeices.add(a);
//System.out.println("added Army group holder "+(a.getGroup()-1));            

         JTextArea num = new JTextArea();
			num.setOpaque(false); 
		   num.setEditable(false);
         if(a.getArmyColor().equals("White"))
	        num.setForeground(new Color(250, 0, 0));
		   else
		     num.setForeground(color);
		   num.setFont(f);
			num.setText(a.getNum().getText());
		   num.setBounds(13+(getCurrentNum(1)*30), 0, 10, 10);
         groupPane[1][0].add(num, new Integer(3));

			addCurrentNum(1);
      }  
    }
//System.out.println("xSET, Yset " +  xSet + ySet);
			if(grp2.size() > 0)  
			{
			  int xpos=0, ypos=0;
			  switch(xSet)
			  {
			    case 1:
					switch(grp2.size())
					{
					  case 2:
                   xpos = 45; 				   
					    break;
					  case 3:
	                xpos = 45; 				   
					    break;
					  case 4:
	                xpos = 45; 				   
					    break;
					  case 5:
	                xpos = 45; 				   
					    break;
					  case 6:
	                xpos = 00; 				   
					    break;
					}
				   break;
			    case 2:
					switch(grp2.size())
					{
					  case 2:
                   xpos = 90; 				   
					    break;
					  case 3:
	                xpos = 90; 				   
					    break;
					  case 4:
	                xpos = 90; 				   
					    break;
					  case 5:
	                xpos = 00; 				   
					    break;
					  case 6:
	                xpos = 00; 				   
					    break;
					}
				   break;
			    case 3:
					switch(grp2.size())
					{
					  case 2:
                   xpos = 135; 				   
					    break;
					  case 3:
	                xpos = 00; 				   
					    break;
					  case 4:
	                xpos = 00; 				   
					    break;
					  case 5:
	                xpos = 00; 				   
					    break;
					  case 6:
	                xpos = 00; 				   
					    break;
					}
				   break;
			    case 4:
					switch(grp2.size())
					{
					  case 2:
                   xpos = 00; 				   
					    break;
					  case 3:
	                xpos = 00; 				   
					    break;
					  case 4:
	                xpos = 00; 				   
					    break;
					  case 5:
	                xpos = 00; 				   
					    break;
					  case 6:
	                xpos = 00; 				   
					    break;
					}
				   break;
			    case 5:
					switch(grp2.size())
					{
					  case 2:
                   xpos = 45; 				   
					    break;
					  case 3:
	                xpos = 45; 				   
					    break;
					  case 4:
	                xpos = 45; 				   
					    break;
					  case 5:
	                xpos = 45; 				   
					    break;
					  case 6:
	                xpos = 00; 				   
					    break;
					}
				   break;
			    case 6:
					switch(grp2.size())
					{
					  case 2:
                   xpos = 60; 				   
					    break;
					  case 3:
	                xpos = 60; 				   
					    break;
					  case 4:
	                xpos = 60; 				   
					    break;
					  case 5:
	                xpos = 00; 				   
					    break;
					  case 6:
	                xpos = 00; 				   
					    break;
					}
				   break;
			    case 7:
					switch(grp2.size())
					{
					  case 2:
                   xpos = 00; 				   
					    break;
					  case 3:
	                xpos = 00; 				   
					    break;
					  case 4:
	                xpos = 00; 				   
					    break;
					  case 5:
	                xpos = 00; 				   
					    break;
					  case 6:
	                xpos = 00; 				   
					    break;
					}
				   break;
			  }
			  switch(ySet)
			  {
			    case 1:
					switch(grp2.size())
					{
					  case 2:
                   ypos = 00; 				   
					    break;
					  case 3:
	                ypos = 00; 				   
					    break;
					  case 4:
	                ypos = 00; 				   
					    break;
					  case 5:
	                ypos = 00; 				   
					    break;
					  case 6:
	                ypos = 45; 				   
					    break;
				   }  
					break;
			    case 2:
					switch(grp2.size())
					{
					  case 2:
                   ypos = 00; 				   
					    break;
					  case 3:
	                ypos = 00; 				   
					    break;
					  case 4:
	                ypos = 00; 				   
					    break;
					  case 5:
	                ypos = 45; 				   
					    break;
					  case 6:
	                ypos = 45; 				   
					    break;
				   }  
					break;
				 case 3:
					switch(grp2.size())
					{
					  case 2:
                   ypos = 00; 				   
					    break;
					  case 3:
	                ypos = 45; 				   
					    break;
					  case 4:
	                ypos = 45; 				   
					    break;
					  case 5:
	                ypos = 45; 				   
					    break;
					  case 6:
	                ypos = 45; 				   
					    break;
               }
				   break;
			    case 4:
					switch(grp2.size())
					{
					  case 2:
                   ypos = 45; 				   
					    break;
					  case 3:
	                ypos = 45; 				   
					    break;
					  case 4:
	                ypos = 45; 				   
					    break;
					  case 5:
	                ypos = 45; 				   
					    break;
					  case 6:
	                ypos = 45; 				   
					    break;
               }
				   break;
			    case 5:
					switch(grp2.size())
					{
					  case 2:
                   ypos = 45; 				   
					    break;
					  case 3:
	                ypos = 45; 				   
					    break;
					  case 4:
	                ypos = 45; 				   
					    break;
					  case 5:
	                ypos = 45; 				   
					    break;
					  case 6:
	                ypos = 90; 				   
					    break;
               }
				   break;
			    case 6:
					switch(grp2.size())
					{
					  case 2:
                   ypos = 45; 				   
					    break;
					  case 3:
	                ypos = 45; 				   
					    break;
					  case 4:
	                ypos = 45; 				   
					    break;
					  case 5:
	                ypos = 90; 				   
					    break;
					  case 6:
	                ypos = 90; 				   
					    break;
               } 
				   break;
			    case 7:
					switch(grp2.size())
					{
					  case 2:
                   ypos = 90; 				   
					    break;
					  case 3:
	                ypos = 90; 				   
					    break;
					  case 4:
	                ypos = 90; 				   
					    break;
					  case 5:
	                ypos = 90; 				   
					    break;
					  case 6:
	                ypos = 90; 				   
					    break;
               }
				   break;
			  }
         
	      gm[1][0] = new JButton();
			Insets in = new Insets(2, 2, 2, 2);
	      gm[1][0].setMargin(in);
		   gm[1][0].setFont(f);
		   gm[1][0].setBounds(3*grp2.size(), 30, 23*grp2.size(), 12);
         gm[1][0].setText("Group 2");//+ Integer.toString(s.getGroup()));
         groupPane[1][0].add(gm[1][0], new Integer(10));      
	      gm[1][0].addActionListener(this);
         gm[1][0].setDefaultCapable(false);
			 
			groupPane[1][0].setBounds(xpos, ypos, 30*grp2.size(), 45);
//System.out.println("GROUP2: "+grp2.size());
			unitHolder.add(groupPane[1][0], new Integer(0));
  
         xSet += grp2.size();
         ySet += grp2.size();
		}

    for(i = 0; i < grp3.size(); i++)
	 {
	   if(grp3.get(i).getClass().getName().equals("Settler"))  
		{
			Settler s = (Settler)grp3.get(i);
			
         groupCount++;
			name.addGrouptotal(s.getGroup());
		   groupHolder[2][i] = new JLabel(name.playerUI.lb.getIcon(s.getX(), s.getY()));  
         groupHolder[2][i].setBounds(0+(getCurrentNum(2)*30), 0, 30, 45);
			groupIcons[2][i] = new JLabel(s.getIcon());  
         groupIcons[2][i].setBounds(5+(getCurrentNum(2)*30), 0, 20, 20);
	      groupPane[2][0].add(groupHolder[2][i], new Integer(1)); 
         groupPane[2][0].add(groupIcons[2][i], new Integer(2));

         groupSettler[2][i] = s.getSettlerLabel();
         groupSettler[2][i].addMouseListener(this);
//         groupPeices.add(s);
//System.out.println("added Settler group holder "+(s.getGroup()-1));            
			
         JTextArea num = new JTextArea();
			num.setOpaque(false); 
		   num.setEditable(false);
		   num.setForeground(color);
		   num.setFont(f);
			num.setText(s.getNum().getText());
		   num.setBounds(13+(getCurrentNum(2)*29), 0, 10, 10);
         groupPane[2][0].add(num, new Integer(3));

			addCurrentNum(2);
       }
		 else
		 {
         Army a = (Army)grp3.get(i);
			
         groupCount++;
			name.addGrouptotal(a.getGroup());
			groupHolder[2][i] = new JLabel(name.playerUI.lb.getIcon(a.getX(), a.getY()));  
         groupHolder[2][i].setBounds(0+(getCurrentNum(2)*30), 0, 30, 45);
			groupIcons[2][i] = new JLabel(a.getIcon());  
         groupIcons[2][i].setBounds(5+(getCurrentNum(2)*30), 0, 20, 20);
	      groupPane[2][0].add(groupHolder[2][i], new Integer(1)); 
         groupPane[2][0].add(groupIcons[2][i], new Integer(2));

         groupSettler[2][i] = a.getArmyLabel();
         groupSettler[2][i].addMouseListener(this);
//         groupPeices.add(a);
//System.out.println("added Army group holder "+(a.getGroup()-1));            

         JTextArea num = new JTextArea();
			num.setOpaque(false); 
		   num.setEditable(false);
         if(a.getArmyColor().equals("White"))
	        num.setForeground(new Color(250, 0, 0));
		   else
		     num.setForeground(color);
		   num.setFont(f);
			num.setText(a.getNum().getText());
		   num.setBounds(13+(getCurrentNum(2)*30), 0, 10, 10);
         groupPane[2][0].add(num, new Integer(3));

			addCurrentNum(2);
      }  
    }
//System.out.println("xSET, Yset " +  xSet + ySet);
			if(grp3.size() > 0)  
			{
			  int xpos=0, ypos=0;
			  switch(xSet)
			  {
			    case 1:
					switch(grp3.size())
					{
					  case 2:
                   xpos = 45; 				   
					    break;
					  case 3:
	                xpos = 45; 				   
					    break;
					  case 4:
	                xpos = 45; 				   
					    break;
					  case 5:
	                xpos = 45; 				   
					    break;
					  case 6:
	                xpos = 00; 				   
					    break;
					}
				   break;
			    case 2:
					switch(grp3.size())
					{
					  case 2:
                   xpos = 90; 				   
					    break;
					  case 3:
	                xpos = 90; 				   
					    break;
					  case 4:
	                xpos = 00; 				   
					    break;
					  case 5:
	                xpos = 00; 				   
					    break;
					  case 6:
	                xpos = 00; 				   
					    break;
					}
				   break;
			    case 3:
					switch(grp3.size())
					{
					  case 2:
                   xpos = 135; 				   
					    break;
					  case 3:
	                xpos = 00; 				   
					    break;
					  case 4:
	                xpos = 00; 				   
					    break;
					  case 5:
	                xpos = 00; 				   
					    break;
					  case 6:
	                xpos = 00; 				   
					    break;
					}
				   break;
			    case 4:
					switch(grp3.size())
					{
					  case 2:
                   xpos = 00; 				   
					    break;
					  case 3:
	                xpos = 00; 				   
					    break;
					  case 4:
	                xpos = 00; 				   
					    break;
					  case 5:
	                xpos = 00; 				   
					    break;
					  case 6:
	                xpos = 00; 				   
					    break;
					}
				   break;
			    case 5:
					switch(grp3.size())
					{
					  case 2:
                   xpos = 00; 				   
					    break;
					  case 3:
	                xpos = 00; 				   
					    break;
					  case 4:
	                xpos = 00; 				   
					    break;
					  case 5:
	                xpos = 00; 				   
					    break;
					  case 6:
	                xpos = 00; 				   
					    break;
					}
				   break;
			    case 6:
					switch(grp3.size())
					{
					  case 2:
                   xpos = 60; 				   
					    break;
					  case 3:
	                xpos = 60; 				   
					    break;
					  case 4:
	                xpos = 60; 				   
					    break;
					  case 5:
	                xpos = 00; 				   
					    break;
					  case 6:
	                xpos = 00; 				   
					    break;
					}
				   break;
			    case 7:
					switch(grp3.size())
					{
					  case 2:
                   xpos = 00; 				   
					    break;
					  case 3:
	                xpos = 00; 				   
					    break;
					  case 4:
	                xpos = 00; 				   
					    break;
					  case 5:
	                xpos = 00; 				   
					    break;
					  case 6:
	                xpos = 00; 				   
					    break;
					}
				   break;
			  }
			  switch(ySet)
			  {
			    case 1:
					switch(grp3.size())
					{
					  case 2:
                   ypos = 00; 				   
					    break;
					  case 3:
	                ypos = 00; 				   
					    break;
					  case 4:
	                ypos = 00; 				   
					    break;
					  case 5:
	                ypos = 00; 				   
					    break;
					  case 6:
	                ypos = 45; 				   
					    break;
				   }  
					break;
			    case 2:
					switch(grp3.size())
					{
					  case 2:
                   ypos = 00; 				   
					    break;
					  case 3:
	                ypos = 00; 				   
					    break;
					  case 4:
	                ypos = 45; 				   
					    break;
					  case 5:
	                ypos = 45; 				   
					    break;
					  case 6:
	                ypos = 45; 				   
					    break;
				   }  
					break;
				 case 3:
					switch(grp3.size())
					{
					  case 2:
                   ypos = 00; 				   
					    break;
					  case 3:
	                ypos = 45; 				   
					    break;
					  case 4:
	                ypos = 45; 				   
					    break;
					  case 5:
	                ypos = 45; 				   
					    break;
					  case 6:
	                ypos = 45; 				   
					    break;
               }
				   break;
			    case 4:
					switch(grp3.size())
					{
					  case 2:
                   ypos = 45; 				   
					    break;
					  case 3:
	                ypos = 45; 				   
					    break;
					  case 4:
	                ypos = 45; 				   
					    break;
					  case 5:
	                ypos = 45; 				   
					    break;
					  case 6:
	                ypos = 45; 				   
					    break;
               }
				   break;
			    case 5:
					switch(grp3.size())
					{
					  case 2:
                   ypos = 45; 				   
					    break;
					  case 3:
	                ypos = 45; 				   
					    break;
					  case 4:
	                ypos = 45; 				   
					    break;
					  case 5:
	                ypos = 45; 				   
					    break;
					  case 6:
	                ypos = 45; 				   
					    break;
               }
				   break;
			    case 6:
					switch(grp3.size())
					{
					  case 2:
                   ypos = 45; 				   
					    break;
					  case 3:
	                ypos = 45; 				   
					    break;
					  case 4:
	                ypos = 45; 				   
					    break;
					  case 5:
	                ypos = 90; 				   
					    break;
					  case 6:
	                ypos = 90; 				   
					    break;
               } 
				   break;
			    case 7:
					switch(grp3.size())
					{
					  case 2:
                   ypos = 90; 				   
					    break;
					  case 3:
	                ypos = 90; 				   
					    break;
					  case 4:
	                ypos = 90; 				   
					    break;
					  case 5:
	                ypos = 90; 				   
					    break;
					  case 6:
	                ypos = 90; 				   
					    break;
               }
				   break;
			  }
         
	      gm[2][0] = new JButton();
			Insets in = new Insets(2, 2, 2, 2);
	      gm[2][0].setMargin(in);
		   gm[2][0].setFont(f);
		   gm[2][0].setBounds(3*grp3.size(), 30, 23*grp3.size(), 12);
         gm[2][0].setText("Group 3");//+ Integer.toString(s.getGroup()));
         groupPane[2][0].add(gm[2][0], new Integer(10));      
	      gm[2][0].addActionListener(this);
         gm[2][0].setDefaultCapable(false);
			 
			groupPane[2][0].setBounds(xpos, ypos, 30*grp3.size(), 45);
//System.out.println("GROUP3: "+grp3.size());
			unitHolder.add(groupPane[2][0], new Integer(0));
  
         xSet += grp3.size();
         ySet += grp3.size();
		}

    for(i = 0; i < grp4.size(); i++)
	 {
	   if(grp4.get(i).getClass().getName().equals("Settler"))  
		{
			Settler s = (Settler)grp4.get(i);
			
         groupCount++;
			name.addGrouptotal(s.getGroup());
		   groupHolder[3][i] = new JLabel(name.playerUI.lb.getIcon(s.getX(), s.getY()));  
         groupHolder[3][i].setBounds(0+(getCurrentNum(3)*30), 0, 30, 45);
			groupIcons[3][i] = new JLabel(s.getIcon());  
         groupIcons[3][i].setBounds(5+(getCurrentNum(3)*30), 0, 20, 20);
	      groupPane[3][0].add(groupHolder[3][i], new Integer(1)); 
         groupPane[3][0].add(groupIcons[3][i], new Integer(2));

         groupSettler[3][i] = s.getSettlerLabel();
         groupSettler[3][i].addMouseListener(this);
//         groupPeices.add(s);
//System.out.println("added Settler group holder "+(s.getGroup()-1));            
			
         JTextArea num = new JTextArea();
			num.setOpaque(false); 
		   num.setEditable(false);
		   num.setForeground(color);
		   num.setFont(f);
			num.setText(s.getNum().getText());
		   num.setBounds(13+(getCurrentNum(3)*29), 0, 10, 10);
         groupPane[3][0].add(num, new Integer(3));

			addCurrentNum(3);
       }
		 else
		 {
         Army a = (Army)grp4.get(i);
			
         groupCount++;
			name.addGrouptotal(a.getGroup());
			groupHolder[3][i] = new JLabel(name.playerUI.lb.getIcon(a.getX(), a.getY()));  
         groupHolder[3][i].setBounds(0+(getCurrentNum(3)*30), 0, 30, 45);
			groupIcons[3][i] = new JLabel(a.getIcon());  
         groupIcons[3][i].setBounds(5+(getCurrentNum(3)*30), 0, 20, 20);
	      groupPane[3][0].add(groupHolder[3][i], new Integer(1)); 
         groupPane[3][0].add(groupIcons[3][i], new Integer(2));

         groupSettler[3][i] = a.getArmyLabel();
         groupSettler[3][i].addMouseListener(this);
//         groupPeices.add(a);
//System.out.println("added Army group holder "+(a.getGroup()-1));            

         JTextArea num = new JTextArea();
			num.setOpaque(false); 
		   num.setEditable(false);
         if(a.getArmyColor().equals("White"))
	        num.setForeground(new Color(250, 0, 0));
		   else
		     num.setForeground(color);
		   num.setFont(f);
			num.setText(a.getNum().getText());
		   num.setBounds(13+(getCurrentNum(3)*30), 0, 10, 10);
         groupPane[3][0].add(num, new Integer(3));

			addCurrentNum(3);
      }  
    }
//System.out.println("xSET, Yset " +  xSet + ySet);
			if(grp4.size() > 0)  
			{
			  int xpos=0, ypos=0;
			  switch(xSet)
			  {
			    case 1:
					switch(grp4.size())
					{
					  case 2:
                   xpos = 45; 				   
					    break;
					  case 3:
	                xpos = 45; 				   
					    break;
					  case 4:
	                xpos = 45; 				   
					    break;
					  case 5:
	                xpos = 45; 				   
					    break;
					  case 6:
	                xpos = 00; 				   
					    break;
					}
				   break;
			    case 2:
					switch(grp4.size())
					{
					  case 2:
                   xpos = 90; 				   
					    break;
					  case 3:
	                xpos = 90; 				   
					    break;
					  case 4:
	                xpos = 00; 				   
					    break;
					  case 5:
	                xpos = 00; 				   
					    break;
					  case 6:
	                xpos = 00; 				   
					    break;
					}
				   break;
			    case 3:
					switch(grp4.size())
					{
					  case 2:
                   xpos = 135; 				   
					    break;
					  case 3:
	                xpos = 00; 				   
					    break;
					  case 4:
	                xpos = 00; 				   
					    break;
					  case 5:
	                xpos = 00; 				   
					    break;
					  case 6:
	                xpos = 00; 				   
					    break;
					}
				   break;
			    case 4:
					switch(grp4.size())
					{
					  case 2:
                   xpos = 00; 				   
					    break;
					  case 3:
	                xpos = 00; 				   
					    break;
					  case 4:
	                xpos = 00; 				   
					    break;
					  case 5:
	                xpos = 00; 				   
					    break;
					  case 6:
	                xpos = 00; 				   
					    break;
					}
				   break;
			    case 5:
					switch(grp4.size())
					{
					  case 2:
                   xpos = 00; 				   
					    break;
					  case 3:
	                xpos = 00; 				   
					    break;
					  case 4:
	                xpos = 00; 				   
					    break;
					  case 5:
	                xpos = 00; 				   
					    break;
					  case 6:
	                xpos = 00; 				   
					    break;
					}
				   break;
			    case 6:
					switch(grp4.size())
					{
					  case 2:
                   xpos = 90; 				   
					    break;
					  case 3:
	                xpos = 90; 				   
					    break;
					}
				   break;
			    case 7:
					switch(grp4.size())
					{
					  case 2:
                   xpos = 90; 				   
					    break;
					  case 3:
	                xpos = 90; 				   
					    break;
					}
				   break;
			  }
			  switch(ySet)
			  {
			    case 1:
					switch(grp4.size())
					{
					  case 2:
                   ypos = 00; 				   
					    break;
					  case 3:
	                ypos = 00; 				   
					    break;
					  case 4:
	                ypos = 00; 				   
					    break;
					  case 5:
	                ypos = 00; 				   
					    break;
					  case 6:
	                ypos = 45; 				   
					    break;
				   }  
					break;
			    case 2:
					switch(grp4.size())
					{
					  case 2:
                   ypos = 00; 				   
					    break;
					  case 3:
	                ypos = 00; 				   
					    break;
					  case 4:
	                ypos = 45; 				   
					    break;
					  case 5:
	                ypos = 45; 				   
					    break;
					  case 6:
	                ypos = 45; 				   
					    break;
				   }  
					break;
				 case 3:
					switch(grp4.size())
					{
					  case 2:
                   ypos = 00; 				   
					    break;
					  case 3:
	                ypos = 45; 				   
					    break;
					  case 4:
	                ypos = 45; 				   
					    break;
					  case 5:
	                ypos = 45; 				   
					    break;
					  case 6:
	                ypos = 45; 				   
					    break;
               }
				   break;
			    case 4:
					switch(grp4.size())
					{
					  case 2:
                   ypos = 45; 				   
					    break;
					  case 3:
	                ypos = 45; 				   
					    break;
					  case 4:
	                ypos = 45; 				   
					    break;
					  case 5:
	                ypos = 45; 				   
					    break;
					  case 6:
	                ypos = 45; 				   
					    break;
               }
				   break;
			    case 5:
					switch(grp4.size())
					{
					  case 2:
                   ypos = 45; 				   
					    break;
					  case 3:
	                ypos = 45; 				   
					    break;
					  case 4:
	                ypos = 45; 				   
					    break;
					  case 5:
	                ypos = 45; 				   
					    break;
					  case 6:
	                ypos = 45; 				   
					    break;
               }
				   break;
			    case 6:
					switch(grp4.size())
					{
					  case 2:
                   ypos = 45; 				   
					    break;
					  case 3:
	                ypos = 45; 				   
					    break;
               } 
				   break;
			    case 7:
					switch(grp4.size())
					{
					  case 2:
                   ypos = 45; 				   
					    break;
					  case 3:
	                ypos = 45; 				   
					    break;
               }
				   break;
			  }
         
	      gm[3][0] = new JButton();
			Insets in = new Insets(2, 2, 2, 2);
	      gm[3][0].setMargin(in);
		   gm[3][0].setFont(f);
		   gm[3][0].setBounds(3*grp4.size(), 30, 23*grp4.size(), 12);
         gm[3][0].setText("Group 4");//+ Integer.toString(s.getGroup()));
         groupPane[3][0].add(gm[3][0], new Integer(10));      
	      gm[3][0].addActionListener(this);
         gm[3][0].setDefaultCapable(false);
			 
			groupPane[3][0].setBounds(xpos, ypos, 30*grp4.size(), 45);
//System.out.println("GROUP2: "+grp4.size());
			unitHolder.add(groupPane[3][0], new Integer(0));
		}

     if(movePeices.size() == 0)
	  {
	    JLayeredPane none = new JLayeredPane();
		 JPanel noneHolder = new JPanel();
       noneHolder.setBackground(name.getPlayerColor());		   
       noneHolder.setPreferredSize(new Dimension(198, 198));

	    JLabel lbl_1 = new JLabel("No units to move!");
		 lbl_1.setBounds(5, 45, 100, 20);
		 none.add(lbl_1);
		    
       turnOver = new JButton("BEGIN RESEARCH TURN");
	    turnOver.setBounds(5, 145, 180, 20);
	    turnOver.setPreferredSize(new Dimension(180, 20));
	    turnOver.addActionListener(this);

		 none.add(turnOver, new Integer(0));
       none.setPreferredSize(new Dimension(197, 197));
       noneHolder.setBounds(0, 0, 198, 198);
		 noneHolder.add(none);

       select.setTitle("Move turn");
       select.add(noneHolder);
		 frame.add(select);
	    select.show();

		try
	   {select.setMaximum(true);}
	   catch(PropertyVetoException ex)
	   {}
//System.out.println("added SIZE 0");            
	  }

     else if(movePeices.size() > 0)
	  {
       unitHolder.setBounds(00, 00, 198, 198);
		 unitHolder.setPreferredSize(new Dimension(198, 198));

       select.add(unitHolder);
		 frame.add(select);
	    select.show();

		 try
	    {select.setMaximum(true);}
	    catch(PropertyVetoException ex)
	    {}
//System.out.println("added SIZE > 0");            

	  }
  }
 }
 
  private int getCurrentNum(int num)
  {
//System.out.println("number asked for "+num);
    switch(num)
	 {
	   case 0:
		  return num0group;
	   case 1:
		  return num1group;
	   case 2:
		  return num2group;
	   case 3:
		  return num3group;
      default:
		  return 0;

	 }
  }

  private void addCurrentNum(int num)
  {
    switch(num)
	 {
	   case 0:
		  num0group++;
		  break;
	   case 1:
		  num1group++;
		  break;
	   case 2:
		  num2group++;
		  break;
	   case 3:
		  num3group++;
		  break;
	 }
  }

  private void moveThisPeice()
  {
			  internal = new JInternalFrame("Move");  
	 	 
           if(name.getColor().equals("Red"))
		       internal.setBackground(new Color(255, 0, 0));    
           else if(name.getColor().equals("Yellow"))
		       internal.setBackground(new Color(255, 255, 0));    
           else if(name.getColor().equals("Blue"))
		       internal.setBackground(new Color(0, 0, 255));    
           else if(name.getColor().equals("Green"))
		       internal.setBackground(new Color(0, 255, 0)); 
				 
	        GameImageIcon arrow1 = getImage("data/arrowLt.png");
           GameImageIcon arrow2 = getImage("data/arrowUp.png");
           GameImageIcon arrow3 = getImage("data/arrowRt.png");
           GameImageIcon arrow4 = getImage("data/arrowDn.png");

  	        lt = new JButton(arrow1);
  	        up = new JButton(arrow2);
  	        rt = new JButton(arrow3);
  	        dw = new JButton(arrow4);

//	        done.addActionListener(this);  
	        lt.addActionListener(this);  
	        rt.addActionListener(this);  
	        up.addActionListener(this);  
	        dw.addActionListener(this);  
		 
	        JLayeredPane direction = new JLayeredPane();

		     done.setBounds(20, 145, 70, 20);  
           lt.setBounds(00, 64, 42, 28);  
           rt.setBounds(70, 64, 42, 28);  
           up.setBounds(42, 22, 28, 42);  
           dw.setBounds(42, 92, 28, 42);  


		     showMoves = new JTextArea();
           showMoves.setEditable(false);
		     showMoves.setBounds(20, 00, 72, 20);
		     showMoves.setBorder(new BevelBorder(1, new Color(250, 250, 250), new Color(0, 0, 0)));
           String dispMoves = null;    

		     if(!movePeices.isEmpty())
		     {
		       if(groupObject.getClass().getName().equals("Settler"))//movePeices.get(targetPeice).getClass().getName() == "Settler")
             {
		         Settler s = (Settler)groupObject;
		         dispMoves = "Moves left: " + s.getMoves();
             }         
		       else if(groupObject.getClass().getName().equals("Army"))//movePeices.get(targetPeice).getClass().getName() == "Army")
		       {
		         Army a = (Army)groupObject;
		         dispMoves = "Moves left: " + a.getMoves();
             }
		       showMoves.setText(dispMoves);  
			  }  

	        direction.add(showMoves);
	        direction.add(up);
	        direction.add(lt);
	        direction.add(rt);
	        direction.add(dw);
	        direction.add(done);

           direction.addKeyListener(this);
           direction.setFocusable(true);
           direction.requestFocusInWindow();

			  direction.setPreferredSize(new Dimension(110, 170));
	        internal.add(direction);
	
	        name.playerBoard.turnHolder.removeAll();//(select);    
	        internal.setBorder(new BevelBorder(1, new Color(0, 0, 0), new Color(250, 250, 250)));
		     internal.show();
			  name.playerBoard.turnHolder.add(internal);

		     try
		     {internal.setMaximum(true);}
		     catch(PropertyVetoException ex)
		     {}  
//			  CivGUI.mainBoard.validate();
  }
 
  public void actionPerformed(ActionEvent e) 
  {
		for(sourcePeice = 0; sourcePeice < noGrp.size(); sourcePeice++)
		{  
		   if(e.getSource() == nm[sourcePeice])
         {
		     targetPeice = sourcePeice;  
           nm[sourcePeice].removeActionListener(this);        

			  Army a = null;
			  Settler s = null;
		   
			  Object temp = noGrp.get(targetPeice);
           groupObject = noGrp.get(targetPeice);
			  
//			  groupObject = noGrp.get(targetPeice);
//			  groupPeices.add(groupObject);
		     
			  int x=0, y=0;
           boolean cangroup = false;
			  if(temp.getClass().getName().equals("Settler"))  
           {
			    s = (Settler)temp;
		       x = s.getX();
			    y = s.getY();
		     }
		     else
           {
			    a = (Army)temp;
		       x = a.getX();
			    y = a.getY();
		     }
			  
			  if(s != null)
			  {
				 Vector ar = name.getArmyVector();
		       Vector sett = name.getSettlerVector();
				 int k;
				 for(k = 0; k < ar.size(); k++)
				 {
				   Army tempAr = (Army)ar.get(k);  
					if((tempAr.getX() == s.getX()) && (tempAr.getY() == s.getY()))
			      {
                  if(tempAr.getMoves() == name.getMoveLimit())
						{
						  cangroup = true;
						}
					}
				 }
				 for(k = 0; k < sett.size(); k++)
				 {
				   Settler tempSett = (Settler) sett.get(k);  
					if((tempSett.getX() == s.getX()) && (tempSett.getY() == s.getY()))
				   {
                 if(tempSett.getMoves() == name.getMoveLimit() && (tempSett != s))
					  {
					    cangroup = true;
					  }
					}
				 } 
			    if(((name.playerUI.lb.getSettlerCount(s.getX(), s.getY()) + name.playerUI.lb.getArmyCount(s.getX(), s.getY())) < 2) || !cangroup)
			    {
                moveThisPeice();
				 }
             else if((name.playerUI.lb.getSettlerCount(s.getX(), s.getY()) + name.playerUI.lb.getArmyCount(s.getX(), s.getY())) > 1)
	          {
					if(s.getGroup() == 0 && cangroup)
					{
					    move = new JButton("Move");
					    move.setBounds(40, 3, 120, 18);
					    move.setPreferredSize(new Dimension(180, 18));
					    move.addActionListener(this);
						 group = new JButton("Group units");
					    group.setBounds(40, 21, 120, 18);
					    group.setPreferredSize(new Dimension(180, 18));
					    group.addActionListener(this);

					    name.playerBoard.turnHolder.removeAll();
						 JLayeredPane none = new JLayeredPane();
				       JLayeredPane buttons = new JLayeredPane();
				
						 JPanel noneHolder = new JPanel();
				       JPanel buttonPanel = new JPanel();
				
				       noneHolder.setBackground(name.getPlayerColor());		   
				       noneHolder.setPreferredSize(new Dimension(198, 145));
				       buttonPanel.setBackground(name.getPlayerColor());		   
				       buttonPanel.setPreferredSize(new Dimension(198, 53));
				
						 none.add(select, new Integer(0));
				       none.setPreferredSize(new Dimension(198, 141));
						 none.setBorder(new BevelBorder(1, new Color(0, 0, 0), new Color(250, 250, 250)));
				
				       buttons.add(move, new Integer(0));  
				       buttons.add(group, new Integer(0));  
				       buttons.setPreferredSize(new Dimension(198, 43));
						 buttons.setBorder(new BevelBorder(1, new Color(0, 0, 0), new Color(250, 250, 250)));
				
						 noneHolder.add(none);
				       buttonPanel.add(buttons);  
				
						 name.playerBoard.turnHolder.add(noneHolder);
					    name.playerBoard.turnHolder.add(buttonPanel);
				   }
			    }
			  }
			  else
			  {
				 Vector ar = name.getArmyVector();
		       Vector sett = name.getSettlerVector();
				 int k;
				 for(k = 0; k < ar.size(); k++)
				 {
				   Army tempAr = (Army)ar.get(k);  
					if((tempAr.getX() == a.getX()) && (tempAr.getY() == a.getY()))
			      {
                 if(tempAr.getMoves() == name.getMoveLimit() && (tempAr != a))
					  {
						  cangroup = true;
					  }
					 }
				  }
				  for(k = 0; k < sett.size(); k++)
				  {
				    Settler tempSett = (Settler) sett.get(k);  
					 if((tempSett.getX() == a.getX()) && (tempSett.getY() == a.getY()))
				    {
                  if(tempSett.getMoves() == name.getMoveLimit())
						{
						  cangroup = true;
						}
					 }
				  } 
			    if(((name.playerUI.lb.getSettlerCount(a.getX(), a.getY()) + name.playerUI.lb.getArmyCount(a.getX(), a.getY())) < 2) || !cangroup)
			    {
                moveThisPeice();
				 }
             else if((name.playerUI.lb.getSettlerCount(a.getX(), a.getY()) + name.playerUI.lb.getArmyCount(a.getX(), a.getY())) > 1)
	          {
               if(a.getGroup() == 0 && cangroup)
					{
					    move = new JButton("Move");
					    move.setBounds(40, 3, 120, 18);
					    move.setPreferredSize(new Dimension(180, 18));
					    move.addActionListener(this);
						 group = new JButton("Group units");
					    group.setBounds(40, 21, 120, 18);
					    group.setPreferredSize(new Dimension(180, 18));
					    group.addActionListener(this);

					    name.playerBoard.turnHolder.removeAll();
						 JLayeredPane none = new JLayeredPane();
				       JLayeredPane buttons = new JLayeredPane();
				
						 JPanel noneHolder = new JPanel();
				       JPanel buttonPanel = new JPanel();
				
				       noneHolder.setBackground(name.getPlayerColor());		   
				       noneHolder.setPreferredSize(new Dimension(198, 145));
				       buttonPanel.setBackground(name.getPlayerColor());		   
				       buttonPanel.setPreferredSize(new Dimension(198, 53));
				
						 none.add(select, new Integer(0));
				       none.setPreferredSize(new Dimension(198, 141));
						 none.setBorder(new BevelBorder(1, new Color(0, 0, 0), new Color(250, 250, 250)));
				
				       buttons.add(move, new Integer(0));  
				       buttons.add(group, new Integer(0));  
				       buttons.setPreferredSize(new Dimension(198, 43));
						 buttons.setBorder(new BevelBorder(1, new Color(0, 0, 0), new Color(250, 250, 250)));
				
						 noneHolder.add(none);
				       buttonPanel.add(buttons);  
				
						 name.playerBoard.turnHolder.add(noneHolder);
					    name.playerBoard.turnHolder.add(buttonPanel);
				   }
			   }
          }    
		  }
	   }
		
		for(groupSource = 0; groupSource < 4; groupSource++)
		{  
        if(e.getSource() == gm[groupSource][0]) 
        {
		     targetPeice = groupSource;  
           gm[groupSource][0].removeActionListener(this);			  
     
				switch(targetPeice)
				{
				  case 0:
	             groupObject = grp1.get(0);
				    break;
				  case 1:
	             groupObject = grp2.get(0);
				    break;
				  case 2:
	             groupObject = grp3.get(0);
				    break;
				  case 3:
	             groupObject = grp4.get(0);
				    break;
				}  

			  groupMove = new JButton("Move");
           groupMove.setBounds(40, 3, 120, 18);
			  groupMove.setPreferredSize(new Dimension(180, 18));
			  groupMove.addActionListener(this);
			
			  disband = new JButton("Disband group");
			  disband.setBounds(40, 21, 120, 18);
			  disband.setPreferredSize(new Dimension(180, 18));
			  disband.addActionListener(this);

			  name.playerBoard.turnHolder.removeAll();
			  JLayeredPane none = new JLayeredPane();
			  JLayeredPane buttons = new JLayeredPane();
				
			  JPanel noneHolder = new JPanel();
			  JPanel buttonPanel = new JPanel();
				
			  noneHolder.setBackground(name.getPlayerColor());		   
			  noneHolder.setPreferredSize(new Dimension(198, 145));
			  buttonPanel.setBackground(name.getPlayerColor());		   
			  buttonPanel.setPreferredSize(new Dimension(198, 53));
			
			  none.add(select, new Integer(0));
			  none.setPreferredSize(new Dimension(198, 141));
			  none.setBorder(new BevelBorder(1, new Color(0, 0, 0), new Color(250, 250, 250)));
				
			  buttons.add(groupMove, new Integer(0));  
			  buttons.add(disband, new Integer(0));  
			  buttons.setPreferredSize(new Dimension(198, 43));
			  buttons.setBorder(new BevelBorder(1, new Color(0, 0, 0), new Color(250, 250, 250)));
				
			  noneHolder.add(none);
			  buttonPanel.add(buttons);  
				
			  name.playerBoard.turnHolder.add(noneHolder);
			  name.playerBoard.turnHolder.add(buttonPanel);
		   }
	   }

		if(e.getSource() == groupMove)
		{
		   internal = new JInternalFrame("Move group");  
	 	 
         if(name.getColor().equals("Red"))
		      internal.setBackground(new Color(255, 0, 0));    
         else if(name.getColor().equals("Yellow"))
		      internal.setBackground(new Color(255, 255, 0));    
         else if(name.getColor().equals("Blue"))
		      internal.setBackground(new Color(0, 0, 255));    
         else if(name.getColor().equals("Green"))
		      internal.setBackground(new Color(0, 255, 0)); 
				 
	      GameImageIcon arrow1 = getImage("data/arrowLt.png");
         GameImageIcon arrow2 = getImage("data/arrowUp.png");
         GameImageIcon arrow3 = getImage("data/arrowRt.png");
         GameImageIcon arrow4 = getImage("data/arrowDn.png");

  	      lt = new JButton(arrow1);
  	      up = new JButton(arrow2);
  	      rt = new JButton(arrow3);
  	      dw = new JButton(arrow4);

//	        done.addActionListener(this);  
         lt.addActionListener(this);  
	      rt.addActionListener(this);  
	      up.addActionListener(this);  
	      dw.addActionListener(this);  
		 
	      JLayeredPane direction = new JLayeredPane();

		   done.setBounds(20, 145, 70, 20);  
         lt.setBounds(00, 64, 42, 28);  
         rt.setBounds(70, 64, 42, 28);  
         up.setBounds(42, 22, 28, 42);  
         dw.setBounds(42, 92, 28, 42);  

		   showMoves = new JTextArea();
         showMoves.setEditable(false);
		   showMoves.setBounds(20, 00, 72, 20);
		   showMoves.setBorder(new BevelBorder(1, new Color(250, 250, 250), new Color(0, 0, 0)));
         String dispMoves = null;    

		   if(groupObject.getClass().getName() == "Settler")
         {
	         Settler s = (Settler)groupObject;
		      dispMoves = "Moves left: " + s.getMoves();
         }         
		   else// if(groupPeices.get(targetPeice).getClass().getName() == "Army")
		   {
		      Army a = (Army)groupObject;
		      dispMoves = "Moves left: " + a.getMoves();
         }

		   showMoves.setText(dispMoves);  

         direction.add(showMoves);
	      direction.add(up);
	      direction.add(lt);
	      direction.add(rt);
	      direction.add(dw);
	      direction.add(done);

         direction.addKeyListener(this);
         direction.setFocusable(true);
         direction.requestFocusInWindow();

			direction.setPreferredSize(new Dimension(110, 170));
	      internal.add(direction);
	
	      name.playerBoard.turnHolder.removeAll();//(select);    
	      internal.setBorder(new BevelBorder(1, new Color(0, 0, 0), new Color(250, 250, 250)));
		   internal.show();
			name.playerBoard.turnHolder.add(internal);

		   try
		   {internal.setMaximum(true);}
		   catch(PropertyVetoException ex)
		   {}  
//			groupPeices = new Vector(0, 1);
		}
		
		if(e.getSource() == move)
		{
			  internal = new JInternalFrame("Move");  
	 	 
           if(name.getColor().equals("Red"))
		       internal.setBackground(new Color(255, 0, 0));    
           else if(name.getColor().equals("Yellow"))
		       internal.setBackground(new Color(255, 255, 0));    
           else if(name.getColor().equals("Blue"))
		       internal.setBackground(new Color(0, 0, 255));    
           else if(name.getColor().equals("Green"))
		       internal.setBackground(new Color(0, 255, 0)); 
				 
	        GameImageIcon arrow1 = getImage("data/arrowLt.png");
           GameImageIcon arrow2 = getImage("data/arrowUp.png");
           GameImageIcon arrow3 = getImage("data/arrowRt.png");
           GameImageIcon arrow4 = getImage("data/arrowDn.png");

  	        lt = new JButton(arrow1);
  	        up = new JButton(arrow2);
  	        rt = new JButton(arrow3);
  	        dw = new JButton(arrow4);

//	        done.addActionListener(this);  
	        lt.addActionListener(this);  
	        rt.addActionListener(this);  
	        up.addActionListener(this);  
	        dw.addActionListener(this);  
		 
	        JLayeredPane direction = new JLayeredPane();

		     done.setBounds(20, 145, 70, 20);  
           lt.setBounds(00, 64, 42, 28);  
           rt.setBounds(70, 64, 42, 28);  
           up.setBounds(42, 22, 28, 42);  
           dw.setBounds(42, 92, 28, 42);  


		     showMoves = new JTextArea();
           showMoves.setEditable(false);
		     showMoves.setBounds(20, 00, 72, 20);
		     showMoves.setBorder(new BevelBorder(1, new Color(250, 250, 250), new Color(0, 0, 0)));
           String dispMoves = null;    

		     if(groupObject.getClass().getName() == "Settler")
           {
		       Settler s = (Settler)groupObject;
		       dispMoves = "Moves left: " + s.getMoves();
           }         
		     else// if(groupPeices.get(targetPeice).getClass().getName() == "Army")
		     {
		       Army a = (Army)groupObject;
		       dispMoves = "Moves left: " + a.getMoves();
           }

		     showMoves.setText(dispMoves);  
	        direction.add(showMoves);
	        direction.add(up);
	        direction.add(lt);
	        direction.add(rt);
	        direction.add(dw);
	        direction.add(done);

           direction.addKeyListener(this);
           direction.setFocusable(true);
           direction.requestFocusInWindow();

			  direction.setPreferredSize(new Dimension(110, 170));
	        internal.add(direction);
	
	        name.playerBoard.turnHolder.removeAll();//(select);    
	        internal.setBorder(new BevelBorder(1, new Color(0, 0, 0), new Color(250, 250, 250)));
		     internal.show();
			  name.playerBoard.turnHolder.add(internal);

		     try
		     {internal.setMaximum(true);}
		     catch(PropertyVetoException ex)
		     {}  
		}

		if(e.getSource() == group)
      {
        name.playerBoard.turnHolder.removeAll();

        select.setPreferredSize(new Dimension(198, 198));
        internal = new JInternalFrame("Choose units to group");    
        internal.setPreferredSize(new Dimension(198, 198));
		  
		  JLayeredPane p = new JLayeredPane();
		  p.setPreferredSize(new Dimension(198, 198));
		  p.setBounds(0, 0, 198, 198);
		  groupCheck = new JCheckBox[movePeices.size()];   

        Settler s = null;
		  Army a = null;
        int x=0, y=0, count;
		  Object temp = groupObject;
		  if(temp.getClass().getName().equals("Settler"))  
        {
			 s = (Settler)temp;
		    x = s.getX();
			 y = s.getY();
		  }
		  else
        {
			 a = (Army)temp;
		    x = a.getX();
			 y = a.getY();
		  }
		  count = 0;
		  for(int k = 0; k < movePeices.size(); k++)
		  {
		    temp = movePeices.get(k);
			 if(temp.getClass().getName().equals("Settler"))  
          {
			   s = (Settler)temp;
			   if(s.getX() == x && s.getY() == y && (s.getGroup() == 0))  
            {
					groupCheck[k] = new JCheckBox();
	            groupCheck[k].setBounds(20, 20+(count*30), 20, 20);        
               groupCheck[k].setBackground(name.getPlayerColor());
	            JLayeredPane l = new JLayeredPane();

					l = new JLayeredPane();
					JLabel lbl = new JLabel(getImage("data/Settler"+name.getColor()+".png"));
               lbl.setBounds(2, 2, lbl.getIcon().getIconWidth(), lbl.getIcon().getIconHeight());
					
					JTextArea ta = new JTextArea(s.getNum().getText());
					ta.setOpaque(false); 
				   ta.setEditable(false);
				   ta.setForeground(new Color(250, 250, 250));
				   Font f = new Font("Monospaced", Font.BOLD, 8);
				   ta.setFont(f);
               ta.setBounds(9, 2, 10, 10);
     
	            l.add(lbl, new Integer(1));
					l.add(ta, new Integer(2));

	    			l.setBounds(45, 20+(count*30), 25, 23);
               l.setBorder(new BevelBorder(1, new Color(0, 0, 0), new Color(250, 250, 250)));
               l.setOpaque(true);
               l.setBackground(new Color(0, 0, 0));
					
	            p.add(groupCheck[k], new Integer(0)); 
				   p.add(l, new Integer(0));  
				   
					count++;
				}
			 }
			 else
			 {
			   a = (Army)temp;	
			   if(a.getX() == x && a.getY() == y && (a.getGroup() == 0))  
            {
					groupCheck[k] = new JCheckBox();
               groupCheck[k].setBackground(name.getPlayerColor());
	            JLayeredPane l = new JLayeredPane();

					l = new JLayeredPane();
					JLabel lbl = new JLabel(getImage("data/Army"+a.getArmyColor()+".png"));
               lbl.setBounds(6, 2, lbl.getIcon().getIconWidth(), lbl.getIcon().getIconHeight());

					JTextArea ta = new JTextArea(a.getNum().getText());
					ta.setOpaque(false); 
				   ta.setEditable(false);
				   ta.setForeground(a.getTextColor());
				   Font f = new Font("Monospaced", Font.BOLD, 8);
				   ta.setFont(f);
               ta.setBounds(11, 2, 10, 10);

               l.add(lbl, new Integer(1));
					l.add(ta, new Integer(2));

               l.setBorder(new BevelBorder(1, new Color(0, 0, 0), new Color(250, 250, 250)));
               l.setOpaque(true);
               l.setBackground(new Color(0, 0, 0));
               if(count < 4)
					{
		            l.setBounds(45, 20+(count*30), 25, 23);
		            groupCheck[k].setBounds(20, 20+(count*30), 20, 20);        
		            p.add(groupCheck[k], new Integer(0)); 
					   p.add(l, new Integer(0));  
					}
               else
					{
		            l.setBounds(125, 20+((count-4)*30), 25, 23);
		            groupCheck[k].setBounds(100, 20+((count-4)*30), 20, 20);        
		            p.add(groupCheck[k], new Integer(0)); 
					   p.add(l, new Integer(0));  
					}
//System.out.println("COUNT: "+count);
               count++;				
				}
		    }
		  }     

		  groupDone = new JButton("Create group");
	     groupDone.setBounds(5, 145, 180, 20);
		  groupDone.addActionListener(this);
		  p.add(groupDone, new Integer(0));  
   
	     internal.add(p);
        internal.setBorder(new BevelBorder(1, new Color(0, 0, 0), new Color(250, 250, 250)));
        internal.setBackground(name.getPlayerColor()); 
		  internal.show();
		  name.playerBoard.turnHolder.add(internal);

		  group.removeActionListener(this); 
		  try
		  {internal.setMaximum(true);}
		  catch(PropertyVetoException ex)
		  {}  
	   }
		 
      if(e.getSource() == groupDone)
		{
		  name.addNumGroups();
        Settler s = null;
		  Army a = null;
        Object temp; 
		  int x=0, y=0, numSelected = 0;
              
		  for(int k = 0; k < movePeices.size(); k++)
		  {
           if(groupCheck[k] != null && groupCheck[k].isSelected())
              numSelected++;    
		  }
		  if(numSelected > 1)
        {
			  for(int k = 0; k < movePeices.size(); k++)
			  {
	          if(groupCheck[k] != null && groupCheck[k].isSelected())
			    {  
					 temp = movePeices.get(k);
				    if(temp.getClass().getName().equals("Settler"))  
		          {
					   s = (Settler)temp;
				      s.setGroup(name.getNumGroups());  
	//System.out.println("Added Settler to group! "+name.getNumGroups());          
					 }
				    else
		          {
					   a = (Army)temp;
				      a.setGroup(name.getNumGroups());  
	//System.out.println("Added Army to group! "+ name.getNumGroups());          
				    }
				 }
			  }
/*			  Vector tempSettlers = new Vector(0, 1);
			  Vector tempArmies = new Vector(0, 1);
		     Settler tempSett = null;
			  Army tempArmy = null;
			  
			  for(int k = 0; k < movePeices.size(); k++)
           {
		       if(movePeices.get(k).getClass().getName() == "Settler")
			    {
			      tempSett = (Settler)movePeices.get(k);   
				   tempSettlers.add(tempSett);
			    }
			    else
             {
			      tempArmy = (Army)movePeices.get(k);	 
               tempArmies.add(tempArmy);
             }
			  }
           int k;
			  movePeices.removeAllElements();
			  for(k = 0; k < tempSettlers.size(); k++)
			  {
//			    movePeices.remove(k+1);  
				 movePeices.add(k, tempSettlers.remove(0));
			  }
			  for(int i = 0; i < tempArmies.size(); i++, k++)
			  {
			    movePeices.add(k, tempArmies.remove(0));
//			    movePeices.remove(k+1);  
			  }
*/			   
			  groupDone.removeActionListener(this);		 
			  name.playerBoard.turnHolder.removeAll();
			  paintGUI(name.playerBoard.turnHolder);
		  }
		}
		
		if(e.getSource() == disband)
      {
        Settler s = null;
		  Army a = null;
		  Object temp = groupObject;
        int grp = 0; 

		  if(temp.getClass().getName().equals("Settler"))  
        {
			 s = (Settler)temp;
          grp = s.getGroup(); 
//	System.out.println("disbanded group!"+s.getGroup());          
          name.disbandGroup(s.getGroup());
//	System.out.println("disbanded group!"+s.getGroup());          
		  }
		  else
        {
			 a = (Army)temp;
          grp = a.getGroup(); 
//	System.out.println("disbanded group!"+a.getGroup());          
          name.disbandGroup(a.getGroup());
//	System.out.println("disbanded group!"+a.getGroup());          
		  }

/*		  switch(grp)
		  {
	        case 1:
	        	 for(int k = 0; k < grp2.size(); k++)
			    {
               temp = grp2.get(k);
		 		   if(temp.getClass().getName().equals("Settler"))  
        			{
					  s = (Settler)temp;
					  s.setGroup(1);
					}
               else
					{
					  a = (Army)temp;
					  a.setGroup(1);
					}							    
			    }
	        	 for(int k = 0; k < grp3.size(); k++)
			    {
               temp = grp3.get(k);
		 		   if(temp.getClass().getName().equals("Settler"))  
        			{
					  s = (Settler)temp;
					  s.setGroup(2);
					}
               else
					{
					  a = (Army)temp;
					  a.setGroup(2);
					}							    
			    }
	        	 for(int k = 0; k < grp4.size(); k++)
			    {
               temp = grp4.get(k);
		 		   if(temp.getClass().getName().equals("Settler"))  
        			{
					  s = (Settler)temp;
					  s.setGroup(3);
					}
               else
					{
					  a = (Army)temp;
					  a.setGroup(3);
					}							    
			    }
				 break;    
	        case 2:
	        	 for(int k = 0; k < grp3.size(); k++)
			    {
               temp = grp3.get(k);
		 		   if(temp.getClass().getName().equals("Settler"))  
        			{
					  s = (Settler)temp;
					  s.setGroup(2);
					}
               else
					{
					  a = (Army)temp;
					  a.setGroup(2);
					}							    
			    }
	        	 for(int k = 0; k < grp4.size(); k++)
			    {
               temp = grp4.get(k);
		 		   if(temp.getClass().getName().equals("Settler"))  
        			{
					  s = (Settler)temp;
					  s.setGroup(3);
					}
               else
					{
					  a = (Army)temp;
					  a.setGroup(3);
					}							    
			    }
				 break;    
	        case 3:
	        	 for(int k = 0; k < grp4.size(); k++)
			    {
               temp = grp4.get(k);
		 		   if(temp.getClass().getName().equals("Settler"))  
        			{
					  s = (Settler)temp;
					  s.setGroup(3);
					}
               else
					{
					  a = (Army)temp;
					  a.setGroup(3);
					}							    
			    }
				 break;    
		  }
*/
		  name.playerBoard.turnHolder.removeAll();
        disband.removeActionListener(this); 
		  name.subNumGroups();
		  paintGUI(name.playerBoard.turnHolder);
		}
		
		if(e.getSource() == turnOver)
      {
//	     name.playerBoard.turnHolder.remove(format);
        removeBoardListeners();
        name.playerBoard.turnHolder.remove(select);
 		  playerDone = true;
//        playerWait();
        turnOver.removeActionListener(this);
        name.getPlayerUI().getTurn().researchTurn(name);
	   }

		if(e.getSource() == done)
      {
          removeBoardListeners();
		    if(groupObject.getClass().getName() == "Settler")
          {
		      Settler s;
		      s = (Settler)groupObject;
            if(name.playerUI.lb.getTerrain(s.getX(), s.getY()).equals("Water") && !name.getWaterLand())        
				{
				  JOptionPane illegal = new JOptionPane();
				  GameImageIcon im = getImage("data/"+name.getNation()+"Icon.png");  
		        illegal.showMessageDialog(name.playerUI.map, "YOU CANNOT LAND IN WATER WITH CURRENT TECHNOLOGY!\nMOVE UNIT TO LAND", "CANNOT END MOVE HERE",
				  					 			   JOptionPane.INFORMATION_MESSAGE, im);	 	 
				}
            else if(name.playerUI.lb.isCitySpace(s.getX(), s.getY()))        
				{
				  JOptionPane illegal = new JOptionPane();
				  GameImageIcon im = getImage("data/"+name.getNation()+"Icon.png");  
		        illegal.showMessageDialog(name.playerUI, "YOU CANNOT END MOVE IN CITY CENTER", "CANNOT END MOVE HERE",
				  					 			   JOptionPane.INFORMATION_MESSAGE, im);	 	 
				}
				else
				  resetSettler(s);        
		    }  
			 else if(groupObject.getClass().getName() == "Army")
		    {
		      Army a;
		      a = (Army)groupObject;
            if(name.playerUI.lb.getTerrain(a.getX(), a.getY()).equals("Water") && !name.getWaterLand())        
				{
				  JOptionPane illegal = new JOptionPane();
				  GameImageIcon im = getImage("data/"+name.getNation()+"Icon.png");  
		        illegal.showMessageDialog(name.playerUI.map, "YOU CANNOT LAND IN WATER WITH CURRENT TECHNOLOGY!\nMOVE UNIT TO LAND", "CANNOT END MOVE HERE",
				  					 			   JOptionPane.INFORMATION_MESSAGE, im);	 	 
				}
            else if(name.playerUI.lb.isCitySpace(a.getX(), a.getY()))        
				{
				  JOptionPane illegal = new JOptionPane();
				  GameImageIcon im = getImage("data/"+name.getNation()+"Icon.png");  
		        illegal.showMessageDialog(name.playerUI.map, "YOU CANNOT END MOVE IN CITY CENTER", "CANNOT END MOVE HERE",
				  					 			   JOptionPane.INFORMATION_MESSAGE, im);	 	 
				}
				else
				  resetArmy(a);
			 }
		}
	   else if(e.getSource() == lt)
      {
		  Settler s;
		  Army a;
//        sourcePeice--;

		    if(groupObject.getClass().getName() == "Settler")
          {
	         s = (Settler)groupObject;
		      if(s.getMoves() != 0)
			   {        
				  moveDirection(s, name, 0);
		        showMoves.setText("Moves left: " + s.getMoves());  
			   }         
		    }  
			 else if(groupObject.getClass().getName() == "Army")
		    {
		      a = (Army)groupObject;
            if(a.getMoves() != 0)
				{        
				  moveDirection(a, name, 0);
              showMoves.setText("Moves left: " + a.getMoves());  
            }
			 }
	   }
  	   else if(e.getSource() == rt)
      {
//        sourcePeice--;
		  Settler s;
		  Army a;

		    if(groupObject.getClass().getName() == "Settler")
          {
		      s = (Settler)groupObject;
            if(s.getMoves() != 0)
				{        
				  moveDirection(s, name, 2);
		        showMoves.setText("Moves left: " + s.getMoves());  
            }         
		    }  
		    else if(groupObject.getClass().getName() == "Army")
		    {
		      a = (Army)groupObject;
            if(a.getMoves() != 0)
				{        
				  moveDirection(a, name, 2);
              showMoves.setText("Moves left: " + a.getMoves());  
            }
			 }
	   }
	   else if(e.getSource() == up)
      {
//        sourcePeice--;
		  Settler s;
		  Army a;
		    if(groupObject.getClass().getName() == "Settler")
          {
		      s = (Settler)groupObject;
            if(s.getMoves() != 0)
				{        
				  moveDirection(s, name, 1);
		        showMoves.setText("Moves left: " + s.getMoves());  
            }         
		    }  
		    else if(groupObject.getClass().getName() == "Army")
		    {
		      a = (Army)groupObject;
            if(a.getMoves() != 0)
				{        
				  moveDirection(a, name, 1);
              showMoves.setText("Moves left: " + a.getMoves());  
            }
			 }
	   }
	   else if(e.getSource() == dw)
      {
//        sourcePeice--;
		  Settler s;
		  Army a;
		    if(groupObject.getClass().getName() == "Settler")
          {
		      s = (Settler)groupObject;
            if(s.getMoves() != 0)
				{        
				  moveDirection(s, name, 3);
		        showMoves.setText("Moves left: " + s.getMoves());  
            }         
          }		     
		    else if(groupObject.getClass().getName() == "Army")
		    {
		      a = (Army)groupObject;
            if(a.getMoves() != 0)
				{        
				  moveDirection(a, name, 3);
              showMoves.setText("Moves left: " + a.getMoves());  
            }
		    }  
      }	
  }
  
  public void keyPressed(KeyEvent e)
  {
    int keyCode = e.getKeyCode();
    Settler s;
    Army a;
	 
    switch( keyCode ) { 
        case KeyEvent.VK_UP:
//System.out.println("UP");	 
		    if(groupObject.getClass().getName() == "Settler")
          {
		      s = (Settler)groupObject;
            if(s.getMoves() != 0)
				{        
				  moveDirection(s, name, 1);
		        showMoves.setText("Moves left: " + s.getMoves());  
            }         
		    }  
		    else if(groupObject.getClass().getName() == "Army")
		    {
		      a = (Army)groupObject;
            if(a.getMoves() != 0)
				{        
				  moveDirection(a, name, 1);
              showMoves.setText("Moves left: " + a.getMoves());  
            }
			 }
          break;
        case KeyEvent.VK_DOWN:
//System.out.println("DOWN");	 
		    if(groupObject.getClass().getName() == "Settler")
          {
		      s = (Settler)groupObject;
            if(s.getMoves() != 0)
				{        
				  moveDirection(s, name, 3);
		        showMoves.setText("Moves left: " + s.getMoves());  
            }         
          }		     
		    else if(groupObject.getClass().getName() == "Army")
		    {
		      a = (Army)groupObject;
            if(a.getMoves() != 0)
				{        
				  moveDirection(a, name, 3);
              showMoves.setText("Moves left: " + a.getMoves());  
            }
		    }  
          break;
        case KeyEvent.VK_LEFT:
//System.out.println("LT");	 
			    if(groupObject.getClass().getName() == "Settler")
	          {
		         s = (Settler)groupObject;
			      if(s.getMoves() != 0)
				   {        
					  moveDirection(s, name, 0);
			        showMoves.setText("Moves left: " + s.getMoves());  
				   }         
			    }  
				 else if(groupObject.getClass().getName() == "Army")
			    {
			      a = (Army)groupObject;
	            if(a.getMoves() != 0)
					{        
					  moveDirection(a, name, 0);
	              showMoves.setText("Moves left: " + a.getMoves());  
	            }
				 }
            break;
        case KeyEvent.VK_RIGHT :
//System.out.println("RT");	 
		    if(groupObject.getClass().getName() == "Settler")
          {
		      s = (Settler)groupObject;
            if(s.getMoves() != 0)
				{        
				  moveDirection(s, name, 2);
		        showMoves.setText("Moves left: " + s.getMoves());  
            }         
		    }  
		    else if(groupObject.getClass().getName() == "Army")
		    {
		      a = (Army)groupObject;
            if(a.getMoves() != 0)
				{        
				  moveDirection(a, name, 2);
              showMoves.setText("Moves left: " + a.getMoves());  
            }
			 }
          break;	 
    }
  }
  
  public void keyReleased(KeyEvent e){}
  public void keyTyped(KeyEvent e){}

  public void mouseClicked(MouseEvent e)
	{
		for(sourcePeice = 0; sourcePeice < noGrp.size(); sourcePeice++)
		{  
		   if(e.getSource() == singleUnit[sourcePeice])
         {
//System.out.println("CLICKSETT");		     
			  targetPeice = sourcePeice;  
           singleUnit[sourcePeice].removeMouseListener(this);        

			  Army a = null;
			  Settler s = null;
		   
			  Object temp = noGrp.get(targetPeice);
           groupObject = noGrp.get(targetPeice);
			  
//			  groupObject = noGrp.get(targetPeice);
//			  groupPeices.add(groupObject);
		     
			  int x=0, y=0;
           boolean cangroup = false;
			  if(temp.getClass().getName().equals("Settler"))  
           {
			    s = (Settler)temp;
		       x = s.getX();
			    y = s.getY();
		     }
		     else
           {
			    a = (Army)temp;
		       x = a.getX();
			    y = a.getY();
		     }
			  
			  if(s != null)
			  {
				 Vector ar = name.getArmyVector();
		       Vector sett = name.getSettlerVector();
				 int k;
				 for(k = 0; k < ar.size(); k++)
				 {
				   Army tempAr = (Army)ar.get(k);  
					if((tempAr.getX() == s.getX()) && (tempAr.getY() == s.getY()))
			      {
                  if(tempAr.getMoves() == name.getMoveLimit())
						{
						  cangroup = true;
						}
					}
				 }
				 for(k = 0; k < sett.size(); k++)
				 {
				   Settler tempSett = (Settler) sett.get(k);  
					if((tempSett.getX() == s.getX()) && (tempSett.getY() == s.getY()))
				   {
                 if(tempSett.getMoves() == name.getMoveLimit() && (tempSett != s))
					  {
					    cangroup = true;
					  }
					}
				 } 
			    if(((name.playerUI.lb.getSettlerCount(s.getX(), s.getY()) + name.playerUI.lb.getArmyCount(s.getX(), s.getY())) < 2) || !cangroup)
			    {
                moveThisPeice();
				 }
             else if((name.playerUI.lb.getSettlerCount(s.getX(), s.getY()) + name.playerUI.lb.getArmyCount(s.getX(), s.getY())) > 1)
	          {
					if(s.getGroup() == 0 && cangroup)
					{
					    move = new JButton("Move");
					    move.setBounds(40, 3, 120, 18);
					    move.setPreferredSize(new Dimension(180, 18));
					    move.addActionListener(this);
						 group = new JButton("Group units");
					    group.setBounds(40, 21, 120, 18);
					    group.setPreferredSize(new Dimension(180, 18));
					    group.addActionListener(this);

					    name.playerBoard.turnHolder.removeAll();
						 JLayeredPane none = new JLayeredPane();
				       JLayeredPane buttons = new JLayeredPane();
				
						 JPanel noneHolder = new JPanel();
				       JPanel buttonPanel = new JPanel();
				
				       noneHolder.setBackground(name.getPlayerColor());		   
				       noneHolder.setPreferredSize(new Dimension(198, 145));
				       buttonPanel.setBackground(name.getPlayerColor());		   
				       buttonPanel.setPreferredSize(new Dimension(198, 53));
				
						 none.add(select, new Integer(0));
				       none.setPreferredSize(new Dimension(198, 141));
						 none.setBorder(new BevelBorder(1, new Color(0, 0, 0), new Color(250, 250, 250)));
				
				       buttons.add(move, new Integer(0));  
				       buttons.add(group, new Integer(0));  
				       buttons.setPreferredSize(new Dimension(198, 43));
						 buttons.setBorder(new BevelBorder(1, new Color(0, 0, 0), new Color(250, 250, 250)));
				
						 noneHolder.add(none);
				       buttonPanel.add(buttons);  
				
						 name.playerBoard.turnHolder.add(noneHolder);
					    name.playerBoard.turnHolder.add(buttonPanel);
				   }
			    }
			  }
			  else
			  {
				 Vector ar = name.getArmyVector();
		       Vector sett = name.getSettlerVector();
				 int k;
				 for(k = 0; k < ar.size(); k++)
				 {
				   Army tempAr = (Army)ar.get(k);  
					if((tempAr.getX() == a.getX()) && (tempAr.getY() == a.getY()))
			      {
                 if(tempAr.getMoves() == name.getMoveLimit() && (tempAr != a))
					  {
						  cangroup = true;
					  }
					 }
				  }
				  for(k = 0; k < sett.size(); k++)
				  {
				    Settler tempSett = (Settler) sett.get(k);  
					 if((tempSett.getX() == a.getX()) && (tempSett.getY() == a.getY()))
				    {
                  if(tempSett.getMoves() == name.getMoveLimit())
						{
						  cangroup = true;
						}
					 }
				  } 
			    if(((name.playerUI.lb.getSettlerCount(a.getX(), a.getY()) + name.playerUI.lb.getArmyCount(a.getX(), a.getY())) < 2) || !cangroup)
			    {
                moveThisPeice();
				 }
             else if((name.playerUI.lb.getSettlerCount(a.getX(), a.getY()) + name.playerUI.lb.getArmyCount(a.getX(), a.getY())) > 1)
	          {
               if(a.getGroup() == 0 && cangroup)
					{
					    move = new JButton("Move");
					    move.setBounds(40, 3, 120, 18);
					    move.setPreferredSize(new Dimension(180, 18));
					    move.addActionListener(this);
						 group = new JButton("Group units");
					    group.setBounds(40, 21, 120, 18);
					    group.setPreferredSize(new Dimension(180, 18));
					    group.addActionListener(this);

					    name.playerBoard.turnHolder.removeAll();
						 JLayeredPane none = new JLayeredPane();
				       JLayeredPane buttons = new JLayeredPane();
				
						 JPanel noneHolder = new JPanel();
				       JPanel buttonPanel = new JPanel();
				
				       noneHolder.setBackground(name.getPlayerColor());		   
				       noneHolder.setPreferredSize(new Dimension(198, 145));
				       buttonPanel.setBackground(name.getPlayerColor());		   
				       buttonPanel.setPreferredSize(new Dimension(198, 53));
				
						 none.add(select, new Integer(0));
				       none.setPreferredSize(new Dimension(198, 141));
						 none.setBorder(new BevelBorder(1, new Color(0, 0, 0), new Color(250, 250, 250)));
				
				       buttons.add(move, new Integer(0));  
				       buttons.add(group, new Integer(0));  
				       buttons.setPreferredSize(new Dimension(198, 43));
						 buttons.setBorder(new BevelBorder(1, new Color(0, 0, 0), new Color(250, 250, 250)));
				
						 noneHolder.add(none);
				       buttonPanel.add(buttons);  
				
						 name.playerBoard.turnHolder.add(noneHolder);
					    name.playerBoard.turnHolder.add(buttonPanel);
				   }
			   }
          }    
		  }
	   }
		
		for(groupSource = 0; groupSource < 4; groupSource++)
		{  
       for(groupInt = 0; groupInt < name.getStacklimit(); groupInt++)
       {    
		  if(e.getSource() == groupSettler[groupSource][groupInt]) 
        {
//System.out.println("CLICKARMY");		     
		     targetPeice = groupSource;  
           groupSettler[groupSource][groupInt].removeMouseListener(this);			  
     
				switch(targetPeice)
				{
				  case 0:
	             groupObject = grp1.get(0);
				    break;
				  case 1:
	             groupObject = grp2.get(0);
				    break;
				  case 2:
	             groupObject = grp3.get(0);
				    break;
				  case 3:
	             groupObject = grp4.get(0);
				    break;
				}  

			  groupMove = new JButton("Move");
           groupMove.setBounds(40, 3, 120, 18);
			  groupMove.setPreferredSize(new Dimension(180, 18));
			  groupMove.addActionListener(this);
			
			  disband = new JButton("Disband group");
			  disband.setBounds(40, 21, 120, 18);
			  disband.setPreferredSize(new Dimension(180, 18));
			  disband.addActionListener(this);

			  name.playerBoard.turnHolder.removeAll();
			  JLayeredPane none = new JLayeredPane();
			  JLayeredPane buttons = new JLayeredPane();
				
			  JPanel noneHolder = new JPanel();
			  JPanel buttonPanel = new JPanel();
				
			  noneHolder.setBackground(name.getPlayerColor());		   
			  noneHolder.setPreferredSize(new Dimension(198, 145));
			  buttonPanel.setBackground(name.getPlayerColor());		   
			  buttonPanel.setPreferredSize(new Dimension(198, 53));
			
			  none.add(select, new Integer(0));
			  none.setPreferredSize(new Dimension(198, 141));
			  none.setBorder(new BevelBorder(1, new Color(0, 0, 0), new Color(250, 250, 250)));
				
			  buttons.add(groupMove, new Integer(0));  
			  buttons.add(disband, new Integer(0));  
			  buttons.setPreferredSize(new Dimension(198, 43));
			  buttons.setBorder(new BevelBorder(1, new Color(0, 0, 0), new Color(250, 250, 250)));
				
			  noneHolder.add(none);
			  buttonPanel.add(buttons);  
				
			  name.playerBoard.turnHolder.add(noneHolder);
			  name.playerBoard.turnHolder.add(buttonPanel);
		   }
	    }  
		}

		if(e.getSource() == groupMove)
		{
		   internal = new JInternalFrame("Move group");  
	 	 
         if(name.getColor().equals("Red"))
		      internal.setBackground(new Color(255, 0, 0));    
         else if(name.getColor().equals("Yellow"))
		      internal.setBackground(new Color(255, 255, 0));    
         else if(name.getColor().equals("Blue"))
		      internal.setBackground(new Color(0, 0, 255));    
         else if(name.getColor().equals("Green"))
		      internal.setBackground(new Color(0, 255, 0)); 
				 
	      GameImageIcon arrow1 = getImage("data/arrowLt.png");
         GameImageIcon arrow2 = getImage("data/arrowUp.png");
         GameImageIcon arrow3 = getImage("data/arrowRt.png");
         GameImageIcon arrow4 = getImage("data/arrowDn.png");

  	      lt = new JButton(arrow1);
  	      up = new JButton(arrow2);
  	      rt = new JButton(arrow3);
  	      dw = new JButton(arrow4);

//	        done.addActionListener(this);  
         lt.addActionListener(this);  
	      rt.addActionListener(this);  
	      up.addActionListener(this);  
	      dw.addActionListener(this);  
		 
	      JLayeredPane direction = new JLayeredPane();

		   done.setBounds(20, 145, 70, 20);  
         lt.setBounds(00, 64, 42, 28);  
         rt.setBounds(70, 64, 42, 28);  
         up.setBounds(42, 22, 28, 42);  
         dw.setBounds(42, 92, 28, 42);  

		   showMoves = new JTextArea();
         showMoves.setEditable(false);
		   showMoves.setBounds(20, 00, 72, 20);
		   showMoves.setBorder(new BevelBorder(1, new Color(250, 250, 250), new Color(0, 0, 0)));
         String dispMoves = null;    

		   if(groupObject.getClass().getName() == "Settler")
         {
	         Settler s = (Settler)groupObject;
		      dispMoves = "Moves left: " + s.getMoves();
         }         
		   else// if(groupPeices.get(targetPeice).getClass().getName() == "Army")
		   {
		      Army a = (Army)groupObject;
		      dispMoves = "Moves left: " + a.getMoves();
         }

		   showMoves.setText(dispMoves);  

         direction.add(showMoves);
	      direction.add(up);
	      direction.add(lt);
	      direction.add(rt);
	      direction.add(dw);
	      direction.add(done);

         direction.addKeyListener(this);
         direction.setFocusable(true);
         direction.requestFocusInWindow();

			direction.setPreferredSize(new Dimension(110, 170));
	      internal.add(direction);
	
	      name.playerBoard.turnHolder.removeAll();//(select);    
	      internal.setBorder(new BevelBorder(1, new Color(0, 0, 0), new Color(250, 250, 250)));
		   internal.show();
			name.playerBoard.turnHolder.add(internal);

		   try
		   {internal.setMaximum(true);}
		   catch(PropertyVetoException ex)
		   {}  
//			groupPeices = new Vector(0, 1);
		}
		
		if(e.getSource() == move)
		{
			  internal = new JInternalFrame("Move");  
	 	 
           if(name.getColor().equals("Red"))
		       internal.setBackground(new Color(255, 0, 0));    
           else if(name.getColor().equals("Yellow"))
		       internal.setBackground(new Color(255, 255, 0));    
           else if(name.getColor().equals("Blue"))
		       internal.setBackground(new Color(0, 0, 255));    
           else if(name.getColor().equals("Green"))
		       internal.setBackground(new Color(0, 255, 0)); 
				 
	        GameImageIcon arrow1 = getImage("data/arrowLt.png");
           GameImageIcon arrow2 = getImage("data/arrowUp.png");
           GameImageIcon arrow3 = getImage("data/arrowRt.png");
           GameImageIcon arrow4 = getImage("data/arrowDn.png");

  	        lt = new JButton(arrow1);
  	        up = new JButton(arrow2);
  	        rt = new JButton(arrow3);
  	        dw = new JButton(arrow4);

//	        done.addActionListener(this);  
	        lt.addActionListener(this);  
	        rt.addActionListener(this);  
	        up.addActionListener(this);  
	        dw.addActionListener(this);  
		 
	        JLayeredPane direction = new JLayeredPane();

		     done.setBounds(20, 145, 70, 20);  
           lt.setBounds(00, 64, 42, 28);  
           rt.setBounds(70, 64, 42, 28);  
           up.setBounds(42, 22, 28, 42);  
           dw.setBounds(42, 92, 28, 42);  


		     showMoves = new JTextArea();
           showMoves.setEditable(false);
		     showMoves.setBounds(20, 00, 72, 20);
		     showMoves.setBorder(new BevelBorder(1, new Color(250, 250, 250), new Color(0, 0, 0)));
           String dispMoves = null;    

		     if(groupObject.getClass().getName() == "Settler")
           {
		       Settler s = (Settler)groupObject;
		       dispMoves = "Moves left: " + s.getMoves();
           }         
		     else// if(groupPeices.get(targetPeice).getClass().getName() == "Army")
		     {
		       Army a = (Army)groupObject;
		       dispMoves = "Moves left: " + a.getMoves();
           }

		     showMoves.setText(dispMoves);  
	        direction.add(showMoves);
	        direction.add(up);
	        direction.add(lt);
	        direction.add(rt);
	        direction.add(dw);
	        direction.add(done);

           direction.addKeyListener(this);
           direction.setFocusable(true);
           direction.requestFocusInWindow();

			  direction.setPreferredSize(new Dimension(110, 170));
	        internal.add(direction);
	
	        name.playerBoard.turnHolder.removeAll();//(select);    
	        internal.setBorder(new BevelBorder(1, new Color(0, 0, 0), new Color(250, 250, 250)));
		     internal.show();
			  name.playerBoard.turnHolder.add(internal);
			  
		     try
		     {internal.setMaximum(true);}
		     catch(PropertyVetoException ex)
		     {}  
		}

		if(e.getSource() == group)
      {
        name.playerBoard.turnHolder.removeAll();

        select.setPreferredSize(new Dimension(198, 198));
        internal = new JInternalFrame("Choose units to group");    
        internal.setPreferredSize(new Dimension(198, 198));
		  
		  JLayeredPane p = new JLayeredPane();
		  p.setPreferredSize(new Dimension(198, 198));
		  p.setBounds(0, 0, 198, 198);
		  groupCheck = new JCheckBox[movePeices.size()];   

        Settler s = null;
		  Army a = null;
        int x=0, y=0, count;
		  Object temp = groupObject;
		  if(temp.getClass().getName().equals("Settler"))  
        {
			 s = (Settler)temp;
		    x = s.getX();
			 y = s.getY();
		  }
		  else
        {
			 a = (Army)temp;
		    x = a.getX();
			 y = a.getY();
		  }
		  count = 0;
		  for(int k = 0; k < movePeices.size(); k++)
		  {
		    temp = movePeices.get(k);
			 if(temp.getClass().getName().equals("Settler"))  
          {
			   s = (Settler)temp;
			   if(s.getX() == x && s.getY() == y && (s.getGroup() == 0))  
            {
					groupCheck[k] = new JCheckBox();
	            groupCheck[k].setBounds(20, 20+(count*30), 20, 20);        
               groupCheck[k].setBackground(name.getPlayerColor());
	            JLayeredPane l = new JLayeredPane();

					l = new JLayeredPane();
					JLabel lbl = new JLabel(getImage("data/Settler"+name.getColor()+".png"));
               lbl.setBounds(2, 2, lbl.getIcon().getIconWidth(), lbl.getIcon().getIconHeight());
					
					JTextArea ta = new JTextArea(s.getNum().getText());
					ta.setOpaque(false); 
				   ta.setEditable(false);
				   ta.setForeground(new Color(250, 250, 250));
				   Font f = new Font("Monospaced", Font.BOLD, 8);
				   ta.setFont(f);
               ta.setBounds(9, 2, 10, 10);
     
	            l.add(lbl, new Integer(1));
					l.add(ta, new Integer(2));

	    			l.setBounds(45, 20+(count*30), 25, 23);
               l.setBorder(new BevelBorder(1, new Color(0, 0, 0), new Color(250, 250, 250)));
               l.setOpaque(true);
               l.setBackground(new Color(0, 0, 0));
					
	            p.add(groupCheck[k], new Integer(0)); 
				   p.add(l, new Integer(0));  
				   
					count++;
				}
			 }
			 else
			 {
			   a = (Army)temp;	
			   if(a.getX() == x && a.getY() == y && (a.getGroup() == 0))  
            {
					groupCheck[k] = new JCheckBox();
               groupCheck[k].setBackground(name.getPlayerColor());
	            JLayeredPane l = new JLayeredPane();

					l = new JLayeredPane();
					JLabel lbl = new JLabel(getImage("data/Army"+a.getArmyColor()+".png"));
               lbl.setBounds(6, 2, lbl.getIcon().getIconWidth(), lbl.getIcon().getIconHeight());

					JTextArea ta = new JTextArea(a.getNum().getText());
					ta.setOpaque(false); 
				   ta.setEditable(false);
				   ta.setForeground(a.getTextColor());
				   Font f = new Font("Monospaced", Font.BOLD, 8);
				   ta.setFont(f);
               ta.setBounds(11, 2, 10, 10);

               l.add(lbl, new Integer(1));
					l.add(ta, new Integer(2));

               l.setBorder(new BevelBorder(1, new Color(0, 0, 0), new Color(250, 250, 250)));
               l.setOpaque(true);
               l.setBackground(new Color(0, 0, 0));
               if(count < 4)
					{
		            l.setBounds(45, 20+(count*30), 25, 23);
		            groupCheck[k].setBounds(20, 20+(count*30), 20, 20);        
		            p.add(groupCheck[k], new Integer(0)); 
					   p.add(l, new Integer(0));  
					}
               else
					{
		            l.setBounds(125, 20+((count-4)*30), 25, 23);
		            groupCheck[k].setBounds(100, 20+((count-4)*30), 20, 20);        
		            p.add(groupCheck[k], new Integer(0)); 
					   p.add(l, new Integer(0));  
					}
//System.out.println("COUNT: "+count);
               count++;				
				}
		    }
		  }     

		  groupDone = new JButton("Create group");
	     groupDone.setBounds(5, 145, 180, 20);
		  groupDone.addActionListener(this);
		  p.add(groupDone, new Integer(0));  
   
	     internal.add(p);
        internal.setBorder(new BevelBorder(1, new Color(0, 0, 0), new Color(250, 250, 250)));
        internal.setBackground(name.getPlayerColor()); 
		  internal.show();
		  name.playerBoard.turnHolder.add(internal);

		  group.removeActionListener(this); 
		  try
		  {internal.setMaximum(true);}
		  catch(PropertyVetoException ex)
		  {}  
	   }
		 
      if(e.getSource() == groupDone)
		{
		  name.addNumGroups();
        Settler s = null;
		  Army a = null;
        Object temp; 
		  int x=0, y=0, numSelected = 0;
              
		  for(int k = 0; k < movePeices.size(); k++)
		  {
           if(groupCheck[k] != null && groupCheck[k].isSelected())
              numSelected++;    
		  }
		  if(numSelected > 1)
        {
			  for(int k = 0; k < movePeices.size(); k++)
			  {
	          if(groupCheck[k] != null && groupCheck[k].isSelected())
			    {  
					 temp = movePeices.get(k);
				    if(temp.getClass().getName().equals("Settler"))  
		          {
					   s = (Settler)temp;
				      s.setGroup(name.getNumGroups());  
	//System.out.println("Added Settler to group! "+name.getNumGroups());          
					 }
				    else
		          {
					   a = (Army)temp;
				      a.setGroup(name.getNumGroups());  
	//System.out.println("Added Army to group! "+ name.getNumGroups());          
				    }
				 }
			  }
/*			  Vector tempSettlers = new Vector(0, 1);
			  Vector tempArmies = new Vector(0, 1);
		     Settler tempSett = null;
			  Army tempArmy = null;
			  
			  for(int k = 0; k < movePeices.size(); k++)
           {
		       if(movePeices.get(k).getClass().getName() == "Settler")
			    {
			      tempSett = (Settler)movePeices.get(k);   
				   tempSettlers.add(tempSett);
			    }
			    else
             {
			      tempArmy = (Army)movePeices.get(k);	 
               tempArmies.add(tempArmy);
             }
			  }
           int k;
			  movePeices.removeAllElements();
			  for(k = 0; k < tempSettlers.size(); k++)
			  {
//			    movePeices.remove(k+1);  
				 movePeices.add(k, tempSettlers.remove(0));
			  }
			  for(int i = 0; i < tempArmies.size(); i++, k++)
			  {
			    movePeices.add(k, tempArmies.remove(0));
//			    movePeices.remove(k+1);  
			  }
*/			   
			  groupDone.removeActionListener(this);		 
			  name.playerBoard.turnHolder.removeAll();
			  paintGUI(name.playerBoard.turnHolder);
		  }
		}
		
		if(e.getSource() == disband)
      {
        Settler s = null;
		  Army a = null;
		  Object temp = groupObject;
        int grp = 0; 

		  if(temp.getClass().getName().equals("Settler"))  
        {
			 s = (Settler)temp;
          grp = s.getGroup(); 
//	System.out.println("disbanded group!"+s.getGroup());          
          name.disbandGroup(s.getGroup());
//	System.out.println("disbanded group!"+s.getGroup());          
		  }
		  else
        {
			 a = (Army)temp;
          grp = a.getGroup(); 
//	System.out.println("disbanded group!"+a.getGroup());          
          name.disbandGroup(a.getGroup());
//	System.out.println("disbanded group!"+a.getGroup());          
		  }

/*		  switch(grp)
		  {
	        case 1:
	        	 for(int k = 0; k < grp2.size(); k++)
			    {
               temp = grp2.get(k);
		 		   if(temp.getClass().getName().equals("Settler"))  
        			{
					  s = (Settler)temp;
					  s.setGroup(1);
					}
               else
					{
					  a = (Army)temp;
					  a.setGroup(1);
					}							    
			    }
	        	 for(int k = 0; k < grp3.size(); k++)
			    {
               temp = grp3.get(k);
		 		   if(temp.getClass().getName().equals("Settler"))  
        			{
					  s = (Settler)temp;
					  s.setGroup(2);
					}
               else
					{
					  a = (Army)temp;
					  a.setGroup(2);
					}							    
			    }
	        	 for(int k = 0; k < grp4.size(); k++)
			    {
               temp = grp4.get(k);
		 		   if(temp.getClass().getName().equals("Settler"))  
        			{
					  s = (Settler)temp;
					  s.setGroup(3);
					}
               else
					{
					  a = (Army)temp;
					  a.setGroup(3);
					}							    
			    }
				 break;    
	        case 2:
	        	 for(int k = 0; k < grp3.size(); k++)
			    {
               temp = grp3.get(k);
		 		   if(temp.getClass().getName().equals("Settler"))  
        			{
					  s = (Settler)temp;
					  s.setGroup(2);
					}
               else
					{
					  a = (Army)temp;
					  a.setGroup(2);
					}							    
			    }
	        	 for(int k = 0; k < grp4.size(); k++)
			    {
               temp = grp4.get(k);
		 		   if(temp.getClass().getName().equals("Settler"))  
        			{
					  s = (Settler)temp;
					  s.setGroup(3);
					}
               else
					{
					  a = (Army)temp;
					  a.setGroup(3);
					}							    
			    }
				 break;    
	        case 3:
	        	 for(int k = 0; k < grp4.size(); k++)
			    {
               temp = grp4.get(k);
		 		   if(temp.getClass().getName().equals("Settler"))  
        			{
					  s = (Settler)temp;
					  s.setGroup(3);
					}
               else
					{
					  a = (Army)temp;
					  a.setGroup(3);
					}							    
			    }
				 break;    
		  }
*/
		  name.playerBoard.turnHolder.removeAll();
        disband.removeActionListener(this); 
		  name.subNumGroups();
		  paintGUI(name.playerBoard.turnHolder);
		}
		
		if(e.getSource() == turnOver)
      {
//	     name.playerBoard.turnHolder.remove(format);
        removeBoardListeners();
        name.playerBoard.turnHolder.remove(select);
 		  playerDone = true;
//        playerWait();
        turnOver.removeActionListener(this);
        name.getPlayerUI().getTurn().researchTurn(name);
	   }

		if(e.getSource() == done)
      {
          removeBoardListeners();
		    if(groupObject.getClass().getName() == "Settler")
          {
		      Settler s;
		      s = (Settler)groupObject;
            if(name.playerUI.lb.getTerrain(s.getX(), s.getY()).equals("Water") && !name.getWaterLand())        
				{
				  JOptionPane illegal = new JOptionPane();
				  GameImageIcon im = getImage("data/"+name.getNation()+"Icon.png");  
		        illegal.showMessageDialog(name.playerUI.map, "YOU CANNOT LAND IN WATER WITH CURRENT TECHNOLOGY!\nMOVE UNIT TO LAND", "CANNOT END MOVE HERE",
				  					 			   JOptionPane.INFORMATION_MESSAGE, im);	 	 
				}
            else if(name.playerUI.lb.isCitySpace(s.getX(), s.getY()))        
				{
				  JOptionPane illegal = new JOptionPane();
				  GameImageIcon im = getImage("data/"+name.getNation()+"Icon.png");  
		        illegal.showMessageDialog(name.playerUI.map, "YOU CANNOT END MOVE IN CITY CENTER", "CANNOT END MOVE HERE",
				  					 			   JOptionPane.INFORMATION_MESSAGE, im);	 	 
				}
				else
				  resetSettler(s);        
		    }  
			 else if(groupObject.getClass().getName() == "Army")
		    {
		      Army a;
		      a = (Army)groupObject;
            if(name.playerUI.lb.getTerrain(a.getX(), a.getY()).equals("Water") && !name.getWaterLand())        
				{
				  JOptionPane illegal = new JOptionPane();
				  GameImageIcon im = getImage("data/"+name.getNation()+"Icon.png");  
		        illegal.showMessageDialog(name.playerUI.map, "YOU CANNOT LAND IN WATER WITH CURRENT TECHNOLOGY!\nMOVE UNIT TO LAND", "CANNOT END MOVE HERE",
				  					 			   JOptionPane.INFORMATION_MESSAGE, im);	 	 
				}
            else if(name.playerUI.lb.isCitySpace(a.getX(), a.getY()))        
				{
				  JOptionPane illegal = new JOptionPane();
				  GameImageIcon im = getImage("data/"+name.getNation()+"Icon.png");  
		        illegal.showMessageDialog(name.playerUI.map, "YOU CANNOT END MOVE IN CITY CENTER", "CANNOT END MOVE HERE",
				  					 			   JOptionPane.INFORMATION_MESSAGE, im);	 	 
				}
				else
				  resetArmy(a);
			 }
		}
	   else if(e.getSource() == lt)
      {
		  Settler s;
		  Army a;
//        sourcePeice--;

		    if(groupObject.getClass().getName() == "Settler")
          {
	         s = (Settler)groupObject;
		      if(s.getMoves() != 0)
			   {        
				  moveDirection(s, name, 0);
		        showMoves.setText("Moves left: " + s.getMoves());  
			   }         
		    }  
			 else if(groupObject.getClass().getName() == "Army")
		    {
		      a = (Army)groupObject;
            if(a.getMoves() != 0)
				{        
				  moveDirection(a, name, 0);
              showMoves.setText("Moves left: " + a.getMoves());  
            }
			 }
	   }
  	   else if(e.getSource() == rt)
      {
//        sourcePeice--;
		  Settler s;
		  Army a;

		    if(groupObject.getClass().getName() == "Settler")
          {
		      s = (Settler)groupObject;
            if(s.getMoves() != 0)
				{        
				  moveDirection(s, name, 2);
		        showMoves.setText("Moves left: " + s.getMoves());  
            }         
		    }  
		    else if(groupObject.getClass().getName() == "Army")
		    {
		      a = (Army)groupObject;
            if(a.getMoves() != 0)
				{        
				  moveDirection(a, name, 2);
              showMoves.setText("Moves left: " + a.getMoves());  
            }
			 }
	   }
	   else if(e.getSource() == up)
      {
//        sourcePeice--;
		  Settler s;
		  Army a;
		    if(groupObject.getClass().getName() == "Settler")
          {
		      s = (Settler)groupObject;
            if(s.getMoves() != 0)
				{        
				  moveDirection(s, name, 1);
		        showMoves.setText("Moves left: " + s.getMoves());  
            }         
		    }  
		    else if(groupObject.getClass().getName() == "Army")
		    {
		      a = (Army)groupObject;
            if(a.getMoves() != 0)
				{        
				  moveDirection(a, name, 1);
              showMoves.setText("Moves left: " + a.getMoves());  
            }
			 }
	   }
	   else if(e.getSource() == dw)
      {
//        sourcePeice--;
		  Settler s;
		  Army a;
		    if(groupObject.getClass().getName() == "Settler")
          {
		      s = (Settler)groupObject;
            if(s.getMoves() != 0)
				{        
				  moveDirection(s, name, 3);
		        showMoves.setText("Moves left: " + s.getMoves());  
            }         
          }		     
		    else if(groupObject.getClass().getName() == "Army")
		    {
		      a = (Army)groupObject;
            if(a.getMoves() != 0)
				{        
				  moveDirection(a, name, 3);
              showMoves.setText("Moves left: " + a.getMoves());  
            }
		    }  
      }	

   }//end mouseClicked
	public void mouseEntered(MouseEvent e){}//end mouseEntered
	public void mousePressed(MouseEvent e){}//end mousePressed
	public void mouseReleased(MouseEvent e){}//end mouseReleased
	public void mouseExited(MouseEvent e){}//end mouseExited

   public synchronized void playerWait()
	{
	  while(!playerDone)
	  {
//       System.out.println(name.getNation()+"is MOVE WAITING!");
		 try{wait(1000);}
	    catch(InterruptedException ex){System.out.println("EXCEPTION");}
	  }
	  notify();
	}

   public void resetSettler(Settler s)
	{
     Vector tempPeices = new Vector(0, 1);
     if(s.getGroup() > 0)
	  {
		  for(int k = 0; k < movePeices.size(); k++)
		  {
	       Object temp = movePeices.get(k);
			 if(temp.getClass().getName().equals("Settler"))
		    {
			   Settler s2 = (Settler)temp;
				if((s2.getGroup() == s.getGroup() && s2 != s) || (s2.getMoves() == 0 && s2 != s))
				{
              s2.combatMoves();
              tempPeices.add(s2);
				}
			 }
		    else
			 {
			   Army a2 = (Army)temp;
				if((a2.getGroup() == s.getGroup()) || (a2.getMoves() == 0))
				{
              a2.combatMoves();
              tempPeices.add(a2);
				}
			 }
		  } 
	   }

		for(int k = 0; k < movePeices.size(); k++)
		{
	     Object temp = movePeices.get(k);
		  if(temp.getClass().getName().equals("Settler"))
		  {
			 Settler s2 = (Settler)temp;
			 if((s2.getMoves() == 0) && s2 != s)
			 {
            tempPeices.add(s2);
			 }
		  }
		  else
		  {
		    Army a2 = (Army)temp;
		 	 if(a2.getMoves() == 0)
			 {
            tempPeices.add(a2);
			 }
		  }
		} 

      for(int k = 0; k < tempPeices.size(); k++)
		{
         movePeices.remove(tempPeices.get(k));      
//			movePeices.size()--;
		}
      s.combatMoves();
	   moveClean(s);
      movePeices.remove(s);
//      s.resetMoves(name);
//	   movePeices.size()--;
	   name.playerBoard.turnHolder.remove(internal);
      name.playerBoard.turnHolder.remove(select);
      name.playerUI.lb.setOwner(s.getX(), s.getY(), name);  
//      turnClean(s.getX(), s.getY());
    	if(movePeices.size() > 0)    
  	     paintGUI(name.playerBoard.turnHolder);
      else
		{
//	     moveClean();
        endTurn();
	   }
	}

   public void resetArmy(Army a)
	{
     Vector tempPeices = new Vector(0, 1);
     if(a.getGroup() > 0)
	  {
        int count = movePeices.size(); 
		  for(int k = 0; k < movePeices.size(); k++)
		  {
	       Object temp = movePeices.get(k);
			 if(temp.getClass().getName().equals("Settler"))
		    {
			   Settler s2 = (Settler)temp;
				if((s2.getGroup() == a.getGroup()) || (s2.getMoves() == 0))
				{
              tempPeices.add(s2);
              s2.combatMoves();
				}
			 }
		    else
			 {
			   Army s2 = (Army)temp;
				if((s2.getGroup() == a.getGroup() && s2 != a) || (s2.getMoves() == 0 && s2 != a))
				{
              tempPeices.add(s2);
              s2.combatMoves();
				}
			 }
		  } 
	   }

 	   for(int k = 0; k < movePeices.size(); k++)
		{
	       Object temp = movePeices.get(k);
			 if(temp.getClass().getName().equals("Settler"))
		    {
			   Settler s2 = (Settler)temp;
				if(s2.getMoves() == 0)
				{
              tempPeices.add(s2);
				}
			 }
		    else
			 {
			   Army s2 = (Army)temp;
				if((s2.getMoves() == 0) && s2 != a)
				{
              tempPeices.add(s2);
				}
			 }
		}

      for(int k = 0; k < tempPeices.size(); k++)
		{
         movePeices.remove(tempPeices.get(k));      
	//		movePeices.size()--;
		}
      a.combatMoves();
	   moveClean(a);
      movePeices.remove(a);
//		a.resetMoves(name);
//		movePeices.size()--;
		name.playerBoard.turnHolder.remove(internal);
      name.playerBoard.turnHolder.remove(select);
      name.playerUI.lb.setOwner(a.getX(), a.getY(), name);  
//      turnClean(a.getX(), a.getY());
      if(movePeices.size() > 0)    
	     paintGUI(name.playerBoard.turnHolder);
      else
		{ 
//	     moveClean();
        endTurn();
	   }
	}
 
   public void endTurn()
	{
        Vector arm = name.getArmyVector();
		  Army temp;
		  for(int k = 0; k < arm.size(); k++)
        {
		    temp = (Army) arm.get(k);
	       endClean(temp);
			 temp.resetMoves(name);
		  }
        Vector sett = name.getSettlerVector();
		  Settler tempSett;
		  for(int k = 0; k < sett.size(); k++)
        {
		    tempSett = (Settler) sett.get(k);
	       endClean(tempSett);
			 tempSett.resetMoves(name);
		  }
		  playerDone = true;    
//        playerWait();    
        name.getPlayerUI().getTurn().researchTurn(name);
	}

   public void combatPause()
	{
		for(int i = 0; i < movePeices.size(); i++)
  		{  
		  Object temp =  movePeices.get(i);
		  if(temp.getClass().getName().equals("Settler"))
		  {
		    Settler s = (Settler)temp;
		    if(s.getGroup() == 0)
          {
//		      nm[i].removeActionListener(this);  
	       }  
		  }  
        else
		  {
		    Army a = (Army)temp;
		    if(a.getGroup() == 0)
          {
//		      nm[i].removeActionListener(this);  
		    }
		  }
		}  
		done.removeActionListener(this);
      lt.removeActionListener(this);  
      rt.removeActionListener(this);  
      up.removeActionListener(this);  
      dw.removeActionListener(this);  
	}
	
	public void combatResume(String win)
	{
		if(win.equals("SettlerOnly"))
		{
		/*do nothing(except reset listeners at bottom of this method and add codeoflaw gold)*/
        if(name.getTechVector().get(1) != null)
		  {
		     name.addTechGold(1, 1);   
		  }
		} 
      else if(win.equals("attacker") && (name.playerUI.lb.hasVillage(combatUnit.getX(), combatUnit.getY()) == 0))
      {
	     Player loser = name.playerUI.lb.getOwner(combatUnit.getX(), combatUnit.getY());
		  Vector a = loser.getArmyVector();
		  Vector s = loser.getSettlerVector();
		  int k;
		  for(k = 0; k < a.size(); k++)
		  {
		    Army temp = (Army) a.get(k);  
			 if((temp.getX() == combatUnit.getX()) && (temp.getY() == combatUnit.getY()))
		    {
			   loser.removeArmy(temp);
		      name.playerUI.lb.subArmyCount(combatUnit.getX(), combatUnit.getY());  
			   name.playerUI.lb.getHolder(combatUnit.getX(), combatUnit.getY()).remove(temp.getLabel());
				lb.getHolder(combatUnit.getX(), combatUnit.getY()).validate();
				lb.getHolder(combatUnit.getX(), combatUnit.getY()).repaint();
			 }
		  } 
		  for(k = 0; k < s.size(); k++)
		  {
          Settler temp = (Settler) s.get(k); 
		    if((temp.getX() == combatUnit.getX()) && (temp.getY() == combatUnit.getY()))
          {
		      JLayeredPane lp = name.playerUI.lb.getHolder(combatUnit.getX(), combatUnit.getY());    
				loser.removeSettler(temp);
		      name.playerUI.lb.subSettlerCount(combatUnit.getX(), combatUnit.getY());  
			   name.playerUI.lb.getHolder(combatUnit.getX(), combatUnit.getY()).remove(temp.getLabel());
				lb.getHolder(combatUnit.getX(), combatUnit.getY()).validate();
				lb.getHolder(combatUnit.getX(), combatUnit.getY()).repaint();
  			 }
		  } 
		  name.playerUI.lb.setOwner(combatUnit.getX(), combatUnit.getY(), name);
        if(name.getTechVector().get(1) != null)
		  {
		     name.addTechGold(1, 1);   
		  }
        if(name.getNation().equals("Rome") && name.playerUI.lb.isCitySpace(combatUnit.getX(), combatUnit.getY()))
          name.getPlayerUI().advanceCultureTrack(1, 0, "Rome");//int num, int player, String name)			
	     if(name.getNation().equals("China"))
		    name.addCulture(3);  
//	     resetArmy(combatUnit);
      }
		else if(win.equals("attacker") && (name.playerUI.lb.hasVillage(combatUnit.getX(), combatUnit.getY()) > 0))
		{
 		  String type = name.playerUI.lb.getVillageResource(combatUnit.getX(), combatUnit.getY());
		  name.addResource(type, "village"); 
		  name.playerUI.tellVillageResource(type);   
		  name.playerBoard.setVillage(1);
        Player[] tempArray = name.getPlayerUI().getPlayers();
 		  for(int k = 0; k < 2; k++)
		  {
			  tempArray[k].playerUI.lb.removeVillage(combatUnit.getX(), combatUnit.getY());
			  tempArray[k].playerUI.lb.setOwner(combatUnit.getX(), combatUnit.getY(), name);
        }
//        name.redrawUnitHand();
        if(name.getTechVector().get(1) != null)
		  {
		     name.addTechGold(1, 1);   
		  }
        if(name.getNation().equals("Rome"))
          name.getPlayerUI().advanceCultureTrack(1, 0, "Rome");//int num, int player, String name)			
	     if(name.getNation().equals("China"))
		    name.addCulture(3);  
		}
		else if(win.equals("defender"))
		{
		  Vector a = name.getArmyVector();
        Vector s = name.getSettlerVector();
		  int k;
		  for(k = 0; k < a.size(); k++)
		  {
		    Army temp = (Army) a.get(k);  
			 if((temp.getX() == combatUnit.getX()) && (temp.getY() == combatUnit.getY()))
		    {
			   name.removeArmy(temp);
		      name.playerUI.lb.subArmyCount(combatUnit.getX(), combatUnit.getY());  
			   name.playerUI.lb.getHolder(combatUnit.getX(), combatUnit.getY()).remove(temp.getLabel());
				lb.getHolder(combatUnit.getX(), combatUnit.getY()).validate();
				lb.getHolder(combatUnit.getX(), combatUnit.getY()).repaint();
			 }
		  }
		  for(k = 0; k < s.size(); k++)
		  {
		    Settler temp = (Settler) s.get(k);  
			 if((temp.getX() == combatUnit.getX()) && (temp.getY() == combatUnit.getY()))
		    {
			   name.removeSettler(temp);
		      name.playerUI.lb.subSettlerCount(combatUnit.getX(), combatUnit.getY());  
			   name.playerUI.lb.getHolder(combatUnit.getX(), combatUnit.getY()).remove(temp.getLabel());
				lb.getHolder(combatUnit.getX(), combatUnit.getY()).validate();
				lb.getHolder(combatUnit.getX(), combatUnit.getY()).repaint();
			 }
		  } 
        if(name.getTechVector().get(1) != null)
		  {
		     name.addTechGold(1, 1);   
		  }
		}
		else if(win.equals("Village"))
	   {
		  Vector a = name.getArmyVector();
		  Vector s = name.getSettlerVector();
		  int k;
		  for(k = 0; k < a.size(); k++)
		  {
		    name.playerUI.lb.setOwner(combatUnit.getX(), combatUnit.getY(), null);
		    Army temp = (Army) a.get(k);  
			 if((temp.getX() == combatUnit.getX()) && (temp.getY() == combatUnit.getY()))
		    {
			   name.removeArmy(temp);
		      name.playerUI.lb.subArmyCount(combatUnit.getX(), combatUnit.getY());  
			   name.playerUI.lb.getHolder(combatUnit.getX(), combatUnit.getY()).remove(temp.getLabel());
				lb.getHolder(combatUnit.getX(), combatUnit.getY()).validate();
				lb.getHolder(combatUnit.getX(), combatUnit.getY()).repaint();
			 }
		  } 
		  for(k = 0; k < s.size(); k++)
		  {
		    name.playerUI.lb.setOwner(combatUnit.getX(), combatUnit.getY(), null);
		    Settler temp = (Settler) s.get(k);  
			 if((temp.getX() == combatUnit.getX()) && (temp.getY() == combatUnit.getY()))
		    {
			   name.removeSettler(temp);
		      name.playerUI.lb.subSettlerCount(combatUnit.getX(), combatUnit.getY());  
			   name.playerUI.lb.getHolder(combatUnit.getX(), combatUnit.getY()).remove(temp.getLabel());
				lb.getHolder(combatUnit.getX(), combatUnit.getY()).validate();
				lb.getHolder(combatUnit.getX(), combatUnit.getY()).repaint();
			 }
		  } 
		}
		
		for(int i = 0; i < movePeices.size(); i++)
  		{  
		  Object temp =  movePeices.get(i);
		  if(temp.getClass().getName().equals("Settler"))
		  {
		    Settler s = (Settler)temp;
		    if(s.getGroup() == 0)
          {  
//		      nm[i].addActionListener(this);  
          }
 	     }  
        else
		  {
		    Army a = (Army)temp;
		    if(a.getGroup() == 0)
          {
//		      nm[i].addActionListener(this);  
          } 
		  }
		}  
      done.addActionListener(this);  
      lt.addActionListener(this);  
	   rt.addActionListener(this);  
	   up.addActionListener(this);  
	   dw.addActionListener(this);  
//		lb.setOwner(combatUnit.getX(), combatUnit.getY(), name);
	   resetArmy(combatUnit);
	   combatUnit = null;
//	   name.redrawUnitHand();
	}
	  
   public Army getCombatUnit()
	{
	  if(combatUnit != null)
	    return combatUnit;
	  return null;	  
	}
	
	public void moveDirection(Object obj, Player name, int direction)
	{	  
	   Settler s = null;
		Army a = null;
      boolean conductCombat = false;
		
		if(obj.getClass().getName() == "Settler")
		{
		  s = (Settler)obj;
		}
		else if(obj.getClass().getName() == "Army")
		{
		  a = (Army)obj;
		}
		
		int dir = -1;
	       int spaceTotalUnits = 0;    
			 if(direction == 0)  
		    {
			   if((s != null) && (s.getX()-1 >= 0))
			   {
				  spaceTotalUnits = name.playerUI.lb.getSettlerCount(s.getX()-1, s.getY()) + name.playerUI.lb.getArmyCount(s.getX()-1, s.getY());
				  if((name.playerUI.lb.getOwner(s.getX()-1, s.getY()) != null) && (name != name.playerUI.lb.getOwner(s.getX()-1, s.getY())))
				  {
                conductCombat = true;
				  }
				  if(conductCombat || ((spaceTotalUnits >= name.getStacklimit()) || (spaceTotalUnits + name.getGrouptotal(s.getGroup()) > name.getStacklimit()) || (name.playerUI.lb.isCitySpace(s.getX()-1, s.getY()) && s.getMoves() < 2)))
				  {
				    dir = -1;
				    //System.out.println(name.getGrouptotal(s.getGroup()));
				  }
				  else
				  {
                if(name.playerUI.lb.getTerrain(s.getX(), s.getY()).equals("Water") && !name.getWaterLand() && (s.getMoves() <= 1) && (name.playerUI.lb.getTerrain(s.getX()-1, s.getY()) == null || name.playerUI.lb.checkExplored(s.getX()-1, s.getY())))
					 {
					   JOptionPane illegal = new JOptionPane();
				      GameImageIcon im = getImage("data/"+name.getNation()+"Icon.png");  
		            illegal.showMessageDialog(name.playerUI.map, "YOU CANNOT LAND IN WATER WITH CURRENT TECHNOLOGY!\nMOVE UNIT TO LAND", "CANNOT END MOVE HERE",
				  					 			        JOptionPane.INFORMATION_MESSAGE, im);	 	 
					 }            
				    else if(name.playerUI.lb.checkExplored(s.getX()-1, s.getY()))
					 {
                  Player[] tempArray = name.getPlayerUI().getPlayers();            
						if(name.playerUI.lb.getHolder(s.getX(), s.getY()).getParent().getName().equals("panel4"))
					   {
						   for(int k = 0; k < 2; k++)//number of players
					      {
						     tempArray[k].playerUI.lb.populateTile(name.getPlayerUI().boardPosition[0], 0, 0, tempArray[k]);
							  tempArray[k].playerUI.map.remove(tempArray[k].playerUI.myfog1);
							  tempArray[k].playerUI.map.add(tempArray[k].playerUI.mypanel0, 0);
					        tempArray[k].playerUI.map.validate();
					        tempArray[k].playerUI.map.repaint();
							}
  						}    
		            else if(name.playerUI.lb.getHolder(s.getX(), s.getY()).getParent().getName().equals("panel5"))
					   {
						   for(int k = 0; k < 2; k++)//number of players
					      {
						     tempArray[k].playerUI.lb.populateTile(name.getPlayerUI().boardPosition[1], 1, 0, tempArray[k]);
							  tempArray[k].playerUI.map.remove(tempArray[k].playerUI.myfog2);
							  tempArray[k].playerUI.map.add(tempArray[k].playerUI.mypanel1, 2);
					        tempArray[k].playerUI.map.validate();
					        tempArray[k].playerUI.map.repaint();
							}
  						}    
		            else if(name.playerUI.lb.getHolder(s.getX(), s.getY()).getParent().getName().equals("panel6"))
					   {
						   for(int k = 0; k < 2; k++)//number of players
					      {
						     tempArray[k].playerUI.lb.populateTile(name.getPlayerUI().boardPosition[2], 2, 0, tempArray[k]);
							  tempArray[k].playerUI.map.remove(tempArray[k].playerUI.myfog4);
							  tempArray[k].playerUI.map.add(tempArray[k].playerUI.mypanel2, 4);
					        tempArray[k].playerUI.map.validate();
					        tempArray[k].playerUI.map.repaint();
							}
						}
				      dir = 0;	
					   s.takeMove();  

						if(s.getGroup() > 0)
						{
						  switch(s.getGroup())
						  {
						    case 1:
								  for(int k = 0; k < grp1.size(); k++)
								  {
								    Object temp = grp1.get(k);
									 if(temp.getClass().getName().equals("Settler"))
								    {
									   Settler s2 = (Settler)temp;
										if(s2.getGroup() == s.getGroup() && s2 != s)
										{
						   			  s2.takeMove();  
										}
									 }
								    else
									 {
									   Army s2 = (Army)temp;
										if(s2.getGroup() == s.getGroup())
										{
										  s2.takeMove();
										}
									 }
						        } 
						        break;  
						    case 2:
								  for(int k = 0; k < grp2.size(); k++)
								  {
								    Object temp = grp2.get(k);
									 if(temp.getClass().getName().equals("Settler"))
								    {
									   Settler s2 = (Settler)temp;
										if(s2.getGroup() == s.getGroup() && s2 != s)
										{
						   			  s2.takeMove();  
										}
									 }
								    else
									 {
									   Army s2 = (Army)temp;
										if(s2.getGroup() == s.getGroup())
										{
										  s2.takeMove();
										}
									 }
						        } 
						        break;  
						    case 3:
								  for(int k = 0; k < grp3.size(); k++)
								  {
								    Object temp = grp3.get(k);
									 if(temp.getClass().getName().equals("Settler"))
								    {
									   Settler s2 = (Settler)temp;
										if(s2.getGroup() == s.getGroup() && s2 != s)
										{
						   			  s2.takeMove();  
										}
									 }
								    else
									 {
									   Army s2 = (Army)temp;
										if(s2.getGroup() == s.getGroup())
										{
										  s2.takeMove();
										}
									 }
						        } 
						        break;  
						    case 4:
								  for(int k = 0; k < grp4.size(); k++)
								  {
								    Object temp = grp4.get(k);
									 if(temp.getClass().getName().equals("Settler"))
								    {
									   Settler s2 = (Settler)temp;
										if(s2.getGroup() == s.getGroup() && s2 != s)
										{
						   			  s2.takeMove();  
										}
									 }
								    else
									 {
									   Army s2 = (Army)temp;
										if(s2.getGroup() == s.getGroup())
										{
										  s2.takeMove();
										}
									 }
						        } 
						        break;  
							 }
						}
						if(s.getMoves() == 0)
		            {
                    resetSettler(s);
				      }
					 }
				    else
					 {
	               if((name.playerUI.lb.hasHut(s.getX()-1, s.getY()) > 0) && name.getCurrentGov() != 1)
						{
						  dir = -1;
						}           
						else if(name.playerUI.lb.hasVillage(s.getX()-1, s.getY()) > 0)
						{
						  dir = -1;
						}
						else if(name.playerUI.lb.getTerrain(s.getX()-1, s.getY()).equals("Water") && !name.getWaterCross())    
						{
						  dir = -1;
						}
	               else if(name.playerUI.lb.getTerrain(s.getX()-1, s.getY()).equals("Water") && !name.getWaterLand() && (s.getMoves() < 2)) 
						{
						  dir = -1;
						}          
                  else
						{
                    Player[] tempArray = name.getPlayerUI().getPlayers();
						  s.setBounds(spaceTotalUnits);
						  for(int k = 0; k < 2; k++)//number of players
					     {
							  if(tempArray[k] == name)
							  {
								  tempArray[k].playerUI.lb.getHolder(s.getX(), s.getY()).add(s.getLabel(), new Integer(5));    
		                    tempArray[k].playerUI.lb.getHolder(s.getX()-1, s.getY()).add(s.getLabel(), new Integer(5));  
		                    tempArray[k].playerUI.lb.subSettlerCount(s.getX(), s.getY()); 
					           if(((tempArray[k].playerUI.lb.getSettlerCount(s.getX(), s.getY()) + tempArray[k].playerUI.lb.getArmyCount(s.getX(), s.getY())) == 0) && !tempArray[k].playerUI.lb.getHasBuild(s.getX(), s.getY()))      
								    tempArray[k].playerUI.lb.setOwner(s.getX(), s.getY(), null);  
		     			        tempArray[k].playerUI.lb.addSettlerCount(s.getX()-1, s.getY());
//		                    turnClean(s.getX()+1, s.getY());
							  }
							  else
							  {
								  JLabel l = new JLabel(s.getIcon());
								  l.setBounds(s.getCurrentbounds());
								  l.setName(s.getLabelName());
		                    tempArray[k].playerUI.lb.getHolder(s.getX()-1, s.getY()).add(l, new Integer(5));  
		     			        tempArray[k].playerUI.lb.addSettlerCount(s.getX()-1, s.getY());
		                    leaveClean(s.getX(), s.getY(), s);
                          tempArray[k].unitSettlerLabels.add(l);
//		                    turnClean(s.getX()+1, s.getY());
		                    tempArray[k].playerUI.lb.subSettlerCount(s.getX(), s.getY()); 
					           if(((tempArray[k].playerUI.lb.getSettlerCount(s.getX(), s.getY()) + tempArray[k].playerUI.lb.getArmyCount(s.getX(), s.getY())) == 0) && !tempArray[k].playerUI.lb.getHasBuild(s.getX(), s.getY()))      
								    tempArray[k].playerUI.lb.setOwner(s.getX(), s.getY(), null);  
                       }
                    }

						  if(s.getGroup() > 0)
						  {
						    switch(s.getGroup())
						    {
						      case 1:
								  for(int k = 0; k < grp1.size(); k++)
								  {
								    Object temp = grp1.get(k);
									 if(temp.getClass().getName().equals("Settler"))
								    {
									   Settler s2 = (Settler)temp;
										if(s2.getGroup() == s.getGroup() && s2 != s)
										{
										  s2.setBounds(spaceTotalUnits);
										  for(int i = 0; i < 2; i++)//number of players
									     {
											  if(tempArray[i] == name)
											  {
												  tempArray[i].playerUI.lb.getHolder(s2.getX(), s2.getY()).add(s2.getLabel(), new Integer(5));    
						                    tempArray[i].playerUI.lb.getHolder(s2.getX()-1, s2.getY()).add(s2.getLabel(), new Integer(5));  
						                    tempArray[i].playerUI.lb.subSettlerCount(s2.getX(), s2.getY()); 
									           if(((tempArray[i].playerUI.lb.getSettlerCount(s2.getX(), s2.getY()) + tempArray[i].playerUI.lb.getArmyCount(s2.getX(), s2.getY())) == 0) && !tempArray[i].playerUI.lb.getHasBuild(s2.getX(), s2.getY()))      
												    tempArray[i].playerUI.lb.setOwner(s2.getX(), s2.getY(), null);  
						     			        tempArray[i].playerUI.lb.addSettlerCount(s2.getX()-1, s2.getY());
//						                    turnClean(s2.getX()-1, s2.getY());
											  }
											  else
											  {
												  JLabel l = new JLabel(s2.getIcon());
												  l.setBounds(s2.getCurrentbounds());
												  l.setName(s2.getLabelName());
						                    tempArray[i].playerUI.lb.getHolder(s2.getX()-1, s2.getY()).add(l, new Integer(5));  
						     			        tempArray[i].playerUI.lb.addSettlerCount(s2.getX()-1, s2.getY());
						                    leaveClean(s2.getX(), s2.getY(), s2);
				                          tempArray[i].unitSettlerLabels.add(l);
//						                    turnClean(s2.getX()-1, s2.getY());
						                    tempArray[i].playerUI.lb.subSettlerCount(s2.getX(), s2.getY()); 
									           if(((tempArray[i].playerUI.lb.getSettlerCount(s2.getX(), s2.getY()) + tempArray[i].playerUI.lb.getArmyCount(s2.getX(), s2.getY())) == 0) && !tempArray[i].playerUI.lb.getHasBuild(s2.getX(), s2.getY()))      
												    tempArray[i].playerUI.lb.setOwner(s2.getX(), s2.getY(), null);  
				                       }
				                    }
					                 s2.setPosition(s2.getX()-1, s2.getY());
				                    turnClean(s2.getX()+1, s2.getY());
	                             turnClean(s.getX(), s.getY());
										  s2.takeMove();  
										}
								    }
								    else
									 {
									   Army s2 = (Army)temp;
										if(s2.getGroup() == s.getGroup())
										{
										  s2.setBounds(name.playerUI.lb.getSettlerCount(s2.getX()-1, s2.getY()), name.playerUI.lb.getArmyCount(s2.getX()-1, s2.getY()));//spaceTotalUnits);
										  for(int i = 0; i < 2; i++)//number of players
									     {
											  if(tempArray[i] == name)
											  {
												  tempArray[i].playerUI.lb.getHolder(s2.getX(), s2.getY()).add(s2.getLabel(), new Integer(5));    
						                    tempArray[i].playerUI.lb.getHolder(s2.getX()-1, s2.getY()).add(s2.getLabel(), new Integer(5));  
						                    tempArray[i].playerUI.lb.subArmyCount(s2.getX(), s2.getY()); 
									           if(((tempArray[i].playerUI.lb.getSettlerCount(s2.getX(), s2.getY()) + tempArray[i].playerUI.lb.getArmyCount(s2.getX(), s2.getY())) == 0) && !tempArray[i].playerUI.lb.getHasBuild(s2.getX(), s2.getY()))      
												    tempArray[i].playerUI.lb.setOwner(s2.getX(), s2.getY(), null);  
						     			        tempArray[i].playerUI.lb.addArmyCount(s2.getX()-1, s2.getY());
//						                    turnClean(s2.getX()-1, s2.getY());
											  }
											  else
											  {
												  JLabel l = new JLabel(s2.getIcon());
												  l.setBounds(s2.getCurrentbounds());
												  l.setName(s2.getLabelName());
						                    tempArray[i].playerUI.lb.getHolder(s2.getX()-1, s2.getY()).add(l, new Integer(5));  
						     			        tempArray[i].playerUI.lb.addArmyCount(s2.getX()-1, s2.getY());
						                    leaveClean(s2.getX(), s2.getY(), s2);
				                          tempArray[i].unitArmyLabels.add(l);
//						                    turnClean(s2.getX()-1, s2.getY());
						                    tempArray[i].playerUI.lb.subArmyCount(s2.getX(), s2.getY()); 
									           if(((tempArray[i].playerUI.lb.getSettlerCount(s2.getX(), s2.getY()) + tempArray[i].playerUI.lb.getArmyCount(s2.getX(), s2.getY())) == 0) && !tempArray[i].playerUI.lb.getHasBuild(s2.getX(), s2.getY()))      
												    tempArray[i].playerUI.lb.setOwner(s2.getX(), s2.getY(), null);  
				                       }
				                    }
							           s2.setPosition(s2.getX()-1, s2.getY());
				                    turnClean(s2.getX()+1, s2.getY());
				                    turnClean(s.getX(), s.getY());
										  s2.takeMove();  
										}
									 }
						        } 
						        break;  
						    case 2:
								  for(int k = 0; k < grp2.size(); k++)
								  {
								    Object temp = grp2.get(k);
									 if(temp.getClass().getName().equals("Settler"))
								    {
									   Settler s2 = (Settler)temp;
										if(s2.getGroup() == s.getGroup() && s2 != s)
										{
										  s2.setBounds(spaceTotalUnits);
										  for(int i = 0; i < 2; i++)//number of players
									     {
											  if(tempArray[i] == name)
											  {
												  tempArray[i].playerUI.lb.getHolder(s2.getX(), s2.getY()).add(s2.getLabel(), new Integer(5));    
						                    tempArray[i].playerUI.lb.getHolder(s2.getX()-1, s2.getY()).add(s2.getLabel(), new Integer(5));  
						                    tempArray[i].playerUI.lb.subSettlerCount(s2.getX(), s2.getY()); 
									           if(((tempArray[i].playerUI.lb.getSettlerCount(s2.getX(), s2.getY()) + tempArray[i].playerUI.lb.getArmyCount(s2.getX(), s2.getY())) == 0) && !tempArray[i].playerUI.lb.getHasBuild(s2.getX(), s2.getY()))      
												    tempArray[i].playerUI.lb.setOwner(s2.getX(), s2.getY(), null);  
						     			        tempArray[i].playerUI.lb.addSettlerCount(s2.getX()-1, s2.getY());
//						                    turnClean(s2.getX()-1, s2.getY());
											  }
											  else
											  {
												  JLabel l = new JLabel(s2.getIcon());
												  l.setBounds(s2.getCurrentbounds());
												  l.setName(s2.getLabelName());
						                    tempArray[i].playerUI.lb.getHolder(s2.getX()-1, s2.getY()).add(l, new Integer(5));  
						     			        tempArray[i].playerUI.lb.addSettlerCount(s2.getX()-1, s2.getY());
						                    leaveClean(s2.getX(), s2.getY(), s2);
				                          tempArray[i].unitSettlerLabels.add(l);
//						                    turnClean(s2.getX()-1, s2.getY());
						                    tempArray[i].playerUI.lb.subSettlerCount(s2.getX(), s2.getY()); 
									           if(((tempArray[i].playerUI.lb.getSettlerCount(s2.getX(), s2.getY()) + tempArray[i].playerUI.lb.getArmyCount(s2.getX(), s2.getY())) == 0) && !tempArray[i].playerUI.lb.getHasBuild(s2.getX(), s2.getY()))      
												    tempArray[i].playerUI.lb.setOwner(s2.getX(), s2.getY(), null);  
				                       }
				                    }
					                 s2.setPosition(s2.getX()-1, s2.getY());
				                    turnClean(s2.getX()+1, s2.getY());
				                    turnClean(s.getX(), s.getY());
										  s2.takeMove();  
										}
								    }  
									 else
									 {
									   Army s2 = (Army)temp;
										if(s2.getGroup() == s.getGroup())
										{
										  s2.setBounds(name.playerUI.lb.getSettlerCount(s2.getX()-1, s2.getY()), name.playerUI.lb.getArmyCount(s2.getX()-1, s2.getY()));//spaceTotalUnits);
										  for(int i = 0; i < 2; i++)//number of players
									     {
											  if(tempArray[i] == name)
											  {
												  tempArray[i].playerUI.lb.getHolder(s2.getX(), s2.getY()).add(s2.getLabel(), new Integer(5));    
						                    tempArray[i].playerUI.lb.getHolder(s2.getX()-1, s2.getY()).add(s2.getLabel(), new Integer(5));  
						                    tempArray[i].playerUI.lb.subArmyCount(s2.getX(), s2.getY()); 
									           if(((tempArray[i].playerUI.lb.getSettlerCount(s2.getX(), s2.getY()) + tempArray[i].playerUI.lb.getArmyCount(s2.getX(), s2.getY())) == 0) && !tempArray[i].playerUI.lb.getHasBuild(s2.getX(), s2.getY()))      
												    tempArray[i].playerUI.lb.setOwner(s2.getX(), s2.getY(), null);  
						     			        tempArray[i].playerUI.lb.addArmyCount(s2.getX()-1, s2.getY());
//						                    turnClean(s2.getX()-1, s2.getY());
											  }
											  else
											  {
												  JLabel l = new JLabel(s2.getIcon());
												  l.setBounds(s2.getCurrentbounds());
												  l.setName(s2.getLabelName());
						                    tempArray[i].playerUI.lb.getHolder(s2.getX()-1, s2.getY()).add(l, new Integer(5));  
						     			        tempArray[i].playerUI.lb.addArmyCount(s2.getX()-1, s2.getY());
						                    leaveClean(s2.getX(), s2.getY(), s2);
				                          tempArray[i].unitArmyLabels.add(l);
//						                    turnClean(s2.getX()-1, s2.getY());
						                    tempArray[i].playerUI.lb.subArmyCount(s2.getX(), s2.getY()); 
									           if(((tempArray[i].playerUI.lb.getSettlerCount(s2.getX(), s2.getY()) + tempArray[i].playerUI.lb.getArmyCount(s2.getX(), s2.getY())) == 0) && !tempArray[i].playerUI.lb.getHasBuild(s2.getX(), s2.getY()))      
												    tempArray[i].playerUI.lb.setOwner(s2.getX(), s2.getY(), null);  
				                       }
				                    }
							           s2.setPosition(s2.getX()-1, s2.getY());
				                    turnClean(s2.getX()+1, s2.getY());
				                    turnClean(s.getX(), s.getY());
										  s2.takeMove();  
										}
									 }
						        } 
						        break;  
						    case 3:
								  for(int k = 0; k < grp3.size(); k++)
								  {
								    Object temp = grp3.get(k);
									 if(temp.getClass().getName().equals("Settler"))
								    {
									   Settler s2 = (Settler)temp;
										if(s2.getGroup() == s.getGroup() && s2 != s)
										{
										  s2.setBounds(spaceTotalUnits);
										  for(int i = 0; i < 2; i++)//number of players
									     {
											  if(tempArray[i] == name)
											  {
												  tempArray[i].playerUI.lb.getHolder(s2.getX(), s2.getY()).add(s2.getLabel(), new Integer(5));    
						                    tempArray[i].playerUI.lb.getHolder(s2.getX()-1, s2.getY()).add(s2.getLabel(), new Integer(5));  
						                    tempArray[i].playerUI.lb.subSettlerCount(s2.getX(), s2.getY()); 
									           if(((tempArray[i].playerUI.lb.getSettlerCount(s2.getX(), s2.getY()) + tempArray[i].playerUI.lb.getArmyCount(s2.getX(), s2.getY())) == 0) && !tempArray[i].playerUI.lb.getHasBuild(s2.getX(), s2.getY()))      
												    tempArray[i].playerUI.lb.setOwner(s2.getX(), s2.getY(), null);  
						     			        tempArray[i].playerUI.lb.addSettlerCount(s2.getX()-1, s2.getY());
//						                    turnClean(s2.getX()-1, s2.getY());
											  }
											  else
											  {
												  JLabel l = new JLabel(s2.getIcon());
												  l.setBounds(s2.getCurrentbounds());
												  l.setName(s2.getLabelName());
						                    tempArray[i].playerUI.lb.getHolder(s2.getX()-1, s2.getY()).add(l, new Integer(5));  
						     			        tempArray[i].playerUI.lb.addSettlerCount(s2.getX()-1, s2.getY());
						                    leaveClean(s2.getX(), s2.getY(), s2);
				                          tempArray[i].unitSettlerLabels.add(l);
//						                    turnClean(s2.getX()-1, s2.getY());
						                    tempArray[i].playerUI.lb.subSettlerCount(s2.getX(), s2.getY()); 
									           if(((tempArray[i].playerUI.lb.getSettlerCount(s2.getX(), s2.getY()) + tempArray[i].playerUI.lb.getArmyCount(s2.getX(), s2.getY())) == 0) && !tempArray[i].playerUI.lb.getHasBuild(s2.getX(), s2.getY()))      
												    tempArray[i].playerUI.lb.setOwner(s2.getX(), s2.getY(), null);  
				                       }
				                    }
					                 s2.setPosition(s2.getX()-1, s2.getY());
				                    turnClean(s2.getX()+1, s2.getY());
				                    turnClean(s.getX(), s.getY());
										  s2.takeMove();  
										}
									 }
								    else
									 {
									   Army s2 = (Army)temp;
										if(s2.getGroup() == s.getGroup())
										{
										  s2.setBounds(name.playerUI.lb.getSettlerCount(s2.getX()-1, s2.getY()), name.playerUI.lb.getArmyCount(s2.getX()-1, s2.getY()));//spaceTotalUnits);
										  for(int i = 0; i < 2; i++)//number of players
									     {
											  if(tempArray[i] == name)
											  {
												  tempArray[i].playerUI.lb.getHolder(s2.getX(), s2.getY()).add(s2.getLabel(), new Integer(5));    
						                    tempArray[i].playerUI.lb.getHolder(s2.getX()-1, s2.getY()).add(s2.getLabel(), new Integer(5));  
						                    tempArray[i].playerUI.lb.subArmyCount(s2.getX(), s2.getY()); 
									           if(((tempArray[i].playerUI.lb.getSettlerCount(s2.getX(), s2.getY()) + tempArray[i].playerUI.lb.getArmyCount(s2.getX(), s2.getY())) == 0) && !tempArray[i].playerUI.lb.getHasBuild(s2.getX(), s2.getY()))      
												    tempArray[i].playerUI.lb.setOwner(s2.getX(), s2.getY(), null);  
						     			        tempArray[i].playerUI.lb.addArmyCount(s2.getX()-1, s2.getY());
//						                    turnClean(s2.getX()-1, s2.getY());
											  }
											  else
											  {
												  JLabel l = new JLabel(s2.getIcon());
												  l.setBounds(s2.getCurrentbounds());
												  l.setName(s2.getLabelName());
						                    tempArray[i].playerUI.lb.getHolder(s2.getX()-1, s2.getY()).add(l, new Integer(5));  
						     			        tempArray[i].playerUI.lb.addArmyCount(s2.getX()-1, s2.getY());
						                    leaveClean(s2.getX(), s2.getY(), s2);
				                          tempArray[i].unitArmyLabels.add(l);
//						                    turnClean(s2.getX()-1, s2.getY());
						                    tempArray[i].playerUI.lb.subArmyCount(s2.getX(), s2.getY()); 
									           if(((tempArray[i].playerUI.lb.getSettlerCount(s2.getX(), s2.getY()) + tempArray[i].playerUI.lb.getArmyCount(s2.getX(), s2.getY())) == 0) && !tempArray[i].playerUI.lb.getHasBuild(s2.getX(), s2.getY()))      
												    tempArray[i].playerUI.lb.setOwner(s2.getX(), s2.getY(), null);  
				                       }
				                    }
							           s2.setPosition(s2.getX()-1, s2.getY());
				                    turnClean(s2.getX()+1, s2.getY());
				                    turnClean(s.getX(), s.getY());
										  s2.takeMove();  
										}
									 }
						        } 
						        break;  
						    case 4:
								  for(int k = 0; k < grp4.size(); k++)
								  {
								    Object temp = grp4.get(k);
									 if(temp.getClass().getName().equals("Settler"))
								    {
									   Settler s2 = (Settler)temp;
										if(s2.getGroup() == s.getGroup() && s2 != s)
										{
										  s2.setBounds(spaceTotalUnits);
										  for(int i = 0; i < 2; i++)//number of players
									     {
											  if(tempArray[i] == name)
											  {
												  tempArray[i].playerUI.lb.getHolder(s2.getX(), s2.getY()).add(s2.getLabel(), new Integer(5));    
						                    tempArray[i].playerUI.lb.getHolder(s2.getX()-1, s2.getY()).add(s2.getLabel(), new Integer(5));  
						                    tempArray[i].playerUI.lb.subSettlerCount(s2.getX(), s2.getY()); 
									           if(((tempArray[i].playerUI.lb.getSettlerCount(s2.getX(), s2.getY()) + tempArray[i].playerUI.lb.getArmyCount(s2.getX(), s2.getY())) == 0) && !tempArray[i].playerUI.lb.getHasBuild(s2.getX(), s2.getY()))      
												    tempArray[i].playerUI.lb.setOwner(s2.getX(), s2.getY(), null);  
						     			        tempArray[i].playerUI.lb.addSettlerCount(s2.getX()-1, s2.getY());
//						                    turnClean(s2.getX()-1, s2.getY());
											  }
											  else
											  {
												  JLabel l = new JLabel(s2.getIcon());
												  l.setBounds(s2.getCurrentbounds());
												  l.setName(s2.getLabelName());
						                    tempArray[i].playerUI.lb.getHolder(s2.getX()-1, s2.getY()).add(l, new Integer(5));  
						     			        tempArray[i].playerUI.lb.addSettlerCount(s2.getX()-1, s2.getY());
						                    leaveClean(s2.getX(), s2.getY(), s2);
				                          tempArray[i].unitSettlerLabels.add(l);
//						                    turnClean(s2.getX()-1, s2.getY());
						                    tempArray[i].playerUI.lb.subSettlerCount(s2.getX(), s2.getY()); 
									           if(((tempArray[i].playerUI.lb.getSettlerCount(s2.getX(), s2.getY()) + tempArray[i].playerUI.lb.getArmyCount(s2.getX(), s2.getY())) == 0) && !tempArray[i].playerUI.lb.getHasBuild(s2.getX(), s2.getY()))      
												    tempArray[i].playerUI.lb.setOwner(s2.getX(), s2.getY(), null);  
				                       }
				                    }
					                 s2.setPosition(s2.getX()-1, s2.getY());
				                    turnClean(s2.getX()+1, s2.getY());
				                    turnClean(s.getX(), s.getY());
										  s2.takeMove();  
										}
								    }  
									 else
									 {
									   Army s2 = (Army)temp;
										if(s2.getGroup() == s.getGroup())
										{
										  s2.setBounds(name.playerUI.lb.getSettlerCount(s2.getX()-1, s2.getY()), name.playerUI.lb.getArmyCount(s2.getX()-1, s2.getY()));//spaceTotalUnits);
										  for(int i = 0; i < 2; i++)//number of players
									     {
											  if(tempArray[i] == name)
											  {
												  tempArray[i].playerUI.lb.getHolder(s2.getX(), s2.getY()).add(s2.getLabel(), new Integer(5));    
						                    tempArray[i].playerUI.lb.getHolder(s2.getX()-1, s2.getY()).add(s2.getLabel(), new Integer(5));  
						                    tempArray[i].playerUI.lb.subArmyCount(s2.getX(), s2.getY()); 
									           if(((tempArray[i].playerUI.lb.getSettlerCount(s2.getX(), s2.getY()) + tempArray[i].playerUI.lb.getArmyCount(s2.getX(), s2.getY())) == 0) && !tempArray[i].playerUI.lb.getHasBuild(s2.getX(), s2.getY()))      
												    tempArray[i].playerUI.lb.setOwner(s2.getX(), s2.getY(), null);  
						     			        tempArray[i].playerUI.lb.addArmyCount(s2.getX()-1, s2.getY());
//						                    turnClean(s2.getX()-1, s2.getY());
											  }
											  else
											  {
												  JLabel l = new JLabel(s2.getIcon());
												  l.setBounds(s2.getCurrentbounds());
												  l.setName(s2.getLabelName());
						                    tempArray[i].playerUI.lb.getHolder(s2.getX()-1, s2.getY()).add(l, new Integer(5));  
						     			        tempArray[i].playerUI.lb.addArmyCount(s2.getX()-1, s2.getY());
						                    leaveClean(s2.getX(), s2.getY(), s2);
				                          tempArray[i].unitArmyLabels.add(l);
//						                    turnClean(s2.getX()-1, s2.getY());
						                    tempArray[i].playerUI.lb.subArmyCount(s2.getX(), s2.getY()); 
									           if(((tempArray[i].playerUI.lb.getSettlerCount(s2.getX(), s2.getY()) + tempArray[i].playerUI.lb.getArmyCount(s2.getX(), s2.getY())) == 0) && !tempArray[i].playerUI.lb.getHasBuild(s2.getX(), s2.getY()))      
												    tempArray[i].playerUI.lb.setOwner(s2.getX(), s2.getY(), null);  
				                       }
				                    }
							           s2.setPosition(s2.getX()-1, s2.getY());
				                    turnClean(s2.getX()+1, s2.getY());
				                    turnClean(s.getX(), s.getY());
										  s2.takeMove();  
										}
									 }
						        } 
						        break;  
							  } 
						  }

						  s.setPosition(s.getX()-1, s.getY());
                    turnClean(s.getX()+1, s.getY());
                    turnClean(s.getX(), s.getY());
	   				  s.takeMove();  

                    if(name.playerUI.lb.hasHut(s.getX(), s.getY()) > 0)              
						  {
							 String type = name.playerUI.lb.getHutResource(s.getX(), s.getY());
							 name.addResource(type, "hut"); 
						    name.playerUI.tellHutResource(type);   
							 name.playerBoard.setHut(1);
							 for(int k = 0; k < 2; k++)
							 {
							   tempArray[k].playerUI.lb.removeHut(s.getX(), s.getY());
						    }
						    if(name.getNation().equals("China"))
							   name.addCulture(3);  
						    resetSettler(s);
						  }
						  if(s.getMoves() == 0)
			           {
  	 	                resetSettler(s);
					     }
					   }
					 }
				  }				
				}//check Settler move
			   else if((a != null) && (a.getX()-1 >= 0))
				{
              Player[] tempArray = name.getPlayerUI().getPlayers();
	           spaceTotalUnits = name.playerUI.lb.getSettlerCount(a.getX()-1, a.getY()) + name.playerUI.lb.getArmyCount(a.getX()-1, a.getY());
				  if(((name.playerUI.lb.getOwner(a.getX()-1, a.getY()) != null) && (name != name.playerUI.lb.getOwner(a.getX()-1, a.getY()))) || (name.playerUI.lb.hasVillage(a.getX()-1, a.getY()) > 0))
				  {
//				    System.out.println("COMBAT");//do combat
                conductCombat = true;
				  }
              if((name.playerUI.lb.isCitySpace(a.getX()-1, a.getY())) && (name != name.playerUI.lb.getOwner(a.getX()-1, a.getY())) && (name.getCurrentGov() == 2))
				  {
					   JOptionPane illegal = new JOptionPane();
				      GameImageIcon im = getImage("data/"+name.getNation()+"Icon.png");  
		            illegal.showMessageDialog(name.playerUI.map, "YOU CANNOT ATTACK ENEMY CITY WHEN\n YOU ARE UNDER DEMOCRACY!", "ILLEGAL MOVE",
				  					 			        JOptionPane.INFORMATION_MESSAGE, im);	 	 
				    conductCombat = false;
				    dir = -1;
				  }
				  else if(!conductCombat && ((spaceTotalUnits >= name.getStacklimit()) || (spaceTotalUnits + name.getGrouptotal(a.getGroup()) > name.getStacklimit())  || (name.playerUI.lb.isCitySpace(a.getX()-1, a.getY()) && a.getMoves() < 2)))
				    dir = -1;
				  else
				  {
                if(name.playerUI.lb.getTerrain(a.getX(), a.getY()).equals("Water") && !name.getWaterLand() && (a.getMoves() <= 1) && (name.playerUI.lb.getTerrain(a.getX()-1, a.getY()) == null || name.playerUI.lb.checkExplored(a.getX()-1, a.getY())))
					 {
					   JOptionPane illegal = new JOptionPane();
				      GameImageIcon im = getImage("data/"+name.getNation()+"Icon.png");  
		            illegal.showMessageDialog(name.playerUI.map, "YOU CANNOT LAND IN WATER WITH CURRENT TECHNOLOGY!\nMOVE UNIT TO LAND", "CANNOT END MOVE HERE",
				  					 			        JOptionPane.INFORMATION_MESSAGE, im);	 	 
					 }            
				    else if(name.playerUI.lb.checkExplored(a.getX()-1, a.getY()))
					 {
                  if(name.playerUI.lb.getHolder(a.getX(), a.getY()).getParent().getName().equals("panel4"))
					   {
						   for(int k = 0; k < 2; k++)//number of players
					      {
						     tempArray[k].playerUI.lb.populateTile(name.getPlayerUI().boardPosition[0], 0, 0, tempArray[k]);
							  tempArray[k].playerUI.map.remove(tempArray[k].playerUI.myfog1);
							  tempArray[k].playerUI.map.add(tempArray[k].playerUI.mypanel0, 0);
					        tempArray[k].playerUI.map.validate();
					        tempArray[k].playerUI.map.repaint();
							}
  						}    
		            else if(name.playerUI.lb.getHolder(a.getX(), a.getY()).getParent().getName().equals("panel5"))
					   {
						   for(int k = 0; k < 2; k++)//number of players
					      {
						     tempArray[k].playerUI.lb.populateTile(name.getPlayerUI().boardPosition[1], 1, 0, tempArray[k]);
							  tempArray[k].playerUI.map.remove(tempArray[k].playerUI.myfog2);
							  tempArray[k].playerUI.map.add(tempArray[k].playerUI.mypanel1, 2);
					        tempArray[k].playerUI.map.validate();
					        tempArray[k].playerUI.map.repaint();
							}
  						}    
		            else if(name.playerUI.lb.getHolder(a.getX(), a.getY()).getParent().getName().equals("panel6"))
					   {
						   for(int k = 0; k < 2; k++)//number of players
					      {
						     tempArray[k].playerUI.lb.populateTile(name.getPlayerUI().boardPosition[2], 2, 0, tempArray[k]);
							  tempArray[k].playerUI.map.remove(tempArray[k].playerUI.myfog4);
							  tempArray[k].playerUI.map.add(tempArray[k].playerUI.mypanel2, 4);
					        tempArray[k].playerUI.map.validate();
					        tempArray[k].playerUI.map.repaint();
							}
						}
				 	   dir = 0;   
	   				a.takeMove();  

						if(a.getGroup() > 0)
						{
						  switch(a.getGroup())
						  {
						    case 1:
								  for(int k = 0; k < grp1.size(); k++)
								  {
								    Object temp = grp1.get(k);
									 if(temp.getClass().getName().equals("Settler"))
								    {
									   Settler s2 = (Settler)temp;
										if(s2.getGroup() == a.getGroup())
										{
						   			  s2.takeMove();  
										}
									 }
								    else
									 {
									   Army s2 = (Army)temp;
										if(s2.getGroup() == a.getGroup() && s2 != a)
										{
										  s2.takeMove();
										}
									 }
						        } 
						        break;  
						    case 2:
								  for(int k = 0; k < grp2.size(); k++)
								  {
								    Object temp = grp2.get(k);
									 if(temp.getClass().getName().equals("Settler"))
								    {
									   Settler s2 = (Settler)temp;
										if(s2.getGroup() == a.getGroup())
										{
						   			  s2.takeMove();  
										}
									 }
								    else
									 {
									   Army s2 = (Army)temp;
										if(s2.getGroup() == a.getGroup() && s2 != a)
										{
										  s2.takeMove();
										}
									 }
						        } 
						        break;  
						    case 3:
								  for(int k = 0; k < grp3.size(); k++)
								  {
								    Object temp = grp3.get(k);
									 if(temp.getClass().getName().equals("Settler"))
								    {
									   Settler s2 = (Settler)temp;
										if(s2.getGroup() == a.getGroup())
										{
						   			  s2.takeMove();  
										}
									 }
								    else
									 {
									   Army s2 = (Army)temp;
										if(s2.getGroup() == a.getGroup() && s2 != a)
										{
										  s2.takeMove();
										}
									 }
						        } 
						        break;  
						    case 4:
								  for(int k = 0; k < grp4.size(); k++)
								  {
								    Object temp = grp4.get(k);
									 if(temp.getClass().getName().equals("Settler"))
								    {
									   Settler s2 = (Settler)temp;
										if(s2.getGroup() == a.getGroup())
										{
						   			  s2.takeMove();  
										}
									 }
								    else
									 {
									   Army s2 = (Army)temp;
										if(s2.getGroup() == a.getGroup() && s2 != a)
										{
										  s2.takeMove();
										}
									 }
						        } 
						        break;  
							 }
						}
						if(a.getMoves() == 0)
		            {
                    resetArmy(a);
				      }
					 }
				    else
					 {
	               if(name.playerUI.lb.getTerrain(a.getX()-1, a.getY()).equals("Water") && !name.getWaterCross())    
						{
						  dir = -1;
						}
	               else if(name.playerUI.lb.getTerrain(a.getX()-1, a.getY()).equals("Water") && !name.getWaterLand() && (a.getMoves() < 2)) 
						{
						  dir = -1;
						}          
						else
						{
						  boolean SettlerOnly = false; 
						  if(name.playerUI.lb.getArmyCount(a.getX()-1, a.getY()) == 0 && conductCombat) 
						  {
							 SettlerOnly = true;
						  }
						  a.setBounds(name.playerUI.lb.getSettlerCount(a.getX()-1, a.getY()), name.playerUI.lb.getArmyCount(a.getX()-1, a.getY()));//spaceTotalUnits);
						  for(int k = 0; k < 2; k++)//number of players
					     {
							  if(tempArray[k] == name)
							  {
								  tempArray[k].playerUI.lb.getHolder(a.getX(), a.getY()).add(a.getLabel(), new Integer(5));    
		                    tempArray[k].playerUI.lb.getHolder(a.getX()-1, a.getY()).add(a.getLabel(), new Integer(5));  
		                    tempArray[k].playerUI.lb.subArmyCount(a.getX(), a.getY()); 
					           if(((tempArray[k].playerUI.lb.getSettlerCount(a.getX(), a.getY()) + tempArray[k].playerUI.lb.getArmyCount(a.getX(), a.getY())) == 0) && !tempArray[k].playerUI.lb.getHasBuild(a.getX(), a.getY()))      
								    tempArray[k].playerUI.lb.setOwner(a.getX(), a.getY(), null);  
		     			        tempArray[k].playerUI.lb.addArmyCount(a.getX()-1, a.getY());
//		                    turnClean(a.getX()-1, a.getY());
							  }
							  else
							  {
								  JLabel l = new JLabel(a.getIcon());
								  l.setBounds(a.getCurrentbounds());
								  l.setName(a.getLabelName());
		                    tempArray[k].playerUI.lb.getHolder(a.getX()-1, a.getY()).add(l, new Integer(5));  
		     			        tempArray[k].playerUI.lb.addArmyCount(a.getX()-1, a.getY());
		                    leaveClean(a.getX(), a.getY(), a);
                          tempArray[k].unitArmyLabels.add(l);
//		                    turnClean(a.getX()-1, a.getY());
		                    tempArray[k].playerUI.lb.subArmyCount(a.getX(), a.getY()); 
					           if(((tempArray[k].playerUI.lb.getSettlerCount(a.getX(), a.getY()) + tempArray[k].playerUI.lb.getArmyCount(a.getX(), a.getY())) == 0) && !tempArray[k].playerUI.lb.getHasBuild(a.getX(), a.getY()))      
								    tempArray[k].playerUI.lb.setOwner(a.getX(), a.getY(), null);  
                       }
                    }

						  if(a.getGroup() > 0)
						  {
						    moveArmyGroup_0(a);
                    }

			           a.setPosition(a.getX()-1, a.getY());
                    turnClean(a.getX()+1, a.getY());
                    turnClean(a.getX(), a.getY());
	   				  a.takeMove();  
						  
                    if(name.playerUI.lb.hasHut(a.getX(), a.getY()) > 0)              
						  {
							 String type = name.playerUI.lb.getHutResource(a.getX(), a.getY());
							 name.addResource(type, "hut"); 
						    name.playerUI.tellHutResource(type);   
							 name.playerBoard.setHut(1);
							 for(int k = 0; k < 2; k++)
							 {
							   tempArray[k].playerUI.lb.removeHut(a.getX(), a.getY());
						    }
						    if(name.getNation().equals("China"))
							   name.addCulture(3);  
						    resetArmy(a);
						  }
 						  if(a.getMoves() == 0 && !conductCombat)
		              {
							 resetArmy(a);
				        }
                    if(conductCombat)
						  {
                      combatPause();  
					       if(name.playerUI.lb.hasVillage(a.getX(), a.getY()) > 0)
							 {
						      combatUnit = a;
							   combat = new Combat(new Player("Village"), name, this);
						      a.combatMoves();
							 }    
                      else if(SettlerOnly)
							 {
						      Player loser = name.playerUI.lb.getOwner(a.getX(), a.getY());
								Vector sl = loser.getSettlerVector();
								int k;
								for(k = 0; k < sl.size(); k++)
								{
						        Settler temp = (Settler) sl.get(k); 
								  if((temp.getX() == a.getX()) && (temp.getY() == a.getY()))
						        {
								    JLayeredPane lp = name.playerUI.lb.getHolder(a.getX(), a.getY());    
								    loser.removeSettler(temp);
								    name.playerUI.lb.subSettlerCount(a.getX(), a.getY());  
									 name.playerUI.lb.getHolder(a.getX(), a.getY()).remove(temp.getLabel());
									 name.playerUI.lb.getHolder(a.getX(), a.getY()).validate();
									 name.playerUI.lb.getHolder(a.getX(), a.getY()).repaint();
						  	 	  }
								} 
							   combat = new Combat(name.playerUI.lb.getOwner(a.getX(), a.getY()), name, true);
// 			     		      for(int i = 0; i < movePeices.size(); i++)
//						  		  nm[i].addActionListener(this);  
						      done.addActionListener(this);  
						      lt.addActionListener(this);  
							   rt.addActionListener(this);  
							   up.addActionListener(this);  
							   dw.addActionListener(this);  
							   resetArmy(a);
							 } 
							 else
							 {
						      combatUnit = a;
							   combat = new Combat(name.playerUI.lb.getOwner(a.getX(), a.getY()), name, this);
						      a.combatMoves();
						    }
						  } 
					   }
					 }
				  }				
				}//check if Army  
			 }
		    else if(direction == 1)  
		    {
			   if((s != null) && (s.getY()-1 >= 0))
			   {
	           spaceTotalUnits = name.playerUI.lb.getSettlerCount(s.getX(), s.getY()-1) + name.playerUI.lb.getArmyCount(s.getX(), s.getY()-1);
				  if((name.playerUI.lb.getOwner(s.getX(), s.getY()-1) != null) && (name != name.playerUI.lb.getOwner(s.getX(), s.getY()-1)))
				  {
                conductCombat = true;
				  }
				  if(conductCombat || ((spaceTotalUnits >= name.getStacklimit()) || (spaceTotalUnits + name.getGrouptotal(s.getGroup()) > name.getStacklimit())  || (name.playerUI.lb.isCitySpace(s.getX(), s.getY()-1) && s.getMoves() < 2)))
				    dir = -1;
				  else
				  {
                if(name.playerUI.lb.getTerrain(s.getX(), s.getY()).equals("Water") && !name.getWaterLand() && (s.getMoves() <= 1) && (name.playerUI.lb.getTerrain(s.getX(), s.getY()-1) == null || name.playerUI.lb.checkExplored(s.getX(), s.getY()-1)))
					 {
					   JOptionPane illegal = new JOptionPane();
				      GameImageIcon im = getImage("data/"+name.getNation()+"Icon.png");  
		            illegal.showMessageDialog(name.playerUI.map, "YOU CANNOT LAND IN WATER WITH CURRENT TECHNOLOGY!\nMOVE UNIT TO LAND", "CANNOT END MOVE HERE",
				  					 			        JOptionPane.INFORMATION_MESSAGE, im);	 	 
					 }            
				    else if(name.playerUI.lb.checkExplored(s.getX(), s.getY()-1))
					 {
						Player[] tempArray = name.getPlayerUI().getPlayers();
						if(name.playerUI.lb.getHolder(s.getX(), s.getY()).getParent().getName().equals("panel3"))
					   {
						   for(int k = 0; k < 2; k++)//number of players
					      {
						     tempArray[k].playerUI.lb.populateTile(name.getPlayerUI().boardPosition[2], 2, 1, tempArray[k]);
							  tempArray[k].playerUI.map.remove(tempArray[k].playerUI.myfog4);
							  tempArray[k].playerUI.map.add(tempArray[k].playerUI.mypanel2, 4);
					        tempArray[k].playerUI.map.validate();
					        tempArray[k].playerUI.map.repaint();
							}
  						}    
		            else if(name.playerUI.lb.getHolder(s.getX(), s.getY()).getParent().getName().equals("panel2"))
					   {
						   for(int k = 0; k < 2; k++)//number of players
					      {
						     tempArray[k].playerUI.lb.populateTile(name.getPlayerUI().boardPosition[1], 1, 1, tempArray[k]);
							  tempArray[k].playerUI.map.remove(tempArray[k].playerUI.myfog2);
							  tempArray[k].playerUI.map.add(tempArray[k].playerUI.mypanel1, 2);
					        tempArray[k].playerUI.map.validate();
					        tempArray[k].playerUI.map.repaint();
							}
  						}    
		            else if(name.playerUI.lb.getHolder(s.getX(), s.getY()).getParent().getName().equals("panel1"))
					   {
						   for(int k = 0; k < 2; k++)//number of players
					      {
						     tempArray[k].playerUI.lb.populateTile(name.getPlayerUI().boardPosition[0], 0, 1, tempArray[k]);
							  tempArray[k].playerUI.map.remove(tempArray[k].playerUI.myfog1);
							  tempArray[k].playerUI.map.add(tempArray[k].playerUI.mypanel0, 0);
					        tempArray[k].playerUI.map.validate();
					        tempArray[k].playerUI.map.repaint();
							}
						}
		            else if(name.playerUI.lb.getHolder(s.getX(), s.getY()).getParent().getName().equals("panel7"))
					   {
						   for(int k = 0; k < 2; k++)//number of players
					      {
						     tempArray[k].playerUI.lb.populateTile(name.getPlayerUI().boardPosition[6], 6, 1, tempArray[k]);
							  tempArray[k].playerUI.map.remove(tempArray[k].playerUI.myfog5);
							  tempArray[k].playerUI.map.add(tempArray[k].playerUI.mypanel6, 5);
					        tempArray[k].playerUI.map.validate();
					        tempArray[k].playerUI.map.repaint();
							}
						}
		            else if(name.playerUI.lb.getHolder(s.getX(), s.getY()).getParent().getName().equals("panel6"))
					   {
						   for(int k = 0; k < 2; k++)//number of players
					      {
						     tempArray[k].playerUI.lb.populateTile(name.getPlayerUI().boardPosition[5], 5, 1, tempArray[k]);
							  tempArray[k].playerUI.map.remove(tempArray[k].playerUI.myfog3);
							  tempArray[k].playerUI.map.add(tempArray[k].playerUI.mypanel5, 3);
					        tempArray[k].playerUI.map.validate();
					        tempArray[k].playerUI.map.repaint();
							}
						}
				 	   dir = 0;   
	   				s.takeMove();  
					   if(s.getGroup() > 0)
						{
						  switch(s.getGroup())
						  {
						    case 1:
								  for(int k = 0; k < grp1.size(); k++)
								  {
								    Object temp = grp1.get(k);
									 if(temp.getClass().getName().equals("Settler"))
								    {
									   Settler s2 = (Settler)temp;
										if(s2.getGroup() == s.getGroup() && s2 != s)
										{
						   			  s2.takeMove();  
										}
									 }
								    else
									 {
									   Army s2 = (Army)temp;
										if(s2.getGroup() == s.getGroup())
										{
										  s2.takeMove();
										}
									 }
						        } 
						        break;  
						    case 2:
								  for(int k = 0; k < grp2.size(); k++)
								  {
								    Object temp = grp2.get(k);
									 if(temp.getClass().getName().equals("Settler"))
								    {
									   Settler s2 = (Settler)temp;
										if(s2.getGroup() == s.getGroup() && s2 != s)
										{
						   			  s2.takeMove();  
										}
									 }
								    else
									 {
									   Army s2 = (Army)temp;
										if(s2.getGroup() == s.getGroup())
										{
										  s2.takeMove();
										}
									 }
						        } 
						        break;  
						    case 3:
								  for(int k = 0; k < grp3.size(); k++)
								  {
								    Object temp = grp3.get(k);
									 if(temp.getClass().getName().equals("Settler"))
								    {
									   Settler s2 = (Settler)temp;
										if(s2.getGroup() == s.getGroup() && s2 != s)
										{
						   			  s2.takeMove();  
										}
									 }
								    else
									 {
									   Army s2 = (Army)temp;
										if(s2.getGroup() == s.getGroup())
										{
										  s2.takeMove();
										}
									 }
						        } 
						        break;  
						    case 4:
								  for(int k = 0; k < grp4.size(); k++)
								  {
								    Object temp = grp4.get(k);
									 if(temp.getClass().getName().equals("Settler"))
								    {
									   Settler s2 = (Settler)temp;
										if(s2.getGroup() == s.getGroup() && s2 != s)
										{
						   			  s2.takeMove();  
										}
									 }
								    else
									 {
									   Army s2 = (Army)temp;
										if(s2.getGroup() == s.getGroup())
										{
										  s2.takeMove();
										}
									 }
						        } 
						        break;  
							 }
						  }
						
						if(s.getMoves() == 0)
		            {
                    resetSettler(s);
				      }
					 }
				    else
					 {
	               if((name.playerUI.lb.hasHut(s.getX(), s.getY()-1) > 0) && name.getCurrentGov() != 1)
						{
						  dir = -1;
						}           
						else if(name.playerUI.lb.hasVillage(s.getX(), s.getY()-1) > 0)
						{
						  dir = -1;
						}
	               else if(name.playerUI.lb.getTerrain(s.getX(), s.getY()-1).equals("Water") && !name.getWaterCross())    
						{
						  dir = -1;
						}
	               else if(name.playerUI.lb.getTerrain(s.getX(), s.getY()-1).equals("Water") && !name.getWaterLand() && (s.getMoves() < 2)) 
						{
						  dir = -1;
						}          
                  else
						{
						  Player[] tempArray = name.getPlayerUI().getPlayers();
						  s.setBounds(spaceTotalUnits);
						  for(int k = 0; k < 2; k++)//number of players
					     {
							  if(tempArray[k] == name)
							  {
								  tempArray[k].playerUI.lb.getHolder(s.getX(), s.getY()).add(s.getLabel(), new Integer(5));    
		                    tempArray[k].playerUI.lb.getHolder(s.getX(), s.getY()-1).add(s.getLabel(), new Integer(5));  
		                    tempArray[k].playerUI.lb.subSettlerCount(s.getX(), s.getY()); 
					           if(((tempArray[k].playerUI.lb.getSettlerCount(s.getX(), s.getY()) + tempArray[k].playerUI.lb.getArmyCount(s.getX(), s.getY())) == 0) && !tempArray[k].playerUI.lb.getHasBuild(s.getX(), s.getY()))      
								    tempArray[k].playerUI.lb.setOwner(s.getX(), s.getY(), null);  
		     			        tempArray[k].playerUI.lb.addSettlerCount(s.getX(), s.getY()-1);
//		                    turnClean(s.getX(), s.getY()-1);
							  }
							  else
							  {
								  JLabel l = new JLabel(s.getIcon());
								  l.setBounds(s.getCurrentbounds());
								  l.setName(s.getLabelName());
		                    tempArray[k].playerUI.lb.getHolder(s.getX(), s.getY()-1).add(l, new Integer(5));  
		     			        tempArray[k].playerUI.lb.addSettlerCount(s.getX(), s.getY()-1);
		                    leaveClean(s.getX(), s.getY(), s);
                          tempArray[k].unitSettlerLabels.add(l);
//		                    turnClean(s.getX(), s.getY()-1);
		                    tempArray[k].playerUI.lb.subSettlerCount(s.getX(), s.getY()); 
					           if(((tempArray[k].playerUI.lb.getSettlerCount(s.getX(), s.getY()) + tempArray[k].playerUI.lb.getArmyCount(s.getX(), s.getY())) == 0) && !tempArray[k].playerUI.lb.getHasBuild(s.getX(), s.getY()))      
								    tempArray[k].playerUI.lb.setOwner(s.getX(), s.getY(), null);  
                       }
                    }
	   				  
						  if(s.getGroup() > 0)
						  {
						    switch(s.getGroup())
						    {
						      case 1:
								  for(int k = 0; k < grp1.size(); k++)
								  {
								    Object temp = grp1.get(k);
									 if(temp.getClass().getName().equals("Settler"))
								    {
									   Settler s2 = (Settler)temp;
										if(s2.getGroup() == s.getGroup() && s2 != s)
										{
										  s2.setBounds(spaceTotalUnits);
										  for(int i = 0; i < 2; i++)//number of players
									     {
											  if(tempArray[i] == name)
											  {
												  tempArray[i].playerUI.lb.getHolder(s2.getX(), s2.getY()).add(s2.getLabel(), new Integer(5));    
						                    tempArray[i].playerUI.lb.getHolder(s2.getX(), s2.getY()-1).add(s2.getLabel(), new Integer(5));  
						                    tempArray[i].playerUI.lb.subSettlerCount(s2.getX(), s2.getY()); 
									           if(((tempArray[i].playerUI.lb.getSettlerCount(s2.getX(), s2.getY()) + tempArray[i].playerUI.lb.getArmyCount(s2.getX(), s2.getY())) == 0) && !tempArray[i].playerUI.lb.getHasBuild(s2.getX(), s2.getY()))      
												    tempArray[i].playerUI.lb.setOwner(s2.getX(), s2.getY(), null);  
						     			        tempArray[i].playerUI.lb.addSettlerCount(s2.getX(), s2.getY()-1);
//						                    turnClean(s2.getX(), s2.getY()-1);
											  }
											  else
											  {
												  JLabel l = new JLabel(s2.getIcon());
												  l.setBounds(s2.getCurrentbounds());
												  l.setName(s2.getLabelName());
						                    tempArray[i].playerUI.lb.getHolder(s2.getX(), s2.getY()-1).add(l, new Integer(5));  
						     			        tempArray[i].playerUI.lb.addSettlerCount(s2.getX(), s2.getY()-1);
						                    leaveClean(s2.getX(), s2.getY(), s2);
				                          tempArray[i].unitSettlerLabels.add(l);
//						                    turnClean(s2.getX(), s2.getY()-1);
						                    tempArray[i].playerUI.lb.subSettlerCount(s2.getX(), s2.getY()); 
									           if(((tempArray[i].playerUI.lb.getSettlerCount(s2.getX(), s2.getY()) + tempArray[i].playerUI.lb.getArmyCount(s2.getX(), s2.getY())) == 0) && !tempArray[i].playerUI.lb.getHasBuild(s2.getX(), s2.getY()))      
												    tempArray[i].playerUI.lb.setOwner(s2.getX(), s2.getY(), null);  
				                       }
				                    }
					                 s2.setPosition(s2.getX(), s2.getY()-1);
				                    turnClean(s2.getX(), s2.getY()+1);
	                             turnClean(s.getX(), s.getY());
										  s2.takeMove();  
										}
								    }
								    else
									 {
									   Army s2 = (Army)temp;
										if(s2.getGroup() == s.getGroup())
										{
										  s2.setBounds(name.playerUI.lb.getSettlerCount(s2.getX(), s2.getY()-1), name.playerUI.lb.getArmyCount(s2.getX(), s2.getY()-1));//spaceTotalUnits);
										  for(int i = 0; i < 2; i++)//number of players
									     {
											  if(tempArray[i] == name)
											  {
												  tempArray[i].playerUI.lb.getHolder(s2.getX(), s2.getY()).add(s2.getLabel(), new Integer(5));    
						                    tempArray[i].playerUI.lb.getHolder(s2.getX(), s2.getY()-1).add(s2.getLabel(), new Integer(5));  
						                    tempArray[i].playerUI.lb.subArmyCount(s2.getX(), s2.getY()); 
									           if(((tempArray[i].playerUI.lb.getSettlerCount(s2.getX(), s2.getY()) + tempArray[i].playerUI.lb.getArmyCount(s2.getX(), s2.getY())) == 0) && !tempArray[i].playerUI.lb.getHasBuild(s2.getX(), s2.getY()))      
												    tempArray[i].playerUI.lb.setOwner(s2.getX(), s2.getY(), null);  
						     			        tempArray[i].playerUI.lb.addArmyCount(s2.getX(), s2.getY()-1);
//						                    turnClean(s2.getX(), s2.getY()-1);
											  }
											  else
											  {
												  JLabel l = new JLabel(s2.getIcon());
												  l.setBounds(s2.getCurrentbounds());
												  l.setName(s2.getLabelName());
						                    tempArray[i].playerUI.lb.getHolder(s2.getX(), s2.getY()-1).add(l, new Integer(5));  
						     			        tempArray[i].playerUI.lb.addArmyCount(s2.getX(), s2.getY()-1);
						                    leaveClean(s2.getX(), s2.getY(), s2);
				                          tempArray[i].unitArmyLabels.add(l);
//						                    turnClean(s2.getX(), s2.getY()-1);
						                    tempArray[i].playerUI.lb.subArmyCount(s2.getX(), s2.getY()); 
									           if(((tempArray[i].playerUI.lb.getSettlerCount(s2.getX(), s2.getY()) + tempArray[i].playerUI.lb.getArmyCount(s2.getX(), s2.getY())) == 0) && !tempArray[i].playerUI.lb.getHasBuild(s2.getX(), s2.getY()))      
												    tempArray[i].playerUI.lb.setOwner(s2.getX(), s2.getY(), null);  
				                       }
				                    }
							           s2.setPosition(s2.getX(), s2.getY()-1);
				                    turnClean(s2.getX(), s2.getY()+1);
				                    turnClean(s.getX(), s.getY());
										  s2.takeMove();  
										}
									 }
						        } 
						        break;  
						    case 2:
								  for(int k = 0; k < grp2.size(); k++)
								  {
								    Object temp = grp2.get(k);
									 if(temp.getClass().getName().equals("Settler"))
								    {
									   Settler s2 = (Settler)temp;
										if(s2.getGroup() == s.getGroup() && s2 != s)
										{
										  s2.setBounds(spaceTotalUnits);
										  for(int i = 0; i < 2; i++)//number of players
									     {
											  if(tempArray[i] == name)
											  {
												  tempArray[i].playerUI.lb.getHolder(s2.getX(), s2.getY()).add(s2.getLabel(), new Integer(5));    
						                    tempArray[i].playerUI.lb.getHolder(s2.getX(), s2.getY()-1).add(s2.getLabel(), new Integer(5));  
						                    tempArray[i].playerUI.lb.subSettlerCount(s2.getX(), s2.getY()); 
									           if(((tempArray[i].playerUI.lb.getSettlerCount(s2.getX(), s2.getY()) + tempArray[i].playerUI.lb.getArmyCount(s2.getX(), s2.getY())) == 0) && !tempArray[i].playerUI.lb.getHasBuild(s2.getX(), s2.getY()))      
												    tempArray[i].playerUI.lb.setOwner(s2.getX(), s2.getY(), null);  
						     			        tempArray[i].playerUI.lb.addSettlerCount(s2.getX(), s2.getY()-1);
//						                    turnClean(s2.getX(), s2.getY()-1);
											  }
											  else
											  {
												  JLabel l = new JLabel(s2.getIcon());
												  l.setBounds(s2.getCurrentbounds());
												  l.setName(s2.getLabelName());
						                    tempArray[i].playerUI.lb.getHolder(s2.getX(), s2.getY()-1).add(l, new Integer(5));  
						     			        tempArray[i].playerUI.lb.addSettlerCount(s2.getX(), s2.getY()-1);
						                    leaveClean(s2.getX(), s2.getY(), s2);
				                          tempArray[i].unitSettlerLabels.add(l);
//						                    turnClean(s2.getX(), s2.getY()-1);
						                    tempArray[i].playerUI.lb.subSettlerCount(s2.getX(), s2.getY()); 
									           if(((tempArray[i].playerUI.lb.getSettlerCount(s2.getX(), s2.getY()) + tempArray[i].playerUI.lb.getArmyCount(s2.getX(), s2.getY())) == 0) && !tempArray[i].playerUI.lb.getHasBuild(s2.getX(), s2.getY()))      
												    tempArray[i].playerUI.lb.setOwner(s2.getX(), s2.getY(), null);  
				                       }
				                    }
					                 s2.setPosition(s2.getX(), s2.getY()-1);
				                    turnClean(s2.getX(), s2.getY()+1);
	                             turnClean(s.getX(), s.getY());
										  s2.takeMove();  
										}
								    }  
									 else
									 {
									   Army s2 = (Army)temp;
										if(s2.getGroup() == s.getGroup())
										{
										  s2.setBounds(name.playerUI.lb.getSettlerCount(s2.getX(), s2.getY()-1), name.playerUI.lb.getArmyCount(s2.getX(), s2.getY()-1));//spaceTotalUnits);
										  for(int i = 0; i < 2; i++)//number of players
									     {
											  if(tempArray[i] == name)
											  {
												  tempArray[i].playerUI.lb.getHolder(s2.getX(), s2.getY()).add(s2.getLabel(), new Integer(5));    
						                    tempArray[i].playerUI.lb.getHolder(s2.getX(), s2.getY()-1).add(s2.getLabel(), new Integer(5));  
						                    tempArray[i].playerUI.lb.subArmyCount(s2.getX(), s2.getY()); 
									           if(((tempArray[i].playerUI.lb.getSettlerCount(s2.getX(), s2.getY()) + tempArray[i].playerUI.lb.getArmyCount(s2.getX(), s2.getY())) == 0) && !tempArray[i].playerUI.lb.getHasBuild(s2.getX(), s2.getY()))      
												    tempArray[i].playerUI.lb.setOwner(s2.getX(), s2.getY(), null);  
						     			        tempArray[i].playerUI.lb.addArmyCount(s2.getX(), s2.getY()-1);
//						                    turnClean(s2.getX(), s2.getY()-1);
											  }
											  else
											  {
												  JLabel l = new JLabel(s2.getIcon());
												  l.setBounds(s2.getCurrentbounds());
												  l.setName(s2.getLabelName());
						                    tempArray[i].playerUI.lb.getHolder(s2.getX(), s2.getY()-1).add(l, new Integer(5));  
						     			        tempArray[i].playerUI.lb.addArmyCount(s2.getX(), s2.getY()-1);
						                    leaveClean(s2.getX(), s2.getY(), s2);
				                          tempArray[i].unitArmyLabels.add(l);
//						                    turnClean(s2.getX(), s2.getY()-1);
						                    tempArray[i].playerUI.lb.subArmyCount(s2.getX(), s2.getY()); 
									           if(((tempArray[i].playerUI.lb.getSettlerCount(s2.getX(), s2.getY()) + tempArray[i].playerUI.lb.getArmyCount(s2.getX(), s2.getY())) == 0) && !tempArray[i].playerUI.lb.getHasBuild(s2.getX(), s2.getY()))      
												    tempArray[i].playerUI.lb.setOwner(s2.getX(), s2.getY(), null);  
				                       }
				                    }
							           s2.setPosition(s2.getX(), s2.getY()-1);
				                    turnClean(s2.getX(), s2.getY()+1);
				                    turnClean(s.getX(), s.getY());
										  s2.takeMove();  
										}
									 }
						        } 
						        break;  
						    case 3:
								  for(int k = 0; k < grp3.size(); k++)
								  {
								    Object temp = grp3.get(k);
									 if(temp.getClass().getName().equals("Settler"))
								    {
									   Settler s2 = (Settler)temp;
										if(s2.getGroup() == s.getGroup() && s2 != s)
										{
										  s2.setBounds(spaceTotalUnits);
										  for(int i = 0; i < 2; i++)//number of players
									     {
											  if(tempArray[i] == name)
											  {
												  tempArray[i].playerUI.lb.getHolder(s2.getX(), s2.getY()).add(s2.getLabel(), new Integer(5));    
						                    tempArray[i].playerUI.lb.getHolder(s2.getX(), s2.getY()-1).add(s2.getLabel(), new Integer(5));  
						                    tempArray[i].playerUI.lb.subSettlerCount(s2.getX(), s2.getY()); 
									           if(((tempArray[i].playerUI.lb.getSettlerCount(s2.getX(), s2.getY()) + tempArray[i].playerUI.lb.getArmyCount(s2.getX(), s2.getY())) == 0) && !tempArray[i].playerUI.lb.getHasBuild(s2.getX(), s2.getY()))      
												    tempArray[i].playerUI.lb.setOwner(s2.getX(), s2.getY(), null);  
						     			        tempArray[i].playerUI.lb.addSettlerCount(s2.getX(), s2.getY()-1);
//						                    turnClean(s2.getX(), s2.getY()-1);
											  }
											  else
											  {
												  JLabel l = new JLabel(s2.getIcon());
												  l.setBounds(s2.getCurrentbounds());
												  l.setName(s2.getLabelName());
						                    tempArray[i].playerUI.lb.getHolder(s2.getX(), s2.getY()-1).add(l, new Integer(5));  
						     			        tempArray[i].playerUI.lb.addSettlerCount(s2.getX(), s2.getY()-1);
						                    leaveClean(s2.getX(), s2.getY(), s2);
				                          tempArray[i].unitSettlerLabels.add(l);
//						                    turnClean(s2.getX(), s2.getY()-1);
						                    tempArray[i].playerUI.lb.subSettlerCount(s2.getX(), s2.getY()); 
									           if(((tempArray[i].playerUI.lb.getSettlerCount(s2.getX(), s2.getY()) + tempArray[i].playerUI.lb.getArmyCount(s2.getX(), s2.getY())) == 0) && !tempArray[i].playerUI.lb.getHasBuild(s2.getX(), s2.getY()))      
												    tempArray[i].playerUI.lb.setOwner(s2.getX(), s2.getY(), null);  
				                       }
				                    }
					                 s2.setPosition(s2.getX(), s2.getY()-1);
				                    turnClean(s2.getX(), s2.getY()+1);
	                             turnClean(s.getX(), s.getY());
										  s2.takeMove();  
										}
									 }
								    else
									 {
									   Army s2 = (Army)temp;
										if(s2.getGroup() == s.getGroup())
										{
										  s2.setBounds(name.playerUI.lb.getSettlerCount(s2.getX(), s2.getY()-1), name.playerUI.lb.getArmyCount(s2.getX(), s2.getY()-1));//spaceTotalUnits);
										  for(int i = 0; i < 2; i++)//number of players
									     {
											  if(tempArray[i] == name)
											  {
												  tempArray[i].playerUI.lb.getHolder(s2.getX(), s2.getY()).add(s2.getLabel(), new Integer(5));    
						                    tempArray[i].playerUI.lb.getHolder(s2.getX(), s2.getY()-1).add(s2.getLabel(), new Integer(5));  
						                    tempArray[i].playerUI.lb.subArmyCount(s2.getX(), s2.getY()); 
									           if(((tempArray[i].playerUI.lb.getSettlerCount(s2.getX(), s2.getY()) + tempArray[i].playerUI.lb.getArmyCount(s2.getX(), s2.getY())) == 0) && !tempArray[i].playerUI.lb.getHasBuild(s2.getX(), s2.getY()))      
												    tempArray[i].playerUI.lb.setOwner(s2.getX(), s2.getY(), null);  
						     			        tempArray[i].playerUI.lb.addArmyCount(s2.getX(), s2.getY()-1);
//						                    turnClean(s2.getX(), s2.getY()-1);
											  }
											  else
											  {
												  JLabel l = new JLabel(s2.getIcon());
												  l.setBounds(s2.getCurrentbounds());
												  l.setName(s2.getLabelName());
						                    tempArray[i].playerUI.lb.getHolder(s2.getX(), s2.getY()-1).add(l, new Integer(5));  
						     			        tempArray[i].playerUI.lb.addArmyCount(s2.getX(), s2.getY()-1);
						                    leaveClean(s2.getX(), s2.getY(), s2);
				                          tempArray[i].unitArmyLabels.add(l);
//						                    turnClean(s2.getX(), s2.getY()-1);
						                    tempArray[i].playerUI.lb.subArmyCount(s2.getX(), s2.getY()); 
									           if(((tempArray[i].playerUI.lb.getSettlerCount(s2.getX(), s2.getY()) + tempArray[i].playerUI.lb.getArmyCount(s2.getX(), s2.getY())) == 0) && !tempArray[i].playerUI.lb.getHasBuild(s2.getX(), s2.getY()))      
												    tempArray[i].playerUI.lb.setOwner(s2.getX(), s2.getY(), null);  
				                       }
				                    }
							           s2.setPosition(s2.getX(), s2.getY()-1);
				                    turnClean(s2.getX(), s2.getY()+1);
				                    turnClean(s.getX(), s.getY());
										  s2.takeMove();  
										}
									 }
						        } 
						        break;  
						    case 4:
								  for(int k = 0; k < grp4.size(); k++)
								  {
								    Object temp = grp4.get(k);
									 if(temp.getClass().getName().equals("Settler"))
								    {
									   Settler s2 = (Settler)temp;
										if(s2.getGroup() == s.getGroup() && s2 != s)
										{
										  s2.setBounds(spaceTotalUnits);
										  for(int i = 0; i < 2; i++)//number of players
									     {
											  if(tempArray[i] == name)
											  {
												  tempArray[i].playerUI.lb.getHolder(s2.getX(), s2.getY()).add(s2.getLabel(), new Integer(5));    
						                    tempArray[i].playerUI.lb.getHolder(s2.getX(), s2.getY()-1).add(s2.getLabel(), new Integer(5));  
						                    tempArray[i].playerUI.lb.subSettlerCount(s2.getX(), s2.getY()); 
									           if(((tempArray[i].playerUI.lb.getSettlerCount(s2.getX(), s2.getY()) + tempArray[i].playerUI.lb.getArmyCount(s2.getX(), s2.getY())) == 0) && !tempArray[i].playerUI.lb.getHasBuild(s2.getX(), s2.getY()))      
												    tempArray[i].playerUI.lb.setOwner(s2.getX(), s2.getY(), null);  
						     			        tempArray[i].playerUI.lb.addSettlerCount(s2.getX(), s2.getY()-1);
//						                    turnClean(s2.getX(), s2.getY()-1);
											  }
											  else
											  {
												  JLabel l = new JLabel(s2.getIcon());
												  l.setBounds(s2.getCurrentbounds());
												  l.setName(s2.getLabelName());
						                    tempArray[i].playerUI.lb.getHolder(s2.getX(), s2.getY()-1).add(l, new Integer(5));  
						     			        tempArray[i].playerUI.lb.addSettlerCount(s2.getX(), s2.getY()-1);
						                    leaveClean(s2.getX(), s2.getY(), s2);
				                          tempArray[i].unitSettlerLabels.add(l);
//						                    turnClean(s2.getX(), s2.getY()-1);
						                    tempArray[i].playerUI.lb.subSettlerCount(s2.getX(), s2.getY()); 
									           if(((tempArray[i].playerUI.lb.getSettlerCount(s2.getX(), s2.getY()) + tempArray[i].playerUI.lb.getArmyCount(s2.getX(), s2.getY())) == 0) && !tempArray[i].playerUI.lb.getHasBuild(s2.getX(), s2.getY()))      
												    tempArray[i].playerUI.lb.setOwner(s2.getX(), s2.getY(), null);  
				                       }
				                    }
					                 s2.setPosition(s2.getX(), s2.getY()-1);
				                    turnClean(s2.getX(), s2.getY()+1);
	                             turnClean(s.getX(), s.getY());
										  s2.takeMove();  
										}
								    }  
									 else
									 {
									   Army s2 = (Army)temp;
										if(s2.getGroup() == s.getGroup())
										{
										  s2.setBounds(name.playerUI.lb.getSettlerCount(s2.getX(), s2.getY()-1), name.playerUI.lb.getArmyCount(s2.getX(), s2.getY()-1));//spaceTotalUnits);
										  for(int i = 0; i < 2; i++)//number of players
									     {
											  if(tempArray[i] == name)
											  {
												  tempArray[i].playerUI.lb.getHolder(s2.getX(), s2.getY()).add(s2.getLabel(), new Integer(5));    
						                    tempArray[i].playerUI.lb.getHolder(s2.getX(), s2.getY()-1).add(s2.getLabel(), new Integer(5));  
						                    tempArray[i].playerUI.lb.subArmyCount(s2.getX(), s2.getY()); 
									           if(((tempArray[i].playerUI.lb.getSettlerCount(s2.getX(), s2.getY()) + tempArray[i].playerUI.lb.getArmyCount(s2.getX(), s2.getY())) == 0) && !tempArray[i].playerUI.lb.getHasBuild(s2.getX(), s2.getY()))      
												    tempArray[i].playerUI.lb.setOwner(s2.getX(), s2.getY(), null);  
						     			        tempArray[i].playerUI.lb.addArmyCount(s2.getX(), s2.getY()-1);
//						                    turnClean(s2.getX(), s2.getY()-1);
											  }
											  else
											  {
												  JLabel l = new JLabel(s2.getIcon());
												  l.setBounds(s2.getCurrentbounds());
												  l.setName(s2.getLabelName());
						                    tempArray[i].playerUI.lb.getHolder(s2.getX(), s2.getY()-1).add(l, new Integer(5));  
						     			        tempArray[i].playerUI.lb.addArmyCount(s2.getX(), s2.getY()-1);
						                    leaveClean(s2.getX(), s2.getY(), s2);
				                          tempArray[i].unitArmyLabels.add(l);
//						                    turnClean(s2.getX(), s2.getY()-1);
						                    tempArray[i].playerUI.lb.subArmyCount(s2.getX(), s2.getY()); 
									           if(((tempArray[i].playerUI.lb.getSettlerCount(s2.getX(), s2.getY()) + tempArray[i].playerUI.lb.getArmyCount(s2.getX(), s2.getY())) == 0) && !tempArray[i].playerUI.lb.getHasBuild(s2.getX(), s2.getY()))      
												    tempArray[i].playerUI.lb.setOwner(s2.getX(), s2.getY(), null);  
				                       }
				                    }
							           s2.setPosition(s2.getX(), s2.getY()-1);
				                    turnClean(s2.getX(), s2.getY()+1);
				                    turnClean(s.getX(), s.getY());
										  s2.takeMove();  
										}
									 }
						        } 
						        break;  
							  } 
						  }

	                 s.setPosition(s.getX(), s.getY()-1);
                    turnClean(s.getX(), s.getY()+1);
                    turnClean(s.getX(), s.getY());
						  s.takeMove();  

                    if(name.playerUI.lb.hasHut(s.getX(), s.getY()) > 0)              
						  {
							 String type = name.playerUI.lb.getHutResource(s.getX(), s.getY());
							 name.addResource(type, "hut"); 
						    name.playerUI.tellHutResource(type);   
							 name.playerBoard.setHut(1);
							 for(int k = 0; k < 2; k++)
							 {
							   tempArray[k].playerUI.lb.removeHut(s.getX(), s.getY());
						    }
						    if(name.getNation().equals("China"))
							   name.addCulture(3);  
						    resetSettler(s);
						  }
						  if(s.getMoves() == 0)
			           {
  	 	                resetSettler(s);
					     }
					   }
					 }
				  }				
				}//check Settler move
			   else if((a != null) && (a.getY()-1 >= 0))
				{
              Player[] tempArray = name.getPlayerUI().getPlayers();
	           boolean democGov = false;       
				  spaceTotalUnits = name.playerUI.lb.getSettlerCount(a.getX(), a.getY()-1) + name.playerUI.lb.getArmyCount(a.getX(), a.getY()-1);
				  if(((name.playerUI.lb.getOwner(a.getX(), a.getY()-1) != null) && (name != name.playerUI.lb.getOwner(a.getX(), a.getY()-1))) || (name.playerUI.lb.hasVillage(a.getX(), a.getY()-1) > 0))
				  {
//				    System.out.println("COMBAT");//do combat
					 conductCombat = true;
				  }
              if((name.playerUI.lb.isCitySpace(a.getX(), a.getY()-1)) && (name != name.playerUI.lb.getOwner(a.getX(), a.getY()-1)) && (name.getCurrentGov() == 2))
				  {
					   JOptionPane illegal = new JOptionPane();
				      GameImageIcon im = getImage("data/"+name.getNation()+"Icon.png");  
		            illegal.showMessageDialog(name.playerUI.map, "YOU CANNOT ATTACK ENEMY CITY WHEN\n YOU ARE UNDER DEMOCRACY!", "ILLEGAL MOVE",
				  					 			        JOptionPane.INFORMATION_MESSAGE, im);	 	 
				    democGov = true;
				    dir = -1;
				  }
				  else if(!conductCombat && ((spaceTotalUnits >= name.getStacklimit()) || (spaceTotalUnits + name.getGrouptotal(a.getGroup()) > name.getStacklimit())  || (name.playerUI.lb.isCitySpace(a.getX(), a.getY()-1) && a.getMoves() < 2)))
				    dir = -1;
				  else
				  {
                if(name.playerUI.lb.getTerrain(a.getX(), a.getY()).equals("Water") && !name.getWaterLand() && (a.getMoves() <= 1) && (name.playerUI.lb.getTerrain(a.getX(), a.getY()-1) == null || name.playerUI.lb.checkExplored(a.getX(), a.getY()-1)))
					 {
					   JOptionPane illegal = new JOptionPane();
				      GameImageIcon im = getImage("data/"+name.getNation()+"Icon.png");  
		            illegal.showMessageDialog(name.playerUI.map, "YOU CANNOT LAND IN WATER WITH CURRENT TECHNOLOGY!\nMOVE UNIT TO LAND", "CANNOT END MOVE HERE",
				  					 			        JOptionPane.INFORMATION_MESSAGE, im);	 	 
					 }            
				    else if(name.playerUI.lb.checkExplored(a.getX(), a.getY()-1))
					 {
                  if(name.playerUI.lb.getHolder(a.getX(), a.getY()).getParent().getName().equals("panel3"))
					   {
						   for(int k = 0; k < 2; k++)//number of players
					      {
						     tempArray[k].playerUI.lb.populateTile(name.getPlayerUI().boardPosition[2], 2, 1, tempArray[k]);
							  tempArray[k].playerUI.map.remove(tempArray[k].playerUI.myfog4);
							  tempArray[k].playerUI.map.add(tempArray[k].playerUI.mypanel2, 4);
					        tempArray[k].playerUI.map.validate();
					        tempArray[k].playerUI.map.repaint();
							}
  						}    
		            else if(name.playerUI.lb.getHolder(a.getX(), a.getY()).getParent().getName().equals("panel2"))
					   {
						   for(int k = 0; k < 2; k++)//number of players
					      {
						     tempArray[k].playerUI.lb.populateTile(name.getPlayerUI().boardPosition[1], 1, 1, tempArray[k]);
							  tempArray[k].playerUI.map.remove(tempArray[k].playerUI.myfog2);
							  tempArray[k].playerUI.map.add(tempArray[k].playerUI.mypanel1, 2);
					        tempArray[k].playerUI.map.validate();
					        tempArray[k].playerUI.map.repaint();
							}
  						}    
		            else if(name.playerUI.lb.getHolder(a.getX(), a.getY()).getParent().getName().equals("panel1"))
					   {
						   for(int k = 0; k < 2; k++)//number of players
					      {
						     tempArray[k].playerUI.lb.populateTile(name.getPlayerUI().boardPosition[0], 0, 1, tempArray[k]);
							  tempArray[k].playerUI.map.remove(tempArray[k].playerUI.myfog1);
							  tempArray[k].playerUI.map.add(tempArray[k].playerUI.mypanel0, 0);
					        tempArray[k].playerUI.map.validate();
					        tempArray[k].playerUI.map.repaint();
							}
						}
		            else if(name.playerUI.lb.getHolder(a.getX(), a.getY()).getParent().getName().equals("panel7"))
					   {
						   for(int k = 0; k < 2; k++)//number of players
					      {
						     tempArray[k].playerUI.lb.populateTile(name.getPlayerUI().boardPosition[6], 6, 1, tempArray[k]);
							  tempArray[k].playerUI.map.remove(tempArray[k].playerUI.myfog5);
							  tempArray[k].playerUI.map.add(tempArray[k].playerUI.mypanel6, 5);
					        tempArray[k].playerUI.map.validate();
					        tempArray[k].playerUI.map.repaint();
							}
						}
		            else if(name.playerUI.lb.getHolder(a.getX(), a.getY()).getParent().getName().equals("panel6"))
					   {
						   for(int k = 0; k < 2; k++)//number of players
					      {
						     tempArray[k].playerUI.lb.populateTile(name.getPlayerUI().boardPosition[5], 5, 1, tempArray[k]);
							  tempArray[k].playerUI.map.remove(tempArray[k].playerUI.myfog3);
							  tempArray[k].playerUI.map.add(tempArray[k].playerUI.mypanel5, 3);
					        tempArray[k].playerUI.map.validate();
					        tempArray[k].playerUI.map.repaint();
							}
						}
                  dir = 0;
	   				a.takeMove();  
						if(a.getGroup() > 0)
						{
						  switch(a.getGroup())
						  {
						    case 1:
								  for(int k = 0; k < grp1.size(); k++)
								  {
								    Object temp = grp1.get(k);
									 if(temp.getClass().getName().equals("Settler"))
								    {
									   Settler s2 = (Settler)temp;
										if(s2.getGroup() == a.getGroup())
										{
						   			  s2.takeMove();  
										}
									 }
								    else
									 {
									   Army s2 = (Army)temp;
										if(s2.getGroup() == a.getGroup() && s2 != a)
										{
										  s2.takeMove();
										}
									 }
						        } 
						        break;  
						    case 2:
								  for(int k = 0; k < grp2.size(); k++)
								  {
								    Object temp = grp2.get(k);
									 if(temp.getClass().getName().equals("Settler"))
								    {
									   Settler s2 = (Settler)temp;
										if(s2.getGroup() == a.getGroup())
										{
						   			  s2.takeMove();  
										}
									 }
								    else
									 {
									   Army s2 = (Army)temp;
										if(s2.getGroup() == a.getGroup() && s2 != a)
										{
										  s2.takeMove();
										}
									 }
						        } 
						        break;  
						    case 3:
								  for(int k = 0; k < grp3.size(); k++)
								  {
								    Object temp = grp3.get(k);
									 if(temp.getClass().getName().equals("Settler"))
								    {
									   Settler s2 = (Settler)temp;
										if(s2.getGroup() == a.getGroup())
										{
						   			  s2.takeMove();  
										}
									 }
								    else
									 {
									   Army s2 = (Army)temp;
										if(s2.getGroup() == a.getGroup() && s2 != a)
										{
										  s2.takeMove();
										}
									 }
						        } 
						        break;  
						    case 4:
								  for(int k = 0; k < grp4.size(); k++)
								  {
								    Object temp = grp4.get(k);
									 if(temp.getClass().getName().equals("Settler"))
								    {
									   Settler s2 = (Settler)temp;
										if(s2.getGroup() == a.getGroup())
										{
						   			  s2.takeMove();  
										}
									 }
								    else
									 {
									   Army s2 = (Army)temp;
										if(s2.getGroup() == a.getGroup() && s2 != a)
										{
										  s2.takeMove();
										}
									 }
						        } 
						        break;  
						  }
						}
						if(a.getMoves() == 0)
		            {
                    resetArmy(a);
				      }
					 }
				    else
					 {
                  if(name.playerUI.lb.getTerrain(a.getX(), a.getY()-1).equals("Water") && !name.getWaterCross())    
						{
						  dir = -1;
						}
	               else if(name.playerUI.lb.getTerrain(a.getX(), a.getY()-1).equals("Water") && !name.getWaterLand() && (a.getMoves() < 2)) 
						{
						  dir = -1;
						}          
						else
						{	 
						  boolean SettlerOnly = false; 
						  if(name.playerUI.lb.getArmyCount(a.getX(), a.getY()-1) == 0 && conductCombat) 
						  {
							 SettlerOnly = true;
						  }
//						  a.setBounds(name.playerUI.lb.getSettlerCount(a.getX(), a.getY()-1), name.playerUI.lb.getArmyCount(a.getX(), a.getY()-1));//spaceTotalUnits);
						  for(int k = 0; k < 2; k++)//number of players
					     {
							  if(tempArray[k] == name)
							  {
								  tempArray[k].playerUI.lb.getHolder(a.getX(), a.getY()).add(a.getLabel(), new Integer(5));    
		                    tempArray[k].playerUI.lb.getHolder(a.getX(), a.getY()-1).add(a.getLabel(), new Integer(5));  
		                    tempArray[k].playerUI.lb.subArmyCount(a.getX(), a.getY()); 
					           if(((tempArray[k].playerUI.lb.getSettlerCount(a.getX(), a.getY()) + tempArray[k].playerUI.lb.getArmyCount(a.getX(), a.getY())) == 0) && !tempArray[k].playerUI.lb.getHasBuild(a.getX(), a.getY()))      
								    tempArray[k].playerUI.lb.setOwner(a.getX(), a.getY(), null);  
		     			        tempArray[k].playerUI.lb.addArmyCount(a.getX(), a.getY()-1);
//		                    turnClean(a.getX(), a.getY()-1);
							  }
							  else
							  {
								  JLabel l = new JLabel(a.getIcon());
								  l.setBounds(a.getCurrentbounds());
								  l.setName(a.getLabelName());
		                    tempArray[k].playerUI.lb.getHolder(a.getX(), a.getY()-1).add(l, new Integer(5));  
		     			        tempArray[k].playerUI.lb.addArmyCount(a.getX(), a.getY()-1);
		                    leaveClean(a.getX(), a.getY(), a);
                          tempArray[k].unitArmyLabels.add(l);
//		                    turnClean(a.getX(), a.getY()-1);
		                    tempArray[k].playerUI.lb.subArmyCount(a.getX(), a.getY()); 
					           if(((tempArray[k].playerUI.lb.getSettlerCount(a.getX(), a.getY()) + tempArray[k].playerUI.lb.getArmyCount(a.getX(), a.getY())) == 0) && !tempArray[k].playerUI.lb.getHasBuild(a.getX(), a.getY()))      
								    tempArray[k].playerUI.lb.setOwner(a.getX(), a.getY(), null);  
                       }
                    }

						  if(a.getGroup() > 0)
						  {
						    moveArmyGroup_1(a); 
						  }	

			           a.setPosition(a.getX(), a.getY()-1);
                    turnClean(a.getX(), a.getY());
                    turnClean(a.getX(), a.getY()+1);
						  a.takeMove();  
//System.out.println("# armies "+name.playerUI.lb.getArmyCount(a.getX(), a.getY()));
                    if(name.playerUI.lb.hasHut(a.getX(), a.getY()) > 0)              
						  {
							 String type = name.playerUI.lb.getHutResource(a.getX(), a.getY());
							 name.addResource(type, "hut"); 
						    name.playerUI.tellHutResource(type);   
							 name.playerBoard.setHut(1);
							 for(int k = 0; k < 2; k++)
							 {
							   tempArray[k].playerUI.lb.removeHut(a.getX(), a.getY());
						    }
						    if(name.getNation().equals("China"))
							   name.addCulture(3);  
						    resetArmy(a);
						  }
						  if(a.getMoves() == 0 && !conductCombat)
		              {
                      resetArmy(a);
				        }
                    if(conductCombat)
						  {
                      combatPause();  
					       if(name.playerUI.lb.hasVillage(a.getX(), a.getY()) > 0)
							 {
						      combatUnit = a;
							   combat = new Combat(new Player("Village"), name, this);
						      a.combatMoves();
							 }    
                      else if(SettlerOnly)
							 {
						      Player loser = name.playerUI.lb.getOwner(a.getX(), a.getY());
								Vector sl = loser.getSettlerVector();
								int k;
								for(k = 0; k < sl.size(); k++)
								{
						        Settler temp = (Settler) sl.get(k); 
								  if((temp.getX() == a.getX()) && (temp.getY() == a.getY()))
						        {
								    JLayeredPane lp = name.playerUI.lb.getHolder(a.getX(), a.getY());    
								    loser.removeSettler(temp);
								    name.playerUI.lb.subSettlerCount(a.getX(), a.getY());  
									 name.playerUI.lb.getHolder(a.getX(), a.getY()).remove(temp.getLabel());
									 name.playerUI.lb.getHolder(a.getX(), a.getY()).validate();
									 name.playerUI.lb.getHolder(a.getX(), a.getY()).repaint();
						  	 	  }
								} 
							   combat = new Combat(name.playerUI.lb.getOwner(a.getX(), a.getY()), name, true);
// 			     		      for(int i = 0; i < movePeices.size(); i++)
//						  		  nm[i].addActionListener(this);  
						      done.addActionListener(this);  
						      lt.addActionListener(this);  
							   rt.addActionListener(this);  
							   up.addActionListener(this);  
							   dw.addActionListener(this);  
							   resetArmy(a);
							 } 
							 else
							 {
						      combatUnit = a;
							   combat = new Combat(name.playerUI.lb.getOwner(a.getX(), a.getY()), name, this);
						      a.combatMoves();
						    }
						  } 
						}
					 }
				  }				
				}//check if Army  
			 }
          else if(direction == 2)
		    {
            Player[] tempArray = name.getPlayerUI().getPlayers();
			   if((s != null) && (s.getX()+1 < 8))
			   {
				  spaceTotalUnits = name.playerUI.lb.getSettlerCount(s.getX()+1, s.getY()) + name.playerUI.lb.getArmyCount(s.getX()+1, s.getY());
				  if((name.playerUI.lb.getOwner(s.getX()+1, s.getY()) != null) && (name != name.playerUI.lb.getOwner(s.getX()+1, s.getY())))
				  {
//				    System.out.println("COMBAT");//do combat
					 conductCombat = true;
				  }
				  if(conductCombat || ((spaceTotalUnits >= name.getStacklimit()) || (spaceTotalUnits + name.getGrouptotal(s.getGroup()) > name.getStacklimit()) || (name.playerUI.lb.isCitySpace(s.getX()+1, s.getY()) && s.getMoves() < 2)))
				    dir = -1;
				  else
				  {
                if(name.playerUI.lb.getTerrain(s.getX(), s.getY()).equals("Water") && !name.getWaterLand() && (s.getMoves() <= 1) && (name.playerUI.lb.getTerrain(s.getX()+1, s.getY()) == null || name.playerUI.lb.checkExplored(s.getX()+1, s.getY())))
					 {
					   JOptionPane illegal = new JOptionPane();
				      GameImageIcon im = getImage("data/"+name.getNation()+"Icon.png");  
		            illegal.showMessageDialog(name.playerUI.map, "YOU CANNOT LAND IN WATER WITH CURRENT TECHNOLOGY!\nMOVE UNIT TO LAND", "CANNOT END MOVE HERE",
				  					 			        JOptionPane.INFORMATION_MESSAGE, im);	 	 
					 }            
				    else if(name.playerUI.lb.checkExplored(s.getX()+1, s.getY()))
					 {
                  if(name.playerUI.lb.getHolder(s.getX(), s.getY()).getParent().getName().equals("panel1"))
					   {
						   for(int k = 0; k < 2; k++)//number of players
					      {
						     tempArray[k].playerUI.lb.populateTile(name.getPlayerUI().boardPosition[5], 5, 2, tempArray[k]);
							  tempArray[k].playerUI.map.remove(tempArray[k].playerUI.myfog3);
							  tempArray[k].playerUI.map.add(tempArray[k].playerUI.mypanel5, 3);
					        tempArray[k].playerUI.map.validate();
					        tempArray[k].playerUI.map.repaint();
							}
  						}    
		            else if(name.playerUI.lb.getHolder(s.getX(), s.getY()).getParent().getName().equals("panel2"))
					   {
						   for(int k = 0; k < 2; k++)//number of players
					      {
						     tempArray[k].playerUI.lb.populateTile(name.getPlayerUI().boardPosition[6], 6, 2, tempArray[k]);
							  tempArray[k].playerUI.map.remove(tempArray[k].playerUI.myfog5);
							  tempArray[k].playerUI.map.add(tempArray[k].playerUI.mypanel6, 5);
					        tempArray[k].playerUI.map.validate();
					        tempArray[k].playerUI.map.repaint();
							}
  						}    
		            else if(name.playerUI.lb.getHolder(s.getX(), s.getY()).getParent().getName().equals("panel3"))
					   {
						   for(int k = 0; k < 2; k++)//number of players
					      {
						     tempArray[k].playerUI.lb.populateTile(name.getPlayerUI().boardPosition[7], 7, 2, tempArray[k]);
							  tempArray[k].playerUI.map.remove(tempArray[k].playerUI.myfog6);
							  tempArray[k].playerUI.map.add(tempArray[k].playerUI.mypanel7, 7);
					        tempArray[k].playerUI.map.validate();
					        tempArray[k].playerUI.map.repaint();
							}
						}
	   				s.takeMove();  
						if(s.getGroup() > 0)
						{
						  switch(s.getGroup())
						  {
						    case 1:
								  for(int k = 0; k < grp1.size(); k++)
								  {
								    Object temp = grp1.get(k);
									 if(temp.getClass().getName().equals("Settler"))
								    {
									   Settler s2 = (Settler)temp;
										if(s2.getGroup() == s.getGroup() && s2 != s)
										{
						   			  s2.takeMove();  
										}
									 }
								    else
									 {
									   Army s2 = (Army)temp;
										if(s2.getGroup() == s.getGroup())
										{
										  s2.takeMove();
										}
									 }
						        } 
						        break;  
						    case 2:
								  for(int k = 0; k < grp2.size(); k++)
								  {
								    Object temp = grp2.get(k);
									 if(temp.getClass().getName().equals("Settler"))
								    {
									   Settler s2 = (Settler)temp;
										if(s2.getGroup() == s.getGroup() && s2 != s)
										{
						   			  s2.takeMove();  
										}
									 }
								    else
									 {
									   Army s2 = (Army)temp;
										if(s2.getGroup() == s.getGroup())
										{
										  s2.takeMove();
										}
									 }
						        } 
						        break;  
						    case 3:
								  for(int k = 0; k < grp3.size(); k++)
								  {
								    Object temp = grp3.get(k);
									 if(temp.getClass().getName().equals("Settler"))
								    {
									   Settler s2 = (Settler)temp;
										if(s2.getGroup() == s.getGroup() && s2 != s)
										{
						   			  s2.takeMove();  
										}
									 }
								    else
									 {
									   Army s2 = (Army)temp;
										if(s2.getGroup() == s.getGroup())
										{
										  s2.takeMove();
										}
									 }
						        } 
						        break;  
						    case 4:
								  for(int k = 0; k < grp4.size(); k++)
								  {
								    Object temp = grp4.get(k);
									 if(temp.getClass().getName().equals("Settler"))
								    {
									   Settler s2 = (Settler)temp;
										if(s2.getGroup() == s.getGroup() && s2 != s)
										{
						   			  s2.takeMove();  
										}
									 }
								    else
									 {
									   Army s2 = (Army)temp;
										if(s2.getGroup() == s.getGroup())
										{
										  s2.takeMove();
										}
									 }
						        } 
						        break;  
							 }
						  }
						if(s.getMoves() == 0)
		            {
                    resetSettler(s);
				      }
					 }
				    else
					 {
	               if((name.playerUI.lb.hasHut(s.getX()+1, s.getY()) > 0) && name.getCurrentGov() != 1)
						{
						  dir = -1;
						}           
						else if(name.playerUI.lb.hasVillage(s.getX()+1, s.getY()) > 0)
						{
						  dir = -1;
						}
                  else if(name.playerUI.lb.getTerrain(s.getX()+1, s.getY()).equals("Water") && !name.getWaterCross())    
						{
						  dir = -1;
						}
	               else if(name.playerUI.lb.getTerrain(s.getX()+1, s.getY()).equals("Water") && !name.getWaterLand() && (s.getMoves() < 2)) 
						{
						  dir = -1;
						}          
						else 
						{
						  s.setBounds(spaceTotalUnits);
						  for(int k = 0; k < 2; k++)//number of players
					     {
							  if(tempArray[k] == name)
							  {
								  tempArray[k].playerUI.lb.getHolder(s.getX(), s.getY()).add(s.getLabel(), new Integer(5));    
		                    tempArray[k].playerUI.lb.getHolder(s.getX()+1, s.getY()).add(s.getLabel(), new Integer(5));  
		                    tempArray[k].playerUI.lb.subSettlerCount(s.getX(), s.getY()); 
					           if(((tempArray[k].playerUI.lb.getSettlerCount(s.getX(), s.getY()) + tempArray[k].playerUI.lb.getArmyCount(s.getX(), s.getY())) == 0) && !tempArray[k].playerUI.lb.getHasBuild(s.getX(), s.getY()))      
								    tempArray[k].playerUI.lb.setOwner(s.getX(), s.getY(), null);  
		     			        tempArray[k].playerUI.lb.addSettlerCount(s.getX()+1, s.getY());
//		                    turnClean(s.getX()+1, s.getY());
							  }
							  else
							  {
								  JLabel l = new JLabel(s.getIcon());
								  l.setBounds(s.getCurrentbounds());
								  l.setName(s.getLabelName());
		                    tempArray[k].playerUI.lb.getHolder(s.getX()+1, s.getY()).add(l, new Integer(5));  
		     			        tempArray[k].playerUI.lb.addSettlerCount(s.getX()+1, s.getY());
		                    leaveClean(s.getX(), s.getY(), s);
                          tempArray[k].unitSettlerLabels.add(l);
//		                    turnClean(s.getX()+1, s.getY());
		                    tempArray[k].playerUI.lb.subSettlerCount(s.getX(), s.getY()); 
					           if(((tempArray[k].playerUI.lb.getSettlerCount(s.getX(), s.getY()) + tempArray[k].playerUI.lb.getArmyCount(s.getX(), s.getY())) == 0) && !tempArray[k].playerUI.lb.getHasBuild(s.getX(), s.getY()))      
								    tempArray[k].playerUI.lb.setOwner(s.getX(), s.getY(), null);  
                       }
 			           }   
						  
						  if(s.getGroup() > 0)
						  {
						    switch(s.getGroup())
						    {
						      case 1:
								  for(int k = 0; k < grp1.size(); k++)
								  {
								    Object temp = grp1.get(k);
									 if(temp.getClass().getName().equals("Settler"))
								    {
									   Settler s2 = (Settler)temp;
										if(s2.getGroup() == s.getGroup() && s2 != s)
										{
										  s2.setBounds(spaceTotalUnits);
										  for(int i = 0; i < 2; i++)//number of players
									     {
											  if(tempArray[i] == name)
											  {
												  tempArray[i].playerUI.lb.getHolder(s2.getX(), s2.getY()).add(s2.getLabel(), new Integer(5));    
						                    tempArray[i].playerUI.lb.getHolder(s2.getX()+1, s2.getY()).add(s2.getLabel(), new Integer(5));  
						                    tempArray[i].playerUI.lb.subSettlerCount(s2.getX(), s2.getY()); 
									           if(((tempArray[i].playerUI.lb.getSettlerCount(s2.getX(), s2.getY()) + tempArray[i].playerUI.lb.getArmyCount(s2.getX(), s2.getY())) == 0) && !tempArray[i].playerUI.lb.getHasBuild(s2.getX(), s2.getY()))      
												    tempArray[i].playerUI.lb.setOwner(s2.getX(), s2.getY(), null);  
						     			        tempArray[i].playerUI.lb.addSettlerCount(s2.getX()+1, s2.getY());
//						                    turnClean(s2.getX()+1, s2.getY());
											  }
											  else
											  {
												  JLabel l = new JLabel(s2.getIcon());
												  l.setBounds(s2.getCurrentbounds());
												  l.setName(s2.getLabelName());
						                    tempArray[i].playerUI.lb.getHolder(s2.getX()+1, s2.getY()).add(l, new Integer(5));  
						     			        tempArray[i].playerUI.lb.addSettlerCount(s2.getX()+1, s2.getY());
						                    leaveClean(s2.getX(), s2.getY(), s2);
				                          tempArray[i].unitSettlerLabels.add(l);
//						                    turnClean(s2.getX()+1, s2.getY());
						                    tempArray[i].playerUI.lb.subSettlerCount(s2.getX(), s2.getY()); 
									           if(((tempArray[i].playerUI.lb.getSettlerCount(s2.getX(), s2.getY()) + tempArray[i].playerUI.lb.getArmyCount(s2.getX(), s2.getY())) == 0) && !tempArray[i].playerUI.lb.getHasBuild(s2.getX(), s2.getY()))      
												    tempArray[i].playerUI.lb.setOwner(s2.getX(), s2.getY(), null);  
				                       }
				                    }
					                 s2.setPosition(s2.getX()+1, s2.getY());
				                    turnClean(s2.getX()-1, s2.getY());
				                    turnClean(s.getX(), s.getY());
										  s2.takeMove();  
										}
								    }
								    else
									 {
									   Army s2 = (Army)temp;
										if(s2.getGroup() == s.getGroup())
										{
										  s2.setBounds(name.playerUI.lb.getSettlerCount(s2.getX()+1, s2.getY()), name.playerUI.lb.getArmyCount(s2.getX()+1, s2.getY()));//spaceTotalUnits);
										  for(int i = 0; i < 2; i++)//number of players
									     {
											  if(tempArray[i] == name)
											  {
												  tempArray[i].playerUI.lb.getHolder(s2.getX(), s2.getY()).add(s2.getLabel(), new Integer(5));    
						                    tempArray[i].playerUI.lb.getHolder(s2.getX()+1, s2.getY()).add(s2.getLabel(), new Integer(5));  
						                    tempArray[i].playerUI.lb.subArmyCount(s2.getX(), s2.getY()); 
									           if(((tempArray[i].playerUI.lb.getSettlerCount(s2.getX(), s2.getY()) + tempArray[i].playerUI.lb.getArmyCount(s2.getX(), s2.getY())) == 0) && !tempArray[i].playerUI.lb.getHasBuild(s2.getX(), s2.getY()))      
												    tempArray[i].playerUI.lb.setOwner(s2.getX(), s2.getY(), null);  
						     			        tempArray[i].playerUI.lb.addArmyCount(s2.getX()+1, s2.getY());
//						                    turnClean(s2.getX()+1, s2.getY());
											  }
											  else
											  {
												  JLabel l = new JLabel(s2.getIcon());
												  l.setBounds(s2.getCurrentbounds());
												  l.setName(s2.getLabelName());
						                    tempArray[i].playerUI.lb.getHolder(s2.getX()+1, s2.getY()).add(l, new Integer(5));  
						     			        tempArray[i].playerUI.lb.addArmyCount(s2.getX()+1, s2.getY());
						                    leaveClean(s2.getX(), s2.getY(), s2);
				                          tempArray[i].unitArmyLabels.add(l);
//						                    turnClean(s2.getX()+1, s2.getY());
						                    tempArray[i].playerUI.lb.subArmyCount(s2.getX(), s2.getY()); 
									           if(((tempArray[i].playerUI.lb.getSettlerCount(s2.getX(), s2.getY()) + tempArray[i].playerUI.lb.getArmyCount(s2.getX(), s2.getY())) == 0) && !tempArray[i].playerUI.lb.getHasBuild(s2.getX(), s2.getY()))      
												    tempArray[i].playerUI.lb.setOwner(s2.getX(), s2.getY(), null);  
				                       }
				                    }
							           s2.setPosition(s2.getX()+1, s2.getY());
				                    turnClean(s2.getX()-1, s2.getY());
				                    turnClean(s.getX(), s.getY());
										  s2.takeMove();  
										}
									 }
						        } 
						        break;  
						    case 2:
								  for(int k = 0; k < grp2.size(); k++)
								  {
								    Object temp = grp2.get(k);
									 if(temp.getClass().getName().equals("Settler"))
								    {
									   Settler s2 = (Settler)temp;
										if(s2.getGroup() == s.getGroup() && s2 != s)
										{
										  s2.setBounds(spaceTotalUnits);
										  for(int i = 0; i < 2; i++)//number of players
									     {
											  if(tempArray[i] == name)
											  {
												  tempArray[i].playerUI.lb.getHolder(s2.getX(), s2.getY()).add(s2.getLabel(), new Integer(5));    
						                    tempArray[i].playerUI.lb.getHolder(s2.getX()+1, s2.getY()).add(s2.getLabel(), new Integer(5));  
						                    tempArray[i].playerUI.lb.subSettlerCount(s2.getX(), s2.getY()); 
									           if(((tempArray[i].playerUI.lb.getSettlerCount(s2.getX(), s2.getY()) + tempArray[i].playerUI.lb.getArmyCount(s2.getX(), s2.getY())) == 0) && !tempArray[i].playerUI.lb.getHasBuild(s2.getX(), s2.getY()))      
												    tempArray[i].playerUI.lb.setOwner(s2.getX(), s2.getY(), null);  
						     			        tempArray[i].playerUI.lb.addSettlerCount(s2.getX()+1, s2.getY());
//						                    turnClean(s2.getX()+1, s2.getY());
											  }
											  else
											  {
												  JLabel l = new JLabel(s2.getIcon());
												  l.setBounds(s2.getCurrentbounds());
												  l.setName(s2.getLabelName());
						                    tempArray[i].playerUI.lb.getHolder(s2.getX()+1, s2.getY()).add(l, new Integer(5));  
						     			        tempArray[i].playerUI.lb.addSettlerCount(s2.getX()+1, s2.getY());
						                    leaveClean(s2.getX(), s2.getY(), s2);
				                          tempArray[i].unitSettlerLabels.add(l);
//						                    turnClean(s2.getX()+1, s2.getY());
						                    tempArray[i].playerUI.lb.subSettlerCount(s2.getX(), s2.getY()); 
									           if(((tempArray[i].playerUI.lb.getSettlerCount(s2.getX(), s2.getY()) + tempArray[i].playerUI.lb.getArmyCount(s2.getX(), s2.getY())) == 0) && !tempArray[i].playerUI.lb.getHasBuild(s2.getX(), s2.getY()))      
												    tempArray[i].playerUI.lb.setOwner(s2.getX(), s2.getY(), null);  
				                       }
				                    }
					                 s2.setPosition(s2.getX()+1, s2.getY());
				                    turnClean(s2.getX()-1, s2.getY());
				                    turnClean(s.getX(), s.getY());
										  s2.takeMove();  
										}
								    }  
									 else
									 {
									   Army s2 = (Army)temp;
										if(s2.getGroup() == s.getGroup())
										{
										  s2.setBounds(name.playerUI.lb.getSettlerCount(s2.getX()+1, s2.getY()), name.playerUI.lb.getArmyCount(s2.getX()+1, s2.getY()));//spaceTotalUnits);
										  for(int i = 0; i < 2; i++)//number of players
									     {
											  if(tempArray[i] == name)
											  {
												  tempArray[i].playerUI.lb.getHolder(s2.getX(), s2.getY()).add(s2.getLabel(), new Integer(5));    
						                    tempArray[i].playerUI.lb.getHolder(s2.getX()+1, s2.getY()).add(s2.getLabel(), new Integer(5));  
						                    tempArray[i].playerUI.lb.subArmyCount(s2.getX(), s2.getY()); 
									           if(((tempArray[i].playerUI.lb.getSettlerCount(s2.getX(), s2.getY()) + tempArray[i].playerUI.lb.getArmyCount(s2.getX(), s2.getY())) == 0) && !tempArray[i].playerUI.lb.getHasBuild(s2.getX(), s2.getY()))      
												    tempArray[i].playerUI.lb.setOwner(s2.getX(), s2.getY(), null);  
						     			        tempArray[i].playerUI.lb.addArmyCount(s2.getX()+1, s2.getY());
//						                    turnClean(s2.getX()+1, s2.getY());
											  }
											  else
											  {
												  JLabel l = new JLabel(s2.getIcon());
												  l.setBounds(s2.getCurrentbounds());
												  l.setName(s2.getLabelName());
						                    tempArray[i].playerUI.lb.getHolder(s2.getX()+1, s2.getY()).add(l, new Integer(5));  
						     			        tempArray[i].playerUI.lb.addArmyCount(s2.getX()+1, s2.getY());
						                    leaveClean(s2.getX(), s2.getY(), s2);
				                          tempArray[i].unitArmyLabels.add(l);
//						                    turnClean(s2.getX()+1, s2.getY());
						                    tempArray[i].playerUI.lb.subArmyCount(s2.getX(), s2.getY()); 
									           if(((tempArray[i].playerUI.lb.getSettlerCount(s2.getX(), s2.getY()) + tempArray[i].playerUI.lb.getArmyCount(s2.getX(), s2.getY())) == 0) && !tempArray[i].playerUI.lb.getHasBuild(s2.getX(), s2.getY()))      
												    tempArray[i].playerUI.lb.setOwner(s2.getX(), s2.getY(), null);  
				                       }
				                    }
							           s2.setPosition(s2.getX()+1, s2.getY());
				                    turnClean(s2.getX()-1, s2.getY());
				                    turnClean(s.getX(), s.getY());
										  s2.takeMove();  
										}
									 }
						        } 
						        break;  
						    case 3:
								  for(int k = 0; k < grp3.size(); k++)
								  {
								    Object temp = grp3.get(k);
									 if(temp.getClass().getName().equals("Settler"))
								    {
									   Settler s2 = (Settler)temp;
										if(s2.getGroup() == s.getGroup() && s2 != s)
										{
										  s2.setBounds(spaceTotalUnits);
										  for(int i = 0; i < 2; i++)//number of players
									     {
											  if(tempArray[i] == name)
											  {
												  tempArray[i].playerUI.lb.getHolder(s2.getX(), s2.getY()).add(s2.getLabel(), new Integer(5));    
						                    tempArray[i].playerUI.lb.getHolder(s2.getX()+1, s2.getY()).add(s2.getLabel(), new Integer(5));  
						                    tempArray[i].playerUI.lb.subSettlerCount(s2.getX(), s2.getY()); 
									           if(((tempArray[i].playerUI.lb.getSettlerCount(s2.getX(), s2.getY()) + tempArray[i].playerUI.lb.getArmyCount(s2.getX(), s2.getY())) == 0) && !tempArray[i].playerUI.lb.getHasBuild(s2.getX(), s2.getY()))      
												    tempArray[i].playerUI.lb.setOwner(s2.getX(), s2.getY(), null);  
						     			        tempArray[i].playerUI.lb.addSettlerCount(s2.getX()+1, s2.getY());
//						                    turnClean(s2.getX()+1, s2.getY());
											  }
											  else
											  {
												  JLabel l = new JLabel(s2.getIcon());
												  l.setBounds(s2.getCurrentbounds());
												  l.setName(s2.getLabelName());
						                    tempArray[i].playerUI.lb.getHolder(s2.getX()+1, s2.getY()).add(l, new Integer(5));  
						     			        tempArray[i].playerUI.lb.addSettlerCount(s2.getX()+1, s2.getY());
						                    leaveClean(s2.getX(), s2.getY(), s2);
				                          tempArray[i].unitSettlerLabels.add(l);
//						                    turnClean(s2.getX()+1, s2.getY());
						                    tempArray[i].playerUI.lb.subSettlerCount(s2.getX(), s2.getY()); 
									           if(((tempArray[i].playerUI.lb.getSettlerCount(s2.getX(), s2.getY()) + tempArray[i].playerUI.lb.getArmyCount(s2.getX(), s2.getY())) == 0) && !tempArray[i].playerUI.lb.getHasBuild(s2.getX(), s2.getY()))      
												    tempArray[i].playerUI.lb.setOwner(s2.getX(), s2.getY(), null);  
				                       }
				                    }
					                 s2.setPosition(s2.getX()+1, s2.getY());
				                    turnClean(s2.getX()-1, s2.getY());
				                    turnClean(s.getX(), s.getY());
										  s2.takeMove();  
										}
									 }
								    else
									 {
									   Army s2 = (Army)temp;
										if(s2.getGroup() == s.getGroup())
										{
										  s2.setBounds(name.playerUI.lb.getSettlerCount(s2.getX()+1, s2.getY()), name.playerUI.lb.getArmyCount(s2.getX()+1, s2.getY()));//spaceTotalUnits);
										  for(int i = 0; i < 2; i++)//number of players
									     {
											  if(tempArray[i] == name)
											  {
												  tempArray[i].playerUI.lb.getHolder(s2.getX(), s2.getY()).add(s2.getLabel(), new Integer(5));    
						                    tempArray[i].playerUI.lb.getHolder(s2.getX()+1, s2.getY()).add(s2.getLabel(), new Integer(5));  
						                    tempArray[i].playerUI.lb.subArmyCount(s2.getX(), s2.getY()); 
									           if(((tempArray[i].playerUI.lb.getSettlerCount(s2.getX(), s2.getY()) + tempArray[i].playerUI.lb.getArmyCount(s2.getX(), s2.getY())) == 0) && !tempArray[i].playerUI.lb.getHasBuild(s2.getX(), s2.getY()))      
												    tempArray[i].playerUI.lb.setOwner(s2.getX(), s2.getY(), null);  
						     			        tempArray[i].playerUI.lb.addArmyCount(s2.getX()+1, s2.getY());
//						                    turnClean(s2.getX()+1, s2.getY());
											  }
											  else
											  {
												  JLabel l = new JLabel(s2.getIcon());
												  l.setBounds(s2.getCurrentbounds());
												  l.setName(s2.getLabelName());
						                    tempArray[i].playerUI.lb.getHolder(s2.getX()+1, s2.getY()).add(l, new Integer(5));  
						     			        tempArray[i].playerUI.lb.addArmyCount(s2.getX()+1, s2.getY());
						                    leaveClean(s2.getX(), s2.getY(), s2);
				                          tempArray[i].unitArmyLabels.add(l);
//						                    turnClean(s2.getX()+1, s2.getY());
						                    tempArray[i].playerUI.lb.subArmyCount(s2.getX(), s2.getY()); 
									           if(((tempArray[i].playerUI.lb.getSettlerCount(s2.getX(), s2.getY()) + tempArray[i].playerUI.lb.getArmyCount(s2.getX(), s2.getY())) == 0) && !tempArray[i].playerUI.lb.getHasBuild(s2.getX(), s2.getY()))      
												    tempArray[i].playerUI.lb.setOwner(s2.getX(), s2.getY(), null);  
				                       }
				                    }
							           s2.setPosition(s2.getX()+1, s2.getY());
				                    turnClean(s2.getX()-1, s2.getY());
				                    turnClean(s.getX(), s.getY());
										  s2.takeMove();  
										}
									 }
						        } 
						        break;  
						    case 4:
								  for(int k = 0; k < grp4.size(); k++)
								  {
								    Object temp = grp4.get(k);
									 if(temp.getClass().getName().equals("Settler"))
								    {
									   Settler s2 = (Settler)temp;
										if(s2.getGroup() == s.getGroup() && s2 != s)
										{
										  s2.setBounds(spaceTotalUnits);
										  for(int i = 0; i < 2; i++)//number of players
									     {
											  if(tempArray[i] == name)
											  {
												  tempArray[i].playerUI.lb.getHolder(s2.getX(), s2.getY()).add(s2.getLabel(), new Integer(5));    
						                    tempArray[i].playerUI.lb.getHolder(s2.getX()+1, s2.getY()).add(s2.getLabel(), new Integer(5));  
						                    tempArray[i].playerUI.lb.subSettlerCount(s2.getX(), s2.getY()); 
									           if(((tempArray[i].playerUI.lb.getSettlerCount(s2.getX(), s2.getY()) + tempArray[i].playerUI.lb.getArmyCount(s2.getX(), s2.getY())) == 0) && !tempArray[i].playerUI.lb.getHasBuild(s2.getX(), s2.getY()))      
												    tempArray[i].playerUI.lb.setOwner(s2.getX(), s2.getY(), null);  
						     			        tempArray[i].playerUI.lb.addSettlerCount(s2.getX()+1, s2.getY());
//						                    turnClean(s2.getX()+1, s2.getY());
											  }
											  else
											  {
												  JLabel l = new JLabel(s2.getIcon());
												  l.setBounds(s2.getCurrentbounds());
												  l.setName(s2.getLabelName());
						                    tempArray[i].playerUI.lb.getHolder(s2.getX()+1, s2.getY()).add(l, new Integer(5));  
						     			        tempArray[i].playerUI.lb.addSettlerCount(s2.getX()+1, s2.getY());
						                    leaveClean(s2.getX(), s2.getY(), s2);
				                          tempArray[i].unitSettlerLabels.add(l);
//						                    turnClean(s2.getX()+1, s2.getY());
						                    tempArray[i].playerUI.lb.subSettlerCount(s2.getX(), s2.getY()); 
									           if(((tempArray[i].playerUI.lb.getSettlerCount(s2.getX(), s2.getY()) + tempArray[i].playerUI.lb.getArmyCount(s2.getX(), s2.getY())) == 0) && !tempArray[i].playerUI.lb.getHasBuild(s2.getX(), s2.getY()))      
												    tempArray[i].playerUI.lb.setOwner(s2.getX(), s2.getY(), null);  
				                       }
				                    }
					                 s2.setPosition(s2.getX()+1, s2.getY());
				                    turnClean(s2.getX()-1, s2.getY());
				                    turnClean(s.getX(), s.getY());
										  s2.takeMove();  
										}
								    }  
									 else
									 {
									   Army s2 = (Army)temp;
										if(s2.getGroup() == s.getGroup())
										{
										  s2.setBounds(name.playerUI.lb.getSettlerCount(s2.getX()+1, s2.getY()), name.playerUI.lb.getArmyCount(s2.getX()+1, s2.getY()));//spaceTotalUnits);
										  for(int i = 0; i < 2; i++)//number of players
									     {
											  if(tempArray[i] == name)
											  {
												  tempArray[i].playerUI.lb.getHolder(s2.getX(), s2.getY()).add(s2.getLabel(), new Integer(5));    
						                    tempArray[i].playerUI.lb.getHolder(s2.getX()+1, s2.getY()).add(s2.getLabel(), new Integer(5));  
						                    tempArray[i].playerUI.lb.subArmyCount(s2.getX(), s2.getY()); 
									           if(((tempArray[i].playerUI.lb.getSettlerCount(s2.getX(), s2.getY()) + tempArray[i].playerUI.lb.getArmyCount(s2.getX(), s2.getY())) == 0) && !tempArray[i].playerUI.lb.getHasBuild(s2.getX(), s2.getY()))      
												    tempArray[i].playerUI.lb.setOwner(s2.getX(), s2.getY(), null);  
						     			        tempArray[i].playerUI.lb.addArmyCount(s2.getX()+1, s2.getY());
//						                    turnClean(s2.getX()+1, s2.getY());
											  }
											  else
											  {
												  JLabel l = new JLabel(s2.getIcon());
												  l.setBounds(s2.getCurrentbounds());
												  l.setName(s2.getLabelName());
						                    tempArray[i].playerUI.lb.getHolder(s2.getX()+1, s2.getY()).add(l, new Integer(5));  
						     			        tempArray[i].playerUI.lb.addArmyCount(s2.getX()+1, s2.getY());
						                    leaveClean(s2.getX(), s2.getY(), s2);
				                          tempArray[i].unitArmyLabels.add(l);
//						                    turnClean(s2.getX()+1, s2.getY());
						                    tempArray[i].playerUI.lb.subArmyCount(s2.getX(), s2.getY()); 
									           if(((tempArray[i].playerUI.lb.getSettlerCount(s2.getX(), s2.getY()) + tempArray[i].playerUI.lb.getArmyCount(s2.getX(), s2.getY())) == 0) && !tempArray[i].playerUI.lb.getHasBuild(s2.getX(), s2.getY()))      
												    tempArray[i].playerUI.lb.setOwner(s2.getX(), s2.getY(), null);  
				                       }
				                    }
							           s2.setPosition(s2.getX()+1, s2.getY());
				                    turnClean(s2.getX()-1, s2.getY());
				                    turnClean(s.getX(), s.getY());
										  s2.takeMove();  
										}
									 }
						        } 
						        break;  
							  } 
						  }
				        s.setPosition(s.getX()+1, s.getY());
                    turnClean(s.getX()-1, s.getY());
                    turnClean(s.getX(), s.getY());
						  s.takeMove();  

                    if(name.playerUI.lb.hasHut(s.getX(), s.getY()) > 0)              
						  {
							 String type = name.playerUI.lb.getHutResource(s.getX(), s.getY());
							 name.addResource(type, "hut"); 
						    name.playerUI.tellHutResource(type);   
							 name.playerBoard.setHut(1);
							 for(int k = 0; k < 2; k++)
							 {
							   tempArray[k].playerUI.lb.removeHut(s.getX(), s.getY());
						    }
						    if(name.getNation().equals("China"))
							   name.addCulture(3);  
						    resetSettler(s);
						  }
						  if(s.getMoves() == 0)
			           {
  	 	                resetSettler(s);
					     }
					   }
					 }
				  }				
				}//check Settler move
				else if((a != null) && (a.getX()+1 < 8))
			   {
				  spaceTotalUnits = name.playerUI.lb.getSettlerCount(a.getX()+1, a.getY()) + name.playerUI.lb.getArmyCount(a.getX()+1, a.getY());
				  if(((name.playerUI.lb.getOwner(a.getX()+1, a.getY()) != null) && (name != name.playerUI.lb.getOwner(a.getX()+1, a.getY()))) || (name.playerUI.lb.hasVillage(a.getX()+1, a.getY()) > 0))
				  {
//				    System.out.println("COMBAT");//do combat
					 conductCombat = true;	
				  }
              if((name.playerUI.lb.isCitySpace(a.getX()+1, a.getY())) && (name != name.playerUI.lb.getOwner(a.getX()+1, a.getY())) && (name.getCurrentGov() == 2))
				  {
					   JOptionPane illegal = new JOptionPane();
				      GameImageIcon im = getImage("data/"+name.getNation()+"Icon.png");  
		            illegal.showMessageDialog(name.playerUI.map, "YOU CANNOT ATTACK ENEMY CITY WHEN\n YOU ARE UNDER DEMOCRACY!", "ILLEGAL MOVE",
				  					 			        JOptionPane.INFORMATION_MESSAGE, im);	 	 
				    conductCombat = false;
				    dir = -1;
				  }
				  else if(!conductCombat && ((spaceTotalUnits >= name.getStacklimit()) || (spaceTotalUnits + name.getGrouptotal(a.getGroup()) > name.getStacklimit()) || (name.playerUI.lb.isCitySpace(a.getX()+1, a.getY()) && a.getMoves() < 2)))
				    dir = -1;
				  else
				  {
                if(name.playerUI.lb.getTerrain(a.getX(), a.getY()).equals("Water") && !name.getWaterLand() && (a.getMoves() <= 1) && (name.playerUI.lb.getTerrain(a.getX()+1, a.getY()) == null || name.playerUI.lb.checkExplored(a.getX()+1, a.getY())))
					 {
					   JOptionPane illegal = new JOptionPane();
				      GameImageIcon im = getImage("data/"+name.getNation()+"Icon.png");  
		            illegal.showMessageDialog(name.playerUI.map, "YOU CANNOT LAND IN WATER WITH CURRENT TECHNOLOGY!\nMOVE UNIT TO LAND", "CANNOT END MOVE HERE",
				  					 			        JOptionPane.INFORMATION_MESSAGE, im);	 	 
					 }            
				    else if(name.playerUI.lb.checkExplored(a.getX()+1, a.getY()))
					 {
                  if(name.playerUI.lb.getHolder(a.getX(), a.getY()).getParent().getName().equals("panel1"))
					   {
						   for(int k = 0; k < 2; k++)//number of players
					      {
						     tempArray[k].playerUI.lb.populateTile(name.getPlayerUI().boardPosition[5], 5, 2, tempArray[k]);
							  tempArray[k].playerUI.map.remove(tempArray[k].playerUI.myfog3);
							  tempArray[k].playerUI.map.add(tempArray[k].playerUI.mypanel5, 3);
					        tempArray[k].playerUI.map.validate();
					        tempArray[k].playerUI.map.repaint();
							}
  						}    
		            else if(name.playerUI.lb.getHolder(a.getX(), a.getY()).getParent().getName().equals("panel2"))
					   {
						   for(int k = 0; k < 2; k++)//number of players
					      {
						     tempArray[k].playerUI.lb.populateTile(name.getPlayerUI().boardPosition[6], 6, 2, tempArray[k]);
							  tempArray[k].playerUI.map.remove(tempArray[k].playerUI.myfog5);
							  tempArray[k].playerUI.map.add(tempArray[k].playerUI.mypanel6, 5);
					        tempArray[k].playerUI.map.validate();
					        tempArray[k].playerUI.map.repaint();
							}
  						}    
		            else if(name.playerUI.lb.getHolder(a.getX(), a.getY()).getParent().getName().equals("panel3"))
					   {
						   for(int k = 0; k < 2; k++)//number of players
					      {
						     tempArray[k].playerUI.lb.populateTile(name.getPlayerUI().boardPosition[7], 7, 2, tempArray[k]);
							  tempArray[k].playerUI.map.remove(tempArray[k].playerUI.myfog6);
							  tempArray[k].playerUI.map.add(tempArray[k].playerUI.mypanel7, 7);
					        tempArray[k].playerUI.map.validate();
					        tempArray[k].playerUI.map.repaint();
							}
						}
	   				a.takeMove();  
						if(a.getGroup() > 0)
						{
						  switch(a.getGroup())
						  {
						    case 1:
								  for(int k = 0; k < grp1.size(); k++)
								  {
								    Object temp = grp1.get(k);
									 if(temp.getClass().getName().equals("Settler"))
								    {
									   Settler s2 = (Settler)temp;
										if(s2.getGroup() == a.getGroup())
										{
						   			  s2.takeMove();  
										}
									 }
								    else
									 {
									   Army s2 = (Army)temp;
										if(s2.getGroup() == a.getGroup() && s2 != a)
										{
										  s2.takeMove();
										}
									 }
						        } 
						        break;  
						    case 2:
								  for(int k = 0; k < grp2.size(); k++)
								  {
								    Object temp = grp2.get(k);
									 if(temp.getClass().getName().equals("Settler"))
								    {
									   Settler s2 = (Settler)temp;
										if(s2.getGroup() == a.getGroup())
										{
						   			  s2.takeMove();  
										}
									 }
								    else
									 {
									   Army s2 = (Army)temp;
										if(s2.getGroup() == a.getGroup() && s2 != a)
										{
										  s2.takeMove();
										}
									 }
						        } 
						        break;  
						    case 3:
								  for(int k = 0; k < grp3.size(); k++)
								  {
								    Object temp = grp3.get(k);
									 if(temp.getClass().getName().equals("Settler"))
								    {
									   Settler s2 = (Settler)temp;
										if(s2.getGroup() == a.getGroup())
										{
						   			  s2.takeMove();  
										}
									 }
								    else
									 {
									   Army s2 = (Army)temp;
										if(s2.getGroup() == a.getGroup() && s2 != a)
										{
										  s2.takeMove();
										}
									 }
						        } 
						        break;  
						    case 4:
								  for(int k = 0; k < grp4.size(); k++)
								  {
								    Object temp = grp4.get(k);
									 if(temp.getClass().getName().equals("Settler"))
								    {
									   Settler s2 = (Settler)temp;
										if(s2.getGroup() == a.getGroup())
										{
						   			  s2.takeMove();  
										}
									 }
								    else
									 {
									   Army s2 = (Army)temp;
										if(s2.getGroup() == a.getGroup() && s2 != a)
										{
										  s2.takeMove();
										}
									 }
						        } 
						        break;  
						  }
						}
						if(a.getMoves() == 0)
		            {
                    resetArmy(a);
				      }
					 }
				    else
					 {
                  if(name.playerUI.lb.getTerrain(a.getX()+1, a.getY()).equals("Water") && !name.getWaterCross())    
						{
						  dir = -1;
						}
	               else if(name.playerUI.lb.getTerrain(a.getX()+1, a.getY()).equals("Water") && !name.getWaterLand() && (a.getMoves() < 2)) 
						{
						  dir = -1;
						}          
	               else
						{
						  boolean SettlerOnly = false; 
						  if(name.playerUI.lb.getArmyCount(a.getX()+1, a.getY()) == 0 && conductCombat) 
						  {
							 SettlerOnly = true;
						  }
						  a.setBounds(name.playerUI.lb.getSettlerCount(a.getX()+1, a.getY()), name.playerUI.lb.getArmyCount(a.getX()+1, a.getY()));//spaceTotalUnits);
						  for(int k = 0; k < 2; k++)//number of players
					     {
							  if(tempArray[k] == name)
							  {
								  tempArray[k].playerUI.lb.getHolder(a.getX(), a.getY()).add(a.getLabel(), new Integer(5));    
		                    tempArray[k].playerUI.lb.getHolder(a.getX()+1, a.getY()).add(a.getLabel(), new Integer(5));  
		                    tempArray[k].playerUI.lb.subArmyCount(a.getX(), a.getY()); 
					           if(((tempArray[k].playerUI.lb.getSettlerCount(a.getX(), a.getY()) + tempArray[k].playerUI.lb.getArmyCount(a.getX(), a.getY())) == 0) && !tempArray[k].playerUI.lb.getHasBuild(a.getX(), a.getY()))      
								    tempArray[k].playerUI.lb.setOwner(a.getX(), a.getY(), null);  
		     			        tempArray[k].playerUI.lb.addArmyCount(a.getX()+1, a.getY());
//		                    turnClean(a.getX()-1, a.getY());
							  }
							  else
							  {
								  JLabel l = new JLabel(a.getIcon());
								  l.setBounds(a.getCurrentbounds());
								  l.setName(a.getLabelName());
		                    tempArray[k].playerUI.lb.getHolder(a.getX()+1, a.getY()).add(l, new Integer(5));  
		     			        tempArray[k].playerUI.lb.addArmyCount(a.getX()+1, a.getY());
		                    leaveClean(a.getX(), a.getY(), a);
                          tempArray[k].unitArmyLabels.add(l);
//		                    turnClean(a.getX()+1, a.getY());
		                    tempArray[k].playerUI.lb.subArmyCount(a.getX(), a.getY()); 
					           if(((tempArray[k].playerUI.lb.getSettlerCount(a.getX(), a.getY()) + tempArray[k].playerUI.lb.getArmyCount(a.getX(), a.getY())) == 0) && !tempArray[k].playerUI.lb.getHasBuild(a.getX(), a.getY()))      
								    tempArray[k].playerUI.lb.setOwner(a.getX(), a.getY(), null);  
                       }
                    }

						  if(a.getGroup() > 0)
						  {
						    switch(a.getGroup())
							 {     
								case 1:
								  for(int k = 0; k < grp1.size(); k++)
								  {
								    Object temp = grp1.get(k);
									 if(temp.getClass().getName().equals("Settler"))
								    {
									   Settler s2 = (Settler)temp;
										if(s2.getGroup() == a.getGroup())
										{
										  s2.setBounds(spaceTotalUnits);
										  for(int i = 0; i < 2; i++)//number of players
									     {
											  if(tempArray[i] == name)
											  {
												  tempArray[i].playerUI.lb.getHolder(s2.getX(), s2.getY()).add(s2.getLabel(), new Integer(5));    
						                    tempArray[i].playerUI.lb.getHolder(s2.getX()+1, s2.getY()).add(s2.getLabel(), new Integer(5));  
						                    tempArray[i].playerUI.lb.subSettlerCount(s2.getX(), s2.getY()); 
									           if(((tempArray[i].playerUI.lb.getSettlerCount(s2.getX(), s2.getY()) + tempArray[i].playerUI.lb.getArmyCount(s2.getX(), s2.getY())) == 0) && !tempArray[i].playerUI.lb.getHasBuild(s2.getX(), s2.getY()))      
												    tempArray[i].playerUI.lb.setOwner(s2.getX(), s2.getY(), null);  
						     			        tempArray[i].playerUI.lb.addSettlerCount(s2.getX()+1, s2.getY());
//						                    turnClean(s2.getX()+1, s2.getY());
											  }
											  else
											  {
												  JLabel l = new JLabel(s2.getIcon());
												  l.setBounds(s2.getCurrentbounds());
												  l.setName(s2.getLabelName());
						                    tempArray[i].playerUI.lb.getHolder(s2.getX()+1, s2.getY()).add(l, new Integer(5));  
						     			        tempArray[i].playerUI.lb.addSettlerCount(s2.getX()+1, s2.getY());
						                    leaveClean(s2.getX(), s2.getY(), s2);
				                          tempArray[i].unitSettlerLabels.add(l);
//						                    turnClean(s2.getX()+1, s2.getY());
						                    tempArray[i].playerUI.lb.subSettlerCount(s2.getX(), s2.getY()); 
									           if(((tempArray[i].playerUI.lb.getSettlerCount(s2.getX(), s2.getY()) + tempArray[i].playerUI.lb.getArmyCount(s2.getX(), s2.getY())) == 0) && !tempArray[i].playerUI.lb.getHasBuild(s2.getX(), s2.getY()))      
												    tempArray[i].playerUI.lb.setOwner(s2.getX(), s2.getY(), null);  
				                       }
				                    }
					                 s2.setPosition(s2.getX()+1, s2.getY());
				                    turnClean(s2.getX()-1, s2.getY());
				                    turnClean(a.getX(), a.getY());
										  s2.takeMove();  
										}
								    }
								    else
									 {
									   Army s2 = (Army)temp;
										if(s2.getGroup() == a.getGroup() && s2 != a)
										{
										  s2.setBounds(name.playerUI.lb.getSettlerCount(s2.getX()+1, s2.getY()), name.playerUI.lb.getArmyCount(s2.getX()+1, s2.getY()));//spaceTotalUnits);
										  for(int i = 0; i < 2; i++)//number of players
									     {
											  if(tempArray[i] == name)
											  {
												  tempArray[i].playerUI.lb.getHolder(s2.getX(), s2.getY()).add(s2.getLabel(), new Integer(5));    
						                    tempArray[i].playerUI.lb.getHolder(s2.getX()+1, s2.getY()).add(s2.getLabel(), new Integer(5));  
						                    tempArray[i].playerUI.lb.subArmyCount(s2.getX(), s2.getY()); 
									           if(((tempArray[i].playerUI.lb.getSettlerCount(s2.getX(), s2.getY()) + tempArray[i].playerUI.lb.getArmyCount(s2.getX(), s2.getY())) == 0) && !tempArray[i].playerUI.lb.getHasBuild(s2.getX(), s2.getY()))      
												    tempArray[i].playerUI.lb.setOwner(s2.getX(), s2.getY(), null);  
						     			        tempArray[i].playerUI.lb.addArmyCount(s2.getX()+1, s2.getY());
//						                    turnClean(s2.getX()+1, s2.getY());
											  }
											  else
											  {
												  JLabel l = new JLabel(s2.getIcon());
												  l.setBounds(s2.getCurrentbounds());
												  l.setName(s2.getLabelName());
						                    tempArray[i].playerUI.lb.getHolder(s2.getX()+1, s2.getY()).add(l, new Integer(5));  
						     			        tempArray[i].playerUI.lb.addArmyCount(s2.getX()+1, s2.getY());
						                    leaveClean(s2.getX(), s2.getY(), s2);
				                          tempArray[i].unitArmyLabels.add(l);
//						                    turnClean(s2.getX()+1, s2.getY());
						                    tempArray[i].playerUI.lb.subArmyCount(s2.getX(), s2.getY()); 
									           if(((tempArray[i].playerUI.lb.getSettlerCount(s2.getX(), s2.getY()) + tempArray[i].playerUI.lb.getArmyCount(s2.getX(), s2.getY())) == 0) && !tempArray[i].playerUI.lb.getHasBuild(s2.getX(), s2.getY()))      
												    tempArray[i].playerUI.lb.setOwner(s2.getX(), s2.getY(), null);  
				                       }
				                    }
							           s2.setPosition(s2.getX()+1, s2.getY());
				                    turnClean(s2.getX()-1, s2.getY());
				                    turnClean(a.getX(), a.getY());
										  s2.takeMove();  
										}
									 }
                          }  
						        break;  
						    case 2:
								  for(int k = 0; k < grp2.size(); k++)
								  {
								    Object temp = grp2.get(k);
									 if(temp.getClass().getName().equals("Settler"))
								    {
									   Settler s2 = (Settler)temp;
										if(s2.getGroup() == a.getGroup())
										{
										  s2.setBounds(spaceTotalUnits);
										  for(int i = 0; i < 2; i++)//number of players
									     {
											  if(tempArray[i] == name)
											  {
												  tempArray[i].playerUI.lb.getHolder(s2.getX(), s2.getY()).add(s2.getLabel(), new Integer(5));    
						                    tempArray[i].playerUI.lb.getHolder(s2.getX()+1, s2.getY()).add(s2.getLabel(), new Integer(5));  
						                    tempArray[i].playerUI.lb.subSettlerCount(s2.getX(), s2.getY()); 
									           if(((tempArray[i].playerUI.lb.getSettlerCount(s2.getX(), s2.getY()) + tempArray[i].playerUI.lb.getArmyCount(s2.getX(), s2.getY())) == 0) && !tempArray[i].playerUI.lb.getHasBuild(s2.getX(), s2.getY()))      
												    tempArray[i].playerUI.lb.setOwner(s2.getX(), s2.getY(), null);  
						     			        tempArray[i].playerUI.lb.addSettlerCount(s2.getX()+1, s2.getY());
//						                    turnClean(s2.getX()+1, s2.getY());
											  }
											  else
											  {
												  JLabel l = new JLabel(s2.getIcon());
												  l.setBounds(s2.getCurrentbounds());
												  l.setName(s2.getLabelName());
						                    tempArray[i].playerUI.lb.getHolder(s2.getX()+1, s2.getY()).add(l, new Integer(5));  
						     			        tempArray[i].playerUI.lb.addSettlerCount(s2.getX()+1, s2.getY());
						                    leaveClean(s2.getX(), s2.getY(), s2);
				                          tempArray[i].unitSettlerLabels.add(l);
//						                    turnClean(s2.getX()+1, s2.getY());
						                    tempArray[i].playerUI.lb.subSettlerCount(s2.getX(), s2.getY()); 
									           if(((tempArray[i].playerUI.lb.getSettlerCount(s2.getX(), s2.getY()) + tempArray[i].playerUI.lb.getArmyCount(s2.getX(), s2.getY())) == 0) && !tempArray[i].playerUI.lb.getHasBuild(s2.getX(), s2.getY()))      
												    tempArray[i].playerUI.lb.setOwner(s2.getX(), s2.getY(), null);  
				                       }
				                    }
					                 s2.setPosition(s2.getX()+1, s2.getY());
				                    turnClean(s2.getX()-1, s2.getY());
				                    turnClean(a.getX(), a.getY());
										  s2.takeMove();  
										}
									 }
								    else
									 {
									   Army s2 = (Army)temp;
										if(s2.getGroup() == a.getGroup() && s2 != a)
										{
										  s2.setBounds(name.playerUI.lb.getSettlerCount(s2.getX()+1, s2.getY()), name.playerUI.lb.getArmyCount(s2.getX()+1, s2.getY()));//spaceTotalUnits);
										  for(int i = 0; i < 2; i++)//number of players
									     {
											  if(tempArray[i] == name)
											  {
												  tempArray[i].playerUI.lb.getHolder(s2.getX(), s2.getY()).add(s2.getLabel(), new Integer(5));    
						                    tempArray[i].playerUI.lb.getHolder(s2.getX()+1, s2.getY()).add(s2.getLabel(), new Integer(5));  
						                    tempArray[i].playerUI.lb.subArmyCount(s2.getX(), s2.getY()); 
									           if(((tempArray[i].playerUI.lb.getSettlerCount(s2.getX(), s2.getY()) + tempArray[i].playerUI.lb.getArmyCount(s2.getX(), s2.getY())) == 0) && !tempArray[i].playerUI.lb.getHasBuild(s2.getX(), s2.getY()))      
												    tempArray[i].playerUI.lb.setOwner(s2.getX(), s2.getY(), null);  
						     			        tempArray[i].playerUI.lb.addArmyCount(s2.getX()+1, s2.getY());
//						                    turnClean(s2.getX()+1, s2.getY());
											  }
											  else
											  {
												  JLabel l = new JLabel(s2.getIcon());
												  l.setBounds(s2.getCurrentbounds());
												  l.setName(s2.getLabelName());
						                    tempArray[i].playerUI.lb.getHolder(s2.getX()+1, s2.getY()).add(l, new Integer(5));  
						     			        tempArray[i].playerUI.lb.addArmyCount(s2.getX()+1, s2.getY());
						                    leaveClean(s2.getX(), s2.getY(), s2);
				                          tempArray[i].unitArmyLabels.add(l);
//						                    turnClean(s2.getX()+1, s2.getY());
						                    tempArray[i].playerUI.lb.subArmyCount(s2.getX(), s2.getY()); 
									           if(((tempArray[i].playerUI.lb.getSettlerCount(s2.getX(), s2.getY()) + tempArray[i].playerUI.lb.getArmyCount(s2.getX(), s2.getY())) == 0) && !tempArray[i].playerUI.lb.getHasBuild(s2.getX(), s2.getY()))      
												    tempArray[i].playerUI.lb.setOwner(s2.getX(), s2.getY(), null);  
				                       }
				                    }
							           s2.setPosition(s2.getX()+1, s2.getY());
				                    turnClean(s2.getX()-1, s2.getY());
				                    turnClean(a.getX(), a.getY());
										  s2.takeMove();  
										}
									 }
						        } 
						        break;  
						    case 3:
								  for(int k = 0; k < grp3.size(); k++)
								  {
								    Object temp = grp3.get(k);
									 if(temp.getClass().getName().equals("Settler"))
								    {
									   Settler s2 = (Settler)temp;
										if(s2.getGroup() == a.getGroup())
										{
										  s2.setBounds(spaceTotalUnits);
										  for(int i = 0; i < 2; i++)//number of players
									     {
											  if(tempArray[i] == name)
											  {
												  tempArray[i].playerUI.lb.getHolder(s2.getX(), s2.getY()).add(s2.getLabel(), new Integer(5));    
						                    tempArray[i].playerUI.lb.getHolder(s2.getX()+1, s2.getY()).add(s2.getLabel(), new Integer(5));  
						                    tempArray[i].playerUI.lb.subSettlerCount(s2.getX(), s2.getY()); 
									           if(((tempArray[i].playerUI.lb.getSettlerCount(s2.getX(), s2.getY()) + tempArray[i].playerUI.lb.getArmyCount(s2.getX(), s2.getY())) == 0) && !tempArray[i].playerUI.lb.getHasBuild(s2.getX(), s2.getY()))      
												    tempArray[i].playerUI.lb.setOwner(s2.getX(), s2.getY(), null);  
						     			        tempArray[i].playerUI.lb.addSettlerCount(s2.getX()+1, s2.getY());
//						                    turnClean(s2.getX()+1, s2.getY());
											  }
											  else
											  {
												  JLabel l = new JLabel(s2.getIcon());
												  l.setBounds(s2.getCurrentbounds());
												  l.setName(s2.getLabelName());
						                    tempArray[i].playerUI.lb.getHolder(s2.getX()+1, s2.getY()).add(l, new Integer(5));  
						     			        tempArray[i].playerUI.lb.addSettlerCount(s2.getX()+1, s2.getY());
						                    leaveClean(s2.getX(), s2.getY(), s2);
				                          tempArray[i].unitSettlerLabels.add(l);
//						                    turnClean(s2.getX()+1, s2.getY());
						                    tempArray[i].playerUI.lb.subSettlerCount(s2.getX(), s2.getY()); 
									           if(((tempArray[i].playerUI.lb.getSettlerCount(s2.getX(), s2.getY()) + tempArray[i].playerUI.lb.getArmyCount(s2.getX(), s2.getY())) == 0) && !tempArray[i].playerUI.lb.getHasBuild(s2.getX(), s2.getY()))      
												    tempArray[i].playerUI.lb.setOwner(s2.getX(), s2.getY(), null);  
				                       }
				                    }
					                 s2.setPosition(s2.getX()+1, s2.getY());
				                    turnClean(s2.getX()-1, s2.getY());
				                    turnClean(a.getX(), a.getY());
										  s2.takeMove();  
										}
									 }
								    else
									 {
									   Army s2 = (Army)temp;
										if(s2.getGroup() == a.getGroup() && s2 != a)
										{
										  s2.setBounds(name.playerUI.lb.getSettlerCount(s2.getX()+1, s2.getY()), name.playerUI.lb.getArmyCount(s2.getX()+1, s2.getY()));//spaceTotalUnits);
										  for(int i = 0; i < 2; i++)//number of players
									     {
											  if(tempArray[i] == name)
											  {
												  tempArray[i].playerUI.lb.getHolder(s2.getX(), s2.getY()).add(s2.getLabel(), new Integer(5));    
						                    tempArray[i].playerUI.lb.getHolder(s2.getX()+1, s2.getY()).add(s2.getLabel(), new Integer(5));  
						                    tempArray[i].playerUI.lb.subArmyCount(s2.getX(), s2.getY()); 
									           if(((tempArray[i].playerUI.lb.getSettlerCount(s2.getX(), s2.getY()) + tempArray[i].playerUI.lb.getArmyCount(s2.getX(), s2.getY())) == 0) && !tempArray[i].playerUI.lb.getHasBuild(s2.getX(), s2.getY()))      
												    tempArray[i].playerUI.lb.setOwner(s2.getX(), s2.getY(), null);  
						     			        tempArray[i].playerUI.lb.addArmyCount(s2.getX()+1, s2.getY());
//						                    turnClean(s2.getX()+1, s2.getY());
											  }
											  else
											  {
												  JLabel l = new JLabel(s2.getIcon());
												  l.setBounds(s2.getCurrentbounds());
												  l.setName(s2.getLabelName());
						                    tempArray[i].playerUI.lb.getHolder(s2.getX()+1, s2.getY()).add(l, new Integer(5));  
						     			        tempArray[i].playerUI.lb.addArmyCount(s2.getX()+1, s2.getY());
						                    leaveClean(s2.getX(), s2.getY(), s2);
				                          tempArray[i].unitArmyLabels.add(l);
//						                    turnClean(s2.getX()+1, s2.getY());
						                    tempArray[i].playerUI.lb.subArmyCount(s2.getX(), s2.getY()); 
									           if(((tempArray[i].playerUI.lb.getSettlerCount(s2.getX(), s2.getY()) + tempArray[i].playerUI.lb.getArmyCount(s2.getX(), s2.getY())) == 0) && !tempArray[i].playerUI.lb.getHasBuild(s2.getX(), s2.getY()))      
												    tempArray[i].playerUI.lb.setOwner(s2.getX(), s2.getY(), null);  
				                       }
				                    }
							           s2.setPosition(s2.getX()+1, s2.getY());
				                    turnClean(s2.getX()-1, s2.getY());
				                    turnClean(a.getX(), a.getY());
										  s2.takeMove();  
										}
									 }
						        } 
						        break;  
						    case 4:
								  for(int k = 0; k < grp4.size(); k++)
								  {
								    Object temp = grp4.get(k);
									 if(temp.getClass().getName().equals("Settler"))
								    {
									   Settler s2 = (Settler)temp;
										if(s2.getGroup() == a.getGroup())
										{
										  s2.setBounds(spaceTotalUnits);
										  for(int i = 0; i < 2; i++)//number of players
									     {
											  if(tempArray[i] == name)
											  {
												  tempArray[i].playerUI.lb.getHolder(s2.getX(), s2.getY()).add(s2.getLabel(), new Integer(5));    
						                    tempArray[i].playerUI.lb.getHolder(s2.getX()+1, s2.getY()).add(s2.getLabel(), new Integer(5));  
						                    tempArray[i].playerUI.lb.subSettlerCount(s2.getX(), s2.getY()); 
									           if(((tempArray[i].playerUI.lb.getSettlerCount(s2.getX(), s2.getY()) + tempArray[i].playerUI.lb.getArmyCount(s2.getX(), s2.getY())) == 0) && !tempArray[i].playerUI.lb.getHasBuild(s2.getX(), s2.getY()))      
												    tempArray[i].playerUI.lb.setOwner(s2.getX(), s2.getY(), null);  
						     			        tempArray[i].playerUI.lb.addSettlerCount(s2.getX()+1, s2.getY());
//						                    turnClean(s2.getX()+1, s2.getY());
											  }
											  else
											  {
												  JLabel l = new JLabel(s2.getIcon());
												  l.setBounds(s2.getCurrentbounds());
												  l.setName(s2.getLabelName());
						                    tempArray[i].playerUI.lb.getHolder(s2.getX()+1, s2.getY()).add(l, new Integer(5));  
						     			        tempArray[i].playerUI.lb.addSettlerCount(s2.getX()+1, s2.getY());
						                    leaveClean(s2.getX(), s2.getY(), s2);
				                          tempArray[i].unitSettlerLabels.add(l);
//						                    turnClean(s2.getX()+1, s2.getY());
						                    tempArray[i].playerUI.lb.subSettlerCount(s2.getX(), s2.getY()); 
									           if(((tempArray[i].playerUI.lb.getSettlerCount(s2.getX(), s2.getY()) + tempArray[i].playerUI.lb.getArmyCount(s2.getX(), s2.getY())) == 0) && !tempArray[i].playerUI.lb.getHasBuild(s2.getX(), s2.getY()))      
												    tempArray[i].playerUI.lb.setOwner(s2.getX(), s2.getY(), null);  
				                       }
				                    }
					                 s2.setPosition(s2.getX()+1, s2.getY());
				                    turnClean(s2.getX()-1, s2.getY());
				                    turnClean(a.getX(), a.getY());
										  s2.takeMove();  
										}
									 }
								    else
									 {
									   Army s2 = (Army)temp;
										if(s2.getGroup() == a.getGroup() && s2 != a)
										{
										  s2.setBounds(name.playerUI.lb.getSettlerCount(s2.getX()+1, s2.getY()), name.playerUI.lb.getArmyCount(s2.getX()+1, s2.getY()));//spaceTotalUnits);
										  for(int i = 0; i < 2; i++)//number of players
									     {
											  if(tempArray[i] == name)
											  {
												  tempArray[i].playerUI.lb.getHolder(s2.getX(), s2.getY()).add(s2.getLabel(), new Integer(5));    
						                    tempArray[i].playerUI.lb.getHolder(s2.getX()+1, s2.getY()).add(s2.getLabel(), new Integer(5));  
						                    tempArray[i].playerUI.lb.subArmyCount(s2.getX(), s2.getY()); 
									           if(((tempArray[i].playerUI.lb.getSettlerCount(s2.getX(), s2.getY()) + tempArray[i].playerUI.lb.getArmyCount(s2.getX(), s2.getY())) == 0) && !tempArray[i].playerUI.lb.getHasBuild(s2.getX(), s2.getY()))      
												    tempArray[i].playerUI.lb.setOwner(s2.getX(), s2.getY(), null);  
						     			        tempArray[i].playerUI.lb.addArmyCount(s2.getX()+1, s2.getY());
//						                    turnClean(s2.getX()+1, s2.getY());
											  }
											  else
											  {
												  JLabel l = new JLabel(s2.getIcon());
												  l.setBounds(s2.getCurrentbounds());
												  l.setName(s2.getLabelName());
						                    tempArray[i].playerUI.lb.getHolder(s2.getX()+1, s2.getY()).add(l, new Integer(5));  
						     			        tempArray[i].playerUI.lb.addArmyCount(s2.getX()+1, s2.getY());
						                    leaveClean(s2.getX(), s2.getY(), s2);
				                          tempArray[i].unitArmyLabels.add(l);
//						                    turnClean(s2.getX()+1, s2.getY());
						                    tempArray[i].playerUI.lb.subArmyCount(s2.getX(), s2.getY()); 
									           if(((tempArray[i].playerUI.lb.getSettlerCount(s2.getX(), s2.getY()) + tempArray[i].playerUI.lb.getArmyCount(s2.getX(), s2.getY())) == 0) && !tempArray[i].playerUI.lb.getHasBuild(s2.getX(), s2.getY()))      
												    tempArray[i].playerUI.lb.setOwner(s2.getX(), s2.getY(), null);  
				                       }
				                    }
							           s2.setPosition(s2.getX()+1, s2.getY());
				                    turnClean(s2.getX()-1, s2.getY());
				                    turnClean(a.getX(), a.getY());
										  s2.takeMove();  
										}
									 }
						        } 
						        break;  
                       }
						  }
 			           a.setPosition(a.getX()+1, a.getY());
                    turnClean(a.getX()-1, a.getY());
                    turnClean(a.getX(), a.getY());
	   				  a.takeMove();  

                    if(name.playerUI.lb.hasHut(a.getX(), a.getY()) > 0)              
						  {
							 String type = name.playerUI.lb.getHutResource(a.getX(), a.getY());
							 name.addResource(type, "hut"); 
						    name.playerUI.tellHutResource(type);   
							 name.playerBoard.setHut(1);
							 for(int k = 0; k < 2; k++)
							 {
							   tempArray[k].playerUI.lb.removeHut(a.getX(), a.getY());
						    }
						    if(name.getNation().equals("China"))
							   name.addCulture(3);  
						    resetArmy(a);
						  }
						  if(a.getMoves() == 0 && !conductCombat)
		              {
                      resetArmy(a);
				        }
                    if(conductCombat)
						  {
                      combatPause();  
					       if(name.playerUI.lb.hasVillage(a.getX(), a.getY()) > 0)
							 {
						      combatUnit = a;
							   combat = new Combat(new Player("Village"), name, this);
						      a.combatMoves();
							 }    
                      else if(SettlerOnly)
							 {
						      Player loser = name.playerUI.lb.getOwner(a.getX(), a.getY());
								Vector sl = loser.getSettlerVector();
								int k;
								for(k = 0; k < sl.size(); k++)
								{
						        Settler temp = (Settler) sl.get(k); 
								  if((temp.getX() == a.getX()) && (temp.getY() == a.getY()))
						        {
								    JLayeredPane lp = name.playerUI.lb.getHolder(a.getX(), a.getY());    
								    loser.removeSettler(temp);
								    name.playerUI.lb.subSettlerCount(a.getX(), a.getY());  
									 name.playerUI.lb.getHolder(a.getX(), a.getY()).remove(temp.getLabel());
									 name.playerUI.lb.getHolder(a.getX(), a.getY()).validate();
									 name.playerUI.lb.getHolder(a.getX(), a.getY()).repaint();
						  	 	  }
								} 
							   combat = new Combat(name.playerUI.lb.getOwner(a.getX(), a.getY()), name, true);
// 			     		      for(int i = 0; i < movePeices.size(); i++)
//						  		  nm[i].addActionListener(this);  
						      done.addActionListener(this);  
						      lt.addActionListener(this);  
							   rt.addActionListener(this);  
							   up.addActionListener(this);  
							   dw.addActionListener(this);  
							   resetArmy(a);
							 } 
							 else
							 {
						      combatUnit = a;
							   combat = new Combat(name.playerUI.lb.getOwner(a.getX(), a.getY()), name, this);
						      a.combatMoves();
						    }
						  } 
						}
					 }
				  }			   
				}//check if Army  
		    }    
		    else if(direction == 3)  
		    {
            Player[] tempArray = name.getPlayerUI().getPlayers();
			   if((s != null) && (s.getY()+1 < 16))
			   {
				  spaceTotalUnits = name.playerUI.lb.getSettlerCount(s.getX(), s.getY()+1) + name.playerUI.lb.getArmyCount(s.getX(), s.getY()+1);
				  if((name.playerUI.lb.getOwner(s.getX(), s.getY()+1) != null) && (name != name.playerUI.lb.getOwner(s.getX(), s.getY()+1)))
				  {
//				    System.out.println("COMBAT");//do combat
					 conductCombat = true;
				  }
				  if(conductCombat || ((spaceTotalUnits >= name.getStacklimit()) || (spaceTotalUnits + name.getGrouptotal(s.getGroup()) > name.getStacklimit())  || (name.playerUI.lb.isCitySpace(s.getX(), s.getY()+1) && s.getMoves() < 2)))
				    dir = -1;
				  else
				  {
                if(name.playerUI.lb.getTerrain(s.getX(), s.getY()).equals("Water") && !name.getWaterLand() && (s.getMoves() <= 1) && (name.playerUI.lb.getTerrain(s.getX(), s.getY()+1) == null || name.playerUI.lb.checkExplored(s.getX(), s.getY()+1)))
					 {
					   JOptionPane illegal = new JOptionPane();
				      GameImageIcon im = getImage("data/"+name.getNation()+"Icon.png");  
		            illegal.showMessageDialog(name.playerUI.map, "YOU CANNOT LAND IN WATER WITH CURRENT TECHNOLOGY!\nMOVE UNIT TO LAND", "CANNOT END MOVE HERE",
				  					 			        JOptionPane.INFORMATION_MESSAGE, im);	 	 
					 }            
				    else if(name.playerUI.lb.checkExplored(s.getX(), s.getY()+1))
					 {
                  if(name.playerUI.lb.getHolder(s.getX(), s.getY()).getParent().getName().equals("panel0"))
					   {
						   for(int k = 0; k < 2; k++)//number of players
					      {
						     tempArray[k].playerUI.lb.populateTile(name.getPlayerUI().boardPosition[1], 1, 3, tempArray[k]);
							  tempArray[k].playerUI.map.remove(tempArray[k].playerUI.myfog2);
							  tempArray[k].playerUI.map.add(tempArray[k].playerUI.mypanel1, 2);
					        tempArray[k].playerUI.map.validate();
					        tempArray[k].playerUI.map.repaint();
							}
  						}    
		            else if(name.playerUI.lb.getHolder(s.getX(), s.getY()).getParent().getName().equals("panel1"))
					   {
						   for(int k = 0; k < 2; k++)//number of players
					      {
						     tempArray[k].playerUI.lb.populateTile(name.getPlayerUI().boardPosition[2], 2, 3, tempArray[k]);
							  tempArray[k].playerUI.map.remove(tempArray[k].playerUI.myfog4);
							  tempArray[k].playerUI.map.add(tempArray[k].playerUI.mypanel2, 4);
					        tempArray[k].playerUI.map.validate();
					        tempArray[k].playerUI.map.repaint();
							}
  						}    
		            else if(name.playerUI.lb.getHolder(s.getX(), s.getY()).getParent().getName().equals("panel4"))
					   {
						   for(int k = 0; k < 2; k++)//number of players
					      {
						     tempArray[k].playerUI.lb.populateTile(name.getPlayerUI().boardPosition[5], 5, 3, tempArray[k]);
							  tempArray[k].playerUI.map.remove(tempArray[k].playerUI.myfog3);
							  tempArray[k].playerUI.map.add(tempArray[k].playerUI.mypanel5, 3);
					        tempArray[k].playerUI.map.validate();
					        tempArray[k].playerUI.map.repaint();
							}
						}
		            else if(name.playerUI.lb.getHolder(s.getX(), s.getY()).getParent().getName().equals("panel5"))
					   {
						   for(int k = 0; k < 2; k++)//number of players
					      {
						     tempArray[k].playerUI.lb.populateTile(name.getPlayerUI().boardPosition[6], 6, 3, tempArray[k]);
							  tempArray[k].playerUI.map.remove(tempArray[k].playerUI.myfog5);
							  tempArray[k].playerUI.map.add(tempArray[k].playerUI.mypanel6, 5);
					        tempArray[k].playerUI.map.validate();
					        tempArray[k].playerUI.map.repaint();
							}
						}
		            else if(name.playerUI.lb.getHolder(s.getX(), s.getY()).getParent().getName().equals("panel6"))
					   {
						   for(int k = 0; k < 2; k++)//number of players
					      {
						     tempArray[k].playerUI.lb.populateTile(name.getPlayerUI().boardPosition[7], 7, 3, tempArray[k]);
							  tempArray[k].playerUI.map.remove(tempArray[k].playerUI.myfog6);
							  tempArray[k].playerUI.map.add(tempArray[k].playerUI.mypanel7, 7);
					        tempArray[k].playerUI.map.validate();
					        tempArray[k].playerUI.map.repaint();
							}
						}
	   				s.takeMove();  
						if(s.getGroup() > 0)
						{
						  switch(s.getGroup())
						  {
						    case 1:
								  for(int k = 0; k < grp1.size(); k++)
								  {
								    Object temp = grp1.get(k);
									 if(temp.getClass().getName().equals("Settler"))
								    {
									   Settler s2 = (Settler)temp;
										if(s2.getGroup() == s.getGroup() && s2 != s)
										{
						   			  s2.takeMove();  
										}
									 }
								    else
									 {
									   Army s2 = (Army)temp;
										if(s2.getGroup() == s.getGroup())
										{
										  s2.takeMove();
										}
									 }
						        } 
						        break;  
						    case 2:
								  for(int k = 0; k < grp2.size(); k++)
								  {
								    Object temp = grp2.get(k);
									 if(temp.getClass().getName().equals("Settler"))
								    {
									   Settler s2 = (Settler)temp;
										if(s2.getGroup() == s.getGroup() && s2 != s)
										{
						   			  s2.takeMove();  
										}
									 }
								    else
									 {
									   Army s2 = (Army)temp;
										if(s2.getGroup() == s.getGroup())
										{
										  s2.takeMove();
										}
									 }
						        } 
						        break;  
						    case 3:
								  for(int k = 0; k < grp3.size(); k++)
								  {
								    Object temp = grp3.get(k);
									 if(temp.getClass().getName().equals("Settler"))
								    {
									   Settler s2 = (Settler)temp;
										if(s2.getGroup() == s.getGroup() && s2 != s)
										{
						   			  s2.takeMove();  
										}
									 }
								    else
									 {
									   Army s2 = (Army)temp;
										if(s2.getGroup() == s.getGroup())
										{
										  s2.takeMove();
										}
									 }
						        } 
						        break;  
						    case 4:
								  for(int k = 0; k < grp4.size(); k++)
								  {
								    Object temp = grp4.get(k);
									 if(temp.getClass().getName().equals("Settler"))
								    {
									   Settler s2 = (Settler)temp;
										if(s2.getGroup() == s.getGroup() && s2 != s)
										{
						   			  s2.takeMove();  
										}
									 }
								    else
									 {
									   Army s2 = (Army)temp;
										if(s2.getGroup() == s.getGroup())
										{
										  s2.takeMove();
										}
									 }
						        } 
						        break;  
							 }
						}
						if(s.getMoves() == 0)
		            {
                    resetSettler(s);
				      }
					 }
				    else
					 {
	               if((name.playerUI.lb.hasHut(s.getX(), s.getY()+1) > 0) && name.getCurrentGov() != 1)
						{
						  dir = -1;
						}           
						else if(name.playerUI.lb.hasVillage(s.getX(), s.getY()+1) > 0)
						{
						  dir = -1;
						}
                  else if(name.playerUI.lb.getTerrain(s.getX(), s.getY()+1).equals("Water") && !name.getWaterCross())    
						{
						  dir = -1;
						}
	               else if(name.playerUI.lb.getTerrain(s.getX(), s.getY()+1).equals("Water") && !name.getWaterLand() && (s.getMoves() < 2)) 
						{
						  dir = -1;
						}          
					   else
						{  
						  s.setBounds(spaceTotalUnits);
						  for(int k = 0; k < 2; k++)//number of players
					     {
							  if(tempArray[k] == name)
							  {
								  tempArray[k].playerUI.lb.getHolder(s.getX(), s.getY()).add(s.getLabel(), new Integer(5));    
		                    tempArray[k].playerUI.lb.getHolder(s.getX(), s.getY()+1).add(s.getLabel(), new Integer(5));  
		                    tempArray[k].playerUI.lb.subSettlerCount(s.getX(), s.getY()); 
					           if(((tempArray[k].playerUI.lb.getSettlerCount(s.getX(), s.getY()) + tempArray[k].playerUI.lb.getArmyCount(s.getX(), s.getY())) == 0) && !tempArray[k].playerUI.lb.getHasBuild(s.getX(), s.getY()))      
								    tempArray[k].playerUI.lb.setOwner(s.getX(), s.getY(), null);  
		     			        tempArray[k].playerUI.lb.addSettlerCount(s.getX(), s.getY()+1);
//		                    turnClean(s.getX(), s.getY()+1);
							  }
							  else
							  {
								  JLabel l = new JLabel(s.getIcon());
								  l.setBounds(s.getCurrentbounds());
								  l.setName(s.getLabelName());
		                    tempArray[k].playerUI.lb.getHolder(s.getX(), s.getY()+1).add(l, new Integer(5));  
		     			        tempArray[k].playerUI.lb.addSettlerCount(s.getX(), s.getY()+1);
		                    leaveClean(s.getX(), s.getY(), s);
                          tempArray[k].unitSettlerLabels.add(l);
//		                    turnClean(s.getX(), s.getY()+1);
		                    tempArray[k].playerUI.lb.subSettlerCount(s.getX(), s.getY()); 
					           if(((tempArray[k].playerUI.lb.getSettlerCount(s.getX(), s.getY()) + tempArray[k].playerUI.lb.getArmyCount(s.getX(), s.getY())) == 0) && !tempArray[k].playerUI.lb.getHasBuild(s.getX(), s.getY()))      
								    tempArray[k].playerUI.lb.setOwner(s.getX(), s.getY(), null);  
                       }
 			           }   

						  if(s.getGroup() > 0)
						  {
						    switch(s.getGroup())
						    {
						      case 1:
								  for(int k = 0; k < grp1.size(); k++)
								  {
								    Object temp = grp1.get(k);
									 if(temp.getClass().getName().equals("Settler"))
								    {
									   Settler s2 = (Settler)temp;
										if(s2.getGroup() == s.getGroup() && s2 != s)
										{
										  s2.setBounds(spaceTotalUnits);
										  for(int i = 0; i < 2; i++)//number of players
									     {
											  if(tempArray[i] == name)
											  {
												  tempArray[i].playerUI.lb.getHolder(s2.getX(), s2.getY()).add(s2.getLabel(), new Integer(5));    
						                    tempArray[i].playerUI.lb.getHolder(s2.getX(), s2.getY()+1).add(s2.getLabel(), new Integer(5));  
						                    tempArray[i].playerUI.lb.subSettlerCount(s2.getX(), s2.getY()); 
									           if(((tempArray[i].playerUI.lb.getSettlerCount(s2.getX(), s2.getY()) + tempArray[i].playerUI.lb.getArmyCount(s2.getX(), s2.getY())) == 0) && !tempArray[i].playerUI.lb.getHasBuild(s2.getX(), s2.getY()))      
												    tempArray[i].playerUI.lb.setOwner(s2.getX(), s2.getY(), null);  
						     			        tempArray[i].playerUI.lb.addSettlerCount(s2.getX(), s2.getY()+1);
//						                    turnClean(s2.getX(), s2.getY()+1);
											  }
											  else
											  {
												  JLabel l = new JLabel(s2.getIcon());
												  l.setBounds(s2.getCurrentbounds());
												  l.setName(s2.getLabelName());
						                    tempArray[i].playerUI.lb.getHolder(s2.getX(), s2.getY()+1).add(l, new Integer(5));  
						     			        tempArray[i].playerUI.lb.addSettlerCount(s2.getX(), s2.getY()+1);
						                    leaveClean(s2.getX(), s2.getY(), s2);
				                          tempArray[i].unitSettlerLabels.add(l);
//						                    turnClean(s2.getX(), s2.getY()+1);
						                    tempArray[i].playerUI.lb.subSettlerCount(s2.getX(), s2.getY()); 
									           if(((tempArray[i].playerUI.lb.getSettlerCount(s2.getX(), s2.getY()) + tempArray[i].playerUI.lb.getArmyCount(s2.getX(), s2.getY())) == 0) && !tempArray[i].playerUI.lb.getHasBuild(s2.getX(), s2.getY()))      
												    tempArray[i].playerUI.lb.setOwner(s2.getX(), s2.getY(), null);  
				                       }
				                    }
					                 s2.setPosition(s2.getX(), s2.getY()+1);
				                    turnClean(s2.getX(), s2.getY()-1);
				                    turnClean(s.getX(), s.getY());
										  s2.takeMove();  
										}
								    }
								    else
									 {
									   Army s2 = (Army)temp;
										if(s2.getGroup() == s.getGroup())
										{
										  s2.setBounds(name.playerUI.lb.getSettlerCount(s2.getX(), s2.getY()+1), name.playerUI.lb.getArmyCount(s2.getX(), s2.getY()+1));//spaceTotalUnits);
										  for(int i = 0; i < 2; i++)//number of players
									     {
											  if(tempArray[i] == name)
											  {
												  tempArray[i].playerUI.lb.getHolder(s2.getX(), s2.getY()).add(s2.getLabel(), new Integer(5));    
						                    tempArray[i].playerUI.lb.getHolder(s2.getX(), s2.getY()+1).add(s2.getLabel(), new Integer(5));  
						                    tempArray[i].playerUI.lb.subArmyCount(s2.getX(), s2.getY()); 
									           if(((tempArray[i].playerUI.lb.getSettlerCount(s2.getX(), s2.getY()) + tempArray[i].playerUI.lb.getArmyCount(s2.getX(), s2.getY())) == 0) && !tempArray[i].playerUI.lb.getHasBuild(s2.getX(), s2.getY()))      
												    tempArray[i].playerUI.lb.setOwner(s2.getX(), s2.getY(), null);  
						     			        tempArray[i].playerUI.lb.addArmyCount(s2.getX(), s2.getY()+1);
//						                    turnClean(s2.getX(), s2.getY()+1);
											  }
											  else
											  {
												  JLabel l = new JLabel(s2.getIcon());
												  l.setBounds(s2.getCurrentbounds());
												  l.setName(s2.getLabelName());
						                    tempArray[i].playerUI.lb.getHolder(s2.getX(), s2.getY()+1).add(l, new Integer(5));  
						     			        tempArray[i].playerUI.lb.addArmyCount(s2.getX(), s2.getY()+1);
						                    leaveClean(s2.getX(), s2.getY(), s2);
				                          tempArray[i].unitArmyLabels.add(l);
//						                    turnClean(s2.getX(), s2.getY()+1);
						                    tempArray[i].playerUI.lb.subArmyCount(s2.getX(), s2.getY()); 
									           if(((tempArray[i].playerUI.lb.getSettlerCount(s2.getX(), s2.getY()) + tempArray[i].playerUI.lb.getArmyCount(s2.getX(), s2.getY())) == 0) && !tempArray[i].playerUI.lb.getHasBuild(s2.getX(), s2.getY()))      
												    tempArray[i].playerUI.lb.setOwner(s2.getX(), s2.getY(), null);  
				                       }
				                    }
							           s2.setPosition(s2.getX(), s2.getY()+1);
				                    turnClean(s2.getX(), s2.getY()-1);
				                    turnClean(s.getX(), s.getY());
										  s2.takeMove();  
										}
									 }
						        } 
						        break;  
						    case 2:
								  for(int k = 0; k < grp2.size(); k++)
								  {
								    Object temp = grp2.get(k);
									 if(temp.getClass().getName().equals("Settler"))
								    {
									   Settler s2 = (Settler)temp;
										if(s2.getGroup() == s.getGroup() && s2 != s)
										{
										  s2.setBounds(spaceTotalUnits);
										  for(int i = 0; i < 2; i++)//number of players
									     {
											  if(tempArray[i] == name)
											  {
												  tempArray[i].playerUI.lb.getHolder(s2.getX(), s2.getY()).add(s2.getLabel(), new Integer(5));    
						                    tempArray[i].playerUI.lb.getHolder(s2.getX(), s2.getY()+1).add(s2.getLabel(), new Integer(5));  
						                    tempArray[i].playerUI.lb.subSettlerCount(s2.getX(), s2.getY()); 
									           if(((tempArray[i].playerUI.lb.getSettlerCount(s2.getX(), s2.getY()) + tempArray[i].playerUI.lb.getArmyCount(s2.getX(), s2.getY())) == 0) && !tempArray[i].playerUI.lb.getHasBuild(s2.getX(), s2.getY()))      
												    tempArray[i].playerUI.lb.setOwner(s2.getX(), s2.getY(), null);  
						     			        tempArray[i].playerUI.lb.addSettlerCount(s2.getX(), s2.getY()+1);
//						                    turnClean(s2.getX(), s2.getY()+1);
											  }
											  else
											  {
												  JLabel l = new JLabel(s2.getIcon());
												  l.setBounds(s2.getCurrentbounds());
												  l.setName(s2.getLabelName());
						                    tempArray[i].playerUI.lb.getHolder(s2.getX(), s2.getY()+1).add(l, new Integer(5));  
						     			        tempArray[i].playerUI.lb.addSettlerCount(s2.getX(), s2.getY()+1);
						                    leaveClean(s2.getX(), s2.getY(), s2);
				                          tempArray[i].unitSettlerLabels.add(l);
//						                    turnClean(s2.getX(), s2.getY()+1);
						                    tempArray[i].playerUI.lb.subSettlerCount(s2.getX(), s2.getY()); 
									           if(((tempArray[i].playerUI.lb.getSettlerCount(s2.getX(), s2.getY()) + tempArray[i].playerUI.lb.getArmyCount(s2.getX(), s2.getY())) == 0) && !tempArray[i].playerUI.lb.getHasBuild(s2.getX(), s2.getY()))      
												    tempArray[i].playerUI.lb.setOwner(s2.getX(), s2.getY(), null);  
				                       }
				                    }
					                 s2.setPosition(s2.getX(), s2.getY()+1);
				                    turnClean(s2.getX(), s2.getY()-1);
				                    turnClean(s.getX(), s.getY());
										  s2.takeMove();  
										}
								    }  
									 else
									 {
									   Army s2 = (Army)temp;
										if(s2.getGroup() == s.getGroup())
										{
										  s2.setBounds(name.playerUI.lb.getSettlerCount(s2.getX(), s2.getY()+1), name.playerUI.lb.getArmyCount(s2.getX(), s2.getY()+1));//spaceTotalUnits);
										  for(int i = 0; i < 2; i++)//number of players
									     {
											  if(tempArray[i] == name)
											  {
												  tempArray[i].playerUI.lb.getHolder(s2.getX(), s2.getY()).add(s2.getLabel(), new Integer(5));    
						                    tempArray[i].playerUI.lb.getHolder(s2.getX(), s2.getY()+1).add(s2.getLabel(), new Integer(5));  
						                    tempArray[i].playerUI.lb.subArmyCount(s2.getX(), s2.getY()); 
									           if(((tempArray[i].playerUI.lb.getSettlerCount(s2.getX(), s2.getY()) + tempArray[i].playerUI.lb.getArmyCount(s2.getX(), s2.getY())) == 0) && !tempArray[i].playerUI.lb.getHasBuild(s2.getX(), s2.getY()))      
												    tempArray[i].playerUI.lb.setOwner(s2.getX(), s2.getY(), null);  
						     			        tempArray[i].playerUI.lb.addArmyCount(s2.getX(), s2.getY()+1);
//						                    turnClean(s2.getX(), s2.getY()+1);
											  }
											  else
											  {
												  JLabel l = new JLabel(s2.getIcon());
												  l.setBounds(s2.getCurrentbounds());
												  l.setName(s2.getLabelName());
						                    tempArray[i].playerUI.lb.getHolder(s2.getX(), s2.getY()+1).add(l, new Integer(5));  
						     			        tempArray[i].playerUI.lb.addArmyCount(s2.getX(), s2.getY()+1);
						                    leaveClean(s2.getX(), s2.getY(), s2);
				                          tempArray[i].unitArmyLabels.add(l);
//						                    turnClean(s2.getX(), s2.getY()+1);
						                    tempArray[i].playerUI.lb.subArmyCount(s2.getX(), s2.getY()); 
									           if(((tempArray[i].playerUI.lb.getSettlerCount(s2.getX(), s2.getY()) + tempArray[i].playerUI.lb.getArmyCount(s2.getX(), s2.getY())) == 0) && !tempArray[i].playerUI.lb.getHasBuild(s2.getX(), s2.getY()))      
												    tempArray[i].playerUI.lb.setOwner(s2.getX(), s2.getY(), null);  
				                       }
				                    }
							           s2.setPosition(s2.getX(), s2.getY()+1);
				                    turnClean(s2.getX(), s2.getY()-1);
				                    turnClean(s.getX(), s.getY());
										  s2.takeMove();  
										}
									 }
						        } 
						        break;  
						    case 3:
								  for(int k = 0; k < grp3.size(); k++)
								  {
								    Object temp = grp3.get(k);
									 if(temp.getClass().getName().equals("Settler"))
								    {
									   Settler s2 = (Settler)temp;
										if(s2.getGroup() == s.getGroup() && s2 != s)
										{
										  s2.setBounds(spaceTotalUnits);
										  for(int i = 0; i < 2; i++)//number of players
									     {
											  if(tempArray[i] == name)
											  {
												  tempArray[i].playerUI.lb.getHolder(s2.getX(), s2.getY()).add(s2.getLabel(), new Integer(5));    
						                    tempArray[i].playerUI.lb.getHolder(s2.getX(), s2.getY()+1).add(s2.getLabel(), new Integer(5));  
						                    tempArray[i].playerUI.lb.subSettlerCount(s2.getX(), s2.getY()); 
									           if(((tempArray[i].playerUI.lb.getSettlerCount(s2.getX(), s2.getY()) + tempArray[i].playerUI.lb.getArmyCount(s2.getX(), s2.getY())) == 0) && !tempArray[i].playerUI.lb.getHasBuild(s2.getX(), s2.getY()))      
												    tempArray[i].playerUI.lb.setOwner(s2.getX(), s2.getY(), null);  
						     			        tempArray[i].playerUI.lb.addSettlerCount(s2.getX(), s2.getY()+1);
//						                    turnClean(s2.getX(), s2.getY()+1);
											  }
											  else
											  {
												  JLabel l = new JLabel(s2.getIcon());
												  l.setBounds(s2.getCurrentbounds());
												  l.setName(s2.getLabelName());
						                    tempArray[i].playerUI.lb.getHolder(s2.getX(), s2.getY()+1).add(l, new Integer(5));  
						     			        tempArray[i].playerUI.lb.addSettlerCount(s2.getX(), s2.getY()+1);
						                    leaveClean(s2.getX(), s2.getY(), s2);
				                          tempArray[i].unitSettlerLabels.add(l);
//						                    turnClean(s2.getX(), s2.getY()+1);
						                    tempArray[i].playerUI.lb.subSettlerCount(s2.getX(), s2.getY()); 
									           if(((tempArray[i].playerUI.lb.getSettlerCount(s2.getX(), s2.getY()) + tempArray[i].playerUI.lb.getArmyCount(s2.getX(), s2.getY())) == 0) && !tempArray[i].playerUI.lb.getHasBuild(s2.getX(), s2.getY()))      
												    tempArray[i].playerUI.lb.setOwner(s2.getX(), s2.getY(), null);  
				                       }
				                    }
					                 s2.setPosition(s2.getX(), s2.getY()+1);
				                    turnClean(s2.getX(), s2.getY()-1);
				                    turnClean(s.getX(), s.getY());
										  s2.takeMove();  
										}
									 }
								    else
									 {
									   Army s2 = (Army)temp;
										if(s2.getGroup() == s.getGroup())
										{
										  s2.setBounds(name.playerUI.lb.getSettlerCount(s2.getX(), s2.getY()+1), name.playerUI.lb.getArmyCount(s2.getX(), s2.getY()+1));//spaceTotalUnits);
										  for(int i = 0; i < 2; i++)//number of players
									     {
											  if(tempArray[i] == name)
											  {
												  tempArray[i].playerUI.lb.getHolder(s2.getX(), s2.getY()).add(s2.getLabel(), new Integer(5));    
						                    tempArray[i].playerUI.lb.getHolder(s2.getX(), s2.getY()+1).add(s2.getLabel(), new Integer(5));  
						                    tempArray[i].playerUI.lb.subArmyCount(s2.getX(), s2.getY()); 
									           if(((tempArray[i].playerUI.lb.getSettlerCount(s2.getX(), s2.getY()) + tempArray[i].playerUI.lb.getArmyCount(s2.getX(), s2.getY())) == 0) && !tempArray[i].playerUI.lb.getHasBuild(s2.getX(), s2.getY()))      
												    tempArray[i].playerUI.lb.setOwner(s2.getX(), s2.getY(), null);  
						     			        tempArray[i].playerUI.lb.addArmyCount(s2.getX(), s2.getY()+1);
//						                    turnClean(s2.getX(), s2.getY()+1);
											  }
											  else
											  {
												  JLabel l = new JLabel(s2.getIcon());
												  l.setBounds(s2.getCurrentbounds());
												  l.setName(s2.getLabelName());
						                    tempArray[i].playerUI.lb.getHolder(s2.getX(), s2.getY()+1).add(l, new Integer(5));  
						     			        tempArray[i].playerUI.lb.addArmyCount(s2.getX(), s2.getY()+1);
						                    leaveClean(s2.getX(), s2.getY(), s2);
				                          tempArray[i].unitArmyLabels.add(l);
//						                    turnClean(s2.getX(), s2.getY()+1);
						                    tempArray[i].playerUI.lb.subArmyCount(s2.getX(), s2.getY()); 
									           if(((tempArray[i].playerUI.lb.getSettlerCount(s2.getX(), s2.getY()) + tempArray[i].playerUI.lb.getArmyCount(s2.getX(), s2.getY())) == 0) && !tempArray[i].playerUI.lb.getHasBuild(s2.getX(), s2.getY()))      
												    tempArray[i].playerUI.lb.setOwner(s2.getX(), s2.getY(), null);  
				                       }
				                    }
							           s2.setPosition(s2.getX(), s2.getY()+1);
				                    turnClean(s2.getX(), s2.getY()-1);
				                    turnClean(s.getX(), s.getY());
										  s2.takeMove();  
										}
									 }
						        } 
						        break;  
						    case 4:
								  for(int k = 0; k < grp4.size(); k++)
								  {
								    Object temp = grp4.get(k);
									 if(temp.getClass().getName().equals("Settler"))
								    {
									   Settler s2 = (Settler)temp;
										if(s2.getGroup() == s.getGroup() && s2 != s)
										{
										  s2.setBounds(spaceTotalUnits);
										  for(int i = 0; i < 2; i++)//number of players
									     {
											  if(tempArray[i] == name)
											  {
												  tempArray[i].playerUI.lb.getHolder(s2.getX(), s2.getY()).add(s2.getLabel(), new Integer(5));    
						                    tempArray[i].playerUI.lb.getHolder(s2.getX(), s2.getY()+1).add(s2.getLabel(), new Integer(5));  
						                    tempArray[i].playerUI.lb.subSettlerCount(s2.getX(), s2.getY()); 
									           if(((tempArray[i].playerUI.lb.getSettlerCount(s2.getX(), s2.getY()) + tempArray[i].playerUI.lb.getArmyCount(s2.getX(), s2.getY())) == 0) && !tempArray[i].playerUI.lb.getHasBuild(s2.getX(), s2.getY()))      
												    tempArray[i].playerUI.lb.setOwner(s2.getX(), s2.getY(), null);  
						     			        tempArray[i].playerUI.lb.addSettlerCount(s2.getX(), s2.getY()+1);
//						                    turnClean(s2.getX(), s2.getY()+1);
											  }
											  else
											  {
												  JLabel l = new JLabel(s2.getIcon());
												  l.setBounds(s2.getCurrentbounds());
												  l.setName(s2.getLabelName());
						                    tempArray[i].playerUI.lb.getHolder(s2.getX(), s2.getY()+1).add(l, new Integer(5));  
						     			        tempArray[i].playerUI.lb.addSettlerCount(s2.getX(), s2.getY()+1);
						                    leaveClean(s2.getX(), s2.getY(), s2);
				                          tempArray[i].unitSettlerLabels.add(l);
//						                    turnClean(s2.getX(), s2.getY()+1);
						                    tempArray[i].playerUI.lb.subSettlerCount(s2.getX(), s2.getY()); 
									           if(((tempArray[i].playerUI.lb.getSettlerCount(s2.getX(), s2.getY()) + tempArray[i].playerUI.lb.getArmyCount(s2.getX(), s2.getY())) == 0) && !tempArray[i].playerUI.lb.getHasBuild(s2.getX(), s2.getY()))      
												    tempArray[i].playerUI.lb.setOwner(s2.getX(), s2.getY(), null);  
				                       }
				                    }
					                 s2.setPosition(s2.getX(), s2.getY()+1);
				                    turnClean(s2.getX(), s2.getY()-1);
				                    turnClean(s.getX(), s.getY());
										  s2.takeMove();  
										}
								    }  
									 else
									 {
									   Army s2 = (Army)temp;
										if(s2.getGroup() == s.getGroup())
										{
										  s2.setBounds(name.playerUI.lb.getSettlerCount(s2.getX(), s2.getY()+1), name.playerUI.lb.getArmyCount(s2.getX(), s2.getY()+1));//spaceTotalUnits);
										  for(int i = 0; i < 2; i++)//number of players
									     {
											  if(tempArray[i] == name)
											  {
												  tempArray[i].playerUI.lb.getHolder(s2.getX(), s2.getY()).add(s2.getLabel(), new Integer(5));    
						                    tempArray[i].playerUI.lb.getHolder(s2.getX(), s2.getY()+1).add(s2.getLabel(), new Integer(5));  
						                    tempArray[i].playerUI.lb.subArmyCount(s2.getX(), s2.getY()); 
									           if(((tempArray[i].playerUI.lb.getSettlerCount(s2.getX(), s2.getY()) + tempArray[i].playerUI.lb.getArmyCount(s2.getX(), s2.getY())) == 0) && !tempArray[i].playerUI.lb.getHasBuild(s2.getX(), s2.getY()))      
												    tempArray[i].playerUI.lb.setOwner(s2.getX(), s2.getY(), null);  
						     			        tempArray[i].playerUI.lb.addArmyCount(s2.getX(), s2.getY()+1);
//						                    turnClean(s2.getX(), s2.getY()+1);
											  }
											  else
											  {
												  JLabel l = new JLabel(s2.getIcon());
												  l.setBounds(s2.getCurrentbounds());
												  l.setName(s2.getLabelName());
						                    tempArray[i].playerUI.lb.getHolder(s2.getX(), s2.getY()+1).add(l, new Integer(5));  
						     			        tempArray[i].playerUI.lb.addArmyCount(s2.getX(), s2.getY()+1);
						                    leaveClean(s2.getX(), s2.getY(), s2);
				                          tempArray[i].unitArmyLabels.add(l);
//						                    turnClean(s2.getX(), s2.getY()+1);
						                    tempArray[i].playerUI.lb.subArmyCount(s2.getX(), s2.getY()); 
									           if(((tempArray[i].playerUI.lb.getSettlerCount(s2.getX(), s2.getY()) + tempArray[i].playerUI.lb.getArmyCount(s2.getX(), s2.getY())) == 0) && !tempArray[i].playerUI.lb.getHasBuild(s2.getX(), s2.getY()))      
												    tempArray[i].playerUI.lb.setOwner(s2.getX(), s2.getY(), null);  
				                       }
				                    }
							           s2.setPosition(s2.getX(), s2.getY()+1);
				                    turnClean(s2.getX(), s2.getY()-1);
				                    turnClean(s.getX(), s.getY());
										  s2.takeMove();  
										}
									 }
						        } 
						        break;  
							  } 
						  }
				        s.setPosition(s.getX(), s.getY()+1);
                    turnClean(s.getX(), s.getY()-1);
                    turnClean(s.getX(), s.getY());
	   				  s.takeMove();  

                    if(name.playerUI.lb.hasHut(s.getX(), s.getY()) > 0)              
						  {
							 String type = name.playerUI.lb.getHutResource(s.getX(), s.getY());
							 name.addResource(type, "hut"); 
						    name.playerUI.tellHutResource(type);   
							 name.playerBoard.setHut(1);
							 for(int k = 0; k < 2; k++)
							 {
							   tempArray[k].playerUI.lb.removeHut(s.getX(), s.getY());
						    }
						    if(name.getNation().equals("China"))
							   name.addCulture(3);  
						    resetSettler(s);
						  }
						  if(s.getMoves() == 0)
			           {
  	 	                resetSettler(s);
					     }
					   }
					 }
				  }				
				}//check Settler move
				else if((a != null) && (a.getY()+1 < 16))
				{
				  spaceTotalUnits = name.playerUI.lb.getSettlerCount(a.getX(), a.getY()+1) + name.playerUI.lb.getArmyCount(a.getX(), a.getY()+1);
				  if(((name.playerUI.lb.getOwner(a.getX(), a.getY()+1) != null) && (name != name.playerUI.lb.getOwner(a.getX(), a.getY()+1))) || (name.playerUI.lb.hasVillage(a.getX(), a.getY()+1) > 0))
				  {
//				    System.out.println("COMBAT");//do combat
					 conductCombat = true;	
				  }
              if((name.playerUI.lb.isCitySpace(a.getX(), a.getY()+1)) && (name != name.playerUI.lb.getOwner(a.getX(), a.getY()+1)) && (name.getCurrentGov() == 2))
				  {
					   JOptionPane illegal = new JOptionPane();
				      GameImageIcon im = getImage("data/"+name.getNation()+"Icon.png");  
		            illegal.showMessageDialog(name.playerUI.map, "YOU CANNOT ATTACK ENEMY CITY WHEN\n YOU ARE UNDER DEMOCRACY!", "ILLEGAL MOVE",
				  					 			        JOptionPane.INFORMATION_MESSAGE, im);	 	 
				    conductCombat = false;
				    dir = -1;
				  }
				  else if(!conductCombat && ((spaceTotalUnits >= name.getStacklimit()) || (spaceTotalUnits + name.getGrouptotal(a.getGroup()) > name.getStacklimit())  || (name.playerUI.lb.isCitySpace(a.getX(), a.getY()+1) && a.getMoves() < 2)))
				    dir = -1;
				  else
				  {
                if(name.playerUI.lb.getTerrain(a.getX(), a.getY()).equals("Water") && !name.getWaterLand() && (a.getMoves() <= 1) && (name.playerUI.lb.getTerrain(a.getX(), a.getY()+1) == null || name.playerUI.lb.checkExplored(a.getX(), a.getY()+1)))
					 {
					   JOptionPane illegal = new JOptionPane();
				      GameImageIcon im = getImage("data/"+name.getNation()+"Icon.png");  
		            illegal.showMessageDialog(name.playerUI.map, "YOU CANNOT LAND IN WATER WITH CURRENT TECHNOLOGY!\nMOVE UNIT TO LAND", "CANNOT END MOVE HERE",
				  					 			        JOptionPane.INFORMATION_MESSAGE, im);	 	 
					 }            
				    else if(name.playerUI.lb.checkExplored(a.getX(), a.getY()+1))
					 {
                  if(name.playerUI.lb.getHolder(a.getX(), a.getY()).getParent().getName().equals("panel0"))
					   {
						   for(int k = 0; k < 2; k++)//number of players
					      {
						     tempArray[k].playerUI.lb.populateTile(name.getPlayerUI().boardPosition[1], 1, 3, tempArray[k]);
							  tempArray[k].playerUI.map.remove(tempArray[k].playerUI.myfog2);
							  tempArray[k].playerUI.map.add(tempArray[k].playerUI.mypanel1, 2);
					        tempArray[k].playerUI.map.validate();
					        tempArray[k].playerUI.map.repaint();
							}
  						}    
		            else if(name.playerUI.lb.getHolder(a.getX(), a.getY()).getParent().getName().equals("panel1"))
					   {
						   for(int k = 0; k < 2; k++)//number of players
					      {
						     tempArray[k].playerUI.lb.populateTile(name.getPlayerUI().boardPosition[2], 2, 3, tempArray[k]);
							  tempArray[k].playerUI.map.remove(tempArray[k].playerUI.myfog4);
							  tempArray[k].playerUI.map.add(tempArray[k].playerUI.mypanel2, 4);
					        tempArray[k].playerUI.map.validate();
					        tempArray[k].playerUI.map.repaint();
							}
  						}    
		            else if(name.playerUI.lb.getHolder(a.getX(), a.getY()).getParent().getName().equals("panel4"))
					   {
						   for(int k = 0; k < 2; k++)//number of players
					      {
						     tempArray[k].playerUI.lb.populateTile(name.getPlayerUI().boardPosition[5], 5, 3, tempArray[k]);
							  tempArray[k].playerUI.map.remove(tempArray[k].playerUI.myfog3);
							  tempArray[k].playerUI.map.add(tempArray[k].playerUI.mypanel5, 3);
					        tempArray[k].playerUI.map.validate();
					        tempArray[k].playerUI.map.repaint();
							}
						}
		            else if(name.playerUI.lb.getHolder(a.getX(), a.getY()).getParent().getName().equals("panel5"))
					   {
						   for(int k = 0; k < 2; k++)//number of players
					      {
						     tempArray[k].playerUI.lb.populateTile(name.getPlayerUI().boardPosition[6], 6, 3, tempArray[k]);
							  tempArray[k].playerUI.map.remove(tempArray[k].playerUI.myfog5);
							  tempArray[k].playerUI.map.add(tempArray[k].playerUI.mypanel6, 5);
					        tempArray[k].playerUI.map.validate();
					        tempArray[k].playerUI.map.repaint();
							}
						}
		            else if(name.playerUI.lb.getHolder(a.getX(), a.getY()).getParent().getName().equals("panel6"))
					   {
						   for(int k = 0; k < 2; k++)//number of players
					      {
						     tempArray[k].playerUI.lb.populateTile(name.getPlayerUI().boardPosition[7], 7, 3, tempArray[k]);
							  tempArray[k].playerUI.map.remove(tempArray[k].playerUI.myfog6);
							  tempArray[k].playerUI.map.add(tempArray[k].playerUI.mypanel7, 7);
					        tempArray[k].playerUI.map.validate();
					        tempArray[k].playerUI.map.repaint();
							}
						}
   				   a.takeMove();  
						if(a.getGroup() > 0)
						{
						  switch(a.getGroup())
						  {
						    case 1:
								  for(int k = 0; k < grp1.size(); k++)
								  {
								    Object temp = grp1.get(k);
									 if(temp.getClass().getName().equals("Settler"))
								    {
									   Settler s2 = (Settler)temp;
										if(s2.getGroup() == a.getGroup())
										{
						   			  s2.takeMove();  
										}
									 }
								    else
									 {
									   Army s2 = (Army)temp;
										if(s2.getGroup() == a.getGroup() && s2 != a)
										{
										  s2.takeMove();
										}
									 }
						        } 
						        break;  
						    case 2:
								  for(int k = 0; k < grp2.size(); k++)
								  {
								    Object temp = grp2.get(k);
									 if(temp.getClass().getName().equals("Settler"))
								    {
									   Settler s2 = (Settler)temp;
										if(s2.getGroup() == a.getGroup())
										{
						   			  s2.takeMove();  
										}
									 }
								    else
									 {
									   Army s2 = (Army)temp;
										if(s2.getGroup() == a.getGroup() && s2 != a)
										{
										  s2.takeMove();
										}
									 }
						        } 
						        break;  
						    case 3:
								  for(int k = 0; k < grp3.size(); k++)
								  {
								    Object temp = grp3.get(k);
									 if(temp.getClass().getName().equals("Settler"))
								    {
									   Settler s2 = (Settler)temp;
										if(s2.getGroup() == a.getGroup())
										{
						   			  s2.takeMove();  
										}
									 }
								    else
									 {
									   Army s2 = (Army)temp;
										if(s2.getGroup() == a.getGroup() && s2 != a)
										{
										  s2.takeMove();
										}
									 }
						        } 
						        break;  
						    case 4:
								  for(int k = 0; k < grp4.size(); k++)
								  {
								    Object temp = grp4.get(k);
									 if(temp.getClass().getName().equals("Settler"))
								    {
									   Settler s2 = (Settler)temp;
										if(s2.getGroup() == a.getGroup())
										{
						   			  s2.takeMove();  
										}
									 }
								    else
									 {
									   Army s2 = (Army)temp;
										if(s2.getGroup() == a.getGroup() && s2 != a)
										{
										  s2.takeMove();
										}
									 }
						        } 
						        break;  
					      }  
						}
						if(a.getMoves() == 0)
		            {
                    resetArmy(a);
				      }
					 }
				    else
					 {
                  if(name.playerUI.lb.getTerrain(a.getX(), a.getY()+1).equals("Water") && !name.getWaterCross())    
						{
						  dir = -1;
						}
	               else if(name.playerUI.lb.getTerrain(a.getX(), a.getY()+1).equals("Water") && !name.getWaterLand() && (a.getMoves() < 2)) 
						{
						  dir = -1;
						}          
						else
						{
						  boolean SettlerOnly = false; 
						  if(name.playerUI.lb.getArmyCount(a.getX(), a.getY()+1) == 0 && conductCombat) 
						  {
							 SettlerOnly = true;
						  }
						  a.setBounds(name.playerUI.lb.getSettlerCount(a.getX(), a.getY()+1), name.playerUI.lb.getArmyCount(a.getX(), a.getY()+1));//spaceTotalUnits);
						  for(int k = 0; k < 2; k++)//number of players
					     {
							  if(tempArray[k] == name)
							  {
								  tempArray[k].playerUI.lb.getHolder(a.getX(), a.getY()).add(a.getLabel(), new Integer(5));    
		                    tempArray[k].playerUI.lb.getHolder(a.getX(), a.getY()+1).add(a.getLabel(), new Integer(5));  
		                    tempArray[k].playerUI.lb.subArmyCount(a.getX(), a.getY()); 
					           if(((tempArray[k].playerUI.lb.getSettlerCount(a.getX(), a.getY()) + tempArray[k].playerUI.lb.getArmyCount(a.getX(), a.getY())) == 0) && !tempArray[k].playerUI.lb.getHasBuild(a.getX(), a.getY()))      
								    tempArray[k].playerUI.lb.setOwner(a.getX(), a.getY(), null);  
		     			        tempArray[k].playerUI.lb.addArmyCount(a.getX(), a.getY()+1);
//		                    turnClean(a.getX(), a.getY()+1);
							  }
							  else
							  {
								  JLabel l = new JLabel(a.getIcon());
								  l.setBounds(a.getCurrentbounds());
								  l.setName(a.getLabelName());
		                    tempArray[k].playerUI.lb.getHolder(a.getX(), a.getY()+1).add(l, new Integer(5));  
		     			        tempArray[k].playerUI.lb.addArmyCount(a.getX(), a.getY()+1);
		                    leaveClean(a.getX(), a.getY(), a);
                          tempArray[k].unitArmyLabels.add(l);
//		                    turnClean(a.getX(), a.getY()+1);
		                    tempArray[k].playerUI.lb.subArmyCount(a.getX(), a.getY()); 
					           if(((tempArray[k].playerUI.lb.getSettlerCount(a.getX(), a.getY()) + tempArray[k].playerUI.lb.getArmyCount(a.getX(), a.getY())) == 0) && !tempArray[k].playerUI.lb.getHasBuild(a.getX(), a.getY()))      
								    tempArray[k].playerUI.lb.setOwner(a.getX(), a.getY(), null);  
                       }
                    }
						  
						  if(a.getGroup() > 0)
						  {
						    switch(a.getGroup())
							 {     
								case 1:
								  for(int k = 0; k < grp1.size(); k++)
								  {
								    Object temp = grp1.get(k);
									 if(temp.getClass().getName().equals("Settler"))
								    {
									   Settler s2 = (Settler)temp;
										if(s2.getGroup() == a.getGroup())
										{
										  s2.setBounds(spaceTotalUnits);
										  for(int i = 0; i < 2; i++)//number of players
									     {
											  if(tempArray[i] == name)
											  {
												  tempArray[i].playerUI.lb.getHolder(s2.getX(), s2.getY()).add(s2.getLabel(), new Integer(5));    
						                    tempArray[i].playerUI.lb.getHolder(s2.getX(), s2.getY()+1).add(s2.getLabel(), new Integer(5));  
						                    tempArray[i].playerUI.lb.subSettlerCount(s2.getX(), s2.getY()); 
									           if(((tempArray[i].playerUI.lb.getSettlerCount(s2.getX(), s2.getY()) + tempArray[i].playerUI.lb.getArmyCount(s2.getX(), s2.getY())) == 0) && !tempArray[i].playerUI.lb.getHasBuild(s2.getX(), s2.getY()))      
												    tempArray[i].playerUI.lb.setOwner(s2.getX(), s2.getY(), null);  
						     			        tempArray[i].playerUI.lb.addSettlerCount(s2.getX(), s2.getY()+1);
//						                    turnClean(s2.getX(), s2.getY()-1);
											  }
											  else
											  {
												  JLabel l = new JLabel(s2.getIcon());
												  l.setBounds(s2.getCurrentbounds());
												  l.setName(s2.getLabelName());
						                    tempArray[i].playerUI.lb.getHolder(s2.getX(), s2.getY()+1).add(l, new Integer(5));  
						     			        tempArray[i].playerUI.lb.addSettlerCount(s2.getX(), s2.getY()+1);
						                    leaveClean(s2.getX(), s2.getY(), s2);
				                          tempArray[i].unitSettlerLabels.add(l);
//						                    turnClean(s2.getX(), s2.getY()-1);
						                    tempArray[i].playerUI.lb.subSettlerCount(s2.getX(), s2.getY()); 
									           if(((tempArray[i].playerUI.lb.getSettlerCount(s2.getX(), s2.getY()) + tempArray[i].playerUI.lb.getArmyCount(s2.getX(), s2.getY())) == 0) && !tempArray[i].playerUI.lb.getHasBuild(s2.getX(), s2.getY()))      
												    tempArray[i].playerUI.lb.setOwner(s2.getX(), s2.getY(), null);  
				                       }
				                    }
					                 s2.setPosition(s2.getX(), s2.getY()+1);
				                    turnClean(s2.getX(), s2.getY());
				                    turnClean(a.getX(), a.getY()-1);
										  s2.takeMove();  
										}
								    }
								    else
									 {
									   Army s2 = (Army)temp;
										if(s2.getGroup() == a.getGroup() && s2 != a)
										{
//										  s2.setBounds(spaceTotalUnits);
										  for(int i = 0; i < 2; i++)//number of players
									     {
											  if(tempArray[i] == name)
											  {
												  tempArray[i].playerUI.lb.getHolder(s2.getX(), s2.getY()).add(s2.getLabel(), new Integer(5));    
						                    tempArray[i].playerUI.lb.getHolder(s2.getX(), s2.getY()+1).add(s2.getLabel(), new Integer(5));  
						                    tempArray[i].playerUI.lb.subArmyCount(s2.getX(), s2.getY()); 
									           if(((tempArray[i].playerUI.lb.getSettlerCount(s2.getX(), s2.getY()) + tempArray[i].playerUI.lb.getArmyCount(s2.getX(), s2.getY())) == 0) && !tempArray[i].playerUI.lb.getHasBuild(s2.getX(), s2.getY()))      
												    tempArray[i].playerUI.lb.setOwner(s2.getX(), s2.getY(), null);  
						     			        tempArray[i].playerUI.lb.addArmyCount(s2.getX(), s2.getY()+1);
//						                    turnClean(s2.getX(), s2.getY()-1);
											  }
											  else
											  {
												  JLabel l = new JLabel(s2.getIcon());
												  l.setBounds(s2.getCurrentbounds());
												  l.setName(s2.getLabelName());
						                    tempArray[i].playerUI.lb.getHolder(s2.getX(), s2.getY()+1).add(l, new Integer(5));  
						     			        tempArray[i].playerUI.lb.addArmyCount(s2.getX(), s2.getY()+1);
						                    leaveClean(s2.getX(), s2.getY(), s2);
				                          tempArray[i].unitArmyLabels.add(l);
//						                    turnClean(s2.getX(), s2.getY()-1);
						                    tempArray[i].playerUI.lb.subArmyCount(s2.getX(), s2.getY()); 
									           if(((tempArray[i].playerUI.lb.getSettlerCount(s2.getX(), s2.getY()) + tempArray[i].playerUI.lb.getArmyCount(s2.getX(), s2.getY())) == 0) && !tempArray[i].playerUI.lb.getHasBuild(s2.getX(), s2.getY()))      
												    tempArray[i].playerUI.lb.setOwner(s2.getX(), s2.getY(), null);  
				                       }
				                    }
							           s2.setPosition(s2.getX(), s2.getY()+1);
				                    turnClean(s2.getX(), s2.getY());
				                    turnClean(a.getX(), a.getY()-1);
										  s2.takeMove();  
										}
									 }
                          }  
						        break;  
						    case 2:
								  for(int k = 0; k < grp2.size(); k++)
								  {
								    Object temp = grp2.get(k);
									 if(temp.getClass().getName().equals("Settler"))
								    {
									   Settler s2 = (Settler)temp;
										if(s2.getGroup() == a.getGroup())
										{
										  s2.setBounds(spaceTotalUnits);
										  for(int i = 0; i < 2; i++)//number of players
									     {
											  if(tempArray[i] == name)
											  {
												  tempArray[i].playerUI.lb.getHolder(s2.getX(), s2.getY()).add(s2.getLabel(), new Integer(5));    
						                    tempArray[i].playerUI.lb.getHolder(s2.getX(), s2.getY()+1).add(s2.getLabel(), new Integer(5));  
						                    tempArray[i].playerUI.lb.subSettlerCount(s2.getX(), s2.getY()); 
									           if(((tempArray[i].playerUI.lb.getSettlerCount(s2.getX(), s2.getY()) + tempArray[i].playerUI.lb.getArmyCount(s2.getX(), s2.getY())) == 0) && !tempArray[i].playerUI.lb.getHasBuild(s2.getX(), s2.getY()))      
												    tempArray[i].playerUI.lb.setOwner(s2.getX(), s2.getY(), null);  
						     			        tempArray[i].playerUI.lb.addSettlerCount(s2.getX(), s2.getY()+1);
//						                    turnClean(s2.getX(), s2.getY()-1);
											  }
											  else
											  {
												  JLabel l = new JLabel(s2.getIcon());
												  l.setBounds(s2.getCurrentbounds());
												  l.setName(s2.getLabelName());
						                    tempArray[i].playerUI.lb.getHolder(s2.getX(), s2.getY()+1).add(l, new Integer(5));  
						     			        tempArray[i].playerUI.lb.addSettlerCount(s2.getX(), s2.getY()+1);
						                    leaveClean(s2.getX(), s2.getY(), s2);
				                          tempArray[i].unitSettlerLabels.add(l);
//						                    turnClean(s2.getX(), s2.getY()-1);
						                    tempArray[i].playerUI.lb.subSettlerCount(s2.getX(), s2.getY()); 
									           if(((tempArray[i].playerUI.lb.getSettlerCount(s2.getX(), s2.getY()) + tempArray[i].playerUI.lb.getArmyCount(s2.getX(), s2.getY())) == 0) && !tempArray[i].playerUI.lb.getHasBuild(s2.getX(), s2.getY()))      
												    tempArray[i].playerUI.lb.setOwner(s2.getX(), s2.getY(), null);  
				                       }
				                    }
					                 s2.setPosition(s2.getX(), s2.getY()+1);
				                    turnClean(s2.getX(), s2.getY());
				                    turnClean(a.getX(), a.getY()-1);
										  s2.takeMove();  
										}
									 }
								    else
									 {
									   Army s2 = (Army)temp;
										if(s2.getGroup() == a.getGroup() && s2 != a)
										{
//										  s2.setBounds(spaceTotalUnits);//spaceTotalUnits);
										  for(int i = 0; i < 2; i++)//number of players
									     {
											  if(tempArray[i] == name)
											  {
												  tempArray[i].playerUI.lb.getHolder(s2.getX(), s2.getY()).add(s2.getLabel(), new Integer(5));    
						                    tempArray[i].playerUI.lb.getHolder(s2.getX(), s2.getY()+1).add(s2.getLabel(), new Integer(5));  
						                    tempArray[i].playerUI.lb.subArmyCount(s2.getX(), s2.getY()); 
									           if(((tempArray[i].playerUI.lb.getSettlerCount(s2.getX(), s2.getY()) + tempArray[i].playerUI.lb.getArmyCount(s2.getX(), s2.getY())) == 0) && !tempArray[i].playerUI.lb.getHasBuild(s2.getX(), s2.getY()))      
												    tempArray[i].playerUI.lb.setOwner(s2.getX(), s2.getY(), null);  
						     			        tempArray[i].playerUI.lb.addArmyCount(s2.getX(), s2.getY()+1);
//						                    turnClean(s2.getX(), s2.getY()-1);
											  }
											  else
											  {
												  JLabel l = new JLabel(s2.getIcon());
												  l.setBounds(s2.getCurrentbounds());
												  l.setName(s2.getLabelName());
						                    tempArray[i].playerUI.lb.getHolder(s2.getX(), s2.getY()+1).add(l, new Integer(5));  
						     			        tempArray[i].playerUI.lb.addArmyCount(s2.getX(), s2.getY()+1);
						                    leaveClean(s2.getX(), s2.getY(), s2);
				                          tempArray[i].unitArmyLabels.add(l);
//						                    turnClean(s2.getX(), s2.getY()-1);
						                    tempArray[i].playerUI.lb.subArmyCount(s2.getX(), s2.getY()); 
									           if(((tempArray[i].playerUI.lb.getSettlerCount(s2.getX(), s2.getY()) + tempArray[i].playerUI.lb.getArmyCount(s2.getX(), s2.getY())) == 0) && !tempArray[i].playerUI.lb.getHasBuild(s2.getX(), s2.getY()))      
												    tempArray[i].playerUI.lb.setOwner(s2.getX(), s2.getY(), null);  
				                       }
				                    }
							           s2.setPosition(s2.getX(), s2.getY()+1);
				                    turnClean(s2.getX(), s2.getY());
				                    turnClean(a.getX(), a.getY()-1);
										  s2.takeMove();  
										}
									 }
						        } 
						        break;  
						    case 3:
								  for(int k = 0; k < grp3.size(); k++)
								  {
								    Object temp = grp3.get(k);
									 if(temp.getClass().getName().equals("Settler"))
								    {
									   Settler s2 = (Settler)temp;
										if(s2.getGroup() == a.getGroup())
										{
										  s2.setBounds(spaceTotalUnits);
										  for(int i = 0; i < 2; i++)//number of players
									     {
											  if(tempArray[i] == name)
											  {
												  tempArray[i].playerUI.lb.getHolder(s2.getX(), s2.getY()).add(s2.getLabel(), new Integer(5));    
						                    tempArray[i].playerUI.lb.getHolder(s2.getX(), s2.getY()+1).add(s2.getLabel(), new Integer(5));  
						                    tempArray[i].playerUI.lb.subSettlerCount(s2.getX(), s2.getY()); 
									           if(((tempArray[i].playerUI.lb.getSettlerCount(s2.getX(), s2.getY()) + tempArray[i].playerUI.lb.getArmyCount(s2.getX(), s2.getY())) == 0) && !tempArray[i].playerUI.lb.getHasBuild(s2.getX(), s2.getY()))      
												    tempArray[i].playerUI.lb.setOwner(s2.getX(), s2.getY(), null);  
						     			        tempArray[i].playerUI.lb.addSettlerCount(s2.getX(), s2.getY()+1);
//						                    turnClean(s2.getX(), s2.getY()-1);
											  }
											  else
											  {
												  JLabel l = new JLabel(s2.getIcon());
												  l.setBounds(s2.getCurrentbounds());
												  l.setName(s2.getLabelName());
						                    tempArray[i].playerUI.lb.getHolder(s2.getX(), s2.getY()+1).add(l, new Integer(5));  
						     			        tempArray[i].playerUI.lb.addSettlerCount(s2.getX(), s2.getY()+1);
						                    leaveClean(s2.getX(), s2.getY(), s2);
				                          tempArray[i].unitSettlerLabels.add(l);
//						                    turnClean(s2.getX(), s2.getY()-1);
						                    tempArray[i].playerUI.lb.subSettlerCount(s2.getX(), s2.getY()); 
									           if(((tempArray[i].playerUI.lb.getSettlerCount(s2.getX(), s2.getY()) + tempArray[i].playerUI.lb.getArmyCount(s2.getX(), s2.getY())) == 0) && !tempArray[i].playerUI.lb.getHasBuild(s2.getX(), s2.getY()))      
												    tempArray[i].playerUI.lb.setOwner(s2.getX(), s2.getY(), null);  
				                       }
				                    }
					                 s2.setPosition(s2.getX(), s2.getY()+1);
				                    turnClean(s2.getX(), s2.getY());
				                    turnClean(a.getX(), a.getY()-1);
										  s2.takeMove();  
										}
									 }
								    else
									 {
									   Army s2 = (Army)temp;
										if(s2.getGroup() == a.getGroup() && s2 != a)
										{
//										  s2.setBounds(spaceTotalUnits);//spaceTotalUnits);
										  for(int i = 0; i < 2; i++)//number of players
									     {
											  if(tempArray[i] == name)
											  {
												  tempArray[i].playerUI.lb.getHolder(s2.getX(), s2.getY()).add(s2.getLabel(), new Integer(5));    
						                    tempArray[i].playerUI.lb.getHolder(s2.getX(), s2.getY()+1).add(s2.getLabel(), new Integer(5));  
						                    tempArray[i].playerUI.lb.subArmyCount(s2.getX(), s2.getY()); 
									           if(((tempArray[i].playerUI.lb.getSettlerCount(s2.getX(), s2.getY()) + tempArray[i].playerUI.lb.getArmyCount(s2.getX(), s2.getY())) == 0) && !tempArray[i].playerUI.lb.getHasBuild(s2.getX(), s2.getY()))      
												    tempArray[i].playerUI.lb.setOwner(s2.getX(), s2.getY(), null);  
						     			        tempArray[i].playerUI.lb.addArmyCount(s2.getX(), s2.getY()+1);
//						                    turnClean(s2.getX(), s2.getY()-1);
											  }
											  else
											  {
												  JLabel l = new JLabel(s2.getIcon());
												  l.setBounds(s2.getCurrentbounds());
												  l.setName(s2.getLabelName());
						                    tempArray[i].playerUI.lb.getHolder(s2.getX(), s2.getY()+1).add(l, new Integer(5));  
						     			        tempArray[i].playerUI.lb.addArmyCount(s2.getX(), s2.getY()+1);
						                    leaveClean(s2.getX(), s2.getY(), s2);
				                          tempArray[i].unitArmyLabels.add(l);
//						                    turnClean(s2.getX(), s2.getY()-1);
						                    tempArray[i].playerUI.lb.subArmyCount(s2.getX(), s2.getY()); 
									           if(((tempArray[i].playerUI.lb.getSettlerCount(s2.getX(), s2.getY()) + tempArray[i].playerUI.lb.getArmyCount(s2.getX(), s2.getY())) == 0) && !tempArray[i].playerUI.lb.getHasBuild(s2.getX(), s2.getY()))      
												    tempArray[i].playerUI.lb.setOwner(s2.getX(), s2.getY(), null);  
				                       }
				                    }
							           s2.setPosition(s2.getX(), s2.getY()+1);
				                    turnClean(s2.getX(), s2.getY());
				                    turnClean(a.getX(), a.getY()-1);
										  s2.takeMove();  
										}
									 }
						        } 
						        break;  
						    case 4:
								  for(int k = 0; k < grp4.size(); k++)
								  {
								    Object temp = grp4.get(k);
									 if(temp.getClass().getName().equals("Settler"))
								    {
									   Settler s2 = (Settler)temp;
										if(s2.getGroup() == a.getGroup())
										{
										  s2.setBounds(spaceTotalUnits);
										  for(int i = 0; i < 2; i++)//number of players
									     {
											  if(tempArray[i] == name)
											  {
												  tempArray[i].playerUI.lb.getHolder(s2.getX(), s2.getY()).add(s2.getLabel(), new Integer(5));    
						                    tempArray[i].playerUI.lb.getHolder(s2.getX(), s2.getY()+1).add(s2.getLabel(), new Integer(5));  
						                    tempArray[i].playerUI.lb.subSettlerCount(s2.getX(), s2.getY()); 
									           if(((tempArray[i].playerUI.lb.getSettlerCount(s2.getX(), s2.getY()) + tempArray[i].playerUI.lb.getArmyCount(s2.getX(), s2.getY())) == 0) && !tempArray[i].playerUI.lb.getHasBuild(s2.getX(), s2.getY()))      
												    tempArray[i].playerUI.lb.setOwner(s2.getX(), s2.getY(), null);  
						     			        tempArray[i].playerUI.lb.addSettlerCount(s2.getX(), s2.getY()+1);
//						                    turnClean(s2.getX(), s2.getY()-1);
											  }
											  else
											  {
												  JLabel l = new JLabel(s2.getIcon());
												  l.setBounds(s2.getCurrentbounds());
												  l.setName(s2.getLabelName());
						                    tempArray[i].playerUI.lb.getHolder(s2.getX(), s2.getY()+1).add(l, new Integer(5));  
						     			        tempArray[i].playerUI.lb.addSettlerCount(s2.getX(), s2.getY()+1);
						                    leaveClean(s2.getX(), s2.getY(), s2);
				                          tempArray[i].unitSettlerLabels.add(l);
//						                    turnClean(s2.getX(), s2.getY()-1);
						                    tempArray[i].playerUI.lb.subSettlerCount(s2.getX(), s2.getY()); 
									           if(((tempArray[i].playerUI.lb.getSettlerCount(s2.getX(), s2.getY()) + tempArray[i].playerUI.lb.getArmyCount(s2.getX(), s2.getY())) == 0) && !tempArray[i].playerUI.lb.getHasBuild(s2.getX(), s2.getY()))      
												    tempArray[i].playerUI.lb.setOwner(s2.getX(), s2.getY(), null);  
				                       }
				                    }
					                 s2.setPosition(s2.getX(), s2.getY()+1);
				                    turnClean(s2.getX(), s2.getY());
				                    turnClean(a.getX(), a.getY()-1);
										  s2.takeMove();  
										}
									 }
								    else
									 {
									   Army s2 = (Army)temp;
										if(s2.getGroup() == a.getGroup() && s2 != a)
										{
//										  s2.setBounds(spaceTotalUnits);//spaceTotalUnits);
										  for(int i = 0; i < 2; i++)//number of players
									     {
											  if(tempArray[i] == name)
											  {
												  tempArray[i].playerUI.lb.getHolder(s2.getX(), s2.getY()).add(s2.getLabel(), new Integer(5));    
						                    tempArray[i].playerUI.lb.getHolder(s2.getX(), s2.getY()+1).add(s2.getLabel(), new Integer(5));  
						                    tempArray[i].playerUI.lb.subArmyCount(s2.getX(), s2.getY()); 
									           if(((tempArray[i].playerUI.lb.getSettlerCount(s2.getX(), s2.getY()) + tempArray[i].playerUI.lb.getArmyCount(s2.getX(), s2.getY())) == 0) && !tempArray[i].playerUI.lb.getHasBuild(s2.getX(), s2.getY()))      
												    tempArray[i].playerUI.lb.setOwner(s2.getX(), s2.getY(), null);  
						     			        tempArray[i].playerUI.lb.addArmyCount(s2.getX(), s2.getY()+1);
//						                    turnClean(s2.getX(), s2.getY()-1);
											  }
											  else
											  {
												  JLabel l = new JLabel(s2.getIcon());
												  l.setBounds(s2.getCurrentbounds());
												  l.setName(s2.getLabelName());
						                    tempArray[i].playerUI.lb.getHolder(s2.getX(), s2.getY()+1).add(l, new Integer(5));  
						     			        tempArray[i].playerUI.lb.addArmyCount(s2.getX(), s2.getY()+1);
						                    leaveClean(s2.getX(), s2.getY(), s2);
				                          tempArray[i].unitArmyLabels.add(l);
//						                    turnClean(s2.getX(), s2.getY()-1);
						                    tempArray[i].playerUI.lb.subArmyCount(s2.getX(), s2.getY()); 
									           if(((tempArray[i].playerUI.lb.getSettlerCount(s2.getX(), s2.getY()) + tempArray[i].playerUI.lb.getArmyCount(s2.getX(), s2.getY())) == 0) && !tempArray[i].playerUI.lb.getHasBuild(s2.getX(), s2.getY()))      
												    tempArray[i].playerUI.lb.setOwner(s2.getX(), s2.getY(), null);  
				                       }
				                    }
							           s2.setPosition(s2.getX(), s2.getY()+1);
				                    turnClean(s2.getX(), s2.getY());
				                    turnClean(a.getX(), a.getY()-1);
										  s2.takeMove();  
										}
									 }
						        } 
						        break;  
                       }
						  }
 			           a.setPosition(a.getX(), a.getY()+1);
                    turnClean(a.getX(), a.getY()-1);
                    turnClean(a.getX(), a.getY());
	   				  a.takeMove();  

                    if(name.playerUI.lb.hasHut(a.getX(), a.getY()) > 0)              
						  {
							 String type = name.playerUI.lb.getHutResource(a.getX(), a.getY());
							 name.addResource(type, "hut"); 
						    name.playerUI.tellHutResource(type);   
							 name.playerBoard.setHut(1);
							 for(int k = 0; k < 2; k++)
							 {
							   tempArray[k].playerUI.lb.removeHut(a.getX(), a.getY());
						    }
						    if(name.getNation().equals("China"))
							   name.addCulture(3);  
						    resetArmy(a);
						  }
						  if(a.getMoves() == 0 && !conductCombat)
		              {
                      resetArmy(a);
				        }
                    if(conductCombat)
						  {
                      combatPause();  
					       if(name.playerUI.lb.hasVillage(a.getX(), a.getY()) > 0)
							 {
						      combatUnit = a;
							   combat = new Combat(new Player("Village"), name, this);
						      a.combatMoves();
							 }    
                      else if(SettlerOnly)
							 {
						      Player loser = name.playerUI.lb.getOwner(a.getX(), a.getY());
								Vector sl = loser.getSettlerVector();
								int k;
								for(k = 0; k < sl.size(); k++)
								{
						        Settler temp = (Settler) sl.get(k); 
								  if((temp.getX() == a.getX()) && (temp.getY() == a.getY()))
						        {
								    JLayeredPane lp = name.playerUI.lb.getHolder(a.getX(), a.getY());    
								    loser.removeSettler(temp);
								    name.playerUI.lb.subSettlerCount(a.getX(), a.getY());  
									 name.playerUI.lb.getHolder(a.getX(), a.getY()).remove(temp.getLabel());
									 name.playerUI.lb.getHolder(a.getX(), a.getY()).validate();
									 name.playerUI.lb.getHolder(a.getX(), a.getY()).repaint();
						  	 	  }
								} 
							   combat = new Combat(name.playerUI.lb.getOwner(a.getX(), a.getY()), name, true);
						      done.addActionListener(this);  
						      lt.addActionListener(this);  
							   rt.addActionListener(this);  
							   up.addActionListener(this);  
							   dw.addActionListener(this);  
							   resetArmy(a);
							 } 
							 else
							 {
						      combatUnit = a;
							   combat = new Combat(name.playerUI.lb.getOwner(a.getX(), a.getY()), name, this);
						      a.combatMoves();
						    }
						  } 
					   }
					 }
				  }			   
				}//check if Army  
		    }    
//     moveClean();  
	  name.playerUI.map.validate();
   }//end moveDirection

   private void moveArmyGroup_0(Army a)
	{
      Player[] tempArray = name.getPlayerUI().getPlayers();
						    switch(a.getGroup())
							 {     
								case 1:
								  for(int k = 0; k < grp1.size(); k++)
								  {
								    Object temp = grp1.get(k);
									 if(temp.getClass().getName().equals("Settler"))
								    {
									   Settler s2 = (Settler)temp;
										if(s2.getGroup() == a.getGroup())
										{
										  s2.setBounds(name.playerUI.lb.getSettlerCount(a.getX()-1, a.getY()) + name.playerUI.lb.getArmyCount(a.getX()-1, a.getY()));
										  for(int i = 0; i < 2; i++)//number of players
									     {
											  if(tempArray[i] == name)
											  {
												  tempArray[i].playerUI.lb.getHolder(s2.getX(), s2.getY()).add(s2.getLabel(), new Integer(5));    
						                    tempArray[i].playerUI.lb.getHolder(s2.getX()-1, s2.getY()).add(s2.getLabel(), new Integer(5));  
						                    tempArray[i].playerUI.lb.subSettlerCount(s2.getX(), s2.getY()); 
									           if(((tempArray[i].playerUI.lb.getSettlerCount(s2.getX(), s2.getY()) + tempArray[i].playerUI.lb.getArmyCount(s2.getX(), s2.getY())) == 0) && !tempArray[i].playerUI.lb.getHasBuild(s2.getX(), s2.getY()))      
												    tempArray[i].playerUI.lb.setOwner(s2.getX(), s2.getY(), null);  
						     			        tempArray[i].playerUI.lb.addSettlerCount(s2.getX()-1, s2.getY());
						                    turnClean(s2.getX()-1, s2.getY());
											  }
											  else
											  {
												  JLabel l = new JLabel(s2.getIcon());
												  l.setBounds(s2.getCurrentbounds());
												  l.setName(s2.getLabelName());
						                    tempArray[i].playerUI.lb.getHolder(s2.getX()-1, s2.getY()).add(l, new Integer(5));  
						     			        tempArray[i].playerUI.lb.addSettlerCount(s2.getX()-1, s2.getY());
						                    leaveClean(s2.getX(), s2.getY(), s2);
				                          tempArray[i].unitSettlerLabels.add(l);
						                    turnClean(s2.getX()-1, s2.getY());
						                    tempArray[i].playerUI.lb.subSettlerCount(s2.getX(), s2.getY()); 
									           if(((tempArray[i].playerUI.lb.getSettlerCount(s2.getX(), s2.getY()) + tempArray[i].playerUI.lb.getArmyCount(s2.getX(), s2.getY())) == 0) && !tempArray[i].playerUI.lb.getHasBuild(s2.getX(), s2.getY()))      
												    tempArray[i].playerUI.lb.setOwner(s2.getX(), s2.getY(), null);  
				                       }
				                    }
					                 s2.setPosition(s2.getX()-1, s2.getY());
				                    turnClean(s2.getX()+1, s2.getY());
				                    turnClean(a.getX()+1, a.getY());
										  s2.takeMove();  
										}
								    }
								    else
									 {
									   Army s2 = (Army)temp;
										if(s2.getGroup() == a.getGroup() && s2 != a)
										{
										  s2.setBounds(name.playerUI.lb.getSettlerCount(s2.getX()-1, s2.getY()), name.playerUI.lb.getArmyCount(s2.getX()-1, s2.getY()));//spaceTotalUnits);
										  for(int i = 0; i < 2; i++)//number of players
									     {
											  if(tempArray[i] == name)
											  {
												  tempArray[i].playerUI.lb.getHolder(s2.getX(), s2.getY()).add(s2.getLabel(), new Integer(5));    
						                    tempArray[i].playerUI.lb.getHolder(s2.getX()-1, s2.getY()).add(s2.getLabel(), new Integer(5));  
						                    tempArray[i].playerUI.lb.subArmyCount(s2.getX(), s2.getY()); 
									           if(((tempArray[i].playerUI.lb.getSettlerCount(s2.getX(), s2.getY()) + tempArray[i].playerUI.lb.getArmyCount(s2.getX(), s2.getY())) == 0) && !tempArray[i].playerUI.lb.getHasBuild(s2.getX(), s2.getY()))      
												    tempArray[i].playerUI.lb.setOwner(s2.getX(), s2.getY(), null);  
						     			        tempArray[i].playerUI.lb.addArmyCount(s2.getX()-1, s2.getY());
						                    turnClean(s2.getX()-1, s2.getY());
											  }
											  else
											  {
												  JLabel l = new JLabel(s2.getIcon());
												  l.setBounds(s2.getCurrentbounds());
												  l.setName(s2.getLabelName());
						                    tempArray[i].playerUI.lb.getHolder(s2.getX()-1, s2.getY()).add(l, new Integer(5));  
						     			        tempArray[i].playerUI.lb.addArmyCount(s2.getX()-1, s2.getY());
						                    leaveClean(s2.getX(), s2.getY(), s2);
				                          tempArray[i].unitArmyLabels.add(l);
						                    turnClean(s2.getX()-1, s2.getY());
						                    tempArray[i].playerUI.lb.subArmyCount(s2.getX(), s2.getY()); 
									           if(((tempArray[i].playerUI.lb.getSettlerCount(s2.getX(), s2.getY()) + tempArray[i].playerUI.lb.getArmyCount(s2.getX(), s2.getY())) == 0) && !tempArray[i].playerUI.lb.getHasBuild(s2.getX(), s2.getY()))      
												    tempArray[i].playerUI.lb.setOwner(s2.getX(), s2.getY(), null);  
				                       }
				                    }
							           s2.setPosition(s2.getX()-1, s2.getY());
				                    turnClean(s2.getX()+1, s2.getY());
				                    turnClean(a.getX()+1, a.getY());
										  s2.takeMove();  
										}
									 }
                          }  
						        break;  
						    case 2:
								  for(int k = 0; k < grp2.size(); k++)
								  {
								    Object temp = grp2.get(k);
									 if(temp.getClass().getName().equals("Settler"))
								    {
									   Settler s2 = (Settler)temp;
										if(s2.getGroup() == a.getGroup())
										{
										  s2.setBounds(name.playerUI.lb.getSettlerCount(a.getX()-1, a.getY()) + name.playerUI.lb.getArmyCount(a.getX()-1, a.getY()));
										  for(int i = 0; i < 2; i++)//number of players
									     {
											  if(tempArray[i] == name)
											  {
												  tempArray[i].playerUI.lb.getHolder(s2.getX(), s2.getY()).add(s2.getLabel(), new Integer(5));    
						                    tempArray[i].playerUI.lb.getHolder(s2.getX()-1, s2.getY()).add(s2.getLabel(), new Integer(5));  
						                    tempArray[i].playerUI.lb.subSettlerCount(s2.getX(), s2.getY()); 
									           if(((tempArray[i].playerUI.lb.getSettlerCount(s2.getX(), s2.getY()) + tempArray[i].playerUI.lb.getArmyCount(s2.getX(), s2.getY())) == 0) && !tempArray[i].playerUI.lb.getHasBuild(s2.getX(), s2.getY()))      
												    tempArray[i].playerUI.lb.setOwner(s2.getX(), s2.getY(), null);  
						     			        tempArray[i].playerUI.lb.addSettlerCount(s2.getX()-1, s2.getY());
						                    turnClean(s2.getX()-1, s2.getY());
											  }
											  else
											  {
												  JLabel l = new JLabel(s2.getIcon());
												  l.setBounds(s2.getCurrentbounds());
												  l.setName(s2.getLabelName());
						                    tempArray[i].playerUI.lb.getHolder(s2.getX()-1, s2.getY()).add(l, new Integer(5));  
						     			        tempArray[i].playerUI.lb.addSettlerCount(s2.getX()-1, s2.getY());
						                    leaveClean(s2.getX(), s2.getY(), s2);
				                          tempArray[i].unitSettlerLabels.add(l);
						                    turnClean(s2.getX()-1, s2.getY());
						                    tempArray[i].playerUI.lb.subSettlerCount(s2.getX(), s2.getY()); 
									           if(((tempArray[i].playerUI.lb.getSettlerCount(s2.getX(), s2.getY()) + tempArray[i].playerUI.lb.getArmyCount(s2.getX(), s2.getY())) == 0) && !tempArray[i].playerUI.lb.getHasBuild(s2.getX(), s2.getY()))      
												    tempArray[i].playerUI.lb.setOwner(s2.getX(), s2.getY(), null);  
				                       }
				                    }
					                 s2.setPosition(s2.getX()-1, s2.getY());
				                    turnClean(s2.getX()+1, s2.getY());
				                    turnClean(a.getX()+1, a.getY());
										  s2.takeMove();  
										}
									 }
								    else
									 {
									   Army s2 = (Army)temp;
										if(s2.getGroup() == a.getGroup() && s2 != a)
										{
										  s2.setBounds(name.playerUI.lb.getSettlerCount(s2.getX()-1, s2.getY()), name.playerUI.lb.getArmyCount(s2.getX()-1, s2.getY()));//name.playerUI.lb.getSettlerCount(a.getX()-1, a.getY()) + name.playerUI.lb.getArmyCount(a.getX()-1, a.getY()));
										  for(int i = 0; i < 2; i++)//number of players
									     {
											  if(tempArray[i] == name)
											  {
												  tempArray[i].playerUI.lb.getHolder(s2.getX(), s2.getY()).add(s2.getLabel(), new Integer(5));    
						                    tempArray[i].playerUI.lb.getHolder(s2.getX()-1, s2.getY()).add(s2.getLabel(), new Integer(5));  
						                    tempArray[i].playerUI.lb.subArmyCount(s2.getX(), s2.getY()); 
									           if(((tempArray[i].playerUI.lb.getSettlerCount(s2.getX(), s2.getY()) + tempArray[i].playerUI.lb.getArmyCount(s2.getX(), s2.getY())) == 0) && !tempArray[i].playerUI.lb.getHasBuild(s2.getX(), s2.getY()))      
												    tempArray[i].playerUI.lb.setOwner(s2.getX(), s2.getY(), null);  
						     			        tempArray[i].playerUI.lb.addArmyCount(s2.getX()-1, s2.getY());
						                    turnClean(s2.getX()-1, s2.getY());
											  }
											  else
											  {
												  JLabel l = new JLabel(s2.getIcon());
												  l.setBounds(s2.getCurrentbounds());
												  l.setName(s2.getLabelName());
						                    tempArray[i].playerUI.lb.getHolder(s2.getX()-1, s2.getY()).add(l, new Integer(5));  
						     			        tempArray[i].playerUI.lb.addArmyCount(s2.getX()-1, s2.getY());
						                    leaveClean(s2.getX(), s2.getY(), s2);
				                          tempArray[i].unitArmyLabels.add(l);
						                    turnClean(s2.getX()-1, s2.getY());
						                    tempArray[i].playerUI.lb.subArmyCount(s2.getX(), s2.getY()); 
									           if(((tempArray[i].playerUI.lb.getSettlerCount(s2.getX(), s2.getY()) + tempArray[i].playerUI.lb.getArmyCount(s2.getX(), s2.getY())) == 0) && !tempArray[i].playerUI.lb.getHasBuild(s2.getX(), s2.getY()))      
												    tempArray[i].playerUI.lb.setOwner(s2.getX(), s2.getY(), null);  
				                       }
				                    }
							           s2.setPosition(s2.getX()-1, s2.getY());
				                    turnClean(s2.getX()+1, s2.getY());
				                    turnClean(a.getX()+1, a.getY());
										  s2.takeMove();  
										}
									 }
						        } 
						        break;  
						    case 3:
								  for(int k = 0; k < grp3.size(); k++)
								  {
								    Object temp = grp3.get(k);
									 if(temp.getClass().getName().equals("Settler"))
								    {
									   Settler s2 = (Settler)temp;
										if(s2.getGroup() == a.getGroup())
										{
										  s2.setBounds(name.playerUI.lb.getSettlerCount(a.getX()-1, a.getY()) + name.playerUI.lb.getArmyCount(a.getX()-1, a.getY()));
										  for(int i = 0; i < 2; i++)//number of players
									     {
											  if(tempArray[i] == name)
											  {
												  tempArray[i].playerUI.lb.getHolder(s2.getX(), s2.getY()).add(s2.getLabel(), new Integer(5));    
						                    tempArray[i].playerUI.lb.getHolder(s2.getX()-1, s2.getY()).add(s2.getLabel(), new Integer(5));  
						                    tempArray[i].playerUI.lb.subSettlerCount(s2.getX(), s2.getY()); 
									           if(((tempArray[i].playerUI.lb.getSettlerCount(s2.getX(), s2.getY()) + tempArray[i].playerUI.lb.getArmyCount(s2.getX(), s2.getY())) == 0) && !tempArray[i].playerUI.lb.getHasBuild(s2.getX(), s2.getY()))      
												    tempArray[i].playerUI.lb.setOwner(s2.getX(), s2.getY(), null);  
						     			        tempArray[i].playerUI.lb.addSettlerCount(s2.getX()-1, s2.getY());
						                    turnClean(s2.getX()-1, s2.getY());
											  }
											  else
											  {
												  JLabel l = new JLabel(s2.getIcon());
												  l.setBounds(s2.getCurrentbounds());
												  l.setName(s2.getLabelName());
						                    tempArray[i].playerUI.lb.getHolder(s2.getX()-1, s2.getY()).add(l, new Integer(5));  
						     			        tempArray[i].playerUI.lb.addSettlerCount(s2.getX()-1, s2.getY());
						                    leaveClean(s2.getX(), s2.getY(), s2);
				                          tempArray[i].unitSettlerLabels.add(l);
						                    turnClean(s2.getX()-1, s2.getY());
						                    tempArray[i].playerUI.lb.subSettlerCount(s2.getX(), s2.getY()); 
									           if(((tempArray[i].playerUI.lb.getSettlerCount(s2.getX(), s2.getY()) + tempArray[i].playerUI.lb.getArmyCount(s2.getX(), s2.getY())) == 0) && !tempArray[i].playerUI.lb.getHasBuild(s2.getX(), s2.getY()))      
												    tempArray[i].playerUI.lb.setOwner(s2.getX(), s2.getY(), null);  
				                       }
				                    }
					                 s2.setPosition(s2.getX()-1, s2.getY());
				                    turnClean(s2.getX()+1, s2.getY());
				                    turnClean(a.getX()+1, a.getY());
										  s2.takeMove();  
										}
									 }
								    else
									 {
									   Army s2 = (Army)temp;
										if(s2.getGroup() == a.getGroup() && s2 != a)
										{
										  s2.setBounds(name.playerUI.lb.getSettlerCount(s2.getX()-1, s2.getY()), name.playerUI.lb.getArmyCount(s2.getX()-1, s2.getY()));//name.playerUI.lb.getSettlerCount(a.getX()-1, a.getY()) + name.playerUI.lb.getArmyCount(a.getX()-1, a.getY()));
										  for(int i = 0; i < 2; i++)//number of players
									     {
											  if(tempArray[i] == name)
											  {
												  tempArray[i].playerUI.lb.getHolder(s2.getX(), s2.getY()).add(s2.getLabel(), new Integer(5));    
						                    tempArray[i].playerUI.lb.getHolder(s2.getX()-1, s2.getY()).add(s2.getLabel(), new Integer(5));  
						                    tempArray[i].playerUI.lb.subArmyCount(s2.getX(), s2.getY()); 
									           if(((tempArray[i].playerUI.lb.getSettlerCount(s2.getX(), s2.getY()) + tempArray[i].playerUI.lb.getArmyCount(s2.getX(), s2.getY())) == 0) && !tempArray[i].playerUI.lb.getHasBuild(s2.getX(), s2.getY()))      
												    tempArray[i].playerUI.lb.setOwner(s2.getX(), s2.getY(), null);  
						     			        tempArray[i].playerUI.lb.addArmyCount(s2.getX()-1, s2.getY());
						                    turnClean(s2.getX()-1, s2.getY());
											  }
											  else
											  {
												  JLabel l = new JLabel(s2.getIcon());
												  l.setBounds(s2.getCurrentbounds());
												  l.setName(s2.getLabelName());
						                    tempArray[i].playerUI.lb.getHolder(s2.getX()-1, s2.getY()).add(l, new Integer(5));  
						     			        tempArray[i].playerUI.lb.addArmyCount(s2.getX()-1, s2.getY());
						                    leaveClean(s2.getX(), s2.getY(), s2);
				                          tempArray[i].unitArmyLabels.add(l);
						                    turnClean(s2.getX()-1, s2.getY());
						                    tempArray[i].playerUI.lb.subArmyCount(s2.getX(), s2.getY()); 
									           if(((tempArray[i].playerUI.lb.getSettlerCount(s2.getX(), s2.getY()) + tempArray[i].playerUI.lb.getArmyCount(s2.getX(), s2.getY())) == 0) && !tempArray[i].playerUI.lb.getHasBuild(s2.getX(), s2.getY()))      
												    tempArray[i].playerUI.lb.setOwner(s2.getX(), s2.getY(), null);  
				                       }
				                    }
							           s2.setPosition(s2.getX()-1, s2.getY());
				                    turnClean(s2.getX()+1, s2.getY());
				                    turnClean(a.getX()+1, a.getY());
										  s2.takeMove();  
										}
									 }
						        } 
						        break;  
						    case 4:
								  for(int k = 0; k < grp4.size(); k++)
								  {
								    Object temp = grp4.get(k);
									 if(temp.getClass().getName().equals("Settler"))
								    {
									   Settler s2 = (Settler)temp;
										if(s2.getGroup() == a.getGroup())
										{
										  s2.setBounds(name.playerUI.lb.getSettlerCount(a.getX()-1, a.getY()) + name.playerUI.lb.getArmyCount(a.getX()-1, a.getY()));
										  for(int i = 0; i < 2; i++)//number of players
									     {
											  if(tempArray[i] == name)
											  {
												  tempArray[i].playerUI.lb.getHolder(s2.getX(), s2.getY()).add(s2.getLabel(), new Integer(5));    
						                    tempArray[i].playerUI.lb.getHolder(s2.getX()-1, s2.getY()).add(s2.getLabel(), new Integer(5));  
						                    tempArray[i].playerUI.lb.subSettlerCount(s2.getX(), s2.getY()); 
									           if(((tempArray[i].playerUI.lb.getSettlerCount(s2.getX(), s2.getY()) + tempArray[i].playerUI.lb.getArmyCount(s2.getX(), s2.getY())) == 0) && !tempArray[i].playerUI.lb.getHasBuild(s2.getX(), s2.getY()))      
												    tempArray[i].playerUI.lb.setOwner(s2.getX(), s2.getY(), null);  
						     			        tempArray[i].playerUI.lb.addSettlerCount(s2.getX()-1, s2.getY());
						                    turnClean(s2.getX()-1, s2.getY());
											  }
											  else
											  {
												  JLabel l = new JLabel(s2.getIcon());
												  l.setBounds(s2.getCurrentbounds());
												  l.setName(s2.getLabelName());
						                    tempArray[i].playerUI.lb.getHolder(s2.getX()-1, s2.getY()).add(l, new Integer(5));  
						     			        tempArray[i].playerUI.lb.addSettlerCount(s2.getX()-1, s2.getY());
						                    leaveClean(s2.getX(), s2.getY(), s2);
				                          tempArray[i].unitSettlerLabels.add(l);
						                    turnClean(s2.getX()-1, s2.getY());
						                    tempArray[i].playerUI.lb.subSettlerCount(s2.getX(), s2.getY()); 
									           if(((tempArray[i].playerUI.lb.getSettlerCount(s2.getX(), s2.getY()) + tempArray[i].playerUI.lb.getArmyCount(s2.getX(), s2.getY())) == 0) && !tempArray[i].playerUI.lb.getHasBuild(s2.getX(), s2.getY()))      
												    tempArray[i].playerUI.lb.setOwner(s2.getX(), s2.getY(), null);  
				                       }
				                    }
					                 s2.setPosition(s2.getX()-1, s2.getY());
				                    turnClean(s2.getX()+1, s2.getY());
				                    turnClean(a.getX()+1, a.getY());
										  s2.takeMove();  
										}
									 }
								    else
									 {
									   Army s2 = (Army)temp;
										if(s2.getGroup() == a.getGroup() && s2 != a)
										{
										  s2.setBounds(name.playerUI.lb.getSettlerCount(s2.getX()-1, s2.getY()), name.playerUI.lb.getArmyCount(s2.getX()-1, s2.getY()));//name.playerUI.lb.getSettlerCount(a.getX()-1, a.getY()) + name.playerUI.lb.getArmyCount(a.getX()-1, a.getY()));
										  for(int i = 0; i < 2; i++)//number of players
									     {
											  if(tempArray[i] == name)
											  {
												  tempArray[i].playerUI.lb.getHolder(s2.getX(), s2.getY()).add(s2.getLabel(), new Integer(5));    
						                    tempArray[i].playerUI.lb.getHolder(s2.getX()-1, s2.getY()).add(s2.getLabel(), new Integer(5));  
						                    tempArray[i].playerUI.lb.subArmyCount(s2.getX(), s2.getY()); 
									           if(((tempArray[i].playerUI.lb.getSettlerCount(s2.getX(), s2.getY()) + tempArray[i].playerUI.lb.getArmyCount(s2.getX(), s2.getY())) == 0) && !tempArray[i].playerUI.lb.getHasBuild(s2.getX(), s2.getY()))      
												    tempArray[i].playerUI.lb.setOwner(s2.getX(), s2.getY(), null);  
						     			        tempArray[i].playerUI.lb.addArmyCount(s2.getX()-1, s2.getY());
						                    turnClean(s2.getX()-1, s2.getY());
											  }
											  else
											  {
												  JLabel l = new JLabel(s2.getIcon());
												  l.setBounds(s2.getCurrentbounds());
												  l.setName(s2.getLabelName());
						                    tempArray[i].playerUI.lb.getHolder(s2.getX()-1, s2.getY()).add(l, new Integer(5));  
						     			        tempArray[i].playerUI.lb.addArmyCount(s2.getX()-1, s2.getY());
						                    leaveClean(s2.getX(), s2.getY(), s2);
				                          tempArray[i].unitArmyLabels.add(l);
						                    turnClean(s2.getX()-1, s2.getY());
						                    tempArray[i].playerUI.lb.subArmyCount(s2.getX(), s2.getY()); 
									           if(((tempArray[i].playerUI.lb.getSettlerCount(s2.getX(), s2.getY()) + tempArray[i].playerUI.lb.getArmyCount(s2.getX(), s2.getY())) == 0) && !tempArray[i].playerUI.lb.getHasBuild(s2.getX(), s2.getY()))      
												    tempArray[i].playerUI.lb.setOwner(s2.getX(), s2.getY(), null);  
				                       }
				                    }
							           s2.setPosition(s2.getX()-1, s2.getY());
				                    turnClean(s2.getX()+1, s2.getY());
				                    turnClean(a.getX()+1, a.getY());
										  s2.takeMove();  
										}
									 }
						        } 
						        break;  
                       }
						  }
	
     
   private void moveArmyGroup_1(Army a)
	{
      Player[] tempArray = name.getPlayerUI().getPlayers();
							 switch(a.getGroup())
							 {     
								case 1:
								  for(int k = 0; k < grp1.size(); k++)
								  {
								    Object temp = grp1.get(k);
									 if(temp.getClass().getName().equals("Settler"))
								    {
									   Settler s2 = (Settler)temp;
										if(s2.getGroup() == a.getGroup())
										{
//										  s2.setBounds(name.playerUI.lb.getSettlerCount(a.getX(), a.getY()-1) + name.playerUI.lb.getArmyCount(a.getX(), a.getY()-1));
										  for(int i = 0; i < 2; i++)//number of players
									     {
											  if(tempArray[i] == name)
											  {
												  tempArray[i].playerUI.lb.getHolder(s2.getX(), s2.getY()).add(s2.getLabel(), new Integer(5));    
						                    tempArray[i].playerUI.lb.getHolder(s2.getX(), s2.getY()-1).add(s2.getLabel(), new Integer(5));  
						                    tempArray[i].playerUI.lb.subSettlerCount(s2.getX(), s2.getY()); 
									           if(((tempArray[i].playerUI.lb.getSettlerCount(s2.getX(), s2.getY()) + tempArray[i].playerUI.lb.getArmyCount(s2.getX(), s2.getY())) == 0) && !tempArray[i].playerUI.lb.getHasBuild(s2.getX(), s2.getY()))      
												    tempArray[i].playerUI.lb.setOwner(s2.getX(), s2.getY(), null);  
						     			        tempArray[i].playerUI.lb.addSettlerCount(s2.getX(), s2.getY()-1);
//						                    turnClean(s2.getX(), s2.getY()-1);
											  }
											  else
											  {
												  JLabel l = new JLabel(s2.getIcon());
												  l.setBounds(s2.getCurrentbounds());
												  l.setName(s2.getLabelName());
						                    tempArray[i].playerUI.lb.getHolder(s2.getX(), s2.getY()-1).add(l, new Integer(5));  
						     			        tempArray[i].playerUI.lb.addSettlerCount(s2.getX(), s2.getY()-1);
						                    leaveClean(s2.getX(), s2.getY(), s2);
				                          tempArray[i].unitSettlerLabels.add(l);
//						                    turnClean(s2.getX(), s2.getY()-1);
						                    tempArray[i].playerUI.lb.subSettlerCount(s2.getX(), s2.getY()); 
									           if(((tempArray[i].playerUI.lb.getSettlerCount(s2.getX(), s2.getY()) + tempArray[i].playerUI.lb.getArmyCount(s2.getX(), s2.getY())) == 0) && !tempArray[i].playerUI.lb.getHasBuild(s2.getX(), s2.getY()))      
												    tempArray[i].playerUI.lb.setOwner(s2.getX(), s2.getY(), null);  
				                       }
				                    }
					                 s2.setPosition(s2.getX(), s2.getY()-1);
				                    turnClean(s2.getX(), s2.getY());
				                    turnClean(s2.getX(), s2.getY()+1);
										  s2.takeMove();  
										}
								    }
								    else
									 {
									   Army s2 = (Army)temp;
										if(s2.getGroup() == a.getGroup() && s2 != a)
										{
//										  s2.setBounds(name.playerUI.lb.getSettlerCount(s2.getX(), s2.getY()-1), name.playerUI.lb.getArmyCount(s2.getX(), s2.getY()-1));//name.playerUI.lb.getSettlerCount(a.getX()-1, a.getY()) + name.playerUI.lb.getArmyCount(a.getX()-1, a.getY()));
										  for(int i = 0; i < 2; i++)//number of players
									     {
											  if(tempArray[i] == name)
											  {
												  tempArray[i].playerUI.lb.getHolder(s2.getX(), s2.getY()).add(s2.getLabel(), new Integer(5));    
						                    tempArray[i].playerUI.lb.getHolder(s2.getX(), s2.getY()-1).add(s2.getLabel(), new Integer(5));  
						                    tempArray[i].playerUI.lb.subArmyCount(s2.getX(), s2.getY()); 
									           if(((tempArray[i].playerUI.lb.getSettlerCount(s2.getX(), s2.getY()) + tempArray[i].playerUI.lb.getArmyCount(s2.getX(), s2.getY())) == 0) && !tempArray[i].playerUI.lb.getHasBuild(s2.getX(), s2.getY()))      
												    tempArray[i].playerUI.lb.setOwner(s2.getX(), s2.getY(), null);  
						     			        tempArray[i].playerUI.lb.addArmyCount(s2.getX(), s2.getY()-1);
						                    turnClean(s2.getX(), s2.getY());
											  }
											  else
											  {
												  JLabel l = new JLabel(s2.getIcon());
												  l.setBounds(s2.getCurrentbounds());
												  l.setName(s2.getLabelName());
						                    tempArray[i].playerUI.lb.getHolder(s2.getX(), s2.getY()-1).add(l, new Integer(5));  
						     			        tempArray[i].playerUI.lb.addArmyCount(s2.getX(), s2.getY()-1);
						                    leaveClean(s2.getX(), s2.getY(), s2);
				                          tempArray[i].unitArmyLabels.add(l);
//						                    turnClean(s2.getX(), s2.getY()-1);
						                    tempArray[i].playerUI.lb.subArmyCount(s2.getX(), s2.getY()); 
									           if(((tempArray[i].playerUI.lb.getSettlerCount(s2.getX(), s2.getY()) + tempArray[i].playerUI.lb.getArmyCount(s2.getX(), s2.getY())) == 0) && !tempArray[i].playerUI.lb.getHasBuild(s2.getX(), s2.getY()))      
												    tempArray[i].playerUI.lb.setOwner(s2.getX(), s2.getY(), null);  
				                       }
				                    }
							           s2.setPosition(s2.getX(), s2.getY()-1);
				                    turnClean(s2.getX(), s2.getY());
				                    turnClean(a.getX(), a.getY()+1);
										  s2.takeMove();  
										}
									 }
                          }  
						        break;  
						    case 2:
								  for(int k = 0; k < grp2.size(); k++)
								  {
								    Object temp = grp2.get(k);
									 if(temp.getClass().getName().equals("Settler"))
								    {
									   Settler s2 = (Settler)temp;
										if(s2.getGroup() == a.getGroup())
										{
//										  s2.setBounds(name.playerUI.lb.getSettlerCount(a.getX(), a.getY()-1) + name.playerUI.lb.getArmyCount(a.getX(), a.getY()-1));
										  for(int i = 0; i < 2; i++)//number of players
									     {
											  if(tempArray[i] == name)
											  {
												  tempArray[i].playerUI.lb.getHolder(s2.getX(), s2.getY()).add(s2.getLabel(), new Integer(5));    
						                    tempArray[i].playerUI.lb.getHolder(s2.getX(), s2.getY()-1).add(s2.getLabel(), new Integer(5));  
						                    tempArray[i].playerUI.lb.subSettlerCount(s2.getX(), s2.getY()); 
									           if(((tempArray[i].playerUI.lb.getSettlerCount(s2.getX(), s2.getY()) + tempArray[i].playerUI.lb.getArmyCount(s2.getX(), s2.getY())) == 0) && !tempArray[i].playerUI.lb.getHasBuild(s2.getX(), s2.getY()))      
												    tempArray[i].playerUI.lb.setOwner(s2.getX(), s2.getY(), null);  
						     			        tempArray[i].playerUI.lb.addSettlerCount(s2.getX(), s2.getY()-1);
						                    turnClean(s2.getX(), s2.getY());
											  }
											  else
											  {
												  JLabel l = new JLabel(s2.getIcon());
												  l.setBounds(s2.getCurrentbounds());
												  l.setName(s2.getLabelName());
						                    tempArray[i].playerUI.lb.getHolder(s2.getX(), s2.getY()-1).add(l, new Integer(5));  
						     			        tempArray[i].playerUI.lb.addSettlerCount(s2.getX(), s2.getY()-1);
						                    leaveClean(s2.getX(), s2.getY(), s2);
				                          tempArray[i].unitSettlerLabels.add(l);
//						                    turnClean(s2.getX(), s2.getY()-1);
						                    tempArray[i].playerUI.lb.subSettlerCount(s2.getX(), s2.getY()); 
									           if(((tempArray[i].playerUI.lb.getSettlerCount(s2.getX(), s2.getY()) + tempArray[i].playerUI.lb.getArmyCount(s2.getX(), s2.getY())) == 0) && !tempArray[i].playerUI.lb.getHasBuild(s2.getX(), s2.getY()))      
												    tempArray[i].playerUI.lb.setOwner(s2.getX(), s2.getY(), null);  
				                       }
				                    }
					                 s2.setPosition(s2.getX(), s2.getY()-1);
				                    turnClean(s2.getX(), s2.getY());
				                    turnClean(s2.getX(), s2.getY()+1);
										  s2.takeMove();  
										}
									 }
								    else
									 {
									   Army s2 = (Army)temp;
										if(s2.getGroup() == a.getGroup() && s2 != a)
										{
//										  s2.setBounds(name.playerUI.lb.getSettlerCount(s2.getX(), s2.getY()-1), name.playerUI.lb.getArmyCount(s2.getX(), s2.getY()-1));//name.playerUI.lb.getSettlerCount(a.getX()-1, a.getY()) + name.playerUI.lb.getArmyCount(a.getX()-1, a.getY()));
										  for(int i = 0; i < 2; i++)//number of players
									     {
											  if(tempArray[i] == name)
											  {
												  tempArray[i].playerUI.lb.getHolder(s2.getX(), s2.getY()).add(s2.getLabel(), new Integer(5));    
						                    tempArray[i].playerUI.lb.getHolder(s2.getX(), s2.getY()-1).add(s2.getLabel(), new Integer(5));  
						                    tempArray[i].playerUI.lb.subArmyCount(s2.getX(), s2.getY()); 
									           if(((tempArray[i].playerUI.lb.getSettlerCount(s2.getX(), s2.getY()) + tempArray[i].playerUI.lb.getArmyCount(s2.getX(), s2.getY())) == 0) && !tempArray[i].playerUI.lb.getHasBuild(s2.getX(), s2.getY()))      
												    tempArray[i].playerUI.lb.setOwner(s2.getX(), s2.getY(), null);  
						     			        tempArray[i].playerUI.lb.addArmyCount(s2.getX(), s2.getY()-1);
						                    turnClean(s2.getX(), s2.getY());
											  }
											  else
											  {
												  JLabel l = new JLabel(s2.getIcon());
												  l.setBounds(s2.getCurrentbounds());
												  l.setName(s2.getLabelName());
						                    tempArray[i].playerUI.lb.getHolder(s2.getX(), s2.getY()-1).add(l, new Integer(5));  
						     			        tempArray[i].playerUI.lb.addArmyCount(s2.getX(), s2.getY()-1);
						                    leaveClean(s2.getX(), s2.getY(), s2);
				                          tempArray[i].unitArmyLabels.add(l);
//						                    turnClean(s2.getX(), s2.getY()-1);
						                    tempArray[i].playerUI.lb.subArmyCount(s2.getX(), s2.getY()); 
									           if(((tempArray[i].playerUI.lb.getSettlerCount(s2.getX(), s2.getY()) + tempArray[i].playerUI.lb.getArmyCount(s2.getX(), s2.getY())) == 0) && !tempArray[i].playerUI.lb.getHasBuild(s2.getX(), s2.getY()))      
												    tempArray[i].playerUI.lb.setOwner(s2.getX(), s2.getY(), null);  
				                       }
				                    }
							           s2.setPosition(s2.getX(), s2.getY()-1);
				                    turnClean(s2.getX(), s2.getY());
				                    turnClean(a.getX(), a.getY()+1);
										  s2.takeMove();  
										}
									 }
						        } 
						        break;  
						    case 3:
								  for(int k = 0; k < grp3.size(); k++)
								  {
								    Object temp = grp3.get(k);
									 if(temp.getClass().getName().equals("Settler"))
								    {
									   Settler s2 = (Settler)temp;
										if(s2.getGroup() == a.getGroup())
										{
//										  s2.setBounds(name.playerUI.lb.getSettlerCount(a.getX(), a.getY()-1) + name.playerUI.lb.getArmyCount(a.getX(), a.getY()-1));
										  for(int i = 0; i < 2; i++)//number of players
									     {
											  if(tempArray[i] == name)
											  {
												  tempArray[i].playerUI.lb.getHolder(s2.getX(), s2.getY()).add(s2.getLabel(), new Integer(5));    
						                    tempArray[i].playerUI.lb.getHolder(s2.getX(), s2.getY()-1).add(s2.getLabel(), new Integer(5));  
						                    tempArray[i].playerUI.lb.subSettlerCount(s2.getX(), s2.getY()); 
									           if(((tempArray[i].playerUI.lb.getSettlerCount(s2.getX(), s2.getY()) + tempArray[i].playerUI.lb.getArmyCount(s2.getX(), s2.getY())) == 0) && !tempArray[i].playerUI.lb.getHasBuild(s2.getX(), s2.getY()))      
												    tempArray[i].playerUI.lb.setOwner(s2.getX(), s2.getY(), null);  
						     			        tempArray[i].playerUI.lb.addSettlerCount(s2.getX(), s2.getY()-1);
						                    turnClean(s2.getX(), s2.getY());
											  }
											  else
											  {
												  JLabel l = new JLabel(s2.getIcon());
												  l.setBounds(s2.getCurrentbounds());
												  l.setName(s2.getLabelName());
						                    tempArray[i].playerUI.lb.getHolder(s2.getX(), s2.getY()-1).add(l, new Integer(5));  
						     			        tempArray[i].playerUI.lb.addSettlerCount(s2.getX(), s2.getY()-1);
						                    leaveClean(s2.getX(), s2.getY(), s2);
				                          tempArray[i].unitSettlerLabels.add(l);
//						                    turnClean(s2.getX(), s2.getY()-1);
						                    tempArray[i].playerUI.lb.subSettlerCount(s2.getX(), s2.getY()); 
									           if(((tempArray[i].playerUI.lb.getSettlerCount(s2.getX(), s2.getY()) + tempArray[i].playerUI.lb.getArmyCount(s2.getX(), s2.getY())) == 0) && !tempArray[i].playerUI.lb.getHasBuild(s2.getX(), s2.getY()))      
												    tempArray[i].playerUI.lb.setOwner(s2.getX(), s2.getY(), null);  
				                       }
				                    }
					                 s2.setPosition(s2.getX(), s2.getY()-1);
				                    turnClean(s2.getX(), s2.getY());
				                    turnClean(s2.getX(), s2.getY()+1);
										  s2.takeMove();  
										}
									 }
								    else
									 {
									   Army s2 = (Army)temp;
										if(s2.getGroup() == a.getGroup() && s2 != a)
										{
//										  s2.setBounds(name.playerUI.lb.getSettlerCount(s2.getX(), s2.getY()-1), name.playerUI.lb.getArmyCount(s2.getX(), s2.getY()-1));//name.playerUI.lb.getSettlerCount(a.getX()-1, a.getY()) + name.playerUI.lb.getArmyCount(a.getX()-1, a.getY()));
										  for(int i = 0; i < 2; i++)//number of players
									     {
											  if(tempArray[i] == name)
											  {
												  tempArray[i].playerUI.lb.getHolder(s2.getX(), s2.getY()).add(s2.getLabel(), new Integer(5));    
						                    tempArray[i].playerUI.lb.getHolder(s2.getX(), s2.getY()-1).add(s2.getLabel(), new Integer(5));  
						                    tempArray[i].playerUI.lb.subArmyCount(s2.getX(), s2.getY()); 
									           if(((tempArray[i].playerUI.lb.getSettlerCount(s2.getX(), s2.getY()) + tempArray[i].playerUI.lb.getArmyCount(s2.getX(), s2.getY())) == 0) && !tempArray[i].playerUI.lb.getHasBuild(s2.getX(), s2.getY()))      
												    tempArray[i].playerUI.lb.setOwner(s2.getX(), s2.getY(), null);  
						     			        tempArray[i].playerUI.lb.addArmyCount(s2.getX(), s2.getY()-1);
						                    turnClean(s2.getX(), s2.getY());
											  }
											  else
											  {
												  JLabel l = new JLabel(s2.getIcon());
												  l.setBounds(s2.getCurrentbounds());
												  l.setName(s2.getLabelName());
						                    tempArray[i].playerUI.lb.getHolder(s2.getX(), s2.getY()-1).add(l, new Integer(5));  
						     			        tempArray[i].playerUI.lb.addArmyCount(s2.getX(), s2.getY()-1);
						                    leaveClean(s2.getX(), s2.getY(), s2);
				                          tempArray[i].unitArmyLabels.add(l);
//						                    turnClean(s2.getX(), s2.getY()-1);
						                    tempArray[i].playerUI.lb.subArmyCount(s2.getX(), s2.getY()); 
									           if(((tempArray[i].playerUI.lb.getSettlerCount(s2.getX(), s2.getY()) + tempArray[i].playerUI.lb.getArmyCount(s2.getX(), s2.getY())) == 0) && !tempArray[i].playerUI.lb.getHasBuild(s2.getX(), s2.getY()))      
												    tempArray[i].playerUI.lb.setOwner(s2.getX(), s2.getY(), null);  
				                       }
				                    }
							           s2.setPosition(s2.getX(), s2.getY()-1);
				                    turnClean(s2.getX(), s2.getY());
				                    turnClean(a.getX(), a.getY()+1);
										  s2.takeMove();  
										}
									 }
						        } 
						        break;  
						    case 4:
								  for(int k = 0; k < grp4.size(); k++)
								  {
								    Object temp = grp4.get(k);
									 if(temp.getClass().getName().equals("Settler"))
								    {
									   Settler s2 = (Settler)temp;
										if(s2.getGroup() == a.getGroup())
										{
//										  s2.setBounds(name.playerUI.lb.getSettlerCount(a.getX(), a.getY()-1) + name.playerUI.lb.getArmyCount(a.getX(), a.getY()-1));
										  for(int i = 0; i < 2; i++)//number of players
									     {
											  if(tempArray[i] == name)
											  {
												  tempArray[i].playerUI.lb.getHolder(s2.getX(), s2.getY()).add(s2.getLabel(), new Integer(5));    
						                    tempArray[i].playerUI.lb.getHolder(s2.getX(), s2.getY()-1).add(s2.getLabel(), new Integer(5));  
						                    tempArray[i].playerUI.lb.subSettlerCount(s2.getX(), s2.getY()); 
									           if(((tempArray[i].playerUI.lb.getSettlerCount(s2.getX(), s2.getY()) + tempArray[i].playerUI.lb.getArmyCount(s2.getX(), s2.getY())) == 0) && !tempArray[i].playerUI.lb.getHasBuild(s2.getX(), s2.getY()))      
												    tempArray[i].playerUI.lb.setOwner(s2.getX(), s2.getY(), null);  
						     			        tempArray[i].playerUI.lb.addSettlerCount(s2.getX(), s2.getY()-1);
						                    turnClean(s2.getX(), s2.getY());
											  }
											  else
											  {
												  JLabel l = new JLabel(s2.getIcon());
												  l.setBounds(s2.getCurrentbounds());
												  l.setName(s2.getLabelName());
						                    tempArray[i].playerUI.lb.getHolder(s2.getX(), s2.getY()-1).add(l, new Integer(5));  
						     			        tempArray[i].playerUI.lb.addSettlerCount(s2.getX(), s2.getY()-1);
						                    leaveClean(s2.getX(), s2.getY(), s2);
				                          tempArray[i].unitSettlerLabels.add(l);
////						                    turnClean(s2.getX(), s2.getY()-1);
						                    tempArray[i].playerUI.lb.subSettlerCount(s2.getX(), s2.getY()); 
									           if(((tempArray[i].playerUI.lb.getSettlerCount(s2.getX(), s2.getY()) + tempArray[i].playerUI.lb.getArmyCount(s2.getX(), s2.getY())) == 0) && !tempArray[i].playerUI.lb.getHasBuild(s2.getX(), s2.getY()))      
												    tempArray[i].playerUI.lb.setOwner(s2.getX(), s2.getY(), null);  
				                       }
				                    }
					                 s2.setPosition(s2.getX(), s2.getY()-1);
				                    turnClean(s2.getX(), s2.getY());
				                    turnClean(a.getX(), a.getY()+1);
										  s2.takeMove();  
										}
									 }
								    else
									 {
									   Army s2 = (Army)temp;
										if(s2.getGroup() == a.getGroup() && s2 != a)
										{
//										  s2.setBounds(name.playerUI.lb.getSettlerCount(s2.getX(), s2.getY()-1), name.playerUI.lb.getArmyCount(s2.getX(), s2.getY()-1));//name.playerUI.lb.getSettlerCount(a.getX()-1, a.getY()) + name.playerUI.lb.getArmyCount(a.getX()-1, a.getY()));
										  for(int i = 0; i < 2; i++)//number of players
									     {
											  if(tempArray[i] == name)
											  {
												  tempArray[i].playerUI.lb.getHolder(s2.getX(), s2.getY()).add(s2.getLabel(), new Integer(5));    
						                    tempArray[i].playerUI.lb.getHolder(s2.getX(), s2.getY()-1).add(s2.getLabel(), new Integer(5));  
						                    tempArray[i].playerUI.lb.subArmyCount(s2.getX(), s2.getY()); 
									           if(((tempArray[i].playerUI.lb.getSettlerCount(s2.getX(), s2.getY()) + tempArray[i].playerUI.lb.getArmyCount(s2.getX(), s2.getY())) == 0) && !tempArray[i].playerUI.lb.getHasBuild(s2.getX(), s2.getY()))      
												    tempArray[i].playerUI.lb.setOwner(s2.getX(), s2.getY(), null);  
						     			        tempArray[i].playerUI.lb.addArmyCount(s2.getX(), s2.getY()-1);
						                    turnClean(s2.getX(), s2.getY());
											  }
											  else
											  {
												  JLabel l = new JLabel(s2.getIcon());
												  l.setBounds(s2.getCurrentbounds());
												  l.setName(s2.getLabelName());
						                    tempArray[i].playerUI.lb.getHolder(s2.getX(), s2.getY()-1).add(l, new Integer(5));  
						     			        tempArray[i].playerUI.lb.addArmyCount(s2.getX(), s2.getY()-1);
						                    leaveClean(s2.getX(), s2.getY(), s2);
				                          tempArray[i].unitArmyLabels.add(l);
						                    turnClean(s2.getX(), s2.getY()-1);
						                    tempArray[i].playerUI.lb.subArmyCount(s2.getX(), s2.getY()); 
									           if(((tempArray[i].playerUI.lb.getSettlerCount(s2.getX(), s2.getY()) + tempArray[i].playerUI.lb.getArmyCount(s2.getX(), s2.getY())) == 0) && !tempArray[i].playerUI.lb.getHasBuild(s2.getX(), s2.getY()))      
												    tempArray[i].playerUI.lb.setOwner(s2.getX(), s2.getY(), null);  
				                       }
				                    }
							           s2.setPosition(s2.getX(), s2.getY()-1);
				                    turnClean(s2.getX(), s2.getY());
				                    turnClean(a.getX(), a.getY()+1);
										  s2.takeMove();  
										}
									 }
						        } 
						        break;  
                       }
						  }
	
	private void moveArmyGroup_2(Army a)
	{
	
	}

   private void moveArmyGroup_3(Army a)
	{
	
	}
	
	private void leaveClean(int x, int y, Object obj)
	{
     Object temp = obj;
     Player[] tempArray = name.getPlayerUI().getPlayers();

	  if(temp.getClass().getName() == "Settler")
	  {
       for(int k = 0; k < 2; k++)//number of players
		 {
          if(name != tempArray[k])
          {
             Settler s = (Settler)temp;
             JLabel l = new JLabel();

				 for(int i = 0; !s.getLabelName().equals(l.getName());)
             {        
				   l = (JLabel)tempArray[k].unitSettlerLabels.get(i);				   
					if(s.getLabelName().equals(l.getName()))
					{
//System.out.println("Settler COMPARED NAME "+l.getName() +" "+s.getLabelName());  
			         tempArray[k].playerUI.lb.getHolder(x, y).remove(tempArray[k].playerUI.lb.getHolder(x, y).getIndexOf((JLabel)tempArray[k].unitSettlerLabels.remove(i)));
               }
					else
					  i++;  
             }
//System.out.println("Settler "+ tempArray[k].playerUI.lb.getSettlerCount(x, y));          
          }
		 } 
     }
     else if(temp.getClass().getName() == "Army")
 	  {
        for(int k = 0; k < 2; k++)//number of players
	     {
          if(name != tempArray[k])
          {
             Army a = (Army)temp;
             JLabel l = new JLabel();
//		        if(tempArray[k].playerUI.lb.getHolder(x, y).getIndexOf((JLabel)tempArray[k].unitArmyLabels.remove(0)) >= 0)
				 for(int i = 0; !a.getLabelName().equals(l.getName());)
             {        
				   l = (JLabel)tempArray[k].unitArmyLabels.get(i);				   
					if(a.getLabelName().equals(l.getName()))
					{
//System.out.println("ARMY COMPARED NAME "+l.getName() +" "+a.getLabelName());  

					  tempArray[k].playerUI.lb.getHolder(x, y).remove(tempArray[k].playerUI.lb.getHolder(x, y).getIndexOf((JLabel)tempArray[k].unitArmyLabels.remove(i)));
					}
			      else 
					  i++;  
				 }
			 }
		  }
     }
   }

   private void turnClean(int x, int y)
	{
     Player[] tempArray = name.getPlayerUI().getPlayers();

     Vector tempSett = new Vector(0, 1);   
  	  for(int i = 0; i < moveEnd.size(); i++)
     {
       Object temp = moveEnd.get(i);
	    if(temp.getClass().getName() == "Settler")
		 {
	      Settler se = (Settler)temp;
		   if(se.getX() == x && se.getY() == y)
		     tempSett.add(se);	 
		 }
	  }    

     for(int i = 0; i < tempSett.size(); i++)
	  {  
		 Settler se = (Settler)tempSett.get(i);
	    se.setBounds(20*i, 00, 25, 25);
       for(int k = 0; k < 2; k++)//number of players
		 {
	       if(name != tempArray[k])    
          {
				 JLabel l = (JLabel)tempArray[k].unitSettlerLabels.get(i);
			    l.setBounds(se.getCurrentbounds());  
	 	       tempArray[k].playerUI.lb.getHolder(x, y).validate();
	          tempArray[k].playerUI.lb.getHolder(x, y).repaint();

//			    tempArray[k].playerUI.lb.getHolder(se.getX(), se.getY()).remove(CivGUI.players[k].playerUI.lb.getHolder(x, y).getIndexOf((JLabel)CivGUI.players[k].unitSettlerLabels.remove(0)));
//	          CivGUI.players[k].playerUI.lb.getHolder(se.getX(), se.getY()).add(l, new Integer(5));
//             CivGUI.players[k].unitSettlerLabels.add(l);
          }
		 } 
//		 JLabel temp = (JLabel)labelSettVector.lastElement();
//       temp.setBounds(se.getCurrentbounds());
 	  } 

	  Vector tempArmy = new Vector(0, 1);   
	  for(int i = 0; i < moveEnd.size(); i++)
	  { 
	    Object temp = moveEnd.get(i);
	    if(temp.getClass().getName() == "Army")
	 	 {
	      Army arm = (Army)temp;
			if(arm.getX() == x && arm.getY() == y)
			  tempArmy.add(arm);	 
		 }  
     }
	  
     for(int i = 0; i < tempArmy.size(); i++)
     {  
		 Army arm = (Army)tempArmy.get(i);
//System.out.println("ARMY "+i+" "+tempArmy.size()+ " "+moveEnd.size());          
		 switch(i+(name.playerUI.lb.getSettlerCount(arm.getX(), arm.getY())))
		 {
		   case 0:
		     arm.setBounds(00, 00, 25, 25);
           for(int k = 0; k < 2; k++)//number of players
		     {
            if(name != tempArray[k]) 
            {
		        JLabel l = getPlayerArmyLabel(tempArray[k], arm);
			     l.setBounds(arm.getCurrentbounds());  
	 	        tempArray[k].playerUI.lb.getHolder(x, y).validate();
	           tempArray[k].playerUI.lb.getHolder(x, y).repaint();
//		        if(tempArray[k].playerUI.lb.getHolder(x, y).getIndexOf((JLabel)tempArray[k].unitArmyLabels.remove(0)) > 0)
//                tempArray[k].playerUI.lb.getHolder(arm.getX(), arm.getY()).remove(tempArray[k].playerUI.lb.getHolder(x, y).getIndexOf((JLabel)tempArray[k].unitArmyLabels.remove(0)));
//	           tempArray[k].playerUI.lb.getHolder(arm.getX(), arm.getY()).add(l, new Integer(5));
  //            tempArray[k].unitArmyLabels.add(l);
			   }
			  }
			  break;
		   case 1:
		     arm.setBounds(20, 00, 25, 25);
           for(int k = 0; k < 2; k++)//number of players
		     {
            if(name != tempArray[k]) 
            {
		        JLabel l = getPlayerArmyLabel(tempArray[k], arm);
			     l.setBounds(arm.getCurrentbounds());  
	 	        tempArray[k].playerUI.lb.getHolder(x, y).validate();
	           tempArray[k].playerUI.lb.getHolder(x, y).repaint();
//		        tempArray[k].playerUI.lb.getHolder(arm.getX(), arm.getY()).remove(0);
//	           tempArray[k].playerUI.lb.getHolder(arm.getX(), arm.getY()).add(l, new Integer(5));
  //            tempArray[k].unitArmyLabels.add(l);
			   }
			  }
			  break;
		   case 2:
		     arm.setBounds(40, 00, 25, 25);
           for(int k = 0; k < 2; k++)//number of players
		     {
            if(name != tempArray[k]) 
            {
		        JLabel l = getPlayerArmyLabel(tempArray[k], arm);
			     l.setBounds(arm.getCurrentbounds());  
	 	        tempArray[k].playerUI.lb.getHolder(x, y).validate();
	           tempArray[k].playerUI.lb.getHolder(x, y).repaint();
//		        tempArray[k].playerUI.lb.getHolder(arm.getX(), arm.getY()).remove(tempArray[k].playerUI.lb.getHolder(x, y).getIndexOf((JLabel)tempArray[k].unitArmyLabels.remove(0)));
//	           tempArray[k].playerUI.lb.getHolder(arm.getX(), arm.getY()).add(l, new Integer(5));
  //            tempArray[k].unitArmyLabels.add(l);
			   }
			  }
			  break;
		   case 3:
		     arm.setBounds(00, 20, 25, 25);
           for(int k = 0; k < 2; k++)//number of players
		     {
            if(name != tempArray[k]) 
            {
		        JLabel l = getPlayerArmyLabel(tempArray[k], arm);
			     l.setBounds(arm.getCurrentbounds());  
	 	        tempArray[k].playerUI.lb.getHolder(x, y).validate();
	           tempArray[k].playerUI.lb.getHolder(x, y).repaint();
//		        tempArray[k].playerUI.lb.getHolder(arm.getX(), arm.getY()).remove(tempArray[k].playerUI.lb.getHolder(x, y).getIndexOf((JLabel)tempArray[k].unitArmyLabels.remove(0)));
//	           tempArray[k].playerUI.lb.getHolder(arm.getX(), arm.getY()).add(l, new Integer(5));
  //            tempArray[k].unitArmyLabels.add(l);
			   }
			  }
			  break;
		   case 4:
		     arm.setBounds(20, 20, 25, 25);
           for(int k = 0; k < 2; k++)//number of players
		     {
            if(name != tempArray[k]) 
            {
		        JLabel l = getPlayerArmyLabel(tempArray[k], arm);
			     l.setBounds(arm.getCurrentbounds());  
	 	        tempArray[k].playerUI.lb.getHolder(x, y).validate();
	           tempArray[k].playerUI.lb.getHolder(x, y).repaint();
//		        tempArray[k].playerUI.lb.getHolder(arm.getX(), arm.getY()).remove(tempArray[k].playerUI.lb.getHolder(x, y).getIndexOf((JLabel)tempArray[k].unitArmyLabels.remove(0)));
//	           tempArray[k].playerUI.lb.getHolder(arm.getX(), arm.getY()).add(l, new Integer(5));
  //            tempArray[k].unitArmyLabels.add(l);
			   }
			  }
			  break;
		   case 5:
		     arm.setBounds(40, 20, 25, 25);
           for(int k = 0; k < 2; k++)//number of players
		     {
            if(name != tempArray[k]) 
            {
		        JLabel l = getPlayerArmyLabel(tempArray[k], arm);
			     l.setBounds(arm.getCurrentbounds());  
	 	        tempArray[k].playerUI.lb.getHolder(x, y).validate();
	           tempArray[k].playerUI.lb.getHolder(x, y).repaint();
//		        tempArray[k].playerUI.lb.getHolder(arm.getX(), arm.getY()).remove(tempArray[k].playerUI.lb.getHolder(x, y).getIndexOf((JLabel)tempArray[k].unitArmyLabels.remove(0)));
//	           tempArray[k].playerUI.lb.getHolder(arm.getX(), arm.getY()).add(l, new Integer(5));
  //            tempArray[k].unitArmyLabels.add(l);
			   }
			  }
			  break;
		   case 6:
		     arm.setBounds(00, 40, 25, 25);
           for(int k = 0; k < 2; k++)//number of players
		     {
            if(name != tempArray[k]) 
            {
		        JLabel l = getPlayerArmyLabel(tempArray[k], arm);
			     l.setBounds(arm.getCurrentbounds());  
	 	        tempArray[k].playerUI.lb.getHolder(x, y).validate();
	           tempArray[k].playerUI.lb.getHolder(x, y).repaint();
//		        tempArray[k].playerUI.lb.getHolder(arm.getX(), arm.getY()).remove(tempArray[k].playerUI.lb.getHolder(x, y).getIndexOf((JLabel)tempArray[k].unitArmyLabels.remove(0)));
//	           tempArray[k].playerUI.lb.getHolder(arm.getX(), arm.getY()).add(l, new Integer(5));
  //            tempArray[k].unitArmyLabels.add(l);
			   }
			  }
			  break;
		   case 7:
		     arm.setBounds(20, 40, 25, 25);
           for(int k = 0; k < 2; k++)//number of players
		     {
            if(name != tempArray[k]) 
            {
		        JLabel l = getPlayerArmyLabel(tempArray[k], arm);
			     l.setBounds(arm.getCurrentbounds());  
	 	        tempArray[k].playerUI.lb.getHolder(x, y).validate();
	           tempArray[k].playerUI.lb.getHolder(x, y).repaint();
	//	        tempArray[k].playerUI.lb.getHolder(arm.getX(), arm.getY()).remove(tempArray[k].playerUI.lb.getHolder(x, y).getIndexOf((JLabel)tempArray[k].unitArmyLabels.remove(0)));
//	           tempArray[k].playerUI.lb.getHolder(arm.getX(), arm.getY()).add(l, new Integer(5));
  //            tempArray[k].unitArmyLabels.add(l);
			   }
			  }
			  break;
		   case 8:
		     arm.setBounds(40, 40, 25, 25);
           for(int k = 0; k < 2; k++)//number of players
		     {
            if(name != tempArray[k]) 
            {
		        JLabel l = getPlayerArmyLabel(tempArray[k], arm);
			     l.setBounds(arm.getCurrentbounds());  
	 	        tempArray[k].playerUI.lb.getHolder(x, y).validate();
	           tempArray[k].playerUI.lb.getHolder(x, y).repaint();
//		        tempArray[k].playerUI.lb.getHolder(arm.getX(), arm.getY()).remove(tempArray[k].playerUI.lb.getHolder(x, y).getIndexOf((JLabel)tempArray[k].unitArmyLabels.remove(0)));
//	           tempArray[k].playerUI.lb.getHolder(arm.getX(), arm.getY()).add(l, new Integer(5));
  //            tempArray[k].unitArmyLabels.add(l);
			   }
			  }
			  break;
		 }
     }
     for(int k = 0; k < 2; k++)//number of players
     {
	 	  tempArray[k].playerUI.map.validate();
	     tempArray[k].playerUI.map.repaint();
     }  
	}
	  
	private void turnClean(Object o)
	{
     if(o.getClass().getName() == "Settler")    
     {
		 JLabel temp = (JLabel)labelSettVector.remove(0);
		 Settler s = (Settler)o; 
       s.setBounds(name.playerUI.lb.getSettlerCount(s.getX(), s.getY())+lb.getArmyCount(s.getX(), s.getY()));
       temp.setBounds(s.getCurrentbounds());
	  }
	  else
	  {
		 JLabel temp = (JLabel)labelArmyVector.remove(0);
		 Army a = (Army)o; 
		 a.setBounds(name.playerUI.lb.getSettlerCount(a.getX(), a.getY()), name.playerUI.lb.getArmyCount(a.getX(), a.getY()));
       temp.setBounds(a.getCurrentbounds());
		
	  }
	  name.playerUI.map.validate();
	  name.playerUI.map.repaint();
	}

	private void moveClean(Object obj)
	{
	  Vector tempSettlers = new Vector(0, 1);
	  Vector tempArmies = new Vector(0, 1);
     Settler s = null, tempSett = null;
	  Army a = null, tempArmy = null;
	  
     if(obj.getClass().getName() == "Settler")
	    s = (Settler)obj;
	  else
	    a = (Army)obj;	 
	  for(int i = 0; i < size; i++)
	  { 
       Object temp = moveEnd.get(i);
       if(temp.getClass().getName() == "Settler")
	      tempSett = (Settler)temp;
	    else
	      tempArmy = (Army)temp;	 
	 	 
		 if(s != null)
       {
			if(tempSett != null)
			{
			  if(s != tempSett && (tempSett.getX() == s.getX() && tempSett.getY() == s.getY()))
			    tempSettlers.add(tempSett);	    
	      }  
         else
			{
			  if(tempArmy.getX() == s.getX() && tempArmy.getY() == s.getY())
			    tempArmies.add(tempArmy);	    
			}
		 }
		 else
		 {
			if(tempSett != null)
			{
			  if(tempSett.getX() == a.getX() && tempSett.getY() == a.getY())
			    tempSettlers.add(tempSett);	    
	      }  
         else
			{
			  if(a != tempArmy && (tempArmy.getX() == a.getX() && tempArmy.getY() == a.getY()))
			    tempArmies.add(tempArmy);	    
			}
	    }
	  }
     int k, i, j;
     Player[] tempArray = name.getPlayerUI().getPlayers();
     for(k = 0; k < tempArmies.size(); k++)
	  {
	    for(i = 0; i < tempSettlers.size(); i++)
		 {
			Settler temp = (Settler)tempSettlers.get(i);
			temp.setBounds(0+(20*i), 00, 25, 25);
         for(int m = 0; m < 2; m++)//number of players
			{
		     if(name != tempArray[m])
		     {
		        JLabel l = new JLabel();
			     
				  for(int n = 0; !temp.getLabelName().equals(l.getName());)
		        {        
					  l = (JLabel)tempArray[m].unitSettlerLabels.get(n);				   
					  if(temp.getLabelName().equals(l.getName()))
					  {
					    l.setBounds(temp.getCurrentbounds());    
		           }
					  else
					    n++;  
		        }
		//System.out.println("Settler "+ CivGUI.players[k].playerUI.lb.getSettlerCount(x, y));          
		     }
	 		} 
		 }
		 Army temp = (Army)tempArmies.get(k);
       switch(i)
		 {
		   case 0:
		     temp.setBounds(0+(20*i), 00, 25, 25);
	        for(int m = 0; m < 2; m++)//number of players
			  {
		       if(name != tempArray[m])
		       {
		          JLabel l = new JLabel();
		
				    for(int n = 0; !temp.getLabelName().equals(l.getName());)
		          {        
						 l = (JLabel)tempArray[m].unitArmyLabels.get(n);				   
						 if(temp.getLabelName().equals(l.getName()))
						 {
					      l.setBounds(temp.getCurrentbounds());    
	 	               tempArray[m].playerUI.lb.getHolder(temp.getX(), temp.getY()).validate();
	                  tempArray[m].playerUI.lb.getHolder(temp.getX(), temp.getY()).repaint();
		//System.out.println("Moveturn 0");          
		             }
					  else
					    n++;  
		          }
		//System.out.println("Settler "+ CivGUI.players[k].playerUI.lb.getSettlerCount(x, y));          
		       }
	 		  } 
			  break;
		   case 1:
		     temp.setBounds(0+(20*i), 00, 25, 25);
	        for(int m = 0; m < 2; m++)//number of players
			  {
		       if(name != tempArray[m])
		       {
		          JLabel l = new JLabel();
		
				    for(int n = 0; !temp.getLabelName().equals(l.getName());)
		          {        
						 l = (JLabel)tempArray[m].unitArmyLabels.get(n);				   
						 if(temp.getLabelName().equals(l.getName()))
						 {
					      l.setBounds(temp.getCurrentbounds());    
	 	               tempArray[m].playerUI.lb.getHolder(temp.getX(), temp.getY()).validate();
	                  tempArray[m].playerUI.lb.getHolder(temp.getX(), temp.getY()).repaint();
		//System.out.println("Moveturn 1");          
		             }
					  else
					    n++;  
		          }
		       }
	 		  } 
			  break;
		   case 2:
		     temp.setBounds(0+(20*i), 00, 25, 25);
	        for(int m = 0; m < 2; m++)//number of players
			  {
		       if(name != tempArray[m])
		       {
		          JLabel l = new JLabel();
		
				    for(int n = 0; !temp.getLabelName().equals(l.getName());)
		          {        
						 l = (JLabel)tempArray[m].unitArmyLabels.get(n);				   
						 if(temp.getLabelName().equals(l.getName()))
						 {
					      l.setBounds(temp.getCurrentbounds());    
	 	               tempArray[m].playerUI.lb.getHolder(temp.getX(), temp.getY()).validate();
	                  tempArray[m].playerUI.lb.getHolder(temp.getX(), temp.getY()).repaint();
		//System.out.println("Moveturn 2");          
		             }
					  else
					    n++;  
		          }
		//System.out.println("Settler "+ CivGUI.players[k].playerUI.lb.getSettlerCount(x, y));          
		       }
	 		  } 
			  break;
		   case 3:
		     temp.setBounds(0+(20*i), 20, 25, 25);
	        for(int m = 0; m < 2; m++)//number of players
			  {
		       if(name != tempArray[m])
		       {
		          JLabel l = new JLabel();
		
				    for(int n = 0; !temp.getLabelName().equals(l.getName());)
		          {        
						 l = (JLabel)tempArray[m].unitArmyLabels.get(n);				   
						 if(temp.getLabelName().equals(l.getName()))
						 {
					      l.setBounds(temp.getCurrentbounds());    
		             }
					  else
					    n++;  
		          }
		//System.out.println("Settler "+ CivGUI.players[k].playerUI.lb.getSettlerCount(x, y));          
		       }
	 		  } 
			  break;
		   case 4:
		     temp.setBounds(0+(20*i), 20, 25, 25);
	        for(int m = 0; m < 2; m++)//number of players
			  {
		       if(name != tempArray[m])
		       {
		          JLabel l = new JLabel();
		
				    for(int n = 0; !temp.getLabelName().equals(l.getName());)
		          {        
						 l = (JLabel)tempArray[m].unitArmyLabels.get(n);				   
						 if(temp.getLabelName().equals(l.getName()))
						 {
					      l.setBounds(temp.getCurrentbounds());    
		             }
					  else
					    n++;  
		          }
		//System.out.println("Settler "+ CivGUI.players[k].playerUI.lb.getSettlerCount(x, y));          
		       }
	 		  } 
			  break;
		   case 5:
		     temp.setBounds(0+(20*i), 20, 25, 25);
	        for(int m = 0; m < 2; m++)//number of players
			  {
		       if(name != tempArray[m])
		       {
		          JLabel l = new JLabel();
		
				    for(int n = 0; !temp.getLabelName().equals(l.getName());)
		          {        
						 l = (JLabel)tempArray[m].unitArmyLabels.get(n);				   
						 if(temp.getLabelName().equals(l.getName()))
						 {
					      l.setBounds(temp.getCurrentbounds());    
		             }
					  else
					    n++;  
		          }
		//System.out.println("Settler "+ CivGUI.players[k].playerUI.lb.getSettlerCount(x, y));          
		       }
	 		  } 
			  break;
		   case 6:
		     temp.setBounds(0+(20*i), 20, 25, 25);
	        for(int m = 0; m < 2; m++)//number of players
			  {
		       if(name != tempArray[m])
		       {
		          JLabel l = new JLabel();
		
				    for(int n = 0; !temp.getLabelName().equals(l.getName());)
		          {        
						 l = (JLabel)tempArray[m].unitArmyLabels.get(n);				   
						 if(temp.getLabelName().equals(l.getName()))
						 {
					      l.setBounds(temp.getCurrentbounds());    
		             }
					  else
					    n++;  
		          }
		//System.out.println("Settler "+ CivGUI.players[k].playerUI.lb.getSettlerCount(x, y));          
		       }
	 		  } 
			  break;
		   case 7:
		     temp.setBounds(0+(20*i), 40, 25, 25);
	        for(int m = 0; m < 2; m++)//number of players
			  {
		       if(name != tempArray[m])
		       {
		          JLabel l = new JLabel();
		
				    for(int n = 0; !temp.getLabelName().equals(l.getName());)
		          {        
						 l = (JLabel)tempArray[m].unitArmyLabels.get(n);				   
						 if(temp.getLabelName().equals(l.getName()))
						 {
					      l.setBounds(temp.getCurrentbounds());    
		             }
					  else
					    n++;  
		          }
		//System.out.println("Settler "+ CivGUI.players[k].playerUI.lb.getSettlerCount(x, y));          
		       }
	 		  } 
			  break;
		   case 8:
		     temp.setBounds(0+(20*i), 40, 25, 25);
	        for(int m = 0; m < 2; m++)//number of players
			  {
		       if(name != tempArray[m])
		       {
		          JLabel l = new JLabel();
		
				    for(int n = 0; !temp.getLabelName().equals(l.getName());)
		          {        
						 l = (JLabel)tempArray[m].unitArmyLabels.get(n);				   
						 if(temp.getLabelName().equals(l.getName()))
						 {
					      l.setBounds(temp.getCurrentbounds());    
		             }
					  else
					    n++;  
		          }
		//System.out.println("Settler "+ CivGUI.players[k].playerUI.lb.getSettlerCount(x, y));          
		       }
	 		  } 
			  break;
		 }
	  }

     for(k = 0; k < 2; k++)//number of players
     {
	 	  tempArray[k].playerUI.map.validate();
	     tempArray[k].playerUI.map.repaint();
     }  

   }


	private void endClean(Object obj)
	{
     Settler s = null;
	  Army a = null;
	  
     if(obj.getClass().getName() == "Settler")
	    s = (Settler)obj;
	  else
	    a = (Army)obj;	 

      
		 if(s != null)
       {
		   Vector tempSett = new Vector(0, 1);   
  	 	   for(int i = 0; i < moveEnd.size(); i++)
         {
           Object temp = moveEnd.get(i);
	        if(temp.getClass().getName() == "Settler")
			  {
	          Settler se = (Settler)temp;
			    if(se.getX() == s.getX() && se.getY() == s.getY())
				   tempSett.add(se);	 
			  }
			}    
         for(int i = 0; i < tempSett.size(); i++)
		   {  
			  Settler se = (Settler)tempSett.get(i);
			  if(s == se)
			    s.setBounds(20*i, 00, 25, 25);
 		   } 
		 }
       else
		 {
		    Vector tempArmy = new Vector(0, 1);   
		 	 for(int i = 0; i < moveEnd.size(); i++)
		    { 
	         Object temp = moveEnd.get(i);
	         if(temp.getClass().getName() == "Army")
				{
	           Army arm = (Army)temp;
				  if(arm.getX() == a.getX() && arm.getY() == a.getY())
				    tempArmy.add(arm);	 
				}  
		    }
	       for(int i = 0; i < tempArmy.size(); i++)
		    {  
				 Army arm = (Army)tempArmy.get(i);
				 switch(i+(name.playerUI.lb.getSettlerCount(arm.getX(), arm.getY())))
				 {
				   case 0:
				     arm.setBounds(00, 00, 25, 25);
					  break;
				   case 1:
				     arm.setBounds(20, 00, 25, 25);
					  break;
				   case 2:
				     arm.setBounds(40, 00, 25, 25);
					  break;
				   case 3:
				     arm.setBounds(00, 20, 25, 25);
					  break;
				   case 4:
				     arm.setBounds(20, 20, 25, 25);
					  break;
				   case 5:
				     arm.setBounds(40, 20, 25, 25);
					  break;
				   case 6:
				     arm.setBounds(00, 40, 25, 25);
					  break;
				   case 7:
				     arm.setBounds(20, 40, 25, 25);
					  break;
				   case 8:
				     arm.setBounds(40, 40, 25, 25);
					  break;
				 }
        }
      }
	   name.playerUI.map.validate();
	   name.playerUI.map.repaint();
	 }

    private JLabel getPlayerArmyLabel(Player p, Army a)
    {
	    for(int m = 0; m < 2; m++)//number of players
		 {
		   if(name != p)
		   {
		      JLabel l = new JLabel();
			     
			   for(int n = 0; !a.getLabelName().equals(l.getName());)
		      {        
				  l = (JLabel)p.unitArmyLabels.get(n);				   
				  if(a.getLabelName().equals(l.getName()))
				  {
				    return l;
		        }
				  else
				    n++;  
		      }
		//System.out.println("Settler "+ CivGUI.players[k].playerUI.lb.getSettlerCount(x, y));          
		   }
	 	 } 
       return null;
    }
	 
    private JLabel getPlayerSettlerLabel(Player p, Settler a)
    {
	    for(int m = 0; m < 2; m++)//number of players
		 {
		   if(name != p)
		   {
		      JLabel l = new JLabel();
			     
			   for(int n = 0; !a.getLabelName().equals(l.getName());)
		      {        
				  l = (JLabel)p.unitSettlerLabels.get(n);				   
				  if(a.getLabelName().equals(l.getName()))
				  {
				    return l;
		        }
				  else
				    n++;  
		      }
		//System.out.println("Settler "+ CivGUI.players[k].playerUI.lb.getSettlerCount(x, y));          
		   }
	 	 } 
       return null;
    }

    private void removeBoardListeners(){
		 int k, j;//eliminate move mouselisteners from board
//System.out.println("SINGLE "+singleUnit.length);
		 for(k = 0; k < singleUnit.length; k++){
		    singleUnit[k].removeMouseListener(this);
		 }
		 for(k = 0; k < 4; k++){
//System.out.println("GROUP "+groupSettler[k].length);
		    for(j = 0; j < groupSettler[k].length; j++){
		       if(groupSettler[k][j] != null){    
				 	 groupSettler[k][j].removeMouseListener(this);
		       }  
			 }
		 }
	 }
	 
    public void removeMaps(JPanel obj)
	 {
      Player[] tempArray = name.getPlayerUI().getPlayers();
	   for(int k = 0; k < 2; k++)//number of players
      {
		  tempArray[k].playerUI.map.remove(obj);
        tempArray[k].playerUI.map.validate();
        tempArray[k].playerUI.map.repaint();
		}
	 }

    public void removeMaps(JLabel obj)
	 {
      Player[] tempArray = name.getPlayerUI().getPlayers();
	   for(int k = 0; k < 2; k++)//number of players
      {
		  tempArray[k].playerUI.map.remove(obj);
        tempArray[k].playerUI.map.validate();
        tempArray[k].playerUI.map.repaint();
		}
	 }


    public void addMaps(JPanel obj, int index)
	 {
      Player[] tempArray = name.getPlayerUI().getPlayers();
	   for(int k = 0; k < 2; k++)//number of players
      {
		  tempArray[k].playerUI.map.add(obj, index);
        tempArray[k].playerUI.map.validate();
        tempArray[k].playerUI.map.repaint();
		}
	 }

}//end class