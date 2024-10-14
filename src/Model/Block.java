
package Model;

import Model.Component.ObjIcon;
import Model.Component.HitBox;
import java.awt.Component;
import java.awt.Graphics;
import java.util.function.Function;



public class Block extends Entity{
    ObjIcon Icon;
    HitBox hitBox;

    public Block() {
    }

    public void setIcon(ObjIcon Icon) {
        this.Icon = Icon;
    }

    public void setHitBox(HitBox hitBox) {
        this.hitBox = hitBox;
    }

    @Override
    public void setCoor(int x, int y) {
        super.setCoor(x, y);
        Icon.setCoor(x, y);
    }
    
    

    @Override
    public void render(Component c, Graphics g) {
        Icon.render(c, g);
    }
    
    

}
