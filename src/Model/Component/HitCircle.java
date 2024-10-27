
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

    



}
