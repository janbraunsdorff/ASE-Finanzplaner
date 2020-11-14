package de.janbraunsdorff.ase.layer.persistence.repositories;

public class BankNotFoundExecution extends Exception{
    public BankNotFoundExecution(String errorMessage) {
        super("Bank mit der ID oder der Abkürzung " + errorMessage + " wurde nicht gefunden");
    }
}
