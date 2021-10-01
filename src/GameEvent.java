import GameObj.Explorer;

import java.awt.image.BufferedImage;

public class GameEvent {

    private Player player;
    private Explorer explorer;
    private boolean win, gameOver;
    private BufferedImage gameOverImg,winImg;


    public GameEvent(Player player, Explorer explorer, BufferedImage gameOverImg, BufferedImage winImg){
        this.player =player;
        this.gameOverImg = gameOverImg;
        this.winImg = winImg;
        this.explorer = explorer;

        win = false;
        gameOver = false;
    }

    /**
     *
     * @param player
     */
    public void updateEvent(Player player){
        if(player.getLives() == 0)      gameOver = true;


    }


    public boolean isGameOver(){
        return gameOver;
    }

    public boolean isWin(){
        return win;
    }


}
