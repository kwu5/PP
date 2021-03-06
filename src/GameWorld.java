import GameObj.Explorer;
import GameObj.GameObj;
import GameObj.Monsters.Beetle;
import GameObj.Monsters.Monsters;
import GameObj.Monsters.Mummy;
import GameObj.Monsters.Scorpion;
import GameObj.PowerUpObj.*;
import GameObj.Walls.*;
import GameObj.Door;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.Timer;

import javax.sound.sampled.*;


import static javax.imageio.ImageIO.read;

public class GameWorld extends JPanel {


    //screen and gameMap size
    private static final int offset = 1000;             //avoid paintImage exception
    public static final int GAME_WIDTH = 1984 + offset;
    public static final int GAME_HEIGHT = 1024 + offset;
    private static final int SCREEN_WIDTH = 1024;
    private static final int SCREEN_HEIGHT = 720;


    private final String map1 = "resources/map1 - Sheet1.csv";
    private float explorerSpeed = 2;         //set explorerSpeed


    private Graphics2D buffer;
    private JFrame jf;
    private SoundPlayer soundPlayer;
    private Player player;
    private Panel panel;
    private TitlePage startScreen;
    private Explorer explorer;
    private ExplorerControl explorerControl;

    private BufferedImage world;
    private Image worldBackgroundImg;
    private BufferedImage darkness;


    private BufferedImage background;
    private BufferedImage explorerUp, explorerDown, explorerLeft, explorerRight;
    private BufferedImage beetleUp, beetleDown, mummyDown, mummyLeft, mummyRight, mummyUp, scorpionLeft, scorpionRight;
    private BufferedImage block, blockHor, blockVert, wall1, wall2, doorImg;
    private BufferedImage sword, scroll, treasure1, treasure2, potion, scarab;
    private BufferedImage lives, buttonHelp, buttonLoad, buttonQuit, buttonScores, buttonStart;
    private BufferedImage congratulation, title, panelImg, light, background1, gameOver;


    private ArrayList<Monsters> monsters;
    private ArrayList<PowerUpObj> powerUpObjs;
    private ArrayList<Wall> walls;
    private ArrayList<Block> blocks;
    private Stack<Collision> collisions;
    private GameEvent gameEvent;
    private Door door;


    public static void main(String[] args) {
        GameWorld gameWorld = new GameWorld();


        try {
            gameWorld.init();
            gameWorld.soundPlayer.playBackgroundMusic();

            while (true) {

                gameWorld.explorer.update();

                //update all in-game objects
                for (Monsters m : gameWorld.monsters) {
                    m.update(gameWorld.explorer);

                    if (m.patrol(gameWorld.explorer)) {
//                        gameWorld.soundPlayer.playSound(m);
                    }
                    gameWorld.checkCollision(m);


                    if (m instanceof Mummy && gameWorld.explorer.isSwordActive() && !((Mummy) m).isFlee()) {
                        ((Mummy) m).flee();


                    }
                    if (m instanceof Mummy && gameWorld.explorer.isScarabsActive() && gameWorld.player.getScarabsNum() > 0
                            && !((Mummy) m).isFlee()) {
                        ((Mummy) m).flee();
                    }
                }
                for (Wall w : gameWorld.walls) {
                    gameWorld.checkCollision(w);
                }
                for (Block b : gameWorld.blocks) {
                    b.update(gameWorld.explorer);
                    gameWorld.checkCollision(b);
                }
                for (PowerUpObj p : gameWorld.powerUpObjs) {
                    p.update();
                    gameWorld.checkCollision(p);
                }
                gameWorld.checkCollision(gameWorld.door);

                //update gameEvent and delete object after collision
                while (!gameWorld.collisions.empty()) {
                    GameObj tem = gameWorld.collisions.pop().handleCollision(gameWorld.player);
                    gameWorld.gameEvent.updateEvent();
                    gameWorld.deleteCollisionObj(tem);
                }

                //update player, gameEvent, panel
                gameWorld.player.update(gameWorld.explorer, gameWorld.explorer);
                gameWorld.gameEvent.updateEvent();
                gameWorld.panel.update(gameWorld.player.getLives(), gameWorld.player.getScarabsNum(), gameWorld.player.getScore());

                gameWorld.repaint();

                //gameEvent check
                if (gameWorld.gameEvent.isGameOver()) {
                    break;
                }
                if (gameWorld.gameEvent.isWin()) {
                    break;
                }
                Thread.sleep(1000 / 144);
            }
        } catch (InterruptedException interruptedException) {
            System.out.println(interruptedException);
        } catch (Exception exception) {
            System.out.println(exception);
        }
    }


