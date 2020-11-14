package de.janbraunsdorff.ase.layer.persistence.repositories.memory;


import de.janbraunsdorff.ase.layer.persistence.repositories.BankNotFoundExecution;
import de.janbraunsdorff.ase.layer.persistence.repositories.entität.BankEntity;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MemoryRepositoryBankTest {

    @SuppressWarnings("unchecked")
    @Test
    public void getExistingBankByGivenId() throws Exception {
        MemoryRepository repo = new MemoryRepository();
        Field f = repo.getClass().getDeclaredField("memory");
        f.setAccessible(true);
        Object field = f.get(repo);
        Map<String, BankEntity> memory = (HashMap<String, BankEntity>) field;
        memory.put("ID", new BankEntity("ID", "name", "acronym"));

        BankEntity bank = repo.getBankById("ID");

        assertThat(bank.getId(), is("ID"));
        assertThat(bank.getName(), is("name"));
        assertThat(bank.getAcronym(), is("acronym"));

    }

    @Test()
    public void getNoneExistingBankByGivenId() {
        MemoryRepository repo = new MemoryRepository();

        Exception exception = assertThrows(BankNotFoundExecution.class, () -> {
            repo.getBankById("ID");
        });

        String expectedMessage = "Bank mit der ID oder der Abkürzung ID wurde nicht gefunden";
        String actualMessage = exception.getMessage();

        assertThat(actualMessage, is(expectedMessage));
    }
}

