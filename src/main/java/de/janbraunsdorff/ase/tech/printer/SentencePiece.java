package de.janbraunsdorff.ase.tech.printer;

public class SentencePiece {

    private Color color;
    private String content;

    public SentencePiece(Color color, String content){

        this.color = color;
        this.content = content;
    }

    public String getPiece() {
        return this.color.getValue() + this.content + Color.BASE.getValue();
    }
}
