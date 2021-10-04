

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


//       obj1.collision(obj2);
       obj2.collision(obj1);

       //block special
        if (obj1 instanceof Block){
            obj1.collision(obj2);
        }

       //powerUpObj
       if(obj1 instanceof PowerUpObj){
           player.update(obj1);
           return obj1;
       }

       //monster
       if(obj1 instanceof Monsters && obj2 instanceof Explorer){
           player.update(obj1);
           return obj1;
       }

       //door
        if(obj1 instanceof Door && obj2 instanceof Explorer){
            player.update(obj1);
        }









//
//        try {
//            //handle wall vs explorer/monster/block
//            //must be the first one to check
//            if (obj1 instanceof Wall) {
//                if (obj2 instanceof Explorer) ((Explorer) obj2).moveBack();
//                else if (obj2 instanceof Monsters) ((Monsters) obj2).moveOpposite();
//                else ((Block) obj2).wallCollision();
//                return null;
//            }
//
//
//            //check powerUpObj vs explorer
//            else if (obj1 instanceof PowerUpObj) {
//                player.update(obj1);
//                return obj1;
//            }
//
//
//            //check monster vs block/explorer/monster
//            else if (obj1 instanceof Monsters) {
//                if (obj2 instanceof Block) ((Monsters) obj1).moveOpposite();
//                else if (obj2 instanceof Monsters) ((Monsters) obj1).moveOpposite();
//                else if(obj2 instanceof Explorer){
//                    player.update(obj2);
//                    return obj1;
//                }
//                return null;
//            }
//
//            //check block vs explorer
//            else if (obj1 instanceof Block) {
//                if (obj2 instanceof Explorer){
//                    if(((Block) obj1).getType()==0)             ((Explorer) obj2).moveBack();
//                    else    ((Block) obj1).isPushed(((Explorer) obj2).getDirection(), ((Explorer) obj2).getSpeed())   ;
//                }
//
//            }
//
//            //check  door vs monster/explorer
//            else if (obj1 instanceof Door) {
//                if (obj2 instanceof Monsters) {
//                    ((Monsters) obj2).moveOpposite();
//                }else if(obj2 instanceof Explorer){
//
//                    if(player.isSwordGained())       return obj1;
//                    else ((Explorer) obj2).moveBack();
//
//                }
//            }
//        } catch (Exception e) {
//            System.out.println("Collision exception:" + e.getMessage());
//        }
        return null;
    }
}

//
//
//




