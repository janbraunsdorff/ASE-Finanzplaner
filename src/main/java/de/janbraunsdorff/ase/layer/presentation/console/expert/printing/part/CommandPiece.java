package de.janbraunsdorff.ase.layer.presentation.console.expert.printing.part;


import de.janbraunsdorff.ase.layer.presentation.console.expert.printing.Color;
import de.janbraunsdorff.ase.layer.presentation.console.expert.printing.SentencePiece;

public class CommandPiece extends SentencePiece {

    public CommandPiece() {
        super(Color.CYAN, ">");
    }
}
