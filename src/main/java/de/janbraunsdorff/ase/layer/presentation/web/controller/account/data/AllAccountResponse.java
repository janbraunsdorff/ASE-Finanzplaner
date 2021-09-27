package de.janbraunsdorff.ase.layer.presentation.web.controller.account.data;

public class AllAccountResponse {
    private String id;
    private String bankName;
    private String name;
    private String img;
    private String balance;
    private Boolean isPositive;

    public AllAccountResponse(String id, String bankName, String name, String img, String balance, Boolean isPositive) {
        this.id = id;
        this.bankName = bankName;
        this.name = name;
        this.img = img;
        this.balance = balance;
        this.isPositive = isPositive;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public Boolean isPositive() {
        return isPositive;
    }

    public void setPositive(Boolean positive) {
        isPositive = positive;
    }
}
