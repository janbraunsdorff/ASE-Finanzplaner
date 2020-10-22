package de.janbraunsdorff.ase;

import de.janbraunsdorff.ase.tech.repositories.CrudBankRepository;
import de.janbraunsdorff.ase.tech.repositories.memory.MemoryRepository;
import de.janbraunsdorff.ase.usecases.crud.CrudBank;
import de.janbraunsdorff.ase.userinterface.console.ExitAction;
import de.janbraunsdorff.ase.userinterface.console.UseCaseController;
import de.janbraunsdorff.ase.userinterface.console.UseCaseControllerBuilder;
import de.janbraunsdorff.ase.userinterface.console.actions.bank.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class App {

    public static void main(String[] args) throws IOException {
        CrudBankRepository repo = new MemoryRepository();
        CrudBank crudBank = new CrudBank(repo);

        CrudBankDistributor crudBankDistributor = new CrudBankDistributorBuilder()
                .addCommand("all", new BankGetAction(crudBank))
                .addCommand("add", new BankAddAction(crudBank))
                .addCommand("update", new BankUpdateAction(crudBank))
                .build();

        UseCaseController controller = new UseCaseControllerBuilder()
                .addUseCase("bank", crudBankDistributor)
                .addUseCase("exit", new ExitAction())
                .build();

        // TODO: cleaner reader
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            String name = reader.readLine();
            controller.answer(name);
        }
    }
}
