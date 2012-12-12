  package games.civ;
	
   import java.util.*;
   import javax.swing.*;
   import games.*;
   import java.io.Serializable;
//   import java.awt.*;

//   import java.awt.event.*;
//   import java.awt.image.*;
//	import java.beans.*;
//   import javax.swing.border.*;

      public class City implements Serializable
      {
		   private static final long serialVersionUID = -6180321269707320790L;

         private  int production;
         private  int trade;
         public  Vector buildings, greatPeople;
         private boolean hasUnique, hasWonder, hasWalls;
         private int xPos, yPos, bonus;
         private GameImageIcon icon;
         private boolean capital;
         private Vector link;//for Settler link for cities
      
         public City(){}//dummy constructor
			      
			public City(int x, int y, boolean cap, Player p)
         {
            greatPeople = new Vector(0, 1);
            link = new Vector(0, 1);
            xPos = x;
            yPos = y;  
            production = p.playerUI.lb.getProduction(x, y);
            trade = p.playerUI.lb.getTrade(x, y);
            buildings = new Vector(8);
            for(int k = 0; k < 8; k++)
               buildings.add(k, null); 
            hasUnique = false;
            hasWonder = false;
            if(cap && p.getNation().equals("China"))
            {
               capital = true;
               hasWalls = true;  
               bonus = 16;          
					icon = getImage("data/capital"+p.getColor()+"16.png");
            }
            else if(cap)
            {
               capital = true;
               bonus = 12;          
					icon = getImage("data/capital"+p.getColor()+"12.png");
            }
            else
            {
               capital = false;  
               bonus = 6; 
               icon = getImage("data/city"+p.getColor()+"6.png");
            } 
         }
      
         public void addWalls(LogicBoard lb, Player p)
         {
            hasWalls = true;
            if(!capital)
               icon = getImage("data/city"+p.getColor()+"10.png");
            else
               icon = getImage("data/capital"+p.getColor()+"16.png");
            p.playerUI.lb.getHolder(xPos, yPos).remove(0);//remove button from cityGUI
            p.playerUI.lb.getHolder(xPos, yPos).remove(0);//remove non-wall label
            GameJLabel l = new GameJLabel(icon);     
            l.setBounds(00, 00, 55, 55); 
            p.playerUI.lb.getHolder(xPos, yPos).add(l, new Integer(1));		
         //		lb.getHolder(xPos, yPos).validate();
            p.playerUI.lb.getHolder(xPos, yPos).repaint();
            bonus += 4;      
			}
      
         public boolean hasWalls()
         {
            return this.hasWalls;
         }
      
         public int getCulture(Player p)
         {
            int cul = 1;//starts at 1 for city itself  
            int k, i;
            int position = 0;
            int x = this.xPos;
            int y = this.yPos;
         
            if(link.size() > 0)
            {
               Settler temp; 
               for(k = 0; k < link.size(); k++)
               {
                  temp = (Settler)link.get(k);
                  if(!p.playerUI.lb.isCityAdjacent(temp.getX(), temp.getY()))
                     cul += p.playerUI.lb.getSpaceCulture(temp.getX(), temp.getY());
               }
            }
            for(k = x-1; k <= x+1; k++)
            {  
               i = y-1;
               if(buildings.get(position) == null)   
                  cul = cul + p.playerUI.lb.getSpaceCulture(k, i);
               else
               {
                  Building temp = (Building)buildings.get(position);
                  cul = cul + temp.getCulture();
               }
               position++;
            }
            for(k = x-1; k <= x+1; k++)
            {  
               i = y+1;
               if(buildings.get(position) == null)   
                  cul = cul + p.playerUI.lb.getSpaceCulture(k, i);
               else
               {
                  Building temp = (Building)buildings.get(position);
                  cul = cul + temp.getCulture();
               }
               position++;
            }
            if(buildings.get(position) == null)   
               cul = cul + p.playerUI.lb.getSpaceCulture(x-1, y);
            else
            {
               Building temp = (Building)buildings.get(position);
               cul = cul + temp.getCulture();
            }
            position++;
            if(buildings.get(position) == null)   
               cul = cul + p.playerUI.lb.getSpaceCulture(x+1, y);
            else
            {
               Building temp = (Building)buildings.get(position);
               cul = cul + temp.getCulture();
            }
         
            if(capital && (p.getGov() == 5))
               cul--;  
 //           System.out.println("CULTURE: "+cul);
            p.culture = cul;
            return p.culture;
         }
      
         public GameImageIcon getIcon()
         {
            return icon;
         }
      
         public int getXPos()
         {
            return xPos;
         }
      
         public int getYPos()
         {
            return yPos;
         }
      
         public int getGold(Player p)
         {
            int gold = 0;  
            int k, i;
            int position = 0;
            int x = this.xPos;
            int y = this.yPos;
         
            if(link.size() > 0)
            {
               Settler temp; 
               for(k = 0; k < link.size(); k++)
               {
                  temp = (Settler)link.get(k);
                  if(p.playerUI.lb.getSpaceGold(temp.getX(), temp.getY()) && !p.playerUI.lb.isCityAdjacent(temp.getX(), temp.getY()))
                     gold++;
               }
            }
            for(k = x-1; k <= x+1; k++)
            {  
               i = y-1;
               if(buildings.get(position) == null)   
               {
                  if(p.playerUI.lb.getSpaceGold(k, i))
                     gold++;
               }
               else
               {
                  Building temp = (Building)buildings.get(position);
                  gold += temp.getGold();
               }
               position++;
            }
            for(k = x-1; k <= x+1; k++)
            {  
               i = y+1;
               if(buildings.get(position) == null)   
               {
                  if(p.playerUI.lb.getSpaceGold(k, i))
                     gold++;
               }
               else
               {
                  Building temp = (Building)buildings.get(position);
                  gold += temp.getGold();
               }
               position++;
            }
            if(buildings.get(position) == null)   
            {
               if(p.playerUI.lb.getSpaceGold(x-1, y))
                  gold++;
            }
            else
            {
               Building temp = (Building)buildings.get(position);
               gold += temp.getGold();
            }
            position++;
            if(buildings.get(position) == null)   
            {
               if(p.playerUI.lb.getSpaceGold(x+1, y))
                  gold++;
            }
            else
            {
               Building temp = (Building)buildings.get(position);
               gold += temp.getGold();
            }
         
 //           System.out.println("GOLD: "+gold+" Position: "+position);
            return gold;
         }
      
         public int getProd(Player p)
         {
            int prod = 0;  
            int k, i;
            int position = 0;
            int x = this.xPos;
            int y = this.yPos;
         
            if(link.size() > 0)
            {
               Settler temp; 
               for(k = 0; k < link.size(); k++)
               {
                  temp = (Settler)link.get(k);
                  if(!p.playerUI.lb.isCityAdjacent(temp.getX(), temp.getY()))
                     prod += p.playerUI.lb.getSpaceProduction(temp.getX(), temp.getY());
               }
            }
            for(k = x-1; k <= x+1; k++)
            {  
               i = y-1;
               if(buildings.get(position) == null)   
                  prod = prod + p.playerUI.lb.getSpaceProduction(k, i);
               else
               {
                  Building temp = (Building)buildings.get(position);
                  prod = prod + temp.getProd();
               }
               position++;
            }
            for(k = x-1; k <= x+1; k++)
            {  
               i = y+1;
               if(buildings.get(position) == null)   
                  prod = prod + p.playerUI.lb.getSpaceProduction(k, i);
               else
               {
                  Building temp = (Building)buildings.get(position);
                  prod = prod + temp.getProd();
               }
               position++;
            }
            if(buildings.get(position) == null)   
               prod = prod + p.playerUI.lb.getSpaceProduction(x-1, y);
            else
            {
               Building temp = (Building)buildings.get(position);
               prod = prod + temp.getProd();
            }
            position++;
            if(buildings.get(position) == null)   
               prod = prod + p.playerUI.lb.getSpaceProduction(x+1, y);
            else
            {
               Building temp = (Building)buildings.get(position);
               prod = prod + temp.getProd();
            }
            if(p.getGov() == 5)
               prod += 2;
 //           System.out.println("PROD: "+prod);
            production = prod;
            return production;
         }
      
         public int getTrade(Player p)
         {
            int trd = 0;  
            int k, i;
            int position = 0;
            int x = this.xPos;
            int y = this.yPos;
         
            if(link.size() > 0)
            {
               Settler temp; 
               for(k = 0; k < link.size(); k++)
               {
                  temp = (Settler)link.get(k);
                  if(!p.playerUI.lb.isCityAdjacent(temp.getX(), temp.getY()))
                     trd += p.playerUI.lb.getSpaceTrade(temp.getX(), temp.getY());
               }
            }
            for(k = x-1; k <= x+1; k++)
            {  
               i = y-1;
               if(buildings.get(position) == null)   
                  trd = trd + p.playerUI.lb.getSpaceTrade(k, i);
               else
               {
                  Building temp = (Building)buildings.get(position);
                  trd = trd + temp.getTrade();
               }
               position++;
            }
            for(k = x-1; k <= x+1; k++)
            {  
               i = y+1;
               if(buildings.get(position) == null)   
                  trd = trd + p.playerUI.lb.getSpaceTrade(k, i);
               else
               {
                  Building temp = (Building)buildings.get(position);
                  trd = trd + temp.getTrade();
               }
               position++;
            }
            if(buildings.get(position) == null)   
               trd = trd + p.playerUI.lb.getSpaceTrade(x-1, y);
            else
            {
               Building temp = (Building)buildings.get(position);
               trd = trd + temp.getTrade();
            }
            position++;
            if(buildings.get(position) == null)   
               trd = trd + p.playerUI.lb.getSpaceTrade(x+1, y);
            else
            {
               Building temp = (Building)buildings.get(position);
               trd = trd + temp.getTrade();
            }
         
//            System.out.println("TRADE: "+trd);
            trade = trd;
            return trade;
         }
      
         public int getBonus()
			{
			  return this.bonus;
			}
			
         public void upgradeBuilding(int type, Player p)
			{
           int index = p.cities.indexOf(this);			  

			  switch(type)
			  {
			    case 0:
	            int k, i;
	            int position = 0;
	            int x = this.xPos;
	            int y = this.yPos;
	         
		         for(k = x-1; k <= x+1; k++)
		         {  
		           i = y-1;
		           if(buildings.get(position) != null)   
		           {
		              Building temp = (Building)buildings.get(position);
		              if(temp.getName().equals("granary"))
                    {
                          p.playerUI.lb.getHolder(k, i).remove(p.playerUI.lb.getHolder(k, i).getIndexOf(temp.getLabel())); 
								  addBuilding(9, position, p);
								  temp = (Building)buildings.get(position);
                          p.playerUI.lb.addBuilding(k, i, 9, temp.getLabel(), false, p, index);
						  }
	              }
                 position++;
	            }
	            for(k = x-1; k <= x+1; k++)
	            {  
	               i = y+1;
	               if(buildings.get(position) != null)   
	               {
	                  Building temp = (Building)buildings.get(position);
	                  if(temp.getName().equals("granary"))
                       {
                         p.playerUI.lb.getHolder(k, i).remove(p.playerUI.lb.getHolder(k, i).getIndexOf(temp.getLabel())); 
							    addBuilding(9, position,p);
							    temp = (Building)buildings.get(position);
                         p.playerUI.lb.addBuilding(k, i, 9, temp.getLabel(), false, p, index);
							}
	               }
                  position++;
	            }
               if(buildings.get(position) != null)   
	               {
	                  Building temp = (Building)buildings.get(position);
	                  if(temp.getName().equals("granary"))
                     {
                         p.playerUI.lb.getHolder(x-1, y).remove(p.playerUI.lb.getHolder(x-1, y).getIndexOf(temp.getLabel())); 
							    addBuilding(9, position, p);
							    temp = (Building)buildings.get(position);
                         p.playerUI.lb.addBuilding(x-1, y, 9, temp.getLabel(), false, p, index);
							}
	               }
               position++;
               if(buildings.get(position) != null)   
               {
                  Building temp = (Building)buildings.get(position);
                  if(temp.getName().equals("granary"))
                  {
                         p.playerUI.lb.getHolder(x+1, y).remove(p.playerUI.lb.getHolder(x+1, y).getIndexOf(temp.getLabel())); 
							    addBuilding(9, position, p);
							    temp = (Building)buildings.get(position);
                         p.playerUI.lb.addBuilding(x+1, y, 9, temp.getLabel(), false, p, index);
						}
               }
				   break;
			    case 1:
	             position = 0;
	             x = this.xPos;
	             y = this.yPos;
	         
		         for(k = x-1; k <= x+1; k++)
		         {  
		           i = y-1;
		           if(buildings.get(position) != null)   
		           {
		              Building temp = (Building)buildings.get(position);
		              if(temp.getName().equals("library"))
                    {
                          p.playerUI.lb.getHolder(k, i).remove(p.playerUI.lb.getHolder(k, i).getIndexOf(temp.getLabel())); 
								  addBuilding(10, position, p);
								  temp = (Building)buildings.get(position);
                          p.playerUI.lb.addBuilding(k, i, 10, temp.getLabel(), false, p, index);
						  }
	              }
                 position++;
	            }
	            for(k = x-1; k <= x+1; k++)
	            {  
	               i = y+1;
	               if(buildings.get(position) != null)   
	               {
	                  Building temp = (Building)buildings.get(position);
	                  if(temp.getName().equals("library"))
                       {
                         p.playerUI.lb.getHolder(k, i).remove(p.playerUI.lb.getHolder(k, i).getIndexOf(temp.getLabel())); 
							    addBuilding(10, position, p);
							    temp = (Building)buildings.get(position);
                         p.playerUI.lb.addBuilding(k, i, 10, temp.getLabel(), false, p, index);
							}
	               }
                  position++;
	            }
               if(buildings.get(position) != null)   
	               {
	                  Building temp = (Building)buildings.get(position);
	                  if(temp.getName().equals("library"))
                     {
                         p.playerUI.lb.getHolder(x-1, y).remove(p.playerUI.lb.getHolder(x-1, y).getIndexOf(temp.getLabel())); 
							    addBuilding(10, position, p);
							    temp = (Building)buildings.get(position);
                         p.playerUI.lb.addBuilding(x-1, y, 10, temp.getLabel(), false, p, index);
							}
	               }
               position++;
               if(buildings.get(position) != null)   
               {
                  Building temp = (Building)buildings.get(position);
                  if(temp.getName().equals("library"))
                  {
                         p.playerUI.lb.getHolder(x+1, y).remove(p.playerUI.lb.getHolder(x+1, y).getIndexOf(temp.getLabel())); 
							    addBuilding(10, position, p);
							    temp = (Building)buildings.get(position);
                         p.playerUI.lb.addBuilding(x+1, y, 10, temp.getLabel(), false, p, index);
						}
               }
				   break;
			    case 2:
	             position = 0;
	             x = this.xPos;
	             y = this.yPos;
	         
		         for(k = x-1; k <= x+1; k++)
		         {  
		           i = y-1;
		           if(buildings.get(position) != null)   
		           {
		              Building temp = (Building)buildings.get(position);
		              if(temp.getName().equals("market"))
                    {
                          p.playerUI.lb.getHolder(k, i).remove(p.playerUI.lb.getHolder(k, i).getIndexOf(temp.getLabel())); 
								  addBuilding(11, position, p);
								  temp = (Building)buildings.get(position);
                          p.playerUI.lb.addBuilding(k, i, 11, temp.getLabel(), false, p, index);
						  }
	              }
                 position++;
	            }
	            for(k = x-1; k <= x+1; k++)
	            {  
	               i = y+1;
	               if(buildings.get(position) != null)   
	               {
	                  Building temp = (Building)buildings.get(position);
	                  if(temp.getName().equals("market"))
                       {
                         p.playerUI.lb.getHolder(k, i).remove(p.playerUI.lb.getHolder(k, i).getIndexOf(temp.getLabel())); 
							    addBuilding(11, position, p);
							    temp = (Building)buildings.get(position);
                         p.playerUI.lb.addBuilding(k, i, 11, temp.getLabel(), false, p, index);
							}
	               }
                  position++;
	            }
               if(buildings.get(position) != null)   
	               {
	                  Building temp = (Building)buildings.get(position);
	                  if(temp.getName().equals("market"))
                     {
                         p.playerUI.lb.getHolder(x-1, y).remove(p.playerUI.lb.getHolder(x-1, y).getIndexOf(temp.getLabel())); 
							    addBuilding(11, position, p);
							    temp = (Building)buildings.get(position);
                         p.playerUI.lb.addBuilding(x-1, y, 11, temp.getLabel(), false, p, index);
							}
	               }
               position++;
               if(buildings.get(position) != null)   
               {
                  Building temp = (Building)buildings.get(position);
                  if(temp.getName().equals("market"))
                  {
                         p.playerUI.lb.getHolder(x+1, y).remove(p.playerUI.lb.getHolder(x+1, y).getIndexOf(temp.getLabel())); 
							    addBuilding(11, position, p);
							    temp = (Building)buildings.get(position);
                         p.playerUI.lb.addBuilding(x+1, y, 11, temp.getLabel(), false, p, index);
						}
               }
				   break;
			    case 3:
	             position = 0;
	             x = this.xPos;
	             y = this.yPos;
	         
		         for(k = x-1; k <= x+1; k++)
		         {  
		           i = y-1;
		           if(buildings.get(position) != null)   
		           {
		              Building temp = (Building)buildings.get(position);
		              if(temp.getName().equals("barracks"))
                    {
                          p.playerUI.lb.getHolder(k, i).remove(p.playerUI.lb.getHolder(k, i).getIndexOf(temp.getLabel())); 
								  addBuilding(8, position, p);
								  temp = (Building)buildings.get(position);
                          p.playerUI.lb.addBuilding(k, i, 8, temp.getLabel(), false, p, index);
						        bonus -=2;
						  }
	              }
                 position++;
	            }
	            for(k = x-1; k <= x+1; k++)
	            {  
	               i = y+1;
	               if(buildings.get(position) != null)   
	               {
	                  Building temp = (Building)buildings.get(position);
	                  if(temp.getName().equals("barracks"))
                       {
                         p.playerUI.lb.getHolder(k, i).remove(p.playerUI.lb.getHolder(k, i).getIndexOf(temp.getLabel())); 
							    addBuilding(8, position, p);
							    temp = (Building)buildings.get(position);
                         p.playerUI.lb.addBuilding(k, i, 8, temp.getLabel(), false, p, index);
						       bonus -=2;
							}
	               }
                  position++;
	            }
               if(buildings.get(position) != null)   
	               {
	                  Building temp = (Building)buildings.get(position);
	                  if(temp.getName().equals("barracks"))
                     {
                         p.playerUI.lb.getHolder(x-1, y).remove(p.playerUI.lb.getHolder(x-1, y).getIndexOf(temp.getLabel())); 
							    addBuilding(8, position, p);
							    temp = (Building)buildings.get(position);
                         p.playerUI.lb.addBuilding(x-1, y, 8, temp.getLabel(), false, p, index);
					          bonus -=2;
							}
	               }
               position++;
               if(buildings.get(position) != null)   
               {
                  Building temp = (Building)buildings.get(position);
                  if(temp.getName().equals("barracks"))
                  {
                         p.playerUI.lb.getHolder(x+1, y).remove(p.playerUI.lb.getHolder(x+1, y).getIndexOf(temp.getLabel())); 
							    addBuilding(8, position, p);
							    temp = (Building)buildings.get(position);
                         p.playerUI.lb.addBuilding(x+1, y, 8, temp.getLabel(), false, p, index);
						       bonus -=2;
						}
               }
				   break;
			    case 4:
	             position = 0;
	             x = this.xPos;
	             y = this.yPos;
	         
		         for(k = x-1; k <= x+1; k++)
		         {  
		           i = y-1;
		           if(buildings.get(position) != null)   
		           {
		              Building temp = (Building)buildings.get(position);
		              if(temp.getName().equals("workshop"))
                    {
                          p.playerUI.lb.getHolder(k, i).remove(p.playerUI.lb.getHolder(k, i).getIndexOf(temp.getLabel())); 
								  addBuilding(13, position, p);
								  temp = (Building)buildings.get(position);
                          p.playerUI.lb.addBuilding(k, i, 13, temp.getLabel(), false, p, index);
						  }
	              }
                 position++;
	            }
	            for(k = x-1; k <= x+1; k++)
	            {  
	               i = y+1;
	               if(buildings.get(position) != null)   
	               {
	                  Building temp = (Building)buildings.get(position);
	                  if(temp.getName().equals("workshop"))
                       {
                         p.playerUI.lb.getHolder(k, i).remove(p.playerUI.lb.getHolder(k, i).getIndexOf(temp.getLabel())); 
							    addBuilding(13, position, p);
							    temp = (Building)buildings.get(position);
                         p.playerUI.lb.addBuilding(k, i, 13, temp.getLabel(), false, p, index);
							}
	               }
                  position++;
	            }
               if(buildings.get(position) != null)   
	               {
	                  Building temp = (Building)buildings.get(position);
	                  if(temp.getName().equals("workshop"))
                     {
                         p.playerUI.lb.getHolder(x-1, y).remove(p.playerUI.lb.getHolder(x-1, y).getIndexOf(temp.getLabel())); 
							    addBuilding(13, position, p);
							    temp = (Building)buildings.get(position);
                         p.playerUI.lb.addBuilding(x-1, y, 13, temp.getLabel(), false, p, index);
							}
	               }
               position++;
               if(buildings.get(position) != null)   
               {
                  Building temp = (Building)buildings.get(position);
                  if(temp.getName().equals("workshop"))
                  {
                         p.playerUI.lb.getHolder(x+1, y).remove(p.playerUI.lb.getHolder(x+1, y).getIndexOf(temp.getLabel())); 
							    addBuilding(13, position, p);
							    temp = (Building)buildings.get(position);
                         p.playerUI.lb.addBuilding(x+1, y, 13, temp.getLabel(), false, p, index);
						}
               }
				   break;
			    case 5:
	             position = 0;
	             x = this.xPos;
	             y = this.yPos;
	         
		         for(k = x-1; k <= x+1; k++)
		         {  
		           i = y-1;
		           if(buildings.get(position) != null)   
		           {
		              Building temp = (Building)buildings.get(position);
		              if(temp.getName().equals("temple"))
                    {
                          p.playerUI.lb.getHolder(k, i).remove(p.playerUI.lb.getHolder(k, i).getIndexOf(temp.getLabel())); 
								  addBuilding(12, position, p);
								  temp = (Building)buildings.get(position);
                          p.playerUI.lb.addBuilding(k, i, 12, temp.getLabel(), false, p, index);
						  }
	              }
                 position++;
	            }
	            for(k = x-1; k <= x+1; k++)
	            {  
	               i = y+1;
	               if(buildings.get(position) != null)   
	               {
	                  Building temp = (Building)buildings.get(position);
	                  if(temp.getName().equals("temple"))
                       {
                         p.playerUI.lb.getHolder(k, i).remove(p.playerUI.lb.getHolder(k, i).getIndexOf(temp.getLabel())); 
							    addBuilding(12, position, p);
							    temp = (Building)buildings.get(position);
                         p.playerUI.lb.addBuilding(k, i, 12, temp.getLabel(), false, p, index);
							}
	               }
                  position++;
	            }
               if(buildings.get(position) != null)   
	               {
	                  Building temp = (Building)buildings.get(position);
	                  if(temp.getName().equals("temple"))
                     {
                         p.playerUI.lb.getHolder(x-1, y).remove(p.playerUI.lb.getHolder(x-1, y).getIndexOf(temp.getLabel())); 
							    addBuilding(12, position, p);
							    temp = (Building)buildings.get(position);
                         p.playerUI.lb.addBuilding(x-1, y, 12, temp.getLabel(), false, p, index);
							}
	               }
               position++;
               if(buildings.get(position) != null)   
               {
                  Building temp = (Building)buildings.get(position);
                  if(temp.getName().equals("temple"))
                  {
                         p.playerUI.lb.getHolder(x+1, y).remove(p.playerUI.lb.getHolder(x+1, y).getIndexOf(temp.getLabel())); 
							    addBuilding(12, position, p);
							    temp = (Building)buildings.get(position);
                         p.playerUI.lb.addBuilding(x+1, y, 12, temp.getLabel(), false, p, index);
						}
               }
				   break;
			  }		
			}
			
         public void addBuilding(int nm, int pos, Player p)
         {
            buildings.removeElementAt(pos);  
            buildings.add(pos, new Building(nm, pos, p)); 
            if(nm == 0 || nm == 4 || nm == 5 || nm == 8 || nm == 11 || nm == 12)
               hasUnique = true;
            if(nm == 20 || nm == 21|| nm == 22 || nm == 23 || nm == 24 || nm == 25
            || nm == 26 || nm == 27 || nm == 28 || nm == 29 || nm == 30 || nm == 31)
               hasWonder = true;  
            Building temp = (Building)buildings.get(pos);
            p.gold += temp.getGold();
			}
      
			public Building getBuilding(int pos)
         {
            Building temp = (Building)buildings.get(pos);  
            return temp;
         }
      
         public GameJLabel getBuildingLabel(int index)
         {
            Building temp = (Building)buildings.get(index);   
            return temp.getLabel();
         }
      
         public String getBuildingName(int index)
         {
            if(buildings.get(index) != null)
				{        
					Building temp = (Building)buildings.get(index);   
	            return temp.getName();
            }
				else
				  return null;    
		   }

         public boolean hasUniqueBuilding()
         {
            return this.hasUnique;
         }
      
         public boolean hasWonder()
         {
            return this.hasWonder;
         }
      
         public void addLink(Settler s)
         {
            link.add(s);
         }
      
         public Vector getLinkVector()
         {
            return link;
         }
      
         public void resetLinkVector()
         {
            link.removeAllElements();
         }
         
			public void removeBuilding(int x, int y, int city_x, int city_y, int nm, Player p)
 			{
//need functionality			
            int pos = -1;
            if(((x - city_x) == -1) && ((y - city_y) == -1))
				  pos = 0;
            else if(((x - city_x) == 0) && ((y - city_y) == -1))
				  pos = 1;
            else if(((x - city_x) == 1) && ((y - city_y) == -1))
				  pos = 2;
            else if(((x - city_x) == -1) && ((y - city_y) == 1))
				  pos = 3;
            else if(((x - city_x) == 0) && ((y - city_y) == 1))
				  pos = 4;
            else if(((x - city_x) == 1) && ((y - city_y) == 1))
				  pos = 5;
            else if(((x - city_x) == -1) && ((y - city_y) == 0))
				  pos = 6;
            else if(((x - city_x) == 1) && ((y - city_y) == 0))
				  pos = 7;

//System.out.println("POS "+pos+" X "+x+" City X "+city_x+" Y "+y+" City Y "+city_y);
            Building temp = (Building)buildings.get(pos);
            p.gold += temp.getGold();

//            playerUI.lb.getHolder(x, y).remove(playerUI.lb.getHolder(x, y).getIndexOf(temp.getLabel()));        

  				temp = null;  
            buildings.removeElementAt(pos);  
            buildings.add(pos, temp); 
            

            if(nm == 0 || nm == 4 || nm == 5 || nm == 8 || nm == 11 || nm == 12)
               hasUnique = false;
            if(nm == 20 || nm == 21|| nm == 22 || nm == 23 || nm == 24 || nm == 25
            || nm == 26 || nm == 27 || nm == 28 || nm == 29 || nm == 30 || nm == 31)
               hasWonder = false;  
			}
      
			public int getProduction() {
				return production;
			}
			public void setProduction(int production) {
				this.production = production;
			}
			public void setTrade(int trade) {
				this.trade = trade;
			}
			public Vector getBuildings() {
				return buildings;
			}
			public void setBuildings(Vector buildings) {
				this.buildings = buildings;
			}
			public Vector getGreatPeople() {
				return greatPeople;
			}
			public void setGreatPeople(Vector greatPeople) {
				this.greatPeople = greatPeople;
			}
			public boolean isHasUnique() {
				return hasUnique;
			}
			public void setHasUnique(boolean hasUnique) {
				this.hasUnique = hasUnique;
			}
			public boolean isHasWonder() {
				return hasWonder;
			}
			public void setHasWonder(boolean hasWonder) {
				this.hasWonder = hasWonder;
			}
			public boolean isHasWalls() {
				return hasWalls;
			}
			public void setHasWalls(boolean hasWalls) {
				this.hasWalls = hasWalls;
			}
			public void setXPos(int xPos) {
				this.xPos = xPos;
			}
			public void setYPos(int yPos) {
				this.yPos = yPos;
			}
			public void setBonus(int bonus) {
				this.bonus = bonus;
			}
			public void setIcon(GameImageIcon icon) {
				this.icon = icon;
			}
			public boolean isCapital() {
				return capital;
			}
			public void setCapital(boolean capital) {
				this.capital = capital;
			}
			public Vector getLink() {
				return link;
			}
			public void setLink(Vector link) {
				this.link = link;
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

			private void readObject(java.io.ObjectInputStream stream) throws java.io.IOException, ClassNotFoundException{
				try{
		         production = stream.readInt();
		         trade = stream.readInt();
		         xPos = stream.readInt();
				   yPos = stream.readInt();
					bonus = stream.readInt();
		         buildings = (Vector)stream.readObject();
					greatPeople = (Vector)stream.readObject();
		         link = (Vector)stream.readObject();
		         hasUnique = stream.readBoolean();
					hasWonder = stream.readBoolean();
					hasWalls = stream.readBoolean();
		         capital = stream.readBoolean();
		         icon = (GameImageIcon)stream.readObject();
		      }catch(java.io.IOException ioEx){
					System.out.println("IO EXCEPTION readObject\n");
					ioEx.printStackTrace();
				}
		   }
		
		   private void writeObject(java.io.ObjectOutputStream stream) throws java.io.IOException{
				try{
					stream.writeInt(production);
					stream.writeInt(trade);
					stream.writeInt(xPos);
					stream.writeInt(yPos);
					stream.writeInt(bonus);
					stream.writeObject(buildings);
					stream.writeObject(greatPeople);
					stream.writeObject(link);
					stream.writeBoolean(hasUnique);
					stream.writeBoolean(hasWonder);
					stream.writeBoolean(hasWalls);
					stream.writeBoolean(capital);
					stream.writeObject(icon);
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
