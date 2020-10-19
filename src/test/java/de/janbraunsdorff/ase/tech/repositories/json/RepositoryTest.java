package de.janbraunsdorff.ase.tech.repositories.json;

import de.janbraunsdorff.ase.tech.repositories.BankEntity;
import de.janbraunsdorff.ase.tech.repositories.CrudBankRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class RepositoryTest {
    private String basePath;

    @BeforeEach
    void setUp() {
        this.basePath = System.getProperty("user.dir");
    }

    @Test
    public void Test_SingleBankCanBeReadFromFile(){
        String path = this.basePath + "/src/test/resources/singleBank.json";
        CrudBankRepository repository = new Repository(path);

        List<BankEntity> bankEntities = repository.get();

        assertThat(bankEntities.size(), is(1));
        assertThat(bankEntities.get(0).getId(), is("ID"));
        assertThat(bankEntities.get(0).getName(), is("Bank Name"));
        assertThat(bankEntities.get(0).getAccounts().size(), is(0));
    }

    @Test
    public void Test_TwoBanksCanBeReadFromFile(){
        String path = this.basePath + "/src/test/resources/twoBank.json";
        CrudBankRepository repository = new Repository(path);

        List<BankEntity> bankEntities = repository.get();

        assertThat(bankEntities.size(), is(2));
        assertThat(bankEntities.get(0).getId(), is("ID1"));
        assertThat(bankEntities.get(1).getId(), is("ID2"));

    }

    @Test
    public void Test_BankAndAccountCanBeReadFromFile(){
        String path = this.basePath + "/src/test/resources/account.json";
        CrudBankRepository repository = new Repository(path);

        List<BankEntity> bankEntities = repository.get();

        assertThat(bankEntities.size(), is(1));
        assertThat(bankEntities.get(0).getAccounts().size(), is(1));
        assertThat(bankEntities.get(0).getAccounts().get(0).getName(), is("account name"));
        assertThat(bankEntities.get(0).getAccounts().get(0).getId(), is("account id"));
        assertThat(bankEntities.get(0).getAccounts().get(0).getNumber(), is("account number"));
        assertThat(bankEntities.get(0).getAccounts().get(0).getOrder(), is(1));
        assertThat(bankEntities.get(0).getAccounts().get(0).getTransactions().size(), is(0));

    }

    @Test
    public void Test_BankAccountAndTransactionCanBeReadFromFile(){
        String path = this.basePath + "/src/test/resources/transaction.json";
        CrudBankRepository repository = new Repository(path);

        List<BankEntity> bankEntities = repository.get();

        assertThat(bankEntities.size(), is(1));
        assertThat(bankEntities.get(0).getAccounts().size(), is(1));
        assertThat(bankEntities.get(0).getAccounts().get(0).getName(), is("account name"));
        assertThat(bankEntities.get(0).getAccounts().get(0).getId(), is("account id"));
        assertThat(bankEntities.get(0).getAccounts().get(0).getNumber(), is("account number"));
        assertThat(bankEntities.get(0).getAccounts().get(0).getOrder(), is(1));
        assertThat(bankEntities.get(0).getAccounts().get(0).getTransactions().size(), is(1));
        assertThat(bankEntities.get(0).getAccounts().get(0).getTransactions().get(0).getCategory(), is("Miete"));
        assertThat(bankEntities.get(0).getAccounts().get(0).getTransactions().get(0).getContract(), is(true));
        assertThat(bankEntities.get(0).getAccounts().get(0).getTransactions().get(0).getDate().getTime(), is(1535932800000L));
        assertThat(bankEntities.get(0).getAccounts().get(0).getTransactions().get(0).getForm(), is(""));
        assertThat(bankEntities.get(0).getAccounts().get(0).getTransactions().get(0).getTo(), is("Vermieter"));
        assertThat(bankEntities.get(0).getAccounts().get(0).getTransactions().get(0).getId(), is("transaction id"));
        assertThat(bankEntities.get(0).getAccounts().get(0).getTransactions().get(0).getValue(), is(-1_000_00));

    }
}
