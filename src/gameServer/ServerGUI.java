package gameServer;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.Serializable;

public class ServerGUI extends JFrame implements ActionListener, Serializable{

   private static final long serialVersionUID = -5148020788533201214L;

   private static JButton QUIT, FLUSH;	    
	private static TextArea showServer;	
		
	public void actionPerformed(ActionEvent e) 
	{
	 	if(e.getSource() == QUIT)
  	   {
			setVisible(false);
			System.exit(0);
		}
	 	else if(e.getSource() == FLUSH)
  	   {
         ChatServer.removeGames();    
		}
	}//end actionPerformed(e)

	public ServerGUI()
	{
	 	JFrame c = new JFrame("CHAT SERVER");	
      JPanel p = new JPanel();
		Panel p2 = new Panel();  
		showServer = new TextArea();	
	   showServer.setEditable(false);
		showServer.setBackground(new Color(255,255,255));    
		QUIT = new JButton("Close");
		QUIT.addActionListener(this);
		FLUSH = new JButton("Flush Games");
		FLUSH.addActionListener(this);
		c.add(p,"Center");
    	p.add(showServer, "North");
		c.add(p2,"South");
      p.add(QUIT, "South"); 
      p.add(FLUSH, "East"); 
		c.setResizable(false);
		c.setBounds(300,200,550,250);
      c.setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
   }//end ServerGUI()
 	
	public void append(String s)
	{
		showServer.append(s);
	}//end append(String)

}//end class
