package games.civ;

import javax.swing.*;
import java.util.Vector;
import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.border.*;
import java.io.Serializable;
import games.*;

public class Deck extends JComponent implements Serializable 
{
  private static final long serialVersionUID = 4646859353798226965L;

  private ArrayList<Card> infDeck; 
  private Vector mntDeck, artilDeck, acftDeck,
          ancientDeck, medievalDeck, modernDeck, wonderDeck, culture1Deck, culture2Deck, culture3Deck;
  private Vector infGrave, mntGrave, artilGrave, acftGrave;
  private JTextArea numInf, numMnt, numArt, numAcft, numWonders, numculture1, numculture2, numculture3;
  private GameJLayeredPane wonderPane, culture1Pane, culture2Pane, culture3Pane, infantryPane, mntPane, artilPane, acftPane;
  
  public Deck(){}//bean default
  
  public Deck(JComponent frame, String type)//maybe type for different Decks?
  {
    if(type.equals("wonder"))
	 {
		 initWonderDeck(frame);    
	    
	    for(int i = 0; i < 2; i++)//number of players
		 {

		    CardLayout card = new CardLayout(1, 1);
		
			 wonderPane = new GameJLayeredPane();
		    wonderPane.setLayout(card);
			 wonderPane.setBounds(10, 8, 89, 54);
		    
			 for(int k = 0; k < wonderDeck.size(); k++)
			 {
		      Card c = (Card)wonderDeck.get(k);
			   wonderPane.add(c.getLabel(), ""+k);
			 }
	
//			 frame.add(wonderPane, new Integer(2));
//		    frame.add(numWonders, new Integer(3));
       }
    }
    else if(type.equals("culture1"))
	 {
		 initCulture1Deck(frame);    
	    
	    CardLayout card = new CardLayout(1, 1);
	
		 culture1Pane = new GameJLayeredPane();
	    culture1Pane.setLayout(card);
		 culture1Pane.setBounds(55, 345, 45, 65);
	    culture1Pane.setBorder(new MatteBorder(5, 5, 5, 5, new Color(50, 50, 50)));
	    
		 for(int k = 0; k < culture1Deck.size(); k++)
		 {
	      Card c = (Card)culture1Deck.get(k);
		   culture1Pane.add(c.getLabel(), ""+k);
		 }
	
	    frame.add(culture1Pane, new Integer(2));
	    frame.add(numculture1, new Integer(3));
    }
    else if(type.equals("culture2"))
	 {
		 initCulture2Deck(frame);    
	    
	    CardLayout card = new CardLayout(1, 1);
	
		 culture2Pane = new GameJLayeredPane();
	    culture2Pane.setLayout(card);
		 culture2Pane.setBounds(305, 345, 45, 65);
	    culture2Pane.setBorder(new MatteBorder(5, 5, 5, 5, new Color(50, 50, 50)));
	    
		 for(int k = 0; k < culture2Deck.size(); k++)
		 {
	      Card c = (Card)culture2Deck.get(k);
		   culture2Pane.add(c.getLabel(), ""+k);
		 }
	
	    frame.add(culture2Pane, new Integer(2));
	    frame.add(numculture2, new Integer(3));
    }
    else if(type.equals("culture3"))
	 {
		 initCulture3Deck(frame);    
	    
	    CardLayout card = new CardLayout(1, 1);
	
		 culture3Pane = new GameJLayeredPane();
	    culture3Pane.setLayout(card);
		 culture3Pane.setBounds(560, 345, 45, 65);
	    culture3Pane.setBorder(new MatteBorder(5, 5, 5, 5, new Color(50, 50, 50)));
	    
		 for(int k = 0; k < culture3Deck.size(); k++)
		 {
	      Card c = (Card)culture3Deck.get(k);
		   culture3Pane.add(c.getLabel(), ""+k);
		 }
	
	    frame.add(culture3Pane, new Integer(2));
	    frame.add(numculture3, new Integer(3));
    }
  }
  
