package de.janbraunsdorff.ase.layer.domain.account;

import de.janbraunsdorff.ase.layer.domain.AccountTestRepo;
import de.janbraunsdorff.ase.layer.domain.BankTestRepo;
import de.janbraunsdorff.ase.layer.domain.TransactionTestRepo;
import de.janbraunsdorff.ase.layer.domain.AccountNotFoundException;
import de.janbraunsdorff.ase.layer.domain.AcronymAlreadyExistsException;
import de.janbraunsdorff.ase.layer.domain.BankNotFoundException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AccountServiceTest {
    @Test
    public void getAccountsOfBank() throws BankNotFoundException {
        BankTestRepo bankRepo = new BankTestRepo();
        AccountTestRepo accRepo = new AccountTestRepo();
        TransactionTestRepo tranRepo = new TransactionTestRepo();

        AccountServiceCrud service = new AccountServiceCrud(accRepo, tranRepo, bankRepo);

        List<AccountDTO> accounts = service.getAccountsOfBank(new AccountGetQuery("b1"));

        assertThat(accounts.size(), is(4));
        assertThat(accounts.get(0).getAccountNumber(), is("number1"));
        assertThat(accounts.get(0).getNumberOfTransaction(), is(4));
        assertThat(accounts.get(0).getName(), is("name1"));
        assertThat(accounts.get(0).getValue(), is(7));
        assertThat(accounts.get(0).getAcronym(), is("a1"));
        assertThat(accounts.get(0).getBankName(), is("bankName"));
    }

    @Test
    public void createAccountWithExistingBank() throws BankNotFoundException, AcronymAlreadyExistsException {
        BankTestRepo bankRepo = new BankTestRepo();
        AccountTestRepo accRepo = new AccountTestRepo();
        TransactionTestRepo tranRepo = new TransactionTestRepo();

        AccountServiceCrud service = new AccountServiceCrud(accRepo, tranRepo, bankRepo);

        AccountDTO accountByAcronym = service.createAccountByAcronym(new AccountCreateCommand("bankAcronymCreate", "name", "number", "accountAcronym"));

        assertThat(accountByAcronym.getAccountNumber(), is("number"));
        assertThat(accountByAcronym.getNumberOfTransaction(), is(0));
        assertThat(accountByAcronym.getName(), is("name"));
        assertThat(accountByAcronym.getValue(), is(0));
        assertThat(accountByAcronym.getAcronym(), is("accountAcronym"));
        assertThat(accountByAcronym.getBankName(), is("bankName"));

    }

    @Test
    public void createAccountWithNonExistingBank() {
        BankTestRepo bankRepo = new BankTestRepo();
        AccountTestRepo accRepo = new AccountTestRepo();
        TransactionTestRepo tranRepo = new TransactionTestRepo();

        AccountServiceCrud service = new AccountServiceCrud(accRepo, tranRepo, bankRepo);

        assertThrows(BankNotFoundException.class, () -> service.createAccountByAcronym(new AccountCreateCommand("nonExistingBank", "", "", "")));
    }

    @Test
    public void deleteAccount() throws AccountNotFoundException {
        BankTestRepo bankRepo = new BankTestRepo();
        AccountTestRepo accRepo = new AccountTestRepo();
        TransactionTestRepo tranRepo = new TransactionTestRepo();

        AccountServiceCrud service = new AccountServiceCrud(accRepo, tranRepo, bankRepo);
        service.deleteByAcronym(new AccountDeleteCommand("accountAcronym"));

        assertThat(accRepo.acronymDelete, is("accountAcronym"));
    }
}
