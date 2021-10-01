package GameObj;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class GameObj {

    protected int x,y;
    protected BufferedImage currentImg;
    protected Rectangle rect;
    protected int speed;


    //since currentImg's weight & height are both 32, I use 35 as rectangle's width & height;
    protected GameObj(int x, int y, BufferedImage currentImg){
        this.x = x;
        this.y = y;
        this.currentImg = currentImg;
//        rect = new Rectangle(x, y, 35,35);
        rect = new Rectangle(x,y,currentImg.getWidth(),currentImg.getHeight());

    }


    public int getX(){return x;}
    public int getY(){return y;}



    public void setX(int x)  {this.x = x;}

    public void setY(int y) {this.y = y;}

    public int getSpeed(){return speed;}

    public Rectangle getRect(){
        return rect;
    }

    public void updateRect(){
        rect.setLocation(x,y);
    }

    public abstract void update();




    public void drawImage(Graphics g){
        Graphics2D g2d= (Graphics2D) g;
        g2d.drawImage(currentImg,x,y,null );
    }

//    public int getX(){return x;}
//    public int getY(){return y;}






}
