package de.janbraunsdorff.ase.layer.domain.repository.exceptions;

public class BankNotFoundExecption extends Exception{
    public BankNotFoundExecption(String errorMessage) {
        super("Bank mit der ID oder der Abkürzung " + errorMessage + " wurde nicht gefunden");
    }
}
