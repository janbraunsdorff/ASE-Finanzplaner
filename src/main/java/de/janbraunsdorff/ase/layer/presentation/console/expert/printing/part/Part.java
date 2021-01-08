package de.janbraunsdorff.ase.layer.presentation.console.expert.printing.part;

import de.janbraunsdorff.ase.layer.presentation.console.expert.printing.Color;

public class Part {

    private final Color color;
    private final String content;

    public Part(Color color, String content) {
        this.color = color;
        this.content = content;
    }

    public String getPiece() {
        return this.color.getValue() + this.content + Color.BASE.getValue();
    }
}