    private void init() {

        world = new BufferedImage(GAME_WIDTH, GAME_HEIGHT, BufferedImage.TYPE_INT_RGB);


        //sound player
        soundPlayer = new SoundPlayer();

        try {
            //import image
            background = read(new File("./resources/Background2.bmp"));
            explorerUp = read(new File("./resources/Explorer_up.gif"));
            explorerDown = read(new File("./resources/Explorer_down.gif"));
            explorerRight = read(new File("./resources/Explorer_right.gif"));
            explorerLeft = read(new File("./resources/Explorer_left.gif"));

            beetleDown = read(new File("./resources/Beetle_down.gif"));
            beetleUp = read(new File("./resources/Beetle_up.gif"));
            mummyDown = read(new File("./resources/Mummy_down.gif"));
            mummyUp = read(new File("./resources/Mummy_up.gif"));
            mummyLeft = read(new File("./resources/Mummy_left.gif"));
            mummyRight = read(new File("./resources/Mummy_right.gif"));
            scorpionLeft = read(new File("./resources/Scorpion_left.gif"));
            scorpionRight = read(new File("./resources/Scorpion_right.gif"));

            block = read(new File("./resources/Block.gif"));
            blockHor = read(new File("./resources/Block_hor.gif"));
            blockVert = read(new File("./resources/Block_vert.gif"));
            wall1 = read(new File("./resources/Wall1.gif"));
            wall2 = read(new File("./resources/Wall2.gif"));

            doorImg = read(new File("./resources/Door.gif"));
            lives = read(new File("./resources/Lives.gif"));
            potion = read(new File("./resources/Potion.gif"));
            scarab = read(new File("./resources/Scarab.gif"));
            scroll = read(new File("./resources/Scroll.gif"));
            sword = read(new File("./resources/Sword.gif"));
            treasure1 = read(new File("./resources/Treasure1.gif"));
            treasure2 = read(new File("./resources/Treasure2.gif"));

            buttonHelp = read(new File("./resources/Button_help.gif"));
            buttonLoad = read(new File("./resources/Button_load.gif"));
            buttonStart = read(new File("./resources/Button_start.gif"));
            buttonQuit = read(new File("./resources/Button_quit.gif"));
            buttonScores = read(new File("./resources/Button_scores.gif"));
            background1 = read(new File("./resources/Background1.bmp"));


            congratulation = read(new File("./resources/Congratulation.gif"));
            light = read(new File("./resources/light1.png"));
            title = read(new File("./resources/Title.gif"));
            panelImg = read(new File("./resources/Panel.gif"));
            gameOver = read(new File("./resources/game_over1.png"));


            //create the background image
            worldBackgroundImg = new BufferedImage(GAME_WIDTH, GAME_HEIGHT, BufferedImage.TYPE_INT_RGB);
            Graphics g = worldBackgroundImg.getGraphics();
            int tileWidth = background.getWidth(null);
            int tileHeight = background.getHeight(null);
            for (int i = 0; i <= (GAME_HEIGHT / tileHeight) - 1; i++)
                for (int j = 0; j <= (GAME_WIDTH / tileWidth) - 1; j++)
                    g.drawImage(background, j * tileWidth, i * tileHeight, tileWidth, tileHeight, null);

            //darkness
            darkness = new BufferedImage(GAME_WIDTH, GAME_HEIGHT, BufferedImage.TYPE_INT_RGB);


            explorer = new Explorer(0, 0, explorerUp, explorerDown, explorerLeft, explorerRight, explorerSpeed);
            monsters = new ArrayList<>();
            walls = new ArrayList<>();
            powerUpObjs = new ArrayList<>();
            blocks = new ArrayList<>();
            collisions = new Stack<>();
            mapSetUp();


        } catch (IOException io) {
            System.out.println("io:" + io);

        } catch (Exception ex) {
            System.out.println("ex:" + ex);
        }

        player = new Player(3);
        explorerControl = new ExplorerControl(explorer, KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT,
                KeyEvent.VK_RIGHT, KeyEvent.VK_SPACE);
        gameEvent = new GameEvent(player, explorer, gameOver, congratulation, explorer.getX(), explorer.getY());
        panel = new Panel(SCREEN_WIDTH, SCREEN_HEIGHT, panelImg, lives, scarab);


        // test filed
//        door  =  new Door(500,700,doorImg);
//        Beetle b1 = new Beetle(250, 50, beetleUp, beetleDown,w1.getY(),w2.getY());
//        Scorpion s1 = new Scorpion(180, 200, scorpionLeft, scorpionRight,w4.getX(),w5.getX());
//        Mummy m1 = new Mummy(900, 100, mummyUp, mummyDown, mummyLeft, mummyRight,500,1500,50,300);
//        monsters.add(b1);
//        monsters.add(m1);
//        monsters.add(s1);
//
//        Potion po1 = new Potion(270, 210, potion);
//        Scarabs sc1 = new Scarabs(300, 210, scarab);
//        Scarabs sc2 = new Scarabs(400, 210, scarab);

//        Sword sw1 = new Sword(350, 210, sword);
//        Treasure tr1 = new Treasure(400, 210, treasure1, 40);
//        Treasure tr2 = new Treasure(450, 210, treasure2, 50);
//        powerUpObjs.add(po1);
//        powerUpObjs.add(sc1);
//        powerUpObjs.add(sc2);
//        powerUpObjs.add(sw1);
//        powerUpObjs.add(tr1);
//        powerUpObjs.add(tr2);
//
//        Block bl0 = new NormalBlock(600, 600,block);
//        Block bl1 = new VertBlock(700,700,blockVert,300,1400);
//        Block bl2 = new HorBlock(900,800,blockHor,400,1600);
////        Block bl3 = new HorBlock(250,200,blockHor,600,1600);
//        blocks.add(bl0);
//        blocks.add(bl1);
//        blocks.add(bl2);
////        blocks.add(bl3);
//

        this.jf = new JFrame("PyramidPanic ");
        this.jf.setLayout(new BorderLayout());
        this.jf.add(this);
        this.jf.addKeyListener(explorerControl);
        this.jf.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        this.jf.setResizable(false);
        this.jf.setLocationRelativeTo(null);

        this.jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.jf.setVisible(false);


        //startScreen setup
        startScreen = new TitlePage(this.jf, buttonStart, buttonLoad, buttonHelp, buttonScores, buttonQuit, background1);
        startScreen.setUp();


    }

