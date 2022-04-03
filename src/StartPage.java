import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartPage extends JFrame implements ActionListener {

    JCheckBox b1;
    JCheckBox b2;
    JCheckBox b3;

    StartPage(){
        this.setTitle("Snake 2D");
        Image logo = new ImageIcon(".File_en\\SnakeLogo.png").getImage();
        this.setIconImage(logo);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.getContentPane().setBackground(Color.BLACK);
        this.setResizable(false);
        this.setSize(600,650);
        this.setLayout(null);

        JLabel l = new JLabel();
        l.setBounds(20, 30, 600, 70);
        l.setText("Snake 2D");
        l.setForeground(Color.RED);
        l.setFont(new Font("Goudy Stout", Font.PLAIN, 65));

        JLabel l1 = new JLabel();
        l1.setBounds(20, 100+15, 600, 50);
        l1.setText("Instructions : ");
        l1.setForeground(Color.ORANGE);
        l1.setFont(new Font("FZShuTi", Font.BOLD, 50));

        JLabel l2 = new JLabel();
        l2.setBounds(30, 145+35, 600, 45);
        l2.setText("In Game - ");
        l2.setForeground(Color.BLUE);
        l2.setFont(new Font("FZShuTi", Font.PLAIN, 45));

        JLabel l3 = new JLabel();
        l3.setBounds(50, 195+50, 600, 40);
        l3.setText("Press 'f' key to stop the");
        l3.setForeground(Color.GREEN);
        l3.setFont(new Font("FZShuTi", Font.PLAIN, 40));

        JLabel l4 = new JLabel();
        l4.setBounds(75, 230+50, 600, 45);
        l4.setText("game and goto score screen");
        l4.setForeground(Color.GREEN);
        l4.setFont(new Font("FZShuTi", Font.PLAIN, 40));

        JLabel l5 = new JLabel();
        l5.setBounds(50, 290+50, 600, 45);
        l5.setText("Press 'ESC' key to stop the");
        l5.setForeground(Color.GREEN);
        l5.setFont(new Font("FZShuTi", Font.PLAIN, 40));

        JLabel l6 = new JLabel();
        l6.setBounds(75, 325+50, 600, 45);
        l6.setText("game and goto HomePage");
        l6.setForeground(Color.GREEN);
        l6.setFont(new Font("FZShuTi", Font.PLAIN, 40));

        JLabel l7 = new JLabel();
        l7.setBounds(20, 325+50+50+20-10, 600, 60);
        l7.setText("Choose Level :");
        l7.setForeground(Color.ORANGE);
        l7.setFont(new Font("FZShuTi", Font.BOLD, 55));

        b1 = new JCheckBox();
        b1.setFocusable(false);
        b1.setBounds(20, 520, 170, 60);
        b1.setText("Easy");
        b1.setFont(new Font("MV Boli", Font.PLAIN, 35));
        b1.setFocusable(false);
        b1.setVerticalAlignment(JLabel.CENTER);
        b1.setHorizontalAlignment(JLabel.CENTER);
        b1.addActionListener(this);

        b2 = new JCheckBox();
        b2.setFocusable(false);
        b2.setBounds(20+170+20, 520, 170, 60);
        b2.setText("Medium");
        b2.setFont(new Font("MV Boli", Font.PLAIN, 35));
        b2.setFocusable(false);
        b2.setVerticalAlignment(JLabel.CENTER);
        b2.setHorizontalAlignment(JLabel.CENTER);
        b2.addActionListener(this);

        b3 = new JCheckBox();
        b3.setFocusable(false);
        b3.setBounds(20+170+20+170+20, 520, 170, 60);
        b3.setText("Hard");
        b3.setFont(new Font("MV Boli", Font.PLAIN, 35));
        b3.setFocusable(false);
        b3.setVerticalAlignment(JLabel.CENTER);
        b3.setHorizontalAlignment(JLabel.CENTER);
        b3.addActionListener(this);

        this.add(l7);
        this.add(l6);
        this.add(l5);
        this.add(l4);
        this.add(l3);
        this.add(l2);
        this.add(l1);
        this.add(l);
        this.add(b3);
        this.add(b2);
        this.add(b1);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1){
            this.dispose();
            MyFrame f = new MyFrame(200);
        }
        if (e.getSource() == b2){
            this.dispose();
            MyFrame f = new MyFrame(100);
        }
        if (e.getSource() == b3){
            this.dispose();
            MyFrame f = new MyFrame(40);
        }
    }
}
