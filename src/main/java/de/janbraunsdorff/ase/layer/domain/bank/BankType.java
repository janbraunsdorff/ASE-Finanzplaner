package de.janbraunsdorff.ase.layer.domain.bank;

import java.util.Arrays;

public enum BankType {
    None("---"),
    Retail("Retail Bank"),
    Investment("Investment Bank");

    private final String name;

    BankType(String name) {
        this.name = name;
    }

    public static BankType getByName(String name){
        return Arrays.stream(BankType.values()).filter(a -> a.name.equals(name)).findFirst().orElse(None);
    }

    public String getName() {
        return name;
    }
}
