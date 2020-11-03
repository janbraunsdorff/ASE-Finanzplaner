package de.janbraunsdorff.ase.layer.presentation.console.printer;


import de.janbraunsdorff.ase.layer.presentation.console.printer.part.NewLine;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OutputBuilder {
    protected final List<SentencePiece> pieces;

    public OutputBuilder() {
        this.pieces = new ArrayList<>();
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

    public String build() {
        return this.pieces
                .stream()
                .map(SentencePiece::getPiece)
                .collect(Collectors.joining(""));
    }
}
