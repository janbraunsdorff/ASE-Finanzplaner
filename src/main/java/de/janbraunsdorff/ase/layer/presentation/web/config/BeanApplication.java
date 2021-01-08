package de.janbraunsdorff.ase.layer.presentation.web.config;

import de.janbraunsdorff.ase.layer.domain.account.AccountApplication;
import de.janbraunsdorff.ase.layer.domain.account.AccountRepository;
import de.janbraunsdorff.ase.layer.domain.account.AccountServiceCrud;
import de.janbraunsdorff.ase.layer.domain.bank.BankApplication;
import de.janbraunsdorff.ase.layer.domain.bank.BankRepository;
import de.janbraunsdorff.ase.layer.domain.bank.BankService;
import de.janbraunsdorff.ase.layer.domain.transaction.TransactionApplication;
import de.janbraunsdorff.ase.layer.domain.transaction.TransactionRepository;
import de.janbraunsdorff.ase.layer.domain.transaction.TransactionService;
import de.janbraunsdorff.ase.layer.persistence.json.account.AccountJsonRepository;
import de.janbraunsdorff.ase.layer.persistence.json.bank.BankJsonRepository;
import de.janbraunsdorff.ase.layer.persistence.json.transaction.TransactionJsonRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanApplication {
    private final String repoBasePath = "/Users/janbraunsdorff/ASE-Finanzplaner/";

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

    @Bean
    public BankApplication createBankApplication(BankRepository br, AccountRepository ar, TransactionRepository tr){
        return new BankService(br, ar, tr);
    }

    @Bean
    public AccountApplication createAccountApplication(BankRepository br, AccountRepository ar, TransactionRepository tr){
        return new AccountServiceCrud(ar, tr, br);
    }

    @Bean
    public TransactionApplication createTransactionApplication(AccountRepository ar, TransactionRepository tr){
        return new TransactionService(tr, ar);
    }
}
