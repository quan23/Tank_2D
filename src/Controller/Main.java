package Controller;

import View.Frame;
import View.Window;
import java.awt.Dimension;
import javax.swing.ImageIcon;


/*
 * @author hongq
 * @author minhq
 * @author nhatq
 */
public class Main {

    public static void main(String[] args) {
        Window window = new Window("1");
        window.setIconImage(new ImageIcon("player1_tank_up.png").getImage());
        Frame render = new Frame(new Dimension(window.getBounds().width,window.getBounds().height));
        window.add(render);
        window.setVisible(true);
    }
    
}
