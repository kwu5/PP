package GameObj.Walls;

import GameObj.GameObj;

import java.awt.image.BufferedImage;

public abstract class Blocks extends GameObj {


    Blocks(int x, int y, BufferedImage img){
        super(x,y, img);
    }

    public void pushLeft(){

    }
    public void pushRight(){

    }

    public void pushUp(){

    }

    public void pushDown(){

    }



}
