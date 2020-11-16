package de.janbraunsdorff.ase.layer.persistence.repositories.memory;


import de.janbraunsdorff.ase.layer.persistence.repositories.AccountNotFoundException;
import de.janbraunsdorff.ase.layer.persistence.repositories.entität.AccountEntity;
import de.janbraunsdorff.ase.layer.persistence.repositories.entität.BankEntity;
import de.janbraunsdorff.ase.layer.persistence.repositories.entität.TransactionEntity;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

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
}


