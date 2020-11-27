package de.janbraunsdorff.ase.layer.presentation.console.expert.action.usecase.analyse;

import de.janbraunsdorff.ase.layer.domain.analyse.TransactionAnalyse;
import de.janbraunsdorff.ase.layer.domain.analyse.TransactionGroupCommand;
import de.janbraunsdorff.ase.layer.domain.analyse.TransactionGroupDTO;
import de.janbraunsdorff.ase.layer.presentation.console.expert.Command;
import de.janbraunsdorff.ase.layer.presentation.console.expert.action.Result;
import de.janbraunsdorff.ase.layer.presentation.console.expert.action.UseCase;
import de.janbraunsdorff.ase.layer.presentation.console.expert.action.usecase.transaction.TransactionHelpResult;

import java.util.List;

public class TransactionGroupAction implements UseCase {
    private final TransactionAnalyse analyse;

    public TransactionGroupAction(TransactionAnalyse analyse) {
        this.analyse = analyse;
    }

    @Override
    public Result act(Command command) throws Exception {
        if (!command.areTagsAndValuesPresent("-a")){
            return new TransactionHelpResult();
        }
        String accountAcronym = command.getParameter("-a");
        List<TransactionGroupDTO> transactionGroupDTOS = analyse.groupMonthly(new TransactionGroupCommand(accountAcronym));
        return new TransactionGroupResult(transactionGroupDTOS);
    }
}
