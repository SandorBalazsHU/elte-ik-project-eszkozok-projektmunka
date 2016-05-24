/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.elte.project.intersection.view.forms;

import hu.elte.project.intersection.view.VerticalFlowLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JColorChooser;
import javax.swing.JComponent;
import javax.swing.colorchooser.ColorSelectionModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


/**
 *
 * @author sandorbalazs
 */
class SetPlayerForm extends JPanel {
    public JTextField playerNameField = new JTextField(20);
    private JColorChooser colorChooser = new JColorChooser(this.getForeground());
    
    public SetPlayerForm(int id) {
        setLayout(new VerticalFlowLayout());
        
        JLabel label = new JLabel("A " + (id+1) + ". játékos neve és színe:");
        add(label);
        
        add(playerNameField);
        
        final MyPreviewPanel pre = new MyPreviewPanel(colorChooser);
        colorChooser.setPreviewPanel(pre);
        ColorSelectionModel model = colorChooser.getSelectionModel();
        model.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent evt) {
                ColorSelectionModel model = (ColorSelectionModel) evt.getSource();
                pre.curColor = model.getSelectedColor();
            }
        });
        add(colorChooser);
    }
    
    public String getPlayerName(){
        return playerNameField.getText();
    }
    
    public Color getColor(){
        return colorChooser.getColor();
    }
    
    class MyPreviewPanel extends JComponent {
        Color curColor;
        public MyPreviewPanel(JColorChooser chooser) {
            curColor = chooser.getColor();
            setPreferredSize(new Dimension(200, 20));
        }
        public void paint(Graphics g) {
            g.setColor(curColor);
            g.fillRect(0, 0, getWidth() - 1, getHeight() - 1);
        }
    }
}
