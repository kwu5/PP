package GameObj.PowerUpObj;

import GameObj.GameObj;
import GameObj.Explorer;


import java.awt.image.BufferedImage;

public abstract class PowerUpObj extends GameObj {

    final int pt;

    PowerUpObj(int x, int y, BufferedImage img, int pt){
        super(x,y,img);
        this.pt = pt;
    }

    @Override
    public void update() {
    }

    @Override
    public void collision(GameObj gameObj) {
    }

    @Override
    public void update(Explorer explorer) {

    }
}
