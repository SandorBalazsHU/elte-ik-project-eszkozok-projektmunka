/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.elte.project.intersection.model;

import java.awt.Color;

/**
 *
 * @author sandorbalazs
 */
public class Player {
    private final String name;
    private final Color color;
    private int xp;
    private boolean isLive;
    
    public Player(String name, Color color){
        this.name = name;
        this.color = color;
        this.xp = 0;
        this.isLive = true;
    }
    
    public String getName(){
        return name;
    }
    
    public Color getcolor(){
        return color;
    }
    
    public int getXP(){
        return xp;
    }
    
    public boolean isLive(){
        return isLive;
    }
    
    protected boolean addXP(int xp) {
        if(isLive){
            this.xp += xp;
            return true;
        }else{
            return false;
        }
    }
    
    protected void kill(){
        isLive = false;
    }
}
