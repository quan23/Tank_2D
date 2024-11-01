package Model.Component;

import Model.Methods.EntityHealth;

public class Health implements EntityHealth{

    private int health;
    private final int mHealth;

    public Health(int health, int mHealth) {
        this.health = health;
        this.mHealth = mHealth;
    }

    public void Damage(int damage) {
        this.health = Integer.max(this.health - damage, 0);

    }

    public boolean Death() {
        return this.health <= 0;
    }

    public void Heal(int heal) {
        this.health = Integer.min(this.health + heal, this.mHealth);
    }
}
