package View;

import Model.Entity;
import Model.Map;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import Model.Methods.EntityPaint;
import java.awt.Dimension;

public class GameFrame extends JPanel {

    private Map map;

    public GameFrame(int x, int y, int u, int v) {
        super();
        this.setPreferredSize(new Dimension(u-x,v-y));
        this.setBounds(x, y, u, v);
        this.setBackground(Color.LIGHT_GRAY);

        this.setDoubleBuffered(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        super.paintComponent(g2d);
        for (Entity E : map.getObjList()) {
            if (E instanceof EntityPaint Ep) {
                Ep.paint(this, g2d);
            }

        }
    }

    public void setMap(Map map) {
        this.map = map;
    }

}