  public Deck(JComponent frame)
  {
	 initInfantry(frame);
    initMounted(frame);
    initArtillery(frame);
    initAircraft(frame);
  
    CardLayout card = new CardLayout(1, 1);

    infantryPane = new GameJLayeredPane();
    infantryPane.setLayout(card);
  
    for(int k = 0; k < infDeck.size(); k++)  
	 {
	    Card c = (Card)infDeck.get(k); 
////System.out.println(c.getValue());		 
		 infantryPane.add(c.getLabel(), ""+k);
	 }	 
	 infantryPane.setBounds(498, 43, 85, 85); 
    numInf.setText(Integer.toString(infDeck.size()-infGrave.size()));

    mntPane = new GameJLayeredPane();
    mntPane.setLayout(card);
  
    for(int k = 0; k < mntDeck.size(); k++)  
	 {
	    Card c = (Card)mntDeck.get(k); 
////System.out.println(c.getValue());		 
		 mntPane.add(c.getLabel(), ""+k);
	 }	 
	 mntPane.setBounds(498, 205, 85, 85); 
    numMnt.setText(Integer.toString(mntDeck.size()-mntGrave.size()));

    artilPane = new GameJLayeredPane();
    artilPane.setLayout(card);
  
    for(int k = 0; k < artilDeck.size(); k++)  
	 {
	    Card c = (Card)artilDeck.get(k); 
////System.out.println(c.getValue());		 
		 artilPane.add(c.getLabel(), ""+k);
	 }	 
	 artilPane.setBounds(683, 43, 85, 85); 
    numArt.setText(Integer.toString(artilDeck.size()-artilGrave.size()));

    acftPane = new GameJLayeredPane();
    acftPane.setLayout(card);
  
    for(int k = 0; k < acftDeck.size(); k++)  
	 {
	    Card c = (Card)acftDeck.get(k); 
////System.out.println(c.getValue());		 
		 acftPane.add(c.getLabel(), ""+k);
	 }	 
	 acftPane.setBounds(683, 205, 85, 85); 
    numAcft.setText(Integer.toString(acftDeck.size()-acftGrave.size()));

//  	 frame.add(infantryPane, new Integer(2));
//  	 frame.add(numInf, new Integer(3));

//  	 frame.add(mntPane, new Integer(2));
//  	 frame.add(numMnt, new Integer(3));

//  	 frame.add(artilPane, new Integer(2));
//  	 frame.add(numArt, new Integer(3));

//  	 frame.add(acftPane, new Integer(2));
//  	 frame.add(numAcft, new Integer(3));

  } 

  public Deck(String type, Deck d, Player p)
  {
    if(type.equals("wonder"))
	 {
      CardLayout card = new CardLayout(1, 1);
		
	   wonderPane = new GameJLayeredPane();
	   wonderPane.setLayout(card);
	   wonderPane.setBounds(10, 8, 89, 54);

	   wonderDeck = new Vector(0, 1);
		for(int k = 0; k < d.wonderDeck.size(); k++)
		{
        Card temp = (Card)d.wonderDeck.get(k);
        Card c = new Card("wonder", temp.getValue());  
		  wonderDeck.add(c);  
		  wonderPane.add(c.getLabel(), ""+k);
      }

		numWonders = new JTextArea();
      numWonders.setOpaque(false);
      numWonders.setEditable(false);
      numWonders.setForeground(new Color(255, 255, 255));
      numWonders.setBounds(20, 10, 15, 15);
      numWonders.setText(Integer.toString(wonderDeck.size()));

      if(p.playerUI.mainBrd2.getIndexOf(d.numWonders) >= 0)  
	   {
//System.out.println("numW "+p.playerUI.mainBrd2.getIndexOf(d.numWonders));
		  p.playerUI.mainBrd2.remove(p.playerUI.mainBrd2.getIndexOf(d.numWonders));
        p.playerUI.mainBrd2.repaint();
		}
      if(p.playerUI.mainBrd2.getIndexOf(d.wonderPane) >= 0)
		{
//System.out.println("Wpane "+p.playerUI.mainBrd2.getIndexOf(d.wonderPane));
		  p.playerUI.mainBrd2.remove(p.playerUI.mainBrd2.getIndexOf(d.wonderPane));
        p.playerUI.mainBrd2.repaint();
      }

		p.playerUI.mainBrd2.add(wonderPane, new Integer(2));
	   p.playerUI.mainBrd2.add(numWonders, new Integer(3));
      p.playerUI.mainBrd2.repaint();
	 }
  
    else if(type.equals("combat"))
	 {
	    CardLayout card = new CardLayout(1, 1);
	
	    infantryPane = new GameJLayeredPane();
	    infantryPane.setLayout(card);
	  
	    infDeck = new ArrayList<Card>();
	    infGrave = new Vector(0, 1);
	    numInf = new JTextArea();
	    numInf.setOpaque(false);
	    numInf.setEditable(false);
	    numInf.setForeground(new Color(255, 255, 255));
	    numInf.setBounds(500, 45, 15, 15);
	    for(int k = 0; k < d.infDeck.size(); k++)  
		 {
		    Card temp = (Card)d.infDeck.get(k); 
			 Card c = new Card(temp.getType(), temp.getValue());
          infDeck.add(c);
			 infantryPane.add(c.getLabel(), ""+k);
		 }	 
		 infantryPane.setBounds(498, 43, 85, 85); 
	    numInf.setText(Integer.toString(infDeck.size()-infGrave.size()));
	

	    mntPane = new GameJLayeredPane();
	    mntPane.setLayout(card);
	    mntDeck = new Vector(0, 1);
	    mntGrave = new Vector(0, 1);
	    numMnt = new JTextArea();
	    numMnt.setOpaque(false);
	    numMnt.setEditable(false);
	    numMnt.setForeground(new Color(255, 255, 255));
	    numMnt.setBounds(500, 205, 15, 15);
	    for(int k = 0; k < d.mntDeck.size(); k++)  
		 {
		    Card temp = (Card)d.mntDeck.get(k); 
			 Card c = new Card(temp.getType(), temp.getValue());
          mntDeck.add(c);
			 mntPane.add(c.getLabel(), ""+k);
		 }	 
		 mntPane.setBounds(498, 205, 85, 85); 
	    numMnt.setText(Integer.toString(mntDeck.size()-mntGrave.size()));
	
	    artilPane = new GameJLayeredPane();
	    artilPane.setLayout(card);
	    artilDeck = new Vector(0, 1);
	    artilGrave = new Vector(0, 1);
	    numArt = new JTextArea();
	    numArt.setOpaque(false);
	    numArt.setEditable(false);
	    numArt.setForeground(new Color(255, 255, 255));
	    numArt.setBounds(685, 45, 15, 15);
	    for(int k = 0; k < d.artilDeck.size(); k++)  
		 {
		    Card temp = (Card)d.artilDeck.get(k); 
			 Card c = new Card(temp.getType(), temp.getValue());
          artilDeck.add(c);  
			 artilPane.add(c.getLabel(), ""+k);
		 }	 
		 artilPane.setBounds(683, 43, 85, 85); 
	    numArt.setText(Integer.toString(artilDeck.size()-artilGrave.size()));
	
	    acftPane = new GameJLayeredPane();
	    acftPane.setLayout(card);
	    acftDeck = new Vector(0, 1);
	    acftGrave = new Vector(0, 1);
	    numAcft = new JTextArea();
	    numAcft.setOpaque(false);
	    numAcft.setEditable(false);
	    numAcft.setForeground(new Color(255, 255, 255));
	    numAcft.setBounds(690, 208, 15, 15);
	    for(int k = 0; k < d.acftDeck.size(); k++)  
		 {
		    Card temp = (Card)d.acftDeck.get(k); 
			 Card c = new Card(temp.getType(), temp.getValue());
			 acftDeck.add(c);
			 acftPane.add(c.getLabel(), ""+k);
		 }	 
		 acftPane.setBounds(683, 205, 85, 85); 
	    numAcft.setText(Integer.toString(acftDeck.size()-acftGrave.size()));

  	    p.playerUI.mainBrd2.add(infantryPane, new Integer(2));
  	    p.playerUI.mainBrd2.add(numInf, new Integer(3));

  	    p.playerUI.mainBrd2.add(mntPane, new Integer(2));
  	    p.playerUI.mainBrd2.add(numMnt, new Integer(3));

    	 p.playerUI.mainBrd2.add(artilPane, new Integer(2));
    	 p.playerUI.mainBrd2.add(numArt, new Integer(3));

   	 p.playerUI.mainBrd2.add(acftPane, new Integer(2));
   	 p.playerUI.mainBrd2.add(numAcft, new Integer(3));
	 }
  }
  
