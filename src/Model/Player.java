
package Model;

import Model.Component.ObjIcon;
import Model.Component.HitBox;
import Model.Component.Health;
import java.awt.Component;
import java.awt.Graphics;

public class Player extends Entity{
    Health health;
    HitBox hitBox;
    ObjIcon icon;
    Weapon temporWeapon;
    Weapon defaultWeapon;

    public Player() {
    }

    public void setHealth(Health health) {
        this.health = health;
    }

    public void setHitBox(HitBox hitBox) {
        this.hitBox = hitBox;
    }

    public void setIcon(ObjIcon icon) {
        this.icon = icon;
    }

    public Health getHealth() {
        return health;
    }

    public HitBox getHitBox() {
        return hitBox;
    }

    @Override
    public void paint(Component c, Graphics g) {
        icon.paint(c, g);
    }

    @Override
    public void setCoor(int x, int y) {
        super.setCoor(x, y);
        icon.setCoor(x, y);
        hitBox.setCoor(x, y);
    }
    public void move(int x,int y) {
        this.x+=x;
        this.y+=y;
        setCoor(this.x, this.y);
    }
    

    
}
