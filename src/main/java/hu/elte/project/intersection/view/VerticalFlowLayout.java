package hu.elte.project.intersection.view;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.LayoutManager2;
import java.util.LinkedHashSet;

/**
 *
 * Sándor Balázs - AZA6NL
 */

/*
A file leírása:
    Egy saját LayoutManager mely oszlopfolytonosan fenntről lefelé rendezi az elemeket.
    A legszélesebb elem szélességére állítja a saját és a benne lévő összes elem szélességét.
    Ha egy elem túllóg lefelé akkor azt új oszloppba teszi!
    Egy rendezetlen halmazban tárolja az elemeket
*/


public class VerticalFlowLayout implements LayoutManager2 
{
    final private LinkedHashSet<Component> components = new LinkedHashSet<Component> ();
    private int hgap;
    private int vgap;
    private int maxWidth;
    private int maxHeight;
    
    private static final int maxItemWidth = 2000;
    private static final int maxItemHeight = 2000;

    public void setHGap(int hgap) { this.hgap = hgap; }
    public void setVGap(int vgap) { this.vgap = vgap; }
    
    public VerticalFlowLayout(){
        hgap = 0;
        vgap = 0;
        maxWidth = 0;
        maxHeight = 0;
    }

    
    @Override public void addLayoutComponent(Component comp, Object constraints) {
        this.components.add(comp);
    }

    @Override public float getLayoutAlignmentX(Container target) {
        return maxWidth;
    }

    @Override public float getLayoutAlignmentY(Container target) {
        return maxHeight;
    }

    @Override public void invalidateLayout(Container target) {
    }

    @Override public void addLayoutComponent(String name, Component comp) {
        this.components.add(comp);
    }

    @Override public void layoutContainer(Container parent) {
        maxWidth = 0;
        maxHeight = 0;
        for (Component c : this.components)
        {
            if (c.isVisible())
            {
                Dimension d = c.getPreferredSize();
                c.setBounds(0, maxHeight, d.width, d.height);
                maxHeight += d.height;  

                if(d.width>maxWidth){
                    maxWidth = d.width;
                }
            }
        }
    }

    @Override public Dimension minimumLayoutSize(Container parent) {
        return new Dimension(0,0);
    }

    @Override public Dimension preferredLayoutSize(Container parent) {
        int outWidth = 0;
        int outHeight = 0;
        for (Component c : this.components)
        {
            if (c.isVisible())
            {
                Dimension d = c.getPreferredSize();
                outHeight += d.height;  
                if(d.width>outWidth){
                    outWidth = d.width;
                }
            }
        }
        return new Dimension(outWidth,outHeight);
    }

    @Override public Dimension maximumLayoutSize(Container target) {
        return new Dimension(maxItemWidth,maxItemHeight);
    }

    @Override public void removeLayoutComponent(Component comp) {
        this.components.remove(comp);
    }
}