  private void initCulture1Deck(JComponent frame)
  {
    culture1Deck = new Vector(0, 1);

	 culture1Deck.add(new Card("culture1", 0));
	 culture1Deck.add(new Card("culture1", 0));
	 culture1Deck.add(new Card("culture1", 1));
	 culture1Deck.add(new Card("culture1", 1));
	 culture1Deck.add(new Card("culture1", 2));
	 culture1Deck.add(new Card("culture1", 2));
	 culture1Deck.add(new Card("culture1", 3));
	 culture1Deck.add(new Card("culture1", 3));
	 culture1Deck.add(new Card("culture1", 4));
	 culture1Deck.add(new Card("culture1", 5));
	 culture1Deck.add(new Card("culture1", 5));
	 culture1Deck.add(new Card("culture1", 5));
	 culture1Deck.add(new Card("culture1", 6));
	 culture1Deck.add(new Card("culture1", 6));
	 culture1Deck.add(new Card("culture1", 6));
	 culture1Deck.add(new Card("culture1", 7));
	 culture1Deck.add(new Card("culture1", 8));
	 culture1Deck.add(new Card("culture1", 8));
	 culture1Deck.add(new Card("culture1", 8));

    shuffle(culture1Deck);

    numculture1 = new JTextArea();
    numculture1.setOpaque(false);
    numculture1.setEditable(false);
    numculture1.setBounds(57, 346, 15, 15);
    numculture1.setForeground(new Color(255, 255, 255));
    numculture1.setText(Integer.toString(culture1Deck.size()));
  }
 
  private void initCulture2Deck(JComponent frame)
  {
    culture2Deck = new Vector(0, 1);
	 culture2Deck.add(new Card("culture2", 0));
	 culture2Deck.add(new Card("culture2", 1));
	 culture2Deck.add(new Card("culture2", 2));
	 culture2Deck.add(new Card("culture2", 2));
	 culture2Deck.add(new Card("culture2", 3));
	 culture2Deck.add(new Card("culture2", 3));
	 culture2Deck.add(new Card("culture2", 4));
	 culture2Deck.add(new Card("culture2", 4));
	 culture2Deck.add(new Card("culture2", 4));
	 culture2Deck.add(new Card("culture2", 5));
	 culture2Deck.add(new Card("culture2", 5));
	 culture2Deck.add(new Card("culture2", 6));
	 culture2Deck.add(new Card("culture2", 6));
	 culture2Deck.add(new Card("culture2", 7));
	 culture2Deck.add(new Card("culture2", 8));
	 culture2Deck.add(new Card("culture2", 8));
	 culture2Deck.add(new Card("culture2", 8));

    shuffle(culture2Deck);

    numculture2 = new JTextArea();
    numculture2.setOpaque(false);
    numculture2.setEditable(false);
    numculture2.setForeground(new Color(255, 255, 255));
    numculture2.setBounds(308, 347, 15, 15);
    numculture2.setText(Integer.toString(culture2Deck.size()));
  }
    
