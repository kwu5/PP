package GameObj.Walls;

import GameObj.GameObj;
import GameObj.Explorer;

import java.awt.image.BufferedImage;

public abstract class Block extends GameObj {

//    private int type;
    private boolean outOfRange, isPushed, atStartPt;
    protected int startX,startY, speed;
    protected Explorer explorer;



    //verify type using 0,1,2 -> block, vertBlock, horBlocks
    public Block(int x, int y, BufferedImage img, Explorer explorer ) {
        super(x, y, img);
        this.explorer = explorer;
        outOfRange = false;
        isPushed = false;
        atStartPt = true;
//        speed = explorer.getSpeed();
        speed = explorer.getSpeed()/2;
//        this.explorer = explorer;
        this.startX = x;
        this.startY = y;
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



    public void pushLeft(int speed){
        this.x -= speed;
    }
    public void pushRight(int speed){
        this.x += speed;
    }
    public void pushUp(int speed){
        this.y -= speed;
    }
    public void pushDown(int speed){
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

    public abstract void backToStartPt();
    public abstract void move();
//    public abstract void stopMovement();


//    public int getSpeed(){return speed;}

    @Override
    public void collision(GameObj g) {
        if (g instanceof Explorer) {
            move();
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
            updateRect();
            backToStartPt();
    }


}
