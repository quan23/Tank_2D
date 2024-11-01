package Controller;

import Model.Block;
import Model.Bullet;
import Model.Component.Health;
import Model.Component.HitCircle;
import Model.Component.HitRectangle;
import Model.Component.StaticIcon;
import Model.Entity;
import Model.Map;
import Model.Methods.EntityHitBox;
import Model.Player;
import View.GameFrame;
import View.SideBar;
import View.Window;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public final class GameControl implements Runnable {

    private Map map;
    private UserInput input;
    private Window gameW;
    private GameFrame fmap;
    private Player player1, player2;
    private Thread gameThread;
    private SideBar sb;
    private JLabel player1Info, player2Info;
    private boolean gameOver = false;

    public GameControl() {

        map = new Map();
        input = new UserInput();
        gameW = new Window();
        fmap = new GameFrame(0, 0, 672, 672);
        fmap.setMap(map);
        gameW.add(fmap);
        gameW.addKeyListener(input);

        player1 = new Player();
        player1.setHealth(new Health(100, 100));
        player1.setIcon(new StaticIcon(32, 32, "player1_tank_up.png"));
        player1.setHitBox(new HitCircle(10));
        player1.setCoor(96, 96);

        player2 = new Player();
        player2.setHealth(new Health(100, 100));
        player2.setIcon(new StaticIcon(32, 32, "player2_tank_up.png"));
        player2.setHitBox(new HitCircle(10));
        player2.setCoor(602, 602);
        sb = new SideBar();
        initLabels();
        gameW.add(sb, BorderLayout.EAST);

        gameW.pack();
        try {
            loadMap("checkbox_states.txt");
        } catch (IOException ex) {
            Logger.getLogger(GameControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        map.addObject(player1);
        map.addObject(player2);
        //Information Panel

        gameW.setVisible(true);

    }

    private void initLabels() {

        // Player 1 Info Label
        player1Info = new JLabel("Player 1 Health: " + player1.getHealth().getHealth());
        player1Info.setForeground(Color.white);
        player1Info.setPreferredSize(new Dimension(240, 150));
        player1Info.setFont(new Font("Arial", Font.BOLD, 16));

        sb.add(player1Info);
        // Player 2 Info Label
        player2Info = new JLabel("Player 2 Health: " + player2.getHealth().getHealth());
        player2Info.setForeground(Color.white);
        player2Info.setPreferredSize(new Dimension(240, 150));
        player2Info.setFont(new Font("Arial", Font.BOLD, 16));

        sb.add(player2Info);
    }

    public void displayHealth(JLabel label, Player player) {
        label.setText(player.getHealth().toString());
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
        while (gameThread!=null) {
          
            update();
            repaint();
            if(stopGame()==true){
                showGameOver();
                break;
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                Logger.getLogger(GameControl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
 }

    public boolean stopGame() {
        if (player1.Death() || player2.Death()) {
            return true;
        }
        return false;
    }

    private void showGameOver() {
        JOptionPane.showMessageDialog(null, "Game Over!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
    }

    public void playerCollisionUpdate(int x, int y, Player player) {
        player.goForward(x, y);
        for (Entity e : map.getObjList()) {
            if ((e instanceof EntityHitBox b) && (b != player) && (!(b instanceof Player))) {
                if ((b.getHitBox() != null) && player.getHitBox().checkCollison(b.getHitBox())) {
                    if (!(b instanceof Bullet)) {
                        player.goForward(-x, -y);
                    }
                }
            }

        }
    }

    public boolean bulletCollisionUpdate(double x, double y, Bullet b) {
        b.goForward(x, y);
        for (Entity e : map.getObjList()) {
            if ((e instanceof EntityHitBox eH) && b.getHitBox() != null && b.getHitBox().checkCollison(eH.getHitBox()) && !(e instanceof Bullet)) {
                if ((e instanceof Player p)) {
                    //System.out.println(b.getX() + " " + b.getY());
                    b.dealDamage(p);
                    return true;
                } else {
                    b.goForward(-x, -y);
                    return b.collide(x, y);
                }
            }
        }
        return false;
    }

    private void update() {
        //Player1_KeyEvent
        if (input.getKeyStatus(KeyEvent.VK_W)) {
            playerCollisionUpdate(0, 1, player1);
            playerCollisionUpdate(1, 0, player1);
        }
        if (input.getKeyStatus(KeyEvent.VK_S)) {
            playerCollisionUpdate(0, -1, player1);
            playerCollisionUpdate(-1, 0, player1);
        }
        if (input.getKeyStatus(KeyEvent.VK_D)) {
            player1.turn(3);
        }

        if (input.getKeyStatus(KeyEvent.VK_A)) {
            player1.turn(-3);
        }
        if (input.getKeyStatus(KeyEvent.VK_SPACE)) {
            map.addObject(player1.shoot());
        }
        //Player2_KeyEvent
        if (input.getKeyStatus(KeyEvent.VK_UP)) {
            playerCollisionUpdate(0, 1, player2);
            playerCollisionUpdate(1, 0, player2);
        }
        if (input.getKeyStatus(KeyEvent.VK_DOWN)) {
            playerCollisionUpdate(0, -1, player2);
            playerCollisionUpdate(-1, 0, player2);
        }
        if (input.getKeyStatus(KeyEvent.VK_RIGHT)) {
            player2.turn(3);
        }
        if (input.getKeyStatus(KeyEvent.VK_LEFT)) {
            player2.turn(-3);
        }
        if (input.getKeyStatus(KeyEvent.VK_P)) {
            map.addObject(player2.shoot());
        }
       
        Iterator<Entity> iterator = map.getObjList().iterator();
        while (iterator.hasNext()) {
            Entity e = iterator.next();
            if (e instanceof Bullet b) {
                boolean checkTime = b.countDown();
                if ((bulletCollisionUpdate(0, 1, b) || bulletCollisionUpdate(1, 0, b)) || checkTime) {
                    iterator.remove();
                }
            }
        }
        player1.update();
        player2.update();

    }

    private void repaint() {
        SwingUtilities.invokeLater(() -> { // Gọi repaint trong luồng chính
            player1Info.setText("Player 1 Health: " + player1.getHealth().getHealth());
            player2Info.setText("Player 2 Health: " + player2.getHealth().getHealth());
            fmap.repaint();
        });
    }

}
