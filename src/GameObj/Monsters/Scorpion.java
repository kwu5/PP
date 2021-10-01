package GameObj.Monsters;

import GameObj.Explorer;

import java.awt.image.BufferedImage;

public class Scorpion extends Monsters{

    private BufferedImage leftImg, rightImg;

    public Scorpion(int x, int y, BufferedImage leftImg, BufferedImage rightImg, Explorer explorer){
        super(x,y,leftImg,explorer);
        this.leftImg = leftImg;
        this.rightImg = rightImg;
        facingdirc = LEFT;
    }


    @Override
    protected void patrol() {
        if(e.getY() == y) {
            if(((facingdirc == 'l') && (e.getX() < this.x)) || ((facingdirc == 'r') && (e.getX() > this.x))){
                speed = fastSpeed;
//                System.out.println("scorpion speed change");
            }else{
                speed = normalSpeed;
//                System.out.println("scorpion normal");
            }
        } else
        {
            speed = normalSpeed;
//            System.out.println("scorpion normal");

        }
    }

    @Override
    protected void move() {
        if(facingdirc == LEFT)      moveLeft(leftImg);
        else if(facingdirc == RIGHT)    moveRight(rightImg);
        else System.out.println("Invalid direction:"+ this.getClass());
    }



}
