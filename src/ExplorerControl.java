import GameObj.Explorer;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ExplorerControl implements KeyListener {


    private Explorer explorer;
    private final int up;
    private final int down;
    private final int right;
    private final int left;

    public ExplorerControl(Explorer ex,int up,int down,int left,int right){
        this.explorer = ex;
        this.down= down;
        this.up = up;
        this.left= left;
        this.right = right;

    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyPressed = e.getKeyCode();
        if (keyPressed == left) {
            this.explorer.toggleLeftPressed();
        }
        if (keyPressed == right) {
            this.explorer.toggleRightPressed();
        }
        if (keyPressed == up) {
            this.explorer.toggleUpPressed();
        }
        if (keyPressed == down) {
            this.explorer.toggleDownPressed();
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

    }
}
