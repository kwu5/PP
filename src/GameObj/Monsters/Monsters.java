package GameObj.Monsters;

import GameObj.GameObj;
import GameObj.Explorer;


import java.awt.*;
import java.awt.image.BufferedImage;



public abstract class Monsters extends GameObj {


    final int normalSpeed = 1;//set up monster speed  //todo
    final int fastSpeed = 2;

    final char UP = 'u';
    final char DOWN = 'd';
    final char LEFT = 'l';
    final char RIGHT = 'r';

    public char facingdirc;
    Explorer e ;

    protected Monsters(int x,int y, BufferedImage img, Explorer e){
        super(x,y,img);
        this.e = e;
        this.speed = normalSpeed;

    }

//    public abstract BufferedImage getCurrentImg();

    /**
     * check if explorer is in sight and react to it
     */
    protected abstract void patrol( );

    /**
     * handle moving action
     */
    protected abstract void move();
//    protected void move(BufferedImage img, char fd){
//        switch (fd){
//            case 'u':
//                moveUp(img);
//                break;
//            case 'd':
//                moveDown(img);
//                break;
//            case 'l':
//                moveLeft(img);
//                break;
//            case 'r':
//                moveRight(img);
//                break;
//            default:
//                System.out.println("move error, check Monsters.java");
//
//        }
//    }
//

    protected void moveUp(BufferedImage upImg){
        this.currentImg= upImg;
        facingdirc = UP;
        y-=speed;}

    protected void moveDown(BufferedImage downImg){
        this.currentImg= downImg;
        facingdirc = DOWN;
        y+=speed;}

    protected void moveLeft(BufferedImage leftImg){
        this.currentImg= leftImg;
        facingdirc = LEFT;
        x-=speed;}

    protected void moveRight(BufferedImage rightImg){
        this.currentImg= rightImg;
        facingdirc = RIGHT;
        x+= speed;}

    public void moveOpposite(){
        switch (facingdirc){
            case UP:
                facingdirc = DOWN;
                break;
            case DOWN:
                facingdirc = UP;
                break;
            case LEFT:
                facingdirc = RIGHT;
                break;
            case RIGHT:
                facingdirc = LEFT;
                break;
            default:
                System.out.println("MoveOpposite: invalid direction");
                break;
        }
    }

    @Override
    public void update() {
        patrol();
        move();
        updateRect();
    }
}
