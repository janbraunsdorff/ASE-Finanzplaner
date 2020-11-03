package de.janbraunsdorff.ase.layer.presentation.console.printer;

import de.janbraunsdorff.ase.layer.presentation.console.printer.part.TableDivider;

import java.util.ArrayList;
import java.util.List;

public class TableOutputBuilder extends OutputBuilder{

    private List<Integer> width;
    private int lastIndex;
    private boolean isFirstRow;

    public TableOutputBuilder() {
        super();
        this.width = new ArrayList<>();
        this.lastIndex = 0;
        this.isFirstRow = true;
    }

    public TableOutputBuilder addTableHeader(int width, String name){
        this.addTableVerticalDivider();
        this.addText(String.format("%-"+width+"s", name));
        this.width.add(width);
        return this;
    }

    public TableOutputBuilder finishFirstLine(){
        this.lastIndex = 0;
        this.isFirstRow = false;
        this.addNewLine();
        return this;
    }

    public TableOutputBuilder addHorizontalLine(){
        StringBuilder builder = new StringBuilder();
        builder.append("+");
        for(Integer i : this.width){
            builder.append(this.getDivider(i));
            builder.append("+");
        }

        this.pieces.add(new TableDivider(builder.toString()));
        this.addNewLine();
        return this;
    }

    public TableOutputBuilder addLine(){
        return this.addTableVerticalDivider();
    }

    public TableOutputBuilder addEntry(String text){
        this.addText(String.format("%-" +getLength()+ "s", text));
        this.lastIndex++;
        this.addTableVerticalDivider();
        return this;
    }

    public OutputBuilder addAmount(int amount) {
        String amountBuilder = amount / 100 + "." + amount % 100 + "€";
        String text = String.format("%-"+getLength()+"s", amountBuilder);
        this.lastIndex++;
        if (amount < 0){
            this.pieces.add(new SentencePiece(Color.RED, text));
        } else {
            this.pieces.add(new SentencePiece(Color.GREEN, text));
        }
        this.addTableVerticalDivider();
        return this;
    }

    private int getLength(){
        return this.width.get(this.lastIndex % this.width.size());
    }

    private String getDivider(int counter) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < counter; i++){
            builder.append("-");
        }
        return builder.toString();
    }

    public TableOutputBuilder addTableVerticalDivider() {
        this.pieces.add(new TableDivider("|"));
        return this;
    }
}
