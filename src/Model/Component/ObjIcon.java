
package Model.Component;

import Model.Entity;
import java.awt.Component;
import java.awt.Graphics;

public abstract class ObjIcon extends Entity{
    protected final int sizeX,sizeY;

    public ObjIcon(int sizeX, int sizeY) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
    }
}
