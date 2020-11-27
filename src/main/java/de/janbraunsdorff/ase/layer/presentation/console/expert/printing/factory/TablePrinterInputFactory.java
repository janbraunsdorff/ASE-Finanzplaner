package de.janbraunsdorff.ase.layer.presentation.console.expert.printing.factory;

import de.janbraunsdorff.ase.layer.presentation.console.expert.printing.Color;
import de.janbraunsdorff.ase.layer.presentation.console.expert.printing.SentencePiece;
import de.janbraunsdorff.ase.layer.presentation.console.expert.printing.charts.BarChart;
import de.janbraunsdorff.ase.layer.presentation.console.expert.printing.part.TableDivider;

import java.util.ArrayList;
import java.util.List;

public class TablePrinterInputFactory extends PrinterInputFactory {

    private final List<Integer> width;
    private int lastIndex;
    private boolean isFirstRow;

    public TablePrinterInputFactory() {
        super();
        this.width = new ArrayList<>();
        this.lastIndex = 0;
        this.isFirstRow = true;
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

    public TablePrinterInputFactory finishFirstLine() {
        this.addTableVerticalDivider();
        this.lastIndex = 0;
        this.isFirstRow = false;
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

    public TablePrinterInputFactory addAmount(int amount) {
        String builder = amount / 100 +
                "." +
                String.format("%2d", Math.abs(amount % 100)).replace(' ', '0') +
                "€";
        String text = String.format("%" + getLength() + "s", builder);
        this.lastIndex++;
        if (amount < 0) {
            this.pieces.add(new SentencePiece(Color.RED, text));
        } else {
            this.pieces.add(new SentencePiece(Color.GREEN, text));
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
        this.addText(chart.getPrintable().getStringToPrint());
    }
}
