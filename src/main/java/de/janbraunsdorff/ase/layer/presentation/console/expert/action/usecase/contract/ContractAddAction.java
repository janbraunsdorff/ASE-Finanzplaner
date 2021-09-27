package de.janbraunsdorff.ase.layer.presentation.console.expert.action.usecase.contract;

import de.janbraunsdorff.ase.layer.domain.Value;
import de.janbraunsdorff.ase.layer.domain.contract.ContractIOApplication;
import de.janbraunsdorff.ase.layer.domain.contract.command.ContractCreateCommand;
import de.janbraunsdorff.ase.layer.presentation.console.expert.ExpertCommand;
import de.janbraunsdorff.ase.layer.presentation.console.expert.action.Result;
import de.janbraunsdorff.ase.layer.presentation.console.expert.action.UseCase;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

//touch -n Miete Bruchsal -acc VB-GK -start 01.07.2021 -end 01.12.2030 -val -XXX,00

public class ContractAddAction implements UseCase {
    private final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private final ContractIOApplication contractIOApplication;

    public ContractAddAction(ContractIOApplication contractIOApplication) {
        this.contractIOApplication = contractIOApplication;
    }

    @Override
    public Result act(ExpertCommand command) throws Exception {
        if (!command.areTagsAndValuesPresent("-n", "-acc", "-start", "-end", "-val")){
            return new ContractHelpResult();
        }

        var name = command.getParameter("-n");
        var acc = command.getParameter("-acc");
        var start =  LocalDate.parse(command.getParameter("-start"), dtf);
        var end =  LocalDate.parse(command.getParameter("-end"), dtf);
        var val = Integer.parseInt(command.getParameter("-val").replaceAll("[,.]", ""));

        var cmd = new ContractCreateCommand(name, acc, start, end, new Value(val));
        var storedContract = this.contractIOApplication.createContract(cmd);
        return new ContractAddResult(storedContract);
    }
}
