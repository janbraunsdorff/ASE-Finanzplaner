package de.janbraunsdorff.ase.layer.persistence.memory;

import de.janbraunsdorff.ase.layer.domain.bank.Bank;
import de.janbraunsdorff.ase.layer.domain.bank.BankRepository;
import de.janbraunsdorff.ase.layer.persistence.AcronymAlreadyExistsException;
import de.janbraunsdorff.ase.layer.persistence.BankNotFoundException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BankMemoryRepository implements BankRepository {

    private final Map<String, Bank> memory = new HashMap<>();


    @Override
    public List<Bank> getBank() {
        return new ArrayList<>(memory.values());
    }

    @Override
    public Bank getBankByAcronym(String acronym) throws BankNotFoundException {
        if (!this.memory.containsKey(acronym)) {
            throw new BankNotFoundException(acronym);
        }
        return this.memory.get(acronym);
    }

    @Override
    public void createBank(Bank bank) throws AcronymAlreadyExistsException {
        if (this.memory.containsKey(bank.getAcronym())) {
            throw new AcronymAlreadyExistsException(bank.getAcronym());
        }
        this.memory.put(bank.getAcronym(), bank);
    }

    @Override
    public void deleteBankByAcronym(String bankId) {
        this.memory.remove(bankId);
    }
}
