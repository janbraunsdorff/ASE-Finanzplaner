package de.janbraunsdorff.ase.layer.domain.reporting.pdf.part.posting;

import de.janbraunsdorff.ase.layer.domain.reporting.pdf.HtmlObject;
import de.janbraunsdorff.ase.layer.domain.reporting.pdf.part.PdfPart;

import java.util.List;
import java.util.stream.Collectors;

public final class PostingPage implements PdfPart {
    private final List<PostingItem> items;

    public PostingPage(List<PostingItem> items) {
        this.items = items;
    }

    @Override
    public HtmlObject render() {
        HtmlObject template = getTemplate("postingPage.html");
        List<HtmlObject> htmlItems = items.stream().map(PostingItem::render).collect(Collectors.toList());
        template.replace("items", htmlItems);
        return template;
    }
}
