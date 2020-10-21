package de.janbraunsdorff.ase.tech.printer.part;

import de.janbraunsdorff.ase.tech.printer.Color;
import de.janbraunsdorff.ase.tech.printer.SentencePiece;

public class Command extends SentencePiece {

    public Command() {
        super(Color.CYAN, ">");
    }
}
