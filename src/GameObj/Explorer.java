package GameObj;

import GameObj.Monsters.Mummy;
import GameObj.PowerUpObj.Sword;
import GameObj.Walls.*;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Explorer extends GameObj {


    private int imgWidth, imgHeight;
    private BufferedImage exUpImg, exDownImg, exLeftImg, exRightImg;

    //    private Rectangle exRect;
    private float speed;

    private boolean isSwordEquipped, isWieldingSword, isScarabsActive, isSwordActive;

    private boolean leftPressed, rightPressed, upPressed, downPressed, spacePressed;
    private boolean isMoveDown, isMoveLeft, isMoveRight, isMoveUp;


    public Explorer(int x, int y, BufferedImage exUp, BufferedImage exDown, BufferedImage exLeft, BufferedImage exRight,
                    float speed) {
        super(x, y, exUp);

        this.speed = speed;
        this.isSwordEquipped = false;
        this.isScarabsActive = false;

        this.currentImg = exDownImg;
        this.exUpImg = exUp;
        this.exDownImg = exDown;
        this.exLeftImg = exLeft;
        this.exRightImg = exRight;

        this.imgHeight = exUpImg.getHeight();
        this.imgWidth = exUpImg.getWidth();

//        this.exRect = new Rectangle(x + 100, y + 100, imgWidth - 100, imgHeight - 100);


    }


    public void toggleRightPressed() {
        this.rightPressed = true;
    }

    public void toggleLeftPressed() {
        this.leftPressed = true;
    }

    public void toggleUpPressed() {
        this.upPressed = true;
    }

    public void toggleDownPressed() {
        this.downPressed = true;
    }

    public void unToggleRightPressed() {
        this.rightPressed = false;
    }

    public void unToggleLeftPressed() {
        this.leftPressed = false;
    }

    public void unToggleUpPressed() {
        this.upPressed = false;
    }

    public void unToggleDownPressed() {
        this.downPressed = false;
    }

    public void toggleSpacePressed() {
        this.spacePressed = true;
    }

    public void unToggleSpacePressed() {
        this.spacePressed = false;
    }


    private void moveUp() {
        y -= speed;
    }

    private void moveDown() {
        y += speed;
    }

    private void moveLeft() {
        x -= speed;
    }

    private void moveRight() {
        x += speed;
    }

    public void moveBack() {
//        System.out.println("moveback");
        if (isMoveDown) y -= speed;
        else if (isMoveUp) y += speed;
        else if (isMoveLeft) x += speed;
        else if (isMoveRight) x -= speed;
    }

    public void equippedSword() {
        isSwordEquipped = true;
    }

    public void wieldingSword() {
        isWieldingSword = true;
    }

    public void setScarabsActive(boolean isScarabsActive) {
        this.isScarabsActive = isScarabsActive;         //try to active, not guarantee successful
    }

    public void setSwordActive(boolean isSwordActive) {
//        System.out.println("setSwordActive");
        if (isSwordEquipped) {
//            System.out.println("setSwordActive: "+ isSwordActive);
            this.isSwordActive = isSwordActive;
        }
    }

    public boolean isWieldingSword() {
        return isWieldingSword;
    }

    public boolean isSwordEquipped() {
        return isSwordEquipped;
    }

    public boolean isScarabsActive() {
        return isScarabsActive;
    }

    public boolean isSwordActive() {
        return isSwordActive;
    }


    public float getSpeed() {
        return speed;
    }

    ;

    public char getDirection() {
        if (isMoveDown) return 'd';
        else if (isMoveUp) return 'u';
        else if (isMoveLeft) return 'l';
        else return 'r';
    }


    /**
     * Explorer reaction to blocks
     *
     * @param block
     */
    private void collisionBlock(Block block) {
        if (block instanceof NormalBlock) {
            moveBack();
        } else if (block instanceof HorBlock) {
            if (getDirection() == 'u' || getDirection() == 'd' || block.isOutOfRange()) {
                moveBack();

            }
        } else if (block instanceof VertBlock) {
            if (getDirection() == 'l' || getDirection() == 'r' || block.isOutOfRange()) {
                moveBack();
            }
        }
    }


    /**
     * Explorer reaction to collision except blocks
     *
     * @param g
     */
    @Override
    public void collision(GameObj g) {
        if (g instanceof Wall || g instanceof Door) {
//            System.out.println("g is "+ g.getClass().getName());
            moveBack();
        } else if (g instanceof Block) {
            collisionBlock((Block) g);
        } else if (g instanceof Sword) {
            equippedSword();
        }


    }


    @Override
    public void update(Explorer explorer) {

    }


    @Override
    public void update() {

        isMoveDown = false;
        isMoveUp = false;
        isMoveLeft = false;
        isMoveRight = false;
        isWieldingSword = false;


        if (spacePressed && isSwordEquipped) {
            wieldingSword();
        }


        if (leftPressed) {
            this.moveLeft();
            isMoveLeft = true;
            this.currentImg = exLeftImg;
        }
        if (rightPressed) {
            this.moveRight();
            isMoveRight = true;
            this.currentImg = exRightImg;
        }
        if (upPressed) {
            this.moveUp();
            isMoveUp = true;
            this.currentImg = exUpImg;
        }
        if (downPressed) {
            this.moveDown();
            isMoveDown = true;
            this.currentImg = exDownImg;
        }
        updateRect();
    }


}


