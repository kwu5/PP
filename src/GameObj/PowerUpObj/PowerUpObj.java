package GameObj.PowerUpObj;

import GameObj.GameObj;

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

    public int getPt(){
        return pt;
    }

}
