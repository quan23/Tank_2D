package Model;

import java.awt.Component;
import java.awt.Graphics;

public abstract class Entity {
    protected int x=0,y=0;
    public void setCoor(int x,int y) {
        this.x=x;
        this.y=y;
    }
    public abstract void render(Component c,Graphics g);
}
