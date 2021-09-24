package GameObj.Monsters;

import java.awt.image.BufferedImage;

public class Scorpians extends Monsters{

    BufferedImage currImg;
    Scorpians(int x, int y){
        super(x,y);
    }

    @Override
    public void update() {

    }

    @Override
    public BufferedImage getCurrImg(){
        return currImg;
    }
}
