   package games.civ;
	
//   import java.util.*;
   import javax.swing.*;
   import games.*;
	import java.io.Serializable;
//   import java.awt.*;
//   import java.awt.event.*;
//   import java.awt.image.*;
//	import java.beans.*;
//   import javax.swing.border.*;

      public class Building implements Serializable
      {
		   private static final long serialVersionUID = -4961828324375953632L;

         private String name, terrain;
         private int position;
         private int trade, production, gold, bonus, culture;
         private GameImageIcon icon;
         private GameJLabel label;
      
         public Building(){}//beans default
			
         public Building(int nm, int pos, Player p)
         {
            position = pos;
            switch(nm)
            {
               case 0:
                  name = "barracks";
                  gold = 0;
                  trade = 2;
                  production = 0;
                  bonus = 2;
                  p.combatBonus += 2;             
						terrain = "Any";
                  culture = 0;
                  icon = getImage("data/barracks.png");
                  label = new GameJLabel(icon);
                  break;
               case 1:
                  name = "granary";
                  gold = 0;
                  trade = 1;
                  production = 1;
                  bonus = 0;
                  terrain = "Grass";
                  culture = 0;
                  icon = getImage("data/granary.png");
                  label = new GameJLabel(icon);
                  break;
               case 2:
                  name = "harbor";
                  gold = 0;
                  trade = 2;
                  production = 1;
                  bonus = 0;
                  terrain = "Water";
                  culture = 0;
                  icon = getImage("data/harbor.png");
                  label = new GameJLabel(icon);
                  break;
               case 3:
                  name = "library";
                  gold = 0;
                  trade = 1;
                  production = 0;
                  bonus = 0;
                  terrain = "Grass";
                  culture = 1;
                  icon = getImage("data/library.png");
                  label = new GameJLabel(icon);
                  break;
               case 4:
                  name = "market";
                  gold = 0;
                  trade = 1;
                  production = 1;
                  bonus = 0;
                  terrain = "Any";
                  culture = 1;
                  icon = getImage("data/market.png");
                  label = new GameJLabel(icon);
                  break;
               case 5:
                  name = "temple";
                  gold = 0;
                  trade = 0;
                  production = 0;
                  bonus = 0;
                  terrain = "Any";
                  culture = 2;
                  icon = getImage("data/temple.png");
                  label = new GameJLabel(icon);
                  break;
               case 6:
                  name = "tradePost";
                  gold = 0;
                  trade = 2;
                  production = 0;
                  bonus = 0;
                  terrain = "Desert";
                  culture = 1;
                  icon = getImage("data/tradingPost.png");
                  label = new GameJLabel(icon);
                  break;
               case 7:
                  name = "workshop";
                  gold = 0;
                  trade = 0;
                  production = 3;
                  bonus = 0;
                  terrain = "Mountain";
                  culture = 0;
                  icon = getImage("data/workshop.png");
                  label = new GameJLabel(icon);
                  break;
               case 8:
                  name = "academy";
                  gold = 0;
                  trade = 2;
                  production = 0;
                  bonus = 4;
                  p.combatBonus += 4;             
                  terrain = "Any";
                  culture = 0;
                  icon = getImage("data/academy.png");
                  label = new GameJLabel(icon);
                  break;
               case 9:
                  name = "aqueduct";
                  gold = 0;
                  trade = 2;
                  production = 2;
                  bonus = 0;
                  terrain = "Grass";
                  culture = 0;
                  icon = getImage("data/aqueduct.png");
                  label = new GameJLabel(icon);
                  break;
               case 10:
                  name = "university";
                  gold = 0;
                  trade = 2;
                  production = 0;
                  bonus = 0;
                  terrain = "Grass";
                  culture = 2;
                  icon = getImage("data/university.png");
                  label = new GameJLabel(icon);
                  break;
               case 11:
                  name = "bank";
                  gold = 1;
                  trade = 1;
                  production = 1;
                  bonus = 0;
                  terrain = "Any";
                  culture = 1;
                  icon = getImage("data/bank.png");
                  label = new GameJLabel(icon);
                  break;
               case 12:
                  name = "cathedral";
                  gold = 0;
                  trade = 0;
                  production = 0;
                  bonus = 0;
                  terrain = "Any";
                  culture = 3;
                  icon = getImage("data/cathedral.png");
                  label = new GameJLabel(icon);
                  break;
               case 13:
                  name = "ironmine";
                  gold = 0;
                  trade = 0;
                  production = 4;
                  bonus = 0;
                  terrain = "Mountain";
                  culture = 0;
                  icon = getImage("data/ironmine.png");
                  label = new GameJLabel(icon);
                  break;
               case 14:
                  name = "artist";
                  trade = 1;
                  culture = 2;
                  gold = 0;
                  bonus = 0;
                  production = 0;
                  icon = getImage("data/artist.png");
                  label = new GameJLabel(icon);
                  terrain = "Any";
                  break; 
               case 15:
                  name = "builder";
                  trade = 0;
                  culture = 0;
                  gold = 1;
                  bonus = 0;
                  production = 2;
                  icon = getImage("data/builder.png");
                  label = new GameJLabel(icon);
                  terrain = "Any";
                  break; 
               case 16:
                  name = "general";
                  trade = 0;
                  culture = 0;
                  gold = 0;
                  bonus = 4;
                  p.combatBonus += 4;             
                  production = 0;
                  icon = getImage("data/general.png");
                  label = new GameJLabel(icon);
                  terrain = "Any";
                  break; 
               case 17:
                  name = "humanitarian";
                  trade = 1;
                  culture = 1;
                  gold = 1;
                  bonus = 0;
                  production = 1;
                  icon = getImage("data/human.png");
                  label = new GameJLabel(icon);
                  terrain = "Any";
                  break; 
               case 18:
                  name = "industrialist";
                  trade = 0;
                  culture = 2;
                  gold = 1;
                  bonus = 0;
                  production = 0;
                  icon = getImage("data/industry.png");
                  label = new GameJLabel(icon);
                  terrain = "Any";
                  break; 
               case 19:
                  name = "scientist";
                  trade = 2;
                  culture = 0;
                  gold = 0;
                  bonus = 0;
                  production = 1;
                  icon = getImage("data/scientist.png");
                  label = new GameJLabel(icon);
                  terrain = "Any";
                  break; 
               case 20:
                  name = "stonehenge";
                  trade = 0;
                  culture = 1;
                  gold = 0;
                  bonus = 0;
                  production = 0;
                  icon = getImage("data/stonehenge.png");
                  label = new GameJLabel(icon);
                  terrain = "Any";
                  break; 
               case 21:
                  name = "colossus";
                  trade = 0;
                  culture = 1;
                  gold = 0;
                  bonus = 0;
                  production = 0;
                  icon = getImage("data/colossus.png");
                  label = new GameJLabel(icon);
                  terrain = "Any";
                  break; 
               case 22:
                  name = "hanginggardens";
                  trade = 0;
                  culture = 1;
                  gold = 0;
                  bonus = 0;
                  production = 0;
                  icon = getImage("data/hanginggardens.png");
                  label = new GameJLabel(icon);
                  terrain = "Any";
                  break; 
               case 23:
                  name = "oracle";
                  trade = 0;
                  culture = 1;
                  gold = 0;
                  bonus = 0;
                  production = 0;
                  icon = getImage("data/oracle.png");
                  label = new GameJLabel(icon);
                  terrain = "Any";
                  break; 
               case 24:
                  name = "tower";
                  trade = 0;
                  culture = 2;
                  gold = 0;
                  bonus = 0;
                  production = 0;
                  icon = getImage("data/tower.png");
                  label = new GameJLabel(icon);
                  terrain = "Any";
                  break; 
               case 25:
                  name = "angkorwat";
                  trade = 0;
                  culture = 2;
                  gold = 0;
                  bonus = 0;
                  production = 0;
                  icon = getImage("data/angkorwat.png");
                  label = new GameJLabel(icon);
                  terrain = "Any";
                  break; 
               case 26:
                  name = "castle";
                  trade = 0;
                  culture = 2;
                  gold = 0;
                  bonus = 0;
                  production = 0;
                  icon = getImage("data/castle.png");
                  label = new GameJLabel(icon);
                  terrain = "Any";
                  break; 
               case 27:
                  name = "louvre";
                  trade = 0;
                  culture = 2;
                  gold = 0;
                  bonus = 0;
                  production = 0;
                  icon = getImage("data/louvre.png");
                  label = new GameJLabel(icon);
                  terrain = "Any";
                  break; 
               case 28:
                  name = "panama";
                  trade = 0;
                  culture = 3;
                  gold = 0;
                  bonus = 0;
                  production = 0;
                  icon = getImage("data/panama.png");
                  label = new GameJLabel(icon);
                  terrain = "Any";
                  break; 
               case 29:
                  name = "statue";
                  trade = 0;
                  culture = 3;
                  gold = 0;
                  bonus = 0;
                  production = 0;
                  icon = getImage("data/statue.png");
                  label = new GameJLabel(icon);
                  terrain = "Any";
                  break; 
               case 30:
                  name = "opera";
                  trade = 0;
                  culture = 3;
                  gold = 0;
                  bonus = 0;
                  production = 0;
                  icon = getImage("data/opera.png");
                  label = new GameJLabel(icon);
                  terrain = "Any";
                  break; 
               case 31:
                  name = "unitednations";
                  trade = 0;
                  culture = 3;
                  gold = 0;
                  bonus = 0;
                  production = 0;
                  icon = getImage("data/unitednations.png");
                  label = new GameJLabel(icon);
                  terrain = "Any";
                  break; 
            }
            label.setBounds(00, 00, icon.getIconWidth(), icon.getIconHeight());
         }
      
         public int getProd()
         {
            return this.production;
         }
			public String getName() {
				return name;
			}
			public void setName(String name) {
				this.name = name;
			}
			public String getTerrain() {
				return terrain;
			}
			public void setTerrain(String terrain) {
				this.terrain = terrain;
			}
			public int getPosition() {
				return position;
			}
			public void setPosition(int position) {
				this.position = position;
			}
			public int getTrade() {
				return trade;
			}
			public void setTrade(int trade) {
				this.trade = trade;
			}
			public int getProduction() {
				return production;
			}
			public void setProduction(int production) {
				this.production = production;
			}
			public int getGold() {
				return gold;
			}
			public void setGold(int gold) {
				this.gold = gold;
			}
			public int getBonus() {
				return bonus;
			}
			public void setBonus(int bonus) {
				this.bonus = bonus;
			}
			public int getCulture() {
				return culture;
			}
			public void setCulture(int culture) {
				this.culture = culture;
			}
			public GameImageIcon getIcon() {
				return icon;
			}
			public void setIcon(GameImageIcon icon) {
				this.icon = icon;
			}
			public GameJLabel getLabel() {
				return label;
			}
			public void setLabel(GameJLabel label) {
				this.label = label;
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
					name = stream.readUTF();
					terrain = stream.readUTF();
					position = stream.readInt();
					trade = stream.readInt();
					production = stream.readInt();
					gold = stream.readInt();
					bonus = stream.readInt();
					culture = stream.readInt();
					icon = (GameImageIcon)stream.readObject();
					label = (GameJLabel)stream.readObject();
		      }catch(java.io.IOException ioEx){
					System.out.println("IO EXCEPTION getSentObject\n");
					ioEx.printStackTrace();
				}
		   }
		
		   private void writeObject(java.io.ObjectOutputStream stream) throws java.io.IOException{
				try{
					stream.writeUTF(name);
					stream.writeUTF(terrain);
					stream.writeInt(position);
					stream.writeInt(trade);
					stream.writeInt(production);
					stream.writeInt(gold);
					stream.writeInt(bonus);
					stream.writeInt(culture);
					stream.writeObject(icon);
					stream.writeObject(label);
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
