package games.civ;

import java.io.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import games.*;

public class LogicBoard implements Serializable
{
  private static final long serialVersionUID = -7058518804799305573L;

  private BoardPeice[][] bp;
  private GameImageIcon iconPlayer1, iconPlayer2, iconPlayer3, iconPlayer4,
		      iconPlayer5, iconPlayer6, iconPlayer7, iconPlayer8,
				iconPlayer9, iconPlayer10, iconPlayer11, iconPlayer12,
				iconPlayer13, iconPlayer14, iconPlayer15, iconPlayer16; 
  private Player play;    
  
  public LogicBoard(){}//bean default
  
  public LogicBoard(String tiles, int startSquare, Player name)
  {
//starting players tiles, the revealed ones will be handled outside constructor
	 bp = new BoardPeice[8][16];

    play = name;
    int pos = 3;  
	 StringTokenizer peices = new StringTokenizer(tiles, ".");

	
//	 System.out.println(peices.countTokens());
//	 while(pos <= 1)//will be numplayers
//	 {
		String token = peices.nextToken();

//	   System.out.println("TOKEN: "+token);
	    for(int j = 0; j < 8; j++)
		  {
		    bp[j][0] = new BoardPeice("setup", -1, j, 0, -1, name);
		    bp[j][1] = new BoardPeice("setup", -1, j, 1, -1, name);
		    bp[j][2] = new BoardPeice("setup", -1, j, 2, -1, name);
		    bp[j][3] = new BoardPeice("setup", -1, j, 3, -1, name);
		    bp[j][4] = new BoardPeice("setup", -1, j, 4, -1, name);
		    bp[j][5] = new BoardPeice("setup", -1, j, 5, -1, name);
		    bp[j][6] = new BoardPeice("setup", -1, j, 6, -1, name);
		    bp[j][7] = new BoardPeice("setup", -1, j, 7, -1, name);
		    bp[j][8] = new BoardPeice("setup", -1, j, 8, -1, name);
		    bp[j][9] = new BoardPeice("setup", -1, j, 9, -1, name);
		    bp[j][10] = new BoardPeice("setup", -1, j, 10, -1, name);
		    bp[j][11] = new BoardPeice("setup", -1, j, 11, -1, name);
		    bp[j][12] = new BoardPeice("setup", -1, j, 12, -1, name);
		    bp[j][13] = new BoardPeice("setup", -1, j, 13, -1, name);
		    bp[j][14] = new BoardPeice("setup", -1, j, 14, -1, name);
		    bp[j][15] = new BoardPeice("setup", -1, j, 15, -1, name);
		  }
		  for(int j = 4; j < 16; j++)
		  {
		    bp[4][j] = new BoardPeice("setup", -1, 4, j, -1, name);
		  }
		  for(int j = 0; j < 12; j++)
		  {
		    bp[3][j] = new BoardPeice("setup", -1, 3, j, -1, name);
		  }
		  for(int j = 0; j < 4; j++)
		  {
		    bp[j][3] = new BoardPeice("setup", -1, j, 3, -1, name);
		  }

	   if(pos == 4)
	   {
		  for(int i = 4; i < 8; i++)
		  {
		    for(int k = 0; k < 4; k++) 
			 {
			   bp[i][k] = new BoardPeice(token, pos, i, k, startSquare, name);
          }    
		  }    
		}  
	   else if(pos == 3)  
		{
		  for(int i = 0; i < 4; i++)
		  {
		    for(int k = 12; k < 16; k++) 
			 {
			   bp[i][k] = new BoardPeice(token, pos, i, k, startSquare, name);
          }    
		  }
		}
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

		public BoardPeice[][] getBp() {
			return bp;
		}
		public void setBp(BoardPeice[][] bp) {
			this.bp = bp;
		}
		public GameImageIcon getIconPlayer1() {
			return iconPlayer1;
		}
		public void setIconPlayer1(GameImageIcon iconPlayer1) {
			this.iconPlayer1 = iconPlayer1;
		}
		public GameImageIcon getIconPlayer2() {
			return iconPlayer2;
		}
		public void setIconPlayer2(GameImageIcon iconPlayer2) {
			this.iconPlayer2 = iconPlayer2;
		}
		public GameImageIcon getIconPlayer3() {
			return iconPlayer3;
		}
		public void setIconPlayer3(GameImageIcon iconPlayer3) {
			this.iconPlayer3 = iconPlayer3;
		}
		public GameImageIcon getIconPlayer4() {
			return iconPlayer4;
		}
		public void setIconPlayer4(GameImageIcon iconPlayer4) {
			this.iconPlayer4 = iconPlayer4;
		}
		public GameImageIcon getIconPlayer5() {
			return iconPlayer5;
		}
		public void setIconPlayer5(GameImageIcon iconPlayer5) {
			this.iconPlayer5 = iconPlayer5;
		}
		public GameImageIcon getIconPlayer6() {
			return iconPlayer6;
		}
		public void setIconPlayer6(GameImageIcon iconPlayer6) {
			this.iconPlayer6 = iconPlayer6;
		}
		public GameImageIcon getIconPlayer7() {
			return iconPlayer7;
		}
		public void setIconPlayer7(GameImageIcon iconPlayer7) {
			this.iconPlayer7 = iconPlayer7;
		}
		public GameImageIcon getIconPlayer8() {
			return iconPlayer8;
		}
		public void setIconPlayer8(GameImageIcon iconPlayer8) {
			this.iconPlayer8 = iconPlayer8;
		}
		public GameImageIcon getIconPlayer9() {
			return iconPlayer9;
		}
		public void setIconPlayer9(GameImageIcon iconPlayer9) {
			this.iconPlayer9 = iconPlayer9;
		}
		public GameImageIcon getIconPlayer10() {
			return iconPlayer10;
		}
		public void setIconPlayer10(GameImageIcon iconPlayer10) {
			this.iconPlayer10 = iconPlayer10;
		}
		public GameImageIcon getIconPlayer11() {
			return iconPlayer11;
		}
		public void setIconPlayer11(GameImageIcon iconPlayer11) {
			this.iconPlayer11 = iconPlayer11;
		}
		public GameImageIcon getIconPlayer12() {
			return iconPlayer12;
		}
		public void setIconPlayer12(GameImageIcon iconPlayer12) {
			this.iconPlayer12 = iconPlayer12;
		}
		public GameImageIcon getIconPlayer13() {
			return iconPlayer13;
		}
		public void setIconPlayer13(GameImageIcon iconPlayer13) {
			this.iconPlayer13 = iconPlayer13;
		}
		public GameImageIcon getIconPlayer14() {
			return iconPlayer14;
		}
		public void setIconPlayer14(GameImageIcon iconPlayer14) {
			this.iconPlayer14 = iconPlayer14;
		}
		public GameImageIcon getIconPlayer15() {
			return iconPlayer15;
		}
		public void setIconPlayer15(GameImageIcon iconPlayer15) {
			this.iconPlayer15 = iconPlayer15;
		}
		public GameImageIcon getIconPlayer16() {
			return iconPlayer16;
		}
		public void setIconPlayer16(GameImageIcon iconPlayer16) {
			this.iconPlayer16 = iconPlayer16;
		}
		public Player getPlay() {
			return play;
		}
		public void setPlay(Player play) {
			this.play = play;
		} 

    public int getCityNumber(int x, int y)
	 {
	   return bp[x][y].getCityIndex();
	 }
	   
	 public int getBoardxpos(GameJLayeredPane pane)
	 {
	   for(int k = 0; k < 8; k++)
		{
		  for(int i = 0; i < 16; i++)
		  {
		    if(bp[k][i] != null)
			 {
			   if(bp[k][i].holder == pane)
				  return bp[k][i].getXpos();
			 }
		  }
		}
	   return -1;
	 }
	   
    public int getBoardypos(GameJLayeredPane pane)
	 {
	   for(int k = 0; k < 8; k++)
		{
		  for(int i = 0; i < 16; i++)
		  {
		    if(bp[k][i] != null)
			 {
			   if(bp[k][i].holder == pane)
				  return bp[k][i].getYpos();
			 }
		  }
		}
	   return -1;
	 }

	 public int getBuildingNumber(GameJLayeredPane pane)
	 {
	   for(int k = 0; k < 8; k++)
		{
		  for(int i = 0; i < 16; i++)
		  {
		    if(bp[k][i] != null)
			 {
			   if(bp[k][i].holder == pane)
				  return bp[k][i].getBuildNumber();
			 }
		  }
		}
	   return -1;
	 }
	 
	 public String getHutResource(int x, int y)
	 {
	   return bp[x][y].getHutType(); 
	 }
	 
    public String getVillageResource(int x, int y)
	 {
	   return bp[x][y].getVillageType(); 
	 }

    public int hasVillage(int x, int y)
	 {
	   return bp[x][y].getVillage();
	 }
	   
    public int hasHut(int x, int y)
	 {
	   return bp[x][y].getHut();
	 }

    public void removeHut(int x, int y)
	 {
	   bp[x][y].subHut();
	 }

    public void removeVillage(int x, int y)
	 {
	   bp[x][y].subVillage();
	 }

	 public String getTerrain(int x, int y)
	 {
	   return bp[x][y].getTerrain();
	 }
	   
	 public boolean isCitySpace(int x, int y)
	 {
	   return bp[x][y].getisCitySpace();
	 }

    public void addSettlerCount(int x, int y)
	 {
	   bp[x][y].addSettler();
	 }

    public void addArmyCount(int x, int y)
	 {
	   bp[x][y].addArmy();
	 }

    public void subSettlerCount(int x, int y)
	 {
	   bp[x][y].subSettler();
	 }

    public void subArmyCount(int x, int y)
	 {
	   bp[x][y].subArmy();
	 }
  
	 public int getSettlerCount(int x, int y)
	 {
	   return bp[x][y].getSettler();
	 }

    public int getArmyCount(int x, int y)
	 {
	   return bp[x][y].getArmy();
	 }

    public boolean isCityAdjacent(int x, int y)
	 {
		if(y == 15 && (x == 0 || x == 7))
		{
		  if(x == 0)
		  { 
		    if(bp[x+1][y-1].getisCitySpace())
		      return true;
		  }
		  else if(x == 7)
		  { 
		    if(bp[x-1][y-1].getisCitySpace())
		      return true;
		  }
		}
		else if(y == 0 && (x == 0 || x == 7))
		{
		  if(x == 0)
		  { 
		    if(bp[x+1][y+1].getisCitySpace())
		      return true;
		  }
		  else if(x == 7)
		  { 
		    if(bp[x-1][y+1].getisCitySpace())
		      return true;
		  }
		}
		else if(y == 15)
		{
		    if(bp[x+1][y-1].getisCitySpace())
		      return true;
		    if(bp[x][y-1].getisCitySpace())
		      return true;
		    if(bp[x-1][y-1].getisCitySpace())
		      return true;
		}
		else if(y == 0)
		{
		    if(bp[x+1][y+1].getisCitySpace())
		      return true;
		    if(bp[x][y+1].getisCitySpace())
		      return true;
		    if(bp[x-1][y+1].getisCitySpace())
		      return true;
		}
		else if(x == 0)
		{
		    if(bp[x+1][y-1].getisCitySpace())
		      return true;
		    if(bp[x+1][y].getisCitySpace())
		      return true;
		    if(bp[x+1][y+1].getisCitySpace())
		      return true;
		}
		else if(x == 7)
		{
		    if(bp[x-1][y-1].getisCitySpace())
		      return true;
		    if(bp[x-1][y].getisCitySpace())
		      return true;
		    if(bp[x-1][y+1].getisCitySpace())
		      return true;
		}
      else
		{
			int k, i; 
			for(k = x-1; k <= x+1; k++)
			{  
			  i = y-1;
		     if(bp[k][i].getisCitySpace())
	          return true;
			}
		   for(k = x-1; k <= x+1; k++)
			{  
			  i = y+1;
		     if(bp[k][i].getisCitySpace())
	          return true;
			}
	      if(bp[x-1][y].getisCitySpace())
	        return true;  
		   if(bp[x+1][y].getisCitySpace())
	        return true; 
      }
 	   return false;
	 }
	    
	 public boolean canBuildCity(int x, int y, Player p)
	 {
       if(y == 15 || x == 0 || y == 0 || x == 7)
		   return false;
	    for(int k = 1; k < 2; k++)
       {
		   if(bp[x-k][y].getisMapBorder())
			  return false; 
		   else if(bp[x+k][y].getisMapBorder())
			  return false; 
		   else if(bp[x][y+k].getisMapBorder())
			  return false; 
		   else if(bp[x][y-k].getisMapBorder())
			  return false; 
		   else if(bp[x-k][y-k].getisMapBorder())
			  return false; 
		   else if(bp[x+k][y+k].getisMapBorder())
			  return false; 
		   else if(bp[x-k][y+k].getisMapBorder())
			  return false; 
		   else if(bp[x+k][y-k].getisMapBorder())
			  return false; 
		 }
		 for(int k = 0; k < 1; k++)
       {
		   if(bp[x-1][y-1].getisCitySpace() || bp[x-1][y-1].getVillage() == 1 || bp[x-1][y-1].getHut() == 1 || (bp[x-1][y-1].getOwner() != null && bp[x-1][y-1].getOwner() != p))
			  return false; 
		   else if(bp[x][y-1].getisCitySpace() || bp[x][y-1].getVillage() == 1 || bp[x][y-1].getHut() == 1 || (bp[x][y-1].getOwner() != null && bp[x][y-1].getOwner() != p))
			  return false; 
		   else if(bp[x+1][y-1].getisCitySpace() || bp[x+1][y-1].getVillage() == 1 || bp[x+1][y-1].getHut() == 1 || (bp[x+1][y-1].getOwner() != null && bp[x+1][y-1].getOwner() != p))
			  return false; 
		   else if(bp[x+1][y].getisCitySpace() || bp[x+1][y].getVillage() == 1 || bp[x+1][y].getHut() == 1 || (bp[x+1][y].getOwner() != null && bp[x+1][y].getOwner() != p))
			  return false; 
		   else if(bp[x+1][y+1].getisCitySpace() || bp[x+1][y+1].getVillage() == 1 || bp[x+1][y+1].getHut() == 1 || (bp[x+1][y+1].getOwner() != null && bp[x+1][y+1].getOwner() != p))
			  return false; 
		   else if(bp[x][y+1].getisCitySpace() || bp[x][y+1].getVillage() == 1 || bp[x][y+1].getHut() == 1 || (bp[x][y+1].getOwner() != null && bp[x][y+1].getOwner() != p))
			  return false; 
		   else if(bp[x-1][y+1].getisCitySpace() || bp[x-1][y+1].getVillage() == 1 || bp[x-1][y+1].getHut() == 1 || (bp[x-1][y+1].getOwner() != null && bp[x-1][y+1].getOwner() != p))
			  return false; 
		   else if(bp[x-1][y].getisCitySpace() || bp[x-1][y].getVillage() == 1 || bp[x-1][y].getHut() == 1 || (bp[x-1][y].getOwner() != null && bp[x-1][y].getOwner() != p))
			  return false; 
		 }
	    for(int k = 0; k < 1; k++)
       {
		   if((x > 1 && y > 1) && (x < 6 && y < 14))
		   {
			  if(bp[x-2][y-2].getisCitySpace())
			    return false; 
   		  else if(bp[x-1][y-2].getisCitySpace())
			    return false; 
		     else if(bp[x][y-2].getisCitySpace())
			    return false; 
		     else if(bp[x+1][y-2].getisCitySpace())
			    return false; 
		     else if(bp[x+2][y-2].getisCitySpace())
			    return false; 
		     else if(bp[x+2][y-1].getisCitySpace())
			    return false; 
		     else if(bp[x+2][y].getisCitySpace())
			    return false; 
		     else if(bp[x+2][y+1].getisCitySpace())
			    return false; 
		     else if(bp[x+2][y+2].getisCitySpace())
			    return false; 
		     else if(bp[x+1][y+2].getisCitySpace())
			    return false; 
		     else if(bp[x][y+2].getisCitySpace())
			    return false; 
		     else if(bp[x-1][y+2].getisCitySpace())
			    return false; 
		     else if(bp[x-2][y+2].getisCitySpace())
			    return false; 
		     else if(bp[x-2][y+1].getisCitySpace())
			    return false; 
		     else if(bp[x-2][y].getisCitySpace())
			    return false; 
		     else if(bp[x-2][y-1].getisCitySpace())
			    return false; 
         }  
		   else if(x == 1 && y < 2)
		   {
			  if(bp[x+2][y-1].getisCitySpace())
			    return false; 
   		  else if(bp[x+2][y].getisCitySpace())
			    return false; 
		     else if(bp[x+2][y+1].getisCitySpace())
			    return false; 
		     else if(bp[x+2][y+2].getisCitySpace())
			    return false; 
		     else if(bp[x+1][y+2].getisCitySpace())
			    return false; 
		     else if(bp[x][y+2].getisCitySpace())
			    return false; 
		     else if(bp[x-1][y+2].getisCitySpace())
			    return false; 
         }  
		   else if(x == 1 && y == 14)
		   {
			  if(bp[x+2][y].getisCitySpace())
			    return false; 
   		  else if(bp[x+2][y-1].getisCitySpace())
			    return false; 
		     else if(bp[x+2][y-2].getisCitySpace())
			    return false; 
		     else if(bp[x+1][y-2].getisCitySpace())
			    return false; 
		     else if(bp[x][y-2].getisCitySpace())
			    return false; 
         }  
		   else if(x == 1 && (y > 1 && y < 14))
		   {
			  if(bp[x-1][y-2].getisCitySpace())
			    return false; 
		     else if(bp[x][y-2].getisCitySpace())
			    return false; 
		     else if(bp[x+1][y-2].getisCitySpace())
			    return false; 
		     else if(bp[x+2][y-2].getisCitySpace())
			    return false; 
		     else if(bp[x+2][y-1].getisCitySpace())
			    return false; 
		     else if(bp[x+2][y].getisCitySpace())
			    return false; 
		     else if(bp[x+2][y+1].getisCitySpace())
			    return false; 
		     else if(bp[x+2][y+2].getisCitySpace())
			    return false; 
		     else if(bp[x+1][y+2].getisCitySpace())
			    return false; 
		     else if(bp[x][y+2].getisCitySpace())
			    return false; 
		     else if(bp[x-1][y+2].getisCitySpace())
			    return false; 
         }
		   else if(x == 6 && y == 14)
		   {
			  if(bp[x-2][y].getisCitySpace())
			    return false; 
   		  else if(bp[x-2][y-1].getisCitySpace())
			    return false; 
		     else if(bp[x-2][y-2].getisCitySpace())
			    return false; 
		     else if(bp[x-1][y-2].getisCitySpace())
			    return false; 
		     else if(bp[x][y-2].getisCitySpace())
			    return false; 
         }  
		   else if(x == 6 && (y > 1 && y < 14))
		   {
			  if(bp[x][y+2].getisCitySpace())
			    return false; 
   		  else if(bp[x-1][y+2].getisCitySpace())
			    return false; 
		     else if(bp[x-2][y+2].getisCitySpace())
			    return false; 
		     else if(bp[x-2][y+1].getisCitySpace())
			    return false; 
		     else if(bp[x-2][y].getisCitySpace())
			    return false; 
		     else if(bp[x-2][y-1].getisCitySpace())
			    return false; 
		     else if(bp[x-2][y-2].getisCitySpace())
			    return false; 
		     else if(bp[x-1][y-2].getisCitySpace())
			    return false; 
		     else if(bp[x][y-2].getisCitySpace())
			    return false; 
         }  
			else if(x > 1 && y == 14)
		   {
			  if(bp[x+2][y].getisCitySpace())
			    return false; 
   		  else if(bp[x+2][y-1].getisCitySpace())
			    return false; 
		     else if(bp[x+2][y-2].getisCitySpace())
			    return false; 
		     else if(bp[x+1][y-2].getisCitySpace())
			    return false; 
		     else if(bp[x][y-2].getisCitySpace())
			    return false; 
		     else if(bp[x-1][y-2].getisCitySpace())
			    return false; 
		     else if(bp[x-2][y-2].getisCitySpace())
			    return false; 
		     else if(bp[x-2][y-1].getisCitySpace())
			    return false; 
		     else if(bp[x-2][y].getisCitySpace())
			    return false; 
		     else if(bp[x-2][y-1].getisCitySpace())
			    return false; 
         }
/*			else if(x > 1 && y < 14)
		   {
			  if(bp[x][y+2].getisCitySpace())
			    return false; 
   		  else if(bp[x+1][y+2].getisCitySpace())
			    return false; 
		     else if(bp[x+2][y+2].getisCitySpace())
			    return false; 
		     else if(bp[x+2][y+1].getisCitySpace())
			    return false; 
		     else if(bp[x+2][y].getisCitySpace())
			    return false; 
		     else if(bp[x+2][y-1].getisCitySpace())
			    return false; 
		     else if(bp[x+2][y-2].getisCitySpace())
			    return false; 
		     else if(bp[x+1][y-2].getisCitySpace())
			    return false; 
		     else if(bp[x][y-2].getisCitySpace())
			    return false; 
         }  */
		 }
	    return true;
	 }

    public int getSpaceCulture(int x, int y)
	 {
       return bp[x][y].getCulture();
	 }
//for getting a cities production
    public int getCulture(int x, int y)
	 {
	   int culture = 1;//for the city  
		int k, i;
		for(k = x-1; k <= x+1; k++)
		{  
		  i = y-1;
		  culture = culture + bp[k][i].getCulture();
		}
	   for(k = x-1; k <= x+1; k++)
		{  
		  i = y+1;
		  culture = culture + bp[k][i].getCulture();
		}
	   culture = culture + bp[x-1][y].getCulture();
  	   culture = culture + bp[x+1][y].getCulture();
      
//	   System.out.println("CULTURE: "+culture);
	   return culture;
	 }

    public int getSpaceProduction(int x, int y)
	 {
	   return bp[x][y].getProduction();
	 }
	  
	 public int getProduction(int x, int y)
	 {
	   int prod = 0;  
		int k, i;
		for(k = x-1; k <= x+1; k++)
		{  
		  i = y-1;
	     prod = prod + bp[k][i].getProduction();
		}
	   for(k = x-1; k <= x+1; k++)
		{  
		  i = y+1;
		  prod = prod + bp[k][i].getProduction();
		}
	   prod = prod + bp[x-1][y].getProduction();
  	   prod = prod + bp[x+1][y].getProduction();
      
//	   System.out.println("PROD: "+prod);
	   return prod;
	 }
       
    public int getSpaceTrade(int x, int y)
	 {
	   return bp[x][y].getTrade();
    }
//for getting a cities trade
    public int getTrade(int x, int y)
	 {
	   int trade = 0;  
		int k, i;
		for(k = x-1; k <= x+1; k++)
		{  
		  i = y-1;
		  trade = trade + bp[k][i].getTrade();
		}
	   for(k = x-1; k <= x+1; k++)
		{  
		  i = y+1;
		  trade = trade + bp[k][i].getTrade();
		}
	   trade = trade + bp[x-1][y].getTrade();
  	   trade = trade + bp[x+1][y].getTrade();
      
//	   System.out.println("TRADE: "+trade);
	   return trade;
	 }

    public boolean getSpaceGold(int x, int y)
	 {
	   return bp[x][y].getGold();
    }

    public boolean canHarvestX(int x, int y, Player p, int city)
    {
		if(city >= 0){

			int k, i;
			for(k = x-1; k <= x+1; k++)
			{  
			  i = y-1;
			  if(bp[k][i].getResource() != null && !bp[k][i].hasBuild)
			    return true;
			}
		   for(k = x-1; k <= x+1; k++)
			{  
			  i = y+1;
			  if(bp[k][i].getResource() != null && !bp[k][i].hasBuild)
			    return true;
			}
			if(bp[x-1][y].getResource() != null && !bp[x-1][y].hasBuild)
			   return true;
			if(bp[x+1][y].getResource() != null && !bp[x+1][y].hasBuild)
			   return true;
		   Vector temp = p.getCitySettlerLink(city);
			if(temp.size() > 0)
			{
			  Settler s;
			  for(k = 0; k < temp.size(); k++)
			  {
	          s = (Settler)temp.get(k);
				 if(bp[s.getX()][s.getY()].getResource() != null && !p.playerUI.lb.isCityAdjacent(s.getX(), s.getY()))
			      return true;
			  }
			}
      }

		return false;
	 }

    public boolean canHarvestIncense(int x, int y, Player p, int city)
    {
		int k, i;
		for(k = x-1; k <= x+1; k++)
		{  
		  i = y-1;
		  if(bp[k][i].getResource() != null && bp[k][i].getResource().equals("Incense") && !bp[k][i].hasBuild)
		    return true;
		}
	   for(k = x-1; k <= x+1; k++)
		{  
		  i = y+1;
		  if(bp[k][i].getResource() != null && bp[k][i].getResource().equals("Incense") && !bp[k][i].hasBuild)
		    return true;
		}
		if(bp[x-1][y].getResource() != null && bp[x-1][y].getResource().equals("Incense") && !bp[x-1][y].hasBuild)
		   return true;
		if(bp[x+1][y].getResource() != null && bp[x+1][y].getResource().equals("Incense") && !bp[x+1][y].hasBuild)
		   return true;
	   Vector temp = p.getCitySettlerLink(city);
		if(temp.size() > 0)
		{
		  Settler s;
		  for(k = 0; k < temp.size(); k++)
		  {
          s = (Settler)temp.get(k);
			 if(bp[s.getX()][s.getY()].getResource() != null && bp[s.getX()][s.getY()].getResource().equals("Incense") && !p.playerUI.lb.isCityAdjacent(s.getX(), s.getY()))
		      return true;
		  }
		}
	   return false;
	 }

    public boolean canHarvestIron(int x, int y, Player p, int city)
    {
		int k, i;
		for(k = x-1; k <= x+1; k++)
		{  
		  i = y-1;
		  if(bp[k][i].getResource() != null && bp[k][i].getResource().equals("Iron") && !bp[k][i].hasBuild)
		    return true;
		}
	   for(k = x-1; k <= x+1; k++)
		{  
		  i = y+1;
		  if(bp[k][i].getResource() != null && bp[k][i].getResource().equals("Iron") && !bp[k][i].hasBuild)
		    return true;
		}
		if(bp[x-1][y].getResource() != null && bp[x-1][y].getResource().equals("Iron") && !bp[x-1][y].hasBuild)
		   return true;
		if(bp[x+1][y].getResource() != null && bp[x+1][y].getResource().equals("Iron") && !bp[x+1][y].hasBuild)
		   return true;
	   Vector temp = p.getCitySettlerLink(city);
		if(temp.size() > 0)
		{
		  Settler s;
		  for(k = 0; k < temp.size(); k++)
		  {
          s = (Settler)temp.get(k);
			 if(bp[s.getX()][s.getY()].getResource() != null && bp[s.getX()][s.getY()].getResource().equals("Iron") && !p.playerUI.lb.isCityAdjacent(s.getX(), s.getY()))
		      return true;
		  }
		}
	   return false;
	 }

    public boolean canHarvestSilk(int x, int y, Player p, int city)
    {
		int k, i;
		for(k = x-1; k <= x+1; k++)
		{  
		  i = y-1;
		  if(bp[k][i].getResource() != null && bp[k][i].getResource().equals("Silk") && !bp[k][i].hasBuild)
		    return true;
		}
	   for(k = x-1; k <= x+1; k++)
		{  
		  i = y+1;
		  if(bp[k][i].getResource() != null && bp[k][i].getResource().equals("Silk") && !bp[k][i].hasBuild)
		    return true;
		}
		if(bp[x-1][y].getResource() != null && bp[x-1][y].getResource().equals("Silk") && !bp[x-1][y].hasBuild)
		   return true;
		if(bp[x+1][y].getResource() != null && bp[x+1][y].getResource().equals("Silk") && !bp[x+1][y].hasBuild)
		   return true;
	   Vector temp = p.getCitySettlerLink(city);
		if(temp.size() > 0)
		{
		  Settler s;
		  for(k = 0; k < temp.size(); k++)
		  {
          s = (Settler)temp.get(k);
			 if(bp[s.getX()][s.getY()].getResource() != null && bp[s.getX()][s.getY()].getResource().equals("Silk") && !p.playerUI.lb.isCityAdjacent(s.getX(), s.getY()))
		      return true;
		  }
		}
	   return false;
	 }

    public boolean canHarvestWheat(int x, int y, Player p, int city)
    {
		int k, i;
		for(k = x-1; k <= x+1; k++)
		{  
		  i = y-1;
		  if(bp[k][i].getResource() != null && bp[k][i].getResource().equals("Wheat") && !bp[k][i].hasBuild)
		    return true;
		}
	   for(k = x-1; k <= x+1; k++)
		{  
		  i = y+1;
		  if(bp[k][i].getResource() != null && bp[k][i].getResource().equals("Wheat") && !bp[k][i].hasBuild)
		    return true;
		}
		if(bp[x-1][y].getResource() != null && bp[x-1][y].getResource().equals("Wheat") && !bp[x-1][y].hasBuild)
		   return true;
		if(bp[x+1][y].getResource() != null && bp[x+1][y].getResource().equals("Wheat") && !bp[x+1][y].hasBuild)
		   return true;
	   Vector temp = p.getCitySettlerLink(city);
		if(temp.size() > 0)
		{
		  Settler s;
		  for(k = 0; k < temp.size(); k++)
		  {
          s = (Settler)temp.get(k);
			 if(bp[s.getX()][s.getY()].getResource() != null && bp[s.getX()][s.getY()].getResource().equals("Wheat") && !p.playerUI.lb.isCityAdjacent(s.getX(), s.getY()))
		      return true;
		  }
		}
	   return false;
	 }
/*the int type represents the peice
0: Settler
1: army 
2: city   
(add)
*/  public boolean getHasBuild(int x, int y)
    {
       if(bp[x][y].hasBuild)
		   return true;
		 return false;	 
    }
	
	 public boolean hasBuildTerrain(int x, int y, String type)
	 {
		int k, i;
		for(k = x-1; k <= x+1; k++)
		{  
		  i = y-1;
		  if(type.equals("Any"))
		  {
		    if(bp[k][i].getTerrain() != null && !bp[k][i].getTerrain().equals("Water") && !bp[k][i].hasBuild())
		      return true;
		  }
		  else
		    if(bp[k][i].getTerrain() != null && bp[k][i].getTerrain().equals(type) && !bp[k][i].hasBuild())
		      return true;
		}
	   for(k = x-1; k <= x+1; k++)
		{  
		  i = y+1;
		  if(type.equals("Any"))
		  {
		    if(bp[k][i].getTerrain() != null && !bp[k][i].getTerrain().equals("Water") && !bp[k][i].hasBuild())
		      return true;
		  }
		  else 
		    if(bp[k][i].getTerrain() != null && bp[k][i].getTerrain().equals(type) && !bp[k][i].hasBuild())
		      return true;
		}
		if(type.equals("Any"))
		{
		  if(bp[x-1][y].getTerrain() != null && !bp[x-1][y].getTerrain().equals("Water") && !bp[x-1][y].hasBuild())
		    return true;
      }    
		else
		  if(bp[x-1][y].getTerrain() != null && bp[x-1][y].getTerrain().equals(type) && !bp[x-1][y].hasBuild())
		     return true;
		if(type.equals("Any"))
		{
		  if(bp[x+1][y].getTerrain() != null && !bp[x+1][y].getTerrain().equals("Water") && !bp[x+1][y].hasBuild())
		    return true;
		}
		else
		  if(bp[x+1][y].getTerrain() != null && bp[x+1][y].getTerrain().equals(type) && !bp[x+1][y].hasBuild())
		    return true;
	   return false;
	 }
	 
	 public void removeBuilding(int x, int y)
	 {
	   bp[x][y].removeBuild();
	 }
	 
	 public void addBuilding(int x, int y, int type, GameJLabel label, boolean upgrade, Player p, int index)
	 {

	   if(!upgrade)
		{
		  switch(type)
		  {
		    case 0:
		      bp[x][y].addBuilding(label, p, " ", type, index);
            break;			  
		    case 1:
		      bp[x][y].addBuilding(label, p, " ", type, index);
            break;			  
		    case 2:
		      bp[x][y].addBuilding(label, p, " ", type, index);
            break;			  
		    case 3:
		      bp[x][y].addBuilding(label, p, " ", type, index);
            break;			  
		    case 4:
		      bp[x][y].addBuilding(label, p, " ", type, index);
            break;			  
		    case 5:
		      bp[x][y].addBuilding(label, p, " ", type, index);
            break;			  
		    case 6:
		      bp[x][y].addBuilding(label, p, " ", type, index);
            break;			  
		    case 7:
		      bp[x][y].addBuilding(label, p, " ", type, index);
            break;			  
		    case 8:
		      bp[x][y].addBuilding(label, p, " ", type, index);
            break;			  
		    case 9:
		      bp[x][y].addBuilding(label, p, " ", type, index);
            break;			  
		    case 10:
		      bp[x][y].addBuilding(label, p, " ", type, index);
            break;			  
		    case 11:
		      bp[x][y].addBuilding(label, p, " ", type, index);
            break;			  
		    case 12:
		      bp[x][y].addBuilding(label, p, " ", type, index);
            break;			  
		    case 13:
		      bp[x][y].addBuilding(label, p, " ", type, index);
            break;			  
		    case 14:
		      bp[x][y].addBuilding(label, p, "greatperson", type, index);
            break;			  
		    case 15:
		      bp[x][y].addBuilding(label, p, "greatperson", type, index);
            break;			  
		    case 16:
		      bp[x][y].addBuilding(label, p, "greatperson", type, index);
            break;			  
		    case 17:
		      bp[x][y].addBuilding(label, p, "greatperson", type, index);
            break;			  
		    case 18:
		      bp[x][y].addBuilding(label, p, "greatperson", type, index);
            break;			  
		    case 19:
		      bp[x][y].addBuilding(label, p, "greatperson", type, index);
            break;			  
		    case 20:
		      bp[x][y].addBuilding(label, p, "wonder", type, index);
            break;			  
		    case 21:
		      bp[x][y].addBuilding(label, p, "wonder", type, index);
            break;			  
		    case 22:
		      bp[x][y].addBuilding(label, p, "wonder", type, index);
            break;			  
		    case 23:
		      bp[x][y].addBuilding(label, p, "wonder", type, index);
            break;			  
		    case 24:
		      bp[x][y].addBuilding(label, p, "wonder", type, index);
            break;			  
		    case 25:
		      bp[x][y].addBuilding(label, p, "wonder", type, index);
            break;			  
		    case 26:
		      bp[x][y].addBuilding(label, p, "wonder", type, index);
            break;			  
		    case 27:
		      bp[x][y].addBuilding(label, p, "wonder", type, index);
            break;			  
		    case 28:
		      bp[x][y].addBuilding(label, p, "wonder", type, index);
            break;			  
		    case 29:
		      bp[x][y].addBuilding(label, p, "wonder", type, index);
            break;			  
		    case 30:
		      bp[x][y].addBuilding(label, p, "wonder", type, index);
            break;			  
		    case 31:
		      bp[x][y].addBuilding(label, p, "wonder", type, index);
            break;			  
        }    
		}
      else{}
		  //do upgrades
      label.setBounds(0, 0, 55, 55);
		switch(type)
      {
		  case 0:
          bp[x][y].getPane().add(label, new Integer(1));      
		    break;
		  case 1:
          bp[x][y].getPane().add(label, new Integer(1));      
		    break;
		  case 2:
          bp[x][y].getPane().add(label, new Integer(1));      
		    break;
		  case 3:
          bp[x][y].getPane().add(label, new Integer(1));      
		    break;
		  case 4:
          bp[x][y].getPane().add(label, new Integer(1));      
		    break;
		  case 5:
          bp[x][y].getPane().add(label, new Integer(1));      
		    break;
		  case 6:
          bp[x][y].getPane().add(label, new Integer(1));      
		    break;
		  case 7:
          bp[x][y].getPane().add(label, new Integer(1));      
		    break;
		  case 8:
          bp[x][y].getPane().add(label, new Integer(1));      
		    break;
		  case 9:
          bp[x][y].getPane().add(label, new Integer(1));      
		    break;
		  case 10:
          bp[x][y].getPane().add(label, new Integer(1));      
		    break;
		  case 11:
          bp[x][y].getPane().add(label, new Integer(1));      
		    break;
		  case 12:
          bp[x][y].getPane().add(label, new Integer(1));      
		    break;
		  case 13:
          bp[x][y].getPane().add(label, new Integer(1));      
		    break;
		  case 14:
          bp[x][y].getPane().add(label, new Integer(1));      
		    break;
		  case 15:
          bp[x][y].getPane().add(label, new Integer(1));      
		    break;
		  case 16:
          bp[x][y].getPane().add(label, new Integer(1));      
		    break;
		  case 17:
          bp[x][y].getPane().add(label, new Integer(1));      
		    break;
		  case 18:
          bp[x][y].getPane().add(label, new Integer(1));      
		    break;
		  case 19:
          bp[x][y].getPane().add(label, new Integer(1));      
		    break;
		  case 20:
          bp[x][y].getPane().add(label, new Integer(1));      
		    break;
		  case 21:
          bp[x][y].getPane().add(label, new Integer(1));      
		    break;
		  case 22:
          bp[x][y].getPane().add(label, new Integer(1));      
		    break;
		  case 23:
          bp[x][y].getPane().add(label, new Integer(1));      
		    break;
		  case 24:
          bp[x][y].getPane().add(label, new Integer(1));      
		    break;
		  case 25:
          bp[x][y].getPane().add(label, new Integer(1));      
		    break;
		  case 26:
          bp[x][y].getPane().add(label, new Integer(1));      
		    break;
		  case 27:
          bp[x][y].getPane().add(label, new Integer(1));      
		    break;
		  case 28:
          bp[x][y].getPane().add(label, new Integer(1));      
		    break;
		  case 29:
          bp[x][y].getPane().add(label, new Integer(1));      
		    break;
		  case 30:
          bp[x][y].getPane().add(label, new Integer(1));      
		    break;
		  case 31:
          bp[x][y].getPane().add(label, new Integer(1));      
		    break;
		}
      bp[x][y].getPane().repaint();      
	 }
	 
	 public void addPeice(int type, int x, int y)
	 {
	   switch(type)
		{
		  case 0:
		    bp[x][y].addSettler();  
			 break;
		  case 1:
		    bp[x][y].addArmy();  
			 break;
 		  case 2:
		    bp[x][y].addCity();  
		    break; 
		}
	 }  

	 //get holder of labels for units
	 public GameJLayeredPane getHolder(int x, int y)
	 {
	   return bp[x][y].getPane();
	 }
	 
	 //get holder of labels for units
	 public GameImageIcon getIcon(int x, int y)
	 {
		 return bp[x][y].getIcon();
	 }

	 public GameJLabel getLabel(int x, int y)
	 {
		 return bp[x][y].getLabel();
	 }

	 //check if unexplored tile
	 public boolean checkExplored(int x, int y)
	 {
	   return bp[x][y].getisMapBorder();
	 }
	 
	 public Player getOwner(int x, int y)
	 {
	   return bp[x][y].getOwner();
	 }

    protected void setOwner(int x, int y, Player own)
	 {
	   bp[x][y].setOwner(own);
	 }

	 //populate the tiles from random non-nation tiles
	 protected void populateTile(String tile, int pos, int startSquare, Player p)
	 {
		if(tile.equals("rand1"))
	   {
			if(pos == 0)
			{
			  for(int i = 0; i < 4; i++)
		     {
		       for(int k = 0; k < 4; k++) 
			    {
			      bp[i][k] = new BoardPeice(tile, pos, i, k, startSquare, p);
             }    
		     }		
			  populateRand1(0, p);
  	      }      
			else if(pos == 1)
			{
			  for(int i = 0; i < 4; i++)
		     {
		       for(int k = 4; k < 8; k++) 
			    {
			      bp[i][k] = new BoardPeice(tile, pos, i, k, startSquare, p);
             }    
		     }		
			  populateRand1(1, p);
	      } 
         else if(pos == 2)
			{
			  for(int i = 0; i < 4; i++)
		     {
		       for(int k = 8; k < 12; k++) 
			    {
			      bp[i][k] = new BoardPeice(tile, pos, i, k, startSquare, p);
             }    
		     }				
			  populateRand1(2, p);
	      }
		   else if(pos == 5)
			{
			  for(int i = 4; i < 8; i++)
		     {
		       for(int k = 4; k < 8; k++) 
			    {
			      bp[i][k] = new BoardPeice(tile, pos, i, k, startSquare, p);
             }    
		     }				
			  populateRand1(5, p);
         }      
			else if(pos == 6)
			{
			  for(int i = 4; i < 8; i++)
		     {
		       for(int k = 8; k < 12; k++) 
			    {
			      bp[i][k] = new BoardPeice(tile, pos, i, k, startSquare, p);
             }    
		     }				
			  populateRand1(6, p);
         }
		   else if(pos == 7)
			{
			  for(int i = 4; i < 8; i++)
		     {
		       for(int k = 12; k < 16; k++) 
			    {
			      bp[i][k] = new BoardPeice(tile, pos, i, k, startSquare, p);
             }    
		     }				
			  populateRand1(7, p);
         } 
		}
		else if(tile.equals("rand2"))
	   {
			if(pos == 0)
			{
			  for(int i = 0; i < 4; i++)
		     {
		       for(int k = 0; k < 4; k++) 
			    {
			      bp[i][k] = new BoardPeice(tile, pos, i, k, startSquare, p);
             }    
		     }		
			  populateRand2(0, p);
  	      }      
			else if(pos == 1)
			{
			  for(int i = 0; i < 4; i++)
		     {
		       for(int k = 4; k < 8; k++) 
			    {
			      bp[i][k] = new BoardPeice(tile, pos, i, k, startSquare, p);
             }    
		     }		
			  populateRand2(1, p);
	      } 
         else if(pos == 2)
			{
			  for(int i = 0; i < 4; i++)
		     {
		       for(int k = 8; k < 12; k++) 
			    {
			      bp[i][k] = new BoardPeice(tile, pos, i, k, startSquare, p);
             }    
		     }				
			  populateRand2(2, p);
	      }
		   else if(pos == 5)
			{
			  for(int i = 4; i < 8; i++)
		     {
		       for(int k = 4; k < 8; k++) 
			    {
			      bp[i][k] = new BoardPeice(tile, pos, i, k, startSquare, p);
             }    
		     }				
			  populateRand2(5, p);
         }      
			else if(pos == 6)
			{
			  for(int i = 4; i < 8; i++)
		     {
		       for(int k = 8; k < 12; k++) 
			    {
			      bp[i][k] = new BoardPeice(tile, pos, i, k, startSquare, p);
             }    
		     }				
			  populateRand2(6, p);
         }
		   else if(pos == 7)
			{
			  for(int i = 4; i < 8; i++)
		     {
		       for(int k = 12; k < 16; k++) 
			    {
			      bp[i][k] = new BoardPeice(tile, pos, i, k, startSquare, p);
             }    
		     }				
			  populateRand2(7, p);
         } 
		}
  		else if(tile.equals("rand3"))
	   {
			if(pos == 0)
			{
			  for(int i = 0; i < 4; i++)
		     {
		       for(int k = 0; k < 4; k++) 
			    {
			      bp[i][k] = new BoardPeice(tile, pos, i, k, startSquare, p);
             }    
		     }		
			  populateRand3(0, p);
  	      }      
			else if(pos == 1)
			{
			  for(int i = 0; i < 4; i++)
		     {
		       for(int k = 4; k < 8; k++) 
			    {
			      bp[i][k] = new BoardPeice(tile, pos, i, k, startSquare, p);
             }    
		     }		
			  populateRand3(1, p);
	      } 
         else if(pos == 2)
			{
			  for(int i = 0; i < 4; i++)
		     {
		       for(int k = 8; k < 12; k++) 
			    {
			      bp[i][k] = new BoardPeice(tile, pos, i, k, startSquare, p);
             }    
		     }				
			  populateRand3(2, p);
	      }
		   else if(pos == 5)
			{
			  for(int i = 4; i < 8; i++)
		     {
		       for(int k = 4; k < 8; k++) 
			    {
			      bp[i][k] = new BoardPeice(tile, pos, i, k, startSquare, p);
             }    
		     }				
			  populateRand3(5, p);
         }      
			else if(pos == 6)
			{
			  for(int i = 4; i < 8; i++)
		     {
		       for(int k = 8; k < 12; k++) 
			    {
			      bp[i][k] = new BoardPeice(tile, pos, i, k, startSquare, p);
             }    
		     }				
			  populateRand3(6, p);
         }
		   else if(pos == 7)
			{
			  for(int i = 4; i < 8; i++)
		     {
		       for(int k = 12; k < 16; k++) 
			    {
			      bp[i][k] = new BoardPeice(tile, pos, i, k, startSquare, p);
             }    
		     }				
			  populateRand3(7, p);
         } 
		}
		else if(tile.equals("rand4"))
	   {
			if(pos == 0)
			{
			  for(int i = 0; i < 4; i++)
		     {
		       for(int k = 0; k < 4; k++) 
			    {
			      bp[i][k] = new BoardPeice(tile, pos, i, k, startSquare, p);
             }    
		     }		
			  populateRand4(0, p);
  	      }      
			else if(pos == 1)
			{
			  for(int i = 0; i < 4; i++)
		     {
		       for(int k = 4; k < 8; k++) 
			    {
			      bp[i][k] = new BoardPeice(tile, pos, i, k, startSquare, p);
             }    
		     }		
			  populateRand4(1, p);
	      } 
         else if(pos == 2)
			{
			  for(int i = 0; i < 4; i++)
		     {
		       for(int k = 8; k < 12; k++) 
			    {
			      bp[i][k] = new BoardPeice(tile, pos, i, k, startSquare, p);
             }    
		     }				
			  populateRand4(2, p);
	      }
		   else if(pos == 5)
			{
			  for(int i = 4; i < 8; i++)
		     {
		       for(int k = 4; k < 8; k++) 
			    {
			      bp[i][k] = new BoardPeice(tile, pos, i, k, startSquare, p);
             }    
		     }				
			  populateRand4(5, p);
         }      
			else if(pos == 6)
			{
			  for(int i = 4; i < 8; i++)
		     {
		       for(int k = 8; k < 12; k++) 
			    {
			      bp[i][k] = new BoardPeice(tile, pos, i, k, startSquare, p);
             }    
		     }				
			  populateRand4(6, p);
         }
		   else if(pos == 7)
			{
			  for(int i = 4; i < 8; i++)
		     {
		       for(int k = 12; k < 16; k++) 
			    {
			      bp[i][k] = new BoardPeice(tile, pos, i, k, startSquare, p);
             }    
		     }				
			  populateRand4(7, p);
         } 
		}
		else if(tile.equals("rand5"))
	   {
			if(pos == 0)
			{
			  for(int i = 0; i < 4; i++)
		     {
		       for(int k = 0; k < 4; k++) 
			    {
			      bp[i][k] = new BoardPeice(tile, pos, i, k, startSquare, p);
             }    
		     }		
			  populateRand5(0, p);
  	      }      
			else if(pos == 1)
			{
			  for(int i = 0; i < 4; i++)
		     {
		       for(int k = 4; k < 8; k++) 
			    {
			      bp[i][k] = new BoardPeice(tile, pos, i, k, startSquare, p);
             }    
		     }		
			  populateRand5(1, p);
	      } 
         else if(pos == 2)
			{
			  for(int i = 0; i < 4; i++)
		     {
		       for(int k = 8; k < 12; k++) 
			    {
			      bp[i][k] = new BoardPeice(tile, pos, i, k, startSquare, p);
             }    
		     }				
			  populateRand5(2, p);
	      }
		   else if(pos == 5)
			{
			  for(int i = 4; i < 8; i++)
		     {
		       for(int k = 4; k < 8; k++) 
			    {
			      bp[i][k] = new BoardPeice(tile, pos, i, k, startSquare, p);
             }    
		     }				
			  populateRand5(5, p);
         }      
			else if(pos == 6)
			{
			  for(int i = 4; i < 8; i++)
		     {
		       for(int k = 8; k < 12; k++) 
			    {
			      bp[i][k] = new BoardPeice(tile, pos, i, k, startSquare, p);
             }    
		     }				
			  populateRand5(6, p);
         }
		   else if(pos == 7)
			{
			  for(int i = 4; i < 8; i++)
		     {
		       for(int k = 12; k < 16; k++) 
			    {
			      bp[i][k] = new BoardPeice(tile, pos, i, k, startSquare, p);
             }    
		     }				
			  populateRand5(7, p);
         } 
		}
		else if(tile.equals("rand6"))
	   {
			if(pos == 0)
			{
			  for(int i = 0; i < 4; i++)
		     {
		       for(int k = 0; k < 4; k++) 
			    {
			      bp[i][k] = new BoardPeice(tile, pos, i, k, startSquare, p);
             }    
		     }		
			  populateRand6(0, p);
  	      }      
			else if(pos == 1)
			{
			  for(int i = 0; i < 4; i++)
		     {
		       for(int k = 4; k < 8; k++) 
			    {
			      bp[i][k] = new BoardPeice(tile, pos, i, k, startSquare, p);
             }    
		     }		
			  populateRand6(1, p);
	      } 
         else if(pos == 2)
			{
			  for(int i = 0; i < 4; i++)
		     {
		       for(int k = 8; k < 12; k++) 
			    {
			      bp[i][k] = new BoardPeice(tile, pos, i, k, startSquare, p);
             }    
		     }				
			  populateRand6(2, p);
	      }
		   else if(pos == 5)
			{
			  for(int i = 4; i < 8; i++)
		     {
		       for(int k = 4; k < 8; k++) 
			    {
			      bp[i][k] = new BoardPeice(tile, pos, i, k, startSquare, p);
             }    
		     }				
			  populateRand6(5, p);
         }      
			else if(pos == 6)
			{
			  for(int i = 4; i < 8; i++)
		     {
		       for(int k = 8; k < 12; k++) 
			    {
			      bp[i][k] = new BoardPeice(tile, pos, i, k, startSquare, p);
             }    
		     }				
			  populateRand6(6, p);
         }
		   else if(pos == 7)
			{
			  for(int i = 4; i < 8; i++)
		     {
		       for(int k = 12; k < 16; k++) 
			    {
			      bp[i][k] = new BoardPeice(tile, pos, i, k, startSquare, p);
             }    
		     }				
			  populateRand6(7, p);
         } 
		}
		else if(tile.equals("rand7"))
	   {
			if(pos == 0)
			{
			  for(int i = 0; i < 4; i++)
		     {
		       for(int k = 0; k < 4; k++) 
			    {
			      bp[i][k] = new BoardPeice(tile, pos, i, k, startSquare, p);
             }    
		     }		
			  populateRand7(0, p);
  	      }      
			else if(pos == 1)
			{
			  for(int i = 0; i < 4; i++)
		     {
		       for(int k = 4; k < 8; k++) 
			    {
			      bp[i][k] = new BoardPeice(tile, pos, i, k, startSquare, p);
             }    
		     }		
			  populateRand7(1, p);
	      } 
         else if(pos == 2)
			{
			  for(int i = 0; i < 4; i++)
		     {
		       for(int k = 8; k < 12; k++) 
			    {
			      bp[i][k] = new BoardPeice(tile, pos, i, k, startSquare, p);
             }    
		     }				
			  populateRand7(2, p);
	      }
		   else if(pos == 5)
			{
			  for(int i = 4; i < 8; i++)
		     {
		       for(int k = 4; k < 8; k++) 
			    {
			      bp[i][k] = new BoardPeice(tile, pos, i, k, startSquare, p);
             }    
		     }				
			  populateRand7(5, p);
         }      
			else if(pos == 6)
			{
			  for(int i = 4; i < 8; i++)
		     {
		       for(int k = 8; k < 12; k++) 
			    {
			      bp[i][k] = new BoardPeice(tile, pos, i, k, startSquare, p);
             }    
		     }				
			  populateRand7(6, p);
         }
		   else if(pos == 7)
			{
			  for(int i = 4; i < 8; i++)
		     {
		       for(int k = 12; k < 16; k++) 
			    {
			      bp[i][k] = new BoardPeice(tile, pos, i, k, startSquare, p);
             }    
		     }				
			  populateRand7(7, p);
         } 
		}
		else if(tile.equals("rand8"))
	   {
			if(pos == 0)
			{
			  for(int i = 0; i < 4; i++)
		     {
		       for(int k = 0; k < 4; k++) 
			    {
			      bp[i][k] = new BoardPeice(tile, pos, i, k, startSquare, p);
             }    
		     }		
			  populateRand8(0, p);
  	      }      
			else if(pos == 1)
			{
			  for(int i = 0; i < 4; i++)
		     {
		       for(int k = 4; k < 8; k++) 
			    {
			      bp[i][k] = new BoardPeice(tile, pos, i, k, startSquare, p);
             }    
		     }		
			  populateRand8(1, p);
	      } 
         else if(pos == 2)
			{
			  for(int i = 0; i < 4; i++)
		     {
		       for(int k = 8; k < 12; k++) 
			    {
			      bp[i][k] = new BoardPeice(tile, pos, i, k, startSquare, p);
             }    
		     }				
			  populateRand8(2, p);
	      }
		   else if(pos == 5)
			{
			  for(int i = 4; i < 8; i++)
		     {
		       for(int k = 4; k < 8; k++) 
			    {
			      bp[i][k] = new BoardPeice(tile, pos, i, k, startSquare, p);
             }    
		     }				
			  populateRand8(5, p);
         }      
			else if(pos == 6)
			{
			  for(int i = 4; i < 8; i++)
		     {
		       for(int k = 8; k < 12; k++) 
			    {
			      bp[i][k] = new BoardPeice(tile, pos, i, k, startSquare, p);
             }    
		     }				
			  populateRand8(6, p);
         }
		   else if(pos == 7)
			{
			  for(int i = 4; i < 8; i++)
		     {
		       for(int k = 12; k < 16; k++) 
			    {
			      bp[i][k] = new BoardPeice(tile, pos, i, k, startSquare, p);
             }    
		     }				
			  populateRand8(7, p);
         } 
		}
		else if(tile.equals("rand9"))
	   {
			if(pos == 0)
			{
			  for(int i = 0; i < 4; i++)
		     {
		       for(int k = 0; k < 4; k++) 
			    {
			      bp[i][k] = new BoardPeice(tile, pos, i, k, startSquare, p);
             }    
		     }		
			  populateRand9(0, p);
  	      }      
			else if(pos == 1)
			{
			  for(int i = 0; i < 4; i++)
		     {
		       for(int k = 4; k < 8; k++) 
			    {
			      bp[i][k] = new BoardPeice(tile, pos, i, k, startSquare, p);
             }    
		     }		
			  populateRand9(1, p);
	      } 
         else if(pos == 2)
			{
			  for(int i = 0; i < 4; i++)
		     {
		       for(int k = 8; k < 12; k++) 
			    {
			      bp[i][k] = new BoardPeice(tile, pos, i, k, startSquare, p);
             }    
		     }				
			  populateRand9(2, p);
	      }
		   else if(pos == 5)
			{
			  for(int i = 4; i < 8; i++)
		     {
		       for(int k = 4; k < 8; k++) 
			    {
			      bp[i][k] = new BoardPeice(tile, pos, i, k, startSquare, p);
             }    
		     }				
			  populateRand9(5, p);
         }      
			else if(pos == 6)
			{
			  for(int i = 4; i < 8; i++)
		     {
		       for(int k = 8; k < 12; k++) 
			    {
			      bp[i][k] = new BoardPeice(tile, pos, i, k, startSquare, p);
             }    
		     }				
			  populateRand9(6, p);
         }
		   else if(pos == 7)
			{
			  for(int i = 4; i < 8; i++)
		     {
		       for(int k = 12; k < 16; k++) 
			    {
			      bp[i][k] = new BoardPeice(tile, pos, i, k, startSquare, p);
             }    
		     }				
			  populateRand9(7, p);
         } 
		}
		else if(tile.equals("rand10"))
	   {
			if(pos == 0)
			{
			  for(int i = 0; i < 4; i++)
		     {
		       for(int k = 0; k < 4; k++) 
			    {
			      bp[i][k] = new BoardPeice(tile, pos, i, k, startSquare, p);
             }    
		     }		
			  populateRand10(0, p);
  	      }      
			else if(pos == 1)
			{
			  for(int i = 0; i < 4; i++)
		     {
		       for(int k = 4; k < 8; k++) 
			    {
			      bp[i][k] = new BoardPeice(tile, pos, i, k, startSquare, p);
             }    
		     }		
			  populateRand10(1, p);
	      } 
         else if(pos == 2)
			{
			  for(int i = 0; i < 4; i++)
		     {
		       for(int k = 8; k < 12; k++) 
			    {
			      bp[i][k] = new BoardPeice(tile, pos, i, k, startSquare, p);
             }    
		     }				
			  populateRand10(2, p);
	      }
		   else if(pos == 5)
			{
			  for(int i = 4; i < 8; i++)
		     {
		       for(int k = 4; k < 8; k++) 
			    {
			      bp[i][k] = new BoardPeice(tile, pos, i, k, startSquare, p);
             }    
		     }				
			  populateRand10(5, p);
         }      
			else if(pos == 6)
			{
			  for(int i = 4; i < 8; i++)
		     {
		       for(int k = 8; k < 12; k++) 
			    {
			      bp[i][k] = new BoardPeice(tile, pos, i, k, startSquare, p);
             }    
		     }				
			  populateRand10(6, p);
         }
		   else if(pos == 7)
			{
			  for(int i = 4; i < 8; i++)
		     {
		       for(int k = 12; k < 16; k++) 
			    {
			      bp[i][k] = new BoardPeice(tile, pos, i, k, startSquare, p);
             }    
		     }				
			  populateRand10(7, p);
         } 
		}
		else if(tile.equals("rand11"))
	   {
			if(pos == 0)
			{
			  for(int i = 0; i < 4; i++)
		     {
		       for(int k = 0; k < 4; k++) 
			    {
			      bp[i][k] = new BoardPeice(tile, pos, i, k, startSquare, p);
             }    
		     }		
			  populateRand11(0, p);
  	      }      
			else if(pos == 1)
			{
			  for(int i = 0; i < 4; i++)
		     {
		       for(int k = 4; k < 8; k++) 
			    {
			      bp[i][k] = new BoardPeice(tile, pos, i, k, startSquare, p);
             }    
		     }		
			  populateRand11(1, p);
	      } 
         else if(pos == 2)
			{
			  for(int i = 0; i < 4; i++)
		     {
		       for(int k = 8; k < 12; k++) 
			    {
			      bp[i][k] = new BoardPeice(tile, pos, i, k, startSquare, p);
             }    
		     }				
			  populateRand11(2, p);
	      }
		   else if(pos == 5)
			{
			  for(int i = 4; i < 8; i++)
		     {
		       for(int k = 4; k < 8; k++) 
			    {
			      bp[i][k] = new BoardPeice(tile, pos, i, k, startSquare, p);
             }    
		     }				
			  populateRand11(5, p);
         }      
			else if(pos == 6)
			{
			  for(int i = 4; i < 8; i++)
		     {
		       for(int k = 8; k < 12; k++) 
			    {
			      bp[i][k] = new BoardPeice(tile, pos, i, k, startSquare, p);
             }    
		     }				
			  populateRand11(6, p);
         }
		   else if(pos == 7)
			{
			  for(int i = 4; i < 8; i++)
		     {
		       for(int k = 12; k < 16; k++) 
			    {
			      bp[i][k] = new BoardPeice(tile, pos, i, k, startSquare, p);
             }    
		     }				
			  populateRand11(7, p);
         } 
		}
		else if(tile.equals("rand12"))
	   {
			if(pos == 0)
			{
			  for(int i = 0; i < 4; i++)
		     {
		       for(int k = 0; k < 4; k++) 
			    {
			      bp[i][k] = new BoardPeice(tile, pos, i, k, startSquare, p);
             }    
		     }		
			  populateRand12(0, p);
  	      }      
			else if(pos == 1)
			{
			  for(int i = 0; i < 4; i++)
		     {
		       for(int k = 4; k < 8; k++) 
			    {
			      bp[i][k] = new BoardPeice(tile, pos, i, k, startSquare, p);
             }    
		     }		
			  populateRand12(1, p);
	      } 
         else if(pos == 2)
			{
			  for(int i = 0; i < 4; i++)
		     {
		       for(int k = 8; k < 12; k++) 
			    {
			      bp[i][k] = new BoardPeice(tile, pos, i, k, startSquare, p);
             }    
		     }				
			  populateRand12(2, p);
	      }
		   else if(pos == 5)
			{
			  for(int i = 4; i < 8; i++)
		     {
		       for(int k = 4; k < 8; k++) 
			    {
			      bp[i][k] = new BoardPeice(tile, pos, i, k, startSquare, p);
             }    
		     }				
			  populateRand12(5, p);
         }      
			else if(pos == 6)
			{
			  for(int i = 4; i < 8; i++)
		     {
		       for(int k = 8; k < 12; k++) 
			    {
			      bp[i][k] = new BoardPeice(tile, pos, i, k, startSquare, p);
             }    
		     }				
			  populateRand12(6, p);
         }
		   else if(pos == 7)
			{
			  for(int i = 4; i < 8; i++)
		     {
		       for(int k = 12; k < 16; k++) 
			    {
			      bp[i][k] = new BoardPeice(tile, pos, i, k, startSquare, p);
             }    
		     }				
			  populateRand12(7, p);
         } 
		}
		else if(tile.equals("rand13"))
	   {
			if(pos == 0)
			{
			  for(int i = 0; i < 4; i++)
		     {
		       for(int k = 0; k < 4; k++) 
			    {
			      bp[i][k] = new BoardPeice(tile, pos, i, k, startSquare, p);
             }    
		     }		
			  populateRand13(0, p);
  	      }      
			else if(pos == 1)
			{
			  for(int i = 0; i < 4; i++)
		     {
		       for(int k = 4; k < 8; k++) 
			    {
			      bp[i][k] = new BoardPeice(tile, pos, i, k, startSquare, p);
             }    
		     }		
			  populateRand13(1, p);
	      } 
         else if(pos == 2)
			{
			  for(int i = 0; i < 4; i++)
		     {
		       for(int k = 8; k < 12; k++) 
			    {
			      bp[i][k] = new BoardPeice(tile, pos, i, k, startSquare, p);
             }    
		     }				
			  populateRand13(2, p);
	      }
		   else if(pos == 5)
			{
			  for(int i = 4; i < 8; i++)
		     {
		       for(int k = 4; k < 8; k++) 
			    {
			      bp[i][k] = new BoardPeice(tile, pos, i, k, startSquare, p);
             }    
		     }				
			  populateRand13(5, p);
         }      
			else if(pos == 6)
			{
			  for(int i = 4; i < 8; i++)
		     {
		       for(int k = 8; k < 12; k++) 
			    {
			      bp[i][k] = new BoardPeice(tile, pos, i, k, startSquare, p);
             }    
		     }				
			  populateRand13(6, p);
         }
		   else if(pos == 7)
			{
			  for(int i = 4; i < 8; i++)
		     {
		       for(int k = 12; k < 16; k++) 
			    {
			      bp[i][k] = new BoardPeice(tile, pos, i, k, startSquare, p);
             }    
		     }				
			  populateRand13(7, p);
         } 
		}
		else if(tile.equals("rand14"))
	   {
			if(pos == 0)
			{
			  for(int i = 0; i < 4; i++)
		     {
		       for(int k = 0; k < 4; k++) 
			    {
			      bp[i][k] = new BoardPeice(tile, pos, i, k, startSquare, p);
             }    
		     }		
			  populateRand14(0, p);
  	      }      
			else if(pos == 1)
			{
			  for(int i = 0; i < 4; i++)
		     {
		       for(int k = 4; k < 8; k++) 
			    {
			      bp[i][k] = new BoardPeice(tile, pos, i, k, startSquare, p);
             }    
		     }		
			  populateRand14(1, p);
	      } 
         else if(pos == 2)
			{
			  for(int i = 0; i < 4; i++)
		     {
		       for(int k = 8; k < 12; k++) 
			    {
			      bp[i][k] = new BoardPeice(tile, pos, i, k, startSquare, p);
             }    
		     }				
			  populateRand14(2, p);
	      }
		   else if(pos == 5)
			{
			  for(int i = 4; i < 8; i++)
		     {
		       for(int k = 4; k < 8; k++) 
			    {
			      bp[i][k] = new BoardPeice(tile, pos, i, k, startSquare, p);
             }    
		     }				
			  populateRand14(5, p);
         }      
			else if(pos == 6)
			{
			  for(int i = 4; i < 8; i++)
		     {
		       for(int k = 8; k < 12; k++) 
			    {
			      bp[i][k] = new BoardPeice(tile, pos, i, k, startSquare, p);
             }    
		     }				
			  populateRand14(6, p);
         }
		   else if(pos == 7)
			{
			  for(int i = 4; i < 8; i++)
		     {
		       for(int k = 12; k < 16; k++) 
			    {
			      bp[i][k] = new BoardPeice(tile, pos, i, k, startSquare, p);
             }    
		     }				
			  populateRand14(7, p);
         } 
		 }
       else if(tile.equals("America"))
	    {
        if(pos == 4)
		  {
			  for(int i = 4; i < 8; i++)
			  {
			    for(int k = 0; k < 4; k++) 
				 {
				   bp[i][k] = new BoardPeice(tile, pos, i, k, startSquare, p);
	          }    
			  }		 
		  }
        else if(pos == 3)
		  {
			  for(int i = 0; i < 4; i++)
			  {
			    for(int k = 12; k < 16; k++) 
				 {
				   bp[i][k] = new BoardPeice(tile, pos, i, k, startSquare, p);
	          }    
			  }		 
		  }
		 }
       else if(tile.equals("China"))
	    {
        if(pos == 4)
		  {
			  for(int i = 4; i < 8; i++)
			  {
			    for(int k = 0; k < 4; k++) 
				 {
				   bp[i][k] = new BoardPeice(tile, pos, i, k, startSquare, p);
	          }    
			  }		 
		  }
        else if(pos == 3)
		  {
			  for(int i = 0; i < 4; i++)
			  {
			    for(int k = 12; k < 16; k++) 
				 {
				   bp[i][k] = new BoardPeice(tile, pos, i, k, startSquare, p);
	          }    
			  }		 
		  }
		 }
       else if(tile.equals("Egypt"))
	    {
        for(int i = 4; i < 8; i++)
		  {
		    for(int k = 0; k < 4; k++) 
			 {
			   bp[i][k] = new BoardPeice(tile, pos, i, k, startSquare, p);
          }    
		  }		 
		 }
       else if(tile.equals("Germany"))
	    {
        for(int i = 4; i < 8; i++)
		  {
		    for(int k = 0; k < 4; k++) 
			 {
			   bp[i][k] = new BoardPeice(tile, pos, i, k, startSquare, p);
          }    
		  }		 
		 }
       else if(tile.equals("Rome"))
	    {
        for(int i = 4; i < 8; i++)
		  {
		    for(int k = 0; k < 4; k++) 
			 {
			   bp[i][k] = new BoardPeice(tile, pos, i, k, startSquare, p);
          }    
		  }		 
		 }
       else if(tile.equals("Russia"))
	    {
         for(int i = 4; i < 8; i++)
		   {
		     for(int k = 0; k < 4; k++) 
			  {
			    bp[i][k] = new BoardPeice(tile, pos, i, k, startSquare, p);
           }    
		   }		 
		 }

      }//end populatetile
				
		private void populateRand1(int tile, Player player)
		{
		  if(tile == 0)
		  {
			  iconPlayer1 =  (bp[0][0].getIcon());
	        iconPlayer2 =  (bp[1][0].getIcon());
 	        iconPlayer3 =  (bp[2][0].getIcon());
	        iconPlayer4 =  (bp[3][0].getIcon());
	        iconPlayer5 =  (bp[0][1].getIcon());
	        iconPlayer6 =  (bp[1][1].getIcon());
	        iconPlayer7 =  (bp[2][1].getIcon());
	        iconPlayer8 =  (bp[3][1].getIcon());
	        iconPlayer9 =  (bp[0][2].getIcon());
	        iconPlayer10 =  (bp[1][2].getIcon());
	        iconPlayer11 =  (bp[2][2].getIcon());
	        iconPlayer12 =  (bp[3][2].getIcon());
	        iconPlayer13 =  (bp[0][3].getIcon());
	        iconPlayer14 =  (bp[1][3].getIcon());
	        iconPlayer15 =  (bp[2][3].getIcon());
	        iconPlayer16 =  (bp[3][3].getIcon());

	
		//add components to board
			  GameJLabel boardPlayer1 = new GameJLabel(iconPlayer1);
			  GameJLabel boardPlayer2 = new GameJLabel(iconPlayer2);
			  GameJLabel boardPlayer3 = new GameJLabel(iconPlayer3);
			  GameJLabel boardPlayer4 = new GameJLabel(iconPlayer4);
			  GameJLabel boardPlayer5 = new GameJLabel(iconPlayer5);
			  GameJLabel boardPlayer6 = new GameJLabel(iconPlayer6);
			  GameJLabel boardPlayer7 = new GameJLabel(iconPlayer7);
			  GameJLabel boardPlayer8 = new GameJLabel(iconPlayer8);
			  GameJLabel boardPlayer9 = new GameJLabel(iconPlayer9);
			  GameJLabel boardPlayer10 = new GameJLabel(iconPlayer10);
			  GameJLabel boardPlayer11 = new GameJLabel(iconPlayer11);
			  GameJLabel boardPlayer12 = new GameJLabel(iconPlayer12);
			  GameJLabel boardPlayer13 = new GameJLabel(iconPlayer13);
			  GameJLabel boardPlayer14 = new GameJLabel(iconPlayer14);
			  GameJLabel boardPlayer15 = new GameJLabel(iconPlayer15);
			  GameJLabel boardPlayer16 = new GameJLabel(iconPlayer16);
		
			  player.playerUI.rand1_1.add(boardPlayer1, new Integer(0));
		     player.playerUI.rand1_2.add(boardPlayer2, new Integer(0));
		     player.playerUI.rand1_3.add(boardPlayer3, new Integer(0));
		     player.playerUI.rand1_4.add(boardPlayer4, new Integer(0));
		     player.playerUI.rand1_5.add(boardPlayer5, new Integer(0));
		     player.playerUI.rand1_6.add(boardPlayer6, new Integer(0));
		     player.playerUI.rand1_7.add(boardPlayer7, new Integer(0));
		     player.playerUI.rand1_8.add(boardPlayer8, new Integer(0));
		     player.playerUI.rand1_9.add(boardPlayer9, new Integer(0));
		     player.playerUI.rand1_10.add(boardPlayer10, new Integer(0));
		     player.playerUI.rand1_11.add(boardPlayer11, new Integer(0));
		     player.playerUI.rand1_12.add(boardPlayer12, new Integer(0));
		     player.playerUI.rand1_13.add(boardPlayer13, new Integer(0));
		     player.playerUI.rand1_14.add(boardPlayer14, new Integer(0));
		     player.playerUI.rand1_15.add(boardPlayer15, new Integer(0));
		     player.playerUI.rand1_16.add(boardPlayer16, new Integer(0));
		
		     boardPlayer1.setBounds(0, 0, iconPlayer1.getIconWidth(),
		                                  iconPlayer1.getIconHeight()); 
		     player.playerUI.rand1_1.setPreferredSize(new Dimension(iconPlayer1.getIconWidth(),
		                                        iconPlayer1.getIconHeight()));
		     boardPlayer2.setBounds(0, 0, iconPlayer2.getIconWidth(),
		                                  iconPlayer2.getIconHeight()); 
		     player.playerUI.rand1_2.setPreferredSize(new Dimension(iconPlayer2.getIconWidth(),
		                                        iconPlayer2.getIconHeight()));
		     boardPlayer3.setBounds(0, 0, iconPlayer3.getIconWidth(),
		                                  iconPlayer3.getIconHeight()); 
		     player.playerUI.rand1_3.setPreferredSize(new Dimension(iconPlayer3.getIconWidth(),
		                                        iconPlayer3.getIconHeight()));
		     boardPlayer4.setBounds(0, 0, iconPlayer4.getIconWidth(),
		                                  iconPlayer4.getIconHeight()); 
		     player.playerUI.rand1_4.setPreferredSize(new Dimension(iconPlayer4.getIconWidth(),
		                                        iconPlayer4.getIconHeight()));
		     boardPlayer5.setBounds(0, 0, iconPlayer5.getIconWidth(),
		                                  iconPlayer5.getIconHeight()); 
		     player.playerUI.rand1_5.setPreferredSize(new Dimension(iconPlayer5.getIconWidth(),
		                                        iconPlayer5.getIconHeight()));
		     boardPlayer6.setBounds(0, 0, iconPlayer6.getIconWidth(),
		                                  iconPlayer6.getIconHeight()); 
		     player.playerUI.rand1_6.setPreferredSize(new Dimension(iconPlayer6.getIconWidth(),
		                                        iconPlayer6.getIconHeight()));
		     boardPlayer7.setBounds(0, 0, iconPlayer7.getIconWidth(),
		                                  iconPlayer7.getIconHeight()); 
		     player.playerUI.rand1_7.setPreferredSize(new Dimension(iconPlayer7.getIconWidth(),
		                                        iconPlayer7.getIconHeight()));
		     boardPlayer8.setBounds(0, 0, iconPlayer8.getIconWidth(),
		                                  iconPlayer8.getIconHeight()); 
		     player.playerUI.rand1_8.setPreferredSize(new Dimension(iconPlayer8.getIconWidth(),
		                                        iconPlayer8.getIconHeight()));
		     boardPlayer9.setBounds(0, 0, iconPlayer9.getIconWidth(),
		                                  iconPlayer9.getIconHeight()); 
		     player.playerUI.rand1_9.setPreferredSize(new Dimension(iconPlayer9.getIconWidth(),
		                                        iconPlayer9.getIconHeight()));
		     boardPlayer10.setBounds(0, 0, iconPlayer10.getIconWidth(),
		                                  iconPlayer10.getIconHeight()); 
		     player.playerUI.rand1_10.setPreferredSize(new Dimension(iconPlayer10.getIconWidth(),
		                                        iconPlayer10.getIconHeight()));													 													 
		     boardPlayer11.setBounds(0, 0, iconPlayer11.getIconWidth(),
		                                  iconPlayer11.getIconHeight()); 
		     player.playerUI.rand1_11.setPreferredSize(new Dimension(iconPlayer11.getIconWidth(),
		                                        iconPlayer11.getIconHeight()));
		     boardPlayer12.setBounds(0, 0, iconPlayer12.getIconWidth(),
		                                  iconPlayer12.getIconHeight()); 
		     player.playerUI.rand1_12.setPreferredSize(new Dimension(iconPlayer12.getIconWidth(),
		                                        iconPlayer12.getIconHeight()));
		     boardPlayer13.setBounds(0, 0, iconPlayer13.getIconWidth(),
		                                  iconPlayer13.getIconHeight()); 
		     player.playerUI.rand1_13.setPreferredSize(new Dimension(iconPlayer13.getIconWidth(),
		                                        iconPlayer13.getIconHeight()));
		     boardPlayer14.setBounds(0, 0, iconPlayer14.getIconWidth(),
		                                  iconPlayer14.getIconHeight()); 
		     player.playerUI.rand1_14.setPreferredSize(new Dimension(iconPlayer14.getIconWidth(),
		                                        iconPlayer14.getIconHeight()));
		     boardPlayer15.setBounds(0, 0, iconPlayer15.getIconWidth(),
		                                  iconPlayer15.getIconHeight()); 
		     player.playerUI.rand1_15.setPreferredSize(new Dimension(iconPlayer15.getIconWidth(),
		                                        iconPlayer15.getIconHeight()));	
		     boardPlayer16.setBounds(0, 0, iconPlayer16.getIconWidth(),
		                                  iconPlayer16.getIconHeight()); 
		     player.playerUI.rand1_16.setPreferredSize(new Dimension(iconPlayer16.getIconWidth(),
		                                        iconPlayer16.getIconHeight()));													 												 													 
		
			  player.playerUI.mypanel0.add(player.playerUI.rand1_1);
		     player.playerUI.mypanel0.add(player.playerUI.rand1_2);
		     player.playerUI.mypanel0.add(player.playerUI.rand1_3);
		     player.playerUI.mypanel0.add(player.playerUI.rand1_4);
		     player.playerUI.mypanel0.add(player.playerUI.rand1_5);
		     player.playerUI.mypanel0.add(player.playerUI.rand1_6);
		     player.playerUI.mypanel0.add(player.playerUI.rand1_7);
		     player.playerUI.mypanel0.add(player.playerUI.rand1_8);
		     player.playerUI.mypanel0.add(player.playerUI.rand1_9);
		     player.playerUI.mypanel0.add(player.playerUI.rand1_10);
		     player.playerUI.mypanel0.add(player.playerUI.rand1_11);
		     player.playerUI.mypanel0.add(player.playerUI.rand1_12);
		     player.playerUI.mypanel0.add(player.playerUI.rand1_13);
		     player.playerUI.mypanel0.add(player.playerUI.rand1_14);
		     player.playerUI.mypanel0.add(player.playerUI.rand1_15);
		     player.playerUI.mypanel0.add(player.playerUI.rand1_16);
		  }
		  else if(tile == 1)
		  {
  	        iconPlayer1 =  (bp[0][4].getIcon());
	        iconPlayer2 =  (bp[1][4].getIcon());
 	        iconPlayer3 =  (bp[2][4].getIcon());
	        iconPlayer4 =  (bp[3][4].getIcon());
	        iconPlayer5 =  (bp[0][5].getIcon());
	        iconPlayer6 =  (bp[1][5].getIcon());
	        iconPlayer7 =  (bp[2][5].getIcon());
	        iconPlayer8 =  (bp[3][5].getIcon());
	        iconPlayer9 =  (bp[0][6].getIcon());
	        iconPlayer10 =  (bp[1][6].getIcon());
	        iconPlayer11 =  (bp[2][6].getIcon());
	        iconPlayer12 =  (bp[3][6].getIcon());
	        iconPlayer13 =  (bp[0][7].getIcon());
	        iconPlayer14 =  (bp[1][7].getIcon());
	        iconPlayer15 =  (bp[2][7].getIcon());
	        iconPlayer16 =  (bp[3][7].getIcon());

	
		//add components to board
			  GameJLabel boardPlayer1 = new GameJLabel(iconPlayer1);
			  GameJLabel boardPlayer2 = new GameJLabel(iconPlayer2);
			  GameJLabel boardPlayer3 = new GameJLabel(iconPlayer3);
			  GameJLabel boardPlayer4 = new GameJLabel(iconPlayer4);
			  GameJLabel boardPlayer5 = new GameJLabel(iconPlayer5);
			  GameJLabel boardPlayer6 = new GameJLabel(iconPlayer6);
			  GameJLabel boardPlayer7 = new GameJLabel(iconPlayer7);
			  GameJLabel boardPlayer8 = new GameJLabel(iconPlayer8);
			  GameJLabel boardPlayer9 = new GameJLabel(iconPlayer9);
			  GameJLabel boardPlayer10 = new GameJLabel(iconPlayer10);
			  GameJLabel boardPlayer11 = new GameJLabel(iconPlayer11);
			  GameJLabel boardPlayer12 = new GameJLabel(iconPlayer12);
			  GameJLabel boardPlayer13 = new GameJLabel(iconPlayer13);
			  GameJLabel boardPlayer14 = new GameJLabel(iconPlayer14);
			  GameJLabel boardPlayer15 = new GameJLabel(iconPlayer15);
			  GameJLabel boardPlayer16 = new GameJLabel(iconPlayer16);
		
			  player.playerUI.rand1_1.add(boardPlayer1, new Integer(0));
		     player.playerUI.rand1_2.add(boardPlayer2, new Integer(0));
		     player.playerUI.rand1_3.add(boardPlayer3, new Integer(0));
		     player.playerUI.rand1_4.add(boardPlayer4, new Integer(0));
		     player.playerUI.rand1_5.add(boardPlayer5, new Integer(0));
		     player.playerUI.rand1_6.add(boardPlayer6, new Integer(0));
		     player.playerUI.rand1_7.add(boardPlayer7, new Integer(0));
		     player.playerUI.rand1_8.add(boardPlayer8, new Integer(0));
		     player.playerUI.rand1_9.add(boardPlayer9, new Integer(0));
		     player.playerUI.rand1_10.add(boardPlayer10, new Integer(0));
		     player.playerUI.rand1_11.add(boardPlayer11, new Integer(0));
		     player.playerUI.rand1_12.add(boardPlayer12, new Integer(0));
		     player.playerUI.rand1_13.add(boardPlayer13, new Integer(0));
		     player.playerUI.rand1_14.add(boardPlayer14, new Integer(0));
		     player.playerUI.rand1_15.add(boardPlayer15, new Integer(0));
		     player.playerUI.rand1_16.add(boardPlayer16, new Integer(0));
		
		     boardPlayer1.setBounds(0, 0, iconPlayer1.getIconWidth(),
		                                  iconPlayer1.getIconHeight()); 
		     player.playerUI.rand1_1.setPreferredSize(new Dimension(iconPlayer1.getIconWidth(),
		                                        iconPlayer1.getIconHeight()));
		     boardPlayer2.setBounds(0, 0, iconPlayer2.getIconWidth(),
		                                  iconPlayer2.getIconHeight()); 
		     player.playerUI.rand1_2.setPreferredSize(new Dimension(iconPlayer2.getIconWidth(),
		                                        iconPlayer2.getIconHeight()));
		     boardPlayer3.setBounds(0, 0, iconPlayer3.getIconWidth(),
		                                  iconPlayer3.getIconHeight()); 
		     player.playerUI.rand1_3.setPreferredSize(new Dimension(iconPlayer3.getIconWidth(),
		                                        iconPlayer3.getIconHeight()));
		     boardPlayer4.setBounds(0, 0, iconPlayer4.getIconWidth(),
		                                  iconPlayer4.getIconHeight()); 
		     player.playerUI.rand1_4.setPreferredSize(new Dimension(iconPlayer4.getIconWidth(),
		                                        iconPlayer4.getIconHeight()));
		     boardPlayer5.setBounds(0, 0, iconPlayer5.getIconWidth(),
		                                  iconPlayer5.getIconHeight()); 
		     player.playerUI.rand1_5.setPreferredSize(new Dimension(iconPlayer5.getIconWidth(),
		                                        iconPlayer5.getIconHeight()));
		     boardPlayer6.setBounds(0, 0, iconPlayer6.getIconWidth(),
		                                  iconPlayer6.getIconHeight()); 
		     player.playerUI.rand1_6.setPreferredSize(new Dimension(iconPlayer6.getIconWidth(),
		                                        iconPlayer6.getIconHeight()));
		     boardPlayer7.setBounds(0, 0, iconPlayer7.getIconWidth(),
		                                  iconPlayer7.getIconHeight()); 
		     player.playerUI.rand1_7.setPreferredSize(new Dimension(iconPlayer7.getIconWidth(),
		                                        iconPlayer7.getIconHeight()));
		     boardPlayer8.setBounds(0, 0, iconPlayer8.getIconWidth(),
		                                  iconPlayer8.getIconHeight()); 
		     player.playerUI.rand1_8.setPreferredSize(new Dimension(iconPlayer8.getIconWidth(),
		                                        iconPlayer8.getIconHeight()));
		     boardPlayer9.setBounds(0, 0, iconPlayer9.getIconWidth(),
		                                  iconPlayer9.getIconHeight()); 
		     player.playerUI.rand1_9.setPreferredSize(new Dimension(iconPlayer9.getIconWidth(),
		                                        iconPlayer9.getIconHeight()));
		     boardPlayer10.setBounds(0, 0, iconPlayer10.getIconWidth(),
		                                  iconPlayer10.getIconHeight()); 
		     player.playerUI.rand1_10.setPreferredSize(new Dimension(iconPlayer10.getIconWidth(),
		                                        iconPlayer10.getIconHeight()));													 													 
		     boardPlayer11.setBounds(0, 0, iconPlayer11.getIconWidth(),
		                                  iconPlayer11.getIconHeight()); 
		     player.playerUI.rand1_11.setPreferredSize(new Dimension(iconPlayer11.getIconWidth(),
		                                        iconPlayer11.getIconHeight()));
		     boardPlayer12.setBounds(0, 0, iconPlayer12.getIconWidth(),
		                                  iconPlayer12.getIconHeight()); 
		     player.playerUI.rand1_12.setPreferredSize(new Dimension(iconPlayer12.getIconWidth(),
		                                        iconPlayer12.getIconHeight()));
		     boardPlayer13.setBounds(0, 0, iconPlayer13.getIconWidth(),
		                                  iconPlayer13.getIconHeight()); 
		     player.playerUI.rand1_13.setPreferredSize(new Dimension(iconPlayer13.getIconWidth(),
		                                        iconPlayer13.getIconHeight()));
		     boardPlayer14.setBounds(0, 0, iconPlayer14.getIconWidth(),
		                                  iconPlayer14.getIconHeight()); 
		     player.playerUI.rand1_14.setPreferredSize(new Dimension(iconPlayer14.getIconWidth(),
		                                        iconPlayer14.getIconHeight()));
		     boardPlayer15.setBounds(0, 0, iconPlayer15.getIconWidth(),
		                                  iconPlayer15.getIconHeight()); 
		     player.playerUI.rand1_15.setPreferredSize(new Dimension(iconPlayer15.getIconWidth(),
		                                        iconPlayer15.getIconHeight()));	
		     boardPlayer16.setBounds(0, 0, iconPlayer16.getIconWidth(),
		                                  iconPlayer16.getIconHeight()); 
		     player.playerUI.rand1_16.setPreferredSize(new Dimension(iconPlayer16.getIconWidth(),
		                                        iconPlayer16.getIconHeight()));													 												 													 
		
			  player.playerUI.mypanel1.add(player.playerUI.rand1_1);
		     player.playerUI.mypanel1.add(player.playerUI.rand1_2);
		     player.playerUI.mypanel1.add(player.playerUI.rand1_3);
		     player.playerUI.mypanel1.add(player.playerUI.rand1_4);
		     player.playerUI.mypanel1.add(player.playerUI.rand1_5);
		     player.playerUI.mypanel1.add(player.playerUI.rand1_6);
		     player.playerUI.mypanel1.add(player.playerUI.rand1_7);
		     player.playerUI.mypanel1.add(player.playerUI.rand1_8);
		     player.playerUI.mypanel1.add(player.playerUI.rand1_9);
		     player.playerUI.mypanel1.add(player.playerUI.rand1_10);
		     player.playerUI.mypanel1.add(player.playerUI.rand1_11);
		     player.playerUI.mypanel1.add(player.playerUI.rand1_12);
		     player.playerUI.mypanel1.add(player.playerUI.rand1_13);
		     player.playerUI.mypanel1.add(player.playerUI.rand1_14);
		     player.playerUI.mypanel1.add(player.playerUI.rand1_15);
		     player.playerUI.mypanel1.add(player.playerUI.rand1_16);
		  
		  }
		  else if(tile == 2)
		  {
  	        iconPlayer1 =  (bp[0][8].getIcon());
	        iconPlayer2 =  (bp[1][8].getIcon());
 	        iconPlayer3 =  (bp[2][8].getIcon());
	        iconPlayer4 =  (bp[3][8].getIcon());
	        iconPlayer5 =  (bp[0][9].getIcon());
	        iconPlayer6 =  (bp[1][9].getIcon());
	        iconPlayer7 =  (bp[2][9].getIcon());
	        iconPlayer8 =  (bp[3][9].getIcon());
	        iconPlayer9 =  (bp[0][10].getIcon());
	        iconPlayer10 =  (bp[1][10].getIcon());
	        iconPlayer11 =  (bp[2][10].getIcon());
	        iconPlayer12 =  (bp[3][10].getIcon());
	        iconPlayer13 =  (bp[0][11].getIcon());
	        iconPlayer14 =  (bp[1][11].getIcon());
	        iconPlayer15 =  (bp[2][11].getIcon());
	        iconPlayer16 =  (bp[3][11].getIcon());

	
		//add components to board
			  GameJLabel boardPlayer1 = new GameJLabel(iconPlayer1);
			  GameJLabel boardPlayer2 = new GameJLabel(iconPlayer2);
			  GameJLabel boardPlayer3 = new GameJLabel(iconPlayer3);
			  GameJLabel boardPlayer4 = new GameJLabel(iconPlayer4);
			  GameJLabel boardPlayer5 = new GameJLabel(iconPlayer5);
			  GameJLabel boardPlayer6 = new GameJLabel(iconPlayer6);
			  GameJLabel boardPlayer7 = new GameJLabel(iconPlayer7);
			  GameJLabel boardPlayer8 = new GameJLabel(iconPlayer8);
			  GameJLabel boardPlayer9 = new GameJLabel(iconPlayer9);
			  GameJLabel boardPlayer10 = new GameJLabel(iconPlayer10);
			  GameJLabel boardPlayer11 = new GameJLabel(iconPlayer11);
			  GameJLabel boardPlayer12 = new GameJLabel(iconPlayer12);
			  GameJLabel boardPlayer13 = new GameJLabel(iconPlayer13);
			  GameJLabel boardPlayer14 = new GameJLabel(iconPlayer14);
			  GameJLabel boardPlayer15 = new GameJLabel(iconPlayer15);
			  GameJLabel boardPlayer16 = new GameJLabel(iconPlayer16);
		
			  player.playerUI.rand1_1.add(boardPlayer1, new Integer(0));
		     player.playerUI.rand1_2.add(boardPlayer2, new Integer(0));
		     player.playerUI.rand1_3.add(boardPlayer3, new Integer(0));
		     player.playerUI.rand1_4.add(boardPlayer4, new Integer(0));
		     player.playerUI.rand1_5.add(boardPlayer5, new Integer(0));
		     player.playerUI.rand1_6.add(boardPlayer6, new Integer(0));
		     player.playerUI.rand1_7.add(boardPlayer7, new Integer(0));
		     player.playerUI.rand1_8.add(boardPlayer8, new Integer(0));
		     player.playerUI.rand1_9.add(boardPlayer9, new Integer(0));
		     player.playerUI.rand1_10.add(boardPlayer10, new Integer(0));
		     player.playerUI.rand1_11.add(boardPlayer11, new Integer(0));
		     player.playerUI.rand1_12.add(boardPlayer12, new Integer(0));
		     player.playerUI.rand1_13.add(boardPlayer13, new Integer(0));
		     player.playerUI.rand1_14.add(boardPlayer14, new Integer(0));
		     player.playerUI.rand1_15.add(boardPlayer15, new Integer(0));
		     player.playerUI.rand1_16.add(boardPlayer16, new Integer(0));
		
		     boardPlayer1.setBounds(0, 0, iconPlayer1.getIconWidth(),
		                                  iconPlayer1.getIconHeight()); 
		     player.playerUI.rand1_1.setPreferredSize(new Dimension(iconPlayer1.getIconWidth(),
		                                        iconPlayer1.getIconHeight()));
		     boardPlayer2.setBounds(0, 0, iconPlayer2.getIconWidth(),
		                                  iconPlayer2.getIconHeight()); 
		     player.playerUI.rand1_2.setPreferredSize(new Dimension(iconPlayer2.getIconWidth(),
		                                        iconPlayer2.getIconHeight()));
		     boardPlayer3.setBounds(0, 0, iconPlayer3.getIconWidth(),
		                                  iconPlayer3.getIconHeight()); 
		     player.playerUI.rand1_3.setPreferredSize(new Dimension(iconPlayer3.getIconWidth(),
		                                        iconPlayer3.getIconHeight()));
		     boardPlayer4.setBounds(0, 0, iconPlayer4.getIconWidth(),
		                                  iconPlayer4.getIconHeight()); 
		     player.playerUI.rand1_4.setPreferredSize(new Dimension(iconPlayer4.getIconWidth(),
		                                        iconPlayer4.getIconHeight()));
		     boardPlayer5.setBounds(0, 0, iconPlayer5.getIconWidth(),
		                                  iconPlayer5.getIconHeight()); 
		     player.playerUI.rand1_5.setPreferredSize(new Dimension(iconPlayer5.getIconWidth(),
		                                        iconPlayer5.getIconHeight()));
		     boardPlayer6.setBounds(0, 0, iconPlayer6.getIconWidth(),
		                                  iconPlayer6.getIconHeight()); 
		     player.playerUI.rand1_6.setPreferredSize(new Dimension(iconPlayer6.getIconWidth(),
		                                        iconPlayer6.getIconHeight()));
		     boardPlayer7.setBounds(0, 0, iconPlayer7.getIconWidth(),
		                                  iconPlayer7.getIconHeight()); 
		     player.playerUI.rand1_7.setPreferredSize(new Dimension(iconPlayer7.getIconWidth(),
		                                        iconPlayer7.getIconHeight()));
		     boardPlayer8.setBounds(0, 0, iconPlayer8.getIconWidth(),
		                                  iconPlayer8.getIconHeight()); 
		     player.playerUI.rand1_8.setPreferredSize(new Dimension(iconPlayer8.getIconWidth(),
		                                        iconPlayer8.getIconHeight()));
		     boardPlayer9.setBounds(0, 0, iconPlayer9.getIconWidth(),
		                                  iconPlayer9.getIconHeight()); 
		     player.playerUI.rand1_9.setPreferredSize(new Dimension(iconPlayer9.getIconWidth(),
		                                        iconPlayer9.getIconHeight()));
		     boardPlayer10.setBounds(0, 0, iconPlayer10.getIconWidth(),
		                                  iconPlayer10.getIconHeight()); 
		     player.playerUI.rand1_10.setPreferredSize(new Dimension(iconPlayer10.getIconWidth(),
		                                        iconPlayer10.getIconHeight()));													 													 
		     boardPlayer11.setBounds(0, 0, iconPlayer11.getIconWidth(),
		                                  iconPlayer11.getIconHeight()); 
		     player.playerUI.rand1_11.setPreferredSize(new Dimension(iconPlayer11.getIconWidth(),
		                                        iconPlayer11.getIconHeight()));
		     boardPlayer12.setBounds(0, 0, iconPlayer12.getIconWidth(),
		                                  iconPlayer12.getIconHeight()); 
		     player.playerUI.rand1_12.setPreferredSize(new Dimension(iconPlayer12.getIconWidth(),
		                                        iconPlayer12.getIconHeight()));
		     boardPlayer13.setBounds(0, 0, iconPlayer13.getIconWidth(),
		                                  iconPlayer13.getIconHeight()); 
		     player.playerUI.rand1_13.setPreferredSize(new Dimension(iconPlayer13.getIconWidth(),
		                                        iconPlayer13.getIconHeight()));
		     boardPlayer14.setBounds(0, 0, iconPlayer14.getIconWidth(),
		                                  iconPlayer14.getIconHeight()); 
		     player.playerUI.rand1_14.setPreferredSize(new Dimension(iconPlayer14.getIconWidth(),
		                                        iconPlayer14.getIconHeight()));
		     boardPlayer15.setBounds(0, 0, iconPlayer15.getIconWidth(),
		                                  iconPlayer15.getIconHeight()); 
		     player.playerUI.rand1_15.setPreferredSize(new Dimension(iconPlayer15.getIconWidth(),
		                                        iconPlayer15.getIconHeight()));	
		     boardPlayer16.setBounds(0, 0, iconPlayer16.getIconWidth(),
		                                  iconPlayer16.getIconHeight()); 
		     player.playerUI.rand1_16.setPreferredSize(new Dimension(iconPlayer16.getIconWidth(),
		                                        iconPlayer16.getIconHeight()));													 												 													 
		
			  player.playerUI.mypanel2.add(player.playerUI.rand1_1);
		     player.playerUI.mypanel2.add(player.playerUI.rand1_2);
		     player.playerUI.mypanel2.add(player.playerUI.rand1_3);
		     player.playerUI.mypanel2.add(player.playerUI.rand1_4);
		     player.playerUI.mypanel2.add(player.playerUI.rand1_5);
		     player.playerUI.mypanel2.add(player.playerUI.rand1_6);
		     player.playerUI.mypanel2.add(player.playerUI.rand1_7);
		     player.playerUI.mypanel2.add(player.playerUI.rand1_8);
		     player.playerUI.mypanel2.add(player.playerUI.rand1_9);
		     player.playerUI.mypanel2.add(player.playerUI.rand1_10);
		     player.playerUI.mypanel2.add(player.playerUI.rand1_11);
		     player.playerUI.mypanel2.add(player.playerUI.rand1_12);
		     player.playerUI.mypanel2.add(player.playerUI.rand1_13);
		     player.playerUI.mypanel2.add(player.playerUI.rand1_14);
		     player.playerUI.mypanel2.add(player.playerUI.rand1_15);
		     player.playerUI.mypanel2.add(player.playerUI.rand1_16);
		  
		  }
		  else if(tile == 5)
		  {
  	        iconPlayer1 =  (bp[4][4].getIcon());
	        iconPlayer2 =  (bp[5][4].getIcon());
 	        iconPlayer3 =  (bp[6][4].getIcon());
	        iconPlayer4 =  (bp[7][4].getIcon());
	        iconPlayer5 =  (bp[4][5].getIcon());
	        iconPlayer6 =  (bp[5][5].getIcon());
	        iconPlayer7 =  (bp[6][5].getIcon());
	        iconPlayer8 =  (bp[7][5].getIcon());
	        iconPlayer9 =  (bp[4][6].getIcon());
	        iconPlayer10 =  (bp[5][6].getIcon());
	        iconPlayer11 =  (bp[6][6].getIcon());
	        iconPlayer12 =  (bp[7][6].getIcon());
	        iconPlayer13 =  (bp[4][7].getIcon());
	        iconPlayer14 =  (bp[5][7].getIcon());
	        iconPlayer15 =  (bp[6][7].getIcon());
	        iconPlayer16 =  (bp[7][7].getIcon());

	
		//add components to board
			  GameJLabel boardPlayer1 = new GameJLabel(iconPlayer1);
			  GameJLabel boardPlayer2 = new GameJLabel(iconPlayer2);
			  GameJLabel boardPlayer3 = new GameJLabel(iconPlayer3);
			  GameJLabel boardPlayer4 = new GameJLabel(iconPlayer4);
			  GameJLabel boardPlayer5 = new GameJLabel(iconPlayer5);
			  GameJLabel boardPlayer6 = new GameJLabel(iconPlayer6);
			  GameJLabel boardPlayer7 = new GameJLabel(iconPlayer7);
			  GameJLabel boardPlayer8 = new GameJLabel(iconPlayer8);
			  GameJLabel boardPlayer9 = new GameJLabel(iconPlayer9);
			  GameJLabel boardPlayer10 = new GameJLabel(iconPlayer10);
			  GameJLabel boardPlayer11 = new GameJLabel(iconPlayer11);
			  GameJLabel boardPlayer12 = new GameJLabel(iconPlayer12);
			  GameJLabel boardPlayer13 = new GameJLabel(iconPlayer13);
			  GameJLabel boardPlayer14 = new GameJLabel(iconPlayer14);
			  GameJLabel boardPlayer15 = new GameJLabel(iconPlayer15);
			  GameJLabel boardPlayer16 = new GameJLabel(iconPlayer16);
		
			  player.playerUI.rand1_1.add(boardPlayer1, new Integer(0));
		     player.playerUI.rand1_2.add(boardPlayer2, new Integer(0));
		     player.playerUI.rand1_3.add(boardPlayer3, new Integer(0));
		     player.playerUI.rand1_4.add(boardPlayer4, new Integer(0));
		     player.playerUI.rand1_5.add(boardPlayer5, new Integer(0));
		     player.playerUI.rand1_6.add(boardPlayer6, new Integer(0));
		     player.playerUI.rand1_7.add(boardPlayer7, new Integer(0));
		     player.playerUI.rand1_8.add(boardPlayer8, new Integer(0));
		     player.playerUI.rand1_9.add(boardPlayer9, new Integer(0));
		     player.playerUI.rand1_10.add(boardPlayer10, new Integer(0));
		     player.playerUI.rand1_11.add(boardPlayer11, new Integer(0));
		     player.playerUI.rand1_12.add(boardPlayer12, new Integer(0));
		     player.playerUI.rand1_13.add(boardPlayer13, new Integer(0));
		     player.playerUI.rand1_14.add(boardPlayer14, new Integer(0));
		     player.playerUI.rand1_15.add(boardPlayer15, new Integer(0));
		     player.playerUI.rand1_16.add(boardPlayer16, new Integer(0));
		
		     boardPlayer1.setBounds(0, 0, iconPlayer1.getIconWidth(),
		                                  iconPlayer1.getIconHeight()); 
		     player.playerUI.rand1_1.setPreferredSize(new Dimension(iconPlayer1.getIconWidth(),
		                                        iconPlayer1.getIconHeight()));
		     boardPlayer2.setBounds(0, 0, iconPlayer2.getIconWidth(),
		                                  iconPlayer2.getIconHeight()); 
		     player.playerUI.rand1_2.setPreferredSize(new Dimension(iconPlayer2.getIconWidth(),
		                                        iconPlayer2.getIconHeight()));
		     boardPlayer3.setBounds(0, 0, iconPlayer3.getIconWidth(),
		                                  iconPlayer3.getIconHeight()); 
		     player.playerUI.rand1_3.setPreferredSize(new Dimension(iconPlayer3.getIconWidth(),
		                                        iconPlayer3.getIconHeight()));
		     boardPlayer4.setBounds(0, 0, iconPlayer4.getIconWidth(),
		                                  iconPlayer4.getIconHeight()); 
		     player.playerUI.rand1_4.setPreferredSize(new Dimension(iconPlayer4.getIconWidth(),
		                                        iconPlayer4.getIconHeight()));
		     boardPlayer5.setBounds(0, 0, iconPlayer5.getIconWidth(),
		                                  iconPlayer5.getIconHeight()); 
		     player.playerUI.rand1_5.setPreferredSize(new Dimension(iconPlayer5.getIconWidth(),
		                                        iconPlayer5.getIconHeight()));
		     boardPlayer6.setBounds(0, 0, iconPlayer6.getIconWidth(),
		                                  iconPlayer6.getIconHeight()); 
		     player.playerUI.rand1_6.setPreferredSize(new Dimension(iconPlayer6.getIconWidth(),
		                                        iconPlayer6.getIconHeight()));
		     boardPlayer7.setBounds(0, 0, iconPlayer7.getIconWidth(),
		                                  iconPlayer7.getIconHeight()); 
		     player.playerUI.rand1_7.setPreferredSize(new Dimension(iconPlayer7.getIconWidth(),
		                                        iconPlayer7.getIconHeight()));
		     boardPlayer8.setBounds(0, 0, iconPlayer8.getIconWidth(),
		                                  iconPlayer8.getIconHeight()); 
		     player.playerUI.rand1_8.setPreferredSize(new Dimension(iconPlayer8.getIconWidth(),
		                                        iconPlayer8.getIconHeight()));
		     boardPlayer9.setBounds(0, 0, iconPlayer9.getIconWidth(),
		                                  iconPlayer9.getIconHeight()); 
		     player.playerUI.rand1_9.setPreferredSize(new Dimension(iconPlayer9.getIconWidth(),
		                                        iconPlayer9.getIconHeight()));
		     boardPlayer10.setBounds(0, 0, iconPlayer10.getIconWidth(),
		                                  iconPlayer10.getIconHeight()); 
		     player.playerUI.rand1_10.setPreferredSize(new Dimension(iconPlayer10.getIconWidth(),
		                                        iconPlayer10.getIconHeight()));													 													 
		     boardPlayer11.setBounds(0, 0, iconPlayer11.getIconWidth(),
		                                  iconPlayer11.getIconHeight()); 
		     player.playerUI.rand1_11.setPreferredSize(new Dimension(iconPlayer11.getIconWidth(),
		                                        iconPlayer11.getIconHeight()));
		     boardPlayer12.setBounds(0, 0, iconPlayer12.getIconWidth(),
		                                  iconPlayer12.getIconHeight()); 
		     player.playerUI.rand1_12.setPreferredSize(new Dimension(iconPlayer12.getIconWidth(),
		                                        iconPlayer12.getIconHeight()));
		     boardPlayer13.setBounds(0, 0, iconPlayer13.getIconWidth(),
		                                  iconPlayer13.getIconHeight()); 
		     player.playerUI.rand1_13.setPreferredSize(new Dimension(iconPlayer13.getIconWidth(),
		                                        iconPlayer13.getIconHeight()));
		     boardPlayer14.setBounds(0, 0, iconPlayer14.getIconWidth(),
		                                  iconPlayer14.getIconHeight()); 
		     player.playerUI.rand1_14.setPreferredSize(new Dimension(iconPlayer14.getIconWidth(),
		                                        iconPlayer14.getIconHeight()));
		     boardPlayer15.setBounds(0, 0, iconPlayer15.getIconWidth(),
		                                  iconPlayer15.getIconHeight()); 
		     player.playerUI.rand1_15.setPreferredSize(new Dimension(iconPlayer15.getIconWidth(),
		                                        iconPlayer15.getIconHeight()));	
		     boardPlayer16.setBounds(0, 0, iconPlayer16.getIconWidth(),
		                                  iconPlayer16.getIconHeight()); 
		     player.playerUI.rand1_16.setPreferredSize(new Dimension(iconPlayer16.getIconWidth(),
		                                        iconPlayer16.getIconHeight()));													 												 													 
		
			  player.playerUI.mypanel5.add(player.playerUI.rand1_1);
		     player.playerUI.mypanel5.add(player.playerUI.rand1_2);
		     player.playerUI.mypanel5.add(player.playerUI.rand1_3);
		     player.playerUI.mypanel5.add(player.playerUI.rand1_4);
		     player.playerUI.mypanel5.add(player.playerUI.rand1_5);
		     player.playerUI.mypanel5.add(player.playerUI.rand1_6);
		     player.playerUI.mypanel5.add(player.playerUI.rand1_7);
		     player.playerUI.mypanel5.add(player.playerUI.rand1_8);
		     player.playerUI.mypanel5.add(player.playerUI.rand1_9);
		     player.playerUI.mypanel5.add(player.playerUI.rand1_10);
		     player.playerUI.mypanel5.add(player.playerUI.rand1_11);
		     player.playerUI.mypanel5.add(player.playerUI.rand1_12);
		     player.playerUI.mypanel5.add(player.playerUI.rand1_13);
		     player.playerUI.mypanel5.add(player.playerUI.rand1_14);
		     player.playerUI.mypanel5.add(player.playerUI.rand1_15);
		     player.playerUI.mypanel5.add(player.playerUI.rand1_16);
		  
		  }
		  else if(tile == 6)
		  {
  	        iconPlayer1 =  (bp[4][8].getIcon());
	        iconPlayer2 =  (bp[5][8].getIcon());
 	        iconPlayer3 =  (bp[6][8].getIcon());
	        iconPlayer4 =  (bp[7][8].getIcon());
	        iconPlayer5 =  (bp[4][9].getIcon());
	        iconPlayer6 =  (bp[5][9].getIcon());
	        iconPlayer7 =  (bp[6][9].getIcon());
	        iconPlayer8 =  (bp[7][9].getIcon());
	        iconPlayer9 =  (bp[4][10].getIcon());
	        iconPlayer10 =  (bp[5][10].getIcon());
	        iconPlayer11 =  (bp[6][10].getIcon());
	        iconPlayer12 =  (bp[7][10].getIcon());
	        iconPlayer13 =  (bp[4][11].getIcon());
	        iconPlayer14 =  (bp[5][11].getIcon());
	        iconPlayer15 =  (bp[6][11].getIcon());
	        iconPlayer16 =  (bp[7][11].getIcon());

	
		//add components to board
			  GameJLabel boardPlayer1 = new GameJLabel(iconPlayer1);
			  GameJLabel boardPlayer2 = new GameJLabel(iconPlayer2);
			  GameJLabel boardPlayer3 = new GameJLabel(iconPlayer3);
			  GameJLabel boardPlayer4 = new GameJLabel(iconPlayer4);
			  GameJLabel boardPlayer5 = new GameJLabel(iconPlayer5);
			  GameJLabel boardPlayer6 = new GameJLabel(iconPlayer6);
			  GameJLabel boardPlayer7 = new GameJLabel(iconPlayer7);
			  GameJLabel boardPlayer8 = new GameJLabel(iconPlayer8);
			  GameJLabel boardPlayer9 = new GameJLabel(iconPlayer9);
			  GameJLabel boardPlayer10 = new GameJLabel(iconPlayer10);
			  GameJLabel boardPlayer11 = new GameJLabel(iconPlayer11);
			  GameJLabel boardPlayer12 = new GameJLabel(iconPlayer12);
			  GameJLabel boardPlayer13 = new GameJLabel(iconPlayer13);
			  GameJLabel boardPlayer14 = new GameJLabel(iconPlayer14);
			  GameJLabel boardPlayer15 = new GameJLabel(iconPlayer15);
			  GameJLabel boardPlayer16 = new GameJLabel(iconPlayer16);
		
			  player.playerUI.rand1_1.add(boardPlayer1, new Integer(0));
		     player.playerUI.rand1_2.add(boardPlayer2, new Integer(0));
		     player.playerUI.rand1_3.add(boardPlayer3, new Integer(0));
		     player.playerUI.rand1_4.add(boardPlayer4, new Integer(0));
		     player.playerUI.rand1_5.add(boardPlayer5, new Integer(0));
		     player.playerUI.rand1_6.add(boardPlayer6, new Integer(0));
		     player.playerUI.rand1_7.add(boardPlayer7, new Integer(0));
		     player.playerUI.rand1_8.add(boardPlayer8, new Integer(0));
		     player.playerUI.rand1_9.add(boardPlayer9, new Integer(0));
		     player.playerUI.rand1_10.add(boardPlayer10, new Integer(0));
		     player.playerUI.rand1_11.add(boardPlayer11, new Integer(0));
		     player.playerUI.rand1_12.add(boardPlayer12, new Integer(0));
		     player.playerUI.rand1_13.add(boardPlayer13, new Integer(0));
		     player.playerUI.rand1_14.add(boardPlayer14, new Integer(0));
		     player.playerUI.rand1_15.add(boardPlayer15, new Integer(0));
		     player.playerUI.rand1_16.add(boardPlayer16, new Integer(0));
		
		     boardPlayer1.setBounds(0, 0, iconPlayer1.getIconWidth(),
		                                  iconPlayer1.getIconHeight()); 
		     player.playerUI.rand1_1.setPreferredSize(new Dimension(iconPlayer1.getIconWidth(),
		                                        iconPlayer1.getIconHeight()));
		     boardPlayer2.setBounds(0, 0, iconPlayer2.getIconWidth(),
		                                  iconPlayer2.getIconHeight()); 
		     player.playerUI.rand1_2.setPreferredSize(new Dimension(iconPlayer2.getIconWidth(),
		                                        iconPlayer2.getIconHeight()));
		     boardPlayer3.setBounds(0, 0, iconPlayer3.getIconWidth(),
		                                  iconPlayer3.getIconHeight()); 
		     player.playerUI.rand1_3.setPreferredSize(new Dimension(iconPlayer3.getIconWidth(),
		                                        iconPlayer3.getIconHeight()));
		     boardPlayer4.setBounds(0, 0, iconPlayer4.getIconWidth(),
		                                  iconPlayer4.getIconHeight()); 
		     player.playerUI.rand1_4.setPreferredSize(new Dimension(iconPlayer4.getIconWidth(),
		                                        iconPlayer4.getIconHeight()));
		     boardPlayer5.setBounds(0, 0, iconPlayer5.getIconWidth(),
		                                  iconPlayer5.getIconHeight()); 
		     player.playerUI.rand1_5.setPreferredSize(new Dimension(iconPlayer5.getIconWidth(),
		                                        iconPlayer5.getIconHeight()));
		     boardPlayer6.setBounds(0, 0, iconPlayer6.getIconWidth(),
		                                  iconPlayer6.getIconHeight()); 
		     player.playerUI.rand1_6.setPreferredSize(new Dimension(iconPlayer6.getIconWidth(),
		                                        iconPlayer6.getIconHeight()));
		     boardPlayer7.setBounds(0, 0, iconPlayer7.getIconWidth(),
		                                  iconPlayer7.getIconHeight()); 
		     player.playerUI.rand1_7.setPreferredSize(new Dimension(iconPlayer7.getIconWidth(),
		                                        iconPlayer7.getIconHeight()));
		     boardPlayer8.setBounds(0, 0, iconPlayer8.getIconWidth(),
		                                  iconPlayer8.getIconHeight()); 
		     player.playerUI.rand1_8.setPreferredSize(new Dimension(iconPlayer8.getIconWidth(),
		                                        iconPlayer8.getIconHeight()));
		     boardPlayer9.setBounds(0, 0, iconPlayer9.getIconWidth(),
		                                  iconPlayer9.getIconHeight()); 
		     player.playerUI.rand1_9.setPreferredSize(new Dimension(iconPlayer9.getIconWidth(),
		                                        iconPlayer9.getIconHeight()));
		     boardPlayer10.setBounds(0, 0, iconPlayer10.getIconWidth(),
		                                  iconPlayer10.getIconHeight()); 
		     player.playerUI.rand1_10.setPreferredSize(new Dimension(iconPlayer10.getIconWidth(),
		                                        iconPlayer10.getIconHeight()));													 													 
		     boardPlayer11.setBounds(0, 0, iconPlayer11.getIconWidth(),
		                                  iconPlayer11.getIconHeight()); 
		     player.playerUI.rand1_11.setPreferredSize(new Dimension(iconPlayer11.getIconWidth(),
		                                        iconPlayer11.getIconHeight()));
		     boardPlayer12.setBounds(0, 0, iconPlayer12.getIconWidth(),
		                                  iconPlayer12.getIconHeight()); 
		     player.playerUI.rand1_12.setPreferredSize(new Dimension(iconPlayer12.getIconWidth(),
		                                        iconPlayer12.getIconHeight()));
		     boardPlayer13.setBounds(0, 0, iconPlayer13.getIconWidth(),
		                                  iconPlayer13.getIconHeight()); 
		     player.playerUI.rand1_13.setPreferredSize(new Dimension(iconPlayer13.getIconWidth(),
		                                        iconPlayer13.getIconHeight()));
		     boardPlayer14.setBounds(0, 0, iconPlayer14.getIconWidth(),
		                                  iconPlayer14.getIconHeight()); 
		     player.playerUI.rand1_14.setPreferredSize(new Dimension(iconPlayer14.getIconWidth(),
		                                        iconPlayer14.getIconHeight()));
		     boardPlayer15.setBounds(0, 0, iconPlayer15.getIconWidth(),
		                                  iconPlayer15.getIconHeight()); 
		     player.playerUI.rand1_15.setPreferredSize(new Dimension(iconPlayer15.getIconWidth(),
		                                        iconPlayer15.getIconHeight()));	
		     boardPlayer16.setBounds(0, 0, iconPlayer16.getIconWidth(),
		                                  iconPlayer16.getIconHeight()); 
		     player.playerUI.rand1_16.setPreferredSize(new Dimension(iconPlayer16.getIconWidth(),
		                                        iconPlayer16.getIconHeight()));													 												 													 
		
			  player.playerUI.mypanel6.add(player.playerUI.rand1_1);
		     player.playerUI.mypanel6.add(player.playerUI.rand1_2);
		     player.playerUI.mypanel6.add(player.playerUI.rand1_3);
		     player.playerUI.mypanel6.add(player.playerUI.rand1_4);
		     player.playerUI.mypanel6.add(player.playerUI.rand1_5);
		     player.playerUI.mypanel6.add(player.playerUI.rand1_6);
		     player.playerUI.mypanel6.add(player.playerUI.rand1_7);
		     player.playerUI.mypanel6.add(player.playerUI.rand1_8);
		     player.playerUI.mypanel6.add(player.playerUI.rand1_9);
		     player.playerUI.mypanel6.add(player.playerUI.rand1_10);
		     player.playerUI.mypanel6.add(player.playerUI.rand1_11);
		     player.playerUI.mypanel6.add(player.playerUI.rand1_12);
		     player.playerUI.mypanel6.add(player.playerUI.rand1_13);
		     player.playerUI.mypanel6.add(player.playerUI.rand1_14);
		     player.playerUI.mypanel6.add(player.playerUI.rand1_15);
		     player.playerUI.mypanel6.add(player.playerUI.rand1_16);
		  
		  }
		  else if(tile == 7)
		  {
  	       iconPlayer1 =  bp[4][12].getIcon();
	       iconPlayer2 =  bp[5][12].getIcon();
	       iconPlayer3 =  bp[6][12].getIcon();
	       iconPlayer4 =  bp[7][12].getIcon();
	       iconPlayer5 =  bp[4][13].getIcon();
	       iconPlayer6 =  bp[5][13].getIcon();
	       iconPlayer7 =  bp[6][13].getIcon();
	       iconPlayer8 =  bp[7][13].getIcon();
	       iconPlayer9 =  bp[4][14].getIcon();
	       iconPlayer10 =  bp[5][14].getIcon();
	       iconPlayer11 =  bp[6][14].getIcon();
	       iconPlayer12 =  bp[7][14].getIcon();
	       iconPlayer13 =  bp[4][15].getIcon();
	       iconPlayer14 =  bp[5][15].getIcon();
	       iconPlayer15 =  bp[6][15].getIcon();
	       iconPlayer16 =  bp[7][15].getIcon();


		//add components to board
			  GameJLabel boardPlayer1 = new GameJLabel(iconPlayer1);
			  GameJLabel boardPlayer2 = new GameJLabel(iconPlayer2);
			  GameJLabel boardPlayer3 = new GameJLabel(iconPlayer3);
			  GameJLabel boardPlayer4 = new GameJLabel(iconPlayer4);
			  GameJLabel boardPlayer5 = new GameJLabel(iconPlayer5);
			  GameJLabel boardPlayer6 = new GameJLabel(iconPlayer6);
			  GameJLabel boardPlayer7 = new GameJLabel(iconPlayer7);
			  GameJLabel boardPlayer8 = new GameJLabel(iconPlayer8);
			  GameJLabel boardPlayer9 = new GameJLabel(iconPlayer9);
			  GameJLabel boardPlayer10 = new GameJLabel(iconPlayer10);
			  GameJLabel boardPlayer11 = new GameJLabel(iconPlayer11);
			  GameJLabel boardPlayer12 = new GameJLabel(iconPlayer12);
			  GameJLabel boardPlayer13 = new GameJLabel(iconPlayer13);
			  GameJLabel boardPlayer14 = new GameJLabel(iconPlayer14);
			  GameJLabel boardPlayer15 = new GameJLabel(iconPlayer15);
			  GameJLabel boardPlayer16 = new GameJLabel(iconPlayer16);
		
			  player.playerUI.rand1_1.add(boardPlayer1, new Integer(0));
		     player.playerUI.rand1_2.add(boardPlayer2, new Integer(0));
		     player.playerUI.rand1_3.add(boardPlayer3, new Integer(0));
		     player.playerUI.rand1_4.add(boardPlayer4, new Integer(0));
		     player.playerUI.rand1_5.add(boardPlayer5, new Integer(0));
		     player.playerUI.rand1_6.add(boardPlayer6, new Integer(0));
		     player.playerUI.rand1_7.add(boardPlayer7, new Integer(0));
		     player.playerUI.rand1_8.add(boardPlayer8, new Integer(0));
		     player.playerUI.rand1_9.add(boardPlayer9, new Integer(0));
		     player.playerUI.rand1_10.add(boardPlayer10, new Integer(0));
		     player.playerUI.rand1_11.add(boardPlayer11, new Integer(0));
		     player.playerUI.rand1_12.add(boardPlayer12, new Integer(0));
		     player.playerUI.rand1_13.add(boardPlayer13, new Integer(0));
		     player.playerUI.rand1_14.add(boardPlayer14, new Integer(0));
		     player.playerUI.rand1_15.add(boardPlayer15, new Integer(0));
		     player.playerUI.rand1_16.add(boardPlayer16, new Integer(0));
		
		     boardPlayer1.setBounds(0, 0, iconPlayer1.getIconWidth(),
		                                  iconPlayer1.getIconHeight()); 
		     player.playerUI.rand1_1.setPreferredSize(new Dimension(iconPlayer1.getIconWidth(),
		                                        iconPlayer1.getIconHeight()));
		     boardPlayer2.setBounds(0, 0, iconPlayer2.getIconWidth(),
		                                  iconPlayer2.getIconHeight()); 
		     player.playerUI.rand1_2.setPreferredSize(new Dimension(iconPlayer2.getIconWidth(),
		                                        iconPlayer2.getIconHeight()));
		     boardPlayer3.setBounds(0, 0, iconPlayer3.getIconWidth(),
		                                  iconPlayer3.getIconHeight()); 
		     player.playerUI.rand1_3.setPreferredSize(new Dimension(iconPlayer3.getIconWidth(),
		                                        iconPlayer3.getIconHeight()));
		     boardPlayer4.setBounds(0, 0, iconPlayer4.getIconWidth(),
		                                  iconPlayer4.getIconHeight()); 
		     player.playerUI.rand1_4.setPreferredSize(new Dimension(iconPlayer4.getIconWidth(),
		                                        iconPlayer4.getIconHeight()));
		     boardPlayer5.setBounds(0, 0, iconPlayer5.getIconWidth(),
		                                  iconPlayer5.getIconHeight()); 
		     player.playerUI.rand1_5.setPreferredSize(new Dimension(iconPlayer5.getIconWidth(),
		                                        iconPlayer5.getIconHeight()));
		     boardPlayer6.setBounds(0, 0, iconPlayer6.getIconWidth(),
		                                  iconPlayer6.getIconHeight()); 
		     player.playerUI.rand1_6.setPreferredSize(new Dimension(iconPlayer6.getIconWidth(),
		                                        iconPlayer6.getIconHeight()));
		     boardPlayer7.setBounds(0, 0, iconPlayer7.getIconWidth(),
		                                  iconPlayer7.getIconHeight()); 
		     player.playerUI.rand1_7.setPreferredSize(new Dimension(iconPlayer7.getIconWidth(),
		                                        iconPlayer7.getIconHeight()));
		     boardPlayer8.setBounds(0, 0, iconPlayer8.getIconWidth(),
		                                  iconPlayer8.getIconHeight()); 
		     player.playerUI.rand1_8.setPreferredSize(new Dimension(iconPlayer8.getIconWidth(),
		                                        iconPlayer8.getIconHeight()));
		     boardPlayer9.setBounds(0, 0, iconPlayer9.getIconWidth(),
		                                  iconPlayer9.getIconHeight()); 
		     player.playerUI.rand1_9.setPreferredSize(new Dimension(iconPlayer9.getIconWidth(),
		                                        iconPlayer9.getIconHeight()));
		     boardPlayer10.setBounds(0, 0, iconPlayer10.getIconWidth(),
		                                  iconPlayer10.getIconHeight()); 
		     player.playerUI.rand1_10.setPreferredSize(new Dimension(iconPlayer10.getIconWidth(),
		                                        iconPlayer10.getIconHeight()));													 													 
		     boardPlayer11.setBounds(0, 0, iconPlayer11.getIconWidth(),
		                                  iconPlayer11.getIconHeight()); 
		     player.playerUI.rand1_11.setPreferredSize(new Dimension(iconPlayer11.getIconWidth(),
		                                        iconPlayer11.getIconHeight()));
		     boardPlayer12.setBounds(0, 0, iconPlayer12.getIconWidth(),
		                                  iconPlayer12.getIconHeight()); 
		     player.playerUI.rand1_12.setPreferredSize(new Dimension(iconPlayer12.getIconWidth(),
		                                        iconPlayer12.getIconHeight()));
		     boardPlayer13.setBounds(0, 0, iconPlayer13.getIconWidth(),
		                                  iconPlayer13.getIconHeight()); 
		     player.playerUI.rand1_13.setPreferredSize(new Dimension(iconPlayer13.getIconWidth(),
		                                        iconPlayer13.getIconHeight()));
		     boardPlayer14.setBounds(0, 0, iconPlayer14.getIconWidth(),
		                                  iconPlayer14.getIconHeight()); 
		     player.playerUI.rand1_14.setPreferredSize(new Dimension(iconPlayer14.getIconWidth(),
		                                        iconPlayer14.getIconHeight()));
		     boardPlayer15.setBounds(0, 0, iconPlayer15.getIconWidth(),
		                                  iconPlayer15.getIconHeight()); 
		     player.playerUI.rand1_15.setPreferredSize(new Dimension(iconPlayer15.getIconWidth(),
		                                        iconPlayer15.getIconHeight()));	
		     boardPlayer16.setBounds(0, 0, iconPlayer16.getIconWidth(),
		                                  iconPlayer16.getIconHeight()); 
		     player.playerUI.rand1_16.setPreferredSize(new Dimension(iconPlayer16.getIconWidth(),
		                                        iconPlayer16.getIconHeight()));													 												 													 
		
			  player.playerUI.mypanel7.add(player.playerUI.rand1_1);
		     player.playerUI.mypanel7.add(player.playerUI.rand1_2);
		     player.playerUI.mypanel7.add(player.playerUI.rand1_3);
		     player.playerUI.mypanel7.add(player.playerUI.rand1_4);
		     player.playerUI.mypanel7.add(player.playerUI.rand1_5);
		     player.playerUI.mypanel7.add(player.playerUI.rand1_6);
		     player.playerUI.mypanel7.add(player.playerUI.rand1_7);
		     player.playerUI.mypanel7.add(player.playerUI.rand1_8);
		     player.playerUI.mypanel7.add(player.playerUI.rand1_9);
		     player.playerUI.mypanel7.add(player.playerUI.rand1_10);
		     player.playerUI.mypanel7.add(player.playerUI.rand1_11);
		     player.playerUI.mypanel7.add(player.playerUI.rand1_12);
		     player.playerUI.mypanel7.add(player.playerUI.rand1_13);
		     player.playerUI.mypanel7.add(player.playerUI.rand1_14);
		     player.playerUI.mypanel7.add(player.playerUI.rand1_15);
		     player.playerUI.mypanel7.add(player.playerUI.rand1_16);

		  }
		}

      private void populateRand2(int tile, Player player)
		{
		  if(tile == 0)
		  {
			  iconPlayer1 =  (bp[0][0].getIcon());
	        iconPlayer2 =  (bp[1][0].getIcon());
 	        iconPlayer3 =  (bp[2][0].getIcon());
	        iconPlayer4 =  (bp[3][0].getIcon());
	        iconPlayer5 =  (bp[0][1].getIcon());
	        iconPlayer6 =  (bp[1][1].getIcon());
	        iconPlayer7 =  (bp[2][1].getIcon());
	        iconPlayer8 =  (bp[3][1].getIcon());
	        iconPlayer9 =  (bp[0][2].getIcon());
	        iconPlayer10 =  (bp[1][2].getIcon());
	        iconPlayer11 =  (bp[2][2].getIcon());
	        iconPlayer12 =  (bp[3][2].getIcon());
	        iconPlayer13 =  (bp[0][3].getIcon());
	        iconPlayer14 =  (bp[1][3].getIcon());
	        iconPlayer15 =  (bp[2][3].getIcon());
	        iconPlayer16 =  (bp[3][3].getIcon());

	
		//add components to board
			  GameJLabel boardPlayer1 = new GameJLabel(iconPlayer1);
			  GameJLabel boardPlayer2 = new GameJLabel(iconPlayer2);
			  GameJLabel boardPlayer3 = new GameJLabel(iconPlayer3);
			  GameJLabel boardPlayer4 = new GameJLabel(iconPlayer4);
			  GameJLabel boardPlayer5 = new GameJLabel(iconPlayer5);
			  GameJLabel boardPlayer6 = new GameJLabel(iconPlayer6);
			  GameJLabel boardPlayer7 = new GameJLabel(iconPlayer7);
			  GameJLabel boardPlayer8 = new GameJLabel(iconPlayer8);
			  GameJLabel boardPlayer9 = new GameJLabel(iconPlayer9);
			  GameJLabel boardPlayer10 = new GameJLabel(iconPlayer10);
			  GameJLabel boardPlayer11 = new GameJLabel(iconPlayer11);
			  GameJLabel boardPlayer12 = new GameJLabel(iconPlayer12);
			  GameJLabel boardPlayer13 = new GameJLabel(iconPlayer13);
			  GameJLabel boardPlayer14 = new GameJLabel(iconPlayer14);
			  GameJLabel boardPlayer15 = new GameJLabel(iconPlayer15);
			  GameJLabel boardPlayer16 = new GameJLabel(iconPlayer16);
		
			  player.playerUI.rand2_1.add(boardPlayer1, new Integer(0));
		     player.playerUI.rand2_2.add(boardPlayer2, new Integer(0));
		     player.playerUI.rand2_3.add(boardPlayer3, new Integer(0));
		     player.playerUI.rand2_4.add(boardPlayer4, new Integer(0));
		     player.playerUI.rand2_5.add(boardPlayer5, new Integer(0));
		     player.playerUI.rand2_6.add(boardPlayer6, new Integer(0));
		     player.playerUI.rand2_7.add(boardPlayer7, new Integer(0));
		     player.playerUI.rand2_8.add(boardPlayer8, new Integer(0));
		     player.playerUI.rand2_9.add(boardPlayer9, new Integer(0));
		     player.playerUI.rand2_10.add(boardPlayer10, new Integer(0));
		     player.playerUI.rand2_11.add(boardPlayer11, new Integer(0));
		     player.playerUI.rand2_12.add(boardPlayer12, new Integer(0));
		     player.playerUI.rand2_13.add(boardPlayer13, new Integer(0));
		     player.playerUI.rand2_14.add(boardPlayer14, new Integer(0));
		     player.playerUI.rand2_15.add(boardPlayer15, new Integer(0));
		     player.playerUI.rand2_16.add(boardPlayer16, new Integer(0));
		
		     boardPlayer1.setBounds(0, 0, iconPlayer1.getIconWidth(),
		                                  iconPlayer1.getIconHeight()); 
		     player.playerUI.rand2_1.setPreferredSize(new Dimension(iconPlayer1.getIconWidth(),
		                                        iconPlayer1.getIconHeight()));
		     boardPlayer2.setBounds(0, 0, iconPlayer2.getIconWidth(),
		                                  iconPlayer2.getIconHeight()); 
		     player.playerUI.rand2_2.setPreferredSize(new Dimension(iconPlayer2.getIconWidth(),
		                                        iconPlayer2.getIconHeight()));
		     boardPlayer3.setBounds(0, 0, iconPlayer3.getIconWidth(),
		                                  iconPlayer3.getIconHeight()); 
		     player.playerUI.rand2_3.setPreferredSize(new Dimension(iconPlayer3.getIconWidth(),
		                                        iconPlayer3.getIconHeight()));
		     boardPlayer4.setBounds(0, 0, iconPlayer4.getIconWidth(),
		                                  iconPlayer4.getIconHeight()); 
		     player.playerUI.rand2_4.setPreferredSize(new Dimension(iconPlayer4.getIconWidth(),
		                                        iconPlayer4.getIconHeight()));
		     boardPlayer5.setBounds(0, 0, iconPlayer5.getIconWidth(),
		                                  iconPlayer5.getIconHeight()); 
		     player.playerUI.rand2_5.setPreferredSize(new Dimension(iconPlayer5.getIconWidth(),
		                                        iconPlayer5.getIconHeight()));
		     boardPlayer6.setBounds(0, 0, iconPlayer6.getIconWidth(),
		                                  iconPlayer6.getIconHeight()); 
		     player.playerUI.rand2_6.setPreferredSize(new Dimension(iconPlayer6.getIconWidth(),
		                                        iconPlayer6.getIconHeight()));
		     boardPlayer7.setBounds(0, 0, iconPlayer7.getIconWidth(),
		                                  iconPlayer7.getIconHeight()); 
		     player.playerUI.rand2_7.setPreferredSize(new Dimension(iconPlayer7.getIconWidth(),
		                                        iconPlayer7.getIconHeight()));
		     boardPlayer8.setBounds(0, 0, iconPlayer8.getIconWidth(),
		                                  iconPlayer8.getIconHeight()); 
		     player.playerUI.rand2_8.setPreferredSize(new Dimension(iconPlayer8.getIconWidth(),
		                                        iconPlayer8.getIconHeight()));
		     boardPlayer9.setBounds(0, 0, iconPlayer9.getIconWidth(),
		                                  iconPlayer9.getIconHeight()); 
		     player.playerUI.rand2_9.setPreferredSize(new Dimension(iconPlayer9.getIconWidth(),
		                                        iconPlayer9.getIconHeight()));
		     boardPlayer10.setBounds(0, 0, iconPlayer10.getIconWidth(),
		                                  iconPlayer10.getIconHeight()); 
		     player.playerUI.rand2_10.setPreferredSize(new Dimension(iconPlayer10.getIconWidth(),
		                                        iconPlayer10.getIconHeight()));													 													 
		     boardPlayer11.setBounds(0, 0, iconPlayer11.getIconWidth(),
		                                  iconPlayer11.getIconHeight()); 
		     player.playerUI.rand2_11.setPreferredSize(new Dimension(iconPlayer11.getIconWidth(),
		                                        iconPlayer11.getIconHeight()));
		     boardPlayer12.setBounds(0, 0, iconPlayer12.getIconWidth(),
		                                  iconPlayer12.getIconHeight()); 
		     player.playerUI.rand2_12.setPreferredSize(new Dimension(iconPlayer12.getIconWidth(),
		                                        iconPlayer12.getIconHeight()));
		     boardPlayer13.setBounds(0, 0, iconPlayer13.getIconWidth(),
		                                  iconPlayer13.getIconHeight()); 
		     player.playerUI.rand2_13.setPreferredSize(new Dimension(iconPlayer13.getIconWidth(),
		                                        iconPlayer13.getIconHeight()));
		     boardPlayer14.setBounds(0, 0, iconPlayer14.getIconWidth(),
		                                  iconPlayer14.getIconHeight()); 
		     player.playerUI.rand2_14.setPreferredSize(new Dimension(iconPlayer14.getIconWidth(),
		                                        iconPlayer14.getIconHeight()));
		     boardPlayer15.setBounds(0, 0, iconPlayer15.getIconWidth(),
		                                  iconPlayer15.getIconHeight()); 
		     player.playerUI.rand2_15.setPreferredSize(new Dimension(iconPlayer15.getIconWidth(),
		                                        iconPlayer15.getIconHeight()));	
		     boardPlayer16.setBounds(0, 0, iconPlayer16.getIconWidth(),
		                                  iconPlayer16.getIconHeight()); 
		     player.playerUI.rand2_16.setPreferredSize(new Dimension(iconPlayer16.getIconWidth(),
		                                        iconPlayer16.getIconHeight()));													 												 													 
		
			  player.playerUI.mypanel0.add(player.playerUI.rand2_1);
		     player.playerUI.mypanel0.add(player.playerUI.rand2_2);
		     player.playerUI.mypanel0.add(player.playerUI.rand2_3);
		     player.playerUI.mypanel0.add(player.playerUI.rand2_4);
		     player.playerUI.mypanel0.add(player.playerUI.rand2_5);
		     player.playerUI.mypanel0.add(player.playerUI.rand2_6);
		     player.playerUI.mypanel0.add(player.playerUI.rand2_7);
		     player.playerUI.mypanel0.add(player.playerUI.rand2_8);
		     player.playerUI.mypanel0.add(player.playerUI.rand2_9);
		     player.playerUI.mypanel0.add(player.playerUI.rand2_10);
		     player.playerUI.mypanel0.add(player.playerUI.rand2_11);
		     player.playerUI.mypanel0.add(player.playerUI.rand2_12);
		     player.playerUI.mypanel0.add(player.playerUI.rand2_13);
		     player.playerUI.mypanel0.add(player.playerUI.rand2_14);
		     player.playerUI.mypanel0.add(player.playerUI.rand2_15);
		     player.playerUI.mypanel0.add(player.playerUI.rand2_16);
		  }
		  else if(tile == 1)
		  {
  	        iconPlayer1 =  (bp[0][4].getIcon());
	        iconPlayer2 =  (bp[1][4].getIcon());
 	        iconPlayer3 =  (bp[2][4].getIcon());
	        iconPlayer4 =  (bp[3][4].getIcon());
	        iconPlayer5 =  (bp[0][5].getIcon());
	        iconPlayer6 =  (bp[1][5].getIcon());
	        iconPlayer7 =  (bp[2][5].getIcon());
	        iconPlayer8 =  (bp[3][5].getIcon());
	        iconPlayer9 =  (bp[0][6].getIcon());
	        iconPlayer10 =  (bp[1][6].getIcon());
	        iconPlayer11 =  (bp[2][6].getIcon());
	        iconPlayer12 =  (bp[3][6].getIcon());
	        iconPlayer13 =  (bp[0][7].getIcon());
	        iconPlayer14 =  (bp[1][7].getIcon());
	        iconPlayer15 =  (bp[2][7].getIcon());
	        iconPlayer16 =  (bp[3][7].getIcon());

	
		//add components to board
			  GameJLabel boardPlayer1 = new GameJLabel(iconPlayer1);
			  GameJLabel boardPlayer2 = new GameJLabel(iconPlayer2);
			  GameJLabel boardPlayer3 = new GameJLabel(iconPlayer3);
			  GameJLabel boardPlayer4 = new GameJLabel(iconPlayer4);
			  GameJLabel boardPlayer5 = new GameJLabel(iconPlayer5);
			  GameJLabel boardPlayer6 = new GameJLabel(iconPlayer6);
			  GameJLabel boardPlayer7 = new GameJLabel(iconPlayer7);
			  GameJLabel boardPlayer8 = new GameJLabel(iconPlayer8);
			  GameJLabel boardPlayer9 = new GameJLabel(iconPlayer9);
			  GameJLabel boardPlayer10 = new GameJLabel(iconPlayer10);
			  GameJLabel boardPlayer11 = new GameJLabel(iconPlayer11);
			  GameJLabel boardPlayer12 = new GameJLabel(iconPlayer12);
			  GameJLabel boardPlayer13 = new GameJLabel(iconPlayer13);
			  GameJLabel boardPlayer14 = new GameJLabel(iconPlayer14);
			  GameJLabel boardPlayer15 = new GameJLabel(iconPlayer15);
			  GameJLabel boardPlayer16 = new GameJLabel(iconPlayer16);
		
			  player.playerUI.rand2_1.add(boardPlayer1, new Integer(0));
		     player.playerUI.rand2_2.add(boardPlayer2, new Integer(0));
		     player.playerUI.rand2_3.add(boardPlayer3, new Integer(0));
		     player.playerUI.rand2_4.add(boardPlayer4, new Integer(0));
		     player.playerUI.rand2_5.add(boardPlayer5, new Integer(0));
		     player.playerUI.rand2_6.add(boardPlayer6, new Integer(0));
		     player.playerUI.rand2_7.add(boardPlayer7, new Integer(0));
		     player.playerUI.rand2_8.add(boardPlayer8, new Integer(0));
		     player.playerUI.rand2_9.add(boardPlayer9, new Integer(0));
		     player.playerUI.rand2_10.add(boardPlayer10, new Integer(0));
		     player.playerUI.rand2_11.add(boardPlayer11, new Integer(0));
		     player.playerUI.rand2_12.add(boardPlayer12, new Integer(0));
		     player.playerUI.rand2_13.add(boardPlayer13, new Integer(0));
		     player.playerUI.rand2_14.add(boardPlayer14, new Integer(0));
		     player.playerUI.rand2_15.add(boardPlayer15, new Integer(0));
		     player.playerUI.rand2_16.add(boardPlayer16, new Integer(0));
		
		     boardPlayer1.setBounds(0, 0, iconPlayer1.getIconWidth(),
		                                  iconPlayer1.getIconHeight()); 
		     player.playerUI.rand2_1.setPreferredSize(new Dimension(iconPlayer1.getIconWidth(),
		                                        iconPlayer1.getIconHeight()));
		     boardPlayer2.setBounds(0, 0, iconPlayer2.getIconWidth(),
		                                  iconPlayer2.getIconHeight()); 
		     player.playerUI.rand2_2.setPreferredSize(new Dimension(iconPlayer2.getIconWidth(),
		                                        iconPlayer2.getIconHeight()));
		     boardPlayer3.setBounds(0, 0, iconPlayer3.getIconWidth(),
		                                  iconPlayer3.getIconHeight()); 
		     player.playerUI.rand2_3.setPreferredSize(new Dimension(iconPlayer3.getIconWidth(),
		                                        iconPlayer3.getIconHeight()));
		     boardPlayer4.setBounds(0, 0, iconPlayer4.getIconWidth(),
		                                  iconPlayer4.getIconHeight()); 
		     player.playerUI.rand2_4.setPreferredSize(new Dimension(iconPlayer4.getIconWidth(),
		                                        iconPlayer4.getIconHeight()));
		     boardPlayer5.setBounds(0, 0, iconPlayer5.getIconWidth(),
		                                  iconPlayer5.getIconHeight()); 
		     player.playerUI.rand2_5.setPreferredSize(new Dimension(iconPlayer5.getIconWidth(),
		                                        iconPlayer5.getIconHeight()));
		     boardPlayer6.setBounds(0, 0, iconPlayer6.getIconWidth(),
		                                  iconPlayer6.getIconHeight()); 
		     player.playerUI.rand2_6.setPreferredSize(new Dimension(iconPlayer6.getIconWidth(),
		                                        iconPlayer6.getIconHeight()));
		     boardPlayer7.setBounds(0, 0, iconPlayer7.getIconWidth(),
		                                  iconPlayer7.getIconHeight()); 
		     player.playerUI.rand2_7.setPreferredSize(new Dimension(iconPlayer7.getIconWidth(),
		                                        iconPlayer7.getIconHeight()));
		     boardPlayer8.setBounds(0, 0, iconPlayer8.getIconWidth(),
		                                  iconPlayer8.getIconHeight()); 
		     player.playerUI.rand2_8.setPreferredSize(new Dimension(iconPlayer8.getIconWidth(),
		                                        iconPlayer8.getIconHeight()));
		     boardPlayer9.setBounds(0, 0, iconPlayer9.getIconWidth(),
		                                  iconPlayer9.getIconHeight()); 
		     player.playerUI.rand2_9.setPreferredSize(new Dimension(iconPlayer9.getIconWidth(),
		                                        iconPlayer9.getIconHeight()));
		     boardPlayer10.setBounds(0, 0, iconPlayer10.getIconWidth(),
		                                  iconPlayer10.getIconHeight()); 
		     player.playerUI.rand2_10.setPreferredSize(new Dimension(iconPlayer10.getIconWidth(),
		                                        iconPlayer10.getIconHeight()));													 													 
		     boardPlayer11.setBounds(0, 0, iconPlayer11.getIconWidth(),
		                                  iconPlayer11.getIconHeight()); 
		     player.playerUI.rand2_11.setPreferredSize(new Dimension(iconPlayer11.getIconWidth(),
		                                        iconPlayer11.getIconHeight()));
		     boardPlayer12.setBounds(0, 0, iconPlayer12.getIconWidth(),
		                                  iconPlayer12.getIconHeight()); 
		     player.playerUI.rand2_12.setPreferredSize(new Dimension(iconPlayer12.getIconWidth(),
		                                        iconPlayer12.getIconHeight()));
		     boardPlayer13.setBounds(0, 0, iconPlayer13.getIconWidth(),
		                                  iconPlayer13.getIconHeight()); 
		     player.playerUI.rand2_13.setPreferredSize(new Dimension(iconPlayer13.getIconWidth(),
		                                        iconPlayer13.getIconHeight()));
		     boardPlayer14.setBounds(0, 0, iconPlayer14.getIconWidth(),
		                                  iconPlayer14.getIconHeight()); 
		     player.playerUI.rand2_14.setPreferredSize(new Dimension(iconPlayer14.getIconWidth(),
		                                        iconPlayer14.getIconHeight()));
		     boardPlayer15.setBounds(0, 0, iconPlayer15.getIconWidth(),
		                                  iconPlayer15.getIconHeight()); 
		     player.playerUI.rand2_15.setPreferredSize(new Dimension(iconPlayer15.getIconWidth(),
		                                        iconPlayer15.getIconHeight()));	
		     boardPlayer16.setBounds(0, 0, iconPlayer16.getIconWidth(),
		                                  iconPlayer16.getIconHeight()); 
		     player.playerUI.rand2_16.setPreferredSize(new Dimension(iconPlayer16.getIconWidth(),
		                                        iconPlayer16.getIconHeight()));													 												 													 
		
			  player.playerUI.mypanel1.add(player.playerUI.rand2_1);
		     player.playerUI.mypanel1.add(player.playerUI.rand2_2);
		     player.playerUI.mypanel1.add(player.playerUI.rand2_3);
		     player.playerUI.mypanel1.add(player.playerUI.rand2_4);
		     player.playerUI.mypanel1.add(player.playerUI.rand2_5);
		     player.playerUI.mypanel1.add(player.playerUI.rand2_6);
		     player.playerUI.mypanel1.add(player.playerUI.rand2_7);
		     player.playerUI.mypanel1.add(player.playerUI.rand2_8);
		     player.playerUI.mypanel1.add(player.playerUI.rand2_9);
		     player.playerUI.mypanel1.add(player.playerUI.rand2_10);
		     player.playerUI.mypanel1.add(player.playerUI.rand2_11);
		     player.playerUI.mypanel1.add(player.playerUI.rand2_12);
		     player.playerUI.mypanel1.add(player.playerUI.rand2_13);
		     player.playerUI.mypanel1.add(player.playerUI.rand2_14);
		     player.playerUI.mypanel1.add(player.playerUI.rand2_15);
		     player.playerUI.mypanel1.add(player.playerUI.rand2_16);
	  
		  }
		  else if(tile == 2)
		  {
  	        iconPlayer1 =  (bp[0][8].getIcon());
	        iconPlayer2 =  (bp[1][8].getIcon());
 	        iconPlayer3 =  (bp[2][8].getIcon());
	        iconPlayer4 =  (bp[3][8].getIcon());
	        iconPlayer5 =  (bp[0][9].getIcon());
	        iconPlayer6 =  (bp[1][9].getIcon());
	        iconPlayer7 =  (bp[2][9].getIcon());
	        iconPlayer8 =  (bp[3][9].getIcon());
	        iconPlayer9 =  (bp[0][10].getIcon());
	        iconPlayer10 =  (bp[1][10].getIcon());
	        iconPlayer11 =  (bp[2][10].getIcon());
	        iconPlayer12 =  (bp[3][10].getIcon());
	        iconPlayer13 =  (bp[0][11].getIcon());
	        iconPlayer14 =  (bp[1][11].getIcon());
	        iconPlayer15 =  (bp[2][11].getIcon());
	        iconPlayer16 =  (bp[3][11].getIcon());

	
		//add components to board
			  GameJLabel boardPlayer1 = new GameJLabel(iconPlayer1);
			  GameJLabel boardPlayer2 = new GameJLabel(iconPlayer2);
			  GameJLabel boardPlayer3 = new GameJLabel(iconPlayer3);
			  GameJLabel boardPlayer4 = new GameJLabel(iconPlayer4);
			  GameJLabel boardPlayer5 = new GameJLabel(iconPlayer5);
			  GameJLabel boardPlayer6 = new GameJLabel(iconPlayer6);
			  GameJLabel boardPlayer7 = new GameJLabel(iconPlayer7);
			  GameJLabel boardPlayer8 = new GameJLabel(iconPlayer8);
			  GameJLabel boardPlayer9 = new GameJLabel(iconPlayer9);
			  GameJLabel boardPlayer10 = new GameJLabel(iconPlayer10);
			  GameJLabel boardPlayer11 = new GameJLabel(iconPlayer11);
			  GameJLabel boardPlayer12 = new GameJLabel(iconPlayer12);
			  GameJLabel boardPlayer13 = new GameJLabel(iconPlayer13);
			  GameJLabel boardPlayer14 = new GameJLabel(iconPlayer14);
			  GameJLabel boardPlayer15 = new GameJLabel(iconPlayer15);
			  GameJLabel boardPlayer16 = new GameJLabel(iconPlayer16);
		
			  player.playerUI.rand2_1.add(boardPlayer1, new Integer(0));
		     player.playerUI.rand2_2.add(boardPlayer2, new Integer(0));
		     player.playerUI.rand2_3.add(boardPlayer3, new Integer(0));
		     player.playerUI.rand2_4.add(boardPlayer4, new Integer(0));
		     player.playerUI.rand2_5.add(boardPlayer5, new Integer(0));
		     player.playerUI.rand2_6.add(boardPlayer6, new Integer(0));
		     player.playerUI.rand2_7.add(boardPlayer7, new Integer(0));
		     player.playerUI.rand2_8.add(boardPlayer8, new Integer(0));
		     player.playerUI.rand2_9.add(boardPlayer9, new Integer(0));
		     player.playerUI.rand2_10.add(boardPlayer10, new Integer(0));
		     player.playerUI.rand2_11.add(boardPlayer11, new Integer(0));
		     player.playerUI.rand2_12.add(boardPlayer12, new Integer(0));
		     player.playerUI.rand2_13.add(boardPlayer13, new Integer(0));
		     player.playerUI.rand2_14.add(boardPlayer14, new Integer(0));
		     player.playerUI.rand2_15.add(boardPlayer15, new Integer(0));
		     player.playerUI.rand2_16.add(boardPlayer16, new Integer(0));
		
		     boardPlayer1.setBounds(0, 0, iconPlayer1.getIconWidth(),
		                                  iconPlayer1.getIconHeight()); 
		     player.playerUI.rand2_1.setPreferredSize(new Dimension(iconPlayer1.getIconWidth(),
		                                        iconPlayer1.getIconHeight()));
		     boardPlayer2.setBounds(0, 0, iconPlayer2.getIconWidth(),
		                                  iconPlayer2.getIconHeight()); 
		     player.playerUI.rand2_2.setPreferredSize(new Dimension(iconPlayer2.getIconWidth(),
		                                        iconPlayer2.getIconHeight()));
		     boardPlayer3.setBounds(0, 0, iconPlayer3.getIconWidth(),
		                                  iconPlayer3.getIconHeight()); 
		     player.playerUI.rand2_3.setPreferredSize(new Dimension(iconPlayer3.getIconWidth(),
		                                        iconPlayer3.getIconHeight()));
		     boardPlayer4.setBounds(0, 0, iconPlayer4.getIconWidth(),
		                                  iconPlayer4.getIconHeight()); 
		     player.playerUI.rand2_4.setPreferredSize(new Dimension(iconPlayer4.getIconWidth(),
		                                        iconPlayer4.getIconHeight()));
		     boardPlayer5.setBounds(0, 0, iconPlayer5.getIconWidth(),
		                                  iconPlayer5.getIconHeight()); 
		     player.playerUI.rand2_5.setPreferredSize(new Dimension(iconPlayer5.getIconWidth(),
		                                        iconPlayer5.getIconHeight()));
		     boardPlayer6.setBounds(0, 0, iconPlayer6.getIconWidth(),
		                                  iconPlayer6.getIconHeight()); 
		     player.playerUI.rand2_6.setPreferredSize(new Dimension(iconPlayer6.getIconWidth(),
		                                        iconPlayer6.getIconHeight()));
		     boardPlayer7.setBounds(0, 0, iconPlayer7.getIconWidth(),
		                                  iconPlayer7.getIconHeight()); 
		     player.playerUI.rand2_7.setPreferredSize(new Dimension(iconPlayer7.getIconWidth(),
		                                        iconPlayer7.getIconHeight()));
		     boardPlayer8.setBounds(0, 0, iconPlayer8.getIconWidth(),
		                                  iconPlayer8.getIconHeight()); 
		     player.playerUI.rand2_8.setPreferredSize(new Dimension(iconPlayer8.getIconWidth(),
		                                        iconPlayer8.getIconHeight()));
		     boardPlayer9.setBounds(0, 0, iconPlayer9.getIconWidth(),
		                                  iconPlayer9.getIconHeight()); 
		     player.playerUI.rand2_9.setPreferredSize(new Dimension(iconPlayer9.getIconWidth(),
		                                        iconPlayer9.getIconHeight()));
		     boardPlayer10.setBounds(0, 0, iconPlayer10.getIconWidth(),
		                                  iconPlayer10.getIconHeight()); 
		     player.playerUI.rand2_10.setPreferredSize(new Dimension(iconPlayer10.getIconWidth(),
		                                        iconPlayer10.getIconHeight()));													 													 
		     boardPlayer11.setBounds(0, 0, iconPlayer11.getIconWidth(),
		                                  iconPlayer11.getIconHeight()); 
		     player.playerUI.rand2_11.setPreferredSize(new Dimension(iconPlayer11.getIconWidth(),
		                                        iconPlayer11.getIconHeight()));
		     boardPlayer12.setBounds(0, 0, iconPlayer12.getIconWidth(),
		                                  iconPlayer12.getIconHeight()); 
		     player.playerUI.rand2_12.setPreferredSize(new Dimension(iconPlayer12.getIconWidth(),
		                                        iconPlayer12.getIconHeight()));
		     boardPlayer13.setBounds(0, 0, iconPlayer13.getIconWidth(),
		                                  iconPlayer13.getIconHeight()); 
		     player.playerUI.rand2_13.setPreferredSize(new Dimension(iconPlayer13.getIconWidth(),
		                                        iconPlayer13.getIconHeight()));
		     boardPlayer14.setBounds(0, 0, iconPlayer14.getIconWidth(),
		                                  iconPlayer14.getIconHeight()); 
		     player.playerUI.rand2_14.setPreferredSize(new Dimension(iconPlayer14.getIconWidth(),
		                                        iconPlayer14.getIconHeight()));
		     boardPlayer15.setBounds(0, 0, iconPlayer15.getIconWidth(),
		                                  iconPlayer15.getIconHeight()); 
		     player.playerUI.rand2_15.setPreferredSize(new Dimension(iconPlayer15.getIconWidth(),
		                                        iconPlayer15.getIconHeight()));	
		     boardPlayer16.setBounds(0, 0, iconPlayer16.getIconWidth(),
		                                  iconPlayer16.getIconHeight()); 
		     player.playerUI.rand2_16.setPreferredSize(new Dimension(iconPlayer16.getIconWidth(),
		                                        iconPlayer16.getIconHeight()));													 												 													 
		
			  player.playerUI.mypanel2.add(player.playerUI.rand2_1);
		     player.playerUI.mypanel2.add(player.playerUI.rand2_2);
		     player.playerUI.mypanel2.add(player.playerUI.rand2_3);
		     player.playerUI.mypanel2.add(player.playerUI.rand2_4);
		     player.playerUI.mypanel2.add(player.playerUI.rand2_5);
		     player.playerUI.mypanel2.add(player.playerUI.rand2_6);
		     player.playerUI.mypanel2.add(player.playerUI.rand2_7);
		     player.playerUI.mypanel2.add(player.playerUI.rand2_8);
		     player.playerUI.mypanel2.add(player.playerUI.rand2_9);
		     player.playerUI.mypanel2.add(player.playerUI.rand2_10);
		     player.playerUI.mypanel2.add(player.playerUI.rand2_11);
		     player.playerUI.mypanel2.add(player.playerUI.rand2_12);
		     player.playerUI.mypanel2.add(player.playerUI.rand2_13);
		     player.playerUI.mypanel2.add(player.playerUI.rand2_14);
		     player.playerUI.mypanel2.add(player.playerUI.rand2_15);
		     player.playerUI.mypanel2.add(player.playerUI.rand2_16);
	  
		  }
		  else if(tile == 5)
		  {
  	        iconPlayer1 =  (bp[4][4].getIcon());
	        iconPlayer2 =  (bp[5][4].getIcon());
 	        iconPlayer3 =  (bp[6][4].getIcon());
	        iconPlayer4 =  (bp[7][4].getIcon());
	        iconPlayer5 =  (bp[4][5].getIcon());
	        iconPlayer6 =  (bp[5][5].getIcon());
	        iconPlayer7 =  (bp[6][5].getIcon());
	        iconPlayer8 =  (bp[7][5].getIcon());
	        iconPlayer9 =  (bp[4][6].getIcon());
	        iconPlayer10 =  (bp[5][6].getIcon());
	        iconPlayer11 =  (bp[6][6].getIcon());
	        iconPlayer12 =  (bp[7][6].getIcon());
	        iconPlayer13 =  (bp[4][7].getIcon());
	        iconPlayer14 =  (bp[5][7].getIcon());
	        iconPlayer15 =  (bp[6][7].getIcon());
	        iconPlayer16 =  (bp[7][7].getIcon());

	
		//add components to board
			  GameJLabel boardPlayer1 = new GameJLabel(iconPlayer1);
			  GameJLabel boardPlayer2 = new GameJLabel(iconPlayer2);
			  GameJLabel boardPlayer3 = new GameJLabel(iconPlayer3);
			  GameJLabel boardPlayer4 = new GameJLabel(iconPlayer4);
			  GameJLabel boardPlayer5 = new GameJLabel(iconPlayer5);
			  GameJLabel boardPlayer6 = new GameJLabel(iconPlayer6);
			  GameJLabel boardPlayer7 = new GameJLabel(iconPlayer7);
			  GameJLabel boardPlayer8 = new GameJLabel(iconPlayer8);
			  GameJLabel boardPlayer9 = new GameJLabel(iconPlayer9);
			  GameJLabel boardPlayer10 = new GameJLabel(iconPlayer10);
			  GameJLabel boardPlayer11 = new GameJLabel(iconPlayer11);
			  GameJLabel boardPlayer12 = new GameJLabel(iconPlayer12);
			  GameJLabel boardPlayer13 = new GameJLabel(iconPlayer13);
			  GameJLabel boardPlayer14 = new GameJLabel(iconPlayer14);
			  GameJLabel boardPlayer15 = new GameJLabel(iconPlayer15);
			  GameJLabel boardPlayer16 = new GameJLabel(iconPlayer16);
		
			  player.playerUI.rand2_1.add(boardPlayer1, new Integer(0));
		     player.playerUI.rand2_2.add(boardPlayer2, new Integer(0));
		     player.playerUI.rand2_3.add(boardPlayer3, new Integer(0));
		     player.playerUI.rand2_4.add(boardPlayer4, new Integer(0));
		     player.playerUI.rand2_5.add(boardPlayer5, new Integer(0));
		     player.playerUI.rand2_6.add(boardPlayer6, new Integer(0));
		     player.playerUI.rand2_7.add(boardPlayer7, new Integer(0));
		     player.playerUI.rand2_8.add(boardPlayer8, new Integer(0));
		     player.playerUI.rand2_9.add(boardPlayer9, new Integer(0));
		     player.playerUI.rand2_10.add(boardPlayer10, new Integer(0));
		     player.playerUI.rand2_11.add(boardPlayer11, new Integer(0));
		     player.playerUI.rand2_12.add(boardPlayer12, new Integer(0));
		     player.playerUI.rand2_13.add(boardPlayer13, new Integer(0));
		     player.playerUI.rand2_14.add(boardPlayer14, new Integer(0));
		     player.playerUI.rand2_15.add(boardPlayer15, new Integer(0));
		     player.playerUI.rand2_16.add(boardPlayer16, new Integer(0));
		
		     boardPlayer1.setBounds(0, 0, iconPlayer1.getIconWidth(),
		                                  iconPlayer1.getIconHeight()); 
		     player.playerUI.rand2_1.setPreferredSize(new Dimension(iconPlayer1.getIconWidth(),
		                                        iconPlayer1.getIconHeight()));
		     boardPlayer2.setBounds(0, 0, iconPlayer2.getIconWidth(),
		                                  iconPlayer2.getIconHeight()); 
		     player.playerUI.rand2_2.setPreferredSize(new Dimension(iconPlayer2.getIconWidth(),
		                                        iconPlayer2.getIconHeight()));
		     boardPlayer3.setBounds(0, 0, iconPlayer3.getIconWidth(),
		                                  iconPlayer3.getIconHeight()); 
		     player.playerUI.rand2_3.setPreferredSize(new Dimension(iconPlayer3.getIconWidth(),
		                                        iconPlayer3.getIconHeight()));
		     boardPlayer4.setBounds(0, 0, iconPlayer4.getIconWidth(),
		                                  iconPlayer4.getIconHeight()); 
		     player.playerUI.rand2_4.setPreferredSize(new Dimension(iconPlayer4.getIconWidth(),
		                                        iconPlayer4.getIconHeight()));
		     boardPlayer5.setBounds(0, 0, iconPlayer5.getIconWidth(),
		                                  iconPlayer5.getIconHeight()); 
		     player.playerUI.rand2_5.setPreferredSize(new Dimension(iconPlayer5.getIconWidth(),
		                                        iconPlayer5.getIconHeight()));
		     boardPlayer6.setBounds(0, 0, iconPlayer6.getIconWidth(),
		                                  iconPlayer6.getIconHeight()); 
		     player.playerUI.rand2_6.setPreferredSize(new Dimension(iconPlayer6.getIconWidth(),
		                                        iconPlayer6.getIconHeight()));
		     boardPlayer7.setBounds(0, 0, iconPlayer7.getIconWidth(),
		                                  iconPlayer7.getIconHeight()); 
		     player.playerUI.rand2_7.setPreferredSize(new Dimension(iconPlayer7.getIconWidth(),
		                                        iconPlayer7.getIconHeight()));
		     boardPlayer8.setBounds(0, 0, iconPlayer8.getIconWidth(),
		                                  iconPlayer8.getIconHeight()); 
		     player.playerUI.rand2_8.setPreferredSize(new Dimension(iconPlayer8.getIconWidth(),
		                                        iconPlayer8.getIconHeight()));
		     boardPlayer9.setBounds(0, 0, iconPlayer9.getIconWidth(),
		                                  iconPlayer9.getIconHeight()); 
		     player.playerUI.rand2_9.setPreferredSize(new Dimension(iconPlayer9.getIconWidth(),
		                                        iconPlayer9.getIconHeight()));
		     boardPlayer10.setBounds(0, 0, iconPlayer10.getIconWidth(),
		                                  iconPlayer10.getIconHeight()); 
		     player.playerUI.rand2_10.setPreferredSize(new Dimension(iconPlayer10.getIconWidth(),
		                                        iconPlayer10.getIconHeight()));													 													 
		     boardPlayer11.setBounds(0, 0, iconPlayer11.getIconWidth(),
		                                  iconPlayer11.getIconHeight()); 
		     player.playerUI.rand2_11.setPreferredSize(new Dimension(iconPlayer11.getIconWidth(),
		                                        iconPlayer11.getIconHeight()));
		     boardPlayer12.setBounds(0, 0, iconPlayer12.getIconWidth(),
		                                  iconPlayer12.getIconHeight()); 
		     player.playerUI.rand2_12.setPreferredSize(new Dimension(iconPlayer12.getIconWidth(),
		                                        iconPlayer12.getIconHeight()));
		     boardPlayer13.setBounds(0, 0, iconPlayer13.getIconWidth(),
		                                  iconPlayer13.getIconHeight()); 
		     player.playerUI.rand2_13.setPreferredSize(new Dimension(iconPlayer13.getIconWidth(),
		                                        iconPlayer13.getIconHeight()));
		     boardPlayer14.setBounds(0, 0, iconPlayer14.getIconWidth(),
		                                  iconPlayer14.getIconHeight()); 
		     player.playerUI.rand2_14.setPreferredSize(new Dimension(iconPlayer14.getIconWidth(),
		                                        iconPlayer14.getIconHeight()));
		     boardPlayer15.setBounds(0, 0, iconPlayer15.getIconWidth(),
		                                  iconPlayer15.getIconHeight()); 
		     player.playerUI.rand2_15.setPreferredSize(new Dimension(iconPlayer15.getIconWidth(),
		                                        iconPlayer15.getIconHeight()));	
		     boardPlayer16.setBounds(0, 0, iconPlayer16.getIconWidth(),
		                                  iconPlayer16.getIconHeight()); 
		     player.playerUI.rand2_16.setPreferredSize(new Dimension(iconPlayer16.getIconWidth(),
		                                        iconPlayer16.getIconHeight()));													 												 													 
		
			  player.playerUI.mypanel5.add(player.playerUI.rand2_1);
		     player.playerUI.mypanel5.add(player.playerUI.rand2_2);
		     player.playerUI.mypanel5.add(player.playerUI.rand2_3);
		     player.playerUI.mypanel5.add(player.playerUI.rand2_4);
		     player.playerUI.mypanel5.add(player.playerUI.rand2_5);
		     player.playerUI.mypanel5.add(player.playerUI.rand2_6);
		     player.playerUI.mypanel5.add(player.playerUI.rand2_7);
		     player.playerUI.mypanel5.add(player.playerUI.rand2_8);
		     player.playerUI.mypanel5.add(player.playerUI.rand2_9);
		     player.playerUI.mypanel5.add(player.playerUI.rand2_10);
		     player.playerUI.mypanel5.add(player.playerUI.rand2_11);
		     player.playerUI.mypanel5.add(player.playerUI.rand2_12);
		     player.playerUI.mypanel5.add(player.playerUI.rand2_13);
		     player.playerUI.mypanel5.add(player.playerUI.rand2_14);
		     player.playerUI.mypanel5.add(player.playerUI.rand2_15);
		     player.playerUI.mypanel5.add(player.playerUI.rand2_16);
		  
		  }
		  else if(tile == 6)
		  {
  	        iconPlayer1 =  (bp[4][8].getIcon());
	        iconPlayer2 =  (bp[5][8].getIcon());
 	        iconPlayer3 =  (bp[6][8].getIcon());
	        iconPlayer4 =  (bp[7][8].getIcon());
	        iconPlayer5 =  (bp[4][9].getIcon());
	        iconPlayer6 =  (bp[5][9].getIcon());
	        iconPlayer7 =  (bp[6][9].getIcon());
	        iconPlayer8 =  (bp[7][9].getIcon());
	        iconPlayer9 =  (bp[4][10].getIcon());
	        iconPlayer10 =  (bp[5][10].getIcon());
	        iconPlayer11 =  (bp[6][10].getIcon());
	        iconPlayer12 =  (bp[7][10].getIcon());
	        iconPlayer13 =  (bp[4][11].getIcon());
	        iconPlayer14 =  (bp[5][11].getIcon());
	        iconPlayer15 =  (bp[6][11].getIcon());
	        iconPlayer16 =  (bp[7][11].getIcon());

	
		//add components to board
			  GameJLabel boardPlayer1 = new GameJLabel(iconPlayer1);
			  GameJLabel boardPlayer2 = new GameJLabel(iconPlayer2);
			  GameJLabel boardPlayer3 = new GameJLabel(iconPlayer3);
			  GameJLabel boardPlayer4 = new GameJLabel(iconPlayer4);
			  GameJLabel boardPlayer5 = new GameJLabel(iconPlayer5);
			  GameJLabel boardPlayer6 = new GameJLabel(iconPlayer6);
			  GameJLabel boardPlayer7 = new GameJLabel(iconPlayer7);
			  GameJLabel boardPlayer8 = new GameJLabel(iconPlayer8);
			  GameJLabel boardPlayer9 = new GameJLabel(iconPlayer9);
			  GameJLabel boardPlayer10 = new GameJLabel(iconPlayer10);
			  GameJLabel boardPlayer11 = new GameJLabel(iconPlayer11);
			  GameJLabel boardPlayer12 = new GameJLabel(iconPlayer12);
			  GameJLabel boardPlayer13 = new GameJLabel(iconPlayer13);
			  GameJLabel boardPlayer14 = new GameJLabel(iconPlayer14);
			  GameJLabel boardPlayer15 = new GameJLabel(iconPlayer15);
			  GameJLabel boardPlayer16 = new GameJLabel(iconPlayer16);
		
			  player.playerUI.rand2_1.add(boardPlayer1, new Integer(0));
		     player.playerUI.rand2_2.add(boardPlayer2, new Integer(0));
		     player.playerUI.rand2_3.add(boardPlayer3, new Integer(0));
		     player.playerUI.rand2_4.add(boardPlayer4, new Integer(0));
		     player.playerUI.rand2_5.add(boardPlayer5, new Integer(0));
		     player.playerUI.rand2_6.add(boardPlayer6, new Integer(0));
		     player.playerUI.rand2_7.add(boardPlayer7, new Integer(0));
		     player.playerUI.rand2_8.add(boardPlayer8, new Integer(0));
		     player.playerUI.rand2_9.add(boardPlayer9, new Integer(0));
		     player.playerUI.rand2_10.add(boardPlayer10, new Integer(0));
		     player.playerUI.rand2_11.add(boardPlayer11, new Integer(0));
		     player.playerUI.rand2_12.add(boardPlayer12, new Integer(0));
		     player.playerUI.rand2_13.add(boardPlayer13, new Integer(0));
		     player.playerUI.rand2_14.add(boardPlayer14, new Integer(0));
		     player.playerUI.rand2_15.add(boardPlayer15, new Integer(0));
		     player.playerUI.rand2_16.add(boardPlayer16, new Integer(0));
		
		     boardPlayer1.setBounds(0, 0, iconPlayer1.getIconWidth(),
		                                  iconPlayer1.getIconHeight()); 
		     player.playerUI.rand2_1.setPreferredSize(new Dimension(iconPlayer1.getIconWidth(),
		                                        iconPlayer1.getIconHeight()));
		     boardPlayer2.setBounds(0, 0, iconPlayer2.getIconWidth(),
		                                  iconPlayer2.getIconHeight()); 
		     player.playerUI.rand2_2.setPreferredSize(new Dimension(iconPlayer2.getIconWidth(),
		                                        iconPlayer2.getIconHeight()));
		     boardPlayer3.setBounds(0, 0, iconPlayer3.getIconWidth(),
		                                  iconPlayer3.getIconHeight()); 
		     player.playerUI.rand2_3.setPreferredSize(new Dimension(iconPlayer3.getIconWidth(),
		                                        iconPlayer3.getIconHeight()));
		     boardPlayer4.setBounds(0, 0, iconPlayer4.getIconWidth(),
		                                  iconPlayer4.getIconHeight()); 
		     player.playerUI.rand2_4.setPreferredSize(new Dimension(iconPlayer4.getIconWidth(),
		                                        iconPlayer4.getIconHeight()));
		     boardPlayer5.setBounds(0, 0, iconPlayer5.getIconWidth(),
		                                  iconPlayer5.getIconHeight()); 
		     player.playerUI.rand2_5.setPreferredSize(new Dimension(iconPlayer5.getIconWidth(),
		                                        iconPlayer5.getIconHeight()));
		     boardPlayer6.setBounds(0, 0, iconPlayer6.getIconWidth(),
		                                  iconPlayer6.getIconHeight()); 
		     player.playerUI.rand2_6.setPreferredSize(new Dimension(iconPlayer6.getIconWidth(),
		                                        iconPlayer6.getIconHeight()));
		     boardPlayer7.setBounds(0, 0, iconPlayer7.getIconWidth(),
		                                  iconPlayer7.getIconHeight()); 
		     player.playerUI.rand2_7.setPreferredSize(new Dimension(iconPlayer7.getIconWidth(),
		                                        iconPlayer7.getIconHeight()));
		     boardPlayer8.setBounds(0, 0, iconPlayer8.getIconWidth(),
		                                  iconPlayer8.getIconHeight()); 
		     player.playerUI.rand2_8.setPreferredSize(new Dimension(iconPlayer8.getIconWidth(),
		                                        iconPlayer8.getIconHeight()));
		     boardPlayer9.setBounds(0, 0, iconPlayer9.getIconWidth(),
		                                  iconPlayer9.getIconHeight()); 
		     player.playerUI.rand2_9.setPreferredSize(new Dimension(iconPlayer9.getIconWidth(),
		                                        iconPlayer9.getIconHeight()));
		     boardPlayer10.setBounds(0, 0, iconPlayer10.getIconWidth(),
		                                  iconPlayer10.getIconHeight()); 
		     player.playerUI.rand2_10.setPreferredSize(new Dimension(iconPlayer10.getIconWidth(),
		                                        iconPlayer10.getIconHeight()));													 													 
		     boardPlayer11.setBounds(0, 0, iconPlayer11.getIconWidth(),
		                                  iconPlayer11.getIconHeight()); 
		     player.playerUI.rand2_11.setPreferredSize(new Dimension(iconPlayer11.getIconWidth(),
		                                        iconPlayer11.getIconHeight()));
		     boardPlayer12.setBounds(0, 0, iconPlayer12.getIconWidth(),
		                                  iconPlayer12.getIconHeight()); 
		     player.playerUI.rand2_12.setPreferredSize(new Dimension(iconPlayer12.getIconWidth(),
		                                        iconPlayer12.getIconHeight()));
		     boardPlayer13.setBounds(0, 0, iconPlayer13.getIconWidth(),
		                                  iconPlayer13.getIconHeight()); 
		     player.playerUI.rand2_13.setPreferredSize(new Dimension(iconPlayer13.getIconWidth(),
		                                        iconPlayer13.getIconHeight()));
		     boardPlayer14.setBounds(0, 0, iconPlayer14.getIconWidth(),
		                                  iconPlayer14.getIconHeight()); 
		     player.playerUI.rand2_14.setPreferredSize(new Dimension(iconPlayer14.getIconWidth(),
		                                        iconPlayer14.getIconHeight()));
		     boardPlayer15.setBounds(0, 0, iconPlayer15.getIconWidth(),
		                                  iconPlayer15.getIconHeight()); 
		     player.playerUI.rand2_15.setPreferredSize(new Dimension(iconPlayer15.getIconWidth(),
		                                        iconPlayer15.getIconHeight()));	
		     boardPlayer16.setBounds(0, 0, iconPlayer16.getIconWidth(),
		                                  iconPlayer16.getIconHeight()); 
		     player.playerUI.rand2_16.setPreferredSize(new Dimension(iconPlayer16.getIconWidth(),
		                                        iconPlayer16.getIconHeight()));													 												 													 
		
			  player.playerUI.mypanel6.add(player.playerUI.rand2_1);
		     player.playerUI.mypanel6.add(player.playerUI.rand2_2);
		     player.playerUI.mypanel6.add(player.playerUI.rand2_3);
		     player.playerUI.mypanel6.add(player.playerUI.rand2_4);
		     player.playerUI.mypanel6.add(player.playerUI.rand2_5);
		     player.playerUI.mypanel6.add(player.playerUI.rand2_6);
		     player.playerUI.mypanel6.add(player.playerUI.rand2_7);
		     player.playerUI.mypanel6.add(player.playerUI.rand2_8);
		     player.playerUI.mypanel6.add(player.playerUI.rand2_9);
		     player.playerUI.mypanel6.add(player.playerUI.rand2_10);
		     player.playerUI.mypanel6.add(player.playerUI.rand2_11);
		     player.playerUI.mypanel6.add(player.playerUI.rand2_12);
		     player.playerUI.mypanel6.add(player.playerUI.rand2_13);
		     player.playerUI.mypanel6.add(player.playerUI.rand2_14);
		     player.playerUI.mypanel6.add(player.playerUI.rand2_15);
		     player.playerUI.mypanel6.add(player.playerUI.rand2_16);
		  
		  }
		  else if(tile == 7)
		  {
	       iconPlayer1 =  bp[4][12].getIcon();
	       iconPlayer2 =  bp[5][12].getIcon();
	       iconPlayer3 =  bp[6][12].getIcon();
	       iconPlayer4 =  bp[7][12].getIcon();
	       iconPlayer5 =  bp[4][13].getIcon();
	       iconPlayer6 =  bp[5][13].getIcon();
	       iconPlayer7 =  bp[6][13].getIcon();
	       iconPlayer8 =  bp[7][13].getIcon();
	       iconPlayer9 =  bp[4][14].getIcon();
	       iconPlayer10 =  bp[5][14].getIcon();
	       iconPlayer11 =  bp[6][14].getIcon();
	       iconPlayer12 =  bp[7][14].getIcon();
	       iconPlayer13 =  bp[4][15].getIcon();
	       iconPlayer14 =  bp[5][15].getIcon();
	       iconPlayer15 =  bp[6][15].getIcon();
	       iconPlayer16 =  bp[7][15].getIcon();


		//add components to board
			  GameJLabel boardPlayer1 = new GameJLabel(iconPlayer1);
			  GameJLabel boardPlayer2 = new GameJLabel(iconPlayer2);
			  GameJLabel boardPlayer3 = new GameJLabel(iconPlayer3);
			  GameJLabel boardPlayer4 = new GameJLabel(iconPlayer4);
			  GameJLabel boardPlayer5 = new GameJLabel(iconPlayer5);
			  GameJLabel boardPlayer6 = new GameJLabel(iconPlayer6);
			  GameJLabel boardPlayer7 = new GameJLabel(iconPlayer7);
			  GameJLabel boardPlayer8 = new GameJLabel(iconPlayer8);
			  GameJLabel boardPlayer9 = new GameJLabel(iconPlayer9);
			  GameJLabel boardPlayer10 = new GameJLabel(iconPlayer10);
			  GameJLabel boardPlayer11 = new GameJLabel(iconPlayer11);
			  GameJLabel boardPlayer12 = new GameJLabel(iconPlayer12);
			  GameJLabel boardPlayer13 = new GameJLabel(iconPlayer13);
			  GameJLabel boardPlayer14 = new GameJLabel(iconPlayer14);
			  GameJLabel boardPlayer15 = new GameJLabel(iconPlayer15);
			  GameJLabel boardPlayer16 = new GameJLabel(iconPlayer16);
		
			  player.playerUI.rand2_1.add(boardPlayer1, new Integer(0));
		     player.playerUI.rand2_2.add(boardPlayer2, new Integer(0));
		     player.playerUI.rand2_3.add(boardPlayer3, new Integer(0));
		     player.playerUI.rand2_4.add(boardPlayer4, new Integer(0));
		     player.playerUI.rand2_5.add(boardPlayer5, new Integer(0));
		     player.playerUI.rand2_6.add(boardPlayer6, new Integer(0));
		     player.playerUI.rand2_7.add(boardPlayer7, new Integer(0));
		     player.playerUI.rand2_8.add(boardPlayer8, new Integer(0));
		     player.playerUI.rand2_9.add(boardPlayer9, new Integer(0));
		     player.playerUI.rand2_10.add(boardPlayer10, new Integer(0));
		     player.playerUI.rand2_11.add(boardPlayer11, new Integer(0));
		     player.playerUI.rand2_12.add(boardPlayer12, new Integer(0));
		     player.playerUI.rand2_13.add(boardPlayer13, new Integer(0));
		     player.playerUI.rand2_14.add(boardPlayer14, new Integer(0));
		     player.playerUI.rand2_15.add(boardPlayer15, new Integer(0));
		     player.playerUI.rand2_16.add(boardPlayer16, new Integer(0));
		
		     boardPlayer1.setBounds(0, 0, iconPlayer1.getIconWidth(),
		                                  iconPlayer1.getIconHeight()); 
		     player.playerUI.rand2_1.setPreferredSize(new Dimension(iconPlayer1.getIconWidth(),
		                                        iconPlayer1.getIconHeight()));
		     boardPlayer2.setBounds(0, 0, iconPlayer2.getIconWidth(),
		                                  iconPlayer2.getIconHeight()); 
		     player.playerUI.rand2_2.setPreferredSize(new Dimension(iconPlayer2.getIconWidth(),
		                                        iconPlayer2.getIconHeight()));
		     boardPlayer3.setBounds(0, 0, iconPlayer3.getIconWidth(),
		                                  iconPlayer3.getIconHeight()); 
		     player.playerUI.rand2_3.setPreferredSize(new Dimension(iconPlayer3.getIconWidth(),
		                                        iconPlayer3.getIconHeight()));
		     boardPlayer4.setBounds(0, 0, iconPlayer4.getIconWidth(),
		                                  iconPlayer4.getIconHeight()); 
		     player.playerUI.rand2_4.setPreferredSize(new Dimension(iconPlayer4.getIconWidth(),
		                                        iconPlayer4.getIconHeight()));
		     boardPlayer5.setBounds(0, 0, iconPlayer5.getIconWidth(),
		                                  iconPlayer5.getIconHeight()); 
		     player.playerUI.rand2_5.setPreferredSize(new Dimension(iconPlayer5.getIconWidth(),
		                                        iconPlayer5.getIconHeight()));
		     boardPlayer6.setBounds(0, 0, iconPlayer6.getIconWidth(),
		                                  iconPlayer6.getIconHeight()); 
		     player.playerUI.rand2_6.setPreferredSize(new Dimension(iconPlayer6.getIconWidth(),
		                                        iconPlayer6.getIconHeight()));
		     boardPlayer7.setBounds(0, 0, iconPlayer7.getIconWidth(),
		                                  iconPlayer7.getIconHeight()); 
		     player.playerUI.rand2_7.setPreferredSize(new Dimension(iconPlayer7.getIconWidth(),
		                                        iconPlayer7.getIconHeight()));
		     boardPlayer8.setBounds(0, 0, iconPlayer8.getIconWidth(),
		                                  iconPlayer8.getIconHeight()); 
		     player.playerUI.rand2_8.setPreferredSize(new Dimension(iconPlayer8.getIconWidth(),
		                                        iconPlayer8.getIconHeight()));
		     boardPlayer9.setBounds(0, 0, iconPlayer9.getIconWidth(),
		                                  iconPlayer9.getIconHeight()); 
		     player.playerUI.rand2_9.setPreferredSize(new Dimension(iconPlayer9.getIconWidth(),
		                                        iconPlayer9.getIconHeight()));
		     boardPlayer10.setBounds(0, 0, iconPlayer10.getIconWidth(),
		                                  iconPlayer10.getIconHeight()); 
		     player.playerUI.rand2_10.setPreferredSize(new Dimension(iconPlayer10.getIconWidth(),
		                                        iconPlayer10.getIconHeight()));													 													 
		     boardPlayer11.setBounds(0, 0, iconPlayer11.getIconWidth(),
		                                  iconPlayer11.getIconHeight()); 
		     player.playerUI.rand2_11.setPreferredSize(new Dimension(iconPlayer11.getIconWidth(),
		                                        iconPlayer11.getIconHeight()));
		     boardPlayer12.setBounds(0, 0, iconPlayer12.getIconWidth(),
		                                  iconPlayer12.getIconHeight()); 
		     player.playerUI.rand2_12.setPreferredSize(new Dimension(iconPlayer12.getIconWidth(),
		                                        iconPlayer12.getIconHeight()));
		     boardPlayer13.setBounds(0, 0, iconPlayer13.getIconWidth(),
		                                  iconPlayer13.getIconHeight()); 
		     player.playerUI.rand2_13.setPreferredSize(new Dimension(iconPlayer13.getIconWidth(),
		                                        iconPlayer13.getIconHeight()));
		     boardPlayer14.setBounds(0, 0, iconPlayer14.getIconWidth(),
		                                  iconPlayer14.getIconHeight()); 
		     player.playerUI.rand2_14.setPreferredSize(new Dimension(iconPlayer14.getIconWidth(),
		                                        iconPlayer14.getIconHeight()));
		     boardPlayer15.setBounds(0, 0, iconPlayer15.getIconWidth(),
		                                  iconPlayer15.getIconHeight()); 
		     player.playerUI.rand2_15.setPreferredSize(new Dimension(iconPlayer15.getIconWidth(),
		                                        iconPlayer15.getIconHeight()));	
		     boardPlayer16.setBounds(0, 0, iconPlayer16.getIconWidth(),
		                                  iconPlayer16.getIconHeight()); 
		     player.playerUI.rand2_16.setPreferredSize(new Dimension(iconPlayer16.getIconWidth(),
		                                        iconPlayer16.getIconHeight()));													 												 													 
		
			  player.playerUI.mypanel7.add(player.playerUI.rand2_1);
		     player.playerUI.mypanel7.add(player.playerUI.rand2_2);
		     player.playerUI.mypanel7.add(player.playerUI.rand2_3);
		     player.playerUI.mypanel7.add(player.playerUI.rand2_4);
		     player.playerUI.mypanel7.add(player.playerUI.rand2_5);
		     player.playerUI.mypanel7.add(player.playerUI.rand2_6);
		     player.playerUI.mypanel7.add(player.playerUI.rand2_7);
		     player.playerUI.mypanel7.add(player.playerUI.rand2_8);
		     player.playerUI.mypanel7.add(player.playerUI.rand2_9);
		     player.playerUI.mypanel7.add(player.playerUI.rand2_10);
		     player.playerUI.mypanel7.add(player.playerUI.rand2_11);
		     player.playerUI.mypanel7.add(player.playerUI.rand2_12);
		     player.playerUI.mypanel7.add(player.playerUI.rand2_13);
		     player.playerUI.mypanel7.add(player.playerUI.rand2_14);
		     player.playerUI.mypanel7.add(player.playerUI.rand2_15);
		     player.playerUI.mypanel7.add(player.playerUI.rand2_16);
		  
		  }
		}

      private void populateRand3(int tile, Player player)
		{
		  if(tile == 0)
		  {
			  iconPlayer1 =  (bp[0][0].getIcon());
	        iconPlayer2 =  (bp[1][0].getIcon());
 	        iconPlayer3 =  (bp[2][0].getIcon());
	        iconPlayer4 =  (bp[3][0].getIcon());
	        iconPlayer5 =  (bp[0][1].getIcon());
	        iconPlayer6 =  (bp[1][1].getIcon());
	        iconPlayer7 =  (bp[2][1].getIcon());
	        iconPlayer8 =  (bp[3][1].getIcon());
	        iconPlayer9 =  (bp[0][2].getIcon());
	        iconPlayer10 =  (bp[1][2].getIcon());
	        iconPlayer11 =  (bp[2][2].getIcon());
	        iconPlayer12 =  (bp[3][2].getIcon());
	        iconPlayer13 =  (bp[0][3].getIcon());
	        iconPlayer14 =  (bp[1][3].getIcon());
	        iconPlayer15 =  (bp[2][3].getIcon());
	        iconPlayer16 =  (bp[3][3].getIcon());

	
		//add components to board
			  GameJLabel boardPlayer1 = new GameJLabel(iconPlayer1);
			  GameJLabel boardPlayer2 = new GameJLabel(iconPlayer2);
			  GameJLabel boardPlayer3 = new GameJLabel(iconPlayer3);
			  GameJLabel boardPlayer4 = new GameJLabel(iconPlayer4);
			  GameJLabel boardPlayer5 = new GameJLabel(iconPlayer5);
			  GameJLabel boardPlayer6 = new GameJLabel(iconPlayer6);
			  GameJLabel boardPlayer7 = new GameJLabel(iconPlayer7);
			  GameJLabel boardPlayer8 = new GameJLabel(iconPlayer8);
			  GameJLabel boardPlayer9 = new GameJLabel(iconPlayer9);
			  GameJLabel boardPlayer10 = new GameJLabel(iconPlayer10);
			  GameJLabel boardPlayer11 = new GameJLabel(iconPlayer11);
			  GameJLabel boardPlayer12 = new GameJLabel(iconPlayer12);
			  GameJLabel boardPlayer13 = new GameJLabel(iconPlayer13);
			  GameJLabel boardPlayer14 = new GameJLabel(iconPlayer14);
			  GameJLabel boardPlayer15 = new GameJLabel(iconPlayer15);
			  GameJLabel boardPlayer16 = new GameJLabel(iconPlayer16);
		
			  player.playerUI.rand3_1.add(boardPlayer1, new Integer(0));
		     player.playerUI.rand3_2.add(boardPlayer2, new Integer(0));
		     player.playerUI.rand3_3.add(boardPlayer3, new Integer(0));
		     player.playerUI.rand3_4.add(boardPlayer4, new Integer(0));
		     player.playerUI.rand3_5.add(boardPlayer5, new Integer(0));
		     player.playerUI.rand3_6.add(boardPlayer6, new Integer(0));
		     player.playerUI.rand3_7.add(boardPlayer7, new Integer(0));
		     player.playerUI.rand3_8.add(boardPlayer8, new Integer(0));
		     player.playerUI.rand3_9.add(boardPlayer9, new Integer(0));
		     player.playerUI.rand3_10.add(boardPlayer10, new Integer(0));
		     player.playerUI.rand3_11.add(boardPlayer11, new Integer(0));
		     player.playerUI.rand3_12.add(boardPlayer12, new Integer(0));
		     player.playerUI.rand3_13.add(boardPlayer13, new Integer(0));
		     player.playerUI.rand3_14.add(boardPlayer14, new Integer(0));
		     player.playerUI.rand3_15.add(boardPlayer15, new Integer(0));
		     player.playerUI.rand3_16.add(boardPlayer16, new Integer(0));
		
		     boardPlayer1.setBounds(0, 0, iconPlayer1.getIconWidth(),
		                                  iconPlayer1.getIconHeight()); 
		     player.playerUI.rand3_1.setPreferredSize(new Dimension(iconPlayer1.getIconWidth(),
		                                        iconPlayer1.getIconHeight()));
		     boardPlayer2.setBounds(0, 0, iconPlayer2.getIconWidth(),
		                                  iconPlayer2.getIconHeight()); 
		     player.playerUI.rand3_2.setPreferredSize(new Dimension(iconPlayer2.getIconWidth(),
		                                        iconPlayer2.getIconHeight()));
		     boardPlayer3.setBounds(0, 0, iconPlayer3.getIconWidth(),
		                                  iconPlayer3.getIconHeight()); 
		     player.playerUI.rand3_3.setPreferredSize(new Dimension(iconPlayer3.getIconWidth(),
		                                        iconPlayer3.getIconHeight()));
		     boardPlayer4.setBounds(0, 0, iconPlayer4.getIconWidth(),
		                                  iconPlayer4.getIconHeight()); 
		     player.playerUI.rand3_4.setPreferredSize(new Dimension(iconPlayer4.getIconWidth(),
		                                        iconPlayer4.getIconHeight()));
		     boardPlayer5.setBounds(0, 0, iconPlayer5.getIconWidth(),
		                                  iconPlayer5.getIconHeight()); 
		     player.playerUI.rand3_5.setPreferredSize(new Dimension(iconPlayer5.getIconWidth(),
		                                        iconPlayer5.getIconHeight()));
		     boardPlayer6.setBounds(0, 0, iconPlayer6.getIconWidth(),
		                                  iconPlayer6.getIconHeight()); 
		     player.playerUI.rand3_6.setPreferredSize(new Dimension(iconPlayer6.getIconWidth(),
		                                        iconPlayer6.getIconHeight()));
		     boardPlayer7.setBounds(0, 0, iconPlayer7.getIconWidth(),
		                                  iconPlayer7.getIconHeight()); 
		     player.playerUI.rand3_7.setPreferredSize(new Dimension(iconPlayer7.getIconWidth(),
		                                        iconPlayer7.getIconHeight()));
		     boardPlayer8.setBounds(0, 0, iconPlayer8.getIconWidth(),
		                                  iconPlayer8.getIconHeight()); 
		     player.playerUI.rand3_8.setPreferredSize(new Dimension(iconPlayer8.getIconWidth(),
		                                        iconPlayer8.getIconHeight()));
		     boardPlayer9.setBounds(0, 0, iconPlayer9.getIconWidth(),
		                                  iconPlayer9.getIconHeight()); 
		     player.playerUI.rand3_9.setPreferredSize(new Dimension(iconPlayer9.getIconWidth(),
		                                        iconPlayer9.getIconHeight()));
		     boardPlayer10.setBounds(0, 0, iconPlayer10.getIconWidth(),
		                                  iconPlayer10.getIconHeight()); 
		     player.playerUI.rand3_10.setPreferredSize(new Dimension(iconPlayer10.getIconWidth(),
		                                        iconPlayer10.getIconHeight()));													 													 
		     boardPlayer11.setBounds(0, 0, iconPlayer11.getIconWidth(),
		                                  iconPlayer11.getIconHeight()); 
		     player.playerUI.rand3_11.setPreferredSize(new Dimension(iconPlayer11.getIconWidth(),
		                                        iconPlayer11.getIconHeight()));
		     boardPlayer12.setBounds(0, 0, iconPlayer12.getIconWidth(),
		                                  iconPlayer12.getIconHeight()); 
		     player.playerUI.rand3_12.setPreferredSize(new Dimension(iconPlayer12.getIconWidth(),
		                                        iconPlayer12.getIconHeight()));
		     boardPlayer13.setBounds(0, 0, iconPlayer13.getIconWidth(),
		                                  iconPlayer13.getIconHeight()); 
		     player.playerUI.rand3_13.setPreferredSize(new Dimension(iconPlayer13.getIconWidth(),
		                                        iconPlayer13.getIconHeight()));
		     boardPlayer14.setBounds(0, 0, iconPlayer14.getIconWidth(),
		                                  iconPlayer14.getIconHeight()); 
		     player.playerUI.rand3_14.setPreferredSize(new Dimension(iconPlayer14.getIconWidth(),
		                                        iconPlayer14.getIconHeight()));
		     boardPlayer15.setBounds(0, 0, iconPlayer15.getIconWidth(),
		                                  iconPlayer15.getIconHeight()); 
		     player.playerUI.rand3_15.setPreferredSize(new Dimension(iconPlayer15.getIconWidth(),
		                                        iconPlayer15.getIconHeight()));	
		     boardPlayer16.setBounds(0, 0, iconPlayer16.getIconWidth(),
		                                  iconPlayer16.getIconHeight()); 
		     player.playerUI.rand3_16.setPreferredSize(new Dimension(iconPlayer16.getIconWidth(),
		                                        iconPlayer16.getIconHeight()));													 												 													 
		
			  player.playerUI.mypanel0.add(player.playerUI.rand3_1);
		     player.playerUI.mypanel0.add(player.playerUI.rand3_2);
		     player.playerUI.mypanel0.add(player.playerUI.rand3_3);
		     player.playerUI.mypanel0.add(player.playerUI.rand3_4);
		     player.playerUI.mypanel0.add(player.playerUI.rand3_5);
		     player.playerUI.mypanel0.add(player.playerUI.rand3_6);
		     player.playerUI.mypanel0.add(player.playerUI.rand3_7);
		     player.playerUI.mypanel0.add(player.playerUI.rand3_8);
		     player.playerUI.mypanel0.add(player.playerUI.rand3_9);
		     player.playerUI.mypanel0.add(player.playerUI.rand3_10);
		     player.playerUI.mypanel0.add(player.playerUI.rand3_11);
		     player.playerUI.mypanel0.add(player.playerUI.rand3_12);
		     player.playerUI.mypanel0.add(player.playerUI.rand3_13);
		     player.playerUI.mypanel0.add(player.playerUI.rand3_14);
		     player.playerUI.mypanel0.add(player.playerUI.rand3_15);
		     player.playerUI.mypanel0.add(player.playerUI.rand3_16);
		  }
		  else if(tile == 1)
		  {
  	        iconPlayer1 =  (bp[0][4].getIcon());
	        iconPlayer2 =  (bp[1][4].getIcon());
 	        iconPlayer3 =  (bp[2][4].getIcon());
	        iconPlayer4 =  (bp[3][4].getIcon());
	        iconPlayer5 =  (bp[0][5].getIcon());
	        iconPlayer6 =  (bp[1][5].getIcon());
	        iconPlayer7 =  (bp[2][5].getIcon());
	        iconPlayer8 =  (bp[3][5].getIcon());
	        iconPlayer9 =  (bp[0][6].getIcon());
	        iconPlayer10 =  (bp[1][6].getIcon());
	        iconPlayer11 =  (bp[2][6].getIcon());
	        iconPlayer12 =  (bp[3][6].getIcon());
	        iconPlayer13 =  (bp[0][7].getIcon());
	        iconPlayer14 =  (bp[1][7].getIcon());
	        iconPlayer15 =  (bp[2][7].getIcon());
	        iconPlayer16 =  (bp[3][7].getIcon());

	
		//add components to board
			  GameJLabel boardPlayer1 = new GameJLabel(iconPlayer1);
			  GameJLabel boardPlayer2 = new GameJLabel(iconPlayer2);
			  GameJLabel boardPlayer3 = new GameJLabel(iconPlayer3);
			  GameJLabel boardPlayer4 = new GameJLabel(iconPlayer4);
			  GameJLabel boardPlayer5 = new GameJLabel(iconPlayer5);
			  GameJLabel boardPlayer6 = new GameJLabel(iconPlayer6);
			  GameJLabel boardPlayer7 = new GameJLabel(iconPlayer7);
			  GameJLabel boardPlayer8 = new GameJLabel(iconPlayer8);
			  GameJLabel boardPlayer9 = new GameJLabel(iconPlayer9);
			  GameJLabel boardPlayer10 = new GameJLabel(iconPlayer10);
			  GameJLabel boardPlayer11 = new GameJLabel(iconPlayer11);
			  GameJLabel boardPlayer12 = new GameJLabel(iconPlayer12);
			  GameJLabel boardPlayer13 = new GameJLabel(iconPlayer13);
			  GameJLabel boardPlayer14 = new GameJLabel(iconPlayer14);
			  GameJLabel boardPlayer15 = new GameJLabel(iconPlayer15);
			  GameJLabel boardPlayer16 = new GameJLabel(iconPlayer16);
		
			  player.playerUI.rand3_1.add(boardPlayer1, new Integer(0));
		     player.playerUI.rand3_2.add(boardPlayer2, new Integer(0));
		     player.playerUI.rand3_3.add(boardPlayer3, new Integer(0));
		     player.playerUI.rand3_4.add(boardPlayer4, new Integer(0));
		     player.playerUI.rand3_5.add(boardPlayer5, new Integer(0));
		     player.playerUI.rand3_6.add(boardPlayer6, new Integer(0));
		     player.playerUI.rand3_7.add(boardPlayer7, new Integer(0));
		     player.playerUI.rand3_8.add(boardPlayer8, new Integer(0));
		     player.playerUI.rand3_9.add(boardPlayer9, new Integer(0));
		     player.playerUI.rand3_10.add(boardPlayer10, new Integer(0));
		     player.playerUI.rand3_11.add(boardPlayer11, new Integer(0));
		     player.playerUI.rand3_12.add(boardPlayer12, new Integer(0));
		     player.playerUI.rand3_13.add(boardPlayer13, new Integer(0));
		     player.playerUI.rand3_14.add(boardPlayer14, new Integer(0));
		     player.playerUI.rand3_15.add(boardPlayer15, new Integer(0));
		     player.playerUI.rand3_16.add(boardPlayer16, new Integer(0));
		
		     boardPlayer1.setBounds(0, 0, iconPlayer1.getIconWidth(),
		                                  iconPlayer1.getIconHeight()); 
		     player.playerUI.rand3_1.setPreferredSize(new Dimension(iconPlayer1.getIconWidth(),
		                                        iconPlayer1.getIconHeight()));
		     boardPlayer2.setBounds(0, 0, iconPlayer2.getIconWidth(),
		                                  iconPlayer2.getIconHeight()); 
		     player.playerUI.rand3_2.setPreferredSize(new Dimension(iconPlayer2.getIconWidth(),
		                                        iconPlayer2.getIconHeight()));
		     boardPlayer3.setBounds(0, 0, iconPlayer3.getIconWidth(),
		                                  iconPlayer3.getIconHeight()); 
		     player.playerUI.rand3_3.setPreferredSize(new Dimension(iconPlayer3.getIconWidth(),
		                                        iconPlayer3.getIconHeight()));
		     boardPlayer4.setBounds(0, 0, iconPlayer4.getIconWidth(),
		                                  iconPlayer4.getIconHeight()); 
		     player.playerUI.rand3_4.setPreferredSize(new Dimension(iconPlayer4.getIconWidth(),
		                                        iconPlayer4.getIconHeight()));
		     boardPlayer5.setBounds(0, 0, iconPlayer5.getIconWidth(),
		                                  iconPlayer5.getIconHeight()); 
		     player.playerUI.rand3_5.setPreferredSize(new Dimension(iconPlayer5.getIconWidth(),
		                                        iconPlayer5.getIconHeight()));
		     boardPlayer6.setBounds(0, 0, iconPlayer6.getIconWidth(),
		                                  iconPlayer6.getIconHeight()); 
		     player.playerUI.rand3_6.setPreferredSize(new Dimension(iconPlayer6.getIconWidth(),
		                                        iconPlayer6.getIconHeight()));
		     boardPlayer7.setBounds(0, 0, iconPlayer7.getIconWidth(),
		                                  iconPlayer7.getIconHeight()); 
		     player.playerUI.rand3_7.setPreferredSize(new Dimension(iconPlayer7.getIconWidth(),
		                                        iconPlayer7.getIconHeight()));
		     boardPlayer8.setBounds(0, 0, iconPlayer8.getIconWidth(),
		                                  iconPlayer8.getIconHeight()); 
		     player.playerUI.rand3_8.setPreferredSize(new Dimension(iconPlayer8.getIconWidth(),
		                                        iconPlayer8.getIconHeight()));
		     boardPlayer9.setBounds(0, 0, iconPlayer9.getIconWidth(),
		                                  iconPlayer9.getIconHeight()); 
		     player.playerUI.rand3_9.setPreferredSize(new Dimension(iconPlayer9.getIconWidth(),
		                                        iconPlayer9.getIconHeight()));
		     boardPlayer10.setBounds(0, 0, iconPlayer10.getIconWidth(),
		                                  iconPlayer10.getIconHeight()); 
		     player.playerUI.rand3_10.setPreferredSize(new Dimension(iconPlayer10.getIconWidth(),
		                                        iconPlayer10.getIconHeight()));													 													 
		     boardPlayer11.setBounds(0, 0, iconPlayer11.getIconWidth(),
		                                  iconPlayer11.getIconHeight()); 
		     player.playerUI.rand3_11.setPreferredSize(new Dimension(iconPlayer11.getIconWidth(),
		                                        iconPlayer11.getIconHeight()));
		     boardPlayer12.setBounds(0, 0, iconPlayer12.getIconWidth(),
		                                  iconPlayer12.getIconHeight()); 
		     player.playerUI.rand3_12.setPreferredSize(new Dimension(iconPlayer12.getIconWidth(),
		                                        iconPlayer12.getIconHeight()));
		     boardPlayer13.setBounds(0, 0, iconPlayer13.getIconWidth(),
		                                  iconPlayer13.getIconHeight()); 
		     player.playerUI.rand3_13.setPreferredSize(new Dimension(iconPlayer13.getIconWidth(),
		                                        iconPlayer13.getIconHeight()));
		     boardPlayer14.setBounds(0, 0, iconPlayer14.getIconWidth(),
		                                  iconPlayer14.getIconHeight()); 
		     player.playerUI.rand3_14.setPreferredSize(new Dimension(iconPlayer14.getIconWidth(),
		                                        iconPlayer14.getIconHeight()));
		     boardPlayer15.setBounds(0, 0, iconPlayer15.getIconWidth(),
		                                  iconPlayer15.getIconHeight()); 
		     player.playerUI.rand3_15.setPreferredSize(new Dimension(iconPlayer15.getIconWidth(),
		                                        iconPlayer15.getIconHeight()));	
		     boardPlayer16.setBounds(0, 0, iconPlayer16.getIconWidth(),
		                                  iconPlayer16.getIconHeight()); 
		     player.playerUI.rand3_16.setPreferredSize(new Dimension(iconPlayer16.getIconWidth(),
		                                        iconPlayer16.getIconHeight()));													 												 													 
		
			  player.playerUI.mypanel1.add(player.playerUI.rand3_1);
		     player.playerUI.mypanel1.add(player.playerUI.rand3_2);
		     player.playerUI.mypanel1.add(player.playerUI.rand3_3);
		     player.playerUI.mypanel1.add(player.playerUI.rand3_4);
		     player.playerUI.mypanel1.add(player.playerUI.rand3_5);
		     player.playerUI.mypanel1.add(player.playerUI.rand3_6);
		     player.playerUI.mypanel1.add(player.playerUI.rand3_7);
		     player.playerUI.mypanel1.add(player.playerUI.rand3_8);
		     player.playerUI.mypanel1.add(player.playerUI.rand3_9);
		     player.playerUI.mypanel1.add(player.playerUI.rand3_10);
		     player.playerUI.mypanel1.add(player.playerUI.rand3_11);
		     player.playerUI.mypanel1.add(player.playerUI.rand3_12);
		     player.playerUI.mypanel1.add(player.playerUI.rand3_13);
		     player.playerUI.mypanel1.add(player.playerUI.rand3_14);
		     player.playerUI.mypanel1.add(player.playerUI.rand3_15);
		     player.playerUI.mypanel1.add(player.playerUI.rand3_16);
		  
		  }
		  else if(tile == 2)
		  {
  	        iconPlayer1 =  (bp[0][8].getIcon());
	        iconPlayer2 =  (bp[1][8].getIcon());
 	        iconPlayer3 =  (bp[2][8].getIcon());
	        iconPlayer4 =  (bp[3][8].getIcon());
	        iconPlayer5 =  (bp[0][9].getIcon());
	        iconPlayer6 =  (bp[1][9].getIcon());
	        iconPlayer7 =  (bp[2][9].getIcon());
	        iconPlayer8 =  (bp[3][9].getIcon());
	        iconPlayer9 =  (bp[0][10].getIcon());
	        iconPlayer10 =  (bp[1][10].getIcon());
	        iconPlayer11 =  (bp[2][10].getIcon());
	        iconPlayer12 =  (bp[3][10].getIcon());
	        iconPlayer13 =  (bp[0][11].getIcon());
	        iconPlayer14 =  (bp[1][11].getIcon());
	        iconPlayer15 =  (bp[2][11].getIcon());
	        iconPlayer16 =  (bp[3][11].getIcon());

	
		//add components to board
			  GameJLabel boardPlayer1 = new GameJLabel(iconPlayer1);
			  GameJLabel boardPlayer2 = new GameJLabel(iconPlayer2);
			  GameJLabel boardPlayer3 = new GameJLabel(iconPlayer3);
			  GameJLabel boardPlayer4 = new GameJLabel(iconPlayer4);
			  GameJLabel boardPlayer5 = new GameJLabel(iconPlayer5);
			  GameJLabel boardPlayer6 = new GameJLabel(iconPlayer6);
			  GameJLabel boardPlayer7 = new GameJLabel(iconPlayer7);
			  GameJLabel boardPlayer8 = new GameJLabel(iconPlayer8);
			  GameJLabel boardPlayer9 = new GameJLabel(iconPlayer9);
			  GameJLabel boardPlayer10 = new GameJLabel(iconPlayer10);
			  GameJLabel boardPlayer11 = new GameJLabel(iconPlayer11);
			  GameJLabel boardPlayer12 = new GameJLabel(iconPlayer12);
			  GameJLabel boardPlayer13 = new GameJLabel(iconPlayer13);
			  GameJLabel boardPlayer14 = new GameJLabel(iconPlayer14);
			  GameJLabel boardPlayer15 = new GameJLabel(iconPlayer15);
			  GameJLabel boardPlayer16 = new GameJLabel(iconPlayer16);
		
			  player.playerUI.rand3_1.add(boardPlayer1, new Integer(0));
		     player.playerUI.rand3_2.add(boardPlayer2, new Integer(0));
		     player.playerUI.rand3_3.add(boardPlayer3, new Integer(0));
		     player.playerUI.rand3_4.add(boardPlayer4, new Integer(0));
		     player.playerUI.rand3_5.add(boardPlayer5, new Integer(0));
		     player.playerUI.rand3_6.add(boardPlayer6, new Integer(0));
		     player.playerUI.rand3_7.add(boardPlayer7, new Integer(0));
		     player.playerUI.rand3_8.add(boardPlayer8, new Integer(0));
		     player.playerUI.rand3_9.add(boardPlayer9, new Integer(0));
		     player.playerUI.rand3_10.add(boardPlayer10, new Integer(0));
		     player.playerUI.rand3_11.add(boardPlayer11, new Integer(0));
		     player.playerUI.rand3_12.add(boardPlayer12, new Integer(0));
		     player.playerUI.rand3_13.add(boardPlayer13, new Integer(0));
		     player.playerUI.rand3_14.add(boardPlayer14, new Integer(0));
		     player.playerUI.rand3_15.add(boardPlayer15, new Integer(0));
		     player.playerUI.rand3_16.add(boardPlayer16, new Integer(0));
		
		     boardPlayer1.setBounds(0, 0, iconPlayer1.getIconWidth(),
		                                  iconPlayer1.getIconHeight()); 
		     player.playerUI.rand3_1.setPreferredSize(new Dimension(iconPlayer1.getIconWidth(),
		                                        iconPlayer1.getIconHeight()));
		     boardPlayer2.setBounds(0, 0, iconPlayer2.getIconWidth(),
		                                  iconPlayer2.getIconHeight()); 
		     player.playerUI.rand3_2.setPreferredSize(new Dimension(iconPlayer2.getIconWidth(),
		                                        iconPlayer2.getIconHeight()));
		     boardPlayer3.setBounds(0, 0, iconPlayer3.getIconWidth(),
		                                  iconPlayer3.getIconHeight()); 
		     player.playerUI.rand3_3.setPreferredSize(new Dimension(iconPlayer3.getIconWidth(),
		                                        iconPlayer3.getIconHeight()));
		     boardPlayer4.setBounds(0, 0, iconPlayer4.getIconWidth(),
		                                  iconPlayer4.getIconHeight()); 
		     player.playerUI.rand3_4.setPreferredSize(new Dimension(iconPlayer4.getIconWidth(),
		                                        iconPlayer4.getIconHeight()));
		     boardPlayer5.setBounds(0, 0, iconPlayer5.getIconWidth(),
		                                  iconPlayer5.getIconHeight()); 
		     player.playerUI.rand3_5.setPreferredSize(new Dimension(iconPlayer5.getIconWidth(),
		                                        iconPlayer5.getIconHeight()));
		     boardPlayer6.setBounds(0, 0, iconPlayer6.getIconWidth(),
		                                  iconPlayer6.getIconHeight()); 
		     player.playerUI.rand3_6.setPreferredSize(new Dimension(iconPlayer6.getIconWidth(),
		                                        iconPlayer6.getIconHeight()));
		     boardPlayer7.setBounds(0, 0, iconPlayer7.getIconWidth(),
		                                  iconPlayer7.getIconHeight()); 
		     player.playerUI.rand3_7.setPreferredSize(new Dimension(iconPlayer7.getIconWidth(),
		                                        iconPlayer7.getIconHeight()));
		     boardPlayer8.setBounds(0, 0, iconPlayer8.getIconWidth(),
		                                  iconPlayer8.getIconHeight()); 
		     player.playerUI.rand3_8.setPreferredSize(new Dimension(iconPlayer8.getIconWidth(),
		                                        iconPlayer8.getIconHeight()));
		     boardPlayer9.setBounds(0, 0, iconPlayer9.getIconWidth(),
		                                  iconPlayer9.getIconHeight()); 
		     player.playerUI.rand3_9.setPreferredSize(new Dimension(iconPlayer9.getIconWidth(),
		                                        iconPlayer9.getIconHeight()));
		     boardPlayer10.setBounds(0, 0, iconPlayer10.getIconWidth(),
		                                  iconPlayer10.getIconHeight()); 
		     player.playerUI.rand3_10.setPreferredSize(new Dimension(iconPlayer10.getIconWidth(),
		                                        iconPlayer10.getIconHeight()));													 													 
		     boardPlayer11.setBounds(0, 0, iconPlayer11.getIconWidth(),
		                                  iconPlayer11.getIconHeight()); 
		     player.playerUI.rand3_11.setPreferredSize(new Dimension(iconPlayer11.getIconWidth(),
		                                        iconPlayer11.getIconHeight()));
		     boardPlayer12.setBounds(0, 0, iconPlayer12.getIconWidth(),
		                                  iconPlayer12.getIconHeight()); 
		     player.playerUI.rand3_12.setPreferredSize(new Dimension(iconPlayer12.getIconWidth(),
		                                        iconPlayer12.getIconHeight()));
		     boardPlayer13.setBounds(0, 0, iconPlayer13.getIconWidth(),
		                                  iconPlayer13.getIconHeight()); 
		     player.playerUI.rand3_13.setPreferredSize(new Dimension(iconPlayer13.getIconWidth(),
		                                        iconPlayer13.getIconHeight()));
		     boardPlayer14.setBounds(0, 0, iconPlayer14.getIconWidth(),
		                                  iconPlayer14.getIconHeight()); 
		     player.playerUI.rand3_14.setPreferredSize(new Dimension(iconPlayer14.getIconWidth(),
		                                        iconPlayer14.getIconHeight()));
		     boardPlayer15.setBounds(0, 0, iconPlayer15.getIconWidth(),
		                                  iconPlayer15.getIconHeight()); 
		     player.playerUI.rand3_15.setPreferredSize(new Dimension(iconPlayer15.getIconWidth(),
		                                        iconPlayer15.getIconHeight()));	
		     boardPlayer16.setBounds(0, 0, iconPlayer16.getIconWidth(),
		                                  iconPlayer16.getIconHeight()); 
		     player.playerUI.rand3_16.setPreferredSize(new Dimension(iconPlayer16.getIconWidth(),
		                                        iconPlayer16.getIconHeight()));													 												 													 
		
			  player.playerUI.mypanel2.add(player.playerUI.rand3_1);
		     player.playerUI.mypanel2.add(player.playerUI.rand3_2);
		     player.playerUI.mypanel2.add(player.playerUI.rand3_3);
		     player.playerUI.mypanel2.add(player.playerUI.rand3_4);
		     player.playerUI.mypanel2.add(player.playerUI.rand3_5);
		     player.playerUI.mypanel2.add(player.playerUI.rand3_6);
		     player.playerUI.mypanel2.add(player.playerUI.rand3_7);
		     player.playerUI.mypanel2.add(player.playerUI.rand3_8);
		     player.playerUI.mypanel2.add(player.playerUI.rand3_9);
		     player.playerUI.mypanel2.add(player.playerUI.rand3_10);
		     player.playerUI.mypanel2.add(player.playerUI.rand3_11);
		     player.playerUI.mypanel2.add(player.playerUI.rand3_12);
		     player.playerUI.mypanel2.add(player.playerUI.rand3_13);
		     player.playerUI.mypanel2.add(player.playerUI.rand3_14);
		     player.playerUI.mypanel2.add(player.playerUI.rand3_15);
		     player.playerUI.mypanel2.add(player.playerUI.rand3_16);
		  
		  }
		  else if(tile == 5)
		  {
  	        iconPlayer1 =  (bp[4][4].getIcon());
	        iconPlayer2 =  (bp[5][4].getIcon());
 	        iconPlayer3 =  (bp[6][4].getIcon());
	        iconPlayer4 =  (bp[7][4].getIcon());
	        iconPlayer5 =  (bp[4][5].getIcon());
	        iconPlayer6 =  (bp[5][5].getIcon());
	        iconPlayer7 =  (bp[6][5].getIcon());
	        iconPlayer8 =  (bp[7][5].getIcon());
	        iconPlayer9 =  (bp[4][6].getIcon());
	        iconPlayer10 =  (bp[5][6].getIcon());
	        iconPlayer11 =  (bp[6][6].getIcon());
	        iconPlayer12 =  (bp[7][6].getIcon());
	        iconPlayer13 =  (bp[4][7].getIcon());
	        iconPlayer14 =  (bp[5][7].getIcon());
	        iconPlayer15 =  (bp[6][7].getIcon());
	        iconPlayer16 =  (bp[7][7].getIcon());

	
		//add components to board
			  GameJLabel boardPlayer1 = new GameJLabel(iconPlayer1);
			  GameJLabel boardPlayer2 = new GameJLabel(iconPlayer2);
			  GameJLabel boardPlayer3 = new GameJLabel(iconPlayer3);
			  GameJLabel boardPlayer4 = new GameJLabel(iconPlayer4);
			  GameJLabel boardPlayer5 = new GameJLabel(iconPlayer5);
			  GameJLabel boardPlayer6 = new GameJLabel(iconPlayer6);
			  GameJLabel boardPlayer7 = new GameJLabel(iconPlayer7);
			  GameJLabel boardPlayer8 = new GameJLabel(iconPlayer8);
			  GameJLabel boardPlayer9 = new GameJLabel(iconPlayer9);
			  GameJLabel boardPlayer10 = new GameJLabel(iconPlayer10);
			  GameJLabel boardPlayer11 = new GameJLabel(iconPlayer11);
			  GameJLabel boardPlayer12 = new GameJLabel(iconPlayer12);
			  GameJLabel boardPlayer13 = new GameJLabel(iconPlayer13);
			  GameJLabel boardPlayer14 = new GameJLabel(iconPlayer14);
			  GameJLabel boardPlayer15 = new GameJLabel(iconPlayer15);
			  GameJLabel boardPlayer16 = new GameJLabel(iconPlayer16);
		
			  player.playerUI.rand3_1.add(boardPlayer1, new Integer(0));
		     player.playerUI.rand3_2.add(boardPlayer2, new Integer(0));
		     player.playerUI.rand3_3.add(boardPlayer3, new Integer(0));
		     player.playerUI.rand3_4.add(boardPlayer4, new Integer(0));
		     player.playerUI.rand3_5.add(boardPlayer5, new Integer(0));
		     player.playerUI.rand3_6.add(boardPlayer6, new Integer(0));
		     player.playerUI.rand3_7.add(boardPlayer7, new Integer(0));
		     player.playerUI.rand3_8.add(boardPlayer8, new Integer(0));
		     player.playerUI.rand3_9.add(boardPlayer9, new Integer(0));
		     player.playerUI.rand3_10.add(boardPlayer10, new Integer(0));
		     player.playerUI.rand3_11.add(boardPlayer11, new Integer(0));
		     player.playerUI.rand3_12.add(boardPlayer12, new Integer(0));
		     player.playerUI.rand3_13.add(boardPlayer13, new Integer(0));
		     player.playerUI.rand3_14.add(boardPlayer14, new Integer(0));
		     player.playerUI.rand3_15.add(boardPlayer15, new Integer(0));
		     player.playerUI.rand3_16.add(boardPlayer16, new Integer(0));
		
		     boardPlayer1.setBounds(0, 0, iconPlayer1.getIconWidth(),
		                                  iconPlayer1.getIconHeight()); 
		     player.playerUI.rand3_1.setPreferredSize(new Dimension(iconPlayer1.getIconWidth(),
		                                        iconPlayer1.getIconHeight()));
		     boardPlayer2.setBounds(0, 0, iconPlayer2.getIconWidth(),
		                                  iconPlayer2.getIconHeight()); 
		     player.playerUI.rand3_2.setPreferredSize(new Dimension(iconPlayer2.getIconWidth(),
		                                        iconPlayer2.getIconHeight()));
		     boardPlayer3.setBounds(0, 0, iconPlayer3.getIconWidth(),
		                                  iconPlayer3.getIconHeight()); 
		     player.playerUI.rand3_3.setPreferredSize(new Dimension(iconPlayer3.getIconWidth(),
		                                        iconPlayer3.getIconHeight()));
		     boardPlayer4.setBounds(0, 0, iconPlayer4.getIconWidth(),
		                                  iconPlayer4.getIconHeight()); 
		     player.playerUI.rand3_4.setPreferredSize(new Dimension(iconPlayer4.getIconWidth(),
		                                        iconPlayer4.getIconHeight()));
		     boardPlayer5.setBounds(0, 0, iconPlayer5.getIconWidth(),
		                                  iconPlayer5.getIconHeight()); 
		     player.playerUI.rand3_5.setPreferredSize(new Dimension(iconPlayer5.getIconWidth(),
		                                        iconPlayer5.getIconHeight()));
		     boardPlayer6.setBounds(0, 0, iconPlayer6.getIconWidth(),
		                                  iconPlayer6.getIconHeight()); 
		     player.playerUI.rand3_6.setPreferredSize(new Dimension(iconPlayer6.getIconWidth(),
		                                        iconPlayer6.getIconHeight()));
		     boardPlayer7.setBounds(0, 0, iconPlayer7.getIconWidth(),
		                                  iconPlayer7.getIconHeight()); 
		     player.playerUI.rand3_7.setPreferredSize(new Dimension(iconPlayer7.getIconWidth(),
		                                        iconPlayer7.getIconHeight()));
		     boardPlayer8.setBounds(0, 0, iconPlayer8.getIconWidth(),
		                                  iconPlayer8.getIconHeight()); 
		     player.playerUI.rand3_8.setPreferredSize(new Dimension(iconPlayer8.getIconWidth(),
		                                        iconPlayer8.getIconHeight()));
		     boardPlayer9.setBounds(0, 0, iconPlayer9.getIconWidth(),
		                                  iconPlayer9.getIconHeight()); 
		     player.playerUI.rand3_9.setPreferredSize(new Dimension(iconPlayer9.getIconWidth(),
		                                        iconPlayer9.getIconHeight()));
		     boardPlayer10.setBounds(0, 0, iconPlayer10.getIconWidth(),
		                                  iconPlayer10.getIconHeight()); 
		     player.playerUI.rand3_10.setPreferredSize(new Dimension(iconPlayer10.getIconWidth(),
		                                        iconPlayer10.getIconHeight()));													 													 
		     boardPlayer11.setBounds(0, 0, iconPlayer11.getIconWidth(),
		                                  iconPlayer11.getIconHeight()); 
		     player.playerUI.rand3_11.setPreferredSize(new Dimension(iconPlayer11.getIconWidth(),
		                                        iconPlayer11.getIconHeight()));
		     boardPlayer12.setBounds(0, 0, iconPlayer12.getIconWidth(),
		                                  iconPlayer12.getIconHeight()); 
		     player.playerUI.rand3_12.setPreferredSize(new Dimension(iconPlayer12.getIconWidth(),
		                                        iconPlayer12.getIconHeight()));
		     boardPlayer13.setBounds(0, 0, iconPlayer13.getIconWidth(),
		                                  iconPlayer13.getIconHeight()); 
		     player.playerUI.rand3_13.setPreferredSize(new Dimension(iconPlayer13.getIconWidth(),
		                                        iconPlayer13.getIconHeight()));
		     boardPlayer14.setBounds(0, 0, iconPlayer14.getIconWidth(),
		                                  iconPlayer14.getIconHeight()); 
		     player.playerUI.rand3_14.setPreferredSize(new Dimension(iconPlayer14.getIconWidth(),
		                                        iconPlayer14.getIconHeight()));
		     boardPlayer15.setBounds(0, 0, iconPlayer15.getIconWidth(),
		                                  iconPlayer15.getIconHeight()); 
		     player.playerUI.rand3_15.setPreferredSize(new Dimension(iconPlayer15.getIconWidth(),
		                                        iconPlayer15.getIconHeight()));	
		     boardPlayer16.setBounds(0, 0, iconPlayer16.getIconWidth(),
		                                  iconPlayer16.getIconHeight()); 
		     player.playerUI.rand3_16.setPreferredSize(new Dimension(iconPlayer16.getIconWidth(),
		                                        iconPlayer16.getIconHeight()));													 												 													 
		
			  player.playerUI.mypanel5.add(player.playerUI.rand3_1);
		     player.playerUI.mypanel5.add(player.playerUI.rand3_2);
		     player.playerUI.mypanel5.add(player.playerUI.rand3_3);
		     player.playerUI.mypanel5.add(player.playerUI.rand3_4);
		     player.playerUI.mypanel5.add(player.playerUI.rand3_5);
		     player.playerUI.mypanel5.add(player.playerUI.rand3_6);
		     player.playerUI.mypanel5.add(player.playerUI.rand3_7);
		     player.playerUI.mypanel5.add(player.playerUI.rand3_8);
		     player.playerUI.mypanel5.add(player.playerUI.rand3_9);
		     player.playerUI.mypanel5.add(player.playerUI.rand3_10);
		     player.playerUI.mypanel5.add(player.playerUI.rand3_11);
		     player.playerUI.mypanel5.add(player.playerUI.rand3_12);
		     player.playerUI.mypanel5.add(player.playerUI.rand3_13);
		     player.playerUI.mypanel5.add(player.playerUI.rand3_14);
		     player.playerUI.mypanel5.add(player.playerUI.rand3_15);
		     player.playerUI.mypanel5.add(player.playerUI.rand3_16);
		  
		  }
		  else if(tile == 6)
		  {
  	        iconPlayer1 =  (bp[4][8].getIcon());
	        iconPlayer2 =  (bp[5][8].getIcon());
 	        iconPlayer3 =  (bp[6][8].getIcon());
	        iconPlayer4 =  (bp[7][8].getIcon());
	        iconPlayer5 =  (bp[4][9].getIcon());
	        iconPlayer6 =  (bp[5][9].getIcon());
	        iconPlayer7 =  (bp[6][9].getIcon());
	        iconPlayer8 =  (bp[7][9].getIcon());
	        iconPlayer9 =  (bp[4][10].getIcon());
	        iconPlayer10 =  (bp[5][10].getIcon());
	        iconPlayer11 =  (bp[6][10].getIcon());
	        iconPlayer12 =  (bp[7][10].getIcon());
	        iconPlayer13 =  (bp[4][11].getIcon());
	        iconPlayer14 =  (bp[5][11].getIcon());
	        iconPlayer15 =  (bp[6][11].getIcon());
	        iconPlayer16 =  (bp[7][11].getIcon());

	
		//add components to board
			  GameJLabel boardPlayer1 = new GameJLabel(iconPlayer1);
			  GameJLabel boardPlayer2 = new GameJLabel(iconPlayer2);
			  GameJLabel boardPlayer3 = new GameJLabel(iconPlayer3);
			  GameJLabel boardPlayer4 = new GameJLabel(iconPlayer4);
			  GameJLabel boardPlayer5 = new GameJLabel(iconPlayer5);
			  GameJLabel boardPlayer6 = new GameJLabel(iconPlayer6);
			  GameJLabel boardPlayer7 = new GameJLabel(iconPlayer7);
			  GameJLabel boardPlayer8 = new GameJLabel(iconPlayer8);
			  GameJLabel boardPlayer9 = new GameJLabel(iconPlayer9);
			  GameJLabel boardPlayer10 = new GameJLabel(iconPlayer10);
			  GameJLabel boardPlayer11 = new GameJLabel(iconPlayer11);
			  GameJLabel boardPlayer12 = new GameJLabel(iconPlayer12);
			  GameJLabel boardPlayer13 = new GameJLabel(iconPlayer13);
			  GameJLabel boardPlayer14 = new GameJLabel(iconPlayer14);
			  GameJLabel boardPlayer15 = new GameJLabel(iconPlayer15);
			  GameJLabel boardPlayer16 = new GameJLabel(iconPlayer16);
		
			  player.playerUI.rand3_1.add(boardPlayer1, new Integer(0));
		     player.playerUI.rand3_2.add(boardPlayer2, new Integer(0));
		     player.playerUI.rand3_3.add(boardPlayer3, new Integer(0));
		     player.playerUI.rand3_4.add(boardPlayer4, new Integer(0));
		     player.playerUI.rand3_5.add(boardPlayer5, new Integer(0));
		     player.playerUI.rand3_6.add(boardPlayer6, new Integer(0));
		     player.playerUI.rand3_7.add(boardPlayer7, new Integer(0));
		     player.playerUI.rand3_8.add(boardPlayer8, new Integer(0));
		     player.playerUI.rand3_9.add(boardPlayer9, new Integer(0));
		     player.playerUI.rand3_10.add(boardPlayer10, new Integer(0));
		     player.playerUI.rand3_11.add(boardPlayer11, new Integer(0));
		     player.playerUI.rand3_12.add(boardPlayer12, new Integer(0));
		     player.playerUI.rand3_13.add(boardPlayer13, new Integer(0));
		     player.playerUI.rand3_14.add(boardPlayer14, new Integer(0));
		     player.playerUI.rand3_15.add(boardPlayer15, new Integer(0));
		     player.playerUI.rand3_16.add(boardPlayer16, new Integer(0));
		
		     boardPlayer1.setBounds(0, 0, iconPlayer1.getIconWidth(),
		                                  iconPlayer1.getIconHeight()); 
		     player.playerUI.rand3_1.setPreferredSize(new Dimension(iconPlayer1.getIconWidth(),
		                                        iconPlayer1.getIconHeight()));
		     boardPlayer2.setBounds(0, 0, iconPlayer2.getIconWidth(),
		                                  iconPlayer2.getIconHeight()); 
		     player.playerUI.rand3_2.setPreferredSize(new Dimension(iconPlayer2.getIconWidth(),
		                                        iconPlayer2.getIconHeight()));
		     boardPlayer3.setBounds(0, 0, iconPlayer3.getIconWidth(),
		                                  iconPlayer3.getIconHeight()); 
		     player.playerUI.rand3_3.setPreferredSize(new Dimension(iconPlayer3.getIconWidth(),
		                                        iconPlayer3.getIconHeight()));
		     boardPlayer4.setBounds(0, 0, iconPlayer4.getIconWidth(),
		                                  iconPlayer4.getIconHeight()); 
		     player.playerUI.rand3_4.setPreferredSize(new Dimension(iconPlayer4.getIconWidth(),
		                                        iconPlayer4.getIconHeight()));
		     boardPlayer5.setBounds(0, 0, iconPlayer5.getIconWidth(),
		                                  iconPlayer5.getIconHeight()); 
		     player.playerUI.rand3_5.setPreferredSize(new Dimension(iconPlayer5.getIconWidth(),
		                                        iconPlayer5.getIconHeight()));
		     boardPlayer6.setBounds(0, 0, iconPlayer6.getIconWidth(),
		                                  iconPlayer6.getIconHeight()); 
		     player.playerUI.rand3_6.setPreferredSize(new Dimension(iconPlayer6.getIconWidth(),
		                                        iconPlayer6.getIconHeight()));
		     boardPlayer7.setBounds(0, 0, iconPlayer7.getIconWidth(),
		                                  iconPlayer7.getIconHeight()); 
		     player.playerUI.rand3_7.setPreferredSize(new Dimension(iconPlayer7.getIconWidth(),
		                                        iconPlayer7.getIconHeight()));
		     boardPlayer8.setBounds(0, 0, iconPlayer8.getIconWidth(),
		                                  iconPlayer8.getIconHeight()); 
		     player.playerUI.rand3_8.setPreferredSize(new Dimension(iconPlayer8.getIconWidth(),
		                                        iconPlayer8.getIconHeight()));
		     boardPlayer9.setBounds(0, 0, iconPlayer9.getIconWidth(),
		                                  iconPlayer9.getIconHeight()); 
		     player.playerUI.rand3_9.setPreferredSize(new Dimension(iconPlayer9.getIconWidth(),
		                                        iconPlayer9.getIconHeight()));
		     boardPlayer10.setBounds(0, 0, iconPlayer10.getIconWidth(),
		                                  iconPlayer10.getIconHeight()); 
		     player.playerUI.rand3_10.setPreferredSize(new Dimension(iconPlayer10.getIconWidth(),
		                                        iconPlayer10.getIconHeight()));													 													 
		     boardPlayer11.setBounds(0, 0, iconPlayer11.getIconWidth(),
		                                  iconPlayer11.getIconHeight()); 
		     player.playerUI.rand3_11.setPreferredSize(new Dimension(iconPlayer11.getIconWidth(),
		                                        iconPlayer11.getIconHeight()));
		     boardPlayer12.setBounds(0, 0, iconPlayer12.getIconWidth(),
		                                  iconPlayer12.getIconHeight()); 
		     player.playerUI.rand3_12.setPreferredSize(new Dimension(iconPlayer12.getIconWidth(),
		                                        iconPlayer12.getIconHeight()));
		     boardPlayer13.setBounds(0, 0, iconPlayer13.getIconWidth(),
		                                  iconPlayer13.getIconHeight()); 
		     player.playerUI.rand3_13.setPreferredSize(new Dimension(iconPlayer13.getIconWidth(),
		                                        iconPlayer13.getIconHeight()));
		     boardPlayer14.setBounds(0, 0, iconPlayer14.getIconWidth(),
		                                  iconPlayer14.getIconHeight()); 
		     player.playerUI.rand3_14.setPreferredSize(new Dimension(iconPlayer14.getIconWidth(),
		                                        iconPlayer14.getIconHeight()));
		     boardPlayer15.setBounds(0, 0, iconPlayer15.getIconWidth(),
		                                  iconPlayer15.getIconHeight()); 
		     player.playerUI.rand3_15.setPreferredSize(new Dimension(iconPlayer15.getIconWidth(),
		                                        iconPlayer15.getIconHeight()));	
		     boardPlayer16.setBounds(0, 0, iconPlayer16.getIconWidth(),
		                                  iconPlayer16.getIconHeight()); 
		     player.playerUI.rand3_16.setPreferredSize(new Dimension(iconPlayer16.getIconWidth(),
		                                        iconPlayer16.getIconHeight()));													 												 													 
		
			  player.playerUI.mypanel6.add(player.playerUI.rand3_1);
		     player.playerUI.mypanel6.add(player.playerUI.rand3_2);
		     player.playerUI.mypanel6.add(player.playerUI.rand3_3);
		     player.playerUI.mypanel6.add(player.playerUI.rand3_4);
		     player.playerUI.mypanel6.add(player.playerUI.rand3_5);
		     player.playerUI.mypanel6.add(player.playerUI.rand3_6);
		     player.playerUI.mypanel6.add(player.playerUI.rand3_7);
		     player.playerUI.mypanel6.add(player.playerUI.rand3_8);
		     player.playerUI.mypanel6.add(player.playerUI.rand3_9);
		     player.playerUI.mypanel6.add(player.playerUI.rand3_10);
		     player.playerUI.mypanel6.add(player.playerUI.rand3_11);
		     player.playerUI.mypanel6.add(player.playerUI.rand3_12);
		     player.playerUI.mypanel6.add(player.playerUI.rand3_13);
		     player.playerUI.mypanel6.add(player.playerUI.rand3_14);
		     player.playerUI.mypanel6.add(player.playerUI.rand3_15);
		     player.playerUI.mypanel6.add(player.playerUI.rand3_16);
		  
		  }
		  else if(tile == 7)
		  {
	       iconPlayer1 =  bp[4][12].getIcon();
	       iconPlayer2 =  bp[5][12].getIcon();
	       iconPlayer3 =  bp[6][12].getIcon();
	       iconPlayer4 =  bp[7][12].getIcon();
	       iconPlayer5 =  bp[4][13].getIcon();
	       iconPlayer6 =  bp[5][13].getIcon();
	       iconPlayer7 =  bp[6][13].getIcon();
	       iconPlayer8 =  bp[7][13].getIcon();
	       iconPlayer9 =  bp[4][14].getIcon();
	       iconPlayer10 =  bp[5][14].getIcon();
	       iconPlayer11 =  bp[6][14].getIcon();
	       iconPlayer12 =  bp[7][14].getIcon();
	       iconPlayer13 =  bp[4][15].getIcon();
	       iconPlayer14 =  bp[5][15].getIcon();
	       iconPlayer15 =  bp[6][15].getIcon();
	       iconPlayer16 =  bp[7][15].getIcon();


		//add components to board
			  GameJLabel boardPlayer1 = new GameJLabel(iconPlayer1);
			  GameJLabel boardPlayer2 = new GameJLabel(iconPlayer2);
			  GameJLabel boardPlayer3 = new GameJLabel(iconPlayer3);
			  GameJLabel boardPlayer4 = new GameJLabel(iconPlayer4);
			  GameJLabel boardPlayer5 = new GameJLabel(iconPlayer5);
			  GameJLabel boardPlayer6 = new GameJLabel(iconPlayer6);
			  GameJLabel boardPlayer7 = new GameJLabel(iconPlayer7);
			  GameJLabel boardPlayer8 = new GameJLabel(iconPlayer8);
			  GameJLabel boardPlayer9 = new GameJLabel(iconPlayer9);
			  GameJLabel boardPlayer10 = new GameJLabel(iconPlayer10);
			  GameJLabel boardPlayer11 = new GameJLabel(iconPlayer11);
			  GameJLabel boardPlayer12 = new GameJLabel(iconPlayer12);
			  GameJLabel boardPlayer13 = new GameJLabel(iconPlayer13);
			  GameJLabel boardPlayer14 = new GameJLabel(iconPlayer14);
			  GameJLabel boardPlayer15 = new GameJLabel(iconPlayer15);
			  GameJLabel boardPlayer16 = new GameJLabel(iconPlayer16);
		
			  player.playerUI.rand3_1.add(boardPlayer1, new Integer(0));
		     player.playerUI.rand3_2.add(boardPlayer2, new Integer(0));
		     player.playerUI.rand3_3.add(boardPlayer3, new Integer(0));
		     player.playerUI.rand3_4.add(boardPlayer4, new Integer(0));
		     player.playerUI.rand3_5.add(boardPlayer5, new Integer(0));
		     player.playerUI.rand3_6.add(boardPlayer6, new Integer(0));
		     player.playerUI.rand3_7.add(boardPlayer7, new Integer(0));
		     player.playerUI.rand3_8.add(boardPlayer8, new Integer(0));
		     player.playerUI.rand3_9.add(boardPlayer9, new Integer(0));
		     player.playerUI.rand3_10.add(boardPlayer10, new Integer(0));
		     player.playerUI.rand3_11.add(boardPlayer11, new Integer(0));
		     player.playerUI.rand3_12.add(boardPlayer12, new Integer(0));
		     player.playerUI.rand3_13.add(boardPlayer13, new Integer(0));
		     player.playerUI.rand3_14.add(boardPlayer14, new Integer(0));
		     player.playerUI.rand3_15.add(boardPlayer15, new Integer(0));
		     player.playerUI.rand3_16.add(boardPlayer16, new Integer(0));
		
		     boardPlayer1.setBounds(0, 0, iconPlayer1.getIconWidth(),
		                                  iconPlayer1.getIconHeight()); 
		     player.playerUI.rand3_1.setPreferredSize(new Dimension(iconPlayer1.getIconWidth(),
		                                        iconPlayer1.getIconHeight()));
		     boardPlayer2.setBounds(0, 0, iconPlayer2.getIconWidth(),
		                                  iconPlayer2.getIconHeight()); 
		     player.playerUI.rand3_2.setPreferredSize(new Dimension(iconPlayer2.getIconWidth(),
		                                        iconPlayer2.getIconHeight()));
		     boardPlayer3.setBounds(0, 0, iconPlayer3.getIconWidth(),
		                                  iconPlayer3.getIconHeight()); 
		     player.playerUI.rand3_3.setPreferredSize(new Dimension(iconPlayer3.getIconWidth(),
		                                        iconPlayer3.getIconHeight()));
		     boardPlayer4.setBounds(0, 0, iconPlayer4.getIconWidth(),
		                                  iconPlayer4.getIconHeight()); 
		     player.playerUI.rand3_4.setPreferredSize(new Dimension(iconPlayer4.getIconWidth(),
		                                        iconPlayer4.getIconHeight()));
		     boardPlayer5.setBounds(0, 0, iconPlayer5.getIconWidth(),
		                                  iconPlayer5.getIconHeight()); 
		     player.playerUI.rand3_5.setPreferredSize(new Dimension(iconPlayer5.getIconWidth(),
		                                        iconPlayer5.getIconHeight()));
		     boardPlayer6.setBounds(0, 0, iconPlayer6.getIconWidth(),
		                                  iconPlayer6.getIconHeight()); 
		     player.playerUI.rand3_6.setPreferredSize(new Dimension(iconPlayer6.getIconWidth(),
		                                        iconPlayer6.getIconHeight()));
		     boardPlayer7.setBounds(0, 0, iconPlayer7.getIconWidth(),
		                                  iconPlayer7.getIconHeight()); 
		     player.playerUI.rand3_7.setPreferredSize(new Dimension(iconPlayer7.getIconWidth(),
		                                        iconPlayer7.getIconHeight()));
		     boardPlayer8.setBounds(0, 0, iconPlayer8.getIconWidth(),
		                                  iconPlayer8.getIconHeight()); 
		     player.playerUI.rand3_8.setPreferredSize(new Dimension(iconPlayer8.getIconWidth(),
		                                        iconPlayer8.getIconHeight()));
		     boardPlayer9.setBounds(0, 0, iconPlayer9.getIconWidth(),
		                                  iconPlayer9.getIconHeight()); 
		     player.playerUI.rand3_9.setPreferredSize(new Dimension(iconPlayer9.getIconWidth(),
		                                        iconPlayer9.getIconHeight()));
		     boardPlayer10.setBounds(0, 0, iconPlayer10.getIconWidth(),
		                                  iconPlayer10.getIconHeight()); 
		     player.playerUI.rand3_10.setPreferredSize(new Dimension(iconPlayer10.getIconWidth(),
		                                        iconPlayer10.getIconHeight()));													 													 
		     boardPlayer11.setBounds(0, 0, iconPlayer11.getIconWidth(),
		                                  iconPlayer11.getIconHeight()); 
		     player.playerUI.rand3_11.setPreferredSize(new Dimension(iconPlayer11.getIconWidth(),
		                                        iconPlayer11.getIconHeight()));
		     boardPlayer12.setBounds(0, 0, iconPlayer12.getIconWidth(),
		                                  iconPlayer12.getIconHeight()); 
		     player.playerUI.rand3_12.setPreferredSize(new Dimension(iconPlayer12.getIconWidth(),
		                                        iconPlayer12.getIconHeight()));
		     boardPlayer13.setBounds(0, 0, iconPlayer13.getIconWidth(),
		                                  iconPlayer13.getIconHeight()); 
		     player.playerUI.rand3_13.setPreferredSize(new Dimension(iconPlayer13.getIconWidth(),
		                                        iconPlayer13.getIconHeight()));
		     boardPlayer14.setBounds(0, 0, iconPlayer14.getIconWidth(),
		                                  iconPlayer14.getIconHeight()); 
		     player.playerUI.rand3_14.setPreferredSize(new Dimension(iconPlayer14.getIconWidth(),
		                                        iconPlayer14.getIconHeight()));
		     boardPlayer15.setBounds(0, 0, iconPlayer15.getIconWidth(),
		                                  iconPlayer15.getIconHeight()); 
		     player.playerUI.rand3_15.setPreferredSize(new Dimension(iconPlayer15.getIconWidth(),
		                                        iconPlayer15.getIconHeight()));	
		     boardPlayer16.setBounds(0, 0, iconPlayer16.getIconWidth(),
		                                  iconPlayer16.getIconHeight()); 
		     player.playerUI.rand3_16.setPreferredSize(new Dimension(iconPlayer16.getIconWidth(),
		                                        iconPlayer16.getIconHeight()));													 												 													 
		
			  player.playerUI.mypanel7.add(player.playerUI.rand3_1);
		     player.playerUI.mypanel7.add(player.playerUI.rand3_2);
		     player.playerUI.mypanel7.add(player.playerUI.rand3_3);
		     player.playerUI.mypanel7.add(player.playerUI.rand3_4);
		     player.playerUI.mypanel7.add(player.playerUI.rand3_5);
		     player.playerUI.mypanel7.add(player.playerUI.rand3_6);
		     player.playerUI.mypanel7.add(player.playerUI.rand3_7);
		     player.playerUI.mypanel7.add(player.playerUI.rand3_8);
		     player.playerUI.mypanel7.add(player.playerUI.rand3_9);
		     player.playerUI.mypanel7.add(player.playerUI.rand3_10);
		     player.playerUI.mypanel7.add(player.playerUI.rand3_11);
		     player.playerUI.mypanel7.add(player.playerUI.rand3_12);
		     player.playerUI.mypanel7.add(player.playerUI.rand3_13);
		     player.playerUI.mypanel7.add(player.playerUI.rand3_14);
		     player.playerUI.mypanel7.add(player.playerUI.rand3_15);
		     player.playerUI.mypanel7.add(player.playerUI.rand3_16);
		  }
		}

      private void populateRand4(int tile, Player player)
		{
		  if(tile == 0)
		  {
			  iconPlayer1 =  (bp[0][0].getIcon());
	        iconPlayer2 =  (bp[1][0].getIcon());
 	        iconPlayer3 =  (bp[2][0].getIcon());
	        iconPlayer4 =  (bp[3][0].getIcon());
	        iconPlayer5 =  (bp[0][1].getIcon());
	        iconPlayer6 =  (bp[1][1].getIcon());
	        iconPlayer7 =  (bp[2][1].getIcon());
	        iconPlayer8 =  (bp[3][1].getIcon());
	        iconPlayer9 =  (bp[0][2].getIcon());
	        iconPlayer10 =  (bp[1][2].getIcon());
	        iconPlayer11 =  (bp[2][2].getIcon());
	        iconPlayer12 =  (bp[3][2].getIcon());
	        iconPlayer13 =  (bp[0][3].getIcon());
	        iconPlayer14 =  (bp[1][3].getIcon());
	        iconPlayer15 =  (bp[2][3].getIcon());
	        iconPlayer16 =  (bp[3][3].getIcon());

	
		//add components to board
			  GameJLabel boardPlayer1 = new GameJLabel(iconPlayer1);
			  GameJLabel boardPlayer2 = new GameJLabel(iconPlayer2);
			  GameJLabel boardPlayer3 = new GameJLabel(iconPlayer3);
			  GameJLabel boardPlayer4 = new GameJLabel(iconPlayer4);
			  GameJLabel boardPlayer5 = new GameJLabel(iconPlayer5);
			  GameJLabel boardPlayer6 = new GameJLabel(iconPlayer6);
			  GameJLabel boardPlayer7 = new GameJLabel(iconPlayer7);
			  GameJLabel boardPlayer8 = new GameJLabel(iconPlayer8);
			  GameJLabel boardPlayer9 = new GameJLabel(iconPlayer9);
			  GameJLabel boardPlayer10 = new GameJLabel(iconPlayer10);
			  GameJLabel boardPlayer11 = new GameJLabel(iconPlayer11);
			  GameJLabel boardPlayer12 = new GameJLabel(iconPlayer12);
			  GameJLabel boardPlayer13 = new GameJLabel(iconPlayer13);
			  GameJLabel boardPlayer14 = new GameJLabel(iconPlayer14);
			  GameJLabel boardPlayer15 = new GameJLabel(iconPlayer15);
			  GameJLabel boardPlayer16 = new GameJLabel(iconPlayer16);
		
			  player.playerUI.rand4_1.add(boardPlayer1, new Integer(0));
		     player.playerUI.rand4_2.add(boardPlayer2, new Integer(0));
		     player.playerUI.rand4_3.add(boardPlayer3, new Integer(0));
		     player.playerUI.rand4_4.add(boardPlayer4, new Integer(0));
		     player.playerUI.rand4_5.add(boardPlayer5, new Integer(0));
		     player.playerUI.rand4_6.add(boardPlayer6, new Integer(0));
		     player.playerUI.rand4_7.add(boardPlayer7, new Integer(0));
		     player.playerUI.rand4_8.add(boardPlayer8, new Integer(0));
		     player.playerUI.rand4_9.add(boardPlayer9, new Integer(0));
		     player.playerUI.rand4_10.add(boardPlayer10, new Integer(0));
		     player.playerUI.rand4_11.add(boardPlayer11, new Integer(0));
		     player.playerUI.rand4_12.add(boardPlayer12, new Integer(0));
		     player.playerUI.rand4_13.add(boardPlayer13, new Integer(0));
		     player.playerUI.rand4_14.add(boardPlayer14, new Integer(0));
		     player.playerUI.rand4_15.add(boardPlayer15, new Integer(0));
		     player.playerUI.rand4_16.add(boardPlayer16, new Integer(0));
		
		     boardPlayer1.setBounds(0, 0, iconPlayer1.getIconWidth(),
		                                  iconPlayer1.getIconHeight()); 
		     player.playerUI.rand4_1.setPreferredSize(new Dimension(iconPlayer1.getIconWidth(),
		                                        iconPlayer1.getIconHeight()));
		     boardPlayer2.setBounds(0, 0, iconPlayer2.getIconWidth(),
		                                  iconPlayer2.getIconHeight()); 
		     player.playerUI.rand4_2.setPreferredSize(new Dimension(iconPlayer2.getIconWidth(),
		                                        iconPlayer2.getIconHeight()));
		     boardPlayer3.setBounds(0, 0, iconPlayer3.getIconWidth(),
		                                  iconPlayer3.getIconHeight()); 
		     player.playerUI.rand4_3.setPreferredSize(new Dimension(iconPlayer3.getIconWidth(),
		                                        iconPlayer3.getIconHeight()));
		     boardPlayer4.setBounds(0, 0, iconPlayer4.getIconWidth(),
		                                  iconPlayer4.getIconHeight()); 
		     player.playerUI.rand4_4.setPreferredSize(new Dimension(iconPlayer4.getIconWidth(),
		                                        iconPlayer4.getIconHeight()));
		     boardPlayer5.setBounds(0, 0, iconPlayer5.getIconWidth(),
		                                  iconPlayer5.getIconHeight()); 
		     player.playerUI.rand4_5.setPreferredSize(new Dimension(iconPlayer5.getIconWidth(),
		                                        iconPlayer5.getIconHeight()));
		     boardPlayer6.setBounds(0, 0, iconPlayer6.getIconWidth(),
		                                  iconPlayer6.getIconHeight()); 
		     player.playerUI.rand4_6.setPreferredSize(new Dimension(iconPlayer6.getIconWidth(),
		                                        iconPlayer6.getIconHeight()));
		     boardPlayer7.setBounds(0, 0, iconPlayer7.getIconWidth(),
		                                  iconPlayer7.getIconHeight()); 
		     player.playerUI.rand4_7.setPreferredSize(new Dimension(iconPlayer7.getIconWidth(),
		                                        iconPlayer7.getIconHeight()));
		     boardPlayer8.setBounds(0, 0, iconPlayer8.getIconWidth(),
		                                  iconPlayer8.getIconHeight()); 
		     player.playerUI.rand4_8.setPreferredSize(new Dimension(iconPlayer8.getIconWidth(),
		                                        iconPlayer8.getIconHeight()));
		     boardPlayer9.setBounds(0, 0, iconPlayer9.getIconWidth(),
		                                  iconPlayer9.getIconHeight()); 
		     player.playerUI.rand4_9.setPreferredSize(new Dimension(iconPlayer9.getIconWidth(),
		                                        iconPlayer9.getIconHeight()));
		     boardPlayer10.setBounds(0, 0, iconPlayer10.getIconWidth(),
		                                  iconPlayer10.getIconHeight()); 
		     player.playerUI.rand4_10.setPreferredSize(new Dimension(iconPlayer10.getIconWidth(),
		                                        iconPlayer10.getIconHeight()));													 													 
		     boardPlayer11.setBounds(0, 0, iconPlayer11.getIconWidth(),
		                                  iconPlayer11.getIconHeight()); 
		     player.playerUI.rand4_11.setPreferredSize(new Dimension(iconPlayer11.getIconWidth(),
		                                        iconPlayer11.getIconHeight()));
		     boardPlayer12.setBounds(0, 0, iconPlayer12.getIconWidth(),
		                                  iconPlayer12.getIconHeight()); 
		     player.playerUI.rand4_12.setPreferredSize(new Dimension(iconPlayer12.getIconWidth(),
		                                        iconPlayer12.getIconHeight()));
		     boardPlayer13.setBounds(0, 0, iconPlayer13.getIconWidth(),
		                                  iconPlayer13.getIconHeight()); 
		     player.playerUI.rand4_13.setPreferredSize(new Dimension(iconPlayer13.getIconWidth(),
		                                        iconPlayer13.getIconHeight()));
		     boardPlayer14.setBounds(0, 0, iconPlayer14.getIconWidth(),
		                                  iconPlayer14.getIconHeight()); 
		     player.playerUI.rand4_14.setPreferredSize(new Dimension(iconPlayer14.getIconWidth(),
		                                        iconPlayer14.getIconHeight()));
		     boardPlayer15.setBounds(0, 0, iconPlayer15.getIconWidth(),
		                                  iconPlayer15.getIconHeight()); 
		     player.playerUI.rand4_15.setPreferredSize(new Dimension(iconPlayer15.getIconWidth(),
		                                        iconPlayer15.getIconHeight()));	
		     boardPlayer16.setBounds(0, 0, iconPlayer16.getIconWidth(),
		                                  iconPlayer16.getIconHeight()); 
		     player.playerUI.rand4_16.setPreferredSize(new Dimension(iconPlayer16.getIconWidth(),
		                                        iconPlayer16.getIconHeight()));													 												 													 
		
			  player.playerUI.mypanel0.add(player.playerUI.rand4_1);
		     player.playerUI.mypanel0.add(player.playerUI.rand4_2);
		     player.playerUI.mypanel0.add(player.playerUI.rand4_3);
		     player.playerUI.mypanel0.add(player.playerUI.rand4_4);
		     player.playerUI.mypanel0.add(player.playerUI.rand4_5);
		     player.playerUI.mypanel0.add(player.playerUI.rand4_6);
		     player.playerUI.mypanel0.add(player.playerUI.rand4_7);
		     player.playerUI.mypanel0.add(player.playerUI.rand4_8);
		     player.playerUI.mypanel0.add(player.playerUI.rand4_9);
		     player.playerUI.mypanel0.add(player.playerUI.rand4_10);
		     player.playerUI.mypanel0.add(player.playerUI.rand4_11);
		     player.playerUI.mypanel0.add(player.playerUI.rand4_12);
		     player.playerUI.mypanel0.add(player.playerUI.rand4_13);
		     player.playerUI.mypanel0.add(player.playerUI.rand4_14);
		     player.playerUI.mypanel0.add(player.playerUI.rand4_15);
		     player.playerUI.mypanel0.add(player.playerUI.rand4_16);
		  }
		  else if(tile == 1)
		  {
  	        iconPlayer1 =  (bp[0][4].getIcon());
	        iconPlayer2 =  (bp[1][4].getIcon());
 	        iconPlayer3 =  (bp[2][4].getIcon());
	        iconPlayer4 =  (bp[3][4].getIcon());
	        iconPlayer5 =  (bp[0][5].getIcon());
	        iconPlayer6 =  (bp[1][5].getIcon());
	        iconPlayer7 =  (bp[2][5].getIcon());
	        iconPlayer8 =  (bp[3][5].getIcon());
	        iconPlayer9 =  (bp[0][6].getIcon());
	        iconPlayer10 =  (bp[1][6].getIcon());
	        iconPlayer11 =  (bp[2][6].getIcon());
	        iconPlayer12 =  (bp[3][6].getIcon());
	        iconPlayer13 =  (bp[0][7].getIcon());
	        iconPlayer14 =  (bp[1][7].getIcon());
	        iconPlayer15 =  (bp[2][7].getIcon());
	        iconPlayer16 =  (bp[3][7].getIcon());

	
		//add components to board
			  GameJLabel boardPlayer1 = new GameJLabel(iconPlayer1);
			  GameJLabel boardPlayer2 = new GameJLabel(iconPlayer2);
			  GameJLabel boardPlayer3 = new GameJLabel(iconPlayer3);
			  GameJLabel boardPlayer4 = new GameJLabel(iconPlayer4);
			  GameJLabel boardPlayer5 = new GameJLabel(iconPlayer5);
			  GameJLabel boardPlayer6 = new GameJLabel(iconPlayer6);
			  GameJLabel boardPlayer7 = new GameJLabel(iconPlayer7);
			  GameJLabel boardPlayer8 = new GameJLabel(iconPlayer8);
			  GameJLabel boardPlayer9 = new GameJLabel(iconPlayer9);
			  GameJLabel boardPlayer10 = new GameJLabel(iconPlayer10);
			  GameJLabel boardPlayer11 = new GameJLabel(iconPlayer11);
			  GameJLabel boardPlayer12 = new GameJLabel(iconPlayer12);
			  GameJLabel boardPlayer13 = new GameJLabel(iconPlayer13);
			  GameJLabel boardPlayer14 = new GameJLabel(iconPlayer14);
			  GameJLabel boardPlayer15 = new GameJLabel(iconPlayer15);
			  GameJLabel boardPlayer16 = new GameJLabel(iconPlayer16);
		
			  player.playerUI.rand4_1.add(boardPlayer1, new Integer(0));
		     player.playerUI.rand4_2.add(boardPlayer2, new Integer(0));
		     player.playerUI.rand4_3.add(boardPlayer3, new Integer(0));
		     player.playerUI.rand4_4.add(boardPlayer4, new Integer(0));
		     player.playerUI.rand4_5.add(boardPlayer5, new Integer(0));
		     player.playerUI.rand4_6.add(boardPlayer6, new Integer(0));
		     player.playerUI.rand4_7.add(boardPlayer7, new Integer(0));
		     player.playerUI.rand4_8.add(boardPlayer8, new Integer(0));
		     player.playerUI.rand4_9.add(boardPlayer9, new Integer(0));
		     player.playerUI.rand4_10.add(boardPlayer10, new Integer(0));
		     player.playerUI.rand4_11.add(boardPlayer11, new Integer(0));
		     player.playerUI.rand4_12.add(boardPlayer12, new Integer(0));
		     player.playerUI.rand4_13.add(boardPlayer13, new Integer(0));
		     player.playerUI.rand4_14.add(boardPlayer14, new Integer(0));
		     player.playerUI.rand4_15.add(boardPlayer15, new Integer(0));
		     player.playerUI.rand4_16.add(boardPlayer16, new Integer(0));
		
		     boardPlayer1.setBounds(0, 0, iconPlayer1.getIconWidth(),
		                                  iconPlayer1.getIconHeight()); 
		     player.playerUI.rand4_1.setPreferredSize(new Dimension(iconPlayer1.getIconWidth(),
		                                        iconPlayer1.getIconHeight()));
		     boardPlayer2.setBounds(0, 0, iconPlayer2.getIconWidth(),
		                                  iconPlayer2.getIconHeight()); 
		     player.playerUI.rand4_2.setPreferredSize(new Dimension(iconPlayer2.getIconWidth(),
		                                        iconPlayer2.getIconHeight()));
		     boardPlayer3.setBounds(0, 0, iconPlayer3.getIconWidth(),
		                                  iconPlayer3.getIconHeight()); 
		     player.playerUI.rand4_3.setPreferredSize(new Dimension(iconPlayer3.getIconWidth(),
		                                        iconPlayer3.getIconHeight()));
		     boardPlayer4.setBounds(0, 0, iconPlayer4.getIconWidth(),
		                                  iconPlayer4.getIconHeight()); 
		     player.playerUI.rand4_4.setPreferredSize(new Dimension(iconPlayer4.getIconWidth(),
		                                        iconPlayer4.getIconHeight()));
		     boardPlayer5.setBounds(0, 0, iconPlayer5.getIconWidth(),
		                                  iconPlayer5.getIconHeight()); 
		     player.playerUI.rand4_5.setPreferredSize(new Dimension(iconPlayer5.getIconWidth(),
		                                        iconPlayer5.getIconHeight()));
		     boardPlayer6.setBounds(0, 0, iconPlayer6.getIconWidth(),
		                                  iconPlayer6.getIconHeight()); 
		     player.playerUI.rand4_6.setPreferredSize(new Dimension(iconPlayer6.getIconWidth(),
		                                        iconPlayer6.getIconHeight()));
		     boardPlayer7.setBounds(0, 0, iconPlayer7.getIconWidth(),
		                                  iconPlayer7.getIconHeight()); 
		     player.playerUI.rand4_7.setPreferredSize(new Dimension(iconPlayer7.getIconWidth(),
		                                        iconPlayer7.getIconHeight()));
		     boardPlayer8.setBounds(0, 0, iconPlayer8.getIconWidth(),
		                                  iconPlayer8.getIconHeight()); 
		     player.playerUI.rand4_8.setPreferredSize(new Dimension(iconPlayer8.getIconWidth(),
		                                        iconPlayer8.getIconHeight()));
		     boardPlayer9.setBounds(0, 0, iconPlayer9.getIconWidth(),
		                                  iconPlayer9.getIconHeight()); 
		     player.playerUI.rand4_9.setPreferredSize(new Dimension(iconPlayer9.getIconWidth(),
		                                        iconPlayer9.getIconHeight()));
		     boardPlayer10.setBounds(0, 0, iconPlayer10.getIconWidth(),
		                                  iconPlayer10.getIconHeight()); 
		     player.playerUI.rand4_10.setPreferredSize(new Dimension(iconPlayer10.getIconWidth(),
		                                        iconPlayer10.getIconHeight()));													 													 
		     boardPlayer11.setBounds(0, 0, iconPlayer11.getIconWidth(),
		                                  iconPlayer11.getIconHeight()); 
		     player.playerUI.rand4_11.setPreferredSize(new Dimension(iconPlayer11.getIconWidth(),
		                                        iconPlayer11.getIconHeight()));
		     boardPlayer12.setBounds(0, 0, iconPlayer12.getIconWidth(),
		                                  iconPlayer12.getIconHeight()); 
		     player.playerUI.rand4_12.setPreferredSize(new Dimension(iconPlayer12.getIconWidth(),
		                                        iconPlayer12.getIconHeight()));
		     boardPlayer13.setBounds(0, 0, iconPlayer13.getIconWidth(),
		                                  iconPlayer13.getIconHeight()); 
		     player.playerUI.rand4_13.setPreferredSize(new Dimension(iconPlayer13.getIconWidth(),
		                                        iconPlayer13.getIconHeight()));
		     boardPlayer14.setBounds(0, 0, iconPlayer14.getIconWidth(),
		                                  iconPlayer14.getIconHeight()); 
		     player.playerUI.rand4_14.setPreferredSize(new Dimension(iconPlayer14.getIconWidth(),
		                                        iconPlayer14.getIconHeight()));
		     boardPlayer15.setBounds(0, 0, iconPlayer15.getIconWidth(),
		                                  iconPlayer15.getIconHeight()); 
		     player.playerUI.rand4_15.setPreferredSize(new Dimension(iconPlayer15.getIconWidth(),
		                                        iconPlayer15.getIconHeight()));	
		     boardPlayer16.setBounds(0, 0, iconPlayer16.getIconWidth(),
		                                  iconPlayer16.getIconHeight()); 
		     player.playerUI.rand4_16.setPreferredSize(new Dimension(iconPlayer16.getIconWidth(),
		                                        iconPlayer16.getIconHeight()));													 												 													 
		
			  player.playerUI.mypanel1.add(player.playerUI.rand4_1);
		     player.playerUI.mypanel1.add(player.playerUI.rand4_2);
		     player.playerUI.mypanel1.add(player.playerUI.rand4_3);
		     player.playerUI.mypanel1.add(player.playerUI.rand4_4);
		     player.playerUI.mypanel1.add(player.playerUI.rand4_5);
		     player.playerUI.mypanel1.add(player.playerUI.rand4_6);
		     player.playerUI.mypanel1.add(player.playerUI.rand4_7);
		     player.playerUI.mypanel1.add(player.playerUI.rand4_8);
		     player.playerUI.mypanel1.add(player.playerUI.rand4_9);
		     player.playerUI.mypanel1.add(player.playerUI.rand4_10);
		     player.playerUI.mypanel1.add(player.playerUI.rand4_11);
		     player.playerUI.mypanel1.add(player.playerUI.rand4_12);
		     player.playerUI.mypanel1.add(player.playerUI.rand4_13);
		     player.playerUI.mypanel1.add(player.playerUI.rand4_14);
		     player.playerUI.mypanel1.add(player.playerUI.rand4_15);
		     player.playerUI.mypanel1.add(player.playerUI.rand4_16);
		  
		  }
		  else if(tile == 2)
		  {
  	        iconPlayer1 =  (bp[0][8].getIcon());
	        iconPlayer2 =  (bp[1][8].getIcon());
 	        iconPlayer3 =  (bp[2][8].getIcon());
	        iconPlayer4 =  (bp[3][8].getIcon());
	        iconPlayer5 =  (bp[0][9].getIcon());
	        iconPlayer6 =  (bp[1][9].getIcon());
	        iconPlayer7 =  (bp[2][9].getIcon());
	        iconPlayer8 =  (bp[3][9].getIcon());
	        iconPlayer9 =  (bp[0][10].getIcon());
	        iconPlayer10 =  (bp[1][10].getIcon());
	        iconPlayer11 =  (bp[2][10].getIcon());
	        iconPlayer12 =  (bp[3][10].getIcon());
	        iconPlayer13 =  (bp[0][11].getIcon());
	        iconPlayer14 =  (bp[1][11].getIcon());
	        iconPlayer15 =  (bp[2][11].getIcon());
	        iconPlayer16 =  (bp[3][11].getIcon());

	
		//add components to board
			  GameJLabel boardPlayer1 = new GameJLabel(iconPlayer1);
			  GameJLabel boardPlayer2 = new GameJLabel(iconPlayer2);
			  GameJLabel boardPlayer3 = new GameJLabel(iconPlayer3);
			  GameJLabel boardPlayer4 = new GameJLabel(iconPlayer4);
			  GameJLabel boardPlayer5 = new GameJLabel(iconPlayer5);
			  GameJLabel boardPlayer6 = new GameJLabel(iconPlayer6);
			  GameJLabel boardPlayer7 = new GameJLabel(iconPlayer7);
			  GameJLabel boardPlayer8 = new GameJLabel(iconPlayer8);
			  GameJLabel boardPlayer9 = new GameJLabel(iconPlayer9);
			  GameJLabel boardPlayer10 = new GameJLabel(iconPlayer10);
			  GameJLabel boardPlayer11 = new GameJLabel(iconPlayer11);
			  GameJLabel boardPlayer12 = new GameJLabel(iconPlayer12);
			  GameJLabel boardPlayer13 = new GameJLabel(iconPlayer13);
			  GameJLabel boardPlayer14 = new GameJLabel(iconPlayer14);
			  GameJLabel boardPlayer15 = new GameJLabel(iconPlayer15);
			  GameJLabel boardPlayer16 = new GameJLabel(iconPlayer16);
		
			  player.playerUI.rand4_1.add(boardPlayer1, new Integer(0));
		     player.playerUI.rand4_2.add(boardPlayer2, new Integer(0));
		     player.playerUI.rand4_3.add(boardPlayer3, new Integer(0));
		     player.playerUI.rand4_4.add(boardPlayer4, new Integer(0));
		     player.playerUI.rand4_5.add(boardPlayer5, new Integer(0));
		     player.playerUI.rand4_6.add(boardPlayer6, new Integer(0));
		     player.playerUI.rand4_7.add(boardPlayer7, new Integer(0));
		     player.playerUI.rand4_8.add(boardPlayer8, new Integer(0));
		     player.playerUI.rand4_9.add(boardPlayer9, new Integer(0));
		     player.playerUI.rand4_10.add(boardPlayer10, new Integer(0));
		     player.playerUI.rand4_11.add(boardPlayer11, new Integer(0));
		     player.playerUI.rand4_12.add(boardPlayer12, new Integer(0));
		     player.playerUI.rand4_13.add(boardPlayer13, new Integer(0));
		     player.playerUI.rand4_14.add(boardPlayer14, new Integer(0));
		     player.playerUI.rand4_15.add(boardPlayer15, new Integer(0));
		     player.playerUI.rand4_16.add(boardPlayer16, new Integer(0));
		
		     boardPlayer1.setBounds(0, 0, iconPlayer1.getIconWidth(),
		                                  iconPlayer1.getIconHeight()); 
		     player.playerUI.rand4_1.setPreferredSize(new Dimension(iconPlayer1.getIconWidth(),
		                                        iconPlayer1.getIconHeight()));
		     boardPlayer2.setBounds(0, 0, iconPlayer2.getIconWidth(),
		                                  iconPlayer2.getIconHeight()); 
		     player.playerUI.rand4_2.setPreferredSize(new Dimension(iconPlayer2.getIconWidth(),
		                                        iconPlayer2.getIconHeight()));
		     boardPlayer3.setBounds(0, 0, iconPlayer3.getIconWidth(),
		                                  iconPlayer3.getIconHeight()); 
		     player.playerUI.rand4_3.setPreferredSize(new Dimension(iconPlayer3.getIconWidth(),
		                                        iconPlayer3.getIconHeight()));
		     boardPlayer4.setBounds(0, 0, iconPlayer4.getIconWidth(),
		                                  iconPlayer4.getIconHeight()); 
		     player.playerUI.rand4_4.setPreferredSize(new Dimension(iconPlayer4.getIconWidth(),
		                                        iconPlayer4.getIconHeight()));
		     boardPlayer5.setBounds(0, 0, iconPlayer5.getIconWidth(),
		                                  iconPlayer5.getIconHeight()); 
		     player.playerUI.rand4_5.setPreferredSize(new Dimension(iconPlayer5.getIconWidth(),
		                                        iconPlayer5.getIconHeight()));
		     boardPlayer6.setBounds(0, 0, iconPlayer6.getIconWidth(),
		                                  iconPlayer6.getIconHeight()); 
		     player.playerUI.rand4_6.setPreferredSize(new Dimension(iconPlayer6.getIconWidth(),
		                                        iconPlayer6.getIconHeight()));
		     boardPlayer7.setBounds(0, 0, iconPlayer7.getIconWidth(),
		                                  iconPlayer7.getIconHeight()); 
		     player.playerUI.rand4_7.setPreferredSize(new Dimension(iconPlayer7.getIconWidth(),
		                                        iconPlayer7.getIconHeight()));
		     boardPlayer8.setBounds(0, 0, iconPlayer8.getIconWidth(),
		                                  iconPlayer8.getIconHeight()); 
		     player.playerUI.rand4_8.setPreferredSize(new Dimension(iconPlayer8.getIconWidth(),
		                                        iconPlayer8.getIconHeight()));
		     boardPlayer9.setBounds(0, 0, iconPlayer9.getIconWidth(),
		                                  iconPlayer9.getIconHeight()); 
		     player.playerUI.rand4_9.setPreferredSize(new Dimension(iconPlayer9.getIconWidth(),
		                                        iconPlayer9.getIconHeight()));
		     boardPlayer10.setBounds(0, 0, iconPlayer10.getIconWidth(),
		                                  iconPlayer10.getIconHeight()); 
		     player.playerUI.rand4_10.setPreferredSize(new Dimension(iconPlayer10.getIconWidth(),
		                                        iconPlayer10.getIconHeight()));													 													 
		     boardPlayer11.setBounds(0, 0, iconPlayer11.getIconWidth(),
		                                  iconPlayer11.getIconHeight()); 
		     player.playerUI.rand4_11.setPreferredSize(new Dimension(iconPlayer11.getIconWidth(),
		                                        iconPlayer11.getIconHeight()));
		     boardPlayer12.setBounds(0, 0, iconPlayer12.getIconWidth(),
		                                  iconPlayer12.getIconHeight()); 
		     player.playerUI.rand4_12.setPreferredSize(new Dimension(iconPlayer12.getIconWidth(),
		                                        iconPlayer12.getIconHeight()));
		     boardPlayer13.setBounds(0, 0, iconPlayer13.getIconWidth(),
		                                  iconPlayer13.getIconHeight()); 
		     player.playerUI.rand4_13.setPreferredSize(new Dimension(iconPlayer13.getIconWidth(),
		                                        iconPlayer13.getIconHeight()));
		     boardPlayer14.setBounds(0, 0, iconPlayer14.getIconWidth(),
		                                  iconPlayer14.getIconHeight()); 
		     player.playerUI.rand4_14.setPreferredSize(new Dimension(iconPlayer14.getIconWidth(),
		                                        iconPlayer14.getIconHeight()));
		     boardPlayer15.setBounds(0, 0, iconPlayer15.getIconWidth(),
		                                  iconPlayer15.getIconHeight()); 
		     player.playerUI.rand4_15.setPreferredSize(new Dimension(iconPlayer15.getIconWidth(),
		                                        iconPlayer15.getIconHeight()));	
		     boardPlayer16.setBounds(0, 0, iconPlayer16.getIconWidth(),
		                                  iconPlayer16.getIconHeight()); 
		     player.playerUI.rand4_16.setPreferredSize(new Dimension(iconPlayer16.getIconWidth(),
		                                        iconPlayer16.getIconHeight()));													 												 													 
		
			  player.playerUI.mypanel2.add(player.playerUI.rand4_1);
		     player.playerUI.mypanel2.add(player.playerUI.rand4_2);
		     player.playerUI.mypanel2.add(player.playerUI.rand4_3);
		     player.playerUI.mypanel2.add(player.playerUI.rand4_4);
		     player.playerUI.mypanel2.add(player.playerUI.rand4_5);
		     player.playerUI.mypanel2.add(player.playerUI.rand4_6);
		     player.playerUI.mypanel2.add(player.playerUI.rand4_7);
		     player.playerUI.mypanel2.add(player.playerUI.rand4_8);
		     player.playerUI.mypanel2.add(player.playerUI.rand4_9);
		     player.playerUI.mypanel2.add(player.playerUI.rand4_10);
		     player.playerUI.mypanel2.add(player.playerUI.rand4_11);
		     player.playerUI.mypanel2.add(player.playerUI.rand4_12);
		     player.playerUI.mypanel2.add(player.playerUI.rand4_13);
		     player.playerUI.mypanel2.add(player.playerUI.rand4_14);
		     player.playerUI.mypanel2.add(player.playerUI.rand4_15);
		     player.playerUI.mypanel2.add(player.playerUI.rand4_16);
		  
		  }
		  else if(tile == 5)
		  {
  	        iconPlayer1 =  (bp[4][4].getIcon());
	        iconPlayer2 =  (bp[5][4].getIcon());
 	        iconPlayer3 =  (bp[6][4].getIcon());
	        iconPlayer4 =  (bp[7][4].getIcon());
	        iconPlayer5 =  (bp[4][5].getIcon());
	        iconPlayer6 =  (bp[5][5].getIcon());
	        iconPlayer7 =  (bp[6][5].getIcon());
	        iconPlayer8 =  (bp[7][5].getIcon());
	        iconPlayer9 =  (bp[4][6].getIcon());
	        iconPlayer10 =  (bp[5][6].getIcon());
	        iconPlayer11 =  (bp[6][6].getIcon());
	        iconPlayer12 =  (bp[7][6].getIcon());
	        iconPlayer13 =  (bp[4][7].getIcon());
	        iconPlayer14 =  (bp[5][7].getIcon());
	        iconPlayer15 =  (bp[6][7].getIcon());
	        iconPlayer16 =  (bp[7][7].getIcon());

	
		//add components to board
			  GameJLabel boardPlayer1 = new GameJLabel(iconPlayer1);
			  GameJLabel boardPlayer2 = new GameJLabel(iconPlayer2);
			  GameJLabel boardPlayer3 = new GameJLabel(iconPlayer3);
			  GameJLabel boardPlayer4 = new GameJLabel(iconPlayer4);
			  GameJLabel boardPlayer5 = new GameJLabel(iconPlayer5);
			  GameJLabel boardPlayer6 = new GameJLabel(iconPlayer6);
			  GameJLabel boardPlayer7 = new GameJLabel(iconPlayer7);
			  GameJLabel boardPlayer8 = new GameJLabel(iconPlayer8);
			  GameJLabel boardPlayer9 = new GameJLabel(iconPlayer9);
			  GameJLabel boardPlayer10 = new GameJLabel(iconPlayer10);
			  GameJLabel boardPlayer11 = new GameJLabel(iconPlayer11);
			  GameJLabel boardPlayer12 = new GameJLabel(iconPlayer12);
			  GameJLabel boardPlayer13 = new GameJLabel(iconPlayer13);
			  GameJLabel boardPlayer14 = new GameJLabel(iconPlayer14);
			  GameJLabel boardPlayer15 = new GameJLabel(iconPlayer15);
			  GameJLabel boardPlayer16 = new GameJLabel(iconPlayer16);
		
			  player.playerUI.rand4_1.add(boardPlayer1, new Integer(0));
		     player.playerUI.rand4_2.add(boardPlayer2, new Integer(0));
		     player.playerUI.rand4_3.add(boardPlayer3, new Integer(0));
		     player.playerUI.rand4_4.add(boardPlayer4, new Integer(0));
		     player.playerUI.rand4_5.add(boardPlayer5, new Integer(0));
		     player.playerUI.rand4_6.add(boardPlayer6, new Integer(0));
		     player.playerUI.rand4_7.add(boardPlayer7, new Integer(0));
		     player.playerUI.rand4_8.add(boardPlayer8, new Integer(0));
		     player.playerUI.rand4_9.add(boardPlayer9, new Integer(0));
		     player.playerUI.rand4_10.add(boardPlayer10, new Integer(0));
		     player.playerUI.rand4_11.add(boardPlayer11, new Integer(0));
		     player.playerUI.rand4_12.add(boardPlayer12, new Integer(0));
		     player.playerUI.rand4_13.add(boardPlayer13, new Integer(0));
		     player.playerUI.rand4_14.add(boardPlayer14, new Integer(0));
		     player.playerUI.rand4_15.add(boardPlayer15, new Integer(0));
		     player.playerUI.rand4_16.add(boardPlayer16, new Integer(0));
		
		     boardPlayer1.setBounds(0, 0, iconPlayer1.getIconWidth(),
		                                  iconPlayer1.getIconHeight()); 
		     player.playerUI.rand4_1.setPreferredSize(new Dimension(iconPlayer1.getIconWidth(),
		                                        iconPlayer1.getIconHeight()));
		     boardPlayer2.setBounds(0, 0, iconPlayer2.getIconWidth(),
		                                  iconPlayer2.getIconHeight()); 
		     player.playerUI.rand4_2.setPreferredSize(new Dimension(iconPlayer2.getIconWidth(),
		                                        iconPlayer2.getIconHeight()));
		     boardPlayer3.setBounds(0, 0, iconPlayer3.getIconWidth(),
		                                  iconPlayer3.getIconHeight()); 
		     player.playerUI.rand4_3.setPreferredSize(new Dimension(iconPlayer3.getIconWidth(),
		                                        iconPlayer3.getIconHeight()));
		     boardPlayer4.setBounds(0, 0, iconPlayer4.getIconWidth(),
		                                  iconPlayer4.getIconHeight()); 
		     player.playerUI.rand4_4.setPreferredSize(new Dimension(iconPlayer4.getIconWidth(),
		                                        iconPlayer4.getIconHeight()));
		     boardPlayer5.setBounds(0, 0, iconPlayer5.getIconWidth(),
		                                  iconPlayer5.getIconHeight()); 
		     player.playerUI.rand4_5.setPreferredSize(new Dimension(iconPlayer5.getIconWidth(),
		                                        iconPlayer5.getIconHeight()));
		     boardPlayer6.setBounds(0, 0, iconPlayer6.getIconWidth(),
		                                  iconPlayer6.getIconHeight()); 
		     player.playerUI.rand4_6.setPreferredSize(new Dimension(iconPlayer6.getIconWidth(),
		                                        iconPlayer6.getIconHeight()));
		     boardPlayer7.setBounds(0, 0, iconPlayer7.getIconWidth(),
		                                  iconPlayer7.getIconHeight()); 
		     player.playerUI.rand4_7.setPreferredSize(new Dimension(iconPlayer7.getIconWidth(),
		                                        iconPlayer7.getIconHeight()));
		     boardPlayer8.setBounds(0, 0, iconPlayer8.getIconWidth(),
		                                  iconPlayer8.getIconHeight()); 
		     player.playerUI.rand4_8.setPreferredSize(new Dimension(iconPlayer8.getIconWidth(),
		                                        iconPlayer8.getIconHeight()));
		     boardPlayer9.setBounds(0, 0, iconPlayer9.getIconWidth(),
		                                  iconPlayer9.getIconHeight()); 
		     player.playerUI.rand4_9.setPreferredSize(new Dimension(iconPlayer9.getIconWidth(),
		                                        iconPlayer9.getIconHeight()));
		     boardPlayer10.setBounds(0, 0, iconPlayer10.getIconWidth(),
		                                  iconPlayer10.getIconHeight()); 
		     player.playerUI.rand4_10.setPreferredSize(new Dimension(iconPlayer10.getIconWidth(),
		                                        iconPlayer10.getIconHeight()));													 													 
		     boardPlayer11.setBounds(0, 0, iconPlayer11.getIconWidth(),
		                                  iconPlayer11.getIconHeight()); 
		     player.playerUI.rand4_11.setPreferredSize(new Dimension(iconPlayer11.getIconWidth(),
		                                        iconPlayer11.getIconHeight()));
		     boardPlayer12.setBounds(0, 0, iconPlayer12.getIconWidth(),
		                                  iconPlayer12.getIconHeight()); 
		     player.playerUI.rand4_12.setPreferredSize(new Dimension(iconPlayer12.getIconWidth(),
		                                        iconPlayer12.getIconHeight()));
		     boardPlayer13.setBounds(0, 0, iconPlayer13.getIconWidth(),
		                                  iconPlayer13.getIconHeight()); 
		     player.playerUI.rand4_13.setPreferredSize(new Dimension(iconPlayer13.getIconWidth(),
		                                        iconPlayer13.getIconHeight()));
		     boardPlayer14.setBounds(0, 0, iconPlayer14.getIconWidth(),
		                                  iconPlayer14.getIconHeight()); 
		     player.playerUI.rand4_14.setPreferredSize(new Dimension(iconPlayer14.getIconWidth(),
		                                        iconPlayer14.getIconHeight()));
		     boardPlayer15.setBounds(0, 0, iconPlayer15.getIconWidth(),
		                                  iconPlayer15.getIconHeight()); 
		     player.playerUI.rand4_15.setPreferredSize(new Dimension(iconPlayer15.getIconWidth(),
		                                        iconPlayer15.getIconHeight()));	
		     boardPlayer16.setBounds(0, 0, iconPlayer16.getIconWidth(),
		                                  iconPlayer16.getIconHeight()); 
		     player.playerUI.rand4_16.setPreferredSize(new Dimension(iconPlayer16.getIconWidth(),
		                                        iconPlayer16.getIconHeight()));													 												 													 
		
			  player.playerUI.mypanel5.add(player.playerUI.rand4_1);
		     player.playerUI.mypanel5.add(player.playerUI.rand4_2);
		     player.playerUI.mypanel5.add(player.playerUI.rand4_3);
		     player.playerUI.mypanel5.add(player.playerUI.rand4_4);
		     player.playerUI.mypanel5.add(player.playerUI.rand4_5);
		     player.playerUI.mypanel5.add(player.playerUI.rand4_6);
		     player.playerUI.mypanel5.add(player.playerUI.rand4_7);
		     player.playerUI.mypanel5.add(player.playerUI.rand4_8);
		     player.playerUI.mypanel5.add(player.playerUI.rand4_9);
		     player.playerUI.mypanel5.add(player.playerUI.rand4_10);
		     player.playerUI.mypanel5.add(player.playerUI.rand4_11);
		     player.playerUI.mypanel5.add(player.playerUI.rand4_12);
		     player.playerUI.mypanel5.add(player.playerUI.rand4_13);
		     player.playerUI.mypanel5.add(player.playerUI.rand4_14);
		     player.playerUI.mypanel5.add(player.playerUI.rand4_15);
		     player.playerUI.mypanel5.add(player.playerUI.rand4_16);
		  
		  }
		  else if(tile == 6)
		  {
  	        iconPlayer1 =  (bp[4][8].getIcon());
	        iconPlayer2 =  (bp[5][8].getIcon());
 	        iconPlayer3 =  (bp[6][8].getIcon());
	        iconPlayer4 =  (bp[7][8].getIcon());
	        iconPlayer5 =  (bp[4][9].getIcon());
	        iconPlayer6 =  (bp[5][9].getIcon());
	        iconPlayer7 =  (bp[6][9].getIcon());
	        iconPlayer8 =  (bp[7][9].getIcon());
	        iconPlayer9 =  (bp[4][10].getIcon());
	        iconPlayer10 =  (bp[5][10].getIcon());
	        iconPlayer11 =  (bp[6][10].getIcon());
	        iconPlayer12 =  (bp[7][10].getIcon());
	        iconPlayer13 =  (bp[4][11].getIcon());
	        iconPlayer14 =  (bp[5][11].getIcon());
	        iconPlayer15 =  (bp[6][11].getIcon());
	        iconPlayer16 =  (bp[7][11].getIcon());

	
		//add components to board
			  GameJLabel boardPlayer1 = new GameJLabel(iconPlayer1);
			  GameJLabel boardPlayer2 = new GameJLabel(iconPlayer2);
			  GameJLabel boardPlayer3 = new GameJLabel(iconPlayer3);
			  GameJLabel boardPlayer4 = new GameJLabel(iconPlayer4);
			  GameJLabel boardPlayer5 = new GameJLabel(iconPlayer5);
			  GameJLabel boardPlayer6 = new GameJLabel(iconPlayer6);
			  GameJLabel boardPlayer7 = new GameJLabel(iconPlayer7);
			  GameJLabel boardPlayer8 = new GameJLabel(iconPlayer8);
			  GameJLabel boardPlayer9 = new GameJLabel(iconPlayer9);
			  GameJLabel boardPlayer10 = new GameJLabel(iconPlayer10);
			  GameJLabel boardPlayer11 = new GameJLabel(iconPlayer11);
			  GameJLabel boardPlayer12 = new GameJLabel(iconPlayer12);
			  GameJLabel boardPlayer13 = new GameJLabel(iconPlayer13);
			  GameJLabel boardPlayer14 = new GameJLabel(iconPlayer14);
			  GameJLabel boardPlayer15 = new GameJLabel(iconPlayer15);
			  GameJLabel boardPlayer16 = new GameJLabel(iconPlayer16);
		
			  player.playerUI.rand4_1.add(boardPlayer1, new Integer(0));
		     player.playerUI.rand4_2.add(boardPlayer2, new Integer(0));
		     player.playerUI.rand4_3.add(boardPlayer3, new Integer(0));
		     player.playerUI.rand4_4.add(boardPlayer4, new Integer(0));
		     player.playerUI.rand4_5.add(boardPlayer5, new Integer(0));
		     player.playerUI.rand4_6.add(boardPlayer6, new Integer(0));
		     player.playerUI.rand4_7.add(boardPlayer7, new Integer(0));
		     player.playerUI.rand4_8.add(boardPlayer8, new Integer(0));
		     player.playerUI.rand4_9.add(boardPlayer9, new Integer(0));
		     player.playerUI.rand4_10.add(boardPlayer10, new Integer(0));
		     player.playerUI.rand4_11.add(boardPlayer11, new Integer(0));
		     player.playerUI.rand4_12.add(boardPlayer12, new Integer(0));
		     player.playerUI.rand4_13.add(boardPlayer13, new Integer(0));
		     player.playerUI.rand4_14.add(boardPlayer14, new Integer(0));
		     player.playerUI.rand4_15.add(boardPlayer15, new Integer(0));
		     player.playerUI.rand4_16.add(boardPlayer16, new Integer(0));
		
		     boardPlayer1.setBounds(0, 0, iconPlayer1.getIconWidth(),
		                                  iconPlayer1.getIconHeight()); 
		     player.playerUI.rand4_1.setPreferredSize(new Dimension(iconPlayer1.getIconWidth(),
		                                        iconPlayer1.getIconHeight()));
		     boardPlayer2.setBounds(0, 0, iconPlayer2.getIconWidth(),
		                                  iconPlayer2.getIconHeight()); 
		     player.playerUI.rand4_2.setPreferredSize(new Dimension(iconPlayer2.getIconWidth(),
		                                        iconPlayer2.getIconHeight()));
		     boardPlayer3.setBounds(0, 0, iconPlayer3.getIconWidth(),
		                                  iconPlayer3.getIconHeight()); 
		     player.playerUI.rand4_3.setPreferredSize(new Dimension(iconPlayer3.getIconWidth(),
		                                        iconPlayer3.getIconHeight()));
		     boardPlayer4.setBounds(0, 0, iconPlayer4.getIconWidth(),
		                                  iconPlayer4.getIconHeight()); 
		     player.playerUI.rand4_4.setPreferredSize(new Dimension(iconPlayer4.getIconWidth(),
		                                        iconPlayer4.getIconHeight()));
		     boardPlayer5.setBounds(0, 0, iconPlayer5.getIconWidth(),
		                                  iconPlayer5.getIconHeight()); 
		     player.playerUI.rand4_5.setPreferredSize(new Dimension(iconPlayer5.getIconWidth(),
		                                        iconPlayer5.getIconHeight()));
		     boardPlayer6.setBounds(0, 0, iconPlayer6.getIconWidth(),
		                                  iconPlayer6.getIconHeight()); 
		     player.playerUI.rand4_6.setPreferredSize(new Dimension(iconPlayer6.getIconWidth(),
		                                        iconPlayer6.getIconHeight()));
		     boardPlayer7.setBounds(0, 0, iconPlayer7.getIconWidth(),
		                                  iconPlayer7.getIconHeight()); 
		     player.playerUI.rand4_7.setPreferredSize(new Dimension(iconPlayer7.getIconWidth(),
		                                        iconPlayer7.getIconHeight()));
		     boardPlayer8.setBounds(0, 0, iconPlayer8.getIconWidth(),
		                                  iconPlayer8.getIconHeight()); 
		     player.playerUI.rand4_8.setPreferredSize(new Dimension(iconPlayer8.getIconWidth(),
		                                        iconPlayer8.getIconHeight()));
		     boardPlayer9.setBounds(0, 0, iconPlayer9.getIconWidth(),
		                                  iconPlayer9.getIconHeight()); 
		     player.playerUI.rand4_9.setPreferredSize(new Dimension(iconPlayer9.getIconWidth(),
		                                        iconPlayer9.getIconHeight()));
		     boardPlayer10.setBounds(0, 0, iconPlayer10.getIconWidth(),
		                                  iconPlayer10.getIconHeight()); 
		     player.playerUI.rand4_10.setPreferredSize(new Dimension(iconPlayer10.getIconWidth(),
		                                        iconPlayer10.getIconHeight()));													 													 
		     boardPlayer11.setBounds(0, 0, iconPlayer11.getIconWidth(),
		                                  iconPlayer11.getIconHeight()); 
		     player.playerUI.rand4_11.setPreferredSize(new Dimension(iconPlayer11.getIconWidth(),
		                                        iconPlayer11.getIconHeight()));
		     boardPlayer12.setBounds(0, 0, iconPlayer12.getIconWidth(),
		                                  iconPlayer12.getIconHeight()); 
		     player.playerUI.rand4_12.setPreferredSize(new Dimension(iconPlayer12.getIconWidth(),
		                                        iconPlayer12.getIconHeight()));
		     boardPlayer13.setBounds(0, 0, iconPlayer13.getIconWidth(),
		                                  iconPlayer13.getIconHeight()); 
		     player.playerUI.rand4_13.setPreferredSize(new Dimension(iconPlayer13.getIconWidth(),
		                                        iconPlayer13.getIconHeight()));
		     boardPlayer14.setBounds(0, 0, iconPlayer14.getIconWidth(),
		                                  iconPlayer14.getIconHeight()); 
		     player.playerUI.rand4_14.setPreferredSize(new Dimension(iconPlayer14.getIconWidth(),
		                                        iconPlayer14.getIconHeight()));
		     boardPlayer15.setBounds(0, 0, iconPlayer15.getIconWidth(),
		                                  iconPlayer15.getIconHeight()); 
		     player.playerUI.rand4_15.setPreferredSize(new Dimension(iconPlayer15.getIconWidth(),
		                                        iconPlayer15.getIconHeight()));	
		     boardPlayer16.setBounds(0, 0, iconPlayer16.getIconWidth(),
		                                  iconPlayer16.getIconHeight()); 
		     player.playerUI.rand4_16.setPreferredSize(new Dimension(iconPlayer16.getIconWidth(),
		                                        iconPlayer16.getIconHeight()));													 												 													 
		
			  player.playerUI.mypanel6.add(player.playerUI.rand4_1);
		     player.playerUI.mypanel6.add(player.playerUI.rand4_2);
		     player.playerUI.mypanel6.add(player.playerUI.rand4_3);
		     player.playerUI.mypanel6.add(player.playerUI.rand4_4);
		     player.playerUI.mypanel6.add(player.playerUI.rand4_5);
		     player.playerUI.mypanel6.add(player.playerUI.rand4_6);
		     player.playerUI.mypanel6.add(player.playerUI.rand4_7);
		     player.playerUI.mypanel6.add(player.playerUI.rand4_8);
		     player.playerUI.mypanel6.add(player.playerUI.rand4_9);
		     player.playerUI.mypanel6.add(player.playerUI.rand4_10);
		     player.playerUI.mypanel6.add(player.playerUI.rand4_11);
		     player.playerUI.mypanel6.add(player.playerUI.rand4_12);
		     player.playerUI.mypanel6.add(player.playerUI.rand4_13);
		     player.playerUI.mypanel6.add(player.playerUI.rand4_14);
		     player.playerUI.mypanel6.add(player.playerUI.rand4_15);
		     player.playerUI.mypanel6.add(player.playerUI.rand4_16);
		  
		  }
		  else if(tile == 7)
		  {
	       iconPlayer1 =  bp[4][12].getIcon();
	       iconPlayer2 =  bp[5][12].getIcon();
	       iconPlayer3 =  bp[6][12].getIcon();
	       iconPlayer4 =  bp[7][12].getIcon();
	       iconPlayer5 =  bp[4][13].getIcon();
	       iconPlayer6 =  bp[5][13].getIcon();
	       iconPlayer7 =  bp[6][13].getIcon();
	       iconPlayer8 =  bp[7][13].getIcon();
	       iconPlayer9 =  bp[4][14].getIcon();
	       iconPlayer10 =  bp[5][14].getIcon();
	       iconPlayer11 =  bp[6][14].getIcon();
	       iconPlayer12 =  bp[7][14].getIcon();
	       iconPlayer13 =  bp[4][15].getIcon();
	       iconPlayer14 =  bp[5][15].getIcon();
	       iconPlayer15 =  bp[6][15].getIcon();
	       iconPlayer16 =  bp[7][15].getIcon();


		//add components to board
			  GameJLabel boardPlayer1 = new GameJLabel(iconPlayer1);
			  GameJLabel boardPlayer2 = new GameJLabel(iconPlayer2);
			  GameJLabel boardPlayer3 = new GameJLabel(iconPlayer3);
			  GameJLabel boardPlayer4 = new GameJLabel(iconPlayer4);
			  GameJLabel boardPlayer5 = new GameJLabel(iconPlayer5);
			  GameJLabel boardPlayer6 = new GameJLabel(iconPlayer6);
			  GameJLabel boardPlayer7 = new GameJLabel(iconPlayer7);
			  GameJLabel boardPlayer8 = new GameJLabel(iconPlayer8);
			  GameJLabel boardPlayer9 = new GameJLabel(iconPlayer9);
			  GameJLabel boardPlayer10 = new GameJLabel(iconPlayer10);
			  GameJLabel boardPlayer11 = new GameJLabel(iconPlayer11);
			  GameJLabel boardPlayer12 = new GameJLabel(iconPlayer12);
			  GameJLabel boardPlayer13 = new GameJLabel(iconPlayer13);
			  GameJLabel boardPlayer14 = new GameJLabel(iconPlayer14);
			  GameJLabel boardPlayer15 = new GameJLabel(iconPlayer15);
			  GameJLabel boardPlayer16 = new GameJLabel(iconPlayer16);
		
			  player.playerUI.rand4_1.add(boardPlayer1, new Integer(0));
		     player.playerUI.rand4_2.add(boardPlayer2, new Integer(0));
		     player.playerUI.rand4_3.add(boardPlayer3, new Integer(0));
		     player.playerUI.rand4_4.add(boardPlayer4, new Integer(0));
		     player.playerUI.rand4_5.add(boardPlayer5, new Integer(0));
		     player.playerUI.rand4_6.add(boardPlayer6, new Integer(0));
		     player.playerUI.rand4_7.add(boardPlayer7, new Integer(0));
		     player.playerUI.rand4_8.add(boardPlayer8, new Integer(0));
		     player.playerUI.rand4_9.add(boardPlayer9, new Integer(0));
		     player.playerUI.rand4_10.add(boardPlayer10, new Integer(0));
		     player.playerUI.rand4_11.add(boardPlayer11, new Integer(0));
		     player.playerUI.rand4_12.add(boardPlayer12, new Integer(0));
		     player.playerUI.rand4_13.add(boardPlayer13, new Integer(0));
		     player.playerUI.rand4_14.add(boardPlayer14, new Integer(0));
		     player.playerUI.rand4_15.add(boardPlayer15, new Integer(0));
		     player.playerUI.rand4_16.add(boardPlayer16, new Integer(0));
		
		     boardPlayer1.setBounds(0, 0, iconPlayer1.getIconWidth(),
		                                  iconPlayer1.getIconHeight()); 
		     player.playerUI.rand4_1.setPreferredSize(new Dimension(iconPlayer1.getIconWidth(),
		                                        iconPlayer1.getIconHeight()));
		     boardPlayer2.setBounds(0, 0, iconPlayer2.getIconWidth(),
		                                  iconPlayer2.getIconHeight()); 
		     player.playerUI.rand4_2.setPreferredSize(new Dimension(iconPlayer2.getIconWidth(),
		                                        iconPlayer2.getIconHeight()));
		     boardPlayer3.setBounds(0, 0, iconPlayer3.getIconWidth(),
		                                  iconPlayer3.getIconHeight()); 
		     player.playerUI.rand4_3.setPreferredSize(new Dimension(iconPlayer3.getIconWidth(),
		                                        iconPlayer3.getIconHeight()));
		     boardPlayer4.setBounds(0, 0, iconPlayer4.getIconWidth(),
		                                  iconPlayer4.getIconHeight()); 
		     player.playerUI.rand4_4.setPreferredSize(new Dimension(iconPlayer4.getIconWidth(),
		                                        iconPlayer4.getIconHeight()));
		     boardPlayer5.setBounds(0, 0, iconPlayer5.getIconWidth(),
		                                  iconPlayer5.getIconHeight()); 
		     player.playerUI.rand4_5.setPreferredSize(new Dimension(iconPlayer5.getIconWidth(),
		                                        iconPlayer5.getIconHeight()));
		     boardPlayer6.setBounds(0, 0, iconPlayer6.getIconWidth(),
		                                  iconPlayer6.getIconHeight()); 
		     player.playerUI.rand4_6.setPreferredSize(new Dimension(iconPlayer6.getIconWidth(),
		                                        iconPlayer6.getIconHeight()));
		     boardPlayer7.setBounds(0, 0, iconPlayer7.getIconWidth(),
		                                  iconPlayer7.getIconHeight()); 
		     player.playerUI.rand4_7.setPreferredSize(new Dimension(iconPlayer7.getIconWidth(),
		                                        iconPlayer7.getIconHeight()));
		     boardPlayer8.setBounds(0, 0, iconPlayer8.getIconWidth(),
		                                  iconPlayer8.getIconHeight()); 
		     player.playerUI.rand4_8.setPreferredSize(new Dimension(iconPlayer8.getIconWidth(),
		                                        iconPlayer8.getIconHeight()));
		     boardPlayer9.setBounds(0, 0, iconPlayer9.getIconWidth(),
		                                  iconPlayer9.getIconHeight()); 
		     player.playerUI.rand4_9.setPreferredSize(new Dimension(iconPlayer9.getIconWidth(),
		                                        iconPlayer9.getIconHeight()));
		     boardPlayer10.setBounds(0, 0, iconPlayer10.getIconWidth(),
		                                  iconPlayer10.getIconHeight()); 
		     player.playerUI.rand4_10.setPreferredSize(new Dimension(iconPlayer10.getIconWidth(),
		                                        iconPlayer10.getIconHeight()));													 													 
		     boardPlayer11.setBounds(0, 0, iconPlayer11.getIconWidth(),
		                                  iconPlayer11.getIconHeight()); 
		     player.playerUI.rand4_11.setPreferredSize(new Dimension(iconPlayer11.getIconWidth(),
		                                        iconPlayer11.getIconHeight()));
		     boardPlayer12.setBounds(0, 0, iconPlayer12.getIconWidth(),
		                                  iconPlayer12.getIconHeight()); 
		     player.playerUI.rand4_12.setPreferredSize(new Dimension(iconPlayer12.getIconWidth(),
		                                        iconPlayer12.getIconHeight()));
		     boardPlayer13.setBounds(0, 0, iconPlayer13.getIconWidth(),
		                                  iconPlayer13.getIconHeight()); 
		     player.playerUI.rand4_13.setPreferredSize(new Dimension(iconPlayer13.getIconWidth(),
		                                        iconPlayer13.getIconHeight()));
		     boardPlayer14.setBounds(0, 0, iconPlayer14.getIconWidth(),
		                                  iconPlayer14.getIconHeight()); 
		     player.playerUI.rand4_14.setPreferredSize(new Dimension(iconPlayer14.getIconWidth(),
		                                        iconPlayer14.getIconHeight()));
		     boardPlayer15.setBounds(0, 0, iconPlayer15.getIconWidth(),
		                                  iconPlayer15.getIconHeight()); 
		     player.playerUI.rand4_15.setPreferredSize(new Dimension(iconPlayer15.getIconWidth(),
		                                        iconPlayer15.getIconHeight()));	
		     boardPlayer16.setBounds(0, 0, iconPlayer16.getIconWidth(),
		                                  iconPlayer16.getIconHeight()); 
		     player.playerUI.rand4_16.setPreferredSize(new Dimension(iconPlayer16.getIconWidth(),
		                                        iconPlayer16.getIconHeight()));													 												 													 
		
			  player.playerUI.mypanel7.add(player.playerUI.rand4_1);
		     player.playerUI.mypanel7.add(player.playerUI.rand4_2);
		     player.playerUI.mypanel7.add(player.playerUI.rand4_3);
		     player.playerUI.mypanel7.add(player.playerUI.rand4_4);
		     player.playerUI.mypanel7.add(player.playerUI.rand4_5);
		     player.playerUI.mypanel7.add(player.playerUI.rand4_6);
		     player.playerUI.mypanel7.add(player.playerUI.rand4_7);
		     player.playerUI.mypanel7.add(player.playerUI.rand4_8);
		     player.playerUI.mypanel7.add(player.playerUI.rand4_9);
		     player.playerUI.mypanel7.add(player.playerUI.rand4_10);
		     player.playerUI.mypanel7.add(player.playerUI.rand4_11);
		     player.playerUI.mypanel7.add(player.playerUI.rand4_12);
		     player.playerUI.mypanel7.add(player.playerUI.rand4_13);
		     player.playerUI.mypanel7.add(player.playerUI.rand4_14);
		     player.playerUI.mypanel7.add(player.playerUI.rand4_15);
		     player.playerUI.mypanel7.add(player.playerUI.rand4_16);
		  
		  }
		}

      private void populateRand5(int tile, Player player)
		{
		  if(tile == 0)
		  {
			  iconPlayer1 =  (bp[0][0].getIcon());
	        iconPlayer2 =  (bp[1][0].getIcon());
 	        iconPlayer3 =  (bp[2][0].getIcon());
	        iconPlayer4 =  (bp[3][0].getIcon());
	        iconPlayer5 =  (bp[0][1].getIcon());
	        iconPlayer6 =  (bp[1][1].getIcon());
	        iconPlayer7 =  (bp[2][1].getIcon());
	        iconPlayer8 =  (bp[3][1].getIcon());
	        iconPlayer9 =  (bp[0][2].getIcon());
	        iconPlayer10 =  (bp[1][2].getIcon());
	        iconPlayer11 =  (bp[2][2].getIcon());
	        iconPlayer12 =  (bp[3][2].getIcon());
	        iconPlayer13 =  (bp[0][3].getIcon());
	        iconPlayer14 =  (bp[1][3].getIcon());
	        iconPlayer15 =  (bp[2][3].getIcon());
	        iconPlayer16 =  (bp[3][3].getIcon());

	
		//add components to board
			  GameJLabel boardPlayer1 = new GameJLabel(iconPlayer1);
			  GameJLabel boardPlayer2 = new GameJLabel(iconPlayer2);
			  GameJLabel boardPlayer3 = new GameJLabel(iconPlayer3);
			  GameJLabel boardPlayer4 = new GameJLabel(iconPlayer4);
			  GameJLabel boardPlayer5 = new GameJLabel(iconPlayer5);
			  GameJLabel boardPlayer6 = new GameJLabel(iconPlayer6);
			  GameJLabel boardPlayer7 = new GameJLabel(iconPlayer7);
			  GameJLabel boardPlayer8 = new GameJLabel(iconPlayer8);
			  GameJLabel boardPlayer9 = new GameJLabel(iconPlayer9);
			  GameJLabel boardPlayer10 = new GameJLabel(iconPlayer10);
			  GameJLabel boardPlayer11 = new GameJLabel(iconPlayer11);
			  GameJLabel boardPlayer12 = new GameJLabel(iconPlayer12);
			  GameJLabel boardPlayer13 = new GameJLabel(iconPlayer13);
			  GameJLabel boardPlayer14 = new GameJLabel(iconPlayer14);
			  GameJLabel boardPlayer15 = new GameJLabel(iconPlayer15);
			  GameJLabel boardPlayer16 = new GameJLabel(iconPlayer16);
		
			  player.playerUI.rand5_1.add(boardPlayer1, new Integer(0));
		     player.playerUI.rand5_2.add(boardPlayer2, new Integer(0));
		     player.playerUI.rand5_3.add(boardPlayer3, new Integer(0));
		     player.playerUI.rand5_4.add(boardPlayer4, new Integer(0));
		     player.playerUI.rand5_5.add(boardPlayer5, new Integer(0));
		     player.playerUI.rand5_6.add(boardPlayer6, new Integer(0));
		     player.playerUI.rand5_7.add(boardPlayer7, new Integer(0));
		     player.playerUI.rand5_8.add(boardPlayer8, new Integer(0));
		     player.playerUI.rand5_9.add(boardPlayer9, new Integer(0));
		     player.playerUI.rand5_10.add(boardPlayer10, new Integer(0));
		     player.playerUI.rand5_11.add(boardPlayer11, new Integer(0));
		     player.playerUI.rand5_12.add(boardPlayer12, new Integer(0));
		     player.playerUI.rand5_13.add(boardPlayer13, new Integer(0));
		     player.playerUI.rand5_14.add(boardPlayer14, new Integer(0));
		     player.playerUI.rand5_15.add(boardPlayer15, new Integer(0));
		     player.playerUI.rand5_16.add(boardPlayer16, new Integer(0));
		
		     boardPlayer1.setBounds(0, 0, iconPlayer1.getIconWidth(),
		                                  iconPlayer1.getIconHeight()); 
		     player.playerUI.rand5_1.setPreferredSize(new Dimension(iconPlayer1.getIconWidth(),
		                                        iconPlayer1.getIconHeight()));
		     boardPlayer2.setBounds(0, 0, iconPlayer2.getIconWidth(),
		                                  iconPlayer2.getIconHeight()); 
		     player.playerUI.rand5_2.setPreferredSize(new Dimension(iconPlayer2.getIconWidth(),
		                                        iconPlayer2.getIconHeight()));
		     boardPlayer3.setBounds(0, 0, iconPlayer3.getIconWidth(),
		                                  iconPlayer3.getIconHeight()); 
		     player.playerUI.rand5_3.setPreferredSize(new Dimension(iconPlayer3.getIconWidth(),
		                                        iconPlayer3.getIconHeight()));
		     boardPlayer4.setBounds(0, 0, iconPlayer4.getIconWidth(),
		                                  iconPlayer4.getIconHeight()); 
		     player.playerUI.rand5_4.setPreferredSize(new Dimension(iconPlayer4.getIconWidth(),
		                                        iconPlayer4.getIconHeight()));
		     boardPlayer5.setBounds(0, 0, iconPlayer5.getIconWidth(),
		                                  iconPlayer5.getIconHeight()); 
		     player.playerUI.rand5_5.setPreferredSize(new Dimension(iconPlayer5.getIconWidth(),
		                                        iconPlayer5.getIconHeight()));
		     boardPlayer6.setBounds(0, 0, iconPlayer6.getIconWidth(),
		                                  iconPlayer6.getIconHeight()); 
		     player.playerUI.rand5_6.setPreferredSize(new Dimension(iconPlayer6.getIconWidth(),
		                                        iconPlayer6.getIconHeight()));
		     boardPlayer7.setBounds(0, 0, iconPlayer7.getIconWidth(),
		                                  iconPlayer7.getIconHeight()); 
		     player.playerUI.rand5_7.setPreferredSize(new Dimension(iconPlayer7.getIconWidth(),
		                                        iconPlayer7.getIconHeight()));
		     boardPlayer8.setBounds(0, 0, iconPlayer8.getIconWidth(),
		                                  iconPlayer8.getIconHeight()); 
		     player.playerUI.rand5_8.setPreferredSize(new Dimension(iconPlayer8.getIconWidth(),
		                                        iconPlayer8.getIconHeight()));
		     boardPlayer9.setBounds(0, 0, iconPlayer9.getIconWidth(),
		                                  iconPlayer9.getIconHeight()); 
		     player.playerUI.rand5_9.setPreferredSize(new Dimension(iconPlayer9.getIconWidth(),
		                                        iconPlayer9.getIconHeight()));
		     boardPlayer10.setBounds(0, 0, iconPlayer10.getIconWidth(),
		                                  iconPlayer10.getIconHeight()); 
		     player.playerUI.rand5_10.setPreferredSize(new Dimension(iconPlayer10.getIconWidth(),
		                                        iconPlayer10.getIconHeight()));													 													 
		     boardPlayer11.setBounds(0, 0, iconPlayer11.getIconWidth(),
		                                  iconPlayer11.getIconHeight()); 
		     player.playerUI.rand5_11.setPreferredSize(new Dimension(iconPlayer11.getIconWidth(),
		                                        iconPlayer11.getIconHeight()));
		     boardPlayer12.setBounds(0, 0, iconPlayer12.getIconWidth(),
		                                  iconPlayer12.getIconHeight()); 
		     player.playerUI.rand5_12.setPreferredSize(new Dimension(iconPlayer12.getIconWidth(),
		                                        iconPlayer12.getIconHeight()));
		     boardPlayer13.setBounds(0, 0, iconPlayer13.getIconWidth(),
		                                  iconPlayer13.getIconHeight()); 
		     player.playerUI.rand5_13.setPreferredSize(new Dimension(iconPlayer13.getIconWidth(),
		                                        iconPlayer13.getIconHeight()));
		     boardPlayer14.setBounds(0, 0, iconPlayer14.getIconWidth(),
		                                  iconPlayer14.getIconHeight()); 
		     player.playerUI.rand5_14.setPreferredSize(new Dimension(iconPlayer14.getIconWidth(),
		                                        iconPlayer14.getIconHeight()));
		     boardPlayer15.setBounds(0, 0, iconPlayer15.getIconWidth(),
		                                  iconPlayer15.getIconHeight()); 
		     player.playerUI.rand5_15.setPreferredSize(new Dimension(iconPlayer15.getIconWidth(),
		                                        iconPlayer15.getIconHeight()));	
		     boardPlayer16.setBounds(0, 0, iconPlayer16.getIconWidth(),
		                                  iconPlayer16.getIconHeight()); 
		     player.playerUI.rand5_16.setPreferredSize(new Dimension(iconPlayer16.getIconWidth(),
		                                        iconPlayer16.getIconHeight()));													 												 													 
		
			  player.playerUI.mypanel0.add(player.playerUI.rand5_1);
		     player.playerUI.mypanel0.add(player.playerUI.rand5_2);
		     player.playerUI.mypanel0.add(player.playerUI.rand5_3);
		     player.playerUI.mypanel0.add(player.playerUI.rand5_4);
		     player.playerUI.mypanel0.add(player.playerUI.rand5_5);
		     player.playerUI.mypanel0.add(player.playerUI.rand5_6);
		     player.playerUI.mypanel0.add(player.playerUI.rand5_7);
		     player.playerUI.mypanel0.add(player.playerUI.rand5_8);
		     player.playerUI.mypanel0.add(player.playerUI.rand5_9);
		     player.playerUI.mypanel0.add(player.playerUI.rand5_10);
		     player.playerUI.mypanel0.add(player.playerUI.rand5_11);
		     player.playerUI.mypanel0.add(player.playerUI.rand5_12);
		     player.playerUI.mypanel0.add(player.playerUI.rand5_13);
		     player.playerUI.mypanel0.add(player.playerUI.rand5_14);
		     player.playerUI.mypanel0.add(player.playerUI.rand5_15);
		     player.playerUI.mypanel0.add(player.playerUI.rand5_16);
		  }
		  else if(tile == 1)
		  {
  	        iconPlayer1 =  (bp[0][4].getIcon());
	        iconPlayer2 =  (bp[1][4].getIcon());
 	        iconPlayer3 =  (bp[2][4].getIcon());
	        iconPlayer4 =  (bp[3][4].getIcon());
	        iconPlayer5 =  (bp[0][5].getIcon());
	        iconPlayer6 =  (bp[1][5].getIcon());
	        iconPlayer7 =  (bp[2][5].getIcon());
	        iconPlayer8 =  (bp[3][5].getIcon());
	        iconPlayer9 =  (bp[0][6].getIcon());
	        iconPlayer10 =  (bp[1][6].getIcon());
	        iconPlayer11 =  (bp[2][6].getIcon());
	        iconPlayer12 =  (bp[3][6].getIcon());
	        iconPlayer13 =  (bp[0][7].getIcon());
	        iconPlayer14 =  (bp[1][7].getIcon());
	        iconPlayer15 =  (bp[2][7].getIcon());
	        iconPlayer16 =  (bp[3][7].getIcon());

	
		//add components to board
			  GameJLabel boardPlayer1 = new GameJLabel(iconPlayer1);
			  GameJLabel boardPlayer2 = new GameJLabel(iconPlayer2);
			  GameJLabel boardPlayer3 = new GameJLabel(iconPlayer3);
			  GameJLabel boardPlayer4 = new GameJLabel(iconPlayer4);
			  GameJLabel boardPlayer5 = new GameJLabel(iconPlayer5);
			  GameJLabel boardPlayer6 = new GameJLabel(iconPlayer6);
			  GameJLabel boardPlayer7 = new GameJLabel(iconPlayer7);
			  GameJLabel boardPlayer8 = new GameJLabel(iconPlayer8);
			  GameJLabel boardPlayer9 = new GameJLabel(iconPlayer9);
			  GameJLabel boardPlayer10 = new GameJLabel(iconPlayer10);
			  GameJLabel boardPlayer11 = new GameJLabel(iconPlayer11);
			  GameJLabel boardPlayer12 = new GameJLabel(iconPlayer12);
			  GameJLabel boardPlayer13 = new GameJLabel(iconPlayer13);
			  GameJLabel boardPlayer14 = new GameJLabel(iconPlayer14);
			  GameJLabel boardPlayer15 = new GameJLabel(iconPlayer15);
			  GameJLabel boardPlayer16 = new GameJLabel(iconPlayer16);
		
			  player.playerUI.rand5_1.add(boardPlayer1, new Integer(0));
		     player.playerUI.rand5_2.add(boardPlayer2, new Integer(0));
		     player.playerUI.rand5_3.add(boardPlayer3, new Integer(0));
		     player.playerUI.rand5_4.add(boardPlayer4, new Integer(0));
		     player.playerUI.rand5_5.add(boardPlayer5, new Integer(0));
		     player.playerUI.rand5_6.add(boardPlayer6, new Integer(0));
		     player.playerUI.rand5_7.add(boardPlayer7, new Integer(0));
		     player.playerUI.rand5_8.add(boardPlayer8, new Integer(0));
		     player.playerUI.rand5_9.add(boardPlayer9, new Integer(0));
		     player.playerUI.rand5_10.add(boardPlayer10, new Integer(0));
		     player.playerUI.rand5_11.add(boardPlayer11, new Integer(0));
		     player.playerUI.rand5_12.add(boardPlayer12, new Integer(0));
		     player.playerUI.rand5_13.add(boardPlayer13, new Integer(0));
		     player.playerUI.rand5_14.add(boardPlayer14, new Integer(0));
		     player.playerUI.rand5_15.add(boardPlayer15, new Integer(0));
		     player.playerUI.rand5_16.add(boardPlayer16, new Integer(0));
		
		     boardPlayer1.setBounds(0, 0, iconPlayer1.getIconWidth(),
		                                  iconPlayer1.getIconHeight()); 
		     player.playerUI.rand5_1.setPreferredSize(new Dimension(iconPlayer1.getIconWidth(),
		                                        iconPlayer1.getIconHeight()));
		     boardPlayer2.setBounds(0, 0, iconPlayer2.getIconWidth(),
		                                  iconPlayer2.getIconHeight()); 
		     player.playerUI.rand5_2.setPreferredSize(new Dimension(iconPlayer2.getIconWidth(),
		                                        iconPlayer2.getIconHeight()));
		     boardPlayer3.setBounds(0, 0, iconPlayer3.getIconWidth(),
		                                  iconPlayer3.getIconHeight()); 
		     player.playerUI.rand5_3.setPreferredSize(new Dimension(iconPlayer3.getIconWidth(),
		                                        iconPlayer3.getIconHeight()));
		     boardPlayer4.setBounds(0, 0, iconPlayer4.getIconWidth(),
		                                  iconPlayer4.getIconHeight()); 
		     player.playerUI.rand5_4.setPreferredSize(new Dimension(iconPlayer4.getIconWidth(),
		                                        iconPlayer4.getIconHeight()));
		     boardPlayer5.setBounds(0, 0, iconPlayer5.getIconWidth(),
		                                  iconPlayer5.getIconHeight()); 
		     player.playerUI.rand5_5.setPreferredSize(new Dimension(iconPlayer5.getIconWidth(),
		                                        iconPlayer5.getIconHeight()));
		     boardPlayer6.setBounds(0, 0, iconPlayer6.getIconWidth(),
		                                  iconPlayer6.getIconHeight()); 
		     player.playerUI.rand5_6.setPreferredSize(new Dimension(iconPlayer6.getIconWidth(),
		                                        iconPlayer6.getIconHeight()));
		     boardPlayer7.setBounds(0, 0, iconPlayer7.getIconWidth(),
		                                  iconPlayer7.getIconHeight()); 
		     player.playerUI.rand5_7.setPreferredSize(new Dimension(iconPlayer7.getIconWidth(),
		                                        iconPlayer7.getIconHeight()));
		     boardPlayer8.setBounds(0, 0, iconPlayer8.getIconWidth(),
		                                  iconPlayer8.getIconHeight()); 
		     player.playerUI.rand5_8.setPreferredSize(new Dimension(iconPlayer8.getIconWidth(),
		                                        iconPlayer8.getIconHeight()));
		     boardPlayer9.setBounds(0, 0, iconPlayer9.getIconWidth(),
		                                  iconPlayer9.getIconHeight()); 
		     player.playerUI.rand5_9.setPreferredSize(new Dimension(iconPlayer9.getIconWidth(),
		                                        iconPlayer9.getIconHeight()));
		     boardPlayer10.setBounds(0, 0, iconPlayer10.getIconWidth(),
		                                  iconPlayer10.getIconHeight()); 
		     player.playerUI.rand5_10.setPreferredSize(new Dimension(iconPlayer10.getIconWidth(),
		                                        iconPlayer10.getIconHeight()));													 													 
		     boardPlayer11.setBounds(0, 0, iconPlayer11.getIconWidth(),
		                                  iconPlayer11.getIconHeight()); 
		     player.playerUI.rand5_11.setPreferredSize(new Dimension(iconPlayer11.getIconWidth(),
		                                        iconPlayer11.getIconHeight()));
		     boardPlayer12.setBounds(0, 0, iconPlayer12.getIconWidth(),
		                                  iconPlayer12.getIconHeight()); 
		     player.playerUI.rand5_12.setPreferredSize(new Dimension(iconPlayer12.getIconWidth(),
		                                        iconPlayer12.getIconHeight()));
		     boardPlayer13.setBounds(0, 0, iconPlayer13.getIconWidth(),
		                                  iconPlayer13.getIconHeight()); 
		     player.playerUI.rand5_13.setPreferredSize(new Dimension(iconPlayer13.getIconWidth(),
		                                        iconPlayer13.getIconHeight()));
		     boardPlayer14.setBounds(0, 0, iconPlayer14.getIconWidth(),
		                                  iconPlayer14.getIconHeight()); 
		     player.playerUI.rand5_14.setPreferredSize(new Dimension(iconPlayer14.getIconWidth(),
		                                        iconPlayer14.getIconHeight()));
		     boardPlayer15.setBounds(0, 0, iconPlayer15.getIconWidth(),
		                                  iconPlayer15.getIconHeight()); 
		     player.playerUI.rand5_15.setPreferredSize(new Dimension(iconPlayer15.getIconWidth(),
		                                        iconPlayer15.getIconHeight()));	
		     boardPlayer16.setBounds(0, 0, iconPlayer16.getIconWidth(),
		                                  iconPlayer16.getIconHeight()); 
		     player.playerUI.rand5_16.setPreferredSize(new Dimension(iconPlayer16.getIconWidth(),
		                                        iconPlayer16.getIconHeight()));													 												 													 
		
			  player.playerUI.mypanel1.add(player.playerUI.rand5_1);
		     player.playerUI.mypanel1.add(player.playerUI.rand5_2);
		     player.playerUI.mypanel1.add(player.playerUI.rand5_3);
		     player.playerUI.mypanel1.add(player.playerUI.rand5_4);
		     player.playerUI.mypanel1.add(player.playerUI.rand5_5);
		     player.playerUI.mypanel1.add(player.playerUI.rand5_6);
		     player.playerUI.mypanel1.add(player.playerUI.rand5_7);
		     player.playerUI.mypanel1.add(player.playerUI.rand5_8);
		     player.playerUI.mypanel1.add(player.playerUI.rand5_9);
		     player.playerUI.mypanel1.add(player.playerUI.rand5_10);
		     player.playerUI.mypanel1.add(player.playerUI.rand5_11);
		     player.playerUI.mypanel1.add(player.playerUI.rand5_12);
		     player.playerUI.mypanel1.add(player.playerUI.rand5_13);
		     player.playerUI.mypanel1.add(player.playerUI.rand5_14);
		     player.playerUI.mypanel1.add(player.playerUI.rand5_15);
		     player.playerUI.mypanel1.add(player.playerUI.rand5_16);
		  
		  }
		  else if(tile == 2)
		  {
  	        iconPlayer1 =  (bp[0][8].getIcon());
	        iconPlayer2 =  (bp[1][8].getIcon());
 	        iconPlayer3 =  (bp[2][8].getIcon());
	        iconPlayer4 =  (bp[3][8].getIcon());
	        iconPlayer5 =  (bp[0][9].getIcon());
	        iconPlayer6 =  (bp[1][9].getIcon());
	        iconPlayer7 =  (bp[2][9].getIcon());
	        iconPlayer8 =  (bp[3][9].getIcon());
	        iconPlayer9 =  (bp[0][10].getIcon());
	        iconPlayer10 =  (bp[1][10].getIcon());
	        iconPlayer11 =  (bp[2][10].getIcon());
	        iconPlayer12 =  (bp[3][10].getIcon());
	        iconPlayer13 =  (bp[0][11].getIcon());
	        iconPlayer14 =  (bp[1][11].getIcon());
	        iconPlayer15 =  (bp[2][11].getIcon());
	        iconPlayer16 =  (bp[3][11].getIcon());

	
		//add components to board
			  GameJLabel boardPlayer1 = new GameJLabel(iconPlayer1);
			  GameJLabel boardPlayer2 = new GameJLabel(iconPlayer2);
			  GameJLabel boardPlayer3 = new GameJLabel(iconPlayer3);
			  GameJLabel boardPlayer4 = new GameJLabel(iconPlayer4);
			  GameJLabel boardPlayer5 = new GameJLabel(iconPlayer5);
			  GameJLabel boardPlayer6 = new GameJLabel(iconPlayer6);
			  GameJLabel boardPlayer7 = new GameJLabel(iconPlayer7);
			  GameJLabel boardPlayer8 = new GameJLabel(iconPlayer8);
			  GameJLabel boardPlayer9 = new GameJLabel(iconPlayer9);
			  GameJLabel boardPlayer10 = new GameJLabel(iconPlayer10);
			  GameJLabel boardPlayer11 = new GameJLabel(iconPlayer11);
			  GameJLabel boardPlayer12 = new GameJLabel(iconPlayer12);
			  GameJLabel boardPlayer13 = new GameJLabel(iconPlayer13);
			  GameJLabel boardPlayer14 = new GameJLabel(iconPlayer14);
			  GameJLabel boardPlayer15 = new GameJLabel(iconPlayer15);
			  GameJLabel boardPlayer16 = new GameJLabel(iconPlayer16);
		
			  player.playerUI.rand5_1.add(boardPlayer1, new Integer(0));
		     player.playerUI.rand5_2.add(boardPlayer2, new Integer(0));
		     player.playerUI.rand5_3.add(boardPlayer3, new Integer(0));
		     player.playerUI.rand5_4.add(boardPlayer4, new Integer(0));
		     player.playerUI.rand5_5.add(boardPlayer5, new Integer(0));
		     player.playerUI.rand5_6.add(boardPlayer6, new Integer(0));
		     player.playerUI.rand5_7.add(boardPlayer7, new Integer(0));
		     player.playerUI.rand5_8.add(boardPlayer8, new Integer(0));
		     player.playerUI.rand5_9.add(boardPlayer9, new Integer(0));
		     player.playerUI.rand5_10.add(boardPlayer10, new Integer(0));
		     player.playerUI.rand5_11.add(boardPlayer11, new Integer(0));
		     player.playerUI.rand5_12.add(boardPlayer12, new Integer(0));
		     player.playerUI.rand5_13.add(boardPlayer13, new Integer(0));
		     player.playerUI.rand5_14.add(boardPlayer14, new Integer(0));
		     player.playerUI.rand5_15.add(boardPlayer15, new Integer(0));
		     player.playerUI.rand5_16.add(boardPlayer16, new Integer(0));
		
		     boardPlayer1.setBounds(0, 0, iconPlayer1.getIconWidth(),
		                                  iconPlayer1.getIconHeight()); 
		     player.playerUI.rand5_1.setPreferredSize(new Dimension(iconPlayer1.getIconWidth(),
		                                        iconPlayer1.getIconHeight()));
		     boardPlayer2.setBounds(0, 0, iconPlayer2.getIconWidth(),
		                                  iconPlayer2.getIconHeight()); 
		     player.playerUI.rand5_2.setPreferredSize(new Dimension(iconPlayer2.getIconWidth(),
		                                        iconPlayer2.getIconHeight()));
		     boardPlayer3.setBounds(0, 0, iconPlayer3.getIconWidth(),
		                                  iconPlayer3.getIconHeight()); 
		     player.playerUI.rand5_3.setPreferredSize(new Dimension(iconPlayer3.getIconWidth(),
		                                        iconPlayer3.getIconHeight()));
		     boardPlayer4.setBounds(0, 0, iconPlayer4.getIconWidth(),
		                                  iconPlayer4.getIconHeight()); 
		     player.playerUI.rand5_4.setPreferredSize(new Dimension(iconPlayer4.getIconWidth(),
		                                        iconPlayer4.getIconHeight()));
		     boardPlayer5.setBounds(0, 0, iconPlayer5.getIconWidth(),
		                                  iconPlayer5.getIconHeight()); 
		     player.playerUI.rand5_5.setPreferredSize(new Dimension(iconPlayer5.getIconWidth(),
		                                        iconPlayer5.getIconHeight()));
		     boardPlayer6.setBounds(0, 0, iconPlayer6.getIconWidth(),
		                                  iconPlayer6.getIconHeight()); 
		     player.playerUI.rand5_6.setPreferredSize(new Dimension(iconPlayer6.getIconWidth(),
		                                        iconPlayer6.getIconHeight()));
		     boardPlayer7.setBounds(0, 0, iconPlayer7.getIconWidth(),
		                                  iconPlayer7.getIconHeight()); 
		     player.playerUI.rand5_7.setPreferredSize(new Dimension(iconPlayer7.getIconWidth(),
		                                        iconPlayer7.getIconHeight()));
		     boardPlayer8.setBounds(0, 0, iconPlayer8.getIconWidth(),
		                                  iconPlayer8.getIconHeight()); 
		     player.playerUI.rand5_8.setPreferredSize(new Dimension(iconPlayer8.getIconWidth(),
		                                        iconPlayer8.getIconHeight()));
		     boardPlayer9.setBounds(0, 0, iconPlayer9.getIconWidth(),
		                                  iconPlayer9.getIconHeight()); 
		     player.playerUI.rand5_9.setPreferredSize(new Dimension(iconPlayer9.getIconWidth(),
		                                        iconPlayer9.getIconHeight()));
		     boardPlayer10.setBounds(0, 0, iconPlayer10.getIconWidth(),
		                                  iconPlayer10.getIconHeight()); 
		     player.playerUI.rand5_10.setPreferredSize(new Dimension(iconPlayer10.getIconWidth(),
		                                        iconPlayer10.getIconHeight()));													 													 
		     boardPlayer11.setBounds(0, 0, iconPlayer11.getIconWidth(),
		                                  iconPlayer11.getIconHeight()); 
		     player.playerUI.rand5_11.setPreferredSize(new Dimension(iconPlayer11.getIconWidth(),
		                                        iconPlayer11.getIconHeight()));
		     boardPlayer12.setBounds(0, 0, iconPlayer12.getIconWidth(),
		                                  iconPlayer12.getIconHeight()); 
		     player.playerUI.rand5_12.setPreferredSize(new Dimension(iconPlayer12.getIconWidth(),
		                                        iconPlayer12.getIconHeight()));
		     boardPlayer13.setBounds(0, 0, iconPlayer13.getIconWidth(),
		                                  iconPlayer13.getIconHeight()); 
		     player.playerUI.rand5_13.setPreferredSize(new Dimension(iconPlayer13.getIconWidth(),
		                                        iconPlayer13.getIconHeight()));
		     boardPlayer14.setBounds(0, 0, iconPlayer14.getIconWidth(),
		                                  iconPlayer14.getIconHeight()); 
		     player.playerUI.rand5_14.setPreferredSize(new Dimension(iconPlayer14.getIconWidth(),
		                                        iconPlayer14.getIconHeight()));
		     boardPlayer15.setBounds(0, 0, iconPlayer15.getIconWidth(),
		                                  iconPlayer15.getIconHeight()); 
		     player.playerUI.rand5_15.setPreferredSize(new Dimension(iconPlayer15.getIconWidth(),
		                                        iconPlayer15.getIconHeight()));	
		     boardPlayer16.setBounds(0, 0, iconPlayer16.getIconWidth(),
		                                  iconPlayer16.getIconHeight()); 
		     player.playerUI.rand5_16.setPreferredSize(new Dimension(iconPlayer16.getIconWidth(),
		                                        iconPlayer16.getIconHeight()));													 												 													 
		
			  player.playerUI.mypanel2.add(player.playerUI.rand5_1);
		     player.playerUI.mypanel2.add(player.playerUI.rand5_2);
		     player.playerUI.mypanel2.add(player.playerUI.rand5_3);
		     player.playerUI.mypanel2.add(player.playerUI.rand5_4);
		     player.playerUI.mypanel2.add(player.playerUI.rand5_5);
		     player.playerUI.mypanel2.add(player.playerUI.rand5_6);
		     player.playerUI.mypanel2.add(player.playerUI.rand5_7);
		     player.playerUI.mypanel2.add(player.playerUI.rand5_8);
		     player.playerUI.mypanel2.add(player.playerUI.rand5_9);
		     player.playerUI.mypanel2.add(player.playerUI.rand5_10);
		     player.playerUI.mypanel2.add(player.playerUI.rand5_11);
		     player.playerUI.mypanel2.add(player.playerUI.rand5_12);
		     player.playerUI.mypanel2.add(player.playerUI.rand5_13);
		     player.playerUI.mypanel2.add(player.playerUI.rand5_14);
		     player.playerUI.mypanel2.add(player.playerUI.rand5_15);
		     player.playerUI.mypanel2.add(player.playerUI.rand5_16);
		  
		  }
		  else if(tile == 5)
		  {
  	        iconPlayer1 =  (bp[4][4].getIcon());
	        iconPlayer2 =  (bp[5][4].getIcon());
 	        iconPlayer3 =  (bp[6][4].getIcon());
	        iconPlayer4 =  (bp[7][4].getIcon());
	        iconPlayer5 =  (bp[4][5].getIcon());
	        iconPlayer6 =  (bp[5][5].getIcon());
	        iconPlayer7 =  (bp[6][5].getIcon());
	        iconPlayer8 =  (bp[7][5].getIcon());
	        iconPlayer9 =  (bp[4][6].getIcon());
	        iconPlayer10 =  (bp[5][6].getIcon());
	        iconPlayer11 =  (bp[6][6].getIcon());
	        iconPlayer12 =  (bp[7][6].getIcon());
	        iconPlayer13 =  (bp[4][7].getIcon());
	        iconPlayer14 =  (bp[5][7].getIcon());
	        iconPlayer15 =  (bp[6][7].getIcon());
	        iconPlayer16 =  (bp[7][7].getIcon());

	
		//add components to board
			  GameJLabel boardPlayer1 = new GameJLabel(iconPlayer1);
			  GameJLabel boardPlayer2 = new GameJLabel(iconPlayer2);
			  GameJLabel boardPlayer3 = new GameJLabel(iconPlayer3);
			  GameJLabel boardPlayer4 = new GameJLabel(iconPlayer4);
			  GameJLabel boardPlayer5 = new GameJLabel(iconPlayer5);
			  GameJLabel boardPlayer6 = new GameJLabel(iconPlayer6);
			  GameJLabel boardPlayer7 = new GameJLabel(iconPlayer7);
			  GameJLabel boardPlayer8 = new GameJLabel(iconPlayer8);
			  GameJLabel boardPlayer9 = new GameJLabel(iconPlayer9);
			  GameJLabel boardPlayer10 = new GameJLabel(iconPlayer10);
			  GameJLabel boardPlayer11 = new GameJLabel(iconPlayer11);
			  GameJLabel boardPlayer12 = new GameJLabel(iconPlayer12);
			  GameJLabel boardPlayer13 = new GameJLabel(iconPlayer13);
			  GameJLabel boardPlayer14 = new GameJLabel(iconPlayer14);
			  GameJLabel boardPlayer15 = new GameJLabel(iconPlayer15);
			  GameJLabel boardPlayer16 = new GameJLabel(iconPlayer16);
		
			  player.playerUI.rand5_1.add(boardPlayer1, new Integer(0));
		     player.playerUI.rand5_2.add(boardPlayer2, new Integer(0));
		     player.playerUI.rand5_3.add(boardPlayer3, new Integer(0));
		     player.playerUI.rand5_4.add(boardPlayer4, new Integer(0));
		     player.playerUI.rand5_5.add(boardPlayer5, new Integer(0));
		     player.playerUI.rand5_6.add(boardPlayer6, new Integer(0));
		     player.playerUI.rand5_7.add(boardPlayer7, new Integer(0));
		     player.playerUI.rand5_8.add(boardPlayer8, new Integer(0));
		     player.playerUI.rand5_9.add(boardPlayer9, new Integer(0));
		     player.playerUI.rand5_10.add(boardPlayer10, new Integer(0));
		     player.playerUI.rand5_11.add(boardPlayer11, new Integer(0));
		     player.playerUI.rand5_12.add(boardPlayer12, new Integer(0));
		     player.playerUI.rand5_13.add(boardPlayer13, new Integer(0));
		     player.playerUI.rand5_14.add(boardPlayer14, new Integer(0));
		     player.playerUI.rand5_15.add(boardPlayer15, new Integer(0));
		     player.playerUI.rand5_16.add(boardPlayer16, new Integer(0));
		
		     boardPlayer1.setBounds(0, 0, iconPlayer1.getIconWidth(),
		                                  iconPlayer1.getIconHeight()); 
		     player.playerUI.rand5_1.setPreferredSize(new Dimension(iconPlayer1.getIconWidth(),
		                                        iconPlayer1.getIconHeight()));
		     boardPlayer2.setBounds(0, 0, iconPlayer2.getIconWidth(),
		                                  iconPlayer2.getIconHeight()); 
		     player.playerUI.rand5_2.setPreferredSize(new Dimension(iconPlayer2.getIconWidth(),
		                                        iconPlayer2.getIconHeight()));
		     boardPlayer3.setBounds(0, 0, iconPlayer3.getIconWidth(),
		                                  iconPlayer3.getIconHeight()); 
		     player.playerUI.rand5_3.setPreferredSize(new Dimension(iconPlayer3.getIconWidth(),
		                                        iconPlayer3.getIconHeight()));
		     boardPlayer4.setBounds(0, 0, iconPlayer4.getIconWidth(),
		                                  iconPlayer4.getIconHeight()); 
		     player.playerUI.rand5_4.setPreferredSize(new Dimension(iconPlayer4.getIconWidth(),
		                                        iconPlayer4.getIconHeight()));
		     boardPlayer5.setBounds(0, 0, iconPlayer5.getIconWidth(),
		                                  iconPlayer5.getIconHeight()); 
		     player.playerUI.rand5_5.setPreferredSize(new Dimension(iconPlayer5.getIconWidth(),
		                                        iconPlayer5.getIconHeight()));
		     boardPlayer6.setBounds(0, 0, iconPlayer6.getIconWidth(),
		                                  iconPlayer6.getIconHeight()); 
		     player.playerUI.rand5_6.setPreferredSize(new Dimension(iconPlayer6.getIconWidth(),
		                                        iconPlayer6.getIconHeight()));
		     boardPlayer7.setBounds(0, 0, iconPlayer7.getIconWidth(),
		                                  iconPlayer7.getIconHeight()); 
		     player.playerUI.rand5_7.setPreferredSize(new Dimension(iconPlayer7.getIconWidth(),
		                                        iconPlayer7.getIconHeight()));
		     boardPlayer8.setBounds(0, 0, iconPlayer8.getIconWidth(),
		                                  iconPlayer8.getIconHeight()); 
		     player.playerUI.rand5_8.setPreferredSize(new Dimension(iconPlayer8.getIconWidth(),
		                                        iconPlayer8.getIconHeight()));
		     boardPlayer9.setBounds(0, 0, iconPlayer9.getIconWidth(),
		                                  iconPlayer9.getIconHeight()); 
		     player.playerUI.rand5_9.setPreferredSize(new Dimension(iconPlayer9.getIconWidth(),
		                                        iconPlayer9.getIconHeight()));
		     boardPlayer10.setBounds(0, 0, iconPlayer10.getIconWidth(),
		                                  iconPlayer10.getIconHeight()); 
		     player.playerUI.rand5_10.setPreferredSize(new Dimension(iconPlayer10.getIconWidth(),
		                                        iconPlayer10.getIconHeight()));													 													 
		     boardPlayer11.setBounds(0, 0, iconPlayer11.getIconWidth(),
		                                  iconPlayer11.getIconHeight()); 
		     player.playerUI.rand5_11.setPreferredSize(new Dimension(iconPlayer11.getIconWidth(),
		                                        iconPlayer11.getIconHeight()));
		     boardPlayer12.setBounds(0, 0, iconPlayer12.getIconWidth(),
		                                  iconPlayer12.getIconHeight()); 
		     player.playerUI.rand5_12.setPreferredSize(new Dimension(iconPlayer12.getIconWidth(),
		                                        iconPlayer12.getIconHeight()));
		     boardPlayer13.setBounds(0, 0, iconPlayer13.getIconWidth(),
		                                  iconPlayer13.getIconHeight()); 
		     player.playerUI.rand5_13.setPreferredSize(new Dimension(iconPlayer13.getIconWidth(),
		                                        iconPlayer13.getIconHeight()));
		     boardPlayer14.setBounds(0, 0, iconPlayer14.getIconWidth(),
		                                  iconPlayer14.getIconHeight()); 
		     player.playerUI.rand5_14.setPreferredSize(new Dimension(iconPlayer14.getIconWidth(),
		                                        iconPlayer14.getIconHeight()));
		     boardPlayer15.setBounds(0, 0, iconPlayer15.getIconWidth(),
		                                  iconPlayer15.getIconHeight()); 
		     player.playerUI.rand5_15.setPreferredSize(new Dimension(iconPlayer15.getIconWidth(),
		                                        iconPlayer15.getIconHeight()));	
		     boardPlayer16.setBounds(0, 0, iconPlayer16.getIconWidth(),
		                                  iconPlayer16.getIconHeight()); 
		     player.playerUI.rand5_16.setPreferredSize(new Dimension(iconPlayer16.getIconWidth(),
		                                        iconPlayer16.getIconHeight()));													 												 													 
		
			  player.playerUI.mypanel5.add(player.playerUI.rand5_1);
		     player.playerUI.mypanel5.add(player.playerUI.rand5_2);
		     player.playerUI.mypanel5.add(player.playerUI.rand5_3);
		     player.playerUI.mypanel5.add(player.playerUI.rand5_4);
		     player.playerUI.mypanel5.add(player.playerUI.rand5_5);
		     player.playerUI.mypanel5.add(player.playerUI.rand5_6);
		     player.playerUI.mypanel5.add(player.playerUI.rand5_7);
		     player.playerUI.mypanel5.add(player.playerUI.rand5_8);
		     player.playerUI.mypanel5.add(player.playerUI.rand5_9);
		     player.playerUI.mypanel5.add(player.playerUI.rand5_10);
		     player.playerUI.mypanel5.add(player.playerUI.rand5_11);
		     player.playerUI.mypanel5.add(player.playerUI.rand5_12);
		     player.playerUI.mypanel5.add(player.playerUI.rand5_13);
		     player.playerUI.mypanel5.add(player.playerUI.rand5_14);
		     player.playerUI.mypanel5.add(player.playerUI.rand5_15);
		     player.playerUI.mypanel5.add(player.playerUI.rand5_16);
		  
		  }
		  else if(tile == 6)
		  {
  	        iconPlayer1 =  (bp[4][8].getIcon());
	        iconPlayer2 =  (bp[5][8].getIcon());
 	        iconPlayer3 =  (bp[6][8].getIcon());
	        iconPlayer4 =  (bp[7][8].getIcon());
	        iconPlayer5 =  (bp[4][9].getIcon());
	        iconPlayer6 =  (bp[5][9].getIcon());
	        iconPlayer7 =  (bp[6][9].getIcon());
	        iconPlayer8 =  (bp[7][9].getIcon());
	        iconPlayer9 =  (bp[4][10].getIcon());
	        iconPlayer10 =  (bp[5][10].getIcon());
	        iconPlayer11 =  (bp[6][10].getIcon());
	        iconPlayer12 =  (bp[7][10].getIcon());
	        iconPlayer13 =  (bp[4][11].getIcon());
	        iconPlayer14 =  (bp[5][11].getIcon());
	        iconPlayer15 =  (bp[6][11].getIcon());
	        iconPlayer16 =  (bp[7][11].getIcon());

	
		//add components to board
			  GameJLabel boardPlayer1 = new GameJLabel(iconPlayer1);
			  GameJLabel boardPlayer2 = new GameJLabel(iconPlayer2);
			  GameJLabel boardPlayer3 = new GameJLabel(iconPlayer3);
			  GameJLabel boardPlayer4 = new GameJLabel(iconPlayer4);
			  GameJLabel boardPlayer5 = new GameJLabel(iconPlayer5);
			  GameJLabel boardPlayer6 = new GameJLabel(iconPlayer6);
			  GameJLabel boardPlayer7 = new GameJLabel(iconPlayer7);
			  GameJLabel boardPlayer8 = new GameJLabel(iconPlayer8);
			  GameJLabel boardPlayer9 = new GameJLabel(iconPlayer9);
			  GameJLabel boardPlayer10 = new GameJLabel(iconPlayer10);
			  GameJLabel boardPlayer11 = new GameJLabel(iconPlayer11);
			  GameJLabel boardPlayer12 = new GameJLabel(iconPlayer12);
			  GameJLabel boardPlayer13 = new GameJLabel(iconPlayer13);
			  GameJLabel boardPlayer14 = new GameJLabel(iconPlayer14);
			  GameJLabel boardPlayer15 = new GameJLabel(iconPlayer15);
			  GameJLabel boardPlayer16 = new GameJLabel(iconPlayer16);
		
			  player.playerUI.rand5_1.add(boardPlayer1, new Integer(0));
		     player.playerUI.rand5_2.add(boardPlayer2, new Integer(0));
		     player.playerUI.rand5_3.add(boardPlayer3, new Integer(0));
		     player.playerUI.rand5_4.add(boardPlayer4, new Integer(0));
		     player.playerUI.rand5_5.add(boardPlayer5, new Integer(0));
		     player.playerUI.rand5_6.add(boardPlayer6, new Integer(0));
		     player.playerUI.rand5_7.add(boardPlayer7, new Integer(0));
		     player.playerUI.rand5_8.add(boardPlayer8, new Integer(0));
		     player.playerUI.rand5_9.add(boardPlayer9, new Integer(0));
		     player.playerUI.rand5_10.add(boardPlayer10, new Integer(0));
		     player.playerUI.rand5_11.add(boardPlayer11, new Integer(0));
		     player.playerUI.rand5_12.add(boardPlayer12, new Integer(0));
		     player.playerUI.rand5_13.add(boardPlayer13, new Integer(0));
		     player.playerUI.rand5_14.add(boardPlayer14, new Integer(0));
		     player.playerUI.rand5_15.add(boardPlayer15, new Integer(0));
		     player.playerUI.rand5_16.add(boardPlayer16, new Integer(0));
		
		     boardPlayer1.setBounds(0, 0, iconPlayer1.getIconWidth(),
		                                  iconPlayer1.getIconHeight()); 
		     player.playerUI.rand5_1.setPreferredSize(new Dimension(iconPlayer1.getIconWidth(),
		                                        iconPlayer1.getIconHeight()));
		     boardPlayer2.setBounds(0, 0, iconPlayer2.getIconWidth(),
		                                  iconPlayer2.getIconHeight()); 
		     player.playerUI.rand5_2.setPreferredSize(new Dimension(iconPlayer2.getIconWidth(),
		                                        iconPlayer2.getIconHeight()));
		     boardPlayer3.setBounds(0, 0, iconPlayer3.getIconWidth(),
		                                  iconPlayer3.getIconHeight()); 
		     player.playerUI.rand5_3.setPreferredSize(new Dimension(iconPlayer3.getIconWidth(),
		                                        iconPlayer3.getIconHeight()));
		     boardPlayer4.setBounds(0, 0, iconPlayer4.getIconWidth(),
		                                  iconPlayer4.getIconHeight()); 
		     player.playerUI.rand5_4.setPreferredSize(new Dimension(iconPlayer4.getIconWidth(),
		                                        iconPlayer4.getIconHeight()));
		     boardPlayer5.setBounds(0, 0, iconPlayer5.getIconWidth(),
		                                  iconPlayer5.getIconHeight()); 
		     player.playerUI.rand5_5.setPreferredSize(new Dimension(iconPlayer5.getIconWidth(),
		                                        iconPlayer5.getIconHeight()));
		     boardPlayer6.setBounds(0, 0, iconPlayer6.getIconWidth(),
		                                  iconPlayer6.getIconHeight()); 
		     player.playerUI.rand5_6.setPreferredSize(new Dimension(iconPlayer6.getIconWidth(),
		                                        iconPlayer6.getIconHeight()));
		     boardPlayer7.setBounds(0, 0, iconPlayer7.getIconWidth(),
		                                  iconPlayer7.getIconHeight()); 
		     player.playerUI.rand5_7.setPreferredSize(new Dimension(iconPlayer7.getIconWidth(),
		                                        iconPlayer7.getIconHeight()));
		     boardPlayer8.setBounds(0, 0, iconPlayer8.getIconWidth(),
		                                  iconPlayer8.getIconHeight()); 
		     player.playerUI.rand5_8.setPreferredSize(new Dimension(iconPlayer8.getIconWidth(),
		                                        iconPlayer8.getIconHeight()));
		     boardPlayer9.setBounds(0, 0, iconPlayer9.getIconWidth(),
		                                  iconPlayer9.getIconHeight()); 
		     player.playerUI.rand5_9.setPreferredSize(new Dimension(iconPlayer9.getIconWidth(),
		                                        iconPlayer9.getIconHeight()));
		     boardPlayer10.setBounds(0, 0, iconPlayer10.getIconWidth(),
		                                  iconPlayer10.getIconHeight()); 
		     player.playerUI.rand5_10.setPreferredSize(new Dimension(iconPlayer10.getIconWidth(),
		                                        iconPlayer10.getIconHeight()));													 													 
		     boardPlayer11.setBounds(0, 0, iconPlayer11.getIconWidth(),
		                                  iconPlayer11.getIconHeight()); 
		     player.playerUI.rand5_11.setPreferredSize(new Dimension(iconPlayer11.getIconWidth(),
		                                        iconPlayer11.getIconHeight()));
		     boardPlayer12.setBounds(0, 0, iconPlayer12.getIconWidth(),
		                                  iconPlayer12.getIconHeight()); 
		     player.playerUI.rand5_12.setPreferredSize(new Dimension(iconPlayer12.getIconWidth(),
		                                        iconPlayer12.getIconHeight()));
		     boardPlayer13.setBounds(0, 0, iconPlayer13.getIconWidth(),
		                                  iconPlayer13.getIconHeight()); 
		     player.playerUI.rand5_13.setPreferredSize(new Dimension(iconPlayer13.getIconWidth(),
		                                        iconPlayer13.getIconHeight()));
		     boardPlayer14.setBounds(0, 0, iconPlayer14.getIconWidth(),
		                                  iconPlayer14.getIconHeight()); 
		     player.playerUI.rand5_14.setPreferredSize(new Dimension(iconPlayer14.getIconWidth(),
		                                        iconPlayer14.getIconHeight()));
		     boardPlayer15.setBounds(0, 0, iconPlayer15.getIconWidth(),
		                                  iconPlayer15.getIconHeight()); 
		     player.playerUI.rand5_15.setPreferredSize(new Dimension(iconPlayer15.getIconWidth(),
		                                        iconPlayer15.getIconHeight()));	
		     boardPlayer16.setBounds(0, 0, iconPlayer16.getIconWidth(),
		                                  iconPlayer16.getIconHeight()); 
		     player.playerUI.rand5_16.setPreferredSize(new Dimension(iconPlayer16.getIconWidth(),
		                                        iconPlayer16.getIconHeight()));													 												 													 
		
			  player.playerUI.mypanel6.add(player.playerUI.rand5_1);
		     player.playerUI.mypanel6.add(player.playerUI.rand5_2);
		     player.playerUI.mypanel6.add(player.playerUI.rand5_3);
		     player.playerUI.mypanel6.add(player.playerUI.rand5_4);
		     player.playerUI.mypanel6.add(player.playerUI.rand5_5);
		     player.playerUI.mypanel6.add(player.playerUI.rand5_6);
		     player.playerUI.mypanel6.add(player.playerUI.rand5_7);
		     player.playerUI.mypanel6.add(player.playerUI.rand5_8);
		     player.playerUI.mypanel6.add(player.playerUI.rand5_9);
		     player.playerUI.mypanel6.add(player.playerUI.rand5_10);
		     player.playerUI.mypanel6.add(player.playerUI.rand5_11);
		     player.playerUI.mypanel6.add(player.playerUI.rand5_12);
		     player.playerUI.mypanel6.add(player.playerUI.rand5_13);
		     player.playerUI.mypanel6.add(player.playerUI.rand5_14);
		     player.playerUI.mypanel6.add(player.playerUI.rand5_15);
		     player.playerUI.mypanel6.add(player.playerUI.rand5_16);
		  
		  }
		  else if(tile == 7)
		  {
	       iconPlayer1 =  bp[4][12].getIcon();
	       iconPlayer2 =  bp[5][12].getIcon();
	       iconPlayer3 =  bp[6][12].getIcon();
	       iconPlayer4 =  bp[7][12].getIcon();
	       iconPlayer5 =  bp[4][13].getIcon();
	       iconPlayer6 =  bp[5][13].getIcon();
	       iconPlayer7 =  bp[6][13].getIcon();
	       iconPlayer8 =  bp[7][13].getIcon();
	       iconPlayer9 =  bp[4][14].getIcon();
	       iconPlayer10 =  bp[5][14].getIcon();
	       iconPlayer11 =  bp[6][14].getIcon();
	       iconPlayer12 =  bp[7][14].getIcon();
	       iconPlayer13 =  bp[4][15].getIcon();
	       iconPlayer14 =  bp[5][15].getIcon();
	       iconPlayer15 =  bp[6][15].getIcon();
	       iconPlayer16 =  bp[7][15].getIcon();


		//add components to board
			  GameJLabel boardPlayer1 = new GameJLabel(iconPlayer1);
			  GameJLabel boardPlayer2 = new GameJLabel(iconPlayer2);
			  GameJLabel boardPlayer3 = new GameJLabel(iconPlayer3);
			  GameJLabel boardPlayer4 = new GameJLabel(iconPlayer4);
			  GameJLabel boardPlayer5 = new GameJLabel(iconPlayer5);
			  GameJLabel boardPlayer6 = new GameJLabel(iconPlayer6);
			  GameJLabel boardPlayer7 = new GameJLabel(iconPlayer7);
			  GameJLabel boardPlayer8 = new GameJLabel(iconPlayer8);
			  GameJLabel boardPlayer9 = new GameJLabel(iconPlayer9);
			  GameJLabel boardPlayer10 = new GameJLabel(iconPlayer10);
			  GameJLabel boardPlayer11 = new GameJLabel(iconPlayer11);
			  GameJLabel boardPlayer12 = new GameJLabel(iconPlayer12);
			  GameJLabel boardPlayer13 = new GameJLabel(iconPlayer13);
			  GameJLabel boardPlayer14 = new GameJLabel(iconPlayer14);
			  GameJLabel boardPlayer15 = new GameJLabel(iconPlayer15);
			  GameJLabel boardPlayer16 = new GameJLabel(iconPlayer16);
		
			  player.playerUI.rand5_1.add(boardPlayer1, new Integer(0));
		     player.playerUI.rand5_2.add(boardPlayer2, new Integer(0));
		     player.playerUI.rand5_3.add(boardPlayer3, new Integer(0));
		     player.playerUI.rand5_4.add(boardPlayer4, new Integer(0));
		     player.playerUI.rand5_5.add(boardPlayer5, new Integer(0));
		     player.playerUI.rand5_6.add(boardPlayer6, new Integer(0));
		     player.playerUI.rand5_7.add(boardPlayer7, new Integer(0));
		     player.playerUI.rand5_8.add(boardPlayer8, new Integer(0));
		     player.playerUI.rand5_9.add(boardPlayer9, new Integer(0));
		     player.playerUI.rand5_10.add(boardPlayer10, new Integer(0));
		     player.playerUI.rand5_11.add(boardPlayer11, new Integer(0));
		     player.playerUI.rand5_12.add(boardPlayer12, new Integer(0));
		     player.playerUI.rand5_13.add(boardPlayer13, new Integer(0));
		     player.playerUI.rand5_14.add(boardPlayer14, new Integer(0));
		     player.playerUI.rand5_15.add(boardPlayer15, new Integer(0));
		     player.playerUI.rand5_16.add(boardPlayer16, new Integer(0));
		
		     boardPlayer1.setBounds(0, 0, iconPlayer1.getIconWidth(),
		                                  iconPlayer1.getIconHeight()); 
		     player.playerUI.rand5_1.setPreferredSize(new Dimension(iconPlayer1.getIconWidth(),
		                                        iconPlayer1.getIconHeight()));
		     boardPlayer2.setBounds(0, 0, iconPlayer2.getIconWidth(),
		                                  iconPlayer2.getIconHeight()); 
		     player.playerUI.rand5_2.setPreferredSize(new Dimension(iconPlayer2.getIconWidth(),
		                                        iconPlayer2.getIconHeight()));
		     boardPlayer3.setBounds(0, 0, iconPlayer3.getIconWidth(),
		                                  iconPlayer3.getIconHeight()); 
		     player.playerUI.rand5_3.setPreferredSize(new Dimension(iconPlayer3.getIconWidth(),
		                                        iconPlayer3.getIconHeight()));
		     boardPlayer4.setBounds(0, 0, iconPlayer4.getIconWidth(),
		                                  iconPlayer4.getIconHeight()); 
		     player.playerUI.rand5_4.setPreferredSize(new Dimension(iconPlayer4.getIconWidth(),
		                                        iconPlayer4.getIconHeight()));
		     boardPlayer5.setBounds(0, 0, iconPlayer5.getIconWidth(),
		                                  iconPlayer5.getIconHeight()); 
		     player.playerUI.rand5_5.setPreferredSize(new Dimension(iconPlayer5.getIconWidth(),
		                                        iconPlayer5.getIconHeight()));
		     boardPlayer6.setBounds(0, 0, iconPlayer6.getIconWidth(),
		                                  iconPlayer6.getIconHeight()); 
		     player.playerUI.rand5_6.setPreferredSize(new Dimension(iconPlayer6.getIconWidth(),
		                                        iconPlayer6.getIconHeight()));
		     boardPlayer7.setBounds(0, 0, iconPlayer7.getIconWidth(),
		                                  iconPlayer7.getIconHeight()); 
		     player.playerUI.rand5_7.setPreferredSize(new Dimension(iconPlayer7.getIconWidth(),
		                                        iconPlayer7.getIconHeight()));
		     boardPlayer8.setBounds(0, 0, iconPlayer8.getIconWidth(),
		                                  iconPlayer8.getIconHeight()); 
		     player.playerUI.rand5_8.setPreferredSize(new Dimension(iconPlayer8.getIconWidth(),
		                                        iconPlayer8.getIconHeight()));
		     boardPlayer9.setBounds(0, 0, iconPlayer9.getIconWidth(),
		                                  iconPlayer9.getIconHeight()); 
		     player.playerUI.rand5_9.setPreferredSize(new Dimension(iconPlayer9.getIconWidth(),
		                                        iconPlayer9.getIconHeight()));
		     boardPlayer10.setBounds(0, 0, iconPlayer10.getIconWidth(),
		                                  iconPlayer10.getIconHeight()); 
		     player.playerUI.rand5_10.setPreferredSize(new Dimension(iconPlayer10.getIconWidth(),
		                                        iconPlayer10.getIconHeight()));													 													 
		     boardPlayer11.setBounds(0, 0, iconPlayer11.getIconWidth(),
		                                  iconPlayer11.getIconHeight()); 
		     player.playerUI.rand5_11.setPreferredSize(new Dimension(iconPlayer11.getIconWidth(),
		                                        iconPlayer11.getIconHeight()));
		     boardPlayer12.setBounds(0, 0, iconPlayer12.getIconWidth(),
		                                  iconPlayer12.getIconHeight()); 
		     player.playerUI.rand5_12.setPreferredSize(new Dimension(iconPlayer12.getIconWidth(),
		                                        iconPlayer12.getIconHeight()));
		     boardPlayer13.setBounds(0, 0, iconPlayer13.getIconWidth(),
		                                  iconPlayer13.getIconHeight()); 
		     player.playerUI.rand5_13.setPreferredSize(new Dimension(iconPlayer13.getIconWidth(),
		                                        iconPlayer13.getIconHeight()));
		     boardPlayer14.setBounds(0, 0, iconPlayer14.getIconWidth(),
		                                  iconPlayer14.getIconHeight()); 
		     player.playerUI.rand5_14.setPreferredSize(new Dimension(iconPlayer14.getIconWidth(),
		                                        iconPlayer14.getIconHeight()));
		     boardPlayer15.setBounds(0, 0, iconPlayer15.getIconWidth(),
		                                  iconPlayer15.getIconHeight()); 
		     player.playerUI.rand5_15.setPreferredSize(new Dimension(iconPlayer15.getIconWidth(),
		                                        iconPlayer15.getIconHeight()));	
		     boardPlayer16.setBounds(0, 0, iconPlayer16.getIconWidth(),
		                                  iconPlayer16.getIconHeight()); 
		     player.playerUI.rand5_16.setPreferredSize(new Dimension(iconPlayer16.getIconWidth(),
		                                        iconPlayer16.getIconHeight()));													 												 													 
		
			  player.playerUI.mypanel7.add(player.playerUI.rand5_1);
		     player.playerUI.mypanel7.add(player.playerUI.rand5_2);
		     player.playerUI.mypanel7.add(player.playerUI.rand5_3);
		     player.playerUI.mypanel7.add(player.playerUI.rand5_4);
		     player.playerUI.mypanel7.add(player.playerUI.rand5_5);
		     player.playerUI.mypanel7.add(player.playerUI.rand5_6);
		     player.playerUI.mypanel7.add(player.playerUI.rand5_7);
		     player.playerUI.mypanel7.add(player.playerUI.rand5_8);
		     player.playerUI.mypanel7.add(player.playerUI.rand5_9);
		     player.playerUI.mypanel7.add(player.playerUI.rand5_10);
		     player.playerUI.mypanel7.add(player.playerUI.rand5_11);
		     player.playerUI.mypanel7.add(player.playerUI.rand5_12);
		     player.playerUI.mypanel7.add(player.playerUI.rand5_13);
		     player.playerUI.mypanel7.add(player.playerUI.rand5_14);
		     player.playerUI.mypanel7.add(player.playerUI.rand5_15);
		     player.playerUI.mypanel7.add(player.playerUI.rand5_16);
		  
		  }
		}

      private void populateRand6(int tile, Player player)
		{
		  if(tile == 0)
		  {
			  iconPlayer1 =  (bp[0][0].getIcon());
	        iconPlayer2 =  (bp[1][0].getIcon());
 	        iconPlayer3 =  (bp[2][0].getIcon());
	        iconPlayer4 =  (bp[3][0].getIcon());
	        iconPlayer5 =  (bp[0][1].getIcon());
	        iconPlayer6 =  (bp[1][1].getIcon());
	        iconPlayer7 =  (bp[2][1].getIcon());
	        iconPlayer8 =  (bp[3][1].getIcon());
	        iconPlayer9 =  (bp[0][2].getIcon());
	        iconPlayer10 =  (bp[1][2].getIcon());
	        iconPlayer11 =  (bp[2][2].getIcon());
	        iconPlayer12 =  (bp[3][2].getIcon());
	        iconPlayer13 =  (bp[0][3].getIcon());
	        iconPlayer14 =  (bp[1][3].getIcon());
	        iconPlayer15 =  (bp[2][3].getIcon());
	        iconPlayer16 =  (bp[3][3].getIcon());

	
		//add components to board
			  GameJLabel boardPlayer1 = new GameJLabel(iconPlayer1);
			  GameJLabel boardPlayer2 = new GameJLabel(iconPlayer2);
			  GameJLabel boardPlayer3 = new GameJLabel(iconPlayer3);
			  GameJLabel boardPlayer4 = new GameJLabel(iconPlayer4);
			  GameJLabel boardPlayer5 = new GameJLabel(iconPlayer5);
			  GameJLabel boardPlayer6 = new GameJLabel(iconPlayer6);
			  GameJLabel boardPlayer7 = new GameJLabel(iconPlayer7);
			  GameJLabel boardPlayer8 = new GameJLabel(iconPlayer8);
			  GameJLabel boardPlayer9 = new GameJLabel(iconPlayer9);
			  GameJLabel boardPlayer10 = new GameJLabel(iconPlayer10);
			  GameJLabel boardPlayer11 = new GameJLabel(iconPlayer11);
			  GameJLabel boardPlayer12 = new GameJLabel(iconPlayer12);
			  GameJLabel boardPlayer13 = new GameJLabel(iconPlayer13);
			  GameJLabel boardPlayer14 = new GameJLabel(iconPlayer14);
			  GameJLabel boardPlayer15 = new GameJLabel(iconPlayer15);
			  GameJLabel boardPlayer16 = new GameJLabel(iconPlayer16);
		
			  player.playerUI.rand6_1.add(boardPlayer1, new Integer(0));
		     player.playerUI.rand6_2.add(boardPlayer2, new Integer(0));
		     player.playerUI.rand6_3.add(boardPlayer3, new Integer(0));
		     player.playerUI.rand6_4.add(boardPlayer4, new Integer(0));
		     player.playerUI.rand6_5.add(boardPlayer5, new Integer(0));
		     player.playerUI.rand6_6.add(boardPlayer6, new Integer(0));
		     player.playerUI.rand6_7.add(boardPlayer7, new Integer(0));
		     player.playerUI.rand6_8.add(boardPlayer8, new Integer(0));
		     player.playerUI.rand6_9.add(boardPlayer9, new Integer(0));
		     player.playerUI.rand6_10.add(boardPlayer10, new Integer(0));
		     player.playerUI.rand6_11.add(boardPlayer11, new Integer(0));
		     player.playerUI.rand6_12.add(boardPlayer12, new Integer(0));
		     player.playerUI.rand6_13.add(boardPlayer13, new Integer(0));
		     player.playerUI.rand6_14.add(boardPlayer14, new Integer(0));
		     player.playerUI.rand6_15.add(boardPlayer15, new Integer(0));
		     player.playerUI.rand6_16.add(boardPlayer16, new Integer(0));
		
		     boardPlayer1.setBounds(0, 0, iconPlayer1.getIconWidth(),
		                                  iconPlayer1.getIconHeight()); 
		     player.playerUI.rand6_1.setPreferredSize(new Dimension(iconPlayer1.getIconWidth(),
		                                        iconPlayer1.getIconHeight()));
		     boardPlayer2.setBounds(0, 0, iconPlayer2.getIconWidth(),
		                                  iconPlayer2.getIconHeight()); 
		     player.playerUI.rand6_2.setPreferredSize(new Dimension(iconPlayer2.getIconWidth(),
		                                        iconPlayer2.getIconHeight()));
		     boardPlayer3.setBounds(0, 0, iconPlayer3.getIconWidth(),
		                                  iconPlayer3.getIconHeight()); 
		     player.playerUI.rand6_3.setPreferredSize(new Dimension(iconPlayer3.getIconWidth(),
		                                        iconPlayer3.getIconHeight()));
		     boardPlayer4.setBounds(0, 0, iconPlayer4.getIconWidth(),
		                                  iconPlayer4.getIconHeight()); 
		     player.playerUI.rand6_4.setPreferredSize(new Dimension(iconPlayer4.getIconWidth(),
		                                        iconPlayer4.getIconHeight()));
		     boardPlayer5.setBounds(0, 0, iconPlayer5.getIconWidth(),
		                                  iconPlayer5.getIconHeight()); 
		     player.playerUI.rand6_5.setPreferredSize(new Dimension(iconPlayer5.getIconWidth(),
		                                        iconPlayer5.getIconHeight()));
		     boardPlayer6.setBounds(0, 0, iconPlayer6.getIconWidth(),
		                                  iconPlayer6.getIconHeight()); 
		     player.playerUI.rand6_6.setPreferredSize(new Dimension(iconPlayer6.getIconWidth(),
		                                        iconPlayer6.getIconHeight()));
		     boardPlayer7.setBounds(0, 0, iconPlayer7.getIconWidth(),
		                                  iconPlayer7.getIconHeight()); 
		     player.playerUI.rand6_7.setPreferredSize(new Dimension(iconPlayer7.getIconWidth(),
		                                        iconPlayer7.getIconHeight()));
		     boardPlayer8.setBounds(0, 0, iconPlayer8.getIconWidth(),
		                                  iconPlayer8.getIconHeight()); 
		     player.playerUI.rand6_8.setPreferredSize(new Dimension(iconPlayer8.getIconWidth(),
		                                        iconPlayer8.getIconHeight()));
		     boardPlayer9.setBounds(0, 0, iconPlayer9.getIconWidth(),
		                                  iconPlayer9.getIconHeight()); 
		     player.playerUI.rand6_9.setPreferredSize(new Dimension(iconPlayer9.getIconWidth(),
		                                        iconPlayer9.getIconHeight()));
		     boardPlayer10.setBounds(0, 0, iconPlayer10.getIconWidth(),
		                                  iconPlayer10.getIconHeight()); 
		     player.playerUI.rand6_10.setPreferredSize(new Dimension(iconPlayer10.getIconWidth(),
		                                        iconPlayer10.getIconHeight()));													 													 
		     boardPlayer11.setBounds(0, 0, iconPlayer11.getIconWidth(),
		                                  iconPlayer11.getIconHeight()); 
		     player.playerUI.rand6_11.setPreferredSize(new Dimension(iconPlayer11.getIconWidth(),
		                                        iconPlayer11.getIconHeight()));
		     boardPlayer12.setBounds(0, 0, iconPlayer12.getIconWidth(),
		                                  iconPlayer12.getIconHeight()); 
		     player.playerUI.rand6_12.setPreferredSize(new Dimension(iconPlayer12.getIconWidth(),
		                                        iconPlayer12.getIconHeight()));
		     boardPlayer13.setBounds(0, 0, iconPlayer13.getIconWidth(),
		                                  iconPlayer13.getIconHeight()); 
		     player.playerUI.rand6_13.setPreferredSize(new Dimension(iconPlayer13.getIconWidth(),
		                                        iconPlayer13.getIconHeight()));
		     boardPlayer14.setBounds(0, 0, iconPlayer14.getIconWidth(),
		                                  iconPlayer14.getIconHeight()); 
		     player.playerUI.rand6_14.setPreferredSize(new Dimension(iconPlayer14.getIconWidth(),
		                                        iconPlayer14.getIconHeight()));
		     boardPlayer15.setBounds(0, 0, iconPlayer15.getIconWidth(),
		                                  iconPlayer15.getIconHeight()); 
		     player.playerUI.rand6_15.setPreferredSize(new Dimension(iconPlayer15.getIconWidth(),
		                                        iconPlayer15.getIconHeight()));	
		     boardPlayer16.setBounds(0, 0, iconPlayer16.getIconWidth(),
		                                  iconPlayer16.getIconHeight()); 
		     player.playerUI.rand6_16.setPreferredSize(new Dimension(iconPlayer16.getIconWidth(),
		                                        iconPlayer16.getIconHeight()));													 												 													 
		
			  player.playerUI.mypanel0.add(player.playerUI.rand6_1);
		     player.playerUI.mypanel0.add(player.playerUI.rand6_2);
		     player.playerUI.mypanel0.add(player.playerUI.rand6_3);
		     player.playerUI.mypanel0.add(player.playerUI.rand6_4);
		     player.playerUI.mypanel0.add(player.playerUI.rand6_5);
		     player.playerUI.mypanel0.add(player.playerUI.rand6_6);
		     player.playerUI.mypanel0.add(player.playerUI.rand6_7);
		     player.playerUI.mypanel0.add(player.playerUI.rand6_8);
		     player.playerUI.mypanel0.add(player.playerUI.rand6_9);
		     player.playerUI.mypanel0.add(player.playerUI.rand6_10);
		     player.playerUI.mypanel0.add(player.playerUI.rand6_11);
		     player.playerUI.mypanel0.add(player.playerUI.rand6_12);
		     player.playerUI.mypanel0.add(player.playerUI.rand6_13);
		     player.playerUI.mypanel0.add(player.playerUI.rand6_14);
		     player.playerUI.mypanel0.add(player.playerUI.rand6_15);
		     player.playerUI.mypanel0.add(player.playerUI.rand6_16);
		  }
		  else if(tile == 1)
		  {
  	        iconPlayer1 =  (bp[0][4].getIcon());
	        iconPlayer2 =  (bp[1][4].getIcon());
 	        iconPlayer3 =  (bp[2][4].getIcon());
	        iconPlayer4 =  (bp[3][4].getIcon());
	        iconPlayer5 =  (bp[0][5].getIcon());
	        iconPlayer6 =  (bp[1][5].getIcon());
	        iconPlayer7 =  (bp[2][5].getIcon());
	        iconPlayer8 =  (bp[3][5].getIcon());
	        iconPlayer9 =  (bp[0][6].getIcon());
	        iconPlayer10 =  (bp[1][6].getIcon());
	        iconPlayer11 =  (bp[2][6].getIcon());
	        iconPlayer12 =  (bp[3][6].getIcon());
	        iconPlayer13 =  (bp[0][7].getIcon());
	        iconPlayer14 =  (bp[1][7].getIcon());
	        iconPlayer15 =  (bp[2][7].getIcon());
	        iconPlayer16 =  (bp[3][7].getIcon());

	
		//add components to board
			  GameJLabel boardPlayer1 = new GameJLabel(iconPlayer1);
			  GameJLabel boardPlayer2 = new GameJLabel(iconPlayer2);
			  GameJLabel boardPlayer3 = new GameJLabel(iconPlayer3);
			  GameJLabel boardPlayer4 = new GameJLabel(iconPlayer4);
			  GameJLabel boardPlayer5 = new GameJLabel(iconPlayer5);
			  GameJLabel boardPlayer6 = new GameJLabel(iconPlayer6);
			  GameJLabel boardPlayer7 = new GameJLabel(iconPlayer7);
			  GameJLabel boardPlayer8 = new GameJLabel(iconPlayer8);
			  GameJLabel boardPlayer9 = new GameJLabel(iconPlayer9);
			  GameJLabel boardPlayer10 = new GameJLabel(iconPlayer10);
			  GameJLabel boardPlayer11 = new GameJLabel(iconPlayer11);
			  GameJLabel boardPlayer12 = new GameJLabel(iconPlayer12);
			  GameJLabel boardPlayer13 = new GameJLabel(iconPlayer13);
			  GameJLabel boardPlayer14 = new GameJLabel(iconPlayer14);
			  GameJLabel boardPlayer15 = new GameJLabel(iconPlayer15);
			  GameJLabel boardPlayer16 = new GameJLabel(iconPlayer16);
		
			  player.playerUI.rand6_1.add(boardPlayer1, new Integer(0));
		     player.playerUI.rand6_2.add(boardPlayer2, new Integer(0));
		     player.playerUI.rand6_3.add(boardPlayer3, new Integer(0));
		     player.playerUI.rand6_4.add(boardPlayer4, new Integer(0));
		     player.playerUI.rand6_5.add(boardPlayer5, new Integer(0));
		     player.playerUI.rand6_6.add(boardPlayer6, new Integer(0));
		     player.playerUI.rand6_7.add(boardPlayer7, new Integer(0));
		     player.playerUI.rand6_8.add(boardPlayer8, new Integer(0));
		     player.playerUI.rand6_9.add(boardPlayer9, new Integer(0));
		     player.playerUI.rand6_10.add(boardPlayer10, new Integer(0));
		     player.playerUI.rand6_11.add(boardPlayer11, new Integer(0));
		     player.playerUI.rand6_12.add(boardPlayer12, new Integer(0));
		     player.playerUI.rand6_13.add(boardPlayer13, new Integer(0));
		     player.playerUI.rand6_14.add(boardPlayer14, new Integer(0));
		     player.playerUI.rand6_15.add(boardPlayer15, new Integer(0));
		     player.playerUI.rand6_16.add(boardPlayer16, new Integer(0));
		
		     boardPlayer1.setBounds(0, 0, iconPlayer1.getIconWidth(),
		                                  iconPlayer1.getIconHeight()); 
		     player.playerUI.rand6_1.setPreferredSize(new Dimension(iconPlayer1.getIconWidth(),
		                                        iconPlayer1.getIconHeight()));
		     boardPlayer2.setBounds(0, 0, iconPlayer2.getIconWidth(),
		                                  iconPlayer2.getIconHeight()); 
		     player.playerUI.rand6_2.setPreferredSize(new Dimension(iconPlayer2.getIconWidth(),
		                                        iconPlayer2.getIconHeight()));
		     boardPlayer3.setBounds(0, 0, iconPlayer3.getIconWidth(),
		                                  iconPlayer3.getIconHeight()); 
		     player.playerUI.rand6_3.setPreferredSize(new Dimension(iconPlayer3.getIconWidth(),
		                                        iconPlayer3.getIconHeight()));
		     boardPlayer4.setBounds(0, 0, iconPlayer4.getIconWidth(),
		                                  iconPlayer4.getIconHeight()); 
		     player.playerUI.rand6_4.setPreferredSize(new Dimension(iconPlayer4.getIconWidth(),
		                                        iconPlayer4.getIconHeight()));
		     boardPlayer5.setBounds(0, 0, iconPlayer5.getIconWidth(),
		                                  iconPlayer5.getIconHeight()); 
		     player.playerUI.rand6_5.setPreferredSize(new Dimension(iconPlayer5.getIconWidth(),
		                                        iconPlayer5.getIconHeight()));
		     boardPlayer6.setBounds(0, 0, iconPlayer6.getIconWidth(),
		                                  iconPlayer6.getIconHeight()); 
		     player.playerUI.rand6_6.setPreferredSize(new Dimension(iconPlayer6.getIconWidth(),
		                                        iconPlayer6.getIconHeight()));
		     boardPlayer7.setBounds(0, 0, iconPlayer7.getIconWidth(),
		                                  iconPlayer7.getIconHeight()); 
		     player.playerUI.rand6_7.setPreferredSize(new Dimension(iconPlayer7.getIconWidth(),
		                                        iconPlayer7.getIconHeight()));
		     boardPlayer8.setBounds(0, 0, iconPlayer8.getIconWidth(),
		                                  iconPlayer8.getIconHeight()); 
		     player.playerUI.rand6_8.setPreferredSize(new Dimension(iconPlayer8.getIconWidth(),
		                                        iconPlayer8.getIconHeight()));
		     boardPlayer9.setBounds(0, 0, iconPlayer9.getIconWidth(),
		                                  iconPlayer9.getIconHeight()); 
		     player.playerUI.rand6_9.setPreferredSize(new Dimension(iconPlayer9.getIconWidth(),
		                                        iconPlayer9.getIconHeight()));
		     boardPlayer10.setBounds(0, 0, iconPlayer10.getIconWidth(),
		                                  iconPlayer10.getIconHeight()); 
		     player.playerUI.rand6_10.setPreferredSize(new Dimension(iconPlayer10.getIconWidth(),
		                                        iconPlayer10.getIconHeight()));													 													 
		     boardPlayer11.setBounds(0, 0, iconPlayer11.getIconWidth(),
		                                  iconPlayer11.getIconHeight()); 
		     player.playerUI.rand6_11.setPreferredSize(new Dimension(iconPlayer11.getIconWidth(),
		                                        iconPlayer11.getIconHeight()));
		     boardPlayer12.setBounds(0, 0, iconPlayer12.getIconWidth(),
		                                  iconPlayer12.getIconHeight()); 
		     player.playerUI.rand6_12.setPreferredSize(new Dimension(iconPlayer12.getIconWidth(),
		                                        iconPlayer12.getIconHeight()));
		     boardPlayer13.setBounds(0, 0, iconPlayer13.getIconWidth(),
		                                  iconPlayer13.getIconHeight()); 
		     player.playerUI.rand6_13.setPreferredSize(new Dimension(iconPlayer13.getIconWidth(),
		                                        iconPlayer13.getIconHeight()));
		     boardPlayer14.setBounds(0, 0, iconPlayer14.getIconWidth(),
		                                  iconPlayer14.getIconHeight()); 
		     player.playerUI.rand6_14.setPreferredSize(new Dimension(iconPlayer14.getIconWidth(),
		                                        iconPlayer14.getIconHeight()));
		     boardPlayer15.setBounds(0, 0, iconPlayer15.getIconWidth(),
		                                  iconPlayer15.getIconHeight()); 
		     player.playerUI.rand6_15.setPreferredSize(new Dimension(iconPlayer15.getIconWidth(),
		                                        iconPlayer15.getIconHeight()));	
		     boardPlayer16.setBounds(0, 0, iconPlayer16.getIconWidth(),
		                                  iconPlayer16.getIconHeight()); 
		     player.playerUI.rand6_16.setPreferredSize(new Dimension(iconPlayer16.getIconWidth(),
		                                        iconPlayer16.getIconHeight()));													 												 													 
		
			  player.playerUI.mypanel1.add(player.playerUI.rand6_1);
		     player.playerUI.mypanel1.add(player.playerUI.rand6_2);
		     player.playerUI.mypanel1.add(player.playerUI.rand6_3);
		     player.playerUI.mypanel1.add(player.playerUI.rand6_4);
		     player.playerUI.mypanel1.add(player.playerUI.rand6_5);
		     player.playerUI.mypanel1.add(player.playerUI.rand6_6);
		     player.playerUI.mypanel1.add(player.playerUI.rand6_7);
		     player.playerUI.mypanel1.add(player.playerUI.rand6_8);
		     player.playerUI.mypanel1.add(player.playerUI.rand6_9);
		     player.playerUI.mypanel1.add(player.playerUI.rand6_10);
		     player.playerUI.mypanel1.add(player.playerUI.rand6_11);
		     player.playerUI.mypanel1.add(player.playerUI.rand6_12);
		     player.playerUI.mypanel1.add(player.playerUI.rand6_13);
		     player.playerUI.mypanel1.add(player.playerUI.rand6_14);
		     player.playerUI.mypanel1.add(player.playerUI.rand6_15);
		     player.playerUI.mypanel1.add(player.playerUI.rand6_16);
		  
		  }
		  else if(tile == 2)
		  {
  	        iconPlayer1 =  (bp[0][8].getIcon());
	        iconPlayer2 =  (bp[1][8].getIcon());
 	        iconPlayer3 =  (bp[2][8].getIcon());
	        iconPlayer4 =  (bp[3][8].getIcon());
	        iconPlayer5 =  (bp[0][9].getIcon());
	        iconPlayer6 =  (bp[1][9].getIcon());
	        iconPlayer7 =  (bp[2][9].getIcon());
	        iconPlayer8 =  (bp[3][9].getIcon());
	        iconPlayer9 =  (bp[0][10].getIcon());
	        iconPlayer10 =  (bp[1][10].getIcon());
	        iconPlayer11 =  (bp[2][10].getIcon());
	        iconPlayer12 =  (bp[3][10].getIcon());
	        iconPlayer13 =  (bp[0][11].getIcon());
	        iconPlayer14 =  (bp[1][11].getIcon());
	        iconPlayer15 =  (bp[2][11].getIcon());
	        iconPlayer16 =  (bp[3][11].getIcon());

	
		//add components to board
			  GameJLabel boardPlayer1 = new GameJLabel(iconPlayer1);
			  GameJLabel boardPlayer2 = new GameJLabel(iconPlayer2);
			  GameJLabel boardPlayer3 = new GameJLabel(iconPlayer3);
			  GameJLabel boardPlayer4 = new GameJLabel(iconPlayer4);
			  GameJLabel boardPlayer5 = new GameJLabel(iconPlayer5);
			  GameJLabel boardPlayer6 = new GameJLabel(iconPlayer6);
			  GameJLabel boardPlayer7 = new GameJLabel(iconPlayer7);
			  GameJLabel boardPlayer8 = new GameJLabel(iconPlayer8);
			  GameJLabel boardPlayer9 = new GameJLabel(iconPlayer9);
			  GameJLabel boardPlayer10 = new GameJLabel(iconPlayer10);
			  GameJLabel boardPlayer11 = new GameJLabel(iconPlayer11);
			  GameJLabel boardPlayer12 = new GameJLabel(iconPlayer12);
			  GameJLabel boardPlayer13 = new GameJLabel(iconPlayer13);
			  GameJLabel boardPlayer14 = new GameJLabel(iconPlayer14);
			  GameJLabel boardPlayer15 = new GameJLabel(iconPlayer15);
			  GameJLabel boardPlayer16 = new GameJLabel(iconPlayer16);
		
			  player.playerUI.rand6_1.add(boardPlayer1, new Integer(0));
		     player.playerUI.rand6_2.add(boardPlayer2, new Integer(0));
		     player.playerUI.rand6_3.add(boardPlayer3, new Integer(0));
		     player.playerUI.rand6_4.add(boardPlayer4, new Integer(0));
		     player.playerUI.rand6_5.add(boardPlayer5, new Integer(0));
		     player.playerUI.rand6_6.add(boardPlayer6, new Integer(0));
		     player.playerUI.rand6_7.add(boardPlayer7, new Integer(0));
		     player.playerUI.rand6_8.add(boardPlayer8, new Integer(0));
		     player.playerUI.rand6_9.add(boardPlayer9, new Integer(0));
		     player.playerUI.rand6_10.add(boardPlayer10, new Integer(0));
		     player.playerUI.rand6_11.add(boardPlayer11, new Integer(0));
		     player.playerUI.rand6_12.add(boardPlayer12, new Integer(0));
		     player.playerUI.rand6_13.add(boardPlayer13, new Integer(0));
		     player.playerUI.rand6_14.add(boardPlayer14, new Integer(0));
		     player.playerUI.rand6_15.add(boardPlayer15, new Integer(0));
		     player.playerUI.rand6_16.add(boardPlayer16, new Integer(0));
		
		     boardPlayer1.setBounds(0, 0, iconPlayer1.getIconWidth(),
		                                  iconPlayer1.getIconHeight()); 
		     player.playerUI.rand6_1.setPreferredSize(new Dimension(iconPlayer1.getIconWidth(),
		                                        iconPlayer1.getIconHeight()));
		     boardPlayer2.setBounds(0, 0, iconPlayer2.getIconWidth(),
		                                  iconPlayer2.getIconHeight()); 
		     player.playerUI.rand6_2.setPreferredSize(new Dimension(iconPlayer2.getIconWidth(),
		                                        iconPlayer2.getIconHeight()));
		     boardPlayer3.setBounds(0, 0, iconPlayer3.getIconWidth(),
		                                  iconPlayer3.getIconHeight()); 
		     player.playerUI.rand6_3.setPreferredSize(new Dimension(iconPlayer3.getIconWidth(),
		                                        iconPlayer3.getIconHeight()));
		     boardPlayer4.setBounds(0, 0, iconPlayer4.getIconWidth(),
		                                  iconPlayer4.getIconHeight()); 
		     player.playerUI.rand6_4.setPreferredSize(new Dimension(iconPlayer4.getIconWidth(),
		                                        iconPlayer4.getIconHeight()));
		     boardPlayer5.setBounds(0, 0, iconPlayer5.getIconWidth(),
		                                  iconPlayer5.getIconHeight()); 
		     player.playerUI.rand6_5.setPreferredSize(new Dimension(iconPlayer5.getIconWidth(),
		                                        iconPlayer5.getIconHeight()));
		     boardPlayer6.setBounds(0, 0, iconPlayer6.getIconWidth(),
		                                  iconPlayer6.getIconHeight()); 
		     player.playerUI.rand6_6.setPreferredSize(new Dimension(iconPlayer6.getIconWidth(),
		                                        iconPlayer6.getIconHeight()));
		     boardPlayer7.setBounds(0, 0, iconPlayer7.getIconWidth(),
		                                  iconPlayer7.getIconHeight()); 
		     player.playerUI.rand6_7.setPreferredSize(new Dimension(iconPlayer7.getIconWidth(),
		                                        iconPlayer7.getIconHeight()));
		     boardPlayer8.setBounds(0, 0, iconPlayer8.getIconWidth(),
		                                  iconPlayer8.getIconHeight()); 
		     player.playerUI.rand6_8.setPreferredSize(new Dimension(iconPlayer8.getIconWidth(),
		                                        iconPlayer8.getIconHeight()));
		     boardPlayer9.setBounds(0, 0, iconPlayer9.getIconWidth(),
		                                  iconPlayer9.getIconHeight()); 
		     player.playerUI.rand6_9.setPreferredSize(new Dimension(iconPlayer9.getIconWidth(),
		                                        iconPlayer9.getIconHeight()));
		     boardPlayer10.setBounds(0, 0, iconPlayer10.getIconWidth(),
		                                  iconPlayer10.getIconHeight()); 
		     player.playerUI.rand6_10.setPreferredSize(new Dimension(iconPlayer10.getIconWidth(),
		                                        iconPlayer10.getIconHeight()));													 													 
		     boardPlayer11.setBounds(0, 0, iconPlayer11.getIconWidth(),
		                                  iconPlayer11.getIconHeight()); 
		     player.playerUI.rand6_11.setPreferredSize(new Dimension(iconPlayer11.getIconWidth(),
		                                        iconPlayer11.getIconHeight()));
		     boardPlayer12.setBounds(0, 0, iconPlayer12.getIconWidth(),
		                                  iconPlayer12.getIconHeight()); 
		     player.playerUI.rand6_12.setPreferredSize(new Dimension(iconPlayer12.getIconWidth(),
		                                        iconPlayer12.getIconHeight()));
		     boardPlayer13.setBounds(0, 0, iconPlayer13.getIconWidth(),
		                                  iconPlayer13.getIconHeight()); 
		     player.playerUI.rand6_13.setPreferredSize(new Dimension(iconPlayer13.getIconWidth(),
		                                        iconPlayer13.getIconHeight()));
		     boardPlayer14.setBounds(0, 0, iconPlayer14.getIconWidth(),
		                                  iconPlayer14.getIconHeight()); 
		     player.playerUI.rand6_14.setPreferredSize(new Dimension(iconPlayer14.getIconWidth(),
		                                        iconPlayer14.getIconHeight()));
		     boardPlayer15.setBounds(0, 0, iconPlayer15.getIconWidth(),
		                                  iconPlayer15.getIconHeight()); 
		     player.playerUI.rand6_15.setPreferredSize(new Dimension(iconPlayer15.getIconWidth(),
		                                        iconPlayer15.getIconHeight()));	
		     boardPlayer16.setBounds(0, 0, iconPlayer16.getIconWidth(),
		                                  iconPlayer16.getIconHeight()); 
		     player.playerUI.rand6_16.setPreferredSize(new Dimension(iconPlayer16.getIconWidth(),
		                                        iconPlayer16.getIconHeight()));													 												 													 
		
			  player.playerUI.mypanel2.add(player.playerUI.rand6_1);
		     player.playerUI.mypanel2.add(player.playerUI.rand6_2);
		     player.playerUI.mypanel2.add(player.playerUI.rand6_3);
		     player.playerUI.mypanel2.add(player.playerUI.rand6_4);
		     player.playerUI.mypanel2.add(player.playerUI.rand6_5);
		     player.playerUI.mypanel2.add(player.playerUI.rand6_6);
		     player.playerUI.mypanel2.add(player.playerUI.rand6_7);
		     player.playerUI.mypanel2.add(player.playerUI.rand6_8);
		     player.playerUI.mypanel2.add(player.playerUI.rand6_9);
		     player.playerUI.mypanel2.add(player.playerUI.rand6_10);
		     player.playerUI.mypanel2.add(player.playerUI.rand6_11);
		     player.playerUI.mypanel2.add(player.playerUI.rand6_12);
		     player.playerUI.mypanel2.add(player.playerUI.rand6_13);
		     player.playerUI.mypanel2.add(player.playerUI.rand6_14);
		     player.playerUI.mypanel2.add(player.playerUI.rand6_15);
		     player.playerUI.mypanel2.add(player.playerUI.rand6_16);
		  
		  }
		  else if(tile == 5)
		  {
  	        iconPlayer1 =  (bp[4][4].getIcon());
	        iconPlayer2 =  (bp[5][4].getIcon());
 	        iconPlayer3 =  (bp[6][4].getIcon());
	        iconPlayer4 =  (bp[7][4].getIcon());
	        iconPlayer5 =  (bp[4][5].getIcon());
	        iconPlayer6 =  (bp[5][5].getIcon());
	        iconPlayer7 =  (bp[6][5].getIcon());
	        iconPlayer8 =  (bp[7][5].getIcon());
	        iconPlayer9 =  (bp[4][6].getIcon());
	        iconPlayer10 =  (bp[5][6].getIcon());
	        iconPlayer11 =  (bp[6][6].getIcon());
	        iconPlayer12 =  (bp[7][6].getIcon());
	        iconPlayer13 =  (bp[4][7].getIcon());
	        iconPlayer14 =  (bp[5][7].getIcon());
	        iconPlayer15 =  (bp[6][7].getIcon());
	        iconPlayer16 =  (bp[7][7].getIcon());

	
		//add components to board
			  GameJLabel boardPlayer1 = new GameJLabel(iconPlayer1);
			  GameJLabel boardPlayer2 = new GameJLabel(iconPlayer2);
			  GameJLabel boardPlayer3 = new GameJLabel(iconPlayer3);
			  GameJLabel boardPlayer4 = new GameJLabel(iconPlayer4);
			  GameJLabel boardPlayer5 = new GameJLabel(iconPlayer5);
			  GameJLabel boardPlayer6 = new GameJLabel(iconPlayer6);
			  GameJLabel boardPlayer7 = new GameJLabel(iconPlayer7);
			  GameJLabel boardPlayer8 = new GameJLabel(iconPlayer8);
			  GameJLabel boardPlayer9 = new GameJLabel(iconPlayer9);
			  GameJLabel boardPlayer10 = new GameJLabel(iconPlayer10);
			  GameJLabel boardPlayer11 = new GameJLabel(iconPlayer11);
			  GameJLabel boardPlayer12 = new GameJLabel(iconPlayer12);
			  GameJLabel boardPlayer13 = new GameJLabel(iconPlayer13);
			  GameJLabel boardPlayer14 = new GameJLabel(iconPlayer14);
			  GameJLabel boardPlayer15 = new GameJLabel(iconPlayer15);
			  GameJLabel boardPlayer16 = new GameJLabel(iconPlayer16);
		
			  player.playerUI.rand6_1.add(boardPlayer1, new Integer(0));
		     player.playerUI.rand6_2.add(boardPlayer2, new Integer(0));
		     player.playerUI.rand6_3.add(boardPlayer3, new Integer(0));
		     player.playerUI.rand6_4.add(boardPlayer4, new Integer(0));
		     player.playerUI.rand6_5.add(boardPlayer5, new Integer(0));
		     player.playerUI.rand6_6.add(boardPlayer6, new Integer(0));
		     player.playerUI.rand6_7.add(boardPlayer7, new Integer(0));
		     player.playerUI.rand6_8.add(boardPlayer8, new Integer(0));
		     player.playerUI.rand6_9.add(boardPlayer9, new Integer(0));
		     player.playerUI.rand6_10.add(boardPlayer10, new Integer(0));
		     player.playerUI.rand6_11.add(boardPlayer11, new Integer(0));
		     player.playerUI.rand6_12.add(boardPlayer12, new Integer(0));
		     player.playerUI.rand6_13.add(boardPlayer13, new Integer(0));
		     player.playerUI.rand6_14.add(boardPlayer14, new Integer(0));
		     player.playerUI.rand6_15.add(boardPlayer15, new Integer(0));
		     player.playerUI.rand6_16.add(boardPlayer16, new Integer(0));
		
		     boardPlayer1.setBounds(0, 0, iconPlayer1.getIconWidth(),
		                                  iconPlayer1.getIconHeight()); 
		     player.playerUI.rand6_1.setPreferredSize(new Dimension(iconPlayer1.getIconWidth(),
		                                        iconPlayer1.getIconHeight()));
		     boardPlayer2.setBounds(0, 0, iconPlayer2.getIconWidth(),
		                                  iconPlayer2.getIconHeight()); 
		     player.playerUI.rand6_2.setPreferredSize(new Dimension(iconPlayer2.getIconWidth(),
		                                        iconPlayer2.getIconHeight()));
		     boardPlayer3.setBounds(0, 0, iconPlayer3.getIconWidth(),
		                                  iconPlayer3.getIconHeight()); 
		     player.playerUI.rand6_3.setPreferredSize(new Dimension(iconPlayer3.getIconWidth(),
		                                        iconPlayer3.getIconHeight()));
		     boardPlayer4.setBounds(0, 0, iconPlayer4.getIconWidth(),
		                                  iconPlayer4.getIconHeight()); 
		     player.playerUI.rand6_4.setPreferredSize(new Dimension(iconPlayer4.getIconWidth(),
		                                        iconPlayer4.getIconHeight()));
		     boardPlayer5.setBounds(0, 0, iconPlayer5.getIconWidth(),
		                                  iconPlayer5.getIconHeight()); 
		     player.playerUI.rand6_5.setPreferredSize(new Dimension(iconPlayer5.getIconWidth(),
		                                        iconPlayer5.getIconHeight()));
		     boardPlayer6.setBounds(0, 0, iconPlayer6.getIconWidth(),
		                                  iconPlayer6.getIconHeight()); 
		     player.playerUI.rand6_6.setPreferredSize(new Dimension(iconPlayer6.getIconWidth(),
		                                        iconPlayer6.getIconHeight()));
		     boardPlayer7.setBounds(0, 0, iconPlayer7.getIconWidth(),
		                                  iconPlayer7.getIconHeight()); 
		     player.playerUI.rand6_7.setPreferredSize(new Dimension(iconPlayer7.getIconWidth(),
		                                        iconPlayer7.getIconHeight()));
		     boardPlayer8.setBounds(0, 0, iconPlayer8.getIconWidth(),
		                                  iconPlayer8.getIconHeight()); 
		     player.playerUI.rand6_8.setPreferredSize(new Dimension(iconPlayer8.getIconWidth(),
		                                        iconPlayer8.getIconHeight()));
		     boardPlayer9.setBounds(0, 0, iconPlayer9.getIconWidth(),
		                                  iconPlayer9.getIconHeight()); 
		     player.playerUI.rand6_9.setPreferredSize(new Dimension(iconPlayer9.getIconWidth(),
		                                        iconPlayer9.getIconHeight()));
		     boardPlayer10.setBounds(0, 0, iconPlayer10.getIconWidth(),
		                                  iconPlayer10.getIconHeight()); 
		     player.playerUI.rand6_10.setPreferredSize(new Dimension(iconPlayer10.getIconWidth(),
		                                        iconPlayer10.getIconHeight()));													 													 
		     boardPlayer11.setBounds(0, 0, iconPlayer11.getIconWidth(),
		                                  iconPlayer11.getIconHeight()); 
		     player.playerUI.rand6_11.setPreferredSize(new Dimension(iconPlayer11.getIconWidth(),
		                                        iconPlayer11.getIconHeight()));
		     boardPlayer12.setBounds(0, 0, iconPlayer12.getIconWidth(),
		                                  iconPlayer12.getIconHeight()); 
		     player.playerUI.rand6_12.setPreferredSize(new Dimension(iconPlayer12.getIconWidth(),
		                                        iconPlayer12.getIconHeight()));
		     boardPlayer13.setBounds(0, 0, iconPlayer13.getIconWidth(),
		                                  iconPlayer13.getIconHeight()); 
		     player.playerUI.rand6_13.setPreferredSize(new Dimension(iconPlayer13.getIconWidth(),
		                                        iconPlayer13.getIconHeight()));
		     boardPlayer14.setBounds(0, 0, iconPlayer14.getIconWidth(),
		                                  iconPlayer14.getIconHeight()); 
		     player.playerUI.rand6_14.setPreferredSize(new Dimension(iconPlayer14.getIconWidth(),
		                                        iconPlayer14.getIconHeight()));
		     boardPlayer15.setBounds(0, 0, iconPlayer15.getIconWidth(),
		                                  iconPlayer15.getIconHeight()); 
		     player.playerUI.rand6_15.setPreferredSize(new Dimension(iconPlayer15.getIconWidth(),
		                                        iconPlayer15.getIconHeight()));	
		     boardPlayer16.setBounds(0, 0, iconPlayer16.getIconWidth(),
		                                  iconPlayer16.getIconHeight()); 
		     player.playerUI.rand6_16.setPreferredSize(new Dimension(iconPlayer16.getIconWidth(),
		                                        iconPlayer16.getIconHeight()));													 												 													 
		
			  player.playerUI.mypanel5.add(player.playerUI.rand6_1);
		     player.playerUI.mypanel5.add(player.playerUI.rand6_2);
		     player.playerUI.mypanel5.add(player.playerUI.rand6_3);
		     player.playerUI.mypanel5.add(player.playerUI.rand6_4);
		     player.playerUI.mypanel5.add(player.playerUI.rand6_5);
		     player.playerUI.mypanel5.add(player.playerUI.rand6_6);
		     player.playerUI.mypanel5.add(player.playerUI.rand6_7);
		     player.playerUI.mypanel5.add(player.playerUI.rand6_8);
		     player.playerUI.mypanel5.add(player.playerUI.rand6_9);
		     player.playerUI.mypanel5.add(player.playerUI.rand6_10);
		     player.playerUI.mypanel5.add(player.playerUI.rand6_11);
		     player.playerUI.mypanel5.add(player.playerUI.rand6_12);
		     player.playerUI.mypanel5.add(player.playerUI.rand6_13);
		     player.playerUI.mypanel5.add(player.playerUI.rand6_14);
		     player.playerUI.mypanel5.add(player.playerUI.rand6_15);
		     player.playerUI.mypanel5.add(player.playerUI.rand6_16);
		  
		  }
		  else if(tile == 6)
		  {
  	        iconPlayer1 =  (bp[4][8].getIcon());
	        iconPlayer2 =  (bp[5][8].getIcon());
 	        iconPlayer3 =  (bp[6][8].getIcon());
	        iconPlayer4 =  (bp[7][8].getIcon());
	        iconPlayer5 =  (bp[4][9].getIcon());
	        iconPlayer6 =  (bp[5][9].getIcon());
	        iconPlayer7 =  (bp[6][9].getIcon());
	        iconPlayer8 =  (bp[7][9].getIcon());
	        iconPlayer9 =  (bp[4][10].getIcon());
	        iconPlayer10 =  (bp[5][10].getIcon());
	        iconPlayer11 =  (bp[6][10].getIcon());
	        iconPlayer12 =  (bp[7][10].getIcon());
	        iconPlayer13 =  (bp[4][11].getIcon());
	        iconPlayer14 =  (bp[5][11].getIcon());
	        iconPlayer15 =  (bp[6][11].getIcon());
	        iconPlayer16 =  (bp[7][11].getIcon());

	
		//add components to board
			  GameJLabel boardPlayer1 = new GameJLabel(iconPlayer1);
			  GameJLabel boardPlayer2 = new GameJLabel(iconPlayer2);
			  GameJLabel boardPlayer3 = new GameJLabel(iconPlayer3);
			  GameJLabel boardPlayer4 = new GameJLabel(iconPlayer4);
			  GameJLabel boardPlayer5 = new GameJLabel(iconPlayer5);
			  GameJLabel boardPlayer6 = new GameJLabel(iconPlayer6);
			  GameJLabel boardPlayer7 = new GameJLabel(iconPlayer7);
			  GameJLabel boardPlayer8 = new GameJLabel(iconPlayer8);
			  GameJLabel boardPlayer9 = new GameJLabel(iconPlayer9);
			  GameJLabel boardPlayer10 = new GameJLabel(iconPlayer10);
			  GameJLabel boardPlayer11 = new GameJLabel(iconPlayer11);
			  GameJLabel boardPlayer12 = new GameJLabel(iconPlayer12);
			  GameJLabel boardPlayer13 = new GameJLabel(iconPlayer13);
			  GameJLabel boardPlayer14 = new GameJLabel(iconPlayer14);
			  GameJLabel boardPlayer15 = new GameJLabel(iconPlayer15);
			  GameJLabel boardPlayer16 = new GameJLabel(iconPlayer16);
		
			  player.playerUI.rand6_1.add(boardPlayer1, new Integer(0));
		     player.playerUI.rand6_2.add(boardPlayer2, new Integer(0));
		     player.playerUI.rand6_3.add(boardPlayer3, new Integer(0));
		     player.playerUI.rand6_4.add(boardPlayer4, new Integer(0));
		     player.playerUI.rand6_5.add(boardPlayer5, new Integer(0));
		     player.playerUI.rand6_6.add(boardPlayer6, new Integer(0));
		     player.playerUI.rand6_7.add(boardPlayer7, new Integer(0));
		     player.playerUI.rand6_8.add(boardPlayer8, new Integer(0));
		     player.playerUI.rand6_9.add(boardPlayer9, new Integer(0));
		     player.playerUI.rand6_10.add(boardPlayer10, new Integer(0));
		     player.playerUI.rand6_11.add(boardPlayer11, new Integer(0));
		     player.playerUI.rand6_12.add(boardPlayer12, new Integer(0));
		     player.playerUI.rand6_13.add(boardPlayer13, new Integer(0));
		     player.playerUI.rand6_14.add(boardPlayer14, new Integer(0));
		     player.playerUI.rand6_15.add(boardPlayer15, new Integer(0));
		     player.playerUI.rand6_16.add(boardPlayer16, new Integer(0));
		
		     boardPlayer1.setBounds(0, 0, iconPlayer1.getIconWidth(),
		                                  iconPlayer1.getIconHeight()); 
		     player.playerUI.rand6_1.setPreferredSize(new Dimension(iconPlayer1.getIconWidth(),
		                                        iconPlayer1.getIconHeight()));
		     boardPlayer2.setBounds(0, 0, iconPlayer2.getIconWidth(),
		                                  iconPlayer2.getIconHeight()); 
		     player.playerUI.rand6_2.setPreferredSize(new Dimension(iconPlayer2.getIconWidth(),
		                                        iconPlayer2.getIconHeight()));
		     boardPlayer3.setBounds(0, 0, iconPlayer3.getIconWidth(),
		                                  iconPlayer3.getIconHeight()); 
		     player.playerUI.rand6_3.setPreferredSize(new Dimension(iconPlayer3.getIconWidth(),
		                                        iconPlayer3.getIconHeight()));
		     boardPlayer4.setBounds(0, 0, iconPlayer4.getIconWidth(),
		                                  iconPlayer4.getIconHeight()); 
		     player.playerUI.rand6_4.setPreferredSize(new Dimension(iconPlayer4.getIconWidth(),
		                                        iconPlayer4.getIconHeight()));
		     boardPlayer5.setBounds(0, 0, iconPlayer5.getIconWidth(),
		                                  iconPlayer5.getIconHeight()); 
		     player.playerUI.rand6_5.setPreferredSize(new Dimension(iconPlayer5.getIconWidth(),
		                                        iconPlayer5.getIconHeight()));
		     boardPlayer6.setBounds(0, 0, iconPlayer6.getIconWidth(),
		                                  iconPlayer6.getIconHeight()); 
		     player.playerUI.rand6_6.setPreferredSize(new Dimension(iconPlayer6.getIconWidth(),
		                                        iconPlayer6.getIconHeight()));
		     boardPlayer7.setBounds(0, 0, iconPlayer7.getIconWidth(),
		                                  iconPlayer7.getIconHeight()); 
		     player.playerUI.rand6_7.setPreferredSize(new Dimension(iconPlayer7.getIconWidth(),
		                                        iconPlayer7.getIconHeight()));
		     boardPlayer8.setBounds(0, 0, iconPlayer8.getIconWidth(),
		                                  iconPlayer8.getIconHeight()); 
		     player.playerUI.rand6_8.setPreferredSize(new Dimension(iconPlayer8.getIconWidth(),
		                                        iconPlayer8.getIconHeight()));
		     boardPlayer9.setBounds(0, 0, iconPlayer9.getIconWidth(),
		                                  iconPlayer9.getIconHeight()); 
		     player.playerUI.rand6_9.setPreferredSize(new Dimension(iconPlayer9.getIconWidth(),
		                                        iconPlayer9.getIconHeight()));
		     boardPlayer10.setBounds(0, 0, iconPlayer10.getIconWidth(),
		                                  iconPlayer10.getIconHeight()); 
		     player.playerUI.rand6_10.setPreferredSize(new Dimension(iconPlayer10.getIconWidth(),
		                                        iconPlayer10.getIconHeight()));													 													 
		     boardPlayer11.setBounds(0, 0, iconPlayer11.getIconWidth(),
		                                  iconPlayer11.getIconHeight()); 
		     player.playerUI.rand6_11.setPreferredSize(new Dimension(iconPlayer11.getIconWidth(),
		                                        iconPlayer11.getIconHeight()));
		     boardPlayer12.setBounds(0, 0, iconPlayer12.getIconWidth(),
		                                  iconPlayer12.getIconHeight()); 
		     player.playerUI.rand6_12.setPreferredSize(new Dimension(iconPlayer12.getIconWidth(),
		                                        iconPlayer12.getIconHeight()));
		     boardPlayer13.setBounds(0, 0, iconPlayer13.getIconWidth(),
		                                  iconPlayer13.getIconHeight()); 
		     player.playerUI.rand6_13.setPreferredSize(new Dimension(iconPlayer13.getIconWidth(),
		                                        iconPlayer13.getIconHeight()));
		     boardPlayer14.setBounds(0, 0, iconPlayer14.getIconWidth(),
		                                  iconPlayer14.getIconHeight()); 
		     player.playerUI.rand6_14.setPreferredSize(new Dimension(iconPlayer14.getIconWidth(),
		                                        iconPlayer14.getIconHeight()));
		     boardPlayer15.setBounds(0, 0, iconPlayer15.getIconWidth(),
		                                  iconPlayer15.getIconHeight()); 
		     player.playerUI.rand6_15.setPreferredSize(new Dimension(iconPlayer15.getIconWidth(),
		                                        iconPlayer15.getIconHeight()));	
		     boardPlayer16.setBounds(0, 0, iconPlayer16.getIconWidth(),
		                                  iconPlayer16.getIconHeight()); 
		     player.playerUI.rand6_16.setPreferredSize(new Dimension(iconPlayer16.getIconWidth(),
		                                        iconPlayer16.getIconHeight()));													 												 													 
		
			  player.playerUI.mypanel6.add(player.playerUI.rand6_1);
		     player.playerUI.mypanel6.add(player.playerUI.rand6_2);
		     player.playerUI.mypanel6.add(player.playerUI.rand6_3);
		     player.playerUI.mypanel6.add(player.playerUI.rand6_4);
		     player.playerUI.mypanel6.add(player.playerUI.rand6_5);
		     player.playerUI.mypanel6.add(player.playerUI.rand6_6);
		     player.playerUI.mypanel6.add(player.playerUI.rand6_7);
		     player.playerUI.mypanel6.add(player.playerUI.rand6_8);
		     player.playerUI.mypanel6.add(player.playerUI.rand6_9);
		     player.playerUI.mypanel6.add(player.playerUI.rand6_10);
		     player.playerUI.mypanel6.add(player.playerUI.rand6_11);
		     player.playerUI.mypanel6.add(player.playerUI.rand6_12);
		     player.playerUI.mypanel6.add(player.playerUI.rand6_13);
		     player.playerUI.mypanel6.add(player.playerUI.rand6_14);
		     player.playerUI.mypanel6.add(player.playerUI.rand6_15);
		     player.playerUI.mypanel6.add(player.playerUI.rand6_16);
		  
		  }
		  else if(tile == 7)
		  {
	       iconPlayer1 =  bp[4][12].getIcon();
	       iconPlayer2 =  bp[5][12].getIcon();
	       iconPlayer3 =  bp[6][12].getIcon();
	       iconPlayer4 =  bp[7][12].getIcon();
	       iconPlayer5 =  bp[4][13].getIcon();
	       iconPlayer6 =  bp[5][13].getIcon();
	       iconPlayer7 =  bp[6][13].getIcon();
	       iconPlayer8 =  bp[7][13].getIcon();
	       iconPlayer9 =  bp[4][14].getIcon();
	       iconPlayer10 =  bp[5][14].getIcon();
	       iconPlayer11 =  bp[6][14].getIcon();
	       iconPlayer12 =  bp[7][14].getIcon();
	       iconPlayer13 =  bp[4][15].getIcon();
	       iconPlayer14 =  bp[5][15].getIcon();
	       iconPlayer15 =  bp[6][15].getIcon();
	       iconPlayer16 =  bp[7][15].getIcon();


		//add components to board
			  GameJLabel boardPlayer1 = new GameJLabel(iconPlayer1);
			  GameJLabel boardPlayer2 = new GameJLabel(iconPlayer2);
			  GameJLabel boardPlayer3 = new GameJLabel(iconPlayer3);
			  GameJLabel boardPlayer4 = new GameJLabel(iconPlayer4);
			  GameJLabel boardPlayer5 = new GameJLabel(iconPlayer5);
			  GameJLabel boardPlayer6 = new GameJLabel(iconPlayer6);
			  GameJLabel boardPlayer7 = new GameJLabel(iconPlayer7);
			  GameJLabel boardPlayer8 = new GameJLabel(iconPlayer8);
			  GameJLabel boardPlayer9 = new GameJLabel(iconPlayer9);
			  GameJLabel boardPlayer10 = new GameJLabel(iconPlayer10);
			  GameJLabel boardPlayer11 = new GameJLabel(iconPlayer11);
			  GameJLabel boardPlayer12 = new GameJLabel(iconPlayer12);
			  GameJLabel boardPlayer13 = new GameJLabel(iconPlayer13);
			  GameJLabel boardPlayer14 = new GameJLabel(iconPlayer14);
			  GameJLabel boardPlayer15 = new GameJLabel(iconPlayer15);
			  GameJLabel boardPlayer16 = new GameJLabel(iconPlayer16);
		
			  player.playerUI.rand6_1.add(boardPlayer1, new Integer(0));
		     player.playerUI.rand6_2.add(boardPlayer2, new Integer(0));
		     player.playerUI.rand6_3.add(boardPlayer3, new Integer(0));
		     player.playerUI.rand6_4.add(boardPlayer4, new Integer(0));
		     player.playerUI.rand6_5.add(boardPlayer5, new Integer(0));
		     player.playerUI.rand6_6.add(boardPlayer6, new Integer(0));
		     player.playerUI.rand6_7.add(boardPlayer7, new Integer(0));
		     player.playerUI.rand6_8.add(boardPlayer8, new Integer(0));
		     player.playerUI.rand6_9.add(boardPlayer9, new Integer(0));
		     player.playerUI.rand6_10.add(boardPlayer10, new Integer(0));
		     player.playerUI.rand6_11.add(boardPlayer11, new Integer(0));
		     player.playerUI.rand6_12.add(boardPlayer12, new Integer(0));
		     player.playerUI.rand6_13.add(boardPlayer13, new Integer(0));
		     player.playerUI.rand6_14.add(boardPlayer14, new Integer(0));
		     player.playerUI.rand6_15.add(boardPlayer15, new Integer(0));
		     player.playerUI.rand6_16.add(boardPlayer16, new Integer(0));
		
		     boardPlayer1.setBounds(0, 0, iconPlayer1.getIconWidth(),
		                                  iconPlayer1.getIconHeight()); 
		     player.playerUI.rand6_1.setPreferredSize(new Dimension(iconPlayer1.getIconWidth(),
		                                        iconPlayer1.getIconHeight()));
		     boardPlayer2.setBounds(0, 0, iconPlayer2.getIconWidth(),
		                                  iconPlayer2.getIconHeight()); 
		     player.playerUI.rand6_2.setPreferredSize(new Dimension(iconPlayer2.getIconWidth(),
		                                        iconPlayer2.getIconHeight()));
		     boardPlayer3.setBounds(0, 0, iconPlayer3.getIconWidth(),
		                                  iconPlayer3.getIconHeight()); 
		     player.playerUI.rand6_3.setPreferredSize(new Dimension(iconPlayer3.getIconWidth(),
		                                        iconPlayer3.getIconHeight()));
		     boardPlayer4.setBounds(0, 0, iconPlayer4.getIconWidth(),
		                                  iconPlayer4.getIconHeight()); 
		     player.playerUI.rand6_4.setPreferredSize(new Dimension(iconPlayer4.getIconWidth(),
		                                        iconPlayer4.getIconHeight()));
		     boardPlayer5.setBounds(0, 0, iconPlayer5.getIconWidth(),
		                                  iconPlayer5.getIconHeight()); 
		     player.playerUI.rand6_5.setPreferredSize(new Dimension(iconPlayer5.getIconWidth(),
		                                        iconPlayer5.getIconHeight()));
		     boardPlayer6.setBounds(0, 0, iconPlayer6.getIconWidth(),
		                                  iconPlayer6.getIconHeight()); 
		     player.playerUI.rand6_6.setPreferredSize(new Dimension(iconPlayer6.getIconWidth(),
		                                        iconPlayer6.getIconHeight()));
		     boardPlayer7.setBounds(0, 0, iconPlayer7.getIconWidth(),
		                                  iconPlayer7.getIconHeight()); 
		     player.playerUI.rand6_7.setPreferredSize(new Dimension(iconPlayer7.getIconWidth(),
		                                        iconPlayer7.getIconHeight()));
		     boardPlayer8.setBounds(0, 0, iconPlayer8.getIconWidth(),
		                                  iconPlayer8.getIconHeight()); 
		     player.playerUI.rand6_8.setPreferredSize(new Dimension(iconPlayer8.getIconWidth(),
		                                        iconPlayer8.getIconHeight()));
		     boardPlayer9.setBounds(0, 0, iconPlayer9.getIconWidth(),
		                                  iconPlayer9.getIconHeight()); 
		     player.playerUI.rand6_9.setPreferredSize(new Dimension(iconPlayer9.getIconWidth(),
		                                        iconPlayer9.getIconHeight()));
		     boardPlayer10.setBounds(0, 0, iconPlayer10.getIconWidth(),
		                                  iconPlayer10.getIconHeight()); 
		     player.playerUI.rand6_10.setPreferredSize(new Dimension(iconPlayer10.getIconWidth(),
		                                        iconPlayer10.getIconHeight()));													 													 
		     boardPlayer11.setBounds(0, 0, iconPlayer11.getIconWidth(),
		                                  iconPlayer11.getIconHeight()); 
		     player.playerUI.rand6_11.setPreferredSize(new Dimension(iconPlayer11.getIconWidth(),
		                                        iconPlayer11.getIconHeight()));
		     boardPlayer12.setBounds(0, 0, iconPlayer12.getIconWidth(),
		                                  iconPlayer12.getIconHeight()); 
		     player.playerUI.rand6_12.setPreferredSize(new Dimension(iconPlayer12.getIconWidth(),
		                                        iconPlayer12.getIconHeight()));
		     boardPlayer13.setBounds(0, 0, iconPlayer13.getIconWidth(),
		                                  iconPlayer13.getIconHeight()); 
		     player.playerUI.rand6_13.setPreferredSize(new Dimension(iconPlayer13.getIconWidth(),
		                                        iconPlayer13.getIconHeight()));
		     boardPlayer14.setBounds(0, 0, iconPlayer14.getIconWidth(),
		                                  iconPlayer14.getIconHeight()); 
		     player.playerUI.rand6_14.setPreferredSize(new Dimension(iconPlayer14.getIconWidth(),
		                                        iconPlayer14.getIconHeight()));
		     boardPlayer15.setBounds(0, 0, iconPlayer15.getIconWidth(),
		                                  iconPlayer15.getIconHeight()); 
		     player.playerUI.rand6_15.setPreferredSize(new Dimension(iconPlayer15.getIconWidth(),
		                                        iconPlayer15.getIconHeight()));	
		     boardPlayer16.setBounds(0, 0, iconPlayer16.getIconWidth(),
		                                  iconPlayer16.getIconHeight()); 
		     player.playerUI.rand6_16.setPreferredSize(new Dimension(iconPlayer16.getIconWidth(),
		                                        iconPlayer16.getIconHeight()));													 												 													 
		
			  player.playerUI.mypanel7.add(player.playerUI.rand6_1);
		     player.playerUI.mypanel7.add(player.playerUI.rand6_2);
		     player.playerUI.mypanel7.add(player.playerUI.rand6_3);
		     player.playerUI.mypanel7.add(player.playerUI.rand6_4);
		     player.playerUI.mypanel7.add(player.playerUI.rand6_5);
		     player.playerUI.mypanel7.add(player.playerUI.rand6_6);
		     player.playerUI.mypanel7.add(player.playerUI.rand6_7);
		     player.playerUI.mypanel7.add(player.playerUI.rand6_8);
		     player.playerUI.mypanel7.add(player.playerUI.rand6_9);
		     player.playerUI.mypanel7.add(player.playerUI.rand6_10);
		     player.playerUI.mypanel7.add(player.playerUI.rand6_11);
		     player.playerUI.mypanel7.add(player.playerUI.rand6_12);
		     player.playerUI.mypanel7.add(player.playerUI.rand6_13);
		     player.playerUI.mypanel7.add(player.playerUI.rand6_14);
		     player.playerUI.mypanel7.add(player.playerUI.rand6_15);
		     player.playerUI.mypanel7.add(player.playerUI.rand6_16);
		  
		  }
		}

      private void populateRand7(int tile, Player player)
		{
		  if(tile == 0)
		  {
			  iconPlayer1 =  (bp[0][0].getIcon());
	        iconPlayer2 =  (bp[1][0].getIcon());
 	        iconPlayer3 =  (bp[2][0].getIcon());
	        iconPlayer4 =  (bp[3][0].getIcon());
	        iconPlayer5 =  (bp[0][1].getIcon());
	        iconPlayer6 =  (bp[1][1].getIcon());
	        iconPlayer7 =  (bp[2][1].getIcon());
	        iconPlayer8 =  (bp[3][1].getIcon());
	        iconPlayer9 =  (bp[0][2].getIcon());
	        iconPlayer10 =  (bp[1][2].getIcon());
	        iconPlayer11 =  (bp[2][2].getIcon());
	        iconPlayer12 =  (bp[3][2].getIcon());
	        iconPlayer13 =  (bp[0][3].getIcon());
	        iconPlayer14 =  (bp[1][3].getIcon());
	        iconPlayer15 =  (bp[2][3].getIcon());
	        iconPlayer16 =  (bp[3][3].getIcon());

	
		//add components to board
			  GameJLabel boardPlayer1 = new GameJLabel(iconPlayer1);
			  GameJLabel boardPlayer2 = new GameJLabel(iconPlayer2);
			  GameJLabel boardPlayer3 = new GameJLabel(iconPlayer3);
			  GameJLabel boardPlayer4 = new GameJLabel(iconPlayer4);
			  GameJLabel boardPlayer5 = new GameJLabel(iconPlayer5);
			  GameJLabel boardPlayer6 = new GameJLabel(iconPlayer6);
			  GameJLabel boardPlayer7 = new GameJLabel(iconPlayer7);
			  GameJLabel boardPlayer8 = new GameJLabel(iconPlayer8);
			  GameJLabel boardPlayer9 = new GameJLabel(iconPlayer9);
			  GameJLabel boardPlayer10 = new GameJLabel(iconPlayer10);
			  GameJLabel boardPlayer11 = new GameJLabel(iconPlayer11);
			  GameJLabel boardPlayer12 = new GameJLabel(iconPlayer12);
			  GameJLabel boardPlayer13 = new GameJLabel(iconPlayer13);
			  GameJLabel boardPlayer14 = new GameJLabel(iconPlayer14);
			  GameJLabel boardPlayer15 = new GameJLabel(iconPlayer15);
			  GameJLabel boardPlayer16 = new GameJLabel(iconPlayer16);
		
			  player.playerUI.rand7_1.add(boardPlayer1, new Integer(0));
		     player.playerUI.rand7_2.add(boardPlayer2, new Integer(0));
		     player.playerUI.rand7_3.add(boardPlayer3, new Integer(0));
		     player.playerUI.rand7_4.add(boardPlayer4, new Integer(0));
		     player.playerUI.rand7_5.add(boardPlayer5, new Integer(0));
		     player.playerUI.rand7_6.add(boardPlayer6, new Integer(0));
		     player.playerUI.rand7_7.add(boardPlayer7, new Integer(0));
		     player.playerUI.rand7_8.add(boardPlayer8, new Integer(0));
		     player.playerUI.rand7_9.add(boardPlayer9, new Integer(0));
		     player.playerUI.rand7_10.add(boardPlayer10, new Integer(0));
		     player.playerUI.rand7_11.add(boardPlayer11, new Integer(0));
		     player.playerUI.rand7_12.add(boardPlayer12, new Integer(0));
		     player.playerUI.rand7_13.add(boardPlayer13, new Integer(0));
		     player.playerUI.rand7_14.add(boardPlayer14, new Integer(0));
		     player.playerUI.rand7_15.add(boardPlayer15, new Integer(0));
		     player.playerUI.rand7_16.add(boardPlayer16, new Integer(0));
		
		     boardPlayer1.setBounds(0, 0, iconPlayer1.getIconWidth(),
		                                  iconPlayer1.getIconHeight()); 
		     player.playerUI.rand7_1.setPreferredSize(new Dimension(iconPlayer1.getIconWidth(),
		                                        iconPlayer1.getIconHeight()));
		     boardPlayer2.setBounds(0, 0, iconPlayer2.getIconWidth(),
		                                  iconPlayer2.getIconHeight()); 
		     player.playerUI.rand7_2.setPreferredSize(new Dimension(iconPlayer2.getIconWidth(),
		                                        iconPlayer2.getIconHeight()));
		     boardPlayer3.setBounds(0, 0, iconPlayer3.getIconWidth(),
		                                  iconPlayer3.getIconHeight()); 
		     player.playerUI.rand7_3.setPreferredSize(new Dimension(iconPlayer3.getIconWidth(),
		                                        iconPlayer3.getIconHeight()));
		     boardPlayer4.setBounds(0, 0, iconPlayer4.getIconWidth(),
		                                  iconPlayer4.getIconHeight()); 
		     player.playerUI.rand7_4.setPreferredSize(new Dimension(iconPlayer4.getIconWidth(),
		                                        iconPlayer4.getIconHeight()));
		     boardPlayer5.setBounds(0, 0, iconPlayer5.getIconWidth(),
		                                  iconPlayer5.getIconHeight()); 
		     player.playerUI.rand7_5.setPreferredSize(new Dimension(iconPlayer5.getIconWidth(),
		                                        iconPlayer5.getIconHeight()));
		     boardPlayer6.setBounds(0, 0, iconPlayer6.getIconWidth(),
		                                  iconPlayer6.getIconHeight()); 
		     player.playerUI.rand7_6.setPreferredSize(new Dimension(iconPlayer6.getIconWidth(),
		                                        iconPlayer6.getIconHeight()));
		     boardPlayer7.setBounds(0, 0, iconPlayer7.getIconWidth(),
		                                  iconPlayer7.getIconHeight()); 
		     player.playerUI.rand7_7.setPreferredSize(new Dimension(iconPlayer7.getIconWidth(),
		                                        iconPlayer7.getIconHeight()));
		     boardPlayer8.setBounds(0, 0, iconPlayer8.getIconWidth(),
		                                  iconPlayer8.getIconHeight()); 
		     player.playerUI.rand7_8.setPreferredSize(new Dimension(iconPlayer8.getIconWidth(),
		                                        iconPlayer8.getIconHeight()));
		     boardPlayer9.setBounds(0, 0, iconPlayer9.getIconWidth(),
		                                  iconPlayer9.getIconHeight()); 
		     player.playerUI.rand7_9.setPreferredSize(new Dimension(iconPlayer9.getIconWidth(),
		                                        iconPlayer9.getIconHeight()));
		     boardPlayer10.setBounds(0, 0, iconPlayer10.getIconWidth(),
		                                  iconPlayer10.getIconHeight()); 
		     player.playerUI.rand7_10.setPreferredSize(new Dimension(iconPlayer10.getIconWidth(),
		                                        iconPlayer10.getIconHeight()));													 													 
		     boardPlayer11.setBounds(0, 0, iconPlayer11.getIconWidth(),
		                                  iconPlayer11.getIconHeight()); 
		     player.playerUI.rand7_11.setPreferredSize(new Dimension(iconPlayer11.getIconWidth(),
		                                        iconPlayer11.getIconHeight()));
		     boardPlayer12.setBounds(0, 0, iconPlayer12.getIconWidth(),
		                                  iconPlayer12.getIconHeight()); 
		     player.playerUI.rand7_12.setPreferredSize(new Dimension(iconPlayer12.getIconWidth(),
		                                        iconPlayer12.getIconHeight()));
		     boardPlayer13.setBounds(0, 0, iconPlayer13.getIconWidth(),
		                                  iconPlayer13.getIconHeight()); 
		     player.playerUI.rand7_13.setPreferredSize(new Dimension(iconPlayer13.getIconWidth(),
		                                        iconPlayer13.getIconHeight()));
		     boardPlayer14.setBounds(0, 0, iconPlayer14.getIconWidth(),
		                                  iconPlayer14.getIconHeight()); 
		     player.playerUI.rand7_14.setPreferredSize(new Dimension(iconPlayer14.getIconWidth(),
		                                        iconPlayer14.getIconHeight()));
		     boardPlayer15.setBounds(0, 0, iconPlayer15.getIconWidth(),
		                                  iconPlayer15.getIconHeight()); 
		     player.playerUI.rand7_15.setPreferredSize(new Dimension(iconPlayer15.getIconWidth(),
		                                        iconPlayer15.getIconHeight()));	
		     boardPlayer16.setBounds(0, 0, iconPlayer16.getIconWidth(),
		                                  iconPlayer16.getIconHeight()); 
		     player.playerUI.rand7_16.setPreferredSize(new Dimension(iconPlayer16.getIconWidth(),
		                                        iconPlayer16.getIconHeight()));													 												 													 
		
			  player.playerUI.mypanel0.add(player.playerUI.rand7_1);
		     player.playerUI.mypanel0.add(player.playerUI.rand7_2);
		     player.playerUI.mypanel0.add(player.playerUI.rand7_3);
		     player.playerUI.mypanel0.add(player.playerUI.rand7_4);
		     player.playerUI.mypanel0.add(player.playerUI.rand7_5);
		     player.playerUI.mypanel0.add(player.playerUI.rand7_6);
		     player.playerUI.mypanel0.add(player.playerUI.rand7_7);
		     player.playerUI.mypanel0.add(player.playerUI.rand7_8);
		     player.playerUI.mypanel0.add(player.playerUI.rand7_9);
		     player.playerUI.mypanel0.add(player.playerUI.rand7_10);
		     player.playerUI.mypanel0.add(player.playerUI.rand7_11);
		     player.playerUI.mypanel0.add(player.playerUI.rand7_12);
		     player.playerUI.mypanel0.add(player.playerUI.rand7_13);
		     player.playerUI.mypanel0.add(player.playerUI.rand7_14);
		     player.playerUI.mypanel0.add(player.playerUI.rand7_15);
		     player.playerUI.mypanel0.add(player.playerUI.rand7_16);
		  }
		  else if(tile == 1)
		  {
  	        iconPlayer1 =  (bp[0][4].getIcon());
	        iconPlayer2 =  (bp[1][4].getIcon());
 	        iconPlayer3 =  (bp[2][4].getIcon());
	        iconPlayer4 =  (bp[3][4].getIcon());
	        iconPlayer5 =  (bp[0][5].getIcon());
	        iconPlayer6 =  (bp[1][5].getIcon());
	        iconPlayer7 =  (bp[2][5].getIcon());
	        iconPlayer8 =  (bp[3][5].getIcon());
	        iconPlayer9 =  (bp[0][6].getIcon());
	        iconPlayer10 =  (bp[1][6].getIcon());
	        iconPlayer11 =  (bp[2][6].getIcon());
	        iconPlayer12 =  (bp[3][6].getIcon());
	        iconPlayer13 =  (bp[0][7].getIcon());
	        iconPlayer14 =  (bp[1][7].getIcon());
	        iconPlayer15 =  (bp[2][7].getIcon());
	        iconPlayer16 =  (bp[3][7].getIcon());

	
		//add components to board
			  GameJLabel boardPlayer1 = new GameJLabel(iconPlayer1);
			  GameJLabel boardPlayer2 = new GameJLabel(iconPlayer2);
			  GameJLabel boardPlayer3 = new GameJLabel(iconPlayer3);
			  GameJLabel boardPlayer4 = new GameJLabel(iconPlayer4);
			  GameJLabel boardPlayer5 = new GameJLabel(iconPlayer5);
			  GameJLabel boardPlayer6 = new GameJLabel(iconPlayer6);
			  GameJLabel boardPlayer7 = new GameJLabel(iconPlayer7);
			  GameJLabel boardPlayer8 = new GameJLabel(iconPlayer8);
			  GameJLabel boardPlayer9 = new GameJLabel(iconPlayer9);
			  GameJLabel boardPlayer10 = new GameJLabel(iconPlayer10);
			  GameJLabel boardPlayer11 = new GameJLabel(iconPlayer11);
			  GameJLabel boardPlayer12 = new GameJLabel(iconPlayer12);
			  GameJLabel boardPlayer13 = new GameJLabel(iconPlayer13);
			  GameJLabel boardPlayer14 = new GameJLabel(iconPlayer14);
			  GameJLabel boardPlayer15 = new GameJLabel(iconPlayer15);
			  GameJLabel boardPlayer16 = new GameJLabel(iconPlayer16);
		
			  player.playerUI.rand7_1.add(boardPlayer1, new Integer(0));
		     player.playerUI.rand7_2.add(boardPlayer2, new Integer(0));
		     player.playerUI.rand7_3.add(boardPlayer3, new Integer(0));
		     player.playerUI.rand7_4.add(boardPlayer4, new Integer(0));
		     player.playerUI.rand7_5.add(boardPlayer5, new Integer(0));
		     player.playerUI.rand7_6.add(boardPlayer6, new Integer(0));
		     player.playerUI.rand7_7.add(boardPlayer7, new Integer(0));
		     player.playerUI.rand7_8.add(boardPlayer8, new Integer(0));
		     player.playerUI.rand7_9.add(boardPlayer9, new Integer(0));
		     player.playerUI.rand7_10.add(boardPlayer10, new Integer(0));
		     player.playerUI.rand7_11.add(boardPlayer11, new Integer(0));
		     player.playerUI.rand7_12.add(boardPlayer12, new Integer(0));
		     player.playerUI.rand7_13.add(boardPlayer13, new Integer(0));
		     player.playerUI.rand7_14.add(boardPlayer14, new Integer(0));
		     player.playerUI.rand7_15.add(boardPlayer15, new Integer(0));
		     player.playerUI.rand7_16.add(boardPlayer16, new Integer(0));
		
		     boardPlayer1.setBounds(0, 0, iconPlayer1.getIconWidth(),
		                                  iconPlayer1.getIconHeight()); 
		     player.playerUI.rand7_1.setPreferredSize(new Dimension(iconPlayer1.getIconWidth(),
		                                        iconPlayer1.getIconHeight()));
		     boardPlayer2.setBounds(0, 0, iconPlayer2.getIconWidth(),
		                                  iconPlayer2.getIconHeight()); 
		     player.playerUI.rand7_2.setPreferredSize(new Dimension(iconPlayer2.getIconWidth(),
		                                        iconPlayer2.getIconHeight()));
		     boardPlayer3.setBounds(0, 0, iconPlayer3.getIconWidth(),
		                                  iconPlayer3.getIconHeight()); 
		     player.playerUI.rand7_3.setPreferredSize(new Dimension(iconPlayer3.getIconWidth(),
		                                        iconPlayer3.getIconHeight()));
		     boardPlayer4.setBounds(0, 0, iconPlayer4.getIconWidth(),
		                                  iconPlayer4.getIconHeight()); 
		     player.playerUI.rand7_4.setPreferredSize(new Dimension(iconPlayer4.getIconWidth(),
		                                        iconPlayer4.getIconHeight()));
		     boardPlayer5.setBounds(0, 0, iconPlayer5.getIconWidth(),
		                                  iconPlayer5.getIconHeight()); 
		     player.playerUI.rand7_5.setPreferredSize(new Dimension(iconPlayer5.getIconWidth(),
		                                        iconPlayer5.getIconHeight()));
		     boardPlayer6.setBounds(0, 0, iconPlayer6.getIconWidth(),
		                                  iconPlayer6.getIconHeight()); 
		     player.playerUI.rand7_6.setPreferredSize(new Dimension(iconPlayer6.getIconWidth(),
		                                        iconPlayer6.getIconHeight()));
		     boardPlayer7.setBounds(0, 0, iconPlayer7.getIconWidth(),
		                                  iconPlayer7.getIconHeight()); 
		     player.playerUI.rand7_7.setPreferredSize(new Dimension(iconPlayer7.getIconWidth(),
		                                        iconPlayer7.getIconHeight()));
		     boardPlayer8.setBounds(0, 0, iconPlayer8.getIconWidth(),
		                                  iconPlayer8.getIconHeight()); 
		     player.playerUI.rand7_8.setPreferredSize(new Dimension(iconPlayer8.getIconWidth(),
		                                        iconPlayer8.getIconHeight()));
		     boardPlayer9.setBounds(0, 0, iconPlayer9.getIconWidth(),
		                                  iconPlayer9.getIconHeight()); 
		     player.playerUI.rand7_9.setPreferredSize(new Dimension(iconPlayer9.getIconWidth(),
		                                        iconPlayer9.getIconHeight()));
		     boardPlayer10.setBounds(0, 0, iconPlayer10.getIconWidth(),
		                                  iconPlayer10.getIconHeight()); 
		     player.playerUI.rand7_10.setPreferredSize(new Dimension(iconPlayer10.getIconWidth(),
		                                        iconPlayer10.getIconHeight()));													 													 
		     boardPlayer11.setBounds(0, 0, iconPlayer11.getIconWidth(),
		                                  iconPlayer11.getIconHeight()); 
		     player.playerUI.rand7_11.setPreferredSize(new Dimension(iconPlayer11.getIconWidth(),
		                                        iconPlayer11.getIconHeight()));
		     boardPlayer12.setBounds(0, 0, iconPlayer12.getIconWidth(),
		                                  iconPlayer12.getIconHeight()); 
		     player.playerUI.rand7_12.setPreferredSize(new Dimension(iconPlayer12.getIconWidth(),
		                                        iconPlayer12.getIconHeight()));
		     boardPlayer13.setBounds(0, 0, iconPlayer13.getIconWidth(),
		                                  iconPlayer13.getIconHeight()); 
		     player.playerUI.rand7_13.setPreferredSize(new Dimension(iconPlayer13.getIconWidth(),
		                                        iconPlayer13.getIconHeight()));
		     boardPlayer14.setBounds(0, 0, iconPlayer14.getIconWidth(),
		                                  iconPlayer14.getIconHeight()); 
		     player.playerUI.rand7_14.setPreferredSize(new Dimension(iconPlayer14.getIconWidth(),
		                                        iconPlayer14.getIconHeight()));
		     boardPlayer15.setBounds(0, 0, iconPlayer15.getIconWidth(),
		                                  iconPlayer15.getIconHeight()); 
		     player.playerUI.rand7_15.setPreferredSize(new Dimension(iconPlayer15.getIconWidth(),
		                                        iconPlayer15.getIconHeight()));	
		     boardPlayer16.setBounds(0, 0, iconPlayer16.getIconWidth(),
		                                  iconPlayer16.getIconHeight()); 
		     player.playerUI.rand7_16.setPreferredSize(new Dimension(iconPlayer16.getIconWidth(),
		                                        iconPlayer16.getIconHeight()));													 												 													 
		
			  player.playerUI.mypanel1.add(player.playerUI.rand7_1);
		     player.playerUI.mypanel1.add(player.playerUI.rand7_2);
		     player.playerUI.mypanel1.add(player.playerUI.rand7_3);
		     player.playerUI.mypanel1.add(player.playerUI.rand7_4);
		     player.playerUI.mypanel1.add(player.playerUI.rand7_5);
		     player.playerUI.mypanel1.add(player.playerUI.rand7_6);
		     player.playerUI.mypanel1.add(player.playerUI.rand7_7);
		     player.playerUI.mypanel1.add(player.playerUI.rand7_8);
		     player.playerUI.mypanel1.add(player.playerUI.rand7_9);
		     player.playerUI.mypanel1.add(player.playerUI.rand7_10);
		     player.playerUI.mypanel1.add(player.playerUI.rand7_11);
		     player.playerUI.mypanel1.add(player.playerUI.rand7_12);
		     player.playerUI.mypanel1.add(player.playerUI.rand7_13);
		     player.playerUI.mypanel1.add(player.playerUI.rand7_14);
		     player.playerUI.mypanel1.add(player.playerUI.rand7_15);
		     player.playerUI.mypanel1.add(player.playerUI.rand7_16);
		  
		  }
		  else if(tile == 2)
		  {
  	        iconPlayer1 =  (bp[0][8].getIcon());
	        iconPlayer2 =  (bp[1][8].getIcon());
 	        iconPlayer3 =  (bp[2][8].getIcon());
	        iconPlayer4 =  (bp[3][8].getIcon());
	        iconPlayer5 =  (bp[0][9].getIcon());
	        iconPlayer6 =  (bp[1][9].getIcon());
	        iconPlayer7 =  (bp[2][9].getIcon());
	        iconPlayer8 =  (bp[3][9].getIcon());
	        iconPlayer9 =  (bp[0][10].getIcon());
	        iconPlayer10 =  (bp[1][10].getIcon());
	        iconPlayer11 =  (bp[2][10].getIcon());
	        iconPlayer12 =  (bp[3][10].getIcon());
	        iconPlayer13 =  (bp[0][11].getIcon());
	        iconPlayer14 =  (bp[1][11].getIcon());
	        iconPlayer15 =  (bp[2][11].getIcon());
	        iconPlayer16 =  (bp[3][11].getIcon());

	
		//add components to board
			  GameJLabel boardPlayer1 = new GameJLabel(iconPlayer1);
			  GameJLabel boardPlayer2 = new GameJLabel(iconPlayer2);
			  GameJLabel boardPlayer3 = new GameJLabel(iconPlayer3);
			  GameJLabel boardPlayer4 = new GameJLabel(iconPlayer4);
			  GameJLabel boardPlayer5 = new GameJLabel(iconPlayer5);
			  GameJLabel boardPlayer6 = new GameJLabel(iconPlayer6);
			  GameJLabel boardPlayer7 = new GameJLabel(iconPlayer7);
			  GameJLabel boardPlayer8 = new GameJLabel(iconPlayer8);
			  GameJLabel boardPlayer9 = new GameJLabel(iconPlayer9);
			  GameJLabel boardPlayer10 = new GameJLabel(iconPlayer10);
			  GameJLabel boardPlayer11 = new GameJLabel(iconPlayer11);
			  GameJLabel boardPlayer12 = new GameJLabel(iconPlayer12);
			  GameJLabel boardPlayer13 = new GameJLabel(iconPlayer13);
			  GameJLabel boardPlayer14 = new GameJLabel(iconPlayer14);
			  GameJLabel boardPlayer15 = new GameJLabel(iconPlayer15);
			  GameJLabel boardPlayer16 = new GameJLabel(iconPlayer16);
		
			  player.playerUI.rand7_1.add(boardPlayer1, new Integer(0));
		     player.playerUI.rand7_2.add(boardPlayer2, new Integer(0));
		     player.playerUI.rand7_3.add(boardPlayer3, new Integer(0));
		     player.playerUI.rand7_4.add(boardPlayer4, new Integer(0));
		     player.playerUI.rand7_5.add(boardPlayer5, new Integer(0));
		     player.playerUI.rand7_6.add(boardPlayer6, new Integer(0));
		     player.playerUI.rand7_7.add(boardPlayer7, new Integer(0));
		     player.playerUI.rand7_8.add(boardPlayer8, new Integer(0));
		     player.playerUI.rand7_9.add(boardPlayer9, new Integer(0));
		     player.playerUI.rand7_10.add(boardPlayer10, new Integer(0));
		     player.playerUI.rand7_11.add(boardPlayer11, new Integer(0));
		     player.playerUI.rand7_12.add(boardPlayer12, new Integer(0));
		     player.playerUI.rand7_13.add(boardPlayer13, new Integer(0));
		     player.playerUI.rand7_14.add(boardPlayer14, new Integer(0));
		     player.playerUI.rand7_15.add(boardPlayer15, new Integer(0));
		     player.playerUI.rand7_16.add(boardPlayer16, new Integer(0));
		
		     boardPlayer1.setBounds(0, 0, iconPlayer1.getIconWidth(),
		                                  iconPlayer1.getIconHeight()); 
		     player.playerUI.rand7_1.setPreferredSize(new Dimension(iconPlayer1.getIconWidth(),
		                                        iconPlayer1.getIconHeight()));
		     boardPlayer2.setBounds(0, 0, iconPlayer2.getIconWidth(),
		                                  iconPlayer2.getIconHeight()); 
		     player.playerUI.rand7_2.setPreferredSize(new Dimension(iconPlayer2.getIconWidth(),
		                                        iconPlayer2.getIconHeight()));
		     boardPlayer3.setBounds(0, 0, iconPlayer3.getIconWidth(),
		                                  iconPlayer3.getIconHeight()); 
		     player.playerUI.rand7_3.setPreferredSize(new Dimension(iconPlayer3.getIconWidth(),
		                                        iconPlayer3.getIconHeight()));
		     boardPlayer4.setBounds(0, 0, iconPlayer4.getIconWidth(),
		                                  iconPlayer4.getIconHeight()); 
		     player.playerUI.rand7_4.setPreferredSize(new Dimension(iconPlayer4.getIconWidth(),
		                                        iconPlayer4.getIconHeight()));
		     boardPlayer5.setBounds(0, 0, iconPlayer5.getIconWidth(),
		                                  iconPlayer5.getIconHeight()); 
		     player.playerUI.rand7_5.setPreferredSize(new Dimension(iconPlayer5.getIconWidth(),
		                                        iconPlayer5.getIconHeight()));
		     boardPlayer6.setBounds(0, 0, iconPlayer6.getIconWidth(),
		                                  iconPlayer6.getIconHeight()); 
		     player.playerUI.rand7_6.setPreferredSize(new Dimension(iconPlayer6.getIconWidth(),
		                                        iconPlayer6.getIconHeight()));
		     boardPlayer7.setBounds(0, 0, iconPlayer7.getIconWidth(),
		                                  iconPlayer7.getIconHeight()); 
		     player.playerUI.rand7_7.setPreferredSize(new Dimension(iconPlayer7.getIconWidth(),
		                                        iconPlayer7.getIconHeight()));
		     boardPlayer8.setBounds(0, 0, iconPlayer8.getIconWidth(),
		                                  iconPlayer8.getIconHeight()); 
		     player.playerUI.rand7_8.setPreferredSize(new Dimension(iconPlayer8.getIconWidth(),
		                                        iconPlayer8.getIconHeight()));
		     boardPlayer9.setBounds(0, 0, iconPlayer9.getIconWidth(),
		                                  iconPlayer9.getIconHeight()); 
		     player.playerUI.rand7_9.setPreferredSize(new Dimension(iconPlayer9.getIconWidth(),
		                                        iconPlayer9.getIconHeight()));
		     boardPlayer10.setBounds(0, 0, iconPlayer10.getIconWidth(),
		                                  iconPlayer10.getIconHeight()); 
		     player.playerUI.rand7_10.setPreferredSize(new Dimension(iconPlayer10.getIconWidth(),
		                                        iconPlayer10.getIconHeight()));													 													 
		     boardPlayer11.setBounds(0, 0, iconPlayer11.getIconWidth(),
		                                  iconPlayer11.getIconHeight()); 
		     player.playerUI.rand7_11.setPreferredSize(new Dimension(iconPlayer11.getIconWidth(),
		                                        iconPlayer11.getIconHeight()));
		     boardPlayer12.setBounds(0, 0, iconPlayer12.getIconWidth(),
		                                  iconPlayer12.getIconHeight()); 
		     player.playerUI.rand7_12.setPreferredSize(new Dimension(iconPlayer12.getIconWidth(),
		                                        iconPlayer12.getIconHeight()));
		     boardPlayer13.setBounds(0, 0, iconPlayer13.getIconWidth(),
		                                  iconPlayer13.getIconHeight()); 
		     player.playerUI.rand7_13.setPreferredSize(new Dimension(iconPlayer13.getIconWidth(),
		                                        iconPlayer13.getIconHeight()));
		     boardPlayer14.setBounds(0, 0, iconPlayer14.getIconWidth(),
		                                  iconPlayer14.getIconHeight()); 
		     player.playerUI.rand7_14.setPreferredSize(new Dimension(iconPlayer14.getIconWidth(),
		                                        iconPlayer14.getIconHeight()));
		     boardPlayer15.setBounds(0, 0, iconPlayer15.getIconWidth(),
		                                  iconPlayer15.getIconHeight()); 
		     player.playerUI.rand7_15.setPreferredSize(new Dimension(iconPlayer15.getIconWidth(),
		                                        iconPlayer15.getIconHeight()));	
		     boardPlayer16.setBounds(0, 0, iconPlayer16.getIconWidth(),
		                                  iconPlayer16.getIconHeight()); 
		     player.playerUI.rand7_16.setPreferredSize(new Dimension(iconPlayer16.getIconWidth(),
		                                        iconPlayer16.getIconHeight()));													 												 													 
		
			  player.playerUI.mypanel2.add(player.playerUI.rand7_1);
		     player.playerUI.mypanel2.add(player.playerUI.rand7_2);
		     player.playerUI.mypanel2.add(player.playerUI.rand7_3);
		     player.playerUI.mypanel2.add(player.playerUI.rand7_4);
		     player.playerUI.mypanel2.add(player.playerUI.rand7_5);
		     player.playerUI.mypanel2.add(player.playerUI.rand7_6);
		     player.playerUI.mypanel2.add(player.playerUI.rand7_7);
		     player.playerUI.mypanel2.add(player.playerUI.rand7_8);
		     player.playerUI.mypanel2.add(player.playerUI.rand7_9);
		     player.playerUI.mypanel2.add(player.playerUI.rand7_10);
		     player.playerUI.mypanel2.add(player.playerUI.rand7_11);
		     player.playerUI.mypanel2.add(player.playerUI.rand7_12);
		     player.playerUI.mypanel2.add(player.playerUI.rand7_13);
		     player.playerUI.mypanel2.add(player.playerUI.rand7_14);
		     player.playerUI.mypanel2.add(player.playerUI.rand7_15);
		     player.playerUI.mypanel2.add(player.playerUI.rand7_16);
		  
		  }
		  else if(tile == 5)
		  {
  	        iconPlayer1 =  (bp[4][4].getIcon());
	        iconPlayer2 =  (bp[5][4].getIcon());
 	        iconPlayer3 =  (bp[6][4].getIcon());
	        iconPlayer4 =  (bp[7][4].getIcon());
	        iconPlayer5 =  (bp[4][5].getIcon());
	        iconPlayer6 =  (bp[5][5].getIcon());
	        iconPlayer7 =  (bp[6][5].getIcon());
	        iconPlayer8 =  (bp[7][5].getIcon());
	        iconPlayer9 =  (bp[4][6].getIcon());
	        iconPlayer10 =  (bp[5][6].getIcon());
	        iconPlayer11 =  (bp[6][6].getIcon());
	        iconPlayer12 =  (bp[7][6].getIcon());
	        iconPlayer13 =  (bp[4][7].getIcon());
	        iconPlayer14 =  (bp[5][7].getIcon());
	        iconPlayer15 =  (bp[6][7].getIcon());
	        iconPlayer16 =  (bp[7][7].getIcon());

	
		//add components to board
			  GameJLabel boardPlayer1 = new GameJLabel(iconPlayer1);
			  GameJLabel boardPlayer2 = new GameJLabel(iconPlayer2);
			  GameJLabel boardPlayer3 = new GameJLabel(iconPlayer3);
			  GameJLabel boardPlayer4 = new GameJLabel(iconPlayer4);
			  GameJLabel boardPlayer5 = new GameJLabel(iconPlayer5);
			  GameJLabel boardPlayer6 = new GameJLabel(iconPlayer6);
			  GameJLabel boardPlayer7 = new GameJLabel(iconPlayer7);
			  GameJLabel boardPlayer8 = new GameJLabel(iconPlayer8);
			  GameJLabel boardPlayer9 = new GameJLabel(iconPlayer9);
			  GameJLabel boardPlayer10 = new GameJLabel(iconPlayer10);
			  GameJLabel boardPlayer11 = new GameJLabel(iconPlayer11);
			  GameJLabel boardPlayer12 = new GameJLabel(iconPlayer12);
			  GameJLabel boardPlayer13 = new GameJLabel(iconPlayer13);
			  GameJLabel boardPlayer14 = new GameJLabel(iconPlayer14);
			  GameJLabel boardPlayer15 = new GameJLabel(iconPlayer15);
			  GameJLabel boardPlayer16 = new GameJLabel(iconPlayer16);
		
			  player.playerUI.rand7_1.add(boardPlayer1, new Integer(0));
		     player.playerUI.rand7_2.add(boardPlayer2, new Integer(0));
		     player.playerUI.rand7_3.add(boardPlayer3, new Integer(0));
		     player.playerUI.rand7_4.add(boardPlayer4, new Integer(0));
		     player.playerUI.rand7_5.add(boardPlayer5, new Integer(0));
		     player.playerUI.rand7_6.add(boardPlayer6, new Integer(0));
		     player.playerUI.rand7_7.add(boardPlayer7, new Integer(0));
		     player.playerUI.rand7_8.add(boardPlayer8, new Integer(0));
		     player.playerUI.rand7_9.add(boardPlayer9, new Integer(0));
		     player.playerUI.rand7_10.add(boardPlayer10, new Integer(0));
		     player.playerUI.rand7_11.add(boardPlayer11, new Integer(0));
		     player.playerUI.rand7_12.add(boardPlayer12, new Integer(0));
		     player.playerUI.rand7_13.add(boardPlayer13, new Integer(0));
		     player.playerUI.rand7_14.add(boardPlayer14, new Integer(0));
		     player.playerUI.rand7_15.add(boardPlayer15, new Integer(0));
		     player.playerUI.rand7_16.add(boardPlayer16, new Integer(0));
		
		     boardPlayer1.setBounds(0, 0, iconPlayer1.getIconWidth(),
		                                  iconPlayer1.getIconHeight()); 
		     player.playerUI.rand7_1.setPreferredSize(new Dimension(iconPlayer1.getIconWidth(),
		                                        iconPlayer1.getIconHeight()));
		     boardPlayer2.setBounds(0, 0, iconPlayer2.getIconWidth(),
		                                  iconPlayer2.getIconHeight()); 
		     player.playerUI.rand7_2.setPreferredSize(new Dimension(iconPlayer2.getIconWidth(),
		                                        iconPlayer2.getIconHeight()));
		     boardPlayer3.setBounds(0, 0, iconPlayer3.getIconWidth(),
		                                  iconPlayer3.getIconHeight()); 
		     player.playerUI.rand7_3.setPreferredSize(new Dimension(iconPlayer3.getIconWidth(),
		                                        iconPlayer3.getIconHeight()));
		     boardPlayer4.setBounds(0, 0, iconPlayer4.getIconWidth(),
		                                  iconPlayer4.getIconHeight()); 
		     player.playerUI.rand7_4.setPreferredSize(new Dimension(iconPlayer4.getIconWidth(),
		                                        iconPlayer4.getIconHeight()));
		     boardPlayer5.setBounds(0, 0, iconPlayer5.getIconWidth(),
		                                  iconPlayer5.getIconHeight()); 
		     player.playerUI.rand7_5.setPreferredSize(new Dimension(iconPlayer5.getIconWidth(),
		                                        iconPlayer5.getIconHeight()));
		     boardPlayer6.setBounds(0, 0, iconPlayer6.getIconWidth(),
		                                  iconPlayer6.getIconHeight()); 
		     player.playerUI.rand7_6.setPreferredSize(new Dimension(iconPlayer6.getIconWidth(),
		                                        iconPlayer6.getIconHeight()));
		     boardPlayer7.setBounds(0, 0, iconPlayer7.getIconWidth(),
		                                  iconPlayer7.getIconHeight()); 
		     player.playerUI.rand7_7.setPreferredSize(new Dimension(iconPlayer7.getIconWidth(),
		                                        iconPlayer7.getIconHeight()));
		     boardPlayer8.setBounds(0, 0, iconPlayer8.getIconWidth(),
		                                  iconPlayer8.getIconHeight()); 
		     player.playerUI.rand7_8.setPreferredSize(new Dimension(iconPlayer8.getIconWidth(),
		                                        iconPlayer8.getIconHeight()));
		     boardPlayer9.setBounds(0, 0, iconPlayer9.getIconWidth(),
		                                  iconPlayer9.getIconHeight()); 
		     player.playerUI.rand7_9.setPreferredSize(new Dimension(iconPlayer9.getIconWidth(),
		                                        iconPlayer9.getIconHeight()));
		     boardPlayer10.setBounds(0, 0, iconPlayer10.getIconWidth(),
		                                  iconPlayer10.getIconHeight()); 
		     player.playerUI.rand7_10.setPreferredSize(new Dimension(iconPlayer10.getIconWidth(),
		                                        iconPlayer10.getIconHeight()));													 													 
		     boardPlayer11.setBounds(0, 0, iconPlayer11.getIconWidth(),
		                                  iconPlayer11.getIconHeight()); 
		     player.playerUI.rand7_11.setPreferredSize(new Dimension(iconPlayer11.getIconWidth(),
		                                        iconPlayer11.getIconHeight()));
		     boardPlayer12.setBounds(0, 0, iconPlayer12.getIconWidth(),
		                                  iconPlayer12.getIconHeight()); 
		     player.playerUI.rand7_12.setPreferredSize(new Dimension(iconPlayer12.getIconWidth(),
		                                        iconPlayer12.getIconHeight()));
		     boardPlayer13.setBounds(0, 0, iconPlayer13.getIconWidth(),
		                                  iconPlayer13.getIconHeight()); 
		     player.playerUI.rand7_13.setPreferredSize(new Dimension(iconPlayer13.getIconWidth(),
		                                        iconPlayer13.getIconHeight()));
		     boardPlayer14.setBounds(0, 0, iconPlayer14.getIconWidth(),
		                                  iconPlayer14.getIconHeight()); 
		     player.playerUI.rand7_14.setPreferredSize(new Dimension(iconPlayer14.getIconWidth(),
		                                        iconPlayer14.getIconHeight()));
		     boardPlayer15.setBounds(0, 0, iconPlayer15.getIconWidth(),
		                                  iconPlayer15.getIconHeight()); 
		     player.playerUI.rand7_15.setPreferredSize(new Dimension(iconPlayer15.getIconWidth(),
		                                        iconPlayer15.getIconHeight()));	
		     boardPlayer16.setBounds(0, 0, iconPlayer16.getIconWidth(),
		                                  iconPlayer16.getIconHeight()); 
		     player.playerUI.rand7_16.setPreferredSize(new Dimension(iconPlayer16.getIconWidth(),
		                                        iconPlayer16.getIconHeight()));													 												 													 
		
			  player.playerUI.mypanel5.add(player.playerUI.rand7_1);
		     player.playerUI.mypanel5.add(player.playerUI.rand7_2);
		     player.playerUI.mypanel5.add(player.playerUI.rand7_3);
		     player.playerUI.mypanel5.add(player.playerUI.rand7_4);
		     player.playerUI.mypanel5.add(player.playerUI.rand7_5);
		     player.playerUI.mypanel5.add(player.playerUI.rand7_6);
		     player.playerUI.mypanel5.add(player.playerUI.rand7_7);
		     player.playerUI.mypanel5.add(player.playerUI.rand7_8);
		     player.playerUI.mypanel5.add(player.playerUI.rand7_9);
		     player.playerUI.mypanel5.add(player.playerUI.rand7_10);
		     player.playerUI.mypanel5.add(player.playerUI.rand7_11);
		     player.playerUI.mypanel5.add(player.playerUI.rand7_12);
		     player.playerUI.mypanel5.add(player.playerUI.rand7_13);
		     player.playerUI.mypanel5.add(player.playerUI.rand7_14);
		     player.playerUI.mypanel5.add(player.playerUI.rand7_15);
		     player.playerUI.mypanel5.add(player.playerUI.rand7_16);
		  
		  }
		  else if(tile == 6)
		  {
  	        iconPlayer1 =  (bp[4][8].getIcon());
	        iconPlayer2 =  (bp[5][8].getIcon());
 	        iconPlayer3 =  (bp[6][8].getIcon());
	        iconPlayer4 =  (bp[7][8].getIcon());
	        iconPlayer5 =  (bp[4][9].getIcon());
	        iconPlayer6 =  (bp[5][9].getIcon());
	        iconPlayer7 =  (bp[6][9].getIcon());
	        iconPlayer8 =  (bp[7][9].getIcon());
	        iconPlayer9 =  (bp[4][10].getIcon());
	        iconPlayer10 =  (bp[5][10].getIcon());
	        iconPlayer11 =  (bp[6][10].getIcon());
	        iconPlayer12 =  (bp[7][10].getIcon());
	        iconPlayer13 =  (bp[4][11].getIcon());
	        iconPlayer14 =  (bp[5][11].getIcon());
	        iconPlayer15 =  (bp[6][11].getIcon());
	        iconPlayer16 =  (bp[7][11].getIcon());

	
		//add components to board
			  GameJLabel boardPlayer1 = new GameJLabel(iconPlayer1);
			  GameJLabel boardPlayer2 = new GameJLabel(iconPlayer2);
			  GameJLabel boardPlayer3 = new GameJLabel(iconPlayer3);
			  GameJLabel boardPlayer4 = new GameJLabel(iconPlayer4);
			  GameJLabel boardPlayer5 = new GameJLabel(iconPlayer5);
			  GameJLabel boardPlayer6 = new GameJLabel(iconPlayer6);
			  GameJLabel boardPlayer7 = new GameJLabel(iconPlayer7);
			  GameJLabel boardPlayer8 = new GameJLabel(iconPlayer8);
			  GameJLabel boardPlayer9 = new GameJLabel(iconPlayer9);
			  GameJLabel boardPlayer10 = new GameJLabel(iconPlayer10);
			  GameJLabel boardPlayer11 = new GameJLabel(iconPlayer11);
			  GameJLabel boardPlayer12 = new GameJLabel(iconPlayer12);
			  GameJLabel boardPlayer13 = new GameJLabel(iconPlayer13);
			  GameJLabel boardPlayer14 = new GameJLabel(iconPlayer14);
			  GameJLabel boardPlayer15 = new GameJLabel(iconPlayer15);
			  GameJLabel boardPlayer16 = new GameJLabel(iconPlayer16);
		
			  player.playerUI.rand7_1.add(boardPlayer1, new Integer(0));
		     player.playerUI.rand7_2.add(boardPlayer2, new Integer(0));
		     player.playerUI.rand7_3.add(boardPlayer3, new Integer(0));
		     player.playerUI.rand7_4.add(boardPlayer4, new Integer(0));
		     player.playerUI.rand7_5.add(boardPlayer5, new Integer(0));
		     player.playerUI.rand7_6.add(boardPlayer6, new Integer(0));
		     player.playerUI.rand7_7.add(boardPlayer7, new Integer(0));
		     player.playerUI.rand7_8.add(boardPlayer8, new Integer(0));
		     player.playerUI.rand7_9.add(boardPlayer9, new Integer(0));
		     player.playerUI.rand7_10.add(boardPlayer10, new Integer(0));
		     player.playerUI.rand7_11.add(boardPlayer11, new Integer(0));
		     player.playerUI.rand7_12.add(boardPlayer12, new Integer(0));
		     player.playerUI.rand7_13.add(boardPlayer13, new Integer(0));
		     player.playerUI.rand7_14.add(boardPlayer14, new Integer(0));
		     player.playerUI.rand7_15.add(boardPlayer15, new Integer(0));
		     player.playerUI.rand7_16.add(boardPlayer16, new Integer(0));
		
		     boardPlayer1.setBounds(0, 0, iconPlayer1.getIconWidth(),
		                                  iconPlayer1.getIconHeight()); 
		     player.playerUI.rand7_1.setPreferredSize(new Dimension(iconPlayer1.getIconWidth(),
		                                        iconPlayer1.getIconHeight()));
		     boardPlayer2.setBounds(0, 0, iconPlayer2.getIconWidth(),
		                                  iconPlayer2.getIconHeight()); 
		     player.playerUI.rand7_2.setPreferredSize(new Dimension(iconPlayer2.getIconWidth(),
		                                        iconPlayer2.getIconHeight()));
		     boardPlayer3.setBounds(0, 0, iconPlayer3.getIconWidth(),
		                                  iconPlayer3.getIconHeight()); 
		     player.playerUI.rand7_3.setPreferredSize(new Dimension(iconPlayer3.getIconWidth(),
		                                        iconPlayer3.getIconHeight()));
		     boardPlayer4.setBounds(0, 0, iconPlayer4.getIconWidth(),
		                                  iconPlayer4.getIconHeight()); 
		     player.playerUI.rand7_4.setPreferredSize(new Dimension(iconPlayer4.getIconWidth(),
		                                        iconPlayer4.getIconHeight()));
		     boardPlayer5.setBounds(0, 0, iconPlayer5.getIconWidth(),
		                                  iconPlayer5.getIconHeight()); 
		     player.playerUI.rand7_5.setPreferredSize(new Dimension(iconPlayer5.getIconWidth(),
		                                        iconPlayer5.getIconHeight()));
		     boardPlayer6.setBounds(0, 0, iconPlayer6.getIconWidth(),
		                                  iconPlayer6.getIconHeight()); 
		     player.playerUI.rand7_6.setPreferredSize(new Dimension(iconPlayer6.getIconWidth(),
		                                        iconPlayer6.getIconHeight()));
		     boardPlayer7.setBounds(0, 0, iconPlayer7.getIconWidth(),
		                                  iconPlayer7.getIconHeight()); 
		     player.playerUI.rand7_7.setPreferredSize(new Dimension(iconPlayer7.getIconWidth(),
		                                        iconPlayer7.getIconHeight()));
		     boardPlayer8.setBounds(0, 0, iconPlayer8.getIconWidth(),
		                                  iconPlayer8.getIconHeight()); 
		     player.playerUI.rand7_8.setPreferredSize(new Dimension(iconPlayer8.getIconWidth(),
		                                        iconPlayer8.getIconHeight()));
		     boardPlayer9.setBounds(0, 0, iconPlayer9.getIconWidth(),
		                                  iconPlayer9.getIconHeight()); 
		     player.playerUI.rand7_9.setPreferredSize(new Dimension(iconPlayer9.getIconWidth(),
		                                        iconPlayer9.getIconHeight()));
		     boardPlayer10.setBounds(0, 0, iconPlayer10.getIconWidth(),
		                                  iconPlayer10.getIconHeight()); 
		     player.playerUI.rand7_10.setPreferredSize(new Dimension(iconPlayer10.getIconWidth(),
		                                        iconPlayer10.getIconHeight()));													 													 
		     boardPlayer11.setBounds(0, 0, iconPlayer11.getIconWidth(),
		                                  iconPlayer11.getIconHeight()); 
		     player.playerUI.rand7_11.setPreferredSize(new Dimension(iconPlayer11.getIconWidth(),
		                                        iconPlayer11.getIconHeight()));
		     boardPlayer12.setBounds(0, 0, iconPlayer12.getIconWidth(),
		                                  iconPlayer12.getIconHeight()); 
		     player.playerUI.rand7_12.setPreferredSize(new Dimension(iconPlayer12.getIconWidth(),
		                                        iconPlayer12.getIconHeight()));
		     boardPlayer13.setBounds(0, 0, iconPlayer13.getIconWidth(),
		                                  iconPlayer13.getIconHeight()); 
		     player.playerUI.rand7_13.setPreferredSize(new Dimension(iconPlayer13.getIconWidth(),
		                                        iconPlayer13.getIconHeight()));
		     boardPlayer14.setBounds(0, 0, iconPlayer14.getIconWidth(),
		                                  iconPlayer14.getIconHeight()); 
		     player.playerUI.rand7_14.setPreferredSize(new Dimension(iconPlayer14.getIconWidth(),
		                                        iconPlayer14.getIconHeight()));
		     boardPlayer15.setBounds(0, 0, iconPlayer15.getIconWidth(),
		                                  iconPlayer15.getIconHeight()); 
		     player.playerUI.rand7_15.setPreferredSize(new Dimension(iconPlayer15.getIconWidth(),
		                                        iconPlayer15.getIconHeight()));	
		     boardPlayer16.setBounds(0, 0, iconPlayer16.getIconWidth(),
		                                  iconPlayer16.getIconHeight()); 
		     player.playerUI.rand7_16.setPreferredSize(new Dimension(iconPlayer16.getIconWidth(),
		                                        iconPlayer16.getIconHeight()));													 												 													 
		
			  player.playerUI.mypanel6.add(player.playerUI.rand7_1);
		     player.playerUI.mypanel6.add(player.playerUI.rand7_2);
		     player.playerUI.mypanel6.add(player.playerUI.rand7_3);
		     player.playerUI.mypanel6.add(player.playerUI.rand7_4);
		     player.playerUI.mypanel6.add(player.playerUI.rand7_5);
		     player.playerUI.mypanel6.add(player.playerUI.rand7_6);
		     player.playerUI.mypanel6.add(player.playerUI.rand7_7);
		     player.playerUI.mypanel6.add(player.playerUI.rand7_8);
		     player.playerUI.mypanel6.add(player.playerUI.rand7_9);
		     player.playerUI.mypanel6.add(player.playerUI.rand7_10);
		     player.playerUI.mypanel6.add(player.playerUI.rand7_11);
		     player.playerUI.mypanel6.add(player.playerUI.rand7_12);
		     player.playerUI.mypanel6.add(player.playerUI.rand7_13);
		     player.playerUI.mypanel6.add(player.playerUI.rand7_14);
		     player.playerUI.mypanel6.add(player.playerUI.rand7_15);
		     player.playerUI.mypanel6.add(player.playerUI.rand7_16);
		  
		  }
		  else if(tile == 7)
		  {
	       iconPlayer1 =  bp[4][12].getIcon();
	       iconPlayer2 =  bp[5][12].getIcon();
	       iconPlayer3 =  bp[6][12].getIcon();
	       iconPlayer4 =  bp[7][12].getIcon();
	       iconPlayer5 =  bp[4][13].getIcon();
	       iconPlayer6 =  bp[5][13].getIcon();
	       iconPlayer7 =  bp[6][13].getIcon();
	       iconPlayer8 =  bp[7][13].getIcon();
	       iconPlayer9 =  bp[4][14].getIcon();
	       iconPlayer10 =  bp[5][14].getIcon();
	       iconPlayer11 =  bp[6][14].getIcon();
	       iconPlayer12 =  bp[7][14].getIcon();
	       iconPlayer13 =  bp[4][15].getIcon();
	       iconPlayer14 =  bp[5][15].getIcon();
	       iconPlayer15 =  bp[6][15].getIcon();
	       iconPlayer16 =  bp[7][15].getIcon();


		//add components to board
			  GameJLabel boardPlayer1 = new GameJLabel(iconPlayer1);
			  GameJLabel boardPlayer2 = new GameJLabel(iconPlayer2);
			  GameJLabel boardPlayer3 = new GameJLabel(iconPlayer3);
			  GameJLabel boardPlayer4 = new GameJLabel(iconPlayer4);
			  GameJLabel boardPlayer5 = new GameJLabel(iconPlayer5);
			  GameJLabel boardPlayer6 = new GameJLabel(iconPlayer6);
			  GameJLabel boardPlayer7 = new GameJLabel(iconPlayer7);
			  GameJLabel boardPlayer8 = new GameJLabel(iconPlayer8);
			  GameJLabel boardPlayer9 = new GameJLabel(iconPlayer9);
			  GameJLabel boardPlayer10 = new GameJLabel(iconPlayer10);
			  GameJLabel boardPlayer11 = new GameJLabel(iconPlayer11);
			  GameJLabel boardPlayer12 = new GameJLabel(iconPlayer12);
			  GameJLabel boardPlayer13 = new GameJLabel(iconPlayer13);
			  GameJLabel boardPlayer14 = new GameJLabel(iconPlayer14);
			  GameJLabel boardPlayer15 = new GameJLabel(iconPlayer15);
			  GameJLabel boardPlayer16 = new GameJLabel(iconPlayer16);
		
			  player.playerUI.rand7_1.add(boardPlayer1, new Integer(0));
		     player.playerUI.rand7_2.add(boardPlayer2, new Integer(0));
		     player.playerUI.rand7_3.add(boardPlayer3, new Integer(0));
		     player.playerUI.rand7_4.add(boardPlayer4, new Integer(0));
		     player.playerUI.rand7_5.add(boardPlayer5, new Integer(0));
		     player.playerUI.rand7_6.add(boardPlayer6, new Integer(0));
		     player.playerUI.rand7_7.add(boardPlayer7, new Integer(0));
		     player.playerUI.rand7_8.add(boardPlayer8, new Integer(0));
		     player.playerUI.rand7_9.add(boardPlayer9, new Integer(0));
		     player.playerUI.rand7_10.add(boardPlayer10, new Integer(0));
		     player.playerUI.rand7_11.add(boardPlayer11, new Integer(0));
		     player.playerUI.rand7_12.add(boardPlayer12, new Integer(0));
		     player.playerUI.rand7_13.add(boardPlayer13, new Integer(0));
		     player.playerUI.rand7_14.add(boardPlayer14, new Integer(0));
		     player.playerUI.rand7_15.add(boardPlayer15, new Integer(0));
		     player.playerUI.rand7_16.add(boardPlayer16, new Integer(0));
		
		     boardPlayer1.setBounds(0, 0, iconPlayer1.getIconWidth(),
		                                  iconPlayer1.getIconHeight()); 
		     player.playerUI.rand7_1.setPreferredSize(new Dimension(iconPlayer1.getIconWidth(),
		                                        iconPlayer1.getIconHeight()));
		     boardPlayer2.setBounds(0, 0, iconPlayer2.getIconWidth(),
		                                  iconPlayer2.getIconHeight()); 
		     player.playerUI.rand7_2.setPreferredSize(new Dimension(iconPlayer2.getIconWidth(),
		                                        iconPlayer2.getIconHeight()));
		     boardPlayer3.setBounds(0, 0, iconPlayer3.getIconWidth(),
		                                  iconPlayer3.getIconHeight()); 
		     player.playerUI.rand7_3.setPreferredSize(new Dimension(iconPlayer3.getIconWidth(),
		                                        iconPlayer3.getIconHeight()));
		     boardPlayer4.setBounds(0, 0, iconPlayer4.getIconWidth(),
		                                  iconPlayer4.getIconHeight()); 
		     player.playerUI.rand7_4.setPreferredSize(new Dimension(iconPlayer4.getIconWidth(),
		                                        iconPlayer4.getIconHeight()));
		     boardPlayer5.setBounds(0, 0, iconPlayer5.getIconWidth(),
		                                  iconPlayer5.getIconHeight()); 
		     player.playerUI.rand7_5.setPreferredSize(new Dimension(iconPlayer5.getIconWidth(),
		                                        iconPlayer5.getIconHeight()));
		     boardPlayer6.setBounds(0, 0, iconPlayer6.getIconWidth(),
		                                  iconPlayer6.getIconHeight()); 
		     player.playerUI.rand7_6.setPreferredSize(new Dimension(iconPlayer6.getIconWidth(),
		                                        iconPlayer6.getIconHeight()));
		     boardPlayer7.setBounds(0, 0, iconPlayer7.getIconWidth(),
		                                  iconPlayer7.getIconHeight()); 
		     player.playerUI.rand7_7.setPreferredSize(new Dimension(iconPlayer7.getIconWidth(),
		                                        iconPlayer7.getIconHeight()));
		     boardPlayer8.setBounds(0, 0, iconPlayer8.getIconWidth(),
		                                  iconPlayer8.getIconHeight()); 
		     player.playerUI.rand7_8.setPreferredSize(new Dimension(iconPlayer8.getIconWidth(),
		                                        iconPlayer8.getIconHeight()));
		     boardPlayer9.setBounds(0, 0, iconPlayer9.getIconWidth(),
		                                  iconPlayer9.getIconHeight()); 
		     player.playerUI.rand7_9.setPreferredSize(new Dimension(iconPlayer9.getIconWidth(),
		                                        iconPlayer9.getIconHeight()));
		     boardPlayer10.setBounds(0, 0, iconPlayer10.getIconWidth(),
		                                  iconPlayer10.getIconHeight()); 
		     player.playerUI.rand7_10.setPreferredSize(new Dimension(iconPlayer10.getIconWidth(),
		                                        iconPlayer10.getIconHeight()));													 													 
		     boardPlayer11.setBounds(0, 0, iconPlayer11.getIconWidth(),
		                                  iconPlayer11.getIconHeight()); 
		     player.playerUI.rand7_11.setPreferredSize(new Dimension(iconPlayer11.getIconWidth(),
		                                        iconPlayer11.getIconHeight()));
		     boardPlayer12.setBounds(0, 0, iconPlayer12.getIconWidth(),
		                                  iconPlayer12.getIconHeight()); 
		     player.playerUI.rand7_12.setPreferredSize(new Dimension(iconPlayer12.getIconWidth(),
		                                        iconPlayer12.getIconHeight()));
		     boardPlayer13.setBounds(0, 0, iconPlayer13.getIconWidth(),
		                                  iconPlayer13.getIconHeight()); 
		     player.playerUI.rand7_13.setPreferredSize(new Dimension(iconPlayer13.getIconWidth(),
		                                        iconPlayer13.getIconHeight()));
		     boardPlayer14.setBounds(0, 0, iconPlayer14.getIconWidth(),
		                                  iconPlayer14.getIconHeight()); 
		     player.playerUI.rand7_14.setPreferredSize(new Dimension(iconPlayer14.getIconWidth(),
		                                        iconPlayer14.getIconHeight()));
		     boardPlayer15.setBounds(0, 0, iconPlayer15.getIconWidth(),
		                                  iconPlayer15.getIconHeight()); 
		     player.playerUI.rand7_15.setPreferredSize(new Dimension(iconPlayer15.getIconWidth(),
		                                        iconPlayer15.getIconHeight()));	
		     boardPlayer16.setBounds(0, 0, iconPlayer16.getIconWidth(),
		                                  iconPlayer16.getIconHeight()); 
		     player.playerUI.rand7_16.setPreferredSize(new Dimension(iconPlayer16.getIconWidth(),
		                                        iconPlayer16.getIconHeight()));													 												 													 
		
			  player.playerUI.mypanel7.add(player.playerUI.rand7_1);
		     player.playerUI.mypanel7.add(player.playerUI.rand7_2);
		     player.playerUI.mypanel7.add(player.playerUI.rand7_3);
		     player.playerUI.mypanel7.add(player.playerUI.rand7_4);
		     player.playerUI.mypanel7.add(player.playerUI.rand7_5);
		     player.playerUI.mypanel7.add(player.playerUI.rand7_6);
		     player.playerUI.mypanel7.add(player.playerUI.rand7_7);
		     player.playerUI.mypanel7.add(player.playerUI.rand7_8);
		     player.playerUI.mypanel7.add(player.playerUI.rand7_9);
		     player.playerUI.mypanel7.add(player.playerUI.rand7_10);
		     player.playerUI.mypanel7.add(player.playerUI.rand7_11);
		     player.playerUI.mypanel7.add(player.playerUI.rand7_12);
		     player.playerUI.mypanel7.add(player.playerUI.rand7_13);
		     player.playerUI.mypanel7.add(player.playerUI.rand7_14);
		     player.playerUI.mypanel7.add(player.playerUI.rand7_15);
		     player.playerUI.mypanel7.add(player.playerUI.rand7_16);
		  
		  }
		}

      private void populateRand8(int tile, Player player)
		{
		  if(tile == 0)
		  {
			  iconPlayer1 =  (bp[0][0].getIcon());
	        iconPlayer2 =  (bp[1][0].getIcon());
 	        iconPlayer3 =  (bp[2][0].getIcon());
	        iconPlayer4 =  (bp[3][0].getIcon());
	        iconPlayer5 =  (bp[0][1].getIcon());
	        iconPlayer6 =  (bp[1][1].getIcon());
	        iconPlayer7 =  (bp[2][1].getIcon());
	        iconPlayer8 =  (bp[3][1].getIcon());
	        iconPlayer9 =  (bp[0][2].getIcon());
	        iconPlayer10 =  (bp[1][2].getIcon());
	        iconPlayer11 =  (bp[2][2].getIcon());
	        iconPlayer12 =  (bp[3][2].getIcon());
	        iconPlayer13 =  (bp[0][3].getIcon());
	        iconPlayer14 =  (bp[1][3].getIcon());
	        iconPlayer15 =  (bp[2][3].getIcon());
	        iconPlayer16 =  (bp[3][3].getIcon());

	
		//add components to board
			  GameJLabel boardPlayer1 = new GameJLabel(iconPlayer1);
			  GameJLabel boardPlayer2 = new GameJLabel(iconPlayer2);
			  GameJLabel boardPlayer3 = new GameJLabel(iconPlayer3);
			  GameJLabel boardPlayer4 = new GameJLabel(iconPlayer4);
			  GameJLabel boardPlayer5 = new GameJLabel(iconPlayer5);
			  GameJLabel boardPlayer6 = new GameJLabel(iconPlayer6);
			  GameJLabel boardPlayer7 = new GameJLabel(iconPlayer7);
			  GameJLabel boardPlayer8 = new GameJLabel(iconPlayer8);
			  GameJLabel boardPlayer9 = new GameJLabel(iconPlayer9);
			  GameJLabel boardPlayer10 = new GameJLabel(iconPlayer10);
			  GameJLabel boardPlayer11 = new GameJLabel(iconPlayer11);
			  GameJLabel boardPlayer12 = new GameJLabel(iconPlayer12);
			  GameJLabel boardPlayer13 = new GameJLabel(iconPlayer13);
			  GameJLabel boardPlayer14 = new GameJLabel(iconPlayer14);
			  GameJLabel boardPlayer15 = new GameJLabel(iconPlayer15);
			  GameJLabel boardPlayer16 = new GameJLabel(iconPlayer16);
		
			  player.playerUI.rand8_1.add(boardPlayer1, new Integer(0));
		     player.playerUI.rand8_2.add(boardPlayer2, new Integer(0));
		     player.playerUI.rand8_3.add(boardPlayer3, new Integer(0));
		     player.playerUI.rand8_4.add(boardPlayer4, new Integer(0));
		     player.playerUI.rand8_5.add(boardPlayer5, new Integer(0));
		     player.playerUI.rand8_6.add(boardPlayer6, new Integer(0));
		     player.playerUI.rand8_7.add(boardPlayer7, new Integer(0));
		     player.playerUI.rand8_8.add(boardPlayer8, new Integer(0));
		     player.playerUI.rand8_9.add(boardPlayer9, new Integer(0));
		     player.playerUI.rand8_10.add(boardPlayer10, new Integer(0));
		     player.playerUI.rand8_11.add(boardPlayer11, new Integer(0));
		     player.playerUI.rand8_12.add(boardPlayer12, new Integer(0));
		     player.playerUI.rand8_13.add(boardPlayer13, new Integer(0));
		     player.playerUI.rand8_14.add(boardPlayer14, new Integer(0));
		     player.playerUI.rand8_15.add(boardPlayer15, new Integer(0));
		     player.playerUI.rand8_16.add(boardPlayer16, new Integer(0));
		
		     boardPlayer1.setBounds(0, 0, iconPlayer1.getIconWidth(),
		                                  iconPlayer1.getIconHeight()); 
		     player.playerUI.rand8_1.setPreferredSize(new Dimension(iconPlayer1.getIconWidth(),
		                                        iconPlayer1.getIconHeight()));
		     boardPlayer2.setBounds(0, 0, iconPlayer2.getIconWidth(),
		                                  iconPlayer2.getIconHeight()); 
		     player.playerUI.rand8_2.setPreferredSize(new Dimension(iconPlayer2.getIconWidth(),
		                                        iconPlayer2.getIconHeight()));
		     boardPlayer3.setBounds(0, 0, iconPlayer3.getIconWidth(),
		                                  iconPlayer3.getIconHeight()); 
		     player.playerUI.rand8_3.setPreferredSize(new Dimension(iconPlayer3.getIconWidth(),
		                                        iconPlayer3.getIconHeight()));
		     boardPlayer4.setBounds(0, 0, iconPlayer4.getIconWidth(),
		                                  iconPlayer4.getIconHeight()); 
		     player.playerUI.rand8_4.setPreferredSize(new Dimension(iconPlayer4.getIconWidth(),
		                                        iconPlayer4.getIconHeight()));
		     boardPlayer5.setBounds(0, 0, iconPlayer5.getIconWidth(),
		                                  iconPlayer5.getIconHeight()); 
		     player.playerUI.rand8_5.setPreferredSize(new Dimension(iconPlayer5.getIconWidth(),
		                                        iconPlayer5.getIconHeight()));
		     boardPlayer6.setBounds(0, 0, iconPlayer6.getIconWidth(),
		                                  iconPlayer6.getIconHeight()); 
		     player.playerUI.rand8_6.setPreferredSize(new Dimension(iconPlayer6.getIconWidth(),
		                                        iconPlayer6.getIconHeight()));
		     boardPlayer7.setBounds(0, 0, iconPlayer7.getIconWidth(),
		                                  iconPlayer7.getIconHeight()); 
		     player.playerUI.rand8_7.setPreferredSize(new Dimension(iconPlayer7.getIconWidth(),
		                                        iconPlayer7.getIconHeight()));
		     boardPlayer8.setBounds(0, 0, iconPlayer8.getIconWidth(),
		                                  iconPlayer8.getIconHeight()); 
		     player.playerUI.rand8_8.setPreferredSize(new Dimension(iconPlayer8.getIconWidth(),
		                                        iconPlayer8.getIconHeight()));
		     boardPlayer9.setBounds(0, 0, iconPlayer9.getIconWidth(),
		                                  iconPlayer9.getIconHeight()); 
		     player.playerUI.rand8_9.setPreferredSize(new Dimension(iconPlayer9.getIconWidth(),
		                                        iconPlayer9.getIconHeight()));
		     boardPlayer10.setBounds(0, 0, iconPlayer10.getIconWidth(),
		                                  iconPlayer10.getIconHeight()); 
		     player.playerUI.rand8_10.setPreferredSize(new Dimension(iconPlayer10.getIconWidth(),
		                                        iconPlayer10.getIconHeight()));													 													 
		     boardPlayer11.setBounds(0, 0, iconPlayer11.getIconWidth(),
		                                  iconPlayer11.getIconHeight()); 
		     player.playerUI.rand8_11.setPreferredSize(new Dimension(iconPlayer11.getIconWidth(),
		                                        iconPlayer11.getIconHeight()));
		     boardPlayer12.setBounds(0, 0, iconPlayer12.getIconWidth(),
		                                  iconPlayer12.getIconHeight()); 
		     player.playerUI.rand8_12.setPreferredSize(new Dimension(iconPlayer12.getIconWidth(),
		                                        iconPlayer12.getIconHeight()));
		     boardPlayer13.setBounds(0, 0, iconPlayer13.getIconWidth(),
		                                  iconPlayer13.getIconHeight()); 
		     player.playerUI.rand8_13.setPreferredSize(new Dimension(iconPlayer13.getIconWidth(),
		                                        iconPlayer13.getIconHeight()));
		     boardPlayer14.setBounds(0, 0, iconPlayer14.getIconWidth(),
		                                  iconPlayer14.getIconHeight()); 
		     player.playerUI.rand8_14.setPreferredSize(new Dimension(iconPlayer14.getIconWidth(),
		                                        iconPlayer14.getIconHeight()));
		     boardPlayer15.setBounds(0, 0, iconPlayer15.getIconWidth(),
		                                  iconPlayer15.getIconHeight()); 
		     player.playerUI.rand8_15.setPreferredSize(new Dimension(iconPlayer15.getIconWidth(),
		                                        iconPlayer15.getIconHeight()));	
		     boardPlayer16.setBounds(0, 0, iconPlayer16.getIconWidth(),
		                                  iconPlayer16.getIconHeight()); 
		     player.playerUI.rand8_16.setPreferredSize(new Dimension(iconPlayer16.getIconWidth(),
		                                        iconPlayer16.getIconHeight()));													 												 													 
		
			  player.playerUI.mypanel0.add(player.playerUI.rand8_1);
		     player.playerUI.mypanel0.add(player.playerUI.rand8_2);
		     player.playerUI.mypanel0.add(player.playerUI.rand8_3);
		     player.playerUI.mypanel0.add(player.playerUI.rand8_4);
		     player.playerUI.mypanel0.add(player.playerUI.rand8_5);
		     player.playerUI.mypanel0.add(player.playerUI.rand8_6);
		     player.playerUI.mypanel0.add(player.playerUI.rand8_7);
		     player.playerUI.mypanel0.add(player.playerUI.rand8_8);
		     player.playerUI.mypanel0.add(player.playerUI.rand8_9);
		     player.playerUI.mypanel0.add(player.playerUI.rand8_10);
		     player.playerUI.mypanel0.add(player.playerUI.rand8_11);
		     player.playerUI.mypanel0.add(player.playerUI.rand8_12);
		     player.playerUI.mypanel0.add(player.playerUI.rand8_13);
		     player.playerUI.mypanel0.add(player.playerUI.rand8_14);
		     player.playerUI.mypanel0.add(player.playerUI.rand8_15);
		     player.playerUI.mypanel0.add(player.playerUI.rand8_16);
		  }
		  else if(tile == 1)
		  {
  	        iconPlayer1 =  (bp[0][4].getIcon());
	        iconPlayer2 =  (bp[1][4].getIcon());
 	        iconPlayer3 =  (bp[2][4].getIcon());
	        iconPlayer4 =  (bp[3][4].getIcon());
	        iconPlayer5 =  (bp[0][5].getIcon());
	        iconPlayer6 =  (bp[1][5].getIcon());
	        iconPlayer7 =  (bp[2][5].getIcon());
	        iconPlayer8 =  (bp[3][5].getIcon());
	        iconPlayer9 =  (bp[0][6].getIcon());
	        iconPlayer10 =  (bp[1][6].getIcon());
	        iconPlayer11 =  (bp[2][6].getIcon());
	        iconPlayer12 =  (bp[3][6].getIcon());
	        iconPlayer13 =  (bp[0][7].getIcon());
	        iconPlayer14 =  (bp[1][7].getIcon());
	        iconPlayer15 =  (bp[2][7].getIcon());
	        iconPlayer16 =  (bp[3][7].getIcon());

	
		//add components to board
			  GameJLabel boardPlayer1 = new GameJLabel(iconPlayer1);
			  GameJLabel boardPlayer2 = new GameJLabel(iconPlayer2);
			  GameJLabel boardPlayer3 = new GameJLabel(iconPlayer3);
			  GameJLabel boardPlayer4 = new GameJLabel(iconPlayer4);
			  GameJLabel boardPlayer5 = new GameJLabel(iconPlayer5);
			  GameJLabel boardPlayer6 = new GameJLabel(iconPlayer6);
			  GameJLabel boardPlayer7 = new GameJLabel(iconPlayer7);
			  GameJLabel boardPlayer8 = new GameJLabel(iconPlayer8);
			  GameJLabel boardPlayer9 = new GameJLabel(iconPlayer9);
			  GameJLabel boardPlayer10 = new GameJLabel(iconPlayer10);
			  GameJLabel boardPlayer11 = new GameJLabel(iconPlayer11);
			  GameJLabel boardPlayer12 = new GameJLabel(iconPlayer12);
			  GameJLabel boardPlayer13 = new GameJLabel(iconPlayer13);
			  GameJLabel boardPlayer14 = new GameJLabel(iconPlayer14);
			  GameJLabel boardPlayer15 = new GameJLabel(iconPlayer15);
			  GameJLabel boardPlayer16 = new GameJLabel(iconPlayer16);
		
			  player.playerUI.rand8_1.add(boardPlayer1, new Integer(0));
		     player.playerUI.rand8_2.add(boardPlayer2, new Integer(0));
		     player.playerUI.rand8_3.add(boardPlayer3, new Integer(0));
		     player.playerUI.rand8_4.add(boardPlayer4, new Integer(0));
		     player.playerUI.rand8_5.add(boardPlayer5, new Integer(0));
		     player.playerUI.rand8_6.add(boardPlayer6, new Integer(0));
		     player.playerUI.rand8_7.add(boardPlayer7, new Integer(0));
		     player.playerUI.rand8_8.add(boardPlayer8, new Integer(0));
		     player.playerUI.rand8_9.add(boardPlayer9, new Integer(0));
		     player.playerUI.rand8_10.add(boardPlayer10, new Integer(0));
		     player.playerUI.rand8_11.add(boardPlayer11, new Integer(0));
		     player.playerUI.rand8_12.add(boardPlayer12, new Integer(0));
		     player.playerUI.rand8_13.add(boardPlayer13, new Integer(0));
		     player.playerUI.rand8_14.add(boardPlayer14, new Integer(0));
		     player.playerUI.rand8_15.add(boardPlayer15, new Integer(0));
		     player.playerUI.rand8_16.add(boardPlayer16, new Integer(0));
		
		     boardPlayer1.setBounds(0, 0, iconPlayer1.getIconWidth(),
		                                  iconPlayer1.getIconHeight()); 
		     player.playerUI.rand8_1.setPreferredSize(new Dimension(iconPlayer1.getIconWidth(),
		                                        iconPlayer1.getIconHeight()));
		     boardPlayer2.setBounds(0, 0, iconPlayer2.getIconWidth(),
		                                  iconPlayer2.getIconHeight()); 
		     player.playerUI.rand8_2.setPreferredSize(new Dimension(iconPlayer2.getIconWidth(),
		                                        iconPlayer2.getIconHeight()));
		     boardPlayer3.setBounds(0, 0, iconPlayer3.getIconWidth(),
		                                  iconPlayer3.getIconHeight()); 
		     player.playerUI.rand8_3.setPreferredSize(new Dimension(iconPlayer3.getIconWidth(),
		                                        iconPlayer3.getIconHeight()));
		     boardPlayer4.setBounds(0, 0, iconPlayer4.getIconWidth(),
		                                  iconPlayer4.getIconHeight()); 
		     player.playerUI.rand8_4.setPreferredSize(new Dimension(iconPlayer4.getIconWidth(),
		                                        iconPlayer4.getIconHeight()));
		     boardPlayer5.setBounds(0, 0, iconPlayer5.getIconWidth(),
		                                  iconPlayer5.getIconHeight()); 
		     player.playerUI.rand8_5.setPreferredSize(new Dimension(iconPlayer5.getIconWidth(),
		                                        iconPlayer5.getIconHeight()));
		     boardPlayer6.setBounds(0, 0, iconPlayer6.getIconWidth(),
		                                  iconPlayer6.getIconHeight()); 
		     player.playerUI.rand8_6.setPreferredSize(new Dimension(iconPlayer6.getIconWidth(),
		                                        iconPlayer6.getIconHeight()));
		     boardPlayer7.setBounds(0, 0, iconPlayer7.getIconWidth(),
		                                  iconPlayer7.getIconHeight()); 
		     player.playerUI.rand8_7.setPreferredSize(new Dimension(iconPlayer7.getIconWidth(),
		                                        iconPlayer7.getIconHeight()));
		     boardPlayer8.setBounds(0, 0, iconPlayer8.getIconWidth(),
		                                  iconPlayer8.getIconHeight()); 
		     player.playerUI.rand8_8.setPreferredSize(new Dimension(iconPlayer8.getIconWidth(),
		                                        iconPlayer8.getIconHeight()));
		     boardPlayer9.setBounds(0, 0, iconPlayer9.getIconWidth(),
		                                  iconPlayer9.getIconHeight()); 
		     player.playerUI.rand8_9.setPreferredSize(new Dimension(iconPlayer9.getIconWidth(),
		                                        iconPlayer9.getIconHeight()));
		     boardPlayer10.setBounds(0, 0, iconPlayer10.getIconWidth(),
		                                  iconPlayer10.getIconHeight()); 
		     player.playerUI.rand8_10.setPreferredSize(new Dimension(iconPlayer10.getIconWidth(),
		                                        iconPlayer10.getIconHeight()));													 													 
		     boardPlayer11.setBounds(0, 0, iconPlayer11.getIconWidth(),
		                                  iconPlayer11.getIconHeight()); 
		     player.playerUI.rand8_11.setPreferredSize(new Dimension(iconPlayer11.getIconWidth(),
		                                        iconPlayer11.getIconHeight()));
		     boardPlayer12.setBounds(0, 0, iconPlayer12.getIconWidth(),
		                                  iconPlayer12.getIconHeight()); 
		     player.playerUI.rand8_12.setPreferredSize(new Dimension(iconPlayer12.getIconWidth(),
		                                        iconPlayer12.getIconHeight()));
		     boardPlayer13.setBounds(0, 0, iconPlayer13.getIconWidth(),
		                                  iconPlayer13.getIconHeight()); 
		     player.playerUI.rand8_13.setPreferredSize(new Dimension(iconPlayer13.getIconWidth(),
		                                        iconPlayer13.getIconHeight()));
		     boardPlayer14.setBounds(0, 0, iconPlayer14.getIconWidth(),
		                                  iconPlayer14.getIconHeight()); 
		     player.playerUI.rand8_14.setPreferredSize(new Dimension(iconPlayer14.getIconWidth(),
		                                        iconPlayer14.getIconHeight()));
		     boardPlayer15.setBounds(0, 0, iconPlayer15.getIconWidth(),
		                                  iconPlayer15.getIconHeight()); 
		     player.playerUI.rand8_15.setPreferredSize(new Dimension(iconPlayer15.getIconWidth(),
		                                        iconPlayer15.getIconHeight()));	
		     boardPlayer16.setBounds(0, 0, iconPlayer16.getIconWidth(),
		                                  iconPlayer16.getIconHeight()); 
		     player.playerUI.rand8_16.setPreferredSize(new Dimension(iconPlayer16.getIconWidth(),
		                                        iconPlayer16.getIconHeight()));													 												 													 
		
			  player.playerUI.mypanel1.add(player.playerUI.rand8_1);
		     player.playerUI.mypanel1.add(player.playerUI.rand8_2);
		     player.playerUI.mypanel1.add(player.playerUI.rand8_3);
		     player.playerUI.mypanel1.add(player.playerUI.rand8_4);
		     player.playerUI.mypanel1.add(player.playerUI.rand8_5);
		     player.playerUI.mypanel1.add(player.playerUI.rand8_6);
		     player.playerUI.mypanel1.add(player.playerUI.rand8_7);
		     player.playerUI.mypanel1.add(player.playerUI.rand8_8);
		     player.playerUI.mypanel1.add(player.playerUI.rand8_9);
		     player.playerUI.mypanel1.add(player.playerUI.rand8_10);
		     player.playerUI.mypanel1.add(player.playerUI.rand8_11);
		     player.playerUI.mypanel1.add(player.playerUI.rand8_12);
		     player.playerUI.mypanel1.add(player.playerUI.rand8_13);
		     player.playerUI.mypanel1.add(player.playerUI.rand8_14);
		     player.playerUI.mypanel1.add(player.playerUI.rand8_15);
		     player.playerUI.mypanel1.add(player.playerUI.rand8_16);
		  
		  }
		  else if(tile == 2)
		  {
  	        iconPlayer1 =  (bp[0][8].getIcon());
	        iconPlayer2 =  (bp[1][8].getIcon());
 	        iconPlayer3 =  (bp[2][8].getIcon());
	        iconPlayer4 =  (bp[3][8].getIcon());
	        iconPlayer5 =  (bp[0][9].getIcon());
	        iconPlayer6 =  (bp[1][9].getIcon());
	        iconPlayer7 =  (bp[2][9].getIcon());
	        iconPlayer8 =  (bp[3][9].getIcon());
	        iconPlayer9 =  (bp[0][10].getIcon());
	        iconPlayer10 =  (bp[1][10].getIcon());
	        iconPlayer11 =  (bp[2][10].getIcon());
	        iconPlayer12 =  (bp[3][10].getIcon());
	        iconPlayer13 =  (bp[0][11].getIcon());
	        iconPlayer14 =  (bp[1][11].getIcon());
	        iconPlayer15 =  (bp[2][11].getIcon());
	        iconPlayer16 =  (bp[3][11].getIcon());

	
		//add components to board
			  GameJLabel boardPlayer1 = new GameJLabel(iconPlayer1);
			  GameJLabel boardPlayer2 = new GameJLabel(iconPlayer2);
			  GameJLabel boardPlayer3 = new GameJLabel(iconPlayer3);
			  GameJLabel boardPlayer4 = new GameJLabel(iconPlayer4);
			  GameJLabel boardPlayer5 = new GameJLabel(iconPlayer5);
			  GameJLabel boardPlayer6 = new GameJLabel(iconPlayer6);
			  GameJLabel boardPlayer7 = new GameJLabel(iconPlayer7);
			  GameJLabel boardPlayer8 = new GameJLabel(iconPlayer8);
			  GameJLabel boardPlayer9 = new GameJLabel(iconPlayer9);
			  GameJLabel boardPlayer10 = new GameJLabel(iconPlayer10);
			  GameJLabel boardPlayer11 = new GameJLabel(iconPlayer11);
			  GameJLabel boardPlayer12 = new GameJLabel(iconPlayer12);
			  GameJLabel boardPlayer13 = new GameJLabel(iconPlayer13);
			  GameJLabel boardPlayer14 = new GameJLabel(iconPlayer14);
			  GameJLabel boardPlayer15 = new GameJLabel(iconPlayer15);
			  GameJLabel boardPlayer16 = new GameJLabel(iconPlayer16);
		
			  player.playerUI.rand8_1.add(boardPlayer1, new Integer(0));
		     player.playerUI.rand8_2.add(boardPlayer2, new Integer(0));
		     player.playerUI.rand8_3.add(boardPlayer3, new Integer(0));
		     player.playerUI.rand8_4.add(boardPlayer4, new Integer(0));
		     player.playerUI.rand8_5.add(boardPlayer5, new Integer(0));
		     player.playerUI.rand8_6.add(boardPlayer6, new Integer(0));
		     player.playerUI.rand8_7.add(boardPlayer7, new Integer(0));
		     player.playerUI.rand8_8.add(boardPlayer8, new Integer(0));
		     player.playerUI.rand8_9.add(boardPlayer9, new Integer(0));
		     player.playerUI.rand8_10.add(boardPlayer10, new Integer(0));
		     player.playerUI.rand8_11.add(boardPlayer11, new Integer(0));
		     player.playerUI.rand8_12.add(boardPlayer12, new Integer(0));
		     player.playerUI.rand8_13.add(boardPlayer13, new Integer(0));
		     player.playerUI.rand8_14.add(boardPlayer14, new Integer(0));
		     player.playerUI.rand8_15.add(boardPlayer15, new Integer(0));
		     player.playerUI.rand8_16.add(boardPlayer16, new Integer(0));
		
		     boardPlayer1.setBounds(0, 0, iconPlayer1.getIconWidth(),
		                                  iconPlayer1.getIconHeight()); 
		     player.playerUI.rand8_1.setPreferredSize(new Dimension(iconPlayer1.getIconWidth(),
		                                        iconPlayer1.getIconHeight()));
		     boardPlayer2.setBounds(0, 0, iconPlayer2.getIconWidth(),
		                                  iconPlayer2.getIconHeight()); 
		     player.playerUI.rand8_2.setPreferredSize(new Dimension(iconPlayer2.getIconWidth(),
		                                        iconPlayer2.getIconHeight()));
		     boardPlayer3.setBounds(0, 0, iconPlayer3.getIconWidth(),
		                                  iconPlayer3.getIconHeight()); 
		     player.playerUI.rand8_3.setPreferredSize(new Dimension(iconPlayer3.getIconWidth(),
		                                        iconPlayer3.getIconHeight()));
		     boardPlayer4.setBounds(0, 0, iconPlayer4.getIconWidth(),
		                                  iconPlayer4.getIconHeight()); 
		     player.playerUI.rand8_4.setPreferredSize(new Dimension(iconPlayer4.getIconWidth(),
		                                        iconPlayer4.getIconHeight()));
		     boardPlayer5.setBounds(0, 0, iconPlayer5.getIconWidth(),
		                                  iconPlayer5.getIconHeight()); 
		     player.playerUI.rand8_5.setPreferredSize(new Dimension(iconPlayer5.getIconWidth(),
		                                        iconPlayer5.getIconHeight()));
		     boardPlayer6.setBounds(0, 0, iconPlayer6.getIconWidth(),
		                                  iconPlayer6.getIconHeight()); 
		     player.playerUI.rand8_6.setPreferredSize(new Dimension(iconPlayer6.getIconWidth(),
		                                        iconPlayer6.getIconHeight()));
		     boardPlayer7.setBounds(0, 0, iconPlayer7.getIconWidth(),
		                                  iconPlayer7.getIconHeight()); 
		     player.playerUI.rand8_7.setPreferredSize(new Dimension(iconPlayer7.getIconWidth(),
		                                        iconPlayer7.getIconHeight()));
		     boardPlayer8.setBounds(0, 0, iconPlayer8.getIconWidth(),
		                                  iconPlayer8.getIconHeight()); 
		     player.playerUI.rand8_8.setPreferredSize(new Dimension(iconPlayer8.getIconWidth(),
		                                        iconPlayer8.getIconHeight()));
		     boardPlayer9.setBounds(0, 0, iconPlayer9.getIconWidth(),
		                                  iconPlayer9.getIconHeight()); 
		     player.playerUI.rand8_9.setPreferredSize(new Dimension(iconPlayer9.getIconWidth(),
		                                        iconPlayer9.getIconHeight()));
		     boardPlayer10.setBounds(0, 0, iconPlayer10.getIconWidth(),
		                                  iconPlayer10.getIconHeight()); 
		     player.playerUI.rand8_10.setPreferredSize(new Dimension(iconPlayer10.getIconWidth(),
		                                        iconPlayer10.getIconHeight()));													 													 
		     boardPlayer11.setBounds(0, 0, iconPlayer11.getIconWidth(),
		                                  iconPlayer11.getIconHeight()); 
		     player.playerUI.rand8_11.setPreferredSize(new Dimension(iconPlayer11.getIconWidth(),
		                                        iconPlayer11.getIconHeight()));
		     boardPlayer12.setBounds(0, 0, iconPlayer12.getIconWidth(),
		                                  iconPlayer12.getIconHeight()); 
		     player.playerUI.rand8_12.setPreferredSize(new Dimension(iconPlayer12.getIconWidth(),
		                                        iconPlayer12.getIconHeight()));
		     boardPlayer13.setBounds(0, 0, iconPlayer13.getIconWidth(),
		                                  iconPlayer13.getIconHeight()); 
		     player.playerUI.rand8_13.setPreferredSize(new Dimension(iconPlayer13.getIconWidth(),
		                                        iconPlayer13.getIconHeight()));
		     boardPlayer14.setBounds(0, 0, iconPlayer14.getIconWidth(),
		                                  iconPlayer14.getIconHeight()); 
		     player.playerUI.rand8_14.setPreferredSize(new Dimension(iconPlayer14.getIconWidth(),
		                                        iconPlayer14.getIconHeight()));
		     boardPlayer15.setBounds(0, 0, iconPlayer15.getIconWidth(),
		                                  iconPlayer15.getIconHeight()); 
		     player.playerUI.rand8_15.setPreferredSize(new Dimension(iconPlayer15.getIconWidth(),
		                                        iconPlayer15.getIconHeight()));	
		     boardPlayer16.setBounds(0, 0, iconPlayer16.getIconWidth(),
		                                  iconPlayer16.getIconHeight()); 
		     player.playerUI.rand8_16.setPreferredSize(new Dimension(iconPlayer16.getIconWidth(),
		                                        iconPlayer16.getIconHeight()));													 												 													 
		
			  player.playerUI.mypanel2.add(player.playerUI.rand8_1);
		     player.playerUI.mypanel2.add(player.playerUI.rand8_2);
		     player.playerUI.mypanel2.add(player.playerUI.rand8_3);
		     player.playerUI.mypanel2.add(player.playerUI.rand8_4);
		     player.playerUI.mypanel2.add(player.playerUI.rand8_5);
		     player.playerUI.mypanel2.add(player.playerUI.rand8_6);
		     player.playerUI.mypanel2.add(player.playerUI.rand8_7);
		     player.playerUI.mypanel2.add(player.playerUI.rand8_8);
		     player.playerUI.mypanel2.add(player.playerUI.rand8_9);
		     player.playerUI.mypanel2.add(player.playerUI.rand8_10);
		     player.playerUI.mypanel2.add(player.playerUI.rand8_11);
		     player.playerUI.mypanel2.add(player.playerUI.rand8_12);
		     player.playerUI.mypanel2.add(player.playerUI.rand8_13);
		     player.playerUI.mypanel2.add(player.playerUI.rand8_14);
		     player.playerUI.mypanel2.add(player.playerUI.rand8_15);
		     player.playerUI.mypanel2.add(player.playerUI.rand8_16);
		  
		  }
		  else if(tile == 5)
		  {
  	        iconPlayer1 =  (bp[4][4].getIcon());
	        iconPlayer2 =  (bp[5][4].getIcon());
 	        iconPlayer3 =  (bp[6][4].getIcon());
	        iconPlayer4 =  (bp[7][4].getIcon());
	        iconPlayer5 =  (bp[4][5].getIcon());
	        iconPlayer6 =  (bp[5][5].getIcon());
	        iconPlayer7 =  (bp[6][5].getIcon());
	        iconPlayer8 =  (bp[7][5].getIcon());
	        iconPlayer9 =  (bp[4][6].getIcon());
	        iconPlayer10 =  (bp[5][6].getIcon());
	        iconPlayer11 =  (bp[6][6].getIcon());
	        iconPlayer12 =  (bp[7][6].getIcon());
	        iconPlayer13 =  (bp[4][7].getIcon());
	        iconPlayer14 =  (bp[5][7].getIcon());
	        iconPlayer15 =  (bp[6][7].getIcon());
	        iconPlayer16 =  (bp[7][7].getIcon());

	
		//add components to board
			  GameJLabel boardPlayer1 = new GameJLabel(iconPlayer1);
			  GameJLabel boardPlayer2 = new GameJLabel(iconPlayer2);
			  GameJLabel boardPlayer3 = new GameJLabel(iconPlayer3);
			  GameJLabel boardPlayer4 = new GameJLabel(iconPlayer4);
			  GameJLabel boardPlayer5 = new GameJLabel(iconPlayer5);
			  GameJLabel boardPlayer6 = new GameJLabel(iconPlayer6);
			  GameJLabel boardPlayer7 = new GameJLabel(iconPlayer7);
			  GameJLabel boardPlayer8 = new GameJLabel(iconPlayer8);
			  GameJLabel boardPlayer9 = new GameJLabel(iconPlayer9);
			  GameJLabel boardPlayer10 = new GameJLabel(iconPlayer10);
			  GameJLabel boardPlayer11 = new GameJLabel(iconPlayer11);
			  GameJLabel boardPlayer12 = new GameJLabel(iconPlayer12);
			  GameJLabel boardPlayer13 = new GameJLabel(iconPlayer13);
			  GameJLabel boardPlayer14 = new GameJLabel(iconPlayer14);
			  GameJLabel boardPlayer15 = new GameJLabel(iconPlayer15);
			  GameJLabel boardPlayer16 = new GameJLabel(iconPlayer16);
		
			  player.playerUI.rand8_1.add(boardPlayer1, new Integer(0));
		     player.playerUI.rand8_2.add(boardPlayer2, new Integer(0));
		     player.playerUI.rand8_3.add(boardPlayer3, new Integer(0));
		     player.playerUI.rand8_4.add(boardPlayer4, new Integer(0));
		     player.playerUI.rand8_5.add(boardPlayer5, new Integer(0));
		     player.playerUI.rand8_6.add(boardPlayer6, new Integer(0));
		     player.playerUI.rand8_7.add(boardPlayer7, new Integer(0));
		     player.playerUI.rand8_8.add(boardPlayer8, new Integer(0));
		     player.playerUI.rand8_9.add(boardPlayer9, new Integer(0));
		     player.playerUI.rand8_10.add(boardPlayer10, new Integer(0));
		     player.playerUI.rand8_11.add(boardPlayer11, new Integer(0));
		     player.playerUI.rand8_12.add(boardPlayer12, new Integer(0));
		     player.playerUI.rand8_13.add(boardPlayer13, new Integer(0));
		     player.playerUI.rand8_14.add(boardPlayer14, new Integer(0));
		     player.playerUI.rand8_15.add(boardPlayer15, new Integer(0));
		     player.playerUI.rand8_16.add(boardPlayer16, new Integer(0));
		
		     boardPlayer1.setBounds(0, 0, iconPlayer1.getIconWidth(),
		                                  iconPlayer1.getIconHeight()); 
		     player.playerUI.rand8_1.setPreferredSize(new Dimension(iconPlayer1.getIconWidth(),
		                                        iconPlayer1.getIconHeight()));
		     boardPlayer2.setBounds(0, 0, iconPlayer2.getIconWidth(),
		                                  iconPlayer2.getIconHeight()); 
		     player.playerUI.rand8_2.setPreferredSize(new Dimension(iconPlayer2.getIconWidth(),
		                                        iconPlayer2.getIconHeight()));
		     boardPlayer3.setBounds(0, 0, iconPlayer3.getIconWidth(),
		                                  iconPlayer3.getIconHeight()); 
		     player.playerUI.rand8_3.setPreferredSize(new Dimension(iconPlayer3.getIconWidth(),
		                                        iconPlayer3.getIconHeight()));
		     boardPlayer4.setBounds(0, 0, iconPlayer4.getIconWidth(),
		                                  iconPlayer4.getIconHeight()); 
		     player.playerUI.rand8_4.setPreferredSize(new Dimension(iconPlayer4.getIconWidth(),
		                                        iconPlayer4.getIconHeight()));
		     boardPlayer5.setBounds(0, 0, iconPlayer5.getIconWidth(),
		                                  iconPlayer5.getIconHeight()); 
		     player.playerUI.rand8_5.setPreferredSize(new Dimension(iconPlayer5.getIconWidth(),
		                                        iconPlayer5.getIconHeight()));
		     boardPlayer6.setBounds(0, 0, iconPlayer6.getIconWidth(),
		                                  iconPlayer6.getIconHeight()); 
		     player.playerUI.rand8_6.setPreferredSize(new Dimension(iconPlayer6.getIconWidth(),
		                                        iconPlayer6.getIconHeight()));
		     boardPlayer7.setBounds(0, 0, iconPlayer7.getIconWidth(),
		                                  iconPlayer7.getIconHeight()); 
		     player.playerUI.rand8_7.setPreferredSize(new Dimension(iconPlayer7.getIconWidth(),
		                                        iconPlayer7.getIconHeight()));
		     boardPlayer8.setBounds(0, 0, iconPlayer8.getIconWidth(),
		                                  iconPlayer8.getIconHeight()); 
		     player.playerUI.rand8_8.setPreferredSize(new Dimension(iconPlayer8.getIconWidth(),
		                                        iconPlayer8.getIconHeight()));
		     boardPlayer9.setBounds(0, 0, iconPlayer9.getIconWidth(),
		                                  iconPlayer9.getIconHeight()); 
		     player.playerUI.rand8_9.setPreferredSize(new Dimension(iconPlayer9.getIconWidth(),
		                                        iconPlayer9.getIconHeight()));
		     boardPlayer10.setBounds(0, 0, iconPlayer10.getIconWidth(),
		                                  iconPlayer10.getIconHeight()); 
		     player.playerUI.rand8_10.setPreferredSize(new Dimension(iconPlayer10.getIconWidth(),
		                                        iconPlayer10.getIconHeight()));													 													 
		     boardPlayer11.setBounds(0, 0, iconPlayer11.getIconWidth(),
		                                  iconPlayer11.getIconHeight()); 
		     player.playerUI.rand8_11.setPreferredSize(new Dimension(iconPlayer11.getIconWidth(),
		                                        iconPlayer11.getIconHeight()));
		     boardPlayer12.setBounds(0, 0, iconPlayer12.getIconWidth(),
		                                  iconPlayer12.getIconHeight()); 
		     player.playerUI.rand8_12.setPreferredSize(new Dimension(iconPlayer12.getIconWidth(),
		                                        iconPlayer12.getIconHeight()));
		     boardPlayer13.setBounds(0, 0, iconPlayer13.getIconWidth(),
		                                  iconPlayer13.getIconHeight()); 
		     player.playerUI.rand8_13.setPreferredSize(new Dimension(iconPlayer13.getIconWidth(),
		                                        iconPlayer13.getIconHeight()));
		     boardPlayer14.setBounds(0, 0, iconPlayer14.getIconWidth(),
		                                  iconPlayer14.getIconHeight()); 
		     player.playerUI.rand8_14.setPreferredSize(new Dimension(iconPlayer14.getIconWidth(),
		                                        iconPlayer14.getIconHeight()));
		     boardPlayer15.setBounds(0, 0, iconPlayer15.getIconWidth(),
		                                  iconPlayer15.getIconHeight()); 
		     player.playerUI.rand8_15.setPreferredSize(new Dimension(iconPlayer15.getIconWidth(),
		                                        iconPlayer15.getIconHeight()));	
		     boardPlayer16.setBounds(0, 0, iconPlayer16.getIconWidth(),
		                                  iconPlayer16.getIconHeight()); 
		     player.playerUI.rand8_16.setPreferredSize(new Dimension(iconPlayer16.getIconWidth(),
		                                        iconPlayer16.getIconHeight()));													 												 													 
		
			  player.playerUI.mypanel5.add(player.playerUI.rand8_1);
		     player.playerUI.mypanel5.add(player.playerUI.rand8_2);
		     player.playerUI.mypanel5.add(player.playerUI.rand8_3);
		     player.playerUI.mypanel5.add(player.playerUI.rand8_4);
		     player.playerUI.mypanel5.add(player.playerUI.rand8_5);
		     player.playerUI.mypanel5.add(player.playerUI.rand8_6);
		     player.playerUI.mypanel5.add(player.playerUI.rand8_7);
		     player.playerUI.mypanel5.add(player.playerUI.rand8_8);
		     player.playerUI.mypanel5.add(player.playerUI.rand8_9);
		     player.playerUI.mypanel5.add(player.playerUI.rand8_10);
		     player.playerUI.mypanel5.add(player.playerUI.rand8_11);
		     player.playerUI.mypanel5.add(player.playerUI.rand8_12);
		     player.playerUI.mypanel5.add(player.playerUI.rand8_13);
		     player.playerUI.mypanel5.add(player.playerUI.rand8_14);
		     player.playerUI.mypanel5.add(player.playerUI.rand8_15);
		     player.playerUI.mypanel5.add(player.playerUI.rand8_16);
		  
		  }
		  else if(tile == 6)
		  {
  	        iconPlayer1 =  (bp[4][8].getIcon());
	        iconPlayer2 =  (bp[5][8].getIcon());
 	        iconPlayer3 =  (bp[6][8].getIcon());
	        iconPlayer4 =  (bp[7][8].getIcon());
	        iconPlayer5 =  (bp[4][9].getIcon());
	        iconPlayer6 =  (bp[5][9].getIcon());
	        iconPlayer7 =  (bp[6][9].getIcon());
	        iconPlayer8 =  (bp[7][9].getIcon());
	        iconPlayer9 =  (bp[4][10].getIcon());
	        iconPlayer10 =  (bp[5][10].getIcon());
	        iconPlayer11 =  (bp[6][10].getIcon());
	        iconPlayer12 =  (bp[7][10].getIcon());
	        iconPlayer13 =  (bp[4][11].getIcon());
	        iconPlayer14 =  (bp[5][11].getIcon());
	        iconPlayer15 =  (bp[6][11].getIcon());
	        iconPlayer16 =  (bp[7][11].getIcon());

	
		//add components to board
			  GameJLabel boardPlayer1 = new GameJLabel(iconPlayer1);
			  GameJLabel boardPlayer2 = new GameJLabel(iconPlayer2);
			  GameJLabel boardPlayer3 = new GameJLabel(iconPlayer3);
			  GameJLabel boardPlayer4 = new GameJLabel(iconPlayer4);
			  GameJLabel boardPlayer5 = new GameJLabel(iconPlayer5);
			  GameJLabel boardPlayer6 = new GameJLabel(iconPlayer6);
			  GameJLabel boardPlayer7 = new GameJLabel(iconPlayer7);
			  GameJLabel boardPlayer8 = new GameJLabel(iconPlayer8);
			  GameJLabel boardPlayer9 = new GameJLabel(iconPlayer9);
			  GameJLabel boardPlayer10 = new GameJLabel(iconPlayer10);
			  GameJLabel boardPlayer11 = new GameJLabel(iconPlayer11);
			  GameJLabel boardPlayer12 = new GameJLabel(iconPlayer12);
			  GameJLabel boardPlayer13 = new GameJLabel(iconPlayer13);
			  GameJLabel boardPlayer14 = new GameJLabel(iconPlayer14);
			  GameJLabel boardPlayer15 = new GameJLabel(iconPlayer15);
			  GameJLabel boardPlayer16 = new GameJLabel(iconPlayer16);
		
			  player.playerUI.rand8_1.add(boardPlayer1, new Integer(0));
		     player.playerUI.rand8_2.add(boardPlayer2, new Integer(0));
		     player.playerUI.rand8_3.add(boardPlayer3, new Integer(0));
		     player.playerUI.rand8_4.add(boardPlayer4, new Integer(0));
		     player.playerUI.rand8_5.add(boardPlayer5, new Integer(0));
		     player.playerUI.rand8_6.add(boardPlayer6, new Integer(0));
		     player.playerUI.rand8_7.add(boardPlayer7, new Integer(0));
		     player.playerUI.rand8_8.add(boardPlayer8, new Integer(0));
		     player.playerUI.rand8_9.add(boardPlayer9, new Integer(0));
		     player.playerUI.rand8_10.add(boardPlayer10, new Integer(0));
		     player.playerUI.rand8_11.add(boardPlayer11, new Integer(0));
		     player.playerUI.rand8_12.add(boardPlayer12, new Integer(0));
		     player.playerUI.rand8_13.add(boardPlayer13, new Integer(0));
		     player.playerUI.rand8_14.add(boardPlayer14, new Integer(0));
		     player.playerUI.rand8_15.add(boardPlayer15, new Integer(0));
		     player.playerUI.rand8_16.add(boardPlayer16, new Integer(0));
		
		     boardPlayer1.setBounds(0, 0, iconPlayer1.getIconWidth(),
		                                  iconPlayer1.getIconHeight()); 
		     player.playerUI.rand8_1.setPreferredSize(new Dimension(iconPlayer1.getIconWidth(),
		                                        iconPlayer1.getIconHeight()));
		     boardPlayer2.setBounds(0, 0, iconPlayer2.getIconWidth(),
		                                  iconPlayer2.getIconHeight()); 
		     player.playerUI.rand8_2.setPreferredSize(new Dimension(iconPlayer2.getIconWidth(),
		                                        iconPlayer2.getIconHeight()));
		     boardPlayer3.setBounds(0, 0, iconPlayer3.getIconWidth(),
		                                  iconPlayer3.getIconHeight()); 
		     player.playerUI.rand8_3.setPreferredSize(new Dimension(iconPlayer3.getIconWidth(),
		                                        iconPlayer3.getIconHeight()));
		     boardPlayer4.setBounds(0, 0, iconPlayer4.getIconWidth(),
		                                  iconPlayer4.getIconHeight()); 
		     player.playerUI.rand8_4.setPreferredSize(new Dimension(iconPlayer4.getIconWidth(),
		                                        iconPlayer4.getIconHeight()));
		     boardPlayer5.setBounds(0, 0, iconPlayer5.getIconWidth(),
		                                  iconPlayer5.getIconHeight()); 
		     player.playerUI.rand8_5.setPreferredSize(new Dimension(iconPlayer5.getIconWidth(),
		                                        iconPlayer5.getIconHeight()));
		     boardPlayer6.setBounds(0, 0, iconPlayer6.getIconWidth(),
		                                  iconPlayer6.getIconHeight()); 
		     player.playerUI.rand8_6.setPreferredSize(new Dimension(iconPlayer6.getIconWidth(),
		                                        iconPlayer6.getIconHeight()));
		     boardPlayer7.setBounds(0, 0, iconPlayer7.getIconWidth(),
		                                  iconPlayer7.getIconHeight()); 
		     player.playerUI.rand8_7.setPreferredSize(new Dimension(iconPlayer7.getIconWidth(),
		                                        iconPlayer7.getIconHeight()));
		     boardPlayer8.setBounds(0, 0, iconPlayer8.getIconWidth(),
		                                  iconPlayer8.getIconHeight()); 
		     player.playerUI.rand8_8.setPreferredSize(new Dimension(iconPlayer8.getIconWidth(),
		                                        iconPlayer8.getIconHeight()));
		     boardPlayer9.setBounds(0, 0, iconPlayer9.getIconWidth(),
		                                  iconPlayer9.getIconHeight()); 
		     player.playerUI.rand8_9.setPreferredSize(new Dimension(iconPlayer9.getIconWidth(),
		                                        iconPlayer9.getIconHeight()));
		     boardPlayer10.setBounds(0, 0, iconPlayer10.getIconWidth(),
		                                  iconPlayer10.getIconHeight()); 
		     player.playerUI.rand8_10.setPreferredSize(new Dimension(iconPlayer10.getIconWidth(),
		                                        iconPlayer10.getIconHeight()));													 													 
		     boardPlayer11.setBounds(0, 0, iconPlayer11.getIconWidth(),
		                                  iconPlayer11.getIconHeight()); 
		     player.playerUI.rand8_11.setPreferredSize(new Dimension(iconPlayer11.getIconWidth(),
		                                        iconPlayer11.getIconHeight()));
		     boardPlayer12.setBounds(0, 0, iconPlayer12.getIconWidth(),
		                                  iconPlayer12.getIconHeight()); 
		     player.playerUI.rand8_12.setPreferredSize(new Dimension(iconPlayer12.getIconWidth(),
		                                        iconPlayer12.getIconHeight()));
		     boardPlayer13.setBounds(0, 0, iconPlayer13.getIconWidth(),
		                                  iconPlayer13.getIconHeight()); 
		     player.playerUI.rand8_13.setPreferredSize(new Dimension(iconPlayer13.getIconWidth(),
		                                        iconPlayer13.getIconHeight()));
		     boardPlayer14.setBounds(0, 0, iconPlayer14.getIconWidth(),
		                                  iconPlayer14.getIconHeight()); 
		     player.playerUI.rand8_14.setPreferredSize(new Dimension(iconPlayer14.getIconWidth(),
		                                        iconPlayer14.getIconHeight()));
		     boardPlayer15.setBounds(0, 0, iconPlayer15.getIconWidth(),
		                                  iconPlayer15.getIconHeight()); 
		     player.playerUI.rand8_15.setPreferredSize(new Dimension(iconPlayer15.getIconWidth(),
		                                        iconPlayer15.getIconHeight()));	
		     boardPlayer16.setBounds(0, 0, iconPlayer16.getIconWidth(),
		                                  iconPlayer16.getIconHeight()); 
		     player.playerUI.rand8_16.setPreferredSize(new Dimension(iconPlayer16.getIconWidth(),
		                                        iconPlayer16.getIconHeight()));													 												 													 
		
			  player.playerUI.mypanel6.add(player.playerUI.rand8_1);
		     player.playerUI.mypanel6.add(player.playerUI.rand8_2);
		     player.playerUI.mypanel6.add(player.playerUI.rand8_3);
		     player.playerUI.mypanel6.add(player.playerUI.rand8_4);
		     player.playerUI.mypanel6.add(player.playerUI.rand8_5);
		     player.playerUI.mypanel6.add(player.playerUI.rand8_6);
		     player.playerUI.mypanel6.add(player.playerUI.rand8_7);
		     player.playerUI.mypanel6.add(player.playerUI.rand8_8);
		     player.playerUI.mypanel6.add(player.playerUI.rand8_9);
		     player.playerUI.mypanel6.add(player.playerUI.rand8_10);
		     player.playerUI.mypanel6.add(player.playerUI.rand8_11);
		     player.playerUI.mypanel6.add(player.playerUI.rand8_12);
		     player.playerUI.mypanel6.add(player.playerUI.rand8_13);
		     player.playerUI.mypanel6.add(player.playerUI.rand8_14);
		     player.playerUI.mypanel6.add(player.playerUI.rand8_15);
		     player.playerUI.mypanel6.add(player.playerUI.rand8_16);
		  
		  }
		  else if(tile == 7)
		  {
	       iconPlayer1 =  bp[4][12].getIcon();
	       iconPlayer2 =  bp[5][12].getIcon();
	       iconPlayer3 =  bp[6][12].getIcon();
	       iconPlayer4 =  bp[7][12].getIcon();
	       iconPlayer5 =  bp[4][13].getIcon();
	       iconPlayer6 =  bp[5][13].getIcon();
	       iconPlayer7 =  bp[6][13].getIcon();
	       iconPlayer8 =  bp[7][13].getIcon();
	       iconPlayer9 =  bp[4][14].getIcon();
	       iconPlayer10 =  bp[5][14].getIcon();
	       iconPlayer11 =  bp[6][14].getIcon();
	       iconPlayer12 =  bp[7][14].getIcon();
	       iconPlayer13 =  bp[4][15].getIcon();
	       iconPlayer14 =  bp[5][15].getIcon();
	       iconPlayer15 =  bp[6][15].getIcon();
	       iconPlayer16 =  bp[7][15].getIcon();


		//add components to board
			  GameJLabel boardPlayer1 = new GameJLabel(iconPlayer1);
			  GameJLabel boardPlayer2 = new GameJLabel(iconPlayer2);
			  GameJLabel boardPlayer3 = new GameJLabel(iconPlayer3);
			  GameJLabel boardPlayer4 = new GameJLabel(iconPlayer4);
			  GameJLabel boardPlayer5 = new GameJLabel(iconPlayer5);
			  GameJLabel boardPlayer6 = new GameJLabel(iconPlayer6);
			  GameJLabel boardPlayer7 = new GameJLabel(iconPlayer7);
			  GameJLabel boardPlayer8 = new GameJLabel(iconPlayer8);
			  GameJLabel boardPlayer9 = new GameJLabel(iconPlayer9);
			  GameJLabel boardPlayer10 = new GameJLabel(iconPlayer10);
			  GameJLabel boardPlayer11 = new GameJLabel(iconPlayer11);
			  GameJLabel boardPlayer12 = new GameJLabel(iconPlayer12);
			  GameJLabel boardPlayer13 = new GameJLabel(iconPlayer13);
			  GameJLabel boardPlayer14 = new GameJLabel(iconPlayer14);
			  GameJLabel boardPlayer15 = new GameJLabel(iconPlayer15);
			  GameJLabel boardPlayer16 = new GameJLabel(iconPlayer16);
		
			  player.playerUI.rand8_1.add(boardPlayer1, new Integer(0));
		     player.playerUI.rand8_2.add(boardPlayer2, new Integer(0));
		     player.playerUI.rand8_3.add(boardPlayer3, new Integer(0));
		     player.playerUI.rand8_4.add(boardPlayer4, new Integer(0));
		     player.playerUI.rand8_5.add(boardPlayer5, new Integer(0));
		     player.playerUI.rand8_6.add(boardPlayer6, new Integer(0));
		     player.playerUI.rand8_7.add(boardPlayer7, new Integer(0));
		     player.playerUI.rand8_8.add(boardPlayer8, new Integer(0));
		     player.playerUI.rand8_9.add(boardPlayer9, new Integer(0));
		     player.playerUI.rand8_10.add(boardPlayer10, new Integer(0));
		     player.playerUI.rand8_11.add(boardPlayer11, new Integer(0));
		     player.playerUI.rand8_12.add(boardPlayer12, new Integer(0));
		     player.playerUI.rand8_13.add(boardPlayer13, new Integer(0));
		     player.playerUI.rand8_14.add(boardPlayer14, new Integer(0));
		     player.playerUI.rand8_15.add(boardPlayer15, new Integer(0));
		     player.playerUI.rand8_16.add(boardPlayer16, new Integer(0));
		
		     boardPlayer1.setBounds(0, 0, iconPlayer1.getIconWidth(),
		                                  iconPlayer1.getIconHeight()); 
		     player.playerUI.rand8_1.setPreferredSize(new Dimension(iconPlayer1.getIconWidth(),
		                                        iconPlayer1.getIconHeight()));
		     boardPlayer2.setBounds(0, 0, iconPlayer2.getIconWidth(),
		                                  iconPlayer2.getIconHeight()); 
		     player.playerUI.rand8_2.setPreferredSize(new Dimension(iconPlayer2.getIconWidth(),
		                                        iconPlayer2.getIconHeight()));
		     boardPlayer3.setBounds(0, 0, iconPlayer3.getIconWidth(),
		                                  iconPlayer3.getIconHeight()); 
		     player.playerUI.rand8_3.setPreferredSize(new Dimension(iconPlayer3.getIconWidth(),
		                                        iconPlayer3.getIconHeight()));
		     boardPlayer4.setBounds(0, 0, iconPlayer4.getIconWidth(),
		                                  iconPlayer4.getIconHeight()); 
		     player.playerUI.rand8_4.setPreferredSize(new Dimension(iconPlayer4.getIconWidth(),
		                                        iconPlayer4.getIconHeight()));
		     boardPlayer5.setBounds(0, 0, iconPlayer5.getIconWidth(),
		                                  iconPlayer5.getIconHeight()); 
		     player.playerUI.rand8_5.setPreferredSize(new Dimension(iconPlayer5.getIconWidth(),
		                                        iconPlayer5.getIconHeight()));
		     boardPlayer6.setBounds(0, 0, iconPlayer6.getIconWidth(),
		                                  iconPlayer6.getIconHeight()); 
		     player.playerUI.rand8_6.setPreferredSize(new Dimension(iconPlayer6.getIconWidth(),
		                                        iconPlayer6.getIconHeight()));
		     boardPlayer7.setBounds(0, 0, iconPlayer7.getIconWidth(),
		                                  iconPlayer7.getIconHeight()); 
		     player.playerUI.rand8_7.setPreferredSize(new Dimension(iconPlayer7.getIconWidth(),
		                                        iconPlayer7.getIconHeight()));
		     boardPlayer8.setBounds(0, 0, iconPlayer8.getIconWidth(),
		                                  iconPlayer8.getIconHeight()); 
		     player.playerUI.rand8_8.setPreferredSize(new Dimension(iconPlayer8.getIconWidth(),
		                                        iconPlayer8.getIconHeight()));
		     boardPlayer9.setBounds(0, 0, iconPlayer9.getIconWidth(),
		                                  iconPlayer9.getIconHeight()); 
		     player.playerUI.rand8_9.setPreferredSize(new Dimension(iconPlayer9.getIconWidth(),
		                                        iconPlayer9.getIconHeight()));
		     boardPlayer10.setBounds(0, 0, iconPlayer10.getIconWidth(),
		                                  iconPlayer10.getIconHeight()); 
		     player.playerUI.rand8_10.setPreferredSize(new Dimension(iconPlayer10.getIconWidth(),
		                                        iconPlayer10.getIconHeight()));													 													 
		     boardPlayer11.setBounds(0, 0, iconPlayer11.getIconWidth(),
		                                  iconPlayer11.getIconHeight()); 
		     player.playerUI.rand8_11.setPreferredSize(new Dimension(iconPlayer11.getIconWidth(),
		                                        iconPlayer11.getIconHeight()));
		     boardPlayer12.setBounds(0, 0, iconPlayer12.getIconWidth(),
		                                  iconPlayer12.getIconHeight()); 
		     player.playerUI.rand8_12.setPreferredSize(new Dimension(iconPlayer12.getIconWidth(),
		                                        iconPlayer12.getIconHeight()));
		     boardPlayer13.setBounds(0, 0, iconPlayer13.getIconWidth(),
		                                  iconPlayer13.getIconHeight()); 
		     player.playerUI.rand8_13.setPreferredSize(new Dimension(iconPlayer13.getIconWidth(),
		                                        iconPlayer13.getIconHeight()));
		     boardPlayer14.setBounds(0, 0, iconPlayer14.getIconWidth(),
		                                  iconPlayer14.getIconHeight()); 
		     player.playerUI.rand8_14.setPreferredSize(new Dimension(iconPlayer14.getIconWidth(),
		                                        iconPlayer14.getIconHeight()));
		     boardPlayer15.setBounds(0, 0, iconPlayer15.getIconWidth(),
		                                  iconPlayer15.getIconHeight()); 
		     player.playerUI.rand8_15.setPreferredSize(new Dimension(iconPlayer15.getIconWidth(),
		                                        iconPlayer15.getIconHeight()));	
		     boardPlayer16.setBounds(0, 0, iconPlayer16.getIconWidth(),
		                                  iconPlayer16.getIconHeight()); 
		     player.playerUI.rand8_16.setPreferredSize(new Dimension(iconPlayer16.getIconWidth(),
		                                        iconPlayer16.getIconHeight()));													 												 													 
		
			  player.playerUI.mypanel7.add(player.playerUI.rand8_1);
		     player.playerUI.mypanel7.add(player.playerUI.rand8_2);
		     player.playerUI.mypanel7.add(player.playerUI.rand8_3);
		     player.playerUI.mypanel7.add(player.playerUI.rand8_4);
		     player.playerUI.mypanel7.add(player.playerUI.rand8_5);
		     player.playerUI.mypanel7.add(player.playerUI.rand8_6);
		     player.playerUI.mypanel7.add(player.playerUI.rand8_7);
		     player.playerUI.mypanel7.add(player.playerUI.rand8_8);
		     player.playerUI.mypanel7.add(player.playerUI.rand8_9);
		     player.playerUI.mypanel7.add(player.playerUI.rand8_10);
		     player.playerUI.mypanel7.add(player.playerUI.rand8_11);
		     player.playerUI.mypanel7.add(player.playerUI.rand8_12);
		     player.playerUI.mypanel7.add(player.playerUI.rand8_13);
		     player.playerUI.mypanel7.add(player.playerUI.rand8_14);
		     player.playerUI.mypanel7.add(player.playerUI.rand8_15);
		     player.playerUI.mypanel7.add(player.playerUI.rand8_16);
		  
		  }
		}

      private void populateRand9(int tile, Player player)
		{
		  if(tile == 0)
		  {
			  iconPlayer1 =  (bp[0][0].getIcon());
	        iconPlayer2 =  (bp[1][0].getIcon());
 	        iconPlayer3 =  (bp[2][0].getIcon());
	        iconPlayer4 =  (bp[3][0].getIcon());
	        iconPlayer5 =  (bp[0][1].getIcon());
	        iconPlayer6 =  (bp[1][1].getIcon());
	        iconPlayer7 =  (bp[2][1].getIcon());
	        iconPlayer8 =  (bp[3][1].getIcon());
	        iconPlayer9 =  (bp[0][2].getIcon());
	        iconPlayer10 =  (bp[1][2].getIcon());
	        iconPlayer11 =  (bp[2][2].getIcon());
	        iconPlayer12 =  (bp[3][2].getIcon());
	        iconPlayer13 =  (bp[0][3].getIcon());
	        iconPlayer14 =  (bp[1][3].getIcon());
	        iconPlayer15 =  (bp[2][3].getIcon());
	        iconPlayer16 =  (bp[3][3].getIcon());

	
		//add components to board
			  GameJLabel boardPlayer1 = new GameJLabel(iconPlayer1);
			  GameJLabel boardPlayer2 = new GameJLabel(iconPlayer2);
			  GameJLabel boardPlayer3 = new GameJLabel(iconPlayer3);
			  GameJLabel boardPlayer4 = new GameJLabel(iconPlayer4);
			  GameJLabel boardPlayer5 = new GameJLabel(iconPlayer5);
			  GameJLabel boardPlayer6 = new GameJLabel(iconPlayer6);
			  GameJLabel boardPlayer7 = new GameJLabel(iconPlayer7);
			  GameJLabel boardPlayer8 = new GameJLabel(iconPlayer8);
			  GameJLabel boardPlayer9 = new GameJLabel(iconPlayer9);
			  GameJLabel boardPlayer10 = new GameJLabel(iconPlayer10);
			  GameJLabel boardPlayer11 = new GameJLabel(iconPlayer11);
			  GameJLabel boardPlayer12 = new GameJLabel(iconPlayer12);
			  GameJLabel boardPlayer13 = new GameJLabel(iconPlayer13);
			  GameJLabel boardPlayer14 = new GameJLabel(iconPlayer14);
			  GameJLabel boardPlayer15 = new GameJLabel(iconPlayer15);
			  GameJLabel boardPlayer16 = new GameJLabel(iconPlayer16);
		
			  player.playerUI.rand9_1.add(boardPlayer1, new Integer(0));
		     player.playerUI.rand9_2.add(boardPlayer2, new Integer(0));
		     player.playerUI.rand9_3.add(boardPlayer3, new Integer(0));
		     player.playerUI.rand9_4.add(boardPlayer4, new Integer(0));
		     player.playerUI.rand9_5.add(boardPlayer5, new Integer(0));
		     player.playerUI.rand9_6.add(boardPlayer6, new Integer(0));
		     player.playerUI.rand9_7.add(boardPlayer7, new Integer(0));
		     player.playerUI.rand9_8.add(boardPlayer8, new Integer(0));
		     player.playerUI.rand9_9.add(boardPlayer9, new Integer(0));
		     player.playerUI.rand9_10.add(boardPlayer10, new Integer(0));
		     player.playerUI.rand9_11.add(boardPlayer11, new Integer(0));
		     player.playerUI.rand9_12.add(boardPlayer12, new Integer(0));
		     player.playerUI.rand9_13.add(boardPlayer13, new Integer(0));
		     player.playerUI.rand9_14.add(boardPlayer14, new Integer(0));
		     player.playerUI.rand9_15.add(boardPlayer15, new Integer(0));
		     player.playerUI.rand9_16.add(boardPlayer16, new Integer(0));
		
		     boardPlayer1.setBounds(0, 0, iconPlayer1.getIconWidth(),
		                                  iconPlayer1.getIconHeight()); 
		     player.playerUI.rand9_1.setPreferredSize(new Dimension(iconPlayer1.getIconWidth(),
		                                        iconPlayer1.getIconHeight()));
		     boardPlayer2.setBounds(0, 0, iconPlayer2.getIconWidth(),
		                                  iconPlayer2.getIconHeight()); 
		     player.playerUI.rand9_2.setPreferredSize(new Dimension(iconPlayer2.getIconWidth(),
		                                        iconPlayer2.getIconHeight()));
		     boardPlayer3.setBounds(0, 0, iconPlayer3.getIconWidth(),
		                                  iconPlayer3.getIconHeight()); 
		     player.playerUI.rand9_3.setPreferredSize(new Dimension(iconPlayer3.getIconWidth(),
		                                        iconPlayer3.getIconHeight()));
		     boardPlayer4.setBounds(0, 0, iconPlayer4.getIconWidth(),
		                                  iconPlayer4.getIconHeight()); 
		     player.playerUI.rand9_4.setPreferredSize(new Dimension(iconPlayer4.getIconWidth(),
		                                        iconPlayer4.getIconHeight()));
		     boardPlayer5.setBounds(0, 0, iconPlayer5.getIconWidth(),
		                                  iconPlayer5.getIconHeight()); 
		     player.playerUI.rand9_5.setPreferredSize(new Dimension(iconPlayer5.getIconWidth(),
		                                        iconPlayer5.getIconHeight()));
		     boardPlayer6.setBounds(0, 0, iconPlayer6.getIconWidth(),
		                                  iconPlayer6.getIconHeight()); 
		     player.playerUI.rand9_6.setPreferredSize(new Dimension(iconPlayer6.getIconWidth(),
		                                        iconPlayer6.getIconHeight()));
		     boardPlayer7.setBounds(0, 0, iconPlayer7.getIconWidth(),
		                                  iconPlayer7.getIconHeight()); 
		     player.playerUI.rand9_7.setPreferredSize(new Dimension(iconPlayer7.getIconWidth(),
		                                        iconPlayer7.getIconHeight()));
		     boardPlayer8.setBounds(0, 0, iconPlayer8.getIconWidth(),
		                                  iconPlayer8.getIconHeight()); 
		     player.playerUI.rand9_8.setPreferredSize(new Dimension(iconPlayer8.getIconWidth(),
		                                        iconPlayer8.getIconHeight()));
		     boardPlayer9.setBounds(0, 0, iconPlayer9.getIconWidth(),
		                                  iconPlayer9.getIconHeight()); 
		     player.playerUI.rand9_9.setPreferredSize(new Dimension(iconPlayer9.getIconWidth(),
		                                        iconPlayer9.getIconHeight()));
		     boardPlayer10.setBounds(0, 0, iconPlayer10.getIconWidth(),
		                                  iconPlayer10.getIconHeight()); 
		     player.playerUI.rand9_10.setPreferredSize(new Dimension(iconPlayer10.getIconWidth(),
		                                        iconPlayer10.getIconHeight()));													 													 
		     boardPlayer11.setBounds(0, 0, iconPlayer11.getIconWidth(),
		                                  iconPlayer11.getIconHeight()); 
		     player.playerUI.rand9_11.setPreferredSize(new Dimension(iconPlayer11.getIconWidth(),
		                                        iconPlayer11.getIconHeight()));
		     boardPlayer12.setBounds(0, 0, iconPlayer12.getIconWidth(),
		                                  iconPlayer12.getIconHeight()); 
		     player.playerUI.rand9_12.setPreferredSize(new Dimension(iconPlayer12.getIconWidth(),
		                                        iconPlayer12.getIconHeight()));
		     boardPlayer13.setBounds(0, 0, iconPlayer13.getIconWidth(),
		                                  iconPlayer13.getIconHeight()); 
		     player.playerUI.rand9_13.setPreferredSize(new Dimension(iconPlayer13.getIconWidth(),
		                                        iconPlayer13.getIconHeight()));
		     boardPlayer14.setBounds(0, 0, iconPlayer14.getIconWidth(),
		                                  iconPlayer14.getIconHeight()); 
		     player.playerUI.rand9_14.setPreferredSize(new Dimension(iconPlayer14.getIconWidth(),
		                                        iconPlayer14.getIconHeight()));
		     boardPlayer15.setBounds(0, 0, iconPlayer15.getIconWidth(),
		                                  iconPlayer15.getIconHeight()); 
		     player.playerUI.rand9_15.setPreferredSize(new Dimension(iconPlayer15.getIconWidth(),
		                                        iconPlayer15.getIconHeight()));	
		     boardPlayer16.setBounds(0, 0, iconPlayer16.getIconWidth(),
		                                  iconPlayer16.getIconHeight()); 
		     player.playerUI.rand9_16.setPreferredSize(new Dimension(iconPlayer16.getIconWidth(),
		                                        iconPlayer16.getIconHeight()));													 												 													 
		
			  player.playerUI.mypanel0.add(player.playerUI.rand9_1);
		     player.playerUI.mypanel0.add(player.playerUI.rand9_2);
		     player.playerUI.mypanel0.add(player.playerUI.rand9_3);
		     player.playerUI.mypanel0.add(player.playerUI.rand9_4);
		     player.playerUI.mypanel0.add(player.playerUI.rand9_5);
		     player.playerUI.mypanel0.add(player.playerUI.rand9_6);
		     player.playerUI.mypanel0.add(player.playerUI.rand9_7);
		     player.playerUI.mypanel0.add(player.playerUI.rand9_8);
		     player.playerUI.mypanel0.add(player.playerUI.rand9_9);
		     player.playerUI.mypanel0.add(player.playerUI.rand9_10);
		     player.playerUI.mypanel0.add(player.playerUI.rand9_11);
		     player.playerUI.mypanel0.add(player.playerUI.rand9_12);
		     player.playerUI.mypanel0.add(player.playerUI.rand9_13);
		     player.playerUI.mypanel0.add(player.playerUI.rand9_14);
		     player.playerUI.mypanel0.add(player.playerUI.rand9_15);
		     player.playerUI.mypanel0.add(player.playerUI.rand9_16);
		  }
		  else if(tile == 1)
		  {
  	        iconPlayer1 =  (bp[0][4].getIcon());
	        iconPlayer2 =  (bp[1][4].getIcon());
 	        iconPlayer3 =  (bp[2][4].getIcon());
	        iconPlayer4 =  (bp[3][4].getIcon());
	        iconPlayer5 =  (bp[0][5].getIcon());
	        iconPlayer6 =  (bp[1][5].getIcon());
	        iconPlayer7 =  (bp[2][5].getIcon());
	        iconPlayer8 =  (bp[3][5].getIcon());
	        iconPlayer9 =  (bp[0][6].getIcon());
	        iconPlayer10 =  (bp[1][6].getIcon());
	        iconPlayer11 =  (bp[2][6].getIcon());
	        iconPlayer12 =  (bp[3][6].getIcon());
	        iconPlayer13 =  (bp[0][7].getIcon());
	        iconPlayer14 =  (bp[1][7].getIcon());
	        iconPlayer15 =  (bp[2][7].getIcon());
	        iconPlayer16 =  (bp[3][7].getIcon());

	
		//add components to board
			  GameJLabel boardPlayer1 = new GameJLabel(iconPlayer1);
			  GameJLabel boardPlayer2 = new GameJLabel(iconPlayer2);
			  GameJLabel boardPlayer3 = new GameJLabel(iconPlayer3);
			  GameJLabel boardPlayer4 = new GameJLabel(iconPlayer4);
			  GameJLabel boardPlayer5 = new GameJLabel(iconPlayer5);
			  GameJLabel boardPlayer6 = new GameJLabel(iconPlayer6);
			  GameJLabel boardPlayer7 = new GameJLabel(iconPlayer7);
			  GameJLabel boardPlayer8 = new GameJLabel(iconPlayer8);
			  GameJLabel boardPlayer9 = new GameJLabel(iconPlayer9);
			  GameJLabel boardPlayer10 = new GameJLabel(iconPlayer10);
			  GameJLabel boardPlayer11 = new GameJLabel(iconPlayer11);
			  GameJLabel boardPlayer12 = new GameJLabel(iconPlayer12);
			  GameJLabel boardPlayer13 = new GameJLabel(iconPlayer13);
			  GameJLabel boardPlayer14 = new GameJLabel(iconPlayer14);
			  GameJLabel boardPlayer15 = new GameJLabel(iconPlayer15);
			  GameJLabel boardPlayer16 = new GameJLabel(iconPlayer16);
		
			  player.playerUI.rand9_1.add(boardPlayer1, new Integer(0));
		     player.playerUI.rand9_2.add(boardPlayer2, new Integer(0));
		     player.playerUI.rand9_3.add(boardPlayer3, new Integer(0));
		     player.playerUI.rand9_4.add(boardPlayer4, new Integer(0));
		     player.playerUI.rand9_5.add(boardPlayer5, new Integer(0));
		     player.playerUI.rand9_6.add(boardPlayer6, new Integer(0));
		     player.playerUI.rand9_7.add(boardPlayer7, new Integer(0));
		     player.playerUI.rand9_8.add(boardPlayer8, new Integer(0));
		     player.playerUI.rand9_9.add(boardPlayer9, new Integer(0));
		     player.playerUI.rand9_10.add(boardPlayer10, new Integer(0));
		     player.playerUI.rand9_11.add(boardPlayer11, new Integer(0));
		     player.playerUI.rand9_12.add(boardPlayer12, new Integer(0));
		     player.playerUI.rand9_13.add(boardPlayer13, new Integer(0));
		     player.playerUI.rand9_14.add(boardPlayer14, new Integer(0));
		     player.playerUI.rand9_15.add(boardPlayer15, new Integer(0));
		     player.playerUI.rand9_16.add(boardPlayer16, new Integer(0));
		
		     boardPlayer1.setBounds(0, 0, iconPlayer1.getIconWidth(),
		                                  iconPlayer1.getIconHeight()); 
		     player.playerUI.rand9_1.setPreferredSize(new Dimension(iconPlayer1.getIconWidth(),
		                                        iconPlayer1.getIconHeight()));
		     boardPlayer2.setBounds(0, 0, iconPlayer2.getIconWidth(),
		                                  iconPlayer2.getIconHeight()); 
		     player.playerUI.rand9_2.setPreferredSize(new Dimension(iconPlayer2.getIconWidth(),
		                                        iconPlayer2.getIconHeight()));
		     boardPlayer3.setBounds(0, 0, iconPlayer3.getIconWidth(),
		                                  iconPlayer3.getIconHeight()); 
		     player.playerUI.rand9_3.setPreferredSize(new Dimension(iconPlayer3.getIconWidth(),
		                                        iconPlayer3.getIconHeight()));
		     boardPlayer4.setBounds(0, 0, iconPlayer4.getIconWidth(),
		                                  iconPlayer4.getIconHeight()); 
		     player.playerUI.rand9_4.setPreferredSize(new Dimension(iconPlayer4.getIconWidth(),
		                                        iconPlayer4.getIconHeight()));
		     boardPlayer5.setBounds(0, 0, iconPlayer5.getIconWidth(),
		                                  iconPlayer5.getIconHeight()); 
		     player.playerUI.rand9_5.setPreferredSize(new Dimension(iconPlayer5.getIconWidth(),
		                                        iconPlayer5.getIconHeight()));
		     boardPlayer6.setBounds(0, 0, iconPlayer6.getIconWidth(),
		                                  iconPlayer6.getIconHeight()); 
		     player.playerUI.rand9_6.setPreferredSize(new Dimension(iconPlayer6.getIconWidth(),
		                                        iconPlayer6.getIconHeight()));
		     boardPlayer7.setBounds(0, 0, iconPlayer7.getIconWidth(),
		                                  iconPlayer7.getIconHeight()); 
		     player.playerUI.rand9_7.setPreferredSize(new Dimension(iconPlayer7.getIconWidth(),
		                                        iconPlayer7.getIconHeight()));
		     boardPlayer8.setBounds(0, 0, iconPlayer8.getIconWidth(),
		                                  iconPlayer8.getIconHeight()); 
		     player.playerUI.rand9_8.setPreferredSize(new Dimension(iconPlayer8.getIconWidth(),
		                                        iconPlayer8.getIconHeight()));
		     boardPlayer9.setBounds(0, 0, iconPlayer9.getIconWidth(),
		                                  iconPlayer9.getIconHeight()); 
		     player.playerUI.rand9_9.setPreferredSize(new Dimension(iconPlayer9.getIconWidth(),
		                                        iconPlayer9.getIconHeight()));
		     boardPlayer10.setBounds(0, 0, iconPlayer10.getIconWidth(),
		                                  iconPlayer10.getIconHeight()); 
		     player.playerUI.rand9_10.setPreferredSize(new Dimension(iconPlayer10.getIconWidth(),
		                                        iconPlayer10.getIconHeight()));													 													 
		     boardPlayer11.setBounds(0, 0, iconPlayer11.getIconWidth(),
		                                  iconPlayer11.getIconHeight()); 
		     player.playerUI.rand9_11.setPreferredSize(new Dimension(iconPlayer11.getIconWidth(),
		                                        iconPlayer11.getIconHeight()));
		     boardPlayer12.setBounds(0, 0, iconPlayer12.getIconWidth(),
		                                  iconPlayer12.getIconHeight()); 
		     player.playerUI.rand9_12.setPreferredSize(new Dimension(iconPlayer12.getIconWidth(),
		                                        iconPlayer12.getIconHeight()));
		     boardPlayer13.setBounds(0, 0, iconPlayer13.getIconWidth(),
		                                  iconPlayer13.getIconHeight()); 
		     player.playerUI.rand9_13.setPreferredSize(new Dimension(iconPlayer13.getIconWidth(),
		                                        iconPlayer13.getIconHeight()));
		     boardPlayer14.setBounds(0, 0, iconPlayer14.getIconWidth(),
		                                  iconPlayer14.getIconHeight()); 
		     player.playerUI.rand9_14.setPreferredSize(new Dimension(iconPlayer14.getIconWidth(),
		                                        iconPlayer14.getIconHeight()));
		     boardPlayer15.setBounds(0, 0, iconPlayer15.getIconWidth(),
		                                  iconPlayer15.getIconHeight()); 
		     player.playerUI.rand9_15.setPreferredSize(new Dimension(iconPlayer15.getIconWidth(),
		                                        iconPlayer15.getIconHeight()));	
		     boardPlayer16.setBounds(0, 0, iconPlayer16.getIconWidth(),
		                                  iconPlayer16.getIconHeight()); 
		     player.playerUI.rand9_16.setPreferredSize(new Dimension(iconPlayer16.getIconWidth(),
		                                        iconPlayer16.getIconHeight()));													 												 													 
		
			  player.playerUI.mypanel1.add(player.playerUI.rand9_1);
		     player.playerUI.mypanel1.add(player.playerUI.rand9_2);
		     player.playerUI.mypanel1.add(player.playerUI.rand9_3);
		     player.playerUI.mypanel1.add(player.playerUI.rand9_4);
		     player.playerUI.mypanel1.add(player.playerUI.rand9_5);
		     player.playerUI.mypanel1.add(player.playerUI.rand9_6);
		     player.playerUI.mypanel1.add(player.playerUI.rand9_7);
		     player.playerUI.mypanel1.add(player.playerUI.rand9_8);
		     player.playerUI.mypanel1.add(player.playerUI.rand9_9);
		     player.playerUI.mypanel1.add(player.playerUI.rand9_10);
		     player.playerUI.mypanel1.add(player.playerUI.rand9_11);
		     player.playerUI.mypanel1.add(player.playerUI.rand9_12);
		     player.playerUI.mypanel1.add(player.playerUI.rand9_13);
		     player.playerUI.mypanel1.add(player.playerUI.rand9_14);
		     player.playerUI.mypanel1.add(player.playerUI.rand9_15);
		     player.playerUI.mypanel1.add(player.playerUI.rand9_16);
		  
		  }
		  else if(tile == 2)
		  {
  	        iconPlayer1 =  (bp[0][8].getIcon());
	        iconPlayer2 =  (bp[1][8].getIcon());
 	        iconPlayer3 =  (bp[2][8].getIcon());
	        iconPlayer4 =  (bp[3][8].getIcon());
	        iconPlayer5 =  (bp[0][9].getIcon());
	        iconPlayer6 =  (bp[1][9].getIcon());
	        iconPlayer7 =  (bp[2][9].getIcon());
	        iconPlayer8 =  (bp[3][9].getIcon());
	        iconPlayer9 =  (bp[0][10].getIcon());
	        iconPlayer10 =  (bp[1][10].getIcon());
	        iconPlayer11 =  (bp[2][10].getIcon());
	        iconPlayer12 =  (bp[3][10].getIcon());
	        iconPlayer13 =  (bp[0][11].getIcon());
	        iconPlayer14 =  (bp[1][11].getIcon());
	        iconPlayer15 =  (bp[2][11].getIcon());
	        iconPlayer16 =  (bp[3][11].getIcon());

	
		//add components to board
			  GameJLabel boardPlayer1 = new GameJLabel(iconPlayer1);
			  GameJLabel boardPlayer2 = new GameJLabel(iconPlayer2);
			  GameJLabel boardPlayer3 = new GameJLabel(iconPlayer3);
			  GameJLabel boardPlayer4 = new GameJLabel(iconPlayer4);
			  GameJLabel boardPlayer5 = new GameJLabel(iconPlayer5);
			  GameJLabel boardPlayer6 = new GameJLabel(iconPlayer6);
			  GameJLabel boardPlayer7 = new GameJLabel(iconPlayer7);
			  GameJLabel boardPlayer8 = new GameJLabel(iconPlayer8);
			  GameJLabel boardPlayer9 = new GameJLabel(iconPlayer9);
			  GameJLabel boardPlayer10 = new GameJLabel(iconPlayer10);
			  GameJLabel boardPlayer11 = new GameJLabel(iconPlayer11);
			  GameJLabel boardPlayer12 = new GameJLabel(iconPlayer12);
			  GameJLabel boardPlayer13 = new GameJLabel(iconPlayer13);
			  GameJLabel boardPlayer14 = new GameJLabel(iconPlayer14);
			  GameJLabel boardPlayer15 = new GameJLabel(iconPlayer15);
			  GameJLabel boardPlayer16 = new GameJLabel(iconPlayer16);
		
			  player.playerUI.rand9_1.add(boardPlayer1, new Integer(0));
		     player.playerUI.rand9_2.add(boardPlayer2, new Integer(0));
		     player.playerUI.rand9_3.add(boardPlayer3, new Integer(0));
		     player.playerUI.rand9_4.add(boardPlayer4, new Integer(0));
		     player.playerUI.rand9_5.add(boardPlayer5, new Integer(0));
		     player.playerUI.rand9_6.add(boardPlayer6, new Integer(0));
		     player.playerUI.rand9_7.add(boardPlayer7, new Integer(0));
		     player.playerUI.rand9_8.add(boardPlayer8, new Integer(0));
		     player.playerUI.rand9_9.add(boardPlayer9, new Integer(0));
		     player.playerUI.rand9_10.add(boardPlayer10, new Integer(0));
		     player.playerUI.rand9_11.add(boardPlayer11, new Integer(0));
		     player.playerUI.rand9_12.add(boardPlayer12, new Integer(0));
		     player.playerUI.rand9_13.add(boardPlayer13, new Integer(0));
		     player.playerUI.rand9_14.add(boardPlayer14, new Integer(0));
		     player.playerUI.rand9_15.add(boardPlayer15, new Integer(0));
		     player.playerUI.rand9_16.add(boardPlayer16, new Integer(0));
		
		     boardPlayer1.setBounds(0, 0, iconPlayer1.getIconWidth(),
		                                  iconPlayer1.getIconHeight()); 
		     player.playerUI.rand9_1.setPreferredSize(new Dimension(iconPlayer1.getIconWidth(),
		                                        iconPlayer1.getIconHeight()));
		     boardPlayer2.setBounds(0, 0, iconPlayer2.getIconWidth(),
		                                  iconPlayer2.getIconHeight()); 
		     player.playerUI.rand9_2.setPreferredSize(new Dimension(iconPlayer2.getIconWidth(),
		                                        iconPlayer2.getIconHeight()));
		     boardPlayer3.setBounds(0, 0, iconPlayer3.getIconWidth(),
		                                  iconPlayer3.getIconHeight()); 
		     player.playerUI.rand9_3.setPreferredSize(new Dimension(iconPlayer3.getIconWidth(),
		                                        iconPlayer3.getIconHeight()));
		     boardPlayer4.setBounds(0, 0, iconPlayer4.getIconWidth(),
		                                  iconPlayer4.getIconHeight()); 
		     player.playerUI.rand9_4.setPreferredSize(new Dimension(iconPlayer4.getIconWidth(),
		                                        iconPlayer4.getIconHeight()));
		     boardPlayer5.setBounds(0, 0, iconPlayer5.getIconWidth(),
		                                  iconPlayer5.getIconHeight()); 
		     player.playerUI.rand9_5.setPreferredSize(new Dimension(iconPlayer5.getIconWidth(),
		                                        iconPlayer5.getIconHeight()));
		     boardPlayer6.setBounds(0, 0, iconPlayer6.getIconWidth(),
		                                  iconPlayer6.getIconHeight()); 
		     player.playerUI.rand9_6.setPreferredSize(new Dimension(iconPlayer6.getIconWidth(),
		                                        iconPlayer6.getIconHeight()));
		     boardPlayer7.setBounds(0, 0, iconPlayer7.getIconWidth(),
		                                  iconPlayer7.getIconHeight()); 
		     player.playerUI.rand9_7.setPreferredSize(new Dimension(iconPlayer7.getIconWidth(),
		                                        iconPlayer7.getIconHeight()));
		     boardPlayer8.setBounds(0, 0, iconPlayer8.getIconWidth(),
		                                  iconPlayer8.getIconHeight()); 
		     player.playerUI.rand9_8.setPreferredSize(new Dimension(iconPlayer8.getIconWidth(),
		                                        iconPlayer8.getIconHeight()));
		     boardPlayer9.setBounds(0, 0, iconPlayer9.getIconWidth(),
		                                  iconPlayer9.getIconHeight()); 
		     player.playerUI.rand9_9.setPreferredSize(new Dimension(iconPlayer9.getIconWidth(),
		                                        iconPlayer9.getIconHeight()));
		     boardPlayer10.setBounds(0, 0, iconPlayer10.getIconWidth(),
		                                  iconPlayer10.getIconHeight()); 
		     player.playerUI.rand9_10.setPreferredSize(new Dimension(iconPlayer10.getIconWidth(),
		                                        iconPlayer10.getIconHeight()));													 													 
		     boardPlayer11.setBounds(0, 0, iconPlayer11.getIconWidth(),
		                                  iconPlayer11.getIconHeight()); 
		     player.playerUI.rand9_11.setPreferredSize(new Dimension(iconPlayer11.getIconWidth(),
		                                        iconPlayer11.getIconHeight()));
		     boardPlayer12.setBounds(0, 0, iconPlayer12.getIconWidth(),
		                                  iconPlayer12.getIconHeight()); 
		     player.playerUI.rand9_12.setPreferredSize(new Dimension(iconPlayer12.getIconWidth(),
		                                        iconPlayer12.getIconHeight()));
		     boardPlayer13.setBounds(0, 0, iconPlayer13.getIconWidth(),
		                                  iconPlayer13.getIconHeight()); 
		     player.playerUI.rand9_13.setPreferredSize(new Dimension(iconPlayer13.getIconWidth(),
		                                        iconPlayer13.getIconHeight()));
		     boardPlayer14.setBounds(0, 0, iconPlayer14.getIconWidth(),
		                                  iconPlayer14.getIconHeight()); 
		     player.playerUI.rand9_14.setPreferredSize(new Dimension(iconPlayer14.getIconWidth(),
		                                        iconPlayer14.getIconHeight()));
		     boardPlayer15.setBounds(0, 0, iconPlayer15.getIconWidth(),
		                                  iconPlayer15.getIconHeight()); 
		     player.playerUI.rand9_15.setPreferredSize(new Dimension(iconPlayer15.getIconWidth(),
		                                        iconPlayer15.getIconHeight()));	
		     boardPlayer16.setBounds(0, 0, iconPlayer16.getIconWidth(),
		                                  iconPlayer16.getIconHeight()); 
		     player.playerUI.rand9_16.setPreferredSize(new Dimension(iconPlayer16.getIconWidth(),
		                                        iconPlayer16.getIconHeight()));													 												 													 
		
			  player.playerUI.mypanel2.add(player.playerUI.rand9_1);
		     player.playerUI.mypanel2.add(player.playerUI.rand9_2);
		     player.playerUI.mypanel2.add(player.playerUI.rand9_3);
		     player.playerUI.mypanel2.add(player.playerUI.rand9_4);
		     player.playerUI.mypanel2.add(player.playerUI.rand9_5);
		     player.playerUI.mypanel2.add(player.playerUI.rand9_6);
		     player.playerUI.mypanel2.add(player.playerUI.rand9_7);
		     player.playerUI.mypanel2.add(player.playerUI.rand9_8);
		     player.playerUI.mypanel2.add(player.playerUI.rand9_9);
		     player.playerUI.mypanel2.add(player.playerUI.rand9_10);
		     player.playerUI.mypanel2.add(player.playerUI.rand9_11);
		     player.playerUI.mypanel2.add(player.playerUI.rand9_12);
		     player.playerUI.mypanel2.add(player.playerUI.rand9_13);
		     player.playerUI.mypanel2.add(player.playerUI.rand9_14);
		     player.playerUI.mypanel2.add(player.playerUI.rand9_15);
		     player.playerUI.mypanel2.add(player.playerUI.rand9_16);
		  
		  }
		  else if(tile == 5)
		  {
  	        iconPlayer1 =  (bp[4][4].getIcon());
	        iconPlayer2 =  (bp[5][4].getIcon());
 	        iconPlayer3 =  (bp[6][4].getIcon());
	        iconPlayer4 =  (bp[7][4].getIcon());
	        iconPlayer5 =  (bp[4][5].getIcon());
	        iconPlayer6 =  (bp[5][5].getIcon());
	        iconPlayer7 =  (bp[6][5].getIcon());
	        iconPlayer8 =  (bp[7][5].getIcon());
	        iconPlayer9 =  (bp[4][6].getIcon());
	        iconPlayer10 =  (bp[5][6].getIcon());
	        iconPlayer11 =  (bp[6][6].getIcon());
	        iconPlayer12 =  (bp[7][6].getIcon());
	        iconPlayer13 =  (bp[4][7].getIcon());
	        iconPlayer14 =  (bp[5][7].getIcon());
	        iconPlayer15 =  (bp[6][7].getIcon());
	        iconPlayer16 =  (bp[7][7].getIcon());

	
		//add components to board
			  GameJLabel boardPlayer1 = new GameJLabel(iconPlayer1);
			  GameJLabel boardPlayer2 = new GameJLabel(iconPlayer2);
			  GameJLabel boardPlayer3 = new GameJLabel(iconPlayer3);
			  GameJLabel boardPlayer4 = new GameJLabel(iconPlayer4);
			  GameJLabel boardPlayer5 = new GameJLabel(iconPlayer5);
			  GameJLabel boardPlayer6 = new GameJLabel(iconPlayer6);
			  GameJLabel boardPlayer7 = new GameJLabel(iconPlayer7);
			  GameJLabel boardPlayer8 = new GameJLabel(iconPlayer8);
			  GameJLabel boardPlayer9 = new GameJLabel(iconPlayer9);
			  GameJLabel boardPlayer10 = new GameJLabel(iconPlayer10);
			  GameJLabel boardPlayer11 = new GameJLabel(iconPlayer11);
			  GameJLabel boardPlayer12 = new GameJLabel(iconPlayer12);
			  GameJLabel boardPlayer13 = new GameJLabel(iconPlayer13);
			  GameJLabel boardPlayer14 = new GameJLabel(iconPlayer14);
			  GameJLabel boardPlayer15 = new GameJLabel(iconPlayer15);
			  GameJLabel boardPlayer16 = new GameJLabel(iconPlayer16);
		
			  player.playerUI.rand9_1.add(boardPlayer1, new Integer(0));
		     player.playerUI.rand9_2.add(boardPlayer2, new Integer(0));
		     player.playerUI.rand9_3.add(boardPlayer3, new Integer(0));
		     player.playerUI.rand9_4.add(boardPlayer4, new Integer(0));
		     player.playerUI.rand9_5.add(boardPlayer5, new Integer(0));
		     player.playerUI.rand9_6.add(boardPlayer6, new Integer(0));
		     player.playerUI.rand9_7.add(boardPlayer7, new Integer(0));
		     player.playerUI.rand9_8.add(boardPlayer8, new Integer(0));
		     player.playerUI.rand9_9.add(boardPlayer9, new Integer(0));
		     player.playerUI.rand9_10.add(boardPlayer10, new Integer(0));
		     player.playerUI.rand9_11.add(boardPlayer11, new Integer(0));
		     player.playerUI.rand9_12.add(boardPlayer12, new Integer(0));
		     player.playerUI.rand9_13.add(boardPlayer13, new Integer(0));
		     player.playerUI.rand9_14.add(boardPlayer14, new Integer(0));
		     player.playerUI.rand9_15.add(boardPlayer15, new Integer(0));
		     player.playerUI.rand9_16.add(boardPlayer16, new Integer(0));
		
		     boardPlayer1.setBounds(0, 0, iconPlayer1.getIconWidth(),
		                                  iconPlayer1.getIconHeight()); 
		     player.playerUI.rand9_1.setPreferredSize(new Dimension(iconPlayer1.getIconWidth(),
		                                        iconPlayer1.getIconHeight()));
		     boardPlayer2.setBounds(0, 0, iconPlayer2.getIconWidth(),
		                                  iconPlayer2.getIconHeight()); 
		     player.playerUI.rand9_2.setPreferredSize(new Dimension(iconPlayer2.getIconWidth(),
		                                        iconPlayer2.getIconHeight()));
		     boardPlayer3.setBounds(0, 0, iconPlayer3.getIconWidth(),
		                                  iconPlayer3.getIconHeight()); 
		     player.playerUI.rand9_3.setPreferredSize(new Dimension(iconPlayer3.getIconWidth(),
		                                        iconPlayer3.getIconHeight()));
		     boardPlayer4.setBounds(0, 0, iconPlayer4.getIconWidth(),
		                                  iconPlayer4.getIconHeight()); 
		     player.playerUI.rand9_4.setPreferredSize(new Dimension(iconPlayer4.getIconWidth(),
		                                        iconPlayer4.getIconHeight()));
		     boardPlayer5.setBounds(0, 0, iconPlayer5.getIconWidth(),
		                                  iconPlayer5.getIconHeight()); 
		     player.playerUI.rand9_5.setPreferredSize(new Dimension(iconPlayer5.getIconWidth(),
		                                        iconPlayer5.getIconHeight()));
		     boardPlayer6.setBounds(0, 0, iconPlayer6.getIconWidth(),
		                                  iconPlayer6.getIconHeight()); 
		     player.playerUI.rand9_6.setPreferredSize(new Dimension(iconPlayer6.getIconWidth(),
		                                        iconPlayer6.getIconHeight()));
		     boardPlayer7.setBounds(0, 0, iconPlayer7.getIconWidth(),
		                                  iconPlayer7.getIconHeight()); 
		     player.playerUI.rand9_7.setPreferredSize(new Dimension(iconPlayer7.getIconWidth(),
		                                        iconPlayer7.getIconHeight()));
		     boardPlayer8.setBounds(0, 0, iconPlayer8.getIconWidth(),
		                                  iconPlayer8.getIconHeight()); 
		     player.playerUI.rand9_8.setPreferredSize(new Dimension(iconPlayer8.getIconWidth(),
		                                        iconPlayer8.getIconHeight()));
		     boardPlayer9.setBounds(0, 0, iconPlayer9.getIconWidth(),
		                                  iconPlayer9.getIconHeight()); 
		     player.playerUI.rand9_9.setPreferredSize(new Dimension(iconPlayer9.getIconWidth(),
		                                        iconPlayer9.getIconHeight()));
		     boardPlayer10.setBounds(0, 0, iconPlayer10.getIconWidth(),
		                                  iconPlayer10.getIconHeight()); 
		     player.playerUI.rand9_10.setPreferredSize(new Dimension(iconPlayer10.getIconWidth(),
		                                        iconPlayer10.getIconHeight()));													 													 
		     boardPlayer11.setBounds(0, 0, iconPlayer11.getIconWidth(),
		                                  iconPlayer11.getIconHeight()); 
		     player.playerUI.rand9_11.setPreferredSize(new Dimension(iconPlayer11.getIconWidth(),
		                                        iconPlayer11.getIconHeight()));
		     boardPlayer12.setBounds(0, 0, iconPlayer12.getIconWidth(),
		                                  iconPlayer12.getIconHeight()); 
		     player.playerUI.rand9_12.setPreferredSize(new Dimension(iconPlayer12.getIconWidth(),
		                                        iconPlayer12.getIconHeight()));
		     boardPlayer13.setBounds(0, 0, iconPlayer13.getIconWidth(),
		                                  iconPlayer13.getIconHeight()); 
		     player.playerUI.rand9_13.setPreferredSize(new Dimension(iconPlayer13.getIconWidth(),
		                                        iconPlayer13.getIconHeight()));
		     boardPlayer14.setBounds(0, 0, iconPlayer14.getIconWidth(),
		                                  iconPlayer14.getIconHeight()); 
		     player.playerUI.rand9_14.setPreferredSize(new Dimension(iconPlayer14.getIconWidth(),
		                                        iconPlayer14.getIconHeight()));
		     boardPlayer15.setBounds(0, 0, iconPlayer15.getIconWidth(),
		                                  iconPlayer15.getIconHeight()); 
		     player.playerUI.rand9_15.setPreferredSize(new Dimension(iconPlayer15.getIconWidth(),
		                                        iconPlayer15.getIconHeight()));	
		     boardPlayer16.setBounds(0, 0, iconPlayer16.getIconWidth(),
		                                  iconPlayer16.getIconHeight()); 
		     player.playerUI.rand9_16.setPreferredSize(new Dimension(iconPlayer16.getIconWidth(),
		                                        iconPlayer16.getIconHeight()));													 												 													 
		
			  player.playerUI.mypanel5.add(player.playerUI.rand9_1);
		     player.playerUI.mypanel5.add(player.playerUI.rand9_2);
		     player.playerUI.mypanel5.add(player.playerUI.rand9_3);
		     player.playerUI.mypanel5.add(player.playerUI.rand9_4);
		     player.playerUI.mypanel5.add(player.playerUI.rand9_5);
		     player.playerUI.mypanel5.add(player.playerUI.rand9_6);
		     player.playerUI.mypanel5.add(player.playerUI.rand9_7);
		     player.playerUI.mypanel5.add(player.playerUI.rand9_8);
		     player.playerUI.mypanel5.add(player.playerUI.rand9_9);
		     player.playerUI.mypanel5.add(player.playerUI.rand9_10);
		     player.playerUI.mypanel5.add(player.playerUI.rand9_11);
		     player.playerUI.mypanel5.add(player.playerUI.rand9_12);
		     player.playerUI.mypanel5.add(player.playerUI.rand9_13);
		     player.playerUI.mypanel5.add(player.playerUI.rand9_14);
		     player.playerUI.mypanel5.add(player.playerUI.rand9_15);
		     player.playerUI.mypanel5.add(player.playerUI.rand9_16);
		  
		  }
		  else if(tile == 6)
		  {
  	        iconPlayer1 =  (bp[4][8].getIcon());
	        iconPlayer2 =  (bp[5][8].getIcon());
 	        iconPlayer3 =  (bp[6][8].getIcon());
	        iconPlayer4 =  (bp[7][8].getIcon());
	        iconPlayer5 =  (bp[4][9].getIcon());
	        iconPlayer6 =  (bp[5][9].getIcon());
	        iconPlayer7 =  (bp[6][9].getIcon());
	        iconPlayer8 =  (bp[7][9].getIcon());
	        iconPlayer9 =  (bp[4][10].getIcon());
	        iconPlayer10 =  (bp[5][10].getIcon());
	        iconPlayer11 =  (bp[6][10].getIcon());
	        iconPlayer12 =  (bp[7][10].getIcon());
	        iconPlayer13 =  (bp[4][11].getIcon());
	        iconPlayer14 =  (bp[5][11].getIcon());
	        iconPlayer15 =  (bp[6][11].getIcon());
	        iconPlayer16 =  (bp[7][11].getIcon());

	
		//add components to board
			  GameJLabel boardPlayer1 = new GameJLabel(iconPlayer1);
			  GameJLabel boardPlayer2 = new GameJLabel(iconPlayer2);
			  GameJLabel boardPlayer3 = new GameJLabel(iconPlayer3);
			  GameJLabel boardPlayer4 = new GameJLabel(iconPlayer4);
			  GameJLabel boardPlayer5 = new GameJLabel(iconPlayer5);
			  GameJLabel boardPlayer6 = new GameJLabel(iconPlayer6);
			  GameJLabel boardPlayer7 = new GameJLabel(iconPlayer7);
			  GameJLabel boardPlayer8 = new GameJLabel(iconPlayer8);
			  GameJLabel boardPlayer9 = new GameJLabel(iconPlayer9);
			  GameJLabel boardPlayer10 = new GameJLabel(iconPlayer10);
			  GameJLabel boardPlayer11 = new GameJLabel(iconPlayer11);
			  GameJLabel boardPlayer12 = new GameJLabel(iconPlayer12);
			  GameJLabel boardPlayer13 = new GameJLabel(iconPlayer13);
			  GameJLabel boardPlayer14 = new GameJLabel(iconPlayer14);
			  GameJLabel boardPlayer15 = new GameJLabel(iconPlayer15);
			  GameJLabel boardPlayer16 = new GameJLabel(iconPlayer16);
		
			  player.playerUI.rand9_1.add(boardPlayer1, new Integer(0));
		     player.playerUI.rand9_2.add(boardPlayer2, new Integer(0));
		     player.playerUI.rand9_3.add(boardPlayer3, new Integer(0));
		     player.playerUI.rand9_4.add(boardPlayer4, new Integer(0));
		     player.playerUI.rand9_5.add(boardPlayer5, new Integer(0));
		     player.playerUI.rand9_6.add(boardPlayer6, new Integer(0));
		     player.playerUI.rand9_7.add(boardPlayer7, new Integer(0));
		     player.playerUI.rand9_8.add(boardPlayer8, new Integer(0));
		     player.playerUI.rand9_9.add(boardPlayer9, new Integer(0));
		     player.playerUI.rand9_10.add(boardPlayer10, new Integer(0));
		     player.playerUI.rand9_11.add(boardPlayer11, new Integer(0));
		     player.playerUI.rand9_12.add(boardPlayer12, new Integer(0));
		     player.playerUI.rand9_13.add(boardPlayer13, new Integer(0));
		     player.playerUI.rand9_14.add(boardPlayer14, new Integer(0));
		     player.playerUI.rand9_15.add(boardPlayer15, new Integer(0));
		     player.playerUI.rand9_16.add(boardPlayer16, new Integer(0));
		
		     boardPlayer1.setBounds(0, 0, iconPlayer1.getIconWidth(),
		                                  iconPlayer1.getIconHeight()); 
		     player.playerUI.rand9_1.setPreferredSize(new Dimension(iconPlayer1.getIconWidth(),
		                                        iconPlayer1.getIconHeight()));
		     boardPlayer2.setBounds(0, 0, iconPlayer2.getIconWidth(),
		                                  iconPlayer2.getIconHeight()); 
		     player.playerUI.rand9_2.setPreferredSize(new Dimension(iconPlayer2.getIconWidth(),
		                                        iconPlayer2.getIconHeight()));
		     boardPlayer3.setBounds(0, 0, iconPlayer3.getIconWidth(),
		                                  iconPlayer3.getIconHeight()); 
		     player.playerUI.rand9_3.setPreferredSize(new Dimension(iconPlayer3.getIconWidth(),
		                                        iconPlayer3.getIconHeight()));
		     boardPlayer4.setBounds(0, 0, iconPlayer4.getIconWidth(),
		                                  iconPlayer4.getIconHeight()); 
		     player.playerUI.rand9_4.setPreferredSize(new Dimension(iconPlayer4.getIconWidth(),
		                                        iconPlayer4.getIconHeight()));
		     boardPlayer5.setBounds(0, 0, iconPlayer5.getIconWidth(),
		                                  iconPlayer5.getIconHeight()); 
		     player.playerUI.rand9_5.setPreferredSize(new Dimension(iconPlayer5.getIconWidth(),
		                                        iconPlayer5.getIconHeight()));
		     boardPlayer6.setBounds(0, 0, iconPlayer6.getIconWidth(),
		                                  iconPlayer6.getIconHeight()); 
		     player.playerUI.rand9_6.setPreferredSize(new Dimension(iconPlayer6.getIconWidth(),
		                                        iconPlayer6.getIconHeight()));
		     boardPlayer7.setBounds(0, 0, iconPlayer7.getIconWidth(),
		                                  iconPlayer7.getIconHeight()); 
		     player.playerUI.rand9_7.setPreferredSize(new Dimension(iconPlayer7.getIconWidth(),
		                                        iconPlayer7.getIconHeight()));
		     boardPlayer8.setBounds(0, 0, iconPlayer8.getIconWidth(),
		                                  iconPlayer8.getIconHeight()); 
		     player.playerUI.rand9_8.setPreferredSize(new Dimension(iconPlayer8.getIconWidth(),
		                                        iconPlayer8.getIconHeight()));
		     boardPlayer9.setBounds(0, 0, iconPlayer9.getIconWidth(),
		                                  iconPlayer9.getIconHeight()); 
		     player.playerUI.rand9_9.setPreferredSize(new Dimension(iconPlayer9.getIconWidth(),
		                                        iconPlayer9.getIconHeight()));
		     boardPlayer10.setBounds(0, 0, iconPlayer10.getIconWidth(),
		                                  iconPlayer10.getIconHeight()); 
		     player.playerUI.rand9_10.setPreferredSize(new Dimension(iconPlayer10.getIconWidth(),
		                                        iconPlayer10.getIconHeight()));													 													 
		     boardPlayer11.setBounds(0, 0, iconPlayer11.getIconWidth(),
		                                  iconPlayer11.getIconHeight()); 
		     player.playerUI.rand9_11.setPreferredSize(new Dimension(iconPlayer11.getIconWidth(),
		                                        iconPlayer11.getIconHeight()));
		     boardPlayer12.setBounds(0, 0, iconPlayer12.getIconWidth(),
		                                  iconPlayer12.getIconHeight()); 
		     player.playerUI.rand9_12.setPreferredSize(new Dimension(iconPlayer12.getIconWidth(),
		                                        iconPlayer12.getIconHeight()));
		     boardPlayer13.setBounds(0, 0, iconPlayer13.getIconWidth(),
		                                  iconPlayer13.getIconHeight()); 
		     player.playerUI.rand9_13.setPreferredSize(new Dimension(iconPlayer13.getIconWidth(),
		                                        iconPlayer13.getIconHeight()));
		     boardPlayer14.setBounds(0, 0, iconPlayer14.getIconWidth(),
		                                  iconPlayer14.getIconHeight()); 
		     player.playerUI.rand9_14.setPreferredSize(new Dimension(iconPlayer14.getIconWidth(),
		                                        iconPlayer14.getIconHeight()));
		     boardPlayer15.setBounds(0, 0, iconPlayer15.getIconWidth(),
		                                  iconPlayer15.getIconHeight()); 
		     player.playerUI.rand9_15.setPreferredSize(new Dimension(iconPlayer15.getIconWidth(),
		                                        iconPlayer15.getIconHeight()));	
		     boardPlayer16.setBounds(0, 0, iconPlayer16.getIconWidth(),
		                                  iconPlayer16.getIconHeight()); 
		     player.playerUI.rand9_16.setPreferredSize(new Dimension(iconPlayer16.getIconWidth(),
		                                        iconPlayer16.getIconHeight()));													 												 													 
		
			  player.playerUI.mypanel6.add(player.playerUI.rand9_1);
		     player.playerUI.mypanel6.add(player.playerUI.rand9_2);
		     player.playerUI.mypanel6.add(player.playerUI.rand9_3);
		     player.playerUI.mypanel6.add(player.playerUI.rand9_4);
		     player.playerUI.mypanel6.add(player.playerUI.rand9_5);
		     player.playerUI.mypanel6.add(player.playerUI.rand9_6);
		     player.playerUI.mypanel6.add(player.playerUI.rand9_7);
		     player.playerUI.mypanel6.add(player.playerUI.rand9_8);
		     player.playerUI.mypanel6.add(player.playerUI.rand9_9);
		     player.playerUI.mypanel6.add(player.playerUI.rand9_10);
		     player.playerUI.mypanel6.add(player.playerUI.rand9_11);
		     player.playerUI.mypanel6.add(player.playerUI.rand9_12);
		     player.playerUI.mypanel6.add(player.playerUI.rand9_13);
		     player.playerUI.mypanel6.add(player.playerUI.rand9_14);
		     player.playerUI.mypanel6.add(player.playerUI.rand9_15);
		     player.playerUI.mypanel6.add(player.playerUI.rand9_16);
		  
		  }
		  else if(tile == 7)
		  {
	       iconPlayer1 =  bp[4][12].getIcon();
	       iconPlayer2 =  bp[5][12].getIcon();
	       iconPlayer3 =  bp[6][12].getIcon();
	       iconPlayer4 =  bp[7][12].getIcon();
	       iconPlayer5 =  bp[4][13].getIcon();
	       iconPlayer6 =  bp[5][13].getIcon();
	       iconPlayer7 =  bp[6][13].getIcon();
	       iconPlayer8 =  bp[7][13].getIcon();
	       iconPlayer9 =  bp[4][14].getIcon();
	       iconPlayer10 =  bp[5][14].getIcon();
	       iconPlayer11 =  bp[6][14].getIcon();
	       iconPlayer12 =  bp[7][14].getIcon();
	       iconPlayer13 =  bp[4][15].getIcon();
	       iconPlayer14 =  bp[5][15].getIcon();
	       iconPlayer15 =  bp[6][15].getIcon();
	       iconPlayer16 =  bp[7][15].getIcon();


		//add components to board
			  GameJLabel boardPlayer1 = new GameJLabel(iconPlayer1);
			  GameJLabel boardPlayer2 = new GameJLabel(iconPlayer2);
			  GameJLabel boardPlayer3 = new GameJLabel(iconPlayer3);
			  GameJLabel boardPlayer4 = new GameJLabel(iconPlayer4);
			  GameJLabel boardPlayer5 = new GameJLabel(iconPlayer5);
			  GameJLabel boardPlayer6 = new GameJLabel(iconPlayer6);
			  GameJLabel boardPlayer7 = new GameJLabel(iconPlayer7);
			  GameJLabel boardPlayer8 = new GameJLabel(iconPlayer8);
			  GameJLabel boardPlayer9 = new GameJLabel(iconPlayer9);
			  GameJLabel boardPlayer10 = new GameJLabel(iconPlayer10);
			  GameJLabel boardPlayer11 = new GameJLabel(iconPlayer11);
			  GameJLabel boardPlayer12 = new GameJLabel(iconPlayer12);
			  GameJLabel boardPlayer13 = new GameJLabel(iconPlayer13);
			  GameJLabel boardPlayer14 = new GameJLabel(iconPlayer14);
			  GameJLabel boardPlayer15 = new GameJLabel(iconPlayer15);
			  GameJLabel boardPlayer16 = new GameJLabel(iconPlayer16);
		
			  player.playerUI.rand9_1.add(boardPlayer1, new Integer(0));
		     player.playerUI.rand9_2.add(boardPlayer2, new Integer(0));
		     player.playerUI.rand9_3.add(boardPlayer3, new Integer(0));
		     player.playerUI.rand9_4.add(boardPlayer4, new Integer(0));
		     player.playerUI.rand9_5.add(boardPlayer5, new Integer(0));
		     player.playerUI.rand9_6.add(boardPlayer6, new Integer(0));
		     player.playerUI.rand9_7.add(boardPlayer7, new Integer(0));
		     player.playerUI.rand9_8.add(boardPlayer8, new Integer(0));
		     player.playerUI.rand9_9.add(boardPlayer9, new Integer(0));
		     player.playerUI.rand9_10.add(boardPlayer10, new Integer(0));
		     player.playerUI.rand9_11.add(boardPlayer11, new Integer(0));
		     player.playerUI.rand9_12.add(boardPlayer12, new Integer(0));
		     player.playerUI.rand9_13.add(boardPlayer13, new Integer(0));
		     player.playerUI.rand9_14.add(boardPlayer14, new Integer(0));
		     player.playerUI.rand9_15.add(boardPlayer15, new Integer(0));
		     player.playerUI.rand9_16.add(boardPlayer16, new Integer(0));
		
		     boardPlayer1.setBounds(0, 0, iconPlayer1.getIconWidth(),
		                                  iconPlayer1.getIconHeight()); 
		     player.playerUI.rand9_1.setPreferredSize(new Dimension(iconPlayer1.getIconWidth(),
		                                        iconPlayer1.getIconHeight()));
		     boardPlayer2.setBounds(0, 0, iconPlayer2.getIconWidth(),
		                                  iconPlayer2.getIconHeight()); 
		     player.playerUI.rand9_2.setPreferredSize(new Dimension(iconPlayer2.getIconWidth(),
		                                        iconPlayer2.getIconHeight()));
		     boardPlayer3.setBounds(0, 0, iconPlayer3.getIconWidth(),
		                                  iconPlayer3.getIconHeight()); 
		     player.playerUI.rand9_3.setPreferredSize(new Dimension(iconPlayer3.getIconWidth(),
		                                        iconPlayer3.getIconHeight()));
		     boardPlayer4.setBounds(0, 0, iconPlayer4.getIconWidth(),
		                                  iconPlayer4.getIconHeight()); 
		     player.playerUI.rand9_4.setPreferredSize(new Dimension(iconPlayer4.getIconWidth(),
		                                        iconPlayer4.getIconHeight()));
		     boardPlayer5.setBounds(0, 0, iconPlayer5.getIconWidth(),
		                                  iconPlayer5.getIconHeight()); 
		     player.playerUI.rand9_5.setPreferredSize(new Dimension(iconPlayer5.getIconWidth(),
		                                        iconPlayer5.getIconHeight()));
		     boardPlayer6.setBounds(0, 0, iconPlayer6.getIconWidth(),
		                                  iconPlayer6.getIconHeight()); 
		     player.playerUI.rand9_6.setPreferredSize(new Dimension(iconPlayer6.getIconWidth(),
		                                        iconPlayer6.getIconHeight()));
		     boardPlayer7.setBounds(0, 0, iconPlayer7.getIconWidth(),
		                                  iconPlayer7.getIconHeight()); 
		     player.playerUI.rand9_7.setPreferredSize(new Dimension(iconPlayer7.getIconWidth(),
		                                        iconPlayer7.getIconHeight()));
		     boardPlayer8.setBounds(0, 0, iconPlayer8.getIconWidth(),
		                                  iconPlayer8.getIconHeight()); 
		     player.playerUI.rand9_8.setPreferredSize(new Dimension(iconPlayer8.getIconWidth(),
		                                        iconPlayer8.getIconHeight()));
		     boardPlayer9.setBounds(0, 0, iconPlayer9.getIconWidth(),
		                                  iconPlayer9.getIconHeight()); 
		     player.playerUI.rand9_9.setPreferredSize(new Dimension(iconPlayer9.getIconWidth(),
		                                        iconPlayer9.getIconHeight()));
		     boardPlayer10.setBounds(0, 0, iconPlayer10.getIconWidth(),
		                                  iconPlayer10.getIconHeight()); 
		     player.playerUI.rand9_10.setPreferredSize(new Dimension(iconPlayer10.getIconWidth(),
		                                        iconPlayer10.getIconHeight()));													 													 
		     boardPlayer11.setBounds(0, 0, iconPlayer11.getIconWidth(),
		                                  iconPlayer11.getIconHeight()); 
		     player.playerUI.rand9_11.setPreferredSize(new Dimension(iconPlayer11.getIconWidth(),
		                                        iconPlayer11.getIconHeight()));
		     boardPlayer12.setBounds(0, 0, iconPlayer12.getIconWidth(),
		                                  iconPlayer12.getIconHeight()); 
		     player.playerUI.rand9_12.setPreferredSize(new Dimension(iconPlayer12.getIconWidth(),
		                                        iconPlayer12.getIconHeight()));
		     boardPlayer13.setBounds(0, 0, iconPlayer13.getIconWidth(),
		                                  iconPlayer13.getIconHeight()); 
		     player.playerUI.rand9_13.setPreferredSize(new Dimension(iconPlayer13.getIconWidth(),
		                                        iconPlayer13.getIconHeight()));
		     boardPlayer14.setBounds(0, 0, iconPlayer14.getIconWidth(),
		                                  iconPlayer14.getIconHeight()); 
		     player.playerUI.rand9_14.setPreferredSize(new Dimension(iconPlayer14.getIconWidth(),
		                                        iconPlayer14.getIconHeight()));
		     boardPlayer15.setBounds(0, 0, iconPlayer15.getIconWidth(),
		                                  iconPlayer15.getIconHeight()); 
		     player.playerUI.rand9_15.setPreferredSize(new Dimension(iconPlayer15.getIconWidth(),
		                                        iconPlayer15.getIconHeight()));	
		     boardPlayer16.setBounds(0, 0, iconPlayer16.getIconWidth(),
		                                  iconPlayer16.getIconHeight()); 
		     player.playerUI.rand9_16.setPreferredSize(new Dimension(iconPlayer16.getIconWidth(),
		                                        iconPlayer16.getIconHeight()));													 												 													 
		
			  player.playerUI.mypanel7.add(player.playerUI.rand9_1);
		     player.playerUI.mypanel7.add(player.playerUI.rand9_2);
		     player.playerUI.mypanel7.add(player.playerUI.rand9_3);
		     player.playerUI.mypanel7.add(player.playerUI.rand9_4);
		     player.playerUI.mypanel7.add(player.playerUI.rand9_5);
		     player.playerUI.mypanel7.add(player.playerUI.rand9_6);
		     player.playerUI.mypanel7.add(player.playerUI.rand9_7);
		     player.playerUI.mypanel7.add(player.playerUI.rand9_8);
		     player.playerUI.mypanel7.add(player.playerUI.rand9_9);
		     player.playerUI.mypanel7.add(player.playerUI.rand9_10);
		     player.playerUI.mypanel7.add(player.playerUI.rand9_11);
		     player.playerUI.mypanel7.add(player.playerUI.rand9_12);
		     player.playerUI.mypanel7.add(player.playerUI.rand9_13);
		     player.playerUI.mypanel7.add(player.playerUI.rand9_14);
		     player.playerUI.mypanel7.add(player.playerUI.rand9_15);
		     player.playerUI.mypanel7.add(player.playerUI.rand9_16);
		  
		  }
		}

      private void populateRand10(int tile, Player player)
		{
		  if(tile == 0)
		  {
			  iconPlayer1 =  (bp[0][0].getIcon());
	        iconPlayer2 =  (bp[1][0].getIcon());
 	        iconPlayer3 =  (bp[2][0].getIcon());
	        iconPlayer4 =  (bp[3][0].getIcon());
	        iconPlayer5 =  (bp[0][1].getIcon());
	        iconPlayer6 =  (bp[1][1].getIcon());
	        iconPlayer7 =  (bp[2][1].getIcon());
	        iconPlayer8 =  (bp[3][1].getIcon());
	        iconPlayer9 =  (bp[0][2].getIcon());
	        iconPlayer10 =  (bp[1][2].getIcon());
	        iconPlayer11 =  (bp[2][2].getIcon());
	        iconPlayer12 =  (bp[3][2].getIcon());
	        iconPlayer13 =  (bp[0][3].getIcon());
	        iconPlayer14 =  (bp[1][3].getIcon());
	        iconPlayer15 =  (bp[2][3].getIcon());
	        iconPlayer16 =  (bp[3][3].getIcon());

	
		//add components to board
			  GameJLabel boardPlayer1 = new GameJLabel(iconPlayer1);
			  GameJLabel boardPlayer2 = new GameJLabel(iconPlayer2);
			  GameJLabel boardPlayer3 = new GameJLabel(iconPlayer3);
			  GameJLabel boardPlayer4 = new GameJLabel(iconPlayer4);
			  GameJLabel boardPlayer5 = new GameJLabel(iconPlayer5);
			  GameJLabel boardPlayer6 = new GameJLabel(iconPlayer6);
			  GameJLabel boardPlayer7 = new GameJLabel(iconPlayer7);
			  GameJLabel boardPlayer8 = new GameJLabel(iconPlayer8);
			  GameJLabel boardPlayer9 = new GameJLabel(iconPlayer9);
			  GameJLabel boardPlayer10 = new GameJLabel(iconPlayer10);
			  GameJLabel boardPlayer11 = new GameJLabel(iconPlayer11);
			  GameJLabel boardPlayer12 = new GameJLabel(iconPlayer12);
			  GameJLabel boardPlayer13 = new GameJLabel(iconPlayer13);
			  GameJLabel boardPlayer14 = new GameJLabel(iconPlayer14);
			  GameJLabel boardPlayer15 = new GameJLabel(iconPlayer15);
			  GameJLabel boardPlayer16 = new GameJLabel(iconPlayer16);
		
			  player.playerUI.rand10_1.add(boardPlayer1, new Integer(0));
		     player.playerUI.rand10_2.add(boardPlayer2, new Integer(0));
		     player.playerUI.rand10_3.add(boardPlayer3, new Integer(0));
		     player.playerUI.rand10_4.add(boardPlayer4, new Integer(0));
		     player.playerUI.rand10_5.add(boardPlayer5, new Integer(0));
		     player.playerUI.rand10_6.add(boardPlayer6, new Integer(0));
		     player.playerUI.rand10_7.add(boardPlayer7, new Integer(0));
		     player.playerUI.rand10_8.add(boardPlayer8, new Integer(0));
		     player.playerUI.rand10_9.add(boardPlayer9, new Integer(0));
		     player.playerUI.rand10_10.add(boardPlayer10, new Integer(0));
		     player.playerUI.rand10_11.add(boardPlayer11, new Integer(0));
		     player.playerUI.rand10_12.add(boardPlayer12, new Integer(0));
		     player.playerUI.rand10_13.add(boardPlayer13, new Integer(0));
		     player.playerUI.rand10_14.add(boardPlayer14, new Integer(0));
		     player.playerUI.rand10_15.add(boardPlayer15, new Integer(0));
		     player.playerUI.rand10_16.add(boardPlayer16, new Integer(0));
		
		     boardPlayer1.setBounds(0, 0, iconPlayer1.getIconWidth(),
		                                  iconPlayer1.getIconHeight()); 
		     player.playerUI.rand10_1.setPreferredSize(new Dimension(iconPlayer1.getIconWidth(),
		                                        iconPlayer1.getIconHeight()));
		     boardPlayer2.setBounds(0, 0, iconPlayer2.getIconWidth(),
		                                  iconPlayer2.getIconHeight()); 
		     player.playerUI.rand10_2.setPreferredSize(new Dimension(iconPlayer2.getIconWidth(),
		                                        iconPlayer2.getIconHeight()));
		     boardPlayer3.setBounds(0, 0, iconPlayer3.getIconWidth(),
		                                  iconPlayer3.getIconHeight()); 
		     player.playerUI.rand10_3.setPreferredSize(new Dimension(iconPlayer3.getIconWidth(),
		                                        iconPlayer3.getIconHeight()));
		     boardPlayer4.setBounds(0, 0, iconPlayer4.getIconWidth(),
		                                  iconPlayer4.getIconHeight()); 
		     player.playerUI.rand10_4.setPreferredSize(new Dimension(iconPlayer4.getIconWidth(),
		                                        iconPlayer4.getIconHeight()));
		     boardPlayer5.setBounds(0, 0, iconPlayer5.getIconWidth(),
		                                  iconPlayer5.getIconHeight()); 
		     player.playerUI.rand10_5.setPreferredSize(new Dimension(iconPlayer5.getIconWidth(),
		                                        iconPlayer5.getIconHeight()));
		     boardPlayer6.setBounds(0, 0, iconPlayer6.getIconWidth(),
		                                  iconPlayer6.getIconHeight()); 
		     player.playerUI.rand10_6.setPreferredSize(new Dimension(iconPlayer6.getIconWidth(),
		                                        iconPlayer6.getIconHeight()));
		     boardPlayer7.setBounds(0, 0, iconPlayer7.getIconWidth(),
		                                  iconPlayer7.getIconHeight()); 
		     player.playerUI.rand10_7.setPreferredSize(new Dimension(iconPlayer7.getIconWidth(),
		                                        iconPlayer7.getIconHeight()));
		     boardPlayer8.setBounds(0, 0, iconPlayer8.getIconWidth(),
		                                  iconPlayer8.getIconHeight()); 
		     player.playerUI.rand10_8.setPreferredSize(new Dimension(iconPlayer8.getIconWidth(),
		                                        iconPlayer8.getIconHeight()));
		     boardPlayer9.setBounds(0, 0, iconPlayer9.getIconWidth(),
		                                  iconPlayer9.getIconHeight()); 
		     player.playerUI.rand10_9.setPreferredSize(new Dimension(iconPlayer9.getIconWidth(),
		                                        iconPlayer9.getIconHeight()));
		     boardPlayer10.setBounds(0, 0, iconPlayer10.getIconWidth(),
		                                  iconPlayer10.getIconHeight()); 
		     player.playerUI.rand10_10.setPreferredSize(new Dimension(iconPlayer10.getIconWidth(),
		                                        iconPlayer10.getIconHeight()));													 													 
		     boardPlayer11.setBounds(0, 0, iconPlayer11.getIconWidth(),
		                                  iconPlayer11.getIconHeight()); 
		     player.playerUI.rand10_11.setPreferredSize(new Dimension(iconPlayer11.getIconWidth(),
		                                        iconPlayer11.getIconHeight()));
		     boardPlayer12.setBounds(0, 0, iconPlayer12.getIconWidth(),
		                                  iconPlayer12.getIconHeight()); 
		     player.playerUI.rand10_12.setPreferredSize(new Dimension(iconPlayer12.getIconWidth(),
		                                        iconPlayer12.getIconHeight()));
		     boardPlayer13.setBounds(0, 0, iconPlayer13.getIconWidth(),
		                                  iconPlayer13.getIconHeight()); 
		     player.playerUI.rand10_13.setPreferredSize(new Dimension(iconPlayer13.getIconWidth(),
		                                        iconPlayer13.getIconHeight()));
		     boardPlayer14.setBounds(0, 0, iconPlayer14.getIconWidth(),
		                                  iconPlayer14.getIconHeight()); 
		     player.playerUI.rand10_14.setPreferredSize(new Dimension(iconPlayer14.getIconWidth(),
		                                        iconPlayer14.getIconHeight()));
		     boardPlayer15.setBounds(0, 0, iconPlayer15.getIconWidth(),
		                                  iconPlayer15.getIconHeight()); 
		     player.playerUI.rand10_15.setPreferredSize(new Dimension(iconPlayer15.getIconWidth(),
		                                        iconPlayer15.getIconHeight()));	
		     boardPlayer16.setBounds(0, 0, iconPlayer16.getIconWidth(),
		                                  iconPlayer16.getIconHeight()); 
		     player.playerUI.rand10_16.setPreferredSize(new Dimension(iconPlayer16.getIconWidth(),
		                                        iconPlayer16.getIconHeight()));													 												 													 
		
			  player.playerUI.mypanel0.add(player.playerUI.rand10_1);
		     player.playerUI.mypanel0.add(player.playerUI.rand10_2);
		     player.playerUI.mypanel0.add(player.playerUI.rand10_3);
		     player.playerUI.mypanel0.add(player.playerUI.rand10_4);
		     player.playerUI.mypanel0.add(player.playerUI.rand10_5);
		     player.playerUI.mypanel0.add(player.playerUI.rand10_6);
		     player.playerUI.mypanel0.add(player.playerUI.rand10_7);
		     player.playerUI.mypanel0.add(player.playerUI.rand10_8);
		     player.playerUI.mypanel0.add(player.playerUI.rand10_9);
		     player.playerUI.mypanel0.add(player.playerUI.rand10_10);
		     player.playerUI.mypanel0.add(player.playerUI.rand10_11);
		     player.playerUI.mypanel0.add(player.playerUI.rand10_12);
		     player.playerUI.mypanel0.add(player.playerUI.rand10_13);
		     player.playerUI.mypanel0.add(player.playerUI.rand10_14);
		     player.playerUI.mypanel0.add(player.playerUI.rand10_15);
		     player.playerUI.mypanel0.add(player.playerUI.rand10_16);
		  }
		  else if(tile == 1)
		  {
  	        iconPlayer1 =  (bp[0][4].getIcon());
	        iconPlayer2 =  (bp[1][4].getIcon());
 	        iconPlayer3 =  (bp[2][4].getIcon());
	        iconPlayer4 =  (bp[3][4].getIcon());
	        iconPlayer5 =  (bp[0][5].getIcon());
	        iconPlayer6 =  (bp[1][5].getIcon());
	        iconPlayer7 =  (bp[2][5].getIcon());
	        iconPlayer8 =  (bp[3][5].getIcon());
	        iconPlayer9 =  (bp[0][6].getIcon());
	        iconPlayer10 =  (bp[1][6].getIcon());
	        iconPlayer11 =  (bp[2][6].getIcon());
	        iconPlayer12 =  (bp[3][6].getIcon());
	        iconPlayer13 =  (bp[0][7].getIcon());
	        iconPlayer14 =  (bp[1][7].getIcon());
	        iconPlayer15 =  (bp[2][7].getIcon());
	        iconPlayer16 =  (bp[3][7].getIcon());

	
		//add components to board
			  GameJLabel boardPlayer1 = new GameJLabel(iconPlayer1);
			  GameJLabel boardPlayer2 = new GameJLabel(iconPlayer2);
			  GameJLabel boardPlayer3 = new GameJLabel(iconPlayer3);
			  GameJLabel boardPlayer4 = new GameJLabel(iconPlayer4);
			  GameJLabel boardPlayer5 = new GameJLabel(iconPlayer5);
			  GameJLabel boardPlayer6 = new GameJLabel(iconPlayer6);
			  GameJLabel boardPlayer7 = new GameJLabel(iconPlayer7);
			  GameJLabel boardPlayer8 = new GameJLabel(iconPlayer8);
			  GameJLabel boardPlayer9 = new GameJLabel(iconPlayer9);
			  GameJLabel boardPlayer10 = new GameJLabel(iconPlayer10);
			  GameJLabel boardPlayer11 = new GameJLabel(iconPlayer11);
			  GameJLabel boardPlayer12 = new GameJLabel(iconPlayer12);
			  GameJLabel boardPlayer13 = new GameJLabel(iconPlayer13);
			  GameJLabel boardPlayer14 = new GameJLabel(iconPlayer14);
			  GameJLabel boardPlayer15 = new GameJLabel(iconPlayer15);
			  GameJLabel boardPlayer16 = new GameJLabel(iconPlayer16);
		
			  player.playerUI.rand10_1.add(boardPlayer1, new Integer(0));
		     player.playerUI.rand10_2.add(boardPlayer2, new Integer(0));
		     player.playerUI.rand10_3.add(boardPlayer3, new Integer(0));
		     player.playerUI.rand10_4.add(boardPlayer4, new Integer(0));
		     player.playerUI.rand10_5.add(boardPlayer5, new Integer(0));
		     player.playerUI.rand10_6.add(boardPlayer6, new Integer(0));
		     player.playerUI.rand10_7.add(boardPlayer7, new Integer(0));
		     player.playerUI.rand10_8.add(boardPlayer8, new Integer(0));
		     player.playerUI.rand10_9.add(boardPlayer9, new Integer(0));
		     player.playerUI.rand10_10.add(boardPlayer10, new Integer(0));
		     player.playerUI.rand10_11.add(boardPlayer11, new Integer(0));
		     player.playerUI.rand10_12.add(boardPlayer12, new Integer(0));
		     player.playerUI.rand10_13.add(boardPlayer13, new Integer(0));
		     player.playerUI.rand10_14.add(boardPlayer14, new Integer(0));
		     player.playerUI.rand10_15.add(boardPlayer15, new Integer(0));
		     player.playerUI.rand10_16.add(boardPlayer16, new Integer(0));
		
		     boardPlayer1.setBounds(0, 0, iconPlayer1.getIconWidth(),
		                                  iconPlayer1.getIconHeight()); 
		     player.playerUI.rand10_1.setPreferredSize(new Dimension(iconPlayer1.getIconWidth(),
		                                        iconPlayer1.getIconHeight()));
		     boardPlayer2.setBounds(0, 0, iconPlayer2.getIconWidth(),
		                                  iconPlayer2.getIconHeight()); 
		     player.playerUI.rand10_2.setPreferredSize(new Dimension(iconPlayer2.getIconWidth(),
		                                        iconPlayer2.getIconHeight()));
		     boardPlayer3.setBounds(0, 0, iconPlayer3.getIconWidth(),
		                                  iconPlayer3.getIconHeight()); 
		     player.playerUI.rand10_3.setPreferredSize(new Dimension(iconPlayer3.getIconWidth(),
		                                        iconPlayer3.getIconHeight()));
		     boardPlayer4.setBounds(0, 0, iconPlayer4.getIconWidth(),
		                                  iconPlayer4.getIconHeight()); 
		     player.playerUI.rand10_4.setPreferredSize(new Dimension(iconPlayer4.getIconWidth(),
		                                        iconPlayer4.getIconHeight()));
		     boardPlayer5.setBounds(0, 0, iconPlayer5.getIconWidth(),
		                                  iconPlayer5.getIconHeight()); 
		     player.playerUI.rand10_5.setPreferredSize(new Dimension(iconPlayer5.getIconWidth(),
		                                        iconPlayer5.getIconHeight()));
		     boardPlayer6.setBounds(0, 0, iconPlayer6.getIconWidth(),
		                                  iconPlayer6.getIconHeight()); 
		     player.playerUI.rand10_6.setPreferredSize(new Dimension(iconPlayer6.getIconWidth(),
		                                        iconPlayer6.getIconHeight()));
		     boardPlayer7.setBounds(0, 0, iconPlayer7.getIconWidth(),
		                                  iconPlayer7.getIconHeight()); 
		     player.playerUI.rand10_7.setPreferredSize(new Dimension(iconPlayer7.getIconWidth(),
		                                        iconPlayer7.getIconHeight()));
		     boardPlayer8.setBounds(0, 0, iconPlayer8.getIconWidth(),
		                                  iconPlayer8.getIconHeight()); 
		     player.playerUI.rand10_8.setPreferredSize(new Dimension(iconPlayer8.getIconWidth(),
		                                        iconPlayer8.getIconHeight()));
		     boardPlayer9.setBounds(0, 0, iconPlayer9.getIconWidth(),
		                                  iconPlayer9.getIconHeight()); 
		     player.playerUI.rand10_9.setPreferredSize(new Dimension(iconPlayer9.getIconWidth(),
		                                        iconPlayer9.getIconHeight()));
		     boardPlayer10.setBounds(0, 0, iconPlayer10.getIconWidth(),
		                                  iconPlayer10.getIconHeight()); 
		     player.playerUI.rand10_10.setPreferredSize(new Dimension(iconPlayer10.getIconWidth(),
		                                        iconPlayer10.getIconHeight()));													 													 
		     boardPlayer11.setBounds(0, 0, iconPlayer11.getIconWidth(),
		                                  iconPlayer11.getIconHeight()); 
		     player.playerUI.rand10_11.setPreferredSize(new Dimension(iconPlayer11.getIconWidth(),
		                                        iconPlayer11.getIconHeight()));
		     boardPlayer12.setBounds(0, 0, iconPlayer12.getIconWidth(),
		                                  iconPlayer12.getIconHeight()); 
		     player.playerUI.rand10_12.setPreferredSize(new Dimension(iconPlayer12.getIconWidth(),
		                                        iconPlayer12.getIconHeight()));
		     boardPlayer13.setBounds(0, 0, iconPlayer13.getIconWidth(),
		                                  iconPlayer13.getIconHeight()); 
		     player.playerUI.rand10_13.setPreferredSize(new Dimension(iconPlayer13.getIconWidth(),
		                                        iconPlayer13.getIconHeight()));
		     boardPlayer14.setBounds(0, 0, iconPlayer14.getIconWidth(),
		                                  iconPlayer14.getIconHeight()); 
		     player.playerUI.rand10_14.setPreferredSize(new Dimension(iconPlayer14.getIconWidth(),
		                                        iconPlayer14.getIconHeight()));
		     boardPlayer15.setBounds(0, 0, iconPlayer15.getIconWidth(),
		                                  iconPlayer15.getIconHeight()); 
		     player.playerUI.rand10_15.setPreferredSize(new Dimension(iconPlayer15.getIconWidth(),
		                                        iconPlayer15.getIconHeight()));	
		     boardPlayer16.setBounds(0, 0, iconPlayer16.getIconWidth(),
		                                  iconPlayer16.getIconHeight()); 
		     player.playerUI.rand10_16.setPreferredSize(new Dimension(iconPlayer16.getIconWidth(),
		                                        iconPlayer16.getIconHeight()));													 												 													 
		
			  player.playerUI.mypanel1.add(player.playerUI.rand10_1);
		     player.playerUI.mypanel1.add(player.playerUI.rand10_2);
		     player.playerUI.mypanel1.add(player.playerUI.rand10_3);
		     player.playerUI.mypanel1.add(player.playerUI.rand10_4);
		     player.playerUI.mypanel1.add(player.playerUI.rand10_5);
		     player.playerUI.mypanel1.add(player.playerUI.rand10_6);
		     player.playerUI.mypanel1.add(player.playerUI.rand10_7);
		     player.playerUI.mypanel1.add(player.playerUI.rand10_8);
		     player.playerUI.mypanel1.add(player.playerUI.rand10_9);
		     player.playerUI.mypanel1.add(player.playerUI.rand10_10);
		     player.playerUI.mypanel1.add(player.playerUI.rand10_11);
		     player.playerUI.mypanel1.add(player.playerUI.rand10_12);
		     player.playerUI.mypanel1.add(player.playerUI.rand10_13);
		     player.playerUI.mypanel1.add(player.playerUI.rand10_14);
		     player.playerUI.mypanel1.add(player.playerUI.rand10_15);
		     player.playerUI.mypanel1.add(player.playerUI.rand10_16);
		  
		  }
		  else if(tile == 2)
		  {
  	        iconPlayer1 =  (bp[0][8].getIcon());
	        iconPlayer2 =  (bp[1][8].getIcon());
 	        iconPlayer3 =  (bp[2][8].getIcon());
	        iconPlayer4 =  (bp[3][8].getIcon());
	        iconPlayer5 =  (bp[0][9].getIcon());
	        iconPlayer6 =  (bp[1][9].getIcon());
	        iconPlayer7 =  (bp[2][9].getIcon());
	        iconPlayer8 =  (bp[3][9].getIcon());
	        iconPlayer9 =  (bp[0][10].getIcon());
	        iconPlayer10 =  (bp[1][10].getIcon());
	        iconPlayer11 =  (bp[2][10].getIcon());
	        iconPlayer12 =  (bp[3][10].getIcon());
	        iconPlayer13 =  (bp[0][11].getIcon());
	        iconPlayer14 =  (bp[1][11].getIcon());
	        iconPlayer15 =  (bp[2][11].getIcon());
	        iconPlayer16 =  (bp[3][11].getIcon());

	
		//add components to board
			  GameJLabel boardPlayer1 = new GameJLabel(iconPlayer1);
			  GameJLabel boardPlayer2 = new GameJLabel(iconPlayer2);
			  GameJLabel boardPlayer3 = new GameJLabel(iconPlayer3);
			  GameJLabel boardPlayer4 = new GameJLabel(iconPlayer4);
			  GameJLabel boardPlayer5 = new GameJLabel(iconPlayer5);
			  GameJLabel boardPlayer6 = new GameJLabel(iconPlayer6);
			  GameJLabel boardPlayer7 = new GameJLabel(iconPlayer7);
			  GameJLabel boardPlayer8 = new GameJLabel(iconPlayer8);
			  GameJLabel boardPlayer9 = new GameJLabel(iconPlayer9);
			  GameJLabel boardPlayer10 = new GameJLabel(iconPlayer10);
			  GameJLabel boardPlayer11 = new GameJLabel(iconPlayer11);
			  GameJLabel boardPlayer12 = new GameJLabel(iconPlayer12);
			  GameJLabel boardPlayer13 = new GameJLabel(iconPlayer13);
			  GameJLabel boardPlayer14 = new GameJLabel(iconPlayer14);
			  GameJLabel boardPlayer15 = new GameJLabel(iconPlayer15);
			  GameJLabel boardPlayer16 = new GameJLabel(iconPlayer16);
		
			  player.playerUI.rand10_1.add(boardPlayer1, new Integer(0));
		     player.playerUI.rand10_2.add(boardPlayer2, new Integer(0));
		     player.playerUI.rand10_3.add(boardPlayer3, new Integer(0));
		     player.playerUI.rand10_4.add(boardPlayer4, new Integer(0));
		     player.playerUI.rand10_5.add(boardPlayer5, new Integer(0));
		     player.playerUI.rand10_6.add(boardPlayer6, new Integer(0));
		     player.playerUI.rand10_7.add(boardPlayer7, new Integer(0));
		     player.playerUI.rand10_8.add(boardPlayer8, new Integer(0));
		     player.playerUI.rand10_9.add(boardPlayer9, new Integer(0));
		     player.playerUI.rand10_10.add(boardPlayer10, new Integer(0));
		     player.playerUI.rand10_11.add(boardPlayer11, new Integer(0));
		     player.playerUI.rand10_12.add(boardPlayer12, new Integer(0));
		     player.playerUI.rand10_13.add(boardPlayer13, new Integer(0));
		     player.playerUI.rand10_14.add(boardPlayer14, new Integer(0));
		     player.playerUI.rand10_15.add(boardPlayer15, new Integer(0));
		     player.playerUI.rand10_16.add(boardPlayer16, new Integer(0));
		
		     boardPlayer1.setBounds(0, 0, iconPlayer1.getIconWidth(),
		                                  iconPlayer1.getIconHeight()); 
		     player.playerUI.rand10_1.setPreferredSize(new Dimension(iconPlayer1.getIconWidth(),
		                                        iconPlayer1.getIconHeight()));
		     boardPlayer2.setBounds(0, 0, iconPlayer2.getIconWidth(),
		                                  iconPlayer2.getIconHeight()); 
		     player.playerUI.rand10_2.setPreferredSize(new Dimension(iconPlayer2.getIconWidth(),
		                                        iconPlayer2.getIconHeight()));
		     boardPlayer3.setBounds(0, 0, iconPlayer3.getIconWidth(),
		                                  iconPlayer3.getIconHeight()); 
		     player.playerUI.rand10_3.setPreferredSize(new Dimension(iconPlayer3.getIconWidth(),
		                                        iconPlayer3.getIconHeight()));
		     boardPlayer4.setBounds(0, 0, iconPlayer4.getIconWidth(),
		                                  iconPlayer4.getIconHeight()); 
		     player.playerUI.rand10_4.setPreferredSize(new Dimension(iconPlayer4.getIconWidth(),
		                                        iconPlayer4.getIconHeight()));
		     boardPlayer5.setBounds(0, 0, iconPlayer5.getIconWidth(),
		                                  iconPlayer5.getIconHeight()); 
		     player.playerUI.rand10_5.setPreferredSize(new Dimension(iconPlayer5.getIconWidth(),
		                                        iconPlayer5.getIconHeight()));
		     boardPlayer6.setBounds(0, 0, iconPlayer6.getIconWidth(),
		                                  iconPlayer6.getIconHeight()); 
		     player.playerUI.rand10_6.setPreferredSize(new Dimension(iconPlayer6.getIconWidth(),
		                                        iconPlayer6.getIconHeight()));
		     boardPlayer7.setBounds(0, 0, iconPlayer7.getIconWidth(),
		                                  iconPlayer7.getIconHeight()); 
		     player.playerUI.rand10_7.setPreferredSize(new Dimension(iconPlayer7.getIconWidth(),
		                                        iconPlayer7.getIconHeight()));
		     boardPlayer8.setBounds(0, 0, iconPlayer8.getIconWidth(),
		                                  iconPlayer8.getIconHeight()); 
		     player.playerUI.rand10_8.setPreferredSize(new Dimension(iconPlayer8.getIconWidth(),
		                                        iconPlayer8.getIconHeight()));
		     boardPlayer9.setBounds(0, 0, iconPlayer9.getIconWidth(),
		                                  iconPlayer9.getIconHeight()); 
		     player.playerUI.rand10_9.setPreferredSize(new Dimension(iconPlayer9.getIconWidth(),
		                                        iconPlayer9.getIconHeight()));
		     boardPlayer10.setBounds(0, 0, iconPlayer10.getIconWidth(),
		                                  iconPlayer10.getIconHeight()); 
		     player.playerUI.rand10_10.setPreferredSize(new Dimension(iconPlayer10.getIconWidth(),
		                                        iconPlayer10.getIconHeight()));													 													 
		     boardPlayer11.setBounds(0, 0, iconPlayer11.getIconWidth(),
		                                  iconPlayer11.getIconHeight()); 
		     player.playerUI.rand10_11.setPreferredSize(new Dimension(iconPlayer11.getIconWidth(),
		                                        iconPlayer11.getIconHeight()));
		     boardPlayer12.setBounds(0, 0, iconPlayer12.getIconWidth(),
		                                  iconPlayer12.getIconHeight()); 
		     player.playerUI.rand10_12.setPreferredSize(new Dimension(iconPlayer12.getIconWidth(),
		                                        iconPlayer12.getIconHeight()));
		     boardPlayer13.setBounds(0, 0, iconPlayer13.getIconWidth(),
		                                  iconPlayer13.getIconHeight()); 
		     player.playerUI.rand10_13.setPreferredSize(new Dimension(iconPlayer13.getIconWidth(),
		                                        iconPlayer13.getIconHeight()));
		     boardPlayer14.setBounds(0, 0, iconPlayer14.getIconWidth(),
		                                  iconPlayer14.getIconHeight()); 
		     player.playerUI.rand10_14.setPreferredSize(new Dimension(iconPlayer14.getIconWidth(),
		                                        iconPlayer14.getIconHeight()));
		     boardPlayer15.setBounds(0, 0, iconPlayer15.getIconWidth(),
		                                  iconPlayer15.getIconHeight()); 
		     player.playerUI.rand10_15.setPreferredSize(new Dimension(iconPlayer15.getIconWidth(),
		                                        iconPlayer15.getIconHeight()));	
		     boardPlayer16.setBounds(0, 0, iconPlayer16.getIconWidth(),
		                                  iconPlayer16.getIconHeight()); 
		     player.playerUI.rand10_16.setPreferredSize(new Dimension(iconPlayer16.getIconWidth(),
		                                        iconPlayer16.getIconHeight()));													 												 													 
		
			  player.playerUI.mypanel2.add(player.playerUI.rand10_1);
		     player.playerUI.mypanel2.add(player.playerUI.rand10_2);
		     player.playerUI.mypanel2.add(player.playerUI.rand10_3);
		     player.playerUI.mypanel2.add(player.playerUI.rand10_4);
		     player.playerUI.mypanel2.add(player.playerUI.rand10_5);
		     player.playerUI.mypanel2.add(player.playerUI.rand10_6);
		     player.playerUI.mypanel2.add(player.playerUI.rand10_7);
		     player.playerUI.mypanel2.add(player.playerUI.rand10_8);
		     player.playerUI.mypanel2.add(player.playerUI.rand10_9);
		     player.playerUI.mypanel2.add(player.playerUI.rand10_10);
		     player.playerUI.mypanel2.add(player.playerUI.rand10_11);
		     player.playerUI.mypanel2.add(player.playerUI.rand10_12);
		     player.playerUI.mypanel2.add(player.playerUI.rand10_13);
		     player.playerUI.mypanel2.add(player.playerUI.rand10_14);
		     player.playerUI.mypanel2.add(player.playerUI.rand10_15);
		     player.playerUI.mypanel2.add(player.playerUI.rand10_16);
		  
		  }
		  else if(tile == 5)
		  {
  	        iconPlayer1 =  (bp[4][4].getIcon());
	        iconPlayer2 =  (bp[5][4].getIcon());
 	        iconPlayer3 =  (bp[6][4].getIcon());
	        iconPlayer4 =  (bp[7][4].getIcon());
	        iconPlayer5 =  (bp[4][5].getIcon());
	        iconPlayer6 =  (bp[5][5].getIcon());
	        iconPlayer7 =  (bp[6][5].getIcon());
	        iconPlayer8 =  (bp[7][5].getIcon());
	        iconPlayer9 =  (bp[4][6].getIcon());
	        iconPlayer10 =  (bp[5][6].getIcon());
	        iconPlayer11 =  (bp[6][6].getIcon());
	        iconPlayer12 =  (bp[7][6].getIcon());
	        iconPlayer13 =  (bp[4][7].getIcon());
	        iconPlayer14 =  (bp[5][7].getIcon());
	        iconPlayer15 =  (bp[6][7].getIcon());
	        iconPlayer16 =  (bp[7][7].getIcon());

	
		//add components to board
			  GameJLabel boardPlayer1 = new GameJLabel(iconPlayer1);
			  GameJLabel boardPlayer2 = new GameJLabel(iconPlayer2);
			  GameJLabel boardPlayer3 = new GameJLabel(iconPlayer3);
			  GameJLabel boardPlayer4 = new GameJLabel(iconPlayer4);
			  GameJLabel boardPlayer5 = new GameJLabel(iconPlayer5);
			  GameJLabel boardPlayer6 = new GameJLabel(iconPlayer6);
			  GameJLabel boardPlayer7 = new GameJLabel(iconPlayer7);
			  GameJLabel boardPlayer8 = new GameJLabel(iconPlayer8);
			  GameJLabel boardPlayer9 = new GameJLabel(iconPlayer9);
			  GameJLabel boardPlayer10 = new GameJLabel(iconPlayer10);
			  GameJLabel boardPlayer11 = new GameJLabel(iconPlayer11);
			  GameJLabel boardPlayer12 = new GameJLabel(iconPlayer12);
			  GameJLabel boardPlayer13 = new GameJLabel(iconPlayer13);
			  GameJLabel boardPlayer14 = new GameJLabel(iconPlayer14);
			  GameJLabel boardPlayer15 = new GameJLabel(iconPlayer15);
			  GameJLabel boardPlayer16 = new GameJLabel(iconPlayer16);
		
			  player.playerUI.rand10_1.add(boardPlayer1, new Integer(0));
		     player.playerUI.rand10_2.add(boardPlayer2, new Integer(0));
		     player.playerUI.rand10_3.add(boardPlayer3, new Integer(0));
		     player.playerUI.rand10_4.add(boardPlayer4, new Integer(0));
		     player.playerUI.rand10_5.add(boardPlayer5, new Integer(0));
		     player.playerUI.rand10_6.add(boardPlayer6, new Integer(0));
		     player.playerUI.rand10_7.add(boardPlayer7, new Integer(0));
		     player.playerUI.rand10_8.add(boardPlayer8, new Integer(0));
		     player.playerUI.rand10_9.add(boardPlayer9, new Integer(0));
		     player.playerUI.rand10_10.add(boardPlayer10, new Integer(0));
		     player.playerUI.rand10_11.add(boardPlayer11, new Integer(0));
		     player.playerUI.rand10_12.add(boardPlayer12, new Integer(0));
		     player.playerUI.rand10_13.add(boardPlayer13, new Integer(0));
		     player.playerUI.rand10_14.add(boardPlayer14, new Integer(0));
		     player.playerUI.rand10_15.add(boardPlayer15, new Integer(0));
		     player.playerUI.rand10_16.add(boardPlayer16, new Integer(0));
		
		     boardPlayer1.setBounds(0, 0, iconPlayer1.getIconWidth(),
		                                  iconPlayer1.getIconHeight()); 
		     player.playerUI.rand10_1.setPreferredSize(new Dimension(iconPlayer1.getIconWidth(),
		                                        iconPlayer1.getIconHeight()));
		     boardPlayer2.setBounds(0, 0, iconPlayer2.getIconWidth(),
		                                  iconPlayer2.getIconHeight()); 
		     player.playerUI.rand10_2.setPreferredSize(new Dimension(iconPlayer2.getIconWidth(),
		                                        iconPlayer2.getIconHeight()));
		     boardPlayer3.setBounds(0, 0, iconPlayer3.getIconWidth(),
		                                  iconPlayer3.getIconHeight()); 
		     player.playerUI.rand10_3.setPreferredSize(new Dimension(iconPlayer3.getIconWidth(),
		                                        iconPlayer3.getIconHeight()));
		     boardPlayer4.setBounds(0, 0, iconPlayer4.getIconWidth(),
		                                  iconPlayer4.getIconHeight()); 
		     player.playerUI.rand10_4.setPreferredSize(new Dimension(iconPlayer4.getIconWidth(),
		                                        iconPlayer4.getIconHeight()));
		     boardPlayer5.setBounds(0, 0, iconPlayer5.getIconWidth(),
		                                  iconPlayer5.getIconHeight()); 
		     player.playerUI.rand10_5.setPreferredSize(new Dimension(iconPlayer5.getIconWidth(),
		                                        iconPlayer5.getIconHeight()));
		     boardPlayer6.setBounds(0, 0, iconPlayer6.getIconWidth(),
		                                  iconPlayer6.getIconHeight()); 
		     player.playerUI.rand10_6.setPreferredSize(new Dimension(iconPlayer6.getIconWidth(),
		                                        iconPlayer6.getIconHeight()));
		     boardPlayer7.setBounds(0, 0, iconPlayer7.getIconWidth(),
		                                  iconPlayer7.getIconHeight()); 
		     player.playerUI.rand10_7.setPreferredSize(new Dimension(iconPlayer7.getIconWidth(),
		                                        iconPlayer7.getIconHeight()));
		     boardPlayer8.setBounds(0, 0, iconPlayer8.getIconWidth(),
		                                  iconPlayer8.getIconHeight()); 
		     player.playerUI.rand10_8.setPreferredSize(new Dimension(iconPlayer8.getIconWidth(),
		                                        iconPlayer8.getIconHeight()));
		     boardPlayer9.setBounds(0, 0, iconPlayer9.getIconWidth(),
		                                  iconPlayer9.getIconHeight()); 
		     player.playerUI.rand10_9.setPreferredSize(new Dimension(iconPlayer9.getIconWidth(),
		                                        iconPlayer9.getIconHeight()));
		     boardPlayer10.setBounds(0, 0, iconPlayer10.getIconWidth(),
		                                  iconPlayer10.getIconHeight()); 
		     player.playerUI.rand10_10.setPreferredSize(new Dimension(iconPlayer10.getIconWidth(),
		                                        iconPlayer10.getIconHeight()));													 													 
		     boardPlayer11.setBounds(0, 0, iconPlayer11.getIconWidth(),
		                                  iconPlayer11.getIconHeight()); 
		     player.playerUI.rand10_11.setPreferredSize(new Dimension(iconPlayer11.getIconWidth(),
		                                        iconPlayer11.getIconHeight()));
		     boardPlayer12.setBounds(0, 0, iconPlayer12.getIconWidth(),
		                                  iconPlayer12.getIconHeight()); 
		     player.playerUI.rand10_12.setPreferredSize(new Dimension(iconPlayer12.getIconWidth(),
		                                        iconPlayer12.getIconHeight()));
		     boardPlayer13.setBounds(0, 0, iconPlayer13.getIconWidth(),
		                                  iconPlayer13.getIconHeight()); 
		     player.playerUI.rand10_13.setPreferredSize(new Dimension(iconPlayer13.getIconWidth(),
		                                        iconPlayer13.getIconHeight()));
		     boardPlayer14.setBounds(0, 0, iconPlayer14.getIconWidth(),
		                                  iconPlayer14.getIconHeight()); 
		     player.playerUI.rand10_14.setPreferredSize(new Dimension(iconPlayer14.getIconWidth(),
		                                        iconPlayer14.getIconHeight()));
		     boardPlayer15.setBounds(0, 0, iconPlayer15.getIconWidth(),
		                                  iconPlayer15.getIconHeight()); 
		     player.playerUI.rand10_15.setPreferredSize(new Dimension(iconPlayer15.getIconWidth(),
		                                        iconPlayer15.getIconHeight()));	
		     boardPlayer16.setBounds(0, 0, iconPlayer16.getIconWidth(),
		                                  iconPlayer16.getIconHeight()); 
		     player.playerUI.rand10_16.setPreferredSize(new Dimension(iconPlayer16.getIconWidth(),
		                                        iconPlayer16.getIconHeight()));													 												 													 
		
			  player.playerUI.mypanel5.add(player.playerUI.rand10_1);
		     player.playerUI.mypanel5.add(player.playerUI.rand10_2);
		     player.playerUI.mypanel5.add(player.playerUI.rand10_3);
		     player.playerUI.mypanel5.add(player.playerUI.rand10_4);
		     player.playerUI.mypanel5.add(player.playerUI.rand10_5);
		     player.playerUI.mypanel5.add(player.playerUI.rand10_6);
		     player.playerUI.mypanel5.add(player.playerUI.rand10_7);
		     player.playerUI.mypanel5.add(player.playerUI.rand10_8);
		     player.playerUI.mypanel5.add(player.playerUI.rand10_9);
		     player.playerUI.mypanel5.add(player.playerUI.rand10_10);
		     player.playerUI.mypanel5.add(player.playerUI.rand10_11);
		     player.playerUI.mypanel5.add(player.playerUI.rand10_12);
		     player.playerUI.mypanel5.add(player.playerUI.rand10_13);
		     player.playerUI.mypanel5.add(player.playerUI.rand10_14);
		     player.playerUI.mypanel5.add(player.playerUI.rand10_15);
		     player.playerUI.mypanel5.add(player.playerUI.rand10_16);
		  
		  }
		  else if(tile == 6)
		  {
  	        iconPlayer1 =  (bp[4][8].getIcon());
	        iconPlayer2 =  (bp[5][8].getIcon());
 	        iconPlayer3 =  (bp[6][8].getIcon());
	        iconPlayer4 =  (bp[7][8].getIcon());
	        iconPlayer5 =  (bp[4][9].getIcon());
	        iconPlayer6 =  (bp[5][9].getIcon());
	        iconPlayer7 =  (bp[6][9].getIcon());
	        iconPlayer8 =  (bp[7][9].getIcon());
	        iconPlayer9 =  (bp[4][10].getIcon());
	        iconPlayer10 =  (bp[5][10].getIcon());
	        iconPlayer11 =  (bp[6][10].getIcon());
	        iconPlayer12 =  (bp[7][10].getIcon());
	        iconPlayer13 =  (bp[4][11].getIcon());
	        iconPlayer14 =  (bp[5][11].getIcon());
	        iconPlayer15 =  (bp[6][11].getIcon());
	        iconPlayer16 =  (bp[7][11].getIcon());

	
		//add components to board
			  GameJLabel boardPlayer1 = new GameJLabel(iconPlayer1);
			  GameJLabel boardPlayer2 = new GameJLabel(iconPlayer2);
			  GameJLabel boardPlayer3 = new GameJLabel(iconPlayer3);
			  GameJLabel boardPlayer4 = new GameJLabel(iconPlayer4);
			  GameJLabel boardPlayer5 = new GameJLabel(iconPlayer5);
			  GameJLabel boardPlayer6 = new GameJLabel(iconPlayer6);
			  GameJLabel boardPlayer7 = new GameJLabel(iconPlayer7);
			  GameJLabel boardPlayer8 = new GameJLabel(iconPlayer8);
			  GameJLabel boardPlayer9 = new GameJLabel(iconPlayer9);
			  GameJLabel boardPlayer10 = new GameJLabel(iconPlayer10);
			  GameJLabel boardPlayer11 = new GameJLabel(iconPlayer11);
			  GameJLabel boardPlayer12 = new GameJLabel(iconPlayer12);
			  GameJLabel boardPlayer13 = new GameJLabel(iconPlayer13);
			  GameJLabel boardPlayer14 = new GameJLabel(iconPlayer14);
			  GameJLabel boardPlayer15 = new GameJLabel(iconPlayer15);
			  GameJLabel boardPlayer16 = new GameJLabel(iconPlayer16);
		
			  player.playerUI.rand10_1.add(boardPlayer1, new Integer(0));
		     player.playerUI.rand10_2.add(boardPlayer2, new Integer(0));
		     player.playerUI.rand10_3.add(boardPlayer3, new Integer(0));
		     player.playerUI.rand10_4.add(boardPlayer4, new Integer(0));
		     player.playerUI.rand10_5.add(boardPlayer5, new Integer(0));
		     player.playerUI.rand10_6.add(boardPlayer6, new Integer(0));
		     player.playerUI.rand10_7.add(boardPlayer7, new Integer(0));
		     player.playerUI.rand10_8.add(boardPlayer8, new Integer(0));
		     player.playerUI.rand10_9.add(boardPlayer9, new Integer(0));
		     player.playerUI.rand10_10.add(boardPlayer10, new Integer(0));
		     player.playerUI.rand10_11.add(boardPlayer11, new Integer(0));
		     player.playerUI.rand10_12.add(boardPlayer12, new Integer(0));
		     player.playerUI.rand10_13.add(boardPlayer13, new Integer(0));
		     player.playerUI.rand10_14.add(boardPlayer14, new Integer(0));
		     player.playerUI.rand10_15.add(boardPlayer15, new Integer(0));
		     player.playerUI.rand10_16.add(boardPlayer16, new Integer(0));
		
		     boardPlayer1.setBounds(0, 0, iconPlayer1.getIconWidth(),
		                                  iconPlayer1.getIconHeight()); 
		     player.playerUI.rand10_1.setPreferredSize(new Dimension(iconPlayer1.getIconWidth(),
		                                        iconPlayer1.getIconHeight()));
		     boardPlayer2.setBounds(0, 0, iconPlayer2.getIconWidth(),
		                                  iconPlayer2.getIconHeight()); 
		     player.playerUI.rand10_2.setPreferredSize(new Dimension(iconPlayer2.getIconWidth(),
		                                        iconPlayer2.getIconHeight()));
		     boardPlayer3.setBounds(0, 0, iconPlayer3.getIconWidth(),
		                                  iconPlayer3.getIconHeight()); 
		     player.playerUI.rand10_3.setPreferredSize(new Dimension(iconPlayer3.getIconWidth(),
		                                        iconPlayer3.getIconHeight()));
		     boardPlayer4.setBounds(0, 0, iconPlayer4.getIconWidth(),
		                                  iconPlayer4.getIconHeight()); 
		     player.playerUI.rand10_4.setPreferredSize(new Dimension(iconPlayer4.getIconWidth(),
		                                        iconPlayer4.getIconHeight()));
		     boardPlayer5.setBounds(0, 0, iconPlayer5.getIconWidth(),
		                                  iconPlayer5.getIconHeight()); 
		     player.playerUI.rand10_5.setPreferredSize(new Dimension(iconPlayer5.getIconWidth(),
		                                        iconPlayer5.getIconHeight()));
		     boardPlayer6.setBounds(0, 0, iconPlayer6.getIconWidth(),
		                                  iconPlayer6.getIconHeight()); 
		     player.playerUI.rand10_6.setPreferredSize(new Dimension(iconPlayer6.getIconWidth(),
		                                        iconPlayer6.getIconHeight()));
		     boardPlayer7.setBounds(0, 0, iconPlayer7.getIconWidth(),
		                                  iconPlayer7.getIconHeight()); 
		     player.playerUI.rand10_7.setPreferredSize(new Dimension(iconPlayer7.getIconWidth(),
		                                        iconPlayer7.getIconHeight()));
		     boardPlayer8.setBounds(0, 0, iconPlayer8.getIconWidth(),
		                                  iconPlayer8.getIconHeight()); 
		     player.playerUI.rand10_8.setPreferredSize(new Dimension(iconPlayer8.getIconWidth(),
		                                        iconPlayer8.getIconHeight()));
		     boardPlayer9.setBounds(0, 0, iconPlayer9.getIconWidth(),
		                                  iconPlayer9.getIconHeight()); 
		     player.playerUI.rand10_9.setPreferredSize(new Dimension(iconPlayer9.getIconWidth(),
		                                        iconPlayer9.getIconHeight()));
		     boardPlayer10.setBounds(0, 0, iconPlayer10.getIconWidth(),
		                                  iconPlayer10.getIconHeight()); 
		     player.playerUI.rand10_10.setPreferredSize(new Dimension(iconPlayer10.getIconWidth(),
		                                        iconPlayer10.getIconHeight()));													 													 
		     boardPlayer11.setBounds(0, 0, iconPlayer11.getIconWidth(),
		                                  iconPlayer11.getIconHeight()); 
		     player.playerUI.rand10_11.setPreferredSize(new Dimension(iconPlayer11.getIconWidth(),
		                                        iconPlayer11.getIconHeight()));
		     boardPlayer12.setBounds(0, 0, iconPlayer12.getIconWidth(),
		                                  iconPlayer12.getIconHeight()); 
		     player.playerUI.rand10_12.setPreferredSize(new Dimension(iconPlayer12.getIconWidth(),
		                                        iconPlayer12.getIconHeight()));
		     boardPlayer13.setBounds(0, 0, iconPlayer13.getIconWidth(),
		                                  iconPlayer13.getIconHeight()); 
		     player.playerUI.rand10_13.setPreferredSize(new Dimension(iconPlayer13.getIconWidth(),
		                                        iconPlayer13.getIconHeight()));
		     boardPlayer14.setBounds(0, 0, iconPlayer14.getIconWidth(),
		                                  iconPlayer14.getIconHeight()); 
		     player.playerUI.rand10_14.setPreferredSize(new Dimension(iconPlayer14.getIconWidth(),
		                                        iconPlayer14.getIconHeight()));
		     boardPlayer15.setBounds(0, 0, iconPlayer15.getIconWidth(),
		                                  iconPlayer15.getIconHeight()); 
		     player.playerUI.rand10_15.setPreferredSize(new Dimension(iconPlayer15.getIconWidth(),
		                                        iconPlayer15.getIconHeight()));	
		     boardPlayer16.setBounds(0, 0, iconPlayer16.getIconWidth(),
		                                  iconPlayer16.getIconHeight()); 
		     player.playerUI.rand10_16.setPreferredSize(new Dimension(iconPlayer16.getIconWidth(),
		                                        iconPlayer16.getIconHeight()));													 												 													 
		
			  player.playerUI.mypanel6.add(player.playerUI.rand10_1);
		     player.playerUI.mypanel6.add(player.playerUI.rand10_2);
		     player.playerUI.mypanel6.add(player.playerUI.rand10_3);
		     player.playerUI.mypanel6.add(player.playerUI.rand10_4);
		     player.playerUI.mypanel6.add(player.playerUI.rand10_5);
		     player.playerUI.mypanel6.add(player.playerUI.rand10_6);
		     player.playerUI.mypanel6.add(player.playerUI.rand10_7);
		     player.playerUI.mypanel6.add(player.playerUI.rand10_8);
		     player.playerUI.mypanel6.add(player.playerUI.rand10_9);
		     player.playerUI.mypanel6.add(player.playerUI.rand10_10);
		     player.playerUI.mypanel6.add(player.playerUI.rand10_11);
		     player.playerUI.mypanel6.add(player.playerUI.rand10_12);
		     player.playerUI.mypanel6.add(player.playerUI.rand10_13);
		     player.playerUI.mypanel6.add(player.playerUI.rand10_14);
		     player.playerUI.mypanel6.add(player.playerUI.rand10_15);
		     player.playerUI.mypanel6.add(player.playerUI.rand10_16);
		  
		  }
		  else if(tile == 7)
		  {
	       iconPlayer1 =  bp[4][12].getIcon();
	       iconPlayer2 =  bp[5][12].getIcon();
	       iconPlayer3 =  bp[6][12].getIcon();
	       iconPlayer4 =  bp[7][12].getIcon();
	       iconPlayer5 =  bp[4][13].getIcon();
	       iconPlayer6 =  bp[5][13].getIcon();
	       iconPlayer7 =  bp[6][13].getIcon();
	       iconPlayer8 =  bp[7][13].getIcon();
	       iconPlayer9 =  bp[4][14].getIcon();
	       iconPlayer10 =  bp[5][14].getIcon();
	       iconPlayer11 =  bp[6][14].getIcon();
	       iconPlayer12 =  bp[7][14].getIcon();
	       iconPlayer13 =  bp[4][15].getIcon();
	       iconPlayer14 =  bp[5][15].getIcon();
	       iconPlayer15 =  bp[6][15].getIcon();
	       iconPlayer16 =  bp[7][15].getIcon();


		//add components to board
			  GameJLabel boardPlayer1 = new GameJLabel(iconPlayer1);
			  GameJLabel boardPlayer2 = new GameJLabel(iconPlayer2);
			  GameJLabel boardPlayer3 = new GameJLabel(iconPlayer3);
			  GameJLabel boardPlayer4 = new GameJLabel(iconPlayer4);
			  GameJLabel boardPlayer5 = new GameJLabel(iconPlayer5);
			  GameJLabel boardPlayer6 = new GameJLabel(iconPlayer6);
			  GameJLabel boardPlayer7 = new GameJLabel(iconPlayer7);
			  GameJLabel boardPlayer8 = new GameJLabel(iconPlayer8);
			  GameJLabel boardPlayer9 = new GameJLabel(iconPlayer9);
			  GameJLabel boardPlayer10 = new GameJLabel(iconPlayer10);
			  GameJLabel boardPlayer11 = new GameJLabel(iconPlayer11);
			  GameJLabel boardPlayer12 = new GameJLabel(iconPlayer12);
			  GameJLabel boardPlayer13 = new GameJLabel(iconPlayer13);
			  GameJLabel boardPlayer14 = new GameJLabel(iconPlayer14);
			  GameJLabel boardPlayer15 = new GameJLabel(iconPlayer15);
			  GameJLabel boardPlayer16 = new GameJLabel(iconPlayer16);
		
			  player.playerUI.rand10_1.add(boardPlayer1, new Integer(0));
		     player.playerUI.rand10_2.add(boardPlayer2, new Integer(0));
		     player.playerUI.rand10_3.add(boardPlayer3, new Integer(0));
		     player.playerUI.rand10_4.add(boardPlayer4, new Integer(0));
		     player.playerUI.rand10_5.add(boardPlayer5, new Integer(0));
		     player.playerUI.rand10_6.add(boardPlayer6, new Integer(0));
		     player.playerUI.rand10_7.add(boardPlayer7, new Integer(0));
		     player.playerUI.rand10_8.add(boardPlayer8, new Integer(0));
		     player.playerUI.rand10_9.add(boardPlayer9, new Integer(0));
		     player.playerUI.rand10_10.add(boardPlayer10, new Integer(0));
		     player.playerUI.rand10_11.add(boardPlayer11, new Integer(0));
		     player.playerUI.rand10_12.add(boardPlayer12, new Integer(0));
		     player.playerUI.rand10_13.add(boardPlayer13, new Integer(0));
		     player.playerUI.rand10_14.add(boardPlayer14, new Integer(0));
		     player.playerUI.rand10_15.add(boardPlayer15, new Integer(0));
		     player.playerUI.rand10_16.add(boardPlayer16, new Integer(0));
		
		     boardPlayer1.setBounds(0, 0, iconPlayer1.getIconWidth(),
		                                  iconPlayer1.getIconHeight()); 
		     player.playerUI.rand10_1.setPreferredSize(new Dimension(iconPlayer1.getIconWidth(),
		                                        iconPlayer1.getIconHeight()));
		     boardPlayer2.setBounds(0, 0, iconPlayer2.getIconWidth(),
		                                  iconPlayer2.getIconHeight()); 
		     player.playerUI.rand10_2.setPreferredSize(new Dimension(iconPlayer2.getIconWidth(),
		                                        iconPlayer2.getIconHeight()));
		     boardPlayer3.setBounds(0, 0, iconPlayer3.getIconWidth(),
		                                  iconPlayer3.getIconHeight()); 
		     player.playerUI.rand10_3.setPreferredSize(new Dimension(iconPlayer3.getIconWidth(),
		                                        iconPlayer3.getIconHeight()));
		     boardPlayer4.setBounds(0, 0, iconPlayer4.getIconWidth(),
		                                  iconPlayer4.getIconHeight()); 
		     player.playerUI.rand10_4.setPreferredSize(new Dimension(iconPlayer4.getIconWidth(),
		                                        iconPlayer4.getIconHeight()));
		     boardPlayer5.setBounds(0, 0, iconPlayer5.getIconWidth(),
		                                  iconPlayer5.getIconHeight()); 
		     player.playerUI.rand10_5.setPreferredSize(new Dimension(iconPlayer5.getIconWidth(),
		                                        iconPlayer5.getIconHeight()));
		     boardPlayer6.setBounds(0, 0, iconPlayer6.getIconWidth(),
		                                  iconPlayer6.getIconHeight()); 
		     player.playerUI.rand10_6.setPreferredSize(new Dimension(iconPlayer6.getIconWidth(),
		                                        iconPlayer6.getIconHeight()));
		     boardPlayer7.setBounds(0, 0, iconPlayer7.getIconWidth(),
		                                  iconPlayer7.getIconHeight()); 
		     player.playerUI.rand10_7.setPreferredSize(new Dimension(iconPlayer7.getIconWidth(),
		                                        iconPlayer7.getIconHeight()));
		     boardPlayer8.setBounds(0, 0, iconPlayer8.getIconWidth(),
		                                  iconPlayer8.getIconHeight()); 
		     player.playerUI.rand10_8.setPreferredSize(new Dimension(iconPlayer8.getIconWidth(),
		                                        iconPlayer8.getIconHeight()));
		     boardPlayer9.setBounds(0, 0, iconPlayer9.getIconWidth(),
		                                  iconPlayer9.getIconHeight()); 
		     player.playerUI.rand10_9.setPreferredSize(new Dimension(iconPlayer9.getIconWidth(),
		                                        iconPlayer9.getIconHeight()));
		     boardPlayer10.setBounds(0, 0, iconPlayer10.getIconWidth(),
		                                  iconPlayer10.getIconHeight()); 
		     player.playerUI.rand10_10.setPreferredSize(new Dimension(iconPlayer10.getIconWidth(),
		                                        iconPlayer10.getIconHeight()));													 													 
		     boardPlayer11.setBounds(0, 0, iconPlayer11.getIconWidth(),
		                                  iconPlayer11.getIconHeight()); 
		     player.playerUI.rand10_11.setPreferredSize(new Dimension(iconPlayer11.getIconWidth(),
		                                        iconPlayer11.getIconHeight()));
		     boardPlayer12.setBounds(0, 0, iconPlayer12.getIconWidth(),
		                                  iconPlayer12.getIconHeight()); 
		     player.playerUI.rand10_12.setPreferredSize(new Dimension(iconPlayer12.getIconWidth(),
		                                        iconPlayer12.getIconHeight()));
		     boardPlayer13.setBounds(0, 0, iconPlayer13.getIconWidth(),
		                                  iconPlayer13.getIconHeight()); 
		     player.playerUI.rand10_13.setPreferredSize(new Dimension(iconPlayer13.getIconWidth(),
		                                        iconPlayer13.getIconHeight()));
		     boardPlayer14.setBounds(0, 0, iconPlayer14.getIconWidth(),
		                                  iconPlayer14.getIconHeight()); 
		     player.playerUI.rand10_14.setPreferredSize(new Dimension(iconPlayer14.getIconWidth(),
		                                        iconPlayer14.getIconHeight()));
		     boardPlayer15.setBounds(0, 0, iconPlayer15.getIconWidth(),
		                                  iconPlayer15.getIconHeight()); 
		     player.playerUI.rand10_15.setPreferredSize(new Dimension(iconPlayer15.getIconWidth(),
		                                        iconPlayer15.getIconHeight()));	
		     boardPlayer16.setBounds(0, 0, iconPlayer16.getIconWidth(),
		                                  iconPlayer16.getIconHeight()); 
		     player.playerUI.rand10_16.setPreferredSize(new Dimension(iconPlayer16.getIconWidth(),
		                                        iconPlayer16.getIconHeight()));													 												 													 
		
			  player.playerUI.mypanel7.add(player.playerUI.rand10_1);
		     player.playerUI.mypanel7.add(player.playerUI.rand10_2);
		     player.playerUI.mypanel7.add(player.playerUI.rand10_3);
		     player.playerUI.mypanel7.add(player.playerUI.rand10_4);
		     player.playerUI.mypanel7.add(player.playerUI.rand10_5);
		     player.playerUI.mypanel7.add(player.playerUI.rand10_6);
		     player.playerUI.mypanel7.add(player.playerUI.rand10_7);
		     player.playerUI.mypanel7.add(player.playerUI.rand10_8);
		     player.playerUI.mypanel7.add(player.playerUI.rand10_9);
		     player.playerUI.mypanel7.add(player.playerUI.rand10_10);
		     player.playerUI.mypanel7.add(player.playerUI.rand10_11);
		     player.playerUI.mypanel7.add(player.playerUI.rand10_12);
		     player.playerUI.mypanel7.add(player.playerUI.rand10_13);
		     player.playerUI.mypanel7.add(player.playerUI.rand10_14);
		     player.playerUI.mypanel7.add(player.playerUI.rand10_15);
		     player.playerUI.mypanel7.add(player.playerUI.rand10_16);
		  
		  }
		}

      private void populateRand11(int tile, Player player)
		{
		  if(tile == 0)
		  {
			  iconPlayer1 =  (bp[0][0].getIcon());
	        iconPlayer2 =  (bp[1][0].getIcon());
 	        iconPlayer3 =  (bp[2][0].getIcon());
	        iconPlayer4 =  (bp[3][0].getIcon());
	        iconPlayer5 =  (bp[0][1].getIcon());
	        iconPlayer6 =  (bp[1][1].getIcon());
	        iconPlayer7 =  (bp[2][1].getIcon());
	        iconPlayer8 =  (bp[3][1].getIcon());
	        iconPlayer9 =  (bp[0][2].getIcon());
	        iconPlayer10 =  (bp[1][2].getIcon());
	        iconPlayer11 =  (bp[2][2].getIcon());
	        iconPlayer12 =  (bp[3][2].getIcon());
	        iconPlayer13 =  (bp[0][3].getIcon());
	        iconPlayer14 =  (bp[1][3].getIcon());
	        iconPlayer15 =  (bp[2][3].getIcon());
	        iconPlayer16 =  (bp[3][3].getIcon());

	
		//add components to board
			  GameJLabel boardPlayer1 = new GameJLabel(iconPlayer1);
			  GameJLabel boardPlayer2 = new GameJLabel(iconPlayer2);
			  GameJLabel boardPlayer3 = new GameJLabel(iconPlayer3);
			  GameJLabel boardPlayer4 = new GameJLabel(iconPlayer4);
			  GameJLabel boardPlayer5 = new GameJLabel(iconPlayer5);
			  GameJLabel boardPlayer6 = new GameJLabel(iconPlayer6);
			  GameJLabel boardPlayer7 = new GameJLabel(iconPlayer7);
			  GameJLabel boardPlayer8 = new GameJLabel(iconPlayer8);
			  GameJLabel boardPlayer9 = new GameJLabel(iconPlayer9);
			  GameJLabel boardPlayer10 = new GameJLabel(iconPlayer10);
			  GameJLabel boardPlayer11 = new GameJLabel(iconPlayer11);
			  GameJLabel boardPlayer12 = new GameJLabel(iconPlayer12);
			  GameJLabel boardPlayer13 = new GameJLabel(iconPlayer13);
			  GameJLabel boardPlayer14 = new GameJLabel(iconPlayer14);
			  GameJLabel boardPlayer15 = new GameJLabel(iconPlayer15);
			  GameJLabel boardPlayer16 = new GameJLabel(iconPlayer16);
		
			  player.playerUI.rand11_1.add(boardPlayer1, new Integer(0));
		     player.playerUI.rand11_2.add(boardPlayer2, new Integer(0));
		     player.playerUI.rand11_3.add(boardPlayer3, new Integer(0));
		     player.playerUI.rand11_4.add(boardPlayer4, new Integer(0));
		     player.playerUI.rand11_5.add(boardPlayer5, new Integer(0));
		     player.playerUI.rand11_6.add(boardPlayer6, new Integer(0));
		     player.playerUI.rand11_7.add(boardPlayer7, new Integer(0));
		     player.playerUI.rand11_8.add(boardPlayer8, new Integer(0));
		     player.playerUI.rand11_9.add(boardPlayer9, new Integer(0));
		     player.playerUI.rand11_10.add(boardPlayer10, new Integer(0));
		     player.playerUI.rand11_11.add(boardPlayer11, new Integer(0));
		     player.playerUI.rand11_12.add(boardPlayer12, new Integer(0));
		     player.playerUI.rand11_13.add(boardPlayer13, new Integer(0));
		     player.playerUI.rand11_14.add(boardPlayer14, new Integer(0));
		     player.playerUI.rand11_15.add(boardPlayer15, new Integer(0));
		     player.playerUI.rand11_16.add(boardPlayer16, new Integer(0));
		
		     boardPlayer1.setBounds(0, 0, iconPlayer1.getIconWidth(),
		                                  iconPlayer1.getIconHeight()); 
		     player.playerUI.rand11_1.setPreferredSize(new Dimension(iconPlayer1.getIconWidth(),
		                                        iconPlayer1.getIconHeight()));
		     boardPlayer2.setBounds(0, 0, iconPlayer2.getIconWidth(),
		                                  iconPlayer2.getIconHeight()); 
		     player.playerUI.rand11_2.setPreferredSize(new Dimension(iconPlayer2.getIconWidth(),
		                                        iconPlayer2.getIconHeight()));
		     boardPlayer3.setBounds(0, 0, iconPlayer3.getIconWidth(),
		                                  iconPlayer3.getIconHeight()); 
		     player.playerUI.rand11_3.setPreferredSize(new Dimension(iconPlayer3.getIconWidth(),
		                                        iconPlayer3.getIconHeight()));
		     boardPlayer4.setBounds(0, 0, iconPlayer4.getIconWidth(),
		                                  iconPlayer4.getIconHeight()); 
		     player.playerUI.rand11_4.setPreferredSize(new Dimension(iconPlayer4.getIconWidth(),
		                                        iconPlayer4.getIconHeight()));
		     boardPlayer5.setBounds(0, 0, iconPlayer5.getIconWidth(),
		                                  iconPlayer5.getIconHeight()); 
		     player.playerUI.rand11_5.setPreferredSize(new Dimension(iconPlayer5.getIconWidth(),
		                                        iconPlayer5.getIconHeight()));
		     boardPlayer6.setBounds(0, 0, iconPlayer6.getIconWidth(),
		                                  iconPlayer6.getIconHeight()); 
		     player.playerUI.rand11_6.setPreferredSize(new Dimension(iconPlayer6.getIconWidth(),
		                                        iconPlayer6.getIconHeight()));
		     boardPlayer7.setBounds(0, 0, iconPlayer7.getIconWidth(),
		                                  iconPlayer7.getIconHeight()); 
		     player.playerUI.rand11_7.setPreferredSize(new Dimension(iconPlayer7.getIconWidth(),
		                                        iconPlayer7.getIconHeight()));
		     boardPlayer8.setBounds(0, 0, iconPlayer8.getIconWidth(),
		                                  iconPlayer8.getIconHeight()); 
		     player.playerUI.rand11_8.setPreferredSize(new Dimension(iconPlayer8.getIconWidth(),
		                                        iconPlayer8.getIconHeight()));
		     boardPlayer9.setBounds(0, 0, iconPlayer9.getIconWidth(),
		                                  iconPlayer9.getIconHeight()); 
		     player.playerUI.rand11_9.setPreferredSize(new Dimension(iconPlayer9.getIconWidth(),
		                                        iconPlayer9.getIconHeight()));
		     boardPlayer10.setBounds(0, 0, iconPlayer10.getIconWidth(),
		                                  iconPlayer10.getIconHeight()); 
		     player.playerUI.rand11_10.setPreferredSize(new Dimension(iconPlayer10.getIconWidth(),
		                                        iconPlayer10.getIconHeight()));													 													 
		     boardPlayer11.setBounds(0, 0, iconPlayer11.getIconWidth(),
		                                  iconPlayer11.getIconHeight()); 
		     player.playerUI.rand11_11.setPreferredSize(new Dimension(iconPlayer11.getIconWidth(),
		                                        iconPlayer11.getIconHeight()));
		     boardPlayer12.setBounds(0, 0, iconPlayer12.getIconWidth(),
		                                  iconPlayer12.getIconHeight()); 
		     player.playerUI.rand11_12.setPreferredSize(new Dimension(iconPlayer12.getIconWidth(),
		                                        iconPlayer12.getIconHeight()));
		     boardPlayer13.setBounds(0, 0, iconPlayer13.getIconWidth(),
		                                  iconPlayer13.getIconHeight()); 
		     player.playerUI.rand11_13.setPreferredSize(new Dimension(iconPlayer13.getIconWidth(),
		                                        iconPlayer13.getIconHeight()));
		     boardPlayer14.setBounds(0, 0, iconPlayer14.getIconWidth(),
		                                  iconPlayer14.getIconHeight()); 
		     player.playerUI.rand11_14.setPreferredSize(new Dimension(iconPlayer14.getIconWidth(),
		                                        iconPlayer14.getIconHeight()));
		     boardPlayer15.setBounds(0, 0, iconPlayer15.getIconWidth(),
		                                  iconPlayer15.getIconHeight()); 
		     player.playerUI.rand11_15.setPreferredSize(new Dimension(iconPlayer15.getIconWidth(),
		                                        iconPlayer15.getIconHeight()));	
		     boardPlayer16.setBounds(0, 0, iconPlayer16.getIconWidth(),
		                                  iconPlayer16.getIconHeight()); 
		     player.playerUI.rand11_16.setPreferredSize(new Dimension(iconPlayer16.getIconWidth(),
		                                        iconPlayer16.getIconHeight()));													 												 													 
		
			  player.playerUI.mypanel0.add(player.playerUI.rand11_1);
		     player.playerUI.mypanel0.add(player.playerUI.rand11_2);
		     player.playerUI.mypanel0.add(player.playerUI.rand11_3);
		     player.playerUI.mypanel0.add(player.playerUI.rand11_4);
		     player.playerUI.mypanel0.add(player.playerUI.rand11_5);
		     player.playerUI.mypanel0.add(player.playerUI.rand11_6);
		     player.playerUI.mypanel0.add(player.playerUI.rand11_7);
		     player.playerUI.mypanel0.add(player.playerUI.rand11_8);
		     player.playerUI.mypanel0.add(player.playerUI.rand11_9);
		     player.playerUI.mypanel0.add(player.playerUI.rand11_10);
		     player.playerUI.mypanel0.add(player.playerUI.rand11_11);
		     player.playerUI.mypanel0.add(player.playerUI.rand11_12);
		     player.playerUI.mypanel0.add(player.playerUI.rand11_13);
		     player.playerUI.mypanel0.add(player.playerUI.rand11_14);
		     player.playerUI.mypanel0.add(player.playerUI.rand11_15);
		     player.playerUI.mypanel0.add(player.playerUI.rand11_16);
		  }
		  else if(tile == 1)
		  {
  	        iconPlayer1 =  (bp[0][4].getIcon());
	        iconPlayer2 =  (bp[1][4].getIcon());
 	        iconPlayer3 =  (bp[2][4].getIcon());
	        iconPlayer4 =  (bp[3][4].getIcon());
	        iconPlayer5 =  (bp[0][5].getIcon());
	        iconPlayer6 =  (bp[1][5].getIcon());
	        iconPlayer7 =  (bp[2][5].getIcon());
	        iconPlayer8 =  (bp[3][5].getIcon());
	        iconPlayer9 =  (bp[0][6].getIcon());
	        iconPlayer10 =  (bp[1][6].getIcon());
	        iconPlayer11 =  (bp[2][6].getIcon());
	        iconPlayer12 =  (bp[3][6].getIcon());
	        iconPlayer13 =  (bp[0][7].getIcon());
	        iconPlayer14 =  (bp[1][7].getIcon());
	        iconPlayer15 =  (bp[2][7].getIcon());
	        iconPlayer16 =  (bp[3][7].getIcon());

	
		//add components to board
			  GameJLabel boardPlayer1 = new GameJLabel(iconPlayer1);
			  GameJLabel boardPlayer2 = new GameJLabel(iconPlayer2);
			  GameJLabel boardPlayer3 = new GameJLabel(iconPlayer3);
			  GameJLabel boardPlayer4 = new GameJLabel(iconPlayer4);
			  GameJLabel boardPlayer5 = new GameJLabel(iconPlayer5);
			  GameJLabel boardPlayer6 = new GameJLabel(iconPlayer6);
			  GameJLabel boardPlayer7 = new GameJLabel(iconPlayer7);
			  GameJLabel boardPlayer8 = new GameJLabel(iconPlayer8);
			  GameJLabel boardPlayer9 = new GameJLabel(iconPlayer9);
			  GameJLabel boardPlayer10 = new GameJLabel(iconPlayer10);
			  GameJLabel boardPlayer11 = new GameJLabel(iconPlayer11);
			  GameJLabel boardPlayer12 = new GameJLabel(iconPlayer12);
			  GameJLabel boardPlayer13 = new GameJLabel(iconPlayer13);
			  GameJLabel boardPlayer14 = new GameJLabel(iconPlayer14);
			  GameJLabel boardPlayer15 = new GameJLabel(iconPlayer15);
			  GameJLabel boardPlayer16 = new GameJLabel(iconPlayer16);
		
			  player.playerUI.rand11_1.add(boardPlayer1, new Integer(0));
		     player.playerUI.rand11_2.add(boardPlayer2, new Integer(0));
		     player.playerUI.rand11_3.add(boardPlayer3, new Integer(0));
		     player.playerUI.rand11_4.add(boardPlayer4, new Integer(0));
		     player.playerUI.rand11_5.add(boardPlayer5, new Integer(0));
		     player.playerUI.rand11_6.add(boardPlayer6, new Integer(0));
		     player.playerUI.rand11_7.add(boardPlayer7, new Integer(0));
		     player.playerUI.rand11_8.add(boardPlayer8, new Integer(0));
		     player.playerUI.rand11_9.add(boardPlayer9, new Integer(0));
		     player.playerUI.rand11_10.add(boardPlayer10, new Integer(0));
		     player.playerUI.rand11_11.add(boardPlayer11, new Integer(0));
		     player.playerUI.rand11_12.add(boardPlayer12, new Integer(0));
		     player.playerUI.rand11_13.add(boardPlayer13, new Integer(0));
		     player.playerUI.rand11_14.add(boardPlayer14, new Integer(0));
		     player.playerUI.rand11_15.add(boardPlayer15, new Integer(0));
		     player.playerUI.rand11_16.add(boardPlayer16, new Integer(0));
		
		     boardPlayer1.setBounds(0, 0, iconPlayer1.getIconWidth(),
		                                  iconPlayer1.getIconHeight()); 
		     player.playerUI.rand11_1.setPreferredSize(new Dimension(iconPlayer1.getIconWidth(),
		                                        iconPlayer1.getIconHeight()));
		     boardPlayer2.setBounds(0, 0, iconPlayer2.getIconWidth(),
		                                  iconPlayer2.getIconHeight()); 
		     player.playerUI.rand11_2.setPreferredSize(new Dimension(iconPlayer2.getIconWidth(),
		                                        iconPlayer2.getIconHeight()));
		     boardPlayer3.setBounds(0, 0, iconPlayer3.getIconWidth(),
		                                  iconPlayer3.getIconHeight()); 
		     player.playerUI.rand11_3.setPreferredSize(new Dimension(iconPlayer3.getIconWidth(),
		                                        iconPlayer3.getIconHeight()));
		     boardPlayer4.setBounds(0, 0, iconPlayer4.getIconWidth(),
		                                  iconPlayer4.getIconHeight()); 
		     player.playerUI.rand11_4.setPreferredSize(new Dimension(iconPlayer4.getIconWidth(),
		                                        iconPlayer4.getIconHeight()));
		     boardPlayer5.setBounds(0, 0, iconPlayer5.getIconWidth(),
		                                  iconPlayer5.getIconHeight()); 
		     player.playerUI.rand11_5.setPreferredSize(new Dimension(iconPlayer5.getIconWidth(),
		                                        iconPlayer5.getIconHeight()));
		     boardPlayer6.setBounds(0, 0, iconPlayer6.getIconWidth(),
		                                  iconPlayer6.getIconHeight()); 
		     player.playerUI.rand11_6.setPreferredSize(new Dimension(iconPlayer6.getIconWidth(),
		                                        iconPlayer6.getIconHeight()));
		     boardPlayer7.setBounds(0, 0, iconPlayer7.getIconWidth(),
		                                  iconPlayer7.getIconHeight()); 
		     player.playerUI.rand11_7.setPreferredSize(new Dimension(iconPlayer7.getIconWidth(),
		                                        iconPlayer7.getIconHeight()));
		     boardPlayer8.setBounds(0, 0, iconPlayer8.getIconWidth(),
		                                  iconPlayer8.getIconHeight()); 
		     player.playerUI.rand11_8.setPreferredSize(new Dimension(iconPlayer8.getIconWidth(),
		                                        iconPlayer8.getIconHeight()));
		     boardPlayer9.setBounds(0, 0, iconPlayer9.getIconWidth(),
		                                  iconPlayer9.getIconHeight()); 
		     player.playerUI.rand11_9.setPreferredSize(new Dimension(iconPlayer9.getIconWidth(),
		                                        iconPlayer9.getIconHeight()));
		     boardPlayer10.setBounds(0, 0, iconPlayer10.getIconWidth(),
		                                  iconPlayer10.getIconHeight()); 
		     player.playerUI.rand11_10.setPreferredSize(new Dimension(iconPlayer10.getIconWidth(),
		                                        iconPlayer10.getIconHeight()));													 													 
		     boardPlayer11.setBounds(0, 0, iconPlayer11.getIconWidth(),
		                                  iconPlayer11.getIconHeight()); 
		     player.playerUI.rand11_11.setPreferredSize(new Dimension(iconPlayer11.getIconWidth(),
		                                        iconPlayer11.getIconHeight()));
		     boardPlayer12.setBounds(0, 0, iconPlayer12.getIconWidth(),
		                                  iconPlayer12.getIconHeight()); 
		     player.playerUI.rand11_12.setPreferredSize(new Dimension(iconPlayer12.getIconWidth(),
		                                        iconPlayer12.getIconHeight()));
		     boardPlayer13.setBounds(0, 0, iconPlayer13.getIconWidth(),
		                                  iconPlayer13.getIconHeight()); 
		     player.playerUI.rand11_13.setPreferredSize(new Dimension(iconPlayer13.getIconWidth(),
		                                        iconPlayer13.getIconHeight()));
		     boardPlayer14.setBounds(0, 0, iconPlayer14.getIconWidth(),
		                                  iconPlayer14.getIconHeight()); 
		     player.playerUI.rand11_14.setPreferredSize(new Dimension(iconPlayer14.getIconWidth(),
		                                        iconPlayer14.getIconHeight()));
		     boardPlayer15.setBounds(0, 0, iconPlayer15.getIconWidth(),
		                                  iconPlayer15.getIconHeight()); 
		     player.playerUI.rand11_15.setPreferredSize(new Dimension(iconPlayer15.getIconWidth(),
		                                        iconPlayer15.getIconHeight()));	
		     boardPlayer16.setBounds(0, 0, iconPlayer16.getIconWidth(),
		                                  iconPlayer16.getIconHeight()); 
		     player.playerUI.rand11_16.setPreferredSize(new Dimension(iconPlayer16.getIconWidth(),
		                                        iconPlayer16.getIconHeight()));													 												 													 
		
			  player.playerUI.mypanel1.add(player.playerUI.rand11_1);
		     player.playerUI.mypanel1.add(player.playerUI.rand11_2);
		     player.playerUI.mypanel1.add(player.playerUI.rand11_3);
		     player.playerUI.mypanel1.add(player.playerUI.rand11_4);
		     player.playerUI.mypanel1.add(player.playerUI.rand11_5);
		     player.playerUI.mypanel1.add(player.playerUI.rand11_6);
		     player.playerUI.mypanel1.add(player.playerUI.rand11_7);
		     player.playerUI.mypanel1.add(player.playerUI.rand11_8);
		     player.playerUI.mypanel1.add(player.playerUI.rand11_9);
		     player.playerUI.mypanel1.add(player.playerUI.rand11_10);
		     player.playerUI.mypanel1.add(player.playerUI.rand11_11);
		     player.playerUI.mypanel1.add(player.playerUI.rand11_12);
		     player.playerUI.mypanel1.add(player.playerUI.rand11_13);
		     player.playerUI.mypanel1.add(player.playerUI.rand11_14);
		     player.playerUI.mypanel1.add(player.playerUI.rand11_15);
		     player.playerUI.mypanel1.add(player.playerUI.rand11_16);
		  
		  }
		  else if(tile == 2)
		  {
  	        iconPlayer1 =  (bp[0][8].getIcon());
	        iconPlayer2 =  (bp[1][8].getIcon());
 	        iconPlayer3 =  (bp[2][8].getIcon());
	        iconPlayer4 =  (bp[3][8].getIcon());
	        iconPlayer5 =  (bp[0][9].getIcon());
	        iconPlayer6 =  (bp[1][9].getIcon());
	        iconPlayer7 =  (bp[2][9].getIcon());
	        iconPlayer8 =  (bp[3][9].getIcon());
	        iconPlayer9 =  (bp[0][10].getIcon());
	        iconPlayer10 =  (bp[1][10].getIcon());
	        iconPlayer11 =  (bp[2][10].getIcon());
	        iconPlayer12 =  (bp[3][10].getIcon());
	        iconPlayer13 =  (bp[0][11].getIcon());
	        iconPlayer14 =  (bp[1][11].getIcon());
	        iconPlayer15 =  (bp[2][11].getIcon());
	        iconPlayer16 =  (bp[3][11].getIcon());

	
		//add components to board
			  GameJLabel boardPlayer1 = new GameJLabel(iconPlayer1);
			  GameJLabel boardPlayer2 = new GameJLabel(iconPlayer2);
			  GameJLabel boardPlayer3 = new GameJLabel(iconPlayer3);
			  GameJLabel boardPlayer4 = new GameJLabel(iconPlayer4);
			  GameJLabel boardPlayer5 = new GameJLabel(iconPlayer5);
			  GameJLabel boardPlayer6 = new GameJLabel(iconPlayer6);
			  GameJLabel boardPlayer7 = new GameJLabel(iconPlayer7);
			  GameJLabel boardPlayer8 = new GameJLabel(iconPlayer8);
			  GameJLabel boardPlayer9 = new GameJLabel(iconPlayer9);
			  GameJLabel boardPlayer10 = new GameJLabel(iconPlayer10);
			  GameJLabel boardPlayer11 = new GameJLabel(iconPlayer11);
			  GameJLabel boardPlayer12 = new GameJLabel(iconPlayer12);
			  GameJLabel boardPlayer13 = new GameJLabel(iconPlayer13);
			  GameJLabel boardPlayer14 = new GameJLabel(iconPlayer14);
			  GameJLabel boardPlayer15 = new GameJLabel(iconPlayer15);
			  GameJLabel boardPlayer16 = new GameJLabel(iconPlayer16);
		
			  player.playerUI.rand11_1.add(boardPlayer1, new Integer(0));
		     player.playerUI.rand11_2.add(boardPlayer2, new Integer(0));
		     player.playerUI.rand11_3.add(boardPlayer3, new Integer(0));
		     player.playerUI.rand11_4.add(boardPlayer4, new Integer(0));
		     player.playerUI.rand11_5.add(boardPlayer5, new Integer(0));
		     player.playerUI.rand11_6.add(boardPlayer6, new Integer(0));
		     player.playerUI.rand11_7.add(boardPlayer7, new Integer(0));
		     player.playerUI.rand11_8.add(boardPlayer8, new Integer(0));
		     player.playerUI.rand11_9.add(boardPlayer9, new Integer(0));
		     player.playerUI.rand11_10.add(boardPlayer10, new Integer(0));
		     player.playerUI.rand11_11.add(boardPlayer11, new Integer(0));
		     player.playerUI.rand11_12.add(boardPlayer12, new Integer(0));
		     player.playerUI.rand11_13.add(boardPlayer13, new Integer(0));
		     player.playerUI.rand11_14.add(boardPlayer14, new Integer(0));
		     player.playerUI.rand11_15.add(boardPlayer15, new Integer(0));
		     player.playerUI.rand11_16.add(boardPlayer16, new Integer(0));
		
		     boardPlayer1.setBounds(0, 0, iconPlayer1.getIconWidth(),
		                                  iconPlayer1.getIconHeight()); 
		     player.playerUI.rand11_1.setPreferredSize(new Dimension(iconPlayer1.getIconWidth(),
		                                        iconPlayer1.getIconHeight()));
		     boardPlayer2.setBounds(0, 0, iconPlayer2.getIconWidth(),
		                                  iconPlayer2.getIconHeight()); 
		     player.playerUI.rand11_2.setPreferredSize(new Dimension(iconPlayer2.getIconWidth(),
		                                        iconPlayer2.getIconHeight()));
		     boardPlayer3.setBounds(0, 0, iconPlayer3.getIconWidth(),
		                                  iconPlayer3.getIconHeight()); 
		     player.playerUI.rand11_3.setPreferredSize(new Dimension(iconPlayer3.getIconWidth(),
		                                        iconPlayer3.getIconHeight()));
		     boardPlayer4.setBounds(0, 0, iconPlayer4.getIconWidth(),
		                                  iconPlayer4.getIconHeight()); 
		     player.playerUI.rand11_4.setPreferredSize(new Dimension(iconPlayer4.getIconWidth(),
		                                        iconPlayer4.getIconHeight()));
		     boardPlayer5.setBounds(0, 0, iconPlayer5.getIconWidth(),
		                                  iconPlayer5.getIconHeight()); 
		     player.playerUI.rand11_5.setPreferredSize(new Dimension(iconPlayer5.getIconWidth(),
		                                        iconPlayer5.getIconHeight()));
		     boardPlayer6.setBounds(0, 0, iconPlayer6.getIconWidth(),
		                                  iconPlayer6.getIconHeight()); 
		     player.playerUI.rand11_6.setPreferredSize(new Dimension(iconPlayer6.getIconWidth(),
		                                        iconPlayer6.getIconHeight()));
		     boardPlayer7.setBounds(0, 0, iconPlayer7.getIconWidth(),
		                                  iconPlayer7.getIconHeight()); 
		     player.playerUI.rand11_7.setPreferredSize(new Dimension(iconPlayer7.getIconWidth(),
		                                        iconPlayer7.getIconHeight()));
		     boardPlayer8.setBounds(0, 0, iconPlayer8.getIconWidth(),
		                                  iconPlayer8.getIconHeight()); 
		     player.playerUI.rand11_8.setPreferredSize(new Dimension(iconPlayer8.getIconWidth(),
		                                        iconPlayer8.getIconHeight()));
		     boardPlayer9.setBounds(0, 0, iconPlayer9.getIconWidth(),
		                                  iconPlayer9.getIconHeight()); 
		     player.playerUI.rand11_9.setPreferredSize(new Dimension(iconPlayer9.getIconWidth(),
		                                        iconPlayer9.getIconHeight()));
		     boardPlayer10.setBounds(0, 0, iconPlayer10.getIconWidth(),
		                                  iconPlayer10.getIconHeight()); 
		     player.playerUI.rand11_10.setPreferredSize(new Dimension(iconPlayer10.getIconWidth(),
		                                        iconPlayer10.getIconHeight()));													 													 
		     boardPlayer11.setBounds(0, 0, iconPlayer11.getIconWidth(),
		                                  iconPlayer11.getIconHeight()); 
		     player.playerUI.rand11_11.setPreferredSize(new Dimension(iconPlayer11.getIconWidth(),
		                                        iconPlayer11.getIconHeight()));
		     boardPlayer12.setBounds(0, 0, iconPlayer12.getIconWidth(),
		                                  iconPlayer12.getIconHeight()); 
		     player.playerUI.rand11_12.setPreferredSize(new Dimension(iconPlayer12.getIconWidth(),
		                                        iconPlayer12.getIconHeight()));
		     boardPlayer13.setBounds(0, 0, iconPlayer13.getIconWidth(),
		                                  iconPlayer13.getIconHeight()); 
		     player.playerUI.rand11_13.setPreferredSize(new Dimension(iconPlayer13.getIconWidth(),
		                                        iconPlayer13.getIconHeight()));
		     boardPlayer14.setBounds(0, 0, iconPlayer14.getIconWidth(),
		                                  iconPlayer14.getIconHeight()); 
		     player.playerUI.rand11_14.setPreferredSize(new Dimension(iconPlayer14.getIconWidth(),
		                                        iconPlayer14.getIconHeight()));
		     boardPlayer15.setBounds(0, 0, iconPlayer15.getIconWidth(),
		                                  iconPlayer15.getIconHeight()); 
		     player.playerUI.rand11_15.setPreferredSize(new Dimension(iconPlayer15.getIconWidth(),
		                                        iconPlayer15.getIconHeight()));	
		     boardPlayer16.setBounds(0, 0, iconPlayer16.getIconWidth(),
		                                  iconPlayer16.getIconHeight()); 
		     player.playerUI.rand11_16.setPreferredSize(new Dimension(iconPlayer16.getIconWidth(),
		                                        iconPlayer16.getIconHeight()));													 												 													 
		
			  player.playerUI.mypanel2.add(player.playerUI.rand11_1);
		     player.playerUI.mypanel2.add(player.playerUI.rand11_2);
		     player.playerUI.mypanel2.add(player.playerUI.rand11_3);
		     player.playerUI.mypanel2.add(player.playerUI.rand11_4);
		     player.playerUI.mypanel2.add(player.playerUI.rand11_5);
		     player.playerUI.mypanel2.add(player.playerUI.rand11_6);
		     player.playerUI.mypanel2.add(player.playerUI.rand11_7);
		     player.playerUI.mypanel2.add(player.playerUI.rand11_8);
		     player.playerUI.mypanel2.add(player.playerUI.rand11_9);
		     player.playerUI.mypanel2.add(player.playerUI.rand11_10);
		     player.playerUI.mypanel2.add(player.playerUI.rand11_11);
		     player.playerUI.mypanel2.add(player.playerUI.rand11_12);
		     player.playerUI.mypanel2.add(player.playerUI.rand11_13);
		     player.playerUI.mypanel2.add(player.playerUI.rand11_14);
		     player.playerUI.mypanel2.add(player.playerUI.rand11_15);
		     player.playerUI.mypanel2.add(player.playerUI.rand11_16);
		  
		  }
		  else if(tile == 5)
		  {
  	        iconPlayer1 =  (bp[4][4].getIcon());
	        iconPlayer2 =  (bp[5][4].getIcon());
 	        iconPlayer3 =  (bp[6][4].getIcon());
	        iconPlayer4 =  (bp[7][4].getIcon());
	        iconPlayer5 =  (bp[4][5].getIcon());
	        iconPlayer6 =  (bp[5][5].getIcon());
	        iconPlayer7 =  (bp[6][5].getIcon());
	        iconPlayer8 =  (bp[7][5].getIcon());
	        iconPlayer9 =  (bp[4][6].getIcon());
	        iconPlayer10 =  (bp[5][6].getIcon());
	        iconPlayer11 =  (bp[6][6].getIcon());
	        iconPlayer12 =  (bp[7][6].getIcon());
	        iconPlayer13 =  (bp[4][7].getIcon());
	        iconPlayer14 =  (bp[5][7].getIcon());
	        iconPlayer15 =  (bp[6][7].getIcon());
	        iconPlayer16 =  (bp[7][7].getIcon());

	
		//add components to board
			  GameJLabel boardPlayer1 = new GameJLabel(iconPlayer1);
			  GameJLabel boardPlayer2 = new GameJLabel(iconPlayer2);
			  GameJLabel boardPlayer3 = new GameJLabel(iconPlayer3);
			  GameJLabel boardPlayer4 = new GameJLabel(iconPlayer4);
			  GameJLabel boardPlayer5 = new GameJLabel(iconPlayer5);
			  GameJLabel boardPlayer6 = new GameJLabel(iconPlayer6);
			  GameJLabel boardPlayer7 = new GameJLabel(iconPlayer7);
			  GameJLabel boardPlayer8 = new GameJLabel(iconPlayer8);
			  GameJLabel boardPlayer9 = new GameJLabel(iconPlayer9);
			  GameJLabel boardPlayer10 = new GameJLabel(iconPlayer10);
			  GameJLabel boardPlayer11 = new GameJLabel(iconPlayer11);
			  GameJLabel boardPlayer12 = new GameJLabel(iconPlayer12);
			  GameJLabel boardPlayer13 = new GameJLabel(iconPlayer13);
			  GameJLabel boardPlayer14 = new GameJLabel(iconPlayer14);
			  GameJLabel boardPlayer15 = new GameJLabel(iconPlayer15);
			  GameJLabel boardPlayer16 = new GameJLabel(iconPlayer16);
		
			  player.playerUI.rand11_1.add(boardPlayer1, new Integer(0));
		     player.playerUI.rand11_2.add(boardPlayer2, new Integer(0));
		     player.playerUI.rand11_3.add(boardPlayer3, new Integer(0));
		     player.playerUI.rand11_4.add(boardPlayer4, new Integer(0));
		     player.playerUI.rand11_5.add(boardPlayer5, new Integer(0));
		     player.playerUI.rand11_6.add(boardPlayer6, new Integer(0));
		     player.playerUI.rand11_7.add(boardPlayer7, new Integer(0));
		     player.playerUI.rand11_8.add(boardPlayer8, new Integer(0));
		     player.playerUI.rand11_9.add(boardPlayer9, new Integer(0));
		     player.playerUI.rand11_10.add(boardPlayer10, new Integer(0));
		     player.playerUI.rand11_11.add(boardPlayer11, new Integer(0));
		     player.playerUI.rand11_12.add(boardPlayer12, new Integer(0));
		     player.playerUI.rand11_13.add(boardPlayer13, new Integer(0));
		     player.playerUI.rand11_14.add(boardPlayer14, new Integer(0));
		     player.playerUI.rand11_15.add(boardPlayer15, new Integer(0));
		     player.playerUI.rand11_16.add(boardPlayer16, new Integer(0));
		
		     boardPlayer1.setBounds(0, 0, iconPlayer1.getIconWidth(),
		                                  iconPlayer1.getIconHeight()); 
		     player.playerUI.rand11_1.setPreferredSize(new Dimension(iconPlayer1.getIconWidth(),
		                                        iconPlayer1.getIconHeight()));
		     boardPlayer2.setBounds(0, 0, iconPlayer2.getIconWidth(),
		                                  iconPlayer2.getIconHeight()); 
		     player.playerUI.rand11_2.setPreferredSize(new Dimension(iconPlayer2.getIconWidth(),
		                                        iconPlayer2.getIconHeight()));
		     boardPlayer3.setBounds(0, 0, iconPlayer3.getIconWidth(),
		                                  iconPlayer3.getIconHeight()); 
		     player.playerUI.rand11_3.setPreferredSize(new Dimension(iconPlayer3.getIconWidth(),
		                                        iconPlayer3.getIconHeight()));
		     boardPlayer4.setBounds(0, 0, iconPlayer4.getIconWidth(),
		                                  iconPlayer4.getIconHeight()); 
		     player.playerUI.rand11_4.setPreferredSize(new Dimension(iconPlayer4.getIconWidth(),
		                                        iconPlayer4.getIconHeight()));
		     boardPlayer5.setBounds(0, 0, iconPlayer5.getIconWidth(),
		                                  iconPlayer5.getIconHeight()); 
		     player.playerUI.rand11_5.setPreferredSize(new Dimension(iconPlayer5.getIconWidth(),
		                                        iconPlayer5.getIconHeight()));
		     boardPlayer6.setBounds(0, 0, iconPlayer6.getIconWidth(),
		                                  iconPlayer6.getIconHeight()); 
		     player.playerUI.rand11_6.setPreferredSize(new Dimension(iconPlayer6.getIconWidth(),
		                                        iconPlayer6.getIconHeight()));
		     boardPlayer7.setBounds(0, 0, iconPlayer7.getIconWidth(),
		                                  iconPlayer7.getIconHeight()); 
		     player.playerUI.rand11_7.setPreferredSize(new Dimension(iconPlayer7.getIconWidth(),
		                                        iconPlayer7.getIconHeight()));
		     boardPlayer8.setBounds(0, 0, iconPlayer8.getIconWidth(),
		                                  iconPlayer8.getIconHeight()); 
		     player.playerUI.rand11_8.setPreferredSize(new Dimension(iconPlayer8.getIconWidth(),
		                                        iconPlayer8.getIconHeight()));
		     boardPlayer9.setBounds(0, 0, iconPlayer9.getIconWidth(),
		                                  iconPlayer9.getIconHeight()); 
		     player.playerUI.rand11_9.setPreferredSize(new Dimension(iconPlayer9.getIconWidth(),
		                                        iconPlayer9.getIconHeight()));
		     boardPlayer10.setBounds(0, 0, iconPlayer10.getIconWidth(),
		                                  iconPlayer10.getIconHeight()); 
		     player.playerUI.rand11_10.setPreferredSize(new Dimension(iconPlayer10.getIconWidth(),
		                                        iconPlayer10.getIconHeight()));													 													 
		     boardPlayer11.setBounds(0, 0, iconPlayer11.getIconWidth(),
		                                  iconPlayer11.getIconHeight()); 
		     player.playerUI.rand11_11.setPreferredSize(new Dimension(iconPlayer11.getIconWidth(),
		                                        iconPlayer11.getIconHeight()));
		     boardPlayer12.setBounds(0, 0, iconPlayer12.getIconWidth(),
		                                  iconPlayer12.getIconHeight()); 
		     player.playerUI.rand11_12.setPreferredSize(new Dimension(iconPlayer12.getIconWidth(),
		                                        iconPlayer12.getIconHeight()));
		     boardPlayer13.setBounds(0, 0, iconPlayer13.getIconWidth(),
		                                  iconPlayer13.getIconHeight()); 
		     player.playerUI.rand11_13.setPreferredSize(new Dimension(iconPlayer13.getIconWidth(),
		                                        iconPlayer13.getIconHeight()));
		     boardPlayer14.setBounds(0, 0, iconPlayer14.getIconWidth(),
		                                  iconPlayer14.getIconHeight()); 
		     player.playerUI.rand11_14.setPreferredSize(new Dimension(iconPlayer14.getIconWidth(),
		                                        iconPlayer14.getIconHeight()));
		     boardPlayer15.setBounds(0, 0, iconPlayer15.getIconWidth(),
		                                  iconPlayer15.getIconHeight()); 
		     player.playerUI.rand11_15.setPreferredSize(new Dimension(iconPlayer15.getIconWidth(),
		                                        iconPlayer15.getIconHeight()));	
		     boardPlayer16.setBounds(0, 0, iconPlayer16.getIconWidth(),
		                                  iconPlayer16.getIconHeight()); 
		     player.playerUI.rand11_16.setPreferredSize(new Dimension(iconPlayer16.getIconWidth(),
		                                        iconPlayer16.getIconHeight()));													 												 													 
		
			  player.playerUI.mypanel5.add(player.playerUI.rand11_1);
		     player.playerUI.mypanel5.add(player.playerUI.rand11_2);
		     player.playerUI.mypanel5.add(player.playerUI.rand11_3);
		     player.playerUI.mypanel5.add(player.playerUI.rand11_4);
		     player.playerUI.mypanel5.add(player.playerUI.rand11_5);
		     player.playerUI.mypanel5.add(player.playerUI.rand11_6);
		     player.playerUI.mypanel5.add(player.playerUI.rand11_7);
		     player.playerUI.mypanel5.add(player.playerUI.rand11_8);
		     player.playerUI.mypanel5.add(player.playerUI.rand11_9);
		     player.playerUI.mypanel5.add(player.playerUI.rand11_10);
		     player.playerUI.mypanel5.add(player.playerUI.rand11_11);
		     player.playerUI.mypanel5.add(player.playerUI.rand11_12);
		     player.playerUI.mypanel5.add(player.playerUI.rand11_13);
		     player.playerUI.mypanel5.add(player.playerUI.rand11_14);
		     player.playerUI.mypanel5.add(player.playerUI.rand11_15);
		     player.playerUI.mypanel5.add(player.playerUI.rand11_16);
		  
		  }
		  else if(tile == 6)
		  {
  	        iconPlayer1 =  (bp[4][8].getIcon());
	        iconPlayer2 =  (bp[5][8].getIcon());
 	        iconPlayer3 =  (bp[6][8].getIcon());
	        iconPlayer4 =  (bp[7][8].getIcon());
	        iconPlayer5 =  (bp[4][9].getIcon());
	        iconPlayer6 =  (bp[5][9].getIcon());
	        iconPlayer7 =  (bp[6][9].getIcon());
	        iconPlayer8 =  (bp[7][9].getIcon());
	        iconPlayer9 =  (bp[4][10].getIcon());
	        iconPlayer10 =  (bp[5][10].getIcon());
	        iconPlayer11 =  (bp[6][10].getIcon());
	        iconPlayer12 =  (bp[7][10].getIcon());
	        iconPlayer13 =  (bp[4][11].getIcon());
	        iconPlayer14 =  (bp[5][11].getIcon());
	        iconPlayer15 =  (bp[6][11].getIcon());
	        iconPlayer16 =  (bp[7][11].getIcon());

	
		//add components to board
			  GameJLabel boardPlayer1 = new GameJLabel(iconPlayer1);
			  GameJLabel boardPlayer2 = new GameJLabel(iconPlayer2);
			  GameJLabel boardPlayer3 = new GameJLabel(iconPlayer3);
			  GameJLabel boardPlayer4 = new GameJLabel(iconPlayer4);
			  GameJLabel boardPlayer5 = new GameJLabel(iconPlayer5);
			  GameJLabel boardPlayer6 = new GameJLabel(iconPlayer6);
			  GameJLabel boardPlayer7 = new GameJLabel(iconPlayer7);
			  GameJLabel boardPlayer8 = new GameJLabel(iconPlayer8);
			  GameJLabel boardPlayer9 = new GameJLabel(iconPlayer9);
			  GameJLabel boardPlayer10 = new GameJLabel(iconPlayer10);
			  GameJLabel boardPlayer11 = new GameJLabel(iconPlayer11);
			  GameJLabel boardPlayer12 = new GameJLabel(iconPlayer12);
			  GameJLabel boardPlayer13 = new GameJLabel(iconPlayer13);
			  GameJLabel boardPlayer14 = new GameJLabel(iconPlayer14);
			  GameJLabel boardPlayer15 = new GameJLabel(iconPlayer15);
			  GameJLabel boardPlayer16 = new GameJLabel(iconPlayer16);
		
			  player.playerUI.rand11_1.add(boardPlayer1, new Integer(0));
		     player.playerUI.rand11_2.add(boardPlayer2, new Integer(0));
		     player.playerUI.rand11_3.add(boardPlayer3, new Integer(0));
		     player.playerUI.rand11_4.add(boardPlayer4, new Integer(0));
		     player.playerUI.rand11_5.add(boardPlayer5, new Integer(0));
		     player.playerUI.rand11_6.add(boardPlayer6, new Integer(0));
		     player.playerUI.rand11_7.add(boardPlayer7, new Integer(0));
		     player.playerUI.rand11_8.add(boardPlayer8, new Integer(0));
		     player.playerUI.rand11_9.add(boardPlayer9, new Integer(0));
		     player.playerUI.rand11_10.add(boardPlayer10, new Integer(0));
		     player.playerUI.rand11_11.add(boardPlayer11, new Integer(0));
		     player.playerUI.rand11_12.add(boardPlayer12, new Integer(0));
		     player.playerUI.rand11_13.add(boardPlayer13, new Integer(0));
		     player.playerUI.rand11_14.add(boardPlayer14, new Integer(0));
		     player.playerUI.rand11_15.add(boardPlayer15, new Integer(0));
		     player.playerUI.rand11_16.add(boardPlayer16, new Integer(0));
		
		     boardPlayer1.setBounds(0, 0, iconPlayer1.getIconWidth(),
		                                  iconPlayer1.getIconHeight()); 
		     player.playerUI.rand11_1.setPreferredSize(new Dimension(iconPlayer1.getIconWidth(),
		                                        iconPlayer1.getIconHeight()));
		     boardPlayer2.setBounds(0, 0, iconPlayer2.getIconWidth(),
		                                  iconPlayer2.getIconHeight()); 
		     player.playerUI.rand11_2.setPreferredSize(new Dimension(iconPlayer2.getIconWidth(),
		                                        iconPlayer2.getIconHeight()));
		     boardPlayer3.setBounds(0, 0, iconPlayer3.getIconWidth(),
		                                  iconPlayer3.getIconHeight()); 
		     player.playerUI.rand11_3.setPreferredSize(new Dimension(iconPlayer3.getIconWidth(),
		                                        iconPlayer3.getIconHeight()));
		     boardPlayer4.setBounds(0, 0, iconPlayer4.getIconWidth(),
		                                  iconPlayer4.getIconHeight()); 
		     player.playerUI.rand11_4.setPreferredSize(new Dimension(iconPlayer4.getIconWidth(),
		                                        iconPlayer4.getIconHeight()));
		     boardPlayer5.setBounds(0, 0, iconPlayer5.getIconWidth(),
		                                  iconPlayer5.getIconHeight()); 
		     player.playerUI.rand11_5.setPreferredSize(new Dimension(iconPlayer5.getIconWidth(),
		                                        iconPlayer5.getIconHeight()));
		     boardPlayer6.setBounds(0, 0, iconPlayer6.getIconWidth(),
		                                  iconPlayer6.getIconHeight()); 
		     player.playerUI.rand11_6.setPreferredSize(new Dimension(iconPlayer6.getIconWidth(),
		                                        iconPlayer6.getIconHeight()));
		     boardPlayer7.setBounds(0, 0, iconPlayer7.getIconWidth(),
		                                  iconPlayer7.getIconHeight()); 
		     player.playerUI.rand11_7.setPreferredSize(new Dimension(iconPlayer7.getIconWidth(),
		                                        iconPlayer7.getIconHeight()));
		     boardPlayer8.setBounds(0, 0, iconPlayer8.getIconWidth(),
		                                  iconPlayer8.getIconHeight()); 
		     player.playerUI.rand11_8.setPreferredSize(new Dimension(iconPlayer8.getIconWidth(),
		                                        iconPlayer8.getIconHeight()));
		     boardPlayer9.setBounds(0, 0, iconPlayer9.getIconWidth(),
		                                  iconPlayer9.getIconHeight()); 
		     player.playerUI.rand11_9.setPreferredSize(new Dimension(iconPlayer9.getIconWidth(),
		                                        iconPlayer9.getIconHeight()));
		     boardPlayer10.setBounds(0, 0, iconPlayer10.getIconWidth(),
		                                  iconPlayer10.getIconHeight()); 
		     player.playerUI.rand11_10.setPreferredSize(new Dimension(iconPlayer10.getIconWidth(),
		                                        iconPlayer10.getIconHeight()));													 													 
		     boardPlayer11.setBounds(0, 0, iconPlayer11.getIconWidth(),
		                                  iconPlayer11.getIconHeight()); 
		     player.playerUI.rand11_11.setPreferredSize(new Dimension(iconPlayer11.getIconWidth(),
		                                        iconPlayer11.getIconHeight()));
		     boardPlayer12.setBounds(0, 0, iconPlayer12.getIconWidth(),
		                                  iconPlayer12.getIconHeight()); 
		     player.playerUI.rand11_12.setPreferredSize(new Dimension(iconPlayer12.getIconWidth(),
		                                        iconPlayer12.getIconHeight()));
		     boardPlayer13.setBounds(0, 0, iconPlayer13.getIconWidth(),
		                                  iconPlayer13.getIconHeight()); 
		     player.playerUI.rand11_13.setPreferredSize(new Dimension(iconPlayer13.getIconWidth(),
		                                        iconPlayer13.getIconHeight()));
		     boardPlayer14.setBounds(0, 0, iconPlayer14.getIconWidth(),
		                                  iconPlayer14.getIconHeight()); 
		     player.playerUI.rand11_14.setPreferredSize(new Dimension(iconPlayer14.getIconWidth(),
		                                        iconPlayer14.getIconHeight()));
		     boardPlayer15.setBounds(0, 0, iconPlayer15.getIconWidth(),
		                                  iconPlayer15.getIconHeight()); 
		     player.playerUI.rand11_15.setPreferredSize(new Dimension(iconPlayer15.getIconWidth(),
		                                        iconPlayer15.getIconHeight()));	
		     boardPlayer16.setBounds(0, 0, iconPlayer16.getIconWidth(),
		                                  iconPlayer16.getIconHeight()); 
		     player.playerUI.rand11_16.setPreferredSize(new Dimension(iconPlayer16.getIconWidth(),
		                                        iconPlayer16.getIconHeight()));													 												 													 
		
			  player.playerUI.mypanel6.add(player.playerUI.rand11_1);
		     player.playerUI.mypanel6.add(player.playerUI.rand11_2);
		     player.playerUI.mypanel6.add(player.playerUI.rand11_3);
		     player.playerUI.mypanel6.add(player.playerUI.rand11_4);
		     player.playerUI.mypanel6.add(player.playerUI.rand11_5);
		     player.playerUI.mypanel6.add(player.playerUI.rand11_6);
		     player.playerUI.mypanel6.add(player.playerUI.rand11_7);
		     player.playerUI.mypanel6.add(player.playerUI.rand11_8);
		     player.playerUI.mypanel6.add(player.playerUI.rand11_9);
		     player.playerUI.mypanel6.add(player.playerUI.rand11_10);
		     player.playerUI.mypanel6.add(player.playerUI.rand11_11);
		     player.playerUI.mypanel6.add(player.playerUI.rand11_12);
		     player.playerUI.mypanel6.add(player.playerUI.rand11_13);
		     player.playerUI.mypanel6.add(player.playerUI.rand11_14);
		     player.playerUI.mypanel6.add(player.playerUI.rand11_15);
		     player.playerUI.mypanel6.add(player.playerUI.rand11_16);
		  
		  }
		  else if(tile == 7)
		  {
	       iconPlayer1 =  bp[4][12].getIcon();
	       iconPlayer2 =  bp[5][12].getIcon();
	       iconPlayer3 =  bp[6][12].getIcon();
	       iconPlayer4 =  bp[7][12].getIcon();
	       iconPlayer5 =  bp[4][13].getIcon();
	       iconPlayer6 =  bp[5][13].getIcon();
	       iconPlayer7 =  bp[6][13].getIcon();
	       iconPlayer8 =  bp[7][13].getIcon();
	       iconPlayer9 =  bp[4][14].getIcon();
	       iconPlayer10 =  bp[5][14].getIcon();
	       iconPlayer11 =  bp[6][14].getIcon();
	       iconPlayer12 =  bp[7][14].getIcon();
	       iconPlayer13 =  bp[4][15].getIcon();
	       iconPlayer14 =  bp[5][15].getIcon();
	       iconPlayer15 =  bp[6][15].getIcon();
	       iconPlayer16 =  bp[7][15].getIcon();


		//add components to board
			  GameJLabel boardPlayer1 = new GameJLabel(iconPlayer1);
			  GameJLabel boardPlayer2 = new GameJLabel(iconPlayer2);
			  GameJLabel boardPlayer3 = new GameJLabel(iconPlayer3);
			  GameJLabel boardPlayer4 = new GameJLabel(iconPlayer4);
			  GameJLabel boardPlayer5 = new GameJLabel(iconPlayer5);
			  GameJLabel boardPlayer6 = new GameJLabel(iconPlayer6);
			  GameJLabel boardPlayer7 = new GameJLabel(iconPlayer7);
			  GameJLabel boardPlayer8 = new GameJLabel(iconPlayer8);
			  GameJLabel boardPlayer9 = new GameJLabel(iconPlayer9);
			  GameJLabel boardPlayer10 = new GameJLabel(iconPlayer10);
			  GameJLabel boardPlayer11 = new GameJLabel(iconPlayer11);
			  GameJLabel boardPlayer12 = new GameJLabel(iconPlayer12);
			  GameJLabel boardPlayer13 = new GameJLabel(iconPlayer13);
			  GameJLabel boardPlayer14 = new GameJLabel(iconPlayer14);
			  GameJLabel boardPlayer15 = new GameJLabel(iconPlayer15);
			  GameJLabel boardPlayer16 = new GameJLabel(iconPlayer16);
		
			  player.playerUI.rand11_1.add(boardPlayer1, new Integer(0));
		     player.playerUI.rand11_2.add(boardPlayer2, new Integer(0));
		     player.playerUI.rand11_3.add(boardPlayer3, new Integer(0));
		     player.playerUI.rand11_4.add(boardPlayer4, new Integer(0));
		     player.playerUI.rand11_5.add(boardPlayer5, new Integer(0));
		     player.playerUI.rand11_6.add(boardPlayer6, new Integer(0));
		     player.playerUI.rand11_7.add(boardPlayer7, new Integer(0));
		     player.playerUI.rand11_8.add(boardPlayer8, new Integer(0));
		     player.playerUI.rand11_9.add(boardPlayer9, new Integer(0));
		     player.playerUI.rand11_10.add(boardPlayer10, new Integer(0));
		     player.playerUI.rand11_11.add(boardPlayer11, new Integer(0));
		     player.playerUI.rand11_12.add(boardPlayer12, new Integer(0));
		     player.playerUI.rand11_13.add(boardPlayer13, new Integer(0));
		     player.playerUI.rand11_14.add(boardPlayer14, new Integer(0));
		     player.playerUI.rand11_15.add(boardPlayer15, new Integer(0));
		     player.playerUI.rand11_16.add(boardPlayer16, new Integer(0));
		
		     boardPlayer1.setBounds(0, 0, iconPlayer1.getIconWidth(),
		                                  iconPlayer1.getIconHeight()); 
		     player.playerUI.rand11_1.setPreferredSize(new Dimension(iconPlayer1.getIconWidth(),
		                                        iconPlayer1.getIconHeight()));
		     boardPlayer2.setBounds(0, 0, iconPlayer2.getIconWidth(),
		                                  iconPlayer2.getIconHeight()); 
		     player.playerUI.rand11_2.setPreferredSize(new Dimension(iconPlayer2.getIconWidth(),
		                                        iconPlayer2.getIconHeight()));
		     boardPlayer3.setBounds(0, 0, iconPlayer3.getIconWidth(),
		                                  iconPlayer3.getIconHeight()); 
		     player.playerUI.rand11_3.setPreferredSize(new Dimension(iconPlayer3.getIconWidth(),
		                                        iconPlayer3.getIconHeight()));
		     boardPlayer4.setBounds(0, 0, iconPlayer4.getIconWidth(),
		                                  iconPlayer4.getIconHeight()); 
		     player.playerUI.rand11_4.setPreferredSize(new Dimension(iconPlayer4.getIconWidth(),
		                                        iconPlayer4.getIconHeight()));
		     boardPlayer5.setBounds(0, 0, iconPlayer5.getIconWidth(),
		                                  iconPlayer5.getIconHeight()); 
		     player.playerUI.rand11_5.setPreferredSize(new Dimension(iconPlayer5.getIconWidth(),
		                                        iconPlayer5.getIconHeight()));
		     boardPlayer6.setBounds(0, 0, iconPlayer6.getIconWidth(),
		                                  iconPlayer6.getIconHeight()); 
		     player.playerUI.rand11_6.setPreferredSize(new Dimension(iconPlayer6.getIconWidth(),
		                                        iconPlayer6.getIconHeight()));
		     boardPlayer7.setBounds(0, 0, iconPlayer7.getIconWidth(),
		                                  iconPlayer7.getIconHeight()); 
		     player.playerUI.rand11_7.setPreferredSize(new Dimension(iconPlayer7.getIconWidth(),
		                                        iconPlayer7.getIconHeight()));
		     boardPlayer8.setBounds(0, 0, iconPlayer8.getIconWidth(),
		                                  iconPlayer8.getIconHeight()); 
		     player.playerUI.rand11_8.setPreferredSize(new Dimension(iconPlayer8.getIconWidth(),
		                                        iconPlayer8.getIconHeight()));
		     boardPlayer9.setBounds(0, 0, iconPlayer9.getIconWidth(),
		                                  iconPlayer9.getIconHeight()); 
		     player.playerUI.rand11_9.setPreferredSize(new Dimension(iconPlayer9.getIconWidth(),
		                                        iconPlayer9.getIconHeight()));
		     boardPlayer10.setBounds(0, 0, iconPlayer10.getIconWidth(),
		                                  iconPlayer10.getIconHeight()); 
		     player.playerUI.rand11_10.setPreferredSize(new Dimension(iconPlayer10.getIconWidth(),
		                                        iconPlayer10.getIconHeight()));													 													 
		     boardPlayer11.setBounds(0, 0, iconPlayer11.getIconWidth(),
		                                  iconPlayer11.getIconHeight()); 
		     player.playerUI.rand11_11.setPreferredSize(new Dimension(iconPlayer11.getIconWidth(),
		                                        iconPlayer11.getIconHeight()));
		     boardPlayer12.setBounds(0, 0, iconPlayer12.getIconWidth(),
		                                  iconPlayer12.getIconHeight()); 
		     player.playerUI.rand11_12.setPreferredSize(new Dimension(iconPlayer12.getIconWidth(),
		                                        iconPlayer12.getIconHeight()));
		     boardPlayer13.setBounds(0, 0, iconPlayer13.getIconWidth(),
		                                  iconPlayer13.getIconHeight()); 
		     player.playerUI.rand11_13.setPreferredSize(new Dimension(iconPlayer13.getIconWidth(),
		                                        iconPlayer13.getIconHeight()));
		     boardPlayer14.setBounds(0, 0, iconPlayer14.getIconWidth(),
		                                  iconPlayer14.getIconHeight()); 
		     player.playerUI.rand11_14.setPreferredSize(new Dimension(iconPlayer14.getIconWidth(),
		                                        iconPlayer14.getIconHeight()));
		     boardPlayer15.setBounds(0, 0, iconPlayer15.getIconWidth(),
		                                  iconPlayer15.getIconHeight()); 
		     player.playerUI.rand11_15.setPreferredSize(new Dimension(iconPlayer15.getIconWidth(),
		                                        iconPlayer15.getIconHeight()));	
		     boardPlayer16.setBounds(0, 0, iconPlayer16.getIconWidth(),
		                                  iconPlayer16.getIconHeight()); 
		     player.playerUI.rand11_16.setPreferredSize(new Dimension(iconPlayer16.getIconWidth(),
		                                        iconPlayer16.getIconHeight()));													 												 													 
		
			  player.playerUI.mypanel7.add(player.playerUI.rand11_1);
		     player.playerUI.mypanel7.add(player.playerUI.rand11_2);
		     player.playerUI.mypanel7.add(player.playerUI.rand11_3);
		     player.playerUI.mypanel7.add(player.playerUI.rand11_4);
		     player.playerUI.mypanel7.add(player.playerUI.rand11_5);
		     player.playerUI.mypanel7.add(player.playerUI.rand11_6);
		     player.playerUI.mypanel7.add(player.playerUI.rand11_7);
		     player.playerUI.mypanel7.add(player.playerUI.rand11_8);
		     player.playerUI.mypanel7.add(player.playerUI.rand11_9);
		     player.playerUI.mypanel7.add(player.playerUI.rand11_10);
		     player.playerUI.mypanel7.add(player.playerUI.rand11_11);
		     player.playerUI.mypanel7.add(player.playerUI.rand11_12);
		     player.playerUI.mypanel7.add(player.playerUI.rand11_13);
		     player.playerUI.mypanel7.add(player.playerUI.rand11_14);
		     player.playerUI.mypanel7.add(player.playerUI.rand11_15);
		     player.playerUI.mypanel7.add(player.playerUI.rand11_16);
		  
		  }
		}

      private void populateRand12(int tile, Player player)
		{
		  if(tile == 0)
		  {
			  iconPlayer1 =  (bp[0][0].getIcon());
	        iconPlayer2 =  (bp[1][0].getIcon());
 	        iconPlayer3 =  (bp[2][0].getIcon());
	        iconPlayer4 =  (bp[3][0].getIcon());
	        iconPlayer5 =  (bp[0][1].getIcon());
	        iconPlayer6 =  (bp[1][1].getIcon());
	        iconPlayer7 =  (bp[2][1].getIcon());
	        iconPlayer8 =  (bp[3][1].getIcon());
	        iconPlayer9 =  (bp[0][2].getIcon());
	        iconPlayer10 =  (bp[1][2].getIcon());
	        iconPlayer11 =  (bp[2][2].getIcon());
	        iconPlayer12 =  (bp[3][2].getIcon());
	        iconPlayer13 =  (bp[0][3].getIcon());
	        iconPlayer14 =  (bp[1][3].getIcon());
	        iconPlayer15 =  (bp[2][3].getIcon());
	        iconPlayer16 =  (bp[3][3].getIcon());

	
		//add components to board
			  GameJLabel boardPlayer1 = new GameJLabel(iconPlayer1);
			  GameJLabel boardPlayer2 = new GameJLabel(iconPlayer2);
			  GameJLabel boardPlayer3 = new GameJLabel(iconPlayer3);
			  GameJLabel boardPlayer4 = new GameJLabel(iconPlayer4);
			  GameJLabel boardPlayer5 = new GameJLabel(iconPlayer5);
			  GameJLabel boardPlayer6 = new GameJLabel(iconPlayer6);
			  GameJLabel boardPlayer7 = new GameJLabel(iconPlayer7);
			  GameJLabel boardPlayer8 = new GameJLabel(iconPlayer8);
			  GameJLabel boardPlayer9 = new GameJLabel(iconPlayer9);
			  GameJLabel boardPlayer10 = new GameJLabel(iconPlayer10);
			  GameJLabel boardPlayer11 = new GameJLabel(iconPlayer11);
			  GameJLabel boardPlayer12 = new GameJLabel(iconPlayer12);
			  GameJLabel boardPlayer13 = new GameJLabel(iconPlayer13);
			  GameJLabel boardPlayer14 = new GameJLabel(iconPlayer14);
			  GameJLabel boardPlayer15 = new GameJLabel(iconPlayer15);
			  GameJLabel boardPlayer16 = new GameJLabel(iconPlayer16);
		
			  player.playerUI.rand12_1.add(boardPlayer1, new Integer(0));
		     player.playerUI.rand12_2.add(boardPlayer2, new Integer(0));
		     player.playerUI.rand12_3.add(boardPlayer3, new Integer(0));
		     player.playerUI.rand12_4.add(boardPlayer4, new Integer(0));
		     player.playerUI.rand12_5.add(boardPlayer5, new Integer(0));
		     player.playerUI.rand12_6.add(boardPlayer6, new Integer(0));
		     player.playerUI.rand12_7.add(boardPlayer7, new Integer(0));
		     player.playerUI.rand12_8.add(boardPlayer8, new Integer(0));
		     player.playerUI.rand12_9.add(boardPlayer9, new Integer(0));
		     player.playerUI.rand12_10.add(boardPlayer10, new Integer(0));
		     player.playerUI.rand12_11.add(boardPlayer11, new Integer(0));
		     player.playerUI.rand12_12.add(boardPlayer12, new Integer(0));
		     player.playerUI.rand12_13.add(boardPlayer13, new Integer(0));
		     player.playerUI.rand12_14.add(boardPlayer14, new Integer(0));
		     player.playerUI.rand12_15.add(boardPlayer15, new Integer(0));
		     player.playerUI.rand12_16.add(boardPlayer16, new Integer(0));
		
		     boardPlayer1.setBounds(0, 0, iconPlayer1.getIconWidth(),
		                                  iconPlayer1.getIconHeight()); 
		     player.playerUI.rand12_1.setPreferredSize(new Dimension(iconPlayer1.getIconWidth(),
		                                        iconPlayer1.getIconHeight()));
		     boardPlayer2.setBounds(0, 0, iconPlayer2.getIconWidth(),
		                                  iconPlayer2.getIconHeight()); 
		     player.playerUI.rand12_2.setPreferredSize(new Dimension(iconPlayer2.getIconWidth(),
		                                        iconPlayer2.getIconHeight()));
		     boardPlayer3.setBounds(0, 0, iconPlayer3.getIconWidth(),
		                                  iconPlayer3.getIconHeight()); 
		     player.playerUI.rand12_3.setPreferredSize(new Dimension(iconPlayer3.getIconWidth(),
		                                        iconPlayer3.getIconHeight()));
		     boardPlayer4.setBounds(0, 0, iconPlayer4.getIconWidth(),
		                                  iconPlayer4.getIconHeight()); 
		     player.playerUI.rand12_4.setPreferredSize(new Dimension(iconPlayer4.getIconWidth(),
		                                        iconPlayer4.getIconHeight()));
		     boardPlayer5.setBounds(0, 0, iconPlayer5.getIconWidth(),
		                                  iconPlayer5.getIconHeight()); 
		     player.playerUI.rand12_5.setPreferredSize(new Dimension(iconPlayer5.getIconWidth(),
		                                        iconPlayer5.getIconHeight()));
		     boardPlayer6.setBounds(0, 0, iconPlayer6.getIconWidth(),
		                                  iconPlayer6.getIconHeight()); 
		     player.playerUI.rand12_6.setPreferredSize(new Dimension(iconPlayer6.getIconWidth(),
		                                        iconPlayer6.getIconHeight()));
		     boardPlayer7.setBounds(0, 0, iconPlayer7.getIconWidth(),
		                                  iconPlayer7.getIconHeight()); 
		     player.playerUI.rand12_7.setPreferredSize(new Dimension(iconPlayer7.getIconWidth(),
		                                        iconPlayer7.getIconHeight()));
		     boardPlayer8.setBounds(0, 0, iconPlayer8.getIconWidth(),
		                                  iconPlayer8.getIconHeight()); 
		     player.playerUI.rand12_8.setPreferredSize(new Dimension(iconPlayer8.getIconWidth(),
		                                        iconPlayer8.getIconHeight()));
		     boardPlayer9.setBounds(0, 0, iconPlayer9.getIconWidth(),
		                                  iconPlayer9.getIconHeight()); 
		     player.playerUI.rand12_9.setPreferredSize(new Dimension(iconPlayer9.getIconWidth(),
		                                        iconPlayer9.getIconHeight()));
		     boardPlayer10.setBounds(0, 0, iconPlayer10.getIconWidth(),
		                                  iconPlayer10.getIconHeight()); 
		     player.playerUI.rand12_10.setPreferredSize(new Dimension(iconPlayer10.getIconWidth(),
		                                        iconPlayer10.getIconHeight()));													 													 
		     boardPlayer11.setBounds(0, 0, iconPlayer11.getIconWidth(),
		                                  iconPlayer11.getIconHeight()); 
		     player.playerUI.rand12_11.setPreferredSize(new Dimension(iconPlayer11.getIconWidth(),
		                                        iconPlayer11.getIconHeight()));
		     boardPlayer12.setBounds(0, 0, iconPlayer12.getIconWidth(),
		                                  iconPlayer12.getIconHeight()); 
		     player.playerUI.rand12_12.setPreferredSize(new Dimension(iconPlayer12.getIconWidth(),
		                                        iconPlayer12.getIconHeight()));
		     boardPlayer13.setBounds(0, 0, iconPlayer13.getIconWidth(),
		                                  iconPlayer13.getIconHeight()); 
		     player.playerUI.rand12_13.setPreferredSize(new Dimension(iconPlayer13.getIconWidth(),
		                                        iconPlayer13.getIconHeight()));
		     boardPlayer14.setBounds(0, 0, iconPlayer14.getIconWidth(),
		                                  iconPlayer14.getIconHeight()); 
		     player.playerUI.rand12_14.setPreferredSize(new Dimension(iconPlayer14.getIconWidth(),
		                                        iconPlayer14.getIconHeight()));
		     boardPlayer15.setBounds(0, 0, iconPlayer15.getIconWidth(),
		                                  iconPlayer15.getIconHeight()); 
		     player.playerUI.rand12_15.setPreferredSize(new Dimension(iconPlayer15.getIconWidth(),
		                                        iconPlayer15.getIconHeight()));	
		     boardPlayer16.setBounds(0, 0, iconPlayer16.getIconWidth(),
		                                  iconPlayer16.getIconHeight()); 
		     player.playerUI.rand12_16.setPreferredSize(new Dimension(iconPlayer16.getIconWidth(),
		                                        iconPlayer16.getIconHeight()));													 												 													 
		
			  player.playerUI.mypanel0.add(player.playerUI.rand12_1);
		     player.playerUI.mypanel0.add(player.playerUI.rand12_2);
		     player.playerUI.mypanel0.add(player.playerUI.rand12_3);
		     player.playerUI.mypanel0.add(player.playerUI.rand12_4);
		     player.playerUI.mypanel0.add(player.playerUI.rand12_5);
		     player.playerUI.mypanel0.add(player.playerUI.rand12_6);
		     player.playerUI.mypanel0.add(player.playerUI.rand12_7);
		     player.playerUI.mypanel0.add(player.playerUI.rand12_8);
		     player.playerUI.mypanel0.add(player.playerUI.rand12_9);
		     player.playerUI.mypanel0.add(player.playerUI.rand12_10);
		     player.playerUI.mypanel0.add(player.playerUI.rand12_11);
		     player.playerUI.mypanel0.add(player.playerUI.rand12_12);
		     player.playerUI.mypanel0.add(player.playerUI.rand12_13);
		     player.playerUI.mypanel0.add(player.playerUI.rand12_14);
		     player.playerUI.mypanel0.add(player.playerUI.rand12_15);
		     player.playerUI.mypanel0.add(player.playerUI.rand12_16);
		  
		  }
		  else if(tile == 1)
		  {
  	        iconPlayer1 =  (bp[0][4].getIcon());
	        iconPlayer2 =  (bp[1][4].getIcon());
 	        iconPlayer3 =  (bp[2][4].getIcon());
	        iconPlayer4 =  (bp[3][4].getIcon());
	        iconPlayer5 =  (bp[0][5].getIcon());
	        iconPlayer6 =  (bp[1][5].getIcon());
	        iconPlayer7 =  (bp[2][5].getIcon());
	        iconPlayer8 =  (bp[3][5].getIcon());
	        iconPlayer9 =  (bp[0][6].getIcon());
	        iconPlayer10 =  (bp[1][6].getIcon());
	        iconPlayer11 =  (bp[2][6].getIcon());
	        iconPlayer12 =  (bp[3][6].getIcon());
	        iconPlayer13 =  (bp[0][7].getIcon());
	        iconPlayer14 =  (bp[1][7].getIcon());
	        iconPlayer15 =  (bp[2][7].getIcon());
	        iconPlayer16 =  (bp[3][7].getIcon());

	
		//add components to board
			  GameJLabel boardPlayer1 = new GameJLabel(iconPlayer1);
			  GameJLabel boardPlayer2 = new GameJLabel(iconPlayer2);
			  GameJLabel boardPlayer3 = new GameJLabel(iconPlayer3);
			  GameJLabel boardPlayer4 = new GameJLabel(iconPlayer4);
			  GameJLabel boardPlayer5 = new GameJLabel(iconPlayer5);
			  GameJLabel boardPlayer6 = new GameJLabel(iconPlayer6);
			  GameJLabel boardPlayer7 = new GameJLabel(iconPlayer7);
			  GameJLabel boardPlayer8 = new GameJLabel(iconPlayer8);
			  GameJLabel boardPlayer9 = new GameJLabel(iconPlayer9);
			  GameJLabel boardPlayer10 = new GameJLabel(iconPlayer10);
			  GameJLabel boardPlayer11 = new GameJLabel(iconPlayer11);
			  GameJLabel boardPlayer12 = new GameJLabel(iconPlayer12);
			  GameJLabel boardPlayer13 = new GameJLabel(iconPlayer13);
			  GameJLabel boardPlayer14 = new GameJLabel(iconPlayer14);
			  GameJLabel boardPlayer15 = new GameJLabel(iconPlayer15);
			  GameJLabel boardPlayer16 = new GameJLabel(iconPlayer16);
		
			  player.playerUI.rand12_1.add(boardPlayer1, new Integer(0));
		     player.playerUI.rand12_2.add(boardPlayer2, new Integer(0));
		     player.playerUI.rand12_3.add(boardPlayer3, new Integer(0));
		     player.playerUI.rand12_4.add(boardPlayer4, new Integer(0));
		     player.playerUI.rand12_5.add(boardPlayer5, new Integer(0));
		     player.playerUI.rand12_6.add(boardPlayer6, new Integer(0));
		     player.playerUI.rand12_7.add(boardPlayer7, new Integer(0));
		     player.playerUI.rand12_8.add(boardPlayer8, new Integer(0));
		     player.playerUI.rand12_9.add(boardPlayer9, new Integer(0));
		     player.playerUI.rand12_10.add(boardPlayer10, new Integer(0));
		     player.playerUI.rand12_11.add(boardPlayer11, new Integer(0));
		     player.playerUI.rand12_12.add(boardPlayer12, new Integer(0));
		     player.playerUI.rand12_13.add(boardPlayer13, new Integer(0));
		     player.playerUI.rand12_14.add(boardPlayer14, new Integer(0));
		     player.playerUI.rand12_15.add(boardPlayer15, new Integer(0));
		     player.playerUI.rand12_16.add(boardPlayer16, new Integer(0));
		
		     boardPlayer1.setBounds(0, 0, iconPlayer1.getIconWidth(),
		                                  iconPlayer1.getIconHeight()); 
		     player.playerUI.rand12_1.setPreferredSize(new Dimension(iconPlayer1.getIconWidth(),
		                                        iconPlayer1.getIconHeight()));
		     boardPlayer2.setBounds(0, 0, iconPlayer2.getIconWidth(),
		                                  iconPlayer2.getIconHeight()); 
		     player.playerUI.rand12_2.setPreferredSize(new Dimension(iconPlayer2.getIconWidth(),
		                                        iconPlayer2.getIconHeight()));
		     boardPlayer3.setBounds(0, 0, iconPlayer3.getIconWidth(),
		                                  iconPlayer3.getIconHeight()); 
		     player.playerUI.rand12_3.setPreferredSize(new Dimension(iconPlayer3.getIconWidth(),
		                                        iconPlayer3.getIconHeight()));
		     boardPlayer4.setBounds(0, 0, iconPlayer4.getIconWidth(),
		                                  iconPlayer4.getIconHeight()); 
		     player.playerUI.rand12_4.setPreferredSize(new Dimension(iconPlayer4.getIconWidth(),
		                                        iconPlayer4.getIconHeight()));
		     boardPlayer5.setBounds(0, 0, iconPlayer5.getIconWidth(),
		                                  iconPlayer5.getIconHeight()); 
		     player.playerUI.rand12_5.setPreferredSize(new Dimension(iconPlayer5.getIconWidth(),
		                                        iconPlayer5.getIconHeight()));
		     boardPlayer6.setBounds(0, 0, iconPlayer6.getIconWidth(),
		                                  iconPlayer6.getIconHeight()); 
		     player.playerUI.rand12_6.setPreferredSize(new Dimension(iconPlayer6.getIconWidth(),
		                                        iconPlayer6.getIconHeight()));
		     boardPlayer7.setBounds(0, 0, iconPlayer7.getIconWidth(),
		                                  iconPlayer7.getIconHeight()); 
		     player.playerUI.rand12_7.setPreferredSize(new Dimension(iconPlayer7.getIconWidth(),
		                                        iconPlayer7.getIconHeight()));
		     boardPlayer8.setBounds(0, 0, iconPlayer8.getIconWidth(),
		                                  iconPlayer8.getIconHeight()); 
		     player.playerUI.rand12_8.setPreferredSize(new Dimension(iconPlayer8.getIconWidth(),
		                                        iconPlayer8.getIconHeight()));
		     boardPlayer9.setBounds(0, 0, iconPlayer9.getIconWidth(),
		                                  iconPlayer9.getIconHeight()); 
		     player.playerUI.rand12_9.setPreferredSize(new Dimension(iconPlayer9.getIconWidth(),
		                                        iconPlayer9.getIconHeight()));
		     boardPlayer10.setBounds(0, 0, iconPlayer10.getIconWidth(),
		                                  iconPlayer10.getIconHeight()); 
		     player.playerUI.rand12_10.setPreferredSize(new Dimension(iconPlayer10.getIconWidth(),
		                                        iconPlayer10.getIconHeight()));													 													 
		     boardPlayer11.setBounds(0, 0, iconPlayer11.getIconWidth(),
		                                  iconPlayer11.getIconHeight()); 
		     player.playerUI.rand12_11.setPreferredSize(new Dimension(iconPlayer11.getIconWidth(),
		                                        iconPlayer11.getIconHeight()));
		     boardPlayer12.setBounds(0, 0, iconPlayer12.getIconWidth(),
		                                  iconPlayer12.getIconHeight()); 
		     player.playerUI.rand12_12.setPreferredSize(new Dimension(iconPlayer12.getIconWidth(),
		                                        iconPlayer12.getIconHeight()));
		     boardPlayer13.setBounds(0, 0, iconPlayer13.getIconWidth(),
		                                  iconPlayer13.getIconHeight()); 
		     player.playerUI.rand12_13.setPreferredSize(new Dimension(iconPlayer13.getIconWidth(),
		                                        iconPlayer13.getIconHeight()));
		     boardPlayer14.setBounds(0, 0, iconPlayer14.getIconWidth(),
		                                  iconPlayer14.getIconHeight()); 
		     player.playerUI.rand12_14.setPreferredSize(new Dimension(iconPlayer14.getIconWidth(),
		                                        iconPlayer14.getIconHeight()));
		     boardPlayer15.setBounds(0, 0, iconPlayer15.getIconWidth(),
		                                  iconPlayer15.getIconHeight()); 
		     player.playerUI.rand12_15.setPreferredSize(new Dimension(iconPlayer15.getIconWidth(),
		                                        iconPlayer15.getIconHeight()));	
		     boardPlayer16.setBounds(0, 0, iconPlayer16.getIconWidth(),
		                                  iconPlayer16.getIconHeight()); 
		     player.playerUI.rand12_16.setPreferredSize(new Dimension(iconPlayer16.getIconWidth(),
		                                        iconPlayer16.getIconHeight()));													 												 													 
		
			  player.playerUI.mypanel1.add(player.playerUI.rand12_1);
		     player.playerUI.mypanel1.add(player.playerUI.rand12_2);
		     player.playerUI.mypanel1.add(player.playerUI.rand12_3);
		     player.playerUI.mypanel1.add(player.playerUI.rand12_4);
		     player.playerUI.mypanel1.add(player.playerUI.rand12_5);
		     player.playerUI.mypanel1.add(player.playerUI.rand12_6);
		     player.playerUI.mypanel1.add(player.playerUI.rand12_7);
		     player.playerUI.mypanel1.add(player.playerUI.rand12_8);
		     player.playerUI.mypanel1.add(player.playerUI.rand12_9);
		     player.playerUI.mypanel1.add(player.playerUI.rand12_10);
		     player.playerUI.mypanel1.add(player.playerUI.rand12_11);
		     player.playerUI.mypanel1.add(player.playerUI.rand12_12);
		     player.playerUI.mypanel1.add(player.playerUI.rand12_13);
		     player.playerUI.mypanel1.add(player.playerUI.rand12_14);
		     player.playerUI.mypanel1.add(player.playerUI.rand12_15);
		     player.playerUI.mypanel1.add(player.playerUI.rand12_16);
		  
		  }
		  else if(tile == 2)
		  {
  	        iconPlayer1 =  (bp[0][8].getIcon());
	        iconPlayer2 =  (bp[1][8].getIcon());
 	        iconPlayer3 =  (bp[2][8].getIcon());
	        iconPlayer4 =  (bp[3][8].getIcon());
	        iconPlayer5 =  (bp[0][9].getIcon());
	        iconPlayer6 =  (bp[1][9].getIcon());
	        iconPlayer7 =  (bp[2][9].getIcon());
	        iconPlayer8 =  (bp[3][9].getIcon());
	        iconPlayer9 =  (bp[0][10].getIcon());
	        iconPlayer10 =  (bp[1][10].getIcon());
	        iconPlayer11 =  (bp[2][10].getIcon());
	        iconPlayer12 =  (bp[3][10].getIcon());
	        iconPlayer13 =  (bp[0][11].getIcon());
	        iconPlayer14 =  (bp[1][11].getIcon());
	        iconPlayer15 =  (bp[2][11].getIcon());
	        iconPlayer16 =  (bp[3][11].getIcon());

	
		//add components to board
			  GameJLabel boardPlayer1 = new GameJLabel(iconPlayer1);
			  GameJLabel boardPlayer2 = new GameJLabel(iconPlayer2);
			  GameJLabel boardPlayer3 = new GameJLabel(iconPlayer3);
			  GameJLabel boardPlayer4 = new GameJLabel(iconPlayer4);
			  GameJLabel boardPlayer5 = new GameJLabel(iconPlayer5);
			  GameJLabel boardPlayer6 = new GameJLabel(iconPlayer6);
			  GameJLabel boardPlayer7 = new GameJLabel(iconPlayer7);
			  GameJLabel boardPlayer8 = new GameJLabel(iconPlayer8);
			  GameJLabel boardPlayer9 = new GameJLabel(iconPlayer9);
			  GameJLabel boardPlayer10 = new GameJLabel(iconPlayer10);
			  GameJLabel boardPlayer11 = new GameJLabel(iconPlayer11);
			  GameJLabel boardPlayer12 = new GameJLabel(iconPlayer12);
			  GameJLabel boardPlayer13 = new GameJLabel(iconPlayer13);
			  GameJLabel boardPlayer14 = new GameJLabel(iconPlayer14);
			  GameJLabel boardPlayer15 = new GameJLabel(iconPlayer15);
			  GameJLabel boardPlayer16 = new GameJLabel(iconPlayer16);
		
			  player.playerUI.rand12_1.add(boardPlayer1, new Integer(0));
		     player.playerUI.rand12_2.add(boardPlayer2, new Integer(0));
		     player.playerUI.rand12_3.add(boardPlayer3, new Integer(0));
		     player.playerUI.rand12_4.add(boardPlayer4, new Integer(0));
		     player.playerUI.rand12_5.add(boardPlayer5, new Integer(0));
		     player.playerUI.rand12_6.add(boardPlayer6, new Integer(0));
		     player.playerUI.rand12_7.add(boardPlayer7, new Integer(0));
		     player.playerUI.rand12_8.add(boardPlayer8, new Integer(0));
		     player.playerUI.rand12_9.add(boardPlayer9, new Integer(0));
		     player.playerUI.rand12_10.add(boardPlayer10, new Integer(0));
		     player.playerUI.rand12_11.add(boardPlayer11, new Integer(0));
		     player.playerUI.rand12_12.add(boardPlayer12, new Integer(0));
		     player.playerUI.rand12_13.add(boardPlayer13, new Integer(0));
		     player.playerUI.rand12_14.add(boardPlayer14, new Integer(0));
		     player.playerUI.rand12_15.add(boardPlayer15, new Integer(0));
		     player.playerUI.rand12_16.add(boardPlayer16, new Integer(0));
		
		     boardPlayer1.setBounds(0, 0, iconPlayer1.getIconWidth(),
		                                  iconPlayer1.getIconHeight()); 
		     player.playerUI.rand12_1.setPreferredSize(new Dimension(iconPlayer1.getIconWidth(),
		                                        iconPlayer1.getIconHeight()));
		     boardPlayer2.setBounds(0, 0, iconPlayer2.getIconWidth(),
		                                  iconPlayer2.getIconHeight()); 
		     player.playerUI.rand12_2.setPreferredSize(new Dimension(iconPlayer2.getIconWidth(),
		                                        iconPlayer2.getIconHeight()));
		     boardPlayer3.setBounds(0, 0, iconPlayer3.getIconWidth(),
		                                  iconPlayer3.getIconHeight()); 
		     player.playerUI.rand12_3.setPreferredSize(new Dimension(iconPlayer3.getIconWidth(),
		                                        iconPlayer3.getIconHeight()));
		     boardPlayer4.setBounds(0, 0, iconPlayer4.getIconWidth(),
		                                  iconPlayer4.getIconHeight()); 
		     player.playerUI.rand12_4.setPreferredSize(new Dimension(iconPlayer4.getIconWidth(),
		                                        iconPlayer4.getIconHeight()));
		     boardPlayer5.setBounds(0, 0, iconPlayer5.getIconWidth(),
		                                  iconPlayer5.getIconHeight()); 
		     player.playerUI.rand12_5.setPreferredSize(new Dimension(iconPlayer5.getIconWidth(),
		                                        iconPlayer5.getIconHeight()));
		     boardPlayer6.setBounds(0, 0, iconPlayer6.getIconWidth(),
		                                  iconPlayer6.getIconHeight()); 
		     player.playerUI.rand12_6.setPreferredSize(new Dimension(iconPlayer6.getIconWidth(),
		                                        iconPlayer6.getIconHeight()));
		     boardPlayer7.setBounds(0, 0, iconPlayer7.getIconWidth(),
		                                  iconPlayer7.getIconHeight()); 
		     player.playerUI.rand12_7.setPreferredSize(new Dimension(iconPlayer7.getIconWidth(),
		                                        iconPlayer7.getIconHeight()));
		     boardPlayer8.setBounds(0, 0, iconPlayer8.getIconWidth(),
		                                  iconPlayer8.getIconHeight()); 
		     player.playerUI.rand12_8.setPreferredSize(new Dimension(iconPlayer8.getIconWidth(),
		                                        iconPlayer8.getIconHeight()));
		     boardPlayer9.setBounds(0, 0, iconPlayer9.getIconWidth(),
		                                  iconPlayer9.getIconHeight()); 
		     player.playerUI.rand12_9.setPreferredSize(new Dimension(iconPlayer9.getIconWidth(),
		                                        iconPlayer9.getIconHeight()));
		     boardPlayer10.setBounds(0, 0, iconPlayer10.getIconWidth(),
		                                  iconPlayer10.getIconHeight()); 
		     player.playerUI.rand12_10.setPreferredSize(new Dimension(iconPlayer10.getIconWidth(),
		                                        iconPlayer10.getIconHeight()));													 													 
		     boardPlayer11.setBounds(0, 0, iconPlayer11.getIconWidth(),
		                                  iconPlayer11.getIconHeight()); 
		     player.playerUI.rand12_11.setPreferredSize(new Dimension(iconPlayer11.getIconWidth(),
		                                        iconPlayer11.getIconHeight()));
		     boardPlayer12.setBounds(0, 0, iconPlayer12.getIconWidth(),
		                                  iconPlayer12.getIconHeight()); 
		     player.playerUI.rand12_12.setPreferredSize(new Dimension(iconPlayer12.getIconWidth(),
		                                        iconPlayer12.getIconHeight()));
		     boardPlayer13.setBounds(0, 0, iconPlayer13.getIconWidth(),
		                                  iconPlayer13.getIconHeight()); 
		     player.playerUI.rand12_13.setPreferredSize(new Dimension(iconPlayer13.getIconWidth(),
		                                        iconPlayer13.getIconHeight()));
		     boardPlayer14.setBounds(0, 0, iconPlayer14.getIconWidth(),
		                                  iconPlayer14.getIconHeight()); 
		     player.playerUI.rand12_14.setPreferredSize(new Dimension(iconPlayer14.getIconWidth(),
		                                        iconPlayer14.getIconHeight()));
		     boardPlayer15.setBounds(0, 0, iconPlayer15.getIconWidth(),
		                                  iconPlayer15.getIconHeight()); 
		     player.playerUI.rand12_15.setPreferredSize(new Dimension(iconPlayer15.getIconWidth(),
		                                        iconPlayer15.getIconHeight()));	
		     boardPlayer16.setBounds(0, 0, iconPlayer16.getIconWidth(),
		                                  iconPlayer16.getIconHeight()); 
		     player.playerUI.rand12_16.setPreferredSize(new Dimension(iconPlayer16.getIconWidth(),
		                                        iconPlayer16.getIconHeight()));													 												 													 
		
			  player.playerUI.mypanel2.add(player.playerUI.rand12_1);
		     player.playerUI.mypanel2.add(player.playerUI.rand12_2);
		     player.playerUI.mypanel2.add(player.playerUI.rand12_3);
		     player.playerUI.mypanel2.add(player.playerUI.rand12_4);
		     player.playerUI.mypanel2.add(player.playerUI.rand12_5);
		     player.playerUI.mypanel2.add(player.playerUI.rand12_6);
		     player.playerUI.mypanel2.add(player.playerUI.rand12_7);
		     player.playerUI.mypanel2.add(player.playerUI.rand12_8);
		     player.playerUI.mypanel2.add(player.playerUI.rand12_9);
		     player.playerUI.mypanel2.add(player.playerUI.rand12_10);
		     player.playerUI.mypanel2.add(player.playerUI.rand12_11);
		     player.playerUI.mypanel2.add(player.playerUI.rand12_12);
		     player.playerUI.mypanel2.add(player.playerUI.rand12_13);
		     player.playerUI.mypanel2.add(player.playerUI.rand12_14);
		     player.playerUI.mypanel2.add(player.playerUI.rand12_15);
		     player.playerUI.mypanel2.add(player.playerUI.rand12_16);
		  
		  }
		  else if(tile == 5)
		  {
  	        iconPlayer1 =  (bp[4][4].getIcon());
	        iconPlayer2 =  (bp[5][4].getIcon());
 	        iconPlayer3 =  (bp[6][4].getIcon());
	        iconPlayer4 =  (bp[7][4].getIcon());
	        iconPlayer5 =  (bp[4][5].getIcon());
	        iconPlayer6 =  (bp[5][5].getIcon());
	        iconPlayer7 =  (bp[6][5].getIcon());
	        iconPlayer8 =  (bp[7][5].getIcon());
	        iconPlayer9 =  (bp[4][6].getIcon());
	        iconPlayer10 =  (bp[5][6].getIcon());
	        iconPlayer11 =  (bp[6][6].getIcon());
	        iconPlayer12 =  (bp[7][6].getIcon());
	        iconPlayer13 =  (bp[4][7].getIcon());
	        iconPlayer14 =  (bp[5][7].getIcon());
	        iconPlayer15 =  (bp[6][7].getIcon());
	        iconPlayer16 =  (bp[7][7].getIcon());

	
		//add components to board
			  GameJLabel boardPlayer1 = new GameJLabel(iconPlayer1);
			  GameJLabel boardPlayer2 = new GameJLabel(iconPlayer2);
			  GameJLabel boardPlayer3 = new GameJLabel(iconPlayer3);
			  GameJLabel boardPlayer4 = new GameJLabel(iconPlayer4);
			  GameJLabel boardPlayer5 = new GameJLabel(iconPlayer5);
			  GameJLabel boardPlayer6 = new GameJLabel(iconPlayer6);
			  GameJLabel boardPlayer7 = new GameJLabel(iconPlayer7);
			  GameJLabel boardPlayer8 = new GameJLabel(iconPlayer8);
			  GameJLabel boardPlayer9 = new GameJLabel(iconPlayer9);
			  GameJLabel boardPlayer10 = new GameJLabel(iconPlayer10);
			  GameJLabel boardPlayer11 = new GameJLabel(iconPlayer11);
			  GameJLabel boardPlayer12 = new GameJLabel(iconPlayer12);
			  GameJLabel boardPlayer13 = new GameJLabel(iconPlayer13);
			  GameJLabel boardPlayer14 = new GameJLabel(iconPlayer14);
			  GameJLabel boardPlayer15 = new GameJLabel(iconPlayer15);
			  GameJLabel boardPlayer16 = new GameJLabel(iconPlayer16);
		
			  player.playerUI.rand12_1.add(boardPlayer1, new Integer(0));
		     player.playerUI.rand12_2.add(boardPlayer2, new Integer(0));
		     player.playerUI.rand12_3.add(boardPlayer3, new Integer(0));
		     player.playerUI.rand12_4.add(boardPlayer4, new Integer(0));
		     player.playerUI.rand12_5.add(boardPlayer5, new Integer(0));
		     player.playerUI.rand12_6.add(boardPlayer6, new Integer(0));
		     player.playerUI.rand12_7.add(boardPlayer7, new Integer(0));
		     player.playerUI.rand12_8.add(boardPlayer8, new Integer(0));
		     player.playerUI.rand12_9.add(boardPlayer9, new Integer(0));
		     player.playerUI.rand12_10.add(boardPlayer10, new Integer(0));
		     player.playerUI.rand12_11.add(boardPlayer11, new Integer(0));
		     player.playerUI.rand12_12.add(boardPlayer12, new Integer(0));
		     player.playerUI.rand12_13.add(boardPlayer13, new Integer(0));
		     player.playerUI.rand12_14.add(boardPlayer14, new Integer(0));
		     player.playerUI.rand12_15.add(boardPlayer15, new Integer(0));
		     player.playerUI.rand12_16.add(boardPlayer16, new Integer(0));
		
		     boardPlayer1.setBounds(0, 0, iconPlayer1.getIconWidth(),
		                                  iconPlayer1.getIconHeight()); 
		     player.playerUI.rand12_1.setPreferredSize(new Dimension(iconPlayer1.getIconWidth(),
		                                        iconPlayer1.getIconHeight()));
		     boardPlayer2.setBounds(0, 0, iconPlayer2.getIconWidth(),
		                                  iconPlayer2.getIconHeight()); 
		     player.playerUI.rand12_2.setPreferredSize(new Dimension(iconPlayer2.getIconWidth(),
		                                        iconPlayer2.getIconHeight()));
		     boardPlayer3.setBounds(0, 0, iconPlayer3.getIconWidth(),
		                                  iconPlayer3.getIconHeight()); 
		     player.playerUI.rand12_3.setPreferredSize(new Dimension(iconPlayer3.getIconWidth(),
		                                        iconPlayer3.getIconHeight()));
		     boardPlayer4.setBounds(0, 0, iconPlayer4.getIconWidth(),
		                                  iconPlayer4.getIconHeight()); 
		     player.playerUI.rand12_4.setPreferredSize(new Dimension(iconPlayer4.getIconWidth(),
		                                        iconPlayer4.getIconHeight()));
		     boardPlayer5.setBounds(0, 0, iconPlayer5.getIconWidth(),
		                                  iconPlayer5.getIconHeight()); 
		     player.playerUI.rand12_5.setPreferredSize(new Dimension(iconPlayer5.getIconWidth(),
		                                        iconPlayer5.getIconHeight()));
		     boardPlayer6.setBounds(0, 0, iconPlayer6.getIconWidth(),
		                                  iconPlayer6.getIconHeight()); 
		     player.playerUI.rand12_6.setPreferredSize(new Dimension(iconPlayer6.getIconWidth(),
		                                        iconPlayer6.getIconHeight()));
		     boardPlayer7.setBounds(0, 0, iconPlayer7.getIconWidth(),
		                                  iconPlayer7.getIconHeight()); 
		     player.playerUI.rand12_7.setPreferredSize(new Dimension(iconPlayer7.getIconWidth(),
		                                        iconPlayer7.getIconHeight()));
		     boardPlayer8.setBounds(0, 0, iconPlayer8.getIconWidth(),
		                                  iconPlayer8.getIconHeight()); 
		     player.playerUI.rand12_8.setPreferredSize(new Dimension(iconPlayer8.getIconWidth(),
		                                        iconPlayer8.getIconHeight()));
		     boardPlayer9.setBounds(0, 0, iconPlayer9.getIconWidth(),
		                                  iconPlayer9.getIconHeight()); 
		     player.playerUI.rand12_9.setPreferredSize(new Dimension(iconPlayer9.getIconWidth(),
		                                        iconPlayer9.getIconHeight()));
		     boardPlayer10.setBounds(0, 0, iconPlayer10.getIconWidth(),
		                                  iconPlayer10.getIconHeight()); 
		     player.playerUI.rand12_10.setPreferredSize(new Dimension(iconPlayer10.getIconWidth(),
		                                        iconPlayer10.getIconHeight()));													 													 
		     boardPlayer11.setBounds(0, 0, iconPlayer11.getIconWidth(),
		                                  iconPlayer11.getIconHeight()); 
		     player.playerUI.rand12_11.setPreferredSize(new Dimension(iconPlayer11.getIconWidth(),
		                                        iconPlayer11.getIconHeight()));
		     boardPlayer12.setBounds(0, 0, iconPlayer12.getIconWidth(),
		                                  iconPlayer12.getIconHeight()); 
		     player.playerUI.rand12_12.setPreferredSize(new Dimension(iconPlayer12.getIconWidth(),
		                                        iconPlayer12.getIconHeight()));
		     boardPlayer13.setBounds(0, 0, iconPlayer13.getIconWidth(),
		                                  iconPlayer13.getIconHeight()); 
		     player.playerUI.rand12_13.setPreferredSize(new Dimension(iconPlayer13.getIconWidth(),
		                                        iconPlayer13.getIconHeight()));
		     boardPlayer14.setBounds(0, 0, iconPlayer14.getIconWidth(),
		                                  iconPlayer14.getIconHeight()); 
		     player.playerUI.rand12_14.setPreferredSize(new Dimension(iconPlayer14.getIconWidth(),
		                                        iconPlayer14.getIconHeight()));
		     boardPlayer15.setBounds(0, 0, iconPlayer15.getIconWidth(),
		                                  iconPlayer15.getIconHeight()); 
		     player.playerUI.rand12_15.setPreferredSize(new Dimension(iconPlayer15.getIconWidth(),
		                                        iconPlayer15.getIconHeight()));	
		     boardPlayer16.setBounds(0, 0, iconPlayer16.getIconWidth(),
		                                  iconPlayer16.getIconHeight()); 
		     player.playerUI.rand12_16.setPreferredSize(new Dimension(iconPlayer16.getIconWidth(),
		                                        iconPlayer16.getIconHeight()));													 												 													 
		
			  player.playerUI.mypanel5.add(player.playerUI.rand12_1);
		     player.playerUI.mypanel5.add(player.playerUI.rand12_2);
		     player.playerUI.mypanel5.add(player.playerUI.rand12_3);
		     player.playerUI.mypanel5.add(player.playerUI.rand12_4);
		     player.playerUI.mypanel5.add(player.playerUI.rand12_5);
		     player.playerUI.mypanel5.add(player.playerUI.rand12_6);
		     player.playerUI.mypanel5.add(player.playerUI.rand12_7);
		     player.playerUI.mypanel5.add(player.playerUI.rand12_8);
		     player.playerUI.mypanel5.add(player.playerUI.rand12_9);
		     player.playerUI.mypanel5.add(player.playerUI.rand12_10);
		     player.playerUI.mypanel5.add(player.playerUI.rand12_11);
		     player.playerUI.mypanel5.add(player.playerUI.rand12_12);
		     player.playerUI.mypanel5.add(player.playerUI.rand12_13);
		     player.playerUI.mypanel5.add(player.playerUI.rand12_14);
		     player.playerUI.mypanel5.add(player.playerUI.rand12_15);
		     player.playerUI.mypanel5.add(player.playerUI.rand12_16);
		  
		  }
		  else if(tile == 6)
		  {
  	        iconPlayer1 =  (bp[4][8].getIcon());
	        iconPlayer2 =  (bp[5][8].getIcon());
 	        iconPlayer3 =  (bp[6][8].getIcon());
	        iconPlayer4 =  (bp[7][8].getIcon());
	        iconPlayer5 =  (bp[4][9].getIcon());
	        iconPlayer6 =  (bp[5][9].getIcon());
	        iconPlayer7 =  (bp[6][9].getIcon());
	        iconPlayer8 =  (bp[7][9].getIcon());
	        iconPlayer9 =  (bp[4][10].getIcon());
	        iconPlayer10 =  (bp[5][10].getIcon());
	        iconPlayer11 =  (bp[6][10].getIcon());
	        iconPlayer12 =  (bp[7][10].getIcon());
	        iconPlayer13 =  (bp[4][11].getIcon());
	        iconPlayer14 =  (bp[5][11].getIcon());
	        iconPlayer15 =  (bp[6][11].getIcon());
	        iconPlayer16 =  (bp[7][11].getIcon());

	
		//add components to board
			  GameJLabel boardPlayer1 = new GameJLabel(iconPlayer1);
			  GameJLabel boardPlayer2 = new GameJLabel(iconPlayer2);
			  GameJLabel boardPlayer3 = new GameJLabel(iconPlayer3);
			  GameJLabel boardPlayer4 = new GameJLabel(iconPlayer4);
			  GameJLabel boardPlayer5 = new GameJLabel(iconPlayer5);
			  GameJLabel boardPlayer6 = new GameJLabel(iconPlayer6);
			  GameJLabel boardPlayer7 = new GameJLabel(iconPlayer7);
			  GameJLabel boardPlayer8 = new GameJLabel(iconPlayer8);
			  GameJLabel boardPlayer9 = new GameJLabel(iconPlayer9);
			  GameJLabel boardPlayer10 = new GameJLabel(iconPlayer10);
			  GameJLabel boardPlayer11 = new GameJLabel(iconPlayer11);
			  GameJLabel boardPlayer12 = new GameJLabel(iconPlayer12);
			  GameJLabel boardPlayer13 = new GameJLabel(iconPlayer13);
			  GameJLabel boardPlayer14 = new GameJLabel(iconPlayer14);
			  GameJLabel boardPlayer15 = new GameJLabel(iconPlayer15);
			  GameJLabel boardPlayer16 = new GameJLabel(iconPlayer16);
		
			  player.playerUI.rand12_1.add(boardPlayer1, new Integer(0));
		     player.playerUI.rand12_2.add(boardPlayer2, new Integer(0));
		     player.playerUI.rand12_3.add(boardPlayer3, new Integer(0));
		     player.playerUI.rand12_4.add(boardPlayer4, new Integer(0));
		     player.playerUI.rand12_5.add(boardPlayer5, new Integer(0));
		     player.playerUI.rand12_6.add(boardPlayer6, new Integer(0));
		     player.playerUI.rand12_7.add(boardPlayer7, new Integer(0));
		     player.playerUI.rand12_8.add(boardPlayer8, new Integer(0));
		     player.playerUI.rand12_9.add(boardPlayer9, new Integer(0));
		     player.playerUI.rand12_10.add(boardPlayer10, new Integer(0));
		     player.playerUI.rand12_11.add(boardPlayer11, new Integer(0));
		     player.playerUI.rand12_12.add(boardPlayer12, new Integer(0));
		     player.playerUI.rand12_13.add(boardPlayer13, new Integer(0));
		     player.playerUI.rand12_14.add(boardPlayer14, new Integer(0));
		     player.playerUI.rand12_15.add(boardPlayer15, new Integer(0));
		     player.playerUI.rand12_16.add(boardPlayer16, new Integer(0));
		
		     boardPlayer1.setBounds(0, 0, iconPlayer1.getIconWidth(),
		                                  iconPlayer1.getIconHeight()); 
		     player.playerUI.rand12_1.setPreferredSize(new Dimension(iconPlayer1.getIconWidth(),
		                                        iconPlayer1.getIconHeight()));
		     boardPlayer2.setBounds(0, 0, iconPlayer2.getIconWidth(),
		                                  iconPlayer2.getIconHeight()); 
		     player.playerUI.rand12_2.setPreferredSize(new Dimension(iconPlayer2.getIconWidth(),
		                                        iconPlayer2.getIconHeight()));
		     boardPlayer3.setBounds(0, 0, iconPlayer3.getIconWidth(),
		                                  iconPlayer3.getIconHeight()); 
		     player.playerUI.rand12_3.setPreferredSize(new Dimension(iconPlayer3.getIconWidth(),
		                                        iconPlayer3.getIconHeight()));
		     boardPlayer4.setBounds(0, 0, iconPlayer4.getIconWidth(),
		                                  iconPlayer4.getIconHeight()); 
		     player.playerUI.rand12_4.setPreferredSize(new Dimension(iconPlayer4.getIconWidth(),
		                                        iconPlayer4.getIconHeight()));
		     boardPlayer5.setBounds(0, 0, iconPlayer5.getIconWidth(),
		                                  iconPlayer5.getIconHeight()); 
		     player.playerUI.rand12_5.setPreferredSize(new Dimension(iconPlayer5.getIconWidth(),
		                                        iconPlayer5.getIconHeight()));
		     boardPlayer6.setBounds(0, 0, iconPlayer6.getIconWidth(),
		                                  iconPlayer6.getIconHeight()); 
		     player.playerUI.rand12_6.setPreferredSize(new Dimension(iconPlayer6.getIconWidth(),
		                                        iconPlayer6.getIconHeight()));
		     boardPlayer7.setBounds(0, 0, iconPlayer7.getIconWidth(),
		                                  iconPlayer7.getIconHeight()); 
		     player.playerUI.rand12_7.setPreferredSize(new Dimension(iconPlayer7.getIconWidth(),
		                                        iconPlayer7.getIconHeight()));
		     boardPlayer8.setBounds(0, 0, iconPlayer8.getIconWidth(),
		                                  iconPlayer8.getIconHeight()); 
		     player.playerUI.rand12_8.setPreferredSize(new Dimension(iconPlayer8.getIconWidth(),
		                                        iconPlayer8.getIconHeight()));
		     boardPlayer9.setBounds(0, 0, iconPlayer9.getIconWidth(),
		                                  iconPlayer9.getIconHeight()); 
		     player.playerUI.rand12_9.setPreferredSize(new Dimension(iconPlayer9.getIconWidth(),
		                                        iconPlayer9.getIconHeight()));
		     boardPlayer10.setBounds(0, 0, iconPlayer10.getIconWidth(),
		                                  iconPlayer10.getIconHeight()); 
		     player.playerUI.rand12_10.setPreferredSize(new Dimension(iconPlayer10.getIconWidth(),
		                                        iconPlayer10.getIconHeight()));													 													 
		     boardPlayer11.setBounds(0, 0, iconPlayer11.getIconWidth(),
		                                  iconPlayer11.getIconHeight()); 
		     player.playerUI.rand12_11.setPreferredSize(new Dimension(iconPlayer11.getIconWidth(),
		                                        iconPlayer11.getIconHeight()));
		     boardPlayer12.setBounds(0, 0, iconPlayer12.getIconWidth(),
		                                  iconPlayer12.getIconHeight()); 
		     player.playerUI.rand12_12.setPreferredSize(new Dimension(iconPlayer12.getIconWidth(),
		                                        iconPlayer12.getIconHeight()));
		     boardPlayer13.setBounds(0, 0, iconPlayer13.getIconWidth(),
		                                  iconPlayer13.getIconHeight()); 
		     player.playerUI.rand12_13.setPreferredSize(new Dimension(iconPlayer13.getIconWidth(),
		                                        iconPlayer13.getIconHeight()));
		     boardPlayer14.setBounds(0, 0, iconPlayer14.getIconWidth(),
		                                  iconPlayer14.getIconHeight()); 
		     player.playerUI.rand12_14.setPreferredSize(new Dimension(iconPlayer14.getIconWidth(),
		                                        iconPlayer14.getIconHeight()));
		     boardPlayer15.setBounds(0, 0, iconPlayer15.getIconWidth(),
		                                  iconPlayer15.getIconHeight()); 
		     player.playerUI.rand12_15.setPreferredSize(new Dimension(iconPlayer15.getIconWidth(),
		                                        iconPlayer15.getIconHeight()));	
		     boardPlayer16.setBounds(0, 0, iconPlayer16.getIconWidth(),
		                                  iconPlayer16.getIconHeight()); 
		     player.playerUI.rand12_16.setPreferredSize(new Dimension(iconPlayer16.getIconWidth(),
		                                        iconPlayer16.getIconHeight()));													 												 													 
		
			  player.playerUI.mypanel6.add(player.playerUI.rand12_1);
		     player.playerUI.mypanel6.add(player.playerUI.rand12_2);
		     player.playerUI.mypanel6.add(player.playerUI.rand12_3);
		     player.playerUI.mypanel6.add(player.playerUI.rand12_4);
		     player.playerUI.mypanel6.add(player.playerUI.rand12_5);
		     player.playerUI.mypanel6.add(player.playerUI.rand12_6);
		     player.playerUI.mypanel6.add(player.playerUI.rand12_7);
		     player.playerUI.mypanel6.add(player.playerUI.rand12_8);
		     player.playerUI.mypanel6.add(player.playerUI.rand12_9);
		     player.playerUI.mypanel6.add(player.playerUI.rand12_10);
		     player.playerUI.mypanel6.add(player.playerUI.rand12_11);
		     player.playerUI.mypanel6.add(player.playerUI.rand12_12);
		     player.playerUI.mypanel6.add(player.playerUI.rand12_13);
		     player.playerUI.mypanel6.add(player.playerUI.rand12_14);
		     player.playerUI.mypanel6.add(player.playerUI.rand12_15);
		     player.playerUI.mypanel6.add(player.playerUI.rand12_16);
		  
		  }
		  else if(tile == 7)
		  {
	       iconPlayer1 =  bp[4][12].getIcon();
	       iconPlayer2 =  bp[5][12].getIcon();
	       iconPlayer3 =  bp[6][12].getIcon();
	       iconPlayer4 =  bp[7][12].getIcon();
	       iconPlayer5 =  bp[4][13].getIcon();
	       iconPlayer6 =  bp[5][13].getIcon();
	       iconPlayer7 =  bp[6][13].getIcon();
	       iconPlayer8 =  bp[7][13].getIcon();
	       iconPlayer9 =  bp[4][14].getIcon();
	       iconPlayer10 =  bp[5][14].getIcon();
	       iconPlayer11 =  bp[6][14].getIcon();
	       iconPlayer12 =  bp[7][14].getIcon();
	       iconPlayer13 =  bp[4][15].getIcon();
	       iconPlayer14 =  bp[5][15].getIcon();
	       iconPlayer15 =  bp[6][15].getIcon();
	       iconPlayer16 =  bp[7][15].getIcon();


		//add components to board
			  GameJLabel boardPlayer1 = new GameJLabel(iconPlayer1);
			  GameJLabel boardPlayer2 = new GameJLabel(iconPlayer2);
			  GameJLabel boardPlayer3 = new GameJLabel(iconPlayer3);
			  GameJLabel boardPlayer4 = new GameJLabel(iconPlayer4);
			  GameJLabel boardPlayer5 = new GameJLabel(iconPlayer5);
			  GameJLabel boardPlayer6 = new GameJLabel(iconPlayer6);
			  GameJLabel boardPlayer7 = new GameJLabel(iconPlayer7);
			  GameJLabel boardPlayer8 = new GameJLabel(iconPlayer8);
			  GameJLabel boardPlayer9 = new GameJLabel(iconPlayer9);
			  GameJLabel boardPlayer10 = new GameJLabel(iconPlayer10);
			  GameJLabel boardPlayer11 = new GameJLabel(iconPlayer11);
			  GameJLabel boardPlayer12 = new GameJLabel(iconPlayer12);
			  GameJLabel boardPlayer13 = new GameJLabel(iconPlayer13);
			  GameJLabel boardPlayer14 = new GameJLabel(iconPlayer14);
			  GameJLabel boardPlayer15 = new GameJLabel(iconPlayer15);
			  GameJLabel boardPlayer16 = new GameJLabel(iconPlayer16);
		
			  player.playerUI.rand12_1.add(boardPlayer1, new Integer(0));
		     player.playerUI.rand12_2.add(boardPlayer2, new Integer(0));
		     player.playerUI.rand12_3.add(boardPlayer3, new Integer(0));
		     player.playerUI.rand12_4.add(boardPlayer4, new Integer(0));
		     player.playerUI.rand12_5.add(boardPlayer5, new Integer(0));
		     player.playerUI.rand12_6.add(boardPlayer6, new Integer(0));
		     player.playerUI.rand12_7.add(boardPlayer7, new Integer(0));
		     player.playerUI.rand12_8.add(boardPlayer8, new Integer(0));
		     player.playerUI.rand12_9.add(boardPlayer9, new Integer(0));
		     player.playerUI.rand12_10.add(boardPlayer10, new Integer(0));
		     player.playerUI.rand12_11.add(boardPlayer11, new Integer(0));
		     player.playerUI.rand12_12.add(boardPlayer12, new Integer(0));
		     player.playerUI.rand12_13.add(boardPlayer13, new Integer(0));
		     player.playerUI.rand12_14.add(boardPlayer14, new Integer(0));
		     player.playerUI.rand12_15.add(boardPlayer15, new Integer(0));
		     player.playerUI.rand12_16.add(boardPlayer16, new Integer(0));
		
		     boardPlayer1.setBounds(0, 0, iconPlayer1.getIconWidth(),
		                                  iconPlayer1.getIconHeight()); 
		     player.playerUI.rand12_1.setPreferredSize(new Dimension(iconPlayer1.getIconWidth(),
		                                        iconPlayer1.getIconHeight()));
		     boardPlayer2.setBounds(0, 0, iconPlayer2.getIconWidth(),
		                                  iconPlayer2.getIconHeight()); 
		     player.playerUI.rand12_2.setPreferredSize(new Dimension(iconPlayer2.getIconWidth(),
		                                        iconPlayer2.getIconHeight()));
		     boardPlayer3.setBounds(0, 0, iconPlayer3.getIconWidth(),
		                                  iconPlayer3.getIconHeight()); 
		     player.playerUI.rand12_3.setPreferredSize(new Dimension(iconPlayer3.getIconWidth(),
		                                        iconPlayer3.getIconHeight()));
		     boardPlayer4.setBounds(0, 0, iconPlayer4.getIconWidth(),
		                                  iconPlayer4.getIconHeight()); 
		     player.playerUI.rand12_4.setPreferredSize(new Dimension(iconPlayer4.getIconWidth(),
		                                        iconPlayer4.getIconHeight()));
		     boardPlayer5.setBounds(0, 0, iconPlayer5.getIconWidth(),
		                                  iconPlayer5.getIconHeight()); 
		     player.playerUI.rand12_5.setPreferredSize(new Dimension(iconPlayer5.getIconWidth(),
		                                        iconPlayer5.getIconHeight()));
		     boardPlayer6.setBounds(0, 0, iconPlayer6.getIconWidth(),
		                                  iconPlayer6.getIconHeight()); 
		     player.playerUI.rand12_6.setPreferredSize(new Dimension(iconPlayer6.getIconWidth(),
		                                        iconPlayer6.getIconHeight()));
		     boardPlayer7.setBounds(0, 0, iconPlayer7.getIconWidth(),
		                                  iconPlayer7.getIconHeight()); 
		     player.playerUI.rand12_7.setPreferredSize(new Dimension(iconPlayer7.getIconWidth(),
		                                        iconPlayer7.getIconHeight()));
		     boardPlayer8.setBounds(0, 0, iconPlayer8.getIconWidth(),
		                                  iconPlayer8.getIconHeight()); 
		     player.playerUI.rand12_8.setPreferredSize(new Dimension(iconPlayer8.getIconWidth(),
		                                        iconPlayer8.getIconHeight()));
		     boardPlayer9.setBounds(0, 0, iconPlayer9.getIconWidth(),
		                                  iconPlayer9.getIconHeight()); 
		     player.playerUI.rand12_9.setPreferredSize(new Dimension(iconPlayer9.getIconWidth(),
		                                        iconPlayer9.getIconHeight()));
		     boardPlayer10.setBounds(0, 0, iconPlayer10.getIconWidth(),
		                                  iconPlayer10.getIconHeight()); 
		     player.playerUI.rand12_10.setPreferredSize(new Dimension(iconPlayer10.getIconWidth(),
		                                        iconPlayer10.getIconHeight()));													 													 
		     boardPlayer11.setBounds(0, 0, iconPlayer11.getIconWidth(),
		                                  iconPlayer11.getIconHeight()); 
		     player.playerUI.rand12_11.setPreferredSize(new Dimension(iconPlayer11.getIconWidth(),
		                                        iconPlayer11.getIconHeight()));
		     boardPlayer12.setBounds(0, 0, iconPlayer12.getIconWidth(),
		                                  iconPlayer12.getIconHeight()); 
		     player.playerUI.rand12_12.setPreferredSize(new Dimension(iconPlayer12.getIconWidth(),
		                                        iconPlayer12.getIconHeight()));
		     boardPlayer13.setBounds(0, 0, iconPlayer13.getIconWidth(),
		                                  iconPlayer13.getIconHeight()); 
		     player.playerUI.rand12_13.setPreferredSize(new Dimension(iconPlayer13.getIconWidth(),
		                                        iconPlayer13.getIconHeight()));
		     boardPlayer14.setBounds(0, 0, iconPlayer14.getIconWidth(),
		                                  iconPlayer14.getIconHeight()); 
		     player.playerUI.rand12_14.setPreferredSize(new Dimension(iconPlayer14.getIconWidth(),
		                                        iconPlayer14.getIconHeight()));
		     boardPlayer15.setBounds(0, 0, iconPlayer15.getIconWidth(),
		                                  iconPlayer15.getIconHeight()); 
		     player.playerUI.rand12_15.setPreferredSize(new Dimension(iconPlayer15.getIconWidth(),
		                                        iconPlayer15.getIconHeight()));	
		     boardPlayer16.setBounds(0, 0, iconPlayer16.getIconWidth(),
		                                  iconPlayer16.getIconHeight()); 
		     player.playerUI.rand12_16.setPreferredSize(new Dimension(iconPlayer16.getIconWidth(),
		                                        iconPlayer16.getIconHeight()));													 												 													 
		
			  player.playerUI.mypanel7.add(player.playerUI.rand12_1);
		     player.playerUI.mypanel7.add(player.playerUI.rand12_2);
		     player.playerUI.mypanel7.add(player.playerUI.rand12_3);
		     player.playerUI.mypanel7.add(player.playerUI.rand12_4);
		     player.playerUI.mypanel7.add(player.playerUI.rand12_5);
		     player.playerUI.mypanel7.add(player.playerUI.rand12_6);
		     player.playerUI.mypanel7.add(player.playerUI.rand12_7);
		     player.playerUI.mypanel7.add(player.playerUI.rand12_8);
		     player.playerUI.mypanel7.add(player.playerUI.rand12_9);
		     player.playerUI.mypanel7.add(player.playerUI.rand12_10);
		     player.playerUI.mypanel7.add(player.playerUI.rand12_11);
		     player.playerUI.mypanel7.add(player.playerUI.rand12_12);
		     player.playerUI.mypanel7.add(player.playerUI.rand12_13);
		     player.playerUI.mypanel7.add(player.playerUI.rand12_14);
		     player.playerUI.mypanel7.add(player.playerUI.rand12_15);
		     player.playerUI.mypanel7.add(player.playerUI.rand12_16);
		  
		  }
		}

      private void populateRand13(int tile, Player player)
		{
		  if(tile == 0)
		  {
			  iconPlayer1 =  (bp[0][0].getIcon());
	        iconPlayer2 =  (bp[1][0].getIcon());
 	        iconPlayer3 =  (bp[2][0].getIcon());
	        iconPlayer4 =  (bp[3][0].getIcon());
	        iconPlayer5 =  (bp[0][1].getIcon());
	        iconPlayer6 =  (bp[1][1].getIcon());
	        iconPlayer7 =  (bp[2][1].getIcon());
	        iconPlayer8 =  (bp[3][1].getIcon());
	        iconPlayer9 =  (bp[0][2].getIcon());
	        iconPlayer10 =  (bp[1][2].getIcon());
	        iconPlayer11 =  (bp[2][2].getIcon());
	        iconPlayer12 =  (bp[3][2].getIcon());
	        iconPlayer13 =  (bp[0][3].getIcon());
	        iconPlayer14 =  (bp[1][3].getIcon());
	        iconPlayer15 =  (bp[2][3].getIcon());
	        iconPlayer16 =  (bp[3][3].getIcon());

	
		//add components to board
			  GameJLabel boardPlayer1 = new GameJLabel(iconPlayer1);
			  GameJLabel boardPlayer2 = new GameJLabel(iconPlayer2);
			  GameJLabel boardPlayer3 = new GameJLabel(iconPlayer3);
			  GameJLabel boardPlayer4 = new GameJLabel(iconPlayer4);
			  GameJLabel boardPlayer5 = new GameJLabel(iconPlayer5);
			  GameJLabel boardPlayer6 = new GameJLabel(iconPlayer6);
			  GameJLabel boardPlayer7 = new GameJLabel(iconPlayer7);
			  GameJLabel boardPlayer8 = new GameJLabel(iconPlayer8);
			  GameJLabel boardPlayer9 = new GameJLabel(iconPlayer9);
			  GameJLabel boardPlayer10 = new GameJLabel(iconPlayer10);
			  GameJLabel boardPlayer11 = new GameJLabel(iconPlayer11);
			  GameJLabel boardPlayer12 = new GameJLabel(iconPlayer12);
			  GameJLabel boardPlayer13 = new GameJLabel(iconPlayer13);
			  GameJLabel boardPlayer14 = new GameJLabel(iconPlayer14);
			  GameJLabel boardPlayer15 = new GameJLabel(iconPlayer15);
			  GameJLabel boardPlayer16 = new GameJLabel(iconPlayer16);
		
			  player.playerUI.rand13_1.add(boardPlayer1, new Integer(0));
		     player.playerUI.rand13_2.add(boardPlayer2, new Integer(0));
		     player.playerUI.rand13_3.add(boardPlayer3, new Integer(0));
		     player.playerUI.rand13_4.add(boardPlayer4, new Integer(0));
		     player.playerUI.rand13_5.add(boardPlayer5, new Integer(0));
		     player.playerUI.rand13_6.add(boardPlayer6, new Integer(0));
		     player.playerUI.rand13_7.add(boardPlayer7, new Integer(0));
		     player.playerUI.rand13_8.add(boardPlayer8, new Integer(0));
		     player.playerUI.rand13_9.add(boardPlayer9, new Integer(0));
		     player.playerUI.rand13_10.add(boardPlayer10, new Integer(0));
		     player.playerUI.rand13_11.add(boardPlayer11, new Integer(0));
		     player.playerUI.rand13_12.add(boardPlayer12, new Integer(0));
		     player.playerUI.rand13_13.add(boardPlayer13, new Integer(0));
		     player.playerUI.rand13_14.add(boardPlayer14, new Integer(0));
		     player.playerUI.rand13_15.add(boardPlayer15, new Integer(0));
		     player.playerUI.rand13_16.add(boardPlayer16, new Integer(0));
		
		     boardPlayer1.setBounds(0, 0, iconPlayer1.getIconWidth(),
		                                  iconPlayer1.getIconHeight()); 
		     player.playerUI.rand13_1.setPreferredSize(new Dimension(iconPlayer1.getIconWidth(),
		                                        iconPlayer1.getIconHeight()));
		     boardPlayer2.setBounds(0, 0, iconPlayer2.getIconWidth(),
		                                  iconPlayer2.getIconHeight()); 
		     player.playerUI.rand13_2.setPreferredSize(new Dimension(iconPlayer2.getIconWidth(),
		                                        iconPlayer2.getIconHeight()));
		     boardPlayer3.setBounds(0, 0, iconPlayer3.getIconWidth(),
		                                  iconPlayer3.getIconHeight()); 
		     player.playerUI.rand13_3.setPreferredSize(new Dimension(iconPlayer3.getIconWidth(),
		                                        iconPlayer3.getIconHeight()));
		     boardPlayer4.setBounds(0, 0, iconPlayer4.getIconWidth(),
		                                  iconPlayer4.getIconHeight()); 
		     player.playerUI.rand13_4.setPreferredSize(new Dimension(iconPlayer4.getIconWidth(),
		                                        iconPlayer4.getIconHeight()));
		     boardPlayer5.setBounds(0, 0, iconPlayer5.getIconWidth(),
		                                  iconPlayer5.getIconHeight()); 
		     player.playerUI.rand13_5.setPreferredSize(new Dimension(iconPlayer5.getIconWidth(),
		                                        iconPlayer5.getIconHeight()));
		     boardPlayer6.setBounds(0, 0, iconPlayer6.getIconWidth(),
		                                  iconPlayer6.getIconHeight()); 
		     player.playerUI.rand13_6.setPreferredSize(new Dimension(iconPlayer6.getIconWidth(),
		                                        iconPlayer6.getIconHeight()));
		     boardPlayer7.setBounds(0, 0, iconPlayer7.getIconWidth(),
		                                  iconPlayer7.getIconHeight()); 
		     player.playerUI.rand13_7.setPreferredSize(new Dimension(iconPlayer7.getIconWidth(),
		                                        iconPlayer7.getIconHeight()));
		     boardPlayer8.setBounds(0, 0, iconPlayer8.getIconWidth(),
		                                  iconPlayer8.getIconHeight()); 
		     player.playerUI.rand13_8.setPreferredSize(new Dimension(iconPlayer8.getIconWidth(),
		                                        iconPlayer8.getIconHeight()));
		     boardPlayer9.setBounds(0, 0, iconPlayer9.getIconWidth(),
		                                  iconPlayer9.getIconHeight()); 
		     player.playerUI.rand13_9.setPreferredSize(new Dimension(iconPlayer9.getIconWidth(),
		                                        iconPlayer9.getIconHeight()));
		     boardPlayer10.setBounds(0, 0, iconPlayer10.getIconWidth(),
		                                  iconPlayer10.getIconHeight()); 
		     player.playerUI.rand13_10.setPreferredSize(new Dimension(iconPlayer10.getIconWidth(),
		                                        iconPlayer10.getIconHeight()));													 													 
		     boardPlayer11.setBounds(0, 0, iconPlayer11.getIconWidth(),
		                                  iconPlayer11.getIconHeight()); 
		     player.playerUI.rand13_11.setPreferredSize(new Dimension(iconPlayer11.getIconWidth(),
		                                        iconPlayer11.getIconHeight()));
		     boardPlayer12.setBounds(0, 0, iconPlayer12.getIconWidth(),
		                                  iconPlayer12.getIconHeight()); 
		     player.playerUI.rand13_12.setPreferredSize(new Dimension(iconPlayer12.getIconWidth(),
		                                        iconPlayer12.getIconHeight()));
		     boardPlayer13.setBounds(0, 0, iconPlayer13.getIconWidth(),
		                                  iconPlayer13.getIconHeight()); 
		     player.playerUI.rand13_13.setPreferredSize(new Dimension(iconPlayer13.getIconWidth(),
		                                        iconPlayer13.getIconHeight()));
		     boardPlayer14.setBounds(0, 0, iconPlayer14.getIconWidth(),
		                                  iconPlayer14.getIconHeight()); 
		     player.playerUI.rand13_14.setPreferredSize(new Dimension(iconPlayer14.getIconWidth(),
		                                        iconPlayer14.getIconHeight()));
		     boardPlayer15.setBounds(0, 0, iconPlayer15.getIconWidth(),
		                                  iconPlayer15.getIconHeight()); 
		     player.playerUI.rand13_15.setPreferredSize(new Dimension(iconPlayer15.getIconWidth(),
		                                        iconPlayer15.getIconHeight()));	
		     boardPlayer16.setBounds(0, 0, iconPlayer16.getIconWidth(),
		                                  iconPlayer16.getIconHeight()); 
		     player.playerUI.rand13_16.setPreferredSize(new Dimension(iconPlayer16.getIconWidth(),
		                                        iconPlayer16.getIconHeight()));													 												 													 
		
			  player.playerUI.mypanel0.add(player.playerUI.rand13_1);
		     player.playerUI.mypanel0.add(player.playerUI.rand13_2);
		     player.playerUI.mypanel0.add(player.playerUI.rand13_3);
		     player.playerUI.mypanel0.add(player.playerUI.rand13_4);
		     player.playerUI.mypanel0.add(player.playerUI.rand13_5);
		     player.playerUI.mypanel0.add(player.playerUI.rand13_6);
		     player.playerUI.mypanel0.add(player.playerUI.rand13_7);
		     player.playerUI.mypanel0.add(player.playerUI.rand13_8);
		     player.playerUI.mypanel0.add(player.playerUI.rand13_9);
		     player.playerUI.mypanel0.add(player.playerUI.rand13_10);
		     player.playerUI.mypanel0.add(player.playerUI.rand13_11);
		     player.playerUI.mypanel0.add(player.playerUI.rand13_12);
		     player.playerUI.mypanel0.add(player.playerUI.rand13_13);
		     player.playerUI.mypanel0.add(player.playerUI.rand13_14);
		     player.playerUI.mypanel0.add(player.playerUI.rand13_15);
		     player.playerUI.mypanel0.add(player.playerUI.rand13_16);
		  }
		  else if(tile == 1)
		  {
  	        iconPlayer1 =  (bp[0][4].getIcon());
	        iconPlayer2 =  (bp[1][4].getIcon());
 	        iconPlayer3 =  (bp[2][4].getIcon());
	        iconPlayer4 =  (bp[3][4].getIcon());
	        iconPlayer5 =  (bp[0][5].getIcon());
	        iconPlayer6 =  (bp[1][5].getIcon());
	        iconPlayer7 =  (bp[2][5].getIcon());
	        iconPlayer8 =  (bp[3][5].getIcon());
	        iconPlayer9 =  (bp[0][6].getIcon());
	        iconPlayer10 =  (bp[1][6].getIcon());
	        iconPlayer11 =  (bp[2][6].getIcon());
	        iconPlayer12 =  (bp[3][6].getIcon());
	        iconPlayer13 =  (bp[0][7].getIcon());
	        iconPlayer14 =  (bp[1][7].getIcon());
	        iconPlayer15 =  (bp[2][7].getIcon());
	        iconPlayer16 =  (bp[3][7].getIcon());

	
		//add components to board
			  GameJLabel boardPlayer1 = new GameJLabel(iconPlayer1);
			  GameJLabel boardPlayer2 = new GameJLabel(iconPlayer2);
			  GameJLabel boardPlayer3 = new GameJLabel(iconPlayer3);
			  GameJLabel boardPlayer4 = new GameJLabel(iconPlayer4);
			  GameJLabel boardPlayer5 = new GameJLabel(iconPlayer5);
			  GameJLabel boardPlayer6 = new GameJLabel(iconPlayer6);
			  GameJLabel boardPlayer7 = new GameJLabel(iconPlayer7);
			  GameJLabel boardPlayer8 = new GameJLabel(iconPlayer8);
			  GameJLabel boardPlayer9 = new GameJLabel(iconPlayer9);
			  GameJLabel boardPlayer10 = new GameJLabel(iconPlayer10);
			  GameJLabel boardPlayer11 = new GameJLabel(iconPlayer11);
			  GameJLabel boardPlayer12 = new GameJLabel(iconPlayer12);
			  GameJLabel boardPlayer13 = new GameJLabel(iconPlayer13);
			  GameJLabel boardPlayer14 = new GameJLabel(iconPlayer14);
			  GameJLabel boardPlayer15 = new GameJLabel(iconPlayer15);
			  GameJLabel boardPlayer16 = new GameJLabel(iconPlayer16);
		
			  player.playerUI.rand13_1.add(boardPlayer1, new Integer(0));
		     player.playerUI.rand13_2.add(boardPlayer2, new Integer(0));
		     player.playerUI.rand13_3.add(boardPlayer3, new Integer(0));
		     player.playerUI.rand13_4.add(boardPlayer4, new Integer(0));
		     player.playerUI.rand13_5.add(boardPlayer5, new Integer(0));
		     player.playerUI.rand13_6.add(boardPlayer6, new Integer(0));
		     player.playerUI.rand13_7.add(boardPlayer7, new Integer(0));
		     player.playerUI.rand13_8.add(boardPlayer8, new Integer(0));
		     player.playerUI.rand13_9.add(boardPlayer9, new Integer(0));
		     player.playerUI.rand13_10.add(boardPlayer10, new Integer(0));
		     player.playerUI.rand13_11.add(boardPlayer11, new Integer(0));
		     player.playerUI.rand13_12.add(boardPlayer12, new Integer(0));
		     player.playerUI.rand13_13.add(boardPlayer13, new Integer(0));
		     player.playerUI.rand13_14.add(boardPlayer14, new Integer(0));
		     player.playerUI.rand13_15.add(boardPlayer15, new Integer(0));
		     player.playerUI.rand13_16.add(boardPlayer16, new Integer(0));
		
		     boardPlayer1.setBounds(0, 0, iconPlayer1.getIconWidth(),
		                                  iconPlayer1.getIconHeight()); 
		     player.playerUI.rand13_1.setPreferredSize(new Dimension(iconPlayer1.getIconWidth(),
		                                        iconPlayer1.getIconHeight()));
		     boardPlayer2.setBounds(0, 0, iconPlayer2.getIconWidth(),
		                                  iconPlayer2.getIconHeight()); 
		     player.playerUI.rand13_2.setPreferredSize(new Dimension(iconPlayer2.getIconWidth(),
		                                        iconPlayer2.getIconHeight()));
		     boardPlayer3.setBounds(0, 0, iconPlayer3.getIconWidth(),
		                                  iconPlayer3.getIconHeight()); 
		     player.playerUI.rand13_3.setPreferredSize(new Dimension(iconPlayer3.getIconWidth(),
		                                        iconPlayer3.getIconHeight()));
		     boardPlayer4.setBounds(0, 0, iconPlayer4.getIconWidth(),
		                                  iconPlayer4.getIconHeight()); 
		     player.playerUI.rand13_4.setPreferredSize(new Dimension(iconPlayer4.getIconWidth(),
		                                        iconPlayer4.getIconHeight()));
		     boardPlayer5.setBounds(0, 0, iconPlayer5.getIconWidth(),
		                                  iconPlayer5.getIconHeight()); 
		     player.playerUI.rand13_5.setPreferredSize(new Dimension(iconPlayer5.getIconWidth(),
		                                        iconPlayer5.getIconHeight()));
		     boardPlayer6.setBounds(0, 0, iconPlayer6.getIconWidth(),
		                                  iconPlayer6.getIconHeight()); 
		     player.playerUI.rand13_6.setPreferredSize(new Dimension(iconPlayer6.getIconWidth(),
		                                        iconPlayer6.getIconHeight()));
		     boardPlayer7.setBounds(0, 0, iconPlayer7.getIconWidth(),
		                                  iconPlayer7.getIconHeight()); 
		     player.playerUI.rand13_7.setPreferredSize(new Dimension(iconPlayer7.getIconWidth(),
		                                        iconPlayer7.getIconHeight()));
		     boardPlayer8.setBounds(0, 0, iconPlayer8.getIconWidth(),
		                                  iconPlayer8.getIconHeight()); 
		     player.playerUI.rand13_8.setPreferredSize(new Dimension(iconPlayer8.getIconWidth(),
		                                        iconPlayer8.getIconHeight()));
		     boardPlayer9.setBounds(0, 0, iconPlayer9.getIconWidth(),
		                                  iconPlayer9.getIconHeight()); 
		     player.playerUI.rand13_9.setPreferredSize(new Dimension(iconPlayer9.getIconWidth(),
		                                        iconPlayer9.getIconHeight()));
		     boardPlayer10.setBounds(0, 0, iconPlayer10.getIconWidth(),
		                                  iconPlayer10.getIconHeight()); 
		     player.playerUI.rand13_10.setPreferredSize(new Dimension(iconPlayer10.getIconWidth(),
		                                        iconPlayer10.getIconHeight()));													 													 
		     boardPlayer11.setBounds(0, 0, iconPlayer11.getIconWidth(),
		                                  iconPlayer11.getIconHeight()); 
		     player.playerUI.rand13_11.setPreferredSize(new Dimension(iconPlayer11.getIconWidth(),
		                                        iconPlayer11.getIconHeight()));
		     boardPlayer12.setBounds(0, 0, iconPlayer12.getIconWidth(),
		                                  iconPlayer12.getIconHeight()); 
		     player.playerUI.rand13_12.setPreferredSize(new Dimension(iconPlayer12.getIconWidth(),
		                                        iconPlayer12.getIconHeight()));
		     boardPlayer13.setBounds(0, 0, iconPlayer13.getIconWidth(),
		                                  iconPlayer13.getIconHeight()); 
		     player.playerUI.rand13_13.setPreferredSize(new Dimension(iconPlayer13.getIconWidth(),
		                                        iconPlayer13.getIconHeight()));
		     boardPlayer14.setBounds(0, 0, iconPlayer14.getIconWidth(),
		                                  iconPlayer14.getIconHeight()); 
		     player.playerUI.rand13_14.setPreferredSize(new Dimension(iconPlayer14.getIconWidth(),
		                                        iconPlayer14.getIconHeight()));
		     boardPlayer15.setBounds(0, 0, iconPlayer15.getIconWidth(),
		                                  iconPlayer15.getIconHeight()); 
		     player.playerUI.rand13_15.setPreferredSize(new Dimension(iconPlayer15.getIconWidth(),
		                                        iconPlayer15.getIconHeight()));	
		     boardPlayer16.setBounds(0, 0, iconPlayer16.getIconWidth(),
		                                  iconPlayer16.getIconHeight()); 
		     player.playerUI.rand13_16.setPreferredSize(new Dimension(iconPlayer16.getIconWidth(),
		                                        iconPlayer16.getIconHeight()));													 												 													 
		
			  player.playerUI.mypanel1.add(player.playerUI.rand13_1);
		     player.playerUI.mypanel1.add(player.playerUI.rand13_2);
		     player.playerUI.mypanel1.add(player.playerUI.rand13_3);
		     player.playerUI.mypanel1.add(player.playerUI.rand13_4);
		     player.playerUI.mypanel1.add(player.playerUI.rand13_5);
		     player.playerUI.mypanel1.add(player.playerUI.rand13_6);
		     player.playerUI.mypanel1.add(player.playerUI.rand13_7);
		     player.playerUI.mypanel1.add(player.playerUI.rand13_8);
		     player.playerUI.mypanel1.add(player.playerUI.rand13_9);
		     player.playerUI.mypanel1.add(player.playerUI.rand13_10);
		     player.playerUI.mypanel1.add(player.playerUI.rand13_11);
		     player.playerUI.mypanel1.add(player.playerUI.rand13_12);
		     player.playerUI.mypanel1.add(player.playerUI.rand13_13);
		     player.playerUI.mypanel1.add(player.playerUI.rand13_14);
		     player.playerUI.mypanel1.add(player.playerUI.rand13_15);
		     player.playerUI.mypanel1.add(player.playerUI.rand13_16);
		  
		  }
		  else if(tile == 2)
		  {
  	        iconPlayer1 =  (bp[0][8].getIcon());
	        iconPlayer2 =  (bp[1][8].getIcon());
 	        iconPlayer3 =  (bp[2][8].getIcon());
	        iconPlayer4 =  (bp[3][8].getIcon());
	        iconPlayer5 =  (bp[0][9].getIcon());
	        iconPlayer6 =  (bp[1][9].getIcon());
	        iconPlayer7 =  (bp[2][9].getIcon());
	        iconPlayer8 =  (bp[3][9].getIcon());
	        iconPlayer9 =  (bp[0][10].getIcon());
	        iconPlayer10 =  (bp[1][10].getIcon());
	        iconPlayer11 =  (bp[2][10].getIcon());
	        iconPlayer12 =  (bp[3][10].getIcon());
	        iconPlayer13 =  (bp[0][11].getIcon());
	        iconPlayer14 =  (bp[1][11].getIcon());
	        iconPlayer15 =  (bp[2][11].getIcon());
	        iconPlayer16 =  (bp[3][11].getIcon());

	
		//add components to board
			  GameJLabel boardPlayer1 = new GameJLabel(iconPlayer1);
			  GameJLabel boardPlayer2 = new GameJLabel(iconPlayer2);
			  GameJLabel boardPlayer3 = new GameJLabel(iconPlayer3);
			  GameJLabel boardPlayer4 = new GameJLabel(iconPlayer4);
			  GameJLabel boardPlayer5 = new GameJLabel(iconPlayer5);
			  GameJLabel boardPlayer6 = new GameJLabel(iconPlayer6);
			  GameJLabel boardPlayer7 = new GameJLabel(iconPlayer7);
			  GameJLabel boardPlayer8 = new GameJLabel(iconPlayer8);
			  GameJLabel boardPlayer9 = new GameJLabel(iconPlayer9);
			  GameJLabel boardPlayer10 = new GameJLabel(iconPlayer10);
			  GameJLabel boardPlayer11 = new GameJLabel(iconPlayer11);
			  GameJLabel boardPlayer12 = new GameJLabel(iconPlayer12);
			  GameJLabel boardPlayer13 = new GameJLabel(iconPlayer13);
			  GameJLabel boardPlayer14 = new GameJLabel(iconPlayer14);
			  GameJLabel boardPlayer15 = new GameJLabel(iconPlayer15);
			  GameJLabel boardPlayer16 = new GameJLabel(iconPlayer16);
		
			  player.playerUI.rand13_1.add(boardPlayer1, new Integer(0));
		     player.playerUI.rand13_2.add(boardPlayer2, new Integer(0));
		     player.playerUI.rand13_3.add(boardPlayer3, new Integer(0));
		     player.playerUI.rand13_4.add(boardPlayer4, new Integer(0));
		     player.playerUI.rand13_5.add(boardPlayer5, new Integer(0));
		     player.playerUI.rand13_6.add(boardPlayer6, new Integer(0));
		     player.playerUI.rand13_7.add(boardPlayer7, new Integer(0));
		     player.playerUI.rand13_8.add(boardPlayer8, new Integer(0));
		     player.playerUI.rand13_9.add(boardPlayer9, new Integer(0));
		     player.playerUI.rand13_10.add(boardPlayer10, new Integer(0));
		     player.playerUI.rand13_11.add(boardPlayer11, new Integer(0));
		     player.playerUI.rand13_12.add(boardPlayer12, new Integer(0));
		     player.playerUI.rand13_13.add(boardPlayer13, new Integer(0));
		     player.playerUI.rand13_14.add(boardPlayer14, new Integer(0));
		     player.playerUI.rand13_15.add(boardPlayer15, new Integer(0));
		     player.playerUI.rand13_16.add(boardPlayer16, new Integer(0));
		
		     boardPlayer1.setBounds(0, 0, iconPlayer1.getIconWidth(),
		                                  iconPlayer1.getIconHeight()); 
		     player.playerUI.rand13_1.setPreferredSize(new Dimension(iconPlayer1.getIconWidth(),
		                                        iconPlayer1.getIconHeight()));
		     boardPlayer2.setBounds(0, 0, iconPlayer2.getIconWidth(),
		                                  iconPlayer2.getIconHeight()); 
		     player.playerUI.rand13_2.setPreferredSize(new Dimension(iconPlayer2.getIconWidth(),
		                                        iconPlayer2.getIconHeight()));
		     boardPlayer3.setBounds(0, 0, iconPlayer3.getIconWidth(),
		                                  iconPlayer3.getIconHeight()); 
		     player.playerUI.rand13_3.setPreferredSize(new Dimension(iconPlayer3.getIconWidth(),
		                                        iconPlayer3.getIconHeight()));
		     boardPlayer4.setBounds(0, 0, iconPlayer4.getIconWidth(),
		                                  iconPlayer4.getIconHeight()); 
		     player.playerUI.rand13_4.setPreferredSize(new Dimension(iconPlayer4.getIconWidth(),
		                                        iconPlayer4.getIconHeight()));
		     boardPlayer5.setBounds(0, 0, iconPlayer5.getIconWidth(),
		                                  iconPlayer5.getIconHeight()); 
		     player.playerUI.rand13_5.setPreferredSize(new Dimension(iconPlayer5.getIconWidth(),
		                                        iconPlayer5.getIconHeight()));
		     boardPlayer6.setBounds(0, 0, iconPlayer6.getIconWidth(),
		                                  iconPlayer6.getIconHeight()); 
		     player.playerUI.rand13_6.setPreferredSize(new Dimension(iconPlayer6.getIconWidth(),
		                                        iconPlayer6.getIconHeight()));
		     boardPlayer7.setBounds(0, 0, iconPlayer7.getIconWidth(),
		                                  iconPlayer7.getIconHeight()); 
		     player.playerUI.rand13_7.setPreferredSize(new Dimension(iconPlayer7.getIconWidth(),
		                                        iconPlayer7.getIconHeight()));
		     boardPlayer8.setBounds(0, 0, iconPlayer8.getIconWidth(),
		                                  iconPlayer8.getIconHeight()); 
		     player.playerUI.rand13_8.setPreferredSize(new Dimension(iconPlayer8.getIconWidth(),
		                                        iconPlayer8.getIconHeight()));
		     boardPlayer9.setBounds(0, 0, iconPlayer9.getIconWidth(),
		                                  iconPlayer9.getIconHeight()); 
		     player.playerUI.rand13_9.setPreferredSize(new Dimension(iconPlayer9.getIconWidth(),
		                                        iconPlayer9.getIconHeight()));
		     boardPlayer10.setBounds(0, 0, iconPlayer10.getIconWidth(),
		                                  iconPlayer10.getIconHeight()); 
		     player.playerUI.rand13_10.setPreferredSize(new Dimension(iconPlayer10.getIconWidth(),
		                                        iconPlayer10.getIconHeight()));													 													 
		     boardPlayer11.setBounds(0, 0, iconPlayer11.getIconWidth(),
		                                  iconPlayer11.getIconHeight()); 
		     player.playerUI.rand13_11.setPreferredSize(new Dimension(iconPlayer11.getIconWidth(),
		                                        iconPlayer11.getIconHeight()));
		     boardPlayer12.setBounds(0, 0, iconPlayer12.getIconWidth(),
		                                  iconPlayer12.getIconHeight()); 
		     player.playerUI.rand13_12.setPreferredSize(new Dimension(iconPlayer12.getIconWidth(),
		                                        iconPlayer12.getIconHeight()));
		     boardPlayer13.setBounds(0, 0, iconPlayer13.getIconWidth(),
		                                  iconPlayer13.getIconHeight()); 
		     player.playerUI.rand13_13.setPreferredSize(new Dimension(iconPlayer13.getIconWidth(),
		                                        iconPlayer13.getIconHeight()));
		     boardPlayer14.setBounds(0, 0, iconPlayer14.getIconWidth(),
		                                  iconPlayer14.getIconHeight()); 
		     player.playerUI.rand13_14.setPreferredSize(new Dimension(iconPlayer14.getIconWidth(),
		                                        iconPlayer14.getIconHeight()));
		     boardPlayer15.setBounds(0, 0, iconPlayer15.getIconWidth(),
		                                  iconPlayer15.getIconHeight()); 
		     player.playerUI.rand13_15.setPreferredSize(new Dimension(iconPlayer15.getIconWidth(),
		                                        iconPlayer15.getIconHeight()));	
		     boardPlayer16.setBounds(0, 0, iconPlayer16.getIconWidth(),
		                                  iconPlayer16.getIconHeight()); 
		     player.playerUI.rand13_16.setPreferredSize(new Dimension(iconPlayer16.getIconWidth(),
		                                        iconPlayer16.getIconHeight()));													 												 													 
		
			  player.playerUI.mypanel2.add(player.playerUI.rand13_1);
		     player.playerUI.mypanel2.add(player.playerUI.rand13_2);
		     player.playerUI.mypanel2.add(player.playerUI.rand13_3);
		     player.playerUI.mypanel2.add(player.playerUI.rand13_4);
		     player.playerUI.mypanel2.add(player.playerUI.rand13_5);
		     player.playerUI.mypanel2.add(player.playerUI.rand13_6);
		     player.playerUI.mypanel2.add(player.playerUI.rand13_7);
		     player.playerUI.mypanel2.add(player.playerUI.rand13_8);
		     player.playerUI.mypanel2.add(player.playerUI.rand13_9);
		     player.playerUI.mypanel2.add(player.playerUI.rand13_10);
		     player.playerUI.mypanel2.add(player.playerUI.rand13_11);
		     player.playerUI.mypanel2.add(player.playerUI.rand13_12);
		     player.playerUI.mypanel2.add(player.playerUI.rand13_13);
		     player.playerUI.mypanel2.add(player.playerUI.rand13_14);
		     player.playerUI.mypanel2.add(player.playerUI.rand13_15);
		     player.playerUI.mypanel2.add(player.playerUI.rand13_16);
		  
		  }
		  else if(tile == 5)
		  {
  	        iconPlayer1 =  (bp[4][4].getIcon());
	        iconPlayer2 =  (bp[5][4].getIcon());
 	        iconPlayer3 =  (bp[6][4].getIcon());
	        iconPlayer4 =  (bp[7][4].getIcon());
	        iconPlayer5 =  (bp[4][5].getIcon());
	        iconPlayer6 =  (bp[5][5].getIcon());
	        iconPlayer7 =  (bp[6][5].getIcon());
	        iconPlayer8 =  (bp[7][5].getIcon());
	        iconPlayer9 =  (bp[4][6].getIcon());
	        iconPlayer10 =  (bp[5][6].getIcon());
	        iconPlayer11 =  (bp[6][6].getIcon());
	        iconPlayer12 =  (bp[7][6].getIcon());
	        iconPlayer13 =  (bp[4][7].getIcon());
	        iconPlayer14 =  (bp[5][7].getIcon());
	        iconPlayer15 =  (bp[6][7].getIcon());
	        iconPlayer16 =  (bp[7][7].getIcon());

	
		//add components to board
			  GameJLabel boardPlayer1 = new GameJLabel(iconPlayer1);
			  GameJLabel boardPlayer2 = new GameJLabel(iconPlayer2);
			  GameJLabel boardPlayer3 = new GameJLabel(iconPlayer3);
			  GameJLabel boardPlayer4 = new GameJLabel(iconPlayer4);
			  GameJLabel boardPlayer5 = new GameJLabel(iconPlayer5);
			  GameJLabel boardPlayer6 = new GameJLabel(iconPlayer6);
			  GameJLabel boardPlayer7 = new GameJLabel(iconPlayer7);
			  GameJLabel boardPlayer8 = new GameJLabel(iconPlayer8);
			  GameJLabel boardPlayer9 = new GameJLabel(iconPlayer9);
			  GameJLabel boardPlayer10 = new GameJLabel(iconPlayer10);
			  GameJLabel boardPlayer11 = new GameJLabel(iconPlayer11);
			  GameJLabel boardPlayer12 = new GameJLabel(iconPlayer12);
			  GameJLabel boardPlayer13 = new GameJLabel(iconPlayer13);
			  GameJLabel boardPlayer14 = new GameJLabel(iconPlayer14);
			  GameJLabel boardPlayer15 = new GameJLabel(iconPlayer15);
			  GameJLabel boardPlayer16 = new GameJLabel(iconPlayer16);
		
			  player.playerUI.rand13_1.add(boardPlayer1, new Integer(0));
		     player.playerUI.rand13_2.add(boardPlayer2, new Integer(0));
		     player.playerUI.rand13_3.add(boardPlayer3, new Integer(0));
		     player.playerUI.rand13_4.add(boardPlayer4, new Integer(0));
		     player.playerUI.rand13_5.add(boardPlayer5, new Integer(0));
		     player.playerUI.rand13_6.add(boardPlayer6, new Integer(0));
		     player.playerUI.rand13_7.add(boardPlayer7, new Integer(0));
		     player.playerUI.rand13_8.add(boardPlayer8, new Integer(0));
		     player.playerUI.rand13_9.add(boardPlayer9, new Integer(0));
		     player.playerUI.rand13_10.add(boardPlayer10, new Integer(0));
		     player.playerUI.rand13_11.add(boardPlayer11, new Integer(0));
		     player.playerUI.rand13_12.add(boardPlayer12, new Integer(0));
		     player.playerUI.rand13_13.add(boardPlayer13, new Integer(0));
		     player.playerUI.rand13_14.add(boardPlayer14, new Integer(0));
		     player.playerUI.rand13_15.add(boardPlayer15, new Integer(0));
		     player.playerUI.rand13_16.add(boardPlayer16, new Integer(0));
		
		     boardPlayer1.setBounds(0, 0, iconPlayer1.getIconWidth(),
		                                  iconPlayer1.getIconHeight()); 
		     player.playerUI.rand13_1.setPreferredSize(new Dimension(iconPlayer1.getIconWidth(),
		                                        iconPlayer1.getIconHeight()));
		     boardPlayer2.setBounds(0, 0, iconPlayer2.getIconWidth(),
		                                  iconPlayer2.getIconHeight()); 
		     player.playerUI.rand13_2.setPreferredSize(new Dimension(iconPlayer2.getIconWidth(),
		                                        iconPlayer2.getIconHeight()));
		     boardPlayer3.setBounds(0, 0, iconPlayer3.getIconWidth(),
		                                  iconPlayer3.getIconHeight()); 
		     player.playerUI.rand13_3.setPreferredSize(new Dimension(iconPlayer3.getIconWidth(),
		                                        iconPlayer3.getIconHeight()));
		     boardPlayer4.setBounds(0, 0, iconPlayer4.getIconWidth(),
		                                  iconPlayer4.getIconHeight()); 
		     player.playerUI.rand13_4.setPreferredSize(new Dimension(iconPlayer4.getIconWidth(),
		                                        iconPlayer4.getIconHeight()));
		     boardPlayer5.setBounds(0, 0, iconPlayer5.getIconWidth(),
		                                  iconPlayer5.getIconHeight()); 
		     player.playerUI.rand13_5.setPreferredSize(new Dimension(iconPlayer5.getIconWidth(),
		                                        iconPlayer5.getIconHeight()));
		     boardPlayer6.setBounds(0, 0, iconPlayer6.getIconWidth(),
		                                  iconPlayer6.getIconHeight()); 
		     player.playerUI.rand13_6.setPreferredSize(new Dimension(iconPlayer6.getIconWidth(),
		                                        iconPlayer6.getIconHeight()));
		     boardPlayer7.setBounds(0, 0, iconPlayer7.getIconWidth(),
		                                  iconPlayer7.getIconHeight()); 
		     player.playerUI.rand13_7.setPreferredSize(new Dimension(iconPlayer7.getIconWidth(),
		                                        iconPlayer7.getIconHeight()));
		     boardPlayer8.setBounds(0, 0, iconPlayer8.getIconWidth(),
		                                  iconPlayer8.getIconHeight()); 
		     player.playerUI.rand13_8.setPreferredSize(new Dimension(iconPlayer8.getIconWidth(),
		                                        iconPlayer8.getIconHeight()));
		     boardPlayer9.setBounds(0, 0, iconPlayer9.getIconWidth(),
		                                  iconPlayer9.getIconHeight()); 
		     player.playerUI.rand13_9.setPreferredSize(new Dimension(iconPlayer9.getIconWidth(),
		                                        iconPlayer9.getIconHeight()));
		     boardPlayer10.setBounds(0, 0, iconPlayer10.getIconWidth(),
		                                  iconPlayer10.getIconHeight()); 
		     player.playerUI.rand13_10.setPreferredSize(new Dimension(iconPlayer10.getIconWidth(),
		                                        iconPlayer10.getIconHeight()));													 													 
		     boardPlayer11.setBounds(0, 0, iconPlayer11.getIconWidth(),
		                                  iconPlayer11.getIconHeight()); 
		     player.playerUI.rand13_11.setPreferredSize(new Dimension(iconPlayer11.getIconWidth(),
		                                        iconPlayer11.getIconHeight()));
		     boardPlayer12.setBounds(0, 0, iconPlayer12.getIconWidth(),
		                                  iconPlayer12.getIconHeight()); 
		     player.playerUI.rand13_12.setPreferredSize(new Dimension(iconPlayer12.getIconWidth(),
		                                        iconPlayer12.getIconHeight()));
		     boardPlayer13.setBounds(0, 0, iconPlayer13.getIconWidth(),
		                                  iconPlayer13.getIconHeight()); 
		     player.playerUI.rand13_13.setPreferredSize(new Dimension(iconPlayer13.getIconWidth(),
		                                        iconPlayer13.getIconHeight()));
		     boardPlayer14.setBounds(0, 0, iconPlayer14.getIconWidth(),
		                                  iconPlayer14.getIconHeight()); 
		     player.playerUI.rand13_14.setPreferredSize(new Dimension(iconPlayer14.getIconWidth(),
		                                        iconPlayer14.getIconHeight()));
		     boardPlayer15.setBounds(0, 0, iconPlayer15.getIconWidth(),
		                                  iconPlayer15.getIconHeight()); 
		     player.playerUI.rand13_15.setPreferredSize(new Dimension(iconPlayer15.getIconWidth(),
		                                        iconPlayer15.getIconHeight()));	
		     boardPlayer16.setBounds(0, 0, iconPlayer16.getIconWidth(),
		                                  iconPlayer16.getIconHeight()); 
		     player.playerUI.rand13_16.setPreferredSize(new Dimension(iconPlayer16.getIconWidth(),
		                                        iconPlayer16.getIconHeight()));													 												 													 
		
			  player.playerUI.mypanel5.add(player.playerUI.rand13_1);
		     player.playerUI.mypanel5.add(player.playerUI.rand13_2);
		     player.playerUI.mypanel5.add(player.playerUI.rand13_3);
		     player.playerUI.mypanel5.add(player.playerUI.rand13_4);
		     player.playerUI.mypanel5.add(player.playerUI.rand13_5);
		     player.playerUI.mypanel5.add(player.playerUI.rand13_6);
		     player.playerUI.mypanel5.add(player.playerUI.rand13_7);
		     player.playerUI.mypanel5.add(player.playerUI.rand13_8);
		     player.playerUI.mypanel5.add(player.playerUI.rand13_9);
		     player.playerUI.mypanel5.add(player.playerUI.rand13_10);
		     player.playerUI.mypanel5.add(player.playerUI.rand13_11);
		     player.playerUI.mypanel5.add(player.playerUI.rand13_12);
		     player.playerUI.mypanel5.add(player.playerUI.rand13_13);
		     player.playerUI.mypanel5.add(player.playerUI.rand13_14);
		     player.playerUI.mypanel5.add(player.playerUI.rand13_15);
		     player.playerUI.mypanel5.add(player.playerUI.rand13_16);
		  
		  }
		  else if(tile == 6)
		  {
  	        iconPlayer1 =  (bp[4][8].getIcon());
	        iconPlayer2 =  (bp[5][8].getIcon());
 	        iconPlayer3 =  (bp[6][8].getIcon());
	        iconPlayer4 =  (bp[7][8].getIcon());
	        iconPlayer5 =  (bp[4][9].getIcon());
	        iconPlayer6 =  (bp[5][9].getIcon());
	        iconPlayer7 =  (bp[6][9].getIcon());
	        iconPlayer8 =  (bp[7][9].getIcon());
	        iconPlayer9 =  (bp[4][10].getIcon());
	        iconPlayer10 =  (bp[5][10].getIcon());
	        iconPlayer11 =  (bp[6][10].getIcon());
	        iconPlayer12 =  (bp[7][10].getIcon());
	        iconPlayer13 =  (bp[4][11].getIcon());
	        iconPlayer14 =  (bp[5][11].getIcon());
	        iconPlayer15 =  (bp[6][11].getIcon());
	        iconPlayer16 =  (bp[7][11].getIcon());

	
		//add components to board
			  GameJLabel boardPlayer1 = new GameJLabel(iconPlayer1);
			  GameJLabel boardPlayer2 = new GameJLabel(iconPlayer2);
			  GameJLabel boardPlayer3 = new GameJLabel(iconPlayer3);
			  GameJLabel boardPlayer4 = new GameJLabel(iconPlayer4);
			  GameJLabel boardPlayer5 = new GameJLabel(iconPlayer5);
			  GameJLabel boardPlayer6 = new GameJLabel(iconPlayer6);
			  GameJLabel boardPlayer7 = new GameJLabel(iconPlayer7);
			  GameJLabel boardPlayer8 = new GameJLabel(iconPlayer8);
			  GameJLabel boardPlayer9 = new GameJLabel(iconPlayer9);
			  GameJLabel boardPlayer10 = new GameJLabel(iconPlayer10);
			  GameJLabel boardPlayer11 = new GameJLabel(iconPlayer11);
			  GameJLabel boardPlayer12 = new GameJLabel(iconPlayer12);
			  GameJLabel boardPlayer13 = new GameJLabel(iconPlayer13);
			  GameJLabel boardPlayer14 = new GameJLabel(iconPlayer14);
			  GameJLabel boardPlayer15 = new GameJLabel(iconPlayer15);
			  GameJLabel boardPlayer16 = new GameJLabel(iconPlayer16);
		
			  player.playerUI.rand13_1.add(boardPlayer1, new Integer(0));
		     player.playerUI.rand13_2.add(boardPlayer2, new Integer(0));
		     player.playerUI.rand13_3.add(boardPlayer3, new Integer(0));
		     player.playerUI.rand13_4.add(boardPlayer4, new Integer(0));
		     player.playerUI.rand13_5.add(boardPlayer5, new Integer(0));
		     player.playerUI.rand13_6.add(boardPlayer6, new Integer(0));
		     player.playerUI.rand13_7.add(boardPlayer7, new Integer(0));
		     player.playerUI.rand13_8.add(boardPlayer8, new Integer(0));
		     player.playerUI.rand13_9.add(boardPlayer9, new Integer(0));
		     player.playerUI.rand13_10.add(boardPlayer10, new Integer(0));
		     player.playerUI.rand13_11.add(boardPlayer11, new Integer(0));
		     player.playerUI.rand13_12.add(boardPlayer12, new Integer(0));
		     player.playerUI.rand13_13.add(boardPlayer13, new Integer(0));
		     player.playerUI.rand13_14.add(boardPlayer14, new Integer(0));
		     player.playerUI.rand13_15.add(boardPlayer15, new Integer(0));
		     player.playerUI.rand13_16.add(boardPlayer16, new Integer(0));
		
		     boardPlayer1.setBounds(0, 0, iconPlayer1.getIconWidth(),
		                                  iconPlayer1.getIconHeight()); 
		     player.playerUI.rand13_1.setPreferredSize(new Dimension(iconPlayer1.getIconWidth(),
		                                        iconPlayer1.getIconHeight()));
		     boardPlayer2.setBounds(0, 0, iconPlayer2.getIconWidth(),
		                                  iconPlayer2.getIconHeight()); 
		     player.playerUI.rand13_2.setPreferredSize(new Dimension(iconPlayer2.getIconWidth(),
		                                        iconPlayer2.getIconHeight()));
		     boardPlayer3.setBounds(0, 0, iconPlayer3.getIconWidth(),
		                                  iconPlayer3.getIconHeight()); 
		     player.playerUI.rand13_3.setPreferredSize(new Dimension(iconPlayer3.getIconWidth(),
		                                        iconPlayer3.getIconHeight()));
		     boardPlayer4.setBounds(0, 0, iconPlayer4.getIconWidth(),
		                                  iconPlayer4.getIconHeight()); 
		     player.playerUI.rand13_4.setPreferredSize(new Dimension(iconPlayer4.getIconWidth(),
		                                        iconPlayer4.getIconHeight()));
		     boardPlayer5.setBounds(0, 0, iconPlayer5.getIconWidth(),
		                                  iconPlayer5.getIconHeight()); 
		     player.playerUI.rand13_5.setPreferredSize(new Dimension(iconPlayer5.getIconWidth(),
		                                        iconPlayer5.getIconHeight()));
		     boardPlayer6.setBounds(0, 0, iconPlayer6.getIconWidth(),
		                                  iconPlayer6.getIconHeight()); 
		     player.playerUI.rand13_6.setPreferredSize(new Dimension(iconPlayer6.getIconWidth(),
		                                        iconPlayer6.getIconHeight()));
		     boardPlayer7.setBounds(0, 0, iconPlayer7.getIconWidth(),
		                                  iconPlayer7.getIconHeight()); 
		     player.playerUI.rand13_7.setPreferredSize(new Dimension(iconPlayer7.getIconWidth(),
		                                        iconPlayer7.getIconHeight()));
		     boardPlayer8.setBounds(0, 0, iconPlayer8.getIconWidth(),
		                                  iconPlayer8.getIconHeight()); 
		     player.playerUI.rand13_8.setPreferredSize(new Dimension(iconPlayer8.getIconWidth(),
		                                        iconPlayer8.getIconHeight()));
		     boardPlayer9.setBounds(0, 0, iconPlayer9.getIconWidth(),
		                                  iconPlayer9.getIconHeight()); 
		     player.playerUI.rand13_9.setPreferredSize(new Dimension(iconPlayer9.getIconWidth(),
		                                        iconPlayer9.getIconHeight()));
		     boardPlayer10.setBounds(0, 0, iconPlayer10.getIconWidth(),
		                                  iconPlayer10.getIconHeight()); 
		     player.playerUI.rand13_10.setPreferredSize(new Dimension(iconPlayer10.getIconWidth(),
		                                        iconPlayer10.getIconHeight()));													 													 
		     boardPlayer11.setBounds(0, 0, iconPlayer11.getIconWidth(),
		                                  iconPlayer11.getIconHeight()); 
		     player.playerUI.rand13_11.setPreferredSize(new Dimension(iconPlayer11.getIconWidth(),
		                                        iconPlayer11.getIconHeight()));
		     boardPlayer12.setBounds(0, 0, iconPlayer12.getIconWidth(),
		                                  iconPlayer12.getIconHeight()); 
		     player.playerUI.rand13_12.setPreferredSize(new Dimension(iconPlayer12.getIconWidth(),
		                                        iconPlayer12.getIconHeight()));
		     boardPlayer13.setBounds(0, 0, iconPlayer13.getIconWidth(),
		                                  iconPlayer13.getIconHeight()); 
		     player.playerUI.rand13_13.setPreferredSize(new Dimension(iconPlayer13.getIconWidth(),
		                                        iconPlayer13.getIconHeight()));
		     boardPlayer14.setBounds(0, 0, iconPlayer14.getIconWidth(),
		                                  iconPlayer14.getIconHeight()); 
		     player.playerUI.rand13_14.setPreferredSize(new Dimension(iconPlayer14.getIconWidth(),
		                                        iconPlayer14.getIconHeight()));
		     boardPlayer15.setBounds(0, 0, iconPlayer15.getIconWidth(),
		                                  iconPlayer15.getIconHeight()); 
		     player.playerUI.rand13_15.setPreferredSize(new Dimension(iconPlayer15.getIconWidth(),
		                                        iconPlayer15.getIconHeight()));	
		     boardPlayer16.setBounds(0, 0, iconPlayer16.getIconWidth(),
		                                  iconPlayer16.getIconHeight()); 
		     player.playerUI.rand13_16.setPreferredSize(new Dimension(iconPlayer16.getIconWidth(),
		                                        iconPlayer16.getIconHeight()));													 												 													 
		
			  player.playerUI.mypanel6.add(player.playerUI.rand13_1);
		     player.playerUI.mypanel6.add(player.playerUI.rand13_2);
		     player.playerUI.mypanel6.add(player.playerUI.rand13_3);
		     player.playerUI.mypanel6.add(player.playerUI.rand13_4);
		     player.playerUI.mypanel6.add(player.playerUI.rand13_5);
		     player.playerUI.mypanel6.add(player.playerUI.rand13_6);
		     player.playerUI.mypanel6.add(player.playerUI.rand13_7);
		     player.playerUI.mypanel6.add(player.playerUI.rand13_8);
		     player.playerUI.mypanel6.add(player.playerUI.rand13_9);
		     player.playerUI.mypanel6.add(player.playerUI.rand13_10);
		     player.playerUI.mypanel6.add(player.playerUI.rand13_11);
		     player.playerUI.mypanel6.add(player.playerUI.rand13_12);
		     player.playerUI.mypanel6.add(player.playerUI.rand13_13);
		     player.playerUI.mypanel6.add(player.playerUI.rand13_14);
		     player.playerUI.mypanel6.add(player.playerUI.rand13_15);
		     player.playerUI.mypanel6.add(player.playerUI.rand13_16);
		  
		  }
		  else if(tile == 7)
		  {
	       iconPlayer1 =  bp[4][12].getIcon();
	       iconPlayer2 =  bp[5][12].getIcon();
	       iconPlayer3 =  bp[6][12].getIcon();
	       iconPlayer4 =  bp[7][12].getIcon();
	       iconPlayer5 =  bp[4][13].getIcon();
	       iconPlayer6 =  bp[5][13].getIcon();
	       iconPlayer7 =  bp[6][13].getIcon();
	       iconPlayer8 =  bp[7][13].getIcon();
	       iconPlayer9 =  bp[4][14].getIcon();
	       iconPlayer10 =  bp[5][14].getIcon();
	       iconPlayer11 =  bp[6][14].getIcon();
	       iconPlayer12 =  bp[7][14].getIcon();
	       iconPlayer13 =  bp[4][15].getIcon();
	       iconPlayer14 =  bp[5][15].getIcon();
	       iconPlayer15 =  bp[6][15].getIcon();
	       iconPlayer16 =  bp[7][15].getIcon();


		//add components to board
			  GameJLabel boardPlayer1 = new GameJLabel(iconPlayer1);
			  GameJLabel boardPlayer2 = new GameJLabel(iconPlayer2);
			  GameJLabel boardPlayer3 = new GameJLabel(iconPlayer3);
			  GameJLabel boardPlayer4 = new GameJLabel(iconPlayer4);
			  GameJLabel boardPlayer5 = new GameJLabel(iconPlayer5);
			  GameJLabel boardPlayer6 = new GameJLabel(iconPlayer6);
			  GameJLabel boardPlayer7 = new GameJLabel(iconPlayer7);
			  GameJLabel boardPlayer8 = new GameJLabel(iconPlayer8);
			  GameJLabel boardPlayer9 = new GameJLabel(iconPlayer9);
			  GameJLabel boardPlayer10 = new GameJLabel(iconPlayer10);
			  GameJLabel boardPlayer11 = new GameJLabel(iconPlayer11);
			  GameJLabel boardPlayer12 = new GameJLabel(iconPlayer12);
			  GameJLabel boardPlayer13 = new GameJLabel(iconPlayer13);
			  GameJLabel boardPlayer14 = new GameJLabel(iconPlayer14);
			  GameJLabel boardPlayer15 = new GameJLabel(iconPlayer15);
			  GameJLabel boardPlayer16 = new GameJLabel(iconPlayer16);
		
			  player.playerUI.rand13_1.add(boardPlayer1, new Integer(0));
		     player.playerUI.rand13_2.add(boardPlayer2, new Integer(0));
		     player.playerUI.rand13_3.add(boardPlayer3, new Integer(0));
		     player.playerUI.rand13_4.add(boardPlayer4, new Integer(0));
		     player.playerUI.rand13_5.add(boardPlayer5, new Integer(0));
		     player.playerUI.rand13_6.add(boardPlayer6, new Integer(0));
		     player.playerUI.rand13_7.add(boardPlayer7, new Integer(0));
		     player.playerUI.rand13_8.add(boardPlayer8, new Integer(0));
		     player.playerUI.rand13_9.add(boardPlayer9, new Integer(0));
		     player.playerUI.rand13_10.add(boardPlayer10, new Integer(0));
		     player.playerUI.rand13_11.add(boardPlayer11, new Integer(0));
		     player.playerUI.rand13_12.add(boardPlayer12, new Integer(0));
		     player.playerUI.rand13_13.add(boardPlayer13, new Integer(0));
		     player.playerUI.rand13_14.add(boardPlayer14, new Integer(0));
		     player.playerUI.rand13_15.add(boardPlayer15, new Integer(0));
		     player.playerUI.rand13_16.add(boardPlayer16, new Integer(0));
		
		     boardPlayer1.setBounds(0, 0, iconPlayer1.getIconWidth(),
		                                  iconPlayer1.getIconHeight()); 
		     player.playerUI.rand13_1.setPreferredSize(new Dimension(iconPlayer1.getIconWidth(),
		                                        iconPlayer1.getIconHeight()));
		     boardPlayer2.setBounds(0, 0, iconPlayer2.getIconWidth(),
		                                  iconPlayer2.getIconHeight()); 
		     player.playerUI.rand13_2.setPreferredSize(new Dimension(iconPlayer2.getIconWidth(),
		                                        iconPlayer2.getIconHeight()));
		     boardPlayer3.setBounds(0, 0, iconPlayer3.getIconWidth(),
		                                  iconPlayer3.getIconHeight()); 
		     player.playerUI.rand13_3.setPreferredSize(new Dimension(iconPlayer3.getIconWidth(),
		                                        iconPlayer3.getIconHeight()));
		     boardPlayer4.setBounds(0, 0, iconPlayer4.getIconWidth(),
		                                  iconPlayer4.getIconHeight()); 
		     player.playerUI.rand13_4.setPreferredSize(new Dimension(iconPlayer4.getIconWidth(),
		                                        iconPlayer4.getIconHeight()));
		     boardPlayer5.setBounds(0, 0, iconPlayer5.getIconWidth(),
		                                  iconPlayer5.getIconHeight()); 
		     player.playerUI.rand13_5.setPreferredSize(new Dimension(iconPlayer5.getIconWidth(),
		                                        iconPlayer5.getIconHeight()));
		     boardPlayer6.setBounds(0, 0, iconPlayer6.getIconWidth(),
		                                  iconPlayer6.getIconHeight()); 
		     player.playerUI.rand13_6.setPreferredSize(new Dimension(iconPlayer6.getIconWidth(),
		                                        iconPlayer6.getIconHeight()));
		     boardPlayer7.setBounds(0, 0, iconPlayer7.getIconWidth(),
		                                  iconPlayer7.getIconHeight()); 
		     player.playerUI.rand13_7.setPreferredSize(new Dimension(iconPlayer7.getIconWidth(),
		                                        iconPlayer7.getIconHeight()));
		     boardPlayer8.setBounds(0, 0, iconPlayer8.getIconWidth(),
		                                  iconPlayer8.getIconHeight()); 
		     player.playerUI.rand13_8.setPreferredSize(new Dimension(iconPlayer8.getIconWidth(),
		                                        iconPlayer8.getIconHeight()));
		     boardPlayer9.setBounds(0, 0, iconPlayer9.getIconWidth(),
		                                  iconPlayer9.getIconHeight()); 
		     player.playerUI.rand13_9.setPreferredSize(new Dimension(iconPlayer9.getIconWidth(),
		                                        iconPlayer9.getIconHeight()));
		     boardPlayer10.setBounds(0, 0, iconPlayer10.getIconWidth(),
		                                  iconPlayer10.getIconHeight()); 
		     player.playerUI.rand13_10.setPreferredSize(new Dimension(iconPlayer10.getIconWidth(),
		                                        iconPlayer10.getIconHeight()));													 													 
		     boardPlayer11.setBounds(0, 0, iconPlayer11.getIconWidth(),
		                                  iconPlayer11.getIconHeight()); 
		     player.playerUI.rand13_11.setPreferredSize(new Dimension(iconPlayer11.getIconWidth(),
		                                        iconPlayer11.getIconHeight()));
		     boardPlayer12.setBounds(0, 0, iconPlayer12.getIconWidth(),
		                                  iconPlayer12.getIconHeight()); 
		     player.playerUI.rand13_12.setPreferredSize(new Dimension(iconPlayer12.getIconWidth(),
		                                        iconPlayer12.getIconHeight()));
		     boardPlayer13.setBounds(0, 0, iconPlayer13.getIconWidth(),
		                                  iconPlayer13.getIconHeight()); 
		     player.playerUI.rand13_13.setPreferredSize(new Dimension(iconPlayer13.getIconWidth(),
		                                        iconPlayer13.getIconHeight()));
		     boardPlayer14.setBounds(0, 0, iconPlayer14.getIconWidth(),
		                                  iconPlayer14.getIconHeight()); 
		     player.playerUI.rand13_14.setPreferredSize(new Dimension(iconPlayer14.getIconWidth(),
		                                        iconPlayer14.getIconHeight()));
		     boardPlayer15.setBounds(0, 0, iconPlayer15.getIconWidth(),
		                                  iconPlayer15.getIconHeight()); 
		     player.playerUI.rand13_15.setPreferredSize(new Dimension(iconPlayer15.getIconWidth(),
		                                        iconPlayer15.getIconHeight()));	
		     boardPlayer16.setBounds(0, 0, iconPlayer16.getIconWidth(),
		                                  iconPlayer16.getIconHeight()); 
		     player.playerUI.rand13_16.setPreferredSize(new Dimension(iconPlayer16.getIconWidth(),
		                                        iconPlayer16.getIconHeight()));													 												 													 
		
			  player.playerUI.mypanel7.add(player.playerUI.rand13_1);
		     player.playerUI.mypanel7.add(player.playerUI.rand13_2);
		     player.playerUI.mypanel7.add(player.playerUI.rand13_3);
		     player.playerUI.mypanel7.add(player.playerUI.rand13_4);
		     player.playerUI.mypanel7.add(player.playerUI.rand13_5);
		     player.playerUI.mypanel7.add(player.playerUI.rand13_6);
		     player.playerUI.mypanel7.add(player.playerUI.rand13_7);
		     player.playerUI.mypanel7.add(player.playerUI.rand13_8);
		     player.playerUI.mypanel7.add(player.playerUI.rand13_9);
		     player.playerUI.mypanel7.add(player.playerUI.rand13_10);
		     player.playerUI.mypanel7.add(player.playerUI.rand13_11);
		     player.playerUI.mypanel7.add(player.playerUI.rand13_12);
		     player.playerUI.mypanel7.add(player.playerUI.rand13_13);
		     player.playerUI.mypanel7.add(player.playerUI.rand13_14);
		     player.playerUI.mypanel7.add(player.playerUI.rand13_15);
		     player.playerUI.mypanel7.add(player.playerUI.rand13_16);
		  
		  }
		}

      private void populateRand14(int tile, Player player)
		{
		  if(tile == 0)
		  {
			  iconPlayer1 =  (bp[0][0].getIcon());
	        iconPlayer2 =  (bp[1][0].getIcon());
 	        iconPlayer3 =  (bp[2][0].getIcon());
	        iconPlayer4 =  (bp[3][0].getIcon());
	        iconPlayer5 =  (bp[0][1].getIcon());
	        iconPlayer6 =  (bp[1][1].getIcon());
	        iconPlayer7 =  (bp[2][1].getIcon());
	        iconPlayer8 =  (bp[3][1].getIcon());
	        iconPlayer9 =  (bp[0][2].getIcon());
	        iconPlayer10 =  (bp[1][2].getIcon());
	        iconPlayer11 =  (bp[2][2].getIcon());
	        iconPlayer12 =  (bp[3][2].getIcon());
	        iconPlayer13 =  (bp[0][3].getIcon());
	        iconPlayer14 =  (bp[1][3].getIcon());
	        iconPlayer15 =  (bp[2][3].getIcon());
	        iconPlayer16 =  (bp[3][3].getIcon());

	
		//add components to board
			  GameJLabel boardPlayer1 = new GameJLabel(iconPlayer1);
			  GameJLabel boardPlayer2 = new GameJLabel(iconPlayer2);
			  GameJLabel boardPlayer3 = new GameJLabel(iconPlayer3);
			  GameJLabel boardPlayer4 = new GameJLabel(iconPlayer4);
			  GameJLabel boardPlayer5 = new GameJLabel(iconPlayer5);
			  GameJLabel boardPlayer6 = new GameJLabel(iconPlayer6);
			  GameJLabel boardPlayer7 = new GameJLabel(iconPlayer7);
			  GameJLabel boardPlayer8 = new GameJLabel(iconPlayer8);
			  GameJLabel boardPlayer9 = new GameJLabel(iconPlayer9);
			  GameJLabel boardPlayer10 = new GameJLabel(iconPlayer10);
			  GameJLabel boardPlayer11 = new GameJLabel(iconPlayer11);
			  GameJLabel boardPlayer12 = new GameJLabel(iconPlayer12);
			  GameJLabel boardPlayer13 = new GameJLabel(iconPlayer13);
			  GameJLabel boardPlayer14 = new GameJLabel(iconPlayer14);
			  GameJLabel boardPlayer15 = new GameJLabel(iconPlayer15);
			  GameJLabel boardPlayer16 = new GameJLabel(iconPlayer16);
		
			  player.playerUI.rand14_1.add(boardPlayer1, new Integer(0));
		     player.playerUI.rand14_2.add(boardPlayer2, new Integer(0));
		     player.playerUI.rand14_3.add(boardPlayer3, new Integer(0));
		     player.playerUI.rand14_4.add(boardPlayer4, new Integer(0));
		     player.playerUI.rand14_5.add(boardPlayer5, new Integer(0));
		     player.playerUI.rand14_6.add(boardPlayer6, new Integer(0));
		     player.playerUI.rand14_7.add(boardPlayer7, new Integer(0));
		     player.playerUI.rand14_8.add(boardPlayer8, new Integer(0));
		     player.playerUI.rand14_9.add(boardPlayer9, new Integer(0));
		     player.playerUI.rand14_10.add(boardPlayer10, new Integer(0));
		     player.playerUI.rand14_11.add(boardPlayer11, new Integer(0));
		     player.playerUI.rand14_12.add(boardPlayer12, new Integer(0));
		     player.playerUI.rand14_13.add(boardPlayer13, new Integer(0));
		     player.playerUI.rand14_14.add(boardPlayer14, new Integer(0));
		     player.playerUI.rand14_15.add(boardPlayer15, new Integer(0));
		     player.playerUI.rand14_16.add(boardPlayer16, new Integer(0));
		
		     boardPlayer1.setBounds(0, 0, iconPlayer1.getIconWidth(),
		                                  iconPlayer1.getIconHeight()); 
		     player.playerUI.rand14_1.setPreferredSize(new Dimension(iconPlayer1.getIconWidth(),
		                                        iconPlayer1.getIconHeight()));
		     boardPlayer2.setBounds(0, 0, iconPlayer2.getIconWidth(),
		                                  iconPlayer2.getIconHeight()); 
		     player.playerUI.rand14_2.setPreferredSize(new Dimension(iconPlayer2.getIconWidth(),
		                                        iconPlayer2.getIconHeight()));
		     boardPlayer3.setBounds(0, 0, iconPlayer3.getIconWidth(),
		                                  iconPlayer3.getIconHeight()); 
		     player.playerUI.rand14_3.setPreferredSize(new Dimension(iconPlayer3.getIconWidth(),
		                                        iconPlayer3.getIconHeight()));
		     boardPlayer4.setBounds(0, 0, iconPlayer4.getIconWidth(),
		                                  iconPlayer4.getIconHeight()); 
		     player.playerUI.rand14_4.setPreferredSize(new Dimension(iconPlayer4.getIconWidth(),
		                                        iconPlayer4.getIconHeight()));
		     boardPlayer5.setBounds(0, 0, iconPlayer5.getIconWidth(),
		                                  iconPlayer5.getIconHeight()); 
		     player.playerUI.rand14_5.setPreferredSize(new Dimension(iconPlayer5.getIconWidth(),
		                                        iconPlayer5.getIconHeight()));
		     boardPlayer6.setBounds(0, 0, iconPlayer6.getIconWidth(),
		                                  iconPlayer6.getIconHeight()); 
		     player.playerUI.rand14_6.setPreferredSize(new Dimension(iconPlayer6.getIconWidth(),
		                                        iconPlayer6.getIconHeight()));
		     boardPlayer7.setBounds(0, 0, iconPlayer7.getIconWidth(),
		                                  iconPlayer7.getIconHeight()); 
		     player.playerUI.rand14_7.setPreferredSize(new Dimension(iconPlayer7.getIconWidth(),
		                                        iconPlayer7.getIconHeight()));
		     boardPlayer8.setBounds(0, 0, iconPlayer8.getIconWidth(),
		                                  iconPlayer8.getIconHeight()); 
		     player.playerUI.rand14_8.setPreferredSize(new Dimension(iconPlayer8.getIconWidth(),
		                                        iconPlayer8.getIconHeight()));
		     boardPlayer9.setBounds(0, 0, iconPlayer9.getIconWidth(),
		                                  iconPlayer9.getIconHeight()); 
		     player.playerUI.rand14_9.setPreferredSize(new Dimension(iconPlayer9.getIconWidth(),
		                                        iconPlayer9.getIconHeight()));
		     boardPlayer10.setBounds(0, 0, iconPlayer10.getIconWidth(),
		                                  iconPlayer10.getIconHeight()); 
		     player.playerUI.rand14_10.setPreferredSize(new Dimension(iconPlayer10.getIconWidth(),
		                                        iconPlayer10.getIconHeight()));													 													 
		     boardPlayer11.setBounds(0, 0, iconPlayer11.getIconWidth(),
		                                  iconPlayer11.getIconHeight()); 
		     player.playerUI.rand14_11.setPreferredSize(new Dimension(iconPlayer11.getIconWidth(),
		                                        iconPlayer11.getIconHeight()));
		     boardPlayer12.setBounds(0, 0, iconPlayer12.getIconWidth(),
		                                  iconPlayer12.getIconHeight()); 
		     player.playerUI.rand14_12.setPreferredSize(new Dimension(iconPlayer12.getIconWidth(),
		                                        iconPlayer12.getIconHeight()));
		     boardPlayer13.setBounds(0, 0, iconPlayer13.getIconWidth(),
		                                  iconPlayer13.getIconHeight()); 
		     player.playerUI.rand14_13.setPreferredSize(new Dimension(iconPlayer13.getIconWidth(),
		                                        iconPlayer13.getIconHeight()));
		     boardPlayer14.setBounds(0, 0, iconPlayer14.getIconWidth(),
		                                  iconPlayer14.getIconHeight()); 
		     player.playerUI.rand14_14.setPreferredSize(new Dimension(iconPlayer14.getIconWidth(),
		                                        iconPlayer14.getIconHeight()));
		     boardPlayer15.setBounds(0, 0, iconPlayer15.getIconWidth(),
		                                  iconPlayer15.getIconHeight()); 
		     player.playerUI.rand14_15.setPreferredSize(new Dimension(iconPlayer15.getIconWidth(),
		                                        iconPlayer15.getIconHeight()));	
		     boardPlayer16.setBounds(0, 0, iconPlayer16.getIconWidth(),
		                                  iconPlayer16.getIconHeight()); 
		     player.playerUI.rand14_16.setPreferredSize(new Dimension(iconPlayer16.getIconWidth(),
		                                        iconPlayer16.getIconHeight()));													 												 													 
		
			  player.playerUI.mypanel0.add(player.playerUI.rand14_1);
		     player.playerUI.mypanel0.add(player.playerUI.rand14_2);
		     player.playerUI.mypanel0.add(player.playerUI.rand14_3);
		     player.playerUI.mypanel0.add(player.playerUI.rand14_4);
		     player.playerUI.mypanel0.add(player.playerUI.rand14_5);
		     player.playerUI.mypanel0.add(player.playerUI.rand14_6);
		     player.playerUI.mypanel0.add(player.playerUI.rand14_7);
		     player.playerUI.mypanel0.add(player.playerUI.rand14_8);
		     player.playerUI.mypanel0.add(player.playerUI.rand14_9);
		     player.playerUI.mypanel0.add(player.playerUI.rand14_10);
		     player.playerUI.mypanel0.add(player.playerUI.rand14_11);
		     player.playerUI.mypanel0.add(player.playerUI.rand14_12);
		     player.playerUI.mypanel0.add(player.playerUI.rand14_13);
		     player.playerUI.mypanel0.add(player.playerUI.rand14_14);
		     player.playerUI.mypanel0.add(player.playerUI.rand14_15);
		     player.playerUI.mypanel0.add(player.playerUI.rand14_16);
		  }
		  else if(tile == 1)
		  {
  	        iconPlayer1 =  (bp[0][4].getIcon());
	        iconPlayer2 =  (bp[1][4].getIcon());
 	        iconPlayer3 =  (bp[2][4].getIcon());
	        iconPlayer4 =  (bp[3][4].getIcon());
	        iconPlayer5 =  (bp[0][5].getIcon());
	        iconPlayer6 =  (bp[1][5].getIcon());
	        iconPlayer7 =  (bp[2][5].getIcon());
	        iconPlayer8 =  (bp[3][5].getIcon());
	        iconPlayer9 =  (bp[0][6].getIcon());
	        iconPlayer10 =  (bp[1][6].getIcon());
	        iconPlayer11 =  (bp[2][6].getIcon());
	        iconPlayer12 =  (bp[3][6].getIcon());
	        iconPlayer13 =  (bp[0][7].getIcon());
	        iconPlayer14 =  (bp[1][7].getIcon());
	        iconPlayer15 =  (bp[2][7].getIcon());
	        iconPlayer16 =  (bp[3][7].getIcon());

	
		//add components to board
			  GameJLabel boardPlayer1 = new GameJLabel(iconPlayer1);
			  GameJLabel boardPlayer2 = new GameJLabel(iconPlayer2);
			  GameJLabel boardPlayer3 = new GameJLabel(iconPlayer3);
			  GameJLabel boardPlayer4 = new GameJLabel(iconPlayer4);
			  GameJLabel boardPlayer5 = new GameJLabel(iconPlayer5);
			  GameJLabel boardPlayer6 = new GameJLabel(iconPlayer6);
			  GameJLabel boardPlayer7 = new GameJLabel(iconPlayer7);
			  GameJLabel boardPlayer8 = new GameJLabel(iconPlayer8);
			  GameJLabel boardPlayer9 = new GameJLabel(iconPlayer9);
			  GameJLabel boardPlayer10 = new GameJLabel(iconPlayer10);
			  GameJLabel boardPlayer11 = new GameJLabel(iconPlayer11);
			  GameJLabel boardPlayer12 = new GameJLabel(iconPlayer12);
			  GameJLabel boardPlayer13 = new GameJLabel(iconPlayer13);
			  GameJLabel boardPlayer14 = new GameJLabel(iconPlayer14);
			  GameJLabel boardPlayer15 = new GameJLabel(iconPlayer15);
			  GameJLabel boardPlayer16 = new GameJLabel(iconPlayer16);
		
			  player.playerUI.rand14_1.add(boardPlayer1, new Integer(0));
		     player.playerUI.rand14_2.add(boardPlayer2, new Integer(0));
		     player.playerUI.rand14_3.add(boardPlayer3, new Integer(0));
		     player.playerUI.rand14_4.add(boardPlayer4, new Integer(0));
		     player.playerUI.rand14_5.add(boardPlayer5, new Integer(0));
		     player.playerUI.rand14_6.add(boardPlayer6, new Integer(0));
		     player.playerUI.rand14_7.add(boardPlayer7, new Integer(0));
		     player.playerUI.rand14_8.add(boardPlayer8, new Integer(0));
		     player.playerUI.rand14_9.add(boardPlayer9, new Integer(0));
		     player.playerUI.rand14_10.add(boardPlayer10, new Integer(0));
		     player.playerUI.rand14_11.add(boardPlayer11, new Integer(0));
		     player.playerUI.rand14_12.add(boardPlayer12, new Integer(0));
		     player.playerUI.rand14_13.add(boardPlayer13, new Integer(0));
		     player.playerUI.rand14_14.add(boardPlayer14, new Integer(0));
		     player.playerUI.rand14_15.add(boardPlayer15, new Integer(0));
		     player.playerUI.rand14_16.add(boardPlayer16, new Integer(0));
		
		     boardPlayer1.setBounds(0, 0, iconPlayer1.getIconWidth(),
		                                  iconPlayer1.getIconHeight()); 
		     player.playerUI.rand14_1.setPreferredSize(new Dimension(iconPlayer1.getIconWidth(),
		                                        iconPlayer1.getIconHeight()));
		     boardPlayer2.setBounds(0, 0, iconPlayer2.getIconWidth(),
		                                  iconPlayer2.getIconHeight()); 
		     player.playerUI.rand14_2.setPreferredSize(new Dimension(iconPlayer2.getIconWidth(),
		                                        iconPlayer2.getIconHeight()));
		     boardPlayer3.setBounds(0, 0, iconPlayer3.getIconWidth(),
		                                  iconPlayer3.getIconHeight()); 
		     player.playerUI.rand14_3.setPreferredSize(new Dimension(iconPlayer3.getIconWidth(),
		                                        iconPlayer3.getIconHeight()));
		     boardPlayer4.setBounds(0, 0, iconPlayer4.getIconWidth(),
		                                  iconPlayer4.getIconHeight()); 
		     player.playerUI.rand14_4.setPreferredSize(new Dimension(iconPlayer4.getIconWidth(),
		                                        iconPlayer4.getIconHeight()));
		     boardPlayer5.setBounds(0, 0, iconPlayer5.getIconWidth(),
		                                  iconPlayer5.getIconHeight()); 
		     player.playerUI.rand14_5.setPreferredSize(new Dimension(iconPlayer5.getIconWidth(),
		                                        iconPlayer5.getIconHeight()));
		     boardPlayer6.setBounds(0, 0, iconPlayer6.getIconWidth(),
		                                  iconPlayer6.getIconHeight()); 
		     player.playerUI.rand14_6.setPreferredSize(new Dimension(iconPlayer6.getIconWidth(),
		                                        iconPlayer6.getIconHeight()));
		     boardPlayer7.setBounds(0, 0, iconPlayer7.getIconWidth(),
		                                  iconPlayer7.getIconHeight()); 
		     player.playerUI.rand14_7.setPreferredSize(new Dimension(iconPlayer7.getIconWidth(),
		                                        iconPlayer7.getIconHeight()));
		     boardPlayer8.setBounds(0, 0, iconPlayer8.getIconWidth(),
		                                  iconPlayer8.getIconHeight()); 
		     player.playerUI.rand14_8.setPreferredSize(new Dimension(iconPlayer8.getIconWidth(),
		                                        iconPlayer8.getIconHeight()));
		     boardPlayer9.setBounds(0, 0, iconPlayer9.getIconWidth(),
		                                  iconPlayer9.getIconHeight()); 
		     player.playerUI.rand14_9.setPreferredSize(new Dimension(iconPlayer9.getIconWidth(),
		                                        iconPlayer9.getIconHeight()));
		     boardPlayer10.setBounds(0, 0, iconPlayer10.getIconWidth(),
		                                  iconPlayer10.getIconHeight()); 
		     player.playerUI.rand14_10.setPreferredSize(new Dimension(iconPlayer10.getIconWidth(),
		                                        iconPlayer10.getIconHeight()));													 													 
		     boardPlayer11.setBounds(0, 0, iconPlayer11.getIconWidth(),
		                                  iconPlayer11.getIconHeight()); 
		     player.playerUI.rand14_11.setPreferredSize(new Dimension(iconPlayer11.getIconWidth(),
		                                        iconPlayer11.getIconHeight()));
		     boardPlayer12.setBounds(0, 0, iconPlayer12.getIconWidth(),
		                                  iconPlayer12.getIconHeight()); 
		     player.playerUI.rand14_12.setPreferredSize(new Dimension(iconPlayer12.getIconWidth(),
		                                        iconPlayer12.getIconHeight()));
		     boardPlayer13.setBounds(0, 0, iconPlayer13.getIconWidth(),
		                                  iconPlayer13.getIconHeight()); 
		     player.playerUI.rand14_13.setPreferredSize(new Dimension(iconPlayer13.getIconWidth(),
		                                        iconPlayer13.getIconHeight()));
		     boardPlayer14.setBounds(0, 0, iconPlayer14.getIconWidth(),
		                                  iconPlayer14.getIconHeight()); 
		     player.playerUI.rand14_14.setPreferredSize(new Dimension(iconPlayer14.getIconWidth(),
		                                        iconPlayer14.getIconHeight()));
		     boardPlayer15.setBounds(0, 0, iconPlayer15.getIconWidth(),
		                                  iconPlayer15.getIconHeight()); 
		     player.playerUI.rand14_15.setPreferredSize(new Dimension(iconPlayer15.getIconWidth(),
		                                        iconPlayer15.getIconHeight()));	
		     boardPlayer16.setBounds(0, 0, iconPlayer16.getIconWidth(),
		                                  iconPlayer16.getIconHeight()); 
		     player.playerUI.rand14_16.setPreferredSize(new Dimension(iconPlayer16.getIconWidth(),
		                                        iconPlayer16.getIconHeight()));													 												 													 
		
			  player.playerUI.mypanel1.add(player.playerUI.rand14_1);
		     player.playerUI.mypanel1.add(player.playerUI.rand14_2);
		     player.playerUI.mypanel1.add(player.playerUI.rand14_3);
		     player.playerUI.mypanel1.add(player.playerUI.rand14_4);
		     player.playerUI.mypanel1.add(player.playerUI.rand14_5);
		     player.playerUI.mypanel1.add(player.playerUI.rand14_6);
		     player.playerUI.mypanel1.add(player.playerUI.rand14_7);
		     player.playerUI.mypanel1.add(player.playerUI.rand14_8);
		     player.playerUI.mypanel1.add(player.playerUI.rand14_9);
		     player.playerUI.mypanel1.add(player.playerUI.rand14_10);
		     player.playerUI.mypanel1.add(player.playerUI.rand14_11);
		     player.playerUI.mypanel1.add(player.playerUI.rand14_12);
		     player.playerUI.mypanel1.add(player.playerUI.rand14_13);
		     player.playerUI.mypanel1.add(player.playerUI.rand14_14);
		     player.playerUI.mypanel1.add(player.playerUI.rand14_15);
		     player.playerUI.mypanel1.add(player.playerUI.rand14_16);
		  
		  }
		  else if(tile == 2)
		  {
  	        iconPlayer1 =  (bp[0][8].getIcon());
	        iconPlayer2 =  (bp[1][8].getIcon());
 	        iconPlayer3 =  (bp[2][8].getIcon());
	        iconPlayer4 =  (bp[3][8].getIcon());
	        iconPlayer5 =  (bp[0][9].getIcon());
	        iconPlayer6 =  (bp[1][9].getIcon());
	        iconPlayer7 =  (bp[2][9].getIcon());
	        iconPlayer8 =  (bp[3][9].getIcon());
	        iconPlayer9 =  (bp[0][10].getIcon());
	        iconPlayer10 =  (bp[1][10].getIcon());
	        iconPlayer11 =  (bp[2][10].getIcon());
	        iconPlayer12 =  (bp[3][10].getIcon());
	        iconPlayer13 =  (bp[0][11].getIcon());
	        iconPlayer14 =  (bp[1][11].getIcon());
	        iconPlayer15 =  (bp[2][11].getIcon());
	        iconPlayer16 =  (bp[3][11].getIcon());

	
		//add components to board
			  GameJLabel boardPlayer1 = new GameJLabel(iconPlayer1);
			  GameJLabel boardPlayer2 = new GameJLabel(iconPlayer2);
			  GameJLabel boardPlayer3 = new GameJLabel(iconPlayer3);
			  GameJLabel boardPlayer4 = new GameJLabel(iconPlayer4);
			  GameJLabel boardPlayer5 = new GameJLabel(iconPlayer5);
			  GameJLabel boardPlayer6 = new GameJLabel(iconPlayer6);
			  GameJLabel boardPlayer7 = new GameJLabel(iconPlayer7);
			  GameJLabel boardPlayer8 = new GameJLabel(iconPlayer8);
			  GameJLabel boardPlayer9 = new GameJLabel(iconPlayer9);
			  GameJLabel boardPlayer10 = new GameJLabel(iconPlayer10);
			  GameJLabel boardPlayer11 = new GameJLabel(iconPlayer11);
			  GameJLabel boardPlayer12 = new GameJLabel(iconPlayer12);
			  GameJLabel boardPlayer13 = new GameJLabel(iconPlayer13);
			  GameJLabel boardPlayer14 = new GameJLabel(iconPlayer14);
			  GameJLabel boardPlayer15 = new GameJLabel(iconPlayer15);
			  GameJLabel boardPlayer16 = new GameJLabel(iconPlayer16);
		
			  player.playerUI.rand14_1.add(boardPlayer1, new Integer(0));
		     player.playerUI.rand14_2.add(boardPlayer2, new Integer(0));
		     player.playerUI.rand14_3.add(boardPlayer3, new Integer(0));
		     player.playerUI.rand14_4.add(boardPlayer4, new Integer(0));
		     player.playerUI.rand14_5.add(boardPlayer5, new Integer(0));
		     player.playerUI.rand14_6.add(boardPlayer6, new Integer(0));
		     player.playerUI.rand14_7.add(boardPlayer7, new Integer(0));
		     player.playerUI.rand14_8.add(boardPlayer8, new Integer(0));
		     player.playerUI.rand14_9.add(boardPlayer9, new Integer(0));
		     player.playerUI.rand14_10.add(boardPlayer10, new Integer(0));
		     player.playerUI.rand14_11.add(boardPlayer11, new Integer(0));
		     player.playerUI.rand14_12.add(boardPlayer12, new Integer(0));
		     player.playerUI.rand14_13.add(boardPlayer13, new Integer(0));
		     player.playerUI.rand14_14.add(boardPlayer14, new Integer(0));
		     player.playerUI.rand14_15.add(boardPlayer15, new Integer(0));
		     player.playerUI.rand14_16.add(boardPlayer16, new Integer(0));
		
		     boardPlayer1.setBounds(0, 0, iconPlayer1.getIconWidth(),
		                                  iconPlayer1.getIconHeight()); 
		     player.playerUI.rand14_1.setPreferredSize(new Dimension(iconPlayer1.getIconWidth(),
		                                        iconPlayer1.getIconHeight()));
		     boardPlayer2.setBounds(0, 0, iconPlayer2.getIconWidth(),
		                                  iconPlayer2.getIconHeight()); 
		     player.playerUI.rand14_2.setPreferredSize(new Dimension(iconPlayer2.getIconWidth(),
		                                        iconPlayer2.getIconHeight()));
		     boardPlayer3.setBounds(0, 0, iconPlayer3.getIconWidth(),
		                                  iconPlayer3.getIconHeight()); 
		     player.playerUI.rand14_3.setPreferredSize(new Dimension(iconPlayer3.getIconWidth(),
		                                        iconPlayer3.getIconHeight()));
		     boardPlayer4.setBounds(0, 0, iconPlayer4.getIconWidth(),
		                                  iconPlayer4.getIconHeight()); 
		     player.playerUI.rand14_4.setPreferredSize(new Dimension(iconPlayer4.getIconWidth(),
		                                        iconPlayer4.getIconHeight()));
		     boardPlayer5.setBounds(0, 0, iconPlayer5.getIconWidth(),
		                                  iconPlayer5.getIconHeight()); 
		     player.playerUI.rand14_5.setPreferredSize(new Dimension(iconPlayer5.getIconWidth(),
		                                        iconPlayer5.getIconHeight()));
		     boardPlayer6.setBounds(0, 0, iconPlayer6.getIconWidth(),
		                                  iconPlayer6.getIconHeight()); 
		     player.playerUI.rand14_6.setPreferredSize(new Dimension(iconPlayer6.getIconWidth(),
		                                        iconPlayer6.getIconHeight()));
		     boardPlayer7.setBounds(0, 0, iconPlayer7.getIconWidth(),
		                                  iconPlayer7.getIconHeight()); 
		     player.playerUI.rand14_7.setPreferredSize(new Dimension(iconPlayer7.getIconWidth(),
		                                        iconPlayer7.getIconHeight()));
		     boardPlayer8.setBounds(0, 0, iconPlayer8.getIconWidth(),
		                                  iconPlayer8.getIconHeight()); 
		     player.playerUI.rand14_8.setPreferredSize(new Dimension(iconPlayer8.getIconWidth(),
		                                        iconPlayer8.getIconHeight()));
		     boardPlayer9.setBounds(0, 0, iconPlayer9.getIconWidth(),
		                                  iconPlayer9.getIconHeight()); 
		     player.playerUI.rand14_9.setPreferredSize(new Dimension(iconPlayer9.getIconWidth(),
		                                        iconPlayer9.getIconHeight()));
		     boardPlayer10.setBounds(0, 0, iconPlayer10.getIconWidth(),
		                                  iconPlayer10.getIconHeight()); 
		     player.playerUI.rand14_10.setPreferredSize(new Dimension(iconPlayer10.getIconWidth(),
		                                        iconPlayer10.getIconHeight()));													 													 
		     boardPlayer11.setBounds(0, 0, iconPlayer11.getIconWidth(),
		                                  iconPlayer11.getIconHeight()); 
		     player.playerUI.rand14_11.setPreferredSize(new Dimension(iconPlayer11.getIconWidth(),
		                                        iconPlayer11.getIconHeight()));
		     boardPlayer12.setBounds(0, 0, iconPlayer12.getIconWidth(),
		                                  iconPlayer12.getIconHeight()); 
		     player.playerUI.rand14_12.setPreferredSize(new Dimension(iconPlayer12.getIconWidth(),
		                                        iconPlayer12.getIconHeight()));
		     boardPlayer13.setBounds(0, 0, iconPlayer13.getIconWidth(),
		                                  iconPlayer13.getIconHeight()); 
		     player.playerUI.rand14_13.setPreferredSize(new Dimension(iconPlayer13.getIconWidth(),
		                                        iconPlayer13.getIconHeight()));
		     boardPlayer14.setBounds(0, 0, iconPlayer14.getIconWidth(),
		                                  iconPlayer14.getIconHeight()); 
		     player.playerUI.rand14_14.setPreferredSize(new Dimension(iconPlayer14.getIconWidth(),
		                                        iconPlayer14.getIconHeight()));
		     boardPlayer15.setBounds(0, 0, iconPlayer15.getIconWidth(),
		                                  iconPlayer15.getIconHeight()); 
		     player.playerUI.rand14_15.setPreferredSize(new Dimension(iconPlayer15.getIconWidth(),
		                                        iconPlayer15.getIconHeight()));	
		     boardPlayer16.setBounds(0, 0, iconPlayer16.getIconWidth(),
		                                  iconPlayer16.getIconHeight()); 
		     player.playerUI.rand14_16.setPreferredSize(new Dimension(iconPlayer16.getIconWidth(),
		                                        iconPlayer16.getIconHeight()));													 												 													 
		
			  player.playerUI.mypanel2.add(player.playerUI.rand14_1);
		     player.playerUI.mypanel2.add(player.playerUI.rand14_2);
		     player.playerUI.mypanel2.add(player.playerUI.rand14_3);
		     player.playerUI.mypanel2.add(player.playerUI.rand14_4);
		     player.playerUI.mypanel2.add(player.playerUI.rand14_5);
		     player.playerUI.mypanel2.add(player.playerUI.rand14_6);
		     player.playerUI.mypanel2.add(player.playerUI.rand14_7);
		     player.playerUI.mypanel2.add(player.playerUI.rand14_8);
		     player.playerUI.mypanel2.add(player.playerUI.rand14_9);
		     player.playerUI.mypanel2.add(player.playerUI.rand14_10);
		     player.playerUI.mypanel2.add(player.playerUI.rand14_11);
		     player.playerUI.mypanel2.add(player.playerUI.rand14_12);
		     player.playerUI.mypanel2.add(player.playerUI.rand14_13);
		     player.playerUI.mypanel2.add(player.playerUI.rand14_14);
		     player.playerUI.mypanel2.add(player.playerUI.rand14_15);
		     player.playerUI.mypanel2.add(player.playerUI.rand14_16);
		  
		  }
		  else if(tile == 5)
		  {
  	        iconPlayer1 =  (bp[4][4].getIcon());
	        iconPlayer2 =  (bp[5][4].getIcon());
 	        iconPlayer3 =  (bp[6][4].getIcon());
	        iconPlayer4 =  (bp[7][4].getIcon());
	        iconPlayer5 =  (bp[4][5].getIcon());
	        iconPlayer6 =  (bp[5][5].getIcon());
	        iconPlayer7 =  (bp[6][5].getIcon());
	        iconPlayer8 =  (bp[7][5].getIcon());
	        iconPlayer9 =  (bp[4][6].getIcon());
	        iconPlayer10 =  (bp[5][6].getIcon());
	        iconPlayer11 =  (bp[6][6].getIcon());
	        iconPlayer12 =  (bp[7][6].getIcon());
	        iconPlayer13 =  (bp[4][7].getIcon());
	        iconPlayer14 =  (bp[5][7].getIcon());
	        iconPlayer15 =  (bp[6][7].getIcon());
	        iconPlayer16 =  (bp[7][7].getIcon());

	
		//add components to board
			  GameJLabel boardPlayer1 = new GameJLabel(iconPlayer1);
			  GameJLabel boardPlayer2 = new GameJLabel(iconPlayer2);
			  GameJLabel boardPlayer3 = new GameJLabel(iconPlayer3);
			  GameJLabel boardPlayer4 = new GameJLabel(iconPlayer4);
			  GameJLabel boardPlayer5 = new GameJLabel(iconPlayer5);
			  GameJLabel boardPlayer6 = new GameJLabel(iconPlayer6);
			  GameJLabel boardPlayer7 = new GameJLabel(iconPlayer7);
			  GameJLabel boardPlayer8 = new GameJLabel(iconPlayer8);
			  GameJLabel boardPlayer9 = new GameJLabel(iconPlayer9);
			  GameJLabel boardPlayer10 = new GameJLabel(iconPlayer10);
			  GameJLabel boardPlayer11 = new GameJLabel(iconPlayer11);
			  GameJLabel boardPlayer12 = new GameJLabel(iconPlayer12);
			  GameJLabel boardPlayer13 = new GameJLabel(iconPlayer13);
			  GameJLabel boardPlayer14 = new GameJLabel(iconPlayer14);
			  GameJLabel boardPlayer15 = new GameJLabel(iconPlayer15);
			  GameJLabel boardPlayer16 = new GameJLabel(iconPlayer16);
		
			  player.playerUI.rand14_1.add(boardPlayer1, new Integer(0));
		     player.playerUI.rand14_2.add(boardPlayer2, new Integer(0));
		     player.playerUI.rand14_3.add(boardPlayer3, new Integer(0));
		     player.playerUI.rand14_4.add(boardPlayer4, new Integer(0));
		     player.playerUI.rand14_5.add(boardPlayer5, new Integer(0));
		     player.playerUI.rand14_6.add(boardPlayer6, new Integer(0));
		     player.playerUI.rand14_7.add(boardPlayer7, new Integer(0));
		     player.playerUI.rand14_8.add(boardPlayer8, new Integer(0));
		     player.playerUI.rand14_9.add(boardPlayer9, new Integer(0));
		     player.playerUI.rand14_10.add(boardPlayer10, new Integer(0));
		     player.playerUI.rand14_11.add(boardPlayer11, new Integer(0));
		     player.playerUI.rand14_12.add(boardPlayer12, new Integer(0));
		     player.playerUI.rand14_13.add(boardPlayer13, new Integer(0));
		     player.playerUI.rand14_14.add(boardPlayer14, new Integer(0));
		     player.playerUI.rand14_15.add(boardPlayer15, new Integer(0));
		     player.playerUI.rand14_16.add(boardPlayer16, new Integer(0));
		
		     boardPlayer1.setBounds(0, 0, iconPlayer1.getIconWidth(),
		                                  iconPlayer1.getIconHeight()); 
		     player.playerUI.rand14_1.setPreferredSize(new Dimension(iconPlayer1.getIconWidth(),
		                                        iconPlayer1.getIconHeight()));
		     boardPlayer2.setBounds(0, 0, iconPlayer2.getIconWidth(),
		                                  iconPlayer2.getIconHeight()); 
		     player.playerUI.rand14_2.setPreferredSize(new Dimension(iconPlayer2.getIconWidth(),
		                                        iconPlayer2.getIconHeight()));
		     boardPlayer3.setBounds(0, 0, iconPlayer3.getIconWidth(),
		                                  iconPlayer3.getIconHeight()); 
		     player.playerUI.rand14_3.setPreferredSize(new Dimension(iconPlayer3.getIconWidth(),
		                                        iconPlayer3.getIconHeight()));
		     boardPlayer4.setBounds(0, 0, iconPlayer4.getIconWidth(),
		                                  iconPlayer4.getIconHeight()); 
		     player.playerUI.rand14_4.setPreferredSize(new Dimension(iconPlayer4.getIconWidth(),
		                                        iconPlayer4.getIconHeight()));
		     boardPlayer5.setBounds(0, 0, iconPlayer5.getIconWidth(),
		                                  iconPlayer5.getIconHeight()); 
		     player.playerUI.rand14_5.setPreferredSize(new Dimension(iconPlayer5.getIconWidth(),
		                                        iconPlayer5.getIconHeight()));
		     boardPlayer6.setBounds(0, 0, iconPlayer6.getIconWidth(),
		                                  iconPlayer6.getIconHeight()); 
		     player.playerUI.rand14_6.setPreferredSize(new Dimension(iconPlayer6.getIconWidth(),
		                                        iconPlayer6.getIconHeight()));
		     boardPlayer7.setBounds(0, 0, iconPlayer7.getIconWidth(),
		                                  iconPlayer7.getIconHeight()); 
		     player.playerUI.rand14_7.setPreferredSize(new Dimension(iconPlayer7.getIconWidth(),
		                                        iconPlayer7.getIconHeight()));
		     boardPlayer8.setBounds(0, 0, iconPlayer8.getIconWidth(),
		                                  iconPlayer8.getIconHeight()); 
		     player.playerUI.rand14_8.setPreferredSize(new Dimension(iconPlayer8.getIconWidth(),
		                                        iconPlayer8.getIconHeight()));
		     boardPlayer9.setBounds(0, 0, iconPlayer9.getIconWidth(),
		                                  iconPlayer9.getIconHeight()); 
		     player.playerUI.rand14_9.setPreferredSize(new Dimension(iconPlayer9.getIconWidth(),
		                                        iconPlayer9.getIconHeight()));
		     boardPlayer10.setBounds(0, 0, iconPlayer10.getIconWidth(),
		                                  iconPlayer10.getIconHeight()); 
		     player.playerUI.rand14_10.setPreferredSize(new Dimension(iconPlayer10.getIconWidth(),
		                                        iconPlayer10.getIconHeight()));													 													 
		     boardPlayer11.setBounds(0, 0, iconPlayer11.getIconWidth(),
		                                  iconPlayer11.getIconHeight()); 
		     player.playerUI.rand14_11.setPreferredSize(new Dimension(iconPlayer11.getIconWidth(),
		                                        iconPlayer11.getIconHeight()));
		     boardPlayer12.setBounds(0, 0, iconPlayer12.getIconWidth(),
		                                  iconPlayer12.getIconHeight()); 
		     player.playerUI.rand14_12.setPreferredSize(new Dimension(iconPlayer12.getIconWidth(),
		                                        iconPlayer12.getIconHeight()));
		     boardPlayer13.setBounds(0, 0, iconPlayer13.getIconWidth(),
		                                  iconPlayer13.getIconHeight()); 
		     player.playerUI.rand14_13.setPreferredSize(new Dimension(iconPlayer13.getIconWidth(),
		                                        iconPlayer13.getIconHeight()));
		     boardPlayer14.setBounds(0, 0, iconPlayer14.getIconWidth(),
		                                  iconPlayer14.getIconHeight()); 
		     player.playerUI.rand14_14.setPreferredSize(new Dimension(iconPlayer14.getIconWidth(),
		                                        iconPlayer14.getIconHeight()));
		     boardPlayer15.setBounds(0, 0, iconPlayer15.getIconWidth(),
		                                  iconPlayer15.getIconHeight()); 
		     player.playerUI.rand14_15.setPreferredSize(new Dimension(iconPlayer15.getIconWidth(),
		                                        iconPlayer15.getIconHeight()));	
		     boardPlayer16.setBounds(0, 0, iconPlayer16.getIconWidth(),
		                                  iconPlayer16.getIconHeight()); 
		     player.playerUI.rand14_16.setPreferredSize(new Dimension(iconPlayer16.getIconWidth(),
		                                        iconPlayer16.getIconHeight()));													 												 													 
		
			  player.playerUI.mypanel5.add(player.playerUI.rand14_1);
		     player.playerUI.mypanel5.add(player.playerUI.rand14_2);
		     player.playerUI.mypanel5.add(player.playerUI.rand14_3);
		     player.playerUI.mypanel5.add(player.playerUI.rand14_4);
		     player.playerUI.mypanel5.add(player.playerUI.rand14_5);
		     player.playerUI.mypanel5.add(player.playerUI.rand14_6);
		     player.playerUI.mypanel5.add(player.playerUI.rand14_7);
		     player.playerUI.mypanel5.add(player.playerUI.rand14_8);
		     player.playerUI.mypanel5.add(player.playerUI.rand14_9);
		     player.playerUI.mypanel5.add(player.playerUI.rand14_10);
		     player.playerUI.mypanel5.add(player.playerUI.rand14_11);
		     player.playerUI.mypanel5.add(player.playerUI.rand14_12);
		     player.playerUI.mypanel5.add(player.playerUI.rand14_13);
		     player.playerUI.mypanel5.add(player.playerUI.rand14_14);
		     player.playerUI.mypanel5.add(player.playerUI.rand14_15);
		     player.playerUI.mypanel5.add(player.playerUI.rand14_16);
		  
		  }
		  else if(tile == 6)
		  {
  	        iconPlayer1 =  (bp[4][8].getIcon());
	        iconPlayer2 =  (bp[5][8].getIcon());
 	        iconPlayer3 =  (bp[6][8].getIcon());
	        iconPlayer4 =  (bp[7][8].getIcon());
	        iconPlayer5 =  (bp[4][9].getIcon());
	        iconPlayer6 =  (bp[5][9].getIcon());
	        iconPlayer7 =  (bp[6][9].getIcon());
	        iconPlayer8 =  (bp[7][9].getIcon());
	        iconPlayer9 =  (bp[4][10].getIcon());
	        iconPlayer10 =  (bp[5][10].getIcon());
	        iconPlayer11 =  (bp[6][10].getIcon());
	        iconPlayer12 =  (bp[7][10].getIcon());
	        iconPlayer13 =  (bp[4][11].getIcon());
	        iconPlayer14 =  (bp[5][11].getIcon());
	        iconPlayer15 =  (bp[6][11].getIcon());
	        iconPlayer16 =  (bp[7][11].getIcon());

	
		//add components to board
			  GameJLabel boardPlayer1 = new GameJLabel(iconPlayer1);
			  GameJLabel boardPlayer2 = new GameJLabel(iconPlayer2);
			  GameJLabel boardPlayer3 = new GameJLabel(iconPlayer3);
			  GameJLabel boardPlayer4 = new GameJLabel(iconPlayer4);
			  GameJLabel boardPlayer5 = new GameJLabel(iconPlayer5);
			  GameJLabel boardPlayer6 = new GameJLabel(iconPlayer6);
			  GameJLabel boardPlayer7 = new GameJLabel(iconPlayer7);
			  GameJLabel boardPlayer8 = new GameJLabel(iconPlayer8);
			  GameJLabel boardPlayer9 = new GameJLabel(iconPlayer9);
			  GameJLabel boardPlayer10 = new GameJLabel(iconPlayer10);
			  GameJLabel boardPlayer11 = new GameJLabel(iconPlayer11);
			  GameJLabel boardPlayer12 = new GameJLabel(iconPlayer12);
			  GameJLabel boardPlayer13 = new GameJLabel(iconPlayer13);
			  GameJLabel boardPlayer14 = new GameJLabel(iconPlayer14);
			  GameJLabel boardPlayer15 = new GameJLabel(iconPlayer15);
			  GameJLabel boardPlayer16 = new GameJLabel(iconPlayer16);
		
			  player.playerUI.rand14_1.add(boardPlayer1, new Integer(0));
		     player.playerUI.rand14_2.add(boardPlayer2, new Integer(0));
		     player.playerUI.rand14_3.add(boardPlayer3, new Integer(0));
		     player.playerUI.rand14_4.add(boardPlayer4, new Integer(0));
		     player.playerUI.rand14_5.add(boardPlayer5, new Integer(0));
		     player.playerUI.rand14_6.add(boardPlayer6, new Integer(0));
		     player.playerUI.rand14_7.add(boardPlayer7, new Integer(0));
		     player.playerUI.rand14_8.add(boardPlayer8, new Integer(0));
		     player.playerUI.rand14_9.add(boardPlayer9, new Integer(0));
		     player.playerUI.rand14_10.add(boardPlayer10, new Integer(0));
		     player.playerUI.rand14_11.add(boardPlayer11, new Integer(0));
		     player.playerUI.rand14_12.add(boardPlayer12, new Integer(0));
		     player.playerUI.rand14_13.add(boardPlayer13, new Integer(0));
		     player.playerUI.rand14_14.add(boardPlayer14, new Integer(0));
		     player.playerUI.rand14_15.add(boardPlayer15, new Integer(0));
		     player.playerUI.rand14_16.add(boardPlayer16, new Integer(0));
		
		     boardPlayer1.setBounds(0, 0, iconPlayer1.getIconWidth(),
		                                  iconPlayer1.getIconHeight()); 
		     player.playerUI.rand14_1.setPreferredSize(new Dimension(iconPlayer1.getIconWidth(),
		                                        iconPlayer1.getIconHeight()));
		     boardPlayer2.setBounds(0, 0, iconPlayer2.getIconWidth(),
		                                  iconPlayer2.getIconHeight()); 
		     player.playerUI.rand14_2.setPreferredSize(new Dimension(iconPlayer2.getIconWidth(),
		                                        iconPlayer2.getIconHeight()));
		     boardPlayer3.setBounds(0, 0, iconPlayer3.getIconWidth(),
		                                  iconPlayer3.getIconHeight()); 
		     player.playerUI.rand14_3.setPreferredSize(new Dimension(iconPlayer3.getIconWidth(),
		                                        iconPlayer3.getIconHeight()));
		     boardPlayer4.setBounds(0, 0, iconPlayer4.getIconWidth(),
		                                  iconPlayer4.getIconHeight()); 
		     player.playerUI.rand14_4.setPreferredSize(new Dimension(iconPlayer4.getIconWidth(),
		                                        iconPlayer4.getIconHeight()));
		     boardPlayer5.setBounds(0, 0, iconPlayer5.getIconWidth(),
		                                  iconPlayer5.getIconHeight()); 
		     player.playerUI.rand14_5.setPreferredSize(new Dimension(iconPlayer5.getIconWidth(),
		                                        iconPlayer5.getIconHeight()));
		     boardPlayer6.setBounds(0, 0, iconPlayer6.getIconWidth(),
		                                  iconPlayer6.getIconHeight()); 
		     player.playerUI.rand14_6.setPreferredSize(new Dimension(iconPlayer6.getIconWidth(),
		                                        iconPlayer6.getIconHeight()));
		     boardPlayer7.setBounds(0, 0, iconPlayer7.getIconWidth(),
		                                  iconPlayer7.getIconHeight()); 
		     player.playerUI.rand14_7.setPreferredSize(new Dimension(iconPlayer7.getIconWidth(),
		                                        iconPlayer7.getIconHeight()));
		     boardPlayer8.setBounds(0, 0, iconPlayer8.getIconWidth(),
		                                  iconPlayer8.getIconHeight()); 
		     player.playerUI.rand14_8.setPreferredSize(new Dimension(iconPlayer8.getIconWidth(),
		                                        iconPlayer8.getIconHeight()));
		     boardPlayer9.setBounds(0, 0, iconPlayer9.getIconWidth(),
		                                  iconPlayer9.getIconHeight()); 
		     player.playerUI.rand14_9.setPreferredSize(new Dimension(iconPlayer9.getIconWidth(),
		                                        iconPlayer9.getIconHeight()));
		     boardPlayer10.setBounds(0, 0, iconPlayer10.getIconWidth(),
		                                  iconPlayer10.getIconHeight()); 
		     player.playerUI.rand14_10.setPreferredSize(new Dimension(iconPlayer10.getIconWidth(),
		                                        iconPlayer10.getIconHeight()));													 													 
		     boardPlayer11.setBounds(0, 0, iconPlayer11.getIconWidth(),
		                                  iconPlayer11.getIconHeight()); 
		     player.playerUI.rand14_11.setPreferredSize(new Dimension(iconPlayer11.getIconWidth(),
		                                        iconPlayer11.getIconHeight()));
		     boardPlayer12.setBounds(0, 0, iconPlayer12.getIconWidth(),
		                                  iconPlayer12.getIconHeight()); 
		     player.playerUI.rand14_12.setPreferredSize(new Dimension(iconPlayer12.getIconWidth(),
		                                        iconPlayer12.getIconHeight()));
		     boardPlayer13.setBounds(0, 0, iconPlayer13.getIconWidth(),
		                                  iconPlayer13.getIconHeight()); 
		     player.playerUI.rand14_13.setPreferredSize(new Dimension(iconPlayer13.getIconWidth(),
		                                        iconPlayer13.getIconHeight()));
		     boardPlayer14.setBounds(0, 0, iconPlayer14.getIconWidth(),
		                                  iconPlayer14.getIconHeight()); 
		     player.playerUI.rand14_14.setPreferredSize(new Dimension(iconPlayer14.getIconWidth(),
		                                        iconPlayer14.getIconHeight()));
		     boardPlayer15.setBounds(0, 0, iconPlayer15.getIconWidth(),
		                                  iconPlayer15.getIconHeight()); 
		     player.playerUI.rand14_15.setPreferredSize(new Dimension(iconPlayer15.getIconWidth(),
		                                        iconPlayer15.getIconHeight()));	
		     boardPlayer16.setBounds(0, 0, iconPlayer16.getIconWidth(),
		                                  iconPlayer16.getIconHeight()); 
		     player.playerUI.rand14_16.setPreferredSize(new Dimension(iconPlayer16.getIconWidth(),
		                                        iconPlayer16.getIconHeight()));													 												 													 
		
			  player.playerUI.mypanel6.add(player.playerUI.rand14_1);
		     player.playerUI.mypanel6.add(player.playerUI.rand14_2);
		     player.playerUI.mypanel6.add(player.playerUI.rand14_3);
		     player.playerUI.mypanel6.add(player.playerUI.rand14_4);
		     player.playerUI.mypanel6.add(player.playerUI.rand14_5);
		     player.playerUI.mypanel6.add(player.playerUI.rand14_6);
		     player.playerUI.mypanel6.add(player.playerUI.rand14_7);
		     player.playerUI.mypanel6.add(player.playerUI.rand14_8);
		     player.playerUI.mypanel6.add(player.playerUI.rand14_9);
		     player.playerUI.mypanel6.add(player.playerUI.rand14_10);
		     player.playerUI.mypanel6.add(player.playerUI.rand14_11);
		     player.playerUI.mypanel6.add(player.playerUI.rand14_12);
		     player.playerUI.mypanel6.add(player.playerUI.rand14_13);
		     player.playerUI.mypanel6.add(player.playerUI.rand14_14);
		     player.playerUI.mypanel6.add(player.playerUI.rand14_15);
		     player.playerUI.mypanel6.add(player.playerUI.rand14_16);
		  
		  }
		  else if(tile == 7)
		  {
	       iconPlayer1 =  bp[4][12].getIcon();
	       iconPlayer2 =  bp[5][12].getIcon();
	       iconPlayer3 =  bp[6][12].getIcon();
	       iconPlayer4 =  bp[7][12].getIcon();
	       iconPlayer5 =  bp[4][13].getIcon();
	       iconPlayer6 =  bp[5][13].getIcon();
	       iconPlayer7 =  bp[6][13].getIcon();
	       iconPlayer8 =  bp[7][13].getIcon();
	       iconPlayer9 =  bp[4][14].getIcon();
	       iconPlayer10 =  bp[5][14].getIcon();
	       iconPlayer11 =  bp[6][14].getIcon();
	       iconPlayer12 =  bp[7][14].getIcon();
	       iconPlayer13 =  bp[4][15].getIcon();
	       iconPlayer14 =  bp[5][15].getIcon();
	       iconPlayer15 =  bp[6][15].getIcon();
	       iconPlayer16 =  bp[7][15].getIcon();


		//add components to board
			  GameJLabel boardPlayer1 = new GameJLabel(iconPlayer1);
			  GameJLabel boardPlayer2 = new GameJLabel(iconPlayer2);
			  GameJLabel boardPlayer3 = new GameJLabel(iconPlayer3);
			  GameJLabel boardPlayer4 = new GameJLabel(iconPlayer4);
			  GameJLabel boardPlayer5 = new GameJLabel(iconPlayer5);
			  GameJLabel boardPlayer6 = new GameJLabel(iconPlayer6);
			  GameJLabel boardPlayer7 = new GameJLabel(iconPlayer7);
			  GameJLabel boardPlayer8 = new GameJLabel(iconPlayer8);
			  GameJLabel boardPlayer9 = new GameJLabel(iconPlayer9);
			  GameJLabel boardPlayer10 = new GameJLabel(iconPlayer10);
			  GameJLabel boardPlayer11 = new GameJLabel(iconPlayer11);
			  GameJLabel boardPlayer12 = new GameJLabel(iconPlayer12);
			  GameJLabel boardPlayer13 = new GameJLabel(iconPlayer13);
			  GameJLabel boardPlayer14 = new GameJLabel(iconPlayer14);
			  GameJLabel boardPlayer15 = new GameJLabel(iconPlayer15);
			  GameJLabel boardPlayer16 = new GameJLabel(iconPlayer16);
		
			  player.playerUI.rand14_1.add(boardPlayer1, new Integer(0));
		     player.playerUI.rand14_2.add(boardPlayer2, new Integer(0));
		     player.playerUI.rand14_3.add(boardPlayer3, new Integer(0));
		     player.playerUI.rand14_4.add(boardPlayer4, new Integer(0));
		     player.playerUI.rand14_5.add(boardPlayer5, new Integer(0));
		     player.playerUI.rand14_6.add(boardPlayer6, new Integer(0));
		     player.playerUI.rand14_7.add(boardPlayer7, new Integer(0));
		     player.playerUI.rand14_8.add(boardPlayer8, new Integer(0));
		     player.playerUI.rand14_9.add(boardPlayer9, new Integer(0));
		     player.playerUI.rand14_10.add(boardPlayer10, new Integer(0));
		     player.playerUI.rand14_11.add(boardPlayer11, new Integer(0));
		     player.playerUI.rand14_12.add(boardPlayer12, new Integer(0));
		     player.playerUI.rand14_13.add(boardPlayer13, new Integer(0));
		     player.playerUI.rand14_14.add(boardPlayer14, new Integer(0));
		     player.playerUI.rand14_15.add(boardPlayer15, new Integer(0));
		     player.playerUI.rand14_16.add(boardPlayer16, new Integer(0));
		
		     boardPlayer1.setBounds(0, 0, iconPlayer1.getIconWidth(),
		                                  iconPlayer1.getIconHeight()); 
		     player.playerUI.rand14_1.setPreferredSize(new Dimension(iconPlayer1.getIconWidth(),
		                                        iconPlayer1.getIconHeight()));
		     boardPlayer2.setBounds(0, 0, iconPlayer2.getIconWidth(),
		                                  iconPlayer2.getIconHeight()); 
		     player.playerUI.rand14_2.setPreferredSize(new Dimension(iconPlayer2.getIconWidth(),
		                                        iconPlayer2.getIconHeight()));
		     boardPlayer3.setBounds(0, 0, iconPlayer3.getIconWidth(),
		                                  iconPlayer3.getIconHeight()); 
		     player.playerUI.rand14_3.setPreferredSize(new Dimension(iconPlayer3.getIconWidth(),
		                                        iconPlayer3.getIconHeight()));
		     boardPlayer4.setBounds(0, 0, iconPlayer4.getIconWidth(),
		                                  iconPlayer4.getIconHeight()); 
		     player.playerUI.rand14_4.setPreferredSize(new Dimension(iconPlayer4.getIconWidth(),
		                                        iconPlayer4.getIconHeight()));
		     boardPlayer5.setBounds(0, 0, iconPlayer5.getIconWidth(),
		                                  iconPlayer5.getIconHeight()); 
		     player.playerUI.rand14_5.setPreferredSize(new Dimension(iconPlayer5.getIconWidth(),
		                                        iconPlayer5.getIconHeight()));
		     boardPlayer6.setBounds(0, 0, iconPlayer6.getIconWidth(),
		                                  iconPlayer6.getIconHeight()); 
		     player.playerUI.rand14_6.setPreferredSize(new Dimension(iconPlayer6.getIconWidth(),
		                                        iconPlayer6.getIconHeight()));
		     boardPlayer7.setBounds(0, 0, iconPlayer7.getIconWidth(),
		                                  iconPlayer7.getIconHeight()); 
		     player.playerUI.rand14_7.setPreferredSize(new Dimension(iconPlayer7.getIconWidth(),
		                                        iconPlayer7.getIconHeight()));
		     boardPlayer8.setBounds(0, 0, iconPlayer8.getIconWidth(),
		                                  iconPlayer8.getIconHeight()); 
		     player.playerUI.rand14_8.setPreferredSize(new Dimension(iconPlayer8.getIconWidth(),
		                                        iconPlayer8.getIconHeight()));
		     boardPlayer9.setBounds(0, 0, iconPlayer9.getIconWidth(),
		                                  iconPlayer9.getIconHeight()); 
		     player.playerUI.rand14_9.setPreferredSize(new Dimension(iconPlayer9.getIconWidth(),
		                                        iconPlayer9.getIconHeight()));
		     boardPlayer10.setBounds(0, 0, iconPlayer10.getIconWidth(),
		                                  iconPlayer10.getIconHeight()); 
		     player.playerUI.rand14_10.setPreferredSize(new Dimension(iconPlayer10.getIconWidth(),
		                                        iconPlayer10.getIconHeight()));													 													 
		     boardPlayer11.setBounds(0, 0, iconPlayer11.getIconWidth(),
		                                  iconPlayer11.getIconHeight()); 
		     player.playerUI.rand14_11.setPreferredSize(new Dimension(iconPlayer11.getIconWidth(),
		                                        iconPlayer11.getIconHeight()));
		     boardPlayer12.setBounds(0, 0, iconPlayer12.getIconWidth(),
		                                  iconPlayer12.getIconHeight()); 
		     player.playerUI.rand14_12.setPreferredSize(new Dimension(iconPlayer12.getIconWidth(),
		                                        iconPlayer12.getIconHeight()));
		     boardPlayer13.setBounds(0, 0, iconPlayer13.getIconWidth(),
		                                  iconPlayer13.getIconHeight()); 
		     player.playerUI.rand14_13.setPreferredSize(new Dimension(iconPlayer13.getIconWidth(),
		                                        iconPlayer13.getIconHeight()));
		     boardPlayer14.setBounds(0, 0, iconPlayer14.getIconWidth(),
		                                  iconPlayer14.getIconHeight()); 
		     player.playerUI.rand14_14.setPreferredSize(new Dimension(iconPlayer14.getIconWidth(),
		                                        iconPlayer14.getIconHeight()));
		     boardPlayer15.setBounds(0, 0, iconPlayer15.getIconWidth(),
		                                  iconPlayer15.getIconHeight()); 
		     player.playerUI.rand14_15.setPreferredSize(new Dimension(iconPlayer15.getIconWidth(),
		                                        iconPlayer15.getIconHeight()));	
		     boardPlayer16.setBounds(0, 0, iconPlayer16.getIconWidth(),
		                                  iconPlayer16.getIconHeight()); 
		     player.playerUI.rand14_16.setPreferredSize(new Dimension(iconPlayer16.getIconWidth(),
		                                        iconPlayer16.getIconHeight()));													 												 													 
		
			  player.playerUI.mypanel7.add(player.playerUI.rand14_1);
		     player.playerUI.mypanel7.add(player.playerUI.rand14_2);
		     player.playerUI.mypanel7.add(player.playerUI.rand14_3);
		     player.playerUI.mypanel7.add(player.playerUI.rand14_4);
		     player.playerUI.mypanel7.add(player.playerUI.rand14_5);
		     player.playerUI.mypanel7.add(player.playerUI.rand14_6);
		     player.playerUI.mypanel7.add(player.playerUI.rand14_7);
		     player.playerUI.mypanel7.add(player.playerUI.rand14_8);
		     player.playerUI.mypanel7.add(player.playerUI.rand14_9);
		     player.playerUI.mypanel7.add(player.playerUI.rand14_10);
		     player.playerUI.mypanel7.add(player.playerUI.rand14_11);
		     player.playerUI.mypanel7.add(player.playerUI.rand14_12);
		     player.playerUI.mypanel7.add(player.playerUI.rand14_13);
		     player.playerUI.mypanel7.add(player.playerUI.rand14_14);
		     player.playerUI.mypanel7.add(player.playerUI.rand14_15);
		     player.playerUI.mypanel7.add(player.playerUI.rand14_16);
		  }
		}

	private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException{
		try{
			bp = (BoardPeice[][])stream.readObject();
			iconPlayer1 = (GameImageIcon)stream.readObject();
			iconPlayer2 = (GameImageIcon)stream.readObject();
			iconPlayer3 = (GameImageIcon)stream.readObject();
			iconPlayer4 = (GameImageIcon)stream.readObject();
			iconPlayer5 = (GameImageIcon)stream.readObject();
			iconPlayer6 = (GameImageIcon)stream.readObject();
			iconPlayer7 = (GameImageIcon)stream.readObject();
			iconPlayer8 = (GameImageIcon)stream.readObject();
			iconPlayer9 = (GameImageIcon)stream.readObject();
			iconPlayer10 = (GameImageIcon)stream.readObject();
			iconPlayer11 = (GameImageIcon)stream.readObject();
			iconPlayer12 = (GameImageIcon)stream.readObject();
			iconPlayer13 = (GameImageIcon)stream.readObject();
			iconPlayer14 = (GameImageIcon)stream.readObject();
			iconPlayer15 = (GameImageIcon)stream.readObject();
			iconPlayer16 = (GameImageIcon)stream.readObject();
			play = (Player)stream.readObject();    
      }catch(IOException ioEx){
			System.out.println("IO EXCEPTION getSentObject\n");
			ioEx.printStackTrace();
		}
   }

   private void writeObject(ObjectOutputStream stream) throws IOException{
		try{
System.out.println(bp);
			stream.writeObject(bp);
			stream.writeObject(iconPlayer1);
			stream.writeObject(iconPlayer2);
			stream.writeObject(iconPlayer3);
			stream.writeObject(iconPlayer4);
			stream.writeObject(iconPlayer5);
			stream.writeObject(iconPlayer6);
			stream.writeObject(iconPlayer7);
			stream.writeObject(iconPlayer8);
			stream.writeObject(iconPlayer9);
			stream.writeObject(iconPlayer10);
			stream.writeObject(iconPlayer11);
			stream.writeObject(iconPlayer12);
			stream.writeObject(iconPlayer13);
			stream.writeObject(iconPlayer14);
			stream.writeObject(iconPlayer15);
			stream.writeObject(iconPlayer16);
			stream.writeObject(play);    
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
		
}//end LogicBoard

