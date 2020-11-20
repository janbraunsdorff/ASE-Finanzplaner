package de.janbraunsdorff.ase.layer.domain.transaction;

import de.janbraunsdorff.ase.layer.domain.AccountTestRepo;
import de.janbraunsdorff.ase.layer.domain.TransactionTestRepo;
import de.janbraunsdorff.ase.layer.persistence.AccountNotFoundException;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TransactionServiceTest {
    @Test
    public void createTransactionWithExistingAccount() throws AccountNotFoundException {
        TransactionTestRepo transRepo = new TransactionTestRepo();
        AccountTestRepo accRepo = new AccountTestRepo();

        TransactionService service = new TransactionService(transRepo, accRepo);
        LocalDate d = LocalDate.now();
        TransactionDTO dto = service.createTransactionByAccountId(
                new TransactionCreateCommand(
                        "accountAcronym",
                        1,
                        d,
                        "third party",
                        "category",
                        false
                )
        );

        // Return value
        assertThat(dto.getValue(), Matchers.is(1));
        assertThat(dto.getDate(), Matchers.is(d));
        assertThat(dto.getThirdParty(), Matchers.is("third party"));
        assertThat(dto.getCategory(), Matchers.is("category"));
        assertThat(dto.getId(), Matchers.notNullValue());

        // repo
        assertThat(transRepo.entity.getValue(), Matchers.is(1));
        assertThat(transRepo.entity.getDate(), Matchers.is(d));
        assertThat(transRepo.entity.getThirdParty(), Matchers.is("third party"));
        assertThat(transRepo.entity.getCategory(), Matchers.is("category"));
        assertThat(transRepo.entity.getId(), Matchers.notNullValue());
        assertThat(transRepo.entity.getAccountAcronym(), Matchers.is("accountAcronym"));

    }

    @Test
    public void createTransactionWithNonExistingAccount(){
        TransactionTestRepo transRepo = new TransactionTestRepo();
        AccountTestRepo accRepo = new AccountTestRepo();

        TransactionService service = new TransactionService(transRepo, accRepo);
        LocalDate d = LocalDate.now();
        assertThrows(AccountNotFoundException.class, () ->service.createTransactionByAccountId(
                new TransactionCreateCommand(
                        "nonAccount",
                        1,
                        d,
                        "third party",
                        "category",
                        false
                )
        ));
    }

    @Test
    public void getTransactionOfAccount() throws AccountNotFoundException {
        TransactionTestRepo transRepo = new TransactionTestRepo();
        AccountTestRepo accRepo = new AccountTestRepo();

        TransactionService service = new TransactionService(transRepo, accRepo);
        LocalDate d = LocalDate.now();
        List<TransactionDTO> dto = service.getTransactions(
                new TransactionGetQuery(
                        "a1",
                        1
                )
        );

        // Return value
        assertThat(dto.size(), Matchers.is(4));
        assertThat(dto.get(0).getValue(), Matchers.is(8));
        assertThat(dto.get(0).getThirdParty(), Matchers.is("tp"));
        assertThat(dto.get(0).getCategory(), Matchers.is("cat"));
        assertThat(dto.get(0).getId(), Matchers.notNullValue());

        // repo
        assertThat(transRepo.count, Matchers.is(1));
        assertThat(transRepo.accountAcronym, Matchers.is("a1"));

    }
}
