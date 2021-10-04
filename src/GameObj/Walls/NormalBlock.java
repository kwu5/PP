package GameObj.Walls;

import GameObj.GameObj;
import GameObj.Explorer;

import java.awt.image.BufferedImage;

public class NormalBlock extends Block{

    public NormalBlock(int x, int y, BufferedImage img, Explorer explorer){
        super(x,y,img,explorer);
    }

    @Override
    public boolean isOutOfRange() {
        return false;
    }

    @Override
    public void backToStartPt() {
//
    }

    @Override
    public void move() {

    }
}
