package GameObj.Monsters;

import GameObj.Explorer;

import java.awt.image.BufferedImage;

public class Scorpion extends Monsters{

    private BufferedImage leftImg, rightImg;
    private int rangeX1,rangeX2;

    public Scorpion(int x, int y, BufferedImage leftImg, BufferedImage rightImg, int rangeX1, int rangeX2){
        super(x,y,leftImg);
        this.leftImg = leftImg;
        this.rightImg = rightImg;
        facingdirc = LEFT;
        this.rangeX1 = rangeX1;
        this.rangeX2 =rangeX2;
    }


    @Override
    protected void patrol(Explorer e) {

        int eX = e.getX();

        //when the monster can see explorer
        if( ((e.getY()+e.getRect().getHeight()) > this.y) &&  ((e.getY() < (this.y+this.currentImg.getHeight())))){
            //in hunting area
            if (eX >= rangeX1 && eX <= rangeX2)   {
                if(eX < this.x && this.facingdirc == LEFT){
                    speed = fastSpeed;
                }else if (eX > this.x && this.facingdirc == RIGHT){
                    speed = fastSpeed;
                }
            }
        }else{
            speed = normalSpeed;
        }


    }

    @Override
    protected void move() {
        if(facingdirc == LEFT)      moveLeft(leftImg);
        else if(facingdirc == RIGHT)    moveRight(rightImg);
        else System.out.println("Invalid direction:"+ this.getClass());
    }



}
