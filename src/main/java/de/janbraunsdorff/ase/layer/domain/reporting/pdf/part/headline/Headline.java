package de.janbraunsdorff.ase.layer.domain.reporting.pdf.part.headline;

import de.janbraunsdorff.ase.layer.domain.reporting.pdf.HtmlObject;
import de.janbraunsdorff.ase.layer.domain.reporting.pdf.part.PdfPart;

public class Headline implements PdfPart {

    private final String name;
    private final HeadlineSize size;

    public Headline(String name, HeadlineSize size) {
        this.name = name;
        this.size = size;
    }

    @Override
    public HtmlObject render() {
        return new HtmlObject(String.format("<%s>%s</%s>", size.getTag(), name, size.getTag()));
    }
}
