package GameObj;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Explorer extends GameObj{

    private int x,y;
    private int imgWidth, imgHeight;

    private BufferedImage exImg;

    private Rectangle exRect;



    private boolean leftPressed, rightPressed, upPressed, downPressed;
    private boolean moveUp, moveDown, moveLeft, moveRight;

    public Explorer(int x, int y, BufferedImage ex){
        this.x = x;
        this.y = y;

        this.moveDown = false;
        this.moveUp = false;
        this.moveLeft = false;
        this.moveRight = false;

        this.exImg = ex;
        this.imgHeight = ex.getHeight();
        this.imgWidth = ex.getWidth();

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
        }
        if(rightPressed){
            this.moveRight();
            moveRight = true;
        }
        if(upPressed){
            this.moveUp();
            moveUp = true;
        }
        if(downPressed){
            this.moveDown();
            moveDown = true;
        }

    }


    private void moveUp(){
        y+=2.5;
    }

    private void moveDown(){
        y+=2.5;
    }
    private void moveLeft(){
        x-=2.5;
    }
    private void moveRight(){
        x+=2.5;
    }


    void drawImage(Graphics g) {
        AffineTransform rotation = AffineTransform.getTranslateInstance(x, y);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(this.exImg, rotation, null);
    }


}


