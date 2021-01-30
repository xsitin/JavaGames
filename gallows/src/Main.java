import javax.swing.*;
import java.awt.*;

enum Result {Win, Lose, Guessed, NotGuessed}

public class Main {
    public static void main(String[] args) {
        String word = JOptionPane.showInputDialog("Input word");
        String lives = JOptionPane.showInputDialog("Input lives count");
        Game game = new Game(word, Integer.parseInt(lives));
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setBounds(0, 0, 700, 800);
        window.setVisible(true);

        JPanel panel = new JPanel();

        JLabel livesLabel = new JLabel() {
            @Override
            public void paint(Graphics g) {
                setText(String.valueOf(game.GetLives()));
                super.paint(g);
            }
        };
        livesLabel.setText(String.valueOf(game.GetLives()));
        livesLabel.setBounds(0, 0, 100, 100);
        livesLabel.setLocation(0, 0);
        livesLabel.setVisible(true);

        JLabel remainderLabel = new InRectangleWordPainter(game);
        remainderLabel.setText(game.GetRemainder());
        remainderLabel.setLocation(100, 0);

        GallowDrawer pic = new GallowDrawer(game);
        pic.setBounds(50, 100, 600, 600);

        window.addKeyListener(new GallowKeyListener(window, game));
        panel.setBounds(0, 0, 1000, 1000);
        panel.add(remainderLabel);
        panel.add(livesLabel);
        panel.add(pic);
        window.add(panel);
        window.repaint();
        panel.setLayout(null);
    }
}




