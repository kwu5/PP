package GameObj.Walls;

import GameObj.GameObj;
import GameObj.Explorer;


import java.awt.image.BufferedImage;

public class Wall extends GameObj {

    public Wall(int x, int y, BufferedImage wall) {
        super(x, y, wall);

    }


    @Override
    public void collision(GameObj g) {
    }

    @Override
    public void update() {
    }

    @Override
    public void update(Explorer explorer) {

    }
}
