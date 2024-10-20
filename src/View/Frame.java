
package View;

import Model.Entity;
import Model.Map;
import Model.Methods.EntityPaint;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;


public class Frame extends JPanel{
    private Map map;

    
    public Frame(int x,int y,int u,int v) {
        super();
        //this.setPreferredSize(dimen);
        this.setBounds(x,y,u,v);
        this.setBackground(Color.LIGHT_GRAY);
        
        this.setDoubleBuffered(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for(Entity E:map.getObjList()) {
            if (E instanceof EntityPaint Ep) {
                Ep.paint(this, g);
            }
                
        }
    }
    
    public void setMap(Map map) {
        this.map = map;
    }
    
    
}
