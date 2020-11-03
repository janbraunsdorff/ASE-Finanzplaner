package de.janbraunsdorff.ase.layer.presentation.console.printer;


import de.janbraunsdorff.ase.layer.presentation.console.printer.part.NewLine;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class OutputBuilder {
    protected final List<SentencePiece> pieces;

    public OutputBuilder() {
        this.pieces = new ArrayList<>();
    }

    protected OutputBuilder addText(String text) {
        this.pieces.add(new SentencePiece(Color.WHITE, text));
        return this;
    }

    protected void addInfoText(String text) {
        this.pieces.add(new SentencePiece(Color.YELLOW, text));
    }

    protected void addErrorText(String text) {
        this.pieces.add(new SentencePiece(Color.RED, text));
    }

    public OutputBuilder addNewLine() {
        this.pieces.add(new NewLine());
        return this;
    }

    public String build() {
        return this.pieces
                .stream()
                .map(SentencePiece::getPiece)
                .collect(Collectors.joining(""));
    }
}
