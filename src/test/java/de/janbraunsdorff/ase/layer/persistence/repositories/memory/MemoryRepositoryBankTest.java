package de.janbraunsdorff.ase.layer.persistence.repositories.memory;


import de.janbraunsdorff.ase.layer.domain.crud.entitties.Bank;
import de.janbraunsdorff.ase.layer.domain.crud.repository.exceptions.AcronymAlreadyExistsException;
import de.janbraunsdorff.ase.layer.domain.crud.repository.exceptions.BankNotFoundExecption;
import de.janbraunsdorff.ase.layer.domain.crud.repository.exceptions.IdAlreadyExitsException;
import de.janbraunsdorff.ase.layer.persistence.repositories.memory.entität.BankMemoryEntity;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
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
        Map<String, BankMemoryEntity> memory = (HashMap<String, BankMemoryEntity>) field;
        memory.put("ID", new BankMemoryEntity("ID", "name", "acronym"));

        Bank bank = repo.getBanks("ID");

        assertThat(bank.getId(), is("ID"));
        assertThat(bank.getName(), is("name"));
        assertThat(bank.getAcronym(), is("acronym"));

    }

    @Test()
    public void getNoneExistingBankByGivenId() {
        MemoryRepository repo = new MemoryRepository();

        Exception exception = assertThrows(BankNotFoundExecption.class, () -> repo.getBanks("ID"));

        String expectedMessage = "Bank mit der ID oder der Abkürzung ID wurde nicht gefunden";
        String actualMessage = exception.getMessage();

        assertThat(actualMessage, is(expectedMessage));
    }

    @SuppressWarnings("unchecked")
    @Test
    public void getExistingBankByGivenAcronym() throws Exception {
        MemoryRepository repo = new MemoryRepository();
        Field f = repo.getClass().getDeclaredField("memory");
        f.setAccessible(true);
        Object field = f.get(repo);
        Map<String, BankMemoryEntity> memory = (HashMap<String, BankMemoryEntity>) field;
        memory.put("ID", new BankMemoryEntity("ID", "name", "acronym"));

        Bank bank = repo.getBankByAcronym("acronym");

        assertThat(bank.getId(), is("ID"));
        assertThat(bank.getName(), is("name"));
        assertThat(bank.getAcronym(), is("acronym"));

    }

    @Test()
    public void getNoneExistingBankByGivenAcronym() {
        MemoryRepository repo = new MemoryRepository();

        Exception exception = assertThrows(BankNotFoundExecption.class, () -> repo.getBankByAcronym("ID"));

        String expectedMessage = "Bank mit der ID oder der Abkürzung ID wurde nicht gefunden";
        String actualMessage = exception.getMessage();

        assertThat(actualMessage, is(expectedMessage));
    }

    @SuppressWarnings("unchecked")
    @Test
    public void createBank() throws Exception {
        MemoryRepository repo = new MemoryRepository();
        Bank entity = new Bank("ID", "name", new ArrayList<>(), "acronym");

        repo.createBank(entity);


        Field f = repo.getClass().getDeclaredField("memory");
        f.setAccessible(true);
        Object field = f.get(repo);
        Map<String, BankMemoryEntity> memory = (HashMap<String, BankMemoryEntity>) field;

        BankMemoryEntity repoEntity = memory.get("ID");

        assertThat(repoEntity.getAcronym(), is(entity.getAcronym()));
        assertThat(repoEntity.getName(), is(entity.getName()));
        assertThat(repoEntity.getId(), is(entity.getId()));
    }

    @SuppressWarnings("unchecked")
    @Test
    public void createBankWithExistingId() throws Exception {
        MemoryRepository repo = new MemoryRepository();
        Field f = repo.getClass().getDeclaredField("memory");
        f.setAccessible(true);
        Object field = f.get(repo);
        Map<String, BankMemoryEntity> memory = (HashMap<String, BankMemoryEntity>) field;
        BankMemoryEntity entity = new BankMemoryEntity("ID", "name", "acronym");
        memory.put("ID", entity);

        Exception exception = assertThrows(IdAlreadyExitsException.class, () -> repo.createBank(new Bank("ID", null, null, null)));


        String expectedMessage = "Die ID ID existiert bereits im System";
        String actualMessage = exception.getMessage();

        assertThat(actualMessage, is(expectedMessage));
    }

    @SuppressWarnings("unchecked")
    @Test
    public void createBankWithExistingAcronym() throws Exception {
        MemoryRepository repo = new MemoryRepository();
        Field f = repo.getClass().getDeclaredField("memory");
        f.setAccessible(true);
        Object field = f.get(repo);
        Map<String, BankMemoryEntity> memory = (HashMap<String, BankMemoryEntity>) field;
        BankMemoryEntity entity = new BankMemoryEntity("ID", "name", "acronym");
        memory.put("Key", entity);

        Exception exception = assertThrows(AcronymAlreadyExistsException.class, () -> repo.createBank(new Bank(null, null, null, "acronym")));


        String expectedMessage = "Die Abkürzung acronym existiert bereits im System";
        String actualMessage = exception.getMessage();

        assertThat(actualMessage, is(expectedMessage));
    }

    @SuppressWarnings("unchecked")
    @Test
    public void deleteBankById() throws Exception {
        MemoryRepository repo = new MemoryRepository();
        Field f = repo.getClass().getDeclaredField("memory");
        f.setAccessible(true);
        Object field = f.get(repo);
        Map<String, BankMemoryEntity> memory = (HashMap<String, BankMemoryEntity>) field;
        BankMemoryEntity entity = new BankMemoryEntity("ID", "name", "acronym");
        memory.put("ID", entity);

        repo.deleteBankById("ID");

        assertThat(memory.values().size(), is(0));

    }

    @SuppressWarnings("unchecked")
    @Test
    public void deleteBankByAcronym() throws Exception {
        MemoryRepository repo = new MemoryRepository();
        Field f = repo.getClass().getDeclaredField("memory");
        f.setAccessible(true);
        Object field = f.get(repo);
        Map<String, BankMemoryEntity> memory = (HashMap<String, BankMemoryEntity>) field;
        BankMemoryEntity entity = new BankMemoryEntity("ID", "name", "acronym");
        memory.put("ID", entity);

        repo.deleteBankByAcronym("acronym");

        assertThat(memory.values().size(), is(0));

    }
}

