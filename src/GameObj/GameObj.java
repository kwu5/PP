package GameObj;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class GameObj {

    protected int x,y;
    protected BufferedImage currentImg;


    protected GameObj(int x, int y, BufferedImage currentImg){
        this.x = x;
        this.y = y;
        this.currentImg = currentImg;
    }

     public abstract void update();
//     void drawImage(Graphics g, BufferedImage currImg);



    public void drawImage(Graphics g, BufferedImage img, int x, int y) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(img,x,y, null);
    }

    public void drawImage(Graphics g){
        Graphics2D g2d= (Graphics2D) g;
        System.out.println(x+"  "+y);
        g2d.drawImage(currentImg,x,y,null );
    }

//    public int getX(){return x;}
//    public int getY(){return y;}






}
