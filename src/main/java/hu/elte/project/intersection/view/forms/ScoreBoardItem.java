/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.elte.project.intersection.view.forms;

import hu.elte.project.intersection.model.Player;
import hu.elte.project.intersection.view.VerticalFlowLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.FlowLayout;

/**
 *
 * @author sandorbalazs
 */
public class ScoreBoardItem extends JPanel{
    private JLabel playerName;
    private JPanel playerColor;
    private JLabel playerKeys;
    private JLabel playerXP;
    private JPanel box;
    
    public ScoreBoardItem(Player player) {
        setLayout(new VerticalFlowLayout());
        setLayout(new FlowLayout(10));
        
        playerName = new JLabel(player.getName());
        add(playerName);
        
        box = new JPanel();
        
        playerColor = new JPanel();
        playerColor.setPreferredSize(new Dimension(20, 20));
        playerColor.setBackground(player.getcolor());
        box.add(playerColor);
        
        playerKeys = new JLabel("Irányítás:  " + player.getControllKey().name + "  ");
        box.add(playerKeys);
        
        playerXP = new JLabel(player.getXP() + " Pont");
        box.add(playerXP);
        
        add(box);
    }
}
