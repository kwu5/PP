package GameObj.Monsters;

import java.awt.image.BufferedImage;

public class Mummies extends Monsters{

    BufferedImage currImg;
    Mummies(int x,int y){
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
