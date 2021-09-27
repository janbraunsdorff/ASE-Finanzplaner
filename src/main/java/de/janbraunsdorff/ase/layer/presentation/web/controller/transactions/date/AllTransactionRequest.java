package de.janbraunsdorff.ase.layer.presentation.web.controller.transactions.date;

public class AllTransactionRequest {
    private String account;
    private Integer count;

    public AllTransactionRequest(String account, Integer count) {
        this.account = account;
        this.count = count;
    }

    public AllTransactionRequest() {
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
