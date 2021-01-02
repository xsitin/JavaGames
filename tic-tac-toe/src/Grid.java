import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Stream;

class Grid {
    public String[][] cells = new String[3][3];
    private String previousFigure = "circle";


    public String GetNextFigure() {
        if (previousFigure.equals("circle"))
            previousFigure = "cross";
        else
            previousFigure = "circle";
        return previousFigure;
    }

    public String GetWinner() {
        for (int col = 0; col < 3; col++) {
            if (Arrays.stream(cells[col]).allMatch(Objects::nonNull))
                if (cells[col][1].equals(cells[col][0]) &&
                        cells[col][2].equals(cells[col][1]))
                    return cells[col][0];
        }
        for (int row = 0; row < 3; row++) {
            int finalRow = row;
            if (Arrays.stream(cells).allMatch(x -> x[finalRow] != null))
                if (cells[1][row].equals(cells[0][row]) &&
                        cells[2][row].equals(cells[1][row]))
                    return cells[0][row];
        }
        if (Stream.of(1, 2)
                .allMatch(x -> cells[x][x] != null && cells[x][x].equals(cells[x - 1][x - 1])))
            return cells[0][0];
        if (Stream.of(1, 2).allMatch(x -> cells[2 - x][x] != null &&
                cells[3 - x][x - 1] != null &&
                cells[3 - x][x - 1].equals(cells[2 - x][x])))
            return cells[0][2];
        for (int i = 0; i < cells.length; i++)
            for (int j = 0; j < cells[i].length; j++) {
                String cell = cells[i][j];
                if (cell == null)
                    return "nobody";

            }
        return "ничья";
    }

}