
package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;


public class Frame extends JPanel{
    
    public Frame(int x,int y,int u,int v) {
        super();
        //this.setPreferredSize(dimen);
        this.setBounds(x,y,u,v);
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
