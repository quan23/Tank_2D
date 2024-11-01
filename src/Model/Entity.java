package Model;

import Model.Component.HitBox;
import Model.Component.ObjIcon;
import Model.Methods.EntityCoor;
import Model.Methods.EntityHitBox;
import Model.Methods.EntityPaint;
import java.awt.Component;
import java.awt.Graphics2D;

public abstract class Entity implements EntityCoor, EntityPaint, EntityHitBox {

    protected int x, y;
    protected HitBox hitBox;
    protected ObjIcon Icon;

    @Override
    public void move(int x, int y) {
        this.x += x;
        this.y += y;
        this.setCoor(x, y);
    }

    @Override
    public void setCoor(int x, int y) {
        this.x = x;
        this.y = y;
        if (Icon != null) {
            Icon.setCoor(x, y);
        }
        if (hitBox != null) {
            hitBox.setCoor(x, y);
        }
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public boolean checkCollison(HitBox target) {
        if (this.hitBox == null) {
            return false;
        }
        if (target == null) {
            return false;
        }
        return this.hitBox.checkCollison(target);
    }

    public void setIcon(ObjIcon Icon) {
        this.Icon = Icon;
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
    public void paint(Component c, Graphics2D g) {
        if (Icon != null) {
            Icon.paint(c, g);
        }
    }

    @Override
    public void setTan(double tan) {
        if (Icon != null) {
            this.Icon.setTan(tan);
        }
    }

    @Override
    public void turn(double tan) {
        if (Icon != null) {
            this.Icon.turn(tan);
        }
    }

}
