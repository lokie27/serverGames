  package games.civ;

  import javax.swing.*;
  import java.awt.Image;//bufferedimage;
  import java.net.URL;
  import java.awt.*;
  import java.awt.image.*;
  import java.io.Serializable;
  import games.*;

  public class Card implements Serializable
  {
    private static final long serialVersionUID = -4357517991407780490L;

    public String type;  
	 public GameRotatedIcon iconFront, iconBack, iconCombat;
	 private GameImageIcon iconBlowup;
	 private int value, wounds;  
    private GameJLabel label;
	 
    public Card(){}//generic
	 
	 public Card(String s, int val)
	 {
		wounds = 0;  
		if(s.equals("infantry"))
		{
        type = "infantry";
		  if(val == 1)
		  {
		    iconFront = new GameRotatedIcon(getImage("data/infantry_1.png"), 00);  
	       iconBack = new GameRotatedIcon(getImage("data/unitBack.png"), 00);  
          iconCombat = new GameRotatedIcon(getImage("data/combatBack.png"), 00);
          label = new GameJLabel(iconBack);
			 value = 1;
		  }
		  else if(val == 2)
        {
		    iconFront = new GameRotatedIcon(getImage("data/infantry_2.png"), 00);  
	       iconBack = new GameRotatedIcon(getImage("data/unitBack.png"), 00);  
          iconCombat = new GameRotatedIcon(getImage("data/combatBack.png"), 00);
          label = new GameJLabel(iconBack);
 			 value = 2;
		  }
		  else if(val == 3)
		  {
		    iconFront = new GameRotatedIcon(getImage("data/infantry_3.png"), 00);  
	       iconBack = new GameRotatedIcon(getImage("data/unitBack.png"), 00);  
          iconCombat = new GameRotatedIcon(getImage("data/combatBack.png"), 00);
          label = new GameJLabel(iconBack);
			 value = 3;
		  }
		}
	   else if(s.equals("mounted"))
		{
		  type = "mounted";
		  if(val == 1)
		  {
		    iconFront = new GameRotatedIcon(getImage("data/mounted_1.png"), 00);  
	       iconBack = new GameRotatedIcon(getImage("data/unitBack.png"), 00);  
          iconCombat = new GameRotatedIcon(getImage("data/combatBack.png"), 00);
          label = new GameJLabel(iconBack);
			 value = 1;
		  }
		  else if(val == 2)
        {
		    iconFront = new GameRotatedIcon(getImage("data/mounted_2.png"), 00);  
	       iconBack = new GameRotatedIcon(getImage("data/unitBack.png"), 00);  
          iconCombat = new GameRotatedIcon(getImage("data/combatBack.png"), 00);
          label = new GameJLabel(iconBack);
 			 value = 2;
		  }
		  else if(val == 3)
		  {
		    iconFront = new GameRotatedIcon(getImage("data/mounted_3.png"), 00);  
	       iconBack = new GameRotatedIcon(getImage("data/unitBack.png"), 00);  
          iconCombat = new GameRotatedIcon(getImage("data/combatBack.png"), 00);
          label = new GameJLabel(iconBack);
			 value = 3;
		  }
		}
	   else if(s.equals("artillery"))
		{
		  type = "artillery";
		  if(val == 1)
		  {
		    iconFront = new GameRotatedIcon(getImage("data/artillery_1.png"), 00);  
	       iconBack = new GameRotatedIcon(getImage("data/unitBack.png"), 00);  
          iconCombat = new GameRotatedIcon(getImage("data/combatBack.png"), 00);
          label = new GameJLabel(iconBack);
			 value = 1;
		  }
		  else if(val == 2)
        {
		    iconFront = new GameRotatedIcon(getImage("data/artillery_2.png"), 00);  
	       iconBack = new GameRotatedIcon(getImage("data/unitBack.png"), 00);  
          iconCombat = new GameRotatedIcon(getImage("data/combatBack.png"), 00);
          label = new GameJLabel(iconBack);
 			 value = 2;
		  }
		  else if(val == 3)
		  {
		    iconFront = new GameRotatedIcon(getImage("data/artillery_3.png"), 00);  
	       iconBack = new GameRotatedIcon(getImage("data/unitBack.png"), 00);  
          iconCombat = new GameRotatedIcon(getImage("data/combatBack.png"), 00);
          label = new GameJLabel(iconBack);
			 value = 3;
		  }
		}
	   else if(s.equals("aircraft"))
		{
        type = "aircraft";
		  if(val == 5)
		  {
		    iconFront = new GameRotatedIcon(getImage("data/aircraft_5.png"), 00);  
	       iconBack = new GameRotatedIcon(getImage("data/unitBack.png"), 00);  
          iconCombat = new GameRotatedIcon(getImage("data/combatBack.png"), 00);
          label = new GameJLabel(iconBack);
			 value = 5;
		  }
		  else if(val == 6)
        {
		    iconFront = new GameRotatedIcon(getImage("data/aircraft_6.png"), 00);  
	       iconBack = new GameRotatedIcon(getImage("data/unitBack.png"), 00);  
          iconCombat = new GameRotatedIcon(getImage("data/combatBack.png"), 00);
          label = new GameJLabel(iconBack);
 			 value = 6;
		  }
		  else if(val == 7)
		  {
		    iconFront = new GameRotatedIcon(getImage("data/aircraft_7.png"), 00);  
	       iconBack = new GameRotatedIcon(getImage("data/unitBack.png"), 00);  
          iconCombat = new GameRotatedIcon(getImage("data/combatBack.png"), 00);
          label = new GameJLabel(iconBack);
			 value = 7;
		  }
		}
	   else if(s.equals("wonder"))
		{
		  if(val == 0)
		  {
		    type = "Stonehenge";
		    value = 0;  
          iconBack = new GameRotatedIcon(getImage("data/ancientCard.png"), 00);
			 Image img = getImage("data/stonehengeCard.png").getImage(); 
			 BufferedImage bi = new BufferedImage(88, 53, BufferedImage.TYPE_INT_ARGB);
		    bi.getGraphics().drawImage(img, 0, 0, 88, 53, null);
		    GameImageIcon icon = new GameImageIcon(bi);
			 iconFront = new GameRotatedIcon(icon, 180);
          iconCombat = new GameRotatedIcon(getImage("data/stonehenge.png"));
		    iconBlowup = getImage("data/stonehengeCard.png");
		  }
		  else if(val == 1)
		  {
		    type = "Colossus";
		    value = 1;  
          iconBack = new GameRotatedIcon(getImage("data/ancientCard.png"), 00);
			 Image img = getImage("data/colossusCard.png").getImage(); 
			 BufferedImage bi = new BufferedImage(88, 53, BufferedImage.TYPE_INT_ARGB);
		    bi.getGraphics().drawImage(img, 0, 0, 88, 53, null);
		    GameImageIcon icon = new GameImageIcon(bi);
			 iconFront = new GameRotatedIcon(icon, 180);
          iconCombat = new GameRotatedIcon(getImage("data/colossus.png"));
		    iconBlowup = getImage("data/colossusCard.png");
		  }
		  else if(val == 2)
		  {
		    type = "Hanging Gardens";
		    value = 2;  
          iconBack = new GameRotatedIcon(getImage("data/ancientCard.png"), 00);
			 Image img = getImage("data/hanginggardensCard.png").getImage(); 
			 BufferedImage bi = new BufferedImage(88, 53, BufferedImage.TYPE_INT_ARGB);
		    bi.getGraphics().drawImage(img, 0, 0, 88, 53, null);
		    GameImageIcon icon = new GameImageIcon(bi);
			 iconFront = new GameRotatedIcon(icon, 180);
          iconCombat = new GameRotatedIcon(getImage("data/hanginggardens.png"));
		    iconBlowup = getImage("data/hanginggardensCard.png");
		  }
		  else if(val == 3)
		  {
		    type = "Oracle";
		    value = 3;  
          iconBack = new GameRotatedIcon(getImage("data/ancientCard.png"), 00);
			 Image img = getImage("data/oracleCard.png").getImage(); 
			 BufferedImage bi = new BufferedImage(88, 53, BufferedImage.TYPE_INT_ARGB);
		    bi.getGraphics().drawImage(img, 0, 0, 88, 53, null);
		    GameImageIcon icon = new GameImageIcon(bi);
			 iconFront = new GameRotatedIcon(icon, 180);
          iconCombat = new GameRotatedIcon(getImage("data/oracle.png"));
		    iconBlowup = getImage("data/oracleCard.png");
		  }
		  else if(val == 4)
		  {
		    type = "tower";
		    value = 4;  
          iconBack = new GameRotatedIcon(getImage("data/medievalCard.png"), 00);
			 Image img = getImage("data/towerCard.png").getImage(); 
			 BufferedImage bi = new BufferedImage(88, 53, BufferedImage.TYPE_INT_ARGB);
		    bi.getGraphics().drawImage(img, 0, 0, 88, 53, null);
		    GameImageIcon icon = new GameImageIcon(bi);
			 iconFront = new GameRotatedIcon(icon, 180);
          iconCombat = new GameRotatedIcon(getImage("data/tower.png"));
		    iconBlowup = getImage("data/towerCard.png");
		  }
		  else if(val == 5)
		  {
		    type = "angkorwat";
		    value = 5;  
          iconBack = new GameRotatedIcon(getImage("data/medievalCard.png"), 00);
			 Image img = getImage("data/angkorwatCard.png").getImage(); 
			 BufferedImage bi = new BufferedImage(88, 53, BufferedImage.TYPE_INT_ARGB);
		    bi.getGraphics().drawImage(img, 0, 0, 88, 53, null);
		    GameImageIcon icon = new GameImageIcon(bi);
			 iconFront = new GameRotatedIcon(icon, 180);
          iconCombat = new GameRotatedIcon(getImage("data/angkorwat.png"));
		    iconBlowup = getImage("data/angkorwatCard.png");
		  }
		  else if(val == 6)
		  {
		    type = "castle";
		    value = 6;  
          iconBack = new GameRotatedIcon(getImage("data/medievalCard.png"), 00);
			 Image img = getImage("data/castleCard.png").getImage(); 
			 BufferedImage bi = new BufferedImage(88, 53, BufferedImage.TYPE_INT_ARGB);
		    bi.getGraphics().drawImage(img, 0, 0, 88, 53, null);
		    GameImageIcon icon = new GameImageIcon(bi);
			 iconFront = new GameRotatedIcon(icon, 180);
          iconCombat = new GameRotatedIcon(getImage("data/castle.png"));
		    iconBlowup = getImage("data/castleCard.png");
		  }
		  else if(val == 7)
		  {
		    type = "louvre";
		    value = 7;  
          iconBack = new GameRotatedIcon(getImage("data/medievalCard.png"), 00);
			 Image img = getImage("data/louvreCard.png").getImage(); 
			 BufferedImage bi = new BufferedImage(88, 53, BufferedImage.TYPE_INT_ARGB);
		    bi.getGraphics().drawImage(img, 0, 0, 88, 53, null);
		    GameImageIcon icon = new GameImageIcon(bi);
			 iconFront = new GameRotatedIcon(icon, 180);
          iconCombat = new GameRotatedIcon(getImage("data/louvre.png"));
		    iconBlowup = getImage("data/louvreCard.png");
		  }
		  else if(val == 8)
		  {
		    type = "panama";
		    value = 8;  
          iconBack = new GameRotatedIcon(getImage("data/modernCard.png"), 00);
			 Image img = getImage("data/panamaCard.png").getImage(); 
			 BufferedImage bi = new BufferedImage(88, 53, BufferedImage.TYPE_INT_ARGB);
		    bi.getGraphics().drawImage(img, 0, 0, 88, 53, null);
		    GameImageIcon icon = new GameImageIcon(bi);
			 iconFront = new GameRotatedIcon(icon, 180);
          iconCombat = new GameRotatedIcon(getImage("data/panama.png"));
		    iconBlowup = getImage("data/panamaCard.png");
		  }
		  else if(val == 9)
		  {
		    type = "statue";
		    value = 9;  
          iconBack = new GameRotatedIcon(getImage("data/modernCard.png"), 00);
			 Image img = getImage("data/statueCard.png").getImage(); 
			 BufferedImage bi = new BufferedImage(88, 53, BufferedImage.TYPE_INT_ARGB);
		    bi.getGraphics().drawImage(img, 0, 0, 88, 53, null);
		    GameImageIcon icon = new GameImageIcon(bi);
			 iconFront = new GameRotatedIcon(icon, 180);
          iconCombat = new GameRotatedIcon(getImage("data/statue.png"));
		    iconBlowup = getImage("data/statueCard.png");
		  }
		  else if(val == 10)
		  {
		    type = "opera";
		    value = 10;  
          iconBack = new GameRotatedIcon(getImage("data/modernCard.png"), 00);
			 Image img = getImage("data/operaCard.png").getImage(); 
			 BufferedImage bi = new BufferedImage(88, 53, BufferedImage.TYPE_INT_ARGB);
		    bi.getGraphics().drawImage(img, 0, 0, 88, 53, null);
		    GameImageIcon icon = new GameImageIcon(bi);
			 iconFront = new GameRotatedIcon(icon, 180);
          iconCombat = new GameRotatedIcon(getImage("data/opera.png"));
		    iconBlowup = getImage("data/operaCard.png");
		  }
		  else if(val == 11)
		  {
		    type = "unitednations";
		    value = 11;  
          iconBack = new GameRotatedIcon(getImage("data/modernCard.png"), 00);
			 Image img = getImage("data/unitednationsCard.png").getImage(); 
			 BufferedImage bi = new BufferedImage(88, 53, BufferedImage.TYPE_INT_ARGB);
		    bi.getGraphics().drawImage(img, 0, 0, 88, 53, null);
		    GameImageIcon icon = new GameImageIcon(bi);
			 iconFront = new GameRotatedIcon(icon, 180);
          iconCombat = new GameRotatedIcon(getImage("data/unitednations.png"));
		    iconBlowup = getImage("data/unitednationsCard.png");
		  }
		  label = new GameJLabel(iconBack);
		}
	   else if(s.equals("culture1"))
		{
		  if(val == 0)
		  {
		    type = "Defection";
		    value = 0;  
          iconBack = new GameRotatedIcon(getImage("data/CUL1back.png"), 00);
			 Image img = getImage("data/CUL1defection.png").getImage(); 
			 BufferedImage bi = new BufferedImage(40, 60, BufferedImage.TYPE_INT_ARGB);
		    bi.getGraphics().drawImage(img, 0, 0, 40, 60, null);
		    GameImageIcon icon = new GameImageIcon(bi);
			 iconFront = new GameRotatedIcon(icon);
//          iconCombat = new GameRotatedIcon(getImage("data/CUL1.png"));
		    iconBlowup = getImage("data/CUL1defection.png");
		  }
		  if(val == 1)
		  {
		    type = "Breadandcircuses";
		    value = 1;  
          iconBack = new GameRotatedIcon(getImage("data/CUL1back.png"), 00);
			 Image img = getImage("data/CUL1breadandcircuses.png").getImage(); 
			 BufferedImage bi = new BufferedImage(40, 60, BufferedImage.TYPE_INT_ARGB);
		    bi.getGraphics().drawImage(img, 0, 0, 40, 60, null);
		    GameImageIcon icon = new GameImageIcon(bi);
			 iconFront = new GameRotatedIcon(icon);
//          iconCombat = new GameRotatedIcon(getImage("data/CUL1.png"));
		    iconBlowup = getImage("data/CUL1breadandcircuses.png");
		  }
		  if(val == 2)
		  {
		    type = "Sabotage";
		    value = 2;  
          iconBack = new GameRotatedIcon(getImage("data/CUL1back.png"), 00);
			 Image img = getImage("data/CUL1sabotage.png").getImage(); 
			 BufferedImage bi = new BufferedImage(40, 60, BufferedImage.TYPE_INT_ARGB);
		    bi.getGraphics().drawImage(img, 0, 0, 40, 60, null);
		    GameImageIcon icon = new GameImageIcon(bi);
			 iconFront = new GameRotatedIcon(icon);
//          iconCombat = new GameRotatedIcon(getImage("data/CUL1.png"));
		    iconBlowup = getImage("data/CUL1sabotage.png");
		  }
		  if(val == 3)
		  {
		    type = "Drought";
		    value = 3;  
          iconBack = new GameRotatedIcon(getImage("data/CUL1back.png"), 00);
			 Image img = getImage("data/CUL1drought.png").getImage(); 
			 BufferedImage bi = new BufferedImage(40, 60, BufferedImage.TYPE_INT_ARGB);
		    bi.getGraphics().drawImage(img, 0, 0, 40, 60, null);
		    GameImageIcon icon = new GameImageIcon(bi);
			 iconFront = new GameRotatedIcon(icon);
//          iconCombat = new GameRotatedIcon(getImage("data/CUL1.png"));
		    iconBlowup = getImage("data/CUL1drought.png");
		  }
		  if(val == 4)
		  {
		    type = "Disoriented";
		    value = 4;  
          iconBack = new GameRotatedIcon(getImage("data/CUL1back.png"), 00);
			 Image img = getImage("data/CUL1disoriented.png").getImage(); 
			 BufferedImage bi = new BufferedImage(40, 60, BufferedImage.TYPE_INT_ARGB);
		    bi.getGraphics().drawImage(img, 0, 0, 40, 60, null);
		    GameImageIcon icon = new GameImageIcon(bi);
			 iconFront = new GameRotatedIcon(icon);
//          iconCombat = new GameRotatedIcon(getImage("data/CUL1.png"));
		    iconBlowup = getImage("data/CUL1disoriented.png");
		  }
		  if(val == 5)
		  {
		    type = "Lovedespot";
		    value = 5;  
          iconBack = new GameRotatedIcon(getImage("data/CUL1back.png"), 00);
			 Image img = getImage("data/CUL1lovedespot.png").getImage(); 
			 BufferedImage bi = new BufferedImage(40, 60, BufferedImage.TYPE_INT_ARGB);
		    bi.getGraphics().drawImage(img, 0, 0, 40, 60, null);
		    GameImageIcon icon = new GameImageIcon(bi);
			 iconFront = new GameRotatedIcon(icon);
//          iconCombat = new GameRotatedIcon(getImage("data/CUL1.png"));
		    iconBlowup = getImage("data/CUL1lovedespot.png");
		  }
		  if(val == 6)
		  {
		    type = "Giftfromafar";
		    value = 6;  
          iconBack = new GameRotatedIcon(getImage("data/CUL1back.png"), 00);
			 Image img = getImage("data/CUL1agiftfromafar.png").getImage(); 
			 BufferedImage bi = new BufferedImage(40, 60, BufferedImage.TYPE_INT_ARGB);
		    bi.getGraphics().drawImage(img, 0, 0, 40, 60, null);
		    GameImageIcon icon = new GameImageIcon(bi);
			 iconFront = new GameRotatedIcon(icon);
//          iconCombat = new GameRotatedIcon(getImage("data/CUL1.png"));
		    iconBlowup = getImage("data/CUL1agiftfromafar.png");
		  }
		  if(val == 7)
		  {
		    type = "Citizenrevolt";
		    value = 7;  
          iconBack = new GameRotatedIcon(getImage("data/CUL1back.png"), 00);
			 Image img = getImage("data/CUL1citizenrevolt.png").getImage(); 
			 BufferedImage bi = new BufferedImage(40, 60, BufferedImage.TYPE_INT_ARGB);
		    bi.getGraphics().drawImage(img, 0, 0, 40, 60, null);
		    GameImageIcon icon = new GameImageIcon(bi);
			 iconFront = new GameRotatedIcon(icon);
//          iconCombat = new GameRotatedIcon(getImage("data/CUL1.png"));
		    iconBlowup = getImage("data/CUL1citizenrevolt.png");
		  }
		  if(val == 8)
		  {
		    type = "Exchangeideas";
		    value = 8;  
          iconBack = new GameRotatedIcon(getImage("data/CUL1back.png"), 00);
			 Image img = getImage("data/CUL1exchangeofideas.png").getImage(); 
			 BufferedImage bi = new BufferedImage(40, 60, BufferedImage.TYPE_INT_ARGB);
		    bi.getGraphics().drawImage(img, 0, 0, 40, 60, null);
		    GameImageIcon icon = new GameImageIcon(bi);
			 iconFront = new GameRotatedIcon(icon);
//          iconCombat = new GameRotatedIcon(getImage("data/CUL1.png"));
		    iconBlowup = getImage("data/CUL1exchangeofideas.png");
		  }
		  label = new GameJLabel(iconBack);
      }
	   else if(s.equals("culture2"))
		{
		  if(val == 0)
		  {
		    type = "Lost";
		    value = 0;  
          iconBack = new GameRotatedIcon(getImage("data/CUL2back.png"), 00);
			 Image img = getImage("data/CUL2lost.png").getImage(); 
			 BufferedImage bi = new BufferedImage(40, 60, BufferedImage.TYPE_INT_ARGB);
		    bi.getGraphics().drawImage(img, 0, 0, 40, 60, null);
		    GameImageIcon icon = new GameImageIcon(bi);
			 iconFront = new GameRotatedIcon(icon);
//          iconCombat = new GameRotatedIcon(getImage("data/CUL2.png"));
		    iconBlowup = getImage("data/CUL2lost.png");
		  }
		  if(val == 1)
		  {
		    type = "Catastrophe";
		    value = 1;  
          iconBack = new GameRotatedIcon(getImage("data/CUL2back.png"), 00);
			 Image img = getImage("data/CUL2catastrophe.png").getImage(); 
			 BufferedImage bi = new BufferedImage(40, 60, BufferedImage.TYPE_INT_ARGB);
		    bi.getGraphics().drawImage(img, 0, 0, 40, 60, null);
		    GameImageIcon icon = new GameImageIcon(bi);
			 iconFront = new GameRotatedIcon(icon);
//          iconCombat = new GameRotatedIcon(getImage("data/CUL2.png"));
		    iconBlowup = getImage("data/CUL2catastrophe.png");
		  }
		  if(val == 2)
		  {
		    type = "Jousting";
		    value = 2;  
          iconBack = new GameRotatedIcon(getImage("data/CUL2back.png"), 00);
			 Image img = getImage("data/CUL2jousting.png").getImage(); 
			 BufferedImage bi = new BufferedImage(40, 60, BufferedImage.TYPE_INT_ARGB);
		    bi.getGraphics().drawImage(img, 0, 0, 40, 60, null);
		    GameImageIcon icon = new GameImageIcon(bi);
			 iconFront = new GameRotatedIcon(icon);
//          iconCombat = new GameRotatedIcon(getImage("data/CUL2.png"));
		    iconBlowup = getImage("data/CUL2jousting.png");
		  }
		  if(val == 3)
		  {
		    type = "Massdefection";
		    value = 3;  
          iconBack = new GameRotatedIcon(getImage("data/CUL2back.png"), 00);
			 Image img = getImage("data/CUL2massdefection.png").getImage(); 
			 BufferedImage bi = new BufferedImage(40, 60, BufferedImage.TYPE_INT_ARGB);
		    bi.getGraphics().drawImage(img, 0, 0, 40, 60, null);
		    GameImageIcon icon = new GameImageIcon(bi);
			 iconFront = new GameRotatedIcon(icon);
//          iconCombat = new GameRotatedIcon(getImage("data/CUL2.png"));
		    iconBlowup = getImage("data/CUL2massdefection.png");
		  }
		  if(val == 4)
		  {
		    type = "Generousgift";
		    value = 4;  
          iconBack = new GameRotatedIcon(getImage("data/CUL2back.png"), 00);
			 Image img = getImage("data/CUL2agenerousgift.png").getImage(); 
			 BufferedImage bi = new BufferedImage(40, 60, BufferedImage.TYPE_INT_ARGB);
		    bi.getGraphics().drawImage(img, 0, 0, 40, 60, null);
		    GameImageIcon icon = new GameImageIcon(bi);
			 iconFront = new GameRotatedIcon(icon);
//          iconCombat = new GameRotatedIcon(getImage("data/CUL2.png"));
		    iconBlowup = getImage("data/CUL2agenerousgift.png");
		  }
		  if(val == 5)
		  {
		    type = "Lovequeen";
		    value = 5;  
          iconBack = new GameRotatedIcon(getImage("data/CUL2back.png"), 00);
			 Image img = getImage("data/CUL2lovequeen.png").getImage(); 
			 BufferedImage bi = new BufferedImage(40, 60, BufferedImage.TYPE_INT_ARGB);
		    bi.getGraphics().drawImage(img, 0, 0, 40, 60, null);
		    GameImageIcon icon = new GameImageIcon(bi);
			 iconFront = new GameRotatedIcon(icon);
//          iconCombat = new GameRotatedIcon(getImage("data/CUL2.png"));
		    iconBlowup = getImage("data/CUL2lovequeen.png");
		  }
		  if(val == 6)
		  {
		    type = "Deforestation";
		    value = 6;  
          iconBack = new GameRotatedIcon(getImage("data/CUL2back.png"), 00);
			 Image img = getImage("data/CUL2deforestation.png").getImage(); 
			 BufferedImage bi = new BufferedImage(40, 60, BufferedImage.TYPE_INT_ARGB);
		    bi.getGraphics().drawImage(img, 0, 0, 40, 60, null);
		    GameImageIcon icon = new GameImageIcon(bi);
			 iconFront = new GameRotatedIcon(icon);
//          iconCombat = new GameRotatedIcon(getImage("data/CUL2.png"));
		    iconBlowup = getImage("data/CUL2deforestation.png");
		  }
		  if(val == 7)
		  {
		    type = "Citizenrevolt2";
		    value = 7;  
          iconBack = new GameRotatedIcon(getImage("data/CUL2back.png"), 00);
			 Image img = getImage("data/CUL2citizensrevolting.png").getImage(); 
			 BufferedImage bi = new BufferedImage(40, 60, BufferedImage.TYPE_INT_ARGB);
		    bi.getGraphics().drawImage(img, 0, 0, 40, 60, null);
		    GameImageIcon icon = new GameImageIcon(bi);
			 iconFront = new GameRotatedIcon(icon);
//          iconCombat = new GameRotatedIcon(getImage("data/CUL2.png"));
		    iconBlowup = getImage("data/CUL2citizensrevolting.png");
		  }
		  if(val == 8)
		  {
		    type = "Knowledgeshared";
		    value = 8;  
          iconBack = new GameRotatedIcon(getImage("data/CUL2back.png"), 00);
			 Image img = getImage("data/CUL2knowledgeshared.png").getImage(); 
			 BufferedImage bi = new BufferedImage(40, 60, BufferedImage.TYPE_INT_ARGB);
		    bi.getGraphics().drawImage(img, 0, 0, 40, 60, null);
		    GameImageIcon icon = new GameImageIcon(bi);
			 iconFront = new GameRotatedIcon(icon);
//          iconCombat = new GameRotatedIcon(getImage("data/CUL2.png"));
		    iconBlowup = getImage("data/CUL2knowledgeshared.png");
		  }
		  label = new GameJLabel(iconBack);
      }
	   else if(s.equals("culture3"))
		{
		  if(val == 0)
		  {
		    type = "Outofposition";
		    value = 0;  
          iconBack = new GameRotatedIcon(getImage("data/CUL3back.png"), 00);
			 Image img = getImage("data/CUL3outofposition.png").getImage(); 
			 BufferedImage bi = new BufferedImage(40, 60, BufferedImage.TYPE_INT_ARGB);
		    bi.getGraphics().drawImage(img, 0, 0, 40, 60, null);
		    GameImageIcon icon = new GameImageIcon(bi);
			 iconFront = new GameRotatedIcon(icon);
//          iconCombat = new GameRotatedIcon(getImage("data/CUL3.png"));
		    iconBlowup = getImage("data/CUL3outofposition.png");
		  }
		  if(val == 1)
		  {
		    type = "Disaster";
		    value = 1;  
          iconBack = new GameRotatedIcon(getImage("data/CUL3back.png"), 00);
			 Image img = getImage("data/CUL3disaster.png").getImage(); 
			 BufferedImage bi = new BufferedImage(40, 60, BufferedImage.TYPE_INT_ARGB);
		    bi.getGraphics().drawImage(img, 0, 0, 40, 60, null);
		    GameImageIcon icon = new GameImageIcon(bi);
			 iconFront = new GameRotatedIcon(icon);
//          iconCombat = new GameRotatedIcon(getImage("data/CUL3.png"));
		    iconBlowup = getImage("data/CUL3disaster.png");
		  }
		  if(val == 2)
		  {
		    type = "Wholesale";
		    value = 2;  
          iconBack = new GameRotatedIcon(getImage("data/CUL3back.png"), 00);
			 Image img = getImage("data/CUL3wholesale.png").getImage(); 
			 BufferedImage bi = new BufferedImage(40, 60, BufferedImage.TYPE_INT_ARGB);
		    bi.getGraphics().drawImage(img, 0, 0, 40, 60, null);
		    GameImageIcon icon = new GameImageIcon(bi);
			 iconFront = new GameRotatedIcon(icon);
//          iconCombat = new GameRotatedIcon(getImage("data/CUL3.png"));
		    iconBlowup = getImage("data/CUL3wholesale.png");
		  }
		  if(val == 3)
		  {
		    type = "Thinktank";
		    value = 3;  
          iconBack = new GameRotatedIcon(getImage("data/CUL3back.png"), 00);
			 Image img = getImage("data/CUL3thinktank.png").getImage(); 
			 BufferedImage bi = new BufferedImage(40, 60, BufferedImage.TYPE_INT_ARGB);
		    bi.getGraphics().drawImage(img, 0, 0, 40, 60, null);
		    GameImageIcon icon = new GameImageIcon(bi);
			 iconFront = new GameRotatedIcon(icon);
//          iconCombat = new GameRotatedIcon(getImage("data/CUL3.png"));
		    iconBlowup = getImage("data/CUL3thinktank.png");
		  }
		  if(val == 4)
		  {
		    type = "Primetime";
		    value = 4;  
          iconBack = new GameRotatedIcon(getImage("data/CUL3back.png"), 00);
			 Image img = getImage("data/CUL3primetime.png").getImage(); 
			 BufferedImage bi = new BufferedImage(40, 60, BufferedImage.TYPE_INT_ARGB);
		    bi.getGraphics().drawImage(img, 0, 0, 40, 60, null);
		    GameImageIcon icon = new GameImageIcon(bi);
			 iconFront = new GameRotatedIcon(icon);
//          iconCombat = new GameRotatedIcon(getImage("data/CUL3.png"));
		    iconBlowup = getImage("data/CUL3primetime.png");
		  }
		  if(val == 5)
		  {
		    type = "Lovepresident";
		    value = 5;  
          iconBack = new GameRotatedIcon(getImage("data/CUL3back.png"), 00);
			 Image img = getImage("data/CUL3lovepresident.png").getImage(); 
			 BufferedImage bi = new BufferedImage(40, 60, BufferedImage.TYPE_INT_ARGB);
		    bi.getGraphics().drawImage(img, 0, 0, 40, 60, null);
		    GameImageIcon icon = new GameImageIcon(bi);
			 iconFront = new GameRotatedIcon(icon);
//          iconCombat = new GameRotatedIcon(getImage("data/CUL3.png"));
		    iconBlowup = getImage("data/CUL3lovepresident.png");
		  }
		  if(val == 6)
		  {
		    type = "Princelygift";
		    value = 6;  
          iconBack = new GameRotatedIcon(getImage("data/CUL3back.png"), 00);
			 Image img = getImage("data/CUL3princelygift.png").getImage(); 
			 BufferedImage bi = new BufferedImage(40, 60, BufferedImage.TYPE_INT_ARGB);
		    bi.getGraphics().drawImage(img, 0, 0, 40, 60, null);
		    GameImageIcon icon = new GameImageIcon(bi);
			 iconFront = new GameRotatedIcon(icon);
//          iconCombat = new GameRotatedIcon(getImage("data/CUL3.png"));
		    iconBlowup = getImage("data/CUL3princelygift.png");
		  }
		  label = new GameJLabel(iconBack);
      }
	 }
	   
    //getter-setter methods
  	 public int getWounds()
	 {
	    return wounds;
	 }

    public void addWounds(int w)
	 {
	    wounds += w;
	 }

    public void subWound(int w)
	 {
	    wounds -= w;
	 }
	   
	 public void resetWounds()
	 {
	    wounds = 0;
	 }
  
	 public String getType()
	 {
	    return type;
	 }

    public int getValue()
	 {
	    return value;
	 }
 
    public GameImageIcon getBlowupIcon()
	 {
	    return iconBlowup;  
	 }

    public GameRotatedIcon getFrontIcon()
	 {
	    return iconFront;  
	 }

    public GameRotatedIcon getBackIcon()
	 {
		 return iconBack;
	 }

    public GameRotatedIcon getCombatBackIcon()
	 {
//System.out.println(iconCombat);
	    return iconCombat;
	 }

    public GameJLabel getLabel()
	 {
	    return label;
	 }

	 public void setLabelIcon(GameRotatedIcon im)
	 {
	   this.label.setIcon(im);
	 }
	 
    public void setLabelIcon(GameImageIcon im)
	 {
	   this.label.setIcon(im);
	 }

	 public void setValues(Card c)
	 {
	    this.type = c.type;  
		 this.iconFront = c.iconFront;
		 this.iconBack = c.iconBack;
		 this.iconCombat = c.iconCombat;
		 this.value = c.value;
		 this.wounds = 0;  
	    this.label = c.label;
	 }

/*    public void upgradeUnit(String type, int level)
	 {
	   if(type.equals("infantry"))
		{
//System.out.println("UPGRADE: " +this.value);
		  if(level == 2)
		  {
//System.out.println("LEVEL2");
		    this.value++;
		    if(this.value == 2)
			   this.iconFront = new GameRotatedIcon(new GameImageIcon("data/infantry_1.png"), 270);
		    else if(this.value == 3)
			   this.iconFront = new GameRotatedIcon(new GameImageIcon("data/infantry_2.png"), 270);
          else if(this.value == 4)			 
			   this.iconFront = new GameRotatedIcon(new GameImageIcon("data/infantry_3.png"), 250);
		    this.label.setIcon(this.iconFront);
//		    return c;
		  }
		  if(level == 3)
		  {
		    this.value+=2;
		    this.iconFront = new GameRotatedIcon(this.iconFront, 180);
		  }
		  if(level == 4)
		  {
		    this.value+=3;
		    this.iconFront = new GameRotatedIcon(this.iconFront, 90);
		  }
		}
//	   return null;
	 }*/
	 
    public boolean trumpsCard(Card c)
	 {
	    if(type.equals("infantry") && c.getType().equals("mounted"))
		   return true;
	    else if(type.equals("mounted") && c.getType().equals("artillery"))
		   return true;
	    else if(type.equals("artillery") && c.getType().equals("infantry"))
		   return true;
	    return false;
	 }
 
	public GameImageIcon getImage(String pathName){
     GameImageIcon image;
     URL url = getClass().getResource(pathName);
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

	public void setType(String type) {
		this.type = type;
	}
	public GameRotatedIcon getIconFront() {
		return iconFront;
	}
	public void setIconFront(GameRotatedIcon iconFront) {
		this.iconFront = iconFront;
	}
	public GameRotatedIcon getIconBack() {
		return iconBack;
	}
	public void setIconBack(GameRotatedIcon iconBack) {
		this.iconBack = iconBack;
	}
	public GameRotatedIcon getIconCombat() {
		return iconCombat;
	}
	public void setIconCombat(GameRotatedIcon iconCombat) {
		this.iconCombat = iconCombat;
	}
	public GameImageIcon getIconBlowup() {
		return iconBlowup;
	}
	public void setIconBlowup(GameImageIcon iconBlowup) {
		this.iconBlowup = iconBlowup;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public void setWounds(int wounds) {
		this.wounds = wounds;
	}
	public void setLabel(GameJLabel label) {
		this.label = label;
	}

	private void readObject(java.io.ObjectInputStream stream) throws java.io.IOException, ClassNotFoundException{
		try{
			type = stream.readUTF();    
			iconFront = (GameRotatedIcon)stream.readObject();
			iconBack = (GameRotatedIcon)stream.readObject();
			iconCombat = (GameRotatedIcon)stream.readObject();
			iconBlowup = (GameImageIcon)stream.readObject();
			value = stream.readInt();
			wounds = stream.readInt();  
			label = (GameJLabel)stream.readObject();
      }catch(java.io.IOException ioEx){
			System.out.println("IO EXCEPTION Deck\n");
			ioEx.printStackTrace();
		}catch(NullPointerException NPEx){
			System.out.println("NullPointerException Deck\n");
			NPEx.printStackTrace();
		}
   }

   private void writeObject(java.io.ObjectOutputStream stream) throws java.io.IOException{
		try{
			stream.writeUTF(type);    
			stream.writeObject(iconFront);
			stream.writeObject(iconBack);
			stream.writeObject(iconCombat);
			stream.writeObject(iconBlowup);
			stream.writeInt(value);
			stream.writeInt(wounds);  
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
