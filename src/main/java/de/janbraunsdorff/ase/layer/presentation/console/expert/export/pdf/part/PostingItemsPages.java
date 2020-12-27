package de.janbraunsdorff.ase.layer.presentation.console.expert.export.pdf.part;

import de.janbraunsdorff.ase.layer.domain.transaction.TransactionDTO;
import de.janbraunsdorff.ase.layer.presentation.console.expert.export.pdf.HtmlObject;

import java.io.IOException;
import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PostingItemsPages implements PdfPart {
    private final static int ITEMS_PER_SITE = 25;
    private final List<TransactionDTO> transactions;

    public PostingItemsPages(List<TransactionDTO> transactions) {
        this.transactions = transactions;
    }

    public List<PdfPart> getPages() {
        List<PdfPart> postingItems = new ArrayList<>();
        int numberOfPages = (this.transactions.size() / ITEMS_PER_SITE) + 1;
        for (int i = 0; i < numberOfPages; i++){
            List<TransactionDTO> transactionsPerPage = this.transactions.subList(i * ITEMS_PER_SITE, Math.min((i + 1) * ITEMS_PER_SITE, this.transactions.size()));
            postingItems.add(new PostingPage(transactionsPerPage
                    .stream()
                    .map(t -> new PostingItem(
                            t.getDate().format(DateTimeFormatter.ofPattern("dd.MM.yyy")),
                            "account", t.getThirdParty(),
                            getDescription(t.getCategory(), t.getContract()),
                            formatValue(t.getValue())))
                    .collect(Collectors.toList())));
        }

        return postingItems;
    }

    private String getDescription(String category, Boolean contract) {
        if (category.contains("Gehalt")){
            return "Gehalt";
        }
        if (category.contains("Miete")) {
            return "Miete";
        }

        if(category.contains("Einkauf")){
            return "Einkauf";
        }

        if(contract){
            return "Vertrag";
        }

        return "";
    }

    @Override
    public HtmlObject render() throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        for (PdfPart page : getPages()){
            stringBuilder.append(page.render());
        }

        return new HtmlObject(stringBuilder.toString());
    }
}
