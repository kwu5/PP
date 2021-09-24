package GameObj;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class GameObj {

    protected int x,y;


    public GameObj(int x, int y){
        this.x = x;
        this.y = y;
    }






    public abstract void update();

    public void drawImage(Graphics g, BufferedImage img, int x, int y) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(img,x,y, null);
    }

    public void drawImage(Graphics g, BufferedImage currImg){
        Graphics2D g2d= (Graphics2D) g;
        System.out.println(x+"  "+y);
        g2d.drawImage(currImg,x,y,null );
    }






}
