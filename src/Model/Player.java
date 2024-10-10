
package Model;

import java.awt.event.KeyEvent;

public class Player extends Objects{
    private int up;
    private int down;
    private int right;
    private int left;

    public Player(int x, int y, String IconLink, int layer) {
        super(x, y, IconLink, layer);
    }

    public Player(int x, int y, String IconLink) {
        super(x, y, IconLink);
    }

    
}
