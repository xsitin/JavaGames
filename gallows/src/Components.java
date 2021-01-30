import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

class GallowDrawer extends JComponent {
    Game game;
    List<Image> images = new ArrayList<>();

    public GallowDrawer(Game game) {
        this.game = game;
        File directory = new File("gallows/src/Pictures");
        for (File file : Objects.requireNonNull(directory.listFiles()))
            if (file.isFile() && file.getName().endsWith(".png")) try {
                images.add(ImageIO.read(file));
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    @Override
    public void paint(Graphics g) {
        int picNumber = (int) (10 * (game.GetLives() / (double) game.GetMaxLives())) - 1;
        if (picNumber < 0)
            return;
        Image img = images.get(picNumber);
        g.drawImage(img, 0, 0, null);
    }
}

class InRectangleWordPainter extends JLabel {
    int rectangleSize;
    Game game;

    public InRectangleWordPainter(Game game) {
        this.game = game;
        rectangleSize = 60;
        setSize(2000, 100);
    }

    @Override
    public void paint(Graphics g) {
        setText(game.GetRemainder());
        char[] charArray = getText().toCharArray();
        g.setFont(new Font("TimesRoman", Font.BOLD, 45));
        for (int i = 0, charArrayLength = charArray.length; i < charArrayLength; i++) {
            char letter = charArray[i];
            g.drawRect(i * rectangleSize, 0, rectangleSize, rectangleSize);
            g.drawString(Character.toString(letter), i * rectangleSize + 5, 35);
        }
    }
}


class GallowKeyListener implements KeyListener {
    Game game;
    JFrame frame;

    public GallowKeyListener(JFrame frame, Game game) {
        this.game = game;
        this.frame = frame;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        Result res = game.TryGuess(e.getKeyChar());
        frame.repaint();
        if (res == Result.Win) {
            JOptionPane.showMessageDialog(null, "You win");
            System.exit(0);
        }
        if (res == Result.Lose) {
            JOptionPane.showMessageDialog(null, "You lose");
            System.exit(0);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
