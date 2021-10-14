import GameObj.GameObj;
import GameObj.Monsters.Beetle;
import GameObj.Monsters.Monsters;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class SoundPlayer {

    public String bettleA, blockA, clickA, dieA, gameOverA, backgroundA, powerEndA, powerStartA, scaredA, scorpion, treasure;
    Clip bettleC, blockC, clickC, dieC, gameOverC, backgroundC, powerStartC, scaredC, scorpionC, treasureC;


    public void playBackgroundMusic() throws Exception {

        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("resources/Music.wav"));

        backgroundC = AudioSystem.getClip();
        backgroundC.open(audioInputStream);
        backgroundC.loop(Clip.LOOP_CONTINUOUSLY);
    }


    /**
     * Event sound effect
     *
     * @param event
     * @throws Exception
     */
    public void playSound(String event) throws Exception {

        AudioInputStream audioInputStream;

        switch (event) {

            case "click":
                audioInputStream = AudioSystem.getAudioInputStream(new File("resources/Click.wav"));
                break;
            case "die":
                audioInputStream = AudioSystem.getAudioInputStream(new File("resources/Die.wav"));
                break;
            case "powerStart":
                audioInputStream = AudioSystem.getAudioInputStream(new File("resources/PowerStart.wav"));
                break;

            default:
                audioInputStream = AudioSystem.getAudioInputStream(new File("resources/Click.wav"));
                System.out.println("SoundPlayer: not a support event");

        }
        Clip clip = AudioSystem.getClip();
        clip.open(audioInputStream);
        clip.start();
    }

    /**
     * Game-object sound effect
     *
     * @param g
     * @throws Exception
     */
    public void playSound(GameObj g) throws Exception {
        AudioInputStream audioInputStream;

        if (g instanceof Monsters) {
            audioInputStream = AudioSystem.getAudioInputStream(new File("resources/Beetle.wav"));
        } else {
            audioInputStream = AudioSystem.getAudioInputStream(new File(""));
        }
        Clip clip = AudioSystem.getClip();
        clip.open(audioInputStream);
        clip.start();
    }

}
