package de.janbraunsdorff.ase.layer.domain;

public class TransactionNotFoundException extends Exception {
    public TransactionNotFoundException(String errorMessage) {
        super("Transaction mit der ID " + errorMessage + " wurde nicht gefunden");
    }
}
