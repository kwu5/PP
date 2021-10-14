package GameObj.Monsters;

import GameObj.Door;
import GameObj.GameObj;
import GameObj.Explorer;
import GameObj.Walls.Block;
import GameObj.Walls.Wall;


import java.awt.*;
import java.awt.image.BufferedImage;


public abstract class Monsters extends GameObj {


    final int normalSpeed = 1;//set up monster speed
    final int fastSpeed = 2;
    public int speed;

    final char UP = 'u';
    final char DOWN = 'd';
    final char LEFT = 'l';
    final char RIGHT = 'r';

    public char facingdirc;

    protected Monsters(int x, int y, BufferedImage img) {
        super(x, y, img);
        this.speed = normalSpeed;


    }


    /**
     * Check if explorer is in sight and react to it
     */
    public abstract boolean patrol(Explorer explorer);

    /**
     * Handle moving action
     */
    protected abstract void move();


    protected void moveUp(BufferedImage upImg) {
        this.currentImg = upImg;
        facingdirc = UP;
        y -= speed;
    }

    protected void moveDown(BufferedImage downImg) {
        this.currentImg = downImg;
        facingdirc = DOWN;
        y += speed;
    }

    protected void moveLeft(BufferedImage leftImg) {
        this.currentImg = leftImg;
        facingdirc = LEFT;
        x -= speed;
    }

    protected void moveRight(BufferedImage rightImg) {
        this.currentImg = rightImg;
        facingdirc = RIGHT;
        x += speed;
    }

    public void moveOpposite() {
        switch (facingdirc) {
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
        }

    }

    /**
     * Monster reaction to collision
     *
     * @param g
     */
    @Override
    public void collision(GameObj g) {
        if (g instanceof Wall || g instanceof Block || g instanceof Door) {
            moveOpposite();
        }
    }

    @Override
    public void update() {
    }

    @Override
    public void update(Explorer explorer) {
        patrol(explorer);
        move();
        updateRect();
    }
}
