import GameObj.Explorer;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player {

    public Explorer explorer ;
//    private BufferedImage exImg;

    public Player(BufferedImage exUpImg, BufferedImage exDownImg, BufferedImage exLeftImg, BufferedImage exRightImg){
        this.explorer = new Explorer(50,20,exUpImg, exDownImg, exLeftImg ,exRightImg);
    }

    public Explorer getExplorer(){
        return explorer;
    }

    public void update(){
        explorer.update();
    }


}
