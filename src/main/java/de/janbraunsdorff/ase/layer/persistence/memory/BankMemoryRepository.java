package de.janbraunsdorff.ase.layer.persistence.memory;

import de.janbraunsdorff.ase.layer.domain.bank.Bank;
import de.janbraunsdorff.ase.layer.domain.repository.BankRepository;
import de.janbraunsdorff.ase.layer.domain.repository.exceptions.AcronymAlreadyExistsException;
import de.janbraunsdorff.ase.layer.domain.repository.exceptions.BankNotFoundExecption;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BankMemoryRepository implements BankRepository {

    private Map<String, Bank> memory = new HashMap<>();
    private Map<String, String> acToId = new HashMap<>();


    @Override
    public List<Bank> getBank() {
        return new ArrayList<>(memory.values());
    }

    @Override
    public Bank getBankByAcronym(String acronym) throws BankNotFoundExecption {
        if (!this.acToId.containsKey(acronym)) {
            throw new BankNotFoundExecption(acronym);
        }
        String id = acToId.get(acronym);
        return this.memory.get(id);
    }

    @Override
    public Bank getBank(String id) throws BankNotFoundExecption {
        if (!this.memory.containsKey(id)) {
            throw new BankNotFoundExecption(id);
        }
        return this.memory.get(id);
    }

    @Override
    public void createBank(Bank bank) throws AcronymAlreadyExistsException {
        if (this.acToId.containsKey(bank.getAcronym())) {
            throw new AcronymAlreadyExistsException(bank.getAcronym());
        }
        this.acToId.put(bank.getAcronym(), bank.getId());
        this.memory.put(bank.getId(), bank);
    }

    @Override
    public void deleteBankById(String bankId) {
        Bank b = this.memory.get(bankId);
        if (b == null) {
            return;
        }
        this.acToId.remove(b.getAcronym());
        this.memory.remove(b.getId());
    }

    @Override
    public void deleteBankByAcronym(String bankId) {
        String id = this.acToId.get(bankId);
        this.memory.remove(id);
        this.acToId.remove(bankId);

    }
}
