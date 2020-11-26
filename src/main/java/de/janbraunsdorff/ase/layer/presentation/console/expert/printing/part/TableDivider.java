package de.janbraunsdorff.ase.layer.presentation.console.expert.printing.part;


import de.janbraunsdorff.ase.layer.presentation.console.expert.printing.Color;
import de.janbraunsdorff.ase.layer.presentation.console.expert.printing.SentencePiece;

public class TableDivider extends SentencePiece {

    public TableDivider(String content) {
        super(Color.CYAN, content);
    }
}
