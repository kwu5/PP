package GameObj.Walls;

import GameObj.GameObj;
import GameObj.Explorer;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Block extends GameObj {

    //    private int type;
    private boolean outOfRange, isPushed, atStartPt;
    protected int startX, startY;
    protected float speed;
    //    protected Explorer explorer;
    protected Rectangle backToStartRect;


    //verify type using 0,1,2 -> block, vertBlock, horBlocks
    public Block(int x, int y, BufferedImage img) {
        super(x, y, img);
//        this.explorer = explorer;
        outOfRange = false;
        isPushed = false;
        atStartPt = true;
//        speed = explorer.getSpeed();
//        speed = explorer.getSpeed()/2;
        speed = 1f;
//        this.explorer = explorer;
        this.startX = x;
        this.startY = y;

        backToStartRect = new Rectangle(x - img.getWidth(), y - img.getHeight(), 2 * img.getWidth(), 2 * img.getHeight());

    }


//    public int getType(){
//        return type;
//    }

//    public boolean getHitwall(){
//        return hitWall;
//    }

    //    public boolean isOutOfRange(){return outOfRange;}
    public abstract boolean isOutOfRange();


//    public int getDirection(){
//        if(type ==0) return 0;
//        else if(type ==1)   return this.y - startY;
//        else return this.x - startX;
//    }


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

    /**
     * if out of range(hit wall), stop movement
     */
//    public void stopMovement(){
//
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

//    public void backToStartPt(){
//        int direction = getDirection();
//        //vertical
//        if(type == 1){
//            while(direction != 0 ){
//                if(direction <0 )           pushDown(speed);
//                else pushUp(speed);
//                direction = getDirection();
//            }
//        }
//        //horizontal
//        else {
//            while (direction !=0 ){
//                if(direction >0)        pushLeft(speed);
//                else pushRight(speed);
//                direction = getDirection();
//            }
//
//        }
//    }

//    public void move(){
//
//        System.out.println("move call");
//        int direction = explorer.getDirection();
////        System.out.println(direction);
//
//        if(outOfRange)     stopMovement();
//        else{
//            switch (direction){
//                case 'l':
//                    if(type == 2)           pushLeft(explorer.getSpeed());
//                    break;
//                case 'r':
//                    if(type == 2)           pushRight(explorer.getSpeed());
//                    break;
//                case 'u':
//                    if(type == 1)           pushDown(explorer.getSpeed());
//                    break;
//                case 'd':
//                    if(type == 1)           pushUp(explorer.getSpeed());
//                    break;
//                default:
//            }
//        }
//
//    }
    public abstract void backToStartPt(Explorer explorer);

    public abstract void move(Explorer explorer);
//    public abstract void stopMovement();


//    public int getSpeed(){return speed;}

    @Override
    public void collision(GameObj g) {
        if (g instanceof Explorer) {
            move((Explorer) g);
        }
//        System.out.println("collision call "+ g.getClass().getName());
//        if(g instanceof Wall || g instanceof Block){
//            hitWall = true;
//        }else


//        if (g instanceof Explorer){
//
//
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
