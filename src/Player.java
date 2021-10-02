import GameObj.Explorer;
import GameObj.GameObj;
import GameObj.Monsters.Monsters;
import GameObj.PowerUpObj.PowerUpObj;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player {

//    private BufferedImage exImg;
    private boolean scarabsActive;
    private int lives,score;
    private boolean swordGained;


    public Player(int lives, int score){
        this.lives =lives;
        this.score = score;
        scarabsActive =false;
        this.swordGained =false;

    }


    public int getLives(){return lives;}
    public int getScore(){return score;}

    public void earnPt(int pt){score += pt;}

    public void gainSword(){swordGained = true;}
    public boolean isSwordGained(){return swordGained;}


    public void update(GameObj p){
        if(p instanceof Monsters){
            lives --;
        }
        else if(p instanceof PowerUpObj){
            earnPt(((PowerUpObj) p).getPt());
        }

    }





}
