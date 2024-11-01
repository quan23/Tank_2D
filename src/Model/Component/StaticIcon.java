package Model.Component;

import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import javax.swing.ImageIcon;

public class StaticIcon extends ObjIcon {

    ImageIcon Icon;

    public StaticIcon(int sizeX, int sizeY, String IconLink) {
        super(sizeX, sizeY);
        Icon = new ImageIcon(new ImageIcon(IconLink).getImage().getScaledInstance(sizeX, sizeY, Image.SCALE_DEFAULT));
    }

    public StaticIcon(int sizeX, int sizeY, ImageIcon iIcon) {
        super(sizeX, sizeY);
        Icon = new ImageIcon(iIcon.getImage());
    }

    @Override
    public void paint(Component c, Graphics2D g) {
        AffineTransform at = AffineTransform.getTranslateInstance(x-sizeX/2, y-sizeY/2);
        at.rotate(Math.toRadians(Tan), sizeX / 2, sizeY / 2);
        g.drawImage(Icon.getImage(), at, c);
    }

}
