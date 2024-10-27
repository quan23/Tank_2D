
package Model.Component;

import Model.Methods.EntityCoor;
import Model.Methods.EntityHitBox;


public abstract class HitBox implements EntityCoor,EntityHitBox{
    protected int x,y;
    
    protected int sqr(int x) {
        return x*x;
    }

    @Override
    public void move(int x, int y) {
        this.x=x;
        this.y=y;
    }
    
    @Override
    public void setCoor(int x,int y) {
        this.x=x;
        this.y=y;
    }
    
    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }
    public abstract boolean checkCollison(HitCircle target);
    public abstract boolean checkCollison(HitRectangle target);
    
    @Override
    public boolean checkCollison(HitBox target) {
        if (target==null) return false;
        else if (target instanceof HitCircle T) this.checkCollison(T);
        else if (target instanceof HitRectangle T) this.checkCollison(T);
        return false;
    }
    
}
