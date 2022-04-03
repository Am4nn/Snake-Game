import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class MyPanel extends JPanel implements KeyListener, ActionListener {

    final static int PHeight = 600;
    final static int PWidth = 600;
    final static int sUnitSize = 20;
    Color snakeColor = new Color(0,190,50);
    int len = 5;
    int[] X = new int[PWidth];
    int[] Y = new int[PHeight];
    int[] XPrevState = new int[PWidth];
    int[] YPrevState = new int[PHeight];
    char snakeDir = 'd';
    Timer timer;
    int speedOfSnake;
    Random random = new Random();
    int hA = sUnitSize*2;
    int wA = sUnitSize*2;
    int xA = random.nextInt((PWidth-sUnitSize)/sUnitSize) * 20;
    int yA = random.nextInt((PHeight - sUnitSize - 60) / sUnitSize) * sUnitSize + 60;
    int Score = 0;
    MyFrame instanceOfMyFrame;
    boolean gameStopped = false;
    int Flag = 0;
    int inFlag = 0;
    String scoreStr;
    Clip clip;
    Image snD = new ImageIcon(".File_en\\SnakeMouthD.png").getImage().getScaledInstance(sUnitSize,sUnitSize,Image.SCALE_DEFAULT);
    Image snA = new ImageIcon(".File_en\\SnakeMouthA.png").getImage().getScaledInstance(sUnitSize,sUnitSize,Image.SCALE_DEFAULT);
    Image snW = new ImageIcon(".File_en\\SnakeMouthW.png").getImage().getScaledInstance(sUnitSize,sUnitSize,Image.SCALE_DEFAULT);
    Image snS = new ImageIcon(".File_en\\SnakeMouthS.png").getImage().getScaledInstance(sUnitSize,sUnitSize,Image.SCALE_DEFAULT);
    Image Apple = new ImageIcon(".File_en\\Apple.png").getImage().getScaledInstance(wA,hA,+Image.SCALE_DEFAULT);

    MyPanel(int speed) {

        speedOfSnake = speed;
        for (int i = 0; i < len; i++) {
            X[i] = 200 - sUnitSize*i;
            Y[i] = 100;
        }
        this.setPreferredSize(new Dimension(PWidth, PHeight));
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
        this.requestFocusInWindow();
        this.addKeyListener(this);
        timer = new Timer(speedOfSnake,this);
        timer.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (inFlag != 1) {
            g.setColor(Color.RED);
            g.setFont(new Font("MV Boli", Font.PLAIN, 55));
            scoreStr = "Score : " + Score;
            g.drawString(scoreStr, 40, 50);
            g.setColor(Color.GREEN);
            g.drawLine(0, 60, PWidth, 60);
            g.drawImage(Apple, xA, yA, null);
            drawSnake(g);
        }
        if (Flag == 1){
            if (inFlag == 0) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            else{
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
                g.setColor(Color.BLACK);
                g.fillRect(0, 62, 600, 580);
                g.setFont(new Font("Goudy Stout", Font.PLAIN, 60));
                g.setColor(new Color(random.nextInt(255) + 1, random.nextInt(255) + 1, random.nextInt(255) + 1));
                g.drawString(scoreStr, 0,180);
                g.setFont(new Font("Goudy Stout", Font.PLAIN, 70));
                g.setColor(new Color(random.nextInt(255) + 1, random.nextInt(255) + 1, random.nextInt(255) + 1));
                g.drawString("Game", 20, 350);
                g.drawString("  Over", 20, 450);
                inFlag = 1;
                repaint();
        }
        if (gameStopped && Flag != 1) {
            g.setColor(Color.BLUE);
            g.drawOval(X[0]-sUnitSize, Y[0]-sUnitSize, sUnitSize * 3, sUnitSize * 3);
            Flag = 1;
            repaint();
        }
    }

    public void drawSnake(Graphics g) {
        g.setColor(Color.BLUE);
        for (int i = 0; i < len; i++) {
            if (i == 0) {
                Image s = new ImageIcon().getImage();
                if (snakeDir == 'd')
                    s = snD;
                if (snakeDir == 'a')
                    s = snA;
                if (snakeDir == 'w')
                    s = snW;
                if (snakeDir == 's')
                    s = snS;
                g.drawImage(s, X[i], Y[i], null);
            }
            else {
                g.setColor(snakeColor);
                g.fillRect(X[i], Y[i], sUnitSize, sUnitSize);
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        char keyIn = e.getKeyChar();
        keyIn = Character.toLowerCase(keyIn);
        if (snakeDir != 'a' && keyIn == 'd')
            snakeDir = keyIn;
        if (snakeDir != 'd' && keyIn == 'a')
            snakeDir = keyIn;
        if (snakeDir != 'w' && keyIn == 's')
            snakeDir = keyIn;
        if (snakeDir != 's' && keyIn == 'w')
            snakeDir = keyIn;
        if (keyIn == 'f')
            Finish();
        snakeColor = new Color(random.nextInt(255)+1,random.nextInt(255)+1,random.nextInt(255)+1);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == 27){
            instanceOfMyFrame.dispose();
            new StartPage();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (snakeDir) {
            case 'd' -> {
                Changes();
                X[0] += sUnitSize;
            }
            case 'a' -> {
                Changes();
                X[0] -= sUnitSize;
            }
            case 'w' -> {
                Changes();
                Y[0] -= sUnitSize;
            }
            case 's' -> {
                Changes();
                Y[0] += sUnitSize;
            }
        }
        repaint();
        checkConditions();
        ifEat();
    }
    public void Changes() {
        for (int i = 0; i < len; i++){
            XPrevState[i] = X[i];
            YPrevState[i] = Y[i];
        }
        for (int i = len; i > 0; i--) {
            X[i] = X[i-1];
            Y[i] = Y[i-1];
        }
    }
    public void ifEat() {
        if ((X[0]==xA || X[0]==xA+sUnitSize) && (Y[0]==yA || Y[0]==yA+sUnitSize)) {

            File file = new File(".File_en\\SnakeBite.wav");
            try {
                AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
                clip = AudioSystem.getClip();
                clip.open(audioStream);
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                e.printStackTrace();
            }
            clip.start();
            Score += 1;
            len += 1;
            RandomPositionForSnake();
        }
    }
    public void checkConditions() {
        if (X[0] < 0) {
            X[0] = PWidth - sUnitSize;
        }
        if (X[0] >= PWidth) {
            X[0] = 0;
        }
        if (Y[0] < 60) {
            Y[0] = PHeight - sUnitSize;
        }
        if (Y[0] >= PHeight) {
            Y[0] = 60;
        }
        for (int i = 1; i < len; i++) {
            if (X[0] == X[i] && Y[0] == Y[i]){
                // Call function to stop the game
                Finish();
                break;
            }
        }
    }

    public void RandomPositionForSnake() {
        // Still to write
        xA = random.nextInt((PWidth - sUnitSize) / sUnitSize) * sUnitSize;
        yA = random.nextInt((PHeight - sUnitSize - 60) / sUnitSize) * sUnitSize + 60;
    }

    public void Finish() {
        timer.stop();
        for (int i = 0; i < len; i++){
            X[i] = XPrevState[i];
            Y[i] = YPrevState[i];
        }
        gameStopped = true;
        repaint();
    }
}