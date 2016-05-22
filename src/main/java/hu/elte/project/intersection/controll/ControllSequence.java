/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.elte.project.intersection.controll;

import hu.elte.project.intersection.view.LogoWindow;
import hu.elte.project.intersection.view.MainWindow;

/**
 *
 * @author sandorbalazs
 */
public class ControllSequence {
     public void start(){
        LogoWindow logoWindow = new LogoWindow();
        logoWindow.setVisible(true);
        /*try {
            Thread.sleep(2000);
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }*/
        logoWindow.setVisible(false);
        logoWindow.dispose();
        
        MainWindow setGameWindow = new MainWindow();
        setGameWindow.pack();
        setGameWindow.setVisible(true);
     }
}
