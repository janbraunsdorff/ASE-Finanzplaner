package de.janbraunsdorff.ase.layer.persistence.memory;

import de.janbraunsdorff.ase.layer.domain.bank.Bank;
import de.janbraunsdorff.ase.layer.persistence.AcronymAlreadyExistsException;
import de.janbraunsdorff.ase.layer.persistence.BankNotFoundException;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BankMemoryRepositoryTest {

    @SuppressWarnings("unchecked")
    private Map<String, Bank> getMemory(BankMemoryRepository obj){
        try {
            Field f = obj.getClass().getDeclaredField("memory"); //NoSuchFieldException
            f.setAccessible(true);
            return (Map<String, Bank>) f.get(obj);
        } catch (IllegalAccessException |NoSuchFieldException e) {
            throw new IllegalArgumentException();
        }
    }

    @Test
    public void givenBankRepositoryWithBanks_shouldReturnsBanks(){
        BankMemoryRepository repo = new BankMemoryRepository();

        Bank bank = new Bank("name", "ac");
        getMemory(repo).put(bank.getId(), bank);

        List<Bank> banks = repo.getBank();

        assertThat(banks.size(), is(1));
    }

    @Test
    public void givenBankEmptyBankRepository_canCreateBank() throws AcronymAlreadyExistsException {
        BankMemoryRepository repo = new BankMemoryRepository();
        Bank bank = new Bank("name", "ac");
        repo.createBank(bank);

        Bank repoBank = getMemory(repo).get(bank.getAcronym());
        assertThat(bank.getAcronym(), is(repoBank.getAcronym()));
        assertThat(bank.getId(), is(repoBank.getId()));
        assertThat(bank.getName(), is(repoBank.getName()));
    }

    @Test
    public void givenBankBankRepositoryWithBankAcronymExits_throwsException() {
        BankMemoryRepository repo = new BankMemoryRepository();
        Bank bank = new Bank("name", "ac");
        getMemory(repo).put(bank.getAcronym(), bank);

        Bank errorBank = new Bank("name", "ac");
        assertThrows(AcronymAlreadyExistsException.class, ()-> repo.createBank(errorBank));
    }

    @Test
    public void givenBankBankRepositoryWithBank_returnsBank() throws BankNotFoundException {
        BankMemoryRepository repo = new BankMemoryRepository();
        Bank bank = new Bank("name", "ac");
        getMemory(repo).put(bank.getAcronym(), bank);


        Bank repoBank = repo.getBankByAcronym("ac");
        assertThat(bank.getId(), is(repoBank.getId()));
        assertThat(bank.getName(), is(repoBank.getName()));
        assertThat(bank.getAcronym(), is(repoBank.getAcronym()));
    }

    @Test
    public void givenBankBankRepositoryWithNotExistingBank_throwsException() {
        BankMemoryRepository repo = new BankMemoryRepository();

        assertThrows(BankNotFoundException.class, ()-> repo.getBankByAcronym("ac"));
    }

    @Test
    public void givenBankRepositoryWithBank_deleteBank(){
        BankMemoryRepository repo = new BankMemoryRepository();
        Bank bank = new Bank("name", "ac");
        getMemory(repo).put(bank.getAcronym(), bank);

        repo.deleteBankByAcronym("ac");

        assertThat(getMemory(repo).size(), is(0));
    }

}
