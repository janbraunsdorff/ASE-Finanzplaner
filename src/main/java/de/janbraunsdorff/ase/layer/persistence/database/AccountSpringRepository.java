package de.janbraunsdorff.ase.layer.persistence.database;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface AccountSpringRepository extends CrudRepository<AccountDatabaseEntity, String> {
    AccountDatabaseEntity findByAcronym(String acronym);

    List<AccountDatabaseEntity> findByBankAcronymLike(String bank);

}
