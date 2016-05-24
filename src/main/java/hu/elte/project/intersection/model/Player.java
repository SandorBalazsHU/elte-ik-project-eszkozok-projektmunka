package hu.elte.project.intersection.model;

import hu.elte.project.intersection.model.graphmodel.CKey;
import java.awt.Color;

/**
 * <h1>Egy játékost reprezentáló osztály.</h1>
 * 
 * @author  <b>Sándor Balázs</b> - <b>Bognár Dániel</b> - <b>Kiss Erik</b>
 * @version 1.0
 * @since   2016-05-03 
 */
public class Player {
    
    /**
     * A játékos neve
     */
    private final String name;
    
    /**
     * A játékos színe
     */
    private final Color color;
    
    /**
     * A játékos pontszáma
     */
    private int xp;
    
    /**
     * A játékos aktív-e?
     */
    private boolean isLive;
    
    /**
     * Távoli vagy helyi játékos-e?
     */
    private boolean isRemoteUser;
    
    /**
     * Milyen billentyűkkel irányít.
     */
    private CKey controllKey;
    
    /**
     * Egy játékost létrehozó konstruktor.
     * @param name A játékos neve
     * @param color A játékos színe
     * @param isRemoteUser  Távoli játékos-e?
     */
    public Player(String name, Color color, boolean isRemoteUser){
        this.name = name;
        this.color = color;
        this.xp = 0;
        this.isLive = true;
        this.isRemoteUser = isRemoteUser;
    }
    
    /**
     * Itt adhaó meg az irányításra használt billenytűpár.
     * @param c Az irányításra használt billenytűpár
     * @return Sikeres volt-e a művelet.
     */
    public boolean setControllKey(CKey c){
        if(!this.isRemoteUser){
            this.controllKey = c;
        }
        return !this.isRemoteUser;
    }
    
    /**
     * Az irányításra használt billenytűpárt adja vissza.
     * @return Az irányításra használt billenytűpár
     */
    public CKey getControllKey(){
        return controllKey;
    }
    
    /**
     * A játékos nevét adja vissza
     * @return A játékos neve
     */
    public String getName(){
        return name;
    }
    
    /**
     * 
     * @return 
     */
    public Color getcolor(){
        return color;
    }
    
    /**
     * Visszaadja a játéos pontszámát
     * @return A játékos pontszáma
     */
    public int getXP(){
        return xp;
    }
    
    /**
     * Megadja, hogy a játékos aktív-e.
     * @return A játékos aktivitásí.
     */
    public boolean isLive(){
        return isLive;
    }
    
    /**
     * A játékos pontszámának övelése
     * @param xp A játékos pontszámáhz adandó mennyiség
     * @return Sikeres volt-e a művelet-
     */
    public boolean addXP(int xp) {
        if(isLive){
            this.xp += xp;
            return true;
        }else{
            return false;
        }
    }
    
    /**
     * A játékos kiesett.
     */
    public void kill(){
        isLive = false;
    }
    
    /**
     * Távoli felhasználórl vN SZÓ?
     * @return Távoli felhaszáló-e?
     */
    public boolean isRemoteUser(){
        return isRemoteUser;
    }
}
