package de.janbraunsdorff.ase.layer.persistence.repositories.memory;


import de.janbraunsdorff.ase.layer.persistence.repositories.BankNotFoundExecption;
import de.janbraunsdorff.ase.layer.persistence.repositories.entität.AccountEntity;
import de.janbraunsdorff.ase.layer.persistence.repositories.entität.BankEntity;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
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
}

