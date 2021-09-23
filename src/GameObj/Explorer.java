package GameObj;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Explorer extends GameObj{

    private int x,y;
    private int imgWidth, imgHeight;

    private BufferedImage exUpImg, exDownImg, exLeftImg, exRightImg;
    private BufferedImage exCurrentImg;

    private Rectangle exRect;



    private boolean leftPressed, rightPressed, upPressed, downPressed;
    private boolean moveUp, moveDown, moveLeft, moveRight;

    public Explorer(int x, int y, BufferedImage exUp, BufferedImage exDown, BufferedImage exLeft, BufferedImage exRight){
        this.x = x;
        this.y = y;

        this.moveDown = false;
        this.moveUp = false;
        this.moveLeft = false;
        this.moveRight = false;

        this.exCurrentImg = exDownImg;
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
            this.exCurrentImg = exLeftImg;
        }
        if(rightPressed){
            this.moveRight();
            moveRight = true;
            this.exCurrentImg = exRightImg;
        }
        if(upPressed){
            this.moveUp();
            moveUp = true;
            this.exCurrentImg = exUpImg;
        }
        if(downPressed){
            this.moveDown();
            moveDown = true;
            this.exCurrentImg = exDownImg;
        }

    }


    private void moveUp(){
        y-=2;
    }
    private void moveDown(){
        y+=2;
    }
    private void moveLeft(){
        x-=2;
    }
    private void moveRight(){
        x+=2;
    }

    public int getX(){return x;};
    public int getY(){return y;};


    public void drawImage(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(exCurrentImg,x,y, null);
    }


}


