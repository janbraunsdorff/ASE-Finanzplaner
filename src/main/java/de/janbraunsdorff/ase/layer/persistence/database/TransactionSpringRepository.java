package de.janbraunsdorff.ase.layer.persistence.database;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import de.janbraunsdorff.ase.layer.persistence.database.entity.TransactionDatabaseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface TransactionSpringRepository extends CrudRepository<TransactionDatabaseEntity, String> {
    // group by Date and Account
    //SELECT TRANSACTION_DATE , ACCOUNT_ACRONYM , SUM(TRANSACTION_VALUE)  FROM TRANSACTION_DATABASE_ENTITY GROUP BY TRANSACTION_DATE, ACCOUNT_ACRONYM ORDER BY TRANSACTION_DATE DESC


    @Query(value = "SELECT * FROM transaction WHERE valute >= ?1 AND  valute <= ?2  AND account  IN ?3 ORDER BY valute", nativeQuery = true)
    List<TransactionDatabaseEntity> findInInterval(LocalDate x1, LocalDate x2, Collection<String> x3);


    List<TransactionDatabaseEntity> findByAccountAcronymOrderByValuteDesc(String x1);
    List<TransactionDatabaseEntity> findByValuteBetweenOrderByValuteDesc(LocalDate x1, LocalDate x2);
    long countByAccountAcronym(String x1);

    List<TransactionDatabaseEntity> findByAccountAcronym(String id);
    Page<TransactionDatabaseEntity> findAllByOrderByValuteDesc(Pageable topTen);

    List<TransactionDatabaseEntity> findByAccountAcronymOrderByValuteDesc(String id, Pageable topTen);
}
