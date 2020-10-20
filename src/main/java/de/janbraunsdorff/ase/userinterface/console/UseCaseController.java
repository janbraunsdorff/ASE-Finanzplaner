package de.janbraunsdorff.ase.userinterface.console;

import de.janbraunsdorff.ase.tech.printer.Printer;
import de.janbraunsdorff.ase.usecases.crud.CrudBank;
import de.janbraunsdorff.ase.userinterface.console.curd.CrudBankDistributor;
import de.janbraunsdorff.ase.userinterface.console.result.Result;

import java.util.HashMap;
import java.util.Map;

public class UseCaseController {
    Map<String, Distributor> useCases;
    Printer printer;

    public  UseCaseController(CrudBank crudBank){
        printer = new Printer();
        useCases = new HashMap<String, Distributor>() {{
            put("bank", new CrudBankDistributor(crudBank));
        }};
    }

    public void answer(String command){
        String useCase = command.split(" ")[0];
        Result res = useCases.getOrDefault(useCase, new DefaultDistributor()).distribute(command);
        printer.print(res);
    }
}
