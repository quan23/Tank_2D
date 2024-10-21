
package Model;

import Model.Component.ObjIcon;
import Model.Component.HitBox;
import Model.Component.StaticIcon;
import Model.Methods.EntityPaint;
import java.awt.Component;
import java.awt.Graphics;
import javax.swing.ImageIcon;

public final class Block extends Entity implements EntityPaint{
    ObjIcon Icon;
    HitBox hitBox;
    
    public Block(int x,int y,String link) {
        Icon = new StaticIcon(32,32,link);
        setCoor(x,y);
    }
    public Block(int x,int y,ImageIcon iIcon) {
        Icon=new StaticIcon(32, 32, iIcon);
        setCoor(x, y);
    }
    public void setIcon(ObjIcon Icon) {
        this.Icon = Icon;
    }
    public void move(int x,int y) {
        super.move(x, y);
        setCoor(this.x, this.y);
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
    public void paint(Component c, Graphics g) {
        Icon.paint(c, g);
    }
    
    

}
