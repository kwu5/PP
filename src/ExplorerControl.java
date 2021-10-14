import GameObj.Explorer;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ExplorerControl implements KeyListener {


    private Explorer explorer;
    private final int up;
    private final int down;
    private final int right;
    private final int left;
    private final int space;

    public ExplorerControl(Explorer ex, int up, int down, int left, int right, int space) {
        this.explorer = ex;
        this.down = down;
        this.up = up;
        this.left = left;
        this.right = right;
        this.space = space;


    }


    @Override
    public void keyTyped(KeyEvent e) {
        char keyTyped = e.getKeyChar();
//        System.out.println(keyTyped);
        if (keyTyped == 'q') {
            this.explorer.setScarabsActive(true);
        } else if (keyTyped == 'e') this.explorer.setSwordActive(true);

//        System.out.println("Type: "+ keyTyped);


    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyPressed = e.getKeyCode();
        if (keyPressed == left) {
            this.explorer.toggleLeftPressed();
            this.explorer.unToggleRightPressed();
            this.explorer.unToggleUpPressed();
            this.explorer.unToggleDownPressed();

        }
        if (keyPressed == right) {
            this.explorer.toggleRightPressed();
            this.explorer.unToggleLeftPressed();
            this.explorer.unToggleUpPressed();
            this.explorer.unToggleDownPressed();
        }
        if (keyPressed == up) {
            this.explorer.toggleUpPressed();
            this.explorer.unToggleLeftPressed();
            this.explorer.unToggleDownPressed();
            this.explorer.unToggleRightPressed();
        }
        if (keyPressed == down) {
            this.explorer.toggleDownPressed();
            this.explorer.unToggleUpPressed();
            this.explorer.unToggleLeftPressed();
            this.explorer.unToggleRightPressed();

        }
        if (keyPressed == space) {
            this.explorer.toggleSpacePressed();
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyPressed = e.getKeyCode();
        if (keyPressed == left) {
            this.explorer.unToggleLeftPressed();
        }
        if (keyPressed == right) {
            this.explorer.unToggleRightPressed();
        }
        if (keyPressed == up) {
            this.explorer.unToggleUpPressed();
        }
        if (keyPressed == down) {
            this.explorer.unToggleDownPressed();
        }
        if (keyPressed == space) {
            this.explorer.unToggleSpacePressed();
        }

    }
}
