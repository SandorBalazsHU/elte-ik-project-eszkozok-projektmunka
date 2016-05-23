
package hu.elte.project.intersection.view;

import hu.elte.project.intersection.view.forms.PopUpWindow;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JOptionPane;

/**
 *
 * Sándor Balázs - AZA6NL
 */

/*
A file leírása:
    Egy saját ablakeseménykezelő a főablak számára
    - azét a logikába került mert nem tartalmaz grafikai elemeket, csak logikát
*/
public class GameWindowListener implements WindowListener{
//public GameWindowListener(){super();} nem kell mert a default konstruktor is ezt csinálja!

@Override //Invoked when the Window is set to be the active Window.
public void windowActivated(WindowEvent e){}
@Override //Invoked when a window has been closed as the result of calling dispose on the window.
public void windowClosed(WindowEvent e){}

@Override //Invoked when the user attempts to close the window from the window's system menu.
public void windowClosing(WindowEvent e){
PopUpWindow window = new PopUpWindow(PopUpWindow.QUESTION, "Mented a pályát a bezárás előtt?");
if (window.answer){
    //SaveGame.save(null);
    System.exit(0);
    }else{
        System.exit(0);
    }
}

@Override //Invoked when a Window is no longer the active Window.
public void windowDeactivated(WindowEvent e){}

@Override //Invoked when a window is changed from a minimized to a normal state.
public void windowDeiconified(WindowEvent e){}

@Override //Invoked when a window is changed from a normal to a minimized state.
public void windowIconified(WindowEvent e){}
@Override //Invoked the first time a window is made visible.
public void windowOpened(WindowEvent e){}
}
