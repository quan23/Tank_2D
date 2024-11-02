package Model;

import Model.Component.Health;
import Model.Methods.EntityHealth;

public class Player extends Entity implements EntityHealth{

    private Health health;
    private final MachineGun defaultWeapon;
    private double Tan, Speed = 1.2;
    private double realX = x, realY = y;

    public Player() {
        defaultWeapon=new MachineGun();
    }

    public Bullet shoot() {
        return defaultWeapon.shoot(x, y, Tan);

    }
    
    public void update() {
        defaultWeapon.coolDown(1);
    }

    public void setHealth(Health health) {
        this.health = health;
    }

    public Health getHealth() {
        return health;
    }

    public void goForward(int dx, int dy) {
        realX += dx * this.Speed * Math.sin(Math.toRadians(Tan));
        realY -= dy * this.Speed * Math.cos(Math.toRadians(Tan));
        super.setCoor((int) realX, (int) realY);
    }


    @Override
    public void setCoor(int x, int y) {
        realX = x;
        realY = y;
        super.setCoor(x, y);
    }


    @Override
    public void setTan(double tan) {
        this.Tan = tan;
        super.setTan(tan);
    }

    @Override
    public void turn(double tan) {
        this.Tan += tan;
        super.setTan(Tan);
    }

    @Override
    public void Damage(int damage) {
        health.Damage(damage);
    }

    @Override
    public boolean Death() {
        return health.Death();
    }

    @Override
    public void Heal(int heal) {
        health.Heal(heal);
    }
    
    
}
