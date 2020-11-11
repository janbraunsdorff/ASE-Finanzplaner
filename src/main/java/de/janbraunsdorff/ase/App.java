package de.janbraunsdorff.ase;


import de.janbraunsdorff.ase.layer.domain.crud.CrudAccount;
import de.janbraunsdorff.ase.layer.domain.crud.CrudBank;
import de.janbraunsdorff.ase.layer.domain.crud.ICrudAccount;
import de.janbraunsdorff.ase.layer.domain.crud.ICrudBank;
import de.janbraunsdorff.ase.layer.persistence.repositories.entität.AccountEntity;
import de.janbraunsdorff.ase.layer.persistence.repositories.entität.BankEntity;
import de.janbraunsdorff.ase.layer.persistence.repositories.entität.TransactionEntity;
import de.janbraunsdorff.ase.layer.persistence.repositories.memory.MemoryRepository;
import de.janbraunsdorff.ase.layer.presentation.console.ExitAction;
import de.janbraunsdorff.ase.layer.presentation.console.UseCaseController;
import de.janbraunsdorff.ase.layer.presentation.console.UseCaseControllerBuilder;
import de.janbraunsdorff.ase.layer.presentation.console.actions.account.*;
import de.janbraunsdorff.ase.layer.presentation.console.actions.bank.*;
import de.janbraunsdorff.ase.layer.presentation.console.actions.transaction.CrudTransactionDistributor;
import de.janbraunsdorff.ase.layer.presentation.console.actions.transaction.CrudTransactionDistributorBuilder;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class App {

    public static void main(String[] args) throws Exception {
//      ++++ create Persistence layer ++++
        MemoryRepository repo = new MemoryRepository();
        repo.create(createVolksbank()); // Create Demo Data Volksbank
        repo.create(createSparkasse()); // Create Demo Data Spaßkasse

//      ++++ create domain layer ++++
        ICrudAccount accountService = new CrudAccount(repo);
        ICrudBank bankService = new CrudBank(repo);


//      ++++ create presentation / input layer +++
//      build distributor
        CrudBankDistributor crudBankDistributor = buildBankDistributor(bankService);
        CrudAccountDistributor crudAccountDistributor = createAccountDistributor(accountService);
        CrudTransactionDistributor crudTransactionDistributor = createTransactionDistributor(repo);


//      composite distributor
        UseCaseController controller = new UseCaseControllerBuilder()
                .addUseCase("bank", crudBankDistributor)
                .addUseCase("account", crudAccountDistributor)
                .addUseCase("transaction", crudTransactionDistributor)
                .addUseCase("exit", new ExitAction())
                .build();

//      TODO: cleaner reader
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            String name = reader.readLine();
            if (name == null) {
                System.exit(0);
            }
            controller.answer(name);
        }
    }

    private static CrudTransactionDistributor createTransactionDistributor(MemoryRepository repository) {
        return new CrudTransactionDistributorBuilder()
                .build();
    }

    private static CrudAccountDistributor createAccountDistributor(ICrudAccount accountService) {
        return new CrudAccountDistributorBuilder()
                .addCommand("all", new AccountAllAction(accountService))
                .addCommand("add", new AccountAddAction(accountService))
                .addCommand("delete", new AccountDeleteAction(accountService))
                .build();
    }

    private static CrudBankDistributor buildBankDistributor(ICrudBank bankService) {
        return new CrudBankDistributorBuilder()
                .addCommand("all", new BankAllAction(bankService))
                .addCommand("get", new BankGetAction(bankService))
                .addCommand("add", new BankAddAction(bankService))
                .addCommand("update", new BankUpdateAction(bankService))
                .addCommand("delete", new BankDeleteAction(bankService))
                .build();
    }

    private static BankEntity createVolksbank() {
        BankEntity vb = new BankEntity("Volksbank Karlsruhe eG", "VB");

        AccountEntity acc0 = new AccountEntity("Girokonto", "DE00 0000 0000 0000 0000 00", "VB-GK");
        acc0.addTransaction(new TransactionEntity(10000, "Jan Braunsdorff", "", "Start", false));
        acc0.addTransaction(new TransactionEntity(-5000, "", "Aldi", "Einkaufen", false));
        vb.addAccount(acc0);


        AccountEntity acc1 = new AccountEntity("Geschäftsanteile", "DE00 0000 0000 0000 0000 01", "VB-GA");
        vb.addAccount(acc1);


        AccountEntity acc2 = new AccountEntity("Kreditkarte", "DE00 0000 0000 0000 0000 02", "VB-KK");
        vb.addAccount(acc2);

        AccountEntity acc3 = new AccountEntity("Depot", "DE00 0000 0000 0000 0000 03", "VB-DT");
        vb.addAccount(acc3);

        return vb;
    }

    private static BankEntity createSparkasse() {
        BankEntity sk = new BankEntity("Spaßkasse", "SK");

        AccountEntity acc0 = new AccountEntity("Aktien", "DE00 0000 0000 0000 0000 04", "SK-AK");
        sk.addAccount(acc0);

        AccountEntity acc1 = new AccountEntity("Girokonto", "DE00 0000 0000 0000 0000 05", "SK-GK");
        sk.addAccount(acc1);

        AccountEntity acc2 = new AccountEntity("Vermögenswirksammeleistungen", "DE00 0000 0000 0000 0000 06", "SK-VL");
        sk.addAccount(acc2);

        AccountEntity acc3 = new AccountEntity("Altersvorsorge", "DE00 0000 0000 0000 0000 07", "SK-AV");
        sk.addAccount(acc3);

        return sk;
    }
}
