package de.janbraunsdorff.ase.layer.presentation.console.printing.part;


import de.janbraunsdorff.ase.layer.presentation.console.printing.Color;
import de.janbraunsdorff.ase.layer.presentation.console.printing.SentencePiece;

public class NewLine extends SentencePiece {
    public NewLine() {
        super(Color.BASE, "\n");
    }
}
