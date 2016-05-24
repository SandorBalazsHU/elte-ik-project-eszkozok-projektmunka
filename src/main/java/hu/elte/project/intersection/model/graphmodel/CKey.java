package hu.elte.project.intersection.model.graphmodel;

import java.awt.event.KeyEvent;

/**
 *<h1> A vezérlőbillentyűk tárolására szolgál</h1>
 * 
 * @author  <b>Sándor Balázs</b> - <b>Bognár Dániel</b> - <b>Kiss Erik</b>
 * @version 1.0
 * @since   2016-05-03 
 */
public class CKey {
    
    /**
     * A baj billenytű
     */
    public final int left;
    
    /**
     * A job illentyú
     */
    public final int right;
    
    /*
    A billenytűombinávió neve.
    */
    public final String name;
    
    /**
     * Az AD konbináció kódja
     */
    public static final int AD = 0;
    
    /**
     * Az JL konbináció kódja
     */
    public static final int JL = 1;
    
    /**
     * Az RZ konbináció kódja
     */
    public static final int RZ = 2;
    
    /**
     * Az VN konbináció kódja
     */
    public static final int VN = 3;
    
    /**
     * Az YC konbináció kódja
     */
    public static final int YC = 4;
    
    /**
     * a KONSTRUKTOR
     * @param c A ívánt billentyűpáros.
     */
    public CKey(int c){
        switch(c){
            case 0:
                left = KeyEvent.VK_A;
                right = KeyEvent.VK_D;
                name = "<=A D=>";
            break;
            case 1:
                left = KeyEvent.VK_J;
                right = KeyEvent.VK_L;
                name = "<=J L=>";
            break;
            case 2:
                left = KeyEvent.VK_R;
                right = KeyEvent.VK_Z;
                name = "<=R Z=>";
            break;
            case 3:
                left = KeyEvent.VK_V;
                right = KeyEvent.VK_N;
                name = "<=V N=>";
            break;
            case 4:
                left = KeyEvent.VK_Y;
                right = KeyEvent.VK_C;
                name = "<=Y C=>";
            break;
            default:
                left = KeyEvent.VK_A;
                right = KeyEvent.VK_D;
                name = "<=A D=>";
            break;
        }
    }
}
