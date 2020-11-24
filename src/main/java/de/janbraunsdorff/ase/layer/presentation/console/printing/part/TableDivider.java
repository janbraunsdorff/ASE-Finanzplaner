package de.janbraunsdorff.ase.layer.presentation.console.printing.part;


import de.janbraunsdorff.ase.layer.presentation.console.printing.Color;
import de.janbraunsdorff.ase.layer.presentation.console.printing.SentencePiece;

public class TableDivider extends SentencePiece {

    public TableDivider(String content) {
        super(Color.CYAN, content);
    }
}
