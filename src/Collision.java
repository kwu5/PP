

import GameObj.*;
import GameObj.Monsters.Monsters;
import GameObj.PowerUpObj.PowerUpObj;
import GameObj.Walls.Blocks;
import GameObj.Walls.Wall;


import java.awt.*;

public class Collision {

    private GameObj obj1, obj2;

    public Collision(GameObj g1, GameObj g2) {
        obj1 = g1;
        obj2 = g2;
//        System.out.println("Collision:"+ g1.getClass().getName()+ "  "+ g2.getClass().getName());
    }


    //handle all collisions
    public GameObj handleCollision() {

        try {
            //handle between wall and explorer/monster/block
            if (obj1 instanceof Wall) {
                if (obj2 instanceof Explorer) ((Explorer) obj2).moveBack();
                else if (obj2 instanceof Monsters) ((Monsters) obj2).moveOpposite();
                else ((Blocks) obj2).wallCollision();
                return null;
            }


            //check between powerUpObj and explorer
            else if (obj1 instanceof PowerUpObj) {
                return obj1;

            }


            //check between monster and block/explorer/monster
            else if (obj1 instanceof Monsters) {
                if (obj2 instanceof Blocks) ((Monsters) obj1).moveOpposite();
                else if (obj2 instanceof Monsters) ((Monsters) obj1).moveOpposite();
                else return obj1;
                return null;
            }

            //check between door and monster/explorer
            else if (obj1 instanceof Door) {
                if (obj2 instanceof Monsters) {
                    ((Monsters) obj2).moveOpposite();
                }
            }
        } catch (Exception e) {
            System.out.println("Collision exception:" + e.getMessage());
        }
        return null;
    }
}

//
//
//




