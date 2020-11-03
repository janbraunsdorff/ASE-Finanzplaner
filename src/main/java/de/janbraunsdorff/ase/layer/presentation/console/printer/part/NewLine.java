package de.janbraunsdorff.ase.layer.presentation.console.printer.part;


import de.janbraunsdorff.ase.layer.presentation.console.printer.Color;
import de.janbraunsdorff.ase.layer.presentation.console.printer.SentencePiece;

public class NewLine extends SentencePiece {
    public NewLine() {
        super(Color.BASE, "\n");
    }
}
