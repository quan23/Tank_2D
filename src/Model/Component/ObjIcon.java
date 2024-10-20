
package Model.Component;

import Model.Entity;
import Model.EntityMethods;
import java.awt.Component;
import java.awt.Graphics;

public abstract class ObjIcon implements EntityMethods{
    protected int x,y;
    protected final int sizeX,sizeY;

    public ObjIcon(int sizeX, int sizeY) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
    }

    @Override
    public void setCoor(int x, int y) {
        this.x=x;
        this.y=y;
    }
    
}
