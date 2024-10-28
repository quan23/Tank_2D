package Model.Methods;

import java.awt.Component;
import java.awt.Graphics2D;

public interface EntityPaint extends EntityCoor {

    public void paint(Component c, Graphics2D g);

    public void setTan(double tan);

    public void turn(double tan);
}
