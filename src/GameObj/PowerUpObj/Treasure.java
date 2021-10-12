package GameObj.PowerUpObj;

import java.awt.image.BufferedImage;

public class Treasure extends PowerUpObj {


    public Treasure(int x, int y, BufferedImage img, int earnpt) {
        super(x, y, img, earnpt);
    }

    public int getPt() {
        return pt;
    }

}
