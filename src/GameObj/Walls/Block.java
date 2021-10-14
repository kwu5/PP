package GameObj.Walls;

import GameObj.GameObj;
import GameObj.Explorer;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Block extends GameObj {

    private boolean outOfRange, isPushed, atStartPt;
    protected int startX, startY;
    protected int speed;
    protected Rectangle backToStartRect;


    public Block(int x, int y, BufferedImage img) {
        super(x, y, img);
        outOfRange = false;
        isPushed = false;
        atStartPt = true;
        speed = 1;
        this.startX = x;
        this.startY = y;

        backToStartRect = new Rectangle(x - img.getWidth(), y - img.getHeight(), 2 * img.getWidth(), 2 * img.getHeight());

    }


    /**
     * Set moving range to avoid  Explorer vs Block vs Wall  collision
     *
     * @return
     */
    public abstract boolean isOutOfRange();


    public void pushLeft(float speed) {
        this.x -= speed;
    }

    public void pushRight(float speed) {
        this.x += speed;
    }

    public void pushUp(float speed) {
        this.y -= speed;
    }

    public void pushDown(float speed) {
        this.y += speed;
    }


//    public void stopMovement(){
//        char direction = explorer.getDirection();
//        int exSpeed = explorer.getSpeed();
//        switch (direction){
//            case 'l':
//                pushRight(exSpeed);
//                break;
//            case 'r':
//                pushLeft(exSpeed);
//                break;
//            case 'u':
//                pushDown(exSpeed);
//                break;
//            case 'd':
//                pushUp(exSpeed);
//                break;
//            default:
//        }
//
//
//    }


    /**
     * When not being push, moving back to startPoint
     *
     * @param explorer
     */
    public abstract void backToStartPt(Explorer explorer);

    /**
     * Movement when being pushed
     *
     * @param explorer
     */
    public abstract void move(Explorer explorer);


    @Override
    public void collision(GameObj g) {
        if (g instanceof Explorer) {
            move((Explorer) g);
        }
//        System.out.println("collision call "+ g.getClass().getName());
//        if(g instanceof Wall || g instanceof Block){
//            hitWall = true;
//        }
    }

    @Override
    public void update() {

    }

    @Override
    public void update(Explorer explorer) {
        updateRect();
        backToStartPt(explorer);
    }

    @Override
    public void updateRect() {
        super.updateRect();
        backToStartRect.setLocation(x - currentImg.getWidth() / 2, y - currentImg.getHeight() / 2);

    }
}
