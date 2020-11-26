package de.janbraunsdorff.ase.layer.presentation.console.expert.printing.part;


import de.janbraunsdorff.ase.layer.presentation.console.expert.printing.Color;
import de.janbraunsdorff.ase.layer.presentation.console.expert.printing.SentencePiece;

public class NewLine extends SentencePiece {
    public NewLine() {
        super(Color.BASE, "\n");
    }
}
