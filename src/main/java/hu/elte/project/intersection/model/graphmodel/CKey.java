/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.elte.project.intersection.model.graphmodel;

import java.awt.event.KeyEvent;

/**
 *
 * @author sandorbalazs
 */
public class CKey {
    public final int left;
    public final int right;
    public final String name;
    
    public static final int AD = 0;
    public static final int JL = 1;
    public static final int RZ = 2;
    public static final int VN = 3;
    public static final int YC = 4;
    
    public CKey(int c){
        switch(c){
            case 0:
                left = KeyEvent.VK_A;
                right = KeyEvent.VK_D;
                name = "<=A D=>";
            break;
            case 1:
                left = KeyEvent.VK_J;
                right = KeyEvent.VK_L;
                name = "<=J L=>";
            break;
            case 2:
                left = KeyEvent.VK_R;
                right = KeyEvent.VK_Z;
                name = "<=R Z=>";
            break;
            case 3:
                left = KeyEvent.VK_V;
                right = KeyEvent.VK_N;
                name = "<=V N=>";
            break;
            case 4:
                left = KeyEvent.VK_Y;
                right = KeyEvent.VK_C;
                name = "<=Y C=>";
            break;
            default:
                left = KeyEvent.VK_A;
                right = KeyEvent.VK_D;
                name = "<=A D=>";
            break;
        }
    }
}
