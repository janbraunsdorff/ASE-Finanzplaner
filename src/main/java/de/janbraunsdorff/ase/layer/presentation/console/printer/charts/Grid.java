package de.janbraunsdorff.ase.layer.presentation.console.printer.charts;

public class Grid {
    private final String[][] grid;
    private final int height = 21;

    public Grid(int width) {
        this.grid = new String[width][height+1];
    }

    public void addHorizontalLine(int column, String value, int start, int end){
        for (int i = start; i < end; i++){
            grid[column][i] = value;
        }
    }
}
