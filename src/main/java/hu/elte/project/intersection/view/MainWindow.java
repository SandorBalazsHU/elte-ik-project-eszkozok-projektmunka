/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.elte.project.intersection.view;

import hu.elte.project.intersection.controll.Logger;
import hu.elte.project.intersection.view.forms.LocalGameForm;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;

/**
 *
 * @author sandorbalazs
 */
public class MainWindow extends JFrame implements ActionListener {
    
private JPanel topPanel = new JPanel(new GridLayout(1,5));

    private final JPanel leftPanel = new JPanel(new VerticalFlowLayout());
    private final JPanel rightPanel = new JPanel(new FlowLayout());
    private final JPanel bottomPanel = new JPanel(new GridLayout(1,5));
    private final JPanel centerPanel = new JPanel(new GridLayout(1,1));
    private final JMenuBar menuBar = new JMenuBar();
    private final JToolBar playersMenu = new JToolBar("Still draggable");
    public DrawFrame gamePanel;
    private LocalGameForm localGameStartForm = new LocalGameForm(this);
    
    public MainWindow(){
        //Általános beállítások!
        setTitle("Intersection!");
        setLayout(new BorderLayout(4, 4));
        //Teljes képernyős méretű ablak legyen 800 * 600 ra kicsinyedi le!
        setMinimumSize(new Dimension(800, 600));
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        ImageIcon img = new ImageIcon("TempFiles/ViewImages/mainIcon.png");
        setIconImage(img.getImage());

        //Menü hozzáadásaű
        setJMenuBar(createMenuBar());
        
        //Eszköztár hozzáadása a bal panelhez
        //JToolBar playersToolbar = createPlayersToolbar();
        //leftPanel.add(playersToolbar);
        
        gamePanel = new DrawFrame();
        gamePanel.setPreferredSize(new Dimension(800, 600));
        
        gamePanel.addKeyListener(gamePanel);

        
        //4 fő panel hozzáadása a frame-hoz
        add(topPanel, BorderLayout.PAGE_START);
        add(rightPanel, BorderLayout.LINE_END);
        add(leftPanel, BorderLayout.LINE_START);
        add(bottomPanel, BorderLayout.PAGE_END);
        add(centerPanel, BorderLayout.CENTER);
        
        add(gamePanel, BorderLayout.CENTER);
        gamePanel.setVisible(false);

        add(localGameStartForm, BorderLayout.CENTER);
        localGameStartForm.setVisible(false);
        
        //Kilépés esetén ne csináljon semmit majd a saját eseménykezelő elintézi!
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        GameWindowListener exitListener = new GameWindowListener();
        addWindowListener(exitListener);
        
        addWindowStateListener(new WindowStateListener() {
            @Override
            public void windowStateChanged(WindowEvent e) {
                if(localGameStartForm.isVisible()) localGameStartForm.PanelRepaint();
            }
        });
    }
  
    private JMenuBar createMenuBar() {
        JMenu menu;
        JMenuItem menuItem;
       
        //File
        ImageIcon fileIcon = new ImageIcon("TempFiles/ViewImages/Icons/fileIcon.png");
        menu = new JMenu("File");
        menu.setMnemonic(KeyEvent.VK_F);
        menu.getAccessibleContext().setAccessibleDescription("File menü!");
        if(fileIcon != null){menu.setIcon(fileIcon);}
        menuBar.add(menu);
        
        //Egyjátékos mód
        ImageIcon singleGameIcon = new ImageIcon("TempFiles/ViewImages/Icons/singlePlayer.png");
        menuItem = new JMenuItem("Egyjátékos mód", singleGameIcon);
        menuItem.setMnemonic(KeyEvent.VK_E);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.ALT_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription("Új játék indítása!");
        menuItem.setActionCommand("single_game_open");
        menuItem.addActionListener(this);
        menu.add(menuItem);
        
        //Helyi játék
        ImageIcon localGameIcon = new ImageIcon("TempFiles/ViewImages/Icons/localGame.png");
        menuItem = new JMenuItem("Helyi játék", localGameIcon);
        menuItem.setMnemonic(KeyEvent.VK_H);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, ActionEvent.ALT_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription("Többszemélyes helyi játék indítása!");
        menuItem.setActionCommand("local_game_open");
        menuItem.addActionListener(this);
        menu.add(menuItem);
        
        //Új szerver
        ImageIcon newServerIcon = new ImageIcon("TempFiles/ViewImages/Icons/fileIcon.png");
        menuItem = new JMenuItem("Új szerver indítása", newServerIcon);
        menuItem.setMnemonic(KeyEvent.VK_U);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U, ActionEvent.ALT_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription("Új saját játékszerver indítása");
        menuItem.setActionCommand("new_server_start");
        menuItem.addActionListener(this);
        menu.add(menuItem);
        
        //Csatlakozás
        ImageIcon connectIcon = new ImageIcon("TempFiles/ViewImages/Icons/connect.png");
        menuItem = new JMenuItem("Csatlakozás", connectIcon);
        menuItem.setMnemonic(KeyEvent.VK_C);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.ALT_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription("Csatlakozás egy futó játékszerverhez");
        menuItem.setActionCommand("connect_to_game");
        menuItem.addActionListener(this);
        menu.add(menuItem);
        
        //Beállítások
        ImageIcon settingsIcon = new ImageIcon("TempFiles/ViewImages/Icons/settingsIcon.png");
        menuItem = new JMenuItem("Beállítások", settingsIcon);
        menuItem.setMnemonic(KeyEvent.VK_B);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B, ActionEvent.ALT_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription("Programbeállítások");
        menuItem.setActionCommand("menu_settings");
        menuItem.addActionListener(this);
        menu.add(menuItem);

        //Súgó
        ImageIcon helpIcon = new ImageIcon("TempFiles/ViewImages/Icons/helpIcon.png");
        menu = new JMenu("Súgó");
        menu.setMnemonic(KeyEvent.VK_S);
        menu.getAccessibleContext().setAccessibleDescription("Súgó");
        menu.setActionCommand("menu_minHelp");
        menu.setIcon(helpIcon);
        menuItem.addActionListener(this);
        menuBar.add(menu);
        
        //Játékleírás
        ImageIcon descriptionIcon = new ImageIcon("TempFiles/ViewImages/Icons/descriptionIcon.png");
        menuItem = new JMenuItem("Játékleírás", descriptionIcon);
        menuItem.setMnemonic(KeyEvent.VK_J);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_J, ActionEvent.ALT_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription("Játékleírás");
        menuItem.setActionCommand("menu_description");
        menuItem.addActionListener(this);
        menu.add(menuItem);
        
        //Súgó
        menuItem = new JMenuItem("Súgó", helpIcon);
        menuItem.setMnemonic(KeyEvent.VK_S);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.ALT_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription("A súgó");
        menuItem.setActionCommand("menu_help");
        menuItem.addActionListener(this);
        menu.add(menuItem);
        
        //Névjegy
        ImageIcon nameCardIcon = new ImageIcon("TempFiles/ViewImages/Icons/nameCardIcon.png");
        menuItem = new JMenuItem("Névjegy", nameCardIcon);
        menuItem.setMnemonic(KeyEvent.VK_N);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.ALT_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription("A program névjegye");
        menuItem.setActionCommand("menu_namecard");
        menuItem.addActionListener(this);
        menu.add(menuItem);
        
        return menuBar;
    }
    
