package de.janbraunsdorff.ase.userinterface.console;

import de.janbraunsdorff.ase.tech.repositories.CrudBankRepository;
import de.janbraunsdorff.ase.usecases.crud.CrudBank;
import de.janbraunsdorff.ase.userinterface.console.curd.CrudBankDistributor;

import java.util.HashMap;
import java.util.Map;

public class UseCaseController {
    Map<String, Distributor> useCases;

    public  UseCaseController(CrudBank crudBank){
        useCases = new HashMap<String, Distributor>() {{
            put("bank", new CrudBankDistributor(crudBank));
        }};
    }

    public void answer(String command){
        String useCase = command.split(" ")[0];
        useCases.getOrDefault(useCase, new DefaultDistributor()).distribute(command);
    }
}
