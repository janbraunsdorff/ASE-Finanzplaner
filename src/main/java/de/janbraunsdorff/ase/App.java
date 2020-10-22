package de.janbraunsdorff.ase;

import de.janbraunsdorff.ase.tech.repositories.entität.AccountEntity;
import de.janbraunsdorff.ase.tech.repositories.entität.BankEntity;
import de.janbraunsdorff.ase.tech.repositories.memory.MemoryRepository;
import de.janbraunsdorff.ase.usecases.crud.CrudAccount;
import de.janbraunsdorff.ase.usecases.crud.CrudBank;
import de.janbraunsdorff.ase.usecases.crud.ICrudAccount;
import de.janbraunsdorff.ase.usecases.crud.ICrudBank;
import de.janbraunsdorff.ase.userinterface.console.ExitAction;
import de.janbraunsdorff.ase.userinterface.console.UseCaseController;
import de.janbraunsdorff.ase.userinterface.console.UseCaseControllerBuilder;
import de.janbraunsdorff.ase.userinterface.console.actions.account.AccountAllAction;
import de.janbraunsdorff.ase.userinterface.console.actions.account.CrudAccountDistributor;
import de.janbraunsdorff.ase.userinterface.console.actions.account.CrudAccountDistributorBuilder;
import de.janbraunsdorff.ase.userinterface.console.actions.bank.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class App {

    public static void main(String[] args) throws Exception {
        MemoryRepository repo = new MemoryRepository();
        List<AccountEntity> accountEntities = new ArrayList<AccountEntity>() {{
            add(new AccountEntity(UUID.randomUUID().toString(), "Girokonto", "DE00 0000 0000 0000 0000 00", 0, new ArrayList<>()));
            add(new AccountEntity(UUID.randomUUID().toString(), "Geschäftsanteile", "DE00 0000 0000 0000 0000 01", 0, new ArrayList<>()));
            add(new AccountEntity(UUID.randomUUID().toString(), "Kreditkarte", "DE00 0000 0000 0000 0000 02", 0, new ArrayList<>()));
            add(new AccountEntity(UUID.randomUUID().toString(), "Depot", "DE00 0000 0000 0000 0000 03", 0, new ArrayList<>()));
        }};
        BankEntity vb = new BankEntity(UUID.randomUUID().toString(), "Volksbank Karlsruhe eG", accountEntities, "VB");
        repo.create(vb);


        accountEntities = new ArrayList<AccountEntity>() {{
            add(new AccountEntity(UUID.randomUUID().toString(), "Aktien", "DE00 0000 0000 0000 0000 04", 0, new ArrayList<>()));
            add(new AccountEntity(UUID.randomUUID().toString(), "Girokonto", "DE00 0000 0000 0000 0000 05", 0, new ArrayList<>()));
            add(new AccountEntity(UUID.randomUUID().toString(), "Vermögenswirksammeleistungen", "DE00 0000 0000 0000 0000 06", 0, new ArrayList<>()));
            add(new AccountEntity(UUID.randomUUID().toString(), "Altersvorsorge", "DE00 0000 0000 0000 0000 07", 0, new ArrayList<>()));
        }};
        vb = new BankEntity(UUID.randomUUID().toString(), "Spaßkasse", accountEntities, "SK");
        repo.create(vb);







        ICrudBank crudBank = new CrudBank(repo);
        CrudBankDistributor crudBankDistributor = new CrudBankDistributorBuilder()
                .addCommand("all", new BankAllAction(crudBank))
                .addCommand("get", new BankGetAction(crudBank))
                .addCommand("add", new BankAddAction(crudBank))
                .addCommand("update", new BankUpdateAction(crudBank))
                .addCommand("delete", new BankDeleteAction(crudBank))
                .build();

        ICrudAccount account = new CrudAccount(repo);
        CrudAccountDistributor crudAccountDistributor = new CrudAccountDistributorBuilder()
                .addCommand("all", new AccountAllAction(account))
                .build();

        UseCaseController controller = new UseCaseControllerBuilder()
                .addUseCase("bank", crudBankDistributor)
                .addUseCase("account", crudAccountDistributor)
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
