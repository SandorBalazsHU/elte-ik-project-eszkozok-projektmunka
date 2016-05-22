package hu.elte.project.intersection.view;

import hu.elte.project.intersection.graphModel.VPlayer;
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

public class DrawFrame  extends JPanel implements ActionListener, KeyListener{
    public static final int ablakX = 800;
    public static final int ablakY = 600;
    private Dimension dim = new Dimension(ablakX, ablakY);
    
    private static final int fps = 50;
    private static final int initDelay = 200;
    
    //5
    private static double roll = 10.0d;
    //5
    private static double r = 5.0d;
    private static int thickness = 5;
    
    public Timer timer;
    private double alpha = -10;
    private double betha = 50;
    private boolean run = true;
    private final Set<Integer> pressed = new HashSet<Integer>();
    ArrayList<VPlayer> playerViews = new ArrayList<VPlayer>();
    
    public DrawFrame(){
        timer = new Timer(fps, this);
        timer.setInitialDelay(initDelay);
        setBackground(Color.DARK_GRAY);
        
        VPlayer p1 = new VPlayer();
        p1.x.add(100); p1.x.add(105);
        p1.y.add(100); p1.y.add(105);
        playerViews.add(p1);
        
        VPlayer p2 = new VPlayer();
        p2.x.add(300); p2.x.add(305);
        p2.y.add(300); p2.y.add(305);
        playerViews.add(p2);
        
    }

    public void step(ArrayList<VPlayer> playerViews, VPlayer playerView, double alpha, double r) {
        double x = playerView.x.get(playerView.x.size()-1);
        double y = playerView.y.get(playerView.y.size()-1);
        int newX = (int) Math.round( x + r * Math.cos(alpha * Math.PI / 180.0d));
        int newY = (int) Math.round( y + r * Math.sin(alpha * Math.PI / 180.0d));
        
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
        
        if(newX > 0 && newY > 0 && newX < ablakX && newY < ablakY && run)
        {
            playerView.x.add(newX);
            playerView.y.add(newY);
        }
    }
    
    public void render(Graphics2D graphics2d, Color color, int thickness,  VPlayer playerView) {
        graphics2d.setColor(color);
        graphics2d.setStroke(new BasicStroke(thickness));
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
        
        step(playerViews, playerViews.get(0), alpha, r);
        render(graphics2d, Color.RED, thickness, playerViews.get(0));
        
        step(playerViews, playerViews.get(1), betha, r);
        render(graphics2d, Color.YELLOW, thickness, playerViews.get(1));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (Integer k : pressed) {
            switch (k) {
                case KeyEvent.VK_A: alpha-=roll;
                break;
                case KeyEvent.VK_D: alpha+=roll;
                break;
                case KeyEvent.VK_H: betha-=roll;
                break;
                case KeyEvent.VK_K: betha+=roll;
                break;
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
