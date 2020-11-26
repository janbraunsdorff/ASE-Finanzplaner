package de.janbraunsdorff.ase.layer.presentation.console.expert.action.usecase.transaction;

import de.janbraunsdorff.ase.layer.domain.transaction.TransactionApplication;
import de.janbraunsdorff.ase.layer.domain.transaction.TransactionCreateCommand;
import de.janbraunsdorff.ase.layer.domain.transaction.TransactionDTO;
import de.janbraunsdorff.ase.layer.presentation.console.expert.Command;
import de.janbraunsdorff.ase.layer.presentation.console.expert.action.Result;
import de.janbraunsdorff.ase.layer.presentation.console.expert.action.UseCase;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TransactionAddAction implements UseCase {
    final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private final TransactionApplication service;

    public TransactionAddAction(TransactionApplication service) {
        this.service = service;
    }

    @Override
    public Result act(Command command) throws Exception {
        if (!command.areTagsAndValuesPresent("-a", "-val", "-thp", "-dat", "-cat")) {
            return new TransactionHelpResult();
        }

        String accountAcronym = command.getParameter("-a");
        String thirdParty = command.getParameter("-thp");
        Integer val = Integer.parseInt(command.getParameter("-val").replaceAll("[,.]", ""));
        LocalDate date = LocalDate.parse(command.getParameter("-dat"), dtf);
        String cat = command.getParameter("-cat");
        boolean contact = command.areTagsPresent("-con");


        TransactionCreateCommand cmd = new TransactionCreateCommand(accountAcronym, val, date, thirdParty, cat, contact);
        TransactionDTO dto = service.createTransactionByAccountId(cmd);
        return new TransactionAddResult(dto);
    }
}
