
package Model.Component;

public class HitRectangle extends HitBox{
    protected int u,v;

    public HitRectangle(int u, int v) {
        this.u = u;
        this.v = v;
    }
    
    @Override
    public boolean checkCollision(HitBox target) {
        if (target instanceof HitCircle) {
            int x=this.x-this.u/2;
            int y=this.y-this.v/2;
            int u=this.x+this.u/2;
            int v=this.y+this.v/2;
            int x2=Integer.max(Integer.min(target.x,u),x);
            int y2=Integer.max(Integer.min(target.y,v),y);
            if (sqr(target.x-x2)+sqr(target.y-y2)<=((HitCircle)target).radius) {
                return true;
            } else return false;
        }
        if (target instanceof HitRectangle) {
            int x1=this.x-this.u/2;
            int y1=this.y-this.v/2;
            int u1=this.x+this.u/2;
            int v1=this.y+this.v/2;
            int x2=target.x-((HitRectangle)target).u/2;
            int y2=target.y-((HitRectangle)target).v/2;
            int u2=target.x+((HitRectangle)target).u/2;
            int v2=target.y+((HitRectangle)target).v/2;
            if (x1<=u2&&u1>=x2&&y1<=v2&&v1>=y2) {
                return true;
            }
            else return false;
        }
        return false;
    }
    
}
