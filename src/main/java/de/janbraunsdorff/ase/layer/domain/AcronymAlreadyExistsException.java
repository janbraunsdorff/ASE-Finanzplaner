package de.janbraunsdorff.ase.layer.domain;

public class AcronymAlreadyExistsException extends Exception {
    public AcronymAlreadyExistsException(String acronym) {
        super("Die Abkürzung " + acronym + " existiert bereits im System");
    }
}
