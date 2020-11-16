package de.janbraunsdorff.ase.layer.persistence.repositories;

public class AccountNotFoundException extends Exception {
    public AccountNotFoundException(String id){
        super("Account mit der ID oder der Abkürzung " + id + " wurde nicht gefunden");
    }
}
