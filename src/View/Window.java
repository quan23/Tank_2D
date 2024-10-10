package View;

import java.awt.Dimension;
import javax.swing.*;

public class Window extends JFrame{
    private final int WIDTH = 640, HEIGHT = 480;
    private Render render;
    public Window(String title) {
        this.setTitle(title);
        this.setSize(WIDTH, HEIGHT);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
        Render render = new Render(new Dimension(WIDTH,HEIGHT));
        this.add(render);
        this.pack();
    }
    
}
