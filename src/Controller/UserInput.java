package Controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashMap;

public class UserInput implements KeyListener {

    private boolean keyList[];

    public UserInput() {
        keyList = new boolean[255];
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        keyList[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keyList[e.getKeyCode()] = false;
    }

    public boolean getKeyStatus(int key) {
        return keyList[key];
    }

}
