/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.elte.project.intersection.view;

import hu.elte.project.intersection.controll.Logger;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JProgressBar;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * Sándor Balázs - AZA6NL
 */

/*
A file leírása:
    Egy Swing abalak nyiása. A betöltést jelzi és a betöltő szál fogja bezárni!
*/
public class LogoWindow extends JFrame{
    public JProgressBar progressBar;
    private BufferedImage loadImage;

        public LogoWindow(){

        //Ablak előkészítés
        setTitle("Transport Company - Betöltés");
        setLayout(new VerticalFlowLayout());
        setSize(305, 329);
        ImageIcon img = new ImageIcon("TempFiles/ViewImages/mainIcon.png");
        setIconImage(img.getImage());
        setLocation(200,100);
        setResizable(false);
        
        //Kilépés
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        //Kép betöltése - Ha nem található a kép a program tovább fut megjelenítés nélkül. Hiba logot hagy!
        try {loadImage = ImageIO.read(new File("TempFiles/viewImages/mainLogo.png"));
        } catch (IOException ex) {
            Logger.writeLog(ex);
            new PopUpWindow(2,"File nem tölthető be: TempFiles/viewImages/mainLogo.png");
        }
        JLabel image = new JLabel();  
        if(loadImage != null){image.setIcon(new ImageIcon(loadImage));}
        
        //Képet középrezárni átméretezés esetére és hozzáadni a Frame-hoz!
        //image.setHorizontalAlignment(JLabel.CENTER);
        //image.setVerticalAlignment(JLabel.CENTER);
        
        image.setPreferredSize(new Dimension(300, 300));
        add(image);
    }
}
