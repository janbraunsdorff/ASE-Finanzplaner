package de.janbraunsdorff.ase.tech.printer.part;

import de.janbraunsdorff.ase.tech.printer.Color;
import de.janbraunsdorff.ase.tech.printer.SentencePiece;

public class NewLine extends SentencePiece {
    public NewLine() {
        super(Color.BASE, "\n");
    }
}
