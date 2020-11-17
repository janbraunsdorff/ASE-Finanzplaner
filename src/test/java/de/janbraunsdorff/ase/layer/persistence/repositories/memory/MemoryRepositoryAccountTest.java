package de.janbraunsdorff.ase.layer.persistence.repositories.memory;


import de.janbraunsdorff.ase.layer.domain.crud.entitties.Account;
import de.janbraunsdorff.ase.layer.domain.crud.repository.exceptions.AccountNotFoundException;
import de.janbraunsdorff.ase.layer.domain.crud.repository.exceptions.AcronymAlreadyExistsException;
import de.janbraunsdorff.ase.layer.domain.crud.repository.exceptions.BankNotFoundExecption;
import de.janbraunsdorff.ase.layer.persistence.repositories.memory.entität.AccountMemoryEntity;
import de.janbraunsdorff.ase.layer.persistence.repositories.memory.entität.BankMemoryEntity;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MemoryRepositoryAccountTest {

    @SuppressWarnings("unchecked")
    @Test
    public void createAccount() throws Exception {
        MemoryRepository repo = new MemoryRepository();
        Field f = repo.getClass().getDeclaredField("memory");
        f.setAccessible(true);
        Object field = f.get(repo);
        Map<String, BankMemoryEntity> memory = (HashMap<String, BankMemoryEntity>) field;
        BankMemoryEntity entity = new BankMemoryEntity("ID", "name", "acronym");
        memory.put("ID", entity);

        repo.createAccountByBankId("ID", new Account("account", "123", "AC"));

        AccountMemoryEntity account = new ArrayList<>(memory.get("ID").getAccounts()).get(0);

        assertThat(account.getName(), is("account"));
        assertThat(account.getNumber(), is("123"));
        assertThat(account.getAcronym(), is("AC"));
    }

    @Test
    public void createAccountBankNotExists(){
        MemoryRepository repo = new MemoryRepository();

        Exception exception = assertThrows(BankNotFoundExecption.class, () -> repo.createAccountByBankId("ACC-ID", new Account("","","")));
        String expected = "Bank mit der ID oder der Abkürzung ACC-ID wurde nicht gefunden";

        assertThat(expected, is(exception.getMessage()));
    }

    @SuppressWarnings("unchecked")
    @Test
    public void createAccountWithExistingAcronym() throws Exception {
        MemoryRepository repo = new MemoryRepository();
        Field f = repo.getClass().getDeclaredField("memory");
        f.setAccessible(true);
        Object field = f.get(repo);
        Map<String, BankMemoryEntity> memory = (HashMap<String, BankMemoryEntity>) field;
        BankMemoryEntity entity = new BankMemoryEntity("ID", "name", "acronym");
        entity.addAccount(new AccountMemoryEntity("name", "123", "AC"));
        memory.put("ID", entity);


        Exception exception = assertThrows(AcronymAlreadyExistsException.class, () -> repo.createAccountByBankId("ID", new Account("","","AC")));
        String expected = "Die Abkürzung AC existiert bereits im System";

        assertThat(expected, is(exception.getMessage()));

    }

    @SuppressWarnings("unchecked")
    @Test
    public void createAccountWithExistingAcronymMultipleBanks() throws Exception {
        MemoryRepository repo = new MemoryRepository();
        Field f = repo.getClass().getDeclaredField("memory");
        f.setAccessible(true);
        Object field = f.get(repo);
        Map<String, BankMemoryEntity> memory = (HashMap<String, BankMemoryEntity>) field;
        BankMemoryEntity entity1 = new BankMemoryEntity("ID1", "name", "bank1");
        entity1.addAccount(new AccountMemoryEntity("name", "123", "AC"));
        memory.put("ID1", entity1);

        BankMemoryEntity entity2 = new BankMemoryEntity("ID2", "name", "bank2");
        memory.put("ID2", entity2);


        Exception exception = assertThrows(AcronymAlreadyExistsException.class, () -> repo.createAccountByBankId("ID2", new Account("","","AC")));
        String expected = "Die Abkürzung AC existiert bereits im System";

        assertThat(expected, is(exception.getMessage()));
    }

    @SuppressWarnings("unchecked")
    @Test
    public void createAccountByAcronymOfBank() throws Exception{
        MemoryRepository repo = new MemoryRepository();
        Field f = repo.getClass().getDeclaredField("memory");
        f.setAccessible(true);
        Object field = f.get(repo);
        Map<String, BankMemoryEntity> memory = (HashMap<String, BankMemoryEntity>) field;
        BankMemoryEntity entity = new BankMemoryEntity("ID1", "name", "bank1");
        memory.put("ID1", entity);


        repo.createAccountByBankAcronym("bank1", new Account("name", "nr", "ac"));

        AccountMemoryEntity account = new ArrayList<>(memory.get("ID1").getAccounts()).get(0);
        assertThat(account.getName(), is("name"));
        assertThat(account.getNumber(), is("nr"));
        assertThat(account.getAcronym(), is("ac"));
    }


    @Test
    public void createAccountByAcronymOfNonExistingBank(){
        MemoryRepository repo = new MemoryRepository();

        Exception exception = assertThrows(BankNotFoundExecption.class, () -> repo.createAccountByBankAcronym("acc", new Account("","","")));
        String expected = "Bank mit der ID oder der Abkürzung acc wurde nicht gefunden";

        assertThat(expected, is(exception.getMessage()));
    }

    @SuppressWarnings("unchecked")
    @Test
    public void getAllAccountsOfOneBankById() throws Exception {
        MemoryRepository repo = new MemoryRepository();
        Field f = repo.getClass().getDeclaredField("memory");
        f.setAccessible(true);
        Object field = f.get(repo);
        Map<String, BankMemoryEntity> memory = (HashMap<String, BankMemoryEntity>) field;
        BankMemoryEntity entity1 = new BankMemoryEntity("ID1", "name", "bank1");
        entity1.addAccount(new AccountMemoryEntity("name", "123", "AC"));
        memory.put("ID1", entity1);

        List<Account> accounts = repo.getAccountsOfBankByBankId("ID1");

        assertThat(accounts.size(), is(1));
    }

    @Test
    public void getAllAccountsOfOneBankByIdBankNotExists() {
        MemoryRepository repo = new MemoryRepository();

        Exception ex = assertThrows(BankNotFoundExecption.class, () -> repo.getAccountsOfBankByBankId("ID1"));

        String expected = "Bank mit der ID oder der Abkürzung ID1 wurde nicht gefunden";

        assertThat(ex.getMessage(), is(expected));
    }

    @SuppressWarnings("unchecked")
    @Test
    public void getAllAccountsOfOneBankByAcronym() throws Exception {
        MemoryRepository repo = new MemoryRepository();
        Field f = repo.getClass().getDeclaredField("memory");
        f.setAccessible(true);
        Object field = f.get(repo);
        Map<String, BankMemoryEntity> memory = (HashMap<String, BankMemoryEntity>) field;
        BankMemoryEntity entity1 = new BankMemoryEntity("ID1", "name", "bank1");
        entity1.addAccount(new AccountMemoryEntity("name", "123", "AC"));
        memory.put("ID1", entity1);

        List<Account> accounts = repo.getAccountsOfBankByBankAcronym("bank1");

        assertThat(accounts.size(), is(1));
    }

    @Test
    public void getAllAccountsOfOneBankByAcronymBankNotExists(){
        MemoryRepository repo = new MemoryRepository();

        Exception ex = assertThrows(BankNotFoundExecption.class, () -> repo.getAccountsOfBankByBankAcronym("AC"));

        String expected = "Bank mit der ID oder der Abkürzung AC wurde nicht gefunden";

        assertThat(ex.getMessage(), is(expected));
    }

    @SuppressWarnings("unchecked")
    @Test
    public void deleteAccountByAccountId() throws Exception {
        MemoryRepository repo = new MemoryRepository();
        Field f = repo.getClass().getDeclaredField("memory");
        f.setAccessible(true);
        Object field = f.get(repo);
        Map<String, BankMemoryEntity> memory = (HashMap<String, BankMemoryEntity>) field;
        BankMemoryEntity entity1 = new BankMemoryEntity("ID1", "name", "bank1");
        entity1.addAccount(new AccountMemoryEntity("ACC-ID", "name", "123", "AC"));
        memory.put("ID1", entity1);

        repo.deleteAccountById("ACC-ID");

        assertThat(memory.get("ID1").getAccounts().size(), is(0));
    }


    @Test
    public void deleteAccountByNonExistingAccountId() {
        MemoryRepository repo = new MemoryRepository();

        Exception ex = assertThrows(AccountNotFoundException.class, () -> repo.deleteAccountById("ID1"));

        String expected = "Account mit der ID oder der Abkürzung ID1 wurde nicht gefunden";

        assertThat(ex.getMessage(), is(expected));
    }

    @SuppressWarnings("unchecked")
    @Test
    public void deleteAccountByAccountAcronym() throws Exception {
        MemoryRepository repo = new MemoryRepository();
        Field f = repo.getClass().getDeclaredField("memory");
        f.setAccessible(true);
        Object field = f.get(repo);
        Map<String, BankMemoryEntity> memory = (HashMap<String, BankMemoryEntity>) field;
        BankMemoryEntity entity1 = new BankMemoryEntity("ID1", "name", "bank1");
        entity1.addAccount(new AccountMemoryEntity("ACC-ID", "name", "123", "AC"));
        memory.put("ID1", entity1);

        repo.deleteAccountByAcronym("AC");

        assertThat(memory.get("ID1").getAccounts().size(), is(0));
    }


    @Test
    public void deleteAccountByNonExistingAccountAcronym() {
        MemoryRepository repo = new MemoryRepository();

        Exception ex = assertThrows(AccountNotFoundException.class, () -> repo.deleteAccountByAcronym("ID1"));

        String expected = "Account mit der ID oder der Abkürzung ID1 wurde nicht gefunden";

        assertThat(ex.getMessage(), is(expected));
    }
}


