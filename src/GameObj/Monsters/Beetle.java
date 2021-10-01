package GameObj.Monsters;

import GameObj.Explorer;

import java.awt.image.BufferedImage;

public class Beetle extends Monsters {

    private BufferedImage upImg, downImg;


    public Beetle(int x, int y, BufferedImage upImg, BufferedImage downImg,Explorer explorer ){
        super(x,y, upImg, explorer);
        this.upImg = upImg;
        this.downImg = downImg;
        this.facingdirc = UP;
    }


    @Override
    protected void patrol() {
        if(e.getX() == x) {
            if(((facingdirc == 'u') && (e.getY() < this.y)) || ((facingdirc == 'd') && (e.getY() > this.y))){
                speed = fastSpeed;
//                System.out.println("beetle speed change");
            }else{
                speed = normalSpeed;
//                System.out.println("beetle normal");
            }
        } else
        {
            speed = normalSpeed;
//            System.out.println("beetle normal");

        }
    }


    @Override
    protected void move() {
        if(facingdirc == UP)        moveUp(upImg);
        else if(facingdirc == DOWN)         moveDown(downImg);
        else System.out.println("invalid direction : "+ this.getClass());
    }


}
