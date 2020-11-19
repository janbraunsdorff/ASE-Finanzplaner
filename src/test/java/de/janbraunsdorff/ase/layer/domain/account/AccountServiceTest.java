package de.janbraunsdorff.ase.layer.domain.account;

import de.janbraunsdorff.ase.layer.domain.AccountTestRepo;
import de.janbraunsdorff.ase.layer.domain.TransactionTestRepo;
import de.janbraunsdorff.ase.layer.persistence.BankNotFoundExecption;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class AccountServiceTest {
    @Test
    public void getAccountsOfBank() throws BankNotFoundExecption {
        AccountTestRepo accRepo = new AccountTestRepo();
        TransactionTestRepo tranRepo = new TransactionTestRepo();

        AccountService service = new AccountService(accRepo, tranRepo);

        List<AccountDTO> accounts = service.getAccountsOfBank(new AccountGetQuery("b1"));

        assertThat(accounts.size(), is(4));
        assertThat(accounts.get(0).getAccountNumber(), is("number1"));
        assertThat(accounts.get(0).getNumberOfTransaction(), is(4));
        assertThat(accounts.get(0).getName(), is("name1"));
        assertThat(accounts.get(0).getValue(), is(7));
    }
}
