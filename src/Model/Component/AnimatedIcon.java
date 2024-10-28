package Model.Component;

import java.awt.Component;
import java.awt.Graphics2D;
import java.util.ArrayList;
import javax.swing.ImageIcon;

public class AnimatedIcon extends ObjIcon {

    protected ArrayList<ImageIcon> animation;
    protected final int frameNumber;
    protected int onFrame = 0;

    public AnimatedIcon(int sizeX, int sizeY, String animationLink, int frameNumber) {
        super(sizeX, sizeY);
        this.frameNumber = frameNumber;
    }

    @Override
    public void paint(Component c, Graphics2D g) {
        animation.get(onFrame++).paintIcon(c, g, x, y);
        onFrame %= frameNumber;
    }

}
