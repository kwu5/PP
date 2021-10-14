package GameObj.Walls;

import GameObj.Explorer;

import java.awt.*;
import java.awt.image.BufferedImage;

public class HorBlock extends Block {

    private int rangeX1, rangeX2;

    public HorBlock(int x, int y, BufferedImage img, int rangeX1, int rangeX2) {
        super(x, y, img);
        this.rangeX1 = rangeX1;
        this.rangeX2 = rangeX2;

    }


    @Override
    public boolean isOutOfRange() {
        return (this.x - rangeX1 < 0 || rangeX2 - this.x < 0);
    }

    @Override
    public void move(Explorer explorer) {

//        System.out.println("moving");

        //moveback
        if (this.x - rangeX1 < 0) {
            pushRight(explorer.getSpeed() + 1);

        } else if (this.x - rangeX2 > 0) {
            pushLeft(explorer.getSpeed() + 1);

        }


        //being pushed
        if (explorer.getDirection() == 'l') {
            pushLeft(explorer.getSpeed() + 1);
        } else if (explorer.getDirection() == 'r') pushRight(explorer.getSpeed() + 1);
    }


    public void backToStartPt(Explorer explorer) {


        if (!backToStartRect.intersects(explorer.getRect())) {

            if (this.x != startX) {
                if (this.x < startX) {
                    System.out.println("btsR");

                    pushRight(speed);
                } else {
                    System.out.println("btsL");

                    pushLeft(speed);
                }
            }
        }

    }

}


//    public void stopMovement() {
//        char direction = explorer.getDirection();
//        int exSpeed = explorer.getSpeed();
//
//        if(direction == 'l')        pushRight(exSpeed);
//        else                        pushLeft(exSpeed);
//    }

