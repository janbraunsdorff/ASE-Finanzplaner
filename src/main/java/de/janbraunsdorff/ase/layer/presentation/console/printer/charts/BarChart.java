package de.janbraunsdorff.ase.layer.presentation.console.printer.charts;

import java.util.Arrays;

public class BarChart {

    private final ChartData data;
    private final int start;
    private final Grid grid;

    public static void main(String[] args) {
        int[] data = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 10};
        String[] keys = new String[]{"01.11", "05.11", "08.11", "12.11", "17.11", "18.11", "21.11", "23.11", "27.11", "29.11", "30.11"};

        BarChart chart = new BarChart(new ChartData(data, keys, "Chart Test"));
        System.out.println(chart.print());
    }

    public BarChart(ChartData data) {
        this.data = data;
        this.start = scale(Arrays.stream(this.data.getValue()).max().getAsInt(), 100, 10);
        this.grid = new Grid(data.getValue().length + 1);
    }

    public String print() {
        drawScale();

        for (int i = 0; i < this.data.getValue().length; i++) {
            drawBar(this.data.getValue()[i], i + 1);
        }

        return this.grid.toString();

    }

    private void drawBar(int value, int column) {
        double height = (double) value / (double) start;
        double halfBlocks = (grid.getHeight() * height) * 2;


        int full = (int) (halfBlocks / 2);
        int half = ((int) halfBlocks % 2 == 0) ? full : full + 1;
        int empty = grid.getHeight() - half;

        if (half == full + 1) {
            grid.addHalfBlock(column, grid.getHeight() - half);
        }

        grid.addEmptyLine(column, 0, empty);
        grid.addFullLine(column, grid.getHeight() - full, grid.getHeight());

    }


    private void drawScale() {
        grid.addY();
        grid.addYPoints(0, start);
        grid.addYPoints(5, (int) (start * 0.75));
        grid.addYPoints(10, (int) (start * 0.5));
        grid.addYPoints(15, (int) (start * 0.25));
        grid.addX(this.data.getKey());
    }


    private int scale(int value, int limit, int step) {
        for (int i = step; i < limit; i += step) {
            if (value / i == 0) {
                return i;
            }
        }
        return scale(value, limit * 10, step * 10);
    }
}

