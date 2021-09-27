package de.janbraunsdorff.ase.layer.presentation.console.expert.printing.charts;


import static de.janbraunsdorff.ase.layer.presentation.console.expert.printing.charts.ComposedChartElements.EMPTY;
import static de.janbraunsdorff.ase.layer.presentation.console.expert.printing.charts.ComposedChartElements.FULL;
import static de.janbraunsdorff.ase.layer.presentation.console.expert.printing.charts.ComposedChartElements.HALF;
import static de.janbraunsdorff.ase.layer.presentation.console.expert.printing.charts.ComposedChartElements.X;
import static de.janbraunsdorff.ase.layer.presentation.console.expert.printing.charts.ComposedChartElements.Y;

public class Grid {

    private final String[][] grid;
    private final int height = 20;

    public Grid(int width) {
        this.grid = new String[width][height + 2];
    }

    private void addHorizontalLine(int column, String value, int start, int end) {
        for (int i = start; i < end; i++) {
            grid[column][i] = value;
        }
    }

    public void addEmptyLine(int column, int start, int end) {
        this.addHorizontalLine(column, EMPTY.getSymbol(), start, end);
    }

    public void addHalfBlock(int column, int index) {
        this.addHorizontalLine(column, HALF.getSymbol(), index, index + 1);
    }

    public void addFullLine(int column, int start, int end) {
        this.addHorizontalLine(column, FULL.getSymbol(), start, end);
    }

    public void addY() {
        this.addHorizontalLine(0, Y.getSymbol(), 0, this.height);
    }

    public void addYPoints(int row, int value) {
        grid[0][row] = String.format("%5s€ %s%-3s", value, ChartElement.Horizontal.getSymbol(), ChartElement.VerticalDataPoint.getSymbol());
    }

    public void addX(String[] key) {
        grid[0][20] = String.format("%5s€ %s%s%s%s", 0, ChartElement.Horizontal.getSymbol(), ChartElement.Edge.getSymbol(), ChartElement.Horizontal.getSymbol(), ChartElement.Horizontal.getSymbol());
        grid[0][21] = String.format("%11s", "");
        for (int i = 1; i < grid.length; i++) {
            grid[i][20] = X.getSymbol();
            grid[i][21] = String.format(" %-5s ", padXValue(key[i - 1]));
        }
    }

    private String padXValue(String s) {
        if (s.length() > 5) {
            return s.substring(0, 5);
        }
        return s;
    }

    public int getHeight() {
        return height;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < grid[0].length; i++) {
            for (String[] strings : grid) {
                builder.append(strings[i]);
            }
            builder.append("\n");
        }

        return builder.toString();
    }
}
