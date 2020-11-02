package de.janbraunsdorff.ase.userinterface.console;

import de.janbraunsdorff.ase.tech.printer.Printer;
import de.janbraunsdorff.ase.userinterface.console.result.Result;

import java.util.HashMap;
import java.util.Map;

public class UseCaseController {
    private final Map<String, Distributor> useCases;
    private final Printer printer;

    protected  UseCaseController(){
        this.printer = new Printer();
        this.useCases = new HashMap<>();
    }

    public void answer(String command){
        command = command.trim();
        String useCase = command.split(" ")[0];
        Result res = this.useCases.getOrDefault(useCase, new DefaultDistributor()).distribute(command);
        this.printer.print(res);
    }

    protected void addUseCase(String command, Distributor distributor){
        this.useCases.put(command, distributor);
    }
}
