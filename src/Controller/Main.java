package Controller;

import View.Frame;
import View.Window;
import java.awt.Dimension;
import javax.swing.ImageIcon;


/*
 * @author hongq
 */
public class Main {

    public static void main(String[] args) {
        Window window = new Window("1");
        window.setIconImage(new ImageIcon("player1_tank_up.png").getImage());
        Frame render = new Frame(0,0,window.getHeight(),window.getHeight());
        window.add(render);
        window.setVisible(true);
    }
    
}
