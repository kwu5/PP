package GameObj.Walls;

import GameObj.Explorer;

import java.awt.image.BufferedImage;

public class HorBlock extends Block{

    private int rangeX1, rangeX2;

    public HorBlock(int x, int y, BufferedImage img, Explorer explorer,int rangeX1, int rangeX2){
        super(x,y,img,explorer);
        this.rangeX1 = rangeX1;
        this.rangeX2 = rangeX2;

    }

//    public int getRangeX1(){return rangeX1;}
//    public int getRangeX2(){return rangeX2;}


    @Override
    public boolean isOutOfRange() {
        return (this.x - rangeX1 < 0 || rangeX2 - this.x < 0 );
    }

    @Override
    public void move() {

        //moveback
        if (this.x - rangeX1 < 0 ){
            pushRight(explorer.getSpeed());
        }else if(this.x - rangeX2 > 0){
            pushLeft(explorer.getSpeed());
        }


        //being pushed
        if(explorer.getDirection() == 'l' )
        {
            pushLeft(explorer.getSpeed());
        }
        else if(explorer.getDirection() == 'r')                                     pushRight(explorer.getSpeed());
    }


    public void backToStartPt() {
        if(!this.rect.intersects(explorer.getRect())){
            if(this.x != startX){
                if (this.x < startX)            pushRight(this.speed);
                else                            pushLeft(this.speed);
            }
        }

    }


//    public void stopMovement() {
//        char direction = explorer.getDirection();
//        int exSpeed = explorer.getSpeed();
//
//        if(direction == 'l')        pushRight(exSpeed);
//        else                        pushLeft(exSpeed);
//    }
}
