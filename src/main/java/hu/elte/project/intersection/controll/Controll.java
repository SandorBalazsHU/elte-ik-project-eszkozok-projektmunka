package hu.elte.project.intersection.controll;

import hu.elte.project.intersection.controll.viewcontroll.View;

/**
 * <h1>A controll osztály összekötő szerepet tölt be.</h1>
 * Az osztály célja összefogni a vezérlést végző osztályokat.
 * Összekötő kapocs a modell és a megjelenítő réteg között
 * 
 * @author  <b>Sándor Balázs</b> - <b>Bognár Dániel</b> - <b>Kiss Erik</b>
 * @version 1.0
 * @since   2016-05-03 
 */
public class Controll {
    
    /**
     * A megjelenítésrt felelős controll interface-re mutató referencia.
     */
    private View view;
    
    /**
     * Paraéter nélküli ontruktor a Controll osztályhoz.
     * Létrehozza a nézet interface-t.
     */
    public Controll(){
        this.view = new View(this);
    }
    
    /**
     * A Controll osztály start metódusával indul a program.
     */
    public void start(){
        view.showMainWindow();
    }
}
