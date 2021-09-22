import GameObj.Explorer;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static javax.imageio.ImageIO.read;

public class GameWorld extends JPanel {


    //screen and gamemap size
    public static final int GAME_WIDTH = 1984;
    public static final int GAME_HEIGHT = 1024;
    private static final int SCREEN_WIDTH = 1024;
    private static final int SCREEN_HEIGHT = 720;

    //todo
    private BufferedImage obj;

    private final String map1 ="resources/map1.csv";

    private Graphics2D buffer;
    private JFrame jf;
    private Player player;
    private Explorer explorer;

    private BufferedImage world;
    private Image background ;
    private BufferedImage explorerUp, explorerDown,explorerLeft,explorerRight;

    private int tileHeight, tileWidth;

    private ArrayList<Collision> collisions;


    public static void main(String[] args) {
        GameWorld gameWorld = new GameWorld();
        gameWorld.init();

        //todo update game state
        try {
            while(true) {
                gameWorld.explorer.update();
                gameWorld.repaint();
                Thread.sleep(1000 / 144);
            }
        }catch (InterruptedException interruptedException){
            System.out.println(interruptedException);
        }catch (Exception exception){
            System.out.println(exception);
        }



    }

    private void init() {

        this.jf = new JFrame("PyramidPanic ");
        this.world = new BufferedImage(GAME_WIDTH,GAME_HEIGHT,BufferedImage.TYPE_INT_RGB);

        try{
            //import image
            background = read(new File("resources/Background2.bmp"));
            explorerUp = read(new File("resources/Explorer_up"));
            explorerDown = read(new File("resources/Explorer_down"));
            explorerRight = read(new File("resources/Explorer_right"));
            explorerLeft = read(new File("resources/Explorer_left"));




            player = new Player();






        }catch (IOException io){
            System.out.println("io:" + io);

        }catch (Exception ex){
            System.out.println("ex:" + ex);
        }

        this.jf.setLayout(new BorderLayout());
        this.jf.add(this);

        this.jf.setSize(SCREEN_WIDTH , SCREEN_HEIGHT);
        this.jf.setResizable(false);
        this.jf.setLocationRelativeTo(null);

        this.jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.jf.setVisible(true);


        this.tileWidth = background.getWidth(null);
        this.tileHeight = background.getHeight(null);


    }
    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        buffer = world.createGraphics();
        super.paintComponent(g2);

        for (int i = 0; i <= (GAME_HEIGHT / tileHeight) - 1; i++)
            for (int j = 0; j <= (GAME_WIDTH / tileWidth) - 1; j++)
                buffer.drawImage(background, j * tileWidth , i * tileHeight , tileWidth, tileHeight, null);
//        buffer.drawImage(background, 20, 20, null);



        g2.drawImage(world, 0, 0, null);



    }

    public void clearMap(){

    }

    public void addMap(){

    }

    private BufferedImage resize(BufferedImage img, int weight, int height){
        Image tmp = img.getScaledInstance(weight, height, Image.SCALE_SMOOTH);
        BufferedImage dimg = new BufferedImage(weight, height, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();

        return dimg;
    }

}
