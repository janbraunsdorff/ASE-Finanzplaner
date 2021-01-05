package de.janbraunsdorff.ase.layer.presentation.web.controller.dashboard;

public class ResponseDashboardOverview {
    private final String total;
    private final String account;
    private final String deposit;
    private final String bar;

    private final int accountValue;
    private final int depositValue;
    private final int bartValue;

    public ResponseDashboardOverview(String total, String account, String deposit, String bar, int accountValue, int depositValue, int bartValue) {
        this.total = total;
        this.account = account;
        this.deposit = deposit;
        this.bar = bar;
        this.accountValue = accountValue;
        this.depositValue = depositValue;
        this.bartValue = bartValue;
    }

    public String getTotal() {
        return total;
    }

    public String getAccount() {
        return account;
    }

    public String getDeposit() {
        return deposit;
    }

    public String getBar() {
        return bar;
    }

    public int getAccountValue() {
        return accountValue;
    }

    public int getDepositValue() {
        return depositValue;
    }

    public int getBartValue() {
        return bartValue;
    }
}
