package Model.Component;

public class HitRectangle extends HitBox{
    protected int u,v;

    public HitRectangle(int u, int v) {
        this.u = u;
        this.v = v;
    }
    @Override
    public boolean checkCollison(HitCircle target) {
        int x1=this.x-this.u/2;
        int y1=this.y-this.v/2;
        int u1=this.x+this.u/2;
        int v1=this.y+this.v/2;
        int x2=Integer.max(Integer.min(target.x,u1),x1);
        int y2=Integer.max(Integer.min(target.y,v1),y1);
        if (x2==target.x&&y2==target.y) return true;
        return sqr(target.x-x2)+sqr(target.y-y2)<=sqr(target.radius);        
    }

    @Override
    public boolean checkCollison(HitRectangle target) {
        int x1=this.x-this.u/2;
        int y1=this.y-this.v/2;
        int u1=this.x+this.u/2;
        int v1=this.y+this.v/2;
        int x2=target.x-target.u/2;
        int y2=target.y-target.v/2;
        int u2=target.x+target.u/2;
        int v2=target.y+target.v/2;
        return x1<=u2&&u1>=x2&&y1<=v2&&v1>=y2;
    }
      @Override
    public boolean checkCollison(HitBox target) {
        if (target==null) return false;
        else if (target instanceof HitCircle T) return this.checkCollison(T);
        else if (target instanceof HitRectangle T) return this.checkCollison(T);
        return false;
    }
}
