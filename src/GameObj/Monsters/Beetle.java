package GameObj.Monsters;

import GameObj.Explorer;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Beetle extends Monsters {

    private BufferedImage upImg, downImg;
    private int rangeY1, rangeY2;


    public Beetle(int x, int y, BufferedImage upImg, BufferedImage downImg, int rangeY1, int rangeY2) {
        super(x, y, upImg);
        this.upImg = upImg;
        this.downImg = downImg;
        this.facingdirc = UP;
        this.rangeY1 = rangeY1;
        this.rangeY2 = rangeY2;

    }


    @Override
    public boolean patrol(Explorer e) {

        int eY = e.getY();

        //when the monster can see the character
        if (((e.getX() + e.getRect().getWidth()) > this.x) && ((e.getX() < (this.x + this.currentImg.getWidth())))) {
            //in hunting area
            if (eY >= rangeY1 && e.getY() <= rangeY2) {           //in range and facing monster
                if (eY < this.y && this.facingdirc == UP) {
                    speed = fastSpeed;
                    return true;
                } else if (eY > this.y && this.facingdirc == DOWN) {
                    speed = fastSpeed;
                    return true;
                }
            }
        } else {
            speed = normalSpeed;
            return false;
        }
        return false;


    }


    @Override
    protected void move() {
        if (facingdirc == UP) moveUp(upImg);
        else if (facingdirc == DOWN) moveDown(downImg);
        else System.out.println("invalid direction : " + this.getClass());
    }


}
