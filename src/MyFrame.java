import javax.swing.*;
import java.awt.*;

public class MyFrame extends JFrame {

    MyFrame(int speed){
        MyPanel pan = new MyPanel(speed);
        this.add(pan);
        pan.instanceOfMyFrame = this;
        this.setTitle("Snake 2D");
        Image logo = new ImageIcon(".File_en\\SnakeLogo.png").getImage();
        this.setIconImage(logo);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}