

import GameObj.*;
import GameObj.Monsters.Monsters;
import GameObj.PowerUpObj.PowerUpObj;
import GameObj.Walls.Block;
import GameObj.Walls.Wall;

public class Collision {

    private GameObj obj1, obj2;

    public Collision(GameObj g1, GameObj g2) {
        obj1 = g1;
        obj2 = g2;
//        System.out.println("Collision:"+ g1.getClass().getName()+ "  "+ g2.getClass().getName());
    }


    //handle all collisions
    public GameObj handleCollision(Player player) {

        try {
            //handle between wall and explorer/monster/block
            if (obj1 instanceof Wall) {
                if (obj2 instanceof Explorer) ((Explorer) obj2).moveBack();
                else if (obj2 instanceof Monsters) ((Monsters) obj2).moveOpposite();
                else ((Block) obj2).wallCollision();
                return null;
            }


            //check between powerUpObj and explorer
            else if (obj1 instanceof PowerUpObj) {
                player.update(obj1);
                return obj1;
            }


            //check between monster and block/explorer/monster
            else if (obj1 instanceof Monsters) {
                if (obj2 instanceof Block) ((Monsters) obj1).moveOpposite();
                else if (obj2 instanceof Monsters) ((Monsters) obj1).moveOpposite();
                else return obj1;
                return null;
            }

            //check block vs explorer
            else if (obj1 instanceof Block) {
                if (obj2 instanceof Explorer && ((Block) obj1).getType()==0) ((Explorer) obj2).moveBack();
                if(((Block) obj1).getType() != 0)   return null;
            }

            //check between door and monster/explorer
            else if (obj1 instanceof Door) {
                if (obj2 instanceof Monsters) {
                    ((Monsters) obj2).moveOpposite();
                }else if(obj2 instanceof Explorer){
                    //todo
                    ((Explorer) obj2).moveBack();
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




