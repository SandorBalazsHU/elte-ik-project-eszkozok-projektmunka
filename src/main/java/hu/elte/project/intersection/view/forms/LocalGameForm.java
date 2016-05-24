/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.elte.project.intersection.view.forms;

import hu.elte.project.intersection.controll.viewcontroll.View;
import hu.elte.project.intersection.view.VerticalFlowLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author sandorbalazs
 */
public class LocalGameForm extends JPanel implements ActionListener{
    public JPanel getUsersNumberForm = new  JPanel();
    public JPanel getUsersForm = new  JPanel();
    private JButton generateGetUsersForm = new JButton("OK");
    private JButton startGame = new JButton("START");
    private ArrayList<SetPlayerForm> setPlayerForms = new ArrayList<SetPlayerForm>();
    private JSpinner playersNumberSpinner;
    private JScrollPane scrollFrameForGetUsersForm = new JScrollPane(getUsersForm);
    private final static int playersNumberLimit = 5;
    private JLabel panelTittleLabel = new JLabel("Új helyi többszemélyes játék indítása.");
    private JFrame parentFrame;
    private final View view;
    
    public LocalGameForm(JFrame parentFrame, View view){
        this.view = view;
        this.parentFrame = parentFrame;
        setLayout(new VerticalFlowLayout());
        panelTittleLabel.setFont(new Font("Plain", Font.BOLD, 22));
        panelTittleLabel.setBorder(new EmptyBorder(10,0,0,10));
        add(panelTittleLabel);
        add(gernerateGetUsersNumberForm());
        
        scrollFrameForGetUsersForm.setPreferredSize(new Dimension(1200, 600));
        scrollFrameForGetUsersForm.getVerticalScrollBar().setUnitIncrement(10);
        scrollFrameForGetUsersForm.setBorder(BorderFactory.createEmptyBorder());
        getUsersForm.setAutoscrolls(true);
        add(scrollFrameForGetUsersForm);
        getUsersForm.setVisible(false);
        this.addComponentListener((new ComponentListener() {
            
            @Override
            public void componentResized(ComponentEvent e){   
                PanelRepaint();
            }
            
            @Override
            public void componentMoved(ComponentEvent e) {}

            @Override
            public void componentShown(ComponentEvent e){
                PanelRepaint();
            }

            @Override
            public void componentHidden(ComponentEvent e) {}
        }));
    }
    private JPanel gernerateGetUsersNumberForm(){
        getUsersNumberForm.setLayout(new FlowLayout(FlowLayout.LEFT,10,2));
        
        SpinnerModel model = new SpinnerNumberModel(2, 2, playersNumberLimit, 1);     
        playersNumberSpinner = new JSpinner(model);
        playersNumberSpinner.setPreferredSize(new Dimension(100, playersNumberSpinner.getPreferredSize().height+2));
        
        generateGetUsersForm.setMnemonic(KeyEvent.VK_O);
        generateGetUsersForm.getAccessibleContext().setAccessibleDescription("Játékosok felvétele");
        generateGetUsersForm.setActionCommand("set_players_number");
        generateGetUsersForm.addActionListener(this);
        
        getUsersNumberForm.add(new JLabel("* Add meg a játékosok számát:"));
        getUsersNumberForm.add(playersNumberSpinner);
        getUsersNumberForm.add(generateGetUsersForm);
        return getUsersNumberForm;
    }
    
    public JPanel generateGetUsersForm(int playersNumber){
        setPlayerForms.clear();
        getUsersForm.removeAll();
        getUsersForm.setLayout(new VerticalFlowLayout());
        
        for(int i = 0; i < playersNumber; ++i){
            SetPlayerForm newSetPlayerForm = new SetPlayerForm(i);
                
                JPanel newSetPlayerFormPanel = new JPanel(new GridLayout(1,1));
                newSetPlayerFormPanel.add(newSetPlayerForm);
                newSetPlayerFormPanel.setBorder(new EmptyBorder(10,10,10,10));
        
            setPlayerForms.add(newSetPlayerForm);
            getUsersForm.add(newSetPlayerFormPanel);
        }
        
        startGame.setBorder(new EmptyBorder(10,10,10,10));
        startGame.setMnemonic(KeyEvent.VK_S);
        startGame.getAccessibleContext().setAccessibleDescription("Játék indítása!");
        startGame.setActionCommand("start_game");
        startGame.addActionListener(this);
        getUsersForm.add(startGame);   
        return getUsersForm;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        if(cmd.contains("set_players_number")){
            view.initLocalGame((Integer) playersNumberSpinner.getValue());
        }
        if(cmd.contains("start_game")){
            for (SetPlayerForm f : setPlayerForms) {
                view.addPlayer(f.playerNameField.getText(), f.getColor(), false);
            }
            view.startLocalGame();
        }
    }
    
    public void PanelRepaint() {
        scrollFrameForGetUsersForm.setPreferredSize(new Dimension(parentFrame.getSize().width-70, parentFrame.getSize().height-140));
        scrollFrameForGetUsersForm.revalidate();
        scrollFrameForGetUsersForm.updateUI();
        scrollFrameForGetUsersForm.repaint();
    }
}
