/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.elte.project.intersection.view.forms;

import hu.elte.project.intersection.model.Game;
import hu.elte.project.intersection.view.VerticalFlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author sandorbalazs
 */
public class ScoreBoard extends JPanel{
    private final JLabel tittle  = new JLabel("Játékosok:");
    private ArrayList<ScoreBoardItem> items = new ArrayList<ScoreBoardItem>();
    private Game game;
    
    public void init(Game game)
    {
        this.game = game;
        setLayout(new VerticalFlowLayout());
        tittle.setFont(new Font("Plain", Font.BOLD, 18));
        tittle.setBorder(new EmptyBorder(10,10,10,10));
        add(tittle);
        for (int i = 0; i < game.getPlayersNumber(); ++i){
            ScoreBoardItem newScoreBoardItem = new ScoreBoardItem(game.getPlayer(i));
            items.add(newScoreBoardItem);
            add(newScoreBoardItem);
        }
    }
    public void repaintME(){    
        for (int i = 0; i < game.getPlayersNumber(); ++i){
            items.get(i).actionButton.doClick(0);
        }
    }
}
