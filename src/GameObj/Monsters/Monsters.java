package GameObj.Monsters;

import GameObj.Door;
import GameObj.GameObj;
import GameObj.Explorer;
import GameObj.Walls.Block;
import GameObj.Walls.Wall;


import java.awt.*;
import java.awt.image.BufferedImage;



public abstract class Monsters extends GameObj {


    final int normalSpeed = 1;//set up monster speed  //todo
    final int fastSpeed = 2;
    public int speed;

    final char UP = 'u';
    final char DOWN = 'd';
    final char LEFT = 'l';
    final char RIGHT = 'r';

    public char facingdirc;
    Explorer e ;

    protected Monsters(int x,int y, BufferedImage img, Explorer e ){
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
        switch (this.facingdirc) {
            case UP -> facingdirc = DOWN;
            case DOWN -> facingdirc = UP;
            case LEFT -> facingdirc = RIGHT;
            case RIGHT -> facingdirc = LEFT;
            default -> System.out.println("MoveOpposite: invalid direction");
        }
    }

    @Override
    public void collision(GameObj g) {
        if(g instanceof Wall || g instanceof Monsters || g instanceof Block || g instanceof Door)
        {
//            System.out.println("g is "+ g.getClass().getName());
            moveOpposite();
        }
    }

    @Override
    public void update() {
        patrol();
        move();
        updateRect();
    }
}
