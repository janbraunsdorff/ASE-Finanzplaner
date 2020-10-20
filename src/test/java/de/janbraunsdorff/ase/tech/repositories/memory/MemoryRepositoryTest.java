package de.janbraunsdorff.ase.tech.repositories.memory;

import de.janbraunsdorff.ase.tech.repositories.BankEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class MemoryRepositoryTest {
    @Test
    public void Test_CreateBankAndGet() throws Exception {
        BankEntity bankEntity = new BankEntity("ID", "Name", Collections.emptyList());
        MemoryRepository repo = new MemoryRepository();
        BankEntity got = repo.create(bankEntity);
        BankEntity get = repo.get("ID");

        assertThat(got.getName(), is(get.getName()));
        assertThat(got.getId(), is(get.getId()));
    }

    @Test
    public void Test_CreateBankWithExistingId() throws Exception {
        MemoryRepository repo = new MemoryRepository();

        BankEntity bankEntity = new BankEntity("ID", "Name", Collections.emptyList());
        repo.create(bankEntity);

        BankEntity sameId = new BankEntity("ID", "new Name", Collections.emptyList());
        Assertions.assertThrows(IllegalArgumentException.class, () -> repo.create(sameId));
    }

    @Test
    public void Test_CreateBankIdIsMissing() throws Exception {
        MemoryRepository repo = new MemoryRepository();

        BankEntity bankEntity = new BankEntity(null, "Name", Collections.emptyList());
        BankEntity got = repo.create(bankEntity);
        BankEntity get = repo.get(got.getId());

        assertThat(got.getName(), is(get.getName()));
        assertThat(got.getId(), is(get.getId()));
        assertThat(got.getId(), notNullValue());
    }

    @Test
    public void Test_GetAllBanks() throws Exception {
        MemoryRepository repo = new MemoryRepository();

        BankEntity bankEntity1 = new BankEntity("ID1", "Name", Collections.emptyList());
        BankEntity bankEntity2 = new BankEntity("ID2", "Name", Collections.emptyList());
        repo.create(bankEntity1);
        repo.create(bankEntity2);

        List<BankEntity> bankEntities = repo.get();
        assertThat(bankEntities.size(), is(2));

    }

    @Test
    public void Test_UpdateExistingBank() throws Exception {
        MemoryRepository repo = new MemoryRepository();

        BankEntity bankEntity = new BankEntity("ID1", "Name", Collections.emptyList());
        repo.create(bankEntity);

        BankEntity updated = new BankEntity("ID1", "Name updated", Collections.emptyList());
        repo.update(updated);

        BankEntity got = repo.get("ID1");
        assertThat(got.getId(), is("ID1"));
        assertThat(got.getName(), is("Name updated"));

    }

    @Test
    public void Test_UpdateNonExistingBank() throws Exception {
        MemoryRepository repo = new MemoryRepository();
        BankEntity updated = new BankEntity("ID1", "Name updated", Collections.emptyList());
        Assertions.assertThrows(IllegalArgumentException.class, () -> repo.update(updated));
    }

    @Test
    public void Test_DeleteExistingBank() throws Exception {
        MemoryRepository repo = new MemoryRepository();

        BankEntity bankEntity = new BankEntity("ID1", "Name", Collections.emptyList());
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
}
