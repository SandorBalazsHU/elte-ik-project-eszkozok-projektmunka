/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.elte.project.intersection.view.forms;

import hu.elte.project.intersection.model.Game;
import hu.elte.project.intersection.view.VerticalFlowLayout;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author sandorbalazs
 */
public class ScoreBoard extends JPanel{
    private final JLabel tittle  = new JLabel("Játékosok:");
    private ArrayList<ScoreBoardItem> items = new ArrayList<ScoreBoardItem>();
    
    public void init(Game game)
    {
        setLayout(new VerticalFlowLayout());
        add(tittle);
        for (int i = 0; i < game.getPlayersNumber(); ++i){
            ScoreBoardItem newScoreBoardItem = new ScoreBoardItem(game.getPlayer(i));
            items.add(newScoreBoardItem);
            add(newScoreBoardItem);
        }
    }
}
