import javax.swing.*;
import java.awt.*;

class WordDrawer extends JLabel {
    int rectangleSize;
    Game game;

    public WordDrawer(Game game) {
        this.game = game;
        rectangleSize = 60;
        setSize(2000, 100);
    }

    @Override
    public void paint(Graphics g) {
        char[] charArray = game.getCurrentWord().toCharArray();
        g.setFont(new Font("TimesRoman", Font.BOLD, 45));
        g.setColor(Color.green);
        for (int k = 0; k < game.getCurrentCharIndex(); k++)
            g.fillRect(k * rectangleSize, 0, rectangleSize, rectangleSize);
        if (game.lastWasMissclick) {
            g.setColor(Color.red);
            g.fillRect(game.getCurrentCharIndex() * rectangleSize, 0, rectangleSize, rectangleSize);
            g.setColor(Color.black);
        }
        g.setColor(Color.black);
        for (int i = 0, charArrayLength = charArray.length; i < charArrayLength; i++) {
            char letter = charArray[i];
            g.setColor(Color.black);
            g.drawRect(i * rectangleSize, 0, rectangleSize, rectangleSize);
            g.drawString(Character.toString(letter), i * rectangleSize + 5, 35);
        }
    }
}