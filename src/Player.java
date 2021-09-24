import GameObj.Explorer;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player {

    private Explorer explorer ;

//    private BufferedImage exImg;

    public Player(BufferedImage exUpImg, BufferedImage exDownImg, BufferedImage exLeftImg, BufferedImage exRightImg,
                  float speed){
        this.explorer = new Explorer(245,100,exUpImg, exDownImg, exLeftImg ,exRightImg,speed);  //todo  default loc
    }

    public Explorer getExplorer(){
        return explorer;
    }

    public void update(){
        explorer.update();
    }


}