  private void initCulture3Deck(JComponent frame)
  {
    culture3Deck = new Vector(0, 1);

	 culture3Deck.add(new Card("culture3", 0));
	 culture3Deck.add(new Card("culture3", 1));
	 culture3Deck.add(new Card("culture3", 2));
	 culture3Deck.add(new Card("culture3", 2));
	 culture3Deck.add(new Card("culture3", 3));
	 culture3Deck.add(new Card("culture3", 3));
	 culture3Deck.add(new Card("culture3", 4));
	 culture3Deck.add(new Card("culture3", 4));
	 culture3Deck.add(new Card("culture3", 5));
	 culture3Deck.add(new Card("culture3", 6));
	 culture3Deck.add(new Card("culture3", 6));

    shuffle(culture3Deck);

    numculture3 = new JTextArea();
    numculture3.setOpaque(false);
    numculture3.setEditable(false);
    numculture3.setForeground(new Color(255, 255, 255));
    numculture3.setBounds(563, 347, 15, 15);
    numculture3.setText(Integer.toString(culture3Deck.size()));
  }  

  private void initWonderDeck(JComponent frame)
  {
    ancientDeck = new Vector(0, 1);
    medievalDeck = new Vector(0, 1);
    modernDeck = new Vector(0, 1);
    wonderDeck = new Vector(0, 1);
	 
	 ancientDeck.add(new Card("wonder", 0));
	 ancientDeck.add(new Card("wonder", 1));
	 ancientDeck.add(new Card("wonder", 2));
	 ancientDeck.add(new Card("wonder", 3));
	 medievalDeck.add(new Card("wonder", 4));
	 medievalDeck.add(new Card("wonder", 5));
	 medievalDeck.add(new Card("wonder", 6));
	 medievalDeck.add(new Card("wonder", 7));
	 modernDeck.add(new Card("wonder", 8));
	 modernDeck.add(new Card("wonder", 9));
	 modernDeck.add(new Card("wonder", 10));
	 modernDeck.add(new Card("wonder", 11));
    
    shuffle(ancientDeck);
    shuffle(medievalDeck);
    shuffle(modernDeck);
  
    for(int k = 0; k < 4; k++)
	 {
	   wonderDeck.add(ancientDeck.get(k));
	 }
    for(int k = 0; k < 4; k++)
	 {
	   wonderDeck.add(medievalDeck.get(k));
	 }
    for(int k = 0; k < 4; k++)
	 {
	   wonderDeck.add(modernDeck.get(k));
	 }

    numWonders = new JTextArea();
    numWonders.setOpaque(false);
    numWonders.setEditable(false);
    numWonders.setForeground(new Color(255, 255, 255));
    numWonders.setBounds(20, 10, 15, 15);
    numWonders.setText(Integer.toString(wonderDeck.size()));
  }
  
  private void initInfantry(JComponent frame)
  {
    infDeck = new ArrayList<Card>();
    infGrave = new Vector(0, 1);
    numInf = new JTextArea();
    numInf.setOpaque(false);
    numInf.setEditable(false);
    numInf.setForeground(new Color(255, 255, 255));
    numInf.setBounds(500, 45, 15, 15);

    for(int k = 0; k < 3; k++)
	 {
      infDeck.add(new Card("infantry", 1));	 
	 }
    for(int k = 3; k < 12; k++)
	 {
      infDeck.add(new Card("infantry", 2));	 
	 }
    for(int k = 12; k < 15; k++)
	 {
      infDeck.add(new Card("infantry", 3));	 
	 }
    shuffle(infDeck);
  }
   
  private void initMounted(JComponent frame)
  {
    mntDeck = new Vector(0, 1);
    mntGrave = new Vector(0, 1);
    numMnt = new JTextArea();
    numMnt.setOpaque(false);
    numMnt.setEditable(false);
    numMnt.setForeground(new Color(255, 255, 255));
    numMnt.setBounds(500, 205, 15, 15);
    for(int k = 0; k < 3; k++)
	 {
      mntDeck.add(new Card("mounted", 1));	 
	 }
    for(int k = 3; k < 12; k++)
	 {
      mntDeck.add(new Card("mounted", 2));	 
	 }
    for(int k = 12; k < 15; k++)
	 {
      mntDeck.add(new Card("mounted", 3));	 
	 }
    shuffle(mntDeck);

  }

  private void initArtillery(JComponent frame)
  {
    artilDeck = new Vector(0, 1);
    artilGrave = new Vector(0, 1);
    numArt = new JTextArea();
    numArt.setOpaque(false);
    numArt.setEditable(false);
    numArt.setForeground(new Color(255, 255, 255));
    numArt.setBounds(685, 45, 15, 15);
    for(int k = 0; k < 3; k++)
	 {
      artilDeck.add(new Card("artillery", 1));	 
	 }
    for(int k = 3; k < 12; k++)
	 {
      artilDeck.add(new Card("artillery", 2));	 
	 }
    for(int k = 12; k < 15; k++)
	 {
      artilDeck.add(new Card("artillery", 3));	 
	 }
    shuffle(artilDeck);
  }

