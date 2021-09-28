package GameObj.Monsters;

import java.awt.image.BufferedImage;

public class Beetles extends Monsters {

    private BufferedImage upImg, downImg;

    public Beetles(int x, int y, BufferedImage upImg, BufferedImage downImg){
        super(x,y, upImg);
        this.upImg = upImg;
        this.downImg = downImg;
    }

    @Override
    public void update() {
        try{
            this.moveDown(downImg);
//            this.moveUp(upImg);
        }catch (Exception e){
            System.out.println("e:" + e);
        }
    }
//
//    @Override
//    public BufferedImage getCurrImg(){
//        return currImg;
//    }

}
