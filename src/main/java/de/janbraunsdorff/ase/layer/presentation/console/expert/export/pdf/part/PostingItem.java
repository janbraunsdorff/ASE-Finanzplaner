package de.janbraunsdorff.ase.layer.presentation.console.expert.export.pdf.part;

import de.janbraunsdorff.ase.layer.presentation.console.expert.export.pdf.HtmlObject;

import java.io.IOException;

public final class PostingItem implements PdfPart {
    private final String date;
    private final String account;
    private final String thirdParty;
    private final String description;
    private final String value;

    public PostingItem(String date, String account, String thirdParty, String description, String value) {
        this.date = date;
        this.account = account;
        this.thirdParty = thirdParty;
        this.description = description;
        this.value = value;
    }

    public HtmlObject render() throws IOException {
        HtmlObject html = getTemplate("postingItem.html");
        html.replace("date", this.date);
        html.replace("account", this.account);
        html.replace("thirdParty", this.thirdParty);
        html.replace("description", !this.description.equals("") ? "<span>" + this.description + "</span>" :"");
        html.replace("value", this.value);

        return html;
    }



}
