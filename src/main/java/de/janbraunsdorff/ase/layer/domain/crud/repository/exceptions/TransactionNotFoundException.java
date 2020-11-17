package de.janbraunsdorff.ase.layer.domain.crud.repository.exceptions;

public class TransactionNotFoundException extends Exception{

    public TransactionNotFoundException(String id){
        super("Transaktion mit der ID " + id + " wurde nicht gefunden");
    }
}
