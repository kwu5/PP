package GameObj.PowerUpObj;

import GameObj.GameObj;

import java.awt.image.BufferedImage;

public abstract class PowerUpObj extends GameObj {

    final int pt ;

    PowerUpObj(int x, int y, BufferedImage img, int earnPt){
        super(x,y,img);
        this.pt = earnPt;
    }

    @Override
    public void update() {
        x +=0;
        y += 0;
    }

    public int getPt(){
        return pt;
    }

}
