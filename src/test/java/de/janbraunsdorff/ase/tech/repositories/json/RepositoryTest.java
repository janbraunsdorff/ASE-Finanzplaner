package de.janbraunsdorff.ase.tech.repositories.json;

import de.janbraunsdorff.ase.tech.repositories.entität.AccountEntity;
import de.janbraunsdorff.ase.tech.repositories.entität.BankEntity;
import de.janbraunsdorff.ase.tech.repositories.CrudBankRepository;
import de.janbraunsdorff.ase.tech.repositories.entität.TransactionEntity;
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

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

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

    @Test
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

        BankEntity bankEntity = new BankEntity("Id", "name", Collections.emptyList(), "n");
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

        BankEntity bankEntity = new BankEntity(null, "name", Collections.emptyList(), "n");
        repository.create(bankEntity);

        BankEntity got = new JsonReader().readBanks(path).get(0);

        assertThat(got.getName(), is(bankEntity.getName()));
        assertThat(got.getAccounts().size(), is(0));
        assertThat(got.getId(), notNullValue());
        Files.delete(Paths.get(path));
    }

    @Test
    public void Test_CreateBankEntity_IdIsEmpty() throws Exception {
        String path = this.basePath + "/src/test/resources/bank_createBank_IdEmpty.json";
        CrudBankRepository repository = new Repository(path);

        BankEntity bankEntity = new BankEntity("", "name", Collections.emptyList(), "un");
        BankEntity result = repository.create(bankEntity);

        BankEntity got = new JsonReader().readBanks(path).get(0);

        assertThat(got.getName(), is(bankEntity.getName()));
        assertThat(got.getAccounts().size(), is(0));

        try{
            UUID.fromString(got.getId());
            UUID.fromString(result.getId());
        } catch (IllegalArgumentException exception){
            assertThat(got.getId(), is(0));
        }

        Files.delete(Paths.get(path));
    }

    @Test
    public void Test_CreateBankEntity_AppendToExistingFile() throws Exception {
        String baseFileContent = "[{\"id\": \"ID\", \"name\": \"Bank Name\", \"accounts\": []}]";
        String path = this.basePath + "/src/test/resources/addBank.json";
        File f = new File(path);
        if (!f.exists()){
            f.delete();
        }
        f.createNewFile();
        Files.write(Paths.get(path), baseFileContent.getBytes());
        CrudBankRepository repository = new Repository(path);

        BankEntity bankEntity = new BankEntity("", "name", Collections.emptyList(), "un");
        repository.create(bankEntity);

        List<BankEntity> bankEntities = new JsonReader().readBanks(path);
        assertThat(bankEntities.size(), is(2));
        assertThat(bankEntities.get(0).getId(), is("ID"));
        try{
            UUID uuid = UUID.fromString(bankEntities.get(1).getId());
        } catch (IllegalArgumentException exception){
            assertThat(bankEntities.get(1).getId(), is(0));
        }

        Files.delete(Paths.get(path));
    }

    @Test
    public void Test_CreateBankEntity_UpdateExistingBank() throws Exception {
        String baseFileContent = "[{\"id\": \"ID\", \"name\": \"Bank Name\", \"accounts\": []}]";
        String path = this.basePath + "/src/test/resources/updateBank.json";
        File f = new File(path);
        if (!f.exists()){
            f.delete();
        }
        f.createNewFile();
        Files.write(Paths.get(path), baseFileContent.getBytes());
        CrudBankRepository repository = new Repository(path);

        BankEntity bankEntity = new BankEntity("ID", "updated name", Collections.emptyList(), "un");
        repository.update(bankEntity);

        List<BankEntity> bankEntities = new JsonReader().readBanks(path);
        assertThat(bankEntities.size(), is(1));
        assertThat(bankEntities.get(0).getId(), is("ID"));
        assertThat(bankEntities.get(0).getName(), is("updated name"));

        Files.delete(Paths.get(path));
    }

    @Test
    public void Test_CreateBankEntity_UpdateExistingKeepOtherBank() throws Exception {
        String baseFileContent =
                "[" +
                "  {" +
                "    \"id\": \"ID1\"," +
                "    \"name\": \"Bank Name1\"," +
                "    \"accounts\": []" +
                "  }," +
                "  {" +
                "    \"id\": \"ID2\"," +
                "    \"name\": \"Bank Name2\"," +
                "    \"accounts\": []" +
                "  }" +
                "]";
        String path = this.basePath + "/src/test/resources/updateKeepOthersBank.json";
        File f = new File(path);
        if (!f.exists()){
            f.delete();
        }
        f.createNewFile();
        Files.write(Paths.get(path), baseFileContent.getBytes());
        CrudBankRepository repository = new Repository(path);

        BankEntity bankEntity = new BankEntity("ID1", "updated name", Collections.emptyList(), "un");
        repository.update(bankEntity);

        List<BankEntity> bankEntities = new JsonReader().readBanks(path);
        assertThat(bankEntities.size(), is(2));
        assertThat(bankEntities.get(0).getId(), is("ID2"));
        assertThat(bankEntities.get(0).getName(), is("Bank Name2"));
        assertThat(bankEntities.get(1).getId(), is("ID1"));
        assertThat(bankEntities.get(1).getName(), is("updated name"));

        Files.delete(Paths.get(path));
    }

    @Test
    public void Test_CreateBankEntity_UpdateNOtExistingBank() throws Exception {
        String baseFileContent = "[]";
        String path = this.basePath + "/src/test/resources/updateNonExisting.json";
        File f = new File(path);
        if (!f.exists()){
            f.delete();
        }
        f.createNewFile();
        Files.write(Paths.get(path), baseFileContent.getBytes());
        CrudBankRepository repository = new Repository(path);

        BankEntity bankEntity = new BankEntity("ID1", "updated name", Collections.emptyList(), "un");
        repository.update(bankEntity);

        List<BankEntity> bankEntities = new JsonReader().readBanks(path);
        assertThat(bankEntities.size(), is(0));

        Files.delete(Paths.get(path));
    }

    @Test
    public void Test_CreateBankEntity_DeleteExistingBank() throws Exception {
        String baseFileContent =
            "[" +
            "  {" +
            "    \"id\": \"ID1\"," +
            "    \"name\": \"Bank Name1\"," +
            "    \"accounts\": []" +
            "  }" +
            "]";
        String path = this.basePath + "/src/test/resources/deleteExistingBank.json";
        File f = new File(path);
        if (!f.exists()){
            f.delete();
        }
        f.createNewFile();
        Files.write(Paths.get(path), baseFileContent.getBytes());
        CrudBankRepository repository = new Repository(path);

        repository.delete("ID1");

        List<BankEntity> bankEntities = new JsonReader().readBanks(path);
        assertThat(bankEntities.size(), is(0));

        Files.delete(Paths.get(path));
    }

    @Test
    public void Test_CreateBankEntity_DeleteExistingBank_KeppOther() throws Exception {
        String baseFileContent =
                "[" +
                        "  {" +
                        "    \"id\": \"ID1\"," +
                        "    \"name\": \"Bank Name1\"," +
                        "    \"accounts\": []" +
                        "  }," +
                        "  {" +
                        "    \"id\": \"ID2\"," +
                        "    \"name\": \"Bank Name2\"," +
                        "    \"accounts\": []" +
                        "  }" +
                        "]";
        String path = this.basePath + "/src/test/resources/deleteKeepOthersBank.json";
        File f = new File(path);
        if (!f.exists()){
            f.delete();
        }
        f.createNewFile();
        Files.write(Paths.get(path), baseFileContent.getBytes());
        CrudBankRepository repository = new Repository(path);

        repository.delete("ID1");

        List<BankEntity> bankEntities = new JsonReader().readBanks(path);
        assertThat(bankEntities.size(), is(1));
        assertThat(bankEntities.get(0).getId(), is("ID2"));
        assertThat(bankEntities.get(0).getName(), is("Bank Name2"));

        Files.delete(Paths.get(path));
    }
}
