package de.janbraunsdorff.ase;

import de.janbraunsdorff.ase.layer.domain.account.AccountIOApplication;
import de.janbraunsdorff.ase.layer.domain.bank.BankApplication;
import de.janbraunsdorff.ase.layer.domain.transaction.TransactionApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ApplicationWeb {

    public void run(String... args) {
        SpringApplication.run(ApplicationWeb.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(AccountIOApplication accountApplication, BankApplication bankApplication, TransactionApplication transactionApplication) {
        return args -> {
            new ApplicationConsoleBuilder()
                    .initWithSpringBeans(bankApplication, accountApplication, transactionApplication)
                    .createOverlay()
                    .run();
        };
    }
}
