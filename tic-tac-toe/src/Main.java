import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        Grid grid = new Grid();
        frame.add(new GridComponent(grid));
        frame.setBounds(0, 0, 620, 640);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Timer drawTimer = new Timer(50, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.repaint();
            }
        });

        frame.setVisible(true);
        frame.addKeyListener(new XCrossKeyListener(grid, frame));
        frame.setResizable(false);
        drawTimer.start();
    }
}