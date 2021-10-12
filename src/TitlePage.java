import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class TitlePage extends SoundPlayer {

    private static final int SCREEN_WIDTH = 1024;
    private static final int SCREEN_HEIGHT = 720;

    BufferedImage startImg, loadImg, helpImg, scoresImg, quitImg;
    JFrame mainJF;

    JFrame startScreen = new JFrame("Start");
    JButton start, load, help, scores, quit;

    TitlePage(JFrame jFrame, BufferedImage start, BufferedImage load, BufferedImage help, BufferedImage scores, BufferedImage quit) {
        this.helpImg = help;
        this.startImg = start;
        this.loadImg = load;
        this.scoresImg = scores;
        this.quitImg = quit;
        this.mainJF = jFrame;


    }


    void setUp() {


        Icon startI = new ImageIcon(startImg);
        Icon quitI = new ImageIcon(quitImg);


        start = new JButton(startI);
        quit = new JButton(quitI);

        start.setPreferredSize(new Dimension(startImg.getWidth(), startImg.getHeight()));
        quit.setPreferredSize(new Dimension(quitImg.getWidth(), quitImg.getHeight()));

        start.addActionListener(listener -> {
            try {
                System.out.println("listener");
                this.playSound("click");
            } catch (Exception e) {
                e.printStackTrace();
            }

            startScreen.setVisible(false);
            mainJF.setVisible(true);

        });

        quit.addActionListener(listener -> {
            try {
                this.playSound("click");
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.exit(0);
        });


        JPanel jPanel = new JPanel();
        jPanel.setLayout(new BoxLayout(jPanel, BoxLayout.PAGE_AXIS));
//
        JPanel FixedPanel = new JPanel(new GridBagLayout());
        FixedPanel.setPreferredSize(new Dimension(500, 500));

        jPanel.setPreferredSize(new Dimension(100, 100));

        jPanel.add(start);
        jPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        jPanel.add(quit);
        jPanel.setBackground(Color.GREEN);

        FixedPanel.add(jPanel);
        startScreen.add(FixedPanel);

        startScreen.pack();
//
////        startScreen.setBackground(Color.BLACK);
        startScreen.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        startScreen.setBackground(Color.BLACK);

        startScreen.setResizable(false);
        startScreen.setLocationRelativeTo(null);
        startScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        startScreen.setVisible(true);
//        startScreen.add(jPanel);


    }


}
