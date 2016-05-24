package hu.elte.project.intersection;

import hu.elte.project.intersection.controll.Controll;

/**
 * {@docRoot}
 * <h1>A főprogram</h1>
 * Ez a program belépési pontja. Itt tároljuk a kontrollra mutató rferenciát.
 * Itt indítjuk a program controlját.
 * 
 * @author  <b>Sándor Balázs</b> - <b>Bognár Dániel</b> - <b>Kiss Erik</b>
 * @version 1.0
 * @since   2016-05-03 
 */
public class Intersection {
    /**
     * A fő kontrollfájlra mutató referencia.
     */
    private static final Controll program = new Controll();
    
    /**
     * A főprogram. Ez a program belépési pontja. 
     * @param args A 
     */
    public static void main( String[] args ) {
        program.start();
    }
}
