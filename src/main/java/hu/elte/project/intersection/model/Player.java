/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.elte.project.intersection.model;

import hu.elte.project.intersection.model.graphmodel.CKey;
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
    private boolean isRemoteUser;
    private CKey controllKey;
    
    public Player(String name, Color color, boolean isRemoteUser){
        this.name = name;
        this.color = color;
        this.xp = 0;
        this.isLive = true;
        this.isRemoteUser = isRemoteUser;
    }
    
    public boolean setControllKey(CKey c){
        if(!this.isRemoteUser){
            this.controllKey = c;
        }
        return !this.isRemoteUser;
    }
    
    public CKey getControllKey(){
        return controllKey;
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
    
    public boolean addXP(int xp) {
        if(isLive){
            this.xp += xp;
            return true;
        }else{
            return false;
        }
    }
    
    public void kill(){
        isLive = false;
    }
    
    public boolean isRemoteUser(){
        return isRemoteUser;
    }
}
