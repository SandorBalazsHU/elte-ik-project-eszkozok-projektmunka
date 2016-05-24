package hu.elte.project.intersection.model;

import java.util.ArrayList;

/**
 *<h1>A játék minden adatát tartalmazó adatszerkezet.</h1>
 * Tárolja a játékosokat és a játék fő adatait.
 * 
 * @author  <b>Sándor Balázs</b> - <b>Bognár Dániel</b> - <b>Kiss Erik</b>
 * @version 1.0
 * @since   2016-05-03 
 */
public class Game {
    /**
     * A játékosokat tárolja
     */
    private ArrayList<Player> players;
    
    /**
     * A konstruktor.
     */
    public Game(){
        players = new ArrayList<Player>();
    }
    
    /**
     * Játékost ad az adatszerkezethez
     * @param p A hozzáadandó játékos
     */
    public void addPlayer(Player p){
        players.add(p);
    }
    
    /**
     * A jelenlegi játékosok száma
     * @return A játékosok száma.
     */
    public int getPlayersNumber(){
        return players.size();
    }
    
    /**
     * Lekérhető eygy játékos az ID-ja alapján.
     * @param i A kért játékos sorszáma.
     * @return A megadott sorszmú játékos.
     */
    public Player getPlayer(int i){
        return players.get(i);
    }
    
    /**
     * Megadja a még játkban lévő játékosok számát
     * @return Az aktív játékosok száma
     */
    public int livePlayers(){
        int livePlayers = 0;
        for (Player p : players) {
            if(p.isLive()) ++livePlayers;
        }
        return livePlayers;
    }
}
