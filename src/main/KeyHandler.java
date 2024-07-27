package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    static char Direction = 'D';

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) Direction = 'W';
        else if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) Direction = 'D';
        else if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) Direction = 'A';
        else if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) Direction = 'S';
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    public char getDirection() {
        return  Direction;
    }

}
