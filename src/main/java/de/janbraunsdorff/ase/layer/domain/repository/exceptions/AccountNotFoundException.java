package de.janbraunsdorff.ase.layer.domain.repository.exceptions;

public class AccountNotFoundException extends Exception {
    public AccountNotFoundException(String id){
        super("Account mit der ID oder der Abkürzung " + id + " wurde nicht gefunden");
    }
}