  private void initAircraft(JComponent frame)
  {
    acftDeck = new Vector(0, 1);
    acftGrave = new Vector(0, 1);
    numAcft = new JTextArea();
    numAcft.setOpaque(false);
    numAcft.setEditable(false);
    numAcft.setForeground(new Color(255, 255, 255));
    numAcft.setBounds(690, 208, 15, 15);
    for(int k = 0; k < 3; k++)
	 {
      acftDeck.add(new Card("aircraft", 5));	 
	 }
    for(int k = 3; k < 6; k++)
	 {
      acftDeck.add(new Card("aircraft", 6));	 
	 }
    for(int k = 6; k < 8; k++)
	 {
      acftDeck.add(new Card("aircraft", 7));	 
	 }
    shuffleAcft(acftDeck);
  }
  
  public void shuffle(Vector c)
  {
    Random rng = new Random();  
	 Die dice = new Die(rng.nextInt(666));
    Object[] obj = new Object[c.size()]; 
    obj = c.toArray();
	 for(int k = 0; k < c.size(); k++)  
    {
	   int rand = dice.rollX(c.size());  
		Object temp = obj[k];
		obj[k] = obj[rand];
		c.remove(k);    
		c.add(k, obj[rand]);
		obj[rand] = temp;
      c.remove(rand);    
		c.add(rand, temp);	   
	 }
  }
  
  public void shuffle(ArrayList<Card> c)
  {
    Random rng = new Random();  
	 Die dice = new Die(rng.nextInt(666));
    Object[] obj = new Object[c.size()]; 
    obj = c.toArray();
	 for(int k = 0; k < c.size(); k++)  
    {
	   int rand = dice.rollX(c.size());  
		Object temp = obj[k];
		obj[k] = obj[rand];
		c.remove(k);    
		c.add(k, (Card)obj[rand]);
		obj[rand] = temp;
      c.remove(rand);    
		c.add(rand, (Card)temp);	   
	 }
  }

  public void shuffleAcft(Vector c)
  {
    Random rng = new Random();  
	 Die dice = new Die(rng.nextInt(666));
    Object[] obj = new Object[c.size()]; 
    obj = c.toArray();
	 for(int k = 0; k < c.size(); k++)  
    {
	   int rand = dice.rollEight();  
		Object temp = obj[k];
		obj[k] = obj[rand];
		c.remove(k);    
		c.add(k, obj[rand]);
		obj[rand] = temp;
      c.remove(rand);    
		c.add(rand, temp);	   
	 }
  }
  
  public void checkDecks()
  {
    if(infDeck.size() == 0 && infGrave.size() > 0)
    {
//System.out.println("Grave size: "+infGrave.size());
      for(int k =0; k < infGrave.size(); k++)
      {
        infDeck.add((Card)infGrave.get(k));
      }
		shuffle(infDeck);
		infGrave.removeAllElements(); 
      numInf.setText(Integer.toString(infDeck.size()));

//System.out.println("Deck size: "+infDeck.size());
for(int k =0; k < infDeck.size(); k++)
{
 Card card = (Card)infDeck.get(k);
// System.out.println("Shuffled: "+card.getType()+" "+card.getValue());
}
	 }  
    if(mntDeck.size() == 0 && mntGrave.size() > 0)
    {
      for(int k =0; k < mntGrave.size(); k++)
      {
        mntDeck.add(mntGrave.get(k));
      }
		shuffle(mntDeck);
		mntGrave.removeAllElements(); 
      numInf.setText(Integer.toString(mntDeck.size()));
	 }
    if(artilDeck.size() == 0 && artilGrave.size() > 0)
    {
      for(int k =0; k < artilGrave.size(); k++)
      {
        artilDeck.add(artilGrave.get(k));
      }
		shuffle(artilDeck);
		artilGrave.removeAllElements(); 
      numInf.setText(Integer.toString(artilDeck.size()));
	 }
    if(acftDeck.size() == 0 && acftGrave.size() > 0)
    {
      for(int k =0; k < acftGrave.size(); k++)
      {
        acftDeck.add(acftGrave.get(k));
      }
		shuffleAcft(acftDeck);
		acftGrave.removeAllElements(); 
      numInf.setText(Integer.toString(acftDeck.size()));
	 } 
  }
  
  public Card getInfantry(int level)
  {
	 if(infDeck.size() > 0)
	 {
		Card temp = (Card)infDeck.remove(0); 
      infantryPane.remove(infantryPane.getIndexOf(temp.getLabel()));
	   numInf.setText(Integer.toString(infDeck.size()));
//	   for(int k = 0; k < 2; k++)//number of players  
//      {
//	 	   CivGUI.players[k].playerUI.mainBrd2.repaint();
//      }
	   if(level == 1)  
      { 
		  return temp;
      }  
	   if(level == 2)  
      { 
        temp.setLabelIcon(new GameRotatedIcon(temp.getFrontIcon(), 270));
        temp.setIconFront((GameRotatedIcon)temp.getLabel().getRotatedIcon());
		  temp.setValue(temp.getValue()+1);        
		  return temp;
      }
	   if(level == 3)  
      { 
        temp.setLabelIcon(new GameRotatedIcon(temp.getFrontIcon(), 180));
        temp.setIconFront((GameRotatedIcon)temp.getLabel().getRotatedIcon());
		  temp.setValue(temp.getValue()+2);        
		  return temp;
      }
	   if(level == 4)  
      { 
        temp.setLabelIcon(new GameRotatedIcon(temp.getFrontIcon(), 90));
        temp.setIconFront((GameRotatedIcon)temp.getLabel().getRotatedIcon());
		  temp.setValue(temp.getValue()+3);        
		  return temp;
      }
	   return null;
	 }
    else
	   return null;
//	 temp.setFrontIcon();
  }
  
