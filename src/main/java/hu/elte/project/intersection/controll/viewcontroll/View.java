package hu.elte.project.intersection.controll.viewcontroll;

import hu.elte.project.intersection.controll.Controll;
import hu.elte.project.intersection.model.Game;
import hu.elte.project.intersection.model.Player;
import hu.elte.project.intersection.model.graphmodel.CKey;
import hu.elte.project.intersection.view.MainWindow;
import hu.elte.project.intersection.view.forms.PopUpWindow;
import java.awt.Color;
import java.awt.FlowLayout;

/**
 * <h1>Interfésza nézehez.</h1>
 * A view osztály egy szamványos kapcsolófelületet kíván adni a megjelenítési réteghez.
 * 
 * @author  <b>Sándor Balázs</b> - <b>Bognár Dániel</b> - <b>Kiss Erik</b>
 * @version 1.0
 * @since   2016-05-03 
 */
public class View {
    /**
     * A controll fájlra mutató referencia a kommunikáció biztosításához.
     */
    private  final Controll controll;
    
    /**
     * A főablakra mutató referencia a kommunikáció biztosításához.
     */
    private MainWindow mainWindow;
    
    /**
     * A Game objektum létrehozása ami a játék összes adatát tárolja.
     */
    private static Game game = new Game();
    
    /**
     * A View osztály konstruktora.
     * Menti a controll osztályra mutató  referencit.
     * 
     * @param c A controll osztályra mutató referencia.
     */
    public View(Controll c){
        this.controll = c;
    }
    
    /**
     * Megjeleníti a főablakot.
     */
    public void showMainWindow(){
        mainWindow = new MainWindow(this);
        mainWindow.pack();
        mainWindow.setVisible(true);
    }
    
    /**
     * Előkészíti és megjeleníti a Helyi játék létrehozása nézetet.
     * Hívásra elrejti az indító logót,
     * Blra zár és megjeleníti  nézetet
     */
    public void showLocalGameForm(){
        mainWindow.logoPanel.setVisible(false);
        mainWindow.centwrPanelLayout.setAlignment(FlowLayout.LEFT);
        mainWindow.localGameForm.getUsersForm.setVisible(false);
        mainWindow.localGameForm.setVisible(true);
        mainWindow.localGameForm.getUsersNumberForm.setVisible(true);
    }
    
    /**
     * Helyi töbszemélyes játékmód előkészítése
     * @param n 
     */
    public void initLocalGame(int n){
        game = new Game();
        mainWindow.localGameForm.getUsersForm.removeAll();
        mainWindow.localGameForm.generateGetUsersForm(n).setVisible(true);
        mainWindow.localGameForm.getUsersForm.revalidate();
        mainWindow.localGameForm.getUsersForm.repaint();
        mainWindow.localGameForm.getUsersNumberForm.setVisible(false);
        mainWindow.localGameForm.PanelRepaint();
    }
    /**
     * Játékos hozzáadása a Game objektumhuz.
     * @param name A játékos neve
     * @param color A választott színe
     * @param isRemoteUser Távoli játékos-e?
     */
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
            if(game.getPlayersNumber() == 3){
                newPlayer.setControllKey(new CKey(CKey.RZ));
            }
            if(game.getPlayersNumber() == 4){
                newPlayer.setControllKey(new CKey(CKey.VN));
            }
            if(game.getPlayersNumber() == 5){
                newPlayer.setControllKey(new CKey(CKey.YC));
            }
        }
    }
    
    /**
     * Helyi játék indítása
     */
    public void startLocalGame(){
        initGame();
        startRound();
        mainWindow.localGameForm.setVisible(false);
    }
    
    /**
     * Kör indítása.
     */
    public void startRound(){
        mainWindow.gamePanel.timer.start();
    }
    
    /**
     * Kör vége.
     */
    public void stopRound(){
        mainWindow.gamePanel.timer.stop();
    }
    
    /**
     * Játékelőkészítés
     */
    public void initGame(){
        mainWindow.logoPanel.setVisible(false);
        mainWindow.centwrPanelLayout.setAlignment(FlowLayout.LEFT);
        mainWindow.scoreBoard.setVisible(true);
        mainWindow.gamePanel.setVisible(true);
        mainWindow.gamePanel.setFocusable(true);
        mainWindow.gamePanel.requestFocus();
        mainWindow.gamePanel.requestFocusInWindow();
        mainWindow.gamePanel.init(game,this);
        mainWindow.scoreBoard.init(game);
    }
    
    /**
     * Az eredménylista frissítéséért felel.
     */
    public void refreshScoreBoard(){
        mainWindow.scoreBoard.repaintME();
    }
    
    /**
     * Győzelem esetén hívódik meg.
     * Eredményt hirdet és előkészíti a következő kört.
     * @param winner A győztes játékos
     */
    public void win(Player winner){
        stopRound();
        
        new PopUpWindow(PopUpWindow.COSTUM, "A nyertes: " + winner.getName() + ".\n" + winner.getXP() + " pontot szerzett.");
        
        try {
            Thread.sleep(2000);
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }

        game = new Game();
        mainWindow.gamePanel.setVisible(false);
        mainWindow.scoreBoard.removeAll();
        mainWindow.scoreBoard.setVisible(false);
        mainWindow.logoPanel.setVisible(true);
        mainWindow.centwrPanelLayout.setAlignment(FlowLayout.CENTER);
    }
}
