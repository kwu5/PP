package GameObj.Monsters;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Mummies extends Monsters{

    private BufferedImage mummiesUp, mummiesDown, mummiesLeft, mummiesRight;

    public Mummies(int x,int y,BufferedImage mUp, BufferedImage mDown,BufferedImage mLeft, BufferedImage mRight ){
        super(x,y,mUp);
        this.mummiesUp = mUp;
        this.mummiesDown = mDown;
        this.mummiesLeft = mLeft;
        this.mummiesRight = mRight;
    }

    @Override
    public void update() {

//        this.moveDown(mummiesDown);
//        this.moveUp(mummiesUp);
//        this.moveLeft(mummiesLeft);
        this.moveRight(mummiesRight);


    }
//    @Override
//    public BufferedImage getCurrImg(){
//        return currImg;
//    }


}
