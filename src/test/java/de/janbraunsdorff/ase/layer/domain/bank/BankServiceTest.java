package de.janbraunsdorff.ase.layer.domain.bank;

import static org.hamcrest.MatcherAssert.assertThat;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import de.janbraunsdorff.ase.layer.domain.AcronymAlreadyExistsException;
import de.janbraunsdorff.ase.layer.domain.account.data.Account;
import de.janbraunsdorff.ase.layer.domain.repo.TestAccountRepository;
import de.janbraunsdorff.ase.layer.domain.repo.TestBankRepository;
import de.janbraunsdorff.ase.layer.domain.repo.TestTransactionRepository;
import de.janbraunsdorff.ase.layer.domain.transaction.Transaction;


class BankServiceTest {

    @Test
    public void get(){
        var bankRepo = new TestBankRepository(
                List.of(
                    new Bank("b1", "ac1", BankType.Retail),
                    new Bank("b2", "ac2", BankType.Investment)
                )
        );

        var accRepo = new TestAccountRepository(
                List.of(
                    new Account("ac1", "a1", "n1", "acr1"),
                    new Account("ac1", "a2", "n2", "acr2")
                )
        );

        var transRepo = new TestTransactionRepository(
                List.of(
                        new Transaction("acr1", 10, LocalDate.of(2020, 12, 30), "thp1", "cat", false, "contractName"),
                        new Transaction("acr1", 50, LocalDate.of(2020, 12, 1), "thp1", "cat", false, "contractName")
                )
        );

        var service = new BankService(bankRepo, accRepo, transRepo);
        List<BankDTO> bankDTOS = service.get();

        assertThat(bankDTOS.size(), Matchers.is(2));
        assertThat(bankDTOS.get(0).acronym(), Matchers.is("ac1"));
        assertThat(bankDTOS.get(0).name(), Matchers.is("b1"));
        assertThat(bankDTOS.get(0).value().getValue(), Matchers.is(60));
        assertThat(bankDTOS.get(0).numberOfAccount(), Matchers.is(2));
        assertThat(bankDTOS.get(0).type(), Matchers.is(BankType.Retail));

        assertThat(bankDTOS.get(1).acronym(), Matchers.is("ac2"));
        assertThat(bankDTOS.get(1).name(), Matchers.is("b2"));
        assertThat(bankDTOS.get(1).value().getValue(), Matchers.is(0));
        assertThat(bankDTOS.get(1).numberOfAccount(), Matchers.is(0));
        assertThat(bankDTOS.get(1).type(), Matchers.is(BankType.Investment));
    }

    @Test
    public void getWithEmptyRepo(){
        var bankRepo = new TestBankRepository(
                Collections.emptyList()
        );

        var accRepo = new TestAccountRepository(
                Collections.emptyList()
        );

        var transRepo = new TestTransactionRepository(
                Collections.emptyList()
        );

        var service = new BankService(bankRepo, accRepo, transRepo);
        List<BankDTO> bankDTOS = service.get();

        assertThat(bankDTOS.size(), Matchers.is(0));
    }

    @Test
    public void create() throws AcronymAlreadyExistsException {
        var bankRepo = new TestBankRepository(
                Collections.emptyList()
        );

        var accRepo = new TestAccountRepository(
                Collections.emptyList()
        );

        var transRepo = new TestTransactionRepository(
                Collections.emptyList()
        );

        var service = new BankService(bankRepo, null, null);
        BankDTO bankDTOS = service.create(new BankCreateCommand("name", "ac", "Investment Bank"));

        assertThat(bankDTOS.name(), Matchers.is("name"));
        assertThat(bankDTOS.acronym(), Matchers.is("ac"));
        assertThat(bankDTOS.value().getValue(), Matchers.is(0));
        assertThat(bankDTOS.numberOfAccount(), Matchers.is(0));
        assertThat(bankDTOS.type(), Matchers.is(BankType.Investment));

        assertThat(bankRepo.createBank.getAcronym(), Matchers.is("ac"));
        assertThat(bankRepo.createBank.getName(), Matchers.is("name"));
        assertThat(bankRepo.createBank.getType(), Matchers.is(BankType.Investment));
    }

    @Test
    public void delete(){
        var bankRepo = new TestBankRepository(
                Collections.emptyList()
        );

        var accRepo = new TestAccountRepository(
                Collections.emptyList()
        );

        var transRepo = new TestTransactionRepository(
                Collections.emptyList()
        );

        var service = new BankService(bankRepo, accRepo, transRepo);
        service.deleteByAcronym(new BankDeleteCommand("ac"));

        assertThat(bankRepo.deleteBankByAcronym, Matchers.is("ac"));
    }
}
