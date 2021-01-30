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
        if (0 < (playerWay + direction) && (playerWay + direction) < 4)
            playerWay += direction;
    }

    void update() {
        ArrayList<Car> toRemove = new ArrayList<>();
        for (Car car : cars) {
            car.down();
            if (car.getY() > 510) {
                toRemove.add(car);
                Score++;
            }
        }
        cars.removeAll(toRemove);
    }

    boolean checkCrash() {
        for (Car car : cars)
            if (car.way == playerWay && car.getY() >= 380)
                return true;
        return false;
    }

    Car spawnCar() {
        Car car = new Car();
        cars.add(car);
        return car;
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
