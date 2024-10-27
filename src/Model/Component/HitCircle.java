
package Model.Component;

public class HitCircle extends HitBox{
    protected final int radius;

    public HitCircle(int radius) {
        this.radius = radius;
    }

    @Override
    public boolean checkCollison(HitCircle target) {
        return sqr(radius+target.radius)<=sqr(x-target.x)+sqr(y-target.y);
    }

    @Override
    public boolean checkCollison(HitRectangle target) {
        return target.checkCollison(this);
    }
  @Override
    public boolean checkCollison(HitBox target) {
        if (target==null) return false;
        else if (target instanceof HitCircle T) return this.checkCollison(T);
        else if (target instanceof HitRectangle T) return this.checkCollison(T);
        return false;
    }
    



}
