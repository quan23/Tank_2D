package Model;

import Model.Methods.Weapon;
import javax.swing.Action;

public class MachineGun implements Weapon{
    private int coolDown = 20,DownTimer=coolDown;
    private int macSize=10, bulletAmount=macSize;
    private int reloadSpeed=120, reloadTime=reloadSpeed;
    private boolean reloaded=true;
    @Override
    public Bullet shoot(int x,int y,double Tan) {
        if (DownTimer<=0&&reloaded)
        {
            DownTimer=coolDown;
            bulletAmount--;
            if (bulletAmount==0) reloaded=false;
            return new Bullet(x+(int)(14*Math.sin(Math.toRadians(Tan))), y+(int)(-14*Math.cos(Math.toRadians(Tan))), Tan);
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
    
}
