package de.janbraunsdorff.ase.usecases.crud;
import de.janbraunsdorff.ase.tech.repositories.BankEntity;
import de.janbraunsdorff.ase.tech.repositories.CrudBankRepository;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class CrudBankTest {
    static BankEntity defaultBankEntity = new BankEntity("ID", "Name", Collections.emptyList());


    private final String  defaultId = "----";

    @Test
    public void Test_CreateBank_ThrowsException(){
        CrudBankRepository repo = new TestCrudBankRepositoryException();
        CrudBank service = new CrudBank(repo);

        BankEntity bankEntity = service.create(new BankEntity());
        assertThat(bankEntity.getId(), is(defaultId));
        assertThat(bankEntity.getName(), is(defaultId));
    }

    @Test
    public void Test_CreateBank_Fine(){
        CrudBankRepository repo = new TestCrudBankRepositoryFine();
        CrudBank service = new CrudBank(repo);

        BankEntity bankEntity = service.create(new BankEntity());
        assertThat(bankEntity.getId(), is(defaultBankEntity.getId()));
        assertThat(bankEntity.getName(), is(defaultBankEntity.getName()));
    }

    @Test
    public void Test_GetBank_ThrowsException(){
        CrudBankRepository repo = new TestCrudBankRepositoryException();
        CrudBank service = new CrudBank(repo);

        BankEntity bankEntity = service.get("ID");
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

    @Test
    public void Test_UpdateBank_ThrowsException(){
        CrudBankRepository repo = new TestCrudBankRepositoryException();
        CrudBank service = new CrudBank(repo);

        BankEntity bankEntity = service.update(new BankEntity());
        assertThat(bankEntity.getId(), is(defaultId));
        assertThat(bankEntity.getName(), is(defaultId));
    }

    @Test
    public void Test_UpdateBank_Fine(){
        CrudBankRepository repo = new TestCrudBankRepositoryFine();
        CrudBank service = new CrudBank(repo);

        BankEntity bankEntity = service.update(new BankEntity());
        assertThat(bankEntity.getName(), is(defaultBankEntity.getName()));
        assertThat(bankEntity.getId(), is(defaultBankEntity.getId()));

    }

    @Test
    public void Test_DeleteBank_ThrowsException(){
        CrudBankRepository repo = new TestCrudBankRepositoryException();
        CrudBank service = new CrudBank(repo);

        boolean wasDeleted = service.delete("ID");
        assertThat(wasDeleted, is(false));
    }

    @Test
    public void Test_DeleteBank_Fine(){
        CrudBankRepository repo = new TestCrudBankRepositoryFine();
        CrudBank service = new CrudBank(repo);

        boolean wasDeleted = service.delete("ID");
        assertThat(wasDeleted, is(true));
    }
}
