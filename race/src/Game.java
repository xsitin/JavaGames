import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

class Game {
    int Score;
    int playerWay = 2;
    static Image playerCar;
    Set<Car> cars = new HashSet<>();

    static {
        try {
            playerCar = ImageIO.read(new File("race/src/greenCar.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static int getWayCoordinate(int way) {
        return way * 250 - 50;
    }

    void ChangePlayerWay(int direction) {
    }

    void update() {

    }

    boolean checkCrash() {
        return true;
    }

    Car spawnCar() {
        return null;
    }
}

class Car extends JComponent {
    static Image pic;
    static Random random = new Random();
    int way;

    public Car() {
        way = 1 + random.nextInt(3);
        setBounds(Game.getWayCoordinate(way), 0, 200, 200);
    }

    static {
        try {
            pic = ImageIO.read(new File("race/src/redCar.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void down() {
        setLocation(getX(), getY() + 5);
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(pic, 0, 0, null);
    }
}
