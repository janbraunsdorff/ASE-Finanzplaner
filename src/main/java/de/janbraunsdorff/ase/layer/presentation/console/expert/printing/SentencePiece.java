package de.janbraunsdorff.ase.layer.presentation.console.expert.printing;

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