    /**
     * Helper method used to keep explorer in sight.
     *
     * @param explorer
     * @return
     */
    private int getSubWorldX(Explorer explorer) {
        int explorerX = explorer.getX();

        if (explorerX - SCREEN_WIDTH / 2 <= 0) {
            return 0;
        } else {
            return explorerX - SCREEN_WIDTH / 2;
        }

    }

    /**
     * Helper method used to keep explorer in sight.
     *
     * @param explorer
     * @return
     */
    private int getSubWorldY(Explorer explorer) {
        int explorerY = explorer.getY();
        if (explorerY - SCREEN_HEIGHT / 2 <= 0) {

            return 0;
        } else {

            return explorerY - SCREEN_HEIGHT / 2;
        }
    }


    /**
     * Map set up
     * Use .csv file to set up the map:
     *
     * @throws Exception
     */
    private void mapSetUp() throws Exception {

        int mxs = 45;
        int mxe = 416 - 32 + 10;
        int mys = 500;
        int mye = 704 - 480 + 10;


        Scanner scanner = new Scanner(new File(map1));
        char itemCode;

        while (scanner.hasNext()) {

            for (int i = 0; i < 31; i++) {          //row

                String data = scanner.next();
                String[] line = data.split(",");
                int sightUnitX = wall1.getWidth();
                int sightUnixY = wall1.getHeight();
                int sight;
                int type;

                for (int j = 0; j < 52; j++) {      //column
                    String item = line[j];
                    if (item.length() > 0) {
                        itemCode = item.charAt(0);
                    } else {
                        itemCode = ' ';
                    }

                    int xTem = j * wall1.getWidth();
                    int yTem = i * wall1.getWidth();

                    switch (itemCode) {

                        //wall
                        case 'w':
                            walls.add(new Wall(xTem, yTem, wall1));
                            break;

                        //treasure
                        case 't':
                            type = item.charAt(1);
                            if (type == 'l') powerUpObjs.add(new Treasure(xTem, yTem, treasure1, 500));
                            else powerUpObjs.add(new Treasure(xTem, yTem, treasure2, 800));
                            break;

                        //sword
                        case 'k':
                            powerUpObjs.add(new Sword(xTem, yTem, sword));
                            break;

                        //scarab
                        case 'c':
                            powerUpObjs.add(new Scarabs(xTem, yTem, scarab));
                            break;

                        //potion
                        case 'p':
                            powerUpObjs.add(new Potion(xTem, yTem, potion));
                            break;

                        //door
                        case 'd':
                            door = new Door(xTem, yTem, doorImg);
                            break;

                        //explorer
                        case 'e':
                            explorer.setX(xTem);
                            explorer.setY(yTem);
                            break;

                        //beetle
                        case 'b':
                            sight = Character.getNumericValue(item.charAt(1));
                            monsters.add(new Beetle(xTem, yTem, beetleUp, beetleDown, yTem - sight * sightUnixY, yTem));
                            break;

                        //scorpion
                        case 's':
                            sight = Character.getNumericValue(item.charAt(1));
                            monsters.add(new Scorpion(xTem, yTem, scorpionLeft, scorpionRight, xTem, xTem + sight * sightUnitX));
                            break;

                        //mummy
                        case 'm':
                            monsters.add(new Mummy(xTem, yTem, mummyUp, mummyDown, mummyLeft, mummyRight, mxs, mxe, mys, mye));
                            break;

                        //vertical block
                        case 'v':
                            sight = Character.getNumericValue(item.charAt(1));
                            blocks.add(new VertBlock(xTem, yTem, blockVert, yTem - sight * sightUnixY, yTem + sight * sightUnixY));
                            break;

                        //normal block
                        case 'u':
                            blocks.add(new NormalBlock(xTem, yTem, block));
                            break;

                        //horizontal block
                        case 'h':
                            sight = Character.getNumericValue(item.charAt(1));
                            blocks.add(new HorBlock(xTem, yTem, blockHor, xTem - sight * sightUnitX, xTem + sight * sightUnitX));
                            break;


                        default:

                    }
                }
            }
        }

    }


