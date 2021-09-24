package GameObj.Monsters;

import java.awt.image.BufferedImage;

public class Beetles extends Monsters {

    private BufferedImage upImg, downImg,currImg;

    public Beetles(int x, int y, BufferedImage upImg, BufferedImage downImg){
        super(x,y);
        this.upImg = upImg;
        this.downImg = downImg;
        this.currImg = downImg;
    }

    @Override
    public void update() {
        try{
//            this.moveDown();

            this.moveUp(currImg);
        }catch (Exception e){
            System.out.println("e:" + e);
        }
    }

    @Override
    public BufferedImage getCurrImg(){
        return currImg;
    }

}
