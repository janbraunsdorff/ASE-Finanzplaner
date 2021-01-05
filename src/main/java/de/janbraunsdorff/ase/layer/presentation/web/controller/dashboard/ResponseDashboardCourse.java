package de.janbraunsdorff.ase.layer.presentation.web.controller.dashboard;

public class ResponseDashboardCourse {
    private final int account;
    private final int deposit;

    public ResponseDashboardCourse(int account, int deposit) {
        this.account = account;
        this.deposit = deposit;
    }

    public int getAccount() {
        return account;
    }

    public int getDeposit() {
        return deposit;
    }
}