    //A fő eszköztár elékészítése
    private JToolBar createPlayersToolbar(){
        playersMenu.setName("Players");
        playersMenu.setAutoscrolls(true);
        GridLayout lay = new GridLayout(4,2,1,3);
        playersMenu.setLayout(lay);
        playersMenu.setOrientation(JToolBar.VERTICAL);
        playersMenu.setMargin(new Insets(10, 2, 2, 2));
        /*JButton button = null;

        button = makeNavigationButton("VerticalRoad", "VerticalRoad","Függőleges út","Függőleges út");
        button.addActionListener(this); button.setActionCommand("groundRoadVertical");
        playersMenu.add(button);

        button = makeNavigationButton("HorizontalRoad", "HorizontalRoad","Vízszintes út","Vízszintes út");
        button.addActionListener(this); button.setActionCommand("groundRoadHorizontal");
        playersMenu.add(button);

        button = makeNavigationButton("RightBottomRoad", "RightBottomRoad","Jobb kanyar","Jobb kanyar le");
        button.addActionListener(this); button.setActionCommand("groundRoadRightBottom");
        playersMenu.add(button);

        button = makeNavigationButton("LeftBottomRoad", "LeftBottomRoad","Bal kanyar","Bal kanyar le");
        button.addActionListener(this); button.setActionCommand("groundRoadLeftBottom");
        playersMenu.add(button);

        button = makeNavigationButton("RightTopRoad", "RightTopRoad","Jobb kanyar","Jobb kanyar fel");
        button.addActionListener(this); button.setActionCommand("groundRoadRightTop");
        playersMenu.add(button);
        
        button = makeNavigationButton("LeftTopRoad", "LeftTopRoad","Bal kanyar","Bal kanyar fel");
        button.addActionListener(this); button.setActionCommand("groundRoadLeftTop");
        playersMenu.add(button);

        button = makeNavigationButton("CrossRoad", "CrossRoad","Kereszteződés","Kereszteződés");
        button.addActionListener(this); button.setActionCommand("groundRoadCross");
        playersMenu.add(button);
        
        button = makeNavigationButton("info", "info","Inforbáció","Inforbáció");
        button.addActionListener(this); button.setActionCommand("info");
        playersMenu.add(button);*/

        return playersMenu;
    }
    /*protected JButton makeNavigationButton(String imageName, String actionCommand,
                                           String toolTipText,
                                           String altText) {
        //Look for the image.
        String imgLocation = "TempFiles/ViewImages/buttons/"+ imageName+ ".jpg";
       
        //Create and initialize the button.
        JButton button = new JButton();
        button.setActionCommand(actionCommand);
        button.setToolTipText(toolTipText);
        ImageIcon icon = new ImageIcon(imgLocation, altText); 
        if (icon != null){
            button.setIcon(icon);
        } else {
            button.setText(altText);
            Logger.writeLog("warning", "Resource not found: "+ imgLocation);
        }

        return button;
    }*/
    
    //ESEMÉNYKEZELÉS
    @Override
    public void actionPerformed(ActionEvent e)
    {
        String cmd = e.getActionCommand();
        /*if(cmd.contains("Road")){ Game.mousePickStatus = cmd; }
        if(cmd.contains("info")){ Game.mousePickStatus = "info"; }*/
        if(cmd.contains("local_game_open")){
            centerPanel.setVisible(false);
            localGameStartForm.setVisible(true);
            /*gamePanel.setVisible(true);
            gamePanel.setFocusable(true);
            gamePanel.requestFocus();
            gamePanel.requestFocusInWindow();
            gamePanel.timer.start();*/
        }
        //System.out.println("Selected: " + e.getActionCommand());
    }
}
