
package Model.Component;

import java.awt.Component;
import java.awt.Graphics;

public abstract class ObjIcon {
    protected int x=0,y=0;
    protected final int sizeX,sizeY;

    public ObjIcon(int sizeX, int sizeY) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
    }
    
    public abstract void draw(Component c,Graphics g);
    public void move(int x,int y) {
        this.x=x;
        this.y=y;
    }
}
