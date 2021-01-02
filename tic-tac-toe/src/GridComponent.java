import javax.swing.*;
import java.awt.*;

class GridComponent extends JComponent {
    Grid grid;

    public GridComponent(Grid grid) {
        this.grid = grid;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.black);
        g.fillRect(196, 0, 4, 600);
        g.fillRect(396, 0, 4, 600);
        g.fillRect(0, 196, 600, 4);
        g.fillRect(0, 396, 600, 4);
        String[][] cells = grid.cells;
        for (int rowNumber = 0, cellsLength = cells.length; rowNumber < cellsLength; rowNumber++) {
            String[] line = cells[rowNumber];
            for (int colNumber = 0, lineLength = line.length; colNumber < lineLength; colNumber++) {
                String cell = line[colNumber];
                if (cell == null)
                    continue;
                if (cell.equals("circle")) {
                    g.setColor(Color.red);
                    g.drawOval(colNumber * 200, rowNumber * 200, 200, 200);
                } else if (cell.equals("cross")) {
                    g.setColor(Color.blue);
                    int[] xPoints = new int[]{
                            colNumber * 200,
                            colNumber * 200 + 100,
                            colNumber * 200,
                            colNumber * 200 + 200,
                            colNumber * 200 + 100,
                            colNumber * 200 + 200,
                    };
                    int[] yPoints = new int[]{
                            rowNumber * 200,
                            rowNumber * 200 + 100,
                            rowNumber * 200 + 200,
                            rowNumber * 200,
                            rowNumber * 200 + 100,
                            rowNumber * 200 + 200,

                    };
                    g.drawPolyline(xPoints, yPoints, 6);
                }
            }

        }
    }
}