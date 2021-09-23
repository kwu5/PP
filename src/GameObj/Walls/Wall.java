package GameObj.Walls;

import java.awt.image.BufferedImage;

public class Wall extends Walls{

    private int x,y;
    private BufferedImage wall1, wall2;

    public Wall(BufferedImage wall1, BufferedImage wall2, int x, int y){
        this.x = x;
        this.y = y;
        this.wall1 = wall1;
        this.wall2 = wall2;
    }




    @Override
    public void update() {

    }
}
