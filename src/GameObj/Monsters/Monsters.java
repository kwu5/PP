package GameObj.Monsters;

import GameObj.GameObj;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Monsters extends GameObj {

    final float speed = 1.0f;    //set up monster speed

    Monsters(int x,int y){
        super(x,y);

    }

//    public abstract BufferedImage getCurrentImg();


//    Monsters(int x,int y){
//        super(x,y);
//    }



    protected void moveUp(BufferedImage currImg){
        y-=speed;}

    protected void moveDown(BufferedImage currImg){y+=speed;}

    protected void moveLeft(BufferedImage currImg){x-=speed;}

    protected void moveRight(BufferedImage currImg){x+= speed;}



    public abstract BufferedImage getCurrImg();



}
