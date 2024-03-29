package de.janbraunsdorff.ase.layer.presentation.console.expert.printing.charts;

import java.util.Arrays;
import java.util.Comparator;

import de.janbraunsdorff.ase.layer.presentation.console.expert.printing.PrinterInput;

public class BarChart {

    private final ChartData data;
    private final int start;
    private final Grid grid;

    public BarChart(ChartData data) {
        this.data = data;
        if(data.value().length == 0) {
            this.start = 0;
        }else {
            this.start = scale(Arrays.stream(this.data.value()).max(Comparator.comparingInt(a -> a)).get(), 100, 10);
        }
        this.grid = new Grid(data.value().length + 1);

        drawScale();
        drawBars();
    }

    public PrinterInput getPrintable() {
        return new PrinterInput(this.data.name() + "\n" + this.grid.toString());
    }

    private void drawBars() {
        for (int i = 0; i < this.data.value().length; i++) {
            drawBar(this.data.value()[i], i + 1);
        }

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
        grid.addX(this.data.key());
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

