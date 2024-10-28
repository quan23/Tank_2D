package Model.Methods;

import Model.Component.HitBox;

public interface EntityHitBox {

    public boolean checkCollison(HitBox target);
    public HitBox getHitBox();
}
