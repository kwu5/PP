package GameObj.Walls;

import GameObj.Explorer;

import java.awt.image.BufferedImage;

public class VertBlock extends Block {
    private int rangeY1, rangeY2;

    public VertBlock(int x, int y, BufferedImage img, int rangeY1, int rangeY2) {
        super(x, y, img);
        this.rangeY1 = rangeY1;
        this.rangeY2 = rangeY2;

    }


    @Override
    public boolean isOutOfRange() {
        return (this.y - rangeY1 < 0 || rangeY2 - this.y < 0);
    }

    @Override
    public void move(Explorer explorer) {

        //moveback
        if (this.y - rangeY1 < 0) {
//            System.out.println("moveback-d");
            pushDown(explorer.getSpeed());
        } else if (this.y - rangeY2 > 0) {
            pushUp(explorer.getSpeed());
//            System.out.println("moveback-u");

        }


        //being pushed
        if (explorer.getDirection() == 'u') {
            pushUp(explorer.getSpeed());
//            System.out.println("move-u");

        } else if (explorer.getDirection() == 'd') {
            pushDown(explorer.getSpeed());
//            System.out.println("move-d");

        }
    }

    //    @Override
    public void backToStartPt(Explorer explorer) {


        if (!backToStartRect.intersects(explorer.getRect())) {
            if (this.y != startY) {
                if (this.y < startY) pushDown(speed);
                else pushUp(speed);
            }
        }

    }


}
