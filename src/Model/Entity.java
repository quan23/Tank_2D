package Model;

import Model.Methods.EntityCoor;
import Model.Methods.EntityPaint;

public abstract class Entity implements EntityCoor{
    protected int x,y;

    @Override
    public void move(int x, int y) {
        this.x+=x;
        this.y+=y;
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
    
}
