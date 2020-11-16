package de.janbraunsdorff.ase.layer.persistence.repositories.memory;


import de.janbraunsdorff.ase.layer.persistence.repositories.AccountNotFoundException;
import de.janbraunsdorff.ase.layer.persistence.repositories.AcronymAlreadyExistsException;
import de.janbraunsdorff.ase.layer.persistence.repositories.BankNotFoundExecption;
import de.janbraunsdorff.ase.layer.persistence.repositories.entität.AccountEntity;
import de.janbraunsdorff.ase.layer.persistence.repositories.entität.BankEntity;
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
        Map<String, BankEntity> memory = (HashMap<String, BankEntity>) field;
        BankEntity entity = new BankEntity("ID", "name", "acronym");
        memory.put("ID", entity);

        repo.createAccountByBankId("ID", new AccountEntity("account", "123", "AC"));

        AccountEntity account = new ArrayList<>(memory.get("ID").getAccounts()).get(0);

        assertThat(account.getName(), is("account"));
        assertThat(account.getNumber(), is("123"));
        assertThat(account.getAcronym(), is("AC"));
    }

    @Test
    public void createAccountBankNotExists(){
        MemoryRepository repo = new MemoryRepository();

        Exception exception = assertThrows(BankNotFoundExecption.class, () -> repo.createAccountByBankId("ACC-ID", new AccountEntity("","","")));
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
        Map<String, BankEntity> memory = (HashMap<String, BankEntity>) field;
        BankEntity entity = new BankEntity("ID", "name", "acronym");
        entity.addAccount(new AccountEntity("name", "123", "AC"));
        memory.put("ID", entity);


        Exception exception = assertThrows(AcronymAlreadyExistsException.class, () -> repo.createAccountByBankId("ID", new AccountEntity("","","AC")));
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
        Map<String, BankEntity> memory = (HashMap<String, BankEntity>) field;
        BankEntity entity1 = new BankEntity("ID1", "name", "bank1");
        entity1.addAccount(new AccountEntity("name", "123", "AC"));
        memory.put("ID1", entity1);

        BankEntity entity2 = new BankEntity("ID2", "name", "bank2");
        memory.put("ID2", entity2);


        Exception exception = assertThrows(AcronymAlreadyExistsException.class, () -> repo.createAccountByBankId("ID2", new AccountEntity("","","AC")));
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
        Map<String, BankEntity> memory = (HashMap<String, BankEntity>) field;
        BankEntity entity = new BankEntity("ID1", "name", "bank1");
        memory.put("ID1", entity);


        repo.createAccountByBankAcronym("bank1", new AccountEntity("name", "nr", "ac"));

        AccountEntity account = new ArrayList<>(memory.get("ID1").getAccounts()).get(0);
        assertThat(account.getName(), is("name"));
        assertThat(account.getNumber(), is("nr"));
        assertThat(account.getAcronym(), is("ac"));
    }


    @Test
    public void createAccountByAcronymOfNonExistingBank(){
        MemoryRepository repo = new MemoryRepository();

        Exception exception = assertThrows(BankNotFoundExecption.class, () -> repo.createAccountByBankAcronym("acc", new AccountEntity("","","")));
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
        Map<String, BankEntity> memory = (HashMap<String, BankEntity>) field;
        BankEntity entity1 = new BankEntity("ID1", "name", "bank1");
        entity1.addAccount(new AccountEntity("name", "123", "AC"));
        memory.put("ID1", entity1);

        List<AccountEntity> accounts = repo.getAccountsOfBankByBankId("ID1");

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
        Map<String, BankEntity> memory = (HashMap<String, BankEntity>) field;
        BankEntity entity1 = new BankEntity("ID1", "name", "bank1");
        entity1.addAccount(new AccountEntity("name", "123", "AC"));
        memory.put("ID1", entity1);

        List<AccountEntity> accounts = repo.getAccountsOfBankByBankAcronym("bank1");

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
        Map<String, BankEntity> memory = (HashMap<String, BankEntity>) field;
        BankEntity entity1 = new BankEntity("ID1", "name", "bank1");
        entity1.addAccount(new AccountEntity("ACC-ID", "name", "123", "AC"));
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
        Map<String, BankEntity> memory = (HashMap<String, BankEntity>) field;
        BankEntity entity1 = new BankEntity("ID1", "name", "bank1");
        entity1.addAccount(new AccountEntity("ACC-ID", "name", "123", "AC"));
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