  public Card getMounted(int level)
  {
//System.out.println("ENTERED mounted");
    if(mntDeck.size() > 0)
	 {
	   Card temp = (Card)mntDeck.remove(0); 
      mntPane.remove(mntPane.getIndexOf(temp.getLabel()));
      numMnt.setText(Integer.toString(mntDeck.size()));
	   if(level == 1)  
      { 
		  return temp;
      }  
	   if(level == 2)  
      { 
        temp.setLabelIcon(new GameRotatedIcon(temp.getFrontIcon(), 270));
        temp.setIconFront((GameRotatedIcon)temp.getLabel().getRotatedIcon());
		  temp.setValue(temp.getValue()+1);        
//System.out.println("LEVEL2 mounted");
		  return temp;
      }
	   if(level == 3)  
      { 
        temp.setLabelIcon(new GameRotatedIcon(temp.getFrontIcon(), 180));
        temp.setIconFront((GameRotatedIcon)temp.getLabel().getRotatedIcon());
		  temp.setValue(temp.getValue()+2);        
		  return temp;
      }
	   if(level == 4)  
      { 
        temp.setLabelIcon(new GameRotatedIcon(temp.getFrontIcon(), 90));
        temp.setIconFront((GameRotatedIcon)temp.getLabel().getRotatedIcon());
		  temp.setValue(temp.getValue()+3);        
		  return temp;
      }
	   return null;
	 }
	 else
	   return null;
  }

  public Card getArtillery(int level)
  {
    if(artilDeck.size() > 0)
	 {
	   Card temp = (Card)artilDeck.remove(0); 
      artilPane.remove(artilPane.getIndexOf(temp.getLabel()));
      numArt.setText(Integer.toString(artilDeck.size()));
	   if(level == 1)  
      { 
		  return temp;
      }  
	   if(level == 2)  
      { 
        temp.setLabelIcon(new GameRotatedIcon(temp.getFrontIcon(), 270));
        temp.setIconFront((GameRotatedIcon)temp.getLabel().getRotatedIcon());
		  temp.setValue(temp.getValue()+1);        
		  return temp;
      }
	   if(level == 3)  
      { 
        temp.setLabelIcon(new GameRotatedIcon(temp.getFrontIcon(), 180));
        temp.setIconFront((GameRotatedIcon)temp.getLabel().getRotatedIcon());
		  temp.setValue(temp.getValue()+2);        
		  return temp;
      }
	   if(level == 4)  
      { 
        temp.setLabelIcon(new GameRotatedIcon(temp.getFrontIcon(), 90));
        temp.setIconFront((GameRotatedIcon)temp.getLabel().getRotatedIcon());
		  temp.setValue(temp.getValue()+3);        
		  return temp;
      }
	   return null;
	 }
	 else
	   return null;
  }

  public Card getAircraft()
  {
    if(acftDeck.size() > 0)
	 {
	   Card temp = (Card)acftDeck.remove(0); 
      acftPane.remove(acftPane.getIndexOf(temp.getLabel()));
      numAcft.setText(Integer.toString(acftDeck.size()));
	   return temp;
    }
    else 
	   return null;
  }

  public void addToGrave(Card c)
  {
    if(c.type.equals("infantry"))
      graveInfantry(c);
    else if(c.type.equals("mounted"))
      graveMounted(c);
    else if(c.type.equals("artillery"))
      graveArtillery(c);
    else if(c.type.equals("aircraft"))
      graveAircraft(c);
  }
  
  private void graveInfantry(Card inf)
  {
    infGrave.add(inf);
    numInf.setText(Integer.toString(infDeck.size()));
  }
  
  public void graveMounted(Card mnt)
  {
    mntGrave.add(mnt);
    numMnt.setText(Integer.toString(mntDeck.size()));
  }

  public void graveArtillery(Card art)
  {
    artilGrave.add(art);
    numArt.setText(Integer.toString(artilDeck.size()));
  }

  public void graveAircraft(Card acft)
  {
    acftGrave.add(acft);
    numAcft.setText(Integer.toString(acftDeck.size()));
  }

  public int getInfSize()
  {
    return infDeck.size();
  }

  public int getMntSize()
  {
    return mntDeck.size();
  }

  public int getArtSize()
  {
    return artilDeck.size();
  }

  public int getAcftSize()
  {
    return acftDeck.size();
  }

