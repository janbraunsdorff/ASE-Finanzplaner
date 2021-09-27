package de.janbraunsdorff.ase.layer.persistence.database;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import de.janbraunsdorff.ase.layer.domain.AccountNotFoundException;
import de.janbraunsdorff.ase.layer.domain.TransactionNotFoundException;
import de.janbraunsdorff.ase.layer.domain.transaction.Transaction;
import de.janbraunsdorff.ase.layer.domain.transaction.TransactionRepository;

public class TransactionDatabaseRepository implements TransactionRepository {

    private final TransactionSpringRepository repo;

    public TransactionDatabaseRepository(TransactionSpringRepository repo){
        this.repo = repo;
    }

    @Override
    @Transactional
    public void createTransaction(Transaction entity) throws AccountNotFoundException {
        var en = new TransactionDatabaseEntity(entity.getId(), entity.getAccountAcronym(), entity.getValue(), entity.getDate(), entity.getThirdParty(), entity.getCategory(), entity.getContract());
        this.repo.save(en);
    }

    @Override
    @Transactional
    public int getValueOfAccount(LocalDate start, LocalDate end, Set<String> accountAcronyms) {
        ArrayList<String> x3 = new ArrayList<>(accountAcronyms);
        List<TransactionDatabaseEntity> byDateBetweenAndAccountAcronymInOrderByDateDesc = this.repo.findInInterval(start, end, x3);
        return byDateBetweenAndAccountAcronymInOrderByDateDesc
                .stream()
                .map(TransactionDatabaseEntity::getValue)
                .reduce(0, Integer::sum);
    }

    @Override
    @Transactional
    public int getValueOfAccount(String accountId) {
       return this.repo.findByAccountAcronymOrderByDateDesc(accountId)
               .stream()
               .map(TransactionDatabaseEntity::getValue)
               .reduce(0, Integer::sum);
    }

    @Override
    @Transactional
    public List<Transaction> getTransactionOfAccount(String id, int count) {
        if (count == -1){
            count = Integer.MAX_VALUE;
        }
        Pageable topTen = PageRequest.of(0, count);
        return this.repo.findByAccountAcronymOrderByDateDesc(id, topTen)
                .stream()
                .map(TransactionDatabaseEntity::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Optional<Transaction> deleteTransactionById(String id) throws TransactionNotFoundException {
        return Optional.empty();
    }

    @Override
    @Transactional
    public List<Transaction> getTransactionOfAccount(List<String> account, LocalDate start, LocalDate end) {
        List<Transaction> collect = this.repo.findInInterval(start, end, account)
                .stream()
                .map(TransactionDatabaseEntity::toDomain)
                .collect(Collectors.toList());
        return collect;
    }

    @Override
    public Long count(String acronym) {
         return this.repo.countByAccountAcronym(acronym);
    }

    @Override
    public List<Transaction> getTransactions(LocalDate start, LocalDate end) {
        return this.repo.findByDateBetweenOrderByDateDesc(start, end).stream().map(TransactionDatabaseEntity::toDomain).collect(Collectors.toList());
    }

    @Override
    public List<Transaction> getLastTransactions(int number) {
        Pageable topTen = PageRequest.of(0, number);
        return this.repo.findAllByOrderByDateDesc(topTen)
                .stream()
                .map(TransactionDatabaseEntity::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public int getMaxValueOfAccount(String accountAcronym) {
        var collect = this.repo.findByAccountAcronym(accountAcronym);

        var maxValue = 0;
        var accountValue = 0;

        for (int i = 0; i < collect.size(); i++) {
            var t = collect.get(i);
            accountValue += t.getValue();
            if (i+1 < collect.size() && t.getDate().isEqual(collect.get(i+1).getDate())) {
                continue;
            }

            if (maxValue < accountValue) {
                maxValue = accountValue;
            }


        }

        return maxValue;
    }
}
