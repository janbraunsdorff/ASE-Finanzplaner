package de.janbraunsdorff.ase.layer.presentation.web.controller.account.data;

public class AllAccountRequest {
    private String orderBy;

    public AllAccountRequest() {
    }

    public AllAccountRequest(String orderBy) {
        this.orderBy = orderBy;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }
}
