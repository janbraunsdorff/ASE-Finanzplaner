package de.janbraunsdorff.ase;


import de.janbraunsdorff.ase.layer.domain.crud.CrudAccount;
import de.janbraunsdorff.ase.layer.domain.crud.CrudBank;
import de.janbraunsdorff.ase.layer.domain.crud.ICrudAccount;
import de.janbraunsdorff.ase.layer.domain.crud.ICrudBank;
import de.janbraunsdorff.ase.layer.persistence.repositories.entität.AccountEntity;
import de.janbraunsdorff.ase.layer.persistence.repositories.entität.BankEntity;
import de.janbraunsdorff.ase.layer.persistence.repositories.memory.MemoryRepository;
import de.janbraunsdorff.ase.layer.presentation.console.ExitAction;
import de.janbraunsdorff.ase.layer.presentation.console.UseCaseController;
import de.janbraunsdorff.ase.layer.presentation.console.UseCaseControllerBuilder;
import de.janbraunsdorff.ase.layer.presentation.console.actions.account.AccountAddAction;
import de.janbraunsdorff.ase.layer.presentation.console.actions.account.AccountAllAction;
import de.janbraunsdorff.ase.layer.presentation.console.actions.account.CrudAccountDistributor;
import de.janbraunsdorff.ase.layer.presentation.console.actions.account.CrudAccountDistributorBuilder;
import de.janbraunsdorff.ase.layer.presentation.console.actions.bank.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class App {

    public static void main(String[] args) throws Exception {
        MemoryRepository repo = new MemoryRepository();
        List<AccountEntity> accountEntities = new ArrayList<AccountEntity>() {{
            add(new AccountEntity("Girokonto", "DE00 0000 0000 0000 0000 00","VB-GK", 0, new ArrayList<>()));
            add(new AccountEntity("Geschäftsanteile", "DE00 0000 0000 0000 0000 01", "VB-GA",0, new ArrayList<>()));
            add(new AccountEntity("Kreditkarte", "DE00 0000 0000 0000 0000 02", "VB-KK", 0, new ArrayList<>()));
            add(new AccountEntity("Depot", "DE00 0000 0000 0000 0000 03", "VB-DT", 0, new ArrayList<>()));
        }};
        BankEntity vb = new BankEntity(UUID.randomUUID().toString(), "Volksbank Karlsruhe eG", accountEntities, "VB");
        repo.create(vb);


        accountEntities = new ArrayList<AccountEntity>() {{
            add(new AccountEntity("Aktien", "DE00 0000 0000 0000 0000 04", "SK-AK", 0, new ArrayList<>()));
            add(new AccountEntity("Girokonto", "DE00 0000 0000 0000 0000 05", "SK-GK", 0, new ArrayList<>()));
            add(new AccountEntity("Vermögenswirksammeleistungen", "DE00 0000 0000 0000 0000 06", "SK-VL", 0, new ArrayList<>()));
            add(new AccountEntity("Altersvorsorge", "DE00 0000 0000 0000 0000 07", "SK-AV", 0, new ArrayList<>()));
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

        ICrudAccount account = new CrudAccount(repo, repo);
        CrudAccountDistributor crudAccountDistributor = new CrudAccountDistributorBuilder()
                .addCommand("all", new AccountAllAction(account))
                .addCommand("add", new AccountAddAction(account))
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
