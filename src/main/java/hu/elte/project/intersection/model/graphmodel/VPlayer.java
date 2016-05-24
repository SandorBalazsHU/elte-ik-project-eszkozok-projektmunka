package hu.elte.project.intersection.model.graphModel;

import java.util.ArrayList;
import hu.elte.project.intersection.model.Player;
import hu.elte.project.intersection.view.GamePanel;
import hu.elte.project.intersection.model.graphmodel.CKey;

/**
 *<h1> A Player osztály kivővítése grafikai adatokkal.</h1>
 * A VPlayer osztály a PLayer osztály burkolóosztálya, mely a rjzoláshoz
 * szükségesadatokat tárolja.
 * 
 * @author  <b>Sándor Balázs</b> - <b>Bognár Dániel</b> - <b>Kiss Erik</b>
 * @version 1.0
 * @since   2016-05-03 
 */
public class VPlayer {
    
    /**
     * A szakaszok x koordinátáit tároló vektor
     */
    public ArrayList<Integer> x = new ArrayList<Integer>();
    
    /**
     * A szakaszok y koordinátáit tároló vektor
     */
    public ArrayList<Integer> y = new ArrayList<Integer>();
    
    /**
     * Ajátékos
     */
    public Player player;
    
    /**
     * Az menetirány
     */
    public double alpha = 10;
    
    /**
     * A lépéstávolság
     */
    public double r = 5.0d;
    
    /**
     * A vonalvastagság
     */
    public int thickness = 5;
    
    /**
     * A vezérlőbillentyűk
     */
    public CKey controllKey;
    
    /**
     * Az elfordulás mértéke
     */
    public double roll = 10.0d;
    
    /**
     * Távoli felhasználó?
     */
    public boolean isRemoteUser;
    
    /**
     * A kezdőpozíciók minimális távolsága a kerettől
     */
    public int padding = 350;
    
    /**
     * A konstruktor
     * @param player A beburkolandó játékos
     */
    public VPlayer(Player player){
        this.player = player;
        this.isRemoteUser = player.isRemoteUser();
        this.controllKey = player.getControllKey();
        

        int randomX = padding + (int)(Math.random() * (GamePanel.windowX-padding)-padding); 
        int randomY = padding + (int)(Math.random() * (GamePanel.windowY-padding)-padding);
        x.add(randomX);
        randomX += r;
        x.add(randomX);
        
        y.add(randomY);
        randomY +=r;
        y.add(randomY);
        
        int randomStartAlpha = 0 + (int)(Math.random() * 360);
        alpha = randomStartAlpha;
    }
}
