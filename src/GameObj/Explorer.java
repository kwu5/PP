package GameObj;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Explorer extends GameObj{



    private int imgWidth, imgHeight;
    private float speed;

    private BufferedImage exUpImg, exDownImg, exLeftImg, exRightImg;


    private Rectangle exRect;



    private boolean leftPressed, rightPressed, upPressed, downPressed;
    private boolean moveUp, moveDown, moveLeft, moveRight;

    public Explorer( int x,int y, BufferedImage exUp, BufferedImage exDown, BufferedImage exLeft, BufferedImage exRight,
    float speed){
        super(x,y, exUp);

        this.speed =speed;

        this.moveDown = false;
        this.moveUp = false;
        this.moveLeft = false;
        this.moveRight = false;

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

    @Override
    public void update(){

        moveDown = false;
        moveUp = false;
        moveRight = false;
        moveLeft = false;

        if(leftPressed){
            this.moveLeft();
            moveLeft = true;
            this.currentImg = exLeftImg;
        }
        if(rightPressed){
            this.moveRight();
            moveRight = true;
            this.currentImg = exRightImg;
        }
        if(upPressed){
            this.moveUp();
            moveUp = true;
            this.currentImg= exUpImg;
        }
        if(downPressed){
            this.moveDown();
            moveDown = true;
            this.currentImg= exDownImg;
        }

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

    public int getX(){return x;};
    public int getY(){return y;};

//    @Override
//    public void drawImage(Graphics g){
//        Graphics2D g2d= (Graphics2D) g;
////        System.out.println(x + "   "+y);
//        g2d.drawImage(currImg,x,y,null );
//    }



}


