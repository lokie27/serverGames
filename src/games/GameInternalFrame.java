package games;

import javax.swing.*;
import java.beans.*;
import java.io.Serializable;

public class GameInternalFrame extends JInternalFrame implements Serializable{

    private static final long serialVersionUID = -9206956042079628515L;

    private boolean maximised = false;

    public GameInternalFrame(){}//bean default

    public GameInternalFrame(String title){
	    super(title);
	 }

    @Override
    public void setMaximum( boolean b )  {
        maximised = b;
        if (isVisible() == false) return;
        try {
            super.setMaximum(b);
        } catch (PropertyVetoException pve) {}
    }
    
    public boolean getMaximum() {
        return maximised;
    }

    @Override
    public void setVisible( boolean b ) {
        super.setVisible(b);
        if (maximised==true) setMaximum(true);            
    }

    public boolean getMaximised(){
	    return maximised;
	 }

    public void setMaximised(boolean maximised){
	    this.maximised = maximised;
	 }
}