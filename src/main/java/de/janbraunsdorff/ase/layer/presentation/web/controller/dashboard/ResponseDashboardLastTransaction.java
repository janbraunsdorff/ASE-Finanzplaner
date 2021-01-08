package de.janbraunsdorff.ase.layer.presentation.web.controller.dashboard;

public class ResponseDashboardLastTransaction {
    private final String date;
    private final String thp;
    private final String value;
    private final String account;
    private final String note;

    public ResponseDashboardLastTransaction(String date, String thp, String value, String account, String note) {
        this.date = date;
        this.thp = thp;
        this.value = value;
        this.account = account;
        this.note = note;
    }

    public String getDate() {
        return date;
    }

    public String getThp() {
        return thp;
    }

    public String getValue() {
        return value;
    }

    public String getAccount() {
        return account;
    }

    public String getNote() {
        return note;
    }
}
