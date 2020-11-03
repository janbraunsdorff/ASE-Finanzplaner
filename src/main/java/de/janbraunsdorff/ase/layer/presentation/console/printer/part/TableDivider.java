package de.janbraunsdorff.ase.layer.presentation.console.printer.part;


import de.janbraunsdorff.ase.layer.presentation.console.printer.Color;
import de.janbraunsdorff.ase.layer.presentation.console.printer.SentencePiece;

public class TableDivider extends SentencePiece {

    public TableDivider(String content) {
        super(Color.CYAN, content);
    }
}
