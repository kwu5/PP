package GameObj.Walls;

import GameObj.GameObj;
import GameObj.Explorer;

import java.awt.image.BufferedImage;

public class Block extends GameObj {

    private int type;
    private boolean hitWall, isPushed, atStartPt;
    private int startX, startY;
    private Explorer explorer;
    private int speed;


    //verify type using 0,1,2 -> block, vertBlock, horBlocks
    public Block(int x, int y, BufferedImage img, int type, Explorer explorer) {
        super(x, y, img);
        this.type = type;
        hitWall = false;
        isPushed = false;
        atStartPt = true;
        startX = x;
        startY = y;
        speed = 1;
        this.explorer = explorer;
    }




    public int getType(){
        return type;
    }

    public boolean getHitwall(){
        return hitWall;
    }

    public int getDirection(){
        if(type ==0) return 0;
        else if(type ==1)   return this.y - startY;
        else return this.x - startX;
    }

    public void pushLeft(int speed){
        this.x -= speed;
    }
    public void pushRight(int speed){
        this.x += speed;
    }
    public void pushUp(int speed){
        this.y += speed;
    }
    public void pushDown(int speed){
        this.y -= speed;
    }

    public void moveBack(){
        System.out.println("moveback call");
        char direction = explorer.getDirection();
        int exSpeed = explorer.getSpeed();
        switch (direction){
            case 'l':
                pushRight(exSpeed);
                break;
            case 'r':
                pushLeft(exSpeed);
                break;
            case 'u':
                pushDown(exSpeed);
                break;
            case 'd':
                pushUp(exSpeed);
                break;
            default:
        }


    }

    public void backToStartPt(){
        int direction = getDirection();
        //vertical
        if(type == 1){
            while(direction != 0 ){
                if(direction <0 )           pushDown(speed);
                else pushUp(speed);
                direction = getDirection();
            }
        }
        //horizontal
        else {
            while (direction !=0 ){
                if(direction >0)        pushLeft(speed);
                else pushRight(speed);
                direction = getDirection();
            }

        }
    }

    public void move(){

        System.out.println("move call");
        int direction = explorer.getDirection();
//        System.out.println(direction);

        if(hitWall)     moveBack();
        else{
            switch (direction){
                case 'l':
                    if(type == 2)           pushLeft(explorer.getSpeed());
                    break;
                case 'r':
                    if(type == 2)           pushRight(explorer.getSpeed());
                    break;
                case 'u':
                    if(type == 1)           pushDown(explorer.getSpeed());
                    break;
                case 'd':
                    if(type == 1)           pushUp(explorer.getSpeed());
                    break;
                default:
            }
        }

    }

    @Override
    public void collision(GameObj g) {
        System.out.println("collision call "+ g.getClass().getName());
        if(g instanceof Wall || g instanceof Block){
            hitWall = true;
        }else if (g instanceof Explorer){
            isPushed = true;
        }
        move();
    }

    @Override
    public void update() {
            updateRect();
    }


}
