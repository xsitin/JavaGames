import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

class XCrossKeyListener implements KeyListener {
    private final Grid grid;
    private final JFrame parent;

    public XCrossKeyListener(Grid grid, JFrame parent) {
        this.grid = grid;
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
            if (grid.cells[i][number % 3] == null)
                grid.cells[i][number % 3] = grid.GetNextFigure();
        }
        if (!grid.GetWinner().equals("nobody")) {
            parent.dispose();
            JOptionPane.showMessageDialog(null, grid.GetWinner());
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