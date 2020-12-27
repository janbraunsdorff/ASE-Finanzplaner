package de.janbraunsdorff.ase.layer.presentation.console.expert.export.pdf.part;

import de.janbraunsdorff.ase.layer.presentation.console.expert.export.pdf.HtmlObject;

import java.io.IOException;

public class Headline implements PdfPart{

    private String name;
    private HeadlineSize size;

    public Headline(String name, HeadlineSize size) {
        this.name = name;
        this.size = size;
    }


    @Override
    public HtmlObject render() throws IOException {
        return new HtmlObject(String.format("<%s>%s</%s>", size.getTag(), name, size.getTag()));
    }
}
