package GameObj.Monsters;

import GameObj.GameObj;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Monsters extends GameObj {

    final float normalSpeed = 1.0f;//set up monster speed
    final float fastSpeed = 2.0f;
    public float speed = normalSpeed;

    protected Monsters(int x,int y, BufferedImage img){
        super(x,y,img);
    }

//    public abstract BufferedImage getCurrentImg();



    protected void moveUp(BufferedImage upImg){
        this.currentImg= upImg;
        y-=speed;}

    protected void moveDown(BufferedImage downImg){
        this.currentImg= downImg;
        y+=speed;}

    protected void moveLeft(BufferedImage leftImg){
        this.currentImg= leftImg;
        x-=speed;}

    protected void moveRight(BufferedImage rightImg){
        this.currentImg= rightImg;
        x+= speed;}

    public void ExplorerInSight(boolean inSight){
        if (inSight) speed = fastSpeed;
        else speed = normalSpeed;
    }





}
