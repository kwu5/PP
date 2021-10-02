package GameObj.Walls;

import GameObj.GameObj;

import java.awt.image.BufferedImage;

public class Block extends GameObj {

    private int type;


    //verify type using 0,1,2 -> block, vertBlock, horBlocks
    Block(int x, int y, BufferedImage img, int type ){
        super(x,y, img);
        this.type = type;
    }


    /**
     *
     final char UP = 'u';
     final char DOWN = 'd';
     final char LEFT = 'l';
     final char RIGHT = 'r';
     * @param diection
     * @param explorerSpeed
     */

    public void isPushed(char diection, int explorerSpeed){
        switch (diection){
            case 'u':
                pushUp(explorerSpeed);
                break;
            case 'd':
                pushDown(explorerSpeed);
                break;
            case 'l':
                pushLeft(explorerSpeed);
                break;
            case 'r':
                pushRight(explorerSpeed);
                break;
        }

    }


    public int getType(){
        return type;
    }

    public void pushLeft(int speed){
        this.x -= speed;
    }
    public void pushRight(int speed){
        this.x += speed;
    }
    public void pushUp(int speed){
        this.y += speed;
    }
    public void pushDown(int speed){
        this.y -= speed;
    }

    public void wallCollision(){
    }

    @Override
    public void collision(GameObj g) {
        if(g instanceof Wall   || g instanceof Block)   wallCollision();

    }

    @Override
    public void update() {

    }


}
