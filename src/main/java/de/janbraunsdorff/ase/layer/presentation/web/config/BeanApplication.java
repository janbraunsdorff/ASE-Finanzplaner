package de.janbraunsdorff.ase.layer.presentation.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import de.janbraunsdorff.ase.layer.domain.account.AccountAnalytics;
import de.janbraunsdorff.ase.layer.domain.account.AccountAnalyticsApplication;
import de.janbraunsdorff.ase.layer.domain.account.AccountIO;
import de.janbraunsdorff.ase.layer.domain.account.AccountIOApplication;
import de.janbraunsdorff.ase.layer.domain.account.AccountRepository;
import de.janbraunsdorff.ase.layer.domain.bank.BankApplication;
import de.janbraunsdorff.ase.layer.domain.bank.BankRepository;
import de.janbraunsdorff.ase.layer.domain.bank.BankService;
import de.janbraunsdorff.ase.layer.domain.contract.ContractIO;
import de.janbraunsdorff.ase.layer.domain.contract.ContractIOApplication;
import de.janbraunsdorff.ase.layer.domain.contract.ContractRepository;
import de.janbraunsdorff.ase.layer.domain.transaction.TransactionApplication;
import de.janbraunsdorff.ase.layer.domain.transaction.TransactionRepository;
import de.janbraunsdorff.ase.layer.domain.transaction.TransactionService;
import de.janbraunsdorff.ase.layer.persistence.database.AccountDatabaseRepository;
import de.janbraunsdorff.ase.layer.persistence.database.AccountSpringRepository;
import de.janbraunsdorff.ase.layer.persistence.database.BankDatabaseRepository;
import de.janbraunsdorff.ase.layer.persistence.database.BankSpringRepository;
import de.janbraunsdorff.ase.layer.persistence.database.ContractDatabaseRepository;
import de.janbraunsdorff.ase.layer.persistence.database.ContractSpringRepository;
import de.janbraunsdorff.ase.layer.persistence.database.TransactionDatabaseRepository;
import de.janbraunsdorff.ase.layer.persistence.database.TransactionSpringRepository;

@Configuration
public class BeanApplication {
    private final String repoBasePath = "/Users/janbraunsdorff/ASE-Finanzplaner/";

    /*
    //--------------------------
    // Json Repo
    //--------------------------
    @Bean
    public BankRepository createBankRepository(){
        return new BankJsonRepository(repoBasePath);
    }

    @Bean
    public AccountRepository createAccountRepository(){
        return new AccountJsonRepository(repoBasePath);
    }

    @Bean
    public TransactionRepository createTransactionRepository(){
        return new TransactionJsonRepository(repoBasePath);
    }
     */

    //--------------------------
    // DB Repo
    //--------------------------
    @Bean
    public BankRepository createBankRepository(BankSpringRepository repo){
        return new BankDatabaseRepository(repo);
    }

    @Bean
    public AccountRepository createAccountRepository(AccountSpringRepository repo, BankSpringRepository bankRepo){
        return new AccountDatabaseRepository(repo, bankRepo);
    }

    @Bean
    public TransactionRepository createTransactionRepository(TransactionSpringRepository repo, AccountSpringRepository accountRepo){
        return new TransactionDatabaseRepository(repo, accountRepo);
    }

    @Bean
    public ContractRepository createContractRepository(ContractSpringRepository repo, AccountSpringRepository accountRepo){
        return new ContractDatabaseRepository(repo, accountRepo);
    }


    //--------------------------
    // Domain
    //--------------------------
    @Bean
    public BankApplication createBankApplication(BankRepository br, AccountRepository ar, TransactionRepository tr){
        return new BankService(br, ar, tr);
    }

    @Bean
    public AccountIOApplication createAccountApplication(BankRepository br, AccountRepository ar, TransactionRepository tr){
        return new AccountIO(ar, tr, br);
    }

     @Bean
     public AccountAnalyticsApplication createAccountAnalyticApplication(AccountRepository ar, TransactionRepository tr, AccountIOApplication ioA){
        return new AccountAnalytics(ar, tr, ioA);
    }

    @Bean
    public TransactionApplication createTransactionApplication(AccountRepository ar, TransactionRepository tr){
        return new TransactionService(tr, ar);
    }

    @Bean
    public ContractIOApplication createAccountIoApplication(ContractRepository cr, TransactionRepository tr){
        return new ContractIO(cr, tr);
    }

}
