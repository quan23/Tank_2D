
package Model;



import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
//chua iMageIcon
//
public class TextureManager {
    private ImageIcon iconList[];
    BufferedImage bf;
    public TextureManager() {
        iconList = new ImageIcon[5];
        getTileImage();
    }
    public void getTileImage()
    {
       iconList[0] = new ImageIcon(new ImageIcon("Minecraft-bricks.jpg").getImage().getScaledInstance(32, 32, Image.SCALE_DEFAULT));
       iconList[1] = new ImageIcon(new ImageIcon("pathway.jpg").getImage().getScaledInstance(32, 32, Image.SCALE_DEFAULT));
    }
    public ImageIcon getImage(int id){
        if(id==0) return iconList[0];
        else return iconList[1];
    }
}