  public GameRotatedIcon getWonderIcon(int w)
  {
    if(wonderDeck.size() > 0)
	 {
	   Card temp = (Card)wonderDeck.get(w);
	   return temp.iconBack;
	 }  
    return null;

  }
  
  public Card getWonder(int w)
  {
    if(wonderDeck.size() > 0)
	 {
	   Card temp = (Card)wonderDeck.get(0);
	   wonderPane.remove(temp.getLabel());  
      wonderDeck.remove(0);	 
	   numWonders.setText(Integer.toString(wonderDeck.size()));
	   wonderPane.repaint();
		
      temp.setLabelIcon(new GameRotatedIcon(temp.getFrontIcon()));
      temp.setIconFront((GameRotatedIcon)temp.getLabel().getRotatedIcon());

	   for(int k = 0; k < 2; k++)//number of players  
      {
//	 	   CivGUI.players[k].playerUI.mainBrd2.repaint();
      }
	   return temp;
	 }  
    return null;
  }

  public Card removeWonder(int w)
  {
    if(wonderDeck.size() > 0)
	 {
	   Card temp = (Card)wonderDeck.get(w);
	   wonderPane.remove(temp.getLabel());  
	   for(int k = 0; k < 2; k++)//number of players  
      {
//	 	   CivGUI.players[k].playerUI.mainBrd2.repaint();
      }
	   wonderPane.repaint();
      wonderDeck.remove(w);	 
		numWonders.setText(Integer.toString(wonderDeck.size()));
	 
      temp.setLabelIcon(new GameRotatedIcon(temp.getFrontIcon()));
      temp.setIconFront((GameRotatedIcon)temp.getLabel().getRotatedIcon());
	   return temp;
	 }  
	 return null;
  }

  public void removeTopWonder()
  {
    wonderDeck.remove(0);
  }

   //getter-setter methods
	public ArrayList<Card> getInfDeck() {
		return infDeck;
	}
	public void setInfDeck(ArrayList<Card> infDeck) {
		this.infDeck = infDeck;
	}
	public Vector getMntDeck() {
		return mntDeck;
	}
	public void setMntDeck(Vector mntDeck) {
		this.mntDeck = mntDeck;
	}
	public Vector getArtilDeck() {
		return artilDeck;
	}
	public void setArtilDeck(Vector artilDeck) {
		this.artilDeck = artilDeck;
	}
	public Vector getAcftDeck() {
		return acftDeck;
	}
	public void setAcftDeck(Vector acftDeck) {
		this.acftDeck = acftDeck;
	}
	public Vector getAncientDeck() {
		return ancientDeck;
	}
	public void setAncientDeck(Vector ancientDeck) {
		this.ancientDeck = ancientDeck;
	}
	public Vector getMedievalDeck() {
		return medievalDeck;
	}
	public void setMedievalDeck(Vector medievalDeck) {
		this.medievalDeck = medievalDeck;
	}
	public Vector getModernDeck() {
		return modernDeck;
	}
	public void setModernDeck(Vector modernDeck) {
		this.modernDeck = modernDeck;
	}
	public Vector getWonderDeck() {
		return wonderDeck;
	}
	public void setWonderDeck(Vector wonderDeck) {
		this.wonderDeck = wonderDeck;
	}
	public Vector getCulture1Deck() {
		return culture1Deck;
	}
	public void setCulture1Deck(Vector culture1Deck) {
		this.culture1Deck = culture1Deck;
	}
	public Vector getCulture2Deck() {
		return culture2Deck;
	}
	public void setCulture2Deck(Vector culture2Deck) {
		this.culture2Deck = culture2Deck;
	}
	public Vector getCulture3Deck() {
		return culture3Deck;
	}
	public void setCulture3Deck(Vector culture3Deck) {
		this.culture3Deck = culture3Deck;
	}
	public Vector getInfGrave() {
		return infGrave;
	}
	public void setInfGrave(Vector infGrave) {
		this.infGrave = infGrave;
	}
	public Vector getMntGrave() {
		return mntGrave;
	}
	public void setMntGrave(Vector mntGrave) {
		this.mntGrave = mntGrave;
	}
	public Vector getArtilGrave() {
		return artilGrave;
	}
	public void setArtilGrave(Vector artilGrave) {
		this.artilGrave = artilGrave;
	}
	public Vector getAcftGrave() {
		return acftGrave;
	}
	public void setAcftGrave(Vector acftGrave) {
		this.acftGrave = acftGrave;
	}
	public JTextArea getNumInf() {
		return numInf;
	}
	public void setNumInf(JTextArea numInf) {
		this.numInf = numInf;
	}
	public JTextArea getNumMnt() {
		return numMnt;
	}
	public void setNumMnt(JTextArea numMnt) {
		this.numMnt = numMnt;
	}
	public JTextArea getNumArt() {
		return numArt;
	}
	public void setNumArt(JTextArea numArt) {
		this.numArt = numArt;
	}
	public JTextArea getNumAcft() {
		return numAcft;
	}
	public void setNumAcft(JTextArea numAcft) {
		this.numAcft = numAcft;
	}
	public JTextArea getNumWonders() {
		return numWonders;
	}
	public void setNumWonders(JTextArea numWonders) {
		this.numWonders = numWonders;
	}
	public JTextArea getNumculture1() {
		return numculture1;
	}
	public void setNumculture1(JTextArea numculture1) {
		this.numculture1 = numculture1;
	}
	public JTextArea getNumculture2() {
		return numculture2;
	}
	public void setNumculture2(JTextArea numculture2) {
		this.numculture2 = numculture2;
	}
	public JTextArea getNumculture3() {
		return numculture3;
	}
	public void setNumculture3(JTextArea numculture3) {
		this.numculture3 = numculture3;
	}
	public GameJLayeredPane getWonderPane() {
		return wonderPane;
	}
	public void setWonderPane(GameJLayeredPane wonderPane) {
		this.wonderPane = wonderPane;
	}
	public GameJLayeredPane getCulture1Pane() {
		return culture1Pane;
	}
	public void setCulture1Pane(GameJLayeredPane culture1Pane) {
		this.culture1Pane = culture1Pane;
	}
	public GameJLayeredPane getCulture2Pane() {
		return culture2Pane;
	}
	public void setCulture2Pane(GameJLayeredPane culture2Pane) {
		this.culture2Pane = culture2Pane;
	}
	public GameJLayeredPane getCulture3Pane() {
		return culture3Pane;
	}
	public void setCulture3Pane(GameJLayeredPane culture3Pane) {
		this.culture3Pane = culture3Pane;
	}
	public GameJLayeredPane getInfantryPane() {
		return infantryPane;
	}
	public void setInfantryPane(GameJLayeredPane infantryPane) {
		this.infantryPane = infantryPane;
	}
	public GameJLayeredPane getMntPane() {
		return mntPane;
	}
	public void setMntPane(GameJLayeredPane mntPane) {
		this.mntPane = mntPane;
	}
	public GameJLayeredPane getArtilPane() {
		return artilPane;
	}
	public void setArtilPane(GameJLayeredPane artilPane) {
		this.artilPane = artilPane;
	}
	public GameJLayeredPane getAcftPane() {
		return acftPane;
	}
	public void setAcftPane(GameJLayeredPane acftPane) {
		this.acftPane = acftPane;
	}

