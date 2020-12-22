package de.janbraunsdorff.ase.layer.presentation.console.expert.action.usecase.transaction;

import de.janbraunsdorff.ase.layer.domain.transaction.TransactionApplication;
import de.janbraunsdorff.ase.layer.domain.transaction.TransactionDTO;
import de.janbraunsdorff.ase.layer.presentation.console.expert.ExpertCommand;
import de.janbraunsdorff.ase.layer.presentation.console.expert.action.Result;
import de.janbraunsdorff.ase.layer.presentation.console.expert.action.UseCase;

import java.util.List;

public class TransactionDeleteAction implements UseCase {

    private final TransactionApplication service;

    public TransactionDeleteAction(TransactionApplication service) {
        this.service = service;
    }

    @Override
    public Result act(ExpertCommand command) throws Exception {
        if (!command.areTagsAndValuesPresent("-id")){
            return new TransactionHelpResult();
        }

        List<TransactionDTO> dto = service.deleteTransaction(command.getParameter("-id"));
        String id = "";
        if (!dto.isEmpty()){
            id = dto.get(0).getId();
        }

        return new TransactionDeleteResult(id);
    }
}
