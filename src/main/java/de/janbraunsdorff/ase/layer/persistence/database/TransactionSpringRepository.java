package de.janbraunsdorff.ase.layer.persistence.database;

import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

public interface TransactionSpringRepository extends CrudRepository<TransactionDatabaseEntity, String> {

    List<TransactionDatabaseEntity> findByDateBetweenAndAccountAcronymInOrderByDateDesc(LocalDate x1, LocalDate x2, Collection<String> x3);
    List<TransactionDatabaseEntity> findByDateBetweenOrderByDateDesc(LocalDate x1, LocalDate x2);
    List<TransactionDatabaseEntity> findByAccountAcronymOrderByDateDesc(String x1);
    long countByAccountAcronym(String x1);

    List<TransactionDatabaseEntity> findByAccountAcronym(String id);
    List<TransactionDatabaseEntity> findAllByOrderByDateDesc();

}
