package de.janbraunsdorff.ase.layer.domain.repo;

import de.janbraunsdorff.ase.layer.domain.AcronymAlreadyExistsException;
import de.janbraunsdorff.ase.layer.domain.BankNotFoundException;
import de.janbraunsdorff.ase.layer.domain.bank.Bank;
import de.janbraunsdorff.ase.layer.domain.bank.BankRepository;

import java.util.List;

public class TestBankRepository implements BankRepository {
    private List<Bank> banks;
    private Bank bank;

    public boolean getBankWasCalled;
    public String getBankByAcronym;
    public Bank createBank;
    public String deleteBankByAcronym;

    public TestBankRepository(List<Bank> banks) {
        this.banks = banks;
    }

    public TestBankRepository(Bank bank) {
        this.bank = bank;
    }


    @Override
    public List<Bank> getBank() {
        this.getBankWasCalled = true;
        return banks;
    }

    @Override
    public Bank getBankByAcronym(String acronym) throws BankNotFoundException {
        this.getBankByAcronym = acronym;
        return bank;
    }

    @Override
    public void createBank(Bank bankEntity) throws AcronymAlreadyExistsException {
        this.createBank = bankEntity;
    }

    @Override
    public void deleteBankByAcronym(String acronym) {
        this.deleteBankByAcronym = acronym;
    }
}
