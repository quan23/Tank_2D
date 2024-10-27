package Controller;

import Model.Component.Health;
import Model.Component.HitRectangle;
import Model.Component.StaticIcon;
import Model.Map;
import Model.Player;
import View.Frame;
import View.Window;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GameControl implements Runnable {
    private Map map;
    private UserInput input;
    private Window gameW;
    private Frame fmap;
    private Player player1, player2;
    private Thread gameThread;

    public GameControl() {
      
            map = new Map();
            input = new UserInput();
            gameW = new Window();
            fmap = new Frame(0, 0, 672, 672);
            fmap.setMap(map);
            gameW.add(fmap);
            gameW.addKeyListener(input);
            player1 = new Player();
            player1.setHealth(new Health(100, 100));
            player1.setIcon(new StaticIcon(32, 32, "player1_tank_up.png"));
            player1.setHitBox(new HitRectangle(32, 32));
            player1.setCoor(100, 100);
//            player2 = new Player();
//            player2.setHealth(new Health(100, 100));
//            player2.setIcon(new StaticIcon(32, 32, "player1_tank_up.png"));
//            player2.setHitBox(new HitRectangle(32, 32));
//            player2.setCoor(200, 200);
    
            
            try{
                loadMap("maptest.txt");
            } catch (IOException ex) {
                Logger.getLogger(GameControl.class.getName()).log(Level.SEVERE, null, ex);
            }
            map.addObject(player1);
            gameW.setVisible(true);   
            
    }
    public void loadMap(String link) throws IOException {
        map.loadMap(link);
        System.out.println("Map load successfully");
    }

    public void startGame() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        while (gameThread != null) {
            update();
            repaint();
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                Logger.getLogger(GameControl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    private void update() {
        if(input.getKeyStatus(KeyEvent.VK_W)){
                player1.goForward();
        }  
         if(input.getKeyStatus(KeyEvent.VK_S)){
                player1.goForward();
        } if(input.getKeyStatus(KeyEvent.VK_D)){
                player1.turn(1);
        }
         if(input.getKeyStatus(KeyEvent.VK_A)){
                player1.turn(-1);
        }
         
    }
    private void repaint() {
        fmap.repaint();
    }
}
