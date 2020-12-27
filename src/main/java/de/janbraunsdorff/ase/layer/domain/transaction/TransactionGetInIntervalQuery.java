package de.janbraunsdorff.ase.layer.domain.transaction;

import java.time.LocalDate;

public final class TransactionGetInIntervalQuery {
    private final String account;
    private final LocalDate start;
    private final LocalDate end;

    public TransactionGetInIntervalQuery(String account, LocalDate start, LocalDate end) {
        this.account = account;
        this.start = start;
        this.end = end;
    }

    public String getAccount() {
        return account;
    }

    public LocalDate getStart() {
        return start;
    }

    public LocalDate getEnd() {
        return end;
    }
}
