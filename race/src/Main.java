import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        JOptionPane.showMessageDialog(null, "Start!");
        var window = new JFrame();
        var panel = new JPanel();
        panel.setLayout(null);
        window.add(panel);
        var game = new Game();
        var map = new JComponent() {
            @Override
            public void paint(Graphics g) {
                try {
                    g.drawImage(ImageIO.read(new File("race/src/map.png")), 0, 0, null);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        map.setBounds(0, 0, 1000, 700);
        var playerCar = new PlayerCar(game);
        playerCar.setBounds(120, 500, 200, 200);
        panel.add(playerCar, 0);
        panel.add(map, -1);

        window.setBounds(100, 100, 1010, 740);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setVisible(true);
        window.addKeyListener(new RaceKeyListener(game, window));
        var redrawTimer = new Timer(10, e -> {
            window.repaint();
            game.update();
            if (game.checkCrash()) {
                JOptionPane.showMessageDialog(null, "Your score:" + game.Score);
                System.exit(0);
            }
            for (var car : game.cars)
                if (car.getY() > 500)
                    panel.remove(car);
        });
        var spawnCarTimer = new Timer(1000, e -> panel.add(game.spawnCar(), 0));
        redrawTimer.start();
        spawnCarTimer.start();
    }
}

class PlayerCar extends JComponent {
    Game game;

    public PlayerCar(Game game) {
        this.game = game;
    }

    @Override
    public void paint(Graphics g) {
        setLocation(Game.getWayCoordinate(game.playerWay), 500);
        g.drawImage(Game.playerCar, 0, 0, null);
    }
}

class RaceKeyListener implements KeyListener {

    Game game;
    JFrame frame;

    public RaceKeyListener(Game game, JFrame frame) {
        this.game = game;
        this.frame = frame;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT)
            game.ChangePlayerWay(-1);
        if (e.getKeyCode() == KeyEvent.VK_RIGHT)
            game.ChangePlayerWay(1);
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
