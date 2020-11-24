package de.janbraunsdorff.ase.layer.presentation.console;

import de.janbraunsdorff.ase.layer.presentation.console.printer.Printer;
import de.janbraunsdorff.ase.layer.presentation.console.action.Result;

import java.util.HashMap;
import java.util.Map;

public class DistributorAction {
    private final Map<String, Distributor> useCases;
    private final Printer printer;

    protected DistributorAction() {
        this.printer = new Printer();
        this.useCases = new HashMap<>();
    }

    public void answer(String command) {
        command = command.trim();
        String useCase = command.split(" ")[0];
        Result res = this.useCases.getOrDefault(useCase, new DistributorDefault()).distribute(command);
        this.printer.print(res);
    }

    protected void addUseCase(String command, Distributor distributor) {
        this.useCases.put(command, distributor);
    }
}
