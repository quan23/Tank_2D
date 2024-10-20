
package View;

import Model.Component.ObjIcon;
import Model.EntityMethods;
import Model.Map;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
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
        for(EntityMethods E:map.getObjList()) {
            E.paint(this, g);
        }
    }
    
    public void setMap(Map map) {
        this.map = map;
    }
    
    
}
