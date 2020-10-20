package de.janbraunsdorff.ase.tech.repositories;

import java.io.IOException;
import java.util.List;

public interface CrudBankRepository {
    BankEntity get(String Id) throws IOException;
    List<BankEntity> get() throws IOException;
    BankEntity create(BankEntity bankEntity) throws Exception;
    BankEntity update(BankEntity bankEntity) throws IOException;
    boolean delete(String bankId) throws IOException;
}
