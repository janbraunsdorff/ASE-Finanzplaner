package de.janbraunsdorff.ase.tech.repositories.json;

import de.janbraunsdorff.ase.tech.repositories.BankEntity;
import de.janbraunsdorff.ase.tech.repositories.CrudBankRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.nullValue;

class RepositoryTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    public void Test_SingleBankCanBeReadFromFile(){
        String currentPath = System.getProperty("user.dir");
        String path = currentPath + "/src/test/resources/singleBank.json";
        CrudBankRepository repository = new Repository(path);

        List<BankEntity> bankEntities = repository.get();

        assertThat(bankEntities.size(), is(1));
        assertThat(bankEntities.get(0).getId(), is("ID"));
        assertThat(bankEntities.get(0).getName(), is("Bank Name"));
        assertThat(bankEntities.get(0).getAccounts(), nullValue());
    }
}
