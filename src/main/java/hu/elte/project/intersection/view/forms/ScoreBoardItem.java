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
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

/**
 *
 * @author sandorbalazs
 */
public class ScoreBoardItem extends JPanel implements ActionListener{
    private JLabel playerName;
    private JPanel playerColor;
    private JLabel playerKeys;
    protected JLabel playerXP;
    private JPanel box;
    private Player player;
    public JButton actionButton = new JButton();
    
    public ScoreBoardItem(Player player) {
        this.player = player;
        
        setLayout(new VerticalFlowLayout());
        
        playerName = new JLabel("  " + player.getName());
        playerName.setFont(new Font("Plain", Font.BOLD, 16));
        add(playerName);
        
        box = new JPanel();
        box.setLayout(new FlowLayout(20));
        
        playerColor = new JPanel();
        playerColor.setPreferredSize(new Dimension(20, 20));
        playerColor.setBackground(player.getcolor());
        box.add(playerColor);
        
        playerKeys = new JLabel("Irányítás:  " + player.getControllKey().name + "  ");
        box.add(playerKeys);
        
        playerXP = new JLabel(player.getXP() + " Pont");
        box.add(playerXP);
        
        add(box);
        
        actionButton.setVisible(false);
        actionButton.addActionListener(this);
        actionButton.setActionCommand("refresh_xp");
        add(actionButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().contains("refresh_xp")) playerXP.setText(player.getXP() + " Pont");
    }
}
