package de.janbraunsdorff.ase.layer.persistence.repositories.memory;


import de.janbraunsdorff.ase.layer.domain.crud.entitties.Transaction;
import de.janbraunsdorff.ase.layer.domain.crud.repository.exceptions.AccountNotFoundException;
import de.janbraunsdorff.ase.layer.domain.crud.repository.exceptions.TransactionNotFoundException;
import de.janbraunsdorff.ase.layer.persistence.repositories.memory.entität.AccountMemoryEntity;
import de.janbraunsdorff.ase.layer.persistence.repositories.memory.entität.BankMemoryEntity;
import de.janbraunsdorff.ase.layer.persistence.repositories.memory.entität.TransactionMemoryEntity;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MemoryRepositoryTransactionTest {

    @Test
    public void createTransaction() throws Exception {
        MemoryRepository base = new MemoryRepository();
        TransactionMemoryRepository repo = new TransactionMemoryRepository(base);
        Map<String, BankMemoryEntity> memory = getMemory(base);
        BankMemoryEntity entity = new BankMemoryEntity("ID", "name", "acronym");
        entity.addAccount(new AccountMemoryEntity("ACC-ID", "name", "nr", "ac"));
        memory.put("ID", entity);

        Transaction transaction = new Transaction( 12, "third", "cat", false, 1);
        repo.createTransactionByAccountAcronym("ac", transaction);


        TransactionMemoryEntity trans = new ArrayList<>(new ArrayList<>(memory.get("ID").getAccounts()).get(0).getTransactionEntities()).get(0);
        assertThat(trans.getValue(), is(12));
        assertThat(trans.getThirdParty(), is("third"));
        assertThat(trans.getCategory(), is("cat"));
        assertThat(trans.getContract(), is(false));
        assertThat(trans.getIndex(), is(0));

    }

    @Test
    public void createTransactionAccountNotExists() {
        MemoryRepository base = new MemoryRepository();
        TransactionMemoryRepository repo = new TransactionMemoryRepository(base);

        Exception ex = assertThrows(AccountNotFoundException.class, () -> repo.createTransactionByAccountAcronym("ID1", new Transaction(1, LocalDate.now(), "", "", false, 1)));

        String expected = "Account mit der ID oder der Abkürzung ID1 wurde nicht gefunden";

        assertThat(ex.getMessage(), is(expected));
    }


    @Test
    public void createTransactionById() throws Exception {
        MemoryRepository base = new MemoryRepository();
        TransactionMemoryRepository repo = new TransactionMemoryRepository(base);
        Map<String, BankMemoryEntity> memory = getMemory(base);
        BankMemoryEntity entity = new BankMemoryEntity("ID", "name", "acronym");
        entity.addAccount(new AccountMemoryEntity("ACC-ID", "name", "nr", "ac"));
        memory.put("ID", entity);

        Transaction transaction = new Transaction( 12, "third", "cat", false, 1);
        repo.createTransactionByAccountId("ACC-ID", transaction);


        TransactionMemoryEntity trans = new ArrayList<>(new ArrayList<>(memory.get("ID").getAccounts()).get(0).getTransactionEntities()).get(0);
        assertThat(trans.getValue(), is(12));
        assertThat(trans.getThirdParty(), is("third"));
        assertThat(trans.getCategory(), is("cat"));
        assertThat(trans.getContract(), is(false));
        assertThat(trans.getIndex(), is(0));
    }

    @Test
    public void createTransactionAccountNotExistsId() {
        MemoryRepository base = new MemoryRepository();
        TransactionMemoryRepository repo = new TransactionMemoryRepository(base);

        Exception ex = assertThrows(AccountNotFoundException.class, () -> repo.createTransactionByAccountId("ACC-ID", new Transaction(1, LocalDate.now(), "", "", false, 1)));

        String expected = "Account mit der ID oder der Abkürzung ACC-ID wurde nicht gefunden";

        assertThat(ex.getMessage(), is(expected));
    }

    @Test
    public void getTransactionById() throws Exception {
        MemoryRepository base = new MemoryRepository();
        TransactionMemoryRepository repo = new TransactionMemoryRepository(base);
        Map<String, BankMemoryEntity> memory = getMemory(base);
        BankMemoryEntity entity = new BankMemoryEntity("ID", "name", "acronym");
        AccountMemoryEntity account = new AccountMemoryEntity("ACC-ID", "name", "nr", "ac");
        account.addTransaction(new TransactionMemoryEntity("Trans-ID", 1, "", "", false, 1));
        entity.addAccount(account);
        memory.put("ID", entity);

        List<Transaction> transactions = repo.getTransactionByAccountId("ACC-ID");

        assertThat(transactions.size(), is(1));
        assertThat(transactions.get(0).getIndex(), is(1));


    }

    @Test
    public void getTransactionAccountNotExistsId() {
        MemoryRepository base = new MemoryRepository();
        TransactionMemoryRepository repo = new TransactionMemoryRepository(base);

        Exception ex = assertThrows(AccountNotFoundException.class, () -> repo.getTransactionByAccountId("ACC-ID"));

        String expected = "Account mit der ID oder der Abkürzung ACC-ID wurde nicht gefunden";

        assertThat(ex.getMessage(), is(expected));
    }

    @Test
    public void getTransactionByAcronym() throws Exception {
        MemoryRepository base = new MemoryRepository();
        TransactionMemoryRepository repo = new TransactionMemoryRepository(base);
        Map<String, BankMemoryEntity> memory = getMemory(base);
        BankMemoryEntity entity = new BankMemoryEntity("ID", "name", "acronym");
        AccountMemoryEntity account = new AccountMemoryEntity("ACC-ID", "name", "nr", "ac");
        account.addTransaction(new TransactionMemoryEntity("Trans-ID", 1, "", "", false, 1));
        entity.addAccount(account);
        memory.put("ID", entity);

        List<Transaction> transactions = repo.getTransactionByAccountAcronym("ac");

        assertThat(transactions.size(), is(1));
        assertThat(transactions.get(0).getIndex(), is(1));


    }

    @Test
    public void getTransactionAccountNotExistsAcronym() {
        MemoryRepository base = new MemoryRepository();
        TransactionMemoryRepository repo = new TransactionMemoryRepository(base);
        Exception ex = assertThrows(AccountNotFoundException.class, () -> repo.getTransactionByAccountAcronym("ACC-ac"));

        String expected = "Account mit der ID oder der Abkürzung ACC-ac wurde nicht gefunden";

        assertThat(ex.getMessage(), is(expected));
    }


    @Test
    public void deleteTransactionById() throws Exception {
        MemoryRepository base = new MemoryRepository();
        TransactionMemoryRepository repo = new TransactionMemoryRepository(base);
        Map<String, BankMemoryEntity> memory = getMemory(base);
        BankMemoryEntity entity = new BankMemoryEntity("ID", "name", "acronym");
        AccountMemoryEntity account = new AccountMemoryEntity("ACC-ID", "name", "nr", "ac");
        TransactionMemoryEntity transact = new TransactionMemoryEntity("Trans-ID", 1, "", "", false, 1);
        account.addTransaction(transact);
        entity.addAccount(account);
        memory.put("ID", entity);

        repo.deleteTransactionById(transact.getIndex());

        assertThat(account.getTransactionEntities().size(), is(0));
    }

    @Test
    public void deleteTransactionAccountNotExistsId() {
        MemoryRepository base = new MemoryRepository();
        TransactionMemoryRepository repo = new TransactionMemoryRepository(base);

        Exception ex = assertThrows(TransactionNotFoundException.class, () -> repo.deleteTransactionById(0));

        String expected = "Transaktion mit der ID 0 wurde nicht gefunden";

        assertThat(ex.getMessage(), is(expected));
    }

    @SuppressWarnings("unchecked")
    private Map<String, BankMemoryEntity> getMemory(MemoryRepository repo) throws NoSuchFieldException, IllegalAccessException {
        Field f = repo.getClass().getDeclaredField("memory");
        f.setAccessible(true);
        Object field = f.get(repo);
        return (Map<String, BankMemoryEntity>)  field;
    }

}


