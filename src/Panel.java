import GameObj.GameObj;

import java.awt.*;
import java.awt.image.BufferedImage;


public class Panel {

    private int x,y;
    final int screenWidth, screenHeight;
    private BufferedImage image,liveImg,scarabsImg;
//    private Player player  ;
    private int lives, scarabsNum, score;

    public Panel(int screenWidth, int screenHeight, BufferedImage panelimg,BufferedImage liveImg,
                 BufferedImage scarabsImg){

        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.image = panelimg;
        this.scarabsImg = scarabsImg;
        this.liveImg = liveImg;

        this.x = 0;
        this.y = screenHeight - panelimg.getHeight();

        this.scarabsNum = 0;
        this.lives = 0;
        this.score = 0;

    }


    public void update(int live, int scarabsNum, int score) {
        this.lives = live;
        this.scarabsNum = scarabsNum;
        this.score = score;
    }

    public void updateLoc(int x, int y){
        this.y = y+620;
        this.x = x;
    }


    public void drawImage(Graphics g){
        Graphics2D g2d= (Graphics2D) g;
        g2d.drawImage(image,x,y,null );

        //lives
        g2d.drawImage(liveImg,x+ 80,y,null);
        if(lives >1)   g2d.drawImage(liveImg,x+80+liveImg.getWidth(),y,null);
        if(lives >2)   g2d.drawImage(liveImg,x+80+liveImg.getWidth()+liveImg.getWidth(),y,null);

        //scarabs
        g2d.drawImage(scarabsImg,x+300,y,null);
        g2d.setColor(Color.yellow);
        g2d.setFont(new Font("TimesRoman", Font.BOLD, 20));
        g2d.drawString(String.valueOf(scarabsNum),x+310+scarabsImg.getWidth(),y+25);

        //score
        g2d.drawString(String.valueOf(score),x + 550,y+25);

    }


    }

