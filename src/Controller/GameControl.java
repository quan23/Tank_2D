package Controller;

import Model.Block;
import Model.Component.Health;
import Model.Component.HitCircle;
import Model.Component.HitRectangle;
import Model.Component.StaticIcon;
import Model.Entity;
import Model.Map;
import Model.Methods.EntityHitBox;
import Model.Player;
import View.Frame;
import View.Window;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class GameControl implements Runnable {

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
        //gameW.pack();
        player1 = new Player();
        player1.setHealth(new Health(100, 100));
        player1.setIcon(new StaticIcon(32, 32, "player1_tank_up.png"));
        player1.setHitBox(new HitCircle(10));
        player1.setCoor(96, 96);
        try {
            loadMap("checkbox_states.txt");
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

    public void collisionUpdate(int x, int y,Player player) {
        player.goForward(x, y);
        for (Entity e : map.getObjList()) {
            if ((e instanceof EntityHitBox b)&&(b!=player)) {
                if ((b.getHitBox() != null) && b.getHitBox().checkCollison(player.getHitBox())) {
                    player.goForward(-x, -y);
                }
            }

        }
    }

    private void update() {
        if (input.getKeyStatus(KeyEvent.VK_W)) {
            collisionUpdate(0,1,player1);
            collisionUpdate(1,0,player1);
        }
        if (input.getKeyStatus(KeyEvent.VK_S)) {
            collisionUpdate(0,-1,player1);
            collisionUpdate(-1,0,player1);
        }
        if (input.getKeyStatus(KeyEvent.VK_D)) {
            player1.turn(3);
        }
        if (input.getKeyStatus(KeyEvent.VK_A)) {
            player1.turn(-3);
        }
    }

    private void repaint() {
        fmap.repaint();
    }
}
