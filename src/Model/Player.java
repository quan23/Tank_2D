
package Model;

import Model.Component.ObjIcon;
import Model.Component.HitBox;
import Model.Component.Health;
import Model.Component.HitCircle;
import Model.Component.HitRectangle;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import Model.Methods.EntityHitBox;
import Model.Methods.EntityPaint;

public class Player extends Entity implements EntityPaint,EntityHitBox{
    private Health health;
    private HitBox hitBox;
    private ObjIcon icon;
    private Weapon temporWeapon;
    private Weapon defaultWeapon;
    private double Tan,Speed=1.2;
    private double realX=x,realY=y;

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
    
    public void goForward(int dx,int dy) {
        realX+=dx*this.Speed*Math.sin(Math.toRadians(Tan));
        realY-=dy*this.Speed*Math.cos(Math.toRadians(Tan));
        super.setCoor((int)realX, (int)realY);
        icon.setCoor((int)realX, (int)realY);
        hitBox.setCoor((int)realX, (int)realY);
    }
   
    @Override
    public void paint(Component c, Graphics2D g) {
        icon.paint(c, g);
    }

    @Override
    public void setCoor(int x, int y) {
        realX=x;
        realY=y;
        super.setCoor(x, y);
        icon.setCoor(x, y);
        hitBox.setCoor(x, y);
    }
    @Override
    public void move(int x,int y) {
        super.move(x, y);
        this.setCoor(this.x, this.y);
    }

    @Override
    public boolean checkCollison(HitBox target) {
        if (this.hitBox==null) return false;
        if (target==null) return false;
        return this.hitBox.checkCollison(target);
    }

    @Override
    public void setTan(double tan) {
        this.Tan=tan;
        this.icon.setTan(tan);
    }

    @Override
    public void turn(double tan) {
        this.Tan+=tan;
        this.icon.setTan(this.Tan);
    }
    

}
