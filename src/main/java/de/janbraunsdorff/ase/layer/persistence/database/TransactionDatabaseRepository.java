package de.janbraunsdorff.ase.layer.persistence.database;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import de.janbraunsdorff.ase.layer.persistence.database.entity.TransactionDatabaseEntity;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import de.janbraunsdorff.ase.layer.domain.AccountNotFoundException;
import de.janbraunsdorff.ase.layer.domain.TransactionNotFoundException;
import de.janbraunsdorff.ase.layer.domain.transaction.Transaction;
import de.janbraunsdorff.ase.layer.domain.transaction.TransactionRepository;

public class TransactionDatabaseRepository implements TransactionRepository {

    private final TransactionSpringRepository repo;
    private final AccountSpringRepository accountRepo;

    public TransactionDatabaseRepository(TransactionSpringRepository repo, AccountSpringRepository accountRepo){
        this.repo = repo;
        this.accountRepo = accountRepo;
    }

    @Override
    @Transactional
    public void createTransaction(Transaction entity) throws AccountNotFoundException {
        var account = accountRepo.findByAcronym(entity.getAccountAcronym());
        var en = new TransactionDatabaseEntity(entity.getId(), account, entity.getValue(), entity.getDate(), entity.getThirdParty(), entity.getCategory(), null);
        this.repo.save(en);
    }

    @Override
    @Transactional
    public Long getValueOfAccount(LocalDate start, LocalDate end, Set<String> accountAcronyms) {
        ArrayList<String> x3 = new ArrayList<>(accountAcronyms);
        List<TransactionDatabaseEntity> byDateBetweenAndAccountAcronymInOrderByDateDesc = this.repo.findInInterval(start, end, x3);
        return byDateBetweenAndAccountAcronymInOrderByDateDesc
                .stream()
                .map(TransactionDatabaseEntity::getValue)
                .reduce(0L, Long::sum);
    }

    @Override
    @Transactional
    public Long getValueOfAccount(String accountId) {
       return this.repo.findByAccountAcronymOrderByValuteDesc(accountId)
               .stream()
               .map(TransactionDatabaseEntity::getValue)
               .reduce(0L, Long::sum);
    }

    @Override
    @Transactional
    public List<Transaction> getTransactionOfAccount(String id, int count) {
        if (count == -1){
            count = Integer.MAX_VALUE;
        }
        Pageable topTen = PageRequest.of(0, count);
        return this.repo.findByAccountAcronymOrderByValuteDesc(id, topTen)
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
        return this.repo.findByValuteBetweenOrderByValuteDesc(start, end).stream().map(TransactionDatabaseEntity::toDomain).collect(Collectors.toList());
    }

    @Override
    public List<Transaction> getLastTransactions(int number) {
        Pageable topTen = PageRequest.of(0, number);
        return this.repo.findAllByOrderByValuteDesc(topTen)
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
            if (i+1 < collect.size() && t.getValute().isEqual(collect.get(i+1).getValute())) {
                continue;
            }

            if (maxValue < accountValue) {
                maxValue = accountValue;
            }


        }

        return maxValue;
    }

    @Override
    public Stream<Transaction> getTransactionByIds(List<String> ids) {
        var b =  this.repo.findAllById(ids).spliterator();
        return StreamSupport.stream(b ,false).map(TransactionDatabaseEntity::toDomain).sorted(Comparator.comparing(Transaction::getDate).reversed());
    }
}
