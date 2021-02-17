package de.janbraunsdorff.ase.layer.presentation.console.expert.action.usecase.transaction;

import de.janbraunsdorff.ase.layer.domain.TransactionNotFoundException;
import de.janbraunsdorff.ase.layer.domain.transaction.TransactionApplication;
import de.janbraunsdorff.ase.layer.domain.transaction.TransactionDTO;
import de.janbraunsdorff.ase.layer.domain.transaction.TransactionDeleteCommand;
import de.janbraunsdorff.ase.layer.presentation.console.expert.ExpertCommand;
import de.janbraunsdorff.ase.layer.presentation.console.expert.action.Result;
import de.janbraunsdorff.ase.layer.presentation.console.expert.action.UseCase;
import de.janbraunsdorff.ase.layer.presentation.console.expert.action.system.ErrorResult;

import java.util.List;
import java.util.stream.Collectors;

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

        try {
            var cmd = new TransactionDeleteCommand(command.getParameter("-id").split(" "));
            List<TransactionDTO> dto = service.deleteTransaction(cmd);
            var id = dto.stream().map(TransactionDTO::getId).collect(Collectors.joining(" | "));
            return new TransactionDeleteResult(id);

        } catch (TransactionNotFoundException ex){
            return new ErrorResult(ex.getMessage());
        }

    }
}
