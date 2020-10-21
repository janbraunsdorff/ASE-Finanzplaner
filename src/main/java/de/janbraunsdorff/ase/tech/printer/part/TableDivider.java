package de.janbraunsdorff.ase.tech.printer.part;

import de.janbraunsdorff.ase.tech.printer.Color;
import de.janbraunsdorff.ase.tech.printer.SentencePiece;

public class TableDivider extends SentencePiece {

    public TableDivider(String content) {
        super(Color.CYAN, content);
    }
}
