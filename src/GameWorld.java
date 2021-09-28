import GameObj.Explorer;
import GameObj.Monsters.Beetles;
import GameObj.Monsters.Monsters;
import GameObj.Monsters.Mummies;
import GameObj.PowerUpObj.PowerUpObj;
import GameObj.Walls.Blocks;
import GameObj.Walls.Wall;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
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

    //todo  test field
    private Beetles b1;
    private Mummies m1;


    private final String map1 ="resources/map1.csv";

    private Graphics2D buffer;
    private JFrame jf;
    private Player player;
    private Explorer explorer;
    private ExplorerControl explorerControl;
    private float explorerSpeed = 2.2f;         //set explorerSpeed

    BufferedImage worldBackgroundImg;


    private BufferedImage world;
    private Image background ;
    private BufferedImage explorerUp, explorerDown,explorerLeft,explorerRight;
    private BufferedImage beetleUp, beetleDown,mummyDown, mummyLeft,mummyRight,mummyUp, scorpionLeft, scorpionRight;
    private BufferedImage block, blockHor,blockVert,wall1, wall2, door;
    private BufferedImage sword, scroll,treasure1, treasure2, potion,scarab;
    private BufferedImage lives,buttonHelp,buttonLoad,buttonQuit, buttonScores, buttonStart;

    private ArrayList<Monsters> monsters;
    private ArrayList<PowerUpObj> powerUpObjs;
    private ArrayList<Wall> walls;
    private ArrayList<Blocks> blocks;



//    private int tileHeight, tileWidth;

    private ArrayList<Collision> collisions;


    public static void main(String[] args) {
        GameWorld gameWorld = new GameWorld();
        gameWorld.init();

        try {
            while(true) {

                //todo update item state
                gameWorld.explorer.update();

                for (Monsters m: gameWorld.monsters) {
                    m.update();
                }

                for (Wall w: gameWorld.walls) {
                    w.update();
                }

                for (Blocks b: gameWorld.blocks) {
                    b.update();
                }





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
        world = new BufferedImage(GAME_WIDTH,GAME_HEIGHT,BufferedImage.TYPE_INT_RGB);




        try{
            //import image
            background = read(new File("resources/Background2.bmp"));
            explorerUp = read(new File("resources/Explorer_up.gif"));
            explorerDown = read(new File("resources/Explorer_down.gif"));
            explorerRight = read(new File("resources/Explorer_right.gif"));
            explorerLeft = read(new File("resources/Explorer_left.gif"));

            beetleDown = read(new File("resources/Beetle_down.gif"));
            beetleUp = read(new File("resources/Beetle_up.gif"));
            mummyDown = read(new File("resources/Mummy_down.gif"));
            mummyUp = read(new File("resources/Mummy_up.gif"));
            mummyLeft = read(new File("resources/Mummy_left.gif"));
            mummyRight = read(new File("resources/Mummy_right.gif"));
            scorpionLeft = read(new File("resources/Scorpion_left.gif"));
            scorpionRight = read(new File("resources/Scorpion_right.gif"));

            block = read(new File("resources/Block.gif"));
            blockHor = read(new File("resources/Block_hor.gif"));
            blockVert = read(new File("resources/Block_vert.gif"));
            wall1 = read(new File("resources/Wall1.gif"));
            wall2 = read(new File("resources/Wall2.gif"));

            door = read(new File("resources/Door.gif"));
            lives= read(new File("resources/Lives.gif"));
            potion = read(new File("resources/Potion.gif"));
            scarab = read(new File("resources/Scarab.gif"));
            scroll = read(new File("resources/Scroll.gif"));
            sword = read(new File("resources/Sword.gif"));
            treasure1 = read(new File("resources/Treasure1.gif"));
            treasure2 = read(new File("resources/Treasure2.gif"));

            buttonHelp = read(new File("resources/Button_help.gif"));
            buttonLoad = read(new File("resources/Button_load.gif"));
            buttonStart= read(new File("resources/Button_start.gif"));
            buttonQuit = read(new File("resources/Button_quit.gif"));
            buttonScores = read(new File("resources/Button_scores.gif"));

            //create the background image
            worldBackgroundImg = new BufferedImage(GAME_WIDTH,GAME_HEIGHT,BufferedImage.TYPE_INT_RGB);
            Graphics g = worldBackgroundImg.getGraphics();
            int tileWidth = background.getWidth(null);
            int tileHeight = background.getHeight(null);
            for (int i = 0; i <= (GAME_HEIGHT / tileHeight) - 1; i++)
                for (int j = 0; j <= (GAME_WIDTH / tileWidth) - 1; j++)
                    g.drawImage(background, j * tileWidth , i * tileHeight , tileWidth, tileHeight, null);
//            ImageIO.write(worldBackgroundImg, "bmp",new File("wbg.bmp"));


        }catch (IOException io){
            System.out.println("io:" + io);

        }catch (Exception ex){
            System.out.println("ex:" + ex);
        }






        monsters = new ArrayList<>();
        walls = new ArrayList<>();
        powerUpObjs  = new ArrayList<>();
        blocks = new ArrayList<>();

        explorer = new Explorer(245,100,explorerUp, explorerDown, explorerLeft ,explorerRight,explorerSpeed);  //todo  default loc
        explorerControl = new ExplorerControl(explorer, KeyEvent.VK_UP,KeyEvent.VK_DOWN,KeyEvent.VK_LEFT,
                KeyEvent.VK_RIGHT);


        //todo test filed
        Beetles b1 = new Beetles(250,100,beetleUp,beetleDown);
        Mummies m1 = new Mummies(260,100,mummyUp,mummyDown,mummyLeft,mummyRight);
        monsters.add(b1);
        monsters.add(m1);



        this.jf.setLayout(new BorderLayout());
        this.jf.add(this);

        this.jf.addKeyListener(explorerControl);

        this.jf.setSize(SCREEN_WIDTH , SCREEN_HEIGHT);
        this.jf.setResizable(false);
        this.jf.setLocationRelativeTo(null);

        this.jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.jf.setVisible(true);



    }

    private int GetSubWorldX( Explorer explorer){
        int explorerX = explorer.getX() + explorerLeft.getWidth()/2;
        if (explorerX - SCREEN_WIDTH/2 <= 0)    return 0;
        else if(explorerX + SCREEN_WIDTH/2 >= GAME_WIDTH)  return GAME_WIDTH - SCREEN_WIDTH;
        else return explorerX - SCREEN_WIDTH/2;

    }

    private int GetSubWorldY( Explorer explorer){
        int explorerY = explorer.getY() + explorerLeft.getHeight()/2;
        if (explorerY - SCREEN_HEIGHT/2 <= 0)    return 0;
        else if(explorerY + SCREEN_HEIGHT/2 >= GAME_HEIGHT)  return GAME_HEIGHT - SCREEN_HEIGHT;
        else return explorerY - SCREEN_HEIGHT/2;




    }






    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        buffer = world.createGraphics();
        super.paintComponent(g2);

        buffer.drawImage(worldBackgroundImg,0,0,null);

        //keep the explorer insight
        int subworldX, subworldY;
        subworldX = GetSubWorldX(explorer);
        subworldY = GetSubWorldY(explorer);
        BufferedImage worldSeen = world.getSubimage(subworldX,subworldY,SCREEN_WIDTH,SCREEN_HEIGHT);


        //obj drawing//todo
        explorer.drawImage(buffer);

        //test field todo
        for (Monsters m: monsters) {
            m.drawImage(buffer);
        }

        g2.drawImage(worldSeen, 0, 0, null);






















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
