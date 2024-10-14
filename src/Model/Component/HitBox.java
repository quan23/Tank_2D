
package Model.Component;

import Model.Entity;
import java.awt.Component;
import java.awt.Graphics;

public abstract class HitBox extends Entity{
    protected int x,y;
    protected int sqr(int x) {
        return x*x;
    }
    public void setPosision(int x,int y) {
        this.x=x;
        this.y=y;
    }

    @Override
    public void render(Component c, Graphics g) {
    }
    
    
    public abstract boolean checkCollison(HitCircle target);
    public abstract boolean checkCollison(HitRectangle target);
    
}
