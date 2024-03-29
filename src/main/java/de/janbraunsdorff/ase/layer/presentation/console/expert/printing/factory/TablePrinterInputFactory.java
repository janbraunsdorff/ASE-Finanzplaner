package de.janbraunsdorff.ase.layer.presentation.console.expert.printing.factory;

import java.util.ArrayList;
import java.util.List;

import de.janbraunsdorff.ase.layer.domain.Value;
import de.janbraunsdorff.ase.layer.presentation.console.expert.printing.Color;
import de.janbraunsdorff.ase.layer.presentation.console.expert.printing.charts.BarChart;
import de.janbraunsdorff.ase.layer.presentation.console.expert.printing.part.Part;
import de.janbraunsdorff.ase.layer.presentation.console.expert.printing.part.TableDivider;

public class TablePrinterInputFactory extends PrinterInputFactory {

    private final List<Integer> width;
    private int lastIndex;

    public TablePrinterInputFactory() {
        super();
        this.width = new ArrayList<>();
        this.lastIndex = 0;
    }

    public TablePrinterInputFactory addHeadline(String text) {
        this.addText(text);
        this.addNewLine();
        return this;
    }

    public TablePrinterInputFactory addTableHeader(int width, String name) {
        this.addTableVerticalDivider();
        width = Math.max(width, name.length());
        this.addText(String.format("%-" + width + "s", name));
        this.width.add(width);
        return this;
    }

    public TablePrinterInputFactory addTableHeader(boolean condition, int width, String name) {
        if (condition){
            this.addTableHeader(width, name);
        }
        return this;
    }

    public TablePrinterInputFactory finishFirstLine() {
        this.addTableVerticalDivider();
        this.lastIndex = 0;
        this.addNewLine();
        return this;
    }

    public TablePrinterInputFactory addHorizontalLine() {
        StringBuilder builder = new StringBuilder();
        builder.append("+");
        for (Integer i : this.width) {
            builder.append(this.getDivider(i));
            builder.append("+");
        }

        this.pieces.add(new TableDivider(builder.toString()));
        this.addNewLine();
        return this;
    }

    public TablePrinterInputFactory addLine() {
        return this.addTableVerticalDivider();
    }

    public TablePrinterInputFactory addEntry(String text) {
        this.addText(String.format("%-" + getLength() + "s", text));
        this.lastIndex++;
        this.addTableVerticalDivider();
        return this;
    }

    public TablePrinterInputFactory addEntry(boolean condition, String text) {
        if (condition){
            this.addEntry(text);
        }
        return this;
    }

    public TablePrinterInputFactory addAmount(int amount) {
        String builder = amount / 100 +
                "." +
                String.format("%2d", Math.abs(amount % 100)).replace(' ', '0') +
                "€";
        String text = String.format("%" + getLength() + "s", builder);
        this.lastIndex++;
        if (amount < 0) {
            this.pieces.add(new Part(Color.RED, text));
        } else {
            this.pieces.add(new Part(Color.GREEN, text));
        }
        this.addTableVerticalDivider();
        return this;
    }

    public TablePrinterInputFactory addAmount(Value value) {
        String text = String.format("%" + getLength() + "s", value.getFormatted());
        this.lastIndex++;
        if (value.isPositive()) {
            this.pieces.add(new Part(Color.GREEN, text));
        } else {
            this.pieces.add(new Part(Color.RED, text));
        }
        this.addTableVerticalDivider();
        return this;
    }

    private int getLength() {
        return this.width.get(this.lastIndex % this.width.size());
    }

    private String getDivider(int counter) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < counter; i++) {
            builder.append("-");
        }
        return builder.toString();
    }

    public TablePrinterInputFactory addTableVerticalDivider() {
        this.pieces.add(new TableDivider("|"));
        return this;
    }

    public void addChart(BarChart chart) {
        this.addText(chart.getPrintable().output());
    }
}
