
package Model.Component;

public class HitCircle extends HitBox{
    protected final int radius;

    public HitCircle(int radius) {
        this.radius = radius;
    }
    
    @Override
    public boolean checkCollision(HitBox target) {
        if (target instanceof HitCircle) {
            if (sqr(radius+((HitCircle)target).radius)<=sqr(x-target.x)+sqr(y-target.y)) {
                return true;
            } else return false;
        }
        if (target instanceof HitRectangle) {
            int x=((HitRectangle)target).x-((HitRectangle)target).u/2;
            int y=((HitRectangle)target).y-((HitRectangle)target).v/2;
            int u=((HitRectangle)target).x+((HitRectangle)target).u/2;
            int v=((HitRectangle)target).y+((HitRectangle)target).v/2;
            int x2=Integer.max(Integer.min(this.x,u),x);
            int y2=Integer.max(Integer.min(this.y,v),y);
            if (sqr(this.x-x2)+sqr(this.y-y2)<=this.radius) {
                return true;
            } else return false;
        }
        return false;
    }
    
    
}
