
package Model.Component;

public class HitCircle extends HitBox{
    protected final int radius;

    public HitCircle(int radius) {
        this.radius = radius;
    }

    @Override
    public boolean checkCollison(HitCircle target) {
        return sqr(radius+((HitCircle)target).radius)<=sqr(x-target.x)+sqr(y-target.y);
    }

    @Override
    public boolean checkCollison(HitRectangle target) {
        int x1=target.x-target.u/2;
        int y1=target.y-target.v/2;
        int u1=target.x+target.u/2;
        int v1=target.y+target.v/2;
        int x2=Integer.max(Integer.min(this.x,u1),x1);
        int y2=Integer.max(Integer.min(this.y,v1),y1);
        if (x2==this.x&&y2==this.y) return true;
        return sqr(this.x-x2)+sqr(this.y-y2)<=this.radius;
    }
}
