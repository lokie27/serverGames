   package games.civ;
	
   import java.util.*;
   import javax.swing.*;
   import java.awt.image.*;
	import java.beans.*;
   import javax.swing.border.*;
   import java.io.Serializable;
   import games.*;
	
      public class Tech implements Serializable
      {
		   private static final long serialVersionUID = -5556989160051896711L;

         private String techName;
         private int vectorNum;
         private int level;
         private String resource;
         private String building;
         public int gold;
         public GameImageIcon icon;
         public GameJLabel label;
       	 
         public Tech(){}//bean default
			      
			public Tech(int num, Player p)
         {
            switch(num)
            {
               case(0):
                  building = "";      
                  resource = "Wheat";
                  level = 1;
                  techName = "animalHusbandry";
                  gold = 0;
                  icon = getImage("data/animalHusbandry.png");
                  label = new GameJLabel(icon); 
                  break;
               case(1):
                  building = "tradingPost";      
                  resource = "";
                  level = 1;
                  techName = "codeOfLaws";
                  gold = 0;
                  p.codeOfLaw = true;
                  icon = getImage("data/codeOfLaws.png");
                  label = new GameJLabel(icon); 
                  if(!p.getNation().equals("Rome"))
                  {
                     p.freeGovChange = true;
                     p.freeGov = 1;
                  }
                  break;
               case(2):
                  building = "market";      
                  resource = "Incense";
                  level = 1;
                  techName = "currency";
                  gold = 0;
                  icon = getImage("data/currency.png");
                  label = new GameJLabel(icon); 
                  break;
               case(3):
                  building = "";      
                  resource = "Silk";
                  level = 1;
                  techName = "horsebackRiding";
                  gold = 0;
                  p.moveLimit = 3;
                  p.resetUnitMoves();
                  icon = getImage("data/horsebackRiding.png");
                  label = new GameJLabel(icon); 
                  break;
               case(4):
                  building = "";      
                  resource = "";
                  level = 1;
                  techName = "masonry";
                  gold = 0;
                  p.stackLimit = 3;
                  icon = getImage("data/masonry.png");
                  label = new GameJLabel(icon); 
                  break;
               case(5):
                  building = "barracks";      
                  resource = "Iron";
                  level = 1;
                  techName = "metalWorking";
                  gold = 0;
                  icon = getImage("data/metalWorking.png");
                  label = new GameJLabel(icon); 
                  break;
               case(6):
                  building = "harbor";      
                  resource = "";
                  level = 1;
                  techName = "navigation";
                  gold = 0;
                  p.canWaterCross = true;
                  icon = getImage("data/navigation.png");
                  label = new GameJLabel(icon); 
                  break;
               case(7):
                  building = "temple";      
                  resource = "Any";
                  level = 1;
                  techName = "philosophy";
                  gold = 0;
                  icon = getImage("data/philosophy.png");
                  label = new GameJLabel(icon); 
                  break;
               case(8):
                  building = "granary";      
                  resource = "Any";
                  level = 1;
                  p.handLimitCulture++;
                  techName = "pottery";
                  gold = 0;
                  icon = getImage("data/pottery.png");
                  label = new GameJLabel(icon); 
                  break;
               case(9):
                  building = "library";      
                  resource = "Spy";
                  level = 1;
                  techName = "writing"; //end level 1
                  gold = 0;
                  icon = getImage("data/writing.png");
                  label = new GameJLabel(icon); 
                  break;
               case(10):
                  building = "";      
                  resource = "Incense";
                  level = 2;
                  techName = "chivalry";
                  gold = 0;
                  p.mntLevel = 2;
                  p.upgradeMnt();
                  icon = getImage("data/chivalry.png");
                  p.freeGovChange = true;
                  p.freeGov = 4;
                  label = new GameJLabel(icon); 
                  break;
               case(11):
                  building = "";      
                  resource = "Spy";
                  level = 2;
                  techName = "civilService";
                  gold = 1;
                  p.updateTechGold(1);      
                  p.handLimitCulture++; 
                  icon = getImage("data/civilService.png");
                  label = new GameJLabel(icon); 
                  break;
               case(12):
                  building = "Workshop";      
                  resource = "Wheat";
                  level = 2;
                  techName = "construction";
                  gold = 0;
                  icon = getImage("data/construction.png");
                  label = new GameJLabel(icon); 
                  break;
               case(13):
                  building = "";      
                  resource = "";
                  level = 2;
                  techName = "democracy";
                  gold = 0;
                  p.infLevel = 2;
                  p.upgradeInf();      
                  icon = getImage("data/democracy.png");
                  p.freeGovChange = true;
                  p.freeGov = 2;
                  label = new GameJLabel(icon); 
                  break;
               case(14):
                  building = "Aqueduct";      
                  resource = "buildtwo";
                  level = 2;
                  techName = "engineering";
                  gold = 0;
                  icon = getImage("data/engineering.png");
                  label = new GameJLabel(icon); 
                  p.upgradeBuildings(0);
                  break;
               case(15):
                  building = "";      
                  resource = "";
                  level = 2;
                  techName = "irrigation";
                  gold = 0;
                  p.hasIrrigation = true;
                  icon = getImage("data/irrigation.png");
                  label = new GameJLabel(icon); 
                  break;
               case(16):
                  building = "";      
                  resource = "Iron";
                  level = 2;
                  techName = "mathematics";
                  gold = 0;
                  p.artLevel = 2;
                  p.upgradeArt();
                  icon = getImage("data/mathematics.png");
                  label = new GameJLabel(icon); 
                  break;
               case(17):
                  building = "";      
                  resource = "Silk";
                  level = 2;
                  techName = "monarchy";
                  gold = 0;
                  icon = getImage("data/monarchy.png");
                  label = new GameJLabel(icon); 
                  p.freeGovChange = true;
                  p.freeGov = 3;
                  break;
               case(18):
                  building = "university";      
                  resource = "";
                  level = 2;
                  techName = "printingPress";
                  gold = 0;
                  p.stackLimit = 4; 
                  icon = getImage("data/printingPress.png");
                  label = new GameJLabel(icon); 
                  p.upgradeBuildings(1);
                  break;
               case(19):
                  building = "";      
                  resource = "";
                  level = 2;
                  techName = "sailing"; //end level 2
                  gold = 0;
                  p.moveLimit = 4;
                  p.resetUnitMoves();
                  p.canWaterCross = true;
                  p.canWaterLand = true; 
                  icon = getImage("data/sailing.png");
                  label = new GameJLabel(icon); 
                  break;
               case(20):
                  building = "bank";      
                  resource = "Wheat";
                  level = 3;
                  techName = "banking";
                  gold = 0;
                  icon = getImage("data/banking.png");
                  label = new GameJLabel(icon); 
                  p.upgradeBuildings(2);
                  break;
               case(21):
                  building = "";      
                  resource = "";
                  level = 3;
                  techName = "biology";
                  gold = 0;
                  p.stackLimit = 5;
                  icon = getImage("data/biology.png");
                  label = new GameJLabel(icon); 
                  break;
               case(22):
                  building = "";      
                  resource = "Spy";
                  level = 3;
                  techName = "communism";
                  gold = 0;
                  icon = getImage("data/communism.png");
                  label = new GameJLabel(icon); 
                  if(!p.getNation().equals("Russia"))
                     p.freeGovChange = true;
                  p.freeGov = 5;
                  break;
               case(23):
                  building = "";      
                  resource = "Any";
                  level = 3;
                  techName = "gunpowder";
                  gold = 0;
                  p.infLevel = 3;
                  p.upgradeInf();
                  icon = getImage("data/gunpowder.png");
                  label = new GameJLabel(icon); 
                  break;
               case(24):
                  building = "";      
                  resource = "Incense";
                  level = 3;
                  techName = "metalCasting";
                  gold = 1;
                  p.updateTechGold(1);      
                  p.artLevel = 3;
                  p.upgradeArt();
                  icon = getImage("data/metalCasting.png");
                  label = new GameJLabel(icon); 
                  break;
               case(25):
                  building = "academy";      
                  resource = "";
                  level = 3;
                  techName = "militaryScience";
                  gold = 0;
                  icon = getImage("data/militaryScience.png");
                  label = new GameJLabel(icon); 
                  p.upgradeBuildings(3);
                  break;
               case(26):
                  building = "ironmine";      
                  resource = "";
                  level = 3;
                  techName = "railroad";
                  gold = 0;
                  p.mntLevel = 3;
                  p.upgradeMnt();
                  icon = getImage("data/railroad.png");
                  label = new GameJLabel(icon); 
                  p.upgradeBuildings(4);
                  break;
               case(27):
                  building = "";      
                  resource = "Silk";
                  level = 3;
                  techName = "steamPower";
                  gold = 0;
                  p.moveLimit = 5;
                  p.resetUnitMoves();
                  p.canWaterCross = true;
                  p.canWaterLand = true;
                  icon = getImage("data/steamPower.png");
                  label = new GameJLabel(icon); 
                  break;
               case(28):
                  building = "cathedral";      
                  resource = "";
                  level = 3;
                  techName = "theology"; //end level 3
                  gold = 0;
                  p.handLimitCulture++;
                  icon = getImage("data/theology.png");
                  label = new GameJLabel(icon); 
                  p.freeGovChange = true;
                  p.freeGov = 6;
                  p.upgradeBuildings(5);
                  break;
               case(29):
                  building = "";      
                  resource = "Uranium";
                  level = 4;
                  techName = "atomicTheory";
                  gold = 0;
                  icon = getImage("data/atomicTheory.png");
                  label = new GameJLabel(icon); 
                  break;
               case(30):
                  building = "";      
                  resource = "Iron";
                  level = 4;
                  techName = "ballistics";
                  gold = 0;
                  p.artLevel = 4;
                  p.upgradeArt();
                  icon = getImage("data/ballistics.png");
                  label = new GameJLabel(icon); 
                  break;
               case(31):
                  building = "";      
                  resource = "";
                  level = 4;
                  techName = "combustion";
                  gold = 0;
                  p.mntLevel = 4;
                  p.upgradeMnt();
                  icon = getImage("data/combustion.png");
                  label = new GameJLabel(icon); 
                  break;
               case(32):
                  building = "";      
                  resource = "";
                  level = 4;
                  techName = "computers";
                  gold = 1;
                  p.updateTechGold(1);      
                  icon = getImage("data/computers.png");
                  label = new GameJLabel(icon); 
                  break;
               case(33):
                  building = "";      
                  resource = "";
                  level = 4;
                  techName = "flight";
                  gold = 0;
                  p.moveLimit = 6;  
                  p.resetUnitMoves();
                  p.acftLevel = 1; 
                  p.upgradeAcft();
                  p.canWaterCross = true;
                  p.canWaterLand = true;
                  icon = getImage("data/flight.png");
                  label = new GameJLabel(icon); 
                  break;
               case(34):
                  building = "";      
                  resource = "Spy";
                  level = 4;
                  techName = "massMedia";
                  gold = 0;
                  icon = getImage("data/massMedia.png");
                  label = new GameJLabel(icon); 
                  break;
               case(35):
                  building = "";      
                  resource = "";
                  level = 4;
                  techName = "replaceableParts"; //end level 4
                  gold = 0;
                  p.infLevel = 4; 
						p.upgradeInf();
                  icon = getImage("data/replacableParts.png");
                  label = new GameJLabel(icon); 
                  break;
               case(36):
                  building = "";      
                  resource = "";
                  level = 5;
                  techName = "spaceFlight";
                  gold = 0;
                  icon = getImage("data/spaceFlight.png");
                  label = new GameJLabel(icon); 
                  break;
            }
         //      if(num < 10)
         //		  numLvl1++;
         //		else if(num >= 10 && num < 20)
         //		  numLvl2++;
         //		else if(num >=20 && num < 29)
         //		  numLvl3++;
         //		else if(num >= 29 && num < 36)
         //		  numLvl4++;
         //		else
         //gamewinner with technology        
            p.techListener[num] = this;
            p.addMListener(label); 
            label.setBounds(00, 00, icon.getIconWidth(), icon.getIconHeight());
			}
      
         public GameJLabel getLabel()
         {
            return this.label;
         }
      
         public int getGold()
         {
            return this.gold;
         }
      
         public void addGold(int g, Player p)
         {
            if(this.gold < 4)  
            {
               this.gold += g;
               p.updateTechGold(g);
 //              System.out.println("added gold");
            }
         }
     
			public String getTechName() {
				return techName;
			}
			public void setTechName(String techName) {
				this.techName = techName;
			}
			public int getVectorNum() {
				return vectorNum;
			}
			public void setVectorNum(int vectorNum) {
				this.vectorNum = vectorNum;
			}
			public int getLevel() {
				return level;
			}
			public void setLevel(int level) {
				this.level = level;
			}
			public String getResource() {
				return resource;
			}
			public void setResource(String resource) {
				this.resource = resource;
			}
			public String getBuilding() {
				return building;
			}
			public void setBuilding(String building) {
				this.building = building;
			}
			public void setGold(int gold) {
				this.gold = gold;
			}
			public GameImageIcon getIcon() {
				return icon;
			}
			public void setIcon(GameImageIcon icon) {
				this.icon = icon;
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
    }
