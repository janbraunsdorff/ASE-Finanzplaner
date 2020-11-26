package de.janbraunsdorff.ase.layer.presentation.console.action.usecase.transaction;

import de.janbraunsdorff.ase.layer.domain.transaction.TransactionApplication;
import de.janbraunsdorff.ase.layer.domain.transaction.TransactionCreateCommand;
import de.janbraunsdorff.ase.layer.domain.transaction.TransactionDTO;
import de.janbraunsdorff.ase.layer.presentation.console.action.Result;
import de.janbraunsdorff.ase.layer.presentation.console.action.UseCase;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class TransactionAddAction extends UseCase {
    final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private final TransactionApplication service;

    public TransactionAddAction(TransactionApplication service) {
        this.service = service;
    }

    @Override
    public Result act(String command) throws Exception {
        Map<String, String> tags = parseCommand(command, 2);
        if (!areTagsAndValuesPresent(tags, "-a", "-val", "-thp", "-dat", "-cat")) {
            return new TransactionHelpResult();
        }

        String accountAcronym = tags.get("-a");
        String thirdParty = tags.get("-thp");
        Integer val = Integer.parseInt(tags.get("-val"));
        LocalDate date = LocalDate.parse(tags.get("-dat"), dtf);
        String cat = tags.get("-cat");
        boolean contact = tags.containsKey("-cat");


        TransactionCreateCommand cmd = new TransactionCreateCommand(accountAcronym, val, date, thirdParty, cat, contact);
        TransactionDTO dto = service.createTransactionByAccountId(cmd);
        return new TransactionAddResult(dto);
    }
}
