
package View;

import Model.Component.ObjIcon;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JPanel;


public class Frame extends JPanel{
    private ArrayList<ObjIcon> IconList=null;
    public Frame(int x,int y,int u,int v) {
        super();
        //this.setPreferredSize(dimen);
        this.setBounds(x,y,u,v);
        this.setBackground(Color.red);
        
        this.setDoubleBuffered(true);
    }

    public void setIconList(ArrayList<ObjIcon> IconList) {
        this.IconList = IconList;
    }
    

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (IconList!=null)
        for (ObjIcon Icon:IconList) {
            Icon.render(this, g);
        }
        //Graphics2D g2d = (Graphics2D) g;
        //new ImageIcon(new ImageIcon("player1_tank_up.png").getImage().getScaledInstance(32, 32, Image.SCALE_DEFAULT)).paintIcon(this, g, 0, 460);
        //AffineTransform old = g2d.getTransform();
        //g2d.rotate(Math.toRadians(30.0));
        //g2d.setTransform(old);
        
    }
    
}
