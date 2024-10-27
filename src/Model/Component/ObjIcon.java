
package Model.Component;

import Model.Methods.EntityCoor;
import Model.Methods.EntityPaint;


public abstract class ObjIcon implements EntityPaint,EntityCoor{
    protected int x,y;
    protected final int sizeX,sizeY;
    protected double Tan=0.0f;

    public ObjIcon(int sizeX, int sizeY) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
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

    @Override
    public void setTan(double tan) {
        this.Tan=tan;
    }

    @Override
    public void turn(double tan) {
        this.Tan+=tan;
    }
    
    
}
