import GameObj.Explorer;
import GameObj.GameObj;
import GameObj.Door;
import GameObj.Monsters.Monsters;
import GameObj.Monsters.Mummy;
import GameObj.PowerUpObj.*;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends SoundPlayer {

    //    private BufferedImage exImg;
    private boolean scarabsActive, unlockDoor;
    private int lives, score, scarabsNum;
    final int liveMax = 3;

//    private Explorer e;


    public Player(int lives, Explorer e) {
        this.lives = lives;


        score = 0;
        scarabsNum = 0;

        scarabsActive = false;
        unlockDoor = false;
    }


    public int getLives() {
        return lives;
    }

    public int getScore() {
        return score;
    }

    public int getScarabsNum() {
        return scarabsNum;
    }

    public void gainPt(int pt) {
        score += pt;
    }

    public void gainLives() {
        if (lives < liveMax) lives++;
    }

//    public void gainSword() {
//        swordGained = true;
//    }

    public void gainScarabs() {
        System.out.println("gainScarabs");
        scarabsNum++;
    }

//    public boolean isSwordGained() {
//        return swordGained;
//    }
//
//    public boolean isUnlockDoor(){
//        return unlockDoor;
//    }

    public void collision(GameObj g, Explorer e) {
        if (g instanceof Monsters) {
//            if(g instanceof Mummy && e.isSwordEquipped()){
//                gainPt(((Mummy) g).getPoints());
//            }else {
            lives--;
//            }
        } else if (g instanceof Treasure) {
            gainPt(((Treasure) g).getPt());
        } else if (g instanceof Potion) {
            gainLives();
        } else if (g instanceof Scarabs) {
            gainScarabs();
        } else if (g instanceof Sword) {
            e.equippedSword();
        }

        if (g instanceof Door && e.isSwordEquipped()) unlockDoor = true;

    }


    public void update(GameObj g, Explorer e) {

        collision(g, e);

        try {

            //wield sword
            if (g instanceof Explorer && ((Explorer) g).isWieldingSword() && ((Explorer) g).isSwordEquipped()) {
                score--;
                if (score < 0) score = 0;

            }

            //active sword
            if (g instanceof Explorer && ((Explorer) g).isSwordActive() && ((Explorer) g).isSwordEquipped()) {

                this.playSound("powerStart");
                score -= 100;
                if (score < 0) score = 0;
                e.setSwordActive(false);
            }

            //active scarab
            if (g instanceof Explorer && ((Explorer) g).isScarabsActive() && scarabsNum > 0) {

                this.playSound("powerStart");
                scarabsNum--;
                e.setScarabsActive(false);
            }

        } catch (Exception exception) {
            System.out.println("Player" + exception.getMessage());
        }


    }


    public boolean isUnlockDoor() {
        return unlockDoor;
    }
}
