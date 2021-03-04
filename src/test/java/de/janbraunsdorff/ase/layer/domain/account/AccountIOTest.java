package de.janbraunsdorff.ase.layer.domain.account;

import de.janbraunsdorff.ase.layer.domain.AccountNotFoundException;
import de.janbraunsdorff.ase.layer.domain.BankNotFoundException;
import de.janbraunsdorff.ase.layer.domain.account.data.Account;
import de.janbraunsdorff.ase.layer.domain.account.data.AccountDTO;
import de.janbraunsdorff.ase.layer.domain.account.querry.AccountsGetByAcronymQuery;
import de.janbraunsdorff.ase.layer.domain.bank.Bank;
import de.janbraunsdorff.ase.layer.domain.bank.BankType;
import de.janbraunsdorff.ase.layer.domain.repo.TestAccountRepository;
import de.janbraunsdorff.ase.layer.domain.repo.TestBankRepository;
import de.janbraunsdorff.ase.layer.domain.repo.TestTransactionRepository;
import de.janbraunsdorff.ase.layer.domain.transaction.Transaction;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


class AccountIOTest {
    @Test
    public void isInstanceObAccountIOApplication() {
        var io = new AccountIO(null, null, null);
        assertThat(io, Matchers.instanceOf(AccountIOApplication.class));
    }

    @Test
    public void getAccountsByAcronym() throws BankNotFoundException, AccountNotFoundException {
        var accountRepo = new TestAccountRepository(List.of(
                new Account("bank1", "account1", "1234","ac1"),
                new Account("bank1", "account2", "4321","ac2")
        ));

        var transactionRepo = new TestTransactionRepository(
                List.of(
                        new Transaction("ac1", 10, LocalDate.now(), "thp", "cat", false),
                        new Transaction("ac1", 20, LocalDate.now(), "thp", "cat", false)
                )
        );

        var bankRepo = new TestBankRepository(
                new Bank("name", "bank1", BankType.Retail)
        );
        var io = new AccountIO(accountRepo, transactionRepo, bankRepo);
        List<AccountDTO> res = io.getAccount(new AccountsGetByAcronymQuery(List.of("ac1", "ac2")));

        assertThat(res.size(), Matchers.is(2));
        assertThat(accountRepo.getAccountByAcronym, Matchers.containsInAnyOrder("ac1", "ac2"));
        assertThat(bankRepo.getBankByAcronym, Matchers.is("bank1"));

        assertThat(res.get(0).getAcronym(), Matchers.is("ac1"));
        assertThat(res.get(0).getAccountNumber(), Matchers.is("1234"));
        assertThat(res.get(0).getValue().getValue(), Matchers.is(30));
        assertThat(res.get(0).getBankName(), Matchers.is("name"));
        assertThat(res.get(0).getNumberOfTransaction(), Matchers.is(2));

        assertThat(res.get(1).getAcronym(), Matchers.is("ac2"));
        assertThat(res.get(1).getAccountNumber(), Matchers.is("4321"));
        assertThat(res.get(1).getValue().getValue(), Matchers.is(0));
        assertThat(res.get(1).getBankName(), Matchers.is("name"));
        assertThat(res.get(1).getNumberOfTransaction(), Matchers.is(0));

    }

    @Test
    public void getAccountsByAcronymMissingBank() {
        var accountRepo = new TestAccountRepository(List.of(
                new Account("bank1", "account1", "1234","ac1"),
                new Account("bank1", "account2", "4321","ac2")
        ));

        var transactionRepo = new TestTransactionRepository(
                List.of(
                        new Transaction("ac1", 10, LocalDate.now(), "thp", "cat", false),
                        new Transaction("ac1", 20, LocalDate.now(), "thp", "cat", false)
                )
        );

        var bankRepo = new TestBankRepository();
        var io = new AccountIO(accountRepo, transactionRepo, bankRepo);
        assertThrows(BankNotFoundException.class, () -> io.getAccount(new AccountsGetByAcronymQuery(List.of("ac1", "ac2"))));

    }

    @Test
    public void getAccountsByAcronymMissingAccount() {
        var accountRepo = new TestAccountRepository(List.of(
                new Account("bank1", "account1", "1234","ac1")
        ));

        var transactionRepo = new TestTransactionRepository(
                List.of(
                        new Transaction("ac1", 10, LocalDate.now(), "thp", "cat", false),
                        new Transaction("ac1", 20, LocalDate.now(), "thp", "cat", false)
                )
        );

        var bankRepo = new TestBankRepository(
                new Bank("name", "bank1", BankType.Retail)
        );
        var io = new AccountIO(accountRepo, transactionRepo, bankRepo);
        assertThrows(AccountNotFoundException.class, () -> io.getAccount(new AccountsGetByAcronymQuery(List.of("ac1", "ac2"))));

    }
}
