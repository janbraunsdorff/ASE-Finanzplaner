package de.janbraunsdorff.ase.layer.domain.transaction;

import java.time.LocalDate;
import java.util.List;

public final class TransactionGetInIntervalQuery {
    private final List<String> account;
    private final LocalDate start;
    private final LocalDate end;

    public TransactionGetInIntervalQuery(List<String> account, LocalDate start, LocalDate end) {
        this.account = account;
        this.start = start;
        this.end = end;
    }

    public List<String> getAccount() {
        return account;
    }

    public LocalDate getStart() {
        return start;
    }

    public LocalDate getEnd() {
        return end;
    }
}
