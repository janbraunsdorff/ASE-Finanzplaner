package de.janbraunsdorff.ase.tech.repositories.json;

import de.janbraunsdorff.ase.tech.repositories.AccountEntity;
import de.janbraunsdorff.ase.tech.repositories.BankEntity;
import de.janbraunsdorff.ase.tech.repositories.CrudBankRepository;
import de.janbraunsdorff.ase.tech.repositories.TransactionEntity;
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

        BankEntity bankEntity = bankEntities.get(0);
        assertThat(bankEntity.getId(), is("ID"));
        assertThat(bankEntity.getName(), is("Bank Name"));
        assertThat(bankEntity.getAccounts().size(), is(0));
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

        AccountEntity account = bankEntities.get(0).getAccounts().get(0);
        assertThat(account.getName(), is("account name"));
        assertThat(account.getId(), is("account id"));
        assertThat(account.getNumber(), is("account number"));
        assertThat(account.getOrder(), is(1));
        assertThat(account.getTransactions().size(), is(0));

    }

    @Test
    public void Test_BankAccountAndTransactionCanBeReadFromFile(){
        String path = this.basePath + "/src/test/resources/transaction.json";
        CrudBankRepository repository = new Repository(path);

        List<BankEntity> bankEntities = repository.get();

        assertThat(bankEntities.size(), is(1));
        assertThat(bankEntities.get(0).getAccounts().size(), is(1));

        AccountEntity account = bankEntities.get(0).getAccounts().get(0);
        assertThat(account.getName(), is("account name"));
        assertThat(account.getId(), is("account id"));
        assertThat(account.getNumber(), is("account number"));
        assertThat(account.getOrder(), is(1));
        assertThat(account.getTransactions().size(), is(1));

        TransactionEntity transaction = account.getTransactions().get(0);
        assertThat(transaction.getCategory(), is("Miete"));
        assertThat(transaction.getContract(), is(true));
        assertThat(transaction.getDate().getTime(), is(1535932800000L));
        assertThat(transaction.getForm(), is(""));
        assertThat(transaction.getTo(), is("Vermieter"));
        assertThat(transaction.getId(), is("transaction id"));
        assertThat(transaction.getValue(), is(-1_000_00));

    }
}
