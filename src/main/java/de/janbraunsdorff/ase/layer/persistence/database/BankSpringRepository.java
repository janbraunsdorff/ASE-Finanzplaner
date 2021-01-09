package de.janbraunsdorff.ase.layer.persistence.database;

import org.springframework.data.repository.CrudRepository;

public interface BankSpringRepository extends CrudRepository<BankDatabaseEntity, String> {
    BankDatabaseEntity findByAcronym(String acronym);
    void deleteByAcronym(String acronym);
}
