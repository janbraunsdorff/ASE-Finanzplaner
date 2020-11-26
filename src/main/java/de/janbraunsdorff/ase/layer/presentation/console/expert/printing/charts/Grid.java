package de.janbraunsdorff.ase.layer.presentation.console.expert.printing.charts;

public class Grid {
    private final String full = String.format("%c%c%c%c%c%c%c", ' ', ' ', ChartElement.Block.getSymbol(), ChartElement.Block.getSymbol(), ChartElement.Block.getSymbol(), ' ', ' ');
    private final String empty = String.format("%7s", "");
    private final String half = String.format("%c%c%c%c%c%c%c", ' ', ' ', ChartElement.HalfBlock.getSymbol(), ChartElement.HalfBlock.getSymbol(), ChartElement.HalfBlock.getSymbol(), ' ', ' ');
    private final String x = String.format("%s%s%s%s%s%s%s", ChartElement.Horizontal.getSymbol(), ChartElement.Horizontal.getSymbol(), ChartElement.Horizontal.getSymbol(), ChartElement.HorizontalDataPoint.getSymbol(), ChartElement.Horizontal.getSymbol(), ChartElement.Horizontal.getSymbol(), ChartElement.Horizontal.getSymbol());
    private final String y = String.format("%8s%-3s", "", ChartElement.Vertical.getSymbol());

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
        this.addHorizontalLine(column, empty, start, end);
    }

    public void addHalfBlock(int column, int index) {
        this.addHorizontalLine(column, half, index, index + 1);
    }

    public void addFullLine(int column, int start, int end) {
        this.addHorizontalLine(column, full, start, end);
    }

    public void addY() {
        this.addHorizontalLine(0, y, 0, this.height);
    }

    public void addYPoints(int row, int value) {
        grid[0][row] = String.format("%5s€ %s%-3s", value, ChartElement.Horizontal.getSymbol(), ChartElement.VerticalDataPoint.getSymbol());
    }

    public void addX(String[] key) {
        grid[0][20] = String.format("%5s€ %s%s%s%s", 0, ChartElement.Horizontal.getSymbol(), ChartElement.Edge.getSymbol(), ChartElement.Horizontal.getSymbol(), ChartElement.Horizontal.getSymbol());
        grid[0][21] = String.format("%11s", "");
        for (int i = 1; i < grid.length; i++) {
            grid[i][20] = x;
            grid[i][21] = String.format(" %s ", key[i - 1]);
        }
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
