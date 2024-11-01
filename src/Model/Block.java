
package Model;

import Model.Component.StaticIcon;
import Model.Methods.EntityHitBox;
import javax.swing.ImageIcon;
import Model.Methods.EntityPaint;

public final class Block extends Entity implements EntityPaint,EntityHitBox{
    
    public Block(int x,int y,String link) {
        Icon = new StaticIcon(32,32,link);
        setCoor(x,y);
    }
    public Block(int x,int y,ImageIcon iIcon) {
        Icon=new StaticIcon(32, 32, iIcon);
        setCoor(x, y);
    }
    

}
