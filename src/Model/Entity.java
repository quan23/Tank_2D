package Model;

import java.awt.Component;
import java.awt.Graphics;

public abstract class Entity implements EntityMethods{
    int x,y;

    public Entity() {
        this.y = 0;
        this.x = 0;
    }
    @Override
    public void setCoor(int x,int y) {
        this.x=x;
        this.y=y;
    }
    
}
