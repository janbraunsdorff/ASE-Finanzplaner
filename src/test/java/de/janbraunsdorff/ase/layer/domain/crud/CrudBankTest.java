package de.janbraunsdorff.ase.layer.domain.crud;
import de.janbraunsdorff.ase.layer.persistence.repositories.CrudBankRepository;
import de.janbraunsdorff.ase.layer.persistence.repositories.entität.BankEntity;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class CrudBankTest {
    static final BankEntity defaultBankEntity = new BankEntity( "", "",  "");


    @Test
    public void Test_CreateBank_ThrowsException(){
        CrudBankRepository repo = new TestCrudBankRepositoryException();
        CrudBank service = new CrudBank(repo);

        BankEntity bankEntity = service.create(new BankEntity("", ""));
        assertThat(bankEntity.getId(), notNullValue());
    }

    @Test
    public void Test_CreateBank_Fine(){
        CrudBankRepository repo = new TestCrudBankRepositoryFine();
        CrudBank service = new CrudBank(repo);

        BankEntity bankEntity = service.create(new BankEntity("", ""));
        assertThat(bankEntity.getAcronym(), is(defaultBankEntity.getAcronym()));
        assertThat(bankEntity.getName(), is(defaultBankEntity.getName()));
        assertThat(bankEntity.getId(), notNullValue());
    }

    @Test
    public void Test_GetBank_ThrowsException(){
        CrudBankRepository repo = new TestCrudBankRepositoryException();
        CrudBank service = new CrudBank(repo);

        BankEntity bankEntity = service.get("ID");
        String defaultId = "----";
        assertThat(bankEntity.getId(), is(defaultId));
        assertThat(bankEntity.getName(), is(defaultId));
    }

    @Test
    public void Test_GetBank_Fine(){
        CrudBankRepository repo = new TestCrudBankRepositoryFine();
        CrudBank service = new CrudBank(repo);

        BankEntity bankEntity = service.get("ID");
        assertThat(bankEntity.getId(), is(defaultBankEntity.getId()));
        assertThat(bankEntity.getName(), is(defaultBankEntity.getName()));
    }

    @Test
    public void Test_GetBanks_ThrowsException(){
        CrudBankRepository repo = new TestCrudBankRepositoryException();
        CrudBank service = new CrudBank(repo);

        List<BankEntity> bankEntity = service.get();
        assertThat(bankEntity.size(), is(0));
    }

    @Test
    public void Test_GetBanks_Fine(){
        CrudBankRepository repo = new TestCrudBankRepositoryFine();
        CrudBank service = new CrudBank(repo);

        List<BankEntity> bankEntity = service.get();
        assertThat(bankEntity.size(), is(1));
        assertThat(bankEntity.get(0).getName(), is(defaultBankEntity.getName()));
        assertThat(bankEntity.get(0).getId(), is(defaultBankEntity.getId()));
    }

}