    /**
     * Paint the gameWorld
     * Keep the explorer in screen
     *
     * @param g
     */
    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        buffer = world.createGraphics();
        super.paintComponent(g2);

        buffer.drawImage(worldBackgroundImg, 0, 0, null);

        //in-game object
        for (Monsters m : monsters) {
            m.drawImage(buffer);
        }
        for (PowerUpObj p : powerUpObjs) {
            p.drawImage(buffer);
        }
        for (Wall w : walls) {
            w.drawImage(buffer);
        }
        for (Block b : blocks) {
            b.drawImage(buffer);
        }


        explorer.drawImage(buffer);
        door.drawImage(buffer);

        //keep the explorer in sight
        int subworldX, subworldY;
        subworldX = getSubWorldX(explorer);
        subworldY = getSubWorldY(explorer);


        BufferedImage worldSeen = world.getSubimage(subworldX, subworldY, SCREEN_WIDTH, SCREEN_HEIGHT);
        panel.updateLoc(subworldX, subworldY);
        panel.drawImage(buffer);


        //change sight
        if (explorer.isSwordEquipped()) changeSight(buffer);


        //gameEvent
        if (gameEvent.isGameOver()) {
            buffer.drawImage(gameOver, subworldX + 10, subworldY + 10, null);
        } else if (gameEvent.isWin()) {
            buffer.drawImage(congratulation, subworldX + SCREEN_WIDTH / 3, subworldY + SCREEN_HEIGHT / 3, null);
        }

