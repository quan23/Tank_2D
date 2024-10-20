
package Controller;

import Model.Component.Health;
import Model.Component.HitRectangle;
import Model.Component.ObjIcon;
import Model.Component.StaticIcon;
import Model.Entity;
import Model.Map;
import Model.Player;
import View.Frame;
import View.Window;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GameControl implements Runnable{
    private Map map; 
    private UserInput input;
    private Window gameW;
    private Frame fmap;
    private Player player1,player2;
    private Thread gameThread;
    public GameControl() {
        map = new Map();
        input = new UserInput();
        gameW = new Window();
        fmap = new Frame(0,0,480,480);
        fmap.setMap(map);
        gameW.add(fmap);
        gameW.addKeyListener(input);
        gameW.setVisible(true);
        player1=new Player();
        
        player1.setHealth(new Health(100,100));
        player1.setIcon(new StaticIcon(32,32,"player1_tank_up.png"));
        player1.setHitBox(new HitRectangle(32,32));
        player1.setCoor(100, 100);
        loadMap("gay");
    }
    
    public void loadMap(String link) {
        map.setObjList(new ArrayList<Entity>());
        map.addObject(player1);

    }
    
    public void startGame() {
        gameThread=new Thread(this);
        gameThread.start();
    }
    
    @Override
    public void run() {
        while (gameThread!=null) {
            update();
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                Logger.getLogger(GameControl.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            repaint();
        }
        
    }
    private void update() {
        if (input.getKeyStatus(KeyEvent.VK_RIGHT)) player1.move(1, 0);
    }

    private void repaint() {
        fmap.repaint();
    }
    
    
}
