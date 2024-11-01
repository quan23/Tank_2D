/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import Model.Component.HitBox;
import Model.Component.HitCircle;
import Model.Component.HitRectangle;
import Model.Component.StaticIcon;
import Model.Methods.EntityCoor;
import Model.Methods.EntityHealth;
import Model.Methods.EntityHitBox;
import Model.Methods.EntityPaint;
import java.awt.Component;
import java.awt.Graphics2D;

/**
 *
 * @author hmqua
 */
public final class Bullet extends Entity implements EntityCoor,EntityHitBox,EntityPaint{
    private int timeToLive = 600;
    private double Tan,Speed=3;
    private double realX=x,realY=y;
    private final HitBox hitBox;
    private double dX=1,dY=1;
    StaticIcon icon;
    private int damage=10;
    public Bullet(int x,int y, double tan){
        hitBox = new HitRectangle(4, 4);
        //hitBox = new HitCircle(8);
        icon = new StaticIcon(8,8,"bullet.png");
        this.setCoor(x, y);
        this.setTan(tan);
    }
    public boolean countDown(){
        return timeToLive<=0;
    }
    public void goForward(double dx,double dy) {
        realX+=dx*dX*this.Speed*Math.sin(Math.toRadians(Tan));
        realY-=dy*dY*this.Speed*Math.cos(Math.toRadians(Tan));
        super.setCoor((int)realX, (int)realY);
        icon.setCoor((int)realX, (int)realY);
        hitBox.setCoor((int)realX, (int)realY);
        timeToLive--;
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
    }
    @Override
    public void move(int x,int y) {
        super.move(x, y);
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

    @Override
    public HitBox getHitBox() {
       return hitBox;
    }
    
    public boolean collide(double dx,double dy) {
        if (dx!=0) this.dX*=(-dx);
        if (dy!=0) this.dY*=(-dy);
        return false;
    }
    public void dealDamage(EntityHealth entity) {
        entity.Damage(damage);
        //System.out.println("damage");
    }

    public void setTimeToLive(int timeToLive) {
        this.timeToLive = timeToLive;
    }
    
}
