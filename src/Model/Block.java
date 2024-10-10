
package Model;

import java.util.function.Function;



public class Block extends Objects{

    public Block(int x, int y, String IconLink, int layer) {
        super(x, y, IconLink, layer);
    }

    
    @Override
    public boolean damageObj(Objects obj) {
        return true;
    }
}
