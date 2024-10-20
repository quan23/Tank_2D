
package Model.Component;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

public class StaticIcon extends ObjIcon{
    ImageIcon Icon;
    
    

    public StaticIcon(int sizeX, int sizeY,String IconLink) {
        super(sizeX,sizeY);
        Icon=new ImageIcon(new ImageIcon(IconLink).getImage().getScaledInstance(sizeX, sizeY, Image.SCALE_DEFAULT));
    }
    

    @Override
    public void paint(Component c, Graphics g) {
        Icon.paintIcon(c, g, x, y);
    }

    
    
}
