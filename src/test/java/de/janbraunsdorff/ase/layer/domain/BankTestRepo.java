package de.janbraunsdorff.ase.layer.domain;

import de.janbraunsdorff.ase.layer.domain.bank.Bank;
import de.janbraunsdorff.ase.layer.domain.bank.BankRepository;
import de.janbraunsdorff.ase.layer.persistence.AcronymAlreadyExistsException;
import de.janbraunsdorff.ase.layer.persistence.BankNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class BankTestRepo implements BankRepository {
    public Bank createBankEntity;
    public String deleteBankId;

    @Override
    public List<Bank> getBank() {
        return new ArrayList<Bank>() {{
            add(new Bank("name1", "b1"));
            add(new Bank("name2", "b2"));
            add(new Bank("name3", "b3"));
            add(new Bank("name4", "b4"));
        }};
    }

    @Override
    public Bank getBankByAcronym(String acronym) throws BankNotFoundException {
        if (acronym.equals("nonExistingBank")) {
            throw new BankNotFoundException(acronym);
        }

        return new Bank("bankName", acronym);
    }

    @Override
    public void createBank(Bank bankEntity) throws AcronymAlreadyExistsException {
        this.createBankEntity = bankEntity;
    }

    @Override
    public void deleteBankByAcronym(String bankId) {
        this.deleteBankId = bankId;
    }
}
