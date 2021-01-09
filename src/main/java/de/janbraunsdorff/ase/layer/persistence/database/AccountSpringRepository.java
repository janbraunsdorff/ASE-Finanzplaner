package de.janbraunsdorff.ase.layer.persistence.database;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AccountSpringRepository extends CrudRepository<AccountDatabaseEntity, String> {
    AccountDatabaseEntity findByAcronym(String acronym);

    List<AccountDatabaseEntity> findByBankAcronymLike(String bank);

}
