package de.janbraunsdorff.ase.layer.persistence.repositories.memory;


import de.janbraunsdorff.ase.layer.domain.crud.entitties.Bank;
import de.janbraunsdorff.ase.layer.domain.crud.repository.exceptions.AcronymAlreadyExistsException;
import de.janbraunsdorff.ase.layer.domain.crud.repository.exceptions.BankNotFoundExecption;
import de.janbraunsdorff.ase.layer.persistence.repositories.memory.entität.BankMemoryEntity;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MemoryRepositoryBankTest {


    @Test
    public void getExistingBankByGivenId() throws Exception {
        MemoryRepository base = new MemoryRepository();
        BankMemoryRepository repo = new BankMemoryRepository(base);
        Map<String, BankMemoryEntity> memory = getMemory(base);
        memory.put("ID", new BankMemoryEntity("ID", "name", "acronym"));

        Bank bank = repo.getBanks("ID");

        assertThat(bank.getName(), is("name"));
        assertThat(bank.getAcronym(), is("acronym"));

    }

    @SuppressWarnings("unchecked")
    private Map<String, BankMemoryEntity> getMemory(MemoryRepository base) throws NoSuchFieldException, IllegalAccessException {
        Field f = base.getClass().getDeclaredField("memory");
        f.setAccessible(true);
        Object field = f.get(base);
        return (Map<String, BankMemoryEntity>) field;
    }

    @Test()
    public void getNoneExistingBankByGivenId() {
        MemoryRepository base = new MemoryRepository();
        BankMemoryRepository repo = new BankMemoryRepository(base);

        Exception exception = assertThrows(BankNotFoundExecption.class, () -> repo.getBanks("ID"));

        String expectedMessage = "Bank mit der ID oder der Abkürzung ID wurde nicht gefunden";
        String actualMessage = exception.getMessage();

        assertThat(actualMessage, is(expectedMessage));
    }

    @Test
    public void getExistingBankByGivenAcronym() throws Exception {
        MemoryRepository base = new MemoryRepository();
        BankMemoryRepository repo = new BankMemoryRepository(base);
        Map<String, BankMemoryEntity> memory = getMemory(base);
        memory.put("ID", new BankMemoryEntity("ID", "name", "acronym"));

        Bank bank = repo.getBankByAcronym("acronym");

        assertThat(bank.getName(), is("name"));
        assertThat(bank.getAcronym(), is("acronym"));

    }

    @Test
    public void getNoneExistingBankByGivenAcronym() {
        MemoryRepository base = new MemoryRepository();
        BankMemoryRepository repo = new BankMemoryRepository(base);

        Exception exception = assertThrows(BankNotFoundExecption.class, () -> repo.getBankByAcronym("ID"));

        String expectedMessage = "Bank mit der ID oder der Abkürzung ID wurde nicht gefunden";
        String actualMessage = exception.getMessage();

        assertThat(actualMessage, is(expectedMessage));
    }

    @Test
    public void createBank() throws Exception {
        MemoryRepository base = new MemoryRepository();
        BankMemoryRepository repo = new BankMemoryRepository(base);
        Bank entity = new Bank("name", new ArrayList<>(), "acronym");

        repo.createBank(entity);


        Map<String, BankMemoryEntity> memory = getMemory(base);

        BankMemoryEntity repoEntity = memory.get("acronym");

        assertThat(repoEntity.getAcronym(), is(entity.getAcronym()));
        assertThat(repoEntity.getName(), is(entity.getName()));
    }

    @Test
    public void createBankWithExistingId() throws Exception {
        MemoryRepository base = new MemoryRepository();
        BankMemoryRepository repo = new BankMemoryRepository(base);
        Map<String, BankMemoryEntity> memory = getMemory(base);
        BankMemoryEntity entity = new BankMemoryEntity("ID", "name", "acronym");
        memory.put("acronym", entity);

        Exception exception = assertThrows(AcronymAlreadyExistsException.class, () -> repo.createBank(new Bank(null, Collections.emptyList(), "acronym")));


        String expectedMessage = "Die Abkürzung acronym existiert bereits im System";
        String actualMessage = exception.getMessage();

        assertThat(actualMessage, is(expectedMessage));
    }

    @Test
    public void createBankWithExistingAcronym() throws Exception {
        MemoryRepository base = new MemoryRepository();
        BankMemoryRepository repo = new BankMemoryRepository(base);
        Map<String, BankMemoryEntity> memory = getMemory(base);
        BankMemoryEntity entity = new BankMemoryEntity("ID", "name", "acronym");
        memory.put("Key", entity);

        Exception exception = assertThrows(AcronymAlreadyExistsException.class, () -> repo.createBank(new Bank(null, null, "acronym")));


        String expectedMessage = "Die Abkürzung acronym existiert bereits im System";
        String actualMessage = exception.getMessage();

        assertThat(actualMessage, is(expectedMessage));
    }

    @Test
    public void deleteBankById() throws Exception {
        MemoryRepository base = new MemoryRepository();
        BankMemoryRepository repo = new BankMemoryRepository(base);
        Map<String, BankMemoryEntity> memory = getMemory(base);
        BankMemoryEntity entity = new BankMemoryEntity("ID", "name", "acronym");
        memory.put("ID", entity);

        repo.deleteBankById("ID");

        assertThat(memory.values().size(), is(0));

    }

    @Test
    public void deleteBankByAcronym() throws Exception {
        MemoryRepository base = new MemoryRepository();
        BankMemoryRepository repo = new BankMemoryRepository(base);
        Map<String, BankMemoryEntity> memory = getMemory(base);
        BankMemoryEntity entity = new BankMemoryEntity("ID", "name", "acronym");
        memory.put("ID", entity);

        repo.deleteBankByAcronym("acronym");

        assertThat(memory.values().size(), is(0));

    }
}

