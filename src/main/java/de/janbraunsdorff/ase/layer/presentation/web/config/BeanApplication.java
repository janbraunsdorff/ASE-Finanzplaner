package de.janbraunsdorff.ase.layer.presentation.web.config;

import de.janbraunsdorff.ase.layer.domain.account.*;
import de.janbraunsdorff.ase.layer.domain.bank.BankApplication;
import de.janbraunsdorff.ase.layer.domain.bank.BankRepository;
import de.janbraunsdorff.ase.layer.domain.bank.BankService;
import de.janbraunsdorff.ase.layer.domain.transaction.TransactionApplication;
import de.janbraunsdorff.ase.layer.domain.transaction.TransactionRepository;
import de.janbraunsdorff.ase.layer.domain.transaction.TransactionService;
import de.janbraunsdorff.ase.layer.persistence.database.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
    public AccountRepository createAccountRepository(AccountSpringRepository repo){
        return new AccountDatabaseRepository(repo);
    }

    @Bean
    public TransactionRepository createTransactionRepository(TransactionSpringRepository repo){
        return new TransactionDatabaseRepository(repo);
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
     public AccountAnalyticsApplication createAccountAnalyticApplication(BankRepository br, AccountRepository ar, TransactionRepository tr, AccountIOApplication ioA){
        return new AccountAnalytics(ar, tr, br, ioA);
    }

    @Bean
    public TransactionApplication createTransactionApplication(AccountRepository ar, TransactionRepository tr){
        return new TransactionService(tr, ar);
    }

}
