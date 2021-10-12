package GameObj.Monsters;

import GameObj.Explorer;
import GameObj.PowerUpObj.PowerUpObj;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Mummy extends Monsters {

    private BufferedImage mummiesUp, mummiesDown, mummiesLeft, mummiesRight;
    final int points = 300;
    private boolean explorerInSight, isFlee;
    private char explorerDir;
    private int rangeX1, rangeX2, rangeY1, rangeY2;
    private Rectangle movingArea;


    public Mummy(int x, int y, BufferedImage mUp, BufferedImage mDown, BufferedImage mLeft, BufferedImage mRight
            , int rangeX1, int rangeX2, int rangeY1, int rangeY2) {
        super(x, y, mUp);
        this.mummiesUp = mUp;
        this.mummiesDown = mDown;
        this.mummiesLeft = mLeft;
        this.mummiesRight = mRight;
        facingdirc = RIGHT;

        this.rangeX1 = rangeX1;
        this.rangeX2 = rangeX2;
        this.rangeY1 = rangeY1;
        this.rangeY2 = rangeY2;

        this.movingArea = new Rectangle(rangeX1, rangeY1, rangeX2, rangeY2);
        this.isFlee = false;

    }


    @Override
    public boolean patrol(Explorer e) {

        //when the monster can see the character
        if (((e.getX() + e.getRect().getWidth()) > this.x) && ((e.getX() < (this.x + this.currentImg.getWidth())))) {
            //in hunting area
            if (e.getY() >= rangeY1 && e.getY() <= rangeY2) {           //in range and facing monster
                if ((e.getY() < this.y && facingdirc == UP) || (e.getY() > this.y && facingdirc == DOWN)) {
                    speed = fastSpeed;
                    explorerInSight = true;
                    return true;
//                    System.out.println("Y chase");
                }
            }
        } else if (((e.getY() + e.getRect().getHeight()) > this.y) && ((e.getY() < (this.y + this.currentImg.getHeight())))) {
            //in hunting area
            if (e.getX() >= rangeX1 && e.getX() <= rangeX2) {
                if ((e.getX() < this.x && facingdirc == LEFT) || (e.getX() > this.x && facingdirc == RIGHT)) {
                    speed = fastSpeed;
                    explorerInSight = true;
                    return true;
//                    System.out.println("X chase");

                }
            }
        } else {
            speed = normalSpeed;
            explorerInSight = false;
            return false;
        }
        return false;
    }


    public void setFlee(boolean isFlee) {
        this.isFlee = isFlee;
    }

    public boolean isFlee() {
        return isFlee;
    }

    public void flee() {

        if (speed == fastSpeed) {
            moveOpposite();
            speed = normalSpeed;
        }
        isFlee = false;

    }

    public int getPoints() {
        return points;
    }

    private char getRandomDirection() {
        int rand = (int) (Math.random() * 500) + 1;
        switch (rand) {
            case 1:
                return 'l';
            case 2:
                return 'r';
            case 3:
                return 'u';
            case 4:
                return 'd';
            default:
                return facingdirc;
        }
    }

    @Override
    protected void move() {


        switch (facingdirc) {
            case UP:
                moveUp(mummiesUp);
                break;
            case DOWN:
                moveDown(mummiesDown);
                break;
            case LEFT:
                moveLeft(mummiesLeft);
                break;
            case RIGHT:
                moveRight(mummiesRight);
                break;
            default:
                System.out.println("Invalid direction:" + this.getClass());
        }
    }

    @Override
    public void update(Explorer explorer) {


        patrol(explorer);
        if (!this.rect.intersects(movingArea)) {            //if go out the area, going back
//            System.out.println("mummy move opposite");
            moveOpposite();
        } else {
            if (!explorerInSight) {                         //normal movement
                facingdirc = getRandomDirection();
//                System.out.println("mummy move");
            }
        }

        //check flee
        if (isFlee) {
            flee();
            System.out.println("flee");
        }

        move();
        updateRect();
    }

    @Override
    public void drawImage(Graphics g) {
        super.drawImage(g);
        g.drawRect(rangeX1, rangeY1, rangeX2, rangeY2);

    }
}

