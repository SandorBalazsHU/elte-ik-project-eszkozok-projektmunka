/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.elte.project.intersection.view;

import hu.elte.project.intersection.controll.viewcontroll.View;
import hu.elte.project.intersection.view.forms.LocalGameForm;
import hu.elte.project.intersection.view.forms.ScoreBoard;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
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
    public final JPanel centerPanel = new JPanel(new VerticalFlowLayout());
    private final JMenuBar menuBar = new JMenuBar();
    public GamePanel gamePanel;
    public LocalGameForm localGameForm;
    private final View view;
    public ScoreBoard scoreBoard = new ScoreBoard();
    
    public MainWindow(View view){
        this.view = view;
        localGameForm = new LocalGameForm(this, view);
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
        
        //(int top, int left, int bottom, int right)
        scoreBoard.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        leftPanel.add(scoreBoard);
        
        gamePanel = new GamePanel();
        gamePanel.setBorder(BorderFactory.createLineBorder(Color.ORANGE, 5));
        gamePanel.setPreferredSize(new Dimension(800, 600));
        
        gamePanel.addKeyListener(gamePanel);

        
        //4 fő panel hozzáadása a frame-hoz
        add(topPanel, BorderLayout.PAGE_START);
        add(rightPanel, BorderLayout.LINE_END);
        add(leftPanel, BorderLayout.LINE_START);
        add(bottomPanel, BorderLayout.PAGE_END);
        add(centerPanel, BorderLayout.CENTER);
        
        centerPanel.add(gamePanel);
        gamePanel.setVisible(false);

        centerPanel.add(localGameForm);
        localGameForm.setVisible(false);
        
        //Kilépés esetén ne csináljon semmit majd a saját eseménykezelő elintézi!
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        GameWindowListener exitListener = new GameWindowListener();
        addWindowListener(exitListener);
        
        addWindowStateListener(new WindowStateListener() {
            @Override
            public void windowStateChanged(WindowEvent e) {
                if(localGameForm.isVisible()) localGameForm.PanelRepaint();
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

    //ESEMÉNYKEZELÉS
    @Override
    public void actionPerformed(ActionEvent e)
    {
        String cmd = e.getActionCommand();
        if(cmd.contains("local_game_open")){
            view.showLocalGameForm();
        }
    }
}
