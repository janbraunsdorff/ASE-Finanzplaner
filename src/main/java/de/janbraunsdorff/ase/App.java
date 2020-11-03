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
import de.janbraunsdorff.ase.layer.presentation.console.actions.account.*;
import de.janbraunsdorff.ase.layer.presentation.console.actions.bank.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class App {

    public static void main(String[] args) throws Exception {
        MemoryRepository repo = new MemoryRepository();

        BankEntity vb = new BankEntity("Volksbank Karlsruhe eG","VB");
        vb.addAccount(new AccountEntity("Girokonto", "DE00 0000 0000 0000 0000 00","VB-GK"));
        vb.addAccount(new AccountEntity("Geschäftsanteile", "DE00 0000 0000 0000 0000 01", "VB-GA"));
        vb.addAccount(new AccountEntity("Kreditkarte", "DE00 0000 0000 0000 0000 02", "VB-KK"));
        vb.addAccount(new AccountEntity("Depot", "DE00 0000 0000 0000 0000 03", "VB-DT"));
        repo.create(vb);

        vb = new BankEntity("Spaßkasse", "SK");
        vb.addAccount(new AccountEntity("Aktien", "DE00 0000 0000 0000 0000 04", "SK-AK"));
        vb.addAccount(new AccountEntity("Girokonto", "DE00 0000 0000 0000 0000 05", "SK-GK"));
        vb.addAccount(new AccountEntity("Vermögenswirksammeleistungen", "DE00 0000 0000 0000 0000 06", "SK-VL"));
        vb.addAccount(new AccountEntity("Altersvorsorge", "DE00 0000 0000 0000 0000 07", "SK-AV"));
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
                .addCommand("add", new AccountAddAction(account))
                .addCommand("delete", new AccountDeleteAction(account))
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
            if (name == null){
                System.exit(0);
            }
            controller.answer(name);
        }
    }
}
