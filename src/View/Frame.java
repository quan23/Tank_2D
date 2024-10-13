
package View;

import Model.Block;
import Model.Player;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import javax.swing.ImageIcon;
import javax.swing.JPanel;


public class Frame extends JPanel{
    
    public Frame(Dimension dimen) {
        super();
        this.setPreferredSize(dimen);
        this.setBounds(100,100,dimen.width,dimen.height);
        this.setBackground(Color.red);
        
        this.setDoubleBuffered(true);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        //Graphics2D g2d = (Graphics2D) g;
        //new ImageIcon(new ImageIcon("player1_tank_up.png").getImage().getScaledInstance(32, 32, Image.SCALE_DEFAULT)).paintIcon(this, g, 0, 460);
        //AffineTransform old = g2d.getTransform();
        //g2d.rotate(Math.toRadians(30.0));
        //g2d.setTransform(old);
        
    }
    
}
