package de.janbraunsdorff.ase.layer.persistence.repositories.memory;

import de.janbraunsdorff.ase.layer.persistence.repositories.entität.AccountEntity;
import de.janbraunsdorff.ase.layer.persistence.repositories.entität.BankEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class MemoryRepositoryTest {
    @Test
    public void Test_CreateBankAndGet() throws Exception {
        BankEntity bankEntity = new BankEntity( "Name",  "n");
        MemoryRepository repo = new MemoryRepository();
        BankEntity got = repo.create(bankEntity);
        BankEntity get = repo.get(bankEntity.getId());

        assertThat(got.getName(), is(get.getName()));
        assertThat(got.getId(), is(get.getId()));
    }

    @Test
    public void Test_CreateBankWithExistingId() throws Exception {
        MemoryRepository repo = new MemoryRepository();

        BankEntity bankEntity = new BankEntity("Name",  "n");
        repo.create(bankEntity);

        BankEntity sameId = new BankEntity("new Name",  "n");
        Assertions.assertThrows(IllegalArgumentException.class, () -> repo.create(sameId));
    }

    @Test
    public void Test_CreateBankIdIsMissing() throws Exception {
        MemoryRepository repo = new MemoryRepository();

        BankEntity bankEntity = new BankEntity("Name",  "n");
        BankEntity got = repo.create(bankEntity);
        BankEntity get = repo.get(got.getId());

        assertThat(got.getName(), is(get.getName()));
        assertThat(got.getId(), is(get.getId()));
        assertThat(got.getId(), notNullValue());
    }

    @Test
    public void Test_GetAllBanks() throws Exception {
        MemoryRepository repo = new MemoryRepository();

        BankEntity bankEntity1 = new BankEntity( "Name",  "n1");
        BankEntity bankEntity2 = new BankEntity("Name",  "n2");
        repo.create(bankEntity1);
        repo.create(bankEntity2);

        List<BankEntity> bankEntities = repo.get();
        assertThat(bankEntities.size(), is(2));

    }

    @Test
    public void Test_UpdateExistingBank() throws Exception {
        MemoryRepository repo = new MemoryRepository();

        BankEntity bankEntity = new BankEntity( "Name",  "n");
        repo.create(bankEntity);

        BankEntity updated = new BankEntity(bankEntity.getId(), "Name updated",  "n");
        repo.update(updated);

        BankEntity got = repo.get(updated.getId());
        assertThat(got.getName(), is("Name updated"));

    }

    @Test
    public void Test_UpdateNonExistingBank() throws Exception {
        MemoryRepository repo = new MemoryRepository();
        BankEntity updated = new BankEntity( "Name updated", "n");
        Assertions.assertThrows(IllegalArgumentException.class, () -> repo.update(updated));
    }

    @Test
    public void Test_DeleteExistingBank() throws Exception {
        MemoryRepository repo = new MemoryRepository();

        BankEntity bankEntity = new BankEntity("Name","n");
        repo.create(bankEntity);
        repo.delete(bankEntity.getId());

        BankEntity got = repo.get("ID1");
        assertThat(got, nullValue());
    }

    @Test
    public void Test_DeleteNonExistingBank() throws Exception {
        MemoryRepository repo = new MemoryRepository();
        repo.delete("ID");
        BankEntity got = repo.get("ID");
        assertThat(got, nullValue());
    }

    @Test
    public void Test_CreateBankWithExistingAcronym() throws Exception {
        MemoryRepository repo = new MemoryRepository();

        BankEntity bankEntity = new BankEntity( "Name",  "n");
        repo.create(bankEntity);

        BankEntity sameId = new BankEntity( "new Name",  "n");
        Assertions.assertThrows(IllegalArgumentException.class, () -> repo.create(sameId));
    }

    @Test
    public void Test_CreateUpdateBankAcronym() throws Exception {
        MemoryRepository repo = new MemoryRepository();

        BankEntity bankEntity = new BankEntity("Name",  "new");
        repo.create(bankEntity);

        BankEntity sameId = new BankEntity(bankEntity.getId(), "new Name", "new");
        BankEntity got = repo.update(sameId);
        assertThat(got.getId(), is(bankEntity.getId()));
        assertThat(got.getAcronym(), is("new"));
    }

    @Test
    public void Test_CreateUpdateBankAcronymToExisting() throws Exception {
        MemoryRepository repo = new MemoryRepository();

        BankEntity bankEntity1 = new BankEntity("Name", "n");
        BankEntity bankEntity2 = new BankEntity( "Name", "arc");
        repo.create(bankEntity1);
        repo.create(bankEntity2);

        BankEntity sameId = new BankEntity("new Name", "arc");
        Assertions.assertThrows(IllegalArgumentException.class, () -> repo.update(sameId));
    }

    @Test
    public void Test_CreateAccountWithCompleteDataAndExistingBankByBankId() throws Exception {
        MemoryRepository repo = new MemoryRepository();
        BankEntity bankEntity = new BankEntity("Name",  "arc");
        repo.create(bankEntity);

        AccountEntity account = new AccountEntity("AccountName", "AccountNumber", "AN");
        AccountEntity accountEntity = repo.create(bankEntity.getId(), account);

        assertThat(accountEntity.getName(), is("AccountName"));
        assertThat(accountEntity.getNumber(), is("AccountNumber"));

    }

    @Test
    public void Test_CreateAccountWithMissingIDAndExistingBankByBankId() throws Exception {
        MemoryRepository repo = new MemoryRepository();
        BankEntity bankEntity = new BankEntity( "Name", "arc");
        repo.create(bankEntity);

        AccountEntity account = new AccountEntity(null, "AccountName", "AccountNumber");
        AccountEntity accountEntity = repo.create(bankEntity.getId(), account);

        assertThat(accountEntity.getId(), notNullValue());

    }
}

