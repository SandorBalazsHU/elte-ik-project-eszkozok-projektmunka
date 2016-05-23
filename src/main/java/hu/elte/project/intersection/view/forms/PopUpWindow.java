package hu.elte.project.intersection.view.forms;

import hu.elte.project.intersection.controll.Logger;
import javax.swing.JOptionPane;

/**
 *
 * Sándor Balázs - AZA6NL
 */

/*
A file leírása:
    Általános felugró ablak kezelő!
*/
public class PopUpWindow {
    public static final int COSTUM = 0;
    public static final int MESSAGE = 1;
    public static final int WARNING = 2;
    public static final int ERROR = 3;
    public static final int QUESTION = -1;
    //Null ha nincs válasz!
    public Boolean answer = null;
    
    
    //Üzenet megjelenítő metódus - Hívásához a konstruktorát kell hívni mivel létrehozunk egy felugró ablakot
    public PopUpWindow(int level, String msg)
    {
        switch(level) { 
            //level 0 Sima üzenet
            case COSTUM: JOptionPane.showMessageDialog(null ,msg, "Üzenet",JOptionPane.PLAIN_MESSAGE); break;
            //level 1 Üzenet ikonnal
            case MESSAGE: JOptionPane.showMessageDialog(null ,msg); break;
            //level 2 Figyelmeztetés
            case WARNING: JOptionPane.showMessageDialog(null ,msg, "Figyelmeztetés",JOptionPane.WARNING_MESSAGE); break;
            //level 3 Hibaüzenet és hibával kilépés. Csa súlyos hiba esetére
            case ERROR: JOptionPane.showMessageDialog(null ,msg, "HIBA",JOptionPane.ERROR_MESSAGE);
            System.exit(1);
            break;
            case QUESTION:
                int confirm = JOptionPane.showOptionDialog(null, "Are You Sure to Close Application?", "Exit Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
                answer = confirm == 0;
            break;
        }
    }
    //Egy szöveggel hívva üzenetet jelenít meg!
    public PopUpWindow(String msg){
        this(1,msg);
    }
    //Kivétellel hívva súlyos hibát jelent és be is zár valamint stackPrintet nyomtat!
    public PopUpWindow(Exception msg){
        this(3,"Súlyos hiba történt!\nHiba leírása:\n\t"+msg.toString()+"\n\tstackprint: "+Logger.stackTracePrint(msg));
    }
}
