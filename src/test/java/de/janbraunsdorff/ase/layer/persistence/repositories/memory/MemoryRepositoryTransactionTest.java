package de.janbraunsdorff.ase.layer.persistence.repositories.memory;


import de.janbraunsdorff.ase.layer.persistence.repositories.AccountNotFoundException;
import de.janbraunsdorff.ase.layer.persistence.repositories.TransactionNotFoundException;
import de.janbraunsdorff.ase.layer.persistence.repositories.entität.AccountEntity;
import de.janbraunsdorff.ase.layer.persistence.repositories.entität.BankEntity;
import de.janbraunsdorff.ase.layer.persistence.repositories.entität.TransactionEntity;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MemoryRepositoryTransactionTest {

    @SuppressWarnings("unchecked")
    @Test
    public void createTransaction() throws Exception {
        MemoryRepository repo = new MemoryRepository();
        Field f = repo.getClass().getDeclaredField("memory");
        f.setAccessible(true);
        Object field = f.get(repo);
        Map<String, BankEntity> memory = (HashMap<String, BankEntity>) field;
        BankEntity entity = new BankEntity("ID", "name", "acronym");
        entity.addAccount(new AccountEntity("ACC-ID", "name", "nr", "ac"));
        memory.put("ID", entity);

        TransactionEntity transaction = new TransactionEntity("ID-Trans", 12, "third", "cat", false);
        repo.createTransactionByAccountAcronym("ac", transaction);


        TransactionEntity trans = new ArrayList<>(new ArrayList<>(memory.get("ID").getAccounts()).get(0).getTransactionEntities()).get(0);
        assertThat(trans.getId(), is("ID-Trans"));
        assertThat(trans.getValue(), is(12));
        assertThat(trans.getThirdParty(), is("third"));
        assertThat(trans.getCategory(), is("cat"));
        assertThat(trans.getContract(), is(false));
    }

    @Test
    public void createTransactionAccountNotExists() {
        MemoryRepository repo = new MemoryRepository();

        Exception ex = assertThrows(AccountNotFoundException.class, () -> repo.createTransactionByAccountAcronym("ID1", new TransactionEntity(1, "", "", false)));

        String expected = "Account mit der ID oder der Abkürzung ID1 wurde nicht gefunden";

        assertThat(ex.getMessage(), is(expected));
    }


    @SuppressWarnings("unchecked")
    @Test
    public void createTransactionById() throws Exception {
        MemoryRepository repo = new MemoryRepository();
        Field f = repo.getClass().getDeclaredField("memory");
        f.setAccessible(true);
        Object field = f.get(repo);
        Map<String, BankEntity> memory = (HashMap<String, BankEntity>) field;
        BankEntity entity = new BankEntity("ID", "name", "acronym");
        entity.addAccount(new AccountEntity("ACC-ID", "name", "nr", "ac"));
        memory.put("ID", entity);

        TransactionEntity transaction = new TransactionEntity("ID-Trans", 12, "third", "cat", false);
        repo.createTransactionByAccountId("ACC-ID", transaction);


        TransactionEntity trans = new ArrayList<>(new ArrayList<>(memory.get("ID").getAccounts()).get(0).getTransactionEntities()).get(0);
        assertThat(trans.getId(), is("ID-Trans"));
        assertThat(trans.getValue(), is(12));
        assertThat(trans.getThirdParty(), is("third"));
        assertThat(trans.getCategory(), is("cat"));
        assertThat(trans.getContract(), is(false));
    }

    @Test
    public void createTransactionAccountNotExistsId() {
        MemoryRepository repo = new MemoryRepository();

        Exception ex = assertThrows(AccountNotFoundException.class, () -> repo.createTransactionByAccountId("ACC-ID", new TransactionEntity(1, "", "", false)));

        String expected = "Account mit der ID oder der Abkürzung ACC-ID wurde nicht gefunden";

        assertThat(ex.getMessage(), is(expected));
    }

    @SuppressWarnings("unchecked")
    @Test
    public void getTransactionById() throws Exception {
        MemoryRepository repo = new MemoryRepository();
        Field f = repo.getClass().getDeclaredField("memory");
        f.setAccessible(true);
        Object field = f.get(repo);
        Map<String, BankEntity> memory = (HashMap<String, BankEntity>) field;
        BankEntity entity = new BankEntity("ID", "name", "acronym");
        AccountEntity account = new AccountEntity("ACC-ID", "name", "nr", "ac");
        account.addTransaction(new TransactionEntity("Trans-ID", 1, "", "", false));
        entity.addAccount(account);
        memory.put("ID", entity);

        List<TransactionEntity> transactions = repo.getTransactionByAccountId("ACC-ID");

        assertThat(transactions.size(), is(1));
        assertThat(transactions.get(0).getId(), is("Trans-ID"));


    }

    @Test
    public void getTransactionAccountNotExistsId() {
        MemoryRepository repo = new MemoryRepository();

        Exception ex = assertThrows(AccountNotFoundException.class, () -> repo.getTransactionByAccountId("ACC-ID"));

        String expected = "Account mit der ID oder der Abkürzung ACC-ID wurde nicht gefunden";

        assertThat(ex.getMessage(), is(expected));
    }

    @SuppressWarnings("unchecked")
    @Test
    public void getTransactionByAcronym() throws Exception {
        MemoryRepository repo = new MemoryRepository();
        Field f = repo.getClass().getDeclaredField("memory");
        f.setAccessible(true);
        Object field = f.get(repo);
        Map<String, BankEntity> memory = (HashMap<String, BankEntity>) field;
        BankEntity entity = new BankEntity("ID", "name", "acronym");
        AccountEntity account = new AccountEntity("ACC-ID", "name", "nr", "ac");
        account.addTransaction(new TransactionEntity("Trans-ID", 1, "", "", false));
        entity.addAccount(account);
        memory.put("ID", entity);

        List<TransactionEntity> transactions = repo.getTransactionByAccountAcronym("ac");

        assertThat(transactions.size(), is(1));
        assertThat(transactions.get(0).getId(), is("Trans-ID"));


    }

    @Test
    public void getTransactionAccountNotExistsAcronym() {
        MemoryRepository repo = new MemoryRepository();

        Exception ex = assertThrows(AccountNotFoundException.class, () -> repo.getTransactionByAccountAcronym("ACC-ac"));

        String expected = "Account mit der ID oder der Abkürzung ACC-ac wurde nicht gefunden";

        assertThat(ex.getMessage(), is(expected));
    }


    @SuppressWarnings("unchecked")
    @Test
    public void deleteTransactionById() throws Exception {
        MemoryRepository repo = new MemoryRepository();
        Field f = repo.getClass().getDeclaredField("memory");
        f.setAccessible(true);
        Object field = f.get(repo);
        Map<String, BankEntity> memory = (HashMap<String, BankEntity>) field;
        BankEntity entity = new BankEntity("ID", "name", "acronym");
        AccountEntity account = new AccountEntity("ACC-ID", "name", "nr", "ac");
        TransactionEntity transact = new TransactionEntity("Trans-ID", 1, "", "", false);
        account.addTransaction(transact);
        entity.addAccount(account);
        memory.put("ID", entity);

        repo.deleteTransactionById(transact.getId());

        assertThat(account.getTransactionEntities().size(), is(0));
    }

    @Test
    public void deleteTransactionAccountNotExistsId() {
        MemoryRepository repo = new MemoryRepository();

        Exception ex = assertThrows(TransactionNotFoundException.class, () -> repo.deleteTransactionById("ACC-ac"));

        String expected = "Transaktion mit der ID ACC-ac wurde nicht gefunden";

        assertThat(ex.getMessage(), is(expected));
    }



}


