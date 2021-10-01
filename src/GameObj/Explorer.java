package GameObj;

import GameObj.PowerUpObj.PowerUpObj;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Explorer extends GameObj{



    private int imgWidth, imgHeight;


    private BufferedImage exUpImg, exDownImg, exLeftImg, exRightImg;


    private Rectangle exRect;



    private boolean leftPressed, rightPressed, upPressed, downPressed;
    private boolean isMoveDown, isMoveLeft, isMoveRight , isMoveUp ;
    private boolean swordGained;

    public Explorer( int x,int y, BufferedImage exUp, BufferedImage exDown, BufferedImage exLeft, BufferedImage exRight,
    int speed){
        super(x,y, exUp);

        this.speed =speed;

        this.currentImg = exDownImg;
        this.exUpImg = exUp;
        this.exDownImg = exDown;
        this.exLeftImg = exLeft;
        this.exRightImg = exRight;

        this.imgHeight = exUpImg.getHeight();
        this.imgWidth = exUpImg.getWidth();

        this.exRect = new Rectangle(x,y,imgWidth,imgHeight);

        this.swordGained =false;

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
//        System.out.println(rect.x + "     "+ rect.y);

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

    public void gainSword(){swordGained = true;}
    public boolean isSwordGained(){return swordGained;}





//    @Override
//    public void drawImage(Graphics g){
//        Graphics2D g2d= (Graphics2D) g;
////        System.out.println(x + "   "+y);
//        g2d.drawImage(currImg,x,y,null );
//    }



}


