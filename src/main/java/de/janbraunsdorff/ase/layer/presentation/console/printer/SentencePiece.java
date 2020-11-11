package de.janbraunsdorff.ase.layer.presentation.console.printer;

public class SentencePiece {

    private final Color color;
    private final String content;

    public SentencePiece(Color color, String content) {

        this.color = color;
        this.content = content;
    }

    public String getPiece() {
        return this.color.getValue() + this.content + Color.BASE.getValue();
    }
}
