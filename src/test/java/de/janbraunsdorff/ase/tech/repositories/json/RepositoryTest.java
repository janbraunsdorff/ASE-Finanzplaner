package de.janbraunsdorff.ase.tech.repositories.json;

import de.janbraunsdorff.ase.tech.repositories.AccountEntity;
import de.janbraunsdorff.ase.tech.repositories.BankEntity;
import de.janbraunsdorff.ase.tech.repositories.CrudBankRepository;
import de.janbraunsdorff.ase.tech.repositories.TransactionEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.nullValue;
import static org.mockito.Mockito.when;

class RepositoryTest {
    private String basePath;

    @BeforeEach
    void setUp() {
        this.basePath = System.getProperty("user.dir");
    }

    @Test
    public void Test_SingleBankCanBeReadFromFile() throws Exception{
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
    public void Test_TwoBanksCanBeReadFromFile() throws Exception{
        String path = this.basePath + "/src/test/resources/twoBank.json";
        CrudBankRepository repository = new Repository(path);

        List<BankEntity> bankEntities = repository.get();

        assertThat(bankEntities.size(), is(2));
        assertThat(bankEntities.get(0).getId(), is("ID1"));
        assertThat(bankEntities.get(1).getId(), is("ID2"));

    }

    @Test
    public void Test_BankAndAccountCanBeReadFromFile() throws Exception{
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
    public void Test_BankAccountAndTransactionCanBeReadFromFile() throws Exception{
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

    @Test
    public void Test_GetBankById() throws Exception{
        String path = this.basePath + "/src/test/resources/twoBank.json";
        CrudBankRepository repository = new Repository(path);

        BankEntity bankEntity = repository.get("ID1");

        assertThat(bankEntity.getId(), is("ID1"));
        assertThat(bankEntity.getName(), is("Bank Name1"));

    }

    @Test
    public void Test_GetBankById_NotPresent() throws Exception{
        String path = this.basePath + "/src/test/resources/twoBank.json";
        CrudBankRepository repository = new Repository(path);

        BankEntity bankEntity = repository.get("ID3");

        assertThat(bankEntity.getId(), nullValue());
        assertThat(bankEntity.getName(), nullValue());

    }

    @Test()
    public void Test_GetBankFileNotFound(){
        String path = this.basePath + "/src/test/resources/notFound.json";
        final CrudBankRepository repository = new Repository(path);

        Assertions.assertThrows(NoSuchFileException.class, () -> repository.get("ID3"));
    }


    @Test
    public void Test_CreateBankEntity_FileNotExits() throws Exception {
        String path = this.basePath + "/src/test/resources/bank_createFile.json";
        CrudBankRepository repository = new Repository(path);

        BankEntity bankEntity = new BankEntity();
        repository.create(bankEntity);

        File f = new File(path);
        assertThat(f.exists(), is(true));
        assertThat(f.isDirectory(), is(false));

        Files.delete(Paths.get(path));
    }

    @Test
    public void Test_CreateBankEntity_AllInformationGiven() throws Exception {
        String path = this.basePath + "/src/test/resources/bank_createBankAllInformation.json";
        CrudBankRepository repository = new Repository(path);

        BankEntity bankEntity = new BankEntity("Id", "name", Collections.emptyList());
        repository.create(bankEntity);

        BankEntity got = new JsonReader().readBanks(path).get(0);

        assertThat(got.getName(), is(bankEntity.getName()));
        assertThat(got.getId(), is(bankEntity.getId()));
        assertThat(got.getAccounts().size(), is(0));


        Files.delete(Paths.get(path));
    }

    @Test
    public void Test_CreateBankEntity_IdIsMissing() throws Exception {
        String path = this.basePath + "/src/test/resources/bank_createBank_IdMissing.json";
        CrudBankRepository repository = new Repository(path);

        BankEntity bankEntity = new BankEntity(null, "name", Collections.emptyList());
        repository.create(bankEntity);

        BankEntity got = new JsonReader().readBanks(path).get(0);

        assertThat(got.getName(), is(bankEntity.getName()));
        assertThat(got.getAccounts().size(), is(0));

        try{
            UUID uuid = UUID.fromString(got.getId());
        } catch (IllegalArgumentException exception){
            assertThat(got.getId(), is(0));
        }

        Files.delete(Paths.get(path));
    }

    @Test
    public void Test_CreateBankEntity_IdIsEmpty() throws Exception {
        String path = this.basePath + "/src/test/resources/bank_createBank_IdEmpty.json";
        CrudBankRepository repository = new Repository(path);

        BankEntity bankEntity = new BankEntity("", "name", Collections.emptyList());
        repository.create(bankEntity);

        BankEntity got = new JsonReader().readBanks(path).get(0);

        assertThat(got.getName(), is(bankEntity.getName()));
        assertThat(got.getAccounts().size(), is(0));

        try{
            UUID uuid = UUID.fromString(got.getId());
        } catch (IllegalArgumentException exception){
            assertThat(got.getId(), is(0));
        }

        Files.delete(Paths.get(path));
    }
}
