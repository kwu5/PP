package GameObj.Walls;

import GameObj.GameObj;

import java.awt.image.BufferedImage;

public class Wall extends GameObj {

    public Wall( int x, int y,BufferedImage wall){
        super(x,y,wall);

    }

    /**
     * wall vs N/A
     * @param g
     */
    @Override
    public void collision(GameObj g) {
    }

    @Override
    public void update() {
    }
}
