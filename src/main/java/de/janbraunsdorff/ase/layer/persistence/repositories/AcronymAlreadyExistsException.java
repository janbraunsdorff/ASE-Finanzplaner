package de.janbraunsdorff.ase.layer.persistence.repositories;

public class AcronymAlreadyExistsException extends Exception {
    public AcronymAlreadyExistsException(String acronym){
        super("Die Abkürzung "+ acronym+" existiert bereits im System");
    }
}
