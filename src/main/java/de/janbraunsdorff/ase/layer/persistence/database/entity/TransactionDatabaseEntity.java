package de.janbraunsdorff.ase.layer.persistence.database.entity;

import de.janbraunsdorff.ase.layer.domain.transaction.Transaction;

import javax.persistence.*;
import java.time.LocalDate;

@Table(name = "transaction", schema = "public")
@Entity
public class TransactionDatabaseEntity {
    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "value", nullable = false)
    private Long value;

    @Column(name = "created")
    private LocalDate created;

    @Column(name = "valute", nullable = false)
    private LocalDate valute;

    @Column(name = "issuer", nullable = false)
    private String issuer;

    @Column(name = "category", nullable = false)
    private String category;

    @ManyToOne(optional = false)
    @JoinColumn(name = "account", nullable = false)
    private AccountDatabaseEntity account;

    @ManyToOne
    @JoinColumn(name = "contract")
    private ContractDatabaseEntity contract;

    public TransactionDatabaseEntity(String id, AccountDatabaseEntity account, Integer value, LocalDate date, String thirdParty, String category, ContractDatabaseEntity contract) {
        this.id = id;
        this.account = account;
        this.value = value.longValue();
        this.valute = date;
        this.issuer = thirdParty;
        this.category = category;
        this.contract = contract;
    }

    public TransactionDatabaseEntity() {
    }

    public ContractDatabaseEntity getContract() {
        return contract;
    }

    public void setContract(ContractDatabaseEntity contract) {
        this.contract = contract;
    }

    public AccountDatabaseEntity getAccount() {
        return account;
    }

    public void setAccount(AccountDatabaseEntity account) {
        this.account = account;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public LocalDate getValute() {
        return valute;
    }

    public void setValute(LocalDate valute) {
        this.valute = valute;
    }

    public LocalDate getCreated() {
        return created;
    }

    public void setCreated(LocalDate created) {
        this.created = created;
    }

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Transaction toDomain() {
        return new Transaction(id, account.getAcronym(), value.intValue(), valute, issuer, category, contract != null, contract !=null ? contract.getName(): "");
    }
}