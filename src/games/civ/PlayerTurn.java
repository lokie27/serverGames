package games.civ;

import javax.swing.*;
import java.awt.*;
import java.io.*; 

public class PlayerTurn implements Serializable
{

   private static final long serialVersionUID = -1949402727324466590L;

   public boolean startOfTurn;
	public boolean trade;
	public boolean cityManage;
   public boolean move;
	public boolean research;

 	public PlayerTurn()
	{
     startOfTurn = false;
	  trade = false;
	  cityManage = false;
	  move = false;
	  research = false;
   }

   public void startTurn(Player name)
   {
	  research = false;
	  startOfTurn = true;
System.out.println("START TURN");
	  name.playerUI.start(name);
	}

   public void tradeTurn(Player name)
   {
	  startOfTurn = false;
     trade = true;
	  name.playerUI.trade(name);
	}

   public void cityTurn(Player name)
   {
	  trade = false;
	  cityManage = true;
	  name.playerUI.city(name);
//	  return true;
	}

   public void moveTurn(Player name)
   {
	  cityManage = false;
	  move = true;
	  name.playerUI.move(name);
//	  return true;
	}

   public void researchTurn(Player name)
   {
	  move = false;
	  research = true;
     name.playerUI.setSpaceOwners();
	  name.playerUI.research(name);
	}

   public boolean getStartOfTurn() {
		return startOfTurn;
	}
	public void setStartOfTurn(boolean startOfTurn) {
		this.startOfTurn = startOfTurn;
	}
	public boolean getTrade() {
		return trade;
	}
	public void setTrade(boolean trade) {
		this.trade = trade;
	}
	public boolean getCityManage() {
		return cityManage;
	}
	public void setCityManage(boolean cityManage) {
		this.cityManage = cityManage;
	}
	public boolean getMove() {
		return move;
	}
	public void setMove(boolean move) {
		this.move = move;
	}
	public boolean getResearch() {
		return research;
	}
	public void setResearch(boolean research) {
		this.research = research;
	}
}//end PlayerTurn class


