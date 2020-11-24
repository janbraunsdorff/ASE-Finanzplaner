package de.janbraunsdorff.ase.layer.presentation.console.printing.factory;

import de.janbraunsdorff.ase.layer.presentation.console.printing.Color;
import de.janbraunsdorff.ase.layer.presentation.console.printing.SentencePiece;
import de.janbraunsdorff.ase.layer.presentation.console.printing.part.TableDivider;

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

    public PrinterInputFactory addAmount(int amount) {
        String amountBuilder = amount / 100 + "." + amount % 100 + "€";
        String text = String.format("%-" + getLength() + "s", amountBuilder);
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
}
