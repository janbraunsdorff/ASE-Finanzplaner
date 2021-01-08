package de.janbraunsdorff.ase.layer.presentation.console.expert.action.usecase.transaction;

import de.janbraunsdorff.ase.layer.domain.transaction.TransactionApplication;
import de.janbraunsdorff.ase.layer.domain.transaction.TransactionGroupCommand;
import de.janbraunsdorff.ase.layer.domain.transaction.TransactionGroupDTO;
import de.janbraunsdorff.ase.layer.presentation.console.expert.ExpertCommand;
import de.janbraunsdorff.ase.layer.presentation.console.expert.action.Result;
import de.janbraunsdorff.ase.layer.presentation.console.expert.action.UseCase;

import java.util.List;

public class TransactionGroupAction implements UseCase {

    private final TransactionApplication service;

    public TransactionGroupAction(TransactionApplication service) {
        this.service = service;
    }

    @Override
    public Result act(ExpertCommand command) throws Exception {
        if (!command.areTagsAndValuesPresent("-a")){
            return new TransactionHelpResult();
        }
        String accountAcronym = command.getParameter("-a");
        List<TransactionGroupDTO> transactionGroupDTOS = service.groupMonthly(new TransactionGroupCommand(accountAcronym));
        return new TransactionGroupResult(transactionGroupDTOS);
    }
}
