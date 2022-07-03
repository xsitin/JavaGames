import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

class XCrossKeyListener implements KeyListener {
    private final Game game;
    private final JFrame parent;

    public XCrossKeyListener(Game game, JFrame parent) {
        this.game = game;
        this.parent = parent;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        System.out.println(e.getKeyChar());
        char code = e.getKeyChar();
        if (Character.isDigit(code) && code != '0') {
            int number = Integer.parseInt(Character.toString(code));
            number--;
            int i = 2-(number / 3);
            if (game.cells[i][number % 3] == null)
                game.cells[i][number % 3] = game.GetNextFigure();
        }
        if (!game.GetWinner().equals("nobody")) {
            parent.dispose();
            JOptionPane.showMessageDialog(null, game.GetWinner());
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