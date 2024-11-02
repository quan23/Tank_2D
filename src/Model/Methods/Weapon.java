
package Model.Methods;

import Model.Bullet;

public interface Weapon extends EntityCoor{
    public Bullet shoot();
    public void reload();
    public void coolDown(int tick);
    public void turn(double tan);
    public void setTan(double tan);

}
