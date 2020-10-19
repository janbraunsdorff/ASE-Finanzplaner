package de.janbraunsdorff.ase.tech.repositories;

import java.util.List;

public interface CrudBankRepository {
    BankEntity get(String Id);
    List<BankEntity> get();
    BankEntity create(BankEntity bankEntity);
    BankEntity update(BankEntity bankEntity);
    boolean delete(String bankId);
}
