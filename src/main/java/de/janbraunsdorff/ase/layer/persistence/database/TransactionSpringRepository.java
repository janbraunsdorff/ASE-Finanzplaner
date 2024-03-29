package de.janbraunsdorff.ase.layer.persistence.database;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface TransactionSpringRepository extends CrudRepository<TransactionDatabaseEntity, String> {
    // group by Date and Account
    //SELECT TRANSACTION_DATE , ACCOUNT_ACRONYM , SUM(TRANSACTION_VALUE)  FROM TRANSACTION_DATABASE_ENTITY GROUP BY TRANSACTION_DATE, ACCOUNT_ACRONYM ORDER BY TRANSACTION_DATE DESC


    @Query(value = "SELECT * FROM transaction_database_entity WHERE transaction_date >= ?1 AND  transaction_date <= ?2  AND account_acronym  IN ?3 ORDER BY transaction_date", nativeQuery = true)
    List<TransactionDatabaseEntity> findInInterval(LocalDate x1, LocalDate x2, Collection<String> x3);


    List<TransactionDatabaseEntity> findByDateBetweenOrderByDateDesc(LocalDate x1, LocalDate x2);
    List<TransactionDatabaseEntity> findByAccountAcronymOrderByDateDesc(String x1);
    long countByAccountAcronym(String x1);

    List<TransactionDatabaseEntity> findByAccountAcronym(String id);
    Page<TransactionDatabaseEntity> findAllByOrderByDateDesc(Pageable topTen);

    List<TransactionDatabaseEntity> findByAccountAcronymOrderByDateDesc(String id, Pageable topTen);
}
