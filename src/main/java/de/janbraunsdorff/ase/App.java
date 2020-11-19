package de.janbraunsdorff.ase;


import de.janbraunsdorff.ase.layer.domain.bank.BankService;
import de.janbraunsdorff.ase.layer.domain.account.AccountService;
import de.janbraunsdorff.ase.layer.domain.transaction.*;
import de.janbraunsdorff.ase.layer.domain.account.Account;
import de.janbraunsdorff.ase.layer.domain.bank.Bank;
import de.janbraunsdorff.ase.layer.domain.transaction.Transaction;
import de.janbraunsdorff.ase.layer.persistence.AcronymAlreadyExistsException;
import de.janbraunsdorff.ase.layer.persistence.memory.AccountMemoryRepository;
import de.janbraunsdorff.ase.layer.persistence.memory.BankMemoryRepository;
import de.janbraunsdorff.ase.layer.persistence.memory.TransactionMemoryRepository;
import de.janbraunsdorff.ase.layer.presentation.AccountApplication;
import de.janbraunsdorff.ase.layer.presentation.BankApplication;
import de.janbraunsdorff.ase.layer.presentation.TransactionApplication;
import de.janbraunsdorff.ase.layer.presentation.console.Distributor;
import de.janbraunsdorff.ase.layer.presentation.console.ExitAction;
import de.janbraunsdorff.ase.layer.presentation.console.UseCaseController;
import de.janbraunsdorff.ase.layer.presentation.console.UseCaseControllerBuilder;
import de.janbraunsdorff.ase.layer.presentation.console.actions.DistributorBuilder;
import de.janbraunsdorff.ase.layer.presentation.console.actions.account.AccountAddAction;
import de.janbraunsdorff.ase.layer.presentation.console.actions.account.AccountAllAction;
import de.janbraunsdorff.ase.layer.presentation.console.actions.account.AccountDefaultAction;
import de.janbraunsdorff.ase.layer.presentation.console.actions.account.AccountDeleteAction;
import de.janbraunsdorff.ase.layer.presentation.console.actions.bank.*;
import de.janbraunsdorff.ase.layer.presentation.console.actions.transaction.TransactionAllAction;
import de.janbraunsdorff.ase.layer.presentation.console.actions.transaction.TransactionDefaultAction;
import de.janbraunsdorff.ase.layer.presentation.console.result.account.AccountHelpResult;
import de.janbraunsdorff.ase.layer.presentation.console.result.bank.BankHelpResult;
import de.janbraunsdorff.ase.layer.presentation.console.result.transaction.TransactionHelpResult;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDate;

public class App {

    public static void main(String[] args) throws Exception {
        System.out.println("Dein Finanzplaner wird aufgebaut");
//      ++++ create Persistence layer ++++
        BankMemoryRepository bankRepo = new BankMemoryRepository();
        AccountMemoryRepository accountRepo = new AccountMemoryRepository();
        TransactionMemoryRepository transactionRepo = new TransactionMemoryRepository();
        

        createVolksbank(bankRepo, accountRepo, transactionRepo); // Create Demo Data Volksbank
        createSpasskasse(bankRepo, accountRepo); // Create Demo Data Spaßkasse

//      ++++ create domain layer ++++
        AccountService accountService = new AccountService(accountRepo, bankRepo, transactionRepo);
        BankService bankService = new BankService(bankRepo, accountRepo, transactionRepo);
        TransactionService transactionService = new TransactionService(transactionRepo, accountRepo);


//      ++++ create presentation / input layer +++
//      build distributor
        Distributor crudBankDistributor = buildBankDistributor(bankService);
        Distributor crudAccountDistributor = createAccountDistributor(accountService);
        Distributor crudTransactionDistributor = createTransactionDistributor(transactionService);


//      composite distributor
        UseCaseController controller = new UseCaseControllerBuilder()
                .addUseCase("bank", crudBankDistributor)
                .addUseCase("account", crudAccountDistributor)
                .addUseCase("transaction", crudTransactionDistributor)
                .addUseCase("exit", new ExitAction())
                .build();

        System.out.println("Dein Planer steht zur benutzung bereit");
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

    private static Distributor createTransactionDistributor(TransactionApplication service) {
        return new DistributorBuilder(new TransactionHelpResult(), new TransactionDefaultAction())
                .addCommand("all", new TransactionAllAction(service))
                .build();
    }

    private static Distributor createAccountDistributor(AccountApplication accountService) {
        return new DistributorBuilder(new AccountHelpResult(), new AccountDefaultAction())
                .addCommand("all", new AccountAllAction(accountService))
                .addCommand("add", new AccountAddAction(accountService))
                .addCommand("delete", new AccountDeleteAction(accountService))
                .build();
    }

    private static Distributor buildBankDistributor(BankApplication bankService) {
        return new DistributorBuilder(new BankHelpResult(), new BankDefaultAction())
                .addCommand("all", new BankAllAction(bankService))
                .addCommand("add", new BankAddAction(bankService))
                .addCommand("delete", new BankDeleteAction(bankService))
                .build();
    }

    private static void createVolksbank(BankMemoryRepository bankRepo, AccountMemoryRepository accountRepo, TransactionMemoryRepository transactionRepo) throws AcronymAlreadyExistsException {
        Bank vb = new Bank("Volksbank Karlsruhe eG", "VB");
        bankRepo.createBank(vb);

        Account acc0 = new Account(vb.getId(), "Girokonto", "DE00 0000 0000 0000 0000 00", "VB-GK");
        accountRepo.createAccount(acc0);

        transactionRepo.createTransaction(new Transaction(acc0.getId(), 10000, LocalDate.now().minusDays(2), "Jan Braunsdorff", "Start", false, 1));
        transactionRepo.createTransaction(new Transaction(acc0.getId(), -5000, LocalDate.now(), "Aldi", "Einkaufen", false, 2));

        accountRepo.createAccount(new Account(vb.getId(), "Geschäftsanteile", "DE00 0000 0000 0000 0000 01", "VB-GA"));
        accountRepo.createAccount(new Account(vb.getId(), "Kreditkarte", "DE00 0000 0000 0000 0000 02", "VB-KK"));
        accountRepo.createAccount(new Account(vb.getId(), "Depot", "DE00 0000 0000 0000 0000 03", "VB-DT"));

    }

    private static Bank createSpasskasse(BankMemoryRepository bankRepo, AccountMemoryRepository accountRepo) throws AcronymAlreadyExistsException {
        Bank sk = new Bank("Spaßkasse", "SK");
        bankRepo.createBank(sk);


        accountRepo.createAccount(new Account(sk.getId(), "Aktien", "DE00 0000 0000 0000 0000 04", "SK-AK"));
        accountRepo.createAccount(new Account(sk.getId(), "Girokonto", "DE00 0000 0000 0000 0000 05", "SK-GK"));
        accountRepo.createAccount(new Account(sk.getId(), "Vermögenswirksammeleistungen", "DE00 0000 0000 0000 0000 06", "SK-VL"));
        accountRepo.createAccount(new Account(sk.getId(), "Altersvorsorge", "DE00 0000 0000 0000 0000 07", "SK-AV"));

        return sk;
    }
}
