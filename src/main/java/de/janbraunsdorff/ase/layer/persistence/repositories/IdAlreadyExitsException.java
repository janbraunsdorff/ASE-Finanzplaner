package de.janbraunsdorff.ase.layer.persistence.repositories;

public class IdAlreadyExitsException extends Exception{
    public IdAlreadyExitsException(String id){
        super("Die ID " + id + " existiert bereits im System");
    }
}
