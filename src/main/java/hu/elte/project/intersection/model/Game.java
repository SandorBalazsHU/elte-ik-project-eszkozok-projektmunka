/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.elte.project.intersection.model;

import java.util.ArrayList;

/**
 *
 * @author sandorbalazs
 */
public class Game {
    private ArrayList<Player> players;
    public Game(){
        players = new ArrayList<Player>();
    }
    public void addPlayerr(Player p){
        players.add(p);
    }
    public int getPlayersNumber(){
        return players.size();
    }
    public Player getPlayer(int i){
        return players.get(i);
    }
}
