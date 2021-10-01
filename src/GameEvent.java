public class GameEvent {

    private Player player;
    private boolean win, gameOver;


    public GameEvent(Player player){
        this.player =player;
        win = false;
        gameOver = false;
    }

    private void updateEvent(Player player){
        if(player.getLives() == 0)      gameOver = true;
        
    }


    public boolean isGameOver(){
        return gameOver;
    }

    public boolean isWin(){
        return win;
    }
}
