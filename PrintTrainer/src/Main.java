import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Main {
    public static void main(String[] args) {
        JOptionPane.showMessageDialog(null, "are u ready?");
        var window = new JFrame();
        var panel = new JPanel();
        window.add(panel);
        var game = new Game();

        var wordPainter = new WordDrawer(game);
        wordPainter.setBounds(50,50,800,400);
        panel.add(wordPainter);
        panel.setLayout(null);
        window.addKeyListener(new PrintTrainerKeyListener(window, game));
        window.setBounds(0, 0, 1000, 500);
        window.setVisible(true);
        window.setResizable(false);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                window.dispose();
                JOptionPane.showMessageDialog(null, game.getStatistic());
                System.exit(0);
            }
        }, 60000);

    }
}

class PrintTrainerKeyListener implements KeyListener {
    private JFrame frame;
    private Game game;

    PrintTrainerKeyListener(JFrame frame, Game game) {
        this.frame = frame;
        this.game = game;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        game.TryInputChar(e.getKeyChar());
        frame.repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}

class Game {
    private final Random random = new Random();
    boolean lastWasMissclick;
    private String[] words;
    private String CurrentWord;
    private int CurrentCharIndex;
    private int enteredCharsCount;
    private int missclickCount;

    public Game() {
        try {
            words = new String(
                    Files.readAllBytes(
                            Paths.get("PrintTrainer/src/words.txt")))
                    .split("\r\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
        CurrentWord = words[random.nextInt(words.length)];
    }

    public boolean TryInputChar(char ch) {
        enteredCharsCount++;
        lastWasMissclick = false;
        if (CurrentWord.charAt(CurrentCharIndex) == ch) {
            if (CurrentCharIndex < (CurrentWord.length() - 1))
                CurrentCharIndex++;
            else {
                CurrentWord = words[random.nextInt(words.length)];
                CurrentCharIndex = 0;
            }
            return true;
        }
        lastWasMissclick = true;
        missclickCount++;
        return false;
    }

    public String getCurrentWord() {
        return CurrentWord;
    }

    public int getCurrentCharIndex() {
        return CurrentCharIndex;
    }

    public String getStatistic() {
        if (enteredCharsCount == 0)
            return "you have not entered anything!";
        return "Entered chars:" + enteredCharsCount +
                "\nChars in second:" + enteredCharsCount / 60 +
                "\nmissclicks count:" + missclickCount +
                "\naccuracy:" + ((enteredCharsCount - missclickCount) * 100) / enteredCharsCount + "%";
    }

}