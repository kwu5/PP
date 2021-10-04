import GameObj.Explorer;
import GameObj.GameObj;
import GameObj.Door;
import GameObj.Monsters.Monsters;
import GameObj.Monsters.Mummy;
import GameObj.PowerUpObj.*;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player {

    //    private BufferedImage exImg;
    private boolean scarabsActive, unlockDoor;
    private int lives, score, scarabsNum;
    private boolean swordGained;
    final int liveMax = 3;


    public Player(int lives) {
        this.lives = lives;
        score = 0;
        scarabsNum = 0;

        scarabsActive = false;
        swordGained = false;
        unlockDoor = false;
    }



    public int getLives() {
        return lives;
    }

    public int getScore() {
        return score;
    }

    public int getScarabsNum(){return scarabsNum;}

    public void gainPt(int pt) {
        score += pt;
    }

    public void gainLives(){
        if(lives < liveMax)        lives++;
    }

    public void gainSword() {
        swordGained = true;
    }

    public void gainScarabs(){
        scarabsNum ++;
    }

    public boolean isSwordGained() {
        return swordGained;
    }

    public boolean isUnlockDoor(){
        return unlockDoor;
    }


    public void update(GameObj g) {
        if (g instanceof Monsters) {
            if(g instanceof Mummy && isSwordGained()){
                gainPt(((Mummy) g).getPoints());
            }else {
                lives--;
                System.out.println("live is "+ lives);
            }
        } else if (g instanceof Treasure) {
            gainPt(((Treasure) g).getPt());
        }else if(g instanceof Potion){
            gainLives();
        } else if(g instanceof Scarabs){
            gainScarabs();
        } else if(g instanceof Sword){
            gainSword();
        }

        if(g instanceof Door && swordGained)             unlockDoor = true;


    }




}
