package de.janbraunsdorff.ase.layer.domain.export.pdf.part.headline;

public enum HeadlineSize {
    H1("h1"),
    H2("h2"),
    H3("h3"),
    H4("h4"),
    H5("h5"),
    H6("h6");

    private final String tag;


    HeadlineSize(String tag) {
        this.tag = tag;
    }

    public String getTag() {
        return tag;
    }
}
