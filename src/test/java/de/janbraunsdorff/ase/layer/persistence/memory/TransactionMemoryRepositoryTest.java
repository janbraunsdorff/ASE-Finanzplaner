package de.janbraunsdorff.ase.layer.persistence.memory;

import de.janbraunsdorff.ase.layer.domain.transaction.Transaction;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class TransactionMemoryRepositoryTest {
    @SuppressWarnings("unchecked")
    private Map<String, Transaction> getMemory(TransactionMemoryRepository obj){
        try {
            Field f = obj.getClass().getDeclaredField("transactions"); //NoSuchFieldException
            f.setAccessible(true);
            return (Map<String, Transaction>) f.get(obj);
        } catch (IllegalAccessException |NoSuchFieldException e) {
            throw new IllegalArgumentException();
        }
    }

    @Test
    public void givenEmptyTransactionMemory_createNewTransaction(){
        TransactionMemoryRepository repo = new TransactionMemoryRepository();

        Transaction t = new Transaction("accAc", 1, LocalDate.now(), "tp", "cat", false);
        repo.createTransaction(t);

        Transaction transaction = getMemory(repo).get(t.getId());
        assertThat(transaction.getValue(), is(t.getValue()));
        assertThat(transaction.getAccountAcronym(), is(t.getAccountAcronym()));
        assertThat(transaction.getCategory(), is(t.getCategory()));
        assertThat(transaction.getThirdParty(), is(t.getThirdParty()));
        assertThat(transaction.getContract(), is(t.getContract()));
    }

    @Test
    public void givenTransactionMemory_getTransactionOfAccount(){
        TransactionMemoryRepository repo = new TransactionMemoryRepository();

        Transaction t = new Transaction("accAc", 1, LocalDate.now(), "tp", "cat", false);
        getMemory(repo).put(t.getId(), t);

        List<Transaction> accAc = repo.getTransactionOfAccount("accAc", 1);

        assertThat(accAc.size(), is(1));

    }

    @Test
    public void givenTransactionMemory_getTransactionOfNonExistingAccount(){
        TransactionMemoryRepository repo = new TransactionMemoryRepository();

        Transaction t = new Transaction("accAc", 1, LocalDate.now(), "tp", "cat", false);
        getMemory(repo).put(t.getId(), t);

        List<Transaction> accAc = repo.getTransactionOfAccount("acc", 1);

        assertThat(accAc.size(), is(0));

    }

    @Test
    public void givenTransactionMemory_getTransactionOfAccountMax20(){
        TransactionMemoryRepository repo = new TransactionMemoryRepository();

        for (int i = 0; i < 25; i++){
            Transaction t = new Transaction("accAc", 1, LocalDate.now(), "tp", "cat", false);
            getMemory(repo).put(t.getId(), t);
        }

        List<Transaction> accAc = repo.getTransactionOfAccount("accAc", 20);

        assertThat(accAc.size(), is(20));

    }

    @Test
    public void givenTransactionMemory_getTransactionOfAccountMaxAll(){
        TransactionMemoryRepository repo = new TransactionMemoryRepository();

        for (int i = 0; i < 25; i++){
            Transaction t = new Transaction("accAc", 1, LocalDate.now(), "tp", "cat", false);
            getMemory(repo).put(t.getId(), t);
        }

        List<Transaction> accAc = repo.getTransactionOfAccount("accAc", -1);

        assertThat(accAc.size(), is(25));

    }

    @Test
    public void givenTransactionMemory_getValueOfAccount(){
        TransactionMemoryRepository repo = new TransactionMemoryRepository();

        for (int i = 0; i < 10; i++){
            Transaction t = new Transaction("accAc", 5, LocalDate.now(), "tp", "cat", false);
            getMemory(repo).put(t.getId(), t);
        }

        int value = repo.getValueOfAccount("accAc");

        assertThat(value, is(50));

    }
}