        g2.drawImage(worldSeen, 0, 0, null);

//        System.out.println("---------------------------------------------------------");
//        System.out.println("---------------------------------------------------------");
//        System.out.println("---------------------------------------------------------");
//        System.out.println("---------------------------------------------------------");


    }


    /**
     * Change sight after the equipping/wielding the sword
     *
     * @param buffer
     */
    private void changeSight(Graphics2D buffer) {
        int lightSubX = explorer.getX() + explorerUp.getWidth() / 2 - light.getWidth() / 2;
        int lightSubY = explorer.getY() + explorerDown.getHeight() / 2 - light.getHeight() / 2;
        int lightWidth = light.getWidth();
        int lightHeight = light.getHeight();


        if (explorer.isWieldingSword()) {
            lightSubX -= 100;
            lightSubY -= 100;
            lightWidth = lightWidth * 2;
            lightHeight = lightHeight * 2;
        }

        if (lightSubX < 0) lightSubX = 0;
        if (lightSubY < 0) lightSubY = 0;

        BufferedImage tem = world.getSubimage(lightSubX, lightSubY, lightWidth, lightHeight);
        BufferedImage lightSight = new BufferedImage(tem.getWidth(), tem.getHeight(), tem.getType());


        Graphics2D gTem = lightSight.createGraphics();
        gTem.drawImage(tem, 0, 0, tem.getHeight(), tem.getWidth(), null);
        buffer.drawImage(darkness, 0, 0, null);
        buffer.drawImage(lightSight, lightSubX, lightSubY, null);
    }

    /**
     * Check collision.
     * priority : Wall>powerUpObj>monster>block>explorer
     *
     * @param g
     */
    private void checkCollision(GameObj g) {

        Rectangle tem = g.getRect();


        //check wall vs explorer/monster/block
        if (g instanceof Wall) {

            if (tem.intersects(explorer.getRect())) collisions.push(new Collision(g, explorer));


            for (Monsters m : monsters) {
                if (tem.intersects(m.getRect())) {
                    collisions.push(new Collision(g, m));
                }
            }

            for (Block b : blocks) {
                if (tem.intersects(b.getRect())) collisions.push(new Collision(g, b));
            }
        }

        //check powerUpObj vs explorer
        else if (g instanceof PowerUpObj) {
            if (tem.intersects(explorer.getRect())) {
                collisions.push(new Collision(g, explorer));

            }
        }


        //check monster vs block/explorer/monster
        else if (g instanceof Monsters) {
            for (Block b : blocks) {
                if (tem.intersects(b.getRect())) collisions.push(new Collision(b, g));
            }

            for (Monsters m : monsters) {
                if (m.equals(g)) continue;
                if (tem.intersects(m.getRect())) collisions.push(new Collision(g, m));
            }

            if (explorer.getRect().intersects(tem)) {
                collisions.add(new Collision(g, explorer));

            }
        }


        //block vs block/explorer
        else if (g instanceof Block) {

            for (Block b : blocks) {
                if (b.equals(g)) continue;
                if (tem.intersects(b.getRect())) collisions.push(new Collision(g, b));
            }
            if (tem.intersects(explorer.getRect())) {
                collisions.push(new Collision(g, explorer));
//                System.out.println(" block collision");

            }
        }
        //door vs explorer
        if (g instanceof Door && tem.intersects(explorer.getRect())) {
            collisions.push(new Collision(g, explorer));
        }

    }

    /**
     * Delete game objects after collision;
     *
     * @param g
     */
    public void deleteCollisionObj(GameObj g) {

        if (g == null) return;
        try {
            if (g instanceof PowerUpObj) powerUpObjs.remove(g);
            else if (g instanceof Monsters) monsters.remove(g);


        } catch (Exception e) {
            System.out.println("getCollisionResultEX: " + e.getMessage());
        }
    }


}