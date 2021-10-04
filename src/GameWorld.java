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
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;

import static javax.imageio.ImageIO.read;

public class GameWorld extends JPanel {


    //screen and gamemap size
    public static final int GAME_WIDTH = 1984;
    public static final int GAME_HEIGHT = 1024;
    private static final int SCREEN_WIDTH = 1024;
    private static final int SCREEN_HEIGHT = 720;




    private final String map1 = "resources/map1.csv";

    private Graphics2D buffer;
    private JFrame jf;
    private Player player;
    private Panel panel;
    private Explorer explorer;
    private ExplorerControl explorerControl;
    private int explorerSpeed = 3;         //set explorerSpeed

    BufferedImage worldBackgroundImg;


    private BufferedImage world;
    private Image background;
    private BufferedImage explorerUp, explorerDown, explorerLeft, explorerRight;
    private BufferedImage beetleUp, beetleDown, mummyDown, mummyLeft, mummyRight, mummyUp, scorpionLeft, scorpionRight;
    private BufferedImage block, blockHor, blockVert, wall1, wall2, doorImg;
    private BufferedImage sword, scroll, treasure1, treasure2, potion, scarab;
    private BufferedImage lives, buttonHelp, buttonLoad, buttonQuit, buttonScores, buttonStart;
    private BufferedImage congratulation, title,panelImg,light,background1, gameOver;

    private ArrayList<Monsters> monsters;
    private ArrayList<PowerUpObj> powerUpObjs;
    private ArrayList<Wall> walls;
    private ArrayList<Block> blocks;
//    private ArrayList<Collision> collisions;
    private Stack<Collision> collisions;
    private GameEvent gameEvent;
    private Door door;



//    private int tileHeight, tileWidth;