	private void readObject(java.io.ObjectInputStream stream) throws java.io.IOException, ClassNotFoundException{
		try{
			infDeck = (ArrayList<Card>)stream.readObject();
			mntDeck = (Vector)stream.readObject();
			artilDeck = (Vector)stream.readObject();
			acftDeck = (Vector)stream.readObject();
			ancientDeck = (Vector)stream.readObject();
			medievalDeck = (Vector)stream.readObject();
			modernDeck = (Vector)stream.readObject();
			wonderDeck = (Vector)stream.readObject();
			culture1Deck = (Vector)stream.readObject();
			culture2Deck = (Vector)stream.readObject();
			culture3Deck = (Vector)stream.readObject();
			infGrave = (Vector)stream.readObject();
			mntGrave = (Vector)stream.readObject();
			artilGrave = (Vector)stream.readObject();
			acftGrave = (Vector)stream.readObject();
			numInf = (JTextArea)stream.readObject();
			numMnt = (JTextArea)stream.readObject();
			numArt = (JTextArea)stream.readObject();
			numAcft = (JTextArea)stream.readObject();
			numWonders = (JTextArea)stream.readObject();
			numculture1 = (JTextArea)stream.readObject();
			numculture2 = (JTextArea)stream.readObject();
			numculture3 = (JTextArea)stream.readObject();
			wonderPane = (GameJLayeredPane)stream.readObject();
			culture1Pane = (GameJLayeredPane)stream.readObject();
			culture2Pane = (GameJLayeredPane)stream.readObject();
			culture3Pane = (GameJLayeredPane)stream.readObject();
			infantryPane = (GameJLayeredPane)stream.readObject();
			mntPane = (GameJLayeredPane)stream.readObject();
			artilPane = (GameJLayeredPane)stream.readObject();
			acftPane = (GameJLayeredPane)stream.readObject();		
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
			stream.writeObject(infDeck);
			stream.writeObject(mntDeck);
			stream.writeObject(artilDeck);
			stream.writeObject(acftDeck);
			stream.writeObject(ancientDeck);
			stream.writeObject(medievalDeck);
			stream.writeObject(modernDeck);
			stream.writeObject(wonderDeck);
			stream.writeObject(culture1Deck);
			stream.writeObject(culture2Deck);
			stream.writeObject(culture3Deck);
			stream.writeObject(infGrave);
			stream.writeObject(mntGrave);
			stream.writeObject(artilGrave);
			stream.writeObject(acftGrave);
			stream.writeObject(numInf);
			stream.writeObject(numMnt);
			stream.writeObject(numArt);
			stream.writeObject(numAcft);
			stream.writeObject(numWonders);
			stream.writeObject(numculture1);
			stream.writeObject(numculture2);
			stream.writeObject(numculture3);
			stream.writeObject(wonderPane);
			stream.writeObject(culture1Pane);
			stream.writeObject(culture2Pane);
			stream.writeObject(culture3Pane);
			stream.writeObject(infantryPane);
			stream.writeObject(mntPane);
			stream.writeObject(artilPane);
			stream.writeObject(acftPane);  
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

