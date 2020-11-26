package de.janbraunsdorff.ase.layer.presentation.console.expert;

import de.janbraunsdorff.ase.layer.presentation.console.expert.action.Result;
import de.janbraunsdorff.ase.layer.presentation.console.expert.printing.Printer;

import java.util.HashMap;
import java.util.Map;

public class DistributorAction {
    private final Map<String, Distributor> useCases;
    private final Printer printer;

    protected DistributorAction() {
        this.printer = new Printer();
        this.useCases = new HashMap<>();
    }

    public void answer(Command command) {
        String useCase = command.getTopLevel();
        Result res = this.useCases.getOrDefault(useCase, new DistributorDefault()).distribute(command);
        this.printer.print(res);
    }

    protected void addUseCase(String command, Distributor distributor) {
        this.useCases.put(command, distributor);
    }
}
