package GameObj.PowerUpObj;

import GameObj.GameObj;

import java.awt.image.BufferedImage;

public abstract class PowerUpObj extends GameObj {

    PowerUpObj(int x, int y, BufferedImage img){
        super(x,y,img);
    }

    @Override
    public void update() {

    }
}
