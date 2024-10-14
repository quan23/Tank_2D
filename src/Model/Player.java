
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
    public void render(Component c, Graphics g) {
        icon.render(c, g);
    }

    
}
