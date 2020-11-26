package de.janbraunsdorff.ase.layer.presentation.console.expert.printing.factory;


import de.janbraunsdorff.ase.layer.presentation.console.expert.printing.Color;
import de.janbraunsdorff.ase.layer.presentation.console.expert.printing.PrinterInput;
import de.janbraunsdorff.ase.layer.presentation.console.expert.printing.SentencePiece;
import de.janbraunsdorff.ase.layer.presentation.console.expert.printing.part.NewLine;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class PrinterInputFactory {
    protected final List<SentencePiece> pieces;

    public PrinterInputFactory() {
        this.pieces = new ArrayList<>();
    }

    protected PrinterInputFactory addText(String text) {
        this.pieces.add(new SentencePiece(Color.WHITE, text));
        return this;
    }

    protected void addInfoText(String text) {
        this.pieces.add(new SentencePiece(Color.YELLOW, text));
    }

    protected void addErrorText(String text) {
        this.pieces.add(new SentencePiece(Color.RED, text));
    }

    public PrinterInputFactory addNewLine() {
        this.pieces.add(new NewLine());
        return this;
    }

    public PrinterInput build() {
        return new PrinterInput(
                this.pieces
                        .stream()
                        .map(SentencePiece::getPiece)
                        .collect(Collectors.joining("")));
    }
}
