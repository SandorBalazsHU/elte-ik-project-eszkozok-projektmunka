/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.elte.project.intersection.controll.viewcontroll;

import hu.elte.project.intersection.controll.Controll;
import hu.elte.project.intersection.model.Game;
import hu.elte.project.intersection.model.Player;
import hu.elte.project.intersection.model.graphmodel.CKey;
import hu.elte.project.intersection.view.LogoWindow;
import hu.elte.project.intersection.view.MainWindow;
import java.awt.Color;

/**
 *
 * @author sandorbalazs
 */
public class View {
    private  final Controll controll;
    private MainWindow mainWindow;
    private static Game game = new Game();
    
    public View(Controll c){
        this.controll = c;
    }
    
    public void showLogoWindow(){
        LogoWindow logoWindow = new LogoWindow();
        logoWindow.setVisible(true);
        try {
            Thread.sleep(2000);
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        logoWindow.setVisible(false);
        logoWindow.dispose();
    }
    
    public void showMainWindow(){
        mainWindow = new MainWindow(this);
        mainWindow.pack();
        mainWindow.setVisible(true);
    }
    
    public void showLocalGameForm(){
        mainWindow.localGameForm.setVisible(true);
    }
    
    public void initLocalGame(int n){
        mainWindow.localGameForm.generateGetUsersForm(n).setVisible(true);
        mainWindow.localGameForm.getUsersForm.revalidate();
        mainWindow.localGameForm.getUsersForm.repaint();
        mainWindow.localGameForm.getUsersNumberForm.setVisible(false);
        mainWindow.localGameForm.PanelRepaint();
    }
    
    public void addPlayer(String name, Color color, boolean isRemoteUser){
        Player newPlayer = new Player(name, color, isRemoteUser);
        game.addPlayer(newPlayer);
        if(!isRemoteUser){
            if(game.getPlayersNumber() == 1){
                newPlayer.setControllKey(new CKey(CKey.AD));
            }
            if(game.getPlayersNumber() == 2){
                newPlayer.setControllKey(new CKey(CKey.JL));
            }
            if(game.getPlayersNumber() > 2){
                int randomNum = 2 + (int)(Math.random() * 4); 
                newPlayer.setControllKey(new CKey(randomNum));
            }
        }

    }
    
    public void startLocalGame(){
        initGame();
        startRound();
        mainWindow.localGameForm.setVisible(false);
        mainWindow.gamePanel.setVisible(true);
        mainWindow.gamePanel.setFocusable(true);
        mainWindow.gamePanel.requestFocus();
        mainWindow.gamePanel.requestFocusInWindow();
    }
    public void startRound(){
        mainWindow.gamePanel.timer.start();
    }
    public void initGame(){
        mainWindow.gamePanel.init(game);
        mainWindow.scoreBoardnew.init(game);
    }
}
