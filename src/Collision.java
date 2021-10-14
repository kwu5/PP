

import GameObj.*;
import GameObj.Monsters.Monsters;
import GameObj.PowerUpObj.PowerUpObj;
import GameObj.Walls.Block;
import GameObj.Walls.Wall;

public class Collision extends SoundPlayer {

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

        //block is special
        if (obj1 instanceof Block) {
            obj1.collision(obj2);
        }

        //powerUpObj
        if (obj1 instanceof PowerUpObj && obj2 instanceof Explorer) {
            player.update(obj1, (Explorer) obj2);
            return obj1;
        }

        //monster
        if (obj1 instanceof Monsters && obj2 instanceof Explorer) {
            player.update(obj1, (Explorer) obj2);
            return obj1;
        }

        //door
        if (obj1 instanceof Door && obj2 instanceof Explorer) {
            player.update(obj1, (Explorer) obj2);
        }

        return null;
    }
}






