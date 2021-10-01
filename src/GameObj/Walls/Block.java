package GameObj.Walls;

import GameObj.GameObj;

import java.awt.image.BufferedImage;

public class Block extends GameObj {

    private final int type;


    //verify type using 0,1,2 -> block, vertBlock, horBlocks
    Block(int x, int y, BufferedImage img, int type ){
        super(x,y, img);
        this.type = type;
    }



    public void isPushed(char diection){
        if(type == 0){

        }
    }


    public int getType(){
        return type;
    }











    public void pushLeft(){

    }
    public void pushRight(){

    }

    public void pushUp(){

    }

    public void pushDown(){

    }

    public void wallCollision(){

    }

    @Override
    public void update() {

    }


}
