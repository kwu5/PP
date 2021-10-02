package GameObj.Monsters;

import GameObj.Explorer;
import GameObj.PowerUpObj.PowerUpObj;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Mummy extends Monsters {

    private BufferedImage mummiesUp, mummiesDown, mummiesLeft, mummiesRight;


    public Mummy(int x, int y, BufferedImage mUp, BufferedImage mDown, BufferedImage mLeft, BufferedImage mRight ,
                 Explorer explorer ){
        super(x,y,mUp,explorer);
        this.mummiesUp = mUp;
        this.mummiesDown = mDown;
        this.mummiesLeft = mLeft;
        this.mummiesRight = mRight;
        facingdirc = RIGHT;


    }


    //todo implement after scarabs
    @Override
    protected void patrol() {


    }

    @Override
    protected void move() {
        switch (facingdirc){
            case UP :
                moveUp(mummiesUp);
                break;
            case DOWN:
                moveDown(mummiesDown);
                break;
            case LEFT:
                moveLeft(mummiesLeft);
                break;
            case RIGHT:
                moveRight(mummiesRight);
                break;
            default:
                System.out.println("Invalid direction:"+ this.getClass());
        }
    }

}
