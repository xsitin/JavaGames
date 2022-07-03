import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Stream;

class Game {
    public String[][] cells = new String[3][3];
    private String previousFigure = "circle";


    //метод для получения следующего "фигуры"
    public String GetNextFigure() {
        if (previousFigure.equals("circle"))
            previousFigure = "cross";
        else
            previousFigure = "circle";
        return previousFigure;
    }

    //метод для проверки наличия победителя
    public String GetWinner() {
        //перебор вариантов по вертикали
        for (int col = 0; col < 3; col++) {
            //проверяем, что в колонке заданяты все клетки
            if (Arrays.stream(cells[col]).allMatch(Objects::nonNull))
                //проверяем, что занятые клетки имеют одинаковые значения
                if (cells[col][1].equals(cells[col][0]) &&
                        cells[col][2].equals(cells[col][1]))
                    return cells[col][0];
        }
        //перебор вариантов по горизонтали
        for (int row = 0; row < 3; row++) {
            //пременная нужна из-за особенностей работы с лямбдами
            int finalRow = row;
            //проверяем, что в строке заняты все клетки
            if (Arrays.stream(cells).allMatch(x -> x[finalRow] != null))
                //проверяем, что занятые клетки имеют одинаковые значения
                if (cells[1][row].equals(cells[0][row]) &&
                        cells[2][row].equals(cells[1][row]))
                    return cells[0][row];
        }
        //проверка вариантов по диагонали
        if (Stream.of(1, 2)
                .allMatch(x -> cells[x][x] != null && cells[x][x].equals(cells[x - 1][x - 1])))
            return cells[0][0];
        if (Stream.of(1, 2).allMatch(x -> cells[2 - x][x] != null &&
                cells[3 - x][x - 1] != null &&
                cells[3 - x][x - 1].equals(cells[2 - x][x])))
            return cells[0][2];
        //проверка на наличие свободных клеток
        for (String[] lines : cells)
            for (String cell : lines) {
                if (cell == null)
                    return "nobody";

            }
        return "ничья";
    }

}