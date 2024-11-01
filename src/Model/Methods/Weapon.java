
package Model.Methods;

import Model.Bullet;

public interface Weapon {
    public Bullet shoot(int x,int y,double Tan);
    public void reload();
    public void coolDown(int tick);
}
