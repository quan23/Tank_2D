
package View;

import Model.Block;
import Model.Player;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import javax.swing.JPanel;


public class Render extends JPanel{
    
    public Render(Dimension dimen) {
        this.setPreferredSize(dimen);
        //this.setBackground(Color.red);
        //this.setBounds(0,0,dimen.width,dimen.height);
    }

    @Override
    public void paint(Graphics g) {
        Block brick = new Block(0,0,"Minecraft-Bricks.jpg",0);
        Player player = new Player(0,0,"player1_tank_up.png",0);
        Graphics2D g2d = (Graphics2D) g;
        AffineTransform old = g2d.getTransform();
        brick.getIcon().paintIcon(this, g, brick.getX(), brick.getY());
        g2d.rotate(Math.toRadians(30.0));
        player.getIcon().paintIcon(this, g, brick.getX(), brick.getY());
        player.getIcon().paintIcon(this, g, brick.getX()+50, brick.getY()+50);
        g2d.setTransform(old);
        brick.getIcon().paintIcon(this, g, brick.getX()+32, brick.getY()+32);
        
    }
    
}
