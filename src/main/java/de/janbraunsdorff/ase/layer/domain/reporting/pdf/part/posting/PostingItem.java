package de.janbraunsdorff.ase.layer.domain.reporting.pdf.part.posting;

import de.janbraunsdorff.ase.layer.domain.reporting.pdf.HtmlObject;
import de.janbraunsdorff.ase.layer.domain.reporting.pdf.part.PdfPart;

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

    public HtmlObject render() {
        HtmlObject html = getTemplate("postingItem.html");
        html.replace("date", this.date);
        html.replace("account", this.account);
        html.replace("thirdParty", this.thirdParty);
        html.replace("description", !this.description.equals("") ? "<span>" + this.description + "</span>" :"");
        html.replace("value", this.value);

        return html;
    }



}
