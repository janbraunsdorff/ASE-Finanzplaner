package de.janbraunsdorff.ase.usecases.crud;

import de.janbraunsdorff.ase.tech.repositories.entität.BankEntity;

import java.util.List;

public interface ICrudBank {
    BankEntity get(String id);

    List<BankEntity> get();

    BankEntity create(BankEntity entity);

    BankEntity update(BankEntity entity);

    boolean delete(String id);
}
