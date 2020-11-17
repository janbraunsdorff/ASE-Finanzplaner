package de.janbraunsdorff.ase.layer.domain.crud.repository.exceptions;

public class IdAlreadyExitsException extends Exception{
    public IdAlreadyExitsException(String id){
        super("Die ID " + id + " existiert bereits im System");
    }
}