    public static void main(String[] args) {
        GameWorld gameWorld = new GameWorld();
        gameWorld.init();

        try {
            while (true) {

                gameWorld.explorer.update();
//                System.out.println(gameWorld.explorer.getX() + "     "+ gameWorld.explorer.getY());

                for (Monsters m : gameWorld.monsters) {
//                    System.out.println("monster");
                    m.update();
                    gameWorld.checkCollision(m);
                }

                for (Wall w : gameWorld.walls) {
//                    System.out.println("wall");
                    gameWorld.checkCollision(w);
                }

                for (Block b : gameWorld.blocks) {
//                    System.out.println("block");
                    b.update();
                    gameWorld.checkCollision(b);
                }

                for (PowerUpObj p:gameWorld.powerUpObjs) {
//                    System.out.println("pobj");
                    p.update();
                    gameWorld.checkCollision(p);
                }

                gameWorld.checkCollision(gameWorld.door);
//                System.out.println("door");



//                for (Collision c:gameWorld.collisions) {
//                    gameWorld.getCollisionResult(c.handleCollision());
//
//                }

                //update gameEvent and delete object that should disappear
                while(!gameWorld.collisions.empty()){
                    GameObj tem = gameWorld.collisions.pop().handleCollision(gameWorld.player);
                    gameWorld.gameEvent.updateEvent();
                    gameWorld.deleteCollisionObj(tem);
                }


                gameWorld.gameEvent.updateEvent();
                gameWorld.panel.update(gameWorld.player.getLives(),gameWorld.player.getScarabsNum(),gameWorld.player.getScore());
                gameWorld.repaint();

                //gameEvent
                if(gameWorld.gameEvent.isGameOver()){
                    break;
                }
                if(gameWorld.gameEvent.isWin()){
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

        this.jf = new JFrame("PyramidPanic ");
        world = new BufferedImage(GAME_WIDTH, GAME_HEIGHT, BufferedImage.TYPE_INT_RGB);
        try {
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

            doorImg = read(new File("resources/Door.gif"));
            lives = read(new File("resources/Lives.gif"));
            potion = read(new File("resources/Potion.gif"));
            scarab = read(new File("resources/Scarab.gif"));
            scroll = read(new File("resources/Scroll.gif"));
            sword = read(new File("resources/Sword.gif"));
            treasure1 = read(new File("resources/Treasure1.gif"));
            treasure2 = read(new File("resources/Treasure2.gif"));

            buttonHelp = read(new File("resources/Button_help.gif"));
            buttonLoad = read(new File("resources/Button_load.gif"));
            buttonStart = read(new File("resources/Button_start.gif"));
            buttonQuit = read(new File("resources/Button_quit.gif"));
            buttonScores = read(new File("resources/Button_scores.gif"));


            congratulation = read(new File("resources/Congratulation.gif"));
            light = read(new File("resources/Light.bmp"));
            title = read(new File("resources/Title.gif"));
            panelImg = read(new File("resources/Panel.gif"));
            gameOver = read(new File("resources/game_over.png"));
//            gameOver = resize(gameOver,GAME_WIDTH,GAME_HEIGHT);
//            congratulation = resize(congratulation,GAME_WIDTH,GAME_HEIGHT);

            //create the background image
            worldBackgroundImg = new BufferedImage(GAME_WIDTH, GAME_HEIGHT, BufferedImage.TYPE_INT_RGB);
            Graphics g = worldBackgroundImg.getGraphics();
            int tileWidth = background.getWidth(null);
            int tileHeight = background.getHeight(null);
            for (int i = 0; i <= (GAME_HEIGHT / tileHeight) - 1; i++)
                for (int j = 0; j <= (GAME_WIDTH / tileWidth) - 1; j++)
                    g.drawImage(background, j * tileWidth, i * tileHeight, tileWidth, tileHeight, null);
//            ImageIO.write(worldBackgroundImg, "bmp",new File("wbg.bmp"));


        } catch (IOException io) {
            System.out.println("io:" + io);

        } catch (Exception ex) {
            System.out.println("ex:" + ex);
        }


        monsters = new ArrayList<>();
        walls = new ArrayList<>();
        powerUpObjs = new ArrayList<>();
        blocks = new ArrayList<>();
//        collisions = new ArrayList<>();
        collisions = new Stack<>();



        door  =  new Door(500,700,doorImg);
        player = new Player(3);
        explorer = new Explorer(800,700 , explorerUp, explorerDown, explorerLeft, explorerRight, explorerSpeed);  //todo  default loc
        explorerControl = new ExplorerControl(explorer, KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT,
                KeyEvent.VK_RIGHT);
        gameEvent = new GameEvent(player,explorer,gameOver,congratulation, explorer.getX(),explorer.getY());
        panel = new Panel(SCREEN_WIDTH,SCREEN_HEIGHT,panelImg,lives,scarab);



        //todo test filed
        Beetle b1 = new Beetle(250, 50, beetleUp, beetleDown, explorer);
        Mummy m1 = new Mummy(300, 100, mummyUp, mummyDown, mummyLeft, mummyRight, explorer);
        Scorpion s1 = new Scorpion(180, 200, scorpionLeft, scorpionRight, explorer);
        monsters.add(b1);
        monsters.add(m1);
        monsters.add(s1);

        Wall w1 = new Wall(250, 500,wall1);
        Wall w3 = new Wall(250,0,wall2);
        Wall w2 = new Wall(450,550,wall2);
        Wall w4 = new Wall(100,200,wall2);
        Wall w5 = new Wall(300,200,wall1);


        Wall w6= new Wall(700,500,wall1);
        Wall w7 = new Wall(500,800,wall1);


        walls.add(w1);
        walls.add(w2);
        walls.add(w4);
        walls.add(w5);
        walls.add(w3);
//        walls.add(w6);
        walls.add(w7);

        Potion po1 = new Potion(270, 210, potion);
        Scarabs sc1 = new Scarabs(300, 210, scarab);
        Sword sw1 = new Sword(350, 210, sword);
        Treasure tr1 = new Treasure(400, 210, treasure1, 40);
        Treasure tr2 = new Treasure(450, 210, treasure2, 50);
        powerUpObjs.add(po1);
        powerUpObjs.add(sc1);
        powerUpObjs.add(sw1);
        powerUpObjs.add(tr1);
        powerUpObjs.add(tr2);

        Block bl0 = new NormalBlock(600, 600,block,explorer);
        Block bl1 = new VertBlock(700,700,blockVert,explorer,300,1400);
        Block bl2 = new HorBlock(800,800,blockHor,explorer,600,1600);
        blocks.add(bl0);
        blocks.add(bl1);
        blocks.add(bl2);


        this.jf.setLayout(new BorderLayout());
        this.jf.add(this);

        this.jf.addKeyListener(explorerControl);

        this.jf.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        this.jf.setResizable(false);
        this.jf.setLocationRelativeTo(null);

        this.jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.jf.setVisible(true);


    }

    private int GetSubWorldX(Explorer explorer) {
        int explorerX = explorer.getX() + explorerLeft.getWidth() / 2;
        if (explorerX - SCREEN_WIDTH / 2 <= 0) return 0;
        else if (explorerX + SCREEN_WIDTH / 2 >= GAME_WIDTH) return GAME_WIDTH - SCREEN_WIDTH;
        else return explorerX - SCREEN_WIDTH / 2;

    }

    private int GetSubWorldY(Explorer explorer) {
        int explorerY = explorer.getY() + explorerLeft.getHeight() / 2;
        if (explorerY - SCREEN_HEIGHT / 2 <= 0) return 0;
        else if (explorerY + SCREEN_HEIGHT / 2 >= GAME_HEIGHT) return GAME_HEIGHT - SCREEN_HEIGHT;
        else return explorerY - SCREEN_HEIGHT / 2;


    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        buffer = world.createGraphics();
        super.paintComponent(g2);

        buffer.drawImage(worldBackgroundImg, 0, 0, null);


        //obj drawing//todo

        //test field todo

        for (Monsters m : monsters) {
            m.drawImage(buffer);
        }

        for (PowerUpObj p : powerUpObjs) {
            p.drawImage(buffer);
        }

        for (Wall w: walls) {
            w.drawImage(buffer);
        }
        for (Block b:blocks){
            b.drawImage(buffer);
        }

        explorer.drawImage(buffer);
        door.drawImage(buffer);


        int subworldX, subworldY;
        subworldX = GetSubWorldX(explorer);
        subworldY = GetSubWorldY(explorer);

        panel.updateLoc(subworldX,subworldY);
        panel.drawImage(buffer);

        //keep the explorer in sight
        BufferedImage worldSeen = world.getSubimage(subworldX, subworldY, SCREEN_WIDTH, SCREEN_HEIGHT- panelImg.getHeight());
        g2.drawImage(worldSeen, 0, 0, null);

        //gameEvent

        int xOffset = SCREEN_WIDTH/2;
        int yOffest = SCREEN_HEIGHT/2;
        if(gameEvent.isGameOver()){
            buffer.drawImage(gameOver,subworldX,subworldY,null);
//            gameEvent.drawImage(buffer,gameOver);
        }else if(gameEvent.isWin()){
//            gameEvent.drawImage(buffer, congratulation);
            buffer.drawImage(gameOver,explorer.getX(),explorer.getY(),null);
        }

    }

    private BufferedImage resize(BufferedImage img, int weight, int height) {
        Image tmp = img.getScaledInstance(weight, height, Image.SCALE_SMOOTH);
        BufferedImage dimg = new BufferedImage(weight, height, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();

        return dimg;
    }



    /**
     * check collision.
     * priority : Wall>powerUpObj>monster>block>explorer
     * @param g
     */
    private void checkCollision(GameObj g) {

        Rectangle tem = g.getRect();


        //check wall vs explorer/monster/block
        if (g instanceof Wall) {

//            if (tem.intersects(explorer.getRect())) collisions.add(new Collision(g, explorer));
            if (tem.intersects(explorer.getRect())) collisions.push(new Collision(g, explorer));


            for (Monsters m : monsters) {
                if (tem.intersects(m.getRect())) collisions.push(new Collision(g, m));
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
                if (tem.intersects(b.getRect())) collisions.push(new Collision(g, b));
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
                collisions.push(new Collision(g,explorer));
//                System.out.println(" block collision");

            }
        }
        //door vs explorer
        if (g instanceof Door && tem.intersects(explorer.getRect())  ) {
//            System.out.println("doooooooooor");
            collisions.push(new Collision(g,explorer));
        }

    }

    /**
     * Delete game objects;
     * @param g
     */
    public void deleteCollisionObj(GameObj g){

        if(g == null)   return;
        try{
            if(g instanceof PowerUpObj)     powerUpObjs.remove(g);
            else if(g instanceof Monsters)      monsters.remove(g);




        }catch (Exception e){
            System.out.println("getCollisionResultEX: "+ e.getMessage());
        }
    }





}