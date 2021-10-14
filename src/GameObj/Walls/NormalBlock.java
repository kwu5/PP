package GameObj.Walls;

import GameObj.GameObj;
import GameObj.Explorer;

import java.awt.image.BufferedImage;

public class NormalBlock extends Block {

    public NormalBlock(int x, int y, BufferedImage img) {
        super(x, y, img);
    }

    @Override
    public boolean isOutOfRange() {
        return false;
    }

    @Override
    public void backToStartPt(Explorer explorer) {
    }

    @Override
    public void move(Explorer explorer) {

    }
}
