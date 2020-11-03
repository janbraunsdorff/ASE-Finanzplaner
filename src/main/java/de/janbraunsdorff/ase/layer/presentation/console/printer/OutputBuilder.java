package de.janbraunsdorff.ase.layer.presentation.console.printer;


import de.janbraunsdorff.ase.layer.presentation.console.printer.part.Command;
import de.janbraunsdorff.ase.layer.presentation.console.printer.part.NewLine;
import de.janbraunsdorff.ase.layer.presentation.console.printer.part.TableDivider;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OutputBuilder {
    private final List<SentencePiece> pieces;

    public OutputBuilder() {
        this.pieces = new ArrayList<>();
    }

    public OutputBuilder addTableVerticalDivider() {
        this.pieces.add(new TableDivider("|"));
        return this;
    }

    public OutputBuilder addTableHorizontal(int... lengths) {
        StringBuilder builder = new StringBuilder();
        builder.append("+");
        for(int i : lengths){
            builder.append(getDivider(i));
            builder.append("+");
        }

        this.pieces.add(new TableDivider(builder.toString()));
        return this;
    }

    public OutputBuilder addText(String text) {
        this.pieces.add(new SentencePiece(Color.WHITE, text));
        return this;
    }

    public OutputBuilder addInfoText(String text) {
        this.pieces.add(new SentencePiece(Color.YELLOW, text));
        return this;
    }

    public OutputBuilder addErrorText(String text) {
        this.pieces.add(new SentencePiece(Color.RED, text));
        return this;
    }

    public OutputBuilder addNewLine() {
        this.pieces.add(new NewLine());
        return this;
    }

    public OutputBuilder addCommand() {
        this.pieces.add(new Command());
        return this;
    }

    public String build() {
        return this.pieces
                .stream()
                .map(SentencePiece::getPiece)
                .collect(Collectors.joining(""));
    }


    private String getDivider(int counter) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < counter; i++){
            builder.append("-");
        }
        return builder.toString();
    }


}
