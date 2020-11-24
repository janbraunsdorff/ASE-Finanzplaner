package de.janbraunsdorff.ase;


import de.janbraunsdorff.ase.layer.domain.account.Account;
import de.janbraunsdorff.ase.layer.domain.account.AccountApplication;
import de.janbraunsdorff.ase.layer.domain.account.AccountService;
import de.janbraunsdorff.ase.layer.domain.bank.Bank;
import de.janbraunsdorff.ase.layer.domain.bank.BankApplication;
import de.janbraunsdorff.ase.layer.domain.bank.BankService;
import de.janbraunsdorff.ase.layer.domain.transaction.Transaction;
import de.janbraunsdorff.ase.layer.domain.transaction.TransactionApplication;
import de.janbraunsdorff.ase.layer.domain.transaction.TransactionService;
import de.janbraunsdorff.ase.layer.domain.AcronymAlreadyExistsException;
import de.janbraunsdorff.ase.layer.persistence.memory.AccountMemoryRepository;
import de.janbraunsdorff.ase.layer.persistence.memory.BankMemoryRepository;
import de.janbraunsdorff.ase.layer.persistence.memory.TransactionMemoryRepository;
import de.janbraunsdorff.ase.layer.presentation.console.Distributor;
import de.janbraunsdorff.ase.layer.presentation.console.action.system.ExitAction;
import de.janbraunsdorff.ase.layer.presentation.console.DistributorAction;
import de.janbraunsdorff.ase.layer.presentation.console.DistributorActionFactory;
import de.janbraunsdorff.ase.layer.presentation.console.DistributorUsecaseFactory;
import de.janbraunsdorff.ase.layer.presentation.console.action.usecase.account.AccountAddAction;
import de.janbraunsdorff.ase.layer.presentation.console.action.usecase.account.AccountAllAction;
import de.janbraunsdorff.ase.layer.presentation.console.action.usecase.account.AccountHelpAction;
import de.janbraunsdorff.ase.layer.presentation.console.action.usecase.account.AccountDeleteAction;
import de.janbraunsdorff.ase.layer.presentation.console.action.usecase.bank.BankAddAction;
import de.janbraunsdorff.ase.layer.presentation.console.action.usecase.bank.BankAllAction;
import de.janbraunsdorff.ase.layer.presentation.console.action.usecase.bank.BankHelpAction;
import de.janbraunsdorff.ase.layer.presentation.console.action.usecase.bank.BankDeleteAction;
import de.janbraunsdorff.ase.layer.presentation.console.action.usecase.transaction.TransactionAllAction;
import de.janbraunsdorff.ase.layer.presentation.console.action.usecase.transaction.TransactionHelpAction;
import de.janbraunsdorff.ase.layer.presentation.console.action.usecase.account.AccountHelpResult;
import de.janbraunsdorff.ase.layer.presentation.console.action.usecase.bank.BankHelpResult;
import de.janbraunsdorff.ase.layer.presentation.console.action.usecase.transaction.TransactionHelpResult;

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
        AccountService accountService = new AccountService(accountRepo, transactionRepo, bankRepo);
        BankService bankService = new BankService(bankRepo, accountRepo, transactionRepo);
        TransactionService transactionService = new TransactionService(transactionRepo, accountRepo);


//      ++++ create presentation / input layer +++
//      build distributor
        Distributor crudBankDistributor = buildBankDistributor(bankService);
        Distributor crudAccountDistributor = createAccountDistributor(accountService);
        Distributor crudTransactionDistributor = createTransactionDistributor(transactionService);


//      composite distributor
        DistributorAction controller = new DistributorActionFactory()
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
        return new DistributorUsecaseFactory(new TransactionHelpResult(), new TransactionHelpAction())
                .addCommand("all", new TransactionAllAction(service))
                .build();
    }

    private static Distributor createAccountDistributor(AccountApplication accountService) {
        return new DistributorUsecaseFactory(new AccountHelpResult(), new AccountHelpAction())
                .addCommand("all", new AccountAllAction(accountService))
                .addCommand("add", new AccountAddAction(accountService))
                .addCommand("delete", new AccountDeleteAction(accountService))
                .build();
    }

    private static Distributor buildBankDistributor(BankApplication bankService) {
        return new DistributorUsecaseFactory(new BankHelpResult(), new BankHelpAction())
                .addCommand("all", new BankAllAction(bankService))
                .addCommand("add", new BankAddAction(bankService))
                .addCommand("delete", new BankDeleteAction(bankService))
                .build();
    }

    private static void createVolksbank(BankMemoryRepository bankRepo, AccountMemoryRepository accountRepo, TransactionMemoryRepository transactionRepo) throws AcronymAlreadyExistsException {
        Bank vb = new Bank("Volksbank Karlsruhe eG", "VB");
        bankRepo.createBank(vb);

        Account acc0 = new Account(vb.getAcronym(), "Girokonto", "DE00 0000 0000 0000 0000 00", "VB-GK");
        accountRepo.createAccount(acc0);

        transactionRepo.createTransaction(new Transaction(acc0.getAcronym(), 10000, LocalDate.now().minusDays(2), "Jan Braunsdorff", "Start", false));
        transactionRepo.createTransaction(new Transaction(acc0.getAcronym(), -5000, LocalDate.now(), "Aldi", "Einkaufen", false));

        accountRepo.createAccount(new Account(vb.getAcronym(), "Geschäftsanteile", "DE00 0000 0000 0000 0000 01", "VB-GA"));
        accountRepo.createAccount(new Account(vb.getAcronym(), "Kreditkarte", "DE00 0000 0000 0000 0000 02", "VB-KK"));
        accountRepo.createAccount(new Account(vb.getAcronym(), "Depot", "DE00 0000 0000 0000 0000 03", "VB-DT"));

    }

    private static Bank createSpasskasse(BankMemoryRepository bankRepo, AccountMemoryRepository accountRepo) throws AcronymAlreadyExistsException {
        Bank sk = new Bank("Spaßkasse", "SK");
        bankRepo.createBank(sk);


        accountRepo.createAccount(new Account(sk.getAcronym(), "Aktien", "DE00 0000 0000 0000 0000 04", "SK-AK"));
        accountRepo.createAccount(new Account(sk.getAcronym(), "Girokonto", "DE00 0000 0000 0000 0000 05", "SK-GK"));
        accountRepo.createAccount(new Account(sk.getAcronym(), "Vermögenswirksammeleistungen", "DE00 0000 0000 0000 0000 06", "SK-VL"));
        accountRepo.createAccount(new Account(sk.getAcronym(), "Altersvorsorge", "DE00 0000 0000 0000 0000 07", "SK-AV"));

        return sk;
    }
}
