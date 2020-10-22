package de.janbraunsdorff.ase.tech.repositories.memory;

import de.janbraunsdorff.ase.tech.repositories.entität.AccountEntity;
import de.janbraunsdorff.ase.tech.repositories.entität.BankEntity;
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
        BankEntity bankEntity = new BankEntity("ID", "Name", Collections.emptyList(), "n");
        MemoryRepository repo = new MemoryRepository();
        BankEntity got = repo.create(bankEntity);
        BankEntity get = repo.get("ID");

        assertThat(got.getName(), is(get.getName()));
        assertThat(got.getId(), is(get.getId()));
    }

    @Test
    public void Test_CreateBankWithExistingId() throws Exception {
        MemoryRepository repo = new MemoryRepository();

        BankEntity bankEntity = new BankEntity("ID", "Name", Collections.emptyList(), "n");
        repo.create(bankEntity);

        BankEntity sameId = new BankEntity("ID", "new Name", Collections.emptyList(), "n");
        Assertions.assertThrows(IllegalArgumentException.class, () -> repo.create(sameId));
    }

    @Test
    public void Test_CreateBankIdIsMissing() throws Exception {
        MemoryRepository repo = new MemoryRepository();

        BankEntity bankEntity = new BankEntity(null, "Name", Collections.emptyList(), "n");
        BankEntity got = repo.create(bankEntity);
        BankEntity get = repo.get(got.getId());

        assertThat(got.getName(), is(get.getName()));
        assertThat(got.getId(), is(get.getId()));
        assertThat(got.getId(), notNullValue());
    }

    @Test
    public void Test_GetAllBanks() throws Exception {
        MemoryRepository repo = new MemoryRepository();

        BankEntity bankEntity1 = new BankEntity("ID1", "Name", Collections.emptyList(), "n1");
        BankEntity bankEntity2 = new BankEntity("ID2", "Name", Collections.emptyList(), "n2");
        repo.create(bankEntity1);
        repo.create(bankEntity2);

        List<BankEntity> bankEntities = repo.get();
        assertThat(bankEntities.size(), is(2));

    }

    @Test
    public void Test_UpdateExistingBank() throws Exception {
        MemoryRepository repo = new MemoryRepository();

        BankEntity bankEntity = new BankEntity("ID1", "Name", Collections.emptyList(), "n");
        repo.create(bankEntity);

        BankEntity updated = new BankEntity("ID1", "Name updated", Collections.emptyList(), "n");
        repo.update(updated);

        BankEntity got = repo.get("ID1");
        assertThat(got.getId(), is("ID1"));
        assertThat(got.getName(), is("Name updated"));

    }

    @Test
    public void Test_UpdateNonExistingBank() throws Exception {
        MemoryRepository repo = new MemoryRepository();
        BankEntity updated = new BankEntity("ID1", "Name updated", Collections.emptyList(), "n");
        Assertions.assertThrows(IllegalArgumentException.class, () -> repo.update(updated));
    }

    @Test
    public void Test_DeleteExistingBank() throws Exception {
        MemoryRepository repo = new MemoryRepository();

        BankEntity bankEntity = new BankEntity("ID1", "Name", Collections.emptyList(), "n");
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

        BankEntity bankEntity = new BankEntity("ID", "Name", Collections.emptyList(), "n");
        repo.create(bankEntity);

        BankEntity sameId = new BankEntity("ID2", "new Name", Collections.emptyList(), "n");
        Assertions.assertThrows(IllegalArgumentException.class, () -> repo.create(sameId));
    }

    @Test
    public void Test_CreateUpdateBankAcronym() throws Exception {
        MemoryRepository repo = new MemoryRepository();

        BankEntity bankEntity = new BankEntity("ID", "Name", Collections.emptyList(), "new");
        repo.create(bankEntity);

        BankEntity sameId = new BankEntity("ID", "new Name", Collections.emptyList(), "new");
        BankEntity got = repo.update(sameId);
        assertThat(got.getId(), is("ID"));
        assertThat(got.getAcronym(), is("new"));
    }

    @Test
    public void Test_CreateUpdateBankAcronymToExisting() throws Exception {
        MemoryRepository repo = new MemoryRepository();

        BankEntity bankEntity1 = new BankEntity("ID", "Name", Collections.emptyList(), "n");
        BankEntity bankEntity2 = new BankEntity("ID2", "Name", Collections.emptyList(), "arc");
        repo.create(bankEntity1);
        repo.create(bankEntity2);

        BankEntity sameId = new BankEntity("ID", "new Name", Collections.emptyList(), "arc");
        Assertions.assertThrows(IllegalArgumentException.class, () -> repo.update(sameId));
    }

    @Test
    public void Test_CreateAccountWithCompleteDataAndExistingBankByBankId() throws Exception {
        MemoryRepository repo = new MemoryRepository();
        BankEntity bankEntity = new BankEntity("ID2", "Name", new ArrayList<>(), "arc");
        repo.create(bankEntity);

        AccountEntity account = new AccountEntity("accountId", "AccountName", "AccountNumber", 0, new ArrayList<>());
        AccountEntity accountEntity = repo.create(bankEntity.getId(), account);

        assertThat(accountEntity.getId(), is("accountId"));
        assertThat(accountEntity.getName(), is("AccountName"));
        assertThat(accountEntity.getNumber(), is("AccountNumber"));
        assertThat(accountEntity.getOrder(), is(0));

        assertThat(repo.get(bankEntity.getId()).getAccounts().get(0).getId(), is("accountId"));

    }

    @Test
    public void Test_CreateAccountWithMissingIDAndExistingBankByBankId() throws Exception {
        MemoryRepository repo = new MemoryRepository();
        BankEntity bankEntity = new BankEntity("ID2", "Name", new ArrayList<>(), "arc");
        repo.create(bankEntity);

        AccountEntity account = new AccountEntity(null, "AccountName", "AccountNumber", 0, new ArrayList<>());
        AccountEntity accountEntity = repo.create(bankEntity.getId(), account);

        assertThat(accountEntity.getId(), notNullValue());

    }
}

