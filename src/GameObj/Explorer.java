package GameObj;

import GameObj.PowerUpObj.PowerUpObj;
import GameObj.Walls.*;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Explorer extends GameObj{



    private int imgWidth, imgHeight;
    private BufferedImage exUpImg, exDownImg, exLeftImg, exRightImg;

    private Rectangle exRect;
    private int speed, normalSpeed;


    private boolean leftPressed, rightPressed, upPressed, downPressed;
    private boolean isMoveDown, isMoveLeft, isMoveRight , isMoveUp ;


    public Explorer( int x,int y, BufferedImage exUp, BufferedImage exDown, BufferedImage exLeft, BufferedImage exRight,
    int speed){
        super(x,y, exUp);

        this.speed =speed;
//        this.normalSpeed = speed;

        this.currentImg = exDownImg;
        this.exUpImg = exUp;
        this.exDownImg = exDown;
        this.exLeftImg = exLeft;
        this.exRightImg = exRight;

        this.imgHeight = exUpImg.getHeight();
        this.imgWidth = exUpImg.getWidth();

        this.exRect = new Rectangle(x,y,imgWidth,imgHeight);



    }


    public void toggleRightPressed() {
        this.rightPressed = true;
    }
    public void toggleLeftPressed() {
        this.leftPressed = true;
    }
    public void toggleUpPressed() {
        this.upPressed = true;
    }
    public void toggleDownPressed() {
        this.downPressed = true;
    }
    public void unToggleRightPressed() {
        this.rightPressed = false;
    }
    public void unToggleLeftPressed() {
        this.leftPressed = false;
    }
    public void unToggleUpPressed() {
        this.upPressed = false;
    }
    public void unToggleDownPressed() {
        this.downPressed = false;
    }

    private void moveUp(){
        y-=speed;
    }
    private void moveDown(){
        y+=speed;
    }
    private void moveLeft(){
        x-=speed;
    }
    private void moveRight(){
        x+=speed;
    }
    public void moveBack(){
//        System.out.println("moveback");
        if(isMoveDown)      y-=speed;
        else if(isMoveUp)     y+= speed;
        else if(isMoveLeft)     x+= speed;
        else if(isMoveRight)    x-=speed;
    }

    public int getSpeed(){return  speed;};
    public char getDirection(){
        if(isMoveDown)      return 'd';
        else if(isMoveUp)   return 'u';
        else if(isMoveLeft) return 'l';
        else     return 'r';
    }

    private void collisionBlock(Block block){
//        speed = block.getSpeed();
        if (block instanceof NormalBlock){
            moveBack();
        }else if(block instanceof HorBlock){
            if(getDirection() == 'u' || getDirection() == 'd' || block.isOutOfRange()){
                moveBack();

            }
        }else if(block instanceof VertBlock){
            if(getDirection() == 'l' || getDirection()=='r'||block.isOutOfRange()){
                moveBack();
            }
        }
    }

    /**
     * explorer collision
     * @param g
     */
    @Override
    public void collision(GameObj g) {
        if (g instanceof Wall || g instanceof Door) {
//            System.out.println("g is "+ g.getClass().getName());
            moveBack();
        }else if (g instanceof Block){
            collisionBlock((Block) g);


//            if(((Block) g).getHitwall())        moveBack();
//            else {
//
//
//                switch (((Block) g).getType()) {
//                    case 0:
//                        moveBack();
//                        break;
//                    case 1:
//                        if (isMoveLeft || isMoveRight) {
//                            moveBack();
//                        }
//                        break;
//                    case 2:
//                        if (isMoveUp || isMoveDown) {
//                            moveBack();
//                        }
//                        break;
//                    default:
//                }
//            }
        }
    }

    @Override
    public void update(){

        isMoveDown = false;
        isMoveUp = false;
        isMoveLeft = false;
        isMoveRight = false;

        if(leftPressed){
            this.moveLeft();
            isMoveLeft = true;
            this.currentImg = exLeftImg;
        }
        if(rightPressed){
            this.moveRight();
            isMoveRight = true;
            this.currentImg = exRightImg;
        }
        if(upPressed){
            this.moveUp();
            isMoveUp = true;
            this.currentImg= exUpImg;
        }
        if(downPressed){
            this.moveDown();
            isMoveDown = true;
            this.currentImg= exDownImg;
        }
        updateRect();
    }






}


