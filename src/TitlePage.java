import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class TitlePage extends SoundPlayer {

//    private static final int SCREEN_WIDTH = 1024;
//    private static final int SCREEN_HEIGHT = 720;

    private static final int SCREEN_WIDTH = 640;
    private static final int SCREEN_HEIGHT = 480;

    BufferedImage startImg, loadImg, helpImg, scoresImg, quitImg, startScreenImg;
    JFrame mainJF;

    JFrame startScreen = new JFrame("Start");
    JButton start, load, help, scores, quit;

    TitlePage(JFrame jFrame, BufferedImage start, BufferedImage load, BufferedImage help, BufferedImage scores, BufferedImage quit, BufferedImage startScreen) {
        this.helpImg = help;
        this.startImg = start;
        this.loadImg = load;
        this.scoresImg = scores;
        this.quitImg = quit;
        this.mainJF = jFrame;
        this.startScreenImg = startScreen;


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
        jPanel.setBackground(new Color(0, 0, 0, 0));
        jPanel.setPreferredSize(new Dimension(100, 500));


        JPanel FixedPanel = new JPanel(new GridBagLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(startScreenImg, 0, 0, null);
            }
        };


        FixedPanel.setPreferredSize(new Dimension(500, 500));
        FixedPanel.setOpaque(false);


        jPanel.add(start);
        jPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        jPanel.add(quit);


        FixedPanel.add(jPanel);


        startScreen.add(FixedPanel);

        startScreen.pack();

        startScreen.setBackground(Color.BLACK);
        startScreen.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);

        startScreen.setResizable(false);
        startScreen.setLocationRelativeTo(null);
        startScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        startScreen.setVisible(true);


    }


}
