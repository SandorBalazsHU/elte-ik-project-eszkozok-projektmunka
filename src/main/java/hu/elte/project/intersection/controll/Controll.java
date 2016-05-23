/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.elte.project.intersection.controll;

import hu.elte.project.intersection.controll.viewcontroll.View;

/**
 *
 * @author sandorbalazs
 */
public class Controll {
    private View view;
    
    public Controll(){
        this.view = new View(this);
    }
    
    public void start(){
        view.showLogoWindow();
        view.showMainWindow();
    }
}
