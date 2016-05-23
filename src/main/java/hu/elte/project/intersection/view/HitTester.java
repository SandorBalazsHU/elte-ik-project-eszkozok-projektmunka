/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.elte.project.intersection.view;

import hu.elte.project.intersection.model.graphModel.VPlayer;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.concurrent.Callable;

public class HitTester implements Callable<Boolean> {

    private Boolean run  = true;
    private double x;
    private double y;
    private int newX;
    private int newY;
    private ArrayList<VPlayer> playerViews;
    
   public HitTester(ArrayList<VPlayer> playerViews, double x, double y, int newX, int newY) {
    this.x = x;
    this.y = y;
    this.newX = newX;
    this.newY = newY;
    this.playerViews = playerViews;
   }

   @Override
   public Boolean call() {
        for(int i = 0; i < playerViews.size(); i++)
        {
            for(int j = 1; j < playerViews.get(i).x.size()-4; j++)
            {
                run = !Line2D.linesIntersect(playerViews.get(i).x.get(j-1),playerViews.get(i).y.get(j-1),playerViews.get(i).x.get(j),playerViews.get(i).y.get(j),x,y,newX,newY);
                if(!run) break;
            }
            if(!run) break;
        }
        return run;
   }
}
