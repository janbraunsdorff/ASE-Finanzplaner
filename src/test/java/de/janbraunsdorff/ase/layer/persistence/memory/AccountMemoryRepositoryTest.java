package de.janbraunsdorff.ase.layer.persistence.memory;

import de.janbraunsdorff.ase.layer.domain.account.Account;
import de.janbraunsdorff.ase.layer.domain.AccountNotFoundException;
import de.janbraunsdorff.ase.layer.domain.AcronymAlreadyExistsException;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AccountMemoryRepositoryTest {

    @SuppressWarnings("unchecked")
    private Map<String, Account> getMemory(AccountMemoryRepository obj) {
        try {
            Field f = obj.getClass().getDeclaredField("accounts"); //NoSuchFieldException
            f.setAccessible(true);
            return (Map<String, Account>) f.get(obj);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            throw new IllegalArgumentException();
        }
    }

    @Test
    public void givenAccountEmptyRepository_createAccount() throws AcronymAlreadyExistsException {
        AccountMemoryRepository repo = new AccountMemoryRepository();

        repo.createAccount(new Account("bankAc", "name", "number", "ac"));

        Account ac = getMemory(repo).get("ac");

        assertThat(ac.getBankAcronym(), is("bankAc"));
        assertThat(ac.getName(), is("name"));
        assertThat(ac.getNumber(), is("number"));
        assertThat(ac.getAcronym(), is("ac"));
    }

    @Test
    public void givenAccountRepositoryWithExistingAcronym_createAccount() {
        AccountMemoryRepository repo = new AccountMemoryRepository();
        getMemory(repo).put("ac", null);

        assertThrows(AcronymAlreadyExistsException.class, () ->
                repo.createAccount(new Account("bankId", "name", "number", "ac")));

    }


    @Test
    public void givenAccountRepositoryWithAccount_returnsAccount() throws AccountNotFoundException {
        AccountMemoryRepository repo = new AccountMemoryRepository();

        Account acc = new Account("bankAc", "name", "number", "ac");
        getMemory(repo).put("ac", acc);

        Account ac = repo.getAccountByAcronym("ac");


        assertThat(ac.getBankAcronym(), is("bankAc"));
        assertThat(ac.getName(), is("name"));
        assertThat(ac.getNumber(), is("number"));
        assertThat(ac.getAcronym(), is("ac"));
    }

    @Test
    public void givenAccountRepositoryWithNoAccount_throwsError() {
        AccountMemoryRepository repo = new AccountMemoryRepository();

        assertThrows(AccountNotFoundException.class, () -> repo.getAccountByAcronym("ac"));
    }

    @Test
    public void givenAccountRepositoryWithMultipleAccountsOfDifferentBanks_returnsOnlyAccountOfOneBank(){
        AccountMemoryRepository repo = new AccountMemoryRepository();

        Account acc = new Account("bankAc", "name1", "number1", "ac1");
        getMemory(repo).put("ac1", acc);

        Account acc2 = new Account("bankAc", "name2", "number2", "ac2");
        getMemory(repo).put("ac2", acc2);

        Account acc3 = new Account("non", "name3", "number3", "ac3");
        getMemory(repo).put("ac3", acc3);

        List<Account> bankAc = repo.getAccountsOfBankByBankAcronym("bankAc");

        assertThat(bankAc.size(), is(2));
    }

    @Test
    public void givenAccountRepositoryWithAccount_deleteAccount() {
        AccountMemoryRepository repo = new AccountMemoryRepository();

        Account acc = new Account("bankAc", "name1", "number1", "ac1");
        getMemory(repo).put("ac1", acc);

       repo.deleteAccountByAcronym("ac1");

        assertThat(getMemory(repo).size(), is(0));
    }
}
