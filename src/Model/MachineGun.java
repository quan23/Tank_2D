package Model;

import Model.Methods.EntityCoor;
import Model.Methods.Weapon;
import javax.swing.Action;

public class MachineGun implements Weapon{
    private int x,y;
    private double Tan;
    private int coolDown = 20,DownTimer=coolDown;
    private int macSize=10, bulletAmount=macSize;
    private int reloadSpeed=120, reloadTime=reloadSpeed;
    private boolean reloaded=true;
    @Override
    public Bullet shoot() {
        if (DownTimer<=0&&reloaded)
        {
            DownTimer=coolDown;
            bulletAmount--;
            if (bulletAmount==0) reloaded=false;
            return new Bullet(x+(int)(15*Math.sin(Math.toRadians(Tan))), y+(int)(-15*Math.cos(Math.toRadians(Tan))), Tan);
        }
        return null;
    }
    @Override
    public void reload() {
        reloadTime--;
        if (reloadTime==0) {
            reloaded=true;
            bulletAmount=macSize;
            reloadTime=reloadSpeed;
        }
    }

    @Override
    public void coolDown(int tick) {
        DownTimer-=tick;
        if (!reloaded) reload();
    }

    @Override
    public void setCoor(int x, int y) {
        this.x=x;
        this.y=y;
    }

    @Override
    public void move(int x, int y) {
        this.x+=x;
        this.y+=y;
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
    public void turn(double tan) {
        this.Tan+=tan;
    }

    @Override
    public void setTan(double tan) {
        this.Tan=tan;
    }
    
}
