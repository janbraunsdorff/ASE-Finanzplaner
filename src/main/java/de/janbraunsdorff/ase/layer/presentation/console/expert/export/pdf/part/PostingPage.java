package de.janbraunsdorff.ase.layer.presentation.console.expert.export.pdf.part;

import de.janbraunsdorff.ase.layer.presentation.console.expert.export.pdf.HtmlObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public final class PostingPage implements PdfPart {
    private final List<PostingItem> items;

    public PostingPage(List<PostingItem> items) {
        this.items = items;
    }

    public List<PostingItem> getItems() {
        return items;
    }


    @Override
    public HtmlObject render() throws IOException {
        HtmlObject template = getTemplate("postingPage.html");
        List<HtmlObject> htmlItems = new ArrayList<>();
        for (PostingItem pi : items){
            htmlItems.add(pi.render());
        }
        template.replace("items", htmlItems);


        return template;
    }
}
