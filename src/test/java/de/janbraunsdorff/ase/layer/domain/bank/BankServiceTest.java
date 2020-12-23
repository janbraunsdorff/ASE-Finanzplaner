package de.janbraunsdorff.ase.layer.domain.bank;

import de.janbraunsdorff.ase.layer.domain.AccountTestRepo;
import de.janbraunsdorff.ase.layer.domain.BankTestRepo;
import de.janbraunsdorff.ase.layer.domain.TransactionTestRepo;
import de.janbraunsdorff.ase.layer.domain.AcronymAlreadyExistsException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class BankServiceTest {
    @Test
    public void getBanks() {
        AccountTestRepo accRepo = new AccountTestRepo();
        BankTestRepo bankRepo = new BankTestRepo();
        TransactionTestRepo tranRepo = new TransactionTestRepo();

        BankService service = new BankService(bankRepo, accRepo, tranRepo);

        List<BankDTO> bankDTOS = service.get();
        assertThat(bankDTOS.size(), is(4));
        assertThat(bankDTOS.get(0).getAcronym(), is("b1"));
        assertThat(bankDTOS.get(0).getNumberOfAccount(), is(4));
        assertThat(bankDTOS.get(0).getName(), is("name1"));
    }

    @Test
    public void createBank() throws AcronymAlreadyExistsException {
        BankTestRepo bankRepo = new BankTestRepo();
        BankService service = new BankService(bankRepo, null, null);

        BankCreateCommand cmd = new BankCreateCommand("name","ac");
        BankDTO bankDTO = service.create(cmd);

        assertThat(bankRepo.createBankEntity.getAcronym(), is("ac"));
        assertThat(bankRepo.createBankEntity.getName(), is("name"));

        assertThat(bankDTO.getValue(), is(0));
        assertThat(bankDTO.getNumberOfAccount(), is(0));
        assertThat(bankDTO.getName(), is("name"));
        assertThat(bankDTO.getAcronym(), is("ac"));
    }

    @Test
    public void deleteBank(){
        BankTestRepo bankRepo = new BankTestRepo();
        BankService service = new BankService(bankRepo, null, null);

        BankDeleteCommand cmd = new BankDeleteCommand("ac");
        service.deleteByAcronym(cmd);
        assertThat(bankRepo.deleteBankId, is("ac"));
    }
}
