package de.janbraunsdorff.ase.tech.repositories;

import java.util.List;

public interface CrudBankRepository {
    BankEntity get(String Id) throws Exception;
    List<BankEntity> get() throws Exception;
    BankEntity create(BankEntity bankEntity) throws Exception;
    BankEntity update(BankEntity bankEntity) throws Exception;
    boolean delete(String bankId) throws Exception;
}
