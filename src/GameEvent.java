import GameObj.Explorer;

import java.awt.*;
import java.awt.image.BufferedImage;

public class GameEvent {

    private Player player;
    private Explorer explorer;
    private boolean win, gameOver;
    private BufferedImage gameOverImg, winImg;
    private int x, y;


    public GameEvent(Player player, Explorer explorer, BufferedImage gameOverImg, BufferedImage winImg, int x, int y) {
        this.player = player;
        this.gameOverImg = gameOverImg;
        this.winImg = winImg;
        this.explorer = explorer;

        win = false;
        gameOver = false;
    }

    /**
     * @param
     */
    public void updateEvent() {
        if (player.getLives() == 0) gameOver = true;
        if (player.isUnlockDoor()) win = true;

    }


    public boolean isGameOver() {
        return gameOver;
    }

    public boolean isWin() {
        return win;
    }


}
