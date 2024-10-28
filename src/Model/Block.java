
package Model;

import Model.Component.ObjIcon;
import Model.Component.HitBox;
import Model.Component.StaticIcon;
import Model.Methods.EntityHitBox;
import java.awt.Component;
import java.awt.Graphics2D;
import javax.swing.ImageIcon;
import Model.Methods.EntityPaint;

public final class Block extends Entity implements EntityPaint,EntityHitBox{
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
    @Override
    public void move(int x,int y) {
        super.move(x, y);
        setCoor(this.x, this.y);
    }

    public void setHitBox(HitBox hitBox) {
        this.hitBox = hitBox;
 
        
    }

    @Override
    public HitBox getHitBox() {
        return hitBox;
    }

    public ObjIcon getIcon() {
        return Icon;
    }
    
    @Override
    public void setCoor(int x, int y) {
        super.setCoor(x, y);
        Icon.setCoor(x, y);
        if(hitBox!=null)hitBox.setCoor(x, y);
    }
    
    

    @Override
    public void paint(Component c, Graphics2D g) {
        Icon.paint(c, g);
    }

    @Override
    public void setTan(double tan) {
        this.Icon.setTan(tan);
    }

    @Override
    public void turn(double tan) {
        this.Icon.turn(tan);
    }

    @Override
    public boolean checkCollison(HitBox target) {
        if (this.hitBox==null) return false;
        if (target==null) return false;
        return this.hitBox.checkCollison(target);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    

}
