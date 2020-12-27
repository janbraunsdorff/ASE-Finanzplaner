package de.janbraunsdorff.ase.layer.presentation.console.expert.export.pdf.part;

import de.janbraunsdorff.ase.layer.domain.AccountNotFoundException;
import de.janbraunsdorff.ase.layer.domain.BankNotFoundException;
import de.janbraunsdorff.ase.layer.domain.account.AccountApplication;
import de.janbraunsdorff.ase.layer.domain.account.AccountGetByAcronymQuery;
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
    private AccountApplication accountService;

    public PostingItemsPages(List<TransactionDTO> transactions, AccountApplication accountService) {
        this.transactions = transactions;
        this.accountService = accountService;
    }

    public List<PdfPart> getPages() {
        List<PdfPart> postingItems = new ArrayList<>();
        int numberOfPages = (this.transactions.size() / ITEMS_PER_SITE) + 1;
        for (int i = 0; i < numberOfPages; i++){
            List<TransactionDTO> transactionsPerPage = this.transactions.subList(i * ITEMS_PER_SITE, Math.min((i + 1) * ITEMS_PER_SITE, this.transactions.size()));
            postingItems.add(new PostingPage(transactionsPerPage
                    .stream()
                    .map(t -> {
                        String accountName = "";
                        try {
                            accountName = accountService.getAccount(new AccountGetByAcronymQuery(t.getAccount())).getName();
                        } catch (AccountNotFoundException | BankNotFoundException e) {
                            e.printStackTrace();
                        }
                        return new PostingItem(
                                t.getDate().format(DateTimeFormatter.ofPattern("dd.MM.yyy")),
                                accountName,
                                t.getThirdParty(),
                                getDescription(t.getCategory(), t.getContract()),
                                formatValue(t.getValue()));
                    })
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