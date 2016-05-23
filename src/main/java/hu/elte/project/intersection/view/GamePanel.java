package hu.elte.project.intersection.view;

import hu.elte.project.intersection.model.Game;
import hu.elte.project.intersection.model.graphModel.VPlayer;
import hu.elte.project.intersection.model.graphmodel.CKey;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel  extends JPanel implements ActionListener, KeyListener{
    public static final int windowX = 800;
    public static final int windowY = 600;
    private Dimension dim = new Dimension(windowX, windowY);
    
    private static final int fps = 50;
    private static final int initDelay = 200;
    
    public Timer timer;
    private boolean run = true;
    private final Set<Integer> pressed = new HashSet<Integer>();
    ArrayList<VPlayer> playerViews = new ArrayList<VPlayer>();
    
    public GamePanel(){
        timer = new Timer(fps, this);
        timer.setInitialDelay(initDelay);
        setBackground(Color.DARK_GRAY); 
    }
    
    public void init(Game game){
        for (int i = 0; i < game.getPlayersNumber(); ++i){
            playerViews.add(new VPlayer(game.getPlayer(i)));
        }
    }

    public void step(ArrayList<VPlayer> playerViews, VPlayer playerView) {
        double x = playerView.x.get(playerView.x.size()-1);
        double y = playerView.y.get(playerView.y.size()-1);
        int newX = (int) Math.round( x + playerView.r * Math.cos(playerView.alpha * Math.PI / 180.0d));
        int newY = (int) Math.round( y + playerView.r * Math.sin(playerView.alpha * Math.PI / 180.0d));
        
        final ExecutorService service;
        final Future<Boolean>  task;

        service = Executors.newFixedThreadPool(1);        
        task    = service.submit(new HitTester(playerViews, x, y, newX, newY));
        
        try {
            run = task.get();
        } catch(final InterruptedException | ExecutionException ex) {
            ex.printStackTrace();
        }
        service.shutdownNow();
        
        if(newX > 0 && newY > 0 && newX < windowX && newY < windowY && run)
        {
            playerView.x.add(newX);
            playerView.y.add(newY);
        }
    }
    
    public void render(Graphics2D graphics2d, VPlayer playerView) {
        graphics2d.setColor(playerView.player.getcolor());
        graphics2d.setStroke(new BasicStroke(playerView.thickness));
        for(int i = 1; i < playerView.x.size(); i++)
        {
            graphics2d.drawLine(playerView.x.get(i-1), playerView.y.get(i-1), playerView.x.get(i), playerView.y.get(i));
        }
    }
    
    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D graphics2d = (Graphics2D) graphics;
        RenderingHints renderingHints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2d.setRenderingHints(renderingHints);
        
        for(VPlayer p : playerViews) {
            step(playerViews, p);
            render(graphics2d, p);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (Integer k : pressed) {
            for(VPlayer p : playerViews) {
                if(p.controllKey.left == k)  p.alpha -= p.roll;
                if(p.controllKey.right == k) p.alpha += p.roll;
            }
        }
        repaint();
    }


    @Override
    public synchronized void keyPressed(KeyEvent e) {
        int k = e.getKeyCode();
        pressed.add(k);

    }
    @Override
    public void keyReleased(KeyEvent e) {
        pressed.remove(e.getKeyCode());
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public Dimension getPreferredSize() {
        return dim;
    }
}
