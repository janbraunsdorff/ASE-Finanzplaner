package de.janbraunsdorff.ase.layer.presentation.console.printer.part;


import de.janbraunsdorff.ase.layer.presentation.console.printer.Color;
import de.janbraunsdorff.ase.layer.presentation.console.printer.SentencePiece;

public class Command extends SentencePiece {

    public Command() {
        super(Color.CYAN, ">");
    }
}
