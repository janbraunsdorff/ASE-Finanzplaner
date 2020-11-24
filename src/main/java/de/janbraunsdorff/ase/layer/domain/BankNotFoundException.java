package de.janbraunsdorff.ase.layer.domain;

public class BankNotFoundException extends Exception {
    public BankNotFoundException(String errorMessage) {
        super("Bank mit der ID oder der Abkürzung " + errorMessage + " wurde nicht gefunden");
    }
}
