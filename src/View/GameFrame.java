package View;

import Model.Entity;
import Model.Map;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import Model.Methods.EntityPaint;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

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
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;

    if (map != null) {
        List<Entity> entities = new ArrayList<>(map.getObjList()); // Tạo bản sao
        for (Entity entity : entities) {
            if (entity instanceof EntityPaint ep) {
                ep.paint(this, g2d);
            }
        }
    }
}


    public void setMap(Map map) {
        this.map = map;
    }

}
