import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    public static void main(String[] args) {
        //создаем окно
        JFrame frame = new JFrame();
        //создаем игру
        Game game = new Game();
        //создаем и добавляем сетку для отрисвки
        frame.add(new GridComponent(game));
        //задаем размеры и расположение окна
        frame.setBounds(0, 0, 620, 640);
        //устанавливаем стандартное действие при нажатии крестика
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //создаем таймер для перерисовки содержимого окна раз в 50 мс
        Timer drawTimer = new Timer(50, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.repaint();
            }
        });
        //делаем окно видимым
        frame.setVisible(true);
        //добавляем обработчик для клавиатуры
        frame.addKeyListener(new XCrossKeyListener(game, frame));
        //делаем окно неизменяемым по размерам
        frame.setResizable(false);
        //запускаем отрисовку
        drawTimer.start();
    }
}