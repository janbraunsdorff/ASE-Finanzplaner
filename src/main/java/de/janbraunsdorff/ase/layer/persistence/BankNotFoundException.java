package de.janbraunsdorff.ase.layer.persistence;

public class BankNotFoundException extends Exception{
    public BankNotFoundException(String errorMessage) {
        super("Bank mit der ID oder der Abkürzung " + errorMessage + " wurde nicht gefunden");
    }
}
