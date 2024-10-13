
package Model.Component;

public abstract class HitBox {
    protected int x,y;
    public abstract boolean checkCollision(HitBox target);
    protected int sqr(int x) {
        return x*x;
    }
}
