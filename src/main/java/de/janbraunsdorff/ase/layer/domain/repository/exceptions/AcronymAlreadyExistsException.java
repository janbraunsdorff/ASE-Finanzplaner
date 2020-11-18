package de.janbraunsdorff.ase.layer.domain.repository.exceptions;

public class AcronymAlreadyExistsException extends Exception {
    public AcronymAlreadyExistsException(String acronym){
        super("Die Abkürzung "+ acronym+" existiert bereits im System");
    }
}
